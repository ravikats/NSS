-- Local Visa Base II outgoing staging setup (2026-10-03)
-- VISA_ACQ_TXN_WORK/DATA and the VISA interface (INT_CODE=14) already exist on
-- the replica. The Visa port (go/outsvc, ProcessVisaOutgoing) additionally needs
-- an ACQUIRER_BINS row with bin_type 'V' (file sequence + file name) and a
-- FILE_FORMATS row for VISA_SYSTEM_CODE=117, type 'O' (format code).
--
-- The ACQ_BIN below (409083) is the Visa acquirer/Centre Information Block seen
-- in the switch traffic (core DE32/DE33=409083) and required by the BASE II
-- trailer validation, but it is FABRICATED for the local replica; confirm the
-- real value before any production use. Idempotent; run as NETWORK_SETTLEMENT_UAT.

-- 1. FILE_FORMATS row for Visa outgoing (system 117, type 'O').
-- FOR_CODE is a GENERATED ALWAYS identity column, so it is omitted.
INSERT INTO FILE_FORMATS (FOR_LAST_UPDATED, FOR_INS_CODE, FOR_TYPE, FOR_DESCRIPTION,
   FOR_SYSTEM_CODE, FOR_INT_TYPE, FOR_FILE_TYPE, FOR_UPDATED_USER)
   SELECT SYSDATE, 1, 'O', 'VISA BASE II Outgoing', 117, 'V', 'T', 4 FROM DUAL
   WHERE NOT EXISTS (SELECT 1 FROM FILE_FORMATS WHERE FOR_SYSTEM_CODE = 117 AND FOR_TYPE = 'O');

-- 2. ACQUIRER_BINS row for Visa bin type 'V'. FABRICATED.
INSERT INTO ACQUIRER_BINS (ACQ_BIN, ACQ_LAST_UPDATED, ACQ_BIN_TYPE, ACQ_DOM_INTL_FLAG,
   ACQ_MC_ICA_NO, ACQ_ARN_SEQ_NO, ACQ_INS_CODE, ACQ_UPDATED_USER)
   SELECT '409083', SYSDATE, 'V', 'D', NULL, 0, 1, 4 FROM DUAL
   WHERE NOT EXISTS (SELECT 1 FROM ACQUIRER_BINS WHERE ACQ_BIN = '409083');

COMMIT;
/
