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
@Table(name = "POS_TRANSACTION_WORK")
public class PosTxnWorkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PTR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "PTR_PID")
    private Integer pid;
    @Column(name = "PTR_INS_CODE")
    private Integer insCode;
    @Column(name = "PTR_LAST_UPDATED")
    private LocalDateTime lastUpdatedDate;
    @Column(name = "PTR_UPDATED_USER")
    private int user;
    @Column(name = "PTR_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "PTR_INT_CODE")
    private Integer intCode;
    @Column(name = "PTR_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name = "PTR_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "PTR_STAGING_FLAG")
    private Character stagingFlag;
    @Column(name = "PTR_PAYMENT_FLAG")
    private Character paymentFlag;
    @Column(name = "PTR_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name = "PTR_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name = "PTR_TXN_UNIQUE_ID")
    private String txnUniqueId;
    @Column(name = "PTR_MSG_TYPE_ID")
    private String msgTypeId;
    @Column(name = "PTR_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "PTR_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "PTR_RESP_CODE")
    private String responseCode;
    @Column(name = "PTR_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "PTR_TERMINAL_ID")
    private String terminalId;
    @Column(name = "PTR_MERCHANT_ID")
    private String merchantId;
    @Column(name = "PTR_MGR_SER_NUMBER")
    private Integer groupSerNumber;
    @Column(name = "PTR_SME_SER_NUMBER")
    private Integer smeSerNumber;
    @Column(name = "PTR_AGR_SER_NUMBER")
    private Integer agrSernumber;
    @Column(name = "PTR_TXN_TYPE")
    private String txnType;
    @Column(name = "PTR_PROC_CODE")
    private String procCode;
    @Column(name = "PTR_CONV_RATE")
    private Double convRate;
    @Column(name = "PTR_STAN")
    private String stan;
    @Column(name = "PTR_AUTH_TYPE")
    private Integer authType;
    @Column(name = "PTR_AUTH_REASON")
    private String authReason;
    @Column(name = "PTR_TXN_ID")
    private String txnId;
    @Column(name = "PTR_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name = "PTR_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name = "PTR_ACQ_INST_CON_CODE")
    private String acqInstConCode;
    @Column(name = "PTR_FWD_INST_ID_CODE")
    private String fwdInstIdCode;
    @Column(name = "PTR_REC_INST_CON_CODE")
    private String recInstConCode;
    @Column(name = "PTR_ADTL_PRIVATE_DATA")
    private String adtlPrivateData;
    @Column(name = "PTR_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name = "PTR_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "PTR_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "PTR_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name = "PTR_TXN_FEE_CUR_CODE")
    private String txnFeeCurCode;
    @Column(name = "PTR_SETL_AMOUNT")
    private Double setlAmount;
    @Column(name = "PTR_TIP_AMOUNT")
    private Double tipAmount;
    @Column(name = "PTR_SETL_CUR_CODE")
    private String setlCurCode;
    @Column(name = "PTR_ADTL_AMOUNTS")
    private String adtlAmounts;
    @Column(name = "PTR_AUTH_AMOUNT")
    private Double authAmount;
    @Column(name = "PTR_REPLACE_AMOUNT")
    private Double replaceAmount;
    @Column(name = "PTR_ADVICE_REASON_CODE")
    private String adviceReasonCode;
    @Column(name = "PTR_INF_DATA")
    private String infData;
    @Column(name = "PTR_NETWORK_DATA")
    private String networkData;
    @Column(name = "PTR_APP_IDENTIFIER")
    private String appIdentifier;
    @Column(name = "PTR_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name = "PTR_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name = "PTR_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name = "PTR_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name = "PTR_CVM_RESULTS")
    private String cvmResult;
    @Column(name = "PTR_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name = "PTR_ISS_APP_DATA")
    private String issAppData;
    @Column(name = "PTR_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name = "PTR_TRL_CAPABILITIES")
    private String trlCapabilities;
    @Column(name = "PTR_TRL_CON_CODE")
    private String trlConCode;
    @Column(name = "PTR_TRL_TYPE")
    private String terminalType;
    @Column(name = "PTR_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name = "PTR_TCC")
    private String tcc;
    @Column(name = "PTR_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name = "PTR_CHIP_TXN_DATE")
    private LocalDate chiptxnDate;
    @Column(name = "PTR_CHIP_TXN_TYPE")
    private String chipTxnDate;
    @Column(name = "PTR_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name = "PTR_CHIP_CASHBACK")
    private Double chipCashBack;
    @Column(name = "PTR_UPBL_NUMBER")
    private String upblNumber;
    @Column(name = "PTR_CARD_AUTH_CODE")
    private Character cardAuthCode;
    @Column(name = "PTR_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name = "PTR_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name = "PTR_POS_CONDITION_CODE")
    private String posConditionCode;
    @Column(name = "PTR_MOTO_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name = "PTR_CARD_VER_RESULT")
    private String cardVerResult;
    @Column(name = "PTR_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name = "PTR_EXPIRY_DATE")
    private String expiryDate;
    @Column(name = "PTR_SERVICE_CODE")
    private String serviceCode;
    @Column(name = "PTR_MCC")
    private String mcc;
    @Column(name = "PTR_ME_NAME")
    private String meName;
    @Column(name = "PTR_ME_CITY")
    private String meCity;
    @Column(name = "PTR_ME_COUNTRY")
    private String meCountry;
    @Column(name = "PTR_ME_PIN_CODE")
    private String mePinCode;
    @Column(name = "PTR_NETWORK")
    private String network;
    @Column(name = "PTR_CARD_TYPE")
    private Character cardType;
    @Column(name = "PTR_CARD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "PTR_CARD_INPUT_ABILITY")
    private Character cardInputAbility;
    @Column(name = "PTR_CH_AUTH_ABILITY")
    private Character chAuthAbility;
    @Column(name = "PTR_CARD_CAPTURE_ABILITY")
    private Character cardCaptureAbility;
    @Column(name = "PTR_OPRT_ENVIRONMENT")
    private Character oprtEnvironment;
    @Column(name = "PTR_CH_PRESENT")
    private Character chPresent;
    @Column(name = "PTR_CARD_PRESENT")
    private Character cardPresent;
    @Column(name = "PTR_CARD_INPUT_MODE")
    private Character cardInputMode;
    @Column(name = "PTR_CH_AUTH_METHOD")
    private Character chAuthMethod;
    @Column(name = "PTR_CH_AUTH_ENTITY")
    private Character chAuthEntity;
    @Column(name = "PTR_CARD_OUTPUT_ABILITY")
    private Character cardOutPutAbility;
    @Column(name = "PTR_TRL_OUTPUT_ABILITTY")
    private Character trlOutPutAbility;
    @Column(name = "PTR_PIN_CAPTURE_ABILITTY")
    private Character pinCaptureAbility;
    @Column(name = "PTR_BTH_NUMBER")
    private Integer batchNumber;
    @Column(name = "PTR_TRL_BTH_NUMBER")
    private Integer trlBatchNumber;
    @Column(name = "PTR_SETL_DATE")
    private LocalDateTime setlDate;
    @Column(name = "PTR_SETL_CODE")
    private Character setlCode;
    @Column(name = "PTR_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name = "PTR_IRD")
    private String ird;
    @Column(name = "PTR_IRD_SER_NUMBER")
    private Integer irdSerNumber;
    @Column(name = "PTR_IRF_FIXED")
    private Double irfFixed;
    @Column(name = "PTR_BANK_CODE")
    private String banKCode;
    @Column(name = "PTR_MER_NET_AMOUNT")
    private Double netAmount;
    @Column(name = "PTR_BDF_NET_AMOUNT")
    private Double bdfNetAmount;
    @Column(name = "PTR_PDF_NET_AMOUNT")
    private Double pdfNetAmount;
    @Column(name = "PTR_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name = "PTR_ONUS_OFFUS_FLAG")
    private Character onusOffusFlag;
    @Column(name = "PTR_SETL_JOB_NUMBER")
    private Integer setlJobNumber;
    @Column(name = "PTR_HOLD_PAYMENT_FLAG")
    private Character holdPaymentFlag;
    @Column(name = "PTR_DMS_SMS_MODE")
    private Character dmsSmsMode;
    @Column(name = "PTR_CARD_CATEGORY")
    private String cardCategory;
    @Column(name = "PTR_CARD_SUB_CATEGORY")
    private String cardSubCategory;
    @Column(name = "PTR_EMV")
    private String emvData;
    @Column(name = "PTR_V_TXN_DATE_TIME")
    private String v_txnDateTime;
    @Column(name = "PTR_V_LOCAL_TXN_TIME")
    private String v_localTxnTime;
    @Column(name = "PTR_V_LOCAL_TXN_DATE")
    private String v_localTxnDate;
    @Column(name = "PTR_DE61")
    private String de61;
    @Column(name = "PTR_DE62")
    private String de62;
    @Column(name = "PTR_DE63")
    private String de63;
    @Column(name = "PTR_SETL_FLAG")
    private Character settlementIndicator;
    @Column(name = "PTR_OPRT_ENV_DE61_1")
    private String oprtEnvDe61_1;
    @Column(name = "PTR_OPRT_ENV_DE61_3")
    private String oprtEnvDe61_3;
    @Column(name = "PTR_V_SETL_DATE")
    private String v_setlDate;
    
    PosTxnWorkEntity(final Integer serialNumber, final Integer pid, final Integer insCode, final LocalDateTime lastUpdatedDate, final int user, final String cardNumber, final Integer intCode, final Integer jobNumber, final Integer genStatus, final Character stagingFlag, final Character paymentFlag, final LocalDate centreProcDate, final LocalDate bussDate, final String txnUniqueId, final String msgTypeId, final Double txnAmount, final LocalDateTime localDateTime, final String responseCode, final String rrn, final String terminalId, final String merchantId, final Integer groupSerNumber, final Integer smeSerNumber, final Integer agrSernumber, final String txnType, final String procCode, final Double convRate, final String stan, final Integer authType, final String authReason, final String txnId, final LocalDateTime txnDateTime, final String acqinstIdCode, final String acqInstConCode, final String fwdInstIdCode, final String recInstConCode, final String adtlPrivateData, final String msgReasonCode, final Double cashBackAmount, final String txnCurCode, final Double txnFeeAmount, final String txnFeeCurCode, final Double setlAmount, final Double tipAmount, final String setlCurCode, final String adtlAmounts, final Double authAmount, final Double replaceAmount, final String adviceReasonCode, final String infData, final String networkData, final String appIdentifier, final String appICProfile, final String appTxnCounter, final String appCryptogram, final String cryptInfoData, final String cvmResult, final String ifdSerNumber, final String issAppData, final String trlAppVerNumber, final String trlCapabilities, final String trlConCode, final String terminalType, final String trlVerResult, final String tcc, final String chipCurCode, final LocalDate chiptxnDate, final String chipTxnDate, final String chipTrlType, final Double chipCashBack, final String upblNumber, final Character cardAuthCode, final String issAuthData, final String posEntryMode, final String posConditionCode, final String motoEcomIndicator, final String cardVerResult, final String cardSeqNumber, final String expiryDate, final String serviceCode, final String mcc, final String meName, final String meCity, final String meCountry, final String mePinCode, final String network, final Character cardType, final Character cardDomIntlFlag, final Character cardInputAbility, final Character chAuthAbility, final Character cardCaptureAbility, final Character oprtEnvironment, final Character chPresent, final Character cardPresent, final Character cardInputMode, final Character chAuthMethod, final Character chAuthEntity, final Character cardOutPutAbility, final Character trlOutPutAbility, final Character pinCaptureAbility, final Integer batchNumber, final Integer trlBatchNumber, final LocalDateTime setlDate, final Character setlCode, final Double cryptAmount, final String ird, final Integer irdSerNumber, final Double irfFixed, final String banKCode, final Double netAmount, final Double bdfNetAmount, final Double pdfNetAmount, final Character revIndiCator, final Character onusOffusFlag, final Integer setlJobNumber, final Character holdPaymentFlag, final Character dmsSmsMode, final String cardCategory, final String cardSubCategory, final String emvData, final String v_txnDateTime, final String v_localTxnTime, final String v_localTxnDate, final String de61, final String de62, final String de63, final Character settlementIndicator, final String oprtEnvDe61_1, final String oprtEnvDe61_3, final String v_setlDate) {
        this.serialNumber = serialNumber;
        this.pid = pid;
        this.insCode = insCode;
        this.lastUpdatedDate = lastUpdatedDate;
        this.user = user;
        this.cardNumber = cardNumber;
        this.intCode = intCode;
        this.jobNumber = jobNumber;
        this.genStatus = genStatus;
        this.stagingFlag = stagingFlag;
        this.paymentFlag = paymentFlag;
        this.centreProcDate = centreProcDate;
        this.bussDate = bussDate;
        this.txnUniqueId = txnUniqueId;
        this.msgTypeId = msgTypeId;
        this.txnAmount = txnAmount;
        this.localDateTime = localDateTime;
        this.responseCode = responseCode;
        this.rrn = rrn;
        this.terminalId = terminalId;
        this.merchantId = merchantId;
        this.groupSerNumber = groupSerNumber;
        this.smeSerNumber = smeSerNumber;
        this.agrSernumber = agrSernumber;
        this.txnType = txnType;
        this.procCode = procCode;
        this.convRate = convRate;
        this.stan = stan;
        this.authType = authType;
        this.authReason = authReason;
        this.txnId = txnId;
        this.txnDateTime = txnDateTime;
        this.acqinstIdCode = acqinstIdCode;
        this.acqInstConCode = acqInstConCode;
        this.fwdInstIdCode = fwdInstIdCode;
        this.recInstConCode = recInstConCode;
        this.adtlPrivateData = adtlPrivateData;
        this.msgReasonCode = msgReasonCode;
        this.cashBackAmount = cashBackAmount;
        this.txnCurCode = txnCurCode;
        this.txnFeeAmount = txnFeeAmount;
        this.txnFeeCurCode = txnFeeCurCode;
        this.setlAmount = setlAmount;
        this.tipAmount = tipAmount;
        this.setlCurCode = setlCurCode;
        this.adtlAmounts = adtlAmounts;
        this.authAmount = authAmount;
        this.replaceAmount = replaceAmount;
        this.adviceReasonCode = adviceReasonCode;
        this.infData = infData;
        this.networkData = networkData;
        this.appIdentifier = appIdentifier;
        this.appICProfile = appICProfile;
        this.appTxnCounter = appTxnCounter;
        this.appCryptogram = appCryptogram;
        this.cryptInfoData = cryptInfoData;
        this.cvmResult = cvmResult;
        this.ifdSerNumber = ifdSerNumber;
        this.issAppData = issAppData;
        this.trlAppVerNumber = trlAppVerNumber;
        this.trlCapabilities = trlCapabilities;
        this.trlConCode = trlConCode;
        this.terminalType = terminalType;
        this.trlVerResult = trlVerResult;
        this.tcc = tcc;
        this.chipCurCode = chipCurCode;
        this.chiptxnDate = chiptxnDate;
        this.chipTxnDate = chipTxnDate;
        this.chipTrlType = chipTrlType;
        this.chipCashBack = chipCashBack;
        this.upblNumber = upblNumber;
        this.cardAuthCode = cardAuthCode;
        this.issAuthData = issAuthData;
        this.posEntryMode = posEntryMode;
        this.posConditionCode = posConditionCode;
        this.motoEcomIndicator = motoEcomIndicator;
        this.cardVerResult = cardVerResult;
        this.cardSeqNumber = cardSeqNumber;
        this.expiryDate = expiryDate;
        this.serviceCode = serviceCode;
        this.mcc = mcc;
        this.meName = meName;
        this.meCity = meCity;
        this.meCountry = meCountry;
        this.mePinCode = mePinCode;
        this.network = network;
        this.cardType = cardType;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.cardInputAbility = cardInputAbility;
        this.chAuthAbility = chAuthAbility;
        this.cardCaptureAbility = cardCaptureAbility;
        this.oprtEnvironment = oprtEnvironment;
        this.chPresent = chPresent;
        this.cardPresent = cardPresent;
        this.cardInputMode = cardInputMode;
        this.chAuthMethod = chAuthMethod;
        this.chAuthEntity = chAuthEntity;
        this.cardOutPutAbility = cardOutPutAbility;
        this.trlOutPutAbility = trlOutPutAbility;
        this.pinCaptureAbility = pinCaptureAbility;
        this.batchNumber = batchNumber;
        this.trlBatchNumber = trlBatchNumber;
        this.setlDate = setlDate;
        this.setlCode = setlCode;
        this.cryptAmount = cryptAmount;
        this.ird = ird;
        this.irdSerNumber = irdSerNumber;
        this.irfFixed = irfFixed;
        this.banKCode = banKCode;
        this.netAmount = netAmount;
        this.bdfNetAmount = bdfNetAmount;
        this.pdfNetAmount = pdfNetAmount;
        this.revIndiCator = revIndiCator;
        this.onusOffusFlag = onusOffusFlag;
        this.setlJobNumber = setlJobNumber;
        this.holdPaymentFlag = holdPaymentFlag;
        this.dmsSmsMode = dmsSmsMode;
        this.cardCategory = cardCategory;
        this.cardSubCategory = cardSubCategory;
        this.emvData = emvData;
        this.v_txnDateTime = v_txnDateTime;
        this.v_localTxnTime = v_localTxnTime;
        this.v_localTxnDate = v_localTxnDate;
        this.de61 = de61;
        this.de62 = de62;
        this.de63 = de63;
        this.settlementIndicator = settlementIndicator;
        this.oprtEnvDe61_1 = oprtEnvDe61_1;
        this.oprtEnvDe61_3 = oprtEnvDe61_3;
        this.v_setlDate = v_setlDate;
    }
    
    public static PosTxnWorkEntityBuilder builder() {
        return new PosTxnWorkEntityBuilder();
    }
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public Integer getPid() {
        return this.pid;
    }
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public LocalDateTime getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public int getUser() {
        return this.user;
    }
    
    public String getCardNumber() {
        return this.cardNumber;
    }
    
    public Integer getIntCode() {
        return this.intCode;
    }
    
    public Integer getJobNumber() {
        return this.jobNumber;
    }
    
    public Integer getGenStatus() {
        return this.genStatus;
    }
    
    public Character getStagingFlag() {
        return this.stagingFlag;
    }
    
    public Character getPaymentFlag() {
        return this.paymentFlag;
    }
    
    public LocalDate getCentreProcDate() {
        return this.centreProcDate;
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
    
    public Double getTxnAmount() {
        return this.txnAmount;
    }
    
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }
    
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getTerminalId() {
        return this.terminalId;
    }
    
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public Integer getGroupSerNumber() {
        return this.groupSerNumber;
    }
    
    public Integer getSmeSerNumber() {
        return this.smeSerNumber;
    }
    
    public Integer getAgrSernumber() {
        return this.agrSernumber;
    }
    
    public String getTxnType() {
        return this.txnType;
    }
    
    public String getProcCode() {
        return this.procCode;
    }
    
    public Double getConvRate() {
        return this.convRate;
    }
    
    public String getStan() {
        return this.stan;
    }
    
    public Integer getAuthType() {
        return this.authType;
    }
    
    public String getAuthReason() {
        return this.authReason;
    }
    
    public String getTxnId() {
        return this.txnId;
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
    
    public String getFwdInstIdCode() {
        return this.fwdInstIdCode;
    }
    
    public String getRecInstConCode() {
        return this.recInstConCode;
    }
    
    public String getAdtlPrivateData() {
        return this.adtlPrivateData;
    }
    
    public String getMsgReasonCode() {
        return this.msgReasonCode;
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
    
    public String getTxnFeeCurCode() {
        return this.txnFeeCurCode;
    }
    
    public Double getSetlAmount() {
        return this.setlAmount;
    }
    
    public Double getTipAmount() {
        return this.tipAmount;
    }
    
    public String getSetlCurCode() {
        return this.setlCurCode;
    }
    
    public String getAdtlAmounts() {
        return this.adtlAmounts;
    }
    
    public Double getAuthAmount() {
        return this.authAmount;
    }
    
    public Double getReplaceAmount() {
        return this.replaceAmount;
    }
    
    public String getAdviceReasonCode() {
        return this.adviceReasonCode;
    }
    
    public String getInfData() {
        return this.infData;
    }
    
    public String getNetworkData() {
        return this.networkData;
    }
    
    public String getAppIdentifier() {
        return this.appIdentifier;
    }
    
    public String getAppICProfile() {
        return this.appICProfile;
    }
    
    public String getAppTxnCounter() {
        return this.appTxnCounter;
    }
    
    public String getAppCryptogram() {
        return this.appCryptogram;
    }
    
    public String getCryptInfoData() {
        return this.cryptInfoData;
    }
    
    public String getCvmResult() {
        return this.cvmResult;
    }
    
    public String getIfdSerNumber() {
        return this.ifdSerNumber;
    }
    
    public String getIssAppData() {
        return this.issAppData;
    }
    
    public String getTrlAppVerNumber() {
        return this.trlAppVerNumber;
    }
    
    public String getTrlCapabilities() {
        return this.trlCapabilities;
    }
    
    public String getTrlConCode() {
        return this.trlConCode;
    }
    
    public String getTerminalType() {
        return this.terminalType;
    }
    
    public String getTrlVerResult() {
        return this.trlVerResult;
    }
    
    public String getTcc() {
        return this.tcc;
    }
    
    public String getChipCurCode() {
        return this.chipCurCode;
    }
    
    public LocalDate getChiptxnDate() {
        return this.chiptxnDate;
    }
    
    public String getChipTrlType() {
        return this.chipTrlType;
    }
    
    public Double getChipCashBack() {
        return this.chipCashBack;
    }
    
    public String getUpblNumber() {
        return this.upblNumber;
    }
    
    public Character getCardAuthCode() {
        return this.cardAuthCode;
    }
    
    public String getIssAuthData() {
        return this.issAuthData;
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
    
    public String getCardVerResult() {
        return this.cardVerResult;
    }
    
    public String getCardSeqNumber() {
        return this.cardSeqNumber;
    }
    
    public String getExpiryDate() {
        return this.expiryDate;
    }
    
    public String getServiceCode() {
        return this.serviceCode;
    }
    
    public String getMcc() {
        return this.mcc;
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
    
    public String getNetwork() {
        return this.network;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }
    
    public Character getCardInputAbility() {
        return this.cardInputAbility;
    }
    
    public Character getChAuthAbility() {
        return this.chAuthAbility;
    }
    
    public Character getCardCaptureAbility() {
        return this.cardCaptureAbility;
    }
    
    public Character getOprtEnvironment() {
        return this.oprtEnvironment;
    }
    
    public Character getChPresent() {
        return this.chPresent;
    }
    
    public Character getCardPresent() {
        return this.cardPresent;
    }
    
    public Character getCardInputMode() {
        return this.cardInputMode;
    }
    
    public Character getChAuthMethod() {
        return this.chAuthMethod;
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
    
    public Character getPinCaptureAbility() {
        return this.pinCaptureAbility;
    }
    
    public Integer getBatchNumber() {
        return this.batchNumber;
    }
    
    public Integer getTrlBatchNumber() {
        return this.trlBatchNumber;
    }
    
    public LocalDateTime getSetlDate() {
        return this.setlDate;
    }
    
    public Character getSetlCode() {
        return this.setlCode;
    }
    
    public Double getCryptAmount() {
        return this.cryptAmount;
    }
    
    public String getIrd() {
        return this.ird;
    }
    
    public Integer getIrdSerNumber() {
        return this.irdSerNumber;
    }
    
    public Double getIrfFixed() {
        return this.irfFixed;
    }
    
    public String getBanKCode() {
        return this.banKCode;
    }
    
    public Double getNetAmount() {
        return this.netAmount;
    }
    
    public Double getBdfNetAmount() {
        return this.bdfNetAmount;
    }
    
    public Double getPdfNetAmount() {
        return this.pdfNetAmount;
    }
    
    public Character getRevIndiCator() {
        return this.revIndiCator;
    }
    
    public Character getOnusOffusFlag() {
        return this.onusOffusFlag;
    }
    
    public Integer getSetlJobNumber() {
        return this.setlJobNumber;
    }
    
    public Character getHoldPaymentFlag() {
        return this.holdPaymentFlag;
    }
    
    public Character getDmsSmsMode() {
        return this.dmsSmsMode;
    }
    
    public String getCardCategory() {
        return this.cardCategory;
    }
    
    public String getCardSubCategory() {
        return this.cardSubCategory;
    }
    
    public String getEmvData() {
        return this.emvData;
    }
    
    public String getV_txnDateTime() {
        return this.v_txnDateTime;
    }
    
    public String getV_localTxnTime() {
        return this.v_localTxnTime;
    }
    
    public String getV_localTxnDate() {
        return this.v_localTxnDate;
    }
    
    public String getDe61() {
        return this.de61;
    }
    
    public String getDe62() {
        return this.de62;
    }
    
    public String getDe63() {
        return this.de63;
    }
    
    public Character getSettlementIndicator() {
        return this.settlementIndicator;
    }
    
    public String getOprtEnvDe61_1() {
        return this.oprtEnvDe61_1;
    }
    
    public String getOprtEnvDe61_3() {
        return this.oprtEnvDe61_3;
    }
    
    public String getV_setlDate() {
        return this.v_setlDate;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setPid(final Integer pid) {
        this.pid = pid;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setLastUpdatedDate(final LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    
    public void setUser(final int user) {
        this.user = user;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setJobNumber(final Integer jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
    }
    
    public void setStagingFlag(final Character stagingFlag) {
        this.stagingFlag = stagingFlag;
    }
    
    public void setPaymentFlag(final Character paymentFlag) {
        this.paymentFlag = paymentFlag;
    }
    
    public void setCentreProcDate(final LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
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
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setGroupSerNumber(final Integer groupSerNumber) {
        this.groupSerNumber = groupSerNumber;
    }
    
    public void setSmeSerNumber(final Integer smeSerNumber) {
        this.smeSerNumber = smeSerNumber;
    }
    
    public void setAgrSernumber(final Integer agrSernumber) {
        this.agrSernumber = agrSernumber;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
    }
    
    public void setConvRate(final Double convRate) {
        this.convRate = convRate;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public void setAuthType(final Integer authType) {
        this.authType = authType;
    }
    
    public void setAuthReason(final String authReason) {
        this.authReason = authReason;
    }
    
    public void setTxnId(final String txnId) {
        this.txnId = txnId;
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
    
    public void setFwdInstIdCode(final String fwdInstIdCode) {
        this.fwdInstIdCode = fwdInstIdCode;
    }
    
    public void setRecInstConCode(final String recInstConCode) {
        this.recInstConCode = recInstConCode;
    }
    
    public void setAdtlPrivateData(final String adtlPrivateData) {
        this.adtlPrivateData = adtlPrivateData;
    }
    
    public void setMsgReasonCode(final String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
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
    
    public void setTxnFeeCurCode(final String txnFeeCurCode) {
        this.txnFeeCurCode = txnFeeCurCode;
    }
    
    public void setSetlAmount(final Double setlAmount) {
        this.setlAmount = setlAmount;
    }
    
    public void setTipAmount(final Double tipAmount) {
        this.tipAmount = tipAmount;
    }
    
    public void setSetlCurCode(final String setlCurCode) {
        this.setlCurCode = setlCurCode;
    }
    
    public void setAdtlAmounts(final String adtlAmounts) {
        this.adtlAmounts = adtlAmounts;
    }
    
    public void setAuthAmount(final Double authAmount) {
        this.authAmount = authAmount;
    }
    
    public void setReplaceAmount(final Double replaceAmount) {
        this.replaceAmount = replaceAmount;
    }
    
    public void setAdviceReasonCode(final String adviceReasonCode) {
        this.adviceReasonCode = adviceReasonCode;
    }
    
    public void setInfData(final String infData) {
        this.infData = infData;
    }
    
    public void setNetworkData(final String networkData) {
        this.networkData = networkData;
    }
    
    public void setAppIdentifier(final String appIdentifier) {
        this.appIdentifier = appIdentifier;
    }
    
    public void setAppICProfile(final String appICProfile) {
        this.appICProfile = appICProfile;
    }
    
    public void setAppTxnCounter(final String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }
    
    public void setAppCryptogram(final String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }
    
    public void setCryptInfoData(final String cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }
    
    public void setCvmResult(final String cvmResult) {
        this.cvmResult = cvmResult;
    }
    
    public void setIfdSerNumber(final String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }
    
    public void setIssAppData(final String issAppData) {
        this.issAppData = issAppData;
    }
    
    public void setTrlAppVerNumber(final String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }
    
    public void setTrlCapabilities(final String trlCapabilities) {
        this.trlCapabilities = trlCapabilities;
    }
    
    public void setTrlConCode(final String trlConCode) {
        this.trlConCode = trlConCode;
    }
    
    public void setTerminalType(final String terminalType) {
        this.terminalType = terminalType;
    }
    
    public void setTrlVerResult(final String trlVerResult) {
        this.trlVerResult = trlVerResult;
    }
    
    public void setTcc(final String tcc) {
        this.tcc = tcc;
    }
    
    public void setChipCurCode(final String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }
    
    public void setChiptxnDate(final LocalDate chiptxnDate) {
        this.chiptxnDate = chiptxnDate;
    }
    
    public void setChipTrlType(final String chipTrlType) {
        this.chipTrlType = chipTrlType;
    }
    
    public void setChipCashBack(final Double chipCashBack) {
        this.chipCashBack = chipCashBack;
    }
    
    public void setUpblNumber(final String upblNumber) {
        this.upblNumber = upblNumber;
    }
    
    public void setCardAuthCode(final Character cardAuthCode) {
        this.cardAuthCode = cardAuthCode;
    }
    
    public void setIssAuthData(final String issAuthData) {
        this.issAuthData = issAuthData;
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
    
    public void setCardVerResult(final String cardVerResult) {
        this.cardVerResult = cardVerResult;
    }
    
    public void setCardSeqNumber(final String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }
    
    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
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
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setCardInputAbility(final Character cardInputAbility) {
        this.cardInputAbility = cardInputAbility;
    }
    
    public void setChAuthAbility(final Character chAuthAbility) {
        this.chAuthAbility = chAuthAbility;
    }
    
    public void setCardCaptureAbility(final Character cardCaptureAbility) {
        this.cardCaptureAbility = cardCaptureAbility;
    }
    
    public void setOprtEnvironment(final Character oprtEnvironment) {
        this.oprtEnvironment = oprtEnvironment;
    }
    
    public void setChPresent(final Character chPresent) {
        this.chPresent = chPresent;
    }
    
    public void setCardPresent(final Character cardPresent) {
        this.cardPresent = cardPresent;
    }
    
    public void setCardInputMode(final Character cardInputMode) {
        this.cardInputMode = cardInputMode;
    }
    
    public void setChAuthMethod(final Character chAuthMethod) {
        this.chAuthMethod = chAuthMethod;
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
    
    public void setPinCaptureAbility(final Character pinCaptureAbility) {
        this.pinCaptureAbility = pinCaptureAbility;
    }
    
    public void setBatchNumber(final Integer batchNumber) {
        this.batchNumber = batchNumber;
    }
    
    public void setTrlBatchNumber(final Integer trlBatchNumber) {
        this.trlBatchNumber = trlBatchNumber;
    }
    
    public void setSetlDate(final LocalDateTime setlDate) {
        this.setlDate = setlDate;
    }
    
    public void setSetlCode(final Character setlCode) {
        this.setlCode = setlCode;
    }
    
    public void setCryptAmount(final Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setIrdSerNumber(final Integer irdSerNumber) {
        this.irdSerNumber = irdSerNumber;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    public void setBanKCode(final String banKCode) {
        this.banKCode = banKCode;
    }
    
    public void setNetAmount(final Double netAmount) {
        this.netAmount = netAmount;
    }
    
    public void setBdfNetAmount(final Double bdfNetAmount) {
        this.bdfNetAmount = bdfNetAmount;
    }
    
    public void setPdfNetAmount(final Double pdfNetAmount) {
        this.pdfNetAmount = pdfNetAmount;
    }
    
    public void setRevIndiCator(final Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }
    
    public void setOnusOffusFlag(final Character onusOffusFlag) {
        this.onusOffusFlag = onusOffusFlag;
    }
    
    public void setSetlJobNumber(final Integer setlJobNumber) {
        this.setlJobNumber = setlJobNumber;
    }
    
    public void setHoldPaymentFlag(final Character holdPaymentFlag) {
        this.holdPaymentFlag = holdPaymentFlag;
    }
    
    public void setDmsSmsMode(final Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }
    
    public void setCardCategory(final String cardCategory) {
        this.cardCategory = cardCategory;
    }
    
    public void setCardSubCategory(final String cardSubCategory) {
        this.cardSubCategory = cardSubCategory;
    }
    
    public void setEmvData(final String emvData) {
        this.emvData = emvData;
    }
    
    public void setV_txnDateTime(final String v_txnDateTime) {
        this.v_txnDateTime = v_txnDateTime;
    }
    
    public void setV_localTxnTime(final String v_localTxnTime) {
        this.v_localTxnTime = v_localTxnTime;
    }
    
    public void setV_localTxnDate(final String v_localTxnDate) {
        this.v_localTxnDate = v_localTxnDate;
    }
    
    public void setDe61(final String de61) {
        this.de61 = de61;
    }
    
    public void setDe62(final String de62) {
        this.de62 = de62;
    }
    
    public void setDe63(final String de63) {
        this.de63 = de63;
    }
    
    public void setSettlementIndicator(final Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }
    
    public void setOprtEnvDe61_1(final String oprtEnvDe61_1) {
        this.oprtEnvDe61_1 = oprtEnvDe61_1;
    }
    
    public void setOprtEnvDe61_3(final String oprtEnvDe61_3) {
        this.oprtEnvDe61_3 = oprtEnvDe61_3;
    }
    
    public void setV_setlDate(final String v_setlDate) {
        this.v_setlDate = v_setlDate;
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
        //     8: instanceof      Lcom/empay/entities/PosTxnWorkEntity;
        //    11: ifne            16
        //    14: iconst_0       
        //    15: ireturn        
        //    16: aload_1         /* o */
        //    17: checkcast       Lcom/empay/entities/PosTxnWorkEntity;
        //    20: astore_2        /* other */
        //    21: aload_2         /* other */
        //    22: aload_0         /* this */
        //    23: invokevirtual   com/empay/entities/PosTxnWorkEntity.canEqual:(Ljava/lang/Object;)Z
        //    26: ifne            31
        //    29: iconst_0       
        //    30: ireturn        
        //    31: aload_0         /* this */
        //    32: invokevirtual   com/empay/entities/PosTxnWorkEntity.getUser:()I
        //    35: aload_2         /* other */
        //    36: invokevirtual   com/empay/entities/PosTxnWorkEntity.getUser:()I
        //    39: if_icmpeq       44
        //    42: iconst_0       
        //    43: ireturn        
        //    44: aload_0         /* this */
        //    45: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSerialNumber:()Ljava/lang/Integer;
        //    48: astore_3        /* this$serialNumber */
        //    49: aload_2         /* other */
        //    50: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSerialNumber:()Ljava/lang/Integer;
        //    53: astore          other$serialNumber
        //    55: aload_3         /* this$serialNumber */
        //    56: ifnonnull       67
        //    59: aload           other$serialNumber
        //    61: ifnull          78
        //    64: goto            76
        //    67: aload_3         /* this$serialNumber */
        //    68: aload           other$serialNumber
        //    70: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    73: ifne            78
        //    76: iconst_0       
        //    77: ireturn        
        //    78: aload_0         /* this */
        //    79: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPid:()Ljava/lang/Integer;
        //    82: astore          this$pid
        //    84: aload_2         /* other */
        //    85: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPid:()Ljava/lang/Integer;
        //    88: astore          other$pid
        //    90: aload           this$pid
        //    92: ifnonnull       103
        //    95: aload           other$pid
        //    97: ifnull          115
        //   100: goto            113
        //   103: aload           this$pid
        //   105: aload           other$pid
        //   107: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   110: ifne            115
        //   113: iconst_0       
        //   114: ireturn        
        //   115: aload_0         /* this */
        //   116: invokevirtual   com/empay/entities/PosTxnWorkEntity.getInsCode:()Ljava/lang/Integer;
        //   119: astore          this$insCode
        //   121: aload_2         /* other */
        //   122: invokevirtual   com/empay/entities/PosTxnWorkEntity.getInsCode:()Ljava/lang/Integer;
        //   125: astore          other$insCode
        //   127: aload           this$insCode
        //   129: ifnonnull       140
        //   132: aload           other$insCode
        //   134: ifnull          152
        //   137: goto            150
        //   140: aload           this$insCode
        //   142: aload           other$insCode
        //   144: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   147: ifne            152
        //   150: iconst_0       
        //   151: ireturn        
        //   152: aload_0         /* this */
        //   153: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIntCode:()Ljava/lang/Integer;
        //   156: astore          this$intCode
        //   158: aload_2         /* other */
        //   159: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIntCode:()Ljava/lang/Integer;
        //   162: astore          other$intCode
        //   164: aload           this$intCode
        //   166: ifnonnull       177
        //   169: aload           other$intCode
        //   171: ifnull          189
        //   174: goto            187
        //   177: aload           this$intCode
        //   179: aload           other$intCode
        //   181: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   184: ifne            189
        //   187: iconst_0       
        //   188: ireturn        
        //   189: aload_0         /* this */
        //   190: invokevirtual   com/empay/entities/PosTxnWorkEntity.getJobNumber:()Ljava/lang/Integer;
        //   193: astore          this$jobNumber
        //   195: aload_2         /* other */
        //   196: invokevirtual   com/empay/entities/PosTxnWorkEntity.getJobNumber:()Ljava/lang/Integer;
        //   199: astore          other$jobNumber
        //   201: aload           this$jobNumber
        //   203: ifnonnull       214
        //   206: aload           other$jobNumber
        //   208: ifnull          226
        //   211: goto            224
        //   214: aload           this$jobNumber
        //   216: aload           other$jobNumber
        //   218: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   221: ifne            226
        //   224: iconst_0       
        //   225: ireturn        
        //   226: aload_0         /* this */
        //   227: invokevirtual   com/empay/entities/PosTxnWorkEntity.getGenStatus:()Ljava/lang/Integer;
        //   230: astore          this$genStatus
        //   232: aload_2         /* other */
        //   233: invokevirtual   com/empay/entities/PosTxnWorkEntity.getGenStatus:()Ljava/lang/Integer;
        //   236: astore          other$genStatus
        //   238: aload           this$genStatus
        //   240: ifnonnull       251
        //   243: aload           other$genStatus
        //   245: ifnull          263
        //   248: goto            261
        //   251: aload           this$genStatus
        //   253: aload           other$genStatus
        //   255: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   258: ifne            263
        //   261: iconst_0       
        //   262: ireturn        
        //   263: aload_0         /* this */
        //   264: invokevirtual   com/empay/entities/PosTxnWorkEntity.getStagingFlag:()Ljava/lang/Character;
        //   267: astore          this$stagingFlag
        //   269: aload_2         /* other */
        //   270: invokevirtual   com/empay/entities/PosTxnWorkEntity.getStagingFlag:()Ljava/lang/Character;
        //   273: astore          other$stagingFlag
        //   275: aload           this$stagingFlag
        //   277: ifnonnull       288
        //   280: aload           other$stagingFlag
        //   282: ifnull          300
        //   285: goto            298
        //   288: aload           this$stagingFlag
        //   290: aload           other$stagingFlag
        //   292: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   295: ifne            300
        //   298: iconst_0       
        //   299: ireturn        
        //   300: aload_0         /* this */
        //   301: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPaymentFlag:()Ljava/lang/Character;
        //   304: astore          this$paymentFlag
        //   306: aload_2         /* other */
        //   307: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPaymentFlag:()Ljava/lang/Character;
        //   310: astore          other$paymentFlag
        //   312: aload           this$paymentFlag
        //   314: ifnonnull       325
        //   317: aload           other$paymentFlag
        //   319: ifnull          337
        //   322: goto            335
        //   325: aload           this$paymentFlag
        //   327: aload           other$paymentFlag
        //   329: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   332: ifne            337
        //   335: iconst_0       
        //   336: ireturn        
        //   337: aload_0         /* this */
        //   338: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnAmount:()Ljava/lang/Double;
        //   341: astore          this$txnAmount
        //   343: aload_2         /* other */
        //   344: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnAmount:()Ljava/lang/Double;
        //   347: astore          other$txnAmount
        //   349: aload           this$txnAmount
        //   351: ifnonnull       362
        //   354: aload           other$txnAmount
        //   356: ifnull          374
        //   359: goto            372
        //   362: aload           this$txnAmount
        //   364: aload           other$txnAmount
        //   366: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   369: ifne            374
        //   372: iconst_0       
        //   373: ireturn        
        //   374: aload_0         /* this */
        //   375: invokevirtual   com/empay/entities/PosTxnWorkEntity.getGroupSerNumber:()Ljava/lang/Integer;
        //   378: astore          this$groupSerNumber
        //   380: aload_2         /* other */
        //   381: invokevirtual   com/empay/entities/PosTxnWorkEntity.getGroupSerNumber:()Ljava/lang/Integer;
        //   384: astore          other$groupSerNumber
        //   386: aload           this$groupSerNumber
        //   388: ifnonnull       399
        //   391: aload           other$groupSerNumber
        //   393: ifnull          411
        //   396: goto            409
        //   399: aload           this$groupSerNumber
        //   401: aload           other$groupSerNumber
        //   403: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   406: ifne            411
        //   409: iconst_0       
        //   410: ireturn        
        //   411: aload_0         /* this */
        //   412: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSmeSerNumber:()Ljava/lang/Integer;
        //   415: astore          this$smeSerNumber
        //   417: aload_2         /* other */
        //   418: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSmeSerNumber:()Ljava/lang/Integer;
        //   421: astore          other$smeSerNumber
        //   423: aload           this$smeSerNumber
        //   425: ifnonnull       436
        //   428: aload           other$smeSerNumber
        //   430: ifnull          448
        //   433: goto            446
        //   436: aload           this$smeSerNumber
        //   438: aload           other$smeSerNumber
        //   440: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   443: ifne            448
        //   446: iconst_0       
        //   447: ireturn        
        //   448: aload_0         /* this */
        //   449: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAgrSernumber:()Ljava/lang/Integer;
        //   452: astore          this$agrSernumber
        //   454: aload_2         /* other */
        //   455: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAgrSernumber:()Ljava/lang/Integer;
        //   458: astore          other$agrSernumber
        //   460: aload           this$agrSernumber
        //   462: ifnonnull       473
        //   465: aload           other$agrSernumber
        //   467: ifnull          485
        //   470: goto            483
        //   473: aload           this$agrSernumber
        //   475: aload           other$agrSernumber
        //   477: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   480: ifne            485
        //   483: iconst_0       
        //   484: ireturn        
        //   485: aload_0         /* this */
        //   486: invokevirtual   com/empay/entities/PosTxnWorkEntity.getConvRate:()Ljava/lang/Double;
        //   489: astore          this$convRate
        //   491: aload_2         /* other */
        //   492: invokevirtual   com/empay/entities/PosTxnWorkEntity.getConvRate:()Ljava/lang/Double;
        //   495: astore          other$convRate
        //   497: aload           this$convRate
        //   499: ifnonnull       510
        //   502: aload           other$convRate
        //   504: ifnull          522
        //   507: goto            520
        //   510: aload           this$convRate
        //   512: aload           other$convRate
        //   514: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   517: ifne            522
        //   520: iconst_0       
        //   521: ireturn        
        //   522: aload_0         /* this */
        //   523: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAuthType:()Ljava/lang/Integer;
        //   526: astore          this$authType
        //   528: aload_2         /* other */
        //   529: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAuthType:()Ljava/lang/Integer;
        //   532: astore          other$authType
        //   534: aload           this$authType
        //   536: ifnonnull       547
        //   539: aload           other$authType
        //   541: ifnull          559
        //   544: goto            557
        //   547: aload           this$authType
        //   549: aload           other$authType
        //   551: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   554: ifne            559
        //   557: iconst_0       
        //   558: ireturn        
        //   559: aload_0         /* this */
        //   560: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCashBackAmount:()Ljava/lang/Double;
        //   563: astore          this$cashBackAmount
        //   565: aload_2         /* other */
        //   566: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCashBackAmount:()Ljava/lang/Double;
        //   569: astore          other$cashBackAmount
        //   571: aload           this$cashBackAmount
        //   573: ifnonnull       584
        //   576: aload           other$cashBackAmount
        //   578: ifnull          596
        //   581: goto            594
        //   584: aload           this$cashBackAmount
        //   586: aload           other$cashBackAmount
        //   588: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   591: ifne            596
        //   594: iconst_0       
        //   595: ireturn        
        //   596: aload_0         /* this */
        //   597: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnFeeAmount:()Ljava/lang/Double;
        //   600: astore          this$txnFeeAmount
        //   602: aload_2         /* other */
        //   603: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnFeeAmount:()Ljava/lang/Double;
        //   606: astore          other$txnFeeAmount
        //   608: aload           this$txnFeeAmount
        //   610: ifnonnull       621
        //   613: aload           other$txnFeeAmount
        //   615: ifnull          633
        //   618: goto            631
        //   621: aload           this$txnFeeAmount
        //   623: aload           other$txnFeeAmount
        //   625: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   628: ifne            633
        //   631: iconst_0       
        //   632: ireturn        
        //   633: aload_0         /* this */
        //   634: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlAmount:()Ljava/lang/Double;
        //   637: astore          this$setlAmount
        //   639: aload_2         /* other */
        //   640: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlAmount:()Ljava/lang/Double;
        //   643: astore          other$setlAmount
        //   645: aload           this$setlAmount
        //   647: ifnonnull       658
        //   650: aload           other$setlAmount
        //   652: ifnull          670
        //   655: goto            668
        //   658: aload           this$setlAmount
        //   660: aload           other$setlAmount
        //   662: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   665: ifne            670
        //   668: iconst_0       
        //   669: ireturn        
        //   670: aload_0         /* this */
        //   671: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTipAmount:()Ljava/lang/Double;
        //   674: astore          this$tipAmount
        //   676: aload_2         /* other */
        //   677: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTipAmount:()Ljava/lang/Double;
        //   680: astore          other$tipAmount
        //   682: aload           this$tipAmount
        //   684: ifnonnull       695
        //   687: aload           other$tipAmount
        //   689: ifnull          707
        //   692: goto            705
        //   695: aload           this$tipAmount
        //   697: aload           other$tipAmount
        //   699: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   702: ifne            707
        //   705: iconst_0       
        //   706: ireturn        
        //   707: aload_0         /* this */
        //   708: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAuthAmount:()Ljava/lang/Double;
        //   711: astore          this$authAmount
        //   713: aload_2         /* other */
        //   714: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAuthAmount:()Ljava/lang/Double;
        //   717: astore          other$authAmount
        //   719: aload           this$authAmount
        //   721: ifnonnull       732
        //   724: aload           other$authAmount
        //   726: ifnull          744
        //   729: goto            742
        //   732: aload           this$authAmount
        //   734: aload           other$authAmount
        //   736: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   739: ifne            744
        //   742: iconst_0       
        //   743: ireturn        
        //   744: aload_0         /* this */
        //   745: invokevirtual   com/empay/entities/PosTxnWorkEntity.getReplaceAmount:()Ljava/lang/Double;
        //   748: astore          this$replaceAmount
        //   750: aload_2         /* other */
        //   751: invokevirtual   com/empay/entities/PosTxnWorkEntity.getReplaceAmount:()Ljava/lang/Double;
        //   754: astore          other$replaceAmount
        //   756: aload           this$replaceAmount
        //   758: ifnonnull       769
        //   761: aload           other$replaceAmount
        //   763: ifnull          781
        //   766: goto            779
        //   769: aload           this$replaceAmount
        //   771: aload           other$replaceAmount
        //   773: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   776: ifne            781
        //   779: iconst_0       
        //   780: ireturn        
        //   781: aload_0         /* this */
        //   782: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChipCashBack:()Ljava/lang/Double;
        //   785: astore          this$chipCashBack
        //   787: aload_2         /* other */
        //   788: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChipCashBack:()Ljava/lang/Double;
        //   791: astore          other$chipCashBack
        //   793: aload           this$chipCashBack
        //   795: ifnonnull       806
        //   798: aload           other$chipCashBack
        //   800: ifnull          818
        //   803: goto            816
        //   806: aload           this$chipCashBack
        //   808: aload           other$chipCashBack
        //   810: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   813: ifne            818
        //   816: iconst_0       
        //   817: ireturn        
        //   818: aload_0         /* this */
        //   819: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardAuthCode:()Ljava/lang/Character;
        //   822: astore          this$cardAuthCode
        //   824: aload_2         /* other */
        //   825: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardAuthCode:()Ljava/lang/Character;
        //   828: astore          other$cardAuthCode
        //   830: aload           this$cardAuthCode
        //   832: ifnonnull       843
        //   835: aload           other$cardAuthCode
        //   837: ifnull          855
        //   840: goto            853
        //   843: aload           this$cardAuthCode
        //   845: aload           other$cardAuthCode
        //   847: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   850: ifne            855
        //   853: iconst_0       
        //   854: ireturn        
        //   855: aload_0         /* this */
        //   856: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardType:()Ljava/lang/Character;
        //   859: astore          this$cardType
        //   861: aload_2         /* other */
        //   862: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardType:()Ljava/lang/Character;
        //   865: astore          other$cardType
        //   867: aload           this$cardType
        //   869: ifnonnull       880
        //   872: aload           other$cardType
        //   874: ifnull          892
        //   877: goto            890
        //   880: aload           this$cardType
        //   882: aload           other$cardType
        //   884: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   887: ifne            892
        //   890: iconst_0       
        //   891: ireturn        
        //   892: aload_0         /* this */
        //   893: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardDomIntlFlag:()Ljava/lang/Character;
        //   896: astore          this$cardDomIntlFlag
        //   898: aload_2         /* other */
        //   899: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardDomIntlFlag:()Ljava/lang/Character;
        //   902: astore          other$cardDomIntlFlag
        //   904: aload           this$cardDomIntlFlag
        //   906: ifnonnull       917
        //   909: aload           other$cardDomIntlFlag
        //   911: ifnull          929
        //   914: goto            927
        //   917: aload           this$cardDomIntlFlag
        //   919: aload           other$cardDomIntlFlag
        //   921: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   924: ifne            929
        //   927: iconst_0       
        //   928: ireturn        
        //   929: aload_0         /* this */
        //   930: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardInputAbility:()Ljava/lang/Character;
        //   933: astore          this$cardInputAbility
        //   935: aload_2         /* other */
        //   936: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardInputAbility:()Ljava/lang/Character;
        //   939: astore          other$cardInputAbility
        //   941: aload           this$cardInputAbility
        //   943: ifnonnull       954
        //   946: aload           other$cardInputAbility
        //   948: ifnull          966
        //   951: goto            964
        //   954: aload           this$cardInputAbility
        //   956: aload           other$cardInputAbility
        //   958: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   961: ifne            966
        //   964: iconst_0       
        //   965: ireturn        
        //   966: aload_0         /* this */
        //   967: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChAuthAbility:()Ljava/lang/Character;
        //   970: astore          this$chAuthAbility
        //   972: aload_2         /* other */
        //   973: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChAuthAbility:()Ljava/lang/Character;
        //   976: astore          other$chAuthAbility
        //   978: aload           this$chAuthAbility
        //   980: ifnonnull       991
        //   983: aload           other$chAuthAbility
        //   985: ifnull          1003
        //   988: goto            1001
        //   991: aload           this$chAuthAbility
        //   993: aload           other$chAuthAbility
        //   995: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   998: ifne            1003
        //  1001: iconst_0       
        //  1002: ireturn        
        //  1003: aload_0         /* this */
        //  1004: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardCaptureAbility:()Ljava/lang/Character;
        //  1007: astore          this$cardCaptureAbility
        //  1009: aload_2         /* other */
        //  1010: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardCaptureAbility:()Ljava/lang/Character;
        //  1013: astore          other$cardCaptureAbility
        //  1015: aload           this$cardCaptureAbility
        //  1017: ifnonnull       1028
        //  1020: aload           other$cardCaptureAbility
        //  1022: ifnull          1040
        //  1025: goto            1038
        //  1028: aload           this$cardCaptureAbility
        //  1030: aload           other$cardCaptureAbility
        //  1032: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1035: ifne            1040
        //  1038: iconst_0       
        //  1039: ireturn        
        //  1040: aload_0         /* this */
        //  1041: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOprtEnvironment:()Ljava/lang/Character;
        //  1044: astore          this$oprtEnvironment
        //  1046: aload_2         /* other */
        //  1047: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOprtEnvironment:()Ljava/lang/Character;
        //  1050: astore          other$oprtEnvironment
        //  1052: aload           this$oprtEnvironment
        //  1054: ifnonnull       1065
        //  1057: aload           other$oprtEnvironment
        //  1059: ifnull          1077
        //  1062: goto            1075
        //  1065: aload           this$oprtEnvironment
        //  1067: aload           other$oprtEnvironment
        //  1069: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1072: ifne            1077
        //  1075: iconst_0       
        //  1076: ireturn        
        //  1077: aload_0         /* this */
        //  1078: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChPresent:()Ljava/lang/Character;
        //  1081: astore          this$chPresent
        //  1083: aload_2         /* other */
        //  1084: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChPresent:()Ljava/lang/Character;
        //  1087: astore          other$chPresent
        //  1089: aload           this$chPresent
        //  1091: ifnonnull       1102
        //  1094: aload           other$chPresent
        //  1096: ifnull          1114
        //  1099: goto            1112
        //  1102: aload           this$chPresent
        //  1104: aload           other$chPresent
        //  1106: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1109: ifne            1114
        //  1112: iconst_0       
        //  1113: ireturn        
        //  1114: aload_0         /* this */
        //  1115: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardPresent:()Ljava/lang/Character;
        //  1118: astore          this$cardPresent
        //  1120: aload_2         /* other */
        //  1121: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardPresent:()Ljava/lang/Character;
        //  1124: astore          other$cardPresent
        //  1126: aload           this$cardPresent
        //  1128: ifnonnull       1139
        //  1131: aload           other$cardPresent
        //  1133: ifnull          1151
        //  1136: goto            1149
        //  1139: aload           this$cardPresent
        //  1141: aload           other$cardPresent
        //  1143: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1146: ifne            1151
        //  1149: iconst_0       
        //  1150: ireturn        
        //  1151: aload_0         /* this */
        //  1152: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardInputMode:()Ljava/lang/Character;
        //  1155: astore          this$cardInputMode
        //  1157: aload_2         /* other */
        //  1158: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardInputMode:()Ljava/lang/Character;
        //  1161: astore          other$cardInputMode
        //  1163: aload           this$cardInputMode
        //  1165: ifnonnull       1176
        //  1168: aload           other$cardInputMode
        //  1170: ifnull          1188
        //  1173: goto            1186
        //  1176: aload           this$cardInputMode
        //  1178: aload           other$cardInputMode
        //  1180: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1183: ifne            1188
        //  1186: iconst_0       
        //  1187: ireturn        
        //  1188: aload_0         /* this */
        //  1189: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChAuthMethod:()Ljava/lang/Character;
        //  1192: astore          this$chAuthMethod
        //  1194: aload_2         /* other */
        //  1195: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChAuthMethod:()Ljava/lang/Character;
        //  1198: astore          other$chAuthMethod
        //  1200: aload           this$chAuthMethod
        //  1202: ifnonnull       1213
        //  1205: aload           other$chAuthMethod
        //  1207: ifnull          1225
        //  1210: goto            1223
        //  1213: aload           this$chAuthMethod
        //  1215: aload           other$chAuthMethod
        //  1217: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1220: ifne            1225
        //  1223: iconst_0       
        //  1224: ireturn        
        //  1225: aload_0         /* this */
        //  1226: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChAuthEntity:()Ljava/lang/Character;
        //  1229: astore          this$chAuthEntity
        //  1231: aload_2         /* other */
        //  1232: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChAuthEntity:()Ljava/lang/Character;
        //  1235: astore          other$chAuthEntity
        //  1237: aload           this$chAuthEntity
        //  1239: ifnonnull       1250
        //  1242: aload           other$chAuthEntity
        //  1244: ifnull          1262
        //  1247: goto            1260
        //  1250: aload           this$chAuthEntity
        //  1252: aload           other$chAuthEntity
        //  1254: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1257: ifne            1262
        //  1260: iconst_0       
        //  1261: ireturn        
        //  1262: aload_0         /* this */
        //  1263: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardOutPutAbility:()Ljava/lang/Character;
        //  1266: astore          this$cardOutPutAbility
        //  1268: aload_2         /* other */
        //  1269: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardOutPutAbility:()Ljava/lang/Character;
        //  1272: astore          other$cardOutPutAbility
        //  1274: aload           this$cardOutPutAbility
        //  1276: ifnonnull       1287
        //  1279: aload           other$cardOutPutAbility
        //  1281: ifnull          1299
        //  1284: goto            1297
        //  1287: aload           this$cardOutPutAbility
        //  1289: aload           other$cardOutPutAbility
        //  1291: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1294: ifne            1299
        //  1297: iconst_0       
        //  1298: ireturn        
        //  1299: aload_0         /* this */
        //  1300: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlOutPutAbility:()Ljava/lang/Character;
        //  1303: astore          this$trlOutPutAbility
        //  1305: aload_2         /* other */
        //  1306: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlOutPutAbility:()Ljava/lang/Character;
        //  1309: astore          other$trlOutPutAbility
        //  1311: aload           this$trlOutPutAbility
        //  1313: ifnonnull       1324
        //  1316: aload           other$trlOutPutAbility
        //  1318: ifnull          1336
        //  1321: goto            1334
        //  1324: aload           this$trlOutPutAbility
        //  1326: aload           other$trlOutPutAbility
        //  1328: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1331: ifne            1336
        //  1334: iconst_0       
        //  1335: ireturn        
        //  1336: aload_0         /* this */
        //  1337: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPinCaptureAbility:()Ljava/lang/Character;
        //  1340: astore          this$pinCaptureAbility
        //  1342: aload_2         /* other */
        //  1343: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPinCaptureAbility:()Ljava/lang/Character;
        //  1346: astore          other$pinCaptureAbility
        //  1348: aload           this$pinCaptureAbility
        //  1350: ifnonnull       1361
        //  1353: aload           other$pinCaptureAbility
        //  1355: ifnull          1373
        //  1358: goto            1371
        //  1361: aload           this$pinCaptureAbility
        //  1363: aload           other$pinCaptureAbility
        //  1365: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1368: ifne            1373
        //  1371: iconst_0       
        //  1372: ireturn        
        //  1373: aload_0         /* this */
        //  1374: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBatchNumber:()Ljava/lang/Integer;
        //  1377: astore          this$batchNumber
        //  1379: aload_2         /* other */
        //  1380: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBatchNumber:()Ljava/lang/Integer;
        //  1383: astore          other$batchNumber
        //  1385: aload           this$batchNumber
        //  1387: ifnonnull       1398
        //  1390: aload           other$batchNumber
        //  1392: ifnull          1410
        //  1395: goto            1408
        //  1398: aload           this$batchNumber
        //  1400: aload           other$batchNumber
        //  1402: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1405: ifne            1410
        //  1408: iconst_0       
        //  1409: ireturn        
        //  1410: aload_0         /* this */
        //  1411: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlBatchNumber:()Ljava/lang/Integer;
        //  1414: astore          this$trlBatchNumber
        //  1416: aload_2         /* other */
        //  1417: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlBatchNumber:()Ljava/lang/Integer;
        //  1420: astore          other$trlBatchNumber
        //  1422: aload           this$trlBatchNumber
        //  1424: ifnonnull       1435
        //  1427: aload           other$trlBatchNumber
        //  1429: ifnull          1447
        //  1432: goto            1445
        //  1435: aload           this$trlBatchNumber
        //  1437: aload           other$trlBatchNumber
        //  1439: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1442: ifne            1447
        //  1445: iconst_0       
        //  1446: ireturn        
        //  1447: aload_0         /* this */
        //  1448: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlCode:()Ljava/lang/Character;
        //  1451: astore          this$setlCode
        //  1453: aload_2         /* other */
        //  1454: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlCode:()Ljava/lang/Character;
        //  1457: astore          other$setlCode
        //  1459: aload           this$setlCode
        //  1461: ifnonnull       1472
        //  1464: aload           other$setlCode
        //  1466: ifnull          1484
        //  1469: goto            1482
        //  1472: aload           this$setlCode
        //  1474: aload           other$setlCode
        //  1476: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1479: ifne            1484
        //  1482: iconst_0       
        //  1483: ireturn        
        //  1484: aload_0         /* this */
        //  1485: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCryptAmount:()Ljava/lang/Double;
        //  1488: astore          this$cryptAmount
        //  1490: aload_2         /* other */
        //  1491: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCryptAmount:()Ljava/lang/Double;
        //  1494: astore          other$cryptAmount
        //  1496: aload           this$cryptAmount
        //  1498: ifnonnull       1509
        //  1501: aload           other$cryptAmount
        //  1503: ifnull          1521
        //  1506: goto            1519
        //  1509: aload           this$cryptAmount
        //  1511: aload           other$cryptAmount
        //  1513: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1516: ifne            1521
        //  1519: iconst_0       
        //  1520: ireturn        
        //  1521: aload_0         /* this */
        //  1522: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIrdSerNumber:()Ljava/lang/Integer;
        //  1525: astore          this$irdSerNumber
        //  1527: aload_2         /* other */
        //  1528: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIrdSerNumber:()Ljava/lang/Integer;
        //  1531: astore          other$irdSerNumber
        //  1533: aload           this$irdSerNumber
        //  1535: ifnonnull       1546
        //  1538: aload           other$irdSerNumber
        //  1540: ifnull          1558
        //  1543: goto            1556
        //  1546: aload           this$irdSerNumber
        //  1548: aload           other$irdSerNumber
        //  1550: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1553: ifne            1558
        //  1556: iconst_0       
        //  1557: ireturn        
        //  1558: aload_0         /* this */
        //  1559: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIrfFixed:()Ljava/lang/Double;
        //  1562: astore          this$irfFixed
        //  1564: aload_2         /* other */
        //  1565: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIrfFixed:()Ljava/lang/Double;
        //  1568: astore          other$irfFixed
        //  1570: aload           this$irfFixed
        //  1572: ifnonnull       1583
        //  1575: aload           other$irfFixed
        //  1577: ifnull          1595
        //  1580: goto            1593
        //  1583: aload           this$irfFixed
        //  1585: aload           other$irfFixed
        //  1587: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1590: ifne            1595
        //  1593: iconst_0       
        //  1594: ireturn        
        //  1595: aload_0         /* this */
        //  1596: invokevirtual   com/empay/entities/PosTxnWorkEntity.getNetAmount:()Ljava/lang/Double;
        //  1599: astore          this$netAmount
        //  1601: aload_2         /* other */
        //  1602: invokevirtual   com/empay/entities/PosTxnWorkEntity.getNetAmount:()Ljava/lang/Double;
        //  1605: astore          other$netAmount
        //  1607: aload           this$netAmount
        //  1609: ifnonnull       1620
        //  1612: aload           other$netAmount
        //  1614: ifnull          1632
        //  1617: goto            1630
        //  1620: aload           this$netAmount
        //  1622: aload           other$netAmount
        //  1624: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1627: ifne            1632
        //  1630: iconst_0       
        //  1631: ireturn        
        //  1632: aload_0         /* this */
        //  1633: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBdfNetAmount:()Ljava/lang/Double;
        //  1636: astore          this$bdfNetAmount
        //  1638: aload_2         /* other */
        //  1639: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBdfNetAmount:()Ljava/lang/Double;
        //  1642: astore          other$bdfNetAmount
        //  1644: aload           this$bdfNetAmount
        //  1646: ifnonnull       1657
        //  1649: aload           other$bdfNetAmount
        //  1651: ifnull          1669
        //  1654: goto            1667
        //  1657: aload           this$bdfNetAmount
        //  1659: aload           other$bdfNetAmount
        //  1661: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1664: ifne            1669
        //  1667: iconst_0       
        //  1668: ireturn        
        //  1669: aload_0         /* this */
        //  1670: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPdfNetAmount:()Ljava/lang/Double;
        //  1673: astore          this$pdfNetAmount
        //  1675: aload_2         /* other */
        //  1676: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPdfNetAmount:()Ljava/lang/Double;
        //  1679: astore          other$pdfNetAmount
        //  1681: aload           this$pdfNetAmount
        //  1683: ifnonnull       1694
        //  1686: aload           other$pdfNetAmount
        //  1688: ifnull          1706
        //  1691: goto            1704
        //  1694: aload           this$pdfNetAmount
        //  1696: aload           other$pdfNetAmount
        //  1698: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1701: ifne            1706
        //  1704: iconst_0       
        //  1705: ireturn        
        //  1706: aload_0         /* this */
        //  1707: invokevirtual   com/empay/entities/PosTxnWorkEntity.getRevIndiCator:()Ljava/lang/Character;
        //  1710: astore          this$revIndiCator
        //  1712: aload_2         /* other */
        //  1713: invokevirtual   com/empay/entities/PosTxnWorkEntity.getRevIndiCator:()Ljava/lang/Character;
        //  1716: astore          other$revIndiCator
        //  1718: aload           this$revIndiCator
        //  1720: ifnonnull       1731
        //  1723: aload           other$revIndiCator
        //  1725: ifnull          1743
        //  1728: goto            1741
        //  1731: aload           this$revIndiCator
        //  1733: aload           other$revIndiCator
        //  1735: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1738: ifne            1743
        //  1741: iconst_0       
        //  1742: ireturn        
        //  1743: aload_0         /* this */
        //  1744: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOnusOffusFlag:()Ljava/lang/Character;
        //  1747: astore          this$onusOffusFlag
        //  1749: aload_2         /* other */
        //  1750: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOnusOffusFlag:()Ljava/lang/Character;
        //  1753: astore          other$onusOffusFlag
        //  1755: aload           this$onusOffusFlag
        //  1757: ifnonnull       1768
        //  1760: aload           other$onusOffusFlag
        //  1762: ifnull          1780
        //  1765: goto            1778
        //  1768: aload           this$onusOffusFlag
        //  1770: aload           other$onusOffusFlag
        //  1772: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1775: ifne            1780
        //  1778: iconst_0       
        //  1779: ireturn        
        //  1780: aload_0         /* this */
        //  1781: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlJobNumber:()Ljava/lang/Integer;
        //  1784: astore          this$setlJobNumber
        //  1786: aload_2         /* other */
        //  1787: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlJobNumber:()Ljava/lang/Integer;
        //  1790: astore          other$setlJobNumber
        //  1792: aload           this$setlJobNumber
        //  1794: ifnonnull       1805
        //  1797: aload           other$setlJobNumber
        //  1799: ifnull          1817
        //  1802: goto            1815
        //  1805: aload           this$setlJobNumber
        //  1807: aload           other$setlJobNumber
        //  1809: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1812: ifne            1817
        //  1815: iconst_0       
        //  1816: ireturn        
        //  1817: aload_0         /* this */
        //  1818: invokevirtual   com/empay/entities/PosTxnWorkEntity.getHoldPaymentFlag:()Ljava/lang/Character;
        //  1821: astore          this$holdPaymentFlag
        //  1823: aload_2         /* other */
        //  1824: invokevirtual   com/empay/entities/PosTxnWorkEntity.getHoldPaymentFlag:()Ljava/lang/Character;
        //  1827: astore          other$holdPaymentFlag
        //  1829: aload           this$holdPaymentFlag
        //  1831: ifnonnull       1842
        //  1834: aload           other$holdPaymentFlag
        //  1836: ifnull          1854
        //  1839: goto            1852
        //  1842: aload           this$holdPaymentFlag
        //  1844: aload           other$holdPaymentFlag
        //  1846: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1849: ifne            1854
        //  1852: iconst_0       
        //  1853: ireturn        
        //  1854: aload_0         /* this */
        //  1855: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDmsSmsMode:()Ljava/lang/Character;
        //  1858: astore          this$dmsSmsMode
        //  1860: aload_2         /* other */
        //  1861: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDmsSmsMode:()Ljava/lang/Character;
        //  1864: astore          other$dmsSmsMode
        //  1866: aload           this$dmsSmsMode
        //  1868: ifnonnull       1879
        //  1871: aload           other$dmsSmsMode
        //  1873: ifnull          1891
        //  1876: goto            1889
        //  1879: aload           this$dmsSmsMode
        //  1881: aload           other$dmsSmsMode
        //  1883: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1886: ifne            1891
        //  1889: iconst_0       
        //  1890: ireturn        
        //  1891: aload_0         /* this */
        //  1892: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSettlementIndicator:()Ljava/lang/Character;
        //  1895: astore          this$settlementIndicator
        //  1897: aload_2         /* other */
        //  1898: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSettlementIndicator:()Ljava/lang/Character;
        //  1901: astore          other$settlementIndicator
        //  1903: aload           this$settlementIndicator
        //  1905: ifnonnull       1916
        //  1908: aload           other$settlementIndicator
        //  1910: ifnull          1928
        //  1913: goto            1926
        //  1916: aload           this$settlementIndicator
        //  1918: aload           other$settlementIndicator
        //  1920: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1923: ifne            1928
        //  1926: iconst_0       
        //  1927: ireturn        
        //  1928: aload_0         /* this */
        //  1929: invokevirtual   com/empay/entities/PosTxnWorkEntity.getLastUpdatedDate:()Ljava/time/LocalDateTime;
        //  1932: astore          this$lastUpdatedDate
        //  1934: aload_2         /* other */
        //  1935: invokevirtual   com/empay/entities/PosTxnWorkEntity.getLastUpdatedDate:()Ljava/time/LocalDateTime;
        //  1938: astore          other$lastUpdatedDate
        //  1940: aload           this$lastUpdatedDate
        //  1942: ifnonnull       1953
        //  1945: aload           other$lastUpdatedDate
        //  1947: ifnull          1965
        //  1950: goto            1963
        //  1953: aload           this$lastUpdatedDate
        //  1955: aload           other$lastUpdatedDate
        //  1957: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1960: ifne            1965
        //  1963: iconst_0       
        //  1964: ireturn        
        //  1965: aload_0         /* this */
        //  1966: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardNumber:()Ljava/lang/String;
        //  1969: astore          this$cardNumber
        //  1971: aload_2         /* other */
        //  1972: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardNumber:()Ljava/lang/String;
        //  1975: astore          other$cardNumber
        //  1977: aload           this$cardNumber
        //  1979: ifnonnull       1990
        //  1982: aload           other$cardNumber
        //  1984: ifnull          2002
        //  1987: goto            2000
        //  1990: aload           this$cardNumber
        //  1992: aload           other$cardNumber
        //  1994: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  1997: ifne            2002
        //  2000: iconst_0       
        //  2001: ireturn        
        //  2002: aload_0         /* this */
        //  2003: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCentreProcDate:()Ljava/time/LocalDate;
        //  2006: astore          this$centreProcDate
        //  2008: aload_2         /* other */
        //  2009: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCentreProcDate:()Ljava/time/LocalDate;
        //  2012: astore          other$centreProcDate
        //  2014: aload           this$centreProcDate
        //  2016: ifnonnull       2027
        //  2019: aload           other$centreProcDate
        //  2021: ifnull          2039
        //  2024: goto            2037
        //  2027: aload           this$centreProcDate
        //  2029: aload           other$centreProcDate
        //  2031: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2034: ifne            2039
        //  2037: iconst_0       
        //  2038: ireturn        
        //  2039: aload_0         /* this */
        //  2040: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBussDate:()Ljava/time/LocalDate;
        //  2043: astore          this$bussDate
        //  2045: aload_2         /* other */
        //  2046: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBussDate:()Ljava/time/LocalDate;
        //  2049: astore          other$bussDate
        //  2051: aload           this$bussDate
        //  2053: ifnonnull       2064
        //  2056: aload           other$bussDate
        //  2058: ifnull          2076
        //  2061: goto            2074
        //  2064: aload           this$bussDate
        //  2066: aload           other$bussDate
        //  2068: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2071: ifne            2076
        //  2074: iconst_0       
        //  2075: ireturn        
        //  2076: aload_0         /* this */
        //  2077: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnUniqueId:()Ljava/lang/String;
        //  2080: astore          this$txnUniqueId
        //  2082: aload_2         /* other */
        //  2083: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnUniqueId:()Ljava/lang/String;
        //  2086: astore          other$txnUniqueId
        //  2088: aload           this$txnUniqueId
        //  2090: ifnonnull       2101
        //  2093: aload           other$txnUniqueId
        //  2095: ifnull          2113
        //  2098: goto            2111
        //  2101: aload           this$txnUniqueId
        //  2103: aload           other$txnUniqueId
        //  2105: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2108: ifne            2113
        //  2111: iconst_0       
        //  2112: ireturn        
        //  2113: aload_0         /* this */
        //  2114: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMsgTypeId:()Ljava/lang/String;
        //  2117: astore          this$msgTypeId
        //  2119: aload_2         /* other */
        //  2120: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMsgTypeId:()Ljava/lang/String;
        //  2123: astore          other$msgTypeId
        //  2125: aload           this$msgTypeId
        //  2127: ifnonnull       2138
        //  2130: aload           other$msgTypeId
        //  2132: ifnull          2150
        //  2135: goto            2148
        //  2138: aload           this$msgTypeId
        //  2140: aload           other$msgTypeId
        //  2142: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2145: ifne            2150
        //  2148: iconst_0       
        //  2149: ireturn        
        //  2150: aload_0         /* this */
        //  2151: invokevirtual   com/empay/entities/PosTxnWorkEntity.getLocalDateTime:()Ljava/time/LocalDateTime;
        //  2154: astore          this$localDateTime
        //  2156: aload_2         /* other */
        //  2157: invokevirtual   com/empay/entities/PosTxnWorkEntity.getLocalDateTime:()Ljava/time/LocalDateTime;
        //  2160: astore          other$localDateTime
        //  2162: aload           this$localDateTime
        //  2164: ifnonnull       2175
        //  2167: aload           other$localDateTime
        //  2169: ifnull          2187
        //  2172: goto            2185
        //  2175: aload           this$localDateTime
        //  2177: aload           other$localDateTime
        //  2179: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2182: ifne            2187
        //  2185: iconst_0       
        //  2186: ireturn        
        //  2187: aload_0         /* this */
        //  2188: invokevirtual   com/empay/entities/PosTxnWorkEntity.getResponseCode:()Ljava/lang/String;
        //  2191: astore          this$responseCode
        //  2193: aload_2         /* other */
        //  2194: invokevirtual   com/empay/entities/PosTxnWorkEntity.getResponseCode:()Ljava/lang/String;
        //  2197: astore          other$responseCode
        //  2199: aload           this$responseCode
        //  2201: ifnonnull       2212
        //  2204: aload           other$responseCode
        //  2206: ifnull          2224
        //  2209: goto            2222
        //  2212: aload           this$responseCode
        //  2214: aload           other$responseCode
        //  2216: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2219: ifne            2224
        //  2222: iconst_0       
        //  2223: ireturn        
        //  2224: aload_0         /* this */
        //  2225: invokevirtual   com/empay/entities/PosTxnWorkEntity.getRrn:()Ljava/lang/String;
        //  2228: astore          this$rrn
        //  2230: aload_2         /* other */
        //  2231: invokevirtual   com/empay/entities/PosTxnWorkEntity.getRrn:()Ljava/lang/String;
        //  2234: astore          other$rrn
        //  2236: aload           this$rrn
        //  2238: ifnonnull       2249
        //  2241: aload           other$rrn
        //  2243: ifnull          2261
        //  2246: goto            2259
        //  2249: aload           this$rrn
        //  2251: aload           other$rrn
        //  2253: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2256: ifne            2261
        //  2259: iconst_0       
        //  2260: ireturn        
        //  2261: aload_0         /* this */
        //  2262: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTerminalId:()Ljava/lang/String;
        //  2265: astore          this$terminalId
        //  2267: aload_2         /* other */
        //  2268: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTerminalId:()Ljava/lang/String;
        //  2271: astore          other$terminalId
        //  2273: aload           this$terminalId
        //  2275: ifnonnull       2286
        //  2278: aload           other$terminalId
        //  2280: ifnull          2298
        //  2283: goto            2296
        //  2286: aload           this$terminalId
        //  2288: aload           other$terminalId
        //  2290: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2293: ifne            2298
        //  2296: iconst_0       
        //  2297: ireturn        
        //  2298: aload_0         /* this */
        //  2299: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMerchantId:()Ljava/lang/String;
        //  2302: astore          this$merchantId
        //  2304: aload_2         /* other */
        //  2305: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMerchantId:()Ljava/lang/String;
        //  2308: astore          other$merchantId
        //  2310: aload           this$merchantId
        //  2312: ifnonnull       2323
        //  2315: aload           other$merchantId
        //  2317: ifnull          2335
        //  2320: goto            2333
        //  2323: aload           this$merchantId
        //  2325: aload           other$merchantId
        //  2327: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2330: ifne            2335
        //  2333: iconst_0       
        //  2334: ireturn        
        //  2335: aload_0         /* this */
        //  2336: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnType:()Ljava/lang/String;
        //  2339: astore          this$txnType
        //  2341: aload_2         /* other */
        //  2342: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnType:()Ljava/lang/String;
        //  2345: astore          other$txnType
        //  2347: aload           this$txnType
        //  2349: ifnonnull       2360
        //  2352: aload           other$txnType
        //  2354: ifnull          2372
        //  2357: goto            2370
        //  2360: aload           this$txnType
        //  2362: aload           other$txnType
        //  2364: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2367: ifne            2372
        //  2370: iconst_0       
        //  2371: ireturn        
        //  2372: aload_0         /* this */
        //  2373: invokevirtual   com/empay/entities/PosTxnWorkEntity.getProcCode:()Ljava/lang/String;
        //  2376: astore          this$procCode
        //  2378: aload_2         /* other */
        //  2379: invokevirtual   com/empay/entities/PosTxnWorkEntity.getProcCode:()Ljava/lang/String;
        //  2382: astore          other$procCode
        //  2384: aload           this$procCode
        //  2386: ifnonnull       2397
        //  2389: aload           other$procCode
        //  2391: ifnull          2409
        //  2394: goto            2407
        //  2397: aload           this$procCode
        //  2399: aload           other$procCode
        //  2401: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2404: ifne            2409
        //  2407: iconst_0       
        //  2408: ireturn        
        //  2409: aload_0         /* this */
        //  2410: invokevirtual   com/empay/entities/PosTxnWorkEntity.getStan:()Ljava/lang/String;
        //  2413: astore          this$stan
        //  2415: aload_2         /* other */
        //  2416: invokevirtual   com/empay/entities/PosTxnWorkEntity.getStan:()Ljava/lang/String;
        //  2419: astore          other$stan
        //  2421: aload           this$stan
        //  2423: ifnonnull       2434
        //  2426: aload           other$stan
        //  2428: ifnull          2446
        //  2431: goto            2444
        //  2434: aload           this$stan
        //  2436: aload           other$stan
        //  2438: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2441: ifne            2446
        //  2444: iconst_0       
        //  2445: ireturn        
        //  2446: aload_0         /* this */
        //  2447: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAuthReason:()Ljava/lang/String;
        //  2450: astore          this$authReason
        //  2452: aload_2         /* other */
        //  2453: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAuthReason:()Ljava/lang/String;
        //  2456: astore          other$authReason
        //  2458: aload           this$authReason
        //  2460: ifnonnull       2471
        //  2463: aload           other$authReason
        //  2465: ifnull          2483
        //  2468: goto            2481
        //  2471: aload           this$authReason
        //  2473: aload           other$authReason
        //  2475: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2478: ifne            2483
        //  2481: iconst_0       
        //  2482: ireturn        
        //  2483: aload_0         /* this */
        //  2484: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnId:()Ljava/lang/String;
        //  2487: astore          this$txnId
        //  2489: aload_2         /* other */
        //  2490: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnId:()Ljava/lang/String;
        //  2493: astore          other$txnId
        //  2495: aload           this$txnId
        //  2497: ifnonnull       2508
        //  2500: aload           other$txnId
        //  2502: ifnull          2520
        //  2505: goto            2518
        //  2508: aload           this$txnId
        //  2510: aload           other$txnId
        //  2512: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2515: ifne            2520
        //  2518: iconst_0       
        //  2519: ireturn        
        //  2520: aload_0         /* this */
        //  2521: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnDateTime:()Ljava/time/LocalDateTime;
        //  2524: astore          this$txnDateTime
        //  2526: aload_2         /* other */
        //  2527: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnDateTime:()Ljava/time/LocalDateTime;
        //  2530: astore          other$txnDateTime
        //  2532: aload           this$txnDateTime
        //  2534: ifnonnull       2545
        //  2537: aload           other$txnDateTime
        //  2539: ifnull          2557
        //  2542: goto            2555
        //  2545: aload           this$txnDateTime
        //  2547: aload           other$txnDateTime
        //  2549: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2552: ifne            2557
        //  2555: iconst_0       
        //  2556: ireturn        
        //  2557: aload_0         /* this */
        //  2558: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAcqinstIdCode:()Ljava/lang/String;
        //  2561: astore          this$acqinstIdCode
        //  2563: aload_2         /* other */
        //  2564: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAcqinstIdCode:()Ljava/lang/String;
        //  2567: astore          other$acqinstIdCode
        //  2569: aload           this$acqinstIdCode
        //  2571: ifnonnull       2582
        //  2574: aload           other$acqinstIdCode
        //  2576: ifnull          2594
        //  2579: goto            2592
        //  2582: aload           this$acqinstIdCode
        //  2584: aload           other$acqinstIdCode
        //  2586: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2589: ifne            2594
        //  2592: iconst_0       
        //  2593: ireturn        
        //  2594: aload_0         /* this */
        //  2595: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAcqInstConCode:()Ljava/lang/String;
        //  2598: astore          this$acqInstConCode
        //  2600: aload_2         /* other */
        //  2601: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAcqInstConCode:()Ljava/lang/String;
        //  2604: astore          other$acqInstConCode
        //  2606: aload           this$acqInstConCode
        //  2608: ifnonnull       2619
        //  2611: aload           other$acqInstConCode
        //  2613: ifnull          2631
        //  2616: goto            2629
        //  2619: aload           this$acqInstConCode
        //  2621: aload           other$acqInstConCode
        //  2623: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2626: ifne            2631
        //  2629: iconst_0       
        //  2630: ireturn        
        //  2631: aload_0         /* this */
        //  2632: invokevirtual   com/empay/entities/PosTxnWorkEntity.getFwdInstIdCode:()Ljava/lang/String;
        //  2635: astore          this$fwdInstIdCode
        //  2637: aload_2         /* other */
        //  2638: invokevirtual   com/empay/entities/PosTxnWorkEntity.getFwdInstIdCode:()Ljava/lang/String;
        //  2641: astore          other$fwdInstIdCode
        //  2643: aload           this$fwdInstIdCode
        //  2645: ifnonnull       2656
        //  2648: aload           other$fwdInstIdCode
        //  2650: ifnull          2668
        //  2653: goto            2666
        //  2656: aload           this$fwdInstIdCode
        //  2658: aload           other$fwdInstIdCode
        //  2660: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2663: ifne            2668
        //  2666: iconst_0       
        //  2667: ireturn        
        //  2668: aload_0         /* this */
        //  2669: invokevirtual   com/empay/entities/PosTxnWorkEntity.getRecInstConCode:()Ljava/lang/String;
        //  2672: astore          this$recInstConCode
        //  2674: aload_2         /* other */
        //  2675: invokevirtual   com/empay/entities/PosTxnWorkEntity.getRecInstConCode:()Ljava/lang/String;
        //  2678: astore          other$recInstConCode
        //  2680: aload           this$recInstConCode
        //  2682: ifnonnull       2693
        //  2685: aload           other$recInstConCode
        //  2687: ifnull          2705
        //  2690: goto            2703
        //  2693: aload           this$recInstConCode
        //  2695: aload           other$recInstConCode
        //  2697: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2700: ifne            2705
        //  2703: iconst_0       
        //  2704: ireturn        
        //  2705: aload_0         /* this */
        //  2706: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAdtlPrivateData:()Ljava/lang/String;
        //  2709: astore          this$adtlPrivateData
        //  2711: aload_2         /* other */
        //  2712: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAdtlPrivateData:()Ljava/lang/String;
        //  2715: astore          other$adtlPrivateData
        //  2717: aload           this$adtlPrivateData
        //  2719: ifnonnull       2730
        //  2722: aload           other$adtlPrivateData
        //  2724: ifnull          2742
        //  2727: goto            2740
        //  2730: aload           this$adtlPrivateData
        //  2732: aload           other$adtlPrivateData
        //  2734: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2737: ifne            2742
        //  2740: iconst_0       
        //  2741: ireturn        
        //  2742: aload_0         /* this */
        //  2743: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMsgReasonCode:()Ljava/lang/String;
        //  2746: astore          this$msgReasonCode
        //  2748: aload_2         /* other */
        //  2749: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMsgReasonCode:()Ljava/lang/String;
        //  2752: astore          other$msgReasonCode
        //  2754: aload           this$msgReasonCode
        //  2756: ifnonnull       2767
        //  2759: aload           other$msgReasonCode
        //  2761: ifnull          2779
        //  2764: goto            2777
        //  2767: aload           this$msgReasonCode
        //  2769: aload           other$msgReasonCode
        //  2771: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2774: ifne            2779
        //  2777: iconst_0       
        //  2778: ireturn        
        //  2779: aload_0         /* this */
        //  2780: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnCurCode:()Ljava/lang/String;
        //  2783: astore          this$txnCurCode
        //  2785: aload_2         /* other */
        //  2786: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnCurCode:()Ljava/lang/String;
        //  2789: astore          other$txnCurCode
        //  2791: aload           this$txnCurCode
        //  2793: ifnonnull       2804
        //  2796: aload           other$txnCurCode
        //  2798: ifnull          2816
        //  2801: goto            2814
        //  2804: aload           this$txnCurCode
        //  2806: aload           other$txnCurCode
        //  2808: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2811: ifne            2816
        //  2814: iconst_0       
        //  2815: ireturn        
        //  2816: aload_0         /* this */
        //  2817: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnFeeCurCode:()Ljava/lang/String;
        //  2820: astore          this$txnFeeCurCode
        //  2822: aload_2         /* other */
        //  2823: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTxnFeeCurCode:()Ljava/lang/String;
        //  2826: astore          other$txnFeeCurCode
        //  2828: aload           this$txnFeeCurCode
        //  2830: ifnonnull       2841
        //  2833: aload           other$txnFeeCurCode
        //  2835: ifnull          2853
        //  2838: goto            2851
        //  2841: aload           this$txnFeeCurCode
        //  2843: aload           other$txnFeeCurCode
        //  2845: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2848: ifne            2853
        //  2851: iconst_0       
        //  2852: ireturn        
        //  2853: aload_0         /* this */
        //  2854: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlCurCode:()Ljava/lang/String;
        //  2857: astore          this$setlCurCode
        //  2859: aload_2         /* other */
        //  2860: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlCurCode:()Ljava/lang/String;
        //  2863: astore          other$setlCurCode
        //  2865: aload           this$setlCurCode
        //  2867: ifnonnull       2878
        //  2870: aload           other$setlCurCode
        //  2872: ifnull          2890
        //  2875: goto            2888
        //  2878: aload           this$setlCurCode
        //  2880: aload           other$setlCurCode
        //  2882: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2885: ifne            2890
        //  2888: iconst_0       
        //  2889: ireturn        
        //  2890: aload_0         /* this */
        //  2891: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAdtlAmounts:()Ljava/lang/String;
        //  2894: astore          this$adtlAmounts
        //  2896: aload_2         /* other */
        //  2897: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAdtlAmounts:()Ljava/lang/String;
        //  2900: astore          other$adtlAmounts
        //  2902: aload           this$adtlAmounts
        //  2904: ifnonnull       2915
        //  2907: aload           other$adtlAmounts
        //  2909: ifnull          2927
        //  2912: goto            2925
        //  2915: aload           this$adtlAmounts
        //  2917: aload           other$adtlAmounts
        //  2919: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2922: ifne            2927
        //  2925: iconst_0       
        //  2926: ireturn        
        //  2927: aload_0         /* this */
        //  2928: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAdviceReasonCode:()Ljava/lang/String;
        //  2931: astore          this$adviceReasonCode
        //  2933: aload_2         /* other */
        //  2934: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAdviceReasonCode:()Ljava/lang/String;
        //  2937: astore          other$adviceReasonCode
        //  2939: aload           this$adviceReasonCode
        //  2941: ifnonnull       2952
        //  2944: aload           other$adviceReasonCode
        //  2946: ifnull          2964
        //  2949: goto            2962
        //  2952: aload           this$adviceReasonCode
        //  2954: aload           other$adviceReasonCode
        //  2956: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2959: ifne            2964
        //  2962: iconst_0       
        //  2963: ireturn        
        //  2964: aload_0         /* this */
        //  2965: invokevirtual   com/empay/entities/PosTxnWorkEntity.getInfData:()Ljava/lang/String;
        //  2968: astore          this$infData
        //  2970: aload_2         /* other */
        //  2971: invokevirtual   com/empay/entities/PosTxnWorkEntity.getInfData:()Ljava/lang/String;
        //  2974: astore          other$infData
        //  2976: aload           this$infData
        //  2978: ifnonnull       2989
        //  2981: aload           other$infData
        //  2983: ifnull          3001
        //  2986: goto            2999
        //  2989: aload           this$infData
        //  2991: aload           other$infData
        //  2993: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  2996: ifne            3001
        //  2999: iconst_0       
        //  3000: ireturn        
        //  3001: aload_0         /* this */
        //  3002: invokevirtual   com/empay/entities/PosTxnWorkEntity.getNetworkData:()Ljava/lang/String;
        //  3005: astore          this$networkData
        //  3007: aload_2         /* other */
        //  3008: invokevirtual   com/empay/entities/PosTxnWorkEntity.getNetworkData:()Ljava/lang/String;
        //  3011: astore          other$networkData
        //  3013: aload           this$networkData
        //  3015: ifnonnull       3026
        //  3018: aload           other$networkData
        //  3020: ifnull          3038
        //  3023: goto            3036
        //  3026: aload           this$networkData
        //  3028: aload           other$networkData
        //  3030: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3033: ifne            3038
        //  3036: iconst_0       
        //  3037: ireturn        
        //  3038: aload_0         /* this */
        //  3039: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppIdentifier:()Ljava/lang/String;
        //  3042: astore          this$appIdentifier
        //  3044: aload_2         /* other */
        //  3045: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppIdentifier:()Ljava/lang/String;
        //  3048: astore          other$appIdentifier
        //  3050: aload           this$appIdentifier
        //  3052: ifnonnull       3063
        //  3055: aload           other$appIdentifier
        //  3057: ifnull          3075
        //  3060: goto            3073
        //  3063: aload           this$appIdentifier
        //  3065: aload           other$appIdentifier
        //  3067: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3070: ifne            3075
        //  3073: iconst_0       
        //  3074: ireturn        
        //  3075: aload_0         /* this */
        //  3076: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppICProfile:()Ljava/lang/String;
        //  3079: astore          this$appICProfile
        //  3081: aload_2         /* other */
        //  3082: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppICProfile:()Ljava/lang/String;
        //  3085: astore          other$appICProfile
        //  3087: aload           this$appICProfile
        //  3089: ifnonnull       3100
        //  3092: aload           other$appICProfile
        //  3094: ifnull          3112
        //  3097: goto            3110
        //  3100: aload           this$appICProfile
        //  3102: aload           other$appICProfile
        //  3104: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3107: ifne            3112
        //  3110: iconst_0       
        //  3111: ireturn        
        //  3112: aload_0         /* this */
        //  3113: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppTxnCounter:()Ljava/lang/String;
        //  3116: astore          this$appTxnCounter
        //  3118: aload_2         /* other */
        //  3119: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppTxnCounter:()Ljava/lang/String;
        //  3122: astore          other$appTxnCounter
        //  3124: aload           this$appTxnCounter
        //  3126: ifnonnull       3137
        //  3129: aload           other$appTxnCounter
        //  3131: ifnull          3149
        //  3134: goto            3147
        //  3137: aload           this$appTxnCounter
        //  3139: aload           other$appTxnCounter
        //  3141: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3144: ifne            3149
        //  3147: iconst_0       
        //  3148: ireturn        
        //  3149: aload_0         /* this */
        //  3150: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppCryptogram:()Ljava/lang/String;
        //  3153: astore          this$appCryptogram
        //  3155: aload_2         /* other */
        //  3156: invokevirtual   com/empay/entities/PosTxnWorkEntity.getAppCryptogram:()Ljava/lang/String;
        //  3159: astore          other$appCryptogram
        //  3161: aload           this$appCryptogram
        //  3163: ifnonnull       3174
        //  3166: aload           other$appCryptogram
        //  3168: ifnull          3186
        //  3171: goto            3184
        //  3174: aload           this$appCryptogram
        //  3176: aload           other$appCryptogram
        //  3178: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3181: ifne            3186
        //  3184: iconst_0       
        //  3185: ireturn        
        //  3186: aload_0         /* this */
        //  3187: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCryptInfoData:()Ljava/lang/String;
        //  3190: astore          this$cryptInfoData
        //  3192: aload_2         /* other */
        //  3193: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCryptInfoData:()Ljava/lang/String;
        //  3196: astore          other$cryptInfoData
        //  3198: aload           this$cryptInfoData
        //  3200: ifnonnull       3211
        //  3203: aload           other$cryptInfoData
        //  3205: ifnull          3223
        //  3208: goto            3221
        //  3211: aload           this$cryptInfoData
        //  3213: aload           other$cryptInfoData
        //  3215: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3218: ifne            3223
        //  3221: iconst_0       
        //  3222: ireturn        
        //  3223: aload_0         /* this */
        //  3224: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCvmResult:()Ljava/lang/String;
        //  3227: astore          this$cvmResult
        //  3229: aload_2         /* other */
        //  3230: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCvmResult:()Ljava/lang/String;
        //  3233: astore          other$cvmResult
        //  3235: aload           this$cvmResult
        //  3237: ifnonnull       3248
        //  3240: aload           other$cvmResult
        //  3242: ifnull          3260
        //  3245: goto            3258
        //  3248: aload           this$cvmResult
        //  3250: aload           other$cvmResult
        //  3252: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3255: ifne            3260
        //  3258: iconst_0       
        //  3259: ireturn        
        //  3260: aload_0         /* this */
        //  3261: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIfdSerNumber:()Ljava/lang/String;
        //  3264: astore          this$ifdSerNumber
        //  3266: aload_2         /* other */
        //  3267: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIfdSerNumber:()Ljava/lang/String;
        //  3270: astore          other$ifdSerNumber
        //  3272: aload           this$ifdSerNumber
        //  3274: ifnonnull       3285
        //  3277: aload           other$ifdSerNumber
        //  3279: ifnull          3297
        //  3282: goto            3295
        //  3285: aload           this$ifdSerNumber
        //  3287: aload           other$ifdSerNumber
        //  3289: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3292: ifne            3297
        //  3295: iconst_0       
        //  3296: ireturn        
        //  3297: aload_0         /* this */
        //  3298: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIssAppData:()Ljava/lang/String;
        //  3301: astore          this$issAppData
        //  3303: aload_2         /* other */
        //  3304: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIssAppData:()Ljava/lang/String;
        //  3307: astore          other$issAppData
        //  3309: aload           this$issAppData
        //  3311: ifnonnull       3322
        //  3314: aload           other$issAppData
        //  3316: ifnull          3334
        //  3319: goto            3332
        //  3322: aload           this$issAppData
        //  3324: aload           other$issAppData
        //  3326: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3329: ifne            3334
        //  3332: iconst_0       
        //  3333: ireturn        
        //  3334: aload_0         /* this */
        //  3335: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlAppVerNumber:()Ljava/lang/String;
        //  3338: astore          this$trlAppVerNumber
        //  3340: aload_2         /* other */
        //  3341: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlAppVerNumber:()Ljava/lang/String;
        //  3344: astore          other$trlAppVerNumber
        //  3346: aload           this$trlAppVerNumber
        //  3348: ifnonnull       3359
        //  3351: aload           other$trlAppVerNumber
        //  3353: ifnull          3371
        //  3356: goto            3369
        //  3359: aload           this$trlAppVerNumber
        //  3361: aload           other$trlAppVerNumber
        //  3363: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3366: ifne            3371
        //  3369: iconst_0       
        //  3370: ireturn        
        //  3371: aload_0         /* this */
        //  3372: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlCapabilities:()Ljava/lang/String;
        //  3375: astore          this$trlCapabilities
        //  3377: aload_2         /* other */
        //  3378: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlCapabilities:()Ljava/lang/String;
        //  3381: astore          other$trlCapabilities
        //  3383: aload           this$trlCapabilities
        //  3385: ifnonnull       3396
        //  3388: aload           other$trlCapabilities
        //  3390: ifnull          3408
        //  3393: goto            3406
        //  3396: aload           this$trlCapabilities
        //  3398: aload           other$trlCapabilities
        //  3400: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3403: ifne            3408
        //  3406: iconst_0       
        //  3407: ireturn        
        //  3408: aload_0         /* this */
        //  3409: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlConCode:()Ljava/lang/String;
        //  3412: astore          this$trlConCode
        //  3414: aload_2         /* other */
        //  3415: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlConCode:()Ljava/lang/String;
        //  3418: astore          other$trlConCode
        //  3420: aload           this$trlConCode
        //  3422: ifnonnull       3433
        //  3425: aload           other$trlConCode
        //  3427: ifnull          3445
        //  3430: goto            3443
        //  3433: aload           this$trlConCode
        //  3435: aload           other$trlConCode
        //  3437: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3440: ifne            3445
        //  3443: iconst_0       
        //  3444: ireturn        
        //  3445: aload_0         /* this */
        //  3446: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTerminalType:()Ljava/lang/String;
        //  3449: astore          this$terminalType
        //  3451: aload_2         /* other */
        //  3452: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTerminalType:()Ljava/lang/String;
        //  3455: astore          other$terminalType
        //  3457: aload           this$terminalType
        //  3459: ifnonnull       3470
        //  3462: aload           other$terminalType
        //  3464: ifnull          3482
        //  3467: goto            3480
        //  3470: aload           this$terminalType
        //  3472: aload           other$terminalType
        //  3474: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3477: ifne            3482
        //  3480: iconst_0       
        //  3481: ireturn        
        //  3482: aload_0         /* this */
        //  3483: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlVerResult:()Ljava/lang/String;
        //  3486: astore          this$trlVerResult
        //  3488: aload_2         /* other */
        //  3489: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTrlVerResult:()Ljava/lang/String;
        //  3492: astore          other$trlVerResult
        //  3494: aload           this$trlVerResult
        //  3496: ifnonnull       3507
        //  3499: aload           other$trlVerResult
        //  3501: ifnull          3519
        //  3504: goto            3517
        //  3507: aload           this$trlVerResult
        //  3509: aload           other$trlVerResult
        //  3511: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3514: ifne            3519
        //  3517: iconst_0       
        //  3518: ireturn        
        //  3519: aload_0         /* this */
        //  3520: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTcc:()Ljava/lang/String;
        //  3523: astore          this$tcc
        //  3525: aload_2         /* other */
        //  3526: invokevirtual   com/empay/entities/PosTxnWorkEntity.getTcc:()Ljava/lang/String;
        //  3529: astore          other$tcc
        //  3531: aload           this$tcc
        //  3533: ifnonnull       3544
        //  3536: aload           other$tcc
        //  3538: ifnull          3556
        //  3541: goto            3554
        //  3544: aload           this$tcc
        //  3546: aload           other$tcc
        //  3548: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3551: ifne            3556
        //  3554: iconst_0       
        //  3555: ireturn        
        //  3556: aload_0         /* this */
        //  3557: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChipCurCode:()Ljava/lang/String;
        //  3560: astore          this$chipCurCode
        //  3562: aload_2         /* other */
        //  3563: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChipCurCode:()Ljava/lang/String;
        //  3566: astore          other$chipCurCode
        //  3568: aload           this$chipCurCode
        //  3570: ifnonnull       3581
        //  3573: aload           other$chipCurCode
        //  3575: ifnull          3593
        //  3578: goto            3591
        //  3581: aload           this$chipCurCode
        //  3583: aload           other$chipCurCode
        //  3585: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3588: ifne            3593
        //  3591: iconst_0       
        //  3592: ireturn        
        //  3593: aload_0         /* this */
        //  3594: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChiptxnDate:()Ljava/time/LocalDate;
        //  3597: astore          this$chiptxnDate
        //  3599: aload_2         /* other */
        //  3600: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChiptxnDate:()Ljava/time/LocalDate;
        //  3603: astore          other$chiptxnDate
        //  3605: aload           this$chiptxnDate
        //  3607: ifnonnull       3618
        //  3610: aload           other$chiptxnDate
        //  3612: ifnull          3630
        //  3615: goto            3628
        //  3618: aload           this$chiptxnDate
        //  3620: aload           other$chiptxnDate
        //  3622: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3625: ifne            3630
        //  3628: iconst_0       
        //  3629: ireturn        
        //  3630: aload_0         /* this */
        //  3631: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChiptxnDate:()Ljava/time/LocalDate;
        //  3634: astore          this$chipTxnDate
        //  3636: aload_2         /* other */
        //  3637: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChiptxnDate:()Ljava/time/LocalDate;
        //  3640: astore          other$chipTxnDate
        //  3642: aload           this$chipTxnDate
        //  3644: ifnonnull       3655
        //  3647: aload           other$chipTxnDate
        //  3649: ifnull          3667
        //  3652: goto            3665
        //  3655: aload           this$chipTxnDate
        //  3657: aload           other$chipTxnDate
        //  3659: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3662: ifne            3667
        //  3665: iconst_0       
        //  3666: ireturn        
        //  3667: aload_0         /* this */
        //  3668: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChipTrlType:()Ljava/lang/String;
        //  3671: astore          this$chipTrlType
        //  3673: aload_2         /* other */
        //  3674: invokevirtual   com/empay/entities/PosTxnWorkEntity.getChipTrlType:()Ljava/lang/String;
        //  3677: astore          other$chipTrlType
        //  3679: aload           this$chipTrlType
        //  3681: ifnonnull       3692
        //  3684: aload           other$chipTrlType
        //  3686: ifnull          3704
        //  3689: goto            3702
        //  3692: aload           this$chipTrlType
        //  3694: aload           other$chipTrlType
        //  3696: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3699: ifne            3704
        //  3702: iconst_0       
        //  3703: ireturn        
        //  3704: aload_0         /* this */
        //  3705: invokevirtual   com/empay/entities/PosTxnWorkEntity.getUpblNumber:()Ljava/lang/String;
        //  3708: astore          this$upblNumber
        //  3710: aload_2         /* other */
        //  3711: invokevirtual   com/empay/entities/PosTxnWorkEntity.getUpblNumber:()Ljava/lang/String;
        //  3714: astore          other$upblNumber
        //  3716: aload           this$upblNumber
        //  3718: ifnonnull       3729
        //  3721: aload           other$upblNumber
        //  3723: ifnull          3741
        //  3726: goto            3739
        //  3729: aload           this$upblNumber
        //  3731: aload           other$upblNumber
        //  3733: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3736: ifne            3741
        //  3739: iconst_0       
        //  3740: ireturn        
        //  3741: aload_0         /* this */
        //  3742: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIssAuthData:()Ljava/lang/String;
        //  3745: astore          this$issAuthData
        //  3747: aload_2         /* other */
        //  3748: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIssAuthData:()Ljava/lang/String;
        //  3751: astore          other$issAuthData
        //  3753: aload           this$issAuthData
        //  3755: ifnonnull       3766
        //  3758: aload           other$issAuthData
        //  3760: ifnull          3778
        //  3763: goto            3776
        //  3766: aload           this$issAuthData
        //  3768: aload           other$issAuthData
        //  3770: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3773: ifne            3778
        //  3776: iconst_0       
        //  3777: ireturn        
        //  3778: aload_0         /* this */
        //  3779: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPosEntryMode:()Ljava/lang/String;
        //  3782: astore          this$posEntryMode
        //  3784: aload_2         /* other */
        //  3785: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPosEntryMode:()Ljava/lang/String;
        //  3788: astore          other$posEntryMode
        //  3790: aload           this$posEntryMode
        //  3792: ifnonnull       3803
        //  3795: aload           other$posEntryMode
        //  3797: ifnull          3815
        //  3800: goto            3813
        //  3803: aload           this$posEntryMode
        //  3805: aload           other$posEntryMode
        //  3807: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3810: ifne            3815
        //  3813: iconst_0       
        //  3814: ireturn        
        //  3815: aload_0         /* this */
        //  3816: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPosConditionCode:()Ljava/lang/String;
        //  3819: astore          this$posConditionCode
        //  3821: aload_2         /* other */
        //  3822: invokevirtual   com/empay/entities/PosTxnWorkEntity.getPosConditionCode:()Ljava/lang/String;
        //  3825: astore          other$posConditionCode
        //  3827: aload           this$posConditionCode
        //  3829: ifnonnull       3840
        //  3832: aload           other$posConditionCode
        //  3834: ifnull          3852
        //  3837: goto            3850
        //  3840: aload           this$posConditionCode
        //  3842: aload           other$posConditionCode
        //  3844: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3847: ifne            3852
        //  3850: iconst_0       
        //  3851: ireturn        
        //  3852: aload_0         /* this */
        //  3853: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMotoEcomIndicator:()Ljava/lang/String;
        //  3856: astore          this$motoEcomIndicator
        //  3858: aload_2         /* other */
        //  3859: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMotoEcomIndicator:()Ljava/lang/String;
        //  3862: astore          other$motoEcomIndicator
        //  3864: aload           this$motoEcomIndicator
        //  3866: ifnonnull       3877
        //  3869: aload           other$motoEcomIndicator
        //  3871: ifnull          3889
        //  3874: goto            3887
        //  3877: aload           this$motoEcomIndicator
        //  3879: aload           other$motoEcomIndicator
        //  3881: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3884: ifne            3889
        //  3887: iconst_0       
        //  3888: ireturn        
        //  3889: aload_0         /* this */
        //  3890: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardVerResult:()Ljava/lang/String;
        //  3893: astore          this$cardVerResult
        //  3895: aload_2         /* other */
        //  3896: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardVerResult:()Ljava/lang/String;
        //  3899: astore          other$cardVerResult
        //  3901: aload           this$cardVerResult
        //  3903: ifnonnull       3914
        //  3906: aload           other$cardVerResult
        //  3908: ifnull          3926
        //  3911: goto            3924
        //  3914: aload           this$cardVerResult
        //  3916: aload           other$cardVerResult
        //  3918: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3921: ifne            3926
        //  3924: iconst_0       
        //  3925: ireturn        
        //  3926: aload_0         /* this */
        //  3927: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardSeqNumber:()Ljava/lang/String;
        //  3930: astore          this$cardSeqNumber
        //  3932: aload_2         /* other */
        //  3933: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardSeqNumber:()Ljava/lang/String;
        //  3936: astore          other$cardSeqNumber
        //  3938: aload           this$cardSeqNumber
        //  3940: ifnonnull       3951
        //  3943: aload           other$cardSeqNumber
        //  3945: ifnull          3963
        //  3948: goto            3961
        //  3951: aload           this$cardSeqNumber
        //  3953: aload           other$cardSeqNumber
        //  3955: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3958: ifne            3963
        //  3961: iconst_0       
        //  3962: ireturn        
        //  3963: aload_0         /* this */
        //  3964: invokevirtual   com/empay/entities/PosTxnWorkEntity.getExpiryDate:()Ljava/lang/String;
        //  3967: astore          this$expiryDate
        //  3969: aload_2         /* other */
        //  3970: invokevirtual   com/empay/entities/PosTxnWorkEntity.getExpiryDate:()Ljava/lang/String;
        //  3973: astore          other$expiryDate
        //  3975: aload           this$expiryDate
        //  3977: ifnonnull       3988
        //  3980: aload           other$expiryDate
        //  3982: ifnull          4000
        //  3985: goto            3998
        //  3988: aload           this$expiryDate
        //  3990: aload           other$expiryDate
        //  3992: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  3995: ifne            4000
        //  3998: iconst_0       
        //  3999: ireturn        
        //  4000: aload_0         /* this */
        //  4001: invokevirtual   com/empay/entities/PosTxnWorkEntity.getServiceCode:()Ljava/lang/String;
        //  4004: astore          this$serviceCode
        //  4006: aload_2         /* other */
        //  4007: invokevirtual   com/empay/entities/PosTxnWorkEntity.getServiceCode:()Ljava/lang/String;
        //  4010: astore          other$serviceCode
        //  4012: aload           this$serviceCode
        //  4014: ifnonnull       4025
        //  4017: aload           other$serviceCode
        //  4019: ifnull          4037
        //  4022: goto            4035
        //  4025: aload           this$serviceCode
        //  4027: aload           other$serviceCode
        //  4029: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4032: ifne            4037
        //  4035: iconst_0       
        //  4036: ireturn        
        //  4037: aload_0         /* this */
        //  4038: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMcc:()Ljava/lang/String;
        //  4041: astore          this$mcc
        //  4043: aload_2         /* other */
        //  4044: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMcc:()Ljava/lang/String;
        //  4047: astore          other$mcc
        //  4049: aload           this$mcc
        //  4051: ifnonnull       4062
        //  4054: aload           other$mcc
        //  4056: ifnull          4074
        //  4059: goto            4072
        //  4062: aload           this$mcc
        //  4064: aload           other$mcc
        //  4066: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4069: ifne            4074
        //  4072: iconst_0       
        //  4073: ireturn        
        //  4074: aload_0         /* this */
        //  4075: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMeName:()Ljava/lang/String;
        //  4078: astore          this$meName
        //  4080: aload_2         /* other */
        //  4081: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMeName:()Ljava/lang/String;
        //  4084: astore          other$meName
        //  4086: aload           this$meName
        //  4088: ifnonnull       4099
        //  4091: aload           other$meName
        //  4093: ifnull          4111
        //  4096: goto            4109
        //  4099: aload           this$meName
        //  4101: aload           other$meName
        //  4103: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4106: ifne            4111
        //  4109: iconst_0       
        //  4110: ireturn        
        //  4111: aload_0         /* this */
        //  4112: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMeCity:()Ljava/lang/String;
        //  4115: astore          this$meCity
        //  4117: aload_2         /* other */
        //  4118: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMeCity:()Ljava/lang/String;
        //  4121: astore          other$meCity
        //  4123: aload           this$meCity
        //  4125: ifnonnull       4136
        //  4128: aload           other$meCity
        //  4130: ifnull          4148
        //  4133: goto            4146
        //  4136: aload           this$meCity
        //  4138: aload           other$meCity
        //  4140: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4143: ifne            4148
        //  4146: iconst_0       
        //  4147: ireturn        
        //  4148: aload_0         /* this */
        //  4149: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMeCountry:()Ljava/lang/String;
        //  4152: astore          this$meCountry
        //  4154: aload_2         /* other */
        //  4155: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMeCountry:()Ljava/lang/String;
        //  4158: astore          other$meCountry
        //  4160: aload           this$meCountry
        //  4162: ifnonnull       4173
        //  4165: aload           other$meCountry
        //  4167: ifnull          4185
        //  4170: goto            4183
        //  4173: aload           this$meCountry
        //  4175: aload           other$meCountry
        //  4177: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4180: ifne            4185
        //  4183: iconst_0       
        //  4184: ireturn        
        //  4185: aload_0         /* this */
        //  4186: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMePinCode:()Ljava/lang/String;
        //  4189: astore          this$mePinCode
        //  4191: aload_2         /* other */
        //  4192: invokevirtual   com/empay/entities/PosTxnWorkEntity.getMePinCode:()Ljava/lang/String;
        //  4195: astore          other$mePinCode
        //  4197: aload           this$mePinCode
        //  4199: ifnonnull       4210
        //  4202: aload           other$mePinCode
        //  4204: ifnull          4222
        //  4207: goto            4220
        //  4210: aload           this$mePinCode
        //  4212: aload           other$mePinCode
        //  4214: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4217: ifne            4222
        //  4220: iconst_0       
        //  4221: ireturn        
        //  4222: aload_0         /* this */
        //  4223: invokevirtual   com/empay/entities/PosTxnWorkEntity.getNetwork:()Ljava/lang/String;
        //  4226: astore          this$network
        //  4228: aload_2         /* other */
        //  4229: invokevirtual   com/empay/entities/PosTxnWorkEntity.getNetwork:()Ljava/lang/String;
        //  4232: astore          other$network
        //  4234: aload           this$network
        //  4236: ifnonnull       4247
        //  4239: aload           other$network
        //  4241: ifnull          4259
        //  4244: goto            4257
        //  4247: aload           this$network
        //  4249: aload           other$network
        //  4251: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4254: ifne            4259
        //  4257: iconst_0       
        //  4258: ireturn        
        //  4259: aload_0         /* this */
        //  4260: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlDate:()Ljava/time/LocalDateTime;
        //  4263: astore          this$setlDate
        //  4265: aload_2         /* other */
        //  4266: invokevirtual   com/empay/entities/PosTxnWorkEntity.getSetlDate:()Ljava/time/LocalDateTime;
        //  4269: astore          other$setlDate
        //  4271: aload           this$setlDate
        //  4273: ifnonnull       4284
        //  4276: aload           other$setlDate
        //  4278: ifnull          4296
        //  4281: goto            4294
        //  4284: aload           this$setlDate
        //  4286: aload           other$setlDate
        //  4288: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4291: ifne            4296
        //  4294: iconst_0       
        //  4295: ireturn        
        //  4296: aload_0         /* this */
        //  4297: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIrd:()Ljava/lang/String;
        //  4300: astore          this$ird
        //  4302: aload_2         /* other */
        //  4303: invokevirtual   com/empay/entities/PosTxnWorkEntity.getIrd:()Ljava/lang/String;
        //  4306: astore          other$ird
        //  4308: aload           this$ird
        //  4310: ifnonnull       4321
        //  4313: aload           other$ird
        //  4315: ifnull          4333
        //  4318: goto            4331
        //  4321: aload           this$ird
        //  4323: aload           other$ird
        //  4325: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4328: ifne            4333
        //  4331: iconst_0       
        //  4332: ireturn        
        //  4333: aload_0         /* this */
        //  4334: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBanKCode:()Ljava/lang/String;
        //  4337: astore          this$banKCode
        //  4339: aload_2         /* other */
        //  4340: invokevirtual   com/empay/entities/PosTxnWorkEntity.getBanKCode:()Ljava/lang/String;
        //  4343: astore          other$banKCode
        //  4345: aload           this$banKCode
        //  4347: ifnonnull       4358
        //  4350: aload           other$banKCode
        //  4352: ifnull          4370
        //  4355: goto            4368
        //  4358: aload           this$banKCode
        //  4360: aload           other$banKCode
        //  4362: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4365: ifne            4370
        //  4368: iconst_0       
        //  4369: ireturn        
        //  4370: aload_0         /* this */
        //  4371: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardCategory:()Ljava/lang/String;
        //  4374: astore          this$cardCategory
        //  4376: aload_2         /* other */
        //  4377: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardCategory:()Ljava/lang/String;
        //  4380: astore          other$cardCategory
        //  4382: aload           this$cardCategory
        //  4384: ifnonnull       4395
        //  4387: aload           other$cardCategory
        //  4389: ifnull          4407
        //  4392: goto            4405
        //  4395: aload           this$cardCategory
        //  4397: aload           other$cardCategory
        //  4399: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4402: ifne            4407
        //  4405: iconst_0       
        //  4406: ireturn        
        //  4407: aload_0         /* this */
        //  4408: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardSubCategory:()Ljava/lang/String;
        //  4411: astore          this$cardSubCategory
        //  4413: aload_2         /* other */
        //  4414: invokevirtual   com/empay/entities/PosTxnWorkEntity.getCardSubCategory:()Ljava/lang/String;
        //  4417: astore          other$cardSubCategory
        //  4419: aload           this$cardSubCategory
        //  4421: ifnonnull       4432
        //  4424: aload           other$cardSubCategory
        //  4426: ifnull          4444
        //  4429: goto            4442
        //  4432: aload           this$cardSubCategory
        //  4434: aload           other$cardSubCategory
        //  4436: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4439: ifne            4444
        //  4442: iconst_0       
        //  4443: ireturn        
        //  4444: aload_0         /* this */
        //  4445: invokevirtual   com/empay/entities/PosTxnWorkEntity.getEmvData:()Ljava/lang/String;
        //  4448: astore          this$emvData
        //  4450: aload_2         /* other */
        //  4451: invokevirtual   com/empay/entities/PosTxnWorkEntity.getEmvData:()Ljava/lang/String;
        //  4454: astore          other$emvData
        //  4456: aload           this$emvData
        //  4458: ifnonnull       4469
        //  4461: aload           other$emvData
        //  4463: ifnull          4481
        //  4466: goto            4479
        //  4469: aload           this$emvData
        //  4471: aload           other$emvData
        //  4473: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4476: ifne            4481
        //  4479: iconst_0       
        //  4480: ireturn        
        //  4481: aload_0         /* this */
        //  4482: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_txnDateTime:()Ljava/lang/String;
        //  4485: astore          this$v_txnDateTime
        //  4487: aload_2         /* other */
        //  4488: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_txnDateTime:()Ljava/lang/String;
        //  4491: astore          other$v_txnDateTime
        //  4493: aload           this$v_txnDateTime
        //  4495: ifnonnull       4506
        //  4498: aload           other$v_txnDateTime
        //  4500: ifnull          4518
        //  4503: goto            4516
        //  4506: aload           this$v_txnDateTime
        //  4508: aload           other$v_txnDateTime
        //  4510: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4513: ifne            4518
        //  4516: iconst_0       
        //  4517: ireturn        
        //  4518: aload_0         /* this */
        //  4519: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_localTxnTime:()Ljava/lang/String;
        //  4522: astore          this$v_localTxnTime
        //  4524: aload_2         /* other */
        //  4525: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_localTxnTime:()Ljava/lang/String;
        //  4528: astore          other$v_localTxnTime
        //  4530: aload           this$v_localTxnTime
        //  4532: ifnonnull       4543
        //  4535: aload           other$v_localTxnTime
        //  4537: ifnull          4555
        //  4540: goto            4553
        //  4543: aload           this$v_localTxnTime
        //  4545: aload           other$v_localTxnTime
        //  4547: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4550: ifne            4555
        //  4553: iconst_0       
        //  4554: ireturn        
        //  4555: aload_0         /* this */
        //  4556: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_localTxnDate:()Ljava/lang/String;
        //  4559: astore          this$v_localTxnDate
        //  4561: aload_2         /* other */
        //  4562: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_localTxnDate:()Ljava/lang/String;
        //  4565: astore          other$v_localTxnDate
        //  4567: aload           this$v_localTxnDate
        //  4569: ifnonnull       4580
        //  4572: aload           other$v_localTxnDate
        //  4574: ifnull          4592
        //  4577: goto            4590
        //  4580: aload           this$v_localTxnDate
        //  4582: aload           other$v_localTxnDate
        //  4584: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4587: ifne            4592
        //  4590: iconst_0       
        //  4591: ireturn        
        //  4592: aload_0         /* this */
        //  4593: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDe61:()Ljava/lang/String;
        //  4596: astore          this$de61
        //  4598: aload_2         /* other */
        //  4599: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDe61:()Ljava/lang/String;
        //  4602: astore          other$de61
        //  4604: aload           this$de61
        //  4606: ifnonnull       4617
        //  4609: aload           other$de61
        //  4611: ifnull          4629
        //  4614: goto            4627
        //  4617: aload           this$de61
        //  4619: aload           other$de61
        //  4621: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4624: ifne            4629
        //  4627: iconst_0       
        //  4628: ireturn        
        //  4629: aload_0         /* this */
        //  4630: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDe62:()Ljava/lang/String;
        //  4633: astore          this$de62
        //  4635: aload_2         /* other */
        //  4636: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDe62:()Ljava/lang/String;
        //  4639: astore          other$de62
        //  4641: aload           this$de62
        //  4643: ifnonnull       4654
        //  4646: aload           other$de62
        //  4648: ifnull          4666
        //  4651: goto            4664
        //  4654: aload           this$de62
        //  4656: aload           other$de62
        //  4658: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4661: ifne            4666
        //  4664: iconst_0       
        //  4665: ireturn        
        //  4666: aload_0         /* this */
        //  4667: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDe63:()Ljava/lang/String;
        //  4670: astore          this$de63
        //  4672: aload_2         /* other */
        //  4673: invokevirtual   com/empay/entities/PosTxnWorkEntity.getDe63:()Ljava/lang/String;
        //  4676: astore          other$de63
        //  4678: aload           this$de63
        //  4680: ifnonnull       4691
        //  4683: aload           other$de63
        //  4685: ifnull          4703
        //  4688: goto            4701
        //  4691: aload           this$de63
        //  4693: aload           other$de63
        //  4695: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4698: ifne            4703
        //  4701: iconst_0       
        //  4702: ireturn        
        //  4703: aload_0         /* this */
        //  4704: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOprtEnvDe61_1:()Ljava/lang/String;
        //  4707: astore          this$oprtEnvDe61_1
        //  4709: aload_2         /* other */
        //  4710: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOprtEnvDe61_1:()Ljava/lang/String;
        //  4713: astore_w        256
        //  4717: aload           this$oprtEnvDe61_1
        //  4719: ifnonnull       4732
        //  4722: aload_w         other$oprtEnvDe61_1
        //  4726: ifnull          4746
        //  4729: goto            4744
        //  4732: aload           this$oprtEnvDe61_1
        //  4734: aload_w         other$oprtEnvDe61_1
        //  4738: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4741: ifne            4746
        //  4744: iconst_0       
        //  4745: ireturn        
        //  4746: aload_0         /* this */
        //  4747: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOprtEnvDe61_3:()Ljava/lang/String;
        //  4750: astore_w        257
        //  4754: aload_2         /* other */
        //  4755: invokevirtual   com/empay/entities/PosTxnWorkEntity.getOprtEnvDe61_3:()Ljava/lang/String;
        //  4758: astore_w        258
        //  4762: aload_w         this$oprtEnvDe61_3
        //  4766: ifnonnull       4779
        //  4769: aload_w         other$oprtEnvDe61_3
        //  4773: ifnull          4795
        //  4776: goto            4793
        //  4779: aload_w         this$oprtEnvDe61_3
        //  4783: aload_w         other$oprtEnvDe61_3
        //  4787: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4790: ifne            4795
        //  4793: iconst_0       
        //  4794: ireturn        
        //  4795: aload_0         /* this */
        //  4796: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_setlDate:()Ljava/lang/String;
        //  4799: astore_w        259
        //  4803: aload_2         /* other */
        //  4804: invokevirtual   com/empay/entities/PosTxnWorkEntity.getV_setlDate:()Ljava/lang/String;
        //  4807: astore_w        260
        //  4811: aload_w         this$v_setlDate
        //  4815: ifnonnull       4828
        //  4818: aload_w         other$v_setlDate
        //  4822: ifnull          4844
        //  4825: goto            4842
        //  4828: aload_w         this$v_setlDate
        //  4832: aload_w         other$v_setlDate
        //  4836: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //  4839: ifne            4844
        //  4842: iconst_0       
        //  4843: ireturn        
        //  4844: iconst_1       
        //  4845: ireturn        
        //    MethodParameters:
        //  Name  Flags  
        //  ----  -----
        //  o     FINAL
        //    StackMapTable: 01 87 07 08 FC 00 0E 07 00 08 0C FD 00 16 07 00 02 07 00 02 08 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 18 07 00 02 07 00 02 09 01 FD 00 1C 07 00 02 07 00 02 0B 01 FD 00 20 07 00 02 07 00 02 0D 01 FD 00 20 07 00 02 07 00 02 0D 01
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
        return other instanceof PosTxnWorkEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUser();
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $pid = this.getPid();
        result = result * 59 + (($pid == null) ? 43 : $pid.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $jobNumber = this.getJobNumber();
        result = result * 59 + (($jobNumber == null) ? 43 : $jobNumber.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $stagingFlag = this.getStagingFlag();
        result = result * 59 + (($stagingFlag == null) ? 43 : $stagingFlag.hashCode());
        final Object $paymentFlag = this.getPaymentFlag();
        result = result * 59 + (($paymentFlag == null) ? 43 : $paymentFlag.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $groupSerNumber = this.getGroupSerNumber();
        result = result * 59 + (($groupSerNumber == null) ? 43 : $groupSerNumber.hashCode());
        final Object $smeSerNumber = this.getSmeSerNumber();
        result = result * 59 + (($smeSerNumber == null) ? 43 : $smeSerNumber.hashCode());
        final Object $agrSernumber = this.getAgrSernumber();
        result = result * 59 + (($agrSernumber == null) ? 43 : $agrSernumber.hashCode());
        final Object $convRate = this.getConvRate();
        result = result * 59 + (($convRate == null) ? 43 : $convRate.hashCode());
        final Object $authType = this.getAuthType();
        result = result * 59 + (($authType == null) ? 43 : $authType.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + (($txnFeeAmount == null) ? 43 : $txnFeeAmount.hashCode());
        final Object $setlAmount = this.getSetlAmount();
        result = result * 59 + (($setlAmount == null) ? 43 : $setlAmount.hashCode());
        final Object $tipAmount = this.getTipAmount();
        result = result * 59 + (($tipAmount == null) ? 43 : $tipAmount.hashCode());
        final Object $authAmount = this.getAuthAmount();
        result = result * 59 + (($authAmount == null) ? 43 : $authAmount.hashCode());
        final Object $replaceAmount = this.getReplaceAmount();
        result = result * 59 + (($replaceAmount == null) ? 43 : $replaceAmount.hashCode());
        final Object $chipCashBack = this.getChipCashBack();
        result = result * 59 + (($chipCashBack == null) ? 43 : $chipCashBack.hashCode());
        final Object $cardAuthCode = this.getCardAuthCode();
        result = result * 59 + (($cardAuthCode == null) ? 43 : $cardAuthCode.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $cardInputAbility = this.getCardInputAbility();
        result = result * 59 + (($cardInputAbility == null) ? 43 : $cardInputAbility.hashCode());
        final Object $chAuthAbility = this.getChAuthAbility();
        result = result * 59 + (($chAuthAbility == null) ? 43 : $chAuthAbility.hashCode());
        final Object $cardCaptureAbility = this.getCardCaptureAbility();
        result = result * 59 + (($cardCaptureAbility == null) ? 43 : $cardCaptureAbility.hashCode());
        final Object $oprtEnvironment = this.getOprtEnvironment();
        result = result * 59 + (($oprtEnvironment == null) ? 43 : $oprtEnvironment.hashCode());
        final Object $chPresent = this.getChPresent();
        result = result * 59 + (($chPresent == null) ? 43 : $chPresent.hashCode());
        final Object $cardPresent = this.getCardPresent();
        result = result * 59 + (($cardPresent == null) ? 43 : $cardPresent.hashCode());
        final Object $cardInputMode = this.getCardInputMode();
        result = result * 59 + (($cardInputMode == null) ? 43 : $cardInputMode.hashCode());
        final Object $chAuthMethod = this.getChAuthMethod();
        result = result * 59 + (($chAuthMethod == null) ? 43 : $chAuthMethod.hashCode());
        final Object $chAuthEntity = this.getChAuthEntity();
        result = result * 59 + (($chAuthEntity == null) ? 43 : $chAuthEntity.hashCode());
        final Object $cardOutPutAbility = this.getCardOutPutAbility();
        result = result * 59 + (($cardOutPutAbility == null) ? 43 : $cardOutPutAbility.hashCode());
        final Object $trlOutPutAbility = this.getTrlOutPutAbility();
        result = result * 59 + (($trlOutPutAbility == null) ? 43 : $trlOutPutAbility.hashCode());
        final Object $pinCaptureAbility = this.getPinCaptureAbility();
        result = result * 59 + (($pinCaptureAbility == null) ? 43 : $pinCaptureAbility.hashCode());
        final Object $batchNumber = this.getBatchNumber();
        result = result * 59 + (($batchNumber == null) ? 43 : $batchNumber.hashCode());
        final Object $trlBatchNumber = this.getTrlBatchNumber();
        result = result * 59 + (($trlBatchNumber == null) ? 43 : $trlBatchNumber.hashCode());
        final Object $setlCode = this.getSetlCode();
        result = result * 59 + (($setlCode == null) ? 43 : $setlCode.hashCode());
        final Object $cryptAmount = this.getCryptAmount();
        result = result * 59 + (($cryptAmount == null) ? 43 : $cryptAmount.hashCode());
        final Object $irdSerNumber = this.getIrdSerNumber();
        result = result * 59 + (($irdSerNumber == null) ? 43 : $irdSerNumber.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $netAmount = this.getNetAmount();
        result = result * 59 + (($netAmount == null) ? 43 : $netAmount.hashCode());
        final Object $bdfNetAmount = this.getBdfNetAmount();
        result = result * 59 + (($bdfNetAmount == null) ? 43 : $bdfNetAmount.hashCode());
        final Object $pdfNetAmount = this.getPdfNetAmount();
        result = result * 59 + (($pdfNetAmount == null) ? 43 : $pdfNetAmount.hashCode());
        final Object $revIndiCator = this.getRevIndiCator();
        result = result * 59 + (($revIndiCator == null) ? 43 : $revIndiCator.hashCode());
        final Object $onusOffusFlag = this.getOnusOffusFlag();
        result = result * 59 + (($onusOffusFlag == null) ? 43 : $onusOffusFlag.hashCode());
        final Object $setlJobNumber = this.getSetlJobNumber();
        result = result * 59 + (($setlJobNumber == null) ? 43 : $setlJobNumber.hashCode());
        final Object $holdPaymentFlag = this.getHoldPaymentFlag();
        result = result * 59 + (($holdPaymentFlag == null) ? 43 : $holdPaymentFlag.hashCode());
        final Object $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + (($dmsSmsMode == null) ? 43 : $dmsSmsMode.hashCode());
        final Object $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + (($settlementIndicator == null) ? 43 : $settlementIndicator.hashCode());
        final Object $lastUpdatedDate = this.getLastUpdatedDate();
        result = result * 59 + (($lastUpdatedDate == null) ? 43 : $lastUpdatedDate.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $centreProcDate = this.getCentreProcDate();
        result = result * 59 + (($centreProcDate == null) ? 43 : $centreProcDate.hashCode());
        final Object $bussDate = this.getBussDate();
        result = result * 59 + (($bussDate == null) ? 43 : $bussDate.hashCode());
        final Object $txnUniqueId = this.getTxnUniqueId();
        result = result * 59 + (($txnUniqueId == null) ? 43 : $txnUniqueId.hashCode());
        final Object $msgTypeId = this.getMsgTypeId();
        result = result * 59 + (($msgTypeId == null) ? 43 : $msgTypeId.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
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
        final Object $txnId = this.getTxnId();
        result = result * 59 + (($txnId == null) ? 43 : $txnId.hashCode());
        final Object $txnDateTime = this.getTxnDateTime();
        result = result * 59 + (($txnDateTime == null) ? 43 : $txnDateTime.hashCode());
        final Object $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + (($acqinstIdCode == null) ? 43 : $acqinstIdCode.hashCode());
        final Object $acqInstConCode = this.getAcqInstConCode();
        result = result * 59 + (($acqInstConCode == null) ? 43 : $acqInstConCode.hashCode());
        final Object $fwdInstIdCode = this.getFwdInstIdCode();
        result = result * 59 + (($fwdInstIdCode == null) ? 43 : $fwdInstIdCode.hashCode());
        final Object $recInstConCode = this.getRecInstConCode();
        result = result * 59 + (($recInstConCode == null) ? 43 : $recInstConCode.hashCode());
        final Object $adtlPrivateData = this.getAdtlPrivateData();
        result = result * 59 + (($adtlPrivateData == null) ? 43 : $adtlPrivateData.hashCode());
        final Object $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + (($msgReasonCode == null) ? 43 : $msgReasonCode.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $txnFeeCurCode = this.getTxnFeeCurCode();
        result = result * 59 + (($txnFeeCurCode == null) ? 43 : $txnFeeCurCode.hashCode());
        final Object $setlCurCode = this.getSetlCurCode();
        result = result * 59 + (($setlCurCode == null) ? 43 : $setlCurCode.hashCode());
        final Object $adtlAmounts = this.getAdtlAmounts();
        result = result * 59 + (($adtlAmounts == null) ? 43 : $adtlAmounts.hashCode());
        final Object $adviceReasonCode = this.getAdviceReasonCode();
        result = result * 59 + (($adviceReasonCode == null) ? 43 : $adviceReasonCode.hashCode());
        final Object $infData = this.getInfData();
        result = result * 59 + (($infData == null) ? 43 : $infData.hashCode());
        final Object $networkData = this.getNetworkData();
        result = result * 59 + (($networkData == null) ? 43 : $networkData.hashCode());
        final Object $appIdentifier = this.getAppIdentifier();
        result = result * 59 + (($appIdentifier == null) ? 43 : $appIdentifier.hashCode());
        final Object $appICProfile = this.getAppICProfile();
        result = result * 59 + (($appICProfile == null) ? 43 : $appICProfile.hashCode());
        final Object $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + (($appTxnCounter == null) ? 43 : $appTxnCounter.hashCode());
        final Object $appCryptogram = this.getAppCryptogram();
        result = result * 59 + (($appCryptogram == null) ? 43 : $appCryptogram.hashCode());
        final Object $cryptInfoData = this.getCryptInfoData();
        result = result * 59 + (($cryptInfoData == null) ? 43 : $cryptInfoData.hashCode());
        final Object $cvmResult = this.getCvmResult();
        result = result * 59 + (($cvmResult == null) ? 43 : $cvmResult.hashCode());
        final Object $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + (($ifdSerNumber == null) ? 43 : $ifdSerNumber.hashCode());
        final Object $issAppData = this.getIssAppData();
        result = result * 59 + (($issAppData == null) ? 43 : $issAppData.hashCode());
        final Object $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + (($trlAppVerNumber == null) ? 43 : $trlAppVerNumber.hashCode());
        final Object $trlCapabilities = this.getTrlCapabilities();
        result = result * 59 + (($trlCapabilities == null) ? 43 : $trlCapabilities.hashCode());
        final Object $trlConCode = this.getTrlConCode();
        result = result * 59 + (($trlConCode == null) ? 43 : $trlConCode.hashCode());
        final Object $terminalType = this.getTerminalType();
        result = result * 59 + (($terminalType == null) ? 43 : $terminalType.hashCode());
        final Object $trlVerResult = this.getTrlVerResult();
        result = result * 59 + (($trlVerResult == null) ? 43 : $trlVerResult.hashCode());
        final Object $tcc = this.getTcc();
        result = result * 59 + (($tcc == null) ? 43 : $tcc.hashCode());
        final Object $chipCurCode = this.getChipCurCode();
        result = result * 59 + (($chipCurCode == null) ? 43 : $chipCurCode.hashCode());
        final Object $chiptxnDate = this.getChiptxnDate();
        result = result * 59 + (($chiptxnDate == null) ? 43 : $chiptxnDate.hashCode());
        final Object $chipTxnDate = this.getChiptxnDate();
        result = result * 59 + (($chipTxnDate == null) ? 43 : $chipTxnDate.hashCode());
        final Object $chipTrlType = this.getChipTrlType();
        result = result * 59 + (($chipTrlType == null) ? 43 : $chipTrlType.hashCode());
        final Object $upblNumber = this.getUpblNumber();
        result = result * 59 + (($upblNumber == null) ? 43 : $upblNumber.hashCode());
        final Object $issAuthData = this.getIssAuthData();
        result = result * 59 + (($issAuthData == null) ? 43 : $issAuthData.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $posConditionCode = this.getPosConditionCode();
        result = result * 59 + (($posConditionCode == null) ? 43 : $posConditionCode.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $cardVerResult = this.getCardVerResult();
        result = result * 59 + (($cardVerResult == null) ? 43 : $cardVerResult.hashCode());
        final Object $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + (($cardSeqNumber == null) ? 43 : $cardSeqNumber.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * 59 + (($expiryDate == null) ? 43 : $expiryDate.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $mePinCode = this.getMePinCode();
        result = result * 59 + (($mePinCode == null) ? 43 : $mePinCode.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $setlDate = this.getSetlDate();
        result = result * 59 + (($setlDate == null) ? 43 : $setlDate.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        final Object $banKCode = this.getBanKCode();
        result = result * 59 + (($banKCode == null) ? 43 : $banKCode.hashCode());
        final Object $cardCategory = this.getCardCategory();
        result = result * 59 + (($cardCategory == null) ? 43 : $cardCategory.hashCode());
        final Object $cardSubCategory = this.getCardSubCategory();
        result = result * 59 + (($cardSubCategory == null) ? 43 : $cardSubCategory.hashCode());
        final Object $emvData = this.getEmvData();
        result = result * 59 + (($emvData == null) ? 43 : $emvData.hashCode());
        final Object $v_txnDateTime = this.getV_txnDateTime();
        result = result * 59 + (($v_txnDateTime == null) ? 43 : $v_txnDateTime.hashCode());
        final Object $v_localTxnTime = this.getV_localTxnTime();
        result = result * 59 + (($v_localTxnTime == null) ? 43 : $v_localTxnTime.hashCode());
        final Object $v_localTxnDate = this.getV_localTxnDate();
        result = result * 59 + (($v_localTxnDate == null) ? 43 : $v_localTxnDate.hashCode());
        final Object $de61 = this.getDe61();
        result = result * 59 + (($de61 == null) ? 43 : $de61.hashCode());
        final Object $de62 = this.getDe62();
        result = result * 59 + (($de62 == null) ? 43 : $de62.hashCode());
        final Object $de63 = this.getDe63();
        result = result * 59 + (($de63 == null) ? 43 : $de63.hashCode());
        final Object $oprtEnvDe61_1 = this.getOprtEnvDe61_1();
        result = result * 59 + (($oprtEnvDe61_1 == null) ? 43 : $oprtEnvDe61_1.hashCode());
        final Object $oprtEnvDe61_2 = this.getOprtEnvDe61_3();
        result = result * 59 + (($oprtEnvDe61_2 == null) ? 43 : $oprtEnvDe61_2.hashCode());
        final Object $v_setlDate = this.getV_setlDate();
        result = result * 59 + (($v_setlDate == null) ? 43 : $v_setlDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "" + ("PosTxnWorkEntity(serialNumber=" + this.getSerialNumber() + ", pid=" + this.getPid() + ", insCode=" + this.getInsCode() + ", lastUpdatedDate=" + String.valueOf(this.getLastUpdatedDate()) + ", user=" + this.getUser() + ", cardNumber=" + this.getCardNumber() + ", intCode=" + this.getIntCode() + ", jobNumber=" + this.getJobNumber() + ", genStatus=" + this.getGenStatus() + ", stagingFlag=" + this.getStagingFlag() + ", paymentFlag=" + this.getPaymentFlag() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", bussDate=" + String.valueOf(this.getBussDate()) + ", txnUniqueId=" + this.getTxnUniqueId() + ", msgTypeId=" + this.getMsgTypeId() + ", txnAmount=" + this.getTxnAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", responseCode=" + this.getResponseCode() + ", rrn=" + this.getRrn() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", groupSerNumber=" + this.getGroupSerNumber() + ", smeSerNumber=" + this.getSmeSerNumber() + ", agrSernumber=" + this.getAgrSernumber() + ", txnType=" + this.getTxnType() + ", procCode=" + this.getProcCode() + ", convRate=" + this.getConvRate() + ", stan=" + this.getStan() + ", authType=" + this.getAuthType() + ", authReason=" + this.getAuthReason() + ", txnId=" + this.getTxnId() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", acqInstConCode=" + this.getAcqInstConCode() + ", fwdInstIdCode=" + this.getFwdInstIdCode() + ", recInstConCode=" + this.getRecInstConCode() + ", adtlPrivateData=" + this.getAdtlPrivateData() + ", msgReasonCode=" + this.getMsgReasonCode() + ", cashBackAmount=" + this.getCashBackAmount() + ", txnCurCode=" + this.getTxnCurCode() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", txnFeeCurCode=" + this.getTxnFeeCurCode() + ", setlAmount=" + this.getSetlAmount() + ", tipAmount=" + this.getTipAmount() + ", setlCurCode=" + this.getSetlCurCode() + ", adtlAmounts=" + this.getAdtlAmounts() + ", authAmount=" + this.getAuthAmount() + ", replaceAmount=" + this.getReplaceAmount() + ", adviceReasonCode=" + this.getAdviceReasonCode() + ", infData=" + this.getInfData() + ", networkData=" + this.getNetworkData() + ", appIdentifier=" + this.getAppIdentifier() + ", appICProfile=" + this.getAppICProfile() + ", appTxnCounter=" + this.getAppTxnCounter() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", cvmResult=" + this.getCvmResult() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", issAppData=" + this.getIssAppData() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", trlCapabilities=" + this.getTrlCapabilities() + ", trlConCode=" + this.getTrlConCode() + ", terminalType=" + this.getTerminalType() + ", trlVerResult=" + this.getTrlVerResult() + ", tcc=" + this.getTcc() + ", chipCurCode=" + this.getChipCurCode() + ", chiptxnDate=" + String.valueOf(this.getChiptxnDate()) + ", chipTxnDate=" + String.valueOf(this.getChiptxnDate()) + ", chipTrlType=" + this.getChipTrlType() + ", chipCashBack=" + this.getChipCashBack() + ", upblNumber=" + this.getUpblNumber() + ", cardAuthCode=" + this.getCardAuthCode() + ", issAuthData=" + this.getIssAuthData() + ", posEntryMode=" + this.getPosEntryMode() + ", posConditionCode=" + this.getPosConditionCode() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", cardVerResult=" + this.getCardVerResult() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", expiryDate=" + this.getExpiryDate() + ", serviceCode=" + this.getServiceCode() + ", mcc=" + this.getMcc() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", mePinCode=" + this.getMePinCode() + ", network=" + this.getNetwork() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", cardInputAbility=" + this.getCardInputAbility() + ", chAuthAbility=" + this.getChAuthAbility() + ", cardCaptureAbility=" + this.getCardCaptureAbility() + ", oprtEnvironment=" + this.getOprtEnvironment() + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent() + ", cardInputMode=" + this.getCardInputMode() + ", chAuthMethod=" + this.getChAuthMethod() + ", chAuthEntity=" + this.getChAuthEntity() + ", cardOutPutAbility=" + this.getCardOutPutAbility() + ", trlOutPutAbility=" + this.getTrlOutPutAbility() + ", pinCaptureAbility=") + /* invokedynamic(!) */ProcyonInvokeDynamicHelper_3.invoke(this.getPinCaptureAbility(), this.getBatchNumber(), this.getTrlBatchNumber(), String.valueOf(this.getSetlDate()), this.getSetlCode(), this.getCryptAmount(), this.getIrd(), this.getIrdSerNumber(), this.getIrfFixed(), this.getBanKCode(), this.getNetAmount(), this.getBdfNetAmount(), this.getPdfNetAmount(), this.getRevIndiCator(), this.getOnusOffusFlag(), this.getSetlJobNumber(), this.getHoldPaymentFlag(), this.getDmsSmsMode(), this.getCardCategory(), this.getCardSubCategory(), this.getEmvData(), this.getV_txnDateTime(), this.getV_localTxnTime(), this.getV_localTxnDate(), this.getDe61(), this.getDe62(), this.getDe63(), this.getSettlementIndicator(), this.getOprtEnvDe61_1(), this.getOprtEnvDe61_3(), this.getV_setlDate());
    }
    
    public static class PosTxnWorkEntityBuilder
    {
        private Integer serialNumber;
        private Integer pid;
        private Integer insCode;
        private LocalDateTime lastUpdatedDate;
        private int user;
        private String cardNumber;
        private Integer intCode;
        private Integer jobNumber;
        private Integer genStatus;
        private Character stagingFlag;
        private Character paymentFlag;
        private LocalDate centreProcDate;
        private LocalDate bussDate;
        private String txnUniqueId;
        private String msgTypeId;
        private Double txnAmount;
        private LocalDateTime localDateTime;
        private String responseCode;
        private String rrn;
        private String terminalId;
        private String merchantId;
        private Integer groupSerNumber;
        private Integer smeSerNumber;
        private Integer agrSernumber;
        private String txnType;
        private String procCode;
        private Double convRate;
        private String stan;
        private Integer authType;
        private String authReason;
        private String txnId;
        private LocalDateTime txnDateTime;
        private String acqinstIdCode;
        private String acqInstConCode;
        private String fwdInstIdCode;
        private String recInstConCode;
        private String adtlPrivateData;
        private String msgReasonCode;
        private Double cashBackAmount;
        private String txnCurCode;
        private Double txnFeeAmount;
        private String txnFeeCurCode;
        private Double setlAmount;
        private Double tipAmount;
        private String setlCurCode;
        private String adtlAmounts;
        private Double authAmount;
        private Double replaceAmount;
        private String adviceReasonCode;
        private String infData;
        private String networkData;
        private String appIdentifier;
        private String appICProfile;
        private String appTxnCounter;
        private String appCryptogram;
        private String cryptInfoData;
        private String cvmResult;
        private String ifdSerNumber;
        private String issAppData;
        private String trlAppVerNumber;
        private String trlCapabilities;
        private String trlConCode;
        private String terminalType;
        private String trlVerResult;
        private String tcc;
        private String chipCurCode;
        private LocalDate chiptxnDate;
        private String chipTxnDate;
        private String chipTrlType;
        private Double chipCashBack;
        private String upblNumber;
        private Character cardAuthCode;
        private String issAuthData;
        private String posEntryMode;
        private String posConditionCode;
        private String motoEcomIndicator;
        private String cardVerResult;
        private String cardSeqNumber;
        private String expiryDate;
        private String serviceCode;
        private String mcc;
        private String meName;
        private String meCity;
        private String meCountry;
        private String mePinCode;
        private String network;
        private Character cardType;
        private Character cardDomIntlFlag;
        private Character cardInputAbility;
        private Character chAuthAbility;
        private Character cardCaptureAbility;
        private Character oprtEnvironment;
        private Character chPresent;
        private Character cardPresent;
        private Character cardInputMode;
        private Character chAuthMethod;
        private Character chAuthEntity;
        private Character cardOutPutAbility;
        private Character trlOutPutAbility;
        private Character pinCaptureAbility;
        private Integer batchNumber;
        private Integer trlBatchNumber;
        private LocalDateTime setlDate;
        private Character setlCode;
        private Double cryptAmount;
        private String ird;
        private Integer irdSerNumber;
        private Double irfFixed;
        private String banKCode;
        private Double netAmount;
        private Double bdfNetAmount;
        private Double pdfNetAmount;
        private Character revIndiCator;
        private Character onusOffusFlag;
        private Integer setlJobNumber;
        private Character holdPaymentFlag;
        private Character dmsSmsMode;
        private String cardCategory;
        private String cardSubCategory;
        private String emvData;
        private String v_txnDateTime;
        private String v_localTxnTime;
        private String v_localTxnDate;
        private String de61;
        private String de62;
        private String de63;
        private Character settlementIndicator;
        private String oprtEnvDe61_1;
        private String oprtEnvDe61_3;
        private String v_setlDate;
        
        PosTxnWorkEntityBuilder() {
        }
        
        public PosTxnWorkEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder pid(final Integer pid) {
            this.pid = pid;
            return this;
        }
        
        public PosTxnWorkEntityBuilder insCode(final Integer insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder lastUpdatedDate(final LocalDateTime lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder user(final int user) {
            this.user = user;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder jobNumber(final Integer jobNumber) {
            this.jobNumber = jobNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder genStatus(final Integer genStatus) {
            this.genStatus = genStatus;
            return this;
        }
        
        public PosTxnWorkEntityBuilder stagingFlag(final Character stagingFlag) {
            this.stagingFlag = stagingFlag;
            return this;
        }
        
        public PosTxnWorkEntityBuilder paymentFlag(final Character paymentFlag) {
            this.paymentFlag = paymentFlag;
            return this;
        }
        
        public PosTxnWorkEntityBuilder centreProcDate(final LocalDate centreProcDate) {
            this.centreProcDate = centreProcDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder bussDate(final LocalDate bussDate) {
            this.bussDate = bussDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnUniqueId(final String txnUniqueId) {
            this.txnUniqueId = txnUniqueId;
            return this;
        }
        
        public PosTxnWorkEntityBuilder msgTypeId(final String msgTypeId) {
            this.msgTypeId = msgTypeId;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public PosTxnWorkEntityBuilder responseCode(final String responseCode) {
            this.responseCode = responseCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public PosTxnWorkEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public PosTxnWorkEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public PosTxnWorkEntityBuilder groupSerNumber(final Integer groupSerNumber) {
            this.groupSerNumber = groupSerNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder smeSerNumber(final Integer smeSerNumber) {
            this.smeSerNumber = smeSerNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder agrSernumber(final Integer agrSernumber) {
            this.agrSernumber = agrSernumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public PosTxnWorkEntityBuilder procCode(final String procCode) {
            this.procCode = procCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder convRate(final Double convRate) {
            this.convRate = convRate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder stan(final String stan) {
            this.stan = stan;
            return this;
        }
        
        public PosTxnWorkEntityBuilder authType(final Integer authType) {
            this.authType = authType;
            return this;
        }
        
        public PosTxnWorkEntityBuilder authReason(final String authReason) {
            this.authReason = authReason;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnId(final String txnId) {
            this.txnId = txnId;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnDateTime(final LocalDateTime txnDateTime) {
            this.txnDateTime = txnDateTime;
            return this;
        }
        
        public PosTxnWorkEntityBuilder acqinstIdCode(final String acqinstIdCode) {
            this.acqinstIdCode = acqinstIdCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder acqInstConCode(final String acqInstConCode) {
            this.acqInstConCode = acqInstConCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder fwdInstIdCode(final String fwdInstIdCode) {
            this.fwdInstIdCode = fwdInstIdCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder recInstConCode(final String recInstConCode) {
            this.recInstConCode = recInstConCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder adtlPrivateData(final String adtlPrivateData) {
            this.adtlPrivateData = adtlPrivateData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder msgReasonCode(final String msgReasonCode) {
            this.msgReasonCode = msgReasonCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnFeeAmount(final Double txnFeeAmount) {
            this.txnFeeAmount = txnFeeAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder txnFeeCurCode(final String txnFeeCurCode) {
            this.txnFeeCurCode = txnFeeCurCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder setlAmount(final Double setlAmount) {
            this.setlAmount = setlAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder tipAmount(final Double tipAmount) {
            this.tipAmount = tipAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder setlCurCode(final String setlCurCode) {
            this.setlCurCode = setlCurCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder adtlAmounts(final String adtlAmounts) {
            this.adtlAmounts = adtlAmounts;
            return this;
        }
        
        public PosTxnWorkEntityBuilder authAmount(final Double authAmount) {
            this.authAmount = authAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder replaceAmount(final Double replaceAmount) {
            this.replaceAmount = replaceAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder adviceReasonCode(final String adviceReasonCode) {
            this.adviceReasonCode = adviceReasonCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder infData(final String infData) {
            this.infData = infData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder networkData(final String networkData) {
            this.networkData = networkData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder appIdentifier(final String appIdentifier) {
            this.appIdentifier = appIdentifier;
            return this;
        }
        
        public PosTxnWorkEntityBuilder appICProfile(final String appICProfile) {
            this.appICProfile = appICProfile;
            return this;
        }
        
        public PosTxnWorkEntityBuilder appTxnCounter(final String appTxnCounter) {
            this.appTxnCounter = appTxnCounter;
            return this;
        }
        
        public PosTxnWorkEntityBuilder appCryptogram(final String appCryptogram) {
            this.appCryptogram = appCryptogram;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cryptInfoData(final String cryptInfoData) {
            this.cryptInfoData = cryptInfoData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cvmResult(final String cvmResult) {
            this.cvmResult = cvmResult;
            return this;
        }
        
        public PosTxnWorkEntityBuilder ifdSerNumber(final String ifdSerNumber) {
            this.ifdSerNumber = ifdSerNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder issAppData(final String issAppData) {
            this.issAppData = issAppData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder trlAppVerNumber(final String trlAppVerNumber) {
            this.trlAppVerNumber = trlAppVerNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder trlCapabilities(final String trlCapabilities) {
            this.trlCapabilities = trlCapabilities;
            return this;
        }
        
        public PosTxnWorkEntityBuilder trlConCode(final String trlConCode) {
            this.trlConCode = trlConCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder terminalType(final String terminalType) {
            this.terminalType = terminalType;
            return this;
        }
        
        public PosTxnWorkEntityBuilder trlVerResult(final String trlVerResult) {
            this.trlVerResult = trlVerResult;
            return this;
        }
        
        public PosTxnWorkEntityBuilder tcc(final String tcc) {
            this.tcc = tcc;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chipCurCode(final String chipCurCode) {
            this.chipCurCode = chipCurCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chiptxnDate(final LocalDate chiptxnDate) {
            this.chiptxnDate = chiptxnDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chipTxnDate(final String chipTxnDate) {
            this.chipTxnDate = chipTxnDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chipTrlType(final String chipTrlType) {
            this.chipTrlType = chipTrlType;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chipCashBack(final Double chipCashBack) {
            this.chipCashBack = chipCashBack;
            return this;
        }
        
        public PosTxnWorkEntityBuilder upblNumber(final String upblNumber) {
            this.upblNumber = upblNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardAuthCode(final Character cardAuthCode) {
            this.cardAuthCode = cardAuthCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder issAuthData(final String issAuthData) {
            this.issAuthData = issAuthData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder posConditionCode(final String posConditionCode) {
            this.posConditionCode = posConditionCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder motoEcomIndicator(final String motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardVerResult(final String cardVerResult) {
            this.cardVerResult = cardVerResult;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardSeqNumber(final String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder expiryDate(final String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder serviceCode(final String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public PosTxnWorkEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public PosTxnWorkEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public PosTxnWorkEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public PosTxnWorkEntityBuilder mePinCode(final String mePinCode) {
            this.mePinCode = mePinCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder network(final String network) {
            this.network = network;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardInputAbility(final Character cardInputAbility) {
            this.cardInputAbility = cardInputAbility;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chAuthAbility(final Character chAuthAbility) {
            this.chAuthAbility = chAuthAbility;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardCaptureAbility(final Character cardCaptureAbility) {
            this.cardCaptureAbility = cardCaptureAbility;
            return this;
        }
        
        public PosTxnWorkEntityBuilder oprtEnvironment(final Character oprtEnvironment) {
            this.oprtEnvironment = oprtEnvironment;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chPresent(final Character chPresent) {
            this.chPresent = chPresent;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardPresent(final Character cardPresent) {
            this.cardPresent = cardPresent;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardInputMode(final Character cardInputMode) {
            this.cardInputMode = cardInputMode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chAuthMethod(final Character chAuthMethod) {
            this.chAuthMethod = chAuthMethod;
            return this;
        }
        
        public PosTxnWorkEntityBuilder chAuthEntity(final Character chAuthEntity) {
            this.chAuthEntity = chAuthEntity;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardOutPutAbility(final Character cardOutPutAbility) {
            this.cardOutPutAbility = cardOutPutAbility;
            return this;
        }
        
        public PosTxnWorkEntityBuilder trlOutPutAbility(final Character trlOutPutAbility) {
            this.trlOutPutAbility = trlOutPutAbility;
            return this;
        }
        
        public PosTxnWorkEntityBuilder pinCaptureAbility(final Character pinCaptureAbility) {
            this.pinCaptureAbility = pinCaptureAbility;
            return this;
        }
        
        public PosTxnWorkEntityBuilder batchNumber(final Integer batchNumber) {
            this.batchNumber = batchNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder trlBatchNumber(final Integer trlBatchNumber) {
            this.trlBatchNumber = trlBatchNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder setlDate(final LocalDateTime setlDate) {
            this.setlDate = setlDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder setlCode(final Character setlCode) {
            this.setlCode = setlCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cryptAmount(final Double cryptAmount) {
            this.cryptAmount = cryptAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder ird(final String ird) {
            this.ird = ird;
            return this;
        }
        
        public PosTxnWorkEntityBuilder irdSerNumber(final Integer irdSerNumber) {
            this.irdSerNumber = irdSerNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder irfFixed(final Double irfFixed) {
            this.irfFixed = irfFixed;
            return this;
        }
        
        public PosTxnWorkEntityBuilder banKCode(final String banKCode) {
            this.banKCode = banKCode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder netAmount(final Double netAmount) {
            this.netAmount = netAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder bdfNetAmount(final Double bdfNetAmount) {
            this.bdfNetAmount = bdfNetAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder pdfNetAmount(final Double pdfNetAmount) {
            this.pdfNetAmount = pdfNetAmount;
            return this;
        }
        
        public PosTxnWorkEntityBuilder revIndiCator(final Character revIndiCator) {
            this.revIndiCator = revIndiCator;
            return this;
        }
        
        public PosTxnWorkEntityBuilder onusOffusFlag(final Character onusOffusFlag) {
            this.onusOffusFlag = onusOffusFlag;
            return this;
        }
        
        public PosTxnWorkEntityBuilder setlJobNumber(final Integer setlJobNumber) {
            this.setlJobNumber = setlJobNumber;
            return this;
        }
        
        public PosTxnWorkEntityBuilder holdPaymentFlag(final Character holdPaymentFlag) {
            this.holdPaymentFlag = holdPaymentFlag;
            return this;
        }
        
        public PosTxnWorkEntityBuilder dmsSmsMode(final Character dmsSmsMode) {
            this.dmsSmsMode = dmsSmsMode;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardCategory(final String cardCategory) {
            this.cardCategory = cardCategory;
            return this;
        }
        
        public PosTxnWorkEntityBuilder cardSubCategory(final String cardSubCategory) {
            this.cardSubCategory = cardSubCategory;
            return this;
        }
        
        public PosTxnWorkEntityBuilder emvData(final String emvData) {
            this.emvData = emvData;
            return this;
        }
        
        public PosTxnWorkEntityBuilder v_txnDateTime(final String v_txnDateTime) {
            this.v_txnDateTime = v_txnDateTime;
            return this;
        }
        
        public PosTxnWorkEntityBuilder v_localTxnTime(final String v_localTxnTime) {
            this.v_localTxnTime = v_localTxnTime;
            return this;
        }
        
        public PosTxnWorkEntityBuilder v_localTxnDate(final String v_localTxnDate) {
            this.v_localTxnDate = v_localTxnDate;
            return this;
        }
        
        public PosTxnWorkEntityBuilder de61(final String de61) {
            this.de61 = de61;
            return this;
        }
        
        public PosTxnWorkEntityBuilder de62(final String de62) {
            this.de62 = de62;
            return this;
        }
        
        public PosTxnWorkEntityBuilder de63(final String de63) {
            this.de63 = de63;
            return this;
        }
        
        public PosTxnWorkEntityBuilder settlementIndicator(final Character settlementIndicator) {
            this.settlementIndicator = settlementIndicator;
            return this;
        }
        
        public PosTxnWorkEntityBuilder oprtEnvDe61_1(final String oprtEnvDe61_1) {
            this.oprtEnvDe61_1 = oprtEnvDe61_1;
            return this;
        }
        
        public PosTxnWorkEntityBuilder oprtEnvDe61_3(final String oprtEnvDe61_3) {
            this.oprtEnvDe61_3 = oprtEnvDe61_3;
            return this;
        }
        
        public PosTxnWorkEntityBuilder v_setlDate(final String v_setlDate) {
            this.v_setlDate = v_setlDate;
            return this;
        }
        
        public PosTxnWorkEntity build() {
            return new PosTxnWorkEntity(this.serialNumber, this.pid, this.insCode, this.lastUpdatedDate, this.user, this.cardNumber, this.intCode, this.jobNumber, this.genStatus, this.stagingFlag, this.paymentFlag, this.centreProcDate, this.bussDate, this.txnUniqueId, this.msgTypeId, this.txnAmount, this.localDateTime, this.responseCode, this.rrn, this.terminalId, this.merchantId, this.groupSerNumber, this.smeSerNumber, this.agrSernumber, this.txnType, this.procCode, this.convRate, this.stan, this.authType, this.authReason, this.txnId, this.txnDateTime, this.acqinstIdCode, this.acqInstConCode, this.fwdInstIdCode, this.recInstConCode, this.adtlPrivateData, this.msgReasonCode, this.cashBackAmount, this.txnCurCode, this.txnFeeAmount, this.txnFeeCurCode, this.setlAmount, this.tipAmount, this.setlCurCode, this.adtlAmounts, this.authAmount, this.replaceAmount, this.adviceReasonCode, this.infData, this.networkData, this.appIdentifier, this.appICProfile, this.appTxnCounter, this.appCryptogram, this.cryptInfoData, this.cvmResult, this.ifdSerNumber, this.issAppData, this.trlAppVerNumber, this.trlCapabilities, this.trlConCode, this.terminalType, this.trlVerResult, this.tcc, this.chipCurCode, this.chiptxnDate, this.chipTxnDate, this.chipTrlType, this.chipCashBack, this.upblNumber, this.cardAuthCode, this.issAuthData, this.posEntryMode, this.posConditionCode, this.motoEcomIndicator, this.cardVerResult, this.cardSeqNumber, this.expiryDate, this.serviceCode, this.mcc, this.meName, this.meCity, this.meCountry, this.mePinCode, this.network, this.cardType, this.cardDomIntlFlag, this.cardInputAbility, this.chAuthAbility, this.cardCaptureAbility, this.oprtEnvironment, this.chPresent, this.cardPresent, this.cardInputMode, this.chAuthMethod, this.chAuthEntity, this.cardOutPutAbility, this.trlOutPutAbility, this.pinCaptureAbility, this.batchNumber, this.trlBatchNumber, this.setlDate, this.setlCode, this.cryptAmount, this.ird, this.irdSerNumber, this.irfFixed, this.banKCode, this.netAmount, this.bdfNetAmount, this.pdfNetAmount, this.revIndiCator, this.onusOffusFlag, this.setlJobNumber, this.holdPaymentFlag, this.dmsSmsMode, this.cardCategory, this.cardSubCategory, this.emvData, this.v_txnDateTime, this.v_localTxnTime, this.v_localTxnDate, this.de61, this.de62, this.de63, this.settlementIndicator, this.oprtEnvDe61_1, this.oprtEnvDe61_3, this.v_setlDate);
        }
        
        @Override
        public String toString() {
            return "" + ("PosTxnWorkEntity.PosTxnWorkEntityBuilder(serialNumber=" + this.serialNumber + ", pid=" + this.pid + ", insCode=" + this.insCode + ", lastUpdatedDate=" + String.valueOf(this.lastUpdatedDate) + ", user=" + this.user + ", cardNumber=" + this.cardNumber + ", intCode=" + this.intCode + ", jobNumber=" + this.jobNumber + ", genStatus=" + this.genStatus + ", stagingFlag=" + this.stagingFlag + ", paymentFlag=" + this.paymentFlag + ", centreProcDate=" + String.valueOf(this.centreProcDate) + ", bussDate=" + String.valueOf(this.bussDate) + ", txnUniqueId=" + this.txnUniqueId + ", msgTypeId=" + this.msgTypeId + ", txnAmount=" + this.txnAmount + ", localDateTime=" + String.valueOf(this.localDateTime) + ", responseCode=" + this.responseCode + ", rrn=" + this.rrn + ", terminalId=" + this.terminalId + ", merchantId=" + this.merchantId + ", groupSerNumber=" + this.groupSerNumber + ", smeSerNumber=" + this.smeSerNumber + ", agrSernumber=" + this.agrSernumber + ", txnType=" + this.txnType + ", procCode=" + this.procCode + ", convRate=" + this.convRate + ", stan=" + this.stan + ", authType=" + this.authType + ", authReason=" + this.authReason + ", txnId=" + this.txnId + ", txnDateTime=" + String.valueOf(this.txnDateTime) + ", acqinstIdCode=" + this.acqinstIdCode + ", acqInstConCode=" + this.acqInstConCode + ", fwdInstIdCode=" + this.fwdInstIdCode + ", recInstConCode=" + this.recInstConCode + ", adtlPrivateData=" + this.adtlPrivateData + ", msgReasonCode=" + this.msgReasonCode + ", cashBackAmount=" + this.cashBackAmount + ", txnCurCode=" + this.txnCurCode + ", txnFeeAmount=" + this.txnFeeAmount + ", txnFeeCurCode=" + this.txnFeeCurCode + ", setlAmount=" + this.setlAmount + ", tipAmount=" + this.tipAmount + ", setlCurCode=" + this.setlCurCode + ", adtlAmounts=" + this.adtlAmounts + ", authAmount=" + this.authAmount + ", replaceAmount=" + this.replaceAmount + ", adviceReasonCode=" + this.adviceReasonCode + ", infData=" + this.infData + ", networkData=" + this.networkData + ", appIdentifier=" + this.appIdentifier + ", appICProfile=" + this.appICProfile + ", appTxnCounter=" + this.appTxnCounter + ", appCryptogram=" + this.appCryptogram + ", cryptInfoData=" + this.cryptInfoData + ", cvmResult=" + this.cvmResult + ", ifdSerNumber=" + this.ifdSerNumber + ", issAppData=" + this.issAppData + ", trlAppVerNumber=" + this.trlAppVerNumber + ", trlCapabilities=" + this.trlCapabilities + ", trlConCode=" + this.trlConCode + ", terminalType=" + this.terminalType + ", trlVerResult=" + this.trlVerResult + ", tcc=" + this.tcc + ", chipCurCode=" + this.chipCurCode + ", chiptxnDate=" + String.valueOf(this.chiptxnDate) + ", chipTxnDate=" + this.chipTxnDate + ", chipTrlType=" + this.chipTrlType + ", chipCashBack=" + this.chipCashBack + ", upblNumber=" + this.upblNumber + ", cardAuthCode=" + this.cardAuthCode + ", issAuthData=" + this.issAuthData + ", posEntryMode=" + this.posEntryMode + ", posConditionCode=" + this.posConditionCode + ", motoEcomIndicator=" + this.motoEcomIndicator + ", cardVerResult=" + this.cardVerResult + ", cardSeqNumber=" + this.cardSeqNumber + ", expiryDate=" + this.expiryDate + ", serviceCode=" + this.serviceCode + ", mcc=" + this.mcc + ", meName=" + this.meName + ", meCity=" + this.meCity + ", meCountry=" + this.meCountry + ", mePinCode=" + this.mePinCode + ", network=" + this.network + ", cardType=" + this.cardType + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", cardInputAbility=" + this.cardInputAbility + ", chAuthAbility=" + this.chAuthAbility + ", cardCaptureAbility=" + this.cardCaptureAbility + ", oprtEnvironment=" + this.oprtEnvironment + ", chPresent=" + this.chPresent + ", cardPresent=" + this.cardPresent + ", cardInputMode=" + this.cardInputMode + ", chAuthMethod=" + this.chAuthMethod + ", chAuthEntity=" + this.chAuthEntity + ", cardOutPutAbility=" + this.cardOutPutAbility + ", trlOutPutAbility=" + this.trlOutPutAbility + ", pinCaptureAbility=") + /* invokedynamic(!) */ProcyonInvokeDynamicHelper_4.invoke(this.pinCaptureAbility, this.batchNumber, this.trlBatchNumber, String.valueOf(this.setlDate), this.setlCode, this.cryptAmount, this.ird, this.irdSerNumber, this.irfFixed, this.banKCode, this.netAmount, this.bdfNetAmount, this.pdfNetAmount, this.revIndiCator, this.onusOffusFlag, this.setlJobNumber, this.holdPaymentFlag, this.dmsSmsMode, this.cardCategory, this.cardSubCategory, this.emvData, this.v_txnDateTime, this.v_localTxnTime, this.v_localTxnDate, this.de61, this.de62, this.de63, this.settlementIndicator, this.oprtEnvDe61_1, this.oprtEnvDe61_3, this.v_setlDate);
        }
        
        // This helper class was generated by Procyon to approximate the behavior of an
        // 'invokedynamic' instruction that it doesn't know how to interpret.
        private static final class ProcyonInvokeDynamicHelper_4
        {
            private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
            private static MethodHandle handle;
            private static volatile int fence;
            
            private static MethodHandle handle() {
                final MethodHandle handle = ProcyonInvokeDynamicHelper_4.handle;
                if (handle != null)
                    return handle;
                return ProcyonInvokeDynamicHelper_4.ensureHandle();
            }
            
            private static MethodHandle ensureHandle() {
                ProcyonInvokeDynamicHelper_4.fence = 0;
                MethodHandle handle = ProcyonInvokeDynamicHelper_4.handle;
                if (handle == null) {
                    MethodHandles.Lookup lookup = ProcyonInvokeDynamicHelper_4.LOOKUP;
                    try {
                        handle = ((CallSite)StringConcatFactory.makeConcatWithConstants(lookup, "makeConcatWithConstants", MethodType.methodType(String.class, Character.class, Integer.class, Integer.class, String.class, Character.class, Double.class, String.class, Integer.class, Double.class, String.class, Double.class, Double.class, Double.class, Character.class, Character.class, Integer.class, Character.class, Character.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Character.class, String.class, String.class, String.class), "\u0001, batchNumber=\u0001, trlBatchNumber=\u0001, setlDate=\u0001, setlCode=\u0001, cryptAmount=\u0001, ird=\u0001, irdSerNumber=\u0001, irfFixed=\u0001, banKCode=\u0001, netAmount=\u0001, bdfNetAmount=\u0001, pdfNetAmount=\u0001, revIndiCator=\u0001, onusOffusFlag=\u0001, setlJobNumber=\u0001, holdPaymentFlag=\u0001, dmsSmsMode=\u0001, cardCategory=\u0001, cardSubCategory=\u0001, emvData=\u0001, v_txnDateTime=\u0001, v_localTxnTime=\u0001, v_localTxnDate=\u0001, de61=\u0001, de62=\u0001, de63=\u0001, settlementIndicator=\u0001, oprtEnvDe61_1=\u0001, oprtEnvDe61_3=\u0001, v_setlDate=\u0001)")).dynamicInvoker();
                    }
                    catch (Throwable t) {
                        throw new UndeclaredThrowableException(t);
                    }
                    ProcyonInvokeDynamicHelper_4.fence = 1;
                    ProcyonInvokeDynamicHelper_4.handle = handle;
                    ProcyonInvokeDynamicHelper_4.fence = 0;
                }
                return handle;
            }
            
            private static String invoke(Character p0, Integer p1, Integer p2, String p3, Character p4, Double p5, String p6, Integer p7, Double p8, String p9, Double p10, Double p11, Double p12, Character p13, Character p14, Integer p15, Character p16, Character p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, Character p27, String p28, String p29, String p30) {
                try {
                    return ProcyonInvokeDynamicHelper_4.handle().invokeExact(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30);
                }
                catch (Throwable t) {
                    throw new UndeclaredThrowableException(t);
                }
            }
        }
    }
    
    // This helper class was generated by Procyon to approximate the behavior of an
    // 'invokedynamic' instruction that it doesn't know how to interpret.
    private static final class ProcyonInvokeDynamicHelper_3
    {
        private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
        private static MethodHandle handle;
        private static volatile int fence;
        
        private static MethodHandle handle() {
            final MethodHandle handle = ProcyonInvokeDynamicHelper_3.handle;
            if (handle != null)
                return handle;
            return ProcyonInvokeDynamicHelper_3.ensureHandle();
        }
        
        private static MethodHandle ensureHandle() {
            ProcyonInvokeDynamicHelper_3.fence = 0;
            MethodHandle handle = ProcyonInvokeDynamicHelper_3.handle;
            if (handle == null) {
                MethodHandles.Lookup lookup = ProcyonInvokeDynamicHelper_3.LOOKUP;
                try {
                    handle = ((CallSite)StringConcatFactory.makeConcatWithConstants(lookup, "makeConcatWithConstants", MethodType.methodType(String.class, Character.class, Integer.class, Integer.class, String.class, Character.class, Double.class, String.class, Integer.class, Double.class, String.class, Double.class, Double.class, Double.class, Character.class, Character.class, Integer.class, Character.class, Character.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Character.class, String.class, String.class, String.class), "\u0001, batchNumber=\u0001, trlBatchNumber=\u0001, setlDate=\u0001, setlCode=\u0001, cryptAmount=\u0001, ird=\u0001, irdSerNumber=\u0001, irfFixed=\u0001, banKCode=\u0001, netAmount=\u0001, bdfNetAmount=\u0001, pdfNetAmount=\u0001, revIndiCator=\u0001, onusOffusFlag=\u0001, setlJobNumber=\u0001, holdPaymentFlag=\u0001, dmsSmsMode=\u0001, cardCategory=\u0001, cardSubCategory=\u0001, emvData=\u0001, v_txnDateTime=\u0001, v_localTxnTime=\u0001, v_localTxnDate=\u0001, de61=\u0001, de62=\u0001, de63=\u0001, settlementIndicator=\u0001, oprtEnvDe61_1=\u0001, oprtEnvDe61_3=\u0001, v_setlDate=\u0001)")).dynamicInvoker();
                }
                catch (Throwable t) {
                    throw new UndeclaredThrowableException(t);
                }
                ProcyonInvokeDynamicHelper_3.fence = 1;
                ProcyonInvokeDynamicHelper_3.handle = handle;
                ProcyonInvokeDynamicHelper_3.fence = 0;
            }
            return handle;
        }
        
        private static String invoke(Character p0, Integer p1, Integer p2, String p3, Character p4, Double p5, String p6, Integer p7, Double p8, String p9, Double p10, Double p11, Double p12, Character p13, Character p14, Integer p15, Character p16, Character p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, Character p27, String p28, String p29, String p30) {
            try {
                return ProcyonInvokeDynamicHelper_3.handle().invokeExact(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30);
            }
            catch (Throwable t) {
                throw new UndeclaredThrowableException(t);
            }
        }
    }
}
