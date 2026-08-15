// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "VISA_IRF_PROGRAMS")
public class VisaIrfProgramEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VRF_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "VRF_REGION")
    private Character region;
    @Column(name = "VRF_TRL_TYPE")
    private String terminalType;
    @Column(name = "VRF_CARD_TYPE")
    private Character cardType;
    @Column(name = "VRF_FP_TYPE")
    private String fpType;
    @Column(name = "VRF_FP_VALUE")
    private String fpValue;
    @Column(name = "VRF_TXN_LIMIT_IND")
    private Character txnLimitIndicator;
    @Column(name = "VRF_FP_DESC")
    private String fpDesc;
    @Column(name = "VRF_PERCENT")
    private Double percent;
    @Column(name = "VRF_FIXED")
    private Double fixed;
    @Column(name = "VRF_MAX")
    private Double maximum;
    @Column(name = "VRF_SLAB")
    private Integer slab;
    @Column(name = "VRF_MCC")
    private String mcc;
    @Column(name = "VRF_MIN")
    private Double minimum;
    @Column(name = "VRF_QUALIFIER_IND")
    private Character qualifierIndicator;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Character getRegion() {
        return this.region;
    }
    
    public String getTerminalType() {
        return this.terminalType;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public String getFpType() {
        return this.fpType;
    }
    
    public String getFpValue() {
        return this.fpValue;
    }
    
    public Character getTxnLimitIndicator() {
        return this.txnLimitIndicator;
    }
    
    public String getFpDesc() {
        return this.fpDesc;
    }
    
    public Double getPercent() {
        return this.percent;
    }
    
    public Double getFixed() {
        return this.fixed;
    }
    
    public Double getMaximum() {
        return this.maximum;
    }
    
    public Integer getSlab() {
        return this.slab;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public Double getMinimum() {
        return this.minimum;
    }
    
    public Character getQualifierIndicator() {
        return this.qualifierIndicator;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setRegion(final Character region) {
        this.region = region;
    }
    
    public void setTerminalType(final String terminalType) {
        this.terminalType = terminalType;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setFpType(final String fpType) {
        this.fpType = fpType;
    }
    
    public void setFpValue(final String fpValue) {
        this.fpValue = fpValue;
    }
    
    public void setTxnLimitIndicator(final Character txnLimitIndicator) {
        this.txnLimitIndicator = txnLimitIndicator;
    }
    
    public void setFpDesc(final String fpDesc) {
        this.fpDesc = fpDesc;
    }
    
    public void setPercent(final Double percent) {
        this.percent = percent;
    }
    
    public void setFixed(final Double fixed) {
        this.fixed = fixed;
    }
    
    public void setMaximum(final Double maximum) {
        this.maximum = maximum;
    }
    
    public void setSlab(final Integer slab) {
        this.slab = slab;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setMinimum(final Double minimum) {
        this.minimum = minimum;
    }
    
    public void setQualifierIndicator(final Character qualifierIndicator) {
        this.qualifierIndicator = qualifierIndicator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaIrfProgramEntity)) {
            return false;
        }
        final VisaIrfProgramEntity other = (VisaIrfProgramEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0065: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        Label_0102: {
            if (this$region == null) {
                if (other$region == null) {
                    break Label_0102;
                }
            }
            else if (this$region.equals(other$region)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0139: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0139;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$txnLimitIndicator = this.getTxnLimitIndicator();
        final Object other$txnLimitIndicator = other.getTxnLimitIndicator();
        Label_0176: {
            if (this$txnLimitIndicator == null) {
                if (other$txnLimitIndicator == null) {
                    break Label_0176;
                }
            }
            else if (this$txnLimitIndicator.equals(other$txnLimitIndicator)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$percent = this.getPercent();
        final Object other$percent = other.getPercent();
        Label_0213: {
            if (this$percent == null) {
                if (other$percent == null) {
                    break Label_0213;
                }
            }
            else if (this$percent.equals(other$percent)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$fixed = this.getFixed();
        final Object other$fixed = other.getFixed();
        Label_0250: {
            if (this$fixed == null) {
                if (other$fixed == null) {
                    break Label_0250;
                }
            }
            else if (this$fixed.equals(other$fixed)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$maximum = this.getMaximum();
        final Object other$maximum = other.getMaximum();
        Label_0287: {
            if (this$maximum == null) {
                if (other$maximum == null) {
                    break Label_0287;
                }
            }
            else if (this$maximum.equals(other$maximum)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$slab = this.getSlab();
        final Object other$slab = other.getSlab();
        Label_0324: {
            if (this$slab == null) {
                if (other$slab == null) {
                    break Label_0324;
                }
            }
            else if (this$slab.equals(other$slab)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$minimum = this.getMinimum();
        final Object other$minimum = other.getMinimum();
        Label_0361: {
            if (this$minimum == null) {
                if (other$minimum == null) {
                    break Label_0361;
                }
            }
            else if (this$minimum.equals(other$minimum)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$qualifierIndicator = this.getQualifierIndicator();
        final Object other$qualifierIndicator = other.getQualifierIndicator();
        Label_0398: {
            if (this$qualifierIndicator == null) {
                if (other$qualifierIndicator == null) {
                    break Label_0398;
                }
            }
            else if (this$qualifierIndicator.equals(other$qualifierIndicator)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$terminalType = this.getTerminalType();
        final Object other$terminalType = other.getTerminalType();
        Label_0435: {
            if (this$terminalType == null) {
                if (other$terminalType == null) {
                    break Label_0435;
                }
            }
            else if (this$terminalType.equals(other$terminalType)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$fpType = this.getFpType();
        final Object other$fpType = other.getFpType();
        Label_0472: {
            if (this$fpType == null) {
                if (other$fpType == null) {
                    break Label_0472;
                }
            }
            else if (this$fpType.equals(other$fpType)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$fpValue = this.getFpValue();
        final Object other$fpValue = other.getFpValue();
        Label_0509: {
            if (this$fpValue == null) {
                if (other$fpValue == null) {
                    break Label_0509;
                }
            }
            else if (this$fpValue.equals(other$fpValue)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$fpDesc = this.getFpDesc();
        final Object other$fpDesc = other.getFpDesc();
        Label_0546: {
            if (this$fpDesc == null) {
                if (other$fpDesc == null) {
                    break Label_0546;
                }
            }
            else if (this$fpDesc.equals(other$fpDesc)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        if (this$mcc == null) {
            if (other$mcc == null) {
                return true;
            }
        }
        else if (this$mcc.equals(other$mcc)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof VisaIrfProgramEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $region = this.getRegion();
        result = result * 59 + (($region == null) ? 43 : $region.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $txnLimitIndicator = this.getTxnLimitIndicator();
        result = result * 59 + (($txnLimitIndicator == null) ? 43 : $txnLimitIndicator.hashCode());
        final Object $percent = this.getPercent();
        result = result * 59 + (($percent == null) ? 43 : $percent.hashCode());
        final Object $fixed = this.getFixed();
        result = result * 59 + (($fixed == null) ? 43 : $fixed.hashCode());
        final Object $maximum = this.getMaximum();
        result = result * 59 + (($maximum == null) ? 43 : $maximum.hashCode());
        final Object $slab = this.getSlab();
        result = result * 59 + (($slab == null) ? 43 : $slab.hashCode());
        final Object $minimum = this.getMinimum();
        result = result * 59 + (($minimum == null) ? 43 : $minimum.hashCode());
        final Object $qualifierIndicator = this.getQualifierIndicator();
        result = result * 59 + (($qualifierIndicator == null) ? 43 : $qualifierIndicator.hashCode());
        final Object $terminalType = this.getTerminalType();
        result = result * 59 + (($terminalType == null) ? 43 : $terminalType.hashCode());
        final Object $fpType = this.getFpType();
        result = result * 59 + (($fpType == null) ? 43 : $fpType.hashCode());
        final Object $fpValue = this.getFpValue();
        result = result * 59 + (($fpValue == null) ? 43 : $fpValue.hashCode());
        final Object $fpDesc = this.getFpDesc();
        result = result * 59 + (($fpDesc == null) ? 43 : $fpDesc.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "VisaIrfProgramEntity(serNumber=" + this.getSerNumber() + ", region=" + this.getRegion() + ", terminalType=" + this.getTerminalType() + ", cardType=" + this.getCardType() + ", fpType=" + this.getFpType() + ", fpValue=" + this.getFpValue() + ", txnLimitIndicator=" + this.getTxnLimitIndicator() + ", fpDesc=" + this.getFpDesc() + ", percent=" + this.getPercent() + ", fixed=" + this.getFixed() + ", maximum=" + this.getMaximum() + ", slab=" + this.getSlab() + ", mcc=" + this.getMcc() + ", minimum=" + this.getMinimum() + ", qualifierIndicator=" + this.getQualifierIndicator();
    }
}

