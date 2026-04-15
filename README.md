# SECTDRIV – COBOL Section Driver (Enterprise COBOL 6.4)

This project delivers a fixed-format COBOL batch **section driver** that organizes
**dedicated sections for many COBOL statements and *all* documented intrinsic functions (per IBM 6.4 list)**.
Each section parses dynamic parameters from an FB **LRECL 132** input file `SYS010` and logs
outputs to an FB **LRECL 132** log (`SYS011`). A separate section demonstrates reading a **KSDS**
`SYS020` by 8-byte key. All sections end with an explicit `EXIT`.

> **Formatting rules**
>
> * Comment lines have `*` in **column 7**.
> * Source lines are wrapped so no text extends beyond **column 72**;
>   continuation lines use `-` in column 7 and start in Area B.
> * Working-Storage item names are kept intact; long `PIC`/`USAGE` clauses are wrapped to the next line.
>
> These behaviors follow IBM's fixed-format rules and continuation guidance.

## Structure

```
src/SECTDRIV.cbl          # Main program (fixed-format) with sections
jcl/BUILD.jcl             # Compile (IGYCRCTL) and link-edit (IEWL) JCL
jcl/ALLOC_LOAD_KSDS.jcl   # IDCAMS JCL to define and load VSAM KSDS
jcl/RUN_SECTDRIV.jcl      # Execution JCL
data/sys010.sample        # Sample FB 132 input (10 records per section)
data/sys020.load          # Sample KSDS load data (FB 132)
README.md                 # This file
```

### Files and DD names
- **SYS010**: FB **LRECL 132** input. Columns:
  - 1–8: Section ID (8 chars) – free text, echoed to the log.
  - 10–39: Operation (function name or statement keyword).
  - 41–132: Comma-separated parameters for that operation.
- **SYS011**: FB **LRECL 132** log. Each line begins with the 8-char Section ID, then a space and message.
- **SYS020**: KSDS (132 bytes), key is **first 8 bytes**.

### Driver behavior
1. `MAIN-SECTION` opens files, reads `SYS010` sequentially.
2. For each record, it fills `WS-SECID`, `WS-OP-NAME`, and `WS-ARG-TEXT`.
3. `PARSE-ARGS` splits parameters by comma into `WS-ARGn-STR` and `WS-ARGn-NUM` (also using `NUMVAL`).
4. `DISPATCH-SECTION` `EVALUATE`s `WS-OP-NAME` and `PERFORM`s the matching section.
5. Each section computes a result and calls `LOG-SECTION` which writes a 132-byte log record to `SYS011`.

## Intrinsic functions covered
All intrinsic functions listed in the IBM **Enterprise COBOL 6.4** language reference are implemented with a dedicated section and a function call. Examples: `ABS`, `ACOS`, `ANNUITY`, `BIT-OF`, `BYTE-LENGTH`, `COMBINED-DATETIME`, `CONTENT-OF`, `CURRENT-DATE`, `DATE-TO-YYYYMMDD`, `E`, `EXP`, `EXP10`, `FACTORIAL`, `FORMATTED-…`, `HEX-…`, `INTEGER-…`, `LENGTH`, `LOG`, `LOG10`, `LOWER-CASE`, `MAX`, `MEAN`, `MEDIAN`, `MIDRANGE`, `MIN`, `MOD`, `NATIONAL-OF`, `NUMVAL*`, `ORD*`, `PI`, `PRESENT-VALUE`, `RANDOM`, `RANGE`, `REM`, `REVERSE`, `SECONDS-…`, `SIGN`, `SIN`, `SQRT`, `STANDARD-DEVIATION`, `SUM`, `TAN`, `TEST-…`, `TRIM`, `U* (UTF-8/UTF-16 helpers)`, `UUID4`, `VARIANCE`, `WHEN-COMPILED`, `YEAR-TO-YYYY`.

> The program **parses per-function arguments** from `SYS010` dynamically and supplies them to the function call in the relevant section.

## Statement sections
Representative sections are provided for common COBOL statements: arithmetic (`ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`, `COMPUTE`), data movement (`MOVE`, `STRING`, `UNSTRING`, `INSPECT`, `INITIALIZE`), control flow (`IF`, `EVALUATE`, `PERFORM`, `CONTINUE`, `EXIT`, `GOBACK`, `STOP RUN`), file handling (`OPEN`, `READ`, `WRITE`, `REWRITE`, `START`, `CLOSE`, `RETURN`, `RELEASE`, `MERGE`, `SORT`, `SEARCH`, `SET`), and JSON (`JSON PARSE`, `JSON GENERATE`). Each one logs input/output with its own 8-char ID.

## Sample input (data/sys010.sample)
A **10-record sample** is generated for **each function and statement section**, plus a `KSDS-READ` demo. Records are 132 chars. Example (visualized):

```
FNABS   | ABS                           | 1
FNLOG10 | LOG10                         | 1000
STADD001| ADD                           | 1,2
KSDS0001| KSDS-READ                     | KEY00001
```

## VSAM KSDS
A KSDS named `&SYSUID..SECT.SYS020.CLUSTER` is defined with `KEYS(8 0)` and `RECORDSIZE(132 132)`. Load data is supplied in `data/sys020.load` and can be uploaded to `&SYSUID..SECT.SYS020.LOAD` for the `REPRO` step.

## Build & Run
1. **Compile & Link** – submit `jcl/BUILD.jcl`. Adjust library DSNs as needed (`SIGYCOMP`, `SCEERUN`, `SCEELKED`). The load module is written to `&SYSUID..SECTLOAD(SECTDRIV)`.
2. **Allocate & Load KSDS** – submit `jcl/ALLOC_LOAD_KSDS.jcl` after copying `data/sys020.load` to `&SYSUID..SECT.SYS020.LOAD`.
3. **Prepare input** – upload `data/sys010.sample` to `&SYSUID..SECT.SYS010.INPUT` as **FB LRECL 132**.
4. **Run** – submit `jcl/RUN_SECTDRIV.jcl`. Logs appear in `SYS011` SYSOUT.

## Notes
- **Signed integers** in Working-Storage use `SIGN IS LEADING EXTERNAL` per your requirement.
- Source adheres to fixed-format rules: `*` in column 7 for comments, wrapped at column 72 with `-` continuations where needed.
- Section endings: every section uses `EXIT.` as the final statement.

## References
- IBM Enterprise COBOL 6.4 – **Intrinsic Functions** list and details.  
- IBM Docs – **Using intrinsic functions** (overview and usage).  
- IBM Docs – **Continuation lines & fixed format rules**.  
- IBM Docs – **Programming Guide / compile JCL** (IGYCRCTL usage).  
- IBM Docs – **IDCAMS examples** (DEFINE/REPRO for KSDS).

