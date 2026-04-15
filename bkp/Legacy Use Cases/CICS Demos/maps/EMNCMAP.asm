***********************************************************************
* Pluralsight CICS Application Programming Fundamentals (COBOL)
* Menu using cursor position for selections
***********************************************************************
EMNUMAP DFHMSD MODE=INOUT,                                             X
               CTRL=(FREEKB,FRSET),                                    X
               CURSLOC=YES,                                            X
               DSATTS=COLOR,                                           X
               MAPATTS=(COLOR,HILIGHT),                                X
               STORAGE=AUTO,                                           X
               LANG=COBOL,                                             X
               TIOAPFX=YES,                                            X
               TYPE=&SYSPARM
EMNUM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1,COLOR=TURQUOISE 
TRANID  DFHMDI POS=(1,1),LENGTH=4,ATTRB=(ASKIP,BRT,IC)
        DFHMDF POS=(1,29),LENGTH=20,ATTRB=(ASKIP,BRT),                 X
               INITIAL='Employee Application'
        DFHMDF POS=(3,1),LENGTH=79,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Position the cursor next to your selection and X
               press Enter.' 
SEL1    DFHMDF POS=(5,8),LENGTH=1,ATTRB=(PROT,BRT,IC),                 X
               HIGHLIGHT=UNDERLINE
        DFHMDF POS=(5,10),LENGTH=60,ATTRB=(ASKIP,NORM),                X
               INITIAL='List Employees'
SEL2    DFHMDF POS=(6,8),LENGTH=1,ATTRB=(PROT,BRT),                    X
               HILIGHT=UNDERLINE
        DFHMDF POS=(6,10),LENGTH=60,ATTRB=(ASKIP,NORM),                X
               INITIAL='View Employee Details'                             
MESS    DFHMDF POS=(23,1),LENGTH=79,ATTRB=(ASKIP,BRT)
        DFHMDF POS=(24,1),LENGTH=79,ATTRB=(ASKIP,NORM),                X
               INITIAL='PF3 Exit  PF10 Sign off'
        DFHMSD TYPE=FINAL
        END                                                          