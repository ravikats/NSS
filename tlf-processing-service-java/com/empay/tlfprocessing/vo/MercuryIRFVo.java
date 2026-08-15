// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class MercuryIRFVo
{
    private String mcc;
    private String mccDescription;
    private Double irfPercentage;
    private Double irfFixed;
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getMccDescription() {
        return this.mccDescription;
    }
    
    public Double getIrfPercentage() {
        return this.irfPercentage;
    }
    
    public Double getIrfFixed() {
        return this.irfFixed;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setMccDescription(final String mccDescription) {
        this.mccDescription = mccDescription;
    }
    
    public void setIrfPercentage(final Double irfPercentage) {
        this.irfPercentage = irfPercentage;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MercuryIRFVo)) {
            return false;
        }
        final MercuryIRFVo other = (MercuryIRFVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$irfPercentage = this.getIrfPercentage();
        final Object other$irfPercentage = other.getIrfPercentage();
        Label_0065: {
            if (this$irfPercentage == null) {
                if (other$irfPercentage == null) {
                    break Label_0065;
                }
            }
            else if (this$irfPercentage.equals(other$irfPercentage)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$irfFixed = this.getIrfFixed();
        final Object other$irfFixed = other.getIrfFixed();
        Label_0102: {
            if (this$irfFixed == null) {
                if (other$irfFixed == null) {
                    break Label_0102;
                }
            }
            else if (this$irfFixed.equals(other$irfFixed)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0139: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0139;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$mccDescription = this.getMccDescription();
        final Object other$mccDescription = other.getMccDescription();
        if (this$mccDescription == null) {
            if (other$mccDescription == null) {
                return true;
            }
        }
        else if (this$mccDescription.equals(other$mccDescription)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof MercuryIRFVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $irfPercentage = this.getIrfPercentage();
        result = result * 59 + (($irfPercentage == null) ? 43 : $irfPercentage.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $mccDescription = this.getMccDescription();
        result = result * 59 + (($mccDescription == null) ? 43 : $mccDescription.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "MercuryIRFVo(mcc=" + this.getMcc() + ", mccDescription=" + this.getMccDescription() + ", irfPercentage=" + this.getIrfPercentage() + ", irfFixed=" + this.getIrfFixed();
    }
}
