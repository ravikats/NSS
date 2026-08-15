/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.OutgoingReportDataWorkEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="OUTGOING_REPORT_DATA_WORK")
public class OutgoingReportDataWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ORD_SER_NUMBER")
    private Integer serNumber;
    @Column(name="ORD_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="ORD_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="ORD_INS_CODE")
    private int institutionCode;
    @Column(name="ORD_OUT_FILE_ID")
    private String outFileId;
    @Column(name="ORD_OUTGOING_DATE")
    private LocalDateTime outgoingDate;
    @Column(name="ORD_TXN_DATE")
    private LocalDate txnDate;
    @Column(name="ORD_NETWORK")
    private String network;
    @Column(name="ORD_POS_OR_PG")
    private String posOrgPg;
    @Column(name="ORD_TXN_TYPE")
    private String txnType;
    @Column(name="ORD_COUNT")
    private Long count;
    @Column(name="ORD_AMOUNT")
    private Double amount;

    public Integer getSerNumber() {
        return this.serNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public int getInstitutionCode() {
        return this.institutionCode;
    }

    public String getOutFileId() {
        return this.outFileId;
    }

    public LocalDateTime getOutgoingDate() {
        return this.outgoingDate;
    }

    public LocalDate getTxnDate() {
        return this.txnDate;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getPosOrgPg() {
        return this.posOrgPg;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public Long getCount() {
        return this.count;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setSerNumber(Integer serNumber) {
        this.serNumber = serNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(int institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setOutFileId(String outFileId) {
        this.outFileId = outFileId;
    }

    public void setOutgoingDate(LocalDateTime outgoingDate) {
        this.outgoingDate = outgoingDate;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setPosOrgPg(String posOrgPg) {
        this.posOrgPg = posOrgPg;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutgoingReportDataWorkEntity)) {
            return false;
        }
        OutgoingReportDataWorkEntity other = (OutgoingReportDataWorkEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        Integer this$serNumber = this.getSerNumber();
        Integer other$serNumber = other.getSerNumber();
        if (this$serNumber == null ? other$serNumber != null : !((Object)this$serNumber).equals(other$serNumber)) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Long this$count = this.getCount();
        Long other$count = other.getCount();
        if (this$count == null ? other$count != null : !((Object)this$count).equals(other$count)) {
            return false;
        }
        Double this$amount = this.getAmount();
        Double other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !((Object)this$amount).equals(other$amount)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$outFileId = this.getOutFileId();
        String other$outFileId = other.getOutFileId();
        if (this$outFileId == null ? other$outFileId != null : !this$outFileId.equals(other$outFileId)) {
            return false;
        }
        LocalDateTime this$outgoingDate = this.getOutgoingDate();
        LocalDateTime other$outgoingDate = other.getOutgoingDate();
        if (this$outgoingDate == null ? other$outgoingDate != null : !((Object)this$outgoingDate).equals(other$outgoingDate)) {
            return false;
        }
        LocalDate this$txnDate = this.getTxnDate();
        LocalDate other$txnDate = other.getTxnDate();
        if (this$txnDate == null ? other$txnDate != null : !((Object)this$txnDate).equals(other$txnDate)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$posOrgPg = this.getPosOrgPg();
        String other$posOrgPg = other.getPosOrgPg();
        if (this$posOrgPg == null ? other$posOrgPg != null : !this$posOrgPg.equals(other$posOrgPg)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        return !(this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OutgoingReportDataWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInstitutionCode();
        Integer $serNumber = this.getSerNumber();
        result = result * 59 + ($serNumber == null ? 43 : ((Object)$serNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Long $count = this.getCount();
        result = result * 59 + ($count == null ? 43 : ((Object)$count).hashCode());
        Double $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : ((Object)$amount).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $outFileId = this.getOutFileId();
        result = result * 59 + ($outFileId == null ? 43 : $outFileId.hashCode());
        LocalDateTime $outgoingDate = this.getOutgoingDate();
        result = result * 59 + ($outgoingDate == null ? 43 : ((Object)$outgoingDate).hashCode());
        LocalDate $txnDate = this.getTxnDate();
        result = result * 59 + ($txnDate == null ? 43 : ((Object)$txnDate).hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $posOrgPg = this.getPosOrgPg();
        result = result * 59 + ($posOrgPg == null ? 43 : $posOrgPg.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        return result;
    }

    public String toString() {
        return "OutgoingReportDataWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", outFileId=" + this.getOutFileId() + ", outgoingDate=" + String.valueOf(this.getOutgoingDate()) + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", network=" + this.getNetwork() + ", posOrgPg=" + this.getPosOrgPg() + ", txnType=" + this.getTxnType() + ", count=" + this.getCount() + ", amount=" + this.getAmount() + ")";
    }
}

