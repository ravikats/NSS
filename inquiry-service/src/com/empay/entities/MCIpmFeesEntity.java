/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MCIpmFeesEntity
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
@Table(name="MC_IPM_FEES")
public class MCIpmFeesEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MCF_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MCF_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MCF_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MCF_INS_CODE")
    private Integer institutionCode;
    @Column(name="MCF_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="MCF_ICA_NUMBER")
    private String icaNumber;
    @Column(name="MCF_FILE_ID")
    private String fileId;
    @Column(name="MCF_SETL_DATE")
    private LocalDate setlDate;
    @Column(name="MCF_SETL_CYCLE")
    private Integer setlCycle;
    @Column(name="MCF_TXN_DATE")
    private LocalDate txnDate;
    @Column(name="MCF_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MCF_PROC_CODE")
    private String procCode;
    @Column(name="MCF_FUNC_CODE")
    private String funcCode;
    @Column(name="MCF_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name="MCF_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name="MCF_TXN_ORG_INST_ID")
    private String txnOrgInstId;
    @Column(name="MCF_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MCF_TXN_CURR_CODE")
    private Integer txnCurrCode;
    @Column(name="MCF_RECON_AMOUNT")
    private Double reconAmount;
    @Column(name="MCF_RECON_CURR_CODE")
    private Integer reconCurrCode;
    @Column(name="MCF_MEMBER_TEXT")
    private String memberText;
    @Column(name="MCF_REV_INDICATOR")
    private String revIndicator;
    @Column(name="MCF_CENTRE_PROC_DATE")
    private LocalDate centerProcDate;
    @Column(name="MCF_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="MCF_GEN_STATUS")
    private String genStatus;
    @Column(name="MCF_ENC_CARD_NUMBER")
    private String encyptedCardNumber;

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

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public String getIcaNumber() {
        return this.icaNumber;
    }

    public String getFileId() {
        return this.fileId;
    }

    public LocalDate getSetlDate() {
        return this.setlDate;
    }

    public Integer getSetlCycle() {
        return this.setlCycle;
    }

    public LocalDate getTxnDate() {
        return this.txnDate;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public String getFuncCode() {
        return this.funcCode;
    }

    public String getMsgReasonCode() {
        return this.msgReasonCode;
    }

    public String getAcqRefData() {
        return this.acqRefData;
    }

    public String getTxnOrgInstId() {
        return this.txnOrgInstId;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Integer getTxnCurrCode() {
        return this.txnCurrCode;
    }

    public Double getReconAmount() {
        return this.reconAmount;
    }

    public Integer getReconCurrCode() {
        return this.reconCurrCode;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public String getRevIndicator() {
        return this.revIndicator;
    }

    public LocalDate getCenterProcDate() {
        return this.centerProcDate;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public String getGenStatus() {
        return this.genStatus;
    }

    public String getEncyptedCardNumber() {
        return this.encyptedCardNumber;
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

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setIcaNumber(String icaNumber) {
        this.icaNumber = icaNumber;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setSetlDate(LocalDate setlDate) {
        this.setlDate = setlDate;
    }

    public void setSetlCycle(Integer setlCycle) {
        this.setlCycle = setlCycle;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public void setMsgReasonCode(String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }

    public void setAcqRefData(String acqRefData) {
        this.acqRefData = acqRefData;
    }

    public void setTxnOrgInstId(String txnOrgInstId) {
        this.txnOrgInstId = txnOrgInstId;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setTxnCurrCode(Integer txnCurrCode) {
        this.txnCurrCode = txnCurrCode;
    }

    public void setReconAmount(Double reconAmount) {
        this.reconAmount = reconAmount;
    }

    public void setReconCurrCode(Integer reconCurrCode) {
        this.reconCurrCode = reconCurrCode;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setRevIndicator(String revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setCenterProcDate(LocalDate centerProcDate) {
        this.centerProcDate = centerProcDate;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setGenStatus(String genStatus) {
        this.genStatus = genStatus;
    }

    public void setEncyptedCardNumber(String encyptedCardNumber) {
        this.encyptedCardNumber = encyptedCardNumber;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MCIpmFeesEntity)) {
            return false;
        }
        MCIpmFeesEntity other = (MCIpmFeesEntity)o;
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
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$setlCycle = this.getSetlCycle();
        Integer other$setlCycle = other.getSetlCycle();
        if (this$setlCycle == null ? other$setlCycle != null : !((Object)this$setlCycle).equals(other$setlCycle)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Integer this$txnCurrCode = this.getTxnCurrCode();
        Integer other$txnCurrCode = other.getTxnCurrCode();
        if (this$txnCurrCode == null ? other$txnCurrCode != null : !((Object)this$txnCurrCode).equals(other$txnCurrCode)) {
            return false;
        }
        Double this$reconAmount = this.getReconAmount();
        Double other$reconAmount = other.getReconAmount();
        if (this$reconAmount == null ? other$reconAmount != null : !((Object)this$reconAmount).equals(other$reconAmount)) {
            return false;
        }
        Integer this$reconCurrCode = this.getReconCurrCode();
        Integer other$reconCurrCode = other.getReconCurrCode();
        if (this$reconCurrCode == null ? other$reconCurrCode != null : !((Object)this$reconCurrCode).equals(other$reconCurrCode)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$icaNumber = this.getIcaNumber();
        String other$icaNumber = other.getIcaNumber();
        if (this$icaNumber == null ? other$icaNumber != null : !this$icaNumber.equals(other$icaNumber)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        LocalDate this$setlDate = this.getSetlDate();
        LocalDate other$setlDate = other.getSetlDate();
        if (this$setlDate == null ? other$setlDate != null : !((Object)this$setlDate).equals(other$setlDate)) {
            return false;
        }
        LocalDate this$txnDate = this.getTxnDate();
        LocalDate other$txnDate = other.getTxnDate();
        if (this$txnDate == null ? other$txnDate != null : !((Object)this$txnDate).equals(other$txnDate)) {
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
        String this$funcCode = this.getFuncCode();
        String other$funcCode = other.getFuncCode();
        if (this$funcCode == null ? other$funcCode != null : !this$funcCode.equals(other$funcCode)) {
            return false;
        }
        String this$msgReasonCode = this.getMsgReasonCode();
        String other$msgReasonCode = other.getMsgReasonCode();
        if (this$msgReasonCode == null ? other$msgReasonCode != null : !this$msgReasonCode.equals(other$msgReasonCode)) {
            return false;
        }
        String this$acqRefData = this.getAcqRefData();
        String other$acqRefData = other.getAcqRefData();
        if (this$acqRefData == null ? other$acqRefData != null : !this$acqRefData.equals(other$acqRefData)) {
            return false;
        }
        String this$txnOrgInstId = this.getTxnOrgInstId();
        String other$txnOrgInstId = other.getTxnOrgInstId();
        if (this$txnOrgInstId == null ? other$txnOrgInstId != null : !this$txnOrgInstId.equals(other$txnOrgInstId)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
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
        String this$genStatus = this.getGenStatus();
        String other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !this$genStatus.equals(other$genStatus)) {
            return false;
        }
        String this$encyptedCardNumber = this.getEncyptedCardNumber();
        String other$encyptedCardNumber = other.getEncyptedCardNumber();
        return !(this$encyptedCardNumber == null ? other$encyptedCardNumber != null : !this$encyptedCardNumber.equals(other$encyptedCardNumber));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MCIpmFeesEntity;
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
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $setlCycle = this.getSetlCycle();
        result = result * 59 + ($setlCycle == null ? 43 : ((Object)$setlCycle).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Integer $txnCurrCode = this.getTxnCurrCode();
        result = result * 59 + ($txnCurrCode == null ? 43 : ((Object)$txnCurrCode).hashCode());
        Double $reconAmount = this.getReconAmount();
        result = result * 59 + ($reconAmount == null ? 43 : ((Object)$reconAmount).hashCode());
        Integer $reconCurrCode = this.getReconCurrCode();
        result = result * 59 + ($reconCurrCode == null ? 43 : ((Object)$reconCurrCode).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $icaNumber = this.getIcaNumber();
        result = result * 59 + ($icaNumber == null ? 43 : $icaNumber.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        LocalDate $setlDate = this.getSetlDate();
        result = result * 59 + ($setlDate == null ? 43 : ((Object)$setlDate).hashCode());
        LocalDate $txnDate = this.getTxnDate();
        result = result * 59 + ($txnDate == null ? 43 : ((Object)$txnDate).hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $funcCode = this.getFuncCode();
        result = result * 59 + ($funcCode == null ? 43 : $funcCode.hashCode());
        String $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + ($msgReasonCode == null ? 43 : $msgReasonCode.hashCode());
        String $acqRefData = this.getAcqRefData();
        result = result * 59 + ($acqRefData == null ? 43 : $acqRefData.hashCode());
        String $txnOrgInstId = this.getTxnOrgInstId();
        result = result * 59 + ($txnOrgInstId == null ? 43 : $txnOrgInstId.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : $revIndicator.hashCode());
        LocalDate $centerProcDate = this.getCenterProcDate();
        result = result * 59 + ($centerProcDate == null ? 43 : ((Object)$centerProcDate).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : $genStatus.hashCode());
        String $encyptedCardNumber = this.getEncyptedCardNumber();
        result = result * 59 + ($encyptedCardNumber == null ? 43 : $encyptedCardNumber.hashCode());
        return result;
    }

    public String toString() {
        return "MCIpmFeesEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", jobNumber=" + this.getJobNumber() + ", icaNumber=" + this.getIcaNumber() + ", fileId=" + this.getFileId() + ", setlDate=" + String.valueOf(this.getSetlDate()) + ", setlCycle=" + this.getSetlCycle() + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", cardNumber=" + this.getCardNumber() + ", procCode=" + this.getProcCode() + ", funcCode=" + this.getFuncCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", acqRefData=" + this.getAcqRefData() + ", txnOrgInstId=" + this.getTxnOrgInstId() + ", txnAmount=" + this.getTxnAmount() + ", txnCurrCode=" + this.getTxnCurrCode() + ", reconAmount=" + this.getReconAmount() + ", reconCurrCode=" + this.getReconCurrCode() + ", memberText=" + this.getMemberText() + ", revIndicator=" + this.getRevIndicator() + ", centerProcDate=" + String.valueOf(this.getCenterProcDate()) + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", genStatus=" + this.getGenStatus() + ", encyptedCardNumber=" + this.getEncyptedCardNumber() + ")";
    }
}

