       Identification Division.
       Program-Id. EADDP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Add Employee
      *****************************************************************
       Data Division.
       Working-Storage Section.       
           copy DFHAID.
           copy DFHBMSCA.
           copy EADDMAP.
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
       01  Audit-Trail-Record.
           copy EAUDREC.
       01  Manager-Department-Id              pic 9(8).
       01  Registered-User-Record.
           copy EREGUSR.
       01  CICS-Response-Code                 pic s9(9) binary.
       01  Employee-Id-Temp                   pic 9(8).
       01  Department-Id-Temp                 pic 9(8).
       01  filler.
           05  filler pic x value space.
               88  File-Is-Empty      value 'X' false space.
           05  filler pic x value space.
               88  Browse-In-Progress value 'X' false space.
           05  filler pic x value space.
               88  Enqueue-Successful value 'X' false space.
       01  Fields-Used-to-Filter-Records.
           05  Filter-Index                   pic s9(4) binary.
           05  filler                         pic x value space.
               88  Department-Id-Hit value 'Y' false space.
           05  Primary-Name-Key-Value         pic x(30).
           05  Primary-Name-Key-Length        pic s9(4) binary.
       01  File-Browsing-Fields.
           05  File-Name-to-Read              pic x(8).  
           05  Record-Id-Field.
               10  RID-Employee-Id            pic 9(8).
               10  filler                     pic x(30).
       01  filler.
           05  filler pic x value space.
               88  Matching-Record-Found value 'X' false space.
           05  filler pic x value space.
               88  No-More-Records       value 'X' false space.
           05  filler pic x value 'N'.
               88  First-Record-Found    value 'Y' false 'N'.                
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
       Procedure Division.
      *****************************************************************
      * Paragraph numbering scheme
      * 0000-0999 Initialization
      * 1000-1999 Processing terminal input
      * 2000-2999 Editing input fields
      * 3000-3999 Updating files
      * 4000-4999 Pseudoconversational support
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
           initialize APP-Container-Data
           EXEC CICS INQUIRE
               TRANSACTION(EIBTRNID)
               PROGRAM(APP-Breadcrumb-Program-Name)
           END-EXEC
           set APP-List-Container-Exists to false
           set APP-Read-by-Employee-Id to true
           perform 4100-Put-Application-Container
           .    
       0200-Populate-Initial-Screen.          
      *****************************************************************
      * Initialize the map for entry of values for a new record.
      *****************************************************************
           move low-values to EADDMO
           perform 2115-Look-Up-Department-Id
           .
       1000-Process-User-Input.          
      *****************************************************************
      * Take the action associated with the Attention Identifier Key.
      *****************************************************************
           perform 1800-Receive-Map
           move spaces to MSG-Out
           evaluate EIBAID
               when DFHENTER
                   perform 3100-Validate-Input
               when DFHPF3
                   perform 2100-Add-Record
                   perform 9200-Exit
               when DFHPF4
                   perform 2100-Add-Record
               when DFHPF12
                   perform 9200-Exit
               when DFHPF10
                   perform 9800-Sign-User-Off
               when other
                   move MSG-Undefined-PF-Key to MSG-Out
           end-evaluate
           perform 9100-Display-and-Return
           .
       1300-Copy-Record-Fields-to-Map.          
      *****************************************************************
      * Copy values from the current record to the output map.
      *****************************************************************
           move CON-Add-TransId to TRANIDO
           move Employee-Id to EMPLIDO
           move Primary-Name to PRMNMO
           move Honorific to HONORO
           move Short-Name to SHNAMEO
           move Full-Name to FLNAMEO
           move Job-Title to JBTITLO
           move Department-Id-Temp to DEPTIDO
           move spaces to DEPTNMO
           move Start-Date to Unformatted-Date
           perform 1310-Format-Date
           move Formatted-Date to STDATEO
           move MSG-Out to MESSO
           .
       1310-Format-Date.          
      *****************************************************************
      * Format a daate from YYYYMMDD to YYYY-MM-DD for display.
      *****************************************************************
           if Unformatted-Date greater than spaces
               move Date-Template to Formatted-Date
               move corresponding Unformatted-Date to Formatted-Date
           else
               move spaces to Formatted-Date
           end-if
           .        
       1800-Receive-Map.          
      *****************************************************************
      * Receive mapped data from the terminal.
      *****************************************************************
           EXEC CICS RECEIVE
               MAP(CON-Add-Map-Name)
               MAPSET(CON-Add-Mapset-Name)
               INTO(EADDMI)
           END-EXEC    
           .
       2100-Add-Record.          
      *****************************************************************
      * Add employee record if input values are valid.
      *****************************************************************
           move spaces to MSG-Out
           perform 3100-Validate-Input
           if MSG-Out equal spaces
               perform 2110-Generate-Employee-Id
                   until Enqueue-Successful
               perform 2120-Write-and-Dequeue
           end-if
           .        
       2110-Generate-Employee-Id.          
      *****************************************************************
      * Find the next integer value above the Employee Id of the last
      * record in the Employee Master file.
      *****************************************************************
           set File-Is-Empty to false
           set Browse-In-Progress to true
           move high-values to Employee-Id-X
           EXEC CICS STARTBR
               FILE(CON-EMPMAST-File-Name)
               RIDFLD(Employee-Id)
               RESP(CICS-Response-Code)
           END-EXEC
           evaluate EIBRESP
               when DFHRESP(NORMAL)
                   set Browse-In-Progress to true
               when DFHRESP(NOTFND)
                   set File-Is-Empty to true
               when other
                   move 'STARTBR' to ERR-Operation
                   move CON-EMPMAST-File-Name to ERR-File-Name
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
                   perform 9100-Display-and-Return
           end-evaluate
           if File-Is-Empty
               move 1 to Employee-Id
           else
               EXEC CICS READPREV
                   FILE(CON-EMPMAST-File-Name)
                   RIDFLD(Employee-Id)                         
                   INTO(Employee-Master-Record)
                   RESP(CICS-Response-Code)
               END-EXEC
               evaluate EIBRESP
                   when DFHRESP(NORMAL)
                       add 1 to Employee-Id
                   when DFHRESP(NOTFND)
                       move 1 to Employee-Id
                   when other            
                       move 'READPREV' to ERR-Operation
                       move CON-EMPMAST-File-Name to ERR-File-Name
                       move EIBRESP to ERR-EIBRESP
                       move EIBRESP2 to ERR-EIBRESP2
                       move MSG-File-Error to MSG-Out
                       perform 9100-Display-and-Return
               end-evaluate
           end-if
           if Browse-In-Progress
               EXEC CICS ENDBR
                   FILE(CON-EMPMAST-File-Name)
               END-EXEC
               set Browse-In-Progress to false
           end-if            
           EXEC CICS ENQ
               RESOURCE(Employee-Id)
               LENGTH(length of Employee-Id)
               RESP(CICS-Response-Code)
           END-EXEC
           evaluate EIBRESP
               when DFHRESP(NORMAL)
                   set Enqueue-Successful to true
               when DFHRESP(ENQBUSY)
                   set Enqueue-Successful to false
               when other             
                   move 'ENQ' to ERR-Operation
                   move CON-EMPMAST-File-Name to ERR-File-Name
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
                   perform 9100-Display-and-Return
           end-evaluate
           .
       2115-Look-Up-Department-Id.          
      *****************************************************************
      * Get the user's Department Id and fill in the field in the new
      * Employee Master record. 
      *****************************************************************
           perform 6300-Get-Registered-User
           if EIBRESP equal DFHRESP(NORMAL)
               continue
           else
               move 'READ' to ERR-Operation 
               move CON-EREGUSR-File-Name to ERR-File-Name    
               move EIBRESP to ERR-EIBRESP
               move EIBRESP2 to ERR-EIBRESP2
               move MSG-File-Error to MSG-Out
               perform 9100-Display-and-Return
           end-if
           move Employee-Master-Record to APP-Current-Record
           move Registered-Employee-Id to Employee-Id
           perform 6200-Get-Employee-Record
           if EIBRESP equal DFHRESP(NORMAL)
               continue
           else
               move 'READ' to ERR-Operation 
               move CON-EMPMAST-File-Name to ERR-File-Name    
               move EIBRESP to ERR-EIBRESP
               move EIBRESP2 to ERR-EIBRESP2
               move MSG-File-Error to MSG-Out
               perform 9100-Display-and-Return
           end-if
           move Department-Id to Department-Id-Temp
           move APP-Current-Record to Employee-Master-Record
           move Department-Id-Temp to Department-Id
           .
       2120-Write-and-Dequeue.          
      *****************************************************************
      * Write Employee Master record and dequeue the Employee Id. 
      *****************************************************************
           move Employee-Id to Employee-Id-Temp
           move APP-Current-Record to Employee-Master-Record
           move Employee-Id-Temp to Employee-Id
           EXEC CICS WRITE
               FILE(CON-EMPMAST-File-Name)
               RIDFLD(Employee-Id)
               FROM(Employee-Master-Record)
               LENGTH(length of Employee-Master-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   move MSG-Employee-Added to MSG-Out
                   initialize APP-Current-Record
               when DFHRESP(DUPREC)
                   move MSG-Duplicate-Primary-Name to MSG-Out
               when other 
                   move 'WRITE' to ERR-Operation 
                   move CON-EMPMAST-File-Name to ERR-File-Name    
                   move EIBRESP to ERR-EIBRESP
                   move EIBRESP2 to ERR-EIBRESP2
                   move MSG-File-Error to MSG-Out
           end-evaluate
           EXEC CICS DEQ
               RESOURCE(Employee-Id)
               LENGTH(length of Employee-Id)
           END-EXEC
           move FUNCTION CURRENT-DATE to AUD-Timestamp
           move MON-UserId to AUD-UserId
           set AUD-Action-Add to true
           move spaces to AUD-Record-Before
           move Employee-Master-Record to AUD-Record-After
           perform 7900-Write-Audit-Trail
           .    
       3100-Validate-Input.          
      *****************************************************************
      * Check all input fields for validity. 
      *****************************************************************
           move APP-Current-Record to Employee-Master-Record
           perform 3200-Copy-Values-from-Map
           perform 3300-Validate-Input-Data
           move Employee-Master-Record to APP-Current-Record
           .   
       3200-Copy-Values-from-Map.          
      *****************************************************************
      * Copy values from the input map to working storage. 
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
               EXEC CICS BIF DEEDIT
                   FIELD(STDATEI)
                   LENGTH(length of STDATEI)
               END-EXEC
               move STDATEI(3:) to Start-Date
           end-if
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
           move XFER-Container-Data to APP-Container-Data
           .
       6200-Get-Employee-Record.          
      *****************************************************************
      * Get the current user's employee record to pick up the
      * Department Id. 
      *****************************************************************
           EXEC CICS READ
               FILE(CON-EMPMAST-File-Name)
               RIDFLD(Employee-Id)
               INTO(Employee-Master-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           .
       6300-Get-Registered-User.          
      *****************************************************************
      * Read the Registered Users File using the current Employee
      * Application User Id.
      *****************************************************************
           EXEC CICS READ
               FILE(CON-Registered-Users-File-Name)
               RIDFLD(MON-UserId)
               INTO(Registered-User-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           .
           copy EMONITOR.
           copy EXFERPD.
           copy EMESSPD.
           copy EAUDPD.    
       9100-Display-and-Return.          
      *****************************************************************
      * Populate fields in the output map with current values and
      * display the screen. Pseudoconversational return.
      *****************************************************************
           perform 1300-Copy-Record-Fields-to-Map
           if Highlight-Error
               move DFHRED to MESSC
           else 
               move -1 to PRMNML    
           end-if
           set Highlight-Error to false
           move MSG-Out to MESSO
           EXEC CICS SEND
               MAP(CON-Add-Map-Name)
               MAPSET(CON-Add-Mapset-Name)
               FROM(EADDMO)
               FRSET
               ERASE
               FREEKB
               CURSOR
           END-EXEC
           perform 4100-Put-Application-Container
           EXEC CICS RETURN
               TRANSID(CON-Add-TransId)
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
               CONTAINER(CON-APP-Container-Name)
               CHANNEL(CON-APP-Channel-Name)
               NOHANDLE
           END-EXEC
           EXEC CICS XCTL
               PROGRAM(APP-Breadcrumb-Program-Name)
               CHANNEL(CON-Monitor-Channel-Name)
           END-EXEC
           .
           copy ESIGNOFF.