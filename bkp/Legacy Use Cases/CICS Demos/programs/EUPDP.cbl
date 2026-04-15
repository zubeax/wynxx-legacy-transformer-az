       Identification Division.
       Program-Id. EUPDP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Update Employee Master Record
      *****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
           copy DFHBMSCA.
           copy EUPDMAP.
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
       01  Raw-Input-Date.
           05  filler                         pic x(2).
           05  De-Edited-Date                 pic x(8).
       01  Input-Validation-Errors.
           05  Start-Date-Label               pic x(20)
               value 'Start Date'.
           05  Department-Id-Label            pic x(20)
               value 'Department Id'.
           05  Job-Title-Label                pic x(20)
               value 'Job Title'.
           05  Full-Name-Label                pic x(20)
               value 'Full Name'.
           05  Primary-Name-Label             pic x(20)
               value 'Primary Name'.
           05  MSG-Mandatory-Field.
               10  filler                     pic x(30)
                   value 'Mandatory value not provided: '.
               10  ERR-Mandatory-Field-Label  pic x(20).                                    
           copy EXFERWS.
           copy EMESSWS.
       Procedure Division.
      *****************************************************************
      * Paragraph numbering scheme
      * 0000-0999 Initialization
      * 1000-1999 Processing terminal input
      * 2000-2999 Updating files
      * 3000-3999 Editing input fields
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
           set APP-Browse-Forward-Enabled to true
           set APP-Browse-Backward-Enabled to true
           EXEC CICS INQUIRE
               TRANSACTION(EIBTRNID)
               PROGRAM(APP-Breadcrumb-Program-Name)
           END-EXEC    
           perform 4300-Get-List-Container
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   set APP-List-Container-Exists to true
                   move CON-Update-Program-Name to LST-Program-Name
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
               to APP-Current-Record 
                  APP-Original-Record
                  Employee-Master-Record
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
                              APP-Original-Record
                   end-if
               when DFHRESP(ENDFILE)
                   move MSG-Employee-Master-File-Empty to MSG-Out
                   set No-More-Records to true
               when other
                   move 'READNEXT' to ERR-Operation
                   perform 8200-File-Error
                   set No-More-Records to true
           end-evaluate
       1000-Process-User-Input.
      *****************************************************************
      * Take the action associated with the Attention Identifier key.
      *****************************************************************
           if APP-Deletion-In-Progress
               perform 1600-Process-Delete-Conf
           end-if    
           perform 1800-Receive-Map
           move spaces to MSG-Out
           evaluate EIBAID
               when DFHENTER
                   perform 1400-Read-New-or-Val-Current
               when DFHPF3
                   perform 2100-Update-Record
                   perform 9200-Exit
               when DFHPF12
                   perform 9200-Exit
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
           move low-values to EUPDMO
           perform 7300-Get-Registered-User
           if CICS-Response-Code not equal DFHRESP(NORMAL)
               move zero to Registered-User-Id
           end-if    
           perform 1320-Copy-Common-Fields-to-Map 
           evaluate true
               when MON-Category-Administrator
                   perform 1350-Copy-Delete-Flag-to-Map
                   perform 1330-Set-Admin-Field-Attrs
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
           move CON-Update-TransId to TRANIDO
           move Employee-Id to EMPLIDO
           move Primary-Name to PRMNMO
           move Honorific to HONORO
           move Short-Name to SHNAMEO
           move Full-Name to FLNAMEO
           .
       1330-Set-Admin-Field-Attrs.
      *****************************************************************
      * Copy field values from the current record that pertain to
      * user category Administrator.
      *****************************************************************
           move DFHPROTN to 
                         JBTITLLA JBTITLA  DEPTNMLA DEPTNMA
                         STDATELA STDATEA  ENDATELA ENDATEA
                         APPRDTLA APPRDTA  APPRRSLA APPRRSA
           move DFHBMASK to
                         FLNAMEA  HONORA  SHNAMEA
                         DEPTIDA  DEPTNMA              
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
           if Registered-Employee-Id equal Employee-Id
               perform 1360-Copy-Appr-Fields-to-Map
               move DFHBMASK to
                         APPRDTLA APPRDTA APPRRSLA APPRRSA                         
           else
               move DFHPROTN to 
                         APPRDTLA APPRDTA APPRRSLA APPRRSA
               move DFHBMASK to
                         PRMNMA  FLNAMEA  SHNAMEA  HONORA          
           end-if
           move DFHPROTN to 
                         DELFLLA DELFLGA DELDTLA DELDTA HLPPF11A
           move DFHBMASK to
                         JBTITLA STDATEA ENDATEA DEPTIDA                      
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
               move DFHPROTN to 
                             APPRDTLA APPRDTA APPRRSLA APPRRSA
                             DELFLLA DELFLGA DELDSCA DELDTLA DELDTA
                             HLPPF11A
               move DFHBMASK to
                             JBTITLA DELDTA STDATEA ENDATEA              
           end-if
           .        
       1400-Read-New-or-Val-Current.
      *****************************************************************
      * If the user entered a different Employee Id, read that record.
      * Otherwise, validate the input values transmitted from the
      * terminal.
      *****************************************************************
           if EMPLIDL greater than zero
               EXEC CICS BIF DEEDIT
                   FIELD(EMPLIDI)
                   LENGTH(length of EMPLIDI)
               END-EXEC
               perform 1420-Get-Specific-Record
           else
               perform 3100-Validate-Input
               perform 1300-Copy-Record-Fields-to-Map
           end-if
           .            
       1420-Get-Specific-Record.
      *****************************************************************
      * Read one record from the Employee Master File based on the
      * Employee Id or Primary Name value passed from the terminal.
      *****************************************************************
           set APP-Browse-Forward-Enabled to true
           set APP-Browse-Backward-Enabled to true
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
                   perform 1450-Read-and-Check-Result
               when PRMNML greater than zero
                   set APP-Read-by-Primary-Name to true
                   move spaces to Employee-Master-Record
                   move CON-EMPMAST-Primary-Name-Path 
                       to File-Name-to-Read
                   move PRMNMI to Record-Id-Field
                   perform 1450-Read-and-Check-Result
               when other
                   move APP-Current-Record to Employee-Master-Record
                   perform 1300-Copy-Record-Fields-to-Map
           end-evaluate
           .                
       1450-Read-and-Check-Result.
      *****************************************************************
      * Read a single record from the Employee Master File and check
      * the response.
      *****************************************************************
           perform 7400-Get-Employee-Record
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   perform 7910-Check-Filters-Read
               when DFHRESP(NOTFND)
                   perform 7600-Employee-Record-Not-Found
               when other
                   move 'READ' to ERR-Operation
                   move CON-EMPMAST-File-Name to ERR-File-Name
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
           end-evaluate
           perform 1300-Copy-Record-Fields-to-Map
           .                
       1500-Begin-Delete-Process.
      *****************************************************************
      * Display confirmation dialog for record deletion.
      *****************************************************************
           set APP-Deletion-In-Progress to true
           move low-values to EDELMO
           move APP-Current-Employee-Id-X to DELEMPO
           EXEC CICS SEND
               MAP(CON-Confirm-Delete-Map-Name)
               MAPSET(CON-Confirm-Delete-Mapset-Name)
               FROM(EDELMO)
           END-EXEC
           perform 4100-Put-Application-Container
           EXEC CICS RETURN
               TRANSID(CON-Update-TransId)
               CHANNEL(CON-APP-Channel-Name)
           END-EXEC
           .        
       1600-Process-Delete-Conf.
      *****************************************************************
      * Handle the user's response to the delete confirmation dialog.
      *****************************************************************
           EXEC CICS RECEIVE END-EXEC
           evaluate EIBAID
               when DFHPF11
                   perform 2200-Delete-Record
               when other
                   perform 1300-Copy-Record-Fields-to-Map
           end-evaluate
           set APP-Deletion-In-Progress to False
           perform 4100-Put-Application-Container
           perform 9100-Display-and-Return
           .            
        1800-Receive-Map.
      *****************************************************************
      * Receive mapped data from the terminal.
      *****************************************************************
           EXEC CICS RECEIVE
               MAP(CON-View-Map-Name)
               MAPSET(CON-View-Mapset-Name)
               INTO(EUPDMI)
           END-EXEC    
           .
      2100-Update-Record.
      *****************************************************************
      * Update the current employee record subject to input validation
      * and any modifications by other tasks while our user was editing
      * the record.
      *****************************************************************
           move spaces to MSG-Out
           perform 3100-Validate-Input
           if MSG-Out equal spaces
               perform 2300-Update-If-No-Conflict
           end-if
           .    
       2200-Delete-Record.
      *****************************************************************
      * Delete the current record.
      *****************************************************************
           perform 2310-Obtain-Active-Lock
           if Employee-Master-Record equal APP-Original-Record


      * Logical deletion begin
               set Deleted in Employee-Master-Record to true
               move FUNCTION CURRENT-DATE to Delete-Date
               EXEC CICS REWRITE
                   FILE(CON-EMPMAST-File-Name)
                   FROM(Employee-Master-Record)
                   RESP(CICS-Response-Code)
               END-EXEC
      * Logical deletion end


      * Physical deletion begin
      *        EXEC CICS DELETE
      *            FILE(CON-EMPMAST-File-Name)
      *            RIDFLD(Employee-Id)
      *            RESP(CICS-Response-Code)
      *        END-EXEC    
      * Physical deletion end

               evaluate CICS-Response-Code
                   when DFHRESP(NORMAL)
                       move APP-Current-Employee-Id-X
                           to MSG-Deleted-Employee-Id
                       move MSG-Record-Deleted to MSG-Out
                   when other
                       move 'DELETE' to ERR-Operation
                       move CON-EMPMAST-File-Name to ERR-File-Name
                       move EIBRESP to ERR-EIBRESP
                       move EIBRESP2 to ERR-EIBRESP2
                       move MSG-File-Error to MSG-Out
                       perform 9100-Display-and-Return
               end-evaluate
           else
               perform 2320-Update-Conflict
               perform 1300-Copy-Record-Fields-to-Map
               perform 9100-Display-and-Return
           end-if
           .                            
       2300-Update-If-No-Conflict.
      *****************************************************************
      * Read the latest version of the record and see if it matches the
      * version our user has been working with as the base for changes.
      * If the original has not been changed by another user, update
      * the record. Otherwise, display the latest version of the record
      * and notify the user of the conflict.
      *****************************************************************
           perform 2310-Obtain-Active-Lock
           if Employee-Master-Record equal APP-Original-Record
               EXEC CICS REWRITE
                   FILE(CON-EMPMAST-File-Name)
                   FROM(APP-Current-Record)
                   RESP(CICS-Response-Code)
               END-EXEC
               evaluate CICS-Response-Code
                   when DFHRESP(NORMAL)
                       move MSG-Successful-Update to MSG-Out
                       move APP-Current-Record
                           to Employee-Master-Record
                   when DFHRESP(DUPREC)
                       move MSG-Duplicate-Primary-Name to MSG-Out
                   when other
                       move 'REWRITE' to ERR-Operation
                       move CON-EMPMAST-File-Name to ERR-File-Name
                       move EIBRESP to ERR-EIBRESP
                       move EIBRESP2 to ERR-EIBRESP2
                       move MSG-File-Error to MSG-Out
                       perform 9100-Display-and-Return
               end-evaluate
           else
               perform 2320-Update-Conflict
           end-if
           perform 1300-Copy-Record-Fields-to-Map
           .                    
       2310-Obtain-Active-Lock.
      *****************************************************************
      * Get an exclusive lock on the current record.
      *****************************************************************
           set Record-Available to false
           perform until Record-Available
               EXEC CICS READ
                   FILE(CON-EMPMAST-File-Name)
                   RIDFLD(Employee-Id)
                   INTO(Employee-Master-Record)
                   LENGTH(length of Employee-Master-Record)
                   UPDATE
                   RESP(CICS-Response-Code)
               END-EXEC
               evaluate CICS-Response-Code
                   when DFHRESP(NORMAL)
                       set Record-Available to true
                   when DFHRESP(RECORDBUSY)
                       continue
                   when other
                       move 'READ UPDATE' to ERR-Operation
                       move CON-EMPMAST-File-Name to ERR-File-Name
                       move EIBRESP to ERR-EIBRESP
                       move EIBRESP2 to ERR-EIBRESP2
                       move MSG-File-Error to MSG-Out
                       perform 9100-Display-and-Return
               end-evaluate
           end-perform
           .                        
       2320-Update-Conflict.
      *****************************************************************
      * Notify the user that the record they are working with was
      * modified by another user.
      *****************************************************************
           move MSG-Update-Conflict to MSG-Out
           move APP-Original-Record to Employee-Master-Record
           .
       3100-Validate-Input.
      *****************************************************************
      * Check all input values for validity.
      *****************************************************************
           move APP-Current-Record to Employee-Master-Record
           perform 3200-Copy-Values-from-Map
           perform 3300-Validate-Input-Data
           move Employee-Master-Record to APP-Current-Record
           .
       3200-Copy-Values-from-Map.
      *****************************************************************
      * Copy modified values from the input map to working storage.
      *****************************************************************
           if PRMNML greater than zero
               move PRMNMI to Primary-Name
           end-if
           if HONORL greater than zero
               move HONORI to Honorific
           end-if
           if SHNAMEL greater than zero
               move SHNAMEI to Short-Name
           end-if
           if FLNAMEL greater than zero
               move FLNAMEI to Full-Name
           end-if
           if JBTITLL greater than zero
               move JBTITLI to Job-Title
           end-if
           if DEPTIDL greater than zero
               EXEC CICS BIF DEEDIT
                   FIELD(DEPTIDI)
                   LENGTH(length of DEPTIDI)
               END-EXEC
               if DEPTIDI equal '00000000'
                   move spaces to Department-Id-X
               else
                   move DEPTIDI to Department-Id-X
               end-if
           end-if
           if STDATEL greater than zero
               move STATEI to Raw-Input-Date
               perform 3210-De-Edit-Date
               move De-Edited-Date to Start-Date
           end-if
           if ENDATEL greater than zero
               move ENDATEI to Raw-Input-Date
               perform 3210-De-Edit-Date
               move De-Edited-Date to End-Date
           end-if
           if APPRDTL greater than zero
               move APPRDTI to Raw-Input-Date
               perform 3210-De-Edit-Date
               move De-Edited-Date to Appraisal-Date
           end-if
           if APPRRSL greater than zero
               move APPRRSI to Appraisal-Result
           end-if
           if DELFLGL greater than zero
               move DELFLGI to Delete-Flag
           end-if
           .                                                        
       3210-De-Edit-Date.
      *****************************************************************
      * De-edit an input date value.
      *****************************************************************
           EXEC CICS BIF DEEDIT
               FIELD(Raw-Input-Date)
               LENGTH(10)
           END-EXEC
           .    
       3300-Validate-Input-Data.
      *****************************************************************
      * Perform field validations.
      *****************************************************************
           if Start-Date equal spaces
               move Start-Date-Label to ERR-Mandatory-Field-Label
               move DFHRED to STDATEC
               move -1 to STDATEL
               perform 3310-Set-Mandatory-Field-Error
           end-if    
           if Job-Title equal spaces
               move Job-Title-Label to ERR-Mandatory-Field-Label
               move DFHRED to JBTITLC
               move -1 to JBTITLL
               perform 3310-Set-Mandatory-Field-Error
           end-if    
           if Full-Name equal spaces
               move Full-Name-Label to ERR-Mandatory-Field-Label
               move DFHRED to FLNAMEC
               move -1 to FLNAMEL
               perform 3310-Set-Mandatory-Field-Error
           end-if    
           if Primary-Name equal spaces
               move Primary-Name-Label to ERR-Mandatory-Field-Label
               move DFHRED to PRMNMC
               move -1 to PRMNML
               perform 3310-Set-Mandatory-Field-Error
           end-if    
           .
       3310-Set-Mandatory-Field-Error.
      *****************************************************************
      * Move mandatory field missing error message to common area.
      *****************************************************************
           move MSG-Mandatory-Field to MSG-Out
           set Highlight-Error to true
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
           set APP-Browse-Forward-Enabled to true
           set APP-Browse-Backward-Enabled to true
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
           set APP-Browse-Forward-Enabled to true
           set APP-Browse-Backward-Enabled to true
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
           set APP-Browse-Backward-Disabled to true
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
                                          APP-Original-Record
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
                              APP-Original-Record
                   end-if
               else
                   set Matching-Record-Found to true
                   move Employee-Master-Record to APP-Current-Record
                                                  APP-Original-Record
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
           set Highlight-Error to false
           EXEC CICS SEND
               MAP(CON-View-Map-Name)
               MAPSET(CON-View-Mapset-Name)
               FROM(EUPDMO)
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
          .
          copy ESIGNOFF.    