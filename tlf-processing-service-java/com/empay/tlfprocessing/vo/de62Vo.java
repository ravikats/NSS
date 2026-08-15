// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de62Vo
{
    private String authCharecteresticId;
    private String txnId;
    private String validationCode;
    private String marketSpecAuthDataInd;
    private String mvv;
    private String productId;
    private String spendQualificationInd;
    
    public String getAuthCharecteresticId() {
        return this.authCharecteresticId;
    }
    
    public String getTxnId() {
        return this.txnId;
    }
    
    public String getValidationCode() {
        return this.validationCode;
    }
    
    public String getMarketSpecAuthDataInd() {
        return this.marketSpecAuthDataInd;
    }
    
    public String getMvv() {
        return this.mvv;
    }
    
    public String getProductId() {
        return this.productId;
    }
    
    public String getSpendQualificationInd() {
        return this.spendQualificationInd;
    }
    
    public void setAuthCharecteresticId(final String authCharecteresticId) {
        this.authCharecteresticId = authCharecteresticId;
    }
    
    public void setTxnId(final String txnId) {
        this.txnId = txnId;
    }
    
    public void setValidationCode(final String validationCode) {
        this.validationCode = validationCode;
    }
    
    public void setMarketSpecAuthDataInd(final String marketSpecAuthDataInd) {
        this.marketSpecAuthDataInd = marketSpecAuthDataInd;
    }
    
    public void setMvv(final String mvv) {
        this.mvv = mvv;
    }
    
    public void setProductId(final String productId) {
        this.productId = productId;
    }
    
    public void setSpendQualificationInd(final String spendQualificationInd) {
        this.spendQualificationInd = spendQualificationInd;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de62Vo)) {
            return false;
        }
        final de62Vo other = (de62Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$authCharecteresticId = this.getAuthCharecteresticId();
        final Object other$authCharecteresticId = other.getAuthCharecteresticId();
        Label_0065: {
            if (this$authCharecteresticId == null) {
                if (other$authCharecteresticId == null) {
                    break Label_0065;
                }
            }
            else if (this$authCharecteresticId.equals(other$authCharecteresticId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$txnId = this.getTxnId();
        final Object other$txnId = other.getTxnId();
        Label_0102: {
            if (this$txnId == null) {
                if (other$txnId == null) {
                    break Label_0102;
                }
            }
            else if (this$txnId.equals(other$txnId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$validationCode = this.getValidationCode();
        final Object other$validationCode = other.getValidationCode();
        Label_0139: {
            if (this$validationCode == null) {
                if (other$validationCode == null) {
                    break Label_0139;
                }
            }
            else if (this$validationCode.equals(other$validationCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$marketSpecAuthDataInd = this.getMarketSpecAuthDataInd();
        final Object other$marketSpecAuthDataInd = other.getMarketSpecAuthDataInd();
        Label_0176: {
            if (this$marketSpecAuthDataInd == null) {
                if (other$marketSpecAuthDataInd == null) {
                    break Label_0176;
                }
            }
            else if (this$marketSpecAuthDataInd.equals(other$marketSpecAuthDataInd)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$mvv = this.getMvv();
        final Object other$mvv = other.getMvv();
        Label_0213: {
            if (this$mvv == null) {
                if (other$mvv == null) {
                    break Label_0213;
                }
            }
            else if (this$mvv.equals(other$mvv)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        Label_0250: {
            if (this$productId == null) {
                if (other$productId == null) {
                    break Label_0250;
                }
            }
            else if (this$productId.equals(other$productId)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$spendQualificationInd = this.getSpendQualificationInd();
        final Object other$spendQualificationInd = other.getSpendQualificationInd();
        if (this$spendQualificationInd == null) {
            if (other$spendQualificationInd == null) {
                return true;
            }
        }
        else if (this$spendQualificationInd.equals(other$spendQualificationInd)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de62Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $authCharecteresticId = this.getAuthCharecteresticId();
        result = result * 59 + (($authCharecteresticId == null) ? 43 : $authCharecteresticId.hashCode());
        final Object $txnId = this.getTxnId();
        result = result * 59 + (($txnId == null) ? 43 : $txnId.hashCode());
        final Object $validationCode = this.getValidationCode();
        result = result * 59 + (($validationCode == null) ? 43 : $validationCode.hashCode());
        final Object $marketSpecAuthDataInd = this.getMarketSpecAuthDataInd();
        result = result * 59 + (($marketSpecAuthDataInd == null) ? 43 : $marketSpecAuthDataInd.hashCode());
        final Object $mvv = this.getMvv();
        result = result * 59 + (($mvv == null) ? 43 : $mvv.hashCode());
        final Object $productId = this.getProductId();
        result = result * 59 + (($productId == null) ? 43 : $productId.hashCode());
        final Object $spendQualificationInd = this.getSpendQualificationInd();
        result = result * 59 + (($spendQualificationInd == null) ? 43 : $spendQualificationInd.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de62Vo(authCharecteresticId=" + this.getAuthCharecteresticId() + ", txnId=" + this.getTxnId() + ", validationCode=" + this.getValidationCode() + ", marketSpecAuthDataInd=" + this.getMarketSpecAuthDataInd() + ", mvv=" + this.getMvv() + ", productId=" + this.getProductId() + ", spendQualificationInd=" + this.getSpendQualificationInd();
    }
}
