      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)      
      * Employee Application User Activity Temp Storage Queue Layout
      *****************************************************************
           05  UACT-UserId                    pic x(8).
           05  UACT-User-Category             pic x(3).      
               88  UACT-User-Category-Not-Set value spaces.
               88  UACT-Standard-User         value 'STD'.	
               88  UACT-Manager-User          value 'MGR'.
               88  UACT-Admin-User            value 'ADM'.
           05  UACT-Signed-On-Status            pic x.
               88  UACT-Signing-On            value 'O'.
               88  UACT-Locked-Out            value 'L'.
               88  UACT-Signed-On             value 'S'.
           05  UACT-Retry-Number              pic 99.
           05  UACT-Last-Activity-Time        pic x(14).        