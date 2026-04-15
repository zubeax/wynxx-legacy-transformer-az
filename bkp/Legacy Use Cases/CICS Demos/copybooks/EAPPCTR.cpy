      *****************************************************************
      * Pluralsight CICS Application Programming Fundamentals (COBOL)
      * Generic container for application data
      *****************************************************************
           05  APP-Breadcrumb-Program-Name    pic x(8).
           05  APP-Indicators.
               10  filler                     pic x value space.
                   88  APP-List-Container-Exists
                                          value 'X' false space.
               10  APP-Read-by-Key            pic x value space.
                   88  APP-Read-by-Employee-Id      value space.
                   88  APP-Read-by-Primary-Name     value 'P'.
           05  APP-Browse-Forward             pic x.
               88  APP-Browse-Forward-Enabled   value space.
               88  APP-Browse-Forward-Disabled  value 'X'.
           05  APP-Browse-Backward            pic x.
               88  APP-Browse-Backward-Enabled  value space.
               88  APP-Browse-Backward-Disabled value 'X'.
           05  APP-Deletion-Flag              pic x.
               88  APP-Deletion-In-Progress value 'X' false space.
           05  APP-Current-Record.
               10  APP-Current-Employee-Id-X.
                   15  APP-Current-Employee-Id pic 9(8).
                   15  filler                 pic x(243).
           05  APP-Original-Record            pic x(243).                                                        