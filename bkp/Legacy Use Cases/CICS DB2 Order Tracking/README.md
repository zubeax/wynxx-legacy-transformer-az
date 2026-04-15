
# CICS/DB2 Sample – Client/Order Management

This package contains a minimal, end-to-end example of an **IBM Enterprise COBOL** program for **CICS TS 6.1** that accesses **Db2 13 for z/OS** to maintain client static data and track orders. It includes:

- COBOL CICS/Db2 program (`CLORDCIC`) with pseudo-conversational flow
- BMS mapset (`CLMAPS`) with three maps (menu, client CRUD, order CRUD)
- Db2 DDL (database, tablespaces, tables, indexes, grants)
- JCL to assemble BMS, precompile/compile/link/bind COBOL, create the database, and prime it with data using the Db2 **LOAD** utility
- Plausible random load data files (`clients.dat`, `orders.dat`)

> **Note**: Adjust HLQs, libraries, subsystem ids, and procedures to your site standards before submitting jobs.

## Contents

```
cics_db2_sample/
  src/cobol/CLORDCIC.cbl
  src/bms/CLMAPS.bms
  db/ddl/db2_ddl.sql
  jcl/assemble_bms.jcl
  jcl/compile_bind.jcl
  jcl/create_db.jcl
  jcl/bind_plan_package.jcl (included inside compile_bind)
  jcl/allocate_load_datasets.jcl
  jcl/load_clients.jcl
  jcl/load_orders.jcl
  data/clients.dat
  data/orders.dat
  README.md
```

## Build order

1. **Assemble BMS maps** (`jcl/assemble_bms.jcl`) using the supplied `DFHMAPS` proc to produce the **physical mapset** in your CICS LOADLIB and the **symbolic copybook** into `your.cobol.copylib`.
   - Put the physical mapset load module in a library available to the region through **DFHRPL** or a dynamic LIBRARY resource. citeturn1search45turn1search40turn1search44
2. **Create Db2 database & objects** using `jcl/create_db.jcl`, which runs the SQL in `db/ddl/db2_ddl.sql` via **DSNTEP2** dynamic SQL program. Ensure `SYSPRINT` LRECL conforms (133 by default). citeturn1search34
3. **Precompile/Compile/Link** the COBOL program with `jcl/compile_bind.jcl` (uses **DSNHPC** precompiler, COBOL compiler, and IEWL link-edit), then **BIND** a package and a plan with **IKJEFT01/DSN** commands. citeturn1search28turn1search31turn1search13
4. **Allocate and upload load data** (`jcl/allocate_load_datasets.jcl`), then **LOAD** the two tables with `jcl/load_clients.jcl` and `jcl/load_orders.jcl` using the Db2 **LOAD** utility with delimited input (`TERMINATED BY '|'`). citeturn1search11turn1search12

## Installing in a CICS TS 6.1 region (CICSTS61)

1. Ensure the **mapset** and **program** load modules are available to the region (DFHRPL concatenation or dynamic LIBRARY). Then define the resources with RDO/`CEDA`:

```
CEDA DEF GROUP(CLAPPGRP) MAPSET(CLMAPS)  DESCRIPTION('Client/Order maps')
CEDA DEF GROUP(CLAPPGRP) PROGRAM(CLORDCIC) LANGUAGE(COBOL) DATALOCATION(ANY) CONCURRENCY(THREADSAFE)
CEDA DEF GROUP(CLAPPGRP) TRANSACTION(CLOR) PROGRAM(CLORDCIC) PROFILE()
CEDA DEF GROUP(CLAPPGRP) DB2CONN(DB2A) DB2ID(DB2A)
CEDA DEF GROUP(CLAPPGRP) DB2ENTRY(CLE1) THREADLIMIT(10) PLAN(CLORDPLN)
CEDA DEF GROUP(CLAPPGRP) DB2TRAN(CLD1) ENTRY(CLE1) TRANSID(CLOR)
CEDA INSTALL GROUP(CLAPPGRP)
```

   - **DB2CONN/DB2ENTRY/DB2TRAN** are the standard CICS–Db2 attachment resources used to connect the region to Db2 and route transactions to threads/plans. Bind your package(s) to a plan and associate it via DB2ENTRY/DB2TRAN. citeturn1search2turn1search1
   - After deploying code, use `CEMT SET PROGRAM(CLORDCIC) NEWCOPY` and (if needed) `CEMT SET MAPSET(CLMAPS) NEWCOPY`. citeturn1search40

2. Start the Db2 connection: `CEMT SET DB2CONN START` and verify with `CEMT INQUIRE DB2CONN`. citeturn1search2

3. From a 3270 terminal, run transaction **CLOR** to reach the menu. Use PF3 or clear to exit.

## Program notes

- The COBOL program uses `EXEC CICS SEND/RECEIVE MAP` against the **CLMAPS** BMS mapset. Update flow is pseudo‑conversational via `RETURN TRANSID('CLOR') COMMAREA(...)`. The symbolic map copybook is produced by assembling the BMS with `LANG=COBOL`. citeturn1search46turn1search49
- SQL is embedded with host variables and precompiled by **DSNHPC**. BIND creates a **package** (collection `CLAPPKG`) and a **plan** (`CLORDPLN`). The job stream uses **IKJEFT01** to issue BIND commands. citeturn1search28turn1search13
- The DDL uses the default stogroup `SYSDEFLT` and two simple tablespaces. Adjust bufferpools and stogroup to your shop standards.
- The LOAD control statements use delimited input (`|`) and external decimal for amounts; they follow Db2 LOAD utility conventions for data sets and required DD names. citeturn1search11turn1search12

## Troubleshooting quick tips

- **DSNTEP2** abend U4038 reason 1 usually means `SYSPRINT` LRECL doesn't match the program's page width (133). Allocate `SYSPRINT` with `RECFM=FBA,LRECL=133`, or omit DCB so defaults apply. citeturn1search34
- If BIND fails with `SQLERROR (NOPACKAGE)` or similar, confirm DBRM was created in the precompile step and that collection/qualifier match your BIND JCL. citeturn1search16
- If the CICS program gets `SQLCODE -923` or `-805`, check that CICS is connected to Db2 and that your package exists and is in a plan referenced by DB2ENTRY/DB2TRAN. citeturn1search2

## Security and environments

- Replace public GRANTs with least-privilege GRANTs appropriate to your runtime authid.
- All dataset and library names in JCL are examples; coordinate with your system programmer for correct procs, libraries, and APF/LPA considerations in CTS 6.1+. citeturn1search42

## Credits & References

- IBM Docs: **DSNTEP2** sample dynamic SQL program and LRECL requirements. citeturn1search34
- IBM Docs: **Db2 LOAD** utility – required DD names and sample control statements. citeturn1search11turn1search12
- IBM Docs / Community samples: **Db2 precompiler & COBOL compile JCL**, **BIND** samples. citeturn1search28turn1search31turn1search13
- IBM Docs: **CICS BMS** map creation and installation; DFHRPL/dynamic LIBRARY usage. citeturn1search45turn1search43turn1search44
- IBM Docs: **CICS–Db2 connection** resources (**DB2CONN/DB2ENTRY/DB2TRAN**). citeturn1search2turn1search1

---
Generated on 2026-03-20 08:35 UTC.
