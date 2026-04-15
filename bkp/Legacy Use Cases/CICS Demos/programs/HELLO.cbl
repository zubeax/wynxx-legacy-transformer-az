       Identification Division.
       Program-Id. HELLO.
      ****************************************************************
      * Simple "Hello, World!" for CICS.
      ****************************************************************
       Data Division.
       Working-Storage Section.
       01  greeting    pic x(25) value "'Hello, World' from CICS!".
       Procedure Division.
           EXEC CICS SEND
               FROM(greeting)
               LENGTH(length of greeting)
               ERASE
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .     