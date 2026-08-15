// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MC_OVERRIDE_RATES")
public class McOverrideRatesEntity
{
    @Id
    @Column(name = "MOR_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "MOR_TRL_TYPE")
    private String terminalType;
    @Column(name = "MOR_CARD_TYPE")
    private Character cardType;
    @Column(name = "MOR_OVERRIDE_ID")
    private String overRideID;
    @Column(name = "MOR_IRD")
    private String ird;
    @Column(name = "MOR_TXN_LIMIT_IND")
    private Character txnLimitIndicator;
    @Column(name = "MOR_MCC")
    private String mcc;
    @Column(name = "MOR_PERCENT")
    private Double percent;
    @Column(name = "MOR_FIXED")
    private Double fixed;
    @Column(name = "MOR_MAX")
    private Double max;
    @Column(name = "MOR_DESCRIPTION")
    private String description;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public String getTerminalType() {
        return this.terminalType;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public String getOverRideID() {
        return this.overRideID;
    }
    
    public String getIrd() {
        return this.ird;
    }
    
    public Character getTxnLimitIndicator() {
        return this.txnLimitIndicator;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public Double getPercent() {
        return this.percent;
    }
    
    public Double getFixed() {
        return this.fixed;
    }
    
    public Double getMax() {
        return this.max;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setTerminalType(final String terminalType) {
        this.terminalType = terminalType;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setOverRideID(final String overRideID) {
        this.overRideID = overRideID;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setTxnLimitIndicator(final Character txnLimitIndicator) {
        this.txnLimitIndicator = txnLimitIndicator;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setPercent(final Double percent) {
        this.percent = percent;
    }
    
    public void setFixed(final Double fixed) {
        this.fixed = fixed;
    }
    
    public void setMax(final Double max) {
        this.max = max;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McOverrideRatesEntity)) {
            return false;
        }
        final McOverrideRatesEntity other = (McOverrideRatesEntity)o;
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
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0102: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0102;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$txnLimitIndicator = this.getTxnLimitIndicator();
        final Object other$txnLimitIndicator = other.getTxnLimitIndicator();
        Label_0139: {
            if (this$txnLimitIndicator == null) {
                if (other$txnLimitIndicator == null) {
                    break Label_0139;
                }
            }
            else if (this$txnLimitIndicator.equals(other$txnLimitIndicator)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$percent = this.getPercent();
        final Object other$percent = other.getPercent();
        Label_0176: {
            if (this$percent == null) {
                if (other$percent == null) {
                    break Label_0176;
                }
            }
            else if (this$percent.equals(other$percent)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$fixed = this.getFixed();
        final Object other$fixed = other.getFixed();
        Label_0213: {
            if (this$fixed == null) {
                if (other$fixed == null) {
                    break Label_0213;
                }
            }
            else if (this$fixed.equals(other$fixed)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$max = this.getMax();
        final Object other$max = other.getMax();
        Label_0250: {
            if (this$max == null) {
                if (other$max == null) {
                    break Label_0250;
                }
            }
            else if (this$max.equals(other$max)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$terminalType = this.getTerminalType();
        final Object other$terminalType = other.getTerminalType();
        Label_0287: {
            if (this$terminalType == null) {
                if (other$terminalType == null) {
                    break Label_0287;
                }
            }
            else if (this$terminalType.equals(other$terminalType)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$overRideID = this.getOverRideID();
        final Object other$overRideID = other.getOverRideID();
        Label_0324: {
            if (this$overRideID == null) {
                if (other$overRideID == null) {
                    break Label_0324;
                }
            }
            else if (this$overRideID.equals(other$overRideID)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$ird = this.getIrd();
        final Object other$ird = other.getIrd();
        Label_0361: {
            if (this$ird == null) {
                if (other$ird == null) {
                    break Label_0361;
                }
            }
            else if (this$ird.equals(other$ird)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0398: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0398;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null) {
            if (other$description == null) {
                return true;
            }
        }
        else if (this$description.equals(other$description)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof McOverrideRatesEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $txnLimitIndicator = this.getTxnLimitIndicator();
        result = result * 59 + (($txnLimitIndicator == null) ? 43 : $txnLimitIndicator.hashCode());
        final Object $percent = this.getPercent();
        result = result * 59 + (($percent == null) ? 43 : $percent.hashCode());
        final Object $fixed = this.getFixed();
        result = result * 59 + (($fixed == null) ? 43 : $fixed.hashCode());
        final Object $max = this.getMax();
        result = result * 59 + (($max == null) ? 43 : $max.hashCode());
        final Object $terminalType = this.getTerminalType();
        result = result * 59 + (($terminalType == null) ? 43 : $terminalType.hashCode());
        final Object $overRideID = this.getOverRideID();
        result = result * 59 + (($overRideID == null) ? 43 : $overRideID.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "McOverrideRatesEntity(serNumber=" + this.getSerNumber() + ", terminalType=" + this.getTerminalType() + ", cardType=" + this.getCardType() + ", overRideID=" + this.getOverRideID() + ", ird=" + this.getIrd() + ", txnLimitIndicator=" + this.getTxnLimitIndicator() + ", mcc=" + this.getMcc() + ", percent=" + this.getPercent() + ", fixed=" + this.getFixed() + ", max=" + this.getMax() + ", description=" + this.getDescription();
    }
}

