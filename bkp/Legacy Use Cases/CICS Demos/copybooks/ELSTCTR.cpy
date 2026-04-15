      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Container for passing record selection and filtering criteria
      *****************************************************************
           05  LST-User-Category                 pic x(3).
           05  LST-Program-Name                  pic x(8).
           05  LST-Current-Page-Number           pic 9(6).
           05  LST-Browse-Forward                pic x.
               88  LST-Browse-Forward-Enabled    value space.
               88  LST-Browse-Forward-Disabled   value 'X'.
           05  LST-Browse-Backward               pic x.
               88  LST-Browse-Backward-Enabled   value space.
               88  LST-Browse-Backward-Disabled  value 'X'.
           05  LST-Filters.
               10  filler                        pic x value space.
                   88  LST-Filters-Are-Set             value 'X'.
                   88  LST-No-Filters-Are-Set          value space.
               10  LST-Selection-Key-Type        pic x value '1'.
                   88  LST-Select-by-Employee-Id       value '1'.
                   88  LST-Select-by-Primary-Name      value '2'.
               10  LST-Selection-Key-Value       pic x(30).
               10  LST-Incl-Department-Id-Filters.
                   15  LST-Incl-Department-Id 
                   occurs 4 indexed by Incl-Dept-Index pic x(8).
               10  LST-Excl-Department-Id-Filters.
                   15  LST-Excl-Department-Id 
                   occurs 4 indexed by Excl-Dept-Index pic x(8).
               10  LST-Employed-After-Date       pic x(8).
               10  LST-Employed-Before-Date      pic x(8).
           05  LST-Selected-Record-Index         pic s9(4) binary.
           05  LST-Current-Record-Area.
               10  LST-Current-Record 
                   occurs 16 times
                   indexed by LST-Record-Index   pic x(251).    
