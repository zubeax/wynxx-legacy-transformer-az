//{HLQ}MQXC CLCBG JOB (ACCT),'C COMPILE/LINK',CLASS=A,MSGCLASS=X,MSGLEVEL=(1,1)
//*
//* Change HLQ and library names to match your site
//* This JCL compiles (with DEBUG info), link-edits, and creates a load module
//* compatible with IBM z/OS Debugger.
//*
// SET HLQ={HLQ}
// SET SRCLIB=&HLQ..C.SOURCE
// SET OBJLIB=&HLQ..C.OBJ
// SET LOADLIB=&HLQ..C.LOAD
// SET LEPRFX=CEE
// SET CPRFX=CBC
// SET DBGPRFX=EQAZ   
//* DBGPRFX may vary: EQAZ/EQAD depending on product level. Ask your sysprog.
//*
//ALLOC    EXEC PGM=IEFBR14
//SRC      DD  DSN=&SRCLIB,DISP=(NEW,CATLG),UNIT=SYSDA,
//             SPACE=(TRK,(5,5,5)),
//             DCB=(RECFM=FB,LRECL=80,BLKSIZE=0,DSORG=PO)
//OBJ      DD  DSN=&OBJLIB,DISP=(NEW,CATLG),UNIT=SYSDA,
//             SPACE=(TRK,(5,5,5)),
//             DCB=(RECFM=FB,LRECL=80,BLKSIZE=0,DSORG=PO)
//LOAD     DD  DSN=&LOADLIB,DISP=(NEW,CATLG),UNIT=SYSDA,
//             SPACE=(TRK,(5,5,5)),
//             DCB=(RECFM=U,BLKSIZE=0,DSORG=PO)
//*
//COPYSRC  EXEC PGM=IEBGENER
//SYSPRINT DD  SYSOUT=*
//SYSUT1   DD  DISP=SHR,DSN=&HLQ..TEMP.MQXCOPY
//SYSUT2   DD  DISP=SHR,DSN=&SRCLIB(MQXCOPY)
//SYSIN    DD  DUMMY
//*
//* --- COMPILE (XL C driver CCNDRVR; use CNWCLANG if your site uses Open XL C/C++)
//CC       EXEC PGM=CCNDRVR,REGION=0M,
//             PARM='/SEARCH (''&LEPRFX..SCEEH.+'') OPT(0) DEBUG SOURCE LIST RENT'
//STEPLIB  DD  DISP=SHR,DSN=&LEPRFX..SCEERUN
//         DD  DISP=SHR,DSN=&LEPRFX..SCEERUN2
//         DD  DISP=SHR,DSN=&CPRFX..SCCNCMP
//SYSPRINT DD  SYSOUT=*
//SYSOUT   DD  SYSOUT=*
//SYSIN    DD  DISP=SHR,DSN=&SRCLIB(MQXCOPY)
//SYSLIN   DD  DISP=SHR,DSN=&OBJLIB(MQXCOPY)
//SYSUT1   DD  UNIT=SYSDA,SPACE=(CYL,(1,1))
//*
//* --- LINK-EDIT (include optional Debugger LE exit for profile-triggered debug)
//LKED     EXEC PGM=IEWL,COND=(0,NE),PARM='MAP,LIST,RENT'
//SYSLIN   DD  DISP=SHR,DSN=&OBJLIB(MQXCOPY)
//SYSLMOD  DD  DISP=SHR,DSN=&LOADLIB(MQXCOPY)
//SYSPRINT DD  SYSOUT=*
//SYSLIB   DD  DISP=SHR,DSN=&LEPRFX..SCEELKED
//* Optional: add your IBM z/OS Debugger SEQAMOD
//DBGSEQ   DD  DISP=SHR,DSN=YOUR.DEBUGGER.SEQAMOD
//SYSIN    DD  *
  ENTRY   MQXCOPY
  NAME    MQXCOPY(R)
  /*
//*
//* Note: If your site uses Open XL C/C++ (CLANG driver), replace CC step with:
//* //CC EXEC PGM=CNWCLANG,REGION=0M,PARM='-mzos-sys-include="//''&LEPRFX..SCEEH.+''" -O0 -g -qascii'
//* and ensure STEPLIB includes your CNWCLANG compiler SCNWCMP library.
//*
