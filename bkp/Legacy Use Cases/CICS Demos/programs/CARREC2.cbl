       Identification Division.
       Program-Id. CARREC2.
      ****************************************************************
      * Use BMS mapset instead of hand-coding the 3270 output stream
      * for the Car Record example.
      ****************************************************************
       Data Division.
       Working-Storage Section.
           copy CARRECS.
       Procedure Division.
           EXEC CICS SEND
               MAP('CARRECM')
               MAPSET('CARRECS')
               FROM(CARRECMO)
               FREEKB
               ERASE
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .               
