//jobname  JOB [installation-specific parameters],NOTIFY=&SYSUID 
//********************************************************************
//* Assemble BMS macros into physical and symbolic maps. 
//********************************************************************
//JLIB      JCLLIB ORDER=your.orgs.cics.PROCLIB
//BMS       EXEC DFHMAPS,INDEX=your.orgs.cics,                        X
//            DSCTLIB=your.cobol.copy.library,                        X
//            MAPLIB=your.cics.program.library,                       X
//            MAPNAME=yourmapname
//COPY.SYSUT1    DD DSN=your.mapset.source(member),DISP=SHR