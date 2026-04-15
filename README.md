# IMS TM COBOL Sample: Customer & Order Entry (EXEC DLI)

This sample shows a small IMS™ Transaction Manager (TM) message processing program (MPP) written in COBOL using **EXEC DLI** to read a customer master database and create a new order. It includes:

- COBOL program `ORDENTR` (MPP)
- IMS MFS source for a simple 3270 order-entry screen
- DBDGEN for two simple HISAM databases (`CUSTDB`, `ORDRDB`)
- PSBGEN for the application (`ORDENTR`)
- JCL to build DBDs/PSB, translate MFS, ACBGEN, compile & link the program, and define a transaction

> **Note**: Dataset names, library qualifiers, IMS version assets, and utilities may differ at your site. Treat these artifacts as starting templates.

## Directory Layout

```
ims_order_sample/
  cobol/       ORDENTR.cbl
  copybooks/   ORDENTRMI.cpy, ORDENTRMO.cpy
  mfs/         ORDENTR.mfs
  dbd/         CUSTDB.dbd, ORDRDB.dbd
  psb/         ORDENTR.psb
  jcl/         DBDGEN.jcl, PSBGEN.jcl, MFSGEN.jcl, ACBGEN.jcl, COBCLG.jcl, DEFINE_TRAN.jcl
```

## Build & Installation Steps

1. **Tailor dataset names**
   - Update JCL members to match your libraries: `IMS.SDFSRESL`, `IMS.SDFSMAC`, `IMS.DBDLIB`, `IMS.PSBLIB`, `IMS.ACBLIB`, COBOL compiler `IGY.*`, user `SRCLIB`, `COPYLIB`, `LOADLIB`, etc.

2. **DBDGEN**
   - Submit `jcl/DBDGEN.jcl` to assemble and link `CUSTDB` and `ORDRDB` into `IMS.DBDLIB`.

3. **PSBGEN**
   - Submit `jcl/PSBGEN.jcl` to assemble and link PSB `ORDENTR` into `IMS.PSBLIB`.

4. **MFS Translation**
   - Submit `jcl/MFSGEN.jcl` to translate `mfs/ORDENTR.mfs` with `DFSUPAA0`, producing format sets in `IMS.MFSLIB` and COBOL copy members in `IMS.COPYLIB`.
   - This sample already provides draft copybooks in `copybooks/` for local testing; prefer the translator-generated copies for exact field layouts.

5. **ACBGEN**
   - Submit `jcl/ACBGEN.jcl` to build ACBs into `IMS.ACBLIB`.

6. **Compile & Link**
   - Place `cobol/ORDENTR.cbl` in your `USER.SRCLIB(ORDENTR)` and the copybooks in your `COPYLIB`.
   - Submit `jcl/COBCLG.jcl` to compile and link. Ensure the IMS stub `DFSLI000` is included at link time.

7. **Define Transaction**
   - Define a transaction code (e.g., `ORD1`) that schedules program `ORDENTR`. You can use IMS Type-2 commands or include it in Stage-1 definitions.
   - The provided `jcl/DEFINE_TRAN.jcl` shows an example using `DFSCP001` to run a Type-2 UPDATE. Adjust parameters (IMSID, priority, mode, etc.).

8. **Run-time**
   - Start/refresh IMS control and message regions as per your site standards.
   - From a 3270 terminal, send transaction `ORD1` to bring up the order-entry screen. Enter:
     - Customer ID (must exist in `CUSTDB`)
     - Item ID
     - Quantity
     - Order Date (YYYYMMDD)
   - Program validates customer, inserts an order into `ORDRDB`, and returns confirmation with a generated Order ID.

## Databases

- **CUSTDB** (HISAM): `CUSTROOT` root segment keyed by `CUST-ID`.
- **ORDRDB** (HISAM): `ORDERSEG` root segment keyed by `ORDER-ID`; contains `ORD-CUST-ID`, `ORD-ITEM-ID`, `ORD-QUANTITY`, `ORD-ORDER-DATE`.

> Consider migrating to HDAM/HIDAM or HALDB for production-grade workloads, adding secondary indexes as needed.

## Notes & Caveats

- PCB masks in the COBOL program are modeled as opaque 256-byte areas; adapt them to your site's PCB layout if you need to inspect status codes directly.
- The EXEC DLI calls in this sample demonstrate the flow but may require tailoring for your compiler/options (e.g., `CBLTDLI` vs `DFSLI000` linkage, `USING`/`FROM`/`INTO` syntax variants).
- MFS syntax here is minimal and for demonstration; use your standard MFS conventions for attributes, modnames, and copybook generation.
- Order ID generation is simplistic. Replace with a proper key-generation strategy consistent with your database design.

## Cleaning Up

- To remove generated artifacts from your IMS libraries (ACBs, DBDs, PSBs, MFSLIB members), follow your site's change and cleanup procedures.

---

© Sample for educational/testing purposes.
