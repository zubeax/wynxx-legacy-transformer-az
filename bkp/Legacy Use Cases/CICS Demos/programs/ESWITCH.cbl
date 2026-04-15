       Identification Division.
       Program-Id. ESWITCH.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Switch audit trail files for offline processing.
      * This program is invoked by EXEC CICS LINK via EXCI from batch.
      *****************************************************************
       Data Division.
       Working-Storage Section.
       01  CICS-Response-Code                   pic 9(8).
       01  Audit-Trail-DSName                   pic x(44).
       01  Tally-Field                          pic s9(4) binary.
       01  Application Constants.
           copy ECONST.
       Procedure Division.
           EXEC CICS INQUIRE
               FILE(CON-Audit-File-Name)
               DSNAME(Audit-Trail-DSName)
               RESP(CICS-Response-Code)
           END-EXEC
           move zero to Tally-Field
           inspect Audit-Trail-DSName
               tallying Tally-Field
               for characters before initial space 
           if Audit-Trail-DSName(Tally-Field:) equal '1'
               move '2' to Audit-Trail-DSName(Tally-Field:)
           else
               move '1' to Audit-Trail-DSName(Tally-Field:)
           end-if
           EXEC CICS ENQ
               RESOURCE(CON-Audit-Request-Id)
               LENGTH(length of CON-Audit-Request-Id)
           END-EXEC
           EXEC CICS SET
               FILE(CON-Audit-File-Name)
               OPENSTATUS(DFHVALUE(CLOSED))
           END-EXEC
           EXEC CICS SET
               FILE(CON-Audit-File-Name)
               DSNAME(Audit-Trail-DSName)
           END-EXEC
           EXEC CICS SET
               FILE(CON-Audit-File-Name)
               OPENSTATUS(DFHVALUE(OPEN))
               EMPTYSTATUS(DFHVALUE(EMPTYREQ))
           END-EXEC
           EXEC CICS DEQ
               RESOURCE(CON-Audit-Request-Id)
               LENGTH(length of CON-Audit-Request-Id)
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .                                        
