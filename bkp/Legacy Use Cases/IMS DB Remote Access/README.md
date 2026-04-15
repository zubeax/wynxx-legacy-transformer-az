
# IMS Connect Starter Kit

This bundle contains:

- `config/HWSCFG00_ATTLS.txt` — IMS Connect configuration with RACF enabled and AT‑TLS (recommended).
- `config/HWSCFG00_InternalSSL.txt` — IMS Connect configuration with RACF and internal SSL (single SSL port).
- `jcl/PSBGEN_ECHOPSB.jcl` — PSBGEN JCL to create PSB `ECHOPSB` (I/O PCB only).
- `jcl/ACBGEN_ACBLIB.jcl` — ACB maintenance JCL to build ACBs into ACBLIB (classic model).
- `jcl/CATALOG_UPDATE.jcl` — Example catalog-based ACB import/activate wrapper (modern model).
- `runbooks/CREATE_TRAN_runbook.md` — Console commands to create transaction `ECHO` and verification steps.
- `sources/COBOL/ECHOPGM.cbl` — Minimal IMS DC COBOL (MPP) echo program (LLZZ protocol).
- `sources/Java/SimpleIMSClient.java` — Minimal Java client using IMS Connect API to send LLZZ/receive reply.

## Quick Start (high level)

1. Submit `jcl/PSBGEN_ECHOPSB.jcl` and confirm RC=0; PSB appears in PSBLIB.
2. Generate runtime ACBs:
   - Classic: submit `jcl/ACBGEN_ACBLIB.jcl` (targets ACBLIB), or
   - Catalog: submit `jcl/CATALOG_UPDATE.jcl` to import/activate PSB in the IMS catalog.
3. Ensure IMS Connect is started with either `config/HWSCFG00_ATTLS.txt` (plus AT‑TLS policy) or `config/HWSCFG00_InternalSSL.txt`.
4. Create transaction dynamically: see `runbooks/CREATE_TRAN_runbook.md`.
5. Run the Java client and check that the COBOL program echoes the text.

## Notes & References

- **AT‑TLS preferred method for TLS on IMS Connect**; internal SSL supports only one SSL port. See IBM docs on AT‑TLS setup and IMS Connect SSL constraints. 
- **HWSCFG TCPIP statement** supports `SSLPORT`/`SSLENVAR` (internal SSL) and security-related parameters (`RACFID`, `EXIT`, `MAXSOC`, `WARNSOC`, etc.).
- **Query IMS Connect**: `QUERY IMSCON TYPE(PORT)` and `TYPE(DATASTORE)` show current port and OTMA status.
- **TRANSACT dynamic definition**: `/CREATE TRAN NAME(ECHO) SET PGM(ECHOPGM) MSGTYPE(SNGLSEG) RESP(Y) MODE(SNGL)`.
- **LLZZ segment format** for IMS TM messages (first two bytes LL, next two bytes ZZ). COBOL MPP uses GU/ISRT on I/O PCB.

For authoritative details, consult IBM documentation:
- AT‑TLS for IMS Connect & recommendation to use it over internal SSL; SSL port limitation: IBM IMS docs.
- TCPIP statement, SSL parameters, port query command: IBM IMS docs.
- PSBGEN/ACB relationship and catalog-based ACB management: IBM IMS docs.
- TRANSACT macro and `/CREATE TRAN` command: IBM IMS docs.