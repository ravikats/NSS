# IRF Calculation Module Review

## Findings

1. `VisaIrfCalculation.java:398-404` and `VisaIrfCalculation.java:447-460`: Visa UAE/Oman rate lookup treats missing/null `maximum` as `0.0`, then caps any positive fee down to `0.0`. Other calculators only cap when max is greater than zero. If `maximum = null/0` means "no max", Visa IRF can be incorrectly calculated as zero.

2. `UAESwitchIRFCalculation.java:48-50` and `JaywanIRFCalculation.java:48-50`: when no IRF config exists, these return a non-null but mostly empty `IRFResultVo`. `TxnProcessingService.java:179-180` then writes null IRF fields to the transaction instead of using `updateDefaultIrf`. This can produce null `ird`, null amounts, and incomplete callbacks.

3. `OmanNetIRFCalculation.java:43`: `cardNumber.substring(0, 6..9)` assumes at least 9 digits. A short decrypted PAN will throw and return `null`, causing default IRF. `OnusIRFCalculation.java:42` is safer because it uses `StringUtils.substring`.

4. `TxnProcessingService.java:1186-1215`: `/v1/RecalculateInterchange` only recalculates Mastercard and Visa. Normal transaction processing supports `UAESWITCH`, `OMANNET`, `JAYWAN`, and `ONUS` too via `fetchIrf` at `TxnProcessingService.java:1441-1452`, so recalculation behavior is incomplete/inconsistent.

## What It Does

The IRF module calculates interchange reimbursement fee data for a transaction, stores it back on `PosTransactionEntity`, and sends an IRF callback.

The main flow is in `TxnProcessingService`:

- A transaction is mapped and saved.
- `irfStatusCheck` decides whether IRF should be calculated: response code must be `00`, not reversal, and transaction mode/proc code must qualify.
- `fetchIrf` routes calculation by network:
- `MCI` / `MDS` -> `UAEMcIRFCalculation`
- `VISA` / `VSMS` -> `VisaIrfCalculation`
- `UAESWITCH` -> `UAESwitchIRFCalculation`
- `OMANNET` -> `OmanNetIRFCalculation`
- `JAYWAN` -> `JaywanIRFCalculation`
- `ONUS` -> `OnusIRFCalculation`
- `updateIrfTxn` copies `IRFResultVo` fields onto the transaction: IRD code, fixed fee, percentage, amount, USD amount, card type, domestic/international flag, product/category, remarks, min/max.
- `irfCallback` persists and sends callback data.

## Calculation Pattern

Most calculators use the same formula:

```text
IRF amount = fixed fee + txn amount * percentage / 100
```

Then they apply a maximum cap if configured.

## Scheme Behavior

### UAE Switch and Jaywan

- Look up IRF config by MCC.
- If MCC is not found, fall back to `GENERAL`.
- For MCC-specific config, use `irfRate` and `irfMax`.
- For `GENERAL`, choose POS or E-COM rates based on terminal type.
- Set domestic debit defaults: card type `D`, domestic flag `D`.

### OmanNet

- Builds 6-9 digit BIN candidates from the PAN.
- Finds the longest matching OmanNet BIN for the transaction network.
- If route is `MAAL`, looks up rate by route, sub-route, card type, and MCC.
- Otherwise falls back to segment `all segment`.
- Calculates fixed plus percentage, capped by max.

### On-us

- Looks up the first 8 digits of PAN.
- Only calculates if BIN route is `onus`.
- Uses route, sub-route, card type, and MCC.
- No fallback config; missing match results in zero/default values.

### Mastercard UAE/Oman

- Looks up Mastercard issuer/acquirer range to determine GCMS product, card program, issuer country, issuer region, and domestic/international flag.
- Inserts an IRF parameter record into `McIrfParamsEntity`.
- Reads computed IRD/rate data from `IpmDetailsView`.
- Defaults to IRD `85` and `2.5%` if no IRD is found.
- Applies Mastercard override rates for domestic transactions and international refunds.
- Converts/stores amounts differently for UAE vs Oman using `exchangeRateAED` or `exchangeRateOMR`.

### Visa

- Builds an `IRFRequestVo` from transaction fields and Visa BIN range data.
- Determines domestic UAE, domestic Oman, MEA, or international region.
- Chooses fee program in priority order, such as industry fee, product rate, alternative rate, acquirer downgrade, base fee, or uncategorized.
- Looks up `VisaIrfProgramEntity` by region, card type, fee program, MCC/product, limit indicator, and qualifier.
- Calculates fixed plus percentage, with min/max handling and exchange-rate conversion.
