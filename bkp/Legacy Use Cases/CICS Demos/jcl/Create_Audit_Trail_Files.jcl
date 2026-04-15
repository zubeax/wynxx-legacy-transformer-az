//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Pluralsight CICS Application Programming Fundamentals (COBOL)
//* Create reusable Entry Sequenced Data Sets for offline processing
//* of audit trail records. 
//********************************************************************
//          JCLLIB ORDER=your.PROCLIB
//DEFN1     EXEC DEFAUD,QUAL=&SYSUID..your.PROCLIB,SUFX=1
//DEFN2     EXEC DEFAUD,QUAL=&SYSUID..your.PROCLIB,SUFX=2
//