/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.PosTransactionEntity
 *  com.empay.staging.entities.PosTransactionEntity$PosTransactionEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import com.empay.staging.entities.PosTransactionEntity;
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
    @Column(name="PTR_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="PTR_RET_REF_NUMBER")
    private String rrn;
    @Column(name="PTR_CARD_TYPE")
    private Character cardType;
    @Column(name="PTR_CARD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="PTR_CARD_NUMBER")
    private String cardNumber;
    @Column(name="PTR_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="PTR_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="PTR_RESP_CODE")
    private String responseCode;
    @Column(name="PTR_APPR_CODE")
    private String approvalCode;
    @Column(name="PTR_TERMINAL_ID")
    private String terminalId;
    @Column(name="PTR_MERCHANT_ID")
    private String merchantId;
    @Column(name="PTR_TXN_TYPE")
    private String txnType;
    @Column(name="PTR_PROC_CODE")
    private String procCode;
    @Column(name="PTR_STAN")
    private String stan;
    @Column(name="PTR_AUTH_REASON")
    private String authReason;
    @Column(name="PTR_ME_NAME")
    private String meName;
    @Column(name="PTR_ME_CITY")
    private String meCity;
    @Column(name="PTR_ME_COUNTRY")
    private String meCountry;
    @Column(name="PTR_ME_PIN_CODE")
    private String mePinCode;
    @Column(name="PTR_STAGING_FLAG")
    private Character stagingFlag;
    @Column(name="PTR_PAYMENT_FLAG")
    private Character paymentFlag;
    @Column(name="PTR_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name="PTR_TXN_UNIQUE_ID")
    private String txnUniqueId;
    @Column(name="PTR_MSG_TYPE_ID")
    private String msgTypeId;
    @Column(name="PTR_AGR_SER_NUMBER")
    private Integer agrSernumber;
    @Column(name="PTR_CARD_INPUT_ABILITY")
    private Character cardInputAbility;
    @Column(name="PTR_CARD_CAPTURE_ABILITY")
    private Character cardCaptureAbility;
    @Column(name="PTR_CH_PRESENT")
    private Character chPresent;
    @Column(name="PTR_CARD_PRESENT")
    private Character cardPresent;
    @Column(name="PTR_OPRT_ENVIRONMENT")
    private String oprtEnvironment;
    @Column(name="PTR_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name="PTR_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="PTR_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name="PTR_SETL_AMOUNT")
    private Double setlAmount;
    @Column(name="PTR_SETL_DATE")
    private LocalDate setlDate;
    @Column(name="PTR_SETL_CUR_CODE")
    private String setlCurCode;
    @Column(name="PTR_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name="PTR_POS_CONDITION_CODE")
    private String posConditionCode;
    @Column(name="PTR_MOTO_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name="PTR_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name="PTR_SERVICE_CODE")
    private String serviceCode;
    @Column(name="PTR_MCC")
    private String mcc;
    @Column(name="PTR_TRL_TYPE")
    private String terminalType;
    @Column(name="PTR_NETWORK")
    private String network;
    @Column(name="PTR_MER_NET_AMOUNT")
    private Double netAmount;
    @Column(name="PTR_SETL_FLAG")
    private Character settlementIndicator;
    @Column(name="PTR_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name="PTR_ONUS_OFFUS_FLAG")
    private Character onusOffusFlag;
    @Column(name="PTR_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name="PTR_CARD_CATEGORY")
    private String cardCategory;
    @Column(name="PTR_CARD_SUB_CATEGORY")
    private String cardSubCategory;
    @Column(name="PTR_DMS_SMS_MODE")
    private Character dmsSmsMode;
    @Column(name="PTR_NETWORK_DATA")
    private String networkData;
    @Column(name="PTR_CH_AUTH_ABILITY")
    private Character chAuthAbility;
    @Column(name="PTR_CH_AUTH_ENTITY")
    private Character chAuthEntity;
    @Column(name="PTR_CARD_OUTPUT_ABILITY")
    private Character cardOutPutAbility;
    @Column(name="PTR_TRL_OUTPUT_ABILITTY")
    private Character trlOutPutAbility;
    @Column(name="PTR_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name="PTR_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name="PTR_ACQ_INST_CON_CODE")
    private String acqInstConCode;
    @Column(name="PTR_CH_AUTH_METHOD")
    private Character chAuthMethod;
    @Column(name="PTR_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name="PTR_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name="PTR_ISS_APP_DATA")
    private String issAppData;
    @Column(name="PTR_UPBL_NUMBER")
    private String upblNumber;
    @Column(name="PTR_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name="PTR_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name="PTR_CHIP_TXN_DATE")
    private String chipTxnDate;
    @Column(name="PTR_CHIP_TXN_TYPE")
    private String chipTxnType;
    @Column(name="PTR_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name="PTR_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name="PTR_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name="PTR_TRL_CON_CODE")
    private String trlConCode;
    @Column(name="PTR_CHIP_CASHBACK")
    private Double chipCashBack;
    @Column(name="PTR_CVM_RESULTS")
    private String cvmResult;
    @Column(name="PTR_TRL_CAPABILITIES")
    private String trlCapabilities;
    @Column(name="PTR_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name="PTR_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name="PTR_TCC")
    private String tcc;
    @Column(name="PTR_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name="PTR_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name="PTR_TXN_ID")
    private String txnId;
    @Column(name="PTR_ME_CATEGORY_TYPE")
    private Character meCategoryType;
    @Column(name="PTR_CARD_ACC_STREET_ADDRESS")
    private String cardAccepStreetAddress;
    @Column(name="PTR_CARD_ACC_STATE_CODE")
    private String cardAccepStateCode;
    @Column(name="PTR_EXPIRY_DATE")
    private String expiryDate;
    @Column(name="PTR_IRD_SER_NUMBER")
    private Integer irdSerNumber;
    @Column(name="PTR_IRD")
    private String ird;
    @Column(name="PTR_IRF_FIXED")
    private Double irfFixed;
    @Column(name="PTR_IRF_PERCENT")
    private Double irfPercent;
    @Column(name="PTR_IRF_AMOUNT")
    private Double irfAmount;
    @Column(name="PTR_REMARKS")
    private String remarks;
    @Column(name="PTR_CH_ID_METHOD")
    private Character chIdMethod;
    @Column(name="PTR_CARD_INPUT_MODE")
    private Character cardInputMode;
    @Column(name="PTR_AUTH_CHAR_INDICATOR")
    private Character authCharecteresticId;
    @Column(name="PTR_VALIDATION_CODE")
    private String validationCode;
    @Column(name="PTR_MARKET_SPEC_DATA_IND")
    private String marketSpecAuthDataInd;
    @Column(name="PTR_PRODUCT_ID")
    private String productId;
    @Column(name="PTR_SPEND_QUALI_IND")
    private Character spendQualificationInd;
    @Column(name="PTR_REIMB_ATTRIBUTE")
    private Character reImbursementAttribute;
    @Column(name="PTR_FEE_PRG_INDICATOR")
    private String feePgmIndicator;
    @Column(name="PTR_ACC_FUND_SOURCE")
    private Character accountFundingSource;
    @Column(name="PTR_BANK_CODE")
    private String banKCode;
    @Column(name="PTR_MAID")
    private String maid;
    @Column(name="PTR_CHIP_TRL_CAPABILITIES")
    private String chipTrlCapabilities;
    @Column(name="PTR_MVV")
    private String mvv;
    @Column(name="PTR_TXN_CODE")
    private String txnCode;
    @Column(name="PTR_ADTL_AMOUNTS")
    private String adtlAmounts;
    @Column(name="PTR_OUT_STATUS")
    private String outStatus;
    @Column(name="PTR_SCHEME")
    private String scheme;

    public static PosTransactionEntityBuilder builder() {
        return new PosTransactionEntityBuilder();
    }

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

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public String getRrn() {
        return this.rrn;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public String getStan() {
        return this.stan;
    }

    public String getAuthReason() {
        return this.authReason;
    }

    public String getMeName() {
        return this.meName;
    }

    public String getMeCity() {
        return this.meCity;
    }

    public String getMeCountry() {
        return this.meCountry;
    }

    public String getMePinCode() {
        return this.mePinCode;
    }

    public Character getStagingFlag() {
        return this.stagingFlag;
    }

    public Character getPaymentFlag() {
        return this.paymentFlag;
    }

    public LocalDate getBussDate() {
        return this.bussDate;
    }

    public String getTxnUniqueId() {
        return this.txnUniqueId;
    }

    public String getMsgTypeId() {
        return this.msgTypeId;
    }

    public Integer getAgrSernumber() {
        return this.agrSernumber;
    }

    public Character getCardInputAbility() {
        return this.cardInputAbility;
    }

    public Character getCardCaptureAbility() {
        return this.cardCaptureAbility;
    }

    public Character getChPresent() {
        return this.chPresent;
    }

    public Character getCardPresent() {
        return this.cardPresent;
    }

    public String getOprtEnvironment() {
        return this.oprtEnvironment;
    }

    public Double getCashBackAmount() {
        return this.cashBackAmount;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
    }

    public Double getTxnFeeAmount() {
        return this.txnFeeAmount;
    }

    public Double getSetlAmount() {
        return this.setlAmount;
    }

    public LocalDate getSetlDate() {
        return this.setlDate;
    }

    public String getSetlCurCode() {
        return this.setlCurCode;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
    }

    public String getPosConditionCode() {
        return this.posConditionCode;
    }

    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }

    public String getCardSeqNumber() {
        return this.cardSeqNumber;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getTerminalType() {
        return this.terminalType;
    }

    public String getNetwork() {
        return this.network;
    }

    public Double getNetAmount() {
        return this.netAmount;
    }

    public Character getSettlementIndicator() {
        return this.settlementIndicator;
    }

    public LocalDate getCentreProcDate() {
        return this.centreProcDate;
    }

    public Character getOnusOffusFlag() {
        return this.onusOffusFlag;
    }

    public Character getRevIndiCator() {
        return this.revIndiCator;
    }

    public String getCardCategory() {
        return this.cardCategory;
    }

    public String getCardSubCategory() {
        return this.cardSubCategory;
    }

    public Character getDmsSmsMode() {
        return this.dmsSmsMode;
    }

    public String getNetworkData() {
        return this.networkData;
    }

    public Character getChAuthAbility() {
        return this.chAuthAbility;
    }

    public Character getChAuthEntity() {
        return this.chAuthEntity;
    }

    public Character getCardOutPutAbility() {
        return this.cardOutPutAbility;
    }

    public Character getTrlOutPutAbility() {
        return this.trlOutPutAbility;
    }

    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }

    public String getAcqinstIdCode() {
        return this.acqinstIdCode;
    }

    public String getAcqInstConCode() {
        return this.acqInstConCode;
    }

    public Character getChAuthMethod() {
        return this.chAuthMethod;
    }

    public String getAppCryptogram() {
        return this.appCryptogram;
    }

    public String getCryptInfoData() {
        return this.cryptInfoData;
    }

    public String getIssAppData() {
        return this.issAppData;
    }

    public String getUpblNumber() {
        return this.upblNumber;
    }

    public String getAppTxnCounter() {
        return this.appTxnCounter;
    }

    public String getTrlVerResult() {
        return this.trlVerResult;
    }

    public String getChipTxnDate() {
        return this.chipTxnDate;
    }

    public String getChipTxnType() {
        return this.chipTxnType;
    }

    public Double getCryptAmount() {
        return this.cryptAmount;
    }

    public String getChipCurCode() {
        return this.chipCurCode;
    }

    public String getAppICProfile() {
        return this.appICProfile;
    }

    public String getTrlConCode() {
        return this.trlConCode;
    }

    public Double getChipCashBack() {
        return this.chipCashBack;
    }

    public String getCvmResult() {
        return this.cvmResult;
    }

    public String getTrlCapabilities() {
        return this.trlCapabilities;
    }

    public String getChipTrlType() {
        return this.chipTrlType;
    }

    public String getIfdSerNumber() {
        return this.ifdSerNumber;
    }

    public String getTcc() {
        return this.tcc;
    }

    public String getTrlAppVerNumber() {
        return this.trlAppVerNumber;
    }

    public String getIssAuthData() {
        return this.issAuthData;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public Character getMeCategoryType() {
        return this.meCategoryType;
    }

    public String getCardAccepStreetAddress() {
        return this.cardAccepStreetAddress;
    }

    public String getCardAccepStateCode() {
        return this.cardAccepStateCode;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public Integer getIrdSerNumber() {
        return this.irdSerNumber;
    }

    public String getIrd() {
        return this.ird;
    }

    public Double getIrfFixed() {
        return this.irfFixed;
    }

    public Double getIrfPercent() {
        return this.irfPercent;
    }

    public Double getIrfAmount() {
        return this.irfAmount;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Character getChIdMethod() {
        return this.chIdMethod;
    }

    public Character getCardInputMode() {
        return this.cardInputMode;
    }

    public Character getAuthCharecteresticId() {
        return this.authCharecteresticId;
    }

    public String getValidationCode() {
        return this.validationCode;
    }

    public String getMarketSpecAuthDataInd() {
        return this.marketSpecAuthDataInd;
    }

    public String getProductId() {
        return this.productId;
    }

    public Character getSpendQualificationInd() {
        return this.spendQualificationInd;
    }

    public Character getReImbursementAttribute() {
        return this.reImbursementAttribute;
    }

    public String getFeePgmIndicator() {
        return this.feePgmIndicator;
    }

    public Character getAccountFundingSource() {
        return this.accountFundingSource;
    }

    public String getBanKCode() {
        return this.banKCode;
    }

    public String getMaid() {
        return this.maid;
    }

    public String getChipTrlCapabilities() {
        return this.chipTrlCapabilities;
    }

    public String getMvv() {
        return this.mvv;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public String getAdtlAmounts() {
        return this.adtlAmounts;
    }

    public String getOutStatus() {
        return this.outStatus;
    }

    public String getScheme() {
        return this.scheme;
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

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setCardDomIntlFlag(Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public void setAuthReason(String authReason) {
        this.authReason = authReason;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public void setMeCity(String meCity) {
        this.meCity = meCity;
    }

    public void setMeCountry(String meCountry) {
        this.meCountry = meCountry;
    }

    public void setMePinCode(String mePinCode) {
        this.mePinCode = mePinCode;
    }

    public void setStagingFlag(Character stagingFlag) {
        this.stagingFlag = stagingFlag;
    }

    public void setPaymentFlag(Character paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public void setBussDate(LocalDate bussDate) {
        this.bussDate = bussDate;
    }

    public void setTxnUniqueId(String txnUniqueId) {
        this.txnUniqueId = txnUniqueId;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public void setAgrSernumber(Integer agrSernumber) {
        this.agrSernumber = agrSernumber;
    }

    public void setCardInputAbility(Character cardInputAbility) {
        this.cardInputAbility = cardInputAbility;
    }

    public void setCardCaptureAbility(Character cardCaptureAbility) {
        this.cardCaptureAbility = cardCaptureAbility;
    }

    public void setChPresent(Character chPresent) {
        this.chPresent = chPresent;
    }

    public void setCardPresent(Character cardPresent) {
        this.cardPresent = cardPresent;
    }

    public void setOprtEnvironment(String oprtEnvironment) {
        this.oprtEnvironment = oprtEnvironment;
    }

    public void setCashBackAmount(Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }

    public void setSetlAmount(Double setlAmount) {
        this.setlAmount = setlAmount;
    }

    public void setSetlDate(LocalDate setlDate) {
        this.setlDate = setlDate;
    }

    public void setSetlCurCode(String setlCurCode) {
        this.setlCurCode = setlCurCode;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public void setPosConditionCode(String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }

    public void setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setCardSeqNumber(String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public void setSettlementIndicator(Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }

    public void setCentreProcDate(LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }

    public void setOnusOffusFlag(Character onusOffusFlag) {
        this.onusOffusFlag = onusOffusFlag;
    }

    public void setRevIndiCator(Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }

    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    public void setCardSubCategory(String cardSubCategory) {
        this.cardSubCategory = cardSubCategory;
    }

    public void setDmsSmsMode(Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }

    public void setNetworkData(String networkData) {
        this.networkData = networkData;
    }

    public void setChAuthAbility(Character chAuthAbility) {
        this.chAuthAbility = chAuthAbility;
    }

    public void setChAuthEntity(Character chAuthEntity) {
        this.chAuthEntity = chAuthEntity;
    }

    public void setCardOutPutAbility(Character cardOutPutAbility) {
        this.cardOutPutAbility = cardOutPutAbility;
    }

    public void setTrlOutPutAbility(Character trlOutPutAbility) {
        this.trlOutPutAbility = trlOutPutAbility;
    }

    public void setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    public void setAcqinstIdCode(String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }

    public void setAcqInstConCode(String acqInstConCode) {
        this.acqInstConCode = acqInstConCode;
    }

    public void setChAuthMethod(Character chAuthMethod) {
        this.chAuthMethod = chAuthMethod;
    }

    public void setAppCryptogram(String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }

    public void setCryptInfoData(String cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }

    public void setIssAppData(String issAppData) {
        this.issAppData = issAppData;
    }

    public void setUpblNumber(String upblNumber) {
        this.upblNumber = upblNumber;
    }

    public void setAppTxnCounter(String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }

    public void setTrlVerResult(String trlVerResult) {
        this.trlVerResult = trlVerResult;
    }

    public void setChipTxnDate(String chipTxnDate) {
        this.chipTxnDate = chipTxnDate;
    }

    public void setChipTxnType(String chipTxnType) {
        this.chipTxnType = chipTxnType;
    }

    public void setCryptAmount(Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }

    public void setChipCurCode(String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }

    public void setAppICProfile(String appICProfile) {
        this.appICProfile = appICProfile;
    }

    public void setTrlConCode(String trlConCode) {
        this.trlConCode = trlConCode;
    }

    public void setChipCashBack(Double chipCashBack) {
        this.chipCashBack = chipCashBack;
    }

    public void setCvmResult(String cvmResult) {
        this.cvmResult = cvmResult;
    }

    public void setTrlCapabilities(String trlCapabilities) {
        this.trlCapabilities = trlCapabilities;
    }

    public void setChipTrlType(String chipTrlType) {
        this.chipTrlType = chipTrlType;
    }

    public void setIfdSerNumber(String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }

    public void setTrlAppVerNumber(String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }

    public void setIssAuthData(String issAuthData) {
        this.issAuthData = issAuthData;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setMeCategoryType(Character meCategoryType) {
        this.meCategoryType = meCategoryType;
    }

    public void setCardAccepStreetAddress(String cardAccepStreetAddress) {
        this.cardAccepStreetAddress = cardAccepStreetAddress;
    }

    public void setCardAccepStateCode(String cardAccepStateCode) {
        this.cardAccepStateCode = cardAccepStateCode;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setIrdSerNumber(Integer irdSerNumber) {
        this.irdSerNumber = irdSerNumber;
    }

    public void setIrd(String ird) {
        this.ird = ird;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public void setIrfPercent(Double irfPercent) {
        this.irfPercent = irfPercent;
    }

    public void setIrfAmount(Double irfAmount) {
        this.irfAmount = irfAmount;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setChIdMethod(Character chIdMethod) {
        this.chIdMethod = chIdMethod;
    }

    public void setCardInputMode(Character cardInputMode) {
        this.cardInputMode = cardInputMode;
    }

    public void setAuthCharecteresticId(Character authCharecteresticId) {
        this.authCharecteresticId = authCharecteresticId;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public void setMarketSpecAuthDataInd(String marketSpecAuthDataInd) {
        this.marketSpecAuthDataInd = marketSpecAuthDataInd;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setSpendQualificationInd(Character spendQualificationInd) {
        this.spendQualificationInd = spendQualificationInd;
    }

    public void setReImbursementAttribute(Character reImbursementAttribute) {
        this.reImbursementAttribute = reImbursementAttribute;
    }

    public void setFeePgmIndicator(String feePgmIndicator) {
        this.feePgmIndicator = feePgmIndicator;
    }

    public void setAccountFundingSource(Character accountFundingSource) {
        this.accountFundingSource = accountFundingSource;
    }

    public void setBanKCode(String banKCode) {
        this.banKCode = banKCode;
    }

    public void setMaid(String maid) {
        this.maid = maid;
    }

    public void setChipTrlCapabilities(String chipTrlCapabilities) {
        this.chipTrlCapabilities = chipTrlCapabilities;
    }

    public void setMvv(String mvv) {
        this.mvv = mvv;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setAdtlAmounts(String adtlAmounts) {
        this.adtlAmounts = adtlAmounts;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
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
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
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
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Character this$stagingFlag = this.getStagingFlag();
        Character other$stagingFlag = other.getStagingFlag();
        if (this$stagingFlag == null ? other$stagingFlag != null : !((Object)this$stagingFlag).equals(other$stagingFlag)) {
            return false;
        }
        Character this$paymentFlag = this.getPaymentFlag();
        Character other$paymentFlag = other.getPaymentFlag();
        if (this$paymentFlag == null ? other$paymentFlag != null : !((Object)this$paymentFlag).equals(other$paymentFlag)) {
            return false;
        }
        Integer this$agrSernumber = this.getAgrSernumber();
        Integer other$agrSernumber = other.getAgrSernumber();
        if (this$agrSernumber == null ? other$agrSernumber != null : !((Object)this$agrSernumber).equals(other$agrSernumber)) {
            return false;
        }
        Character this$cardInputAbility = this.getCardInputAbility();
        Character other$cardInputAbility = other.getCardInputAbility();
        if (this$cardInputAbility == null ? other$cardInputAbility != null : !((Object)this$cardInputAbility).equals(other$cardInputAbility)) {
            return false;
        }
        Character this$cardCaptureAbility = this.getCardCaptureAbility();
        Character other$cardCaptureAbility = other.getCardCaptureAbility();
        if (this$cardCaptureAbility == null ? other$cardCaptureAbility != null : !((Object)this$cardCaptureAbility).equals(other$cardCaptureAbility)) {
            return false;
        }
        Character this$chPresent = this.getChPresent();
        Character other$chPresent = other.getChPresent();
        if (this$chPresent == null ? other$chPresent != null : !((Object)this$chPresent).equals(other$chPresent)) {
            return false;
        }
        Character this$cardPresent = this.getCardPresent();
        Character other$cardPresent = other.getCardPresent();
        if (this$cardPresent == null ? other$cardPresent != null : !((Object)this$cardPresent).equals(other$cardPresent)) {
            return false;
        }
        Double this$cashBackAmount = this.getCashBackAmount();
        Double other$cashBackAmount = other.getCashBackAmount();
        if (this$cashBackAmount == null ? other$cashBackAmount != null : !((Object)this$cashBackAmount).equals(other$cashBackAmount)) {
            return false;
        }
        Double this$txnFeeAmount = this.getTxnFeeAmount();
        Double other$txnFeeAmount = other.getTxnFeeAmount();
        if (this$txnFeeAmount == null ? other$txnFeeAmount != null : !((Object)this$txnFeeAmount).equals(other$txnFeeAmount)) {
            return false;
        }
        Double this$setlAmount = this.getSetlAmount();
        Double other$setlAmount = other.getSetlAmount();
        if (this$setlAmount == null ? other$setlAmount != null : !((Object)this$setlAmount).equals(other$setlAmount)) {
            return false;
        }
        Double this$netAmount = this.getNetAmount();
        Double other$netAmount = other.getNetAmount();
        if (this$netAmount == null ? other$netAmount != null : !((Object)this$netAmount).equals(other$netAmount)) {
            return false;
        }
        Character this$settlementIndicator = this.getSettlementIndicator();
        Character other$settlementIndicator = other.getSettlementIndicator();
        if (this$settlementIndicator == null ? other$settlementIndicator != null : !((Object)this$settlementIndicator).equals(other$settlementIndicator)) {
            return false;
        }
        Character this$onusOffusFlag = this.getOnusOffusFlag();
        Character other$onusOffusFlag = other.getOnusOffusFlag();
        if (this$onusOffusFlag == null ? other$onusOffusFlag != null : !((Object)this$onusOffusFlag).equals(other$onusOffusFlag)) {
            return false;
        }
        Character this$revIndiCator = this.getRevIndiCator();
        Character other$revIndiCator = other.getRevIndiCator();
        if (this$revIndiCator == null ? other$revIndiCator != null : !((Object)this$revIndiCator).equals(other$revIndiCator)) {
            return false;
        }
        Character this$dmsSmsMode = this.getDmsSmsMode();
        Character other$dmsSmsMode = other.getDmsSmsMode();
        if (this$dmsSmsMode == null ? other$dmsSmsMode != null : !((Object)this$dmsSmsMode).equals(other$dmsSmsMode)) {
            return false;
        }
        Character this$chAuthAbility = this.getChAuthAbility();
        Character other$chAuthAbility = other.getChAuthAbility();
        if (this$chAuthAbility == null ? other$chAuthAbility != null : !((Object)this$chAuthAbility).equals(other$chAuthAbility)) {
            return false;
        }
        Character this$chAuthEntity = this.getChAuthEntity();
        Character other$chAuthEntity = other.getChAuthEntity();
        if (this$chAuthEntity == null ? other$chAuthEntity != null : !((Object)this$chAuthEntity).equals(other$chAuthEntity)) {
            return false;
        }
        Character this$cardOutPutAbility = this.getCardOutPutAbility();
        Character other$cardOutPutAbility = other.getCardOutPutAbility();
        if (this$cardOutPutAbility == null ? other$cardOutPutAbility != null : !((Object)this$cardOutPutAbility).equals(other$cardOutPutAbility)) {
            return false;
        }
        Character this$trlOutPutAbility = this.getTrlOutPutAbility();
        Character other$trlOutPutAbility = other.getTrlOutPutAbility();
        if (this$trlOutPutAbility == null ? other$trlOutPutAbility != null : !((Object)this$trlOutPutAbility).equals(other$trlOutPutAbility)) {
            return false;
        }
        Character this$chAuthMethod = this.getChAuthMethod();
        Character other$chAuthMethod = other.getChAuthMethod();
        if (this$chAuthMethod == null ? other$chAuthMethod != null : !((Object)this$chAuthMethod).equals(other$chAuthMethod)) {
            return false;
        }
        Double this$cryptAmount = this.getCryptAmount();
        Double other$cryptAmount = other.getCryptAmount();
        if (this$cryptAmount == null ? other$cryptAmount != null : !((Object)this$cryptAmount).equals(other$cryptAmount)) {
            return false;
        }
        Double this$chipCashBack = this.getChipCashBack();
        Double other$chipCashBack = other.getChipCashBack();
        if (this$chipCashBack == null ? other$chipCashBack != null : !((Object)this$chipCashBack).equals(other$chipCashBack)) {
            return false;
        }
        Character this$meCategoryType = this.getMeCategoryType();
        Character other$meCategoryType = other.getMeCategoryType();
        if (this$meCategoryType == null ? other$meCategoryType != null : !((Object)this$meCategoryType).equals(other$meCategoryType)) {
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
        Character this$chIdMethod = this.getChIdMethod();
        Character other$chIdMethod = other.getChIdMethod();
        if (this$chIdMethod == null ? other$chIdMethod != null : !((Object)this$chIdMethod).equals(other$chIdMethod)) {
            return false;
        }
        Character this$cardInputMode = this.getCardInputMode();
        Character other$cardInputMode = other.getCardInputMode();
        if (this$cardInputMode == null ? other$cardInputMode != null : !((Object)this$cardInputMode).equals(other$cardInputMode)) {
            return false;
        }
        Character this$authCharecteresticId = this.getAuthCharecteresticId();
        Character other$authCharecteresticId = other.getAuthCharecteresticId();
        if (this$authCharecteresticId == null ? other$authCharecteresticId != null : !((Object)this$authCharecteresticId).equals(other$authCharecteresticId)) {
            return false;
        }
        Character this$spendQualificationInd = this.getSpendQualificationInd();
        Character other$spendQualificationInd = other.getSpendQualificationInd();
        if (this$spendQualificationInd == null ? other$spendQualificationInd != null : !((Object)this$spendQualificationInd).equals(other$spendQualificationInd)) {
            return false;
        }
        Character this$reImbursementAttribute = this.getReImbursementAttribute();
        Character other$reImbursementAttribute = other.getReImbursementAttribute();
        if (this$reImbursementAttribute == null ? other$reImbursementAttribute != null : !((Object)this$reImbursementAttribute).equals(other$reImbursementAttribute)) {
            return false;
        }
        Character this$accountFundingSource = this.getAccountFundingSource();
        Character other$accountFundingSource = other.getAccountFundingSource();
        if (this$accountFundingSource == null ? other$accountFundingSource != null : !((Object)this$accountFundingSource).equals(other$accountFundingSource)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$stan = this.getStan();
        String other$stan = other.getStan();
        if (this$stan == null ? other$stan != null : !this$stan.equals(other$stan)) {
            return false;
        }
        String this$authReason = this.getAuthReason();
        String other$authReason = other.getAuthReason();
        if (this$authReason == null ? other$authReason != null : !this$authReason.equals(other$authReason)) {
            return false;
        }
        String this$meName = this.getMeName();
        String other$meName = other.getMeName();
        if (this$meName == null ? other$meName != null : !this$meName.equals(other$meName)) {
            return false;
        }
        String this$meCity = this.getMeCity();
        String other$meCity = other.getMeCity();
        if (this$meCity == null ? other$meCity != null : !this$meCity.equals(other$meCity)) {
            return false;
        }
        String this$meCountry = this.getMeCountry();
        String other$meCountry = other.getMeCountry();
        if (this$meCountry == null ? other$meCountry != null : !this$meCountry.equals(other$meCountry)) {
            return false;
        }
        String this$mePinCode = this.getMePinCode();
        String other$mePinCode = other.getMePinCode();
        if (this$mePinCode == null ? other$mePinCode != null : !this$mePinCode.equals(other$mePinCode)) {
            return false;
        }
        LocalDate this$bussDate = this.getBussDate();
        LocalDate other$bussDate = other.getBussDate();
        if (this$bussDate == null ? other$bussDate != null : !((Object)this$bussDate).equals(other$bussDate)) {
            return false;
        }
        String this$txnUniqueId = this.getTxnUniqueId();
        String other$txnUniqueId = other.getTxnUniqueId();
        if (this$txnUniqueId == null ? other$txnUniqueId != null : !this$txnUniqueId.equals(other$txnUniqueId)) {
            return false;
        }
        String this$msgTypeId = this.getMsgTypeId();
        String other$msgTypeId = other.getMsgTypeId();
        if (this$msgTypeId == null ? other$msgTypeId != null : !this$msgTypeId.equals(other$msgTypeId)) {
            return false;
        }
        String this$oprtEnvironment = this.getOprtEnvironment();
        String other$oprtEnvironment = other.getOprtEnvironment();
        if (this$oprtEnvironment == null ? other$oprtEnvironment != null : !this$oprtEnvironment.equals(other$oprtEnvironment)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
            return false;
        }
        LocalDate this$setlDate = this.getSetlDate();
        LocalDate other$setlDate = other.getSetlDate();
        if (this$setlDate == null ? other$setlDate != null : !((Object)this$setlDate).equals(other$setlDate)) {
            return false;
        }
        String this$setlCurCode = this.getSetlCurCode();
        String other$setlCurCode = other.getSetlCurCode();
        if (this$setlCurCode == null ? other$setlCurCode != null : !this$setlCurCode.equals(other$setlCurCode)) {
            return false;
        }
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode)) {
            return false;
        }
        String this$posConditionCode = this.getPosConditionCode();
        String other$posConditionCode = other.getPosConditionCode();
        if (this$posConditionCode == null ? other$posConditionCode != null : !this$posConditionCode.equals(other$posConditionCode)) {
            return false;
        }
        String this$motoEcomIndicator = this.getMotoEcomIndicator();
        String other$motoEcomIndicator = other.getMotoEcomIndicator();
        if (this$motoEcomIndicator == null ? other$motoEcomIndicator != null : !this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
            return false;
        }
        String this$cardSeqNumber = this.getCardSeqNumber();
        String other$cardSeqNumber = other.getCardSeqNumber();
        if (this$cardSeqNumber == null ? other$cardSeqNumber != null : !this$cardSeqNumber.equals(other$cardSeqNumber)) {
            return false;
        }
        String this$serviceCode = this.getServiceCode();
        String other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null ? other$serviceCode != null : !this$serviceCode.equals(other$serviceCode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$terminalType = this.getTerminalType();
        String other$terminalType = other.getTerminalType();
        if (this$terminalType == null ? other$terminalType != null : !this$terminalType.equals(other$terminalType)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        LocalDate this$centreProcDate = this.getCentreProcDate();
        LocalDate other$centreProcDate = other.getCentreProcDate();
        if (this$centreProcDate == null ? other$centreProcDate != null : !((Object)this$centreProcDate).equals(other$centreProcDate)) {
            return false;
        }
        String this$cardCategory = this.getCardCategory();
        String other$cardCategory = other.getCardCategory();
        if (this$cardCategory == null ? other$cardCategory != null : !this$cardCategory.equals(other$cardCategory)) {
            return false;
        }
        String this$cardSubCategory = this.getCardSubCategory();
        String other$cardSubCategory = other.getCardSubCategory();
        if (this$cardSubCategory == null ? other$cardSubCategory != null : !this$cardSubCategory.equals(other$cardSubCategory)) {
            return false;
        }
        String this$networkData = this.getNetworkData();
        String other$networkData = other.getNetworkData();
        if (this$networkData == null ? other$networkData != null : !this$networkData.equals(other$networkData)) {
            return false;
        }
        LocalDateTime this$txnDateTime = this.getTxnDateTime();
        LocalDateTime other$txnDateTime = other.getTxnDateTime();
        if (this$txnDateTime == null ? other$txnDateTime != null : !((Object)this$txnDateTime).equals(other$txnDateTime)) {
            return false;
        }
        String this$acqinstIdCode = this.getAcqinstIdCode();
        String other$acqinstIdCode = other.getAcqinstIdCode();
        if (this$acqinstIdCode == null ? other$acqinstIdCode != null : !this$acqinstIdCode.equals(other$acqinstIdCode)) {
            return false;
        }
        String this$acqInstConCode = this.getAcqInstConCode();
        String other$acqInstConCode = other.getAcqInstConCode();
        if (this$acqInstConCode == null ? other$acqInstConCode != null : !this$acqInstConCode.equals(other$acqInstConCode)) {
            return false;
        }
        String this$appCryptogram = this.getAppCryptogram();
        String other$appCryptogram = other.getAppCryptogram();
        if (this$appCryptogram == null ? other$appCryptogram != null : !this$appCryptogram.equals(other$appCryptogram)) {
            return false;
        }
        String this$cryptInfoData = this.getCryptInfoData();
        String other$cryptInfoData = other.getCryptInfoData();
        if (this$cryptInfoData == null ? other$cryptInfoData != null : !this$cryptInfoData.equals(other$cryptInfoData)) {
            return false;
        }
        String this$issAppData = this.getIssAppData();
        String other$issAppData = other.getIssAppData();
        if (this$issAppData == null ? other$issAppData != null : !this$issAppData.equals(other$issAppData)) {
            return false;
        }
        String this$upblNumber = this.getUpblNumber();
        String other$upblNumber = other.getUpblNumber();
        if (this$upblNumber == null ? other$upblNumber != null : !this$upblNumber.equals(other$upblNumber)) {
            return false;
        }
        String this$appTxnCounter = this.getAppTxnCounter();
        String other$appTxnCounter = other.getAppTxnCounter();
        if (this$appTxnCounter == null ? other$appTxnCounter != null : !this$appTxnCounter.equals(other$appTxnCounter)) {
            return false;
        }
        String this$trlVerResult = this.getTrlVerResult();
        String other$trlVerResult = other.getTrlVerResult();
        if (this$trlVerResult == null ? other$trlVerResult != null : !this$trlVerResult.equals(other$trlVerResult)) {
            return false;
        }
        String this$chipTxnDate = this.getChipTxnDate();
        String other$chipTxnDate = other.getChipTxnDate();
        if (this$chipTxnDate == null ? other$chipTxnDate != null : !this$chipTxnDate.equals(other$chipTxnDate)) {
            return false;
        }
        String this$chipTxnType = this.getChipTxnType();
        String other$chipTxnType = other.getChipTxnType();
        if (this$chipTxnType == null ? other$chipTxnType != null : !this$chipTxnType.equals(other$chipTxnType)) {
            return false;
        }
        String this$chipCurCode = this.getChipCurCode();
        String other$chipCurCode = other.getChipCurCode();
        if (this$chipCurCode == null ? other$chipCurCode != null : !this$chipCurCode.equals(other$chipCurCode)) {
            return false;
        }
        String this$appICProfile = this.getAppICProfile();
        String other$appICProfile = other.getAppICProfile();
        if (this$appICProfile == null ? other$appICProfile != null : !this$appICProfile.equals(other$appICProfile)) {
            return false;
        }
        String this$trlConCode = this.getTrlConCode();
        String other$trlConCode = other.getTrlConCode();
        if (this$trlConCode == null ? other$trlConCode != null : !this$trlConCode.equals(other$trlConCode)) {
            return false;
        }
        String this$cvmResult = this.getCvmResult();
        String other$cvmResult = other.getCvmResult();
        if (this$cvmResult == null ? other$cvmResult != null : !this$cvmResult.equals(other$cvmResult)) {
            return false;
        }
        String this$trlCapabilities = this.getTrlCapabilities();
        String other$trlCapabilities = other.getTrlCapabilities();
        if (this$trlCapabilities == null ? other$trlCapabilities != null : !this$trlCapabilities.equals(other$trlCapabilities)) {
            return false;
        }
        String this$chipTrlType = this.getChipTrlType();
        String other$chipTrlType = other.getChipTrlType();
        if (this$chipTrlType == null ? other$chipTrlType != null : !this$chipTrlType.equals(other$chipTrlType)) {
            return false;
        }
        String this$ifdSerNumber = this.getIfdSerNumber();
        String other$ifdSerNumber = other.getIfdSerNumber();
        if (this$ifdSerNumber == null ? other$ifdSerNumber != null : !this$ifdSerNumber.equals(other$ifdSerNumber)) {
            return false;
        }
        String this$tcc = this.getTcc();
        String other$tcc = other.getTcc();
        if (this$tcc == null ? other$tcc != null : !this$tcc.equals(other$tcc)) {
            return false;
        }
        String this$trlAppVerNumber = this.getTrlAppVerNumber();
        String other$trlAppVerNumber = other.getTrlAppVerNumber();
        if (this$trlAppVerNumber == null ? other$trlAppVerNumber != null : !this$trlAppVerNumber.equals(other$trlAppVerNumber)) {
            return false;
        }
        String this$issAuthData = this.getIssAuthData();
        String other$issAuthData = other.getIssAuthData();
        if (this$issAuthData == null ? other$issAuthData != null : !this$issAuthData.equals(other$issAuthData)) {
            return false;
        }
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
            return false;
        }
        String this$cardAccepStreetAddress = this.getCardAccepStreetAddress();
        String other$cardAccepStreetAddress = other.getCardAccepStreetAddress();
        if (this$cardAccepStreetAddress == null ? other$cardAccepStreetAddress != null : !this$cardAccepStreetAddress.equals(other$cardAccepStreetAddress)) {
            return false;
        }
        String this$cardAccepStateCode = this.getCardAccepStateCode();
        String other$cardAccepStateCode = other.getCardAccepStateCode();
        if (this$cardAccepStateCode == null ? other$cardAccepStateCode != null : !this$cardAccepStateCode.equals(other$cardAccepStateCode)) {
            return false;
        }
        String this$expiryDate = this.getExpiryDate();
        String other$expiryDate = other.getExpiryDate();
        if (this$expiryDate == null ? other$expiryDate != null : !this$expiryDate.equals(other$expiryDate)) {
            return false;
        }
        String this$ird = this.getIrd();
        String other$ird = other.getIrd();
        if (this$ird == null ? other$ird != null : !this$ird.equals(other$ird)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        if (this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks)) {
            return false;
        }
        String this$validationCode = this.getValidationCode();
        String other$validationCode = other.getValidationCode();
        if (this$validationCode == null ? other$validationCode != null : !this$validationCode.equals(other$validationCode)) {
            return false;
        }
        String this$marketSpecAuthDataInd = this.getMarketSpecAuthDataInd();
        String other$marketSpecAuthDataInd = other.getMarketSpecAuthDataInd();
        if (this$marketSpecAuthDataInd == null ? other$marketSpecAuthDataInd != null : !this$marketSpecAuthDataInd.equals(other$marketSpecAuthDataInd)) {
            return false;
        }
        String this$productId = this.getProductId();
        String other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) {
            return false;
        }
        String this$feePgmIndicator = this.getFeePgmIndicator();
        String other$feePgmIndicator = other.getFeePgmIndicator();
        if (this$feePgmIndicator == null ? other$feePgmIndicator != null : !this$feePgmIndicator.equals(other$feePgmIndicator)) {
            return false;
        }
        String this$banKCode = this.getBanKCode();
        String other$banKCode = other.getBanKCode();
        if (this$banKCode == null ? other$banKCode != null : !this$banKCode.equals(other$banKCode)) {
            return false;
        }
        String this$maid = this.getMaid();
        String other$maid = other.getMaid();
        if (this$maid == null ? other$maid != null : !this$maid.equals(other$maid)) {
            return false;
        }
        String this$chipTrlCapabilities = this.getChipTrlCapabilities();
        String other$chipTrlCapabilities = other.getChipTrlCapabilities();
        if (this$chipTrlCapabilities == null ? other$chipTrlCapabilities != null : !this$chipTrlCapabilities.equals(other$chipTrlCapabilities)) {
            return false;
        }
        String this$mvv = this.getMvv();
        String other$mvv = other.getMvv();
        if (this$mvv == null ? other$mvv != null : !this$mvv.equals(other$mvv)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$adtlAmounts = this.getAdtlAmounts();
        String other$adtlAmounts = other.getAdtlAmounts();
        if (this$adtlAmounts == null ? other$adtlAmounts != null : !this$adtlAmounts.equals(other$adtlAmounts)) {
            return false;
        }
        String this$outStatus = this.getOutStatus();
        String other$outStatus = other.getOutStatus();
        if (this$outStatus == null ? other$outStatus != null : !this$outStatus.equals(other$outStatus)) {
            return false;
        }
        String this$scheme = this.getScheme();
        String other$scheme = other.getScheme();
        return !(this$scheme == null ? other$scheme != null : !this$scheme.equals(other$scheme));
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
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Character $stagingFlag = this.getStagingFlag();
        result = result * 59 + ($stagingFlag == null ? 43 : ((Object)$stagingFlag).hashCode());
        Character $paymentFlag = this.getPaymentFlag();
        result = result * 59 + ($paymentFlag == null ? 43 : ((Object)$paymentFlag).hashCode());
        Integer $agrSernumber = this.getAgrSernumber();
        result = result * 59 + ($agrSernumber == null ? 43 : ((Object)$agrSernumber).hashCode());
        Character $cardInputAbility = this.getCardInputAbility();
        result = result * 59 + ($cardInputAbility == null ? 43 : ((Object)$cardInputAbility).hashCode());
        Character $cardCaptureAbility = this.getCardCaptureAbility();
        result = result * 59 + ($cardCaptureAbility == null ? 43 : ((Object)$cardCaptureAbility).hashCode());
        Character $chPresent = this.getChPresent();
        result = result * 59 + ($chPresent == null ? 43 : ((Object)$chPresent).hashCode());
        Character $cardPresent = this.getCardPresent();
        result = result * 59 + ($cardPresent == null ? 43 : ((Object)$cardPresent).hashCode());
        Double $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + ($cashBackAmount == null ? 43 : ((Object)$cashBackAmount).hashCode());
        Double $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + ($txnFeeAmount == null ? 43 : ((Object)$txnFeeAmount).hashCode());
        Double $setlAmount = this.getSetlAmount();
        result = result * 59 + ($setlAmount == null ? 43 : ((Object)$setlAmount).hashCode());
        Double $netAmount = this.getNetAmount();
        result = result * 59 + ($netAmount == null ? 43 : ((Object)$netAmount).hashCode());
        Character $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + ($settlementIndicator == null ? 43 : ((Object)$settlementIndicator).hashCode());
        Character $onusOffusFlag = this.getOnusOffusFlag();
        result = result * 59 + ($onusOffusFlag == null ? 43 : ((Object)$onusOffusFlag).hashCode());
        Character $revIndiCator = this.getRevIndiCator();
        result = result * 59 + ($revIndiCator == null ? 43 : ((Object)$revIndiCator).hashCode());
        Character $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + ($dmsSmsMode == null ? 43 : ((Object)$dmsSmsMode).hashCode());
        Character $chAuthAbility = this.getChAuthAbility();
        result = result * 59 + ($chAuthAbility == null ? 43 : ((Object)$chAuthAbility).hashCode());
        Character $chAuthEntity = this.getChAuthEntity();
        result = result * 59 + ($chAuthEntity == null ? 43 : ((Object)$chAuthEntity).hashCode());
        Character $cardOutPutAbility = this.getCardOutPutAbility();
        result = result * 59 + ($cardOutPutAbility == null ? 43 : ((Object)$cardOutPutAbility).hashCode());
        Character $trlOutPutAbility = this.getTrlOutPutAbility();
        result = result * 59 + ($trlOutPutAbility == null ? 43 : ((Object)$trlOutPutAbility).hashCode());
        Character $chAuthMethod = this.getChAuthMethod();
        result = result * 59 + ($chAuthMethod == null ? 43 : ((Object)$chAuthMethod).hashCode());
        Double $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : ((Object)$cryptAmount).hashCode());
        Double $chipCashBack = this.getChipCashBack();
        result = result * 59 + ($chipCashBack == null ? 43 : ((Object)$chipCashBack).hashCode());
        Character $meCategoryType = this.getMeCategoryType();
        result = result * 59 + ($meCategoryType == null ? 43 : ((Object)$meCategoryType).hashCode());
        Integer $irdSerNumber = this.getIrdSerNumber();
        result = result * 59 + ($irdSerNumber == null ? 43 : ((Object)$irdSerNumber).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        Double $irfPercent = this.getIrfPercent();
        result = result * 59 + ($irfPercent == null ? 43 : ((Object)$irfPercent).hashCode());
        Double $irfAmount = this.getIrfAmount();
        result = result * 59 + ($irfAmount == null ? 43 : ((Object)$irfAmount).hashCode());
        Character $chIdMethod = this.getChIdMethod();
        result = result * 59 + ($chIdMethod == null ? 43 : ((Object)$chIdMethod).hashCode());
        Character $cardInputMode = this.getCardInputMode();
        result = result * 59 + ($cardInputMode == null ? 43 : ((Object)$cardInputMode).hashCode());
        Character $authCharecteresticId = this.getAuthCharecteresticId();
        result = result * 59 + ($authCharecteresticId == null ? 43 : ((Object)$authCharecteresticId).hashCode());
        Character $spendQualificationInd = this.getSpendQualificationInd();
        result = result * 59 + ($spendQualificationInd == null ? 43 : ((Object)$spendQualificationInd).hashCode());
        Character $reImbursementAttribute = this.getReImbursementAttribute();
        result = result * 59 + ($reImbursementAttribute == null ? 43 : ((Object)$reImbursementAttribute).hashCode());
        Character $accountFundingSource = this.getAccountFundingSource();
        result = result * 59 + ($accountFundingSource == null ? 43 : ((Object)$accountFundingSource).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $stan = this.getStan();
        result = result * 59 + ($stan == null ? 43 : $stan.hashCode());
        String $authReason = this.getAuthReason();
        result = result * 59 + ($authReason == null ? 43 : $authReason.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $mePinCode = this.getMePinCode();
        result = result * 59 + ($mePinCode == null ? 43 : $mePinCode.hashCode());
        LocalDate $bussDate = this.getBussDate();
        result = result * 59 + ($bussDate == null ? 43 : ((Object)$bussDate).hashCode());
        String $txnUniqueId = this.getTxnUniqueId();
        result = result * 59 + ($txnUniqueId == null ? 43 : $txnUniqueId.hashCode());
        String $msgTypeId = this.getMsgTypeId();
        result = result * 59 + ($msgTypeId == null ? 43 : $msgTypeId.hashCode());
        String $oprtEnvironment = this.getOprtEnvironment();
        result = result * 59 + ($oprtEnvironment == null ? 43 : $oprtEnvironment.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        LocalDate $setlDate = this.getSetlDate();
        result = result * 59 + ($setlDate == null ? 43 : ((Object)$setlDate).hashCode());
        String $setlCurCode = this.getSetlCurCode();
        result = result * 59 + ($setlCurCode == null ? 43 : $setlCurCode.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        String $posConditionCode = this.getPosConditionCode();
        result = result * 59 + ($posConditionCode == null ? 43 : $posConditionCode.hashCode());
        String $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : $motoEcomIndicator.hashCode());
        String $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + ($cardSeqNumber == null ? 43 : $cardSeqNumber.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $terminalType = this.getTerminalType();
        result = result * 59 + ($terminalType == null ? 43 : $terminalType.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        LocalDate $centreProcDate = this.getCentreProcDate();
        result = result * 59 + ($centreProcDate == null ? 43 : ((Object)$centreProcDate).hashCode());
        String $cardCategory = this.getCardCategory();
        result = result * 59 + ($cardCategory == null ? 43 : $cardCategory.hashCode());
        String $cardSubCategory = this.getCardSubCategory();
        result = result * 59 + ($cardSubCategory == null ? 43 : $cardSubCategory.hashCode());
        String $networkData = this.getNetworkData();
        result = result * 59 + ($networkData == null ? 43 : $networkData.hashCode());
        LocalDateTime $txnDateTime = this.getTxnDateTime();
        result = result * 59 + ($txnDateTime == null ? 43 : ((Object)$txnDateTime).hashCode());
        String $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + ($acqinstIdCode == null ? 43 : $acqinstIdCode.hashCode());
        String $acqInstConCode = this.getAcqInstConCode();
        result = result * 59 + ($acqInstConCode == null ? 43 : $acqInstConCode.hashCode());
        String $appCryptogram = this.getAppCryptogram();
        result = result * 59 + ($appCryptogram == null ? 43 : $appCryptogram.hashCode());
        String $cryptInfoData = this.getCryptInfoData();
        result = result * 59 + ($cryptInfoData == null ? 43 : $cryptInfoData.hashCode());
        String $issAppData = this.getIssAppData();
        result = result * 59 + ($issAppData == null ? 43 : $issAppData.hashCode());
        String $upblNumber = this.getUpblNumber();
        result = result * 59 + ($upblNumber == null ? 43 : $upblNumber.hashCode());
        String $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + ($appTxnCounter == null ? 43 : $appTxnCounter.hashCode());
        String $trlVerResult = this.getTrlVerResult();
        result = result * 59 + ($trlVerResult == null ? 43 : $trlVerResult.hashCode());
        String $chipTxnDate = this.getChipTxnDate();
        result = result * 59 + ($chipTxnDate == null ? 43 : $chipTxnDate.hashCode());
        String $chipTxnType = this.getChipTxnType();
        result = result * 59 + ($chipTxnType == null ? 43 : $chipTxnType.hashCode());
        String $chipCurCode = this.getChipCurCode();
        result = result * 59 + ($chipCurCode == null ? 43 : $chipCurCode.hashCode());
        String $appICProfile = this.getAppICProfile();
        result = result * 59 + ($appICProfile == null ? 43 : $appICProfile.hashCode());
        String $trlConCode = this.getTrlConCode();
        result = result * 59 + ($trlConCode == null ? 43 : $trlConCode.hashCode());
        String $cvmResult = this.getCvmResult();
        result = result * 59 + ($cvmResult == null ? 43 : $cvmResult.hashCode());
        String $trlCapabilities = this.getTrlCapabilities();
        result = result * 59 + ($trlCapabilities == null ? 43 : $trlCapabilities.hashCode());
        String $chipTrlType = this.getChipTrlType();
        result = result * 59 + ($chipTrlType == null ? 43 : $chipTrlType.hashCode());
        String $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + ($ifdSerNumber == null ? 43 : $ifdSerNumber.hashCode());
        String $tcc = this.getTcc();
        result = result * 59 + ($tcc == null ? 43 : $tcc.hashCode());
        String $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + ($trlAppVerNumber == null ? 43 : $trlAppVerNumber.hashCode());
        String $issAuthData = this.getIssAuthData();
        result = result * 59 + ($issAuthData == null ? 43 : $issAuthData.hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $cardAccepStreetAddress = this.getCardAccepStreetAddress();
        result = result * 59 + ($cardAccepStreetAddress == null ? 43 : $cardAccepStreetAddress.hashCode());
        String $cardAccepStateCode = this.getCardAccepStateCode();
        result = result * 59 + ($cardAccepStateCode == null ? 43 : $cardAccepStateCode.hashCode());
        String $expiryDate = this.getExpiryDate();
        result = result * 59 + ($expiryDate == null ? 43 : $expiryDate.hashCode());
        String $ird = this.getIrd();
        result = result * 59 + ($ird == null ? 43 : $ird.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        String $validationCode = this.getValidationCode();
        result = result * 59 + ($validationCode == null ? 43 : $validationCode.hashCode());
        String $marketSpecAuthDataInd = this.getMarketSpecAuthDataInd();
        result = result * 59 + ($marketSpecAuthDataInd == null ? 43 : $marketSpecAuthDataInd.hashCode());
        String $productId = this.getProductId();
        result = result * 59 + ($productId == null ? 43 : $productId.hashCode());
        String $feePgmIndicator = this.getFeePgmIndicator();
        result = result * 59 + ($feePgmIndicator == null ? 43 : $feePgmIndicator.hashCode());
        String $banKCode = this.getBanKCode();
        result = result * 59 + ($banKCode == null ? 43 : $banKCode.hashCode());
        String $maid = this.getMaid();
        result = result * 59 + ($maid == null ? 43 : $maid.hashCode());
        String $chipTrlCapabilities = this.getChipTrlCapabilities();
        result = result * 59 + ($chipTrlCapabilities == null ? 43 : $chipTrlCapabilities.hashCode());
        String $mvv = this.getMvv();
        result = result * 59 + ($mvv == null ? 43 : $mvv.hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $adtlAmounts = this.getAdtlAmounts();
        result = result * 59 + ($adtlAmounts == null ? 43 : $adtlAmounts.hashCode());
        String $outStatus = this.getOutStatus();
        result = result * 59 + ($outStatus == null ? 43 : $outStatus.hashCode());
        String $scheme = this.getScheme();
        result = result * 59 + ($scheme == null ? 43 : $scheme.hashCode());
        return result;
    }

    public String toString() {
        return "PosTransactionEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", genStatus=" + this.getGenStatus() + ", jobNumber=" + this.getJobNumber() + ", rrn=" + this.getRrn() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", cardNumber=" + this.getCardNumber() + ", txnAmount=" + this.getTxnAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", responseCode=" + this.getResponseCode() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", txnType=" + this.getTxnType() + ", procCode=" + this.getProcCode() + ", stan=" + this.getStan() + ", authReason=" + this.getAuthReason() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", mePinCode=" + this.getMePinCode() + ", stagingFlag=" + this.getStagingFlag() + ", paymentFlag=" + this.getPaymentFlag() + ", bussDate=" + String.valueOf(this.getBussDate()) + ", txnUniqueId=" + this.getTxnUniqueId() + ", msgTypeId=" + this.getMsgTypeId() + ", agrSernumber=" + this.getAgrSernumber() + ", cardInputAbility=" + this.getCardInputAbility() + ", cardCaptureAbility=" + this.getCardCaptureAbility() + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent() + ", oprtEnvironment=" + this.getOprtEnvironment() + ", cashBackAmount=" + this.getCashBackAmount() + ", txnCurCode=" + this.getTxnCurCode() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", setlAmount=" + this.getSetlAmount() + ", setlDate=" + String.valueOf(this.getSetlDate()) + ", setlCurCode=" + this.getSetlCurCode() + ", posEntryMode=" + this.getPosEntryMode() + ", posConditionCode=" + this.getPosConditionCode() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", serviceCode=" + this.getServiceCode() + ", mcc=" + this.getMcc() + ", terminalType=" + this.getTerminalType() + ", network=" + this.getNetwork() + ", netAmount=" + this.getNetAmount() + ", settlementIndicator=" + this.getSettlementIndicator() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", onusOffusFlag=" + this.getOnusOffusFlag() + ", revIndiCator=" + this.getRevIndiCator() + ", cardCategory=" + this.getCardCategory() + ", cardSubCategory=" + this.getCardSubCategory() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", networkData=" + this.getNetworkData() + ", chAuthAbility=" + this.getChAuthAbility() + ", chAuthEntity=" + this.getChAuthEntity() + ", cardOutPutAbility=" + this.getCardOutPutAbility() + ", trlOutPutAbility=" + this.getTrlOutPutAbility() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", acqInstConCode=" + this.getAcqInstConCode() + ", chAuthMethod=" + this.getChAuthMethod() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", issAppData=" + this.getIssAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", trlVerResult=" + this.getTrlVerResult() + ", chipTxnDate=" + this.getChipTxnDate() + ", chipTxnType=" + this.getChipTxnType() + ", cryptAmount=" + this.getCryptAmount() + ", chipCurCode=" + this.getChipCurCode() + ", appICProfile=" + this.getAppICProfile() + ", trlConCode=" + this.getTrlConCode() + ", chipCashBack=" + this.getChipCashBack() + ", cvmResult=" + this.getCvmResult() + ", trlCapabilities=" + this.getTrlCapabilities() + ", chipTrlType=" + this.getChipTrlType() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", tcc=" + this.getTcc() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", issAuthData=" + this.getIssAuthData() + ", txnId=" + this.getTxnId() + ", meCategoryType=" + this.getMeCategoryType() + ", cardAccepStreetAddress=" + this.getCardAccepStreetAddress() + ", cardAccepStateCode=" + this.getCardAccepStateCode() + ", expiryDate=" + this.getExpiryDate() + ", irdSerNumber=" + this.getIrdSerNumber() + ", ird=" + this.getIrd() + ", irfFixed=" + this.getIrfFixed() + ", irfPercent=" + this.getIrfPercent() + ", irfAmount=" + this.getIrfAmount() + ", remarks=" + this.getRemarks() + ", chIdMethod=" + this.getChIdMethod() + ", cardInputMode=" + (this.getCardInputMode() + ", authCharecteresticId=" + this.getAuthCharecteresticId() + ", validationCode=" + this.getValidationCode() + ", marketSpecAuthDataInd=" + this.getMarketSpecAuthDataInd() + ", productId=" + this.getProductId() + ", spendQualificationInd=" + this.getSpendQualificationInd() + ", reImbursementAttribute=" + this.getReImbursementAttribute() + ", feePgmIndicator=" + this.getFeePgmIndicator() + ", accountFundingSource=" + this.getAccountFundingSource() + ", banKCode=" + this.getBanKCode() + ", maid=" + this.getMaid() + ", chipTrlCapabilities=" + this.getChipTrlCapabilities() + ", mvv=" + this.getMvv() + ", txnCode=" + this.getTxnCode() + ", adtlAmounts=" + this.getAdtlAmounts() + ", outStatus=" + this.getOutStatus() + ", scheme=" + this.getScheme() + ")");
    }

    public PosTransactionEntity(Integer serialNumber, LocalDateTime lastUpdated, Integer updatedUser, Integer insCode, Integer intCode, Integer genStatus, Integer jobNumber, String rrn, Character cardType, Character cardDomIntlFlag, String cardNumber, Double txnAmount, LocalDateTime localDateTime, String responseCode, String approvalCode, String terminalId, String merchantId, String txnType, String procCode, String stan, String authReason, String meName, String meCity, String meCountry, String mePinCode, Character stagingFlag, Character paymentFlag, LocalDate bussDate, String txnUniqueId, String msgTypeId, Integer agrSernumber, Character cardInputAbility, Character cardCaptureAbility, Character chPresent, Character cardPresent, String oprtEnvironment, Double cashBackAmount, String txnCurCode, Double txnFeeAmount, Double setlAmount, LocalDate setlDate, String setlCurCode, String posEntryMode, String posConditionCode, String motoEcomIndicator, String cardSeqNumber, String serviceCode, String mcc, String terminalType, String network, Double netAmount, Character settlementIndicator, LocalDate centreProcDate, Character onusOffusFlag, Character revIndiCator, String cardCategory, String cardSubCategory, Character dmsSmsMode, String networkData, Character chAuthAbility, Character chAuthEntity, Character cardOutPutAbility, Character trlOutPutAbility, LocalDateTime txnDateTime, String acqinstIdCode, String acqInstConCode, Character chAuthMethod, String appCryptogram, String cryptInfoData, String issAppData, String upblNumber, String appTxnCounter, String trlVerResult, String chipTxnDate, String chipTxnType, Double cryptAmount, String chipCurCode, String appICProfile, String trlConCode, Double chipCashBack, String cvmResult, String trlCapabilities, String chipTrlType, String ifdSerNumber, String tcc, String trlAppVerNumber, String issAuthData, String txnId, Character meCategoryType, String cardAccepStreetAddress, String cardAccepStateCode, String expiryDate, Integer irdSerNumber, String ird, Double irfFixed, Double irfPercent, Double irfAmount, String remarks, Character chIdMethod, Character cardInputMode, Character authCharecteresticId, String validationCode, String marketSpecAuthDataInd, String productId, Character spendQualificationInd, Character reImbursementAttribute, String feePgmIndicator, Character accountFundingSource, String banKCode, String maid, String chipTrlCapabilities, String mvv, String txnCode, String adtlAmounts, String outStatus, String scheme) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.insCode = insCode;
        this.intCode = intCode;
        this.genStatus = genStatus;
        this.jobNumber = jobNumber;
        this.rrn = rrn;
        this.cardType = cardType;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.cardNumber = cardNumber;
        this.txnAmount = txnAmount;
        this.localDateTime = localDateTime;
        this.responseCode = responseCode;
        this.approvalCode = approvalCode;
        this.terminalId = terminalId;
        this.merchantId = merchantId;
        this.txnType = txnType;
        this.procCode = procCode;
        this.stan = stan;
        this.authReason = authReason;
        this.meName = meName;
        this.meCity = meCity;
        this.meCountry = meCountry;
        this.mePinCode = mePinCode;
        this.stagingFlag = stagingFlag;
        this.paymentFlag = paymentFlag;
        this.bussDate = bussDate;
        this.txnUniqueId = txnUniqueId;
        this.msgTypeId = msgTypeId;
        this.agrSernumber = agrSernumber;
        this.cardInputAbility = cardInputAbility;
        this.cardCaptureAbility = cardCaptureAbility;
        this.chPresent = chPresent;
        this.cardPresent = cardPresent;
        this.oprtEnvironment = oprtEnvironment;
        this.cashBackAmount = cashBackAmount;
        this.txnCurCode = txnCurCode;
        this.txnFeeAmount = txnFeeAmount;
        this.setlAmount = setlAmount;
        this.setlDate = setlDate;
        this.setlCurCode = setlCurCode;
        this.posEntryMode = posEntryMode;
        this.posConditionCode = posConditionCode;
        this.motoEcomIndicator = motoEcomIndicator;
        this.cardSeqNumber = cardSeqNumber;
        this.serviceCode = serviceCode;
        this.mcc = mcc;
        this.terminalType = terminalType;
        this.network = network;
        this.netAmount = netAmount;
        this.settlementIndicator = settlementIndicator;
        this.centreProcDate = centreProcDate;
        this.onusOffusFlag = onusOffusFlag;
        this.revIndiCator = revIndiCator;
        this.cardCategory = cardCategory;
        this.cardSubCategory = cardSubCategory;
        this.dmsSmsMode = dmsSmsMode;
        this.networkData = networkData;
        this.chAuthAbility = chAuthAbility;
        this.chAuthEntity = chAuthEntity;
        this.cardOutPutAbility = cardOutPutAbility;
        this.trlOutPutAbility = trlOutPutAbility;
        this.txnDateTime = txnDateTime;
        this.acqinstIdCode = acqinstIdCode;
        this.acqInstConCode = acqInstConCode;
        this.chAuthMethod = chAuthMethod;
        this.appCryptogram = appCryptogram;
        this.cryptInfoData = cryptInfoData;
        this.issAppData = issAppData;
        this.upblNumber = upblNumber;
        this.appTxnCounter = appTxnCounter;
        this.trlVerResult = trlVerResult;
        this.chipTxnDate = chipTxnDate;
        this.chipTxnType = chipTxnType;
        this.cryptAmount = cryptAmount;
        this.chipCurCode = chipCurCode;
        this.appICProfile = appICProfile;
        this.trlConCode = trlConCode;
        this.chipCashBack = chipCashBack;
        this.cvmResult = cvmResult;
        this.trlCapabilities = trlCapabilities;
        this.chipTrlType = chipTrlType;
        this.ifdSerNumber = ifdSerNumber;
        this.tcc = tcc;
        this.trlAppVerNumber = trlAppVerNumber;
        this.issAuthData = issAuthData;
        this.txnId = txnId;
        this.meCategoryType = meCategoryType;
        this.cardAccepStreetAddress = cardAccepStreetAddress;
        this.cardAccepStateCode = cardAccepStateCode;
        this.expiryDate = expiryDate;
        this.irdSerNumber = irdSerNumber;
        this.ird = ird;
        this.irfFixed = irfFixed;
        this.irfPercent = irfPercent;
        this.irfAmount = irfAmount;
        this.remarks = remarks;
        this.chIdMethod = chIdMethod;
        this.cardInputMode = cardInputMode;
        this.authCharecteresticId = authCharecteresticId;
        this.validationCode = validationCode;
        this.marketSpecAuthDataInd = marketSpecAuthDataInd;
        this.productId = productId;
        this.spendQualificationInd = spendQualificationInd;
        this.reImbursementAttribute = reImbursementAttribute;
        this.feePgmIndicator = feePgmIndicator;
        this.accountFundingSource = accountFundingSource;
        this.banKCode = banKCode;
        this.maid = maid;
        this.chipTrlCapabilities = chipTrlCapabilities;
        this.mvv = mvv;
        this.txnCode = txnCode;
        this.adtlAmounts = adtlAmounts;
        this.outStatus = outStatus;
        this.scheme = scheme;
    }

    public PosTransactionEntity() {
    }
}

