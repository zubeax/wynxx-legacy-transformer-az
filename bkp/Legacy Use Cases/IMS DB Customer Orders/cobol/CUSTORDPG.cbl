IDENTIFICATION DIVISION.
       PROGRAM-ID. CUSTORDPG.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  CUSTOMER-SEG.
           05 CUSTID     PIC X(10).
           05 CUSTNAME   PIC X(40).
           05 ADDRESS    PIC X(50).
       01  ORDER-SEG.
           05 ORDID      PIC X(10).
           05 ORDDATE    PIC X(8).
           05 AMOUNT     PIC S9(7)V99 COMP-3.
           05 STATUS     PIC X(10).
       LINKAGE SECTION.
       01  IO-PCB  PIC X(24).
       01  DB-PCB.
           05 FILLER PIC X(8).
           05 DB-STATUS PIC X(2).
           05 FILLER PIC X(8).
           05 SEG-LEVEL PIC S9(4) COMP.
           05 KEY-LEN   PIC S9(4) COMP.
           05 SEG-NAME  PIC X(8).
           05 KEY-FB    PIC X(200).
       PROCEDURE DIVISION USING IO-PCB DB-PCB.
       MAIN.
           DISPLAY 'CUSTORDPG START'.
           GOBACK.
