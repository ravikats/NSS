/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.ViewGCOIpmOutWorkEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="VW_GCO_IPM_OUT_WORK")
public class ViewGCOIpmOutWorkEntity {
    @Id
    @Column(name="SERIALNO")
    private Integer serialNo;
    @Column(name="DE001")
    private String de001;
    @Column(name="DE002")
    private String de002;
    @Column(name="DE003")
    private String de003;
    @Column(name="DE004")
    private String de004;
    @Column(name="DE012")
    private String de012;
    @Column(name="DE014")
    private String de014;
    @Column(name="DE022")
    private String de022;
    @Column(name="DE023")
    private String de023;
    @Column(name="DE024")
    private String de024;
    @Column(name="DE025")
    private String de025;
    @Column(name="DE026")
    private String de026;
    @Column(name="DE030")
    private String de030;
    @Column(name="DE031")
    private String de031;
    @Column(name="DE033")
    private String de033;
    @Column(name="DE037")
    private String de037;
    @Column(name="DE038")
    private String de038;
    @Column(name="DE040")
    private String de040;
    @Column(name="DE041")
    private String de041;
    @Column(name="DE042")
    private String de042;
    @Column(name="DE043")
    private String de043;
    @Column(name="DE049")
    private String de049;
    @Column(name="DE054")
    private String de054;
    @Column(name="DE063")
    private String de063;
    @Column(name="DE071")
    private String de071;
    @Column(name="DE072")
    private String de072;
    @Column(name="DE093")
    private String de093;
    @Column(name="DE095")
    private String de095;
    @Column(name="DE048_0023")
    private String de0480023Pds23;
    @Column(name="DE048_0025")
    private String de0480025;
    @Column(name="DE048_0052")
    private String de0480052;
    @Column(name="DE048_0137")
    private String de0480137;
    @Column(name="DE048_0148")
    private String de0480148;
    @Column(name="DE048_0149")
    private String de0480149;
    @Column(name="DE048_0155")
    private String de0480155;
    @Column(name="DE048_0165")
    private String de0480165;
    @Column(name="DE048_0176")
    private String de0480176;
    @Column(name="DE048_0211")
    private String de0480211;
    @Column(name="DE048_0262")
    private String de0480262;
    @Column(name="DE055_9F26")
    private String de0559f26;
    @Column(name="DE055_9F27")
    private String de0559f27;
    @Column(name="DE055_9F10")
    private String de0559f10;
    @Column(name="DE055_9F37")
    private String de0559f37;
    @Column(name="DE055_9F36")
    private String de0559f36;
    @Column(name="DE055_95")
    private String de05595;
    @Column(name="DE055_9A")
    private String de0559a;
    @Column(name="DE055_9C")
    private String de0559c;
    @Column(name="DE055_9F02")
    private String de0559f02;
    @Column(name="DE055_5F2A")
    private String de0555f2a;
    @Column(name="DE055_82")
    private String de05582;
    @Column(name="DE055_9F1A")
    private String de0559f1a;
    @Column(name="DE055_9F03")
    private String de0559f03;
    @Column(name="DE055_84")
    private String de05584;
    @Column(name="DE055_9F33")
    private String de0559f33;
    @Column(name="DE055_9F34")
    private String de0559f34;
    @Column(name="test")
    private String localDateTime;
    @Column(name="test1")
    private String txnType;
    @Column(name="test2")
    private String txnAmount;

    public Integer getSerialNo() {
        return this.serialNo;
    }

    public String getDe001() {
        return this.de001;
    }

    public String getDe002() {
        return this.de002;
    }

    public String getDe003() {
        return this.de003;
    }

    public String getDe004() {
        return this.de004;
    }

    public String getDe012() {
        return this.de012;
    }

    public String getDe014() {
        return this.de014;
    }

    public String getDe022() {
        return this.de022;
    }

    public String getDe023() {
        return this.de023;
    }

    public String getDe024() {
        return this.de024;
    }

    public String getDe025() {
        return this.de025;
    }

    public String getDe026() {
        return this.de026;
    }

    public String getDe030() {
        return this.de030;
    }

    public String getDe031() {
        return this.de031;
    }

    public String getDe033() {
        return this.de033;
    }

    public String getDe037() {
        return this.de037;
    }

    public String getDe038() {
        return this.de038;
    }

    public String getDe040() {
        return this.de040;
    }

    public String getDe041() {
        return this.de041;
    }

    public String getDe042() {
        return this.de042;
    }

    public String getDe043() {
        return this.de043;
    }

    public String getDe049() {
        return this.de049;
    }

    public String getDe054() {
        return this.de054;
    }

    public String getDe063() {
        return this.de063;
    }

    public String getDe071() {
        return this.de071;
    }

    public String getDe072() {
        return this.de072;
    }

    public String getDe093() {
        return this.de093;
    }

    public String getDe095() {
        return this.de095;
    }

    public String getDe0480023Pds23() {
        return this.de0480023Pds23;
    }

    public String getDe0480025() {
        return this.de0480025;
    }

    public String getDe0480052() {
        return this.de0480052;
    }

    public String getDe0480137() {
        return this.de0480137;
    }

    public String getDe0480148() {
        return this.de0480148;
    }

    public String getDe0480149() {
        return this.de0480149;
    }

    public String getDe0480155() {
        return this.de0480155;
    }

    public String getDe0480165() {
        return this.de0480165;
    }

    public String getDe0480176() {
        return this.de0480176;
    }

    public String getDe0480211() {
        return this.de0480211;
    }

    public String getDe0480262() {
        return this.de0480262;
    }

    public String getDe0559f26() {
        return this.de0559f26;
    }

    public String getDe0559f27() {
        return this.de0559f27;
    }

    public String getDe0559f10() {
        return this.de0559f10;
    }

    public String getDe0559f37() {
        return this.de0559f37;
    }

    public String getDe0559f36() {
        return this.de0559f36;
    }

    public String getDe05595() {
        return this.de05595;
    }

    public String getDe0559a() {
        return this.de0559a;
    }

    public String getDe0559c() {
        return this.de0559c;
    }

    public String getDe0559f02() {
        return this.de0559f02;
    }

    public String getDe0555f2a() {
        return this.de0555f2a;
    }

    public String getDe05582() {
        return this.de05582;
    }

    public String getDe0559f1a() {
        return this.de0559f1a;
    }

    public String getDe0559f03() {
        return this.de0559f03;
    }

    public String getDe05584() {
        return this.de05584;
    }

    public String getDe0559f33() {
        return this.de0559f33;
    }

    public String getDe0559f34() {
        return this.de0559f34;
    }

    public String getLocalDateTime() {
        return this.localDateTime;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getTxnAmount() {
        return this.txnAmount;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public void setDe001(String de001) {
        this.de001 = de001;
    }

    public void setDe002(String de002) {
        this.de002 = de002;
    }

    public void setDe003(String de003) {
        this.de003 = de003;
    }

    public void setDe004(String de004) {
        this.de004 = de004;
    }

    public void setDe012(String de012) {
        this.de012 = de012;
    }

    public void setDe014(String de014) {
        this.de014 = de014;
    }

    public void setDe022(String de022) {
        this.de022 = de022;
    }

    public void setDe023(String de023) {
        this.de023 = de023;
    }

    public void setDe024(String de024) {
        this.de024 = de024;
    }

    public void setDe025(String de025) {
        this.de025 = de025;
    }

    public void setDe026(String de026) {
        this.de026 = de026;
    }

    public void setDe030(String de030) {
        this.de030 = de030;
    }

    public void setDe031(String de031) {
        this.de031 = de031;
    }

    public void setDe033(String de033) {
        this.de033 = de033;
    }

    public void setDe037(String de037) {
        this.de037 = de037;
    }

    public void setDe038(String de038) {
        this.de038 = de038;
    }

    public void setDe040(String de040) {
        this.de040 = de040;
    }

    public void setDe041(String de041) {
        this.de041 = de041;
    }

    public void setDe042(String de042) {
        this.de042 = de042;
    }

    public void setDe043(String de043) {
        this.de043 = de043;
    }

    public void setDe049(String de049) {
        this.de049 = de049;
    }

    public void setDe054(String de054) {
        this.de054 = de054;
    }

    public void setDe063(String de063) {
        this.de063 = de063;
    }

    public void setDe071(String de071) {
        this.de071 = de071;
    }

    public void setDe072(String de072) {
        this.de072 = de072;
    }

    public void setDe093(String de093) {
        this.de093 = de093;
    }

    public void setDe095(String de095) {
        this.de095 = de095;
    }

    public void setDe0480023Pds23(String de0480023Pds23) {
        this.de0480023Pds23 = de0480023Pds23;
    }

    public void setDe0480025(String de0480025) {
        this.de0480025 = de0480025;
    }

    public void setDe0480052(String de0480052) {
        this.de0480052 = de0480052;
    }

    public void setDe0480137(String de0480137) {
        this.de0480137 = de0480137;
    }

    public void setDe0480148(String de0480148) {
        this.de0480148 = de0480148;
    }

    public void setDe0480149(String de0480149) {
        this.de0480149 = de0480149;
    }

    public void setDe0480155(String de0480155) {
        this.de0480155 = de0480155;
    }

    public void setDe0480165(String de0480165) {
        this.de0480165 = de0480165;
    }

    public void setDe0480176(String de0480176) {
        this.de0480176 = de0480176;
    }

    public void setDe0480211(String de0480211) {
        this.de0480211 = de0480211;
    }

    public void setDe0480262(String de0480262) {
        this.de0480262 = de0480262;
    }

    public void setDe0559f26(String de0559f26) {
        this.de0559f26 = de0559f26;
    }

    public void setDe0559f27(String de0559f27) {
        this.de0559f27 = de0559f27;
    }

    public void setDe0559f10(String de0559f10) {
        this.de0559f10 = de0559f10;
    }

    public void setDe0559f37(String de0559f37) {
        this.de0559f37 = de0559f37;
    }

    public void setDe0559f36(String de0559f36) {
        this.de0559f36 = de0559f36;
    }

    public void setDe05595(String de05595) {
        this.de05595 = de05595;
    }

    public void setDe0559a(String de0559a) {
        this.de0559a = de0559a;
    }

    public void setDe0559c(String de0559c) {
        this.de0559c = de0559c;
    }

    public void setDe0559f02(String de0559f02) {
        this.de0559f02 = de0559f02;
    }

    public void setDe0555f2a(String de0555f2a) {
        this.de0555f2a = de0555f2a;
    }

    public void setDe05582(String de05582) {
        this.de05582 = de05582;
    }

    public void setDe0559f1a(String de0559f1a) {
        this.de0559f1a = de0559f1a;
    }

    public void setDe0559f03(String de0559f03) {
        this.de0559f03 = de0559f03;
    }

    public void setDe05584(String de05584) {
        this.de05584 = de05584;
    }

    public void setDe0559f33(String de0559f33) {
        this.de0559f33 = de0559f33;
    }

    public void setDe0559f34(String de0559f34) {
        this.de0559f34 = de0559f34;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewGCOIpmOutWorkEntity)) {
            return false;
        }
        ViewGCOIpmOutWorkEntity other = (ViewGCOIpmOutWorkEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNo = this.getSerialNo();
        Integer other$serialNo = other.getSerialNo();
        if (this$serialNo == null ? other$serialNo != null : !((Object)this$serialNo).equals(other$serialNo)) {
            return false;
        }
        String this$de001 = this.getDe001();
        String other$de001 = other.getDe001();
        if (this$de001 == null ? other$de001 != null : !this$de001.equals(other$de001)) {
            return false;
        }
        String this$de002 = this.getDe002();
        String other$de002 = other.getDe002();
        if (this$de002 == null ? other$de002 != null : !this$de002.equals(other$de002)) {
            return false;
        }
        String this$de003 = this.getDe003();
        String other$de003 = other.getDe003();
        if (this$de003 == null ? other$de003 != null : !this$de003.equals(other$de003)) {
            return false;
        }
        String this$de004 = this.getDe004();
        String other$de004 = other.getDe004();
        if (this$de004 == null ? other$de004 != null : !this$de004.equals(other$de004)) {
            return false;
        }
        String this$de012 = this.getDe012();
        String other$de012 = other.getDe012();
        if (this$de012 == null ? other$de012 != null : !this$de012.equals(other$de012)) {
            return false;
        }
        String this$de014 = this.getDe014();
        String other$de014 = other.getDe014();
        if (this$de014 == null ? other$de014 != null : !this$de014.equals(other$de014)) {
            return false;
        }
        String this$de022 = this.getDe022();
        String other$de022 = other.getDe022();
        if (this$de022 == null ? other$de022 != null : !this$de022.equals(other$de022)) {
            return false;
        }
        String this$de023 = this.getDe023();
        String other$de023 = other.getDe023();
        if (this$de023 == null ? other$de023 != null : !this$de023.equals(other$de023)) {
            return false;
        }
        String this$de024 = this.getDe024();
        String other$de024 = other.getDe024();
        if (this$de024 == null ? other$de024 != null : !this$de024.equals(other$de024)) {
            return false;
        }
        String this$de025 = this.getDe025();
        String other$de025 = other.getDe025();
        if (this$de025 == null ? other$de025 != null : !this$de025.equals(other$de025)) {
            return false;
        }
        String this$de026 = this.getDe026();
        String other$de026 = other.getDe026();
        if (this$de026 == null ? other$de026 != null : !this$de026.equals(other$de026)) {
            return false;
        }
        String this$de030 = this.getDe030();
        String other$de030 = other.getDe030();
        if (this$de030 == null ? other$de030 != null : !this$de030.equals(other$de030)) {
            return false;
        }
        String this$de031 = this.getDe031();
        String other$de031 = other.getDe031();
        if (this$de031 == null ? other$de031 != null : !this$de031.equals(other$de031)) {
            return false;
        }
        String this$de033 = this.getDe033();
        String other$de033 = other.getDe033();
        if (this$de033 == null ? other$de033 != null : !this$de033.equals(other$de033)) {
            return false;
        }
        String this$de037 = this.getDe037();
        String other$de037 = other.getDe037();
        if (this$de037 == null ? other$de037 != null : !this$de037.equals(other$de037)) {
            return false;
        }
        String this$de038 = this.getDe038();
        String other$de038 = other.getDe038();
        if (this$de038 == null ? other$de038 != null : !this$de038.equals(other$de038)) {
            return false;
        }
        String this$de040 = this.getDe040();
        String other$de040 = other.getDe040();
        if (this$de040 == null ? other$de040 != null : !this$de040.equals(other$de040)) {
            return false;
        }
        String this$de041 = this.getDe041();
        String other$de041 = other.getDe041();
        if (this$de041 == null ? other$de041 != null : !this$de041.equals(other$de041)) {
            return false;
        }
        String this$de042 = this.getDe042();
        String other$de042 = other.getDe042();
        if (this$de042 == null ? other$de042 != null : !this$de042.equals(other$de042)) {
            return false;
        }
        String this$de043 = this.getDe043();
        String other$de043 = other.getDe043();
        if (this$de043 == null ? other$de043 != null : !this$de043.equals(other$de043)) {
            return false;
        }
        String this$de049 = this.getDe049();
        String other$de049 = other.getDe049();
        if (this$de049 == null ? other$de049 != null : !this$de049.equals(other$de049)) {
            return false;
        }
        String this$de054 = this.getDe054();
        String other$de054 = other.getDe054();
        if (this$de054 == null ? other$de054 != null : !this$de054.equals(other$de054)) {
            return false;
        }
        String this$de063 = this.getDe063();
        String other$de063 = other.getDe063();
        if (this$de063 == null ? other$de063 != null : !this$de063.equals(other$de063)) {
            return false;
        }
        String this$de071 = this.getDe071();
        String other$de071 = other.getDe071();
        if (this$de071 == null ? other$de071 != null : !this$de071.equals(other$de071)) {
            return false;
        }
        String this$de072 = this.getDe072();
        String other$de072 = other.getDe072();
        if (this$de072 == null ? other$de072 != null : !this$de072.equals(other$de072)) {
            return false;
        }
        String this$de093 = this.getDe093();
        String other$de093 = other.getDe093();
        if (this$de093 == null ? other$de093 != null : !this$de093.equals(other$de093)) {
            return false;
        }
        String this$de095 = this.getDe095();
        String other$de095 = other.getDe095();
        if (this$de095 == null ? other$de095 != null : !this$de095.equals(other$de095)) {
            return false;
        }
        String this$de0480023Pds23 = this.getDe0480023Pds23();
        String other$de0480023Pds23 = other.getDe0480023Pds23();
        if (this$de0480023Pds23 == null ? other$de0480023Pds23 != null : !this$de0480023Pds23.equals(other$de0480023Pds23)) {
            return false;
        }
        String this$de0480025 = this.getDe0480025();
        String other$de0480025 = other.getDe0480025();
        if (this$de0480025 == null ? other$de0480025 != null : !this$de0480025.equals(other$de0480025)) {
            return false;
        }
        String this$de0480052 = this.getDe0480052();
        String other$de0480052 = other.getDe0480052();
        if (this$de0480052 == null ? other$de0480052 != null : !this$de0480052.equals(other$de0480052)) {
            return false;
        }
        String this$de0480137 = this.getDe0480137();
        String other$de0480137 = other.getDe0480137();
        if (this$de0480137 == null ? other$de0480137 != null : !this$de0480137.equals(other$de0480137)) {
            return false;
        }
        String this$de0480148 = this.getDe0480148();
        String other$de0480148 = other.getDe0480148();
        if (this$de0480148 == null ? other$de0480148 != null : !this$de0480148.equals(other$de0480148)) {
            return false;
        }
        String this$de0480149 = this.getDe0480149();
        String other$de0480149 = other.getDe0480149();
        if (this$de0480149 == null ? other$de0480149 != null : !this$de0480149.equals(other$de0480149)) {
            return false;
        }
        String this$de0480155 = this.getDe0480155();
        String other$de0480155 = other.getDe0480155();
        if (this$de0480155 == null ? other$de0480155 != null : !this$de0480155.equals(other$de0480155)) {
            return false;
        }
        String this$de0480165 = this.getDe0480165();
        String other$de0480165 = other.getDe0480165();
        if (this$de0480165 == null ? other$de0480165 != null : !this$de0480165.equals(other$de0480165)) {
            return false;
        }
        String this$de0480176 = this.getDe0480176();
        String other$de0480176 = other.getDe0480176();
        if (this$de0480176 == null ? other$de0480176 != null : !this$de0480176.equals(other$de0480176)) {
            return false;
        }
        String this$de0480211 = this.getDe0480211();
        String other$de0480211 = other.getDe0480211();
        if (this$de0480211 == null ? other$de0480211 != null : !this$de0480211.equals(other$de0480211)) {
            return false;
        }
        String this$de0480262 = this.getDe0480262();
        String other$de0480262 = other.getDe0480262();
        if (this$de0480262 == null ? other$de0480262 != null : !this$de0480262.equals(other$de0480262)) {
            return false;
        }
        String this$de0559f26 = this.getDe0559f26();
        String other$de0559f26 = other.getDe0559f26();
        if (this$de0559f26 == null ? other$de0559f26 != null : !this$de0559f26.equals(other$de0559f26)) {
            return false;
        }
        String this$de0559f27 = this.getDe0559f27();
        String other$de0559f27 = other.getDe0559f27();
        if (this$de0559f27 == null ? other$de0559f27 != null : !this$de0559f27.equals(other$de0559f27)) {
            return false;
        }
        String this$de0559f10 = this.getDe0559f10();
        String other$de0559f10 = other.getDe0559f10();
        if (this$de0559f10 == null ? other$de0559f10 != null : !this$de0559f10.equals(other$de0559f10)) {
            return false;
        }
        String this$de0559f37 = this.getDe0559f37();
        String other$de0559f37 = other.getDe0559f37();
        if (this$de0559f37 == null ? other$de0559f37 != null : !this$de0559f37.equals(other$de0559f37)) {
            return false;
        }
        String this$de0559f36 = this.getDe0559f36();
        String other$de0559f36 = other.getDe0559f36();
        if (this$de0559f36 == null ? other$de0559f36 != null : !this$de0559f36.equals(other$de0559f36)) {
            return false;
        }
        String this$de05595 = this.getDe05595();
        String other$de05595 = other.getDe05595();
        if (this$de05595 == null ? other$de05595 != null : !this$de05595.equals(other$de05595)) {
            return false;
        }
        String this$de0559a = this.getDe0559a();
        String other$de0559a = other.getDe0559a();
        if (this$de0559a == null ? other$de0559a != null : !this$de0559a.equals(other$de0559a)) {
            return false;
        }
        String this$de0559c = this.getDe0559c();
        String other$de0559c = other.getDe0559c();
        if (this$de0559c == null ? other$de0559c != null : !this$de0559c.equals(other$de0559c)) {
            return false;
        }
        String this$de0559f02 = this.getDe0559f02();
        String other$de0559f02 = other.getDe0559f02();
        if (this$de0559f02 == null ? other$de0559f02 != null : !this$de0559f02.equals(other$de0559f02)) {
            return false;
        }
        String this$de0555f2a = this.getDe0555f2a();
        String other$de0555f2a = other.getDe0555f2a();
        if (this$de0555f2a == null ? other$de0555f2a != null : !this$de0555f2a.equals(other$de0555f2a)) {
            return false;
        }
        String this$de05582 = this.getDe05582();
        String other$de05582 = other.getDe05582();
        if (this$de05582 == null ? other$de05582 != null : !this$de05582.equals(other$de05582)) {
            return false;
        }
        String this$de0559f1a = this.getDe0559f1a();
        String other$de0559f1a = other.getDe0559f1a();
        if (this$de0559f1a == null ? other$de0559f1a != null : !this$de0559f1a.equals(other$de0559f1a)) {
            return false;
        }
        String this$de0559f03 = this.getDe0559f03();
        String other$de0559f03 = other.getDe0559f03();
        if (this$de0559f03 == null ? other$de0559f03 != null : !this$de0559f03.equals(other$de0559f03)) {
            return false;
        }
        String this$de05584 = this.getDe05584();
        String other$de05584 = other.getDe05584();
        if (this$de05584 == null ? other$de05584 != null : !this$de05584.equals(other$de05584)) {
            return false;
        }
        String this$de0559f33 = this.getDe0559f33();
        String other$de0559f33 = other.getDe0559f33();
        if (this$de0559f33 == null ? other$de0559f33 != null : !this$de0559f33.equals(other$de0559f33)) {
            return false;
        }
        String this$de0559f34 = this.getDe0559f34();
        String other$de0559f34 = other.getDe0559f34();
        if (this$de0559f34 == null ? other$de0559f34 != null : !this$de0559f34.equals(other$de0559f34)) {
            return false;
        }
        String this$localDateTime = this.getLocalDateTime();
        String other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !this$localDateTime.equals(other$localDateTime)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$txnAmount = this.getTxnAmount();
        String other$txnAmount = other.getTxnAmount();
        return !(this$txnAmount == null ? other$txnAmount != null : !this$txnAmount.equals(other$txnAmount));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewGCOIpmOutWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNo = this.getSerialNo();
        result = result * 59 + ($serialNo == null ? 43 : ((Object)$serialNo).hashCode());
        String $de001 = this.getDe001();
        result = result * 59 + ($de001 == null ? 43 : $de001.hashCode());
        String $de002 = this.getDe002();
        result = result * 59 + ($de002 == null ? 43 : $de002.hashCode());
        String $de003 = this.getDe003();
        result = result * 59 + ($de003 == null ? 43 : $de003.hashCode());
        String $de004 = this.getDe004();
        result = result * 59 + ($de004 == null ? 43 : $de004.hashCode());
        String $de012 = this.getDe012();
        result = result * 59 + ($de012 == null ? 43 : $de012.hashCode());
        String $de014 = this.getDe014();
        result = result * 59 + ($de014 == null ? 43 : $de014.hashCode());
        String $de022 = this.getDe022();
        result = result * 59 + ($de022 == null ? 43 : $de022.hashCode());
        String $de023 = this.getDe023();
        result = result * 59 + ($de023 == null ? 43 : $de023.hashCode());
        String $de024 = this.getDe024();
        result = result * 59 + ($de024 == null ? 43 : $de024.hashCode());
        String $de025 = this.getDe025();
        result = result * 59 + ($de025 == null ? 43 : $de025.hashCode());
        String $de026 = this.getDe026();
        result = result * 59 + ($de026 == null ? 43 : $de026.hashCode());
        String $de030 = this.getDe030();
        result = result * 59 + ($de030 == null ? 43 : $de030.hashCode());
        String $de031 = this.getDe031();
        result = result * 59 + ($de031 == null ? 43 : $de031.hashCode());
        String $de033 = this.getDe033();
        result = result * 59 + ($de033 == null ? 43 : $de033.hashCode());
        String $de037 = this.getDe037();
        result = result * 59 + ($de037 == null ? 43 : $de037.hashCode());
        String $de038 = this.getDe038();
        result = result * 59 + ($de038 == null ? 43 : $de038.hashCode());
        String $de040 = this.getDe040();
        result = result * 59 + ($de040 == null ? 43 : $de040.hashCode());
        String $de041 = this.getDe041();
        result = result * 59 + ($de041 == null ? 43 : $de041.hashCode());
        String $de042 = this.getDe042();
        result = result * 59 + ($de042 == null ? 43 : $de042.hashCode());
        String $de043 = this.getDe043();
        result = result * 59 + ($de043 == null ? 43 : $de043.hashCode());
        String $de049 = this.getDe049();
        result = result * 59 + ($de049 == null ? 43 : $de049.hashCode());
        String $de054 = this.getDe054();
        result = result * 59 + ($de054 == null ? 43 : $de054.hashCode());
        String $de063 = this.getDe063();
        result = result * 59 + ($de063 == null ? 43 : $de063.hashCode());
        String $de071 = this.getDe071();
        result = result * 59 + ($de071 == null ? 43 : $de071.hashCode());
        String $de072 = this.getDe072();
        result = result * 59 + ($de072 == null ? 43 : $de072.hashCode());
        String $de093 = this.getDe093();
        result = result * 59 + ($de093 == null ? 43 : $de093.hashCode());
        String $de095 = this.getDe095();
        result = result * 59 + ($de095 == null ? 43 : $de095.hashCode());
        String $de0480023Pds23 = this.getDe0480023Pds23();
        result = result * 59 + ($de0480023Pds23 == null ? 43 : $de0480023Pds23.hashCode());
        String $de0480025 = this.getDe0480025();
        result = result * 59 + ($de0480025 == null ? 43 : $de0480025.hashCode());
        String $de0480052 = this.getDe0480052();
        result = result * 59 + ($de0480052 == null ? 43 : $de0480052.hashCode());
        String $de0480137 = this.getDe0480137();
        result = result * 59 + ($de0480137 == null ? 43 : $de0480137.hashCode());
        String $de0480148 = this.getDe0480148();
        result = result * 59 + ($de0480148 == null ? 43 : $de0480148.hashCode());
        String $de0480149 = this.getDe0480149();
        result = result * 59 + ($de0480149 == null ? 43 : $de0480149.hashCode());
        String $de0480155 = this.getDe0480155();
        result = result * 59 + ($de0480155 == null ? 43 : $de0480155.hashCode());
        String $de0480165 = this.getDe0480165();
        result = result * 59 + ($de0480165 == null ? 43 : $de0480165.hashCode());
        String $de0480176 = this.getDe0480176();
        result = result * 59 + ($de0480176 == null ? 43 : $de0480176.hashCode());
        String $de0480211 = this.getDe0480211();
        result = result * 59 + ($de0480211 == null ? 43 : $de0480211.hashCode());
        String $de0480262 = this.getDe0480262();
        result = result * 59 + ($de0480262 == null ? 43 : $de0480262.hashCode());
        String $de0559f26 = this.getDe0559f26();
        result = result * 59 + ($de0559f26 == null ? 43 : $de0559f26.hashCode());
        String $de0559f27 = this.getDe0559f27();
        result = result * 59 + ($de0559f27 == null ? 43 : $de0559f27.hashCode());
        String $de0559f10 = this.getDe0559f10();
        result = result * 59 + ($de0559f10 == null ? 43 : $de0559f10.hashCode());
        String $de0559f37 = this.getDe0559f37();
        result = result * 59 + ($de0559f37 == null ? 43 : $de0559f37.hashCode());
        String $de0559f36 = this.getDe0559f36();
        result = result * 59 + ($de0559f36 == null ? 43 : $de0559f36.hashCode());
        String $de05595 = this.getDe05595();
        result = result * 59 + ($de05595 == null ? 43 : $de05595.hashCode());
        String $de0559a = this.getDe0559a();
        result = result * 59 + ($de0559a == null ? 43 : $de0559a.hashCode());
        String $de0559c = this.getDe0559c();
        result = result * 59 + ($de0559c == null ? 43 : $de0559c.hashCode());
        String $de0559f02 = this.getDe0559f02();
        result = result * 59 + ($de0559f02 == null ? 43 : $de0559f02.hashCode());
        String $de0555f2a = this.getDe0555f2a();
        result = result * 59 + ($de0555f2a == null ? 43 : $de0555f2a.hashCode());
        String $de05582 = this.getDe05582();
        result = result * 59 + ($de05582 == null ? 43 : $de05582.hashCode());
        String $de0559f1a = this.getDe0559f1a();
        result = result * 59 + ($de0559f1a == null ? 43 : $de0559f1a.hashCode());
        String $de0559f03 = this.getDe0559f03();
        result = result * 59 + ($de0559f03 == null ? 43 : $de0559f03.hashCode());
        String $de05584 = this.getDe05584();
        result = result * 59 + ($de05584 == null ? 43 : $de05584.hashCode());
        String $de0559f33 = this.getDe0559f33();
        result = result * 59 + ($de0559f33 == null ? 43 : $de0559f33.hashCode());
        String $de0559f34 = this.getDe0559f34();
        result = result * 59 + ($de0559f34 == null ? 43 : $de0559f34.hashCode());
        String $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : $localDateTime.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : $txnAmount.hashCode());
        return result;
    }

    public String toString() {
        return "ViewGCOIpmOutWorkEntity(serialNo=" + this.getSerialNo() + ", de001=" + this.getDe001() + ", de002=" + this.getDe002() + ", de003=" + this.getDe003() + ", de004=" + this.getDe004() + ", de012=" + this.getDe012() + ", de014=" + this.getDe014() + ", de022=" + this.getDe022() + ", de023=" + this.getDe023() + ", de024=" + this.getDe024() + ", de025=" + this.getDe025() + ", de026=" + this.getDe026() + ", de030=" + this.getDe030() + ", de031=" + this.getDe031() + ", de033=" + this.getDe033() + ", de037=" + this.getDe037() + ", de038=" + this.getDe038() + ", de040=" + this.getDe040() + ", de041=" + this.getDe041() + ", de042=" + this.getDe042() + ", de043=" + this.getDe043() + ", de049=" + this.getDe049() + ", de054=" + this.getDe054() + ", de063=" + this.getDe063() + ", de071=" + this.getDe071() + ", de072=" + this.getDe072() + ", de093=" + this.getDe093() + ", de095=" + this.getDe095() + ", de0480023Pds23=" + this.getDe0480023Pds23() + ", de0480025=" + this.getDe0480025() + ", de0480052=" + this.getDe0480052() + ", de0480137=" + this.getDe0480137() + ", de0480148=" + this.getDe0480148() + ", de0480149=" + this.getDe0480149() + ", de0480155=" + this.getDe0480155() + ", de0480165=" + this.getDe0480165() + ", de0480176=" + this.getDe0480176() + ", de0480211=" + this.getDe0480211() + ", de0480262=" + this.getDe0480262() + ", de0559f26=" + this.getDe0559f26() + ", de0559f27=" + this.getDe0559f27() + ", de0559f10=" + this.getDe0559f10() + ", de0559f37=" + this.getDe0559f37() + ", de0559f36=" + this.getDe0559f36() + ", de05595=" + this.getDe05595() + ", de0559a=" + this.getDe0559a() + ", de0559c=" + this.getDe0559c() + ", de0559f02=" + this.getDe0559f02() + ", de0555f2a=" + this.getDe0555f2a() + ", de05582=" + this.getDe05582() + ", de0559f1a=" + this.getDe0559f1a() + ", de0559f03=" + this.getDe0559f03() + ", de05584=" + this.getDe05584() + ", de0559f33=" + this.getDe0559f33() + ", de0559f34=" + this.getDe0559f34() + ", localDateTime=" + this.getLocalDateTime() + ", txnType=" + this.getTxnType() + ", txnAmount=" + this.getTxnAmount() + ")";
    }
}

