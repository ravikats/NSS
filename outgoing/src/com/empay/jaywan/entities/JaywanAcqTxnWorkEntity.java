/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.entities.JaywanAcqTxnWorkEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.jaywan.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="JAYWAN_ACQ_TXN_WORK")
public class JaywanAcqTxnWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="JWN_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="JWN_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="JWN_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="JWN_INS_CODE")
    private Integer institutionCode;
    @Column(name="JWN_INT_CODE")
    private Integer intCode;
    @Column(name="JWN_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name="JWN_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name="JWN_TXN_TYPE")
    private String txnType;
    @Column(name="JWN_TXN_CODE")
    private String txnCode;
    @Column(name="JWN_MSG_TYPE_ID")
    private String messageTypeId;
    @Column(name="JWN_FUNC_CODE")
    private String functionCode;
    @Column(name="JWN_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="JWN_CARD_NUMBER")
    private String cardNumber;
    @Column(name="JWN_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name="JWN_APPR_CODE")
    private String approvalCode;
    @Column(name="JWN_TERMINAL_ID")
    private String terminalId;
    @Column(name="JWN_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="JWN_SETL_AMOUNT")
    private Double settledAmount;
    @Column(name="JWN_BILL_AMOUNT")
    private Double billAmount;
    @Column(name="JWN_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name="JWN_CONV_RATE")
    private Double convRate;
    @Column(name="JWN_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="JWN_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name="JWN_RET_REF_NUMBER")
    private String rrn;
    @Column(name="JWN_MERCHANT_ID")
    private String merchantId;
    @Column(name="JWN_ME_NAME")
    private String meName;
    @Column(name="JWN_ME_CITY")
    private String meCity;
    @Column(name="JWN_ME_STATE_CODE")
    private String meStateCode;
    @Column(name="JWN_ME_COUNTRY")
    private String meCountry;
    @Column(name="JWN_MCC")
    private String mcc;
    @Column(name="JWN_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name="JWN_ACQ_INST_ID")
    private String acqinstIdCode;
    @Column(name="JWN_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name="JWN_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="JWN_TRL_TYPE")
    private String trlType;
    @Column(name="JWN_ME_CATEGORY_TYPE")
    private Character meCategoryType;
    @Column(name="JWN_CARD_TYPE")
    private Character cardType;
    @Column(name="JWN_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name="JWN_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name="JWN_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name="JWN_FILE_ID")
    private String fileID;
    @Column(name="JWN_GEN_STATUS")
    private Integer genStatus;
    @Column(name="JWN_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name="JWN_RESP_CODE")
    private String responseCode;
    @Column(name="JWN_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name="JWN_SETTL_DATE")
    private LocalDate settlDate;
    @Column(name="JWN_SETTL_INDICATOR")
    private Character settlIndiCator;
    @Column(name="JWN_POS_CONDITION_CODE")
    private String posConditionCode;
    @Column(name="JWN_FULL_PARTIAL_INDICATOR")
    private Character fullPartialIndiCator;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInstitutionCode() {
        return this.institutionCode;
    }

    public Integer getIntCode() {
        return this.intCode;
    }

    public Integer getPrjSerNumber() {
        return this.prjSerNumber;
    }

    public Integer getTxnRefNumber() {
        return this.txnRefNumber;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public String getMessageTypeId() {
        return this.messageTypeId;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getAcqRefData() {
        return this.acqRefData;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getSettledAmount() {
        return this.settledAmount;
    }

    public Double getBillAmount() {
        return this.billAmount;
    }

    public Double getSurchargeAmount() {
        return this.surchargeAmount;
    }

    public Double getConvRate() {
        return this.convRate;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
    }

    public Double getCashBackAmount() {
        return this.cashBackAmount;
    }

    public String getRrn() {
        return this.rrn;
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

    public String getMeStateCode() {
        return this.meStateCode;
    }

    public String getMeCountry() {
        return this.meCountry;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
    }

    public String getAcqinstIdCode() {
        return this.acqinstIdCode;
    }

    public Character getRevIndiCator() {
        return this.revIndiCator;
    }

    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }

    public String getTrlType() {
        return this.trlType;
    }

    public Character getMeCategoryType() {
        return this.meCategoryType;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public Character getDmsSmsMode() {
        return this.dmsSmsMode;
    }

    public LocalDate getCentreProcDate() {
        return this.centreProcDate;
    }

    public LocalDate getFileProcDate() {
        return this.fileProcDate;
    }

    public String getFileID() {
        return this.fileID;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }

    public LocalDate getSettlDate() {
        return this.settlDate;
    }

    public Character getSettlIndiCator() {
        return this.settlIndiCator;
    }

    public String getPosConditionCode() {
        return this.posConditionCode;
    }

    public Character getFullPartialIndiCator() {
        return this.fullPartialIndiCator;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setIntCode(Integer intCode) {
        this.intCode = intCode;
    }

    public void setPrjSerNumber(Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }

    public void setTxnRefNumber(Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAcqRefData(String acqRefData) {
        this.acqRefData = acqRefData;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setSettledAmount(Double settledAmount) {
        this.settledAmount = settledAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public void setSurchargeAmount(Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public void setConvRate(Double convRate) {
        this.convRate = convRate;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setCashBackAmount(Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
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

    public void setMeStateCode(String meStateCode) {
        this.meStateCode = meStateCode;
    }

    public void setMeCountry(String meCountry) {
        this.meCountry = meCountry;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public void setAcqinstIdCode(String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }

    public void setRevIndiCator(Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }

    public void setCardDomIntlFlag(Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setTrlType(String trlType) {
        this.trlType = trlType;
    }

    public void setMeCategoryType(Character meCategoryType) {
        this.meCategoryType = meCategoryType;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setDmsSmsMode(Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }

    public void setCentreProcDate(LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }

    public void setFileProcDate(LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setSettlDate(LocalDate settlDate) {
        this.settlDate = settlDate;
    }

    public void setSettlIndiCator(Character settlIndiCator) {
        this.settlIndiCator = settlIndiCator;
    }

    public void setPosConditionCode(String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }

    public void setFullPartialIndiCator(Character fullPartialIndiCator) {
        this.fullPartialIndiCator = fullPartialIndiCator;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanAcqTxnWorkEntity)) {
            return false;
        }
        JaywanAcqTxnWorkEntity other = (JaywanAcqTxnWorkEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Integer this$institutionCode = this.getInstitutionCode();
        Integer other$institutionCode = other.getInstitutionCode();
        if (this$institutionCode == null ? other$institutionCode != null : !((Object)this$institutionCode).equals(other$institutionCode)) {
            return false;
        }
        Integer this$intCode = this.getIntCode();
        Integer other$intCode = other.getIntCode();
        if (this$intCode == null ? other$intCode != null : !((Object)this$intCode).equals(other$intCode)) {
            return false;
        }
        Integer this$prjSerNumber = this.getPrjSerNumber();
        Integer other$prjSerNumber = other.getPrjSerNumber();
        if (this$prjSerNumber == null ? other$prjSerNumber != null : !((Object)this$prjSerNumber).equals(other$prjSerNumber)) {
            return false;
        }
        Integer this$txnRefNumber = this.getTxnRefNumber();
        Integer other$txnRefNumber = other.getTxnRefNumber();
        if (this$txnRefNumber == null ? other$txnRefNumber != null : !((Object)this$txnRefNumber).equals(other$txnRefNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$settledAmount = this.getSettledAmount();
        Double other$settledAmount = other.getSettledAmount();
        if (this$settledAmount == null ? other$settledAmount != null : !((Object)this$settledAmount).equals(other$settledAmount)) {
            return false;
        }
        Double this$billAmount = this.getBillAmount();
        Double other$billAmount = other.getBillAmount();
        if (this$billAmount == null ? other$billAmount != null : !((Object)this$billAmount).equals(other$billAmount)) {
            return false;
        }
        Double this$surchargeAmount = this.getSurchargeAmount();
        Double other$surchargeAmount = other.getSurchargeAmount();
        if (this$surchargeAmount == null ? other$surchargeAmount != null : !((Object)this$surchargeAmount).equals(other$surchargeAmount)) {
            return false;
        }
        Double this$convRate = this.getConvRate();
        Double other$convRate = other.getConvRate();
        if (this$convRate == null ? other$convRate != null : !((Object)this$convRate).equals(other$convRate)) {
            return false;
        }
        Double this$cashBackAmount = this.getCashBackAmount();
        Double other$cashBackAmount = other.getCashBackAmount();
        if (this$cashBackAmount == null ? other$cashBackAmount != null : !((Object)this$cashBackAmount).equals(other$cashBackAmount)) {
            return false;
        }
        Character this$revIndiCator = this.getRevIndiCator();
        Character other$revIndiCator = other.getRevIndiCator();
        if (this$revIndiCator == null ? other$revIndiCator != null : !((Object)this$revIndiCator).equals(other$revIndiCator)) {
            return false;
        }
        Character this$cardDomIntlFlag = this.getCardDomIntlFlag();
        Character other$cardDomIntlFlag = other.getCardDomIntlFlag();
        if (this$cardDomIntlFlag == null ? other$cardDomIntlFlag != null : !((Object)this$cardDomIntlFlag).equals(other$cardDomIntlFlag)) {
            return false;
        }
        Character this$meCategoryType = this.getMeCategoryType();
        Character other$meCategoryType = other.getMeCategoryType();
        if (this$meCategoryType == null ? other$meCategoryType != null : !((Object)this$meCategoryType).equals(other$meCategoryType)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Character this$dmsSmsMode = this.getDmsSmsMode();
        Character other$dmsSmsMode = other.getDmsSmsMode();
        if (this$dmsSmsMode == null ? other$dmsSmsMode != null : !((Object)this$dmsSmsMode).equals(other$dmsSmsMode)) {
            return false;
        }
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        Character this$settlIndiCator = this.getSettlIndiCator();
        Character other$settlIndiCator = other.getSettlIndiCator();
        if (this$settlIndiCator == null ? other$settlIndiCator != null : !((Object)this$settlIndiCator).equals(other$settlIndiCator)) {
            return false;
        }
        Character this$fullPartialIndiCator = this.getFullPartialIndiCator();
        Character other$fullPartialIndiCator = other.getFullPartialIndiCator();
        if (this$fullPartialIndiCator == null ? other$fullPartialIndiCator != null : !((Object)this$fullPartialIndiCator).equals(other$fullPartialIndiCator)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$messageTypeId = this.getMessageTypeId();
        String other$messageTypeId = other.getMessageTypeId();
        if (this$messageTypeId == null ? other$messageTypeId != null : !this$messageTypeId.equals(other$messageTypeId)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$acqRefData = this.getAcqRefData();
        String other$acqRefData = other.getAcqRefData();
        if (this$acqRefData == null ? other$acqRefData != null : !this$acqRefData.equals(other$acqRefData)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
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
        String this$meStateCode = this.getMeStateCode();
        String other$meStateCode = other.getMeStateCode();
        if (this$meStateCode == null ? other$meStateCode != null : !this$meStateCode.equals(other$meStateCode)) {
            return false;
        }
        String this$meCountry = this.getMeCountry();
        String other$meCountry = other.getMeCountry();
        if (this$meCountry == null ? other$meCountry != null : !this$meCountry.equals(other$meCountry)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode)) {
            return false;
        }
        String this$acqinstIdCode = this.getAcqinstIdCode();
        String other$acqinstIdCode = other.getAcqinstIdCode();
        if (this$acqinstIdCode == null ? other$acqinstIdCode != null : !this$acqinstIdCode.equals(other$acqinstIdCode)) {
            return false;
        }
        String this$trlType = this.getTrlType();
        String other$trlType = other.getTrlType();
        if (this$trlType == null ? other$trlType != null : !this$trlType.equals(other$trlType)) {
            return false;
        }
        LocalDate this$centreProcDate = this.getCentreProcDate();
        LocalDate other$centreProcDate = other.getCentreProcDate();
        if (this$centreProcDate == null ? other$centreProcDate != null : !((Object)this$centreProcDate).equals(other$centreProcDate)) {
            return false;
        }
        LocalDate this$fileProcDate = this.getFileProcDate();
        LocalDate other$fileProcDate = other.getFileProcDate();
        if (this$fileProcDate == null ? other$fileProcDate != null : !((Object)this$fileProcDate).equals(other$fileProcDate)) {
            return false;
        }
        String this$fileID = this.getFileID();
        String other$fileID = other.getFileID();
        if (this$fileID == null ? other$fileID != null : !this$fileID.equals(other$fileID)) {
            return false;
        }
        String this$encryptedCardNumber = this.getEncryptedCardNumber();
        String other$encryptedCardNumber = other.getEncryptedCardNumber();
        if (this$encryptedCardNumber == null ? other$encryptedCardNumber != null : !this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
            return false;
        }
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$motoEcomIndicator = this.getMotoEcomIndicator();
        String other$motoEcomIndicator = other.getMotoEcomIndicator();
        if (this$motoEcomIndicator == null ? other$motoEcomIndicator != null : !this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
            return false;
        }
        LocalDate this$settlDate = this.getSettlDate();
        LocalDate other$settlDate = other.getSettlDate();
        if (this$settlDate == null ? other$settlDate != null : !((Object)this$settlDate).equals(other$settlDate)) {
            return false;
        }
        String this$posConditionCode = this.getPosConditionCode();
        String other$posConditionCode = other.getPosConditionCode();
        return !(this$posConditionCode == null ? other$posConditionCode != null : !this$posConditionCode.equals(other$posConditionCode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanAcqTxnWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + ($prjSerNumber == null ? 43 : ((Object)$prjSerNumber).hashCode());
        Integer $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + ($txnRefNumber == null ? 43 : ((Object)$txnRefNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $settledAmount = this.getSettledAmount();
        result = result * 59 + ($settledAmount == null ? 43 : ((Object)$settledAmount).hashCode());
        Double $billAmount = this.getBillAmount();
        result = result * 59 + ($billAmount == null ? 43 : ((Object)$billAmount).hashCode());
        Double $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + ($surchargeAmount == null ? 43 : ((Object)$surchargeAmount).hashCode());
        Double $convRate = this.getConvRate();
        result = result * 59 + ($convRate == null ? 43 : ((Object)$convRate).hashCode());
        Double $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + ($cashBackAmount == null ? 43 : ((Object)$cashBackAmount).hashCode());
        Character $revIndiCator = this.getRevIndiCator();
        result = result * 59 + ($revIndiCator == null ? 43 : ((Object)$revIndiCator).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Character $meCategoryType = this.getMeCategoryType();
        result = result * 59 + ($meCategoryType == null ? 43 : ((Object)$meCategoryType).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + ($dmsSmsMode == null ? 43 : ((Object)$dmsSmsMode).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        Character $settlIndiCator = this.getSettlIndiCator();
        result = result * 59 + ($settlIndiCator == null ? 43 : ((Object)$settlIndiCator).hashCode());
        Character $fullPartialIndiCator = this.getFullPartialIndiCator();
        result = result * 59 + ($fullPartialIndiCator == null ? 43 : ((Object)$fullPartialIndiCator).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $messageTypeId = this.getMessageTypeId();
        result = result * 59 + ($messageTypeId == null ? 43 : $messageTypeId.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $acqRefData = this.getAcqRefData();
        result = result * 59 + ($acqRefData == null ? 43 : $acqRefData.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meStateCode = this.getMeStateCode();
        result = result * 59 + ($meStateCode == null ? 43 : $meStateCode.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        String $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + ($acqinstIdCode == null ? 43 : $acqinstIdCode.hashCode());
        String $trlType = this.getTrlType();
        result = result * 59 + ($trlType == null ? 43 : $trlType.hashCode());
        LocalDate $centreProcDate = this.getCentreProcDate();
        result = result * 59 + ($centreProcDate == null ? 43 : ((Object)$centreProcDate).hashCode());
        LocalDate $fileProcDate = this.getFileProcDate();
        result = result * 59 + ($fileProcDate == null ? 43 : ((Object)$fileProcDate).hashCode());
        String $fileID = this.getFileID();
        result = result * 59 + ($fileID == null ? 43 : $fileID.hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : $motoEcomIndicator.hashCode());
        LocalDate $settlDate = this.getSettlDate();
        result = result * 59 + ($settlDate == null ? 43 : ((Object)$settlDate).hashCode());
        String $posConditionCode = this.getPosConditionCode();
        result = result * 59 + ($posConditionCode == null ? 43 : $posConditionCode.hashCode());
        return result;
    }

    public String toString() {
        return "JaywanAcqTxnWorkEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", txnRefNumber=" + this.getTxnRefNumber() + ", txnType=" + this.getTxnType() + ", txnCode=" + this.getTxnCode() + ", messageTypeId=" + this.getMessageTypeId() + ", functionCode=" + this.getFunctionCode() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", cardNumber=" + this.getCardNumber() + ", acqRefData=" + this.getAcqRefData() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", txnAmount=" + this.getTxnAmount() + ", settledAmount=" + this.getSettledAmount() + ", billAmount=" + this.getBillAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", convRate=" + this.getConvRate() + ", txnCurCode=" + this.getTxnCurCode() + ", cashBackAmount=" + this.getCashBackAmount() + ", rrn=" + this.getRrn() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meStateCode=" + this.getMeStateCode() + ", meCountry=" + this.getMeCountry() + ", mcc=" + this.getMcc() + ", posEntryMode=" + this.getPosEntryMode() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", revIndiCator=" + this.getRevIndiCator() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", trlType=" + this.getTrlType() + ", meCategoryType=" + this.getMeCategoryType() + ", cardType=" + this.getCardType() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", fileID=" + this.getFileID() + ", genStatus=" + this.getGenStatus() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", responseCode=" + this.getResponseCode() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", settlDate=" + String.valueOf(this.getSettlDate()) + ", settlIndiCator=" + this.getSettlIndiCator() + ", posConditionCode=" + this.getPosConditionCode() + ", fullPartialIndiCator=" + this.getFullPartialIndiCator() + ")";
    }
}

