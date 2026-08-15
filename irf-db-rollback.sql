-- =============================================================================
-- IRF-SERVICE DB ROLLBACK  (undo of irf-db-upgrade.sql)
-- Run manually on the real Oracle UAT schema ONLY if rolling back the upgrade.
-- WARNING: DROPS DATA. Confirm before running.
--
-- Idempotent: skips anything already dropped. Order matters:
--   1) drop the 13 added IRF_CALLBACK columns
--   2) drop the 2 tables created by the upgrade
-- =============================================================================
SET SERVEROUTPUT ON

-- -----------------------------------------------------------------------------
-- 1. Drop the 13 columns added to IRF_CALLBACK (data loss)
-- -----------------------------------------------------------------------------
DECLARE
    v_cnt NUMBER;
BEGIN
    FOR c IN (
        SELECT 'ICB_CARD_CLASSIFICATION' col FROM dual UNION ALL
        SELECT 'ICB_CP_MID'               FROM dual UNION ALL
        SELECT 'ICB_DOM_INTL_FLAG'        FROM dual UNION ALL
        SELECT 'ICB_FIXED'                FROM dual UNION ALL
        SELECT 'ICB_IRF_AMOUNT'           FROM dual UNION ALL
        SELECT 'ICB_PERCENTAGE'           FROM dual UNION ALL
        SELECT 'ICB_TXN_AMOUNT'           FROM dual UNION ALL
        SELECT 'ICB_IS_CREDIT'            FROM dual UNION ALL
        SELECT 'ICB_JOB_NUMBER'           FROM dual UNION ALL
        SELECT 'ICB_IRD_CODE'             FROM dual UNION ALL
        SELECT 'ICB_MTI'                  FROM dual UNION ALL
        SELECT 'ICB_RRN'                  FROM dual UNION ALL
        SELECT 'ICB_UNIQUE_ID'            FROM dual
    ) LOOP
        SELECT COUNT(*) INTO v_cnt FROM user_tab_columns
         WHERE table_name = 'IRF_CALLBACK' AND column_name = c.col;
        IF v_cnt > 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE IRF_CALLBACK DROP COLUMN ' || c.col;
            DBMS_OUTPUT.PUT_LINE('DROPPED: IRF_CALLBACK.' || c.col);
        ELSE
            DBMS_OUTPUT.PUT_LINE('SKIP (not present): IRF_CALLBACK.' || c.col);
        END IF;
    END LOOP;
END;
/

-- -----------------------------------------------------------------------------
-- 2. Drop the tables created by the upgrade (data loss)
-- -----------------------------------------------------------------------------
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE OMAN_NET_IRF';
    DBMS_OUTPUT.PUT_LINE('DROPPED: OMAN_NET_IRF');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -942 THEN
            DBMS_OUTPUT.PUT_LINE('SKIP (not present): OMAN_NET_IRF');
        ELSE
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE OMANNET_BIN_DATA';
    DBMS_OUTPUT.PUT_LINE('DROPPED: OMANNET_BIN_DATA');
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -942 THEN
            DBMS_OUTPUT.PUT_LINE('SKIP (not present): OMANNET_BIN_DATA');
        ELSE
            RAISE;
        END IF;
END;
/

-- -----------------------------------------------------------------------------
-- 3. Sanity check: nothing below should return rows
-- -----------------------------------------------------------------------------
SELECT column_name FROM user_tab_columns
 WHERE table_name = 'IRF_CALLBACK'
   AND column_name IN ('ICB_CARD_CLASSIFICATION','ICB_CP_MID','ICB_DOM_INTL_FLAG',
                       'ICB_FIXED','ICB_IRF_AMOUNT','ICB_PERCENTAGE','ICB_TXN_AMOUNT',
                       'ICB_IS_CREDIT','ICB_JOB_NUMBER','ICB_IRD_CODE','ICB_MTI',
                       'ICB_RRN','ICB_UNIQUE_ID');
SELECT table_name FROM user_tables WHERE table_name IN ('OMAN_NET_IRF', 'OMANNET_BIN_DATA');
