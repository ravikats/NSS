// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "CURRENCY_RATES")
public class CurrencyRatesEntity
{
    @Id
    @Column(name = "CRT_CODE")
    private Integer crtCode;
    @Column(name = "CRT_RATE")
    private Double currencyRate;
    @Column(name = "CRT_INS_CODE")
    private Integer insCode;
    @Column(name = "CRT_SRC_CURR_CODE")
    private String sourceCurCode;
    
    public static CurrencyRatesEntityBuilder builder() {
        return new CurrencyRatesEntityBuilder();
    }
    
    public Integer getCrtCode() {
        return this.crtCode;
    }
    
    public Double getCurrencyRate() {
        return this.currencyRate;
    }
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public String getSourceCurCode() {
        return this.sourceCurCode;
    }
    
    public void setCrtCode(final Integer crtCode) {
        this.crtCode = crtCode;
    }
    
    public void setCurrencyRate(final Double currencyRate) {
        this.currencyRate = currencyRate;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setSourceCurCode(final String sourceCurCode) {
        this.sourceCurCode = sourceCurCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CurrencyRatesEntity)) {
            return false;
        }
        final CurrencyRatesEntity other = (CurrencyRatesEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$crtCode = this.getCrtCode();
        final Object other$crtCode = other.getCrtCode();
        Label_0065: {
            if (this$crtCode == null) {
                if (other$crtCode == null) {
                    break Label_0065;
                }
            }
            else if (this$crtCode.equals(other$crtCode)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$currencyRate = this.getCurrencyRate();
        final Object other$currencyRate = other.getCurrencyRate();
        Label_0102: {
            if (this$currencyRate == null) {
                if (other$currencyRate == null) {
                    break Label_0102;
                }
            }
            else if (this$currencyRate.equals(other$currencyRate)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0139: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0139;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$sourceCurCode = this.getSourceCurCode();
        final Object other$sourceCurCode = other.getSourceCurCode();
        if (this$sourceCurCode == null) {
            if (other$sourceCurCode == null) {
                return true;
            }
        }
        else if (this$sourceCurCode.equals(other$sourceCurCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CurrencyRatesEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $crtCode = this.getCrtCode();
        result = result * 59 + (($crtCode == null) ? 43 : $crtCode.hashCode());
        final Object $currencyRate = this.getCurrencyRate();
        result = result * 59 + (($currencyRate == null) ? 43 : $currencyRate.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $sourceCurCode = this.getSourceCurCode();
        result = result * 59 + (($sourceCurCode == null) ? 43 : $sourceCurCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CurrencyRatesEntity(crtCode=" + this.getCrtCode() + ", currencyRate=" + this.getCurrencyRate() + ", insCode=" + this.getInsCode() + ", sourceCurCode=" + this.getSourceCurCode();
    }
    
    public CurrencyRatesEntity(final Integer crtCode, final Double currencyRate, final Integer insCode, final String sourceCurCode) {
        this.crtCode = crtCode;
        this.currencyRate = currencyRate;
        this.insCode = insCode;
        this.sourceCurCode = sourceCurCode;
    }
    
    public CurrencyRatesEntity() {
    }
    
    public static class CurrencyRatesEntityBuilder
    {
        private Integer crtCode;
        private Double currencyRate;
        private Integer insCode;
        private String sourceCurCode;
        
        CurrencyRatesEntityBuilder() {
        }
        
        public CurrencyRatesEntityBuilder crtCode(final Integer crtCode) {
            this.crtCode = crtCode;
            return this;
        }
        
        public CurrencyRatesEntityBuilder currencyRate(final Double currencyRate) {
            this.currencyRate = currencyRate;
            return this;
        }
        
        public CurrencyRatesEntityBuilder insCode(final Integer insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public CurrencyRatesEntityBuilder sourceCurCode(final String sourceCurCode) {
            this.sourceCurCode = sourceCurCode;
            return this;
        }
        
        public CurrencyRatesEntity build() {
            return new CurrencyRatesEntity(this.crtCode, this.currencyRate, this.insCode, this.sourceCurCode);
        }
        
        @Override
        public String toString() {
            return "CurrencyRatesEntity.CurrencyRatesEntityBuilder(crtCode=" + this.crtCode + ", currencyRate=" + this.currencyRate + ", insCode=" + this.insCode + ", sourceCurCode=" + this.sourceCurCode;
        }
    }
}
