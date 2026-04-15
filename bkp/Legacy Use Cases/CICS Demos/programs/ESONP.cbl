       Identification Division.
       Program-Id. ESONP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Application Sign On
      *****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
           copy ESONMAP.
       01  Application Constants.
           copy ECONST.
       01  MON-Container-Data.
           copy EMONCTR.
       01  Registered-User-Record.
           copy EREGUSR        
       01  WS-Commarea.
           05  WS-UserId               pic x(8).
           05  WS-Password             pic x(8).
       01  W-Resp                      pic 9(9) binary.
       01  W-Current-Date.
           05  W-YYYYMMDDhhmmss        pic x(14).
       01  MSG-UserId-Not-Registered.
           05  E-Not-Found-UserId      pic x(8).
           05  filler                  pic x(30)
               value ' is not registered'.
       01  MSG-Wrong-Password.
           05  filler                  pic x(8)
               value 'User Id '.
           05  E-No-Match-UserId       pic x(8).
           05  filler                  pic x(30)
               value ' passwords didn''t match'.
       01  MSG-UserId-Not-Active.
           05  filler                  pic x(8)
               value 'User Id'.
           05  E-Not-Active-UserId     pic x(8).
           05  filler                  pic x(30)
               value ' is not active'.
       01  MSG-Error-Xferring-to-Landing.
           05  filler                  pic x(22)
               value 'Error transferring to '.
           05  E-Landing-Program-Name  pic x(8).
           05  filler                  pic x(10)
               value '. EIBRESP='.
           05  E-Landing-Error-EIBRESP pic 9(8).
           05  filler                  pic x(11)
               value '; EIBRESP2='.
           05  E-Landing-Error-EIBRESP2 pic 9(8).
           05  filler                  pic x
               value '.'.
       01  MSG-Read-Error.
           05  filler                  pic x(8) 
               value 'RIDFLD: '.
           05  E-Read-Error-RIDFLD     pic x(8).
           05  filler                  pic x(10)
               value ', EIBRESP='.    
           05  E-Read-Error-EIBRESP    pic 9(8).
           05  filler                  pic x(11)
               value ', EIBRESP2='.
           05  E-Read-Error-EIBRESP2   pic 9(8).
       01  MSG-Invalid-User-Status.
           05  filler                  pic x(40)
               value 'Activity Monitor returned invalid value '.
           05  filler                  pic x(23)
               value 'for Signed-On-Status: <'.
           05  E-Invalid-User-Status-Value pic x.
           05  filler                  pic x(2)
               value '>.'                
       01  MSG-General-Error.
           05  E-General-Command       pic x(30).
           05  filler                  pic x(10)
               value ', EIBRESP='.
           05  E-General-EIBRESP       pic 9(8).
           05  filler                  pic x(11)
               value ', EIBRESP2='.
       Linkage Section.
       01  DFHCOMMAREA                 pic x(16).        
       Procedure Division.
      *****************************************************************
      * This program uses the Commarea to save data between
      * pseudoconversational invocations. This is to provide a
      * working example of using the Commarea. For new applications,
      * IBM recommends using Channels and Containers instead.
      ***************************************************************** 
           if EIBCALEN equal zero
               perform 0000-First-Time
           else
               perform 1000-Process-User-Input
           end-if
           .
       0000-First-Time.            
      *****************************************************************
      * On the initial invocation of the program, there will be no
      * saved data from the previous invocation.
      * We display the data entry screen. 
      ***************************************************************** 
           perform 0100-Initialize-Commarea
           perform 0200-Initialize-Map
           perform 9200-Send-and-Return
           .
       0100-Initialize-Commarea
      *****************************************************************
      * Clear any leftover values from the Commarea.
      ***************************************************************** 
           move spaces to W-UserId W-Password
           .
       0200-Initialize-Map
      *****************************************************************
      * Prepare the output map for initial display.
      *****************************************************************
           move low-values to ESONMO 
           .        
       1000-Process-User-Input.
      *****************************************************************
      * On subsequent invocations of the program during the
      * pseudoconversation, we use the contents of the Commarea to
      * resume interaction with the user where we left off.
      *****************************************************************
           move DFHCOMMAREA to W-Commarea
           EXEC CICS RECEIVE
               MAP(CON-Sign-On-Map-Name)
               MAPSET(CON-Sign-On-Mapset-Name)
               INTO(ESONMI)
           END-EXEC
           evaluate EIBAID
               when DFHPF3
               when DFHPF12
                   perform 2000-Cancel-Sign-On
               when DFHENTER
                   perform 3000-Process-Sign-On-Request
               when other
                   continue
           end-evaluate
           perform 9200-Send-and-Return                     
           .
       2000-Cancel-Sign-On.
      *****************************************************************
      * Clear the terminal buffer and end the transaction.
      *****************************************************************
           EXEC CICS SEND CONTROL
               ERASE
               FREEKB
           END-EXEC
           EXEC CICS RETURN
           END-EXEC
           .
       3000-Process-Sign-On-Request.
      *****************************************************************
      * Attempt to sign the user on with the user id and password
      * they provided.
      *****************************************************************
           perform 3100-Set-UserId-and-Password
           if W-UserId equal spaces
               continue
           else
               perform 3200-Check-Users-Status
           end-if        
           perform 3300-Look-Up-UserId 
           .
       3100-Set-UserId-and-Password.
      *****************************************************************
      * If they didn't enter a new user id and/or password, use the
      * value(s) from the Commarea.
      *****************************************************************
           if USERIDI equal low-values
           or USERIDI equal spaces
               continue
           else
               move USERIDI to W-UserId
           end-if
           if PASSWDI equal low-values
           or PASSWDI equal spaces
               continue
           else
               move PASSWDI to W-Password
           end-if                 
           .
       3200-Check-Users-Status.
      *****************************************************************
      * LINK to the Activity Monitor to check the user's current
      * signed-on status.
      *****************************************************************
           set MON-Action-Sign-On to true
           move space to MON-Error-Flag
           perform 3260-Call-Activity-Monitor
           perform 3280-Check-User-Activity 
           .
       3220-Notify-Activity-Monitor.
      *****************************************************************
      * Notify the Activity Monitor that this user has successfully
      * signed on.
      *****************************************************************
           set MON-Action-Notify to true
           move Registered-User-Type to MON-User-Category
           perform 3260-Call-Activity-Monitor 
           .
       3260-Call-Activity-Monitor.
      *****************************************************************
      * Common code to finish setting values in the container and to
      * LINK to the Activity Monitor.
      *****************************************************************
           move CON-Sign-On-Program-Name to MON-Linking-Program
           move W-UserId to MON-UserId
           perform 5100-Put-Container
           EXEC CICS LINK
               PROGRAM(CON-Activity-Monitor)
               CHANNEL(CON-Monitor-Channel-Name)
               TRANSID(EIBTRNID)
               RESP(W-Resp)
           END-EXEC
           if W-Resp equal DFHRESP(NORMAL)
               continue
           else
               move 'LINK to EACTMON' to E-General-Command
               perform 8100-Set-General-Error-Fields
           end-if             
           .
       3280-Check-User-Activity.
      *****************************************************************
      * Pick up the results of a LINK to the Activity Monitor and take
      * appropriate action.
      *****************************************************************
           EXEC CICS GET 
               CONTAINER(CON-Monitor-Container-Name)
               CHANNEL(CON-Monitor-Channel-Name)
               INTO(MON-Container-Data)
               RESP(W-Resp)
           END-EXEC
           if W-Resp equal DFHRESP(NORMAL)
               continue
           else
               move 'GET CONTAINER' to E-General-Command
               perform 8100-Set-General-Error-Fields
           end-if
           evaluate true
               when MON-Processing-Error
               when MON-Status-Locked-Out
                   move MON-Message to MESSO
                   perform 9200-Send-and-Return
               when MON-Status-Signed-On
                   perform 9100-Transfer-to-Landing-Page
               when MON-Status-Signing-On
               when MON-Status-Not-Set
                   continue
               when other
                   move MON-Signed-On-Status
                       to E-Invalid-User-Status-Value
                   move MSG-Invalid-User-Status to MESSO
                   perform 9200-Send-and-Return
           end-evaluate                                
           .
       3300-Look-Up-UserId.
      *****************************************************************
      * See if the user id submitted for sign-on is present in the
      * Registered Users File.
      *****************************************************************
           EXEC CICS READ
               FILE(CON-Registered-Users-Filename)
               RIDFLD(W-UserId)
               INTO(Registered-User-Record)
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
                   perform 3400-Check-User-Credentials
               when DFHRESP(NOTFND)
                   move W-UserId to E-Not-Found-UserId
                   move MSG-UserId-Not-Registered to MESSO
               when other
                   move W-UserId to E-Read-Error-RIDFLD
                   move W-Resp to E-Read-Error-EIBRESP
                   move EIBRESP2 to E-Read-Error-EIBRESP2
                   move MSG-Read-Error to MESSO
           end-evaluate                     
           .
       3400-Check-User-Credentials.
      *****************************************************************
      * Verify that the user id is active as of the present date
      *****************************************************************
           if W-Password equal Registered-Password
               if ACTIVE
                   move function CURRENT-DATE to W-Current-Date
                   if W-Current-Date not less than
                           Last-Effective-Date-Time
                       perform 3220-Notify-Activity-Monitor
                       perform 9100-Transfer-to-Landing-Page
                   else
                       move W-UserId to E-Not-Active-UserId
                       move MSG-UserId-Not-Active to MESSO
                   end-if
               else
                   move W-UserId to E-Not-Active-UserId
                   move MSG-UserId-Not-Active to MESSO
               end-if
           else
               move W-UserId to E-No-Match-UserId
               move MSG-Wrong-Password to MESSO
           end-if            
           .
       5100-Put-Container.
      *****************************************************************
      * PUT CONTAINER command performed from multiple places in
      * the program.
      *****************************************************************
           EXEC CICS PUT 
               CONTAINER(CON-Monitor-Container-Name)
               CHANNEL(CON-Monitor-Channel-Name)
               FROM(MON-Container-Data)
               FLENGTH(length of MON-Container-Data)
           END-EXEC
           if W-Resp equal DFHRESP(NORMAL)
               continue
           else
               move 'PUT CONTAINER' to E-General-Command
               perform 8100-Set-General-Error-Fields
           end-if             
           .
       8100-Set-General-Error-Fields.
      *****************************************************************
      * Common processing to prepare general error message.
      *****************************************************************
           move W-Resp to E-General-EIBRESP
           move EIBRESP2 to E-General-EIBRESP2
           move MSG-General-Error to MESSO 
           .
       9100-Transfer-to-Landing-Page.
      *****************************************************************
      * Transfer control to the initial Employee Application program.
      *****************************************************************
           perform 5100-Put-Container
           EXEC CICS XCTL
               PROGRAM(CON-Landing-Program-Name)
               CHANNEL(CON-Monitor-Channel-Name)
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
                   continue
               when other
                   move CON-Landing-Program-Name
                       to E-Landing-Program-Name
                   move W-Resp to E-Landing-Error-EIBRESP
                   move EIBRESP2 to E-Landing-Error-EIBRESP2
                   move MSG-Error-Xferring-to-Landing to MESSO
                   perform 9200-Send-and-Return
           end-evaluate                     
           .
       9200-Send-and-Return.
      *****************************************************************
      * Populate fields in the output map with current values and
      * display the screen.
      *****************************************************************
           move EIBTRNID to TRANIDO
           move W-UserId to USERIDO
           move low-values to PASSWDO
           EXEC CICS SEND
               MAP(CON-Sign-On-Map-Name)
               MAPSET(CON-Sign-On-Mapset-Name)
               FROM(ESONMO)
               ERASE
               FREEKB
           END-EXEC
           EXEC CICS RETURN
               TRANSID(EIBTRNID)
               COMMAREA(W-Commarea)
               LENGTH(length of W-Commarea)
           END-EXEC         
           .