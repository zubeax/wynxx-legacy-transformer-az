# SortDtaExternal.md — Extended Java Program Notes

This document consolidates **all notes** for the extended (large‑dataset) Java implementation of the COBOL `SORTDTA` program. It covers design goals, algorithm, configuration, operations, performance tuning, and extension ideas.

---

## 1) Purpose & Compatibility
- **Goal**: Faithfully reproduce COBOL behavior: read fixed‑length 80‑char records, sort **ascending by BLZ (8) then KTO (10)**, write records unchanged.
- **Compatibility**: Keeps exact field boundaries and fixed‑width layout: `BLZ[0..7]`, `KTO[8..17]`, `REST[18..79]`.
- **Charset**: Uses `ISO-8859-1` to ensure 1:1 char→byte mapping for safe substring slicing. If your data is **EBCDIC**, transcode before running (or extend the program to handle it explicitly).

---

## 2) High‑Level Design
To handle inputs around **100 GB** (and beyond), the program implements an **external merge sort**:

1. **Run Generation (Chunking)**
   - Stream the input line‑by‑line.
   - Normalize each line to 80 chars (right‑pad or truncate).
   - Accumulate up to `TARGET_RECORDS_PER_CHUNK` records in memory.
   - Sort in memory by `(BLZ, KTO)`; spill as a **sorted run** to a temp file.

2. **K‑Way Merge**
   - Open all run files.
   - Use a min‑heap (`PriorityQueue`) ordered by `(BLZ, KTO)` to merge.
   - Always write exactly 80‑char records (plus newline) to the output.

This keeps memory bounded by **O(chunk size)** and makes performance mostly a function of sequential I/O throughput.

---

## 3) Key Source Files
- `SortDta.java` — external‑merge‑sorting implementation (production code)
- `README.md` — overview and quickstart
- `sort_external.md` — focused doc on external sort (this file is the comprehensive version)
- Scripts: `build.sh`, `run.sh`, `build.ps1`, `run.ps1`, `test.sh`, `verify_fixed_length.sh`, `Makefile`

---

## 4) Configuration & Tuning

### Constants in `SortDta.java`
- `TARGET_RECORDS_PER_CHUNK` (default **2,000,000**) — number of records per in‑memory chunk. Increase if you allocate a larger heap.
- `IO_BUFFER_SIZE` (default **1 MiB**) — buffered I/O size. 1–8 MiB is typical.
- Temp file prefix: `sortdta_run_` (created under the JVM temp directory by default).

### Runtime Parameters (via scripts)
- **Heap size**: set `JAVA_OPTS`, e.g. `-Xms4g -Xmx16g`.
- **Temp directory**: set `TMPDIR` (Linux/macOS) or `%TEMP%` (Windows). Scripts forward it to the JVM (`-Djava.io.tmpdir=...`).

### Practical Guidelines
- Place temp on a **fast SSD/NVMe** with free space ≳ input size.
- If you produce **many run files**, either raise file descriptor limits or perform **staged merges** (multi‑pass merge).
- Prefer larger, fewer chunks (up to your heap comfort zone) to reduce the number of run files.

---

## 5) Operations

### Build
```bash
./build.sh
# Windows
./build.ps1
```

### Run (large input)
```bash
# Linux/macOS (example with larger heap and fast temp)
TMPDIR=/mnt/nvme/tmp JAVA_OPTS='-Xms4g -Xmx16g' ./run.sh input.txt output.txt

# Windows (PowerShell)
$env:TEMP='D:\fasttmp'
$env:JAVA_OPTS='-Xms4g -Xmx16g'
./run.ps1 -InputFile input.txt -OutputFile output.txt
```

### Verify fixed width (optional)
```bash
./verify_fixed_length.sh output.txt
```

---

## 6) Record Semantics & Normalization
- Input lines are **padded** with spaces if shorter than 80, **truncated** if longer.
- Field slices:
  - `BLZ`: `line.substring(0, 8)`
  - `KTO`: `line.substring(8, 18)`
  - `REST`: `line.substring(18, 80)`
- Sort order is **lexicographic** on `BLZ`, then `KTO`.

> If stable ordering among identical keys is required, add a tertiary tie‑breaker (e.g., original record ordinal within each run) and propagate it through the merge.

---

## 7) Performance Notes
- **I/O**: Predominantly sequential. Use larger I/O buffers and fast storage.
- **Chunk size**: Larger chunks reduce number of runs and heap fragmentation; balance against GC pressure.
- **Merge fan‑in**: If the number of run files is huge, merge in stages (e.g., groups of 64) before a final pass.
- **Compression (optional)**: Spilling runs with gzip can reduce I/O but increases CPU. Requires gzip‑aware readers/writers.

---

## 8) Error Handling & Robustness
- Input lines of unexpected length are normalized; consider logging counters for **short/long** lines.
- Detect and fail early on disk‑space exhaustion in the temp directory.
- Ensure the process cleans up temp files (the current code attempts cleanup even on partial failures).

---

## 9) Extensibility
Potential enhancements:
- **CLI flags**: `--chunk-records`, `--tmp`, `--charset`, `--max-open-files`.
- **Multi‑pass merge**: explicit staged merge for strict FD limits.
- **Parallel run generation**: sort chunks with parallel sort or worker pool.
- **Unit tests**: synthetic large datasets, checksum validation.
- **Charset module**: optional EBCDIC↔ASCII conversion step.

---

## 10) Troubleshooting
- **Slow runtime** → Verify temp is on SSD/NVMe; raise `TARGET_RECORDS_PER_CHUNK`; increase heap; ensure no antivirus scans temp/output paths.
- **Too many open files** → Increase OS limit or enable staged merges.
- **OutOfMemoryError** → Lower chunk size or increase `-Xmx`.
- **Broken fixed width** → Run `verify_fixed_length.sh` and check upstream producers.

---

**Version**: Extended external‑merge‑sorting `SortDta.java` (generated by M365 Copilot)