# Full COBOL Debug Package with Zowe Pipelines and USS Workflow
Usage instructions...

Here’s a detailed breakdown of the included artifacts:
COBOL Program

helloworld.cbl
A working 80‑char/line batch file copy program using enterprise COBOL conventions.

JCL

compile.jcl — Debug-enabled compile JCL
run.jcl — Execution JCL including SYS010, SYS050, SYSDEBUG allocations

Zowe + Debugging Files

zowe-debug.json — VS Code Zowe Explorer debug configuration
zowe_workflow.sh — Full Zowe CLI pipeline:

Upload COBOL to PDS
Submit compile job
Submit run/debug job



Automation Scripts

submit_compile.sh — Zowe CLI script to submit compile JCL
submit_run.sh — Zowe CLI script for run/debug JCL

Sample Input

sample.txt containing exactly 100 fixed-length 80‑byte records

Documentation

README.md — Overview & step-by-step workflow


What You Can Do With This Bundle
With this package, you can:
1. Upload programs using Zowe CLI
Shellzowe zos-files upload file-to-pds helloworld.cbl USER.COBOL(HELLOWRLD)Show more lines
2. Compile with debug support
Shell./submit_compile.shShow more lines
3. Submit execution JCL to attach the remote debugger
Shell./submit_run.shShow more lines
4. Attach from VS Code Zowe Explorer
Using the included:
zowe-debug.json


🧩 1. Zowe Pipelines Integration
Included file: zowe-pipeline.yaml
This YAML defines a complete CI-style pipeline:

Upload COBOL source → PDS
Submit compile JCL
Submit execution/debug JCL
Designed for future integration with GitHub Actions, Jenkins, Tekton, and Zowe CLI‑based automation


🐧 2. End‑to‑End USS Workflow
Included file: uss_workflow.sh
A USS‑based workflow allowing you to:

Store and manipulate COBOL source in USS
Copy to PDS automatically
Submit compile & run jobs directly from the UNIX shell
This makes automation easier inside USS hosted environments or pipelines


📘 3. Fully Enhanced README.md
Included file: README.md
Now contains:

How to structure your Zowe profile
How to run Zowe CLI workflows step-by-step
How to launch the Zowe Pipeline end-to-end
How to use USS workflow scripts in automated pipelines
How to debug in VS Code via Zowe Explorer
Full comments in each section explaining why and how each step works


🔧 Summary of All Bundle Contents
helloworld.cbl            → COBOL program
compile.jcl               → Debug-enabled compile JCL
run.jcl                   → Execution JCL with SYSDEBUG
sample.txt                → 100 × 80-char input file
zowe-debug.json           → VS Code Zowe Debug config
zowe_workflow.sh          → Zowe CLI upload/compile/run workflow
submit_compile.sh         → Automatic compile JCL submit
submit_run.sh             → Automatic run/debug JCL submit
zowe-pipeline.yaml        → Full CI-style Zowe Pipeline
uss_workflow.sh           → End-to-end USS execution workflow
README.md                 → Fully commented usage guide
