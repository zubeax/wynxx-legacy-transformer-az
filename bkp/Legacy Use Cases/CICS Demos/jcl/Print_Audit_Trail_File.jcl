//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//* Print Audit Trail File for the Employee Application
//********************************************************************
//S1     EXEC PGM=IDCAMS
//SYSPRINT DD SYSOUT=*
//SYSIN    DD *
 PRINT INDATASET(yourhlq.ESDS.EAUDIT1) CHARACTER
/*                   