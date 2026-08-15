// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de126Vo
{
    private String posEnv;
    
    public String getPosEnv() {
        return this.posEnv;
    }
    
    public void setPosEnv(final String posEnv) {
        this.posEnv = posEnv;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de126Vo)) {
            return false;
        }
        final de126Vo other = (de126Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$posEnv = this.getPosEnv();
        final Object other$posEnv = other.getPosEnv();
        if (this$posEnv == null) {
            if (other$posEnv == null) {
                return true;
            }
        }
        else if (this$posEnv.equals(other$posEnv)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de126Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $posEnv = this.getPosEnv();
        result = result * 59 + (($posEnv == null) ? 43 : $posEnv.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de126Vo(posEnv=" + this.getPosEnv();
    }
}
