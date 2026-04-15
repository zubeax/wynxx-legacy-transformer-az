//DBDGEN   JOB  (ACCT),'DBDGEN',CLASS=A,MSGCLASS=X,NOTIFY=&SYSUID
//*****************************************************************
//* Assemble and link DBDs
//*****************************************************************
//DBDGEN   EXEC PGM=ASMA90,REGION=0M,
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
* DBDGEN for CUSTOMER database (HISAM for simplicity)
* Root segment: CUSTROOT keyed by CUST-ID
* --------------------------------------------------------------------
         DBD   NAME=CUSTDB,ACCESS=HISAM
         DATASET DD1=IMS.CUSTDB,DEVICE=3380
         SEGM  NAME=CUSTROOT,BYTES=80
         FIELD NAME=(CUST-ID,SEQ,U),BYTES=10,START=1
         FIELD NAME=CUST-NAME,BYTES=30,START=11
         FIELD NAME=CUST-ADDR,BYTES=40,START=41
         END


* --------------------------------------------------------------------
* DBDGEN for ORDER database (HISAM for simplicity)
* Root segment: ORDERSEG keyed by ORDER-ID
* --------------------------------------------------------------------
         DBD   NAME=ORDRDB,ACCESS=HISAM
         DATASET DD1=IMS.ORDRDB,DEVICE=3380
         SEGM  NAME=ORDERSEG,BYTES=52
         FIELD NAME=(ORDER-ID,SEQ,U),BYTES=14,START=1
         FIELD NAME=ORD-CUST-ID,BYTES=10,START=15
         FIELD NAME=ORD-ITEM-ID,BYTES=10,START=25
         FIELD NAME=ORD-QUANTITY,BYTES=5,START=35
         FIELD NAME=ORD-ORDER-DATE,BYTES=8,START=40
         END

/*
//LKED     EXEC PGM=IEWL,PARM='XREF,LIST,RENT'
//SYSLMOD  DD  DISP=SHR,DSN=IMS.DBDLIB
//SYSPRINT DD  SYSOUT=*
//SYSLIN   DD  DSN=&&OBJ,DISP=(OLD,DELETE)
//         DD  DDNAME=SYSIN
//SYSIN    DD  DUMMY
