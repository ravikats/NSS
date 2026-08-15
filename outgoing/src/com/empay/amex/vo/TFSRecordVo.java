/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.vo.TFSRecordVo
 */
package com.empay.amex.vo;

public class TFSRecordVo {
    private String recordType;
    private String recordNumber;
    private String NumberOfDebits;
    private String reserved1;
    private String hashTotalDebAmount;
    private String NumberOfCredits;
    private String reserved2;
    private String hashTotalCreditAmount;
    private String reserved3;
    private String hashTotalAmount;
    private String reserved4;

    public String getTFS() {
        return this.recordType + this.recordNumber + this.NumberOfDebits + this.reserved1 + this.hashTotalDebAmount + this.NumberOfCredits + this.reserved2 + this.hashTotalCreditAmount + this.reserved3 + this.hashTotalAmount + this.reserved4;
    }

    public String getRecordType() {
        return this.recordType;
    }

    public String getRecordNumber() {
        return this.recordNumber;
    }

    public String getNumberOfDebits() {
        return this.NumberOfDebits;
    }

    public String getReserved1() {
        return this.reserved1;
    }

    public String getHashTotalDebAmount() {
        return this.hashTotalDebAmount;
    }

    public String getNumberOfCredits() {
        return this.NumberOfCredits;
    }

    public String getReserved2() {
        return this.reserved2;
    }

    public String getHashTotalCreditAmount() {
        return this.hashTotalCreditAmount;
    }

    public String getReserved3() {
        return this.reserved3;
    }

    public String getHashTotalAmount() {
        return this.hashTotalAmount;
    }

    public String getReserved4() {
        return this.reserved4;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setNumberOfDebits(String NumberOfDebits) {
        this.NumberOfDebits = NumberOfDebits;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public void setHashTotalDebAmount(String hashTotalDebAmount) {
        this.hashTotalDebAmount = hashTotalDebAmount;
    }

    public void setNumberOfCredits(String NumberOfCredits) {
        this.NumberOfCredits = NumberOfCredits;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public void setHashTotalCreditAmount(String hashTotalCreditAmount) {
        this.hashTotalCreditAmount = hashTotalCreditAmount;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public void setHashTotalAmount(String hashTotalAmount) {
        this.hashTotalAmount = hashTotalAmount;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TFSRecordVo)) {
            return false;
        }
        TFSRecordVo other = (TFSRecordVo)o;
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
        String this$NumberOfDebits = this.getNumberOfDebits();
        String other$NumberOfDebits = other.getNumberOfDebits();
        if (this$NumberOfDebits == null ? other$NumberOfDebits != null : !this$NumberOfDebits.equals(other$NumberOfDebits)) {
            return false;
        }
        String this$reserved1 = this.getReserved1();
        String other$reserved1 = other.getReserved1();
        if (this$reserved1 == null ? other$reserved1 != null : !this$reserved1.equals(other$reserved1)) {
            return false;
        }
        String this$hashTotalDebAmount = this.getHashTotalDebAmount();
        String other$hashTotalDebAmount = other.getHashTotalDebAmount();
        if (this$hashTotalDebAmount == null ? other$hashTotalDebAmount != null : !this$hashTotalDebAmount.equals(other$hashTotalDebAmount)) {
            return false;
        }
        String this$NumberOfCredits = this.getNumberOfCredits();
        String other$NumberOfCredits = other.getNumberOfCredits();
        if (this$NumberOfCredits == null ? other$NumberOfCredits != null : !this$NumberOfCredits.equals(other$NumberOfCredits)) {
            return false;
        }
        String this$reserved2 = this.getReserved2();
        String other$reserved2 = other.getReserved2();
        if (this$reserved2 == null ? other$reserved2 != null : !this$reserved2.equals(other$reserved2)) {
            return false;
        }
        String this$hashTotalCreditAmount = this.getHashTotalCreditAmount();
        String other$hashTotalCreditAmount = other.getHashTotalCreditAmount();
        if (this$hashTotalCreditAmount == null ? other$hashTotalCreditAmount != null : !this$hashTotalCreditAmount.equals(other$hashTotalCreditAmount)) {
            return false;
        }
        String this$reserved3 = this.getReserved3();
        String other$reserved3 = other.getReserved3();
        if (this$reserved3 == null ? other$reserved3 != null : !this$reserved3.equals(other$reserved3)) {
            return false;
        }
        String this$hashTotalAmount = this.getHashTotalAmount();
        String other$hashTotalAmount = other.getHashTotalAmount();
        if (this$hashTotalAmount == null ? other$hashTotalAmount != null : !this$hashTotalAmount.equals(other$hashTotalAmount)) {
            return false;
        }
        String this$reserved4 = this.getReserved4();
        String other$reserved4 = other.getReserved4();
        return !(this$reserved4 == null ? other$reserved4 != null : !this$reserved4.equals(other$reserved4));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TFSRecordVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $recordType = this.getRecordType();
        result = result * 59 + ($recordType == null ? 43 : $recordType.hashCode());
        String $recordNumber = this.getRecordNumber();
        result = result * 59 + ($recordNumber == null ? 43 : $recordNumber.hashCode());
        String $NumberOfDebits = this.getNumberOfDebits();
        result = result * 59 + ($NumberOfDebits == null ? 43 : $NumberOfDebits.hashCode());
        String $reserved1 = this.getReserved1();
        result = result * 59 + ($reserved1 == null ? 43 : $reserved1.hashCode());
        String $hashTotalDebAmount = this.getHashTotalDebAmount();
        result = result * 59 + ($hashTotalDebAmount == null ? 43 : $hashTotalDebAmount.hashCode());
        String $NumberOfCredits = this.getNumberOfCredits();
        result = result * 59 + ($NumberOfCredits == null ? 43 : $NumberOfCredits.hashCode());
        String $reserved2 = this.getReserved2();
        result = result * 59 + ($reserved2 == null ? 43 : $reserved2.hashCode());
        String $hashTotalCreditAmount = this.getHashTotalCreditAmount();
        result = result * 59 + ($hashTotalCreditAmount == null ? 43 : $hashTotalCreditAmount.hashCode());
        String $reserved3 = this.getReserved3();
        result = result * 59 + ($reserved3 == null ? 43 : $reserved3.hashCode());
        String $hashTotalAmount = this.getHashTotalAmount();
        result = result * 59 + ($hashTotalAmount == null ? 43 : $hashTotalAmount.hashCode());
        String $reserved4 = this.getReserved4();
        result = result * 59 + ($reserved4 == null ? 43 : $reserved4.hashCode());
        return result;
    }

    public String toString() {
        return "TFSRecordVo(recordType=" + this.getRecordType() + ", recordNumber=" + this.getRecordNumber() + ", NumberOfDebits=" + this.getNumberOfDebits() + ", reserved1=" + this.getReserved1() + ", hashTotalDebAmount=" + this.getHashTotalDebAmount() + ", NumberOfCredits=" + this.getNumberOfCredits() + ", reserved2=" + this.getReserved2() + ", hashTotalCreditAmount=" + this.getHashTotalCreditAmount() + ", reserved3=" + this.getReserved3() + ", hashTotalAmount=" + this.getHashTotalAmount() + ", reserved4=" + this.getReserved4() + ")";
    }
}

