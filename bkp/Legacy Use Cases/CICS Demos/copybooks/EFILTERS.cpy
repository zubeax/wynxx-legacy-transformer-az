       7700-Apply-Filters.
      *****************************************************************
      * Compare user-defined filter criteria to an Employee Master
      * record.
      *****************************************************************
           set Filters-Match to true

      * Filtering by Primary Name 
       
           if LST-Select-by-Primary-Name
           and LST-Selection-Key-Value greater than spaces
               move LST-Selection-Key-Value to Filter-Value
               perform 7720-Locate-Wildcard
               if Filter-Index greater or equal
                       length of LST-Selection-Key-Value
                   if Filter-Value not equal Primary-Name 
                       set Filters-Match to false
                   end-if 
               else
                   if Filter-Value(1:Filter-Index) 
                           not equal Primary-Name(1:Filter-Index) 
                       set Filters-Match to false
                   end-if
               end-if 
           end-if 

      * Department Id includes 

           if Filters-Match 
               move Department-Id to Check-Department-Id-N
               if LST-Incl-Department-Id-Filters equal spaces
                   set Department-Id-Hit to true 
               else 
                   set Department-Id-Hit to false 
                   perform varying Incl-Dept-Index from 1 by 1
                           until Incl-Dept-Index greater than 4
                       if LST-Incl-Department-Id(Incl-Dept-Index)
                               greater than spaces
                           move LST-Incl-Department-Id(Incl-Dept-Index) 
                               to Filter-Value 
                           perform 7720-Locate-Wildcard
                           if Filter-Index greater or equal 
                                   length of Check-Department-Id 
                               if Filter-Value equal Check-Department-Id 
                                   set Department-Id-Hit to true 
                               end-if 
                           else 
                               if Filter-Value(1:Filter-Index) equal 
                                       Check-Department-Id(1:Filter-Index) 
                                   set Department-Id-Hit to true 
                               end-if 
                           end-if 
                       end-if 
                   end-perform
                   if not Department-Id-Hit 
                       set Filters-Match to false 
                   end-if 
               end-if 
           end-if 
            
       * Department Id excludes 
         
            if Filters-Match 
                move Department-Id to Check-Department-Id-N 
                if LST-Excl-Department-Id-Filters equal spaces 
                    set Department-Id-Hit to false 
                else 
                    set Department-Id-Hit to true 
                    perform varying Excl-Dept-Index from 1 by 1
                            until Excl-Dept-Index greater than 4 
                        if LST-Excl-Department-Id(Excl-Dept-Index) 
                                greater than spaces 
                            move LST-Excl-Department-Id(Excl-Dept-Index) 
                                to Filter-Value 
                            perform 7720-Locate-Wildcard 
                            if Filter-Index greater or equal 
                                    length of Check-Department-Id 
                                if Filter-Value equal Check-Department-Id 
                                    set Department-Id-Hit to false 
                                end-if 
                            else
                                if Filter-Value(1:Filter-Index) equal 
                                        Check-Department-Id(1:Filter-Index)
                                    set Department-Id-Hit to false 
                                end-if 
                            end-if 
                        end-if 
                    end-perform
                    if not Department-Id-Hit 
                        set Filters-Match to false 
                    end-if 
                end-if 
            end-if 
                  
      * Filtering by employment date 

            if Filters-Match 
                if LST-Employed-After-Date greater than spaces 
                    move LST-Employed-After-Date to Filter-Value 
                    perform 7720-Locate-Wildcard 
                    if Filter-Index greater or equal 
                            length of Start-Date 
                        if Start-Date less than Filter-Value 
                            set Filters-Match to false 
                        end-if 
                    else 
                        if Start-Date(1:Filter-Index) 
                                less than Filter-Value(1:Filter-Index) 
                            set Filters-Match to false 
                        end-if 
                    end-if 
                end-if 
                if LST-Employed-Before-Date greater than spaces 
                    move LST-Employed-Before-Date to Filter-Value 
                    perform 7720-Locate-Wildcard
                    if Filter-Index greater or equal 
                            length of Start-Date
                        if Start-Date greater than Filter-Value 
                            set Filters-Match to false 
                        end-if 
                    else 
                        if Start-Date(1:Filter-Index) greater than 
                                Filter-Value(1:Filter-Index) 
                            set Filters-Match to false
                        end-if 
                    end-if 
                end-if 
            end-if 
            .
        7720-Locate-Wildcard.                                        
       *****************************************************************
       * Find the position of the wildcard character, if it is present,
       * in a filter value.
       *****************************************************************
            move zero to Filter-Index 
            inspect Filter-Value 
                tallying Filter-Index 
                for characters before initial Wildcard-Character
            .    
