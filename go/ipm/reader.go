package ipm

import (
	"fmt"
	"strings"
)

// Reader is a positional reader over a binary record payload. It is the Go
// port of parser/de_reader.py (DEReader).
type Reader struct {
	data []byte
	pos  int
}

// maxLengthSkip mirrors DEReader.MAX_LENGTH_SKIP.
const maxLengthSkip = 8

// NewReader returns a Reader over payload.
func NewReader(payload []byte) *Reader { return &Reader{data: payload} }

// Tell returns the current read position.
func (r *Reader) Tell() int { return r.pos }

// Seek moves the read position.
func (r *Reader) Seek(pos int) { r.pos = pos }

// EOF reports whether the position has reached the end of the payload.
func (r *Reader) EOF() bool { return r.pos >= len(r.data) }

// Remaining returns the number of unread bytes.
func (r *Reader) Remaining() int { return len(r.data) - r.pos }

// ReadBytes returns the next length bytes and advances. A short read returns
// everything available, mirroring Python slicing.
func (r *Reader) ReadBytes(length int) []byte {
	if length < 0 {
		length = 0
	}
	end := r.pos + length
	if end > len(r.data) {
		end = len(r.data)
	}
	out := r.data[r.pos:end]
	r.pos = end
	return out
}

// ReadUint reads a big-endian unsigned integer of the given width.
func (r *Reader) ReadUint(length int) uint64 {
	var v uint64
	for _, b := range r.ReadBytes(length) {
		v = v<<8 | uint64(b)
	}
	return v
}

// Peek returns up to length bytes without advancing.
func (r *Reader) Peek(length int) []byte {
	end := r.pos + length
	if end > len(r.data) {
		end = len(r.data)
	}
	return r.data[r.pos:end]
}

// Skip advances the position by length.
func (r *Reader) Skip(length int) { r.pos += length }

// ReadBitmap returns the next 8 bytes.
func (r *Reader) ReadBitmap() []byte { return r.ReadBytes(8) }

// ReadEbcdic reads length EBCDIC characters, skipping embedded 0x00 padding,
// so the decoded string holds exactly length characters (or fewer at EOF).
func (r *Reader) ReadEbcdic(length int) string {
	collected := make([]byte, 0, length)
	for len(collected) < length && r.pos < len(r.data) {
		b := r.data[r.pos]
		r.pos++
		if b == 0x00 {
			continue
		}
		collected = append(collected, b)
	}
	return decodeEbcdicIgnore(collected)
}

// ReadLength reads an EBCDIC numeric length of the given digit count. It
// tolerates EBCDIC control bytes (0x00-0x3F) and 0xAA in front of/inside the
// length field, up to maxLengthSkip of them, exactly like the Python reader.
func (r *Reader) ReadLength(digits int) (string, error) {
	start := r.pos
	collected := make([]byte, 0, digits)
	junk := 0
	pos := start

loop:
	for len(collected) < digits && pos < len(r.data) {
		b := r.data[pos]
		switch {
		case b >= 0xF0 && b <= 0xF9:
			collected = append(collected, b)
		case b < 0x40 || b == 0xAA:
			junk++
			if junk > maxLengthSkip {
				break loop
			}
		default:
			break loop
		}
		pos++
	}
	if len(collected) == digits {
		r.pos = pos
		return decodeEbcdic(collected), nil
	}
	return "", fmt.Errorf("invalid %d-digit length at offset %d", digits, start)
}

// ReadLLVAR reads a two-digit-length-prefixed EBCDIC field.
func (r *Reader) ReadLLVAR() (string, error) {
	n, err := r.ReadLength(2)
	if err != nil {
		return "", err
	}
	return r.ReadEbcdic(atoiSafe(n)), nil
}

// ReadLLLVAR reads a three-digit-length-prefixed EBCDIC field.
func (r *Reader) ReadLLLVAR() (string, error) {
	n, err := r.ReadLength(3)
	if err != nil {
		return "", err
	}
	return r.ReadEbcdic(atoiSafe(n)), nil
}

// ReadFixed reads length EBCDIC characters.
func (r *Reader) ReadFixed(length int) string { return r.ReadEbcdic(length) }

func atoiSafe(s string) int {
	v := 0
	for i := 0; i < len(s); i++ {
		if s[i] < '0' || s[i] > '9' {
			return 0
		}
		v = v*10 + int(s[i]-'0')
	}
	return v
}

// Bitmap is the ISO-8583 bitmap of a record (parser/bitmap.py).
type Bitmap struct {
	primary   []byte
	secondary []byte
}

// BitmapFromReader reads the primary bitmap and, when bit 1 is set, the
// 8-byte secondary bitmap.
func BitmapFromReader(r *Reader) *Bitmap {
	primary := r.ReadBytes(8)
	b := &Bitmap{primary: primary}
	if len(primary) == 8 && primary[0]&0x80 != 0 {
		b.secondary = r.ReadBytes(8)
	}
	return b
}

// HasSecondary reports whether a secondary bitmap was read.
func (b *Bitmap) HasSecondary() bool { return b.secondary != nil }

// Raw returns primary+secondary (or just primary).
func (b *Bitmap) Raw() []byte {
	if b.secondary != nil {
		return append(append([]byte{}, b.primary...), b.secondary...)
	}
	return b.primary
}

// PresentDEs returns the numbers of all present data elements.
func (b *Bitmap) PresentDEs() []int {
	raw := b.Raw()
	des := make([]int, 0, len(raw)*8)
	bitno := 1
	for _, by := range raw {
		for i := 0; i < 8; i++ {
			if by&(1<<(7-uint(i))) != 0 {
				des = append(des, bitno)
			}
			bitno++
		}
	}
	return des
}

// ReadField reads one data element according to its metadata definition
// (parser/field_reader.py).
func ReadField(r *Reader, f FieldDef) (string, error) {
	format := strings.ToUpper(f.Format)
	ftype := strings.ToLower(f.Type)

	// DE55 (EMV TLV): 3-digit EBCDIC length then raw binary.
	if ftype == "b" && format == "LLLVAR" {
		n, err := r.ReadLength(3)
		if err != nil {
			return "", err
		}
		return strings.ToUpper(hexUpper(r.ReadBytes(atoiSafe(n)))), nil
	}

	switch format {
	case "FIXED":
		if f.Encoding == "BINARY" {
			return strings.ToUpper(hexUpper(r.ReadBytes(f.Length))), nil
		}
		return r.ReadFixed(f.Length), nil
	case "LLVAR":
		return r.ReadLLVAR()
	case "LLLVAR":
		return r.ReadLLLVAR()
	case "BINARY":
		return strings.ToUpper(hexUpper(r.ReadBytes(f.Length))), nil
	}
	return "", fmt.Errorf("unsupported format %s", format)
}

const hexDigits = "0123456789ABCDEF"

func hexUpper(b []byte) string {
	out := make([]byte, 0, len(b)*2)
	for _, c := range b {
		out = append(out, hexDigits[c>>4], hexDigits[c&0x0F])
	}
	return string(out)
}

// PDS is one parsed PDS chunk (parser/pds.py): PPPP LLL DATA.
type PDS struct {
	ID     string
	Length int
	Value  string
}

// ParsePDS parses a DE48/PDS blob into its chunks.
func ParsePDS(text string) []PDS {
	pos := 0
	var out []PDS
	for pos < len(text) {
		if pos+7 > len(text) {
			break
		}
		id := text[pos : pos+4]
		length := atoiSafe(text[pos+4 : pos+7])
		// Python raises on a non-numeric length and breaks; atoiSafe returns
		// 0 for those, which we treat as a malformed tail.
		if length <= 0 && !isAllDigits(text[pos+4:pos+7]) {
			break
		}
		end := pos + 7 + length
		if end > len(text) {
			value := text[pos+7:]
			out = append(out, PDS{ID: id, Length: len(value), Value: value})
			break
		}
		out = append(out, PDS{ID: id, Length: length, Value: text[pos+7 : end]})
		pos = end
	}
	return out
}

func isAllDigits(s string) bool {
	if s == "" {
		return false
	}
	for i := 0; i < len(s); i++ {
		if s[i] < '0' || s[i] > '9' {
			return false
		}
	}
	return true
}

// PDSDict is the {id: {name, value}} shape the compliance rules address.
type PDSDict map[string]PDSDictEntry

// PDSDictEntry is one PDS chunk with its resolved name.
type PDSDictEntry struct {
	Name  string
	Value string
}

func pdsToDict(list []PDS, names map[string]string) PDSDict {
	out := make(PDSDict, len(list))
	for _, p := range list {
		name := names[p.ID]
		if name == "" {
			name = p.ID
		}
		out[p.ID] = PDSDictEntry{Name: name, Value: p.Value}
	}
	return out
}
