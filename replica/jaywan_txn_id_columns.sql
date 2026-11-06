-- Columns the Jaywan clearing generator needs on the work/data tables
-- (2026-11-06). Both are written by the split and exported in the XML.
--
-- JWN_TXN_ID           <nTxnId>          N15, space padded. Source is the acquirer
--                                        transaction id (MPGS 6221 -> PTR_TXN_ID).
-- JWN_TXN_ORG_INST_ID  <nTxnOrgInstCd>   N9,  space padded. Configured per acquirer
--                                        via TXN_ORG_INST_CD in the mpgs-service env
--                                        (004601 for this acquirer).
--
-- Apply once, as NETWORK_SETTLEMENT_UAT:
--   sqlplus NETWORK_SETTLEMENT_UAT/***@//localhost:1521/FREEPDB1 @jaywan_txn_id_columns.sql

ALTER TABLE JAYWAN_ACQ_TXN_WORK ADD (JWN_TXN_ID VARCHAR2(15));
ALTER TABLE JAYWAN_ACQ_TXN_WORK ADD (JWN_TXN_ORG_INST_ID VARCHAR2(9));

ALTER TABLE JAYWAN_ACQ_TXN_DATA ADD (JWN_TXN_ID VARCHAR2(15));
ALTER TABLE JAYWAN_ACQ_TXN_DATA ADD (JWN_TXN_ORG_INST_ID VARCHAR2(9));

COMMIT;
