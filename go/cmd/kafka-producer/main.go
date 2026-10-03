package main

import (
	"context"
	"encoding/json"
	"fmt"
	"os"
	"time"

	"github.com/segmentio/kafka-go"
)

func main() {
	broker := "localhost:9092"
	topic := "oracle_TRANSACTIONS"
	payloadPath := "/tmp/opencode/payload.json"
	if len(os.Args) > 1 {
		payloadPath = os.Args[1]
	}

	data, err := os.ReadFile(payloadPath)
	if err != nil {
		fmt.Fprintln(os.Stderr, "read payload:", err)
		os.Exit(1)
	}

	var payloads []map[string]interface{}
	if err := json.Unmarshal(data, &payloads); err != nil {
		fmt.Fprintln(os.Stderr, "parse payload:", err)
		os.Exit(1)
	}
	fmt.Printf("Loaded %d payloads\n", len(payloads))

	w := &kafka.Writer{
		Addr:         kafka.TCP(broker),
		Topic:        topic,
		Balancer:     &kafka.LeastBytes{},
		BatchTimeout: 100 * time.Millisecond,
	}
	defer w.Close()

	for i, p := range payloads {
		wrapped := map[string]interface{}{
			"payload": p,
		}
		msg, err := json.Marshal(wrapped)
		if err != nil {
			fmt.Fprintf(os.Stderr, "marshal %d: %v\n", i, err)
			os.Exit(1)
		}
		err = w.WriteMessages(context.Background(), kafka.Message{
			Key:   []byte(fmt.Sprintf("%d", i)),
			Value: msg,
		})
		if err != nil {
			fmt.Fprintf(os.Stderr, "produce %d: %v\n", i, err)
			os.Exit(1)
		}
	}
	fmt.Printf("Produced %d messages to %s\n", len(payloads), topic)
}
