-- Local Amex outgoing staging setup (2026-10-02)
-- AMEX_ACQ_TXN_WORK/DATA and the AMEX interface (INT_CODE=16) already exist on
-- the replica. The Amex port (go/outsvc, ProcessAmexOutgoing) additionally needs
-- an ACQUIRER_BINS row with bin_type 'A' (for the file sequence) and a
-- FILE_FORMATS row for AMEX_SYSTEM_CODE=121, type 'O' (for the format code).
--
-- The ACQ_BIN below is FABRICATED for the local replica (UAT/replica has no
-- Amex bin); confirm the real value before any production use. Idempotent; run
-- as NETWORK_SETTLEMENT_UAT.

-- 1. FILE_FORMATS row for Amex outgoing (system 121, type 'O').
-- FOR_CODE is a GENERATED ALWAYS identity column, so it is omitted.
INSERT INTO FILE_FORMATS (FOR_LAST_UPDATED, FOR_INS_CODE, FOR_TYPE, FOR_DESCRIPTION,
   FOR_SYSTEM_CODE, FOR_INT_TYPE, FOR_FILE_TYPE, FOR_UPDATED_USER)
   SELECT SYSDATE, 1, 'O', 'AMEX FSF Outgoing', 121, 'N', 'T', 4 FROM DUAL
   WHERE NOT EXISTS (SELECT 1 FROM FILE_FORMATS WHERE FOR_SYSTEM_CODE = 121 AND FOR_TYPE = 'O');

-- 2. ACQUIRER_BINS row for Amex bin type 'A'. FABRICATED.
INSERT INTO ACQUIRER_BINS (ACQ_BIN, ACQ_LAST_UPDATED, ACQ_BIN_TYPE, ACQ_DOM_INTL_FLAG,
   ACQ_MC_ICA_NO, ACQ_ARN_SEQ_NO, ACQ_INS_CODE, ACQ_UPDATED_USER)
   SELECT '970964', SYSDATE, 'A', 'D', '970962', 0, 1, 4 FROM DUAL
   WHERE NOT EXISTS (SELECT 1 FROM ACQUIRER_BINS WHERE ACQ_BIN = '970964');

COMMIT;
/
