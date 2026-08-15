// Command tlf-service is the runnable Go TLF (network settlement) processor.
// It is the online counterpart of mpgs-service: it consumes TLF requests from
// Kafka, inserts POS_TRANSACTIONS, calls the external irf-service over HTTP
// for IRF calculation, writes the IRF columns back, and enqueues the scheme
// callback (irf-service owns IRF_CALLBACK).
//
// Endpoints:
//
//	GET  /healthz   -> 200 OK
//
// Transaction ingestion is via the Kafka consumer (see below).
//
// Environment:
//
//	ORACLE_DSN       go-ora URL, e.g. oracle://user:pass@host:1521/ORCL
//	IRF_SERVICE_URL  irf-service base URL (default http://localhost:8085)
//	IRF_SERVICE_SEC  irf-service shared secret (default change-me)
//	HTTP_PORT        listen port (default 19030)
//	INS_CODE         TLF institution code (default 1)
//	INTERFACE_CODE_TLF  interface/int code (default 11)
//	INTERFACE_CODE_KAFKA  interface code for the Kafka path (default = INTERFACE_CODE_TLF)
//	UPDATED_USER     updated-by user id (default 4)
//	TIMESTAMP_JOB    job number (default 1)
//	EXCHANGE_RATE    settlement exchange rate (default 0.27)
//	STAGE_MERCURY    stage MERCURY-network txns into MERCURY_ACQ_TXN_WORK (default false)
//
// CryptAPI PAN decryption (Go port of com.empay.cryptapi.CryptAPI) is enabled
// when decUrl is set; switch_crypt_token is then resolved to the PAN before the
// irf-service call (Java getCardNumber -> fetchIrf):
//
//	decUrl / encUrl / bankId / accessToken / cryptUserName / cryptPassword
//	cryptAppIdEncryption / cryptAppIdDecryption / cryptClientId
//
// Kafka ingest (Go port of TxnFetchKafkaService) is enabled when KAFKA_BROKERS
// is set. Stage 1 (validate + insert) runs on the consumer; stage 2 (IRF
// calculate + finalize) runs asynchronously on a worker pool (§7.12 of
// IRF_SERVICE_HANDOVER.md), so the consumer never blocks on irf-service:
//
//	KAFKA_BROKERS        comma-separated broker list, e.g. localhost:9092
//	KAFKA_GROUP          consumer group (default fetch-txn-group)
//	KAFKA_TOPIC_TXN      txn topic (default oracle_TRANSACTIONS)
//	KAFKA_TOPIC_ACK      ack topic (default ack_TOPIC)
//	KAFKA_TOPIC_ERR      err topic (default err_TOPIC)
//	TLF_WORKERS          stage-2 worker goroutines (default 10)
//	TLF_QUEUE_SIZE       stage-2 bounded queue depth (default 1000)
//	TLF_REAPER_AGE       re-enqueue rows stuck at gen_status=9 for this long (s, default 60)
//	TLF_REAPER_INTERVAL  reaper scan period (s, default 60)
package main

import (
	"context"
	"database/sql"
	"fmt"
	"log/slog"
	"net/http"
	"os"
	"os/signal"
	"strconv"
	"strings"
	"syscall"
	"time"

	"empay/irf/cryptapi"
	"empay/irf/tlfsvc"

	_ "github.com/sijms/go-ora/v2"
)

func env(key, def string) string {
	if v := os.Getenv(key); v != "" {
		return v
	}
	return def
}

func envInt(key string, def int) int {
	if v := os.Getenv(key); v != "" {
		if n, err := strconv.Atoi(v); err == nil {
			return n
		}
	}
	return def
}

func main() {
	log := slog.New(slog.NewTextHandler(os.Stdout, nil))
	slog.SetDefault(log)

	dsn := env("ORACLE_DSN", "")
	if dsn == "" {
		log.Error("ORACLE_DSN is required (oracle://user:pass@host:1521/service)")
		os.Exit(1)
	}
	baseURL := env("IRF_SERVICE_URL", "http://localhost:8085")
	secret := env("IRF_SERVICE_SEC", "change-me")
	port := env("HTTP_PORT", "19030")

	db, err := sql.Open("oracle", dsn)
	if err != nil {
		log.Error("open oracle", "err", err)
		os.Exit(1)
	}
	db.SetMaxOpenConns(20)
	db.SetMaxIdleConns(5)
	db.SetConnMaxLifetime(30 * time.Minute)

	ctx, cancel := context.WithTimeout(context.Background(), 20*time.Second)
	defer cancel()
	if err := db.PingContext(ctx); err != nil {
		log.Error("ping oracle", "err", err)
		os.Exit(1)
	}
	log.Info("oracle connected")

	cfg := tlfsvc.Config{
		InsCode:       envInt("INS_CODE", 1),
		IntCode:       envInt("INTERFACE_CODE_TLF", 11),
		KafkaIntCode:  envInt("INTERFACE_CODE_KAFKA", envInt("INTERFACE_CODE_TLF", 11)),
		UserSerNumber: envInt("UPDATED_USER", 4),
		JobNumber:     envInt("TIMESTAMP_JOB_NUMBER", 1),
		ExchangeRate:  envFloat("EXCHANGE_RATE", 0.27),
		StageMercury:  envBool("STAGE_MERCURY", false),
	}
	svc := tlfsvc.New(db, baseURL, secret, log, cfg)
	svc.CountryLookup = nil // TLV country alpha-3 lookup is optional for the test payload

	// CryptAPI PAN decryption. Enabled when decUrl is configured; the service
	// then resolves switch_crypt_token -> PAN (Java getCardNumber) before the
	// irf-service call and fails with 404 on decryption failure.
	if decURL := env("decUrl", ""); decURL != "" {
		svc.Crypto = cryptapi.NewCryptoClient(cryptapi.CryptoConfig{
			EncURL:               env("encUrl", ""),
			DecURL:               decURL,
			BankID:               env("bankId", ""),
			AccessToken:          env("accessToken", ""),
			Username:             env("cryptUserName", ""),
			Password:             env("cryptPassword", ""),
			CryptAppIDEncryption: env("cryptAppIdEncryption", ""),
			CryptAppIDDecryption: env("cryptAppIdDecryption", ""),
			ClientID:             env("cryptClientId", ""),
		})
		log.Info("cryptapi decrypt enabled")
	}

	ctx, stop := signal.NotifyContext(context.Background(), os.Interrupt, syscall.SIGTERM)
	defer stop()

	// Kafka ingest (Go port of TxnFetchKafkaService). Enabled when KAFKA_BROKERS
	// is set; IRF calculation still happens on the external irf-service, now
	// asynchronously on a worker pool (stage 2) so the consumer never blocks on it.
	if brokers := splitCSV(env("KAFKA_BROKERS", "")); len(brokers) > 0 {
		kafkaCfg := tlfsvc.KafkaConfig{
			Brokers:   brokers,
			GroupID:   env("KAFKA_GROUP", tlfsvc.DefaultKafkaGroup),
			TxnTopic:  env("KAFKA_TOPIC_TXN", tlfsvc.DefaultKafkaTxnTopic),
			AckTopic:  env("KAFKA_TOPIC_ACK", tlfsvc.DefaultKafkaAckTopic),
			ErrTopic:  env("KAFKA_TOPIC_ERR", tlfsvc.DefaultKafkaErrTopic),
			JobNumber: cfg.JobNumber,
			FileName:  "KAFKA",
		}
		workers := tlfsvc.NewWorkerPool(svc, envInt("TLF_WORKERS", 10), envInt("TLF_QUEUE_SIZE", 1000))
		workers.Start(ctx)
		reaperAge := time.Duration(envInt("TLF_REAPER_AGE", 60)) * time.Second
		reaperEvery := time.Duration(envInt("TLF_REAPER_INTERVAL", 60)) * time.Second
		go workers.Reap(ctx, reaperAge, reaperEvery)

		consumer := &tlfsvc.Consumer{Svc: svc, Workers: workers, Log: log, Cfg: kafkaCfg}
		go func() {
			if err := consumer.Run(ctx); err != nil && ctx.Err() == nil {
				log.Error("kafka consumer", "err", err)
			}
			log.Info("kafka consumer stopped")
		}()
		log.Info("kafka consumer started",
			"brokers", brokers, "topic", kafkaCfg.TxnTopic, "group", kafkaCfg.GroupID,
			"workers", envInt("TLF_WORKERS", 10), "queue", envInt("TLF_QUEUE_SIZE", 1000),
			"reaper_age", reaperAge, "reaper_interval", reaperEvery)
	}

	mux := http.NewServeMux()
	mux.HandleFunc("GET /healthz", func(w http.ResponseWriter, _ *http.Request) {
		w.WriteHeader(http.StatusOK)
		fmt.Fprintln(w, "OK")
	})

	log.Info("tlf-service listening", "port", port)
	srv := &http.Server{Addr: ":" + port, Handler: mux, ReadHeaderTimeout: 10 * time.Second}
	go func() {
		if err := srv.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Error("server", "err", err)
			stop()
		}
	}()
	<-ctx.Done()
	shutdownCtx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	if err := srv.Shutdown(shutdownCtx); err != nil {
		log.Error("shutdown", "err", err)
	}
}

func envFloat(key string, def float64) float64 {
	if v := os.Getenv(key); v != "" {
		if f, err := strconv.ParseFloat(v, 64); err == nil {
			return f
		}
	}
	return def
}

func envBool(key string, def bool) bool {
	if v := os.Getenv(key); v != "" {
		if b, err := strconv.ParseBool(v); err == nil {
			return b
		}
	}
	return def
}

func splitCSV(s string) []string {
	if strings.TrimSpace(s) == "" {
		return nil
	}
	var out []string
	for _, p := range strings.Split(s, ",") {
		if p = strings.TrimSpace(p); p != "" {
			out = append(out, p)
		}
	}
	return out
}
