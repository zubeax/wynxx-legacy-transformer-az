       Identification Division.
       Program-Id. EIBSHOW.
      ****************************************************************
      * Display selected fields from the Execute Interface Block
      ****************************************************************
       Data Division.
       Working-Storage Section.
           copy EIBMAPS.
           copy DFHBMSCA.
           copy DFHAID.
       01  filler.
           05  ws-eibdate          pic 9(7).
           05  filler redefines ws-eibdate.
               10  filler          pic x.
               10  ws-century      pic 9.
               10  ws-year-last-2  pic 9(2).
               10  ws-3-digit-day  pic 9(3).
           05  filler.
               10  ws-base-year    pic 9(4) value 1900.
               10  ws-century-factor pic 9(3) value 100.
               10  ws-leap-adjust  pic 9.
           05  ws-formatted-date.
               10  ws-form-month   pic x(4).
               10  ws-form-day     pic Z9.
               10  filler          pic x(2) value ', '.
               10  ws-form-year    pic 9(4).
           05  ws-month-values.
               10  filler          pic x(3) value 'Jan'.
               10  filler          pic s9(4) packed-decimal value 31.                
               10  filler          pic x(3) value 'Feb'.
               10  filler          pic s9(4) packed-decimal value 59.                
               10  filler          pic x(3) value 'Mar'.
               10  filler          pic s9(4) packed-decimal value 90.                
               10  filler          pic x(3) value 'Apr'.
               10  filler          pic s9(4) packed-decimal value 120.                
               10  filler          pic x(3) value 'May'.
               10  filler          pic s9(4) packed-decimal value 151.                
               10  filler          pic x(3) value 'Jun'.
               10  filler          pic s9(4) packed-decimal value 182.                
               10  filler          pic x(3) value 'Jul'.
               10  filler          pic s9(4) packed-decimal value 212.                
               10  filler          pic x(3) value 'Aug'.
               10  filler          pic s9(4) packed-decimal value 243.                
               10  filler          pic x(3) value 'Sep'.
               10  filler          pic s9(4) packed-decimal value 274.                
               10  filler          pic x(3) value 'Oct'.
               10  filler          pic s9(4) packed-decimal value 304.                
               10  filler          pic x(3) value 'Nov'.
               10  filler          pic s9(4) packed-decimal value 335.                
               10  filler          pic x(3) value 'Dec'.
               10  filler          pic s9(4) packed-decimal value 365.  
           05  ws-month-table redefines ws-month-values.
               10  ws-month-entry occurs 12 times
                                  indexed by month-ix.
                   15  ws-month-abbrev  pic x(3).
                   15  ws-end-day       pic s9(4) packed-decimal.
           05  ws-eibtime          pic 9(7).
           05  ws-time-breakdown redefines ws-eibtime.
               10  filler          pic x.
               10  ws-hour         pic 9(2).
               10  ws-minute       pic x(2).
               10  ws-second       pic x(2).
           05  ws-formatted-time.
               10  ws-hour         pic Z9.
               10  filler          pic x value ':'.
               10  ws-minute       pic x(2).
               10  filler          pic x value ':'.
               10  ws-second       pic x(2).
      * loop control technique pre-COBOL 6.1
           05  ws-loop-control     pic s9(3) packed-decimal.         
       Procedure Division.
           perform 0000-loop
               varying ws-loop-control from 1 by 1
               until ws-loop-control greater than 10
      *
      * COBOL 6.1
      *    perform 0000-loop
      *        until exit
      *
           .
       0000-loop.
           initialize EIBMAPMO
           perform 1000-Format-the-Date
           move ws-formatted-date to EDATEO
           perform 2000-Format-the-Time
           move ws-formatted-time to ETIMEO
           move EIBTRNID to ETRANO
           move EIBTASKN to ETASKO
           move EIBTRMID to ETERMO
           move EIBRCODE to RCODEO
           move EIBERR to ERRXO
           move EIBERRCD to ERRCDO
           move EIBRESP to RESPO
           move EIBRESP2 to RESP2O
           move EIBTRNID to NEXTO
           move DFHBMFSE to NEXTA
           EXEC CICS SEND
               MAP('EIBMAPM')
               MAPSET('EIBMAPS')
               FROM(EIBMAPMO)
               FREEKB
               ERASE
           END-EXEC
           EXEC CICS RECEIVE
               MAP('EIBMAPM')
               MAPSET('EIBMAPS')
               INTO(EIBMAPMI)
           END-EXEC
           if EIBRESP equal DFHRESP(MAPFAIL)
               next sentence
           end-if
           if EIBAID equal DFHPF3
               EXEC SEND CONTROL ERASE END-EXEC
      * pre-COBOL 6.1 
               move 100 to ws-loop-control
      *
      * COBOL 6.1
      *        exit perform
      *
           end-if
           .
       1000-Format-the-Date.
      * Convert from 0CYYDDD to 'Mmm dd, yyyy'
           move EIBDATE to ws-eibdate
           compute ws-form-year =
               ws-year-last-2 +
                   (ws-base-year + 
                       (ws-century * ws-century-factor)
           end-compute
           if ws-3-digit-day less than 28
               move 0 to ws-leap-adjust
           else
               compute ws-leap-adjust =
                   function mod(ws-form-year, 4)
               end-compute 
               if function mod(ws-form-year, 100) equal zero
               and function mod(ws-form-year, 400) not equal zero
                   move 0 to ws-leap-adjust
               end-if
           end-if

           set month-ix to 1
           search ws-month-entry
               varying month-ix
               when ws-3-digit-day
                       less than or equal to ws-end-day(month-ix) + 1
                   move ws-month-abbrev(month-ix) to ws-form-month
                   compute ws-form-day = 
                       ws-end-day(month-ix) - ws-3-digit-day + 1
                   end-compute
           end-search
           .
       2000-Format-the-Time.
           move EIBTIME to ws-eibtime
           move corresponding
               ws-time-breakout to ws-formatted-time
           .                    




           .     