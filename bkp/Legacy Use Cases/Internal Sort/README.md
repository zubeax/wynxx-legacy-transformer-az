# COBOL DTA Sort Batch Program

This project contains a COBOL batch program that performs an **internal sort** on German **DTA Record Type C** files.

## Sorting Criteria
- Creditor **Bankleitzahl (BLZ)** — 8 characters
- **Account Number (KTO)** — 10 characters

The program reads an input file, sorts the records internally using COBOL's `SORT` verb, and writes the sorted records to an output file.

## Files Included
- `sortdta.cbl` – COBOL source code
- `input.dat` – placeholder input file
- `sorted_output.dat` – output file (generated when program runs)
- `sort.tmp` – temporary internal sort file

## How to Compile
```
cobc -x sortdta.cbl -o sortdta
```

## How to Run
```
./sortdta
```

Ensure `input.dat` exists in the working directory.
