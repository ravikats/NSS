/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.vo.JaywanXmlHeaderValueObject
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
 */
package com.empay.jaywan.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class JaywanXmlHeaderValueObject {
    @JacksonXmlProperty(localName="nMTI")
    private String nMTI;
    @JacksonXmlProperty(localName="nFunCd")
    private String nFunCd;
    @JacksonXmlProperty(localName="nRecNum")
    private String nRecNum;
    @JacksonXmlProperty(localName="nDtTmFlGen")
    private String nDtTmFlGen;
    @JacksonXmlProperty(localName="nDtSet")
    private String nDtSet;
    @JacksonXmlProperty(localName="nMemInstCd")
    private String nMemInstCd;
    @JacksonXmlProperty(localName="nUnFlNm")
    private String nUnFlNm;
    @JacksonXmlProperty(localName="nProdCd")
    private String nProdCd;
    @JacksonXmlProperty(localName="nFlCatg")
    private String nFlCatg;
    @JacksonXmlProperty(localName="nVerNum")
    private String nVerNum;
    @JacksonXmlProperty(localName="nFlRejInd")
    private String nFlRejInd;
    @JacksonXmlProperty(localName="nFlRejRsnCd")
    private String nFlRejRsnCd;

    public String getNMTI() {
        return this.nMTI;
    }

    public String getNFunCd() {
        return this.nFunCd;
    }

    public String getNRecNum() {
        return this.nRecNum;
    }

    public String getNDtTmFlGen() {
        return this.nDtTmFlGen;
    }

    public String getNDtSet() {
        return this.nDtSet;
    }

    public String getNMemInstCd() {
        return this.nMemInstCd;
    }

    public String getNUnFlNm() {
        return this.nUnFlNm;
    }

    public String getNProdCd() {
        return this.nProdCd;
    }

    public String getNFlCatg() {
        return this.nFlCatg;
    }

    public String getNVerNum() {
        return this.nVerNum;
    }

    public String getNFlRejInd() {
        return this.nFlRejInd;
    }

    public String getNFlRejRsnCd() {
        return this.nFlRejRsnCd;
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

    @JacksonXmlProperty(localName="nDtTmFlGen")
    public void setNDtTmFlGen(String nDtTmFlGen) {
        this.nDtTmFlGen = nDtTmFlGen;
    }

    @JacksonXmlProperty(localName="nDtSet")
    public void setNDtSet(String nDtSet) {
        this.nDtSet = nDtSet;
    }

    @JacksonXmlProperty(localName="nMemInstCd")
    public void setNMemInstCd(String nMemInstCd) {
        this.nMemInstCd = nMemInstCd;
    }

    @JacksonXmlProperty(localName="nUnFlNm")
    public void setNUnFlNm(String nUnFlNm) {
        this.nUnFlNm = nUnFlNm;
    }

    @JacksonXmlProperty(localName="nProdCd")
    public void setNProdCd(String nProdCd) {
        this.nProdCd = nProdCd;
    }

    @JacksonXmlProperty(localName="nFlCatg")
    public void setNFlCatg(String nFlCatg) {
        this.nFlCatg = nFlCatg;
    }

    @JacksonXmlProperty(localName="nVerNum")
    public void setNVerNum(String nVerNum) {
        this.nVerNum = nVerNum;
    }

    @JacksonXmlProperty(localName="nFlRejInd")
    public void setNFlRejInd(String nFlRejInd) {
        this.nFlRejInd = nFlRejInd;
    }

    @JacksonXmlProperty(localName="nFlRejRsnCd")
    public void setNFlRejRsnCd(String nFlRejRsnCd) {
        this.nFlRejRsnCd = nFlRejRsnCd;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanXmlHeaderValueObject)) {
            return false;
        }
        JaywanXmlHeaderValueObject other = (JaywanXmlHeaderValueObject)o;
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
        String this$nDtTmFlGen = this.getNDtTmFlGen();
        String other$nDtTmFlGen = other.getNDtTmFlGen();
        if (this$nDtTmFlGen == null ? other$nDtTmFlGen != null : !this$nDtTmFlGen.equals(other$nDtTmFlGen)) {
            return false;
        }
        String this$nDtSet = this.getNDtSet();
        String other$nDtSet = other.getNDtSet();
        if (this$nDtSet == null ? other$nDtSet != null : !this$nDtSet.equals(other$nDtSet)) {
            return false;
        }
        String this$nMemInstCd = this.getNMemInstCd();
        String other$nMemInstCd = other.getNMemInstCd();
        if (this$nMemInstCd == null ? other$nMemInstCd != null : !this$nMemInstCd.equals(other$nMemInstCd)) {
            return false;
        }
        String this$nUnFlNm = this.getNUnFlNm();
        String other$nUnFlNm = other.getNUnFlNm();
        if (this$nUnFlNm == null ? other$nUnFlNm != null : !this$nUnFlNm.equals(other$nUnFlNm)) {
            return false;
        }
        String this$nProdCd = this.getNProdCd();
        String other$nProdCd = other.getNProdCd();
        if (this$nProdCd == null ? other$nProdCd != null : !this$nProdCd.equals(other$nProdCd)) {
            return false;
        }
        String this$nFlCatg = this.getNFlCatg();
        String other$nFlCatg = other.getNFlCatg();
        if (this$nFlCatg == null ? other$nFlCatg != null : !this$nFlCatg.equals(other$nFlCatg)) {
            return false;
        }
        String this$nVerNum = this.getNVerNum();
        String other$nVerNum = other.getNVerNum();
        if (this$nVerNum == null ? other$nVerNum != null : !this$nVerNum.equals(other$nVerNum)) {
            return false;
        }
        String this$nFlRejInd = this.getNFlRejInd();
        String other$nFlRejInd = other.getNFlRejInd();
        if (this$nFlRejInd == null ? other$nFlRejInd != null : !this$nFlRejInd.equals(other$nFlRejInd)) {
            return false;
        }
        String this$nFlRejRsnCd = this.getNFlRejRsnCd();
        String other$nFlRejRsnCd = other.getNFlRejRsnCd();
        return !(this$nFlRejRsnCd == null ? other$nFlRejRsnCd != null : !this$nFlRejRsnCd.equals(other$nFlRejRsnCd));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanXmlHeaderValueObject;
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
        String $nDtTmFlGen = this.getNDtTmFlGen();
        result = result * 59 + ($nDtTmFlGen == null ? 43 : $nDtTmFlGen.hashCode());
        String $nDtSet = this.getNDtSet();
        result = result * 59 + ($nDtSet == null ? 43 : $nDtSet.hashCode());
        String $nMemInstCd = this.getNMemInstCd();
        result = result * 59 + ($nMemInstCd == null ? 43 : $nMemInstCd.hashCode());
        String $nUnFlNm = this.getNUnFlNm();
        result = result * 59 + ($nUnFlNm == null ? 43 : $nUnFlNm.hashCode());
        String $nProdCd = this.getNProdCd();
        result = result * 59 + ($nProdCd == null ? 43 : $nProdCd.hashCode());
        String $nFlCatg = this.getNFlCatg();
        result = result * 59 + ($nFlCatg == null ? 43 : $nFlCatg.hashCode());
        String $nVerNum = this.getNVerNum();
        result = result * 59 + ($nVerNum == null ? 43 : $nVerNum.hashCode());
        String $nFlRejInd = this.getNFlRejInd();
        result = result * 59 + ($nFlRejInd == null ? 43 : $nFlRejInd.hashCode());
        String $nFlRejRsnCd = this.getNFlRejRsnCd();
        result = result * 59 + ($nFlRejRsnCd == null ? 43 : $nFlRejRsnCd.hashCode());
        return result;
    }

    public String toString() {
        return "JaywanXmlHeaderValueObject(nMTI=" + this.getNMTI() + ", nFunCd=" + this.getNFunCd() + ", nRecNum=" + this.getNRecNum() + ", nDtTmFlGen=" + this.getNDtTmFlGen() + ", nDtSet=" + this.getNDtSet() + ", nMemInstCd=" + this.getNMemInstCd() + ", nUnFlNm=" + this.getNUnFlNm() + ", nProdCd=" + this.getNProdCd() + ", nFlCatg=" + this.getNFlCatg() + ", nVerNum=" + this.getNVerNum() + ", nFlRejInd=" + this.getNFlRejInd() + ", nFlRejRsnCd=" + this.getNFlRejRsnCd() + ")";
    }
}

