       Identification Division.
       Program-Id. EACTMON.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Check user's current sign-on status and enforce sign-on rules,
      * i.e. automatic sign-off after inactivity, lockout after max
      * allowed sign-on attempts.
      *****************************************************************
       Data Division.
       Working-Storage Section.
       01  Application-Constants.
           copy ECONST.
       01  MON-Container-Data.
           copy EMONCTR.
       01  User-Activity-Queue-Data.
           copy EUACTTS.
       01  Sign-On-Rules-Record.
           copy ESONRUL.
       01  W-User-Activity-QName.
           05  W-User-Activity-QName-Prefix   pic x(8).
           05  W-User-Activity-Queue-UserId   pic x(8).
       01  W-Item-Number                      pic s9(4) binary.
       01  W-Resp                             pic s9(9) binary.
       01  W-New-Activity-Time                pic x(14).
       01  W-Time-Comparison-Fields.
           05  filler                         pic x.
               88  Interval-Expired           value 'Y'.
               88  Interval-Not-Expired       value 'N'.
           05  W-Current-Minutes              pic s9(5) packed-decimal.
           05  W-Starting-Minutes             pic s9(5) packed-decimal.
           05  W-Defined-Interval             pic s9(3) packed-decimal.
           05  W-Computed-Interval            pic s9(3) packed-decimal.
           05  W-Current-Activity-Time.
               10  W-Current-YYYYMMDD         pic x(8).
               10  W-Current-Hour             pic 9(2).
               10  W-Current-Minute           pic 9(2).
               10  W-Current-Second           pic 9(2).
           05  W-Starting-Activity-Time.
               10  W-Starting-YYYYMMDD        pic x(8).
               10  W-Starting-Hour            pic 9(2).
               10  W-Starting-Minute          pic 9(2).
               10  W-Starting-Second          pic 9(2).
       01  MSG-User-Locked-Out.
           05  filler                         pic x(79)
               value 'User is locked out'.
       01  MSG-Read-Error.
           05  filler                         pic x(42)
               value 'Error reading Sign-on Rules file (ESONRUL)'.
           05  filler                         pic x(10)
               value ', EIBRESP='.
           05  MSG-Read-Error-EIBRESP         pic 9(8).
           05  filler                         pic x(11)
               value ', EIBRESP2='.
           05  MSG-Read-Error-EIBRESP2        pic 9(8).
       01  MSG-Queue-Error.
           05  filler                         pic x(9)
               value 'Error on '.
           05  MSG-Queue-Error-Command        pic x(10).
           05  filler                         pic x(5)
               value ' for '.
           05  MSG-Queue-Error-QName          pic x(16).
           05  filler                         pic x(6)
               value ' RESP='.
           05  MSG-Queue-Error-EIBRESP        pic 9(8).
           05  filler                         pic x(8)
               value ', RESP2='.
           05  MSG-Queue-Error-EIBRESP2       pic 9(8).
       01  MSG-Missing-Queue.
           05  MSG-Missing-Queue-Program      pic x(8).
           05  filler                         pic x(33)
               value ' linked to EACTMON when there was'.
           05  filler                         pic x(26)
               value ' no User Activity TS queue'.
       01  MSG-Undefined-Status.
           05  MSG-Undefined-Status-Program   pic x(8).
           05  filler                         pic x(38)
               value ' linked to EACTMON with user status <'.
           05  MSG-Undefined-Status-Value     pic x.
           05  filler                         pic x(25)
               value '>. Don''t know what to do.'.
       Procedure Division.
      *****************************************************************
      * Paragraph numbering scheme
      * 0000-0999 Initialization and top-level processing flow
      * 1000-1999 User was locked out as of their last interaction
      * 2000-2999 User was signed on as of their last interaction
      * 3000-3999 User was in the process of signing on as of their
      *           last interaction
      * 7000-7999 Helpers and logic common to multiple user states
      * 8000-8999 Error-handling code
      * 9000-9999 Exiting or returning (RETURN, START)
      *****************************************************************
           perform 0100-Get-Data-from-Caller
           perform 0000-Process-Request
           perform 9100-Return-Data-to-Caller
           .
       0000-Process-Request.
      *****************************************************************
      * Main processing flow
      *****************************************************************
           move space to MON-Error-Flag
           if MON-UserId equal CON-Invalid-UserId
               set MON-Terminate-Now to true
               perform 9800-Sign-User-Off
               perform 9900-Return-to-Caller
           end-if
           move FUNCTION CURRENT-DATE to W-New-Activity-Time
           perform 0200-Get-Sign-On-Rules
           perform 0300-Get-User-Activity-Queue    
           if MON-Action-Sign-Off
               perform 9800-Sign-User-Off
               perform 9900-Return-to-Caller
           end-if
           if MON-Action-Notify
               perform 3300-Set-Signed-On-Status
           end-if
           evaluate true
               when UACT-Locked-Out
                   perform 1000-Locked-Out
               when UACT-Signed-On
                   perform 2000-Signed-On
               when UACT-Signing-On
                   perform 3000-Signing-On
               when other
                   if MON-Linking-Program equal
                           CON-Sign-On-Program-Name
                       set UACT-Signing-On to true
                       perform 3000-Signing-On                        
                   else
                       perform 8200-Undefined-Status
                   end-if
           end-evaluate
           .            
       0100-Get-Data-from-Caller.
      *****************************************************************
      * Pick up the data from the container we expect to receive from
      * the caller. If this doesn't work, allow CICS to abend the task.
      *****************************************************************
           EXEC CICS GET CONTAINER(CON-Monitor-Container-Name)
               CHANNEL(CON-Monitor-Channel-Name)
               INTO(MON-Container-Data)
               FLENGTH(length of MON-Container-Data)
           END-EXEC
           initialize MON-Response
           .
       0200-Get-Sign-On-Rules.        
      *****************************************************************
      * Get the sign-on rules from the TS queue or the file.
      * Try the TS queue first. If it doesn't exist, read the file
      * and create the queue.
      *****************************************************************
           move CON-ESONRUL-Item-Number
               to W-Item-Number
           EXEC CICS READQ TS
               QNAME(CON-ESONRUL-QName)
               ITEM(W-Item-Number)
               INTO(Sign-On-Rules-Record)
               RESP(W-Resp)
           END-EXEC        
           evaluate W-Resp
               when DFHRESP(NORMAL)
                   continue
               when DFHRESP(QIDERR)
                   perform 0220-Load-Rules-from-File
               when other
                   move 'READQ TS' to MSG-Queue-Error-Command
                   move CON-ESONRUL-QName
                       to MSG-Queue-Error-QName
                   perform 8100-Queue-Error
           end-evaluate                    
           .
       0220-Load-Rules-from-File.
      *****************************************************************
      * No Sign-on Rules Temp Storage queue exists, so we must read the
      * Sign-On Rules file to retrieve the rules.
      *****************************************************************
           EXEC CICS READ
               FILE(CON-ESONRUL-File-Name)
               INTO(Sign-On-Rules-Record)
               RIDFLD(CON-ESONRUL-RRN)
               RRN
               RESP(W-Resp)
           END-EXEC
           if W-Resp equal DFHRESP(NORMAL)
               perform 0240-Write-ESONRUL-Queue
           else
               move W-Resp to MSG-Read-Error-EIBRESP
               move EIBRESP2 to MSG-Read-Error-EIBRESP2
               move MSG-Read-Error to MON-Message
               set MON-Processig-Error to true
               perform 9100-Return-Data-to-Caller
           end-if
           .            
       0240-Write-ESONRUL-Queue.
      *****************************************************************
      * Create a queue to contain the sign-on rules from the sign-on
      * rules file.
      *****************************************************************
           move CON-ESONRUL-Item-Number
               to W-Item-Number
           EXEC CICS WRITEQ TS
               QNAME(CON-ESONRUL-QName)
               ITEM(W-Item-Number)
               FROM(Sign-On-Rules-Record)
               MAIN
               RESP(W-Resp)
           END-EXEC
           if W-Resp equal DFHRESP(NORMAL)
               continue
           else
               move 'WRITEQ TS' to MSG-Queue-Error-Command
               move CON-ESONRUL-QName
                   to MSG-Queue-Error-QName
               perform 8100-Queue-Error
           end-if
           .                    
       0300-Get-User-Activity-Queue.
      *****************************************************************
      * If no User Activity queue exists for this user, create one and
      * return to the LINKing program.
      * The only time this should happen is when the user initiates the
      * sign-on process by entering the sign-on transaction id on their
      * terminal emulator. In that case we are linked by the Sign-on
      * Program. When we are linked by any other program, a User
      * Activity queue should already exist. Otherwise, there's a bug
      * somewhere in our application code.
      *****************************************************************
           move CON-EUACTTS-Queue-Prefix
               to W-User-Activity-QName-Prefix
           move MON-User-Id
               to W-User-Activity-QName-UserId    
           move CON-EUACTTS-Item-Number
               to W-Item-Number
           EXEC CICS READQ TS
               QNAME(W-User-Activity-QName)
               ITEM(W-Item-Number)
               INTO(User-Activity-Queue-Data)
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
                   continue        
               when DFHRESP(QIDERR)
                   perform 0320-No-User-Activity-Queue
               when other    
                   move 'READQ TS' to MSG-Queue-Error-Command
                   move W-User-Activity-QName
                       to MSG-Queue-Error-QName
                   perform 8100-Queue-Error    
           end-evaluate
           .
       0320-No-User-Activity-Queue
      *****************************************************************
      * If we were linked from the Sign-on Program, this is a normal
      * condition when the user had no signed-on status left over from
      * previous use of the application, and they have initiated
      * sign-on.
      * If we were linked from any other program, it means there's a
      * bug somewhere in our application code.
      *****************************************************************
           if MON-Linking-Program equal
                   CON-Sign-On-Program-Name
               perform 0400-Create-User-Act-Queue
               perform 7400-Set-Container-for-Sign-On
           else
               perform 9300-Initiate-Sign-On
           end-if    
           perform 9100-Return-Data-to-Caller
           .
       0400-Create-User-Act-Queue.    
      *****************************************************************
      * Create a User Activity Temp Storage queue for this user.
      *****************************************************************
           move MON-UserId to UACT-UserId 
           set UACT-User-Category-Not-Set to true
           set UACT-Signing-On to true
           move 1 to UACT-Retry-Number
           move W-New-Activity-Time to UACT-Last-Activity-Time
           move CON-EUACTTS-Item-Number to W-Item-Number
           EXEC CICS WRITEQ TS
               QNAME(W-User-Activity-QName)
               ITEM(W-Item-Number)
               FROM(User-Activity-Queue-Data)
               MAIN
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
                   continue
               when other
                   move 'WRITEQ TS' to MSG-Queue-Error-Command
                   move W-User-Activity-QName
                       to MSG-Queue-Error-QName
                   perform 8100-Queue-Error
           end-evaluate
           .
       1000-Locked-Out.
      *****************************************************************
      * This user was locked out as of their last action.
      *****************************************************************
           move SO-Lockout-Expiration-Interval to W-Defined-Interval
           perform 7200-Check-Time-Interval
           if Interval-Expired
               perform 9300-Initiate-Sign-On
           else
               move UACT-Signed-On-Status to MON-Signed-On-Status
               move MSG-User-Locked-Out to MON-Message
               perform 9100-Return-Data-to-Caller
           end-if
           .
       2000-Signed-On.            
      *****************************************************************
      * This user was signed on as of their last action.
      *****************************************************************
           move SO-Inactivity-Signout-Interval to W-Defined-Interval
           perform 7100-Check-Time-Interval
           if Interval-Expired
               perform 9300-Initiate-Sign-On
           else
               move zero to UACT-Retry-Number
               move W-New-Activity-Time to UACT-Last-Activity-Time
               move UACT-User-Category to MON-User-Category
               move UACT-Signed-On-Status to MON-Signed-On-Status
               perform 7300-Update-User-Activity
               perform 9100-Return-Data-to-Caller
           end-if
           .
       3000-Signing-On.            
      *****************************************************************
      * The last action this user took was a sign-on attempt.
      * If they were in the process of signing on the last time they
      * accessed the application, and they have allowed the inactivity
      * interval to expire since then, they must start sign-on again.
      * If they have exceeded the maximum allowed number of attempts to
      * sign on without entering valid credentials, they will now be
      * locked out.
      *****************************************************************
           move SO-Inactivity-Signout-Interval to W-Defined-Interval
           perform 7100-Check-Time-Interval
           if Interval-Expired
               perform 7200-Set-Activity-for-Sign-On
           else
               move W-New-Activity-Time to UACT-Last-Activity-Time
               if UACT-Retry-Number greater or equal
                       SO-Max-Sign-On-Attempts
                   perform 3100-Set-User-to-Locked-Out
               else
                   perform 3200-Increment-Retry-Count
               end-if
               perform 7300-Update-User-Activity
               perform 9100-Return-Data-to-Caller
           end-if
           .
       3100-Set-User-to-Locked-Out.
      *****************************************************************
      * User has exceeded the maximum allowed number of sign-on
      * attempts.
      *****************************************************************
           set UACT-Locked-Out to true
           move zero to UACT-Retry-Number
           move MSG-User-Locked-Out to MON-Message
           set MON-Status-Locked-Out to true
           move UACT-User-Category to MON-User-Category
           .
       3200-Increment-Retry-Count.
      *****************************************************************
      * User is signing on and has not exceeded the maximum allowed
      * number of sign-on attempts.
      *****************************************************************
           add 1 to UACT-Retry-Number
           set UACT-Signing-On to true
           set MON-Status-Signing-On to true
           move UACT-User-Category to MON-User-Category
           .
       3300-Set-Signed-On-Status.
      *****************************************************************
      * The Sign-on Program has notified us of a change in user status
      * so that we can update the User Activity queue.
      *****************************************************************
           set UACT-Signed-On to true
           move zero to UACT-Retry-Number
           set MON-Status-Signed-On to true
           move UACT-User-Category to MON-User-Category
           move UACT-Signed-On-Status to MON-Signed-On-Status
           perform 7300-Update-User-Activity
           perform 9100-Return-Data-to-Caller
           .
       7100-Check-Time-Interval.    
      *****************************************************************
      * Simplified algorithm to check expiration of a time interval.
      * If year-month-day portion of the date values differ, the
      * interval is considered to be expired.
      * Otherwise, we calculate time based on HHMMSS to the minute,
      * rounded based on the second.
      * Before performing this paragraph:
      *   Put the time interval from the Sign-on Rules into
      *     W-Defined-Interval
      *  That will be either SO-Lockout-Expiration-Interval or
      *     SO-Inactivity-Signout-Interval
      *****************************************************************
           set Interval-Not-Expired to true
           move UACT-Last-Activity-Time to W-Starting-Activity-Time
           move W-New-Activity-Time to W-Current-Activity-Time
           if W-Current-YYMMDD not equal W-Starting-YYMMDD
               set Interval-Expired to true
           else
               compute W-Current-Minutes = 
                   (W-Current-Hour * 60) + W-Current-Minute
               end-compute
               if W-Current-Second greater than 29
                   add 1 to W-Current-Minute
               end-if
               compute W-Starting-Minutes =
                   (W-Starting-Hour * 60) + W-Starting-Minute
               end-compute
               if W-Starting-Second greater than 29
                   add 1 to W-Starting-Minute
               end-if
               compute W-Computed-Interval =
                   W-Current-Minutes - W-Starting-Minutes
               end-compute
               if W-Computed-Interval greater than or equal to
                       W-Defined-Interval
                   set Interval-Expired to true
               end-if
           end-if                                    
           .
       7200-Set-Activity-for-Sign-On.    
      *****************************************************************
      * Set values in the User Activity queue for initial sign-on.
      *****************************************************************
           set UACT-Signing-On to true
           move 1 to UACT-Retry-Number
           move W-New-Activity-Time to UACT-Last-Activity-Time
           perform 7300-Update-User-Activity
           .
       7300-Update-User-Activity.    
      *****************************************************************
      * Rewrite the User Activity queue item with updated values.
      *****************************************************************
           move CON-EUACTTS-Item-Number 
               to W-Item-Number
           EXEC CICS WRITEQ TS
               QNAME(W-User-Activity-QName)
               ITEM(W-Item-Number)
               FROM(User-Activity-Queue-Data)
               REWRITE
               MAIN
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
                   continue
               when other
                   move 'REWRITE' to MSG-Queue-Error-Command
                   move W-User-Activity-QName
                       to MSG-Queue-Error-QName
                   perform 8100-Queue-Error
           end-evaluate
           .
       7400-Set-Container-for-Sign-On.
      *****************************************************************
      * Set values in the container for user's initial sign-on attempt.
      *****************************************************************
           set MON-Status-Signing-On to true
           set MON-Terminate-Now to true
           perform 7500-Populate-Container
           .
       7500-Populate-Container.    
      *****************************************************************
      * Copy values from Working-Storage to the container.
      * If this fails, allow CICS to abend the task.
      *****************************************************************
           EXEC CICS PUT CONTAINER(CON-Monitor-Container-Name)
               CHANNEL(CON-Monitor-Channel-Name)
               FROM(MON-Container-Data)
               FLENGTH(length of MON-Container-Data)
           END-EXEC
           .
       8100-Queue-Error.        
      *****************************************************************
      * Common code for handling errors related to reading and writing
      * Temp Storage queues.
      *****************************************************************
           set MON-Processing-Error to true	
           move W-Resp to MSG-Queue-Error-EIBRESP
           move EIBRESP2 to MSG-Queue-Error-EIBRESP2
           move MSG-Queue-Error to MON-Message
           perform 9100-Return-Data-to-Caller
           .
       8200-Undefined-Status.    
      *****************************************************************
      * Report application logic error: This program was LINKed but
      * the User Activity queue does not have a valid signed-on status.
      *****************************************************************
           move MON-Linking-Program
                   to MSG-Undefined-Status-Program
           move UACT-Signed-On-Status
                   to MSG-Undefined-Status-Value
           move MSG-Undefined-Status to MON-Message
           set MON-Processing-Error to true
           perform 9100-Return-Data-to-Caller
           .
       9100-Return-Data-to-Caller.                    
      *****************************************************************
      * Set new values in the container and return it to the caller.
      *****************************************************************
           perform 7500-Populate-Container
           perform 9900-Return-to-Caller
           .
       9300-Initiate-Sign-On.
      *****************************************************************
      * START the Sign-on transaction attached to the user's terminal.
      *****************************************************************
           if MON-Terminate-Now
               perform 9800-Sign-User-Off
               EXEC CICS START
                   TRANSID(CON-Sign-On-TransId)
                   TERMID(EIBTRMID)
               END-EXEC
           else                   
               perform 7200-Set-Activity-for-Sign-On
               EXEC CICS START
                   TRANSID(CON-Sign-On-TransId)
                   CHANNEL(CON-Monitor-Channel-Name)
                   TERMID(EIBTRMID)
               END-EXEC
           end-if    
           perform 7400-Set-Container-for-Sign-On
           perform 9900-Return-to-Caller
           .           
       9800-User-Sign-Off.
      *****************************************************************
      * Delete the User Activity queue and exit to CICS.
      *****************************************************************
           EXEC CICS DELETEQ TS
               QNAME(W-User-Activity-QName)
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
               when DFHRESP(QIDERR)
                   continue
           end-evaluate
           EXEC CICS DELETE CONTAINER(CON-Monitor-Container-Name)
               CHANNEL(CON-Monitor-Channel-Name)
               RESP(W-Resp)
           END-EXEC
           evaluate W-Resp
               when DFHRESP(NORMAL)
               when DFHRESP(CHANNELERR)
               when DFHRESP(CONTAINERERR)
                   continue
           end-evaluate                    
           .
       9900-Return-to-Caller.           
      *****************************************************************
      * Return to the linking program and pass the channel.
      *****************************************************************
           EXEC CICS 
               RETURN
           END-EXEC
           .    