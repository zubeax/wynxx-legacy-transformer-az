       9800-Sign-User-Off.
      *****************************************************************
      * Call the Activity Monitor to sign the user off and clean up.
      *****************************************************************
           set MON-Action-Sign-Off to true
           perform 6200-Put-Monitor-Container
           perform 6300-Call-Activity-Monitor
           perform 9900-End-Transaction
           .
       9900-End-Transaction.
           EXEC CICS SEND CONTROL
               ERASE FREEKB
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .        