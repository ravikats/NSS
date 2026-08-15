# IRF Service — API Request / Response Reference

Base URL: `http://<host>:8085` (UAT box `10.100.139.30`, port `8085`).
**All endpoints require `?sec=<irf.service.sec>`** (UAT default `change-me`;
UAT deployed secret `UAT-IRF-7f3a9c2b`). Wrong/missing `sec` → `401`.

---

## 1. POST /irf/v1/calculate?sec=…

Compute IRF for one transaction.

### Request body — `IrfCalculateRequest`

```json
{
  "network": "VISA",
  "insCode": 7085,
  "cardNumber": "4111111111111111",
  "txnData": {
    "serialNumber": 12345,
    "mcc": "5499",
    "txnAmount": 100.0,
    "setlAmount": 100.0,
    "posEntryMode": "012",
    "approvalCode": "ABC123",
    "responseCode": "00",
    "rrn": "000123456789",
    "acqInstConCode": "784",
    "txnCurCode": "840",
    "setlCurCode": "840",
    "txnDateTime": "2026-08-13T12:30:00"
  }
}
```

Notes:
- `txnData.network` is optional — if null the server copies `request.network`
  into it (IrfCalculationController.java:36).
- `txnData.serialNumber` is **required on the MC path** (audit row key); omitting
  it → 500 `IdentifierGenerationException`.
- `insCode` must be ≤ 99 on UAT (`IRF_CALLBACK.ICB_INS_CODE` is `NUMBER(2)`).
- `Character` fields (`trlCapabilities`, `oprtEnvironment`, etc.) reject
  multi-char strings → HTTP 400.

### Response 200 — `IrfCalculateResponse` + `IRFResultVo`

```json
{
  "calculated": true,
  "result": {
    "irdSerNumber": 1987,
    "morSerNumber": null,
    "irdCode": "F",
    "irfPercentage": 1.6,
    "irfFixed": 0.0,
    "irfAmount": 1.6,
    "domIntlFlag": "I",
    "cardType": "D",
    "gcmsProductID": null,
    "irfDesc": "NONPREMIUM ALT",
    "irfCountry": "US",
    "irfMinAmount": null,
    "irfMaxAmount": null,
    "irfAmountUSD": null
  }
}
```

(Real UAT VISA result, handover §7.2.) No match → fallback result per network
(`calculated:true` with zeros / empty VO). No registered calculator → 501.

---

## 2. POST /irf/v1/callback?sec=…

Enqueue a callback row (persists `IRF_CALLBACK` as PENDING).

### Request body — `IrfResultData`

```json
{
  "cpMid": "1234567890",
  "uniqueId": "TXN-987654",
  "irdCode": "85",
  "fixed": 0.0,
  "percentage": 2.5,
  "irfAmount": 2.5,
  "txnAmount": 100.0,
  "rrn": "000123456789",
  "mti": "0100",
  "domIntlFlag": "I",
  "isCredit": false,
  "cardClassification": "NONPREMIUM ALT",
  "insCode": 1,
  "refSerNumber": 12345
}
```

### Response 200

The new `ICB_SER_NUMBER` as a plain JSON number:

```json
152966
```

(Real Oracle serials seen on UAT: `152966/152967/152968`.)

---

## 3. POST /irf/v1/callback/flush?sec=&insCode=&jobNumber=

Send a job's pending rows to the scheme webhook (`irf.callback.target.url`).
`insCode`/`jobNumber` optional — both omitted flushes **all** pending rows.

### Request

No body. Example:

```
POST /irf/v1/callback/flush?sec=UAT-IRF-7f3a9c2b&insCode=1&jobNumber=42
```

### Response 200

```json
200 OK
```

(Empty body; rows updated to SENT/ERROR + `ICB_RESPONSE` + `ICB_LAST_UPDATED`.)

---

## 4. POST /irf/v1/callback/retry?sec=&refSerNumber=

Re-send a single row. Keyed by the **entity serial** (`ICB_SER_NUMBER`,
`findById`), not `refSerNumber`.

### Request

No body. Example:

```
POST /irf/v1/callback/retry?sec=UAT-IRF-7f3a9c2b&refSerNumber=152966
```

### Response 200 — boolean

```json
true
```

(`true` = webhook accepted; `false` = error stored as `ICB_RESPONSE`.)

---

## Error responses

| Status | Meaning |
|---|---|
| 401 | wrong/missing `sec` |
| 400 | Jackson deserialization failure (e.g. multi-char `Character` field) |
| 501 | network not relocated / no calculator registered |
| 500 | server-side error (e.g. missing `serialNumber` on MC path, `ORA-00904` on callback after the 2026-08-13 schema revert) |

## Quick smoke test

```bash
curl -s -X POST "http://localhost:8085/irf/v1/calculate?sec=change-me" \
  -H 'Content-Type: application/json' \
  -d '{"network":"VISA","insCode":7085,"cardNumber":"411111111","txnData":{"mcc":"5499","txnAmount":100.0,"posEntryMode":"012","approvalCode":"ABC123","acqInstConCode":"784"}}'
```
