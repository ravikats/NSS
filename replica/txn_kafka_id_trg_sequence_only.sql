-- Reduce ADMIN.TXN_KAFKA_ID_TRG to its only remaining job: assign the CDC
-- checkpoint sequence value.
--
-- The trigger is a BEFORE INSERT row-level trigger on ADMIN.TRANSACTIONS that
-- fires only when the incoming row carries no KAFKA_ID:
--
--     before insert on ADMIN.TRANSACTIONS
--     for each row
--     when (new.KAFKA_ID is null)
--
-- It MUST assign the sequence: switch-core does not bind KAFKA_ID when it
-- journals a transaction (see oracle/schema.go -- the column is declared there
-- but never inserted), so without this the row would never get a checkpoint id
-- and cdc's reader (SELECT ... WHERE KAFKA_ID > :1 ORDER BY KAFKA_ID) would
-- not stream it.
--
-- Everything else it used to do is REMOVED:
--   * SWITCH_CRYPT_TOKEN := 'ed8de959...'  -- the hardcoded vault token whose PAN
--     was the same dummy card number on every outgoing file (core now mints a
--     real per-transaction token via CryptAPI pan-encryption).
--   * PAN := '4104999999999999'            -- dummy PAN.
--   * ~24 placeholder defaults (784, MASTERCARD, TEST MERCHANT/DUBAI/DU/LOC001,
--     POS, 012, 00, ONUS, sysdate stamps, ...).
--
-- Why drop the defaults: they silently repaired incomplete rows, so a record
-- missing a real value still reached tlf-parser looking valid. With them gone a
-- partial insert stays NULL and fails validation (cdc requires 11 fields
-- including PAN; the parser requires switch_crypt_token), which is the
-- intended fail-closed behaviour.
--
-- Apply as ADMIN (owner of both the table and the sequence):
--   sqlplus ADMIN/<pwd>@//localhost:1521/FREEPDB1 @txn_kafka_id_trg_sequence_only.sql
--
-- Idempotent: CREATE OR REPLACE.
--
-- Note: this trigger exists ONLY in the local replica. UAT has no triggers on
-- ADMIN.TRANSACTIONS (verified 2026-10-04), so there is nothing to apply there.

CREATE OR REPLACE TRIGGER ADMIN.TXN_KAFKA_ID_TRG
before insert on ADMIN.TRANSACTIONS
for each row
when (new.KAFKA_ID is null)
begin
  :new.KAFKA_ID := ADMIN.TXN_KAFKA_ID_SEQ.nextval;
end;
/