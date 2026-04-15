//ZFT2PAIN  JOB (ACCT),'BUILD ZFT2PAIN',CLASS=A,MSGCLASS=X,MSGLEVEL=(1,1)
//* ------------------------------------------------------------------
//* Compile & Link using IBM cataloged proc IGYWCL
//* Adjust libraries/DSNs to your site standards before submit.
//* ------------------------------------------------------------------
//COMPLNK  EXEC IGYWCL,PARM='XREF,LIST,OFFSET'
//COBOL.SYSIN  DD  DSN=YOUR.HLQ.SRC(ZFT2PAIN),DISP=SHR
//LKED.SYSLMOD DD  DSN=YOUR.HLQ.LOAD(ZFT2PAIN),DISP=SHR
//* Optionally: copybooks
//* //COBOL.SYSLIB DD  DSN=YOUR.HLQ.COPY,DISP=SHR
