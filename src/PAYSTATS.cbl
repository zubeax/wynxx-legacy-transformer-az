       IDENTIFICATION DIVISION.
       PROGRAM-ID. PAYSTATS.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT OUTFILE ASSIGN TO UT-S-OUTFILE
                  ORGANIZATION IS SEQUENTIAL.

       DATA DIVISION.
       FILE SECTION.
       FD  OUTFILE
           RECORDING MODE IS F
           LABEL RECORDS ARE STANDARD
           RECORD CONTAINS 32756 CHARACTERS
           BLOCK CONTAINS 0 RECORDS
           DATA RECORD IS OUT-REC.
       01  OUT-REC                  PIC X(32756).

       WORKING-STORAGE SECTION.
       01  WS-PROG-NAME             PIC X(08) VALUE 'PAYSTATS'.
       01  WS-COUNTRY               PIC X(2)  VALUE 'DE'.
       01  WS-REF-YEAR              PIC 9(4)  VALUE 2025.
       01  WS-REF-HALF              PIC X(2)  VALUE 'H1'.
       01  WS-REF-QUARTER           PIC X(2)  VALUE 'Q1'.
       01  WS-DATE-FROM             PIC X(10) VALUE '2025-01-01'.
       01  WS-DATE-TO               PIC X(10) VALUE '2025-03-31'.

       01  WS-PROGRAM-NAMES.
           05  WS-PGM-SUBINP        PIC X(8) VALUE 'SUBINP'.
           05  WS-PGM-SUBAGG        PIC X(8) VALUE 'SUBAGG'.
           05  WS-PGM-SUBFMT        PIC X(8) VALUE 'SUBFMT'.

       EXEC SQL INCLUDE SQLCA END-EXEC.

       COPY 'CPBKPSTA'.

       01  AGG-ITEM.
           05  AGG-SERVICE          PIC X(30).
           05  AGG-CHANNEL          PIC X(30).
           05  AGG-SCA              PIC X(1).
           05  AGG-COUNT            PIC 9(9) COMP.
           05  AGG-TOTAL            PIC 9(13)V99.

       01  AGG-TABLE.
           05  AGG-NUM              PIC 9(9) COMP VALUE 0.
           05  AGG-MAX              PIC 9(9) COMP VALUE 2000.
           05  AGG-ROWS OCCURS 1 TO 2000 DEPENDING ON AGG-NUM
                                         INDEXED BY AG-IDX.
               10  AGG-ENTRY.
                   15  AE-SERVICE   PIC X(30).
                   15  AE-CHANNEL   PIC X(30).
                   15  AE-SCA       PIC X(1).
                   15  AE-COUNT     PIC 9(9) COMP.
                   15  AE-TOTAL     PIC 9(13)V99.

       01  XML-BUFFER               PIC X(32756).
       01  XML-LEN                  PIC 9(9) COMP.

       LINKAGE SECTION.
       PROCEDURE DIVISION.
       MAIN-LOGIC.

           EXEC SQL INCLUDE PKGSETXX   END-EXEC.

           DISPLAY '*** ' WS-PROG-NAME ' START'

           CALL WS-PGM-SUBINP USING BY REFERENCE
             WS-DATE-FROM
             WS-DATE-TO
             WS-COUNTRY
             TXN-TABLE
           END-CALL

           IF SQLCODE NOT = 0 AND SQLCODE NOT = 100
           THEN
              DISPLAY 'SUBINP returned SQLCODE=' SQLCODE
           END-IF

           CALL WS-PGM-SUBAGG USING BY REFERENCE TXN-TABLE AGG-TABLE

           CALL WS-PGM-SUBFMT USING BY REFERENCE
              WS-COUNTRY
              WS-REF-YEAR WS-REF-HALF WS-REF-QUARTER
              AGG-TABLE XML-BUFFER
           END-CALL

           OPEN OUTPUT OUTFILE

           MOVE XML-BUFFER TO OUT-REC
           WRITE OUT-REC

           CLOSE OUTFILE

           DISPLAY '*** ' WS-PROG-NAME ' END'
           GOBACK.
       END PROGRAM PAYSTATS.
