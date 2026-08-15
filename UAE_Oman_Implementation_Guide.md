# UAE & Oman — IRF Engine Implementation Guide

**Prepared for:** Credopay — focused reference for coding the IRF (Interchange Reimbursement Fee) engine's United Arab Emirates and Oman logic.

**Source documents:** Mastercard *Interchange Manual — Middle East/Africa Region*, old edition (3 February 2026, 2,089 pages) and new edition (4 August 2026, 2,058 pages).

This guide narrows the full 2,000+ page manual down to what's needed to implement UAE and Oman: the interchange rate designator (IRD) qualification criteria, the rate tables, and every change between the two manual editions that affects these two countries specifically. It is meant to be read directly by whoever (or whichever agent) is writing the engine code — data is presented as tables wherever the source is tabular, and every section is traceable back to a manual page number.

## How the numbers here were produced

Mastercard's PDF renders its widest rate tables (UAE/Oman consumer credit and debit rates, organized by merchant category × card tier) as a borderless grid with values in the middle of the page and no repeated row header — standard text extraction badly garbles these. For this guide, those specific tables were reconstructed from the PDF's per-character position data (column x-coordinates + row grouping), not from linear text extraction, and spot-checked against the rendered PDF page. Simpler tables (IRD criteria, MoneySend rates, ATM rates, acceptor business segments) came from a structure-aware text parse keyed to each manual's own table of contents. Both methods are automated and, at this scale, not perfect — anywhere the data looked internally inconsistent it's flagged below with a "verify against source" note and the exact page number to check.

---

# Part A — What changed for UAE & Oman (new manual vs. old manual)

Mastercard's own published "Summary of changes" (in the new manual, pages 27–28) does **not name UAE or Oman** in its Middle East/Africa change list — it calls out Jordan, Kuwait, Qatar, and general MoneySend/interregional items only (see the main IRF manual document, Part 2, for that list in full).

However, a direct field-by-field comparison of every UAE and Oman IRD's criteria table (GCMS product ID lists, message types, processing codes, etc.) between the two manual editions turned up real, consistent changes that Mastercard's summary did not call out by country. These are the changes the engine actually needs to absorb for UAE/Oman:

## Confirmed product code changes (high confidence — consistent pattern across many IRDs)

| Change | Where it applies | Detail |
| --- | --- | --- |
| **MXG** added | Oman: IRD 24, 3C, 75/85/95, 79, L3 (consumer debit product lists). UAE: IRD 20, 2A, 75/85/95, MS (MoneySend) | New consumer debit product code now qualifies for these IRDs in both countries. Matches the interregional change "GLB 12504 — Enhancing Mastercard Pay by Account Program" (Part 2), which turns out to cascade into UAE/Oman intracountry criteria even though the official summary only mentioned it at the interregional level. |
| **MXP** added | Oman: IRD PC, PE, PF, PM, PS, PT (Platinum consumer debit tier), IRD L3. UAE: IRD 20, 2A, MS, PF | Same program (GLB 12504) — Platinum/general consumer debit tier. |
| **MIO** added | Oman: IRD 21 (commercial payment transaction), IRD 61 (commercial standard). UAE: IRD 2A, 61, Q5, Q6, Q9, QL (commercial credit product lists) | The official summary attributes MIO only to **Jordan** ("MEA 12846 — Introducing Mastercard Pay & Split Commercial Card... for Jordan"), but the same product code shows up added to UAE's and Oman's commercial IRDs too. **For Oman this is now confirmed, not just a criteria-table addition** — the new manual's Oman commercial rate table (Part B.2) publishes an explicit MIO rate row (Genl 2.15% / GvtSvc 0.70% / Chrtes 0.25 / Whole 2.15%), so MIO should be enabled in the engine for Oman. **UAE still needs verification with Mastercard/account team** — no equivalent published UAE MIO rate row was found; confirm before enabling MIO in UAE's product-code validation list. |
| **MRC, MKA, MKD lifecycled (removed)** | Oman: IRD 20, 24, 3C, 73/83, 75/85/95, 79, PC/PE/PF/PM/PS/PT (removal is MRC only there), L3. UAE: IRD 20, 2A, MS, EE, EF | The old manual carried "NOTE: Mastercard is lifecycling product code(s) X with the 26.Q2 release" against these — the new manual (dated 4 Aug 2026, i.e. after Q2 2026) shows the codes actually removed from the qualifying list, consistent with that plan. **The engine should stop accepting MRC/MKA/MKD as qualifying product codes for these IRDs.** |

## Other confirmed changes

| Change | Detail |
| --- | --- |
| **UAE IRD 74 retired** | "IRD 74: United Arab Emirates Intracountry Mastercard Electronic Card Consumer" exists in the old manual's criteria chapter and is **gone entirely** in the new manual (no successor IRD given in its place in the criteria chapter — but it's still referenced in both the old and new MoneySend rate tables, so treat it as still valid for MoneySend routing but check whether it should still be reachable via ordinary POS criteria). |
| **CORRECTED: Oman "World Elite" tier is unchanged — two brand-new tiers added above it** | An earlier pass of this guide claimed Oman's "Consumer World Elite (USD)" tier rates increased between editions. That was wrong — it was a row-merging artifact from automated table reconstruction. Verified directly against a clean ground-truth conversion of both PDFs: **Consumer World Elite (USD) is byte-for-byte identical in both editions** (Electronic/EE 2.10%, Standard/ES 2.20%, Issuer Contactless Incentive/EC 2.20%, Merchant UCAF/EM 2.10%, Full UCAF/EF 2.10% — no change). What actually changed: the new manual **adds two entirely new card tiers above World Elite** — "Consumer World Legend (USD)" and "Consumer World Legend Exclusive (USD)" — that did not exist in the old manual at all. These new tiers **reuse the exact same IRD codes as World Elite** (ET, EE, EC, ES, EM, EF), so the engine cannot distinguish them by IRD code alone — the rate that applies depends on which tier bucket the card/product qualifies into (see Part B.1 below for the exact new rates and the tier-detection implication). |
| **Oman new tokenized-specific line items** | The new manual adds distinct "when tokenized" rate rows for several Merchant UCAF / Full UCAF tiers (Standard, Titanium, Platinum, World, World Elite) that don't exist as separate rows in the old manual — confirmed against the ground-truth conversion (source PDF p. 270–277, new manual) — this is real, not a parsing artifact. See Part B.1 for the exact new rows. |
| **Oman commercial "All Other Products" threshold lowered** | The commercial rate table's "All Other Products" bracket split point dropped from **above USD 15,000** (old manual) to **above USD 5,000** (new manual) for the 0.50% wholesale-tier rate. Confirmed directly against both manuals' ground-truth text — see Part B.2. This changes which transactions qualify for the higher/lower bracket and the engine's threshold constant needs updating. |
| **Oman commercial rate for MIO now has a published rate** | Confirmed (not just a criteria-table addition): the new manual's Oman commercial rate table adds an explicit row for product code **MIO** — Genl 2.15%, GvtSvc 0.70%, Chrtes 0.25, Whole 2.15% (no bracket split). This resolves the "MIO added — verify with Mastercard" caveat below for Oman specifically: the rate is documented, not just the product-code qualification. |
| **UAE credit/debit rates: no change detected** | The UAE consumer credit rate matrix (Table 221, all tiers Standard through World Legend Exclusive) was reconstructed independently from both manual editions and the percentages matched exactly, tier for tier. Treat UAE's POS consumer rates as unchanged. |

## Caveats / things to verify directly against the source PDF before shipping

- **RESOLVED:** A handful of "Notes" and criteria fields (IRD EF's PDS reference notes, IRD 2T's Trace ID/Magnetic stripe/Approval code fields for Oman) previously looked truncated or blanked in this guide's extraction of the new manual. Checked directly against the ground-truth conversion: **these fields are identical, word-for-word, between old and new manual** — the apparent truncation was purely a table-splitting artifact of this guide's own text extraction (the source PDF wraps these fields across a page break, which the extractor mis-handled), not a real content change and not an issue in the source PDF itself. No engine action needed for these specific fields. IRD 2T's GCMS product ID list did change, though — see the MRC/MKA removal in the confirmed product code changes table above (independently reconfirmed against ground truth: MRC and MKA removed from IRD 2T's consumer debit product list; MXG added).
- The "MIO added to UAE/Oman" finding contradicts the official change summary's country attribution (Jordan only). **Oman is now confirmed** (published rate row exists — see Part B.2); **UAE is still unconfirmed** — recommend confirming with Mastercard before relying on it for UAE.

---

# Part B — Oman

## B.1 Consumer credit/debit interchange rates (reconstructed grid)

**Oman intracountry Mastercard consumer credit/debit interchange rates — old manual (3 Feb 2026)**

<sub>manual p. 252 (Table 146/147)</sub>

NOTE (from source): the incentive interchange rate is four bps lower across all AB codes except for the charities category, and applies to tokenized card-not-present transactions.

| Row | Chrtes | GvtSvcUt | InScRe | PetSpmkConvl | Genl |
| --- | --- | --- | --- | --- | --- |
| **Consumer Standard/Gold (USD)** |  |  |  |  |  |
| Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.50% |
| Acquirer Contactless Incentive (2T) | 0.25 | 0.55% | 0.56% | 0.56% | 1.06% |
| Electronic (73, 83) | 0.25 | 0.65% | 0.66% | 0.66% | 1.16% |
| Issuer Contactless Incentive (3C) | 0.25 | 0.75% | 0.76% | 0.76% | 1.26% |
| Standard (75, 85, 95) | 0.25 | 1.69% | 1.69% | 1.69% | 1.69% |
| Merchant UCAF (24) | 0.25 | 0.65% | 1.30% | 1.30% | 1.50% |
| Full UCAF (24) | 0.25 | 0.65% | 1.35% | 1.35% | 1.60% |
| **Consumer Titanium (USD)** |  |  |  |  |  |
| Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.50% |
| Acquirer Contactless Incentive (TT) | 0.25 | 0.55% | 0.56% | 0.56% | 1.70% |
| Electronic (TE) | 0.25 | 0.65% | 0.66% | 0.66% | 1.80% |
| Issuer Contactless Incentive (TC) | 0.25 | 0.75% | 0.76% | 0.76% | 1.90% |
| Standard (TS) | 0.25 | 1.90% | 1.90% | 1.90% | 1.90% |
| Merchant UCAF (TM) | 0.25 | 0.65% | 1.30% | 1.30% | 1.80% |
| Full UCAF (TF) | 0.25 | 0.65% | 1.35% | 1.35% | 1.80% |
| **Consumer Platinum (USD)** |  |  |  |  |  |
| Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.50% |
| Acquirer Contactless Incentive (PT) | 0.25 | 0.55% | 0.56% | 0.56% | 1.85% |
| Electronic (PE) | 0.25 | 0.65% | 0.66% | 0.66% | 1.95% |
| Issuer Contactless Incentive (PC) | 0.25 | 0.75% | 0.76% | 0.76% | 2.05% |
| Standard (PS) | 0.25 | 2.05% | 2.05% | 2.05% | 2.05% |
| Merchant UCAF (PM) | 0.25 | 0.65% | 1.30% | 1.30% | 1.95% |
| Full UCAF (PF) | 0.25 | 0.65% | 1.35% | 1.35% | 1.95% |
| **Consumer World (USD)** |  |  |  |  |  |
| Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.50% |
| Acquirer Contactless Incentive (WT) | 0.25 | 0.55% | 0.56% | 0.56% | 1.95% |
| Electronic (WE) | 0.25 | 0.65% | 0.66% | 0.66% | 2.05% |
| Issuer Contactless Incentive (WC) | 0.25 | 0.75% | 0.76% | 0.76% | 2.15% |
| Standard (WS) | 0.25 | 2.15% | 2.15% | 2.15% | 2.15% |
| Merchant UCAF (WM) | 0.25 | 0.65% | 1.30% | 1.30% | 2.05% |
| Full UCAF (WF) | 0.25 | 0.65% | 1.35% | 1.35% | 2.05% |
| **Consumer World Elite (USD)** |  |  |  |  |  |
| Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.50% |
| Acquirer Contactless Incentive (ET) | 0.25 | 0.55% | 0.56% | 0.56% | 2.00% |
| Electronic (EE) | 0.25 | 0.65% | 0.66% | 0.66% | 2.10% |
| Issuer Contactless Incentive (EC) | 0.25 | 0.75% | 0.76% | 0.76% | 2.20% |
| Standard (ES) | 0.25 | 2.20% | 2.20% | 2.20% | 2.20% |
| Merchant UCAF (EM) | 0.25 | 0.65% | 1.30% | 1.30% | 2.10% |
| Full UCAF (EF) | 0.25 | 0.65% | 1.35% | 1.35% | 2.10% |
| **Commercial (USD)** |  |  |  |  |  |
| Standard (61) | 0.00% | 0.70% | 2.00% | 2.00% | 2.00% |


**Column key:** Chrtes = Charities · GvtSvcUt = Government Services and Utilities · InScRe = Insurance, Schools, and Real Estate · PetSpmkConvl = Petrol, Supermarkets, and Convenience Stores · Genl = General. First numeric column ("0.25") is a flat per-transaction fee in USD; the rest are ad-valorem percentages. Row groups (bold) are card tier: Standard/Gold, Titanium, Platinum, World, World Elite, Commercial.

### B.1a New manual (4 Aug 2026) — what actually changed in this table

<sub>manual p. 270–276 (new manual); cross-checked against the ground-truth markdown conversion, not just the reconstructed grid above</sub>

Two things changed here, and neither is what the previous version of this guide claimed:

1. **"Consumer World Elite (USD)" is identical in both editions.** Confirmed row-for-row: Low Value Contactless Terminal (L3) 0.25/0.50/0.50/0.50/0.50%, Acquirer Contactless Incentive (ET) 0.25/0.55/0.56/0.56/**2.00%**, Electronic (EE) 0.25/0.65/0.66/0.66/**2.10%**, Issuer Contactless Incentive (EC) 0.25/0.75/0.76/0.76/**2.20%**, Standard (ES) 0.25/2.20/2.20/2.20/**2.20%**, Merchant UCAF (EM) 0.25/0.65/1.30/1.30/**2.10%**, Full UCAF (EF) 0.25/0.65/1.35/1.35/**2.10%** — no change from the old-manual table above. Do not implement a World Elite rate change in the engine.

2. **Every tokenized Merchant UCAF / Full UCAF row across all tiers gets an explicit "when tokenized" sibling row** (new in this edition; consistent with the manual's own note that the incentive rate runs 4 bps lower for tokenized card-not-present transactions — this is that note now spelled out as literal rows instead of left implicit):

| Tier | Row | Chrtes | GvtSvcUt | InScRe | PetSpmkConvl | Genl |
| --- | --- | --- | --- | --- | --- | --- |
| Standard/Gold | Merchant UCAF (24) when tokenized | 0.25 | 0.61% | 1.26% | 1.26% | 1.46% |
| Standard/Gold | Full UCAF (24) when tokenized | 0.25 | 0.61% | 1.31% | 1.35%* | 1.56% |
| Titanium | Merchant UCAF (TM) when tokenized | 0.25 | 0.61% | 1.26% | 1.26% | 1.76% |
| Titanium | Full UCAF (TF) when tokenized | 0.25 | 0.61% | 1.31% | 1.31% | 1.76% |
| Platinum | Merchant UCAF (PM) when tokenized | 0.25 | 0.61% | 1.26% | 1.26% | 1.91% |
| Platinum | Full UCAF (PF) when tokenized | 0.25 | 0.61% | 1.31% | 1.31% | 1.91% |
| World | Merchant UCAF (WM) when tokenized | 0.25 | 0.61% | 1.26% | 1.26% | 2.01% |
| World | Full UCAF (WF) when tokenized | 0.25 | 0.61% | 1.31% | 1.31% | 2.01% |
| World Elite | Merchant UCAF (EM) when tokenized | 0.25 | 0.61% | 1.26% | 1.26% | 2.06% |
| World Elite | Full UCAF (EF) when tokenized | 0.25 | 0.61% | 1.31% | 1.31% | 2.06% |

\* The source renders this cell as "1.351" rather than a clean "1.35%" — almost certainly the same value as every other row's PetSpmkConvl≈InScRe pairing, but confirm against source PDF p. 271 (new manual) before hard-coding.

3. **Two brand-new card tiers are added above World Elite: "Consumer World Legend (USD)" and "Consumer World Legend Exclusive (USD)".** This is the real change behind the original (incorrect) "World Elite rate increase" finding — the new tiers **reuse World Elite's own IRD codes** (ET, EE, EC, ES, EM, EF), so IRD code alone does not distinguish them; the engine needs a separate signal (product code / tier flag on the account or BIN range — check IRD ET/EE/EC/ES/EM/EF's criteria chapter entries in Part B.3 for the qualifying product codes Mastercard uses to separate the three tiers) to pick the right rate:

| Tier | Row | Chrtes | GvtSvcUt | InScRe | PetSpmkConvl | Genl |
| --- | --- | --- | --- | --- | --- | --- |
| **Consumer World Legend (USD)** |  |  |  |  |  |  |
| | Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.60% |
| | Acquirer Contactless Incentive (ET) | 0.25 | 0.55% | 0.56% | 0.56% | 2.10% |
| | Electronic (EE) | 0.25 | 0.65% | 0.66% | 0.66% | 2.20% |
| | Issuer Contactless Incentive (EC) | 0.25 | 0.75% | 0.76% | 0.76% | 2.30% |
| | Standard (ES) | 0.25 | 2.20% | 2.20% | 2.20% | 2.30% |
| | Merchant UCAF (EM) | 0.25 | 0.65% | 1.30% | 1.30% | 2.20% |
| | Full UCAF (EF) | 0.25 | 0.65% | 1.35% | 1.35% | 2.20% |
| **Consumer World Legend Exclusive (USD)** |  |  |  |  |  |  |
| | Low Value Contactless Terminal (L3) | 0.25 | 0.50% | 0.50% | 0.50% | 0.65% |
| | Acquirer Contactless Incentive (ET) | 0.25 | 0.55% | 0.56% | 0.56% | 2.15% |
| | Electronic (EE) | 0.25 | 0.65% | 0.66% | 0.66% | 2.25% |
| | Issuer Contactless Incentive (EC) | 0.25 | 0.75% | 0.76% | 0.76% | 2.35% |
| | Standard (ES) | 0.25 | 2.20% | 2.20% | 2.20% | 2.35% |
| | Merchant UCAF (EM) | 0.25 | 0.65% | 1.30% | 1.30% | 2.25% |
| | Full UCAF (EF) | 0.25 | 0.65% | 1.35% | 1.35% | 2.25% |

Rate progression is clean and monotonic (Elite < Legend < Legend Exclusive, roughly +0.10 then +0.05 on the Genl column per tier), which supports these being genuine, correctly-transcribed new rows rather than an extraction error.

## B.2 Other Oman rate tables (commercial, Merchant-on-Record, payment transaction, acceptor business segments)



#### Oman intracountry interchange rates
<sub>manual p. 252</sub>

This section lists rates for this interchange program group.

##### Oman intracountry Mastercard consumer credit and debit interchange rates
<sub>manual p. 252</sub>

Transactions meeting interchange program criteria qualify for associated rates.
NOTE: The incentive interchange rate is four bps lower across all AB codes except for the charities category and applies to tokenized card-not-
present transactions.

**Table 146: Abbreviations for column headings**

| Chrtes = Charities | InScRe = Insurance, Schools, and Real Estate | Genl = General GvtSvcUt = Government Services and Utilities PetSpmkConv = Petrol, Supermarkets, and |
| --- | --- | --- |


##### Oman intracountry Mastercard commercial interchange rates
<sub>manual p. 256</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 148: Abbreviations for column headings**

| Chrtes = Charities | Genl = General |
| --- | --- |
| GvtSvc = Commercial Government Services and Utilities | Whole = Wholesale |


**Table 149: Interchange rates — old manual (3 Feb 2026), manual p. 257**

| Product code(s) | Genl | GvtSvc | Chrtes | Whole |
| --- | --- | --- | --- | --- |
| MEO, MCO, MWO | 2.00% | 0.70% | 0.25 | n/a |
| MCB, MDT | 2.00% | 0.70% | 0.25 | 2.00% (below USD 15,000) / 0.75% (above USD 15,000) |
| MEB | 2.10% | 0.70% | 0.25 | 2.10% (below USD 15,000) / 0.75% (above USD 15,000) |
| BPD, MWB | 2.15% | 0.70% | 0.25 | 2.15% (below USD 15,000) / 0.75% (above USD 15,000) |
| MAB | 2.20% | 0.70% | 0.25 | n/a |
| All Other Products | 2.00% (below USD 15,000) / 0.50% (above USD 15,000) | 0.70% | 0.25 | n/a |

For rates not specified, general rates apply.

**Interchange rates — new manual (4 Aug 2026), manual p. 277 — CHANGED vs. old manual**

<sub>Two confirmed differences from the old-manual table above: (1) a new **MIO** row is added; (2) the "All Other Products" bracket threshold drops from USD 15,000 to **USD 5,000**. Everything else in this table is unchanged.</sub>

| Product code(s) | Genl | GvtSvc | Chrtes | Whole |
| --- | --- | --- | --- | --- |
| MEO, MCO, MWO | 2.00% | 0.70% | 0.25 | n/a |
| MCB, MDT | 2.00% | 0.70% | 0.25 | 2.00% (below USD 15,000) / 0.75% (above USD 15,000) |
| MEB | 2.10% | 0.70% | 0.25 | 2.10% (below USD 15,000) / 0.75% (above USD 15,000) |
| BPD, MWB | 2.15% | 0.70% | 0.25 | 2.15% (below USD 15,000) / 0.75% (above USD 15,000) |
| MAB | 2.20% | 0.70% | 0.25 | n/a |
| **MIO** (new row) | **2.15%** | 0.70% | 0.25 | **2.15%** (no bracket) |
| All Other Products | 2.00% (below USD 15,000) / **0.50% (above USD 5,000)** | 0.70% | 0.25 | n/a |

For rates not specified, general rates apply.


##### Oman intracountry Mastercard commercial Merchant on Record program interchange rates
<sub>manual p. 258</sub>

Transactions meeting interchange program criteria qualify for associated rates.
The specific Mastercard Assigned Merchant Identifier (MAID) IDs will be provided directly to Mastercard approved Fintechs and
Payment Service Providers (PSPs) participating in the program.

**Table 150: Interchange rates**

| IRD | Rates |
| --- | --- |
| 61 | MAID ID 1 60 bps |


##### Oman intracountry Mastercard payment transaction rates
<sub>manual p. 258</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 151: Interchange rates**

| Group | IRD | Rate |
| --- | --- | --- |
| Payment Transaction | 20 | 0.19% + 0.53 |
| Payment Transaction—Commercial | 21 | 0.19% + 0.53 |


##### Oman intracountry consumer acceptor business segments
<sub>manual p. 259</sub>

Acceptor business code (MCC) is a criteria for transactions qualifying for interchange programs and rates.

**Table 152: Acceptor business segments**

| Acceptor business segment Available MCCs Charities | • | MCC 8398 (Organizations, Charitable and Social Service) |
| --- | --- | --- |
| Government Services, and Utilities | • | MCC 4900 (Utilities—Electric, Gas, Heating Oil, Sanitary, Water) Acceptor business segment Available MCCs |
| Insurance, Schools, and Real Estate | • | MCC 5960 (Direct Marketing—Insurance Services) |
| Rentals | • | MCC 6300 (Insurance Sales, Underwriting, and Premiums) |
| Petrol, Supermarkets, and Convenience | • | MCC 5411 (Grocery Stores, Supermarkets) |
| Stores | • | MCC 5499 (Miscellaneous Food Stores—Convenience Stores, Markets, Specialty Stores, and Vending |


##### Oman intracountry commercial acceptor business segments
<sub>manual p. 261</sub>

Acceptor business code (MCC) is a criteria for transactions qualifying for interchange programs and rates.

**Table 153: Acceptor business segments**

| Acceptor business segment Available MCCs Commercial Government Services and | • | MCC 4900 (Utilities-Electric, Gas, Heating Oil, Sanitary, Water) |
| --- | --- | --- |
| Utilities | • | MCC 9211 (Court Costs Including Alimony and Child Support) |
| Charities | • | MCC 8398 (Organizations, Charitable and Social Service) |
| Wholesale | • | MCC 5013: Motor Vehicle Supplies and New Parts |



## B.3 Oman IRD qualification criteria (full)



### Chapter 19 Oman intracountry interchange criteria
<sub>manual p. 1524</sub>

This section includes the transaction criteria for the intracountry interchange programs for which there
is an associated rate mandated by Mastercard. The transactions were conducted using a Mastercard®
card issued by a customer in this country and acquired by a customer in this country.

#### GCMS timeliness criterion
<sub>manual p. 398</sub>

The timeliness of a customer submitting a transaction is a criterion used to determine whether
the transaction qualifies for specific interchange programs.
A GCMS timeliness criterion is provided in some of the IRD tables in this chapter.
Transaction passes GCMS timeliness
A transaction passes the GCMS timeliness criterion if file header date (PDS 0105 [File ID],
subfield 2 [File Reference Date]) minus the transaction date (DE 12 [Transaction Date and
Time, Local Transaction]) is less than, or equal to, the number of days specified for the IRD
timeliness criterion.
Transaction does not pass GCMS timeliness
A transaction does not pass the GCMS timeliness criterion if file header date (PDS 0105 [File
ID], subfield 2 [File Reference Date]) minus the transaction date (DE 12 [Transaction Date and
Time, Local Transaction]) is the greater than the number of days specified for the IRD timeliness
criterion.
References
For more information on holidays, refer to GCMS Parameter Table Layouts.
For more information about GCMS timeliness, refer to the GCMS Reference Manual.

#### IRD 2A: Oman Intracountry Mastercard Initiated Reward
<sub>manual p. 1526</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 693: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | MCC |
| GCMS product ID | All products |
| Message type ID | First Presentment/1240 |
| Processing code | • 20: Credit (Purchase Return); 28: Payment Transaction |
| Acceptor business | I001: Mastercard-Initiated Rebate/Reward |
| (AB) program | For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD 20: Oman Intracountry Consumer Payment Transaction
<sub>manual p. 1528</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 694: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | MCC |
| GCMS product ID | Consumer credit: GCP, MCC, MCE, MCG, MCS, MCT, MCW, MGP, MKE, MKF, MPL, MRC, MRG, MWP Consumer prepaid: GPP NOTE: Mastercard is lifecyling product code MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | 28: Payment Transaction |
| Acceptor business | • D001: Payment Transactions |
| (AB) program | • MON1: Mastercard MoneySend For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 Acquirers may submit a Financial Detail Addendum (Payment Transaction Detail)/ 1644. |
| Notes | In the First Presentment/1240:; PDS 0043 (Transaction Type Identifier) is required and must contain a value that is; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the customer URL. The acquirer pays the interchange fee to the issuer. Acquirers may submit a Payment Transaction only after collected funds are on deposit and under the control of the acquirer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, or of A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bonafide agent relationship with said customer financial institution. |


#### IRD 21: Oman Intracountry Commercial Payment Transaction
<sub>manual p. 1530</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 695: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCO, MCP, MEB, MEO, MES, MNF, MPW, MRW, MWB The following should only be accepted at non-fuel locations: MCF Commercial debit: BPD, MDT |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | 28: Payment Transaction |
| Acceptor business | • D001: Payment Transactions |
| (AB) program | • MON1: MoneySend For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | Not required Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0043 (Transaction Type Identifier) is required and must contain a value that is; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the customer URL. The acquirer pays the interchange fee to the issuer. Acquirers may submit a Payment Transaction only after collected funds are on deposit and under the control of the acquirer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, or of A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bonafide agent relationship with said customer financial institution. |


#### IRD 24: Oman Intracountry Consumer Merchant UCAF
<sub>manual p. 1532</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 696: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCC, MCE, MCG, MCS, MGP, MIU, MRC, MRG Consumer debit: MIP, MDG, MDO, MDS, MHA, MKA, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY NOTE: Mastercard is lifecyling product codes MKA and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and should contain the value CT6, indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD 2T: Oman Intracountry Consumer Standard/Gold Contactless Terminal
<sub>manual p. 1534</sub>


#### IRD 2T: Oman Intracountry Consumer Standard/Gold Contactless Terminal
<sub>manual p. 1534</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 697: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCC, MCE, MCG, MCS, MGP, MIU, MRC, MRG, SUR Consumer debit: MDG, MDO, MDS, MHA, MIP, MKA, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY NOTE: Mastercard is lifecyling product codes MKA and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |


#### IRD 2T: Oman Intracountry Consumer Standard/Gold Contactless Terminal
<sub>manual p. 1534</sub>


**Criteria              Requirement**

| Approval code | Not required |
| --- | --- |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be A or M. Subfield 5 (Cardholder Present Data) must be 0. Subfield 7 (Card Data: Input Mode) must be 2, B, C or F. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Criteria | Requirement |
| Notes | The cardholder must be present at the time of the transaction. To qualify for this interchange program, the transaction must be acquired from a non-contactless- enabled card on a contactless terminal. To determine whether a card is registered as contactless-enabled, refer to the GCMS Parameter Table Layouts manual, IPM MPE table IP0040T1, Contactless Enabled Indicator. |


#### IRD 3C: Oman Intracountry Consumer Standard/Gold Contactless Card
<sub>manual p. 1536</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 698: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCC, MCE, MCG, MCS, MGP, MIU, MRC, MRG, SUR Consumer debit: MDG, MDO, MDS, MHA, MIP, MKA, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY NOTE: Mastercard is lifecyling product codes MKA and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be 2, 5, B, C, D, E. Subfield 5 (Cardholder Present Data) must be 0. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. |


#### IRD 61: Oman Intracountry Commercial Standard
<sub>manual p. 1538</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 699: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MES, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Not required Addendum/1644 |
| Notes | None |


#### IRD 73, 83: Oman Intracountry Consumer Electronic
<sub>manual p. 1540</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 700: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCC, MCE, MCG, MCS, MGP, MIU, MRC, MRG, SUR |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction and |


#### IRD 75, 85, 95: Oman Intracountry Consumer Standard
<sub>manual p. 1543</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 701: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCC, MCE, MCG, MCS, MGP, MIU, MRC, MRG, SUR Consumer debit: MIP, MDG, MDO, MDS, MHA, MKA, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY NOTE: Mastercard is lifecyling product codes MKA, MKD, and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD 79: Oman Intracountry Consumer Full UCAF
<sub>manual p. 1545</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 702: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCC, MCE, MCG, MCS, MGP, MIU, MRC, MRG, SUR Consumer debit: MIP, MDG, MDO, MDS, MHA, MKA, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY NOTE: Mastercard is lifecyling product codes MKA and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and should contain the value CT6, indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD EC: Oman Intracountry Consumer World Elite Contactless Card
<sub>manual p. 1547</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 703: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE, MWJ, MWK Consumer debit: MDW, MKD NOTE: Mastercard is lifecyling product code MKD with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be 2, 5, B, C, D, E. Subfield 5 (Cardholder Present Data) must be 0. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. |


#### IRD EE: Oman Intracountry Consumer Elite Electronic
<sub>manual p. 1549</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 704: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE, MWJ, MWK |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and |


#### IRD EF: Oman Intracountry Consumer Elite Full UCAF
<sub>manual p. 1551</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 705: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE, MWJ, MWK |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Travel-related addendum messages may be provided if DE 26 (Acceptor Business |
| Addendum/1644 | Code) is travel related. |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and must contain value CT6, indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD EM: Oman Intracountry Consumer Elite Merchant UCAF
<sub>manual p. 1553</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 706: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE, MWJ, MWK Consumer debit: MKD NOTE: Mastercard is lifecyling product code MKD with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Travel-related addendum messages may be provided if DE 26 (Acceptor Business |
| Addendum/1644 | Code) is travel related. |
| Notes | In the First Presentment/1240: |


#### IRD ES: Oman Intracountry Consumer Elite Standard
<sub>manual p. 1555</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 707: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE, MWJ, MWK |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD ET: Oman Intracountry Consumer World Elite Contactless Terminal
<sub>manual p. 1557</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 708: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE, MWJ, MWK Consumer debit: MDW, MKD NOTE: Mastercard is lifecyling product code MKD with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be A or M. Subfield 5 (Cardholder Present Data) must be 0. Subfield 7 (Card Data: Input Mode) must be 2, B, C or F. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. To qualify for this interchange program, the transaction must be acquired from a non-contactless- enabled card on a contactless terminal. To determine whether a card is registered as contactless-enabled, refer to the GCMS Parameter Table Layouts manual, IPM MPE table IP0040T1, Contactless Enabled Indicator. |


#### IRD L3: Oman Intracountry Low Value Contactless Terminal
<sub>manual p. 1559</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 709: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCC, MCE, MCG, MCS, MCT, MCW, MGP, MIU, MKE, MKF, MKG, MKH, MNW, MPL, MRC, MRG, MWE, MWJ, MWK, MWP, SUR Consumer debit: MDG, MDH, MDO, MDP, MDS, MDW, MET, MHA, MIP, MKA, MKB, MKC, MKD, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY, MRH, WPD Consumer prepaid: GPP, TPM NOTE: Mastercard is lifecyling product codes MKA, MKD, and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be A or M. Subfield 5 (Cardholder Present Data) must be 0. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | Less than or equal to USD 25 |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. |


#### IRD PC: Oman Intracountry Consumer Platinum Contactless Card
<sub>manual p. 1562</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 710: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB, MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be 2, 5, B, C, D, E. Subfield 5 (Cardholder Present Data) must be 0. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. |


#### IRD PE: Oman Intracountry Consumer Premium Electronic
<sub>manual p. 1564</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 711: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB, MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table Must contain an MCC other than one of the following:; MCC 5542 (Automated Fuel Dispenser); Mail order/telephone order (MO/TO) MCCs: |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face. |


#### IRD PF: Oman Intracountry Consumer Premium Full UCAF
<sub>manual p. 1566</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 712: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB, MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and should contain the value CT6, indicating CAT level 6 (electronic commerce transaction).; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF Collection Indicator) is required and should contain the value 2 (Both merchant and issuer are UCAF-enabled, as indicated in the Authorization Request/0100 message). |


#### IRD PM: Oman Intracountry Consumer Premium Merchant UCAF
<sub>manual p. 1568</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 713: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB, MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and should contain the value CT6, indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD PS: Oman Intracountry Consumer Premium Standard
<sub>manual p. 1570</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 714: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB, MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD PT: Oman Intracountry Consumer Platinum Contactless Terminal
<sub>manual p. 1572</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 715: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB, MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be A or M. Subfield 5 (Cardholder Present Data) must be 0. Subfield 7 (Card Data: Input Mode) must be 2, B, C or F. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. To qualify for this interchange program, the transaction must be acquired from a non-contactless- enabled card on a contactless terminal. To determine whether a card is registered as contactless-enabled, refer to the GCMS Parameter Table Layouts manual, IPM MPE table IP0040T1, Contactless Enabled Indicator. |


#### IRD TC: Oman Intracountry Consumer Titanium Contactless Card
<sub>manual p. 1574</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 716: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be 2, 5, B, C, D, E. Subfield 5 (Cardholder Present Data) must be 0. Not required for all other subfields |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. |


#### IRD TE: Oman Intracountry Consumer Premium Acquirer Chip
<sub>manual p. 1576</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 717: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table Must contain an MCC other than one of the following:; MCC 5542 (Fuel Dispenser, Automated); Mail order/telephone order (MO/TO) MCCs: |
| Amount tolerance | N/A |
| Service code | Required |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face. |


#### IRD TF: Oman Intracountry Consumer Premium Full UCAF
<sub>manual p. 1579</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 718: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and must contain the value of CT6 indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD TM: Oman Intracountry Consumer Premium Merchant UCAF
<sub>manual p. 1581</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 719: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and must contain the value of CT6 indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD TS: Oman Intracountry Consumer Premium Standard
<sub>manual p. 1583</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 720: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD TT: Oman Intracountry Consumer Titanium Contactless Terminal
<sub>manual p. 1584</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 721: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be A or M. Subfield 5 (Cardholder Present Data) must be 0. Subfield 7 (Card Data: Input Mode) must be 2, B, C or F. Not required for all other subfields |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. To qualify for this interchange program, the transaction must be acquired from a non-contactless- enabled card on a contactless terminal. To determine whether a card is registered as contactless-enabled, refer to the GCMS Parameter Table Layouts manual, IPM MPE table IP0040T1, Contactless Enabled Indicator. |


#### IRD WC: Oman Intracountry Consumer World Contactless Card
<sub>manual p. 1587</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 722: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW, MWP Consumer debit: MDH, MKC, WPD Consumer prepaid: GPP |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be 2, 5, B, C, D, E. Subfield 5 (Cardholder Present Data) must be 0. Not required for all other subfields |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. |


#### IRD WE: Oman Intracountry Consumer Super Premium Electronic
<sub>manual p. 1589</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 723: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW, MWP Consumer debit: MDH, MDW, MKC, WPD Consumer prepaid: GPP |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face |


#### IRD WF: Oman Intracountry Consumer Super Premium Full UCAF
<sub>manual p. 1591</sub>

Transactions must meet requirements to qualify for this interchange program.
NOTE: This program is accepted for life cycle transactions only, until Release 18.Q4.

**Table 724: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW, MWP Consumer debit: MDH, MDW, MKC, WPD Consumer prepaid: GPP |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and should contain the value CT6, indicating CAT level 6 (electronic commerce transaction).; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF Collection Indicator) is required and should contain the value 2 (Both merchant and issuer are UCAF-enabled, as indicated in the Authorization Request/0100 message). |


#### IRD WM: Oman Intracountry Consumer Super Premium Merchant UCAF
<sub>manual p. 1593</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 725: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW, MWP Consumer debit: MDH, MDW, MKC, WPD Consumer prepaid: GPP |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and should contain the value CT6, indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD WT: Oman Intracountry Consumer World Contactless Terminal
<sub>manual p. 1595</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 726: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW, MWP Consumer debit: MDH, MKC, WPD Consumer prepaid: GPP |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. Point of service data Required |
| code | Subfield 1 (Terminal Data: Card Data Input Capability) must be A or M. Subfield 5 (Cardholder Present Data) must be 0. Subfield 7 (Card Data: Input Mode) must be 2, B, C or F. Not required for all other subfields |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The cardholder must be present at the time of the transaction. To qualify for this interchange program, the transaction must be acquired from a non-contactless- enabled card on a contactless terminal. To determine whether a card is registered as contactless-enabled, refer to the GCMS Parameter Table Layouts manual, IPM MPE table IP0040T1, Contactless Enabled Indicator. |



---

# Part C — United Arab Emirates

## C.1 Consumer credit interchange rates (reconstructed grid)

**United Arab Emirates intracountry Mastercard credit interchange rates — old manual (3 Feb 2026)**

<sub>manual p. 363-370 (Table 220/221)</sub>

| Row | Chrtes | GovBillTrans | Edu | CatRes | RelEst | Ins & Mkt | Vhcles | ExcH | Petrol | Spmkts | Genl |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| **United Arab Emirates intracountry interchange rates** |  |  |  |  |  |  |  |  |  |  |  |
| **United Arab Emirates intracountry Mastercard credit interchange rates** |  |  |  |  |  |  |  |  |  |  |  |
| **Transit** |  |  |  |  |  |  |  |  |  |  |  |
| **Catering, and Laundry** |  |  |  |  |  |  |  |  |  |  |  |
| **Consumer Standard** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (73, 83) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.20%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.20% |
| Standard (75, 95) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.30%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.30% |
| Consumer Card Present (UE) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.30%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.30% |
| Non-Tokenized CNP (79) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.20%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.20% |
| Tokenized CNP (79) | USD 0.25 | 0.46% | 0.61% | 0.96% | 1.16%; | 0.96% | 0.46%; | 0.50 | 0.46% | 1.01% | 1.16% |
| **Consumer Gold** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (73, 83) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.30%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.30% |
| Standard (75, 95) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.40%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.40% |
| Consumer Card Present (UE) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.40%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.00% | 1.40% |
| Non-Tokenized CNP (79) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.30%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.30% |
| Tokenized CNP (79) | USD 0.25 | 0.46% | 0.61% | 0.96% | 1.26%; | 0.96% | 1.26%; | 0.50 | 0.46% | 1.01% | 1.26% |
| **Consumer Titanium** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (TE) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.70%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.70% |
| Standard (TS) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.80%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.80% |
| Consumer Card Present (UF) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.80%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.80% |
| Non-Tokenized CNP (TF) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.70%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.70% |
| Tokenized CNP (TF) | USD 0.25 | 0.46% | 0.61% | 0.96% | 1.66%; | 0.96% | 1.26%; | 0.50 | 0.46% | 1.01% | 1.66% |
| **Consumer Platinum** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (PE) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.90%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.90% |
| Standard (PS) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.00%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.00% |
| Consumer Card Present (UG) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.00%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.00% | 2.00% |
| Non-Tokenized CNP (PF) | USD 0.25 | 0.50% | 0.65% | 1.00% | 1.90%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 1.90% |
| Tokenized CNP (PF) | USD 0.25 | 0.46% | 0.61% | 0.96% | 1.86%; | 0.96% | 1.26%; | 0.50 | 0.46% | 1.01% | 1.86% |
| **Consumer World** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (WE) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.10%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.10% |
| Standard (WS) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.20%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.20% |
| Consumer Card Present (UH) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.20%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.00% | 2.20% |
| Non-Tokenized CNP (WF) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.10%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.10% |
| Tokenized CNP (WF) | USD 0.25 | 0.46% | 0.61% | 0.96% | 2.06%; | 0.96% | 1.26%; | 0.50 | 0.46% | 1.01% | 2.06% |
| **Consumer World Elite** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (EE) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.15%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.15% |
| Standard (ES) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.25%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.25% |
| Consumer Card Present (UI) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.25%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.25% |
| Non-Tokenized CNP (EF) | USD 0.25 | 0.50% | 0.65% | 1.00% | 2.15%; | 1.00% | 1.30%; | 0.50 | 0.50% | 1.05% | 2.15% |
| Tokenized CNP (EF) | USD 0.25 | 0.46% | 0.61% | 0.96% | 2.11%; | 0.96% | 1.26%; | 0.50 | 0.46% | 1.01% | 2.11% |
| **Consumer World Legend** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (EE) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.25% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.25% | — | 1.05% | 2.25% |
| Consumer Elite Card Present (UI) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.35% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.35% | — | 1.05% | 2.35% |
| Full UCAF (EF) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.25% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.25% | — | 1.05% | 2.25% |
| Full UCAF tokenized (EF) | USD 0.25 | 0.46% | — | 0.61% | 0.61%; 2.21% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.21% | — | 1.01% | 2.21% |
| Standard (ES) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.35% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.35% | — | 1.05% | 2.35% |
| **Consumer World Legend Exclusive** |  |  |  |  |  |  |  |  |  |  |  |
| Electronic (EE) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.30% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.30% | — | 1.05% | 2.30% |
| Consumer Elite Card Present (UI) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.40% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.40% | — | 1.05% | 2.40% |
| Full UCAF (EF) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.30% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.30% | — | 1.05% | 2.30% |
| Full UCAF tokenized (EF) | USD 0.25 | 0.50% | — | 0.61% | 0.61%; 2.26% | 1.00% | 1.30%; 0.50% | USD 0.50; 2.26% | — | 1.01% | 2.26% |
| Standard (ES) | USD 0.25 | 0.50% | — | 0.65% | 0.65%; 2.40% | 1.00% USD 1,000. | 1.30%; 0.50% | USD 0.50; 2.40% | — | 1.05% | 2.40% USD |
| For Real Estate, the first rate is for transactions less than The second rate is for transactions greater than or equal to | 1,000. | — | — | — | USD | 10,000. | — | — | — | — | USD |
| For Vehicles, the first rate is for transactions less than The second rate is for transactions greater than or equal to | 10,000. | — | — | — | — | — | USD 1,000. | — | — | — | — |
| For Exchange Houses, the first rate is for transactions less than or equal to The second rate is for transactions greater than | USD 1,000. | — | — | — | — | 24, | — | — | — | 1 2025. | — |
| **NOTE: The merchant UCAF Interchange Rate Designators (IRDs) TM, PM, WM, and EM have lifecycled, effective April** |  |  |  |  |  |  |  |  |  |  |  |


**Column key:** Chrtes = Charities · GovBillTrans = Government, Bill Payment, and Transit · Edu = Schools and Education · CatRes = Bookstores, Florists, Photo Studios, Catering, and Laundry · RelEst = Real Estate · Ins & Mkt = Insurance and Marketplaces · Vhcles = Vehicles · ExcH = Exchange Houses · Petrol = Petroleum · Spmkts = Supermarkets · Genl = General. Row groups (bold) are card tier: Standard through World Legend Exclusive. For Real Estate, Vehicles, and Exchange Houses columns, values separated by `;` represent two transaction-size tiers (see the note under the World Legend Exclusive rows in the source, manual p. 370 old / p. 402 new, for the exact USD thresholds per tier).

## C.2 Consumer debit and prepaid interchange rates (RESOLVED — clean table, confirmed unchanged)

The debit/prepaid rate matrix (manual p. 371–374 old / p. 403–406 new) previously could not be reliably reconstructed by this guide's own table extraction (two-line-per-cell layout: percentage on one line, "max AED xx.xx" cap on the next). It has now been rebuilt directly from a clean, verified ground-truth conversion of both PDFs and **confirmed byte-for-byte identical between the old and new manual editions** — no rate change here. Columns: General (Debit), General (Prepaid), Government/Utilities/Transport/Petrol, Education/Real Estate, Charity, Exchange Houses (flat AED amount, not a rate).

<sub>manual p. 371–374 (old) / p. 403–406 (new) — identical in both editions</sub>

| Tier | IRD / row | General (Debit) | General (Prepaid) | Govt/Utilities/Transport/Petrol | Education/Real Estate | Charity | Exchange Houses |
| --- | --- | --- | --- | --- | --- | --- | --- |
| **Consumer Standard** |  |  |  |  |  |  |  |
| | Electronic (73, 83) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Consumer Card Present (UE) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Non-Tokenized CNP (79) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Tokenized CNP (79) | 0.96%, max AED 50.00 | 0.96%, max AED 50.00 | 0.46%, max AED 25.00 | 0.61%, max AED 32.50 | 0.61%, max AED 1.00 | AED 2.00 |
| | Standard (75, 85, 95) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| **Consumer Gold** |  |  |  |  |  |  |  |
| | Electronic (73, 83) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Consumer Card Present (UE) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Non-Tokenized CNP (79) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Tokenized CNP (79) | 0.96%, max AED 50.00 | 0.96%, max AED 50.00 | 0.46%, max AED 25.00 | 0.61%, max AED 32.50 | 0.61%, max AED 1.00 | AED 2.00 |
| | Standard (75, 85, 95) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| **Consumer Titanium** |  |  |  |  |  |  |  |
| | Electronic (TE) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Consumer Premium Card Present (UF) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Non-Tokenized CNP (TF) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Tokenized CNP (TF) | 0.96%, max AED 50.00 | 0.96%, max AED 50.00 | 0.46%, max AED 25.00 | 0.61%, max AED 32.50 | 0.61%, max AED 1.00 | AED 2.00 |
| | Standard (TS) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| **Consumer Platinum** |  |  |  |  |  |  |  |
| | Electronic (PE) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Consumer Premium Card Present (UG) | 0.75%, max AED 37.50 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Non-Tokenized CNP (PF) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Tokenized CNP (PF) | 0.96%, max AED 50.00 | 0.96%, max AED 50.00 | 0.46%, max AED 25.00 | 0.61%, max AED 32.50 | 0.61%, max AED 1.00 | AED 2.00 |
| | Standard (PS) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| **Consumer World** |  |  |  |  |  |  |  |
| | Electronic (WE) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Consumer Super Premium Card Present (UH) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Non-Tokenized CNP (WF) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Tokenized CNP (WF) | 0.96%, max AED 50.00 | 0.96%, max AED 50.00 | 0.46%, max AED 25.00 | 0.61%, max AED 32.50 | 0.61%, max AED 1.00 | AED 2.00 |
| | Standard (WS) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| **Consumer World Elite** |  |  |  |  |  |  |  |
| | Electronic (EE) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Consumer Elite Card Present (UI) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Non-Tokenized CNP (EF) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |
| | Tokenized (EF) | 0.96%, max AED 50.00 | 0.96%, max AED 50.00 | 0.46%, max AED 25.00 | 0.61%, max AED 32.50 | 0.61%, max AED 1.00 | AED 2.00 |
| | Standard (ES) | 1.00%, max AED 50.00 | 1.00%, max AED 50.00 | 0.50%, max AED 25.00 | 0.65%, max AED 32.50 | 0.65%, max AED 1.00 | AED 2.00 |

Note: unlike the credit table, the debit/prepaid table does not have separately-published World Legend / World Legend Exclusive rows in either manual edition — those new tiers (see Part A) are confirmed additions to the *credit* rate table and IRD criteria only.

## C.3 Other UAE rate tables (commercial, Merchant-on-Record, payment transaction, Dragon Mart/Night Market, ATM, Installment Payments, MoneySend, acceptor business segments)



#### United Arab Emirates intracountry interchange rates
<sub>manual p. 363</sub>

This section lists rates for this interchange program group.

##### United Arab Emirates intracountry Mastercard credit interchange rates
<sub>manual p. 363</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 220: Abbreviations for column headings**

| Chrtes = Charities | RelEst = Real Estate | Petrol = Petroleum |
| --- | --- | --- |
| GovBillTrans = Government, Bill Payment, and | Ins & Mkt = Insurance and Marketplaces | Spmkts = Supermarkets Transit |
| Edu = Schools and Education | Vhcles = Vehicles | Genl = General CatRes = Bookstores, Florists, Photo Studios, ExcH = Exchange Houses Catering, and Laundry |


##### United Arab Emirates intracountry Mastercard debit and prepaid interchange rates
<sub>manual p. 371 old / p. 403 new — see the clean, verified rendering of this table in Part C.2 above</sub>

Transactions meeting interchange program criteria qualify for associated rates.

##### United Arab Emirates intracountry Mastercard commercial interchange rates
<sub>manual p. 374</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 223: Abbreviations for column headings**

| GvtSvc = Commercial Government Services and | ComEM = Commercial Emerging Market | Genl = General Utilities |
| --- | --- | --- |
| Commercial Petrol = Petrol | ComTCS = Commercial Telecommunication and | Whole = Wholesale Chrtes = Charities ComRelEs = Commercial Real Estate |


**Table 224: Interchange rates**

| MCB, MDT | 2.00% | 0.50% | 0.25 | 0.50% | 0.50% | 2.00% (below | 2.00% (below |
| --- | --- | --- | --- | --- | --- | --- | --- |
| MEB | 2.10% | 0.50% | 0.25 | 0.50% | 0.50% | 2.10% (below | 2.10% (below |
| BPD, MWB | 2.15% | 0.50% | 0.25 | 0.50% | 0.50% | 2.15% (below | 2.15% (below |
| MAB | 2.20% | 0.50% | 0.25 | 0.50% | 0.50% | 2.20% (below | N/A |
| All other | 2.00% (below | 0.50% | 0.25 | 0.50% | 0.50% | 2.20% (below | N/A products USD 15,000) USD 5,000) For rates not specified in this table, the general rates apply. For the Commercial Telecommunication and Computer System category, MCCs 4816 and 4899 are excluded from the available MCC categories. |


##### United Arab Emirates intracountry Mastercard commercial Merchant on Record program interchange rates
<sub>manual p. 376</sub>

Transactions meeting interchange program criteria qualify for associated rates.
The specific Mastercard Assigned Merchant Identifier (MAID) IDs will be provided directly to Mastercard approved Fintechs and
Payment Service Providers (PSPs) participating in the program.

**Table 225: Interchange rates**

| IRD | Rates |
| --- | --- |
| 61 | MAID ID 1 60 bps |


##### United Arab Emirates intracountry Mastercard payment transaction rates
<sub>manual p. 377</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 226: Interchange rates**

| Group | IRD | Rate |
| --- | --- | --- |
| Payment Transaction | 20 | 0.19% + 0.53 |
| Payment Transaction: Commercial | 21 | 0.19% + 0.53 |


##### United Arab Emirates intracountry Mastercard Dragon Mart and Night Market rate
<sub>manual p. 377</sub>

Transactions meeting interchange program criteria qualify for associated rates.
This rate is applicable for consumer products in UAE for POS transactions.
The rate only applies when the related MAID is submitted by the acquirer in clearing messages. The MAID will be individually shared
with Nakheel.
This MAID should be only used for Dragon Mart and Night Market.
Interchange rate
The interchange rate will be 0.65% when the MAID is present across all MCCs.
Product types DMC and MCC for these IRDs will be enabled by the special MAID.

**Table 227: IRDs**

| IRDs |
| --- |
| 24, 33, 34, 73, 75, 79, 83, 85, 95 |
| EA, EE, EF, EI, EM, ES |
| PA, PE, PF, PI, PM, PS |
| TA, TE, TF, TI, TM, TS |
| WA, WE, WF, WI, WM, WS |


##### United Arab Emirates intracountry Mastercard ATM rates
<sub>manual p. 378</sub>

Transactions meeting interchange program criteria qualify for associated rates.
The rates in the following table apply when the issuer and ATM are located within the same country

**Table 228: Interchange rates**

| Approved financial (USD) | Non-financial (USD) |
| --- | --- |
| 0.47 | 0.19 |


##### United Arab Emirates intracountry consumer acceptor business segments
<sub>manual p. 379</sub>

Acceptor business code: MCC is a criteria for transactions qualifying for interchange programs and rates.

**Table 229: Acceptor business segments**

| Acceptor business segment Available MCCs Charities | • | MCC 8398: Organizations, Charitable, and Social Service |
| --- | --- | --- |
| Government, Bill Payment, and Transit | • | MCC 4111: Transportation: Suburban and Local Commuter Passenger |
| Real Estate | • | MCC 6513: Real Estate Agents and Managers: Rentals |
| Schools and Education | • | MCC 8211: Schools, Elementary, and Secondary Acceptor business segment Available MCCs |
| Bookstores, Florists, Photo Studios, | • | MCC 5193: Florist supplies, nursery stock, and flowers |
| Catering, and Laundry | • | MCC 5811: Caterers |
| Petroleum | • | MCC 5541: Service Stations (with or without Ancillary Services) |
| Supermarkets | • | MCC 5411: Grocery Stores, Supermarkets |
| Vehicles | • | MCC 5511: Automotive and Truck Dealers: Sales, Service, Repairs, Parts, and Leasing |
| Insurance | • | MCC 5960: Direct Marketing: Insurance Services |
| Exchange Houses | • | MCC 6051: Quasi Cash-Merchant |
| Funding transactions | • | 4829: Money Transfer |
| Payment transactions | • | 6536: MoneySend Intracountry |


**Table 230: Debit and prepaid Special Merchant Segment acceptor business segments**

| Special Merchant Segment Available MCCs Government and Utilities | • | 4111: Local and Suburban Commuter Passenger Transportation, |
| --- | --- | --- |
| Transport | • | 4784: Tolls and Bridge Fees, Tolls |
| Petrol | • | 5541: Service Stations Special Merchant Segment Available MCCs |
| Education | • | 8211: Elementary and Secondary Schools |
| Real Estate | • | 6513: Real Estate Agents and Managers – Rentals |
| Charity | • | 8398: Charitable Social Service Organizations |
| Exchange Houses | • | 6051: Quasi Cash Merchant NOTE: For the Exchange Houses category, the MCC-6051 is assigned to licensed Exchange Houses that handle Foreign Currency Exchange, Money Orders/Remittances, Travelers Cheques and other similar services, not including Wire Transfers. |


##### United Arab Emirates intracountry Mastercard Installment Payments
<sub>manual p. 382</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 231: Abbreviations for column headings**

| Books = Bookstores, | Chrtes - Charities | Exch = Exchange Houses | Gov = Government, Bill | Ins = Insurance Florists, Photo Studios, Payment, Transit Catering, and Laundry Petro = Petroleum Real = Real Estate Supermarket = SuperMkt |
| --- | --- | --- | --- | --- |


**Table 232: Interchange rates**

| Books | Chrtes | Exch | Generic | Gov | Ins | Petro | Real | Schools | SuperMkt | Vehicles 0.65% USD 0.25 USD 0.50 1.35% 0.50% 1.00% 0.50% 0.65% for 0.65% 1.05% 0.50% for |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |


##### United Arab Emirates intracountry debit MoneySend funding and payment transaction interchange rates
<sub>manual p. 384</sub>

Transactions meeting interchange program criteria qualify for associated rates.

**Table 233: Interchange rates**

| Interchange program type | IRD | [Sending Institution]) | [Originating Institution]) |
| --- | --- | --- | --- |
| United Arab Emirates Intracountry | 24 | 0.15%; max AED 50 | N/A Consumer Merchant UCAF |
| United Arab Emirates Intracountry | 33 | 0.15% | N/A Consumer Issuer Chip |
| United Arab Emirates Intracountry | 34 | 0.15% | N/A Consumer Acquirer Chip |
| United Arab Emirates Intracountry | 73, 83 | 1%; max USD 0.12 | N/A Consumer Electronic |
| United Arab Emirates Intracountry | 74 | 1%; max USD 0.12 | N/A Mastercard Electronic Card Consumer |
| United Arab Emirates Intracountry | 75, 85, 95 | 1%; max USD 0.12 | N/A Consumer Standard |
| United Arab Emirates Intracountry | 79 | 1%; max USD 0.12 | N/A Consumer Full UCAF |
| United Arab Emirates Intracountry | EA | 0.15% | N/A Consumer Elite Chip |
| United Arab Emirates Intracountry | EE | 1%; max USD 0.12 | N/A Consumer Elite Electronic |
| United Arab Emirates Intracountry | EF | 1%; max USD 0.12 | N/A Consumer Elite Full UCAF |
| United Arab Emirates Intracountry | EI | 0.15% | N/A Consumer Elite Issuer Chip |
| United Arab Emirates Intracountry | EM | 0.15%; max AED 50 | N/A Consumer Elite Merchant UCAF |
| United Arab Emirates Intracountry | ES | 1%; max USD 0.12 | N/A Consumer Elite Standard |
| United Arab Emirates Intracountry | MS | N/A | 0.50% Mastercard MoneySend |
| United Arab Emirates Intracountry | PA | 0.15% | N/A Consumer Premium Acquirer Chip |
| United Arab Emirates Intracountry | PE | 1%; max USD 0.12 | N/A Consumer Premium Electronic |
| United Arab Emirates Intracountry | PF | 1%; max USD 0.12 | N/A Consumer Premium Full UCAF |
| United Arab Emirates Intracountry | PI | 0.15% | N/A Consumer Premium Issuer Chip |
| United Arab Emirates Intracountry | PM | 0.15%; max AED 50 | N/A Consumer Premium Merchant UCAF |
| United Arab Emirates Intracountry | PS | 1%; max USD 0.12 | N/A Consumer Premium Standard |
| United Arab Emirates Intracountry | Q1 | 1%; max USD 0.12 | 0.50% Transfer to Own Debit or Prepaid Card Account |
| United Arab Emirates Intracountry | Q2 | 1%; max USD 0.12 | 0.50% General Person-to-Person Transfer |
| United Arab Emirates Intracountry | Q3 | 1%; max USD 0.12 | 0.50% General Transfer to Own Account |
| United Arab Emirates Intracountry | Q4 | 1%; max USD 0.12 | 0.50% Payment of Own Credit Card Bill |
| United Arab Emirates Intracountry | Q5 | 1%; max USD 0.12 | 0.50% Business Disbursement |
| United Arab Emirates Intracountry | Q6 | 1%; max USD 0.12 | N/A Transfer to Own Staged Digital Wallet Account |
| United Arab Emirates Intracountry | Q7 | 1%; max USD 0.12 | 0.50% General Business-to-Business Transfer |
| United Arab Emirates Intracountry | Q8 | N/A | 0.50% Government/Non-profit Disbursement |
| United Arab Emirates Intracountry | Q9 | N/A | 0.50% Rapid Merchant Settlement |
| United Arab Emirates Intracountry | QL | 1%; max USD 0.12 | 0.50% Agent Cash Out |
| United Arab Emirates Intracountry | QM | 1%; max USD 0.12 | 0.50% Person-to-Person Transfer to Card Account |
| United Arab Emirates Intracountry | QN | N/A | 0.50% Cash in at ATM United Arab Emirates Intracountry QO 0.00% Cash in at Point-of-Sale |
| United Arab Emirates Intracountry | TA | 0.15% | N/A Consumer Premium Acquirer Chip |
| United Arab Emirates Intracountry | TE | 1%; max USD 0.12 | N/A Consumer Premium Electronic |
| United Arab Emirates Intracountry | TF | 1%; max USD 0.12 | N/A Consumer Premium Full UCAF |
| United Arab Emirates Intracountry | TI | 0.15% | N/A Consumer Premium Issuer Chip |
| United Arab Emirates Intracountry | TM | 0.15%; max AED 50 | N/A Consumer Premium Merchant UCAF |
| United Arab Emirates Intracountry | TS | 1%; max USD 0.12 | N/A Consumer Premium Standard |
| United Arab Emirates Intracountry | UE | 1%; max USD 0.12 | N/A Consumer Card Present |
| United Arab Emirates Intracountry | UF | 1%; max USD 0.12 | N/A Consumer Premium Card Present |
| United Arab Emirates Intracountry | UG | 1%; max USD 0.12 | N/A Consumer Premium Card Present |
| United Arab Emirates Intracountry | UH | 1%; max USD 0.12 | N/A Consumer Super Premium Card Present |
| United Arab Emirates Intracountry | UI | 1%; max USD 0.12 | N/A Consumer Elite Card Present United Arab Emirates Intracountry WA 0.15% N/A Consumer Super Premium Acquirer Chip |
| United Arab Emirates Intracountry | WE | 1%; max USD 0.12 | N/A Consumer Super Premium Electronic |
| United Arab Emirates Intracountry | WF | 1%; max USD 0.12 | N/A Consumer Super Premium Full UCAF |
| United Arab Emirates Intracountry | WI | 0.15% | N/A Consumer Super Premium Issuer Chip |
| United Arab Emirates Intracountry | WM | 0.15%; max AED 50 | N/A Consumer Super Premium Merchant UCAF |
| United Arab Emirates Intracountry | WS | 1%; max USD 0.12 | N/A Consumer Super Premium Standard |


##### United Arab Emirates intracountry commercial acceptor business segments
<sub>manual p. 389</sub>

Acceptor business code (MCC) is a criteria for transactions qualifying for interchange programs and rates.

**Table 234: Acceptor business segments**

| Acceptor business segment Available MCCs Commercial Government Services and | • | MCC 4900 (Utilities-Electric, Gas, Heating Oil, Sanitary, Water) |
| --- | --- | --- |
| Utilities | • | MCC 9211 (Court Costs Including Alimony and Child Support) |
| Commercial Petrol | • | MCC 5541 (Service Stations With or Without Ancillary Service) |
| Charities | • | MCC 8398 (Organizations, Charitable and Social Service) Acceptor business segment Available MCCs |
| Commercial Emerging Market | • | MCC 4011 (Railroads—Freight) |
| Commercial Telecommunication & | • | MCC 4814 (Telecommunication Services Including But Not Limited To Prepaid Phone Services And Computer System Recurring Phone Services) Commercial Real Estate MCC 6513 (Real Estate Agents And Managers—Rentals) |
| Wholesale | • | MCC 5013: Motor Vehicle Supplies and New Parts |


##### United Arab Emirates intracountry prepaid MoneySend funding and payment transaction interchange rates
<sub>manual p. 391</sub>

Mastercard is introducing and modifying interchange rates for MoneySend funding and payment transactions as detailed in this table.

**Table 235: Interchange rates**

| Interchange program type | IRD | [Sending Institution]) | [Originating Institution]) |
| --- | --- | --- | --- |
| United Arab Emirates Intracountry | 20 | N/A | 0.50% Consumer Payment Transaction |
| United Arab Emirates Intracountry | 24 | 0.15%; max AED 50 | N/A Consumer Merchant UCAF |
| United Arab Emirates Intracountry | 33 | 0.15% | N/A Consumer Issuer Chip |
| United Arab Emirates Intracountry | 34 | 0.15% | N/A Consumer Acquirer Chip |
| United Arab Emirates Intracountry | 73, 83 | 1%; max USD 0.12 | N/A Consumer Electronic |
| United Arab Emirates Intracountry | 74 | 1%; max USD 0.12 | N/A Mastercard Electronic Card Consumer |
| United Arab Emirates Intracountry | 75, 85, 95 | 1%; max USD 0.12 | N/A Consumer Standard |
| United Arab Emirates Intracountry | EE | 1%; max USD 0.12 | N/A Consumer Elite Electronic |
| United Arab Emirates Intracountry | EF | 1%; max USD 0.12 | N/A Consumer Elite Full UCAF |
| United Arab Emirates Intracountry | ES | 1%; max USD 0.12 | N/A Consumer Elite Standard |
| United Arab Emirates Intracountry | PE | 1%; max USD 0.12 | N/A Consumer Premium Electronic |
| United Arab Emirates Intracountry | PF | 1%; max USD 0.12 | N/A Consumer Premium Full UCAF |
| United Arab Emirates Intracountry | PS | 1%; max USD 0.12 | N/A Consumer Premium Standard |
| United Arab Emirates Intracountry | MS | N/A | 0.50% Mastercard MoneySend |
| United Arab Emirates Intracountry | Q1 | 1%; max USD 0.12 | N/A Transfer to Own Debit or Prepaid Card Account |
| United Arab Emirates Intracountry | Q2 | 1%; max USD 0.12 | 0.50% General Person-to-Person Transfer |
| United Arab Emirates Intracountry | Q3 | 1%; max USD 0.12 | 0.50% General Transfer to Own Account |
| United Arab Emirates Intracountry | Q4 | 1%; max USD 0.12 | 0.50% Payment of Own Credit Card Bill |
| United Arab Emirates Intracountry | Q5 | 1%; max USD 0.12 | 0.50% Business Disbursement |
| United Arab Emirates Intracountry | Q6 | 1%; max USD 0.12 | N/A Transfer to Own Staged Digital Wallet Account |
| United Arab Emirates Intracountry | Q7 | 1%; max USD 0.12 | 0.50% General Business-to-Business Transfer |
| United Arab Emirates Intracountry | Q8 | N/A | 0.50% Government/Non-profit Disbursement |
| United Arab Emirates Intracountry | Q9 | N/A | 0.50% Rapid Merchant Settlement |
| United Arab Emirates Intracountry | QL | 1%; max USD 0.12 | 0.50% Agent Cash Out |
| United Arab Emirates Intracountry | QM | 1%; max USD 0.12 | N/A Person-to-Person Transfer to Card Account |
| United Arab Emirates Intracountry | QN | N/A | 0.50% Cash in at ATM |
| United Arab Emirates Intracountry | TE | 1%; max USD 0.12 | N/A Consumer Premium Electronic |
| United Arab Emirates Intracountry | TF | 1%; max USD 0.12 | N/A Consumer Premium Full UCAF |
| United Arab Emirates Intracountry | TS | 1%; max USD 0.12 | N/A Consumer Premium Standard |
| United Arab Emirates Intracountry | UE | 1%; max USD 0.12 | N/A Consumer Card Present |
| United Arab Emirates Intracountry | UF | 1%; max USD 0.12 | N/A Consumer Premium Card Present |
| United Arab Emirates Intracountry | UG | 1%; max USD 0.12 | N/A consumer Premium Card Present |
| United Arab Emirates Intracountry | UH | 0.15%; max AED 50 | N/A Consumer Super Premium Card Present |
| United Arab Emirates Intracountry | UI | 1%; max USD 0.12 | N/A consumer Premium Card Present United Arab Emirates Intracountry WA 0.15% N/A Consumer Super Premium Acquirer Chip |
| United Arab Emirates Intracountry | WE | 1%; max USD 0.12 | N/A Consumer Super Premium Electronic |
| United Arab Emirates Intracountry | WF | 1%; max USD 0.12 | N/A Consumer Super Premium Full UCAF |
| United Arab Emirates Intracountry | WI | 0.15% | N/A Consumer Super Premium Issuer Chip |
| United Arab Emirates Intracountry | WM | 0.15%; max AED 50 | N/A Consumer Super Premium Merchant UCAF |
| United Arab Emirates Intracountry | WS | 1%; max USD 0.12 | N/A Consumer Super Premium Standard |


#### Mastercard/Maestro/Cirrus ATM rates
<sub>manual p. 395</sub>

This section lists rates for this interchange program group.

##### Intracountry ATM rates
<sub>manual p. 395</sub>

The rates in the following table apply when the issuer and ATM are located within the same country.

**Table 236: Intracountry rates**

| Location | Approved financial | Non-financial |
| --- | --- | --- |
| Bahrain | USD 0.33 | 0.00% + USD 0.00 Ghana GHS 1.80 |
| Kuwait | USD 0.35 | 0.00% + USD 0.00 Nigeria NGN 55 |
| Tanzania | TZS 700 | TZS 300 |


#### Mastercard manual cash disbursement rates
<sub>manual p. 396</sub>

Following are the intercountry and intracountry cash disbursement interchange fees.
Intercountry cash disbursement interchange fees apply to manual (transaction not through an ATM) advances of cash by one
Mastercard customer at the customer’s offices where teller services are provided to any Mastercard cardholder, regardless of the card
issuer. Intracountry cash disbursement interchange fees apply to manual advances of cash when the issuer is resident in the same
country as the acquirer and no other applicable fee agreements exist between the customers in that country.
For more details, refer to Chapter 8, “Settlement,” of the Mastercard Rules.

**Table 237: Mastercard manual cash disbursement rates**

| Program name | Rate (USD) |
| --- | --- |
| Intracountry Manual Cash | 2.05 |
| Intercountry Manual Cash | 0.09% + 3.60 |



## C.4 UAE IRD qualification criteria (full)



### Chapter 27 United Arab Emirates intracountry interchange criteria
<sub>manual p. 1961</sub>

This section includes the transaction criteria for the intracountry interchange programs for which there
is an associated rate mandated by Mastercard. The transactions were conducted using a Mastercard®
card issued by a customer in this country and acquired by a customer in this country.

#### GCMS timeliness criterion
<sub>manual p. 398</sub>

The timeliness of a customer submitting a transaction is a criterion used to determine whether
the transaction qualifies for specific interchange programs.
A GCMS timeliness criterion is provided in some of the IRD tables in this chapter.
Transaction passes GCMS timeliness
A transaction passes the GCMS timeliness criterion if file header date (PDS 0105 [File ID],
subfield 2 [File Reference Date]) minus the transaction date (DE 12 [Transaction Date and
Time, Local Transaction]) is less than, or equal to, the number of days specified for the IRD
timeliness criterion.
Transaction does not pass GCMS timeliness
A transaction does not pass the GCMS timeliness criterion if file header date (PDS 0105 [File
ID], subfield 2 [File Reference Date]) minus the transaction date (DE 12 [Transaction Date and
Time, Local Transaction]) is the greater than the number of days specified for the IRD timeliness
criterion.
References
For more information on holidays, refer to GCMS Parameter Table Layouts.
For more information about GCMS timeliness, refer to the GCMS Reference Manual.

#### IRD 20: United Arab Emirates Intracountry Consumer Payment Transaction
<sub>manual p. 1963</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 889: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCC, MCE, MCG, MCS, MCT, MCW, MKE, MKF, MKG, MKH, MIU, MPL, MWE Consumer debit: MDG, MDH, MDO, MDP, MDS, MDW, MET, MHB, MHH, MKA, MKB, MKC, MKD Consumer prepaid: GPP, MHA, MIP, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY, MRH, WPD, MGP, MRC, MRG, MWP, SUR, TPM NOTE: Mastercard is lifecyling product codes MKA, MKD, and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return); 28: Payment Transaction |
| Acceptor business | • D001: Payment Transactions |
| (AB) program | • MON1: Mastercard MoneySend For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 Acquirers may submit a Financial Detail Addendum (Payment Transaction Detail)/ 1644. |
| Notes | In the First Presentment/1240:; PDS 0043 (Transaction Type Identifier) is required and must contain a value that is; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the acceptor URL. The acquirer pays the interchange fee to the issuer. Acquirers may submit a Payment Transaction only after collected funds are on deposit and under the control of the acquirer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, or of A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bonafide agent relationship with said customer financial institution. |


#### IRD 21: United Arab Emirates Intracountry Commercial Payment Transaction
<sub>manual p. 1966</sub>


#### IRD 21: United Arab Emirates Intracountry Commercial Payment Transaction
<sub>manual p. 1966</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 890: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MES, MLA, MNF, MWB The following is accepted at non-fuel locations: MCF Commercial debit: BPD, MDT Commercial prepaid: MBP, MPW, MRW |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return); 28: Payment Transaction |
| Acceptor business | • D001: Payment Transactions |
| (AB) program | • MON1: MoneySend For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |


#### IRD 21: United Arab Emirates Intracountry Commercial Payment Transaction
<sub>manual p. 1966</sub>


**Criteria              Requirement**

| Acceptor ID code | Required |
| --- | --- |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Criteria | Requirement |
| Notes | In the First Presentment/1240:; PDS 0043 (Transaction Type Identifier) is required and must contain a value that is valid when used in combination with the MCC.; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone Number) should contain the customer service phone number.; PDS 0175 (Acceptor URL) should contain the acceptor URL. The acquirer pays the interchange fee to the issuer. Acquirers may submit a Payment Transaction only after collected funds are on deposit and under the control of the acquirer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value usable for gambling that were purchased at any gambling merchant; or transfer of the proceeds from a Mastercard transaction to a commercial entity or to another Mastercard merchant.; Cardholder authentication, including authentication of a Mastercard account, or of the cardholder, by posting a nominal credit (with a Payment Transaction) to a cardholder’s account and requesting cardholders to report a coded acceptor description to the Payment Transaction provider. A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bonafide agent relationship with said customer financial institution. |


#### IRD 2A: United Arab Emirates Intracountry Mastercard Initiated Rewards
<sub>manual p. 1968</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 891: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MES, MLA, MNF, MWB Commercial debit: BPD, MDT Commercial prepaid: MBP, MPW, MRW Consumer credit: GCP, MCC, MCE, MCG, MCS, MCT, MCW, MIU, MKG, MKH, MPL, MWE Consumer debit: MDG, MDH, MDO, MDP, MDS, MDW, MET, MHB , MHH, MKA, MKB, MKC, MKD Consumer prepaid: GPP, MHA, MIP, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY, MRH, WPD, MGP, MRC, MRG, MWP, SUR, TPM NOTE: Mastercard is lifecyling product codes MKA, MKD, and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 28: Payment Transaction |
| Acceptor business | • I001: Mastercard-Initiated Rebate/Reward (AB) program For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |


#### IRD 47: United Arab Emirates Intracountry Mastercard Electronic Card Commercial
<sub>manual p. 1970</sub>


**Criteria              Requirement**

| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros. |
| --- | --- |
| Acceptor postal | Required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD 47: United Arab Emirates Intracountry Mastercard Electronic Card Commercial
<sub>manual p. 1970</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 892: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | MCC |
| GCMS product ID | Commercial credit: MBE |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (Unique MCC); 20: Credit (Purchase Return) |


#### IRD 47: United Arab Emirates Intracountry Mastercard Electronic Card Commercial
<sub>manual p. 1970</sub>


**Criteria             Requirement**

| Acceptor business | • A001: Airline |
| --- | --- |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH2: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Required for all transactions, except Internet transactions. data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table Must contain an MCC other than one of the following:; MCC 5542 (Fuel Dispenser, Automated); Mail order/telephone order (MO/TO) MCCs: |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Criteria | Requirement |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros. |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD 57: United Arab Emirates Intracountry Consumer Private Label
<sub>manual p. 1972</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 893: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | PVL |
| GCMS product ID | Consumer private label: PVA, PVB, PVC, PVD, PVE, PVF, PVG, PVI, PVJ, PVL |
| Message type ID | • First and Second Presentments/1240; First Chargeback/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (Unique MCC); 20: Credit (Purchase Return); 28: Payment Transaction |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; D001: Payment Transactions; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; OTH2: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Required Transactions submitted for the account ranges participating in the Co-Brand Proprietary Transaction Management Program must provide a valid approval code. |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD. Transactions submitted for the account ranges participating in the Co-brand Proprietary Transaction Management Program must provide a valid trace ID. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros. |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None |
| Addendum/1644 | Acquirers may submit a Financial Detail Addendum (Private Label Common Data)/ 1644 or Financial Detail Addendum (Private Label Line-Item Detail)/1644. |
| Notes | None |


#### IRD 57: United Arab Emirates Intracountry Commercial Private Label
<sub>manual p. 1974</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 894: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | PVL |
| GCMS product ID | Consumer private label: PVA, PVB, PVC, PVD, PVE, PVF, PVG, PVI, PVJ, PVL |
| Message type ID | • First and Second Presentments/1240; First Chargeback/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (Unique MCC); 20: Credit (Purchase Return); 28: Payment Transaction |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; D001: Payment Transactions; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; OTH2: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Required Transactions submitted for the account ranges participating in the Co-Brand Proprietary Transaction Management Program must provide a valid approval code. |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD. Transactions submitted for the account ranges participting in the Co-BRand Proprietary Transaction Management Program must provide a valid trace ID. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None |
| Addendum/1644 | Acquirers may submit a Financial Detail Addendum (Private Label Common Data)/ |
| Notes | None |


#### IRD 61: United Arab Emirates Intracountry Commercial Standard
<sub>manual p. 1976</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 895: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MES, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | None |


#### IRD 73, 83: United Arab Emirates Intracountry Consumer Electronic
<sub>manual p. 1978</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 896: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: DCG, MCC, MCE, MCG, MCS, MIU |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |


#### IRD 74: United Arab Emirates Intracountry Mastercard Electronic Card Consumer
<sub>manual p. 1981</sub>


**Criteria             Requirement**

| Notes | The card and the cardholder should be present at the time of the transaction and |
| --- | --- |


#### IRD 74: United Arab Emirates Intracountry Mastercard Electronic Card Consumer
<sub>manual p. 1981</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 897: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCE Consumer debit: MDG, MDO, MDS, MHB, MHH Consumer prepaid: MHA, MIP, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY, MRC NOTE: Mastercard is lifecyling product code MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (Unique MCC); 20: Credit (Purchase Return) |


#### IRD 74: United Arab Emirates Intracountry Mastercard Electronic Card Consumer
<sub>manual p. 1981</sub>


**Criteria              Requirement**

| Acceptor business | • A001: Airline |
| --- | --- |
| (AB) program | • B001: Cruise/Steamship; C001: Cash Disbursements; F001: Restaurant; H001: Lodging; OTH2: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club; Z001: ATM For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table Must contain an MCC other than one of the following:; MCC 5542 (Fuel Dispenser, Automated); Mail order/telephone order (MO/TO) MCCs: |
| Criteria | Requirement |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros. |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | Customers must have a Mastercard Electronic license agreement with Mastercard to acquire Mastercard Electronic transactions. |


#### IRD 75, 85, 95: United Arab Emirates Intracountry Consumer Standard
<sub>manual p. 1983</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 898: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: DCG, MCC, MCE, MCG, MCS, MIU |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must not be present at the time of the transaction. |


#### IRD 79: United Arab Emirates Intracountry Consumer Full UCAF
<sub>manual p. 1986</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 899: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: DCG, MCC, MCE, MCG, MCS, MIU |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Travel-related addendum messages may be provided if DE 26 (Acceptor Business |
| Addendum/1644 | Code) is travel related. |
| Notes | In the First Presentment/1240 message:; PDS 0023 (Terminal Type) is required and should contain the value CT6,; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF MCE consumer e-commerce activity is available under IRD 79: United Arab Emirates Intracountry Consumer Full UCAF. Customers must have a Mastercard Electronic license agreement with Mastercard to acquire Mastercard Electronic transactions. |


#### IRD EE: United Arab Emirates Intracountry Consumer Elite Electronic
<sub>manual p. 1988</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 900: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE Consumer debit: MDW, MKD NOTE: Mastercard is lifecyling product code MKD with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face. DE 22 (Point of Service [POS] Entry Mode) must contain:; Subfield 5 (Cardholder Present Data) with a valid value equal to 0 (Cardholder; Subfield 6 (Card Present Data) with a valid value equal to 1 (Card present) |


#### IRD EF: United Arab Emirates Intracountry Consumer Elite Full UCAF
<sub>manual p. 1991</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 901: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE Consumer debit: MDW, MKD NOTE: Mastercard is lifecyling product code MKD with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | Five days |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Travel-related addendum messages may be provided if DE 26 (Acceptor Business |
| Addendum/1644 | Code) is travel related. |
| Notes | In the First Presentment/1240: |


#### IRD ES: United Arab Emirates Intracountry Consumer Elite Standard
<sub>manual p. 1993</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 902: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must not be present at the time of the transaction. |


#### IRD MS: United Arab Emirates Intracountry Mastercard MoneySend
<sub>manual p. 1995</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 903: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MDB, MEB, MPC, MCF, MCO, MCP, MEO, MGF, MLA, MLC, MPB, MWO,MWB, TCB, TCO Commercial debit: BPD, MDT Commercial prepaid: MBP, MPW, MRW Consumer credit: GCP, MCC, MCE, MCG, MCS, MCT, MCW, MIU, MKE, MKF, MKG, MKH, MNW, MPL, MWE Consumer debit: MDG, MDH, MDO, MDP, MDS, MDW, MET, MHH, MKA, MKB, MKC, MKD Consumer prepaid: GPP, MGP, MHA, MIP, MPA, MPF, MPG, MPM, MPN, MPO, MPP, MPR, MPT, MPV, MPX, MPY, MRH, MRC, MRG, MWP, SUR, TPM, WPD NOTE: Mastercard is lifecyling product codes MKA, MKD, and MRC with the 26.Q2 release. |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 20: Credit (Purchase Return); 28: Payment Transaction |
| Acceptor business | • MON1: MoneySend (AB) program For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | For Mastercard® MoneySend™ transactions, acquirers must submit the following data in |
| Addendum/1644 | a Financial Detail Addendum (Payment Transaction Detail)/1644:; PDS 0670 (Payer/User Information); PDS 0671 (Date of Funds Requested); PDS 0674 (Additional Trace/Reference Number Used by Acceptor); PDS 0765 (Payee Information) must be submitted for all MoneySend Payment |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM terminal), CT2 (CAT level 2 [self-service terminal]), CT6 (CAT level 6 [electronic commerce transaction]), or NA (Terminal type data unknown or not available [The third position of this value is a space.]).; PDS 0043 (Transaction Type Identifier) is required and must contain a value of C07, C52, C53, C54, C55, C56, C57, or C65 when transaction processing code is a value of 00 or 28.; PDS 0043 (Transaction Type Identifier) is required and must contain a value of C58, or C59 when transaction processing code is a value of 28.; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone Number) should contain the customer service phone number.; PDS 0175 (Acceptor URL) should contain the acceptor URL. The issuer pays the interchange fee to the acquirer. Acquirers may submit a MoneySend Payment Transaction only after collected funds are on deposit and under the control of the acquirer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value usable for gambling that were purchased at any gambling acceptor; or transfer of the proceeds from a Mastercard transaction to a commercial entity or to another Mastercard merchant.; Cardholder authentication, including authentication of a Mastercard account, or the cardholder, by posting a nominal credit (with a Payment Transaction) to a cardholder’s account and requesting cardholders to report a coded acceptor description to the Payment Transaction provider. A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transaction). MCC 6538 (Funding Transactions for MoneySend) is to only be used with Processing Code 00 (used with MoneySend Funding Transaction). |


#### IRD O4: United Arab Emirates Mastercard Installment Payments
<sub>manual p. 1999</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 904: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card Program ID | MCC |
| GCMS Product ID | Product Identifier: ETA, ETB, ETC, ETD, ETE, ETF, ETG, ETH, ETI, ETJ, ETK, ETL, ETM, ETN, SBJ, SBK, SPP, and SPS. |
| Message Type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing Code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor Business | • INSA: Mastercard Installment Payments Group A |
| (AB) Program | • INSB: Mastercard Installment Payments Group B; INSC: Mastercard Installment Payments Group C For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | None |
| Approval Code | Required for processing codes 00 and 18 |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required for processing codes 00 and 18 Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor Business | Required |
| Code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount Tolerance | N/A |
| Acceptor ID Code | Required |
| Acceptor Name | Required |
| Acceptor Street | Required Address Acceptor City Name Required |
| Acceptor Postal | Required Code |
| Acceptor State, | Required Province, or Region Code |
| Acceptor Country | Required |
| Code | Cannot contain spaces |
| Mastercard | Required for product codes ETA, ETB, ETC, ETD, ETE, ETF, and ETG. Assigned ID |


#### IRD PE: United Arab Emirates Intracountry Consumer Premium Electronic
<sub>manual p. 2000</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 905: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB Consumer prepaid: MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |


#### IRD PE: United Arab Emirates Intracountry Consumer Premium Electronic
<sub>manual p. 2000</sub>


**Criteria            Requirement**

| Acceptor business | • A001: Airline |
| --- | --- |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; OTH2: Other: Fuel Dispenser, Automated (MCC 5542) Excluded; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Criteria | Requirement |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table Must contain an MCC other than one of the following:; MCC 5542 (Fuel Dispenser, Automated); Mail order/telephone order (MO/TO) MCCs: |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |


#### IRD PF: United Arab Emirates Intracountry Consumer Premium Full UCAF
<sub>manual p. 2003</sub>


**Criteria            Requirement**

| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face. The following requirements apply:; For chip transactions (both issuer and acquirer are chip compliant): – DE 40 (Service Code), position 1 should be provided with a value of 2 or 6. – DE 22 (Point of Service Data Code), subfield 1 (Card Data Input Capability); For non-chip transactions (issuer, acquirer, or both do not meet the requirements for the Chip interchange programs): – DE 40, position 1 should be provided with a value of 1, 5, or 7. – DE 22, subfield 1 should be provided with a value of A, B, V, 0, 1, 2, 4, or 6. |
| --- | --- |


#### IRD PF: United Arab Emirates Intracountry Consumer Premium Full UCAF
<sub>manual p. 2003</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 906: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL Consumer debit: MDP, MKB Consumer prepaid: MRH |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. |
| Approval code | Required |
| Magnetic stripe | Not required data from Refer to “Notes” in this table. authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Travel-related addendum messages may be provided if DE 26 (Acceptor Business Code) |
| Addendum/1644 | is travel related. |
| Notes | In the First Presentment/1240: |


#### IRD PS: United Arab Emirates Intracountry Consumer Premium Standard
<sub>manual p. 2005</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 907: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |


#### IRD Q1: United Arab Emirates Intracountry Transfer to Own Debit or Prepaid Card Account
<sub>manual p. 2007</sub>


**Criteria              Requirement**

| Acceptor state, | Not required province, or region code |
| --- | --- |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must not be present at the time of the transaction. |


#### IRD Q1: United Arab Emirates Intracountry Transfer to Own Debit or Prepaid Card Account
<sub>manual p. 2007</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 908: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |


#### IRD Q1: United Arab Emirates Intracountry Transfer to Own Debit or Prepaid Card Account
<sub>manual p. 2007</sub>


**Criteria            Requirement**

| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| --- | --- |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | MON2: Funding (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address |


#### IRD Q1: United Arab Emirates Intracountry Transfer to Own Debit or Prepaid Card Account
<sub>manual p. 2007</sub>

Criteria              Requirement

**Acceptor city name Not required**

| Acceptor postal | Not required code |
| --- | --- |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | For Mastercard® funding transactions and funding transaction refunds, acquirers |
| Addendum/1644 | may submit the following data in a Financial Detail Addendum (Payment |
| Notes | In the First Presentment/1240 message: |


#### IRD Q2: United Arab Emirates Intracountry General Person-to-Person Transfer
<sub>manual p. 2010</sub>

IRD Q2: United Arab Emirates Intracountry General Person-to-
Person Transfer
Transactions must meet requirements to qualify for this interchange program.

**Table 909: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend |
| (AB) program | • MON2: Funding |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |


#### IRD Q2: United Arab Emirates Intracountry General Person-to-Person Transfer
<sub>manual p. 2010</sub>


**Criteria              Requirement**

| Trace ID | Required |
| --- | --- |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |


#### IRD Q2: United Arab Emirates Intracountry General Person-to-Person Transfer
<sub>manual p. 2010</sub>


**Criteria           Requirement**

| Notes | In the First Presentment/1240 message:; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM The acquirer pays the interchange fee to the issuer. Multiple payment transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions). MCC 6538 (MoneySend Funding) can only be used for processing code 00 or 20 if PDS 0043 equals C07. MCC 6540 (POI Funding Transactions) and MCC 4829 (Money Transfer) can only be used for processing code 00 or 20 if PDS 0043 equals F07. |
| --- | --- |


#### IRD Q3: United Arab Emirates Intracountry General Transfer to Own Account
<sub>manual p. 2013</sub>


#### IRD Q3: United Arab Emirates Intracountry General Transfer to Own Account
<sub>manual p. 2013</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 910: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend |
| (AB) program | • MON2: Funding |
| Timeliness | None |
| Approval code | Required |


#### IRD Q3: United Arab Emirates Intracountry General Transfer to Own Account
<sub>manual p. 2013</sub>


**Criteria              Requirement**

| Magnetic stripe | Not required data from authorization message |
| --- | --- |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |


#### IRD Q3: United Arab Emirates Intracountry General Transfer to Own Account
<sub>manual p. 2013</sub>


**Criteria           Requirement**

| Notes | In the First Presentment/1240 message:; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM The acquirer pays the interchange fee to the issuer. Multiple payment transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions). MCC 6538 (MoneySend Funding) can only be used for processing code 00 or 20 if PDS 0043 equals C52. MCC 6540 (POI Funding Transactions) and MCC 4829 (Money Transfer) can only be used for processing code 00 or 20 if PDS 0043 equals F52. |
| --- | --- |


#### IRD Q4: United Arab Emirates Intracountry Payment of Own Credit Card Bill
<sub>manual p. 2016</sub>


#### IRD Q4: United Arab Emirates Intracountry Payment of Own Credit Card Bill
<sub>manual p. 2016</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 911: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend |
| (AB) program | • MON2: Funding |
| Timeliness | None |
| Approval code | Required |


#### IRD Q4: United Arab Emirates Intracountry Payment of Own Credit Card Bill
<sub>manual p. 2016</sub>


**Criteria              Requirement**

| Magnetic stripe | Not required data from authorization message |
| --- | --- |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |


#### IRD Q4: United Arab Emirates Intracountry Payment of Own Credit Card Bill
<sub>manual p. 2016</sub>


**Criteria           Requirement**

| Notes | In the First Presentment/1240 message:; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM The acquirer pays the interchange fee to the issuer. Multiple payment transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions). MCC 6538 (MoneySend Funding) can only be used for processing code 00 or 20 if PDS 0043 equals C54. MCC 6540 (POI Funding Transactions) and MCC 4829 (Money Transfer) can only be used for processing code 00 or 20 if PDS 0043 equals F54. |
| --- | --- |


#### IRD Q5: United Arab Emirates Intracountry Business Disbursement
<sub>manual p. 2019</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 912: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend |
| (AB) program | • MON2: Funding |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |


#### IRD Q6: United Arab Emirates Intracountry Transfer to Own Staged Digital Wallet Account
<sub>manual p. 2021</sub>


**Criteria           Requirement**

| Notes | In the First Presentment/1240 message:; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions). MCC 6538 (MoneySend Funding) can only be used for processing code 00 or 20 if PDS 0043 equals C55. MCC 6540 (POI Funding Transactions) and MCC 4829 (Money Transfer) can only be used for processing code 00 or 20 if PDS 0043 equals F55. |
| --- | --- |


#### IRD Q6: United Arab Emirates Intracountry Transfer to Own Staged Digital Wallet Account
<sub>manual p. 2021</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 913: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |


#### IRD Q6: United Arab Emirates Intracountry Transfer to Own Staged Digital Wallet Account
<sub>manual p. 2021</sub>


**Criteria            Requirement**

| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| --- | --- |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON2: Funding (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |


#### IRD Q6: United Arab Emirates Intracountry Transfer to Own Staged Digital Wallet Account
<sub>manual p. 2021</sub>


**Criteria              Requirement**

| Acceptor street | Required address Acceptor city name Not required |
| --- | --- |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | For Mastercard® funding transactions and funding transaction refunds, acquirers |
| Addendum/1644 | may submit the following data in a Financial Detail Addendum (Payment |
| Notes | In the First Presentment/1240 message: |


#### IRD Q7: United Arab Emirates Intracountry General Business-to-Business Transfer
<sub>manual p. 2024</sub>

IRD Q7: United Arab Emirates Intracountry General Business-
to-Business Transfer
Transactions must meet requirements to qualify for this interchange program.

**Table 914: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend |
| (AB) program | • MON2: Funding |
| Timeliness | None |
| Approval code | Required |


#### IRD Q7: United Arab Emirates Intracountry General Business-to-Business Transfer
<sub>manual p. 2024</sub>


**Criteria              Requirement**

| Magnetic stripe | Not required data from authorization message |
| --- | --- |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |


#### IRD Q8: United Arab Emirates Intracountry Government/Non-Profit Disbursement
<sub>manual p. 2026</sub>


**Criteria            Requirement**

| Notes | In the First Presentment/1240 message: IRD Q8: United Arab Emirates Intracountry Government/Non- Profit Disbursement Transactions must meet requirements to qualify for this interchange program. |
| --- | --- |


**Table 915: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |


#### IRD Q8: United Arab Emirates Intracountry Government/Non-Profit Disbursement
<sub>manual p. 2026</sub>


**Criteria            Requirement**

| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| --- | --- |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |


#### IRD Q8: United Arab Emirates Intracountry Government/Non-Profit Disbursement
<sub>manual p. 2026</sub>


**Criteria              Requirement**

| Acceptor street | Required address Acceptor city name Not required |
| --- | --- |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |
| Notes | In the First Presentment/1240 message: |


#### IRD Q9: United Arab Emirates Intracountry Rapid Merchant Settlement
<sub>manual p. 2029</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 916: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |
| Notes | In the First Presentment/1240 message:; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions). MCC 6538 (MoneySend Funding) can only be used for processing code 00 or 20. |


#### IRD QL: United Arab Emirates Intracountry Agent Cash Out
<sub>manual p. 2031</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 917: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON1: MoneySend |
| (AB) program | • MON2: Funding |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |
| Notes | In the First Presentment/1240 message:; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0043 (Transaction Type Identifier) is required and must contain a value of; PDS 0043(Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM The acquirer pays the interchange fee to the issuer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions). MCC 6538 (MoneySend Funding) can only be used for processing code 00 or 20 if PDS 0043 equals C53. MCC 6540 (POI Funding Transactions) and MCC 4829 (Money Transfer) can only be used for processing code 00 or 20 if PDS 0043 equal F53. |


### IRD QM: United Arab Emirates Intracountry Person-to-Person Transfer to Card Account
<sub>manual p. 2035</sub>


### IRD QM: United Arab Emirates Intracountry Person-to-Person Transfer to Card Account
<sub>manual p. 2035</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 918: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • MON2: Funding (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |


### IRD QM: United Arab Emirates Intracountry Person-to-Person Transfer to Card Account
<sub>manual p. 2035</sub>


**Criteria              Requirement**

| Trace ID | Required |
| --- | --- |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | For Mastercard® funding transactions and funding transaction refunds, acquirers |
| Addendum/1644 | may submit the following data in a Financial Detail Addendum (Payment |
| Criteria | Requirement |
| Notes | In the First Presentment/1240 message:; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0043(Transaction Type Identifier) is required and must contain a value of The acquirer pays the interchange fee to the issuer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6540 (POI Funding Transactions) and MCC 4829 (Money Transfer) can only be used for processing code 00 or 20 if PDS 0043 equal F08. |


### IRD QN: United Arab Emirates Intracountry Cash in at ATM
<sub>manual p. 2037</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 919: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MCP, MDB, MEB, MEO, MGF, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 28: Payment Transaction |
| Acceptor business | • MON3: MoneySend Payment (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |
| Notes | In the First Presentment/1240 message:; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0043(Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM The acquirer pays the interchange fee to the issuer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions) if PDS 0043 equals C58. IRD QO: United Arab Emirates Intracountry Cash in at Point- of-Sale Transactions must meet requirements to qualify for this interchange program. |


**Table 920: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Commercial credit: MAB, MBD, MCB, MCF, MCO, MDB, MEB, MEO, MGF, MLA, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 28: Payment Transaction |
| Acceptor business | • MON3: MoneySend Payment (AB) program |
| Timeliness | None |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Required address Acceptor city name Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | Acquirers must submit the following data in Financial Detail Addendum (Payment |
| Addendum/1644 | Transaction Detail)/1644 messages for MoneySend payment transactions and |
| Notes | In the First Presentment/1240 message:; PDS 0170 (Acceptor Inquiry Information), subfield 1 (Customer Service Phone; PDS 0175 (Acceptor URL) should contain the acceptor URL.; PDS 0043(Transaction Type Identifier) is required and must contain a value of; PDS 0023 (Terminal Type) is required and must contain a value of ATM (ATM The acquirer pays the interchange fee to the issuer. Multiple Payment Transactions may not be aggregated into a single Mastercard authorization or clearing transaction. The MoneySend Payment Transaction may not be used for any of the following circumstances:; Transfer of gambling winnings or funds related to chips, currency, or other value; Cardholder authentication, including authentication of a Mastercard account, A Payment Transaction provider may not represent itself as an agent of any customer financial institution, for any purpose, including accepting minimum monthly payments or account balance payments on behalf of the customer financial institution, unless it has entered into a bona fide agent relationship with said customer financial institution. MCC 6536 (MoneySend Intracountry) and MCC 6537 (MoneySend Intercountry) are to only be used with Processing Code 28 (used with MoneySend Payment Transactions) if PDS 0043 equals C59. |


#### IRD TE: United Arab Emirates Intracountry Consumer Premium Electronic
<sub>manual p. 2044</sub>


#### IRD TE: United Arab Emirates Intracountry Consumer Premium Electronic
<sub>manual p. 2044</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 921: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; OTH2: Other: Fuel Dispenser, Automated (MCC 5542) Excluded; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Not required If present, the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table Must contain an MCC other than one of the following:; MCC 5542 (Fuel Dispenser, Automated); Mail order/telephone order (MO/TO) MCCs: |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face. The following requirements apply:; For chip transactions (both issuer and acquirer are chip compliant):; For non-chip transactions (issuer, acquirer, or both do not meet the requirements for |


#### IRD TF: United Arab Emirates Intracountry Consumer Premium Full UCAF
<sub>manual p. 2046</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 922: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT Consumer debit: MET Consumer prepaid: TPM |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. |
| Approval code | Required |
| Magnetic stripe | Not required |
| data from | Refer to “Notes” in this table. authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required |
| code (MCC) | Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |
| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required |
| code | Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | Travel-related addendum messages may be provided if DE 26 (Acceptor Business Code) |
| Addendum/1644 | is travel related. |
| Notes | In the First Presentment/1240:; PDS 0023 (Terminal Type) is required and must contain the value of CT6 indicating; PDS 0052 (Electronic Commerce Security Level Indicator), subfield 3 (UCAF |


#### IRD TS: United Arab Emirates Intracountry Consumer Premium Standard
<sub>manual p. 2048</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 923: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address Acceptor city name Required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must not be present at the time of the transaction. |


#### IRD UE: United Arab Emirates Intracountry Consumer Card Present
<sub>manual p. 2050</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 924: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: DCG, MCC, MCE, MCG, MCS, |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | 00: Purchase (Goods and Services) |
| Acceptor business (AB) program | A001: Airline |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe data from authorization message | Not required |
| Trace ID | Not required |
| Acceptor business code (MCC) | Required |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street address | Not required |
| Acceptor city name | Required |
| Acceptor postal code | Not required |
| Acceptor state, province, or region code | Not required |
| Acceptor country code | Required |
| Mastercard Assigned ID (MAID) | Not required |
| Financial Detail Addendum/1644 | None |
| Note | The card and the cardholder must be present at |


#### IRD UF: United Arab Emirates Intracountry Consumer Premium Card Present
<sub>manual p. 2053</sub>


#### IRD UF: United Arab Emirates Intracountry Consumer Premium Card Present
<sub>manual p. 2053</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 925: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MCT |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | 00: Purchase (Goods and Services) |
| Acceptor business (AB) program | A001: Airline |
| Timeliness | None |


#### IRD UF: United Arab Emirates Intracountry Consumer Premium Card Present
<sub>manual p. 2053</sub>


**Criteria                                          Requirement**

| Approval code | Not required |
| --- | --- |
| Magnetic stripe data from authorization message | Not required |
| Trace ID | Not required |
| Acceptor business code (MCC) | Required |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street address | Not required |
| Acceptor city name | Required |
| Acceptor postal code | Not required |
| Acceptor state, province, or region code | Not required |
| Acceptor country code | Required |
| Mastercard Assigned ID (MAID) | Not required |
| Financial Detail Addendum/1644 | None |
| Note | The card and the cardholder must be present at |


#### IRD UG: United Arab Emirates Intracountry Consumer Premium Card Present
<sub>manual p. 2055</sub>


#### IRD UG: United Arab Emirates Intracountry Consumer Premium Card Present
<sub>manual p. 2055</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 926: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKE, MPL |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | 00: Purchase (Goods and Services) |
| Acceptor business (AB) program | A001: Airline |
| Timeliness | None |


#### IRD UG: United Arab Emirates Intracountry Consumer Premium Card Present
<sub>manual p. 2055</sub>


**Criteria                                          Requirement**

| Approval code | Not required |
| --- | --- |
| Magnetic stripe data from authorization message | Not required |
| Trace ID | Not required |
| Acceptor business code (MCC) | Required |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street address | Not required |
| Acceptor city name | Required |
| Acceptor postal code | Not required |
| Acceptor state, province, or region code | Not required |
| Acceptor country code | Required |
| Mastercard Assigned ID (MAID) | Not required |
| Financial Detail Addendum/1644 | None |
| Note | The card and the cardholder must be present at |


#### IRD UH: United Arab Emirates Intracountry Consumer Super Premium Card Present
<sub>manual p. 2057</sub>


#### IRD UH: United Arab Emirates Intracountry Consumer Super Premium Card Present
<sub>manual p. 2057</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 927: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | 00: Purchase (Goods and Services) |
| Acceptor business (AB) program | A001: Airline |
| Timeliness | None |


#### IRD UH: United Arab Emirates Intracountry Consumer Super Premium Card Present
<sub>manual p. 2057</sub>


**Criteria                                          Requirement**

| Approval code | Not required |
| --- | --- |
| Magnetic stripe data from authorization message | Not required |
| Trace ID | Not required |
| Acceptor business code (MCC) | Required |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street address | Not required |
| Acceptor city name | Required |
| Acceptor postal code | Not required |
| Acceptor state, province, or region code | Not required |
| Acceptor country code | Required |
| Mastercard Assigned ID (MAID) | Not required |
| Financial Detail Addendum/1644 | None |
| Note | The card and the cardholder must be present at |


#### IRD UI: United Arab Emirates Intracountry Consumer Elite Card Present
<sub>manual p. 2059</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 928: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: MKG, MKH, MWE |
| Message type ID | • First and Second Presentments/1240 |
| Processing code | 00: Purchase (Goods and Services) |
| Acceptor business (AB) program | A001: Airline |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe data from authorization message | Not required |
| Trace ID | Not required |
| Acceptor business code (MCC) | Required |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street address | Not required |
| Acceptor city name | Required |
| Acceptor postal code | Not required |


#### IRD WE: United Arab Emirates Intracountry Consumer Super Premium Electronic
<sub>manual p. 2061</sub>


**Criteria                                           Requirement**

| Acceptor state, province, or region code | Not required |
| --- | --- |
| Acceptor country code | Required |
| Mastercard Assigned ID (MAID) | Not required |
| Financial Detail Addendum/1644 | None |
| Note | The card and the cardholder must be present at |


#### IRD WE: United Arab Emirates Intracountry Consumer Super Premium Electronic
<sub>manual p. 2061</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 929: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW Consumer debit: MDH, MKC Consumer prepaid: GPP, MWP, WPD |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |
| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |


#### IRD WE: United Arab Emirates Intracountry Consumer Super Premium Electronic
<sub>manual p. 2061</sub>


**Criteria              Requirement**

| Acceptor business | • A001: Airline |
| --- | --- |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse Club For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD. |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |


#### IRD WF: United Arab Emirates Intracountry Consumer Super Premium Full UCAF
<sub>manual p. 2063</sub>


**Criteria              Requirement**

| Acceptor city name | Required Must be left-justified and cannot contain all spaces or all zeros |
| --- | --- |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code Cannot contain spaces |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must be present at the time of the transaction, and the transaction must be face-to-face. DE 22 (Point of Service [POS] Entry Mode) must contain:; Subfield 5 (Cardholder Present Data) with a valid value equal to 0 (Cardholder; Subfield 6 (Card Present Data) with a valid value equal to 1 (Card present) |


#### IRD WF: United Arab Emirates Intracountry Consumer Super Premium Full UCAF
<sub>manual p. 2063</sub>

Transactions must meet requirements to qualify for this interchange program.

**Table 930: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW Consumer debit: MDH, MKC Consumer prepaid: GPP, MWP, WPD |
| Message type ID | • First and Second Presentments/1240; First and Arbitration Chargebacks/1442 |


#### IRD WF: United Arab Emirates Intracountry Consumer Super Premium Full UCAF
<sub>manual p. 2063</sub>


**Criteria              Requirement**

| Processing code | • 00: Purchase (Goods and Services); 18: Unique Transaction (requires unique MCC); 20: Credit (Purchase Return) |
| --- | --- |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship; F001: Restaurant; H001: Lodging; M001: Mail Order/Telephone Order; OTH1: Other; P001: Beauty Salons; R001: Railways; S001: Supermarket; T001: Telephone; U001: Unique; V001: Automobile/Vehicle Rental; W001: Warehouse For acceptor business codes (MCCs) associated with AB programs, refer to Chapter 3 in the Quick Reference Booklet. |
| Timeliness | Five days; Holidays are not excluded.; Transaction date is excluded.; File header date is not excluded. All post-authorized aggregated transit authority transactions are exempt from the timeliness test. |
| Approval code | Required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Required Must contain a valid Banknet Reference Number, and the Banknet Date must contain a valid date in the format MMDD |
| Acceptor business | Required code (MCC) Must contain an MCC belonging to an acceptor business (AB) program previously listed in this table |
| Amount tolerance | N/A |
| Acceptor ID code | Required |


#### IRD WS: United Arab Emirates Intracountry Consumer Super Premium Standard
<sub>manual p. 2065</sub>


**Criteria                 Requirement**

| Acceptor name | Required |
| --- | --- |
| Acceptor street | Not required address |
| Acceptor city name | Not required |
| Acceptor postal | Not required code |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | In the First Presentment/1240: |


#### IRD WS: United Arab Emirates Intracountry Consumer Super Premium Standard
<sub>manual p. 2065</sub>

Transactions must meet requirements to qualify for this MEA region interchange program.

**Table 931: IRD criteria details**

| Criteria | Requirement |
| --- | --- |
| Card program ID | DMC, MCC |
| GCMS product ID | Consumer credit: GCP, MCW, MKF, MNW |


#### IRD WS: United Arab Emirates Intracountry Consumer Super Premium Standard
<sub>manual p. 2065</sub>


**Criteria            Requirement**

| Message type ID | • First and Second Presentments/1240 |
| --- | --- |
| Processing code | • 00: Purchase (Goods and Services) |
| Acceptor business | • A001: Airline |
| (AB) program | • B001: Cruise/Steamship |
| Timeliness | None |
| Approval code | Not required |
| Magnetic stripe | Not required data from authorization message |
| Trace ID | Not required |
| Acceptor business | Required code (MCC) |
| Amount tolerance | N/A |
| Acceptor ID code | Required |
| Acceptor name | Required |
| Acceptor street | Not required address |


#### IRD WS: United Arab Emirates Intracountry Consumer Super Premium Standard
<sub>manual p. 2065</sub>

Criteria              Requirement

**Acceptor city name Required**

| Acceptor postal | Not required code |
| --- | --- |
| Acceptor state, | Not required province, or region code |
| Acceptor country | Required code |
| Mastercard | Not required Assigned ID |
| Financial Detail | None Addendum/1644 |
| Notes | The card and the cardholder must not be present at the time of the transaction. |


