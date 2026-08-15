/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.UaeSwitchNetworkDataEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="UAE_SWITCH_NETWORK_DATA")
public class UaeSwitchNetworkDataEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USN_SER_NUMBER")
    private int serialNumber;
    @Column(name="USN_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="USN_UPDATED_USER")
    private int updatedUser;
    @Column(name="USN_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="USN_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="USN_INS_CODE")
    private Integer institutionCode;
    @Column(name="USN_PRIMARY_ACC_NO")
    private String primaryAccNo;
    @Column(name="USN_PROC_CODE")
    private String procCode;
    @Column(name="USN_TRANS_AMOUNT")
    private Double txnAmount;
    @Column(name="USN_TRANS_DATE_TIME")
    private String txnDateTime;
    @Column(name="USN_SYSTEM_TRACE_NO")
    private String sysTraceNo;
    @Column(name="USN_LOCAL_TRANS_DATE_TIME")
    private Date localTransDateTime;
    @Column(name="USN_SETTLEMENT_DATE")
    private Date settlementDate;
    @Column(name="USN_CAPTURE_DATE")
    private String captureDate;
    @Column(name="USN_MERCHANT_CATEGORY")
    private String merchantCategory;
    @Column(name="USN_ACQ_COUNTRY_CODE")
    private String acqCountryCode;
    @Column(name="USN_POS_ENTRY")
    private String posEntry;
    @Column(name="USN_CONDITION_CODE")
    private String conditionCode;
    @Column(name="USN_ACQ_INS_ID")
    private String acqInsId;
    @Column(name="USN_ISS_INS_ID")
    private String issInsId;
    @Column(name="USN_FORWARD_INS_ID")
    private String forwardInsId;
    @Column(name="USN_RETRIEVAL_REF_NO")
    private String retrievalRefNo;
    @Column(name="USN_AUTH_ID_RESPONSE")
    private String authIdResponse;
    @Column(name="USN_RESPONSE_CODE")
    private String responseCode;
    @Column(name="USN_CARD_ACC_TERMINAL_ID")
    private String cardAccTerminalId;
    @Column(name="USN_CAD_ACC_ID_CODE")
    private String cadAccIdCode;
    @Column(name="USN_CARD_ACC_ACQ_NAME")
    private String cardAccAcqName;
    @Column(name="USN_TRANS_CURRENCY_CODE")
    private String transCurrencyCode;
    @Column(name="USN_ADDITIONAL_AMOUNT")
    private Double additionalAmount;
    @Column(name="USN_POS_TXN_STATUS")
    private String posTxnStatus;
    @Column(name="USN_PRE_AUTH_TIME_LIMIT")
    private String preAuthLimit;
    @Column(name="USN_TRANS_IDENTIFIER")
    private String transIdentifier;
    @Column(name="USN_GCC_NET_AMOUNT")
    private Double netAmount;
    @Column(name="USN_EXCHANGE_RATE")
    private Double exchangeRate;
    @Column(name="USN_IRF")
    private Double irf;
    @Column(name="USN_PF1")
    private Double pf1;
    @Column(name="USN_PF2")
    private Double pf2;
    @Column(name="USN_VAT")
    private Double vat;
    @Column(name="USN_MTI")
    private Double mt1;
    @Column(name="USN_BF_ACQUIRER")
    private Double bfAcqirer;
    @Column(name="USN_BF_ACQ_VAT")
    private Double bfAcqvat;
    @Column(name="USN_BF_ISSUER")
    private Double bfIssuer;
    @Column(name="USN_ISS_VAT")
    private Double issVat;
    @Column(name="USN_TOKEN_FEE_ACQ")
    private Double tokenFeeAcq;
    @Column(name="USN_TOKEN_FEE_ISSUER")
    private Double tokenFeeIssuer;
    @Column(name="USN_ECOM_FEE_ACQ")
    private Double ecomFeeAcq;
    @Column(name="USN_ECOM_FEE_ISS")
    private Double ecomFeeIss;
    @Column(name="USN_OBS_FEE_ACQ")
    private Double obsFeeAcq;
    @Column(name="USN_OBS_FEE_ISS")
    private Double obsFeeIss;
    @Column(name="USN_ON_US_TOKEN_FEE_ACQ")
    private Double onusTokenFeeAcq;
    @Column(name="USN_ON_US_TOKEN_FEE_ISS")
    private Double onusTokenFeeIss;
    @Column(name="USN_ECOM")
    private String ecom;
    @Column(name="USN_PAR")
    private String par;
    @Column(name="USN_WALLET_ID")
    private String walletId;
    @Column(name="USN_TOKEN_REQ_ID")
    private String tokenTReqId;
    @Column(name="USN_ECI_INDICATOR")
    private String eciIndicator;
    @Column(name="USN_OBS_RESULT_VALUE")
    private String obsResultValue;
    @Column(name="USN_REC_PAYMENT_INDICATOR")
    private String recPaymentIndicator;
    @Column(name="USN_AAV_VALIDATION_RESULT")
    private String aavValidationResult;
    @Column(name="USN_TOKEN_REF_NO")
    private String tokenRefNo;
    @Column(name="USN_PRE_AUTH")
    private String preAuth;
    @Column(name="USN_IRF_MATCH")
    private Character matchStatus;
    @Column(name="USN_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name="USN_IRF_DIFF")
    private Double irfDiff;

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public int getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Integer getInstitutionCode() {
        return this.institutionCode;
    }

    public String getPrimaryAccNo() {
        return this.primaryAccNo;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public String getTxnDateTime() {
        return this.txnDateTime;
    }

    public String getSysTraceNo() {
        return this.sysTraceNo;
    }

    public Date getLocalTransDateTime() {
        return this.localTransDateTime;
    }

    public Date getSettlementDate() {
        return this.settlementDate;
    }

    public String getCaptureDate() {
        return this.captureDate;
    }

    public String getMerchantCategory() {
        return this.merchantCategory;
    }

    public String getAcqCountryCode() {
        return this.acqCountryCode;
    }

    public String getPosEntry() {
        return this.posEntry;
    }

    public String getConditionCode() {
        return this.conditionCode;
    }

    public String getAcqInsId() {
        return this.acqInsId;
    }

    public String getIssInsId() {
        return this.issInsId;
    }

    public String getForwardInsId() {
        return this.forwardInsId;
    }

    public String getRetrievalRefNo() {
        return this.retrievalRefNo;
    }

    public String getAuthIdResponse() {
        return this.authIdResponse;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getCardAccTerminalId() {
        return this.cardAccTerminalId;
    }

    public String getCadAccIdCode() {
        return this.cadAccIdCode;
    }

    public String getCardAccAcqName() {
        return this.cardAccAcqName;
    }

    public String getTransCurrencyCode() {
        return this.transCurrencyCode;
    }

    public Double getAdditionalAmount() {
        return this.additionalAmount;
    }

    public String getPosTxnStatus() {
        return this.posTxnStatus;
    }

    public String getPreAuthLimit() {
        return this.preAuthLimit;
    }

    public String getTransIdentifier() {
        return this.transIdentifier;
    }

    public Double getNetAmount() {
        return this.netAmount;
    }

    public Double getExchangeRate() {
        return this.exchangeRate;
    }

    public Double getIrf() {
        return this.irf;
    }

    public Double getPf1() {
        return this.pf1;
    }

    public Double getPf2() {
        return this.pf2;
    }

    public Double getVat() {
        return this.vat;
    }

    public Double getMt1() {
        return this.mt1;
    }

    public Double getBfAcqirer() {
        return this.bfAcqirer;
    }

    public Double getBfAcqvat() {
        return this.bfAcqvat;
    }

    public Double getBfIssuer() {
        return this.bfIssuer;
    }

    public Double getIssVat() {
        return this.issVat;
    }

    public Double getTokenFeeAcq() {
        return this.tokenFeeAcq;
    }

    public Double getTokenFeeIssuer() {
        return this.tokenFeeIssuer;
    }

    public Double getEcomFeeAcq() {
        return this.ecomFeeAcq;
    }

    public Double getEcomFeeIss() {
        return this.ecomFeeIss;
    }

    public Double getObsFeeAcq() {
        return this.obsFeeAcq;
    }

    public Double getObsFeeIss() {
        return this.obsFeeIss;
    }

    public Double getOnusTokenFeeAcq() {
        return this.onusTokenFeeAcq;
    }

    public Double getOnusTokenFeeIss() {
        return this.onusTokenFeeIss;
    }

    public String getEcom() {
        return this.ecom;
    }

    public String getPar() {
        return this.par;
    }

    public String getWalletId() {
        return this.walletId;
    }

    public String getTokenTReqId() {
        return this.tokenTReqId;
    }

    public String getEciIndicator() {
        return this.eciIndicator;
    }

    public String getObsResultValue() {
        return this.obsResultValue;
    }

    public String getRecPaymentIndicator() {
        return this.recPaymentIndicator;
    }

    public String getAavValidationResult() {
        return this.aavValidationResult;
    }

    public String getTokenRefNo() {
        return this.tokenRefNo;
    }

    public String getPreAuth() {
        return this.preAuth;
    }

    public Character getMatchStatus() {
        return this.matchStatus;
    }

    public Integer getTxnRefNumber() {
        return this.txnRefNumber;
    }

    public Double getIrfDiff() {
        return this.irfDiff;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setPrimaryAccNo(String primaryAccNo) {
        this.primaryAccNo = primaryAccNo;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setTxnDateTime(String txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    public void setSysTraceNo(String sysTraceNo) {
        this.sysTraceNo = sysTraceNo;
    }

    public void setLocalTransDateTime(Date localTransDateTime) {
        this.localTransDateTime = localTransDateTime;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }

    public void setMerchantCategory(String merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public void setAcqCountryCode(String acqCountryCode) {
        this.acqCountryCode = acqCountryCode;
    }

    public void setPosEntry(String posEntry) {
        this.posEntry = posEntry;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    public void setAcqInsId(String acqInsId) {
        this.acqInsId = acqInsId;
    }

    public void setIssInsId(String issInsId) {
        this.issInsId = issInsId;
    }

    public void setForwardInsId(String forwardInsId) {
        this.forwardInsId = forwardInsId;
    }

    public void setRetrievalRefNo(String retrievalRefNo) {
        this.retrievalRefNo = retrievalRefNo;
    }

    public void setAuthIdResponse(String authIdResponse) {
        this.authIdResponse = authIdResponse;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setCardAccTerminalId(String cardAccTerminalId) {
        this.cardAccTerminalId = cardAccTerminalId;
    }

    public void setCadAccIdCode(String cadAccIdCode) {
        this.cadAccIdCode = cadAccIdCode;
    }

    public void setCardAccAcqName(String cardAccAcqName) {
        this.cardAccAcqName = cardAccAcqName;
    }

    public void setTransCurrencyCode(String transCurrencyCode) {
        this.transCurrencyCode = transCurrencyCode;
    }

    public void setAdditionalAmount(Double additionalAmount) {
        this.additionalAmount = additionalAmount;
    }

    public void setPosTxnStatus(String posTxnStatus) {
        this.posTxnStatus = posTxnStatus;
    }

    public void setPreAuthLimit(String preAuthLimit) {
        this.preAuthLimit = preAuthLimit;
    }

    public void setTransIdentifier(String transIdentifier) {
        this.transIdentifier = transIdentifier;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setIrf(Double irf) {
        this.irf = irf;
    }

    public void setPf1(Double pf1) {
        this.pf1 = pf1;
    }

    public void setPf2(Double pf2) {
        this.pf2 = pf2;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public void setMt1(Double mt1) {
        this.mt1 = mt1;
    }

    public void setBfAcqirer(Double bfAcqirer) {
        this.bfAcqirer = bfAcqirer;
    }

    public void setBfAcqvat(Double bfAcqvat) {
        this.bfAcqvat = bfAcqvat;
    }

    public void setBfIssuer(Double bfIssuer) {
        this.bfIssuer = bfIssuer;
    }

    public void setIssVat(Double issVat) {
        this.issVat = issVat;
    }

    public void setTokenFeeAcq(Double tokenFeeAcq) {
        this.tokenFeeAcq = tokenFeeAcq;
    }

    public void setTokenFeeIssuer(Double tokenFeeIssuer) {
        this.tokenFeeIssuer = tokenFeeIssuer;
    }

    public void setEcomFeeAcq(Double ecomFeeAcq) {
        this.ecomFeeAcq = ecomFeeAcq;
    }

    public void setEcomFeeIss(Double ecomFeeIss) {
        this.ecomFeeIss = ecomFeeIss;
    }

    public void setObsFeeAcq(Double obsFeeAcq) {
        this.obsFeeAcq = obsFeeAcq;
    }

    public void setObsFeeIss(Double obsFeeIss) {
        this.obsFeeIss = obsFeeIss;
    }

    public void setOnusTokenFeeAcq(Double onusTokenFeeAcq) {
        this.onusTokenFeeAcq = onusTokenFeeAcq;
    }

    public void setOnusTokenFeeIss(Double onusTokenFeeIss) {
        this.onusTokenFeeIss = onusTokenFeeIss;
    }

    public void setEcom(String ecom) {
        this.ecom = ecom;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public void setTokenTReqId(String tokenTReqId) {
        this.tokenTReqId = tokenTReqId;
    }

    public void setEciIndicator(String eciIndicator) {
        this.eciIndicator = eciIndicator;
    }

    public void setObsResultValue(String obsResultValue) {
        this.obsResultValue = obsResultValue;
    }

    public void setRecPaymentIndicator(String recPaymentIndicator) {
        this.recPaymentIndicator = recPaymentIndicator;
    }

    public void setAavValidationResult(String aavValidationResult) {
        this.aavValidationResult = aavValidationResult;
    }

    public void setTokenRefNo(String tokenRefNo) {
        this.tokenRefNo = tokenRefNo;
    }

    public void setPreAuth(String preAuth) {
        this.preAuth = preAuth;
    }

    public void setMatchStatus(Character matchStatus) {
        this.matchStatus = matchStatus;
    }

    public void setTxnRefNumber(Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }

    public void setIrfDiff(Double irfDiff) {
        this.irfDiff = irfDiff;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UaeSwitchNetworkDataEntity)) {
            return false;
        }
        UaeSwitchNetworkDataEntity other = (UaeSwitchNetworkDataEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSerialNumber() != other.getSerialNumber()) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$institutionCode = this.getInstitutionCode();
        Integer other$institutionCode = other.getInstitutionCode();
        if (this$institutionCode == null ? other$institutionCode != null : !((Object)this$institutionCode).equals(other$institutionCode)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$additionalAmount = this.getAdditionalAmount();
        Double other$additionalAmount = other.getAdditionalAmount();
        if (this$additionalAmount == null ? other$additionalAmount != null : !((Object)this$additionalAmount).equals(other$additionalAmount)) {
            return false;
        }
        Double this$netAmount = this.getNetAmount();
        Double other$netAmount = other.getNetAmount();
        if (this$netAmount == null ? other$netAmount != null : !((Object)this$netAmount).equals(other$netAmount)) {
            return false;
        }
        Double this$exchangeRate = this.getExchangeRate();
        Double other$exchangeRate = other.getExchangeRate();
        if (this$exchangeRate == null ? other$exchangeRate != null : !((Object)this$exchangeRate).equals(other$exchangeRate)) {
            return false;
        }
        Double this$irf = this.getIrf();
        Double other$irf = other.getIrf();
        if (this$irf == null ? other$irf != null : !((Object)this$irf).equals(other$irf)) {
            return false;
        }
        Double this$pf1 = this.getPf1();
        Double other$pf1 = other.getPf1();
        if (this$pf1 == null ? other$pf1 != null : !((Object)this$pf1).equals(other$pf1)) {
            return false;
        }
        Double this$pf2 = this.getPf2();
        Double other$pf2 = other.getPf2();
        if (this$pf2 == null ? other$pf2 != null : !((Object)this$pf2).equals(other$pf2)) {
            return false;
        }
        Double this$vat = this.getVat();
        Double other$vat = other.getVat();
        if (this$vat == null ? other$vat != null : !((Object)this$vat).equals(other$vat)) {
            return false;
        }
        Double this$mt1 = this.getMt1();
        Double other$mt1 = other.getMt1();
        if (this$mt1 == null ? other$mt1 != null : !((Object)this$mt1).equals(other$mt1)) {
            return false;
        }
        Double this$bfAcqirer = this.getBfAcqirer();
        Double other$bfAcqirer = other.getBfAcqirer();
        if (this$bfAcqirer == null ? other$bfAcqirer != null : !((Object)this$bfAcqirer).equals(other$bfAcqirer)) {
            return false;
        }
        Double this$bfAcqvat = this.getBfAcqvat();
        Double other$bfAcqvat = other.getBfAcqvat();
        if (this$bfAcqvat == null ? other$bfAcqvat != null : !((Object)this$bfAcqvat).equals(other$bfAcqvat)) {
            return false;
        }
        Double this$bfIssuer = this.getBfIssuer();
        Double other$bfIssuer = other.getBfIssuer();
        if (this$bfIssuer == null ? other$bfIssuer != null : !((Object)this$bfIssuer).equals(other$bfIssuer)) {
            return false;
        }
        Double this$issVat = this.getIssVat();
        Double other$issVat = other.getIssVat();
        if (this$issVat == null ? other$issVat != null : !((Object)this$issVat).equals(other$issVat)) {
            return false;
        }
        Double this$tokenFeeAcq = this.getTokenFeeAcq();
        Double other$tokenFeeAcq = other.getTokenFeeAcq();
        if (this$tokenFeeAcq == null ? other$tokenFeeAcq != null : !((Object)this$tokenFeeAcq).equals(other$tokenFeeAcq)) {
            return false;
        }
        Double this$tokenFeeIssuer = this.getTokenFeeIssuer();
        Double other$tokenFeeIssuer = other.getTokenFeeIssuer();
        if (this$tokenFeeIssuer == null ? other$tokenFeeIssuer != null : !((Object)this$tokenFeeIssuer).equals(other$tokenFeeIssuer)) {
            return false;
        }
        Double this$ecomFeeAcq = this.getEcomFeeAcq();
        Double other$ecomFeeAcq = other.getEcomFeeAcq();
        if (this$ecomFeeAcq == null ? other$ecomFeeAcq != null : !((Object)this$ecomFeeAcq).equals(other$ecomFeeAcq)) {
            return false;
        }
        Double this$ecomFeeIss = this.getEcomFeeIss();
        Double other$ecomFeeIss = other.getEcomFeeIss();
        if (this$ecomFeeIss == null ? other$ecomFeeIss != null : !((Object)this$ecomFeeIss).equals(other$ecomFeeIss)) {
            return false;
        }
        Double this$obsFeeAcq = this.getObsFeeAcq();
        Double other$obsFeeAcq = other.getObsFeeAcq();
        if (this$obsFeeAcq == null ? other$obsFeeAcq != null : !((Object)this$obsFeeAcq).equals(other$obsFeeAcq)) {
            return false;
        }
        Double this$obsFeeIss = this.getObsFeeIss();
        Double other$obsFeeIss = other.getObsFeeIss();
        if (this$obsFeeIss == null ? other$obsFeeIss != null : !((Object)this$obsFeeIss).equals(other$obsFeeIss)) {
            return false;
        }
        Double this$onusTokenFeeAcq = this.getOnusTokenFeeAcq();
        Double other$onusTokenFeeAcq = other.getOnusTokenFeeAcq();
        if (this$onusTokenFeeAcq == null ? other$onusTokenFeeAcq != null : !((Object)this$onusTokenFeeAcq).equals(other$onusTokenFeeAcq)) {
            return false;
        }
        Double this$onusTokenFeeIss = this.getOnusTokenFeeIss();
        Double other$onusTokenFeeIss = other.getOnusTokenFeeIss();
        if (this$onusTokenFeeIss == null ? other$onusTokenFeeIss != null : !((Object)this$onusTokenFeeIss).equals(other$onusTokenFeeIss)) {
            return false;
        }
        Character this$matchStatus = this.getMatchStatus();
        Character other$matchStatus = other.getMatchStatus();
        if (this$matchStatus == null ? other$matchStatus != null : !((Object)this$matchStatus).equals(other$matchStatus)) {
            return false;
        }
        Integer this$txnRefNumber = this.getTxnRefNumber();
        Integer other$txnRefNumber = other.getTxnRefNumber();
        if (this$txnRefNumber == null ? other$txnRefNumber != null : !((Object)this$txnRefNumber).equals(other$txnRefNumber)) {
            return false;
        }
        Double this$irfDiff = this.getIrfDiff();
        Double other$irfDiff = other.getIrfDiff();
        if (this$irfDiff == null ? other$irfDiff != null : !((Object)this$irfDiff).equals(other$irfDiff)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$primaryAccNo = this.getPrimaryAccNo();
        String other$primaryAccNo = other.getPrimaryAccNo();
        if (this$primaryAccNo == null ? other$primaryAccNo != null : !this$primaryAccNo.equals(other$primaryAccNo)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$txnDateTime = this.getTxnDateTime();
        String other$txnDateTime = other.getTxnDateTime();
        if (this$txnDateTime == null ? other$txnDateTime != null : !this$txnDateTime.equals(other$txnDateTime)) {
            return false;
        }
        String this$sysTraceNo = this.getSysTraceNo();
        String other$sysTraceNo = other.getSysTraceNo();
        if (this$sysTraceNo == null ? other$sysTraceNo != null : !this$sysTraceNo.equals(other$sysTraceNo)) {
            return false;
        }
        Date this$localTransDateTime = this.getLocalTransDateTime();
        Date other$localTransDateTime = other.getLocalTransDateTime();
        if (this$localTransDateTime == null ? other$localTransDateTime != null : !((Object)this$localTransDateTime).equals(other$localTransDateTime)) {
            return false;
        }
        Date this$settlementDate = this.getSettlementDate();
        Date other$settlementDate = other.getSettlementDate();
        if (this$settlementDate == null ? other$settlementDate != null : !((Object)this$settlementDate).equals(other$settlementDate)) {
            return false;
        }
        String this$captureDate = this.getCaptureDate();
        String other$captureDate = other.getCaptureDate();
        if (this$captureDate == null ? other$captureDate != null : !this$captureDate.equals(other$captureDate)) {
            return false;
        }
        String this$merchantCategory = this.getMerchantCategory();
        String other$merchantCategory = other.getMerchantCategory();
        if (this$merchantCategory == null ? other$merchantCategory != null : !this$merchantCategory.equals(other$merchantCategory)) {
            return false;
        }
        String this$acqCountryCode = this.getAcqCountryCode();
        String other$acqCountryCode = other.getAcqCountryCode();
        if (this$acqCountryCode == null ? other$acqCountryCode != null : !this$acqCountryCode.equals(other$acqCountryCode)) {
            return false;
        }
        String this$posEntry = this.getPosEntry();
        String other$posEntry = other.getPosEntry();
        if (this$posEntry == null ? other$posEntry != null : !this$posEntry.equals(other$posEntry)) {
            return false;
        }
        String this$conditionCode = this.getConditionCode();
        String other$conditionCode = other.getConditionCode();
        if (this$conditionCode == null ? other$conditionCode != null : !this$conditionCode.equals(other$conditionCode)) {
            return false;
        }
        String this$acqInsId = this.getAcqInsId();
        String other$acqInsId = other.getAcqInsId();
        if (this$acqInsId == null ? other$acqInsId != null : !this$acqInsId.equals(other$acqInsId)) {
            return false;
        }
        String this$issInsId = this.getIssInsId();
        String other$issInsId = other.getIssInsId();
        if (this$issInsId == null ? other$issInsId != null : !this$issInsId.equals(other$issInsId)) {
            return false;
        }
        String this$forwardInsId = this.getForwardInsId();
        String other$forwardInsId = other.getForwardInsId();
        if (this$forwardInsId == null ? other$forwardInsId != null : !this$forwardInsId.equals(other$forwardInsId)) {
            return false;
        }
        String this$retrievalRefNo = this.getRetrievalRefNo();
        String other$retrievalRefNo = other.getRetrievalRefNo();
        if (this$retrievalRefNo == null ? other$retrievalRefNo != null : !this$retrievalRefNo.equals(other$retrievalRefNo)) {
            return false;
        }
        String this$authIdResponse = this.getAuthIdResponse();
        String other$authIdResponse = other.getAuthIdResponse();
        if (this$authIdResponse == null ? other$authIdResponse != null : !this$authIdResponse.equals(other$authIdResponse)) {
            return false;
        }
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$cardAccTerminalId = this.getCardAccTerminalId();
        String other$cardAccTerminalId = other.getCardAccTerminalId();
        if (this$cardAccTerminalId == null ? other$cardAccTerminalId != null : !this$cardAccTerminalId.equals(other$cardAccTerminalId)) {
            return false;
        }
        String this$cadAccIdCode = this.getCadAccIdCode();
        String other$cadAccIdCode = other.getCadAccIdCode();
        if (this$cadAccIdCode == null ? other$cadAccIdCode != null : !this$cadAccIdCode.equals(other$cadAccIdCode)) {
            return false;
        }
        String this$cardAccAcqName = this.getCardAccAcqName();
        String other$cardAccAcqName = other.getCardAccAcqName();
        if (this$cardAccAcqName == null ? other$cardAccAcqName != null : !this$cardAccAcqName.equals(other$cardAccAcqName)) {
            return false;
        }
        String this$transCurrencyCode = this.getTransCurrencyCode();
        String other$transCurrencyCode = other.getTransCurrencyCode();
        if (this$transCurrencyCode == null ? other$transCurrencyCode != null : !this$transCurrencyCode.equals(other$transCurrencyCode)) {
            return false;
        }
        String this$posTxnStatus = this.getPosTxnStatus();
        String other$posTxnStatus = other.getPosTxnStatus();
        if (this$posTxnStatus == null ? other$posTxnStatus != null : !this$posTxnStatus.equals(other$posTxnStatus)) {
            return false;
        }
        String this$preAuthLimit = this.getPreAuthLimit();
        String other$preAuthLimit = other.getPreAuthLimit();
        if (this$preAuthLimit == null ? other$preAuthLimit != null : !this$preAuthLimit.equals(other$preAuthLimit)) {
            return false;
        }
        String this$transIdentifier = this.getTransIdentifier();
        String other$transIdentifier = other.getTransIdentifier();
        if (this$transIdentifier == null ? other$transIdentifier != null : !this$transIdentifier.equals(other$transIdentifier)) {
            return false;
        }
        String this$ecom = this.getEcom();
        String other$ecom = other.getEcom();
        if (this$ecom == null ? other$ecom != null : !this$ecom.equals(other$ecom)) {
            return false;
        }
        String this$par = this.getPar();
        String other$par = other.getPar();
        if (this$par == null ? other$par != null : !this$par.equals(other$par)) {
            return false;
        }
        String this$walletId = this.getWalletId();
        String other$walletId = other.getWalletId();
        if (this$walletId == null ? other$walletId != null : !this$walletId.equals(other$walletId)) {
            return false;
        }
        String this$tokenTReqId = this.getTokenTReqId();
        String other$tokenTReqId = other.getTokenTReqId();
        if (this$tokenTReqId == null ? other$tokenTReqId != null : !this$tokenTReqId.equals(other$tokenTReqId)) {
            return false;
        }
        String this$eciIndicator = this.getEciIndicator();
        String other$eciIndicator = other.getEciIndicator();
        if (this$eciIndicator == null ? other$eciIndicator != null : !this$eciIndicator.equals(other$eciIndicator)) {
            return false;
        }
        String this$obsResultValue = this.getObsResultValue();
        String other$obsResultValue = other.getObsResultValue();
        if (this$obsResultValue == null ? other$obsResultValue != null : !this$obsResultValue.equals(other$obsResultValue)) {
            return false;
        }
        String this$recPaymentIndicator = this.getRecPaymentIndicator();
        String other$recPaymentIndicator = other.getRecPaymentIndicator();
        if (this$recPaymentIndicator == null ? other$recPaymentIndicator != null : !this$recPaymentIndicator.equals(other$recPaymentIndicator)) {
            return false;
        }
        String this$aavValidationResult = this.getAavValidationResult();
        String other$aavValidationResult = other.getAavValidationResult();
        if (this$aavValidationResult == null ? other$aavValidationResult != null : !this$aavValidationResult.equals(other$aavValidationResult)) {
            return false;
        }
        String this$tokenRefNo = this.getTokenRefNo();
        String other$tokenRefNo = other.getTokenRefNo();
        if (this$tokenRefNo == null ? other$tokenRefNo != null : !this$tokenRefNo.equals(other$tokenRefNo)) {
            return false;
        }
        String this$preAuth = this.getPreAuth();
        String other$preAuth = other.getPreAuth();
        return !(this$preAuth == null ? other$preAuth != null : !this$preAuth.equals(other$preAuth));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UaeSwitchNetworkDataEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        result = result * 59 + this.getUpdatedUser();
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $additionalAmount = this.getAdditionalAmount();
        result = result * 59 + ($additionalAmount == null ? 43 : ((Object)$additionalAmount).hashCode());
        Double $netAmount = this.getNetAmount();
        result = result * 59 + ($netAmount == null ? 43 : ((Object)$netAmount).hashCode());
        Double $exchangeRate = this.getExchangeRate();
        result = result * 59 + ($exchangeRate == null ? 43 : ((Object)$exchangeRate).hashCode());
        Double $irf = this.getIrf();
        result = result * 59 + ($irf == null ? 43 : ((Object)$irf).hashCode());
        Double $pf1 = this.getPf1();
        result = result * 59 + ($pf1 == null ? 43 : ((Object)$pf1).hashCode());
        Double $pf2 = this.getPf2();
        result = result * 59 + ($pf2 == null ? 43 : ((Object)$pf2).hashCode());
        Double $vat = this.getVat();
        result = result * 59 + ($vat == null ? 43 : ((Object)$vat).hashCode());
        Double $mt1 = this.getMt1();
        result = result * 59 + ($mt1 == null ? 43 : ((Object)$mt1).hashCode());
        Double $bfAcqirer = this.getBfAcqirer();
        result = result * 59 + ($bfAcqirer == null ? 43 : ((Object)$bfAcqirer).hashCode());
        Double $bfAcqvat = this.getBfAcqvat();
        result = result * 59 + ($bfAcqvat == null ? 43 : ((Object)$bfAcqvat).hashCode());
        Double $bfIssuer = this.getBfIssuer();
        result = result * 59 + ($bfIssuer == null ? 43 : ((Object)$bfIssuer).hashCode());
        Double $issVat = this.getIssVat();
        result = result * 59 + ($issVat == null ? 43 : ((Object)$issVat).hashCode());
        Double $tokenFeeAcq = this.getTokenFeeAcq();
        result = result * 59 + ($tokenFeeAcq == null ? 43 : ((Object)$tokenFeeAcq).hashCode());
        Double $tokenFeeIssuer = this.getTokenFeeIssuer();
        result = result * 59 + ($tokenFeeIssuer == null ? 43 : ((Object)$tokenFeeIssuer).hashCode());
        Double $ecomFeeAcq = this.getEcomFeeAcq();
        result = result * 59 + ($ecomFeeAcq == null ? 43 : ((Object)$ecomFeeAcq).hashCode());
        Double $ecomFeeIss = this.getEcomFeeIss();
        result = result * 59 + ($ecomFeeIss == null ? 43 : ((Object)$ecomFeeIss).hashCode());
        Double $obsFeeAcq = this.getObsFeeAcq();
        result = result * 59 + ($obsFeeAcq == null ? 43 : ((Object)$obsFeeAcq).hashCode());
        Double $obsFeeIss = this.getObsFeeIss();
        result = result * 59 + ($obsFeeIss == null ? 43 : ((Object)$obsFeeIss).hashCode());
        Double $onusTokenFeeAcq = this.getOnusTokenFeeAcq();
        result = result * 59 + ($onusTokenFeeAcq == null ? 43 : ((Object)$onusTokenFeeAcq).hashCode());
        Double $onusTokenFeeIss = this.getOnusTokenFeeIss();
        result = result * 59 + ($onusTokenFeeIss == null ? 43 : ((Object)$onusTokenFeeIss).hashCode());
        Character $matchStatus = this.getMatchStatus();
        result = result * 59 + ($matchStatus == null ? 43 : ((Object)$matchStatus).hashCode());
        Integer $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + ($txnRefNumber == null ? 43 : ((Object)$txnRefNumber).hashCode());
        Double $irfDiff = this.getIrfDiff();
        result = result * 59 + ($irfDiff == null ? 43 : ((Object)$irfDiff).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $primaryAccNo = this.getPrimaryAccNo();
        result = result * 59 + ($primaryAccNo == null ? 43 : $primaryAccNo.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $txnDateTime = this.getTxnDateTime();
        result = result * 59 + ($txnDateTime == null ? 43 : $txnDateTime.hashCode());
        String $sysTraceNo = this.getSysTraceNo();
        result = result * 59 + ($sysTraceNo == null ? 43 : $sysTraceNo.hashCode());
        Date $localTransDateTime = this.getLocalTransDateTime();
        result = result * 59 + ($localTransDateTime == null ? 43 : ((Object)$localTransDateTime).hashCode());
        Date $settlementDate = this.getSettlementDate();
        result = result * 59 + ($settlementDate == null ? 43 : ((Object)$settlementDate).hashCode());
        String $captureDate = this.getCaptureDate();
        result = result * 59 + ($captureDate == null ? 43 : $captureDate.hashCode());
        String $merchantCategory = this.getMerchantCategory();
        result = result * 59 + ($merchantCategory == null ? 43 : $merchantCategory.hashCode());
        String $acqCountryCode = this.getAcqCountryCode();
        result = result * 59 + ($acqCountryCode == null ? 43 : $acqCountryCode.hashCode());
        String $posEntry = this.getPosEntry();
        result = result * 59 + ($posEntry == null ? 43 : $posEntry.hashCode());
        String $conditionCode = this.getConditionCode();
        result = result * 59 + ($conditionCode == null ? 43 : $conditionCode.hashCode());
        String $acqInsId = this.getAcqInsId();
        result = result * 59 + ($acqInsId == null ? 43 : $acqInsId.hashCode());
        String $issInsId = this.getIssInsId();
        result = result * 59 + ($issInsId == null ? 43 : $issInsId.hashCode());
        String $forwardInsId = this.getForwardInsId();
        result = result * 59 + ($forwardInsId == null ? 43 : $forwardInsId.hashCode());
        String $retrievalRefNo = this.getRetrievalRefNo();
        result = result * 59 + ($retrievalRefNo == null ? 43 : $retrievalRefNo.hashCode());
        String $authIdResponse = this.getAuthIdResponse();
        result = result * 59 + ($authIdResponse == null ? 43 : $authIdResponse.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $cardAccTerminalId = this.getCardAccTerminalId();
        result = result * 59 + ($cardAccTerminalId == null ? 43 : $cardAccTerminalId.hashCode());
        String $cadAccIdCode = this.getCadAccIdCode();
        result = result * 59 + ($cadAccIdCode == null ? 43 : $cadAccIdCode.hashCode());
        String $cardAccAcqName = this.getCardAccAcqName();
        result = result * 59 + ($cardAccAcqName == null ? 43 : $cardAccAcqName.hashCode());
        String $transCurrencyCode = this.getTransCurrencyCode();
        result = result * 59 + ($transCurrencyCode == null ? 43 : $transCurrencyCode.hashCode());
        String $posTxnStatus = this.getPosTxnStatus();
        result = result * 59 + ($posTxnStatus == null ? 43 : $posTxnStatus.hashCode());
        String $preAuthLimit = this.getPreAuthLimit();
        result = result * 59 + ($preAuthLimit == null ? 43 : $preAuthLimit.hashCode());
        String $transIdentifier = this.getTransIdentifier();
        result = result * 59 + ($transIdentifier == null ? 43 : $transIdentifier.hashCode());
        String $ecom = this.getEcom();
        result = result * 59 + ($ecom == null ? 43 : $ecom.hashCode());
        String $par = this.getPar();
        result = result * 59 + ($par == null ? 43 : $par.hashCode());
        String $walletId = this.getWalletId();
        result = result * 59 + ($walletId == null ? 43 : $walletId.hashCode());
        String $tokenTReqId = this.getTokenTReqId();
        result = result * 59 + ($tokenTReqId == null ? 43 : $tokenTReqId.hashCode());
        String $eciIndicator = this.getEciIndicator();
        result = result * 59 + ($eciIndicator == null ? 43 : $eciIndicator.hashCode());
        String $obsResultValue = this.getObsResultValue();
        result = result * 59 + ($obsResultValue == null ? 43 : $obsResultValue.hashCode());
        String $recPaymentIndicator = this.getRecPaymentIndicator();
        result = result * 59 + ($recPaymentIndicator == null ? 43 : $recPaymentIndicator.hashCode());
        String $aavValidationResult = this.getAavValidationResult();
        result = result * 59 + ($aavValidationResult == null ? 43 : $aavValidationResult.hashCode());
        String $tokenRefNo = this.getTokenRefNo();
        result = result * 59 + ($tokenRefNo == null ? 43 : $tokenRefNo.hashCode());
        String $preAuth = this.getPreAuth();
        result = result * 59 + ($preAuth == null ? 43 : $preAuth.hashCode());
        return result;
    }

    public String toString() {
        return "UaeSwitchNetworkDataEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobNumber=" + this.getJobNumber() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", institutionCode=" + this.getInstitutionCode() + ", primaryAccNo=" + this.getPrimaryAccNo() + ", procCode=" + this.getProcCode() + ", txnAmount=" + this.getTxnAmount() + ", txnDateTime=" + this.getTxnDateTime() + ", sysTraceNo=" + this.getSysTraceNo() + ", localTransDateTime=" + String.valueOf(this.getLocalTransDateTime()) + ", settlementDate=" + String.valueOf(this.getSettlementDate()) + ", captureDate=" + this.getCaptureDate() + ", merchantCategory=" + this.getMerchantCategory() + ", acqCountryCode=" + this.getAcqCountryCode() + ", posEntry=" + this.getPosEntry() + ", conditionCode=" + this.getConditionCode() + ", acqInsId=" + this.getAcqInsId() + ", issInsId=" + this.getIssInsId() + ", forwardInsId=" + this.getForwardInsId() + ", retrievalRefNo=" + this.getRetrievalRefNo() + ", authIdResponse=" + this.getAuthIdResponse() + ", responseCode=" + this.getResponseCode() + ", cardAccTerminalId=" + this.getCardAccTerminalId() + ", cadAccIdCode=" + this.getCadAccIdCode() + ", cardAccAcqName=" + this.getCardAccAcqName() + ", transCurrencyCode=" + this.getTransCurrencyCode() + ", additionalAmount=" + this.getAdditionalAmount() + ", posTxnStatus=" + this.getPosTxnStatus() + ", preAuthLimit=" + this.getPreAuthLimit() + ", transIdentifier=" + this.getTransIdentifier() + ", netAmount=" + this.getNetAmount() + ", exchangeRate=" + this.getExchangeRate() + ", irf=" + this.getIrf() + ", pf1=" + this.getPf1() + ", pf2=" + this.getPf2() + ", vat=" + this.getVat() + ", mt1=" + this.getMt1() + ", bfAcqirer=" + this.getBfAcqirer() + ", bfAcqvat=" + this.getBfAcqvat() + ", bfIssuer=" + this.getBfIssuer() + ", issVat=" + this.getIssVat() + ", tokenFeeAcq=" + this.getTokenFeeAcq() + ", tokenFeeIssuer=" + this.getTokenFeeIssuer() + ", ecomFeeAcq=" + this.getEcomFeeAcq() + ", ecomFeeIss=" + this.getEcomFeeIss() + ", obsFeeAcq=" + this.getObsFeeAcq() + ", obsFeeIss=" + this.getObsFeeIss() + ", onusTokenFeeAcq=" + this.getOnusTokenFeeAcq() + ", onusTokenFeeIss=" + this.getOnusTokenFeeIss() + ", ecom=" + this.getEcom() + ", par=" + this.getPar() + ", walletId=" + this.getWalletId() + ", tokenTReqId=" + this.getTokenTReqId() + ", eciIndicator=" + this.getEciIndicator() + ", obsResultValue=" + this.getObsResultValue() + ", recPaymentIndicator=" + this.getRecPaymentIndicator() + ", aavValidationResult=" + this.getAavValidationResult() + ", tokenRefNo=" + this.getTokenRefNo() + ", preAuth=" + this.getPreAuth() + ", matchStatus=" + this.getMatchStatus() + ", txnRefNumber=" + this.getTxnRefNumber() + ", irfDiff=" + this.getIrfDiff() + ")";
    }
}

