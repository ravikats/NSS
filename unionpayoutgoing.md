# UnionPay Outgoing — Go Port (`go/outsvc`)

Settlement-file generation for **UnionPay** (cross-border dual-message) in the Go
outgoing service, ported per the UnionPay "Technical Specifications on Bankcard
Interoperability — Part III File Interface" (v25.2), sections 2 (Basic
Specifications) and 4 (Settlement File).

Status: **implemented + unit-tested locally (2026-08-16)**. Not yet deployed to
UAT; the target DB schema is **assumed** (see §5) because the live Oracle on
`192.168.29.79:1521` was unreachable at build time.

## 1. File

One sequential settlement file per run:

| Network | Filename | Notes |
|---------|----------|-------|
| UNIONPAY | `OFCYYMMDD5?C` (12 chars) | `O`=outgoing, `F`=cross-border, `C`=dual-message settlement, `YYMMDD`=file date, `5`=member digit, `?`=batch number (0–9, `seq % 10`), `C`=file type |

Per the manual: `OFCYYMMDD5?` for positions 1–11 with position 12 `C` (settlement
file). The batch digit comes from `ACQUIRER_BINS.OUT_FILE_SEQ` (`updateAndGetUnionPayFileSequence`,
same row the other networks use; resets to 1 when the date changes).

## 2. Record Layout (CRLF-terminated, one record per line)

- **TC 000 — File Header Record** (46 chars, Block 0 only, bitmap `8000`):
  `000` + `8000` + IIN (11, left-justified, from `ACQUIRER_BINS.MC_ICA_NO`) +
  `YYYYMMDD` + 8 spaces + version tag (`TEST`/`PROD`, 4, env) + `00000001`.
- **TC 100 / TC 101 — Transaction Records**:
  - **Block 0 (269 chars)** — basic settlement information. See §3.
  - **Block 1 (118 chars)** — exchange-rate features. Always present.
  - **Block 2 (294 chars)** — IC card characteristic data. **Chip transactions
    only** (POS entry mode `05*`/`07*`/`95*`).
  - Bitmap: `C000` (Block 0+1) for magstripe, `E000` (Block 0+1+2) for chip.
- **TC 001 — File Trailer Record** (49 chars, Block 0 only): `001` + `8000` +
  total record count (`%010d`, header + txns + trailer) + 16-space MAK + 16-space
  MAC.

## 3. Block 0 Field Map (positions 1-based)

| Pos | Len | Field | Source |
|-----|-----|-------|--------|
| 1–3 | 3 | Transaction code | `100`/`101` |
| 4–7 | 4 | Bitmap | `C000`/`E000` |
| 8–26 | 19 | PAN (decrypted) | left-justified |
| 27–38 | 12 | Amount (settlement, minor units) | `TxnAmount × 10^fractionDigits`, zero-padded |
| 39–41 | 3 | Currency code | `TxnCurCode` (default `784`) |
| 42–51 | 10 | Transaction datetime | `MMDDhhmmss` from `LocalDateTime` |
| 52–57 | 6 | STAN | `StanNumber`, zero-padded n6 |
| 58–63 | 6 | Authorization response code | `ApprovalCode` |
| 64–67 | 4 | Authorization date | `MMDD` |
| 68–79 | 12 | RRN | `Rrn` |
| 80–90 | 11 | Acquiring institution ID | `AcqinstIdCode` |
| 91–101 | 11 | Forwarding institution ID | `FwdInstIdCode` |
| 102–105 | 4 | MCC | `Mcc` |
| 106–113 | 8 | Terminal ID | `TerminalId` |
| 114–128 | 15 | Card acceptor ID | `MerchantId` |
| 129–168 | 40 | Card acceptor name | `MeName` |
| 169–191 | 23 | Original transaction info | zeros |
| 192–195 | 4 | Message reason code | `0000` |
| 196 | 1 | Dual-message flag | `1` |
| 197–205 | 9 | GSCS serial number | zeros |
| 206–216 | 11 | Receiving institution ID | `ReceivingInstIdCode` |
| 217–227 | 11 | Original institution ID | `OrgInstIdCode` |
| 228 | 1 | GSCS notice identifier | `0` |
| 229–230 | 2 | Initiating channel | `TxnInitiatingChannel` (default `00`) |
| 231 | 1 | Transaction features | space |
| 232–234 | 3 | Transaction scenario | spaces |
| 235–239 | 5 | Reserved | spaces |
| 240–269 | 30 | Other information | `00`+` `+POS condition(2)+merchant country(3)+` `+`100`+` `+pricing(2)+15 spaces |

## 4. Block 1 (118 chars)

`POS entry mode(3)` + `0` (floor-limit id) + `0 ` (payment service) +
amount settlement (12) + currency (3) + conversion rate `20000100` + amount
billing (12) + currency billing (3) + rate billing `20000100` + net fee
` 00000000000` (12) + IRF billing currency `000` + exchange rate `30001000` +
org abbreviation (3 spaces) + Mainland indicator (1 space) + txn fee
` 00000000000` (12) + QRC voucher (20 spaces) + reserved (7).

## 5. Assumed Schema (`UP_ACQ_TXN_WORK` / `UP_ACQ_TXN_DATA`)

Columns prefixed `UPT_*`, mirroring the `MERCURY_ACQ_TXN_WORK` port
(`mercury_store.go`). The `Store` interface additions:

- `CountUnionPayWorkBetween` / `CountUnionPayWorkLessThanEqual` (status 3 count)
- `FindUnionPayWorkBetween` / `FindUnionPayWorkLessThanEqual` / `FindUnionPayWorkByStatus`
- `UpdateUnionPayWorkStatuses` (3→9, 9→4, 4→7)
- `DeleteUnionPayWork`, `InsertUnionPayData` (66-column copy, work→data move)
- `CompleteUnionPayPosStatus` (POS rows → completed)

`ACQUIRER_BINS` bin type **`U`** (assumed — confirm on UAT). IIN for the header
comes from the bin's `MC_ICA_NO` column. `INTERFACES` category `UNIONPAY`
(assumed — confirm the `INT_CODE`).

## 6. Wiring / Config

- `controller.go` — `outgoingNetworks["UNIONPAY"]`, `formatCode =
  cfg.UnionPaySysCode`, validation message updated.
- `mc.go` — `getTxnCount` + `scheduleFileProcessing` cases route to
  `ProcessUnionPayOutgoing`.
- `cmd/outgoing-service/main.go` — env `UNIONPAY_SYSTEM_CODE` (default 0) and
  `UNIONPAY_VERSION_TAG` (default `TEST`).

```bash
UNIONPAY_SYSTEM_CODE=137 UNIONPAY_VERSION_TAG=TEST   # example values — confirm on UAT
```

## 7. Flow (`ProcessUnionPayOutgoing`)

1. `FindFileFormatBySystemCodeAndType(sysCode, "O")` → format code.
2. `FindInterfaceByCategory("UNIONPAY")` → interface code.
3. `FindFileLogByFormatCodeAndStatuses` (status 1/9) → "File Generation already
   Scheduled" if any.
4. `FindAcquirerBins(ins, "U")` → IIN + file sequence.
5. `FindUnionPayWork*` (status 3, date range) → "No data found" if empty.
6. Mark work 3→9; decrypt PAN tokens via CryptAPI (deduped). Decryption failure →
   status 7, "Outgoing Failed".
7. Per chunk (≤ 50000 txns): bump bin sequence, name `OFCYYMMDD5?C`,
   `inserOutFileLog` (status 9), build TC000+txn records+TC001, write CRLF file
   to `RECON_OUT_<INS>`, status 9→4 + file id, `updateOutFilelog` (status 4),
   `insertUnionPayIntoOutgoingSummary` (grouped by `TxnType`).
8. `CompleteUnionPayPosStatus`; move work→data; delete work rows. Returns
   "Success".

## 8. Tests

`go/outsvc/unionpayout_test.go` — `TestUnionPayFileBuilder` (full flow via a
`unionPayFakeStore`: header/trailer layout, magstripe 387-char vs chip 681-char
records, PAN/amount/currency/datetime offsets, Block 2 cryptogram, CRLF, work→
data move, POS complete, bin sequence bump), `TestUnionPayFileName`,
`TestUnionPayIsChipTxn`.

```bash
go test ./outsvc/ -run UnionPay -count=1 -v
```

## 9. Open Items / Risks

- **Schema is assumed** (`UPT_*` columns, bin type `U`, `INTERFACES` category
  `UNIONPAY`). Confirm against the real UAT Oracle before deploy.
- Block 2 emits only when POS entry mode starts `05`/`07`/`95` (chip). Magstripe
  txns stay Block 0+1.
- Default currency `784` (AED) used when `TxnCurCode` is empty.
- Batch digit wraps `seq % 10` (0–9); the manual allows `00–99` — revisit if >10
  files per day.