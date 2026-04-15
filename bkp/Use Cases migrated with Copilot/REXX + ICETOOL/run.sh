#!/usr/bin/env bash
set -euo pipefail

# --- RESET OUTPUT DATASET (JCL IEFBR14 delete equivalent) ---
rm -f out/sorted.txt out/unsorted.txt || true

# --- STEP1: Generate (REXX/IRXJCL equivalent) ---
MIN=${1:-20260101}
MAX=${2:-20261231}
COUNT=${3:-1000}

# compile if needed
if [ ! -f out/PayGen.class ] || [ ! -f out/PaySort.class ]; then
  ./build.sh
fi

echo "Generating ${COUNT} transactions between ${MIN} and ${MAX}..."
java -cp out PayGen \
  --min="${MIN}" --max="${MAX}" --count="${COUNT}" \
  --accounts="data/INACCTS.txt" --purposes="data/INPURP.txt" \
  --out="out/unsorted.txt"

# --- STEP2: Sort (ICETOOL equivalent) ---
echo "Sorting to out/sorted.txt by date (1-8) and creditor account (10-43)..."
java -cp out PaySort --in="out/unsorted.txt" --out="out/sorted.txt"

echo "Done."
