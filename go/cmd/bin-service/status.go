package main

import (
	"encoding/json"
	"net/http"
	"strconv"
	"time"

	"empay/irf/binsvc"
)

// statusResponse is the GET /bin/v1/status payload consumed by the
// Settlement → BIN Files tab.
type statusResponse struct {
	Service  string `json:"service"`
	Port     string `json:"port"`
	InputDir string `json:"inputDir"`
	// Tables maps network -> the range table its BIN file loads into, so the UI
	// can label a count without hardcoding the mapping.
	Tables map[string]string `json:"tables"`
	// Counts is the live row count per network; -1 means the table is absent in
	// this schema rather than empty.
	Counts map[string]int64 `json:"counts"`
	Jobs   []jobView        `json:"jobs"`
	// Uploads is per-FILE progress: the Settlement → BIN Files tab uses it to
	// answer "is this staged file finished?" without a SQL query. Status is
	// FILE_UPLOAD_LOG.UPL_UPLOAD_STATUS: 1 pending, 4 complete, 5 error.
	Uploads []uploadView `json:"uploads"`
}

// uploadView is one FILE_UPLOAD_LOG row.
type uploadView struct {
	FileName   string `json:"fileName"`
	Status     int    `json:"status"`
	State      string `json:"state"` // pending | complete | error | unknown
	TotalRows  int    `json:"totalRows"`
	Accepted   int    `json:"acceptedRows"`
	Remarks    string `json:"remarks,omitempty"`
	FormatCode int    `json:"formatCode,omitempty"`
	// ProcessedAt is when the load reached status 4. It is UPL_LAST_UPDATED,
	// which UpdateUploadLog stamps with SYSDATE as it writes the terminal
	// status -- so it is the completion time, not the row's insert time. Only
	// populated for a completed load; a pending row's UPL_LAST_UPDATED is when
	// the upload was staged and must not be shown as a completion time.
	ProcessedAt string `json:"processedAt,omitempty"`
}

// uploadStateName maps UPL_UPLOAD_STATUS to something an operator can read.
// 1 = written but not finished, 4 = complete, 5 = failed. Anything else is
// reported verbatim rather than guessed at.
func uploadStateName(status int) string {
	switch status {
	case 1:
		return "pending"
	case 4:
		return "complete"
	case 5:
		return "error"
	default:
		return "unknown"
	}
}

type jobView struct {
	SerialNumber int64  `json:"serialNumber"`
	ProcessName  string `json:"processName"`
	StartTime    string `json:"startTime"`
	EndTime      string `json:"endTime,omitempty"`
	Status       *int   `json:"status,omitempty"`
}

// statusHandler reports what bin-service has loaded: recent jobs, per-file
// progress and a live row count per range table. A count failure must not blank the whole view, so the
// store returns -1 per network rather than an error.
func statusHandler(svc *binsvc.Service, port string) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		limit := 20
		if v := r.URL.Query().Get("limit"); v != "" {
			if n, err := strconv.Atoi(v); err == nil && n > 0 && n <= 100 {
				limit = n
			}
		}

		counts, err := svc.Store.RangeTableCounts(r.Context())
		if err != nil {
			http.Error(w, "range counts: "+err.Error(), http.StatusInternalServerError)
			return
		}
		jobs, err := svc.Store.RecentJobs(r.Context(), limit)
		if err != nil {
			http.Error(w, "recent jobs: "+err.Error(), http.StatusInternalServerError)
			return
		}

		logs, err := svc.Store.RecentUploadLogs(r.Context(), limit*2)
		if err != nil {
			http.Error(w, "recent upload logs: "+err.Error(), http.StatusInternalServerError)
			return
		}
		uploadViews := make([]uploadView, 0, len(logs))
		for _, u := range logs {
			v := uploadView{
				FileName:   u.FileName,
				Status:     u.UploadStatus,
				State:      uploadStateName(u.UploadStatus),
				TotalRows:  u.TotalTxnCount,
				Accepted:   u.TotalAcceptedTxnCount,
				FormatCode: u.FormatCode,
			}
			if u.Remarks != nil {
				v.Remarks = *u.Remarks
			}
			v.ProcessedAt = processedAt(u)
			uploadViews = append(uploadViews, v)
		}

		views := make([]jobView, 0, len(jobs))
		for _, j := range jobs {
			v := jobView{
				SerialNumber: j.SerialNumber,
				ProcessName:  j.ProcessName,
				StartTime:    j.StartTime.Format("2006-01-02T15:04:05"),
				Status:       j.Status,
			}
			if j.EndTime != nil {
				v.EndTime = j.EndTime.Format("2006-01-02T15:04:05")
			}
			views = append(views, v)
		}

		w.Header().Set("Content-Type", "application/json")
		_ = json.NewEncoder(w).Encode(statusResponse{
			Service:  "bin-service",
			Port:     port,
			InputDir: svc.Cfg.ReconIn,
			Tables:   binsvc.RangeTables(),
			Counts:   counts,
			Jobs:     views,
			Uploads:  uploadViews,
		})
	}
}

// processedAt returns when the load reached "complete", or "" otherwise.
//
// The source is UPL_LAST_UPDATED, which UpdateUploadLog stamps with SYSDATE as
// it writes the terminal status. It is only a completion time for a completed
// load: the INSERT also sets UPL_LAST_UPDATED, so on a still-pending row it
// records when the file was STAGED. Publishing that as a completion time would
// tell an operator a stuck load had finished, so status 4 is the only case
// where it is reported.
func processedAt(u *binsvc.UploadLog) string {
	if u == nil || u.UploadStatus != 4 || u.LastUpdated.IsZero() {
		return ""
	}
	return u.LastUpdated.UTC().Format(time.RFC3339)
}
