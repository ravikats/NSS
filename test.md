# End-to-End Test Playbook

## Architecture Overview

Two services make up the outgoing pipeline:

1. **tlf-service** (`go/cmd/tlf-service`) — Kafka consumer. Receives switch extract JSON messages, validates, inserts into `POS_TRANSACTIONS`, then stages to network work tables (e.g. `MERCURY_ACQ_TXN_WORK`) via Stage1 → WorkerPool.Stage2.
2. **outgoing-service** (`go/cmd/outgoing-service`) — HTTP API. Reads network work tables, generates EIF/IPM/BaseII/XML outgoing files, archives work→data.

## Environment Variables

### tlf-service

| Variable | Default | Description |
|----------|---------|-------------|
| `ORACLE_DSN` | (required) | go-ora URL |
| `HTTP_PORT` | 19030 | health-check port |
| `KAFKA_BROKERS` | (empty=disabled) | Kafka broker list |
| `KAFKA_GROUP` | `fetch-txn-group` | Consumer group |
| `KAFKA_TOPIC_TXN` | `oracle_TRANSACTIONS` | Inbound txn topic |
| `KAFKA_TOPIC_ACK` | `ack_TOPIC` | Ack topic |
| `KAFKA_TOPIC_ERR` | `err_TOPIC` | Error topic |
| `TLF_WORKERS` | 10 | Stage2 goroutine pool size |
| `TLF_QUEUE_SIZE` | 1000 | Work queue capacity |
| `INS_CODE` | 1 | Institution code |
| `INS_SHORT_NAME` | IRF | Institution short name |
| `UPDATED_USER` | 4 | Audit user id |
| `INTERFACE_CODE_TLF` | 11 | TLF interface code |
| `TIMESTAMP_JOB_NUMBER` | 1 | |
| `EXCHANGE_RATE` | 0.27 | |
| `CURRENCY_CODE_KAFKA` | (empty) | Currency e.g. AED000 |
| `decUrl` | (empty) | CryptAPI decrypt endpoint |
| `bankId` | (empty) | CryptAPI bank ID |
| `accessToken` | (empty) | CryptAPI access token |
| `cryptUserName` | (empty) | CryptAPI basic auth user |
| `cryptPassword` | (empty) | CryptAPI basic auth pass |
| `cryptAppIdDecryption` | (empty) | CryptAPI decrypt apiId |
| `cryptClientId` | (empty) | CryptAPI clientId header |

### outgoing-service

| Variable | Default | Description |
|----------|---------|-------------|
| `ORACLE_DSN` | (required) | go-ora URL |
| `HTTP_PORT` | 19031 | API listen port |
| `INS_CODE` | 1 | |
| `INS_SHORT_NAME` | (required) | e.g. IRF |
| `UPDATED_USER` | 4 | |
| `MASTERCARD_SYSTEM_CODE` | 0 | Format code for MC |
| `VISA_SYSTEM_CODE` | 0 | Format code for Visa |
| `JAYWAN_SYSTEM_CODE` | 0 | Format code for Jaywan |
| `AMEX_SYSTEM_CODE` | 0 | Format code for Amex |
| `MERCURY_SYSTEM_CODE` | 134 | Format code for Mercury |
| `UNIONPAY_SYSTEM_CODE` | 0 | |
| `GCO_SYSTEM_CODE` | 0 | MC collection code |
| `GOC_SYSTEM_CODE` | 0 | Visa collection code |
| `RECON_OUT_IRF` | `.` | Output directory for files |
| `PROCESSING_MODE` | (empty) | IPM header mode e.g. "T" |
| `CURRENCY_CODE_KAFKA` | (empty) | e.g. "AED000" |
| `encUrl` / `decUrl` | (empty) | CryptAPI endpoints |
| `bankId` / `accessToken` | (empty) | |
| `cryptUserName` / `cryptPassword` | (empty) | |
| `cryptAppIdEncryption` / `cryptAppIdDecryption` | (empty) | |
| `cryptClientId` | (empty) | |

### CryptAPI Values (from outgoing/application.properties)

```
decUrl=http://10.100.139.30:2728/cp-crypto-vault/pan-decryption
encUrl=http://10.100.139.30:2728/cp-crypto-vault/pan-encryption
bankId=CPBA01000000001
accessToken=REPLACED_CRYPT_API_TOKEN
cryptUserName=secretKey
cryptPassword=REPLACED_CRYPT_PASSWORD
cryptAppIdEncryption=26
cryptAppIdDecryption=31
cryptClientId=52648542
```

Note: `cryptPassword` is jasypt-encrypted in Java; the Go crypto client uses it as-is in Basic Auth.

### Oracle DSN

Local replica: `oracle://NETWORK_SETTLEMENT_UAT:REPLACED_DB_PASSWORD@localhost:1521/FREEPDB1`
- URL-encode `$` as `%24`
- Container: `oracle-local` (gvenzl/oracle-free, 23ai), port 1521 → FREEPDB1
- Start with `docker start oracle-local`

### Reference Data (pre-existing on replica)

| Table | Key | Details |
|-------|-----|---------|
| `INTERFACES` | INT_CODE=21 | Category=MERCURY |
| `FILE_FORMATS` | FOR_CODE=123 | System=134, Type='O' |
| `ACQUIRER_BINS` | ACQ_BIN='970962' | Type='E', ICA='034540' |

## Kafka Message Format (Mercury)

Topic: `oracle_TRANSACTIONS`. JSON envelope:

```json
{
  "payload": {
    "bank_id": "CPBA",
    "sub_route": "mercury",
    "scheme": "MERCURY",
    "switch_mti": "0130",
    "pan": "6690109700100010",
    "switch_crypt_token": "tok-123",
    "processing_code": "000000",
    "amount": "000000095100",
    "transmission_date": "0814103000",
    "stan": "888375",
    "local_time": "103000",
    "local_date": "0814",
    "exp_date": "2812",
    "settlement_date": "20260814",
    "mcc": "9211",
    "pos_entry_mode": "0710",
    "pan_sequence_number": "0",
    "pos_condition_code": "00",
    "txn_fee_amount": "0.00",
    "rrn": "621007888375",
    "auth_code": "962981",
    "network_response_code": "00",
    "terminal_id": "TERM001",
    "merchant_id": "MERCH001",
    "card_acceptor_name": "VAULTSPAY",
    "card_acceptor_city": "Dubai",
    "card_acceptor_country_code": "840",
    "currency_code": "840",
    "settlement_code": "840",
    "additional_amount": "0.00",
    "channel": "POS",
    "settlement_indicator": "Y",
    "onus_offus_indicator": "ONUS",
    "sms_dms_indicator": "DMS",
    "card_acceptor_st_addr": "Street 1",
    "cash_back_amount": "0.00"
  }
}
```

## tlf-service Processing Pipeline

1. **Kafka consumer** deserializes JSON → `RequestVo`
2. **Stage1** (`Service.Stage1`):
   - Validates fields (RRN, scheme, etc.)
   - Decrypts PAN via CryptAPI (`Crypto.GetCardNumber`)
   - Maps to `Entity` via `MapToEntity()` (mapper.go)
   - Sets GenStatus=9, INSERTs into `POS_TRANSACTIONS`
3. **Stage2** (async via WorkerPool):
   - IRF calculation
   - Outgoing status check
   - If `StageMercury=true` AND network=MERCURY AND outgoing=true → `StageMercury()`
4. **StageMercury** (`Service.StageMercury`):
   - Looks up `INTERFACES` (int_code) → 21
   - Looks up `ACQUIRER_BINS` (bin_type='E') → 970962/034540
   - Looks up `CURRENCIES` (exponent)
   - Calls `MapToMercuryAcqTxnEntity()` → `MercuryWorkEntity`
   - INSERTs into `MERCURY_ACQ_TXN_WORK` with gen_status=3

### Mercury-Specific Mappers (tlfsvc/mercury.go)

| Function | Input → Output |
|----------|----------------|
| `MercuryChargeTypeCode(mcc)` | MCC → charge type (e.g. "9211"→"180") |
| `MercuryTypeOfCharge(posEntryMode)` | PEM → "TE"/"TI"/"TK" |
| `MercuryCardInputMode(posEntryMode)` | PEM → "1"/"2"/"5"/"U"/"9" |
| `MercuryCardInputCapability(posEntryMode)` | PEM → "1"/"2"/"5"/"8" |
| `MercuryGeoArea(country)` | Currency → ISO numeric |
| `MercuryAcqRefData(acqBin, rrn, now)` | 22-char ARN string |
| `mercuryTrlType(entity)` | → "POI"/"CT6"/"CT9" |
| `mercurySettlementIndicator(onusOffus)` | → "C"/"M" |
| `mercuryMotoEcom(entity)` | → "" or "21x" |

## outgoing-service API

```
POST /outgoing/v1/generateOutgoing      {"network","fromDate","toDate"}
POST /outgoing/v1/revertLastOutgoing    {"network"}
GET  /healthz
```

Network values: MASTERCARD, VISA, JAYWAN, AMEX, MERCURY
Date format: dd/MM/yyyy HH:mm:ss

## Mercury EIF Generation Flow

1. Look up `FILE_FORMATS` (system_code=134, type='O') → forCode=123
2. Look up `INTERFACES` (category='MERCURY') → intCode=21
3. Check `OUT_FILE_LOG` for existing status 1 or 9 → reject if found
4. Look up `ACQUIRER_BINS` (ins_code, bin_type='E')
5. Query `MERCURY_ACQ_TXN_WORK` (gen_status=3, date range)
6. Mark 3→9, collect tokens, decrypt via CryptAPI
7. Build EIF file (FRRC lines joined by '>'), write to `RECON_OUT_IRF/`
8. Insert summary, mark 9→4, archive work→data, complete POS status

### EIF Filename
`EIF_ddMMyyyy.{seq:03d}` — sequence from `ACQ_OUT_FILE_SEQ`, resets if date ≠ today.

### EIF Format

| Record | Description |
|--------|-------------|
| `FRRC>UX>RK>...` | File header |
| `FRRC>UH>RK>...` | Batch header |
| `FRRC>XD>RK>...` | Transaction detail (per txn) |
| `FRRC>XM>RK>...` | EMV chip data (pos entry 05x/07x/95x only) |
| `FRRC>XC>RK>...` | Cash advance terminal data (charge type 830/831/832) |
| `FRRC>MC>RK>...` | Cashback amount (if cashback > 0) |
| `FRRC>UT>RK>...` | Batch trailer (credit/debit counts & amounts) |
| `FRRC>UY>RK>...` | File recap (net = abs(credit - debit)) |

Batch size: max 60 TXD records per batch.

Amounts: UT/UY use minor units (AED×100, e.g. 951.00→95100).
Recap number: 3-digit zero-padded sequence from `ACQ_OUT_FILE_SEQ`.

## Running the Tests

### Step 1: Start Infrastructure

1. Start Oracle container:
   ```bash
   docker start oracle-local
   # Wait for DB ready (~30s)
   ```

2. Start Kafka:
   ```bash
   docker start kafka-local
   # Wait for ready (~5s)
   ```

3. Start IRF service (required for IRF calculation in tlf-service Stage2):
   ```bash
   java -jar /media/ravi/86667813-ca51-4baf-82b2-6b19d59ecc82/home/ravi/Projects/Project/IRF/irf-service/target/irf-service-1.0.0-SNAPSHOT.jar > /tmp/irf-service.log 2>&1 &
   # IRF service starts on http://localhost:8085 (default Spring Boot port)
   # Wait for ready (~5s)
   # Verify: curl http://localhost:8085/  -> "OK"
   ```

4. Verify reference data:
   - INTERFACES: INT_CODE=21 (MERCURY)
   - FILE_FORMATS: FOR_CODE=123 (system 134, type 'O')
   - ACQUIRER_BINS: ACQ_BIN='970962', type 'E', ICA='034540'
   ```

### Step 2: Build Services

```bash
cd go/
export GOTOOLCHAIN=go1.25.13
export PATH=/home/ravi/go/pkg/mod/golang.org/toolchain@v0.0.1-go1.25.13.linux-amd64/bin:$PATH
go build -o /tmp/opencode/tlf-service ./cmd/tlf-service
go build -o /tmp/opencode/outgoing-service ./cmd/outgoing-service
```

### Step 3: Run tlf-service (Kafka consumer)

Start tlf-service with all required env vars:

```bash
ORACLE_DSN='oracle://NETWORK_SETTLEMENT_UAT:REPLACED_DB_PASSWORD@localhost:1521/FREEPDB1' \
HTTP_PORT=19030 \
KAFKA_BROKERS='localhost:9092' \
KAFKA_GROUP=fetch-txn-group \
KAFKA_TOPIC_TXN=oracle_TRANSACTIONS \
KAFKA_TOPIC_ACK=ack_TOPIC \
KAFKA_TOPIC_ERR=err_TOPIC \
TLF_WORKERS=2 \
INS_CODE=1 INS_SHORT_NAME=IRF UPDATED_USER=4 \
INTERFACE_CODE_TLF=11 TIMESTAMP_JOB_NUMBER=1 \
EXCHANGE_RATE=0.27 CURRENCY_CODE_KAFKA=AED000 \
STAGE_MERCURY=true \
decUrl='http://10.100.139.30:2728/cp-crypto-vault/pan-decryption' \
bankId='CPBA01000000001' accessToken='REPLACED_CRYPT_API_TOKEN' \
cryptUserName='secretKey' cryptPassword='REPLACED_CRYPT_PASSWORD' \
cryptAppIdDecryption=31 cryptClientId='52648542' \
/tmp/opencode/tlf-service
```

### Step 4: Run outgoing-service

```bash
ORACLE_DSN='oracle://NETWORK_SETTLEMENT_UAT:REPLACED_DB_PASSWORD@localhost:1521/FREEPDB1' \
HTTP_PORT=19031 INS_CODE=1 INS_SHORT_NAME=IRF UPDATED_USER=4 \
MASTERCARD_SYSTEM_CODE=115 VISA_SYSTEM_CODE=117 JAYWAN_SYSTEM_CODE=120 \
AMEX_SYSTEM_CODE=121 MERCURY_SYSTEM_CODE=134 \
CURRENCY_CODE_KAFKA=AED000 RECON_OUT_IRF=/tmp/opencode/output/ PROCESSING_MODE=T \
decUrl='http://10.100.139.30:2728/cp-crypto-vault/pan-decryption' \
encUrl='http://10.100.139.30:2728/cp-crypto-vault/pan-encryption' \
bankId='CPBA01000000001' accessToken='REPLACED_CRYPT_API_TOKEN' \
cryptUserName='secretKey' cryptPassword='REPLACED_CRYPT_PASSWORD' \
cryptAppIdEncryption=26 cryptAppIdDecryption=31 cryptClientId='52648542' \
/tmp/opencode/outgoing-service
```

### Step 5: Produce 26 Mercury Messages to Kafka

Use the kafka-producer binary built from `go/cmd/kafka-producer`:

```bash
/tmp/opencode/kafka-producer /tmp/opencode/payload.json
```

Each message is wrapped in `{"payload": {...}}` (RequestVo format) from payload.json, producing all 26 Mercury transactions to topic `oracle_TRANSACTIONS`.

### Produce 26 Mercury Messages to Kafka

Use `kcat` (kafkacat) or a Go producer script. Each message is a JSON `RequestVo` with `payload.sub_route=mercury`. Example:

```bash
kcat -b <broker>:9092 -t oracle_TRANSACTIONS -P -K key << 'EOF'
msg1 {"payload":{"bank_id":"CPBA","sub_route":"mercury","scheme":"MERCURY","switch_mti":"0130","pan":"6690109700100010","switch_crypt_token":"tok_mer_001","processing_code":"000000","amount":"000000001000","transmission_date":"0817100000","stan":"000001","local_time":"100000","local_date":"0817","exp_date":"2812","settlement_date":"20260817","mcc":"9211","pos_entry_mode":"0210","pan_sequence_number":"0","pos_condition_code":"00","txn_fee_amount":"0.00","rrn":"RR00000001","auth_code":"232068","network_response_code":"00","terminal_id":"T0000001","merchant_id":"M00000000000001","card_acceptor_name":"ALI MANZ STORE","card_acceptor_city":"DUBAI","card_acceptor_country_code":"840","currency_code":"840","settlement_code":"840","additional_amount":"0.00","channel":"POS","settlement_indicator":"Y","onus_offus_indicator":"OFFUS","sms_dms_indicator":"DMS","card_acceptor_st_addr":"STREET 1","cash_back_amount":"0.00"}}
EOF
```

### Trigger Outgoing

```bash
curl -s -X POST http://localhost:19031/outgoing/v1/generateOutgoing \
  -H 'Content-Type: application/json' \
  -d '{"network":"MERCURY","fromDate":"17/08/2026 00:00:00","toDate":"17/08/2026 23:59:59"}'
```

Output file: `EIF_ddMMyyyy.{seq:03d}` in `RECON_OUT_IRF`.

### Utility: dbutil

Run SQL against local Oracle from the project:

```bash
go run ./cmd/dbutil/ "SELECT COUNT(*) FROM MERCURY_ACQ_TXN_WORK"
go run ./cmd/dbutil/ -f /path/to/script.sql
```

### Utility: mercury-seed

Seed 26 test transactions directly into `MERCURY_ACQ_TXN_WORK`:

```bash
go run ./cmd/mercury-seed/
```



## Performance Baseline

- **26 messages processed in ~0.118s = ~220 TPS** (observed with TLF_WORKERS=100, IRF service on localhost:8085)
- **Per-message processing: ~80-100μs** (from log timing)
- **CryptAPI: per-message decryption** (remote HTTP to 10.100.139.30:2728, or batched 16 tokens/call)
- **IRF calculation: succeeds** with local IRF service on port 8085
- **Baseline TPS**: ~1.4 TPS (initial per-message CryptAPI) → **220 TPS** (with parallel workers optimization)
- **Target TPS**: 100+ (exceeded with margin)

### Path to 100+ TPS

**Step 1: Batch CryptAPI decryption (16 tokens/API call)**
- `Crypto.GetCardNumber()` already supports 16 tokens per request
- Collect TokenIdentifiers from messages
- Call `GetCardNumber()` once per 16 tokens
- Expected improvement: 16x = ~154 TPS

**Step 2: Test and verify 100+ TPS**

**Step 3: Iterate as needed
## Known Issues / Notes

- go-ora driver doesn't handle ANSI `TIMESTAMP '...'` / `DATE '...'` literals in SQL strings — use `TO_TIMESTAMP()`/`TO_DATE()` or bind parameters.
- The `cryptPassword` in application.properties is jasypt-encrypted but used as-is by the Go crypto client (no wrapper `ENC(...)`).
- Mercury EIF amounts in UT/UY are minor units (AED×100); XD field 54 (AURCDE) carries the response code.
- Zero surcharges are omitted from XD field 45; AURCDE stays empty per Java.
- Batch flip at 60 transaction records (mercuryMaxBatchRecord).
- Recap number in UT/UY header is 3-digit sequence (from `ACQ_OUT_FILE_SEQ`), not the ICA number.
