       IDENTIFICATION DIVISION.
       PROGRAM-ID. ECHOPGM.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       77  DLI-GU     PIC X(4) VALUE 'GU  '.
       77  DLI-ISRT   PIC X(4) VALUE 'ISRT'.
       
       01  IN-MSG.
           05 IN-LL        PIC S9(4) COMP.
           05 IN-ZZ        PIC S9(4) COMP VALUE 0.
           05 IN-TRANCODE  PIC X(8).
           05 IN-DATA      PIC X(252).
       
       01  OUT-MSG.
           05 OUT-LL       PIC S9(4) COMP.
           05 OUT-ZZ       PIC S9(4) COMP VALUE 0.
           05 OUT-DATA     PIC X(252).
       
       LINKAGE SECTION.
       01  IO-PCB.
           05 IO-LTERM     PIC X(8).
           05 IO-RES1      PIC X(2).
           05 IO-STATUS    PIC X(2).
           05 FILLER       PIC X(52).
       
       PROCEDURE DIVISION USING IO-PCB.
       MAIN-LOGIC.
           CALL 'CBLTDLI' USING DLI-GU IO-PCB IN-MSG
           IF IO-STATUS NOT = '  '
              GO TO PROGRAM-END
           END-IF
       
           MOVE SPACES TO OUT-DATA
           STRING 'ECHO: ' DELIMITED BY SIZE
                  IN-DATA  DELIMITED BY SIZE
                  INTO OUT-DATA
           END-STRING
       
           COMPUTE OUT-LL = 4 + FUNCTION LENGTH(OUT-DATA)
       
           CALL 'CBLTDLI' USING DLI-ISRT IO-PCB OUT-MSG
       
       PROGRAM-END.
           GOBACK.
