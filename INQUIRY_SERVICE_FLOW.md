# transaction-inquiry-service Flow Documentation

## Quick Overview

`transaction-inquiry-service` (port 9033) is a Spring Boot application that provides **inquiry endpoints** for querying transaction status, BIN file processing status, upload logs, batch totals, IRF calculations, and more. It is a **read-only query service** — it queries database views and tables but does not modify transaction data (except BIN file deletion).

---

## 1. Entry Points (HTTP API)

**Base URL**: `http://host:9033/inquiry/`

### Health Check
- `GET /` → returns `"OK"`

### Inquiry Endpoints

| Method | Endpoint | Service Delegate | Description |
|--------|----------|-----------------|-------------|
| `GET` | `/inquiry/v1/getBinFileStatus` | `InquiryService.getProcessBinStatus` | Get BIN file processing status by filename |
| `GET` | `/inquiry/v1/getFileStatus` | `InquiryService.getFileStatus` | Get file status by filename |
| `GET` | `/inquiry/v1/getTLFUploadLog` | `InquiryService.getTLFUploadLog` | Get TLF upload logs (paginated) |
| `GET` | `/inquiry/v1/getTxnStatus` | `InquiryService.getTxnStatus` | Get transaction status by RRN (paginated) |
| `GET` | `/inquiry/v1/getOutgoingStatus` | `OutgoingInquiryService.getOutgoingStatus` | Get current outgoing file status |
| `GET` | `/inquiry/v1/getOutgoingHist` | `OutgoingInquiryService.getOutgoingHist` | Get outgoing file history (paginated) |
| `GET` | `/inquiry/v1/getBinUploadLog` | `BinHistoryService.getBinHist` | Get BIN upload history (paginated) |
| `GET` | `/inquiry/v1/getTxnDetails` | `InquiryService.getTxnDetails` | Get detailed transaction info (paginated) |
| `GET` | `/inquiry/v1/getMPGSUploadLog` | `InquiryService.getMPGSUploadLog` | Get MPGS upload logs (paginated) |
| `GET` | `/inquiry/v1/getIncomingUploadLog` | `InquiryService.getIncomingUploadLog` | Get incoming upload logs (paginated) |
| `GET` | `/inquiry/v1/getCyberSourceUploadLog` | `InquiryService.getCyberSourceUploadLog` | Get CyberSource upload logs (paginated) |
| `GET` | `/inquiry/v1/getTC33InquiryAPI` | `InquiryService.getVisaTxnInchgDetails` | Visa TC33 interchange inquiry |
| `GET` | `/inquiry/v1/getTN70InquiryAPI` | `InquiryService.getMcTxnInchgDetails` | Mastercard TN70 interchange inquiry |
| `GET` | `/inquiry/v1/getReportDetails` | `InquiryService.getReportDetails` | Get report details |
| `GET` | `/inquiry/v1/getUAESwitchInterchange` | `InquiryService.getUAESwitchInterchange` | UAESwitch interchange inquiry |
| `GET` | `/inquiry/v1/getOmanNetInterchange` | `InquiryService.getOmanNetInterchange` | OmanNet interchange inquiry |
| `GET` | `/inquiry/v1/getSchemeFundDetails` | `InquiryService.getSchemeFundDetails` | Scheme fund details inquiry |
| `GET` | `/inquiry/v1/getFeeCollectionDetails` | `InquiryService.getFeeCollectionDetails` | Fee collection details inquiry |
| `GET` | `/inquiry/v1/getChargeBackDetails` | `InquiryService.getChargeBackDetails` | Chargeback details inquiry |
| `GET` | `/inquiry/v1/getCollectionOnlyOutgoingHist` | `OutgoingInquiryService.getOutgoingHist` ("C") | Collection-only outgoing history |
| `GET` | `/inquiry/v1/getBatchAPI` | `BatchInquiryService.getBatchTotals` | Batch transaction totals |
| `GET` | `/inquiry/v1/getTxnCallback` | `InquiryService.getTxnDetails` | Callback endpoint (auth required) |

### Request VOs

**RequestVo** (used by many endpoints):
```json
{
  "fileName": "IRFR11114082026.01",
  "bussDate": "14/08/2026",
  "network": "MASTERCARD",
  "rrn": "123456789012",
  "fromGeneratedDate": "14/08/2026 10:00:00",
  "toGeneratedDate": "14/08/2026 23:59:59",
  "status": "completed",
  "fromDate": "14/08/2026",
  "toDate": "14/08/2026"
}
```

**TxnDetailsRequestVo** (detailed inquiry):
```json
{
  "fromDate": "14/08/2026 00:00:00",
  "toDate": "14/08/2026 23:59:59",
  "merchantId": "M123456",
  "terminalId": "T7890",
  "channel": "MASTERCARD",
  "transactionType": "Sale",
  "rrn": "123456789012",
  "status": "SUCCESS",
  "mti": "0100",
  "domIntlFlag": "DOMESTIC",
  "bankId": "1234",
  "incomingStatus": "COMPLETED",
  "outgoingStatus": "COMPLETED",
  "cardAccIdCode": "ACC123",
  "cardAccTerminalId": "TERM456",
  "retrievalRefNo": "987654321098"
}
```

**InquiryRequestVo** (TC33/TN70 interchange):
```json
{
  "fromDate": "14/08/2026 00:00:00",
  "toDate": "14/08/2026 23:59:59",
  "network": "MASTERCARD"
}
```

**ReportRequestVo**:
```json
{
  "fromDate": "dd/MM/yyyy",
  "toDate": "dd/MM/yyyy",
  "reportName": "report_name",
  "reportType": "report_type"
}
```

**InterchangeRequestVo** and **SchemeFundDetailsRequestVo** similarly structured.

---

## 2. Request Flow Diagrams

### 2.1 getBinFileStatus (POST body, GET endpoint)

```
[Client] ⊂ GET /inquiry/v1/getBinFileStatus
       body: { fileName: "MC_BIN_FILE.txt" }
        │
        ▼
┌ BinProcessingController.getBinFileStatus()               │
│   → validateService.validateLength(fileName, 60)          │
│   → service.getProcessBinStatus(fileName)                 │
└───────────────────────────────────────────────────────────┘
        │
        ▼
┌ InquiryService.getProcessBinStatus(fileName)            │
│   1. Find FileUploadLogEntity by fileName                │
│      → fileUploadLogRepository.findByFileName(fileName)  │
│   2. If null → return empty map                          │
│   3. Map to response: fileName, status, totalAcceptedCount,│
│      totalTxnCount, remarks, businessDate, generatedDate  │
│   4. Map uploadStatus → string: 4=Completed, 5=Processing Failed,│
│      8=Deletion in Processing, 9=In processing           │
└───────────────────────────────────────────────────────────┘
```

### 2.2 getFileStatus (POST body, GET endpoint)

```
[Client] ⊂ GET /inquiry/v1/getFileStatus
       body: { fileName: "IRFR11114082026.01" }
        │
        ▼
┌ InquiryService.getFileStatus(fileName)                 │
│   1. Find OutgoingFileLogEntity (OUT_FILE_LOG) by fileName │
│      → outgoingFileLogRepository.findByFileName(fileName) │
│   2. If null → find by fileId:                           │
│      outgoingFileLogRepository.findByFileId(fileName)     │
│   3. Map to response: fileFormat, fileName, fileId,     │
│      businessDate, generatedDate, status, totalCount      │
└───────────────────────────────────────────────────────────┘
```

### 2.3 getTxnStatus (POST body with RRN, GET endpoint)

```
[Client] ⊂ GET /inquiry/v1/getTxnStatus?rrn=123456789012&page=1&size=20
       body: { rrn: "123456789012" }
        │
        ▼
┌ InquiryService.getTxnStatus(rrn, page, size)            │
│   1. validateRrn(rrn, 12) — must be 12-18 chars         │
│   2. Query ViewTxnInquiryDetails by RRN with pagination  │
│      → viewTxnInquiryDetailsRepo.findByRrn(rrn, pageable)│
│   3. Map to response: merchantId, terminalId, rrn,       │
│      irfAmount, txnAmount, responseCode, status, etc.    │
│   4. Add totalCount and totalPage to first element       │
└───────────────────────────────────────────────────────────┘
```

### 2.4 getTxnDetails (POST body with filters, GET endpoint)

```
[Client] ⊂ GET /inquiry/v1/getTxnDetails?page=1&size=20
       body: { fromDate: "...", toDate: "...", channel: "MASTERCARD", ... }
        │
        ▼
┌ InquiryService.getTxnDetails(requestVo, page, size)     │
│   1. validateTxnDetails: both fromDate/toDate required  │
│   2. Build JPA Specification from filter fields:         │
│      → merchantId, terminalId, channel, transactionType, │
│        rrn, status, mti, domIntlFlag, bankId,             │
│        incomingStatus, outgoingStatus, cardAccIdCode,    │
│        cardAccTerminalId, retrievalRefNo                  │
│   3. Query using Specification across ALL networks:      │
│      → MC: McAcqTxnWorkEntity                           │
│      → VISA: VisaAcqTxnWorkEntity                        │
│      → JAYWAN: JaywanAcqTxnWorkEntity                   │
│      → AMEX: AmexAcqTxnWorkEntity                        │
│   4. Map results via ViewTxnInquiryDetails               │
└───────────────────────────────────────────────────────────┘
```

### 2.5 getBatchAPI (auth required, GET endpoint)

```
[Client] ⊂ GET /inquiry/v1/getBatchAPI?batchNo=5&txnDate=20260814
           Header: Authorization: {batchAPIAuthKey}
        │
        ▼
┌ BatchInquiryService.getBatchTotals(startTime, endTime)   │
│   1. validateBatchParams(batchNo 1-24, txnDate YYYYMMDD) │
│   2. startTime = date.atTime(batchNo-1, 0)               │
│   3. endTime = startTime.plusHours(1)                    │
│   4. Query PosTransactionEntity by txnDateTimeBetween    │
│   5. Group by (trlType + "|" + network)                  │
│   6. For each group, compute BatchTxnSummaryVo:          │
│      → sales: procCode starts "00" + MTI "0110"          │
│      → refund: procCode "20" + MTI "0110"                │
│      → reversal: procCode "00" + MTI "0410"              │
│      → preauth: procCode "61" + MTI "0110"               │
│      → void: procCode "00" + MTI "0430"                  │
│      → etc. (10 different transaction type mappings)     │
│   7. Map trlType+network → batch_id format (e.g. mastercard_pos)│
└───────────────────────────────────────────────────────────┘
```

### 2.6 getTxnCallback (auth required via Authorization header)

```
[Client] ⊂ GET /inquiry/v1/getTxnCallback?rrn=123&terminalId=T1&mti=0100&channel=MC
           Header: Authorization: {authKey}
        │
        ▼
┌ InquiryController.getTxnCallback()                     │
│   → validateService.isValidAuth(authorization)          │
│   → Build TxnDetailsRequestVo from params               │
│   → service.getTxnDetails(vo, 1, 20)                    │
└───────────────────────────────────────────────────────────┘
```

### 2.7 Outgoing Inquiry Flow

```
┌ OutgoingInquiryService.getOutgoingStatus()             │
│   1. outgoingFileLogRepository.findByBusinessDate(bussDate)│
│   → OUT_FILE_LOG for today's business date              │
│   2. Return: fileFormat, fileName, businessDate,        │
│      generatedDate, status                              │
└───────────────────────────────────────────────────────────┘

┌ OutgoingInquiryService.getOutgoingHist(requestVo, page, size, filetype) │
│   1. Determine forCode based on network + filetype:     │
│      → filetype="C": MASTERCARD→GCO_FORMAT_CODE,       │
│        VISA→GOC_FORMAT_CODE                            │
│      → else: network-specific format codes             │
│   2. Build JPA Specification: forCode, bussDate,       │
│      genStatus, date range, fileName                    │
│   3. Paginated query of OUT_FILE_LOG                   │
│   4. Map: fileName, fileId, businessDate, generatedDate, │
│      status, totalCount, totalPage                      │
└───────────────────────────────────────────────────────────┘
```

### 2.8 Interchange Inquiry Flows

```
getUAESwitchInterchange(InquiryAPIRequestVo):
  → env: NETWORK="MASTERCARD" or "VISA" for UAESwitch
  → Query MC_ACQ_TXN_WORK / VISA_ACQ_TXN_WORK by date range + IRF fields
  → Return: irfAmount, irfAmountUSD, interchangeFeeAmountLocal, etc.

getOmanNetInterchange():
  → Query OMANNET incoming tables
  → Return: interchangeAmountLocal, interchangeAmountUSD, etc.

getTc33InquiryAPI (Visa TC33):
  → InquiryService.getVisaTxnInchgDetails()
  → Query VISA_ISS_ACC_RANGE + VISA_ACQ_TXN_WORK
  → Return: interchangeRateDesignator, interchangeAmount, etc.

getTn70InquiryAPI (MC TN70):
  → InquiryService.getMcTxnInchgDetails()
  → Query MC_ISS_ACC_RANGE + MC_ACQ_TXN_WORK
  → Return: interchange data
```

---

## 3. Database Schema (Key Tables & Fields)

### 3.1 Entities Used (queried from DB)

| Entity | Table | Purpose |
|--------|-------|---------|
| `FileUploadLogEntity` | `FILE_UPLOAD_LOG` | BIN file upload status logs |
| `OutgoingFileLogEntity` | `OUT_FILE_LOG` | Outgoing file generation logs |
| `BusinessDateEntity` | `BUSINESS_DATE` | Institution business date |
| `ViewTxnInquiryDetails` | `VIEW_TXN_INQUIRY_DETAILS` | Transaction inquiry view |
| `PosTransactionEntity` | `POS_TRANSACTION` | POS transaction records (for batch API) |
| `FileFormatsEntity` | `FILE_FORMATS` | File format code lookup |
| `McAcqTxnWorkEntity` | `MC_ACQ_TXN_WORK` | Mastercard staging (IRF data) |
| `VisaIssAcqRangeEntity` | `VISA_ISS_ACC_RANGE` | Visa BIN ranges |
| `OutgoingSchedulerEntity` | `OUTGOING_SCHEDULER` | Scheduled tasks |
| `IRFCallbackEntity` | `IRF_CALLBACK` | IRF callback records |
| `McNetworkDataEntity` | `MC_NETWORK_DATA` | MC network data (reports) |
| `VisaNetworkDataEntity` | `VISA_NETWORK_DATA` | Visa network data (reports) |
| `MCAcquirerExceptionsEntity` | `MC_ACQUIRER_EXCEPTIONS` | MC exceptions |
| `MCIpmFeesEntity` | `MC_IPM_FEES` | MC IPM fee data |
| `MCRejectionsEntity` | `MC_REJECTIONS` | MC rejection records |
| `ReportGenerationLogEntity` | `REPORT_GENERATION_LOG` | Report generation log |
| `JaywanIRFEntity` / `UaeSwitchIrfEntity` / `OmanNetIRFEntity` | various | Network-specific IRF data |
| `VisaDisputeFinanceDataEntity` | `VISA_DISPUTE_FINANCE_DATA` | Dispute finance data |
| `ViewFileFormatsEntity` | `VIEW_FILE_FORMATS` | Format codes view |
| `ViewInterfaceEntity` | `VIEW_INTERFACE` | Interface codes view |
| `ViewMcRejectionDetails` | `VIEW_MC_REJECTION_DETAILS` | MC rejection details |
| `ViewMCNetReconDetailsEntity` | `VIEW_MC_NET_RECON_DETAILS` | MC network reconciliation |
| `ViewVisaFundsTransferDetailsEntity` | `VIEW_VISA_FUNDS_TRANSFER_DETAILS` | Visa funds transfer |

### 3.2 Status Code Reference

**FILE_UPLOAD_LOG.UPL_UPLOAD_STATUS**:
| Code | String |
|------|--------|
| 1 | (pending) |
| 4 | Completed |
| 5 | Processing Failed |
| 8 | Deletion in Processing |
| 9 | In processing |

**Outgoing status mappings** (OutgoingInquiryService):
| String | Status Code |
|--------|-------------|
| "completed" | 6 |
| "pending" | 3 |
| "marked for outgoing" | 4 |
| "processing failed" / "failed" | 5 |

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

## 4. Validation Rules Summary

| Validator Method | Parameters | Rules |
|-----------------|------------|-------|
| `validateLength` | fileName, length | Not null/empty, ≤ max length |
| `validateRrn` | rrn, length | Not null/empty, ≤ max length (12 default) |
| `validateRequest` | bussDate, dateLength, network, validateNetwork | Date format `dd/MM/yyyy`, network must match pattern |
| `validateTxnDetails` | TxnDetailsRequestVo | Both fromDate and toDate required together |
| `validateBatchParams` | batchNo, txnDate, authorization | batchNo 1-24, txnDate YYYYMMDD, valid auth key |
| `validateReportDates` | ReportRequestVo | Both dates required together |
| `validateDates` | InquiryRequestVo | Both dates required together |
| `validateChargebackDates` | InquiryAPIRequestVo | Both dates required together |
| `validateCollectionOnlyRequest` | bussDate, network | Network matches `MASTERCARD\|VISA` only |
| `isValidAuth` | authorization | Must equal `authKey` from env |

### Network Validation Patterns

```
General: ^(i)(MASTERCARD|VISA|JAYWAN|AMEX|UAESWITCH|RUPAY SMS|VISA SMS|omannet_tps)$
Collection-only: ^(i)(MASTERCARD|VISA)$
InquiryAPI: ^(i)(MASTERCARD|VISA)$
BIN service: ^(i)(MASTERCARD|VISA|JAYWAN|OMANNET|MERCURY)$
```

### Transaction Type Validation Pattern (TxnDetailsRequestVo):
```
^(?i)(Sale|Cash on POS|Cash Adv|Cashback|Refund|Cash withdrawal|
Balance inquiry|Sale CB|Cash on POS CB|Cash Adv CB|Cashback CB|
Refund CB|Sale Reversal|Cash@POS Reversal|Cash Adv Reversal|
Cashback Reversal|Refund Reversal|Cash withdrawal Reversal|
Sale CB Reversal|Cash@POS CB Reversal|Cash Adv CB Reversal|
Cashback CB Reversal|Refund CB Reversal|Mini Statements|Fee|
Loan Sharing|Preauth|Preauth incremental|Void|Reversal|Preauth Complete)$
```

---

## 5. Batch Totals Calculation (BatchInquiryService)

```
Input: startTime (batchNo-1 hour), endTime (batchNo hour)
Output: Map<String, Map<String, BatchTxnSummaryVo>>

Group key = trlType + "|" + network
  → trlType = "POS" or "ECOM"
  → network = "MCI"/"VISA"/"JAYWAN"/"AMEX"/"OMANNET"/etc.

Transaction type mappings (by procCode prefix + MTI):
├─ sales:             procCode "00" + MTI "0110"
├─ refund:            procCode "20" + MTI "0110"
├─ refund_reversal:   procCode "20" + MTI "0410"
├─ preauth:           procCode "61" + MTI "0110"
├─ preauth_increment: procCode "71" + MTI "0110"
├─ void:              procCode "00" + MTI "0430"
├─ reversal:          procCode "00" + MTI "0410"
├─ refund_void:       procCode "20" + MTI "0430"
└─ preauth_complete:  procCode "00" + MTI "0130"

Network name mapping:
  → trlType "POS" → "{network}_pos" (MCI→"mastercard_pos")
  → trlType else → "{network}_ecom"
  → OmanNet is special: "omannet_tps_pos" or "omannet_tps_ecom"

batch_id = "BATCH-" + txnDate(YYYYMMDD) + "-" + batchNo
Each BatchTxnSummaryVo: successCount, failedCount, successAmount, failedAmount
```

---

## 6. Security / Authentication

| Endpoint | Auth Method |
|----------|-------------|
| `getTxnCallback` | `Authorization` header → must match env `authKey` |
| `getBatchAPI` | `Authorization` header → must match env `batchAPIAuthKey` |
| All other GET endpoints | **No authentication** (open access) |

### Unauthorized Response:
```json
{
  "timestamp": "2026-08-14T10:00:00",
  "status": 401,
  "error": "UNAUTHORIZED",
  "message": "The request was unauthorized."
}
```

---

## 7. Environment Properties

| Property | Value | Description |
|----------|-------|-------------|
| `spring.application.name` | `transaction-inquiry-service` | App name |
| `server.port` | `9033` | HTTP port |
| `spring.datasource.url` | Oracle JDBC | Database URL |
| `spring.datasource.username` | `NETWORK_SETTLEMENT_UAT` | DB user |
| `INS_CODE` | `1` | Institution code |
| `INS_SHORT_NAME` | `TEST` | Used in path properties |
| `UPDATED_USER` | `2` | User ID for audit |
| `BIN_INTERFACE_CODE` | `12` | BIN interface code |
| `MC_BIN_FORMAT_CODE` | `112` | MC BIN format code |
| `VISA_BIN_FORMAT_CODE` | `113` | Visa BIN format code |
| `JAYWAN_BIN_FORMAT_CODE` | `199` (overridden to `119` at line 53) | Jaywan BIN format |
| `OMANNET_BIN_FORMAT_CODE` | `126` | OmanNet BIN format |
| `RUPAY_BIN_FORMAT_CODE` | `114` | Rupay BIN format |
| `MASTERCARD_SYSTEM_FORMAT_CODE` | `115` | MC outgoing format |
| `VISA_SYSTEM_FORMAT_CODE` | `117` | Visa outgoing format |
| `JAYWAN_SYSTEM_FORMAT_CODE` | `120` | Jaywan outgoing format |
| `AMEX_SYSTEM_FORMAT_CODE` | `121` | Amex outgoing format |
| `GCO_FORMAT_CODE` | `131` | Collection MC outgoing format |
| `GOC_FORMAT_CODE` | `133` | Collection VISA outgoing format |
| `authKey` | `Vsjd83IXBpWdZ1EB2tGq9a1dSEWKXvQO` | Callback API auth key |
| `batchAPIAuthKey` | `YPidd5SRsiNlPCO8EdOMz7YNIISyQBUW` | Batch API auth key |
| `RECON_REPORT_TEST` | `Netset/Network_Settlement/REPORTS/` | Report output dir |
| `logging.level.root` | `DEBUG` | Log level |
| `spring.jpa.hibernate.ddl-auto` | `none` or `validate` | Hibernate DDL mode |

---

## 8. Response VO Types

| Response VO | Endpoint | Key Fields |
|-------------|----------|------------|
| `Map<String, String>` | getBinFileStatus, getFileStatus, getOutgoingStatus | fileName, status, businessDate, generatedDate |
| `List<Map<String, String>>` | getTLFUploadLog, getTxnStatus, getOutgoingHist, getBinUploadLog, getMPGSUploadLog, getIncomingUploadLog, getCyberSourceUploadLog, getTc33InquiryAPI, getTn70InquiryAPI, getReportDetails, getUAESwitchInterchange, getSchemeFundDetails, getFeeCollectionDetails, getChargeBackDetails, getCollectionOnlyOutgoingHist | Pagination + field maps |
| `List<TxnDetailsResponseVo>` | getTxnDetails, getTxnCallback | merchantId, terminalId, rrn, irfAmount, txnAmount, cardNumber, txnStatus, network, etc. |
| `List<BatchDetailsResponseVo>` | getBatchAPI | batch_id, batch_totals (nested Map) |
| `List<InterchangeResponseVo>` | getUAESwitchInterchange | interchangeFeeAmount, interchangeFeeSign, feeDescriptor, etc. |
| `List<OmanNetinterchangeResponseVo>` | getOmanNetInterchange | OmanNet-specific interchange fields |
| `BinResponseVO` | (from bin-processing-service) | message only |

### TxnDetailsResponseVo Key Fields (40+ fields):
```
merchantId, terminalId, rrn, irdCode, fixed, percentage, irfMin, irfMax,
irfAmount, txnAmount, domIntlFlag, cardType, cardNumber, txnType,
description, network, txnDate, txnStatus, outgoingStatus, incomingStatus,
responseCode, mti, irfCallbackStatus, refCode, totalCount, totalPage,
bankId, revIndicator, irfAmountUSD, originalRRN, localDate, transactionId,
traceId, interchangeFeeAmountLocal, interchangeFeeSign, feeDescriptor,
interchangeMatched, interchangeDifferenceAmount, transactionAmountLocal,
reconciliationAmountUSD, interchangeAmountLocal, interchangeAmountUSD,
interchangeRateDesignator, processingCode, txnAmountLocal, uaesIrf,
uaesPF1, uaesPF2, scheme, fileID, businessCycle, approvalCode,
dccIndicator, dccAmount, dccCurrency, exchangeRate
```

---

## 9. Key Design Notes for Go Port

1. **Read-only service** — no transaction modification (unlike incoming/outgoing services)
2. **No background threads** — all endpoints respond directly
3. **Pagination**: default page=1, size=20; Spring Data PageRequest with (page-1, size)
4. **Specifications**: Uses JPA Criteria API for dynamic queries (Specification<T>)
5. **Two auth models**: env-based `authKey` for callback, `batchAPIAuthKey` for batch API
6. **File status**: Upload status codes differ from splitProcessAndStaging (4=completed, 5=failed, 8=deletion, 9=processing)
7. **Network mapping**: "MCI" → "mastercard" in batch totals (case-insensitive)
8. **OmanNet special case**: trlType "POS" → "omannet_tps_pos"
