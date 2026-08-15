// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MERCURY_IRF_RATES")
public class MercuryIRFRateEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MIR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "MIR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "MIR_MCC")
    private String mcc;
    @Column(name = "MIR_MCC_DESC")
    private String mccDesc;
    @Column(name = "MIR_IRF_PERCENT")
    private Double irfPercent;
    @Column(name = "MIR_IRF_FIXED")
    private Double irfFixed;
    
    public static MercuryIRFRateEntityBuilder builder() {
        return new MercuryIRFRateEntityBuilder();
    }
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getMccDesc() {
        return this.mccDesc;
    }
    
    public Double getIrfPercent() {
        return this.irfPercent;
    }
    
    public Double getIrfFixed() {
        return this.irfFixed;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setMccDesc(final String mccDesc) {
        this.mccDesc = mccDesc;
    }
    
    public void setIrfPercent(final Double irfPercent) {
        this.irfPercent = irfPercent;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MercuryIRFRateEntity)) {
            return false;
        }
        final MercuryIRFRateEntity other = (MercuryIRFRateEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0065: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$irfPercent = this.getIrfPercent();
        final Object other$irfPercent = other.getIrfPercent();
        Label_0102: {
            if (this$irfPercent == null) {
                if (other$irfPercent == null) {
                    break Label_0102;
                }
            }
            else if (this$irfPercent.equals(other$irfPercent)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$irfFixed = this.getIrfFixed();
        final Object other$irfFixed = other.getIrfFixed();
        Label_0139: {
            if (this$irfFixed == null) {
                if (other$irfFixed == null) {
                    break Label_0139;
                }
            }
            else if (this$irfFixed.equals(other$irfFixed)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0176: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0176;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0213: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0213;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$mccDesc = this.getMccDesc();
        final Object other$mccDesc = other.getMccDesc();
        if (this$mccDesc == null) {
            if (other$mccDesc == null) {
                return true;
            }
        }
        else if (this$mccDesc.equals(other$mccDesc)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof MercuryIRFRateEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $irfPercent = this.getIrfPercent();
        result = result * 59 + (($irfPercent == null) ? 43 : $irfPercent.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $mccDesc = this.getMccDesc();
        result = result * 59 + (($mccDesc == null) ? 43 : $mccDesc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "MercuryIRFRateEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", mcc=" + this.getMcc() + ", mccDesc=" + this.getMccDesc() + ", irfPercent=" + this.getIrfPercent() + ", irfFixed=" + this.getIrfFixed();
    }
    
    public MercuryIRFRateEntity() {
    }
    
    public MercuryIRFRateEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final String mcc, final String mccDesc, final Double irfPercent, final Double irfFixed) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.mcc = mcc;
        this.mccDesc = mccDesc;
        this.irfPercent = irfPercent;
        this.irfFixed = irfFixed;
    }
    
    public static class MercuryIRFRateEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private String mcc;
        private String mccDesc;
        private Double irfPercent;
        private Double irfFixed;
        
        MercuryIRFRateEntityBuilder() {
        }
        
        public MercuryIRFRateEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public MercuryIRFRateEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public MercuryIRFRateEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public MercuryIRFRateEntityBuilder mccDesc(final String mccDesc) {
            this.mccDesc = mccDesc;
            return this;
        }
        
        public MercuryIRFRateEntityBuilder irfPercent(final Double irfPercent) {
            this.irfPercent = irfPercent;
            return this;
        }
        
        public MercuryIRFRateEntityBuilder irfFixed(final Double irfFixed) {
            this.irfFixed = irfFixed;
            return this;
        }
        
        public MercuryIRFRateEntity build() {
            return new MercuryIRFRateEntity(this.serialNumber, this.lastUpdated, this.mcc, this.mccDesc, this.irfPercent, this.irfFixed);
        }
        
        @Override
        public String toString() {
            return "MercuryIRFRateEntity.MercuryIRFRateEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", mcc=" + this.mcc + ", mccDesc=" + this.mccDesc + ", irfPercent=" + this.irfPercent + ", irfFixed=" + this.irfFixed;
        }
    }
}
