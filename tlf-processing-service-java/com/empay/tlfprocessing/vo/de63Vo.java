// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de63Vo
{
    private String networkData;
    private String reImbursementAttribute;
    private String feePgmIndicator;
    
    public String getNetworkData() {
        return this.networkData;
    }
    
    public String getReImbursementAttribute() {
        return this.reImbursementAttribute;
    }
    
    public String getFeePgmIndicator() {
        return this.feePgmIndicator;
    }
    
    public void setNetworkData(final String networkData) {
        this.networkData = networkData;
    }
    
    public void setReImbursementAttribute(final String reImbursementAttribute) {
        this.reImbursementAttribute = reImbursementAttribute;
    }
    
    public void setFeePgmIndicator(final String feePgmIndicator) {
        this.feePgmIndicator = feePgmIndicator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de63Vo)) {
            return false;
        }
        final de63Vo other = (de63Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$networkData = this.getNetworkData();
        final Object other$networkData = other.getNetworkData();
        Label_0065: {
            if (this$networkData == null) {
                if (other$networkData == null) {
                    break Label_0065;
                }
            }
            else if (this$networkData.equals(other$networkData)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$reImbursementAttribute = this.getReImbursementAttribute();
        final Object other$reImbursementAttribute = other.getReImbursementAttribute();
        Label_0102: {
            if (this$reImbursementAttribute == null) {
                if (other$reImbursementAttribute == null) {
                    break Label_0102;
                }
            }
            else if (this$reImbursementAttribute.equals(other$reImbursementAttribute)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$feePgmIndicator = this.getFeePgmIndicator();
        final Object other$feePgmIndicator = other.getFeePgmIndicator();
        if (this$feePgmIndicator == null) {
            if (other$feePgmIndicator == null) {
                return true;
            }
        }
        else if (this$feePgmIndicator.equals(other$feePgmIndicator)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de63Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $networkData = this.getNetworkData();
        result = result * 59 + (($networkData == null) ? 43 : $networkData.hashCode());
        final Object $reImbursementAttribute = this.getReImbursementAttribute();
        result = result * 59 + (($reImbursementAttribute == null) ? 43 : $reImbursementAttribute.hashCode());
        final Object $feePgmIndicator = this.getFeePgmIndicator();
        result = result * 59 + (($feePgmIndicator == null) ? 43 : $feePgmIndicator.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de63Vo(networkData=" + this.getNetworkData() + ", reImbursementAttribute=" + this.getReImbursementAttribute() + ", feePgmIndicator=" + this.getFeePgmIndicator();
    }
}
