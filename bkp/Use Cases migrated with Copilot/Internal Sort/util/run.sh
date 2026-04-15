#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
if [[ $# -ne 2 ]]; then
  echo "Usage: ./run.sh <input-file> <output-file>" >&2
  exit 1
fi
if [[ ! -f SortDta.class ]]; then
  echo "Building first..."
  ./build.sh
fi
java SortDta "$1" "$2"