// Command mpgs-service is the runnable Go MPGS DCF processor that feeds the
// irf-service jar over HTTP for IRF calculation.
//
// It exposes a small HTTP API to trigger a job:
//
//	POST /process   {"jobNumber": 42, "insCode": 1}   -> job Summary JSON
//	GET  /healthz                                     -> 200 OK
//
// Environment:
//
//	ORACLE_DSN       go-ora URL, e.g. oracle://user:pass@host:1521/ORCL
//	IRF_SERVICE_URL  irf-service base URL (default http://localhost:8085)
//	IRF_SERVICE_SEC  irf-service shared secret (default change-me)
//	HTTP_PORT        listen port (default 18086)
//	JAYWAN_RANGES_FILE  CSV of Jaywan card ranges (default /vp-switch/Network_Settlement/jaywan_ranges.csv)
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
	"time"

	"empay/irf/mpgsdcf"
	"empay/irf/mpgssvc"

	_ "github.com/sijms/go-ora/v2"
)

type processRequest struct {
	JobNumber int `json:"jobNumber"`
	InsCode   int `json:"insCode"`
}

type mpgsRequest struct {
	FileName string `json:"fileName"`
	Network  string `json:"network"`
}

func env(key, def string) string {
	if v := os.Getenv(key); v != "" {
		return v
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
	port := env("HTTP_PORT", "18086")

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

	svc := mpgssvc.New(db, baseURL, secret, log)

	cfg := mpgsdcf.Config{
		InsCode:          envInt("INS_CODE", 1),
		IntCode:          envInt("INTERFACE_CODE_MPGS", 13),
		UserSerNumber:    envInt("UPDATED_USER", 2),
		SystemFileFormat: envInt("MPGS_SYSTEM_FILE_FORMAT_CODE", 116),
		InsShortName:     env("INS_SHORT_NAME", "TEST"),
		InputDir:         env("RECON_IN_TEST", env("RECON_IN_DIR", "/vp-switch/INPUT")),
		ProcessName:      env("MPGS_PROCESS_NAME", "MPGS"),
		JaywanRangesFile: env("JAYWAN_RANGES_FILE", "/vp-switch/Network_Settlement/jaywan_ranges.csv"),
	}
	fileSvc := mpgsdcf.NewFileService(db, baseURL, secret, log, cfg)

	mux := http.NewServeMux()
	mux.HandleFunc("POST /process", processHandler(svc))
	mux.HandleFunc("POST /paymentGateway/v1/processMPGS", processMPGSHandler(fileSvc))
	mux.HandleFunc("GET /healthz", func(w http.ResponseWriter, _ *http.Request) {
		w.WriteHeader(http.StatusOK)
		fmt.Fprintln(w, "OK")
	})

	log.Info("mpgs-service listening", "port", port)
	srv := &http.Server{Addr: ":" + port, Handler: mux, ReadHeaderTimeout: 10 * time.Second}
	if err := srv.ListenAndServe(); err != nil {
		log.Error("server", "err", err)
		os.Exit(1)
	}
}

func processHandler(svc *mpgssvc.Service) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req processRequest
		if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
			http.Error(w, fmt.Sprintf("bad json: %v", err), http.StatusBadRequest)
			return
		}
		if req.JobNumber == 0 {
			http.Error(w, "jobNumber is required", http.StatusBadRequest)
			return
		}
		ctx, cancel := context.WithTimeout(r.Context(), 30*time.Minute)
		defer cancel()
		sum, err := svc.ProcessJob(ctx, req.JobNumber, req.InsCode)
		if err != nil {
			slog.Error("process failed", "job", req.JobNumber, "err", err)
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		w.Header().Set("Content-Type", "application/json")
		_ = json.NewEncoder(w).Encode(sum)
	}
}

func processMPGSHandler(svc *mpgsdcf.FileService) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req mpgsRequest
		if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
			http.Error(w, fmt.Sprintf("bad json: %v", err), http.StatusBadRequest)
			return
		}
		if req.FileName == "" {
			http.Error(w, "fileName is required", http.StatusBadRequest)
			return
		}
		ctx, cancel := context.WithTimeout(r.Context(), 30*time.Minute)
		defer cancel()
		res, err := svc.ProcessFile(ctx, req.FileName, req.Network)
		w.Header().Set("Content-Type", "application/json")
		if err != nil {
			slog.Error("mpgs processing failed", "file", req.FileName, "err", err)
			w.WriteHeader(http.StatusInternalServerError)
			_ = json.NewEncoder(w).Encode(res)
			return
		}
		_ = json.NewEncoder(w).Encode(res)
	}
}

func envInt(key string, def int) int {
	if v := os.Getenv(key); v != "" {
		if n, err := strconv.Atoi(v); err == nil {
			return n
		}
	}
	return def
}
