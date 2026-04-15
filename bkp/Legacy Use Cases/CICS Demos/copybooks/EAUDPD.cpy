       7900-Write-Audit-Trail.
      *****************************************************************
      * Start the transaction responsible for writing to the audit
      * trail file.
      *****************************************************************
           EXEC CICS START 
               TRANSID(CON-Audit-TransId)
               TERMID(EIBTRMID)
               FROM(Audit-Trail-Record)
               REQID(CON-Audit-Request-Id)
           END-EXEC
           .   