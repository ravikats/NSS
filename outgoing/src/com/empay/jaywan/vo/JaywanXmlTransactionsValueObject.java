/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.vo.JaywanXmlTransactionsValueObject
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
 */
package com.empay.jaywan.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class JaywanXmlTransactionsValueObject {
    @JacksonXmlProperty(localName="nMTI")
    private String nMTI;
    @JacksonXmlProperty(localName="nFunCd")
    private String nFunCd;
    @JacksonXmlProperty(localName="nRecNum")
    private String nRecNum;
    @JacksonXmlProperty(localName="nDtTmLcTxn")
    private String nDtTmLcTxn;
    @JacksonXmlProperty(localName="nPAN")
    private String nPAN;
    @JacksonXmlProperty(localName="nARD")
    private String nARD;
    @JacksonXmlProperty(localName="nRRN")
    private String nRRN;
    @JacksonXmlProperty(localName="nAcqInstCd")
    private String nAcqInstCd;
    @JacksonXmlProperty(localName="nApprvlCd")
    private String nApprvlCd;
    @JacksonXmlProperty(localName="nCrdAcptTrmId")
    private String nCrdAcptTrmId;
    @JacksonXmlProperty(localName="nAmtTxn")
    private String nAmtTxn;
    @JacksonXmlProperty(localName="nCcyCdTxn")
    private String nCcyCdTxn;
    @JacksonXmlProperty(localName="nTxnOrgInstCd")
    private String nTxnOrgInstCd;
    @JacksonXmlProperty(localName="nTxnDesInstCd")
    private String nTxnDesInstCd;
    @JacksonXmlProperty(localName="nUnFlNm")
    private String nUnFlNm;
    @JacksonXmlProperty(localName="nDtSet")
    private String nDtSet;
    @JacksonXmlProperty(localName="nSetDCInd")
    private String nSetDCInd;
    @JacksonXmlProperty(localName="nAmtSet")
    private String nAmtSet;
    @JacksonXmlProperty(localName="nCcyCdSet")
    private String nCcyCdSet;
    @JacksonXmlProperty(localName="nConvRtSet")
    private String nConvRtSet;
    @JacksonXmlProperty(localName="nAmtBil")
    private String nAmtBil;
    @JacksonXmlProperty(localName="nConvRtBil")
    private String nConvRtBil;
    @JacksonXmlProperty(localName="nCcyCdBil")
    private String nCcyCdBil;
    @JacksonXmlProperty(localName="nLtPrsntInd")
    private String nLtPrsntInd;
    @JacksonXmlProperty(localName="nProcSts")
    private String nProcSts;
    @JacksonXmlProperty(localName="nRejRsnCd")
    private String nRejRsnCd;
    @JacksonXmlProperty(localName="nAddData")
    private String nAddData;
    @JacksonXmlProperty(localName="nECIInd")
    private String nECIInd;
    @JacksonXmlProperty(localName="nCrdAcpIDCd")
    private String nCrdAcpIDCd;
    @JacksonXmlProperty(localName="nCrdAcpNm")
    private String nCrdAcpNm;
    @JacksonXmlProperty(localName="nCrdAcpCity")
    private String nCrdAcpCity;
    @JacksonXmlProperty(localName="nCrdAcpStNm")
    private String nCrdAcpStNm;
    @JacksonXmlProperty(localName="nCrdAcpCtryCd")
    private String nCrdAcpCtryCd;
    @JacksonXmlProperty(localName="nRecrPymtCd")
    private String nRecrPymtCd;
    @JacksonXmlProperty(localName="nCrdAcpBussCd")
    private String nCrdAcpBussCd;
    @JacksonXmlProperty(localName="nProcCd")
    private String nProcCd;
    @JacksonXmlProperty(localName="nPosEntMode")
    private String nPosEntMode;
    @JacksonXmlProperty(localName="nPosCondCd")
    private String nPosCondCd;
    @JacksonXmlProperty(localName="nActnCd")
    private String nActnCd;
    @JacksonXmlProperty(localName="nFulParInd")
    private String nFulParInd;
    @JacksonXmlProperty(localName="nIntrnTrackNum")
    private String nIntrnTrackNum;

    public String getNMTI() {
        return this.nMTI;
    }

    public String getNFunCd() {
        return this.nFunCd;
    }

    public String getNRecNum() {
        return this.nRecNum;
    }

    public String getNDtTmLcTxn() {
        return this.nDtTmLcTxn;
    }

    public String getNPAN() {
        return this.nPAN;
    }

    public String getNARD() {
        return this.nARD;
    }

    public String getNRRN() {
        return this.nRRN;
    }

    public String getNAcqInstCd() {
        return this.nAcqInstCd;
    }

    public String getNApprvlCd() {
        return this.nApprvlCd;
    }

    public String getNCrdAcptTrmId() {
        return this.nCrdAcptTrmId;
    }

    public String getNAmtTxn() {
        return this.nAmtTxn;
    }

    public String getNCcyCdTxn() {
        return this.nCcyCdTxn;
    }

    public String getNTxnOrgInstCd() {
        return this.nTxnOrgInstCd;
    }

    public String getNTxnDesInstCd() {
        return this.nTxnDesInstCd;
    }

    public String getNUnFlNm() {
        return this.nUnFlNm;
    }

    public String getNDtSet() {
        return this.nDtSet;
    }

    public String getNSetDCInd() {
        return this.nSetDCInd;
    }

    public String getNAmtSet() {
        return this.nAmtSet;
    }

    public String getNCcyCdSet() {
        return this.nCcyCdSet;
    }

    public String getNConvRtSet() {
        return this.nConvRtSet;
    }

    public String getNAmtBil() {
        return this.nAmtBil;
    }

    public String getNConvRtBil() {
        return this.nConvRtBil;
    }

    public String getNCcyCdBil() {
        return this.nCcyCdBil;
    }

    public String getNLtPrsntInd() {
        return this.nLtPrsntInd;
    }

    public String getNProcSts() {
        return this.nProcSts;
    }

    public String getNRejRsnCd() {
        return this.nRejRsnCd;
    }

    public String getNAddData() {
        return this.nAddData;
    }

    public String getNECIInd() {
        return this.nECIInd;
    }

    public String getNCrdAcpIDCd() {
        return this.nCrdAcpIDCd;
    }

    public String getNCrdAcpNm() {
        return this.nCrdAcpNm;
    }

    public String getNCrdAcpCity() {
        return this.nCrdAcpCity;
    }

    public String getNCrdAcpStNm() {
        return this.nCrdAcpStNm;
    }

    public String getNCrdAcpCtryCd() {
        return this.nCrdAcpCtryCd;
    }

    public String getNRecrPymtCd() {
        return this.nRecrPymtCd;
    }

    public String getNCrdAcpBussCd() {
        return this.nCrdAcpBussCd;
    }

    public String getNProcCd() {
        return this.nProcCd;
    }

    public String getNPosEntMode() {
        return this.nPosEntMode;
    }

    public String getNPosCondCd() {
        return this.nPosCondCd;
    }

    public String getNActnCd() {
        return this.nActnCd;
    }

    public String getNFulParInd() {
        return this.nFulParInd;
    }

    public String getNIntrnTrackNum() {
        return this.nIntrnTrackNum;
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

    @JacksonXmlProperty(localName="nDtTmLcTxn")
    public void setNDtTmLcTxn(String nDtTmLcTxn) {
        this.nDtTmLcTxn = nDtTmLcTxn;
    }

    @JacksonXmlProperty(localName="nPAN")
    public void setNPAN(String nPAN) {
        this.nPAN = nPAN;
    }

    @JacksonXmlProperty(localName="nARD")
    public void setNARD(String nARD) {
        this.nARD = nARD;
    }

    @JacksonXmlProperty(localName="nRRN")
    public void setNRRN(String nRRN) {
        this.nRRN = nRRN;
    }

    @JacksonXmlProperty(localName="nAcqInstCd")
    public void setNAcqInstCd(String nAcqInstCd) {
        this.nAcqInstCd = nAcqInstCd;
    }

    @JacksonXmlProperty(localName="nApprvlCd")
    public void setNApprvlCd(String nApprvlCd) {
        this.nApprvlCd = nApprvlCd;
    }

    @JacksonXmlProperty(localName="nCrdAcptTrmId")
    public void setNCrdAcptTrmId(String nCrdAcptTrmId) {
        this.nCrdAcptTrmId = nCrdAcptTrmId;
    }

    @JacksonXmlProperty(localName="nAmtTxn")
    public void setNAmtTxn(String nAmtTxn) {
        this.nAmtTxn = nAmtTxn;
    }

    @JacksonXmlProperty(localName="nCcyCdTxn")
    public void setNCcyCdTxn(String nCcyCdTxn) {
        this.nCcyCdTxn = nCcyCdTxn;
    }

    @JacksonXmlProperty(localName="nTxnOrgInstCd")
    public void setNTxnOrgInstCd(String nTxnOrgInstCd) {
        this.nTxnOrgInstCd = nTxnOrgInstCd;
    }

    @JacksonXmlProperty(localName="nTxnDesInstCd")
    public void setNTxnDesInstCd(String nTxnDesInstCd) {
        this.nTxnDesInstCd = nTxnDesInstCd;
    }

    @JacksonXmlProperty(localName="nUnFlNm")
    public void setNUnFlNm(String nUnFlNm) {
        this.nUnFlNm = nUnFlNm;
    }

    @JacksonXmlProperty(localName="nDtSet")
    public void setNDtSet(String nDtSet) {
        this.nDtSet = nDtSet;
    }

    @JacksonXmlProperty(localName="nSetDCInd")
    public void setNSetDCInd(String nSetDCInd) {
        this.nSetDCInd = nSetDCInd;
    }

    @JacksonXmlProperty(localName="nAmtSet")
    public void setNAmtSet(String nAmtSet) {
        this.nAmtSet = nAmtSet;
    }

    @JacksonXmlProperty(localName="nCcyCdSet")
    public void setNCcyCdSet(String nCcyCdSet) {
        this.nCcyCdSet = nCcyCdSet;
    }

    @JacksonXmlProperty(localName="nConvRtSet")
    public void setNConvRtSet(String nConvRtSet) {
        this.nConvRtSet = nConvRtSet;
    }

    @JacksonXmlProperty(localName="nAmtBil")
    public void setNAmtBil(String nAmtBil) {
        this.nAmtBil = nAmtBil;
    }

    @JacksonXmlProperty(localName="nConvRtBil")
    public void setNConvRtBil(String nConvRtBil) {
        this.nConvRtBil = nConvRtBil;
    }

    @JacksonXmlProperty(localName="nCcyCdBil")
    public void setNCcyCdBil(String nCcyCdBil) {
        this.nCcyCdBil = nCcyCdBil;
    }

    @JacksonXmlProperty(localName="nLtPrsntInd")
    public void setNLtPrsntInd(String nLtPrsntInd) {
        this.nLtPrsntInd = nLtPrsntInd;
    }

    @JacksonXmlProperty(localName="nProcSts")
    public void setNProcSts(String nProcSts) {
        this.nProcSts = nProcSts;
    }

    @JacksonXmlProperty(localName="nRejRsnCd")
    public void setNRejRsnCd(String nRejRsnCd) {
        this.nRejRsnCd = nRejRsnCd;
    }

    @JacksonXmlProperty(localName="nAddData")
    public void setNAddData(String nAddData) {
        this.nAddData = nAddData;
    }

    @JacksonXmlProperty(localName="nECIInd")
    public void setNECIInd(String nECIInd) {
        this.nECIInd = nECIInd;
    }

    @JacksonXmlProperty(localName="nCrdAcpIDCd")
    public void setNCrdAcpIDCd(String nCrdAcpIDCd) {
        this.nCrdAcpIDCd = nCrdAcpIDCd;
    }

    @JacksonXmlProperty(localName="nCrdAcpNm")
    public void setNCrdAcpNm(String nCrdAcpNm) {
        this.nCrdAcpNm = nCrdAcpNm;
    }

    @JacksonXmlProperty(localName="nCrdAcpCity")
    public void setNCrdAcpCity(String nCrdAcpCity) {
        this.nCrdAcpCity = nCrdAcpCity;
    }

    @JacksonXmlProperty(localName="nCrdAcpStNm")
    public void setNCrdAcpStNm(String nCrdAcpStNm) {
        this.nCrdAcpStNm = nCrdAcpStNm;
    }

    @JacksonXmlProperty(localName="nCrdAcpCtryCd")
    public void setNCrdAcpCtryCd(String nCrdAcpCtryCd) {
        this.nCrdAcpCtryCd = nCrdAcpCtryCd;
    }

    @JacksonXmlProperty(localName="nRecrPymtCd")
    public void setNRecrPymtCd(String nRecrPymtCd) {
        this.nRecrPymtCd = nRecrPymtCd;
    }

    @JacksonXmlProperty(localName="nCrdAcpBussCd")
    public void setNCrdAcpBussCd(String nCrdAcpBussCd) {
        this.nCrdAcpBussCd = nCrdAcpBussCd;
    }

    @JacksonXmlProperty(localName="nProcCd")
    public void setNProcCd(String nProcCd) {
        this.nProcCd = nProcCd;
    }

    @JacksonXmlProperty(localName="nPosEntMode")
    public void setNPosEntMode(String nPosEntMode) {
        this.nPosEntMode = nPosEntMode;
    }

    @JacksonXmlProperty(localName="nPosCondCd")
    public void setNPosCondCd(String nPosCondCd) {
        this.nPosCondCd = nPosCondCd;
    }

    @JacksonXmlProperty(localName="nActnCd")
    public void setNActnCd(String nActnCd) {
        this.nActnCd = nActnCd;
    }

    @JacksonXmlProperty(localName="nFulParInd")
    public void setNFulParInd(String nFulParInd) {
        this.nFulParInd = nFulParInd;
    }

    @JacksonXmlProperty(localName="nIntrnTrackNum")
    public void setNIntrnTrackNum(String nIntrnTrackNum) {
        this.nIntrnTrackNum = nIntrnTrackNum;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanXmlTransactionsValueObject)) {
            return false;
        }
        JaywanXmlTransactionsValueObject other = (JaywanXmlTransactionsValueObject)o;
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
        String this$nDtTmLcTxn = this.getNDtTmLcTxn();
        String other$nDtTmLcTxn = other.getNDtTmLcTxn();
        if (this$nDtTmLcTxn == null ? other$nDtTmLcTxn != null : !this$nDtTmLcTxn.equals(other$nDtTmLcTxn)) {
            return false;
        }
        String this$nPAN = this.getNPAN();
        String other$nPAN = other.getNPAN();
        if (this$nPAN == null ? other$nPAN != null : !this$nPAN.equals(other$nPAN)) {
            return false;
        }
        String this$nARD = this.getNARD();
        String other$nARD = other.getNARD();
        if (this$nARD == null ? other$nARD != null : !this$nARD.equals(other$nARD)) {
            return false;
        }
        String this$nRRN = this.getNRRN();
        String other$nRRN = other.getNRRN();
        if (this$nRRN == null ? other$nRRN != null : !this$nRRN.equals(other$nRRN)) {
            return false;
        }
        String this$nAcqInstCd = this.getNAcqInstCd();
        String other$nAcqInstCd = other.getNAcqInstCd();
        if (this$nAcqInstCd == null ? other$nAcqInstCd != null : !this$nAcqInstCd.equals(other$nAcqInstCd)) {
            return false;
        }
        String this$nApprvlCd = this.getNApprvlCd();
        String other$nApprvlCd = other.getNApprvlCd();
        if (this$nApprvlCd == null ? other$nApprvlCd != null : !this$nApprvlCd.equals(other$nApprvlCd)) {
            return false;
        }
        String this$nCrdAcptTrmId = this.getNCrdAcptTrmId();
        String other$nCrdAcptTrmId = other.getNCrdAcptTrmId();
        if (this$nCrdAcptTrmId == null ? other$nCrdAcptTrmId != null : !this$nCrdAcptTrmId.equals(other$nCrdAcptTrmId)) {
            return false;
        }
        String this$nAmtTxn = this.getNAmtTxn();
        String other$nAmtTxn = other.getNAmtTxn();
        if (this$nAmtTxn == null ? other$nAmtTxn != null : !this$nAmtTxn.equals(other$nAmtTxn)) {
            return false;
        }
        String this$nCcyCdTxn = this.getNCcyCdTxn();
        String other$nCcyCdTxn = other.getNCcyCdTxn();
        if (this$nCcyCdTxn == null ? other$nCcyCdTxn != null : !this$nCcyCdTxn.equals(other$nCcyCdTxn)) {
            return false;
        }
        String this$nTxnOrgInstCd = this.getNTxnOrgInstCd();
        String other$nTxnOrgInstCd = other.getNTxnOrgInstCd();
        if (this$nTxnOrgInstCd == null ? other$nTxnOrgInstCd != null : !this$nTxnOrgInstCd.equals(other$nTxnOrgInstCd)) {
            return false;
        }
        String this$nTxnDesInstCd = this.getNTxnDesInstCd();
        String other$nTxnDesInstCd = other.getNTxnDesInstCd();
        if (this$nTxnDesInstCd == null ? other$nTxnDesInstCd != null : !this$nTxnDesInstCd.equals(other$nTxnDesInstCd)) {
            return false;
        }
        String this$nUnFlNm = this.getNUnFlNm();
        String other$nUnFlNm = other.getNUnFlNm();
        if (this$nUnFlNm == null ? other$nUnFlNm != null : !this$nUnFlNm.equals(other$nUnFlNm)) {
            return false;
        }
        String this$nDtSet = this.getNDtSet();
        String other$nDtSet = other.getNDtSet();
        if (this$nDtSet == null ? other$nDtSet != null : !this$nDtSet.equals(other$nDtSet)) {
            return false;
        }
        String this$nSetDCInd = this.getNSetDCInd();
        String other$nSetDCInd = other.getNSetDCInd();
        if (this$nSetDCInd == null ? other$nSetDCInd != null : !this$nSetDCInd.equals(other$nSetDCInd)) {
            return false;
        }
        String this$nAmtSet = this.getNAmtSet();
        String other$nAmtSet = other.getNAmtSet();
        if (this$nAmtSet == null ? other$nAmtSet != null : !this$nAmtSet.equals(other$nAmtSet)) {
            return false;
        }
        String this$nCcyCdSet = this.getNCcyCdSet();
        String other$nCcyCdSet = other.getNCcyCdSet();
        if (this$nCcyCdSet == null ? other$nCcyCdSet != null : !this$nCcyCdSet.equals(other$nCcyCdSet)) {
            return false;
        }
        String this$nConvRtSet = this.getNConvRtSet();
        String other$nConvRtSet = other.getNConvRtSet();
        if (this$nConvRtSet == null ? other$nConvRtSet != null : !this$nConvRtSet.equals(other$nConvRtSet)) {
            return false;
        }
        String this$nAmtBil = this.getNAmtBil();
        String other$nAmtBil = other.getNAmtBil();
        if (this$nAmtBil == null ? other$nAmtBil != null : !this$nAmtBil.equals(other$nAmtBil)) {
            return false;
        }
        String this$nConvRtBil = this.getNConvRtBil();
        String other$nConvRtBil = other.getNConvRtBil();
        if (this$nConvRtBil == null ? other$nConvRtBil != null : !this$nConvRtBil.equals(other$nConvRtBil)) {
            return false;
        }
        String this$nCcyCdBil = this.getNCcyCdBil();
        String other$nCcyCdBil = other.getNCcyCdBil();
        if (this$nCcyCdBil == null ? other$nCcyCdBil != null : !this$nCcyCdBil.equals(other$nCcyCdBil)) {
            return false;
        }
        String this$nLtPrsntInd = this.getNLtPrsntInd();
        String other$nLtPrsntInd = other.getNLtPrsntInd();
        if (this$nLtPrsntInd == null ? other$nLtPrsntInd != null : !this$nLtPrsntInd.equals(other$nLtPrsntInd)) {
            return false;
        }
        String this$nProcSts = this.getNProcSts();
        String other$nProcSts = other.getNProcSts();
        if (this$nProcSts == null ? other$nProcSts != null : !this$nProcSts.equals(other$nProcSts)) {
            return false;
        }
        String this$nRejRsnCd = this.getNRejRsnCd();
        String other$nRejRsnCd = other.getNRejRsnCd();
        if (this$nRejRsnCd == null ? other$nRejRsnCd != null : !this$nRejRsnCd.equals(other$nRejRsnCd)) {
            return false;
        }
        String this$nAddData = this.getNAddData();
        String other$nAddData = other.getNAddData();
        if (this$nAddData == null ? other$nAddData != null : !this$nAddData.equals(other$nAddData)) {
            return false;
        }
        String this$nECIInd = this.getNECIInd();
        String other$nECIInd = other.getNECIInd();
        if (this$nECIInd == null ? other$nECIInd != null : !this$nECIInd.equals(other$nECIInd)) {
            return false;
        }
        String this$nCrdAcpIDCd = this.getNCrdAcpIDCd();
        String other$nCrdAcpIDCd = other.getNCrdAcpIDCd();
        if (this$nCrdAcpIDCd == null ? other$nCrdAcpIDCd != null : !this$nCrdAcpIDCd.equals(other$nCrdAcpIDCd)) {
            return false;
        }
        String this$nCrdAcpNm = this.getNCrdAcpNm();
        String other$nCrdAcpNm = other.getNCrdAcpNm();
        if (this$nCrdAcpNm == null ? other$nCrdAcpNm != null : !this$nCrdAcpNm.equals(other$nCrdAcpNm)) {
            return false;
        }
        String this$nCrdAcpCity = this.getNCrdAcpCity();
        String other$nCrdAcpCity = other.getNCrdAcpCity();
        if (this$nCrdAcpCity == null ? other$nCrdAcpCity != null : !this$nCrdAcpCity.equals(other$nCrdAcpCity)) {
            return false;
        }
        String this$nCrdAcpStNm = this.getNCrdAcpStNm();
        String other$nCrdAcpStNm = other.getNCrdAcpStNm();
        if (this$nCrdAcpStNm == null ? other$nCrdAcpStNm != null : !this$nCrdAcpStNm.equals(other$nCrdAcpStNm)) {
            return false;
        }
        String this$nCrdAcpCtryCd = this.getNCrdAcpCtryCd();
        String other$nCrdAcpCtryCd = other.getNCrdAcpCtryCd();
        if (this$nCrdAcpCtryCd == null ? other$nCrdAcpCtryCd != null : !this$nCrdAcpCtryCd.equals(other$nCrdAcpCtryCd)) {
            return false;
        }
        String this$nRecrPymtCd = this.getNRecrPymtCd();
        String other$nRecrPymtCd = other.getNRecrPymtCd();
        if (this$nRecrPymtCd == null ? other$nRecrPymtCd != null : !this$nRecrPymtCd.equals(other$nRecrPymtCd)) {
            return false;
        }
        String this$nCrdAcpBussCd = this.getNCrdAcpBussCd();
        String other$nCrdAcpBussCd = other.getNCrdAcpBussCd();
        if (this$nCrdAcpBussCd == null ? other$nCrdAcpBussCd != null : !this$nCrdAcpBussCd.equals(other$nCrdAcpBussCd)) {
            return false;
        }
        String this$nProcCd = this.getNProcCd();
        String other$nProcCd = other.getNProcCd();
        if (this$nProcCd == null ? other$nProcCd != null : !this$nProcCd.equals(other$nProcCd)) {
            return false;
        }
        String this$nPosEntMode = this.getNPosEntMode();
        String other$nPosEntMode = other.getNPosEntMode();
        if (this$nPosEntMode == null ? other$nPosEntMode != null : !this$nPosEntMode.equals(other$nPosEntMode)) {
            return false;
        }
        String this$nPosCondCd = this.getNPosCondCd();
        String other$nPosCondCd = other.getNPosCondCd();
        if (this$nPosCondCd == null ? other$nPosCondCd != null : !this$nPosCondCd.equals(other$nPosCondCd)) {
            return false;
        }
        String this$nActnCd = this.getNActnCd();
        String other$nActnCd = other.getNActnCd();
        if (this$nActnCd == null ? other$nActnCd != null : !this$nActnCd.equals(other$nActnCd)) {
            return false;
        }
        String this$nFulParInd = this.getNFulParInd();
        String other$nFulParInd = other.getNFulParInd();
        if (this$nFulParInd == null ? other$nFulParInd != null : !this$nFulParInd.equals(other$nFulParInd)) {
            return false;
        }
        String this$nIntrnTrackNum = this.getNIntrnTrackNum();
        String other$nIntrnTrackNum = other.getNIntrnTrackNum();
        return !(this$nIntrnTrackNum == null ? other$nIntrnTrackNum != null : !this$nIntrnTrackNum.equals(other$nIntrnTrackNum));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanXmlTransactionsValueObject;
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
        String $nDtTmLcTxn = this.getNDtTmLcTxn();
        result = result * 59 + ($nDtTmLcTxn == null ? 43 : $nDtTmLcTxn.hashCode());
        String $nPAN = this.getNPAN();
        result = result * 59 + ($nPAN == null ? 43 : $nPAN.hashCode());
        String $nARD = this.getNARD();
        result = result * 59 + ($nARD == null ? 43 : $nARD.hashCode());
        String $nRRN = this.getNRRN();
        result = result * 59 + ($nRRN == null ? 43 : $nRRN.hashCode());
        String $nAcqInstCd = this.getNAcqInstCd();
        result = result * 59 + ($nAcqInstCd == null ? 43 : $nAcqInstCd.hashCode());
        String $nApprvlCd = this.getNApprvlCd();
        result = result * 59 + ($nApprvlCd == null ? 43 : $nApprvlCd.hashCode());
        String $nCrdAcptTrmId = this.getNCrdAcptTrmId();
        result = result * 59 + ($nCrdAcptTrmId == null ? 43 : $nCrdAcptTrmId.hashCode());
        String $nAmtTxn = this.getNAmtTxn();
        result = result * 59 + ($nAmtTxn == null ? 43 : $nAmtTxn.hashCode());
        String $nCcyCdTxn = this.getNCcyCdTxn();
        result = result * 59 + ($nCcyCdTxn == null ? 43 : $nCcyCdTxn.hashCode());
        String $nTxnOrgInstCd = this.getNTxnOrgInstCd();
        result = result * 59 + ($nTxnOrgInstCd == null ? 43 : $nTxnOrgInstCd.hashCode());
        String $nTxnDesInstCd = this.getNTxnDesInstCd();
        result = result * 59 + ($nTxnDesInstCd == null ? 43 : $nTxnDesInstCd.hashCode());
        String $nUnFlNm = this.getNUnFlNm();
        result = result * 59 + ($nUnFlNm == null ? 43 : $nUnFlNm.hashCode());
        String $nDtSet = this.getNDtSet();
        result = result * 59 + ($nDtSet == null ? 43 : $nDtSet.hashCode());
        String $nSetDCInd = this.getNSetDCInd();
        result = result * 59 + ($nSetDCInd == null ? 43 : $nSetDCInd.hashCode());
        String $nAmtSet = this.getNAmtSet();
        result = result * 59 + ($nAmtSet == null ? 43 : $nAmtSet.hashCode());
        String $nCcyCdSet = this.getNCcyCdSet();
        result = result * 59 + ($nCcyCdSet == null ? 43 : $nCcyCdSet.hashCode());
        String $nConvRtSet = this.getNConvRtSet();
        result = result * 59 + ($nConvRtSet == null ? 43 : $nConvRtSet.hashCode());
        String $nAmtBil = this.getNAmtBil();
        result = result * 59 + ($nAmtBil == null ? 43 : $nAmtBil.hashCode());
        String $nConvRtBil = this.getNConvRtBil();
        result = result * 59 + ($nConvRtBil == null ? 43 : $nConvRtBil.hashCode());
        String $nCcyCdBil = this.getNCcyCdBil();
        result = result * 59 + ($nCcyCdBil == null ? 43 : $nCcyCdBil.hashCode());
        String $nLtPrsntInd = this.getNLtPrsntInd();
        result = result * 59 + ($nLtPrsntInd == null ? 43 : $nLtPrsntInd.hashCode());
        String $nProcSts = this.getNProcSts();
        result = result * 59 + ($nProcSts == null ? 43 : $nProcSts.hashCode());
        String $nRejRsnCd = this.getNRejRsnCd();
        result = result * 59 + ($nRejRsnCd == null ? 43 : $nRejRsnCd.hashCode());
        String $nAddData = this.getNAddData();
        result = result * 59 + ($nAddData == null ? 43 : $nAddData.hashCode());
        String $nECIInd = this.getNECIInd();
        result = result * 59 + ($nECIInd == null ? 43 : $nECIInd.hashCode());
        String $nCrdAcpIDCd = this.getNCrdAcpIDCd();
        result = result * 59 + ($nCrdAcpIDCd == null ? 43 : $nCrdAcpIDCd.hashCode());
        String $nCrdAcpNm = this.getNCrdAcpNm();
        result = result * 59 + ($nCrdAcpNm == null ? 43 : $nCrdAcpNm.hashCode());
        String $nCrdAcpCity = this.getNCrdAcpCity();
        result = result * 59 + ($nCrdAcpCity == null ? 43 : $nCrdAcpCity.hashCode());
        String $nCrdAcpStNm = this.getNCrdAcpStNm();
        result = result * 59 + ($nCrdAcpStNm == null ? 43 : $nCrdAcpStNm.hashCode());
        String $nCrdAcpCtryCd = this.getNCrdAcpCtryCd();
        result = result * 59 + ($nCrdAcpCtryCd == null ? 43 : $nCrdAcpCtryCd.hashCode());
        String $nRecrPymtCd = this.getNRecrPymtCd();
        result = result * 59 + ($nRecrPymtCd == null ? 43 : $nRecrPymtCd.hashCode());
        String $nCrdAcpBussCd = this.getNCrdAcpBussCd();
        result = result * 59 + ($nCrdAcpBussCd == null ? 43 : $nCrdAcpBussCd.hashCode());
        String $nProcCd = this.getNProcCd();
        result = result * 59 + ($nProcCd == null ? 43 : $nProcCd.hashCode());
        String $nPosEntMode = this.getNPosEntMode();
        result = result * 59 + ($nPosEntMode == null ? 43 : $nPosEntMode.hashCode());
        String $nPosCondCd = this.getNPosCondCd();
        result = result * 59 + ($nPosCondCd == null ? 43 : $nPosCondCd.hashCode());
        String $nActnCd = this.getNActnCd();
        result = result * 59 + ($nActnCd == null ? 43 : $nActnCd.hashCode());
        String $nFulParInd = this.getNFulParInd();
        result = result * 59 + ($nFulParInd == null ? 43 : $nFulParInd.hashCode());
        String $nIntrnTrackNum = this.getNIntrnTrackNum();
        result = result * 59 + ($nIntrnTrackNum == null ? 43 : $nIntrnTrackNum.hashCode());
        return result;
    }

    public String toString() {
        return "JaywanXmlTransactionsValueObject(nMTI=" + this.getNMTI() + ", nFunCd=" + this.getNFunCd() + ", nRecNum=" + this.getNRecNum() + ", nDtTmLcTxn=" + this.getNDtTmLcTxn() + ", nPAN=" + this.getNPAN() + ", nARD=" + this.getNARD() + ", nRRN=" + this.getNRRN() + ", nAcqInstCd=" + this.getNAcqInstCd() + ", nApprvlCd=" + this.getNApprvlCd() + ", nCrdAcptTrmId=" + this.getNCrdAcptTrmId() + ", nAmtTxn=" + this.getNAmtTxn() + ", nCcyCdTxn=" + this.getNCcyCdTxn() + ", nTxnOrgInstCd=" + this.getNTxnOrgInstCd() + ", nTxnDesInstCd=" + this.getNTxnDesInstCd() + ", nUnFlNm=" + this.getNUnFlNm() + ", nDtSet=" + this.getNDtSet() + ", nSetDCInd=" + this.getNSetDCInd() + ", nAmtSet=" + this.getNAmtSet() + ", nCcyCdSet=" + this.getNCcyCdSet() + ", nConvRtSet=" + this.getNConvRtSet() + ", nAmtBil=" + this.getNAmtBil() + ", nConvRtBil=" + this.getNConvRtBil() + ", nCcyCdBil=" + this.getNCcyCdBil() + ", nLtPrsntInd=" + this.getNLtPrsntInd() + ", nProcSts=" + this.getNProcSts() + ", nRejRsnCd=" + this.getNRejRsnCd() + ", nAddData=" + this.getNAddData() + ", nECIInd=" + this.getNECIInd() + ", nCrdAcpIDCd=" + this.getNCrdAcpIDCd() + ", nCrdAcpNm=" + this.getNCrdAcpNm() + ", nCrdAcpCity=" + this.getNCrdAcpCity() + ", nCrdAcpStNm=" + this.getNCrdAcpStNm() + ", nCrdAcpCtryCd=" + this.getNCrdAcpCtryCd() + ", nRecrPymtCd=" + this.getNRecrPymtCd() + ", nCrdAcpBussCd=" + this.getNCrdAcpBussCd() + ", nProcCd=" + this.getNProcCd() + ", nPosEntMode=" + this.getNPosEntMode() + ", nPosCondCd=" + this.getNPosCondCd() + ", nActnCd=" + this.getNActnCd() + ", nFulParInd=" + this.getNFulParInd() + ", nIntrnTrackNum=" + this.getNIntrnTrackNum() + ")";
    }
}

