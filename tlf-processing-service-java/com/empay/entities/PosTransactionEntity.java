// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.lang.invoke.CallSite;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.StringConcatFactory;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "POS_TRANSACTIONS")
public class PosTransactionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PTR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "PTR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "PTR_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "PTR_INS_CODE")
    private Integer insCode;
    @Column(name = "PTR_INT_CODE")
    private Integer intCode;
    @Column(name = "PTR_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "PTR_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name = "PTR_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "PTR_CARD_TYPE")
    private Character cardType;
    @Column(name = "PTR_CARD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "PTR_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "PTR_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "PTR_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "PTR_RESP_CODE")
    private String responseCode;
    @Column(name = "PTR_APPR_CODE")
    private String approvalCode;
    @Column(name = "PTR_TERMINAL_ID")
    private String terminalId;
    @Column(name = "PTR_MERCHANT_ID")
    private String merchantId;
    @Column(name = "PTR_TXN_TYPE")
    private String txnType;
    @Column(name = "PTR_PROC_CODE")
    private String procCode;
    @Column(name = "PTR_STAN")
    private String stan;
    @Column(name = "PTR_AUTH_REASON")
    private String authReason;
    @Column(name = "PTR_ME_NAME")
    private String meName;
    @Column(name = "PTR_ME_CITY")
    private String meCity;
    @Column(name = "PTR_ME_COUNTRY")
    private String meCountry;
    @Column(name = "PTR_ME_PIN_CODE")
    private String mePinCode;
    @Column(name = "PTR_STAGING_FLAG")
    private Character stagingFlag;
    @Column(name = "PTR_PAYMENT_FLAG")
    private Character paymentFlag;
    @Column(name = "PTR_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name = "PTR_TXN_UNIQUE_ID")
    private String txnUniqueId;
    @Column(name = "PTR_MSG_TYPE_ID")
    private String msgTypeId;
    @Column(name = "PTR_AGR_SER_NUMBER")
    private Integer agrSernumber;
    @Column(name = "PTR_CARD_INPUT_ABILITY")
    private Character cardInputAbility;
    @Column(name = "PTR_CARD_CAPTURE_ABILITY")
    private Character cardCaptureAbility;
    @Column(name = "PTR_CH_PRESENT")
    private Character chPresent;
    @Column(name = "PTR_CARD_PRESENT")
    private Character cardPresent;
    @Column(name = "PTR_OPRT_ENVIRONMENT")
    private String oprtEnvironment;
    @Column(name = "PTR_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "PTR_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "PTR_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name = "PTR_SETL_AMOUNT")
    private Double setlAmount;
    @Column(name = "PTR_SETL_DATE")
    private LocalDate setlDate;
    @Column(name = "PTR_SETL_CUR_CODE")
    private String setlCurCode;
    @Column(name = "PTR_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name = "PTR_POS_CONDITION_CODE")
    private String posConditionCode;
    @Column(name = "PTR_MOTO_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name = "PTR_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name = "PTR_SERVICE_CODE")
    private String serviceCode;
    @Column(name = "PTR_MCC")
    private String mcc;
    @Column(name = "PTR_TRL_TYPE")
    private String terminalType;
    @Column(name = "PTR_NETWORK")
    private String network;
    @Column(name = "PTR_MER_NET_AMOUNT")
    private Double netAmount;
    @Column(name = "PTR_SETL_FLAG")
    private Character settlementIndicator;
    @Column(name = "PTR_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name = "PTR_ONUS_OFFUS_FLAG")
    private Character onusOffusFlag;
    @Column(name = "PTR_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name = "PTR_CARD_CATEGORY")
    private String cardCategory;
    @Column(name = "PTR_CARD_SUB_CATEGORY")
    private String cardSubCategory;
    @Column(name = "PTR_DMS_SMS_MODE")
    private Character dmsSmsMode;
    @Column(name = "PTR_NETWORK_DATA")
    private String networkData;
    @Column(name = "PTR_CH_AUTH_ABILITY")
    private Character chAuthAbility;
    @Column(name = "PTR_CH_AUTH_ENTITY")
    private Character chAuthEntity;
    @Column(name = "PTR_CARD_OUTPUT_ABILITY")
    private Character cardOutPutAbility;
    @Column(name = "PTR_TRL_OUTPUT_ABILITTY")
    private Character trlOutPutAbility;
    @Column(name = "PTR_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name = "PTR_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name = "PTR_ACQ_INST_CON_CODE")
    private String acqInstConCode;
    @Column(name = "PTR_CH_AUTH_METHOD")
    private Character chAuthMethod;
    @Column(name = "PTR_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name = "PTR_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name = "PTR_ISS_APP_DATA")
    private String issAppData;
    @Column(name = "PTR_UPBL_NUMBER")
    private String upblNumber;
    @Column(name = "PTR_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name = "PTR_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name = "PTR_CHIP_TXN_DATE")
    private String chipTxnDate;
    @Column(name = "PTR_CHIP_TXN_TYPE")
    private String chipTxnType;
    @Column(name = "PTR_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name = "PTR_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name = "PTR_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name = "PTR_TRL_CON_CODE")
    private String trlConCode;
    @Column(name = "PTR_CHIP_CASHBACK")
    private Double chipCashBack;
    @Column(name = "PTR_CVM_RESULTS")
    private String cvmResult;
    @Column(name = "PTR_TRL_CAPABILITIES")
    private String trlCapabilities;
    @Column(name = "PTR_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name = "PTR_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name = "PTR_TCC")
    private String tcc;
    @Column(name = "PTR_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name = "PTR_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name = "PTR_TXN_ID")
    private String txnId;
    @Column(name = "PTR_ME_CATEGORY_TYPE")
    private Character meCategoryType;
    @Column(name = "PTR_CARD_ACC_STREET_ADDRESS")
    private String cardAccepStreetAddress;
    @Column(name = "PTR_CARD_ACC_STATE_CODE")
    private String cardAccepStateCode;
    @Column(name = "PTR_EXPIRY_DATE")
    private String expiryDate;
    @Column(name = "PTR_IRD_SER_NUMBER")
    private Integer irdSerNumber;
    @Column(name = "PTR_IRD")
    private String ird;
    @Column(name = "PTR_IRF_FIXED")
    private Double irfFixed;
    @Column(name = "PTR_IRF_PERCENT")
    private Double irfPercent;
    @Column(name = "PTR_IRF_AMOUNT")
    private Double irfAmount;
    @Column(name = "PTR_REMARKS")
    private String remarks;
    @Column(name = "PTR_CH_ID_METHOD")
    private Character chIdMethod;
    @Column(name = "PTR_CARD_INPUT_MODE")
    private Character cardInputMode;
    @Column(name = "PTR_AUTH_CHAR_INDICATOR")
    private Character authCharecteresticId;
    @Column(name = "PTR_VALIDATION_CODE")
    private String validationCode;
    @Column(name = "PTR_MARKET_SPEC_DATA_IND")
    private String marketSpecAuthDataInd;
    @Column(name = "PTR_PRODUCT_ID")
    private String productId;
    @Column(name = "PTR_SPEND_QUALI_IND")
    private Character spendQualificationInd;
    @Column(name = "PTR_REIMB_ATTRIBUTE")
    private Character reImbursementAttribute;
    @Column(name = "PTR_FEE_PRG_INDICATOR")
    private String feePgmIndicator;
    @Column(name = "PTR_ACC_FUND_SOURCE")
    private Character accountFundingSource;
    @Column(name = "PTR_BANK_CODE")
    private String banKCode;
    @Column(name = "PTR_MAID")
    private String maid;
    @Column(name = "PTR_CHIP_TRL_CAPABILITIES")
    private String chipTrlCapabilities;
    @Column(name = "PTR_MVV")
    private String mvv;
    @Column(name = "PTR_TXN_CODE")
    private String txnCode;
    @Column(name = "PTR_ADTL_AMOUNTS")
    private String adtlAmounts;
    @Column(name = "PTR_ENC_CARD_NUMBER")
    private String encCardNumber;
    @Column(name = "PTR_FORM_FACTOR_INDICATOR")
    private String formFactorIndicator;
    @Column(name = "PTR_IRF_MAX_AMOUNT")
    private Double irfMaxAmount;
    @Column(name = "PTR_IRF_MIN_AMOUNT")
    private Double irfMinAmount;
    @Column(name = "PTR_OUT_STATUS")
    private String outStatus;
    @Column(name = "PTR_INC_STATUS")
    private String incomingStatus;
    @Column(name = "PTR_ME_COUNTRY_OF_ORIGIN")
    private String meCountryOfOrigin;
    @Column(name = "PTR_AUTH_AMOUNT")
    private Double authAmount;
    @Column(name = "PTR_IRF_AMOUNT_USD")
    private Double irfAmountUSD;
    @Column(name = "PTR_ORG_RRN")
    private String originalRRN;
    @Column(name = "PTR_SE_NUMBER")
    private String amexMerchantId;
    @Column(name = "PTR_MER_CONTACT_PHONE_NO")
    private String merchantContactInfo;
    @Column(name = "PTR_TRL_LOCATION")
    private String merchantLocationId;
    @Column(name = "PTR_LOCATION_REGION_CODE")
    private String locationRegionCode;
    @Column(name = "PTR_REFUND_INDICATOR")
    private Character refundIndicator;
    @Column(name = "PTR_INVOICE_NUMBER")
    private String invoiceNumber;
    @Column(name = "PTR_TIP_AMOUNT")
    private Double tipAmount;
    @Column(name = "PTR_DEDICATED_FILE_NAME")
    private String dedicatedFileName;
    @Column(name = "PTR_SCHEME")
    private String scheme;
    @Column(name = "PTR_DCC_AMOUNT")
    private Double dccAmount;
    @Column(name = "PTR_DCC_CURRENCY")
    private String dccCurrency;
    @Column(name = "PTR_DCC_INDICATOR")
    private Character dccIndicator;
    @Column(name = "PTR_DCC_EXCHANGE_RATE")
    private Double dccExchangeRate;
    @Column(name = "PTR_MPOS_ACC_DEV_TYPE")
    private Character mposAccDevType;
    @Column(name = "PTR_ACC_TRL_INDICATOR")
    private Character acceptanceTrlIndicator;
    @Column(name = "PTR_ACC_URL_ADDRESS")
    private String accepterUrlAddress;
    
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
    
    public String getEncCardNumber() {
        return this.encCardNumber;
    }
    
    public String getFormFactorIndicator() {
        return this.formFactorIndicator;
    }
    
    public Double getIrfMaxAmount() {
        return this.irfMaxAmount;
    }
    
    public Double getIrfMinAmount() {
        return this.irfMinAmount;
    }
    
    public String getOutStatus() {
        return this.outStatus;
    }
    
    public String getIncomingStatus() {
        return this.incomingStatus;
    }
    
    public String getMeCountryOfOrigin() {
        return this.meCountryOfOrigin;
    }
    
    public Double getAuthAmount() {
        return this.authAmount;
    }
    
    public Double getIrfAmountUSD() {
        return this.irfAmountUSD;
    }
    
    public String getOriginalRRN() {
        return this.originalRRN;
    }
    
    public String getAmexMerchantId() {
        return this.amexMerchantId;
    }
    
    public String getMerchantContactInfo() {
        return this.merchantContactInfo;
    }
    
    public String getMerchantLocationId() {
        return this.merchantLocationId;
    }
    
    public String getLocationRegionCode() {
        return this.locationRegionCode;
    }
    
    public Character getRefundIndicator() {
        return this.refundIndicator;
    }
    
    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }
    
    public Double getTipAmount() {
        return this.tipAmount;
    }
    
    public String getDedicatedFileName() {
        return this.dedicatedFileName;
    }
    
    public String getScheme() {
        return this.scheme;
    }
    
    public Double getDccAmount() {
        return this.dccAmount;
    }
    
    public String getDccCurrency() {
        return this.dccCurrency;
    }
    
    public Character getDccIndicator() {
        return this.dccIndicator;
    }
    
    public Double getDccExchangeRate() {
        return this.dccExchangeRate;
    }
    
    public Character getMposAccDevType() {
        return this.mposAccDevType;
    }
    
    public Character getAcceptanceTrlIndicator() {
        return this.acceptanceTrlIndicator;
    }
    
    public String getAccepterUrlAddress() {
        return this.accepterUrlAddress;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
    }
    
    public void setJobNumber(final Integer jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public void setAuthReason(final String authReason) {
        this.authReason = authReason;
    }
    
    public void setMeName(final String meName) {
        this.meName = meName;
    }
    
    public void setMeCity(final String meCity) {
        this.meCity = meCity;
    }
    
    public void setMeCountry(final String meCountry) {
        this.meCountry = meCountry;
    }
    
    public void setMePinCode(final String mePinCode) {
        this.mePinCode = mePinCode;
    }
    
    public void setStagingFlag(final Character stagingFlag) {
        this.stagingFlag = stagingFlag;
    }
    
    public void setPaymentFlag(final Character paymentFlag) {
        this.paymentFlag = paymentFlag;
    }
    
    public void setBussDate(final LocalDate bussDate) {
        this.bussDate = bussDate;
    }
    
    public void setTxnUniqueId(final String txnUniqueId) {
        this.txnUniqueId = txnUniqueId;
    }
    
    public void setMsgTypeId(final String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }
    
    public void setAgrSernumber(final Integer agrSernumber) {
        this.agrSernumber = agrSernumber;
    }
    
    public void setCardInputAbility(final Character cardInputAbility) {
        this.cardInputAbility = cardInputAbility;
    }
    
    public void setCardCaptureAbility(final Character cardCaptureAbility) {
        this.cardCaptureAbility = cardCaptureAbility;
    }
    
    public void setChPresent(final Character chPresent) {
        this.chPresent = chPresent;
    }
    
    public void setCardPresent(final Character cardPresent) {
        this.cardPresent = cardPresent;
    }
    
    public void setOprtEnvironment(final String oprtEnvironment) {
        this.oprtEnvironment = oprtEnvironment;
    }
    
    public void setCashBackAmount(final Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }
    
    public void setTxnFeeAmount(final Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }
    
    public void setSetlAmount(final Double setlAmount) {
        this.setlAmount = setlAmount;
    }
    
    public void setSetlDate(final LocalDate setlDate) {
        this.setlDate = setlDate;
    }
    
    public void setSetlCurCode(final String setlCurCode) {
        this.setlCurCode = setlCurCode;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    public void setPosConditionCode(final String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }
    
    public void setMotoEcomIndicator(final String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setCardSeqNumber(final String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }
    
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setTerminalType(final String terminalType) {
        this.terminalType = terminalType;
    }
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setNetAmount(final Double netAmount) {
        this.netAmount = netAmount;
    }
    
    public void setSettlementIndicator(final Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }
    
    public void setCentreProcDate(final LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }
    
    public void setOnusOffusFlag(final Character onusOffusFlag) {
        this.onusOffusFlag = onusOffusFlag;
    }
    
    public void setRevIndiCator(final Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }
    
    public void setCardCategory(final String cardCategory) {
        this.cardCategory = cardCategory;
    }
    
    public void setCardSubCategory(final String cardSubCategory) {
        this.cardSubCategory = cardSubCategory;
    }
    
    public void setDmsSmsMode(final Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }
    
    public void setNetworkData(final String networkData) {
        this.networkData = networkData;
    }
    
    public void setChAuthAbility(final Character chAuthAbility) {
        this.chAuthAbility = chAuthAbility;
    }
    
    public void setChAuthEntity(final Character chAuthEntity) {
        this.chAuthEntity = chAuthEntity;
    }
    
    public void setCardOutPutAbility(final Character cardOutPutAbility) {
        this.cardOutPutAbility = cardOutPutAbility;
    }
    
    public void setTrlOutPutAbility(final Character trlOutPutAbility) {
        this.trlOutPutAbility = trlOutPutAbility;
    }
    
    public void setTxnDateTime(final LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }
    
    public void setAcqinstIdCode(final String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }
    
    public void setAcqInstConCode(final String acqInstConCode) {
        this.acqInstConCode = acqInstConCode;
    }
    
    public void setChAuthMethod(final Character chAuthMethod) {
        this.chAuthMethod = chAuthMethod;
    }
    
    public void setAppCryptogram(final String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }
    
    public void setCryptInfoData(final String cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }
    
    public void setIssAppData(final String issAppData) {
        this.issAppData = issAppData;
    }
    
    public void setUpblNumber(final String upblNumber) {
        this.upblNumber = upblNumber;
    }
    
    public void setAppTxnCounter(final String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }
    
    public void setTrlVerResult(final String trlVerResult) {
        this.trlVerResult = trlVerResult;
    }
    
    public void setChipTxnDate(final String chipTxnDate) {
        this.chipTxnDate = chipTxnDate;
    }
    
    public void setChipTxnType(final String chipTxnType) {
        this.chipTxnType = chipTxnType;
    }
    
    public void setCryptAmount(final Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }
    
    public void setChipCurCode(final String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }
    
    public void setAppICProfile(final String appICProfile) {
        this.appICProfile = appICProfile;
    }
    
    public void setTrlConCode(final String trlConCode) {
        this.trlConCode = trlConCode;
    }
    
    public void setChipCashBack(final Double chipCashBack) {
        this.chipCashBack = chipCashBack;
    }
    
    public void setCvmResult(final String cvmResult) {
        this.cvmResult = cvmResult;
    }
    
    public void setTrlCapabilities(final String trlCapabilities) {
        this.trlCapabilities = trlCapabilities;
    }
    
    public void setChipTrlType(final String chipTrlType) {
        this.chipTrlType = chipTrlType;
    }
    
    public void setIfdSerNumber(final String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }
    
    public void setTcc(final String tcc) {
        this.tcc = tcc;
    }
    
    public void setTrlAppVerNumber(final String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }
    
    public void setIssAuthData(final String issAuthData) {
        this.issAuthData = issAuthData;
    }
    
    public void setTxnId(final String txnId) {
        this.txnId = txnId;
    }
    
    public void setMeCategoryType(final Character meCategoryType) {
        this.meCategoryType = meCategoryType;
    }
    
    public void setCardAccepStreetAddress(final String cardAccepStreetAddress) {
        this.cardAccepStreetAddress = cardAccepStreetAddress;
    }
    
    public void setCardAccepStateCode(final String cardAccepStateCode) {
        this.cardAccepStateCode = cardAccepStateCode;
    }
    
    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setIrdSerNumber(final Integer irdSerNumber) {
        this.irdSerNumber = irdSerNumber;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    public void setIrfPercent(final Double irfPercent) {
        this.irfPercent = irfPercent;
    }
    
    public void setIrfAmount(final Double irfAmount) {
        this.irfAmount = irfAmount;
    }
    
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }
    
    public void setChIdMethod(final Character chIdMethod) {
        this.chIdMethod = chIdMethod;
    }
    
    public void setCardInputMode(final Character cardInputMode) {
        this.cardInputMode = cardInputMode;
    }
    
    public void setAuthCharecteresticId(final Character authCharecteresticId) {
        this.authCharecteresticId = authCharecteresticId;
    }
    
    public void setValidationCode(final String validationCode) {
        this.validationCode = validationCode;
    }
    
    public void setMarketSpecAuthDataInd(final String marketSpecAuthDataInd) {
        this.marketSpecAuthDataInd = marketSpecAuthDataInd;
    }
    
    public void setProductId(final String productId) {
        this.productId = productId;
    }
    
    public void setSpendQualificationInd(final Character spendQualificationInd) {
        this.spendQualificationInd = spendQualificationInd;
    }
    
    public void setReImbursementAttribute(final Character reImbursementAttribute) {
        this.reImbursementAttribute = reImbursementAttribute;
    }
    
    public void setFeePgmIndicator(final String feePgmIndicator) {
        this.feePgmIndicator = feePgmIndicator;
    }
    
    public void setAccountFundingSource(final Character accountFundingSource) {
        this.accountFundingSource = accountFundingSource;
    }
    
    public void setBanKCode(final String banKCode) {
        this.banKCode = banKCode;
    }
    
    public void setMaid(final String maid) {
        this.maid = maid;
    }
    
    public void setChipTrlCapabilities(final String chipTrlCapabilities) {
        this.chipTrlCapabilities = chipTrlCapabilities;
    }
    
    public void setMvv(final String mvv) {
        this.mvv = mvv;
    }
    
    public void setTxnCode(final String txnCode) {
        this.txnCode = txnCode;
    }
    
    public void setAdtlAmounts(final String adtlAmounts) {
        this.adtlAmounts = adtlAmounts;
    }
    
    public void setEncCardNumber(final String encCardNumber) {
        this.encCardNumber = encCardNumber;
    }
    
    public void setFormFactorIndicator(final String formFactorIndicator) {
        this.formFactorIndicator = formFactorIndicator;
    }
    
    public void setIrfMaxAmount(final Double irfMaxAmount) {
        this.irfMaxAmount = irfMaxAmount;
    }
    
    public void setIrfMinAmount(final Double irfMinAmount) {
        this.irfMinAmount = irfMinAmount;
    }
    
    public void setOutStatus(final String outStatus) {
        this.outStatus = outStatus;
    }
    
    public void setIncomingStatus(final String incomingStatus) {
        this.incomingStatus = incomingStatus;
    }
    
    public void setMeCountryOfOrigin(final String meCountryOfOrigin) {
        this.meCountryOfOrigin = meCountryOfOrigin;
    }
    
    public void setAuthAmount(final Double authAmount) {
        this.authAmount = authAmount;
    }
    
    public void setIrfAmountUSD(final Double irfAmountUSD) {
        this.irfAmountUSD = irfAmountUSD;
    }
    
    public void setOriginalRRN(final String originalRRN) {
        this.originalRRN = originalRRN;
    }
    
    public void setAmexMerchantId(final String amexMerchantId) {
        this.amexMerchantId = amexMerchantId;
    }
    
    public void setMerchantContactInfo(final String merchantContactInfo) {
        this.merchantContactInfo = merchantContactInfo;
    }
    
    public void setMerchantLocationId(final String merchantLocationId) {
        this.merchantLocationId = merchantLocationId;
    }
    
    public void setLocationRegionCode(final String locationRegionCode) {
        this.locationRegionCode = locationRegionCode;
    }
    
    public void setRefundIndicator(final Character refundIndicator) {
        this.refundIndicator = refundIndicator;
    }
    
    public void setInvoiceNumber(final String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    
    public void setTipAmount(final Double tipAmount) {
        this.tipAmount = tipAmount;
    }
    
    public void setDedicatedFileName(final String dedicatedFileName) {
        this.dedicatedFileName = dedicatedFileName;
    }
    
    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }
    
    public void setDccAmount(final Double dccAmount) {
        this.dccAmount = dccAmount;
    }
    
    public void setDccCurrency(final String dccCurrency) {
        this.dccCurrency = dccCurrency;
    }
    
    public void setDccIndicator(final Character dccIndicator) {
        this.dccIndicator = dccIndicator;
    }
    
    public void setDccExchangeRate(final Double dccExchangeRate) {
        this.dccExchangeRate = dccExchangeRate;
    }
    
    public void setMposAccDevType(final Character mposAccDevType) {
        this.mposAccDevType = mposAccDevType;
    }
    
    public void setAcceptanceTrlIndicator(final Character acceptanceTrlIndicator) {
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
    }
    
    public void setAccepterUrlAddress(final String accepterUrlAddress) {
        this.accepterUrlAddress = accepterUrlAddress;
    }
    
    @Override
    public boolean equals(final Object o) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_0         /* this */
        //     2: if_acmpne       7
        //     5: iconst_1       
        //     6: ireturn        
        //     7: aload_1         /* o */
        //     8: instanceof      Lcom/empay/entities/PosTransactionEntity;
        //    11: ifne            16
        //    14: iconst_0       
        //    15: ireturn        
        //    16: aload_1         /* o */
        //    17: checkcast       Lcom/empay/entities/PosTransactionEntity;
        //    20: astore_2        /* other */
        //    21: aload_2         /* other */
        //    22: aload_0         /* this */
        //    23: invokevirtual   com/empay/entities/PosTransactionEntity.canEqual:(Ljava/lang/Object;)Z
        //    26: ifne            31
        //    29: iconst_0       
        //    30: ireturn        
        //    31: aload_0         /* this */
        //    32: invokevirtual   com/empay/entities/PosTransactionEntity.getSerialNumber:()Ljava/lang/Integer;
        //    35: astore_3        /* this$serialNumber */
        //    36: aload_2         /* other */
        //    37: invokevirtual   com/empay/entities/PosTransactionEntity.getSerialNumber:()Ljava/lang/Integer;
        //    40: astore          other$serialNumber
        //    42: aload_3         /* this$serialNumber */
        //    43: ifnonnull       54
        //    46: aload           other$serialNumber
        //    48: ifnull          65
        //    51: goto            63
        //    54: aload_3         /* this$serialNumber */
        //    55: aload           other$serialNumber
        //    57: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    60: ifne            65
        //    63: iconst_0       
        //    64: ireturn        
        //    65: aload_0         /* this */
        //    66: invokevirtual   com/empay/entities/PosTransactionEntity.getUpdatedUser:()Ljava/lang/Integer;
        //    69: astore          this$updatedUser
        //    71: aload_2         /* other */
        //    72: invokevirtual   com/empay/entities/PosTransactionEntity.getUpdatedUser:()Ljava/lang/Integer;
        //    75: astore          other$updatedUser
        //    77: aload           this$updatedUser
        //    79: ifnonnull       90
        //    82: aload           other$updatedUser
        //    84: ifnull          102
        //    87: goto            100
        //    90: aload           this$updatedUser
        //    92: aload           other$updatedUser
        //    94: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    97: ifne            102
        //   100: iconst_0       
        //   101: ireturn        
        //   102: aload_0         /* this */
        //   103: invokevirtual   com/empay/entities/PosTransactionEntity.getInsCode:()Ljava/lang/Integer;
        //   106: astore          this$insCode
        //   108: aload_2         /* other */
        //   109: invokevirtual   com/empay/entities/PosTransactionEntity.getInsCode:()Ljava/lang/Integer;
        //   112: astore          other$insCode
        //   114: aload           this$insCode
        //   116: ifnonnull       127
        //   119: aload           other$insCode
        //   121: ifnull          139
        //   124: goto            137
        //   127: aload           this$insCode
        //   129: aload           other$insCode
        //   131: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   134: ifne            139
        //   137: iconst_0       
        //   138: ireturn        
        //   139: aload_0         /* this */
        //   140: invokevirtual   com/empay/entities/PosTransactionEntity.getIntCode:()Ljava/lang/Integer;
        //   143: astore          this$intCode
        //   145: aload_2         /* other */
        //   146: invokevirtual   com/empay/entities/PosTransactionEntity.getIntCode:()Ljava/lang/Integer;
        //   149: astore          other$intCode
        //   151: aload           this$intCode
        //   153: ifnonnull       164
        //   156: aload           other$intCode
        //   158: ifnull          176
        //   161: goto            174
        //   164: aload           this$intCode
        //   166: aload           other$intCode
        //   168: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   171: ifne            176
        //   174: iconst_0       
        //   175: ireturn        
        //   176: aload_0         /* this */
        //   177: invokevirtual   com/empay/entities/PosTransactionEntity.getGenStatus:()Ljava/lang/Integer;
        //   180: astore          this$genStatus
        //   182: aload_2         /* other */
        //   183: invokevirtual   com/empay/entities/PosTransactionEntity.getGenStatus:()Ljava/lang/Integer;
        //   186: astore          other$genStatus
        //   188: aload           this$genStatus
        //   190: ifnonnull       201
        //   193: aload           other$genStatus
        //   195: ifnull          213
        //   198: goto            211
        //   201: aload           this$genStatus
        //   203: aload           other$genStatus
        //   205: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   208: ifne            213
        //   211: iconst_0       
        //   212: ireturn        
        //   213: aload_0         /* this */
        //   214: invokevirtual   com/empay/entities/PosTransactionEntity.getJobNumber:()Ljava/lang/Integer;
        //   217: astore          this$jobNumber
        //   219: aload_2         /* other */
        //   220: invokevirtual   com/empay/entities/PosTransactionEntity.getJobNumber:()Ljava/lang/Integer;
        //   223: astore          other$jobNumber
        //   225: aload           this$jobNumber
        //   227: ifnonnull       238
        //   230: aload           other$jobNumber
        //   232: ifnull          250
        //   235: goto            248
        //   238: aload           this$jobNumber
        //   240: aload           other$jobNumber
        //   242: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   245: ifne            250
        //   248: iconst_0       
        //   249: ireturn        
        //   250: aload_0         /* this */
        //   251: invokevirtual   com/empay/entities/PosTransactionEntity.getCardType:()Ljava/lang/Character;
        //   254: astore          this$cardType
        //   256: aload_2         /* other */
        //   257: invokevirtual   com/empay/entities/PosTransactionEntity.getCardType:()Ljava/lang/Character;
        //   260: astore          other$cardType
        //   262: aload           this$cardType
        //   264: ifnonnull       275
        //   267: aload           other$cardType
        //   269: ifnull          287
        //   272: goto            285
        //   275: aload           this$cardType
        //   277: aload           other$cardType
        //   279: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   282: ifne            287
        //   285: iconst_0       
        //   286: ireturn        
        //   287: aload_0         /* this */
        //   288: invokevirtual   com/empay/entities/PosTransactionEntity.getCardDomIntlFlag:()Ljava/lang/Character;
        //   291: astore          this$cardDomIntlFlag
        //   293: aload_2         /* other */
        //   294: invokevirtual   com/empay/entities/PosTransactionEntity.getCardDomIntlFlag:()Ljava/lang/Character;
        //   297: astore          other$cardDomIntlFlag
        //   299: aload           this$cardDomIntlFlag
        //   301: ifnonnull       312
        //   304: aload           other$cardDomIntlFlag
        //   306: ifnull          324
        //   309: goto            322
        //   312: aload           this$cardDomIntlFlag
        //   314: aload           other$cardDomIntlFlag
        //   316: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   319: ifne            324
        //   322: iconst_0       
        //   323: ireturn        
        //   324: aload_0         /* this */
        //   325: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnAmount:()Ljava/lang/Double;
        //   328: astore          this$txnAmount
        //   330: aload_2         /* other */
        //   331: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnAmount:()Ljava/lang/Double;
        //   334: astore          other$txnAmount
        //   336: aload           this$txnAmount
        //   338: ifnonnull       349
        //   341: aload           other$txnAmount
        //   343: ifnull          361
        //   346: goto            359
        //   349: aload           this$txnAmount
        //   351: aload           other$txnAmount
        //   353: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   356: ifne            361
        //   359: iconst_0       
        //   360: ireturn        
        //   361: aload_0         /* this */
        //   362: invokevirtual   com/empay/entities/PosTransactionEntity.getStagingFlag:()Ljava/lang/Character;
        //   365: astore          this$stagingFlag
        //   367: aload_2         /* other */
        //   368: invokevirtual   com/empay/entities/PosTransactionEntity.getStagingFlag:()Ljava/lang/Character;
        //   371: astore          other$stagingFlag
        //   373: aload           this$stagingFlag
        //   375: ifnonnull       386
        //   378: aload           other$stagingFlag
        //   380: ifnull          398
        //   383: goto            396
        //   386: aload           this$stagingFlag
        //   388: aload           other$stagingFlag
        //   390: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   393: ifne            398
        //   396: iconst_0       
        //   397: ireturn        
        //   398: aload_0         /* this */
        //   399: invokevirtual   com/empay/entities/PosTransactionEntity.getPaymentFlag:()Ljava/lang/Character;
        //   402: astore          this$paymentFlag
        //   404: aload_2         /* other */
        //   405: invokevirtual   com/empay/entities/PosTransactionEntity.getPaymentFlag:()Ljava/lang/Character;
        //   408: astore          other$paymentFlag
        //   410: aload           this$paymentFlag
        //   412: ifnonnull       423
        //   415: aload           other$paymentFlag
        //   417: ifnull          435
        //   420: goto            433
        //   423: aload           this$paymentFlag
        //   425: aload           other$paymentFlag
        //   427: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   430: ifne            435
        //   433: iconst_0       
        //   434: ireturn        
        //   435: aload_0         /* this */
        //   436: invokevirtual   com/empay/entities/PosTransactionEntity.getAgrSernumber:()Ljava/lang/Integer;
        //   439: astore          this$agrSernumber
        //   441: aload_2         /* other */
        //   442: invokevirtual   com/empay/entities/PosTransactionEntity.getAgrSernumber:()Ljava/lang/Integer;
        //   445: astore          other$agrSernumber
        //   447: aload           this$agrSernumber
        //   449: ifnonnull       460
        //   452: aload           other$agrSernumber
        //   454: ifnull          472
        //   457: goto            470
        //   460: aload           this$agrSernumber
        //   462: aload           other$agrSernumber
        //   464: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   467: ifne            472
        //   470: iconst_0       
        //   471: ireturn        
        //   472: aload_0         /* this */
        //   473: invokevirtual   com/empay/entities/PosTransactionEntity.getCardInputAbility:()Ljava/lang/Character;
        //   476: astore          this$cardInputAbility
        //   478: aload_2         /* other */
        //   479: invokevirtual   com/empay/entities/PosTransactionEntity.getCardInputAbility:()Ljava/lang/Character;
        //   482: astore          other$cardInputAbility
        //   484: aload           this$cardInputAbility
        //   486: ifnonnull       497
        //   489: aload           other$cardInputAbility
        //   491: ifnull          509
        //   494: goto            507
        //   497: aload           this$cardInputAbility
        //   499: aload           other$cardInputAbility
        //   501: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   504: ifne            509
        //   507: iconst_0       
        //   508: ireturn        
        //   509: aload_0         /* this */
        //   510: invokevirtual   com/empay/entities/PosTransactionEntity.getCardCaptureAbility:()Ljava/lang/Character;
        //   513: astore          this$cardCaptureAbility
        //   515: aload_2         /* other */
        //   516: invokevirtual   com/empay/entities/PosTransactionEntity.getCardCaptureAbility:()Ljava/lang/Character;
        //   519: astore          other$cardCaptureAbility
        //   521: aload           this$cardCaptureAbility
        //   523: ifnonnull       534
        //   526: aload           other$cardCaptureAbility
        //   528: ifnull          546
        //   531: goto            544
        //   534: aload           this$cardCaptureAbility
        //   536: aload           other$cardCaptureAbility
        //   538: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   541: ifne            546
        //   544: iconst_0       
        //   545: ireturn        
        //   546: aload_0         /* this */
        //   547: invokevirtual   com/empay/entities/PosTransactionEntity.getChPresent:()Ljava/lang/Character;
        //   550: astore          this$chPresent
        //   552: aload_2         /* other */
        //   553: invokevirtual   com/empay/entities/PosTransactionEntity.getChPresent:()Ljava/lang/Character;
        //   556: astore          other$chPresent
        //   558: aload           this$chPresent
        //   560: ifnonnull       571
        //   563: aload           other$chPresent
        //   565: ifnull          583
        //   568: goto            581
        //   571: aload           this$chPresent
        //   573: aload           other$chPresent
        //   575: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   578: ifne            583
        //   581: iconst_0       
        //   582: ireturn        
        //   583: aload_0         /* this */
        //   584: invokevirtual   com/empay/entities/PosTransactionEntity.getCardPresent:()Ljava/lang/Character;
        //   587: astore          this$cardPresent
        //   589: aload_2         /* other */
        //   590: invokevirtual   com/empay/entities/PosTransactionEntity.getCardPresent:()Ljava/lang/Character;
        //   593: astore          other$cardPresent
        //   595: aload           this$cardPresent
        //   597: ifnonnull       608
        //   600: aload           other$cardPresent
        //   602: ifnull          620
        //   605: goto            618
        //   608: aload           this$cardPresent
        //   610: aload           other$cardPresent
        //   612: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   615: ifne            620
        //   618: iconst_0       
        //   619: ireturn        
        //   620: aload_0         /* this */
        //   621: invokevirtual   com/empay/entities/PosTransactionEntity.getCashBackAmount:()Ljava/lang/Double;
        //   624: astore          this$cashBackAmount
        //   626: aload_2         /* other */
        //   627: invokevirtual   com/empay/entities/PosTransactionEntity.getCashBackAmount:()Ljava/lang/Double;
        //   630: astore          other$cashBackAmount
        //   632: aload           this$cashBackAmount
        //   634: ifnonnull       645
        //   637: aload           other$cashBackAmount
        //   639: ifnull          657
        //   642: goto            655
        //   645: aload           this$cashBackAmount
        //   647: aload           other$cashBackAmount
        //   649: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   652: ifne            657
        //   655: iconst_0       
        //   656: ireturn        
        //   657: aload_0         /* this */
        //   658: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnFeeAmount:()Ljava/lang/Double;
        //   661: astore          this$txnFeeAmount
        //   663: aload_2         /* other */
        //   664: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnFeeAmount:()Ljava/lang/Double;
        //   667: astore          other$txnFeeAmount
        //   669: aload           this$txnFeeAmount
        //   671: ifnonnull       682
        //   674: aload           other$txnFeeAmount
        //   676: ifnull          694
        //   679: goto            692
        //   682: aload           this$txnFeeAmount
        //   684: aload           other$txnFeeAmount
        //   686: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   689: ifne            694
        //   692: iconst_0       
        //   693: ireturn        
        //   694: aload_0         /* this */
        //   695: invokevirtual   com/empay/entities/PosTransactionEntity.getSetlAmount:()Ljava/lang/Double;
        //   698: astore          this$setlAmount
        //   700: aload_2         /* other */
        //   701: invokevirtual   com/empay/entities/PosTransactionEntity.getSetlAmount:()Ljava/lang/Double;
        //   704: astore          other$setlAmount
        //   706: aload           this$setlAmount
        //   708: ifnonnull       719
        //   711: aload           other$setlAmount
        //   713: ifnull          731
        //   716: goto            729
        //   719: aload           this$setlAmount
        //   721: aload           other$setlAmount
        //   723: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   726: ifne            731
        //   729: iconst_0       
        //   730: ireturn        
        //   731: aload_0         /* this */
        //   732: invokevirtual   com/empay/entities/PosTransactionEntity.getNetAmount:()Ljava/lang/Double;
        //   735: astore          this$netAmount
        //   737: aload_2         /* other */
        //   738: invokevirtual   com/empay/entities/PosTransactionEntity.getNetAmount:()Ljava/lang/Double;
        //   741: astore          other$netAmount
        //   743: aload           this$netAmount
        //   745: ifnonnull       756
        //   748: aload           other$netAmount
        //   750: ifnull          768
        //   753: goto            766
        //   756: aload           this$netAmount
        //   758: aload           other$netAmount
        //   760: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   763: ifne            768
        //   766: iconst_0       
        //   767: ireturn        
        //   768: aload_0         /* this */
        //   769: invokevirtual   com/empay/entities/PosTransactionEntity.getSettlementIndicator:()Ljava/lang/Character;
        //   772: astore          this$settlementIndicator
        //   774: aload_2         /* other */
        //   775: invokevirtual   com/empay/entities/PosTransactionEntity.getSettlementIndicator:()Ljava/lang/Character;
        //   778: astore          other$settlementIndicator
        //   780: aload           this$settlementIndicator
        //   782: ifnonnull       793
        //   785: aload           other$settlementIndicator
        //   787: ifnull          805
        //   790: goto            803
        //   793: aload           this$settlementIndicator
        //   795: aload           other$settlementIndicator
        //   797: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   800: ifne            805
        //   803: iconst_0       
        //   804: ireturn        
        //   805: aload_0         /* this */
        //   806: invokevirtual   com/empay/entities/PosTransactionEntity.getOnusOffusFlag:()Ljava/lang/Character;
        //   809: astore          this$onusOffusFlag
        //   811: aload_2         /* other */
        //   812: invokevirtual   com/empay/entities/PosTransactionEntity.getOnusOffusFlag:()Ljava/lang/Character;
        //   815: astore          other$onusOffusFlag
        //   817: aload           this$onusOffusFlag
        //   819: ifnonnull       830
        //   822: aload           other$onusOffusFlag
        //   824: ifnull          842
        //   827: goto            840
        //   830: aload           this$onusOffusFlag
        //   832: aload           other$onusOffusFlag
        //   834: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   837: ifne            842
        //   840: iconst_0       
        //   841: ireturn        
        //   842: aload_0         /* this */
        //   843: invokevirtual   com/empay/entities/PosTransactionEntity.getRevIndiCator:()Ljava/lang/Character;
        //   846: astore          this$revIndiCator
        //   848: aload_2         /* other */
        //   849: invokevirtual   com/empay/entities/PosTransactionEntity.getRevIndiCator:()Ljava/lang/Character;
        //   852: astore          other$revIndiCator
        //   854: aload           this$revIndiCator
        //   856: ifnonnull       867
        //   859: aload           other$revIndiCator
        //   861: ifnull          879
        //   864: goto            877
        //   867: aload           this$revIndiCator
        //   869: aload           other$revIndiCator
        //   871: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   874: ifne            879
        //   877: iconst_0       
        //   878: ireturn        
        //   879: aload_0         /* this */
        //   880: invokevirtual   com/empay/entities/PosTransactionEntity.getDmsSmsMode:()Ljava/lang/Character;
        //   883: astore          this$dmsSmsMode
        //   885: aload_2         /* other */
        //   886: invokevirtual   com/empay/entities/PosTransactionEntity.getDmsSmsMode:()Ljava/lang/Character;
        //   889: astore          other$dmsSmsMode
        //   891: aload           this$dmsSmsMode
        //   893: ifnonnull       904
        //   896: aload           other$dmsSmsMode
        //   898: ifnull          916
        //   901: goto            914
        //   904: aload           this$dmsSmsMode
        //   906: aload           other$dmsSmsMode
        //   908: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   911: ifne            916
        //   914: iconst_0       
        //   915: ireturn        
        //   916: aload_0         /* this */
        //   917: invokevirtual   com/empay/entities/PosTransactionEntity.getChAuthAbility:()Ljava/lang/Character;
        //   920: astore          this$chAuthAbility
        //   922: aload_2         /* other */
        //   923: invokevirtual   com/empay/entities/PosTransactionEntity.getChAuthAbility:()Ljava/lang/Character;
        //   926: astore          other$chAuthAbility
        //   928: aload           this$chAuthAbility
        //   930: ifnonnull       941
        //   933: aload           other$chAuthAbility
        //   935: ifnull          953
        //   938: goto            951
        //   941: aload           this$chAuthAbility
        //   943: aload           other$chAuthAbility
        //   945: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   948: ifne            953
        //   951: iconst_0       
        //   952: ireturn        
        //   953: aload_0         /* this */
        //   954: invokevirtual   com/empay/entities/PosTransactionEntity.getChAuthEntity:()Ljava/lang/Character;
        //   957: astore          this$chAuthEntity
        //   959: aload_2         /* other */
        //   960: invokevirtual   com/empay/entities/PosTransactionEntity.getChAuthEntity:()Ljava/lang/Character;
        //   963: astore          other$chAuthEntity
        //   965: aload           this$chAuthEntity
        //   967: ifnonnull       978
        //   970: aload           other$chAuthEntity
        //   972: ifnull          990
        //   975: goto            988
        //   978: aload           this$chAuthEntity
        //   980: aload           other$chAuthEntity
        //   982: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   985: ifne            990
        //   988: iconst_0       
        //   989: ireturn        
        //   990: aload_0         /* this */
        //   991: invokevirtual   com/empay/entities/PosTransactionEntity.getCardOutPutAbility:()Ljava/lang/Character;
        //   994: astore          this$cardOutPutAbility
        //   996: aload_2         /* other */
        //   997: invokevirtual   com/empay/entities/PosTransactionEntity.getCardOutPutAbility:()Ljava/lang/Character;
        //  1000: astore          other$cardOutPutAbility
        //  1002: aload           this$cardOutPutAbility
        //  1004: ifnonnull       1015
        //  1007: aload           other$cardOutPutAbility
        //  1009: ifnull          1027
        //  1012: goto            1025
        //  1015: aload           this$cardOutPutAbility
        //  1017: aload           other$cardOutPutAbility
        //  1019: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1022: ifne            1027
        //  1025: iconst_0       
        //  1026: ireturn        
        //  1027: aload_0         /* this */
        //  1028: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlOutPutAbility:()Ljava/lang/Character;
        //  1031: astore          this$trlOutPutAbility
        //  1033: aload_2         /* other */
        //  1034: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlOutPutAbility:()Ljava/lang/Character;
        //  1037: astore          other$trlOutPutAbility
        //  1039: aload           this$trlOutPutAbility
        //  1041: ifnonnull       1052
        //  1044: aload           other$trlOutPutAbility
        //  1046: ifnull          1064
        //  1049: goto            1062
        //  1052: aload           this$trlOutPutAbility
        //  1054: aload           other$trlOutPutAbility
        //  1056: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1059: ifne            1064
        //  1062: iconst_0       
        //  1063: ireturn        
        //  1064: aload_0         /* this */
        //  1065: invokevirtual   com/empay/entities/PosTransactionEntity.getChAuthMethod:()Ljava/lang/Character;
        //  1068: astore          this$chAuthMethod
        //  1070: aload_2         /* other */
        //  1071: invokevirtual   com/empay/entities/PosTransactionEntity.getChAuthMethod:()Ljava/lang/Character;
        //  1074: astore          other$chAuthMethod
        //  1076: aload           this$chAuthMethod
        //  1078: ifnonnull       1089
        //  1081: aload           other$chAuthMethod
        //  1083: ifnull          1101
        //  1086: goto            1099
        //  1089: aload           this$chAuthMethod
        //  1091: aload           other$chAuthMethod
        //  1093: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1096: ifne            1101
        //  1099: iconst_0       
        //  1100: ireturn        
        //  1101: aload_0         /* this */
        //  1102: invokevirtual   com/empay/entities/PosTransactionEntity.getCryptAmount:()Ljava/lang/Double;
        //  1105: astore          this$cryptAmount
        //  1107: aload_2         /* other */
        //  1108: invokevirtual   com/empay/entities/PosTransactionEntity.getCryptAmount:()Ljava/lang/Double;
        //  1111: astore          other$cryptAmount
        //  1113: aload           this$cryptAmount
        //  1115: ifnonnull       1126
        //  1118: aload           other$cryptAmount
        //  1120: ifnull          1138
        //  1123: goto            1136
        //  1126: aload           this$cryptAmount
        //  1128: aload           other$cryptAmount
        //  1130: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1133: ifne            1138
        //  1136: iconst_0       
        //  1137: ireturn        
        //  1138: aload_0         /* this */
        //  1139: invokevirtual   com/empay/entities/PosTransactionEntity.getChipCashBack:()Ljava/lang/Double;
        //  1142: astore          this$chipCashBack
        //  1144: aload_2         /* other */
        //  1145: invokevirtual   com/empay/entities/PosTransactionEntity.getChipCashBack:()Ljava/lang/Double;
        //  1148: astore          other$chipCashBack
        //  1150: aload           this$chipCashBack
        //  1152: ifnonnull       1163
        //  1155: aload           other$chipCashBack
        //  1157: ifnull          1175
        //  1160: goto            1173
        //  1163: aload           this$chipCashBack
        //  1165: aload           other$chipCashBack
        //  1167: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1170: ifne            1175
        //  1173: iconst_0       
        //  1174: ireturn        
        //  1175: aload_0         /* this */
        //  1176: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCategoryType:()Ljava/lang/Character;
        //  1179: astore          this$meCategoryType
        //  1181: aload_2         /* other */
        //  1182: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCategoryType:()Ljava/lang/Character;
        //  1185: astore          other$meCategoryType
        //  1187: aload           this$meCategoryType
        //  1189: ifnonnull       1200
        //  1192: aload           other$meCategoryType
        //  1194: ifnull          1212
        //  1197: goto            1210
        //  1200: aload           this$meCategoryType
        //  1202: aload           other$meCategoryType
        //  1204: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1207: ifne            1212
        //  1210: iconst_0       
        //  1211: ireturn        
        //  1212: aload_0         /* this */
        //  1213: invokevirtual   com/empay/entities/PosTransactionEntity.getIrdSerNumber:()Ljava/lang/Integer;
        //  1216: astore          this$irdSerNumber
        //  1218: aload_2         /* other */
        //  1219: invokevirtual   com/empay/entities/PosTransactionEntity.getIrdSerNumber:()Ljava/lang/Integer;
        //  1222: astore          other$irdSerNumber
        //  1224: aload           this$irdSerNumber
        //  1226: ifnonnull       1237
        //  1229: aload           other$irdSerNumber
        //  1231: ifnull          1249
        //  1234: goto            1247
        //  1237: aload           this$irdSerNumber
        //  1239: aload           other$irdSerNumber
        //  1241: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1244: ifne            1249
        //  1247: iconst_0       
        //  1248: ireturn        
        //  1249: aload_0         /* this */
        //  1250: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfFixed:()Ljava/lang/Double;
        //  1253: astore          this$irfFixed
        //  1255: aload_2         /* other */
        //  1256: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfFixed:()Ljava/lang/Double;
        //  1259: astore          other$irfFixed
        //  1261: aload           this$irfFixed
        //  1263: ifnonnull       1274
        //  1266: aload           other$irfFixed
        //  1268: ifnull          1286
        //  1271: goto            1284
        //  1274: aload           this$irfFixed
        //  1276: aload           other$irfFixed
        //  1278: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1281: ifne            1286
        //  1284: iconst_0       
        //  1285: ireturn        
        //  1286: aload_0         /* this */
        //  1287: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfPercent:()Ljava/lang/Double;
        //  1290: astore          this$irfPercent
        //  1292: aload_2         /* other */
        //  1293: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfPercent:()Ljava/lang/Double;
        //  1296: astore          other$irfPercent
        //  1298: aload           this$irfPercent
        //  1300: ifnonnull       1311
        //  1303: aload           other$irfPercent
        //  1305: ifnull          1323
        //  1308: goto            1321
        //  1311: aload           this$irfPercent
        //  1313: aload           other$irfPercent
        //  1315: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1318: ifne            1323
        //  1321: iconst_0       
        //  1322: ireturn        
        //  1323: aload_0         /* this */
        //  1324: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfAmount:()Ljava/lang/Double;
        //  1327: astore          this$irfAmount
        //  1329: aload_2         /* other */
        //  1330: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfAmount:()Ljava/lang/Double;
        //  1333: astore          other$irfAmount
        //  1335: aload           this$irfAmount
        //  1337: ifnonnull       1348
        //  1340: aload           other$irfAmount
        //  1342: ifnull          1360
        //  1345: goto            1358
        //  1348: aload           this$irfAmount
        //  1350: aload           other$irfAmount
        //  1352: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1355: ifne            1360
        //  1358: iconst_0       
        //  1359: ireturn        
        //  1360: aload_0         /* this */
        //  1361: invokevirtual   com/empay/entities/PosTransactionEntity.getChIdMethod:()Ljava/lang/Character;
        //  1364: astore          this$chIdMethod
        //  1366: aload_2         /* other */
        //  1367: invokevirtual   com/empay/entities/PosTransactionEntity.getChIdMethod:()Ljava/lang/Character;
        //  1370: astore          other$chIdMethod
        //  1372: aload           this$chIdMethod
        //  1374: ifnonnull       1385
        //  1377: aload           other$chIdMethod
        //  1379: ifnull          1397
        //  1382: goto            1395
        //  1385: aload           this$chIdMethod
        //  1387: aload           other$chIdMethod
        //  1389: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1392: ifne            1397
        //  1395: iconst_0       
        //  1396: ireturn        
        //  1397: aload_0         /* this */
        //  1398: invokevirtual   com/empay/entities/PosTransactionEntity.getCardInputMode:()Ljava/lang/Character;
        //  1401: astore          this$cardInputMode
        //  1403: aload_2         /* other */
        //  1404: invokevirtual   com/empay/entities/PosTransactionEntity.getCardInputMode:()Ljava/lang/Character;
        //  1407: astore          other$cardInputMode
        //  1409: aload           this$cardInputMode
        //  1411: ifnonnull       1422
        //  1414: aload           other$cardInputMode
        //  1416: ifnull          1434
        //  1419: goto            1432
        //  1422: aload           this$cardInputMode
        //  1424: aload           other$cardInputMode
        //  1426: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1429: ifne            1434
        //  1432: iconst_0       
        //  1433: ireturn        
        //  1434: aload_0         /* this */
        //  1435: invokevirtual   com/empay/entities/PosTransactionEntity.getAuthCharecteresticId:()Ljava/lang/Character;
        //  1438: astore          this$authCharecteresticId
        //  1440: aload_2         /* other */
        //  1441: invokevirtual   com/empay/entities/PosTransactionEntity.getAuthCharecteresticId:()Ljava/lang/Character;
        //  1444: astore          other$authCharecteresticId
        //  1446: aload           this$authCharecteresticId
        //  1448: ifnonnull       1459
        //  1451: aload           other$authCharecteresticId
        //  1453: ifnull          1471
        //  1456: goto            1469
        //  1459: aload           this$authCharecteresticId
        //  1461: aload           other$authCharecteresticId
        //  1463: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1466: ifne            1471
        //  1469: iconst_0       
        //  1470: ireturn        
        //  1471: aload_0         /* this */
        //  1472: invokevirtual   com/empay/entities/PosTransactionEntity.getSpendQualificationInd:()Ljava/lang/Character;
        //  1475: astore          this$spendQualificationInd
        //  1477: aload_2         /* other */
        //  1478: invokevirtual   com/empay/entities/PosTransactionEntity.getSpendQualificationInd:()Ljava/lang/Character;
        //  1481: astore          other$spendQualificationInd
        //  1483: aload           this$spendQualificationInd
        //  1485: ifnonnull       1496
        //  1488: aload           other$spendQualificationInd
        //  1490: ifnull          1508
        //  1493: goto            1506
        //  1496: aload           this$spendQualificationInd
        //  1498: aload           other$spendQualificationInd
        //  1500: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1503: ifne            1508
        //  1506: iconst_0       
        //  1507: ireturn        
        //  1508: aload_0         /* this */
        //  1509: invokevirtual   com/empay/entities/PosTransactionEntity.getReImbursementAttribute:()Ljava/lang/Character;
        //  1512: astore          this$reImbursementAttribute
        //  1514: aload_2         /* other */
        //  1515: invokevirtual   com/empay/entities/PosTransactionEntity.getReImbursementAttribute:()Ljava/lang/Character;
        //  1518: astore          other$reImbursementAttribute
        //  1520: aload           this$reImbursementAttribute
        //  1522: ifnonnull       1533
        //  1525: aload           other$reImbursementAttribute
        //  1527: ifnull          1545
        //  1530: goto            1543
        //  1533: aload           this$reImbursementAttribute
        //  1535: aload           other$reImbursementAttribute
        //  1537: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1540: ifne            1545
        //  1543: iconst_0       
        //  1544: ireturn        
        //  1545: aload_0         /* this */
        //  1546: invokevirtual   com/empay/entities/PosTransactionEntity.getAccountFundingSource:()Ljava/lang/Character;
        //  1549: astore          this$accountFundingSource
        //  1551: aload_2         /* other */
        //  1552: invokevirtual   com/empay/entities/PosTransactionEntity.getAccountFundingSource:()Ljava/lang/Character;
        //  1555: astore          other$accountFundingSource
        //  1557: aload           this$accountFundingSource
        //  1559: ifnonnull       1570
        //  1562: aload           other$accountFundingSource
        //  1564: ifnull          1582
        //  1567: goto            1580
        //  1570: aload           this$accountFundingSource
        //  1572: aload           other$accountFundingSource
        //  1574: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1577: ifne            1582
        //  1580: iconst_0       
        //  1581: ireturn        
        //  1582: aload_0         /* this */
        //  1583: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfMaxAmount:()Ljava/lang/Double;
        //  1586: astore          this$irfMaxAmount
        //  1588: aload_2         /* other */
        //  1589: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfMaxAmount:()Ljava/lang/Double;
        //  1592: astore          other$irfMaxAmount
        //  1594: aload           this$irfMaxAmount
        //  1596: ifnonnull       1607
        //  1599: aload           other$irfMaxAmount
        //  1601: ifnull          1619
        //  1604: goto            1617
        //  1607: aload           this$irfMaxAmount
        //  1609: aload           other$irfMaxAmount
        //  1611: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1614: ifne            1619
        //  1617: iconst_0       
        //  1618: ireturn        
        //  1619: aload_0         /* this */
        //  1620: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfMinAmount:()Ljava/lang/Double;
        //  1623: astore          this$irfMinAmount
        //  1625: aload_2         /* other */
        //  1626: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfMinAmount:()Ljava/lang/Double;
        //  1629: astore          other$irfMinAmount
        //  1631: aload           this$irfMinAmount
        //  1633: ifnonnull       1644
        //  1636: aload           other$irfMinAmount
        //  1638: ifnull          1656
        //  1641: goto            1654
        //  1644: aload           this$irfMinAmount
        //  1646: aload           other$irfMinAmount
        //  1648: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1651: ifne            1656
        //  1654: iconst_0       
        //  1655: ireturn        
        //  1656: aload_0         /* this */
        //  1657: invokevirtual   com/empay/entities/PosTransactionEntity.getAuthAmount:()Ljava/lang/Double;
        //  1660: astore          this$authAmount
        //  1662: aload_2         /* other */
        //  1663: invokevirtual   com/empay/entities/PosTransactionEntity.getAuthAmount:()Ljava/lang/Double;
        //  1666: astore          other$authAmount
        //  1668: aload           this$authAmount
        //  1670: ifnonnull       1681
        //  1673: aload           other$authAmount
        //  1675: ifnull          1693
        //  1678: goto            1691
        //  1681: aload           this$authAmount
        //  1683: aload           other$authAmount
        //  1685: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1688: ifne            1693
        //  1691: iconst_0       
        //  1692: ireturn        
        //  1693: aload_0         /* this */
        //  1694: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfAmountUSD:()Ljava/lang/Double;
        //  1697: astore          this$irfAmountUSD
        //  1699: aload_2         /* other */
        //  1700: invokevirtual   com/empay/entities/PosTransactionEntity.getIrfAmountUSD:()Ljava/lang/Double;
        //  1703: astore          other$irfAmountUSD
        //  1705: aload           this$irfAmountUSD
        //  1707: ifnonnull       1718
        //  1710: aload           other$irfAmountUSD
        //  1712: ifnull          1730
        //  1715: goto            1728
        //  1718: aload           this$irfAmountUSD
        //  1720: aload           other$irfAmountUSD
        //  1722: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1725: ifne            1730
        //  1728: iconst_0       
        //  1729: ireturn        
        //  1730: aload_0         /* this */
        //  1731: invokevirtual   com/empay/entities/PosTransactionEntity.getRefundIndicator:()Ljava/lang/Character;
        //  1734: astore          this$refundIndicator
        //  1736: aload_2         /* other */
        //  1737: invokevirtual   com/empay/entities/PosTransactionEntity.getRefundIndicator:()Ljava/lang/Character;
        //  1740: astore          other$refundIndicator
        //  1742: aload           this$refundIndicator
        //  1744: ifnonnull       1755
        //  1747: aload           other$refundIndicator
        //  1749: ifnull          1767
        //  1752: goto            1765
        //  1755: aload           this$refundIndicator
        //  1757: aload           other$refundIndicator
        //  1759: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1762: ifne            1767
        //  1765: iconst_0       
        //  1766: ireturn        
        //  1767: aload_0         /* this */
        //  1768: invokevirtual   com/empay/entities/PosTransactionEntity.getTipAmount:()Ljava/lang/Double;
        //  1771: astore          this$tipAmount
        //  1773: aload_2         /* other */
        //  1774: invokevirtual   com/empay/entities/PosTransactionEntity.getTipAmount:()Ljava/lang/Double;
        //  1777: astore          other$tipAmount
        //  1779: aload           this$tipAmount
        //  1781: ifnonnull       1792
        //  1784: aload           other$tipAmount
        //  1786: ifnull          1804
        //  1789: goto            1802
        //  1792: aload           this$tipAmount
        //  1794: aload           other$tipAmount
        //  1796: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1799: ifne            1804
        //  1802: iconst_0       
        //  1803: ireturn        
        //  1804: aload_0         /* this */
        //  1805: invokevirtual   com/empay/entities/PosTransactionEntity.getDccAmount:()Ljava/lang/Double;
        //  1808: astore          this$dccAmount
        //  1810: aload_2         /* other */
        //  1811: invokevirtual   com/empay/entities/PosTransactionEntity.getDccAmount:()Ljava/lang/Double;
        //  1814: astore          other$dccAmount
        //  1816: aload           this$dccAmount
        //  1818: ifnonnull       1829
        //  1821: aload           other$dccAmount
        //  1823: ifnull          1841
        //  1826: goto            1839
        //  1829: aload           this$dccAmount
        //  1831: aload           other$dccAmount
        //  1833: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1836: ifne            1841
        //  1839: iconst_0       
        //  1840: ireturn        
        //  1841: aload_0         /* this */
        //  1842: invokevirtual   com/empay/entities/PosTransactionEntity.getDccIndicator:()Ljava/lang/Character;
        //  1845: astore          this$dccIndicator
        //  1847: aload_2         /* other */
        //  1848: invokevirtual   com/empay/entities/PosTransactionEntity.getDccIndicator:()Ljava/lang/Character;
        //  1851: astore          other$dccIndicator
        //  1853: aload           this$dccIndicator
        //  1855: ifnonnull       1866
        //  1858: aload           other$dccIndicator
        //  1860: ifnull          1878
        //  1863: goto            1876
        //  1866: aload           this$dccIndicator
        //  1868: aload           other$dccIndicator
        //  1870: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1873: ifne            1878
        //  1876: iconst_0       
        //  1877: ireturn        
        //  1878: aload_0         /* this */
        //  1879: invokevirtual   com/empay/entities/PosTransactionEntity.getDccExchangeRate:()Ljava/lang/Double;
        //  1882: astore          this$dccExchangeRate
        //  1884: aload_2         /* other */
        //  1885: invokevirtual   com/empay/entities/PosTransactionEntity.getDccExchangeRate:()Ljava/lang/Double;
        //  1888: astore          other$dccExchangeRate
        //  1890: aload           this$dccExchangeRate
        //  1892: ifnonnull       1903
        //  1895: aload           other$dccExchangeRate
        //  1897: ifnull          1915
        //  1900: goto            1913
        //  1903: aload           this$dccExchangeRate
        //  1905: aload           other$dccExchangeRate
        //  1907: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1910: ifne            1915
        //  1913: iconst_0       
        //  1914: ireturn        
        //  1915: aload_0         /* this */
        //  1916: invokevirtual   com/empay/entities/PosTransactionEntity.getMposAccDevType:()Ljava/lang/Character;
        //  1919: astore          this$mposAccDevType
        //  1921: aload_2         /* other */
        //  1922: invokevirtual   com/empay/entities/PosTransactionEntity.getMposAccDevType:()Ljava/lang/Character;
        //  1925: astore          other$mposAccDevType
        //  1927: aload           this$mposAccDevType
        //  1929: ifnonnull       1940
        //  1932: aload           other$mposAccDevType
        //  1934: ifnull          1952
        //  1937: goto            1950
        //  1940: aload           this$mposAccDevType
        //  1942: aload           other$mposAccDevType
        //  1944: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1947: ifne            1952
        //  1950: iconst_0       
        //  1951: ireturn        
        //  1952: aload_0         /* this */
        //  1953: invokevirtual   com/empay/entities/PosTransactionEntity.getAcceptanceTrlIndicator:()Ljava/lang/Character;
        //  1956: astore          this$acceptanceTrlIndicator
        //  1958: aload_2         /* other */
        //  1959: invokevirtual   com/empay/entities/PosTransactionEntity.getAcceptanceTrlIndicator:()Ljava/lang/Character;
        //  1962: astore          other$acceptanceTrlIndicator
        //  1964: aload           this$acceptanceTrlIndicator
        //  1966: ifnonnull       1977
        //  1969: aload           other$acceptanceTrlIndicator
        //  1971: ifnull          1989
        //  1974: goto            1987
        //  1977: aload           this$acceptanceTrlIndicator
        //  1979: aload           other$acceptanceTrlIndicator
        //  1981: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1984: ifne            1989
        //  1987: iconst_0       
        //  1988: ireturn        
        //  1989: aload_0         /* this */
        //  1990: invokevirtual   com/empay/entities/PosTransactionEntity.getLastUpdated:()Ljava/time/LocalDateTime;
        //  1993: astore          this$lastUpdated
        //  1995: aload_2         /* other */
        //  1996: invokevirtual   com/empay/entities/PosTransactionEntity.getLastUpdated:()Ljava/time/LocalDateTime;
        //  1999: astore          other$lastUpdated
        //  2001: aload           this$lastUpdated
        //  2003: ifnonnull       2014
        //  2006: aload           other$lastUpdated
        //  2008: ifnull          2026
        //  2011: goto            2024
        //  2014: aload           this$lastUpdated
        //  2016: aload           other$lastUpdated
        //  2018: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2021: ifne            2026
        //  2024: iconst_0       
        //  2025: ireturn        
        //  2026: aload_0         /* this */
        //  2027: invokevirtual   com/empay/entities/PosTransactionEntity.getRrn:()Ljava/lang/String;
        //  2030: astore          this$rrn
        //  2032: aload_2         /* other */
        //  2033: invokevirtual   com/empay/entities/PosTransactionEntity.getRrn:()Ljava/lang/String;
        //  2036: astore          other$rrn
        //  2038: aload           this$rrn
        //  2040: ifnonnull       2051
        //  2043: aload           other$rrn
        //  2045: ifnull          2063
        //  2048: goto            2061
        //  2051: aload           this$rrn
        //  2053: aload           other$rrn
        //  2055: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2058: ifne            2063
        //  2061: iconst_0       
        //  2062: ireturn        
        //  2063: aload_0         /* this */
        //  2064: invokevirtual   com/empay/entities/PosTransactionEntity.getCardNumber:()Ljava/lang/String;
        //  2067: astore          this$cardNumber
        //  2069: aload_2         /* other */
        //  2070: invokevirtual   com/empay/entities/PosTransactionEntity.getCardNumber:()Ljava/lang/String;
        //  2073: astore          other$cardNumber
        //  2075: aload           this$cardNumber
        //  2077: ifnonnull       2088
        //  2080: aload           other$cardNumber
        //  2082: ifnull          2100
        //  2085: goto            2098
        //  2088: aload           this$cardNumber
        //  2090: aload           other$cardNumber
        //  2092: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2095: ifne            2100
        //  2098: iconst_0       
        //  2099: ireturn        
        //  2100: aload_0         /* this */
        //  2101: invokevirtual   com/empay/entities/PosTransactionEntity.getLocalDateTime:()Ljava/time/LocalDateTime;
        //  2104: astore          this$localDateTime
        //  2106: aload_2         /* other */
        //  2107: invokevirtual   com/empay/entities/PosTransactionEntity.getLocalDateTime:()Ljava/time/LocalDateTime;
        //  2110: astore          other$localDateTime
        //  2112: aload           this$localDateTime
        //  2114: ifnonnull       2125
        //  2117: aload           other$localDateTime
        //  2119: ifnull          2137
        //  2122: goto            2135
        //  2125: aload           this$localDateTime
        //  2127: aload           other$localDateTime
        //  2129: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2132: ifne            2137
        //  2135: iconst_0       
        //  2136: ireturn        
        //  2137: aload_0         /* this */
        //  2138: invokevirtual   com/empay/entities/PosTransactionEntity.getResponseCode:()Ljava/lang/String;
        //  2141: astore          this$responseCode
        //  2143: aload_2         /* other */
        //  2144: invokevirtual   com/empay/entities/PosTransactionEntity.getResponseCode:()Ljava/lang/String;
        //  2147: astore          other$responseCode
        //  2149: aload           this$responseCode
        //  2151: ifnonnull       2162
        //  2154: aload           other$responseCode
        //  2156: ifnull          2174
        //  2159: goto            2172
        //  2162: aload           this$responseCode
        //  2164: aload           other$responseCode
        //  2166: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2169: ifne            2174
        //  2172: iconst_0       
        //  2173: ireturn        
        //  2174: aload_0         /* this */
        //  2175: invokevirtual   com/empay/entities/PosTransactionEntity.getApprovalCode:()Ljava/lang/String;
        //  2178: astore          this$approvalCode
        //  2180: aload_2         /* other */
        //  2181: invokevirtual   com/empay/entities/PosTransactionEntity.getApprovalCode:()Ljava/lang/String;
        //  2184: astore          other$approvalCode
        //  2186: aload           this$approvalCode
        //  2188: ifnonnull       2199
        //  2191: aload           other$approvalCode
        //  2193: ifnull          2211
        //  2196: goto            2209
        //  2199: aload           this$approvalCode
        //  2201: aload           other$approvalCode
        //  2203: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2206: ifne            2211
        //  2209: iconst_0       
        //  2210: ireturn        
        //  2211: aload_0         /* this */
        //  2212: invokevirtual   com/empay/entities/PosTransactionEntity.getTerminalId:()Ljava/lang/String;
        //  2215: astore          this$terminalId
        //  2217: aload_2         /* other */
        //  2218: invokevirtual   com/empay/entities/PosTransactionEntity.getTerminalId:()Ljava/lang/String;
        //  2221: astore          other$terminalId
        //  2223: aload           this$terminalId
        //  2225: ifnonnull       2236
        //  2228: aload           other$terminalId
        //  2230: ifnull          2248
        //  2233: goto            2246
        //  2236: aload           this$terminalId
        //  2238: aload           other$terminalId
        //  2240: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2243: ifne            2248
        //  2246: iconst_0       
        //  2247: ireturn        
        //  2248: aload_0         /* this */
        //  2249: invokevirtual   com/empay/entities/PosTransactionEntity.getMerchantId:()Ljava/lang/String;
        //  2252: astore          this$merchantId
        //  2254: aload_2         /* other */
        //  2255: invokevirtual   com/empay/entities/PosTransactionEntity.getMerchantId:()Ljava/lang/String;
        //  2258: astore          other$merchantId
        //  2260: aload           this$merchantId
        //  2262: ifnonnull       2273
        //  2265: aload           other$merchantId
        //  2267: ifnull          2285
        //  2270: goto            2283
        //  2273: aload           this$merchantId
        //  2275: aload           other$merchantId
        //  2277: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2280: ifne            2285
        //  2283: iconst_0       
        //  2284: ireturn        
        //  2285: aload_0         /* this */
        //  2286: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnType:()Ljava/lang/String;
        //  2289: astore          this$txnType
        //  2291: aload_2         /* other */
        //  2292: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnType:()Ljava/lang/String;
        //  2295: astore          other$txnType
        //  2297: aload           this$txnType
        //  2299: ifnonnull       2310
        //  2302: aload           other$txnType
        //  2304: ifnull          2322
        //  2307: goto            2320
        //  2310: aload           this$txnType
        //  2312: aload           other$txnType
        //  2314: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2317: ifne            2322
        //  2320: iconst_0       
        //  2321: ireturn        
        //  2322: aload_0         /* this */
        //  2323: invokevirtual   com/empay/entities/PosTransactionEntity.getProcCode:()Ljava/lang/String;
        //  2326: astore          this$procCode
        //  2328: aload_2         /* other */
        //  2329: invokevirtual   com/empay/entities/PosTransactionEntity.getProcCode:()Ljava/lang/String;
        //  2332: astore          other$procCode
        //  2334: aload           this$procCode
        //  2336: ifnonnull       2347
        //  2339: aload           other$procCode
        //  2341: ifnull          2359
        //  2344: goto            2357
        //  2347: aload           this$procCode
        //  2349: aload           other$procCode
        //  2351: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2354: ifne            2359
        //  2357: iconst_0       
        //  2358: ireturn        
        //  2359: aload_0         /* this */
        //  2360: invokevirtual   com/empay/entities/PosTransactionEntity.getStan:()Ljava/lang/String;
        //  2363: astore          this$stan
        //  2365: aload_2         /* other */
        //  2366: invokevirtual   com/empay/entities/PosTransactionEntity.getStan:()Ljava/lang/String;
        //  2369: astore          other$stan
        //  2371: aload           this$stan
        //  2373: ifnonnull       2384
        //  2376: aload           other$stan
        //  2378: ifnull          2396
        //  2381: goto            2394
        //  2384: aload           this$stan
        //  2386: aload           other$stan
        //  2388: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2391: ifne            2396
        //  2394: iconst_0       
        //  2395: ireturn        
        //  2396: aload_0         /* this */
        //  2397: invokevirtual   com/empay/entities/PosTransactionEntity.getAuthReason:()Ljava/lang/String;
        //  2400: astore          this$authReason
        //  2402: aload_2         /* other */
        //  2403: invokevirtual   com/empay/entities/PosTransactionEntity.getAuthReason:()Ljava/lang/String;
        //  2406: astore          other$authReason
        //  2408: aload           this$authReason
        //  2410: ifnonnull       2421
        //  2413: aload           other$authReason
        //  2415: ifnull          2433
        //  2418: goto            2431
        //  2421: aload           this$authReason
        //  2423: aload           other$authReason
        //  2425: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2428: ifne            2433
        //  2431: iconst_0       
        //  2432: ireturn        
        //  2433: aload_0         /* this */
        //  2434: invokevirtual   com/empay/entities/PosTransactionEntity.getMeName:()Ljava/lang/String;
        //  2437: astore          this$meName
        //  2439: aload_2         /* other */
        //  2440: invokevirtual   com/empay/entities/PosTransactionEntity.getMeName:()Ljava/lang/String;
        //  2443: astore          other$meName
        //  2445: aload           this$meName
        //  2447: ifnonnull       2458
        //  2450: aload           other$meName
        //  2452: ifnull          2470
        //  2455: goto            2468
        //  2458: aload           this$meName
        //  2460: aload           other$meName
        //  2462: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2465: ifne            2470
        //  2468: iconst_0       
        //  2469: ireturn        
        //  2470: aload_0         /* this */
        //  2471: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCity:()Ljava/lang/String;
        //  2474: astore          this$meCity
        //  2476: aload_2         /* other */
        //  2477: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCity:()Ljava/lang/String;
        //  2480: astore          other$meCity
        //  2482: aload           this$meCity
        //  2484: ifnonnull       2495
        //  2487: aload           other$meCity
        //  2489: ifnull          2507
        //  2492: goto            2505
        //  2495: aload           this$meCity
        //  2497: aload           other$meCity
        //  2499: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2502: ifne            2507
        //  2505: iconst_0       
        //  2506: ireturn        
        //  2507: aload_0         /* this */
        //  2508: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCountry:()Ljava/lang/String;
        //  2511: astore          this$meCountry
        //  2513: aload_2         /* other */
        //  2514: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCountry:()Ljava/lang/String;
        //  2517: astore          other$meCountry
        //  2519: aload           this$meCountry
        //  2521: ifnonnull       2532
        //  2524: aload           other$meCountry
        //  2526: ifnull          2544
        //  2529: goto            2542
        //  2532: aload           this$meCountry
        //  2534: aload           other$meCountry
        //  2536: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2539: ifne            2544
        //  2542: iconst_0       
        //  2543: ireturn        
        //  2544: aload_0         /* this */
        //  2545: invokevirtual   com/empay/entities/PosTransactionEntity.getMePinCode:()Ljava/lang/String;
        //  2548: astore          this$mePinCode
        //  2550: aload_2         /* other */
        //  2551: invokevirtual   com/empay/entities/PosTransactionEntity.getMePinCode:()Ljava/lang/String;
        //  2554: astore          other$mePinCode
        //  2556: aload           this$mePinCode
        //  2558: ifnonnull       2569
        //  2561: aload           other$mePinCode
        //  2563: ifnull          2581
        //  2566: goto            2579
        //  2569: aload           this$mePinCode
        //  2571: aload           other$mePinCode
        //  2573: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2576: ifne            2581
        //  2579: iconst_0       
        //  2580: ireturn        
        //  2581: aload_0         /* this */
        //  2582: invokevirtual   com/empay/entities/PosTransactionEntity.getBussDate:()Ljava/time/LocalDate;
        //  2585: astore          this$bussDate
        //  2587: aload_2         /* other */
        //  2588: invokevirtual   com/empay/entities/PosTransactionEntity.getBussDate:()Ljava/time/LocalDate;
        //  2591: astore          other$bussDate
        //  2593: aload           this$bussDate
        //  2595: ifnonnull       2606
        //  2598: aload           other$bussDate
        //  2600: ifnull          2618
        //  2603: goto            2616
        //  2606: aload           this$bussDate
        //  2608: aload           other$bussDate
        //  2610: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2613: ifne            2618
        //  2616: iconst_0       
        //  2617: ireturn        
        //  2618: aload_0         /* this */
        //  2619: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnUniqueId:()Ljava/lang/String;
        //  2622: astore          this$txnUniqueId
        //  2624: aload_2         /* other */
        //  2625: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnUniqueId:()Ljava/lang/String;
        //  2628: astore          other$txnUniqueId
        //  2630: aload           this$txnUniqueId
        //  2632: ifnonnull       2643
        //  2635: aload           other$txnUniqueId
        //  2637: ifnull          2655
        //  2640: goto            2653
        //  2643: aload           this$txnUniqueId
        //  2645: aload           other$txnUniqueId
        //  2647: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2650: ifne            2655
        //  2653: iconst_0       
        //  2654: ireturn        
        //  2655: aload_0         /* this */
        //  2656: invokevirtual   com/empay/entities/PosTransactionEntity.getMsgTypeId:()Ljava/lang/String;
        //  2659: astore          this$msgTypeId
        //  2661: aload_2         /* other */
        //  2662: invokevirtual   com/empay/entities/PosTransactionEntity.getMsgTypeId:()Ljava/lang/String;
        //  2665: astore          other$msgTypeId
        //  2667: aload           this$msgTypeId
        //  2669: ifnonnull       2680
        //  2672: aload           other$msgTypeId
        //  2674: ifnull          2692
        //  2677: goto            2690
        //  2680: aload           this$msgTypeId
        //  2682: aload           other$msgTypeId
        //  2684: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2687: ifne            2692
        //  2690: iconst_0       
        //  2691: ireturn        
        //  2692: aload_0         /* this */
        //  2693: invokevirtual   com/empay/entities/PosTransactionEntity.getOprtEnvironment:()Ljava/lang/String;
        //  2696: astore          this$oprtEnvironment
        //  2698: aload_2         /* other */
        //  2699: invokevirtual   com/empay/entities/PosTransactionEntity.getOprtEnvironment:()Ljava/lang/String;
        //  2702: astore          other$oprtEnvironment
        //  2704: aload           this$oprtEnvironment
        //  2706: ifnonnull       2717
        //  2709: aload           other$oprtEnvironment
        //  2711: ifnull          2729
        //  2714: goto            2727
        //  2717: aload           this$oprtEnvironment
        //  2719: aload           other$oprtEnvironment
        //  2721: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2724: ifne            2729
        //  2727: iconst_0       
        //  2728: ireturn        
        //  2729: aload_0         /* this */
        //  2730: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnCurCode:()Ljava/lang/String;
        //  2733: astore          this$txnCurCode
        //  2735: aload_2         /* other */
        //  2736: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnCurCode:()Ljava/lang/String;
        //  2739: astore          other$txnCurCode
        //  2741: aload           this$txnCurCode
        //  2743: ifnonnull       2754
        //  2746: aload           other$txnCurCode
        //  2748: ifnull          2766
        //  2751: goto            2764
        //  2754: aload           this$txnCurCode
        //  2756: aload           other$txnCurCode
        //  2758: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2761: ifne            2766
        //  2764: iconst_0       
        //  2765: ireturn        
        //  2766: aload_0         /* this */
        //  2767: invokevirtual   com/empay/entities/PosTransactionEntity.getSetlDate:()Ljava/time/LocalDate;
        //  2770: astore          this$setlDate
        //  2772: aload_2         /* other */
        //  2773: invokevirtual   com/empay/entities/PosTransactionEntity.getSetlDate:()Ljava/time/LocalDate;
        //  2776: astore          other$setlDate
        //  2778: aload           this$setlDate
        //  2780: ifnonnull       2791
        //  2783: aload           other$setlDate
        //  2785: ifnull          2803
        //  2788: goto            2801
        //  2791: aload           this$setlDate
        //  2793: aload           other$setlDate
        //  2795: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2798: ifne            2803
        //  2801: iconst_0       
        //  2802: ireturn        
        //  2803: aload_0         /* this */
        //  2804: invokevirtual   com/empay/entities/PosTransactionEntity.getSetlCurCode:()Ljava/lang/String;
        //  2807: astore          this$setlCurCode
        //  2809: aload_2         /* other */
        //  2810: invokevirtual   com/empay/entities/PosTransactionEntity.getSetlCurCode:()Ljava/lang/String;
        //  2813: astore          other$setlCurCode
        //  2815: aload           this$setlCurCode
        //  2817: ifnonnull       2828
        //  2820: aload           other$setlCurCode
        //  2822: ifnull          2840
        //  2825: goto            2838
        //  2828: aload           this$setlCurCode
        //  2830: aload           other$setlCurCode
        //  2832: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2835: ifne            2840
        //  2838: iconst_0       
        //  2839: ireturn        
        //  2840: aload_0         /* this */
        //  2841: invokevirtual   com/empay/entities/PosTransactionEntity.getPosEntryMode:()Ljava/lang/String;
        //  2844: astore          this$posEntryMode
        //  2846: aload_2         /* other */
        //  2847: invokevirtual   com/empay/entities/PosTransactionEntity.getPosEntryMode:()Ljava/lang/String;
        //  2850: astore          other$posEntryMode
        //  2852: aload           this$posEntryMode
        //  2854: ifnonnull       2865
        //  2857: aload           other$posEntryMode
        //  2859: ifnull          2877
        //  2862: goto            2875
        //  2865: aload           this$posEntryMode
        //  2867: aload           other$posEntryMode
        //  2869: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2872: ifne            2877
        //  2875: iconst_0       
        //  2876: ireturn        
        //  2877: aload_0         /* this */
        //  2878: invokevirtual   com/empay/entities/PosTransactionEntity.getPosConditionCode:()Ljava/lang/String;
        //  2881: astore          this$posConditionCode
        //  2883: aload_2         /* other */
        //  2884: invokevirtual   com/empay/entities/PosTransactionEntity.getPosConditionCode:()Ljava/lang/String;
        //  2887: astore          other$posConditionCode
        //  2889: aload           this$posConditionCode
        //  2891: ifnonnull       2902
        //  2894: aload           other$posConditionCode
        //  2896: ifnull          2914
        //  2899: goto            2912
        //  2902: aload           this$posConditionCode
        //  2904: aload           other$posConditionCode
        //  2906: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2909: ifne            2914
        //  2912: iconst_0       
        //  2913: ireturn        
        //  2914: aload_0         /* this */
        //  2915: invokevirtual   com/empay/entities/PosTransactionEntity.getMotoEcomIndicator:()Ljava/lang/String;
        //  2918: astore          this$motoEcomIndicator
        //  2920: aload_2         /* other */
        //  2921: invokevirtual   com/empay/entities/PosTransactionEntity.getMotoEcomIndicator:()Ljava/lang/String;
        //  2924: astore          other$motoEcomIndicator
        //  2926: aload           this$motoEcomIndicator
        //  2928: ifnonnull       2939
        //  2931: aload           other$motoEcomIndicator
        //  2933: ifnull          2951
        //  2936: goto            2949
        //  2939: aload           this$motoEcomIndicator
        //  2941: aload           other$motoEcomIndicator
        //  2943: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2946: ifne            2951
        //  2949: iconst_0       
        //  2950: ireturn        
        //  2951: aload_0         /* this */
        //  2952: invokevirtual   com/empay/entities/PosTransactionEntity.getCardSeqNumber:()Ljava/lang/String;
        //  2955: astore          this$cardSeqNumber
        //  2957: aload_2         /* other */
        //  2958: invokevirtual   com/empay/entities/PosTransactionEntity.getCardSeqNumber:()Ljava/lang/String;
        //  2961: astore          other$cardSeqNumber
        //  2963: aload           this$cardSeqNumber
        //  2965: ifnonnull       2976
        //  2968: aload           other$cardSeqNumber
        //  2970: ifnull          2988
        //  2973: goto            2986
        //  2976: aload           this$cardSeqNumber
        //  2978: aload           other$cardSeqNumber
        //  2980: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2983: ifne            2988
        //  2986: iconst_0       
        //  2987: ireturn        
        //  2988: aload_0         /* this */
        //  2989: invokevirtual   com/empay/entities/PosTransactionEntity.getServiceCode:()Ljava/lang/String;
        //  2992: astore          this$serviceCode
        //  2994: aload_2         /* other */
        //  2995: invokevirtual   com/empay/entities/PosTransactionEntity.getServiceCode:()Ljava/lang/String;
        //  2998: astore          other$serviceCode
        //  3000: aload           this$serviceCode
        //  3002: ifnonnull       3013
        //  3005: aload           other$serviceCode
        //  3007: ifnull          3025
        //  3010: goto            3023
        //  3013: aload           this$serviceCode
        //  3015: aload           other$serviceCode
        //  3017: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3020: ifne            3025
        //  3023: iconst_0       
        //  3024: ireturn        
        //  3025: aload_0         /* this */
        //  3026: invokevirtual   com/empay/entities/PosTransactionEntity.getMcc:()Ljava/lang/String;
        //  3029: astore          this$mcc
        //  3031: aload_2         /* other */
        //  3032: invokevirtual   com/empay/entities/PosTransactionEntity.getMcc:()Ljava/lang/String;
        //  3035: astore          other$mcc
        //  3037: aload           this$mcc
        //  3039: ifnonnull       3050
        //  3042: aload           other$mcc
        //  3044: ifnull          3062
        //  3047: goto            3060
        //  3050: aload           this$mcc
        //  3052: aload           other$mcc
        //  3054: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3057: ifne            3062
        //  3060: iconst_0       
        //  3061: ireturn        
        //  3062: aload_0         /* this */
        //  3063: invokevirtual   com/empay/entities/PosTransactionEntity.getTerminalType:()Ljava/lang/String;
        //  3066: astore          this$terminalType
        //  3068: aload_2         /* other */
        //  3069: invokevirtual   com/empay/entities/PosTransactionEntity.getTerminalType:()Ljava/lang/String;
        //  3072: astore          other$terminalType
        //  3074: aload           this$terminalType
        //  3076: ifnonnull       3087
        //  3079: aload           other$terminalType
        //  3081: ifnull          3099
        //  3084: goto            3097
        //  3087: aload           this$terminalType
        //  3089: aload           other$terminalType
        //  3091: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3094: ifne            3099
        //  3097: iconst_0       
        //  3098: ireturn        
        //  3099: aload_0         /* this */
        //  3100: invokevirtual   com/empay/entities/PosTransactionEntity.getNetwork:()Ljava/lang/String;
        //  3103: astore          this$network
        //  3105: aload_2         /* other */
        //  3106: invokevirtual   com/empay/entities/PosTransactionEntity.getNetwork:()Ljava/lang/String;
        //  3109: astore          other$network
        //  3111: aload           this$network
        //  3113: ifnonnull       3124
        //  3116: aload           other$network
        //  3118: ifnull          3136
        //  3121: goto            3134
        //  3124: aload           this$network
        //  3126: aload           other$network
        //  3128: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3131: ifne            3136
        //  3134: iconst_0       
        //  3135: ireturn        
        //  3136: aload_0         /* this */
        //  3137: invokevirtual   com/empay/entities/PosTransactionEntity.getCentreProcDate:()Ljava/time/LocalDate;
        //  3140: astore          this$centreProcDate
        //  3142: aload_2         /* other */
        //  3143: invokevirtual   com/empay/entities/PosTransactionEntity.getCentreProcDate:()Ljava/time/LocalDate;
        //  3146: astore          other$centreProcDate
        //  3148: aload           this$centreProcDate
        //  3150: ifnonnull       3161
        //  3153: aload           other$centreProcDate
        //  3155: ifnull          3173
        //  3158: goto            3171
        //  3161: aload           this$centreProcDate
        //  3163: aload           other$centreProcDate
        //  3165: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3168: ifne            3173
        //  3171: iconst_0       
        //  3172: ireturn        
        //  3173: aload_0         /* this */
        //  3174: invokevirtual   com/empay/entities/PosTransactionEntity.getCardCategory:()Ljava/lang/String;
        //  3177: astore          this$cardCategory
        //  3179: aload_2         /* other */
        //  3180: invokevirtual   com/empay/entities/PosTransactionEntity.getCardCategory:()Ljava/lang/String;
        //  3183: astore          other$cardCategory
        //  3185: aload           this$cardCategory
        //  3187: ifnonnull       3198
        //  3190: aload           other$cardCategory
        //  3192: ifnull          3210
        //  3195: goto            3208
        //  3198: aload           this$cardCategory
        //  3200: aload           other$cardCategory
        //  3202: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3205: ifne            3210
        //  3208: iconst_0       
        //  3209: ireturn        
        //  3210: aload_0         /* this */
        //  3211: invokevirtual   com/empay/entities/PosTransactionEntity.getCardSubCategory:()Ljava/lang/String;
        //  3214: astore          this$cardSubCategory
        //  3216: aload_2         /* other */
        //  3217: invokevirtual   com/empay/entities/PosTransactionEntity.getCardSubCategory:()Ljava/lang/String;
        //  3220: astore          other$cardSubCategory
        //  3222: aload           this$cardSubCategory
        //  3224: ifnonnull       3235
        //  3227: aload           other$cardSubCategory
        //  3229: ifnull          3247
        //  3232: goto            3245
        //  3235: aload           this$cardSubCategory
        //  3237: aload           other$cardSubCategory
        //  3239: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3242: ifne            3247
        //  3245: iconst_0       
        //  3246: ireturn        
        //  3247: aload_0         /* this */
        //  3248: invokevirtual   com/empay/entities/PosTransactionEntity.getNetworkData:()Ljava/lang/String;
        //  3251: astore          this$networkData
        //  3253: aload_2         /* other */
        //  3254: invokevirtual   com/empay/entities/PosTransactionEntity.getNetworkData:()Ljava/lang/String;
        //  3257: astore          other$networkData
        //  3259: aload           this$networkData
        //  3261: ifnonnull       3272
        //  3264: aload           other$networkData
        //  3266: ifnull          3284
        //  3269: goto            3282
        //  3272: aload           this$networkData
        //  3274: aload           other$networkData
        //  3276: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3279: ifne            3284
        //  3282: iconst_0       
        //  3283: ireturn        
        //  3284: aload_0         /* this */
        //  3285: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnDateTime:()Ljava/time/LocalDateTime;
        //  3288: astore          this$txnDateTime
        //  3290: aload_2         /* other */
        //  3291: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnDateTime:()Ljava/time/LocalDateTime;
        //  3294: astore          other$txnDateTime
        //  3296: aload           this$txnDateTime
        //  3298: ifnonnull       3309
        //  3301: aload           other$txnDateTime
        //  3303: ifnull          3321
        //  3306: goto            3319
        //  3309: aload           this$txnDateTime
        //  3311: aload           other$txnDateTime
        //  3313: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3316: ifne            3321
        //  3319: iconst_0       
        //  3320: ireturn        
        //  3321: aload_0         /* this */
        //  3322: invokevirtual   com/empay/entities/PosTransactionEntity.getAcqinstIdCode:()Ljava/lang/String;
        //  3325: astore          this$acqinstIdCode
        //  3327: aload_2         /* other */
        //  3328: invokevirtual   com/empay/entities/PosTransactionEntity.getAcqinstIdCode:()Ljava/lang/String;
        //  3331: astore          other$acqinstIdCode
        //  3333: aload           this$acqinstIdCode
        //  3335: ifnonnull       3346
        //  3338: aload           other$acqinstIdCode
        //  3340: ifnull          3358
        //  3343: goto            3356
        //  3346: aload           this$acqinstIdCode
        //  3348: aload           other$acqinstIdCode
        //  3350: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3353: ifne            3358
        //  3356: iconst_0       
        //  3357: ireturn        
        //  3358: aload_0         /* this */
        //  3359: invokevirtual   com/empay/entities/PosTransactionEntity.getAcqInstConCode:()Ljava/lang/String;
        //  3362: astore          this$acqInstConCode
        //  3364: aload_2         /* other */
        //  3365: invokevirtual   com/empay/entities/PosTransactionEntity.getAcqInstConCode:()Ljava/lang/String;
        //  3368: astore          other$acqInstConCode
        //  3370: aload           this$acqInstConCode
        //  3372: ifnonnull       3383
        //  3375: aload           other$acqInstConCode
        //  3377: ifnull          3395
        //  3380: goto            3393
        //  3383: aload           this$acqInstConCode
        //  3385: aload           other$acqInstConCode
        //  3387: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3390: ifne            3395
        //  3393: iconst_0       
        //  3394: ireturn        
        //  3395: aload_0         /* this */
        //  3396: invokevirtual   com/empay/entities/PosTransactionEntity.getAppCryptogram:()Ljava/lang/String;
        //  3399: astore          this$appCryptogram
        //  3401: aload_2         /* other */
        //  3402: invokevirtual   com/empay/entities/PosTransactionEntity.getAppCryptogram:()Ljava/lang/String;
        //  3405: astore          other$appCryptogram
        //  3407: aload           this$appCryptogram
        //  3409: ifnonnull       3420
        //  3412: aload           other$appCryptogram
        //  3414: ifnull          3432
        //  3417: goto            3430
        //  3420: aload           this$appCryptogram
        //  3422: aload           other$appCryptogram
        //  3424: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3427: ifne            3432
        //  3430: iconst_0       
        //  3431: ireturn        
        //  3432: aload_0         /* this */
        //  3433: invokevirtual   com/empay/entities/PosTransactionEntity.getCryptInfoData:()Ljava/lang/String;
        //  3436: astore          this$cryptInfoData
        //  3438: aload_2         /* other */
        //  3439: invokevirtual   com/empay/entities/PosTransactionEntity.getCryptInfoData:()Ljava/lang/String;
        //  3442: astore          other$cryptInfoData
        //  3444: aload           this$cryptInfoData
        //  3446: ifnonnull       3457
        //  3449: aload           other$cryptInfoData
        //  3451: ifnull          3469
        //  3454: goto            3467
        //  3457: aload           this$cryptInfoData
        //  3459: aload           other$cryptInfoData
        //  3461: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3464: ifne            3469
        //  3467: iconst_0       
        //  3468: ireturn        
        //  3469: aload_0         /* this */
        //  3470: invokevirtual   com/empay/entities/PosTransactionEntity.getIssAppData:()Ljava/lang/String;
        //  3473: astore          this$issAppData
        //  3475: aload_2         /* other */
        //  3476: invokevirtual   com/empay/entities/PosTransactionEntity.getIssAppData:()Ljava/lang/String;
        //  3479: astore          other$issAppData
        //  3481: aload           this$issAppData
        //  3483: ifnonnull       3494
        //  3486: aload           other$issAppData
        //  3488: ifnull          3506
        //  3491: goto            3504
        //  3494: aload           this$issAppData
        //  3496: aload           other$issAppData
        //  3498: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3501: ifne            3506
        //  3504: iconst_0       
        //  3505: ireturn        
        //  3506: aload_0         /* this */
        //  3507: invokevirtual   com/empay/entities/PosTransactionEntity.getUpblNumber:()Ljava/lang/String;
        //  3510: astore          this$upblNumber
        //  3512: aload_2         /* other */
        //  3513: invokevirtual   com/empay/entities/PosTransactionEntity.getUpblNumber:()Ljava/lang/String;
        //  3516: astore          other$upblNumber
        //  3518: aload           this$upblNumber
        //  3520: ifnonnull       3531
        //  3523: aload           other$upblNumber
        //  3525: ifnull          3543
        //  3528: goto            3541
        //  3531: aload           this$upblNumber
        //  3533: aload           other$upblNumber
        //  3535: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3538: ifne            3543
        //  3541: iconst_0       
        //  3542: ireturn        
        //  3543: aload_0         /* this */
        //  3544: invokevirtual   com/empay/entities/PosTransactionEntity.getAppTxnCounter:()Ljava/lang/String;
        //  3547: astore          this$appTxnCounter
        //  3549: aload_2         /* other */
        //  3550: invokevirtual   com/empay/entities/PosTransactionEntity.getAppTxnCounter:()Ljava/lang/String;
        //  3553: astore          other$appTxnCounter
        //  3555: aload           this$appTxnCounter
        //  3557: ifnonnull       3568
        //  3560: aload           other$appTxnCounter
        //  3562: ifnull          3580
        //  3565: goto            3578
        //  3568: aload           this$appTxnCounter
        //  3570: aload           other$appTxnCounter
        //  3572: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3575: ifne            3580
        //  3578: iconst_0       
        //  3579: ireturn        
        //  3580: aload_0         /* this */
        //  3581: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlVerResult:()Ljava/lang/String;
        //  3584: astore          this$trlVerResult
        //  3586: aload_2         /* other */
        //  3587: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlVerResult:()Ljava/lang/String;
        //  3590: astore          other$trlVerResult
        //  3592: aload           this$trlVerResult
        //  3594: ifnonnull       3605
        //  3597: aload           other$trlVerResult
        //  3599: ifnull          3617
        //  3602: goto            3615
        //  3605: aload           this$trlVerResult
        //  3607: aload           other$trlVerResult
        //  3609: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3612: ifne            3617
        //  3615: iconst_0       
        //  3616: ireturn        
        //  3617: aload_0         /* this */
        //  3618: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTxnDate:()Ljava/lang/String;
        //  3621: astore          this$chipTxnDate
        //  3623: aload_2         /* other */
        //  3624: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTxnDate:()Ljava/lang/String;
        //  3627: astore          other$chipTxnDate
        //  3629: aload           this$chipTxnDate
        //  3631: ifnonnull       3642
        //  3634: aload           other$chipTxnDate
        //  3636: ifnull          3654
        //  3639: goto            3652
        //  3642: aload           this$chipTxnDate
        //  3644: aload           other$chipTxnDate
        //  3646: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3649: ifne            3654
        //  3652: iconst_0       
        //  3653: ireturn        
        //  3654: aload_0         /* this */
        //  3655: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTxnType:()Ljava/lang/String;
        //  3658: astore          this$chipTxnType
        //  3660: aload_2         /* other */
        //  3661: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTxnType:()Ljava/lang/String;
        //  3664: astore          other$chipTxnType
        //  3666: aload           this$chipTxnType
        //  3668: ifnonnull       3679
        //  3671: aload           other$chipTxnType
        //  3673: ifnull          3691
        //  3676: goto            3689
        //  3679: aload           this$chipTxnType
        //  3681: aload           other$chipTxnType
        //  3683: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3686: ifne            3691
        //  3689: iconst_0       
        //  3690: ireturn        
        //  3691: aload_0         /* this */
        //  3692: invokevirtual   com/empay/entities/PosTransactionEntity.getChipCurCode:()Ljava/lang/String;
        //  3695: astore          this$chipCurCode
        //  3697: aload_2         /* other */
        //  3698: invokevirtual   com/empay/entities/PosTransactionEntity.getChipCurCode:()Ljava/lang/String;
        //  3701: astore          other$chipCurCode
        //  3703: aload           this$chipCurCode
        //  3705: ifnonnull       3716
        //  3708: aload           other$chipCurCode
        //  3710: ifnull          3728
        //  3713: goto            3726
        //  3716: aload           this$chipCurCode
        //  3718: aload           other$chipCurCode
        //  3720: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3723: ifne            3728
        //  3726: iconst_0       
        //  3727: ireturn        
        //  3728: aload_0         /* this */
        //  3729: invokevirtual   com/empay/entities/PosTransactionEntity.getAppICProfile:()Ljava/lang/String;
        //  3732: astore          this$appICProfile
        //  3734: aload_2         /* other */
        //  3735: invokevirtual   com/empay/entities/PosTransactionEntity.getAppICProfile:()Ljava/lang/String;
        //  3738: astore          other$appICProfile
        //  3740: aload           this$appICProfile
        //  3742: ifnonnull       3753
        //  3745: aload           other$appICProfile
        //  3747: ifnull          3765
        //  3750: goto            3763
        //  3753: aload           this$appICProfile
        //  3755: aload           other$appICProfile
        //  3757: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3760: ifne            3765
        //  3763: iconst_0       
        //  3764: ireturn        
        //  3765: aload_0         /* this */
        //  3766: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlConCode:()Ljava/lang/String;
        //  3769: astore          this$trlConCode
        //  3771: aload_2         /* other */
        //  3772: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlConCode:()Ljava/lang/String;
        //  3775: astore          other$trlConCode
        //  3777: aload           this$trlConCode
        //  3779: ifnonnull       3790
        //  3782: aload           other$trlConCode
        //  3784: ifnull          3802
        //  3787: goto            3800
        //  3790: aload           this$trlConCode
        //  3792: aload           other$trlConCode
        //  3794: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3797: ifne            3802
        //  3800: iconst_0       
        //  3801: ireturn        
        //  3802: aload_0         /* this */
        //  3803: invokevirtual   com/empay/entities/PosTransactionEntity.getCvmResult:()Ljava/lang/String;
        //  3806: astore          this$cvmResult
        //  3808: aload_2         /* other */
        //  3809: invokevirtual   com/empay/entities/PosTransactionEntity.getCvmResult:()Ljava/lang/String;
        //  3812: astore          other$cvmResult
        //  3814: aload           this$cvmResult
        //  3816: ifnonnull       3827
        //  3819: aload           other$cvmResult
        //  3821: ifnull          3839
        //  3824: goto            3837
        //  3827: aload           this$cvmResult
        //  3829: aload           other$cvmResult
        //  3831: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3834: ifne            3839
        //  3837: iconst_0       
        //  3838: ireturn        
        //  3839: aload_0         /* this */
        //  3840: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlCapabilities:()Ljava/lang/String;
        //  3843: astore          this$trlCapabilities
        //  3845: aload_2         /* other */
        //  3846: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlCapabilities:()Ljava/lang/String;
        //  3849: astore          other$trlCapabilities
        //  3851: aload           this$trlCapabilities
        //  3853: ifnonnull       3864
        //  3856: aload           other$trlCapabilities
        //  3858: ifnull          3876
        //  3861: goto            3874
        //  3864: aload           this$trlCapabilities
        //  3866: aload           other$trlCapabilities
        //  3868: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3871: ifne            3876
        //  3874: iconst_0       
        //  3875: ireturn        
        //  3876: aload_0         /* this */
        //  3877: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTrlType:()Ljava/lang/String;
        //  3880: astore          this$chipTrlType
        //  3882: aload_2         /* other */
        //  3883: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTrlType:()Ljava/lang/String;
        //  3886: astore          other$chipTrlType
        //  3888: aload           this$chipTrlType
        //  3890: ifnonnull       3901
        //  3893: aload           other$chipTrlType
        //  3895: ifnull          3913
        //  3898: goto            3911
        //  3901: aload           this$chipTrlType
        //  3903: aload           other$chipTrlType
        //  3905: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3908: ifne            3913
        //  3911: iconst_0       
        //  3912: ireturn        
        //  3913: aload_0         /* this */
        //  3914: invokevirtual   com/empay/entities/PosTransactionEntity.getIfdSerNumber:()Ljava/lang/String;
        //  3917: astore          this$ifdSerNumber
        //  3919: aload_2         /* other */
        //  3920: invokevirtual   com/empay/entities/PosTransactionEntity.getIfdSerNumber:()Ljava/lang/String;
        //  3923: astore          other$ifdSerNumber
        //  3925: aload           this$ifdSerNumber
        //  3927: ifnonnull       3938
        //  3930: aload           other$ifdSerNumber
        //  3932: ifnull          3950
        //  3935: goto            3948
        //  3938: aload           this$ifdSerNumber
        //  3940: aload           other$ifdSerNumber
        //  3942: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3945: ifne            3950
        //  3948: iconst_0       
        //  3949: ireturn        
        //  3950: aload_0         /* this */
        //  3951: invokevirtual   com/empay/entities/PosTransactionEntity.getTcc:()Ljava/lang/String;
        //  3954: astore          this$tcc
        //  3956: aload_2         /* other */
        //  3957: invokevirtual   com/empay/entities/PosTransactionEntity.getTcc:()Ljava/lang/String;
        //  3960: astore          other$tcc
        //  3962: aload           this$tcc
        //  3964: ifnonnull       3975
        //  3967: aload           other$tcc
        //  3969: ifnull          3987
        //  3972: goto            3985
        //  3975: aload           this$tcc
        //  3977: aload           other$tcc
        //  3979: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3982: ifne            3987
        //  3985: iconst_0       
        //  3986: ireturn        
        //  3987: aload_0         /* this */
        //  3988: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlAppVerNumber:()Ljava/lang/String;
        //  3991: astore          this$trlAppVerNumber
        //  3993: aload_2         /* other */
        //  3994: invokevirtual   com/empay/entities/PosTransactionEntity.getTrlAppVerNumber:()Ljava/lang/String;
        //  3997: astore          other$trlAppVerNumber
        //  3999: aload           this$trlAppVerNumber
        //  4001: ifnonnull       4012
        //  4004: aload           other$trlAppVerNumber
        //  4006: ifnull          4024
        //  4009: goto            4022
        //  4012: aload           this$trlAppVerNumber
        //  4014: aload           other$trlAppVerNumber
        //  4016: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4019: ifne            4024
        //  4022: iconst_0       
        //  4023: ireturn        
        //  4024: aload_0         /* this */
        //  4025: invokevirtual   com/empay/entities/PosTransactionEntity.getIssAuthData:()Ljava/lang/String;
        //  4028: astore          this$issAuthData
        //  4030: aload_2         /* other */
        //  4031: invokevirtual   com/empay/entities/PosTransactionEntity.getIssAuthData:()Ljava/lang/String;
        //  4034: astore          other$issAuthData
        //  4036: aload           this$issAuthData
        //  4038: ifnonnull       4049
        //  4041: aload           other$issAuthData
        //  4043: ifnull          4061
        //  4046: goto            4059
        //  4049: aload           this$issAuthData
        //  4051: aload           other$issAuthData
        //  4053: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4056: ifne            4061
        //  4059: iconst_0       
        //  4060: ireturn        
        //  4061: aload_0         /* this */
        //  4062: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnId:()Ljava/lang/String;
        //  4065: astore          this$txnId
        //  4067: aload_2         /* other */
        //  4068: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnId:()Ljava/lang/String;
        //  4071: astore          other$txnId
        //  4073: aload           this$txnId
        //  4075: ifnonnull       4086
        //  4078: aload           other$txnId
        //  4080: ifnull          4098
        //  4083: goto            4096
        //  4086: aload           this$txnId
        //  4088: aload           other$txnId
        //  4090: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4093: ifne            4098
        //  4096: iconst_0       
        //  4097: ireturn        
        //  4098: aload_0         /* this */
        //  4099: invokevirtual   com/empay/entities/PosTransactionEntity.getCardAccepStreetAddress:()Ljava/lang/String;
        //  4102: astore          this$cardAccepStreetAddress
        //  4104: aload_2         /* other */
        //  4105: invokevirtual   com/empay/entities/PosTransactionEntity.getCardAccepStreetAddress:()Ljava/lang/String;
        //  4108: astore          other$cardAccepStreetAddress
        //  4110: aload           this$cardAccepStreetAddress
        //  4112: ifnonnull       4123
        //  4115: aload           other$cardAccepStreetAddress
        //  4117: ifnull          4135
        //  4120: goto            4133
        //  4123: aload           this$cardAccepStreetAddress
        //  4125: aload           other$cardAccepStreetAddress
        //  4127: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4130: ifne            4135
        //  4133: iconst_0       
        //  4134: ireturn        
        //  4135: aload_0         /* this */
        //  4136: invokevirtual   com/empay/entities/PosTransactionEntity.getCardAccepStateCode:()Ljava/lang/String;
        //  4139: astore          this$cardAccepStateCode
        //  4141: aload_2         /* other */
        //  4142: invokevirtual   com/empay/entities/PosTransactionEntity.getCardAccepStateCode:()Ljava/lang/String;
        //  4145: astore          other$cardAccepStateCode
        //  4147: aload           this$cardAccepStateCode
        //  4149: ifnonnull       4160
        //  4152: aload           other$cardAccepStateCode
        //  4154: ifnull          4172
        //  4157: goto            4170
        //  4160: aload           this$cardAccepStateCode
        //  4162: aload           other$cardAccepStateCode
        //  4164: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4167: ifne            4172
        //  4170: iconst_0       
        //  4171: ireturn        
        //  4172: aload_0         /* this */
        //  4173: invokevirtual   com/empay/entities/PosTransactionEntity.getExpiryDate:()Ljava/lang/String;
        //  4176: astore          this$expiryDate
        //  4178: aload_2         /* other */
        //  4179: invokevirtual   com/empay/entities/PosTransactionEntity.getExpiryDate:()Ljava/lang/String;
        //  4182: astore          other$expiryDate
        //  4184: aload           this$expiryDate
        //  4186: ifnonnull       4197
        //  4189: aload           other$expiryDate
        //  4191: ifnull          4209
        //  4194: goto            4207
        //  4197: aload           this$expiryDate
        //  4199: aload           other$expiryDate
        //  4201: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4204: ifne            4209
        //  4207: iconst_0       
        //  4208: ireturn        
        //  4209: aload_0         /* this */
        //  4210: invokevirtual   com/empay/entities/PosTransactionEntity.getIrd:()Ljava/lang/String;
        //  4213: astore          this$ird
        //  4215: aload_2         /* other */
        //  4216: invokevirtual   com/empay/entities/PosTransactionEntity.getIrd:()Ljava/lang/String;
        //  4219: astore          other$ird
        //  4221: aload           this$ird
        //  4223: ifnonnull       4234
        //  4226: aload           other$ird
        //  4228: ifnull          4246
        //  4231: goto            4244
        //  4234: aload           this$ird
        //  4236: aload           other$ird
        //  4238: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4241: ifne            4246
        //  4244: iconst_0       
        //  4245: ireturn        
        //  4246: aload_0         /* this */
        //  4247: invokevirtual   com/empay/entities/PosTransactionEntity.getRemarks:()Ljava/lang/String;
        //  4250: astore          this$remarks
        //  4252: aload_2         /* other */
        //  4253: invokevirtual   com/empay/entities/PosTransactionEntity.getRemarks:()Ljava/lang/String;
        //  4256: astore          other$remarks
        //  4258: aload           this$remarks
        //  4260: ifnonnull       4271
        //  4263: aload           other$remarks
        //  4265: ifnull          4283
        //  4268: goto            4281
        //  4271: aload           this$remarks
        //  4273: aload           other$remarks
        //  4275: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4278: ifne            4283
        //  4281: iconst_0       
        //  4282: ireturn        
        //  4283: aload_0         /* this */
        //  4284: invokevirtual   com/empay/entities/PosTransactionEntity.getValidationCode:()Ljava/lang/String;
        //  4287: astore          this$validationCode
        //  4289: aload_2         /* other */
        //  4290: invokevirtual   com/empay/entities/PosTransactionEntity.getValidationCode:()Ljava/lang/String;
        //  4293: astore          other$validationCode
        //  4295: aload           this$validationCode
        //  4297: ifnonnull       4308
        //  4300: aload           other$validationCode
        //  4302: ifnull          4320
        //  4305: goto            4318
        //  4308: aload           this$validationCode
        //  4310: aload           other$validationCode
        //  4312: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4315: ifne            4320
        //  4318: iconst_0       
        //  4319: ireturn        
        //  4320: aload_0         /* this */
        //  4321: invokevirtual   com/empay/entities/PosTransactionEntity.getMarketSpecAuthDataInd:()Ljava/lang/String;
        //  4324: astore          this$marketSpecAuthDataInd
        //  4326: aload_2         /* other */
        //  4327: invokevirtual   com/empay/entities/PosTransactionEntity.getMarketSpecAuthDataInd:()Ljava/lang/String;
        //  4330: astore          other$marketSpecAuthDataInd
        //  4332: aload           this$marketSpecAuthDataInd
        //  4334: ifnonnull       4345
        //  4337: aload           other$marketSpecAuthDataInd
        //  4339: ifnull          4357
        //  4342: goto            4355
        //  4345: aload           this$marketSpecAuthDataInd
        //  4347: aload           other$marketSpecAuthDataInd
        //  4349: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4352: ifne            4357
        //  4355: iconst_0       
        //  4356: ireturn        
        //  4357: aload_0         /* this */
        //  4358: invokevirtual   com/empay/entities/PosTransactionEntity.getProductId:()Ljava/lang/String;
        //  4361: astore          this$productId
        //  4363: aload_2         /* other */
        //  4364: invokevirtual   com/empay/entities/PosTransactionEntity.getProductId:()Ljava/lang/String;
        //  4367: astore          other$productId
        //  4369: aload           this$productId
        //  4371: ifnonnull       4382
        //  4374: aload           other$productId
        //  4376: ifnull          4394
        //  4379: goto            4392
        //  4382: aload           this$productId
        //  4384: aload           other$productId
        //  4386: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4389: ifne            4394
        //  4392: iconst_0       
        //  4393: ireturn        
        //  4394: aload_0         /* this */
        //  4395: invokevirtual   com/empay/entities/PosTransactionEntity.getFeePgmIndicator:()Ljava/lang/String;
        //  4398: astore          this$feePgmIndicator
        //  4400: aload_2         /* other */
        //  4401: invokevirtual   com/empay/entities/PosTransactionEntity.getFeePgmIndicator:()Ljava/lang/String;
        //  4404: astore          other$feePgmIndicator
        //  4406: aload           this$feePgmIndicator
        //  4408: ifnonnull       4419
        //  4411: aload           other$feePgmIndicator
        //  4413: ifnull          4431
        //  4416: goto            4429
        //  4419: aload           this$feePgmIndicator
        //  4421: aload           other$feePgmIndicator
        //  4423: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4426: ifne            4431
        //  4429: iconst_0       
        //  4430: ireturn        
        //  4431: aload_0         /* this */
        //  4432: invokevirtual   com/empay/entities/PosTransactionEntity.getBanKCode:()Ljava/lang/String;
        //  4435: astore          this$banKCode
        //  4437: aload_2         /* other */
        //  4438: invokevirtual   com/empay/entities/PosTransactionEntity.getBanKCode:()Ljava/lang/String;
        //  4441: astore          other$banKCode
        //  4443: aload           this$banKCode
        //  4445: ifnonnull       4456
        //  4448: aload           other$banKCode
        //  4450: ifnull          4468
        //  4453: goto            4466
        //  4456: aload           this$banKCode
        //  4458: aload           other$banKCode
        //  4460: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4463: ifne            4468
        //  4466: iconst_0       
        //  4467: ireturn        
        //  4468: aload_0         /* this */
        //  4469: invokevirtual   com/empay/entities/PosTransactionEntity.getMaid:()Ljava/lang/String;
        //  4472: astore          this$maid
        //  4474: aload_2         /* other */
        //  4475: invokevirtual   com/empay/entities/PosTransactionEntity.getMaid:()Ljava/lang/String;
        //  4478: astore          other$maid
        //  4480: aload           this$maid
        //  4482: ifnonnull       4493
        //  4485: aload           other$maid
        //  4487: ifnull          4505
        //  4490: goto            4503
        //  4493: aload           this$maid
        //  4495: aload           other$maid
        //  4497: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4500: ifne            4505
        //  4503: iconst_0       
        //  4504: ireturn        
        //  4505: aload_0         /* this */
        //  4506: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTrlCapabilities:()Ljava/lang/String;
        //  4509: astore          this$chipTrlCapabilities
        //  4511: aload_2         /* other */
        //  4512: invokevirtual   com/empay/entities/PosTransactionEntity.getChipTrlCapabilities:()Ljava/lang/String;
        //  4515: astore          other$chipTrlCapabilities
        //  4517: aload           this$chipTrlCapabilities
        //  4519: ifnonnull       4530
        //  4522: aload           other$chipTrlCapabilities
        //  4524: ifnull          4542
        //  4527: goto            4540
        //  4530: aload           this$chipTrlCapabilities
        //  4532: aload           other$chipTrlCapabilities
        //  4534: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4537: ifne            4542
        //  4540: iconst_0       
        //  4541: ireturn        
        //  4542: aload_0         /* this */
        //  4543: invokevirtual   com/empay/entities/PosTransactionEntity.getMvv:()Ljava/lang/String;
        //  4546: astore          this$mvv
        //  4548: aload_2         /* other */
        //  4549: invokevirtual   com/empay/entities/PosTransactionEntity.getMvv:()Ljava/lang/String;
        //  4552: astore          other$mvv
        //  4554: aload           this$mvv
        //  4556: ifnonnull       4567
        //  4559: aload           other$mvv
        //  4561: ifnull          4579
        //  4564: goto            4577
        //  4567: aload           this$mvv
        //  4569: aload           other$mvv
        //  4571: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4574: ifne            4579
        //  4577: iconst_0       
        //  4578: ireturn        
        //  4579: aload_0         /* this */
        //  4580: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnCode:()Ljava/lang/String;
        //  4583: astore          this$txnCode
        //  4585: aload_2         /* other */
        //  4586: invokevirtual   com/empay/entities/PosTransactionEntity.getTxnCode:()Ljava/lang/String;
        //  4589: astore          other$txnCode
        //  4591: aload           this$txnCode
        //  4593: ifnonnull       4604
        //  4596: aload           other$txnCode
        //  4598: ifnull          4616
        //  4601: goto            4614
        //  4604: aload           this$txnCode
        //  4606: aload           other$txnCode
        //  4608: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4611: ifne            4616
        //  4614: iconst_0       
        //  4615: ireturn        
        //  4616: aload_0         /* this */
        //  4617: invokevirtual   com/empay/entities/PosTransactionEntity.getAdtlAmounts:()Ljava/lang/String;
        //  4620: astore          this$adtlAmounts
        //  4622: aload_2         /* other */
        //  4623: invokevirtual   com/empay/entities/PosTransactionEntity.getAdtlAmounts:()Ljava/lang/String;
        //  4626: astore          other$adtlAmounts
        //  4628: aload           this$adtlAmounts
        //  4630: ifnonnull       4641
        //  4633: aload           other$adtlAmounts
        //  4635: ifnull          4653
        //  4638: goto            4651
        //  4641: aload           this$adtlAmounts
        //  4643: aload           other$adtlAmounts
        //  4645: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4648: ifne            4653
        //  4651: iconst_0       
        //  4652: ireturn        
        //  4653: aload_0         /* this */
        //  4654: invokevirtual   com/empay/entities/PosTransactionEntity.getEncCardNumber:()Ljava/lang/String;
        //  4657: astore          this$encCardNumber
        //  4659: aload_2         /* other */
        //  4660: invokevirtual   com/empay/entities/PosTransactionEntity.getEncCardNumber:()Ljava/lang/String;
        //  4663: astore          other$encCardNumber
        //  4665: aload           this$encCardNumber
        //  4667: ifnonnull       4678
        //  4670: aload           other$encCardNumber
        //  4672: ifnull          4690
        //  4675: goto            4688
        //  4678: aload           this$encCardNumber
        //  4680: aload           other$encCardNumber
        //  4682: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4685: ifne            4690
        //  4688: iconst_0       
        //  4689: ireturn        
        //  4690: aload_0         /* this */
        //  4691: invokevirtual   com/empay/entities/PosTransactionEntity.getFormFactorIndicator:()Ljava/lang/String;
        //  4694: astore          this$formFactorIndicator
        //  4696: aload_2         /* other */
        //  4697: invokevirtual   com/empay/entities/PosTransactionEntity.getFormFactorIndicator:()Ljava/lang/String;
        //  4700: astore_w        256
        //  4704: aload           this$formFactorIndicator
        //  4706: ifnonnull       4719
        //  4709: aload_w         other$formFactorIndicator
        //  4713: ifnull          4733
        //  4716: goto            4731
        //  4719: aload           this$formFactorIndicator
        //  4721: aload_w         other$formFactorIndicator
        //  4725: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4728: ifne            4733
        //  4731: iconst_0       
        //  4732: ireturn        
        //  4733: aload_0         /* this */
        //  4734: invokevirtual   com/empay/entities/PosTransactionEntity.getOutStatus:()Ljava/lang/String;
        //  4737: astore_w        257
        //  4741: aload_2         /* other */
        //  4742: invokevirtual   com/empay/entities/PosTransactionEntity.getOutStatus:()Ljava/lang/String;
        //  4745: astore_w        258
        //  4749: aload_w         this$outStatus
        //  4753: ifnonnull       4766
        //  4756: aload_w         other$outStatus
        //  4760: ifnull          4782
        //  4763: goto            4780
        //  4766: aload_w         this$outStatus
        //  4770: aload_w         other$outStatus
        //  4774: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4777: ifne            4782
        //  4780: iconst_0       
        //  4781: ireturn        
        //  4782: aload_0         /* this */
        //  4783: invokevirtual   com/empay/entities/PosTransactionEntity.getIncomingStatus:()Ljava/lang/String;
        //  4786: astore_w        259
        //  4790: aload_2         /* other */
        //  4791: invokevirtual   com/empay/entities/PosTransactionEntity.getIncomingStatus:()Ljava/lang/String;
        //  4794: astore_w        260
        //  4798: aload_w         this$incomingStatus
        //  4802: ifnonnull       4815
        //  4805: aload_w         other$incomingStatus
        //  4809: ifnull          4831
        //  4812: goto            4829
        //  4815: aload_w         this$incomingStatus
        //  4819: aload_w         other$incomingStatus
        //  4823: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4826: ifne            4831
        //  4829: iconst_0       
        //  4830: ireturn        
        //  4831: aload_0         /* this */
        //  4832: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCountryOfOrigin:()Ljava/lang/String;
        //  4835: astore_w        261
        //  4839: aload_2         /* other */
        //  4840: invokevirtual   com/empay/entities/PosTransactionEntity.getMeCountryOfOrigin:()Ljava/lang/String;
        //  4843: astore_w        262
        //  4847: aload_w         this$meCountryOfOrigin
        //  4851: ifnonnull       4864
        //  4854: aload_w         other$meCountryOfOrigin
        //  4858: ifnull          4880
        //  4861: goto            4878
        //  4864: aload_w         this$meCountryOfOrigin
        //  4868: aload_w         other$meCountryOfOrigin
        //  4872: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4875: ifne            4880
        //  4878: iconst_0       
        //  4879: ireturn        
        //  4880: aload_0         /* this */
        //  4881: invokevirtual   com/empay/entities/PosTransactionEntity.getOriginalRRN:()Ljava/lang/String;
        //  4884: astore_w        263
        //  4888: aload_2         /* other */
        //  4889: invokevirtual   com/empay/entities/PosTransactionEntity.getOriginalRRN:()Ljava/lang/String;
        //  4892: astore_w        264
        //  4896: aload_w         this$originalRRN
        //  4900: ifnonnull       4913
        //  4903: aload_w         other$originalRRN
        //  4907: ifnull          4929
        //  4910: goto            4927
        //  4913: aload_w         this$originalRRN
        //  4917: aload_w         other$originalRRN
        //  4921: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4924: ifne            4929
        //  4927: iconst_0       
        //  4928: ireturn        
        //  4929: aload_0         /* this */
        //  4930: invokevirtual   com/empay/entities/PosTransactionEntity.getAmexMerchantId:()Ljava/lang/String;
        //  4933: astore_w        265
        //  4937: aload_2         /* other */
        //  4938: invokevirtual   com/empay/entities/PosTransactionEntity.getAmexMerchantId:()Ljava/lang/String;
        //  4941: astore_w        266
        //  4945: aload_w         this$amexMerchantId
        //  4949: ifnonnull       4962
        //  4952: aload_w         other$amexMerchantId
        //  4956: ifnull          4978
        //  4959: goto            4976
        //  4962: aload_w         this$amexMerchantId
        //  4966: aload_w         other$amexMerchantId
        //  4970: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4973: ifne            4978
        //  4976: iconst_0       
        //  4977: ireturn        
        //  4978: aload_0         /* this */
        //  4979: invokevirtual   com/empay/entities/PosTransactionEntity.getMerchantContactInfo:()Ljava/lang/String;
        //  4982: astore_w        267
        //  4986: aload_2         /* other */
        //  4987: invokevirtual   com/empay/entities/PosTransactionEntity.getMerchantContactInfo:()Ljava/lang/String;
        //  4990: astore_w        268
        //  4994: aload_w         this$merchantContactInfo
        //  4998: ifnonnull       5011
        //  5001: aload_w         other$merchantContactInfo
        //  5005: ifnull          5027
        //  5008: goto            5025
        //  5011: aload_w         this$merchantContactInfo
        //  5015: aload_w         other$merchantContactInfo
        //  5019: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5022: ifne            5027
        //  5025: iconst_0       
        //  5026: ireturn        
        //  5027: aload_0         /* this */
        //  5028: invokevirtual   com/empay/entities/PosTransactionEntity.getMerchantLocationId:()Ljava/lang/String;
        //  5031: astore_w        269
        //  5035: aload_2         /* other */
        //  5036: invokevirtual   com/empay/entities/PosTransactionEntity.getMerchantLocationId:()Ljava/lang/String;
        //  5039: astore_w        270
        //  5043: aload_w         this$merchantLocationId
        //  5047: ifnonnull       5060
        //  5050: aload_w         other$merchantLocationId
        //  5054: ifnull          5076
        //  5057: goto            5074
        //  5060: aload_w         this$merchantLocationId
        //  5064: aload_w         other$merchantLocationId
        //  5068: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5071: ifne            5076
        //  5074: iconst_0       
        //  5075: ireturn        
        //  5076: aload_0         /* this */
        //  5077: invokevirtual   com/empay/entities/PosTransactionEntity.getLocationRegionCode:()Ljava/lang/String;
        //  5080: astore_w        271
        //  5084: aload_2         /* other */
        //  5085: invokevirtual   com/empay/entities/PosTransactionEntity.getLocationRegionCode:()Ljava/lang/String;
        //  5088: astore_w        272
        //  5092: aload_w         this$locationRegionCode
        //  5096: ifnonnull       5109
        //  5099: aload_w         other$locationRegionCode
        //  5103: ifnull          5125
        //  5106: goto            5123
        //  5109: aload_w         this$locationRegionCode
        //  5113: aload_w         other$locationRegionCode
        //  5117: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5120: ifne            5125
        //  5123: iconst_0       
        //  5124: ireturn        
        //  5125: aload_0         /* this */
        //  5126: invokevirtual   com/empay/entities/PosTransactionEntity.getInvoiceNumber:()Ljava/lang/String;
        //  5129: astore_w        273
        //  5133: aload_2         /* other */
        //  5134: invokevirtual   com/empay/entities/PosTransactionEntity.getInvoiceNumber:()Ljava/lang/String;
        //  5137: astore_w        274
        //  5141: aload_w         this$invoiceNumber
        //  5145: ifnonnull       5158
        //  5148: aload_w         other$invoiceNumber
        //  5152: ifnull          5174
        //  5155: goto            5172
        //  5158: aload_w         this$invoiceNumber
        //  5162: aload_w         other$invoiceNumber
        //  5166: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5169: ifne            5174
        //  5172: iconst_0       
        //  5173: ireturn        
        //  5174: aload_0         /* this */
        //  5175: invokevirtual   com/empay/entities/PosTransactionEntity.getDedicatedFileName:()Ljava/lang/String;
        //  5178: astore_w        275
        //  5182: aload_2         /* other */
        //  5183: invokevirtual   com/empay/entities/PosTransactionEntity.getDedicatedFileName:()Ljava/lang/String;
        //  5186: astore_w        276
        //  5190: aload_w         this$dedicatedFileName
        //  5194: ifnonnull       5207
        //  5197: aload_w         other$dedicatedFileName
        //  5201: ifnull          5223
        //  5204: goto            5221
        //  5207: aload_w         this$dedicatedFileName
        //  5211: aload_w         other$dedicatedFileName
        //  5215: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5218: ifne            5223
        //  5221: iconst_0       
        //  5222: ireturn        
        //  5223: aload_0         /* this */
        //  5224: invokevirtual   com/empay/entities/PosTransactionEntity.getScheme:()Ljava/lang/String;
        //  5227: astore_w        277
        //  5231: aload_2         /* other */
        //  5232: invokevirtual   com/empay/entities/PosTransactionEntity.getScheme:()Ljava/lang/String;
        //  5235: astore_w        278
        //  5239: aload_w         this$scheme
        //  5243: ifnonnull       5256
        //  5246: aload_w         other$scheme
        //  5250: ifnull          5272
        //  5253: goto            5270
        //  5256: aload_w         this$scheme
        //  5260: aload_w         other$scheme
        //  5264: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5267: ifne            5272
        //  5270: iconst_0       
        //  5271: ireturn        
        //  5272: aload_0         /* this */
        //  5273: invokevirtual   com/empay/entities/PosTransactionEntity.getDccCurrency:()Ljava/lang/String;
        //  5276: astore_w        279
        //  5280: aload_2         /* other */
        //  5281: invokevirtual   com/empay/entities/PosTransactionEntity.getDccCurrency:()Ljava/lang/String;
        //  5284: astore_w        280
        //  5288: aload_w         this$dccCurrency
        //  5292: ifnonnull       5305
        //  5295: aload_w         other$dccCurrency
        //  5299: ifnull          5321
        //  5302: goto            5319
        //  5305: aload_w         this$dccCurrency
        //  5309: aload_w         other$dccCurrency
        //  5313: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5316: ifne            5321
        //  5319: iconst_0       
        //  5320: ireturn        
        //  5321: aload_0         /* this */
        //  5322: invokevirtual   com/empay/entities/PosTransactionEntity.getAccepterUrlAddress:()Ljava/lang/String;
        //  5325: astore_w        281
        //  5329: aload_2         /* other */
        //  5330: invokevirtual   com/empay/entities/PosTransactionEntity.getAccepterUrlAddress:()Ljava/lang/String;
        //  5333: astore_w        282
        //  5337: aload_w         this$accepterUrlAddress
        //  5341: ifnonnull       5354
        //  5344: aload_w         other$accepterUrlAddress
        //  5348: ifnull          5370
        //  5351: goto            5368
        //  5354: aload_w         this$accepterUrlAddress
        //  5358: aload_w         other$accepterUrlAddress
        //  5362: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  5365: ifne            5370
        //  5368: iconst_0       
        //  5369: ireturn        
        //  5370: iconst_1       
        //  5371: ireturn        
        //    MethodParameters:
        //  Name  Flags  
        //  ----  -----
        //  o     FINAL
        //    StackMapTable: 01 A7 07 08 FC 00 0E 07 00 08 FD 00 16 07 01 BC 07 01 BC 08 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 18 07 01 BC 07 01 BC 09 01 FD 00 1C 07 01 BC 07 01 BC 0B 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01 FD 00 20 07 01 BC 07 01 BC 0D 01
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException: Cannot read field "references" because "newVariable" is null
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2945)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2501)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:203)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:93)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:868)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:761)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:638)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:605)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:195)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:162)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:137)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:334)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:255)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:130)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof PosTransactionEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $jobNumber = this.getJobNumber();
        result = result * 59 + (($jobNumber == null) ? 43 : $jobNumber.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $stagingFlag = this.getStagingFlag();
        result = result * 59 + (($stagingFlag == null) ? 43 : $stagingFlag.hashCode());
        final Object $paymentFlag = this.getPaymentFlag();
        result = result * 59 + (($paymentFlag == null) ? 43 : $paymentFlag.hashCode());
        final Object $agrSernumber = this.getAgrSernumber();
        result = result * 59 + (($agrSernumber == null) ? 43 : $agrSernumber.hashCode());
        final Object $cardInputAbility = this.getCardInputAbility();
        result = result * 59 + (($cardInputAbility == null) ? 43 : $cardInputAbility.hashCode());
        final Object $cardCaptureAbility = this.getCardCaptureAbility();
        result = result * 59 + (($cardCaptureAbility == null) ? 43 : $cardCaptureAbility.hashCode());
        final Object $chPresent = this.getChPresent();
        result = result * 59 + (($chPresent == null) ? 43 : $chPresent.hashCode());
        final Object $cardPresent = this.getCardPresent();
        result = result * 59 + (($cardPresent == null) ? 43 : $cardPresent.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + (($txnFeeAmount == null) ? 43 : $txnFeeAmount.hashCode());
        final Object $setlAmount = this.getSetlAmount();
        result = result * 59 + (($setlAmount == null) ? 43 : $setlAmount.hashCode());
        final Object $netAmount = this.getNetAmount();
        result = result * 59 + (($netAmount == null) ? 43 : $netAmount.hashCode());
        final Object $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + (($settlementIndicator == null) ? 43 : $settlementIndicator.hashCode());
        final Object $onusOffusFlag = this.getOnusOffusFlag();
        result = result * 59 + (($onusOffusFlag == null) ? 43 : $onusOffusFlag.hashCode());
        final Object $revIndiCator = this.getRevIndiCator();
        result = result * 59 + (($revIndiCator == null) ? 43 : $revIndiCator.hashCode());
        final Object $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + (($dmsSmsMode == null) ? 43 : $dmsSmsMode.hashCode());
        final Object $chAuthAbility = this.getChAuthAbility();
        result = result * 59 + (($chAuthAbility == null) ? 43 : $chAuthAbility.hashCode());
        final Object $chAuthEntity = this.getChAuthEntity();
        result = result * 59 + (($chAuthEntity == null) ? 43 : $chAuthEntity.hashCode());
        final Object $cardOutPutAbility = this.getCardOutPutAbility();
        result = result * 59 + (($cardOutPutAbility == null) ? 43 : $cardOutPutAbility.hashCode());
        final Object $trlOutPutAbility = this.getTrlOutPutAbility();
        result = result * 59 + (($trlOutPutAbility == null) ? 43 : $trlOutPutAbility.hashCode());
        final Object $chAuthMethod = this.getChAuthMethod();
        result = result * 59 + (($chAuthMethod == null) ? 43 : $chAuthMethod.hashCode());
        final Object $cryptAmount = this.getCryptAmount();
        result = result * 59 + (($cryptAmount == null) ? 43 : $cryptAmount.hashCode());
        final Object $chipCashBack = this.getChipCashBack();
        result = result * 59 + (($chipCashBack == null) ? 43 : $chipCashBack.hashCode());
        final Object $meCategoryType = this.getMeCategoryType();
        result = result * 59 + (($meCategoryType == null) ? 43 : $meCategoryType.hashCode());
        final Object $irdSerNumber = this.getIrdSerNumber();
        result = result * 59 + (($irdSerNumber == null) ? 43 : $irdSerNumber.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $irfPercent = this.getIrfPercent();
        result = result * 59 + (($irfPercent == null) ? 43 : $irfPercent.hashCode());
        final Object $irfAmount = this.getIrfAmount();
        result = result * 59 + (($irfAmount == null) ? 43 : $irfAmount.hashCode());
        final Object $chIdMethod = this.getChIdMethod();
        result = result * 59 + (($chIdMethod == null) ? 43 : $chIdMethod.hashCode());
        final Object $cardInputMode = this.getCardInputMode();
        result = result * 59 + (($cardInputMode == null) ? 43 : $cardInputMode.hashCode());
        final Object $authCharecteresticId = this.getAuthCharecteresticId();
        result = result * 59 + (($authCharecteresticId == null) ? 43 : $authCharecteresticId.hashCode());
        final Object $spendQualificationInd = this.getSpendQualificationInd();
        result = result * 59 + (($spendQualificationInd == null) ? 43 : $spendQualificationInd.hashCode());
        final Object $reImbursementAttribute = this.getReImbursementAttribute();
        result = result * 59 + (($reImbursementAttribute == null) ? 43 : $reImbursementAttribute.hashCode());
        final Object $accountFundingSource = this.getAccountFundingSource();
        result = result * 59 + (($accountFundingSource == null) ? 43 : $accountFundingSource.hashCode());
        final Object $irfMaxAmount = this.getIrfMaxAmount();
        result = result * 59 + (($irfMaxAmount == null) ? 43 : $irfMaxAmount.hashCode());
        final Object $irfMinAmount = this.getIrfMinAmount();
        result = result * 59 + (($irfMinAmount == null) ? 43 : $irfMinAmount.hashCode());
        final Object $authAmount = this.getAuthAmount();
        result = result * 59 + (($authAmount == null) ? 43 : $authAmount.hashCode());
        final Object $irfAmountUSD = this.getIrfAmountUSD();
        result = result * 59 + (($irfAmountUSD == null) ? 43 : $irfAmountUSD.hashCode());
        final Object $refundIndicator = this.getRefundIndicator();
        result = result * 59 + (($refundIndicator == null) ? 43 : $refundIndicator.hashCode());
        final Object $tipAmount = this.getTipAmount();
        result = result * 59 + (($tipAmount == null) ? 43 : $tipAmount.hashCode());
        final Object $dccAmount = this.getDccAmount();
        result = result * 59 + (($dccAmount == null) ? 43 : $dccAmount.hashCode());
        final Object $dccIndicator = this.getDccIndicator();
        result = result * 59 + (($dccIndicator == null) ? 43 : $dccIndicator.hashCode());
        final Object $dccExchangeRate = this.getDccExchangeRate();
        result = result * 59 + (($dccExchangeRate == null) ? 43 : $dccExchangeRate.hashCode());
        final Object $mposAccDevType = this.getMposAccDevType();
        result = result * 59 + (($mposAccDevType == null) ? 43 : $mposAccDevType.hashCode());
        final Object $acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        result = result * 59 + (($acceptanceTrlIndicator == null) ? 43 : $acceptanceTrlIndicator.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        final Object $stan = this.getStan();
        result = result * 59 + (($stan == null) ? 43 : $stan.hashCode());
        final Object $authReason = this.getAuthReason();
        result = result * 59 + (($authReason == null) ? 43 : $authReason.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $mePinCode = this.getMePinCode();
        result = result * 59 + (($mePinCode == null) ? 43 : $mePinCode.hashCode());
        final Object $bussDate = this.getBussDate();
        result = result * 59 + (($bussDate == null) ? 43 : $bussDate.hashCode());
        final Object $txnUniqueId = this.getTxnUniqueId();
        result = result * 59 + (($txnUniqueId == null) ? 43 : $txnUniqueId.hashCode());
        final Object $msgTypeId = this.getMsgTypeId();
        result = result * 59 + (($msgTypeId == null) ? 43 : $msgTypeId.hashCode());
        final Object $oprtEnvironment = this.getOprtEnvironment();
        result = result * 59 + (($oprtEnvironment == null) ? 43 : $oprtEnvironment.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $setlDate = this.getSetlDate();
        result = result * 59 + (($setlDate == null) ? 43 : $setlDate.hashCode());
        final Object $setlCurCode = this.getSetlCurCode();
        result = result * 59 + (($setlCurCode == null) ? 43 : $setlCurCode.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $posConditionCode = this.getPosConditionCode();
        result = result * 59 + (($posConditionCode == null) ? 43 : $posConditionCode.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + (($cardSeqNumber == null) ? 43 : $cardSeqNumber.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $terminalType = this.getTerminalType();
        result = result * 59 + (($terminalType == null) ? 43 : $terminalType.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $centreProcDate = this.getCentreProcDate();
        result = result * 59 + (($centreProcDate == null) ? 43 : $centreProcDate.hashCode());
        final Object $cardCategory = this.getCardCategory();
        result = result * 59 + (($cardCategory == null) ? 43 : $cardCategory.hashCode());
        final Object $cardSubCategory = this.getCardSubCategory();
        result = result * 59 + (($cardSubCategory == null) ? 43 : $cardSubCategory.hashCode());
        final Object $networkData = this.getNetworkData();
        result = result * 59 + (($networkData == null) ? 43 : $networkData.hashCode());
        final Object $txnDateTime = this.getTxnDateTime();
        result = result * 59 + (($txnDateTime == null) ? 43 : $txnDateTime.hashCode());
        final Object $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + (($acqinstIdCode == null) ? 43 : $acqinstIdCode.hashCode());
        final Object $acqInstConCode = this.getAcqInstConCode();
        result = result * 59 + (($acqInstConCode == null) ? 43 : $acqInstConCode.hashCode());
        final Object $appCryptogram = this.getAppCryptogram();
        result = result * 59 + (($appCryptogram == null) ? 43 : $appCryptogram.hashCode());
        final Object $cryptInfoData = this.getCryptInfoData();
        result = result * 59 + (($cryptInfoData == null) ? 43 : $cryptInfoData.hashCode());
        final Object $issAppData = this.getIssAppData();
        result = result * 59 + (($issAppData == null) ? 43 : $issAppData.hashCode());
        final Object $upblNumber = this.getUpblNumber();
        result = result * 59 + (($upblNumber == null) ? 43 : $upblNumber.hashCode());
        final Object $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + (($appTxnCounter == null) ? 43 : $appTxnCounter.hashCode());
        final Object $trlVerResult = this.getTrlVerResult();
        result = result * 59 + (($trlVerResult == null) ? 43 : $trlVerResult.hashCode());
        final Object $chipTxnDate = this.getChipTxnDate();
        result = result * 59 + (($chipTxnDate == null) ? 43 : $chipTxnDate.hashCode());
        final Object $chipTxnType = this.getChipTxnType();
        result = result * 59 + (($chipTxnType == null) ? 43 : $chipTxnType.hashCode());
        final Object $chipCurCode = this.getChipCurCode();
        result = result * 59 + (($chipCurCode == null) ? 43 : $chipCurCode.hashCode());
        final Object $appICProfile = this.getAppICProfile();
        result = result * 59 + (($appICProfile == null) ? 43 : $appICProfile.hashCode());
        final Object $trlConCode = this.getTrlConCode();
        result = result * 59 + (($trlConCode == null) ? 43 : $trlConCode.hashCode());
        final Object $cvmResult = this.getCvmResult();
        result = result * 59 + (($cvmResult == null) ? 43 : $cvmResult.hashCode());
        final Object $trlCapabilities = this.getTrlCapabilities();
        result = result * 59 + (($trlCapabilities == null) ? 43 : $trlCapabilities.hashCode());
        final Object $chipTrlType = this.getChipTrlType();
        result = result * 59 + (($chipTrlType == null) ? 43 : $chipTrlType.hashCode());
        final Object $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + (($ifdSerNumber == null) ? 43 : $ifdSerNumber.hashCode());
        final Object $tcc = this.getTcc();
        result = result * 59 + (($tcc == null) ? 43 : $tcc.hashCode());
        final Object $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + (($trlAppVerNumber == null) ? 43 : $trlAppVerNumber.hashCode());
        final Object $issAuthData = this.getIssAuthData();
        result = result * 59 + (($issAuthData == null) ? 43 : $issAuthData.hashCode());
        final Object $txnId = this.getTxnId();
        result = result * 59 + (($txnId == null) ? 43 : $txnId.hashCode());
        final Object $cardAccepStreetAddress = this.getCardAccepStreetAddress();
        result = result * 59 + (($cardAccepStreetAddress == null) ? 43 : $cardAccepStreetAddress.hashCode());
        final Object $cardAccepStateCode = this.getCardAccepStateCode();
        result = result * 59 + (($cardAccepStateCode == null) ? 43 : $cardAccepStateCode.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * 59 + (($expiryDate == null) ? 43 : $expiryDate.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        final Object $remarks = this.getRemarks();
        result = result * 59 + (($remarks == null) ? 43 : $remarks.hashCode());
        final Object $validationCode = this.getValidationCode();
        result = result * 59 + (($validationCode == null) ? 43 : $validationCode.hashCode());
        final Object $marketSpecAuthDataInd = this.getMarketSpecAuthDataInd();
        result = result * 59 + (($marketSpecAuthDataInd == null) ? 43 : $marketSpecAuthDataInd.hashCode());
        final Object $productId = this.getProductId();
        result = result * 59 + (($productId == null) ? 43 : $productId.hashCode());
        final Object $feePgmIndicator = this.getFeePgmIndicator();
        result = result * 59 + (($feePgmIndicator == null) ? 43 : $feePgmIndicator.hashCode());
        final Object $banKCode = this.getBanKCode();
        result = result * 59 + (($banKCode == null) ? 43 : $banKCode.hashCode());
        final Object $maid = this.getMaid();
        result = result * 59 + (($maid == null) ? 43 : $maid.hashCode());
        final Object $chipTrlCapabilities = this.getChipTrlCapabilities();
        result = result * 59 + (($chipTrlCapabilities == null) ? 43 : $chipTrlCapabilities.hashCode());
        final Object $mvv = this.getMvv();
        result = result * 59 + (($mvv == null) ? 43 : $mvv.hashCode());
        final Object $txnCode = this.getTxnCode();
        result = result * 59 + (($txnCode == null) ? 43 : $txnCode.hashCode());
        final Object $adtlAmounts = this.getAdtlAmounts();
        result = result * 59 + (($adtlAmounts == null) ? 43 : $adtlAmounts.hashCode());
        final Object $encCardNumber = this.getEncCardNumber();
        result = result * 59 + (($encCardNumber == null) ? 43 : $encCardNumber.hashCode());
        final Object $formFactorIndicator = this.getFormFactorIndicator();
        result = result * 59 + (($formFactorIndicator == null) ? 43 : $formFactorIndicator.hashCode());
        final Object $outStatus = this.getOutStatus();
        result = result * 59 + (($outStatus == null) ? 43 : $outStatus.hashCode());
        final Object $incomingStatus = this.getIncomingStatus();
        result = result * 59 + (($incomingStatus == null) ? 43 : $incomingStatus.hashCode());
        final Object $meCountryOfOrigin = this.getMeCountryOfOrigin();
        result = result * 59 + (($meCountryOfOrigin == null) ? 43 : $meCountryOfOrigin.hashCode());
        final Object $originalRRN = this.getOriginalRRN();
        result = result * 59 + (($originalRRN == null) ? 43 : $originalRRN.hashCode());
        final Object $amexMerchantId = this.getAmexMerchantId();
        result = result * 59 + (($amexMerchantId == null) ? 43 : $amexMerchantId.hashCode());
        final Object $merchantContactInfo = this.getMerchantContactInfo();
        result = result * 59 + (($merchantContactInfo == null) ? 43 : $merchantContactInfo.hashCode());
        final Object $merchantLocationId = this.getMerchantLocationId();
        result = result * 59 + (($merchantLocationId == null) ? 43 : $merchantLocationId.hashCode());
        final Object $locationRegionCode = this.getLocationRegionCode();
        result = result * 59 + (($locationRegionCode == null) ? 43 : $locationRegionCode.hashCode());
        final Object $invoiceNumber = this.getInvoiceNumber();
        result = result * 59 + (($invoiceNumber == null) ? 43 : $invoiceNumber.hashCode());
        final Object $dedicatedFileName = this.getDedicatedFileName();
        result = result * 59 + (($dedicatedFileName == null) ? 43 : $dedicatedFileName.hashCode());
        final Object $scheme = this.getScheme();
        result = result * 59 + (($scheme == null) ? 43 : $scheme.hashCode());
        final Object $dccCurrency = this.getDccCurrency();
        result = result * 59 + (($dccCurrency == null) ? 43 : $dccCurrency.hashCode());
        final Object $accepterUrlAddress = this.getAccepterUrlAddress();
        result = result * 59 + (($accepterUrlAddress == null) ? 43 : $accepterUrlAddress.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "" + ("PosTransactionEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", genStatus=" + this.getGenStatus() + ", jobNumber=" + this.getJobNumber() + ", rrn=" + this.getRrn() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", cardNumber=" + this.getCardNumber() + ", txnAmount=" + this.getTxnAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", responseCode=" + this.getResponseCode() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", txnType=" + this.getTxnType() + ", procCode=" + this.getProcCode() + ", stan=" + this.getStan() + ", authReason=" + this.getAuthReason() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", mePinCode=" + this.getMePinCode() + ", stagingFlag=" + this.getStagingFlag() + ", paymentFlag=" + this.getPaymentFlag() + ", bussDate=" + String.valueOf(this.getBussDate()) + ", txnUniqueId=" + this.getTxnUniqueId() + ", msgTypeId=" + this.getMsgTypeId() + ", agrSernumber=" + this.getAgrSernumber() + ", cardInputAbility=" + this.getCardInputAbility() + ", cardCaptureAbility=" + this.getCardCaptureAbility() + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent() + ", oprtEnvironment=" + this.getOprtEnvironment() + ", cashBackAmount=" + this.getCashBackAmount() + ", txnCurCode=" + this.getTxnCurCode() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", setlAmount=" + this.getSetlAmount() + ", setlDate=" + String.valueOf(this.getSetlDate()) + ", setlCurCode=" + this.getSetlCurCode() + ", posEntryMode=" + this.getPosEntryMode() + ", posConditionCode=" + this.getPosConditionCode() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", serviceCode=" + this.getServiceCode() + ", mcc=" + this.getMcc() + ", terminalType=" + this.getTerminalType() + ", network=" + this.getNetwork() + ", netAmount=" + this.getNetAmount() + ", settlementIndicator=" + this.getSettlementIndicator() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", onusOffusFlag=" + this.getOnusOffusFlag() + ", revIndiCator=" + this.getRevIndiCator() + ", cardCategory=" + this.getCardCategory() + ", cardSubCategory=" + this.getCardSubCategory() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", networkData=" + this.getNetworkData() + ", chAuthAbility=" + this.getChAuthAbility() + ", chAuthEntity=" + this.getChAuthEntity() + ", cardOutPutAbility=" + this.getCardOutPutAbility() + ", trlOutPutAbility=" + this.getTrlOutPutAbility() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", acqInstConCode=" + this.getAcqInstConCode() + ", chAuthMethod=" + this.getChAuthMethod() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", issAppData=" + this.getIssAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", trlVerResult=" + this.getTrlVerResult() + ", chipTxnDate=" + this.getChipTxnDate() + ", chipTxnType=" + this.getChipTxnType() + ", cryptAmount=" + this.getCryptAmount() + ", chipCurCode=" + this.getChipCurCode() + ", appICProfile=" + this.getAppICProfile() + ", trlConCode=" + this.getTrlConCode() + ", chipCashBack=" + this.getChipCashBack() + ", cvmResult=" + this.getCvmResult() + ", trlCapabilities=" + this.getTrlCapabilities() + ", chipTrlType=" + this.getChipTrlType() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", tcc=" + this.getTcc() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", issAuthData=" + this.getIssAuthData() + ", txnId=" + this.getTxnId() + ", meCategoryType=" + this.getMeCategoryType() + ", cardAccepStreetAddress=" + this.getCardAccepStreetAddress() + ", cardAccepStateCode=" + this.getCardAccepStateCode() + ", expiryDate=" + this.getExpiryDate() + ", irdSerNumber=" + this.getIrdSerNumber() + ", ird=" + this.getIrd() + ", irfFixed=" + this.getIrfFixed() + ", irfPercent=" + this.getIrfPercent() + ", irfAmount=" + this.getIrfAmount() + ", remarks=" + this.getRemarks() + ", chIdMethod=" + this.getChIdMethod() + ", cardInputMode=") + /* invokedynamic(!) */ProcyonInvokeDynamicHelper_1.invoke(this.getCardInputMode(), this.getAuthCharecteresticId(), this.getValidationCode(), this.getMarketSpecAuthDataInd(), this.getProductId(), this.getSpendQualificationInd(), this.getReImbursementAttribute(), this.getFeePgmIndicator(), this.getAccountFundingSource(), this.getBanKCode(), this.getMaid(), this.getChipTrlCapabilities(), this.getMvv(), this.getTxnCode(), this.getAdtlAmounts(), this.getEncCardNumber(), this.getFormFactorIndicator(), this.getIrfMaxAmount(), this.getIrfMinAmount(), this.getOutStatus(), this.getIncomingStatus(), this.getMeCountryOfOrigin(), this.getAuthAmount(), this.getIrfAmountUSD(), this.getOriginalRRN(), this.getAmexMerchantId(), this.getMerchantContactInfo(), this.getMerchantLocationId(), this.getLocationRegionCode(), this.getRefundIndicator(), this.getInvoiceNumber(), this.getTipAmount(), this.getDedicatedFileName(), this.getScheme(), this.getDccAmount(), this.getDccCurrency(), this.getDccIndicator(), this.getDccExchangeRate(), this.getMposAccDevType(), this.getAcceptanceTrlIndicator(), this.getAccepterUrlAddress());
    }
    
    public PosTransactionEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final Integer insCode, final Integer intCode, final Integer genStatus, final Integer jobNumber, final String rrn, final Character cardType, final Character cardDomIntlFlag, final String cardNumber, final Double txnAmount, final LocalDateTime localDateTime, final String responseCode, final String approvalCode, final String terminalId, final String merchantId, final String txnType, final String procCode, final String stan, final String authReason, final String meName, final String meCity, final String meCountry, final String mePinCode, final Character stagingFlag, final Character paymentFlag, final LocalDate bussDate, final String txnUniqueId, final String msgTypeId, final Integer agrSernumber, final Character cardInputAbility, final Character cardCaptureAbility, final Character chPresent, final Character cardPresent, final String oprtEnvironment, final Double cashBackAmount, final String txnCurCode, final Double txnFeeAmount, final Double setlAmount, final LocalDate setlDate, final String setlCurCode, final String posEntryMode, final String posConditionCode, final String motoEcomIndicator, final String cardSeqNumber, final String serviceCode, final String mcc, final String terminalType, final String network, final Double netAmount, final Character settlementIndicator, final LocalDate centreProcDate, final Character onusOffusFlag, final Character revIndiCator, final String cardCategory, final String cardSubCategory, final Character dmsSmsMode, final String networkData, final Character chAuthAbility, final Character chAuthEntity, final Character cardOutPutAbility, final Character trlOutPutAbility, final LocalDateTime txnDateTime, final String acqinstIdCode, final String acqInstConCode, final Character chAuthMethod, final String appCryptogram, final String cryptInfoData, final String issAppData, final String upblNumber, final String appTxnCounter, final String trlVerResult, final String chipTxnDate, final String chipTxnType, final Double cryptAmount, final String chipCurCode, final String appICProfile, final String trlConCode, final Double chipCashBack, final String cvmResult, final String trlCapabilities, final String chipTrlType, final String ifdSerNumber, final String tcc, final String trlAppVerNumber, final String issAuthData, final String txnId, final Character meCategoryType, final String cardAccepStreetAddress, final String cardAccepStateCode, final String expiryDate, final Integer irdSerNumber, final String ird, final Double irfFixed, final Double irfPercent, final Double irfAmount, final String remarks, final Character chIdMethod, final Character cardInputMode, final Character authCharecteresticId, final String validationCode, final String marketSpecAuthDataInd, final String productId, final Character spendQualificationInd, final Character reImbursementAttribute, final String feePgmIndicator, final Character accountFundingSource, final String banKCode, final String maid, final String chipTrlCapabilities, final String mvv, final String txnCode, final String adtlAmounts, final String encCardNumber, final String formFactorIndicator, final Double irfMaxAmount, final Double irfMinAmount, final String outStatus, final String incomingStatus, final String meCountryOfOrigin, final Double authAmount, final Double irfAmountUSD, final String originalRRN, final String amexMerchantId, final String merchantContactInfo, final String merchantLocationId, final String locationRegionCode, final Character refundIndicator, final String invoiceNumber, final Double tipAmount, final String dedicatedFileName, final String scheme, final Double dccAmount, final String dccCurrency, final Character dccIndicator, final Double dccExchangeRate, final Character mposAccDevType, final Character acceptanceTrlIndicator, final String accepterUrlAddress) {
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
        this.encCardNumber = encCardNumber;
        this.formFactorIndicator = formFactorIndicator;
        this.irfMaxAmount = irfMaxAmount;
        this.irfMinAmount = irfMinAmount;
        this.outStatus = outStatus;
        this.incomingStatus = incomingStatus;
        this.meCountryOfOrigin = meCountryOfOrigin;
        this.authAmount = authAmount;
        this.irfAmountUSD = irfAmountUSD;
        this.originalRRN = originalRRN;
        this.amexMerchantId = amexMerchantId;
        this.merchantContactInfo = merchantContactInfo;
        this.merchantLocationId = merchantLocationId;
        this.locationRegionCode = locationRegionCode;
        this.refundIndicator = refundIndicator;
        this.invoiceNumber = invoiceNumber;
        this.tipAmount = tipAmount;
        this.dedicatedFileName = dedicatedFileName;
        this.scheme = scheme;
        this.dccAmount = dccAmount;
        this.dccCurrency = dccCurrency;
        this.dccIndicator = dccIndicator;
        this.dccExchangeRate = dccExchangeRate;
        this.mposAccDevType = mposAccDevType;
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
        this.accepterUrlAddress = accepterUrlAddress;
    }
    
    public PosTransactionEntity() {
    }
    
    public static class PosTransactionEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private Integer insCode;
        private Integer intCode;
        private Integer genStatus;
        private Integer jobNumber;
        private String rrn;
        private Character cardType;
        private Character cardDomIntlFlag;
        private String cardNumber;
        private Double txnAmount;
        private LocalDateTime localDateTime;
        private String responseCode;
        private String approvalCode;
        private String terminalId;
        private String merchantId;
        private String txnType;
        private String procCode;
        private String stan;
        private String authReason;
        private String meName;
        private String meCity;
        private String meCountry;
        private String mePinCode;
        private Character stagingFlag;
        private Character paymentFlag;
        private LocalDate bussDate;
        private String txnUniqueId;
        private String msgTypeId;
        private Integer agrSernumber;
        private Character cardInputAbility;
        private Character cardCaptureAbility;
        private Character chPresent;
        private Character cardPresent;
        private String oprtEnvironment;
        private Double cashBackAmount;
        private String txnCurCode;
        private Double txnFeeAmount;
        private Double setlAmount;
        private LocalDate setlDate;
        private String setlCurCode;
        private String posEntryMode;
        private String posConditionCode;
        private String motoEcomIndicator;
        private String cardSeqNumber;
        private String serviceCode;
        private String mcc;
        private String terminalType;
        private String network;
        private Double netAmount;
        private Character settlementIndicator;
        private LocalDate centreProcDate;
        private Character onusOffusFlag;
        private Character revIndiCator;
        private String cardCategory;
        private String cardSubCategory;
        private Character dmsSmsMode;
        private String networkData;
        private Character chAuthAbility;
        private Character chAuthEntity;
        private Character cardOutPutAbility;
        private Character trlOutPutAbility;
        private LocalDateTime txnDateTime;
        private String acqinstIdCode;
        private String acqInstConCode;
        private Character chAuthMethod;
        private String appCryptogram;
        private String cryptInfoData;
        private String issAppData;
        private String upblNumber;
        private String appTxnCounter;
        private String trlVerResult;
        private String chipTxnDate;
        private String chipTxnType;
        private Double cryptAmount;
        private String chipCurCode;
        private String appICProfile;
        private String trlConCode;
        private Double chipCashBack;
        private String cvmResult;
        private String trlCapabilities;
        private String chipTrlType;
        private String ifdSerNumber;
        private String tcc;
        private String trlAppVerNumber;
        private String issAuthData;
        private String txnId;
        private Character meCategoryType;
        private String cardAccepStreetAddress;
        private String cardAccepStateCode;
        private String expiryDate;
        private Integer irdSerNumber;
        private String ird;
        private Double irfFixed;
        private Double irfPercent;
        private Double irfAmount;
        private String remarks;
        private Character chIdMethod;
        private Character cardInputMode;
        private Character authCharecteresticId;
        private String validationCode;
        private String marketSpecAuthDataInd;
        private String productId;
        private Character spendQualificationInd;
        private Character reImbursementAttribute;
        private String feePgmIndicator;
        private Character accountFundingSource;
        private String banKCode;
        private String maid;
        private String chipTrlCapabilities;
        private String mvv;
        private String txnCode;
        private String adtlAmounts;
        private String encCardNumber;
        private String formFactorIndicator;
        private Double irfMaxAmount;
        private Double irfMinAmount;
        private String outStatus;
        private String incomingStatus;
        private String meCountryOfOrigin;
        private Double authAmount;
        private Double irfAmountUSD;
        private String originalRRN;
        private String amexMerchantId;
        private String merchantContactInfo;
        private String merchantLocationId;
        private String locationRegionCode;
        private Character refundIndicator;
        private String invoiceNumber;
        private Double tipAmount;
        private String dedicatedFileName;
        private String scheme;
        private Double dccAmount;
        private String dccCurrency;
        private Character dccIndicator;
        private Double dccExchangeRate;
        private Character mposAccDevType;
        private Character acceptanceTrlIndicator;
        private String accepterUrlAddress;
        
        PosTransactionEntityBuilder() {
        }
        
        public PosTransactionEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public PosTransactionEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public PosTransactionEntityBuilder insCode(final Integer insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public PosTransactionEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public PosTransactionEntityBuilder genStatus(final Integer genStatus) {
            this.genStatus = genStatus;
            return this;
        }
        
        public PosTransactionEntityBuilder jobNumber(final Integer jobNumber) {
            this.jobNumber = jobNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public PosTransactionEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public PosTransactionEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public PosTransactionEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public PosTransactionEntityBuilder responseCode(final String responseCode) {
            this.responseCode = responseCode;
            return this;
        }
        
        public PosTransactionEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public PosTransactionEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public PosTransactionEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public PosTransactionEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public PosTransactionEntityBuilder procCode(final String procCode) {
            this.procCode = procCode;
            return this;
        }
        
        public PosTransactionEntityBuilder stan(final String stan) {
            this.stan = stan;
            return this;
        }
        
        public PosTransactionEntityBuilder authReason(final String authReason) {
            this.authReason = authReason;
            return this;
        }
        
        public PosTransactionEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public PosTransactionEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public PosTransactionEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public PosTransactionEntityBuilder mePinCode(final String mePinCode) {
            this.mePinCode = mePinCode;
            return this;
        }
        
        public PosTransactionEntityBuilder stagingFlag(final Character stagingFlag) {
            this.stagingFlag = stagingFlag;
            return this;
        }
        
        public PosTransactionEntityBuilder paymentFlag(final Character paymentFlag) {
            this.paymentFlag = paymentFlag;
            return this;
        }
        
        public PosTransactionEntityBuilder bussDate(final LocalDate bussDate) {
            this.bussDate = bussDate;
            return this;
        }
        
        public PosTransactionEntityBuilder txnUniqueId(final String txnUniqueId) {
            this.txnUniqueId = txnUniqueId;
            return this;
        }
        
        public PosTransactionEntityBuilder msgTypeId(final String msgTypeId) {
            this.msgTypeId = msgTypeId;
            return this;
        }
        
        public PosTransactionEntityBuilder agrSernumber(final Integer agrSernumber) {
            this.agrSernumber = agrSernumber;
            return this;
        }
        
        public PosTransactionEntityBuilder cardInputAbility(final Character cardInputAbility) {
            this.cardInputAbility = cardInputAbility;
            return this;
        }
        
        public PosTransactionEntityBuilder cardCaptureAbility(final Character cardCaptureAbility) {
            this.cardCaptureAbility = cardCaptureAbility;
            return this;
        }
        
        public PosTransactionEntityBuilder chPresent(final Character chPresent) {
            this.chPresent = chPresent;
            return this;
        }
        
        public PosTransactionEntityBuilder cardPresent(final Character cardPresent) {
            this.cardPresent = cardPresent;
            return this;
        }
        
        public PosTransactionEntityBuilder oprtEnvironment(final String oprtEnvironment) {
            this.oprtEnvironment = oprtEnvironment;
            return this;
        }
        
        public PosTransactionEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public PosTransactionEntityBuilder txnFeeAmount(final Double txnFeeAmount) {
            this.txnFeeAmount = txnFeeAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder setlAmount(final Double setlAmount) {
            this.setlAmount = setlAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder setlDate(final LocalDate setlDate) {
            this.setlDate = setlDate;
            return this;
        }
        
        public PosTransactionEntityBuilder setlCurCode(final String setlCurCode) {
            this.setlCurCode = setlCurCode;
            return this;
        }
        
        public PosTransactionEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public PosTransactionEntityBuilder posConditionCode(final String posConditionCode) {
            this.posConditionCode = posConditionCode;
            return this;
        }
        
        public PosTransactionEntityBuilder motoEcomIndicator(final String motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder cardSeqNumber(final String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder serviceCode(final String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public PosTransactionEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public PosTransactionEntityBuilder terminalType(final String terminalType) {
            this.terminalType = terminalType;
            return this;
        }
        
        public PosTransactionEntityBuilder network(final String network) {
            this.network = network;
            return this;
        }
        
        public PosTransactionEntityBuilder netAmount(final Double netAmount) {
            this.netAmount = netAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder settlementIndicator(final Character settlementIndicator) {
            this.settlementIndicator = settlementIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder centreProcDate(final LocalDate centreProcDate) {
            this.centreProcDate = centreProcDate;
            return this;
        }
        
        public PosTransactionEntityBuilder onusOffusFlag(final Character onusOffusFlag) {
            this.onusOffusFlag = onusOffusFlag;
            return this;
        }
        
        public PosTransactionEntityBuilder revIndiCator(final Character revIndiCator) {
            this.revIndiCator = revIndiCator;
            return this;
        }
        
        public PosTransactionEntityBuilder cardCategory(final String cardCategory) {
            this.cardCategory = cardCategory;
            return this;
        }
        
        public PosTransactionEntityBuilder cardSubCategory(final String cardSubCategory) {
            this.cardSubCategory = cardSubCategory;
            return this;
        }
        
        public PosTransactionEntityBuilder dmsSmsMode(final Character dmsSmsMode) {
            this.dmsSmsMode = dmsSmsMode;
            return this;
        }
        
        public PosTransactionEntityBuilder networkData(final String networkData) {
            this.networkData = networkData;
            return this;
        }
        
        public PosTransactionEntityBuilder chAuthAbility(final Character chAuthAbility) {
            this.chAuthAbility = chAuthAbility;
            return this;
        }
        
        public PosTransactionEntityBuilder chAuthEntity(final Character chAuthEntity) {
            this.chAuthEntity = chAuthEntity;
            return this;
        }
        
        public PosTransactionEntityBuilder cardOutPutAbility(final Character cardOutPutAbility) {
            this.cardOutPutAbility = cardOutPutAbility;
            return this;
        }
        
        public PosTransactionEntityBuilder trlOutPutAbility(final Character trlOutPutAbility) {
            this.trlOutPutAbility = trlOutPutAbility;
            return this;
        }
        
        public PosTransactionEntityBuilder txnDateTime(final LocalDateTime txnDateTime) {
            this.txnDateTime = txnDateTime;
            return this;
        }
        
        public PosTransactionEntityBuilder acqinstIdCode(final String acqinstIdCode) {
            this.acqinstIdCode = acqinstIdCode;
            return this;
        }
        
        public PosTransactionEntityBuilder acqInstConCode(final String acqInstConCode) {
            this.acqInstConCode = acqInstConCode;
            return this;
        }
        
        public PosTransactionEntityBuilder chAuthMethod(final Character chAuthMethod) {
            this.chAuthMethod = chAuthMethod;
            return this;
        }
        
        public PosTransactionEntityBuilder appCryptogram(final String appCryptogram) {
            this.appCryptogram = appCryptogram;
            return this;
        }
        
        public PosTransactionEntityBuilder cryptInfoData(final String cryptInfoData) {
            this.cryptInfoData = cryptInfoData;
            return this;
        }
        
        public PosTransactionEntityBuilder issAppData(final String issAppData) {
            this.issAppData = issAppData;
            return this;
        }
        
        public PosTransactionEntityBuilder upblNumber(final String upblNumber) {
            this.upblNumber = upblNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder appTxnCounter(final String appTxnCounter) {
            this.appTxnCounter = appTxnCounter;
            return this;
        }
        
        public PosTransactionEntityBuilder trlVerResult(final String trlVerResult) {
            this.trlVerResult = trlVerResult;
            return this;
        }
        
        public PosTransactionEntityBuilder chipTxnDate(final String chipTxnDate) {
            this.chipTxnDate = chipTxnDate;
            return this;
        }
        
        public PosTransactionEntityBuilder chipTxnType(final String chipTxnType) {
            this.chipTxnType = chipTxnType;
            return this;
        }
        
        public PosTransactionEntityBuilder cryptAmount(final Double cryptAmount) {
            this.cryptAmount = cryptAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder chipCurCode(final String chipCurCode) {
            this.chipCurCode = chipCurCode;
            return this;
        }
        
        public PosTransactionEntityBuilder appICProfile(final String appICProfile) {
            this.appICProfile = appICProfile;
            return this;
        }
        
        public PosTransactionEntityBuilder trlConCode(final String trlConCode) {
            this.trlConCode = trlConCode;
            return this;
        }
        
        public PosTransactionEntityBuilder chipCashBack(final Double chipCashBack) {
            this.chipCashBack = chipCashBack;
            return this;
        }
        
        public PosTransactionEntityBuilder cvmResult(final String cvmResult) {
            this.cvmResult = cvmResult;
            return this;
        }
        
        public PosTransactionEntityBuilder trlCapabilities(final String trlCapabilities) {
            this.trlCapabilities = trlCapabilities;
            return this;
        }
        
        public PosTransactionEntityBuilder chipTrlType(final String chipTrlType) {
            this.chipTrlType = chipTrlType;
            return this;
        }
        
        public PosTransactionEntityBuilder ifdSerNumber(final String ifdSerNumber) {
            this.ifdSerNumber = ifdSerNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder tcc(final String tcc) {
            this.tcc = tcc;
            return this;
        }
        
        public PosTransactionEntityBuilder trlAppVerNumber(final String trlAppVerNumber) {
            this.trlAppVerNumber = trlAppVerNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder issAuthData(final String issAuthData) {
            this.issAuthData = issAuthData;
            return this;
        }
        
        public PosTransactionEntityBuilder txnId(final String txnId) {
            this.txnId = txnId;
            return this;
        }
        
        public PosTransactionEntityBuilder meCategoryType(final Character meCategoryType) {
            this.meCategoryType = meCategoryType;
            return this;
        }
        
        public PosTransactionEntityBuilder cardAccepStreetAddress(final String cardAccepStreetAddress) {
            this.cardAccepStreetAddress = cardAccepStreetAddress;
            return this;
        }
        
        public PosTransactionEntityBuilder cardAccepStateCode(final String cardAccepStateCode) {
            this.cardAccepStateCode = cardAccepStateCode;
            return this;
        }
        
        public PosTransactionEntityBuilder expiryDate(final String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
        
        public PosTransactionEntityBuilder irdSerNumber(final Integer irdSerNumber) {
            this.irdSerNumber = irdSerNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder ird(final String ird) {
            this.ird = ird;
            return this;
        }
        
        public PosTransactionEntityBuilder irfFixed(final Double irfFixed) {
            this.irfFixed = irfFixed;
            return this;
        }
        
        public PosTransactionEntityBuilder irfPercent(final Double irfPercent) {
            this.irfPercent = irfPercent;
            return this;
        }
        
        public PosTransactionEntityBuilder irfAmount(final Double irfAmount) {
            this.irfAmount = irfAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder remarks(final String remarks) {
            this.remarks = remarks;
            return this;
        }
        
        public PosTransactionEntityBuilder chIdMethod(final Character chIdMethod) {
            this.chIdMethod = chIdMethod;
            return this;
        }
        
        public PosTransactionEntityBuilder cardInputMode(final Character cardInputMode) {
            this.cardInputMode = cardInputMode;
            return this;
        }
        
        public PosTransactionEntityBuilder authCharecteresticId(final Character authCharecteresticId) {
            this.authCharecteresticId = authCharecteresticId;
            return this;
        }
        
        public PosTransactionEntityBuilder validationCode(final String validationCode) {
            this.validationCode = validationCode;
            return this;
        }
        
        public PosTransactionEntityBuilder marketSpecAuthDataInd(final String marketSpecAuthDataInd) {
            this.marketSpecAuthDataInd = marketSpecAuthDataInd;
            return this;
        }
        
        public PosTransactionEntityBuilder productId(final String productId) {
            this.productId = productId;
            return this;
        }
        
        public PosTransactionEntityBuilder spendQualificationInd(final Character spendQualificationInd) {
            this.spendQualificationInd = spendQualificationInd;
            return this;
        }
        
        public PosTransactionEntityBuilder reImbursementAttribute(final Character reImbursementAttribute) {
            this.reImbursementAttribute = reImbursementAttribute;
            return this;
        }
        
        public PosTransactionEntityBuilder feePgmIndicator(final String feePgmIndicator) {
            this.feePgmIndicator = feePgmIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder accountFundingSource(final Character accountFundingSource) {
            this.accountFundingSource = accountFundingSource;
            return this;
        }
        
        public PosTransactionEntityBuilder banKCode(final String banKCode) {
            this.banKCode = banKCode;
            return this;
        }
        
        public PosTransactionEntityBuilder maid(final String maid) {
            this.maid = maid;
            return this;
        }
        
        public PosTransactionEntityBuilder chipTrlCapabilities(final String chipTrlCapabilities) {
            this.chipTrlCapabilities = chipTrlCapabilities;
            return this;
        }
        
        public PosTransactionEntityBuilder mvv(final String mvv) {
            this.mvv = mvv;
            return this;
        }
        
        public PosTransactionEntityBuilder txnCode(final String txnCode) {
            this.txnCode = txnCode;
            return this;
        }
        
        public PosTransactionEntityBuilder adtlAmounts(final String adtlAmounts) {
            this.adtlAmounts = adtlAmounts;
            return this;
        }
        
        public PosTransactionEntityBuilder encCardNumber(final String encCardNumber) {
            this.encCardNumber = encCardNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder formFactorIndicator(final String formFactorIndicator) {
            this.formFactorIndicator = formFactorIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder irfMaxAmount(final Double irfMaxAmount) {
            this.irfMaxAmount = irfMaxAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder irfMinAmount(final Double irfMinAmount) {
            this.irfMinAmount = irfMinAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder outStatus(final String outStatus) {
            this.outStatus = outStatus;
            return this;
        }
        
        public PosTransactionEntityBuilder incomingStatus(final String incomingStatus) {
            this.incomingStatus = incomingStatus;
            return this;
        }
        
        public PosTransactionEntityBuilder meCountryOfOrigin(final String meCountryOfOrigin) {
            this.meCountryOfOrigin = meCountryOfOrigin;
            return this;
        }
        
        public PosTransactionEntityBuilder authAmount(final Double authAmount) {
            this.authAmount = authAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder irfAmountUSD(final Double irfAmountUSD) {
            this.irfAmountUSD = irfAmountUSD;
            return this;
        }
        
        public PosTransactionEntityBuilder originalRRN(final String originalRRN) {
            this.originalRRN = originalRRN;
            return this;
        }
        
        public PosTransactionEntityBuilder amexMerchantId(final String amexMerchantId) {
            this.amexMerchantId = amexMerchantId;
            return this;
        }
        
        public PosTransactionEntityBuilder merchantContactInfo(final String merchantContactInfo) {
            this.merchantContactInfo = merchantContactInfo;
            return this;
        }
        
        public PosTransactionEntityBuilder merchantLocationId(final String merchantLocationId) {
            this.merchantLocationId = merchantLocationId;
            return this;
        }
        
        public PosTransactionEntityBuilder locationRegionCode(final String locationRegionCode) {
            this.locationRegionCode = locationRegionCode;
            return this;
        }
        
        public PosTransactionEntityBuilder refundIndicator(final Character refundIndicator) {
            this.refundIndicator = refundIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder invoiceNumber(final String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }
        
        public PosTransactionEntityBuilder tipAmount(final Double tipAmount) {
            this.tipAmount = tipAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder dedicatedFileName(final String dedicatedFileName) {
            this.dedicatedFileName = dedicatedFileName;
            return this;
        }
        
        public PosTransactionEntityBuilder scheme(final String scheme) {
            this.scheme = scheme;
            return this;
        }
        
        public PosTransactionEntityBuilder dccAmount(final Double dccAmount) {
            this.dccAmount = dccAmount;
            return this;
        }
        
        public PosTransactionEntityBuilder dccCurrency(final String dccCurrency) {
            this.dccCurrency = dccCurrency;
            return this;
        }
        
        public PosTransactionEntityBuilder dccIndicator(final Character dccIndicator) {
            this.dccIndicator = dccIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder dccExchangeRate(final Double dccExchangeRate) {
            this.dccExchangeRate = dccExchangeRate;
            return this;
        }
        
        public PosTransactionEntityBuilder mposAccDevType(final Character mposAccDevType) {
            this.mposAccDevType = mposAccDevType;
            return this;
        }
        
        public PosTransactionEntityBuilder acceptanceTrlIndicator(final Character acceptanceTrlIndicator) {
            this.acceptanceTrlIndicator = acceptanceTrlIndicator;
            return this;
        }
        
        public PosTransactionEntityBuilder accepterUrlAddress(final String accepterUrlAddress) {
            this.accepterUrlAddress = accepterUrlAddress;
            return this;
        }
        
        public PosTransactionEntity build() {
            return new PosTransactionEntity(this.serialNumber, this.lastUpdated, this.updatedUser, this.insCode, this.intCode, this.genStatus, this.jobNumber, this.rrn, this.cardType, this.cardDomIntlFlag, this.cardNumber, this.txnAmount, this.localDateTime, this.responseCode, this.approvalCode, this.terminalId, this.merchantId, this.txnType, this.procCode, this.stan, this.authReason, this.meName, this.meCity, this.meCountry, this.mePinCode, this.stagingFlag, this.paymentFlag, this.bussDate, this.txnUniqueId, this.msgTypeId, this.agrSernumber, this.cardInputAbility, this.cardCaptureAbility, this.chPresent, this.cardPresent, this.oprtEnvironment, this.cashBackAmount, this.txnCurCode, this.txnFeeAmount, this.setlAmount, this.setlDate, this.setlCurCode, this.posEntryMode, this.posConditionCode, this.motoEcomIndicator, this.cardSeqNumber, this.serviceCode, this.mcc, this.terminalType, this.network, this.netAmount, this.settlementIndicator, this.centreProcDate, this.onusOffusFlag, this.revIndiCator, this.cardCategory, this.cardSubCategory, this.dmsSmsMode, this.networkData, this.chAuthAbility, this.chAuthEntity, this.cardOutPutAbility, this.trlOutPutAbility, this.txnDateTime, this.acqinstIdCode, this.acqInstConCode, this.chAuthMethod, this.appCryptogram, this.cryptInfoData, this.issAppData, this.upblNumber, this.appTxnCounter, this.trlVerResult, this.chipTxnDate, this.chipTxnType, this.cryptAmount, this.chipCurCode, this.appICProfile, this.trlConCode, this.chipCashBack, this.cvmResult, this.trlCapabilities, this.chipTrlType, this.ifdSerNumber, this.tcc, this.trlAppVerNumber, this.issAuthData, this.txnId, this.meCategoryType, this.cardAccepStreetAddress, this.cardAccepStateCode, this.expiryDate, this.irdSerNumber, this.ird, this.irfFixed, this.irfPercent, this.irfAmount, this.remarks, this.chIdMethod, this.cardInputMode, this.authCharecteresticId, this.validationCode, this.marketSpecAuthDataInd, this.productId, this.spendQualificationInd, this.reImbursementAttribute, this.feePgmIndicator, this.accountFundingSource, this.banKCode, this.maid, this.chipTrlCapabilities, this.mvv, this.txnCode, this.adtlAmounts, this.encCardNumber, this.formFactorIndicator, this.irfMaxAmount, this.irfMinAmount, this.outStatus, this.incomingStatus, this.meCountryOfOrigin, this.authAmount, this.irfAmountUSD, this.originalRRN, this.amexMerchantId, this.merchantContactInfo, this.merchantLocationId, this.locationRegionCode, this.refundIndicator, this.invoiceNumber, this.tipAmount, this.dedicatedFileName, this.scheme, this.dccAmount, this.dccCurrency, this.dccIndicator, this.dccExchangeRate, this.mposAccDevType, this.acceptanceTrlIndicator, this.accepterUrlAddress);
        }
        
        @Override
        public String toString() {
            return "" + ("PosTransactionEntity.PosTransactionEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", insCode=" + this.insCode + ", intCode=" + this.intCode + ", genStatus=" + this.genStatus + ", jobNumber=" + this.jobNumber + ", rrn=" + this.rrn + ", cardType=" + this.cardType + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", cardNumber=" + this.cardNumber + ", txnAmount=" + this.txnAmount + ", localDateTime=" + String.valueOf(this.localDateTime) + ", responseCode=" + this.responseCode + ", approvalCode=" + this.approvalCode + ", terminalId=" + this.terminalId + ", merchantId=" + this.merchantId + ", txnType=" + this.txnType + ", procCode=" + this.procCode + ", stan=" + this.stan + ", authReason=" + this.authReason + ", meName=" + this.meName + ", meCity=" + this.meCity + ", meCountry=" + this.meCountry + ", mePinCode=" + this.mePinCode + ", stagingFlag=" + this.stagingFlag + ", paymentFlag=" + this.paymentFlag + ", bussDate=" + String.valueOf(this.bussDate) + ", txnUniqueId=" + this.txnUniqueId + ", msgTypeId=" + this.msgTypeId + ", agrSernumber=" + this.agrSernumber + ", cardInputAbility=" + this.cardInputAbility + ", cardCaptureAbility=" + this.cardCaptureAbility + ", chPresent=" + this.chPresent + ", cardPresent=" + this.cardPresent + ", oprtEnvironment=" + this.oprtEnvironment + ", cashBackAmount=" + this.cashBackAmount + ", txnCurCode=" + this.txnCurCode + ", txnFeeAmount=" + this.txnFeeAmount + ", setlAmount=" + this.setlAmount + ", setlDate=" + String.valueOf(this.setlDate) + ", setlCurCode=" + this.setlCurCode + ", posEntryMode=" + this.posEntryMode + ", posConditionCode=" + this.posConditionCode + ", motoEcomIndicator=" + this.motoEcomIndicator + ", cardSeqNumber=" + this.cardSeqNumber + ", serviceCode=" + this.serviceCode + ", mcc=" + this.mcc + ", terminalType=" + this.terminalType + ", network=" + this.network + ", netAmount=" + this.netAmount + ", settlementIndicator=" + this.settlementIndicator + ", centreProcDate=" + String.valueOf(this.centreProcDate) + ", onusOffusFlag=" + this.onusOffusFlag + ", revIndiCator=" + this.revIndiCator + ", cardCategory=" + this.cardCategory + ", cardSubCategory=" + this.cardSubCategory + ", dmsSmsMode=" + this.dmsSmsMode + ", networkData=" + this.networkData + ", chAuthAbility=" + this.chAuthAbility + ", chAuthEntity=" + this.chAuthEntity + ", cardOutPutAbility=" + this.cardOutPutAbility + ", trlOutPutAbility=" + this.trlOutPutAbility + ", txnDateTime=" + String.valueOf(this.txnDateTime) + ", acqinstIdCode=" + this.acqinstIdCode + ", acqInstConCode=" + this.acqInstConCode + ", chAuthMethod=" + this.chAuthMethod + ", appCryptogram=" + this.appCryptogram + ", cryptInfoData=" + this.cryptInfoData + ", issAppData=" + this.issAppData + ", upblNumber=" + this.upblNumber + ", appTxnCounter=" + this.appTxnCounter + ", trlVerResult=" + this.trlVerResult + ", chipTxnDate=" + this.chipTxnDate + ", chipTxnType=" + this.chipTxnType + ", cryptAmount=" + this.cryptAmount + ", chipCurCode=" + this.chipCurCode + ", appICProfile=" + this.appICProfile + ", trlConCode=" + this.trlConCode + ", chipCashBack=" + this.chipCashBack + ", cvmResult=" + this.cvmResult + ", trlCapabilities=" + this.trlCapabilities + ", chipTrlType=" + this.chipTrlType + ", ifdSerNumber=" + this.ifdSerNumber + ", tcc=" + this.tcc + ", trlAppVerNumber=" + this.trlAppVerNumber + ", issAuthData=" + this.issAuthData + ", txnId=" + this.txnId + ", meCategoryType=" + this.meCategoryType + ", cardAccepStreetAddress=" + this.cardAccepStreetAddress + ", cardAccepStateCode=" + this.cardAccepStateCode + ", expiryDate=" + this.expiryDate + ", irdSerNumber=" + this.irdSerNumber + ", ird=" + this.ird + ", irfFixed=" + this.irfFixed + ", irfPercent=" + this.irfPercent + ", irfAmount=" + this.irfAmount + ", remarks=" + this.remarks + ", chIdMethod=" + this.chIdMethod + ", cardInputMode=") + /* invokedynamic(!) */ProcyonInvokeDynamicHelper_2.invoke(this.cardInputMode, this.authCharecteresticId, this.validationCode, this.marketSpecAuthDataInd, this.productId, this.spendQualificationInd, this.reImbursementAttribute, this.feePgmIndicator, this.accountFundingSource, this.banKCode, this.maid, this.chipTrlCapabilities, this.mvv, this.txnCode, this.adtlAmounts, this.encCardNumber, this.formFactorIndicator, this.irfMaxAmount, this.irfMinAmount, this.outStatus, this.incomingStatus, this.meCountryOfOrigin, this.authAmount, this.irfAmountUSD, this.originalRRN, this.amexMerchantId, this.merchantContactInfo, this.merchantLocationId, this.locationRegionCode, this.refundIndicator, this.invoiceNumber, this.tipAmount, this.dedicatedFileName, this.scheme, this.dccAmount, this.dccCurrency, this.dccIndicator, this.dccExchangeRate, this.mposAccDevType, this.acceptanceTrlIndicator, this.accepterUrlAddress);
        }
        
        // This helper class was generated by Procyon to approximate the behavior of an
        // 'invokedynamic' instruction that it doesn't know how to interpret.
        private static final class ProcyonInvokeDynamicHelper_2
        {
            private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
            private static MethodHandle handle;
            private static volatile int fence;
            
            private static MethodHandle handle() {
                final MethodHandle handle = ProcyonInvokeDynamicHelper_2.handle;
                if (handle != null)
                    return handle;
                return ProcyonInvokeDynamicHelper_2.ensureHandle();
            }
            
            private static MethodHandle ensureHandle() {
                ProcyonInvokeDynamicHelper_2.fence = 0;
                MethodHandle handle = ProcyonInvokeDynamicHelper_2.handle;
                if (handle == null) {
                    MethodHandles.Lookup lookup = ProcyonInvokeDynamicHelper_2.LOOKUP;
                    try {
                        handle = ((CallSite)StringConcatFactory.makeConcatWithConstants(lookup, "makeConcatWithConstants", MethodType.methodType(String.class, Character.class, Character.class, String.class, String.class, String.class, Character.class, Character.class, String.class, Character.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, String.class, String.class, String.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class, Character.class, String.class, Double.class, String.class, String.class, Double.class, String.class, Character.class, Double.class, Character.class, Character.class, String.class), "\u0001, authCharecteresticId=\u0001, validationCode=\u0001, marketSpecAuthDataInd=\u0001, productId=\u0001, spendQualificationInd=\u0001, reImbursementAttribute=\u0001, feePgmIndicator=\u0001, accountFundingSource=\u0001, banKCode=\u0001, maid=\u0001, chipTrlCapabilities=\u0001, mvv=\u0001, txnCode=\u0001, adtlAmounts=\u0001, encCardNumber=\u0001, formFactorIndicator=\u0001, irfMaxAmount=\u0001, irfMinAmount=\u0001, outStatus=\u0001, incomingStatus=\u0001, meCountryOfOrigin=\u0001, authAmount=\u0001, irfAmountUSD=\u0001, originalRRN=\u0001, amexMerchantId=\u0001, merchantContactInfo=\u0001, merchantLocationId=\u0001, locationRegionCode=\u0001, refundIndicator=\u0001, invoiceNumber=\u0001, tipAmount=\u0001, dedicatedFileName=\u0001, scheme=\u0001, dccAmount=\u0001, dccCurrency=\u0001, dccIndicator=\u0001, dccExchangeRate=\u0001, mposAccDevType=\u0001, acceptanceTrlIndicator=\u0001, accepterUrlAddress=\u0001)")).dynamicInvoker();
                    }
                    catch (Throwable t) {
                        throw new UndeclaredThrowableException(t);
                    }
                    ProcyonInvokeDynamicHelper_2.fence = 1;
                    ProcyonInvokeDynamicHelper_2.handle = handle;
                    ProcyonInvokeDynamicHelper_2.fence = 0;
                }
                return handle;
            }
            
            private static String invoke(Character p0, Character p1, String p2, String p3, String p4, Character p5, Character p6, String p7, Character p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, String p16, Double p17, Double p18, String p19, String p20, String p21, Double p22, Double p23, String p24, String p25, String p26, String p27, String p28, Character p29, String p30, Double p31, String p32, String p33, Double p34, String p35, Character p36, Double p37, Character p38, Character p39, String p40) {
                try {
                    return ProcyonInvokeDynamicHelper_2.handle().invokeExact(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40);
                }
                catch (Throwable t) {
                    throw new UndeclaredThrowableException(t);
                }
            }
        }
    }
    
    // This helper class was generated by Procyon to approximate the behavior of an
    // 'invokedynamic' instruction that it doesn't know how to interpret.
    private static final class ProcyonInvokeDynamicHelper_1
    {
        private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
        private static MethodHandle handle;
        private static volatile int fence;
        
        private static MethodHandle handle() {
            final MethodHandle handle = ProcyonInvokeDynamicHelper_1.handle;
            if (handle != null)
                return handle;
            return ProcyonInvokeDynamicHelper_1.ensureHandle();
        }
        
        private static MethodHandle ensureHandle() {
            ProcyonInvokeDynamicHelper_1.fence = 0;
            MethodHandle handle = ProcyonInvokeDynamicHelper_1.handle;
            if (handle == null) {
                MethodHandles.Lookup lookup = ProcyonInvokeDynamicHelper_1.LOOKUP;
                try {
                    handle = ((CallSite)StringConcatFactory.makeConcatWithConstants(lookup, "makeConcatWithConstants", MethodType.methodType(String.class, Character.class, Character.class, String.class, String.class, String.class, Character.class, Character.class, String.class, Character.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, String.class, String.class, String.class, Double.class, Double.class, String.class, String.class, String.class, String.class, String.class, Character.class, String.class, Double.class, String.class, String.class, Double.class, String.class, Character.class, Double.class, Character.class, Character.class, String.class), "\u0001, authCharecteresticId=\u0001, validationCode=\u0001, marketSpecAuthDataInd=\u0001, productId=\u0001, spendQualificationInd=\u0001, reImbursementAttribute=\u0001, feePgmIndicator=\u0001, accountFundingSource=\u0001, banKCode=\u0001, maid=\u0001, chipTrlCapabilities=\u0001, mvv=\u0001, txnCode=\u0001, adtlAmounts=\u0001, encCardNumber=\u0001, formFactorIndicator=\u0001, irfMaxAmount=\u0001, irfMinAmount=\u0001, outStatus=\u0001, incomingStatus=\u0001, meCountryOfOrigin=\u0001, authAmount=\u0001, irfAmountUSD=\u0001, originalRRN=\u0001, amexMerchantId=\u0001, merchantContactInfo=\u0001, merchantLocationId=\u0001, locationRegionCode=\u0001, refundIndicator=\u0001, invoiceNumber=\u0001, tipAmount=\u0001, dedicatedFileName=\u0001, scheme=\u0001, dccAmount=\u0001, dccCurrency=\u0001, dccIndicator=\u0001, dccExchangeRate=\u0001, mposAccDevType=\u0001, acceptanceTrlIndicator=\u0001, accepterUrlAddress=\u0001)")).dynamicInvoker();
                }
                catch (Throwable t) {
                    throw new UndeclaredThrowableException(t);
                }
                ProcyonInvokeDynamicHelper_1.fence = 1;
                ProcyonInvokeDynamicHelper_1.handle = handle;
                ProcyonInvokeDynamicHelper_1.fence = 0;
            }
            return handle;
        }
        
        private static String invoke(Character p0, Character p1, String p2, String p3, String p4, Character p5, Character p6, String p7, Character p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, String p16, Double p17, Double p18, String p19, String p20, String p21, Double p22, Double p23, String p24, String p25, String p26, String p27, String p28, Character p29, String p30, Double p31, String p32, String p33, Double p34, String p35, Character p36, Double p37, Character p38, Character p39, String p40) {
            try {
                return ProcyonInvokeDynamicHelper_1.handle().invokeExact(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40);
            }
            catch (Throwable t) {
                throw new UndeclaredThrowableException(t);
            }
        }
    }
}
