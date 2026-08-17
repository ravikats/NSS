package outsvc

import (
	"context"
	"encoding/json"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"testing"
	"time"
)

// XD field indexes per FIELD_MAP in eif_decode.py.
const (
	xdCAMTR = 8
	xdRRN   = 32
	xdREFNO = 17
	xdCHTYP = 11
	xdTYPCH = 16
	xdGEOCD = 14
	xdCHOLP = 38
	xdCARDP = 39
	xdCPTRM = 40
	xdNRID  = 43
	xdCRDIN = 44
)

// XM field indexes.
const (
	xmCTRMG = 23
	xmCTRNC = 30
)

type refRecord struct {
	xd []string
	xm []string
}

// minorToFloat converts a 12-digit minor-units amount string ("000000003200")
// to the corresponding major value (32.0).
func minorToFloat(s string) float64 {
	s = strings.TrimLeft(s, "0")
	if s == "" {
		return 0
	}
	n, _ := strconv.ParseFloat(s, 64)
	return n / 100
}

func refDate(s string) *time.Time {
	if len(s) != 6 {
		return nil
	}
	y, _ := strconv.Atoi("20" + s[:2])
	m, _ := strconv.Atoi(s[2:4])
	d, _ := strconv.Atoi(s[4:6])
	t := time.Date(y, time.Month(m), d, 0, 0, 0, 0, time.UTC)
	return &t
}

func refDateTime(date6, hhmmss string) *time.Time {
	base := refDate(date6)
	if base == nil || len(hhmmss) != 6 {
		return nil
	}
	h, _ := strconv.Atoi(hhmmss[:2])
	mi, _ := strconv.Atoi(hhmmss[2:4])
	se, _ := strconv.Atoi(hhmmss[4:6])
	t := time.Date(base.Year(), base.Month(), base.Day(), h, mi, se, 0, time.UTC)
	return &t
}

func strMap(p map[string]any, k string) string {
	if v, ok := p[k]; ok && v != nil {
		return strings.TrimSpace(v.(string))
	}
	return ""
}

func strMapOr(p map[string]any, k, def string) string {
	if v, ok := p[k]; ok && v != nil {
		if s := strings.TrimSpace(v.(string)); s != "" {
			return s
		}
	}
	return def
}

// TestMercuryOutputVsReference1708 regenerates the Mercury EIF from
// payload.json + the 1708 ground-truth DB values and asserts the Go builders
// reproduce EIF_17082026.001 field-for-field (REFNO is random and masked).
func TestMercuryOutputVsReference1708(t *testing.T) {
	payPath := filepath.Join("testdata", "payload.json")
	refPath := `D:\Mercury\submission\EIF_17082026.001`
	if _, err := os.Stat(payPath); err != nil || os.IsNotExist(err) {
		t.Skipf("payload.json not present: %v", err)
	}
	if _, err := os.Stat(refPath); err != nil || os.IsNotExist(err) {
		t.Skipf("reference file not present: %v", err)
	}

	payloadB, err := os.ReadFile(payPath)
	if err != nil {
		t.Fatal(err)
	}
	var payload []map[string]any
	if err := json.Unmarshal(payloadB, &payload); err != nil {
		t.Fatal(err)
	}
	byRRN := map[string]map[string]any{}
	for _, p := range payload {
		byRRN[strMap(p, "rrn")] = p
	}

	refB, err := os.ReadFile(refPath)
	if err != nil {
		t.Fatal(err)
	}
	refLines := strings.Split(strings.TrimRight(string(refB), "\n"), "\n")
	for i := range refLines {
		refLines[i] = strings.TrimSuffix(refLines[i], "\r")
	}

	// Pair each XD with its following XM in the reference file.
	var refs []*refRecord
	for i, ln := range refLines {
		if !strings.HasPrefix(ln, "FRRC>XD>") {
			continue
		}
		rec := &refRecord{xd: strings.Split(ln, ">")}
		if i+1 < len(refLines) && strings.HasPrefix(refLines[i+1], "FRRC>XM>") {
			rec.xm = strings.Split(refLines[i+1], ">")
		}
		refs = append(refs, rec)
	}
	if len(refs) != len(payload) {
		t.Fatalf("reference XD count %d != payload count %d", len(refs), len(payload))
	}

	response := map[string]string{}
	var txns []*MercuryAcqTxnWorkEntity
	for _, rec := range refs {
		p := byRRN[rec.xd[xdRRN]]
		if p == nil {
			t.Fatalf("no payload record for rrn %s", rec.xd[xdRRN])
		}
		de55, _ := p["de55_json"].(map[string]any)
		token := strMap(p, "switch_crypt_token")
		response[token] = rec.xd[7]

		entity := &MercuryAcqTxnWorkEntity{
			EncryptedCardNumber:    token,
			Rrn:                    strMap(p, "rrn"),
			MerchantId:             strMap(p, "merchant_id"),
			TerminalId:             strMap(p, "terminal_id"),
			TxnAmount:              minorToFloat(strMap(p, "amount")),
			SurchargeAmount:        0,
			TxnDate:                refDate("26" + strMap(p, "local_date")),
			LocalDateTime:          refDateTime(strMap(de55, "9A"), strMap(p, "local_time")),
			ChargeType:             rec.xd[xdCHTYP],
			TypeOfCharge:           rec.xd[xdTYPCH],
			GeoArea:                rec.xd[xdGEOCD],
			MeName:                 strMap(p, "card_acceptor_name"),
			MeCity:                 strMap(p, "card_acceptor_city"),
			MeCountry:              strMap(p, "card_acceptor_country_code"),
			CardAccepStreetAddress: strMap(p, "card_acceptor_st_addr"),
			MePinCode:              strMap(p, "card_acceptor_pin_code"),
			EstPhoneNumber:         strMap(p, "merchant_contact_information"),
			Mcc:                    strMap(p, "mcc"),
			ApprovalCode:           strMap(p, "auth_code"),
			MercuryRefId:           rec.xd[xdNRID],
			ChPresent:              rec.xd[xdCHOLP],
			CardPresent:            rec.xd[xdCARDP],
			CardInputMode:          rec.xd[xdCPTRM],
			CardInputCapability:    rec.xd[xdCRDIN],
			ResponseCode:           strMap(p, "network_response_code"),
			PosEntryMode:           strMap(p, "pos_entry_mode"),
			PanSequenceNumber:      strMap(p, "pan_sequence_number"),
			AppICProfile:           strMapOr(de55, "82", ""),
			AppTxnCounter:          strMapOr(de55, "9F36", ""),
			AppCryptogram:          strMapOr(de55, "9F26", ""),
			CryptAmount:            minorToFloat(strMapOr(de55, "9F02", "000000000000")),
			CashBackAmount:         minorToFloat(strMapOr(de55, "9F03", "000000000000")),
			CryptInfoData:          strMapOr(de55, "9F27", ""),
			CvmResult:              strMapOr(de55, "9F34", ""),
			DedicatedFileName:      strMapOr(de55, "84", ""),
			IfdSerNumber:           strMapOr(de55, "9F1E", ""),
			IssAppData:             strMapOr(de55, "9F10", ""),
			IssAuthData:            strMapOr(de55, "91", ""),
			TrlConCode:             rec.xm[xmCTRMG],
			TrlAppVerNumber:        strMapOr(de55, "9F09", ""),
			ChipTrlCapabilities:    strMapOr(de55, "9F33", ""),
			ChipTrlType:            strMapOr(de55, "9F35", ""),
			TrlVerResult:           strMapOr(de55, "95", ""),
			ChipTxnDate:            strMapOr(de55, "9A", ""),
			ChipTxnType:            strMapOr(de55, "9C", ""),
			ChipCurCode:            rec.xm[xmCTRNC],
			UpblNumber:             strMapOr(de55, "9F37", ""),
		}
		txns = append(txns, entity)
	}

	outDir := t.TempDir()
	s := NewOutgoingService(OutgoingConfig{
		ReconOutDir:       outDir,
		CurrencyCodeKafka: "AED000",
	}, nil, nil)
	s.now = func() time.Time { return time.Date(2026, 8, 17, 0, 0, 0, 0, time.UTC) }

	fileName := "EIF_17082026.001"
	if got := s.writeMercuryFile(context.Background(), txns, "IRF", fileName, "297", response); got != fileName {
		t.Fatalf("writeMercuryFile = %q", got)
	}

	gotB, err := os.ReadFile(filepath.Join(outDir, fileName))
	if err != nil {
		t.Fatal(err)
	}
	gotLines := strings.Split(strings.TrimRight(string(gotB), "\n"), "\n")
	if len(gotLines) != len(refLines) {
		t.Fatalf("line count %d != reference %d", len(gotLines), len(refLines))
	}

	mismatch := 0
	for i, got := range gotLines {
		want := refLines[i]
		if strings.HasPrefix(got, "FRRC>XD>") {
			g := strings.Split(got, ">")
			w := strings.Split(want, ">")
			g[xdREFNO] = "*"
			w[xdREFNO] = "*"
			for j := range g {
				if g[j] != w[j] {
					t.Errorf("XD line %d field %d = %q, want %q", i, j, g[j], w[j])
					mismatch++
				}
			}
			continue
		}
		if got != want {
			t.Errorf("line %d = %q, want %q", i, got, want)
			mismatch++
		}
	}
	if mismatch == 0 {
		t.Logf("OK: generated output matches EIF_17082026.001 for all %d lines (REFNO masked)", len(refLines))
	}
}