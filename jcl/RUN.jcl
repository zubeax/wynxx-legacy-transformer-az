//ZFT2RUN  JOB (ACCT),'RUN ZFT2PAIN',CLASS=A,MSGCLASS=X,MSGLEVEL=(1,1)
//* ------------------------------------------------------------------
//* 1. Allocate OUTPUT VB UTF-8 dataset (adjust UNIT/SPACE)
//* ------------------------------------------------------------------
//ALLOCOUT EXEC PGM=IEFBR14
//OUTDD    DD  DSN=YOUR.HLQ.SEPA.PAIN001.VB,
//             DISP=(NEW,CATLG,DELETE),UNIT=SYSDA,
//             SPACE=(CYL,(5,1)),
//             DCB=(RECFM=VB,LRECL=4096,BLKSIZE=0),
//             CCSID=1208
//* Note: Some shops prefer setting CCSID via DATACLAS. If CCSID
//*       is not accepted on DD, use a data class with CCSID=1208.
//* ------------------------------------------------------------------
//* 2. Execute the program
//* ------------------------------------------------------------------
//STEP1    EXEC PGM=ZFT2PAIN
//STEPLIB  DD  DSN=YOUR.HLQ.LOAD,DISP=SHR
//DDname-IN  DD  DSN=YOUR.HLQ.FT.INPUT80,DISP=SHR
//DDname-OUT DD  DSN=YOUR.HLQ.SEPA.PAIN001.VB,DISP=SHR
//SYSOUT   DD  SYSOUT=*
