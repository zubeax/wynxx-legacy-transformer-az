-- ddl_ecb_paystats_db2.sql
-- DB2 z/OS DDL for a simplified schema that captures the core variables
-- required to aggregate ECB payments statistics per Regulation (EU) 2020/2011
-- (ECB/2020/59) amending Regulation (EU) No 1409/2013 (ECB/2013/43).
-- This schema models payment services, initiation channels, card MCC, SCA flags,
-- scheme, fraud flags, and geographic dimensions frequently referenced
-- in Annex I/II tables (e.g., Table 4a and 9). It is intended as an illustrative
-- schema for internal aggregation. For official reporting you must map to the
-- National Central Bank XML/XSD.

CREATE SCHEMA PAYSTATS ;

CREATE TABLE PAYSTATS.PAYMENT_TRANSACTIONS (
    TRANS_ID            BIGINT       NOT NULL,
    TRANS_TS            TIMESTAMP    NOT NULL,
    SERVICE_CODE        VARCHAR(30)  NOT NULL, -- e.g., CREDIT_TRANSFER, DIRECT_DEBIT, CARD, E_MONEY, CASH_WITHDRAWAL, CHEQUE, MONEY_REMITTANCE, OTHER
    INIT_CHANNEL        VARCHAR(30)  NOT NULL, -- e.g., PAPER, ELECTRONIC, REMOTE_ELECTRONIC, MOBILE, PIS, OTHER
    SCHEME_CODE         VARCHAR(40)           , -- e.g., SEPA_CT, SEPA_SCT_INST, SEPA_DD_CORE, SEPA_DD_B2B, VISA, MASTERCARD, OTHER
    SCA_APPLIED         CHAR(1)      NOT NULL WITH DEFAULT 'Y', -- 'Y' strong customer authentication applied; 'N' otherwise
    NON_SCA_REASON      VARCHAR(40)           , -- reason for SCA exemption or N/A
    FRAUD_FLAG          CHAR(1)      NOT NULL WITH DEFAULT 'N', -- 'Y' if fraudulent
    FRAUD_ORIGIN        VARCHAR(20)           , -- e.g., PAYER, PAYEE, OTHER/UNKNOWN
    AMOUNT_EUR          DECIMAL(15,2) NOT NULL,
    CURRENCY            CHAR(3)      NOT NULL WITH DEFAULT 'EUR',
    PAYER_COUNTRY       CHAR(2)      NOT NULL,
    PAYEE_COUNTRY       CHAR(2)      NOT NULL,
    POS_COUNTRY         CHAR(2)              , -- for card transactions, POS country (can differ from payee residency)
    MCC                 CHAR(4)              , -- merchant category code (ISO 18245) for card transactions
    PRIMARY KEY (TRANS_ID)
);

CREATE TABLE PAYSTATS.LOOKUP_SERVICE (
    SERVICE_CODE VARCHAR(30) PRIMARY KEY,
    DESCRIPTION  VARCHAR(200)
);

CREATE TABLE PAYSTATS.LOOKUP_CHANNEL (
    INIT_CHANNEL VARCHAR(30) PRIMARY KEY,
    DESCRIPTION  VARCHAR(200)
);

CREATE TABLE PAYSTATS.LOOKUP_SCA_REASON (
    NON_SCA_REASON VARCHAR(40) PRIMARY KEY,
    DESCRIPTION    VARCHAR(200)
);

CREATE TABLE PAYSTATS.LOOKUP_SCHEME (
    SCHEME_CODE VARCHAR(40) PRIMARY KEY,
    DESCRIPTION VARCHAR(200)
);

CREATE TABLE PAYSTATS.LOOKUP_COUNTRY (
    ISO2 CHAR(2) PRIMARY KEY,
    NAME VARCHAR(100)
);

CREATE TABLE PAYSTATS.LOOKUP_MCC (
    MCC CHAR(4) PRIMARY KEY,
    NAME VARCHAR(200)
);
