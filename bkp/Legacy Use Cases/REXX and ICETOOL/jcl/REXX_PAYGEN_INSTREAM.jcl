//REXXICET JOB (ACC1,ACC2,ACCT3),'PRT3/ZUBER',
// CLASS=A,MSGCLASS=X,MSGLEVEL=(1,1),
// REGION=256M,NOTIFY=&SYSUID
//* --------------------------------------------------------------
//* STEP1 runs a REXX script from an instream dataset to generate
//* an output file random payment transactions. the number to
//* generate is specified as a parameter.
//* --------------------------------------------------------------
//* RESET OUTPUT DATASET
//* --------------------------------------------------------------
//DELETE EXEC PGM=IEFBR14
//DELETE1 DD DSN=RFPLT.PAYGEN.SORTED,SPACE=(TRK,0),
// DISP=(MOD,DELETE,DELETE)
//* --------------------------------------------------------------
//* IRXJCL with REXX in-stream
//* INACCTS & INPURP also in-stream (50 lines each)
//* --------------------------------------------------------------
//* for irxjcl to accept a script from a sequential file
//*                           +- '~' must be replaced with x'00'
//*                           V
//STEP1 EXEC PGM=IRXJCL,PARM='~ MIN=20260101 MAX=20261231 COUNT=1000'
//SYSTSPRT DD SYSOUT=*
//SYSTSIN DD DUMMY
//SYSEXEC DD *
    /* REXX */
    /* trace r */
    parse upper arg argstr
    minDate = ''
    maxDate = ''
    count = ''
    do while argstr <> ''
        parse var argstr token argstr
        parse var token key '=' val
        select
        when key = 'MIN' then minDate = strip(val)
        when key = 'MAX' then maxDate = strip(val)
        when key = 'COUNT' then count = strip(val)
        otherwise nop
        end
    end
    if minDate = '' | maxDate = '' | count = '' then do
        call usage
        exit 12
    end
    if length(minDate) <> 8 | length(maxDate) <> 8 then do
        say 'ERROR: MIN and MAX must be in YYYYMMDD format.'
        call usage
        exit 12
    end
    if datatype(count,'W') = 0 then do
        say 'ERROR: COUNT must be a positive integer.'
        call usage
        exit 12
    end
    count = count + 0
    if count < 1 then do
        say 'ERROR: COUNT must be >= 1.'
        exit 12
    end
    minBase = date('B', minDate, 'S')
    maxBase = date('B', maxDate, 'S')
    if datatype(minBase,'W') = 0 | datatype(maxBase,'W') = 0 then do
        say 'ERROR: Invalid MIN or MAX date.'
        exit 12
    end
    if minBase > maxBase then do
        tmp = minBase; minBase = maxBase; maxBase = tmp
    end
    accRc = 0
    RAWACCTS. = ''
    'EXECIO * DISKR INACCTS (STEM RAWACCTS. OPEN FINIS)'
    accRc = rc
    if accRc <> 0 then do
        say 'ERROR: EXECIO RC' accRc 'reading INACCTS.'
        exit 12
    end
    accCount = 0
    Do i = 1 To RAWACCTS.0
        line = strip(RAWACCTS.i)
        if line = '' then iterate
        parse var line acctno ',' name
        if name = '' then parse var line acctno ';' name
        if name = '' then parse var line acctno name
        acctno = strip(acctno)
        name = strip(name)
        if acctno = '' | name = '' then iterate
        accCount = accCount + 1
        ACC.accCount.xNUM  = acctno
        ACC.accCount.xNAME = name
    end
    if accCount < 2 then do
        say 'ERROR: Need at least 2 accounts in INACCTS',
        ' to form debtor/creditor pairs.'
        exit 12
    end
    purRc = 0
    RAWPURP. = ''
    'EXECIO * DISKR INPURP (STEM RAWPURP. OPEN FINIS)'
    purRc = rc
    if purRc <> 0 then do
        say 'ERROR: EXECIO RC' purRc 'reading INPURP.'
        exit 12
    end
    purCount = 0
    Do i = 1 To RAWPURP.0
        p = strip(RAWPURP.i)
        if p = '' then iterate
        purCount = purCount + 1
        PURP.purCount = p
    end
    if purCount < 1 then do
        say 'ERROR: Need at least 1 purpose in INPURP.'
        exit 12
    end
    LRECL = 200
    outN = 0
    Do t = 1 To count
        ci = random(1, accCount)
        do until di <> ci
            di = random(1, accCount)
        end
        pi = random(1, purCount)
        bday = random(minBase, maxBase)
        exDate = date('S', bday, 'B')
        euros = random(1, 1000)
        cents = random(0, 99)
        amtCents = (euros * 100) + cents
        credAcct = ACC.ci.xNUM
        credName = ACC.ci.xNAME
        debtAcct = ACC.di.xNUM
        debtName = ACC.di.xNAME
        purpose = PURP.pi
        line = exDate || ' ' || ,
        left(substr(credAcct,1,34),34) || ,
        left(substr(credName,1,30),30) || ,
        left(substr(debtAcct,1,34),34) || ,
        left(substr(debtName,1,30),30) || ,
        right(amtCents,10,'0') || 'EUR' || ,
        left(substr(purpose,1,50),50)
        if length(line) < LRECL then do
            line = line || copies(' ', LRECL - length(line))
        end
        if length(line) > LRECL then line = substr(line,1,LRECL)
        outN = outN + 1
        OUT.outN = line
        say 'line[' length(line) ']: ' line
    end
    OUT.0 = outN
    wrRc = 0
    'EXECIO' outN 'DISKW OUTTRN (STEM OUT. OPEN FINIS)'
    wrRc = rc
    if wrRc <> 0 then do
       say 'ERROR: EXECIO RC' wrRc 'writing OUTTRN.'
       exit 12
    end
    say 'Generated' outN 'transactions.'
    exit 0
    usage:
    say 'Usage (PARM): MIN=YYYYMMDD MAX=YYYYMMDD COUNT=nnn'
    say 'Example: MIN=20260101 MAX=20261231 COUNT=500'
    return
/*
//INACCTS DD *
DE36020590162320564127,Quinn Koch
DE66131896599353689436,Ben Seidel
DE63750198830411180484,Contoso GmbH & Co. KG
DE07556482680170378816,Mara Meyer
DE02583396189917238642,Adventure GmbH & Co. KG
DE85921517881871421455,Proseware SE
DE59477914354788188848,Adventure AG
DE76149848827547979138,Oskar Schmidt
DE15023902862321032571,Litware KG
DE82236389212168306946,Alex Klein
DE98374611315343700064,Paula Maier
DE49183346440724713977,Rita Richter
DE06372682457323844516,Alex Keller
DE10206435611013268520,Litware GmbH & Co. KG
DE64437226331745825585,Quinn Hoffmann
DE96761882092449255397,Contoso AG
DE38103441189410500147,Fabrikam AG
DE28981637619479732227,Quinn Schmidt
DE55557838158640221605,Rita Schmidt
DE46090469733610342984,BlueYonder AG
DE10404181642948985151,Fabrikam SE
DE38306209127835356669,Tailspin KG
DE96574264735061382460,Ben Kaiser
DE70312706818192080594,Alex Koch
DE92477699437680125378,Paula Koch
DE18548809490185756586,Ben Becker
DE29444971656535712654,Xenia Schmidt
DE54974848898756829204,Lena Richter
DE60888846536321629072,Woodgrove GmbH
DE21277002840775597529,Quinn Kaiser
DE34168366837665355824,Iris Schmidt
DE03430009886550923765,Northwind SE
DE48182853128764673871,Sven Richter
DE36548185906401566308,Woodgrove KG
DE30718799641701007310,Fabrikam KG
DE70399245471575487149,Timo Richter
DE30223407389379090891,Tailspin KG
DE81645678582410025493,Lena Meyer
DE92644593209952145396,Xenia Lang
DE34394151979528117237,Adventure GmbH & Co. KG
DE38420312302875094337,Yara Maier
DE13601837900667689853,Zoe Schmidt
DE67056649876122798283,Adventure SE
DE12688681543669046434,Wiebke Kaiser
DE04501912569920198451,Gina Maier
DE90054950380801446402,Litware GmbH & Co. KG
DE97263755668974886894,Oskar Koch
DE93403455728592189244,Zoe Koch
DE16746926480371082818,BlueYonder KG
DE33566653891325211034,WideWorld AG
/*
//INPURP DD *
Maintenance 3185
Reimbursement
Sponsorship
Bonus Aug 2026
Donation
Commission Jan 2025
Sponsorship
Donation
Royalty
Hardware 8573
Commission Jul 2027
Membership Fee
Rent Sep 2024
Hardware 4671
Reimbursement
Support 8233
Rent Dec 2025
Commission Feb 2027
Bonus Mar 2026
Conference Fee
Invoice 2348
Invoice 9056
Conference Fee
Insurance Sep 2025
Travel Expenses
Travel Expenses
Consulting 3412
Royalty
Reimbursement
Hosting 3122
Marketing
Consulting 7014
Membership Fee
Refund
Donation
Insurance Jun 2027
Commission Mar 2026
Refund
Software License 6289
Conference Fee
Bonus Dec 2025
Invoice 3181
Refund
Hosting 1286
Membership Fee
Conference Fee
Maintenance 1778
Marketing
Training 5784
Invoice 6558
/*
//OUTTRN DD DSN=&&UNSORTED,DISP=(MOD,PASS),
// DCB=(RECFM=FB,LRECL=200,BLKSIZE=200),
// SPACE=(TRK,(10,5),RLSE),UNIT=SYSDA
//* --------------------------------------------------------------
//* Sort by date (1-8) + creditor account (10-43) via ICETOOL
//* --------------------------------------------------------------
//STEP2 EXEC PGM=ICETOOL
//TOOLMSG DD SYSOUT=*
//DFSMSG DD SYSOUT=*
//SYS010 DD DSN=&&UNSORTED,DISP=(MOD,DELETE,DELETE)
//* ------------------------------------------
//* CAVEAT : ICETOOL requires BLKSIZE == LRECL
//* ------------------------------------------
//SYS020 DD DSN=RFPLT.PAYGEN.SORTED,DISP=(NEW,CATLG,DELETE),
// DCB=(RECFM=FB,LRECL=200,BLKSIZE=200),
// SPACE=(TRK,(10,5),RLSE),UNIT=SYSDA
//TOOLIN DD *
SORT FROM(SYS010) TO(SYS020) USING(CTL1)
/*
//CTL1CNTL DD *
OPTION EQUALS
SORT FIELDS=(1,8,CH,A,10,34,CH,A)
/*
