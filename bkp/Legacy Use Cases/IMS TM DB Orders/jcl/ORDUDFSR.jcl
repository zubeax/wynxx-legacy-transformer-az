//ORDUNLD  JOB (ACCT),'ORDERS UNLOAD',CLASS=A,MSGCLASS=X,NOTIFY=&SYSUID
//*********************************************************************
//* Batch DL/I Unload for ORDRDB -> sequential flat file (FB, CSV-like)
//* Requires: ACB for PSB providing DB PCB to ORDRDB with PROCOPT=G
//*           Load module ORDUNLD in USER.LOADLIB (DLI batch program)
//*********************************************************************
//UNLOAD   EXEC PGM=DFSRRC00,REGION=0M,
//         PARM='DLI,ORDUNLD,ORDUNLDP'       <-- PGM=ORDUNLD, PSB=ORDUNLDP
//STEPLIB  DD  DISP=SHR,DSN=IMS.SDFSRESL
//         DD  DISP=SHR,DSN=USER.LOADLIB     <-- your unload program
//DFSRESLB DD  DISP=SHR,DSN=IMS.SDFSRESL
//DFSVSAMP DD  DISP=SHR,DSN=IMS.PROCLIB(DFSVSAMP)  <-- site standard
//IMS      DD  DISP=SHR,DSN=IMS.PROCLIB(DFSPBxxx)  <-- your IMSID PB parm
//ACBLIB   DD  DISP=SHR,DSN=IMS.ACBLIB
//DBDLIB   DD  DISP=SHR,DSN=IMS.DBDLIB
//PSBLIB   DD  DISP=SHR,DSN=IMS.PSBLIB
//SYSPRINT DD  SYSOUT=*
//SYSOUT   DD  SYSOUT=*
//* Output (unload) dataset. Adjust space/volumes as needed.
//OUTORDS  DD  DSN=USER.ORDRDB.UNLOAD(+1),
//             DISP=(NEW,CATLG,DELETE),
//             UNIT=SYSDA,SPACE=(CYL,(5,5),RLSE),
//             DCB=(RECFM=FB,LRECL=120,BLKSIZE=0)
//* optional: capture rejects or diagnostics, if your program writes them
//REJECTS  DD  DSN=USER.ORDRDB.REJ(+1),
//             DISP=(NEW,CATLG,DELETE),
//             UNIT=SYSDA,SPACE=(TRK,(1,1)),DCB=(RECFM=FB,LRECL=120)
//*