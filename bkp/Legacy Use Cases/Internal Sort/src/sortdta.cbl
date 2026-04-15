       IDENTIFICATION DIVISION.
       PROGRAM-ID. SORTDTA.

       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT INFILE ASSIGN TO SYS010
               ORGANIZATION IS SEQUENTIAL.
           SELECT OUTFILE ASSIGN TO SYS050
               ORGANIZATION IS SEQUENTIAL.
           SELECT SORTFILE ASSIGN TO SORT0001.

       DATA DIVISION.
       FILE SECTION.
       FD  INFILE.
       01  IN-REC.
           05 BLZ         PIC X(08).
           05 KTO         PIC X(10).
           05 REST        PIC X(62).

       FD  OUTFILE.
       01  OUT-REC       PIC X(80).

       SD  SORTFILE.
       01  SORT-REC.
           05 S-BLZ       PIC X(08).
           05 S-KTO       PIC X(10).
           05 S-REST      PIC X(62).

       WORKING-STORAGE SECTION.
       01 EOF-IN PIC X VALUE 'N'.
       01 EOF-OUT PIC X VALUE 'N'.

       PROCEDURE DIVISION.

       MAIN-LOGIC.
           SORT SORTFILE
               ON ASCENDING KEY S-BLZ S-KTO
               INPUT PROCEDURE IS READ-IN
               OUTPUT PROCEDURE IS WRITE-OUT
           STOP RUN.

       READ-IN.
           OPEN INPUT INFILE
           PERFORM UNTIL EOF-IN
               READ INFILE
                   AT END MOVE 'Y' TO EOF-IN
                   NOT AT END
                       MOVE IN-REC TO SORT-REC
                       RELEASE SORT-REC
               END-READ
           END-PERFORM
           CLOSE INFILE
           .

       WRITE-OUT.
           OPEN OUTPUT OUTFILE
           PERFORM UNTIL EOF-OUT
               RETURN SORTFILE
                   AT END MOVE 'Y' TO EOF-OUT
                   NOT AT END
                       MOVE SORT-REC TO OUT-REC
                       WRITE OUT-REC
               END-RETURN
           END-PERFORM
           CLOSE OUTFILE
           .
       END PROGRAM SORTDTA.
