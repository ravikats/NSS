/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewMcRejectionDetails
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="VW_MC_REJECTION_DATA")
public class ViewMcRejectionDetails {
    @Id
    @Column(name="MRJ_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MCT_TXN_TYPE")
    private String txnType;
    @Column(name="MRJ_MSG_TYPE_ID")
    private String msgTypeId;
    @Column(name="MRJ_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MRJ_PROC_CODE")
    private String procCode;
    @Column(name="MRJ_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MCT_SCHG_AMOUNT")
    private Double schgAmount;
    @Column(name="MRJ_LOCAL_DATE_TIME")
    private LocalDate localDateTime;
    @Column(name="MCT_EXPIRY_DATE")
    private String expiryDate;
    @Column(name="MCT_POS_DATA_CODE")
    private String posDataCode;
    @Column(name="MCT_FUNC_CODE")
    private String functionCode;
    @Column(name="MCT_MSG_REASON_CODE")
    private String reasonCode;
    @Column(name="MCT_MCC")
    private String mcc;
    @Column(name="MRJ_ACQ_REF_NUMBER")
    private String acqRefNumber;
    @Column(name="MRJ_ACQ_INST_ID_CODE")
    private String acqInstIdCode;
    @Column(name="MRJ_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name="MRJ_APPR_CODE")
    private String apprCode;
    @Column(name="MRJ_RESP_CODE")
    private String respCode;
    @Column(name="MCT_SERVICE_CODE")
    private String serviceCode;
    @Column(name="MCT_TERMINAL_ID")
    private String terminalId;
    @Column(name="MCT_MERCHANT_ID")
    private String merchantId;
    @Column(name="MCT_ME_NAME")
    private String meName;
    @Column(name="MCT_ME_CITY")
    private String meCity;
    @Column(name="MCT_ME_ZIP_CODE")
    private String meZipCode;
    @Column(name="MCT_ME_COUNTRY")
    private String meCountry;
    @Column(name="MCT_TRL_TYPE")
    private String trlType;
    @Column(name="MRJ_MOTO_ECOM_INDICATOR")
    private String ecomIndicator;
    @Column(name="MRJ_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name="MCT_TXN_CURR_EXP")
    private Integer txnCurExponent;
    @Column(name="MCT_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="MCT_IRD")
    private String mctIrd;
    @Column(name="MRJ_SETL_FLAG")
    private Character setlFlag;
    @Column(name="MCT_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name="MCT_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name="MCT_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name="MCT_ISS_APP_DATA")
    private String appData;
    @Column(name="MCT_UPBL_NUMBER")
    private String upblNumber;
    @Column(name="MCT_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name="MCT_TRL_VER_RESULTS")
    private String terminalVerificationResult;
    @Column(name="MCT_TXN_DATE")
    private LocalDate transactionDate;
    @Column(name="MCT_CHIP_TXN_DATE")
    private String chipTransactionDate;
    @Column(name="MCT_CHIP_TXN_TYPE")
    private String chipTransactionType;
    @Column(name="MCT_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name="MCT_APP_IC_PROFILE")
    private String applicationInterchangeProfile;
    @Column(name="MCT_TRL_CON_CODE")
    private String terminalCountryCode;
    @Column(name="MCT_CASHBACK_AMOUNT")
    private Double cashbackAmount;
    @Column(name="MCT_CVM_RESULTS")
    private String cvmResult;
    @Column(name="MCT_TRL_CAPABILITIES")
    private String terminalCapabilities;
    @Column(name="MCT_TCC")
    private String tcc;
    @Column(name="MCT_CHIP_CUR_CODE")
    private String chipCurrencyCode;
    @Column(name="MCT_CHIP_TRL_TYPE")
    private String chipTerminalType;
    @Column(name="MCT_TRL_APP_VER_NUMBER")
    private String terminalApplicationVerificationNumber;
    @Column(name="MCT_TXN_SEQ_COUNTER")
    private String transactionSequenceCounter;
    @Column(name="MCT_ISS_AUTH_DATA")
    private String issuerAuthData;
    @Column(name="MCT_TXN_LIFE_CYCL_ID")
    private String transactionLifeCycleId;
    @Column(name="MCT_MSG_NUMBER")
    private String messageNumber;
    @Column(name="MCT_MEMBER_TEXT")
    private String memberText;
    @Column(name="MCT_ORG_INST_ID_CODE")
    private String organizationInstIdCode;
    @Column(name="MRJ_REV_INDICATOR")
    private Character reversalIndicator;
    @Column(name="MCT_MER_MC_ASSIGNED_ID")
    private String mastercardAssignedId;
    @Column(name="MRJ_CARD_TYPE")
    private Character cardType;
    @Column(name="MCT_DOM_INTL_FLAG")
    private Character domesticInternationFlag;
    @Column(name="MCT_SMS_DMS_FLAG")
    private Character smsDmsFlag;
    @Column(name="MCT_POS_PG_TYPE")
    private String posPgType;
    @Column(name="MRJ_CENTRE_PROC_DATE")
    private LocalDate centralProcessingDate;
    @Column(name="MCT_OUT_FILE_DATE")
    private LocalDate outgoingFileDate;
    @Column(name="MRJ_REJECT_FIELD")
    private String rejectedField;
    @Column(name="MCT_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name="MCT_ME_COUNTRY_OF_ORIGIN")
    private String countryOfOrigin;
    @Column(name="MRJ_BUSS_DATE")
    private LocalDate businessDate;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getMsgTypeId() {
        return this.msgTypeId;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getSchgAmount() {
        return this.schgAmount;
    }

    public LocalDate getLocalDateTime() {
        return this.localDateTime;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getPosDataCode() {
        return this.posDataCode;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getAcqRefNumber() {
        return this.acqRefNumber;
    }

    public String getAcqInstIdCode() {
        return this.acqInstIdCode;
    }

    public String getRetRefNumber() {
        return this.retRefNumber;
    }

    public String getApprCode() {
        return this.apprCode;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getMeName() {
        return this.meName;
    }

    public String getMeCity() {
        return this.meCity;
    }

    public String getMeZipCode() {
        return this.meZipCode;
    }

    public String getMeCountry() {
        return this.meCountry;
    }

    public String getTrlType() {
        return this.trlType;
    }

    public String getEcomIndicator() {
        return this.ecomIndicator;
    }

    public Double getTxnFeeAmount() {
        return this.txnFeeAmount;
    }

    public Integer getTxnCurExponent() {
        return this.txnCurExponent;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
    }

    public String getMctIrd() {
        return this.mctIrd;
    }

    public Character getSetlFlag() {
        return this.setlFlag;
    }

    public String getCardSeqNumber() {
        return this.cardSeqNumber;
    }

    public String getAppCryptogram() {
        return this.appCryptogram;
    }

    public String getCryptInfoData() {
        return this.cryptInfoData;
    }

    public String getAppData() {
        return this.appData;
    }

    public String getUpblNumber() {
        return this.upblNumber;
    }

    public String getAppTxnCounter() {
        return this.appTxnCounter;
    }

    public String getTerminalVerificationResult() {
        return this.terminalVerificationResult;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public String getChipTransactionDate() {
        return this.chipTransactionDate;
    }

    public String getChipTransactionType() {
        return this.chipTransactionType;
    }

    public Double getCryptAmount() {
        return this.cryptAmount;
    }

    public String getApplicationInterchangeProfile() {
        return this.applicationInterchangeProfile;
    }

    public String getTerminalCountryCode() {
        return this.terminalCountryCode;
    }

    public Double getCashbackAmount() {
        return this.cashbackAmount;
    }

    public String getCvmResult() {
        return this.cvmResult;
    }

    public String getTerminalCapabilities() {
        return this.terminalCapabilities;
    }

    public String getTcc() {
        return this.tcc;
    }

    public String getChipCurrencyCode() {
        return this.chipCurrencyCode;
    }

    public String getChipTerminalType() {
        return this.chipTerminalType;
    }

    public String getTerminalApplicationVerificationNumber() {
        return this.terminalApplicationVerificationNumber;
    }

    public String getTransactionSequenceCounter() {
        return this.transactionSequenceCounter;
    }

    public String getIssuerAuthData() {
        return this.issuerAuthData;
    }

    public String getTransactionLifeCycleId() {
        return this.transactionLifeCycleId;
    }

    public String getMessageNumber() {
        return this.messageNumber;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public String getOrganizationInstIdCode() {
        return this.organizationInstIdCode;
    }

    public Character getReversalIndicator() {
        return this.reversalIndicator;
    }

    public String getMastercardAssignedId() {
        return this.mastercardAssignedId;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public Character getDomesticInternationFlag() {
        return this.domesticInternationFlag;
    }

    public Character getSmsDmsFlag() {
        return this.smsDmsFlag;
    }

    public String getPosPgType() {
        return this.posPgType;
    }

    public LocalDate getCentralProcessingDate() {
        return this.centralProcessingDate;
    }

    public LocalDate getOutgoingFileDate() {
        return this.outgoingFileDate;
    }

    public String getRejectedField() {
        return this.rejectedField;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }

    public String getCountryOfOrigin() {
        return this.countryOfOrigin;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setSchgAmount(Double schgAmount) {
        this.schgAmount = schgAmount;
    }

    public void setLocalDateTime(LocalDate localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setAcqRefNumber(String acqRefNumber) {
        this.acqRefNumber = acqRefNumber;
    }

    public void setAcqInstIdCode(String acqInstIdCode) {
        this.acqInstIdCode = acqInstIdCode;
    }

    public void setRetRefNumber(String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }

    public void setApprCode(String apprCode) {
        this.apprCode = apprCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public void setMeCity(String meCity) {
        this.meCity = meCity;
    }

    public void setMeZipCode(String meZipCode) {
        this.meZipCode = meZipCode;
    }

    public void setMeCountry(String meCountry) {
        this.meCountry = meCountry;
    }

    public void setTrlType(String trlType) {
        this.trlType = trlType;
    }

    public void setEcomIndicator(String ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }

    public void setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }

    public void setTxnCurExponent(Integer txnCurExponent) {
        this.txnCurExponent = txnCurExponent;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setMctIrd(String mctIrd) {
        this.mctIrd = mctIrd;
    }

    public void setSetlFlag(Character setlFlag) {
        this.setlFlag = setlFlag;
    }

    public void setCardSeqNumber(String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }

    public void setAppCryptogram(String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }

    public void setCryptInfoData(String cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    public void setUpblNumber(String upblNumber) {
        this.upblNumber = upblNumber;
    }

    public void setAppTxnCounter(String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }

    public void setTerminalVerificationResult(String terminalVerificationResult) {
        this.terminalVerificationResult = terminalVerificationResult;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setChipTransactionDate(String chipTransactionDate) {
        this.chipTransactionDate = chipTransactionDate;
    }

    public void setChipTransactionType(String chipTransactionType) {
        this.chipTransactionType = chipTransactionType;
    }

    public void setCryptAmount(Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }

    public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
        this.applicationInterchangeProfile = applicationInterchangeProfile;
    }

    public void setTerminalCountryCode(String terminalCountryCode) {
        this.terminalCountryCode = terminalCountryCode;
    }

    public void setCashbackAmount(Double cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public void setCvmResult(String cvmResult) {
        this.cvmResult = cvmResult;
    }

    public void setTerminalCapabilities(String terminalCapabilities) {
        this.terminalCapabilities = terminalCapabilities;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }

    public void setChipCurrencyCode(String chipCurrencyCode) {
        this.chipCurrencyCode = chipCurrencyCode;
    }

    public void setChipTerminalType(String chipTerminalType) {
        this.chipTerminalType = chipTerminalType;
    }

    public void setTerminalApplicationVerificationNumber(String terminalApplicationVerificationNumber) {
        this.terminalApplicationVerificationNumber = terminalApplicationVerificationNumber;
    }

    public void setTransactionSequenceCounter(String transactionSequenceCounter) {
        this.transactionSequenceCounter = transactionSequenceCounter;
    }

    public void setIssuerAuthData(String issuerAuthData) {
        this.issuerAuthData = issuerAuthData;
    }

    public void setTransactionLifeCycleId(String transactionLifeCycleId) {
        this.transactionLifeCycleId = transactionLifeCycleId;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setOrganizationInstIdCode(String organizationInstIdCode) {
        this.organizationInstIdCode = organizationInstIdCode;
    }

    public void setReversalIndicator(Character reversalIndicator) {
        this.reversalIndicator = reversalIndicator;
    }

    public void setMastercardAssignedId(String mastercardAssignedId) {
        this.mastercardAssignedId = mastercardAssignedId;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setDomesticInternationFlag(Character domesticInternationFlag) {
        this.domesticInternationFlag = domesticInternationFlag;
    }

    public void setSmsDmsFlag(Character smsDmsFlag) {
        this.smsDmsFlag = smsDmsFlag;
    }

    public void setPosPgType(String posPgType) {
        this.posPgType = posPgType;
    }

    public void setCentralProcessingDate(LocalDate centralProcessingDate) {
        this.centralProcessingDate = centralProcessingDate;
    }

    public void setOutgoingFileDate(LocalDate outgoingFileDate) {
        this.outgoingFileDate = outgoingFileDate;
    }

    public void setRejectedField(String rejectedField) {
        this.rejectedField = rejectedField;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewMcRejectionDetails)) {
            return false;
        }
        ViewMcRejectionDetails other = (ViewMcRejectionDetails)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$schgAmount = this.getSchgAmount();
        Double other$schgAmount = other.getSchgAmount();
        if (this$schgAmount == null ? other$schgAmount != null : !((Object)this$schgAmount).equals(other$schgAmount)) {
            return false;
        }
        Double this$txnFeeAmount = this.getTxnFeeAmount();
        Double other$txnFeeAmount = other.getTxnFeeAmount();
        if (this$txnFeeAmount == null ? other$txnFeeAmount != null : !((Object)this$txnFeeAmount).equals(other$txnFeeAmount)) {
            return false;
        }
        Integer this$txnCurExponent = this.getTxnCurExponent();
        Integer other$txnCurExponent = other.getTxnCurExponent();
        if (this$txnCurExponent == null ? other$txnCurExponent != null : !((Object)this$txnCurExponent).equals(other$txnCurExponent)) {
            return false;
        }
        Character this$setlFlag = this.getSetlFlag();
        Character other$setlFlag = other.getSetlFlag();
        if (this$setlFlag == null ? other$setlFlag != null : !((Object)this$setlFlag).equals(other$setlFlag)) {
            return false;
        }
        Double this$cryptAmount = this.getCryptAmount();
        Double other$cryptAmount = other.getCryptAmount();
        if (this$cryptAmount == null ? other$cryptAmount != null : !((Object)this$cryptAmount).equals(other$cryptAmount)) {
            return false;
        }
        Double this$cashbackAmount = this.getCashbackAmount();
        Double other$cashbackAmount = other.getCashbackAmount();
        if (this$cashbackAmount == null ? other$cashbackAmount != null : !((Object)this$cashbackAmount).equals(other$cashbackAmount)) {
            return false;
        }
        Character this$reversalIndicator = this.getReversalIndicator();
        Character other$reversalIndicator = other.getReversalIndicator();
        if (this$reversalIndicator == null ? other$reversalIndicator != null : !((Object)this$reversalIndicator).equals(other$reversalIndicator)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Character this$domesticInternationFlag = this.getDomesticInternationFlag();
        Character other$domesticInternationFlag = other.getDomesticInternationFlag();
        if (this$domesticInternationFlag == null ? other$domesticInternationFlag != null : !((Object)this$domesticInternationFlag).equals(other$domesticInternationFlag)) {
            return false;
        }
        Character this$smsDmsFlag = this.getSmsDmsFlag();
        Character other$smsDmsFlag = other.getSmsDmsFlag();
        if (this$smsDmsFlag == null ? other$smsDmsFlag != null : !((Object)this$smsDmsFlag).equals(other$smsDmsFlag)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$msgTypeId = this.getMsgTypeId();
        String other$msgTypeId = other.getMsgTypeId();
        if (this$msgTypeId == null ? other$msgTypeId != null : !this$msgTypeId.equals(other$msgTypeId)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        LocalDate this$localDateTime = this.getLocalDateTime();
        LocalDate other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$expiryDate = this.getExpiryDate();
        String other$expiryDate = other.getExpiryDate();
        if (this$expiryDate == null ? other$expiryDate != null : !this$expiryDate.equals(other$expiryDate)) {
            return false;
        }
        String this$posDataCode = this.getPosDataCode();
        String other$posDataCode = other.getPosDataCode();
        if (this$posDataCode == null ? other$posDataCode != null : !this$posDataCode.equals(other$posDataCode)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        String this$reasonCode = this.getReasonCode();
        String other$reasonCode = other.getReasonCode();
        if (this$reasonCode == null ? other$reasonCode != null : !this$reasonCode.equals(other$reasonCode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$acqRefNumber = this.getAcqRefNumber();
        String other$acqRefNumber = other.getAcqRefNumber();
        if (this$acqRefNumber == null ? other$acqRefNumber != null : !this$acqRefNumber.equals(other$acqRefNumber)) {
            return false;
        }
        String this$acqInstIdCode = this.getAcqInstIdCode();
        String other$acqInstIdCode = other.getAcqInstIdCode();
        if (this$acqInstIdCode == null ? other$acqInstIdCode != null : !this$acqInstIdCode.equals(other$acqInstIdCode)) {
            return false;
        }
        String this$retRefNumber = this.getRetRefNumber();
        String other$retRefNumber = other.getRetRefNumber();
        if (this$retRefNumber == null ? other$retRefNumber != null : !this$retRefNumber.equals(other$retRefNumber)) {
            return false;
        }
        String this$apprCode = this.getApprCode();
        String other$apprCode = other.getApprCode();
        if (this$apprCode == null ? other$apprCode != null : !this$apprCode.equals(other$apprCode)) {
            return false;
        }
        String this$respCode = this.getRespCode();
        String other$respCode = other.getRespCode();
        if (this$respCode == null ? other$respCode != null : !this$respCode.equals(other$respCode)) {
            return false;
        }
        String this$serviceCode = this.getServiceCode();
        String other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null ? other$serviceCode != null : !this$serviceCode.equals(other$serviceCode)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$meName = this.getMeName();
        String other$meName = other.getMeName();
        if (this$meName == null ? other$meName != null : !this$meName.equals(other$meName)) {
            return false;
        }
        String this$meCity = this.getMeCity();
        String other$meCity = other.getMeCity();
        if (this$meCity == null ? other$meCity != null : !this$meCity.equals(other$meCity)) {
            return false;
        }
        String this$meZipCode = this.getMeZipCode();
        String other$meZipCode = other.getMeZipCode();
        if (this$meZipCode == null ? other$meZipCode != null : !this$meZipCode.equals(other$meZipCode)) {
            return false;
        }
        String this$meCountry = this.getMeCountry();
        String other$meCountry = other.getMeCountry();
        if (this$meCountry == null ? other$meCountry != null : !this$meCountry.equals(other$meCountry)) {
            return false;
        }
        String this$trlType = this.getTrlType();
        String other$trlType = other.getTrlType();
        if (this$trlType == null ? other$trlType != null : !this$trlType.equals(other$trlType)) {
            return false;
        }
        String this$ecomIndicator = this.getEcomIndicator();
        String other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null ? other$ecomIndicator != null : !this$ecomIndicator.equals(other$ecomIndicator)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
            return false;
        }
        String this$mctIrd = this.getMctIrd();
        String other$mctIrd = other.getMctIrd();
        if (this$mctIrd == null ? other$mctIrd != null : !this$mctIrd.equals(other$mctIrd)) {
            return false;
        }
        String this$cardSeqNumber = this.getCardSeqNumber();
        String other$cardSeqNumber = other.getCardSeqNumber();
        if (this$cardSeqNumber == null ? other$cardSeqNumber != null : !this$cardSeqNumber.equals(other$cardSeqNumber)) {
            return false;
        }
        String this$appCryptogram = this.getAppCryptogram();
        String other$appCryptogram = other.getAppCryptogram();
        if (this$appCryptogram == null ? other$appCryptogram != null : !this$appCryptogram.equals(other$appCryptogram)) {
            return false;
        }
        String this$cryptInfoData = this.getCryptInfoData();
        String other$cryptInfoData = other.getCryptInfoData();
        if (this$cryptInfoData == null ? other$cryptInfoData != null : !this$cryptInfoData.equals(other$cryptInfoData)) {
            return false;
        }
        String this$appData = this.getAppData();
        String other$appData = other.getAppData();
        if (this$appData == null ? other$appData != null : !this$appData.equals(other$appData)) {
            return false;
        }
        String this$upblNumber = this.getUpblNumber();
        String other$upblNumber = other.getUpblNumber();
        if (this$upblNumber == null ? other$upblNumber != null : !this$upblNumber.equals(other$upblNumber)) {
            return false;
        }
        String this$appTxnCounter = this.getAppTxnCounter();
        String other$appTxnCounter = other.getAppTxnCounter();
        if (this$appTxnCounter == null ? other$appTxnCounter != null : !this$appTxnCounter.equals(other$appTxnCounter)) {
            return false;
        }
        String this$terminalVerificationResult = this.getTerminalVerificationResult();
        String other$terminalVerificationResult = other.getTerminalVerificationResult();
        if (this$terminalVerificationResult == null ? other$terminalVerificationResult != null : !this$terminalVerificationResult.equals(other$terminalVerificationResult)) {
            return false;
        }
        LocalDate this$transactionDate = this.getTransactionDate();
        LocalDate other$transactionDate = other.getTransactionDate();
        if (this$transactionDate == null ? other$transactionDate != null : !((Object)this$transactionDate).equals(other$transactionDate)) {
            return false;
        }
        String this$chipTransactionDate = this.getChipTransactionDate();
        String other$chipTransactionDate = other.getChipTransactionDate();
        if (this$chipTransactionDate == null ? other$chipTransactionDate != null : !this$chipTransactionDate.equals(other$chipTransactionDate)) {
            return false;
        }
        String this$chipTransactionType = this.getChipTransactionType();
        String other$chipTransactionType = other.getChipTransactionType();
        if (this$chipTransactionType == null ? other$chipTransactionType != null : !this$chipTransactionType.equals(other$chipTransactionType)) {
            return false;
        }
        String this$applicationInterchangeProfile = this.getApplicationInterchangeProfile();
        String other$applicationInterchangeProfile = other.getApplicationInterchangeProfile();
        if (this$applicationInterchangeProfile == null ? other$applicationInterchangeProfile != null : !this$applicationInterchangeProfile.equals(other$applicationInterchangeProfile)) {
            return false;
        }
        String this$terminalCountryCode = this.getTerminalCountryCode();
        String other$terminalCountryCode = other.getTerminalCountryCode();
        if (this$terminalCountryCode == null ? other$terminalCountryCode != null : !this$terminalCountryCode.equals(other$terminalCountryCode)) {
            return false;
        }
        String this$cvmResult = this.getCvmResult();
        String other$cvmResult = other.getCvmResult();
        if (this$cvmResult == null ? other$cvmResult != null : !this$cvmResult.equals(other$cvmResult)) {
            return false;
        }
        String this$terminalCapabilities = this.getTerminalCapabilities();
        String other$terminalCapabilities = other.getTerminalCapabilities();
        if (this$terminalCapabilities == null ? other$terminalCapabilities != null : !this$terminalCapabilities.equals(other$terminalCapabilities)) {
            return false;
        }
        String this$tcc = this.getTcc();
        String other$tcc = other.getTcc();
        if (this$tcc == null ? other$tcc != null : !this$tcc.equals(other$tcc)) {
            return false;
        }
        String this$chipCurrencyCode = this.getChipCurrencyCode();
        String other$chipCurrencyCode = other.getChipCurrencyCode();
        if (this$chipCurrencyCode == null ? other$chipCurrencyCode != null : !this$chipCurrencyCode.equals(other$chipCurrencyCode)) {
            return false;
        }
        String this$chipTerminalType = this.getChipTerminalType();
        String other$chipTerminalType = other.getChipTerminalType();
        if (this$chipTerminalType == null ? other$chipTerminalType != null : !this$chipTerminalType.equals(other$chipTerminalType)) {
            return false;
        }
        String this$terminalApplicationVerificationNumber = this.getTerminalApplicationVerificationNumber();
        String other$terminalApplicationVerificationNumber = other.getTerminalApplicationVerificationNumber();
        if (this$terminalApplicationVerificationNumber == null ? other$terminalApplicationVerificationNumber != null : !this$terminalApplicationVerificationNumber.equals(other$terminalApplicationVerificationNumber)) {
            return false;
        }
        String this$transactionSequenceCounter = this.getTransactionSequenceCounter();
        String other$transactionSequenceCounter = other.getTransactionSequenceCounter();
        if (this$transactionSequenceCounter == null ? other$transactionSequenceCounter != null : !this$transactionSequenceCounter.equals(other$transactionSequenceCounter)) {
            return false;
        }
        String this$issuerAuthData = this.getIssuerAuthData();
        String other$issuerAuthData = other.getIssuerAuthData();
        if (this$issuerAuthData == null ? other$issuerAuthData != null : !this$issuerAuthData.equals(other$issuerAuthData)) {
            return false;
        }
        String this$transactionLifeCycleId = this.getTransactionLifeCycleId();
        String other$transactionLifeCycleId = other.getTransactionLifeCycleId();
        if (this$transactionLifeCycleId == null ? other$transactionLifeCycleId != null : !this$transactionLifeCycleId.equals(other$transactionLifeCycleId)) {
            return false;
        }
        String this$messageNumber = this.getMessageNumber();
        String other$messageNumber = other.getMessageNumber();
        if (this$messageNumber == null ? other$messageNumber != null : !this$messageNumber.equals(other$messageNumber)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
            return false;
        }
        String this$organizationInstIdCode = this.getOrganizationInstIdCode();
        String other$organizationInstIdCode = other.getOrganizationInstIdCode();
        if (this$organizationInstIdCode == null ? other$organizationInstIdCode != null : !this$organizationInstIdCode.equals(other$organizationInstIdCode)) {
            return false;
        }
        String this$mastercardAssignedId = this.getMastercardAssignedId();
        String other$mastercardAssignedId = other.getMastercardAssignedId();
        if (this$mastercardAssignedId == null ? other$mastercardAssignedId != null : !this$mastercardAssignedId.equals(other$mastercardAssignedId)) {
            return false;
        }
        String this$posPgType = this.getPosPgType();
        String other$posPgType = other.getPosPgType();
        if (this$posPgType == null ? other$posPgType != null : !this$posPgType.equals(other$posPgType)) {
            return false;
        }
        LocalDate this$centralProcessingDate = this.getCentralProcessingDate();
        LocalDate other$centralProcessingDate = other.getCentralProcessingDate();
        if (this$centralProcessingDate == null ? other$centralProcessingDate != null : !((Object)this$centralProcessingDate).equals(other$centralProcessingDate)) {
            return false;
        }
        LocalDate this$outgoingFileDate = this.getOutgoingFileDate();
        LocalDate other$outgoingFileDate = other.getOutgoingFileDate();
        if (this$outgoingFileDate == null ? other$outgoingFileDate != null : !((Object)this$outgoingFileDate).equals(other$outgoingFileDate)) {
            return false;
        }
        String this$rejectedField = this.getRejectedField();
        String other$rejectedField = other.getRejectedField();
        if (this$rejectedField == null ? other$rejectedField != null : !this$rejectedField.equals(other$rejectedField)) {
            return false;
        }
        String this$encryptedCardNumber = this.getEncryptedCardNumber();
        String other$encryptedCardNumber = other.getEncryptedCardNumber();
        if (this$encryptedCardNumber == null ? other$encryptedCardNumber != null : !this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
            return false;
        }
        String this$countryOfOrigin = this.getCountryOfOrigin();
        String other$countryOfOrigin = other.getCountryOfOrigin();
        if (this$countryOfOrigin == null ? other$countryOfOrigin != null : !this$countryOfOrigin.equals(other$countryOfOrigin)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        return !(this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewMcRejectionDetails;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $schgAmount = this.getSchgAmount();
        result = result * 59 + ($schgAmount == null ? 43 : ((Object)$schgAmount).hashCode());
        Double $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + ($txnFeeAmount == null ? 43 : ((Object)$txnFeeAmount).hashCode());
        Integer $txnCurExponent = this.getTxnCurExponent();
        result = result * 59 + ($txnCurExponent == null ? 43 : ((Object)$txnCurExponent).hashCode());
        Character $setlFlag = this.getSetlFlag();
        result = result * 59 + ($setlFlag == null ? 43 : ((Object)$setlFlag).hashCode());
        Double $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : ((Object)$cryptAmount).hashCode());
        Double $cashbackAmount = this.getCashbackAmount();
        result = result * 59 + ($cashbackAmount == null ? 43 : ((Object)$cashbackAmount).hashCode());
        Character $reversalIndicator = this.getReversalIndicator();
        result = result * 59 + ($reversalIndicator == null ? 43 : ((Object)$reversalIndicator).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $domesticInternationFlag = this.getDomesticInternationFlag();
        result = result * 59 + ($domesticInternationFlag == null ? 43 : ((Object)$domesticInternationFlag).hashCode());
        Character $smsDmsFlag = this.getSmsDmsFlag();
        result = result * 59 + ($smsDmsFlag == null ? 43 : ((Object)$smsDmsFlag).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $msgTypeId = this.getMsgTypeId();
        result = result * 59 + ($msgTypeId == null ? 43 : $msgTypeId.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        LocalDate $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $expiryDate = this.getExpiryDate();
        result = result * 59 + ($expiryDate == null ? 43 : $expiryDate.hashCode());
        String $posDataCode = this.getPosDataCode();
        result = result * 59 + ($posDataCode == null ? 43 : $posDataCode.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        String $reasonCode = this.getReasonCode();
        result = result * 59 + ($reasonCode == null ? 43 : $reasonCode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $acqRefNumber = this.getAcqRefNumber();
        result = result * 59 + ($acqRefNumber == null ? 43 : $acqRefNumber.hashCode());
        String $acqInstIdCode = this.getAcqInstIdCode();
        result = result * 59 + ($acqInstIdCode == null ? 43 : $acqInstIdCode.hashCode());
        String $retRefNumber = this.getRetRefNumber();
        result = result * 59 + ($retRefNumber == null ? 43 : $retRefNumber.hashCode());
        String $apprCode = this.getApprCode();
        result = result * 59 + ($apprCode == null ? 43 : $apprCode.hashCode());
        String $respCode = this.getRespCode();
        result = result * 59 + ($respCode == null ? 43 : $respCode.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meZipCode = this.getMeZipCode();
        result = result * 59 + ($meZipCode == null ? 43 : $meZipCode.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $trlType = this.getTrlType();
        result = result * 59 + ($trlType == null ? 43 : $trlType.hashCode());
        String $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + ($ecomIndicator == null ? 43 : $ecomIndicator.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        String $mctIrd = this.getMctIrd();
        result = result * 59 + ($mctIrd == null ? 43 : $mctIrd.hashCode());
        String $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + ($cardSeqNumber == null ? 43 : $cardSeqNumber.hashCode());
        String $appCryptogram = this.getAppCryptogram();
        result = result * 59 + ($appCryptogram == null ? 43 : $appCryptogram.hashCode());
        String $cryptInfoData = this.getCryptInfoData();
        result = result * 59 + ($cryptInfoData == null ? 43 : $cryptInfoData.hashCode());
        String $appData = this.getAppData();
        result = result * 59 + ($appData == null ? 43 : $appData.hashCode());
        String $upblNumber = this.getUpblNumber();
        result = result * 59 + ($upblNumber == null ? 43 : $upblNumber.hashCode());
        String $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + ($appTxnCounter == null ? 43 : $appTxnCounter.hashCode());
        String $terminalVerificationResult = this.getTerminalVerificationResult();
        result = result * 59 + ($terminalVerificationResult == null ? 43 : $terminalVerificationResult.hashCode());
        LocalDate $transactionDate = this.getTransactionDate();
        result = result * 59 + ($transactionDate == null ? 43 : ((Object)$transactionDate).hashCode());
        String $chipTransactionDate = this.getChipTransactionDate();
        result = result * 59 + ($chipTransactionDate == null ? 43 : $chipTransactionDate.hashCode());
        String $chipTransactionType = this.getChipTransactionType();
        result = result * 59 + ($chipTransactionType == null ? 43 : $chipTransactionType.hashCode());
        String $applicationInterchangeProfile = this.getApplicationInterchangeProfile();
        result = result * 59 + ($applicationInterchangeProfile == null ? 43 : $applicationInterchangeProfile.hashCode());
        String $terminalCountryCode = this.getTerminalCountryCode();
        result = result * 59 + ($terminalCountryCode == null ? 43 : $terminalCountryCode.hashCode());
        String $cvmResult = this.getCvmResult();
        result = result * 59 + ($cvmResult == null ? 43 : $cvmResult.hashCode());
        String $terminalCapabilities = this.getTerminalCapabilities();
        result = result * 59 + ($terminalCapabilities == null ? 43 : $terminalCapabilities.hashCode());
        String $tcc = this.getTcc();
        result = result * 59 + ($tcc == null ? 43 : $tcc.hashCode());
        String $chipCurrencyCode = this.getChipCurrencyCode();
        result = result * 59 + ($chipCurrencyCode == null ? 43 : $chipCurrencyCode.hashCode());
        String $chipTerminalType = this.getChipTerminalType();
        result = result * 59 + ($chipTerminalType == null ? 43 : $chipTerminalType.hashCode());
        String $terminalApplicationVerificationNumber = this.getTerminalApplicationVerificationNumber();
        result = result * 59 + ($terminalApplicationVerificationNumber == null ? 43 : $terminalApplicationVerificationNumber.hashCode());
        String $transactionSequenceCounter = this.getTransactionSequenceCounter();
        result = result * 59 + ($transactionSequenceCounter == null ? 43 : $transactionSequenceCounter.hashCode());
        String $issuerAuthData = this.getIssuerAuthData();
        result = result * 59 + ($issuerAuthData == null ? 43 : $issuerAuthData.hashCode());
        String $transactionLifeCycleId = this.getTransactionLifeCycleId();
        result = result * 59 + ($transactionLifeCycleId == null ? 43 : $transactionLifeCycleId.hashCode());
        String $messageNumber = this.getMessageNumber();
        result = result * 59 + ($messageNumber == null ? 43 : $messageNumber.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $organizationInstIdCode = this.getOrganizationInstIdCode();
        result = result * 59 + ($organizationInstIdCode == null ? 43 : $organizationInstIdCode.hashCode());
        String $mastercardAssignedId = this.getMastercardAssignedId();
        result = result * 59 + ($mastercardAssignedId == null ? 43 : $mastercardAssignedId.hashCode());
        String $posPgType = this.getPosPgType();
        result = result * 59 + ($posPgType == null ? 43 : $posPgType.hashCode());
        LocalDate $centralProcessingDate = this.getCentralProcessingDate();
        result = result * 59 + ($centralProcessingDate == null ? 43 : ((Object)$centralProcessingDate).hashCode());
        LocalDate $outgoingFileDate = this.getOutgoingFileDate();
        result = result * 59 + ($outgoingFileDate == null ? 43 : ((Object)$outgoingFileDate).hashCode());
        String $rejectedField = this.getRejectedField();
        result = result * 59 + ($rejectedField == null ? 43 : $rejectedField.hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $countryOfOrigin = this.getCountryOfOrigin();
        result = result * 59 + ($countryOfOrigin == null ? 43 : $countryOfOrigin.hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        return result;
    }

    public String toString() {
        return "ViewMcRejectionDetails(serialNumber=" + this.getSerialNumber() + ", txnType=" + this.getTxnType() + ", msgTypeId=" + this.getMsgTypeId() + ", cardNumber=" + this.getCardNumber() + ", procCode=" + this.getProcCode() + ", txnAmount=" + this.getTxnAmount() + ", schgAmount=" + this.getSchgAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", expiryDate=" + this.getExpiryDate() + ", posDataCode=" + this.getPosDataCode() + ", functionCode=" + this.getFunctionCode() + ", reasonCode=" + this.getReasonCode() + ", mcc=" + this.getMcc() + ", acqRefNumber=" + this.getAcqRefNumber() + ", acqInstIdCode=" + this.getAcqInstIdCode() + ", retRefNumber=" + this.getRetRefNumber() + ", apprCode=" + this.getApprCode() + ", respCode=" + this.getRespCode() + ", serviceCode=" + this.getServiceCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meZipCode=" + this.getMeZipCode() + ", meCountry=" + this.getMeCountry() + ", trlType=" + this.getTrlType() + ", ecomIndicator=" + this.getEcomIndicator() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", txnCurExponent=" + this.getTxnCurExponent() + ", txnCurCode=" + this.getTxnCurCode() + ", mctIrd=" + this.getMctIrd() + ", setlFlag=" + this.getSetlFlag() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", appData=" + this.getAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", terminalVerificationResult=" + this.getTerminalVerificationResult() + ", transactionDate=" + String.valueOf(this.getTransactionDate()) + ", chipTransactionDate=" + this.getChipTransactionDate() + ", chipTransactionType=" + this.getChipTransactionType() + ", cryptAmount=" + this.getCryptAmount() + ", applicationInterchangeProfile=" + this.getApplicationInterchangeProfile() + ", terminalCountryCode=" + this.getTerminalCountryCode() + ", cashbackAmount=" + this.getCashbackAmount() + ", cvmResult=" + this.getCvmResult() + ", terminalCapabilities=" + this.getTerminalCapabilities() + ", tcc=" + this.getTcc() + ", chipCurrencyCode=" + this.getChipCurrencyCode() + ", chipTerminalType=" + this.getChipTerminalType() + ", terminalApplicationVerificationNumber=" + this.getTerminalApplicationVerificationNumber() + ", transactionSequenceCounter=" + this.getTransactionSequenceCounter() + ", issuerAuthData=" + this.getIssuerAuthData() + ", transactionLifeCycleId=" + this.getTransactionLifeCycleId() + ", messageNumber=" + this.getMessageNumber() + ", memberText=" + this.getMemberText() + ", organizationInstIdCode=" + this.getOrganizationInstIdCode() + ", reversalIndicator=" + this.getReversalIndicator() + ", mastercardAssignedId=" + this.getMastercardAssignedId() + ", cardType=" + this.getCardType() + ", domesticInternationFlag=" + this.getDomesticInternationFlag() + ", smsDmsFlag=" + this.getSmsDmsFlag() + ", posPgType=" + this.getPosPgType() + ", centralProcessingDate=" + String.valueOf(this.getCentralProcessingDate()) + ", outgoingFileDate=" + String.valueOf(this.getOutgoingFileDate()) + ", rejectedField=" + this.getRejectedField() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", countryOfOrigin=" + this.getCountryOfOrigin() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ")";
    }
}

