package mpgsdcf

import (
	"encoding/csv"
	"fmt"
	"io"
	"os"
	"strings"
)

// LoadJaywanRanges reads a CSV of "start,end" card ranges (one per line, no
// header) from path and returns them as JaywanRanges. Blank lines and a
// trailing header are tolerated; malformed lines are skipped with a warning
// line returned alongside the parsed ranges.
func LoadJaywanRanges(path string) (JaywanRanges, []string, error) {
	f, err := os.Open(path)
	if err != nil {
		return nil, nil, fmt.Errorf("mpgsdcf: open ranges %s: %w", path, err)
	}
	defer f.Close()

	var (
		rs     JaywanRanges
		warns  []string
		lineNo int
	)
	cr := csv.NewReader(f)
	cr.FieldsPerRecord = -1
	for {
		rec, err := cr.Read()
		if err == io.EOF {
			break
		}
		if err != nil {
			warns = append(warns, fmt.Sprintf("line %d: %v", lineNo+1, err))
			continue
		}
		lineNo++
		if len(rec) < 2 {
			warns = append(warns, fmt.Sprintf("line %d: expected start,end", lineNo))
			continue
		}
		start := strings.TrimSpace(rec[0])
		end := strings.TrimSpace(rec[1])
		if start == "" || end == "" {
			warns = append(warns, fmt.Sprintf("line %d: empty range", lineNo))
			continue
		}
		rs = append(rs, PanRange{Start: start, End: end})
	}
	if len(rs) == 0 {
		return nil, warns, fmt.Errorf("mpgsdcf: no ranges parsed from %s", path)
	}
	return rs, warns, nil
}
