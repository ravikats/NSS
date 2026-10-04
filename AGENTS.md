# AGENTS.md

## Working style
Do not report changes elaborately. Focus on actual work (tool calls, code,
fixes) and keep narrative output to a minimum unless the user asks for detail.

## Go module layout
The Go code lives under `go/` (module `empay/irf`, go.mod at `go/go.mod`).
The system Go (`/usr/bin/go`) is go1.18 and cannot build this module. Use the
downloaded toolchain from the module cache instead:

```
export GOTOOLCHAIN=go1.25.13
export PATH=/home/ravi/go/pkg/mod/golang.org/toolchain@v0.0.1-go1.25.13.linux-amd64/bin:$PATH
```

## Build / verify commands (run from `go/`, with the toolchain env above)
- `go build ./...`
- `go vet ./...`
- `go test ./...`              # all packages, including tlfsvc (fake-store IRF flow tests)
  # mpgsdcf/TestLoadJaywanRanges needs the fixture /tmp/opencode/jaywan_ranges.csv:
  # fetch from UAT server `scp ec2-user@10.100.139.30:/home/ec2-user/jaywan_ranges.csv
  # /tmp/opencode/` (the /vp-switch/Network_Settlement copy has a 7th range — leave it).
- `CGO_ENABLED=0 go build -trimpath -ldflags="-s -w" -o tlf-service ./cmd/tlf-service`
  (static binary for UAT deploy)
- `CGO_ENABLED=0 go build -trimpath -ldflags="-s -w" -o /tmp/opencode/outgoing-service ./cmd/outgoing-service`

## Local Oracle replica (for DB-backed tests / regenerating outgoing)
- Docker container `oracle-local` (`gvenzl/oracle-free`, 23ai). Start with
  `docker start oracle-local`; port 1521 → FREEPDB1.
- Local DSN: `oracle://NETWORK_SETTLEMENT_UAT:J6erQ%24o6E24@localhost:1521/FREEPDB1`
  (URL-encode `$` as `%24`). sqlplus works too:
  `sqlplus NETWORK_SETTLEMENT_UAT/'J6erQ$o6E24'@localhost:1521/FREEPDB1`.
- UAT DSN (not reachable from this machine; VPN/network): 
  `oracle://NETWORK_SETTLEMENT_UAT:J6erQ$o6E24@switch-uat.c3guuusy8mm5.me-central-1.rds.amazonaws.com:1521/ORCL`
- Transaction tables (`*_ACQ_TXN_WORK/DATA`, `POS_TRANSACTIONS`, `OUT_FILE_LOG`,
  `OUTGOING_SUMMARY`) are empty on the replica — test data must be injected.
  Mercury reference rows: `INTERFACES` INT_CODE=21 (category MERCURY),
  `FILE_FORMATS` FOR_CODE=123 (system 134, type 'O'), `ACQUIRER_BINS`
  ACQ_BIN='970962' type 'E' ICA '034540'. See `replica/README.md` +
  `replica/mercury_staging_setup.sql`.
- The CryptAPI server (`10.100.139.30:2728`) is reachable from this machine
  (needed for PAN decryption during EIF generation). When starting the service,
  pass the `decUrl`/`bankId`/`accessToken`/`crypt*` envs from
  `outgoing/application.properties`. If it appears unreachable, ask the user to
  connect the VPN first — do not assume it is down.

## Regenerating the Mercury EIF outgoing file on the replica
1. `docker start oracle-local` and wait for the DB to be ready.
2. Move `MERCURY_ACQ_TXN_DATA` rows back to `MERCURY_ACQ_TXN_WORK` with
   `MAT_GEN_STATUS=3` (SQL insert-from-data + delete), or use the service's
   revert endpoint (only MASTERCARD/VISA/JAYWAN revert is ported; Mercury has none).
 3. Run the service (from `go/`; **leave it running** — do not kill it after a
    generation, later runs reuse it):
    ```
    ORACLE_DSN='oracle://NETWORK_SETTLEMENT_UAT:J6erQ%24o6E24@localhost:1521/FREEPDB1' \
    HTTP_PORT=19031 INS_CODE=1 INS_SHORT_NAME=IRF UPDATED_USER=4 \
    MASTERCARD_SYSTEM_CODE=115 VISA_SYSTEM_CODE=117 JAYWAN_SYSTEM_CODE=120 \
    AMEX_SYSTEM_CODE=121 MERCURY_SYSTEM_CODE=134 \
    CURRENCY_CODE_KAFKA=AED000 RECON_OUT_IRF=/vp-switch/OUTPUT/ PROCESSING_MODE=T \
    /tmp/opencode/outgoing-service
    ```
4. Trigger generation:
   `curl -s -X POST http://localhost:19031/outgoing/v1/generateOutgoing -H 'Content-Type: application/json' \
   -d '{"network":"MERCURY","fromDate":"01/08/2026 00:00:00","toDate":"31/08/2026 23:59:59"}'`
5. Output: `EIF_ddMMyyyy.{seq:03d}` in `RECON_OUT_IRF` (sequence comes from
   `ACQ_OUT_FILE_SEQ`/`ACQ_OUT_FILE_DATE` on the bin row; resets to 1 if the date
   differs from today).
6. Rows end in `MERCURY_ACQ_TXN_DATA` with `MAT_GEN_STATUS=4`; POS rows get
   `PTR_GEN_STATUS=6`/`Completed`; `OUT_FILE_LOG` rows get status 4.

Known Mercury EIF format notes (2026-08-16): amounts in XD/UT/UY are plain
decimals with the currency's fraction digits (`10.00`, not `1000`); XM
CAMTA/CAMTO are 12-digit minor units (value×100); zero surcharges are omitted
from XD field 45; AURCDE (XD field 54) stays empty.

## New TLF service packages
- `go/tlfsvc` — online TLF processor (payload.go, mapper.go, store.go, service.go,
  worker.go, kafka.go, mercury.go, mapper_test.go, service_test.go)
- `go/cmd/tlf-service` — Kafka consumer entrypoint (Stage1 → WorkerPool.Stage2,
  §7.12 of IRF_SERVICE_HANDOVER.md) + `GET /healthz`; `POST /tlf/v1/PostmanTxn` removed 2026-08-15

## Environment for the running service
- `ORACLE_DSN`, `IRF_SERVICE_URL`, `IRF_SERVICE_SEC`, `HTTP_PORT` (default 19030),
  `INS_CODE` (1), `INTERFACE_CODE_TLF` (11), `UPDATED_USER` (4), `TIMESTAMP_JOB_NUMBER` (1),
  `EXCHANGE_RATE` (0.27).

## Objective: Port `splitProcessAndStaging` (Spring Boot / Java) to Go

The Java application (`splitProcessAndStaging`, port 9031 on server `10.100.128.232`)
generates outgoing settlement files for 5 payment networks. This document describes
the full architecture so a Go agent can rebuild it.

### 1. REST API Endpoints

All endpoints are under `/outgoing/` (controller: `OutGoingController`) and
`/OutgoingScheduler/` (controller: `SchedulerController`).

#### OutGoingController (`/outgoing/`)

| Method | Path                    | Request Body VO          | Handler Delegate                  |
|--------|-------------------------|--------------------------|-----------------------------------|
| POST   | `/v1/generateOutgoing`  | `OutGoingRequestVo`      | `OutGoingProcessingService.processAndMoveData` |
| POST   | `/v1/revertLastOutgoing`| `OutGoingRequestVo`      | `OutGoingProcessingService.revertLastOutgoingData` |
| PUT    | `/v1/updateRejectedData`| `RejectedTxnUpdateRequestVo` | `OutgoingUpdateService` |
| POST   | `/v1/generateCollectionOnly` | `CollectionOnlyRequestVo` | `CollectionOnlyProcessingService.processAndMoveData` |
| POST   | `/v1/revertLastCollectionOnly` | `OutGoingRequestVo` | `CollectionOnlyProcessingService.revertLastCollectionOnlyData` |

#### SchedulerController (`/OutgoingScheduler/`)

| Method | Path              | Request Body VO    | Handler Delegate                |
|--------|-------------------|--------------------|----------------------------------|
| POST   | `/v1/addCycle`    | `OutgoingSchedulerVo` | `DynamicSchedulerService.configureSchedulerCycle` (editMode=false) |
| DELETE | `/removeCycle/{taskId}` | (path param)    | `DynamicSchedulerService.removeCycle` |
| PUT    | `/v1/UpdateCycle` | `OutgoingSchedulerVo` | `DynamicSchedulerService.configureSchedulerCycle` (editMode=true) |
| GET    | `/v1/getAllCycle` | —                  | `DynamicSchedulerService.getScheduledTasks` |

#### StatusCheckController (`/`)
- `GET /` — health check, returns static string

### 2. Request VO Validation Rules

**OutGoingRequestVo** (for `/v1/generateOutgoing` and `/v1/revertLastOutgoing`):
- `network`: `@Pattern(regexp="^(MASTERCARD|VISA|RUPAY|AMEX|JAYWAN)$")`
- `fromDate`: `@Pattern(regexp="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])")`
- `toDate`: same pattern as `fromDate`

**CollectionOnlyRequestVo** (for `/v1/generateCollectionOnly`):
- `network`: `@Pattern(regexp="^(UAESWITCH|OMANNET)$")`
- `scheme`: `@Pattern(regexp="^(MASTERCARD|VISA)$")`
- `fromDate`, `toDate`: same dd/MM/yyyy HH:mm:ss pattern

**OutgoingSchedulerVo** (for scheduler CRUD):
- `name`: @NotNull, @NotEmpty
- `network`: `@Pattern(regexp="^(?i)(MASTERCARD|VISA|RUPAY|AMEX|JAYWAN)$")`
- `isActive`: @NotNull Boolean
- `endTime`: `@Pattern(regexp="^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")` (HH:mm:ss)
- `editMode`: Boolean (set by controller — false for add, true for update)

**RejectedTxnUpdateRequestVo**: Used by `OutgoingUpdateService` (details not yet analyzed;
check `outgoing/src/com/empay/vo/RejectedTxnUpdateRequestVo.java` for fields).

### 3. Business Logic Flow

#### 3.1 Regular Outgoing Generation (`processAndMoveData`)

1. Parse `fromDate`/`toDate` from `OutGoingRequestVo` using format `dd/MM/yyyy HH:mm:ss`
2. Determine `network` from VO
3. Count transactions in work tables matching institution code, gen status=3, and date range
   - MASTERCARD → `MC_ACQ_TXN_WORK` (localDateTime filter)
   - VISA → `VISA_ACQ_TXN_WORK` (purchaseDate filter)
   - JAYWAN → `JAYWAN_ACQ_TXN_WORK` (localDateTime filter)
   - AMEX → `AMEX_ACQ_TXN_WORK` (localDateTime filter)
4. If txnCount == 0, return "There are no transactions to stage!"
5. If txnCount > 0, spawn a background goroutine that calls the network-specific service:
   - `MCOutgoingService.processMCOutgoing()`
   - `VisaOutgoingServiceImpl.generateVisaOutgoing()`
   - `JaywanOutgoingServiceImpl.generateJaywanOutgoing()`
   - `AmexOutgoingServiceImpl.generateAmexOutgoing()`
6. Return "Outgoing File Processing Scheduled Successfully."

#### 3.2 Network-Specific Processing

Each network service follows this pattern:

```
a. Look up FileFormatsEntity: system_code=formatCode, type='O', institution_code
   → forCode = entity.code (0 if not found)
b. Look up InterfacesEntity: interface_category (network-specific)
   → intCode = entity.interface_code (0 if not found)
c. Check OutGoingFileProcessingEntity where format_code=forCode AND generated_status IN (1,9)
   → if any exist, return "File Generation already Scheduled"
d. Create OutGoingFileProcessingEntity entry:
   - generated_status = 9 (processing)
   - business_date from BusinessDateEntity
   - Save and flush → get serialNumber (outgoingLogSerialNumber)
e. Get AcquirerBinsEntity where institution_code=insCode AND bin_type=network-specific
   - Update file sequence (increment if same day, else reset to 1)
   - Save updated acquirer bin
f. Generate filename using pattern (see §4)
g. Query work entities by institution_code, int_code, gen_status=3, date range
   - (VISA also filters txn_code NOT IN ('10','20') for regular txns, and IN ('10','20') for fee txns)
h. Update work entities gen_status → 9 (in preparation)
i. Update OutGoingFileProcessingEntity with filename
j. Collect encrypted card tokens from work entities
k. Call CryptAPI.getCardNumber(tokens) → decrypted map
l. If decryption fails (null response or null cardNumbers):
   - Set work entities gen_status = 7 (failed)
   - Return "Outgoing Failed"
m. Generate file content (see §4)
n. Write file to filesystem
o. Update OutGoingFileProcessingEntity with file_id, generated_status=4 (success)
p. Insert OutgoingSummaryEntity records (grouped by txn type/function code)
q. Update work entities gen_status → 4 (completed)
r. Move work → data table (copy all fields)
s. Delete from work table
t. Update POS transaction status (complete network-specific POS status)
u. Generate PDF summary via IOutGoingSummaryService.generateOutgoingSummaryPDF
```

#### 3.3 Collection-Only Processing (`processAndMoveData` in CollectionOnlyProcessingService)

Same flow as regular outgoing but:
- `scheme` is MASTERCARD or VISA (not network)
- `network` is UAESWITCH or OMANNET (passed as parameter to the service)
- MC collection → `GCOServiceImpl.generateMcCollectionOnly()` → IPM with fileType="GCO"
- VISA collection → `GOCServiceImpl.generateVisaCollectionOnly()` → Base II with GOC data

#### 3.4 Revert Last Outgoing (`revertLastOutgoingData`)

- For each network, find the last OutGoingFileProcessingEntity with generated_status=4
  and the network's interface code, ordered by last_updated desc
- Move entities from data table back to work table (set gen_status=3)
- Update POS transaction entities (set gen_status=4, out_status="Marked for Outgoing")
- Delete from data table
- Delete OutGoingFileProcessingEntity record

### 4. File Naming Patterns

| Network       | File Type | Filename Pattern                                    |
|---------------|-----------|-----------------------------------------------------|
| MASTERCARD    | IPM       | `{insShortName}R111{ddMMyyyy}.{seq:02d}`            |
| VISA          | Base II   | `{insShortName}_{acquirerBins}_{ddMMyyyy}.{seq:03d}` |
| JAYWAN        | XML       | `{000}{participantId}{julianDate}{seq}.xml`          |
| AMEX          | Text      | (see AmexOutgoingServiceImpl)                        |
| COLLECTION MC | IPM/GCO   | Same as MASTERCARD                                   |
| COLLECTION VISA | Base II/GOC | Same as VISA                                     |

### 5. File Format Details

#### 5.1 Mastercard IPM/EBCDIC Format (`IpmOutEbcidic`)

Files are binary EBCDIC-encoded ISO 8583 messages.

**Header Record** (MTI 1644):
- Field 23 (DE024 = "697") — header flag
- Field 47 (DE048) — contains: "0105025002" + yyMMdd + processorId (11 chars, zero-padded) + seqNo (5 digits) + "0122001" + PROCESSING_MODE
  - fileId = substring(7,32) of DE048 content
- Field 70 (DE071) — "00000001"
- Encoded in EBCDIC, bitmap set

**Detail Records** — per transaction entity from `IpmOutWorkEntity`:
- MTI from DE001 field
- DE002 (PAN) — decrypted from cryptic token
- DE003 (Transaction Code)
- DE004 (Amount, transaction)
- DE012 (Local transaction time)
- DE022 (POS entry mode)
- DE023 (Card sequence number)
- DE024 (Network international ID)
- DE025 (Point of entry mode / condition)
- DE026 (Point of entry condition code)
- DE030, DE031, DE032, DE033 (acquirer/batch/reference data)
- DE037 (Retrieval reference number)
- DE038 (Approval identification response)
- DE040, DE041 (card acceptor terminal ID)
- DE042, DE043 (card acceptor name/location)
- DE048 (Additional data — built from PDS fields)
- DE049 (Currency code)
- DE054 (Amount fees/surcharges)
- DE055 (ICC data — concatenated from DE055_9F26, 9F27, 9F10, 9F34, 9F33, 9F37, 9F36, 95, 9A, 9C, 9F02, 5F2A, 82, 9F1A, 9F03, 84)
- DE063 (Reserved private — network-specific)
- DE071, DE072 (Batch/lambda data)
- DE093, DE094, DE095 (File security / transaction origin)

Each record: 4-byte length + 4-byte MTI(EBCDIC) + 16-byte bitmap + data fields(EBCDIC)
If MTI is "1740", certain DE fields are nulled and DE048 is reconstructed from subset of PDS fields.

**Footer Record** (MTI 1644):
- Field 23 ("695") — footer flag
- Field 47 — contains: "0105025002" + yyMMdd + processorId + seqNo + "0301016" + padded amount (16 chars) + "0306008" + zero-padded recCnt (8 chars)
- Field 70 — zero-padded recCnt (8 chars)

**Post-processing**:
- IPM.tmp file is read back, every 1012 bytes insert two null bytes (0x00 0x00)
- Pad to 1014-byte boundary with zeros
- Write to final file

**`IPM` utility class**:
- Defines 128-field ISO 8583 element lengths and types
- Type codes: 0=BIN (fixed), 1=Alpha/ASCII (fixed), 2=LLVAR (2-digit length prefix), 3=LLLVAR (3-digit length prefix), 5=LLLVAR binary
- ASCII-to-EBCDIC conversion table (16×16 lookup matrix)
- Bitmap management (sets bits for present fields; auto-adds secondary bitmap if fields >64)

#### 5.2 Visa Base II Text Format (`BaseIIOutgoingServiceImpl`)

Files are plain text, one record per line. Each record is built from `TCRZeroVo` fields.

**Record types**:
1. **TCR0** (component seq 0) — Transaction Record: card data, amounts, merchant info
2. **Additional Data** (component seq 1) — Terminal/card accept ID, fee indicators, member text
3. **Payment Service Data** (component seq 5) — Auth amounts, DCC data, market indicators
4. **Chip Card Txn Data** (component seq 7) — EMV data (only if POS entry mode is "05" or "07")
5. **AFT Data** (component seq 3) — Account Funding Transaction data (only if business app ID present and txn code "05")
6. **Fee Collection** (separate) — For txn codes 10 and 20

**Footers**:
- **Footer 91**: Batch control record — txn count, batch number, TCR count, total amount
- **Footer 92**: File control record — totals across all batches, final aggregate

**Key formatting rules**:
- Currency multiplier from `CURRENCY_CODE_KAFKA` env (defaults based on currency fraction digits)
- DCC amounts used if `dccIndicator == 'Y'`
- Merchant names with "&" escaped to "^&"
- Amounts formatted as left-padded zero strings with currency multiplier applied
- Max 3250 TCR records per batch (triggers footer 91)

**`TCRZeroVo`** fields map to Visa Base II 1644 file format — has getters for 6 different record format strings: `getTcr0format`, `getAdditionalDataformat`, `getPaymentServiceDataformat`, `getChipCardTxnDataformat`, `getFeeCollectionformat`, `getAFTDataformat`.

#### 5.3 Jaywan XML Format (`JaywanOutgoingServiceImpl`)

Uses Jackson `XmlMapper` to serialize `JaywanXmlWrapperValueObject`:
- Root wrapper with `<Header>`, `<TransactionsBlock>`, `<Trailer>`
- Header: MTI=1644, function code=670, record number, date/time, member institution code, file ID, product code, file category, version, reject indicator
- Each transaction: MTI, function code, record number, transaction datetime, PAN (decrypted), acquirer ID, approval code, terminal ID, amounts, currency, merchant info, POS data
- Trailer: MTI=1644, function code=671, record count, total amount
- Written with XML declaration, standalone="no", pretty-printed
- File path from `RECON_OUT_{insShortName}` property + filename

### 6. CryptAPI Integration

- **Service**: External REST API for PAN encryption/decryption
- **Decrypt endpoint**: `decUrl` property (not defined in local application.properties; check server)
- **Encrypt endpoint**: `encUrl` property (not defined in local application.properties; check server)
- **Auth**: Basic auth with `cryptUserName:cryptPassword`, Base64-encoded
- **Headers**: `apiId` (encrypt/decrypt specific), `clientId`, `bankId`, `accessToken`, `Content-Type: application/json`
- **Chunk size**: 16 card numbers/tokens per request
- **Decrypt flow**: Send list of UUIDs → response contains `cardNumbers` map (UUID → PAN)
- **Encrypt flow**: Send list of PANs → response contains `uuids` map (PAN → UUID)
- **Response VOs**: `DecryptResponseVo` (has `cardNumbers` map), `EncryptResponseVo` (has `uuids` map)

### 7. Scheduler Infrastructure

**`OUTGOING_SCHEDULER` table schema** (`OutgoingSchedulerEntity`):
- `OGS_SER_NUMBER` (auto-increment PK)
- `OGS_LAST_UPDATED` (timestamp)
- `OGS_UPDATED_USER` (int)
- `OGS_GEN_STATUS` (char: 'A'=active, 'D'=deactive)
- `OGS_TASK_ID` (unique string)
- `OGS_NETWORK` (MASTERCARD, VISA, etc.)
- `OGS_TIME_ZONE` (string)
- `OGS_END_TIME` (HH:mm:ss format)

**`OUT_FILE_LOG` table** (`OutGoingFileProcessingEntity`):
- `OFL_SER_NUMBER` (auto-increment PK)
- `OFL_LAST_UPDATED`, `OFL_UPDATED_USER`, `OFL_INS_CODE`, `OFL_INT_CODE`, `OFL_FOR_CODE`
- `OFL_FILE_NAME` (string)
- `OFL_GNERATE_DATE`, `OFL_GENERATE_STATUS` (1=scheduled, 4=completed, 5=failed, 9=processing)
- `OFL_PROC_DATE` (java.sql.Date)
- `OFL_BUSS_DATE` (LocalDate)
- `OFL_PRJ_SER_NUMBER`, `OFL_TOT_TXN_COUNT`, `OFL_TOT_TXN_AMOUNT`
- `OFL_TOT_ACCP_TXN_COUNT`, `OFL_TOT_ACCP_TXN_AMOUNT`
- `OFL_FILE_ID` (string)

**Scheduler startup** (`SchedulerInitializer`):
- On application start, query `OUTGOING_SCHEDULER` where `gen_status = 'A'`
- For each active schedule, parse `endTime` as `LocalTime` and register with `DynamicSchedulerService.scheduleDailyTask()`
- Task calls `OutGoingProcessingService.automateSchedulerTriggering(endTime, network)`

**`DynamicSchedulerService`**:
- Uses `ThreadPoolTaskScheduler` (Spring)
- Maintains two `ConcurrentHashMap`s: `scheduledTasks` (taskId → ScheduledFuture), `taskTimes` (taskId → LocalTime)
- `scheduleDailyTask`: calculates next run time (today's time if future, else tomorrow), uses `Trigger` to schedule daily repeat
- `configureSchedulerCycle`: inserts/updates `OutgoingSchedulerEntity`, checks for duplicate endTime
- `removeCycle`: cancels scheduled task, deletes entity from DB
- `getScheduledTasks`: maps all entities to `OutgoingSchedulerVo`

### 8. Status Code Meanings

**OutGoingFileProcessingEntity.generatedStatus**:
- `1` = File scheduled / pending
- `9` = File processing in progress
- `4` = File generation completed successfully
- `5` = File generation failed

**Work/Data entity genStatus**:
- `3` = Pending (ready for outgoing)
- `9` = Marked for outgoing
- `4` = Outgoing completed
- `7` = Failed (decryption failed or processing error)

**OutgoingSchedulerEntity.genStatus**:
- `'A'` = Active
- `'D'` = Deactivated

### 9. Environment Properties (from application.properties)

**Core**:
- `spring.application.name=splitProcessAndStaging`
- `server.port=9031`
- `spring.datasource.url` (Oracle JDBC URL — see server for full DSN)
- `spring.jpa.hibernate.ddl-auto=none` or `validate`

**Institution config**:
- `INS_CODE` = 1
- `INS_SHORT_NAME` = "IRF" (check on server for actual value)
- `UPDATED_USER` = 4
- `INTERFACE_CODE_TLF` = 11
- `TIMESTAMP_JOB_NUMBER` = 1
- `EXCHANGE_RATE` = 0.27
- `PROCESSING_MODE` = (check on server)

**Network system codes** (format codes for OUTGOING):
- `MASTERCARD_SYSTEM_CODE`, `VISA_SYSTEM_CODE`, `JAYWAN_SYSTEM_CODE`, `AMEX_SYSTEM_CODE`, `MERCURY_SYSTEM_CODE`
- `GCO_SYSTEM_CODE`, `GOC_SYSTEM_CODE` (collection-only codes)
- `CURRENCY_CODE_KAFKA` (e.g., "USD000")

**Crypto API**:
- `encUrl`, `decUrl`
- `bankId`, `accessToken`
- `cryptUserName`, `cryptPassword`
- `cryptAppIdEncryption`, `cryptAppIdDecryption`, `cryptClientId`

**Jaywan**:
- `PRODUCT_CODE`, `FILE_CATEGORY`, `VERSION_NUMBER`

**Output directories**:
- `RECON_OUT_{INS_SHORT_NAME}` = base directory for outgoing files (e.g., `RECON_OUT_IRF`)

### 10. Database Schema Summary

Key tables and their relationships:

```
ACQUIRER_BINS (acquirer bin ranges, file sequences, MC ICA numbers)
├── bin_type: 'M' (Mastercard), 'V' (Visa), 'J' (Jaywan)
├── institution_code (FK to institution)
├── outfile_seq, outfile_date (track file numbering per day)
└── mc_ica_no (Mastercard ICA for IPM header)

OUTGOING_SCHEDULER (scheduled task definitions)
├── task_id (unique)
├── network (MASTERCARD/VISA/JAYWAN/AMEX)
├── gen_status ('A'/'D')
└── end_time (HH:mm:ss daily trigger time)

OUT_FILE_LOG (outgoing file generation tracking)
├── serial_number (PK)
├── institution_code, interface_code, format_code
├── file_name, file_id
├── generated_status (1/4/5/9)
└── business_date

OUTGOING_SUMMARY (per-message-type transaction summary)
├── message_type_id, function_code, proc_code
├── count, txn_amount, surcharge_amount, net_amount
└── file_id (FK to OUT_FILE_LOG)

MC_ACQ_TXN_WORK → MC_ACQ_TXN_DATA (Mastercard staging → archive)
VISA_ACQ_TXN_WORK → VISA_ACQ_TXN_DATA (Visa staging → archive)
JAYWAN_ACQ_TXN_WORK → JAYWAN_ACQ_TXN_DATA (Jaywan staging → archive)
AMEX_ACQ_TXN_WORK → AMEX_ACQ_TXN_DATA (Amex staging → archive)
VISA_GOC_WORK → VISA_GOC_DATA (Visa collection-only staging → archive)
MC_GCO_TXN_WORK → MC_GCO_TXN_DATA (MC collection-only staging → archive)
IPM_OUT_WORK (Mastercard IPM staging view)

InterfacesEntity (INTERFACES table) — network → interface_code mapping
FileFormatsEntity (FILE_FORMATS table) — system_code + type → format code
BusinessDateEntity (BUSINESS_DATE table) — institution → business_date
```

### 11. Implementation Guidance for Go Port

**Package structure** (under `go/`):
```
go/
  cmd/split-process-and-staging/    # main.go — HTTP server, scheduler init
  internal/
    controller/                     # HTTP handlers (OutGoingController, SchedulerController)
    service/                        # business logic orchestrators
    serviceimpl/                    # network-specific file generators
    ipm/                            # IPM/EBCDIC encoding, ISO 8583 field handling
    baseii/                         # Visa Base II record formatting, TCRZeroVo
    xmlgen/                         # Jaywan XML marshaling
    crypto/                         # CryptAPI HTTP client
    scheduler/                      # DynamicSchedulerService equivalent
    model/                          # Entities, VOs
    repository/                     # DB queries (Spring Data JPA equivalents)
```

**Key design decisions**:
1. Use `gorm.io/gorm` or `database/sql` + `sqlx` for Oracle connectivity (matching JPA semantics)
2. Use `encoding/xml` or `github.com/antch/go-xml` for Jaywan XML generation (Jackson XmlMapper equivalent)
3. Use `github.com/robfig/cron/v3` for dynamic scheduling (ThreadPoolTaskScheduler equivalent)
4. Use `gorilla/mux` or standard `net/http` for routing (Spring REST equivalent)
5. Use `encoding/json` for request/response VOs with validation via `go-playground/validator`
6. Background processing uses goroutines instead of Java Threads
7. File I/O uses standard `os`/`io` packages
8. HTTP client uses standard `net/http` for CryptAPI calls
9. Logging: `log/slog` or `github.com/sirupsen/logrus`

**Go-specific status conventions to mirror**:
- Work entity statuses: 3=pending, 9=marked, 4=completed, 7=failed
- File log statuses: 1=scheduled, 9=processing, 4=completed, 5=failed
- Scheduler status: 'A'=active, 'D'=deactivated (use string or rune)
- File size limit: 3250 TCR records per batch (triggers footer 91) for Visa
- IPM record padding: 1012-byte blocks with 2-byte null insertion
- Encryption chunk size: 16 entries per CryptAPI request

## Jaywan outgoing: IRF1 full-fidelity mapper restored (2026-10-01) — DECIDE LATER
`go/outsvc/jaywan{,_xml,_store,_entity}.go` are now the verbatim IRF1 port (all Txn
tags, Java declaration order), replacing IRF's reduced 26-tag V1.3 mapper that
joined `JAYWAN_NETWORK_DATA` for `nTxnId`/`nPosTxnStat`/`nProcCd`. `padRight` was
moved to `amex.go` (its only remaining user). The IRF-only `jaywanout_test.go`
(asserted byte-equality with `jaywan.xml`) and `JaywanNetworkDataEntity` +
`FindJaywanNetworkDataByRef` were removed. NOTE: the restored mapper emits tags the
rework had dropped (nAddData, nAmtBil, nAmtSet, nARD, nCcyCdBil, nCcyCdSet,
nConvRtBil, nConvRtSet, nDtSet, nIntrnTrackNum, nLtPrsntInd, nProcSts, nRecrPymtCd,
nRejRsnCd, nSetDCInd, nTxnDesInstCd, nUnFlNm) — decide later whether to trim back.

## AMEX outgoing wired end-to-end + two port fixes (2026-10-02)
AMEX was NOT a stub in `go/outsvc` (that comment was stale); `ProcessAmexOutgoing`
was already implemented and the deployed binary contained it. It only looked dead
because of missing config and two port bugs. To enable AMEX outgoing:

- **UI**: added `'AMEX'` to `NETWORKS` in
  `switch/inquiry-service/ui/src/pages/settlement/Outgoing.tsx` (dropdown now
  MASTERCARD, VISA, AMEX, JAYWAN, MERCURY). Rebuilt dist in
  `/tmp/opencode/inquiry-ui-build` (`npm run build`; cannot build on the FAT32
  workspace) and copied to workspace `ui/dist` + `/App/ui/dist`. inquiry-service
  serves `/App/ui/dist` (`INQUIRY_UI_DIST`), so no Go rebuild needed.
- **Config**: `/App/outgoing-service/outgoing-service.env` gained
  `AMEX_SYSTEM_CODE=121` (unset -> 0 -> `FindFileFormatBySystemCodeAndType(0,"O")`
  = nil -> `forCode=0` -> `OUT_FILE_LOG.OFL_FOR_CODE` FK violation ORA-02291).
- **DB seed** `IRF/replica/amex_staging_setup.sql` (idempotent): `FILE_FORMATS`
  row system 121 type 'O' (FOR_CODE is GENERATED ALWAYS identity — do NOT insert
  it; FOR_LAST_UPDATED is NOT NULL), and `ACQUIRER_BINS` bin_type 'A'
  (`ACQ_BIN='970964'`, fabricated; `ACQ_LAST_UPDATED` NOT NULL). AMEX interface
  INT_CODE=16 and AMEX_ACQ_TXN_WORK/DATA already existed.
- **Port fixes** (`go/outsvc`):
  1. `amex_store.go InsertAmexData`: placeholder count was `mercuryInsertValues(62)`
     but `amexColumns`/`amexDataArgs` are 57 -> ORA-01008 on the work->data move,
     which the Go flow then swallowed and still deleted the work rows (data loss).
     Fixed to 57.
  2. `amex.go amexFileName`: produced `VAPAY000001_...`; Java/spec name is
     `AMEX_FSF_VAPAY000001_{yyyyMMdd}_{seq:02d}` (Java literal in
     `AmexOutgoingServiceImpl`), so prefixed `AMEX_FSF_`.
- **Verified** (temp instance on :19032 with the new binary + env, separate
  output dir): AMEX FSF file `AMEX_FSF_VAPAY000001_20261002_03` written (TFH/TAB/
  TAA/TBT/TFS, PAN decrypted 4104999999999998), OUT_FILE_LOG row forCode 22
  intCode 16 status 4, WORK 0, AMEX_ACQ_TXN_DATA rows archived gen_status 4.
  No test currently covers `InsertAmexData`/`amexFileName` against a full file
  round-trip.
- **Deploy**: new binary `/tmp/opencode/outgoing-service` (md5
  `2a0aad09332f1488d0a9e578aa0b5221`) written over
  `/App/outgoing-service/outgoing-service` (ravi-owned, world-writable dir).
  **The running service is root-owned (PID started by start_all_services.sh step
  7b) and cannot be signalled without sudo — the USER must restart it** (e.g.
  `sudo /App/start_all_services.sh restart outgoing`, or stop+start) so the new
  binary and `AMEX_SYSTEM_CODE` take effect. Until then the old process keeps the
  old inode/env.

## Visa BASE II post-generation validation — **NOT IMPLEMENTED (stub only)** (2026-10-04 correction)

**An earlier version of this section claimed validation was working. It is not.**
Corrected 2026-10-04 after verifying against the tree.

- `go/base2` **does not exist** in this repo. `go/outsvc/visa.go`
  `validateVisaFile(...)` returns `nil` unconditionally and `visaValidation(...)`
  returns `nil`, both with comments saying "base2 package not present". There is
  no `base2/base2_test.go` either.
- Consequence: **no Visa file is ever parsed after generation.** No
  `base2_reports/*.csv|*.jsonl` are written, `/outgoing/v1/validations` returns
  nothing, and **`VISA_VALIDATION_STRICT=true` has no effect** because the
  strict branch is unreachable (`verr` is always nil). `BASE2_REPORTS_DIR` is
  likewise unused.
- `outsvc/visaout_test.go TestProcessVisaOutgoingRecordsValidation` and
  `TestProcessVisaOutgoingStrictAborts` were rewritten to **assert the stub
  behaviour** ("validations = 0, want 0 (base2 not available)"). They are not
  evidence that validation works — they pin that it does nothing.
- The claims of Python parity on `tc3009.003` / `tc0709.001` / `tc2608.001`
  were never reproducible in this tree. Do not treat them as verified.
- To actually implement: port `BASE2/settlement_parser` to `go/base2` with
  `go:embed` for `config.json` + `layouts/visa/**`, then give `validateVisaFile`
  a real body calling it. The BASE2 layouts (including the `tc10`, `tc20`,
  `tc25`, `tc26`, `tc61` record types) still need adding there.

## Visa BASE II fixed-width fields: pad AND clamp (2026-10-04)
  service. `BASE2_REPORTS_DIR` added to `/App/outgoing-service/outgoing-service.env`.
- **Known non-issue**: the generated test file fails validation because the fake
  store bin yields center `100870` vs expected CIB `409083` and short trailer
  records — that is why report-only is the default.

### Visa BASE II fixed-width fields: pad AND clamp (2026-10-04)

Every Base II record is exactly 168 bytes (`base2/config/config.json`
`recordLength`, and every `base2/layouts/visa/**` layout). Two separate classes
of bug were breaking that and making the parser reject whole files:

1. **Empty input emitted `"null"`.** `rpad`/`lpad`/`jstr` returned the 4-char
   string `"null"` instead of n pad characters, so any blank field silently
   shortened the record by `n-4` and shifted every later field (e.g. `Source
   Amount ... got 'MERCHANT'`). Fixed: helpers now blank-pad; added `orSpace`.
2. **Pad-without-clamp lengthened the record.** Java's `StringUtils.leftPad`
   returns the input unchanged when it is LONGER than the width, so
   over-long values (15-char `BussAppId`, 8-char `acqBin`, 16-char PAN in a
   6-byte field) produced 170/181/193-byte records. Fixed by clamping each
   fixed-width site with `sleft(...)`/`sright(...)`. Affected: fee-record card
   number + `acqBin`, AFT `BussAppId`, `TxnCurCode`, `TxnId`, and **both
   footers** (`generateFooter91`/`92` insert `acqBin` for the 6-byte CIB).

**Why this hid for so long:** `TestBaseIIRecordLengths` *documented the bug as
intended* ("Java leftPad does not truncate ... make it 193") and asserted 193/181.
If a test asserts a wrong width, the code is wrong, not the test.

Guards (all proven to fail on the pre-fix code):
`TestBaseIIRecordWidths`, `TestTcr0EmptyArnKeepsWidth`, `TestBaseIIHelpers`.
Verified end-to-end: `TestProcessVisaOutgoing*` previously logged 6x
`invalid record length 170 expected 168`; now zero.

Fixture notes (not bugs): the fake store's `acqBin` is `10087096` while the
layout hardcodes CIB `409083`, so a Python-parser check of the test output
intentionally logs 2 CIB violations. Production/UAT use `ACQ_BIN=409083`.
`Source Currency Code` must be
ISO-4217 **numeric** (`^[0-9]{3}$`) — it comes from `VISA_ACQ_TXN_WORK.
VTD_TXN_CUR_CODE` (`784` locally); `NewBaseIIGenerator`'s arg only sets the
fractional-digit multiplier, it is not written to the record.

### Visa regeneration re-verified after the width fix (2026-10-04)

Moved the 21 `VISA_ACQ_TXN_DATA` rows back to `VISA_ACQ_TXN_WORK` (gen_status 3)
and regenerated with the fixed binary on **:19033** (`RECON_OUT_IRF=/tmp/opencode/
visaout/regen`) -> `IRF_409083_04102026.002`, 85 records.

The "violations" column was counted by running the **Python** parser in
`BASE2/settlement_parser` over the output — NOT by the Go service, whose
validation is a stub (see the correction above).

| | BEFORE `TEST_409083_03102026.002` | AFTER `IRF_409083_04102026.002` |
|---|---|---|
| records | 14 (3 were 149 bytes) | 85 (all 168) |
| violations (Python parser) | **41** | **2** |
| literal `"null"` | yes | no |

All 85 records are exactly 168 bytes: TC05/TCR0,1,5,7 + TC91 + TC92. The only
remaining 2 violations are `Authorization code must be present`, and they are
**source data, not the generator**: `SELECT COUNT(VTD_APPR_CODE),
SUM(CASE WHEN TRIM(VTD_APPR_CODE) IS NULL...)` on the 21 staged rows gives 20
present / 2 blank, matching the 2 empty fields 1:1.

Reversing the fix makes the guards fail, so they are real guards:
`rpad`->`"null"` (tcr0 140 / additionalData 157 / fee 102), `jstr` for
TerminalCapability (tcr0 171), fee card unclamped (fee 191), AFT `BussAppId`
unclamped (aft 166).

## Visa generation E2E + `visa_store.go` date-bind bug (2026-10-03)

First real Visa generation through the new binary:

- **Bug fixed**: `outsvc/visa_store.go` `FindVisaWorkFeeBetween` /
  `FindVisaWorkFeeLessThanEqual` / `FindVisaWorkTxnBetween` /
  `FindVisaWorkTxnLessThanEqual` bound the purchase-date literals **bare**
  (`... BETWEEN :4 AND :5`), so Oracle applied the session NLS format and raised
  `ORA-01861: literal does not match format string` (the MC `getTxnCount` and the
  first Visa queries already wrap `TO_DATE(:N,'YYYY-MM-DD HH24:MI:SS')`). Wrapped
  all four. Never surfaced before because the earlier attempt died at
  `InsertFileLog` (FK) before reaching the fee query.
- **Local Visa config seeded** (`replica/visa_staging_setup.sql`, idempotent):
  `FILE_FORMATS` system 117 type 'O' (FOR_CODE is GENERATED ALWAYS identity →
  omit it) and `ACQUIRER_BINS` `409083` bin_type 'V' (fabricated; CIB 409083
  matches switch DE32/DE33 and the BASE II trailer check). `VISA_SYSTEM_CODE=117`
  added to `/App/outgoing-service/outgoing-service.env` (was missing → forCode 0 →
  `FK_OFL_FOR_CODE` ORA-02291). Missing either of these = "Scheduled
  Successfully" reply but no file (the reply is returned before the background
  generation, so it is NOT a success indicator; check `/App/logs/outgoing.log`).
- **E2E run**: `vp_switch_load.py visa 1 1 --host 127.0.0.1 --port 4000 --http`
  → core `txn OK f39=00` → staged `VISA_ACQ_TXN_WORK` (status 3) → temp
  new-binary instance on :19032 → `generateOutgoing` VISA → file
  `TEST_409083_03102026.002`, DB moved WORK→DATA, `OUT_FILE_LOG` status 4.
  The `base2_reports/*.csv|jsonl` files and the
  `GET /outgoing/v1/validations` response quoted in earlier revisions of this
  note were **not** produced by the Go service — its validation is a stub (see
  the correction above). The "41 failures" figure came from the Python parser
  run separately over the generated file.
- **Gotcha**: a prior failed run left an `OUT_FILE_LOG` row at status 9
  (`OFL_FILE_NAME` null); the next generation then returns "File Generation
  already Scheduled" (the format-code 1/9 guard) and writes nothing. Reset it to
  5 before retrying. The temp ravi instance cannot write `/vp-switch/OUTPUT`
  (root-owned 755) — run with `RECON_OUT_<INSSHORT>` pointing at a writable dir.
- **CORRECTION (2026-10-04) — the old "synthetic-row caveat" above was WRONG.**
  The 149-char TCR0 records were NOT caused by a sparse staged row. `rpad`/
  `lpad`/`jstr` returned the literal string `"null"` for an empty input, so
  `rpad(e.Arn, 23, " ")` emitted 4 bytes instead of 23 and shifted every
  following field. Real UAT never showed it only because ARN is always
  populated there. Fixed in `baseii.go` (blank-pad + `orSpace`), so the same
  sparse row now yields a full 168-byte record. Do not re-add that caveat.
- **Deployed**: new binary written to `/App/outgoing-service/outgoing-service`
  (md5 `c2d48651edb0c55e71edb04caaad825d`). The running process is root-owned;
  **USER must restart** to activate the binary + `VISA_SYSTEM_CODE`.
