
//CLKBUILD JOB (ACCT),'STCK EXAMPLE',CLASS=A,MSGCLASS=X,REGION=0M
//*--------------------------------------------------------------*
//*  1) ASSEMBLE STCKREAD & STCKPARSE                            *
//*--------------------------------------------------------------*
//ASM1     EXEC PGM=ASMA90,PARM='DECK,NOOBJECT,LIST,RENT'
//SYSLIB   DD   DSN=SYS1.MACLIB,DISP=SHR
//SYSUT1   DD   UNIT=SYSDA,SPACE=(1700,(200,200))
//SYSPRINT DD   SYSOUT=*
//SYSPUNCH DD   DSN=&&OBJ1,UNIT=SYSDA,SPACE=(TRK,(1,1)),
//               DISP=(,PASS),DCB=(RECFM=FB,LRECL=80,BLKSIZE=3120)
//SYSIN    DD   *
  * Paste STCKREAD.asm here if you build inline; otherwise point SYSLIN
/*
//ASM2     EXEC PGM=ASMA90,PARM='DECK,NOOBJECT,LIST,RENT'
//SYSLIB   DD   DSN=SYS1.MACLIB,DISP=SHR
//SYSUT1   DD   UNIT=SYSDA,SPACE=(1700,(200,200))
//SYSPRINT DD   SYSOUT=*
//SYSPUNCH DD   DSN=&&OBJ2,UNIT=SYSDA,SPACE=(TRK,(1,1)),
//               DISP=(,PASS),DCB=(RECFM=FB,LRECL=80,BLKSIZE=3120)
//SYSIN    DD   *
  * Paste STCKPARSE.asm here if you build inline
/*
//*--------------------------------------------------------------*
//*  2) COMPILE COBOL (MAIN + JULIAN7)                           *
//*--------------------------------------------------------------*
//COB1     EXEC PGM=IGYCRCTL,PARM='RENT,LIB,NOADV'
//STEPLIB  DD   DSN=IGY.V6R3M0.SIGYCOMP,DISP=SHR
//SYSPRINT DD   SYSOUT=*
//SYSIN    DD   *
  * Paste JULIAN7.cbl here if you build inline
/*
//COB1OBJ  DD   DSN=&&OBJ3,UNIT=SYSDA,SPACE=(TRK,(1,1)),
//             DISP=(,PASS)
//COB2     EXEC PGM=IGYCRCTL,PARM='RENT,LIB,NOADV'
//STEPLIB  DD   DSN=IGY.V6R3M0.SIGYCOMP,DISP=SHR
//SYSPRINT DD   SYSOUT=*
//SYSIN    DD   *
  * Paste CLKMAIN.cbl here if you build inline
/*
//COB2OBJ  DD   DSN=&&OBJ4,UNIT=SYSDA,SPACE=(TRK,(1,1)),
//             DISP=(,PASS)
//*--------------------------------------------------------------*
//*  3) LINKEDIT                                                  *
//*--------------------------------------------------------------*
//LKED     EXEC PGM=IEWL,PARM='XREF,LIST,RENT,AMODE=31,RMODE=ANY'
//SYSPRINT DD   SYSOUT=*
//SYSLMOD  DD   DSN=YOUR.LOADLIB,DISP=SHR
//SYSLIN   DD   DSN=&&OBJ1,DISP=(OLD,DELETE)
//         DD   DSN=&&OBJ2,DISP=(OLD,DELETE)
//         DD   DSN=&&OBJ3,DISP=(OLD,DELETE)
//         DD   DSN=&&OBJ4,DISP=(OLD,DELETE)
//*
//* To run:
//* //RUN EXEC PGM=CLKMAIN
//* //STEPLIB DD  DSN=YOUR.LOADLIB,DISP=SHR
//* //SYSOUT  DD  SYSOUT=*
//*
