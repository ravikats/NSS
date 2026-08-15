/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.vo.JaywanXmlTrailerValueObject
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
 */
package com.empay.jaywan.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class JaywanXmlTrailerValueObject {
    @JacksonXmlProperty(localName="nMTI")
    private String nMTI;
    @JacksonXmlProperty(localName="nFunCd")
    private String nFunCd;
    @JacksonXmlProperty(localName="nRecNum")
    private String nRecNum;
    @JacksonXmlProperty(localName="nUnFlNm")
    private String nUnFlNm;
    @JacksonXmlProperty(localName="nTxnCnt")
    private String nTxnCnt;
    @JacksonXmlProperty(localName="nRnTtlAmt")
    private String nRnTtlAmt;

    public String getNMTI() {
        return this.nMTI;
    }

    public String getNFunCd() {
        return this.nFunCd;
    }

    public String getNRecNum() {
        return this.nRecNum;
    }

    public String getNUnFlNm() {
        return this.nUnFlNm;
    }

    public String getNTxnCnt() {
        return this.nTxnCnt;
    }

    public String getNRnTtlAmt() {
        return this.nRnTtlAmt;
    }

    @JacksonXmlProperty(localName="nMTI")
    public void setNMTI(String nMTI) {
        this.nMTI = nMTI;
    }

    @JacksonXmlProperty(localName="nFunCd")
    public void setNFunCd(String nFunCd) {
        this.nFunCd = nFunCd;
    }

    @JacksonXmlProperty(localName="nRecNum")
    public void setNRecNum(String nRecNum) {
        this.nRecNum = nRecNum;
    }

    @JacksonXmlProperty(localName="nUnFlNm")
    public void setNUnFlNm(String nUnFlNm) {
        this.nUnFlNm = nUnFlNm;
    }

    @JacksonXmlProperty(localName="nTxnCnt")
    public void setNTxnCnt(String nTxnCnt) {
        this.nTxnCnt = nTxnCnt;
    }

    @JacksonXmlProperty(localName="nRnTtlAmt")
    public void setNRnTtlAmt(String nRnTtlAmt) {
        this.nRnTtlAmt = nRnTtlAmt;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanXmlTrailerValueObject)) {
            return false;
        }
        JaywanXmlTrailerValueObject other = (JaywanXmlTrailerValueObject)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$nMTI = this.getNMTI();
        String other$nMTI = other.getNMTI();
        if (this$nMTI == null ? other$nMTI != null : !this$nMTI.equals(other$nMTI)) {
            return false;
        }
        String this$nFunCd = this.getNFunCd();
        String other$nFunCd = other.getNFunCd();
        if (this$nFunCd == null ? other$nFunCd != null : !this$nFunCd.equals(other$nFunCd)) {
            return false;
        }
        String this$nRecNum = this.getNRecNum();
        String other$nRecNum = other.getNRecNum();
        if (this$nRecNum == null ? other$nRecNum != null : !this$nRecNum.equals(other$nRecNum)) {
            return false;
        }
        String this$nUnFlNm = this.getNUnFlNm();
        String other$nUnFlNm = other.getNUnFlNm();
        if (this$nUnFlNm == null ? other$nUnFlNm != null : !this$nUnFlNm.equals(other$nUnFlNm)) {
            return false;
        }
        String this$nTxnCnt = this.getNTxnCnt();
        String other$nTxnCnt = other.getNTxnCnt();
        if (this$nTxnCnt == null ? other$nTxnCnt != null : !this$nTxnCnt.equals(other$nTxnCnt)) {
            return false;
        }
        String this$nRnTtlAmt = this.getNRnTtlAmt();
        String other$nRnTtlAmt = other.getNRnTtlAmt();
        return !(this$nRnTtlAmt == null ? other$nRnTtlAmt != null : !this$nRnTtlAmt.equals(other$nRnTtlAmt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanXmlTrailerValueObject;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $nMTI = this.getNMTI();
        result = result * 59 + ($nMTI == null ? 43 : $nMTI.hashCode());
        String $nFunCd = this.getNFunCd();
        result = result * 59 + ($nFunCd == null ? 43 : $nFunCd.hashCode());
        String $nRecNum = this.getNRecNum();
        result = result * 59 + ($nRecNum == null ? 43 : $nRecNum.hashCode());
        String $nUnFlNm = this.getNUnFlNm();
        result = result * 59 + ($nUnFlNm == null ? 43 : $nUnFlNm.hashCode());
        String $nTxnCnt = this.getNTxnCnt();
        result = result * 59 + ($nTxnCnt == null ? 43 : $nTxnCnt.hashCode());
        String $nRnTtlAmt = this.getNRnTtlAmt();
        result = result * 59 + ($nRnTtlAmt == null ? 43 : $nRnTtlAmt.hashCode());
        return result;
    }

    public String toString() {
        return "JaywanXmlTrailerValueObject(nMTI=" + this.getNMTI() + ", nFunCd=" + this.getNFunCd() + ", nRecNum=" + this.getNRecNum() + ", nUnFlNm=" + this.getNUnFlNm() + ", nTxnCnt=" + this.getNTxnCnt() + ", nRnTtlAmt=" + this.getNRnTtlAmt() + ")";
    }
}

