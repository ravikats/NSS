package tlfsvc

import (
	"fmt"
	"strconv"
	"strings"
	"time"
)

// MercuryChargeTypeCode mirrors MercuryChargeTypeUtil.getChargeTypeCode: an MCC
// maps to a Mercury charge-type code; unknown/blank/non-numeric MCCs -> "999".
func MercuryChargeTypeCode(mcc string) string {
	if mcc == "" || strings.TrimSpace(mcc) == "" {
		return "999"
	}
	v, err := strconv.Atoi(strings.TrimSpace(mcc))
	if err != nil {
		return "999"
	}
	switch v {
	case 9311:
		return "181"
	case 9222:
		return "182"
	case 8043:
		return "309"
	case 4814:
		return "860"
	case 4816:
		return "920"
	}
	if v >= 3501 && v <= 3999 {
		return "100"
	}
	if v >= 7531 && v <= 7538 {
		return "410"
	}
	if v >= 3000 && v <= 3299 {
		return "500"
	}
	switch v {
	case 8011, 8021, 8031, 8041, 8042, 8050, 8062, 8071, 8099:
		return "110"
	case 8211, 8220, 8241, 8244, 8249, 8299:
		return "120"
	case 7210, 7211, 7216, 7217:
		return "130"
	case 4899, 4900:
		return "150"
	case 6513:
		return "160"
	case 9211, 9223, 9399, 9402, 9405:
		return "180"
	case 4784, 7523:
		return "183"
	case 5812:
		return "200"
	case 5811:
		return "201"
	case 5813:
		return "210"
	case 7832, 7922, 7929:
		return "211"
	case 5941:
		return "302"
	case 5942, 5943:
		return "303"
	case 5921:
		return "305"
	case 5193, 5992:
		return "306"
	case 7230:
		return "307"
	case 5912:
		return "314"
	case 5411, 5499:
		return "315"
	case 5094, 5944:
		return "320"
	case 5621, 5651, 5691:
		return "330"
	case 5732, 5734:
		return "340"
	case 7512:
		return "400"
	case 4121:
		return "420"
	case 4511:
		return "500"
	case 4722:
		return "600"
	case 4468:
		return "610"
	case 4411:
		return "620"
	case 4112:
		return "630"
	case 5541:
		return "700"
	case 5542:
		return "710"
	case 7995:
		return "850"
	case 5960, 5964, 5965:
		return "900"
	case 5968, 5969:
		return "910"
	case 8111:
		return "930"
	case 8931:
		return "940"
	case 7393:
		return "950"
	case 7361:
		return "960"
	case 7392:
		return "970"
	case 1520, 1711, 1731, 1740:
		return "980"
	default:
		return "999"
	}
}

// MercuryTypeOfCharge mirrors MercuryPosUtil.getTYPCH.
func MercuryTypeOfCharge(posEntryMode string) string {
	if posEntryMode == "" || strings.TrimSpace(posEntryMode) == "" {
		return "TE"
	}
	value := strings.ToUpper(strings.TrimSpace(posEntryMode))
	if value == "ECOM" {
		return "TI"
	}
	if len(value) >= 3 {
		value = value[:3]
	}
	switch value {
	case "051", "052", "071", "072":
		return "TK"
	default:
		return "TE"
	}
}

// MercuryCardInputMode mirrors MercuryPosUtil.getCPTRM (returns a single char).
func MercuryCardInputMode(posEntryMode string) string {
	if posEntryMode == "" || strings.TrimSpace(posEntryMode) == "" {
		return "1"
	}
	value := strings.TrimSpace(posEntryMode)
	if len(value) >= 3 {
		value = value[:3]
	}
	switch value {
	case "051", "052":
		return "5"
	case "071", "072":
		return "U"
	case "801", "802":
		return "9"
	case "021", "022", "901", "902":
		return "2"
	case "012":
		return "1"
	default:
		return "1"
	}
}

// MercuryCardInputCapability mirrors MercuryPosUtil.getCRDINP.
func MercuryCardInputCapability(posEntryMode string) string {
	if posEntryMode == "" || strings.TrimSpace(posEntryMode) == "" {
		return "1"
	}
	value := strings.TrimSpace(posEntryMode)
	if len(value) >= 3 {
		value = value[:3]
	}
	switch value {
	case "051", "052":
		return "5"
	case "071", "072":
		return "8"
	case "021", "022":
		return "2"
	case "012":
		return "1"
	default:
		return "1"
	}
}

// MercuryGeoArea mirrors MercurySplitService.getGEO: ISO alpha currency/country
// to the Mercury geo-area code; unknown -> "784".
func MercuryGeoArea(country string) string {
	if country == "" || strings.TrimSpace(country) == "" {
		return ""
	}
	switch strings.ToUpper(strings.TrimSpace(country)) {
	case "AED":
		return "784"
	case "USD":
		return "840"
	case "EUR":
		return "978"
	default:
		return "784"
	}
}

// JulianYDDD mirrors JulianDateConverter.getCurrentJulianYDDD: last digit of the
// year + zero-padded day-of-year (e.g. 2026-08-14 -> "6226", 2026-01-01 -> "6001").
func JulianYDDD(t time.Time) string {
	return fmt.Sprintf("%d%03d", t.Year()%10, t.YearDay())
}

// AddCheckDigit mirrors ARNCheckDigit.addCheckDigit (Luhn-style check digit).
func AddCheckDigit(number string) string {
	sum := 0
	for i := 0; i < len(number); i++ {
		d := int(number[len(number)-1-i] - '0')
		if i%2 == 0 {
			d *= 2
			if d > 9 {
				d -= 9
			}
		}
		sum += d
	}
	check := (10 - sum%10) % 10
	return number + strconv.Itoa(check)
}

// MercuryAcqRefData mirrors MercurySplitService.getAcqRefData:
// "2" + acqBin + julianYDDD + last-11-chars(rrn) + check digit.
func MercuryAcqRefData(acqBin, rrn string, now time.Time) string {
	arn := "2" + acqBin + JulianYDDD(now)
	if len(rrn) > 11 {
		arn += rrn[len(rrn)-11:]
	} else {
		arn += rrn
	}
	return AddCheckDigit(arn)
}
