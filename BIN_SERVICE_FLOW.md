# bin-processing-service Flow Documentation

## Quick Overview

`bin-processing-service` (port 9029) is a Spring Boot application that processes **BIN (Bank Identification Number) files** uploaded to the input directory. It parses incoming files in different formats (binary EBCDIC for Mastercard, CSV for Jaywan/Mercury, Excel for OmanNet, text for Visa), validates records, and stores BIN range data into corresponding database tables.

On startup, it also sends a permission file to an ACL (Access Control List) integration endpoint.

---

## 1. Entry Points (HTTP API)

**Base URL**: `http://host:9029/bin/`

| Method | Endpoint | Request Body VO | Purpose |
|--------|----------|----------------|---------|
| `POST` | `/bin/v1/processBin` | `BinRequestVo` | Process a BIN file for a given network |
| `DELETE` | `/bin/v1/deleteBinFile` | `BinRequestVo` | Delete a failed BIN file record from DB |

**Health Check**: `GET /` returns `"OK"`

### Request Body (`BinRequestVo`):
```json
{
  "fileName": "MC_BIN_FILE_20260814.txt",
  "network": "MASTERCARD"
}
```

**Validation Rules**:
- `fileName`: @NotNull, @NotBlank, @Size(max=60)
- `network`: @Pattern `^(?i)(MASTERCARD|VISA|JAYWAN|OMANNET|MERCURY)$`

---

## 2. Request Flow

### 2.1 Process BIN: `POST /bin/v1/processBin`

```
[Client]
  ⊂ POST /bin/v1/processBin
       body: { fileName: "MC_BIN_FILE.txt", network: "MASTERCARD" }
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│ StatusCheckController (GET /) → returns "OK"                    │
│                                                                      │
│ BinProcessingController.processBin()                                │
│   1. Read env props: UPDATED_USER, INS_CODE, INS_SHORT_NAME        │
│   2. Determine binFormatCode from network:                         │
│      → MASTERCARD → MC_BIN_FORMAT_CODE (2)                         │
│      → JAYWAN → JAYWAN_BIN_FORMAT_CODE (61)                        │
│      → VISA → VISA_BIN_FORMAT_CODE (3)                             │
│      → OMANNET → OMANNET_BIN_FORMAT_CODE (env)                     │
│      → MERCURY → MERCURY_BIN_FORMAT_CODE (env)                     │
│   3. binInterfaceCode = BIN_INTERFACE_CODE (12) from env           │
│   4. path = RECON_IN_{insShortName} + fileName                      │
│   5. File file = new File(path)                                     │
│   6. Call service.processBin(fileName, network, user, insCode,     │
│      binInterfaceCode, binFormatCode, insShortName, file)          │
└─────────────────────────────────────────────────────────────────┘
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│ BinProcessingService.processBin()                                 │
│   1. Call validationService.validateFile(file, network, fileName) │
│      → Check file exists                                          │
│      → Check countByUploadStatus(9) == 0 (no file in processing) │
│      → For VISA: check file not empty, fileName ends with .txt   │
│   2. If validation returns non-null response → return it          │
│   3. insertProcessingJob(user, insCode) → jobSerialNumber          │
│      → INSERT INTO PROCESSING_JOBS: lastUpdated, updatedUser,      │
│        institutionCode, refNumber=1, processName="BIN_FILE_PROCESSING",│
│        startTime=NOW → returns serialNumber                       │
│   4. commonService.insertFileUploadLog(insCode, filename, user,   │
│      jobSerialNumber, intCode, forCode) → uploadSerialNumber       │
│      → INSERT INTO FILE_UPLOAD_LOG (auto PK)                        │
│      → Returns 0 on unique constraint violation, null on error     │
│   5. If resCode is 0/null → return error response                   │
│   6. Else: new BinFileProcessingThread(...) → spawns THREAD         │
│   7. Return "BIN File Processing Scheduled Successfully."           │
└─────────────────────────────────────────────────────────────────┘
        │
        └─ (async — response returns immediately)
```

### 2.2 Background Thread: `BinFileProcessingThread`

```
┌ BinFileProcessingThread.run()                                    │
│   1. updateFileUploadLog(jobSerialNumber, 9, null) → set status=9 │
│      → UPDATE FILE_UPLOAD_LOG SET uploadStatus=9 WHERE jobNumber=X│
│   2. switch (network.toUpperCase()):                              │
│      → MASTERCARD → mcT67Pro.processMCBin(...)                    │
│      → VISA → visaProcessing.processVisaBin(...)                  │
│      → JAYWAN → jaywanBinProcess.processJaywanBin(...)            │
│      → OMANNET → omanNetBinProcessing.processOmanNetBin(...)      │
│      → MERCURY → mercuryBinProcessing.processMercuryBin(...)      │
└───────────────────────────────────────────────────────────────────┘
```

### 2.3 Delete Failed BIN File: `DELETE /bin/v1/deleteBinFile`

```
[Client] ⊂ DELETE /bin/v1/deleteBinFile
       body: { fileName: "MC_BIN_FILE.txt", network: "MASTERCARD" }
        │
        ▼
┌ BinProcessingController.deleteFailedBinFile()                    │
│   → service.binFileDeletion(fileName, network)                    │
└───────────────────────────────────────────────────────────────────┘
        │
        ▼
┌ BinProcessingService.binFileDeletion(filename, network)          │
│   1. Find FileUploadLogEntity by fileName                         │
│      → fileUploadLogRepo.findByFileName(filename)                 │
│   2. If entity == null → return "FILE_NOT_FOUND"                  │
│   3. If uploadStatus != 5 → return "DELETION_NOT_ALLOWED"         │
│      (only allows deletion of failed files with status=5)          │
│   4. Delete records from network-specific BIN table:               │
│      → MASTERCARD: mcIssAcqRangeRepo.deleteAllByJobSerialNumber(jobNum)│
│      → VISA: visaIssRepo.deleteAllByJobSerialNumber(jobNum)        │
│      → JAYWAN: jaywanMcIssAccRangeRepo.deleteAllByJobNumber(jobNum)│
│      → OMANNET: omanNetBinRepo.deleteAllByJobNumber(jobNum)        │
│   5. Delete FileUploadLogEntity by fileName                        │
│      → fileUploadLogRepo.deleteByFileName(filename)               │
│   6. Return "File deleted successfully."                          │
└───────────────────────────────────────────────────────────────────┘
```

### 3. ACL Integration (on app startup)

```
┌─────────────────────────────────────────────────────────────────┐
│ ACLIntegration.sendPermissionFIle()                           │
│   1. Read ACL_INTEGRATION_FLAG from env (default=1)              │
│   2. If flag == "1":                                             │
│      → readFile(): Read PermissionFile from BIN_PERMISSION_FILE   │
│      → sendRequest(JSON): HTTP POST to ACL_URL                  │
│        → Headers: Content-Type: application/json                  │
│        → Authorization: Bearer {ACL_USER_APP_ID} (JWT token)      │
│   3. If flag != "1": log "ACL_INTEGRATION_FLAG is not enabled"   │
└─────────────────────────────────────────────────────────────────┘
```

---

## 4. Network-Specific BIN Processing Flows

### 4.1 Mastercard BIN Processing (`McT67Pro.processMCBin`)

```
Input: Binary EBCDIC file (MC T067/T068 format)
Output: Records inserted into MC_ISS_ACC_RANGE table

Process:
  1. filePath = RECON_IN_{insShortName} + fileName
  2. businessDate = commonService.getBusinessDate() (from BUSINESS_DATE table)
  3. Open file as FileInputStream → wrap in BufferedReader
  4. Create log file at RECON_LOG + fileName + ".log" → redirect System.out
  5. Read byte-by-byte until EOF:
     - Track record boundaries: STX(0x02)/ETX(0x03)/NUL(0x00)
     - Detect "PTRAILER RECORD IP0040T1" → end of file marker → break
     - Track field boundaries using special chars: \u00b8 (¸), \u00a9 (©), \u00c2 (Â)
  6. For each record (when flag=true and not "@"):
     → insertMcIssAccRange(jobNumber, user, recordData, businessDate)
       a. Parse fixed-width fields from record:
          - [0:5] → effectiveDate (Julian date → LocalDate via convertToGregorianDate)
          - [7:8] → activeCode (Character)
          - [11:30] → issRangeLow (String)
          - [30:33] → gcmsProductId (String)
          - [33:52] → issRangeHigh (String)
          - [52:55] → cardProgId (String, must match DMC|MCC|MSI|PVL)
       b. Only process if fields[5] (cardProgId) matches "DMC|MCC|MSI|PVL" AND fields[1] (activeCode) == "A"
       c. Parse additional fields (55:57) through (158:159) into 36-element array
       d. deleteMCIssAccRange(lowRange, highRange, priorityCode) → delete existing matching record
       e. INSERT new MCIssAcqRangeEntity with all parsed fields, genStatus=1
  7. After EOF:
     → updateProcess(uploadSerialNumber, jobNumber, totalCount, totalCount, 4) → status=4 (success)
     → moveFile to RECON_PROCESSED_{insShortName}
     → Close streams
  8. On error:
     → Set uploadStatus=5, remarks="An error occurred..."
     → moveFile to RECON_REJECTED_{insShortName}
```

### 4.2 Visa BIN Processing (`VisaARDEF.processVisaBin`)

```
Input: Fixed-width text file (ARDEF format)
Output: Records inserted into VISA_ISS_ACC_RANGE table

Process:
  1. filePath = RECON_IN_{insShortName} + fileName
  2. Read entire file line-by-line into StringBuilder → split("\n")
  3. Calculate batchSize: totalLines/10, clamped to [1000, 2000]
  4. If lines.length > 0:
     a. deleteFromVisaIssRange(batchSize) → DELETE batch from VISA_ISS_ACC_RANGE
     b. For each line:
        → Parse fixed-position fields:
          - [0:9] → issAccountRangeHigh (trim)
          - [12:21] → issAccountRangeLow (trim)
          - [24:30] → bin
          - [31:33] → binLength (Integer)
          - [35:41] → processingBin
          - [41:42] → domain (Character)
          - [42:43] → region (Character)
          - [43:45] → countryAlphaCode
          - [58:60] → cardProduct
          - [69:70] → crdrIndicator (Character)
          - [74:76] → productSubTytpe
          → Save entity in batch (batchSize threshold)
     c. Save remaining entities
     d. updateProcess(uploadSerialNumber, jobNumber, totalCount, totalCount) → status=4
     e. moveFile to RECON_PROCESSED_{insShortName}
  5. If empty file → move to RECON_PROCESSED_ without processing
  6. On error:
     → Set uploadStatus=5 via handleUploadError()
     → moveFile to RECON_REJECTED_{insShortName}
```

### 4.3 Jaywan BIN Processing (`JaywanBinProcessImpl.processJaywanBin`)

```
Input: CSV file (Jaywan BIN format)
Output: Records inserted into JAYWAN_ISS_ACC_RANGE table

Process:
  1. filePath = RECON_IN_{insShortName} + fileName
  2. Count total lines → calculateBatchSize: totalLines/10, clamped [3500, 10000]
  3. Open CSVReader → skip header row
  4. For each CSV row (nextLine array):
     a. Validate: must have exactly 16 comma-separated fields → isValidLine()
     b. Check for existing BIN range overlap → findByBinRangeLowAndBinRangeHigh(low, high)
        → If found, add to delete list (batch threshold: 1000)
     c. Parse row into JaywanIssAccRangeEntity via readLine():
        - nextLine[0] → issuerBank
        - nextLine[1] → institutionId (Integer.parseInt)
        - nextLine[2] → binRangeLow (Long.parseLong)
        - nextLine[3] → binRangeHigh (Long.parseLong)
        - nextLine[4] → panLength (Integer)
        - nextLine[5] → productType (Character)
        - nextLine[6] → schemeCode (Character)
        - nextLine[7] → schemeProduct
        - nextLine[8] → cardType (Integer)
        - nextLine[9] → service (Integer)
        - nextLine[10] → currencyCode (Integer)
        - nextLine[11] → isoNumCurrCode (Integer)
        - nextLine[12] → actionTaken (Character)
        - nextLine[13] → binLength (Integer)
        - nextLine[14] → issAccCap (Character)
        - nextLine[15] → prodClssfy (Character)
        (Note: file has 17 fields, but readLine only uses indices 0-16)
     d. If entity != null → add to saveList (batch threshold)
     e. Increment counts
  5. Save remaining saveList → saveAllAndFlush
  6. Delete remaining deleteList → deleteAll + flush
  7. updateProcess(uploadSerialNumber, jobNumber, totalCount, totalAccTxnCount, 4) → status=4
  8. moveFile to RECON_PROCESSED_{insShortName}
  9. On error:
     → updateProcess → status=5
     → moveFile to RECON_REJECTED_{insShortName}
```

### 4.4 OmanNet BIN Processing (`OmanNetBinProcessing.processOmanNetBin`)

```
Input: Excel file (.xlsx, first sheet)
Output: Records inserted into OMANNET_BIN_DATA table

Process:
  1. filePath = RECON_IN_{insShortName} + fileName
  2. Open Excel: FileInputStream → XSSFWorkbook → getSheetAt(0)
  3. calculatedBatchSize: (totalRows/10), clamped [3500, 10000]
  4. For each row (skip row 0 = header):
     For each cell in row (switch by columnIndex):
       case 0: SubRoute
         → empty → genStatus=7, remark="No Value in Sub route"
         → length > 20 → genStatus=7, remark="Sub route length exceeds limit"
         → contains [@#$%] → genStatus=7, remark="Invalid Value in Route"
         → else → setSubRoute(trim.toUpperCase())
       case 1: Route
         → same validation rules as case 0
       case 2: BinNumber
         → empty → genStatus=7, remark="Null value Bin Number"
         → length > 8 → genStatus=7, remark="Bin Number length exceeds"
         → must match ^\d+$ → else genStatus=7
         → else → setBinNumber(trim)
       case 3: CardType
         → empty → skip
         → length > 7 → genStatus=7, remark="Card Type length exceeds"
         → contains [@#$%] → genStatus=7
         → else → setCardType(trim)
  5. After processing row:
     → valiateInputData checks length constraints
     → resolveCardType maps: "credit"→'C', "debit"→'D', else→'P'
     → mapToOmanNetData() → OmanNetBinEntity
     → removeDuplicate() → find existing bins, delete them
     → Batch save (saveAll + flush at batchSize threshold)
  6. After all rows:
     → updateFileUploadLog(uploadSerialNumber, 4, "Success")
     → updateProcess(uploadSerialNumber, jobNumber, totalCount, accTxnCount, 4)
     → moveFile to RECON_PROCESSED_{insShortName}
  7. On EOFException → log error
  8. On IOException:
     → updateFileUploadLog(uploadSerialNumber, 5, "Failed")
     → moveFile to RECON_REJECTED_{insShortName}
```

### 4.5 Mercury BIN Processing (`MercuryBinProcessing.processMercuryBin`)

```
Input: CSV file (Mercury BIN format, 9 columns)
Output: Records inserted into MERCURY_ISS_ACC_RANGE table

Process:
  1. filePath = RECON_IN_{insShortName} + fileName
  2. Count total lines → calculateBatchSize: totalLines/10, clamped [3500, 10000]
  3. Open CSVReader → skip header
  4. For each CSV row:
     a. Validate: row.length == 9, not all blank → isValidLine()
     b. Parse row into MercuryIssAccRangeEntity via readLine():
        - nextLine[0] → binRangeLow (Long)
        - nextLine[1] → binRangeHigh (Long)
        - nextLine[2] → cardType (Integer)
        - nextLine[3] → cardProductId (Integer)
        - nextLine[4] → cardVariant (Integer)
        - nextLine[5] → cardScheme (Integer)
        - nextLine[6] → currencyCode (Integer)
        - nextLine[7] → countryCode (Integer)
        - nextLine[8] → status (Character)
     c. Check for existing BIN range → findByBinRangeLowAndBinRangeHigh(low, high)
     d. Switch on status (Character):
        → 'A' (Add): add to saveList, increment accTxnCount
        → 'E'/'U' (Edit/Update): delete existing, add to saveList, increment accTxnCount
        → 'D' (Delete): add existing to deleteList
        → default: log warning
  5. Batch delete/save: deleteAll at batchCount threshold, saveAllAndFlush at batchCount threshold
  6. Flush remaining deleteList and saveList
  7. updateProcess(uploadSerialNumber, jobNumber, totalCount, totalAccTxnCount, 4) → status=4
  8. moveFile to RECON_PROCESSED_{insShortName}
  9. On error:
     → updateProcess → status=5
     → moveFile to RECON_REJECTED_{insShortName}
```

---

## 5. Database Schema (Key Tables & Fields)

### 5.1 PROCESSING_JOBS
| Column | Type | Purpose |
|--------|------|---------|
| PRJ_SER_NUMBER | INT (auto PK) | Job serial number |
| PRJ_LAST_UPDATED | TIMESTAMP | Last updated |
| PRJ_UPDATED_USER | INT | Updated user |
| PRJ_INS_CODE | INT | Institution code |
| PRJ_REF_NUMBER | INT | Reference number (always 1) |
| PRJ_PARENT_SER_NUMBER | INT | Parent job (always 0) |
| PRJ_PROCESS_NAME | VARCHAR | "BIN_FILE_PROCESSING" |
| PRJ_START_TIME | TIMESTAMP | Job start time |
| PRJ_END_TIME | TIMESTAMP | Job end time |
| PRJ_STATUS | INT | Status (1=started, 4=complete, 5=failed) |

### 5.2 FILE_UPLOAD_LOG (Upload Status Codes: 1=pending, 4=completed, 5=failed, 9=processing)
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | UPL_SER_NUMBER | INT (auto PK) | |
| lastUpdated | UPL_LAST_UPDATED | TIMESTAMP | |
| updatedUser | UPL_UPDATED_USER | INT | |
| institutionCode | UPL_INS_CODE | INT | |
| interfaceCode | UPL_INT_CODE | INT | BIN_INTERFACE_CODE=12 |
| jobNumber | UPL_PRJ_SER_NUMBER | INT | FK to PROCESSING_JOBS |
| fileName | UPL_FILE_NAME | VARCHAR(60) | |
| uploadDate | UPL_UPLOAD_DATE | DATE | |
| uploadStatus | UPL_UPLOAD_STATUS | INT | 1=pending, 4=completed, 5=failed, 9=processing |
| processingDate | UPL_PROC_DATE | DATE | |
| businessDate | UPL_BUSS_DATE | DATE | |
| fileId | UPL_FILE_ID | VARCHAR | Set to fileName |
| totalAcceptedTxnCount | UPL_TOT_ACCP_TXN_COUNT | INT | Count of valid records |
| totalTxnCount | UPL_TOT_TXN_COUNT | INT | Total records processed |
| formatCode | UPL_FOR_CODE | INT | Network-specific format code |
| remarks | UPL_REMARKS | VARCHAR | Error messages |

### 5.3 MC_ISS_ACC_RANGE
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | MAR_SER_NUMBER | INT (auto PK) | |
| lastUpdated | MAR_LAST_UPDATED | TIMESTAMP | |
| updatedUser | MAR_UPDATED_USER | INT | |
| jobSerialNumber | MAR_PRJ_SER_NUMBER | INT | FK to PROCESSING_JOBS |
| effectiveDate | MAR_EFFECTIVE_DATE | DATE | From Julian date conversion |
| activeCode | MAR_ACTIVE_CODE | CHAR | 'A' = active |
| issRangeLow | MAR_ISS_RANGE_LOW | VARCHAR | |
| gcmsProductId | MAR_GCMS_PROD_ID | VARCHAR | |
| issRangeHigh | MAR_ISS_RANGE_HIGH | VARCHAR | |
| cardProgId | MAR_CARD_PROG_ID | VARCHAR | Must match DMC\|MCC\|MSI\|PVL |
| priorityCode | MAR_PRIORITY_CODE | VARCHAR | |
| memberId | MAR_MEMBER_ID | VARCHAR | |
| prodTypeId | MAR_PROD_TYPE_ID | CHAR | |
| endPoint | MAR_END_POINT | VARCHAR | |
| countryAlphaCode | MAR_COUNTRY_ALPHA_CODE | VARCHAR | |
| countryCode | MAR_COUNTRY_CODE | VARCHAR | |
| region | MAR_REGION | CHAR | |
| productClass | MAR_PRODUCT_CLASS | VARCHAR | |
| txnRoutInd | MAR_TXN_ROUT_IND | CHAR | |
| fpReasignSwitch | MAR_FP_REASSIGN_SWITCH | CHAR | |
| prodReasignSwitch | MAR_PROD_REASSIGN_SWITCH | CHAR | |
| pwcbSwitch | MAR_PWCB_SWITCH | CHAR | |
| licProdId | MAR_LIC_PROD_ID | VARCHAR | |
| mapServInd | MAR_MAP_SERV_IND | CHAR | |
| accLevelInd | MAR_ACC_LEVEL_IND | CHAR | |
| accLevelActDate | MAR_ACC_LEVEL_ACT_DATE | DATE | |
| chBillCurr | MAR_CH_BILL_CURR | VARCHAR | |
| chBillCurrExp | MAR_CH_BILL_CURR_EXP | CHAR | |
| chipServInd | MAR_CHIP_SERV_IND | CHAR | |
| floorExpDate | MAR_FLOOR_EXP_DATE | VARCHAR | |
| coBrandSwitch | MAR_CO_BRAND_SWITCH | CHAR | |
| spendControlSwitch | MAR_SPEND_CONTROL_SWITCH | CHAR | |
| meCleansingService | MAR_ME_CLEANSING_SERVICE | VARCHAR | |
| meCleansingActiveDate | MAR_ME_CLEANSING_ACTIVE_DATE | DATE | |
| mePayPassInd | MAR_ME_PAYPASS_IND | CHAR | |
| rateTypeInd | MAR_RATE_TYPE_IND | CHAR | |
| psnRouteInd | MAR_PSN_ROUTE_IND | CHAR | |
| cbWithoutPurchase | MAR_CB_WITHOUT_PURCHASE | CHAR | |
| repowerReloadInd | MAR_REPOWER_RELOAD_IND | CHAR | |
| moneySendInd | MAR_MONEYSEND_IND | CHAR | |
| durbinRateInd | MAR_DURBIN_RATE_IND | CHAR | |
| bussDate | MAR_BUSS_DATE | DATE | |
| genStatus | MAR_GEN_STATUS | INT | 1=pending, 4=completed |

### 5.4 VISA_ISS_ACC_RANGE
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | VAR_SER_NUMBER | INT (auto PK) | |
| updatedDate | VAR_LAST_UPDATED | TIMESTAMP | |
| updatedUser | VAR_UPDATED_USER | INT | |
| jobSerialNumber | VAR_PRJ_SER_NUMBER | INT | |
| issAccountRangeLow | VAR_ISS_RANGE_LOW | VARCHAR | |
| issAccountRangeHigh | VAR_ISS_RANGE_HIGH | VARCHAR | |
| bin | VAR_BIN | VARCHAR(6) | |
| binLength | VAR_BIN_LENGTH | INT | |
| processingBin | VAR_PROC_BIN | VARCHAR(6) | |
| domain | VAR_DOMAIN | CHAR | |
| region | VAR_REGION | CHAR | |
| countryAlphaCode | VAR_COUNTRY_ALPHA_CODE | VARCHAR(2) | |
| cardProduct | VAR_CARD_PRODUCT | VARCHAR(2) | |
| crdrIndicator | VAR_DR_CR_CARD_IND | CHAR | |
| productSubTytpe | VAR_PROD_SUB_TYPE | VARCHAR(2) | |

### 5.5 JAYWAN_ISS_ACC_RANGE
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | JBS_SER_NUMBER | INT (auto PK) | |
| lastUpdated | JBS_LAST_UPDATED | TIMESTAMP | |
| updatedUser | JBS_UPDATED_USER | INT | |
| jobNumber | JBS_PRJ_SER_NUMBER | INT | |
| issuerBank | JBS_ISSUER_BANK | VARCHAR | |
| institutionId | JBS_INS_ID | INT | |
| binRangeLow | JBS_BIN_LOW_VALUE | Long | |
| binRangeHigh | JBS_BIN_HIGH_VALUE | Long | |
| panLength | JBS_PAN_LENGTH | INT | |
| binLength | JBS_BIN_LENGTH | INT | |
| productType | JBS_PRODUCT_TYPE | CHAR | |
| schemeCode | JBS_SCHEME_CODE | CHAR | |
| schemeProduct | JBS_SCHEME_PRODUCT | VARCHAR | |
| cardType | JBS_CARD_TYPE | INT | |
| service | JBS_SERVICE | INT | |
| currencyCode | JBS_CUR_CODE | INT | |
| isoNumCurrCode | JBS_ISO_NUM_CUR_CODE | INT | |
| actionTaken | JBS_ACTION_TAKEN | CHAR | |
| issAccCap | JBS_ISS_ACC_CAP | CHAR | |
| prodClssfy | JBS_PROD_CLSSFY | CHAR | |
| badgeInd | JBS_CO_BADGE_IND | VARCHAR | |

### 5.6 OMANNET_BIN_DATA
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | OBN_SER_NUMBER | INT (auto PK) | |
| lastUpdated | OBN_LAST_UPDATED | TIMESTAMP | |
| user | OBN_UPDATED_USER | INT | |
| jobNumber | OBN_PRJ_SER_NUMBER | INT | |
| genStatus | OBN_GEN_STATUS | INT | 4=valid, 7=error |
| route | OBN_ROUTE | VARCHAR(20) | UPPER case |
| subRoute | OBN_SUBROUTE | VARCHAR(20) | UPPER case |
| binNumber | OBN_BIN_NUMBER | VARCHAR(8) | Must be numeric |
| cardType | OBN_CARD_TYPE | CHAR | 'C'=Credit, 'D'=Debit, 'P'=Prepaid |
| remarks | OBN_REMARK | VARCHAR | Validation remarks |

### 5.7 MERCURY_ISS_ACC_RANGE
| Column (alias) | Column Name | Type | Purpose |
|---------------|-------------|------|---------|
| serialNumber | MIA_SER_NUMBER | INT (auto PK) | |
| lastUpdated | MIA_LAST_UPDATED | TIMESTAMP | |
| updatedUser | MIA_UPDATED_USER | INT | |
| jobNumber | MIA_PRJ_SER_NUMBER | INT | |
| binRangeLow | MIA_BIN_LOW_VALUE | Long | |
| binRangeHigh | MIA_BIN_HIGH_VALUE | Long | |
| cardType | MIA_CARD_TYPE | INT | |
| cardProductId | MIA_CARD_PRODUCT_ID | INT | |
| cardVariant | MIA_CARD_VARIANT | INT | |
| cardScheme | MIA_CARD_SCHEME | INT | |
| currencyCode | MIA_CUR_CODE | INT | |
| countryCode | MIA_CON_CODE | INT | |
| status | MIA_STATUS | CHAR | 'A'=Add, 'E'/'U'=Edit, 'D'=Delete |

### 5.8 BUSINESS_DATE
| Column | Type | Purpose |
|--------|------|---------|
| BDT_INS_CODE | INT (PK) | Institution code |
| BDT_BUSINESS_DATE | DATE | Current business date |
| BDT_LAST_BUSINESS_DATE | DATE | Previous business date |

### 5.9 Status Code Reference

**FILE_UPLOAD_LOG.UPL_UPLOAD_STATUS**:
| Code | Meaning |
|------|---------|
| 1 | Pending |
| 4 | Completed successfully |
| 5 | Failed |
| 9 | Processing |

**PROCESSING_JOBS.PRJ_STATUS**:
| Code | Meaning |
|------|---------|
| 1 | Started |
| 4 | Completed |
| 5 | Failed |

**MC_ISS_ACC_RANGE.MAR_GEN_STATUS**:
| Code | Meaning |
|------|---------|
| 1 | Active |

**OMANNET_BIN_DATA.OBN_GEN_STATUS**:
| Code | Meaning |
|------|---------|
| 4 | Valid |
| 7 | Error/rejected |

---

## 6. Environment Properties

| Property | Value | Description |
|----------|-------|-------------|
| `spring.application.name` | `bin-processing-service` | App name |
| `server.port` | `9029` | HTTP port |
| `spring.datasource.url` | Oracle JDBC URL | Database connection |
| `spring.datasource.username` | `NETWORK_SETTLEMENT_UAT` | DB user |
| `INS_CODE` | `1` | Institution code |
| `INS_SHORT_NAME` | `TEST` | Used in path: `RECON_IN_TEST` |
| `UPDATED_USER` | `2` | User ID for audit |
| `BIN_INTERFACE_CODE` | `12` | Interface code for BIN files |
| `MC_BIN_FORMAT_CODE` | `2` | Format code for Mastercard |
| `VISA_BIN_FORMAT_CODE` | `3` | Format code for Visa |
| `JAYWAN_BIN_FORMAT_CODE` | `61` | Format code for Jaywan |
| `OMANNET_BIN_FORMAT_CODE` | (env) | Format code for OmanNet |
| `MERCURY_BIN_FORMAT_CODE` | (env) | Format code for Mercury |
| `RECON_IN_TEST` | `/vp-switch/INPUT/` | Input file directory |
| `RECON_PROCESSED_TEST` | `/vp-switch/OUTPUT/` | Processed file directory |
| `RECON_REJECTED_TEST` | `/vp-switch/OUTPUT/` | Rejected file directory |
| `RECON_LOG` | `/Netset/Network_Settlement/logs/recon_log` | Log directory |
| `ACL_INTEGRATION_FLAG` | `1` | Enable ACL permission file sync at startup |
| `BIN_PERMISSION_FILE` | Path to JSON | Permission file for ACL |
| `ACL_URL` | `http://10.100.136.223:4008/entity-access-control` | ACL endpoint |
| `ACL_USER_APP_ID` | (JWT token) | Bearer token for ACL auth |
| `logging.level.root` | `INFO` | Log level |
| `spring.datasource.hikari.maximum-pool-size` | `20` | Max DB connections |

---

## 7. File Formats

### 7.1 Mastercard T067/T068 (Binary EBCDIC)

**Record delimiters**: STX (0x02), ETX (0x03), NUL (0x00)
**Field separators**: \u00b8 (¸), \u00a9 (©), \u00c2 (Â)
**End-of-file marker**: `PTRAILER RECORD IP0040T1`
**CSV columns per line**: 17 columns (comma-separated after EBCDIC decode)

| Field Index | Substrings | Description |
|-------------|-----------|-------------|
| 0 | [0:5] | Effective date (Julian date) |
| 1 | [7:8] | Active code |
| 2 | [11:30] | ISS range low |
| 3 | [30:33] | GCMS product ID |
| 4 | [33:52] | ISS range high |
| 5 | [52:55] | Card program ID (DMC/MCC/MSI/PVL) |
| 6 | [55:57] | Priority code |
| 7 | [57:68] | Member ID |
| 8 | [68:69] | Prod type ID |
| 9 | [69:76] | End point |
| 10 | [76:79] | Country alpha code |
| 11-34 | various | Additional Mastercard product/processing fields |

### 7.2 Visa ARDEF (Fixed-Width Text)

| Position | Field | Length |
|----------|-------|--------|
| [0:9] | ISS Account Range High | 9 |
| [9:11] | (padding) | 2 |
| [12:21] | ISS Account Range Low | 9 |
| [21:24] | (padding) | 3 |
| [24:30] | BIN | 6 |
| [31:33] | BIN Length | 2 |
| [35:41] | Processing BIN | 6 |
| [41:42] | Domain | 1 |
| [42:43] | Region | 1 |
| [43:45] | Country Alpha Code | 2 |
| [58:60] | Card Product | 2 |
| [69:70] | Credit/Debit indicator | 1 |
| [74:76] | Product Sub Type | 2 |

### 7.3 Jaywan BIN (CSV - 17 columns)

| Column Index | Field | Type |
|-------------|-------|------|
| 0 | Issuer Bank | String |
| 1 | Institution ID | Integer |
| 2 | BIN Range Low | Long |
| 3 | BIN Range High | Long |
| 4 | PAN Length | Integer |
| 5 | Product Type | Character |
| 6 | Scheme Code | Character |
| 7 | Scheme Product | String |
| 8 | Card Type | Integer |
| 9 | Service | Integer |
| 10 | Currency Code | Integer |
| 11 | ISO Num Currency Code | Integer |
| 12 | Action Taken | Character |
| 13 | BIN Length | Integer |
| 14 | Iss Acc Capability | Character |
| 15 | Product Classification | Character |
| 16 | Badge Indicator | String |

### 7.4 OmanNet BIN (Excel .xlsx)

| Column Index | Field | Validation |
|-------------|-------|------------|
| 0 | Sub Route | ≤20 chars, no @#$%, uppercase |
| 1 | Route | ≤20 chars, no @#$%, uppercase |
| 2 | BIN Number | Exactly 8 chars, numeric ^\d+$ |
| 3 | Card Type | ≤7 chars, no @#$% |

Card type mapping: `credit`→'C', `debit`→'D', else→'P'

### 7.5 Mercury BIN (CSV - 9 columns)

| Column Index | Field | Type |
|-------------|-------|------|
| 0 | BIN Range Low | Long |
| 1 | BIN Range High | Long |
| 2 | Card Type | Integer |
| 3 | Card Product ID | Integer |
| 4 | Card Variant | Integer |
| 5 | Card Scheme | Integer |
| 6 | Currency Code | Integer |
| 7 | Country Code | Integer |
| 8 | Status | Character (A/E/U/D) |

**Status Action Mapping**:
- `'A'` → Add (save new record)
- `'E'` or `'U'` → Edit (delete existing, then save new)
- `'D'` → Delete (remove existing record)

---

## 8. Background Processing Pattern

Java uses `new Thread().start()` for background BIN file processing:

```java
new BinFileProcessingThread(service, filename, network, jobSerialNumber, 
    userSerialNumber, uploadSerialNumber, insShortName).start();
```

Internally creates a new inner-class Thread that calls `processBinFile()` which dispatches to the appropriate network-specific processor.

In Go, this maps to a **goroutine**: `go service.processBinFile(...)`

---

## 9. File Lifecycle

```
[BIN File Uploaded to RECON_IN_TEST]
        │
        ▼
  Validation Check
        │ (passes)
        ▼
  INSERT processing_jobs (jobSerialNumber)
        │
        ▼
  INSERT file_upload_log (uploadSerialNumber, status=1)
        │
        ▼
  Spawn Background Thread
  → updateFileUploadLog(jobNum, status=9)
        │
        ▼
  Network-specific parsing:
  → MC: McT67Pro.processMCBin (EBCDIC)
  → VISA: VisaARDEF.processVisaBin (fixed-width text)
  → JAYWAN: JaywanBinProcessImpl (CSV)
  → OMANNET: OmanNetBinProcessing (Excel)
  → MERCURY: MercuryBinProcessing (CSV)
        │
        ├─→ On success: updateProcess(status=4), move to RECON_PROCESSED_TEST
        └─→ On failure: updateProcess(status=5), move to RECON_REJECTED_TEST

[Optional: DELETE /bin/v1/deleteBinFile]
        │
        ▼
  Only allowed if file_upload_log.uploadStatus = 5 (failed)
  → Delete network-specific BIN records by jobNumber
  → Delete FILE_UPLOAD_LOG record by fileName
```
