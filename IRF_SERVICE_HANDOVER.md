# IRF — REST Engine Handover

> Goal: IRF calculation becomes a **standalone, independently-deployable Spring
> Boot service**. The transaction-matching services (TLF, MPGS) keep only a
> thin entity→`IrfTxnData` mapper + an HTTP call. No more duplicated calculators,
> no more blocking scheme-callback inside the Kafka listener.

- `irf-common` — **library only** (DTOs, entities, repos, calculator contracts,
  HTTP-client impls). No server.
- `irf-service` — **the only runnable**. Owns the calculators + the
  `IRF_CALLBACK` table.
- TLF, MPGS — consumers only.

## 1. Layout

```
D:\NSS\IRF
├── irf-common/                       # shared library (NO server)
│   ├── entities/  vo/  repo/  cryptapi/ …
│   └── irf/
│       ├── IrfTxnData / IrfTxnDataMapper<T>           # shared DTO + per-service mapper contract
│       ├── IrfResultData / IRFCallbackSender          # callback contract + payload
│       ├── IrfCalculateRequest / IrfCalculateResponse # REST envelope
│       ├── HttpIrfCalculator / HttpIRFCallbackSender  # REST CLIENT impls (drop-in beans)
│       └── IrfCalculator / VisaIrfCalculator / McIrfCalculator
├── irf-service/                     # NEW — standalone Spring Boot app
│   ├── pom.xml  (parent: spring-boot-starter-parent 3.2.4; dep: irf-common + H2 + h2 test)
│   ├── src/main/resources/application.yml
│   └── src/main/java/com/empay/irfservice/
│       ├── IrfServiceApplication.java
│       ├── calculator/          # network → bean dispatch
│       │   ├── IrfCalculationService + IrfCalculatorRegistry
│       │   ├── VisaIrfCalculationService        (relocated — full Visa logic)
│       │   ├── McIrfCalculationService          (relocated — TLF+MPGS MC logic)
│       │   ├── UaeSwitchIrfCalculationService   (relocated)
│       │   ├── JaywanIrfCalculationService      (relocated)
│       │   ├── OmanNetIrfCalculationService     (relocated)
│       │   └── OnusIrfCalculationService        (relocated)
│       ├── callback/            # owns the IRF_CALLBACK table
│       │   ├── IRFCallbackEntity + IRFCallbackRepository
│       │   └── LocalIRFCallbackSender    (implements IRFCallbackSender server-side)
│       ├── controller/          # REST
│       │   ├── IrfCalculationController → POST /irf/v1/calculate
│       │   └── IrfCallbackController    → /callback, /callback/flush, /callback/retry
│       └── config/IrfServiceProviderConfig   # RestTemplate + calculator registry
├── go/                                # Go consumer-side client + mappers (validated vs the jar)
│   ├── go.mod (module empay/irf, go 1.18)
│   ├── irf/           # HTTP client, IrfTxnData DTO, LocalDateTime, stub test server
│   ├── tlf/           # TlfTxnMapper port (PosTransactionEntity subset → IrfTxnData)
│   ├── mpgs/          # MpgsTxnMapper port (MPGSTxnWorkEntity subset → IrfTxnData)
│   └── live/          # live integration tests against a running irf-service jar
├── tlf-processing-service-java/
└── mpgs-java/

## 2. Build & run

Needs JDK 17 + Maven 3.9+. No root parent required (`irf-service` uses Spring
Boot's standard parent and reads `irf-common` from the local repo):

```bash
mvn -f irf-common/pom.xml install -DskipTests          # publish shared library
mvn -f irf-service/pom.yaml spring-boot:run             # start the engine
# packaged:  mvn -f irf-service/pom.xml package && java -jar irf-service/target/irf-service-1.0.0-SNAPSHOT.jar
mvn -f irf-service/pom.xml test                        # run the ITs (H2 auto-configured)
```

**Go consumer services** (Go 1.21+, module `empay/irf` at `go/go.mod`):
```bash
cd go && CGO_ENABLED=0 go build -o /tmp/tlf-service ./cmd/tlf-service   # TLF online processor
cd go && CGO_ENABLED=0 go build -o /tmp/mpgs-service ./cmd/mpgs-service  # MPGS DCF batch processor
go test ./...                                                             # all Go tests (stub-based, CI-safe)
go test ./live/ -v                                                        # live tests against a running jar
```

Runtime config (`application.yml` / env). Test defaults are in
`src/test/resources/application-test.yml`:

| Property | Default | Purpose |
|---|---|---|
| `server.port` | `8085` | engine port |
| `irf.service.sec` | `change-me` | shared secret for every `/irf/v1/**` call (`?sec=`) |
| `irf.service.url` | `http://localhost:8085` | base URL the **client** beans (`HttpIrfCalculator`/`HttpIRFCallbackSender`) POST to |
| `irf.callback.target.url` | — | downstream scheme webhook `flush` POSTs to |
| `irf.exchange-rate-aed / -omr /` | `3.67 / 0.38 / 1.0` | rates used by the calculators |

## 3. REST API (all endpoints require `?sec=<irf.service.sec>`)

```
POST /irf/v1/calculate?sec=<sec>
  { "network":"VISA", "insCode":7085, "cardNumber":"411111111", "txnData":{...} }
  200 { "calculated":true|false, "result":{IRFResultVo|null} }
  401 wrong/missing sec
  501 network not yet relocated (stub calculator)

POST /irf/v1/callback?sec=<sec>                       BODY {IrfResultData}  -> 200 : serialNumber
POST /irf/v1/callback/flush?sec=<sec>&insCode=&jobNumber=   -> 200
POST /irf/v1/callback/retry?sec=<sec>&refSerNumber=         -> 200 : true|false
```

> The three `/callback*` endpoints map the 13 `ICB_*` columns (§7.2). Against
> the **reverted** UAT schema (§7.3) they fail with `ORA-00904` until
> `irf-db-upgrade.sql` is re-applied. `/calculate` is unaffected.

## 4. How TLF & MPGS consume it (migration steps)

This is the checklist for the next engineer cutting over a service. **It is
written but not yet applied** (no TLF/MPGS build in this workspace; deletion is
gated on a runnable build so the cutover is verified, not guessed).

### 4.1 Add the dependency
```xml
<dependency>
  <groupId>com.empay</groupId><artifactId>irf-common</artifactId><version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 4.2 Register the REST-client beans (identical block, one `@Configuration` class)
```java
@Bean RestTemplate restTemplate() { return new RestTemplate(); }

@Bean IrfCalculator irfCalculator(RestTemplate rt, Environment env) {
    return new HttpIrfCalculator(rt, env);          // POSTs /irf/v1/calculate
}
@Bean IRFCallbackSender irfCallbackSender(RestTemplate rt, Environment env) {
    return new HttpIRFCallbackSender(rt, env);      // POSTs /irf/v1/callback*
}
```
```yaml
# application.yml / env for each service
irf:
  service:
    url: http://localhost:8085
    sec: <shared-secret>
```

### 4.3 Keep ONE thin mapper per service

- **TLF** — `tlf-processing-service-java/com/empay/tlfprocessing/mapper/TlfTxnMapper.java`
  (`implements IrfTxnDataMapper<PosTransactionEntity>`).
- **MPGS** — `mpgs-java/decompiled/com/empay/mappers/MpgsTxnMapper.java`
  (`implements IrfTxnDataMapper<MPGSTxnWorkEntity>`).

Each maps the IRF-relevant getters onto a fresh `IrfTxnData`. The full field
set (mirrored verbatim by the Go mappers, §7): `serialNumber, insCode, network,
scheme (TLF only; MPGS entity has no getter), mcc, txnCode, txnId, responseCode,
approvalCode, rrn, posEntryMode, posConditionCode, serviceCode, cardSeqNumber,
terminalType, txnAmount, setlAmount, cashBackAmount, netAmount, txnCurCode,
setlCurCode, feePgmIndicator, reImbursementAttribute, motoEcomIndicator,
terminalCapability, trlCapabilities, mvv, networkData, maid, meCategoryType,
chAuthAbility, cardInputAbility, cardCaptureAbility, cardInputMode, chPresent,
cardPresent, oprtEnvironment, txnDateTime, msgTypeId, txnUniqueId, cardNumber,
encCardNumber, acqInstConCode, cardAcceptorCountryCode`.

**Java `Character` / `String` narrowing:** `IrfTxnData` fields typed `Character`
(and `trlCapabilities`/`oprtEnvironment`) reject multi-char strings at the JSON
boundary (Jackson → HTTP 400). Both Java mappers and the Go mappers use the same
`firstChar(...)` helper: multi-char source → `null`, single char → that char.

### 4.4 DELETE (once the call-site is rewritten)

TLF only:
```
com/empay/visa/VisaIrfCalculation.java            # relocated → irf-service
com/empay/visa/repo/* , com/empay/visa/entities/* # now in irf-common
com/empay/common/functions/UAEMcIRFCalculation.java # relocated → irf-service
com/empay/entities/IRFCallbackEntity.java          # owned by irf-service now
com/empay/service/IRFCallbackService.java          # replaced by HTTP client + async flush
```
MPGS only:
```
com/empay/common/functions/VisaIrfCalculation.java      # relocated → irf-service
com/empay/common/functions/UAEMcIRFCalculation.java     # relocated → irf-service
com/empay/common/vo/IRFRequestVo.java , IRFResultVo.java # live in irf-common now
com/empay/entities/IRFCallbackEntity.java               # owned by irf-service now
com/empay/service/IRFCallbackService.java               # replaced by HTTP client
com/empay/vo/*                                        # any IRF VO copies duplicating common
```

### 4.5 Rewrite a call-site (TLF example)

```java
// before: local bean, blocking callback on the listener thread (~2.1s)
IRFResultVo r = visaIrfCalculation.getVisaIrf(insCode, posTxnEntity, pan);
irfCallbackService.insertIntoIRFCallback(r, posTxnEntity);
irfCallbackService.updateApiResponse(callback, r);

// after: HTTP to irf-service; enqueue is persisted server-side, flush is async
IrfTxnData data = tlfTxnMapper.toIrfData(posTxnEntity);
IRFResultVo r = irfCalculator.calculate(insCode, data, "VISA", pan);
if (r != null && r.getIrdSerNumber() != null) {
    IrfResultData payload = new IrfResultData(...)
        .setInsCode(insCode).setRefSerNumber(posTxnEntity.getSerialNumber()) ...;
    irfCallbackSender.enqueue(payload);          // PENDING row in irf-service, not TLF
}
```
MPGS: same shape, `mpgsTxnMapper.toIrfData(workEntity)` / `MPGSTxnWorkEntity`.

## 5. Per-network calculator ownership

| Network | Calculator (in irf-service) | Status |
|---|---|---|
| Visa / Visa-SMS | `VisaIrfCalculationService` | ✅ Relocated (full logic) |
| Mastercard (UAE/OMAN) | `McIrfCalculationService` | ✅ Relocated (TLF `UAEMcIRFCalculation` UAE+OMAN path + MPGS pan; see class javadoc for the `IrfTxnData` adaptations) |
| UAE-Switch | `UaeSwitchIrfCalculationService` | ✅ Relocated |
| Jaywan | `JaywanIrfCalculationService` | ✅ Relocated |
| OmanNet | `OmanNetIrfCalculationService` | ✅ Relocated |
| On-us | `OnusIrfCalculationService` | ✅ Relocated |

New `IrfCalculator` bean + one entry in `IrfServiceProviderConfig.DEFAULTS` enables a network.

## 6. Fixes this layout enables (vs. the bug list)

- **One copy** of each calculator ⇒ #3 (missing max cap on Visa UAE/OMAN),
  #4 (empty-result/null writes in UAE-Switch/Jaywan/OmanNet), and #9
  (MPGS min/max overwrite) are fixed at the single source.
- **Async `/callback/flush`** moves the webhook off the Kafka listener thread ⇒
  #7 (≈2.1 s/txn), #8 (RestTemplate timeouts), #9 race.
- `/calculate` returns `calculated:false` instead of `null` ⇒ NPE-prone null
  handling gone across both services.
- `sec` auth + no PAN/IRD logging ⇒ #10 (credential leakage).

## 7. Verification (honest)

- `irf-service` ships a `@SpringBootTest` IT:
  `src/test/java/com/empay/irfservice/controller/IrfCalculationControllerIT.java`
  (+ `src/test/resources/application-test.yml`, H2). It asserts:
  1. `POST /irf/v1/calculate?sec=…` → `200 {calculated:true,result:{country:"XX",irfAmount:0.0}}`
     (no-BIN-range branch returns the documented fallback `IRFResultVo`, not null).
  2. Wrong/missing `sec` → **401**.
  3. `HttpIrfCalculator` **client** round-trips the same payload against the live
     test server and deserializes `IRFResultVo` identically.
- **Verified 2026-08-13** on JDK 17.0.19 + Maven 3.6.3 (installed via apt in this
  workspace):
  - `mvn -f irf-common/pom.xml install` → BUILD SUCCESS.
  - `mvn -f irf-service/pom.xml package` → BUILD SUCCESS.
  - 3/3 `IrfCalculationControllerIT` tests pass. **Note:** the class is named
    `*IT` so surefire's default includes skip it — run it explicitly with
    `mvn test -Dtest=IrfCalculationControllerIT`.
  - Live jar smoke test (H2, empty tables) — all network keys return
    `calculated:true` with the correct fallback per calculator: VISA/VSMS →
    `{country:"XX", amount:0.0}`, MCI/MDS → `"Crypt API Failed.Invalid cardnumber"`,
    UAE-Switch/Jaywan → empty `IRFResultVo` (matches TLF's `return irdResult` on
    "No IRF configuration found"), OmanNet/Onus → `domIntlFlag:'D'`, zeros.
- Fixes required to make the build pass (all applied to this tree):
  1. `irf-common/pom.xml`: pinned `commons-collections4` → 4.4 and
     `maven-compiler-plugin` → 3.13.0 (versions missing); `jasypt` 3.0.4 → **1.9.3**
     (3.0.4 does not exist on Maven Central; `EncryptionUtil` classes verified present
     in 1.9.3); dropped explicit `jakarta.transaction-api` version (3.1.0 does not
     exist; BOM manages 2.0.1).
  2. Stripped UTF-8 BOMs from 34 decompiled `.java` files in `irf-common` (javac
     rejects `\ufeff` at byte 1).
  3. Renamed `MCIssAcqRangeEntity`/`MCIssAcqRangeRepo` → `McIssAcqRange*` to match
     filenames (case-insensitive USB FS prevents the reverse rename); updated the
     entity/repo/service references.
  4. `HttpIRFCallbackSender`: `IRFResultData` → `IrfResultData` (top-level class name).
  5. `StatusCheckController`: `ResponseEntity.ok((Object)"OK")` → `ResponseEntity.ok("OK")`.
  6. `LocalIRFCallbackSender`: fixed `org.springframework.web.re.RestTemplate` import
     typo; `findBy*StatusOrderById` → `findBy*StatusOrderBySerialNumber` (matches
     repo methods; entity `@Id` is `serialNumber`).
  7. `IrfCalculationControllerIT`: `LocalServerPort` import →
     `org.springframework.boot.test.web.server.LocalServerPort`.
  8. `IrfServiceApplication`: added `@EntityScan` + `@EnableJpaRepositories` for
     `com.empay.common.entities` / `com.empay.common.repo` (component-scan alone does
     not register the shared repos/entities).
  9. `application.yml`: `spring.jackson.serialization.write-dates-with-timezone` →
     `write-dates-as-timestamps` (invalid `SerializationFeature` enum constant).
- `irf-common` + `irf-service` compile cleanly against each other (no cycle).
- TLF/MPGS mapper templates are plain `@Component` beans with **no** references to
  `irf-service` classes (one-way dependency: service → common), so they drop in
  once a service build is available.

### 7.1 Go client/mapper validation (2026-08-13)

The `go/` module ports the TLF/MPGS consumer side (HTTP client + thin mappers)
and is tested in two layers:

- **Stub** (`go test ./...`): `go/irf/stub` serves the REST contract in-process
  and backs `tlf`/`mpgs` mapper tests (`Calculate` fallback, `EnqueueCallback`,
  `FlushCallbacks`, `RetryCallback`, 401 on bad `sec`). Passes without the jar.
- **Live** (`go/live/live_integration_test.go`, run `go test ./live/` with the
  jar up): drives the **real `tlf.Txn`/`mpgs.Txn` mappers** through the Go client
  against the actual service on `:18085`. Config via `IRF_BASE_URL` (default
  `http://localhost:18085`) and `IRF_SECRET` (default `test-secret`; use
  `change-me` for the packaged default). Sweeps all 8 network keys through both
  mappers and asserts the same fallbacks as §7's jar smoke test; also round-trips
  `EnqueueCallback` (returns real H2 `serialNumber`). All green.

Two Go↔Java serialization bugs were found **only by the live test** and fixed:

1. `trlCapabilities: "00"` sent a 2-char string → Jackson rejected the `Character`
   field with HTTP 400. Fixed: `firstChar()` in `go/tlf/tlf.go` + `go/mpgs/mpgs.go`
   (matches the Java helper: multi-char → `nil`).
2. `time.Time` marshals with an offset (`+05:30`) → Java `LocalDateTime` cannot
   parse → HTTP 400. Fixed: `irf.LocalDateTime` type in `go/irf/client.go`
   (`MarshalJSON` emits `2006-01-02T15:04:05` with no offset; `UnmarshalJSON`
   accepts plain + offset layouts for round-trips). Both mappers now set it.

The Go DTO + both Go mappers were then fully synced with the Java
`TlfTxnMapper`/`MpgsTxnMapper` field-for-field (see §4.3 field list): added
`TxnId`, `MeCategoryType`, `ChAuthAbility`, `CardInputAbility`,
`CardCaptureAbility`, `CardInputMode`, `ChPresent`, `CardPresent`,
`OprtEnvironment` (all `Character` → single-char, `firstChar`-narrowed), plus the
missing amount/string fields TLF maps (`Scheme`, `TxnCode`, `PosConditionCode`,
`ServiceCode`, `CardSeqNumber`, `CashBackAmount`, `NetAmount`, `NetworkData`,
`Maid`). Verified again: `go test ./...` green (stub + live) with `serialNumber`
populated on the MC path (`MCI/MDS → ird:"85"` fallback after the server-side
`MC_IRF_PARAMS` insert).

### 7.2 UAT server deployment + Oracle verification (2026-08-13)

> **Updated 2026-08-13 (same day): the schema changes below have since been
> reverted on UAT and the two new tables dropped — see §7.3.**

The jar now runs on the UAT box wired to the **real Oracle UAT schema**
(`NETWORK_SETTLEMENT_UAT`) and every path in §7's flow was re-verified there.

**Server** (from `.properties` files + bash history, not SSH config):
- `10.100.139.30` — RHEL 9.4, JDK 21 (openjdk 21.0.5), 4 cores, 15 GB RAM.
- SSH: `ssh -i /home/ravi/switch-uat-key-pair.pem ec2-user@10.100.139.30`
  (`sudo` needs no password; ICMP blocked, port 22 open).
- Oracle UAT: `jdbc:oracle:thin:@//switch-uat.c3guuusy8mm5.me-central-1.rds.amazonaws.com:1521/ORCL`,
  user `NETWORK_SETTLEMENT_UAT`. The other `.properties` files carry the same creds.

**Deployment:**
- Jar (ojdbc11 21.9.0.0 bundled): `/App/Vaultspay/irf-service/irf-service-1.0.0-SNAPSHOT.jar`.
- Env: `/App/Vaultspay/config/irf-service/irf-service.env` —
  `SERVER_PORT=8085`, `IRF_SERVICE_SEC=UAT-IRF-7f3a9c2b`,
  `SPRING_JPA_HIBERNATE_DDL_AUTO=none`, OracleDialect, log file
  `/App/Vaultspay/irf-service_logs/irf-service.log` (mirrors the Go services' convention).
- systemd: `/etc/systemd/system/irf-service.service` (`java -Xms256m -Xmx1g -jar ...`).
  Restart: `sudo systemctl restart irf-service`; if it hangs in `stop-sigterm`
  (Java ignores SIGTERM), `sudo systemctl kill -s SIGKILL irf-service` then
  `sudo systemctl reset-failed && sudo systemctl start irf-service`.

**Oracle schema work:**
- 14/16 objects already existed (`VISA_IRF_PROGRAMS` 1242 rows,
  `MC_ISS_ACC_RANGE` 168,182, `MC_IRF_PARAMS` 15,869, `UAE_SWITCH_IRF` 23,
  `JAYWAN_IRF` 30; `VW_IPM_DETAILS` is a **view**).
- Created missing `OMAN_NET_IRF` + `OMANNET_BIN_DATA` (identity PKs).
- Legacy `IRF_CALLBACK` (115,314 rows, `ICB_SER_NUMBER NUMBER(10)` identity
  default `ISEQ$$_1888719`, `ICB_LAST_UPDATED DATE NOT NULL`) was missing 13
  entity columns → added via `ALTER TABLE` (`ICB_CARD_CLASSIFICATION`
  VARCHAR2(50), `ICB_CP_MID` VARCHAR2(30), `ICB_DOM_INTL_FLAG` CHAR(1),
  `ICB_FIXED`/`ICB_IRF_AMOUNT`/`ICB_PERCENTAGE`/`ICB_TXN_AMOUNT` NUMBER,
  `ICB_IS_CREDIT` NUMBER(1), `ICB_JOB_NUMBER` NUMBER, `ICB_IRD_CODE`
  VARCHAR2(10), `ICB_MTI` VARCHAR2(4), `ICB_RRN` VARCHAR2(12),
  `ICB_UNIQUE_ID` VARCHAR2(50)).

**Real-data results** (`sec=UAT-IRF-7f3a9c2b`):
- VISA/VSMS card `4001460001234567` → real program match: `irdSerNumber:1987`,
  `irdCode:"F"`, 1.6%, `"NONPREMIUM ALT"`, country `"US"`, cardType `"D"`, domIntlFlag `"I"`.
- MC card `2233726975000000000` → range match (product `MBH`, country HKG),
  audit row written to `MC_IRF_PARAMS`, `irdCode:"85"` (crypt-PAN failure →
  override rates), 2.5%, domIntlFlag `"I"`, cardType `"C"`.
- MDS → `85` fallback; UAESWITCH/JAYWAN → empty VO; ONUS/OMANNET →
  `domIntlFlag:"D"` zeros. Callback enqueue → real Oracle serial (`152966/152967/152968`).
- Go live suite run against the remote via tunnel passed all 8 networks × both mappers.

**Three gotchas discovered on real data (test-input issues, not code bugs):**
1. `IRF_CALLBACK.ICB_INS_CODE` is `NUMBER(2,0)` — `insCode` must be ≤ 99
   (use `1`, the real UAT institution code). `insCode=7085` (the H2 smoke value)
   → `ORA-01438` on enqueue.
2. The MC path keys its audit row on `txnData.serialNumber`
   (`McIrfParamsEntity.MIP_SER_NUMBER`, manually-assigned `@Id`). Omitting
   `serialNumber` from `txnData` → `IdentifierGenerationException` → 500.
   `serialNumber` must always flow through the mappers (§4.3).
3. OMANNET NPE'd when `txnData.network` was absent (`OmanNetIrfCalculationService`
   `getNetwork().toUpperCase()`); `IrfCalculationController` now copies
   `request.network` into `txnData.network` when null.
- `flush`/`retry` need `irf.callback.target.url` set per institution (not set in
  UAT — do not point it at the real scheme webhook by accident). Note `retry` is
  keyed by the **entity serial** (`findById`), not `refSerNumber`.

### 7.3 UAT schema rollback + On-us dependency (2026-08-13)

The DB changes from §7.2 were **reverted against the live UAT schema** the same
day. Verified directly via sqlplus, not guessed.

**Verification before rollback** (Oracle Instant Client 21.9 sqlplus installed
locally on this workspace box — Ubuntu 22.04, `libaio1` via apt, instant client
unzipped to `/opt/oracle/instantclient_21_9`, `sqlplus` symlinked to
`/usr/local/bin`, `/etc/ld.so.conf.d/oracle-instantclient.conf` + `ldconfig`):
- `OMAN_NET_IRF` + `OMANNET_BIN_DATA` present — created 13-AUG-26 together with
  identity sequences `ISEQ$$_10691190`/`ISEQ$$_10691193`.
- `IRF_CALLBACK` (base table created 15-APR-25) had `last_ddl_time = 13-AUG-26`
  and all 13 entity columns (column_ids 10–22).

**Rollback applied** (`irf-db-rollback.sql`): dropped the 13 `ICB_*` columns,
then `OMAN_NET_IRF` + `OMANNET_BIN_DATA`. Post-check: `IRF_CALLBACK` back to its
original 9 columns (`ICB_LAST_UPDATED` still `NOT NULL`), both tables gone.

**New SQL scripts** (idempotent Oracle DDL, repo root):
- `irf-db-upgrade.sql` — creates the 2 OmanNet tables + the 13 `IRF_CALLBACK`
  columns (skips anything already present; OmanNet column sizes are inferred
  from the decompiled entities — confirm before prod).
- `irf-db-rollback.sql` — drops them again (skips anything already absent).

**Scheme impact after the rollback (from source, not yet live-tested):**
- VISA/VSMS, MCI/MDS, UAESWITCH, JAYWAN — **unaffected**: their calculators read
  only their own tables (`VISA_IRF_PROGRAMS`, `MC_ISS_ACC_RANGE`/`MC_IRF_PARAMS`/
  `MC_OVERRIDE_RATES`/`MC_PRODUCT_MAPPING`/`VW_IPM_DETAILS`, `UAE_SWITCH_IRF`,
  `JAYWAN_IRF`), none of which were touched.
- **ONUS is also broken** — `OnusIrfCalculationService` reads the *same* two
  dropped tables (`OmanNetBinRepo.findByBinNumberAndSubRoute` →
  `OMANNET_BIN_DATA`, then `OMAN_NET_IRF` for the rate, route `"onus"`). Both
  `OMANNET` and `ONUS` now return `calculated:false` (null). If On-us must work,
  re-apply the upgrade or give On-us its own tables.
- **Callback endpoints** (`/irf/v1/callback`, `/flush`, `/retry`) now fail with
  `ORA-00904` — `LocalIRFCallbackSender`/`IRFCallbackEntity` still map the 13
  dropped columns. `/irf/v1/calculate` does **not** touch `IRF_CALLBACK`, so it
  is unaffected.
- The **running jar on UAT** still has code that needs the dropped objects:
  keep it stopped, redeploy an older jar, or re-apply `irf-db-upgrade.sql`
  before the TLF/MPGS cutover — §4.5's `enqueue` path requires the columns.

### 7.4 Go MPGS DCF service deployed + live-tested on UAT (2026-08-13)

The **Go MPGS pipeline** (`go/cmd/mpgs-service` + `go/mpgsdcf`) is deployed
to the UAT box and verified end-to-end against real DCF files in the real Oracle
schema and the running `irf-service` jar. This mirrors the Java MPGS batch
(`MpgsDataProcessor` → `POS_TRANSACTIONS` → external HTTP IRF) with **no DDL** —
only INSERT/UPDATE (per user constraint: "dont create or alter tables. insert
and update are fine").

**Service on UAT:**
- Binary: `/App/Vaultspay/mpgs-service` (static `CGO_ENABLED=0` build).
- Env: `/App/Vaultspay/config/mpgs-service/mpgs-service.env` —
  `ORACLE_DSN=oracle://NETWORK_SETTLEMENT_UAT:…@switch-uat…:1521/ORCL`,
  `IRF_SERVICE_URL=http://localhost:8085`, `IRF_SERVICE_SEC=UAT-IRF-7f3a9c2b`,
  `HTTP_PORT=18086`, `INS_CODE=1`, `INTERFACE_CODE_MPGS=13`, `UPDATED_USER=2`,
  `MPGS_SYSTEM_FILE_FORMAT_CODE=116`, `INS_SHORT_NAME=TEST`,
  `RECON_IN_TEST=/vp-switch/Network_Settlement/INPUT`, `MPGS_PROCESS_NAME=MPGS`.
- systemd: `/etc/systemd/system/mpgs-service.service` (root, Restart=always).
  Control: `sudo systemctl {start,stop,restart,status} mpgs-service`;
  health: `curl http://localhost:18086/healthz`.
- Trigger (Java-mirroring): `POST /paymentGateway/v1/processMPGS
  {"fileName":"TTSI2T0…001","network":"MASTERCARD"}`. Also legacy
  `POST /process {"jobNumber","insCode"}`.

**Live results (real files, not the local sample):**
- `TTSI2T0.2025-01-07-09-58-72.001` (74 lines/18 txns) → job `1262`,
  `rows:18 updated:18`; serials 176472–176489, `PTR_NETWORK=MCI`,
  `PTR_TXN_CUR_CODE=784`, `PTR_GEN_STATUS=3`, job status `4`.
- `TTSI2T0.2025-01-03-14-35-46.001` (38 lines) → job `1263`, `rows:9 updated:9`;
  one row `PTR_NETWORK=VISA`, rest MCI. Upload logs `1281/1282` → status `4`
  with totals; the run against the buggy binary left `1281` at status `9`.
- IRF step reached the real jar each txn (irf-service log shows
  `UAE Mc Irf Calculation Start` / the known `Get McIrf UAE !!` fallback for
  the synthetic test PANs → Go logs `no irf`, i.e. `calculated:false`).
- Duplicate guard verified: re-POSTing the same file returns
  `The filename already exists` without inserting.

**go-ora `RETURNING INTO` gotchas (found live, fixed in code):**
1. Use `Exec` + `go_ora.Out{Dest: &id}` — **not** `QueryRow(...).Scan` with
   `database/sql`'s `sql.Out`, and the `Dest` must be a plain `*int`/`*int64`
   (a `*sql.NullInt64` struct → `call register type before use UDT`).
2. go-ora cannot reuse a positional bind (`:5` twice) → `ORA-01008 not all
   variables bound`. Each value needs its own ordinal (`UPL_FILE_NAME :5` /
   `UPL_FILE_ID :8`; the Java mapper sets both to `fileName`).

### 7.4.1 Reprocessing a file already in FILE_UPLOAD_LOG (UAT)

The service rejects any filename that already has a `FILE_UPLOAD_LOG` row
(`isValid` duplicate check → `The filename already exists`). To re-run a file
that was already processed (e.g. to re-test after a code fix), delete the
previous run's rows **in this order** (children before parents). All queries
run as `NETWORK_SETTLEMENT_UAT` via sqlplus:

```bash
sqlplus -S -L 'NETWORK_SETTLEMENT_UAT/"J6erQ$o6E24"@//switch-uat.c3guuusy8mm5.me-central-1.rds.amazonaws.com:1521/ORCL'
```

1. **Find the upload-log row and its job number** (the job is `UPL_PRJ_SER_NUMBER`):
```sql
SELECT UPL_SER_NUMBER, UPL_PRJ_SER_NUMBER, UPL_UPLOAD_STATUS, UPL_TOT_TXN_COUNT
FROM   FILE_UPLOAD_LOG
WHERE  UPL_FILE_NAME = 'DCF14072026.001';
```

2. **Delete the inserted transactions for that job**, then the upload-log row,
   then the processing-job row. Using the values found in step 1
   (`UPL_SER_NUMBER=1261`, `PRJ_SER_NUMBER=1242` in the worked example):
```sql
DELETE FROM POS_TRANSACTIONS WHERE PTR_PRJ_SER_NUMBER = 1242; -- = UPL_PRJ_SER_NUMBER
DELETE FROM FILE_UPLOAD_LOG    WHERE UPL_SER_NUMBER      = 1261;
DELETE FROM PROCESSING_JOBS    WHERE PRJ_SER_NUMBER      = 1242;
COMMIT;
```

3. **Re-POST the file** — the duplicate check now passes:
```bash
curl -s -X POST http://localhost:18086/paymentGateway/v1/processMPGS \
  -H 'Content-Type: application/json' \
  -d '{"fileName":"DCF14072026.001","network":"MASTERCARD"}'
# -> {"message":"MPGS File Processing Completed","jobNumber":1266,"rows":3,"updated":3,"callbacks":0}
```

> Order matters: deleting `POS_TRANSACTIONS` first avoids orphans, and the
> upload-log row must be removed or the duplicate guard blocks the re-run.
> This is test-data cleanup on UAT only — nothing here touches `IRF_CALLBACK`
> or the reverted §7.3 objects.

### 7.5 Jaywan range detection in the DCF parser + live test (2026-08-14)

Jaywan DCF files (e.g. `DCF_JAYWAN.001`) contain **only** `6200/6220/6221/6290/6240`
records — there is **no** `6222` (MC) or `6223` (VISA) record to drive the network,
so the Java-derived processor would leave `Network` empty. The Go parser now
identifies Jaywan by **card range** — the PAN (numeric) must fall inside one of
the `[start,end]` ranges loaded from a CSV at service startup:

```
/vp-switch/Network_Settlement/jaywan_ranges.csv   (6 ranges today; the
/vp-switch copy now has a 7th range — 6690109900000000,6690109999999999 — leave
it as-is, it is not used by the tests)
9784500300000000,9784500399999999
6690090000000000,6690090099999999
6690090100000000,6690090199999999
6690107100000000,6690107199999999
6690095000000000,6690095099999999
6690090300000000,6690090399999999
```

> ⚠️ **Access:** only **Engineer 1** can access the Jaywan ranges file
> (`/home/ec2-user/jaywan_ranges.csv` on `10.100.139.30`, 6 ranges). Engineer 2
> does **not** need to care about it — the local test fixture
> (`/tmp/opencode/jaywan_ranges.csv`, same 6 ranges) is what
> `mpgsdcf/TestLoadJaywanRanges` reads, and the deployed service reads its own
> `/vp-switch/Network_Settlement/jaywan_ranges.csv`.

- **Loading:** `mpgsdcf.LoadJaywanRanges(path)` parses the CSV (skips malformed
  lines with warnings; errors if no ranges parse). Wired in
  `NewFileService` via `Config.JaywanRangesFile` (env `JAYWAN_RANGES_FILE`,
  default `/vp-switch/Network_Settlement/jaywan_ranges.csv`); the loaded ranges
  are logged at startup (`jaywan ranges loaded … count=N`).
- **Matching:** `PanRange.Contains(pan)` compares the whole PAN numerically
  (`math/big`) against `[start,end]` — not a BIN prefix, so a PAN is Jaywan only
  if it is fully inside a range. `Processor.Jaywan` (`JaywanRanges`) is fed to
  `onTxn1()` (`processor.go`): a match sets `Network = "JAYWAN"`.
- **Precedence:** a present network detail record (`6222`→MCI, `6223`→VISA,
  `6224`→AMEX, `6226`→DINERS, `6260`→RUPAY) still overrides the range guess —
  range detection only fills the gap when no detail record exists.
- The resulting `Network` is stored in `PTR_NETWORK` and passed unchanged to
  irf-service, whose `IrfCalculatorRegistry` maps the string `JAYWAN` to
  `JaywanIrfCalculationService` (which looks up `JAYWAN_IRF` by MCC — no BIN
  table involved, see below).

**BIN/range table status (no Jaywan BIN table exists in the DB):**
- `LOCAL_BINS` — only scheme `M` (29 BINs, all "MARS"), no Jaywan.
- `ISSUER_BINS` / `AT_ISSUER_BINS` — 0 rows.
- `ACQUIRER_BINS` — 4 test rows with `ACQ_BIN_TYPE` codes `M/A/J/V`, but
  `AcquirerBinRepo` is never consumed by any calculator in the jar, so the
  `J` value is unused. Which card "is Jaywan" is decided upstream (now by the
  Go parser's range file) when `PTR_NETWORK` is set; irf-service trusts that
  string.

**Live result (`DCF_JAYWAN.001`, 89 lines / 29 txns) → job `1268`:**
- `rows:29 updated:29`, `callbacks:0` (expected — §8 item 13).
- **13 rows** `PTR_NETWORK=JAYWAN` (BIN `66900900` falls in the
  `6690090000000000–6690090099999999` range); **16 rows** `PTR_NETWORK=NULL`
  (BIN `66901099` falls in **no** range — intentionally excluded, per user).
- irf-service log: `JaywanIrfCalculationService : JAYWAN Irf Calculation Start/End`
  for the 13 matched rows (no `McIrf UAE` fallback) — real IRF applied per MCC:
  - MCC `6051` → `JRF_IRF_FIXED=2` (Exchange Houses), `PTR_IRF_FIXED=2`,
    `PTR_IRF_AMOUNT=2`, `PTR_CARD_TYPE=D`, `PTR_CARD_DOM_INTL_FLAG=D`,
    `PTR_REMARKS=Exchange Houses`, `PTR_IRD=NULL` (Jaywan sets no IRD code).
- **Known wrinkle:** Jaywan files have no `6222/6223` record, so the amount
  divisor is never applied — `PTR_TXN_AMOUNT` stores raw units (e.g. `20000`).
  This matches current Java behavior for non-MC/VISA networks; flag if the
  business wants Jaywan amounts divided by 100.

**Tests:** `TestPanRange` (edge/below/above/short/non-numeric),
`TestJaywanRangesContains` (ranges from the CSV), `TestLoadJaywanRanges`
(reads the real CSV, 6 ranges), and `TestParseJaywanSample` (parses the real
`DCF_JAYWAN.001`, asserts 13/29 JAYWAN) — all green.

### 7.6 Go TLF service deployed + live-tested on UAT (2026-08-14)

The **Go TLF service** (`go/cmd/tlf-service` + `go/tlfsvc` + `go/tlf`) is deployed
to the UAT box and verified end-to-end against the real Oracle schema and the
running `irf-service` jar — the online counterpart to the batch MPGS pipeline
(§7.4). It is the Go equivalent of the Java `SwitchFileProcessingController` /
`TxnProcessingService.processOnlineTxn`: receives a Postman-style TLF request,
INSERTs `POS_TRANSACTIONS`, calls `irf-service` over HTTP for IRF calculation,
UPDATEs the IRF columns, and (when the result carries an IRD code) enqueues the
scheme callback server-side.

> **Updated 2026-08-15:** the `/tlf/v1/PostmanTxn` HTTP ingestion path was
> **removed** from the Go code — Kafka is now the sole ingestion path
> (`Consumer.processRequest` → `Stage1` → `WorkerPool.Stage2`, §7.12). The Postman-style
> smoke test below is **historical**; on UAT the service now needs
> `KAFKA_BROKERS`/`KAFKA_GROUP`/`KAFKA_TOPIC` configured to consume.

**Service on UAT:**
- Binary: `/App/Vaultspay/tlf-service` (static `CGO_ENABLED=0` build).
- Env: `/App/Vaultspay/config/tlf-service/tlf-service.env` —
  `ORACLE_DSN` (same `NETWORK_SETTLEMENT_UAT` creds as MPGS), `IRF_SERVICE_URL=http://localhost:8085`,
  `IRF_SERVICE_SEC=UAT-IRF-7f3a9c2b`, `HTTP_PORT=19030`, `INS_CODE=1`,
  `INTERFACE_CODE_TLF=11`, `UPDATED_USER=4`, `TIMESTAMP_JOB_NUMBER=1`,
  `EXCHANGE_RATE=0.27`.
- systemd: `/etc/systemd/system/tlf-service.service` (`Restart=always`).
  Control: `sudo systemctl {start,stop,restart,status} tlf-service`;
  health: `curl http://localhost:19030/healthz`.

**Smoke test (HISTORICAL — `POST /tlf/v1/PostmanTxn` was removed 2026-08-15, the
endpoint now 404s. Kept as the record of the 2026-08-14 UAT live test; real
payload at `/vp-switch/Network_Settlement/payload.json`):**

```bash
curl -s -X POST http://localhost:19030/tlf/v1/PostmanTxn \
  -H 'Content-Type: application/json' \
  -d @/vp-switch/Network_Settlement/payload.json
```

**SMS result** (`sms_dms_indicator:"SMS"` → mode `"S"` → not outgoing):
```json
[{"pan":"************0010","rrn":"621007888375","status":"Transaction status is not successful"}]
```
- DB row 176688: `PTR_SCHEME=JAYWAN`, `PTR_NETWORK=UAESWITCH`, `PTR_DMS_SMS_MODE=S`,
  `PTR_IRD_SER_NUMBER=11`, `PTR_IRF_FIXED=0`, `PTR_IRF_PERCENT=0.5`,
  `PTR_IRF_AMOUNT=4.755`, `PTR_CARD_TYPE=D`, `PTR_CARD_DOM_INTL_FLAG=D`,
  `PTR_CARD_CATEGORY=D`, `PTR_REMARKS="Government and Utilities"`,
  `PTR_INC_STATUS=NA`, `PTR_OUT_STATUS=NA`.
- irf-service log: `UaeSwitchIrfCalculationService : Start/End` at 11:52:15.
- Callback skipped (`overlay.Ird` is empty — UAE Switch returned IRD code blank).

**DMS result** (`sms_dms_indicator:"DMS"` → mode `"D"` → outgoing):
```json
[{"amount":"4.755","cardType":"D","description":"Government and Utilities","domIntlFlag":"D","fixed":"","irdCode":"","pan":"************0010","percentage":"0.5","rrn":"621007888375","status":"Completed"}]
```
- DB row 176691: same IRF columns, `PTR_INC_STATUS=Pending` (SetOutgoing not
  called when outgoing), irf-service log confirms calculate at 12:01:56.
- Both runs: no errors in tlf-service or irf-service logs.

**Fix required to make the deployment pass (applied to this tree):**
1. `go/tlfsvc/store.go`: `SetOutgoing` used column `PTR_INCOMING_STATUS` which
   does **not** exist in the UAT `POS_TRANSACTIONS` table — corrected to
   `PTR_INC_STATUS` (the actual column name, matching the INSERT in
   `mpgsdcf/store.go`). No DDL; the code was wrong.

**UAT schema state recall (§7.3):** the 13 `ICB_*` columns and both OmanNet
tables were reverted 2026-08-13. The callback endpoints (`/irf/v1/callback*`)
will fail with `ORA-00904` until `irf-db-upgrade.sql` is re-applied. The TLF
online service calls `/calculate` (unaffected) and only calls
`/callback` when `overlay.Ird != ""`; for the Jaywan/SMS/DMS payload above
`IrdCode` is blank so no callback is attempted — the SMS run returned the
expected "not successful" and the DMS run returned "Completed" with full IRF
fields, no callback errors.

### 7.6.1 Go module dependency graph

```
go/  (module empay/irf, go 1.24)
├── go/irf/           (# shared HTTP client, DTOs, stub — consumed by both tlf/ and mpgs/)
│   ├── client.go       irf.Client → POST /irf/v1/{calculate,callback,flush,retry}
│   └── irf/stub/       test server (httptest) for CI-safe unit tests
├── go/cryptapi/       (# shared CryptAPI client — consumed by outsvc and tlfsvc; stdlib only)
│   └── cryptapi.go      CryptoClient: GetCardNumber (decrypt) / GetToken (encrypt),
│                       16-token chunking, Basic auth + apiId/clientId headers
├── go/tlf/            (# TLF mapper + HTTP client wrapper)
│   ├── tlf.go           Txn DTO → IrfTxnData, IrfClient{Calculate,EnqueueCallback}
│   └── tlf_test.go      stub-based tests
├── go/mpgs/            (# MPGS mapper + HTTP client wrapper)
│   ├── mpgs.go          Txn DTO → IrfTxnData, IrfClient{Calculate,EnqueueCallback}
│   └── mpgs_test.go     stub-based tests
├── go/tlfsvc/          (# TLF online service: Kafka-driven, two-stage)
│   ├── service.go       Stage1 (validate/decrypt/INSERT at gen_status=9) +
│   │                    Stage2 (calculate → UPDATE IRF → SetOutgoing → callback → SetReady)
│   ├── worker.go        WorkerPool (async stage 2) + Reaper (re-enqueue at-9 rows)
│   ├── payload.go       RequestVo / SwitchExtractVo JSON types
│   ├── mapper.go        NetworkMapping / SchemeMapping / IrfStatusCheck / OutgoingStatusCheck
│   │                    MapToEntity / Entity.ApplyResult / Entity.ToTxn
│   ├── store.go         OracleStore: Insert (via mpgsdcf.InsertEntity) + UpdateIrf + SetOutgoing
│   │                    + SetReady (9→3) + FindPendingIRFRows (reaper scan)
│   └── mapper_test.go   in-process tests (fakeStore + stub IrfClient)
├── go/mpgsdcf/         (# MPGS DCF file parser — shared POS_TRANSACTIONS INSERT)
│   ├── store.go         InsertEntity (86-col INSERT + RETURNING via go_ora.Out)
│   ├── service.go       FileService: ProcessFile, duplicate guard, job/upload inserts
│   ├── processor.go     DCF fixed-width tokenizer → Entity
│   ├── ranges.go        Jaywan PAN range CSV loader
│   └── *.go             (tests)
├── go/mpgssvc/          (# MPGS batch service: ProcessJob)
│   ├── service.go       ProcessJob: SELECT rows → calculate → UPDATE IRF + callback
│   └── service_test.go  row→Txn / updateArgs tests
├── go/cmd/tlf-service/  (# TLF entrypoint — Kafka consumer + HTTP health)
│   └── main.go          Kafka consumer goroutine + GET /healthz (PostmanTxn removed 2026-08-15);
│                        WorkerPool.Start + Reap; TLF_WORKERS/QUEUE_SIZE/REAPER_* env; STAGE_MERCURY
└── go/cmd/mpgs-service/ (# MPGS HTTP entrypoint)
    └── main.go          POST /process, GET /healthz
```

**Mercury staging files (new, 2026-08-14, `go/tlfsvc/`):** `mercury.go` (pure
helpers: `MercuryChargeTypeCode`, `MercuryTypeOfCharge`, `MercuryCardInputMode`,
`MercuryCardInputCapability`, `MercuryGeoArea`, `JulianYDDD`, `AddCheckDigit`,
`MercuryAcqRefData`), `mercury_entity.go` (`MercuryWorkEntity` + `MercurySplitParams`
+ `MapToMercuryAcqTxnEntity`), `mercury_test.go`. `store.go` gains
`FindInterfaceCode`, `FindAcquirerBin`, `FindCurrencyExponent`,
`InsertMercuryWork` (75-col `MERCURY_ACQ_TXN_WORK` INSERT + `RETURNING`);
`service.go` gains `StageMercury` + wiring in `Stage2` (gated on
`Config.StageMercury`); `mapper.go` gains `Config.StageMercury`.

**One-way dependency:** both `go/tlf/` and `go/mpgs/` import `go/irf/` (shared client/DTO).
Both `go/tlfsvc/` and `go/mpgssvc/` import `go/irf/` + their respective mapper
package + `go/mpgsdcf/` (for the shared `InsertEntity` + `Entity`). No package
imports a sibling `*svc` or `*dcf` package that imports it back — no cycles.

### 7.7 Mercury staging port (2026-08-14)

Ported Java `MercurySplitService.mapToMercuryAcqTxnEntity` into the Go TLF
service so Mercury-network transactions are staged into `MERCURY_ACQ_TXN_WORK`
(gate: `stageMercury=true` in `tlfsvc.Config`, env `STAGE_MERCURY=1`).

**What was ported (field-for-field from Java):**
- `go/tlfsvc/mercury.go` — pure helpers: `MercuryChargeTypeCode`
  (full `MercuryChargeTypeUtil` switch + MCC range checks), `MercuryTypeOfCharge`
  (`getTYPCH`), `MercuryCardInputMode` (`getCPTRM`), `MercuryCardInputCapability`
  (`getCRDINP`), `MercuryGeoArea` (`getGEO`), `JulianYDDD` (`%d%03d`, fixed
  zero-padding), `AddCheckDigit` (Luhn), `MercuryAcqRefData` (ARN builder).
- `go/tlfsvc/mercury_entity.go` — `MercuryWorkEntity` (MAT_* columns),
  `MercurySplitParams`, `MapToMercuryAcqTxnEntity` (full builder incl. trlType,
  settlement indicator, moto/ecom, cashBack, GeoArea, ARN).
- Field sourcing notes (Java `TxnProcessingService`): `EncryptedCardNumber` ←
  `switchVo.getTokenIdentifier()`; `CardAccepStreetAddress` ←
  `switchVo.getCardAcceptorStreetAddress()`; `CentreProcDate` not modelled →
  NULL; `posEntryMode` truncated to 3 chars when length 4.
- `store.go` — `FindInterfaceCode` (INT_CODE by category), `FindAcquirerBin`
  (ACQ_BIN + MC ICA by bin type), `FindCurrencyExponent`, `InsertMercuryWork`
  (75-col INSERT + `RETURNING MAT_SER_NUMBER INTO` via `go_ora.Out{Dest:&ser}`
  — the dest must be a pointer).
- `service.go` — `StageMercury` (lookup MERCURY interface code → `'E'` acquirer
  bin → currency exponent → map → insert). Wired into `Stage2` only when
  `net=="MERCURY" && outgoing`; on error a WARN is logged and the transaction
  proceeds (non-fatal); on success the serial is INFO-logged (`"mercury
  staged"`) — the old `resp["mercuryStaged"]` field went away with the
  PostmanTxn response (§7.11).

**Reference data — UAT has NONE of this, values below are LOCAL-ONLY and
fabricated for the replica (confirm real values before any UAT use):**
- `INTERFACES` INT_CODE=21, category/name/short='MERCURY' (ins 1, type 'N').
- `ACQUIRER_BINS` ACQ_BIN='970962', ACQ_BIN_TYPE='E', ACQ_MC_ICA_NO='034540',
  ACQ_DOM_INTL_FLAG='D', ACQ_ARN_SEQ_NO=0 (ins 1).
- `MERCURY_ACQ_TXN_WORK` + `MERCURY_ACQ_TXN_DATA` created locally (identity
  `MAT_SER_NUMBER`, column sizes modelled on `MC_ACQ_TXN_WORK`).

**Verified** end-to-end against the local replica (`/tmp/opencode/replica/probe`):
`POST /tlf/v1/PostmanTxn` (MERCURY DMS payload, mcc 5812, posEntryMode "0710")
produced `MERCURY_ACQ_TXN_WORK` row ser=1/2 with
`chargeType=200, typeOfCharge=TK, geo=784, trl=POI, settlementIndicator=C,
acqInst=034540, acqRefData=29709626226210078883756, posEntryMode=071, mcc=5812,
int_code=21, gen_status=3` — all matching the Java mapping. `go build/vet/test
./...` pass; tlfsvc unit tests (`TestMercury*`, `TestStageMercuryWire/Disabled`)
pass.

### 7.8 Jaywan outgoing XML — V1.3 format port (2026-08-15)

`ProcessJaywanOutgoing` in `go/outsvc/jaywan.go` was reworked from the
decompiled-Java mirror (`JaywanOutgoingServiceImpl` pretty-printed Jackson XML)
to the real UAE Switch Clearing Spec **V1.3** layout, verified **byte-for-byte**
against the reference sample `jaywan.xml` (2903 bytes). The old format emitted a
12-hour timestamp, week-based-year julian date, header fields `nDtSet/nProdCd/
nFlRejInd`, per-line pretty-printed tags and an XML declaration on its own line —
none of which match the sample.

**Ground truth:**
- `jaywan.xml` — reference sample (3 txns, CRLF line endings, no trailing
  newline). Structure: line 1 = `<?xml … standalone="no"?><File><Hdr>…8 tags…
  </Hdr><TxnBlock>`; lines 2..N = one `<Txn>` per line; last line =
  `</TxnBlock><Trl>…</Trl></File>`.
- `JAYWAN_XML_GENERATION.md` — UAE Switch Clearing Specification V1.3 field
  sizes/order.
- `replica/ddl.sql` — UAT-authoritative schema (extracted via `DBMS_METADATA`).

**Key facts learned:**
- `JAYWAN_ACQ_TXN_WORK` has **no** `txnId`/`chPresent`/`procCode` columns. The
  fields are sourced by joining `JAYWAN_NETWORK_DATA` on
  `JWN_PRJ_SER_NUMBER + JWN_TXN_REF_NUMBER ↔ JND_PRJ_SER_NUMBER +
  JND_TXN_REF_NUMBER` (RRN is unreliable — duplicated across txns in the
  sample): `JND_TRANS_IDENTIFIER → nTxnId`, `JND_POS_TXN_STATUS → nPosTxnStat`,
  `JND_PROC_CODE → nProcCd`. `nPosCPInd` has no column anywhere → defaults to
  `"5"` per spec (test injects `0`).
- Header is **8 tags only**: `nMTI=1644, nFunCd=670, nRecNum=00000001,
  nDtTmFlGen` (MMDDhhmmss **24-hour**, Go layout `"0102150405"`), `nMemInstCd`
  (participant ID), `nUnFlNm` (file id), `nFlCatg`, `nVerNum`.
- Txn is **26 tags in fixed order** (sample order, not Java declaration order):
  …`nCrdAcpNm` **AN23** right-padded (sample `"ALI MANZ STORE"` + 9 spaces; the
  spec's AN25 is wrong for the sample), `nCrdAcpCity` AN13 right-padded,
  `nCrdAcpStNm` validated against the Jaywan state codes (invalid → default
  `DU`), `nAmtTxn` N12 **fils** (`TxnAmount × 100`), `nFulParInd` fixed `"F"`,
  `nPosCPInd`/`nPosTxnStat` from network data. `nECIInd` only when
  `MotoEcomIndicator` is non-empty.
- Trailer: `nMTI=1644, nFunCd=671, nRecNum` (txn count + 2), `nUnFlNm`,
  `nTxnCnt` N8, `nRnTtlAmt` N15 integer fils (sum of `TxnAmount × 100`).
- File id: `"000" + participant + julianDateStr(YYDDD) + %02d(fileSequence)`
  (sample `0007846666612621510` = seq 10).

**Implementation:**
- `go/outsvc/jaywan.go` — rewritten: `mapJaywanHeader` (8 tags),
  `mapJaywanEntityToTxn` (26 tags, network-data lookup by `TxnRefNumber`),
  `padRight`, `jaywanValidState`, `jaywanTxnDateTime` (24h), trailer
  `nTxnCnt`/`nRnTtlAmt`, file-id builder. `OutgoingService` gained a
  `now func() time.Time` field (default `time.Now`, set in `NewOutgoingService`)
  so generation time / julian date are deterministic in tests.
- `go/outsvc/jaywan_xml.go` — rewritten: compact one-record-per-line CRLF
  layout, tags concatenated without whitespace, no trailing newline. Dropped
  `jaywanEmptyTag`/`javaDoubleString`/old 12h date helpers.
- `go/outsvc/jaywan_entity.go` — added `JaywanNetworkDataEntity` (JND_* fields;
  `PosCPInd` has no DB column, populated by the test fake).
- `go/outsvc/jaywan_store.go` + `store.go` — added
  `FindJaywanNetworkDataByRef(ctx, prjSerNumber, refs)` to the `Store`
  interface (Oracle IN-clause impl) + `bindJaywanNetworkData`.
- `go/outsvc/jaywanout_test.go` — byte-for-byte test vs `jaywan.xml` using a
  fake store + frozen clock (`s.now` → 2026-08-03 13:37:57, matching the
  sample's `nDtTmFlGen=0803133757` and julian `26215`) + injected network-data
  rows. `go build ./...`, `go vet ./outsvc/`, `go test ./outsvc/` all pass.

### 7.9 Mastercard IRF gap assessment vs. UAE/Oman Implementation Guide (2026-08-15)

Full gap analysis written to **`MC_IRF_GAP_ASSESSMENT.md`** (repo root), driven
by `UAE_Oman_Implementation_Guide.md` (Mastercard *Interchange Manual —
Middle East/Africa*, old ed. 3 Feb 2026 vs. new ed. 4 Aug 2026). It compares the
new manual's confirmed UAE/Oman changes against `McIrfCalculationService`
(`irf-service`) and its source `UAEMcIRFCalculation` (TLF), plus the
`MC_OVERRIDE_RATES`/`MC_PRODUCT_MAPPING`/`MC_ISS_ACC_RANGE` data knobs and the
`VW_IPM_DETAILS` upstream feed.

**Code changes applied (both confirmed items):**
- `McIrfCalculationService.java:327` — Oman commercial "All Other Products"
  bracket threshold `txnAmount < 15000.0` → **`< 5000.0`** (new manual p. 277
  drops the 0.50% split from above USD 15,000 to above USD 5,000).
- `McIrfCalculationService.java:232` — Oman commercial special case regex
  `"MEO|MCO|MWO|MAB"` → **`"MEO|MCO|MWO|MAB|MIO"`** (new manual publishes an
  explicit Oman **MIO** commercial row: Genl 2.15% / GvtSvc 0.70% / Chrtes 0.25 /
  Whole 2.15%, no bracket split).

**Still open (verify with Mastercard / data work — see the gap doc, §5 order):**
- MXG / MXP consumer-debit product-code additions; MRC/MKA/MKD lifecycle removal
  (data: `MC_PRODUCT_MAPPING`/`MC_ISS_ACC_RANGE`).
- Oman "when tokenized" Merchant/Full UCAF rows — engine has no token dimension
  in `getDomOverRide` (does `VW_IPM_DETAILS` supply the tokenized rate?).
- Oman World Legend / World Legend Exclusive tiers reuse World Elite's IRD codes
  (ET/EE/EC/ES/EM/EF) — tier not resolvable by IRD alone.
- UAE IRD 74 retired from criteria (still in MoneySend rate tables);
  TM/PM/WM/EM lifecycled for UAE effective April 2026.
- Dragon Mart / Night Market MAID-based 0.65% rate — MAID never used in rate
  selection today.
- UAE MIO: listed in criteria but no published UAE rate row — confirm before
  enabling.

**Non-changes (do not touch):** Oman World Elite rates, UAE credit matrix, UAE
debit/prepaid rates — all verified byte-for-byte identical between editions.

### 7.10 Crypt API PAN decryption in the Go TLF service (2026-08-15)

Ported Java `TxnProcessingService.getCardNumber(tokenIdentifier)` (→
`com.empay.cryptapi.CryptAPI.getCardNumber`, `decUrl`) into the Go TLF online
service so `switch_crypt_token` is resolved to the real PAN **before** the
irf-service call. Previously the Go port used the payload `pan` directly (the
UAT payloads carry masked PANs); this closes that gap when a CryptAPI endpoint
is configured.

**Shared client (`go/cryptapi`, new package):**
- Extracted the existing outgoing-service `CryptoClient` (moved verbatim from
  `go/outsvc/crypto.go`) into `go/cryptapi/cryptapi.go` — `CryptoConfig`,
  `CardCrypto` interface, `CryptoClient.GetCardNumber([]string) map[string]string`
  (decrypt) / `GetToken([]string) map[string]string` (encrypt), **16-token
  chunking**, Basic auth `cryptUserName:cryptPassword` (Base64), headers
  `apiId`/`clientId`, request bodies `{bankId, accessToken, uuids}` /
  `{cardNumbers}`, response maps `cardNumbers`/`uuids`. A nil map signals total
  failure (mirrors Java's null `DecryptResponseVo` on any error).
- `go/outsvc/crypto.go` now **re-exports** those types via type aliases
  (`type CryptoConfig = cryptapi.CryptoConfig`, `CardCrypto`, `CryptoClient`,
  `NewCryptoClient`) so the outgoing services (`outsvc` +
  `cmd/outgoing-service`) compile unchanged.

**tlfsvc wiring:**
- `Service.Crypto cryptapi.CardCrypto` — nil = not configured (UAT mode: payload
  pan used as-is, so the existing smoke payloads keep working).
- `Stage1` mirrors `getCardNumber` + `processOnlineTxn`: empty
  `switch_crypt_token`, nil decrypt map, or a missing token entry →
  `ErrCryptAPIFailed` + `status:"Crypt API Failed , No CardNumber found"`. The
  Kafka consumer (`Consumer.processRequest`) maps `ErrCryptAPIFailed` to a
  rejection (the former PostmanTxn handler's **HTTP 404** mapping was removed
  with the endpoint, §7.11). On success the decrypted PAN feeds the
  irf-service `calculate` call (`Entity.ToTxn(p, cardNumber)` — `ToTxn` now
  takes the resolved card), while the stored `POS_TRANSACTIONS` row keeps the
  payload (masked) pan, exactly as Java's
  `mapTxnToPosData(...).cardNumber(switchVo.getCardNumber())`.
- `cmd/tlf-service/main.go` enables it when `decUrl` is set (Java property
  names): `decUrl`, `encUrl`, `bankId`, `accessToken`, `cryptUserName`,
  `cryptPassword`, `cryptAppIdEncryption`, `cryptAppIdDecryption`, `cryptClientId`.

**Tests:** `go/cryptapi/cryptapi_test.go` (auth/headers/body, 16-chunk → 2 HTTP
calls for 17 tokens, non-200 → nil, encrypt path) and `go/tlfsvc/service_test.go`
(decrypted card reaches the IRF client, no-crypto fallback keeps the payload
pan, empty-token and unknown-token → `ErrCryptAPIFailed`). `go build`,
`go vet`, `go test ./cryptapi/ ./tlfsvc/ ./outsvc/` pass (the only suite failure
remains the pre-existing environmental `mpgsdcf/TestLoadJaywanRanges`).

### 7.11 PostmanTxn removal + `Calculate` cardNumber fix (2026-08-15)

Two Go-module changes (no Java / `.md` changes; scope was the `go/` code only):

**1. `/tlf/v1/PostmanTxn` removed — Kafka is the sole TLF ingestion path.**
- `go/cmd/tlf-service/main.go`: deleted the `POST /tlf/v1/PostmanTxn` route and
  `postmanTxnHandler`; the HTTP surface is now just `GET /healthz`. The
  `ErrCryptAPIFailed` **HTTP 404** mapping (from §7.10) went with the handler —
  the Kafka consumer now maps it to a rejection. Kafka env wiring
  (`KAFKA_BROKERS`, `KAFKA_GROUP`, `KAFKA_TOPIC`, `ack_TOPIC`, `err_TOPIC`)
  stays.
- `go/tlfsvc/service.go`: deleted the public `ProcessTxn` method (the Postman
  delegate). `ProcessKafkaTxn` (used by `Consumer.processRequest`,
  `kafka.go:199`) drives the shared `processTxn` core, so INSERT → Crypt/IRF →
  UPDATE → SetOutgoing → Mercury staging → callback all run identically.
  → **2026-08-15 (later same day, §7.12):** `ProcessKafkaTxn`/`processTxn`
  were then split into `Stage1` + `Stage2` on a `WorkerPool` + reaper. Nothing
  above is live code anymore — see §7.12 for the current entry point.
- `go/tlfsvc/payload.go`: package doc rewritten — the flow is Kafka-driven and
  the Crypt API **is** ported (the old "intentionally NOT ported" line was
  stale). `RequestVo`/`SwitchExtractVo` doc comments no longer reference
  PostmanTxn.
- `go/tlfsvc/mapper.go`: `ToTxn` comment dropped the "(and Java PostmanTxn)"
  aside; `KafkaIntCode` comment updated. `cmd/tlf-service/main.go` comment
  `INTERFACE_CODE_KAFKA` dropped "Kafka/Postman" → "Kafka path".
- Tests converted to the Kafka entry point: `service_test.go` (4 tests →
  `TestProcessKafkaTxn*`), `mapper_test.go`
  (`TestProcessTxn_JaywanSMSPayload` → `TestProcessKafkaTxn_JaywanSMSPayload`),
  `mercury_test.go` (`TestStageMercuryWire`/`TestStageMercuryDisabled`).
  → **2026-08-15 (later, §7.12):** the tests were then reworked again to
  `Stage1`/`Stage2`/`WorkerPool` (`service_test.go`, `mapper_test.go`,
  `mercury_test.go`).

⚠️ **UAT impact:** the deployed `tlf-service.env` has no `KAFKA_*` vars, so after
this change the UAT service has **no ingestion path** until a Kafka consumer is
configured (see §8 item 20).

**2. `Calculate` now passes the real PAN, not the encrypted token.**
- `go/tlf/tlf.go:137` (`IrfClient.Calculate`) and `go/mpgs/mpgs.go:139` passed
  `t.EncCardNumber` (the token) as the top-level `cardNumber` to
  `irf-service` — a port bug. Java's `fetchIrf(...cardNumber)` passes the
  **decrypted** PAN (`TxnProcessingService.java:150-174`), and the calculators
  do BIN lookups on it (OmanNet/Onus `cardNumber.substring(0,6..9)`, Visa
  `left(cardNumber,9)`, Mastercard range lookup); MPGS Java uses
  `posData.getCardNumber()` (plaintext). Both now pass `t.CardNumber`.
- Doc comments updated on both clients ("cardNumber must be the real PAN... not
  the encrypted token").
- Test fixtures updated: `tlf_test.go` / `mpgs_test.go` `sampleTxn()` →
  `CardNumber: "4111111111111111"`, `EncCardNumber: "tok-001"`.
  `live/live_integration_test.go` already set both fields to the PAN.

**Verify:** `go build ./...`, `go vet ./...`, and
`go test ./cryptapi/ ./tlfsvc/ ./tlf/ ./mpgs/ ./mpgssvc/ ./live/ ./irf/ ./outsvc/`
all pass; the only full-suite failure remains the pre-existing environmental
`mpgsdcf/TestLoadJaywanRanges` (missing `/tmp/opencode/jaywan_ranges.csv` on
Windows). The fixture is fetched from Engineer 1's server copy
(`scp ec2-user@10.100.139.30:/home/ec2-user/jaywan_ranges.csv
/tmp/opencode/`); Engineer 2 does not need to care about the ranges.

> ⚠️ UAT `tlf-service.env` does **not** set `decUrl`, so the deployed service
> still runs in masked-pan mode. To enable real decryption: add the CryptAPI
> props above to `tlf-service.env` and redeploy.

### 7.12 Design note — decoupling the Kafka consumer from the irf-service wait (2026-08-15)

**Problem:** pre-decoupling `Consumer.processRequest` → `ProcessKafkaTxn` blocked on
`IrfClient.Calculate` (30s HTTP timeout, `irf/client.go`) inline in the consume
loop. irf-service latency or an outage stalls Kafka ingestion and couples
consumer throughput to irf-service health.

**Ack contract (verified, removes the ack concern):** the Kafka ack is a plain
`ResponseVo` (`kafka.go:73`, sent at `kafka.go:225`) carrying only
`uniqueId` / `cardAcceptorTid` / `cardAcceptorId` /
`responseMessage="Transaction Successfully Accepted"` / `responseCode` / `mti` /
`rrn` — the switch caller **never receives the IRF result**. The IRF fields
(`amount`, `cardType`, `irdCode`, `"Completed"`) were only in the removed
PostmanTxn HTTP response. So decoupling the calculate changes **nothing** the
switch sees — the only downstream stakeholder is the settlement split pipeline,
which reads `POS_TRANSACTIONS` where `PTR_GEN_STATUS=3`.

**Recommended design — two-stage pipeline with the DB row as outbox + bounded
in-process worker pool (no DDL):**

```
Kafka topic ──> Consumer.processRequest (fast path, no irf-service dependency)
                 validate → dedupe → crypt decrypt → MapToEntity
                 INSERT POS_TRANSACTIONS  (gen_status=9 in-flight, every row)
                 push serial → bounded queue  (block when full = backpressure)
                 ack + commit offset

queue ──> WorkerPool (N goroutines, go/tlfsvc/worker.go)
            pop serial
            Calculate → ApplyResult → UpdateIrf → SetOutgoing
            EnqueueCallback (warn-only)
            flip gen_status 9 → 3      ← last write; row now visible to split

Reaper (ticker in the same service)
  SELECT ser FROM POS_TRANSACTIONS
  WHERE gen_status=9 AND last_updated < now()-TLF_REAPER_AGE → re-enqueue
```

- **Stage 1 (consumer, unchanged rules):** validate, duplicate RRN check,
  crypt decrypt, `MapToEntity`, `store.Insert`. Crypt failure still rejects
  with no INSERT (preserves Java rejection semantics; no outbox state needed).
  **Every** ingested row inserts at **9** — stage 2 finalizes all of them, so
  the split never sees a row before its IRF *and* outgoing/incoming status are
  written. `IrfStatusCheck` (pure function of `responseCode/procCode/dms/rev`)
  only decides whether stage 2 calls `calculate`; it does not bypass the queue.
- **Stage 2 (worker):** `Calculate` → `ApplyResult` → `UpdateIrf` →
  `SetOutgoing` → flip to 3. `EnqueueCallback` stays here (needs the IRF
  result); Mercury staging stays here (reads post-`ApplyResult` fields).
- **Marker = `PTR_GEN_STATUS`:** 9 = IRF in-flight (invisible to the split),
  3 = ready. Reuses existing column/status semantics, split predicate unchanged.
- **Residual race:** a split snapshot taken exactly while a row sits at 9 skips
  it that run. Mitigation: workers finish in seconds while splits run on a
  schedule, or (airtight) one nullable `PTR_IRF_FLAG CHAR(1)` if the DBA
  permits — same logic, zero race.
- **Reaper / poison rows:** re-enqueue after `TLF_REAPER_AGE`; after N attempts
  (e.g. 5) leave the row at 9 and WARN/alert rather than setting 7 (7 would
  silently drop it from settlement — worse than a visible stuck row).
- **Backpressure / ordering:** bounded queue; when full stop consuming → Kafka
  redelivery is the lever. Ordering not required (settlement is DB-driven and
  messages are already unordered across partitions); if ever needed,
  `lane = hash(ser) % N`.
- **Config (wired in `cmd/tlf-service/main.go`):** `TLF_WORKERS=10`,
  `TLF_QUEUE_SIZE=1000`, `TLF_REAPER_INTERVAL=60s`, `TLF_REAPER_AGE=60s`.
- **What stays synchronous:** validate, dedupe, crypt failure rejection, and
  the `INSERT` — only the irf-service wait moves off the consume loop.
- **Not decoupled by this design:** only the *calculate* wait. If the ack ever
  needs to carry IRF fields, only the callback flush can move async (the split
  Java already made).

**✅ Implemented (2026-08-15) — exact code mapping:**
- `service.go`: `processTxn` split into `Stage1` (validate → crypt decrypt →
  `MapToEntity` → `INSERT` at `PTR_GEN_STATUS=9` → return `workItem`) and
  `Stage2` (calculate → `ApplyResult` → `UpdateIrf` → `SetOutgoing` →
  `StageMercury` → `EnqueueCallback` → `SetReady`). `workItem` carries
  `{ser, overlay, payload, cardNumber}`; the decrypted PAN is held only in
  memory.
- `store.go`: added `SetReady` (`UPDATE ... SET PTR_GEN_STATUS=3,
  PTR_LAST_UPDATED=SYSDATE`) and `FindPendingIRFRows` (`WHERE PTR_GEN_STATUS=9
  AND PTR_LAST_UPDATED < :cutoff`). No DDL.
- `worker.go` (new): `WorkerPool` — bounded `chan *workItem` (backpressure:
  `Submit` blocks → consumer stops committing), N worker goroutines run
  `Stage2` and only then remove the item from the in-memory `bySer` registry;
  `Reap`/`reapOnce` scan `FindPendingIRFRows` and re-enqueue items still in
  `bySer`.
- `kafka.go`: `Consumer.Workers` field; `processRequest` runs `Stage1` and
  `Workers.Submit(ctx, w)` instead of the old synchronous call. When
  `Workers == nil` (tests) `Stage2` runs inline. The ack stays the plain
  `ResponseVo` (unchanged contract).
- `cmd/tlf-service/main.go`: starts `WorkerPool` + reaper and reads
  `TLF_WORKERS` (10), `TLF_QUEUE_SIZE` (1000), `TLF_REAPER_AGE` (60s),
  `TLF_REAPER_INTERVAL` (60s).
- Tests: `service_test.go` → `TestStage1*` / `TestStage2AppliesIRFAndReadies` /
  `TestWorkerPoolFinalizesTxn` / `TestWorkerPoolReaperReenqueues`;
  `mercury_test.go` → `TestStageMercuryWire/Disabled` via `Stage2`;
  `fakeStore` gained a mutex + `readyList()`. `go build` / `go vet` /
  `go test ./tlfsvc/ ./cryptapi/ ./tlf/ ./mpgs/ ./mpgssvc/ ./live/ ./irf/`
  pass (only the pre-existing environmental `mpgsdcf/TestLoadJaywanRanges`
  fails). ⚠️ `-race` needs a C compiler (not available on this box).

**Capacity evidence (2026-08-14, `irf_bench_results.json` at repo root):**
irf-service on the UAT loopback, 20 mixed JAYWAN/UAESWITCH/VISA payloads,
10s per concurrency level, 0 errors at every level: concurrency 1 → p50
6.32ms / 166 tps; 5 → p50 10.89ms / 501 tps; 10 → p50 19.64ms / 553 tps;
20 → 36.37ms / 597 tps; 50 → 83.68ms / 686 tps (max sustainable
concurrency 150, worst tail ~541ms). The ~100 msg/s TLF target is trivial;
`TLF_WORKERS=10` keeps p95 ≈ 50ms with the 30s irf-service timeout as a
huge ceiling, so the worker pool will never starve the consumer under
normal load.

**Recovery reality (read before rollout):** the reaper can re-enqueue only
items whose decrypted PAN is still in the pool's memory (`bySer`). Since a
worker goroutine panic kills the whole process, `bySer` items at 9 past the age
threshold are only reachable while the process is alive; after a restart, rows
left at 9 are **logged as orphans** for ops review (the decrypted PAN is not
persisted, so they cannot be auto-replayed). This matches Java, which has no
crash recovery for a mid-`processOnlineTxn` failure either.

### 7.13 UnionPay outgoing port (2026-08-16)

Full UnionPay outgoing settlement-file support added to `go/outsvc`, mirroring
the Mercury port and the UnionPay Part III File Interface manual (v25.2). See
**`unionpayoutgoing.md`** (repo root) for the complete spec map (file naming,
TC000/TC100/TC001 layouts, Block 0/1/2 field maps, flow, env config, tests).

- `go/outsvc/unionpay.go` — `ProcessUnionPayOutgoing` + record builders
  (`unionPayBlock0/1/2`, `unionPayTC000/TC001`, `unionPayFileName`
  `OFCYYMMDD5?C`, `unionPayIsChipTxn` Block-2 gating on POS entry `05/07/95`).
- `go/outsvc/unionpay_entity.go` — `UnionPayAcqTxnWorkEntity`/`UnionPayAcqTxnDataEntity`
  (`UPT_*` columns).
- `go/outsvc/unionpay_store.go` + `store.go` — 10 new `Store` methods
  (count/find/update/delete work, insert data, complete POS status).
- `go/outsvc/controller.go` / `mc.go` / `vo.go` — `UNIONPAY` network wiring +
  routing; `cmd/outgoing-service/main.go` — `UNIONPAY_SYSTEM_CODE` +
  `UNIONPAY_VERSION_TAG` env.
- `go/outsvc/unionpayout_test.go` — `TestUnionPayFileBuilder` (magstripe 387-char
  vs chip 681-char records, header/trailer, CRLF, work→data move, bin sequence),
  `TestUnionPayFileName`, `TestUnionPayIsChipTxn`. `go build/vet/test ./outsvc/`
  pass.

⚠️ **Not deployed to UAT.** Schema is **assumed** (`UP_ACQ_TXN_WORK`/`DATA`,
bin type `U`, `INTERFACES` category `UNIONPAY`) because the live Oracle on
`192.168.29.79:1521` was unreachable from this machine at build time. Confirm
the real DDL + reference rows before deploying.

## 8. TODO (next engineer)

1. ✅ Done — JDK 17 + Maven installed and `irf-common` + `irf-service` build
   successfully; the 3/3 ITs pass (`mvn test -Dtest=IrfCalculationControllerIT`).
   See §7 for the build fixes applied.
2. Cut over TLF: add beans (4.2) → add `TlfTxnMapper` (4.3) → rewrite call-site (4.5) → delete (4.4). Repeat for MPGS (4.4/4.5).
3. ✅ Done — ported `UAEMcIRFCalculation` (MPGS) + TLF MC path → `McIrfCalculationService`; same for UAE-Switch/Jaywan/OmanNet/Onus. ✅ Compiled + smoke-tested on JDK 17 (see §7). The `oprtEnvironment` ISO-code→MC-code normalisation was dropped (see `McIrfCalculationService` javadoc) — reinstate if the raw 2-digit code is needed.
4. Wire `irf.callback.target.url` per institution (single URL today).
5. Replace `LocalIRFCallbackSender.buildRequestJson` (hand-rolled JSON) with Jackson serialization.
6. Swap H2 for prod datasource; tune `hibernate.ddl-auto`.
7. Add OpenAPI + Micrometer metrics for `/irf/**`.
8. ✅ Done — Go client + `tlf`/`mpgs` mapper ports validated against the live jar,
   interop fixes applied, Go DTO/mappers synced field-for-field with the Java
   templates (see §7.1).
9. Go live tests (`go/live`) require a running jar + `IRF_SECRET=change-me`;
   the stub-based tests (`go/irf/stub`) run without it and are the CI-safe set.
   Against the UAT server use `IRF_BASE_URL=http://localhost:18085
   IRF_SECRET=UAT-IRF-7f3a9c2b go test ./live/ -v` (through `ssh -L 18085:localhost:8085`).
   The live samples now use `InsCode=1` (UAT's `ICB_INS_CODE` is `NUMBER(2)`).
10. ✅ Done — deployed to the UAT server against real Oracle and re-verified the
     whole §7 flow there, including the Go live suite (see §7.2).
11. ⚠️ **DB reverted on UAT 2026-08-13 (§7.3):** the 13 `IRF_CALLBACK` columns
     and both OmanNet tables are gone. Re-apply `irf-db-upgrade.sql` before any
     `/callback*` enqueue test or the TLF/MPGS cutover. Note On-us shares the
     OmanNet tables — if On-us is required, keep them.
12. ✅ Done — Go MPGS DCF pipeline deployed to UAT and live-tested end-to-end
     (§7.4): `POST /paymentGateway/v1/processMPGS` reads/parses the DCF,
     INSERTs `POS_TRANSACTIONS` (chunk of 10), runs the external HTTP IRF step,
     and updates job + upload-log statuses. No DDL. Still out of scope (per
     user): `updateCardNumbers` (CryptAPI `:2728/cp-crypto-vault/pan-encryption`),
     MC/VISA splits, and `moveFile()` — see §7.4.
13. ⚠️ `/irf/v1/callback*` still broken on UAT (dropped `ICB_*` columns) — the
     Go flow's `callbacks:0` is expected until `irf-db-upgrade.sql` is re-applied;
     `/calculate` is unaffected, which is all the MPGS pipeline needs today.
14. ✅ Done — Jaywan range detection in the DCF parser (`jaywan_ranges.csv`
     loaded at startup, numeric PAN-in-range match) + live test of
      `DCF_JAYWAN.001` on UAT (job `1268`, 13/29 `PTR_NETWORK=JAYWAN` via real
      IRF; BIN `66901099` intentionally excluded). See §7.5.
15. ✅ Done — Go TLF online service (`go/cmd/tlf-service` + `go/tlfsvc` +
      `go/tlf`) deployed to UAT on port 19030 and live-tested end-to-end with
      the real payload at `/vp-switch/Network_Settlement/payload.json` (§7.6).
      Fixed `PTR_INCOMING_STATUS` → `PTR_INC_STATUS` column name in
      `tlfsvc/store.go` (no DDL, code fix only). The service reads
      `sms_dms_indicator` ("SMS"/"DMS"/"RTS") → mode "S"/"D"/"R", maps it
      through `NetworkMapping`/`IrfStatusCheck`/`OutgoingStatusCheck` (§4.3),
      and calls `irf-service` via HTTP for calculate + callback.
16. ✅ Done — Mercury staging ported into the Go TLF service and verified
       end-to-end against the local replica (§7.7): `MERCURY_ACQ_TXN_WORK` insert
       via `STAGE_MERCURY=1`. ⚠️ UAT has **no** MERCURY `INTERFACES`/`ACQUIRER_BINS`
       rows and no `MERCURY_ACQ_TXN_WORK` table — the local values (INT_CODE=21,
       bin 'E' 970962 / ICA 034540) are fabricated. Get the real values from the
       team, then: (a) create the tables + reference rows on UAT, (b) confirm the
       mapper field sources, (c) enable `STAGE_MERCURY=1` on the deployed service.
17. ✅ Done — Jaywan outgoing XML reworked to the V1.3 format and verified
        byte-for-byte against `jaywan.xml` (§7.8). The join to
        `JAYWAN_NETWORK_DATA` supplies `nTxnId`/`nPosTxnStat`/`nProcCd`. ⚠️
        `nPosCPInd` defaults to `"5"` (spec) — no `chPresent` column exists in
        UAT; if the switch payload's value is required, source it from
        `POS_TRANSACTIONS.PTR_CH_PRESENT` or the raw payload. Also confirm the
        `nCrdAcpNm` AN23 width (sample) vs the spec's AN25 before prod.
18. ✅ Done — Mastercard IRF gap assessment vs. `UAE_Oman_Implementation_Guide.md`
        written to `MC_IRF_GAP_ASSESSMENT.md` (§7.9). Applied the two confirmed
        code changes in `McIrfCalculationService` (Oman `9999` "All Other
        Products" bracket 15,000 → **5,000**; `MIO` added to the Oman commercial
        special case). ⚠️ Remaining items are mostly **data** (`MC_OVERRIDE_RATES`,
        `MC_PRODUCT_MAPPING`, `MC_ISS_ACC_RANGE`: MXG/MXP/MIO rows, MRC/MKA/MKD
        removal) plus **Mastercard confirmations** (tokenized-rate source, World
        Legend/Exclusive tier signal, UAE MIO rate, UAE IRD 74 + TM/PM/WM/EM
        lifecycle, Dragon Mart/Night Market MAID rate) — see §7.9 / the gap doc
        §5 for the recommended order.
19. ✅ Done — Crypt API PAN decrypt ported into the Go TLF service (§7.10):
        shared `go/cryptapi` package (extracted from `outsvc`, re-exported via
        aliases), `Service.Crypto` wiring, `switch_crypt_token` resolved to the
        real PAN for the irf-service call, rejection on decryption failure (was
        HTTP 404 on the removed PostmanTxn handler, §7.11).
        ⚠️ Enabled **only** when `decUrl` is set — UAT `tlf-service.env` still
        runs masked-pan mode; add `decUrl`/`bankId`/`accessToken`/`cryptUserName`/
        `cryptPassword`/`cryptAppIdDecryption`/`cryptClientId` and redeploy to
        use real decryption.
20. ✅ Done — PostmanTxn removed from the Go TLF module (Kafka-only ingestion)
        + `tlf`/`mpgs` `Calculate` cardNumber fix (§7.11).
        ⚠️ **Deployment action:** the UAT `tlf-service.env` has no `KAFKA_*`
        vars, so the deployed service currently has **no ingestion path**.
        Add `KAFKA_BROKERS`, `KAFKA_GROUP`, `KAFKA_TOPIC` (+ `ack_TOPIC`,
        `err_TOPIC`) and redeploy before relying on the UAT service for
        incoming transactions.
21. ✅ Done — Kafka consumer decoupled from the irf-service wait (§7.12):
        `processTxn` split into `Stage1` (validate → decrypt → INSERT at
        `PTR_GEN_STATUS=9` → enqueue) + `Stage2` (calculate → UPDATE IRF →
        SetOutgoing → callback → `SetReady` 9→3) on a bounded `WorkerPool`
        (`go/tlfsvc/worker.go`) + reaper (`FindPendingIRFRows`). Config knobs
        `TLF_WORKERS=10`, `TLF_QUEUE_SIZE=1000`, `TLF_REAPER_AGE/INTERVAL=60`.
        No DDL; ack contract unchanged (plain `ResponseVo`).
        ⚠️ **Deploy action:** UAT `tlf-service.env` still needs `KAFKA_BROKERS`/
        `KAFKA_GROUP`/`KAFKA_TOPIC` (+ `ack_TOPIC`/`err_TOPIC`) — no ingestion
        path until configured. ⚠️ Post-restart orphans at gen_status=9 are
        WARN-logged (decrypted PAN not persisted — see §7.12 "Recovery reality").
22. ✅ Done — UnionPay outgoing settlement-file port (`go/outsvc`) per the
        UnionPay Part III File Interface manual — `OFCYYMMDD5?C` files with
        TC000/TC100/TC001 and Block 0/1/2 (see **`unionpayoutgoing.md`**, §7.13).
        ⚠️ **Not deployed:** schema (`UP_ACQ_TXN_WORK`/`DATA`, bin type `U`,
        `INTERFACES` category `UNIONPAY`) is **assumed** — the live Oracle on
        `192.168.29.79:1521` was unreachable at build time. Confirm DDL +
        reference rows, then deploy with `UNIONPAY_SYSTEM_CODE` set.

## 9. Files created/modified (by this handover)

**`irf-common` (new + edited):**
`irf/IrfCalculateRequest`, `IrfCalculateResponse`, `IrfResultData` (new);
`HttpIrfCalculator`, `HttpIRFCallbackSender` (new);
`IRFCallbackSender` (inner `IRFResultData` promoted to top-level class);
`IrfTxnData` (added `acqInstConCode` + getter/setter; later added `serialNumber` + `txnId` + getter/setter, `meCategoryType` widened to `Character`);
`entities/UAESwitchIRFEntity`, `entities/JaywanIRFEntity`, `entities/OmanNetIRFEntity`,
`entities/OmanNetBinDataEntity` (relocated from TLF, package `com.empay.common.entities`);
`repo/UAESwitchIRFRepo`, `repo/JaywanIRFRepo`, `repo/OmanNetIRFRepo`, `repo/OmanNetBinRepo` (relocated from TLF, package `com.empay.common.repo`).

**`irf-service` (new module):** `pom.xml` (adds `com.oracle.database.jdbc:ojdbc11`,
runtime, BOM-managed), `application.yml`, `IrfServiceApplication`, controllers, calculator beans (Visa relocated + MC/UAE-Switch/Jaywan/OmanNet/Onus ported), `LocalIRFCallbackSender`/`IRFCallbackEntity`/`IRFCallbackRepository`, `IrfServiceProviderConfig`, `IrfCalculationControllerIT` + test config.

**TLF/MPGS (new template files only, not compiled here):**
`tlf-processing-service-java/com/empay/tlfprocessing/mapper/TlfTxnMapper.java` (extended with the MC fields)
`mpgs-java/decompiled/com/empay/mappers/MpgsTxnMapper.java`

**Go module (new):** `go/go.mod`; `go/irf/client.go` (HTTP client, `IrfTxnData`
DTO, `LocalDateTime` type, 401/error handling); `go/irf/stub/` (in-process
contract server for CI-safe tests); `go/tlf/tlf.go` + `go/tlf/tlf_test.go`;
`go/mpgs/mpgs.go` + `go/mpgs/mpgs_test.go`; `go/live/live_integration_test.go`
(requires a running jar).

**Go MPGS DCF pipeline (new, 2026-08-13):** `go/mpgsdcf/` — `dcf.go`
(fixed-width 62xx tokenizer per `BatchConfigurations` ranges), `processor.go`
(stateful `MpgsDataProcessor` port → `Entity`; **2026-08-14:** added Jaywan
range detection — `PanRange`/`JaywanRanges` + `Contains()` wired into `onTxn1`,
§7.5), `ranges.go` (**2026-08-14:** `LoadJaywanRanges` CSV loader for
`jaywan_ranges.csv`), `service.go` (`FileService`:
MASTERCARD validation, duplicate check, `PROCESSING_JOBS` + `FILE_UPLOAD_LOG`
inserts, chunk-of-10 ingest, external IRF via `mpgssvc.ProcessJob`, status
updates; `NewFileService` loads the ranges file), `store.go` (86-column
`POS_TRANSACTIONS` INSERT with
`RETURNING PTR_SER_NUMBER INTO` via `go_ora.Out`), `processor_test.go`
(real-sample + unit tests; **2026-08-14:** + `TestPanRange`,
`TestJaywanRangesContains`, `TestLoadJaywanRanges`, `TestParseJaywanSample`).
`go/cmd/mpgs-service/main.go` adds
`POST /paymentGateway/v1/processMPGS {fileName, network}` + env config
(`JAYWAN_RANGES_FILE`). Deployed on UAT + live-tested (§7.4, §7.5).

**Go TLF service (new, 2026-08-14):** `go/cmd/tlf-service/main.go` —
`GET /healthz`, Kafka consumer goroutine (`KAFKA_*` env; the original
`POST /tlf/v1/PostmanTxn` route was **removed 2026-08-15**, §7.11), env config
(`ORACLE_DSN`, `IRF_SERVICE_URL`, `IRF_SERVICE_SEC`,
`HTTP_PORT` 19030, `INS_CODE` 1, `INTERFACE_CODE_TLF` 11, `UPDATED_USER` 4,
`TIMESTAMP_JOB_NUMBER` 1, `EXCHANGE_RATE` 0.27). `go/tlf/tlf.go` — `Txn` DTO,
`ToIrfData` mapper, `IrfClient` (Calculate/EnqueueCallback via `irf.Client`;
**2026-08-15:** `Calculate` passes the real PAN `t.CardNumber`, not the
token). `go/tlf/tlf_test.go` — stub-based tests. `go/tlfsvc/` — `service.go`
(`Stage1`: validate → decrypt → INSERT at `PTR_GEN_STATUS=9`; `Stage2`:
irfStatusCheck → irf-service calculate → UPDATE IRF
columns → SetOutgoing → enqueue callback → `SetReady` 9→3), `worker.go`
(`WorkerPool` + reaper, **2026-08-15**, §7.12), `payload.go` (`RequestVo` /
`SwitchExtractVo` JSON types), `mapper.go` (`NetworkMapping`, `SchemeMapping`,
`DmsSmsMode`, `IrfStatusCheck`, `OutgoingStatusCheck`, `GetTxnCode`,
`MapToEntity`, `Entity.ApplyResult`, `Entity.ToTxn(p, cardNumber)`), `store.go`
(`OracleStore`: `Insert` via `mpgsdcf.FileService.InsertEntity`, `UpdateIrf`,
`SetOutgoing`; **fix:** `PTR_INCOMING_STATUS` → `PTR_INC_STATUS`),
`mapper_test.go` (in-process tests with `fakeStore` + `tlfStubIrfClient`).
Deployed on UAT (systemd `tlf-service`, port 19030) + live-tested with
`/vp-switch/Network_Settlement/payload.json` (§7.6; PostmanTxn now removed).

**Mercury staging (new, 2026-08-14, §7.7):** `go/tlfsvc/mercury.go` —
`MercuryChargeTypeCode`, `MercuryTypeOfCharge`, `MercuryCardInputMode`,
`MercuryCardInputCapability`, `MercuryGeoArea`, `JulianYDDD`, `AddCheckDigit`,
`MercuryAcqRefData`. `go/tlfsvc/mercury_entity.go` — `MercuryWorkEntity`,
`MercurySplitParams`, `MapToMercuryAcqTxnEntity`. `go/tlfsvc/mercury_test.go`.
`go/tlfsvc/store.go` — added `FindInterfaceCode`, `FindAcquirerBin`,
`FindCurrencyExponent`, `InsertMercuryWork` (75-col INSERT + `RETURNING`).
`go/tlfsvc/service.go` — `StageMercury` wired into `Stage2`; `mapper.go` —
`Config.StageMercury`; `go/cmd/tlf-service/main.go` — `STAGE_MERCURY` env
(default false). Local-only reference data (fabricated; UAT lacks MERCURY
rows/tables — see §7.7): `INTERFACES` INT_CODE=21 'MERCURY', `ACQUIRER_BINS`
bin 'E' 970962 / ICA 034540, `MERCURY_ACQ_TXN_WORK`/`MERCURY_ACQ_TXN_DATA`.
Verified end-to-end on the local replica (probe scripts in
`/tmp/opencode/replica/probe/`: `mercury_e2e.go`, `mercury_verify.go`).

**Jaywan outgoing V1.3 (new, 2026-08-15, §7.8):** `go/outsvc/jaywan.go` —
`ProcessJaywanOutgoing` reworked to the V1.3 format (8-tag header, 26-tag txns,
N8 `nTxnCnt` / N15 `nRnTtlAmt` trailer, file id `"000"+participant+julian+%02d`);
`mapJaywanHeader`/`mapJaywanEntityToTxn`/`padRight`/`jaywanValidState`/
`jaywanTxnDateTime` (MMDDhhmmss 24h). `go/outsvc/jaywan_xml.go` — compact
CRLF one-record-per-line layout, no trailing newline. `go/outsvc/jaywan_entity.go`
— `JaywanNetworkDataEntity`. `go/outsvc/jaywan_store.go` + `store.go` —
`FindJaywanNetworkDataByRef`. `go/outsvc/jaywanout_test.go` — byte-for-byte test
vs `jaywan.xml` (fake store + frozen clock). `go/outsvc/mc.go` —
`OutgoingService.now` clock field; `go/cmd/outgoing-service/main.go` — stale
  comment fixed. `go build/vet/test ./outsvc/` pass.

**UnionPay outgoing (new, 2026-08-16, §7.13):** `unionpayoutgoing.md` (repo root
— full spec map: naming `OFCYYMMDD5?C`, TC000/TC100/TC001 layouts, Block 0/1/2
field maps, flow, env, tests, open items). `go/outsvc/unionpay.go` —
`ProcessUnionPayOutgoing` (`find format` → `find interface` → file-log 1/9 guard →
bin `U` → status-3 work → CryptAPI decrypt → per-chunk seq/name/`inserOutFileLog`/
write/status-4/`updateOutFilelog`/summary → `CompleteUnionPayPosStatus` →
work→data move), `unionPayBlock0/1/2` (`C000`/`E000` bitmap, Block 2 gated on
chip POS entry `05/07/95`), `unionPayTC000/TC001`, `unionPayFileName`,
`unionPayMinor12` (value×10^fd), `unionPayStan`, `unionPayPad`,
`splitUnionPayTransactions` (≤50000/file). `go/outsvc/unionpay_entity.go` —
`UnionPayAcqTxnWorkEntity`/`UnionPayAcqTxnDataEntity` (`UPT_*`). `go/outsvc/unionpay_store.go`
+ `store.go` — 10 new `Store` methods. `go/outsvc/controller.go`/`mc.go`/`vo.go`
— `UNIONPAY` wiring; `go/cmd/outgoing-service/main.go` — `UNIONPAY_SYSTEM_CODE`/
`UNIONPAY_VERSION_TAG` env. `go/outsvc/unionpayout_test.go` — 3 tests (builder
asserts 387-char magstripe vs 681-char chip records, header/trailer, CRLF, data
move, bin seq). `go build/vet/test ./outsvc/` pass. ⚠️ schema assumed — see
§7.13.

**Mastercard IRF gap assessment (new, 2026-08-15, §7.9):** `MC_IRF_GAP_ASSESSMENT.md`
(repo root) — compares `UAE_Oman_Implementation_Guide.md` against
`McIrfCalculationService`/`UAEMcIRFCalculation` and the MC rate data knobs;
includes the applied code changes (Oman `9999` bracket 15,000→5,000,
`MIO` in the Oman commercial special case) and the outstanding
data/Mastercard-confirmation items. `irf-service/.../McIrfCalculationService.java`
edited for those two confirmed items.

**Crypt API decrypt (new, 2026-08-15, §7.10):** `go/cryptapi/cryptapi.go` (new
package — `CryptoClient` moved verbatim from `outsvc`: `GetCardNumber`/`GetToken`,
16-chunk, Basic auth, `apiId`/`clientId` headers, `cardNumbers`/`uuids` maps) +
`go/cryptapi/cryptapi_test.go`. `go/outsvc/crypto.go` — now re-exports the
cryptapi types via aliases (no caller changes). `go/tlfsvc/service.go` —
`Service.Crypto cryptapi.CardCrypto`, `ErrCryptAPIFailed`, `Stage1`
resolves `switch_crypt_token` → PAN (empty token / failed decrypt / missing
entry → `ErrCryptAPIFailed` + `status:"Crypt API Failed , No CardNumber found"`);
stored entity keeps the payload (masked) pan, only the irf-service call gets the
decrypted PAN. `go/tlfsvc/mapper.go` — `ToTxn(p, cardNumber)` signature.
`go/tlfsvc/service_test.go` — decrypt-path tests. `go/cmd/tlf-service/main.go` —
CryptAPI env wiring (`decUrl`/`encUrl`/`bankId`/`accessToken`/`cryptUserName`/
`cryptPassword`/`cryptAppIdEncryption`/`cryptAppIdDecryption`/`cryptClientId`,
enabled when `decUrl` set). `ErrCryptAPIFailed` is mapped to a rejection by the
Kafka consumer — the PostmanTxn handler's HTTP 404 went away with the endpoint
(§7.11).

**DB scripts (new, repo root):** `irf-db-upgrade.sql` / `irf-db-rollback.sql` —
idempotent Oracle DDL for the 2 OmanNet tables + 13 `IRF_CALLBACK` columns.
Rollback executed against live UAT 2026-08-13 and verified (§7.3).

**Local tooling (this workspace box):** Oracle Instant Client 21.9 +
`sqlplus` installed (`/opt/oracle/instantclient_21_9`, symlink to
`/usr/local/bin`, `ld.so.conf.d` entry) for direct UAT DB verification.

## 10. Quick smoke test
```bash
mvn -f irf-common/pom.xml install -DskipTests
mvn -f irf-service/pom.xml spring-boot:run &
sleep 8
curl -s -X POST "http://localhost:8085/irf/v1/calculate?sec=change-me" \
  -H 'Content-Type: application/json' \
  -d '{"network":"VISA","insCode":7085,"cardNumber":"411111111","txnData":{"mcc":"5499","txnAmount":100.0,"posEntryMode":"012","approvalCode":"ABC123","acqInstConCode":"784"}}'
```

### Go MPGS DCF pipeline smoke test (UAT)
```bash
# service is live on the UAT box (systemd mpgs-service, port 18086)
curl -s -X POST http://localhost:18086/paymentGateway/v1/processMPGS \
  -H 'Content-Type: application/json' \
  -d '{"fileName":"<a fresh DCF .001 from /vp-switch/Network_Settlement/INPUT>","network":"MASTERCARD"}'
# -> {"message":"MPGS File Processing Completed","jobNumber":N,"rows":18,"updated":18,"callbacks":0}
# build locally:  CGO_ENABLED=0 go build -o /tmp/mpgs-service ./cmd/mpgs-service
```

### Go MPGS Jaywan smoke test (UAT)
```bash
# a Jaywan DCF (only 6200/6220/6221/6290/6240 records) is auto-detected via the
# card-range file (JAYWAN_RANGES_FILE, default /vp-switch/Network_Settlement/jaywan_ranges.csv) —
# network is derived from the file, so "MASTERCARD" still passes the gate
curl -s -X POST http://localhost:18086/paymentGateway/v1/processMPGS \
  -H 'Content-Type: application/json' \
  -d '{"fileName":"DCF_JAYWAN.001","network":"MASTERCARD"}'
# -> {"message":"MPGS File Processing Completed","jobNumber":1268,"rows":29,"updated":29,"callbacks":0}
# verify:  SELECT PTR_NETWORK, PTR_IRF_FIXED, PTR_IRF_AMOUNT, PTR_REMARKS
#          FROM POS_TRANSACTIONS WHERE PTR_PRJ_SER_NUMBER = 1268;
#          -> 13 JAYWAN (BIN 66900900), 16 NULL (BIN 66901099 outside all ranges)
```

### Go TLF online smoke test (UAT) — endpoint removed 2026-08-15
```bash
# NOTE (2026-08-15): POST /tlf/v1/PostmanTxn was removed from the Go code
# (§7.11). The curl below is HISTORICAL — it 404s on current builds. Ingestion
# is now Kafka-only (KAFKA_TOPIC → Consumer.processRequest → Stage1 →
# WorkerPool.Stage2).
# service was live on the UAT box (systemd tlf-service, port 19030)
curl -s -X POST http://localhost:19030/tlf/v1/PostmanTxn \
  -H 'Content-Type: application/json' \
  -d @/vp-switch/Network_Settlement/payload.json
# -> [{"amount":"4.755","cardType":"D","description":"Government and Utilities",
#      "domIntlFlag":"D","fixed":"","irdCode":"","pan":"************0010",
#      "percentage":"0.5","rrn":"621007888375","status":"Completed"}]
#
# SMS variant (sms_dms_indicator:"SMS" in payload.json):
# -> [{"pan":"************0010","rrn":"621007888375",
#      "status":"Transaction status is not successful"}]
#
# build locally:  CGO_ENABLED=0 go build -o /tmp/tlf-service ./cmd/tlf-service
# verify DB:
#   SELECT PTR_SCHEME, PTR_NETWORK, PTR_DMS_SMS_MODE, PTR_IRF_PERCENT,
#          PTR_IRF_AMOUNT, PTR_CARD_TYPE, PTR_CARD_DOM_INTL_FLAG,
#          PTR_REMARKS, PTR_INC_STATUS, PTR_OUT_STATUS
#   FROM POS_TRANSACTIONS WHERE PTR_RET_REF_NUMBER = '621007888375';
```

### Go TLF Mercury staging smoke test (local replica, 2026-08-14)
```bash
# NOTE (2026-08-15): the POST /tlf/v1/PostmanTxn endpoint is removed (§7.11).
# This smoke test used it on port 19031; the equivalent path is now the Kafka
# entry (Stage1 → Stage2 with STAGE_MERCURY=1). The 2026-08-14 result below is
# historical; the staging logic itself is covered by TestStageMercuryWire.
# local-only: needs INTERFACES 21 (MERCURY), ACQUIRER_BINS 'E', and
# MERCURY_ACQ_TXN_WORK created on the local Oracle replica (see §7.7).
# Run the service with STAGE_MERCURY=1 (off by default).
STAGE_MERCURY=1 HTTP_PORT=19031 \
  /tmp/opencode/go/bin/go run ./cmd/tlf-service &   # from go/
curl -s -X POST http://localhost:19031/tlf/v1/PostmanTxn \
  -H 'Content-Type: application/json' \
  -d '{"payload":{"bank_id":"CPBA","sub_route":"mercury","scheme":"MERCURY",
      "switch_mti":"0130","pan":"6690109700100010","switch_crypt_token":"e2e-tok-001",
      "processing_code":"000000","amount":"000000095100","transmission_date":"0814103000",
      "stan":"888375","local_time":"103000","local_date":"0814","exp_date":"2812",
      "settlement_date":"20260814","mcc":"5812","acq_inst_country_code":"840",
      "pos_entry_mode":"0710","pan_sequence_number":"0","pos_condition_code":"00",
      "txn_fee_amount":"0.00","rrn":"621007888375","auth_code":"962981",
      "network_response_code":"00","service_restriction_code":"00","terminal_id":"TERM001",
      "merchant_id":"MERCH001","card_acceptor_name":"VAULTSPAY","card_acceptor_st_addr":"Street 1",
      "card_acceptor_city":"Dubai","card_acceptor_pin_code":"00000",
      "card_acceptor_country_code":"840","currency_code":"840","settlement_code":"840",
      "additional_amount":"0.00","channel":"POS","settlement_indicator":"Y",
      "onus_offus_indicator":"ONUS","sms_dms_indicator":"DMS"}}'
# response includes "mercuryStaged":"<MAT_SER_NUMBER>"
# verify:
#   SELECT MAT_SER_NUMBER,MAT_CHARGE_TYPE,MAT_TRL_TYPE,MAT_SETL_INDICATOR,
#          MAT_ACQ_REF_DATA,MAT_POS_ENTRY_MODE
#   FROM NETWORK_SETTLEMENT_UAT.MERCURY_ACQ_TXN_WORK;
#   -> 200 / POI / C / 29709626226210078883756 / 071
```

### Mercury scheme gate + staging a 26-txn payload (local replica, 2026-08-16)

**Finding — Mercury outgoing gate is scheme-exact-match.** `OutgoingStatusCheck`
(`go/tlfsvc/mapper.go`) for a MERCURY network accepts a scheme only if it exactly
equals `DISCOVER`, `DINERS`, `MERCURY`, or `RUPAY` (via `matchAny`, which is
case-insensitive but **not** prefix/substring). A payload carrying
`scheme="DINERS CLUB INTERNATIONAL"` therefore **fails** the gate
(`SchemeMapping` only upper-cases it, it never becomes `"DINERS"`), so those
txns are never staged to `MERCURY_ACQ_TXN_WORK`. The same value is also rejected
earlier by the Kafka `validate` scheme `@Length(max=20)` check
(`go/tlfsvc/kafka.go`) — `"DINERS CLUB INTERNATIONAL"` is 25 chars. To stage
those txns the payload must say `"DINERS"`.

Verified against the real payload pulled from the box
(`/tmp/opencode/payload.json`, 26 txns, all `sub_route:"mercury"`, MTI 0110,
proc `000000`, `network_response_code:00`, DMS): after fixing
`DINERS CLUB INTERNATIONAL → DINERS`, **all 26** pass
`NetworkMapping + OutgoingStatusCheck` and get staged to `MERCURY_ACQ_TXN_WORK`
(via `Stage1`+`Stage2` with `STAGE_MERCURY=1`).

**Running the irf-service jar locally (needed so Stage2 hits a real
`/irf/v1/calculate` instead of a dead endpoint):**
- The jar is on **`10.100.139.30`** (`.232` has only `splitProcessAndStaging`
  + the other `/Netset` jars; no irf-service there):
  `scp -i ~/switch-uat-key-pair.pem ec2-user@10.100.139.30:/App/Vaultspay/irf-service/irf-service-1.0.0-SNAPSHOT.jar /tmp/opencode/`
- Run against the **local replica** (UAT RDS is unreachable from this machine):
  ```
  cd /tmp/opencode
  SERVER_PORT=8085 IRF_SERVICE_SEC=UAT-IRF-7f3a9c2b \
  SPRING_DATASOURCE_URL='jdbc:oracle:thin:@//localhost:1521/FREEPDB1' \
  SPRING_DATASOURCE_USERNAME=NETWORK_SETTLEMENT_UAT SPRING_DATASOURCE_PASSWORD='J6erQ$o6E24' \
  SPRING_JPA_HIBERNATE_DDL_AUTO=none \
  nohup java -Xms256m -Xmx1g -jar irf-service-1.0.0-SNAPSHOT.jar > /tmp/opencode/irf-service_logs/console.log 2>&1 &
  ```
  Health: `POST /irf/v1/calculate` → 401 without `?sec=`, 200 with
  `?sec=UAT-IRF-7f3a9c2b`. MERCURY has **no** IRF calculator in the jar, so a
  Mercury `calculate` returns `{"calculated":false,"result":null}` — non-fatal
  in Stage2 (IRF columns just get the cleared shape; staging still happens).
  The jar needs JDK 17 (system `/usr/bin/java` is 17.0.19).
- Re-run result: 26/26 txns staged, `MERCURY_ACQ_TXN_WORK` rows = 26 (RRNs
  `622607652332`–`622611102183`), zero `irf calculate failed` warnings.

> ⚠️ Only **Engineer 1** can access the `.30`/`.232` boxes; Engineer 2 does not
> need to care — the staging driver + local irf-service run is reproducible from
> `/tmp/opencode` on Engineer 1's machine.
