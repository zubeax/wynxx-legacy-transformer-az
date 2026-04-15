
# CICS via CTG – Order Persistence (ECI COMMAREA)

This package replaces the earlier MQ-triggered design with a **CICS Transaction Gateway (CTG)** invocation using the **ECI** Java API. The COBOL program is executed by CTG over **IPIC** to the CICS region; the **payload format** is unchanged.

## Components

- `src/cobol/ORQCTG.cbl` — CICS COBOL program. Input: pipe-delimited payload in **COMMAREA**. Output: 80‑byte confirmation in COMMAREA.
- `src/java/CtgOrderClient.java` — Simple Java client using **ctgclient.jar** (**ECIRequest**) to flow the payload and print the 80‑byte reply.
- `conf/ctg.ini.sample` — Example CTG configuration (IPIC server to CICS + TCP/IP protocol handler).
- `jcl/build_orqctg.jcl` — JCL to precompile, compile, link into CICS, and **BIND** package/plan.

## Payload

**Request** (text):
```
ORDER_ID|CLIENT_ID|YYYY-MM-DD|STATUS|AMOUNT
```
**Reply** (80 chars):
```
CONFIRMED|ORDER_ID|OK[padding spaces]
```

## CICS configuration (CTG over IPIC)

1. **Define PROGRAM and TRANSACTION** for the COBOL:
   ```
   CEDA DEF GROUP(ORQGRP) PROGRAM(ORQCTG) LANGUAGE(COBOL) CONCURRENCY(THREADSAFE)
   CEDA DEF GROUP(ORQGRP) TRANSACTION(ORQ2) PROGRAM(ORQCTG)
   CEDA INSTALL GROUP(ORQGRP)
   ```

2. **Enable IPIC** and create inbound listener (**TCPIPSERVICE**) and **IPCONN**:
   - `TCPIPSERVICE`: set `PROTOCOL(IPIC)`, `HOST(<cics-host-or-ANY>)`, and listening `PORT(50889)`; `TRANSACTION(CISS)` default is fine. citeturn3search100
   - `IPCONN`: set `APPLID` and `NETWORKID` to match CTG’s `APPLID`/`APPLIDQUALIFIER` in `ctg.ini`, associate the `TCPIPSERVICE`, **inbound** receive count, link/user security, and put **INService(YES)**. Example values are shown in IBM’s scenario. citeturn3search101turn3search104
   - Background on IPIC requirements and capabilities (channels/containers, syncpoint, IPv4/IPv6) is in the CICS TS docs. citeturn3search103

3. **Configure CTG** to connect to the CICS IPIC listener:
   - Use the **Configuration Tool** (`ctgcfg`) to create/update `ctg.ini` and define an **IPICSERVER** with your CICS host/port. The sample snippet in `conf/ctg.ini.sample` mirrors IBM’s guidance (HOSTNAME/PORT/SENDSESSIONS). citeturn3search92

## Building and installing the COBOL

1. Tailor and submit `jcl/build_orqctg.jcl` to precompile, compile, link to your CICS LOADLIB, and **BIND** (`ORQCOLL`/`ORQPLAN`). (Standard DSNHPC + IKJEFT01/DSN flow.) citeturn1search13
2. Ensure CICS’s **DB2 attachment** (DB2CONN/DB2ENTRY/DB2TRAN, as per your shop) associates the `ORQPLAN` to the incoming `ORQ2` transaction. citeturn1search2

## Java client (standalone) – compile & run

> You need the **CTG SDK** (for `ctgclient.jar`) in your classpath. IBM provides downloadable SDK/JARs; see the SDK page and Java client samples. citeturn3search98turn3search95

```bash
# Compile (Windows example; adjust paths)
javac -cp "C:\Program Files\IBM\CICS Transaction Gateway\classes\ctgclient.jar" src\java\CtgOrderClient.java

# Run
java -cp ".;C:\Program Files\IBM\CICS Transaction Gateway\classes\ctgclient.jar"   CtgOrderClient <ctgHost> <ctgPort> <serverName> <programName> <payloadFile> [userid] [password]

# Example
java -cp ".;C:\Program Files\IBM\CICS Transaction Gateway\classes\ctgclient.jar"   CtgOrderClient ctg.example.com 2006 CICSA ORQCTG order.txt CTGUSER secret
```
- `ctgHost`/`ctgPort` – the **Gateway daemon** host/port (TCP/IP protocol handler port). `serverName` – the **IPICSERVER** name from `ctg.ini` (e.g., `CICSA`). `programName` – `ORQCTG`.
- The client uses **ECIRequest** and **JavaGateway.flow** to link to the program with a COMMAREA, as described in IBM docs. citeturn3search105turn3search108
- Ensure `ctgclient.jar` (and any needed `ctgsamples.jar`) are in the classpath. citeturn3search95

### Code page
The client uses code page **Cp1047** (EBCDIC). Adjust encoding if your environment differs.

## Test procedure

1. Create `order.txt` with: `O00000000001|C000000001|2025-07-02|NEW|123.45`.
2. Start the **CTG** daemon with your tailored `ctg.ini`. citeturn3search92
3. Ensure CICS **TCPIPSERVICE**/**IPCONN** are **INService** and the program/transaction are installed. citeturn3search100
4. Run the Java client; you should see: `CONFIRMED|O00000000001|OK`.

## Notes & references

- **ECI Java API** (`ECIRequest`, `JavaGateway`), COMMAREA size and null‑stripping, timeouts, LUW control. citeturn3search105turn3search108
- **CTG Java client samples**: classpath requirements (`ctgclient.jar`, `ctgsamples.jar`). citeturn3search95
- **CTG configuration** and **IPICSERVER** mapping to CICS listener host/port. citeturn3search92
- **CICS IPIC** resource definitions (TCPIPSERVICE / IPCONN) and security options; predefined secure IPCONN scenario shows matching values between CTG and CICS. citeturn3search100turn3search101turn3search104

---
Generated 2026-03-20 09:22 UTC.
