//DEFTRAN  JOB (ACCT),'DEFINE TRAN',CLASS=A,MSGCLASS=X,NOTIFY=&SYSUID
//*****************************************************************
//* Define/Update IMS transaction (requires IMS commands authority)
//* Adjust IMSID and LTERM as needed. Alternatively define in Stage-1.
//*****************************************************************
//DFSINS   EXEC PGM=DFSCP001,REGION=0M,PARM='IMSID'
//STEPLIB  DD  DISP=SHR,DSN=IMS.SDFSRESL
//IMS      DD  DISP=SHR,DSN=IMS.PROCLIB
//SYSOUT   DD  SYSOUT=*
//SYSPRINT DD  SYSOUT=*
//SYSIN    DD  *
  UPDATE TRAN NAME(ORD1) PGM(ORDENTR) SCHDTYP(MPP) PRI(5) \
    RESP(YES) MODE(TERM) MSGTYPE(NOTSNPS)
/*
