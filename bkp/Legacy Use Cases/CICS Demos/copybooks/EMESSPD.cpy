       8100-Container-Error.
      *****************************************************************
      * Display response codes after undexpected condition when	 
      * getting a container.
      *****************************************************************
           move XFER-Channel-Name to ERR-Channel-Name
           move XFER-Container-Name to Err-Container-Name
           move EIBRESP to ERR-Container-EIBRESP
           move EIBRESP2 to ERR-Container-EIBRESP2
           move MGS-Container-Error to MSG-Out
           perform 9100-Display-and-Return
           .
       8200-File-Error.
      *****************************************************************
      * Display response codes after an unexpected condition when 
      * performing a File Control operation.
      *****************************************************************
           move File-Name-to-Read to ERR-File-Name
           move EIBRESP to ERR-EIBRESP 
           move EIBRESP2 to ERR-EIBRESP2 
           move MSG-File-Error to MSG-Out
           set Highlight-Error to true
           .