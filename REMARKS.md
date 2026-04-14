# Manual changes to the generated code.

The original SELECT clause for the VSAM file SYS020 in the FILE-CONTROL section
contained a 'RECORDING MODE' phrase. This had to be removed. 

```
     SELECT SYS020 ASSIGN TO DD-SYS020
     ORGANIZATION IS INDEXED ACCESS MODE IS DYNAMIC
     RECORD KEY IS KS-KEY FILE STATUS IS FS-SYS020     
     RECORDING MODE IS F RECORD CONTAINS 132 CHARACTERS.
```