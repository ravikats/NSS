/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.IPMProcessing.IPM
 *  org.springframework.stereotype.Service
 */
package com.empay.IPMProcessing;

import org.springframework.stereotype.Service;

@Service
public class IPM {
    private int[] LengthOfElement = new int[129];
    private int[] TypeOfElement = new int[129];
    private int[] ElementPossition = new int[129];
    private static char[][] Tcode = new char[16][16];
    private static char[] Row = new char[16];
    private static char[] Column = new char[16];

    public IPM() {
        this.Initialize();
    }

    private void Initialize() {
        this.InitLengthOfElement();
        this.InitTypeOfElement();
    }

    private void InitLengthOfElement() {
        this.LengthOfElement[0] = 8;
        this.LengthOfElement[1] = 8;
        this.LengthOfElement[2] = -1;
        this.LengthOfElement[3] = 6;
        this.LengthOfElement[4] = 12;
        this.LengthOfElement[5] = 12;
        this.LengthOfElement[6] = 12;
        this.LengthOfElement[7] = 10;
        this.LengthOfElement[8] = 8;
        this.LengthOfElement[9] = 8;
        this.LengthOfElement[10] = 8;
        this.LengthOfElement[11] = 6;
        this.LengthOfElement[12] = 12;
        this.LengthOfElement[13] = 4;
        this.LengthOfElement[14] = 4;
        this.LengthOfElement[15] = 4;
        this.LengthOfElement[16] = 4;
        this.LengthOfElement[17] = 4;
        this.LengthOfElement[18] = 4;
        this.LengthOfElement[19] = 3;
        this.LengthOfElement[20] = 3;
        this.LengthOfElement[21] = 3;
        this.LengthOfElement[22] = 12;
        this.LengthOfElement[23] = 3;
        this.LengthOfElement[24] = 3;
        this.LengthOfElement[25] = 4;
        this.LengthOfElement[26] = 4;
        this.LengthOfElement[27] = 1;
        this.LengthOfElement[28] = 6;
        this.LengthOfElement[29] = 3;
        this.LengthOfElement[30] = 24;
        this.LengthOfElement[31] = -1;
        this.LengthOfElement[32] = -1;
        this.LengthOfElement[33] = -1;
        this.LengthOfElement[34] = -1;
        this.LengthOfElement[35] = -1;
        this.LengthOfElement[36] = -2;
        this.LengthOfElement[37] = 12;
        this.LengthOfElement[38] = 6;
        this.LengthOfElement[39] = 3;
        this.LengthOfElement[40] = 3;
        this.LengthOfElement[41] = 8;
        this.LengthOfElement[42] = 15;
        this.LengthOfElement[43] = -1;
        this.LengthOfElement[44] = -1;
        this.LengthOfElement[45] = -1;
        this.LengthOfElement[46] = -2;
        this.LengthOfElement[47] = -2;
        this.LengthOfElement[48] = -2;
        this.LengthOfElement[49] = 3;
        this.LengthOfElement[50] = 3;
        this.LengthOfElement[51] = 3;
        this.LengthOfElement[52] = 16;
        this.LengthOfElement[53] = -1;
        this.LengthOfElement[54] = -2;
        this.LengthOfElement[55] = -2;
        this.LengthOfElement[56] = -1;
        this.LengthOfElement[57] = 3;
        this.LengthOfElement[58] = -1;
        this.LengthOfElement[59] = -2;
        this.LengthOfElement[60] = -2;
        this.LengthOfElement[61] = -2;
        this.LengthOfElement[62] = -2;
        this.LengthOfElement[63] = -2;
        this.LengthOfElement[64] = 8;
        this.LengthOfElement[65] = 8;
        this.LengthOfElement[66] = -2;
        this.LengthOfElement[67] = 2;
        this.LengthOfElement[68] = 3;
        this.LengthOfElement[69] = 3;
        this.LengthOfElement[70] = 3;
        this.LengthOfElement[71] = 8;
        this.LengthOfElement[72] = -2;
        this.LengthOfElement[73] = 6;
        this.LengthOfElement[74] = 10;
        this.LengthOfElement[75] = 10;
        this.LengthOfElement[76] = 10;
        this.LengthOfElement[77] = 10;
        this.LengthOfElement[78] = 10;
        this.LengthOfElement[79] = 10;
        this.LengthOfElement[80] = 10;
        this.LengthOfElement[81] = 10;
        this.LengthOfElement[82] = 10;
        this.LengthOfElement[83] = 10;
        this.LengthOfElement[84] = 10;
        this.LengthOfElement[85] = 10;
        this.LengthOfElement[86] = 16;
        this.LengthOfElement[87] = 16;
        this.LengthOfElement[88] = 16;
        this.LengthOfElement[89] = 16;
        this.LengthOfElement[90] = 42;
        this.LengthOfElement[91] = 3;
        this.LengthOfElement[92] = 3;
        this.LengthOfElement[93] = -1;
        this.LengthOfElement[94] = -1;
        this.LengthOfElement[95] = -1;
        this.LengthOfElement[96] = -2;
        this.LengthOfElement[97] = 17;
        this.LengthOfElement[98] = 25;
        this.LengthOfElement[99] = -1;
        this.LengthOfElement[100] = -1;
        this.LengthOfElement[101] = -1;
        this.LengthOfElement[102] = -1;
        this.LengthOfElement[103] = -1;
        this.LengthOfElement[104] = -2;
        this.LengthOfElement[105] = 16;
        this.LengthOfElement[106] = 16;
        this.LengthOfElement[107] = 10;
        this.LengthOfElement[108] = 10;
        this.LengthOfElement[109] = -1;
        this.LengthOfElement[110] = -1;
        this.LengthOfElement[111] = -2;
        this.LengthOfElement[112] = -2;
        this.LengthOfElement[113] = -2;
        this.LengthOfElement[114] = -2;
        this.LengthOfElement[115] = -2;
        this.LengthOfElement[116] = -2;
        this.LengthOfElement[117] = -2;
        this.LengthOfElement[118] = -2;
        this.LengthOfElement[119] = -2;
        this.LengthOfElement[120] = -2;
        this.LengthOfElement[121] = -2;
        this.LengthOfElement[122] = -2;
        this.LengthOfElement[123] = -2;
        this.LengthOfElement[124] = -2;
        this.LengthOfElement[125] = -2;
        this.LengthOfElement[126] = -2;
        this.LengthOfElement[127] = -2;
        this.LengthOfElement[128] = 8;
    }

    private void InitTypeOfElement() {
        this.TypeOfElement[0] = 0;
        this.TypeOfElement[1] = 2;
        this.TypeOfElement[2] = 1;
        this.TypeOfElement[3] = 1;
        this.TypeOfElement[4] = 1;
        this.TypeOfElement[5] = 1;
        this.TypeOfElement[6] = 1;
        this.TypeOfElement[7] = 1;
        this.TypeOfElement[8] = 1;
        this.TypeOfElement[9] = 1;
        this.TypeOfElement[10] = 1;
        this.TypeOfElement[11] = 1;
        this.TypeOfElement[12] = 1;
        this.TypeOfElement[13] = 1;
        this.TypeOfElement[14] = 1;
        this.TypeOfElement[15] = 1;
        this.TypeOfElement[16] = 1;
        this.TypeOfElement[17] = 1;
        this.TypeOfElement[18] = 1;
        this.TypeOfElement[19] = 1;
        this.TypeOfElement[20] = 1;
        this.TypeOfElement[21] = 1;
        this.TypeOfElement[22] = 1;
        this.TypeOfElement[23] = 1;
        this.TypeOfElement[24] = 1;
        this.TypeOfElement[25] = 1;
        this.TypeOfElement[26] = 1;
        this.TypeOfElement[27] = 1;
        this.TypeOfElement[28] = 1;
        this.TypeOfElement[29] = 1;
        this.TypeOfElement[30] = 2;
        this.TypeOfElement[31] = 2;
        this.TypeOfElement[32] = 2;
        this.TypeOfElement[33] = 2;
        this.TypeOfElement[34] = 2;
        this.TypeOfElement[35] = 3;
        this.TypeOfElement[36] = 1;
        this.TypeOfElement[37] = 1;
        this.TypeOfElement[38] = 1;
        this.TypeOfElement[39] = 1;
        this.TypeOfElement[40] = 1;
        this.TypeOfElement[41] = 1;
        this.TypeOfElement[42] = 2;
        this.TypeOfElement[43] = 2;
        this.TypeOfElement[44] = 2;
        this.TypeOfElement[45] = 3;
        this.TypeOfElement[46] = 3;
        this.TypeOfElement[47] = 3;
        this.TypeOfElement[48] = 1;
        this.TypeOfElement[49] = 1;
        this.TypeOfElement[50] = 1;
        this.TypeOfElement[51] = 1;
        this.TypeOfElement[52] = 2;
        this.TypeOfElement[53] = 3;
        this.TypeOfElement[54] = 5;
        this.TypeOfElement[55] = 3;
        this.TypeOfElement[56] = 1;
        this.TypeOfElement[57] = 2;
        this.TypeOfElement[58] = 3;
        this.TypeOfElement[59] = 3;
        this.TypeOfElement[60] = 3;
        this.TypeOfElement[61] = 3;
        this.TypeOfElement[62] = 3;
        this.TypeOfElement[63] = 1;
        this.TypeOfElement[64] = 1;
        this.TypeOfElement[65] = 3;
        this.TypeOfElement[66] = 1;
        this.TypeOfElement[67] = 1;
        this.TypeOfElement[68] = 1;
        this.TypeOfElement[69] = 1;
        this.TypeOfElement[70] = 1;
        this.TypeOfElement[71] = 3;
        this.TypeOfElement[72] = 1;
        this.TypeOfElement[73] = 1;
        this.TypeOfElement[74] = 1;
        this.TypeOfElement[75] = 1;
        this.TypeOfElement[76] = 1;
        this.TypeOfElement[77] = 1;
        this.TypeOfElement[78] = 1;
        this.TypeOfElement[79] = 1;
        this.TypeOfElement[80] = 1;
        this.TypeOfElement[81] = 1;
        this.TypeOfElement[82] = 1;
        this.TypeOfElement[83] = 1;
        this.TypeOfElement[84] = 1;
        this.TypeOfElement[85] = 1;
        this.TypeOfElement[86] = 1;
        this.TypeOfElement[87] = 1;
        this.TypeOfElement[88] = 1;
        this.TypeOfElement[89] = 1;
        this.TypeOfElement[90] = 1;
        this.TypeOfElement[91] = 1;
        this.TypeOfElement[92] = 2;
        this.TypeOfElement[93] = 2;
        this.TypeOfElement[94] = 2;
        this.TypeOfElement[95] = 3;
        this.TypeOfElement[96] = 1;
        this.TypeOfElement[97] = 1;
        this.TypeOfElement[98] = 2;
        this.TypeOfElement[99] = 2;
        this.TypeOfElement[100] = 2;
        this.TypeOfElement[101] = 2;
        this.TypeOfElement[102] = 2;
        this.TypeOfElement[103] = 3;
        this.TypeOfElement[104] = 1;
        this.TypeOfElement[105] = 1;
        this.TypeOfElement[106] = 1;
        this.TypeOfElement[107] = 1;
        this.TypeOfElement[108] = 2;
        this.TypeOfElement[109] = 2;
        this.TypeOfElement[110] = 3;
        this.TypeOfElement[111] = 3;
        this.TypeOfElement[112] = 3;
        this.TypeOfElement[113] = 3;
        this.TypeOfElement[114] = 3;
        this.TypeOfElement[115] = 3;
        this.TypeOfElement[116] = 3;
        this.TypeOfElement[117] = 3;
        this.TypeOfElement[118] = 3;
        this.TypeOfElement[119] = 3;
        this.TypeOfElement[120] = 3;
        this.TypeOfElement[121] = 3;
        this.TypeOfElement[122] = 3;
        this.TypeOfElement[123] = 3;
        this.TypeOfElement[124] = 3;
        this.TypeOfElement[125] = 3;
        this.TypeOfElement[126] = 3;
        this.TypeOfElement[127] = 1;
    }

    public void AddIsoField(StringBuffer Msg, int DataElementIndex, String FieldValue, char[] Bitmap) {
        switch (this.GetFieldType(DataElementIndex)) {
            case 0: {
                this.AddFieldBIN(DataElementIndex + 1, Msg, FieldValue);
                this.CreateBitMap(DataElementIndex, Bitmap);
                break;
            }
            case 1: {
                this.AddFieldAlphaAscii(DataElementIndex + 1, Msg, FieldValue);
                this.CreateBitMap(DataElementIndex, Bitmap);
                break;
            }
            case 2: {
                this.AddFieldLLVARAscii(DataElementIndex + 1, Msg, FieldValue);
                this.CreateBitMap(DataElementIndex, Bitmap);
                break;
            }
            case 3: {
                this.AddFieldLLLVARAscii(DataElementIndex + 1, Msg, FieldValue);
                this.CreateBitMap(DataElementIndex, Bitmap);
                break;
            }
            case 5: {
                this.AddFieldLLLVAR(DataElementIndex + 1, Msg, FieldValue);
                this.CreateBitMap(DataElementIndex, Bitmap);
            }
        }
    }

    public int GetFieldType(int DataElementIndex) {
        return this.TypeOfElement[DataElementIndex];
    }

    public void AddFieldBIN(int DataElementIndex, StringBuffer Msg, String FieldValue) {
        Msg.append(FieldValue.substring(0, this.GetFieldLength(DataElementIndex)));
    }

    public int GetFieldLength(int DataElementIndex) {
        return this.LengthOfElement[DataElementIndex];
    }

    public void CreateBitMap(int DataElementIndex, char[] Bitmap) {
        int Pos2 = DataElementIndex / 8;
        char bit = (char)(128 >> DataElementIndex % 8);
        int n = Pos2;
        Bitmap[n] = (char)(Bitmap[n] | bit);
    }

    public void AddFieldAlphaAscii(int DataElementIndex, StringBuffer Msg, String FieldValue) {
        Msg.append(FieldValue.substring(0, this.GetFieldLength(DataElementIndex)));
    }

    public void AddFieldLLVARAscii(int DataElementIndex, StringBuffer Msg, String FieldValue) {
        Msg.append(String.format("%02d", FieldValue.length()));
        Msg.append(FieldValue.substring(0, FieldValue.length()));
    }

    public void AddFieldLLLVARAscii(int DataElementIndex, StringBuffer Msg, String FieldValue) {
        Msg.append(String.format("%03d", FieldValue.length()));
        Msg.append(FieldValue.substring(0, FieldValue.length()));
    }

    public void AddFieldLLLVAR(int DataElementIndex, StringBuffer Msg, String FieldValue) {
        Msg.append(String.format("%03d", FieldValue.length() / 2));
        this.To55Field(Msg, FieldValue, FieldValue.length());
    }

    public void To55Field(StringBuffer msg, String AsciiData, int Length) {
        int j = 0;
        char[] tempField = new char[512];
        for (int i = 0; i < Length; ++i) {
            j = ++i / 2;
            tempField[j] = i <= Length ? (char)((char)(this.AsciiToHex(AsciiData.charAt(i - 1)) << 4) | this.AsciiToHex(AsciiData.charAt(i))) : (char)(this.AsciiToHex(AsciiData.charAt(i)) & 0xF);
        }
        for (int k = 0; k <= j; ++k) {
            msg.append(tempField[k]);
        }
    }

    public char AsciiToHex(char c) {
        if (c >= '0' && c <= '9') {
            return (char)(c - 48);
        }
        if (c >= 'A' && c <= 'F') {
            return (char)(c - 65 + 10);
        }
        if (c >= 'a' && c <= 'f') {
            return (char)(c - 97 + 10);
        }
        return '\u0000';
    }

    public int AsciiToEbcdic(int val_ascii) {
        int j;
        int i;
        for (i = 0; i <= 15; ++i) {
            IPM.Row[i] = (char)(i * 16);
        }
        for (i = 0; i <= 15; ++i) {
            IPM.Column[i] = (char)i;
        }
        IPM.Tcode[0][0] = '\u0000';
        IPM.Tcode[1][0] = 16;
        IPM.Tcode[2][0] = 64;
        IPM.Tcode[3][0] = 240;
        IPM.Tcode[4][0] = 124;
        IPM.Tcode[5][0] = 215;
        IPM.Tcode[6][0] = 121;
        IPM.Tcode[7][0] = 151;
        IPM.Tcode[8][0] = 32;
        IPM.Tcode[9][0] = 48;
        IPM.Tcode[10][0] = 65;
        IPM.Tcode[11][0] = 88;
        IPM.Tcode[12][0] = 118;
        IPM.Tcode[13][0] = 159;
        IPM.Tcode[14][0] = 184;
        IPM.Tcode[15][0] = 220;
        IPM.Tcode[0][1] = '\u0001';
        IPM.Tcode[1][1] = 17;
        IPM.Tcode[2][1] = 90;
        IPM.Tcode[3][1] = 241;
        IPM.Tcode[4][1] = 193;
        IPM.Tcode[5][1] = 216;
        IPM.Tcode[6][1] = 129;
        IPM.Tcode[7][1] = 152;
        IPM.Tcode[8][1] = 33;
        IPM.Tcode[9][1] = 49;
        IPM.Tcode[10][1] = 66;
        IPM.Tcode[11][1] = 89;
        IPM.Tcode[12][1] = 119;
        IPM.Tcode[13][1] = 160;
        IPM.Tcode[14][1] = 185;
        IPM.Tcode[15][1] = 221;
        IPM.Tcode[0][2] = 2;
        IPM.Tcode[1][2] = 18;
        IPM.Tcode[2][2] = 127;
        IPM.Tcode[3][2] = 242;
        IPM.Tcode[4][2] = 194;
        IPM.Tcode[5][2] = 217;
        IPM.Tcode[6][2] = 130;
        IPM.Tcode[7][2] = 153;
        IPM.Tcode[8][2] = 34;
        IPM.Tcode[9][2] = 26;
        IPM.Tcode[10][2] = 67;
        IPM.Tcode[11][2] = 98;
        IPM.Tcode[12][2] = 120;
        IPM.Tcode[13][2] = 170;
        IPM.Tcode[14][2] = 186;
        IPM.Tcode[15][2] = 222;
        IPM.Tcode[0][3] = 3;
        IPM.Tcode[1][3] = 19;
        IPM.Tcode[2][3] = 123;
        IPM.Tcode[3][3] = 243;
        IPM.Tcode[4][3] = 195;
        IPM.Tcode[5][3] = 226;
        IPM.Tcode[6][3] = 131;
        IPM.Tcode[7][3] = 162;
        IPM.Tcode[8][3] = 35;
        IPM.Tcode[9][3] = 51;
        IPM.Tcode[10][3] = 68;
        IPM.Tcode[11][3] = 99;
        IPM.Tcode[12][3] = 128;
        IPM.Tcode[13][3] = 171;
        IPM.Tcode[14][3] = 187;
        IPM.Tcode[15][3] = 223;
        IPM.Tcode[0][4] = 55;
        IPM.Tcode[1][4] = 60;
        IPM.Tcode[2][4] = 91;
        IPM.Tcode[3][4] = 244;
        IPM.Tcode[4][4] = 196;
        IPM.Tcode[5][4] = 227;
        IPM.Tcode[6][4] = 132;
        IPM.Tcode[7][4] = 163;
        IPM.Tcode[8][4] = 36;
        IPM.Tcode[9][4] = 52;
        IPM.Tcode[10][4] = 69;
        IPM.Tcode[11][4] = 100;
        IPM.Tcode[12][4] = 138;
        IPM.Tcode[13][4] = 172;
        IPM.Tcode[14][4] = 188;
        IPM.Tcode[15][4] = 234;
        IPM.Tcode[0][5] = 45;
        IPM.Tcode[1][5] = 61;
        IPM.Tcode[2][5] = 108;
        IPM.Tcode[3][5] = 245;
        IPM.Tcode[4][5] = 197;
        IPM.Tcode[5][5] = 228;
        IPM.Tcode[6][5] = 133;
        IPM.Tcode[7][5] = 164;
        IPM.Tcode[8][5] = 21;
        IPM.Tcode[9][5] = 53;
        IPM.Tcode[10][5] = 70;
        IPM.Tcode[11][5] = 101;
        IPM.Tcode[12][5] = 139;
        IPM.Tcode[13][5] = 173;
        IPM.Tcode[14][5] = 189;
        IPM.Tcode[15][5] = 235;
        IPM.Tcode[0][6] = 46;
        IPM.Tcode[1][6] = 50;
        IPM.Tcode[2][6] = 80;
        IPM.Tcode[3][6] = 246;
        IPM.Tcode[4][6] = 198;
        IPM.Tcode[5][6] = 229;
        IPM.Tcode[6][6] = 134;
        IPM.Tcode[7][6] = 165;
        IPM.Tcode[8][6] = 6;
        IPM.Tcode[9][6] = 54;
        IPM.Tcode[10][6] = 71;
        IPM.Tcode[11][6] = 102;
        IPM.Tcode[12][6] = 140;
        IPM.Tcode[13][6] = 174;
        IPM.Tcode[14][6] = 190;
        IPM.Tcode[15][6] = 236;
        IPM.Tcode[0][7] = 47;
        IPM.Tcode[1][7] = 38;
        IPM.Tcode[2][7] = 125;
        IPM.Tcode[3][7] = 247;
        IPM.Tcode[4][7] = 199;
        IPM.Tcode[5][7] = 230;
        IPM.Tcode[6][7] = 135;
        IPM.Tcode[7][7] = 166;
        IPM.Tcode[8][7] = 23;
        IPM.Tcode[9][7] = 8;
        IPM.Tcode[10][7] = 72;
        IPM.Tcode[11][7] = 103;
        IPM.Tcode[12][7] = 141;
        IPM.Tcode[13][7] = 175;
        IPM.Tcode[14][7] = 191;
        IPM.Tcode[15][7] = 237;
        IPM.Tcode[0][8] = 22;
        IPM.Tcode[1][8] = 24;
        IPM.Tcode[2][8] = 77;
        IPM.Tcode[3][8] = 248;
        IPM.Tcode[4][8] = 200;
        IPM.Tcode[5][8] = 231;
        IPM.Tcode[6][8] = 136;
        IPM.Tcode[7][8] = 167;
        IPM.Tcode[8][8] = 40;
        IPM.Tcode[9][8] = 56;
        IPM.Tcode[10][8] = 73;
        IPM.Tcode[11][8] = 104;
        IPM.Tcode[12][8] = 142;
        IPM.Tcode[13][8] = 176;
        IPM.Tcode[14][8] = 202;
        IPM.Tcode[15][8] = 238;
        IPM.Tcode[0][9] = 5;
        IPM.Tcode[1][9] = 25;
        IPM.Tcode[2][9] = 93;
        IPM.Tcode[3][9] = 249;
        IPM.Tcode[4][9] = 201;
        IPM.Tcode[5][9] = 232;
        IPM.Tcode[6][9] = 137;
        IPM.Tcode[7][9] = 168;
        IPM.Tcode[8][9] = 41;
        IPM.Tcode[9][9] = 57;
        IPM.Tcode[10][9] = 81;
        IPM.Tcode[11][9] = 105;
        IPM.Tcode[12][9] = 143;
        IPM.Tcode[13][9] = 177;
        IPM.Tcode[14][9] = 203;
        IPM.Tcode[15][9] = 239;
        IPM.Tcode[0][10] = 37;
        IPM.Tcode[1][10] = 63;
        IPM.Tcode[2][10] = 92;
        IPM.Tcode[3][10] = 122;
        IPM.Tcode[4][10] = 209;
        IPM.Tcode[5][10] = 233;
        IPM.Tcode[6][10] = 145;
        IPM.Tcode[7][10] = 169;
        IPM.Tcode[8][10] = 42;
        IPM.Tcode[9][10] = 58;
        IPM.Tcode[10][10] = 82;
        IPM.Tcode[11][10] = 112;
        IPM.Tcode[12][10] = 144;
        IPM.Tcode[13][10] = 178;
        IPM.Tcode[14][10] = 204;
        IPM.Tcode[15][10] = 250;
        IPM.Tcode[0][11] = 11;
        IPM.Tcode[1][11] = 39;
        IPM.Tcode[2][11] = 78;
        IPM.Tcode[3][11] = 94;
        IPM.Tcode[4][11] = 210;
        IPM.Tcode[5][11] = 74;
        IPM.Tcode[6][11] = 146;
        IPM.Tcode[7][11] = 192;
        IPM.Tcode[8][11] = 43;
        IPM.Tcode[9][11] = 59;
        IPM.Tcode[10][11] = 83;
        IPM.Tcode[11][11] = 113;
        IPM.Tcode[12][11] = 154;
        IPM.Tcode[13][11] = 179;
        IPM.Tcode[14][11] = 205;
        IPM.Tcode[15][11] = 251;
        IPM.Tcode[0][12] = 12;
        IPM.Tcode[1][12] = 28;
        IPM.Tcode[2][12] = 107;
        IPM.Tcode[3][12] = 76;
        IPM.Tcode[4][12] = 211;
        IPM.Tcode[5][12] = 224;
        IPM.Tcode[6][12] = 147;
        IPM.Tcode[7][12] = 106;
        IPM.Tcode[8][12] = 44;
        IPM.Tcode[9][12] = 4;
        IPM.Tcode[10][12] = 84;
        IPM.Tcode[11][12] = 114;
        IPM.Tcode[12][12] = 155;
        IPM.Tcode[13][12] = 180;
        IPM.Tcode[14][12] = 206;
        IPM.Tcode[15][12] = 252;
        IPM.Tcode[0][13] = 13;
        IPM.Tcode[1][13] = 29;
        IPM.Tcode[2][13] = 96;
        IPM.Tcode[3][13] = 126;
        IPM.Tcode[4][13] = 212;
        IPM.Tcode[5][13] = 79;
        IPM.Tcode[6][13] = 148;
        IPM.Tcode[7][13] = 208;
        IPM.Tcode[8][13] = 9;
        IPM.Tcode[9][13] = 20;
        IPM.Tcode[10][13] = 85;
        IPM.Tcode[11][13] = 115;
        IPM.Tcode[12][13] = 156;
        IPM.Tcode[13][13] = 181;
        IPM.Tcode[14][13] = 207;
        IPM.Tcode[15][13] = 253;
        IPM.Tcode[0][14] = 14;
        IPM.Tcode[1][14] = 30;
        IPM.Tcode[2][14] = 75;
        IPM.Tcode[3][14] = 110;
        IPM.Tcode[4][14] = 213;
        IPM.Tcode[5][14] = 95;
        IPM.Tcode[6][14] = 149;
        IPM.Tcode[7][14] = 161;
        IPM.Tcode[8][14] = 10;
        IPM.Tcode[9][14] = 62;
        IPM.Tcode[10][14] = 86;
        IPM.Tcode[11][14] = 116;
        IPM.Tcode[12][14] = 157;
        IPM.Tcode[13][14] = 182;
        IPM.Tcode[14][14] = 218;
        IPM.Tcode[15][14] = 254;
        IPM.Tcode[0][15] = 15;
        IPM.Tcode[1][15] = 31;
        IPM.Tcode[2][15] = 97;
        IPM.Tcode[3][15] = 111;
        IPM.Tcode[4][15] = 214;
        IPM.Tcode[5][15] = 109;
        IPM.Tcode[6][15] = 150;
        IPM.Tcode[7][15] = 7;
        IPM.Tcode[8][15] = 27;
        IPM.Tcode[9][15] = 225;
        IPM.Tcode[10][15] = 87;
        IPM.Tcode[11][15] = 117;
        IPM.Tcode[12][15] = 158;
        IPM.Tcode[13][15] = 183;
        IPM.Tcode[14][15] = 219;
        IPM.Tcode[15][15] = 255;
        char c = (char)(val_ascii & 0xF0);
        char c1 = (char)(val_ascii & 0xF);
        for (i = 0; i < 16 && Row[i] != c; ++i) {
        }
        for (j = 0; j < 16 && Column[j] != c1; ++j) {
        }
        return Tcode[i][j];
    }

    public void SetSecondaryBitMap(char[] map) {
        char sbit = '\u0000';
        for (int i = 8; i < 16; ++i) {
            sbit = (char)(sbit | map[i]);
        }
        if (sbit != '\u0000') {
            map[0] = (char)(map[0] | 0x80);
        }
    }

    public void ProcessIsoMessageAscii(String IsoMsg) {
        int i;
        char[] length = new char[3];
        int StartPoint = 0;
        StartPoint += 4;
        int k = 0;
        this.ElementPossition[0] = 8;
        int bitmapLen = 8;
        if ((0x80 & IsoMsg.charAt(4)) > 0) {
            bitmapLen = 16;
        }
        for (int j = 0; j < bitmapLen; ++j) {
            int bit = 128;
            char Bit_map = IsoMsg.charAt(StartPoint + j);
            for (i = 0; i < 8; ++i) {
                k = j * 8 + i + 1;
                this.ElementPossition[k] = this.ElementPossition[k - 1];
                if ((Bit_map & bit) > 0) {
                    switch (this.LengthOfElement[k]) {
                        case -2: {
                            length[0] = IsoMsg.charAt(StartPoint + this.ElementPossition[k]);
                            length[1] = IsoMsg.charAt(StartPoint + this.ElementPossition[k] + 1);
                            length[2] = IsoMsg.charAt(StartPoint + this.ElementPossition[k] + 2);
                            String Slength = new String(length);
                            int n = k;
                            this.ElementPossition[n] = this.ElementPossition[n] + (Integer.parseInt(Slength) + 3);
                            break;
                        }
                        case -1: {
                            length[0] = IsoMsg.charAt(StartPoint + this.ElementPossition[k]);
                            length[1] = IsoMsg.charAt(StartPoint + this.ElementPossition[k] + 1);
                            String Slength = new String(length);
                            int n = k;
                            this.ElementPossition[n] = this.ElementPossition[n] + (Integer.parseInt(Slength.substring(0, 2)) + 2);
                            break;
                        }
                        default: {
                            int n = k;
                            this.ElementPossition[n] = this.ElementPossition[n] + this.LengthOfElement[k];
                        }
                    }
                }
                int biti = bit >> 1 & 0x7F;
                bit = (char)biti;
            }
        }
        for (i = k + 1; i < 129; ++i) {
            this.ElementPossition[i] = this.ElementPossition[i - 1];
        }
    }

    public String GetIsoField(String IsoMsg, int DataElementIndex, String sDataFormat) {
        String msg = IsoMsg.substring(4);
        int index = DataElementIndex;
        String FieldValue = null;
        if (this.IsFieldPresent(DataElementIndex) == 0) {
            ++DataElementIndex;
            switch (this.GetFieldType(index)) {
                case 0: {
                    FieldValue = this.GetFieldBIN(index + 1, msg);
                    break;
                }
                case 1: {
                    FieldValue = this.GetFieldAlphaAscii(index + 1, msg);
                    break;
                }
                case 2: {
                    FieldValue = this.GetFieldLLVARAscii(index + 1, msg);
                    break;
                }
                case 3: {
                    FieldValue = this.GetFieldLLLVARAscii(index + 1, msg);
                    break;
                }
                case 5: {
                    FieldValue = this.GetFieldLLLVAR(index + 1, msg, sDataFormat);
                }
            }
            return FieldValue;
        }
        return null;
    }

    private String GetFieldLLLVAR(int DataElementIndex, String IsoMsg, String sDataFormat) {
        int start = this.ElementPossition[DataElementIndex - 1];
        String DataLength = IsoMsg.substring(start, start + 3);
        String Data = this.FieldTo55(IsoMsg.substring(this.ElementPossition[DataElementIndex - 1] + 3), Integer.parseInt(DataLength), sDataFormat);
        return Data;
    }

    public String FieldTo55(String EbcdicData, int Length, String sDataFormat) {
        Object AsciiData = "";
        for (int i = 0; i < Length; ++i) {
            int j = i * 2;
            char c = sDataFormat == "EBCIDIC" ? (char)this.AsciiToEbcdic((int)EbcdicData.charAt(i)) : EbcdicData.charAt(i);
            char c1 = (char)(c >> 4 & 0xF);
            AsciiData = (String)AsciiData + this.ConvTo55(c1);
            char c2 = (char)(c & 0xF);
            AsciiData = (String)AsciiData + this.ConvTo55(c2);
        }
        return AsciiData;
    }

    public char ConvTo55(char c) {
        if (c <= '\t') {
            c = (char)(c + 48);
        } else if (c == '\n') {
            c = (char)65;
        } else if (c == '\u000b') {
            c = (char)66;
        } else if (c == '\f') {
            c = (char)67;
        } else if (c == '\r') {
            c = (char)68;
        } else if (c == '\u000e') {
            c = (char)69;
        } else if (c == '\u000f') {
            c = (char)70;
        }
        return c;
    }

    private String GetFieldBIN(int DataElementIndex, String IsoMsg) {
        int start = this.ElementPossition[DataElementIndex - 1];
        return IsoMsg.substring(start, start + this.GetFieldLength(DataElementIndex));
    }

    public int IsFieldPresent(int DataElementIndex) {
        if (this.ElementPossition[DataElementIndex + 1] - this.ElementPossition[DataElementIndex] <= 0) {
            return -1;
        }
        return 0;
    }

    private String GetFieldAlphaAscii(int DataElementIndex, String IsoMsg) {
        int start = this.ElementPossition[DataElementIndex - 1];
        String Data = IsoMsg.substring(start, start + this.GetFieldLength(DataElementIndex));
        return Data;
    }

    private String GetFieldLLVARAscii(int DataElementIndex, String IsoMsg) {
        int start = this.ElementPossition[DataElementIndex - 1];
        String DataLength = IsoMsg.substring(start, start + 2);
        String Data = IsoMsg.substring(start + 2, start + 2 + Integer.parseInt(DataLength));
        return Data;
    }

    private String GetFieldLLLVARAscii(int DataElementIndex, String IsoMsg) {
        int start = this.ElementPossition[DataElementIndex - 1];
        String DataLength = IsoMsg.substring(start, start + 3);
        String Data = IsoMsg.substring(start + 3, start + 3 + Integer.parseInt(DataLength));
        return Data;
    }
}

