/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.VisaNetworkDataEntity
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
@Table(name="VISA_NETWORK_DATA")
public class VisaNetworkDataEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="VND_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="VND_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="VND_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="VND_INS_CODE")
    private Integer insCode;
    @Column(name="VND_INT_CODE")
    private Integer interfaceCode;
    @Column(name="VND_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="VND_DESTINATION_ID")
    private String destinationId;
    @Column(name="VND_SOURCE_ID")
    private String sourceId;
    @Column(name="VND_CAS_ID")
    private String casId;
    @Column(name="VND_ORIGIN_ID")
    private Character originId;
    @Column(name="VND_TXN_CODE")
    private String txnCode;
    @Column(name="VND_TCQ_ID")
    private Character tcqId;
    @Column(name="VND_CENTER_PROC_DATE")
    private LocalDate centerProcDate;
    @Column(name="VND_TXN_ID")
    private String txnId;
    @Column(name="VND_CARD_NUMBER")
    private String cardNumber;
    @Column(name="VND_ACQ_REF_NUMBER")
    private String acqRefNumber;
    @Column(name="VND_ME_CODE")
    private String merchantId;
    @Column(name="VND_TERMINAL_ID")
    private String terminalId;
    @Column(name="VND_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="VND_TXN_CURRENCY")
    private String txnCurrency;
    @Column(name="VND_SETL_FLAG")
    private Character setlFlag;
    @Column(name="VND_SERVICE_ID")
    private String serviceId;
    @Column(name="VND_SETL_CURRENCY")
    private String setlCurrency;
    @Column(name="VND_SRE_ID")
    private String sreId;
    @Column(name="VND_FUND_TRANSFER_ID")
    private String fundTransferId;
    @Column(name="VND_SETL_AMOUNT_INCHG")
    private Double setlAmountInchg;
    @Column(name="VND_SETL_AMOUNT_SIGN")
    private Character setlAmountSign;
    @Column(name="VND_USAGE_CODE")
    private Character usageCode;
    @Column(name="VND_RECLASS_IND")
    private Character reClassInd;
    @Column(name="VND_REQ_PAY_SERVICE")
    private Character reqPayService;
    @Column(name="VND_INCHG_FEE_AMOUNT")
    private Double inchgFeeAmount;
    @Column(name="VND_INCHG_FEE_SIGN")
    private Character inchgFeeSign;
    @Column(name="VND_MVV")
    private String mvv;
    @Column(name="VND_FEE_PRGM_IND")
    private String feePrgmInd;
    @Column(name="VND_FEE_DESC")
    private String feeDesc;
    @Column(name="VND_CONV_DATE")
    private LocalDate convDate;
    @Column(name="VND_SETL_DATE")
    private LocalDate setlDate;
    @Column(name="VND_BASII_FILE_ID")
    private String baseIIFileId;
    @Column(name="VND_FX_INDICATOR")
    private Character fxInd;
    @Column(name="VND_RATE_TABLE_ID")
    private String rateTableId;
    @Column(name="VND_TOKEN_RESP")
    private Character tokenResp;
    @Column(name="VND_AGGREMENT_ID")
    private String aggrementId;
    @Column(name="VND_ENC_CARD_NUMBER")
    private String encCardNumber;
    @Column(name="VND_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name="VND_IRF_MATCH")
    private Character matchStatus;
    @Column(name="VND_REF_SER_NUMBER")
    private Integer refSerNumber;
    @Column(name="VND_IRF_DIFF")
    private Double irfDiff;
    @Column(name="VND_BUSS_DATE")
    private LocalDate bussDate;

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

    public Integer getInterfaceCode() {
        return this.interfaceCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public String getDestinationId() {
        return this.destinationId;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public String getCasId() {
        return this.casId;
    }

    public Character getOriginId() {
        return this.originId;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public Character getTcqId() {
        return this.tcqId;
    }

    public LocalDate getCenterProcDate() {
        return this.centerProcDate;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getAcqRefNumber() {
        return this.acqRefNumber;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    public Character getSetlFlag() {
        return this.setlFlag;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public String getSetlCurrency() {
        return this.setlCurrency;
    }

    public String getSreId() {
        return this.sreId;
    }

    public String getFundTransferId() {
        return this.fundTransferId;
    }

    public Double getSetlAmountInchg() {
        return this.setlAmountInchg;
    }

    public Character getSetlAmountSign() {
        return this.setlAmountSign;
    }

    public Character getUsageCode() {
        return this.usageCode;
    }

    public Character getReClassInd() {
        return this.reClassInd;
    }

    public Character getReqPayService() {
        return this.reqPayService;
    }

    public Double getInchgFeeAmount() {
        return this.inchgFeeAmount;
    }

    public Character getInchgFeeSign() {
        return this.inchgFeeSign;
    }

    public String getMvv() {
        return this.mvv;
    }

    public String getFeePrgmInd() {
        return this.feePrgmInd;
    }

    public String getFeeDesc() {
        return this.feeDesc;
    }

    public LocalDate getConvDate() {
        return this.convDate;
    }

    public LocalDate getSetlDate() {
        return this.setlDate;
    }

    public String getBaseIIFileId() {
        return this.baseIIFileId;
    }

    public Character getFxInd() {
        return this.fxInd;
    }

    public String getRateTableId() {
        return this.rateTableId;
    }

    public Character getTokenResp() {
        return this.tokenResp;
    }

    public String getAggrementId() {
        return this.aggrementId;
    }

    public String getEncCardNumber() {
        return this.encCardNumber;
    }

    public String getRetRefNumber() {
        return this.retRefNumber;
    }

    public Character getMatchStatus() {
        return this.matchStatus;
    }

    public Integer getRefSerNumber() {
        return this.refSerNumber;
    }

    public Double getIrfDiff() {
        return this.irfDiff;
    }

    public LocalDate getBussDate() {
        return this.bussDate;
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

    public void setInterfaceCode(Integer interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setCasId(String casId) {
        this.casId = casId;
    }

    public void setOriginId(Character originId) {
        this.originId = originId;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setTcqId(Character tcqId) {
        this.tcqId = tcqId;
    }

    public void setCenterProcDate(LocalDate centerProcDate) {
        this.centerProcDate = centerProcDate;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAcqRefNumber(String acqRefNumber) {
        this.acqRefNumber = acqRefNumber;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public void setSetlFlag(Character setlFlag) {
        this.setlFlag = setlFlag;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setSetlCurrency(String setlCurrency) {
        this.setlCurrency = setlCurrency;
    }

    public void setSreId(String sreId) {
        this.sreId = sreId;
    }

    public void setFundTransferId(String fundTransferId) {
        this.fundTransferId = fundTransferId;
    }

    public void setSetlAmountInchg(Double setlAmountInchg) {
        this.setlAmountInchg = setlAmountInchg;
    }

    public void setSetlAmountSign(Character setlAmountSign) {
        this.setlAmountSign = setlAmountSign;
    }

    public void setUsageCode(Character usageCode) {
        this.usageCode = usageCode;
    }

    public void setReClassInd(Character reClassInd) {
        this.reClassInd = reClassInd;
    }

    public void setReqPayService(Character reqPayService) {
        this.reqPayService = reqPayService;
    }

    public void setInchgFeeAmount(Double inchgFeeAmount) {
        this.inchgFeeAmount = inchgFeeAmount;
    }

    public void setInchgFeeSign(Character inchgFeeSign) {
        this.inchgFeeSign = inchgFeeSign;
    }

    public void setMvv(String mvv) {
        this.mvv = mvv;
    }

    public void setFeePrgmInd(String feePrgmInd) {
        this.feePrgmInd = feePrgmInd;
    }

    public void setFeeDesc(String feeDesc) {
        this.feeDesc = feeDesc;
    }

    public void setConvDate(LocalDate convDate) {
        this.convDate = convDate;
    }

    public void setSetlDate(LocalDate setlDate) {
        this.setlDate = setlDate;
    }

    public void setBaseIIFileId(String baseIIFileId) {
        this.baseIIFileId = baseIIFileId;
    }

    public void setFxInd(Character fxInd) {
        this.fxInd = fxInd;
    }

    public void setRateTableId(String rateTableId) {
        this.rateTableId = rateTableId;
    }

    public void setTokenResp(Character tokenResp) {
        this.tokenResp = tokenResp;
    }

    public void setAggrementId(String aggrementId) {
        this.aggrementId = aggrementId;
    }

    public void setEncCardNumber(String encCardNumber) {
        this.encCardNumber = encCardNumber;
    }

    public void setRetRefNumber(String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }

    public void setMatchStatus(Character matchStatus) {
        this.matchStatus = matchStatus;
    }

    public void setRefSerNumber(Integer refSerNumber) {
        this.refSerNumber = refSerNumber;
    }

    public void setIrfDiff(Double irfDiff) {
        this.irfDiff = irfDiff;
    }

    public void setBussDate(LocalDate bussDate) {
        this.bussDate = bussDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaNetworkDataEntity)) {
            return false;
        }
        VisaNetworkDataEntity other = (VisaNetworkDataEntity)o;
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
        Integer this$interfaceCode = this.getInterfaceCode();
        Integer other$interfaceCode = other.getInterfaceCode();
        if (this$interfaceCode == null ? other$interfaceCode != null : !((Object)this$interfaceCode).equals(other$interfaceCode)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Character this$originId = this.getOriginId();
        Character other$originId = other.getOriginId();
        if (this$originId == null ? other$originId != null : !((Object)this$originId).equals(other$originId)) {
            return false;
        }
        Character this$tcqId = this.getTcqId();
        Character other$tcqId = other.getTcqId();
        if (this$tcqId == null ? other$tcqId != null : !((Object)this$tcqId).equals(other$tcqId)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Character this$setlFlag = this.getSetlFlag();
        Character other$setlFlag = other.getSetlFlag();
        if (this$setlFlag == null ? other$setlFlag != null : !((Object)this$setlFlag).equals(other$setlFlag)) {
            return false;
        }
        Double this$setlAmountInchg = this.getSetlAmountInchg();
        Double other$setlAmountInchg = other.getSetlAmountInchg();
        if (this$setlAmountInchg == null ? other$setlAmountInchg != null : !((Object)this$setlAmountInchg).equals(other$setlAmountInchg)) {
            return false;
        }
        Character this$setlAmountSign = this.getSetlAmountSign();
        Character other$setlAmountSign = other.getSetlAmountSign();
        if (this$setlAmountSign == null ? other$setlAmountSign != null : !((Object)this$setlAmountSign).equals(other$setlAmountSign)) {
            return false;
        }
        Character this$usageCode = this.getUsageCode();
        Character other$usageCode = other.getUsageCode();
        if (this$usageCode == null ? other$usageCode != null : !((Object)this$usageCode).equals(other$usageCode)) {
            return false;
        }
        Character this$reClassInd = this.getReClassInd();
        Character other$reClassInd = other.getReClassInd();
        if (this$reClassInd == null ? other$reClassInd != null : !((Object)this$reClassInd).equals(other$reClassInd)) {
            return false;
        }
        Character this$reqPayService = this.getReqPayService();
        Character other$reqPayService = other.getReqPayService();
        if (this$reqPayService == null ? other$reqPayService != null : !((Object)this$reqPayService).equals(other$reqPayService)) {
            return false;
        }
        Double this$inchgFeeAmount = this.getInchgFeeAmount();
        Double other$inchgFeeAmount = other.getInchgFeeAmount();
        if (this$inchgFeeAmount == null ? other$inchgFeeAmount != null : !((Object)this$inchgFeeAmount).equals(other$inchgFeeAmount)) {
            return false;
        }
        Character this$inchgFeeSign = this.getInchgFeeSign();
        Character other$inchgFeeSign = other.getInchgFeeSign();
        if (this$inchgFeeSign == null ? other$inchgFeeSign != null : !((Object)this$inchgFeeSign).equals(other$inchgFeeSign)) {
            return false;
        }
        Character this$fxInd = this.getFxInd();
        Character other$fxInd = other.getFxInd();
        if (this$fxInd == null ? other$fxInd != null : !((Object)this$fxInd).equals(other$fxInd)) {
            return false;
        }
        Character this$tokenResp = this.getTokenResp();
        Character other$tokenResp = other.getTokenResp();
        if (this$tokenResp == null ? other$tokenResp != null : !((Object)this$tokenResp).equals(other$tokenResp)) {
            return false;
        }
        Character this$matchStatus = this.getMatchStatus();
        Character other$matchStatus = other.getMatchStatus();
        if (this$matchStatus == null ? other$matchStatus != null : !((Object)this$matchStatus).equals(other$matchStatus)) {
            return false;
        }
        Integer this$refSerNumber = this.getRefSerNumber();
        Integer other$refSerNumber = other.getRefSerNumber();
        if (this$refSerNumber == null ? other$refSerNumber != null : !((Object)this$refSerNumber).equals(other$refSerNumber)) {
            return false;
        }
        Double this$irfDiff = this.getIrfDiff();
        Double other$irfDiff = other.getIrfDiff();
        if (this$irfDiff == null ? other$irfDiff != null : !((Object)this$irfDiff).equals(other$irfDiff)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$destinationId = this.getDestinationId();
        String other$destinationId = other.getDestinationId();
        if (this$destinationId == null ? other$destinationId != null : !this$destinationId.equals(other$destinationId)) {
            return false;
        }
        String this$sourceId = this.getSourceId();
        String other$sourceId = other.getSourceId();
        if (this$sourceId == null ? other$sourceId != null : !this$sourceId.equals(other$sourceId)) {
            return false;
        }
        String this$casId = this.getCasId();
        String other$casId = other.getCasId();
        if (this$casId == null ? other$casId != null : !this$casId.equals(other$casId)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        LocalDate this$centerProcDate = this.getCenterProcDate();
        LocalDate other$centerProcDate = other.getCenterProcDate();
        if (this$centerProcDate == null ? other$centerProcDate != null : !((Object)this$centerProcDate).equals(other$centerProcDate)) {
            return false;
        }
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$acqRefNumber = this.getAcqRefNumber();
        String other$acqRefNumber = other.getAcqRefNumber();
        if (this$acqRefNumber == null ? other$acqRefNumber != null : !this$acqRefNumber.equals(other$acqRefNumber)) {
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
        String this$txnCurrency = this.getTxnCurrency();
        String other$txnCurrency = other.getTxnCurrency();
        if (this$txnCurrency == null ? other$txnCurrency != null : !this$txnCurrency.equals(other$txnCurrency)) {
            return false;
        }
        String this$serviceId = this.getServiceId();
        String other$serviceId = other.getServiceId();
        if (this$serviceId == null ? other$serviceId != null : !this$serviceId.equals(other$serviceId)) {
            return false;
        }
        String this$setlCurrency = this.getSetlCurrency();
        String other$setlCurrency = other.getSetlCurrency();
        if (this$setlCurrency == null ? other$setlCurrency != null : !this$setlCurrency.equals(other$setlCurrency)) {
            return false;
        }
        String this$sreId = this.getSreId();
        String other$sreId = other.getSreId();
        if (this$sreId == null ? other$sreId != null : !this$sreId.equals(other$sreId)) {
            return false;
        }
        String this$fundTransferId = this.getFundTransferId();
        String other$fundTransferId = other.getFundTransferId();
        if (this$fundTransferId == null ? other$fundTransferId != null : !this$fundTransferId.equals(other$fundTransferId)) {
            return false;
        }
        String this$mvv = this.getMvv();
        String other$mvv = other.getMvv();
        if (this$mvv == null ? other$mvv != null : !this$mvv.equals(other$mvv)) {
            return false;
        }
        String this$feePrgmInd = this.getFeePrgmInd();
        String other$feePrgmInd = other.getFeePrgmInd();
        if (this$feePrgmInd == null ? other$feePrgmInd != null : !this$feePrgmInd.equals(other$feePrgmInd)) {
            return false;
        }
        String this$feeDesc = this.getFeeDesc();
        String other$feeDesc = other.getFeeDesc();
        if (this$feeDesc == null ? other$feeDesc != null : !this$feeDesc.equals(other$feeDesc)) {
            return false;
        }
        LocalDate this$convDate = this.getConvDate();
        LocalDate other$convDate = other.getConvDate();
        if (this$convDate == null ? other$convDate != null : !((Object)this$convDate).equals(other$convDate)) {
            return false;
        }
        LocalDate this$setlDate = this.getSetlDate();
        LocalDate other$setlDate = other.getSetlDate();
        if (this$setlDate == null ? other$setlDate != null : !((Object)this$setlDate).equals(other$setlDate)) {
            return false;
        }
        String this$baseIIFileId = this.getBaseIIFileId();
        String other$baseIIFileId = other.getBaseIIFileId();
        if (this$baseIIFileId == null ? other$baseIIFileId != null : !this$baseIIFileId.equals(other$baseIIFileId)) {
            return false;
        }
        String this$rateTableId = this.getRateTableId();
        String other$rateTableId = other.getRateTableId();
        if (this$rateTableId == null ? other$rateTableId != null : !this$rateTableId.equals(other$rateTableId)) {
            return false;
        }
        String this$aggrementId = this.getAggrementId();
        String other$aggrementId = other.getAggrementId();
        if (this$aggrementId == null ? other$aggrementId != null : !this$aggrementId.equals(other$aggrementId)) {
            return false;
        }
        String this$encCardNumber = this.getEncCardNumber();
        String other$encCardNumber = other.getEncCardNumber();
        if (this$encCardNumber == null ? other$encCardNumber != null : !this$encCardNumber.equals(other$encCardNumber)) {
            return false;
        }
        String this$retRefNumber = this.getRetRefNumber();
        String other$retRefNumber = other.getRetRefNumber();
        if (this$retRefNumber == null ? other$retRefNumber != null : !this$retRefNumber.equals(other$retRefNumber)) {
            return false;
        }
        LocalDate this$bussDate = this.getBussDate();
        LocalDate other$bussDate = other.getBussDate();
        return !(this$bussDate == null ? other$bussDate != null : !((Object)this$bussDate).equals(other$bussDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof VisaNetworkDataEntity;
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
        Integer $interfaceCode = this.getInterfaceCode();
        result = result * 59 + ($interfaceCode == null ? 43 : ((Object)$interfaceCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Character $originId = this.getOriginId();
        result = result * 59 + ($originId == null ? 43 : ((Object)$originId).hashCode());
        Character $tcqId = this.getTcqId();
        result = result * 59 + ($tcqId == null ? 43 : ((Object)$tcqId).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Character $setlFlag = this.getSetlFlag();
        result = result * 59 + ($setlFlag == null ? 43 : ((Object)$setlFlag).hashCode());
        Double $setlAmountInchg = this.getSetlAmountInchg();
        result = result * 59 + ($setlAmountInchg == null ? 43 : ((Object)$setlAmountInchg).hashCode());
        Character $setlAmountSign = this.getSetlAmountSign();
        result = result * 59 + ($setlAmountSign == null ? 43 : ((Object)$setlAmountSign).hashCode());
        Character $usageCode = this.getUsageCode();
        result = result * 59 + ($usageCode == null ? 43 : ((Object)$usageCode).hashCode());
        Character $reClassInd = this.getReClassInd();
        result = result * 59 + ($reClassInd == null ? 43 : ((Object)$reClassInd).hashCode());
        Character $reqPayService = this.getReqPayService();
        result = result * 59 + ($reqPayService == null ? 43 : ((Object)$reqPayService).hashCode());
        Double $inchgFeeAmount = this.getInchgFeeAmount();
        result = result * 59 + ($inchgFeeAmount == null ? 43 : ((Object)$inchgFeeAmount).hashCode());
        Character $inchgFeeSign = this.getInchgFeeSign();
        result = result * 59 + ($inchgFeeSign == null ? 43 : ((Object)$inchgFeeSign).hashCode());
        Character $fxInd = this.getFxInd();
        result = result * 59 + ($fxInd == null ? 43 : ((Object)$fxInd).hashCode());
        Character $tokenResp = this.getTokenResp();
        result = result * 59 + ($tokenResp == null ? 43 : ((Object)$tokenResp).hashCode());
        Character $matchStatus = this.getMatchStatus();
        result = result * 59 + ($matchStatus == null ? 43 : ((Object)$matchStatus).hashCode());
        Integer $refSerNumber = this.getRefSerNumber();
        result = result * 59 + ($refSerNumber == null ? 43 : ((Object)$refSerNumber).hashCode());
        Double $irfDiff = this.getIrfDiff();
        result = result * 59 + ($irfDiff == null ? 43 : ((Object)$irfDiff).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $destinationId = this.getDestinationId();
        result = result * 59 + ($destinationId == null ? 43 : $destinationId.hashCode());
        String $sourceId = this.getSourceId();
        result = result * 59 + ($sourceId == null ? 43 : $sourceId.hashCode());
        String $casId = this.getCasId();
        result = result * 59 + ($casId == null ? 43 : $casId.hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        LocalDate $centerProcDate = this.getCenterProcDate();
        result = result * 59 + ($centerProcDate == null ? 43 : ((Object)$centerProcDate).hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $acqRefNumber = this.getAcqRefNumber();
        result = result * 59 + ($acqRefNumber == null ? 43 : $acqRefNumber.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $txnCurrency = this.getTxnCurrency();
        result = result * 59 + ($txnCurrency == null ? 43 : $txnCurrency.hashCode());
        String $serviceId = this.getServiceId();
        result = result * 59 + ($serviceId == null ? 43 : $serviceId.hashCode());
        String $setlCurrency = this.getSetlCurrency();
        result = result * 59 + ($setlCurrency == null ? 43 : $setlCurrency.hashCode());
        String $sreId = this.getSreId();
        result = result * 59 + ($sreId == null ? 43 : $sreId.hashCode());
        String $fundTransferId = this.getFundTransferId();
        result = result * 59 + ($fundTransferId == null ? 43 : $fundTransferId.hashCode());
        String $mvv = this.getMvv();
        result = result * 59 + ($mvv == null ? 43 : $mvv.hashCode());
        String $feePrgmInd = this.getFeePrgmInd();
        result = result * 59 + ($feePrgmInd == null ? 43 : $feePrgmInd.hashCode());
        String $feeDesc = this.getFeeDesc();
        result = result * 59 + ($feeDesc == null ? 43 : $feeDesc.hashCode());
        LocalDate $convDate = this.getConvDate();
        result = result * 59 + ($convDate == null ? 43 : ((Object)$convDate).hashCode());
        LocalDate $setlDate = this.getSetlDate();
        result = result * 59 + ($setlDate == null ? 43 : ((Object)$setlDate).hashCode());
        String $baseIIFileId = this.getBaseIIFileId();
        result = result * 59 + ($baseIIFileId == null ? 43 : $baseIIFileId.hashCode());
        String $rateTableId = this.getRateTableId();
        result = result * 59 + ($rateTableId == null ? 43 : $rateTableId.hashCode());
        String $aggrementId = this.getAggrementId();
        result = result * 59 + ($aggrementId == null ? 43 : $aggrementId.hashCode());
        String $encCardNumber = this.getEncCardNumber();
        result = result * 59 + ($encCardNumber == null ? 43 : $encCardNumber.hashCode());
        String $retRefNumber = this.getRetRefNumber();
        result = result * 59 + ($retRefNumber == null ? 43 : $retRefNumber.hashCode());
        LocalDate $bussDate = this.getBussDate();
        result = result * 59 + ($bussDate == null ? 43 : ((Object)$bussDate).hashCode());
        return result;
    }

    public String toString() {
        return "VisaNetworkDataEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", interfaceCode=" + this.getInterfaceCode() + ", jobNumber=" + this.getJobNumber() + ", destinationId=" + this.getDestinationId() + ", sourceId=" + this.getSourceId() + ", casId=" + this.getCasId() + ", originId=" + this.getOriginId() + ", txnCode=" + this.getTxnCode() + ", tcqId=" + this.getTcqId() + ", centerProcDate=" + String.valueOf(this.getCenterProcDate()) + ", txnId=" + this.getTxnId() + ", cardNumber=" + this.getCardNumber() + ", acqRefNumber=" + this.getAcqRefNumber() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", txnAmount=" + this.getTxnAmount() + ", txnCurrency=" + this.getTxnCurrency() + ", setlFlag=" + this.getSetlFlag() + ", serviceId=" + this.getServiceId() + ", setlCurrency=" + this.getSetlCurrency() + ", sreId=" + this.getSreId() + ", fundTransferId=" + this.getFundTransferId() + ", setlAmountInchg=" + this.getSetlAmountInchg() + ", setlAmountSign=" + this.getSetlAmountSign() + ", usageCode=" + this.getUsageCode() + ", reClassInd=" + this.getReClassInd() + ", reqPayService=" + this.getReqPayService() + ", inchgFeeAmount=" + this.getInchgFeeAmount() + ", inchgFeeSign=" + this.getInchgFeeSign() + ", mvv=" + this.getMvv() + ", feePrgmInd=" + this.getFeePrgmInd() + ", feeDesc=" + this.getFeeDesc() + ", convDate=" + String.valueOf(this.getConvDate()) + ", setlDate=" + String.valueOf(this.getSetlDate()) + ", baseIIFileId=" + this.getBaseIIFileId() + ", fxInd=" + this.getFxInd() + ", rateTableId=" + this.getRateTableId() + ", tokenResp=" + this.getTokenResp() + ", aggrementId=" + this.getAggrementId() + ", encCardNumber=" + this.getEncCardNumber() + ", retRefNumber=" + this.getRetRefNumber() + ", matchStatus=" + this.getMatchStatus() + ", refSerNumber=" + this.getRefSerNumber() + ", irfDiff=" + this.getIrfDiff() + ", bussDate=" + String.valueOf(this.getBussDate()) + ")";
    }
}

