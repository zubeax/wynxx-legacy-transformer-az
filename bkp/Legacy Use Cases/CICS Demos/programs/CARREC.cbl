       Identification Division.
       Program-Id. CARREC.
      ****************************************************************
      * Build 3270 output stream the hard way.
      * Based on example in CICS Transaction Server for z/OS.
      ****************************************************************
       Data Division.
       Working-Storage Section.
       01  DataStream.
           05  filler      pic x     value x'11'.
           05  filler      pic x(2)  value x'40D6'.
           05  filler      pic x     value x'1D'.
           05  filler      pic x     value x'F8'.
           05  filler      pic x(10) value 'Car record'.
           05  filler      pic x     value x'11'.
           05  filler      pic x(2)  value x'C260'.
           05  filler      pic x     value x'1D'.
           05  filler      pic x     value x'F0'.
           05  filler      pic x(12) value 'Employee No:'.
           05  filler      pic x     value x'29'.
           05  filler      pic x     value x'02'.
           05  filler      pic x     value x'41'.
           05  filler      pic x     value x'F4'.
           05  filler      pic x     value x'C0'.
           05  filler      pic x     value x'50'.
           05  filler      pic x     value x'13'.
           05  filler      pic x     value x'11'.
           05  filler      pic x(2)  value x'C2F4'.
           05  filler      pic x     value x'1D'.
           05  filler      pic x     value x'F0'.
           05  filler      pic x(9)  value '  Tag No:'.
           05  filler      pic x     value x'29'.
           05  filler      pic x     value x'02'.
           05  filler      pic x     value x'41'.
           05  filler      pic x     value x'F4'.
           05  filler      pic x     value x'C0'.
           05  filler      pic x     value x'40'.
           05  filler      pic x     value x'11'.
           05  filler      pic x(2)  value x'C3C7'.
           05  filler      pic x     value x'1D'.
           05  filler      pic x     value x'F0'.
           05  filler      pic x(8)  value '  State:'..
           05  filler      pic x     value x'29'.
           05  filler      pic x     value x'02'.
           05  filler      pic x     value x'41'.
           05  filler      pic x     value x'F4'.
           05  filler      pic x     value x'C0'.
           05  filler      pic x     value x'40'.
           05  filler      pic x(2)  value x'0000'.
           05  filler      pic x     value x'1D'.
           05  filler      pic x     value x'F0'.
       Procedure Division.
           EXEC CICS SEND
               FROM(DataStream)
               LENGTH(length of DataStream)
               ERASE
           END-EXEC
           EXEC CICS RETURN END-EXEC
           .               
