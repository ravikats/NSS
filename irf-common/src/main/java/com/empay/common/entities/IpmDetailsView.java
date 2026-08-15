// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "VW_IPM_DETAILS")
public class IpmDetailsView
{
    @Id
    @Column(name = "MIP_SER_NUMBER")
    private Integer txnSerNumber;
    @Column(name = "IIC_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "IIC_IRD")
    private String ird;
    @Column(name = "IIC_RATE_PERCENT")
    private Double ratePercent;
    @Column(name = "IIC_RATE_FIXED")
    private Double rateFixed;
    @Column(name = "IIC_PRIORITY")
    private Integer priority;
    @Column(name = "IIC_MIN_AMOUNT")
    private Double irfMinAmount;
    @Column(name = "IIC_MAX_AMOUNT")
    private Double irfMaxAmount;
    
    public Integer getTxnSerNumber() {
        return this.txnSerNumber;
    }
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public String getIrd() {
        return this.ird;
    }
    
    public Double getRatePercent() {
        return this.ratePercent;
    }
    
    public Double getRateFixed() {
        return this.rateFixed;
    }
    
    public Integer getPriority() {
        return this.priority;
    }
    
    public Double getIrfMinAmount() {
        return this.irfMinAmount;
    }
    
    public Double getIrfMaxAmount() {
        return this.irfMaxAmount;
    }
    
    public void setTxnSerNumber(final Integer txnSerNumber) {
        this.txnSerNumber = txnSerNumber;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setRatePercent(final Double ratePercent) {
        this.ratePercent = ratePercent;
    }
    
    public void setRateFixed(final Double rateFixed) {
        this.rateFixed = rateFixed;
    }
    
    public void setPriority(final Integer priority) {
        this.priority = priority;
    }
    
    public void setIrfMinAmount(final Double irfMinAmount) {
        this.irfMinAmount = irfMinAmount;
    }
    
    public void setIrfMaxAmount(final Double irfMaxAmount) {
        this.irfMaxAmount = irfMaxAmount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmDetailsView)) {
            return false;
        }
        final IpmDetailsView other = (IpmDetailsView)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$txnSerNumber = this.getTxnSerNumber();
        final Object other$txnSerNumber = other.getTxnSerNumber();
        Label_0065: {
            if (this$txnSerNumber == null) {
                if (other$txnSerNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$txnSerNumber.equals(other$txnSerNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0102: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0102;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ratePercent = this.getRatePercent();
        final Object other$ratePercent = other.getRatePercent();
        Label_0139: {
            if (this$ratePercent == null) {
                if (other$ratePercent == null) {
                    break Label_0139;
                }
            }
            else if (this$ratePercent.equals(other$ratePercent)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$rateFixed = this.getRateFixed();
        final Object other$rateFixed = other.getRateFixed();
        Label_0176: {
            if (this$rateFixed == null) {
                if (other$rateFixed == null) {
                    break Label_0176;
                }
            }
            else if (this$rateFixed.equals(other$rateFixed)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$priority = this.getPriority();
        final Object other$priority = other.getPriority();
        Label_0213: {
            if (this$priority == null) {
                if (other$priority == null) {
                    break Label_0213;
                }
            }
            else if (this$priority.equals(other$priority)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$irfMinAmount = this.getIrfMinAmount();
        final Object other$irfMinAmount = other.getIrfMinAmount();
        Label_0250: {
            if (this$irfMinAmount == null) {
                if (other$irfMinAmount == null) {
                    break Label_0250;
                }
            }
            else if (this$irfMinAmount.equals(other$irfMinAmount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$irfMaxAmount = this.getIrfMaxAmount();
        final Object other$irfMaxAmount = other.getIrfMaxAmount();
        Label_0287: {
            if (this$irfMaxAmount == null) {
                if (other$irfMaxAmount == null) {
                    break Label_0287;
                }
            }
            else if (this$irfMaxAmount.equals(other$irfMaxAmount)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$ird = this.getIrd();
        final Object other$ird = other.getIrd();
        if (this$ird == null) {
            if (other$ird == null) {
                return true;
            }
        }
        else if (this$ird.equals(other$ird)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmDetailsView;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $txnSerNumber = this.getTxnSerNumber();
        result = result * 59 + (($txnSerNumber == null) ? 43 : $txnSerNumber.hashCode());
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $ratePercent = this.getRatePercent();
        result = result * 59 + (($ratePercent == null) ? 43 : $ratePercent.hashCode());
        final Object $rateFixed = this.getRateFixed();
        result = result * 59 + (($rateFixed == null) ? 43 : $rateFixed.hashCode());
        final Object $priority = this.getPriority();
        result = result * 59 + (($priority == null) ? 43 : $priority.hashCode());
        final Object $irfMinAmount = this.getIrfMinAmount();
        result = result * 59 + (($irfMinAmount == null) ? 43 : $irfMinAmount.hashCode());
        final Object $irfMaxAmount = this.getIrfMaxAmount();
        result = result * 59 + (($irfMaxAmount == null) ? 43 : $irfMaxAmount.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmDetailsView(txnSerNumber=" + this.getTxnSerNumber() + ", serialNumber=" + this.getSerialNumber() + ", ird=" + this.getIrd() + ", ratePercent=" + this.getRatePercent() + ", rateFixed=" + this.getRateFixed() + ", priority=" + this.getPriority() + ", irfMinAmount=" + this.getIrfMinAmount() + ", irfMaxAmount=" + this.getIrfMaxAmount();
    }
}

