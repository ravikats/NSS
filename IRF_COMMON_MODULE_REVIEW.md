# Shared `irf-common` Module — Analysis & Scaffold Summary

Generated for the consolidation of `mpgs-java` (batch file processor) and
`tlf-processing-service-java` (online / Kafka processor).

## 1. What was shared

Both services ship `com.empay` source trees. A class-name comparison found 56
classes with identical names across the two projects:

- **46 byte-identical or package-only-different** (move verbatim, repackage to
  `com.empay.common.*`).
- **10 functionally shared but divergent-algorithm** (Visa/MC IRF calculators,
  IRF callback service, CryptAPI, split services).

### Moved verbatim into `irf-common` (40 source files)

| package | classes |
|---|---|
| `com.empay.common.functions` | `ARNCheckDigit`, `JulianDateConverter`, `PinCaptureAbility` |
| `com.empay.common.cryptapi` | `DecryptResponseVo`, `EncryptResponseVo` |
| `com.empay.common.exceptions` | `ValidationException` |
| `com.empay.common.security` | `EncryptionUtil` |
| `com.empay.common.util` | `CurrencyUtil` |
| `com.empay.common.entities` | `AmexAcqTxnWorkEntity`, `McIrfParamsEntity`, `McIssAcqRangeEntity`, `McOverrideRatesEntity`, `IpmDetailsView`, `AcquirerBinEntity`, `CountriesEntity`, `CurrencyEntity`, `VisaIrfProgramEntity`, `VisaIssAccRangeEntity`, `McProductMappingEntity` |
| `com.empay.common.repo` | `AmexAcqTxnRepo`, `AcquirerBinRepo`, `CountriesRepository`, `CurrencyRepo`, `IpmDetailsViewRepo`, `McIrfParamsRepo`, `McIssAcqRangeRepo`, `McOverrideRatesRepo`, `McProductMappingRepo`, `VisaIrfProgramRepo`, `VisaIssAccRangeRepo` |
| `com.empay.common.vo` | `IRFRequestVo`, `IRFResultVo`, `MCOverRideResultVo` |
| `com.empay.common.controllers` | `StatusCheckController` |

### New IRF core (`com.empay.common.irf`)

- `IrfTxnData` — txn-agnostic DTO (field union read by the Visa/MC calculators);
  each service provides an adapter (`IrfTxnDataMapper<T>`) to map
  `PosTransactionEntity` / `MPGSTxnWorkEntity` → this DTO.
- `IrfCalculator` — base contract + static `supports(network,...)` routing helper.
- `VisaIrfCalculator`, `McIrfCalculator` — per-scheme contracts.
- `IRFCallbackSender` (+ immutable `IRFResultData`) — enqueue/flush/retry,
  supporting both the TLF synchronous strategy and the MPGS deferred strategy.

## 2. IRF Calculation Findings (cross-checked)

1. **Visa max-cap bug** — `VisaIrfCalculation` (`getVisaIrfRate_uae` /
   `getVisaIrfRate_oman`) caps `irfAmount = irfMaximum` **without** a
   `irfMaximum > 0` guard. `null`/missing maximum defaults to `0.0`, so a
   positive fee can be zeroed. Every other calculator guards with `> 0`.

2. **Empty-result on missing config** — `UAESwitchIRFCalculation` /
   `JaywanIRFCalculation` return a non-null but empty `IRFResultVo` when no IRF
   config exists. `TxnProcessingService.updateIrfTxn` then writes `null` ird /
   amounts into `PosTransactionEntity` (VO uses boxed `Double`/`Integer`)
   instead of `updateDefaultIrf`'s zero defaults, producing nulls downstream into
   the IRF callback payload.

3. **OmanNet PAN-length crash** — `OmanNetIRFCalculation` uses
   `cardNumber.substring(0,6..9)`; a PAN under 9 decrypted digits throws,
   returns `null`, falls back to default IRF. `OnusIRFCalculation` is safer
   (`StringUtils.substring`).

4. **Recalculation incomplete** — `interChangeRecalculation` only handles
   `MCI|MDS` and `VISA`; normal processing supports UAESWITCH, OMANNET, JAYWAN,
   ONUS via `fetchIrf`. Behaviour is therefore inconsistent.

5. **(MPGS-only) `IrfCalculationService.populateIrfData` dead-code bug** —
   sets `irfMinAmount`/`irfMaxAmount` from the result then immediately
   overwrites both to `0.0`, so MPGS batch always loses IRF min/max.

6. **Credential leak in logs** — `CryptAPI.getCardNumber` logs the full request
   entity, including `bankId` and `accessToken`.

7. **Unused dependencies/params** — `UAEMcIRFCalculation` injects `CryptAPI`
   but never uses it; `insCode` unused by the UAE-Switch/Jaywan calculators.

8. **Null-route NPE risk** — `OmanNetIRFCalculation`/`OnusIRFCalculation`
   dereference `binData.getRoute()` without a null check.

9. **Per-day holiday loop** — `VisaIrfCalculation.getVisaTimeLines` iterates
   day-by-day from txn date to now (up to ~3 calls/txn); slow for old txns.

## 3. Performance Findings (Kafka / TLF online path)

Confirmed in `KAFKA_IRF_PERFORMANCE_REVIEW.md`:
- Synchronous IRF callback HTTP inside the Kafka listener
  (`TxnProcessingService:182` → `IRFCallbackService.updateApiResponse` HTTP POST).
- Synchronous decrypt HTTP (`CryptAPI.getCardNumber`) per txn.
- `RestTemplate` with **no timeouts** (`TlfProcessingServiceApplication:24`,
  `TxnProcessingService:1537`).
- Duplicate validation: `TxnFetchKafkaService:72` validates, then
  `TxnProcessingService:148` validates again (incl. a duplicate-check DB query).
- Per-message `new ObjectMapper()` (`TxnFetchKafkaService:47`) and
  `Validation.buildDefaultValidatorFactory()` (`TxnFetchKafkaService:67`).
- Excessive `saveAndFlush` across the flow; MC path has a write-then-read sync
  (`McIrfParamsEntity` insert → `IpmDetailsView` read).

Notable contrast: the MPGS **batch** path persists the callback as `PENDING`
and defers the HTTP POST to a later per-job step (`IRFCallbackService.
updateIRFCallback(jobNumber)`), so the synchronous-callback latency problem is
specific to the TLF Kafka path.

## 4. Quick wins (ordered)

1. Fix the Visa `irfMaximum > 0` guard (uae + oman branches).
2. Make UAE-Switch/Jaywan/OmanNet return `null` (or a zeroed default) when no
   BIN/config matches, so `updateDefaultIrf` is used instead of writing nulls.
3. Use `StringUtils.substring` in `OmanNetIRFCalculation` like `OnusIRFCalculation`.
4. Extend `interChangeRecalculation` to the four missing networks.
5. Fix MPGS `populateIrfData` min/max overwrite (lines 91-92).
6. Remove `accessToken`/`bankId` from `CryptAPI` request log.
7. Make IRF callback asynchronous; add RestTemplate timeouts; reuse
   `ObjectMapper`/`Validator`; drop duplicate validate for Kafka; replace
   non-essential `saveAndFlush` with `save`.

## 5. Saved artifacts

- Common module source: `D:\NSS\IRF\irf-common\...`
- Archive: `D:\NSS\IRF\irf-common-2026-08-12.zip`
- This summary: `D:\NSS\IRF\IRF_COMMON_MODULE_REVIEW.md`
