//CLMAPS  JOB (ACCT),'ASM BMS',CLASS=A,MSGCLASS=A,NOTIFY=&SYSUID
//* Assemble and link the CLMAPS BMS mapset (physical + symbolic)
//ASM      EXEC PROC=DFHMAPS,REGION=0M,
//             DSCTLIB=your.cobol.copylib,
//             SYSPARM='MAP',
//             PARM.LKED='LIST,LET,XREF,RENT,RMODE=ANY'
//SYSIN    DD  DISP=SHR,DSN=your.source.bms(CLMAPS)   
//* NOTE: DSCTLIB will receive the generated COBOL copybook
//*       for symbolic map
//*       The link-edited mapset will be written
//*       to your CICS LOADLIB from DFHMAPS
