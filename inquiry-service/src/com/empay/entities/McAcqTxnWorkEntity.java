/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.McAcqTxnWorkEntity
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
import java.time.LocalDateTime;

@Entity
@Table(name="MC_ACQ_TXN_WORK")
public class McAcqTxnWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MCT_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MCT_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MCT_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MCT_INS_CODE")
    private Integer insCode;
    @Column(name="MCT_GEN_STATUS")
    private Integer genStatus;
    @Column(name="MCT_INT_CODE")
    private Integer intCode;
    @Column(name="MCT_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="MCT_TXN_REF_NUMBER")
    private Integer refNumber;
    @Column(name="MCT_TXN_TYPE")
    private String txnType;
    @Column(name="MCT_MSG_TYPE_ID")
    private String msgTypeId;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInsCode() {
        return this.insCode;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public Integer getIntCode() {
        return this.intCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public Integer getRefNumber() {
        return this.refNumber;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getMsgTypeId() {
        return this.msgTypeId;
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

    public void setInsCode(Integer insCode) {
        this.insCode = insCode;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setIntCode(Integer intCode) {
        this.intCode = intCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setRefNumber(Integer refNumber) {
        this.refNumber = refNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McAcqTxnWorkEntity)) {
            return false;
        }
        McAcqTxnWorkEntity other = (McAcqTxnWorkEntity)o;
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
        Integer this$insCode = this.getInsCode();
        Integer other$insCode = other.getInsCode();
        if (this$insCode == null ? other$insCode != null : !((Object)this$insCode).equals(other$insCode)) {
            return false;
        }
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        Integer this$intCode = this.getIntCode();
        Integer other$intCode = other.getIntCode();
        if (this$intCode == null ? other$intCode != null : !((Object)this$intCode).equals(other$intCode)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$refNumber = this.getRefNumber();
        Integer other$refNumber = other.getRefNumber();
        if (this$refNumber == null ? other$refNumber != null : !((Object)this$refNumber).equals(other$refNumber)) {
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
        String this$msgTypeId = this.getMsgTypeId();
        String other$msgTypeId = other.getMsgTypeId();
        return !(this$msgTypeId == null ? other$msgTypeId != null : !this$msgTypeId.equals(other$msgTypeId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof McAcqTxnWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $insCode = this.getInsCode();
        result = result * 59 + ($insCode == null ? 43 : ((Object)$insCode).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $refNumber = this.getRefNumber();
        result = result * 59 + ($refNumber == null ? 43 : ((Object)$refNumber).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $msgTypeId = this.getMsgTypeId();
        result = result * 59 + ($msgTypeId == null ? 43 : $msgTypeId.hashCode());
        return result;
    }

    public String toString() {
        return "McAcqTxnWorkEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", genStatus=" + this.getGenStatus() + ", intCode=" + this.getIntCode() + ", jobNumber=" + this.getJobNumber() + ", refNumber=" + this.getRefNumber() + ", txnType=" + this.getTxnType() + ", msgTypeId=" + this.getMsgTypeId() + ")";
    }
}

