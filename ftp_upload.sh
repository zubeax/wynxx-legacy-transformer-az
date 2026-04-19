#!/bin/bash

while read recln filename destname
do

ftp -nv << EOF
open vs01.kippel.de
user ibmuser password
ascii
quote site LRECL=$recln
quote site RECFM=FB 
quote site TRACKS
quote site PRIM=5
quote site SEC=5
cd 'WYNXX.ECBSTATS.LOADDATA'
put ./db/sample_load/$filename $destname
quit 
EOF

done<<EOT
    59 LOOKUP_CHANNEL.csv       CHANNEL
    17 LOOKUP_COUNTRY.csv       COUNTRY
    55 LOOKUP_MCC.csv           MCC
    39 LOOKUP_SCA_REASON.csv    SCAREASN
    46 LOOKUP_SCHEME.csv        SCHEME
    72 LOOKUP_SERVICE.csv       SERVICE
   170 PAYMENT_TRANSACTIONS.csv PMNTTRNS
EOT

exit 0
