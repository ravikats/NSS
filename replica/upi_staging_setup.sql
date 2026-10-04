-- UPI/UnionPay outgoing staging setup (corrected column names)
-- Run as NETWORK_SETTLEMENT_UAT on local oracle-local

-- 1. INTERFACES: category UNIONPAY -> INT_CODE=15
MERGE INTO INTERFACES t
USING (SELECT 15 AS int_code, 'UNIONPAY' AS int_category, 1 AS int_ins_code FROM dual) s
ON (t.INT_CODE = s.int_code)
WHEN NOT MATCHED THEN INSERT (INT_CODE, INT_CATEGORY, INT_INS_CODE, INT_LAST_UPDATED, INT_UPDATED_USER, INT_TYPE)
VALUES (15, 'UNIONPAY', 1, SYSDATE, 4, 'N');

-- 2. FILE_FORMATS: system 115, type 'O' (outgoing), FOR_CODE is GENERATED ALWAYS identity
MERGE INTO FILE_FORMATS t
USING (SELECT 115 AS sys_code, 'O' AS fmt_type, 1 AS for_ins_code, 'O' AS for_int_type FROM dual) s
ON (t.FOR_SYSTEM_CODE = s.sys_code AND t.FOR_TYPE = s.fmt_type AND t.FOR_INS_CODE = s.for_ins_code)
WHEN NOT MATCHED THEN INSERT (FOR_SYSTEM_CODE, FOR_TYPE, FOR_INS_CODE, FOR_INT_TYPE, FOR_LAST_UPDATED, FOR_UPDATED_USER, FOR_DESCRIPTION)
VALUES (115, 'O', 1, 'O', SYSDATE, 4, 'UnionPay Outgoing');

-- 3. ACQUIRER_BINS: bin_type 'U' for UnionPay
MERGE INTO ACQUIRER_BINS t
USING (
  SELECT '970962' AS acq_bin, 1 AS acq_ins_code, 'U' AS acq_bin_type,
         '034540' AS acq_mc_ica_no, 0 AS acq_out_file_seq, SYSDATE AS acq_out_file_date
  FROM dual
) s
ON (t.ACQ_BIN = s.acq_bin AND t.ACQ_INS_CODE = s.acq_ins_code AND t.ACQ_BIN_TYPE = s.acq_bin_type)
WHEN NOT MATCHED THEN INSERT (
  ACQ_BIN, ACQ_INS_CODE, ACQ_BIN_TYPE, ACQ_MC_ICA_NO,
  ACQ_OUT_FILE_SEQ, ACQ_OUT_FILE_DATE, ACQ_DOM_INTL_FLAG,
  ACQ_LAST_UPDATED, ACQ_UPDATED_USER
) VALUES (
  '970962', 1, 'U', '034540',
  0, SYSDATE, 'D',
  SYSDATE, 4
);

COMMIT;