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

       01  WS-NULL-INDICATORS.
           05  WS-NI-SCHEME           PIC S9(4) COMP.
           05  WS-NI-NON-SCA-REASON   PIC S9(4) COMP.
           05  WS-NI-FRAUD-ORIGIN     PIC S9(4) COMP.
           05  WS-NI-POS-COUNTRY      PIC S9(4) COMP.
           05  WS-NI-MCC              PIC S9(4) COMP.

       01  WS-AUX-HOST-VARIABLES.
           05  AUX-SERVICE            PIC X(30).
           05  AUX-CHANNEL            PIC X(30).
           05  AUX-SCHEME             PIC X(40).
           05  AUX-SCA                PIC X(1).
           05  AUX-NON-SCA-REASON     PIC X(10).
           05  AUX-FRAUD              PIC X(1).
           05  AUX-FRAUD-ORIGIN       PIC X(10).
           05  AUX-CURRENCY           PIC X(3).
           05  AUX-PAYER-COUNTRY      PIC X(2).
           05  AUX-PAYEE-COUNTRY      PIC X(2).
           05  AUX-POS-COUNTRY        PIC X(2).
           05  AUX-MCC                PIC X(4).

       LINKAGE SECTION.
       01  LK-DATE-FROM     PIC X(10).
       01  LK-DATE-TO       PIC X(10).
       01  LK-COUNTRY       PIC X(2).

       COPY 'CPBKPSTA'.

       PROCEDURE DIVISION USING  LK-DATE-FROM
                                 LK-DATE-TO
                                 LK-COUNTRY
                                 TXN-TABLE.
       MAIN-LOGIC.

           EXEC SQL INCLUDE PKGSETXX   END-EXEC.

           MOVE LK-DATE-FROM TO WS-TS-FROM(1:10)
           MOVE ' 00:00:00.000000' TO WS-TS-FROM(11:16)
           MOVE LK-DATE-TO   TO WS-TS-TO(1:10)
           MOVE ' 23:59:59.999999' TO WS-TS-TO(11:16)

           MOVE 0 TO TXN-COUNT

           EXEC SQL
             DECLARE C1 CURSOR FOR
               SELECT   VARCHAR_FORMAT(TRANS_TS,'YYYY-MM-DD HH24:MI:SS')
                      , SERVICE_CODE
                      , INIT_CHANNEL
                      , SCHEME_CODE
                      , SCA_APPLIED
                      , NON_SCA_REASON
                      , FRAUD_FLAG
                      , COALESCE(FRAUD_ORIGIN,'')
                      , AMOUNT_EUR
                      , CURRENCY
                      , PAYER_COUNTRY
                      , PAYEE_COUNTRY
                      , COALESCE(POS_COUNTRY,'')
                      , COALESCE(MCC,'')
               FROM PAYMENT_TRANSACTIONS
               WHERE TRANS_TS BETWEEN TIMESTAMP(:WS-TS-FROM)
                 AND TIMESTAMP(:WS-TS-TO)
                 AND (PAYER_COUNTRY = :LK-COUNTRY
                   OR PAYEE_COUNTRY = :LK-COUNTRY)
           END-EXEC

           EXEC SQL OPEN C1 END-EXEC

           SET TX-IDX TO 1

           PERFORM UNTIL SQLCODE NOT = 0
              EXEC SQL FETCH C1 INTO
                    :WS-TS
                   ,:AUX-SERVICE
                   ,:AUX-CHANNEL
                   ,:AUX-SCHEME:WS-NI-SCHEME
                   ,:AUX-SCA
                   ,:AUX-NON-SCA-REASON:WS-NI-NON-SCA-REASON
                   ,:AUX-FRAUD
                   ,:AUX-FRAUD-ORIGIN:WS-NI-FRAUD-ORIGIN
                   ,:WS-AMOUNT
                   ,:AUX-CURRENCY
                   ,:AUX-PAYER-COUNTRY
                   ,:AUX-PAYEE-COUNTRY
                   ,:AUX-POS-COUNTRY:WS-NI-POS-COUNTRY
                   ,:AUX-MCC:WS-NI-MCC
              END-EXEC
              IF SQLCODE = 0
              THEN
                 ADD 1 TO TXN-COUNT

                 MOVE AUX-SERVICE        TO TX-SERVICE(TX-IDX)
                 MOVE AUX-CHANNEL        TO TX-CHANNEL(TX-IDX)
                 MOVE AUX-SCHEME         TO TX-SCHEME(TX-IDX)
                 MOVE AUX-SCA            TO TX-SCA(TX-IDX)
                 MOVE AUX-NON-SCA-REASON TO TX-NON-SCA-REASON(TX-IDX)
                 MOVE AUX-FRAUD          TO TX-FRAUD(TX-IDX)
                 MOVE AUX-FRAUD-ORIGIN   TO TX-FRAUD-ORIGIN(TX-IDX)
                 MOVE AUX-CURRENCY       TO TX-CURRENCY(TX-IDX)
                 MOVE AUX-PAYER-COUNTRY  TO TX-PAYER-COUNTRY(TX-IDX)
                 MOVE AUX-PAYEE-COUNTRY  TO TX-PAYEE-COUNTRY(TX-IDX)
                 MOVE AUX-POS-COUNTRY    TO TX-POS-COUNTRY(TX-IDX)
                 MOVE AUX-MCC            TO TX-MCC(TX-IDX)

                 MOVE TXN-COUNT TO WS-I
                 MOVE WS-I               TO TX-TRANS-ID(TX-IDX)
                 MOVE WS-TS(1:10)        TO TX-TS-DATE(TX-IDX)
                 MOVE WS-TS(12:8)        TO TX-TS-TIME(TX-IDX)
                 MOVE WS-AMOUNT          TO TX-AMOUNT-EUR(TX-IDX)

                 SET TX-IDX UP BY 1
              END-IF
           END-PERFORM

           EXEC SQL CLOSE C1 END-EXEC

           GOBACK.
       END PROGRAM SUBINP.
