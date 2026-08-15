package outsvc

import (
	"errors"
	"strconv"
)

var errFieldTooShort = errors.New("field value shorter than fixed element length")

// IPM is a faithful Go port of the Java com.empay.IPMProcessing.IPM class:
// the ISO-8583 field-length/type tables and the ASCII<->EBCDIC conversion
// used to build Mastercard IPM settlement files.

// tcode is the ASCII->EBCDIC lookup matrix (Tcode in Java), indexed by
// [highNibble][lowNibble] of the ASCII byte value.
var tcode = [16][16]byte{
	{0, 1, 2, 3, 55, 45, 46, 47, 22, 5, 37, 11, 12, 13, 14, 15},
	{16, 17, 18, 19, 60, 61, 50, 38, 24, 25, 63, 39, 28, 29, 30, 31},
	{64, 90, 127, 123, 91, 108, 80, 125, 77, 93, 92, 78, 107, 96, 75, 97},
	{240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 122, 94, 76, 126, 110, 111},
	{124, 193, 194, 195, 196, 197, 198, 199, 200, 201, 209, 210, 211, 212, 213, 214},
	{215, 216, 217, 226, 227, 228, 229, 230, 231, 232, 233, 74, 224, 79, 95, 109},
	{121, 129, 130, 131, 132, 133, 134, 135, 136, 137, 145, 146, 147, 148, 149, 150},
	{151, 152, 153, 162, 163, 164, 165, 166, 167, 168, 169, 192, 106, 208, 161, 7},
	{32, 33, 34, 35, 36, 21, 6, 23, 40, 41, 42, 43, 44, 9, 10, 27},
	{48, 49, 26, 51, 52, 53, 54, 8, 56, 57, 58, 59, 4, 20, 62, 225},
	{65, 66, 67, 68, 69, 70, 71, 72, 73, 81, 82, 83, 84, 85, 86, 87},
	{88, 89, 98, 99, 100, 101, 102, 103, 104, 105, 112, 113, 114, 115, 116, 117},
	{118, 119, 120, 128, 138, 139, 140, 141, 142, 143, 144, 154, 155, 156, 157, 158},
	{159, 160, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183},
	{184, 185, 186, 187, 188, 189, 190, 191, 202, 203, 204, 205, 206, 207, 218, 219},
	{220, 221, 222, 223, 234, 235, 236, 237, 238, 239, 250, 251, 252, 253, 254, 255},
}

// lengthOfElement mirrors IPM.LengthOfElement.
var lengthOfElement = [129]int{
	8, 8, -1, 6, 12, 12, 12, 10, 8, 8, 8, 6, 12, 4, 4, 4,
	4, 4, 4, 3, 3, 3, 12, 3, 3, 4, 4, 1, 6, 3, 24, -1,
	-1, -1, -1, -1, -2, 12, 6, 3, 3, 8, 15, -1, -1, -1, -2, -2,
	-2, 3, 3, 3, 16, -1, -2, -2, -1, 3, -1, -2, -2, -2, -2, -2,
	8, 8, -2, 2, 3, 3, 3, 8, -2, 6, 10, 10, 10, 10, 10, 10,
	10, 10, 10, 10, 10, 10, 16, 16, 16, 16, 42, 3, 3, -1, -1, -1,
	-2, 17, 25, -1, -1, -1, -1, -1, -2, 16, 16, 10, 10, -1, -1, -2,
	-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
	8,
}

// typeOfElement mirrors IPM.TypeOfElement.
var typeOfElement = [129]int{
	0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2,
	2, 2, 2, 3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3,
	1, 1, 1, 1, 2, 3, 5, 3, 1, 2, 3, 3, 3, 3, 3, 1,
	1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1,
	1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3,
	1, 1, 2, 2, 2, 2, 2, 3, 1, 1, 1, 1, 2, 2, 3, 3,
	3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1,
}

// asciiToEbcdic converts a single ASCII byte to its EBCDIC equivalent.
func asciiToEbcdic(val int) byte {
	return tcode[(val>>4)&0xF][val&0xF]
}

// asciiToHex converts an ASCII hex digit char to its 0..15 value.
func asciiToHex(c byte) byte {
	switch {
	case c >= '0' && c <= '9':
		return c - 48
	case c >= 'A' && c <= 'F':
		return c - 65 + 10
	case c >= 'a' && c <= 'f':
		return c - 97 + 10
	default:
		return 0
	}
}

// getFieldLength returns the fixed/encoded length for ISO element index (1-based
// as in Java GetFieldLength). Negative values denote variable-length encodings.
func getFieldLength(idx int) int {
	if idx < 0 || idx > 128 {
		return 0
	}
	return lengthOfElement[idx]
}

// getFieldType returns the type code for ISO element index (0-based as in Java).
func getFieldType(idx int) int {
	if idx < 0 || idx > 128 {
		return 0
	}
	return typeOfElement[idx]
}

// createBitMap sets the bit for ISO element index (0-based) in the 16-byte map.
func createBitMap(idx int, bm *[16]byte) {
	pos := idx / 8
	bit := byte(128 >> (idx % 8))
	bm[pos] |= bit
}

// setSecondaryBitMap sets bit 0x80 on byte 0 if any secondary (bytes 8..15) bit
// is set (mirrors IPM.SetSecondaryBitMap).
func setSecondaryBitMap(bm *[16]byte) {
	var sbit byte
	for i := 8; i < 16; i++ {
		sbit |= bm[i]
	}
	if sbit != 0 {
		bm[0] |= 0x80
	}
}

// padZeroLeft left-pads s with '0' to width w (Java StringUtils.leftPad).
func padZeroLeft(s string, w int) string {
	if len(s) >= w {
		return s
	}
	return repeatBytes('0', w-len(s)) + s
}

// padZeroRight right-pads s with '0' to width w (Java StringUtils.rightPad).
func padZeroRight(s string, w int) string {
	if len(s) >= w {
		return s
	}
	return s + repeatBytes('0', w-len(s))
}

// padSpaceLeft left-pads s with spaces to width w.
func padSpaceLeft(s string, w int) string {
	if len(s) >= w {
		return s
	}
	return repeatBytes(' ', w-len(s)) + s
}

// padSpaceRight right-pads s with spaces to width w.
func padSpaceRight(s string, w int) string {
	if len(s) >= w {
		return s
	}
	return s + repeatBytes(' ', w-len(s))
}

// cutLeft truncates s to the leftmost w chars (Java StringUtils.left).
func cutLeft(s string, w int) string {
	if len(s) <= w {
		return s
	}
	return s[:w]
}

// cutRight truncates s to the rightmost w chars (Java StringUtils.right).
func cutRight(s string, w int) string {
	if len(s) <= w {
		return s
	}
	return s[len(s)-w:]
}

// leftPadDefault is StringUtils.leftPad(s, size, pad) + right(s,size) combined:
// leftPad to size then right-truncate to size (net effect: exact-size value).
func lrZero(s string, w int) string {
	return cutRight(padZeroLeft(s, w), w)
}

func repeatBytes(c byte, n int) string {
	if n <= 0 {
		return ""
	}
	b := make([]byte, n)
	for i := range b {
		b[i] = c
	}
	return string(b)
}

// addIsoField appends the encoded ISO element (0-based index, as Java
// AddIsoField) to msg, setting the corresponding bitmap bit. It returns the
// new message and any encoding error.
func addIsoField(msg string, dataElementIndex int, fieldValue string, bm *[16]byte) (string, error) {
	t := getFieldType(dataElementIndex)
	de := strconv.Itoa(dataElementIndex + 1)
	var err error
	switch t {
	case 0:
		msg, err = addFieldBIN(de, msg, fieldValue)
	case 1:
		msg, err = addFieldAlphaAscii(de, msg, fieldValue)
	case 2:
		msg = addFieldLLVARAscii(de, msg, fieldValue)
	case 3:
		msg = addFieldLLLVARAscii(de, msg, fieldValue)
	case 5:
		msg = addFieldLLLVAR(de, msg, fieldValue)
	}
	if err != nil {
		return msg, err
	}
	createBitMap(dataElementIndex, bm)
	return msg, nil
}

func addFieldBIN(de, msg, fieldValue string) (string, error) {
	n := getFieldLength(atoi(de))
	if len(fieldValue) < n {
		return msg, errFieldTooShort
	}
	return msg + fieldValue[:n], nil
}

func addFieldAlphaAscii(de, msg, fieldValue string) (string, error) {
	n := getFieldLength(atoi(de))
	if len(fieldValue) < n {
		return msg, errFieldTooShort
	}
	return msg + fieldValue[:n], nil
}

func addFieldLLVARAscii(de, msg, fieldValue string) string {
	return msg + twoDigit(len(fieldValue)) + fieldValue
}

func addFieldLLLVARAscii(de, msg, fieldValue string) string {
	return msg + threeDigit(len(fieldValue)) + fieldValue
}

func addFieldLLLVAR(de, msg, fieldValue string) string {
	msg += threeDigit(len(fieldValue) / 2)
	return msg + to55Field(fieldValue, len(fieldValue))
}

// to55Field packs pairs of ASCII hex digits into binary bytes (IPM.To55Field).
func to55Field(asciiData string, length int) string {
	j := 0
	temp := make([]byte, 512)
	for i := 0; i < length; {
		i++
		j = i / 2
		var lo byte
		if i < length {
			lo = asciiToHex(asciiData[i])
		}
		temp[j] = (asciiToHex(asciiData[i-1]) << 4) | lo
		i++
	}
	return string(temp[:j+1])
}

// processIsoMessageAscii computes the element position table for a message
// (IPM.ProcessIsoMessageAscii). Only needed for dump diagnostics.
func processIsoMessageAscii(isoMsg string) [129]int {
	var pos [129]int
	var length [3]byte
	startPoint := 4
	pos[0] = 8
	bitmapLen := 8
	if isoMsg[4]&0x80 > 0 {
		bitmapLen = 16
	}
	k := 0
	for j := 0; j < bitmapLen; j++ {
		bit := byte(128)
		bitMap := isoMsg[startPoint+j]
		for i := 0; i < 8; i++ {
			k = j*8 + i + 1
			pos[k] = pos[k-1]
			if bitMap&bit > 0 {
				switch lengthOfElement[k] {
				case -2:
					length[0] = isoMsg[startPoint+pos[k]]
					length[1] = isoMsg[startPoint+pos[k]+1]
					length[2] = isoMsg[startPoint+pos[k]+2]
					n := atoi(string(length[:]))
					pos[k] += n + 3
				case -1:
					length[0] = isoMsg[startPoint+pos[k]]
					length[1] = isoMsg[startPoint+pos[k]+1]
					n := atoi(string(length[:2]))
					pos[k] += n + 2
				default:
					pos[k] += lengthOfElement[k]
				}
			}
			bit = (bit >> 1) & 0x7F
		}
	}
	for i := k + 1; i < 129; i++ {
		pos[i] = pos[i-1]
	}
	return pos
}

// getIsoField extracts a single field from a message given the element
// position table computed by processIsoMessageAscii (IPM.GetIsoField).
func getIsoField(isoMsg string, dataElementIndex int, dataFormat string, pos [129]int) string {
	msg := isoMsg[4:]
	index := dataElementIndex
	fieldValue := ""
	de := strconv.Itoa(index + 1)
	posOf := pos[index+1] - pos[index]
	_ = posOf
	if isFieldPresent(dataElementIndex, pos) {
		return ""
	}
	switch getFieldType(index) {
	case 0:
		fieldValue = getFieldBIN(de, msg, pos)
	case 1:
		fieldValue = getFieldAlphaAscii(de, msg, pos)
	case 2:
		fieldValue = getFieldLLVARAscii(de, msg, pos)
	case 3:
		fieldValue = getFieldLLLVARAscii(de, msg, pos)
	case 5:
		fieldValue = getFieldLLLVARB(de, msg, dataFormat, pos)
	}
	return fieldValue
}

// isFieldPresent returns true when the field occupies bytes (IPM.IsFieldPresent
// returns 0/-1; we invert).
func isFieldPresent(idx int, pos [129]int) bool {
	return pos[idx+1]-pos[idx] <= 0
}

func getFieldBIN(de, msg string, pos [129]int) string {
	start := pos[atoi(de)-1]
	return msg[start : start+getFieldLength(atoi(de))]
}

func getFieldAlphaAscii(de, msg string, pos [129]int) string {
	start := pos[atoi(de)-1]
	return msg[start : start+getFieldLength(atoi(de))]
}

func getFieldLLVARAscii(de, msg string, pos [129]int) string {
	start := pos[atoi(de)-1]
	dataLen := atoi(msg[start : start+2])
	return msg[start+2 : start+2+dataLen]
}

func getFieldLLLVARAscii(de, msg string, pos [129]int) string {
	start := pos[atoi(de)-1]
	dataLen := atoi(msg[start : start+3])
	return msg[start+3 : start+3+dataLen]
}

func getFieldLLLVARB(de, msg, dataFormat string, pos [129]int) string {
	start := pos[atoi(de)-1]
	dataLen := atoi(msg[start : start+3])
	return fieldTo55(msg[start+3:], dataLen, dataFormat)
}

func fieldTo55(ebcdicData string, length int, dataFormat string) string {
	asciiData := ""
	for i := 0; i < length; i++ {
		var c byte
		if dataFormat == "EBCIDIC" {
			c = asciiToEbcdic(int(ebcdicData[i]))
		} else {
			c = ebcdicData[i]
		}
		asciiData += string(convTo55(c >> 4 & 0xF))
		asciiData += string(convTo55(c & 0xF))
	}
	return asciiData
}

func convTo55(c byte) byte {
	switch {
	case c <= 9:
		return c + 48
	case c == 10:
		return 65
	case c == 11:
		return 66
	case c == 12:
		return 67
	case c == 13:
		return 68
	case c == 14:
		return 69
	case c == 15:
		return 70
	}
	return c
}

func atoi(s string) int {
	n := 0
	for i := 0; i < len(s); i++ {
		if s[i] < '0' || s[i] > '9' {
			break
		}
		n = n*10 + int(s[i]-'0')
	}
	return n
}

func twoDigit(n int) string {
	if n > 99 {
		return "99"
	}
	return string([]byte{byte(n/10) + '0', byte(n%10) + '0'})
}

func threeDigit(n int) string {
	if n > 999 {
		return "999"
	}
	return string([]byte{byte(n/100) + '0', byte(n/10%10) + '0', byte(n%10) + '0'})
}
