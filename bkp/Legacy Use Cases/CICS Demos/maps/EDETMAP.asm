***********************************************************************
* Pluralsight CICS Application Programming Fundamentals (COBOL)
* View Employee Details
***********************************************************************
EDETMAP DFHMSD MODE=INOUT,                                             X
               CTRL=(FREEKB,FRSET),                                    X
               CURSLOC=YES,                                            X
               DSATTS=COLOR,                                           X
               MAPATTS=(COLOR,HILIGHT),                                X
               COLOR=BLUE,                                             X
               STORAGE=AUTO,                                           X
               LANG=COBOL,                                             X
               TIOAPFX=YES,                                            X
               TYPE=&SYSPARM
EDETM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1
TRANID  DFHMDF POS=(1,1),LENGTH=4,ATTRB=(ASKIP,NORM)
        DFHMDF POS=(1,32),LENGTH=16,ATTRB=(ASKIP,NORM),                X
               INITIAL='Employee Details'
        DFHMDF POS=(3,1),LENGTH=3,ATTRB=(ASKIP,NORM),INITIAL='Id:'
EMPLID  DFHMDF POS=(3,5),LENGTH=8,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(3,13),LENGTH=21,ATTRB=(ASKIP,NORM),                X
               INITIAL='        Primary Name:'
PRMNM   DFHMDF POS=(3,35),LENGTH=38,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(4,1),LENGTH=10,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Honorific:'                    
HONOR   DFHMDF POS=(4,12),LENGTH=8,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(4,21),LENGTH=21,ATTRB=(ASKIP,NORM),                X
               INITIAL='          ShortName:'
SHNAME  DFHMDF POS=(4,35),LENGTH=38,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(5,1),LENGTH=10,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Full Name:'                            
FLNAME  DFHMDF POS=(6,1),LENGTH=79,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(7,1),LENGTH=10,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Job Title:'
JBTITL  DFHMDF POS=(7,12),LENGTH=38,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(9,1),LENGTH=9,ATTRB=(ASKIP,NORM),                  X
               INITIAL='Dept. Id:'                                   
DEPTID  DFHMDF POS=(9,11),LENGTH=8,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(9,20),LENGTH=13,ATTRB=(ASKIP,NORM)                 X
               INITIAL='  Dept. Name:'
DEPTNM  DFHMDF POS=(9,34),LENGTH=38,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(10,1),LENGTH=11,ATTRB=(ASKIP,NORM),                X
               INITIAL='Start Date:'                               
STDATE  DFHMDF POS=(10,13),LENGTH=10,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(10,24),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='End Date:'
ENDATE  DFHMDF POS=(10,34),LENGTH=10,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(11,1),LENGTH=20,ATTRB=(ASKIP,NORM),                X
               INITIAL='Last Appraisal Date:'                   
APPRDT  DFHMDF POS=(11,22),LENGTH=10,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(11,33),LENGTH=7,ATTRB=(ASKIP,NORM),                X
               INITIAL='Result:'                                
APPRRS  DFHMDF POS=(11,41),LENGTH=30,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(12,1),LENGTH=16,ATTRB=(ASKIP,NORM),                X
               INITIAL='Record Deletion:'
DELFLG  DFHMDF POS=(12,18),LENGTH=1,ATTRB=(ASKIP,BRT)
DELDSC  DFHMDF POS=(12,20),LENGTH=9,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(12,30),LENGTH=6,ATTRB=(ASKIP,NORM),INITIAL='as of:'               
DELDT   DFHMDF POS=(12,37),LENGTH=10,ATTRB=(ASKIP,BRT)
MESS    DFHMDF POS=(23,1),LENGTH=79,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(24,1),LENGTH=18,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF3 Save and Exit'
HLPPF7  DFHMDF POS=(24,20),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF7 Prev'
HLPPF8  DFHMDF POS=(24,30),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF Next'
        DFHMDF POS=(24,40),LENGTH=26,ATTRB=(ASKIP,NORM),               X
               INITIAL='PF10 Sign off  PF12 Cancel'
        DFHMSD TYPE=FINAL
        END                      
MESS    DFHMDF POS=(23,1),LENGTH=79,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(24,1),LENGTH=19,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF3 Exit  PF5 Edit'
HLPPF7  DFHMDF POS=(24,21),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF7 Prev'
HLPPF8  DFHMDF POS=(24,31),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF Next'
        DFHMDF POS=(24,41),LENGTH=26,ATTRB=(ASKIP,NORM),               X
               INITIAL='PF10 Sign off  PF12 Cancel'
        DFHMSD TYPE=FINAL
        END              