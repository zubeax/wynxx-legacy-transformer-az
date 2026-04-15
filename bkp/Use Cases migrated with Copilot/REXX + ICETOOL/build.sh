#!/usr/bin/env bash
set -euo pipefail

mkdir -p out
javac -d out src/PayGen.java src/PaySort.java

echo "Build complete."
