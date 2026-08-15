// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de111Vo
{
    private String accountFundingSource;
    
    public String getAccountFundingSource() {
        return this.accountFundingSource;
    }
    
    public void setAccountFundingSource(final String accountFundingSource) {
        this.accountFundingSource = accountFundingSource;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de111Vo)) {
            return false;
        }
        final de111Vo other = (de111Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$accountFundingSource = this.getAccountFundingSource();
        final Object other$accountFundingSource = other.getAccountFundingSource();
        if (this$accountFundingSource == null) {
            if (other$accountFundingSource == null) {
                return true;
            }
        }
        else if (this$accountFundingSource.equals(other$accountFundingSource)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de111Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accountFundingSource = this.getAccountFundingSource();
        result = result * 59 + (($accountFundingSource == null) ? 43 : $accountFundingSource.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de111Vo(accountFundingSource=" + this.getAccountFundingSource();
    }
}
