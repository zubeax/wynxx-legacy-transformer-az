       Identification Division.
       Program-Id. EMENUP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Menu using AID keys for selections.
      *****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
           copy DFHBMSCA.
           copy EMNUMAP.
       01  Application-Constants.
           copy ECONST.
       01  MON-Container-Data.
           copy EMONCTR.
       01  APP-Container-Data.
           copy EAPPCTR.
       01  LST-Container-Data.
           copy ELSTCTR.
       01  CICS-Response-Code                 pic s9(9) binary.
           copy EXFERWS.
           copy EMESSWS.
       01  Next-Program                       pic x(8).
       Procedure Division.    
           copy EINIT.
       0100-Initialize-App-Container.
           initialize APP-Container-Data
           perform 4400-Get-List-Container
           evaulate CICS-Response-Code
               when DFHRESP(NORMAL)
                   set APP-List-Container-Exists to true
                   move CON-Menu-Program-Name to LST-Program-Name
               when other
                   set APP-List-Container-Exists to false
           end-evaluate
           perform 4100-Put-Application-Container
           .
       0200-Populate-Initial-Screen.
           move low-values to EMNUMO
           move spaces to MSG-Out
           .
       1000-Process-User-Input.
           perform 1800-Receive-Map
           move spaces to MSG-Out
           evaluate EIBAID
               when DFHPF1
                   move CON-List-Program-Name to Next-Program
                   perform 1100-Transfer-Control
               when DFHPF2
                   move CON-View-Program-Name to Next-Program
                   perform 1100-Transfer-Control
               when DFHPF3
               when DFHPF12
                   perform 9200-Exit
               when DFHPF10
                   perform 9800-Sign-User-Off
               when other
                   move MSG-Undefined-PF-Key to MSG-Out
           end-evaluate
           perform 9100-Display-and-Return
           .
       1100-Transfer-Control.
           perform 6200-Put-Monitor-Container
           if APP-List-Container-Exists
               perform 4300-Put-List-Container
           end-if
           EXEC CICS XCTL
               PROGRAM(Next-Program)
               CHANNEL(CON-Monitor-Channel-Name)
           END-EXEC
           .
       1800-Receive-Map
           EXEC CICS RECEIVE
               MAP(CON-Menu-Map-Name)
               MAPSET(CON-Menu-Mapset-Name)
               INTO(EMNUMI)
           END-EXEC
           .
       4100-Put-Application-Container.
           move CON-App-Container-Name to XFER-Container-Name
           move CON-App-Channel-Name to XFER-Channel-Name
           move APP-Container-Data to XFER-Container-Data
           move length of APP-Container-Data 
               to XFER-Container-Data-Length
           perform 7100-Put-Container
           .
       4200-Get-Application-Container.
           move CON-App-Container-Name to XFER-Container-Name
           move CON-App-Channel-Name to XFER-Channel-Name
           perform 7200-Get-Container
           move XFER-Container-Data to APP-Container-Data
           .
       4300-Put-List-Container.
           move CON-List-Container-Name to XFER-Container-Name
           move CON-List-Channel-Name to XFER-Channel-Name
           move LST-Container-Data to XFER-Container-Data
           move length of LST-Container-Data 
               to XFER-Container-Data-Length
           perform 7100-Put-Container
           .
           copy EMONITOR.
           copy EXFERPD.
           copy EMESSPD.
       9100-Display-and-Return.
           move MSG-Out to MESSO
           move CON-Menu-TransId to TRANIDO
           move DFHBMFSE to TRANIDA
           EXEC CICS SEND
               MAP(CON-Menu-Map-Name)
               MAPSET(CON-Menu-Mapset-Name)
               FROM(EMNUMO)
               FRSET
               ERASE
               FREEKB
           END-EXEC
           perform 4100-Put-Application-Container
           EXEC CICS RETURN
               TRANSID(CON-Menu-TransId)
               CHANNEL(CON-App-Channel-Name)
           END-EXEC
           .
       9200-Exit.
           perform 9800-Sign-User-Off
           .
           copy ESIGNOFF.                                                                                             



