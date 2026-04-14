       IDENTIFICATION DIVISION.
       PROGRAM-ID. SUBINP.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       EXEC SQL INCLUDE SQLCA END-EXEC.

       01  WS-TS-FROM       PIC X(26).
       01  WS-TS-TO         PIC X(26).

       01  WS-I             PIC 9(9) COMP VALUE 0.
       01  WS-AMOUNT        PIC S9(13)V99 COMP-3.
       01  WS-TS            PIC X(26).

       LINKAGE SECTION.
       01  LK-DATE-FROM     PIC X(10).
       01  LK-DATE-TO       PIC X(10).
       01  LK-COUNTRY       PIC X(2).

       COPY 'CPBKPSTA'.

       PROCEDURE DIVISION USING  LK-DATE-FROM  LK-DATE-TO
                                 LK-COUNTRY TXN-TABLE.
       MAIN-LOGIC.
           MOVE LK-DATE-FROM TO WS-TS-FROM(1:10)
           MOVE ' 00:00:00.000000' TO WS-TS-FROM(11:16)
           MOVE LK-DATE-TO   TO WS-TS-TO(1:10)
           MOVE ' 23:59:59.999999' TO WS-TS-TO(11:16)

           MOVE 0 TO TXN-COUNT

           EXEC SQL
             DECLARE C1 CURSOR FOR
               SELECT VARCHAR_FORMAT(TRANS_TS,'YYYY-MM-DD HH24:MI:SS'),
                      SERVICE_CODE, INIT_CHANNEL, SCHEME_CODE,
                      SCA_APPLIED, NON_SCA_REASON,
                      FRAUD_FLAG, COALESCE(FRAUD_ORIGIN,''),
                      AMOUNT_EUR, CURRENCY,
                      PAYER_COUNTRY, PAYEE_COUNTRY,
                      COALESCE(POS_COUNTRY,''), COALESCE(MCC,'')
               FROM PAYSTATS.PAYMENT_TRANSACTIONS
               WHERE TRANS_TS BETWEEN TIMESTAMP(:WS-TS-FROM)
                 AND TIMESTAMP(:WS-TS-TO)
                 AND (PAYER_COUNTRY = :LK-COUNTRY
                   OR PAYEE_COUNTRY = :LK-COUNTRY)
           END-EXEC

           EXEC SQL OPEN C1 END-EXEC

           PERFORM UNTIL SQLCODE NOT = 0
              EXEC SQL FETCH C1 INTO :WS-TS,
                                   :TX-SERVICE(TXN-COUNT + 1),
                                   :TX-CHANNEL(TXN-COUNT + 1),
                                   :TX-SCHEME(TXN-COUNT + 1),
                                   :TX-SCA(TXN-COUNT + 1),
                                   :TX-NON-SCA-REASON(TXN-COUNT + 1),
                                   :TX-FRAUD(TXN-COUNT + 1),
                                   :TX-FRAUD-ORIGIN(TXN-COUNT + 1),
                                   :WS-AMOUNT,
                                   :TX-CURRENCY(TXN-COUNT + 1),
                                   :TX-PAYER-COUNTRY(TXN-COUNT + 1),
                                   :TX-PAYEE-COUNTRY(TXN-COUNT + 1),
                                   :TX-POS-COUNTRY(TXN-COUNT + 1),
                                   :TX-MCC(TXN-COUNT + 1)
              END-EXEC
              IF SQLCODE = 0
                 ADD 1            TO TXN-COUNT
                 MOVE TXN-COUNT   TO WS-I
                 MOVE WS-I        TO TX-TRANS-ID(WS-I)
                 MOVE WS-TS(1:10) TO TX-TS-DATE(WS-I)
                 MOVE WS-TS(12:8) TO TX-TS-TIME(WS-I)
                 MOVE WS-AMOUNT   TO TX-AMOUNT-EUR(WS-I)
              END-IF
           END-PERFORM

           EXEC SQL CLOSE C1 END-EXEC

           GOBACK.
       END PROGRAM SUBINP.
