
# ZFT2PAIN — SEPA pain.001 (ISO 20022) generator on z/OS (COBOL)

This package contains a complete, runnable sample that:

* reads a fixed-block (FB) 80-byte sequential dataset with funds transfer requests,
* builds a `pain.001.001.03` Customer Credit Transfer Initiation XML with `XML GENERATE`, and
* writes the result **UTF-8**-encoded to a **VB** dataset.

> Tested structure-wise against IBM Enterprise COBOL documentation for XML generation and UTF‑8 handling. See references at the bottom.

---

## 1) Files

```
./src/ZFT2PAIN.cbl       # Enterprise COBOL batch program
./jcl/BUILD.jcl           # Compile + link with IGYWCL
./jcl/RUN.jcl             # Allocate VB UTF-8 DS + execute the program
./data/FT_INPUT_80.txt    # Sample input (100 lines, LRECL=80)
```

## 2) Input format (FB 80)

Each line is a single transaction in fixed positions:

| Bytes  | Field               | Type          | Notes                                  |
|--------|---------------------|---------------|----------------------------------------|
| 1–16   | `END-TO-END_ID`     | X(16)         | Free-form ID, echoed as `<EndToEndId>` |
| 17–29  | `AMOUNT_IN_CENTS`   | 9(13)         | Implied 2 decimals (e.g. `000000001234` → `12.34`) |
| 30–51  | `CREDITOR_IBAN`     | X(22)         | **DE** IBAN (22 chars) in sample file  |
| 52–80  | `CREDITOR_NAME`     | X(29)         | ASCII name (umlauts de‑accented)       |

> The sample generator produces German IBANs to fit LRECL=80; adjust the layout if you need longer IBANs (up to 34 chars).

## 3) Output

A `pain.001.001.03` document with default namespace `urn:iso:std:iso:20022:tech:xsd:pain.001.001.03`, including:

* `<GrpHdr>` with `MsgId`, timestamp, `NbOfTxs`, `CtrlSum`, `InitgPty`.
* A single `<PmtInf>` block with debtor, debtor account, debtor agent and SEPA service level.
* One `<CdtTrfTxInf>` per input line: `EndToEndId`, `Amt/<InstdAmt Ccy="EUR">`, creditor name and account, and `<RmtInf>/<Ustrd>`.

The program writes UTF‑8 bytes to a **VB** dataset, chunked into ≤4000‑byte records to avoid LRECL issues. The JCL example allocates `RECFM=VB,LRECL=4096` and **tags** the dataset with CCSID **1208** (UTF‑8).

## 4) Build (compile + link)

1. Create a PDS for sources and a LOAD library (site standard).
2. Upload `src/ZFT2PAIN.cbl` as member `ZFT2PAIN` (EBCDIC text).
3. Edit `jcl/BUILD.jcl` and replace `YOUR.HLQ.*` with your DSNs.
4. Submit `BUILD.jcl` to compile/link via **IGYWCL**.

`IGYWCL` is IBM’s cataloged procedure that runs `IGYCRCTL` (COBOL compiler) followed by the link‑edit step, producing a load module in your LOAD library.  
(IBM docs: **COBOL compile and link procedure with IGYWCL**.)

## 5) Prepare input & run

1. Allocate an FB 80 input dataset (or reuse an existing one).  
   Upload `data/FT_INPUT_80.txt` in **text/ASCII mode** so your FTP/NDM converts to your EBCDIC CCSID.
2. Edit `jcl/RUN.jcl` and set:
   * `YOUR.HLQ.FT.INPUT80` (input)
   * `YOUR.HLQ.SEPA.PAIN001.VB` (output; keep `RECFM=VB,LRECL=4096`)
   * `YOUR.HLQ.LOAD` (loadlib with `ZFT2PAIN`)
3. Submit `RUN.jcl`.

### About UTF‑8 tagging
The sample `RUN.jcl` uses `CCSID=1208` on the DD to tag the newly created VB dataset as UTF‑8. If your shop disallows `CCSID` on DD statements, use an **SMS DATACLAS** that sets CCSID 1208 for the dataset, or tag afterwards per local standards.

## 6) Customization

Open `ZFT2PAIN.cbl` and adjust the defaults in `WS-PARAMS`:

* `WS-INITG-NAME` / `WS-DEBTOR-NAME`
* `WS-DEBTOR-IBAN` / `WS-DEBTOR-BIC`
* `WS-MSG-ID` / `WS-PMTINF-ID` / `WS-REQ-EXEC-DATE`

For larger batches, increase the `XML-OUT` size and `OCCURS` limit. The program already slices XML into multiple VB records (≤4000 bytes each).

## 7) Data model vs XML (how it’s generated)

The program builds a COBOL group that mirrors the `pain.001.001.03` structure and then calls `XML GENERATE` with:

* `WITH ENCODING 1208` → **UTF‑8** bytes in the target buffer.
* `WITH XML-DECLARATION` → emits `<?xml version="1.0" encoding="UTF-8"?>`.
* `NAMESPACE IS 'urn:…pain.001.001.03'` → default namespace on `<Document>`.
* `TYPE OF InstdAmtCcy IS ATTRIBUTE` and `NAME OF … IS "Ccy"` → makes `Ccy` an attribute of `<InstdAmt>`.
* `NAME OF … IS "…"` → enforces ISO 20022 tag casing.

## 8) Sample input generator (what’s in `data/FT_INPUT_80.txt`)

* 100 records (LRECL 80, FB).
* German IBANs with correct checksum.
* Random `EndToEndId` + amounts + names (ASCII only for simplicity).

## 9) References

* **Encoding & XML GENERATE** — `WITH ENCODING codepage` (use `1208` for UTF‑8) and `WITH XML-DECLARATION`; how counts are returned; attributes & namespaces.  
  IBM Enterprise COBOL docs.  
* **Producing XML with XML GENERATE** — overview & examples; link‑edit note.  
  IBM docs.  
* **XML GENERATE — TYPE/ATTRIBUTE and NAME OF** — controlling attribute vs element and exact tag names.  
  IBM docs & community examples.  
* **COBOL compile & link (IGYWCL)** — JCL and procedure structure.  
  IBM docs & tutorials.  
* **pain.001.001.03** — structure, required elements & SEPA service level.  
  Bank/industry guides and sample files.

## 10) Troubleshooting

* **UTF‑8 tagging**: If your JCL rejects `CCSID=1208` on DD, allocate via SMS **DATACLAS** with CCSID 1208, or write to a z/OS UNIX file tagged 1208 and then copy to MVS.
* **Record too long**: Increase output `LRECL` (e.g., 8192) or the chunk size in `WRITE-CHUNKS`.
* **Schema validation**: Banks sometimes require bank‑specific constraints. Validate the result against your bank’s `pain.001` XSD/IG.

---

© 2026-03-12 — sample for educational/testing purposes.
