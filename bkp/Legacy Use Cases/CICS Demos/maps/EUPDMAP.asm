***********************************************************************
* Pluralsight CICS Application Programming Fundamentals (COBOL)
* Update Employee Details
***********************************************************************
EUPDMAP DFHMSD MODE=INOUT,                                             X
               CTRL=(FREEKB,FRSET),                                    X
               CURSLOC=YES,                                            X
               DSATTS=COLOR,                                           X
               MAPATTS=(COLOR,HILIGHT),                                X
               COLOR=BLUE,                                             X
               STORAGE=AUTO,                                           X
               LANG=COBOL,                                             X
               TIOAPFX=YES,                                            X
               TYPE=&SYSPARM
EUPDM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1
TRANID  DFHMDF POS=(1,1),LENGTH=4,ATTRB=(ASKIP,NORM)
        DFHMDF POS=(1,28),LENGTH=23,ATTRB=(ASKIP,NORM),                X
               INITIAL='Update Employee Details'
        DFHMDF POS=(3,1),LENGTH=3,ATTRB=(ASKIP,NORM),INITIAL='Id:'
EMPLID  DFHMDF POS=(3,5),LENGTH=8,ATTRB=(UNPROT,BRT,IC),               X
               HILIGHT=UNDERLINE
PRMNML  DFHMDF POS=(3,14),LENGTH=21,ATTRB=(ASKIP,NORM),                X
               INITIAL='        Primary Name:'
PRMNM   DFHMDF POS=(3,38),LENGTH=38,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE
        DFHMDF POS=(3,75),LENGTH=0
HONORL  DFHMDF POS=(4,1),LENGTH=10,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Honorific:'                    
HONOR   DFHMDF POS=(4,12),LENGTH=8,ATTRB=(UNPROT,BRT),                 X
               HILIGHT=UNDERLINE
SHNAMEL DFHMDF POS=(4,21),LENGTH=21,ATTRB=(ASKIP,NORM),                X
               INITIAL='          Short Name:'
SHNAME  DFHMDF POS=(4,44),LENGTH=35,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE
FLNAMEL DFHMDF POS=(5,1),LENGTH=10,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Full Name:'                            
FLNAME  DFHMDF POS=(6,1),LENGTH=79,ATTRB=(UNPROT,BRT),                 X
               HILIGHT=UNDERLINE
JBTITLL DFHMDF POS=(7,1),LENGTH=10,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Job Title:'
JBTITL  DFHMDF POS=(7,12),LENGTH=38,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE
        DFHMDF POS=(7,51),LENGTH=0       
DEPTIDL DFHMDF POS=(9,1),LENGTH=9,ATTRB=(ASKIP,NORM),                  X
               INITIAL='Dept. Id:'                                   
DEPTID  DFHMDF POS=(9,11),LENGTH=8,ATTRB=(UNPROT,BRT),                 X
               HILIGHT=UNDERLINE
DEPTNML  DFHMDF POS=(9,20),LENGTH=13,ATTRB=(ASKIP,NORM)                X
               INITIAL='  Dept. Name:'
DEPTNM  DFHMDF POS=(9,34),LENGTH=38,ATTRB=(ASKIP,BRT)
STDATEL DFHMDF POS=(10,1),LENGTH=11,ATTRB=(ASKIP,NORM),                X
               INITIAL='Start Date:'                               
STDATE  DFHMDF POS=(10,13),LENGTH=10,ATTRB=(UNPROT,BRT),               X
               HILIGHT=UNDERLINE
ENDATEL DFHMDF POS=(10,24),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='End Date:'
ENDATE  DFHMDF POS=(10,34),LENGTH=10,ATTRB=(UNPROT,BRT),               X
               HILIGHT=UNDERLINE
        DFHMDF POS=(10,45),LENGTH=0       
APPRDTL DFHMDF POS=(11,1),LENGTH=20,ATTRB=(ASKIP,NORM),                X
               INITIAL='Last Appraisal Date:'                   
APPRDT  DFHMDF POS=(11,22),LENGTH=10,ATTRB=(UNPROT,BRT),               X
               HILIGHT=UNDERLINE
APPRRSL DFHMDF POS=(11,33),LENGTH=7,ATTRB=(ASKIP,NORM),                X
               INITIAL='Result:'                                
APPRRS  DFHMDF POS=(11,41),LENGTH=30,ATTRB=(UNPROT,BRT),               X
               HILIGHT=UNDERLINE
        DFHMDF POS=(11,72),LENGTH=0       
DELFLL  DFHMDF POS=(12,1),LENGTH=16,ATTRB=(ASKIP,NORM),                X
               INITIAL='Record Deletion:'
DELFLG  DFHMDF POS=(12,18),LENGTH=1,ATTRB=(ASKIP,BRT)
DELDSC  DFHMDF POS=(12,20),LENGTH=9,ATTRB=(ASKIP,BRT)
DELDTL  DFHMDF POS=(12,30),LENGTH=6,ATTRB=(ASKIP,NORM),INITIAL='as of:'               
DELDT   DFHMDF POS=(12,37),LENGTH=10,ATTRB=(ASKIP,BRT)
MESS    DFHMDF POS=(23,1),LENGTH=79,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(24,1),LENGTH=17,ATTRB=(ASKIP,NORM),                X
               INITIAL='ENT Val  3 Exit  '
HLPPF4  DFHMDF POS=(24,19),LENGTH=8,ATTRB=(ASKIP,NORM),                X
               INITIAL='4 Save  '
HLPPF7  DFHMDF POS=(24,28),LENGTH=8,ATTRB=(ASKIP,NORM),                X
               INITIAL='7 Prev  '
HLPPF8  DFHMDF POS=(24,37),LENGTH=8,ATTRB=(ASKIP,NORM),                X
               INITIAL='8 Next  '
        DFHMDF POS=(24,45),LENGTH=13,ATTRB=(ASKIP,NORM),               X
               INITIAL='10 Sign off  '
HLPPF11 DFHMDF POS=(24,59),LENGTH=11,ATTRB=(ASKIP,NORM),               X
               INITIAL='11 Delete  ' 
        DFHMDF POS=(24,71),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='12 Cancel'
 
* Delete confirmation dialog 
  
EDELM   DFHMDI SIZE=(8,40),LINE=12,COLUMN=20,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(1,1),LENGTH=39,                                    X
               INITIAL=' /===================================\ '
        DFHMDF POS=(2,1),LENGTH=2,INITIAL=' |'
        DFHMDF POS=(2,4),LENGTH=35,                                    X
               INITIAL='     Confirm Record Deletion'
        DFHMDF POS=(2,38),LENGTH=2,INITIAL='| '
        DFHMDF POS=(3,1),LENGTH=2,INITIAL=' |'
        DFHMDF POS=(3,38),LENGTH=2,INITIAL='| '
        DFHMDF POS=(4,1),LENGTH=2,INITIAL=' |'
        DFHMDF POS=(4,4),LENGTH=13,INITIAL='Employee Id: '
DELEMP  DFHMDF POS=(4,18,LENGTH=8)        
        DFHMDF POS=(4,38),LENGTH=2,INITIAL='| '
        DFHMDF POS=(5,1),LENGTH=2,INITIAL=' |'
        DFHMDF POS=(5,38),LENGTH=2,INITIAL='| '
        DFHMDF POS=(6,1),LENGTH=2,INITIAL=' |'
        DFHMDF POS=(6,4),LENGTH=35,                                     X
               INITIAL='PF11 Confirm Delete  PF12 Cancel'
        DFHMDF POS=(6,38),LENGTH=2,INITIAL='| '
        DFHMDF POS=(7,1),LENGTH=2,INITIAL=' |'
        DFHMDF POS=(7,38),LENGTH=2,INITIAL='| '
        DFHMDF POS=(8,1),LENGTH=39,                                     X
               INITIAL=' \===================================/ '
        DFHMSD TYPE=FINAL
        END                      