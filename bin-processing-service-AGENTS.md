# bin-processing-service — Go Port Specification

## Overview

Spring Boot application (port 9029) that processes **incoming BIN range files** from multiple payment networks (Mastercard, Visa, Jaywan, OmanNet, Mercury). Parses files in various formats (EBCDIC binary, fixed-width text, CSV, Excel) and inserts BIN range records into Oracle DB tables.

## HTTP API Endpoints (`/bin/`)

| Method | Path | Handler |
|--------|------|---------|
| POST | `/bin/v1/processBin` | `BinProcessingController.processBin` → `BinProcessingService.processBin` |
| DELETE | `/bin/v1/deleteBinFile` | `BinProcessingController.deleteFailedBinFile` → `BinProcessingService.binFileDeletion` |
| GET | `/` | `StatusCheckController` → returns "OK" |

### Request VO — `BinRequestVo`

JSON body for both POST and DELETE:
```json
{
  "fileName": "MC_BIN_FILE.txt",
  "network": "MASTERCARD"
}
```

Validation rules:
- `fileName`: @NotNull, @NotBlank, @Size(max=60)
- `network`: @Pattern `^(?i)(MASTERCARD|VISA|JAYWAN|OMANNET|MERCURY)$`

## Architecture Flow

```
[HTTP Request]
  │
  ▼
BinProcessingController.processBin()
  │ Read env: UPDATED_USER, INS_CODE, INS_SHORT_NAME, BIN_INTERFACE_CODE,
  │   network-specific format code (MC_BIN_FORMAT_CODE etc.)
  │
  ▼
ValidationService.validateFile()
  ← Check file exists on disk
  ← Check countByUploadStatus(9) == 0 (no file already in processing)
  ← For VISA: check file.size > 0, fileName ends with ".txt"
  ← Returns BinResponseVO error or null (OK)
  │
  ▼
BinProcessingService.processBin()
  │ 1. insertProcessingJob() → INSERT INTO PROCESSING_JOBS → jobSerialNumber
  │ 2. commonService.insertFileUploadLog() → INSERT INTO FILE_UPLOAD_LOG → uploadSerialNumber
  │ 3. If file name duplicate (resCode=0) → return DUPLICATE_FILENAME
  │ 4. If job insert failed (null) → return FAILED_TO_INSERT_JOB
  │ 5. new BinFileProcessingThread(...) → spawn THREAD
  │ 6. Return "BIN File Processing Scheduled Successfully."
  │
  ▼ (async)
BinFileProcessingThread.run()
  │ 1. updateFileUploadLog(jobSerialNumber, 9, null) → status=processing
  │ 2. switch(network):
  │    → MASTERCARD → mcT67Pro.processMCBin()    (EBCDIC binary)
  │    → VISA → visaProcessing.processVisaBin()  (fixed-width text)
  │    → JAYWAN → jaywanBinProcess.processJaywanBin() (CSV)
  │    → OMANNET → omanNetBinProcessing.processOmanNetBin() (Excel)
  │    → MERCURY → mercuryBinProcessing.processMercuryBin() (CSV)
  └─→ deleteBinFile() → delete by jobNumber if status=5
```

## Network-Specific Processing

All processors take: `(fileName, userSerialNumber, jobSerialNumber, uploadSerialNumber, insShortName)`

### Mastercard BIN (`McT67Pro.processMCBin`)

**Input**: Binary EBCDIC file (T067/T068 format)  
**Output**: `MC_ISS_ACC_RANGE` table

1. Read file byte-by-byte (InputStream)
2. Track records delimited by STX(0x02)/ETX(0x03)/NUL(0x00)
3. Detect end-of-file: "PTRAILER RECORD IP0040T1"
4. Track field boundaries using special chars: `\u00b8`, `\u00a9`, `\u00c2`
5. Skip `@` character in records
6. For each record, call `insertMcIssAccRange()`:
   - Parse `cardProgId` [52:55] must match `DMC|MCC|MSI|PVL`
   - `activeCode` [7:8] must be `A` (active)
   - Only insert if both conditions met
   - Parse 36 fields via substring [0:5], [7:8], [11:30], [30:33], etc.
   - `effectiveDate`: [0:5] → `convertToGregorianDate()` (Julian → LocalDate)
   - Delete existing matching record: `deleteMCIssAccRange(lowRange, highRange, priorityCode)`
   - INSERT entity with genStatus=1
7. Set uploadStatus=4, totalAccepted=totalCount
8. Move file to `RECON_PROCESSED_{insShortName}`

### Visa BIN (`VisaARDEF.processVisaBin`)

**Input**: Fixed-width text file (ARDEF)  
**Output**: `VISA_ISS_ACC_RANGE` table

1. Read all lines → split on "\n"
2. batchSize = totalLines/10, clamped [1000, 2000]
3. `deleteFromVisaIssRange()`: delete ALL existing records in batches
4. For each line, parse fixed positions:
   - [0:9] issAccountRangeHigh.trim()
   - [12:21] issAccountRangeLow.trim()
   - [24:30] bin
   - [31:33] binLength → Integer.parseInt
   - [35:41] processingBin
   - [41:42] domain → charAt(0)
   - [42:43] region → charAt(0)
   - [43:45] countryAlphaCode
   - [58:60] cardProduct
   - [69:70] crdrIndicator → charAt(0)
   - [74:76] productSubTytpe (StringUtils.substring)
5. Batch save at batchSize threshold
6. updateProcess(status=4), moveFile → RECON_PROCESSED_

### Jaywan BIN (`JaywanBinProcessImpl.processJaywanBin`)

**Input**: CSV file (17 columns)  
**Output**: `JAYWAN_ISS_ACC_RANGE` table

1. Count lines → batchCount = totalLines/10, clamped [3500, 10000]
2. Open CSVReader (OpenCSV), skip header
3. For each row:
   - `isValidLine`: must have 16 commas (17 fields)
   - Check for existing BIN overlap: `findByBinRangeLowAndBinRangeHigh`
   - Delete overlapping records (batch threshold 1000)
   - `readLine()`: parse nextLine[0..16] → JaywanIssAccRangeEntity
     - nextLine[2],[3] → binRangeLow/High (Long.parseLong)
     - nextLine[4] → panLength (Integer.parseInt)
     - nextLine[5] → productType (charAt)
     - nextLine[6] → schemeCode (charAt)
     - nextLine[8] → cardType (Integer.parseInt)
     - nextLine[10] → currencyCode (Integer.parseInt)
     - nextLine[14] → issAccCap (charAt)
     - nextLine[15] → prodClssfy (charAt)
   - Batch save at batchCount threshold
4. Save remaining, delete remaining
5. updateProcess(status=4), moveFile → RECON_PROCESSED_

### OmanNet BIN (`OmanNetBinProcessing.processOmanNetBin`)

**Input**: Excel (.xlsx), first sheet  
**Output**: `OMANNET_BIN_DATA` table

1. Open XSSFWorkbook → getSheetAt(0)
2. batchSize = (totalRows/10), clamped [3500, 10000]
3. For each row (skip row 0):
   - Cell 0: SubRoute (≤20 chars, no special chars [@#$%], uppercase)
   - Cell 1: Route (same validation as SubRoute)
   - Cell 2: BinNumber (≤8 chars, numeric ^\d+$)
   - Cell 3: CardType (≤7 chars, no special chars)
   - genStatus set to 7 on validation failure with remark
4. `resolveCardType`: "credit"→'C', "debit"→'D', else→'P'
5. `removeDuplicate`: find existing bins → delete them
6. Batch save at batchSize threshold
7. updateFileUploadLog(uploadSerialNumber, 4, "Success")
8. updateProcess(status=4), moveFile → RECON_PROCESSED_
9. On EOFException/IOException → status=5, moveFile → RECON_REJECTED_

### Mercury BIN (`MercuryBinProcessing.processMercuryBin`)

**Input**: CSV file (9 columns)  
**Output**: `MERCURY_ISS_ACC_RANGE` table

1. Count lines → batchCount = totalLines/10, clamped [3500, 10000]
2. Open CSVReader, skip header
3. For each row:
   - `isValidLine`: 9 columns, not all blank
   - `readLine()`: parse nextLine[0..8] → MercuryIssAccRangeEntity
     - [0],[1] → binRangeLow/High (Long.parseLong)
     - [2]-[5] → cardType, cardProductId, cardVariant, cardScheme (Integer.parseInt)
     - [6],[7] → currencyCode, countryCode (Integer.parseInt)
     - [8] → status (charAt)
   - Switch on status:
     - 'A' → add to saveList, increment accTxnCount
     - 'E'/'U' → delete existing, add to saveList, increment accTxnCount
     - 'D' → add existing to deleteList
4. Batch delete/save at batchCount threshold
5. updateProcess(status=4), moveFile → RECON_PROCESSED_

## Database Schema

| Table | Description |
|-------|-------------|
| `PROCESSING_JOBS` | Parent job tracker; one row per BIN file upload |
| `FILE_UPLOAD_LOG` | Upload metadata; status 1=pending, 9=processing, 4=completed, 5=failed |
| `MC_ISS_ACC_RANGE` | Mastercard issuer/acquirer range data (36+ columns) |
| `VISA_ISS_ACC_RANGE` | Visa issuer/acquirer range data (15 columns) |
| `JAYWAN_ISS_ACC_RANGE` | Jaywan BIN range data (20+ columns) |
| `OMANNET_BIN_DATA` | OmanNet BIN routing data (9 columns) |
| `MERCURY_ISS_ACC_RANGE` | Mercury BIN range data (13 columns) |
| `BUSINESS_DATE` | Institution business date reference |

## Environment Properties

```properties
spring.application.name=bin-processing-service
server.port=9029

spring.datasource.url=jdbc:oracle:thin:@//switch-uat.c3guuusy8mm5.me-central-1.rds.amazonaws.com:1521/ORCL
spring.datasource.username=NETWORK_SETTLEMENT_UAT

INS_CODE=1
INS_SHORT_NAME=TEST
UPDATED_USER=2
BIN_INTERFACE_CODE=12
MC_BIN_FORMAT_CODE=2
VISA_BIN_FORMAT_CODE=3
JAYWAN_BIN_FORMAT_CODE=61

RECON_IN_TEST=/vp-switch/INPUT/
RECON_LOG=/Netset/Network_Settlement/logs/recon_log
RECON_PROCESSED_TEST=/vp-switch/OUTPUT/
RECON_REJECTED_TEST=/vp-switch/OUTPUT/
RECON_MC_BINFILES=/vp-switch/OUTPUT/

ACL_INTEGRATION_FLAG=1
BIN_PERMISSION_FILE=/Netset/Network_Settlement/BINSERVICE/config/PermissionFile_NetworkSettlement.json
ACL_URL=http://10.100.136.223:4008/entity-access-control
ACL_USER_APP_ID=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

spring.datasource.hikari.maximum-pool-size=20
logging.level.root=INFO
```

## ACL Integration (Startup)

On application startup (`@PostConstruct`):
1. Read `ACL_INTEGRATION_FLAG` (default "1")
2. If "1": read JSON from `BIN_PERMISSION_FILE` path, POST to `ACL_URL`
3. Auth header: `Authorization: Bearer {ACL_USER_APP_ID}`

## Status Codes

**Upload Status (FILE_UPLOAD_LOG.UPL_UPLOAD_STATUS)**:
| Code | Meaning |
|------|---------|
| 1 | Pending |
| 4 | Completed |
| 5 | Failed |
| 9 | Processing |

**Processing Job Status (PROCESSING_JOBS.PRJ_STATUS)**:
| Code | Meaning |
|------|---------|
| 1 | Started (implied) |
| 4 | Completed |
| 5 | Failed (implied) |

**MC Gen Status (MC_ISS_ACC_RANGE.MAR_GEN_STATUS)**:
| Code | Meaning |
|------|---------|
| 1 | Active |

**OmanNet Gen Status (OMANNET_BIN_DATA.OBN_GEN_STATUS)**:
| Code | Meaning |
|------|---------|
| 4 | Valid |
| 7 | Error/rejected |

## File Formats

### Mastercard T067/T068 (Binary EBCDIC)
- Record delimiters: STX (0x02), ETX (0x03), NUL (0x00)
- Field separators: `\u00b8`, `\u00a9`, `\u00c2`
- End-of-file marker: "PTRAILER RECORD IP0040T1"
- Fields parsed via substring offsets in 36-element array
- Only records where `cardProgId` ∈ {DMC, MCC, MSI, PVL} AND `activeCode` == 'A' are saved

### Visa ARDEF (Fixed-Width Text)
- 76-character lines (positions [0:76])
- 9 fields extracted via fixed-position substrings

### Jaywan BIN (CSV)
- 17 comma-separated columns, header row skipped
- Columns 0-16 map to entity fields

### OmanNet BIN (Excel)
- XLSX format, first sheet
- 4 columns: SubRoute, Route, BinNumber, CardType
- Validation rules per column (length, character restrictions)

### Mercury BIN (CSV)
- 9 comma-separated columns, header row skipped
- Column 8 = status (A=Add, E/U=Update, D=Delete)

## Key Design Notes for Go Port

1. Use goroutines instead of Java Threads for background processing
2. The `BinFileProcessingThread` inner class just calls `processBinFile` in a new thread — use `go`
3. Batch sizes are calculated as `totalLines/10`, clamped between min/max thresholds per network
4. File movement: rename from RECON_IN to RECON_PROCESSED on success, RECON_REJECTED on failure
5. Only failed files (uploadStatus=5) can be deleted via DELETE endpoint
6. Use `encoding/csv` for CSV parsing, `tealeg/xlsx` or `excelize` for Excel parsing
7. For MC EBCDIC binary parsing: read byte-by-byte with delimiter detection
8. ACL integration runs once at startup, not per-request
