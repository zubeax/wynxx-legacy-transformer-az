//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//* Create and seed Employee Master File
//********************************************************************
//S1     EXEC PGM=IDCAMS
//SEEDDATA DD DSN=&SYSUID..DATA.EMPMAST,
//            DISP=SHR
//SYSPRINT DD SYSOUT=*
//SYSIN    DD *
 DELETE - 
    yourhlq.KSDS.EMPMAST - 
    CLUSTER -
    ERASE -
    PURGE
 DEFINE -
    CLUSTER ( -
        NAME(yourhlq.KSDS.EMPMAST) -
        SHAREOPTIONS(3,3) -
        RECORDS(10 10) -
        VOLUMES(yourvolume) - 
        CONTROLINTERVALSIZE(4096) -
        FREESPACE(10 10) -
        KEYS(8 0) -
        RECORDSIZE(251 251) -
        INDEXED -
    ) - 
    DATA (NAME(yourhlq.KSDS.EMPMAST.DATA) - 
    ) -
    INDEX (NAME(yourhlq.KSDS.EMPMAST.INDEX) - 
 )
 IF LASTCC = 0 THEN -
     REPRO INFILE(SEEDDATA) OUTDATASET(yourhlq.KSDS.EMPMAST)
     IF LASTCC = 0 THEN - 
       LISTCAT ENTRIES(yourhlq.KSDS.EMPMAST) ALL 
       IF LASTCC = 0 THEN - 
         PRINT INDATASET(yourhlq.KSDS.EMPMAST) -
         CHARACTER
/*                   