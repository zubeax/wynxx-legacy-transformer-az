Both datasets are read via DD names `INACCTS` and `INPURP` in the IRXJCL step.

## Output datasets

- **Unsorted**: `YOUR.HLQ.PAYGEN.UNSORTED` – created by the REXX step, FB LRECL=200.
- **Sorted**:   `YOUR.HLQ.PAYGEN.SORTED`   – created by the ICETOOL step, FB LRECL=200.

## Record layout (LRECL = 200, RECFM = FB)

| Pos | Len | Description                    | Format           |
|----:|----:|--------------------------------|------------------|
| 1   | 8   | Execution date                 | `YYYYMMDD`       |
| 9   | 1   | Blank separator                | space            |
| 10  | 34  | Creditor account               | left-padded/trunc|
| 44  | 30  | Creditor name                  | left-padded/trunc|
| 74  | 34  | Debtor account                 | left-padded/trunc|
| 108 | 30  | Debtor name                    | left-padded/trunc|
| 138 | 10  | Amount in **cents**            | zero-padded      |
| 148 | 3   | Currency                       | `EUR`            |
| 151 | 50  | Payment purpose                | left-padded/trunc|

Sorting keys used by ICETOOL: `(1,8,CH,A)` then `(10,34,CH,A)`.

## How it works

1. **IRXJCL** runs the REXX script from an **instream DD**.
2. The script reads `INACCTS` and `INPURP` into stem variables.
3. It generates `COUNT` transactions by randomly selecting a **creditor** and a **debtor** (ensuring they differ),
 a random **purpose**, a random **date** between `MIN` and `MAX`, and a random **amount** between 1.00 and 1000.99 EUR.
4. The script writes fixed-length records (FB, LRECL=200) to `OUTTRN`.
5. **ICETOOL** sorts the unsorted output by date and creditor account and writes to a persistent FB dataset.

## Usage

1. Edit `jcl/REXX_PAYGEN.jcl` and **customize dataset names**:
 - Replace `YOUR.HLQ.PAYGEN.ACCOUNTS` with your accounts dataset (CSV `acct_no,name`).
 - Replace `YOUR.HLQ.PAYGEN.PURPOSES` with your purposes dataset (one per line).
 - Replace `YOUR.HLQ.PAYGEN.UNSORTED` and `YOUR.HLQ.PAYGEN.SORTED` to meet site standards.
2. Optionally change `PARM` values for date range and count, e.g.:

PARM='DD:REXXIN MIN=20260101 MAX=20261231 COUNT=5000'
3. Submit the job:

SUBMIT 'your.hlq.jcl(REXX_PAYGEN)'
4. Check `SYSTSPRT` for the message `Generated n transactions.`
5. The sorted output will be in `YOUR.HLQ.PAYGEN.SORTED`.

## Notes & Troubleshooting

- The script expects TSO/E REXX `DATE` function with conversion forms (e.g. `DATE('B',date,'S')`).
- If `INACCTS` has fewer than two records, the job ends with RC=12.
- If you prefer **integer euros** only (no cents), replace the `cents` calculation with `0` in the REXX.
- All output fields are truncated to their maximum length; shorter fields are space-padded.
- ICETOOL control uses `OPTION EQUALS` to maintain relative order among equal keys.

## Security & Data Handling

- This generator is **synthetic**; do not use real customer data in non-production environments.
- Ensure proper dataset protections (RACF/ACF2/TopSecret) for any data you create.

## Example (small)

Given:

INACCTS:
DE44500105175407324931,Contoso GmbH
DE15500800000001523123,Northwind AG
INPURP:
Invoice 4711 April
With `COUNT=3`, you might see records like (spaces omitted for brevity):

20260615 DE44500105175407324931 Contoso GmbH      DE15500800000001523123 Northwind AG       00005789 EUR Invoice 4711 April
20260201 DE15500800000001523123 Northwind AG      DE44500105175407324931 Contoso GmbH       00032045 EUR Invoice 4711 April
