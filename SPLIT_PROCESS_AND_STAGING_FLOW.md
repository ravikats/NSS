# splitProcessAndStaging Flow Documentation

## Quick Overview

`splitProcessAndStaging` is a Spring Boot application (port 9031) that generates **outgoing settlement files** for payment networks. It pulls pending transactions from "work" tables, decrypts card numbers via an external CryptAPI, formats them into network-specific file formats (IPM/EBCDIC, Base II text, XML), writes the files to disk, then moves the transactions to "data" tables.

---

## 1. Entry Points (HTTP API)

### Regular Outgoing Endpoints (`OutGoingController` at `/outgoing/`)

| Method | Endpoint | Purpose |
|--------|----------|---------|
| `POST` | `/outgoing/v1/generateOutgoing` | Generate a settlement file for MASTERCARD/VISA/JAYWAN/AMEX |
| `POST` | `/outgoing/v1/revertLastOutgoing` | Revert the last generated file (move data back to work tables) |
| `PUT` | `/outgoing/v1/updateRejectedData` | Update rejected transaction data |
| `POST` | `/outgoing/v1/generateCollectionOnly` | Generate a collection-only file for UAESWITCH/OMANNET networks |
| `POST` | `/outgoing/v1/revertLastCollectionOnly` | Revert last collection-only file |

**Request Body for `/v1/generateOutgoing`** (JSON):
```json
{
  "network": "MASTERCARD",
  "fromDate": "14/08/2026 10:00:00",
  "toDate": "14/08/2026 23:59:59"
}
```

**Request Body for `/v1/generateCollectionOnly`** (JSON):
```json
{
  "network": "UAESWITCH",
  "scheme": "MASTERCARD",
  "fromDate": "14/08/2026 10:00:00",
  "toDate": "14/08/2026 23:59:59"
}
```

### Scheduler Endpoints (`SchedulerController` at `/OutgoingScheduler/`)

| Method | Endpoint | Purpose |
|--------|----------|---------|
| `POST` | `/OutgoingScheduler/v1/addCycle` | Add a daily recurring scheduled task |
| `DELETE` | `/OutgoingScheduler/removeCycle/{name}` | Remove a scheduled task |
| `PUT` | `/OutgoingScheduler/v1/UpdateCycle` | Update an existing scheduled task |
| `GET` | `/OutgoingScheduler/v1/getAllCycle` | List all scheduled tasks |

**Request Body for `addCycle`/`UpdateCycle`**:
```json
{
  "name": "MC_EOD",
  "network": "MASTERCARD",
  "isActive": true,
  "endTime": "23:30:00"
}
```

### Health Check
- `GET /` → returns a static health string.

---

## 2. Request Flow Diagrams

### 2.1 Regular Outgoing: `POST /v1/generateOutgoing`

```
[Client]
  ⊂ POST /outgoing/v1/generateOutgoing
       body: { network: "MASTERCARD", fromDate: "...", toDate: "..." }
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│ OutGoingController.generateOutGoing()                           │
│   1. Read env props: UPDATED_USER, INS_CODE, INS_SHORT_NAME       │
│   2. Determine formatCode from network (env: MASTERCARD_SYSTEM_CODE etc.)│
│   3. Call outGoingProcessing.processAndMoveData(...)            │
└─────────────────────────────────────────────────────────────────┘
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│ OutGoingProcessingService.processAndMoveData()                  │
│   1. Parse fromDate/toDate (dd/MM/yyyy HH:mm:ss)                │
│   2. Validate: toDate must be >= fromDate                         │
│   3. getTxnCount(network, insCode, fromDate, toDate)             │
│      → Queries work repo by institution + status=3 + date range │
│      → Returns 0 for unknown networks                            │
│   4. If txnCount == 0 → return "There are no transactions to stage!"│
│   5. spawnFileProcessing(...) — spawns a NEW THREAD               │
│      → Mastercard → MCOutgoingService.processMCOutgoing()        │
│      → Visa     → VisaOutgoingServiceImpl.generateVisaOutgoing()│
│      → Jaywan   → JaywanOutgoingServiceImpl.generateJaywanOutgoing()│
│      → Amex     → AmexOutgoingService.generateAmexOutgoing()    │
│   6. Return "Outgoing File Processing Scheduled Successfully."  │
│                                                              ^   │
│   NOTE: The background thread handles steps 2.2a–2.2u below   │   │
└─────────────────────────────────────────────────────────────────┘
        │
        └─ (async — response returns immediately)
```

### 2.2 Collection-Only: `POST /v1/generateCollectionOnly`

```
[Client]
  ⊂ POST /outgoing/v1/generateCollectionOnly
       body: { network: "UAESWITCH", scheme: "MASTERCARD", fromDate: "...", toDate: "..." }
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│ OutGoingController.generateCollectionOnlyOutgoing()             │
│   1. Read env props                                               │
│   2. Determine formatCode from SCHEME (GCO_SYSTEM_CODE / GOC_SYSTEM_CODE)│
│   3. Call collectionOnlyService.processAndMoveData(...)         │
└─────────────────────────────────────────────────────────────────┘
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│ CollectionOnlyProcessingService.processAndMoveData()              │
│   1. Parse dates                                                  │
│   2. getTxnCount(scheme, insCode, fromDate, toDate, network)      │
│      → MASTERCARD scheme → mcGCOWorkRepo (by network)            │
│      → VISA scheme → visaGOCTxnRepo (by network)                 │
│   3. If txnCount == 0 → return "There are no transactions to stage!"│
│   4. spawnFileProcessing(...) — spawns a NEW THREAD              │
│      → MASTERCARD + UAESWITCH/OMANNET → GCOServiceImpl.generateMcCollectionOnly()│
│      → VISA + UAESWITCH/OMANNET → GOCServiceImpl.generateVisaCollectionOnly()│
│   5. Return "CollectionOnly File Processing Scheduled Successfully."│
└─────────────────────────────────────────────────────────────────┘
```

### 2.3 Revert: `POST /v1/revertLastOutgoing`

```
[Client] ⊂ POST /v1/revertLastOutgoing
       body: { network: "MASTERCARD" }
        │
        ▼
┌ OutGoingController.revertOutgoing() ──────────────┐
│   → calls outGoingProcessing.revertLastOutgoingData(network, insCode) │
└───────────────────────────────────────────────────┘
        │
        ▼
┌ OutGoingProcessingService.revertLastOutgoingData() │
│   1. Look up interface code for the network        │
│   2. Find last successful file log entry:          │
│      outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode) │
│   3. Get the fileId from that entry                │
│   4. Find matching data entities in the DATA table │
│   5. Map each data entity → work entity (set gen_status=3) │
│   6. Save work entities back to WORK table         │
│   7. Update POS transactions: set genStatus=4, outStatus="Marked for Outgoing" │
│   8. Delete data entities from DATA table          │
│   9. Delete file log entry from OUT_FILE_LOG      │
│  10. Return "Revert Successfully Completed"       │
└───────────────────────────────────────────────────────────────────┘
```

### 2.4 Scheduled Trigger Flow

```
┌─────────────────────────────────────────────────────────────┐
│ SchedulerInitializer (on app startup)                        │
│   1. Query OUTGOING_SCHEDULER where gen_status='A'          │
│   2. For each active schedule:                               │
│      → Parse endTime (HH:mm:ss)                              │
│      → Call DynamicSchedulerService.scheduleDailyTask()     │
│        → Uses ThreadPoolTaskScheduler with a Trigger         │
│        → Calculates next run: if endTime is today & future, run today; │
│          else run tomorrow                                   │
 │        → Task runs every 24h at the scheduled time        │
│        → Task calls: outGoingProcessing.automateSchedulerTriggering(endTime, network) │
└─────────────────────────────────────────────────────────────┘
        │
        ▼
┌ OutGoingProcessingService.automateSchedulerTriggering(endTime, network) │
│   1. Read env props (formatCode, insCode, user, insShortName)             │
│   2. Parse endTime as LocalTime → LocalDateTime of today                 │
│   3. getTxnCount(network, insCode, null, endTime)                        │
│      → fromDate=null → uses toDateLessThanEqual queries                 │
│   4. If txnCount == 0 → log and return                                  │
│   5. spawnFileProcessing(insCode, user, formatCode, insShortName,       │
│     network, null, endTime)                                              │
│   6. Return "Outgoing File Processing Scheduled Successfully."         │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Network-Specific File Generation Flows

All four flows share these common steps (steps a–e):

```
a. Look up FileFormatsEntity by system_code + type='O' → forCode
b. Look up InterfacesEntity by interface_category → intCode
c. Check if any OutGoingFileProcessingEntity exists with status IN (1,9) → if yes, abort ("already Scheduled")
d. Create new OutGoingFileProcessingEntity:
   - generatedStatus = 9 (processing)
   - businessDate from BusinessDateEntity
   - Save & flush → get serialNumber
e. Get/create AcquirerBinsEntity (by insCode + binType) → update sequence
```

### 3.1 Mastercard Outgoing (`MCOutgoingService.processMCOutgoing`)

```
Steps f–u:
  f. fileName = insShortName + "R111" + ddMMyyyy + "." + String.format("%02d", seqNo)
     e.g., "IRFR11114082026.01"
  g. Query McAcqTxnWorkEntity: insCode, intCode=9 (MCI interface), genStatus=3, date range
  h. Update work entities genStatus → 9
  i. Update OutGoingFileProcessingEntity with fileName
  j. Collect encrypted card tokens (DE002 field) from work entities
  k. Call CryptAPI.getCardNumber(tokens) → decrypt
  l. If decrypt fails → set work entities genStatus=7, return "Outgoing Failed"
  m. Call IpmOutEbcidic.ipmPro(fileName, processorId, seqNo, insCode, intCode,
     businessDate, serialNumber, user, insShortName, fromDate, toDate, "", "")
     → This is the HEAVY file generation logic (see §5.1)
  n. (ipmPro writes the file itself)
  o. Update OutGoingFileProcessingEntity with fileId, generatedStatus=4
  p. Insert OutgoingSummaryEntity records (grouped by txnType)
  q. Update work entities genStatus → 4
  r. Move work → data (McAcqTxnDataEntity)
  s. Delete from work table
  t. posTxnRepo.completePosStatus(insCode)
  u. Generate PDF via IOutGoingSummaryService.generateOutgoingSummaryPDF()
```

### 3.2 Visa Outgoing (`VisaOutgoingServiceImpl.generateVisaOutgoing`)

```
Steps f–u:
  f. fileName = insShortName + "_" + acquirerBins + "_" + ddMMyyyy + "." + String.format("%03d", seq)
     e.g., "IRF_BIN1234_14082026.001"
  g1. feeEntity = visaTxnWorkRepo.findBy...(insCode, intCode=3, txnCode IN('10','20'), ...)
      txnEntity = visaTxnWorkRepo.findBy...(insCode, intCode=3, txnCode NOT IN('10','20'), ...)
      data = visaTxnWorkRepo.findBy...(insCode, intCode=3, ALL, ...)  ← for decrypt + move
  h. updateVisaAcqWork(insCode, user, intCode, "", 3, startDate, toDate)
     → Sets genStatus from 3→9 (marks for outgoing)
  i. updateOutFilelog(fileName, fileId, insCode, outgoingLogSerialNumber)
  j. Collect encCardNumber from `data` list
  k. Decrypt via CryptAPI
  l. If decrypt fails → set data genStatus=7, return "Outgoing Failed"
  m. lines = baseIIService.getFeeAndTxnData(feeEntity, txnEntity, response, acqBin, seq)
     → Builds Base II TCR0/AdditionalData/PaymentServiceData/ChipCardTxnData/AFTData records
  n. fileId = writeLinesToFile(lines, insShortName, fileName) → writes CSV/text file
  o. updateOutFilelog(fileName, fileId, insCode, outgoingLogSerialNumber) → status=4
  p. insertIntoOutgoingSummary(user, insCode, intCode, fileName, serialNumber)
  q. updateVisaAcqWork(insCode, user, intCode, fileId, 9, startDate, toDate) → status=4
  r. posTxnRepo.completeVISAPosStatus(insCode)
  s. moveWorkToData(insCode, 4) → move visaTxnWork → visaAcqTxnData
  t. (no delete step shown, but work entities are moved to data)
  u. Generate PDF
```

**Note**: Visa has a separate `updateVisaAcqWork` call that's invoked **twice** — once with status=3 (to mark 3→9), and once with status=9 (to mark 9→4). The fee entity is queried separately (txnCode IN '10','20') for fee collection records.

### 3.3 Jaywan Outgoing (`JaywanOutgoingServiceImpl.generateJaywanOutgoing`)

```
Steps f–u:
  f. fileName = fileId + ".xml"
     fileId = "000" + participantId + julianDate(yyDDD) + seq
     e.g., "000P123456202652301.xml"
  g. Query JaywanAcqTxnWorkEntity: insCode, intCode, genStatus=3, date range (localDateTime)
  h. Set work entities genStatus → 9 (same logic: 3→9)
  i. updateOutFilelog(fileName, fileId, ...)
  j. Collect encryptedCardNumber from entities
  k. Decrypt via CryptAPI
  l. If decrypt fails → set genStatus=7, return "Outgoing Failed"
  m. mapToXmlWrapper(entities, ...) → build JaywanXmlWrapperValueObject
  n. generateXmlFile() → use Jackson XmlMapper, write with XML declaration (standalone="no"), pretty-printed
  o. updateOutFilelog(fileName, fileName, ...) → status=4
  p. insertOutgoingSummary(user, insCode, intCode, fileName, serialNumber)
  q. entities genStatus → 4
  r. moveWorkToData(insCode, entities, fileName) → JaywanAcqTxnDataEntity
  s. txnWorkRepo.deleteAll(entities) ← Note: explicitly deletes work entities
  t. posTxnRepo.completeJaywanPosStatus(insCode)
  u. Generate PDF
```

**Note**: Jaywan deletes work entities explicitly (unlike MC/Visa which implicitly remove during move or leave them).

### 3.4 Collection-Only MC (`GCOServiceImpl.generateMcCollectionOnly`)

Same as §3.1 Mastercard flow, but:
- Calls `IpmOutEbcidic.ipmPro(fileName, processorId, seqNo, insCode, intCode, businessDate, outgoingLogSerialNumber, user, insShortName, fromDate, toDate, network, "GCO")`
- The `fileType="GCO"` parameter causes ipmPro to use `McGCOTxnWorkEntity` instead of `McAcqTxnWorkEntity`

### 3.5 Collection-Only VISA (`GOCServiceImpl.generateVisaCollectionOnly`)

Same structure as §3.2 Visa, but:
- Uses `BaseIIGOCService.getGOCTxnData()` instead of `BaseIIOutgoingService.getFeeAndTxnData()`
- Queries `VisaGOCWorkEntity` instead of `VisaAcqTxnWorkEntity`
- Moves to `VisaGOCDataEntity` instead of `VisaAcqTxnDataEntity`

### 3.6 Amex Outgoing (`AmexOutgoingServiceImpl`)

Uses a **single INSERT ALL** SQL statement to bulk-insert AmexAcqTxnDataEntity records, then deletes work records, updates POS status, and generates PDF.

---

## 4. Database Schema (Key Tables & Fields)

### 4.1 ACQUIRER_BINS
| Column | Type | Purpose |
|--------|------|---------|
| BIN_TYPE | CHAR | 'M' (Mastercard), 'V' (Visa), 'J' (Jaywan) |
| INSTITUTION_CODE | INT | FK to institution |
| OUTFILE_SEQ | INT | File sequence number (per day) |
| OUTFILE_DATE | DATE | Date of last sequence increment |
| MC_ICA_NO | INT | Mastercard ICA for IPM header |
| ACQUIRER_BIN | STRING | Acquirer bin range (e.g., "1234") |

### 4.2 OUTGOING_SCHEDULER
| Column | Type | Purpose |
|--------|------|---------|
| OGS_SER_NUMBER | BIGINT (auto PK) | Serial number |
| OGS_LAST_UPDATED | TIMESTAMP | Last updated |
| OGS_UPDATED_USER | INT | Updated user |
| OGS_GEN_STATUS | CHAR | 'A' (active), 'D' (deactivated) |
| OGS_TASK_ID | VARCHAR | Unique task name |
| OGS_NETWORK | VARCHAR | MASTERCARD/VISA/JAYWAN/AMEX |
| OGS_TIME_ZONE | VARCHAR | Time zone string |
| OGS_END_TIME | VARCHAR | HH:mm:ss daily trigger time |

### 4.3 OUT_FILE_LOG (OutGoingFileProcessingEntity)
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | OFL_SER_NUMBER | BIGINT (auto PK) | |
| lastUpdated | OFL_LAST_UPDATED | TIMESTAMP | |
| updatedUser | OFL_UPDATED_USER | INT | |
| institutionCode | OFL_INS_CODE | INT | |
| interfaceCode | OFL_INT_CODE | INT | Network interface (e.g., 9=MCI) |
| formatCode | OFL_FOR_CODE | INT | File format code |
| fileName | OFL_FILE_NAME | VARCHAR | Generated file name |
| generateDate | OFL_GNERATE_DATE | TIMESTAMP | When generation started |
| generatedStatus | OFL_GENERATE_STATUS | INT | 1=scheduled, 4=completed, 5=failed, 9=processing |
| procDate | OFL_PROC_DATE | DATE | Business date |
| bussDate | OFL_BUSS_DATE | DATE | |
| projectSerialNumber | OFL_PRJ_SER_NUMBER | BIGINT | |
| txnCount | OFL_TOT_TXN_COUNT | INT | Total transactions |
| txnAmount | OFL_TOT_TXN_AMOUNT | DECIMAL | Total amount |
| accpTxnCount | OFL_TOT_ACCP_TXN_COUNT | INT | |
| accpTxnAmount | OFL_TOT_ACCP_TXN_AMOUNT | DECIMAL | |
| fileId | OFL_FILE_ID | VARCHAR | File ID (IPM) |

### 4.4 OUTGOING_SUMMARY (OutgoingSummaryEntity)
| Column | Type | Purpose |
|--------|------|---------|
| OUTGOING_MESSAGE_TYPE_ID | VARCHAR | Message type ID |
| OUTGOING_FUNCTION_CODE | VARCHAR | Function code |
| OUTGOING_PROC_CODE | VARCHAR | Process code |
| OUTGOING_COUNT | INT | Count |
| OUTGOING_TXN_AMOUNT | DECIMAL | Transaction amount |
| OUTGOING_SURCHARGE_AMOUNT | DECIMAL | Surcharge amount |
| OUTGOING_NET_AMOUNT | DECIMAL | Net amount |
| OUTGOING_FILE_ID | VARCHAR | FK to file log |

### 4.5 Work Tables → Data Tables Mapping
| Network | Work Table | Data Table |
|---------|-----------|------------|
| MASTERCARD | MC_ACQ_TXN_WORK | MC_ACQ_TXN_DATA |
| VISA | VISA_ACQ_TXN_WORK | VISA_ACQ_TXN_DATA |
| JAYWAN | JAYWAN_ACQ_TXN_WORK | JAYWAN_ACQ_TXN_DATA |
| AMEX | AMEX_ACQ_TXN_WORK | AMEX_ACQ_TXN_DATA |
| COLLECTION MC | MC_GCO_TXN_WORK | MC_GCO_TXN_DATA |
| COLLECTION VISA | VISA_GOC_WORK | VISA_GOC_DATA |

### 4.6 Common Work/Data Entity Fields (by network)
All work entities share these DB columns:
| Column | Type | Description |
|--------|------|-------------|
| INSTITUTION_CODE | INT | Institution code |
| INT_CODE | INT | Interface code |
| GEN_STATUS | INT | 3=pending, 9=marked, 4=completed, 7=failed |
| LOCAL_DATE_TIME | TIMESTAMP | Local transaction datetime |
| PURCHASE_DATE | DATE | (VISA specific) |
| ENC_CARD_NUMBER | VARCHAR | Encrypted card number (PAN token) |
| TXN_CODE | VARCHAR | Transaction code |
| TXN_AMOUNT | DECIMAL | Transaction amount |
| FILE_ID | VARCHAR | FK to generated file |
| OTHER ISO 8583 fields (DE002–DE095) | various | Transaction details |

### 4.7 INTERFACES (InterfacesEntity)
| Column | Type | Purpose |
|--------|------|---------|
| INTERFACE_CODE | INT | Interface code (9=MCI for MC, 3=VISA) |
| INTERFACE_CATEGORY | VARCHAR | Network category ("MCI", "VISA", "JAYWAN", "AMEX") |
| INSTITUTION_CODE | INT | |

### 4.8 FILE_FORMATS (FileFormatsEntity)
| Column | Type | Purpose |
|--------|------|---------|
| CODE | INT | Format code (forCode) |
| SYSTEM_CODE | VARCHAR | e.g., MASTERCARD_SYSTEM_CODE=115 |
| TYPE | CHAR | 'O' for outgoing |

### 4.9 BUSINESS_DATE (BusinessDateEntity)
| Column | Type | Purpose |
|--------|------|---------|
| BUSINESS_DATE | DATE | Current business date |
| INSTITUTION_CODE | INT | |

### 4.10 POS_TXN (used in completePosStatus)
| Column | Type | Purpose |
|--------|------|---------|
| GEN_STATUS | INT | Updated to 4 after outgoing |
| OUT_STATUS | VARCHAR | Updated to "Marked for Outgoing" on revert |

### 4.11 Status Code Reference

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

---

## 5. File Format Technical Details

### 5.1 IPM / EBCDIC (Mastercard) — `IpmOutEbcidic.ipmPro()`

```
[IPM.tmp file]
├─ Header Record (MTI=1644)
│  ├─ DE024 = "697" (header flag)
│  ├─ DE048 = "0105025002" + yyMMdd + procId(11 chars, 0-padded) + seqNo(5 digits) + "0122001" + PROCESSING_MODE
│  │         → fileId = DE048.substring(7, 32)
│  └─ DE071 = "00000001"
│
├─ Detail Records (one per transaction)
│  ├─ MTI from DE001 (transaction's message type)
│  ├─ DE002 (decrypted PAN)
│  ├─ DE003–DE095 (various ISO fields, conditionally set)
│  ├─ DE055 = concatenation of ICC data elements (9F26, 9F27, 9F10, etc.)
│  ├─ If MTI="1740" → some DEs nulled, DE048 reconstructed from fewer PDS fields
│  └─ Each record: 4-byte length + 4-byte MTI(EBCDIC) + 16-byte bitmap + data(EBCDIC)
│
└─ Footer Record (MTI=1644)
   ├─ DE024 = "695" (footer flag)
   ├─ DE048 = "0105025002" + yyMMdd + procId + seqNo + "0301016" + amount(16 chars, 0-padded) + "0306008" + recCnt(8 chars, 0-padded)
   └─ DE071 = zero-padded recCnt (8 chars)
```

**Post-processing** (critical IPM detail):
```
Read IPM.tmp → write to final fileName:
  - For every 1012 bytes written, insert 2 null bytes (0x00 0x00)
  - Pad remaining bytes to next 1014-byte boundary with zeros
  - Write one final 1014-byte block of zeros
```

**`IPM` utility class functions**:
| Method | Purpose |
|--------|---------|
| `AddIsoField(Msg, idx, value, bitmap)` | Add field to message, set bitmap bit, based on type |
| `GetFieldType(idx)` | Return type code (0=BIN, 1=Alpha, 2=LLVAR, 3=LLLVAR, 5=LLLVAR-binary) |
| `GetFieldLength(idx)` | Return fixed length |
| `CreateBitMap(idx, bitmap)` | Set bit at position idx |
| `AsciiToEbcdic(val)` | Convert ASCII to EBCDIC via 16×16 Tcode lookup table |
| `SetSecondaryBitMap(map)` | Set bit 1 of byte[0] if any secondary fields present |

### 5.2 Visa Base II Text — `BaseIIOutgoingServiceImpl.getFeeAndTxnData()`

```
Output: List<StringBuilder> (one line per record)
├─ TCR0 records (component seq="0"): one per transaction
│  → Fields: txnCode, codeQualifier, seqNum, PAN(16), ext(3), floorLimit, crbExcp, reserved,
│    ARN(23), acquirerBusID("10087096"), purchaseDate(MMdd), destAmt(12 zeros), destCcy(3 spaces),
│    sourceAmt(12 zeros, currency-multiplied), sourceCcy(3),
│    merchantName(25, "&"→"^&"), city(13), country(3), mcc(4), zip(5 zeros), state(3 spaces),
│    reqPaymentService("9"), numPaymentForms(" "), usageCode("1"), reasonCode("00"),
│    settlementFlag("9"), authCharIndicator, authCode(6), posTerminalCapability, reserved1(" "),
│    cardholderIDMethod, collectionOnlyFlag(" "), posEntryMode(2), centralProcessingDate,
│    reimbursementAttribute("B")
│
├─ Fee Collection records: for txnCode IN ("10", "20")
│  → destinationIdentifier, sourceIdentifier, msgText, etc.
│
├─ Footer 91 (batch control): triggered at 3250 TCRs
│  → "91" + acquirerBins + 5 zeros + txnCount(12) + batchNum(6) + tcrCount(12) + totalAmount(15 zeros) + 7 spaces
│
└─ Footer 92 (file control): at end
   → "92" + acquirerBins + 5 zeros + allTxnCount(12) + batchNum(6) + allTcrCount(12) + allAmount(15 zeros) + 7 spaces
```

### 5.3 Jaywan XML — `JaywanOutgoingServiceImpl`

Uses Jackson `XmlMapper` to serialize `JaywanXmlWrapperValueObject`:
```xml
<root standalone="no">
  <Header>
    <N-MTI>1644</N-MTI>
    <N-FunCd>670</N-FunCd>
    <N-RecNum>00000001</N-RecNum>
    <N-DtTmFlGen>MMddhhmmss</N-DtTmFlGen>
    <N-DtSet>yyMMdd</N-DtSet>
    <N-MemInstCd>participantId</N-MemInstCd>
    <N-UnFlNm>fileId</N-UnFlNm>
    <N-ProdCd>PRODUCT_CODE</N-ProdCd>
    <N-FlCatg>FILE_CATEGORY</N-FlCatg>
    <N-VerNum>VERSION_NUMBER</N-VerNum>
    <N-FlRejInd>N</N-FlRejInd>
  </Header>
  <TransactionsBlock>
    <Transaction> (one per transaction)
      <N-MTI>...</N-MTI>
      <N-AccptCd>...</N-AccptCd>
      <N-PmntAmt>...</N-PmntAmt>
      ... merchant data, PAN, approval code, etc.
    </Transaction>
  </TransactionsBlock>
  <Trailer>
    <N-MTI>1644</N-MTI>
    <N-FunCd>671</N-FunCd>
    <N-RecCnt>recCnt</N-RecCnt>
    <N-TotAmt>totalAmount</N-TotAmt>
  </Trailer>
</root>
```

### 5.4 Amex Text Format

Amex uses plain text format. Details in `AmexOutgoingServiceImpl.java`.

---

## 6. CryptAPI Integration

```
CryptAPI (external REST API at http://10.100.139.30:2728/cp-crypto-vault)
├─ Decrypt endpoint: /pan-decryption
│   → Request: { "tokens": ["uuid1", "uuid2", ... ] } (16 per batch)
│   → Response: { "cardNumbers": { "uuid1": "4111111111111111", "uuid2": "..." } }
│   → Auth: Basic auth with cryptUserName:cryptPassword (Base64)
│   → Headers: apiId (decryption=31), clientId, bankId, accessToken, Content-Type
│
└─ Encrypt endpoint: /pan-encryption
    → Request: { "pans": ["4111111111111111", "5500000000000004", ... ] } (16 per batch)
    → Response: { "uuids": { "4111111111111111": "uuid1", ... } }
    → Auth: Basic auth, apiId (encryption=26)
```

---

## 7. Environment Properties

| Property | Value | Description |
|----------|-------|-------------|
| `INS_CODE` | 1 | Institution code |
| `INS_SHORT_NAME` | TEST | Short name for file naming |
| `UPDATED_USER` | 2 | User ID for audit |
| `INTERFACE_CODE_TLF` | (env) 11 | TLF interface code |
| `MASTERCARD_SYSTEM_CODE` | 115 | Mastercard format system code |
| `VISA_SYSTEM_CODE` | 117 | Visa format system code |
| `AMEX_SYSTEM_CODE` | 121 | Amex format system code |
| `JAYWAN_SYSTEM_CODE` | 120 | Jaywan format system code |
| `GCO_SYSTEM_CODE` | 131 | Collection MC format code |
| `GOC_SYSTEM_CODE` | 133 | Collection VISA format code |
| `PROCESSING_MODE` | T | IPM header processing mode |
| `CURRENCY_CODE_KAFKA` | AED | Currency for Base II amounts |
| `PRODUCT_CODE` | (Jaywan) | Jaywan product code |
| `FILE_CATEGORY` | (Jaywan) | Jaywan file category |
| `VERSION_NUMBER` | (Jaywan) | Jaywan version |
| `RECON_OUT_TEST` | /vp-switch/OUTPUT/ | Output directory |
| `SERVER_PORT` | 9031 | HTTP server port |
| `encUrl` | http://10.100.139.30:2728/cp-crypto-vault/pan-encryption | Encrypt API |
| `decUrl` | http://10.100.139.30:2728/cp-crypto-vault/pan-decryption | Decrypt API |
| `bankId` | CPBA01000000001 | Crypto API bank ID |
| `accessToken` | (secret) | Crypto API access token |
| `cryptUserName` | secretKey | Crypto API username |
| `cryptPassword` | (encrypted) | Crypto API password |

---

## 8. Background Processing

Java uses `new Thread().start()` for all file generation:

```java
new Thread(() -> {
    switch (network) {
        case "MASTERCARD" -> mcOutGoingService.processMCOutgoing(...);
        case "VISA" -> visaOutService.generateVisaOutgoing(...);
        case "JAYWAN" -> jaywanService.generateJaywanOutgoing(...);
        case "AMEX" -> amexService.generateAmexOutgoing(...);
    }
}).start();
```

In Go, this maps to a **goroutine**: `go networkService.processOutgoing(...)`
