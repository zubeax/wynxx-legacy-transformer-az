# ECB Payments Statistics Demo (COBOL + DB2 on z/OS)

This package provides a working **IBM Enterprise COBOL** example with **embedded SQL for Db2 for z/OS** which ingests transactional payment data, aggregates it according to the core dimensions in the **ECB payments statistics** amending Regulation **ECB/2020/59** (published as **Regulation (EU) 2020/2011**) and emits a compact **XML** file using the COBOL `XML GENERATE` feature.

> ⚠️ **Scope note**: This implementation focuses on the essentials of *Table 4a / Table 9* style aggregates (payment service × initiation channel × SCA) to demonstrate the end‑to‑end flow (input → processing → formatting). National Central Banks publish official **XSDs** and submission guides. For production reporting you must map the output to your NCB’s XML/XSD and validation rules.

## Contents

```
ecb-paystats/
  src/
    PAYSTATS01.cbl          # main driver (CALLs subroutines)
    SUBINP.cbl              # input: reads Db2 (EXEC SQL) and fills in-memory table
    SUBAGG.cbl              # processing: aggregates into service×channel×SCA buckets
    SUBFMT.cbl              # formatting: XML GENERATE to a sequential dataset
    copybooks/
      PAYSTATS-COMMON.cpy   # shared record/table layouts
  db/
    ddl_ecb_paystats_db2.sql         # Db2 DDL (schema + tables)
    sample_load/
      PAYMENT_TRANSACTIONS.csv       # ≥1,200 sample transactions (Q1‑2025)
      LOOKUP_*.csv                   # lookups for code lists (optional)
      load_payment_transactions.jcl  # template for DSNUTILB LOAD from CSV
  jcl/
    create_db_objects.jcl    # run DDL via DSNTEP2/IKJEFT01
    build_compile_link.jcl   # DSNHPC precompile + IGYCRCTL compile + IEWL linkedit + BIND
    run_program.jcl          # run under Db2 attachment; writes XML to dataset
  out/
    (placeholder for PAYSTATS.xml created on z/OS)
```

## Data model (Db2)

- **PAYSTATS.PAYMENT_TRANSACTIONS** — captures the main variables needed to compile ECB payment statistics: `SERVICE_CODE` (credit transfers, direct debits, card, e‑money, cash withdrawals, cheques, money remittances, other), `INIT_CHANNEL` (paper/electronic/remote/mobile/PIS), **SCA** and reason (where not applied), optional `SCHEME_CODE` (e.g. SEPA SCT/SDD, card scheme), card **MCC**, **POS country**, payer/payee residency, amount (EUR). The fields reflect the categories and definitions in ECB/2020/59 (Annex I/II).

- Lookup tables for service, channel, SCA reason, scheme, country, MCC are included for clarity and validation.

## Build & run (z/OS)

1. **Create Db2 objects**
   - Upload `db/ddl_ecb_paystats_db2.sql` to a dataset and run `jcl/create_db_objects.jcl` (paste the SQL into `SYSIN` or point DSNTEP2 to your dataset). Update `&SSID` and library names.

2. **Load sample data**
   - Transfer `db/sample_load/PAYMENT_TRANSACTIONS.csv` to a z/OS sequential dataset in text (ASCII→EBCDIC) with `RECFM=VB,LRECL≥512` (or use z/OS UNIX).
   - Edit `db/sample_load/load_payment_transactions.jcl`: set `&INDSN` to the uploaded dataset; submit to load via `DSNUTILB`.
   - Optional: load the LOOKUP_*.csv into your own reference tables as needed.

3. **Prepare source libraries**
   - Create PDS members for `SRC`, `OBJ`, `DBRM`, `LOAD`, and a copybook PDS (or use PDSE). Place the `.cbl` files into `SRC` members (`PAYSTATS01`, `SUBINP`, `SUBAGG`, `SUBFMT`) and `copybooks/PAYSTATS-COMMON.cpy` into your copybook library referenced by `SYSCPY`.

4. **Build**
   - Submit `jcl/build_compile_link.jcl` after setting `&HLQ`, `&SSID`, and library symbols to your installation values. This JCL **precompiles** the SQL modules (PAYSTATS01, SUBINP), **compiles** all modules, **link‑edits** a single load module `PAYSTATS01` and **BINDs** the Db2 packages and plan.

5. **Run**
   - Submit `jcl/run_program.jcl`. The job allocates `&HLQ..OUT.PAYSTATS.XML` and runs the program under Db2. The output XML contains aggregates for **Q1‑2025** and **country DE** (defaults in the main program’s WS variables). Adjust the reference period and country in `PAYSTATS01.cbl` if needed.

## Program flow & responsibilities

- **PAYSTATS01 (main)**: Sets the reference period/country, `CALL 'SUBINP'`, then `CALL 'SUBAGG'`, then `CALL 'SUBFMT'`. Writes the XML buffer to `OUTFILE`.
- **SUBINP (input)**: Uses a **cursor** to fetch rows from `PAYSTATS.PAYMENT_TRANSACTIONS` filtered by `TRANS_TS BETWEEN :DATE_FROM AND :DATE_TO` and `(PAYER_COUNTRY= :COUNTRY OR PAYEE_COUNTRY= :COUNTRY)`, and fills the in‑memory table (`OCCURS … DEPENDING ON`).
- **SUBAGG (processing)**: Pure COBOL aggregation into distinct **service × initiation channel × SCA** buckets with counters and totals.
- **SUBFMT (formatting)**: Copies aggregates into an XML‑friendly structure and issues `XML GENERATE` with UTF‑8 encoding.

### XML structure (produced by `SUBFMT`)

```xml
<WS-XML-DOC>
  <HEADER>
    <COUNTRY>DE</COUNTRY>
    <REF-YEAR>2025</REF-YEAR>
    <REF-HALF>H1</REF-HALF>
    <REF-QUARTER>Q1</REF-QUARTER>
  </HEADER>
  <AGGREGATES>
    <ITEM>
      <SERVICE>CREDIT_TRANSFER</SERVICE>
      <INITIATION-CHANNEL>ELECTRONIC</INITIATION-CHANNEL>
      <SCA>Y</SCA>
      <TRANSACTION-COUNT>…</TRANSACTION-COUNT>
      <TOTAL-VALUE-EUR>…</TOTAL-VALUE-EUR>
    </ITEM>
    …
  </AGGREGATES>
</WS-XML-DOC>
```

## Notes & assumptions

- **Submission format**: The ECB regulation foresees **quarterly and semi‑annual** reporting with detailed breakdowns (including fraud, POS vs. counterpart geography, and MCC for cards). NCBs provide the **authoritative XSDs** and validation rulebooks. This demo concentrates on a subset sufficient to show how to **map, aggregate and emit XML**; extend the schema and `SUBAGG` as required for your jurisdiction.
- **Fraud data**: A `FRAUD_FLAG`/`FRAUD_ORIGIN` is present in the table to facilitate extension to **Table 5** style outputs; the current XML focuses on non‑fraud aggregates.
- **Card specifics**: `MCC` and `POS_COUNTRY` columns exist to facilitate cross‑border and MCC analyses.

## License

MIT (for this demo code and data). Replace all placeholders before use in production.
