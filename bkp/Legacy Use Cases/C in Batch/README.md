# MQXCOPY (z/OS C) – Record copy from SYS010 → SYS050

**MQXCOPY** is a small z/OS C program that reads a 133-byte record dataset (typically `RECFM=FBA,LRECL=133`) from `//SYS010` and writes it to `//SYS050`, preserving record boundaries and bytes (including ASA carriage control in column 1).

It is delivered with:

- **C source** (`src/mqxcopy.c`)
- **Compile/Link JCL** (`jcl/compile_dbg.jcl`)
- **Run JCL for two debug flows**
  - `jcl/run_dbg_tcpip.jcl` – direct remote debug via `TEST(…TCPIP&)`
  - `jcl/run_dbg_park.jcl` – **parked** session using IBM z/OS Debugger RDS (recommended for VS Code Z Open Debug)

> Prereqs: IBM XL C or Open XL C compiler; IBM z/OS Debugger (Remote Debug Service & Debug Profile Service on host); LE (`CEE.*`) libraries; and the VS Code **IBM Z Open Debug** extension.

---

## 1) Program behavior

- Opens input and output by **DD name** in **record mode**: `fopen("dd:SYS010","rb,type=record,noseek")` and `fopen("dd:SYS050","wb,type=record,noseek")`.
- Reads one record at a time (LRECL=133). If a short record is ever encountered, it is right-padded with blanks to 133 and written out (defensive). No codepage translation is performed because files are opened in binary mode. Record mode preserves QSAM record boundaries.  
  *Rationale*: Using `type=record` and, optionally, `noseek` is the documented way to do OS file I/O against MVS datasets from C on z/OS. Existing **DCB** attributes (RECFM/LRECL/BLKSIZE) from the DDs are honored unless you override them in `fopen()`; DD attributes take precedence when opening by ddname. citeturn1search37turn1search41turn1search23
- Designed for classic report files with **`RECFM=FBA,LRECL=133`** (132 printable columns + ASA carriage control in col 1). Keep the output as `FBA,133` if you plan to view/print it like a report. citeturn1search19turn1search20

---

## 2) Build (compile & link) with DEBUG info

Use `jcl/compile_dbg.jcl`. It:

- Allocates PDS libraries for **source**, **object**, and **load** modules.
- Compiles with **no optimization and debug info** (`OPT(0) DEBUG SOURCE`) using the XL C driver `CCNDRVR` (or `CNWCLANG` if your site uses Open XL C/C++), referencing the LE headers via `/SEARCH('CEE.SCEEH.+')`. citeturn1search25turn1search30
- Link-edits to `&HLQ..C.LOAD(MQXCOPY)` as **RENT**. Optionally adds your IBM z/OS Debugger library (`…SEQAMOD`) to **STEPLIB** at runtime. Including the optional **EQADBCXT**/LE exit at link-edit time is a common approach when you want the debugger to auto-intercept via profiles. citeturn1search31

### What to change before submit

Edit the symbols at the top of the JCL:

- `HLQ` – your high-level qualifier
- `LEPRFX` – usually `CEE`
- `CPRFX` – usually `CBC` (XL C)
- `DBGPRFX` / `DBGSEQ` – your site’s z/OS Debugger loadlib (for example, `EQAZ.SEQAMOD`). Your sysprog can confirm the exact dataset name.

> **Note (Open XL C/C++):** If your site uses the newer Clang-based compiler, switch the step comment in the JCL to **CNWCLANG** as shown inline. IBM’s sample JCL shows how to drive Open XL C from batch. citeturn1search30

---

## 3) Run + debug from VS Code (Z Open Debug)

You have two options. In both cases, allocate your 133-byte datasets to DD names `SYS010` and `SYS050` in the JCL.

### A. Direct remote debug (TCP/IP)

Use `jcl/run_dbg_tcpip.jcl`. Replace `{HOST}` and `{PORT}` with the host/port pair where your **remote debugger** waits (VS Code Z Open Debug starts the listener for you). The step passes LE runtime option **`TEST(,*,PROMPT,TCPIP&<host>%<port>:*)`** via **`PARM='/…'`**, which triggers the z/OS Debugger and connects out to your workstation. citeturn1search47turn1search48turn1search56

**Steps:**
1. In VS Code, install **IBM Z Open Debug** and **Zowe Explorer**. Configure the Z Open Debug connection (DPS/RDS) as needed. citeturn1search10turn1search8
2. Start **“Launch and debug a z/OS application”** or open a TCPIP listener from the extension, then submit `run_dbg_tcpip.jcl`. The job will connect back and the session starts. citeturn1search65

### B. **Parked** debug session (RDS + debug profile) – recommended

Use `jcl/run_dbg_park.jcl`. This supplies **`CEEOPTS`** with `TEST(,*,PROMPT,RDS)`. With IBM z/OS Debugger **Remote Debug Service (RDS)** and a **Debug Profile** active for your job (you set this up in the Z Open Debug extension), the job starts, **parks** in the debugger, and you **attach** from VS Code via *Connect to parked IBM Z Open Debug session*. citeturn1search47turn1search12turn1search65

> You can manage debug profiles and connect to parked sessions directly from the Z Open Debug UI. Ensure your site’s **Debug Profile Service** (DPS) and **Remote Debug Service** (RDS) are up, and that your Zowe profiles in VS Code are configured. citeturn1search7turn1search12

**CEEOPTS format & rules:** The CEEOPTS DD merges LE runtime options at enclave init; it must be a fixed or fixed-block dataset (not VB). Our samples also turn on `RPTOPTS/RPTSTG` so you can confirm the options in the LE report. citeturn1search13

---

## 4) Sample allocations for 133-byte report files

If you need to create test datasets:

```jcl
//IN133   DD  DSN=&HLQ..DATA.IN133,DISP=(NEW,CATLG),UNIT=SYSDA,
//            SPACE=(TRK,(5,5),RLSE),
//            DCB=(RECFM=FBA,LRECL=133,BLKSIZE=0)
//OUT133  DD  DSN=&HLQ..DATA.OUT133,DISP=(NEW,CATLG),UNIT=SYSDA,
//            SPACE=(TRK,(5,5),RLSE),
//            DCB=(RECFM=FBA,LRECL=133,BLKSIZE=0)
```

`FBA,133` means 132 printable columns plus one **ASA carriage control** byte. Keep `133` in mind when viewing/printing; SORT/ICETOOL also treat report outputs this way and will add a carriage control column when using reporting features. citeturn1search20turn1search24

---

## 5) How the code opens MVS datasets by DD name

- Use `fopen("dd:DDNAME", "rb,type=record,noseek")` to read records without translation and with record integrity, and similarly `wb,type=record` for writing. You can optionally set `recfm=`/`lrecl=` in the mode string, but when opening by ddname, the DD/DCB attributes are used unless you override them. citeturn1search37turn1search41turn1search23
- When writing to spool printer files, LE defaults to variable/print formats if you do not specify `recfm=`; so for report datasets use DD DCB attributes (e.g., `RECFM=FBA,LRECL=133`). citeturn1search23

---

## 6) VS Code quick start

1. Install **Zowe Explorer** and **IBM Z Open Debug** in VS Code. Configure Zowe team configuration and a **zOpenDebug** connection profile (host, DPS port, RDS port, TLS, credentials). citeturn1search8turn1search12
2. Create a **debug profile** for your job (e.g., jobname and program `MQXCOPY`). Activate the profile to produce a **parked session** when the job runs. Then submit `jcl/run_dbg_park.jcl` and choose **Connect to parked IBM Z Open Debug session** in the Run/Debug menu. citeturn1search65

---

## 7) Troubleshooting

- **Open fails on DD**: Ensure DD names exist in JCL and point to QSAM datasets with appropriate **DCB**. In-stream datasets (DD * / DD DATA) are FB-80 by default; do not use those for the 133-byte input. citeturn1search5turn1search1
- **Output looks garbled**: If the dataset is accidentally `FBM` (machine carriage control) instead of `FBA`, printers may show strange characters in col 1. Allocate `RECFM=FBA,LRECL=133`. citeturn1search21turn1search22
- **Cannot connect debugger**: Verify RDS/DPS are up and your Zowe profile is valid. The extension docs list prerequisites and connection setup. citeturn1search10

---

## File map

```
src/mqxcopy.c              # C source
jcl/compile_dbg.jcl        # Allocate, compile (DEBUG), and link-edit
jcl/run_dbg_tcpip.jcl      # Run with TEST(…TCPIP&) – connect from VS Code
jcl/run_dbg_park.jcl       # Run with TEST(…RDS) – parked debug session
```

— Happy debugging!
