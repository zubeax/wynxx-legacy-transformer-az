       Identification Division.
       Program-Id. ELISTP.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * List Employees program
      *****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
           copy DFHBMSCA.
           copy ELSTMAP2.
       01  W-Subscript pic 9(5).    
       01  Application-Constants.
           copy ECONST.
       01  MON-Container-Data.
           copy EMONCTR.
       01  LST-Container-Data.
           copy ELSTCTR.
       01  Employee-Master-Record.
           copy EMPMAST.
       01  Previous-Screen-Records            pic x(4016).    
       01  Max-Rows-per-Page                  pic s9(4) binary
                                              value +16.    
       01  CICS-Response-Code                 pic s9(9) binary.
       01  Transaction-Id-to-Return           pic x(4).
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
       01  Container-Transfer-Fields.
           05  XFER-Channel-Name              pic x(16).
           05  XFER-Container-Name            pic x(16).
           05  XFER-Container-Data            pic x(5000).
           05  XFER-Container-Data-Length     pic s9(9) binary.
       01  Map-Work-Areas.
           05  Map-Name-to-Transfer           pic x(8).
           05  Terminal-Data-Area             pic x(1920).
       01  Populating-Filters-List.
           05  Label-Primary-Name             pic x(4)
                                              value 'Name'.
           05  Label-Dept                     pic x(4).
           05  Label-After-Date               pic x(5)
                                              value 'After'.
           05  Label-Before-Date              pic x(6)
                                              value 'Before'.
           05  Filters-Delimiter              pic x value '/'.
           05  Label-to-Append                pic x(20).
           05  Value-to-Append                pic x(30).
           05  Colon-Separator                pic x(2)
                                              value ': '.
           05  Filters-List                   pic x(69).
       01  File-Browsing-Fields.
           05  File-Name-to-Read              pic x(8).
           05  Record-Id-Field.
               10  RID-Employee-Id            pic 9(8).
               10  filler                     pic x(30).    
       01  Display-Messages.
           05  MSG-Out                        pic x(79).
           05  MSG-No-Filters-Are-Set         pic x(8) value '(None)'.
           05  MSG-Undefined-PF-Key           pic x(16)
               value 'Undefined PF key'.
           05  MSG-Initial-Prompt.
               10  filler                     pic x(79)
                   value 'Provide filter criteria and press Enter, or om
      -                  'it criteria to see all records.'.
           05  MSG-Top-of-File                pic x(11)
               value 'Top of file'.
           05  MSG-No-More-Records            pic x(26)
               value 'No more records to display'.
           05  filler                         pic x value 'N'.
               88  Highlight-Error value 'Y' false 'N'.
           05  MSG-Container-Error.
               10  filler                     pic x(14)
               value 'GET CONTAINER('.
               10  ERR-Container-Name         pic x(16).
               10  filler                     pic x(10).
               10  ERR-Channel-Name           pic x(16).
               10  filler                     pic x(2) value ') '.
               10  ERR-Container-EIBRESP      pic 9(8).
               10  filler                     pic x value space.
               10  ERR-Container-EIBRESP2     pic 9(8).
           05  MSG-File-Error.
               10  ERR-Operation              pic x(12).
               10  filler                     pic x(6)
                   value ' file '.
               10  ERR-File-Name              pic x(8).
               10  filler                     pic x value space.
               10  ERR-EIBRESP                pic 9(8).
               10  filler                     pic x value space.
               10  ERR-EIBRESP2               pic 9(8).        
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
           perform 4400-Get-List-Container
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)  
                   if LST-Program-Name equal CON-List-Program-Name
                       perform 1000-Process-User-Input
                   else
                       perform 0000-First-Time
                   end-if        
               when DFHRESP(CHANNELERR)
               when DFHRESP(CONTAINERERR)
                   perform 0000-First-Time
               when other
                   perform 8100-Container-Error
           end-evaluate
           .                
       0000-First-Time.
      *****************************************************************
      * First entry into this program in a conversation.
      *****************************************************************
           perform 6100-Check-User-Activity
           perform 0100-Initialize-List-Container
           perform 0300-Initialize-Filters-Map
           move CON-Filters-TransId to Transaction-Id-to-Return
           perform 9500-Solicit-Filters
           .
       0100-Initialize-List-Container.         
      *****************************************************************
      * Prepare the List Container work area for first-time use.
      *****************************************************************
           initialize LST-Container-Data
           move CON-List-Program-Name to LST-Program-Name
           move zero to LST-Current-Page-Number
           .
       0300-Initialize-Filters-Map.    
      *****************************************************************
      * Prepare the Filters map for initial display.
      *****************************************************************
           move low-values to EFILMO
           .
       1000-Process-User-Input.    
      *****************************************************************
      * Route control to the appropriate paragraph based on transid.
      *****************************************************************
           perform 6100-Check-User-Activity
           if EIBTRNID equal CON-List-TransId
               perform 1200-Process-List-Input
           end-if
           perform 1100-Process-Filters-Input
           .    
       1100-Process-Filters-Input.    
      *****************************************************************
      * Take the action associated with the Attention Identifier key
      * transmitted with the Filters map.
      *****************************************************************
           move zero to LST-Current-Page-Number
           move CON-Filters-Map-Name to Map-Name-to-Transfer
           perform 1800-Receive-Map
           move Terminal-Data-Area to EFILMI
           evaluate EIBAID
               when DFHENTER
                   move CON-List-TransId to Transaction-Id-to-Return
                   perform 1700-Set-Filters
                   perform 1300-Page-Forward
               when DFHPF3
               when DFHPF12
                   perform 9700-Exit
               when DFHPF10
                   perform 9900-Sign-User-Off
               when other
                   move 'Undefined PF key' to MSG-Out
           end-evaluate
           perform 9200-Display-Filters-Return
           .                        
       1200-Process-List-Input.    
      *****************************************************************
      * Take the action associated with the Attention Identifier key
      * transmitted with the List map.
      *****************************************************************
           move CON-List-Map-Name to Map-Name-to-Transfer
           perform 1800-Receive-Map
           move Terminal-Data-Area to ELSTMI
           evaluate EIBAID
               when DFHENTER
                   perform 1500-Display-Details
               when DFHPF3
                   perform 7000-Copy-Filters-to-Map
                   perform 0100-Initialize-List-Container
                   perform 9500-Solicit-Filters
               when DFHPF12
                   perform 7050-Clear-Filters
                   perform 9500-Solicit-Filters
               when DFHPF10
                   perform 9800-Sign-User-Off
               when DFHPF8
                   if LST-Browse-Forward-Enabled
                       perform 1300-Page-Forward
                   else
                       move MSG-No-More-Records to MSG-Out
                       set Highlight-Error to true    
                   end-if
               when DFHPF7
                   if LST-Browse-Backward-Enabled
                       perform 1400-Page-Backward
                   else
                       move MSG-Top-of-File to MSG-Out    
                   end-if
               when other
                   move MSG-Undefined-PF-Key to MSG-Out
           end-evaluate
           perform 9100-Display-List-and-Return
           .
       1300-Page-Forward.                                        
      *****************************************************************
      * Read the next set of records from the Employee Master file.
      *****************************************************************
           if LST-Select-by-Primary-Name
               if LST-Selection-Key-Value greater than spaces
                   move LST-Selection-Key-Value to Filter-Value
                   perform 7720-Locate-Wildcard
                   if Filter-Index greater or equal
                           length of LST-Selection-Key-Value
                       move Filter-Value to Primary-Name
                                            Primary-Name-Key-Value
                       move length of Primary-Name
                                         to Primary-Name-Key-Length
                   else
                       move Filter-Value(1:Filter-Index)
                                         to Primary-Name
                                            Primary-Name-Key-Value
                       move Filtter-Index to Primary-Name-Key-Length
                   end-if
               else                                                                            
                   move LST-Current-Record(16) to Employee-Master-Record
                   move high-values to Primary-Name(37:)
               end-if
           else    
               add 1 to Employee-Id
           end-if    
           move spaces to MSG-Out
           perform 7500-Browse-Forward
           perform 4200-Put-List-Container
           perform 9100-Display-List-and-Return
           .
       1400-Page-Backward.
      *****************************************************************
      * Read the previous set of records from the Employee Master file.
      *****************************************************************
           move spaces to MSG-Out
           set LST-Browse-Forward-Enabled to true
           if LST-Current-Page-Number greater than 1
               subtract 1 from LST-Current-Page-Number
           else
               move MSG-Top-of-File to MSG-Out
               set LST-Browse-Backward-Disabled to true
               perform 9100-Display-List-and-Return
           end-if
           move LST-Current-Record(1) to Employee-Master-Record
           if LST-Select-by-Primary-Name
               move CON-EMPMAST-Primary-Name-Path to File-Name-to-Read
               if Primary-Name equal spaces
                   move high-values to Primary-Name
               end-if
               move Primary-Name to Record-Id-Field
           else        
               if Employee-Id-X equal spaces
                   move high-values to Employee-Id-X
               else
                   subtract 1 from Employee-Id
                   move spaces to Record-Id-Field
                   move Employee-Id to RID-Employee-Id
               end-if
           end-if                
           perform 7600-Browse-Backward
           perform 4200-Put-List-Container
           perform 9100-Display-List-and-Return
           .
       1500-Display-Details.
      *****************************************************************
      * Transfer to detail display program.
      *****************************************************************
           move zero to LST-Selected-Record-Index
           perform varying W-Subscript from 1 by 1
                   until W-Subscript greater than 16
               move SELCTF(W-Subscript) to DFHBMFLG
               if DFHCURSR
                   move W-Subscript to LST-Selected-Record-Index
                   move 17 to W-Subscript
               end-if
           end-perform
           perform 4200-Put-List-Container
           move CON-View-Program-Name to XCTL-Target-Program-Name
           perform 7300-Transfer-Control
           .
       1600-Copy-Container-to-Map.
      *****************************************************************
      * Copy the current set of records to the output map.
      *****************************************************************
           move low-values to ELSTMO
           move EIBTRNID to TRANIDO
           move LST-Current-Page-Number to PAGENO
           if LST-No-Filters-Are-Set
               move MSG-No-Filters-Are-Set to FLTRSO
           end-if
           move MSG-Out to MESSO

           perform varying LST-Record-Index from 1 by 1
                   until LST-Record-Index
                       greater than Max-Rows-per-Page
               move LST-Current-Record(LST-Record-Index)
                   to Employee-Master-Record
               set LineO-Index to LST-Record-Index
               move Employee-Id to EMPIDO(LineO-Index)
               move Primary_Name to PRMNMO(LineO-Index)
               move Job-Title to JOBTLO(LineO-Index)
               move Department-Id to DPTIDO(LineO-Index)
           end-perform   

           move Filters-Delimiter to Filters-List
           if LST-Filters-Are-Set
               if LST-Select-by-Primary-Name
               and LST-Selection-Key-Value greater than spaces
                   move Label-Primary-Name to Label-to-Append
                   move LST-Selection-Key-Value to Value-to-Append
                   perform 1620-Append-Value-to-Filters
               end-if
               move 'Incl' to Label-Dept
               move ': ' to Colon-Separator
               perform varying Incl-Dept-Index from 1 by 1
                           until Incl-Dept-Index greater than 4
                   if LST-Incl-Department-Id(Incl-Dept-Index)
                           greater than spaces
                       move Label-Dept to Label-to-Append
                       move LST-Incl-Department-Id(Incl-Dept-Index)
                           to Value-to-Append
                       perform 1620-Append-Value-to-Filters
                       move spaces to Label-Dept
                       move spaces to Colon-Separator
                   end-if
               end-perform
               move 'Excl' to Label-Dept
               move ': ' to Colon-Separator
               perform varying Excl-Dept-Index from 1 by 1
                           until Excl-Dept-Index greater than 4
                   if LST-Excl-Department-Id(Excl-Dept-Index)
                           greater than spaces
                       move Label-Dept to Label-to-Append
                       move LST-Excl-Department-Id(Excl-Dept-Index)
                           to Value-to-Append
                       perform 1620-Append-Value-to-Filters
                       move spaces to Label-Dept
                       move spaces to Colon-Separator
                   end-if
               end-perform
               move ': ' to Colon-Separator
               if LST-Employed-After-Date greater than spaces
                   move Label-After-Date to Label-to-Append
                   move LST-Employed-After-Date to Value-to-Append
                   perform 1620-Append-Value-to-Filters
               end-if
               if LST-Employed-Before-Date greater than spaces
                   move Label-Before-Date to Label-to-Append
                   move LST-Employed-Before-Date to Value-to-Append
                   perform 1620-Append-Value-to-Filters
               end-if
           end-if    
           inspect Filters-List
               replacing first Filters-Delimiter by space
           if Filters-List greater than spaces
               move Filters-List to FLTRSO
           else
               move MSG-No-Filters-Are-Set to FLTRSO
           end-if                    
           .
       1620-Append-Value-to-Filters.
      *****************************************************************
      * Append a value to the list of filters to display on the List
      * map.
      *****************************************************************
           string Filters-List      delimited by Filters-Delimiter
                  Label-to-Append   delimited by space
                  Colon-Separator   delimited by size
                  Value-to-Append   delimited by space
                  space             delimited by size
                  Filters-Delimiter delimited by size
              into Filters-List
           end-string       
           .
       1700-Set-Filters.
      *****************************************************************
      * Set the filters selected by the user.
      *****************************************************************
           move zero to Employee-Id
           initialize LST-Filters

           if KEYSELI equal '1'
           or KEYSELI equal '2'
               move KEYSELI to LST-Selection-Key-Type
           else
               set LST-Select-by-Employee-Id to true
           end-if
           
           if MATCHI greater than spaces
               set LST-Filters-Are-Set to true
               move MATCHI to LST-Selection-Key-Value
           end-if
           
           perform varying Filter-Index from 1 by 1
                   until Filter-Index greater than 4
               if DPTINCLI(Filter-Index) greater than spaces
                   set LST-Filters-Are-Set to true
                   set Incl-Dept-Index to Filter-Index
                   move DPTINCLI(Filter-Index)
                       to LST-Incl-Department-Id(Incl-Dept-Index)
               end-if
           end-perform

           if EDATEAI greater than spaces
               set LST-Filters-Are-Set to true
               move EDATEAI to LST-Employed-After-Date
           end-if
           
           if EDATEBI greater than spaces
               set LST-Filters-Are-Set to true
               move EDATEBI to LST-Employed-Before-Date
           end-if
           .                                    
       1800-Receive-Map.
      *****************************************************************
      * Receive mapped data from the terminal.
      *****************************************************************
           EXEC CICS RECEIVE 
               MAP(Map-Name-to-Transfer)
               MAPSET(CON-List-Mapset-Name)
               INTO(Terminal-Data-Area)
           END-EXEC
           .
       4200-Put-List-Container.
      *****************************************************************
      * Copy working storage data to the List container.
      *****************************************************************
           move CON-List-Container-Name to XFER-Container-Name
           move CON-List-Channel-Name to XFER-Channel-Name
           move LST-Container-Data to XFER-Container-Data
           move length of LST-Container-Data
               to XFER-Container-Data-Length
           perform 7100-Put-Container
           .
        4400-Get-List-Container.
      *****************************************************************
      * Copy List container data to working storage.
      *****************************************************************
           move CON-List-Container-Name to XFER-Container-Name
           move CON-List-Channel-Name to XFER-Channel-Name
           perform 7200-Get-Container
           move XFER-Container-Data to LST-Container-Data
           .  
           copy EMONITOR.          
       7000-Copy-Filters-to-Map.
      *****************************************************************
      * Copy filter settings from the container to the Filters map.
      *****************************************************************
           move LST-Selection-Key-Type to KEYSELO
           move LST-Selection-Key-Value to MATCHO
           move LST-Incl-Department-Id(1) to DPTINCLO(1)
           move LST-Incl-Department-Id(2) to DPTINCLO(2)
           move LST-Incl-Department-Id(3) to DPTINCLO(3)
           move LST-Incl-Department-Id(4) to DPTINCLO(4)
           move LST-Excl-Department-Id(1) to DPTEXCLO(1)
           move LST-Excl-Department-Id(2) to DPTEXCLO(2)
           move LST-Excl-Department-Id(3) to DPTEXCLO(3)
           move LST-Excl-Department-Id(4) to DPTEXCLO(4)
           move LST-Employed-After-Date to EDATEAO
           move LST-Employed-Before-Date to EDATEBO
           move DFHBMFSE to KEYSELA MATCHA
                            DPTINCLA(1) DPTINCLA(2)
                            DPTINCLA(3) DPTINCLA(4)
                            DPTEXCLA(1) DPTEXCLA(2)
                            DPTEXCLA(3) DPTEXCLA(4)
                            EDATEAA EDATEBA
           .            
       7050-Clear-Filters.
      *****************************************************************
      * Reset search/filter values.
      *****************************************************************
           perform 0100-Initialize-List-Container
           move low-values to EFILMO
           .
       7100-Put-Container.
      *****************************************************************
      * Copy working storage data to a container.
      *****************************************************************
           EXEC CICS PUT CONTAINER(XFER-Container-Name)
               CHANNEL(XFER-Channel-Name)
               FROM(XFER-Container-Data)
               FLENGTH(XFER-Container-Data-Length)
               RESP(CICS-Response-Code)
           END-EXEC
           if CICS-Response-Code equal DFHRESP(NORMAL)
               continue
           else
               perform 8100-Container-Error
           end-if
           .            
       7200-Get-Container.
      *****************************************************************
      * Copy data from a container to working storage.
      *****************************************************************
           EXEC CICS GET CONTAINER(XFER-Container-Name)
               CHANNEL(XFER-Channel-Name)
               INTO(XFER-Container-Data)
               RESP(CICS-Response-Code)
           END-EXEC
           .            
       7300-Transfer-Control.
      *****************************************************************
      * Common XCTL command.
      *****************************************************************
           EXEC CICS XCTL
               PROGRAM(XCTL-Target-Program-Name)
               CHANNEL(CON-List-Channel-Name)
           END-EXEC    
           .    
       7500-Browse-Forward.
      *****************************************************************
      * Browse forward starting with the current value of Employee-Id.
      *****************************************************************
           perform 7800-Start-Browse
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   continue
               when DFHRESP(NOTFND)
                   move MSG-No-More-Records to MSG-Out
                   set Highlight-Error to true
                   set LST-Browse-Forward-Disabled to true
                   perform 9100-Display-and-Return
               when other
                   move 'STARTBR' to ERR-Operation
                   perform 8200-File-Error
           end-evaluate                    
           add 1 to LST-Current-Page-Number
           move spaces to LST-Current-Record-Area
           set LST-Record-Index to 1
           perform 7520-Read-Next-Record
               until LST-Record-Index greater than Max-Rows-per-Page
           perform 7820-End-Browse
           if LST-Current-Record(1) equal spaces
               set LST-Browse-Forward-Disabled to true
               move Previous-Screen-Records to LST-Current-Record-Area
               subtract 1 from LST-Current-Page-Number
           else
               move LST-Current-Record-Area to Previous-Screen-Records
           end-if        
           .
       7520-Read-Next-Record.
      *****************************************************************
      * Read the next logical record from the Employee Master file.
      *****************************************************************
           EXEC CICS READNEXT
               FILE(File-Name-to-Read)
               RIDFLD(Record-Id-Field)
               INTO(Employee-Master-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   if LST-Select-by-Primary-Name
                       if LST-Selection-Key-Value greater than spaces
                           if LST-Selection-Key-Value
                           (1:Primary-Name-Key-Length)
                                   not equal Primary-Name
                                         (1:Primary-Name-Key-Length)
                               set LST-Record-Index to Max-Rows-per-Page
                               set LST-Record-Index up by 1          
                            end-if
                        end-if
                    end-if        
                   perform 7700-Apply-Filters
                   if Filters-Match
                       move Employee-Master-Record
                           to LST-Current-Record(LST-Record-Index)
                       set LST-Record-Index up by 1
                   end-if        
               when DFHRESP(ENDFILE)
                   set LST-Record-Index to Max-Rows-per-Page
                   set LST-Record-Index up by 1
                   set LST-Browse-Forward-Disabled to true
               when other
                   move 'READNEXT' to ERR-Operation
                   perform 8200-File-Error
           end-evaluate                        
           .        
       7600-Browse-Backward.
      *****************************************************************
      * Browse backward starting with the first record key in the
      * current set of records, or end of file if there's no record.
      *****************************************************************
           perform 7800-Start-Browse
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   continue
               when other
                   move 'STARTBR' to ERR-Operation
                   perform 8200-File-Error
           end-evaluate
           move spaces to LST-Current-Record-Area
           if LST-Select-by-Primary-Name
               perform 7620-Read-Previous-Record
           end-if    
           set LST-Record-Index to Max-Rows-per-Page
           perform 7620-Read-Previous-Record
               until LST-Record-Index less than 1
               or LST-Browse-Backward-Disabled
           perform 7820-End-Browse
           .        
       7620-Read-Previous-Record.
      *****************************************************************
      * Read the previous logical record from the Employee Master file.
      *****************************************************************
           EXEC CICS READPREV
               FILE(File-Name-to-Read)
               RIDFLD(Record-Id-Field)
               INTO(Employee-Master-Record)
               RESP(CICS-Response-Code)
           END-EXEC
           evaluate CICS-Response-Code
               when DFHRESP(NORMAL)
                   perform 7700-Apply-Filters
                   if Filters-Match
                       move Employee-Master-Record
                           to LST-Current-Record(LST-Record-Index)
                       set LST-Record-Index down by 1
                   end-if
               when DFHRESP(ENDFILE)
                   set LST-Record-Index to 1
                   set LST-Browse-Backward-Disabled to true
               when other
                   move 'READPREV' to ERR-Operation
                   perform 8200-File-Error
           end-evaluate                                
           .
           copy EFILTERS.
       7800-Start-Browse.
      *****************************************************************
      * Initiate browsing on the Employee Master file.
      *****************************************************************
           if LST-Select-by-Primary-Name
               move CON-EMPMAST-Primary-Name-Path
                   to File-Name-to-Read
               move Primary-Name to Record-Id-Field
           else
               move CON-EMPMAST-File-Name
                   to File-Name-to-Read
               move spaces to Record-Id-Field
               move Employee-Id to RID-Employee-Id
           end-if
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
       8100-Container-Error.
      *****************************************************************
      * Display response codes after unexpected condition when
      * getting a container.
      *****************************************************************
           move XFER-Channel-Name to ERR-Channel-Name
           move XFER-Container-Name to ERR-Container-Name
           move EIBRESP to ERR-Container-EIBRESP
           move EIBRESP2 to ERR-Container-EIBRESP2
           move MSG-Container-Error to MESSO
           perform 9100-Display-and-Return
           .
       8200-File-Error.
      *****************************************************************
      * Display response codes after unexpected condition when
      * performing a File Control operation.
      *****************************************************************
           move CON-EMPMAST-File-Name
               to ERR-File-Name
           move EIBRESP to ERR-EIBRESP
           move EIBRESP2 to ERR-EIBRESP2
           move MSG-File-Error to MSG-Out
           set Highlight-Error to true
           .
       9100-Display-List-and-Return.    
      *****************************************************************
      * Populate fields in the List map with current values and
      * display the screen. Pseudoconversational return.
      *****************************************************************
           perform 1600-Copy-Container-to-Map
           if LST-Browse-Forward-Disabled
               move DFHPROTN to HLPPF8A
           end-if
           if LST-Current-Page-Number equal 1
               move DFHPROTN to HLPPF7A
           end-if    
           if Highlight-Error
               move DFHRED to MESSC
           end-if
           set Highlight-Error to false
           move CON-List-Map-Name to Map-Name-to-Transfer
           move CON-List-TransId to Transaction-Id-to-Return
           move ELSTMO to Terminal-Data-Area
           perform 9300-Display-Map-and-Return
           .
       9200-Display-Filters-Return.    
      *****************************************************************
      * Populate fields in the Filters map with current values and
      * display the screen. Pseudoconversational return.
      *****************************************************************
           move CON-Filters-Map-Name to Map-Name-to-Transfer
           move Transaction-Id-to-Return to TRANFLO
           move MSG-Out to MESSFLO
           move EFILMO to Terminal-Data-Area
           perform 9300-Display-Map-and-Return
           .
       9300-Display-Map-and-Return.    
           EXEC CICS SEND
               MAP(Map-Name-to-Transfer)
               MAPSET(CON-List-Mapset-Name)
               FROM(Terminal-Data-Area)
               ERASE
               FREEKB
           END-EXEC
           EXEC CICS RETURN
               TRANSID(Transaction-Id-to-Return)
               CHANNEL(CON-List-Channel-Name)
           END-EXEC
           .
       9500-Solicit-Filters.    
      *****************************************************************
      * Display filter input screen to get user's filter values.
      *****************************************************************
           perform 4200-Put-List-Container
           move MSG-Initial-Prompt to MSG-Out
           move CON-Filters-TransId to Transaction-Id-to-Return
           perform 9200-Display-Filters-Return
          .
       9700-Exit.
      *****************************************************************
      * Transfer control to the application menu.
      *****************************************************************
           EXEC CICS DELETE
               CONTAINER(CON-List-Container-Name)
               CHANNEL(CON-List-Channel-Name)
               NOHANDLE
           END-EXEC
           move CON-Menu-Program-Name to XCTL-Target-Program-Name
           perform 7300-Transfer-Control
           .
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