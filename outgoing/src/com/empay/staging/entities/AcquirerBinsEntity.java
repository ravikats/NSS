/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="ACQUIRER_BINS")
public class AcquirerBinsEntity {
    @Id
    @Column(name="ACQ_BIN")
    private String acquirerBins;
    @Column(name="ACQ_LAST_UPDATED")
    private Date lastUpdatedDate;
    @Column(name="ACQ_UPDATED_USER")
    private Integer users;
    @Column(name="ACQ_INS_CODE")
    private int institutionCode;
    @Column(name="ACQ_BIN_TYPE")
    private String binType;
    @Column(name="ACQ_ARN_SEQ_NO")
    private Integer arnSeqNo;
    @Column(name="ACQ_OUT_FILE_DATE")
    private LocalDateTime outfileDate;
    @Column(name="ACQ_MC_ICA_NO")
    private String mcIcaNo;
    @Column(name="ACQ_OUT_BATCH_NO")
    private Integer outBatchNo;
    @Column(name="ACQ_OUT_FILE_ID")
    private String outFileId;
    @Column(name="ACQ_OUT_FILE_SEQ")
    private Integer outFileSeq;
    @Column(name="ACQ_PARTICIPANT_ID")
    private String participantID;
    @Column(name="ACQ_TRANSACTION_TYPE")
    private String txnType;

    public String getAcquirerBins() {
        return this.acquirerBins;
    }

    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public Integer getUsers() {
        return this.users;
    }

    public int getInstitutionCode() {
        return this.institutionCode;
    }

    public String getBinType() {
        return this.binType;
    }

    public Integer getArnSeqNo() {
        return this.arnSeqNo;
    }

    public LocalDateTime getOutfileDate() {
        return this.outfileDate;
    }

    public String getMcIcaNo() {
        return this.mcIcaNo;
    }

    public Integer getOutBatchNo() {
        return this.outBatchNo;
    }

    public String getOutFileId() {
        return this.outFileId;
    }

    public Integer getOutFileSeq() {
        return this.outFileSeq;
    }

    public String getParticipantID() {
        return this.participantID;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public void setAcquirerBins(String acquirerBins) {
        this.acquirerBins = acquirerBins;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }

    public void setInstitutionCode(int institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setBinType(String binType) {
        this.binType = binType;
    }

    public void setArnSeqNo(Integer arnSeqNo) {
        this.arnSeqNo = arnSeqNo;
    }

    public void setOutfileDate(LocalDateTime outfileDate) {
        this.outfileDate = outfileDate;
    }

    public void setMcIcaNo(String mcIcaNo) {
        this.mcIcaNo = mcIcaNo;
    }

    public void setOutBatchNo(Integer outBatchNo) {
        this.outBatchNo = outBatchNo;
    }

    public void setOutFileId(String outFileId) {
        this.outFileId = outFileId;
    }

    public void setOutFileSeq(Integer outFileSeq) {
        this.outFileSeq = outFileSeq;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AcquirerBinsEntity)) {
            return false;
        }
        AcquirerBinsEntity other = (AcquirerBinsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        Integer this$users = this.getUsers();
        Integer other$users = other.getUsers();
        if (this$users == null ? other$users != null : !((Object)this$users).equals(other$users)) {
            return false;
        }
        Integer this$arnSeqNo = this.getArnSeqNo();
        Integer other$arnSeqNo = other.getArnSeqNo();
        if (this$arnSeqNo == null ? other$arnSeqNo != null : !((Object)this$arnSeqNo).equals(other$arnSeqNo)) {
            return false;
        }
        Integer this$outBatchNo = this.getOutBatchNo();
        Integer other$outBatchNo = other.getOutBatchNo();
        if (this$outBatchNo == null ? other$outBatchNo != null : !((Object)this$outBatchNo).equals(other$outBatchNo)) {
            return false;
        }
        Integer this$outFileSeq = this.getOutFileSeq();
        Integer other$outFileSeq = other.getOutFileSeq();
        if (this$outFileSeq == null ? other$outFileSeq != null : !((Object)this$outFileSeq).equals(other$outFileSeq)) {
            return false;
        }
        String this$acquirerBins = this.getAcquirerBins();
        String other$acquirerBins = other.getAcquirerBins();
        if (this$acquirerBins == null ? other$acquirerBins != null : !this$acquirerBins.equals(other$acquirerBins)) {
            return false;
        }
        Date this$lastUpdatedDate = this.getLastUpdatedDate();
        Date other$lastUpdatedDate = other.getLastUpdatedDate();
        if (this$lastUpdatedDate == null ? other$lastUpdatedDate != null : !((Object)this$lastUpdatedDate).equals(other$lastUpdatedDate)) {
            return false;
        }
        String this$binType = this.getBinType();
        String other$binType = other.getBinType();
        if (this$binType == null ? other$binType != null : !this$binType.equals(other$binType)) {
            return false;
        }
        LocalDateTime this$outfileDate = this.getOutfileDate();
        LocalDateTime other$outfileDate = other.getOutfileDate();
        if (this$outfileDate == null ? other$outfileDate != null : !((Object)this$outfileDate).equals(other$outfileDate)) {
            return false;
        }
        String this$mcIcaNo = this.getMcIcaNo();
        String other$mcIcaNo = other.getMcIcaNo();
        if (this$mcIcaNo == null ? other$mcIcaNo != null : !this$mcIcaNo.equals(other$mcIcaNo)) {
            return false;
        }
        String this$outFileId = this.getOutFileId();
        String other$outFileId = other.getOutFileId();
        if (this$outFileId == null ? other$outFileId != null : !this$outFileId.equals(other$outFileId)) {
            return false;
        }
        String this$participantID = this.getParticipantID();
        String other$participantID = other.getParticipantID();
        if (this$participantID == null ? other$participantID != null : !this$participantID.equals(other$participantID)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        return !(this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AcquirerBinsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInstitutionCode();
        Integer $users = this.getUsers();
        result = result * 59 + ($users == null ? 43 : ((Object)$users).hashCode());
        Integer $arnSeqNo = this.getArnSeqNo();
        result = result * 59 + ($arnSeqNo == null ? 43 : ((Object)$arnSeqNo).hashCode());
        Integer $outBatchNo = this.getOutBatchNo();
        result = result * 59 + ($outBatchNo == null ? 43 : ((Object)$outBatchNo).hashCode());
        Integer $outFileSeq = this.getOutFileSeq();
        result = result * 59 + ($outFileSeq == null ? 43 : ((Object)$outFileSeq).hashCode());
        String $acquirerBins = this.getAcquirerBins();
        result = result * 59 + ($acquirerBins == null ? 43 : $acquirerBins.hashCode());
        Date $lastUpdatedDate = this.getLastUpdatedDate();
        result = result * 59 + ($lastUpdatedDate == null ? 43 : ((Object)$lastUpdatedDate).hashCode());
        String $binType = this.getBinType();
        result = result * 59 + ($binType == null ? 43 : $binType.hashCode());
        LocalDateTime $outfileDate = this.getOutfileDate();
        result = result * 59 + ($outfileDate == null ? 43 : ((Object)$outfileDate).hashCode());
        String $mcIcaNo = this.getMcIcaNo();
        result = result * 59 + ($mcIcaNo == null ? 43 : $mcIcaNo.hashCode());
        String $outFileId = this.getOutFileId();
        result = result * 59 + ($outFileId == null ? 43 : $outFileId.hashCode());
        String $participantID = this.getParticipantID();
        result = result * 59 + ($participantID == null ? 43 : $participantID.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        return result;
    }

    public String toString() {
        return "AcquirerBinsEntity(acquirerBins=" + this.getAcquirerBins() + ", lastUpdatedDate=" + String.valueOf(this.getLastUpdatedDate()) + ", users=" + this.getUsers() + ", institutionCode=" + this.getInstitutionCode() + ", binType=" + this.getBinType() + ", arnSeqNo=" + this.getArnSeqNo() + ", outfileDate=" + String.valueOf(this.getOutfileDate()) + ", mcIcaNo=" + this.getMcIcaNo() + ", outBatchNo=" + this.getOutBatchNo() + ", outFileId=" + this.getOutFileId() + ", outFileSeq=" + this.getOutFileSeq() + ", participantID=" + this.getParticipantID() + ", txnType=" + this.getTxnType() + ")";
    }
}

