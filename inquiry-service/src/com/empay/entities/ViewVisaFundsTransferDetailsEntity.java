/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewVisaFundsTransferDetailsEntity
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
@Table(name="VW_VISA_FUNDS_TRANSFER_DETAILS")
public class ViewVisaFundsTransferDetailsEntity {
    @Id
    @Column(name="BUSINESS_DATE")
    private LocalDate businessDate;
    @Column(name="FUND_TRANSFER_CREDIT")
    private Double visaFundsTransferCredit;
    @Column(name="TXN_COUNT")
    private Long visaTxnCount;
    @Column(name="TXN_CREDIT")
    private Double visaTxnCredit;
    @Column(name="TXN_DEBIT")
    private Double visaTxnDebit;
    @Column(name="TOTAL_TXN")
    private Double visaTxnTotal;
    @Column(name="REIMBURSEMNET_CREDIT")
    private Double visaReimbursementCredit;
    @Column(name="REIMBURSEMNET_DEBIT")
    private Double visaReimbursementDebit;
    @Column(name="REIMBURSEMNET_TOTAL")
    private Double visaReimbursementTotal;

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Double getVisaFundsTransferCredit() {
        return this.visaFundsTransferCredit;
    }

    public Long getVisaTxnCount() {
        return this.visaTxnCount;
    }

    public Double getVisaTxnCredit() {
        return this.visaTxnCredit;
    }

    public Double getVisaTxnDebit() {
        return this.visaTxnDebit;
    }

    public Double getVisaTxnTotal() {
        return this.visaTxnTotal;
    }

    public Double getVisaReimbursementCredit() {
        return this.visaReimbursementCredit;
    }

    public Double getVisaReimbursementDebit() {
        return this.visaReimbursementDebit;
    }

    public Double getVisaReimbursementTotal() {
        return this.visaReimbursementTotal;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setVisaFundsTransferCredit(Double visaFundsTransferCredit) {
        this.visaFundsTransferCredit = visaFundsTransferCredit;
    }

    public void setVisaTxnCount(Long visaTxnCount) {
        this.visaTxnCount = visaTxnCount;
    }

    public void setVisaTxnCredit(Double visaTxnCredit) {
        this.visaTxnCredit = visaTxnCredit;
    }

    public void setVisaTxnDebit(Double visaTxnDebit) {
        this.visaTxnDebit = visaTxnDebit;
    }

    public void setVisaTxnTotal(Double visaTxnTotal) {
        this.visaTxnTotal = visaTxnTotal;
    }

    public void setVisaReimbursementCredit(Double visaReimbursementCredit) {
        this.visaReimbursementCredit = visaReimbursementCredit;
    }

    public void setVisaReimbursementDebit(Double visaReimbursementDebit) {
        this.visaReimbursementDebit = visaReimbursementDebit;
    }

    public void setVisaReimbursementTotal(Double visaReimbursementTotal) {
        this.visaReimbursementTotal = visaReimbursementTotal;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewVisaFundsTransferDetailsEntity)) {
            return false;
        }
        ViewVisaFundsTransferDetailsEntity other = (ViewVisaFundsTransferDetailsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Double this$visaFundsTransferCredit = this.getVisaFundsTransferCredit();
        Double other$visaFundsTransferCredit = other.getVisaFundsTransferCredit();
        if (this$visaFundsTransferCredit == null ? other$visaFundsTransferCredit != null : !((Object)this$visaFundsTransferCredit).equals(other$visaFundsTransferCredit)) {
            return false;
        }
        Long this$visaTxnCount = this.getVisaTxnCount();
        Long other$visaTxnCount = other.getVisaTxnCount();
        if (this$visaTxnCount == null ? other$visaTxnCount != null : !((Object)this$visaTxnCount).equals(other$visaTxnCount)) {
            return false;
        }
        Double this$visaTxnCredit = this.getVisaTxnCredit();
        Double other$visaTxnCredit = other.getVisaTxnCredit();
        if (this$visaTxnCredit == null ? other$visaTxnCredit != null : !((Object)this$visaTxnCredit).equals(other$visaTxnCredit)) {
            return false;
        }
        Double this$visaTxnDebit = this.getVisaTxnDebit();
        Double other$visaTxnDebit = other.getVisaTxnDebit();
        if (this$visaTxnDebit == null ? other$visaTxnDebit != null : !((Object)this$visaTxnDebit).equals(other$visaTxnDebit)) {
            return false;
        }
        Double this$visaTxnTotal = this.getVisaTxnTotal();
        Double other$visaTxnTotal = other.getVisaTxnTotal();
        if (this$visaTxnTotal == null ? other$visaTxnTotal != null : !((Object)this$visaTxnTotal).equals(other$visaTxnTotal)) {
            return false;
        }
        Double this$visaReimbursementCredit = this.getVisaReimbursementCredit();
        Double other$visaReimbursementCredit = other.getVisaReimbursementCredit();
        if (this$visaReimbursementCredit == null ? other$visaReimbursementCredit != null : !((Object)this$visaReimbursementCredit).equals(other$visaReimbursementCredit)) {
            return false;
        }
        Double this$visaReimbursementDebit = this.getVisaReimbursementDebit();
        Double other$visaReimbursementDebit = other.getVisaReimbursementDebit();
        if (this$visaReimbursementDebit == null ? other$visaReimbursementDebit != null : !((Object)this$visaReimbursementDebit).equals(other$visaReimbursementDebit)) {
            return false;
        }
        Double this$visaReimbursementTotal = this.getVisaReimbursementTotal();
        Double other$visaReimbursementTotal = other.getVisaReimbursementTotal();
        if (this$visaReimbursementTotal == null ? other$visaReimbursementTotal != null : !((Object)this$visaReimbursementTotal).equals(other$visaReimbursementTotal)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        return !(this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewVisaFundsTransferDetailsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Double $visaFundsTransferCredit = this.getVisaFundsTransferCredit();
        result = result * 59 + ($visaFundsTransferCredit == null ? 43 : ((Object)$visaFundsTransferCredit).hashCode());
        Long $visaTxnCount = this.getVisaTxnCount();
        result = result * 59 + ($visaTxnCount == null ? 43 : ((Object)$visaTxnCount).hashCode());
        Double $visaTxnCredit = this.getVisaTxnCredit();
        result = result * 59 + ($visaTxnCredit == null ? 43 : ((Object)$visaTxnCredit).hashCode());
        Double $visaTxnDebit = this.getVisaTxnDebit();
        result = result * 59 + ($visaTxnDebit == null ? 43 : ((Object)$visaTxnDebit).hashCode());
        Double $visaTxnTotal = this.getVisaTxnTotal();
        result = result * 59 + ($visaTxnTotal == null ? 43 : ((Object)$visaTxnTotal).hashCode());
        Double $visaReimbursementCredit = this.getVisaReimbursementCredit();
        result = result * 59 + ($visaReimbursementCredit == null ? 43 : ((Object)$visaReimbursementCredit).hashCode());
        Double $visaReimbursementDebit = this.getVisaReimbursementDebit();
        result = result * 59 + ($visaReimbursementDebit == null ? 43 : ((Object)$visaReimbursementDebit).hashCode());
        Double $visaReimbursementTotal = this.getVisaReimbursementTotal();
        result = result * 59 + ($visaReimbursementTotal == null ? 43 : ((Object)$visaReimbursementTotal).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        return result;
    }

    public String toString() {
        return "ViewVisaFundsTransferDetailsEntity(businessDate=" + String.valueOf(this.getBusinessDate()) + ", visaFundsTransferCredit=" + this.getVisaFundsTransferCredit() + ", visaTxnCount=" + this.getVisaTxnCount() + ", visaTxnCredit=" + this.getVisaTxnCredit() + ", visaTxnDebit=" + this.getVisaTxnDebit() + ", visaTxnTotal=" + this.getVisaTxnTotal() + ", visaReimbursementCredit=" + this.getVisaReimbursementCredit() + ", visaReimbursementDebit=" + this.getVisaReimbursementDebit() + ", visaReimbursementTotal=" + this.getVisaReimbursementTotal() + ")";
    }

    public ViewVisaFundsTransferDetailsEntity() {
    }

    public ViewVisaFundsTransferDetailsEntity(LocalDate businessDate, Double visaFundsTransferCredit, Long visaTxnCount, Double visaTxnCredit, Double visaTxnDebit, Double visaTxnTotal, Double visaReimbursementCredit, Double visaReimbursementDebit, Double visaReimbursementTotal) {
        this.businessDate = businessDate;
        this.visaFundsTransferCredit = visaFundsTransferCredit;
        this.visaTxnCount = visaTxnCount;
        this.visaTxnCredit = visaTxnCredit;
        this.visaTxnDebit = visaTxnDebit;
        this.visaTxnTotal = visaTxnTotal;
        this.visaReimbursementCredit = visaReimbursementCredit;
        this.visaReimbursementDebit = visaReimbursementDebit;
        this.visaReimbursementTotal = visaReimbursementTotal;
    }
}

