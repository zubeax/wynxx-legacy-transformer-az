//BPECB01P JOB (ACC1,ACC2,ACCT3),'BINDPLAN',
//             CLASS=A,MSGCLASS=X,MSGLEVEL=(1,1),
//             REGION=256M,NOTIFY=&SYSUID
//*-------------------------------------------------------*
//*    BIND DB/2 PLAN                                     *
//*-------------------------------------------------------*
//*  CAVEAT: THE COLLECTION ID IN THE PLAN'S PACKAGE LIST *
//*          MUST BE EXPLICITLY SET WITH A                *
//*          'SET CURRENT PACKAGESET'                     *
//*          STATEMENT IN THE DB/2 PROGRAMS ACCESSING     *
//*          THE DATABASE.                                *
//*-------------------------------------------------------*
//BINDPL   EXEC PGM=IKJEFT01
//STEPLIB  DD DISP=SHR,DSN=DBD1.SDSNEXIT
//         DD DISP=SHR,DSN=DB2V13.SDSNLOAD
//         DD DISP=SHR,DSN=DBD1.RUNLIB.LOAD
//SYSTSPRT DD SYSOUT=*
//SYSPRINT DD SYSOUT=*
//SYSTSIN  DD *
  DSN SYSTEM(DBD1)
     BIND PLAN(ECB01PLN) EXPLAIN(NO)          +
     OWNER(DB2USER) QUALIFIER(DB)             +
     PKLIST(*.DB.*)                           +
     ACQUIRE(USE) RELEASE(COMMIT)             +
     VALIDATE(BIND) ISOLATION(CS)             +
     ENABLE (*)                               +
     ACTION (REPLACE)                         +
     RETAIN
  END
/*
