/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.vo.TAALocationRecordVo
 */
package com.empay.amex.vo;

public class TAALocationRecordVo {
    private String recordType;
    private String recordNumber;
    private String transactionIdentifier;
    private String reserved1;
    private String addenaTypeCode;
    private String locationName;
    private String locationAddress;
    private String locationCity;
    private String locationRegionCode;
    private String locationCountryCode;
    private String locationPostalCode;
    private String merchantCategoryCode;
    private String sellerId;
    private String reserved2;

    public String getTAALocationDetail() {
        return this.recordType + this.recordNumber + this.transactionIdentifier + this.reserved1 + this.addenaTypeCode + this.locationName + this.locationAddress + this.locationCity + this.locationRegionCode + this.locationCountryCode + this.locationPostalCode + this.merchantCategoryCode + this.sellerId + this.reserved2;
    }

    public String getRecordType() {
        return this.recordType;
    }

    public String getRecordNumber() {
        return this.recordNumber;
    }

    public String getTransactionIdentifier() {
        return this.transactionIdentifier;
    }

    public String getReserved1() {
        return this.reserved1;
    }

    public String getAddenaTypeCode() {
        return this.addenaTypeCode;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public String getLocationAddress() {
        return this.locationAddress;
    }

    public String getLocationCity() {
        return this.locationCity;
    }

    public String getLocationRegionCode() {
        return this.locationRegionCode;
    }

    public String getLocationCountryCode() {
        return this.locationCountryCode;
    }

    public String getLocationPostalCode() {
        return this.locationPostalCode;
    }

    public String getMerchantCategoryCode() {
        return this.merchantCategoryCode;
    }

    public String getSellerId() {
        return this.sellerId;
    }

    public String getReserved2() {
        return this.reserved2;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setTransactionIdentifier(String transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public void setAddenaTypeCode(String addenaTypeCode) {
        this.addenaTypeCode = addenaTypeCode;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public void setLocationRegionCode(String locationRegionCode) {
        this.locationRegionCode = locationRegionCode;
    }

    public void setLocationCountryCode(String locationCountryCode) {
        this.locationCountryCode = locationCountryCode;
    }

    public void setLocationPostalCode(String locationPostalCode) {
        this.locationPostalCode = locationPostalCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TAALocationRecordVo)) {
            return false;
        }
        TAALocationRecordVo other = (TAALocationRecordVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$recordType = this.getRecordType();
        String other$recordType = other.getRecordType();
        if (this$recordType == null ? other$recordType != null : !this$recordType.equals(other$recordType)) {
            return false;
        }
        String this$recordNumber = this.getRecordNumber();
        String other$recordNumber = other.getRecordNumber();
        if (this$recordNumber == null ? other$recordNumber != null : !this$recordNumber.equals(other$recordNumber)) {
            return false;
        }
        String this$transactionIdentifier = this.getTransactionIdentifier();
        String other$transactionIdentifier = other.getTransactionIdentifier();
        if (this$transactionIdentifier == null ? other$transactionIdentifier != null : !this$transactionIdentifier.equals(other$transactionIdentifier)) {
            return false;
        }
        String this$reserved1 = this.getReserved1();
        String other$reserved1 = other.getReserved1();
        if (this$reserved1 == null ? other$reserved1 != null : !this$reserved1.equals(other$reserved1)) {
            return false;
        }
        String this$addenaTypeCode = this.getAddenaTypeCode();
        String other$addenaTypeCode = other.getAddenaTypeCode();
        if (this$addenaTypeCode == null ? other$addenaTypeCode != null : !this$addenaTypeCode.equals(other$addenaTypeCode)) {
            return false;
        }
        String this$locationName = this.getLocationName();
        String other$locationName = other.getLocationName();
        if (this$locationName == null ? other$locationName != null : !this$locationName.equals(other$locationName)) {
            return false;
        }
        String this$locationAddress = this.getLocationAddress();
        String other$locationAddress = other.getLocationAddress();
        if (this$locationAddress == null ? other$locationAddress != null : !this$locationAddress.equals(other$locationAddress)) {
            return false;
        }
        String this$locationCity = this.getLocationCity();
        String other$locationCity = other.getLocationCity();
        if (this$locationCity == null ? other$locationCity != null : !this$locationCity.equals(other$locationCity)) {
            return false;
        }
        String this$locationRegionCode = this.getLocationRegionCode();
        String other$locationRegionCode = other.getLocationRegionCode();
        if (this$locationRegionCode == null ? other$locationRegionCode != null : !this$locationRegionCode.equals(other$locationRegionCode)) {
            return false;
        }
        String this$locationCountryCode = this.getLocationCountryCode();
        String other$locationCountryCode = other.getLocationCountryCode();
        if (this$locationCountryCode == null ? other$locationCountryCode != null : !this$locationCountryCode.equals(other$locationCountryCode)) {
            return false;
        }
        String this$locationPostalCode = this.getLocationPostalCode();
        String other$locationPostalCode = other.getLocationPostalCode();
        if (this$locationPostalCode == null ? other$locationPostalCode != null : !this$locationPostalCode.equals(other$locationPostalCode)) {
            return false;
        }
        String this$merchantCategoryCode = this.getMerchantCategoryCode();
        String other$merchantCategoryCode = other.getMerchantCategoryCode();
        if (this$merchantCategoryCode == null ? other$merchantCategoryCode != null : !this$merchantCategoryCode.equals(other$merchantCategoryCode)) {
            return false;
        }
        String this$sellerId = this.getSellerId();
        String other$sellerId = other.getSellerId();
        if (this$sellerId == null ? other$sellerId != null : !this$sellerId.equals(other$sellerId)) {
            return false;
        }
        String this$reserved2 = this.getReserved2();
        String other$reserved2 = other.getReserved2();
        return !(this$reserved2 == null ? other$reserved2 != null : !this$reserved2.equals(other$reserved2));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TAALocationRecordVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $recordType = this.getRecordType();
        result = result * 59 + ($recordType == null ? 43 : $recordType.hashCode());
        String $recordNumber = this.getRecordNumber();
        result = result * 59 + ($recordNumber == null ? 43 : $recordNumber.hashCode());
        String $transactionIdentifier = this.getTransactionIdentifier();
        result = result * 59 + ($transactionIdentifier == null ? 43 : $transactionIdentifier.hashCode());
        String $reserved1 = this.getReserved1();
        result = result * 59 + ($reserved1 == null ? 43 : $reserved1.hashCode());
        String $addenaTypeCode = this.getAddenaTypeCode();
        result = result * 59 + ($addenaTypeCode == null ? 43 : $addenaTypeCode.hashCode());
        String $locationName = this.getLocationName();
        result = result * 59 + ($locationName == null ? 43 : $locationName.hashCode());
        String $locationAddress = this.getLocationAddress();
        result = result * 59 + ($locationAddress == null ? 43 : $locationAddress.hashCode());
        String $locationCity = this.getLocationCity();
        result = result * 59 + ($locationCity == null ? 43 : $locationCity.hashCode());
        String $locationRegionCode = this.getLocationRegionCode();
        result = result * 59 + ($locationRegionCode == null ? 43 : $locationRegionCode.hashCode());
        String $locationCountryCode = this.getLocationCountryCode();
        result = result * 59 + ($locationCountryCode == null ? 43 : $locationCountryCode.hashCode());
        String $locationPostalCode = this.getLocationPostalCode();
        result = result * 59 + ($locationPostalCode == null ? 43 : $locationPostalCode.hashCode());
        String $merchantCategoryCode = this.getMerchantCategoryCode();
        result = result * 59 + ($merchantCategoryCode == null ? 43 : $merchantCategoryCode.hashCode());
        String $sellerId = this.getSellerId();
        result = result * 59 + ($sellerId == null ? 43 : $sellerId.hashCode());
        String $reserved2 = this.getReserved2();
        result = result * 59 + ($reserved2 == null ? 43 : $reserved2.hashCode());
        return result;
    }

    public String toString() {
        return "TAALocationRecordVo(recordType=" + this.getRecordType() + ", recordNumber=" + this.getRecordNumber() + ", transactionIdentifier=" + this.getTransactionIdentifier() + ", reserved1=" + this.getReserved1() + ", addenaTypeCode=" + this.getAddenaTypeCode() + ", locationName=" + this.getLocationName() + ", locationAddress=" + this.getLocationAddress() + ", locationCity=" + this.getLocationCity() + ", locationRegionCode=" + this.getLocationRegionCode() + ", locationCountryCode=" + this.getLocationCountryCode() + ", locationPostalCode=" + this.getLocationPostalCode() + ", merchantCategoryCode=" + this.getMerchantCategoryCode() + ", sellerId=" + this.getSellerId() + ", reserved2=" + this.getReserved2() + ")";
    }
}

