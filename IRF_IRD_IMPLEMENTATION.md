# IRF & IRD Implementation — Complete Reference

Standalone description of how Interchange Reporting Fee (IRF) is computed and how the
Interchange Rate Description (IRD) is resolved and applied across all six schemes
(Visa, Mastercard, UAE-Switch, Jaywan, OmanNet, On-us).

This document is the single reference for the IRF/IRD engine. Related docs:

- `IRF_SERVICE_HANDOVER.md` — build/run, REST migration steps, verification log, TODOs.
- `IRF_CALCULATION_REVIEW.md` — review findings for the per-scheme calculators.
- `IRF_COMMON_MODULE_REVIEW.md` — what was shared into `irf-common` and performance notes.
- `MC_IRF_GAP_ASSESSMENT.md` — Mastercard manual gap analysis (ed. 3 Feb 2026 vs 4 Aug 2026).
- `UAE_Oman_Implementation_Guide.md` — Mastercard UAE/Oman interchange manual-derived guide.
- `IRF_API_SAMPLES.md`, `IRF_CALLBACK_COLUMNS.md` — API samples and callback column mapping.

---

## 1. Architecture

IRF is served by a dedicated Spring Boot service (`irf-service`, port 8085) that owns the
calculators and the `IRF_CALLBACK` table. The TLF and MPGS applications consume it either
in-process (via `irf-common` beans) or over REST (via `HttpIrfCalculator` /
`HttpIRFCallbackSender`). Both consumers share the same contract so the orchestration code
does not change when the wiring switches.

```
TLF / MPGS txn entity
        |
        |  IrfTxnDataMapper<T> (thin adapter)
        v
   IrfTxnData (shared DTO)
        |
        |  IrfCalculator.calculate(insCode, txnData, cardNumber)
        v
   IrfCalculationService -> IrfCalculatorRegistry (network -> bean map)
        |
        +-- VISA / VSMS   -> VisaIrfCalculationService
        +-- MCI  / MDS    -> McIrfCalculationService
        +-- UAESWITCH     -> UaeSwitchIrfCalculationService
        +-- JAYWAN        -> JaywanIrfCalculationService
        +-- OMANNET       -> OmanNetIrfCalculationService
        +-- ONUS          -> OnusIrfCalculationService
        |
        v
   IRFResultVo
        |
        |  IRFCallbackSender.enqueue/flush/retry
        v
   IRF_CALLBACK (ICB_*) -> scheme callback URL
```

Module layout:

- `irf-common/src/main/java/com/empay/common/irf/` — contracts: `IrfCalculator`,
  `VisaIrfCalculator`, `McIrfCalculator`, `IrfTxnData`, `IrfTxnDataMapper`, `IrfResultData`,
  `IrfCalculateRequest`/`IrfCalculateResponse`, `IRFCallbackSender`, `HttpIrfCalculator`,
  `HttpIRFCallbackSender`.
- `irf-common/src/main/java/com/empay/common/vo/` — `IRFRequestVo`, `IRFResultVo`, `MCOverRideResultVo`.
- `irf-common/src/main/java/com/empay/common/repo/` + `entities/` — rate/BIN reference data.
- `irf-service/src/main/java/com/empay/irfservice/` — the six calculators, the registry,
  `IrfCalculationService`, the callback store (`callback/`), controllers, config.

---

## 2. Shared contracts (`com.empay.common.irf`)

### 2.1 `IrfCalculator`

```java
@FunctionalInterface
public interface IrfCalculator {
    IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber);
    static boolean supports(String network, String... networks) { ... }
}
```

`cardNumber` is the **decrypted PAN**; the first 9 digits are normally enough for BIN/range
lookups. Return `null` when no IRF applies.

### 2.2 `IrfTxnData`

Txn-agnostic view consumed by every calculator. The field set is the union of the fields
read by the Visa and Mastercard calculators; fields used by only one scheme are simply left
null by the other scheme's mapper. Notable fields:

- Identity/route: `serialNumber`, `insCode`, `network`, `scheme`, `mcc`, `txnCode`,
  `terminalType`, `txnSource`, `posEntryMode`, `posConditionCode`, `serviceCode`,
  `cardSeqNumber`, `acqInstConCode`, `maid`, `tokenIdentifier`, `cardNumber`, `encCardNumber`.
- Amounts: `txnAmount`, `setlAmount`, `cashBackAmount`, `authAmount`, `netAmount`,
  `tipAmount`, `txnFeeAmount`, `cryptAmount`, `chipCashBack`, `dccAmount`.
- Auth/message: `responseCode`, `approvalCode`, `rrn`, `stan`, `mti`, `msgTypeId`,
  `txnUniqueId`, `txnDateTime`, `motoEcomIndicator`, `terminalCapability`, `trlCapabilities`,
  `mvv`, `networkData`, `feePgmIndicator`, `reImbursementAttribute`.
- EMV/terminal flags (used by the MC audit insert): `meCategoryType`, `chAuthAbility`,
  `cardInputAbility`, `cardCaptureAbility`, `cardInputMode`, `oprtEnvironment`,
  `chPresent`, `cardPresent`, `settlementIndicator`, `authReason`.
- Currency: `txnCurCode`, `setlCurCode`, `dccCurrency`, `cardAcceptorCountryCode`.

`IrfTxnData` also exposes `toIrfRequestVo()` which maps the common fields onto `IRFRequestVo`
(mirrors what `VisaIrfCalculationService.buildIrfRequest` does inline).

### 2.3 `IrfTxnDataMapper<T>`

```java
@FunctionalInterface
public interface IrfTxnDataMapper<T> {
    IrfTxnData toIrfData(T txnEntity);
}
```

Each consuming service keeps ONE thin mapper (`TlfTxnMapper<PosTransactionEntity>`,
`MpgsTxnMapper<MPGSTxnWorkEntity>`, ...) so the calculators stay in the shared module.

### 2.4 `IrfCalculateRequest` / `IrfCalculateResponse` (REST)

- Request: `network`, `insCode`, `txnData` (`IrfTxnData`), `cardNumber` (decrypted PAN).
- Response: `calculated` (boolean) + `result` (`IRFResultVo`).

---

## 3. Orchestration

### 3.1 `IrfCalculationService` (irf-service)

Thin router. Delegates straight to the registry:

```java
public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String network, String cardNumber) {
    return registry.calculate(insCode, txnData, network, cardNumber);
}
```

### 3.2 `IrfCalculatorRegistry`

Resolves `network.toUpperCase()` → `IrfCalculator` bean from a `Map<String, IrfCalculator>`.
Unknown network logs a warning and returns `null`.

### 3.3 `IrfServiceProviderConfig` (network → bean wiring)

| Network key | Bean |
|-------------|------|
| `VISA`, `VSMS` | `VisaIrfCalculationService` |
| `MCI`, `MDS` | `McIrfCalculationService` |
| `UAESWITCH` | `UaeSwitchIrfCalculationService` |
| `JAYWAN` | `JaywanIrfCalculationService` |
| `OMANNET` | `OmanNetIrfCalculationService` |
| `ONUS` | `OnusIrfCalculationService` |

`application.yml` also carries an optional override map `irf.service.calculators`
(bean-name overrides). Adding a new scheme = new key in this config + a new `IrfCalculator` bean.

### 3.4 REST entry points

- `POST /irf/v1/calculate?sec=...` — body `IrfCalculateRequest`; returns `IrfCalculateResponse`.
  If `request.txnData.network` is null it is copied from `request.network`.
  Returns `401` on bad `sec`; `501 NOT_IMPLEMENTED` on `UnsupportedOperationException`.
- `POST /irf/v1/callback`, `/irf/v1/callback/flush`, `/irf/v1/callback/retry` — callback store
  (see §7).
- Shared secret: `irf.service.sec` env (`IRF_SERVICE_SEC`), passed as query param `sec`.

---

## 4. Visa IRF (`VisaIrfCalculationService`)

Implements `VisaIrfCalculator`. Flow:

1. `buildIrfRequest` seeds `IRFRequestVo`: `insCode`, `terminalType="POS"`,
   `cardDomIntlFlag='I'`, `txnLimitIndicator='A'`, `progRegion='R'`.
2. Country flag from `txnData.acqInstConCode`: `"784"→'U'` (UAE), `"512"→'O'` (Oman),
   else `' '`. Reimbursement attribute: for `'O'` use `reImbursementAttribute` (default `'B'`);
   otherwise `'B'`.
3. Copy transaction fields (`pan`, `txnDateTime`, `txnAmount`, `mcc`, `feePgmIndicator`,
   terminal capability, `authCode`, `responseCode`, `motoEcomIndicator`, `mvv`,
   `posEntryMode` first 2 chars). `fwdinstCode = null`.
4. Trim `cardNumber` to first 9 digits; look up `VISA_ISS_ACC_RANGE`
   (`findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqual`).
   - **No BIN** → result with `irdCode` as-is, zeroed amounts, `domIntlFlag`,
     `cardType`, `irfCountry="XX"` (this is the "no bin data" case).
5. From the BIN range: `issuerRegion`, credit/debit indicator (`'R'→'P'`, `'H'→'C'`,
   else as-is; also stored as `crDrIndicatorActual`), `countryCode` (alpha), `cardProduct`
   (default `"AO"`). `irdCode = cardProduct`. `qualifierIndicator = 'Q'` when
   `productSubTytpe == "TK"`, else `'N'`.
6. Region routing:
   - `countryCode=="AE"` + flag `'U'` → `progRegion='I'`, `cardDomIntlFlag='D'`.
   - `countryCode=="OM"` + flag `'O'` → `progRegion='I'`, `cardDomIntlFlag='D'`.
   - `issuerRegion=='6'` → `progRegion='E'`.
   - otherwise `progRegion='R'`.
7. Dispatch: `uaeIrfCalculation` (`'I'`+`'U'`), `omanIrfCalculation` (`'I'`+`'O'`),
   `meaIrfCalculation` (`'E'`), `interationalIrfCalculation` (`'R'`).
8. If the dispatched method returns null (no match), a zeroed `IRFResultVo` is returned.

Fee-program selection per region (only within `getVisaTimeLines(txnDateTime) <= 3`, i.e.
transaction ≤ 3 "business days" old — calendar days minus Sundays and 25-Dec):

- **UAE debit/prepaid** (`crDrIndicator` `'D'|'P'`): INDUSTRY FEE PROGRAM by MCC; then CP
  (POS-entry-mode `02|03|05|06|07|90|91|95` and null `motoEcomIndicator`); then CNP.
- **UAE credit** (`'C'`): INDUSTRY FEE PROGRAM (Petrol MCC `5541|5542`; then all-others;
  MCC `5511|5521` with amount ≤ 10000 AED sets `txnLimitIndicator='B'`); PROD-RATE
  (reimb `'B'`, POS-entry-mode `05|07|90|91`); ALT-RATE (POS-entry-mode `01|10`);
  fallback ACQ-DGR. Product `F2` with amount `< 200` sets `'B'`.
- **Oman**: PUBLIC SERVICES FEE PROGRAM (reimb `'D'`, MCC `4214|8062|8641`);
  INDUSTRY FEE PROGRAM TAXI (reimb `'B'`, POS-entry-mode `05|07|90|91`, MCC `4121`);
  INDUSTRY FEE PROGRAM; PROD-RATE; ALT-RATE; fallback ACQ-DGR. Product `F2` with
  amount `< 20` and `'P'` sets `'B'`.
- **MEA** (`'E'`): PROD-RATE (reimb `'B'`, POS-entry-mode `02|05|07|90|91|95`, response
  `Y1|Y3` or 6-digit auth); ALT-RATE (auth 6 digits); fallback ACQ-DGR.
- **International** (`'R'`): BASE-FEE; ALT-FEE; ACQ-DGR; then UNCAT ("UN").

Rate lookup helpers `getVisaIrfRate_uae`, `getVisaIrfRate_oman`, `getVisaIrfRate` query
`VISA_IRF_PROGRAMS` with various `findByRegionAndCardTypeAndFpType...` combinations, then:

```
irfAmount = irfFixed + txnAmount * irfPercent * 0.01
clamp to [irfMin, irfMax]   (UAE petrol: fixed multiplied by AED rate;
                              UAE petrol-pump 5541/5542: min multiplied by AED rate)
irfAmountUSD = irfAmount * exchangeRate
```

Exchange rates from env: `irf.exchange-rate-aed` (3.67), `irf.exchange-rate-omr` (0.38),
`irf.exchange-rate` (1.0). `getVisaTimeLines` counts days minus Sundays and 25-Dec.

---

## 5. Mastercard IRF / IRD (`McIrfCalculationService`)

Implements `McIrfCalculator`. This is the IRD engine (UAE + Oman). Merges the TLF
`UAEMcIRFCalculation` path (full UAE+OMAN, OMR rates, issuer-region, R999 refund override)
with the MPGS variant (populates `pan` on the `MC_IRF_PARAMS` audit row).

Step-by-step:

1. Flags from `txnData`: `approvalCode` non-null → `approvalCodeFlag=1`; `txnId` non-null →
   `traceIdFlag=1`; `maid` non-null → `mcAssigIdFlag=1`; POS-entry-mode starts with
   `02|90|05|07|08` → `magstripeFlag=1`.
2. `countryCodeFlag` from `acqInstConCode` (`"784"→'U'`, `"512"→'O'`, else `' '`).
3. **BIN resolution** — `MC_ISS_ACC_RANGE`:
   `findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqualAndActiveCodeOrderByPriorityCodeAscEffectiveDateDesc(cardNumber, cardNumber, 'A')`.
   Produces `gcmsProdId`, `cardProgId`, `countryCode`, `issuerRegion`.
4. Region: `countryCode 784` + `'U'` → `progRegion='I'`, `cardDomIntlFlag='D'`;
   `countryCode 512` + `'O'` → same; `issuerRegion=='E'` → `'E'`/`'I'`; else `'R'`/`'I'`.
5. **Card type / amount basis**: `cardProgId` matches `MSI|DMC` → debit `'D'`
   (UAE: `txnAmount`; Oman: `setlAmount`); otherwise credit `'C'` using `setlAmount`.
   Credit + `txnType=="01"` → `txnType="09"`.
6. **IRD resolution** — for `MDS` network, `irdCode` stays null (no IPM read). Otherwise:
   a. `mcIrfParamInsert(...)` builds a `MC_IRF_PARAMS` row keyed by `txnData.serialNumber`
      (also carries `pan`, amounts, terminal capabilities normalised through the
      `getCardInputCapability`/`getCardHoldAuthCapability`/`getCardCaptureCapability`/
      `getCardPresent`/`getCHPresent` helpers, `oprtEnvironment` as-is, timeline, flags,
      `issuerRegion`, `cardProgId`, `gcmsProdId`, and last char of `motoEcomIndicator`).
      The row is `saveAndFlush`'d so `VW_IPM_DETAILS` can be read back.
   b. `IpmDetailsView ipmData = ipmDetailsViewRepo.findByTxnSerNumber(txnData.getSerialNumber())`
      → `irdSerNumber`, `irdCode`, `ratePercent`, `irfMinAmount`, `irfMaxAmount`.
   c. If `irdCode` is still null → default `irdCode = "85"`, `irfPercentage = 2.5`.
7. **UAE product override** — `MC_PRODUCT_MAPPING.findByIrdAndGcmsProductId(morIrd, gcmsProdId)`
   (only when `countryCodeFlag=='U'`); if found, `cardCrDrInd = mapping.cardType`.
8. Recompute `txnAmount` basis: `cardCrDrInd=='C'` → `setlAmount`; `'U'` → `txnAmount`;
   `'O'` → `setlAmount`.
9. **Override rates** (see §6.2):
   - Domestic (`cardDomIntlFlag=='D'`): `getLimitIndicator(txnAmount, mcc, morIrd, cardCrDrInd, countryCodeFlag)`.
     Oman special case: `morIrd=="61"`, `mcc=="9999"`, `gcmsProdId ∈ {MEO,MCO,MWO,MAB,MIO}`
     → `limitIndicator='A'` and `mcc=gcmsProdId`.
     Then `getDomOverRide`: Check1 by `mcc`; fallback Check2 by `gcmsProdId`;
     fallback Check3 resets limit indicator for `mcc "9999"`.
   - International refund (`txnType=="20"`): `getIntlRefundOverRide(morIrd)` — looks up
     override id `"REFUND"`, `MCC "R999"`, limit `'A'`.
10. If an override row was found (`morSerNumber != null`): `irfPercentage = morPrecentage`,
    `irfFixed = morFixed`, `irfMax = morMax`, desc becomes `"IRF Amount Override <morSerNumber>"`.
11. `irfAmount = irfFixed + txnAmount * irfPercentage * 0.01`, clamp to `irfMax` (>0).
12. Currency conversion:
    - UAE + credit: `irfAmount = irfAmount / exchangeRateAED`, `irfAmountUSD = irfAmount`.
    - UAE + debit: `irfAmount = irfAmount`, `irfAmountUSD = irfAmount * exchangeRateAED`.
    - Oman: `irfAmount = irfAmount / exchangeRateOMR`, `irfAmountUSD = irfAmount`.
13. Populate `IRFResultVo` (`irdSerNumber`, `irdCode`, `irfPercentage`, `irfFixed`,
    `irfAmount`, `irfAmountUSD`, `domIntlFlag`, `cardType`, `gcmsProductID`, `irfDesc`,
    `irfMinAmount`, `irfMaxAmount`).
14. **No valid BIN** → zeroed result, `irdCode="85"`, desc `"Crypt API Failed.Invalid cardnumber"`.

### 5.1 `getLimitIndicator` (current, after 2026-08-15 changes)

```
UAE ('U'):
  mcc 6513  && txnAmount < 1000            -> 'B'
  mcc 6051  && txnAmount <= 1000           -> 'B'
  card C   && txnAmount < 10000 && mcc in {4468,5013,5511,5521,5531,5532,5533,
                                           5551,5561,5571,5592,5599,7531,7534,7535,7538} -> 'B'
  ird 61   && mcc 9999   && txnAmount < 15000  -> 'B'
  ird 61   && mcc 6513   && txnAmount < 5000   -> 'B'
Oman ('O'):
  ird 61   && mcc 9999   && txnAmount < 5000   -> 'B'     (was < 15000 before 2026-08-15)
default -> 'A'
```

### 5.2 Capability normalisation helpers (MC audit row)

- `getCardInputCapability`: `0,1,2,6`→same; `3→'M'`; `4→'A'`; `5→'D'`; `7→'B'`;
  `8→'C'`; `9→'5'`; else `'0'`; null→`'0'`.
- `getCardHoldAuthCapability`: `0→'9'`; `1→'1'`; `2→'0'`; `8→'5'`; `4→'6'`; else/null→`'9'`.
- `getCardCaptureCapability`: `0→'0'`; `1→'1'`; else/null→`'9'`.
- `getCardPresent`: `1→'0'`; `0→'1'`; else/null→`'0'`.
- `getCHPresent`: `0..5` as-is; else/null→`'0'`.

---

## 6. The other four schemes

### 6.1 UAE-Switch (`UaeSwitchIrfCalculationService`)

Constant identity: `cardType='D'`, `domIntlFlag='D'`, `gcmsProductID=""`, `irdCode=""`,
`irfCountry=""`.

1. Resolve `UAE_SWITCH_IRF` by `findByMcc(mcc)`; if not found fall back to
   `findBySegment("GENERAL")`. No row → empty result.
2. Rate selection on the resolved row:
   - MCC-specific row → `irfRate` / `irfMax`.
   - Segment row + terminal `POS` → `posIrf` / `posIrfMax`.
   - Segment row + terminal `E-COM` → `ecomIrf` / `ecomIrfMax`.
   - else default (segment row general) rates.
3. `irfAmount = irfFixed + txnAmount * irfPercentage * 0.01`; clamp to `irfMax` when non-zero.
4. Populate result (`irdSerNumber`, `irfAmount`, `irfDesc`, `irfFixed`, `irfMaxAmount`,
   `irfMinAmount`, `irfPercentage`).

### 6.2 Jaywan (`JaywanIrfCalculationService`)

Identical structure to UAE-Switch against `JAYWAN_IRF` (`findByMcc`, `findBySegment("GENERAL")`,
`irfRate`/`posIrf`/`ecomIrf` + maxes). Same constants (`cardType='D'`, `domIntlFlag='D'`).

### 6.3 OmanNet (`OmanNetIrfCalculationService`)

1. Build candidate BINs from first 6,7,8,9 digits; `findByBinNumberInAndSubRoute(bins,
   network.toUpperCase())`; pick the longest matching `OmanNetBinDataEntity`.
2. If BIN found and `route=="MAAL"`: `cardType` from BIN; rate lookup
   `findByRouteAndSubRouteAndCardTypeAndMcc(route=bin.subRoute.toLowerCase(),
   subRoute=bin.route.toLowerCase(), cardType, mcc)`.
3. Else (no valid BIN): `findBySegment("all segment")`.
4. `irfAmount = irfFixed + txnAmount * irfPercentage * 0.01`; clamp to `irfMax` when > 0.
   `domIntlFlag='D'`. Populates `irdSerNumber`, `irfDesc`, `cardType`.

### 6.4 On-us (`OnusIrfCalculationService`)

1. BIN = first 8 digits; `findByBinNumberAndSubRoute(bin, network)`.
2. If BIN found and `route.toLowerCase()=="onus"`: same rate lookup as OmanNet by
   route/sub-route/card-type/MCC. Otherwise no entity (zeros).
3. Same amount formula; `domIntlFlag='D'`.

---

## 7. IRD reference data (tables / views)

### 7.1 `VW_IPM_DETAILS` (IpmDetailsView) — the IRD source

Populated upstream by the MC IRD engine (the view keys on the serial number of the
`MC_IRF_PARAMS` row). The calculator reads it back **after** inserting the params row.

| Column | Entity field | Meaning |
|--------|--------------|---------|
| `MIP_SER_NUMBER` | `txnSerNumber` | FK to `MC_IRF_PARAMS.MIP_SER_NUMBER` |
| `IIC_SER_NUMBER` | `serialNumber` | IRD row id → `irdSerNumber` |
| `IIC_IRD` | `ird` | IRD code (e.g. "85", "61", product codes) |
| `IIC_RATE_PERCENT` | `ratePercent` | rate % |
| `IIC_RATE_FIXED` | `rateFixed` | fixed amount (not consumed by calculator) |
| `IIC_PRIORITY` | `priority` | |
| `IIC_MIN_AMOUNT` | `irfMinAmount` | min cap |
| `IIC_MAX_AMOUNT` | `irfMaxAmount` | max cap |

### 7.2 `MC_IRF_PARAMS` (McIrfParamsEntity)

Audit/param row the engine writes before reading the view. Key = `MIP_SER_NUMBER`
(= txn serial number). Columns: `MIP_INS_CODE`, `MIP_PAN`, `MIP_TXN_AMOUNT`,
`MIP_CASHBK_AMOUNT`, `MIP_TRL_TYPE`, `MIP_TXN_DATE_TIME`, `MIP_MCC`, `MIP_NETWORK`,
`MIP_TXN_TYPE`, `MIP_APPR_CODE`, `MIP_TXN_ID`, `MIP_POS_ENTRY_MODE`, `MIP_SERVICE_CODE`,
`MIP_CARD_INPUT_ABILITY`, `MIP_CH_AUTH_ABILITY`, `MIP_CARD_CAPTURE_ABILITY`,
`MIP_OPRT_ENVIRONMENT`, `MIP_CH_PRESENT`, `MIP_CARD_PRESENT`, `MIP_CARD_INPUT_MODE`,
`MIP_MER_TYPE`, `MIP_MAID`, `MIP_PROG_REGION`, `MIP_TIMELINE`, `MIP_APPR_CODE_FLAG`,
`MIP_MAGSTRIPE_FLAG`, `MIP_TRACE_ID_FLAG`, `MIP_MC_ASSIG_ID_FLAG`, `MIP_ISSUER_REGION`,
`MIP_CARD_PROG_ID`, `MIP_GCMS_PROD_ID`, `MIP_ECOM_INDICATOR`.

### 7.3 `MC_OVERRIDE_RATES` (McOverrideRatesEntity)

Applied only for domestic (`cardDomIntlFlag=='D'`) and international refunds
(`txnType=="20"`, override id `REFUND`). Lookups used:

- `findByIrdAndCardTypeAndMccAndTxnLimitIndicator(ird, cardType, mcc, limit)` — no override id.
- `findByIrdAndOverRideIDAndCardTypeAndMccAndTxnLimitIndicator(ird, overrideId, cardType, mcc, limit)`.
- `findByIrdAndOverRideIDAndMccAndTxnLimitIndicator(ird, overrideId, "R999", 'A')` — refund.

| Column | Meaning |
|--------|---------|
| `MOR_SER_NUMBER` | PK (`morSerNumber`) |
| `MOR_TRL_TYPE` | terminal type |
| `MOR_CARD_TYPE` | card type (C/D) |
| `MOR_OVERRIDE_ID` | e.g. `REFUND` |
| `MOR_IRD` | IRD code |
| `MOR_TXN_LIMIT_IND` | `A`/`B` limit indicator |
| `MOR_MCC` | MCC (or `R999` for refunds) |
| `MOR_PERCENT` | rate % |
| `MOR_FIXED` | fixed amount |
| `MOR_MAX` | max cap |
| `MOR_DESCRIPTION` | description |

If a matching override is found, its `morPercentage`/`morFixed`/`morMax` replace the
view-derived rate.

### 7.4 `MC_PRODUCT_MAPPING` (McProductMappingEntity)

UAE-only card-type override: `MPM_GCMS_ID` + `MOR_IRD` → `MPM_CARD_TYPE`. Lookup
`findByIrdAndGcmsProductId(ird, gcmsProdId)`; when found, `cardCrDrInd = mapping.cardType`.

### 7.5 `MC_ISS_ACC_RANGE` (McIssAcqRangeEntity)

BIN range → card attributes for the MC path. Lookup by `MAR_ISS_RANGE_LOW <= pan <=
MAR_ISS_RANGE_HIGH`, `MAR_ACTIVE_CODE='A'`, ordered by `MAR_PRIORITY_CODE asc,
MAR_EFFECTIVE_DATE desc` (top 1). Consumed: `MAR_GCMS_PROD_ID`, `MAR_CARD_PROG_ID`,
`MAR_COUNTRY_CODE`, `MAR_REGION`.

### 7.6 `VISA_ISS_ACC_RANGE` (VisaIssAcqRangeEntity) + `VISA_IRF_PROGRAMS` (VisaIrfProgramEntity)

- `VISA_ISS_ACC_RANGE`: `findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqual` →
  `VAR_REGION`, `VAR_COUNTRY_ALPHA_CODE`, `VAR_CARD_PRODUCT`, `VAR_DR_CR_CARD_IND`,
  `VAR_PROD_SUB_TYPE`.
- `VISA_IRF_PROGRAMS`: rate rows keyed by `VRF_REGION`, `VRF_CARD_TYPE`, `VRF_FP_TYPE`,
  `VRF_FP_VALUE` (card product), `VRF_TXN_LIMIT_IND`, `VRF_QUALIFIER_IND`, optional
  `VRF_MCC`; rates in `VRF_PERCENT`, `VRF_FIXED`, `VRF_MAX`, `VRF_MIN`; label `VRF_FP_DESC`.
  Lookups: the four `findByRegionAndCardTypeAndFpType...` variants in `VisaIrfProgramRepo`.

### 7.7 `UAE_SWITCH_IRF`, `JAYWAN_IRF`, `OMAN_NET_IRF`, `OMANNET_BIN_DATA`

- `UAE_SWITCH_IRF` (`URF_*`) / `JAYWAN_IRF` (`JRF_*`): `SEGMENT`, `SEGMENT_DESC`, `MCC`,
  `MCC_DESC`, `IRF_RATE`, `IRF_MAX`, `IRF_FIXED`, `POS_IRF`, `ECOM_IRF`, `POS_IRF_MAX`,
  `ECOM_IRF_MAX`.
- `OMAN_NET_IRF` (`ONI_*`): `ROUTE`, `SUB_ROUTE`, `CARD_TYPE`, `SEGMENT`, `SEGMENT_DESC`,
  `MCC`, `MCC_DESC`, `IRF_PERCENTAGE`, `IRF_FIXED`, `IRF_MAX`.
- `OMANNET_BIN_DATA` (`OBN_*`): `ROUTE`, `SUBROUTE`, `BIN_NUMBER`, `CARD_TYPE`, `GEN_STATUS`.

---

## 8. Result VO (`IRFResultVo`) — field semantics

| Field | Produced by |
|-------|-------------|
| `irdSerNumber` | MC: `IIC_SER_NUMBER`; Visa: `VRF_SER_NUMBER`; UAE-Switch/Jaywan/OmanNet/On-us: rate row serial |
| `morSerNumber` | MC only, when an override row matched |
| `irdCode` | MC: `IIC_IRD` (default "85"); Visa: card product; others: empty |
| `irfPercentage` | selected rate % |
| `irfFixed` | selected fixed amount |
| `irfAmount` | computed (already converted for MC AED/OMR) |
| `irfAmountUSD` | amount in USD (MC: converted; Visa: amount × rate) |
| `domIntlFlag` | `'D'`/`'I'` per scheme routing |
| `cardType` | `'C'`/`'D'` (or `'P'` for Visa prepaid) |
| `gcmsProductID` | MC only (from `MC_ISS_ACC_RANGE`) |
| `irfDesc` | program/segment description or override note |
| `irfCountry` | Visa: alpha country code; others: empty |
| `irfMinAmount` / `irfMaxAmount` | min/max caps (MC from view; Visa from program row) |

Common formula across all schemes: `irfAmount = fixed + txnAmount * percent * 0.01`,
clamped to `[min, max]`.

---

## 9. Callback flow (`IRF_CALLBACK`)

Table `IRF_CALLBACK` (`ICB_*`) columns: `ICB_SER_NUMBER`, `ICB_LAST_UPDATED`,
`ICB_UPDATED_USER`, `ICB_INS_CODE`, `ICB_JOB_NUMBER`, `ICB_STATUS` (`'P'`=pending,
`'S'`=sent, `'E'`=error), `ICB_REF_SER_NUMBER`, `ICB_REQUEST`, `ICB_RESPONSE`,
`ICB_SCHEME_INCHG_FLAG`, `ICB_CP_MID`, `ICB_UNIQUE_ID`, `ICB_IRD_CODE`, `ICB_FIXED`,
`ICB_PERCENTAGE`, `ICB_IRF_AMOUNT`, `ICB_TXN_AMOUNT`, `ICB_RRN`, `ICB_MTI`,
`ICB_DOM_INTL_FLAG`, `ICB_IS_CREDIT`, `ICB_CARD_CLASSIFICATION`.

Two strategies (kept behind `IRFCallbackSender`):

- **TLF**: synchronous POST inside the Kafka listener thread (dominant ~2.1 s/txn latency).
- **MPGS**: row persisted PENDING, bulk-sent later by job.

`LocalIRFCallbackSender` (server side, owns the table):
- `enqueue(IrfResultData)` → persist PENDING row, return serial.
- `flush(insCode, jobNumber)` → send pending rows to `irf.callback.target.url`, set `'S'`/`'E'`.
- `retry(refSerNumber)` → re-send one row by serial.

`HttpIRFCallbackSender` (client side) POSTs to `/irf/v1/callback[/flush|/retry]`.
`IrfResultData` carries the callback payload fields (cpMid, uniqueId, irdCode, fixed,
percentage, irfAmount, txnAmount, rrn, mti, domIntlFlag, isCredit, cardClassification,
insCode, refSerNumber).

---

## 10. Configuration

`irf-service/src/main/resources/application.yml` (dev H2 by default; Oracle on UAT):

- `server.port: 8085`
- `spring.datasource` H2 in-memory (dev), JPA `ddl-auto: update`
- `irf.service.sec: ${IRF_SERVICE_SEC:change-me}` — shared secret (query param `sec`)
- `irf.service.calculators` — network → bean-name overrides
- `irf.exchange-rate-aed: 3.67`
- `irf.exchange-rate-omr: 0.38`
- `irf.exchange-rate: 1.0`

Consumers (`TLF`, `MPGS`) must set `irf.service.url` and `irf.service.sec` when using the
REST clients.

---

## 11. Mastercard gap changes applied (2026-08-15)

Code changes already applied to `McIrfCalculationService.java` and compile-verified:

1. **Oman "All Other Products" bracket threshold**: `getLimitIndicator` Oman branch now
   returns `'B'` for `ird 61 + mcc 9999 + txnAmount < 5000` (was `< 15000`) — new manual
   p. 277: the 0.50% commercial bracket now applies above USD 5,000. (line ~327)
2. **Oman commercial special case regex**: `"MEO|MCO|MWO|MAB"` → `"MEO|MCO|MWO|MAB|MIO"`
   (new manual publishes an explicit Oman MIO row; MIO now treated as a "9999" product
   bucket for the override lookup). (line ~232)

Outstanding **data** changes (`MC_OVERRIDE_RATES`, `MC_PRODUCT_MAPPING`, `MC_ISS_ACC_RANGE`)
and items requiring Mastercard confirmation (tokenized-vs-non-tokenized rates, World
Legend tiers, UAE MIO, UAE IRD 74 retirement, TM/PM/WM/EM lifecycle, Dragon Mart/Night
Market, UAE Table 224) are tracked in `MC_IRF_GAP_ASSESSMENT.md`. Confirmed **non-changes**:
Oman World Elite rates, UAE credit matrix, UAE debit/prepaid rates.