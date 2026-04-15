***********************************************************************
* Pluralsight CICS Application Programming Fundamentals (COBOL)
* Mapset for the List Employees feature of the Employee application
***********************************************************************
ELSTMAP DFHMSD MODE=INOUT,                                             X
               CTRL=(FREEKB,FRSET),                                    X
               CURSLOC=YES,                                            X
               DSATTS=COLOR,                                           X
               MAPATTS=(COLOR,HILIGHT),                                X
               STORAGE=AUTO,                                           X
               LANG=COBOL,                                             X
               TIOAPFX=YES,                                            X
               TYPE=&SYSPARM

* Filters map 

EFILM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1 
TRANFL  DFHMDF POS=(1,1),LENGTH=4,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(1,31),LENGTH=18,ATTRB=(ASKIP,BRT),                 X
               INITIAL='Enter/Edit Filters'
        DFHMDF POS=(3,1),LENGTH=15,ATTRB=(ASKIP,BRT),                  X
               INITIAL='Select/Order by'
KEYSEL  DFHMDF POS=(3,17),LENGTH=1,ATTRB=(ASKIP,BRT),                  X
               INITIAL='1',HILIGHT=UNDERLINE
        DFHMDF POS=(3,19),LENGTH=25,ATTRB=(ASKIP,NORM),                X
               INITIAL='1 Employee Id    Matching'
MATCH   DFHMDF POS=(3,45),LENGTH=30,ATTRB=(ASKIP,BRT),                 X
               HILIGHT=UNDERLINE,COLOR=YELLOW
        DFHMDF POS=(3,76),LENGTH=0
        DFHMDF POS=(4,19),LENGTH=15,ATTRB=(ASKIP,NORM),                X
               INITIAL='2 Employee Name'
        DFHMDF POS=(6,1),LENGTH=7,ATTRB=(ASKIP,NORM),                  X
               INITIAL='Filters'
        DFHMDF POS=(8,8),LENGTH=25,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Department Id     Include'
DPTINCL DFHMDF POS=(8,34),LENGTH=8,ATTRB=(UNPROT,BRT),OCCURS=4,        X
               HILIGHT=UNDERLINE,COLOR=YELLOW
        DFHMDF POS=(8,70),LENGTH=0
        DFHMDF POS=(9,26),LENGTH=7,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Exclude'
DPTEXCL DFHMDF POS=(9,34),LENGTH=8,ATTRB=(UNPROT,BRT),OCCURS=4,        X
               HILIGHT=UNDERLINE,COLOR=YELLOW 
        DFHMDF POS=(9,70),LENGTH=0
        DFHMDF POS=(10,8),LENGTH=34,ATTRB=(ASKIP,NORM),                X
               INITIAL='Employment Date (YYYYMMDD)   After' 
EDATEA  DFHMDF POS=(10,43),LENGTH=8,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE,COLOR=YELLOW 
        DFHMDF POS=(10,52),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='  Before'
EDATEB  DFHMDF POS=(10,61),LENGTH=8,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE,COLOR=YELLOW 
        DFHMDF POS=(10,71),LENGTH=0 

* Message line 

MESSFL  DFHMDF POS=(23,1),LENGTH=79,ATTRB=(ASKIP,BRT)
 
* PF key help 
 
        DFHMDF POS=(24,1),LENGTH=43,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF3 Exit  PF10 Sign off  PF12 Clear Filters'

* List map                

ELSTM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1
TRANID  DFHMDF POS=(1,1),LENGTH=4,ATTRB=(ASKIP,NORM)
        DFHMDF POS=(1,33),LENGTH=13,ATTRB=(ASKIP,NORM),                X
               INITIAL='Employee List'
        DFHMDF POS=(1,60),LENGTH=4,ATTRB=(ASKIP,NORM),INITIAL='Page'
PAGEN   DFHMDF POS=(1,74),ATTRB=(ASKIP,NORM),PICOUT='ZZZZZ9'

* Filters in effect 
 
        DFHMDF POS=(3,1),LENGTH=9,ATTRB=(ASKIP,NORM),                  X
               INITIAL='Filters: '
FLTRS   DFHMDF POS=(3,11),LENGTH=69,ATTRB=(ASKIP,NORM)
 
* Column headings 

        DFHMDF POS=(5,1),LENGTH=39,ATTRB=(ASKIP,BRT),                  X
               INITIAL='  Emp Id   Primary Name                '
        DFHMDF POS=(5,41),LENGTH=39,ATTRB=(ASKIP,BRT),                 X
               INITIAL='  Job Title                     Dept Id'

* The combination of IC and PROT produces a level 4 assembly warning
SELCT01 DFHMDF POS=(6,1),LENGTH=1,ATTRB=(IC,PROT),                     X
        HILIGHT=UNDERLINE
EMPID01 DFHMDF POS=(6,3),LENGTH=8,ATTRB=(ASKIP,NORM)
PRMNM01 DFHMDF POS=(6,12),LENGTH=29,ATTRB=(ASKIP,NORM)
JOBTL01 DFHMDF POS=(6,42),LENGTH=29,ATTRB=(ASKIP,NORM)
DPTID01 DFHMDF POS=(6,72),LENGTH=8,ATTRB=(ASKIP,NORM)
SELCT02 DFHMDF POS=(7,1),LENGTH=1,ATTRB=(PROT),                        X
        HILIGHT=UNDERLINE
EMPID02 DFHMDF POS=(7,3),LENGTH=8,ATTRB=(ASKIP,NORM)
PRMNM02 DFHMDF POS=(7,12),LENGTH=29,ATTRB=(ASKIP,NORM)
JOBTL02 DFHMDF POS=(7,42),LENGTH=29,ATTRB=(ASKIP,NORM)
DPTID02 DFHMDF POS=(7,72),LENGTH=8,ATTRB=(ASKIP,NORM)
SELCT03 DFHMDF POS=(8,1),LENGTH=1,ATTRB=(PROT),                        X
        HILIGHT=UNDERLINE
EMPID03 DFHMDF POS=(8,3),LENGTH=8,ATTRB=(ASKIP,NORM)
PRMNM03 DFHMDF POS=(8,12),LENGTH=29,ATTRB=(ASKIP,NORM)
JOBTL03 DFHMDF POS=(8,42),LENGTH=29,ATTRB=(ASKIP,NORM)
DPTID03 DFHMDF POS=(8,72),LENGTH=8,ATTRB=(ASKIP,NORM)

* Message line

MESS    DFHMDF POS=(23,1),LENGTH=79,ATTRB=(ASKIP,NORM)

* PF key help
 
        DFHMDF POS=(24,1),LENGTH=27,ATTRB=(ASKIP,NORM),                X
               INITIAL='ENTER Details  PF3 Filters'
HLPPF7  DFHMDF POS=(24,29),LENGTH=9,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF7 Prev '
HLPPF8  DFHMDF POS=(24,39),LENGTH=26,ATTRB=(ASKIP,NORM),               X
               INITIAL='PF10 Sign off  PF12 Cancel'                              
        DFHMSD TYPE=FINAL
        END        