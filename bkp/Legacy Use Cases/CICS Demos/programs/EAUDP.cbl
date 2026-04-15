       Identification Division.
       Program-Id. EAUDP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Write audit trail records.
      * This program is invoked via START FROM() REQID().
      *****************************************************************
       Data Division.
       Working-Storage Section.
       01  CICS-Response-Code                 pic 9(8).
       01  RBA-Field                          pic s9(8) binary.
       01  Request-Id                         pic x(8).
       01  filler.
           05  filler                         pic x value space.
               88  No-More-Data                     value 'X'.
       01  Audit-Trail-Record.
           copy EAUDREC.
       01  Application-Constants.
           copy ECONST.
       Procedure Division.
           perform until No-More-Data
               EXEC CICS RETRIEVE
                   INTO(Audit-Trail-Record)
                   RESP(CICS-Response-Code)
               END-EXEC
               evaluate CICS-Response-Code
                   when DFHRESP(NORMAL)
                       perform 1000-Write-to-Audit-File
                   when DFHRESP(ENDDATA)
                       set No-More-Data to true
                   when other 
                       EXEC CICS ABEND
                           ABCODE(CON-Audit-TransId)
                           NODUMP
                       END-EXEC
               end-evaluate
           end-perform
           EXEC CICS RETURN END-EXEC
           .
       1000-Write-to-Audit-File.
           EXEC CICS ENQ
               RESOURCE(CON-Audit-Request-Id)
               LENGTH(length of CON-Audit-Request-Id)
           END-EXEC
           EXEC CICS WRITE
               FILE(CON-Audit-File-Name)
               RIDFLD(RBA-Field)
               RBA
               FROM(Audit-Trail-Record)
               LENGTH(length of Audit-Trail-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           EXEC CICS DEQ
               RESOURCE(CON-Audit-Request-Id)
               LENGTH(length of CON-Audit-Request-Id)
           END-EXEC
           .                                                            