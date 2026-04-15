//MQDEFS  JOB (ACCT),'DEFINE MQ OBJ',CLASS=A,MSGCLASS=A,NOTIFY=&SYSUID
//*****************************************************************
//* Define MQ objects (INITQ, application Q, PROCESS, reply Q)
//* Tailor: QMGR name in PARM, library DSNs, and command input DSN
//*****************************************************************
//CSQUTIL  EXEC PGM=CSQUTIL,PARM='&QMGR',REGION=0M
//STEPLIB  DD DISP=SHR,DSN=MQ9R?.SCSQAUTH
//SYSPRINT DD SYSOUT=*
//SYSIN    DD DUMMY
//CSQUCMD  DD DISP=SHR,DSN=your.hlq.mqsc(MQOBJS)
/*
