       7100-Put-Container.
      *****************************************************************
      * Copy working storage data to a container.
      *****************************************************************
           EXEC CICS PUT 
               CONTAINER(XFER-Container-Name)
               CHANNEL(XFER-Channel-Name)
               FROM(XFER-Container-Data)
               FLENGTH(XFER-Container-Data-Length)
               RESP(CICS-Response-Code)
           END-EXEC
           if CICS-Response-Code equal DFHRESP(NORMAL)
               continue
           else
               perform 8100-Container-Error
           end-if
           .
       7200-Get-Container.
      *****************************************************************
      * Copy data from a container to working storage.
      *****************************************************************
           EXEC CICS GET 
               CONTAINER(XFER-Container-Name)
               CHANNEL(XFER-Channel-Name)
               INTO(XFER-Container-Data)
               RESP(CICS-Response-Code)
           END-EXEC
           .