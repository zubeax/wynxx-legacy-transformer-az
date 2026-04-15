       IDENTIFICATION DIVISION.
       PROGRAM-ID.  ORDENTR.
       AUTHOR.      SAMPLE-GENERATED.
       ENVIRONMENT DIVISION.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
      * -------------------------------------------------------------
      * Simple IMS TM (MPP) using EXEC DLI to create a new ORDER
      * for an existing CUSTOMER.
      *
      * Assumptions:
      *  - MFS generates copybooks ORDENTRMI (input) and ORDENTRMO (output)
      *  - PSB provides three PCBs in this order: IOPCB, CUSTDB PCB, ORDRDB PCB   
      *  - DBDs CUSTDB and ORDRDB exist with root segments CUSTROOT and ORDERSEG
      *  - ORDER-ID is synthesized (timestamp-based) here for demo purposes
      * -------------------------------------------------------------
       01  WS-TIMESTAMP                 PIC X(20).
       01  WS-ORDER-ID                  PIC X(14).
       01  WS-MSG                       PIC X(60).

      * Input and Output areas aligned with MFS copybooks
       01  ORDENTR-IN.
           COPY ORDENTRMI.

       01  ORDENTR-OUT.
           COPY ORDENTRMO.

      * Customer and Order segments (database I/O areas)
       01  CUST-SEG.
           05  CUST-ID                  PIC X(10).
           05  CUST-NAME                PIC X(30).
           05  CUST-ADDR                PIC X(40).

       01  ORDER-SEG.
           05  ORD-ORDER-ID             PIC X(14).
           05  ORD-CUST-ID              PIC X(10).
           05  ORD-ITEM-ID              PIC X(10).
           05  ORD-QUANTITY             PIC 9(5).
           05  ORD-ORDER-DATE           PIC X(8).

      * Simple SSAs (unqualified; sized generously for demo)
       01  SSA-CUST                     PIC X(64).
       01  SSA-ORDR                     PIC X(64).

      * Convenience constants
       01  SPACES-CONST                 PIC X VALUE ' '.

       LINKAGE SECTION.
      * PCB masks (site formats vary; keep as opaque here)
       01  IOPCB                        PIC X(256).
       01  CUSTPCB                      PIC X(256).
       01  ORDRPCB                      PIC X(256).

       PROCEDURE DIVISION USING IOPCB CUSTPCB ORDRPCB.
       MAIN-LOGIC.
           PERFORM RECEIVE-SCREEN
           IF MI-CUST-ID = SPACES
              PERFORM SEND-ERROR-MESSAGE
              GOBACK
           END-IF

           PERFORM FIND-CUSTOMER
           IF CUST-NOT-FOUND
              PERFORM SEND-NOT-FOUND
              GOBACK
           END-IF

           PERFORM BUILD-ORDER
           PERFORM INSERT-ORDER
           IF ORDER-INSERT-FAILED
              PERFORM SEND-FAILURE
           ELSE
              PERFORM SEND-SUCCESS
           END-IF

           GOBACK.

      * -------------------------------------------------------------
      * Section: Receive input message via I/O PCB
      * -------------------------------------------------------------
       RECEIVE-SCREEN.
           EXEC DLI
                GU IOPCB
                INTO(ORDENTR-IN)
           END-EXEC.

      * -------------------------------------------------------------
      * Section: Build and issue GU to CUSTOMER database
      * -------------------------------------------------------------
       FIND-CUSTOMER.
           MOVE 'CUSTROOT(CUST-ID='     TO SSA-CUST(1:18).
           MOVE MI-CUST-ID               TO SSA-CUST(19:10).
           MOVE ')'                      TO SSA-CUST(29:1).
           EXEC DLI
                GU CUSTPCB
                USING(SSA-CUST)
                INTO(CUST-SEG)
           END-EXEC.
           IF CUST-SEG:CUST-ID NOT = MI-CUST-ID
              SET CUST-NOT-FOUND TO TRUE
           ELSE
              SET CUST-NOT-FOUND TO FALSE
           END-IF.

       01  CUST-NOT-FOUND               PIC X VALUE 'N'.
           88 CUST-NOT-FOUND            VALUE 'Y'.
           88 CUST-FOUND                VALUE 'N'.

      * -------------------------------------------------------------
      * Section: Build ORDER segment and INSERT
      * -------------------------------------------------------------
       BUILD-ORDER.
           PERFORM GET-TIMESTAMP.
           MOVE WS-ORDER-ID              TO ORD-ORDER-ID.
           MOVE MI-CUST-ID               TO ORD-CUST-ID.
           MOVE MI-ITEM-ID               TO ORD-ITEM-ID.
           MOVE MI-QUANTITY              TO ORD-QUANTITY.
           MOVE MI-ORDER-DATE            TO ORD-ORDER-DATE.

       INSERT-ORDER.
           EXEC DLI
                ISRT ORDRPCB
                INTO(ORDER-SEG)
           END-EXEC.
           MOVE 'N' TO ORDER-INSERT-FAILED.

       01  ORDER-INSERT-FAILED          PIC X VALUE 'N'.
           88 ORDER-INSERT-FAILED       VALUE 'Y'.
           88 ORDER-INSERT-OK           VALUE 'N'.

      * -------------------------------------------------------------
      * Section: Respond to terminal via I/O PCB
      * -------------------------------------------------------------
       SEND-NOT-FOUND.
           MOVE 'Customer not found'     TO MO-MESSAGE.
           MOVE SPACES                   TO MO-ORDER-ID.
           EXEC DLI
                ISRT IOPCB
                FROM(ORDENTR-OUT)
           END-EXEC.

       SEND-FAILURE.
           MOVE 'Order insert failed'    TO MO-MESSAGE.
           MOVE SPACES                   TO MO-ORDER-ID.
           EXEC DLI
                ISRT IOPCB
                FROM(ORDENTR-OUT)
           END-EXEC.

       SEND-SUCCESS.
           MOVE 'Order created: '        TO MO-MESSAGE(1:15).
           MOVE WS-ORDER-ID              TO MO-ORDER-ID.
           EXEC DLI
                ISRT IOPCB
                FROM(ORDENTR-OUT)
           END-EXEC.

       SEND-ERROR-MESSAGE.
           MOVE 'Customer ID is required' TO MO-MESSAGE.
           MOVE SPACES                    TO MO-ORDER-ID.
           EXEC DLI
                ISRT IOPCB
                FROM(ORDENTR-OUT)
           END-EXEC.

      * -------------------------------------------------------------
      * Utility: Create a pseudo-unique ORDER ID from date/time
      * -------------------------------------------------------------
       GET-TIMESTAMP.
           ACCEPT WS-TIMESTAMP FROM TIME.
           * WS-TIMESTAMP format is site-dependent; as a demo, just map
           * numeric characters into a 14-byte order-id placeholder.
           MOVE WS-TIMESTAMP(1:14)       TO WS-ORDER-ID.

       END PROGRAM ORDENTR.
