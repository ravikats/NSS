/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.vo.TBTRecordVo
 */
package com.empay.amex.vo;

public class TBTRecordVo {
    private String recordType;
    private String recordNumber;
    private String merchantId;
    private String reserved1;
    private String tbtIdenticationNumber;
    private String tbtCreationDate;
    private String totalNumOfTabs;
    private String reserved2;
    private String tbtAmount;
    private String tbtAmountSign;
    private String tbtCurrencyCode;
    private String reserved3;
    private String reserved4;
    private String reserved5;
    private String tbtImageSeqNumber;
    private String reserved6;

    public String getTBT() {
        return this.recordType + this.recordNumber + this.merchantId + this.reserved1 + this.tbtIdenticationNumber + this.tbtCreationDate + this.totalNumOfTabs + this.reserved2 + this.tbtAmount + this.tbtAmountSign + this.tbtCurrencyCode + this.reserved3 + this.reserved4 + this.reserved5 + this.tbtImageSeqNumber + this.reserved6;
    }

    public String getRecordType() {
        return this.recordType;
    }

    public String getRecordNumber() {
        return this.recordNumber;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getReserved1() {
        return this.reserved1;
    }

    public String getTbtIdenticationNumber() {
        return this.tbtIdenticationNumber;
    }

    public String getTbtCreationDate() {
        return this.tbtCreationDate;
    }

    public String getTotalNumOfTabs() {
        return this.totalNumOfTabs;
    }

    public String getReserved2() {
        return this.reserved2;
    }

    public String getTbtAmount() {
        return this.tbtAmount;
    }

    public String getTbtAmountSign() {
        return this.tbtAmountSign;
    }

    public String getTbtCurrencyCode() {
        return this.tbtCurrencyCode;
    }

    public String getReserved3() {
        return this.reserved3;
    }

    public String getReserved4() {
        return this.reserved4;
    }

    public String getReserved5() {
        return this.reserved5;
    }

    public String getTbtImageSeqNumber() {
        return this.tbtImageSeqNumber;
    }

    public String getReserved6() {
        return this.reserved6;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public void setTbtIdenticationNumber(String tbtIdenticationNumber) {
        this.tbtIdenticationNumber = tbtIdenticationNumber;
    }

    public void setTbtCreationDate(String tbtCreationDate) {
        this.tbtCreationDate = tbtCreationDate;
    }

    public void setTotalNumOfTabs(String totalNumOfTabs) {
        this.totalNumOfTabs = totalNumOfTabs;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public void setTbtAmount(String tbtAmount) {
        this.tbtAmount = tbtAmount;
    }

    public void setTbtAmountSign(String tbtAmountSign) {
        this.tbtAmountSign = tbtAmountSign;
    }

    public void setTbtCurrencyCode(String tbtCurrencyCode) {
        this.tbtCurrencyCode = tbtCurrencyCode;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public void setReserved5(String reserved5) {
        this.reserved5 = reserved5;
    }

    public void setTbtImageSeqNumber(String tbtImageSeqNumber) {
        this.tbtImageSeqNumber = tbtImageSeqNumber;
    }

    public void setReserved6(String reserved6) {
        this.reserved6 = reserved6;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TBTRecordVo)) {
            return false;
        }
        TBTRecordVo other = (TBTRecordVo)o;
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
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$reserved1 = this.getReserved1();
        String other$reserved1 = other.getReserved1();
        if (this$reserved1 == null ? other$reserved1 != null : !this$reserved1.equals(other$reserved1)) {
            return false;
        }
        String this$tbtIdenticationNumber = this.getTbtIdenticationNumber();
        String other$tbtIdenticationNumber = other.getTbtIdenticationNumber();
        if (this$tbtIdenticationNumber == null ? other$tbtIdenticationNumber != null : !this$tbtIdenticationNumber.equals(other$tbtIdenticationNumber)) {
            return false;
        }
        String this$tbtCreationDate = this.getTbtCreationDate();
        String other$tbtCreationDate = other.getTbtCreationDate();
        if (this$tbtCreationDate == null ? other$tbtCreationDate != null : !this$tbtCreationDate.equals(other$tbtCreationDate)) {
            return false;
        }
        String this$totalNumOfTabs = this.getTotalNumOfTabs();
        String other$totalNumOfTabs = other.getTotalNumOfTabs();
        if (this$totalNumOfTabs == null ? other$totalNumOfTabs != null : !this$totalNumOfTabs.equals(other$totalNumOfTabs)) {
            return false;
        }
        String this$reserved2 = this.getReserved2();
        String other$reserved2 = other.getReserved2();
        if (this$reserved2 == null ? other$reserved2 != null : !this$reserved2.equals(other$reserved2)) {
            return false;
        }
        String this$tbtAmount = this.getTbtAmount();
        String other$tbtAmount = other.getTbtAmount();
        if (this$tbtAmount == null ? other$tbtAmount != null : !this$tbtAmount.equals(other$tbtAmount)) {
            return false;
        }
        String this$tbtAmountSign = this.getTbtAmountSign();
        String other$tbtAmountSign = other.getTbtAmountSign();
        if (this$tbtAmountSign == null ? other$tbtAmountSign != null : !this$tbtAmountSign.equals(other$tbtAmountSign)) {
            return false;
        }
        String this$tbtCurrencyCode = this.getTbtCurrencyCode();
        String other$tbtCurrencyCode = other.getTbtCurrencyCode();
        if (this$tbtCurrencyCode == null ? other$tbtCurrencyCode != null : !this$tbtCurrencyCode.equals(other$tbtCurrencyCode)) {
            return false;
        }
        String this$reserved3 = this.getReserved3();
        String other$reserved3 = other.getReserved3();
        if (this$reserved3 == null ? other$reserved3 != null : !this$reserved3.equals(other$reserved3)) {
            return false;
        }
        String this$reserved4 = this.getReserved4();
        String other$reserved4 = other.getReserved4();
        if (this$reserved4 == null ? other$reserved4 != null : !this$reserved4.equals(other$reserved4)) {
            return false;
        }
        String this$reserved5 = this.getReserved5();
        String other$reserved5 = other.getReserved5();
        if (this$reserved5 == null ? other$reserved5 != null : !this$reserved5.equals(other$reserved5)) {
            return false;
        }
        String this$tbtImageSeqNumber = this.getTbtImageSeqNumber();
        String other$tbtImageSeqNumber = other.getTbtImageSeqNumber();
        if (this$tbtImageSeqNumber == null ? other$tbtImageSeqNumber != null : !this$tbtImageSeqNumber.equals(other$tbtImageSeqNumber)) {
            return false;
        }
        String this$reserved6 = this.getReserved6();
        String other$reserved6 = other.getReserved6();
        return !(this$reserved6 == null ? other$reserved6 != null : !this$reserved6.equals(other$reserved6));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TBTRecordVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $recordType = this.getRecordType();
        result = result * 59 + ($recordType == null ? 43 : $recordType.hashCode());
        String $recordNumber = this.getRecordNumber();
        result = result * 59 + ($recordNumber == null ? 43 : $recordNumber.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $reserved1 = this.getReserved1();
        result = result * 59 + ($reserved1 == null ? 43 : $reserved1.hashCode());
        String $tbtIdenticationNumber = this.getTbtIdenticationNumber();
        result = result * 59 + ($tbtIdenticationNumber == null ? 43 : $tbtIdenticationNumber.hashCode());
        String $tbtCreationDate = this.getTbtCreationDate();
        result = result * 59 + ($tbtCreationDate == null ? 43 : $tbtCreationDate.hashCode());
        String $totalNumOfTabs = this.getTotalNumOfTabs();
        result = result * 59 + ($totalNumOfTabs == null ? 43 : $totalNumOfTabs.hashCode());
        String $reserved2 = this.getReserved2();
        result = result * 59 + ($reserved2 == null ? 43 : $reserved2.hashCode());
        String $tbtAmount = this.getTbtAmount();
        result = result * 59 + ($tbtAmount == null ? 43 : $tbtAmount.hashCode());
        String $tbtAmountSign = this.getTbtAmountSign();
        result = result * 59 + ($tbtAmountSign == null ? 43 : $tbtAmountSign.hashCode());
        String $tbtCurrencyCode = this.getTbtCurrencyCode();
        result = result * 59 + ($tbtCurrencyCode == null ? 43 : $tbtCurrencyCode.hashCode());
        String $reserved3 = this.getReserved3();
        result = result * 59 + ($reserved3 == null ? 43 : $reserved3.hashCode());
        String $reserved4 = this.getReserved4();
        result = result * 59 + ($reserved4 == null ? 43 : $reserved4.hashCode());
        String $reserved5 = this.getReserved5();
        result = result * 59 + ($reserved5 == null ? 43 : $reserved5.hashCode());
        String $tbtImageSeqNumber = this.getTbtImageSeqNumber();
        result = result * 59 + ($tbtImageSeqNumber == null ? 43 : $tbtImageSeqNumber.hashCode());
        String $reserved6 = this.getReserved6();
        result = result * 59 + ($reserved6 == null ? 43 : $reserved6.hashCode());
        return result;
    }

    public String toString() {
        return "TBTRecordVo(recordType=" + this.getRecordType() + ", recordNumber=" + this.getRecordNumber() + ", merchantId=" + this.getMerchantId() + ", reserved1=" + this.getReserved1() + ", tbtIdenticationNumber=" + this.getTbtIdenticationNumber() + ", tbtCreationDate=" + this.getTbtCreationDate() + ", totalNumOfTabs=" + this.getTotalNumOfTabs() + ", reserved2=" + this.getReserved2() + ", tbtAmount=" + this.getTbtAmount() + ", tbtAmountSign=" + this.getTbtAmountSign() + ", tbtCurrencyCode=" + this.getTbtCurrencyCode() + ", reserved3=" + this.getReserved3() + ", reserved4=" + this.getReserved4() + ", reserved5=" + this.getReserved5() + ", tbtImageSeqNumber=" + this.getTbtImageSeqNumber() + ", reserved6=" + this.getReserved6() + ")";
    }
}

