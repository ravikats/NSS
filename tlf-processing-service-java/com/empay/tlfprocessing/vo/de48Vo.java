// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de48Vo
{
    private String maid;
    private String setIndicator;
    private String pinServiceCode;
    private String meCountryCode;
    private String mposAccDevType;
    
    public String getMaid() {
        return this.maid;
    }
    
    public String getSetIndicator() {
        return this.setIndicator;
    }
    
    public String getPinServiceCode() {
        return this.pinServiceCode;
    }
    
    public String getMeCountryCode() {
        return this.meCountryCode;
    }
    
    public String getMposAccDevType() {
        return this.mposAccDevType;
    }
    
    public void setMaid(final String maid) {
        this.maid = maid;
    }
    
    public void setSetIndicator(final String setIndicator) {
        this.setIndicator = setIndicator;
    }
    
    public void setPinServiceCode(final String pinServiceCode) {
        this.pinServiceCode = pinServiceCode;
    }
    
    public void setMeCountryCode(final String meCountryCode) {
        this.meCountryCode = meCountryCode;
    }
    
    public void setMposAccDevType(final String mposAccDevType) {
        this.mposAccDevType = mposAccDevType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de48Vo)) {
            return false;
        }
        final de48Vo other = (de48Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$maid = this.getMaid();
        final Object other$maid = other.getMaid();
        Label_0065: {
            if (this$maid == null) {
                if (other$maid == null) {
                    break Label_0065;
                }
            }
            else if (this$maid.equals(other$maid)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$setIndicator = this.getSetIndicator();
        final Object other$setIndicator = other.getSetIndicator();
        Label_0102: {
            if (this$setIndicator == null) {
                if (other$setIndicator == null) {
                    break Label_0102;
                }
            }
            else if (this$setIndicator.equals(other$setIndicator)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$pinServiceCode = this.getPinServiceCode();
        final Object other$pinServiceCode = other.getPinServiceCode();
        Label_0139: {
            if (this$pinServiceCode == null) {
                if (other$pinServiceCode == null) {
                    break Label_0139;
                }
            }
            else if (this$pinServiceCode.equals(other$pinServiceCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$meCountryCode = this.getMeCountryCode();
        final Object other$meCountryCode = other.getMeCountryCode();
        Label_0176: {
            if (this$meCountryCode == null) {
                if (other$meCountryCode == null) {
                    break Label_0176;
                }
            }
            else if (this$meCountryCode.equals(other$meCountryCode)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$mposAccDevType = this.getMposAccDevType();
        final Object other$mposAccDevType = other.getMposAccDevType();
        if (this$mposAccDevType == null) {
            if (other$mposAccDevType == null) {
                return true;
            }
        }
        else if (this$mposAccDevType.equals(other$mposAccDevType)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de48Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $maid = this.getMaid();
        result = result * 59 + (($maid == null) ? 43 : $maid.hashCode());
        final Object $setIndicator = this.getSetIndicator();
        result = result * 59 + (($setIndicator == null) ? 43 : $setIndicator.hashCode());
        final Object $pinServiceCode = this.getPinServiceCode();
        result = result * 59 + (($pinServiceCode == null) ? 43 : $pinServiceCode.hashCode());
        final Object $meCountryCode = this.getMeCountryCode();
        result = result * 59 + (($meCountryCode == null) ? 43 : $meCountryCode.hashCode());
        final Object $mposAccDevType = this.getMposAccDevType();
        result = result * 59 + (($mposAccDevType == null) ? 43 : $mposAccDevType.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de48Vo(maid=" + this.getMaid() + ", setIndicator=" + this.getSetIndicator() + ", pinServiceCode=" + this.getPinServiceCode() + ", meCountryCode=" + this.getMeCountryCode() + ", mposAccDevType=" + this.getMposAccDevType();
    }
}
