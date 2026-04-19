//GRANTECB JOB (AAAA,BBB,CCCC),'GRANTECB',
//             CLASS=A,MSGCLASS=X,MSGLEVEL=(1,1),
//             REGION=1024M,NOTIFY=&SYSUID
//**********************************************************************
//*  GRANT DB2 PERMISSIONS TO TECHNICAL USERS                          *
//**********************************************************************
//DSNTIAD  EXEC PGM=IKJEFT1A,DYNAMNBR=100
//STEPLIB  DD DISP=SHR,DSN=DBD1.SDSNEXIT
//         DD DISP=SHR,DSN=DB2V13.SDSNLOAD
//DBRMLIB  DD DSN=DBD1.DBRMLIB.DATA,DISP=SHR
//SYSTSPRT DD SYSOUT=*
//SYSPRINT DD SYSOUT=*
//SYSUDUMP DD SYSOUT=*
//SYSIN    DD *
    REVOKE PACKADM IN COLLECTION DB FROM DB2USER;
    REVOKE CREATE  IN COLLECTION DB FROM DB2USER;
    GRANT DBADM                     ON DATABASE ECBSTATS TO DB2USER;
--  GRANT IMAGCOPY, LOAD, RECOVERDB, REORG, REPAIR, STATS
--                                  ON DATABASE ECBSTATS TO DB2USER;
   GRANT CREATE                     IN COLLECTION DB
                                    TO DB2USER;
-- GRANT BIND, EXECUTE              ON PACKAGE DB.*
   GRANT ALL                        ON PACKAGE DB.*
                                    TO DB2USER;
   GRANT BIND, EXECUTE              ON PLAN ECB01PLN
                                    TO DB2USER;

   GRANT DBADM                      ON DATABASE ECBSTATS TO DB2CICS;
   GRANT EXECUTE                    ON PACKAGE DB.*
                                    TO DB2CICS;
   GRANT EXECUTE                    ON PLAN ECB01PLN
                                    TO DB2CICS;
/*
//SYSTSIN  DD *
 DSN SYSTEM(DBD1)
 RUN  PROGRAM(DSNTEP4) PLAN(DSNTP413) +
      LIB('DBD1.RUNLIB.LOAD') +
      PARMS('/ALIGN(MID)')
 END
/*
