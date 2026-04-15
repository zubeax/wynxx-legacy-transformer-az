      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Application registered users file record layout
      *****************************************************************
           05  Registered-User-Id        pic x(8).
           05  Registered-Password       pic x(8).
           05  Registered-User-Type      pic x(3).
               88  Standard-User         value 'STD'.
               88  Manager-User          value 'MGR'.
               88  Admin-User            value 'ADM'.
           05  Registered-Status         pic x.
               88  ACTIVE                value 'A'.
               88  DELETED               value 'D'.
      * YYYYMMDDHHMMSS
           05  Last-Effective-Date-Time  pic x(14).
           05  filler                    pic x(66).                   