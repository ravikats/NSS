/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MercuryIssAccRangeEntity
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
@Table(name="MERCURY_ISS_ACC_RANGE")
public class MercuryIssAccRangeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MIA_SER_NUMBER")
    private int serialNumber;
    @Column(name="MIA_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MIA_UPDATED_USER")
    private int updatedUser;
    @Column(name="MIA_PRJ_SER_NUMBER")
    private int jobNumber;
    @Column(name="MIA_BIN_LOW_VALUE")
    private Long binRangeLow;
    @Column(name="MIA_BIN_HIGH_VALUE")
    private Long binRangeHigh;
    @Column(name="MIA_CARD_TYPE")
    private Integer cardType;
    @Column(name="MIA_CARD_PRODUCT_ID")
    private Integer cardProductId;
    @Column(name="MIA_CARD_VARIANT")
    private Integer cardVariant;
    @Column(name="MIA_CARD_SCHEME")
    private Integer cardScheme;
    @Column(name="MIA_CUR_CODE")
    private Integer currencyCode;
    @Column(name="MIA_CON_CODE")
    private Integer countryCode;
    @Column(name="MIA_STATUS")
    private Character status;

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

    public Long getBinRangeLow() {
        return this.binRangeLow;
    }

    public Long getBinRangeHigh() {
        return this.binRangeHigh;
    }

    public Integer getCardType() {
        return this.cardType;
    }

    public Integer getCardProductId() {
        return this.cardProductId;
    }

    public Integer getCardVariant() {
        return this.cardVariant;
    }

    public Integer getCardScheme() {
        return this.cardScheme;
    }

    public Integer getCurrencyCode() {
        return this.currencyCode;
    }

    public Integer getCountryCode() {
        return this.countryCode;
    }

    public Character getStatus() {
        return this.status;
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

    public void setBinRangeLow(Long binRangeLow) {
        this.binRangeLow = binRangeLow;
    }

    public void setBinRangeHigh(Long binRangeHigh) {
        this.binRangeHigh = binRangeHigh;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public void setCardProductId(Integer cardProductId) {
        this.cardProductId = cardProductId;
    }

    public void setCardVariant(Integer cardVariant) {
        this.cardVariant = cardVariant;
    }

    public void setCardScheme(Integer cardScheme) {
        this.cardScheme = cardScheme;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MercuryIssAccRangeEntity)) {
            return false;
        }
        MercuryIssAccRangeEntity other = (MercuryIssAccRangeEntity)o;
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
        Integer this$cardType = this.getCardType();
        Integer other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Integer this$cardProductId = this.getCardProductId();
        Integer other$cardProductId = other.getCardProductId();
        if (this$cardProductId == null ? other$cardProductId != null : !((Object)this$cardProductId).equals(other$cardProductId)) {
            return false;
        }
        Integer this$cardVariant = this.getCardVariant();
        Integer other$cardVariant = other.getCardVariant();
        if (this$cardVariant == null ? other$cardVariant != null : !((Object)this$cardVariant).equals(other$cardVariant)) {
            return false;
        }
        Integer this$cardScheme = this.getCardScheme();
        Integer other$cardScheme = other.getCardScheme();
        if (this$cardScheme == null ? other$cardScheme != null : !((Object)this$cardScheme).equals(other$cardScheme)) {
            return false;
        }
        Integer this$currencyCode = this.getCurrencyCode();
        Integer other$currencyCode = other.getCurrencyCode();
        if (this$currencyCode == null ? other$currencyCode != null : !((Object)this$currencyCode).equals(other$currencyCode)) {
            return false;
        }
        Integer this$countryCode = this.getCountryCode();
        Integer other$countryCode = other.getCountryCode();
        if (this$countryCode == null ? other$countryCode != null : !((Object)this$countryCode).equals(other$countryCode)) {
            return false;
        }
        Character this$status = this.getStatus();
        Character other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        return !(this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MercuryIssAccRangeEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getJobNumber();
        Long $binRangeLow = this.getBinRangeLow();
        result = result * 59 + ($binRangeLow == null ? 43 : ((Object)$binRangeLow).hashCode());
        Long $binRangeHigh = this.getBinRangeHigh();
        result = result * 59 + ($binRangeHigh == null ? 43 : ((Object)$binRangeHigh).hashCode());
        Integer $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Integer $cardProductId = this.getCardProductId();
        result = result * 59 + ($cardProductId == null ? 43 : ((Object)$cardProductId).hashCode());
        Integer $cardVariant = this.getCardVariant();
        result = result * 59 + ($cardVariant == null ? 43 : ((Object)$cardVariant).hashCode());
        Integer $cardScheme = this.getCardScheme();
        result = result * 59 + ($cardScheme == null ? 43 : ((Object)$cardScheme).hashCode());
        Integer $currencyCode = this.getCurrencyCode();
        result = result * 59 + ($currencyCode == null ? 43 : ((Object)$currencyCode).hashCode());
        Integer $countryCode = this.getCountryCode();
        result = result * 59 + ($countryCode == null ? 43 : ((Object)$countryCode).hashCode());
        Character $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        return result;
    }

    public String toString() {
        return "MercuryIssAccRangeEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobNumber=" + this.getJobNumber() + ", binRangeLow=" + this.getBinRangeLow() + ", binRangeHigh=" + this.getBinRangeHigh() + ", cardType=" + this.getCardType() + ", cardProductId=" + this.getCardProductId() + ", cardVariant=" + this.getCardVariant() + ", cardScheme=" + this.getCardScheme() + ", currencyCode=" + this.getCurrencyCode() + ", countryCode=" + this.getCountryCode() + ", status=" + this.getStatus() + ")";
    }
}

