// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class UAESwitchIRFVo
{
    private String segment;
    private String segmentDesc;
    private String mcc;
    private String mccDescription;
    private Double irfRate;
    private Double irfMax;
    private Double irfFixed;
    private Double posIrf;
    private Double ecomIrf;
    private Double posIrfMax;
    private Double ecomIrfMax;
    
    public String getSegment() {
        return this.segment;
    }
    
    public String getSegmentDesc() {
        return this.segmentDesc;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getMccDescription() {
        return this.mccDescription;
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
    
    public void setSegment(final String segment) {
        this.segment = segment;
    }
    
    public void setSegmentDesc(final String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setMccDescription(final String mccDescription) {
        this.mccDescription = mccDescription;
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
        if (!(o instanceof UAESwitchIRFVo)) {
            return false;
        }
        final UAESwitchIRFVo other = (UAESwitchIRFVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$irfRate = this.getIrfRate();
        final Object other$irfRate = other.getIrfRate();
        Label_0065: {
            if (this$irfRate == null) {
                if (other$irfRate == null) {
                    break Label_0065;
                }
            }
            else if (this$irfRate.equals(other$irfRate)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$irfMax = this.getIrfMax();
        final Object other$irfMax = other.getIrfMax();
        Label_0102: {
            if (this$irfMax == null) {
                if (other$irfMax == null) {
                    break Label_0102;
                }
            }
            else if (this$irfMax.equals(other$irfMax)) {
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
        final Object this$posIrf = this.getPosIrf();
        final Object other$posIrf = other.getPosIrf();
        Label_0176: {
            if (this$posIrf == null) {
                if (other$posIrf == null) {
                    break Label_0176;
                }
            }
            else if (this$posIrf.equals(other$posIrf)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$ecomIrf = this.getEcomIrf();
        final Object other$ecomIrf = other.getEcomIrf();
        Label_0213: {
            if (this$ecomIrf == null) {
                if (other$ecomIrf == null) {
                    break Label_0213;
                }
            }
            else if (this$ecomIrf.equals(other$ecomIrf)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$posIrfMax = this.getPosIrfMax();
        final Object other$posIrfMax = other.getPosIrfMax();
        Label_0250: {
            if (this$posIrfMax == null) {
                if (other$posIrfMax == null) {
                    break Label_0250;
                }
            }
            else if (this$posIrfMax.equals(other$posIrfMax)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$ecomIrfMax = this.getEcomIrfMax();
        final Object other$ecomIrfMax = other.getEcomIrfMax();
        Label_0287: {
            if (this$ecomIrfMax == null) {
                if (other$ecomIrfMax == null) {
                    break Label_0287;
                }
            }
            else if (this$ecomIrfMax.equals(other$ecomIrfMax)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$segment = this.getSegment();
        final Object other$segment = other.getSegment();
        Label_0324: {
            if (this$segment == null) {
                if (other$segment == null) {
                    break Label_0324;
                }
            }
            else if (this$segment.equals(other$segment)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$segmentDesc = this.getSegmentDesc();
        final Object other$segmentDesc = other.getSegmentDesc();
        Label_0361: {
            if (this$segmentDesc == null) {
                if (other$segmentDesc == null) {
                    break Label_0361;
                }
            }
            else if (this$segmentDesc.equals(other$segmentDesc)) {
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
        return other instanceof UAESwitchIRFVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
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
        final Object $segment = this.getSegment();
        result = result * 59 + (($segment == null) ? 43 : $segment.hashCode());
        final Object $segmentDesc = this.getSegmentDesc();
        result = result * 59 + (($segmentDesc == null) ? 43 : $segmentDesc.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $mccDescription = this.getMccDescription();
        result = result * 59 + (($mccDescription == null) ? 43 : $mccDescription.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UAESwitchIRFVo(segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDescription=" + this.getMccDescription() + ", irfRate=" + this.getIrfRate() + ", irfMax=" + this.getIrfMax() + ", irfFixed=" + this.getIrfFixed() + ", posIrf=" + this.getPosIrf() + ", ecomIrf=" + this.getEcomIrf() + ", posIrfMax=" + this.getPosIrfMax() + ", ecomIrfMax=" + this.getEcomIrfMax();
    }
}
