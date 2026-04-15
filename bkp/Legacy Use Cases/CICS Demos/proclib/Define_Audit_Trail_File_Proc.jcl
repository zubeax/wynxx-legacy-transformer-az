//********************************************************************
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//* PROC to define an Audit Trail File for the Employee Application
//********************************************************************
//DEFAUD PROC QUAL=&SYSUID..ESDS,SUFX=1
//       EXPORT SYMLIST=(QUAL,SUFX)
//       SET QUAL=&QUAL
//       SET SUFX=&SUFX
//S1     EXEC PGM=IDCAMS
//SYSPRINT DD SYSOUT=*
//SYSIN    DD *,SYMBOLS=JCLONLY
 DELETE -
    &QUAL..EAUDIT&SUFX -
    CLUSTER -
    ERASE -
    PURGE
 DEFINE -
    CLUSTER ( -
        NAME(&QUAL..EAUDIT&SUFX) -
        RECORDS(100 100) -
        SPANNED -
        VOLUMES(yourvolume) -
        RECORDSIZE(532 532) -
        NONINDEXED -
        REUSE -
    )       
/*
//      PEND                   