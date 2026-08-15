/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MCAcquirerExceptionsEntity
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
@Table(name="MC_ACQ_EXCEPTIONS")
public class MCAcquirerExceptionsEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MAX_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MAX_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MAX_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MAX_INS_CODE")
    private Integer institutionCode;
    @Column(name="MAX_INT_CODE")
    private Integer interfaceCode;
    @Column(name="MAX_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="MAX_ORG_SER_NUMBER")
    private Integer orgSerNumber;
    @Column(name="MAX_FILE_ID")
    private String fileId;
    @Column(name="MAX_MESG_TYPE_ID")
    private String msgTypeId;
    @Column(name="MAX_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MAX_PROC_CODE")
    private String processCode;
    @Column(name="MAX_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MAX_RECON_AMOUNT")
    private Double reconAmount;
    @Column(name="MAX_CONV_RATE")
    private Double conversionRate;
    @Column(name="MAX_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="MAX_POS_DATA")
    private String posData;
    @Column(name="MAX_FUNC_CODE")
    private String functionCode;
    @Column(name="MAX_MSG_REASON_CODE")
    private Integer msgReasonCode;
    @Column(name="MAX_ME_CATEGORY")
    private String meCategory;
    @Column(name="MAX_ORG_AMOUNT")
    private Double orgAmount;
    @Column(name="MAX_ACQ_REF_NUMBER")
    private String acqRefNumber;
    @Column(name="MAX_ACQ_INST_ID_CODE")
    private String acqInstIdCode;
    @Column(name="MAX_FWD_INST_ID_CODE")
    private String fwdInstIdCode;
    @Column(name="MAX_APPR_CODE")
    private String apprCode;
    @Column(name="MAX_SERVICE_CODE")
    private String serviceCode;
    @Column(name="MAX_TERMINAL_ID")
    private String terminalId;
    @Column(name="MAX_ME_CODE")
    private String meCode;
    @Column(name="MAX_CARD_ACPT_NAME")
    private String cardAccetName;
    @Column(name="MAX_CARD_ACPT_CITY")
    private String cardAccetCity;
    @Column(name="MAX_CARD_ACPT_COUNTRY")
    private String cardAccetCountry;
    @Column(name="MAX_TXN_CURRENCY")
    private String txnCurrency;
    @Column(name="MAX_RECON_CURRENCY")
    private String reconCurrency;
    @Column(name="MAX_TXN_LIFE_CYCL_ID")
    private String txnLifeCycleId;
    @Column(name="MAX_MSG_NUMBER")
    private String msgNumber;
    @Column(name="MAX_MEMBER_TEXT")
    private String memberText;
    @Column(name="MAX_TXN_DST_INST_ID_CODE")
    private String txnDstInstIdCode;
    @Column(name="MAX_TXN_ORG_INST_ID_CODE")
    private String txnOrgInstIdCode;
    @Column(name="MAX_RCV_INST_ID_CODE")
    private String rcvInstIdCode;
    @Column(name="MAX_ISS_REF_NUMBER")
    private String issRefNumber;
    @Column(name="MAX_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name="MAX_REV_INDICATOR")
    private Character revIndicator;
    @Column(name="MAX_COMM_INDICATOR")
    private String maxCommIndicator;
    @Column(name="MAX_CURR_EXPONENTS")
    private String currExponents;
    @Column(name="MAX_ORG_CURRENCY")
    private String orgCurrency;
    @Column(name="MAX_BUSS_ACTIVITY")
    private String bussActivity;
    @Column(name="MAX_SETTLEMENT_INDICATOR")
    private String settlementIndicator;
    @Column(name="MAX_DOC_INDICATOR")
    private String docIndicator;
    @Column(name="MAX_CROSS_BORDER_INDICATOR")
    private Character crossBorderIndicator;
    @Column(name="MAX_MSG_REF_NUMBER")
    private String msgRefNumber;
    @Column(name="MAX_STATUS_CODE")
    private Integer statusCode;
    @Column(name="MAX_ACTION_CODE")
    private Integer actionCode;
    @Column(name="MAX_NEXT_ACTION_ON")
    private Date nextActionOn;
    @Column(name="MAX_PRIORITY")
    private Integer priority;
    @Column(name="MAX_ACTIVE_STATUS")
    private Integer activeStatus;
    @Column(name="MAX_REMARKS")
    private String remarks;
    @Column(name="MAX_CENTRE_PROC_DATE")
    private LocalDate centerProcessDate;
    @Column(name="MAX_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="MAX_GEN_STATUS")
    private Integer genStatus;
    @Column(name="MAX_ENC_CARD_NUMBER")
    private String encyptedCardNumber;
    @Column(name="MAX_CASE_ID")
    private String caseId;

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

    public String getFileId() {
        return this.fileId;
    }

    public String getMsgTypeId() {
        return this.msgTypeId;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcessCode() {
        return this.processCode;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getReconAmount() {
        return this.reconAmount;
    }

    public Double getConversionRate() {
        return this.conversionRate;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public String getPosData() {
        return this.posData;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public Integer getMsgReasonCode() {
        return this.msgReasonCode;
    }

    public String getMeCategory() {
        return this.meCategory;
    }

    public Double getOrgAmount() {
        return this.orgAmount;
    }

    public String getAcqRefNumber() {
        return this.acqRefNumber;
    }

    public String getAcqInstIdCode() {
        return this.acqInstIdCode;
    }

    public String getFwdInstIdCode() {
        return this.fwdInstIdCode;
    }

    public String getApprCode() {
        return this.apprCode;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getMeCode() {
        return this.meCode;
    }

    public String getCardAccetName() {
        return this.cardAccetName;
    }

    public String getCardAccetCity() {
        return this.cardAccetCity;
    }

    public String getCardAccetCountry() {
        return this.cardAccetCountry;
    }

    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    public String getReconCurrency() {
        return this.reconCurrency;
    }

    public String getTxnLifeCycleId() {
        return this.txnLifeCycleId;
    }

    public String getMsgNumber() {
        return this.msgNumber;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public String getTxnDstInstIdCode() {
        return this.txnDstInstIdCode;
    }

    public String getTxnOrgInstIdCode() {
        return this.txnOrgInstIdCode;
    }

    public String getRcvInstIdCode() {
        return this.rcvInstIdCode;
    }

    public String getIssRefNumber() {
        return this.issRefNumber;
    }

    public String getRetRefNumber() {
        return this.retRefNumber;
    }

    public Character getRevIndicator() {
        return this.revIndicator;
    }

    public String getMaxCommIndicator() {
        return this.maxCommIndicator;
    }

    public String getCurrExponents() {
        return this.currExponents;
    }

    public String getOrgCurrency() {
        return this.orgCurrency;
    }

    public String getBussActivity() {
        return this.bussActivity;
    }

    public String getSettlementIndicator() {
        return this.settlementIndicator;
    }

    public String getDocIndicator() {
        return this.docIndicator;
    }

    public Character getCrossBorderIndicator() {
        return this.crossBorderIndicator;
    }

    public String getMsgRefNumber() {
        return this.msgRefNumber;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public Integer getActionCode() {
        return this.actionCode;
    }

    public Date getNextActionOn() {
        return this.nextActionOn;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public Integer getActiveStatus() {
        return this.activeStatus;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public LocalDate getCenterProcessDate() {
        return this.centerProcessDate;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getEncyptedCardNumber() {
        return this.encyptedCardNumber;
    }

    public String getCaseId() {
        return this.caseId;
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

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setReconAmount(Double reconAmount) {
        this.reconAmount = reconAmount;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setPosData(String posData) {
        this.posData = posData;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setMsgReasonCode(Integer msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }

    public void setMeCategory(String meCategory) {
        this.meCategory = meCategory;
    }

    public void setOrgAmount(Double orgAmount) {
        this.orgAmount = orgAmount;
    }

    public void setAcqRefNumber(String acqRefNumber) {
        this.acqRefNumber = acqRefNumber;
    }

    public void setAcqInstIdCode(String acqInstIdCode) {
        this.acqInstIdCode = acqInstIdCode;
    }

    public void setFwdInstIdCode(String fwdInstIdCode) {
        this.fwdInstIdCode = fwdInstIdCode;
    }

    public void setApprCode(String apprCode) {
        this.apprCode = apprCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMeCode(String meCode) {
        this.meCode = meCode;
    }

    public void setCardAccetName(String cardAccetName) {
        this.cardAccetName = cardAccetName;
    }

    public void setCardAccetCity(String cardAccetCity) {
        this.cardAccetCity = cardAccetCity;
    }

    public void setCardAccetCountry(String cardAccetCountry) {
        this.cardAccetCountry = cardAccetCountry;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public void setReconCurrency(String reconCurrency) {
        this.reconCurrency = reconCurrency;
    }

    public void setTxnLifeCycleId(String txnLifeCycleId) {
        this.txnLifeCycleId = txnLifeCycleId;
    }

    public void setMsgNumber(String msgNumber) {
        this.msgNumber = msgNumber;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setTxnDstInstIdCode(String txnDstInstIdCode) {
        this.txnDstInstIdCode = txnDstInstIdCode;
    }

    public void setTxnOrgInstIdCode(String txnOrgInstIdCode) {
        this.txnOrgInstIdCode = txnOrgInstIdCode;
    }

    public void setRcvInstIdCode(String rcvInstIdCode) {
        this.rcvInstIdCode = rcvInstIdCode;
    }

    public void setIssRefNumber(String issRefNumber) {
        this.issRefNumber = issRefNumber;
    }

    public void setRetRefNumber(String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }

    public void setRevIndicator(Character revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setMaxCommIndicator(String maxCommIndicator) {
        this.maxCommIndicator = maxCommIndicator;
    }

    public void setCurrExponents(String currExponents) {
        this.currExponents = currExponents;
    }

    public void setOrgCurrency(String orgCurrency) {
        this.orgCurrency = orgCurrency;
    }

    public void setBussActivity(String bussActivity) {
        this.bussActivity = bussActivity;
    }

    public void setSettlementIndicator(String settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }

    public void setDocIndicator(String docIndicator) {
        this.docIndicator = docIndicator;
    }

    public void setCrossBorderIndicator(Character crossBorderIndicator) {
        this.crossBorderIndicator = crossBorderIndicator;
    }

    public void setMsgRefNumber(String msgRefNumber) {
        this.msgRefNumber = msgRefNumber;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setActionCode(Integer actionCode) {
        this.actionCode = actionCode;
    }

    public void setNextActionOn(Date nextActionOn) {
        this.nextActionOn = nextActionOn;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setCenterProcessDate(LocalDate centerProcessDate) {
        this.centerProcessDate = centerProcessDate;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setEncyptedCardNumber(String encyptedCardNumber) {
        this.encyptedCardNumber = encyptedCardNumber;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MCAcquirerExceptionsEntity)) {
            return false;
        }
        MCAcquirerExceptionsEntity other = (MCAcquirerExceptionsEntity)o;
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
        Double this$reconAmount = this.getReconAmount();
        Double other$reconAmount = other.getReconAmount();
        if (this$reconAmount == null ? other$reconAmount != null : !((Object)this$reconAmount).equals(other$reconAmount)) {
            return false;
        }
        Double this$conversionRate = this.getConversionRate();
        Double other$conversionRate = other.getConversionRate();
        if (this$conversionRate == null ? other$conversionRate != null : !((Object)this$conversionRate).equals(other$conversionRate)) {
            return false;
        }
        Integer this$msgReasonCode = this.getMsgReasonCode();
        Integer other$msgReasonCode = other.getMsgReasonCode();
        if (this$msgReasonCode == null ? other$msgReasonCode != null : !((Object)this$msgReasonCode).equals(other$msgReasonCode)) {
            return false;
        }
        Double this$orgAmount = this.getOrgAmount();
        Double other$orgAmount = other.getOrgAmount();
        if (this$orgAmount == null ? other$orgAmount != null : !((Object)this$orgAmount).equals(other$orgAmount)) {
            return false;
        }
        Character this$revIndicator = this.getRevIndicator();
        Character other$revIndicator = other.getRevIndicator();
        if (this$revIndicator == null ? other$revIndicator != null : !((Object)this$revIndicator).equals(other$revIndicator)) {
            return false;
        }
        Character this$crossBorderIndicator = this.getCrossBorderIndicator();
        Character other$crossBorderIndicator = other.getCrossBorderIndicator();
        if (this$crossBorderIndicator == null ? other$crossBorderIndicator != null : !((Object)this$crossBorderIndicator).equals(other$crossBorderIndicator)) {
            return false;
        }
        Integer this$statusCode = this.getStatusCode();
        Integer other$statusCode = other.getStatusCode();
        if (this$statusCode == null ? other$statusCode != null : !((Object)this$statusCode).equals(other$statusCode)) {
            return false;
        }
        Integer this$actionCode = this.getActionCode();
        Integer other$actionCode = other.getActionCode();
        if (this$actionCode == null ? other$actionCode != null : !((Object)this$actionCode).equals(other$actionCode)) {
            return false;
        }
        Integer this$priority = this.getPriority();
        Integer other$priority = other.getPriority();
        if (this$priority == null ? other$priority != null : !((Object)this$priority).equals(other$priority)) {
            return false;
        }
        Integer this$activeStatus = this.getActiveStatus();
        Integer other$activeStatus = other.getActiveStatus();
        if (this$activeStatus == null ? other$activeStatus != null : !((Object)this$activeStatus).equals(other$activeStatus)) {
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
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
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
        String this$processCode = this.getProcessCode();
        String other$processCode = other.getProcessCode();
        if (this$processCode == null ? other$processCode != null : !this$processCode.equals(other$processCode)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$posData = this.getPosData();
        String other$posData = other.getPosData();
        if (this$posData == null ? other$posData != null : !this$posData.equals(other$posData)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        String this$meCategory = this.getMeCategory();
        String other$meCategory = other.getMeCategory();
        if (this$meCategory == null ? other$meCategory != null : !this$meCategory.equals(other$meCategory)) {
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
        String this$fwdInstIdCode = this.getFwdInstIdCode();
        String other$fwdInstIdCode = other.getFwdInstIdCode();
        if (this$fwdInstIdCode == null ? other$fwdInstIdCode != null : !this$fwdInstIdCode.equals(other$fwdInstIdCode)) {
            return false;
        }
        String this$apprCode = this.getApprCode();
        String other$apprCode = other.getApprCode();
        if (this$apprCode == null ? other$apprCode != null : !this$apprCode.equals(other$apprCode)) {
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
        String this$meCode = this.getMeCode();
        String other$meCode = other.getMeCode();
        if (this$meCode == null ? other$meCode != null : !this$meCode.equals(other$meCode)) {
            return false;
        }
        String this$cardAccetName = this.getCardAccetName();
        String other$cardAccetName = other.getCardAccetName();
        if (this$cardAccetName == null ? other$cardAccetName != null : !this$cardAccetName.equals(other$cardAccetName)) {
            return false;
        }
        String this$cardAccetCity = this.getCardAccetCity();
        String other$cardAccetCity = other.getCardAccetCity();
        if (this$cardAccetCity == null ? other$cardAccetCity != null : !this$cardAccetCity.equals(other$cardAccetCity)) {
            return false;
        }
        String this$cardAccetCountry = this.getCardAccetCountry();
        String other$cardAccetCountry = other.getCardAccetCountry();
        if (this$cardAccetCountry == null ? other$cardAccetCountry != null : !this$cardAccetCountry.equals(other$cardAccetCountry)) {
            return false;
        }
        String this$txnCurrency = this.getTxnCurrency();
        String other$txnCurrency = other.getTxnCurrency();
        if (this$txnCurrency == null ? other$txnCurrency != null : !this$txnCurrency.equals(other$txnCurrency)) {
            return false;
        }
        String this$reconCurrency = this.getReconCurrency();
        String other$reconCurrency = other.getReconCurrency();
        if (this$reconCurrency == null ? other$reconCurrency != null : !this$reconCurrency.equals(other$reconCurrency)) {
            return false;
        }
        String this$txnLifeCycleId = this.getTxnLifeCycleId();
        String other$txnLifeCycleId = other.getTxnLifeCycleId();
        if (this$txnLifeCycleId == null ? other$txnLifeCycleId != null : !this$txnLifeCycleId.equals(other$txnLifeCycleId)) {
            return false;
        }
        String this$msgNumber = this.getMsgNumber();
        String other$msgNumber = other.getMsgNumber();
        if (this$msgNumber == null ? other$msgNumber != null : !this$msgNumber.equals(other$msgNumber)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
            return false;
        }
        String this$txnDstInstIdCode = this.getTxnDstInstIdCode();
        String other$txnDstInstIdCode = other.getTxnDstInstIdCode();
        if (this$txnDstInstIdCode == null ? other$txnDstInstIdCode != null : !this$txnDstInstIdCode.equals(other$txnDstInstIdCode)) {
            return false;
        }
        String this$txnOrgInstIdCode = this.getTxnOrgInstIdCode();
        String other$txnOrgInstIdCode = other.getTxnOrgInstIdCode();
        if (this$txnOrgInstIdCode == null ? other$txnOrgInstIdCode != null : !this$txnOrgInstIdCode.equals(other$txnOrgInstIdCode)) {
            return false;
        }
        String this$rcvInstIdCode = this.getRcvInstIdCode();
        String other$rcvInstIdCode = other.getRcvInstIdCode();
        if (this$rcvInstIdCode == null ? other$rcvInstIdCode != null : !this$rcvInstIdCode.equals(other$rcvInstIdCode)) {
            return false;
        }
        String this$issRefNumber = this.getIssRefNumber();
        String other$issRefNumber = other.getIssRefNumber();
        if (this$issRefNumber == null ? other$issRefNumber != null : !this$issRefNumber.equals(other$issRefNumber)) {
            return false;
        }
        String this$retRefNumber = this.getRetRefNumber();
        String other$retRefNumber = other.getRetRefNumber();
        if (this$retRefNumber == null ? other$retRefNumber != null : !this$retRefNumber.equals(other$retRefNumber)) {
            return false;
        }
        String this$maxCommIndicator = this.getMaxCommIndicator();
        String other$maxCommIndicator = other.getMaxCommIndicator();
        if (this$maxCommIndicator == null ? other$maxCommIndicator != null : !this$maxCommIndicator.equals(other$maxCommIndicator)) {
            return false;
        }
        String this$currExponents = this.getCurrExponents();
        String other$currExponents = other.getCurrExponents();
        if (this$currExponents == null ? other$currExponents != null : !this$currExponents.equals(other$currExponents)) {
            return false;
        }
        String this$orgCurrency = this.getOrgCurrency();
        String other$orgCurrency = other.getOrgCurrency();
        if (this$orgCurrency == null ? other$orgCurrency != null : !this$orgCurrency.equals(other$orgCurrency)) {
            return false;
        }
        String this$bussActivity = this.getBussActivity();
        String other$bussActivity = other.getBussActivity();
        if (this$bussActivity == null ? other$bussActivity != null : !this$bussActivity.equals(other$bussActivity)) {
            return false;
        }
        String this$settlementIndicator = this.getSettlementIndicator();
        String other$settlementIndicator = other.getSettlementIndicator();
        if (this$settlementIndicator == null ? other$settlementIndicator != null : !this$settlementIndicator.equals(other$settlementIndicator)) {
            return false;
        }
        String this$docIndicator = this.getDocIndicator();
        String other$docIndicator = other.getDocIndicator();
        if (this$docIndicator == null ? other$docIndicator != null : !this$docIndicator.equals(other$docIndicator)) {
            return false;
        }
        String this$msgRefNumber = this.getMsgRefNumber();
        String other$msgRefNumber = other.getMsgRefNumber();
        if (this$msgRefNumber == null ? other$msgRefNumber != null : !this$msgRefNumber.equals(other$msgRefNumber)) {
            return false;
        }
        Date this$nextActionOn = this.getNextActionOn();
        Date other$nextActionOn = other.getNextActionOn();
        if (this$nextActionOn == null ? other$nextActionOn != null : !((Object)this$nextActionOn).equals(other$nextActionOn)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        if (this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks)) {
            return false;
        }
        LocalDate this$centerProcessDate = this.getCenterProcessDate();
        LocalDate other$centerProcessDate = other.getCenterProcessDate();
        if (this$centerProcessDate == null ? other$centerProcessDate != null : !((Object)this$centerProcessDate).equals(other$centerProcessDate)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$encyptedCardNumber = this.getEncyptedCardNumber();
        String other$encyptedCardNumber = other.getEncyptedCardNumber();
        if (this$encyptedCardNumber == null ? other$encyptedCardNumber != null : !this$encyptedCardNumber.equals(other$encyptedCardNumber)) {
            return false;
        }
        String this$caseId = this.getCaseId();
        String other$caseId = other.getCaseId();
        return !(this$caseId == null ? other$caseId != null : !this$caseId.equals(other$caseId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MCAcquirerExceptionsEntity;
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
        Double $reconAmount = this.getReconAmount();
        result = result * 59 + ($reconAmount == null ? 43 : ((Object)$reconAmount).hashCode());
        Double $conversionRate = this.getConversionRate();
        result = result * 59 + ($conversionRate == null ? 43 : ((Object)$conversionRate).hashCode());
        Integer $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + ($msgReasonCode == null ? 43 : ((Object)$msgReasonCode).hashCode());
        Double $orgAmount = this.getOrgAmount();
        result = result * 59 + ($orgAmount == null ? 43 : ((Object)$orgAmount).hashCode());
        Character $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : ((Object)$revIndicator).hashCode());
        Character $crossBorderIndicator = this.getCrossBorderIndicator();
        result = result * 59 + ($crossBorderIndicator == null ? 43 : ((Object)$crossBorderIndicator).hashCode());
        Integer $statusCode = this.getStatusCode();
        result = result * 59 + ($statusCode == null ? 43 : ((Object)$statusCode).hashCode());
        Integer $actionCode = this.getActionCode();
        result = result * 59 + ($actionCode == null ? 43 : ((Object)$actionCode).hashCode());
        Integer $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : ((Object)$priority).hashCode());
        Integer $activeStatus = this.getActiveStatus();
        result = result * 59 + ($activeStatus == null ? 43 : ((Object)$activeStatus).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $msgTypeId = this.getMsgTypeId();
        result = result * 59 + ($msgTypeId == null ? 43 : $msgTypeId.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $processCode = this.getProcessCode();
        result = result * 59 + ($processCode == null ? 43 : $processCode.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $posData = this.getPosData();
        result = result * 59 + ($posData == null ? 43 : $posData.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        String $meCategory = this.getMeCategory();
        result = result * 59 + ($meCategory == null ? 43 : $meCategory.hashCode());
        String $acqRefNumber = this.getAcqRefNumber();
        result = result * 59 + ($acqRefNumber == null ? 43 : $acqRefNumber.hashCode());
        String $acqInstIdCode = this.getAcqInstIdCode();
        result = result * 59 + ($acqInstIdCode == null ? 43 : $acqInstIdCode.hashCode());
        String $fwdInstIdCode = this.getFwdInstIdCode();
        result = result * 59 + ($fwdInstIdCode == null ? 43 : $fwdInstIdCode.hashCode());
        String $apprCode = this.getApprCode();
        result = result * 59 + ($apprCode == null ? 43 : $apprCode.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $meCode = this.getMeCode();
        result = result * 59 + ($meCode == null ? 43 : $meCode.hashCode());
        String $cardAccetName = this.getCardAccetName();
        result = result * 59 + ($cardAccetName == null ? 43 : $cardAccetName.hashCode());
        String $cardAccetCity = this.getCardAccetCity();
        result = result * 59 + ($cardAccetCity == null ? 43 : $cardAccetCity.hashCode());
        String $cardAccetCountry = this.getCardAccetCountry();
        result = result * 59 + ($cardAccetCountry == null ? 43 : $cardAccetCountry.hashCode());
        String $txnCurrency = this.getTxnCurrency();
        result = result * 59 + ($txnCurrency == null ? 43 : $txnCurrency.hashCode());
        String $reconCurrency = this.getReconCurrency();
        result = result * 59 + ($reconCurrency == null ? 43 : $reconCurrency.hashCode());
        String $txnLifeCycleId = this.getTxnLifeCycleId();
        result = result * 59 + ($txnLifeCycleId == null ? 43 : $txnLifeCycleId.hashCode());
        String $msgNumber = this.getMsgNumber();
        result = result * 59 + ($msgNumber == null ? 43 : $msgNumber.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $txnDstInstIdCode = this.getTxnDstInstIdCode();
        result = result * 59 + ($txnDstInstIdCode == null ? 43 : $txnDstInstIdCode.hashCode());
        String $txnOrgInstIdCode = this.getTxnOrgInstIdCode();
        result = result * 59 + ($txnOrgInstIdCode == null ? 43 : $txnOrgInstIdCode.hashCode());
        String $rcvInstIdCode = this.getRcvInstIdCode();
        result = result * 59 + ($rcvInstIdCode == null ? 43 : $rcvInstIdCode.hashCode());
        String $issRefNumber = this.getIssRefNumber();
        result = result * 59 + ($issRefNumber == null ? 43 : $issRefNumber.hashCode());
        String $retRefNumber = this.getRetRefNumber();
        result = result * 59 + ($retRefNumber == null ? 43 : $retRefNumber.hashCode());
        String $maxCommIndicator = this.getMaxCommIndicator();
        result = result * 59 + ($maxCommIndicator == null ? 43 : $maxCommIndicator.hashCode());
        String $currExponents = this.getCurrExponents();
        result = result * 59 + ($currExponents == null ? 43 : $currExponents.hashCode());
        String $orgCurrency = this.getOrgCurrency();
        result = result * 59 + ($orgCurrency == null ? 43 : $orgCurrency.hashCode());
        String $bussActivity = this.getBussActivity();
        result = result * 59 + ($bussActivity == null ? 43 : $bussActivity.hashCode());
        String $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + ($settlementIndicator == null ? 43 : $settlementIndicator.hashCode());
        String $docIndicator = this.getDocIndicator();
        result = result * 59 + ($docIndicator == null ? 43 : $docIndicator.hashCode());
        String $msgRefNumber = this.getMsgRefNumber();
        result = result * 59 + ($msgRefNumber == null ? 43 : $msgRefNumber.hashCode());
        Date $nextActionOn = this.getNextActionOn();
        result = result * 59 + ($nextActionOn == null ? 43 : ((Object)$nextActionOn).hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        LocalDate $centerProcessDate = this.getCenterProcessDate();
        result = result * 59 + ($centerProcessDate == null ? 43 : ((Object)$centerProcessDate).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $encyptedCardNumber = this.getEncyptedCardNumber();
        result = result * 59 + ($encyptedCardNumber == null ? 43 : $encyptedCardNumber.hashCode());
        String $caseId = this.getCaseId();
        result = result * 59 + ($caseId == null ? 43 : $caseId.hashCode());
        return result;
    }

    public String toString() {
        return "MCAcquirerExceptionsEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", interfaceCode=" + this.getInterfaceCode() + ", jobNumber=" + this.getJobNumber() + ", orgSerNumber=" + this.getOrgSerNumber() + ", fileId=" + this.getFileId() + ", msgTypeId=" + this.getMsgTypeId() + ", cardNumber=" + this.getCardNumber() + ", processCode=" + this.getProcessCode() + ", txnAmount=" + this.getTxnAmount() + ", reconAmount=" + this.getReconAmount() + ", conversionRate=" + this.getConversionRate() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", posData=" + this.getPosData() + ", functionCode=" + this.getFunctionCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", meCategory=" + this.getMeCategory() + ", orgAmount=" + this.getOrgAmount() + ", acqRefNumber=" + this.getAcqRefNumber() + ", acqInstIdCode=" + this.getAcqInstIdCode() + ", fwdInstIdCode=" + this.getFwdInstIdCode() + ", apprCode=" + this.getApprCode() + ", serviceCode=" + this.getServiceCode() + ", terminalId=" + this.getTerminalId() + ", meCode=" + this.getMeCode() + ", cardAccetName=" + this.getCardAccetName() + ", cardAccetCity=" + this.getCardAccetCity() + ", cardAccetCountry=" + this.getCardAccetCountry() + ", txnCurrency=" + this.getTxnCurrency() + ", reconCurrency=" + this.getReconCurrency() + ", txnLifeCycleId=" + this.getTxnLifeCycleId() + ", msgNumber=" + this.getMsgNumber() + ", memberText=" + this.getMemberText() + ", txnDstInstIdCode=" + this.getTxnDstInstIdCode() + ", txnOrgInstIdCode=" + this.getTxnOrgInstIdCode() + ", rcvInstIdCode=" + this.getRcvInstIdCode() + ", issRefNumber=" + this.getIssRefNumber() + ", retRefNumber=" + this.getRetRefNumber() + ", revIndicator=" + this.getRevIndicator() + ", maxCommIndicator=" + this.getMaxCommIndicator() + ", currExponents=" + this.getCurrExponents() + ", orgCurrency=" + this.getOrgCurrency() + ", bussActivity=" + this.getBussActivity() + ", settlementIndicator=" + this.getSettlementIndicator() + ", docIndicator=" + this.getDocIndicator() + ", crossBorderIndicator=" + this.getCrossBorderIndicator() + ", msgRefNumber=" + this.getMsgRefNumber() + ", statusCode=" + this.getStatusCode() + ", actionCode=" + this.getActionCode() + ", nextActionOn=" + String.valueOf(this.getNextActionOn()) + ", priority=" + this.getPriority() + ", activeStatus=" + this.getActiveStatus() + ", remarks=" + this.getRemarks() + ", centerProcessDate=" + String.valueOf(this.getCenterProcessDate()) + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", genStatus=" + this.getGenStatus() + ", encyptedCardNumber=" + this.getEncyptedCardNumber() + ", caseId=" + this.getCaseId() + ")";
    }
}

