       Identification Division.
       Program-Id. HANCON.
      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL) 
      * Program demonstrating HANDLE CONDITION commands
      *****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
           copy HANMAP.
       01  Parm-Variables.
           05  Parm-Filename       pic x(8).
           05  Parm-Length         pic s9(04) binary.
           05  Parm-Keylength      pic s9(04) binary.
           05  Parm-Ridval         pic x(8).
       01  Default-Values.
           05  Default-Filename    pic x(8).
           05  Default-Length      pic s9(04) binary.
           05  Default-Keylength   pic s9(04) binary.
           05  Default-Ridval      pic x(8).
       01  Record-Area             pic x(100).
       01  Result-to-Display       pic x(50) value spaces.
       01  Container-Info.
           05  Channel-Name        pic x(16) value 'HanconChannel'.
           05  Container-Name      pic x(16) value 'HanconContainer'. 
       01  Container-Data.
           05  Previous-Filename   pic x(8).
           05  Previous-Length     pic 9(4).
           05  Previous-Keylength  pic 9(4).
           05  Previous-Ridval     pic x(8).
       01  BMS-Values.
           05  Map-Name            pic x(8) value 'HANM'.
           05  Mapset-Name         pic x(8) value 'HANMAP'.
       01  CICS-Response           pic s9(9) binary.
       Procedure Division.
           EXEC CICS GET CONTAINER(Container-Name)
               CHANNEL(Channel-Name)
               INTO(Container-Data)
               RESP(CICS-Response)
           END-EXEC
           if CICS-Response equal DFHRESP(CHANNELERR)
               perform 0000-First-Time
           else
               perform 1000-Process-Input
           end-if
           .
       0000-First-Time.
           perform 0100-Clear-Map-Data
           perform 0200-Initialize-Local-Data
           perform 9000-Display-Output
           .
       0100-Clear-Map-Data.
           move low-values to HANMO
           .
       0200-Initialize-Local-Data.
           move Default-Values to Parm-Variables
           initialize Container-Data
           .
       1000-Process-Input.    
           perform 1100-Receive-Terminal-Input
           perform 1200-Handle-Condition-Commands
           perform 1300-Execute-Read-Command
           perform 9000-Display-Output
           .
       1100-Receive-Terminal-Input.
           EXEC CICS RECEIVE
               MAP(Map-Name)
               MAPSET(Mapset-Name)
               INTO(HANMI)
           END-EXEC
           evaluate EIBAID
               when DFHPF3
               when DFHPF12
                   perform 9900-end
               when other
                   continue
           end-evaluate
           
           if FILENMI > spaces
               move FILENMI to Parm-Filename
           else
               if Previous-Filename > spaces
                   move Previous-Filename to Parm-Filename
               else
                   move Default-Filename to Parm-Filename
               end-if
           end-if
           
           if LENVALI > spaces
               move LENVALI to Parm-Length
           else
               if Previous-Length > spaces
                   move Previous-Length to Parm-Length
               else
                   move Default-Length to Parm-Length
               end-if
           end-if
           
           if KEYLENI > spaces
               move KEYLENI to Parm-Keylength
           else
               if Previous-Keylength > spaces
                   move Previous-Keylength to Parm-Keylength
               else
                   move Default-Keylength to Parm-Keylength
               end-if
           end-if
           
           if RIDVALI > spaces
               move RIDVALI to Parm-Filename
           else
               if Previous-Ridval > spaces
                   move Previous-Ridval to Parm-Ridval
               else
                   move Default-Ridval to Parm-Ridval
               end-if
           end-if
           .
       1200-Handle-Condition-Commands.
           EXEC CICS HANDLE CONDITION
               FILENOTFOUND(8100-FileNotFound)
               NOTFND(8200-RecordNotFound)
               INVREQ(8300-InvalidRequest)
               ERROR(8900-UnexpectedError)
           END-EXEC
           EXEC CICS IGNORE CONDITION
               LENGERR
           END-EXEC
           .
       1300-Execute-Read-Command.
           move spaces to RESULTO
           EXEC CICS READ
               INTO(Record-Area)
               FILE(Parm-Filename)
               LENGTH(Parm-Length)
               RIDFLD(Parm-Ridval)
               KEYLENGTH(Parm-Keylength)                
           END-EXEC
           move 'Successful read' to Result-to-Display
           .
       8100-FileNotFound.
           move '8100-FileNotFound' 
               to Result-to-Display
           perform 9000-Display-Output
           .
       8200-RecordNotFound.
           move '8200-RecordNotFound'
               to Result-to-Display
           perform 9000-Display-Output
           .
       8300-InvalidRequest.
           move '8300-Invalid-Request'            
               to Result-to-Display	
           perform 9000-Display-Output
           .
       8900-UnexpectedError.
           move '8900-UndexpectedError'    
               to Result-to-Display	
           perform 9000-Display-Output
           .
       9000-Display-Output.
           move Parm-Filename to FILENMO Previous-Filename
           move Parm-Length to LENVALO Previous-Length
           move Parm-Ridval to RIDVALO Previous-Ridval
           move Parm-Keylength to KEYLENO Previous-Keylength
           move Result-to-Display to RESULTO
           EXEC CICS PUT CONTAINER(Container-Name)
               CHANNEL(Channel-Name)
               FROM(Container-Data)
           END-EXEC
           EXEC CICS SEND
               MAP(Map-Name)
               MAPSET(Mapset-Name)
               FROM(HANMO)
               ERASE
           END-EXEC
           EXEC CICS RETURN
               TRANSID(EIBTRNID)
               CHANNEL(Channel-Name)
           END-EXEC
           .
       9900-End.
           EXEC CICS SEND CONTROL
               ERASE
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .                    

















