
* STCKPARSE -- from STCK token -> YYYY DDD HH MM SS (text)
* CALLING SEQUENCE (by reference):
*   1: 8-byte STCK token
*   2: 4-byte char YEAR (YYYY)
*   3: 3-byte char DDD  (day-of-year)
*   4: 2-byte char HH
*   5: 2-byte char MM
*   6: 2-byte char SS

         PRINT NOGEN
STCKPARSE CSECT
STCKPARSE AMODE 31
STCKPARSE RMODE ANY
         USING STCKPARSE,R15
         SAVE  (14,12)
         LR    R12,R15
         USING STCKPARSE,R12

         LR    R10,R1              * R10 -> parm list
         L     R2,0(R10)           * R2 = &STCKIN
         L     R3,4(R10)           * R3 = &YEAR-CHAR(4)
         L     R4,8(R10)           * R4 = &DDD-CHAR(3)
         L     R5,12(R10)          * R5 = &HH-CHAR(2)
         L     R6,16(R10)          * R6 = &MM-CHAR(2)
         L     R7,20(R10)          * R7 = &SS-CHAR(2)

* -- Convert STCK -> DEC time + YYYYDDD date into WORK16 (TIME-compatible)
         LA    R8,WORK16
         LA    R9,STCKLST          * list form anchor
         STCKCONV STCKVAL=(R2),CONVVAL=(R8),TIMETYPE=DEC,  X
               DATETYPE=YYYYDDD,MF=(E,(R9))

* Unpack TIME portion: packed HHMMSSTHMIJU in first 6 bytes of WORK16
* We only need HH,MM,SS  -> TZ holds 12 digits zoned (HHMMSSTHMIJU)
         UNPK  TZ(13),WORK16(6)
         MVC   0(2,R5),TZ          * HH
         MVC   0(2,R6),TZ+2        * MM
         MVC   0(2,R7),TZ+4        * SS

* Unpack DATE portion (YYYYDDD) returned in the date half of WORK16
         UNPK  DZ(9),WORK16+8(4)   * DZ gets 8 digits (0YYYYDDD)
         MVC   0(4,R3),DZ+1        * YYYY
         MVC   0(3,R4),DZ+5        * DDD

         XR    R15,R15
         RETURN (14,12),RC=0

* ------ Working storage ----------
WORK16   DS    CL16                * STCKCONV output area (time+date)
TZ       DS    CL13                * zoned time digits (HHMMSSTHMIJU)
DZ       DS    CL9                 * zoned date digits (0YYYYDDD)
STCKLST  STCKCONV MF=L             * list form for execute
         DROP  R12
         END   STCKPARSE
