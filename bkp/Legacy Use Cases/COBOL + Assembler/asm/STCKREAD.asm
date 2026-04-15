
* STCKREAD -- return 8-byte TOD clock value (STCK token)
* INPUT : R1 -> parameter list: 1st word -> 8-byte field in caller
* OUTPUT: token stored at that address; R15 = 0
         PRINT NOGEN
STCKREAD CSECT
STCKREAD AMODE 31
STCKREAD RMODE ANY
         USING STCKREAD,R15
         SAVE  (14,12)
         LR    R12,R15
         USING STCKREAD,R12

         L     R1,0(R1)           * R1 -> address of 8-byte target
         STCK  0(,R1)             * Store TOD clock (64-bit)

         XR    R15,R15            * RC=0
         RETURN (14,12),RC=0
         DROP  R12
         END   STCKREAD
