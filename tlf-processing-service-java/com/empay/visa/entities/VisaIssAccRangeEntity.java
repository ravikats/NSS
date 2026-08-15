// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.visa.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "VISA_ISS_ACC_RANGE")
public class VisaIssAccRangeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VAR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "VAR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "VAR_UPDATED_USER")
    private int updatedUser;
    @Column(name = "VAR_PRJ_SER_NUMBER")
    private int jobSerialNumber;
    @Column(name = "VAR_ISS_RANGE_LOW")
    private String issRangeLow;
    @Column(name = "VAR_ISS_RANGE_HIGH")
    private String issRangeHigh;
    @Column(name = "VAR_BIN")
    private String bin;
    @Column(name = "VAR_REGION")
    private Character region;
    @Column(name = "VAR_COUNTRY_ALPHA_CODE")
    private String countryAlphaCode;
    @Column(name = "VAR_CARD_PRODUCT")
    private String cardProduct;
    @Column(name = "VAR_DR_CR_CARD_IND")
    private Character crDrIndicator;
    @Column(name = "VAR_PROD_SUB_TYPE")
    private String productSubTytpe;
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public int getUpdatedUser() {
        return this.updatedUser;
    }
    
    public int getJobSerialNumber() {
        return this.jobSerialNumber;
    }
    
    public String getIssRangeLow() {
        return this.issRangeLow;
    }
    
    public String getIssRangeHigh() {
        return this.issRangeHigh;
    }
    
    public String getBin() {
        return this.bin;
    }
    
    public Character getRegion() {
        return this.region;
    }
    
    public String getCountryAlphaCode() {
        return this.countryAlphaCode;
    }
    
    public String getCardProduct() {
        return this.cardProduct;
    }
    
    public Character getCrDrIndicator() {
        return this.crDrIndicator;
    }
    
    public String getProductSubTytpe() {
        return this.productSubTytpe;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final int updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setJobSerialNumber(final int jobSerialNumber) {
        this.jobSerialNumber = jobSerialNumber;
    }
    
    public void setIssRangeLow(final String issRangeLow) {
        this.issRangeLow = issRangeLow;
    }
    
    public void setIssRangeHigh(final String issRangeHigh) {
        this.issRangeHigh = issRangeHigh;
    }
    
    public void setBin(final String bin) {
        this.bin = bin;
    }
    
    public void setRegion(final Character region) {
        this.region = region;
    }
    
    public void setCountryAlphaCode(final String countryAlphaCode) {
        this.countryAlphaCode = countryAlphaCode;
    }
    
    public void setCardProduct(final String cardProduct) {
        this.cardProduct = cardProduct;
    }
    
    public void setCrDrIndicator(final Character crDrIndicator) {
        this.crDrIndicator = crDrIndicator;
    }
    
    public void setProductSubTytpe(final String productSubTytpe) {
        this.productSubTytpe = productSubTytpe;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaIssAccRangeEntity)) {
            return false;
        }
        final VisaIssAccRangeEntity other = (VisaIssAccRangeEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        if (this.getJobSerialNumber() != other.getJobSerialNumber()) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0091: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0091;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0091;
            }
            return false;
        }
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        Label_0128: {
            if (this$region == null) {
                if (other$region == null) {
                    break Label_0128;
                }
            }
            else if (this$region.equals(other$region)) {
                break Label_0128;
            }
            return false;
        }
        final Object this$crDrIndicator = this.getCrDrIndicator();
        final Object other$crDrIndicator = other.getCrDrIndicator();
        Label_0165: {
            if (this$crDrIndicator == null) {
                if (other$crDrIndicator == null) {
                    break Label_0165;
                }
            }
            else if (this$crDrIndicator.equals(other$crDrIndicator)) {
                break Label_0165;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0202: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0202;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0202;
            }
            return false;
        }
        final Object this$issRangeLow = this.getIssRangeLow();
        final Object other$issRangeLow = other.getIssRangeLow();
        Label_0239: {
            if (this$issRangeLow == null) {
                if (other$issRangeLow == null) {
                    break Label_0239;
                }
            }
            else if (this$issRangeLow.equals(other$issRangeLow)) {
                break Label_0239;
            }
            return false;
        }
        final Object this$issRangeHigh = this.getIssRangeHigh();
        final Object other$issRangeHigh = other.getIssRangeHigh();
        Label_0276: {
            if (this$issRangeHigh == null) {
                if (other$issRangeHigh == null) {
                    break Label_0276;
                }
            }
            else if (this$issRangeHigh.equals(other$issRangeHigh)) {
                break Label_0276;
            }
            return false;
        }
        final Object this$bin = this.getBin();
        final Object other$bin = other.getBin();
        Label_0313: {
            if (this$bin == null) {
                if (other$bin == null) {
                    break Label_0313;
                }
            }
            else if (this$bin.equals(other$bin)) {
                break Label_0313;
            }
            return false;
        }
        final Object this$countryAlphaCode = this.getCountryAlphaCode();
        final Object other$countryAlphaCode = other.getCountryAlphaCode();
        Label_0350: {
            if (this$countryAlphaCode == null) {
                if (other$countryAlphaCode == null) {
                    break Label_0350;
                }
            }
            else if (this$countryAlphaCode.equals(other$countryAlphaCode)) {
                break Label_0350;
            }
            return false;
        }
        final Object this$cardProduct = this.getCardProduct();
        final Object other$cardProduct = other.getCardProduct();
        Label_0387: {
            if (this$cardProduct == null) {
                if (other$cardProduct == null) {
                    break Label_0387;
                }
            }
            else if (this$cardProduct.equals(other$cardProduct)) {
                break Label_0387;
            }
            return false;
        }
        final Object this$productSubTytpe = this.getProductSubTytpe();
        final Object other$productSubTytpe = other.getProductSubTytpe();
        if (this$productSubTytpe == null) {
            if (other$productSubTytpe == null) {
                return true;
            }
        }
        else if (this$productSubTytpe.equals(other$productSubTytpe)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof VisaIssAccRangeEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getJobSerialNumber();
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $region = this.getRegion();
        result = result * 59 + (($region == null) ? 43 : $region.hashCode());
        final Object $crDrIndicator = this.getCrDrIndicator();
        result = result * 59 + (($crDrIndicator == null) ? 43 : $crDrIndicator.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $issRangeLow = this.getIssRangeLow();
        result = result * 59 + (($issRangeLow == null) ? 43 : $issRangeLow.hashCode());
        final Object $issRangeHigh = this.getIssRangeHigh();
        result = result * 59 + (($issRangeHigh == null) ? 43 : $issRangeHigh.hashCode());
        final Object $bin = this.getBin();
        result = result * 59 + (($bin == null) ? 43 : $bin.hashCode());
        final Object $countryAlphaCode = this.getCountryAlphaCode();
        result = result * 59 + (($countryAlphaCode == null) ? 43 : $countryAlphaCode.hashCode());
        final Object $cardProduct = this.getCardProduct();
        result = result * 59 + (($cardProduct == null) ? 43 : $cardProduct.hashCode());
        final Object $productSubTytpe = this.getProductSubTytpe();
        result = result * 59 + (($productSubTytpe == null) ? 43 : $productSubTytpe.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "VisaIssAccRangeEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobSerialNumber=" + this.getJobSerialNumber() + ", issRangeLow=" + this.getIssRangeLow() + ", issRangeHigh=" + this.getIssRangeHigh() + ", bin=" + this.getBin() + ", region=" + this.getRegion() + ", countryAlphaCode=" + this.getCountryAlphaCode() + ", cardProduct=" + this.getCardProduct() + ", crDrIndicator=" + this.getCrDrIndicator() + ", productSubTytpe=" + this.getProductSubTytpe();
    }
}
