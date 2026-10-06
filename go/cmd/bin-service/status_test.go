package main

import (
	"testing"
	"time"

	"empay/irf/binsvc"
)

func TestProcessedAtOnlyForCompletedLoads(t *testing.T) {
	when := time.Date(2026, 10, 6, 7, 54, 7, 0, time.UTC)
	cases := []struct {
		name string
		u    *binsvc.UploadLog
		want string
	}{
		{"complete reports the timestamp", &binsvc.UploadLog{
			UploadStatus: 4, LastUpdated: when,
		}, "2026-10-06T07:54:07Z"},
		// The regression this guards: a pending row's UPL_LAST_UPDATED is the
		// staging time, not a completion time. Reporting it would make a stuck
		// load look finished.
		{"pending does not report it", &binsvc.UploadLog{
			UploadStatus: 1, LastUpdated: when,
		}, ""},
		{"error does not report it", &binsvc.UploadLog{
			UploadStatus: 5, LastUpdated: when,
		}, ""},
		{"complete with zero time", &binsvc.UploadLog{
			UploadStatus: 4,
		}, ""},
		{"nil row", nil, ""},
	}
	for _, tc := range cases {
		t.Run(tc.name, func(t *testing.T) {
			if got := processedAt(tc.u); got != tc.want {
				t.Fatalf("processedAt() = %q, want %q", got, tc.want)
			}
		})
	}
}
