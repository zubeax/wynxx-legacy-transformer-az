       IDENTIFICATION DIVISION.
       PROGRAM-ID. SUBFMT.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  WS-XML-DOC.
           05  HEADER.
               10  COUNTRY        PIC X(2).
               10  REF-YEAR       PIC 9(4).
               10  REF-HALF       PIC X(2).
               10  REF-QUARTER    PIC X(2).
           05  AGGREGATES.
               10  ITEM OCCURS 1 TO 2000 DEPENDING ON AG-CNT
                              INDEXED BY A-IDX.
                   15 PAYMENT-SERVICE     PIC X(30).
                   15 INITIATION-CHANNEL  PIC X(30).
                   15 SCA                 PIC X(1).
                   15 TRANSACTION-COUNT   PIC 9(9).
                   15 TOTAL-VALUE-EUR     PIC 9(13)V99.
       01  AG-CNT            PIC 9(9) COMP VALUE 0.

       LINKAGE SECTION.
       01  LK-COUNTRY        PIC X(2).
       01  LK-REF-YEAR       PIC 9(4).
       01  LK-REF-HALF       PIC X(2).
       01  LK-REF-QUARTER    PIC X(2).
       01  LK-AGG-TABLE.
           05  LK-AGG-NUM    PIC 9(9) COMP.
           05  LK-AGG-MAX    PIC 9(9) COMP.
           05  LK-AGG-ROWS OCCURS 1 TO 2000 DEPENDING ON LK-AGG-NUM
               INDEXED BY LK-AG-IDX.
               10 LK-AE-SERVICE PIC X(30).
               10 LK-AE-CHANNEL PIC X(30).
               10 LK-AE-SCA     PIC X(1).
               10 LK-AE-COUNT   PIC 9(9) COMP.
               10 LK-AE-TOTAL   PIC 9(13)V99.
       01  LK-XML-BUFFER     PIC X(32756).

       PROCEDURE DIVISION USING LK-COUNTRY LK-REF-YEAR LK-REF-HALF
         LK-REF-QUARTER LK-AGG-TABLE LK-XML-BUFFER.
           MOVE LK-COUNTRY     TO COUNTRY.
           MOVE LK-REF-YEAR    TO REF-YEAR.
           MOVE LK-REF-HALF    TO REF-HALF.
           MOVE LK-REF-QUARTER TO REF-QUARTER.

           MOVE LK-AGG-NUM TO AG-CNT.
           PERFORM VARYING A-IDX FROM 1 BY 1 UNTIL A-IDX > AG-CNT
              SET LK-AG-IDX TO A-IDX
              MOVE LK-AE-SERVICE TO PAYMENT-SERVICE(A-IDX)
              MOVE LK-AE-CHANNEL TO INITIATION-CHANNEL(A-IDX)
              MOVE LK-AE-SCA     TO SCA(A-IDX)
              MOVE LK-AE-COUNT   TO TRANSACTION-COUNT(A-IDX)
              MOVE LK-AE-TOTAL   TO TOTAL-VALUE-EUR(A-IDX)
           END-PERFORM

           XML GENERATE LK-XML-BUFFER FROM WS-XML-DOC
                COUNT IN AG-CNT
                WITH ENCODING 'UTF-8'
                SUPPRESS WHEN ZEROS
                ON EXCEPTION CONTINUE
           END-XML.

           GOBACK.
       END PROGRAM SUBFMT.
