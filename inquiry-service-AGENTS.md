# transaction-inquiry-service — Go Port Specification

## Overview

Spring Boot application (port 9033) — **read-only inquiry/query service** that provides HTTP endpoints for querying transaction status, BIN file processing status, upload logs, batch totals, IRF/interchange data, and report generation. No background processing or file writing.

## HTTP API Endpoints (`/inquiry/`)

All endpoints are GET requests with optional `@RequestParam` for pagination and `@RequestBody` for filters.

| Method | Endpoint | Service | Auth |
|--------|----------|---------|------|
| GET | `/inquiry/v1/getBinFileStatus` | InquiryService.getProcessBinStatus | None |
| GET | `/inquiry/v1/getFileStatus` | InquiryService.getFileStatus | None |
| GET | `/inquiry/v1/getTLFUploadLog` | InquiryService.getTLFUploadLog | None |
| GET | `/inquiry/v1/getTxnStatus` | InquiryService.getTxnStatus | None |
| GET | `/inquiry/v1/getOutgoingStatus` | OutgoingInquiryService.getOutgoingStatus | None |
| GET | `/inquiry/v1/getOutgoingHist` | OutgoingInquiryService.getOutgoingHist | None |
| GET | `/inquiry/v1/getBinUploadLog` | BinHistoryService.getBinHist | None |
| GET | `/inquiry/v1/getTxnDetails` | InquiryService.getTxnDetails | None |
| GET | `/inquiry/v1/getMPGSUploadLog` | InquiryService.getMPGSUploadLog | None |
| GET | `/inquiry/v1/getIncomingUploadLog` | InquiryService.getIncomingUploadLog | None |
| GET | `/inquiry/v1/getCyberSourceUploadLog` | InquiryService.getCyberSourceUploadLog | None |
| GET | `/inquiry/v1/getTC33InquiryAPI` | InquiryService.getVisaTxnInchgDetails | None |
| GET | `/inquiry/v1/getTN70InquiryAPI` | InquiryService.getMcTxnInchgDetails | None |
| GET | `/inquiry/v1/getReportDetails` | InquiryService.getReportDetails | None |
| GET | `/inquiry/v1/getUAESwitchInterchange` | InquiryService.getUAESwitchInterchange | None |
| GET | `/inquiry/v1/getOmanNetInterchange` | InquiryService.getOmanNetInterchange | None |
| GET | `/inquiry/v1/getSchemeFundDetails` | InquiryService.getSchemeFundDetails | None |
| GET | `/inquiry/v1/getFeeCollectionDetails` | InquiryService.getFeeCollectionDetails | None |
| GET | `/inquiry/v1/getChargeBackDetails` | InquiryService.getChargeBackDetails | None |
| GET | `/inquiry/v1/getCollectionOnlyOutgoingHist` | OutgoingInquiryService (filetype="C") | None |
| GET | `/inquiry/v1/getBatchAPI` | BatchInquiryService.getBatchTotals | `batchAPIAuthKey` header |
| GET | `/inquiry/v1/getTxnCallback` | InquiryService.getTxnDetails | `authKey` header |
| GET | `/` | StatusCheckController | None |

## Common Request Parameters

All paginated endpoints accept:
- `page` (int, default 1) — `page > 0 ? page : 1`
- `size` (int, default 20) — `size > 0 ? size : 20`

Spring Data pagination: `PageRequest.of(page-1, size)`

## Request VOs

**RequestVo** (most endpoints):
- `fileName` (String)
- `bussDate` (String, dd/MM/yyyy)
- `network` (String)
- `rrn` (String)
- `fromGeneratedDate`, `toGeneratedDate` (String, dd/MM/yyyy HH:mm:ss)
- `status` (String)
- `fromDate`, `toDate` (String)

**TxnDetailsRequestVo** (getTxnDetails):
- `fromDate`, `toDate` (dd/MM/yyyy HH:mm:ss, length 19)
- `merchantId`, `terminalId` (1-60 chars)
- `channel` — pattern: MASTERCARD\|VISA\|JAYWAN\|AMEX\|UAESWITCH\|VISA SMS\|RUPAY SMS\|omannet_tps
- `transactionType` — 30+ valid types (Sale, Refund, Void, Reversal, Preauth, etc.)
- `rrn` (12-18 chars)
- `status` — SUCCESS\|FAILED
- `mti` (1-4 chars)
- `domIntlFlag` — DOMESTIC\|INTERNATIONAL
- `bankId` (1-4 chars)
- `incomingStatus` — PENDING\|COMPLETED\|REJECTED\|CB RECEIVED\|RR RECEIVED
- `outgoingStatus` — COMPLETED\|MARKED FOR OUTGOING\|PENDING
- `cardAccIdCode`, `cardAccTerminalId` (1-60 chars)
- `retrievalRefNo` (12-18 chars)

**InquiryRequestVo** (getTC33/getTN70):
- `fromDate`, `toDate` (dd/MM/yyyy HH:mm:ss)
- `network` — MASTERCARD

## Service Layer

### InquiryService (main service)
Handles 12 endpoints: getProcessBinStatus, getFileStatus, getTLFUploadLog, getTxnStatus, getTxnDetails, getMPGSUploadLog, getIncomingUploadLog, getCyberSourceUploadLog, getVisaTxnInchgDetails, getMcTxnInchgDetails, getReportDetails, getUAESwitchInterchange, getSchemeFundDetails, getFeeCollectionDetails, getChargeBackDetails

Uses JPA **Specification** pattern for dynamic queries across multiple entity types.

### OutgoingInquiryService
Handles: getOutgoingStatus, getOutgoingHist
- forCode mapping: MASTERCARD→115, VISA→117, JAYWAN→120, AMEX→121, RUPAY→114
- Collection-only: MASTERCARD→GCO_FORMAT_CODE(131), VISA→GOC_FORMAT_CODE(133)
- Gen status mapping: completed→6, pending→3, marked for outgoing→4, failed→5

### BinHistoryService
Handles: getBinHist
- Maps network → format code via FILE_FORMATS table
- Upload status mapping: 4→Completed, 5→Processing Failed, 8→Deletion in Processing, 9→In processing

### BatchInquiryService
Handles: getBatchTotals
- Input: startTime (batchNo-1), endTime (batchNo)
- Groups PosTransactionEntity by `trlType + "|" + network`
- 10 transaction type mappings (sales, refund, reversal, preauth, void, etc.)
- Network name mapping: MCI→mastercard, trlType POS→_pos, else→_ecom
- Special case: OmanNet → "omannet_tps_pos" or "omannet_tps_ecom"

## Environment Properties

```properties
spring.application.name=transaction-inquiry-service
server.port=9033
spring.datasource.url=jdbc:oracle:thin:@//switch-uat.c3guuusy8mm5.me-central-1.rds.amazonaws.com:1521/ORCL
spring.datasource.username=NETWORK_SETTLEMENT_UAT
spring.jpa.hibernate.ddl-auto=none  # or validate

INS_CODE=1
INS_SHORT_NAME=TEST
UPDATED_USER=2
BIN_INTERFACE_CODE=12
MC_BIN_FORMAT_CODE=112
VISA_BIN_FORMAT_CODE=113
VISA_BIN_FORMAT_CODE=199
OMANNET_BIN_FORMAT_CODE=126
RUPAY_BIN_FORMAT_CODE=114
MASTERCARD_SYSTEM_FORMAT_CODE=115
VISA_SYSTEM_FORMAT_CODE=117
JAYWAN_SYSTEM_FORMAT_CODE=120
AMEX_SYSTEM_FORMAT_CODE=121
GCO_FORMAT_CODE=131
GOC_FORMAT_CODE=133
MC_INCOMING_FORMAT_CODE=118
RUPAY_INCOMING_FORMAT_CODE=611
MC_T112_FORMAT_CODE=551
SWITCH_TLF_SYSTEM_CODE=111
MPGS_SYSTEM_FORMAT_CODE=116
UAESWITCH_SYSTEM_FORMAT_CODE=123
CYBERSOURCE_SYSTEM_FORMAT_CODE=125
JAYWAN_INCOMING_FORMAT_CODE=129
OMANNET_CBO_INCOMING_FORMAT_CODE=127
OMANNET_TPS_INCOMING_FORMAT_CODE=128
OMANNET_BIN_FORMAT_CODE=126
JAYWAN_BIN_FORMAT_CODE=119
GCO_SYSTEM_CODE=131
JAYWAN_SYSTEM_CODE=120

authKey=Vsjd83IXBpWdZ1EB2tGq9a1dSEWKXvQO
batchAPIAuthKey=YPidd5SRsiNlPCO8EdOMz7YNIISyQBUW
RECON_REPORT_TEST=Netset/Network_Settlement/REPORTS/

spring.datasource.hikari.maximum-pool-size=20
logging.level.root=DEBUG
```

## Key Design Decisions for Go Port

1. **Read-only service** — only SELECT queries, no transaction modification
2. **No background threads** — all endpoints synchronous
3. **JPA Specifications** → translate to dynamic WHERE clause builder in Go
4. **Pagination**: Spring `PageRequest.of(page-1, size)` → Go `(page-1)*size` offset, `size` limit
5. **Auth**: Two different header-based auth keys for specific endpoints
6. **Dynamic queries**: Use query builder for optional filter fields
7. **Response format**: `Map<String, String>` for simple queries, typed VO for detailed responses
8. **Network normalization**: "MCI" → lowercase "mastercard" in batch totals
9. **Date validation**: dd/MM/yyyy for bussDate, dd/MM/yyyy HH:mm:ss for timestamps
10. **Only bin-processing-service has a DELETE endpoint** (file deletion of failed records)

## Database Tables Queried

Primary tables/views:
- `FILE_UPLOAD_LOG` — BIN file upload status
- `OUT_FILE_LOG` — Outgoing file generation status
- `BUSINESS_DATE` — Current business date
- `VIEW_TXN_INQUIRY_DETAILS` — Transaction inquiry view (MC, VISA, JAYWAN, AMEX, UAESWITCH, POS, ECOM)
- `POS_TRANSACTION` — POS transaction records (batch API)
- `FILE_FORMATS` — Format code lookup
- `MC_ISS_ACC_RANGE`, `VISA_ISS_ACC_RANGE`, `JAYWAN_ISS_ACC_RANGE` — BIN range tables
- `OUTGOING_SCHEDULER` — Scheduled tasks
- `IRF_CALLBACK` — IRF callback records
- `MC_NETWORK_DATA`, `VISA_NETWORK_DATA` — Network reconciliation data
- `MC_REJECTIONS`, `MC_ACQUIRER_EXCEPTIONS`, `MC_IPM_FEES` — MC specific data
- `REPORT_GENERATION_LOG` — Report generation log
- All network-specific IRF entities (JaywanIRF, UaeSwitchIrf, OmanNetIRF)
- `VISA_DISPUTE_FINANCE_DATA` — Dispute finance data

## Status Code Reference

### FILE_UPLOAD_LOG.UPL_UPLOAD_STATUS
| Code | String |
|------|--------|
| 1 | (pending/inserted) |
| 4 | Completed |
| 5 | Processing Failed |
| 8 | Deletion in Processing |
| 9 | In processing |

### Outgoing File Status (mapped to strings)
| DB Code | String |
|---------|--------|
| 6 | completed |
| 3 | pending |
| 4 | marked for outgoing |
| 5 | failed |

### OMANNET_BIN_DATA.OBN_GEN_STATUS
| Code | Meaning |
|------|---------|
| 4 | Valid |
| 7 | Error/rejected |

### MC_ISS_ACC_RANGE.MAR_GEN_STATUS
| Code | Meaning |
|------|---------|
| 1 | Active |

## Validation Patterns

```
Date: ^(0[1-9]|[12][0-9]|3[01])/([0][1-9]|1[0-2])/(?!0000)[0-9]{4}$
DateTime: ^\d{2}/\d{2}/\d{4} \d{2}:\d{2}:\d{2}$  (exactly 19 chars)
Batch date: \d{8} (YYYYMMDD)
Network (general): (?i)^(MASTERCARD|VISA|JAYWAN|AMEX|UAESWITCH|RUPAY SMS|VISA SMS|omannet_tps)$
Network (collection-only): (?i)^(MASTERCARD|VISA)$
RRN: 12-18 characters
```
