// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "COUNTRIES")
public class CountriesEntity
{
    @Id
    @Column(name = "CON_CODE")
    private String countryCode;
    @Column(name = "CON_ALPHA3_CODE")
    private String countryAlpha3Code;
    @Column(name = "CON_ALPHA2_CODE")
    private String countryAlpha2Code;
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public String getCountryAlpha3Code() {
        return this.countryAlpha3Code;
    }
    
    public String getCountryAlpha2Code() {
        return this.countryAlpha2Code;
    }
    
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
    
    public void setCountryAlpha3Code(final String countryAlpha3Code) {
        this.countryAlpha3Code = countryAlpha3Code;
    }
    
    public void setCountryAlpha2Code(final String countryAlpha2Code) {
        this.countryAlpha2Code = countryAlpha2Code;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CountriesEntity)) {
            return false;
        }
        final CountriesEntity other = (CountriesEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$countryCode = this.getCountryCode();
        final Object other$countryCode = other.getCountryCode();
        Label_0065: {
            if (this$countryCode == null) {
                if (other$countryCode == null) {
                    break Label_0065;
                }
            }
            else if (this$countryCode.equals(other$countryCode)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$countryAlpha3Code = this.getCountryAlpha3Code();
        final Object other$countryAlpha3Code = other.getCountryAlpha3Code();
        Label_0102: {
            if (this$countryAlpha3Code == null) {
                if (other$countryAlpha3Code == null) {
                    break Label_0102;
                }
            }
            else if (this$countryAlpha3Code.equals(other$countryAlpha3Code)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$countryAlpha2Code = this.getCountryAlpha2Code();
        final Object other$countryAlpha2Code = other.getCountryAlpha2Code();
        if (this$countryAlpha2Code == null) {
            if (other$countryAlpha2Code == null) {
                return true;
            }
        }
        else if (this$countryAlpha2Code.equals(other$countryAlpha2Code)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CountriesEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $countryCode = this.getCountryCode();
        result = result * 59 + (($countryCode == null) ? 43 : $countryCode.hashCode());
        final Object $countryAlpha3Code = this.getCountryAlpha3Code();
        result = result * 59 + (($countryAlpha3Code == null) ? 43 : $countryAlpha3Code.hashCode());
        final Object $countryAlpha2Code = this.getCountryAlpha2Code();
        result = result * 59 + (($countryAlpha2Code == null) ? 43 : $countryAlpha2Code.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CountriesEntity(countryCode=" + this.getCountryCode() + ", countryAlpha3Code=" + this.getCountryAlpha3Code() + ", countryAlpha2Code=" + this.getCountryAlpha2Code();
    }
}

