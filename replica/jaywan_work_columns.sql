-- Local-replica schema fixes the Jaywan split/outgoing needed (2026-11-06).
--
-- JWN_ECOM_INDICATOR is written as a two-character code ("05"/"06"/"07"/"08")
-- by the Java mapper (JaywanSplitTxnService.getMOTOEcomInd) and by the Go port,
-- but the local replica created both Jaywan tables with VARCHAR2(1). The split
-- then failed with ORA-12899 (actual 2, maximum 1) and, after the column was
-- widened, the outgoing archive failed the same way on JAYWAN_ACQ_TXN_DATA.
--
-- JWN_CH_PRESENT exists in the Java entity (@Column JWN_CH_PRESENT) and is
-- written by the split, but was missing from both local tables, so the insert
-- could not bind it.
--
-- Apply once, as NETWORK_SETTLEMENT_UAT:
--   sqlplus NETWORK_SETTLEMENT_UAT/***@//localhost:1521/FREEPDB1 @jaywan_work_columns.sql

ALTER TABLE JAYWAN_ACQ_TXN_WORK MODIFY (JWN_ECOM_INDICATOR VARCHAR2(2));
ALTER TABLE JAYWAN_ACQ_TXN_WORK ADD (JWN_CH_PRESENT CHAR(1));

ALTER TABLE JAYWAN_ACQ_TXN_DATA MODIFY (JWN_ECOM_INDICATOR VARCHAR2(2));
ALTER TABLE JAYWAN_ACQ_TXN_DATA ADD (JWN_CH_PRESENT CHAR(1));

COMMIT;
