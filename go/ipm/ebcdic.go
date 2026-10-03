package ipm

// tcode is the ASCII->EBCDIC lookup matrix (Tcode in the Java IPM class),
// indexed by [highNibble][lowNibble] of the ASCII byte. It is identical to
// the matrix the file generator uses (outsvc/ipm.go), so decoding here and
// encoding there can never disagree about the alphabet.
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

// ebcdicToASCII is the inverse mapping, built once from tcode. Where two
// ASCII bytes map to the same EBCDIC byte the first (lowest ASCII) wins,
// which is unreachable for the printable range but keeps the table total.
var ebcdicToASCII = func() [256]byte {
	var t [256]byte
	for i := range t {
		t[i] = ' '
	}
	seen := make([]bool, 256)
	for a := 0; a < 256; a++ {
		e := tcode[(a>>4)&0xF][a&0xF]
		if !seen[e] {
			seen[e] = true
			t[e] = byte(a)
		}
	}
	// Newline in EBCDIC is 0x15/0x25; map them back to '\n' explicitly.
	t[0x15] = '\n'
	t[0x25] = '\n'
	return t
}()

// decodeEbcdic decodes a cp037-ish byte string, dropping undecodable bytes
// the way Python's decode("cp037", errors="ignore") does.
func decodeEbcdic(b []byte) string {
	out := make([]byte, 0, len(b))
	for _, c := range b {
		out = append(out, ebcdicToASCII[c])
	}
	return string(out)
}

// decodeEbcdicIgnore behaves like decodeEbcdic but skips the NUL byte, which
// is what readEbcdic does implicitly while skipping embedded padding.
func decodeEbcdicIgnore(b []byte) string {
	out := make([]byte, 0, len(b))
	for _, c := range b {
		if c == 0x00 {
			continue
		}
		out = append(out, ebcdicToASCII[c])
	}
	return string(out)
}
