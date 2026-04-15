# z/OS JES2 JCL with in-stream REXX → Java + Shell Conversion

This project ports the original mainframe job that ran a **REXX** generator via **IRXJCL** and then sorted the output with **ICETOOL** into a portable solution using **Java** and simple **shell scripts**.

- **PayGen.java** – replicates the REXX logic to create fixed-length (FB) records (LRECL=200)
- **PaySort.java** – replicates the ICETOOL `SORT FIELDS=(1,8,CH,A,10,34,CH,A)`
- **build.sh / run.sh** – mimic the two job steps (delete → generate → sort)

No external dependencies required. Just `javac` / `java` (Java 8+).

---

## 1) Original Job Summary (What the JCL/REXX did)

1. **RESET OUTPUT DATASET** – `IEFBR14` deleted prior `RFPLT.PAYGEN.SORTED` dataset.
2. **Generate** – `IRXJCL` invoked an in-stream **REXX** program:
   - Parameters: `MIN=YYYYMMDD MAX=YYYYMMDD COUNT=N`
   - Reads `INACCTS` (accounts) and `INPURP` (purposes) from in-stream DDs
   - Randomly creates payment transactions between `MIN` and `MAX` dates
   - Ensures fixed-length **200-byte** records (FB, LRECL=200)
   - Writes to `OUTTRN` temporary dataset
3. **Sort** – `ICETOOL` sorted `OUTTRN` by:
   - Primary: columns **1–8** (date `YYYYMMDD` ascending)
   - Secondary: columns **10–43** (creditor account, ascending)
   - Using `OPTION EQUALS` (stable)

---

## 2) Mapping to Java + Shell

| Mainframe Piece | Mainframe Behavior | Java/Shell Equivalent |
|---|---|---|
| `IEFBR14` delete | Reset output dataset | `rm -f out/*.txt` inside `run.sh` |
| `IRXJCL` + REXX + PARM | Generate fixed FB 200 records | `PayGen.java` with `--min --max --count` |
| `INACCTS`, `INPURP` in-stream | Input lists | Flat files `data/INACCTS.txt`, `data/INPURP.txt` |
| `EXECIO` read/write | Sequential I/O | Buffered file I/O (UTF-8) |
| LRECL=200 | Fixed-width records | Java formatter pads/truncates to exactly 200 chars |
| `ICETOOL` sort `(1,8)+(10,34)` | Stable sort | `PaySort.java` comparator on `[0,8)` then `[9,43)` |

> Note: No external libraries; portable across Linux/macOS/Windows (use WSL or Git Bash on Windows for the shell scripts).

---

## 3) Project Layout

```
paygen/
├─ src/
│  ├─ PayGen.java
│  └─ PaySort.java
├─ data/
│  ├─ INACCTS.txt
│  └─ INPURP.txt
├─ out/
│  ├─ unsorted.txt   (created by PayGen)
│  └─ sorted.txt     (created by PaySort)
├─ build.sh
└─ run.sh
```

---

## 4) Build & Run

```bash
# Build (compiles PayGen and PaySort into ./out)
./build.sh

# Run (mimics JCL: delete → generate → sort)
# Args: MIN MAX COUNT
./run.sh 20260101 20261231 1000
```

Artifacts:
- `out/unsorted.txt` – generator output (FB 200, one record per line)
- `out/sorted.txt` – final sorted output

If you want deterministic generation for testing, add a seed when invoking PayGen directly:

```bash
java -cp out PayGen \
  --min=20260101 --max=20261231 --count=10 \
  --accounts=data/INACCTS.txt --purposes=data/INPURP.txt \
  --out=out/unsorted.txt --seed=42
```

---

## 5) Generator Details (`PayGen.java`)

Behavior identical to the REXX script:

- **Parameters**: `--min=YYYYMMDD --max=YYYYMMDD --count=N [--accounts=path] [--purposes=path] [--out=path] [--seed=long]`
- **Validations**: format checks; `count>=1`; at least 2 accounts and 1 purpose
- **Random selection**: distinct creditor/debtor; uniform random date in inclusive range
- **Amount**: euros `[1..1000]`, cents `[0..99]`; 10-digit **cents** with zero-left padding
- **Record layout** (exact FB 200):

```
1-8    : date YYYYMMDD
9      : space
10-43  : creditor account (34, left-justified, space-padded)
44-73  : creditor name    (30, left-justified)
74-107 : debtor account   (34, left-justified)
108-137: debtor name      (30, left-justified)
138-147: amount in cents  (10, numeric, zero-left-padded)
148-150: literal 'EUR'
151-200: purpose          (50, left-justified)
```

The program pads/truncates fields so each line is **exactly 200 characters**.

---

## 6) Sorter Details (`PaySort.java`)

Replicates `ICETOOL`:

- Stable sort (like `OPTION EQUALS`)
- Keys:
  - Primary: columns **1–8** (substring `[0,8)`)
  - Secondary: columns **10–43** (substring `[9,43)`)
- Validates that all input lines are exactly 200 characters

---

## 7) Notes & Parity with Mainframe Behavior

- **Fixed-length 200** enforced on generation and validated before sort
- **Sorting** matches `SORT FIELDS=(1,8,CH,A,10,34,CH,A)`
- **Dataset reset** step is reproduced by removing previous output files
- **Encoding**: files are UTF‑8 text; fields are padded with ASCII spaces
- **Portability**: no external deps; compiles with `javac` (Java 8+)
- **BLKSIZE vs LRECL**: ICETOOL's historical requirement `BLKSIZE==LRECL` is not applicable here, but output record size parity is maintained
- **IBANs**: content is treated as opaque strings; no IBAN validation performed

---

## 8) Example Data

The `data/INACCTS.txt` and `data/INPURP.txt` files are included and originate from the in-stream DDs in the original JCL. You may freely edit or replace them.

---

## 9) Troubleshooting

- **"Generated line not 200 chars"** – indicates a formatting bug or field size issue; open an issue with the exact inputs
- **"Input line ... is not 200 chars"** – the sorter validates LRECL; ensure the generator or any editing preserved column widths
- **Windows** – use Git Bash or WSL; after cloning, run `chmod +x build.sh run.sh`

---

## 10) License

Sample code – use freely within your organization.
