/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.VisaIssAcqRangeEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="VISA_ISS_ACC_RANGE")
public class VisaIssAcqRangeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="VAR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="VAR_LAST_UPDATED")
    private LocalDateTime updatedDate;
    @Column(name="VAR_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="VAR_PRJ_SER_NUMBER")
    private Integer jobSerialNumber;
    @Column(name="VAR_ISS_RANGE_LOW")
    private String issAccountRangeLow;
    @Column(name="VAR_ISS_RANGE_HIGH")
    private String issAccountRangeHigh;
    @Column(name="VAR_BIN")
    private String bin;
    @Column(name="VAR_BIN_LENGTH")
    private Integer binLength;
    @Column(name="VAR_PROC_BIN")
    private String processingBin;
    @Column(name="VAR_DOMAIN")
    private Character domain;
    @Column(name="VAR_REGION")
    private Character region;
    @Column(name="VAR_COUNTRY_ALPHA_CODE")
    private String countryAlphaCode;
    @Column(name="VAR_CARD_PRODUCT")
    private String cardProduct;
    @Column(name="VAR_DR_CR_CARD_IND")
    private Character crdrIndicator;
    @Column(name="VAR_PROD_SUB_TYPE")
    private String productSubTytpe;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getJobSerialNumber() {
        return this.jobSerialNumber;
    }

    public String getIssAccountRangeLow() {
        return this.issAccountRangeLow;
    }

    public String getIssAccountRangeHigh() {
        return this.issAccountRangeHigh;
    }

    public String getBin() {
        return this.bin;
    }

    public Integer getBinLength() {
        return this.binLength;
    }

    public String getProcessingBin() {
        return this.processingBin;
    }

    public Character getDomain() {
        return this.domain;
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

    public Character getCrdrIndicator() {
        return this.crdrIndicator;
    }

    public String getProductSubTytpe() {
        return this.productSubTytpe;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setJobSerialNumber(Integer jobSerialNumber) {
        this.jobSerialNumber = jobSerialNumber;
    }

    public void setIssAccountRangeLow(String issAccountRangeLow) {
        this.issAccountRangeLow = issAccountRangeLow;
    }

    public void setIssAccountRangeHigh(String issAccountRangeHigh) {
        this.issAccountRangeHigh = issAccountRangeHigh;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public void setBinLength(Integer binLength) {
        this.binLength = binLength;
    }

    public void setProcessingBin(String processingBin) {
        this.processingBin = processingBin;
    }

    public void setDomain(Character domain) {
        this.domain = domain;
    }

    public void setRegion(Character region) {
        this.region = region;
    }

    public void setCountryAlphaCode(String countryAlphaCode) {
        this.countryAlphaCode = countryAlphaCode;
    }

    public void setCardProduct(String cardProduct) {
        this.cardProduct = cardProduct;
    }

    public void setCrdrIndicator(Character crdrIndicator) {
        this.crdrIndicator = crdrIndicator;
    }

    public void setProductSubTytpe(String productSubTytpe) {
        this.productSubTytpe = productSubTytpe;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaIssAcqRangeEntity)) {
            return false;
        }
        VisaIssAcqRangeEntity other = (VisaIssAcqRangeEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Integer this$jobSerialNumber = this.getJobSerialNumber();
        Integer other$jobSerialNumber = other.getJobSerialNumber();
        if (this$jobSerialNumber == null ? other$jobSerialNumber != null : !((Object)this$jobSerialNumber).equals(other$jobSerialNumber)) {
            return false;
        }
        Integer this$binLength = this.getBinLength();
        Integer other$binLength = other.getBinLength();
        if (this$binLength == null ? other$binLength != null : !((Object)this$binLength).equals(other$binLength)) {
            return false;
        }
        Character this$domain = this.getDomain();
        Character other$domain = other.getDomain();
        if (this$domain == null ? other$domain != null : !((Object)this$domain).equals(other$domain)) {
            return false;
        }
        Character this$region = this.getRegion();
        Character other$region = other.getRegion();
        if (this$region == null ? other$region != null : !((Object)this$region).equals(other$region)) {
            return false;
        }
        Character this$crdrIndicator = this.getCrdrIndicator();
        Character other$crdrIndicator = other.getCrdrIndicator();
        if (this$crdrIndicator == null ? other$crdrIndicator != null : !((Object)this$crdrIndicator).equals(other$crdrIndicator)) {
            return false;
        }
        LocalDateTime this$updatedDate = this.getUpdatedDate();
        LocalDateTime other$updatedDate = other.getUpdatedDate();
        if (this$updatedDate == null ? other$updatedDate != null : !((Object)this$updatedDate).equals(other$updatedDate)) {
            return false;
        }
        String this$issAccountRangeLow = this.getIssAccountRangeLow();
        String other$issAccountRangeLow = other.getIssAccountRangeLow();
        if (this$issAccountRangeLow == null ? other$issAccountRangeLow != null : !this$issAccountRangeLow.equals(other$issAccountRangeLow)) {
            return false;
        }
        String this$issAccountRangeHigh = this.getIssAccountRangeHigh();
        String other$issAccountRangeHigh = other.getIssAccountRangeHigh();
        if (this$issAccountRangeHigh == null ? other$issAccountRangeHigh != null : !this$issAccountRangeHigh.equals(other$issAccountRangeHigh)) {
            return false;
        }
        String this$bin = this.getBin();
        String other$bin = other.getBin();
        if (this$bin == null ? other$bin != null : !this$bin.equals(other$bin)) {
            return false;
        }
        String this$processingBin = this.getProcessingBin();
        String other$processingBin = other.getProcessingBin();
        if (this$processingBin == null ? other$processingBin != null : !this$processingBin.equals(other$processingBin)) {
            return false;
        }
        String this$countryAlphaCode = this.getCountryAlphaCode();
        String other$countryAlphaCode = other.getCountryAlphaCode();
        if (this$countryAlphaCode == null ? other$countryAlphaCode != null : !this$countryAlphaCode.equals(other$countryAlphaCode)) {
            return false;
        }
        String this$cardProduct = this.getCardProduct();
        String other$cardProduct = other.getCardProduct();
        if (this$cardProduct == null ? other$cardProduct != null : !this$cardProduct.equals(other$cardProduct)) {
            return false;
        }
        String this$productSubTytpe = this.getProductSubTytpe();
        String other$productSubTytpe = other.getProductSubTytpe();
        return !(this$productSubTytpe == null ? other$productSubTytpe != null : !this$productSubTytpe.equals(other$productSubTytpe));
    }

    protected boolean canEqual(Object other) {
        return other instanceof VisaIssAcqRangeEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $jobSerialNumber = this.getJobSerialNumber();
        result = result * 59 + ($jobSerialNumber == null ? 43 : ((Object)$jobSerialNumber).hashCode());
        Integer $binLength = this.getBinLength();
        result = result * 59 + ($binLength == null ? 43 : ((Object)$binLength).hashCode());
        Character $domain = this.getDomain();
        result = result * 59 + ($domain == null ? 43 : ((Object)$domain).hashCode());
        Character $region = this.getRegion();
        result = result * 59 + ($region == null ? 43 : ((Object)$region).hashCode());
        Character $crdrIndicator = this.getCrdrIndicator();
        result = result * 59 + ($crdrIndicator == null ? 43 : ((Object)$crdrIndicator).hashCode());
        LocalDateTime $updatedDate = this.getUpdatedDate();
        result = result * 59 + ($updatedDate == null ? 43 : ((Object)$updatedDate).hashCode());
        String $issAccountRangeLow = this.getIssAccountRangeLow();
        result = result * 59 + ($issAccountRangeLow == null ? 43 : $issAccountRangeLow.hashCode());
        String $issAccountRangeHigh = this.getIssAccountRangeHigh();
        result = result * 59 + ($issAccountRangeHigh == null ? 43 : $issAccountRangeHigh.hashCode());
        String $bin = this.getBin();
        result = result * 59 + ($bin == null ? 43 : $bin.hashCode());
        String $processingBin = this.getProcessingBin();
        result = result * 59 + ($processingBin == null ? 43 : $processingBin.hashCode());
        String $countryAlphaCode = this.getCountryAlphaCode();
        result = result * 59 + ($countryAlphaCode == null ? 43 : $countryAlphaCode.hashCode());
        String $cardProduct = this.getCardProduct();
        result = result * 59 + ($cardProduct == null ? 43 : $cardProduct.hashCode());
        String $productSubTytpe = this.getProductSubTytpe();
        result = result * 59 + ($productSubTytpe == null ? 43 : $productSubTytpe.hashCode());
        return result;
    }

    public String toString() {
        return "VisaIssAcqRangeEntity(serialNumber=" + this.getSerialNumber() + ", updatedDate=" + String.valueOf(this.getUpdatedDate()) + ", updatedUser=" + this.getUpdatedUser() + ", jobSerialNumber=" + this.getJobSerialNumber() + ", issAccountRangeLow=" + this.getIssAccountRangeLow() + ", issAccountRangeHigh=" + this.getIssAccountRangeHigh() + ", bin=" + this.getBin() + ", binLength=" + this.getBinLength() + ", processingBin=" + this.getProcessingBin() + ", domain=" + this.getDomain() + ", region=" + this.getRegion() + ", countryAlphaCode=" + this.getCountryAlphaCode() + ", cardProduct=" + this.getCardProduct() + ", crdrIndicator=" + this.getCrdrIndicator() + ", productSubTytpe=" + this.getProductSubTytpe() + ")";
    }
}

