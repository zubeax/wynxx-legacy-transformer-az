      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Application Sign-on Rules File Record Layout
      *****************************************************************
           05  SO-Max-Sign-On-Attempts          pic 99.
      * minutes
           05  SO-Lockout-Expiration-Interval   pic 9(4).
      * minutes
           05  SO-Inactivity-Signout-Interval   pic 9(4).          