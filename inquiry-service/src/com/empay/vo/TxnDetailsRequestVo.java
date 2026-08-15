/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.TxnDetailsRequestVo
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.empay.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TxnDetailsRequestVo {
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String fromDate;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String toDate;
    @Size(min=1, max=60, message="Invalid merchantId; Please provide valid merchantId.")
    private @Size(min=1, max=60, message="Invalid merchantId; Please provide valid merchantId.") String merchantId;
    @Size(min=1, max=60, message="Invalid terminalId; Please provide valid terminalId.")
    private @Size(min=1, max=60, message="Invalid terminalId; Please provide valid terminalId.") String terminalId;
    @Pattern(regexp="^(?i)(MASTERCARD|VISA|JAYWAN|AMEX|UAESWITCH|VISA SMS|RUPAY SMS|omannet_tps)$", message="Invalid network; Please provide valid network.")
    private @Pattern(regexp="^(?i)(MASTERCARD|VISA|JAYWAN|AMEX|UAESWITCH|VISA SMS|RUPAY SMS|omannet_tps)$", message="Invalid network; Please provide valid network.") String channel;
    @Pattern(regexp="^(?i)(Sale|Cash on POS|Cash Adv|Cashback|Refund|Cash withdrawal|Balance inquiry|Sale CB|Cash on POS CB|Cash Adv CB|Cashback CB|Refund CB|Sale Reversal|Cash@POS Reversal|Cash Adv Reversal|Cashback Reversal|Refund Reversal|Cash withdrawal Reversal|Sale CB Reversal|Cash@POS CB Reversal|Cash Adv CB Reversal|Cashback CB Reversal|Refund CB Reversal|Mini Statements|Fee|Loan Sharing|Preauth|Preauth incremental|Void|Reversal|Preauth Complete)$", message="Invalid transactionType; Please provide valid transaction type.")
    private @Pattern(regexp="^(?i)(Sale|Cash on POS|Cash Adv|Cashback|Refund|Cash withdrawal|Balance inquiry|Sale CB|Cash on POS CB|Cash Adv CB|Cashback CB|Refund CB|Sale Reversal|Cash@POS Reversal|Cash Adv Reversal|Cashback Reversal|Refund Reversal|Cash withdrawal Reversal|Sale CB Reversal|Cash@POS CB Reversal|Cash Adv CB Reversal|Cashback CB Reversal|Refund CB Reversal|Mini Statements|Fee|Loan Sharing|Preauth|Preauth incremental|Void|Reversal|Preauth Complete)$", message="Invalid transactionType; Please provide valid transaction type.") String transactionType;
    @Size(min=12, max=18, message="Invalid rrn; Please provide a valid rrn")
    private @Size(min=12, max=18, message="Invalid rrn; Please provide a valid rrn") String rrn;
    @Pattern(regexp="^(?i)(SUCCESS|FAILED)$", message="Invalid status; Please provide valid status.")
    private @Pattern(regexp="^(?i)(SUCCESS|FAILED)$", message="Invalid status; Please provide valid status.") String status;
    @Size(min=1, max=4)
    private @Size(min=1, max=4) String mti;
    @Pattern(regexp="^(?i)(DOMESTIC|INTERNATIONAL)$", message="Invalid domOrIntl; Please provide valid domOrIntl.")
    private @Pattern(regexp="^(?i)(DOMESTIC|INTERNATIONAL)$", message="Invalid domOrIntl; Please provide valid domOrIntl.") String domIntlFlag;
    @Size(min=1, max=4)
    private @Size(min=1, max=4) String bankId;
    @Pattern(regexp="^(?i)(PENDING|COMPLETED|REJECTED|CB RECEIVED|RR RECEIVED)$", message="Invalid incomingStatus; Please provide valid incomingStatus.")
    private @Pattern(regexp="^(?i)(PENDING|COMPLETED|REJECTED|CB RECEIVED|RR RECEIVED)$", message="Invalid incomingStatus; Please provide valid incomingStatus.") String incomingStatus;
    @Pattern(regexp="^(?i)(COMPLETED|MARKED FOR OUTGOING|PENDING)$", message="Invalid outgoingStatus; Please provide valid outgoingStatus.")
    private @Pattern(regexp="^(?i)(COMPLETED|MARKED FOR OUTGOING|PENDING)$", message="Invalid outgoingStatus; Please provide valid outgoingStatus.") String outgoingStatus;
    @Size(min=1, max=60, message="Invalid merchantId; Please provide valid merchantId.")
    private @Size(min=1, max=60, message="Invalid merchantId; Please provide valid merchantId.") String cardAccIdCode;
    @Size(min=1, max=60, message="Invalid terminalId; Please provide valid terminalId.")
    private @Size(min=1, max=60, message="Invalid terminalId; Please provide valid terminalId.") String cardAccTerminalId;
    @Size(min=12, max=18, message="Invalid rrn; Please provide a valid rrn")
    private @Size(min=12, max=18, message="Invalid rrn; Please provide a valid rrn") String retrievalRefNo;

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public String getRrn() {
        return this.rrn;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMti() {
        return this.mti;
    }

    public String getDomIntlFlag() {
        return this.domIntlFlag;
    }

    public String getBankId() {
        return this.bankId;
    }

    public String getIncomingStatus() {
        return this.incomingStatus;
    }

    public String getOutgoingStatus() {
        return this.outgoingStatus;
    }

    public String getCardAccIdCode() {
        return this.cardAccIdCode;
    }

    public String getCardAccTerminalId() {
        return this.cardAccTerminalId;
    }

    public String getRetrievalRefNo() {
        return this.retrievalRefNo;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setDomIntlFlag(String domIntlFlag) {
        this.domIntlFlag = domIntlFlag;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public void setIncomingStatus(String incomingStatus) {
        this.incomingStatus = incomingStatus;
    }

    public void setOutgoingStatus(String outgoingStatus) {
        this.outgoingStatus = outgoingStatus;
    }

    public void setCardAccIdCode(String cardAccIdCode) {
        this.cardAccIdCode = cardAccIdCode;
    }

    public void setCardAccTerminalId(String cardAccTerminalId) {
        this.cardAccTerminalId = cardAccTerminalId;
    }

    public void setRetrievalRefNo(String retrievalRefNo) {
        this.retrievalRefNo = retrievalRefNo;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TxnDetailsRequestVo)) {
            return false;
        }
        TxnDetailsRequestVo other = (TxnDetailsRequestVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$fromDate = this.getFromDate();
        String other$fromDate = other.getFromDate();
        if (this$fromDate == null ? other$fromDate != null : !this$fromDate.equals(other$fromDate)) {
            return false;
        }
        String this$toDate = this.getToDate();
        String other$toDate = other.getToDate();
        if (this$toDate == null ? other$toDate != null : !this$toDate.equals(other$toDate)) {
            return false;
        }
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$channel = this.getChannel();
        String other$channel = other.getChannel();
        if (this$channel == null ? other$channel != null : !this$channel.equals(other$channel)) {
            return false;
        }
        String this$transactionType = this.getTransactionType();
        String other$transactionType = other.getTransactionType();
        if (this$transactionType == null ? other$transactionType != null : !this$transactionType.equals(other$transactionType)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$mti = this.getMti();
        String other$mti = other.getMti();
        if (this$mti == null ? other$mti != null : !this$mti.equals(other$mti)) {
            return false;
        }
        String this$domIntlFlag = this.getDomIntlFlag();
        String other$domIntlFlag = other.getDomIntlFlag();
        if (this$domIntlFlag == null ? other$domIntlFlag != null : !this$domIntlFlag.equals(other$domIntlFlag)) {
            return false;
        }
        String this$bankId = this.getBankId();
        String other$bankId = other.getBankId();
        if (this$bankId == null ? other$bankId != null : !this$bankId.equals(other$bankId)) {
            return false;
        }
        String this$incomingStatus = this.getIncomingStatus();
        String other$incomingStatus = other.getIncomingStatus();
        if (this$incomingStatus == null ? other$incomingStatus != null : !this$incomingStatus.equals(other$incomingStatus)) {
            return false;
        }
        String this$outgoingStatus = this.getOutgoingStatus();
        String other$outgoingStatus = other.getOutgoingStatus();
        if (this$outgoingStatus == null ? other$outgoingStatus != null : !this$outgoingStatus.equals(other$outgoingStatus)) {
            return false;
        }
        String this$cardAccIdCode = this.getCardAccIdCode();
        String other$cardAccIdCode = other.getCardAccIdCode();
        if (this$cardAccIdCode == null ? other$cardAccIdCode != null : !this$cardAccIdCode.equals(other$cardAccIdCode)) {
            return false;
        }
        String this$cardAccTerminalId = this.getCardAccTerminalId();
        String other$cardAccTerminalId = other.getCardAccTerminalId();
        if (this$cardAccTerminalId == null ? other$cardAccTerminalId != null : !this$cardAccTerminalId.equals(other$cardAccTerminalId)) {
            return false;
        }
        String this$retrievalRefNo = this.getRetrievalRefNo();
        String other$retrievalRefNo = other.getRetrievalRefNo();
        return !(this$retrievalRefNo == null ? other$retrievalRefNo != null : !this$retrievalRefNo.equals(other$retrievalRefNo));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TxnDetailsRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $fromDate = this.getFromDate();
        result = result * 59 + ($fromDate == null ? 43 : $fromDate.hashCode());
        String $toDate = this.getToDate();
        result = result * 59 + ($toDate == null ? 43 : $toDate.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $channel = this.getChannel();
        result = result * 59 + ($channel == null ? 43 : $channel.hashCode());
        String $transactionType = this.getTransactionType();
        result = result * 59 + ($transactionType == null ? 43 : $transactionType.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $mti = this.getMti();
        result = result * 59 + ($mti == null ? 43 : $mti.hashCode());
        String $domIntlFlag = this.getDomIntlFlag();
        result = result * 59 + ($domIntlFlag == null ? 43 : $domIntlFlag.hashCode());
        String $bankId = this.getBankId();
        result = result * 59 + ($bankId == null ? 43 : $bankId.hashCode());
        String $incomingStatus = this.getIncomingStatus();
        result = result * 59 + ($incomingStatus == null ? 43 : $incomingStatus.hashCode());
        String $outgoingStatus = this.getOutgoingStatus();
        result = result * 59 + ($outgoingStatus == null ? 43 : $outgoingStatus.hashCode());
        String $cardAccIdCode = this.getCardAccIdCode();
        result = result * 59 + ($cardAccIdCode == null ? 43 : $cardAccIdCode.hashCode());
        String $cardAccTerminalId = this.getCardAccTerminalId();
        result = result * 59 + ($cardAccTerminalId == null ? 43 : $cardAccTerminalId.hashCode());
        String $retrievalRefNo = this.getRetrievalRefNo();
        result = result * 59 + ($retrievalRefNo == null ? 43 : $retrievalRefNo.hashCode());
        return result;
    }

    public String toString() {
        return "TxnDetailsRequestVo(fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", channel=" + this.getChannel() + ", transactionType=" + this.getTransactionType() + ", rrn=" + this.getRrn() + ", status=" + this.getStatus() + ", mti=" + this.getMti() + ", domIntlFlag=" + this.getDomIntlFlag() + ", bankId=" + this.getBankId() + ", incomingStatus=" + this.getIncomingStatus() + ", outgoingStatus=" + this.getOutgoingStatus() + ", cardAccIdCode=" + this.getCardAccIdCode() + ", cardAccTerminalId=" + this.getCardAccTerminalId() + ", retrievalRefNo=" + this.getRetrievalRefNo() + ")";
    }
}

