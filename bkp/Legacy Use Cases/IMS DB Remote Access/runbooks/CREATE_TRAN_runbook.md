
# /CREATE TRAN runbook (ECHO → ECHOPGM)

## Prerequisites
- PSB `ECHOPSB` generated and available (ACBLIB or Catalog).
- Program resource `ECHOPGM` available in IMS (points to your COBOL load).

## Create the transaction (dynamic)
```
/CREATE TRAN NAME(ECHO) SET PGM(ECHOPGM) MSGTYPE(SNGLSEG) RESP(Y) MODE(SNGL)
```

## Verify
```
/DIS TRAN ECHO
/QUERY IMSCON TYPE(PORT) NAME(*)
/QUERY IMSCON TYPE(DATASTORE) NAME(*)
```

## Notes
- Single-segment, response transaction; non-conversational.
- Ensure client sends **LLZZ + TRANCODE(8) + DATA**; COBOL replies with LLZZ + DATA.
