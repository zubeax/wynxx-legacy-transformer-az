//ALLOC   JOB (ACCT),'ALLOC LOAD DS',CLASS=A,MSGCLASS=A,NOTIFY=&SYSUID
//* Allocate and upload the two delimited input datasets for LOAD
//ALLOC1  EXEC PGM=IEFBR14
//CLIENTS DD  DISP=(NEW,CATLG,DELETE),
//             DSN=your.hlq.data.CLIENTS,
//             UNIT=SYSDA,SPACE=(TRK,(1,1)),
//             DCB=(RECFM=VB,LRECL=4096,BLKSIZE=0)
//ORDERS  DD  DISP=(NEW,CATLG,DELETE),
//             DSN=your.hlq.data.ORDERS,
//             UNIT=SYSDA,SPACE=(TRK,(1,1)),
//             DCB=(RECFM=VB,LRECL=4096,BLKSIZE=0)
//* Use your site file transfer to upload data/clients.dat
//* to your.hlq.data.CLIENTS
//* and data/orders.dat to your.hlq.data.ORDERS in text mode
