       Identification Division.
       Program-Id. DAYCICS.
      ****************************************************************
      * Display the line from Monday's Child for today.
      * CICS version.
      ****************************************************************
       Data Division.
       Working-Storage Section.
       01  phrases-record.
           copy phrases.
       Procedure Division.
           accept day-of-week-code from day-of-week
           EXEC CICS READ FILE('PHRASES')
               INTO(phrases-record)
               RIDFLD(day-of-week-code)
           END-EXEC
           EXEC CICS SEND
               FROM(day-phrase)
               LENGTH(length of day-phrase)
               ERASE
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .             