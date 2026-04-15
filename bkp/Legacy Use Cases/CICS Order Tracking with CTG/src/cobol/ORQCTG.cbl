*--------------------------------------------------------------*
       * Program : ORQCTG (CICS program invoked via CTG ECI)          *
        * Purpose : Accept pipe-delimited order request in COMMAREA    *
       *           Format: ORDER_ID|CLIENT_ID|YYYY-MM-DD|STATUS|AMOUNT *
       *           Persist to Db2 (CLAPP.ORDERS), return 80-char reply *
       *           'CONFIRMED|ORDER_ID|OK' padded to 80 bytes.         *
       *--------------------------------------------------------------*
       IDENTIFICATION DIVISION.
       PROGRAM-ID. ORQCTG.

       ENVIRONMENT DIVISION.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WS-MSG             PIC X(80)  VALUE SPACES.
       01 WS-DELIM           PIC X      VALUE '|'.
       01 WS-AMOUNT-TEXT     PIC X(20)  VALUE SPACES.

       EXEC SQL INCLUDE SQLCA END-EXEC.

       01 HV-ORDER.
          05 HV-ORDER-ID     PIC X(12).
          05 HV-CLIENT-ID    PIC X(10).
          05 HV-ORDER-DATE   PIC X(10).
          05 HV-STATUS       PIC X(12).
          05 HV-AMOUNT       PIC S9(7)V99 COMP-3.

       LINKAGE SECTION.
       01 DFHCOMMAREA.
          05 CA-DATA         PIC X(4096).

       PROCEDURE DIVISION USING DFHCOMMAREA.
       MAIN-SECTION.

           EVALUATE TRUE
              WHEN EIBCALEN = 0
                 MOVE 'INVALID REQUEST: NO DATA' TO WS-MSG
                 PERFORM SEND-REPLY
                 GOBACK
              WHEN OTHER
                 PERFORM PROCESS-REQUEST
           END-EVALUATE.
           GOBACK.

       PROCESS-REQUEST.
           UNSTRING CA-DATA DELIMITED BY WS-DELIM INTO
                   HV-ORDER-ID
                   HV-CLIENT-ID
                   HV-ORDER-DATE
                   HV-STATUS
                   WS-AMOUNT-TEXT
           END-UNSTRING
           ON OVERFLOW
               MOVE 'INVALID FORMAT' TO WS-MSG
               PERFORM SEND-REPLY
               GOBACK
           END-UNSTRING
           
           IF WS-AMOUNT-TEXT NOT = SPACES
              MOVE FUNCTION NUMVAL(WS-AMOUNT-TEXT) TO HV-AMOUNT
           ELSE
              MOVE 0 TO HV-AMOUNT
           END-IF

           EXEC SQL
             INSERT INTO CLAPP.ORDERS
              (ORDER_ID, CLIENT_ID, ORDER_DATE, STATUS, TOTAL_AMT)
             VALUES
              (:HV-ORDER-ID, :HV-CLIENT-ID, :HV-ORDER-DATE,
               :HV-STATUS, :HV-AMOUNT)
           END-EXEC

           IF SQLCODE = 0
              STRING 'CONFIRMED|' DELIMITED BY SIZE
                     HV-ORDER-ID  DELIMITED BY SIZE
                     '|'          DELIMITED BY SIZE
                     'OK'         DELIMITED BY SIZE
                     INTO WS-MSG
              END-STRING
           ELSE
              STRING 'DB2ERR|' DELIMITED BY SIZE
                     HV-ORDER-ID  DELIMITED BY SIZE
                     '|'          DELIMITED BY SIZE
                     FUNCTION NUMVAL-C (FUNCTION TRIM (SQLCODE))
                        DELIMITED BY SIZE
                     INTO WS-MSG
              END-STRING
           END-IF

           EXEC CICS SYNCPOINT END-EXEC
           PERFORM SEND-REPLY.

       SEND-REPLY.
           *> Pad or truncate to 80 bytes
           IF LENGTH(WS-MSG) < 80
              STRING WS-MSG DELIMITED BY SIZE
                     SPACE  DELIMITED BY SIZE
                     INTO WS-MSG
              END-STRING
           END-IF
           MOVE WS-MSG(1:80) TO CA-DATA(1:80).
       END PROGRAM ORQCTG.
