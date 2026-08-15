# IRF_CALLBACK — Columns & Callback Flow

> Owned by `irf-service`. The `IRF_CALLBACK` table stores every computed IRF
> result so it can be flushed to the downstream scheme webhook asynchronously —
> off the Kafka-listener thread.

## 1. The 13 entity columns

These are the columns the standalone `IRFCallbackEntity`
(`irf-service/src/main/java/com/empay/irfservice/callback/IRFCallbackEntity.java`)
maps **in addition** to the legacy 9 columns. They hold the IRF callback payload
for the downstream scheme webhook.

| Column | Type (Oracle) | Purpose |
|---|---|---|
| `ICB_CARD_CLASSIFICATION` | VARCHAR2(50) | Card classification (e.g. `"NONPREMIUM ALT"`) |
| `ICB_CP_MID` | VARCHAR2(30) | Card-acceptor / merchant ID in the callback |
| `ICB_DOM_INTL_FLAG` | CHAR(1) | Domestic/international flag (`'D'` / `'I'`) |
| `ICB_FIXED` | NUMBER | Fixed component of the IRF rate |
| `ICB_IRF_AMOUNT` | NUMBER | Final computed IRF charge |
| `ICB_IS_CREDIT` | NUMBER(1) | Credit vs debit indicator (0/1) |
| `ICB_JOB_NUMBER` | NUMBER | Batch/job id used to group rows for bulk flush |
| `ICB_IRD_CODE` | VARCHAR2(10) | IRF rule code resolved by the calculator (e.g. `"F"`, `"85"`) |
| `ICB_MTI` | VARCHAR2(4) | ISO message type id |
| `ICB_PERCENTAGE` | NUMBER | Percentage component of the IRF rate |
| `ICB_RRN` | VARCHAR2(12) | Retrieval reference number |
| `ICB_TXN_AMOUNT` | NUMBER | The transaction amount the IRF applied to |
| `ICB_UNIQUE_ID` | VARCHAR2(50) | Transaction-unique id echoed to the scheme |

## 2. How the columns are used (callback flow)

All handling is in `LocalIRFCallbackSender`
(`irf-service/src/main/java/com/empay/irfservice/callback/LocalIRFCallbackSender.java`).

### enqueue (persist a PENDING row)

On `POST /irf/v1/callback` each field of the incoming `IrfResultData` is copied
onto the row (`LocalIRFCallbackSender.java:49-70`), including the 13 columns
above. The row starts with `ICB_STATUS = 'P'` (PENDING) and the callback JSON is
built from the same fields (`buildRequestJson`, `LocalIRFCallbackSender.java:72`).

Notes:
- `ICB_JOB_NUMBER` is **never set by enqueue** (reserved for a future batch job).
- `ICB_UPDATED_USER` also stays null.
- The JSON is hand-rolled (TODO in the code: replace with Jackson).

### flush (send a job's pending rows)

`POST /irf/v1/callback/flush?insCode=&jobNumber=` selects PENDING rows — all, or
filtered by institution + job — and POSTs each row's `ICB_REQUEST` JSON to
`irf.callback.target.url`. Outcome per row:
- HTTP success → `ICB_STATUS = 'S'` (SENT), `ICB_RESPONSE` = reply
- failure → `ICB_STATUS = 'E'` (ERROR), `ICB_RESPONSE` = error message

`ICB_LAST_UPDATED` is stamped each time. Run **asynchronously** so the caller is
not blocked (handover issues #7/#8).

### retry (re-send one row)

`POST /irf/v1/callback/retry?refSerNumber=` re-sends a **single** row keyed by
the entity serial (`findById`, i.e. `ICB_SER_NUMBER` — not `refSerNumber`),
then updates `ICB_STATUS`/`ICB_RESPONSE`/`ICB_LAST_UPDATED` the same way.

## 3. Schema status (2026-08-13)

- The 13 columns were added to UAT's legacy `IRF_CALLBACK` via
  `irf-db-upgrade.sql`, then **reverted** the same day via `irf-db-rollback.sql`
  (see handover §7.2/§7.3). UAT currently has only the original 9 columns.
- Until `irf-db-upgrade.sql` is re-applied, the `/irf/v1/callback*` endpoints
  fail with `ORA-00904`; `/irf/v1/calculate` is unaffected (it never touches
  `IRF_CALLBACK`).
