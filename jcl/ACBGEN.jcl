//ACBGEN   JOB  (ACCT),'ACBGEN',CLASS=A,MSGCLASS=X,NOTIFY=&SYSUID
//*****************************************************************
//* Build ACBs from DBDs and PSBs
//*****************************************************************
//ACBGEN   EXEC PGM=DFS3PU00,REGION=0M
//STEPLIB  DD  DISP=SHR,DSN=IMS.SDFSRESL
//IMS      DD  DISP=SHR,DSN=IMS.DBDLIB
//PSB      DD  DISP=SHR,DSN=IMS.PSBLIB
//ACBLIB   DD  DISP=SHR,DSN=IMS.ACBLIB
//SYSPRINT DD  SYSOUT=*
