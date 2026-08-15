# Jaywan XML Generation Documentation

## Overview

This document describes the UAE Switch Clearing XML file generation process based on the **UAE Switch Clearing Specification Document V1.3** (Al Etihad Payments / Jaywan).

## File Structure

### File Naming Convention (Section 2.5.3)

```
File Type (2) + Clearing Cycle (1) + Participant ID (9) + Julian Date (5) + Sequence (2)
```

| Component | Format | Example |
|-----------|--------|---------|
| File Type | `00` = Acquirer member generated | `00` |
| Clearing Cycle | `0` = Default | `0` |
| Participant ID | 9 digits, zero-padded | `784666661` |
| Julian Date | YY + DDD (day of year) | `26208` (2026, day 208) |
| Sequence | 2 digits, auto-increment | `00` |

**Example:** `0007846666612620808`

### XML Structure

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><File><Hdr>...</Hdr><TxnBlock><Txn>...</Txn><Txn>...</Txn></TxnBlock><Trl>...</Trl></File>
```

- **Line 1:** XML declaration + `<File><Hdr>...</Hdr><TxnBlock>`
- **Lines 2-N:** `<Txn>...</Txn>` (one per transaction)
- **Last Line:** `</TxnBlock><Trl>...</Trl></File>`

---

## Header Record (MTI 1644, FunCd 670)

| Field | Tag | Value | Notes |
|-------|-----|-------|-------|
| Message Type Identifier | nMTI | `1644` | Administrative/Notification |
| Function Code | nFunCd | `670` | Header Message |
| Record Number | nRecNum | `00000001` | Always 1 |
| Date Time File Generation | nDtTmFlGen | `MMDDhhmmss` | UTC timestamp |
| Member Institution Code | nMemInstCd | 9 digits | Participant ID |
| Unique File Name | nUnFlNm | 19 chars | Generated filename |
| File Category | nFlCatg | `T` or `P` | Test/Production |
| Version Number | nVerNum | `01.01` | Current version |

---

## Transaction Record (MTI 1240, FunCd 200)

| Field | Tag | Source | Format | Notes |
|-------|-----|--------|--------|-------|
| Message Type Identifier | nMTI | Fixed | `1240` | Presentment |
| Function Code | nFunCd | Fixed | `200` | Presentment with Auth |
| Record Number | nRecNum | Sequential | N8 | Starts at 00000002 |
| Date Time Local Transaction | nDtTmLcTxn | `local_date + local_time` | MMDDhhmmss | |
| Primary Account Number | nPAN | `pan` | N..19 | Full PAN required |
| Retrieval Reference Number | nRRN | `rrn` | N12 | Unique per batch |
| Acquirer Institution Code | nAcqInstCd | `acquier_id` | N9 | |
| Approval Code | nApprvlCd | `auth_code` | AN6 | |
| Card Acceptor Terminal ID | nCrdAcptTrmId | `terminal_id` | AN8 | |
| Amount Transaction | nAmtTxn | `amount` | N12 | In fils (smallest unit) |
| Currency Code Transaction | nCcyCdTxn | `currency_code` | N3 | 784 = AED |
| Transaction Originator Inst Code | nTxnOrgInstCd | `acquier_id` | N9 | Same as nAcqInstCd |
| E-Commerce Indicator | nECIInd | `de60_json.ecomIndicator` | N2 | **Only for ECOM** |
| Card Acceptor ID Code | nCrdAcpIDCd | `merchant_id` | AN15 | |
| Card Acceptor Name | nCrdAcpNm | `card_acceptor_name` | AN25 | |
| Card Acceptor City | nCrdAcpCity | `card_acceptor_city` | AN13 | |
| Card Acceptor State Code | nCrdAcpStNm | `card_acceptor_state_code` | AN3 | Must be valid code |
| Card Acceptor Country Code | nCrdAcpCtryCd | `card_acceptor_country_code` | N3 | |
| Card Acceptor Business Code | nCrdAcpBussCd | `mcc` | N4 | Merchant Category Code |
| Processing Code | nProcCd | `processing_code` | N6 | |
| POS Entry Mode | nPosEntMode | `pos_entry_mode` | N3 | |
| POS Condition Code | nPosCondCd | `pos_condition_code` | N2 | |
| Action Code | nActnCd | `network_response_code` | N3 | |
| Transaction ID | nTxnId | `de62_json.txnId` | N15 | Required for presentment |
| Full/Partial Indicator | nFulParInd | Fixed | `F` | F=Full |
| POS Cardholder Presence Ind | nPosCPInd | `de61_json.chPresent` | N1 | |
| POS Transaction Status Ind | nPosTxnStat | Fixed/Derived | `0` or `4` | 0=Normal (POS), 4=Preauth |

---

## Trailer Record (MTI 1644, FunCd 671)

| Field | Tag | Value | Notes |
|-------|-----|-------|-------|
| Message Type Identifier | nMTI | `1644` | Administrative/Notification |
| Function Code | nFunCd | `671` | Trailer Message |
| Record Number | nRecNum | txn_count + 2 | |
| Unique File Name | nUnFlNm | 19 chars | Same as header |
| Transaction Count | nTxnCnt | N8 | Total transactions |
| Run Total Amount | nRnTtlAmt | N15 | Sum of all amounts in fils |

---

## Valid State Codes

| Code | Description |
|------|-------------|
| DU | Dubai |
| SH | Sharjah |
| AJ | Ajman |
| FU | Fujairah |
| RK | Ras Al Khaimah |
| UQ | Umm Al Quwain |
| AZ | Al Ain |

**Note:** `AE` is only valid as a default when state code is blank. Invalid codes are corrected to `DU`.

---

## Valid Processing Codes

| Code | Description |
|------|-------------|
| 000000 | Purchase |
| 200000 | Refund |
| 010000 | Cash Withdrawal |

---

## Valid MTIs for Clearing

| MTI | Description |
|-----|-------------|
| 0100 | Authorization Request |
| 0110 | Authorization Response |
| 0130 | Financial Transaction Request |

---

## Validation Rules

1. **Duplicate RRN Detection** - Transactions with duplicate RRNs are rejected
2. **Required Fields** - PAN, amount, terminal_id, merchant_id, auth_code must be present
3. **MTI Validation** - Must be 0100, 0110, or 0130
4. **Processing Code Validation** - Must be 000000, 200000, or 010000
5. **State Code Correction** - Invalid codes are replaced with default (DU)

---

## Field Derivations

### nPosCPInd (POS Cardholder Presence Indicator)

Derived from `de61_json.chPresent` in payload. Default: `5` if not provided.

### nPosTxnStat (POS Transaction Status Indicator)

Set to `0` (Normal) for standard POS transactions. Set to `4` for preauthorization transactions.

### nECIInd (E-Commerce Indicator)

Only included when `channel = "ECOM"`. Derived from `de60_json.ecomIndicator`. Default: `07`.

### nTxnId (Transaction ID)

Derived from `de62_json.txnId` in payload. Required for online presentment.

### nRnTtlAmt (Run Total Amount)

Sum of all transaction amounts in fils, formatted as N15 (15-digit zero-padded).

---

## Payload JSON Structure

```json
{
  "payload": {
    "bank_id": "CPBA",
    "scheme": "JAYWAN",
    "ref_id": "UUID",
    "switch_mti": "0110",
    "pan": "6690109700100010",
    "processing_code": "000000",
    "amount": "000000020200",
    "local_time": "150242",
    "local_date": "0714",
    "mcc": "5411",
    "acquier_id": "784666661",
    "pos_entry_mode": "071",
    "rrn": "619511887502",
    "auth_code": "149984",
    "network_response_code": "00",
    "terminal_id": "T0000212",
    "merchant_id": "M00000000000006",
    "card_acceptor_name": "hbk store",
    "card_acceptor_city": "dubai",
    "card_acceptor_state_code": "AE",
    "card_acceptor_country_code": "AE",
    "currency_code": "784",
    "de61_json": {
      "chPresent": "0"
    },
    "de62_json": {
      "txnId": "071411024288750"
    },
    "channel": "POS"
  }
}
```

---

## Script Usage

```bash
python generate_jaywan_xml.py
```

### Configuration (in script)

```python
CONFIG = {
    "output_dir": r"D:\UAE Switch\Jaywan\outgoing",
    "payload_file": r"D:\UAE Switch\Jaywan\outgoing\payload.json",
    "file_type": "00",           # 00 = Acquirer member generated
    "clearing_cycle": "0",       # 0 = Default
    "file_category": "T",        # T = Test, P = Production
    "version_number": "01.01",   # Current version
    "default_state_code": "DU",  # DU = Dubai (fallback if invalid)
}
```

---

## Reference Files

| File | Description |
|------|-------------|
| `0007846014382605702` | Reference file with 59 transactions (1,026,800 AED total) |
| `0007846666612620808` | Latest generated test file (5 transactions, 2,499.00 AED) |

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2026-07-27 | Initial implementation |
| 1.1 | 2026-07-27 | Added validation, filtering, nPosTxnStat=0, nPosCPInd from chPresent |
