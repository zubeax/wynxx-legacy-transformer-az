       IDENTIFICATION DIVISION.                                         
       PROGRAM-ID. SECTDRIV.                                            
      ** COBOL Test Driver with sections for statements and intrinsic   
      *functions                                                        
      ** Regenerated with WS-SECID for statement sections               
       ENVIRONMENT DIVISION.                                            
       INPUT-OUTPUT SECTION.                                            
       FILE-CONTROL.                                                    
           SELECT SYS010 ASSIGN TO DD-SYS010                            
           ORGANIZATION IS SEQUENTIAL 
           ACCESS MODE IS SEQUENTIAL         
           RECORDING MODE IS F 
           RECORD CONTAINS 132 CHARACTERS.
      *               
           SELECT SYS011 ASSIGN TO DD-SYS011                            
           ORGANIZATION IS SEQUENTIAL 
           ACCESS MODE IS SEQUENTIAL         
           RECORDING MODE IS F 
           RECORD CONTAINS 132 CHARACTERS.          
      *
           SELECT SYS020 ASSIGN TO DD-SYS020                            
           ORGANIZATION IS INDEXED 
           ACCESS MODE IS DYNAMIC               
           RECORD KEY IS KS-KEY FILE STATUS IS FS-SYS020.               
       DATA DIVISION.                                                   
       FILE SECTION.                                                    
       FD SYS010 RECORDING MODE F RECORD CONTAINS 132 CHARACTERS        
           DATA RECORD IS SYS010-REC.                                   
       01 SYS010-REC PIC X(132).                                        
       FD SYS011 RECORDING MODE F RECORD CONTAINS 132 CHARACTERS        
           DATA RECORD IS SYS011-REC.                                   
       01 SYS011-REC PIC X(132).                                        
       FD SYS020 RECORDING MODE F RECORD CONTAINS 132 CHARACTERS        
           LABEL RECORDS ARE STANDARD                                   
           DATA RECORD IS SYS020-REC.                                   
       01 SYS020-REC.                                                   
           05 KS-KEY PIC X(8).                                          
           05 KS-DATA PIC X(124).                                       
       WORKING-STORAGE SECTION.                                         
       01 FS-SYS020 PIC XX VALUE SPACES.                            
       01 WS-EOF-SYS010 PIC X VALUE "N".                            
       01 WS-CURRENT.                                                   
           05 WS-SECID PIC X(8).                                        
           05 WS-OP-NAME PIC X(30).                                     
           05 WS-ARG-TEXT PIC X(94).                                    
       01 WS-ARG-PARSE.                                                 
           05 WS-ARG-COUNT PIC S9(4) SIGN IS LEADING EXTERNAL VALUE +0. 
           05 WS-ARG1-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG2-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG3-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG4-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG5-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG6-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG7-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG8-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG9-STR PIC X(64) VALUE SPACES.                       
           05 WS-ARG10-STR PIC X(64) VALUE SPACES.                      
           05 WS-ARG1-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG2-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG3-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG4-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG5-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG6-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG7-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG8-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG9-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE 0.
           05 WS-ARG10-NUM PIC S9(15)V9(9) SIGN LEADING EXTERNAL VALUE
                                           +0.
       01 WS-RESULTS.                                                   
           05 WS-RES-STR PIC X(120) VALUE SPACES.                       
           05 WS-RES-NUM PIC -9(18).9(9) SIGN LEADING EXTERNAL VALUE 0. 
           05 WS-RES-INT PIC S9(18) SIGN IS LEADING EXTERNAL VALUE +0.  
       01 WS-TEMP.                                                      
           05 WS-TOKEN PIC X(64).                                       
           05 WS-REST PIC X(94).                                        
       01 WS-LOG-REC.                                                   
           05 LOG-SECID PIC X(8).                                       
           05 FILLER PIC X VALUE ' '.                                     
           05 LOG-TEXT PIC X(123).                                      
       PROCEDURE DIVISION.                                              
       MAIN-SECTION SECTION.                                            
           PERFORM INIT-SECTION                                         
           PERFORM OPEN-FILES                                           
           PERFORM UNTIL WS-EOF-SYS010 = "Y"                            
           READ SYS010 AT END MOVE "Y" TO WS-EOF-SYS010                 
           NOT AT END PERFORM PROCESS-RECORD                            
           END-READ                                                     
           END-PERFORM                                                  
           PERFORM CLOSE-FILES                                          
           GOBACK.                                                      

       INIT-SECTION SECTION.
           MOVE SPACES TO WS-CURRENT WS-RESULTS WS-ARG-PARSE  
       .          
       EXIT.                                                            
       OPEN-FILES SECTION.                                              
           OPEN INPUT SYS010                                            
           OPEN OUTPUT SYS011                                           
           OPEN I-O SYS020                                              
       .          
       EXIT.                                                            
       CLOSE-FILES SECTION.                                             
           CLOSE SYS010 SYS011 SYS020                                   
       .          
       EXIT.                                                            
       PROCESS-RECORD SECTION.                                          
           MOVE SYS010-REC(1:8) TO WS-SECID                             
           MOVE FUNCTION TRIM(SYS010-REC(10:30)) TO WS-OP-NAME          
           MOVE SYS010-REC(41:92) TO WS-ARG-TEXT                        
           PERFORM PARSE-ARGS                                           
           PERFORM DISPATCH-SECTION                                     
       .          
       EXIT.                                                            
       PARSE-ARGS SECTION.                                              
           MOVE +0 TO WS-ARG-COUNT                                      
           MOVE SPACES TO WS-ARG1-STR                                   
           MOVE +0 TO WS-ARG1-NUM                                       
           MOVE SPACES TO WS-ARG2-STR                                   
           MOVE +0 TO WS-ARG2-NUM                                       
           MOVE SPACES TO WS-ARG3-STR                                   
           MOVE +0 TO WS-ARG3-NUM                                       
           MOVE SPACES TO WS-ARG4-STR                                   
           MOVE +0 TO WS-ARG4-NUM                                       
           MOVE SPACES TO WS-ARG5-STR                                   
           MOVE +0 TO WS-ARG5-NUM                                       
           MOVE SPACES TO WS-ARG6-STR                                   
           MOVE +0 TO WS-ARG6-NUM                                       
           MOVE SPACES TO WS-ARG7-STR                                   
           MOVE +0 TO WS-ARG7-NUM                                       
           MOVE SPACES TO WS-ARG8-STR                                   
           MOVE +0 TO WS-ARG8-NUM                                       
           MOVE SPACES TO WS-ARG9-STR                                   
           MOVE +0 TO WS-ARG9-NUM                                       
           MOVE SPACES TO WS-ARG10-STR                                  
           MOVE +0 TO WS-ARG10-NUM                                      
           MOVE FUNCTION TRIM(WS-ARG-TEXT) TO WS-REST                   
           PERFORM VARYING WS-ARG-COUNT FROM +0 BY +1 UNTIL WS-ARG-COUNT
      -    +9                                                           
           IF WS-REST = SPACES EXIT PERFORM                             
           UNSTRING WS-REST DELIMITED BY ","                            
           INTO WS-TOKEN WS-REST                                        
           WITH POINTER 1 TALLYING IN WS-ARG-COUNT                      
           END-UNSTRING                                                 
           EVALUATE WS-ARG-COUNT                                        
           WHEN +1 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG1-STR          
           MOVE FUNCTION NUMVAL(WS-ARG1-STR) TO WS-ARG1-NUM             
           WHEN +2 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG2-STR          
           MOVE FUNCTION NUMVAL(WS-ARG2-STR) TO WS-ARG2-NUM             
           WHEN +3 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG3-STR          
           MOVE FUNCTION NUMVAL(WS-ARG3-STR) TO WS-ARG3-NUM             
           WHEN +4 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG4-STR          
           MOVE FUNCTION NUMVAL(WS-ARG4-STR) TO WS-ARG4-NUM             
           WHEN +5 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG5-STR          
           MOVE FUNCTION NUMVAL(WS-ARG5-STR) TO WS-ARG5-NUM             
           WHEN +6 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG6-STR          
           MOVE FUNCTION NUMVAL(WS-ARG6-STR) TO WS-ARG6-NUM             
           WHEN +7 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG7-STR          
           MOVE FUNCTION NUMVAL(WS-ARG7-STR) TO WS-ARG7-NUM             
           WHEN +8 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG8-STR          
           MOVE FUNCTION NUMVAL(WS-ARG8-STR) TO WS-ARG8-NUM             
           WHEN +9 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG9-STR          
           MOVE FUNCTION NUMVAL(WS-ARG9-STR) TO WS-ARG9-NUM             
           WHEN +10 MOVE FUNCTION TRIM(WS-TOKEN) TO WS-ARG10-STR        
           MOVE FUNCTION NUMVAL(WS-ARG10-STR) TO WS-ARG10-NUM           
           WHEN OTHER CONTINUE                                          
           END-EVALUATE                                                 
           END-PERFORM                                                  
       EXIT.                                                            
       LOG-SECTION SECTION.                                             
           MOVE WS-SECID TO LOG-SECID                                   
           MOVE WS-RES-STR TO LOG-TEXT                                  
           WRITE SYS011-REC FROM WS-LOG-REC                             
       EXIT.                                                            
       DISPATCH-SECTION SECTION.                                        
           EVALUATE FUNCTION UPPER-CASE(WS-OP-NAME)                     
           WHEN "ADD" PERFORM ADD-SECTION                               
           WHEN "SUBTRACT" PERFORM SUBTRACT-SECTION                     
           WHEN "MULTIPLY" PERFORM MULTIPLY-SECTION                     
           WHEN "DIVIDE" PERFORM DIVIDE-SECTION                         
           WHEN "COMPUTE" PERFORM COMPUTE-SECTION                       
           WHEN "MOVE" PERFORM MOVE-SECTION                             
           WHEN "DISPLAY" PERFORM DISPLAY-SECTION                       
           WHEN "STRING" PERFORM STRING-SECTION                         
           WHEN "UNSTRING" PERFORM UNSTRING-SECTION                     
           WHEN "INSPECT" PERFORM INSPECT-SECTION                       
           WHEN "INITIALIZE" PERFORM INITIALIZE-SECTION                 
           WHEN "IF" PERFORM IF-SECTION                                 
           WHEN "EVALUATE" PERFORM EVALUATE-SECTION                     
           WHEN "PERFORM" PERFORM PERFORM-SECTION                       
           WHEN "OPEN" PERFORM OPEN-SECTION                             
           WHEN "READ" PERFORM READ-SECTION                             
           WHEN "WRITE" PERFORM WRITE-SECTION                           
           WHEN "REWRITE" PERFORM REWRITE-SECTION                       
           WHEN "START" PERFORM START-SECTION                           
           WHEN "CLOSE" PERFORM CLOSE-SECTION                           
           WHEN "CALL" PERFORM CALL-SECTION                             
           WHEN "CANCEL" PERFORM CANCEL-SECTION                         
           WHEN "RETURN" PERFORM RETURN-SECTION                         
           WHEN "RELEASE" PERFORM RELEASE-SECTION                       
           WHEN "MERGE" PERFORM MERGE-SECTION                           
           WHEN "SORT" PERFORM SORT-SECTION                             
           WHEN "SET" PERFORM SET-SECTION                               
           WHEN "SEARCH" PERFORM SEARCH-SECTION                         
           WHEN "CONTINUE" PERFORM CONTINUE-SECTION                     
           WHEN "EXIT" PERFORM EXIT-SECTION                             
           WHEN "GOBACK" PERFORM GOBACK-SECTION                         
           WHEN "STOP RUN" PERFORM STOP-RUN-SECTION                     
           WHEN "JSON PARSE" PERFORM JSON-PARSE-SECTION                 
           WHEN "JSON GENERATE" PERFORM JSON-GENERATE-SECTION           
           WHEN "ABS" PERFORM FN-ABS-SECTION                            
           WHEN "ACOS" PERFORM FN-ACOS-SECTION                          
           WHEN "ANNUITY" PERFORM FN-ANNUITY-SECTION                    
           WHEN "ASIN" PERFORM FN-ASIN-SECTION                          
           WHEN "ATAN" PERFORM FN-ATAN-SECTION                          
           WHEN "BIT-OF" PERFORM FN-BIT-OF-SECTION                      
           WHEN "BIT-TO-CHAR" PERFORM FN-BIT-TO-CHAR-SECTION            
           WHEN "BYTE-LENGTH" PERFORM FN-BYTE-LENGTH-SECTION            
           WHEN "CHAR" PERFORM FN-CHAR-SECTION                          
           WHEN "COMBINED-DATETIME" PERFORM FN-COMBINED-DATETIME-SECTION
           WHEN "CONTENT-OF" PERFORM FN-CONTENT-OF-SECTION              
           WHEN "CURRENT-DATE" PERFORM FN-CURRENT-DATE-SECTION          
           WHEN "COS" PERFORM FN-COS-SECTION                            
           WHEN "DATE-OF-INTEGER" PERFORM FN-DATE-OF-INTEGER-SECTION    
           WHEN "DATE-TO-YYYYMMDD" PERFORM FN-DATE-TO-YYYYMMDD-SECTION  
           WHEN "DAY-OF-INTEGER" PERFORM FN-DAY-OF-INTEGER-SECTION      
           WHEN "DAY-TO-YYYYDDD" PERFORM FN-DAY-TO-YYYYDDD-SECTION      
           WHEN "DISPLAY-OF" PERFORM FN-DISPLAY-OF-SECTION              
           WHEN "E" PERFORM FN-E-SECTION                                
           WHEN "EXP" PERFORM FN-EXP-SECTION                            
           WHEN "EXP10" PERFORM FN-EXP10-SECTION                        
           WHEN "FACTORIAL" PERFORM FN-FACTORIAL-SECTION                
           WHEN "FORMATTED-CURRENT-DATE" PERFORM                        
      -    FN-FORMATTED-CURRENT-DATE-SECTION                            
           WHEN "FORMATTED-DATE" PERFORM FN-FORMATTED-DATE-SECTION      
           WHEN "FORMATTED-DATETIME" PERFORM FN-FORMATTED-DATETIME-SECTI
           WHEN "FORMATTED-TIME" PERFORM FN-FORMATTED-TIME-SECTION      
           WHEN "HEX-OF" PERFORM FN-HEX-OF-SECTION                      
           WHEN "HEX-TO-CHAR" PERFORM FN-HEX-TO-CHAR-SECTION            
           WHEN "INTEGER" PERFORM FN-INTEGER-SECTION                    
           WHEN "INTEGER-OF-DATE" PERFORM FN-INTEGER-OF-DATE-SECTION    
           WHEN "INTEGER-OF-DAY" PERFORM FN-INTEGER-OF-DAY-SECTION      
           WHEN "INTEGER-OF-FORMATTED-DATE" PERFORM                     
      -    FN-INTEGER-OF-FORMATTED-DATE-SECTION                         
           WHEN "INTEGER-PART" PERFORM FN-INTEGER-PART-SECTION          
           WHEN "LENGTH" PERFORM FN-LENGTH-SECTION                      
           WHEN "LOG" PERFORM FN-LOG-SECTION                            
           WHEN "LOG10" PERFORM FN-LOG10-SECTION                        
           WHEN "LOWER-CASE" PERFORM FN-LOWER-CASE-SECTION              
           WHEN "MAX" PERFORM FN-MAX-SECTION                            
           WHEN "MEAN" PERFORM FN-MEAN-SECTION                          
           WHEN "MEDIAN" PERFORM FN-MEDIAN-SECTION                      
           WHEN "MIDRANGE" PERFORM FN-MIDRANGE-SECTION                  
           WHEN "MIN" PERFORM FN-MIN-SECTION                            
           WHEN "MOD" PERFORM FN-MOD-SECTION                            
           WHEN "NATIONAL-OF" PERFORM FN-NATIONAL-OF-SECTION            
           WHEN "NUMVAL" PERFORM FN-NUMVAL-SECTION                      
           WHEN "NUMVAL-C" PERFORM FN-NUMVAL-C-SECTION                  
           WHEN "NUMVAL-F" PERFORM FN-NUMVAL-F-SECTION                  
           WHEN "ORD" PERFORM FN-ORD-SECTION                            
           WHEN "ORD-MAX" PERFORM FN-ORD-MAX-SECTION                    
           WHEN "ORD-MIN" PERFORM FN-ORD-MIN-SECTION                    
           WHEN "PI" PERFORM FN-PI-SECTION                              
           WHEN "PRESENT-VALUE" PERFORM FN-PRESENT-VALUE-SECTION        
           WHEN "RANDOM" PERFORM FN-RANDOM-SECTION                      
           WHEN "RANGE" PERFORM FN-RANGE-SECTION                        
           WHEN "REM" PERFORM FN-REM-SECTION                            
           WHEN "REVERSE" PERFORM FN-REVERSE-SECTION                    
           WHEN "SECONDS-FROM-FORMATTED-TIME" PERFORM                   
      -    FN-SECONDS-FROM-FORMATTED-TIME-SECTION                       
           WHEN "SECONDS-PAST-MIDNIGHT" PERFORM                         
      -    FN-SECONDS-PAST-MIDNIGHT-SECTION                             
           WHEN "SIGN" PERFORM FN-SIGN-SECTION                          
           WHEN "SIN" PERFORM FN-SIN-SECTION                            
           WHEN "SQRT" PERFORM FN-SQRT-SECTION                          
           WHEN "STANDARD-DEVIATION" PERFORM FN-STANDARD-DEVIATION-SECTI
           WHEN "SUM" PERFORM FN-SUM-SECTION                            
           WHEN "TAN" PERFORM FN-TAN-SECTION                            
           WHEN "TEST-DATE-YYYYMMDD" PERFORM FN-TEST-DATE-YYYYMMDD-SECTI
           WHEN "TEST-DAY-YYYYDDD" PERFORM FN-TEST-DAY-YYYYDDD-SECTION  
           WHEN "TEST-FORMATTED-DATETIME" PERFORM                       
      -    FN-TEST-FORMATTED-DATETIME-SECTION                           
           WHEN "TEST-NUMVAL" PERFORM FN-TEST-NUMVAL-SECTION            
           WHEN "TEST-NUMVAL-C" PERFORM FN-TEST-NUMVAL-C-SECTION        
           WHEN "TEST-NUMVAL-F" PERFORM FN-TEST-NUMVAL-F-SECTION        
           WHEN "TRIM" PERFORM FN-TRIM-SECTION                          
           WHEN "ULENGTH" PERFORM FN-ULENGTH-SECTION                    
           WHEN "UPOS" PERFORM FN-UPOS-SECTION                          
           WHEN "UPPER-CASE" PERFORM FN-UPPER-CASE-SECTION              
           WHEN "USUBSTR" PERFORM FN-USUBSTR-SECTION                    
           WHEN "USUPPLEMENTARY" PERFORM FN-USUPPLEMENTARY-SECTION      
           WHEN "UUID4" PERFORM FN-UUID4-SECTION                        
           WHEN "UVALID" PERFORM FN-UVALID-SECTION                      
           WHEN "UWIDTH" PERFORM FN-UWIDTH-SECTION                      
           WHEN "VARIANCE" PERFORM FN-VARIANCE-SECTION                  
           WHEN "WHEN-COMPILED" PERFORM FN-WHEN-COMPILED-SECTION        
           WHEN "YEAR-TO-YYYY" PERFORM FN-YEAR-TO-YYYY-SECTION          
           WHEN OTHER                                                   
           MOVE "UNKNOWN OP" TO WS-RES-STR                              
           PERFORM LOG-SECTION                                          
           END-EVALUATE                                                 
       EXIT.                                                            
       ADD-SECTION SECTION.                                             
           MOVE "STADD001" TO WS-SECID                                  
           COMPUTE WS-RES-NUM = WS-ARG1-NUM + WS-ARG2-NUM               
           MOVE "ADD" TO WS-RES-STR                                     
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       SUBTRACT-SECTION SECTION.                                        
           MOVE "STSUB001" TO WS-SECID                                  
           COMPUTE WS-RES-NUM = WS-ARG1-NUM - WS-ARG2-NUM               
           MOVE "SUBTRACT" TO WS-RES-STR                                
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       MULTIPLY-SECTION SECTION.                                        
           MOVE "STMUL001" TO WS-SECID                                  
           COMPUTE WS-RES-NUM = WS-ARG1-NUM * WS-ARG2-NUM               
           MOVE "MULTIPLY" TO WS-RES-STR                                
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       DIVIDE-SECTION SECTION.                                          
           MOVE "STDIV001" TO WS-SECID                                  
           IF WS-ARG2-NUM NOT = 0 COMPUTE WS-RES-NUM = WS-ARG1-NUM /    
      -    WS-ARG2-NUM                                                  
           MOVE "DIVIDE" TO WS-RES-STR                                  
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       COMPUTE-SECTION SECTION.                                         
           MOVE "STCMP001" TO WS-SECID                                  
           COMPUTE WS-RES-NUM = (WS-ARG1-NUM ** 2) + WS-ARG2-NUM        
           MOVE "COMPUTE" TO WS-RES-STR                                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       MOVE-SECTION SECTION.                                            
           MOVE "STMOV001" TO WS-SECID                                  
           MOVE WS-ARG1-STR TO WS-RES-STR                               
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       DISPLAY-SECTION SECTION.                                         
           MOVE "STDSP001" TO WS-SECID                                  
           MOVE WS-ARG1-STR TO WS-RES-STR                               
           DISPLAY WS-RES-STR                                           
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       STRING-SECTION SECTION.                                          
           MOVE "STSTR001" TO WS-SECID                                  
           STRING WS-ARG1-STR DELIMITED BY SIZE " " DELIMITED BY SIZE   
      -    WS-ARG2-STR DELIMITED BY SIZE INTO WS-RES-STR                
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       UNSTRING-SECTION SECTION.                                        
           MOVE "STUNS001" TO WS-SECID                                  
           UNSTRING WS-ARG1-STR DELIMITED BY "," INTO WS-RES-STR        
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       INSPECT-SECTION SECTION.                                         
           MOVE "STINP001" TO WS-SECID                                  
           MOVE WS-ARG1-STR TO WS-RES-STR                               
           INSPECT WS-RES-STR TALLYING WS-ARG1-NUM FOR ALL "A"          
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       INITIALIZE-SECTION SECTION.                                      
           MOVE "STINI001" TO WS-SECID                                  
           INITIALIZE WS-RESULTS                                        
           MOVE "INITIALIZE" TO WS-RES-STR                              
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       IF-SECTION SECTION.                                              
           MOVE "STIF0001" TO WS-SECID                                  
           IF WS-ARG1-NUM > 0 MOVE "POSITIVE" TO WS-RES-STR ELSE MOVE   
      -    "NONPOS" TO WS-RES-STR                                       
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       EVALUATE-SECTION SECTION.                                        
           MOVE "STEVA001" TO WS-SECID                                  
           EVALUATE TRUE WHEN WS-ARG1-NUM > 10 MOVE "GT10" TO WS-RES-STR
      -    WHEN OTHER MOVE "LE10" TO WS-RES-STR                         
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       PERFORM-SECTION SECTION.                                         
           MOVE "STPER001" TO WS-SECID                                  
           PERFORM VARYING WS-ARG1-NUM FROM +1 BY +1 UNTIL WS-ARG1-NUM >
           CONTINUE                                                     
           END-PERFORM                                                  
           MOVE "PERFORM" TO WS-RES-STR                                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       OPEN-SECTION SECTION.                                            
           MOVE "STOPN001" TO WS-SECID                                  
           MOVE "OPEN-NOOP" TO WS-RES-STR                               
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       READ-SECTION SECTION.                                            
           MOVE "STRD0001" TO WS-SECID                                  
           READ SYS020 KEY IS KS-KEY INVALID KEY MOVE "NOTFOUND" TO     
      -    WS-RES-STR NOT INVALID KEY MOVE KS-DATA TO WS-RES-STR END-REA
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       WRITE-SECTION SECTION.                                           
           MOVE "STWRT001" TO WS-SECID                                  
           MOVE WS-ARG1-STR TO SYS011-REC                               
           WRITE SYS011-REC                                             
           MOVE "WROTE" TO WS-RES-STR                                   
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       REWRITE-SECTION SECTION.                                         
           MOVE "STRWT001" TO WS-SECID                                  
           MOVE WS-ARG1-STR TO KS-DATA                                  
           REWRITE SYS020-REC INVALID KEY CONTINUE                      
           MOVE "REWRITE" TO WS-RES-STR                                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       START-SECTION SECTION.                                           
           MOVE "STSTA001" TO WS-SECID                                  
           MOVE WS-ARG1-STR(1:8) TO KS-KEY                              
           START SYS020 KEY >= KS-KEY INVALID KEY CONTINUE              
           MOVE "START" TO WS-RES-STR                                   
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       CLOSE-SECTION SECTION.                                           
           MOVE "STCLS001" TO WS-SECID                                  
           MOVE "CLOSE-NOOP" TO WS-RES-STR                              
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       CALL-SECTION SECTION.                                            
           MOVE "STCAL001" TO WS-SECID                                  
           CALL WS-ARG1-STR ON EXCEPTION MOVE "EXC" TO WS-RES-STR NOT ON
      -    EXCEPTION MOVE "OK" TO WS-RES-STR END-CALL                   
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       CANCEL-SECTION SECTION.                                          
           MOVE "STCAN001" TO WS-SECID                                  
           CANCEL WS-ARG1-STR                                           
           MOVE "CANCEL" TO WS-RES-STR                                  
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       RETURN-SECTION SECTION.                                          
           MOVE "STRET001" TO WS-SECID                                  
           MOVE "RETURN-NOOP" TO WS-RES-STR                             
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       RELEASE-SECTION SECTION.                                         
           MOVE "STREL001" TO WS-SECID                                  
           MOVE "RELEASE-NOOP" TO WS-RES-STR                            
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       MERGE-SECTION SECTION.                                           
           MOVE "STMGE001" TO WS-SECID                                  
           MOVE "MERGE-NOOP" TO WS-RES-STR                              
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       SORT-SECTION SECTION.                                            
           MOVE "STSRT001" TO WS-SECID                                  
           MOVE "SORT-NOOP" TO WS-RES-STR                               
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       SET-SECTION SECTION.                                             
           MOVE "STSET001" TO WS-SECID                                  
           SET WS-ARG1-NUM UP BY +1                                     
           MOVE "SET" TO WS-RES-STR                                     
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       SEARCH-SECTION SECTION.                                          
           MOVE "STSCH001" TO WS-SECID                                  
           MOVE "SEARCH-NOOP" TO WS-RES-STR                             
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       CONTINUE-SECTION SECTION.                                        
           MOVE "STCNT001" TO WS-SECID                                  
           CONTINUE                                                     
           MOVE "CONTINUE" TO WS-RES-STR                                
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       EXIT-SECTION SECTION.                                            
           MOVE "STEXT001" TO WS-SECID                                  
           EXIT                                                         
           MOVE "EXIT" TO WS-RES-STR                                    
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       GOBACK-SECTION SECTION.                                          
           MOVE "STGBK001" TO WS-SECID                                  
           MOVE "GOBACK-NOOP" TO WS-RES-STR                             
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       STOP-RUN-SECTION SECTION.                                        
           MOVE "STSPR001" TO WS-SECID                                  
           MOVE "STOPRUN-NOOP" TO WS-RES-STR                            
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       JSON-PARSE-SECTION SECTION.                                      
           MOVE "STJNP001" TO WS-SECID                                  
           MOVE "JSONPARSE-NOOP" TO WS-RES-STR                          
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       JSON-GENERATE-SECTION SECTION.                                   
           MOVE "STJNG001" TO WS-SECID                                  
           MOVE "JSONGEN-NOOP" TO WS-RES-STR                            
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ABS-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION ABS(WS-ARG1-NUM)               
           MOVE "FNABS   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ACOS-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION ACOS(WS-ARG1-NUM)              
           MOVE "FNACOS  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ANNUITY-SECTION SECTION.                                      
           COMPUTE WS-RES-NUM = FUNCTION ANNUITY(WS-ARG1-NUM, WS-ARG2-NU
      -    WS-ARG3-NUM)                                                 
           MOVE "FNANNUIT" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ASIN-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION ASIN(WS-ARG1-NUM)              
           MOVE "FNASIN  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ATAN-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION ATAN(WS-ARG1-NUM)              
           MOVE "FNATAN  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-BIT-OF-SECTION SECTION.                                       
           MOVE FUNCTION BIT-OF(WS-ARG1-STR) TO WS-RES-STR              
           MOVE "FNBITOF " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-BIT-TO-CHAR-SECTION SECTION.                                  
           MOVE FUNCTION BIT-TO-CHAR(WS-ARG1-STR) TO WS-RES-STR         
           MOVE "FNBITTO " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-BYTE-LENGTH-SECTION SECTION.                                  
           COMPUTE WS-RES-NUM = FUNCTION BYTE-LENGTH(WS-ARG1-STR)       
           MOVE "FNBYTEL " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-CHAR-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION CHAR(WS-ARG1-NUM)              
           MOVE "FNCHAR  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-COMBINED-DATETIME-SECTION SECTION.                            
           COMPUTE WS-RES-NUM = FUNCTION COMBINED-DATETIME(WS-ARG1-NUM, 
      -    WS-ARG2-NUM)                                                 
           MOVE "FNCOMBIN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-CONTENT-OF-SECTION SECTION.                                   
           MOVE FUNCTION CONTENT-OF(WS-ARG1-STR) TO WS-RES-STR          
           MOVE "FNCONTEN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-CURRENT-DATE-SECTION SECTION.                                 
           MOVE FUNCTION CURRENT-DATE TO WS-RES-STR                     
           MOVE "FNCURREN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-COS-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION COS(WS-ARG1-NUM)               
           MOVE "FNCOS   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-DATE-OF-INTEGER-SECTION SECTION.                              
           COMPUTE WS-RES-NUM = FUNCTION DATE-OF-INTEGER(WS-ARG1-NUM)   
           MOVE "FNDATEO " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-DATE-TO-YYYYMMDD-SECTION SECTION.                             
           COMPUTE WS-RES-NUM = FUNCTION DATE-TO-YYYYMMDD(WS-ARG1-NUM,  
      -    WS-ARG2-NUM)                                                 
           MOVE "FNDATET " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-DAY-OF-INTEGER-SECTION SECTION.                               
           COMPUTE WS-RES-NUM = FUNCTION DAY-OF-INTEGER(WS-ARG1-NUM)    
           MOVE "FNDAYOF " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-DAY-TO-YYYYDDD-SECTION SECTION.                               
           COMPUTE WS-RES-NUM = FUNCTION DAY-TO-YYYYDDD(WS-ARG1-NUM,    
      -    WS-ARG2-NUM)                                                 
           MOVE "FNDAYTO " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-DISPLAY-OF-SECTION SECTION.                                   
           MOVE FUNCTION DISPLAY-OF(WS-ARG1-STR) TO WS-RES-STR          
           MOVE "FNDISPLA" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-E-SECTION SECTION.                                            
           MOVE FUNCTION E TO WS-RES-STR                                
           MOVE "FNE     " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-EXP-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION EXP(WS-ARG1-NUM)               
           MOVE "FNEXP   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-EXP10-SECTION SECTION.                                        
           COMPUTE WS-RES-NUM = FUNCTION EXP10(WS-ARG1-NUM)             
           MOVE "FNEXP10 " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-FACTORIAL-SECTION SECTION.                                    
           COMPUTE WS-RES-NUM = FUNCTION FACTORIAL(WS-ARG1-NUM)         
           MOVE "FNFACTOR" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-FORMATTED-CURRENT-DATE-SECTION SECTION.                       
           MOVE FUNCTION FORMATTED-CURRENT-DATE(WS-ARG1-STR) TO WS-RES-S
           MOVE "FNFORMAT" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-FORMATTED-DATE-SECTION SECTION.                               
           COMPUTE WS-RES-NUM = FUNCTION FORMATTED-DATE(WS-ARG1-NUM,    
      -    WS-ARG2-NUM)                                                 
           MOVE "FNFORMAT" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-FORMATTED-DATETIME-SECTION SECTION.                           
           COMPUTE WS-RES-NUM = FUNCTION FORMATTED-DATETIME(WS-ARG1-NUM,
      -    WS-ARG2-NUM, WS-ARG3-NUM)                                    
           MOVE "FNFORMAT" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-FORMATTED-TIME-SECTION SECTION.                               
           COMPUTE WS-RES-NUM = FUNCTION FORMATTED-TIME(WS-ARG1-NUM,    
      -    WS-ARG2-NUM)                                                 
           MOVE "FNFORMAT" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-HEX-OF-SECTION SECTION.                                       
           MOVE FUNCTION HEX-OF(WS-ARG1-STR) TO WS-RES-STR              
           MOVE "FNHEXOF " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-HEX-TO-CHAR-SECTION SECTION.                                  
           MOVE FUNCTION HEX-TO-CHAR(WS-ARG1-STR) TO WS-RES-STR         
           MOVE "FNHEXTO " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-INTEGER-SECTION SECTION.                                      
           COMPUTE WS-RES-NUM = FUNCTION INTEGER(WS-ARG1-NUM)           
           MOVE "FNINTEGE" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-INTEGER-OF-DATE-SECTION SECTION.                              
           COMPUTE WS-RES-NUM = FUNCTION INTEGER-OF-DATE(WS-ARG1-NUM)   
           MOVE "FNINTEGE" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-INTEGER-OF-DAY-SECTION SECTION.                               
           COMPUTE WS-RES-NUM = FUNCTION INTEGER-OF-DAY(WS-ARG1-NUM)    
           MOVE "FNINTEGE" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-INTEGER-OF-FORMATTED-DATE-SECTION SECTION.                    
           MOVE FUNCTION INTEGER-OF-FORMATTED-DATE(WS-ARG1-STR) TO      
      -    WS-RES-STR                                                   
           MOVE "FNINTEGE" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-INTEGER-PART-SECTION SECTION.                                 
           COMPUTE WS-RES-NUM = FUNCTION INTEGER-PART(WS-ARG1-NUM)      
           MOVE "FNINTEGE" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-LENGTH-SECTION SECTION.                                       
           COMPUTE WS-RES-NUM = FUNCTION LENGTH(WS-ARG1-STR)            
           MOVE "FNLENGTH" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-LOG-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION LOG(WS-ARG1-NUM)               
           MOVE "FNLOG   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-LOG10-SECTION SECTION.                                        
           COMPUTE WS-RES-NUM = FUNCTION LOG10(WS-ARG1-NUM)             
           MOVE "FNLOG10 " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-LOWER-CASE-SECTION SECTION.                                   
           MOVE FUNCTION LOWER-CASE(WS-ARG1-STR) TO WS-RES-STR          
           MOVE "FNLOWER " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-MAX-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION MAX(WS-ARG1-NUM, WS-ARG2-NUM)  
           MOVE "FNMAX   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-MEAN-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION MEAN(WS-ARG1-NUM, WS-ARG2-NUM) 
           MOVE "FNMEAN  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-MEDIAN-SECTION SECTION.                                       
           COMPUTE WS-RES-NUM = FUNCTION MEDIAN(WS-ARG1-NUM, WS-ARG2-NUM
      -    WS-ARG3-NUM)                                                 
           MOVE "FNMEDIAN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-MIDRANGE-SECTION SECTION.                                     
           COMPUTE WS-RES-NUM = FUNCTION MIDRANGE(WS-ARG1-NUM, WS-ARG2-N
           MOVE "FNMIDRAN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-MIN-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION MIN(WS-ARG1-NUM, WS-ARG2-NUM)  
           MOVE "FNMIN   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-MOD-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION MOD(WS-ARG1-NUM, WS-ARG2-NUM)  
           MOVE "FNMOD   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-NATIONAL-OF-SECTION SECTION.                                  
           MOVE FUNCTION NATIONAL-OF(WS-ARG1-STR) TO WS-RES-STR         
           MOVE "FNNATION" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-NUMVAL-SECTION SECTION.                                       
           MOVE FUNCTION NUMVAL(WS-ARG1-STR) TO WS-RES-STR              
           MOVE "FNNUMVAL" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-NUMVAL-C-SECTION SECTION.                                     
           MOVE FUNCTION NUMVAL-C(WS-ARG1-STR) TO WS-RES-STR            
           MOVE "FNNUMVAL" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-NUMVAL-F-SECTION SECTION.                                     
           MOVE FUNCTION NUMVAL-F(WS-ARG1-STR) TO WS-RES-STR            
           MOVE "FNNUMVAL" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ORD-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION ORD(WS-ARG1-STR)               
           MOVE "FNORD   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ORD-MAX-SECTION SECTION.                                      
           COMPUTE WS-RES-NUM = FUNCTION ORD-MAX(WS-ARG1-NUM, WS-ARG2-NU
      -    WS-ARG3-NUM)                                                 
           MOVE "FNORDMA " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ORD-MIN-SECTION SECTION.                                      
           COMPUTE WS-RES-NUM = FUNCTION ORD-MIN(WS-ARG1-NUM, WS-ARG2-NU
      -    WS-ARG3-NUM)                                                 
           MOVE "FNORDMI " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-PI-SECTION SECTION.                                           
           MOVE FUNCTION PI TO WS-RES-STR                               
           MOVE "FNPI    " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-PRESENT-VALUE-SECTION SECTION.                                
           COMPUTE WS-RES-NUM = FUNCTION PRESENT-VALUE(WS-ARG1-NUM,     
      -    WS-ARG2-NUM)                                                 
           MOVE "FNPRESEN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-RANDOM-SECTION SECTION.                                       
           COMPUTE WS-RES-NUM = FUNCTION RANDOM(WS-ARG1-NUM)            
           MOVE "FNRANDOM" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-RANGE-SECTION SECTION.                                        
           COMPUTE WS-RES-NUM = FUNCTION RANGE(WS-ARG1-NUM, WS-ARG2-NUM,
      -    WS-ARG3-NUM)                                                 
           MOVE "FNRANGE " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-REM-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION REM(WS-ARG1-NUM, WS-ARG2-NUM)  
           MOVE "FNREM   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-REVERSE-SECTION SECTION.                                      
           MOVE FUNCTION REVERSE(WS-ARG1-STR) TO WS-RES-STR             
           MOVE "FNREVERS" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-SECONDS-FROM-FORMATTED-TIME-SECTION SECTION.                  
           MOVE FUNCTION SECONDS-FROM-FORMATTED-TIME(WS-ARG1-STR) TO    
      -    WS-RES-STR                                                   
           MOVE "FNSECOND" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-SECONDS-PAST-MIDNIGHT-SECTION SECTION.                        
           MOVE FUNCTION SECONDS-PAST-MIDNIGHT TO WS-RES-STR            
           MOVE "FNSECOND" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-SIGN-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION SIGN(WS-ARG1-NUM)              
           MOVE "FNSIGN  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-SIN-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION SIN(WS-ARG1-NUM)               
           MOVE "FNSIN   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-SQRT-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION SQRT(WS-ARG1-NUM)              
           MOVE "FNSQRT  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-STANDARD-DEVIATION-SECTION SECTION.                           
           COMPUTE WS-RES-NUM = FUNCTION STANDARD-DEVIATION(WS-ARG1-NUM,
      -    WS-ARG2-NUM, WS-ARG3-NUM)                                    
           MOVE "FNSTANDA" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-SUM-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION SUM(WS-ARG1-NUM, WS-ARG2-NUM)  
           MOVE "FNSUM   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TAN-SECTION SECTION.                                          
           COMPUTE WS-RES-NUM = FUNCTION TAN(WS-ARG1-NUM)               
           MOVE "FNTAN   " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TEST-DATE-YYYYMMDD-SECTION SECTION.                           
           COMPUTE WS-RES-NUM = FUNCTION TEST-DATE-YYYYMMDD(WS-ARG1-NUM)
           MOVE "FNTESTD " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TEST-DAY-YYYYDDD-SECTION SECTION.                             
           COMPUTE WS-RES-NUM = FUNCTION TEST-DAY-YYYYDDD(WS-ARG1-NUM)  
           MOVE "FNTESTD " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TEST-FORMATTED-DATETIME-SECTION SECTION.                      
           MOVE FUNCTION TEST-FORMATTED-DATETIME(WS-ARG1-STR) TO WS-RES-
           MOVE "FNTESTF " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TEST-NUMVAL-SECTION SECTION.                                  
           MOVE FUNCTION TEST-NUMVAL(WS-ARG1-STR) TO WS-RES-STR         
           MOVE "FNTESTN " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TEST-NUMVAL-C-SECTION SECTION.                                
           MOVE FUNCTION TEST-NUMVAL-C(WS-ARG1-STR) TO WS-RES-STR       
           MOVE "FNTESTN " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TEST-NUMVAL-F-SECTION SECTION.                                
           MOVE FUNCTION TEST-NUMVAL-F(WS-ARG1-STR) TO WS-RES-STR       
           MOVE "FNTESTN " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-TRIM-SECTION SECTION.                                         
           MOVE FUNCTION TRIM(WS-ARG1-STR) TO WS-RES-STR                
           MOVE "FNTRIM  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-ULENGTH-SECTION SECTION.                                      
           COMPUTE WS-RES-NUM = FUNCTION ULENGTH(WS-ARG1-STR)           
           MOVE "FNULENGT" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-UPOS-SECTION SECTION.                                         
           COMPUTE WS-RES-NUM = FUNCTION UPOS(WS-ARG1-NUM, WS-ARG2-NUM) 
           MOVE "FNUPOS  " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-UPPER-CASE-SECTION SECTION.                                   
           MOVE FUNCTION UPPER-CASE(WS-ARG1-STR) TO WS-RES-STR          
           MOVE "FNUPPER " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-USUBSTR-SECTION SECTION.                                      
           COMPUTE WS-RES-NUM = FUNCTION USUBSTR(WS-ARG1-NUM, WS-ARG2-NU
      -    WS-ARG3-NUM)                                                 
           MOVE "FNUSUBST" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-USUPPLEMENTARY-SECTION SECTION.                               
           MOVE FUNCTION USUPPLEMENTARY(WS-ARG1-STR) TO WS-RES-STR      
           MOVE "FNUSUPPL" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-UUID4-SECTION SECTION.                                        
           MOVE FUNCTION UUID4 TO WS-RES-STR                            
           MOVE "FNUUID4 " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-UVALID-SECTION SECTION.                                       
           COMPUTE WS-RES-NUM = FUNCTION UVALID(WS-ARG1-STR)            
           MOVE "FNUVALID" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-UWIDTH-SECTION SECTION.                                       
           COMPUTE WS-RES-NUM = FUNCTION UWIDTH(WS-ARG1-NUM, WS-ARG2-NUM
           MOVE "FNUWIDTH" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-VARIANCE-SECTION SECTION.                                     
           COMPUTE WS-RES-NUM = FUNCTION VARIANCE(WS-ARG1-NUM, WS-ARG2-N
      -    WS-ARG3-NUM)                                                 
           MOVE "FNVARIAN" TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-WHEN-COMPILED-SECTION SECTION.                                
           MOVE FUNCTION WHEN-COMPILED TO WS-RES-STR                    
           MOVE "FNWHENC " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       FN-YEAR-TO-YYYY-SECTION SECTION.                                 
           COMPUTE WS-RES-NUM = FUNCTION YEAR-TO-YYYY(WS-ARG1-NUM,      
      -    WS-ARG2-NUM)                                                 
           MOVE "FNYEART " TO WS-SECID                                  
           MOVE FUNCTION TRIM(WS-RES-STR) TO WS-RES-STR                 
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
       KSDS-READ-SECTION SECTION.                                       
           MOVE "KSDS0001" TO WS-SECID                                  
           MOVE WS-ARG1-STR(1:8) TO KS-KEY                              
           READ SYS020 KEY IS KS-KEY                                    
           INVALID KEY MOVE "KSDSMISS" TO WS-RES-STR                    
           NOT INVALID KEY MOVE KS-DATA TO WS-RES-STR                   
           END-READ                                                     
           PERFORM LOG-SECTION                                          
       EXIT.                                                            
      