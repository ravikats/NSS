package com.empay.common.irf;

import java.time.LocalDateTime;

import com.empay.common.vo.IRFRequestVo;

/**
 * Txn-agnostic view of a transaction that the IRF calculators consume.
 * Each service (MPGS file-processor, TLF online/Kafka processor) provides a thin
 * adapter that maps its own transaction entity ({@code MPGSTxnWorkEntity}/
 * {@code PosTransactionEntity}) onto this DTO, so the calculator implementations
 * live once in the common module.
 *
 * The field set is the union of the fields read by {@link VisaIrfCalculation}
 * (mpgs + tlf) and {@link UAEMcIRFCalculation} (mpgs + tlf). Fields that only one
 * network uses are simply left null by the other adapter.
 */
public class IrfTxnData {

    private Integer serialNumber;
    private Integer insCode;
    private String network;
    private String scheme;
    private String mcc;
    private String txnCode;
    private String responseCode;
    private String approvalCode;
    private String rrn;
    private String posEntryMode;
    private String terminalType;
    private String txnSource;
    private String posConditionCode;
    private String serviceCode;
    private String cardSeqNumber;

    private Double txnAmount;
    private Double setlAmount;
    private Double cashBackAmount;
    private Double authAmount;
    private Double netAmount;
    private Double tipAmount;
    private Double txnFeeAmount;
    private Double cryptAmount;
    private Double chipCashBack;
    private Double dccAmount;

    private String txnCurCode;
    private String setlCurCode;
    private String dccCurrency;
    private String cardAcceptorCountryCode;

    private String feePgmIndicator;
    private Character reImbursementAttribute;
    private String motoEcomIndicator;
    private String terminalCapability;
    private Character trlCapabilities;
    private String mvv;
    private String networkData;
    private String maid;
    private String txnId;
    private Character meCategoryType;
    private Character chAuthAbility;
    private Character cardInputAbility;
    private Character cardCaptureAbility;
    private Character cardInputMode;
    private Character oprtEnvironment;
    private Character chPresent;
    private Character cardPresent;
    private String settlementIndicator;
    private String authReason;
    private LocalDateTime txnDateTime;
    private String stan;
    private String msgTypeId;
    private String txnUniqueId;
    private String cardNumber;
    private String encCardNumber;
    private String tokenIdentifier;
    private String acqInstConCode;

    public IrfTxnData() {
    }

    public IRFRequestVo toIrfRequestVo() {
        IRFRequestVo req = new IRFRequestVo();
        req.setInsCode(insCode);
        req.setPan(encCardNumber);
        req.setTxnDateTime(txnDateTime);
        req.setTxnAmount(txnAmount);
        req.setTerminalType(terminalType);
        req.setMcc(mcc);
        req.setFeePgmIndicator(feePgmIndicator);
        req.setReimbAttribute(reImbursementAttribute);
        req.setPosEntryMode(StringUtils_left(posEntryMode, 2));
        req.setTerminalCapability(terminalCapability);
        req.setAuthCode(approvalCode);
        req.setResponseCode(responseCode);
        req.setMotoEcomIndicator(motoEcomIndicator);
        req.setMvv(mvv);
        return req;
    }

    private static String StringUtils_left(String s, int len) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return s.substring(0, Math.min(s.length(), len));
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public IrfTxnData setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Integer getInsCode() {
        return insCode;
    }

    public IrfTxnData setInsCode(Integer insCode) {
        this.insCode = insCode;
        return this;
    }

    public String getNetwork() {
        return network;
    }

    public IrfTxnData setNetwork(String network) {
        this.network = network;
        return this;
    }

    public String getScheme() {
        return scheme;
    }

    public IrfTxnData setScheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public String getMcc() {
        return mcc;
    }

    public IrfTxnData setMcc(String mcc) {
        this.mcc = mcc;
        return this;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public IrfTxnData setTxnCode(String txnCode) {
        this.txnCode = txnCode;
        return this;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public IrfTxnData setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public IrfTxnData setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
        return this;
    }

    public String getRrn() {
        return rrn;
    }

    public IrfTxnData setRrn(String rrn) {
        this.rrn = rrn;
        return this;
    }

    public String getPosEntryMode() {
        return posEntryMode;
    }

    public IrfTxnData setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
        return this;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public IrfTxnData setTerminalType(String terminalType) {
        this.terminalType = terminalType;
        return this;
    }

    public String getTxnSource() {
        return txnSource;
    }

    public IrfTxnData setTxnSource(String txnSource) {
        this.txnSource = txnSource;
        return this;
    }

    public String getPosConditionCode() {
        return posConditionCode;
    }

    public IrfTxnData setPosConditionCode(String posConditionCode) {
        this.posConditionCode = posConditionCode;
        return this;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public IrfTxnData setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public String getCardSeqNumber() {
        return cardSeqNumber;
    }

    public IrfTxnData setCardSeqNumber(String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
        return this;
    }

    public Double getTxnAmount() {
        return txnAmount;
    }

    public IrfTxnData setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
        return this;
    }

    public Double getSetlAmount() {
        return setlAmount;
    }

    public IrfTxnData setSetlAmount(Double setlAmount) {
        this.setlAmount = setlAmount;
        return this;
    }

    public Double getCashBackAmount() {
        return cashBackAmount;
    }

    public IrfTxnData setCashBackAmount(Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
        return this;
    }

    public Double getAuthAmount() {
        return authAmount;
    }

    public IrfTxnData setAuthAmount(Double authAmount) {
        this.authAmount = authAmount;
        return this;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public IrfTxnData setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
        return this;
    }

    public Double getTipAmount() {
        return tipAmount;
    }

    public IrfTxnData setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
        return this;
    }

    public Double getTxnFeeAmount() {
        return txnFeeAmount;
    }

    public IrfTxnData setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
        return this;
    }

    public Double getCryptAmount() {
        return cryptAmount;
    }

    public IrfTxnData setCryptAmount(Double cryptAmount) {
        this.cryptAmount = cryptAmount;
        return this;
    }

    public Double getChipCashBack() {
        return chipCashBack;
    }

    public IrfTxnData setChipCashBack(Double chipCashBack) {
        this.chipCashBack = chipCashBack;
        return this;
    }

    public Double getDccAmount() {
        return dccAmount;
    }

    public IrfTxnData setDccAmount(Double dccAmount) {
        this.dccAmount = dccAmount;
        return this;
    }

    public String getTxnCurCode() {
        return txnCurCode;
    }

    public IrfTxnData setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
        return this;
    }

    public String getSetlCurCode() {
        return setlCurCode;
    }

    public IrfTxnData setSetlCurCode(String setlCurCode) {
        this.setlCurCode = setlCurCode;
        return this;
    }

    public String getDccCurrency() {
        return dccCurrency;
    }

    public IrfTxnData setDccCurrency(String dccCurrency) {
        this.dccCurrency = dccCurrency;
        return this;
    }

    public String getCardAcceptorCountryCode() {
        return cardAcceptorCountryCode;
    }

    public IrfTxnData setCardAcceptorCountryCode(String cardAcceptorCountryCode) {
        this.cardAcceptorCountryCode = cardAcceptorCountryCode;
        return this;
    }

    public String getFeePgmIndicator() {
        return feePgmIndicator;
    }

    public IrfTxnData setFeePgmIndicator(String feePgmIndicator) {
        this.feePgmIndicator = feePgmIndicator;
        return this;
    }

    public Character getReImbursementAttribute() {
        return reImbursementAttribute;
    }

    public IrfTxnData setReImbursementAttribute(Character reImbursementAttribute) {
        this.reImbursementAttribute = reImbursementAttribute;
        return this;
    }

    public String getMotoEcomIndicator() {
        return motoEcomIndicator;
    }

    public IrfTxnData setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
        return this;
    }

    public String getTerminalCapability() {
        return terminalCapability;
    }

    public IrfTxnData setTerminalCapability(String terminalCapability) {
        this.terminalCapability = terminalCapability;
        return this;
    }

    public Character getTrlCapabilities() {
        return trlCapabilities;
    }

    public IrfTxnData setTrlCapabilities(Character trlCapabilities) {
        this.trlCapabilities = trlCapabilities;
        return this;
    }

    public String getMvv() {
        return mvv;
    }

    public IrfTxnData setMvv(String mvv) {
        this.mvv = mvv;
        return this;
    }

    public String getNetworkData() {
        return networkData;
    }

    public IrfTxnData setNetworkData(String networkData) {
        this.networkData = networkData;
        return this;
    }

    public String getMaid() {
        return maid;
    }

    public IrfTxnData setMaid(String maid) {
        this.maid = maid;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public IrfTxnData setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public Character getMeCategoryType() {
        return meCategoryType;
    }

    public IrfTxnData setMeCategoryType(Character meCategoryType) {
        this.meCategoryType = meCategoryType;
        return this;
    }

    public Character getChAuthAbility() {
        return chAuthAbility;
    }

    public IrfTxnData setChAuthAbility(Character chAuthAbility) {
        this.chAuthAbility = chAuthAbility;
        return this;
    }

    public Character getCardInputAbility() {
        return cardInputAbility;
    }

    public IrfTxnData setCardInputAbility(Character cardInputAbility) {
        this.cardInputAbility = cardInputAbility;
        return this;
    }

    public Character getCardCaptureAbility() {
        return cardCaptureAbility;
    }

    public IrfTxnData setCardCaptureAbility(Character cardCaptureAbility) {
        this.cardCaptureAbility = cardCaptureAbility;
        return this;
    }

    public Character getCardInputMode() {
        return cardInputMode;
    }

    public IrfTxnData setCardInputMode(Character cardInputMode) {
        this.cardInputMode = cardInputMode;
        return this;
    }

    public Character getOprtEnvironment() {
        return oprtEnvironment;
    }

    public IrfTxnData setOprtEnvironment(Character oprtEnvironment) {
        this.oprtEnvironment = oprtEnvironment;
        return this;
    }

    public Character getChPresent() {
        return chPresent;
    }

    public IrfTxnData setChPresent(Character chPresent) {
        this.chPresent = chPresent;
        return this;
    }

    public Character getCardPresent() {
        return cardPresent;
    }

    public IrfTxnData setCardPresent(Character cardPresent) {
        this.cardPresent = cardPresent;
        return this;
    }

    public String getSettlementIndicator() {
        return settlementIndicator;
    }

    public IrfTxnData setSettlementIndicator(String settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
        return this;
    }

    public String getAuthReason() {
        return authReason;
    }

    public IrfTxnData setAuthReason(String authReason) {
        this.authReason = authReason;
        return this;
    }

    public LocalDateTime getTxnDateTime() {
        return txnDateTime;
    }

    public IrfTxnData setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
        return this;
    }

    public String getStan() {
        return stan;
    }

    public IrfTxnData setStan(String stan) {
        this.stan = stan;
        return this;
    }

    public String getMsgTypeId() {
        return msgTypeId;
    }

    public IrfTxnData setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
        return this;
    }

    public String getTxnUniqueId() {
        return txnUniqueId;
    }

    public IrfTxnData setTxnUniqueId(String txnUniqueId) {
        this.txnUniqueId = txnUniqueId;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public IrfTxnData setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getEncCardNumber() {
        return encCardNumber;
    }

    public IrfTxnData setEncCardNumber(String encCardNumber) {
        this.encCardNumber = encCardNumber;
        return this;
    }

    public String getTokenIdentifier() {
        return tokenIdentifier;
    }

    public IrfTxnData setTokenIdentifier(String tokenIdentifier) {
        this.tokenIdentifier = tokenIdentifier;
        return this;
    }

    public String getAcqInstConCode() {
        return acqInstConCode;
    }

    public IrfTxnData setAcqInstConCode(String acqInstConCode) {
        this.acqInstConCode = acqInstConCode;
        return this;
    }
}
