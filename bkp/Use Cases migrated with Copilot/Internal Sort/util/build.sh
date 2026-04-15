#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
echo "Compiling SortDta.java..."
javac SortDta.java
echo "Done. Class files generated in $(pwd)"