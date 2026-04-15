       01  Display-Messages.
           05  filler                         pic x value space.
               88  Highlight-Error value 'x' false space.
           05  MSG-Out                        pic x(79).
           05  MSG-Confirm-Deletion           pic x(79)]
               value 'Press PF11 again to confirm deletion.'.
           05  MSG-Record-Deleted.
               10  filler                     pic x(23)
                   value 'Record for Employee Id '.
               10  MSG-Deleted-Employee-Id    pic x(8).
               10  filler                     pic x(9)
                   value ' deleted.'.
           05  MSG-Employee Added             pic x(79)
               value 'New employee record added.'.
           05  MSG-Successful-Update          pic x(79)
               value 'Record successfully updated.'.
           05  MSG-Update-Conflict            pic x(79)
               value 'Record was updated since you read it.'.
           05  MSG-Duplicate-Primary-Name     pic x(79)
               value 'Primary Name exists already.'.
           05  MSG-Top-of-File                pic x(79)
               value 'Top of file.'.
           05  MSG-No-More-Records            pic x(79)
               value 'No more records to display.'.
           05  MSG-Undefined-PF-Key           pic x(79)
               value 'Undefined PF key.'.
           05  MSG-Employee-Not-Found.
               10  filler                     pic x(12)
               value 'Employee Id '.
               10  NOTFND-Employee-Id         pic 9(8).
               10  filler                     pic x(23)
               value ' is not present in the '.
               10  filler                     pic x(21)
               value 'Employee Master File.'.
           05  MSG-Employee-Master-File-Empty pic x(79)
               value 'Employee Master File is empty.'.
           05  MSG-Container-Error.
               10  filler                     pic x(14)
               value 'GET CONTAINER('.
               10  ERR-Container-Name         pic x(16).
               10  filler                     pic x(10)
               value ') CHANNEL('.
               10  ERR-Channel-Name           pic x(16).
               10  filler                     pic x value space.
               10  ERR-Container-EIBRESP      pic 9(8).
               10  filler                     pic x value space.
               10  ERR-Container-EIBRESP2     pic 9(8).
           05  MSG-File-Error.
               10  ERR-Operation              pic x(12).
               10  filler                     pic x(6)
                   value ' file '.
               10  ERR-File-Name              pic x(8).
               10  filler                     pic x value space.
               10  ERR-EIBRESP                pic 9(8).
               10  filler                     pic x value space.
               10  ERR-EIBRESP2               pic 9(8).                                                                    