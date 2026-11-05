// Command cdc-emulator emulates the upstream CDC event engine (the Oracle
// log-based change-data-capture bridge that publishes POS transaction events to
// the oracle_TRANSACTIONS topic consumed by tlf-service).
//
// Instead of a live Oracle → Kafka CDC pipeline, it reads a dump of switch
// events (payload.json: a JSON array of SwitchExtractVo objects) and publishes
// each one as a CDC event message shaped exactly like the ones
// TxnFetchKafkaService consumes: {"payload": {...}, "op": "c", ...}. Messages
// are keyed by RRN (falling back to a sequence number) so they land in the same
// partition and are processed in order.
//
// Usage (from go/):
//
//	export ORACLE_DSN='oracle://user:pass@localhost:1521/FREEPDB1' # optional, enables the duplicate guard
//	go run ./cmd/cdc-emulator -payload payload.json
//
// Flags:
//
//	-payload   JSON array of transaction events (default payload.json)
//	-broker    Kafka bootstrap server (default localhost:9092)
//	-topic     CDC output topic (default oracle_TRANSACTIONS)
//	-key-field payload field used as the message key (default rrn)
//	-rate-ms   delay between events, to emulate a live CDC stream (default 0)
//	-force     publish even when some RRNs are already staged in POS_TRANSACTIONS
//
// When ORACLE_DSN is set the emulator first checks POS_TRANSACTIONS for RRNs
// that were already staged (the same (msg type, rrn, proc code) duplicate check
// the consumer's validate performs) and refuses to publish unless -force is
// given — re-publishing a dump would otherwise mass-reject at the consumer.
package main

import (
	"context"
	"database/sql"
	"encoding/json"
	"flag"
	"fmt"
	"log"
	"os"
	"strconv"
	"time"

	"github.com/segmentio/kafka-go"
	_ "github.com/sijms/go-ora/v2"
)

type event struct {
	Payload map[string]any `json:"-"` // raw txn object, written under "payload"
	Op      string         `json:"-"`
	TSMS    int64          `json:"-"`
}

func main() {
	payloadPath := flag.String("payload", "payload.json", "JSON array of transaction events")
	broker := flag.String("broker", "localhost:9092", "Kafka bootstrap server")
	topic := flag.String("topic", "oracle_TRANSACTIONS", "CDC output topic")
	keyField := flag.String("key-field", "rrn", "payload field used as the message key")
	rateMS := flag.Int("rate-ms", 0, "delay between events (ms), 0 = as fast as possible")
	force := flag.Bool("force", false, "publish even when RRNs are already staged in POS_TRANSACTIONS")
	flag.Parse()

	data, err := os.ReadFile(*payloadPath)
	if err != nil {
		log.Fatalf("cdc-emulator: read %s: %v", *payloadPath, err)
	}
	var txns []map[string]any
	if err := json.Unmarshal(data, &txns); err != nil {
		log.Fatalf("cdc-emulator: parse %s (expect a JSON array of transactions): %v", *payloadPath, err)
	}
	if len(txns) == 0 {
		log.Fatalf("cdc-emulator: %s contains no events", *payloadPath)
	}

	// Duplicate guard: the consumer's validation rejects a (msg type, rrn,
	// proc code) already present in POS_TRANSACTIONS, so a naive replay of a
	// dump would only mass-reject at the consumer. Refuse unless -force.
	if dsn := os.Getenv("ORACLE_DSN"); dsn != "" {
		staged, err := countStaged(dsn, txns)
		if err != nil {
			log.Fatalf("cdc-emulator: duplicate guard: %v", err)
		}
		if len(staged) > 0 && !*force {
			if len(staged) > 5 {
				staged = staged[:5]
			}
			log.Fatalf("cdc-emulator: %d of %d RRNs are already staged in POS_TRANSACTIONS (e.g. %v); use -force to replay anyway",
				len(staged), len(txns), staged)
		}
	}

	w := &kafka.Writer{
		Addr:         kafka.TCP(*broker),
		Topic:        *topic,
		Balancer:     &kafka.LeastBytes{},
		BatchTimeout: 100 * time.Millisecond,
	}
	defer w.Close()

	now := time.Now()
	ctx := context.Background()
	for i, txn := range txns {
		key := str(txn, *keyField)
		if key == "" {
			key = strconv.Itoa(i)
		}
		event := map[string]any{
			// The field TxnFetchKafkaService deserialises into RequestVo.
			"payload": txn,
			// Debezium-style CDC metadata; unknown fields are ignored by the
			// consumer but make the stream look like the real CDC engine.
			"op":    "c",
			"ts_ms": now.Add(time.Duration(i) * time.Millisecond).UnixMilli(),
			"source": map[string]any{
				"table": "POS_TRANSACTIONS",
			},
		}
		value, err := json.Marshal(event)
		if err != nil {
			log.Fatalf("cdc-emulator: marshal event %d: %v", i, err)
		}
		if *rateMS > 0 {
			time.Sleep(time.Duration(*rateMS) * time.Millisecond)
		}
		if err := w.WriteMessages(ctx, kafka.Message{
			Key:   []byte(key),
			Value: value,
		}); err != nil {
			log.Fatalf("cdc-emulator: publish event %d (rrn=%s): %v", i, key, err)
		}
		log.Printf("cdc-emulator: published event %d/%d key=%s op=c", i+1, len(txns), key)
	}
	log.Printf("cdc-emulator: published %d CDC events to %s (broker %s)", len(txns), *topic, *broker)
}

func str(m map[string]any, field string) string {
	v, ok := m[field].(string)
	if !ok {
		return ""
	}
	return v
}

// countStaged returns the RRNs from txns that already exist in
// POS_TRANSACTIONS under the same (msg type, rrn, proc code) triple checked by
// the consumer's duplicate validation (tlfsvc FindByMsgTypeIdAndRrnAndProcCode).
func countStaged(dsn string, txns []map[string]any) ([]string, error) {
	db, err := sql.Open("oracle", dsn)
	if err != nil {
		return nil, err
	}
	defer db.Close()
	ctx, cancel := context.WithTimeout(context.Background(), 30*time.Second)
	defer cancel()
	if err := db.PingContext(ctx); err != nil {
		return nil, err
	}
	var staged []string
	for _, txn := range txns {
		mti, rrn, proc := str(txn, "switch_mti"), str(txn, "rrn"), str(txn, "processing_code")
		if rrn == "" {
			continue
		}
		var n int
		if err := db.QueryRowContext(ctx,
			`SELECT COUNT(*) FROM POS_TRANSACTIONS
			 WHERE PTR_MSG_TYPE_ID = :1 AND PTR_RET_REF_NUMBER = :2 AND PTR_PROC_CODE = :3`,
			mti, rrn, proc).Scan(&n); err != nil {
			return nil, fmt.Errorf("duplicate check rrn=%s: %w", rrn, err)
		}
		if n > 0 {
			staged = append(staged, rrn)
		}
	}
	return staged, nil
}
