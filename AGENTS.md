# AGENTS.md

## Go module layout
The Go code lives under `go/` (module `empay/irf`, go.mod at `go/go.mod`). Go 1.24.6
is installed at `/tmp/opencode/go/bin/go`; no system Go is required.

## Build / verify commands (run from `go/`)
- `go build ./...`
- `go vet ./...`
- `go test ./...`              # all packages, including tlfsvc (fake-store IRF flow tests)
- `CGO_ENABLED=0 go build -trimpath -ldflags="-s -w" -o tlf-service ./cmd/tlf-service`
  (static binary for UAT deploy)

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
