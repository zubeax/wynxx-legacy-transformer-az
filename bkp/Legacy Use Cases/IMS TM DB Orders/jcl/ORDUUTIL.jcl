//ORDUUTL  JOB (ACCT),'IMS UNLOAD',CLASS=A,MSGCLASS=X,NOTIFY=&SYSUID
//*********************************************************************
//* IMS Database Unload Utility (template)
//* Tailor PROC/PGM and control statements to your IMS level/standards.
//* Outputs a flat file with all ORDERSEG occurrences from ORDRDB.
//*********************************************************************
//UNLOAD   EXEC PGM=DFSURGU0,REGION=0M
//STEPLIB  DD  DISP=SHR,DSN=IMS.SDFSRESL
//DFSRESLB DD  DISP=SHR,DSN=IMS.SDFSRESL
//IMS      DD  DISP=SHR,DSN=IMS.PROCLIB(DFSPBxxx)   <-- IMSID parms
//DBDLIB   DD  DISP=SHR,DSN=IMS.DBDLIB
//ACBLIB   DD  DISP=SHR,DSN=IMS.ACBLIB
//SYSPRINT DD  SYSOUT=*
//SYSUDUMP DD  SYSOUT=*
//* Unload output dataset
//DFSURGU1 DD  DSN=USER.ORDRDB.UNLOAD.UTIL(+1),
//             DISP=(NEW,CATLG,DELETE),
//             UNIT=SYSDA,SPACE=(CYL,(5,5),RLSE),
//             DCB=(RECFM=FB,LRECL=256,BLKSIZE=0)
//* Input control (example). Tailor selection/format options to utility.
//SYSIN    DD  *
  DBNAME=ORDRDB
  SEGMENT=ORDERSEG
  OUTPUT=DFSURGU1
/*
//*