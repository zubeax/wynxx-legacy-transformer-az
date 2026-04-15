      *-------------------------------------------------------------*
      * Program: CLORDCIC - CICS/DB2 Client & Order Management      *
      * Language: IBM Enterprise COBOL for z/OS                    *
      * CICS TS 6.1 + Db2 13 for z/OS                              *
      *-------------------------------------------------------------*
       IDENTIFICATION DIVISION.
       PROGRAM-ID. CLORDCIC.
       ENVIRONMENT DIVISION.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  WS-COMMAREA.
           05  WS-STATE           PIC X(8)   VALUE 'MENU'.
           05  WS-MSG             PIC X(70)  VALUE SPACES.
       01  WS-RESP                PIC S9(9) COMP.
       01  WS-RESP2               PIC S9(9) COMP.

       01  FILLER                 PIC X(40)  VALUE
           '*** Host variables for Db2 ***'.
       EXEC SQL INCLUDE SQLCA END-EXEC.

      * Host vars for CLIENTS
       01  HV-CLIENT.
           05  HV-CLIENT-ID       PIC X(10).
           05  HV-NAME            PIC X(35).
           05  HV-EMAIL           PIC X(40).
           05  HV-PHONE           PIC X(20).
           05  HV-STATUS          PIC X(10).

      * Host vars for ORDERS
       01  HV-ORDER.
           05  HV-ORDER-ID        PIC X(12).
           05  HV-O-CLIENT-ID     PIC X(10).
           05  HV-ORDER-DATE      PIC X(10).
           05  HV-STATUS          PIC X(12).
           05  HV-AMOUNT          PIC S9(7)V99 COMP-3.

      * Map copybook generated from BMS assembly (CLMAPS)
       COPY CLMAPS.

       LINKAGE SECTION.
       01  DFHCOMMAREA.
           05  L-STATE            PIC X(8).
           05  L-MSG              PIC X(70).

       PROCEDURE DIVISION.
       MAIN-LOGIC.
           IF EIBCALEN > 0
              MOVE DFHCOMMAREA TO WS-COMMAREA
           END-IF

           EVALUATE TRUE
              WHEN WS-STATE = 'MENU'
                 PERFORM DO-MENU
              WHEN WS-STATE = 'CLIENT'
                 PERFORM DO-CLIENT
              WHEN WS-STATE = 'ORDER'
                 PERFORM DO-ORDER
              WHEN OTHER
                 MOVE 'MENU' TO WS-STATE
                 PERFORM DO-MENU
           END-EVALUATE
           .

      *-------------------------------------------------------------*
      * MENU SCREEN                                                 *
      *-------------------------------------------------------------*
       DO-MENU.
           MOVE SPACES TO MENUMAPI MENUMAPO
           MOVE WS-MSG TO MSGO
           EXEC CICS SEND MAP('MENUMAP') MAPSET('CLMAPS') ERASE END-EXEC
           EXEC CICS RECEIVE MAP('MENUMAP') MAPSET('CLMAPS') END-EXEC
           IF OPTL > 0
              EVALUATE OPTI(1:1)
                 WHEN '1'
                    MOVE 'CLIENT' TO WS-STATE
                 WHEN '2'
                    MOVE 'ORDER'  TO WS-STATE
                 WHEN '3'
                    MOVE 'CLIENT' TO WS-STATE
                 WHEN OTHER
                    MOVE 'Invalid option' TO WS-MSG
              END-EVALUATE
           ELSE
              MOVE 'Enter an option' TO WS-MSG
           END-IF
           PERFORM RETURN-TO-CICS
           .

      *-------------------------------------------------------------*
      * CLIENT CRUD                                                 *
      *-------------------------------------------------------------*
       DO-CLIENT.
           MOVE SPACES TO CLIMAPI CLIMAPO
           MOVE WS-MSG TO CMSGO
           EXEC CICS SEND MAP('CLIMAP') MAPSET('CLMAPS') ERASE END-EXEC
           EXEC CICS RECEIVE MAP('CLIMAP') MAPSET('CLMAPS') END-EXEC

           EVALUATE CFUNCI(1:1)
              WHEN 'I'  PERFORM INS-CLIENT
              WHEN 'U'  PERFORM UPD-CLIENT
              WHEN 'D'  PERFORM DEL-CLIENT
              WHEN 'Q'  PERFORM INQ-CLIENT
              WHEN OTHER
                 MOVE 'Use I/U/D/Q in Action' TO WS-MSG
           END-EVALUATE
           MOVE 'CLIENT' TO WS-STATE
           PERFORM RETURN-TO-CICS
           .

       INS-CLIENT.
           MOVE CIDI    TO HV-CLIENT-ID
           MOVE CNAMEI   TO HV-NAME
           MOVE CMAILI   TO HV-EMAIL
           MOVE CPHONEI  TO HV-PHONE
           MOVE CSTATUSI TO HV-STATUS
           EXEC SQL
              INSERT INTO CLAPP.CLIENTS
              (CLIENT_ID, NAME, EMAIL, PHONE, STATUS)
              VALUES (:HV-CLIENT-ID, :HV-NAME, :HV-EMAIL,
                      :HV-PHONE, :HV-STATUS)
           END-EXEC
           IF SQLCODE = 0
              MOVE 'Client inserted' TO WS-MSG
           ELSE
              MOVE 'SQL error on insert CLIENT' TO WS-MSG
           END-IF
           .

       UPD-CLIENT.
           MOVE CIDI    TO HV-CLIENT-ID
           MOVE CNAMEI   TO HV-NAME
           MOVE CMAILI   TO HV-EMAIL
           MOVE CPHONEI  TO HV-PHONE
           MOVE CSTATUSI TO HV-STATUS
           EXEC SQL
              UPDATE CLAPP.CLIENTS
                 SET NAME=:HV-NAME, EMAIL=:HV-EMAIL,
                     PHONE=:HV-PHONE, STATUS=:HV-STATUS
               WHERE CLIENT_ID=:HV-CLIENT-ID
           END-EXEC
           IF SQLCODE = 0
              MOVE 'Client updated' TO WS-MSG
           ELSE
              MOVE 'SQL error on update CLIENT' TO WS-MSG
           END-IF
           .

       DEL-CLIENT.
           MOVE CIDI TO HV-CLIENT-ID
           EXEC SQL
              DELETE FROM CLAPP.CLIENTS
               WHERE CLIENT_ID=:HV-CLIENT-ID
           END-EXEC
           IF SQLCODE = 0
              MOVE 'Client deleted' TO WS-MSG
           ELSE
              MOVE 'SQL error on delete CLIENT' TO WS-MSG
           END-IF
           .

       INQ-CLIENT.
           MOVE CIDI TO HV-CLIENT-ID
           EXEC SQL
              SELECT NAME, EMAIL, PHONE, STATUS
                INTO :HV-NAME, :HV-EMAIL, :HV-PHONE, :HV-STATUS
                FROM CLAPP.CLIENTS
               WHERE CLIENT_ID=:HV-CLIENT-ID
           END-EXEC
           IF SQLCODE = 0
              MOVE HV-NAME   TO CNAMEO
              MOVE HV-EMAIL  TO CMAILO
              MOVE HV-PHONE  TO CPHONEO
              MOVE HV-STATUS TO CSTATUSO
              MOVE 'OK' TO WS-MSG
           ELSE
              MOVE 'Client not found' TO WS-MSG
           END-IF
           .

      *-------------------------------------------------------------*
      * ORDER CRUD                                                  *
      *-------------------------------------------------------------*
       DO-ORDER.
           MOVE SPACES TO ORDMAPI ORDMAPO
           MOVE WS-MSG TO OMSGO
           EXEC CICS SEND MAP('ORDMAP') MAPSET('CLMAPS') ERASE END-EXEC
           EXEC CICS RECEIVE MAP('ORDMAP') MAPSET('CLMAPS') END-EXEC

           EVALUATE OFUNCI(1:1)
              WHEN 'I'  PERFORM INS-ORDER
              WHEN 'U'  PERFORM UPD-ORDER
              WHEN 'D'  PERFORM DEL-ORDER
              WHEN 'Q'  PERFORM INQ-ORDER
              WHEN OTHER
                 MOVE 'Use I/U/D/Q in Action' TO WS-MSG
           END-EVALUATE
           MOVE 'ORDER' TO WS-STATE
           PERFORM RETURN-TO-CICS
           .

       INS-ORDER.
           MOVE OIDI     TO HV-ORDER-ID
           MOVE OCIDI    TO HV-O-CLIENT-ID
           MOVE ODATEI   TO HV-ORDER-DATE
           MOVE OSTATUSI TO HV-STATUS
           MOVE OAMOUNTI TO HV-AMOUNT
           EXEC SQL
              INSERT INTO CLAPP.ORDERS
              (ORDER_ID, CLIENT_ID, ORDER_DATE, STATUS, TOTAL_AMT)
              VALUES(:HV-ORDER-ID,:HV-O-CLIENT-ID,:HV-ORDER-DATE,
                     :HV-STATUS,:HV-AMOUNT)
           END-EXEC
           IF SQLCODE = 0
              MOVE 'Order inserted' TO WS-MSG
           ELSE
              MOVE 'SQL error on insert ORDER' TO WS-MSG
           END-IF
           .

       UPD-ORDER.
           MOVE OIDI     TO HV-ORDER-ID
           MOVE OCIDI    TO HV-O-CLIENT-ID
           MOVE ODATEI   TO HV-ORDER-DATE
           MOVE OSTATUSI TO HV-STATUS
           MOVE OAMOUNTI TO HV-AMOUNT
           EXEC SQL
              UPDATE CLAPP.ORDERS
                 SET CLIENT_ID=:HV-O-CLIENT-ID,
                     ORDER_DATE=:HV-ORDER-DATE,
                     STATUS=:HV-STATUS,
                     TOTAL_AMT=:HV-AMOUNT
               WHERE ORDER_ID=:HV-ORDER-ID
           END-EXEC
           IF SQLCODE = 0
              MOVE 'Order updated' TO WS-MSG
           ELSE
              MOVE 'SQL error on update ORDER' TO WS-MSG
           END-IF
           .

       DEL-ORDER.
           MOVE OIDI TO HV-ORDER-ID
           EXEC SQL
              DELETE FROM CLAPP.ORDERS
               WHERE ORDER_ID=:HV-ORDER-ID
           END-EXEC
           IF SQLCODE = 0
              MOVE 'Order deleted' TO WS-MSG
           ELSE
              MOVE 'SQL error on delete ORDER' TO WS-MSG
           END-IF
           .

       INQ-ORDER.
           MOVE OIDI TO HV-ORDER-ID
           EXEC SQL
              SELECT CLIENT_ID, ORDER_DATE, STATUS, TOTAL_AMT
                INTO :HV-O-CLIENT-ID, :HV-ORDER-DATE,
                     :HV-STATUS, :HV-AMOUNT
                FROM CLAPP.ORDERS
               WHERE ORDER_ID=:HV-ORDER-ID
           END-EXEC
           IF SQLCODE = 0
              MOVE HV-O-CLIENT-ID TO OCIDO
              MOVE HV-ORDER-DATE  TO ODATEO
              MOVE HV-STATUS      TO OSTATUSO
              MOVE HV-AMOUNT      TO OAMOUNTO
              MOVE 'OK' TO WS-MSG
           ELSE
              MOVE 'Order not found' TO WS-MSG
           END-IF
           .

       RETURN-TO-CICS.
           MOVE WS-COMMAREA TO DFHCOMMAREA
           EXEC CICS RETURN TRANSID('CLOR') COMMAREA(DFHCOMMAREA)
                     LENGTH(LENGTH OF WS-COMMAREA) END-EXEC
           GOBACK.
       END PROGRAM CLORDCIC.
