***********************************************************************
* Pluralsight CICS Application Programming Fundamentals (COBOL)
* Employee Application user sign-on
***********************************************************************
ESONMAP DFHMSD MODE=INOUT,                                             X
               CTRL=(FREEKB,FRSET),                                    X
               CURSLOC=YES,                                            X
               DSATTS=COLOR,                                           X
               MAPATTS=(COLOR,HILIGHT),                                X
               STORAGE=AUTO,                                           X
               LANG=COBOL,                                             X
               TIOAPFX=YES,                                            X
               TYPE=&SYSPARM
ESONM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1
TRANID  DFHMDF POS=(1,1),LENGTH=4,ATTRB=(ASKIP,NORM)     
        DFHMDF POS=(1,25),LENGTH=28,ATTRB=(ASKIP,NORM),                X
               INITIAL='Employee Application Sign On'
        DFHMDF POS=(3,1),LENGTH=66,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Please enter your Employee Application user id X       
               and password below.'
        DFHMDF POS=(5,29),LENGTH=8,ATTRB=(ASKIP,NORM),                 X
               INITIAL='User Id:'
USERID  DFHMDF POS=(5,29),LENGTH=8,ATTRB=(UNPROT,BRT,IC),              X
               HILIGHT=UNDERLINE
        DFHMDF POS=(5,38),LENGTH=13,ATTRB=(ASKIP,NORM),                X
               INITIAL='    Password:'
PASSWD  DFHMDF POS=(5,53),LENGTH=8,ATTRB=(UNPROT,DRK),                 X
               HILIGHT=UNDERLINE
        DFHMDF POS=(5,62),LENGTH=0
MESS    DFHMDF POS=(7,1),LENGTH=79,ATTRB=(ASKIP,NORM)
        DFHMDF POS=(9,1),LENGTH=8,ATTRB=(ASKIP,NORM),                  X
               INITIAL='PF3 Exit'                   
        DFHMSD TYPE=FINAL
        END