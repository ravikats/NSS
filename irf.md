# MC IRF / IRD Implementation Review — Server DB Validation (Aug 2026)

**Date:** 2026-08-20
**Scope:** Review the IRF (interchange fee) and IRD (interchange rate designator) implementation against the Mastercard Interchange Manual MEA edition and live server data.

## Sources

- **Manual:** `mc_InterchangeManualMEACustomer_04th_Aug_26/full.md` (57,556 lines) — ground-truth for IRD criteria and rate tables.
- **Server DB:** Oracle RDS `switch-uat.c3guuusy8mm5.me-central-1.rds.amazonaws.com:1521/ORCL`, schema `NETWORK_SETTLEMENT_UAT` (from server `tlf_application.properties`).
- **Engines:**
  - `tlf-finalize` (Module B): decompiled copy `com/empay/common/functions/UAEMcIRFCalculation.java` — used via `TxnProcessingService.fetchIrf` (MCI/MDS → `uaeMcIrf.getMcIrfUAE`).
  - `irf-service`: port `com/empay/irfservice/calculator/McIrfCalculationService.java`.
- **Prior gap doc:** `MC_IRF_GAP_ASSESSMENT.md` (2026-08-15).

## How the engine works (recap)

The IRD code, rate %, min/max, and AED/USD caps come from the **upstream IPM engine** via `VW_IPM_DETAILS` (`IpmDetailsView`). The MC engine only *selects* the IRD by inserting `MC_IRF_PARAMS` and re-reading the view. `MC_OVERRIDE_RATES` (`getDomOverRide`) applies **only** for domestic (`cardDomIntlFlag=='D'`) txns and international refunds (`txnType=="20"`, override id `REFUND`). `MC_PRODUCT_MAPPING` overrides card type (`C`/`D`) for **UAE only**. Product-code qualification per IRD is mostly **data** (`MC_ISS_ACC_RANGE`, `MC_PRODUCT_MAPPING`, `MC_OVERRIDE_RATES`) with a small amount of hard-coded logic.

## 1. Engine divergence — tlf-finalize copy is STALE

The gap fixes from `MC_IRF_GAP_ASSESSMENT.md` §1.1/1.2 were applied to the **irf-service port only**, NOT to the decompiled copy tlf-finalize actually runs.

| Item | `UAEMcIRFCalculation.java` (tlf-finalize) | `McIrfCalculationService.java` (port) | Gap doc ref |
|---|---|---|---|
| Oman 61/9999 threshold | `15000.0` (line 303) | `5000.0` (line 327) | 1.1 |
| Oman product regex | `MEO|MCO|MWO|MAB` (line 203) | `MEO|MCO|MWO|MAB|MIO` (line 232) | 1.2 |

**Impact:**
- Oman "All Other Products" txns between USD 5k–15k get **2.00%** in tlf-finalize instead of **0.50%**.
- Oman `MIO` product never matches the special case (falls to "All Other Products" path).

**Action:** sync the tlf-finalize copy to match the port (`15000.0` → `5000.0`; add `MIO` to regex).

## 2. Data gaps — verified against server RDS

### 2.1 No MIO override row (gap doc 2.2 NOT applied)
- `MC_OVERRIDE_RATES` has **no `mcc=MIO` row** even though the port now regexes for MIO.
- Manual publishes Oman MIO **2.15%** (full.md:8665) and UAE MIO **2.15%** (full.md:12367).
- As-is: Oman MIO → Check3 → 9999 Genl 0.50%/2.00%; UAE MIO (via `MC_PRODUCT_MAPPING`) → 9999 too.
- `MC_PRODUCT_MAPPING` IRD 61 rows: BPD, MAB, MBD, MCB, MCF, MCO, MCP, MDB, MDP, MDT, MEB, MEO, MES, MLA, MNF, MPW, MRK, MRW, MWB, MWO — **MIO missing** despite manual listing MIO for both UAE and Oman IRD 61.
- `MC_ISS_ACC_RANGE` **has** MIO (44 rows).

**Action:** add `MC_OVERRIDE_RATES` row `(ird=61, mcc=MIO, limitIndicator='A', percent=2.15, fixed=0, max=999999)` for card types C/D/P; add MIO to `MC_PRODUCT_MAPPING` for UAE.

### 2.2 MAB override rate wrong
- DB: `61 C A MAB = 2.15` (also D and P rows).
- Manual: MAB = **2.20%** in both Oman (full.md:8664) and UAE (full.md:12369).
- Every MAB override row is off by **5 bps**.

**Action:** update MAB rows `2.15` → `2.20`.

### 2.3 No country dimension in MC_OVERRIDE_RATES
Table columns confirm: `MOR_SER_NUMBER, MOR_LAST_UPDATED, MOR_UPDATED_USER, MOR_TRL_TYPE, MOR_CARD_TYPE, MOR_OVERRIDE_ID, MOR_IRD, MOR_TXN_LIMIT_IND, MOR_MCC, MOR_PERCENT, MOR_FIXED, MOR_MAX, MOR_DESCRIPTION` — **no country column**.

- Oman GvtSvc = **0.70%** (full.md:8660-8667); UAE GvtSvc = **0.50%** (full.md:12364-12369). DB rows store **0.50%** → Oman GvtSvc underpriced.
- Oman commercial table has only 4 columns (Genl / GvtSvc / Chrtes / Whole); ComEM/ComTCS/ComRelEs/Petrol do **not exist** for Oman — any shared override row for those MCCs misprices Oman (e.g. ComEM 0.80% applied to Oman where "general rates apply").
- Whole category values differ too (Oman Whole 2.00%<15k/0.75%> vs UAE Whole 2.00%<15k/0.75%> — these match, but ComRelEs 6513 rows 0.65/2.00 are UAE-only in the manual).

**Action:** determine whether Oman needs country-scoped override rows (add a country discriminator or a parallel table), or confirm Oman commercial transactions are out of scope for the override path.

### 2.4 Wholesale (Whole) category not modeled
- Manual has Oman Whole rows (full.md:8661-8665): MCB/MDT 2.00% below $15k / 0.75% above, MEB 2.10%, BPD/MWB 2.15%, MIO 2.15%.
- Manual has UAE Whole rows (full.md:12365-12368).
- **No wholesale MCC rows exist in `MC_OVERRIDE_RATES`** → wholesale MCC txns price as 9999 Genl (0.50%/2.00%).

### 2.5 Default fallback `85` / 2.5%
- `McIrfCalculationService.java:205-206`: when irdCode is null (MDS network / no IPM match), forces `irdCode = "85"; irfPercentage = 2.5`.
- Manual UAE Consumer Standard (75/85/95) Genl = **1.30%** credit / **1.00%** debit (full.md:12091, 54666). **2.5% matches no published rate.**
- Server DB: 381 MCI rows stored at 85/2.5 (the fallback); plus 7 at 85/0 and 2551 at 85/1.0.

### 2.6 Lifecycled product codes still present
- `MC_ISS_ACC_RANGE` still carries **MRC / MKA / MKD** (gap doc 2.5 — remove). **MXG / MXP** absent (gap doc 2.3/2.4 — add).

## 3. What matches (verified)

- UAE 61/9999 split: below $15k → 2.00%, above → 0.50% matches UAE "All Other Products" (full.md:12381-12384).
- GvtSvc / ComEM / ComTCS / Petrol / ComRelEs / Chrtes UAE commercial values match the manual for the rows that exist.
- `MC_ISS_ACC_RANGE`: UAE (784) region E 1,451 rows, Oman (512) region E 156 rows, US (840) region '1' 49,911 rows.
- `IPM_IRD_CODES` region I (UAE/Oman domestic): IRDs 24, 47, 61, 73, 74, 75, 79, EE-ES, IP, PE-PS, TE-TS, UE-UI, WE-WS, YA-YZ/ZX. **No 83, 85, or 95 rows** → Consumer Standard only reachable as IRD 75 (1.40%).
- MCI IRF distribution in POS_TRANSACTIONS: 61 (0.5/0.8/2), 63 (2), 73 (0.5/1), 74 (0, 7263 rows), 75 (0.5/0.65/1/1.05/1.3), 79, 85 (0/1/2.5), EF, ES, PE, PF etc.

## 4. Recommended next steps

1. **Code:** sync tlf-finalize `UAEMcIRFCalculation.java` to the port (Oman 5000.0, MIO in regex) — §1.
2. **Data:** add MIO override row + MIO product mapping (§2.1); fix MAB 2.15→2.20 (§2.2); add Whole MCC rows (§2.4); add MXG/MXP and remove MRC/MKA/MKD from `MC_ISS_ACC_RANGE` (§2.6).
3. **Design decision:** country-scoped override rows for Oman vs UAE (§2.3) — confirm scope with Mastercard/account team before modeling.
4. **Verify:** confirm whether the 2.5% default fallback (§2.5) should be replaced with the manual's Standard Genl rate (1.30% credit / 1.00% debit).

---

# 5. Existing IRF — Mastercard & Visa analysis from server DB (2026-08-20)

Source: Oracle RDS `POS_TRANSACTIONS` (149,540 rows total), networks `MCI` (46,963 rows) and `VISA` (100,817 rows). Rows without an IRD are status 2/3/4/6 (reversed / GEN_PENDING / staged / settled) — the IRF-bearing rows are the finalized subset.

## 5.1 Headline numbers

| Network | Rows | Rows w/ IRD | Rows w/ IRF>0 | Sum IRF (AED) | Avg % | Distinct IRDs |
|---|---|---|---|---|---|---|
| MCI | 46,963 | 25,376 | ~25,300 | 4,308,695 | 0.428 | 34 |
| VISA | 100,817 | 50,903 | ~50,900 | 252,262 | 0.585 | 23 |

Notes:
- MCI IRF is dominated by **interregional** transactions (11,018 rows, 3.47M IRF); domestic (14,352 rows) contributes only ~41,783 IRF.
- VISA IRF is split domestic (5,827 rows, ~92,784 IRF) vs interregional (45,076 rows, ~159,479 IRF).

## 5.2 MCI IRD top contributors (by sum IRF)

| IRD | Meaning | Rows | Sum IRF | Avg % |
|---|---|---|---|---|
| YD | Interregional Consumer Rate II, CP, Core — 1.10% (manual p.479/2038) | 10,002 | 3,472,542 | 1.0995 |
| 85 | UAE Consumer Standard fallback (default `irdCode="85"`, 2.5%) | 2,944 | 780,254 | 1.194 |
| ES | — | 470 | 11,956 | 1.334 |
| WS | — | 976 | 10,187 | 1.074 |
| PS | — | 755 | 6,417 | 1.037 |
| 61 | UAE Commercial Standard | 133 | 6,333 | 1.811 |
| YG/YI | Interregional premium tiers (1.6/1.69, 1.98/2.15) | 228/219 | 5,512/4,179 | 1.607/1.953 |

Key observations:
- **YD at 1.10% matches the manual exactly** — this is the dominant MCI IRD (80% of total IRF) and is consistent with the "Interregional Consumer Rate II, Card Present, Core" rate.
- **IRD 85 at 2.5% is the hard-coded default fallback** (§2.5). Of the 2,944 rows, 2,551 are `D D` @ 1.0% (MCC 0940, ~280K sum txn) and 385 are international @ 2.5% (mostly test rows 37-53 + a handful of large 8299/6051 rows). The 2.5% fallback rate does not appear in any published manual rate.
- Domestic consumer IRDs (75/79/EF/ES/PE/PF/PS/TE/TF/TS/WE/WF/WS) carry rates 0.5–2.2% consistent with the manual tables.
- **IRD 74 (UAE Electronic) has 7,263 rows all at 0%** — consistent with the gap-doc 1.5 finding (74 retired from criteria; only reachable via MoneySend tables). Those rows are MCC 9399, `D D`, small ticket (~AED 20-90).
- `MC_OVERRIDE_RATES` IRD 61 C/D/P rows: ComEM 0.80, ComTCS 0.50, GvtSvc 0.50, Petrol 0.50, ComRelEs 0.65(A)/2.00(B), Chrtes 0.25 fixed, Genl 9999 A 0.50 / B 2.00, MAB 2.15 (wrong — should be 2.20), MEO/MCO/MWO 2.00, R999 refund 1.8.

## 5.3 VISA IRD top contributors (by sum IRF)

| IRD | Program (VISA_IRF_PROGRAMS desc) | Rows | Sum IRF | Avg % |
|---|---|---|---|---|
| P | GOLD (STD GOLD NN) | 59 | 134,929 | 1.363 |
| I | INF (STD INF NN / INF Q NN) | 1,638 | 39,153 | 2.020 |
| N | PLAT (STD PLAT NN / PLAT Q NN) | 2,052 | 20,860 | 1.220 |
| C | SIG (STD SIG NN / SIG Q NN) | 890 | 14,957 | 1.571 |
| G3 | BUS PLAT (STD BUS PLAT NN) | 173 | 13,367 | 2.037 |
| F | CLAS (STD CLAS NN) | 44,357 | 12,807 | 1.101 |
| N1 | RWD (STD RWD NN) | 973 | 6,437 | 1.188 |
| G | BUS (STD BUS NN) | 112 | 5,504 | 2.005 |

Key observations:
- **VISA IRD codes map to Visa program values** (`VRF_FP_VALUE`): C=SIG, F=CLAS, F2=FLEX, G=BUS, G1=BUS SIG, G3=BUS PLAT, G5=BUS RWD, I=INF, I2=UHNW, K=CORP, N=PLAT, N1=RWD, P=GOLD, S=PUR.
- The bulk of VISA volume (44,357 rows, IRD F/CLAS) is priced at **1.10%** — matches `BASE-FEE` NON PREMIUM CARD 1.1% (region R) rows in `VISA_IRF_PROGRAMS`.
- The **P (GOLD) 1.65% block on MCC 6051** (exchange houses, ~AED 900K each) generates 134,729 IRF from just 28 rows — the single largest VISA IRF concentration. Program rows show GOLD STD 1.2% (ACQ-DGR) / 1.15% (PROD-RATE) — the stored 1.65% needs verification against the OMAN/UAE GOLD rate tables.
- **VISA also carries the IRD 85 @ 2.5% default fallback** (29 rows, C I) — same concern as §2.5/5.2.
- VISA domestic (D) rows use rates 0.5–2.1% (CP DEBIT/PREPAID caps: F 0.75 max 37.50, C 1.0 max 50, etc. from `VISA_IRF_PROGRAMS` region I CP rows).

## 5.4 Cross-network observations

1. **Default fallback (85 @ 2.5%) is contaminating both networks** — ~385 MCI + 29 VISA international rows. For MCI this drives 780K AED of the 4.3M total. It never matches a published rate (§2.5).
2. **MAB override rate (2.15) is 5 bps low** for both UAE & Oman commercial (§2.2).
3. **VISA GOLD stored at 1.65% vs program-table 1.2/1.15%** — largest single VISA IRF block; verify which table/downgrade path yields 1.65%.
4. Both networks rely heavily on interregional rates (MC YD 1.10%, VISA F 1.10%) which match their published base rates; the divergence risk is concentrated in the override/default paths, not the view-driven interregional rates.

---

# 6. IRF/IRD parameters & worked example (2026-08-20)

Two stages:
1. **IRD derivation** — happens *upstream* in the IPM engine. The MC engine inserts a `MC_IRF_PARAMS` row and re-reads `VW_IPM_DETAILS`, which returns the winning **IRD code + rate% + min/max**.
2. **Rate/amount finalize** — the engine then applies `MC_OVERRIDE_RATES` (domestic / intl-refund only) and computes `irfAmount`.

## 6.1 Parameters needed

**From the card (PAN lookup → `MC_ISS_ACC_RANGE`):**
- `cardNumber` (PAN) → `gcmsProductId`, `cardProgId`, `countryCode`, `issuerRegion`
- Derived: `cardCrDrInd` (D if cardProgId `MSI|DMC`, else C), `progRegion` (I = issuer country matches acquirer; E = intraregional; R = interregional), `cardDomIntlFlag` (D when `countryCode` == acquirer country else I)

**From the transaction (`IrfTxnData` / `SwitchExtractVo`):**

| Param | Purpose |
|---|---|
| `acqInstConCode` | country: 784→UAE, 512→Oman |
| `network` | MCI/MDS/VISA/etc. |
| `txnCode` | processing code (00, 20, 05…) |
| `mcc` | merchant category |
| `txnAmount`, `setlAmount` | amount basis |
| `posEntryMode` | magstripe flag (starts 02/90/05/07/08) |
| `approvalCode` | approval-code flag |
| `txnId` | trace-ID flag |
| `maid` | MC-assigned ID flag |
| `serialNumber` | key for `VW_IPM_DETAILS` re-read |
| `cardInputAbility`, `chAuthAbility`, `cardCaptureAbility`, `oprtEnvironment`, `chPresent`, `cardPresent`, `cardInputMode`, `meCategoryType`, `terminalType`, `txnDateTime`, `serviceCode`, `motoEcomIndicator`, `cashBackAmount` | IPM criteria fields written to `MC_IRF_PARAMS` |

**Config:** `exchangeRateAED`, `exchangeRateOMR` (env).

## 6.2 Worked example — serial 18922 (real MCI row)

Row: `MCI, IRD 61, 2.00%, fixed 0, amount 101.097, C, D, txnCode 00, MCC 7538, txn 5013.75 AED, setl 1364.81`

1. Acquirer `784` → `countryCodeFlag='U'`. PAN range matches a UAE issuer → `progRegion='I'`, `cardDomIntlFlag='D'` (domestic).
2. Card program `MSI/DMC`? No → `cardCrDrInd='C'`, `txnAmount = setlAmount = 1364.81`.
3. IPM view returns IRD **61** (UAE Commercial Standard, MCC 7538 = vehicle-repair MCC in the commercial AB-program list), `ratePercent=2.0`.
4. Domestic (`D`) → override path:
   - `getLimitIndicator(1364.81, "7538", "61", 'C', 'U')`: MCC 7538 is in the vehicle list `…7531|7534|7535|7538` and `1364.81 < 10000` → **'B'**.
   - Check1 `getDomOverRide(61, C, 7538, 'B')` → no row → Check2 `(61, C, gcmsProdId, 'B')` → no row → Check3 `(61, C, "9999", 'B')` → row `61 C B 9999 = 2.00%`.
5. `irfAmount = 0 + 1364.81 × 0.02 = 27.2962` USD. UAE `C` branch: `irfAmount(AED) = 27.2962 / 0.27 = 101.097` ✓ matches stored `101.097037`.

## 6.3 When the override path is NOT taken

- **Interregional** (e.g. MCI YD 1.10%, VISA F 1.10%): `cardDomIntlFlag='I'`, `txnCode != "20"` → no override; rate comes straight from `VW_IPM_DETAILS`. That's why YD/F match the published base rates exactly.
- **International refunds** (`txnCode="20"`): uses `REFUND/R999` rows.
- **No IPM match / MDS network**: `irdCode` stays null → hard-coded fallback `85 @ 2.5%` (§2.5 — the contamination seen in the DB).

---

# 7. VISA IRF walkthrough (2026-08-20)

Unlike MC, VISA does **not** use the IPM view. IRD derivation + rate selection happen entirely in `VisaIrfCalculation` against `VISA_IRF_PROGRAMS` (fees programs) + `VISA_ISS_ACC_RANGE` (BIN table). The "IRD code" is simply the card product code (F=CLAS, P=GOLD, C=SIG, …).

## 7.1 Parameters needed

**From the POS row (`PosTransactionEntity`):**

| Param | Source | Purpose |
|---|---|---|
| `acqInstConCode` | `PTR_ACQ_INST_CON_CODE` | 784→'U' (UAE), 512→'O' (Oman), else abort |
| `encCardNumber` | PAN (encrypted) | BIN lookup; `cardNumber` truncated to 9 digits for range match |
| `txnDateTime` | `PTR_TXN_DATE_TIME` | timeline (business days vs now) |
| `txnAmount` | `PTR_TXN_AMOUNT` | amount basis (AED/OMR) |
| `mcc` | `PTR_MCC` | merchant category |
| `feePgmIndicator` | `PTR_FEE_PRG_INDICATOR` | fee program indicator |
| `trlCapabilities` | `PTR_TRL_CAPABILITIES` | terminal capability |
| `approvalCode` | `PTR_APPROVAL_CODE` | auth-code length check (6 = preferred) |
| `responseCode` | `PTR_RESPONSE_CODE` | Y1/Y3 check |
| `motoEcomIndicator` | `PTR_MOTO_ECOM_INDICATOR` | CP vs CNP discrimination |
| `posEntryMode` | `PTR_POS_ENTRY_MODE` | left 2 chars; chip list `02|03|05|06|07|90|91|95` |
| `reImbursementAttribute` | `PTR_REIMB_ATTRIBUTE` | default 'B' (UAE); Oman passes through |
| `mvv` | `PTR_MVV` | — |

**From the BIN (`VISA_ISS_ACC_RANGE`, matched via `VAR_ISS_RANGE_LOW <= PAN9 <= VAR_ISS_RANGE_HIGH`):**

| Column | Mapping in code |
|---|---|
| `VAR_REGION` | `issuerRegion`; '6' → progRegion 'E', else fallback 'R' |
| `VAR_COUNTRY_ALPHA_CODE` | `countryCode` (AE/OM/…) |
| `VAR_CARD_PRODUCT` | `cardProduct` (default `"AO"`); also the IRD code |
| `VAR_DR_CR_CARD_IND` | `R`→'D' (debit), `H`→'C' (credit), else pass-through |
| `VAR_PROD_SUB_TYPE` | `TK` → qualifier 'Q', else 'N' |

**Derived:** `cardDomIntlFlag` (AE+U or OM+O → 'D' else 'I'), `progRegion` (domestic→'I', issuerRegion '6'→'E', else 'R'), `crDrIndicator`, `txnLimitIndicator` (default 'A'), `qualifierIndicator` (Q/N), `reimbAttribute` ('B' default), `exchangeRate` (env `exchangeRateAED`/`exchangeRateOMR`), `timeline` (business days between txn and now, skipping Sundays + Dec 25 — `getVisaTimeLines`).

## 7.2 Flow

1. `getVisaIrf` → set country flag, defaults, BIN lookup (no BIN → zero-IRF result).
2. Dispatch by `progRegion` + country:
   - `I`+U → `uaeIrfCalculation`; `I`+O → `omanIrfCalculation`; `E` → `meaIrfCalculation`; `R` → `interationalIrfCalculation`.
3. **UAE debit/prepaid** (`crDr` D or P): try in order —
   `INDUSTRY FEE PROGRAM` by MCC (no product) → `CP` by product if `posEntryMode` in chip list **and no moto/ecom** → `CNP` by card type (no MCC/product). First hit wins.
4. **UAE credit**: if `timeline <= 3` — petrol IFP (MCC 5541/5542, reimb 'B') → auto limit 'B' if MCC 5511/5521 and `amount×rate ≤ 10000` → IFP by MCC+product → 'B' if F2 & `< 200` → `PROD-RATE` (reimb 'B', chip pos) → `ALT-RATE` (pos 01|10); then `ACQ-DGR` by product. First hit wins.
5. **Interregional (`interationalIrfCalculation`)**: if reimb 'B', chip pos, (auth len 6 or resp Y1/Y3), timeline ≤ 3 → `BASE-FEE` by product; else non-chip + auth6 → `ALT-FEE`; else `ACQ-DGR` by product; final fallback **`UNCAT` / product `"UN"`**.
6. `getVisaIrfRate_uae` / `getVisaIrfRate` / `getVisaIrfRate_oman`: query `VISA_IRF_PROGRAMS` by `region + cardType + fpType + [mcc] + [cardProduct] + txnLimitIndicator + qualifierIndicator`, falling back to `issuerRegion` as the limit indicator if no row. Compute:
   `irfAmount = irfFixed + txnAmount × irfPercent × 0.01`, clamped to `[irfMin, irfMax]`; `irfAmountUSD = irfAmount × exchangeRate`.
   - Petrol MCC 5511/5521: `irfFixed *= exchangeRate`; MCC 5541/5542: `irfMin *= exchangeRate` (UAE).
   - MCC 5511/5521, card C/F/Q/P/I/I2/N/N1/F2, Oman: max capped in USD (`irfDollarAmount`).
   - Interregional "Industry Progarmme Others" MCC 5511/5521 limit 'A': `txnAmount -= 10000`.

## 7.3 Worked examples (real DB rows)

**Ex 1 — serial 8946 (interregional, the dominant F/1.1% block):**
`VISA, IRD F, 1.1%, amount 1.21, C, I, MCC 9399, txn 110.0, setl 29.942, pos 05, moto 051`

1. `acqInstConCode=784` → countryFlag 'U'. PAN9 → BIN: `VAR_REGION` ≠ '6' → `progRegion='R'`, `cardDomIntlFlag='I'`, `cardProduct='F'`, qualifier 'N'.
2. Dispatch → `interationalIrfCalculation`. reimb 'B', pos "05" in chip list, auth/response qualifies, timeline ≤ 3 → **`BASE-FEE`** lookup: region R, card C, fpValue F, limit 'A' → no row (rows have limit 2/3/4/5) → fallback to `issuerRegion` → row 1853 `R C BASE-FEE F 1.1% max 999999`.
3. `irfAmount = 0 + 110 × 0.011 = 1.21` AED ✓ matches stored `1.21`; USD = `1.21 × 0.27 ≈ 0.33` (setl 29.942 = 110 × 0.2722 actual FX).

**Ex 2 — serial 18863 (UAE domestic debit → CNP):**
`VISA, IRD F, 1%, amount 4.0, D, D, MCC 5992, txn 400.0, pos 05, moto 051`

1. countryFlag 'U'; BIN → AE issuer → `cardDomIntlFlag='D'`, `progRegion='I'`, `crDr='D'` (VAR_DR_CR_CARD_IND='R'→D), `cardProduct='F'`.
2. Dispatch → `uaeIrfCalculation`. Debit path: `INDUSTRY FEE PROGRAM` by MCC 5992 → **no row** (5992 not in IFP list). `CP` requires chip pos **and null moto** — moto=051 present → skipped. `CNP` (no MCC/product) → row 1703 `I D CNP 1% max 50`.
3. `irfAmount = 0 + 400 × 0.01 = 4.0` AED ✓ matches stored `4.0`. Had it been pure card-present (no moto) it would have hit `CP` row 1694 `F 0.75%` → 3.0.

**Ex 3 — the P/GOLD 1.65% block (28 rows, 134,729 IRF):**
Interregional credit with `cardProduct='P'`; `BASE-FEE`/`ALT-FEE` miss → **`ACQ-DGR`** lookup falls back to `issuerRegion` = 3/4/5 → rows 2118/2133/2135 `R C ACQ-DGR P 1.65%`. This is the largest VISA IRF block and **needs verification against the manual** (program tables show GOLD 1.2% ACQ-DGR / 1.15% PROD-RATE for region I) — see §5.4.

## 7.4 Key differences vs MC

- No IPM view / no `MC_IRF_PARAMS` insert — rate source is purely the fees-program table.
- IRD code = card product (not an interchange designation); unknown products default to `"AO"`.
- Domestic + debit follow a strict program ladder (IFP→CP→CNP); credit adds PROD-RATE/ALT-RATE/ACQ-DGR; interregional uses BASE-FEE/ALT-FEE/ACQ-DGR/UNCAT.
- Fallback is explicit: `UNCAT`/`"UN"` (not a hard-coded 85/2.5% like MC), though both can still yield no row → zero-IRF result.
- Amount basis is always `txnAmount` (local currency); USD conversion via `exchangeRate` env for reporting only.