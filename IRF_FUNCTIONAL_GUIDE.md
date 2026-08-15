# IRF — Functional Implementation Guide (for new developers)

A teaching-style explanation of how Interchange Reimbursement Fee (IRF) is implemented for
the **existing schemes** in this codebase: Visa, Mastercard, UAE-Switch, Jaywan, OmanNet,
and On-us. Read this before touching any calculator code.

Companion docs:

- `IRF_IRD_IMPLEMENTATION.md` — precise per-file spec (field/column mappings, code locations).
- `IRF_SERVICE_HANDOVER.md` — build/run, how TLF & MPGS consume the engine, verification log.
- `MC_IRF_GAP_ASSESSMENT.md` — Mastercard rate-criteria gaps vs. the interchange manual.

---

## 1. What IRF is

IRF = the interchange fee the card network charges the acquirer for processing a card
transaction. In this system the acquirer has to:

1. **Work out which interchange rate applies** to each transaction (this is the IRD —
   Interchange Rate Description — the row in the network's fee schedule), and
2. **Compute the fee amount** from that rate and the transaction amount.

Each network publishes its own fee schedule, so the lookup logic is **network-specific**.
That is why there are six different calculators behind one common interface.

---

## 2. The big picture: one engine, many schemes

```
            any transaction (TLF online / MPGS file / TLF Kafka)
                              |
   (A) thin mapper  -->  IrfTxnData        (a scheme-neutral envelope)
                              |
   (B) registry     -->  pick calculator by txn.network
                              |
   (C) calculator   -->  read scheme rate tables + txn fields
                              |
   (D) result VO    -->  IRFResultVo        (rate, fixed, computed fee, card type, ...)
                              |
   (E) store        -->  written back to the transaction (PTR_IRF_*, ...)
   (F) callback     -->  IRF_CALLBACK row -> sent to the scheme
```

The design goal: **the calculators know nothing about the source transaction table**.
A transaction arrives as `IrfTxnData`, the engine returns `IRFResultVo`, and the caller
decides what to do with it. This is why one calculator works for both TLF and MPGS.

---

## 3. Step (A): the mapper — turning a txn into `IrfTxnData`

`IrfTxnData` is a plain DTO holding everything a calculator might need: `mcc`,
`posEntryMode`, `txnAmount`/`setlAmount`, `approvalCode`, `responseCode`, `acqInstConCode`,
`maid`, `network`, `txnCode`, EMV capability flags, etc.

Each consuming service keeps **one mapper** (`IrfTxnDataMapper<T>`) that copies its own
entity (`PosTransactionEntity`, `MPGSTxnWorkEntity`, ...) onto this DTO. Fields a scheme
doesn't use are just left null.

> New developer tip: don't add scheme-specific fields to the calculators. If you need a
> new input, add it to `IrfTxnData` and populate it in the mapper.

---

## 4. Steps (B): dispatch — how a network finds its calculator

`IrfCalculationService` → `IrfCalculatorRegistry` (a `Map<String, IrfCalculator>` built in
`IrfServiceProviderConfig`). The map key is the upper-cased network string:

| Network key on the txn | Calculator |
|------------------------|------------|
| `VISA`, `VSMS` | `VisaIrfCalculationService` |
| `MCI`, `MDS` | `McIrfCalculationService` |
| `UAESWITCH` | `UaeSwitchIrfCalculationService` |
| `JAYWAN` | `JaywanIrfCalculationService` |
| `OMANNET` | `OmanNetIrfCalculationService` |
| `ONUS` | `OnusIrfCalculationService` |

Every calculator implements the same 3-argument method:

```
IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber)
```

`cardNumber` is the **decrypted PAN** — the first 8–9 digits are used for BIN/range lookups.

---

## 5. Step (C): how each existing scheme actually picks its rate

### 5.1 Visa — "fee program ladder"

Visa does not use a single rate row. It has **fee programs** (INDUSTRY FEE PROGRAM,
PROD-RATE, ALT-RATE, ACQ-DGR, BASE-FEE, ALT-FEE, UNCAT, CP, CNP). The calculator:

1. Reads the acquiring country from `acqInstConCode` (`784` = UAE, `512` = Oman) to decide
   which regional rulebook applies.
2. Looks up the issuing card in `VISA_ISS_ACC_RANGE` (BIN range) to get:
   - card product code (e.g. `F2`, `G1`, `AO`),
   - debit/credit/prepaid indicator (`R`→`P`, `H`→`C`),
   - country and region.
3. Routes to one of four rule sets by region:
   - UAE domestic, Oman domestic, Middle-East, or International.
4. Inside that rule set it tries fee programs **in priority order** until one matches. The
   match conditions use transaction attributes:
   - MCC (e.g. petrol `5511|5521`, fuel `5541|5542`, taxi `4121`, government `4214|8062|8641`),
   - POS entry mode (`05|07|90|91` = chip/PIN "card present", `01|10` = keyed/MOTO "not present"),
   - authorisation: 6-digit auth code or response `Y1|Y3` = fully authenticated,
   - **timeline**: most Visa programs only apply if the transaction is ≤ 3 business days
     old (`getVisaTimeLines` = calendar days minus Sundays and 25-Dec). Older transactions
     skip the card-present programs and fall to ACQ-DGR.
   - reimbursement attribute `B` (purchase) vs `D`,
   - amount brackets: e.g. a `5511`/`5521` txn under AED 10,000 sets limit indicator `B`.
5. The matched row in `VISA_IRF_PROGRAMS` supplies `percent`, `fixed`, `min`, `max`.
   Fee = `fixed + txnAmount * percent / 100`, clamped to `[min, max]`, converted to USD.

> Functionally: Visa IRF = "pick the most specific fee program the transaction qualifies
> for; if the card is debit/prepaid pick its own ladder, else the credit ladder."

### 5.2 Mastercard — "IRD from upstream, override for domestic"

Mastercard works differently: the **IRD row itself is computed upstream** by Mastercard's
own data and surfaced through a database view. The calculator mostly *applies* it.

1. Look up the card in `MC_ISS_ACC_RANGE` → `gcmsProductId`, `cardProgId`, country, region.
2. Write an **audit/params row** (`MC_IRF_PARAMS`) keyed by the transaction serial number —
   this "wakes up" the upstream view.
3. Read the **IRD** back from `VW_IPM_DETAILS` (`IIC_IRD`, `IIC_RATE_PERCENT`,
   `IIC_MIN_AMOUNT`, `IIC_MAX_AMOUNT`). If the view returns nothing, default to IRD `85` @ 2.5%.
   (`MDS` network deliberately skips this read — no IRD.)
4. **UAE-only tweak**: `MC_PRODUCT_MAPPING` may override the debit/credit card type for a
   given IRD + GCMS product (some products are billed under a different card-type column).
5. **Domestic transactions** (`cardDomIntlFlag == 'D'`) apply an override rate from
   `MC_OVERRIDE_RATES` instead of the raw view rate:
   - the amount brackets (`getLimitIndicator`) pick limit indicator `A` vs `B`
     (e.g. UAE `6513` < 1000 → `B`, Oman `61+9999` < 5000 → `B`),
   - look up `MC_OVERRIDE_RATES` by `IRD + card type + MCC + limit indicator`, falling
     back from the real MCC → GCMS product → `9999`.
6. **International refunds** (`txnCode == "20"`) use a special override row keyed by
   override id `REFUND` + MCC `R999`.
7. Convert to the report currency:
   - UAE credit: divide by AED rate (3.67); UAE debit: multiply by AED rate.
   - Oman: divide by OMR rate (0.38).

> Functionally: Mastercard IRF = "take the IRD Mastercard assigned upstream, then replace
> the rate with a locally-controlled override for domestic UAE/Oman cards, and convert to
> AED/OMR/USD."

### 5.3 UAE-Switch and Jaywan — "one MCC table, POS vs E-com"

These are the simplest. Each network has a single rate table (`UAE_SWITCH_IRF`,
`JAYWAN_IRF`):

1. Look up the row by **MCC**; if the MCC isn't in the table, fall back to the `GENERAL`
   segment row.
2. The row stores a generic rate plus POS and E-com variants:
   - an MCC-specific row uses `IRF_RATE`,
   - a segment row uses `POS_IRF` when the terminal type is `POS`, `ECOM_IRF` when
     `E-COM`, otherwise the generic rate.
3. Fee = `fixed + txnAmount * rate / 100`, capped at the row's max.
4. Card type is always `D`, always domestic (`domIntlFlag = 'D'`).

### 5.4 OmanNet — "route by BIN"

1. Take the first 6–9 digits of the PAN, find the longest match in `OMANNET_BIN_DATA`.
2. If the BIN's route is **MAAL**, look up the rate in `OMAN_NET_IRF` by
   `route + sub-route + card type + MCC`.
3. If no valid BIN, use the `all segment` rate row.
4. Fee = `fixed + txnAmount * percent / 100`, capped at max. Card type from the BIN/rate row.

### 5.5 On-us — "the OmanNet table, onus route only"

1. Take the first 8 digits of the PAN, look up `OMANNET_BIN_DATA`.
2. Only a BIN whose route is **onus** gets a rate: same
   `route + sub-route + card type + MCC` lookup as OmanNet.
3. Anything else gets a zero fee.

---

## 6. Step (D): the answer — `IRFResultVo`

Every scheme returns the same VO:

| Field | What it holds |
|-------|---------------|
| `irdSerNumber` | which rate row was used (for audit) |
| `irdCode` | the IRD/product code (e.g. MC `85`, Visa product `F2`) |
| `irfPercentage`, `irfFixed` | the rate parts used |
| `irfAmount` | the computed fee (in AED/OMR for MC, local for others) |
| `irfAmountUSD` | the fee converted to USD |
| `cardType` | `C` credit / `D` debit / `P` prepaid |
| `domIntlFlag` | `D` domestic / `I` international |
| `gcmsProductID` | Mastercard GCMS product (MC only) |
| `irfDesc` | human-readable label of the program/segment |

**No match anywhere** → either a zeroed VO (Visa/MC "no BIN" case) or `null` (other schemes).
The caller treats `null` as "no IRF applies".

---

## 7. Steps (E) + (F): storing the result and the callback

The caller (TLF/MPGS) writes the result back onto its own transaction row — the TLF
columns are `PTR_IRF_PERCENT`, `PTR_IRF_AMOUNT`, `PTR_CARD_TYPE`,
`PTR_CARD_DOM_INTL_FLAG`, `PTR_REMARKS` (IRF desc), etc.

Separately, the result is turned into an `IrfResultData` payload and handed to an
`IRFCallbackSender`, which persists a row in **`IRF_CALLBACK`** (`ICB_*` columns: cpMid,
uniqueId, irdCode, fixed, percentage, irfAmount, txnAmount, rrn, mti, domIntlFlag, credit,
cardClassification) with status `P` (pending) and later sends it to the scheme callback URL
(`irf.callback.target.url`). Status becomes `S` (sent) or `E` (error). Two delivery
strategies exist — TLF sends synchronously per txn, MPGS batches by job — but both go
through the same table and sender interface.

---

## 8. Key tables at a glance

| Table | Used by | Purpose |
|-------|---------|---------|
| `VISA_ISS_ACC_RANGE` | Visa | BIN range → product / debit-credit / country / region |
| `VISA_IRF_PROGRAMS` | Visa | fee-program rates (region, card type, program, MCC, brackets) |
| `MC_ISS_ACC_RANGE` | MC | BIN range → GCMS product / card program / country / region |
| `MC_IRF_PARAMS` | MC | audit/params row that activates the IPM view read |
| `VW_IPM_DETAILS` | MC | **the IRD source** (code + rate + min/max per txn) |
| `MC_OVERRIDE_RATES` | MC | domestic + refund override rates (per IRD/card/MCC/bracket) |
| `MC_PRODUCT_MAPPING` | MC | UAE card-type override per IRD+GCMS product |
| `UAE_SWITCH_IRF` / `JAYWAN_IRF` | UAE-Switch / Jaywan | MCC + segment rates (POS / E-com variants) |
| `OMAN_NET_IRF` | OmanNet / On-us | route/sub-route/card-type/MCC rates |
| `OMANNET_BIN_DATA` | OmanNet / On-us | BIN → route (MAAL / onus) + card type |
| `IRF_CALLBACK` | all | pending/sent/error callback rows |

---

## 9. Configuration knobs

All in `application.yml` / env:

- `irf.service.sec` (`IRF_SERVICE_SEC`) — shared secret for the REST endpoints (`?sec=`).
- `irf.service.calculators` — optional network → bean-name overrides.
- `irf.exchange-rate-aed` = 3.67, `irf.exchange-rate-omr` = 0.38, `irf.exchange-rate` = 1.0.
- Consumers set `irf.service.url` + `irf.service.sec` for the REST clients.
- `irf.callback.target.url` — downstream scheme callback URL.

---

## 10. Mental model / gotchas for a new developer

- **One interface, six personalities.** The registry hides the differences; always add a
  scheme by implementing `IrfCalculator` and registering a key, never by branching inside
  an existing calculator.
- **`txnAmount` vs `setlAmount`** is scheme- and card-type-specific. MC uses `setlAmount`
  for credit and Oman, `txnAmount` for UAE debit; Visa always uses `txnAmount`. Don't
  "fix" this without checking the scheme's settlement semantics.
- **Limit indicator `A`/`B`** is a bracket selector (`getLimitIndicator`). It decides which
  `MC_OVERRIDE_RATES` row matches, not a fee by itself.
- **The MC IRD comes from the view, not the code.** If `VW_IPM_DETAILS` returns nothing,
  the fallback is IRD `85` @ 2.5% — a *silent* default. If you see many `85`s in the data,
  the upstream IRD load is the problem, not the calculator.
- **Timeline gates most Visa card-present programs** (≤ 3 business days). Old transactions
  legitimately fall through to ACQ-DGR / UNCAT.
- **Refunds** are a separate path: MC international refunds use the `REFUND`/`R999`
  override; Visa has no refund ladder (reverses flow through the normal programs).
- **`MDS` skips IRD resolution** on purpose — never "fix" that to force a view read.
- **Card type must be resolved from the BIN ranges**, not from an arbitrary field —
  debit is detected only via `MSI|DMC` card programs (MC) or `R/H` indicators (Visa).