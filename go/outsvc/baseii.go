package outsvc

import (
	"fmt"
	"math"
	"math/big"
	"strconv"
	"strings"
	"time"
)

// ---- Base II / TCRZero record builders (Go port of BaseIIOutgoingServiceImpl) ----

// The Visa Base II 1644 file is plain text, one record per line. Each record is
// a fixed-layout concatenation of fields (see TCRZeroVo.get*format in Java).
// Field values are pre-formatted (padded/truncated) before concatenation, so the
// builders below reproduce the exact Java StringUtils calls.

// currencyFractionDigits mirrors java.util.Currency.getDefaultFractionDigits().
// ISO 4217 minor units; defaults to 2 (most currencies). Parsed from
// CURRENCY_CODE_KAFKA (e.g. "USD000" -> USD -> 2).
func currencyFractionDigits(code string) int {
	if len(code) >= 3 {
		switch code[:3] {
		case "BHD", "IQD", "JOD", "KWD", "LYD", "OMR", "TND":
			return 3
		case "CLF", "MGA", "VUV":
			return 0
		case "JPY", "KRW", "PYG", "RWF", "UGX", "VND", "XAF", "XOF", "XPF":
			return 0
		default:
			return 2
		}
	}
	return 2
}

// fractionalDigits mirrors java.util.Currency.getDefaultFractionDigits(): the
// ISO 4217 minor-unit exponent for the code at the head of CURRENCY_CODE_KAFKA,
// defaulting to 2 for the vast majority of currencies.
func fractionalDigits(currencyCode string) int {
	return currencyFractionDigits(currencyCode)
}

// lpad/truncate helpers mirror commons-lang3 StringUtils:
//   leftPad(s, n, c) - pad on left to width n; unchanged if longer
//   rightPad(s, n, c) - pad on right to width n; unchanged if longer
//   left(s, n)  - first n chars (or whole string)
//   right(s, n) - last n chars (or whole string)
func sleftPad(s string, n int, c string) string {
	if len(s) >= n {
		return s
	}
	return strings.Repeat(c, n-len(s)) + s
}

func srightPad(s string, n int, c string) string {
	if len(s) >= n {
		return s
	}
	return s + strings.Repeat(c, n-len(s))
}

func sleft(s string, n int) string {
	if len(s) <= n {
		return s
	}
	return s[:n]
}

func sright(s string, n int) string {
	if len(s) <= n {
		return s
	}
	return s[len(s)-n:]
}

// srep repeats a single-char string n times (StringUtils.repeat).
func srep(c string, n int) string {
	if n <= 0 {
		return ""
	}
	return strings.Repeat(c, n)
}

// jstr maps an empty (NULL) Go string to Java's string-concat semantics for a
// null String/Character reference: the literal "null" appears in the output
// (String + null -> "null"). Non-empty values pass through unchanged.
func jstr(s string) string {
	if s == "" {
		return "null"
	}
	return s
}

// rpad mirrors StringUtils.rightPad with Java null semantics: a null input
// yields the literal "null" (rightPad(null) returns null, and the subsequent
// string concat prints "null"), rather than a padded empty field.
func rpad(s string, n int, c string) string {
	if s == "" {
		return "null"
	}
	return srightPad(s, n, c)
}

// lpad mirrors StringUtils.leftPad with Java null semantics (see rpad).
func lpad(s string, n int, c string) string {
	if s == "" {
		return "null"
	}
	return sleftPad(s, n, c)
}

// amtToInt converts a currency amount to its minor-unit integer using the
// multiplier (BigDecimal(amount)*multiplier setScale(0, HALF_UP)).
//
// BigDecimal.valueOf(v) uses Double.toString(v) -- the shortest exact decimal
// representation -- so 1.005 stays 1.005 rather than float64's
// 1.0049999... . We reproduce that with math/big.Rat on the same shortest
// decimal string before scaling, so HALF_UP rounding matches Java exactly.
func amtToInt(v float64, mult float64) int64 {
	s := strconv.FormatFloat(v, 'f', -1, 64)
	r, ok := new(big.Rat).SetString(s)
	if !ok {
		return int64(math.Round(v * mult))
	}
	fd := 0
	switch {
	case mult >= 1000:
		fd = 3
	case mult >= 100:
		fd = 2
	case mult >= 10:
		fd = 1
	}
	scale := new(big.Int).Exp(big.NewInt(10), big.NewInt(int64(fd)), nil)
	r = new(big.Rat).Mul(r, new(big.Rat).SetInt(scale))
	num := r.Num()
	den := r.Denom()
	q := new(big.Int).Quo(num, den)
	absRem := new(big.Int).Rem(new(big.Int).Abs(new(big.Int).Set(num)), den)
	if new(big.Int).Mul(big.NewInt(2), absRem).Cmp(den) >= 0 {
		if num.Sign() >= 0 {
			q.Add(q, big.NewInt(1))
		} else {
			q.Sub(q, big.NewInt(1))
		}
	}
	return q.Int64()
}

// BaseIIGenerator holds the file-level counters (91/92 footers) for one run of
// getFeeAndTxnData. A fresh generator is used per file.
type BaseIIGenerator struct {
	multiplier    float64
	txnCount91    int
	tcrCount91    int
	totalTxnAmt91 float64
	txnCount92    int
	tcrCount92    int
	allTcrCount92 int
	totalTxnAmt92 float64
	batchNumber   int
}

// NewBaseIIGenerator mirrors BaseIIOutgoingServiceImpl construction: the
// multiplier is derived from CURRENCY_CODE_KAFKA's currency fraction digits.
func NewBaseIIGenerator(currencyCode string) *BaseIIGenerator {
	fd := fractionalDigits(currencyCode)
	return &BaseIIGenerator{multiplier: math.Pow10(fd)}
}

const maxTCRPerBatch = 3250

// GetFeeAndTxnData mirrors BaseIIOutgoingServiceImpl.getFeeAndTxnData. It builds
// the file lines (TCR records + 91/92 footers) from the regular txn and fee
// entity lists, using the decrypted card map (token -> PAN).
//
// Returns nil lines when both lists are empty (Java: null txnEntity && null
// feeEntity). updateFailed is invoked for entities whose token could not be
// decrypted (Java: sets genStatus 7); it may be nil for tests.
func (g *BaseIIGenerator) GetFeeAndTxnData(feeEntity, txnEntity []*VisaAcqTxnWorkEntity, decrypted map[string]string, acquirerBins string, fileSequence int, updateFailed func(arn string)) []string {
	if txnEntity == nil && feeEntity == nil {
		return nil
	}
	var lines []string
	eventDate := time.Now().Format("0102")
	jDate := julianForFooter(time.Now())

	for _, e := range txnEntity {
		card, ok := decrypted[e.EncCardNumber]
		if !ok {
			if updateFailed != nil {
				updateFailed(e.Arn)
			}
			continue
		}
		g.txnCount91++
		if e.DccIndicator == "Y" {
			g.totalTxnAmt91 += dccOrTxn(e)
		} else {
			g.totalTxnAmt91 += e.TxnAmount
		}
		if line := g.getTcr0(e, card); line != "" {
			lines = append(lines, line)
			g.tcrCount91++
		}
		if line := g.getAdditionalData(e); line != "" {
			lines = append(lines, line)
			g.tcrCount91++
		}
		if line := g.getPaymentServiceData(e); line != "" {
			lines = append(lines, line)
			g.tcrCount91++
		}
		if (e.PosEntryMode == "05" || e.PosEntryMode == "07") && g.getChipCardTxnData(e) != "" {
			lines = append(lines, g.getChipCardTxnData(e))
			g.tcrCount91++
		}
		if e.BussAppId != "" && g.getAFTData(e) != "" {
			lines = append(lines, g.getAFTData(e))
			g.tcrCount91++
		}
		if g.tcrCount91 > maxTCRPerBatch {
			lines = append(lines, g.generateFooter91(acquirerBins, fileSequence, jDate))
		}
	}
	for _, e := range feeEntity {
		card, ok := decrypted[e.EncCardNumber]
		if !ok {
			if updateFailed != nil {
				updateFailed(e.Arn)
			}
			continue
		}
		g.txnCount91++
		g.totalTxnAmt91 += e.TxnAmount
		if line := g.getFeeCollection(e, card, acquirerBins, eventDate); line != "" {
			lines = append(lines, line)
			g.tcrCount91++
		}
		if g.tcrCount91 > maxTCRPerBatch {
			lines = append(lines, g.generateFooter91(acquirerBins, fileSequence, jDate))
		}
	}
	if g.txnCount91 > 0 {
		lines = append(lines, g.generateFooter91(acquirerBins, fileSequence, jDate))
	}
	lines = append(lines, g.generateFooter92(acquirerBins))
	return lines
}

// dccOrTxn returns the DCC amount when DCC applies, else the txn amount.
func dccOrTxn(e *VisaAcqTxnWorkEntity) float64 {
	if e.DccIndicator == "Y" && e.DccAmount != nil {
		return *e.DccAmount
	}
	return e.TxnAmount
}

func julianForFooter(t time.Time) string {
	return fmt.Sprintf("%02d%03d", t.Year()%100, t.YearDay())
}

// orDef is Optional.ofNullable(...).orElse(default).
func orDef(s, def string) string {
	if s == "" {
		return def
	}
	return s
}

// getTcr0 mirrors TCRZeroVo.getTcr0 + getTcr0format (transaction record).
func (g *BaseIIGenerator) getTcr0(e *VisaAcqTxnWorkEntity, card string) string {
	txnCode := orDef(e.TxnCode, "05")
	txnCodeQualifier := "0"
	if e.BussAppId != "" && e.TxnCode == "05" {
		txnCodeQualifier = "1"
	}
	accountNumber := sleft(srightPad(card, 16, "0"), 16)
	accountNumberExtension := sright(srightPad(card, 19, "0"), 3)

	var sourceAmount, sourceCurrencyCode string
	if e.DccIndicator == "Y" {
		sourceAmount = sleftPad(fmt.Sprintf("%d", amtToInt(dccOrTxn(e), g.multiplier)), 12, "0")
		sourceCurrencyCode = sleft(orDef(e.DccCurrency, "")+srep(" ", 3), 3)
	} else {
		sourceAmount = sleftPad(fmt.Sprintf("%d", amtToInt(e.TxnAmount, g.multiplier)), 12, "0")
		sourceCurrencyCode = sleft(orDef(e.TxnCurCode, "")+srep(" ", 3), 3)
	}

	var merchantName string
	if e.MeName != "" && strings.Contains(e.MeName, "&") {
		replaced := strings.ReplaceAll(e.MeName, "&", "^&")
		adjustedLength := 25 + len(replaced) - len(e.MeName)
		merchantName = sleft(replaced+srep(" ", adjustedLength), 25)
	} else {
		merchantName = sleft(orDef(e.MeName, "")+srep(" ", 25), 25)
	}

	var purchaseDate string
	if e.PurchaseDate != nil {
		purchaseDate = e.PurchaseDate.Format("0102")
	} else {
		purchaseDate = srep("0", 4)
	}

	var centralProcDate string
	if e.TxnCode == "25" || e.TxnCode == "26" {
		j := julianForFooter(time.Now())
		centralProcDate = j[1:5]
	} else {
		centralProcDate = "0000"
	}

	return txnCode +
		txnCodeQualifier + "0" +
		accountNumber + accountNumberExtension +
		" " + " " + " " +
		rpad(e.Arn, 23, " ") +
		"10087096" +
		purchaseDate +
		srep("0", 12) + srep(" ", 3) +
		sourceAmount + sourceCurrencyCode +
		merchantName +
		sleft(rpad(e.MeCity, 13, " "), 13) +
		srightPad(orDef(e.MeCountry, ""), 3, " ") +
		sleft(jstr(e.Mcc)+srep(" ", 4), 4) +
		srep("0", 5) + srep(" ", 3) +
		"9" + " " +
		"1" + "00" + "9" +
		g.getAuthorizationCharInd(e.AuthCharIndicator, e.MotoEcomIndicator) +
		sleft(orDef(e.ApprovalCode, "")+srep(" ", 6), 6) +
		jstr(e.TerminalCapability) +
		" " + orDef(e.ChIdMethod, " ") + " " +
		sleft(orDef(e.PosEntryMode, "")+srep(" ", 2), 2) +
		centralProcDate +
		"B"
}

// getAdditionalData mirrors getAdditionalData + getAdditionalDataformat.
func (g *BaseIIGenerator) getAdditionalData(e *VisaAcqTxnWorkEntity) string {
	txnCode := orDef(e.TxnCode, "05")
	txnCodeQualifier := "0"
	if e.BussAppId != "" && e.TxnCode == "05" {
		txnCodeQualifier = "1"
	}
	var cashback int64
	if e.CashbackAmount != nil {
		cashback = amtToInt(*e.CashbackAmount, g.multiplier)
	}
	accTrl := " "
	if e.AcceptanceTrlIndicator != "" {
		accTrl = e.AcceptanceTrlIndicator
	}
	return txnCode +
		txnCodeQualifier + "1" +
		" " + srep(" ", 2) + srep(" ", 5) + srep(" ", 4) + srep("0", 6) +
		" " +
		sleft(orDef(e.MemberText, "")+srep(" ", 50), 50) +
		srep(" ", 2) +
		sleft(orDef(e.FeePrgIndicator, "")+srep(" ", 3), 3) +
		" " + " " +
		rpad(e.MerchantId, 15, " ") +
		sleft(orDef(e.TerminalId, "")+srep(" ", 8), 8) +
		srep("0", 12) +
		orDef(e.MotoEcomIndicator, " ") +
		" " + srep("0", 4) + srep("0", 2) +
		accTrl +
		" " + "0" + " " + "5" +
		" " + orDef(e.AccSelection, " ") +
		srep(" ", 2) + srep(" ", 25) +
		sleftPad(fmt.Sprintf("%d", cashback), 9, "0") +
		" " + orDef(e.PosEnvironment, " ")
}

// getPaymentServiceData mirrors getPaymentServiceData + format.
func (g *BaseIIGenerator) getPaymentServiceData(e *VisaAcqTxnWorkEntity) string {
	txnCode := orDef(e.TxnCode, "05")
	var authAmount int64
	if e.AuthAmount != nil {
		authAmount = amtToInt(*e.AuthAmount, g.multiplier)
	}
	dcc := " "
	if e.DccIndicator == "Y" {
		dcc = "1"
	}
	return txnCode +
		"0" + "5" +
		sright(lpad(e.TxnId, 15, "0"), 15) +
		sleftPad(fmt.Sprintf("%d", authAmount), 12, "0") +
		sleft(orDef(e.TxnCurCode, "")+srep(" ", 3), 3) +
		sright("00"+jstr(e.RespCode), 2) +
		orDef(e.ValidationCode, srep(" ", 4)) +
		" " + " " + srep(" ", 2) +
		"01" + "01" +
		orDef(e.MarketSpecDataInd, " ") +
		srep("0", 12) +
		"N" +
		srep(" ", 14) + " " + srep(" ", 2) + srep(" ", 2) + srep(" ", 10) +
		srep("0", 15) + " " +
		srep("0", 8) + srep("0", 8) + srep("0", 12) +
		srep(" ", 2) + srep(" ", 6) +
		dcc +
		srep(" ", 4) +
		orDef(e.SpendQualiIndictor, " ") +
		srep(" ", 16) + " " + " " + " "
}

// getChipCardTxnData mirrors getChipCardTxnData + format. Only emitted when
// posEntryMode is "05" or "07".
func (g *BaseIIGenerator) getChipCardTxnData(e *VisaAcqTxnWorkEntity) string {
	txnCode := orDef(e.TxnCode, "05")
	var trlTxnDate string
	if e.TrlTxnDate != nil {
		trlTxnDate = e.TrlTxnDate.Format("060102")
	} else {
		trlTxnDate = srep("0", 6)
	}
	var cryptAmt int64
	if e.CryptAmount != nil {
		cryptAmt = amtToInt(*e.CryptAmount, g.multiplier)
	}
	return txnCode +
		"0" + "7" +
		srep("0", 2) +
		orDef(e.CardSeqNumber, srep("0", 3)) +
		trlTxnDate +
		orDef(e.TrlCapProfile, srep("0", 6)) +
		orDef(e.TrlCountryCode, srep("0", 3)) +
		srep(" ", 8) +
		orDef(e.UpblNumber, srep("0", 8)) +
		orDef(e.AppTxnCounter, srep(" ", 4)) +
		orDef(e.AppIcProfile, srep(" ", 4)) +
		orDef(e.AppCryptogram, srep(" ", 16)) +
		orDef(e.IssAppDataB2, srep("0", 2)) +
		orDef(e.IssAppDataB3, srep(" ", 2)) +
		orDef(e.TrlVerResult, srep("0", 10)) +
		orDef(e.IssAppDataB4, srep("0", 8)) +
		sleftPad(fmt.Sprintf("%d", cryptAmt), 12, "0") +
		orDef(e.IssAppDataB8, srep("0", 2)) +
		orDef(e.IssAppDataB9, srep("0", 16)) +
		orDef(e.IssAppDataB1, srep("0", 2)) +
		orDef(e.IssAppDataB17, srep("0", 2)) +
		orDef(e.IssAppDataB18, srep("0", 30)) +
		orDef(e.FormFactorIndicator, srep("0", 8)) +
		srep("0", 10)
}

// getFeeCollection mirrors getFeeCollection + getFeeCollectionformat (txn codes
// 10/20 fee records).
func (g *BaseIIGenerator) getFeeCollection(e *VisaAcqTxnWorkEntity, card, acqBin, eventDate string) string {
	return jstr(e.TxnCode) +
		"0" + "0" +
		sleftPad(card, 6, " ") +
		lpad(acqBin, 6, " ") +
		srep("0", 4) + srep(" ", 3) +
		eventDate +
		sleftPad(card, 16, "0") +
		sleftPad(card, 3, "0") +
		srep("0", 12) + srep(" ", 3) +
		sleftPad(fmt.Sprintf("%d", amtToInt(e.TxnAmount, g.multiplier)), 12, "0") +
		lpad(e.TxnCurCode, 3, " ") +
		rpad(e.Arn, 70, " ") +
		"9" +
		lpad(e.TxnId, 15, "0") +
		" " + srep("0", 4) + "B"
}

// getAFTData mirrors getAFTData + getAFTDataformat (Account Funding
// Transactions; only when bussAppId present and txnCode 05).
func (g *BaseIIGenerator) getAFTData(e *VisaAcqTxnWorkEntity) string {
	return jstr(e.TxnCode) +
		"1" + "3" +
		"00" +
		srep(" ", 9) + " " +
		"CR" + e.BussAppId + "3" +
		srep(" ", 2) + srep(" ", 16) +
		sleft(orDef(e.SenderAccount, "")+srep(" ", 34), 34) +
		sleft(orDef(e.SenderName, "")+srep(" ", 30), 30) +
		srep(" ", 35) + srep(" ", 25) + srep(" ", 2) + srep(" ", 3)
}

// getAuthorizationCharInd mirrors the Java switch on the ecom indicator.
func (g *BaseIIGenerator) getAuthorizationCharInd(authorizationInd, ecomIndicator string) string {
	if authorizationInd != "" && authorizationInd != " " {
		return authorizationInd
	}
	if ecomIndicator == "" || ecomIndicator == " " {
		return "E"
	}
	switch ecomIndicator {
	case "5":
		return "U"
	case "6":
		return "S"
	default:
		return "W"
	}
}

// generateFooter91 mirrors BaseIIOutgoingServiceImpl.generateFooter91.
func (g *BaseIIGenerator) generateFooter91(acqBin string, fileSeq int, jDate string) string {
	g.batchNumber++
	g.allTcrCount92++
	footer := "91" + "00" + acqBin +
		srep("0", 5) + srep("0", 15) +
		sright(sleftPad(fmt.Sprintf("%d", g.txnCount91), 12, "0"), 12) +
		sright(sleftPad(fmt.Sprintf("%d", g.batchNumber), 6, "0"), 6) +
		sright(sleftPad(fmt.Sprintf("%d", g.tcrCount91+1), 12, "0"), 12) +
		srep(" ", 6) +
		jDate + sright(sleftPad(fmt.Sprintf("%d", fileSeq), 3, "0"), 3) +
		sright(sleftPad(fmt.Sprintf("%d", g.txnCount91+1), 9, "0"), 9) +
		srep(" ", 18) +
		sright(sleftPad(fmt.Sprintf("%d", amtToInt(g.totalTxnAmt91, g.multiplier)), 15, "0"), 15) +
		srep(" ", 15) + srep(" ", 15) + srep(" ", 15) + srep(" ", 7)
	g.txnCount92 += g.txnCount91
	g.tcrCount92 += g.tcrCount91
	g.allTcrCount92 += g.tcrCount91
	g.totalTxnAmt92 += g.totalTxnAmt91
	g.txnCount91 = 0
	g.tcrCount91 = 0
	g.totalTxnAmt91 = 0
	return footer
}

// generateFooter92 mirrors BaseIIOutgoingServiceImpl.generateFooter92.
func (g *BaseIIGenerator) generateFooter92(acqBin string) string {
	footer := "92" + "00" + acqBin +
		srep("0", 5) + srep("0", 15) +
		sright(sleftPad(fmt.Sprintf("%d", g.txnCount92), 12, "0"), 12) +
		sright(sleftPad(fmt.Sprintf("%d", g.batchNumber), 6, "0"), 6) +
		sright(sleftPad(fmt.Sprintf("%d", g.allTcrCount92+1), 12, "0"), 12) +
		srep(" ", 6) + srep(" ", 8) +
		sright(sleftPad(fmt.Sprintf("%d", g.txnCount92+g.batchNumber+1), 9, "0"), 9) +
		srep(" ", 18) +
		sright(sleftPad(fmt.Sprintf("%d", amtToInt(g.totalTxnAmt92, g.multiplier)), 15, "0"), 15) +
		srep(" ", 15) + srep(" ", 15) + srep(" ", 15) + srep(" ", 7)
	g.txnCount91 = 0
	g.tcrCount91 = 0
	g.totalTxnAmt91 = 0
	g.txnCount92 = 0
	g.tcrCount92 = 0
	g.allTcrCount92 = 0
	g.totalTxnAmt92 = 0
	g.batchNumber = 0
	return footer
}
