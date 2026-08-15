// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "CURRENCIES")
public class CurrencyEntity
{
    @Id
    @Column(name = "CUR_CODE")
    private String currencyCode;
    @Column(name = "CUR_NAME")
    private String currencyName;
    @Column(name = "CUR_ALPHA_CODE")
    private String alphaCode;
    @Column(name = "CUR_EXPONENT")
    private Integer curExponent;
    @Column(name = "CUR_UPDATED_USER")
    private Integer user;
    
    public static CurrencyEntityBuilder builder() {
        return new CurrencyEntityBuilder();
    }
    
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    
    public String getCurrencyName() {
        return this.currencyName;
    }
    
    public String getAlphaCode() {
        return this.alphaCode;
    }
    
    public Integer getCurExponent() {
        return this.curExponent;
    }
    
    public Integer getUser() {
        return this.user;
    }
    
    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    public void setCurrencyName(final String currencyName) {
        this.currencyName = currencyName;
    }
    
    public void setAlphaCode(final String alphaCode) {
        this.alphaCode = alphaCode;
    }
    
    public void setCurExponent(final Integer curExponent) {
        this.curExponent = curExponent;
    }
    
    public void setUser(final Integer user) {
        this.user = user;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CurrencyEntity)) {
            return false;
        }
        final CurrencyEntity other = (CurrencyEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$curExponent = this.getCurExponent();
        final Object other$curExponent = other.getCurExponent();
        Label_0065: {
            if (this$curExponent == null) {
                if (other$curExponent == null) {
                    break Label_0065;
                }
            }
            else if (this$curExponent.equals(other$curExponent)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        Label_0102: {
            if (this$user == null) {
                if (other$user == null) {
                    break Label_0102;
                }
            }
            else if (this$user.equals(other$user)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$currencyCode = this.getCurrencyCode();
        final Object other$currencyCode = other.getCurrencyCode();
        Label_0139: {
            if (this$currencyCode == null) {
                if (other$currencyCode == null) {
                    break Label_0139;
                }
            }
            else if (this$currencyCode.equals(other$currencyCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$currencyName = this.getCurrencyName();
        final Object other$currencyName = other.getCurrencyName();
        Label_0176: {
            if (this$currencyName == null) {
                if (other$currencyName == null) {
                    break Label_0176;
                }
            }
            else if (this$currencyName.equals(other$currencyName)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$alphaCode = this.getAlphaCode();
        final Object other$alphaCode = other.getAlphaCode();
        if (this$alphaCode == null) {
            if (other$alphaCode == null) {
                return true;
            }
        }
        else if (this$alphaCode.equals(other$alphaCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CurrencyEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $curExponent = this.getCurExponent();
        result = result * 59 + (($curExponent == null) ? 43 : $curExponent.hashCode());
        final Object $user = this.getUser();
        result = result * 59 + (($user == null) ? 43 : $user.hashCode());
        final Object $currencyCode = this.getCurrencyCode();
        result = result * 59 + (($currencyCode == null) ? 43 : $currencyCode.hashCode());
        final Object $currencyName = this.getCurrencyName();
        result = result * 59 + (($currencyName == null) ? 43 : $currencyName.hashCode());
        final Object $alphaCode = this.getAlphaCode();
        result = result * 59 + (($alphaCode == null) ? 43 : $alphaCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CurrencyEntity(currencyCode=" + this.getCurrencyCode() + ", currencyName=" + this.getCurrencyName() + ", alphaCode=" + this.getAlphaCode() + ", curExponent=" + this.getCurExponent() + ", user=" + this.getUser();
    }
    
    public CurrencyEntity() {
    }
    
    public CurrencyEntity(final String currencyCode, final String currencyName, final String alphaCode, final Integer curExponent, final Integer user) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.alphaCode = alphaCode;
        this.curExponent = curExponent;
        this.user = user;
    }
    
    public static class CurrencyEntityBuilder
    {
        private String currencyCode;
        private String currencyName;
        private String alphaCode;
        private Integer curExponent;
        private Integer user;
        
        CurrencyEntityBuilder() {
        }
        
        public CurrencyEntityBuilder currencyCode(final String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }
        
        public CurrencyEntityBuilder currencyName(final String currencyName) {
            this.currencyName = currencyName;
            return this;
        }
        
        public CurrencyEntityBuilder alphaCode(final String alphaCode) {
            this.alphaCode = alphaCode;
            return this;
        }
        
        public CurrencyEntityBuilder curExponent(final Integer curExponent) {
            this.curExponent = curExponent;
            return this;
        }
        
        public CurrencyEntityBuilder user(final Integer user) {
            this.user = user;
            return this;
        }
        
        public CurrencyEntity build() {
            return new CurrencyEntity(this.currencyCode, this.currencyName, this.alphaCode, this.curExponent, this.user);
        }
        
        @Override
        public String toString() {
            return "CurrencyEntity.CurrencyEntityBuilder(currencyCode=" + this.currencyCode + ", currencyName=" + this.currencyName + ", alphaCode=" + this.alphaCode + ", curExponent=" + this.curExponent + ", user=" + this.user;
        }
    }
}
