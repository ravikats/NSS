// Package stub implements a faithful, in-process replica of the irf-service REST
// contract (paths, `?sec=` auth, JSON shapes) so Go clients can be tested
// end-to-end without the Java service. It exists because no JDK 17 is available
// in this workspace to run irf-service.
package stub

import (
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"testing"

	"empay/irf/irf"
)

const DefaultSecret = "test-secret"

// NewIRFService returns a started httptest.Server that behaves like irf-service.
func NewIRFService(t *testing.T) *httptest.Server {
	t.Helper()
	mux := http.NewServeMux()

	mux.HandleFunc("/irf/v1/calculate", func(w http.ResponseWriter, r *http.Request) {
		if !okSec(r, t) {
			w.WriteHeader(http.StatusUnauthorized)
			return
		}
		var req irf.CalculateRequest
		if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
			t.Errorf("calculate decode: %v", err)
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}
		// Unsupported networks (amex, etc.) mirror the Java stubs -> 501.
		if req.Network != "" && req.Network != "VISA" && req.Network != "VSMS" {
			w.WriteHeader(http.StatusNotImplemented)
			return
		}
		// No BIN range -> Java Visa calculator returns the "XX"/0.0 fallback VO.
		amt := 0.0
		pct := 0.0
		fixed := 0.0
		vo := irf.IrfResultVo{
			IrdCode: "AO", IrfPercentage: &pct, IrfFixed: &fixed,
			IrfAmount: &amt, IrfAmountUSD: &amt, DomIntlFlag: "I", IrfCountry: "XX",
		}
		_ = json.NewEncoder(w).Encode(irf.CalculateResponse{Calculated: true, Result: &vo})
	})

	mux.HandleFunc("/irf/v1/callback", func(w http.ResponseWriter, r *http.Request) {
		if !okSec(r, t) {
			w.WriteHeader(http.StatusUnauthorized)
			return
		}
		var data irf.CallbackData
		if err := json.NewDecoder(r.Body).Decode(&data); err != nil {
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}
		if data.Rrn == "" {
			http.Error(w, "missing rrn", http.StatusBadRequest)
			return
		}
		w.WriteHeader(http.StatusOK)
		_ = json.NewEncoder(w).Encode(50001)
	})

	mux.HandleFunc("/irf/v1/callback/flush", func(w http.ResponseWriter, r *http.Request) {
		if !okSec(r, t) {
			w.WriteHeader(http.StatusUnauthorized)
			return
		}
		w.WriteHeader(http.StatusOK)
	})

	mux.HandleFunc("/irf/v1/callback/retry", func(w http.ResponseWriter, r *http.Request) {
		if !okSec(r, t) {
			w.WriteHeader(http.StatusUnauthorized)
			return
		}
		w.WriteHeader(http.StatusOK)
		_ = json.NewEncoder(w).Encode(true)
	})

	srv := httptest.NewServer(mux)
	t.Cleanup(srv.Close)
	return srv
}

// NewClient wires a Client to the stub server (and returns the client + server so
// tests can point a second client with a bad secret at the same URL).
func NewClient(t *testing.T) (*irf.Client, *httptest.Server) {
	t.Helper()
	srv := NewIRFService(t)
	c := irf.NewClient(srv.URL, DefaultSecret)
	c.HTTP = srv.Client()
	return c, srv
}

func okSec(r *http.Request, t *testing.T) bool {
	if r.URL.Query().Get("sec") != DefaultSecret {
		return false
	}
	return true
}
