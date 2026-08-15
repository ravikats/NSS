/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MCRejectionsEntity
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

@Entity
@Table(name="MC_REJECTIONS")
public class MCRejectionsEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MRJ_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MRJ_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MRJ_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MRJ_INS_CODE")
    private Integer institutionCode;
    @Column(name="MRJ_INT_CODE")
    private Integer interfaceCode;
    @Column(name="MRJ_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="MRJ_ORG_SER_NUMBER")
    private Integer orgSerNumber;
    @Column(name="MRJ_MSG_TYPE_ID")
    private String msgTypeId;
    @Column(name="MRJ_MSG_SUB_TYPE")
    private String msgSubType;
    @Column(name="MRJ_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MRJ_PROC_CODE")
    private String procCode;
    @Column(name="MRJ_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MRJ_SETL_AMOUNT")
    private Double setlAmount;
    @Column(name="MRJ_BILL_AMOUNT")
    private Double billAmount;
    @Column(name="MRJ_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name="MRJ_CONV_RATE")
    private Double conversionRate;
    @Column(name="MRJ_STAN")
    private String stan;
    @Column(name="MRJ_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="MRJ_MOTO_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name="MRJ_ME_CATEGORY")
    private String meCategory;
    @Column(name="MRJ_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name="MRJ_ORG_AMOUNT")
    private Double orgAmount;
    @Column(name="MRJ_CASH_BACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name="MRJ_ACQ_INST_ID_CODE")
    private String acqInstIdCode;
    @Column(name="MRJ_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name="MRJ_APPR_CODE")
    private String apprCode;
    @Column(name="MRJ_TERMINAL_ID")
    private String terminalId;
    @Column(name="MRJ_RESP_CODE")
    private String respCode;
    @Column(name="MRJ_ME_CODE")
    private String meCode;
    @Column(name="MRJ_CARD_ACPT_NAME")
    private String cardAcceptName;
    @Column(name="MRJ_CARD_ACPT_CITY")
    private String cardAcceptCity;
    @Column(name="MRJ_CARD_ACPT_COUNTRY")
    private String cardAcceptCounrty;
    @Column(name="MRJ_TXN_CURRENCY")
    private String txnCurrency;
    @Column(name="MRJ_SETL_CURRENCY")
    private String setlCurrency;
    @Column(name="MRJ_BILL_CURRENCY")
    private String billCurrency;
    @Column(name="MRJ_ACQ_REF_NUMBER")
    private String acqRefNumber;
    @Column(name="MRJ_REJECT_FIELD")
    private String rejectedField;
    @Column(name="MRJ_CR_DR_INDICATOR")
    private Character crDrIndicator;
    @Column(name="MRJ_ACQ_ISS_FLAG")
    private Character acqIssFlag;
    @Column(name="MRJ_ONUS_OFFUS_FLAG")
    private Character onusOffusFlag;
    @Column(name="MRJ_REV_INDICATOR")
    private String revIndicator;
    @Column(name="MRJ_CARD_TYPE")
    private Character cardType;
    @Column(name="MRJ_SETL_FLAG")
    private Character setlFlag;
    @Column(name="MRJ_CENTRE_PROC_DATE")
    private LocalDate centerProcDate;
    @Column(name="MRJ_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="MRJ_GEN_STATUS")
    private Integer genStatus;
    @Column(name="MRJ_ENC_CARD_NUMBER")
    private String encryptedCardNumber;

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

    public Integer getInterfaceCode() {
        return this.interfaceCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public Integer getOrgSerNumber() {
        return this.orgSerNumber;
    }

    public String getMsgTypeId() {
        return this.msgTypeId;
    }

    public String getMsgSubType() {
        return this.msgSubType;
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

    public Double getSetlAmount() {
        return this.setlAmount;
    }

    public Double getBillAmount() {
        return this.billAmount;
    }

    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }

    public Double getConversionRate() {
        return this.conversionRate;
    }

    public String getStan() {
        return this.stan;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }

    public String getMeCategory() {
        return this.meCategory;
    }

    public Double getTxnFeeAmount() {
        return this.txnFeeAmount;
    }

    public Double getOrgAmount() {
        return this.orgAmount;
    }

    public Double getCashBackAmount() {
        return this.cashBackAmount;
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

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public String getMeCode() {
        return this.meCode;
    }

    public String getCardAcceptName() {
        return this.cardAcceptName;
    }

    public String getCardAcceptCity() {
        return this.cardAcceptCity;
    }

    public String getCardAcceptCounrty() {
        return this.cardAcceptCounrty;
    }

    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    public String getSetlCurrency() {
        return this.setlCurrency;
    }

    public String getBillCurrency() {
        return this.billCurrency;
    }

    public String getAcqRefNumber() {
        return this.acqRefNumber;
    }

    public String getRejectedField() {
        return this.rejectedField;
    }

    public Character getCrDrIndicator() {
        return this.crDrIndicator;
    }

    public Character getAcqIssFlag() {
        return this.acqIssFlag;
    }

    public Character getOnusOffusFlag() {
        return this.onusOffusFlag;
    }

    public String getRevIndicator() {
        return this.revIndicator;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public Character getSetlFlag() {
        return this.setlFlag;
    }

    public LocalDate getCenterProcDate() {
        return this.centerProcDate;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
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

    public void setInterfaceCode(Integer interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setOrgSerNumber(Integer orgSerNumber) {
        this.orgSerNumber = orgSerNumber;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public void setMsgSubType(String msgSubType) {
        this.msgSubType = msgSubType;
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

    public void setSetlAmount(Double setlAmount) {
        this.setlAmount = setlAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public void setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setMeCategory(String meCategory) {
        this.meCategory = meCategory;
    }

    public void setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }

    public void setOrgAmount(Double orgAmount) {
        this.orgAmount = orgAmount;
    }

    public void setCashBackAmount(Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
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

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setMeCode(String meCode) {
        this.meCode = meCode;
    }

    public void setCardAcceptName(String cardAcceptName) {
        this.cardAcceptName = cardAcceptName;
    }

    public void setCardAcceptCity(String cardAcceptCity) {
        this.cardAcceptCity = cardAcceptCity;
    }

    public void setCardAcceptCounrty(String cardAcceptCounrty) {
        this.cardAcceptCounrty = cardAcceptCounrty;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public void setSetlCurrency(String setlCurrency) {
        this.setlCurrency = setlCurrency;
    }

    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    public void setAcqRefNumber(String acqRefNumber) {
        this.acqRefNumber = acqRefNumber;
    }

    public void setRejectedField(String rejectedField) {
        this.rejectedField = rejectedField;
    }

    public void setCrDrIndicator(Character crDrIndicator) {
        this.crDrIndicator = crDrIndicator;
    }

    public void setAcqIssFlag(Character acqIssFlag) {
        this.acqIssFlag = acqIssFlag;
    }

    public void setOnusOffusFlag(Character onusOffusFlag) {
        this.onusOffusFlag = onusOffusFlag;
    }

    public void setRevIndicator(String revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setSetlFlag(Character setlFlag) {
        this.setlFlag = setlFlag;
    }

    public void setCenterProcDate(LocalDate centerProcDate) {
        this.centerProcDate = centerProcDate;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MCRejectionsEntity)) {
            return false;
        }
        MCRejectionsEntity other = (MCRejectionsEntity)o;
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
        Integer this$interfaceCode = this.getInterfaceCode();
        Integer other$interfaceCode = other.getInterfaceCode();
        if (this$interfaceCode == null ? other$interfaceCode != null : !((Object)this$interfaceCode).equals(other$interfaceCode)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$orgSerNumber = this.getOrgSerNumber();
        Integer other$orgSerNumber = other.getOrgSerNumber();
        if (this$orgSerNumber == null ? other$orgSerNumber != null : !((Object)this$orgSerNumber).equals(other$orgSerNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$setlAmount = this.getSetlAmount();
        Double other$setlAmount = other.getSetlAmount();
        if (this$setlAmount == null ? other$setlAmount != null : !((Object)this$setlAmount).equals(other$setlAmount)) {
            return false;
        }
        Double this$billAmount = this.getBillAmount();
        Double other$billAmount = other.getBillAmount();
        if (this$billAmount == null ? other$billAmount != null : !((Object)this$billAmount).equals(other$billAmount)) {
            return false;
        }
        Double this$conversionRate = this.getConversionRate();
        Double other$conversionRate = other.getConversionRate();
        if (this$conversionRate == null ? other$conversionRate != null : !((Object)this$conversionRate).equals(other$conversionRate)) {
            return false;
        }
        Double this$txnFeeAmount = this.getTxnFeeAmount();
        Double other$txnFeeAmount = other.getTxnFeeAmount();
        if (this$txnFeeAmount == null ? other$txnFeeAmount != null : !((Object)this$txnFeeAmount).equals(other$txnFeeAmount)) {
            return false;
        }
        Double this$orgAmount = this.getOrgAmount();
        Double other$orgAmount = other.getOrgAmount();
        if (this$orgAmount == null ? other$orgAmount != null : !((Object)this$orgAmount).equals(other$orgAmount)) {
            return false;
        }
        Double this$cashBackAmount = this.getCashBackAmount();
        Double other$cashBackAmount = other.getCashBackAmount();
        if (this$cashBackAmount == null ? other$cashBackAmount != null : !((Object)this$cashBackAmount).equals(other$cashBackAmount)) {
            return false;
        }
        Character this$crDrIndicator = this.getCrDrIndicator();
        Character other$crDrIndicator = other.getCrDrIndicator();
        if (this$crDrIndicator == null ? other$crDrIndicator != null : !((Object)this$crDrIndicator).equals(other$crDrIndicator)) {
            return false;
        }
        Character this$acqIssFlag = this.getAcqIssFlag();
        Character other$acqIssFlag = other.getAcqIssFlag();
        if (this$acqIssFlag == null ? other$acqIssFlag != null : !((Object)this$acqIssFlag).equals(other$acqIssFlag)) {
            return false;
        }
        Character this$onusOffusFlag = this.getOnusOffusFlag();
        Character other$onusOffusFlag = other.getOnusOffusFlag();
        if (this$onusOffusFlag == null ? other$onusOffusFlag != null : !((Object)this$onusOffusFlag).equals(other$onusOffusFlag)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Character this$setlFlag = this.getSetlFlag();
        Character other$setlFlag = other.getSetlFlag();
        if (this$setlFlag == null ? other$setlFlag != null : !((Object)this$setlFlag).equals(other$setlFlag)) {
            return false;
        }
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$msgTypeId = this.getMsgTypeId();
        String other$msgTypeId = other.getMsgTypeId();
        if (this$msgTypeId == null ? other$msgTypeId != null : !this$msgTypeId.equals(other$msgTypeId)) {
            return false;
        }
        String this$msgSubType = this.getMsgSubType();
        String other$msgSubType = other.getMsgSubType();
        if (this$msgSubType == null ? other$msgSubType != null : !this$msgSubType.equals(other$msgSubType)) {
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
        LocalDateTime this$txnDateTime = this.getTxnDateTime();
        LocalDateTime other$txnDateTime = other.getTxnDateTime();
        if (this$txnDateTime == null ? other$txnDateTime != null : !((Object)this$txnDateTime).equals(other$txnDateTime)) {
            return false;
        }
        String this$stan = this.getStan();
        String other$stan = other.getStan();
        if (this$stan == null ? other$stan != null : !this$stan.equals(other$stan)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$motoEcomIndicator = this.getMotoEcomIndicator();
        String other$motoEcomIndicator = other.getMotoEcomIndicator();
        if (this$motoEcomIndicator == null ? other$motoEcomIndicator != null : !this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
            return false;
        }
        String this$meCategory = this.getMeCategory();
        String other$meCategory = other.getMeCategory();
        if (this$meCategory == null ? other$meCategory != null : !this$meCategory.equals(other$meCategory)) {
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
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$respCode = this.getRespCode();
        String other$respCode = other.getRespCode();
        if (this$respCode == null ? other$respCode != null : !this$respCode.equals(other$respCode)) {
            return false;
        }
        String this$meCode = this.getMeCode();
        String other$meCode = other.getMeCode();
        if (this$meCode == null ? other$meCode != null : !this$meCode.equals(other$meCode)) {
            return false;
        }
        String this$cardAcceptName = this.getCardAcceptName();
        String other$cardAcceptName = other.getCardAcceptName();
        if (this$cardAcceptName == null ? other$cardAcceptName != null : !this$cardAcceptName.equals(other$cardAcceptName)) {
            return false;
        }
        String this$cardAcceptCity = this.getCardAcceptCity();
        String other$cardAcceptCity = other.getCardAcceptCity();
        if (this$cardAcceptCity == null ? other$cardAcceptCity != null : !this$cardAcceptCity.equals(other$cardAcceptCity)) {
            return false;
        }
        String this$cardAcceptCounrty = this.getCardAcceptCounrty();
        String other$cardAcceptCounrty = other.getCardAcceptCounrty();
        if (this$cardAcceptCounrty == null ? other$cardAcceptCounrty != null : !this$cardAcceptCounrty.equals(other$cardAcceptCounrty)) {
            return false;
        }
        String this$txnCurrency = this.getTxnCurrency();
        String other$txnCurrency = other.getTxnCurrency();
        if (this$txnCurrency == null ? other$txnCurrency != null : !this$txnCurrency.equals(other$txnCurrency)) {
            return false;
        }
        String this$setlCurrency = this.getSetlCurrency();
        String other$setlCurrency = other.getSetlCurrency();
        if (this$setlCurrency == null ? other$setlCurrency != null : !this$setlCurrency.equals(other$setlCurrency)) {
            return false;
        }
        String this$billCurrency = this.getBillCurrency();
        String other$billCurrency = other.getBillCurrency();
        if (this$billCurrency == null ? other$billCurrency != null : !this$billCurrency.equals(other$billCurrency)) {
            return false;
        }
        String this$acqRefNumber = this.getAcqRefNumber();
        String other$acqRefNumber = other.getAcqRefNumber();
        if (this$acqRefNumber == null ? other$acqRefNumber != null : !this$acqRefNumber.equals(other$acqRefNumber)) {
            return false;
        }
        String this$rejectedField = this.getRejectedField();
        String other$rejectedField = other.getRejectedField();
        if (this$rejectedField == null ? other$rejectedField != null : !this$rejectedField.equals(other$rejectedField)) {
            return false;
        }
        String this$revIndicator = this.getRevIndicator();
        String other$revIndicator = other.getRevIndicator();
        if (this$revIndicator == null ? other$revIndicator != null : !this$revIndicator.equals(other$revIndicator)) {
            return false;
        }
        LocalDate this$centerProcDate = this.getCenterProcDate();
        LocalDate other$centerProcDate = other.getCenterProcDate();
        if (this$centerProcDate == null ? other$centerProcDate != null : !((Object)this$centerProcDate).equals(other$centerProcDate)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$encryptedCardNumber = this.getEncryptedCardNumber();
        String other$encryptedCardNumber = other.getEncryptedCardNumber();
        return !(this$encryptedCardNumber == null ? other$encryptedCardNumber != null : !this$encryptedCardNumber.equals(other$encryptedCardNumber));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MCRejectionsEntity;
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
        Integer $interfaceCode = this.getInterfaceCode();
        result = result * 59 + ($interfaceCode == null ? 43 : ((Object)$interfaceCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $orgSerNumber = this.getOrgSerNumber();
        result = result * 59 + ($orgSerNumber == null ? 43 : ((Object)$orgSerNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $setlAmount = this.getSetlAmount();
        result = result * 59 + ($setlAmount == null ? 43 : ((Object)$setlAmount).hashCode());
        Double $billAmount = this.getBillAmount();
        result = result * 59 + ($billAmount == null ? 43 : ((Object)$billAmount).hashCode());
        Double $conversionRate = this.getConversionRate();
        result = result * 59 + ($conversionRate == null ? 43 : ((Object)$conversionRate).hashCode());
        Double $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + ($txnFeeAmount == null ? 43 : ((Object)$txnFeeAmount).hashCode());
        Double $orgAmount = this.getOrgAmount();
        result = result * 59 + ($orgAmount == null ? 43 : ((Object)$orgAmount).hashCode());
        Double $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + ($cashBackAmount == null ? 43 : ((Object)$cashBackAmount).hashCode());
        Character $crDrIndicator = this.getCrDrIndicator();
        result = result * 59 + ($crDrIndicator == null ? 43 : ((Object)$crDrIndicator).hashCode());
        Character $acqIssFlag = this.getAcqIssFlag();
        result = result * 59 + ($acqIssFlag == null ? 43 : ((Object)$acqIssFlag).hashCode());
        Character $onusOffusFlag = this.getOnusOffusFlag();
        result = result * 59 + ($onusOffusFlag == null ? 43 : ((Object)$onusOffusFlag).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $setlFlag = this.getSetlFlag();
        result = result * 59 + ($setlFlag == null ? 43 : ((Object)$setlFlag).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $msgTypeId = this.getMsgTypeId();
        result = result * 59 + ($msgTypeId == null ? 43 : $msgTypeId.hashCode());
        String $msgSubType = this.getMsgSubType();
        result = result * 59 + ($msgSubType == null ? 43 : $msgSubType.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        LocalDateTime $txnDateTime = this.getTxnDateTime();
        result = result * 59 + ($txnDateTime == null ? 43 : ((Object)$txnDateTime).hashCode());
        String $stan = this.getStan();
        result = result * 59 + ($stan == null ? 43 : $stan.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : $motoEcomIndicator.hashCode());
        String $meCategory = this.getMeCategory();
        result = result * 59 + ($meCategory == null ? 43 : $meCategory.hashCode());
        String $acqInstIdCode = this.getAcqInstIdCode();
        result = result * 59 + ($acqInstIdCode == null ? 43 : $acqInstIdCode.hashCode());
        String $retRefNumber = this.getRetRefNumber();
        result = result * 59 + ($retRefNumber == null ? 43 : $retRefNumber.hashCode());
        String $apprCode = this.getApprCode();
        result = result * 59 + ($apprCode == null ? 43 : $apprCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $respCode = this.getRespCode();
        result = result * 59 + ($respCode == null ? 43 : $respCode.hashCode());
        String $meCode = this.getMeCode();
        result = result * 59 + ($meCode == null ? 43 : $meCode.hashCode());
        String $cardAcceptName = this.getCardAcceptName();
        result = result * 59 + ($cardAcceptName == null ? 43 : $cardAcceptName.hashCode());
        String $cardAcceptCity = this.getCardAcceptCity();
        result = result * 59 + ($cardAcceptCity == null ? 43 : $cardAcceptCity.hashCode());
        String $cardAcceptCounrty = this.getCardAcceptCounrty();
        result = result * 59 + ($cardAcceptCounrty == null ? 43 : $cardAcceptCounrty.hashCode());
        String $txnCurrency = this.getTxnCurrency();
        result = result * 59 + ($txnCurrency == null ? 43 : $txnCurrency.hashCode());
        String $setlCurrency = this.getSetlCurrency();
        result = result * 59 + ($setlCurrency == null ? 43 : $setlCurrency.hashCode());
        String $billCurrency = this.getBillCurrency();
        result = result * 59 + ($billCurrency == null ? 43 : $billCurrency.hashCode());
        String $acqRefNumber = this.getAcqRefNumber();
        result = result * 59 + ($acqRefNumber == null ? 43 : $acqRefNumber.hashCode());
        String $rejectedField = this.getRejectedField();
        result = result * 59 + ($rejectedField == null ? 43 : $rejectedField.hashCode());
        String $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : $revIndicator.hashCode());
        LocalDate $centerProcDate = this.getCenterProcDate();
        result = result * 59 + ($centerProcDate == null ? 43 : ((Object)$centerProcDate).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        return result;
    }

    public String toString() {
        return "MCRejectionsEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", interfaceCode=" + this.getInterfaceCode() + ", jobNumber=" + this.getJobNumber() + ", orgSerNumber=" + this.getOrgSerNumber() + ", msgTypeId=" + this.getMsgTypeId() + ", msgSubType=" + this.getMsgSubType() + ", cardNumber=" + this.getCardNumber() + ", procCode=" + this.getProcCode() + ", txnAmount=" + this.getTxnAmount() + ", setlAmount=" + this.getSetlAmount() + ", billAmount=" + this.getBillAmount() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", conversionRate=" + this.getConversionRate() + ", stan=" + this.getStan() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", meCategory=" + this.getMeCategory() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", orgAmount=" + this.getOrgAmount() + ", cashBackAmount=" + this.getCashBackAmount() + ", acqInstIdCode=" + this.getAcqInstIdCode() + ", retRefNumber=" + this.getRetRefNumber() + ", apprCode=" + this.getApprCode() + ", terminalId=" + this.getTerminalId() + ", respCode=" + this.getRespCode() + ", meCode=" + this.getMeCode() + ", cardAcceptName=" + this.getCardAcceptName() + ", cardAcceptCity=" + this.getCardAcceptCity() + ", cardAcceptCounrty=" + this.getCardAcceptCounrty() + ", txnCurrency=" + this.getTxnCurrency() + ", setlCurrency=" + this.getSetlCurrency() + ", billCurrency=" + this.getBillCurrency() + ", acqRefNumber=" + this.getAcqRefNumber() + ", rejectedField=" + this.getRejectedField() + ", crDrIndicator=" + this.getCrDrIndicator() + ", acqIssFlag=" + this.getAcqIssFlag() + ", onusOffusFlag=" + this.getOnusOffusFlag() + ", revIndicator=" + this.getRevIndicator() + ", cardType=" + this.getCardType() + ", setlFlag=" + this.getSetlFlag() + ", centerProcDate=" + String.valueOf(this.getCenterProcDate()) + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", genStatus=" + this.getGenStatus() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ")";
    }
}

