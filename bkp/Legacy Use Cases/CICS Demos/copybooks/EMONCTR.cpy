      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Application User Activity Monitor Container layout
      *****************************************************************
           05  MON-Request.
               10  MON-Linking-Program               pic x(8).
               10  MON-UserId                        pic x(8).
               10  MON-User-Category                 pic x(3).
                   88  MON-Category-Not-Set          value spaces.
                   88  MON-Category-Administrator    value 'ADM'.
                   88  MON-Category-Manager          value 'MGR'.
                   88  MON-Category-Standard         value 'STD'.
               10  MON-User-Action                   pic x.
                   88  MON-Action-Notify             value 'N'.
                   88  MON-Action-Sign-On            value 'O'.
                   88  MON-Action-Sign-Off           value 'F'.
                   88  MON-Action-App-Function       value 'A'.
           05  MON-Response.
               10  MON-Error-Flag                    pic x.
                   88  MON-Processing-Error          value 'E'.
                   88  MON-Terminate-Now             value 'T'..
               10  MON-Signed-On-Status              pic x.
                   88  MON-Status-Not-Set            value ' '.
                   88  MON-Status-Locked-Out         value 'L'.
                   88  MON-Status-Signed-On          value 'S'.
                   88  MON-Status-Signing-On         value 'O'.
               10  MON-Message                 pic x(79).                       