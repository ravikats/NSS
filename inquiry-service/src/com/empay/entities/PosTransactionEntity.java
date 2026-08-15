/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.PosTransactionEntity
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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="POS_TRANSACTIONS")
public class PosTransactionEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PTR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="PTR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="PTR_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="PTR_INS_CODE")
    private Integer insCode;
    @Column(name="PTR_INT_CODE")
    private Integer intCode;
    @Column(name="PTR_GEN_STATUS")
    private Integer genStatus;
    @Column(name="PTR_LOCAL_DATE_TIME")
    private LocalDateTime txnLocalDateTime;
    @Column(name="PTR_RET_REF_NUMBER")
    private String rrn;
    @Column(name="PTR_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="PTR_CARD_TYPE")
    private Character cardType;
    @Column(name="PTR_CARD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="PTR_IRD_SER_NUMBER")
    private Integer irdSerNumber;
    @Column(name="PTR_IRF_FIXED")
    private Double irfFixed;
    @Column(name="PTR_IRF_PERCENT")
    private Double irfPercent;
    @Column(name="PTR_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name="PTR_IRF_AMOUNT")
    private Double irfAmount;
    @Column(name="PTR_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="PTR_MERCHANT_ID")
    private String merchantId;
    @Column(name="PTR_TERMINAL_ID")
    private String terminalId;
    @Column(name="PTR_NETWORK")
    private String network;
    @Column(name="PTR_TXN_TYPE")
    private String txnType;
    @Column(name="PTR_REMARKS")
    private String remarks;
    @Column(name="PTR_TXN_CODE")
    private String txnCode;
    @Column(name="PTR_RESP_CODE")
    private String responseCode;
    @Column(name="PTR_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="PTR_IRD")
    private String ird;
    @Column(name="PTR_CARD_NUMBER")
    private String cardNumber;
    @Column(name="PTR_MSG_TYPE_ID")
    private String mti;
    @Column(name="PTR_IRF_MAX_AMOUNT")
    private Double irfMaxAmount;
    @Column(name="PTR_IRF_MIN_AMOUNT")
    private Double irfMinAmount;
    @Column(name="PTR_BANK_CODE")
    private String bankCode;
    @Column(name="PTR_INC_STATUS")
    private String incomingStatus;
    @Column(name="PTR_OUT_STATUS")
    private String outgoingStatus;
    @Column(name="PTR_REV_INDICATOR")
    private Character revIndicator;
    @Column(name="PTR_IRF_AMOUNT_USD")
    private Double irfAmountUSD;
    @Column(name="PTR_PROC_CODE")
    private String procCode;
    @Column(name="PTR_TRL_TYPE")
    private String trlType;
    @Column(name="PTR_ORG_RRN")
    private String originalRRN;
    @Column(name="PTR_TXN_ID")
    private String txnId;
    @Column(name="PTR_NETWORK_DATA")
    private String traceId;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInsCode() {
        return this.insCode;
    }

    public Integer getIntCode() {
        return this.intCode;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public LocalDateTime getTxnLocalDateTime() {
        return this.txnLocalDateTime;
    }

    public String getRrn() {
        return this.rrn;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }

    public Integer getIrdSerNumber() {
        return this.irdSerNumber;
    }

    public Double getIrfFixed() {
        return this.irfFixed;
    }

    public Double getIrfPercent() {
        return this.irfPercent;
    }

    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }

    public Double getIrfAmount() {
        return this.irfAmount;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public String getIrd() {
        return this.ird;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getMti() {
        return this.mti;
    }

    public Double getIrfMaxAmount() {
        return this.irfMaxAmount;
    }

    public Double getIrfMinAmount() {
        return this.irfMinAmount;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public String getIncomingStatus() {
        return this.incomingStatus;
    }

    public String getOutgoingStatus() {
        return this.outgoingStatus;
    }

    public Character getRevIndicator() {
        return this.revIndicator;
    }

    public Double getIrfAmountUSD() {
        return this.irfAmountUSD;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public String getTrlType() {
        return this.trlType;
    }

    public String getOriginalRRN() {
        return this.originalRRN;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInsCode(Integer insCode) {
        this.insCode = insCode;
    }

    public void setIntCode(Integer intCode) {
        this.intCode = intCode;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setTxnLocalDateTime(LocalDateTime txnLocalDateTime) {
        this.txnLocalDateTime = txnLocalDateTime;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setCardDomIntlFlag(Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setIrdSerNumber(Integer irdSerNumber) {
        this.irdSerNumber = irdSerNumber;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public void setIrfPercent(Double irfPercent) {
        this.irfPercent = irfPercent;
    }

    public void setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    public void setIrfAmount(Double irfAmount) {
        this.irfAmount = irfAmount;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setIrd(String ird) {
        this.ird = ird;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setIrfMaxAmount(Double irfMaxAmount) {
        this.irfMaxAmount = irfMaxAmount;
    }

    public void setIrfMinAmount(Double irfMinAmount) {
        this.irfMinAmount = irfMinAmount;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setIncomingStatus(String incomingStatus) {
        this.incomingStatus = incomingStatus;
    }

    public void setOutgoingStatus(String outgoingStatus) {
        this.outgoingStatus = outgoingStatus;
    }

    public void setRevIndicator(Character revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setIrfAmountUSD(Double irfAmountUSD) {
        this.irfAmountUSD = irfAmountUSD;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setTrlType(String trlType) {
        this.trlType = trlType;
    }

    public void setOriginalRRN(String originalRRN) {
        this.originalRRN = originalRRN;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PosTransactionEntity)) {
            return false;
        }
        PosTransactionEntity other = (PosTransactionEntity)o;
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
        Integer this$insCode = this.getInsCode();
        Integer other$insCode = other.getInsCode();
        if (this$insCode == null ? other$insCode != null : !((Object)this$insCode).equals(other$insCode)) {
            return false;
        }
        Integer this$intCode = this.getIntCode();
        Integer other$intCode = other.getIntCode();
        if (this$intCode == null ? other$intCode != null : !((Object)this$intCode).equals(other$intCode)) {
            return false;
        }
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Character this$cardDomIntlFlag = this.getCardDomIntlFlag();
        Character other$cardDomIntlFlag = other.getCardDomIntlFlag();
        if (this$cardDomIntlFlag == null ? other$cardDomIntlFlag != null : !((Object)this$cardDomIntlFlag).equals(other$cardDomIntlFlag)) {
            return false;
        }
        Integer this$irdSerNumber = this.getIrdSerNumber();
        Integer other$irdSerNumber = other.getIrdSerNumber();
        if (this$irdSerNumber == null ? other$irdSerNumber != null : !((Object)this$irdSerNumber).equals(other$irdSerNumber)) {
            return false;
        }
        Double this$irfFixed = this.getIrfFixed();
        Double other$irfFixed = other.getIrfFixed();
        if (this$irfFixed == null ? other$irfFixed != null : !((Object)this$irfFixed).equals(other$irfFixed)) {
            return false;
        }
        Double this$irfPercent = this.getIrfPercent();
        Double other$irfPercent = other.getIrfPercent();
        if (this$irfPercent == null ? other$irfPercent != null : !((Object)this$irfPercent).equals(other$irfPercent)) {
            return false;
        }
        Double this$irfAmount = this.getIrfAmount();
        Double other$irfAmount = other.getIrfAmount();
        if (this$irfAmount == null ? other$irfAmount != null : !((Object)this$irfAmount).equals(other$irfAmount)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$irfMaxAmount = this.getIrfMaxAmount();
        Double other$irfMaxAmount = other.getIrfMaxAmount();
        if (this$irfMaxAmount == null ? other$irfMaxAmount != null : !((Object)this$irfMaxAmount).equals(other$irfMaxAmount)) {
            return false;
        }
        Double this$irfMinAmount = this.getIrfMinAmount();
        Double other$irfMinAmount = other.getIrfMinAmount();
        if (this$irfMinAmount == null ? other$irfMinAmount != null : !((Object)this$irfMinAmount).equals(other$irfMinAmount)) {
            return false;
        }
        Character this$revIndicator = this.getRevIndicator();
        Character other$revIndicator = other.getRevIndicator();
        if (this$revIndicator == null ? other$revIndicator != null : !((Object)this$revIndicator).equals(other$revIndicator)) {
            return false;
        }
        Double this$irfAmountUSD = this.getIrfAmountUSD();
        Double other$irfAmountUSD = other.getIrfAmountUSD();
        if (this$irfAmountUSD == null ? other$irfAmountUSD != null : !((Object)this$irfAmountUSD).equals(other$irfAmountUSD)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        LocalDateTime this$txnLocalDateTime = this.getTxnLocalDateTime();
        LocalDateTime other$txnLocalDateTime = other.getTxnLocalDateTime();
        if (this$txnLocalDateTime == null ? other$txnLocalDateTime != null : !((Object)this$txnLocalDateTime).equals(other$txnLocalDateTime)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        LocalDateTime this$txnDateTime = this.getTxnDateTime();
        LocalDateTime other$txnDateTime = other.getTxnDateTime();
        if (this$txnDateTime == null ? other$txnDateTime != null : !((Object)this$txnDateTime).equals(other$txnDateTime)) {
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
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        if (this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$ird = this.getIrd();
        String other$ird = other.getIrd();
        if (this$ird == null ? other$ird != null : !this$ird.equals(other$ird)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$mti = this.getMti();
        String other$mti = other.getMti();
        if (this$mti == null ? other$mti != null : !this$mti.equals(other$mti)) {
            return false;
        }
        String this$bankCode = this.getBankCode();
        String other$bankCode = other.getBankCode();
        if (this$bankCode == null ? other$bankCode != null : !this$bankCode.equals(other$bankCode)) {
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
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$trlType = this.getTrlType();
        String other$trlType = other.getTrlType();
        if (this$trlType == null ? other$trlType != null : !this$trlType.equals(other$trlType)) {
            return false;
        }
        String this$originalRRN = this.getOriginalRRN();
        String other$originalRRN = other.getOriginalRRN();
        if (this$originalRRN == null ? other$originalRRN != null : !this$originalRRN.equals(other$originalRRN)) {
            return false;
        }
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
            return false;
        }
        String this$traceId = this.getTraceId();
        String other$traceId = other.getTraceId();
        return !(this$traceId == null ? other$traceId != null : !this$traceId.equals(other$traceId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PosTransactionEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $insCode = this.getInsCode();
        result = result * 59 + ($insCode == null ? 43 : ((Object)$insCode).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Integer $irdSerNumber = this.getIrdSerNumber();
        result = result * 59 + ($irdSerNumber == null ? 43 : ((Object)$irdSerNumber).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        Double $irfPercent = this.getIrfPercent();
        result = result * 59 + ($irfPercent == null ? 43 : ((Object)$irfPercent).hashCode());
        Double $irfAmount = this.getIrfAmount();
        result = result * 59 + ($irfAmount == null ? 43 : ((Object)$irfAmount).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $irfMaxAmount = this.getIrfMaxAmount();
        result = result * 59 + ($irfMaxAmount == null ? 43 : ((Object)$irfMaxAmount).hashCode());
        Double $irfMinAmount = this.getIrfMinAmount();
        result = result * 59 + ($irfMinAmount == null ? 43 : ((Object)$irfMinAmount).hashCode());
        Character $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : ((Object)$revIndicator).hashCode());
        Double $irfAmountUSD = this.getIrfAmountUSD();
        result = result * 59 + ($irfAmountUSD == null ? 43 : ((Object)$irfAmountUSD).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        LocalDateTime $txnLocalDateTime = this.getTxnLocalDateTime();
        result = result * 59 + ($txnLocalDateTime == null ? 43 : ((Object)$txnLocalDateTime).hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        LocalDateTime $txnDateTime = this.getTxnDateTime();
        result = result * 59 + ($txnDateTime == null ? 43 : ((Object)$txnDateTime).hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $ird = this.getIrd();
        result = result * 59 + ($ird == null ? 43 : $ird.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $mti = this.getMti();
        result = result * 59 + ($mti == null ? 43 : $mti.hashCode());
        String $bankCode = this.getBankCode();
        result = result * 59 + ($bankCode == null ? 43 : $bankCode.hashCode());
        String $incomingStatus = this.getIncomingStatus();
        result = result * 59 + ($incomingStatus == null ? 43 : $incomingStatus.hashCode());
        String $outgoingStatus = this.getOutgoingStatus();
        result = result * 59 + ($outgoingStatus == null ? 43 : $outgoingStatus.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $trlType = this.getTrlType();
        result = result * 59 + ($trlType == null ? 43 : $trlType.hashCode());
        String $originalRRN = this.getOriginalRRN();
        result = result * 59 + ($originalRRN == null ? 43 : $originalRRN.hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $traceId = this.getTraceId();
        result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
        return result;
    }

    public String toString() {
        return "PosTransactionEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", genStatus=" + this.getGenStatus() + ", txnLocalDateTime=" + String.valueOf(this.getTxnLocalDateTime()) + ", rrn=" + this.getRrn() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", irdSerNumber=" + this.getIrdSerNumber() + ", irfFixed=" + this.getIrfFixed() + ", irfPercent=" + this.getIrfPercent() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", irfAmount=" + this.getIrfAmount() + ", jobNumber=" + this.getJobNumber() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", network=" + this.getNetwork() + ", txnType=" + this.getTxnType() + ", remarks=" + this.getRemarks() + ", txnCode=" + this.getTxnCode() + ", responseCode=" + this.getResponseCode() + ", txnAmount=" + this.getTxnAmount() + ", ird=" + this.getIrd() + ", cardNumber=" + this.getCardNumber() + ", mti=" + this.getMti() + ", irfMaxAmount=" + this.getIrfMaxAmount() + ", irfMinAmount=" + this.getIrfMinAmount() + ", bankCode=" + this.getBankCode() + ", incomingStatus=" + this.getIncomingStatus() + ", outgoingStatus=" + this.getOutgoingStatus() + ", revIndicator=" + this.getRevIndicator() + ", irfAmountUSD=" + this.getIrfAmountUSD() + ", procCode=" + this.getProcCode() + ", trlType=" + this.getTrlType() + ", originalRRN=" + this.getOriginalRRN() + ", txnId=" + this.getTxnId() + ", traceId=" + this.getTraceId() + ")";
    }
}

