
# z/OS STCK → Time Fields → Julian (YYYYDDD) Sample

This package contains a minimal, production-style example that:

1. Reads the **Store Clock** (STCK) token on z/OS.
2. Uses a called HLASM subroutine (via IBM **STCKCONV**) to parse time fields.
3. Calls a COBOL subroutine to compute/format a **Julian date** (ordinal date `YYYYDDD`).

> **References**
>
> * STCK/ETOD and conversion service (`STCKCONV`) are documented by IBM; `STCKCONV` converts a TOD value into human‑readable time/date formats compatible with `TIME` macro formats. citeturn1search10turn1search1
> * STCK returns a 64‑bit TOD clock value (time since 1900‑01‑01); background and rollover notes. citeturn1search14turn1search3
> * Time‑zone/leap‑second adjustments for local time (CVTLDTO/CVTLSO) precede `STCKCONV` if needed. citeturn1search18
> * COBOL intrinsic functions for ordinal dates ("Julian" `YYYYDDD`). citeturn1search8

---

## Contents

```
CLKMAIN.cbl      # Main COBOL program (drives STCK read, parse, and Julian build)
JULIAN7.cbl      # COBOL subroutine: YEAR(YYYY) + DDD -> YYYYDDD
STCKREAD.asm     # HLASM subroutine: issues STCK and returns 8-byte TOD token
STCKPARSE.asm    # HLASM subroutine: STCKCONV+UNPK -> YYYY, DDD, HH, MM, SS
CLKBUILD.jcl     # One-job example to assemble/compile/link the set
```

---

## Build & Run (Batch)

1. **Edit `CLKBUILD.jcl`** and set your libraries:
   * COBOL compiler loadlib (e.g., `IGY.V6R3M0.SIGYCOMP`).
   * Macro libraries (e.g., `SYS1.MACLIB`).
   * Target `YOUR.LOADLIB`.
2. **Submit** `CLKBUILD.jcl`.
3. To run, add the commented `//RUN` step at the bottom with `PGM=CLKMAIN`.

For **CICS**, use the CICS translators/procs and ensure programs are reentrant. citeturn1search26

---

## UTC vs Local Time

`STCK`/`STCKCONV` produce **UTC**. To obtain local time, adjust the TOD value by adding `CVTLDTO` (local offset) and subtracting `CVTLSO` (leap seconds) before calling `STCKCONV`. `STCKCONV` itself does not apply a time‑zone. citeturn1search18

---

## 2042 Rollover (classic STCK)

Classic 64‑bit `STCK` encodes TOD bits that wrap around in **Sep‑17‑2042**; for long‑horizon apps or sysplex‑unique timestamps, consider `STCKE` (ETOD) and the `STCKEVAL` path of `STCKCONV`. citeturn1search14

---

## Files

### `CLKMAIN.cbl`
* Calls `STCKREAD` → 8‑byte token
* Calls `STCKPARSE` → YEAR, DDD, HH, MM, SS (text)
* Calls `JULIAN7` → `YYYYDDD`

### `JULIAN7.cbl`
* Simple STRING of `YYYY` and `DDD`.

### `STCKREAD.asm`
* Issues `STCK` and returns the 8‑byte TOD token by reference. citeturn1search14

### `STCKPARSE.asm`
* Executes `STCKCONV TIMETYPE=DEC, DATETYPE=YYYYDDD` then UNPKs into EBCDIC text. Layout is compatible with `TIME` macro formats per IBM docs. citeturn1search10turn1search1

### `CLKBUILD.jcl`
* Assembles both HLASM modules, compiles both COBOL modules, and links into one loadlib. Example procs/styles adapted from standard IBM/Broadcom documentation patterns. citeturn1search26

---

## Caveats

* All code is sample quality but reentrant and LE‑friendly from a linkage perspective; harden as needed for your environment.
* If your macro level differs, verify the offsets once (use a `DISPLAY` of the unpacked fields) and adjust if required. `STCKCONV` remains the supported conversion path. citeturn1search10

---

## Output

You should see something like:

```
UTC Time  : 13:57:36
Julian    : 2024123
```

(Values depend on runtime.)

---

© 2026 Example – for instructional use.
