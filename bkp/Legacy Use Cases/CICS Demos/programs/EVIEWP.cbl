       Identification Division.
       Program-Id. EVIEWP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * View Employee Details
      *****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
           copy DFHBMSCA.
           copy EDETMAP.
       01  Application-Constants.
           copy ECONST.
       01  MON-Container-Data.
           copy EMONCTR.
       01  APP-Container-Data.
           copy EAPPCTR.
       01  LST-Container-Data.
           copy ELSTCTR.
       01  Employee-Master-Record.
           copy EMPMAST.
       01  Temp-Master-Record                 pic x(251).
       01  Manager-Department-Id              pic 9(8).
       01  Registered-User-Record.
           copy EREGUSR.

       01  CICS-Response-Code                 pic s9(9) binary.
       01  Fields-Used-to-Filter-Records.
           05  Filter-Index                   pic s9(4) binary.
           05  filler                         pic x value space.
               88  Filters-Match              value 'Y', false space.
           05  Filter-Value                   pic x(30).
           05  Wildcard-Character             pic x value '*'.
           05  Check-Department-Id.
               10  Check-Department-Id-N      pic 9(8).
           05  filler                         pic x value space.
               88  Department-Id-Hit          value 'Y', false space.   
           05  Primary-Name-Key-Value         pic x(30).
           05  Primary-Name-Key-Length        pic s9(4) binary.                 
       01  File-Browsing-Fields.
           05  File-Name-to-Read              pic x(8).
           05  Record-Id-Field.
               10  RID-Employee-Id            pic 9(8).
               10  filler                     pic x(30).
       01  filler.
           05  filler                         pic x value space.
               88 Matching-Record-Found value 'X' false space.
           05  filler                         pic x value space.
               88  No-More-Records      value 'X' false space.
           05  filler                         pic x value 'N'.
               88  First-Record-Found   value 'Y' false 'N'.        
       01  Date-Formatting-Fields.
           05  Date-Template                  pic x(10)
                                              value '    -  -  '.
           05  Unformatted-Date.
               10  YYYY-Value                 pic x(4).
               10  MM-Value                   pic x(2).
               10  DD-Value                   pic x(2).
           05  Formatted-Date.
               10  YYYY-Value                 pic x(4).
               10  filler                     pic x value '-'.
               10  MM-Value                   pic x(2).
               10  filler                     pic x value '-'.
               10  DD-Value                   pic x(2).
           copy EXFERWS.
           copy EMESSWS.
       Procedure Division.
      *****************************************************************
      * Paragraph numbering scheme
      * 0000-0999 Initialization
      * 1000-1999 Processing terminal input
      * 4000-4999 Pseudoconversational support
      * 5000-5999 Processing filters
      * 6000-6999 Interaction with User Activity Monitor
      * 7000-7999 Common helper routines
      * 8000-8999 Error-handling routines
      * 9000-9999 Exiting or returning
      *****************************************************************
           copy EINIT.
       0100-Initialize-App-Container.
      *****************************************************************
      * Prepare the App Container work area for first-time use.
      *****************************************************************
           initialize App-Container-Data
           EXEC CICS INQUIRE
               TRANSACTION(EIBTRNID)
               PROGRAM(APP-Breadcrumb-Program-Name)
           END-EXEC    
           perform 4300-Get-List-Container
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   set APP-List-Container-Exists to true
                   move CON-View-Program-Name to LST-Program-Name
                   perform 4400-Put-List-Container
                   if LST-Select-by-Primary-Name
                       set APP-Read-by-Primary-Name to true
                   else
                       set APP-Read-by-Employee-Id to true
                   end-if        
               when other
                   set APP-List-Container-Exists to false
                   set APP-Read-by-Employee-Id to true
           end-evaluate
           perform 4100-Put-Application-Container            
           .
       0200-Populate-Initial-Screen.
      *****************************************************************
      * If a List Container was passed and a record is selected,
      * populate the output map from the record data in the container.
      * Otherwise, read the first record in the Employee Master file
      * based on the primary key.
      *****************************************************************
           if APP-List-Container-Exists
               perform 0210-Copy-from-List-Container
           else
               perform 0215-Get-Managers-Department
               perform 0220-Read-First-Record
           end-if
           .        
       0210-Copy-from-List-Container.
      *****************************************************************
      * Copy record fields from the selected record in the List
      * Container.
      *****************************************************************
           move spaces to MSG-Out
           move LST-Current-Record(LST-Selected-Record-Index)
               to APP-Current-Record Employee-Master-Record
           .    
       0215-Get-Managers-Department.
      *****************************************************************
      * Look up the current user's department id.
      *****************************************************************
           perform 7300-Get-Registered-User
           if EIBRESP equal DFHRESP(NORMAL)
               move Employee-Master-Record to Temp-Master-Record
               move CON-EMPMAST-File-Name to File-Name-to-Read
               move spaces to Record-Id-Field
               move Registered-Employee-Id to RID-Employee-Id
               perform 7400-Get-Employee-Record
               move Department-Id to Manager-Department-Id
               move Temp-Master-Record to Employee-Master-Record
               move spaces to Temp-Master-Record
               move spaces to Record-Id-Field
           end-if
           .    
       0220-Read-First-Record.
      *****************************************************************
      * Read the first record from the Employee Master file.
      *****************************************************************
           move spaces to MSG-Out
           set First-Record-Found to false
           set No-More-Records to false
           initialize Employee-Master-Record
           move CON-EMPMAST-File-Name to File-Name-to-Read
           move spaces to Record-Id-Field
           move zero to RID-Employee-Id
           perform 7800-Start-Browse
           evaluate EIBRESP
               when DFHRESP(NORMAL)
                   perform 0230-Find-First-Record
                           until First-Record-Found
                              or No-More-Records
               when DFHRESP(NOTFND)
                   move MSG-Employee-Master-File-Empty to MSG-Out
                   set No-More-Records to true
               when other
                   move 'STARTBR' to ERR-Operation
                   move File-Name-to-Read to ERR-File-Name
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
           end-evaluate
           .                           
       0230-Find-First-Record.
      *****************************************************************
      * Browse the Employee Master File until we reach the first
      * active record or end of file.
      *****************************************************************
           EXEC CICS READNEXT
               FILE(File-Name-to-Read)
               RIDFLD(Record-Id-Field)
               INTO(Employee-Master-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           evaluate EIBRESP
               when DFHRESP(NORMAL)
                   if Active in Employee-Master-Record
                       set First-Record-Found to true
                       move Employee-Master-Record
                           to APP-Current-Record
                   end-if
               when DFHRESP(ENDFILE)
                   move MSG-Employee-Master-File-Empty to MSG-Out
                   set No-More-Records to true
               when other
                   move 'READNEXT' to ERR-Operation
                   move File-Name-to-Read to ERR-File-Name
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
                   set No-More-Records to true
           end-evaluate
       1000-Process-User-Input.
      *****************************************************************
      * Take the action associated with the Attention Identifier key.
      *****************************************************************
           perform 1800-Receive-Map
           evaluate EIBAID
               when DFHENTER
                   perform 1400-Get-Specific-Record
               when DFHPF3
               when DFHPF12
                   perform 9200-Exit
               when DFHPF5
                   perform 9700-Xfer-to-Update-Program
               when DFHPF10
                   perform 9800-Sign-User-Off
               when DFHPF8
                   perform 1100-Page-Forward
               when DFHPF7
                   perform 1200-Page-Backward
               when other
                   move MSG-Undefined-PF-Key to MSG-Out
           end-evaluate
           perform 9100-Display-and-Return
           .
           copy EPAGING.
       1300-Copy-Record-Fields-to-Map.
      *****************************************************************
      * Copy values from the current record to the output map.
      * To Do: Set visibility of fields based on user category.
      *****************************************************************
           move low-values to EDETMO
           perform 1320-Copy-Common-Fields-to-Map 
           evaluate true
               when MON-Category-Administrator
                   perform 1350-Copy-Delete-Flag-to-Map
                   perform 1330-Copy-Admin-Fields-to-Map
               when MON-Category-Standard
                   perform 1340-Copy-Info-Fields-to-Map
                   perform 1370-Copy-Std-Fields-to-Map
               when MON-Category-Manager
                   perform 1340-Copy-Info-Fields-to-Map
                   perform 1350-Copy-Delete-Flag-to-Map
                   perform 1380-Copy-Mgr-Fields-to-Map
               when other
      *            Do something better than this
                   continue
           end-evaluate
           move MSG-Out to MESSO
           .                                   
       1310-Format-Date.
      *****************************************************************
      * Format a date from YYYYMMDD to YYYY-MM-DD for display.
      *****************************************************************
           move spaces to Formatted-Date
           if Unformatted-Date greater than spaces
               move Date-Template to Formatted-Date
               move corresponding Unformatted-Date to Formatted-Date
           end-if
           .        
       1320-Copy-Common-Fields-to-Map.
      *****************************************************************
      * Copy field values from the current record that are displayed
      * for all user categories.
      *****************************************************************
          move CON-View-TransId to TRANIDO
           move Employee-Id to EMPLIDO
           move Primary-Name to PRMNMO
           move Honorific to HONORO
           move Short-Name to SHNAMEO
           move Full-Name to FLNAMEO
           .
       1330-Copy-Admin-Fields-to-Map.
      *****************************************************************
      * Copy field values from the current record that pertain to
      * user category Administrator.
      *****************************************************************
           move DFHPROTN to JBTITLLA DEPTIDLA DEPTNMLA STDATELA
                            ENDATELA APPRDTLA APPRRSLA
           .                 
       1340-Copy-Info-Fields-to-Map.
      *****************************************************************
      * Copy general information fields from the current record.
      *****************************************************************
           move Job-Title to JBTITLO
           move Department-Id to DEPTIDO
           move spaces to DEPTNMO
           move Start-Date to Unformatted-Date
           perform 1310-Format-Date
           move Formatted-Date to STDATEO
           move End-Date to Unformatted-Date
           perform 1310-Format-Date
           move Formatted-Date to ENDATEO
           .
       1350-Copy-Delete-Flag-to-Map.
      *****************************************************************
      * Copy the record deletion flag and date from the current
      * record.
      *****************************************************************
           move Delete-Flag to DELFLGO
           if Deleted
               move '(Deleted)' to DELDSCO
           else
               move '(Active)' to DELDSCO
           end-if
           move Delete-Date to Unformatted-Date
           perform 1310-Format-Date
           move Formatted-Date to DELDTO
           .           
       1360-Copy-Appr-Fields-to-Map.
      *****************************************************************
      * Copy the fields pertaining to employee appraisal to the map.
      *****************************************************************
           move Appraisal-Date to Unformatted-Date
           perform 1310-Format-Date
           move Formatted-Date to APPRDTO
           evaluate true
               when Exceeds-Expectations
                   move 'Exceeds expectations' to APPRRSO
               when Meets-Expectations
                   move 'Meets expectations' to APPRRSO
               when Uh-Oh
                   move 'Uh-oh' to APPRRSO
               when other
                   move '(undefined)' to APPRRSO
           end-evaluate
           .
       1370-Copy-Std-Fields-to-Map.
      *****************************************************************
      * If the Employee Master File record pertains to the current
      * user, show the employee appraisal date and result fields.
      *****************************************************************
           perform 7300-Get-Registered-User
           if EIBRESP equal DFHRESP(NORMAL)
           and Registered-Employee-Id equal Employee-Id
               perform 1360-Copy-Appr-Fields-to-Map
           else
               move DFHPROTN to APPRDTLA APPRRSLA
           end-if
           move DFHPROTN to DELFLLA ASOFLA        
           .
       1380-Copy-Mgr-Fields-to-Map.
      *****************************************************************
      * If the Employee Master File record pertains to an employee in
      * this manager's department, show the employee appraisal date
      * and result fields.
      *****************************************************************
           if Manager-Department-Id equal Department-Id
               perform 1360-Copy-Appr-Fields-to-Map
           else
               move DFHPROTN to APPRDTLA APPRRSLA
           end-if
           .        
       1400-Get-Specific-Record.
      *****************************************************************
      * Read one record from the Employee Master File based on the
      * Employee Id or Primary Name value passed from the terminal.
      *****************************************************************
           evaluate true
               when EMPLIDL greater than zero
                   set APP-Read-by-Employee-Id to true
                   EXEC CICS BIF DEEDIT
                       FIELD(EMPLIDI)
                       LENGTH(8)
                   END-EXEC
                   move spaces to Employee-Master-Record
                   move CON-EMPMAST-File-Name to File-Name-to-Read
                   move EMPLIDI to Record-Id-Field
                   perform 1410-Read-and-Check-Result
               when PRMNML greater than zero
                   set APP-Read-by-Primary-Name to true
                   move spaces to Employee-Master-Record
                   move CON-EMPMAST-Primary-Name-Path 
                       to File-Name-to-Read
                   move PRMNMI to Record-Id-Field
                   perform 1410-Read-and-Check-Result
               when other
                   move APP-Current-Record to Employee-Master-Record
                   perform 1300-Copy-Record-Fields-to-Map
           end-evaluate
           .                
       1410-Read-and-Check-Result.
      *****************************************************************
      * Read a single record from the Employee Master File and check
      * the response.
      *****************************************************************
           perform 7400-Get-Employee-Record
           evaluate EIBRESP
               when DFHRESP(NORMAL)
                   perform 7910-Check-Filters-Read
               when DFHRESP(NOTFND)
                   perform 7600-Employee-Record-Not-Found
               when other
                   move 'READ' to ERR-Operation
                   move File-Name-to-Read to ERR-File-Name
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
           end-evaluate
           perform 1300-Copy-Record-Fields-to-Map
           .                
       1800-Receive-Map.
      *****************************************************************
      * Receive mapped data from the terminal.
      *****************************************************************
           EXEC CICS RECEIVE
               MAP(CON-View-Map-Name)
               MAPSET(CON-View-Mapset-Name)
               INTO(EDETMI)
           END-EXEC    
           .
       4100-Put-Application-Container.
      *****************************************************************
      * Copy working storage data to the Application Container.
      *****************************************************************
           move CON-App-Container-Name to XFER-Container-Name
           move CON-App-Channel-Name to XFER-Channel-Name
           move APP-Container-Data to XFER-Container-Data
           move length of APP-Container-Data
               to XFER-Container-Data-Length
           perform 7100-Put-Container
           .
       4200-Get-Application-Container.
      *****************************************************************
      * Copy Application Container data to working storage.
      *****************************************************************
           move CON-App-Container-Name to XFER-Container-Name
           move CON-App-Channel-Name to XFER-Channel-Name
           perform 7200-Get-Container
           move XFER-Container-Data to App-Container-Data
           .            
       4300-Get-List-Container.
      *****************************************************************
      * Copy List Container data to working storage.
      *****************************************************************
           move CON-List-Container-Name to XFER-Container-Name
           move CON-List-Channel-Name to XFER-Channel-Name
           perform 7200-Get-Container
           move XFER-Container-Data to LST-Container-Data
           .            
       4400-Put-List-Container.
      *****************************************************************
      * Copy working storage data to the List Container.
      *****************************************************************
           move CON-List-Container-Name to XFER-Container-Name
           move CON-List-Channel-Name to XFER-Channel-Name
           move LST-Container-Data to XFER-Container-Data
           move length of LST-Container-Data
               to XFER-Container-Data-Length
           perform 7100-Put-Container
           .
           copy EMONITOR.
           copy EXFERPD.
       7300-Get-Registered-User.
      *****************************************************************
      * Read the Registered Users File using the current Employee
      * Application User Id.
      *****************************************************************
          EXEC CICS READ
              FILE(CON-Registered-Users-Filename)
              RIDFLD(MON-UserId)
              INTO(Registered-User-Record)
              RESP(CICS-Response-Code)
          END-EXEC
          .    
       7400-Get-Employee-Record.
      *****************************************************************
      * Read the Employee Master File.
      *****************************************************************
          EXEC CICS READ
              FILE(File-Name-to-Read)
              RIDFLD(Record-Id-Field)
              INTO(Employee-Master-Record)
              RESP(CICS-Response-Code)
          END-EXEC
          .    
       7510-Get-Next-Employee-Record.
      *****************************************************************
      * Browse the Employee Master File.
      *****************************************************************
           if APP-Read-by-Primary-Name
               move CON-EMPMAST-Primary-Name-Path to File-Name-to-Read
               move Primary-Name to Record-Id-Field
               move high-values to Record-Id-Field(37:)
           else
               move CON-EMPMAST-File-Name to File-Name-to-Read
               move spaces to Record-Id-Field
               move Employee-Id to RID-Employee-Id
               add 1 to RID-Employee-Id
           end-if
           perform 7800-Start-Browse
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   continue
               when DFHRESP(NOTFND)
                   perform 7530-End-File-Forward
               when other
                   move 'STARTBR' to ERR-Operation
                   perform 8200-File-Error
                   perform 9100-Display-and-Return
           end-evaluate
           perform 7520-Read-Next
           if No-More-Records
               perform 7530-End-File-Forward
           end-if
           perform 7820-End-Browse
           .                                       
       7520-Read-Next.
      *****************************************************************
      * Browse forward through the Employee Master File until we reach
      * a record that is not logically deleted and that matches any
      * filtering criteria that are in effect.
      *****************************************************************
           set Matching-Record-Found to false
           set No-More-Records to false
           perform until Matching-Record-Found
                      or No-More-Records
               EXEC CICS READNEXT
                   FILE(File-Name-to-Read)
                   RIDFLD(Record-Id-Field)
                   INTO(Employee-Master-Record)
                   RESP(CICS-Response-Code)
               END-EXEC
               evaluate EIBRESP
                   when DFHRESP(NORMAL)
                       perform 7920-Check-Filters-Read-Next
                   when DFHRESP(ENDFILE)
                       set No-More-Records to true
                   when other
                       move 'READNEXT' to ERR-Operation
                       move File-Name-to-Read to ERR-File-Name
                       move EIBRESP to ERR-EIBRESP
                       move EIBRESP2 to ERR-EIBRESP2
                       move MSG-File-Error to MSG-Out
               end-evaluate
           end-perform                                   
           .                 
       7530-End-File-Forward.
      *****************************************************************
      * Common code when we reach end of file during a forward browse.
      *****************************************************************
           move MSG-No-More-Records to MSG-Out
           set Highlight-Error to true
           move APP-Current-Record to Employee-Master-Record
           perform 1300-Copy-Record-Fields-to-Map
           perform 9100-Display-and-Return
           .
       7540-Get-Prev-Employee-Record.
      *****************************************************************
      * Read the previous Employee Master record.
      *****************************************************************
           if APP-Read-by-Primary-Name
               move CON-EMPMAST-Primary-Name-Path to File-Name-to-Read
               move Primary-Name to Record-Id-Field
           else
               move CON-EMPMAST-File-Name to File-Name-to-Read
               move spaces to Record-Id-Field
               move Employee-Id to RID-Employee-Id
           end-if
           perform 7800-Start-Browse
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   perform 7555-Read-Previous
                   evaluate EIBRESP
                       when DFHRESP(ENDFILE)
                       when DFHRESP(NOTFND)
                       perform 7560-End-File-Backward
                   end-evaluate
               when DFHRESP(NOTFND)
                   perform 7560-End-File-Backward
               when other
                   move 'STARTBR' to ERR-Operation
                   perform 8200-File-Error
                   perform 9100-Display-and-Return
           end-evaluate
           perform 7550-Read-Previous
           if No-More-Records
               perform 7560-End-File-Backward
           end-if
           perform 7820-End-Browse
           .                            
       7550-Read-Previous.
      *****************************************************************
      * Browse backward through the Employee Master File until we 
      * reach a record that is not logically deleted and that matches
      * any filtering criteria that are in effect.
      *****************************************************************
           set Matching-Record-Found to false
           set No-More-Records to false
           perform until Matching-Record-Found
                      or No-More-Records
               perform 7555-Read-Previous
               evaluate CICS-Response-Code
                   when DFHRESP(NORMAL)
                       perform 7920-Check-Filters-Read-Next
                   when DFHRESP(ENDFILE)
                       set No-More-Records to true
                   when other
                       move 'READPREV' to ERR-Operation
                       perform 8200-File-Error
               end-evaluate
           end-perform
           .                               
       7555-Read-Previous.
      *****************************************************************
      * Common READPREV command.
      *****************************************************************
           EXEC CICS READPREV
               FILE(File-Name-to-Read)
               RIDFLD(Record-Id-Field)
               INTO(Employee-Master-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           .    
       7560-End-File-Backward.
      *****************************************************************
      * Common code when we reach end of file during a backward browse.
      *****************************************************************
           move MSG-Top-of-File to MSG-Out
           set Highlight-Error to true
           move APP-Current-Record to Employee-Master-Record
           perform 1300-Copy-Record-Fields-to-Map
           perform 9100-Display-and-Return
           .
       7600-Employee-Record-Not-Found.
      *****************************************************************
      * Common code for all cases when the Employee Master record is
      * treated as 'not found' (logically or physically).
      *****************************************************************
           move Employee-Id to NOTFND-Employee-Id
           move spaces to Employee-Master-Record
           move NOTFND-Employee-Id to Employee-Id
           move MSG-Employee-Not-Found to MSG-Out
           .
           copy EFILTERS.
       7800-Start-Browse.
      *****************************************************************
      * Initiate browsing on the Employee Master file.
      *****************************************************************
           EXEC CICS STARTBR
               FILE(File-Name-to-Read)
               RIDFLD(Record-Id-Field)
               RESP(CICS-Response-Code)
           END-EXEC    
           .
       7820-End-Browse.
      *****************************************************************
      * Terminate browsing on the Employee Master file.
      *****************************************************************
           EXEC CICS ENDBR
               FILE(File-Name-to-Read)
           END-EXEC    
           .
       7910-Check-Filters-Read.
      *****************************************************************
      * See if the current record is logically deleted and whether it
      * matches any record filtering criteria in effect.
      * Take action appropriate to reading a specific record.
      *****************************************************************
           if Deleted in Employee-Master-Record
               perform 7600-Employee-Record-Not-Found
           end-if
           if APP-List-Container-Exists
               perform 7700-Apply-Filters
               if Filters-Match
                   continue
               else
                   perform 7600-Record-Not-Found
               end-if
           end-if
           move Employee-Master-Record to APP-Current-Record
           .                
       7920-Check-Filters-Read-Next.
      *****************************************************************
      * See if the current record is logically deleted and whether it
      * matches any record filtering criteria in effect.
      * Take action appropriate to browsing for the next matching
      * record.
      *****************************************************************
           if Deleted in Employee-Master-Record
               continue
           else
               if APP-List-Container-Exists
                   perform 7700-Apply-Filters    
                   if Filters-Match
                       set Matching-Record-Found to true
                       move Employee-Master-Record 
                           to APP-Current-Record
                   end-if
               else
                   set Matching-Record-Found to true
                   move Employee-Master-Record 
                       to APP-Current-Record
               end-if
           end-if
           .
           copy EMESSPD.                
       9100-Display-and-Return.
      *****************************************************************
      * Populate fields in the output map with current values and
      * display the screen. Pseudoconversational return.
      *****************************************************************
           if APP-List-Container-Exists
               perform 4400-Put-List-Container
           end-if
           if APP-Browse-Forward-Disabled
               move DFHPROTN to HLPPF8A
           end-if
           if APP-Browse-Backward-Disabled
               move DFHPROTN to HLPPF7A
           end-if
           if Highlight-Error
               move DFHRED to MESSC
           end-if
           set Highlight-Error to false
           EXEC CICS SEND
               MAP(CON-View-Map-Name)
               MAPSET(CON-View-Mapset-Name)
               FROM(EDETMO)
               FRSET
               ERASE
               FREEKB
           END-EXEC
           EXEC CICS RETURN
               TRANSID(CON-View-TransId)
               CHANNEL(CON-APP-Channel-Name)
           END-EXEC
           .
       9200-Exit.
      *****************************************************************
      * Transfer control to the function that is logically upstream
      * from here.
      *****************************************************************
           perform 6200-Put-Monitor-Container
           EXEC CICS DELETE
               CONTAINER(CON-List-Container-Name)
               CHANNEL(CON-List-Channel-Name)
               NOHANDLE
           END-EXEC
           EXEC CICS DELETE
               CONTAINER(CON-APP-Container-Name)
               CHANNEL(CON-APP-Channel-Name)
               NOHANDLE
           END-EXEC
           EXEC CICS XCTL
               PROGRAM(APP-Breadcrumb-Program-Name)
               CHANNEL(CON-Monitor-Channel-Name)
           END-EXEC            
           .
       9700-Xfer-to-Update-Program.
      *****************************************************************
      * Transfer control to the update program.
      *****************************************************************
          move APP-Current-Record to LST-Current-Record(1)
          move 1 to LST-Selected-Record-Index
          perform 4400-Put-List-Container
          EXEC CICS XCTL
              PROGRAM(CON-Update-Program-Name)
              CHANNEL(CON-List-Channel-Name)
          END-EXEC
          .
          copy ESIGNOFF.    