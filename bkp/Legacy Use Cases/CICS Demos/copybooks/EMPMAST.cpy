      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Employee Master File record layout.
      *****************************************************************
       05  Employee-Id-X.
           10  Employee-Id                    pic 9(8).
       05  Full-Name                          pic x(79).
       05  Primary-Name                       pic x(38).
       05  Honorific                          pic x(8).
       05  Short-Name                         pic x(38).
       05  Job-Title                          pic x(38).
       05  Department-Id-X.
           10  Department-Id                  pic 9(8).
       05  Start-Date                         pic x(8).
       05  End-Date                           pic x(8).
       05  Appraisal-Date                     pic x(8).
       05  Appraisal-Result                   pic x.
           88  Exceeds-Expectations           value 'E'.
           88  Meets-Expectations             value 'M'.
           88  Uh-Oh                          value 'U'.
       05  Delete-Flag                        pic x.
           88  Deleted                        value 'D'.
           88  Active                         value 'A'.
       05  Delete-Date                        pic x(8).        