//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//* Create Alternate Index and Path on Primary-Name for the
//* Employee Master File.
//********************************************************************
//S1     EXEC PGM=IDCAMS
//SYSPRINT DD SYSOUT=*
//SYSIN    DD *
 DELETE - 
    yourhlq.AIX.PRIMNAME - 
    ALTERNATEINDEX -
    ERASE -
    PURGE
 DEFINE -
    ALTERNATEINDEX ( -
        NAME(yourhlq.AIX.PRIMNAME) -
        RELATE(yourhlq.KSDS.EMPMAST) -
        KEYS(38 87) - 
        RECORDSIZE(51 51) -
        CYLINDERS(10 10) -
        UNIQUEKEY - 
        UPGRADE
    ) 
 DEFINE -
    PATH ( - 
       NAME(yourhlq.PATH.PRIMNAME) -
       PATHENTRY(yourhlq.AIX.PRIMNAME) -
    )
 BLDINDEX -
    INDATASET(yourhlq.KSDS.EMPMAST) -
    OUTDATASET(yourhlq.AIX.PRIMNAME)      
/*                   