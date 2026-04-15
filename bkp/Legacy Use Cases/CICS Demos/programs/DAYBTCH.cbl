       Identification Division.
       Program-Id. DAYBTCH.
      ****************************************************************
      * Display the line from Monday's Child for today.
      * Batch version.
      ****************************************************************
       Environment Division.
       Input-Output Section.
       File-Control.
           select phrases-file assign to 'PHRASES'
               organization  indexed
               access mode   dynamic
               record key    day-of-week-code
               file status   phrases-file-status.
       Data Division.
       File Section.
       FD  phrases-file.
       01  phrases-record.
           copy phrases.
       Working-Storage Section.
       01  filler.
           05  phrases-file-status        pic x(2).
               88  open-success           value '00'.
       Procedure Division.
           open input phrases-file
           if not open-success
               display 'File Status on open for PHRASES file was'
                       phrases-file-status
               goback
           end-if
           accept day-of-week-code from day-of-week
           read phrases-file
               invalid key
                   display 'No record found in PHRASES file'
                           'for day-of-week' day-of-week-code
                   goback
           end-read
           display day-phrase
           close phrases-file
           goback
           .                                                
