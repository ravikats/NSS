/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.vo.TAAEMVRecordVo
 */
package com.empay.amex.vo;

public class TAAEMVRecordVo {
    private String recordType;
    private String recordNumber;
    private String transactionIdentifier;
    private String emvFormatType;
    private String addenaTypeCode;
    private String iccSystemRelatedData;
    private String reserved;

    public String getEMVchipCardData() {
        return this.recordType + this.recordNumber + this.transactionIdentifier + this.emvFormatType + this.addenaTypeCode + this.iccSystemRelatedData + this.reserved;
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

    public String getEmvFormatType() {
        return this.emvFormatType;
    }

    public String getAddenaTypeCode() {
        return this.addenaTypeCode;
    }

    public String getIccSystemRelatedData() {
        return this.iccSystemRelatedData;
    }

    public String getReserved() {
        return this.reserved;
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

    public void setEmvFormatType(String emvFormatType) {
        this.emvFormatType = emvFormatType;
    }

    public void setAddenaTypeCode(String addenaTypeCode) {
        this.addenaTypeCode = addenaTypeCode;
    }

    public void setIccSystemRelatedData(String iccSystemRelatedData) {
        this.iccSystemRelatedData = iccSystemRelatedData;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TAAEMVRecordVo)) {
            return false;
        }
        TAAEMVRecordVo other = (TAAEMVRecordVo)o;
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
        String this$emvFormatType = this.getEmvFormatType();
        String other$emvFormatType = other.getEmvFormatType();
        if (this$emvFormatType == null ? other$emvFormatType != null : !this$emvFormatType.equals(other$emvFormatType)) {
            return false;
        }
        String this$addenaTypeCode = this.getAddenaTypeCode();
        String other$addenaTypeCode = other.getAddenaTypeCode();
        if (this$addenaTypeCode == null ? other$addenaTypeCode != null : !this$addenaTypeCode.equals(other$addenaTypeCode)) {
            return false;
        }
        String this$iccSystemRelatedData = this.getIccSystemRelatedData();
        String other$iccSystemRelatedData = other.getIccSystemRelatedData();
        if (this$iccSystemRelatedData == null ? other$iccSystemRelatedData != null : !this$iccSystemRelatedData.equals(other$iccSystemRelatedData)) {
            return false;
        }
        String this$reserved = this.getReserved();
        String other$reserved = other.getReserved();
        return !(this$reserved == null ? other$reserved != null : !this$reserved.equals(other$reserved));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TAAEMVRecordVo;
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
        String $emvFormatType = this.getEmvFormatType();
        result = result * 59 + ($emvFormatType == null ? 43 : $emvFormatType.hashCode());
        String $addenaTypeCode = this.getAddenaTypeCode();
        result = result * 59 + ($addenaTypeCode == null ? 43 : $addenaTypeCode.hashCode());
        String $iccSystemRelatedData = this.getIccSystemRelatedData();
        result = result * 59 + ($iccSystemRelatedData == null ? 43 : $iccSystemRelatedData.hashCode());
        String $reserved = this.getReserved();
        result = result * 59 + ($reserved == null ? 43 : $reserved.hashCode());
        return result;
    }

    public String toString() {
        return "TAAEMVRecordVo(recordType=" + this.getRecordType() + ", recordNumber=" + this.getRecordNumber() + ", transactionIdentifier=" + this.getTransactionIdentifier() + ", emvFormatType=" + this.getEmvFormatType() + ", addenaTypeCode=" + this.getAddenaTypeCode() + ", iccSystemRelatedData=" + this.getIccSystemRelatedData() + ", reserved=" + this.getReserved() + ")";
    }
}

