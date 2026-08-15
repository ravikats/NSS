// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class dccVo
{
    private String dcc_amount;
    private String dcc_currency;
    private String dcc_indicator;
    private String exchange_rate;
    
    public String getDcc_amount() {
        return this.dcc_amount;
    }
    
    public String getDcc_currency() {
        return this.dcc_currency;
    }
    
    public String getDcc_indicator() {
        return this.dcc_indicator;
    }
    
    public String getExchange_rate() {
        return this.exchange_rate;
    }
    
    public void setDcc_amount(final String dcc_amount) {
        this.dcc_amount = dcc_amount;
    }
    
    public void setDcc_currency(final String dcc_currency) {
        this.dcc_currency = dcc_currency;
    }
    
    public void setDcc_indicator(final String dcc_indicator) {
        this.dcc_indicator = dcc_indicator;
    }
    
    public void setExchange_rate(final String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof dccVo)) {
            return false;
        }
        final dccVo other = (dccVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$dcc_amount = this.getDcc_amount();
        final Object other$dcc_amount = other.getDcc_amount();
        Label_0065: {
            if (this$dcc_amount == null) {
                if (other$dcc_amount == null) {
                    break Label_0065;
                }
            }
            else if (this$dcc_amount.equals(other$dcc_amount)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$dcc_currency = this.getDcc_currency();
        final Object other$dcc_currency = other.getDcc_currency();
        Label_0102: {
            if (this$dcc_currency == null) {
                if (other$dcc_currency == null) {
                    break Label_0102;
                }
            }
            else if (this$dcc_currency.equals(other$dcc_currency)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$dcc_indicator = this.getDcc_indicator();
        final Object other$dcc_indicator = other.getDcc_indicator();
        Label_0139: {
            if (this$dcc_indicator == null) {
                if (other$dcc_indicator == null) {
                    break Label_0139;
                }
            }
            else if (this$dcc_indicator.equals(other$dcc_indicator)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$exchange_rate = this.getExchange_rate();
        final Object other$exchange_rate = other.getExchange_rate();
        if (this$exchange_rate == null) {
            if (other$exchange_rate == null) {
                return true;
            }
        }
        else if (this$exchange_rate.equals(other$exchange_rate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof dccVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $dcc_amount = this.getDcc_amount();
        result = result * 59 + (($dcc_amount == null) ? 43 : $dcc_amount.hashCode());
        final Object $dcc_currency = this.getDcc_currency();
        result = result * 59 + (($dcc_currency == null) ? 43 : $dcc_currency.hashCode());
        final Object $dcc_indicator = this.getDcc_indicator();
        result = result * 59 + (($dcc_indicator == null) ? 43 : $dcc_indicator.hashCode());
        final Object $exchange_rate = this.getExchange_rate();
        result = result * 59 + (($exchange_rate == null) ? 43 : $exchange_rate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "dccVo(dcc_amount=" + this.getDcc_amount() + ", dcc_currency=" + this.getDcc_currency() + ", dcc_indicator=" + this.getDcc_indicator() + ", exchange_rate=" + this.getExchange_rate();
    }
}
