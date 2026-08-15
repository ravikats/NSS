/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewMCNetReconDetailsEntity
 *  com.empay.entities.ViewMCNetReconId
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.IdClass
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.ViewMCNetReconId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@IdClass(value=ViewMCNetReconId.class)
@Table(name="VW_MC_NET_RECON_SUMMARY")
public class ViewMCNetReconDetailsEntity {
    @Id
    @Column(name="BUSINESS_DATE")
    private LocalDate businessDate;
    @Column(name="TXN_TYPE")
    private String txnType;
    @Column(name="RRN")
    private String rrn;
    @Column(name="TOTAL_TXN_COUNT")
    private Long totalTxnCount;
    @Column(name="TOTAL_CREDIT_AMOUNT")
    private Double totalCreditAmount;
    @Column(name="TOTAL_DEBIT_AMOUNT")
    private Double totalDebitAmount;
    @Column(name="TOTAL_NET_AMOUNT")
    private Double totalNetAmount;
    @Column(name="RECON_CREDIT_FEE_AMOUNT")
    private Double reconCreditFeeAmount;
    @Column(name="RECON_DEBIT_FEE_AMOUNT")
    private Double reconDebitFeeAmount;
    @Column(name="RECON_NET_FEE_AMOUNT")
    private Double reconNetFeeAmount;

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getRrn() {
        return this.rrn;
    }

    public Long getTotalTxnCount() {
        return this.totalTxnCount;
    }

    public Double getTotalCreditAmount() {
        return this.totalCreditAmount;
    }

    public Double getTotalDebitAmount() {
        return this.totalDebitAmount;
    }

    public Double getTotalNetAmount() {
        return this.totalNetAmount;
    }

    public Double getReconCreditFeeAmount() {
        return this.reconCreditFeeAmount;
    }

    public Double getReconDebitFeeAmount() {
        return this.reconDebitFeeAmount;
    }

    public Double getReconNetFeeAmount() {
        return this.reconNetFeeAmount;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setTotalTxnCount(Long totalTxnCount) {
        this.totalTxnCount = totalTxnCount;
    }

    public void setTotalCreditAmount(Double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    public void setTotalDebitAmount(Double totalDebitAmount) {
        this.totalDebitAmount = totalDebitAmount;
    }

    public void setTotalNetAmount(Double totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public void setReconCreditFeeAmount(Double reconCreditFeeAmount) {
        this.reconCreditFeeAmount = reconCreditFeeAmount;
    }

    public void setReconDebitFeeAmount(Double reconDebitFeeAmount) {
        this.reconDebitFeeAmount = reconDebitFeeAmount;
    }

    public void setReconNetFeeAmount(Double reconNetFeeAmount) {
        this.reconNetFeeAmount = reconNetFeeAmount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewMCNetReconDetailsEntity)) {
            return false;
        }
        ViewMCNetReconDetailsEntity other = (ViewMCNetReconDetailsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$totalTxnCount = this.getTotalTxnCount();
        Long other$totalTxnCount = other.getTotalTxnCount();
        if (this$totalTxnCount == null ? other$totalTxnCount != null : !((Object)this$totalTxnCount).equals(other$totalTxnCount)) {
            return false;
        }
        Double this$totalCreditAmount = this.getTotalCreditAmount();
        Double other$totalCreditAmount = other.getTotalCreditAmount();
        if (this$totalCreditAmount == null ? other$totalCreditAmount != null : !((Object)this$totalCreditAmount).equals(other$totalCreditAmount)) {
            return false;
        }
        Double this$totalDebitAmount = this.getTotalDebitAmount();
        Double other$totalDebitAmount = other.getTotalDebitAmount();
        if (this$totalDebitAmount == null ? other$totalDebitAmount != null : !((Object)this$totalDebitAmount).equals(other$totalDebitAmount)) {
            return false;
        }
        Double this$totalNetAmount = this.getTotalNetAmount();
        Double other$totalNetAmount = other.getTotalNetAmount();
        if (this$totalNetAmount == null ? other$totalNetAmount != null : !((Object)this$totalNetAmount).equals(other$totalNetAmount)) {
            return false;
        }
        Double this$reconCreditFeeAmount = this.getReconCreditFeeAmount();
        Double other$reconCreditFeeAmount = other.getReconCreditFeeAmount();
        if (this$reconCreditFeeAmount == null ? other$reconCreditFeeAmount != null : !((Object)this$reconCreditFeeAmount).equals(other$reconCreditFeeAmount)) {
            return false;
        }
        Double this$reconDebitFeeAmount = this.getReconDebitFeeAmount();
        Double other$reconDebitFeeAmount = other.getReconDebitFeeAmount();
        if (this$reconDebitFeeAmount == null ? other$reconDebitFeeAmount != null : !((Object)this$reconDebitFeeAmount).equals(other$reconDebitFeeAmount)) {
            return false;
        }
        Double this$reconNetFeeAmount = this.getReconNetFeeAmount();
        Double other$reconNetFeeAmount = other.getReconNetFeeAmount();
        if (this$reconNetFeeAmount == null ? other$reconNetFeeAmount != null : !((Object)this$reconNetFeeAmount).equals(other$reconNetFeeAmount)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        return !(this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewMCNetReconDetailsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $totalTxnCount = this.getTotalTxnCount();
        result = result * 59 + ($totalTxnCount == null ? 43 : ((Object)$totalTxnCount).hashCode());
        Double $totalCreditAmount = this.getTotalCreditAmount();
        result = result * 59 + ($totalCreditAmount == null ? 43 : ((Object)$totalCreditAmount).hashCode());
        Double $totalDebitAmount = this.getTotalDebitAmount();
        result = result * 59 + ($totalDebitAmount == null ? 43 : ((Object)$totalDebitAmount).hashCode());
        Double $totalNetAmount = this.getTotalNetAmount();
        result = result * 59 + ($totalNetAmount == null ? 43 : ((Object)$totalNetAmount).hashCode());
        Double $reconCreditFeeAmount = this.getReconCreditFeeAmount();
        result = result * 59 + ($reconCreditFeeAmount == null ? 43 : ((Object)$reconCreditFeeAmount).hashCode());
        Double $reconDebitFeeAmount = this.getReconDebitFeeAmount();
        result = result * 59 + ($reconDebitFeeAmount == null ? 43 : ((Object)$reconDebitFeeAmount).hashCode());
        Double $reconNetFeeAmount = this.getReconNetFeeAmount();
        result = result * 59 + ($reconNetFeeAmount == null ? 43 : ((Object)$reconNetFeeAmount).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        return result;
    }

    public String toString() {
        return "ViewMCNetReconDetailsEntity(businessDate=" + String.valueOf(this.getBusinessDate()) + ", txnType=" + this.getTxnType() + ", rrn=" + this.getRrn() + ", totalTxnCount=" + this.getTotalTxnCount() + ", totalCreditAmount=" + this.getTotalCreditAmount() + ", totalDebitAmount=" + this.getTotalDebitAmount() + ", totalNetAmount=" + this.getTotalNetAmount() + ", reconCreditFeeAmount=" + this.getReconCreditFeeAmount() + ", reconDebitFeeAmount=" + this.getReconDebitFeeAmount() + ", reconNetFeeAmount=" + this.getReconNetFeeAmount() + ")";
    }

    public ViewMCNetReconDetailsEntity() {
    }

    public ViewMCNetReconDetailsEntity(LocalDate businessDate, String txnType, String rrn, Long totalTxnCount, Double totalCreditAmount, Double totalDebitAmount, Double totalNetAmount, Double reconCreditFeeAmount, Double reconDebitFeeAmount, Double reconNetFeeAmount) {
        this.businessDate = businessDate;
        this.txnType = txnType;
        this.rrn = rrn;
        this.totalTxnCount = totalTxnCount;
        this.totalCreditAmount = totalCreditAmount;
        this.totalDebitAmount = totalDebitAmount;
        this.totalNetAmount = totalNetAmount;
        this.reconCreditFeeAmount = reconCreditFeeAmount;
        this.reconDebitFeeAmount = reconDebitFeeAmount;
        this.reconNetFeeAmount = reconNetFeeAmount;
    }
}

