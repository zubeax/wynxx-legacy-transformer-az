       Identification Division.
       Program-Id. EAUDP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Test driver for EAUDP.
      *****************************************************************
       Data Division.
       Working-Storage Section.
       01  CICS-Response-Code                 pic 9(8).
       01  Task-Number-X.
           05  Task-Number                    pic 9(7).
       01  Audit-Trail-Record.
           copy EAUDREC.
       01  Application-Constants.
           copy ECONST.
       Procedure Division.
           move 'TEST ' to Audit-Trail-Record
           move EIBTASKN to Task-Number
           move Task-Number-X to Audit-Trail-Record(6:)
           EXEC CICS START
               TRANSID(CON-Audit-TransId)
               TERMID(EIBTRMID)
               FROM(Audit-Trail-Record)
               REQID(CON-Audit-Request-Id)
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .    