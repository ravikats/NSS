# Kafka Consumer and IRF Performance Review

## Findings

1. `TxnProcessingService.java:182` calls `irfCallback(txnEntity)` inside the Kafka processing path, and `IRFCallbackService.java:100` performs a synchronous HTTP POST before the Kafka listener completes. This is likely the largest contributor to 2.1s latency because every transaction waits for the callback service response.

2. `TxnProcessingService.java:150` decrypts the card number synchronously via `CryptAPI.getCardNumber`, and `CryptAPI.java:54` performs another blocking HTTP call. So each transaction already waits on at least two external HTTP calls: decrypt and IRF callback.

3. `TlfProcessingServiceApplication.java:23-25` creates a plain `RestTemplate` with no connect/read timeouts. `TxnProcessingService.java:1537` also creates another plain `RestTemplate`. If decrypt or callback is slow, the Kafka listener thread can block for a long time.

4. `TxnFetchKafkaService.java:43-44` uses a default `@KafkaListener` with no visible concurrency setting. With one listener thread per partition/container, the service processes messages serially, so 2.1s per transaction means roughly 0.48 TPS per consumer thread.

5. `TxnFetchKafkaService.java:47` creates a new `ObjectMapper` for every Kafka message. This is not the 2.1s root cause, but it is unnecessary per-message overhead.

6. `TxnFetchKafkaService.java:67` builds a new Bean Validation `ValidatorFactory` per message through `Validation.buildDefaultValidatorFactory()`. That factory is expensive and should be injected/reused.

7. `TxnFetchKafkaService.java:72` calls `validationService.validateTxnRequest`, and `TxnProcessingService.java:148` calls the same validation again inside `processOnlineTxn`. For valid Kafka messages this duplicates validation and at least one duplicate-check DB query: `ValidationService.java:51`.

8. `TxnProcessingService.java:171`, `TxnProcessingService.java:187`, and many later outgoing branches call `saveAndFlush` repeatedly. `saveAndFlush` forces immediate DB round trips. In a transactional method this defeats batching and adds latency.

9. `UAEMcIRFCalculation.java:149-151` writes `McIrfParamsEntity` using `saveAndFlush`, then immediately reads `IpmDetailsView` by transaction serial number. If the view calculation depends on database-side logic, Mastercard IRF has a mandatory write/read synchronization point per transaction.

10. Visa/Mastercard IRF paths perform several sequential DB lookups to choose rates and overrides. Example: Visa can try multiple `VisaIrfProgramRepo` queries before settling on a fee program. Mastercard can query BIN range, IPM view, product mapping, and multiple override combinations.

11. `IRFCallbackService.java:96`, `TxnFetchKafkaService.java:46`, `TxnFetchKafkaService.java:100`, and entity `toString()` logging can emit large payloads and sensitive data. Logging the full input/request/response synchronously can add I/O overhead and security risk.

## Likely 2.1s Breakdown

The 2.1s is probably not pure IRF arithmetic. The calculation itself is trivial. The latency is from blocking I/O in the same Kafka thread:

```text
Kafka listener
-> deserialize
-> bean validation
-> validation DB queries
-> duplicate validation again
-> decrypt HTTP call
-> insert transaction, flush
-> IRF DB lookups
-> update transaction, flush
-> insert callback row, flush
-> callback HTTP call
-> update callback row, flush
-> outgoing work DB lookup/insert/update
-> send ack
```

For Mastercard specifically:

```text
decrypt HTTP
+ DB BIN range lookup
+ insert mc_irf_params and flush
+ read ipm_details_view
+ optional product mapping lookup
+ 1-3 override lookups
+ callback HTTP
```

## Highest Impact Fixes

1. Make IRF callback asynchronous.

Use the Kafka flow to save the callback request as `PENDING`, then return/ack. Send the callback from a separate worker, scheduler, or dedicated Kafka topic. This removes one external HTTP call from per-transaction latency.

2. Add timeouts to all `RestTemplate` clients.

Set short connect/read timeouts for decrypt and callback. Example target: connect `200-500ms`, read based on SLA. Without this, one slow dependency blocks the consumer.

3. Stop validating twice for Kafka.

`TxnFetchKafkaService.parseMessage` validates before calling `processOnlineTxn`, then `processOnlineTxn` validates again. Add a processing method that accepts already validated input or skip internal validation when `kafkaFlag=true` and caller already validated.

4. Reuse `ObjectMapper` and `Validator`.

Inject the existing `ObjectMapper` and `jakarta.validation.Validator`. Do not create them per message.

5. Replace most `saveAndFlush` calls with `save`.

Within `@Transactional`, use `save` and let commit flush once. Keep `flush` only where the next query truly depends on the row being visible, such as the Mastercard `McIrfParamsEntity` -> `IpmDetailsView` flow.

6. Increase Kafka listener concurrency after DB/API bottlenecks are controlled.

Add concurrency via Kafka listener container factory or `@KafkaListener(concurrency = "...")`, and ensure topic partitions are sufficient. This improves throughput, not single-message latency.

## IRF-Specific Optimizations

1. Cache static IRF rate tables.

UAE Switch, Jaywan, OmanNet, Visa fee programs, Mastercard overrides, product mapping, and BIN ranges look like reference data. Cache by lookup key with invalidation when admin APIs update IRF tables.

2. Add/check DB indexes for lookup fields.

Critical indexes include:

```text
pos_transaction(msg_type_id, rrn, proc_code)
pos_transaction(rrn, proc_code, msg_type_id, gen_status)
visa_iss_acc_range(iss_range_low, iss_range_high)
visa_irf_program(region, card_type, fp_type, fp_value, txn_limit_indicator, qualifier_indicator)
mc_override_rates(ird, card_type, mcc, txn_limit_indicator)
mc_iss_acq_range(iss_range_low, iss_range_high, active_code)
IRF MCC columns
```

3. Review Mastercard IPM view dependency.

`UAEMcIRFCalculation` persists params just to read a view. If that view is expensive, this will dominate MC processing. Consider moving the IRD/rate decision into Java/service code or replacing the view with a stored function/procedure returning the result in one DB call.

4. Avoid full callback HTTP in IRF calculation path.

IRF calculation should return data only. Callback delivery should be post-processing.

## Quick Wins

1. Remove per-message `new ObjectMapper()`.
2. Inject `Validator` instead of creating a factory.
3. Remove duplicate validation.
4. Add `RestTemplate` timeouts.
5. Change non-essential `saveAndFlush` to `save`.
6. Make IRF callback async.

These are the changes most likely to reduce single-message time from approximately 2.1s to whatever the decrypt call plus DB path actually costs.
