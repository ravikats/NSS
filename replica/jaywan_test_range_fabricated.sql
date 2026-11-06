-- FABRICATED TEST DATA -- LOCAL ONLY, NEVER APPLY TO UAT.
--
-- The GEN4 sample files under data/ are masked ("66901099*****010"), and their
-- BIN is not in the Jaywan BIN file we hold (UAESWITCH-POS-BIN-20260805.csv has
-- 93 ranges under 669009 and dozens under 669010, none covering 66901099). To
-- exercise the Jaywan split and clearing-file generator locally, a synthetic
-- range was added for that BIN and the sample was de-masked with a Luhn-valid
-- PAN: data/GEN4_JAYWAN_NET_20251218_DEMASKED.001 (6690109926024010).
--
-- This row makes every 66901099xx card classify as Jaywan on this replica. It
-- is not reference data and must not exist on a shared or UAT schema.
--
-- JBS_PRJ_SER_NUMBER must be a real job number: the Java entities declare the
-- field as a primitive int, so a NULL makes finalize's jaywanProductType fail
-- with "Null value was assigned to a property ... of primitive type" and every
-- Jaywan transaction stalls at gen_status 9.
--
-- Apply once, as NETWORK_SETTLEMENT_UAT:
--   sqlplus NETWORK_SETTLEMENT_UAT/***@//localhost:1521/FREEPDB1 @jaywan_test_range_fabricated.sql

INSERT INTO JAYWAN_ISS_ACC_RANGE
  (JBS_LAST_UPDATED, JBS_UPDATED_USER, JBS_PRJ_SER_NUMBER, JBS_ISSUER_BANK,
   JBS_INS_ID, JBS_BIN_LOW_VALUE, JBS_BIN_HIGH_VALUE, JBS_PAN_LENGTH,
   JBS_PRODUCT_TYPE, JBS_SCHEME_CODE, JBS_SCHEME_PRODUCT, JBS_CARD_TYPE,
   JBS_SERVICE, JBS_CUR_CODE, JBS_ISO_NUM_CUR_CODE, JBS_ACTION_TAKEN,
   JBS_BIN_LENGTH)
VALUES
  (SYSTIMESTAMP, 1, 10, 'FABRICATED TEST RANGE (GEN4 sample)',
   999999999, '6690109900', '6690109999', 16,
   'D', 'J', 'Jaywan Test', 3,
   6, 784, 784, 'A',
   8);

COMMIT;
