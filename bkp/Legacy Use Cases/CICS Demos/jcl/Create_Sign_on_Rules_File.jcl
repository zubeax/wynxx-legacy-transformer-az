//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//* Create and seed Sign-on Rules File
//********************************************************************
//S1     EXEC PGM=IDCAMS
//SEEDDATA DD DSN=&SYSUID..DATA.ESONRUL,
//            DISP=SHR
//SYSPRINT DD SYSOUT=*
//SYSIN    DD *
 DELETE - 
    yourhlq.RRDS.ESONRUL - 
    CLUSTER -
    ERASE -
    PURGE
 DEFINE -
    CLUSTER ( -
        NAME(yourhlq.RRDS.ESONRUL) -
        SHAREOPTIONS(3,3) -
        RECORDS(10 10) -
        VOLUMES(yourvolume) - 
        CONTROLINTERVALSIZE(4096) -
        FREESPACE(10 10) -
        RECORDSIZE(10 10) -
        NUMBERED -
    ) - 
    DATA (NAME(yourhlq.RRDS.ESONRUL.DATA) -  
 )
 IF LASTCC = 0 THEN -
     REPRO INFILE(SEEDDATA) OUTDATASET(yourhlq.KSDS.EMPMAST)
     IF LASTCC = 0 THEN - 
       LISTCAT ENTRIES(yourhlq.KSDS.EMPMAST) ALL 
       IF LASTCC = 0 THEN - 
         PRINT INDATASET(yourhlq.KSDS.EMPMAST) -
         CHARACTER
/*                   