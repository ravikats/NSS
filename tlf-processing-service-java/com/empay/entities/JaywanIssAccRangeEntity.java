// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "JAYWAN_ISS_ACC_RANGE")
public class JaywanIssAccRangeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JBS_SER_NUMBER")
    private int serialNumber;
    @Column(name = "JBS_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "JBS_UPDATED_USER")
    private int updatedUser;
    @Column(name = "JBS_PRJ_SER_NUMBER")
    private int jobNumber;
    @Column(name = "JBS_ISSUER_BANK")
    private String issuerBank;
    @Column(name = "JBS_INS_ID")
    private Integer institutionId;
    @Column(name = "JBS_BIN_LOW_VALUE")
    private Long binRangeLow;
    @Column(name = "JBS_BIN_HIGH_VALUE")
    private Long binRangeHigh;
    @Column(name = "JBS_PAN_LENGTH")
    private Integer panLength;
    @Column(name = "JBS_BIN_LENGTH")
    private Integer binLength;
    @Column(name = "JBS_PRODUCT_TYPE")
    private Character productType;
    @Column(name = "JBS_SCHEME_CODE")
    private Character schemeCode;
    @Column(name = "JBS_SCHEME_PRODUCT")
    private String schemeProduct;
    @Column(name = "JBS_CARD_TYPE")
    private Integer cardType;
    @Column(name = "JBS_SERVICE")
    private Integer service;
    @Column(name = "JBS_CUR_CODE")
    private Integer currencyCode;
    @Column(name = "JBS_ISO_NUM_CUR_CODE")
    private Integer isoNumCurrCode;
    @Column(name = "JBS_ACTION_TAKEN")
    private Character actionTaken;
    @Column(name = "JBS_ISS_ACC_CAP")
    private Character issAccCap;
    @Column(name = "JBS_PROD_CLSSFY")
    private Character prodClssfy;
    @Column(name = "JBS_CO_BADGE_IND")
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
    
    public void setSerialNumber(final int serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final int updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setJobNumber(final int jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setIssuerBank(final String issuerBank) {
        this.issuerBank = issuerBank;
    }
    
    public void setInstitutionId(final Integer institutionId) {
        this.institutionId = institutionId;
    }
    
    public void setBinRangeLow(final Long binRangeLow) {
        this.binRangeLow = binRangeLow;
    }
    
    public void setBinRangeHigh(final Long binRangeHigh) {
        this.binRangeHigh = binRangeHigh;
    }
    
    public void setPanLength(final Integer panLength) {
        this.panLength = panLength;
    }
    
    public void setBinLength(final Integer binLength) {
        this.binLength = binLength;
    }
    
    public void setProductType(final Character productType) {
        this.productType = productType;
    }
    
    public void setSchemeCode(final Character schemeCode) {
        this.schemeCode = schemeCode;
    }
    
    public void setSchemeProduct(final String schemeProduct) {
        this.schemeProduct = schemeProduct;
    }
    
    public void setCardType(final Integer cardType) {
        this.cardType = cardType;
    }
    
    public void setService(final Integer service) {
        this.service = service;
    }
    
    public void setCurrencyCode(final Integer currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    public void setIsoNumCurrCode(final Integer isoNumCurrCode) {
        this.isoNumCurrCode = isoNumCurrCode;
    }
    
    public void setActionTaken(final Character actionTaken) {
        this.actionTaken = actionTaken;
    }
    
    public void setIssAccCap(final Character issAccCap) {
        this.issAccCap = issAccCap;
    }
    
    public void setProdClssfy(final Character prodClssfy) {
        this.prodClssfy = prodClssfy;
    }
    
    public void setBadgeInd(final String badgeInd) {
        this.badgeInd = badgeInd;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanIssAccRangeEntity)) {
            return false;
        }
        final JaywanIssAccRangeEntity other = (JaywanIssAccRangeEntity)o;
        if (!other.canEqual(this)) {
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
        final Object this$institutionId = this.getInstitutionId();
        final Object other$institutionId = other.getInstitutionId();
        Label_0104: {
            if (this$institutionId == null) {
                if (other$institutionId == null) {
                    break Label_0104;
                }
            }
            else if (this$institutionId.equals(other$institutionId)) {
                break Label_0104;
            }
            return false;
        }
        final Object this$binRangeLow = this.getBinRangeLow();
        final Object other$binRangeLow = other.getBinRangeLow();
        Label_0141: {
            if (this$binRangeLow == null) {
                if (other$binRangeLow == null) {
                    break Label_0141;
                }
            }
            else if (this$binRangeLow.equals(other$binRangeLow)) {
                break Label_0141;
            }
            return false;
        }
        final Object this$binRangeHigh = this.getBinRangeHigh();
        final Object other$binRangeHigh = other.getBinRangeHigh();
        Label_0178: {
            if (this$binRangeHigh == null) {
                if (other$binRangeHigh == null) {
                    break Label_0178;
                }
            }
            else if (this$binRangeHigh.equals(other$binRangeHigh)) {
                break Label_0178;
            }
            return false;
        }
        final Object this$panLength = this.getPanLength();
        final Object other$panLength = other.getPanLength();
        Label_0215: {
            if (this$panLength == null) {
                if (other$panLength == null) {
                    break Label_0215;
                }
            }
            else if (this$panLength.equals(other$panLength)) {
                break Label_0215;
            }
            return false;
        }
        final Object this$binLength = this.getBinLength();
        final Object other$binLength = other.getBinLength();
        Label_0252: {
            if (this$binLength == null) {
                if (other$binLength == null) {
                    break Label_0252;
                }
            }
            else if (this$binLength.equals(other$binLength)) {
                break Label_0252;
            }
            return false;
        }
        final Object this$productType = this.getProductType();
        final Object other$productType = other.getProductType();
        Label_0289: {
            if (this$productType == null) {
                if (other$productType == null) {
                    break Label_0289;
                }
            }
            else if (this$productType.equals(other$productType)) {
                break Label_0289;
            }
            return false;
        }
        final Object this$schemeCode = this.getSchemeCode();
        final Object other$schemeCode = other.getSchemeCode();
        Label_0326: {
            if (this$schemeCode == null) {
                if (other$schemeCode == null) {
                    break Label_0326;
                }
            }
            else if (this$schemeCode.equals(other$schemeCode)) {
                break Label_0326;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0363: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0363;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0363;
            }
            return false;
        }
        final Object this$service = this.getService();
        final Object other$service = other.getService();
        Label_0400: {
            if (this$service == null) {
                if (other$service == null) {
                    break Label_0400;
                }
            }
            else if (this$service.equals(other$service)) {
                break Label_0400;
            }
            return false;
        }
        final Object this$currencyCode = this.getCurrencyCode();
        final Object other$currencyCode = other.getCurrencyCode();
        Label_0437: {
            if (this$currencyCode == null) {
                if (other$currencyCode == null) {
                    break Label_0437;
                }
            }
            else if (this$currencyCode.equals(other$currencyCode)) {
                break Label_0437;
            }
            return false;
        }
        final Object this$isoNumCurrCode = this.getIsoNumCurrCode();
        final Object other$isoNumCurrCode = other.getIsoNumCurrCode();
        Label_0474: {
            if (this$isoNumCurrCode == null) {
                if (other$isoNumCurrCode == null) {
                    break Label_0474;
                }
            }
            else if (this$isoNumCurrCode.equals(other$isoNumCurrCode)) {
                break Label_0474;
            }
            return false;
        }
        final Object this$actionTaken = this.getActionTaken();
        final Object other$actionTaken = other.getActionTaken();
        Label_0511: {
            if (this$actionTaken == null) {
                if (other$actionTaken == null) {
                    break Label_0511;
                }
            }
            else if (this$actionTaken.equals(other$actionTaken)) {
                break Label_0511;
            }
            return false;
        }
        final Object this$issAccCap = this.getIssAccCap();
        final Object other$issAccCap = other.getIssAccCap();
        Label_0548: {
            if (this$issAccCap == null) {
                if (other$issAccCap == null) {
                    break Label_0548;
                }
            }
            else if (this$issAccCap.equals(other$issAccCap)) {
                break Label_0548;
            }
            return false;
        }
        final Object this$prodClssfy = this.getProdClssfy();
        final Object other$prodClssfy = other.getProdClssfy();
        Label_0585: {
            if (this$prodClssfy == null) {
                if (other$prodClssfy == null) {
                    break Label_0585;
                }
            }
            else if (this$prodClssfy.equals(other$prodClssfy)) {
                break Label_0585;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0622: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0622;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0622;
            }
            return false;
        }
        final Object this$issuerBank = this.getIssuerBank();
        final Object other$issuerBank = other.getIssuerBank();
        Label_0659: {
            if (this$issuerBank == null) {
                if (other$issuerBank == null) {
                    break Label_0659;
                }
            }
            else if (this$issuerBank.equals(other$issuerBank)) {
                break Label_0659;
            }
            return false;
        }
        final Object this$schemeProduct = this.getSchemeProduct();
        final Object other$schemeProduct = other.getSchemeProduct();
        Label_0696: {
            if (this$schemeProduct == null) {
                if (other$schemeProduct == null) {
                    break Label_0696;
                }
            }
            else if (this$schemeProduct.equals(other$schemeProduct)) {
                break Label_0696;
            }
            return false;
        }
        final Object this$badgeInd = this.getBadgeInd();
        final Object other$badgeInd = other.getBadgeInd();
        if (this$badgeInd == null) {
            if (other$badgeInd == null) {
                return true;
            }
        }
        else if (this$badgeInd.equals(other$badgeInd)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof JaywanIssAccRangeEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getJobNumber();
        final Object $institutionId = this.getInstitutionId();
        result = result * 59 + (($institutionId == null) ? 43 : $institutionId.hashCode());
        final Object $binRangeLow = this.getBinRangeLow();
        result = result * 59 + (($binRangeLow == null) ? 43 : $binRangeLow.hashCode());
        final Object $binRangeHigh = this.getBinRangeHigh();
        result = result * 59 + (($binRangeHigh == null) ? 43 : $binRangeHigh.hashCode());
        final Object $panLength = this.getPanLength();
        result = result * 59 + (($panLength == null) ? 43 : $panLength.hashCode());
        final Object $binLength = this.getBinLength();
        result = result * 59 + (($binLength == null) ? 43 : $binLength.hashCode());
        final Object $productType = this.getProductType();
        result = result * 59 + (($productType == null) ? 43 : $productType.hashCode());
        final Object $schemeCode = this.getSchemeCode();
        result = result * 59 + (($schemeCode == null) ? 43 : $schemeCode.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $service = this.getService();
        result = result * 59 + (($service == null) ? 43 : $service.hashCode());
        final Object $currencyCode = this.getCurrencyCode();
        result = result * 59 + (($currencyCode == null) ? 43 : $currencyCode.hashCode());
        final Object $isoNumCurrCode = this.getIsoNumCurrCode();
        result = result * 59 + (($isoNumCurrCode == null) ? 43 : $isoNumCurrCode.hashCode());
        final Object $actionTaken = this.getActionTaken();
        result = result * 59 + (($actionTaken == null) ? 43 : $actionTaken.hashCode());
        final Object $issAccCap = this.getIssAccCap();
        result = result * 59 + (($issAccCap == null) ? 43 : $issAccCap.hashCode());
        final Object $prodClssfy = this.getProdClssfy();
        result = result * 59 + (($prodClssfy == null) ? 43 : $prodClssfy.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $issuerBank = this.getIssuerBank();
        result = result * 59 + (($issuerBank == null) ? 43 : $issuerBank.hashCode());
        final Object $schemeProduct = this.getSchemeProduct();
        result = result * 59 + (($schemeProduct == null) ? 43 : $schemeProduct.hashCode());
        final Object $badgeInd = this.getBadgeInd();
        result = result * 59 + (($badgeInd == null) ? 43 : $badgeInd.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "JaywanIssAccRangeEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobNumber=" + this.getJobNumber() + ", issuerBank=" + this.getIssuerBank() + ", institutionId=" + this.getInstitutionId() + ", binRangeLow=" + this.getBinRangeLow() + ", binRangeHigh=" + this.getBinRangeHigh() + ", panLength=" + this.getPanLength() + ", binLength=" + this.getBinLength() + ", productType=" + this.getProductType() + ", schemeCode=" + this.getSchemeCode() + ", schemeProduct=" + this.getSchemeProduct() + ", cardType=" + this.getCardType() + ", service=" + this.getService() + ", currencyCode=" + this.getCurrencyCode() + ", isoNumCurrCode=" + this.getIsoNumCurrCode() + ", actionTaken=" + this.getActionTaken() + ", issAccCap=" + this.getIssAccCap() + ", prodClssfy=" + this.getProdClssfy() + ", badgeInd=" + this.getBadgeInd();
    }
}
