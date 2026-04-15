       6100-Check-User-Activity.
      *****************************************************************
      * Get the user category from the Activity Monitor.
      *****************************************************************
            move CON-Monitor-Channel-Name to XFER-Channel-Name
            move CON-Monitor-Container-Name to XFER-Container-Name
            perform 7200-Get-Container
            evaluate CICS-Response-Code
                when DFHRESP(NORMAL)
                    move XFER-Container-Data to MON-Container-Data
                    if MON-Terminate-Now
                        perform 9900-End-Transaction
                    end-if
                when DFHRESP(CHANNELERR)
                when DFHRESP(CONTAINERERR)
                    initialize MON-Container-Data
                    move CON-Invalid-UserId to MON-UserId
                when other
                    continue 
            end-evaluate
            move CON-View-Program-Name to MON-Linking-Program
            set MON-Action-App-Function to true
            perform 6200-Put-Monitor-Container
            perform 6300-Call-Activity-Monitor
            perform 6400-Get-Monitor-Container
            if MON-Terminate-Now
                perform 9900-End-Transaction
            end-if
            move MON-User-Category to LST-User-Category
            .
        6200-Put-Monitor-Container. 
      *****************************************************************
      * Copy working storage data to the Activity Monitor container.
      *****************************************************************
           move CON-Monitor-Container-Name to XFER-Container-Name
           move CON-Monitor-Channel-Name to XFER-Channel-Name
           move MON-Container-Data to XFER-Container-Data
           move length of MON-Container-Data 
               to XFER-Container-Data-Length
           perform 7100-Put-Container
           .
       6300-Call-Activity-Monitor.    
      *****************************************************************
      * Link to the Activity Monitor.
      *****************************************************************
           EXEC CICS LINK 
               PROGRAM(CON-Activity-Monitor)
               CHANNEL(CON-Monitor-Channel-Name)
               TRANSID(EIBTRNID)
           END-EXEC
           .
       6400-Get-Monitor-Container.
      *****************************************************************
      * Copy Activity Monitor container data to working storage.
      *****************************************************************
           move CON-Monitor-Container-Name to XFER-Container-Name
           move CON-Monitor-Channel-Name to XFER-Channel-Name
           perform 7200-Get-Container
           move XFER-Container-Data to MON-Container-Data
           .
