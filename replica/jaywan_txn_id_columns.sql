-- Columns the Jaywan clearing generator needs on the work/data tables
-- (2026-11-06). Both are written by the split and exported in the XML.
--
-- JWN_TXN_ID           <nTxnId>          N15, space padded. Source is MPGS 6222
--                                        positions 5-13 (network code + Banknet
--                                        reference), carried in PTR_TXN_ID_6222 --
--                                        NOT the 6221 identifier in PTR_TXN_ID.
--                                        A DCF block without a 6222 record has no
--                                        value and is held in the exception report
--                                        (AEP R-28), never written without the tag.
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
