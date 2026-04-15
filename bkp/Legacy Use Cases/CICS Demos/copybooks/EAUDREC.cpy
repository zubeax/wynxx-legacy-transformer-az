      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Application audit trail record layout.
      *****************************************************************
           05  AUD-Timestamp            pic x(21).
           05  AUD-UserId               pic x(8).
           05  AUD-Action               pic x.
               88  AUD-Action-Add       value 'A'.
               88  AUD-Action-Update    value 'U'.
               88  AUD-Action-Delete    value 'D'.
           05  AUD-Record-Before        pic x(251).
           05  AUD-Record-After         pic x(251).    