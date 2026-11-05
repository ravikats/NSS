-- Local-replica reference rows the Go mpgs-service needs, and why.
--
-- 1. FILE_FORMATS with FOR_SYSTEM_CODE = 116
--    mpgsdcf resolves the upload-log format by system code (Cfg.SystemFileFormat,
--    env MPGS_SYSTEM_FILE_FORMAT_CODE=116) and reads the row's FOR_CODE. Without
--    a 116 row every MPGS load fails at FILE_UPLOAD_LOG insert time with
--    "mpgsdcf: file format 116: sql: no rows in result set".
--    UAT carries the real row; this is the local stand-in (its FOR_CODE is
--    arbitrary as long as it is unique).
--
-- 2. INTERFACES 13 = MPGS
--    FILE_UPLOAD_LOG.UPL_INT_CODE is an FK (FK_UPL_INT_CODE). The local replica
--    seeded 11/12/14..22 but not 13, so the insert failed with ORA-02291.
--    The interface code itself comes from INTERFACE_CODE_MPGS (default 13) and
--    must match whatever INTERFACES row is seeded.
--
-- Apply once, as NETWORK_SETTLEMENT_UAT:
--   sqlplus NETWORK_SETTLEMENT_UAT/***@//localhost:1521/FREEPDB1 @mpgs_file_format.sql

INSERT INTO FILE_FORMATS
  (FOR_CODE, FOR_LAST_UPDATED, FOR_UPDATED_USER, FOR_INS_CODE, FOR_TYPE,
   FOR_INT_TYPE, FOR_DESCRIPTION, FOR_SYSTEM_CODE)
VALUES (4, SYSDATE, 1, 1, 'S', 'N', 'MPGS FILE', 116);

INSERT INTO INTERFACES
  (INT_CODE, INT_LAST_UPDATED, INT_UPDATED_USER, INT_INS_CODE, INT_TYPE,
   INT_NAME, INT_SHORT_NAME, INT_CATEGORY)
VALUES (13, SYSDATE, 1, 1, 'S', 'MPGS', 'MPGS', 'MPGS');

COMMIT;
