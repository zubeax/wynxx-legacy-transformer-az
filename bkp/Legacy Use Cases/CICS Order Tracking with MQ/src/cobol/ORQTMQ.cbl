*-----------------------------------------------------------------*
       * Program : ORQTMQ  (CICS/MQ trigger-driven order persistence)    *
       * Purpose : Started by CKTI from MQ trigger message.               *
       *           Reads order request from application queue (from       *
       *           MQTM.QNAME), persists to Db2, and replies to           *
       *           MQMD.ReplyToQ in the original request.                 *
       * Platform: IBM Enterprise COBOL for z/OS + CICS TS 6.1 + MQ z/OS  *
       *           + Db2 13 for z/OS                                      *
       *-----------------------------------------------------------------*
       IDENTIFICATION DIVISION.
       PROGRAM-ID. ORQTMQ.

       ENVIRONMENT DIVISION.
       DATA DIVISION.

       WORKING-STORAGE SECTION.
       01 WS-RESP         PIC S9(9) COMP VALUE 0.
       01 WS-RESP2        PIC S9(9) COMP VALUE 0.
       01 WS-MSG          PIC X(80) VALUE SPACES.

       * MQ structures and constants
       01  MQ-CONN-HANDLE      PIC S9(9) BINARY VALUE 0.
       01  MQ-OBJ-HANDLE-IN    PIC S9(9) BINARY VALUE 0.
       01  MQ-OBJ-HANDLE-OUT   PIC S9(9) BINARY VALUE 0.
       01  MQ-COMPCODE         PIC S9(9) BINARY VALUE 0.
       01  MQ-REASON           PIC S9(9) BINARY VALUE 0.
       01  MQ-DATA-LEN         PIC S9(9) BINARY VALUE 0.

       01  MQOD.
           COPY CMQODV.
       01  MQMD.
           COPY CMQMDV.
       01  MQGMO.
           COPY CMQGMOV.
       01  MQPMO.
           COPY CMQPMOV.
       01  MQTM.
           COPY CMQTMV.

       01  IN-BUFFER.
           05 IN-TEXT     PIC X(512).

       01  OUT-BUFFER.
           05 OUT-TEXT    PIC X(256).

       * Db2 host variables (reuse CLAPP schema)
       EXEC SQL INCLUDE SQLCA END-EXEC.
       01  HV-ORDER.
           05 HV-ORDER-ID     PIC X(12).
           05 HV-CLIENT-ID    PIC X(10).
           05 HV-ORDER-DATE   PIC X(10).
           05 HV-STATUS       PIC X(12).
           05 HV-AMOUNT       PIC S9(7)V99 COMP-3.

       01  WS-QUEUE-NAME      PIC X(48) VALUE SPACES.
       01  WS-REPLYQ          PIC X(48) VALUE SPACES.
       01  WS-REPLYQM         PIC X(48) VALUE SPACES.

       01  WS-DELIM   PIC X VALUE '|'.

       LINKAGE SECTION.
       01 DFHCOMMAREA.
          05 FILLER PIC X(1).

       PROCEDURE DIVISION.
       MAIN.
           *> Retrieve the MQTM trigger message from CKTI
           EXEC CICS RETRIEVE INTO(MQTM) LENGTH(LENGTH OF MQTM)
                          RESP(WS-RESP) RESP2(WS-RESP2) END-EXEC
           IF WS-RESP NOT = DFHRESP(NORMAL)
              MOVE 'No trigger message passed' TO WS-MSG
              GOBACK
           END-IF

           *> Target application queue name comes from MQTM-QNAME
           MOVE TM-Q-NAME TO WS-QUEUE-NAME

           *> Open the application queue for input under syncpoint
           MOVE SPACES TO MQOD
           MOVE WS-QUEUE-NAME TO OD-OBJECT-NAME
           MOVE MQOO-INPUT-AS-Q-DEF + MQOO-FAIL-IF-QUIESCING
                TO OD-OPTIONS
           CALL 'MQOPEN' USING MQ-CONN-HANDLE MQOD MQ-OBJ-HANDLE-IN
                                 MQ-COMPCODE MQ-REASON
           IF MQ-COMPCODE NOT = MQCC-OK
              MOVE 'MQOPEN input failed' TO WS-MSG
              GOBACK
           END-IF

           PERFORM UNTIL 1 = 2
              MOVE SPACES TO MQMD
              MOVE MQGMO-WAIT + MQGMO-SYNCPOINT + MQGMO-FAIL-IF-QUIESCING
                   TO GMO-OPTIONS
              MOVE 5000 TO GMO-WAIT-INTERVAL
              MOVE LENGTH OF IN-TEXT TO MQ-DATA-LEN
              CALL 'MQGET' USING MQ-CONN-HANDLE MQ-OBJ-HANDLE-IN
                                  MQMD MQGMO MQ-DATA-LEN IN-BUFFER
                                  MQ-COMPCODE MQ-REASON
              EVALUATE TRUE
                 WHEN MQ-COMPCODE = MQCC-OK
                    PERFORM PROCESS-ONE-MESSAGE
                 WHEN MQ-COMPCODE = MQCC-WARNING AND
                      MQ-REASON    = MQRC-NO-MSG-AVAILABLE
                    EXIT PERFORM
                 WHEN OTHER
                    *> Backout this UOW and stop
                    EXEC CICS SYNCPOINT ROLLBACK END-EXEC
                    MOVE 'MQGET failure' TO WS-MSG
                    EXIT PERFORM
              END-EVALUATE
           END-PERFORM

           *> Close queue and return
           CALL 'MQCLOSE' USING MQ-CONN-HANDLE MQ-OBJ-HANDLE-IN
                                0 MQ-COMPCODE MQ-REASON
           GOBACK.

       PROCESS-ONE-MESSAGE.
           *> Parse request body: ORDER_ID|CLIENT_ID|YYYY-MM-DD|STATUS|AMOUNT
           PERFORM VARYING WS-RESP FROM 1 BY 1 UNTIL WS-RESP > 1
              *> simple parsing using UNSTRING
              UNSTRING IN-TEXT DELIMITED BY WS-DELIM INTO
                  HV-ORDER-ID
                  HV-CLIENT-ID
                  HV-ORDER-DATE
                  HV-STATUS
                  OUT-TEXT
              END-UNSTRING
              *> convert last field to amount
              MOVE FUNCTION NUMVAL(OUT-TEXT) TO HV-AMOUNT

              *> Insert into Db2
              EXEC SQL
                INSERT INTO CLAPP.ORDERS
                  (ORDER_ID, CLIENT_ID, ORDER_DATE, STATUS, TOTAL_AMT)
                VALUES
                  (:HV-ORDER-ID, :HV-CLIENT-ID, :HV-ORDER-DATE,
                   :HV-STATUS, :HV-AMOUNT)
              END-EXEC
              IF SQLCODE NOT = 0
                 EXEC CICS SYNCPOINT ROLLBACK END-EXEC
                 MOVE 'DB2 insert failed' TO WS-MSG
                 EXIT PARAGRAPH
              END-IF

              *> Prepare reply using ReplyToQ from request MQMD
              MOVE MQMD-REPLYTOQ    TO WS-REPLYQ
              MOVE MQMD-REPLYTOQMGR TO WS-REPLYQM
              IF WS-REPLYQ NOT = SPACES
                 MOVE SPACES TO MQOD
                 MOVE WS-REPLYQ TO OD-OBJECT-NAME
                 MOVE MQOO-OUTPUT + MQOO-FAIL-IF-QUIESCING
                      TO OD-OPTIONS
                 CALL 'MQOPEN' USING MQ-CONN-HANDLE MQOD MQ-OBJ-HANDLE-OUT
                                       MQ-COMPCODE MQ-REASON
                 IF MQ-COMPCODE = MQCC-OK
                    MOVE SPACES TO MQMD
                    MOVE MQMT-REPLY        TO MQMD-MSGTYPE
                    MOVE MQMD-MSGID        TO MQMD-CORRELID
                    MOVE WS-REPLYQM        TO MQMD-REPLYTOQMGR
                    *> Build confirmation payload
                    STRING 'CONFIRMED|' DELIMITED BY SIZE
                           HV-ORDER-ID    DELIMITED BY SIZE
                           '|'            DELIMITED BY SIZE
                           'OK'           DELIMITED BY SIZE
                           INTO OUT-TEXT
                    END-STRING
                    MOVE LENGTH OF OUT-TEXT TO MQ-DATA-LEN
                    MOVE MQPER-PERSISTENT  TO MQMD-PERSISTENCE
                    MOVE MQPMO-SYNCPOINT + MQPMO-FAIL-IF-QUIESCING
                         TO PMO-OPTIONS
                    CALL 'MQPUT' USING MQ-CONN-HANDLE MQ-OBJ-HANDLE-OUT
                                         MQMD MQPMO MQ-DATA-LEN OUT-TEXT
                                         MQ-COMPCODE MQ-REASON
                    CALL 'MQCLOSE' USING MQ-CONN-HANDLE MQ-OBJ-HANDLE-OUT
                                          0 MQ-COMPCODE MQ-REASON
                 END-IF
              END-IF

              *> Commit one message worth of work
              EXEC CICS SYNCPOINT END-EXEC
           END-PERFORM.

       END PROGRAM ORQTMQ.
