# Mastercard IRF — Gap Assessment vs. UAE/Oman Implementation Guide

**Date:** 2026-08-15
**Source guide:** `UAE_Oman_Implementation_Guide.md` (Mastercard *Interchange Manual — Middle East/Africa*, old ed. 3 Feb 2026, 2,089 pp → new ed. 4 Aug 2026, 2,058 pp)
**Engine under review:** `irf-service` → `McIrfCalculationService` (`src/main/java/com/empay/irfservice/calculator/McIrfCalculationService.java`), the port of TLF `UAEMcIRFCalculation` (`tlf-processing-service-java/com/empay/common/functions/UAEMcIRFCalculation.java`). Decompiled constants verified identical to the port (15000/5000/10000, `MEO|MCO|MWO|MAB`, `9999`, `6513`, `6051`).

**How the engine works (relevant context):** the IRD code, rate %, min/max, and AED/USD caps come from the **upstream** IPM engine via `VW_IPM_DETAILS` (`IpmDetailsView`, read by `ipmDetailsViewRepo.findByTxnSerNumber`). The engine only *selects* the IRD by inserting `MC_IRF_PARAMS` and re-reading the view. `MC_OVERRIDE_RATES` (`getDomOverRide`) is applied **only** for domestic (`cardDomIntlFlag=='D'`) transactions and international refunds (`txnType=="20"`, override id `REFUND`). `MC_PRODUCT_MAPPING` overrides card type (`C`/`D`) for **UAE only**. Product-code qualification per IRD is therefore mostly **data** (`MC_ISS_ACC_RANGE`, `MC_PRODUCT_MAPPING`, `MC_OVERRIDE_RATES`), with a small amount of hard-coded code. That split drives the gap list below.

---

## 1. Code changes (hard-coded in `McIrfCalculationService`)

### 1.1 Oman commercial "All Other Products" bracket threshold: 15,000 → 5,000 — CONFIRMED
- Guide: Part A / Part B.2 (new manual p. 277) — the "All Other Products" row split drops from *above USD 15,000* to *above USD 5,000* for the 0.50% rate. Confirmed directly against both manuals' ground-truth text.
- Engine: `getLimitIndicator` Oman branch, `McIrfCalculationService.java:327` (`UAEMcIRFCalculation.java:303`):
  ```java
  else if (countryCodeFlag == 'O' && morIrd.equals("61") && "9999".equals(mcc) && txnAmount < 15000.0)
  ```
  The `15000.0` constant for the `9999`/All-Other-Products path must become `5000.0`.
- **Caveat:** the per-product Wholesale rows (MCB/MDT/MEB/BPD/MWB) keep their **USD 15,000** split in the new manual — only the "All Other Products" 0.50% tier moved. The override rows in `MC_OVERRIDE_RATES` for `9999` must be updated to the 5,000 split (below = 2.00%, above = 0.50%).

### 1.2 Oman commercial: add `MIO` to the product-code special case — CONFIRMED (Oman), UNCONFIRMED (UAE)
- Guide: Part A / B.2 — new manual publishes an explicit **MIO** Oman commercial rate row (Genl 2.15% / GvtSvc 0.70% / Chrtes 0.25 / Whole 2.15%, **no bracket split**). This is a published rate, not just a criteria addition. UAE has **no** published MIO rate row — verify with Mastercard/account team before enabling in UAE.
- Engine: `McIrfCalculationService.java:232` (`UAEMcIRFCalculation.java:203`):
  ```java
  if (countryCodeFlag == 'O' && morIrd.equals("61") && mcc.equals("9999") && gcmsProdId.matches("MEO|MCO|MWO|MAB"))
  ```
  Today a `MIO` product with MCC 9999 falls through to the "All Other Products" path (2.00%/0.50%, limit-indicator dependent). To price it at its own 2.15% row, **add `MIO` to the regex** (`MEO|MCO|MWO|MAB|MIO`) **and** add the matching `MC_OVERRIDE_RATES` row keyed `(ird=61, cardType, mcc=MIO, limitIndicator='A', percent=2.15, fixed=0.25, max=none)`.
- **UAE:** guide's criteria list MIO on UAE IRD 2A/61/Q5/Q6/Q9/QL, but no published UAE rate. Do **not** enable MIO in UAE until confirmed.

### 1.3 Tokenized vs. non-tokenized rates (Merchant UCAF / Full UCAF "when tokenized") — needs a signal the engine does not have
- Guide: Part A / B.1a — the new manual spells out literal "when tokenized" rows across Standard→World Elite (4 bps lower; the old manual left this implicit). Same IRD codes as the non-tokenized rows (24/TM/PM/WM/EM and 24/TF/PF/WF/EF).
- Engine: `IrfTxnData` carries `tokenIdentifier` but the MC path **never reads it**; the override lookup (`getDomOverRide`) keys only on `(ird, cardType, mcc, limitIndicator)` — no token dimension.
- **Action:** determine which layer assigns the tokenized rate. If `VW_IPM_DETAILS` already returns the correct per-token rate upstream (likely — IPM sees DE22/DE55 tokenization), no code change is needed. If the engine is expected to pick the tokenized override row itself, the lookup needs a token flag threaded from `IrfTxnData.tokenIdentifier` and `MC_OVERRIDE_RATES` needs a token variant. Confirm with the IPM upstream feed before coding.

### 1.4 World Legend / World Legend Exclusive (Oman): same IRD codes as World Elite — tier not resolvable by IRD
- Guide: Part A / B.1 — the new manual adds two card tiers *above* World Elite that **reuse World Elite's IRD codes** (ET/EE/EC/ES/EM/EF). Rates differ per tier (+0.10 / +0.05 on the Genl column). The engine cannot distinguish them by IRD code.
- Engine: product/tier comes only from `MC_ISS_ACC_RANGE` (`gcmsProductId`, `cardProgId`) and `MC_PRODUCT_MAPPING` (UAE). For Oman, `MC_PRODUCT_MAPPING` is not consulted (`findByIrdAndGcmsProductId` is UAE-only), so a World Legend vs. World Legend Exclusive card with the same IRD and same product-code mapping would price identically.
- **Action (verify with Mastercard):** the criteria entries (guide Part B.3) list qualifying GCMS product codes (e.g. `MWJ`, `MWK` now appear on IRD EC/EE/EF/EM/ES/ET). Confirm which product codes distinguish Legend/Exclusive, ensure they exist in `MC_ISS_ACC_RANGE`, and if the rate must come from `MC_OVERRIDE_RATES` rather than the view, extend `getDomOverRide` to key consumer overrides by product code.

### 1.5 UAE IRD 74 retired — verify reachability
- Guide: Part A — "IRD 74: UAE Intracountry Mastercard Electronic Card Consumer" is **removed from the new manual's criteria chapter**, but **still referenced** in both old and new MoneySend rate tables (Tables 233/235). Treat as valid for MoneySend routing, but confirm it should no longer be reachable via ordinary POS criteria.
- Engine: no code references IRD 74; it comes through `VW_IPM_DETAILS`. **Action:** confirm the upstream IPM view no longer emits 74 for POS, and check `MC_OVERRIDE_RATES` for stale 74 rows.

### 1.6 UAE merchant UCAF IRDs TM/PM/WM/EM lifecycled (effective April 2026)
- Guide: Part C.1 note at the bottom of the UAE credit rate table (new manual): "The merchant UCAF IRDs TM, PM, WM, EM have lifecycled, effective April [2026]."
- Engine: no explicit handling; these IRDs still flow through the override path for domestic UAE txns if present in the data.
- **Action:** confirm `VW_IPM_DETAILS`/`MC_OVERRIDE_RATES` stop emitting TM/PM/WM/EM for UAE POS; note they remain listed in the UAE MoneySend rate tables (Table 233) as `0.15%; max AED 50` for the sending institution, so don't delete them wholesale if MoneySend is in scope.

### 1.7 Dragon Mart / Night Market MAID rate (UAE) — MAID not used for rate selection
- Guide: Part C.3 (Table 227) — 0.65% across all MCCs when the special Dragon Mart/Night Market **MAID** is present, enabled for DMC/MCC products across a fixed IRD list (24, 33, 34, 73, 75, 79, 83, 85, 95, EA…WS).
- Engine: `IrfTxnData.maid` is read (`mcAssigIdFlag`) and stored on the `MC_IRF_PARAMS` audit row, but the **MAID value itself is never used to select a rate** in `getDomOverRide` (which looks up by ird/cardType/mcc/limitIndicator, not MAID).
- **Action:** confirm whether this is handled upstream in `VW_IPM_DETAILS`. If the engine must honor the MAID-based 0.65%, `getDomOverRide` would need a MAID-aware lookup (or a pre-step that maps the special MAID → override row).

---

## 2. Data changes (`MC_OVERRIDE_RATES`, `MC_PRODUCT_MAPPING`, `MC_ISS_ACC_RANGE`)

| # | Change (guide ref) | Table(s) | Detail |
|---|---|---|---|
| 2.1 | Oman "All Other Products" split 15k→5k | `MC_OVERRIDE_RATES` | 0.50% now applies above USD 5,000 (below = 2.00%). See 1.1. |
| 2.2 | Oman MIO rate row | `MC_OVERRIDE_RATES` | New row: ird=61, mcc=MIO, limitIndicator='A', percent 2.15, fixed 0.25, no max. See 1.2. |
| 2.3 | MXG (consumer debit) added | `MC_PRODUCT_MAPPING` / `MC_ISS_ACC_RANGE` | Now qualifies: Oman IRD 24/3C/75/85/95/79/L3; UAE IRD 20/2A/75/85/95/MS. Needs card-type (`D`) mapping rows + BIN-range product codes. |
| 2.4 | MXP (consumer debit, Platinum tier) added | `MC_PRODUCT_MAPPING` / `MC_ISS_ACC_RANGE` | Now qualifies: Oman IRD PC/PE/PF/PM/PS/PT/L3; UAE IRD 20/2A/MS/PF. Needs card-type (`D`) mapping rows + BIN-range product codes. |
| 2.5 | MRC / MKA / MKD lifecycled (removed) | `MC_PRODUCT_MAPPING` / `MC_ISS_ACC_RANGE` | New manual actually removes these from qualifying lists (old manual carried a "lifecycling with 26.Q2" note). Remove mapping/BIN-range entries so these codes no longer qualify for the affected IRDs (Oman 20/24/3C/73/83/75/85/95/79/PC/PE/PF/PM/PS/PT/L3; UAE 20/2A/MS/EE/EF). |
| 2.6 | Payment Transaction rates IRD 20/21 = 0.19% + 0.53 (flat) | `MC_OVERRIDE_RATES` | Fixed+percent rows must exist for proc code 28 txns in both countries (Oman Table 151, UAE Table 226). Engine formula (`irfFixed + amt*percent*0.01`) supports it directly. |
| 2.7 | UAE debit/prepaid AED caps | `MC_OVERRIDE_RATES` / view | Caps max AED 37.50/50.00/25.00/32.50/1.00 and Exchange-House AED 2.00 (Part C.2, confirmed unchanged). Confirm these live in `VW_IPM_DETAILS` (irfMaxAmount) and that AED caps aren't converted to USD via `exchangeRateAED` on the `D` path (the `D` branch keeps `irfAmount` raw — see `McIrfCalculationService.java:273-280`). |

---

## 3. Confirmed non-changes (do not touch)

- **Oman "World Elite (USD)" rates unchanged** (guide Part A correction): Electronic/EE 2.10%, Standard/ES 2.20%, Issuer Contactless/EC 2.20%, Merchant UCAF/EM 2.10%, Full UCAF/EF 2.10%. The earlier "World Elite increase" was a table-reconstruction artifact.
- **UAE consumer credit matrix unchanged** (Part C.1): reconstructed from both editions, matched tier-for-tier.
- **UAE debit/prepaid rates unchanged** (Part C.2): rebuilt from ground truth, byte-for-byte identical.
- **IRD 2T criteria Notes/Magnetic-stripe/Approval-code/Trace-ID fields** unchanged between editions (Part A caveat — extraction artifact only). Only IRD 2T's GCMS product list changed (MRC/MKA removed, MXG added).

---

## 4. Items flagged "verify against source" in the guide (carry page refs)

| Guide loc. | Manual page | Item |
|---|---|---|
| B.1a (`1.351` cell) | new manual p. 271 | Standard/Gold "Full UCAF (24) when tokenized" PetSpmkConvl renders as `1.351` — confirm it is `1.35` before hard-coding. |
| C.1 note | p. 370 old / p. 402 new | Real Estate / Vehicles / Exchange Houses two-tier thresholds: Real Estate <$1,000 / ≥$1,000; Vehicles <$10,000 / ≥$10,000; Exchange Houses ≤$1,000 / >$1,000. Engine `getLimitIndicator` UAE already encodes 1000/1000/10000 — verify against source (engine uses `6513<1000`, `6051<=1000`, `C`-list `<10000`). |
| C.3 Table 224 | UAE p. 374 | UAE commercial table reconstruction is **garbled** ("All other products 2.00% (below USD 15,000) … 2.20% (below USD 5,000)"). The confirmed 5,000 change is **Oman-only**; UAE's commercial split needs manual verification. Engine UAE `61+9999` still uses 15000 (`McIrfCalculationService.java:321`). |
| A (Part A) | — | MIO on **UAE** IRD 2A/61/Q5/Q6/Q9/QL listed in criteria but **no published UAE rate row** — confirm with Mastercard before enabling. |
| A (Part A) | — | World Legend / World Legend Exclusive tier-detection signal (product code / tier flag) — confirm with Mastercard which product codes separate the three tiers sharing IRDs ET/EE/EC/ES/EM/EF. |

---

## 5. Recommended order

1. **Data first (no code):** 2.1 (Oman 9999 5k split), 2.2 (Oman MIO row), 2.3/2.4 (MXG/MXP mappings), 2.5 (remove MRC/MKA/MKD), 2.6 (payment-txn rows), 2.7 (AED caps check).
2. **Code:** 1.1 (Oman `15000.0` → `5000.0`), 1.2 (`MIO` in regex + override row).
3. **Verify-with-Mastercard before any further code:** 1.3 (tokenized signal source), 1.4 (World Legend/Exclusive tier signal), 1.5 (UAE IRD 74 reachability), 1.6 (TM/PM/WM/EM lifecycle for UAE), 1.7 (Dragon Mart/Night Market MAID rate), 4-Table-224 (UAE commercial split).

## 6. Regression note

The engine's domestic path only applies `MC_OVERRIDE_RATES` when `cardDomIntlFlag=='D'`. Before changing the `getLimitIndicator` thresholds (1.1/1.4), confirm the intended bracket behavior for **intra-country** Oman txns only — international txns are priced from `VW_IPM_DETAILS` and must be unaffected by any `MC_OVERRIDE_RATES`/threshold edits.
