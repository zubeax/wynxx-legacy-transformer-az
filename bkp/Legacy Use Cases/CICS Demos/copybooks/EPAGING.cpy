       1100-Page-Forward.
      *****************************************************************
      * Display the next record based on Employee Id or Primary Name.
      *****************************************************************
           move APP-Current-Record to Employee-Master-Record
           perform 7510-Get-Next-Employee-Record
           perform 1300-Copy-Record-Fields-to-Map
           perform 9100-Display-and-Return
           .
      *****************************************************************
      * Display the previous record based on Employee Id or Primary 
      * Name.
      *****************************************************************
       1200-Page-Backward.
           move APP-Current-Record to Employee-Master-Record
           perform 7540-Get-Prev-Employee-Record
           perform 1300-Copy-Record-Fields-to-Map
           perform 9100-Display-and-Return
           .    
