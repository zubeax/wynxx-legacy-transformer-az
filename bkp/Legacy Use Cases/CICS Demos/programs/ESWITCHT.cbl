       Identification Division.
       Program-Id. ESWITCHT.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Switch audit trail files for offline processing.
      * LINK to ESWITCH to simulate invocation through EXCI.
      *****************************************************************
       Data Division.
       Working-Storage Section.
       01  CICS-Response-Code                   pic 9(8).
       Procedure Division.
           EXEC CICS LINK
               PROGRAM('ESWITCH')
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .                                        
