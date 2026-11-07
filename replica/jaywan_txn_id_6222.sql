-- <nTxnId> must come from MPGS DCF record 6222 positions 5-13 (network code +
-- Banknet reference), not from the 6221 identifier that PTR_TXN_ID carries for
-- the other schemes. The two are kept apart so a 6221 value can never leak into
-- the clearing file (mapping v1.0 row 34, AEP R-28).
--
-- A block with no 6222 record has no nTxnId at all; the generator then holds the
-- row in the exception report (gen_status 7) instead of writing it.
--
-- Apply once, as NETWORK_SETTLEMENT_UAT:
--   sqlplus NETWORK_SETTLEMENT_UAT/***@//localhost:1521/FREEPDB1 @jaywan_txn_id_6222.sql

ALTER TABLE POS_TRANSACTIONS ADD (PTR_TXN_ID_6222 VARCHAR2(15));

COMMIT;
