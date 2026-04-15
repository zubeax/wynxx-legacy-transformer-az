           perform 4200-Get-Application-Container
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   perform 1000-Process-User-Input
               when DFHRESP(CHANNELERR)
               when DFHRESP(CONTAINERERR)
                   perform 0000-First-Time
               when other
                   perform 8100-Container-Error
           end-evaluate
           .
      *****************************************************************
      * First entry into this program in a conversation.
      *****************************************************************
           perform 6100-Check-User-Activity
           perform 0100-Initialize-App-Container
           perform 0200-Populate-Initial-Screen
           perform 9100-Display-and-Return
           .                           