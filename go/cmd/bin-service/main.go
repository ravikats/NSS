// Command bin-service is the runnable Go port of bin-processing-service.
// It receives a BIN range file upload request, validates it, records the
// PROCESSING_JOBS / FILE_UPLOAD_LOG rows, and processes the file per network
// (MASTERCARD T067/T068, VISA ARDEF, JAYWAN CSV, OMANNET xlsx, MERCURY CSV)
// in a background goroutine, then moves the file to the processed/rejected dir.
//
// Endpoints:
//
//	POST /bin/v1/processBin     {"fileName": "...", "network": "..."} -> BinResponseVO
//	DELETE /bin/v1/deleteBinFile {"fileName": "...", "network": "..."} -> BinResponseVO
//	GET  /                       -> OK
//
// Environment:
//
//	ORACLE_DSN              go-ora URL, e.g. oracle://user:pass@host:1521/ORCL
//	HTTP_PORT               listen port (default 19029)
//	UPDATED_USER            updated-by user id (default 2)
//	INS_CODE                institution code (default 1)
//	INS_SHORT_NAME          institution short name, e.g. TEST (default TEST)
//	BIN_INTERFACE_CODE      interface/int code (default 12)
//	MC_BIN_FORMAT_CODE      Mastercard FOR_CODE (default 2)
//	VISA_BIN_FORMAT_CODE    Visa FOR_CODE (default 3)
//	JAYWAN_BIN_FORMAT_CODE  Jaywan FOR_CODE (default 61)
//	OMANNET_BIN_FORMAT_CODE OmanNet FOR_CODE
//	MERCURY_BIN_FORMAT_CODE Mercury FOR_CODE
//	RECON_IN_<INS>          input directory for BIN files (default /vp-switch/INPUT/)
//	RECON_PROCESSED_<INS>   processed directory (default /vp-switch/OUTPUT/)
//	RECON_REJECTED_<INS>    rejected directory (default /vp-switch/OUTPUT/)
//	ACL_INTEGRATION_FLAG    "1" enables startup ACL sync
//	ACL_URL                 ACL endpoint
//	ACL_USER_APP_ID         Bearer token for ACL
//	BIN_PERMISSION_FILE     path to the permission file JSON
package main

import (
	"context"
	"database/sql"
	"encoding/json"
	"fmt"
	"log/slog"
	"net/http"
	"os"
	"strconv"
	"strings"
	"time"

	"empay/irf/binsvc"

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
	port := env("HTTP_PORT", "19029")
	insShort := env("INS_SHORT_NAME", "TEST")

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

	formatCodes := map[string]int{
		"MASTERCARD": envInt("MC_BIN_FORMAT_CODE", 2),
		"VISA":       envInt("VISA_BIN_FORMAT_CODE", 3),
		"JAYWAN":     envInt("JAYWAN_BIN_FORMAT_CODE", 61),
		"OMANNET":    envInt("OMANNET_BIN_FORMAT_CODE", 0),
		"MERCURY":    envInt("MERCURY_BIN_FORMAT_CODE", 0),
	}
	cfg := binsvc.Config{
		InsCode:          envInt("INS_CODE", 1),
		InsShortName:     insShort,
		UpdatedUser:      envInt("UPDATED_USER", 2),
		BinInterfaceCode: envInt("BIN_INTERFACE_CODE", 12),
		FormatCodes:      formatCodes,
		ReconIn:          env("RECON_IN_"+insShort, "/vp-switch/INPUT/"),
		ReconProcessed:   env("RECON_PROCESSED_"+insShort, "/vp-switch/OUTPUT/"),
		ReconRejected:    env("RECON_REJECTED_"+insShort, "/vp-switch/OUTPUT/"),
		ACLFlag:          env("ACL_INTEGRATION_FLAG", "0") == "1",
		ACLURL:           env("ACL_URL", ""),
		ACLUserAppID:     env("ACL_USER_APP_ID", ""),
		ACLPermFile:      env("BIN_PERMISSION_FILE", ""),
	}

	svc := binsvc.New(db, log, cfg)
	svc.SyncACL()

	mux := http.NewServeMux()
	mux.HandleFunc("POST /bin/v1/processBin", binHandler(svc))
	mux.HandleFunc("DELETE /bin/v1/deleteBinFile", deleteHandler(svc))
	mux.HandleFunc("GET /", func(w http.ResponseWriter, _ *http.Request) {
		w.WriteHeader(http.StatusOK)
		fmt.Fprintln(w, "OK")
	})

	log.Info("bin-service listening", "port", port)
	srv := &http.Server{Addr: ":" + port, Handler: mux, ReadHeaderTimeout: 10 * time.Second}
	if err := srv.ListenAndServe(); err != nil {
		log.Error("server", "err", err)
		os.Exit(1)
	}
}

type binRequest struct {
	FileName string `json:"fileName"`
	Network  string `json:"network"`
}

func binHandler(svc *binsvc.Service) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req binRequest
		if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
			http.Error(w, "bad json: "+err.Error(), http.StatusBadRequest)
			return
		}
		if strings.TrimSpace(req.FileName) == "" {
			http.Error(w, "fileName must not be empty/blank;", http.StatusBadRequest)
			return
		}
		if len(req.FileName) > 60 {
			http.Error(w, "fileName should not exceed 60 characters;", http.StatusBadRequest)
			return
		}
		if !isNetwork(req.Network) {
			http.Error(w, "Invalid network;", http.StatusBadRequest)
			return
		}
		status := http.StatusOK
		resp := svc.ProcessBin(r.Context(), req.FileName, req.Network)
		if strings.HasPrefix(resp.Message, "Unexpected error occurred") {
			status = http.StatusInternalServerError
		}
		writeJSON(w, status, resp)
	}
}

func deleteHandler(svc *binsvc.Service) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req binRequest
		if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
			http.Error(w, "bad json: "+err.Error(), http.StatusBadRequest)
			return
		}
		writeJSON(w, http.StatusOK, svc.BinFileDeletion(r.Context(), req.FileName, req.Network))
	}
}

func isNetwork(n string) bool {
	switch strings.ToUpper(n) {
	case "MASTERCARD", "VISA", "JAYWAN", "OMANNET", "MERCURY":
		return true
	}
	return false
}

func writeJSON(w http.ResponseWriter, status int, v any) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(status)
	_ = json.NewEncoder(w).Encode(v)
}
