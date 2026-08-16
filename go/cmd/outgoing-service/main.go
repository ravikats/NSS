// Command outgoing-service is the runnable Go port of the Java
// splitProcessAndStaging service: it generates outgoing settlement files for
// the payment networks. Mastercard IPM, Visa Base II, Jaywan XML and Mercury
// EIF generation are ported; Amex and collection-only flows are still pending.
//
// Endpoints:
//
//	POST /outgoing/v1/generateOutgoing      {"network","fromDate","toDate"}
//	POST /outgoing/v1/revertLastOutgoing    {"network"}
//	PUT  /outgoing/v1/updateRejectedData    (not yet implemented)
//	POST /outgoing/v1/generateCollectionOnly (not yet implemented)
//	POST /outgoing/v1/revertLastCollectionOnly (not yet implemented)
//	GET  /healthz                           200 OK
//
// Environment:
//
//	ORACLE_DSN            go-ora URL, e.g. oracle://user:pass@host:1521/ORCL
//	HTTP_PORT             listen port (default 19031)
//	INS_CODE              institution code (default 1)
//	INS_SHORT_NAME        e.g. IRF
//	UPDATED_USER          updated-by user id (default 4)
//	MASTERCARD_SYSTEM_CODE / VISA_SYSTEM_CODE / JAYWAN_SYSTEM_CODE / AMEX_SYSTEM_CODE / MERCURY_SYSTEM_CODE
//	GCO_SYSTEM_CODE       MC collection system code
//	GOC_SYSTEM_CODE       Visa collection system code
//	RECON_OUT_<INS_SHORT_NAME>  base output directory for files
//	PROCESSING_MODE       IPM header processing mode
//	encUrl / decUrl / bankId / accessToken / cryptUserName / cryptPassword
//	cryptAppIdEncryption / cryptAppIdDecryption / cryptClientId
package main

import (
	"context"
	"database/sql"
	"fmt"
	"log/slog"
	"net/http"
	"os"
	"strconv"
	"time"

	"empay/irf/outsvc"

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
	port := env("HTTP_PORT", "19031")
	insShortName := env("INS_SHORT_NAME", "")

	db, err := sql.Open("oracle", dsn)
	if err != nil {
		log.Error("open oracle", "err", err)
		os.Exit(1)
	}
	db.SetMaxOpenConns(20)
	db.SetMaxIdleConns(5)
	db.SetConnMaxLifetime(30 * time.Minute)

	ctx, cancel := context.WithTimeout(context.Background(), 15*time.Second)
	if err := db.PingContext(ctx); err != nil {
		log.Error("ping oracle", "err", err)
		cancel()
		os.Exit(1)
	}
	cancel()

	cfg := outsvc.OutgoingConfig{
		InsCode:            envInt("INS_CODE", 1),
		InsShortName:       insShortName,
		UpdatedUser:        envInt("UPDATED_USER", 4),
		MastercardSysCode:  envInt("MASTERCARD_SYSTEM_CODE", 0),
		VisaSysCode:        envInt("VISA_SYSTEM_CODE", 0),
		JaywanSysCode:      envInt("JAYWAN_SYSTEM_CODE", 0),
		AmexSysCode:        envInt("AMEX_SYSTEM_CODE", 0),
		MercurySysCode:     envInt("MERCURY_SYSTEM_CODE", 0),
		UnionPaySysCode:    envInt("UNIONPAY_SYSTEM_CODE", 0),
		GCOSysCode:         envInt("GCO_SYSTEM_CODE", 0),
		GOCSysCode:         envInt("GOC_SYSTEM_CODE", 0),
		ReconOutDir:        env("RECON_OUT_"+insShortName, "."),
		ProcessingMode:     env("PROCESSING_MODE", ""),
		CurrencyCodeKafka:  env("CURRENCY_CODE_KAFKA", ""),
		ProductCode:        env("PRODUCT_CODE", ""),
		FileCategory:       env("FILE_CATEGORY", ""),
		VersionNumber:      env("VERSION_NUMBER", ""),
		UnionPayVersionTag: env("UNIONPAY_VERSION_TAG", "TEST"),
	}

	crypto := outsvc.NewCryptoClient(outsvc.CryptoConfig{
		EncURL:               env("encUrl", ""),
		DecURL:               env("decUrl", ""),
		BankID:               env("bankId", ""),
		AccessToken:          env("accessToken", ""),
		Username:             env("cryptUserName", ""),
		Password:             env("cryptPassword", ""),
		CryptAppIDEncryption: env("cryptAppIdEncryption", ""),
		CryptAppIDDecryption: env("cryptAppIdDecryption", ""),
		ClientID:             env("cryptClientId", ""),
	})

	store := outsvc.NewOracleStore(db)
	svc := outsvc.NewOutgoingService(cfg, store, crypto)
	ctl := outsvc.NewOutgoingController(svc)

	mux := http.NewServeMux()
	mux.HandleFunc("POST /outgoing/v1/generateOutgoing", ctl.GenerateOutgoing)
	mux.HandleFunc("POST /outgoing/v1/revertLastOutgoing", ctl.RevertLastOutgoing)
	mux.HandleFunc("PUT /outgoing/v1/updateRejectedData", ctl.UpdateRejectedData)
	mux.HandleFunc("POST /outgoing/v1/generateCollectionOnly", ctl.GenerateCollectionOnly)
	mux.HandleFunc("POST /outgoing/v1/revertLastCollectionOnly", ctl.RevertLastCollectionOnly)
	mux.HandleFunc("GET /healthz", func(w http.ResponseWriter, _ *http.Request) {
		w.WriteHeader(http.StatusOK)
		fmt.Fprintln(w, "OK")
	})

	log.Info("outgoing-service listening", "port", port)
	if err := http.ListenAndServe(":"+port, mux); err != nil {
		log.Error("listen", "err", err)
		os.Exit(1)
	}
}
