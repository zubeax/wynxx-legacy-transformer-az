       IDENTIFICATION DIVISION.
       PROGRAM-ID. SUBAGG.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  WS-I                 PIC 9(9) COMP.
       01  WS-J                 PIC 9(9) COMP.
       01  FOUND-FLAG           PIC X VALUE 'N'.

       LINKAGE SECTION.

       COPY 'CPBKPSTA'.

       01  LK-AGG-TABLE.
           05  LK-AGG-NUM       PIC 9(9) COMP.
           05  LK-AGG-MAX       PIC 9(9) COMP.
           05  LK-AGG-ROWS OCCURS 1 TO 2000 DEPENDING ON LK-AGG-NUM
                                 INDEXED BY LK-AG-IDX.
               10  LK-AE-SERVICE PIC X(30).
               10  LK-AE-CHANNEL PIC X(30).
               10  LK-AE-SCA     PIC X(1).
               10  LK-AE-COUNT   PIC 9(9) COMP.
               10  LK-AE-TOTAL   PIC 9(13)V99.

       PROCEDURE DIVISION USING TXN-TABLE LK-AGG-TABLE.
           MOVE 0 TO LK-AGG-NUM.
           IF TXN-COUNT = 0
              GOBACK
           END-IF.

           PERFORM VARYING WS-I FROM 1 BY 1 UNTIL WS-I > TXN-COUNT
              MOVE 'N' TO FOUND-FLAG
              PERFORM VARYING WS-J FROM 1 BY 1 
                      UNTIL WS-J > LK-AGG-NUM OR FOUND-FLAG = 'Y'
                 SET LK-AG-IDX TO WS-J
                 IF TX-SERVICE(WS-I) = LK-AE-SERVICE AND
                    TX-CHANNEL(WS-I) = LK-AE-CHANNEL AND
                    TX-SCA(WS-I)     = LK-AE-SCA
                    ADD 1 TO LK-AE-COUNT
                    ADD TX-AMOUNT-EUR(WS-I) TO LK-AE-TOTAL
                    MOVE 'Y' TO FOUND-FLAG
                 END-IF
              END-PERFORM
              IF FOUND-FLAG = 'N'
                 ADD 1                 TO LK-AGG-NUM
                 SET LK-AG-IDX         TO LK-AGG-NUM
                 MOVE TX-SERVICE(WS-I) TO LK-AE-SERVICE
                 MOVE TX-CHANNEL(WS-I) TO LK-AE-CHANNEL
                 MOVE TX-SCA(WS-I)     TO LK-AE-SCA
                 MOVE 1                TO LK-AE-COUNT
                 MOVE TX-AMOUNT-EUR(WS-I) TO LK-AE-TOTAL
              END-IF
           END-PERFORM.

           GOBACK.
       END PROGRAM SUBAGG.
