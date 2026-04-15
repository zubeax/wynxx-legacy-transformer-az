//PSBGEN   JOB  (ACCT),'PSBGEN',CLASS=A,MSGCLASS=X,NOTIFY=&SYSUID
//*****************************************************************
//* Assemble and link PSB
//*****************************************************************
//PSBGEN   EXEC PGM=ASMA90,REGION=0M,
// PARM='OBJECT,DECK,RENT'
//SYSLIB   DD  DISP=SHR,DSN=IMS.SDFSMAC
//SYSPRINT DD  SYSOUT=*
//SYSUT1   DD  UNIT=SYSDA,SPACE=(CYL,(1,1))
//SYSUT2   DD  UNIT=SYSDA,SPACE=(CYL,(1,1))
//SYSPUNCH DD  DSN=&&OBJ,UNIT=SYSDA,SPACE=(TRK,(1,1)),
//             DISP=(,PASS)
//SYSIN    DD  *
         PRINT NOGEN

* --------------------------------------------------------------------
* PSBGEN for ORDENTR MPP
*  - I/O PCB for message handling
*  - DB PCB for CUSTDB (read)
*  - DB PCB for ORDRDB (insert)
* --------------------------------------------------------------------
         PSB   PSBNAME=ORDENTR,LANG=COBOL
         PCB   TYPE=TP,MODNAME=ORDENTRO
         PCB   TYPE=DB,DBDNAME=CUSTDB,PROCOPT=G
         PCB   TYPE=DB,DBDNAME=ORDRDB,PROCOPT=A
         END

/*
//LKED     EXEC PGM=IEWL,PARM='XREF,LIST,RENT'
//SYSLMOD  DD  DISP=SHR,DSN=IMS.PSBLIB
//SYSPRINT DD  SYSOUT=*
//SYSLIN   DD  DSN=&&OBJ,DISP=(OLD,DELETE)
//         DD  DDNAME=SYSIN
//SYSIN    DD  DUMMY
