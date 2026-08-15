// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "UAE_SWITCH_IRF")
public class UAESwitchIRFEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "URF_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "URF_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "URF_SEGMENT")
    private String segment;
    @Column(name = "URF_SEGMENT_DESC")
    private String segmentDesc;
    @Column(name = "URF_MCC")
    private String mcc;
    @Column(name = "URF_MCC_DESC")
    private String mccDesc;
    @Column(name = "URF_IRF_RATE")
    private Double irfRate;
    @Column(name = "URF_IRF_MAX")
    private Double irfMax;
    @Column(name = "URF_IRF_FIXED")
    private Double irfFixed;
    @Column(name = "URF_POS_IRF")
    private Double posIrf;
    @Column(name = "URF_ECOM_IRF")
    private Double ecomIrf;
    @Column(name = "URF_POS_IRF_MAX")
    private Double posIrfMax;
    @Column(name = "URF_ECOM_IRF_MAX")
    private Double ecomIrfMax;
    
    public static UAESwitchIRFEntityBuilder builder() {
        return new UAESwitchIRFEntityBuilder();
    }
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public String getSegment() {
        return this.segment;
    }
    
    public String getSegmentDesc() {
        return this.segmentDesc;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getMccDesc() {
        return this.mccDesc;
    }
    
    public Double getIrfRate() {
        return this.irfRate;
    }
    
    public Double getIrfMax() {
        return this.irfMax;
    }
    
    public Double getIrfFixed() {
        return this.irfFixed;
    }
    
    public Double getPosIrf() {
        return this.posIrf;
    }
    
    public Double getEcomIrf() {
        return this.ecomIrf;
    }
    
    public Double getPosIrfMax() {
        return this.posIrfMax;
    }
    
    public Double getEcomIrfMax() {
        return this.ecomIrfMax;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setSegment(final String segment) {
        this.segment = segment;
    }
    
    public void setSegmentDesc(final String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setMccDesc(final String mccDesc) {
        this.mccDesc = mccDesc;
    }
    
    public void setIrfRate(final Double irfRate) {
        this.irfRate = irfRate;
    }
    
    public void setIrfMax(final Double irfMax) {
        this.irfMax = irfMax;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    public void setPosIrf(final Double posIrf) {
        this.posIrf = posIrf;
    }
    
    public void setEcomIrf(final Double ecomIrf) {
        this.ecomIrf = ecomIrf;
    }
    
    public void setPosIrfMax(final Double posIrfMax) {
        this.posIrfMax = posIrfMax;
    }
    
    public void setEcomIrfMax(final Double ecomIrfMax) {
        this.ecomIrfMax = ecomIrfMax;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UAESwitchIRFEntity)) {
            return false;
        }
        final UAESwitchIRFEntity other = (UAESwitchIRFEntity)o;
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
        final Object this$irfRate = this.getIrfRate();
        final Object other$irfRate = other.getIrfRate();
        Label_0102: {
            if (this$irfRate == null) {
                if (other$irfRate == null) {
                    break Label_0102;
                }
            }
            else if (this$irfRate.equals(other$irfRate)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$irfMax = this.getIrfMax();
        final Object other$irfMax = other.getIrfMax();
        Label_0139: {
            if (this$irfMax == null) {
                if (other$irfMax == null) {
                    break Label_0139;
                }
            }
            else if (this$irfMax.equals(other$irfMax)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$irfFixed = this.getIrfFixed();
        final Object other$irfFixed = other.getIrfFixed();
        Label_0176: {
            if (this$irfFixed == null) {
                if (other$irfFixed == null) {
                    break Label_0176;
                }
            }
            else if (this$irfFixed.equals(other$irfFixed)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$posIrf = this.getPosIrf();
        final Object other$posIrf = other.getPosIrf();
        Label_0213: {
            if (this$posIrf == null) {
                if (other$posIrf == null) {
                    break Label_0213;
                }
            }
            else if (this$posIrf.equals(other$posIrf)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$ecomIrf = this.getEcomIrf();
        final Object other$ecomIrf = other.getEcomIrf();
        Label_0250: {
            if (this$ecomIrf == null) {
                if (other$ecomIrf == null) {
                    break Label_0250;
                }
            }
            else if (this$ecomIrf.equals(other$ecomIrf)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$posIrfMax = this.getPosIrfMax();
        final Object other$posIrfMax = other.getPosIrfMax();
        Label_0287: {
            if (this$posIrfMax == null) {
                if (other$posIrfMax == null) {
                    break Label_0287;
                }
            }
            else if (this$posIrfMax.equals(other$posIrfMax)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$ecomIrfMax = this.getEcomIrfMax();
        final Object other$ecomIrfMax = other.getEcomIrfMax();
        Label_0324: {
            if (this$ecomIrfMax == null) {
                if (other$ecomIrfMax == null) {
                    break Label_0324;
                }
            }
            else if (this$ecomIrfMax.equals(other$ecomIrfMax)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0361: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0361;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$segment = this.getSegment();
        final Object other$segment = other.getSegment();
        Label_0398: {
            if (this$segment == null) {
                if (other$segment == null) {
                    break Label_0398;
                }
            }
            else if (this$segment.equals(other$segment)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$segmentDesc = this.getSegmentDesc();
        final Object other$segmentDesc = other.getSegmentDesc();
        Label_0435: {
            if (this$segmentDesc == null) {
                if (other$segmentDesc == null) {
                    break Label_0435;
                }
            }
            else if (this$segmentDesc.equals(other$segmentDesc)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0472: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0472;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0472;
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
        return other instanceof UAESwitchIRFEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $irfRate = this.getIrfRate();
        result = result * 59 + (($irfRate == null) ? 43 : $irfRate.hashCode());
        final Object $irfMax = this.getIrfMax();
        result = result * 59 + (($irfMax == null) ? 43 : $irfMax.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $posIrf = this.getPosIrf();
        result = result * 59 + (($posIrf == null) ? 43 : $posIrf.hashCode());
        final Object $ecomIrf = this.getEcomIrf();
        result = result * 59 + (($ecomIrf == null) ? 43 : $ecomIrf.hashCode());
        final Object $posIrfMax = this.getPosIrfMax();
        result = result * 59 + (($posIrfMax == null) ? 43 : $posIrfMax.hashCode());
        final Object $ecomIrfMax = this.getEcomIrfMax();
        result = result * 59 + (($ecomIrfMax == null) ? 43 : $ecomIrfMax.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $segment = this.getSegment();
        result = result * 59 + (($segment == null) ? 43 : $segment.hashCode());
        final Object $segmentDesc = this.getSegmentDesc();
        result = result * 59 + (($segmentDesc == null) ? 43 : $segmentDesc.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $mccDesc = this.getMccDesc();
        result = result * 59 + (($mccDesc == null) ? 43 : $mccDesc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UAESwitchIRFEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDesc=" + this.getMccDesc() + ", irfRate=" + this.getIrfRate() + ", irfMax=" + this.getIrfMax() + ", irfFixed=" + this.getIrfFixed() + ", posIrf=" + this.getPosIrf() + ", ecomIrf=" + this.getEcomIrf() + ", posIrfMax=" + this.getPosIrfMax() + ", ecomIrfMax=" + this.getEcomIrfMax();
    }
    
    public UAESwitchIRFEntity() {
    }
    
    public UAESwitchIRFEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final String segment, final String segmentDesc, final String mcc, final String mccDesc, final Double irfRate, final Double irfMax, final Double irfFixed, final Double posIrf, final Double ecomIrf, final Double posIrfMax, final Double ecomIrfMax) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.segment = segment;
        this.segmentDesc = segmentDesc;
        this.mcc = mcc;
        this.mccDesc = mccDesc;
        this.irfRate = irfRate;
        this.irfMax = irfMax;
        this.irfFixed = irfFixed;
        this.posIrf = posIrf;
        this.ecomIrf = ecomIrf;
        this.posIrfMax = posIrfMax;
        this.ecomIrfMax = ecomIrfMax;
    }
    
    public static class UAESwitchIRFEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private String segment;
        private String segmentDesc;
        private String mcc;
        private String mccDesc;
        private Double irfRate;
        private Double irfMax;
        private Double irfFixed;
        private Double posIrf;
        private Double ecomIrf;
        private Double posIrfMax;
        private Double ecomIrfMax;
        
        UAESwitchIRFEntityBuilder() {
        }
        
        public UAESwitchIRFEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder segment(final String segment) {
            this.segment = segment;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder segmentDesc(final String segmentDesc) {
            this.segmentDesc = segmentDesc;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder mccDesc(final String mccDesc) {
            this.mccDesc = mccDesc;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder irfRate(final Double irfRate) {
            this.irfRate = irfRate;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder irfMax(final Double irfMax) {
            this.irfMax = irfMax;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder irfFixed(final Double irfFixed) {
            this.irfFixed = irfFixed;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder posIrf(final Double posIrf) {
            this.posIrf = posIrf;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder ecomIrf(final Double ecomIrf) {
            this.ecomIrf = ecomIrf;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder posIrfMax(final Double posIrfMax) {
            this.posIrfMax = posIrfMax;
            return this;
        }
        
        public UAESwitchIRFEntityBuilder ecomIrfMax(final Double ecomIrfMax) {
            this.ecomIrfMax = ecomIrfMax;
            return this;
        }
        
        public UAESwitchIRFEntity build() {
            return new UAESwitchIRFEntity(this.serialNumber, this.lastUpdated, this.segment, this.segmentDesc, this.mcc, this.mccDesc, this.irfRate, this.irfMax, this.irfFixed, this.posIrf, this.ecomIrf, this.posIrfMax, this.ecomIrfMax);
        }
        
        @Override
        public String toString() {
            return "UAESwitchIRFEntity.UAESwitchIRFEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", segment=" + this.segment + ", segmentDesc=" + this.segmentDesc + ", mcc=" + this.mcc + ", mccDesc=" + this.mccDesc + ", irfRate=" + this.irfRate + ", irfMax=" + this.irfMax + ", irfFixed=" + this.irfFixed + ", posIrf=" + this.posIrf + ", ecomIrf=" + this.ecomIrf + ", posIrfMax=" + this.posIrfMax + ", ecomIrfMax=" + this.ecomIrfMax;
        }
    }
}
