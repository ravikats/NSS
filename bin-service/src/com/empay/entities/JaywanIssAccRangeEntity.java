/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.JaywanIssAccRangeEntity
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
@Table(name="JAYWAN_ISS_ACC_RANGE")
public class JaywanIssAccRangeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="JBS_SER_NUMBER")
    private int serialNumber;
    @Column(name="JBS_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="JBS_UPDATED_USER")
    private int updatedUser;
    @Column(name="JBS_PRJ_SER_NUMBER")
    private int jobNumber;
    @Column(name="JBS_ISSUER_BANK")
    private String issuerBank;
    @Column(name="JBS_INS_ID")
    private Integer institutionId;
    @Column(name="JBS_BIN_LOW_VALUE")
    private Long binRangeLow;
    @Column(name="JBS_BIN_HIGH_VALUE")
    private Long binRangeHigh;
    @Column(name="JBS_PAN_LENGTH")
    private Integer panLength;
    @Column(name="JBS_BIN_LENGTH")
    private Integer binLength;
    @Column(name="JBS_PRODUCT_TYPE")
    private Character productType;
    @Column(name="JBS_SCHEME_CODE")
    private Character schemeCode;
    @Column(name="JBS_SCHEME_PRODUCT")
    private String schemeProduct;
    @Column(name="JBS_CARD_TYPE")
    private Integer cardType;
    @Column(name="JBS_SERVICE")
    private Integer service;
    @Column(name="JBS_CUR_CODE")
    private Integer currencyCode;
    @Column(name="JBS_ISO_NUM_CUR_CODE")
    private Integer isoNumCurrCode;
    @Column(name="JBS_ACTION_TAKEN")
    private Character actionTaken;
    @Column(name="JBS_ISS_ACC_CAP")
    private Character issAccCap;
    @Column(name="JBS_PROD_CLSSFY")
    private Character prodClssfy;
    @Column(name="JBS_CO_BADGE_IND")
    private String badgeInd;

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public int getUpdatedUser() {
        return this.updatedUser;
    }

    public int getJobNumber() {
        return this.jobNumber;
    }

    public String getIssuerBank() {
        return this.issuerBank;
    }

    public Integer getInstitutionId() {
        return this.institutionId;
    }

    public Long getBinRangeLow() {
        return this.binRangeLow;
    }

    public Long getBinRangeHigh() {
        return this.binRangeHigh;
    }

    public Integer getPanLength() {
        return this.panLength;
    }

    public Integer getBinLength() {
        return this.binLength;
    }

    public Character getProductType() {
        return this.productType;
    }

    public Character getSchemeCode() {
        return this.schemeCode;
    }

    public String getSchemeProduct() {
        return this.schemeProduct;
    }

    public Integer getCardType() {
        return this.cardType;
    }

    public Integer getService() {
        return this.service;
    }

    public Integer getCurrencyCode() {
        return this.currencyCode;
    }

    public Integer getIsoNumCurrCode() {
        return this.isoNumCurrCode;
    }

    public Character getActionTaken() {
        return this.actionTaken;
    }

    public Character getIssAccCap() {
        return this.issAccCap;
    }

    public Character getProdClssfy() {
        return this.prodClssfy;
    }

    public String getBadgeInd() {
        return this.badgeInd;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setIssuerBank(String issuerBank) {
        this.issuerBank = issuerBank;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public void setBinRangeLow(Long binRangeLow) {
        this.binRangeLow = binRangeLow;
    }

    public void setBinRangeHigh(Long binRangeHigh) {
        this.binRangeHigh = binRangeHigh;
    }

    public void setPanLength(Integer panLength) {
        this.panLength = panLength;
    }

    public void setBinLength(Integer binLength) {
        this.binLength = binLength;
    }

    public void setProductType(Character productType) {
        this.productType = productType;
    }

    public void setSchemeCode(Character schemeCode) {
        this.schemeCode = schemeCode;
    }

    public void setSchemeProduct(String schemeProduct) {
        this.schemeProduct = schemeProduct;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setIsoNumCurrCode(Integer isoNumCurrCode) {
        this.isoNumCurrCode = isoNumCurrCode;
    }

    public void setActionTaken(Character actionTaken) {
        this.actionTaken = actionTaken;
    }

    public void setIssAccCap(Character issAccCap) {
        this.issAccCap = issAccCap;
    }

    public void setProdClssfy(Character prodClssfy) {
        this.prodClssfy = prodClssfy;
    }

    public void setBadgeInd(String badgeInd) {
        this.badgeInd = badgeInd;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanIssAccRangeEntity)) {
            return false;
        }
        JaywanIssAccRangeEntity other = (JaywanIssAccRangeEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSerialNumber() != other.getSerialNumber()) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        if (this.getJobNumber() != other.getJobNumber()) {
            return false;
        }
        Integer this$institutionId = this.getInstitutionId();
        Integer other$institutionId = other.getInstitutionId();
        if (this$institutionId == null ? other$institutionId != null : !((Object)this$institutionId).equals(other$institutionId)) {
            return false;
        }
        Long this$binRangeLow = this.getBinRangeLow();
        Long other$binRangeLow = other.getBinRangeLow();
        if (this$binRangeLow == null ? other$binRangeLow != null : !((Object)this$binRangeLow).equals(other$binRangeLow)) {
            return false;
        }
        Long this$binRangeHigh = this.getBinRangeHigh();
        Long other$binRangeHigh = other.getBinRangeHigh();
        if (this$binRangeHigh == null ? other$binRangeHigh != null : !((Object)this$binRangeHigh).equals(other$binRangeHigh)) {
            return false;
        }
        Integer this$panLength = this.getPanLength();
        Integer other$panLength = other.getPanLength();
        if (this$panLength == null ? other$panLength != null : !((Object)this$panLength).equals(other$panLength)) {
            return false;
        }
        Integer this$binLength = this.getBinLength();
        Integer other$binLength = other.getBinLength();
        if (this$binLength == null ? other$binLength != null : !((Object)this$binLength).equals(other$binLength)) {
            return false;
        }
        Character this$productType = this.getProductType();
        Character other$productType = other.getProductType();
        if (this$productType == null ? other$productType != null : !((Object)this$productType).equals(other$productType)) {
            return false;
        }
        Character this$schemeCode = this.getSchemeCode();
        Character other$schemeCode = other.getSchemeCode();
        if (this$schemeCode == null ? other$schemeCode != null : !((Object)this$schemeCode).equals(other$schemeCode)) {
            return false;
        }
        Integer this$cardType = this.getCardType();
        Integer other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Integer this$service = this.getService();
        Integer other$service = other.getService();
        if (this$service == null ? other$service != null : !((Object)this$service).equals(other$service)) {
            return false;
        }
        Integer this$currencyCode = this.getCurrencyCode();
        Integer other$currencyCode = other.getCurrencyCode();
        if (this$currencyCode == null ? other$currencyCode != null : !((Object)this$currencyCode).equals(other$currencyCode)) {
            return false;
        }
        Integer this$isoNumCurrCode = this.getIsoNumCurrCode();
        Integer other$isoNumCurrCode = other.getIsoNumCurrCode();
        if (this$isoNumCurrCode == null ? other$isoNumCurrCode != null : !((Object)this$isoNumCurrCode).equals(other$isoNumCurrCode)) {
            return false;
        }
        Character this$actionTaken = this.getActionTaken();
        Character other$actionTaken = other.getActionTaken();
        if (this$actionTaken == null ? other$actionTaken != null : !((Object)this$actionTaken).equals(other$actionTaken)) {
            return false;
        }
        Character this$issAccCap = this.getIssAccCap();
        Character other$issAccCap = other.getIssAccCap();
        if (this$issAccCap == null ? other$issAccCap != null : !((Object)this$issAccCap).equals(other$issAccCap)) {
            return false;
        }
        Character this$prodClssfy = this.getProdClssfy();
        Character other$prodClssfy = other.getProdClssfy();
        if (this$prodClssfy == null ? other$prodClssfy != null : !((Object)this$prodClssfy).equals(other$prodClssfy)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$issuerBank = this.getIssuerBank();
        String other$issuerBank = other.getIssuerBank();
        if (this$issuerBank == null ? other$issuerBank != null : !this$issuerBank.equals(other$issuerBank)) {
            return false;
        }
        String this$schemeProduct = this.getSchemeProduct();
        String other$schemeProduct = other.getSchemeProduct();
        if (this$schemeProduct == null ? other$schemeProduct != null : !this$schemeProduct.equals(other$schemeProduct)) {
            return false;
        }
        String this$badgeInd = this.getBadgeInd();
        String other$badgeInd = other.getBadgeInd();
        return !(this$badgeInd == null ? other$badgeInd != null : !this$badgeInd.equals(other$badgeInd));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanIssAccRangeEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getJobNumber();
        Integer $institutionId = this.getInstitutionId();
        result = result * 59 + ($institutionId == null ? 43 : ((Object)$institutionId).hashCode());
        Long $binRangeLow = this.getBinRangeLow();
        result = result * 59 + ($binRangeLow == null ? 43 : ((Object)$binRangeLow).hashCode());
        Long $binRangeHigh = this.getBinRangeHigh();
        result = result * 59 + ($binRangeHigh == null ? 43 : ((Object)$binRangeHigh).hashCode());
        Integer $panLength = this.getPanLength();
        result = result * 59 + ($panLength == null ? 43 : ((Object)$panLength).hashCode());
        Integer $binLength = this.getBinLength();
        result = result * 59 + ($binLength == null ? 43 : ((Object)$binLength).hashCode());
        Character $productType = this.getProductType();
        result = result * 59 + ($productType == null ? 43 : ((Object)$productType).hashCode());
        Character $schemeCode = this.getSchemeCode();
        result = result * 59 + ($schemeCode == null ? 43 : ((Object)$schemeCode).hashCode());
        Integer $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Integer $service = this.getService();
        result = result * 59 + ($service == null ? 43 : ((Object)$service).hashCode());
        Integer $currencyCode = this.getCurrencyCode();
        result = result * 59 + ($currencyCode == null ? 43 : ((Object)$currencyCode).hashCode());
        Integer $isoNumCurrCode = this.getIsoNumCurrCode();
        result = result * 59 + ($isoNumCurrCode == null ? 43 : ((Object)$isoNumCurrCode).hashCode());
        Character $actionTaken = this.getActionTaken();
        result = result * 59 + ($actionTaken == null ? 43 : ((Object)$actionTaken).hashCode());
        Character $issAccCap = this.getIssAccCap();
        result = result * 59 + ($issAccCap == null ? 43 : ((Object)$issAccCap).hashCode());
        Character $prodClssfy = this.getProdClssfy();
        result = result * 59 + ($prodClssfy == null ? 43 : ((Object)$prodClssfy).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $issuerBank = this.getIssuerBank();
        result = result * 59 + ($issuerBank == null ? 43 : $issuerBank.hashCode());
        String $schemeProduct = this.getSchemeProduct();
        result = result * 59 + ($schemeProduct == null ? 43 : $schemeProduct.hashCode());
        String $badgeInd = this.getBadgeInd();
        result = result * 59 + ($badgeInd == null ? 43 : $badgeInd.hashCode());
        return result;
    }

    public String toString() {
        return "JaywanIssAccRangeEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobNumber=" + this.getJobNumber() + ", issuerBank=" + this.getIssuerBank() + ", institutionId=" + this.getInstitutionId() + ", binRangeLow=" + this.getBinRangeLow() + ", binRangeHigh=" + this.getBinRangeHigh() + ", panLength=" + this.getPanLength() + ", binLength=" + this.getBinLength() + ", productType=" + this.getProductType() + ", schemeCode=" + this.getSchemeCode() + ", schemeProduct=" + this.getSchemeProduct() + ", cardType=" + this.getCardType() + ", service=" + this.getService() + ", currencyCode=" + this.getCurrencyCode() + ", isoNumCurrCode=" + this.getIsoNumCurrCode() + ", actionTaken=" + this.getActionTaken() + ", issAccCap=" + this.getIssAccCap() + ", prodClssfy=" + this.getProdClssfy() + ", badgeInd=" + this.getBadgeInd() + ")";
    }
}

