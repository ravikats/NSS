// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.vo;

public class MCOverRideResultVo
{
    private Integer morSerNumber;
    private double morPercentage;
    private double morFixed;
    private double morMax;
    
    public Integer getMorSerNumber() {
        return this.morSerNumber;
    }
    
    public double getMorPercentage() {
        return this.morPercentage;
    }
    
    public double getMorFixed() {
        return this.morFixed;
    }
    
    public double getMorMax() {
        return this.morMax;
    }
    
    public void setMorSerNumber(final Integer morSerNumber) {
        this.morSerNumber = morSerNumber;
    }
    
    public void setMorPercentage(final double morPercentage) {
        this.morPercentage = morPercentage;
    }
    
    public void setMorFixed(final double morFixed) {
        this.morFixed = morFixed;
    }
    
    public void setMorMax(final double morMax) {
        this.morMax = morMax;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MCOverRideResultVo)) {
            return false;
        }
        final MCOverRideResultVo other = (MCOverRideResultVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Double.compare(this.getMorPercentage(), other.getMorPercentage()) != 0) {
            return false;
        }
        if (Double.compare(this.getMorFixed(), other.getMorFixed()) != 0) {
            return false;
        }
        if (Double.compare(this.getMorMax(), other.getMorMax()) != 0) {
            return false;
        }
        final Object this$morSerNumber = this.getMorSerNumber();
        final Object other$morSerNumber = other.getMorSerNumber();
        if (this$morSerNumber == null) {
            if (other$morSerNumber == null) {
                return true;
            }
        }
        else if (this$morSerNumber.equals(other$morSerNumber)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof MCOverRideResultVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $morPercentage = Double.doubleToLongBits(this.getMorPercentage());
        result = result * 59 + (int)($morPercentage >>> 32 ^ $morPercentage);
        final long $morFixed = Double.doubleToLongBits(this.getMorFixed());
        result = result * 59 + (int)($morFixed >>> 32 ^ $morFixed);
        final long $morMax = Double.doubleToLongBits(this.getMorMax());
        result = result * 59 + (int)($morMax >>> 32 ^ $morMax);
        final Object $morSerNumber = this.getMorSerNumber();
        result = result * 59 + (($morSerNumber == null) ? 43 : $morSerNumber.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "MCOverRideResultVo(morSerNumber=" + this.getMorSerNumber() + ", morPercentage=" + this.getMorPercentage() + ", morFixed=" + this.getMorFixed() + ", morMax=" + this.getMorMax();
    }
}

