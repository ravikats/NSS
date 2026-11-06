-- DCF fields the Jaywan clearing mapping needs and we had nowhere to keep
-- (mapping V1.5 / v1.0 rows 31/34/36/37). All are read from the MPGS DCF record.
--
-- POS_TRANSACTIONS
--   PTR_POS_CP_IND      6220 F31 pos 176  POS Cardholder Presence Indicator -> <nPosCPInd>
--   PTR_POS_TXN_STATUS  6220 F32 pos 177  POS Transaction Status Indicator  -> <nPosTxnStat>
--   PTR_CIT_MIT_IND     6222 F19 pos 114-117 CIT/MIT indicator (C101/M103) -> recurring rule
--
-- JAYWAN_ACQ_TXN_WORK / _DATA carry the same three so the generator can derive
-- the clearing values after the split.
--
-- Apply once, as NETWORK_SETTLEMENT_UAT:
--   sqlplus NETWORK_SETTLEMENT_UAT/***@//localhost:1521/FREEPDB1 @jaywan_dcf_fields.sql

ALTER TABLE POS_TRANSACTIONS ADD (PTR_POS_CP_IND CHAR(1));
ALTER TABLE POS_TRANSACTIONS ADD (PTR_POS_TXN_STATUS CHAR(1));
ALTER TABLE POS_TRANSACTIONS ADD (PTR_CIT_MIT_IND VARCHAR2(4));

ALTER TABLE JAYWAN_ACQ_TXN_WORK ADD (JWN_POS_CP_IND CHAR(1));
ALTER TABLE JAYWAN_ACQ_TXN_WORK ADD (JWN_POS_TXN_STATUS CHAR(1));
ALTER TABLE JAYWAN_ACQ_TXN_WORK ADD (JWN_CIT_MIT_IND VARCHAR2(4));

ALTER TABLE JAYWAN_ACQ_TXN_DATA ADD (JWN_POS_CP_IND CHAR(1));
ALTER TABLE JAYWAN_ACQ_TXN_DATA ADD (JWN_POS_TXN_STATUS CHAR(1));
ALTER TABLE JAYWAN_ACQ_TXN_DATA ADD (JWN_CIT_MIT_IND VARCHAR2(4));

COMMIT;
