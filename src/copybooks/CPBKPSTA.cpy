      * CPBKPSTA.cpy
       01  TXN-TABLE.
           05  TXN-COUNT              PIC 9(9) COMP VALUE 0.
           05  TXN-MAX                PIC 9(9) COMP VALUE 20000.
           05  TXN-ROWS OCCURS 1 TO 20000 DEPENDING ON TXN-COUNT
                                   INDEXED BY TX-IDX.
               10  TX-TRANS-ID            PIC 9(18).
               10  TX-TS-DATE             PIC X(10).
               10  TX-TS-TIME             PIC X(8).
               10  TX-SERVICE             PIC X(30).
               10  TX-CHANNEL             PIC X(30).
               10  TX-SCHEME              PIC X(40).
               10  TX-SCA                 PIC X(1).
               10  TX-NON-SCA-REASON      PIC X(10).
               10  TX-FRAUD               PIC X(1).
               10  TX-FRAUD-ORIGIN        PIC X(10).
               10  TX-AMOUNT-EUR          PIC 9(13)V99.
               10  TX-CURRENCY            PIC X(3).
               10  TX-PAYER-COUNTRY       PIC X(2).
               10  TX-PAYEE-COUNTRY       PIC X(2).
               10  TX-POS-COUNTRY         PIC X(2).
               10  TX-MCC                 PIC X(4).
