//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Create and seed Registered Users file for the Employee Application 
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//********************************************************************
//S1     EXEC PGM=IDCAMS
//SEEDDATA DD DSN=seed.data.dsname,
//            DISP=SHR
//SYSPRINT DD SYSOUT=*
//SYSIN    DD *
 DELETE -
    hlq.mlq.EREGUSR -
    CLUSTER -
    ERASE -
    PURGE
 DEFINE -
    CLUSTER ( -
        NAME(hlq.mlq.EREGUSR) -
        RECORDS(10 10) -
        VOLUMES(yourvolume) -
        CONTROLINTERVALSIZE(4096) -
        FREESPACE(10 10) -
        KEYS(8 0) -
        RECORDSIZE(100 100) -
        INDEXED -
    ) -
    DATA (NAME(hlq.mlq.EREGUSR.DATA) -
    ) -
    INDEX (NAME(hlq.mlq.EREGUSR.INDEX) -
 )
 IF LASTCC = 0 THEN -
     REPRO INFILE(SEEDDATA) OUTDATASET(hlq.mlq.EREGUSR)
     IF LASTCC = 0 THEN -
         LISTCAT ENTRIES(hlq.mlq.EREGUSR)
         IF LASTCC = 0 THEN -
             PRINT INDATASET(hlq.mlq.EREGUSR)
/*