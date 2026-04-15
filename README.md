
# CICS/MQ Triggered Order Persistence – Package

This package contains a CICS COBOL program (`ORQTMQ`) that is started by the IBM MQ **CKTI** trigger monitor when a message arrives on a triggered local queue. The program reads an **order request** message from the application queue, persists it to Db2, and then sends a **confirmation** to the `ReplyToQ` specified in the original request.

## Contents

- `src/cobol/ORQTMQ.cbl` – CICS + MQ + Db2 COBOL program
- `mqsc/mq_objects.mqsc` – MQSC commands to define INITQ, request queue, process, and a sample reply queue
- `jcl/define_mq_objects.jcl` – JCL wrapper using **CSQUTIL COMMAND** to apply the MQSC
- `jcl/build_orqtmq.jcl` – JCL to precompile/compile/link/bind the program
- `docs/` – (this README)

## Message format

Request body: `ORDER_ID|CLIENT_ID|YYYY-MM-DD|STATUS|AMOUNT` (pipe-delimited)

Response body: `CONFIRMED|ORDER_ID|OK` (put to `MQMD.ReplyToQ` with `CorrelId = request.MsgId`)

## Build / Deploy

1. **Create MQ objects**
   - Copy `mqsc/mq_objects.mqsc` to a PDS member, e.g., `your.hlq.mqsc(MQOBJS)`.
   - Tailor queue names if needed and submit `jcl/define_mq_objects.jcl` with `PARM='YOURQM'` and libraries for `SCSQAUTH`.
2. **Build program**
   - Tailor datasets and submit `jcl/build_orqtmq.jcl` to precompile, compile, link to your CICS loadlib, and bind (`ORQCOLL/ORQPLAN`).
3. **CICS definitions** (example `CEDA` commands):
   ```
   CEDA DEF GROUP(ORQGRP) PROGRAM(ORQTMQ) LANGUAGE(COBOL) CONCURRENCY(THREADSAFE)
   CEDA DEF GROUP(ORQGRP) TRANSACTION(ORQ1) PROGRAM(ORQTMQ)
   * MQ connection to queue manager and default initiation queue
   CEDA DEF GROUP(ORQGRP) MQCONN(ORQCONN) MQNAME(YOURQM) INITQNAME(CICS.APP.INITQ)
   CEDA INSTALL GROUP(ORQGRP)
   * Start the connection and CKTI via MQMONITOR DFHMQINI
   CEMT SET MQCONN START
   ```
   Notes:
   - `MQCONN` defines the CICS–MQ connection and default initiation queue. Installing it automatically creates **MQMONITOR DFHMQINI** to run **CKTI** for that INITQ. You can also define additional **MQMONITOR** resources for more initiation queues if required.
   - Ensure the IBM MQ libraries are in your DFHRPL/STEPLIB as per site standards.

4. **Db2**
   - Bind outputs use `ORQCOLL` (collection) and `ORQPLAN`. Ensure CICS DB2 attachment (DB2CONN/DB2ENTRY/DB2TRAN) is set up to use the plan.

5. **Test**
   - Put a request message to `ORQ.REQUEST.Q` with `MQMD.ReplyToQ` set to a queue you read (e.g., `ORQ.REPLY.Q`). The message arrival will trigger **ORQ1**, which will process and reply.

## Operational considerations

- The triggered program uses `MQGMO-WAIT` with 5‑second timeout and reads until `MQRC_NO_MSG_AVAILABLE`, then ends. Use `TRIGTYPE(FIRST)` on the application queue.
- The program calls `EXEC CICS SYNCPOINT` after each successful insert and reply, providing unit-of-work integrity across MQ and Db2.
- Security: Grant your CICS region ID access to the MQ queues and PROCESS object, and to Db2 plan/package as per site RACF/DB2 security rules.

---
Generated 2026-03-20 09:14 UTC.
