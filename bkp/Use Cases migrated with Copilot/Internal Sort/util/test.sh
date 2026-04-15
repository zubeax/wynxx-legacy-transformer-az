#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
./build.sh
./run.sh sample_input.txt sample_output.txt
echo "--- Sample output ---"
cat sample_output.txt