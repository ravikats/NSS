-- MERCURY_ISS_ACC_RANGE — created locally from the binsvc/Java entity mapping.
--
-- UAT does NOT have this table (only MC/VISA/JAYWAN range tables exist there),
-- and the Java bin-processing-service has no MERCURY_BIN_FORMAT_CODE configured,
-- so Mercury BIN ingest has never run on the real system. The columns below are
-- the ones the loader writes (com.empay.entities.MercuryIssAccRangeEntity /
-- irf-go binsvc.MercuryRange); types follow the sibling range tables.
--
-- Do not treat this as the UAT schema: if Mercury BIN ingest is ever enabled for
-- real, the table must be defined by the schema owners.

CREATE TABLE MERCURY_ISS_ACC_RANGE (
  MIA_SER_NUMBER       NUMBER(10) GENERATED ALWAYS AS IDENTITY,
  MIA_LAST_UPDATED     TIMESTAMP(6) NOT NULL,
  MIA_UPDATED_USER     NUMBER(10)   NOT NULL,
  MIA_PRJ_SER_NUMBER   NUMBER(10),
  MIA_BIN_LOW_VALUE    NUMBER(19),
  MIA_BIN_HIGH_VALUE   NUMBER(19),
  MIA_CARD_TYPE        NUMBER(10),
  MIA_CARD_PRODUCT_ID  NUMBER(10),
  MIA_CARD_VARIANT     NUMBER(10),
  MIA_CARD_SCHEME      NUMBER(10),
  MIA_CUR_CODE         NUMBER(10),
  MIA_CON_CODE         NUMBER(10),
  MIA_STATUS           CHAR(1),
  CONSTRAINT PK_MIA_SER_NUMBER PRIMARY KEY (MIA_SER_NUMBER)
);
