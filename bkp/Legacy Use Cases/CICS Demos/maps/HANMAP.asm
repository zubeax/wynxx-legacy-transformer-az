HANMAP  DFHMSD MODE=INOUT,                                             X
               CTRL=(FREEKB,FRSET),                                    X
               CURSLOC=YES,                                            X
               DSATTS=COLOR,                                           X
               MAPATTS=(COLOR,HILIGHT),                                X
               STORAGE=AUTO,                                           X
               LANG=COBOL,                                             X
               TIOAPFX=YES,                                            X
               TYPE=&SYSPARM
HANM    DFHMDI SIZE=(24,80),LINE=1,COLUMN=1
        DFHMDF POS=(1,27),LENGTH=24,ATTRB=(ASKIP,NORM),                X
               INITIAL='Demo of HANDLE CONDITION'
        DFHMDF POS=(3,1),LENGTH=73,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Program HANCON issues an EXEC CICS READ FILE coX
               mmand for file EREGUSR'
        DFHMDF POS=(4,1),LENGTH=45,ATTRB=(ASKIP,NORM),                 X
               INITIAL='with the following HANDLE commands in effect:'              
        DFHMDF POS=(6,1),LENGTH=28,ATTRB=(ASKIP,NORM),                 X
               INITIAL='EXEC CICS HANDLE CONDITION'
        DFHMDF POS=(7,5),LENGTH=31,ATTRB=(ASKIP,NORM),                 X
               INITIAL='FILENOTFOUND(8100-FileNotFound)'       
        DFHMDF POS=(8,5),LENGTH=31,ATTRB=(ASKIP,NORM),                 X
               INITIAL='NOTFND(8200-RecordNotFound)'       
        DFHMDF POS=(9,5),LENGTH=31,ATTRB=(ASKIP,NORM),                 X
               INITIAL='INVREQ(8300-InvalidRequest)'       
        DFHMDF POS=(10,5),LENGTH=31,ATTRB=(ASKIP,NORM),                X
               INITIAL='ERROR(8900-UnexpectedError)'       
        DFHMDF POS=(11,1),LENGTH=8,ATTRB=(ASKIP,NORM),                 X
               INITIAL='END-EXEC'       
        DFHMDF POS=(12,1),LENGTH=25,ATTRB=(ASKIP,NORM),                X
               INITIAL='EXEC CICS IGNORE CONDITION'       
        DFHMDF POS=(13,5),LENGTH=7,ATTRB=(ASKIP,NORM),                 X
               INITIAL='LENGERR'       
        DFHMDF POS=(14,1),LENGTH=8,ATTRB=(ASKIP,NORM),                 X
               INITIAL='END-EXEC'       
        DFHMDF POS=(16,1),LENGTH=70,ATTRB=(ASKIP,NORM),                X
               INITIAL='Change the parameters below to elicit one of thX
               e exception conditions'
        DFHMDF POS=(18,1),LENGTH=20,ATTRB=(ASKIP,NORM),                X
               INITIAL='EXEC CICS READ FILE('
FILENM  DFHMDF POS=(18,22),LENGTH=8,ATTRB=(UNPROT,BRT,IC),             X
               HILIGHT=UNDERLINE
        DFHMDF POS=(18,31),LENGTH=1,ATTRB=(ASKIP,NORM),                X
               INITIAL=')'
        DFHMDF POS=(19,5),LENGTH=7,ATTRB=(ASKIP,NORM),                 X
               INITIAL='RIDFLD('
RIDVAL  DFHMDF POS=(19,13),LENGTH=8,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE
        DFHMDF POS=(19,22),LENGTH=1,ATTRB=(ASKIP,NORM),                X
               INITIAL=')'
        DFHMDF POS=(20,5),LENGTH=7,ATTRB=(ASKIP,NORM),                 X
               INITIAL='LENGTH('
LENVAL  DFHMDF POS=(20,13),LENGTH=4,ATTRB=(UNPROT,BRT),                X
               HILIGHT=UNDERLINE
        DFHMDF POS=(20,18),LENGTH=1,ATTRB=(ASKIP,NORM),                X
               INITIAL=')'
        DFHMDF POS=(21,5),LENGTH=10,ATTRB=(ASKIP,NORM),                X
               INITIAL='KEYLENGTH('
KEYLEN  DFHMDF POS=(21,16),LENGTH=4,ATTRB=(UNPROT,BRT)                 X
               HILIGHT=UNDERLINE
        DFHMDF POS=(21,21),LENGTH=1,ATTRB=(ASKIP,NORM),                X
               INITIAL=')'
        DFHMDF POS=(23,1),LENGTH=7,ATTRB=(ASKIP,NORM),                 X
               INITIAL='Result:'
RESULT  DFHMDF POS=(23,9),LENGTH=50,ATTRB=(ASKIP,BRT)
        DFHMSD TYPE=FINAL
        END               



        



















