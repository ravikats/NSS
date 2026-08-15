/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.IpmOutWorkEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="IPM_OUT_WORK")
public class IpmOutWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IOW_SER_NUMBER")
    private int serialNumber;
    @Column(name="IOW_IN_CODE")
    private int insCode;
    @Column(name="IOW_FILE_ID")
    private String fileId;
    @Column(name="IOW_REF_SER_NUMBER")
    private int refNumber;
    @Column(name="IOW_DE001")
    private String DE001;
    @Column(name="IOW_DE002")
    private String DE002;
    @Column(name="IOW_DE003")
    private String DE003;
    @Column(name="IOW_DE004")
    private String DE004;
    @Column(name="IOW_DE012")
    private String DE012;
    @Column(name="IOW_DE014")
    private String DE014;
    @Column(name="IOW_DE022")
    private String DE022;
    @Column(name="IOW_DE023")
    private String DE023;
    @Column(name="IOW_DE024")
    private String DE024;
    @Column(name="IOW_DE025")
    private String DE025;
    @Column(name="IOW_DE026")
    private String DE026;
    @Column(name="IOW_DE030")
    private String DE030;
    @Column(name="IOW_DE031")
    private String DE031;
    @Column(name="IOW_DE032")
    private String DE032;
    @Column(name="IOW_DE033")
    private String DE033;
    @Column(name="IOW_DE037")
    private String DE037;
    @Column(name="IOW_DE038")
    private String DE038;
    @Column(name="IOW_DE040")
    private String DE040;
    @Column(name="IOW_DE041")
    private String DE041;
    @Column(name="IOW_DE042")
    private String DE042;
    @Column(name="IOW_DE043")
    private String DE043;
    @Column(name="IOW_DE049")
    private String DE049;
    @Column(name="IOW_DE054")
    private String DE054;
    @Column(name="IOW_DE063")
    private String DE063;
    @Column(name="IOW_DE071")
    private String DE071;
    @Column(name="IOW_DE072")
    private String DE072;
    @Column(name="IOW_DE093")
    private String DE093;
    @Column(name="IOW_DE094")
    private String DE094;
    @Column(name="IOW_DE095")
    private String DE095;
    @Column(name="IOW_PDS23")
    private String PDS23;
    @Column(name="IOW_PDS25")
    private String PDS25;
    @Column(name="IOW_PDS52")
    private String PDS52;
    @Column(name="IOW_PDS137")
    private String PDS137;
    @Column(name="IOW_PDS148")
    private String PDS148;
    @Column(name="IOW_PDS149")
    private String PDS149;
    @Column(name="IOW_PDS155")
    private String PDS155;
    @Column(name="IOW_PDS165")
    private String PDS165;
    @Column(name="IOW_PDS176")
    private String PDS176;
    @Column(name="IOW_PDS211")
    private String PDS211;
    @Column(name="IOW_PDS262")
    private String PDS262;
    @Column(name="IOW_DE055_9F26")
    private String DE055_9F26;
    @Column(name="IOW_DE055_9F27")
    private String DE055_9F27;
    @Column(name="IOW_DE055_9F10")
    private String DE055_9F10;
    @Column(name="IOW_DE055_9F37")
    private String DE055_9F37;
    @Column(name="IOW_DE055_9F36")
    private String DE055_9F36;
    @Column(name="IOW_DE055_95")
    private String DE055_95;
    @Column(name="IOW_DE055_9A")
    private String DE055_9A;
    @Column(name="IOW_DE055_9C")
    private String DE055_9C;
    @Column(name="IOW_DE055_9F02")
    private String DE055_9F02;
    @Column(name="IOW_DE055_5F2A")
    private String DE055_5F2A;
    @Column(name="IOW_DE055_82")
    private String DE055_82;
    @Column(name="IOW_DE055_9F1A")
    private String DE055_9F1A;
    @Column(name="IOW_DE055_9F03")
    private String DE055_9F03;
    @Column(name="IOW_DE048_PDS0213")
    private String DE048_PDS0213;
    @Column(name="IOW_DE055_84")
    private String DE055_84;
    @Column(name="IOW_DE055_9F33")
    private String DE055_9F33;
    @Column(name="IOW_DE055_9F34")
    private String DE055_9F34;
    @Column(name="IOW_DE048_PDS0170")
    private String DE048_PDS0170;
    @Column(name="IOW_PDS0018")
    private String PDS0018;
    @Column(name="IOW_DE048_PDS0175")
    private String DE048_PDS0175;

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public int getInsCode() {
        return this.insCode;
    }

    public String getFileId() {
        return this.fileId;
    }

    public int getRefNumber() {
        return this.refNumber;
    }

    public String getDE001() {
        return this.DE001;
    }

    public String getDE002() {
        return this.DE002;
    }

    public String getDE003() {
        return this.DE003;
    }

    public String getDE004() {
        return this.DE004;
    }

    public String getDE012() {
        return this.DE012;
    }

    public String getDE014() {
        return this.DE014;
    }

    public String getDE022() {
        return this.DE022;
    }

    public String getDE023() {
        return this.DE023;
    }

    public String getDE024() {
        return this.DE024;
    }

    public String getDE025() {
        return this.DE025;
    }

    public String getDE026() {
        return this.DE026;
    }

    public String getDE030() {
        return this.DE030;
    }

    public String getDE031() {
        return this.DE031;
    }

    public String getDE032() {
        return this.DE032;
    }

    public String getDE033() {
        return this.DE033;
    }

    public String getDE037() {
        return this.DE037;
    }

    public String getDE038() {
        return this.DE038;
    }

    public String getDE040() {
        return this.DE040;
    }

    public String getDE041() {
        return this.DE041;
    }

    public String getDE042() {
        return this.DE042;
    }

    public String getDE043() {
        return this.DE043;
    }

    public String getDE049() {
        return this.DE049;
    }

    public String getDE054() {
        return this.DE054;
    }

    public String getDE063() {
        return this.DE063;
    }

    public String getDE071() {
        return this.DE071;
    }

    public String getDE072() {
        return this.DE072;
    }

    public String getDE093() {
        return this.DE093;
    }

    public String getDE094() {
        return this.DE094;
    }

    public String getDE095() {
        return this.DE095;
    }

    public String getPDS23() {
        return this.PDS23;
    }

    public String getPDS25() {
        return this.PDS25;
    }

    public String getPDS52() {
        return this.PDS52;
    }

    public String getPDS137() {
        return this.PDS137;
    }

    public String getPDS148() {
        return this.PDS148;
    }

    public String getPDS149() {
        return this.PDS149;
    }

    public String getPDS155() {
        return this.PDS155;
    }

    public String getPDS165() {
        return this.PDS165;
    }

    public String getPDS176() {
        return this.PDS176;
    }

    public String getPDS211() {
        return this.PDS211;
    }

    public String getPDS262() {
        return this.PDS262;
    }

    public String getDE055_9F26() {
        return this.DE055_9F26;
    }

    public String getDE055_9F27() {
        return this.DE055_9F27;
    }

    public String getDE055_9F10() {
        return this.DE055_9F10;
    }

    public String getDE055_9F37() {
        return this.DE055_9F37;
    }

    public String getDE055_9F36() {
        return this.DE055_9F36;
    }

    public String getDE055_95() {
        return this.DE055_95;
    }

    public String getDE055_9A() {
        return this.DE055_9A;
    }

    public String getDE055_9C() {
        return this.DE055_9C;
    }

    public String getDE055_9F02() {
        return this.DE055_9F02;
    }

    public String getDE055_5F2A() {
        return this.DE055_5F2A;
    }

    public String getDE055_82() {
        return this.DE055_82;
    }

    public String getDE055_9F1A() {
        return this.DE055_9F1A;
    }

    public String getDE055_9F03() {
        return this.DE055_9F03;
    }

    public String getDE048_PDS0213() {
        return this.DE048_PDS0213;
    }

    public String getDE055_84() {
        return this.DE055_84;
    }

    public String getDE055_9F33() {
        return this.DE055_9F33;
    }

    public String getDE055_9F34() {
        return this.DE055_9F34;
    }

    public String getDE048_PDS0170() {
        return this.DE048_PDS0170;
    }

    public String getPDS0018() {
        return this.PDS0018;
    }

    public String getDE048_PDS0175() {
        return this.DE048_PDS0175;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setInsCode(int insCode) {
        this.insCode = insCode;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setRefNumber(int refNumber) {
        this.refNumber = refNumber;
    }

    public void setDE001(String DE001) {
        this.DE001 = DE001;
    }

    public void setDE002(String DE002) {
        this.DE002 = DE002;
    }

    public void setDE003(String DE003) {
        this.DE003 = DE003;
    }

    public void setDE004(String DE004) {
        this.DE004 = DE004;
    }

    public void setDE012(String DE012) {
        this.DE012 = DE012;
    }

    public void setDE014(String DE014) {
        this.DE014 = DE014;
    }

    public void setDE022(String DE022) {
        this.DE022 = DE022;
    }

    public void setDE023(String DE023) {
        this.DE023 = DE023;
    }

    public void setDE024(String DE024) {
        this.DE024 = DE024;
    }

    public void setDE025(String DE025) {
        this.DE025 = DE025;
    }

    public void setDE026(String DE026) {
        this.DE026 = DE026;
    }

    public void setDE030(String DE030) {
        this.DE030 = DE030;
    }

    public void setDE031(String DE031) {
        this.DE031 = DE031;
    }

    public void setDE032(String DE032) {
        this.DE032 = DE032;
    }

    public void setDE033(String DE033) {
        this.DE033 = DE033;
    }

    public void setDE037(String DE037) {
        this.DE037 = DE037;
    }

    public void setDE038(String DE038) {
        this.DE038 = DE038;
    }

    public void setDE040(String DE040) {
        this.DE040 = DE040;
    }

    public void setDE041(String DE041) {
        this.DE041 = DE041;
    }

    public void setDE042(String DE042) {
        this.DE042 = DE042;
    }

    public void setDE043(String DE043) {
        this.DE043 = DE043;
    }

    public void setDE049(String DE049) {
        this.DE049 = DE049;
    }

    public void setDE054(String DE054) {
        this.DE054 = DE054;
    }

    public void setDE063(String DE063) {
        this.DE063 = DE063;
    }

    public void setDE071(String DE071) {
        this.DE071 = DE071;
    }

    public void setDE072(String DE072) {
        this.DE072 = DE072;
    }

    public void setDE093(String DE093) {
        this.DE093 = DE093;
    }

    public void setDE094(String DE094) {
        this.DE094 = DE094;
    }

    public void setDE095(String DE095) {
        this.DE095 = DE095;
    }

    public void setPDS23(String PDS23) {
        this.PDS23 = PDS23;
    }

    public void setPDS25(String PDS25) {
        this.PDS25 = PDS25;
    }

    public void setPDS52(String PDS52) {
        this.PDS52 = PDS52;
    }

    public void setPDS137(String PDS137) {
        this.PDS137 = PDS137;
    }

    public void setPDS148(String PDS148) {
        this.PDS148 = PDS148;
    }

    public void setPDS149(String PDS149) {
        this.PDS149 = PDS149;
    }

    public void setPDS155(String PDS155) {
        this.PDS155 = PDS155;
    }

    public void setPDS165(String PDS165) {
        this.PDS165 = PDS165;
    }

    public void setPDS176(String PDS176) {
        this.PDS176 = PDS176;
    }

    public void setPDS211(String PDS211) {
        this.PDS211 = PDS211;
    }

    public void setPDS262(String PDS262) {
        this.PDS262 = PDS262;
    }

    public void setDE055_9F26(String DE055_9F26) {
        this.DE055_9F26 = DE055_9F26;
    }

    public void setDE055_9F27(String DE055_9F27) {
        this.DE055_9F27 = DE055_9F27;
    }

    public void setDE055_9F10(String DE055_9F10) {
        this.DE055_9F10 = DE055_9F10;
    }

    public void setDE055_9F37(String DE055_9F37) {
        this.DE055_9F37 = DE055_9F37;
    }

    public void setDE055_9F36(String DE055_9F36) {
        this.DE055_9F36 = DE055_9F36;
    }

    public void setDE055_95(String DE055_95) {
        this.DE055_95 = DE055_95;
    }

    public void setDE055_9A(String DE055_9A) {
        this.DE055_9A = DE055_9A;
    }

    public void setDE055_9C(String DE055_9C) {
        this.DE055_9C = DE055_9C;
    }

    public void setDE055_9F02(String DE055_9F02) {
        this.DE055_9F02 = DE055_9F02;
    }

    public void setDE055_5F2A(String DE055_5F2A) {
        this.DE055_5F2A = DE055_5F2A;
    }

    public void setDE055_82(String DE055_82) {
        this.DE055_82 = DE055_82;
    }

    public void setDE055_9F1A(String DE055_9F1A) {
        this.DE055_9F1A = DE055_9F1A;
    }

    public void setDE055_9F03(String DE055_9F03) {
        this.DE055_9F03 = DE055_9F03;
    }

    public void setDE048_PDS0213(String DE048_PDS0213) {
        this.DE048_PDS0213 = DE048_PDS0213;
    }

    public void setDE055_84(String DE055_84) {
        this.DE055_84 = DE055_84;
    }

    public void setDE055_9F33(String DE055_9F33) {
        this.DE055_9F33 = DE055_9F33;
    }

    public void setDE055_9F34(String DE055_9F34) {
        this.DE055_9F34 = DE055_9F34;
    }

    public void setDE048_PDS0170(String DE048_PDS0170) {
        this.DE048_PDS0170 = DE048_PDS0170;
    }

    public void setPDS0018(String PDS0018) {
        this.PDS0018 = PDS0018;
    }

    public void setDE048_PDS0175(String DE048_PDS0175) {
        this.DE048_PDS0175 = DE048_PDS0175;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmOutWorkEntity)) {
            return false;
        }
        IpmOutWorkEntity other = (IpmOutWorkEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSerialNumber() != other.getSerialNumber()) {
            return false;
        }
        if (this.getInsCode() != other.getInsCode()) {
            return false;
        }
        if (this.getRefNumber() != other.getRefNumber()) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$DE001 = this.getDE001();
        String other$DE001 = other.getDE001();
        if (this$DE001 == null ? other$DE001 != null : !this$DE001.equals(other$DE001)) {
            return false;
        }
        String this$DE002 = this.getDE002();
        String other$DE002 = other.getDE002();
        if (this$DE002 == null ? other$DE002 != null : !this$DE002.equals(other$DE002)) {
            return false;
        }
        String this$DE003 = this.getDE003();
        String other$DE003 = other.getDE003();
        if (this$DE003 == null ? other$DE003 != null : !this$DE003.equals(other$DE003)) {
            return false;
        }
        String this$DE004 = this.getDE004();
        String other$DE004 = other.getDE004();
        if (this$DE004 == null ? other$DE004 != null : !this$DE004.equals(other$DE004)) {
            return false;
        }
        String this$DE012 = this.getDE012();
        String other$DE012 = other.getDE012();
        if (this$DE012 == null ? other$DE012 != null : !this$DE012.equals(other$DE012)) {
            return false;
        }
        String this$DE014 = this.getDE014();
        String other$DE014 = other.getDE014();
        if (this$DE014 == null ? other$DE014 != null : !this$DE014.equals(other$DE014)) {
            return false;
        }
        String this$DE022 = this.getDE022();
        String other$DE022 = other.getDE022();
        if (this$DE022 == null ? other$DE022 != null : !this$DE022.equals(other$DE022)) {
            return false;
        }
        String this$DE023 = this.getDE023();
        String other$DE023 = other.getDE023();
        if (this$DE023 == null ? other$DE023 != null : !this$DE023.equals(other$DE023)) {
            return false;
        }
        String this$DE024 = this.getDE024();
        String other$DE024 = other.getDE024();
        if (this$DE024 == null ? other$DE024 != null : !this$DE024.equals(other$DE024)) {
            return false;
        }
        String this$DE025 = this.getDE025();
        String other$DE025 = other.getDE025();
        if (this$DE025 == null ? other$DE025 != null : !this$DE025.equals(other$DE025)) {
            return false;
        }
        String this$DE026 = this.getDE026();
        String other$DE026 = other.getDE026();
        if (this$DE026 == null ? other$DE026 != null : !this$DE026.equals(other$DE026)) {
            return false;
        }
        String this$DE030 = this.getDE030();
        String other$DE030 = other.getDE030();
        if (this$DE030 == null ? other$DE030 != null : !this$DE030.equals(other$DE030)) {
            return false;
        }
        String this$DE031 = this.getDE031();
        String other$DE031 = other.getDE031();
        if (this$DE031 == null ? other$DE031 != null : !this$DE031.equals(other$DE031)) {
            return false;
        }
        String this$DE032 = this.getDE032();
        String other$DE032 = other.getDE032();
        if (this$DE032 == null ? other$DE032 != null : !this$DE032.equals(other$DE032)) {
            return false;
        }
        String this$DE033 = this.getDE033();
        String other$DE033 = other.getDE033();
        if (this$DE033 == null ? other$DE033 != null : !this$DE033.equals(other$DE033)) {
            return false;
        }
        String this$DE037 = this.getDE037();
        String other$DE037 = other.getDE037();
        if (this$DE037 == null ? other$DE037 != null : !this$DE037.equals(other$DE037)) {
            return false;
        }
        String this$DE038 = this.getDE038();
        String other$DE038 = other.getDE038();
        if (this$DE038 == null ? other$DE038 != null : !this$DE038.equals(other$DE038)) {
            return false;
        }
        String this$DE040 = this.getDE040();
        String other$DE040 = other.getDE040();
        if (this$DE040 == null ? other$DE040 != null : !this$DE040.equals(other$DE040)) {
            return false;
        }
        String this$DE041 = this.getDE041();
        String other$DE041 = other.getDE041();
        if (this$DE041 == null ? other$DE041 != null : !this$DE041.equals(other$DE041)) {
            return false;
        }
        String this$DE042 = this.getDE042();
        String other$DE042 = other.getDE042();
        if (this$DE042 == null ? other$DE042 != null : !this$DE042.equals(other$DE042)) {
            return false;
        }
        String this$DE043 = this.getDE043();
        String other$DE043 = other.getDE043();
        if (this$DE043 == null ? other$DE043 != null : !this$DE043.equals(other$DE043)) {
            return false;
        }
        String this$DE049 = this.getDE049();
        String other$DE049 = other.getDE049();
        if (this$DE049 == null ? other$DE049 != null : !this$DE049.equals(other$DE049)) {
            return false;
        }
        String this$DE054 = this.getDE054();
        String other$DE054 = other.getDE054();
        if (this$DE054 == null ? other$DE054 != null : !this$DE054.equals(other$DE054)) {
            return false;
        }
        String this$DE063 = this.getDE063();
        String other$DE063 = other.getDE063();
        if (this$DE063 == null ? other$DE063 != null : !this$DE063.equals(other$DE063)) {
            return false;
        }
        String this$DE071 = this.getDE071();
        String other$DE071 = other.getDE071();
        if (this$DE071 == null ? other$DE071 != null : !this$DE071.equals(other$DE071)) {
            return false;
        }
        String this$DE072 = this.getDE072();
        String other$DE072 = other.getDE072();
        if (this$DE072 == null ? other$DE072 != null : !this$DE072.equals(other$DE072)) {
            return false;
        }
        String this$DE093 = this.getDE093();
        String other$DE093 = other.getDE093();
        if (this$DE093 == null ? other$DE093 != null : !this$DE093.equals(other$DE093)) {
            return false;
        }
        String this$DE094 = this.getDE094();
        String other$DE094 = other.getDE094();
        if (this$DE094 == null ? other$DE094 != null : !this$DE094.equals(other$DE094)) {
            return false;
        }
        String this$DE095 = this.getDE095();
        String other$DE095 = other.getDE095();
        if (this$DE095 == null ? other$DE095 != null : !this$DE095.equals(other$DE095)) {
            return false;
        }
        String this$PDS23 = this.getPDS23();
        String other$PDS23 = other.getPDS23();
        if (this$PDS23 == null ? other$PDS23 != null : !this$PDS23.equals(other$PDS23)) {
            return false;
        }
        String this$PDS25 = this.getPDS25();
        String other$PDS25 = other.getPDS25();
        if (this$PDS25 == null ? other$PDS25 != null : !this$PDS25.equals(other$PDS25)) {
            return false;
        }
        String this$PDS52 = this.getPDS52();
        String other$PDS52 = other.getPDS52();
        if (this$PDS52 == null ? other$PDS52 != null : !this$PDS52.equals(other$PDS52)) {
            return false;
        }
        String this$PDS137 = this.getPDS137();
        String other$PDS137 = other.getPDS137();
        if (this$PDS137 == null ? other$PDS137 != null : !this$PDS137.equals(other$PDS137)) {
            return false;
        }
        String this$PDS148 = this.getPDS148();
        String other$PDS148 = other.getPDS148();
        if (this$PDS148 == null ? other$PDS148 != null : !this$PDS148.equals(other$PDS148)) {
            return false;
        }
        String this$PDS149 = this.getPDS149();
        String other$PDS149 = other.getPDS149();
        if (this$PDS149 == null ? other$PDS149 != null : !this$PDS149.equals(other$PDS149)) {
            return false;
        }
        String this$PDS155 = this.getPDS155();
        String other$PDS155 = other.getPDS155();
        if (this$PDS155 == null ? other$PDS155 != null : !this$PDS155.equals(other$PDS155)) {
            return false;
        }
        String this$PDS165 = this.getPDS165();
        String other$PDS165 = other.getPDS165();
        if (this$PDS165 == null ? other$PDS165 != null : !this$PDS165.equals(other$PDS165)) {
            return false;
        }
        String this$PDS176 = this.getPDS176();
        String other$PDS176 = other.getPDS176();
        if (this$PDS176 == null ? other$PDS176 != null : !this$PDS176.equals(other$PDS176)) {
            return false;
        }
        String this$PDS211 = this.getPDS211();
        String other$PDS211 = other.getPDS211();
        if (this$PDS211 == null ? other$PDS211 != null : !this$PDS211.equals(other$PDS211)) {
            return false;
        }
        String this$PDS262 = this.getPDS262();
        String other$PDS262 = other.getPDS262();
        if (this$PDS262 == null ? other$PDS262 != null : !this$PDS262.equals(other$PDS262)) {
            return false;
        }
        String this$DE055_9F26 = this.getDE055_9F26();
        String other$DE055_9F26 = other.getDE055_9F26();
        if (this$DE055_9F26 == null ? other$DE055_9F26 != null : !this$DE055_9F26.equals(other$DE055_9F26)) {
            return false;
        }
        String this$DE055_9F27 = this.getDE055_9F27();
        String other$DE055_9F27 = other.getDE055_9F27();
        if (this$DE055_9F27 == null ? other$DE055_9F27 != null : !this$DE055_9F27.equals(other$DE055_9F27)) {
            return false;
        }
        String this$DE055_9F10 = this.getDE055_9F10();
        String other$DE055_9F10 = other.getDE055_9F10();
        if (this$DE055_9F10 == null ? other$DE055_9F10 != null : !this$DE055_9F10.equals(other$DE055_9F10)) {
            return false;
        }
        String this$DE055_9F37 = this.getDE055_9F37();
        String other$DE055_9F37 = other.getDE055_9F37();
        if (this$DE055_9F37 == null ? other$DE055_9F37 != null : !this$DE055_9F37.equals(other$DE055_9F37)) {
            return false;
        }
        String this$DE055_9F36 = this.getDE055_9F36();
        String other$DE055_9F36 = other.getDE055_9F36();
        if (this$DE055_9F36 == null ? other$DE055_9F36 != null : !this$DE055_9F36.equals(other$DE055_9F36)) {
            return false;
        }
        String this$DE055_95 = this.getDE055_95();
        String other$DE055_95 = other.getDE055_95();
        if (this$DE055_95 == null ? other$DE055_95 != null : !this$DE055_95.equals(other$DE055_95)) {
            return false;
        }
        String this$DE055_9A = this.getDE055_9A();
        String other$DE055_9A = other.getDE055_9A();
        if (this$DE055_9A == null ? other$DE055_9A != null : !this$DE055_9A.equals(other$DE055_9A)) {
            return false;
        }
        String this$DE055_9C = this.getDE055_9C();
        String other$DE055_9C = other.getDE055_9C();
        if (this$DE055_9C == null ? other$DE055_9C != null : !this$DE055_9C.equals(other$DE055_9C)) {
            return false;
        }
        String this$DE055_9F02 = this.getDE055_9F02();
        String other$DE055_9F02 = other.getDE055_9F02();
        if (this$DE055_9F02 == null ? other$DE055_9F02 != null : !this$DE055_9F02.equals(other$DE055_9F02)) {
            return false;
        }
        String this$DE055_5F2A = this.getDE055_5F2A();
        String other$DE055_5F2A = other.getDE055_5F2A();
        if (this$DE055_5F2A == null ? other$DE055_5F2A != null : !this$DE055_5F2A.equals(other$DE055_5F2A)) {
            return false;
        }
        String this$DE055_82 = this.getDE055_82();
        String other$DE055_82 = other.getDE055_82();
        if (this$DE055_82 == null ? other$DE055_82 != null : !this$DE055_82.equals(other$DE055_82)) {
            return false;
        }
        String this$DE055_9F1A = this.getDE055_9F1A();
        String other$DE055_9F1A = other.getDE055_9F1A();
        if (this$DE055_9F1A == null ? other$DE055_9F1A != null : !this$DE055_9F1A.equals(other$DE055_9F1A)) {
            return false;
        }
        String this$DE055_9F03 = this.getDE055_9F03();
        String other$DE055_9F03 = other.getDE055_9F03();
        if (this$DE055_9F03 == null ? other$DE055_9F03 != null : !this$DE055_9F03.equals(other$DE055_9F03)) {
            return false;
        }
        String this$DE048_PDS0213 = this.getDE048_PDS0213();
        String other$DE048_PDS0213 = other.getDE048_PDS0213();
        if (this$DE048_PDS0213 == null ? other$DE048_PDS0213 != null : !this$DE048_PDS0213.equals(other$DE048_PDS0213)) {
            return false;
        }
        String this$DE055_84 = this.getDE055_84();
        String other$DE055_84 = other.getDE055_84();
        if (this$DE055_84 == null ? other$DE055_84 != null : !this$DE055_84.equals(other$DE055_84)) {
            return false;
        }
        String this$DE055_9F33 = this.getDE055_9F33();
        String other$DE055_9F33 = other.getDE055_9F33();
        if (this$DE055_9F33 == null ? other$DE055_9F33 != null : !this$DE055_9F33.equals(other$DE055_9F33)) {
            return false;
        }
        String this$DE055_9F34 = this.getDE055_9F34();
        String other$DE055_9F34 = other.getDE055_9F34();
        if (this$DE055_9F34 == null ? other$DE055_9F34 != null : !this$DE055_9F34.equals(other$DE055_9F34)) {
            return false;
        }
        String this$DE048_PDS0170 = this.getDE048_PDS0170();
        String other$DE048_PDS0170 = other.getDE048_PDS0170();
        if (this$DE048_PDS0170 == null ? other$DE048_PDS0170 != null : !this$DE048_PDS0170.equals(other$DE048_PDS0170)) {
            return false;
        }
        String this$PDS0018 = this.getPDS0018();
        String other$PDS0018 = other.getPDS0018();
        if (this$PDS0018 == null ? other$PDS0018 != null : !this$PDS0018.equals(other$PDS0018)) {
            return false;
        }
        String this$DE048_PDS0175 = this.getDE048_PDS0175();
        String other$DE048_PDS0175 = other.getDE048_PDS0175();
        return !(this$DE048_PDS0175 == null ? other$DE048_PDS0175 != null : !this$DE048_PDS0175.equals(other$DE048_PDS0175));
    }

    protected boolean canEqual(Object other) {
        return other instanceof IpmOutWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        result = result * 59 + this.getInsCode();
        result = result * 59 + this.getRefNumber();
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $DE001 = this.getDE001();
        result = result * 59 + ($DE001 == null ? 43 : $DE001.hashCode());
        String $DE002 = this.getDE002();
        result = result * 59 + ($DE002 == null ? 43 : $DE002.hashCode());
        String $DE003 = this.getDE003();
        result = result * 59 + ($DE003 == null ? 43 : $DE003.hashCode());
        String $DE004 = this.getDE004();
        result = result * 59 + ($DE004 == null ? 43 : $DE004.hashCode());
        String $DE012 = this.getDE012();
        result = result * 59 + ($DE012 == null ? 43 : $DE012.hashCode());
        String $DE014 = this.getDE014();
        result = result * 59 + ($DE014 == null ? 43 : $DE014.hashCode());
        String $DE022 = this.getDE022();
        result = result * 59 + ($DE022 == null ? 43 : $DE022.hashCode());
        String $DE023 = this.getDE023();
        result = result * 59 + ($DE023 == null ? 43 : $DE023.hashCode());
        String $DE024 = this.getDE024();
        result = result * 59 + ($DE024 == null ? 43 : $DE024.hashCode());
        String $DE025 = this.getDE025();
        result = result * 59 + ($DE025 == null ? 43 : $DE025.hashCode());
        String $DE026 = this.getDE026();
        result = result * 59 + ($DE026 == null ? 43 : $DE026.hashCode());
        String $DE030 = this.getDE030();
        result = result * 59 + ($DE030 == null ? 43 : $DE030.hashCode());
        String $DE031 = this.getDE031();
        result = result * 59 + ($DE031 == null ? 43 : $DE031.hashCode());
        String $DE032 = this.getDE032();
        result = result * 59 + ($DE032 == null ? 43 : $DE032.hashCode());
        String $DE033 = this.getDE033();
        result = result * 59 + ($DE033 == null ? 43 : $DE033.hashCode());
        String $DE037 = this.getDE037();
        result = result * 59 + ($DE037 == null ? 43 : $DE037.hashCode());
        String $DE038 = this.getDE038();
        result = result * 59 + ($DE038 == null ? 43 : $DE038.hashCode());
        String $DE040 = this.getDE040();
        result = result * 59 + ($DE040 == null ? 43 : $DE040.hashCode());
        String $DE041 = this.getDE041();
        result = result * 59 + ($DE041 == null ? 43 : $DE041.hashCode());
        String $DE042 = this.getDE042();
        result = result * 59 + ($DE042 == null ? 43 : $DE042.hashCode());
        String $DE043 = this.getDE043();
        result = result * 59 + ($DE043 == null ? 43 : $DE043.hashCode());
        String $DE049 = this.getDE049();
        result = result * 59 + ($DE049 == null ? 43 : $DE049.hashCode());
        String $DE054 = this.getDE054();
        result = result * 59 + ($DE054 == null ? 43 : $DE054.hashCode());
        String $DE063 = this.getDE063();
        result = result * 59 + ($DE063 == null ? 43 : $DE063.hashCode());
        String $DE071 = this.getDE071();
        result = result * 59 + ($DE071 == null ? 43 : $DE071.hashCode());
        String $DE072 = this.getDE072();
        result = result * 59 + ($DE072 == null ? 43 : $DE072.hashCode());
        String $DE093 = this.getDE093();
        result = result * 59 + ($DE093 == null ? 43 : $DE093.hashCode());
        String $DE094 = this.getDE094();
        result = result * 59 + ($DE094 == null ? 43 : $DE094.hashCode());
        String $DE095 = this.getDE095();
        result = result * 59 + ($DE095 == null ? 43 : $DE095.hashCode());
        String $PDS23 = this.getPDS23();
        result = result * 59 + ($PDS23 == null ? 43 : $PDS23.hashCode());
        String $PDS25 = this.getPDS25();
        result = result * 59 + ($PDS25 == null ? 43 : $PDS25.hashCode());
        String $PDS52 = this.getPDS52();
        result = result * 59 + ($PDS52 == null ? 43 : $PDS52.hashCode());
        String $PDS137 = this.getPDS137();
        result = result * 59 + ($PDS137 == null ? 43 : $PDS137.hashCode());
        String $PDS148 = this.getPDS148();
        result = result * 59 + ($PDS148 == null ? 43 : $PDS148.hashCode());
        String $PDS149 = this.getPDS149();
        result = result * 59 + ($PDS149 == null ? 43 : $PDS149.hashCode());
        String $PDS155 = this.getPDS155();
        result = result * 59 + ($PDS155 == null ? 43 : $PDS155.hashCode());
        String $PDS165 = this.getPDS165();
        result = result * 59 + ($PDS165 == null ? 43 : $PDS165.hashCode());
        String $PDS176 = this.getPDS176();
        result = result * 59 + ($PDS176 == null ? 43 : $PDS176.hashCode());
        String $PDS211 = this.getPDS211();
        result = result * 59 + ($PDS211 == null ? 43 : $PDS211.hashCode());
        String $PDS262 = this.getPDS262();
        result = result * 59 + ($PDS262 == null ? 43 : $PDS262.hashCode());
        String $DE055_9F26 = this.getDE055_9F26();
        result = result * 59 + ($DE055_9F26 == null ? 43 : $DE055_9F26.hashCode());
        String $DE055_9F27 = this.getDE055_9F27();
        result = result * 59 + ($DE055_9F27 == null ? 43 : $DE055_9F27.hashCode());
        String $DE055_9F10 = this.getDE055_9F10();
        result = result * 59 + ($DE055_9F10 == null ? 43 : $DE055_9F10.hashCode());
        String $DE055_9F37 = this.getDE055_9F37();
        result = result * 59 + ($DE055_9F37 == null ? 43 : $DE055_9F37.hashCode());
        String $DE055_9F36 = this.getDE055_9F36();
        result = result * 59 + ($DE055_9F36 == null ? 43 : $DE055_9F36.hashCode());
        String $DE055_95 = this.getDE055_95();
        result = result * 59 + ($DE055_95 == null ? 43 : $DE055_95.hashCode());
        String $DE055_9A = this.getDE055_9A();
        result = result * 59 + ($DE055_9A == null ? 43 : $DE055_9A.hashCode());
        String $DE055_9C = this.getDE055_9C();
        result = result * 59 + ($DE055_9C == null ? 43 : $DE055_9C.hashCode());
        String $DE055_9F02 = this.getDE055_9F02();
        result = result * 59 + ($DE055_9F02 == null ? 43 : $DE055_9F02.hashCode());
        String $DE055_5F2A = this.getDE055_5F2A();
        result = result * 59 + ($DE055_5F2A == null ? 43 : $DE055_5F2A.hashCode());
        String $DE055_82 = this.getDE055_82();
        result = result * 59 + ($DE055_82 == null ? 43 : $DE055_82.hashCode());
        String $DE055_9F1A = this.getDE055_9F1A();
        result = result * 59 + ($DE055_9F1A == null ? 43 : $DE055_9F1A.hashCode());
        String $DE055_9F03 = this.getDE055_9F03();
        result = result * 59 + ($DE055_9F03 == null ? 43 : $DE055_9F03.hashCode());
        String $DE048_PDS0213 = this.getDE048_PDS0213();
        result = result * 59 + ($DE048_PDS0213 == null ? 43 : $DE048_PDS0213.hashCode());
        String $DE055_84 = this.getDE055_84();
        result = result * 59 + ($DE055_84 == null ? 43 : $DE055_84.hashCode());
        String $DE055_9F33 = this.getDE055_9F33();
        result = result * 59 + ($DE055_9F33 == null ? 43 : $DE055_9F33.hashCode());
        String $DE055_9F34 = this.getDE055_9F34();
        result = result * 59 + ($DE055_9F34 == null ? 43 : $DE055_9F34.hashCode());
        String $DE048_PDS0170 = this.getDE048_PDS0170();
        result = result * 59 + ($DE048_PDS0170 == null ? 43 : $DE048_PDS0170.hashCode());
        String $PDS0018 = this.getPDS0018();
        result = result * 59 + ($PDS0018 == null ? 43 : $PDS0018.hashCode());
        String $DE048_PDS0175 = this.getDE048_PDS0175();
        result = result * 59 + ($DE048_PDS0175 == null ? 43 : $DE048_PDS0175.hashCode());
        return result;
    }

    public String toString() {
        return "IpmOutWorkEntity(serialNumber=" + this.getSerialNumber() + ", insCode=" + this.getInsCode() + ", fileId=" + this.getFileId() + ", refNumber=" + this.getRefNumber() + ", DE001=" + this.getDE001() + ", DE002=" + this.getDE002() + ", DE003=" + this.getDE003() + ", DE004=" + this.getDE004() + ", DE012=" + this.getDE012() + ", DE014=" + this.getDE014() + ", DE022=" + this.getDE022() + ", DE023=" + this.getDE023() + ", DE024=" + this.getDE024() + ", DE025=" + this.getDE025() + ", DE026=" + this.getDE026() + ", DE030=" + this.getDE030() + ", DE031=" + this.getDE031() + ", DE032=" + this.getDE032() + ", DE033=" + this.getDE033() + ", DE037=" + this.getDE037() + ", DE038=" + this.getDE038() + ", DE040=" + this.getDE040() + ", DE041=" + this.getDE041() + ", DE042=" + this.getDE042() + ", DE043=" + this.getDE043() + ", DE049=" + this.getDE049() + ", DE054=" + this.getDE054() + ", DE063=" + this.getDE063() + ", DE071=" + this.getDE071() + ", DE072=" + this.getDE072() + ", DE093=" + this.getDE093() + ", DE094=" + this.getDE094() + ", DE095=" + this.getDE095() + ", PDS23=" + this.getPDS23() + ", PDS25=" + this.getPDS25() + ", PDS52=" + this.getPDS52() + ", PDS137=" + this.getPDS137() + ", PDS148=" + this.getPDS148() + ", PDS149=" + this.getPDS149() + ", PDS155=" + this.getPDS155() + ", PDS165=" + this.getPDS165() + ", PDS176=" + this.getPDS176() + ", PDS211=" + this.getPDS211() + ", PDS262=" + this.getPDS262() + ", DE055_9F26=" + this.getDE055_9F26() + ", DE055_9F27=" + this.getDE055_9F27() + ", DE055_9F10=" + this.getDE055_9F10() + ", DE055_9F37=" + this.getDE055_9F37() + ", DE055_9F36=" + this.getDE055_9F36() + ", DE055_95=" + this.getDE055_95() + ", DE055_9A=" + this.getDE055_9A() + ", DE055_9C=" + this.getDE055_9C() + ", DE055_9F02=" + this.getDE055_9F02() + ", DE055_5F2A=" + this.getDE055_5F2A() + ", DE055_82=" + this.getDE055_82() + ", DE055_9F1A=" + this.getDE055_9F1A() + ", DE055_9F03=" + this.getDE055_9F03() + ", DE048_PDS0213=" + this.getDE048_PDS0213() + ", DE055_84=" + this.getDE055_84() + ", DE055_9F33=" + this.getDE055_9F33() + ", DE055_9F34=" + this.getDE055_9F34() + ", DE048_PDS0170=" + this.getDE048_PDS0170() + ", PDS0018=" + this.getPDS0018() + ", DE048_PDS0175=" + this.getDE048_PDS0175() + ")";
    }
}

