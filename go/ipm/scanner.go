package ipm

import "fmt"

// validMTI is the set of message type indicators the scanner recognises.
var validMTI = map[string]bool{
	"1240": true, "1241": true, "1242": true, "1243": true, "1244": true,
	"1442": true, "1644": true, "1645": true, "1646": true,
	"1740": true, "1741": true,
}

// Record is one scanned physical record (RawRecord in Python).
type Record struct {
	Offset   int
	Length   int
	MTI      string
	Payload  []byte
	Repairs  []string
	Trailing []byte
}

// Scanner walks an IPM file using the 4-byte big-endian length prefix of each
// record. It is the Go port of parser/scanner.py, including its narrowly
// scoped recovery for MTI and bitmap corruption caused by the inserted
// "00 00" pairs of the 1012->1014 block transform.
type Scanner struct{}

// NewScanner returns a Scanner.
func NewScanner() *Scanner { return &Scanner{} }

// ScanFile reads path and returns every record it contains.
func (s *Scanner) ScanFile(path string) ([]Record, error) {
	data, err := readFile(path)
	if err != nil {
		return nil, err
	}
	return s.Scan(data), nil
}

// Scan walks data and returns every record it contains.
func (s *Scanner) Scan(data []byte) []Record {
	var records []Record
	offset := 0
	for {
		rec, ok := s.readRecord(data, offset)
		if !ok {
			break
		}

		next, found := s.findNextRecord(data, offset, rec.Length)
		if found {
			declaredEnd := offset + 4 + rec.Length
			if next > declaredEnd {
				rec.Trailing = data[declaredEnd:next]
			}
		}
		// Append only after Trailing is set: Record is a value type, so
		// assigning to it after the append would mutate a discarded copy.
		records = append(records, rec)

		if !found {
			break
		}
		offset = next
	}
	return records
}

// classifyPayload returns the MTI and a cleaned payload, reporting false when
// no valid MTI can be located. Corruption is confined to the first eight
// bytes: inserted 0x00/0x0D bytes are removed around the four EBCDIC MTI
// bytes so the bitmap and everything after stay byte-aligned.
func classifyPayload(payload []byte) (string, []byte, bool) {
	if len(payload) < 4 {
		return "", nil, false
	}
	if mti := decodeEbcdic(payload[:4]); validMTI[mti] {
		return mti, payload, true
	}

	scanEnd := 8
	if scanEnd > len(payload) {
		scanEnd = len(payload)
	}
	var selected []int
	for i := 0; i < scanEnd; i++ {
		b := payload[i]
		if b == 0x00 || b == 0x0D {
			continue
		}
		selected = append(selected, i)
		if len(selected) == 4 {
			break
		}
	}
	if len(selected) != 4 {
		return "", nil, false
	}
	raw := make([]byte, 4)
	for i, idx := range selected {
		raw[i] = payload[idx]
	}
	mti := decodeEbcdic(raw)
	if !validMTI[mti] {
		return "", nil, false
	}
	mtiEnd := selected[3] + 1
	cleaned := make([]byte, 0, 4+len(payload)-mtiEnd)
	cleaned = append(cleaned, raw...)
	cleaned = append(cleaned, payload[mtiEnd:]...)
	return mti, cleaned, true
}

// isEbcdicNumeric reports whether every byte is an EBCDIC digit F0-F9.
func isEbcdicNumeric(b []byte) bool {
	if len(b) == 0 {
		return false
	}
	for _, c := range b {
		if c < 0xF0 || c > 0xF9 {
			return false
		}
	}
	return true
}

const (
	mtiLen          = 4
	primaryBitmapLn = 8
	secondaryLn     = 8
)

// bitmapCandidate is one candidate bitmap reconstruction.
type bitmapCandidate struct {
	bitmap   []byte
	consumed int
	removed  []int
}

// bitmapCandidateValid validates a reconstructed primary bitmap. consumed is
// how many source bytes the primary occupied after the MTI. A valid
// reconstruction must place a numeric EBCDIC DE2 length right after the
// complete bitmap (when DE2 is present).
func bitmapCandidateValid(payload, primary []byte, consumed int) bool {
	if len(primary) != primaryBitmapLn {
		return false
	}
	hasSecondary := primary[0]&0x80 != 0
	hasDE2 := primary[0]&0x40 != 0

	if hasSecondary && mtiLen+consumed+secondaryLn > len(payload) {
		return false
	}
	de2Start := mtiLen + consumed
	if hasSecondary {
		de2Start += secondaryLn
	}
	if hasDE2 {
		if de2Start+2 > len(payload) {
			return false
		}
		return isEbcdicNumeric(payload[de2Start : de2Start+2])
	}
	return true
}

// generatePrimaryCandidates enumerates 8-byte primary bitmaps, optionally
// deleting inserted "00 00" pairs. Only the primary is reconstructed, so
// legitimate 00 00 bytes in a secondary bitmap are never touched.
func generatePrimaryCandidates(payload []byte, maxRemovedPairs int) []bitmapCandidate {
	start := mtiLen
	var results []bitmapCandidate
	seen := make(map[string]bool)
	out := make([]byte, 0, primaryBitmapLn)
	var removed []int

	var walk func(index, depth int)
	walk = func(index, depth int) {
		if len(out) == primaryBitmapLn {
			key := fmt.Sprintf("%x|%d|%v", out, index-start, removed)
			if !seen[key] {
				seen[key] = true
				cp := make([]byte, len(out))
				copy(cp, out)
				results = append(results, bitmapCandidate{
					bitmap:   cp,
					consumed: index - start,
					removed:  append([]int(nil), removed...),
				})
			}
			return
		}
		if index >= len(payload) {
			return
		}
		// Keep this byte.
		out = append(out, payload[index])
		walk(index+1, depth)
		out = out[:len(out)-1]

		// Or drop an inserted 00 00 pair.
		if depth < maxRemovedPairs && index+1 < len(payload) &&
			payload[index] == 0x00 && payload[index+1] == 0x00 {
			removed = append(removed, index-start)
			walk(index+2, depth+1)
			removed = removed[:len(removed)-1]
		}
	}
	walk(start, 0)
	return results
}

// generateSecondaryCandidates enumerates 8-byte secondary bitmaps by deleting
// inserted "00 00" pairs, accepting only candidates that leave a valid DE2
// length immediately after the reconstructed bitmap.
func generateSecondaryCandidates(payload []byte, primaryConsumed, maxRemovedPairs int) []bitmapCandidate {
	start := mtiLen + primaryConsumed
	var results []bitmapCandidate
	seen := make(map[string]bool)
	out := make([]byte, 0, secondaryLn)
	var removed []int
	maxSource := start + secondaryLn + 2*maxRemovedPairs
	if maxSource > len(payload) {
		// Python clamps the search window via the len(payload) check inside
		// walk; keep the bound but never read past the buffer.
		maxSource = min(len(payload), maxSource)
	}

	de2Valid := func(consumed int) bool {
		de2Start := start + consumed
		if de2Start+2 > len(payload) {
			return false
		}
		return isEbcdicNumeric(payload[de2Start : de2Start+2])
	}

	var walk func(index, depth int)
	walk = func(index, depth int) {
		if index > maxSource {
			return
		}
		if len(out) == secondaryLn {
			consumed := index - start
			if !de2Valid(consumed) {
				return
			}
			key := fmt.Sprintf("%x|%d", out, consumed)
			if !seen[key] {
				seen[key] = true
				cp := make([]byte, len(out))
				copy(cp, out)
				results = append(results, bitmapCandidate{
					bitmap:   cp,
					consumed: consumed,
					removed:  append([]int(nil), removed...),
				})
			}
			return
		}
		if index >= len(payload) || index >= maxSource {
			return
		}
		out = append(out, payload[index])
		walk(index+1, depth)
		out = out[:len(out)-1]

		if depth < maxRemovedPairs && index+1 < len(payload) &&
			payload[index] == 0x00 && payload[index+1] == 0x00 {
			removed = append(removed, index-start)
			walk(index+2, depth+1)
			removed = removed[:len(removed)-1]
		}
	}
	walk(start, 0)
	return results
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

// repairPayload repairs known IPM bitmap corruption, returning the repaired
// payload and the list of repairs applied.
//
// Rules:
//   - primary bitmap is exactly 8 bytes;
//   - primary bit 1 => secondary bitmap exists and is exactly 8 bytes;
//   - primary bit 2 => DE2 present, and its EBCDIC LLVAR length validates the
//     end of the complete bitmap;
//   - if the normal secondary boundary yields a valid DE2 length nothing is
//     touched, including legitimate 00 00 bytes;
//   - otherwise inserted 00 00 pairs are considered, and a repair is applied
//     only when exactly one reconstruction is valid.
func repairPayload(payload []byte) ([]byte, []string) {
	var repairs []string
	if len(payload) < mtiLen+primaryBitmapLn {
		return payload, repairs
	}
	p := payload

	// Existing exception: F0 10 missing immediately before the bitmap.
	if p[4] == 0x07 && p[5] == 0xC3 && p[6] == 0x8D && p[7] == 0xE1 {
		out := make([]byte, 0, len(p)+2)
		out = append(out, p[:4]...)
		out = append(out, 0xF0, 0x10)
		out = append(out, p[4:]...)
		return out, append(repairs, "missing-bitmap-f010")
	}

	// Existing exception: 00 00 inserted before F0 10.
	if p[4] == 0x00 && p[5] == 0x00 && p[6] == 0xF0 && p[7] == 0x10 {
		out := make([]byte, 0, len(p)-2)
		out = append(out, p[:4]...)
		out = append(out, p[6:]...)
		return out, append(repairs, "nulls-before-bitmap")
	}

	primary := p[4:12]
	hasSecondary := primary[0]&0x80 != 0
	hasDE2 := primary[0]&0x40 != 0

	if hasSecondary && hasDE2 {
		normalSecEnd := 4 + 8 + 8
		if normalSecEnd+2 <= len(p) && isEbcdicNumeric(p[normalSecEnd:normalSecEnd+2]) {
			return p, repairs // already aligned; do not touch any 00 00
		}

		// Try repairing the primary first.
		var primaryOK []bitmapCandidate
		for _, c := range generatePrimaryCandidates(p, 4) {
			if c.consumed == 8 && len(c.removed) == 0 {
				continue
			}
			if bitmapCandidateValid(p, c.bitmap, c.consumed) {
				primaryOK = append(primaryOK, c)
			}
		}
		if len(primaryOK) == 1 {
			c := primaryOK[0]
			remainder := 4 + c.consumed
			out := make([]byte, 0, len(p))
			out = append(out, p[:4]...)
			out = append(out, c.bitmap...)
			out = append(out, p[remainder:]...)
			return out, append(repairs, "nulls-in-primary-bitmap-at:"+joinInts(c.removed))
		}

		// Otherwise repair the secondary bitmap.
		candidates := generateSecondaryCandidates(p, 8, 1)

		// In these Mastercard 1240 records bit 94 discriminates the intended
		// secondary bitmap.
		if filtered := filterBit(candidates, 3, 0x04); len(filtered) > 0 {
			candidates = filtered
		}
		// Bit 71 breaks ties inside runs of 00 bytes.
		if filtered := filterBit(candidates, 0, 0x02); len(filtered) > 0 {
			candidates = filtered
		}

		if len(candidates) == 1 {
			c := candidates[0]
			secStart := 4 + 8
			remainder := secStart + c.consumed
			out := make([]byte, 0, len(p))
			out = append(out, p[:secStart]...)
			out = append(out, c.bitmap...)
			out = append(out, p[remainder:]...)
			return out, append(repairs, "nulls-in-secondary-bitmap-at:"+joinInts(c.removed))
		}
		if len(candidates) > 1 {
			repairs = append(repairs, "secondary-bitmap-repair-ambiguous")
		}
		return p, repairs
	}

	// No secondary bitmap.
	if !hasSecondary {
		if !hasDE2 || (12+2 <= len(p) && isEbcdicNumeric(p[12:14])) {
			return p, repairs
		}
	}

	var valid []bitmapCandidate
	for _, c := range generatePrimaryCandidates(p, 4) {
		if c.consumed == 8 && len(c.removed) == 0 {
			continue
		}
		if bitmapCandidateValid(p, c.bitmap, c.consumed) {
			valid = append(valid, c)
		}
	}
	if len(valid) == 1 {
		c := valid[0]
		remainder := 4 + c.consumed
		out := make([]byte, 0, len(p))
		out = append(out, p[:4]...)
		out = append(out, c.bitmap...)
		out = append(out, p[remainder:]...)
		return out, append(repairs, "nulls-in-primary-bitmap-at:"+joinInts(c.removed))
	}
	if len(valid) > 1 {
		repairs = append(repairs, "bitmap-repair-ambiguous")
	}
	return p, repairs
}

// filterBit keeps candidates whose bitmap byte at idx has all bits of mask set.
func filterBit(cs []bitmapCandidate, idx int, mask byte) []bitmapCandidate {
	var out []bitmapCandidate
	for _, c := range cs {
		if idx < len(c.bitmap) && c.bitmap[idx]&mask == mask {
			out = append(out, c)
		}
	}
	return out
}

func joinInts(v []int) string {
	if len(v) == 0 {
		return ""
	}
	out := ""
	for i, n := range v {
		if i > 0 {
			out += ","
		}
		out += itoa(n)
	}
	return out
}

func itoa(n int) string {
	if n == 0 {
		return "0"
	}
	neg := n < 0
	if neg {
		n = -n
	}
	var b [20]byte
	i := len(b)
	for n > 0 {
		i--
		b[i] = byte('0' + n%10)
		n /= 10
	}
	if neg {
		i--
		b[i] = '-'
	}
	return string(b[i:])
}

// readRecord reads the record at offset.
func (s *Scanner) readRecord(data []byte, offset int) (Record, bool) {
	if offset+8 > len(data) {
		return Record{}, false
	}
	length := int(be32(data[offset : offset+4]))
	if length < 20 || length > 5000 {
		return Record{}, false
	}
	if offset+4+length > len(data) {
		return Record{}, false
	}
	payload := data[offset+4 : offset+4+length]

	mti, cleaned, ok := classifyPayload(payload)
	if !ok {
		if rec, ok2 := s.recoverShiftedRecord(data, offset, length); ok2 {
			return rec, true
		}
		return Record{}, false
	}
	cleaned, repairs := repairPayload(cleaned)
	return Record{
		Offset: offset, Length: length, MTI: mti,
		Payload: cleaned, Repairs: repairs,
	}, true
}

// recoverShiftedRecord recovers records with junk bytes before a valid MTI.
// The declared record must fail normal MTI classification, skipping at most
// four bytes must expose a valid MTI, and a following normal record header
// must prove the physical end of the recovered record.
func (s *Scanner) recoverShiftedRecord(data []byte, offset, declaredLength int) (Record, bool) {
	if declaredLength < 20 || declaredLength > 5000 {
		return Record{}, false
	}
	if offset+4+declaredLength > len(data) {
		return Record{}, false
	}
	recordStart := offset + 4

	for skip := 1; skip <= 4; skip++ {
		payloadStart := recordStart + skip
		if payloadStart+4 > len(data) {
			continue
		}
		mti := decodeEbcdic(data[payloadStart : payloadStart+4])
		if !validMTI[mti] {
			continue
		}
		searchStart := max(recordStart+declaredLength, payloadStart+20)
		searchEnd := min(recordStart+5000, len(data))

		for nextOffset := searchStart; nextOffset < searchEnd; nextOffset++ {
			if !s.isValidHeader(data, nextOffset) {
				continue
			}
			physicalLength := nextOffset - recordStart
			payload := data[payloadStart:nextOffset]
			if len(payload) < 20 || len(payload) > 5000 {
				continue
			}
			cleaned, repairs := repairPayload(payload)
			return Record{
				Offset: offset, Length: physicalLength, MTI: mti,
				Payload: cleaned,
				Repairs: append([]string{
					"skipped-prefix-bytes-" + itoa(skip),
					"recovered-length-from-next-header",
				}, repairs...),
			}, true
		}
	}
	return Record{}, false
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func be32(b []byte) uint32 {
	return uint32(b[0])<<24 | uint32(b[1])<<16 | uint32(b[2])<<8 | uint32(b[3])
}

// isValidHeader reports whether offset begins a plausible record.
func (s *Scanner) isValidHeader(data []byte, offset int) bool {
	if offset+8 > len(data) {
		return false
	}
	length := int(be32(data[offset : offset+4]))
	if length <= 0 || offset+4+length > len(data) {
		return false
	}
	_, _, ok := classifyPayload(data[offset+4 : offset+4+length])
	return ok
}

// findNextRecord locates the record following the current one.
func (s *Scanner) findNextRecord(data []byte, currentOffset, currentLength int) (int, bool) {
	expected := currentOffset + 4 + currentLength
	deltas := []int{0, 2, -2, 1, -1, 3, -3, 4, -4}

	for _, d := range deltas {
		pos := expected + d
		if pos < 0 {
			continue
		}
		if s.isValidHeader(data, pos) {
			return pos, true
		}
		if pos+4 <= len(data) {
			if _, ok := s.recoverShiftedRecord(data, pos, int(be32(data[pos:pos+4]))); ok {
				return pos, true
			}
		}
	}

	end := min(expected+4096, len(data))
	for pos := expected; pos < end; pos++ {
		valid := s.isValidHeader(data, pos)
		if !valid && pos+4 <= len(data) {
			valid = func() bool {
				_, ok := s.recoverShiftedRecord(data, pos, int(be32(data[pos:pos+4])))
				return ok
			}()
		}
		if !valid {
			continue
		}
		nextExpected := pos + 4 + int(be32(data[pos:pos+4]))
		for _, d := range deltas {
			nxt := nextExpected + d
			if nxt < 0 {
				continue
			}
			if s.isValidHeader(data, nxt) {
				return pos, true
			}
		}
	}
	return 0, false
}
