//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* COBOL COMPILE AND BIND FOR CICS 
//********************************************************************
//JLIB      JCLLIB ORDER=your.orgs.cics.PROCLIB
//S1      EXEC DFHZITCL,PROGLIB=your.cics.program.library
//COBOL.STEPLIB  DD DSN=your.orgs.cics.library,DISP=SHR
//COBOL.SYSIN    DD DSN=your.source.library(yourprogram),
//          DISP=SHR
//COBOL.SYSLIB   DD DSN=your.copy.library, 
//          DISP=SHR
//LKED.SYSIN DD *
  NAME yourprogram(R)