        #!/usr/bin/env bash
        # Verify that all lines are exactly 80 characters
        set -euo pipefail
        if [[ $# -ne 1 ]]; then
          echo "Usage: ./verify_fixed_length.sh <file>" >&2
          exit 1
        fi
        awk '{ if (length($0)!=80) { printf("Line %d has length %d
", NR, length($0)); err=1 } } END { exit err }' "$1"
        echo "All lines are 80 characters."