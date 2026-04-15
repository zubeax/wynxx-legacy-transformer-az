EIBMAPS   DFHMSD TYPE=&SYSPARM,                                        X
               CTRL=(FREEKB,FRSET),                                    X
               LANG=COBOL,                                             X
               MAPATTS=HILIGHT,                                        X
               MODE=INOUT,                                             X
               STORAGE=AUTO,                                           X
               TIOAPFX=YES
EIBMAPM   DFHMDI SIZE=(24,80),LINE=1,COLUMN=1
NEXT      DFHMDF POS=(1,1),LENGTH=16,INITIAL='SHWE',                   X
               ATTRB=(UNPROT,IC)
          DFHMDF POS=(1,29),LENGTH=21,                                 X
               INITIAL='Selected EIBLK Fields'
          DFHMDF POS=(3,9),LENGTH=16,                                  X
               INITIAL='           Date:'
EDATE     DFHMDF POS=(3,39),LENGTH=12
          DFHMDF POS=(4,9),LENGTH=16,                                  X
               INITIAL='     Time (UTC):'
ETIME     DFHMDF POS=(4,39),LENGTH=8
          DFHMDF POS=(5,9),LENGTH=16,                                  X
               INITIAL=' Transaction Id:'
ETRAN     DFHMDF POS=(5,39),LENGTH=4
          DFHMDF POS=(6,9),LENGTH=16,                                  X
               INITIAL='     Task Number:'
ETASK     DFHMDF POS=(6,39),PICOUT='ZZZZZZ9'
          DFHMDF POS=(7,9),LENGTH=16,                                  X
               INITIAL='     Terminal Id:'
ETERM     DFHMDF POS=(7,39),LENGTH=4
          DFHMDF POS=(8,9),LENGTH=16,                                  X
          DFHMDF POS=(10,9),LENGTH=17,INITIAL='Press PF3 to exit'
          DFHMSD TYPE=FINAL
          END                         