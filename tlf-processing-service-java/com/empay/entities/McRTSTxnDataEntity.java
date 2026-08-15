// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MC_RTS_TXN_DATA")
public class McRTSTxnDataEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MRD_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "MRD_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "MRD_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "MRD_INS_CODE")
    private int institutionCode;
    @Column(name = "MRD_INT_CODE")
    private Integer intCode;
    @Column(name = "MRD_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "MRD_GEN_STATUS")
    private int generalStatus;
    @Column(name = "MRD_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name = "MRD_TXN_TYPE")
    private String txnType;
    @Column(name = "MRD_FILE_ID")
    private String fileID;
    @Column(name = "MRD_MSG_TYPE_ID")
    private String messageTypeId;
    @Column(name = "MRD_PROC_CODE")
    private String procCode;
    @Column(name = "MRD_FUNC_CODE")
    private String functionCode;
    @Column(name = "MRD_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "MRD_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name = "MRD_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name = "MRD_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "MRD_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "MRD_EXPIRY_DATE")
    private String expiryDate;
    @Column(name = "MRD_POS_DATA_CODE")
    private String posDataCode;
    @Column(name = "MRD_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name = "MRD_MCC")
    private String mcc;
    @Column(name = "MRD_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name = "MRD_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name = "MRD_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "MRD_APPR_CODE")
    private String approvalCode;
    @Column(name = "MRD_RESP_CODE")
    private String responseCode;
    @Column(name = "MRD_SERVICE_CODE")
    private String serviceCode;
    @Column(name = "MRD_TERMINAL_ID")
    private String terminalId;
    @Column(name = "MRD_MERCHANT_ID")
    private String merchantId;
    @Column(name = "MRD_ME_NAME")
    private String meName;
    @Column(name = "MRD_ME_CITY")
    private String meCity;
    @Column(name = "MRD_ME_ZIP_CODE")
    private String mePinCode;
    @Column(name = "MRD_ME_COUNTRY")
    private String meCountry;
    @Column(name = "MRD_TRL_TYPE")
    private String trlType;
    @Column(name = "MRD_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name = "MRD_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name = "MRD_TXN_CURR_EXP")
    private int txnCurrencyExponent;
    @Column(name = "MRD_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "MRD_IRD")
    private String ird;
    @Column(name = "MRD_SETL_INDICATOR")
    private Character settlementIndicator;
    @Column(name = "MRD_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name = "MRD_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name = "MRD_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name = "MRD_ISS_APP_DATA")
    private String issAppData;
    @Column(name = "MRD_UPBL_NUMBER")
    private String upblNumber;
    @Column(name = "MRD_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name = "MRD_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name = "MRD_TXN_DATE")
    private LocalDate txnDate;
    @Column(name = "MRD_CHIP_TXN_DATE")
    private String chipTxnDate;
    @Column(name = "MRD_CHIP_TXN_TYPE")
    private String chipTxnType;
    @Column(name = "MRD_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name = "MRD_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name = "MRD_TRL_CON_CODE")
    private String trlConCode;
    @Column(name = "MRD_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "MRD_CVM_RESULTS")
    private String cvmResult;
    @Column(name = "MRD_TRL_CAPABILITIES")
    private String trlCapabilities;
    @Column(name = "MRD_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name = "MRD_TCC")
    private String tcc;
    @Column(name = "MRD_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name = "MRD_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name = "MRD_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name = "MRD_TXN_SEQ_COUNTER")
    private String txnSeqCounter;
    @Column(name = "MRD_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name = "MRD_TXN_LIFE_CYCL_ID")
    private String txnlifeCycleId;
    @Column(name = "MRD_MSG_NUMBER")
    private String msgNumber;
    @Column(name = "MRD_MEMBER_TEXT")
    private String memberText;
    @Column(name = "MRD_ORG_INST_ID_CODE")
    private String orgInstIdCode;
    @Column(name = "MRD_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name = "MRD_MER_MC_ASSIGNED_ID")
    private String maid;
    @Column(name = "MRD_CARD_TYPE")
    private Character cardType;
    @Column(name = "MRD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "MRD_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name = "MRD_POS_PG_TYPE")
    private String posPgType;
    @Column(name = "MRD_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name = "MRD_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name = "MRD_ME_COUNTRY_OF_ORIGIN")
    private String meCountryOfOrigin;
    @Column(name = "MRD_RESPONSE")
    private String apiResponse;
    @Column(name = "MRD_TIP_AMOUNT")
    private Double tipAmount;
    @Column(name = "MRD_MPOS_ACC_DEV_TYPE")
    private Character mposAccDevType;
    
    public static McRTSTxnDataEntityBuilder builder() {
        return new McRTSTxnDataEntityBuilder();
    }
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public Integer getUpdatedUser() {
        return this.updatedUser;
    }
    
    public int getInstitutionCode() {
        return this.institutionCode;
    }
    
    public Integer getIntCode() {
        return this.intCode;
    }
    
    public Integer getPrjSerNumber() {
        return this.prjSerNumber;
    }
    
    public int getGeneralStatus() {
        return this.generalStatus;
    }
    
    public Integer getTxnRefNumber() {
        return this.txnRefNumber;
    }
    
    public String getTxnType() {
        return this.txnType;
    }
    
    public String getFileID() {
        return this.fileID;
    }
    
    public String getMessageTypeId() {
        return this.messageTypeId;
    }
    
    public String getProcCode() {
        return this.procCode;
    }
    
    public String getFunctionCode() {
        return this.functionCode;
    }
    
    public Double getTxnAmount() {
        return this.txnAmount;
    }
    
    public Double getSurchargeAmount() {
        return this.surchargeAmount;
    }
    
    public LocalDate getFileProcDate() {
        return this.fileProcDate;
    }
    
    public String getCardNumber() {
        return this.cardNumber;
    }
    
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }
    
    public String getExpiryDate() {
        return this.expiryDate;
    }
    
    public String getPosDataCode() {
        return this.posDataCode;
    }
    
    public String getMsgReasonCode() {
        return this.msgReasonCode;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getAcqRefData() {
        return this.acqRefData;
    }
    
    public String getAcqinstIdCode() {
        return this.acqinstIdCode;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getApprovalCode() {
        return this.approvalCode;
    }
    
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public String getServiceCode() {
        return this.serviceCode;
    }
    
    public String getTerminalId() {
        return this.terminalId;
    }
    
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public String getMeName() {
        return this.meName;
    }
    
    public String getMeCity() {
        return this.meCity;
    }
    
    public String getMePinCode() {
        return this.mePinCode;
    }
    
    public String getMeCountry() {
        return this.meCountry;
    }
    
    public String getTrlType() {
        return this.trlType;
    }
    
    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }
    
    public Double getTxnFeeAmount() {
        return this.txnFeeAmount;
    }
    
    public int getTxnCurrencyExponent() {
        return this.txnCurrencyExponent;
    }
    
    public String getTxnCurCode() {
        return this.txnCurCode;
    }
    
    public String getIrd() {
        return this.ird;
    }
    
    public Character getSettlementIndicator() {
        return this.settlementIndicator;
    }
    
    public String getCardSeqNumber() {
        return this.cardSeqNumber;
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
    
    public LocalDate getTxnDate() {
        return this.txnDate;
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
    
    public String getAppICProfile() {
        return this.appICProfile;
    }
    
    public String getTrlConCode() {
        return this.trlConCode;
    }
    
    public Double getCashBackAmount() {
        return this.cashBackAmount;
    }
    
    public String getCvmResult() {
        return this.cvmResult;
    }
    
    public String getTrlCapabilities() {
        return this.trlCapabilities;
    }
    
    public String getIfdSerNumber() {
        return this.ifdSerNumber;
    }
    
    public String getTcc() {
        return this.tcc;
    }
    
    public String getChipCurCode() {
        return this.chipCurCode;
    }
    
    public String getChipTrlType() {
        return this.chipTrlType;
    }
    
    public String getTrlAppVerNumber() {
        return this.trlAppVerNumber;
    }
    
    public String getTxnSeqCounter() {
        return this.txnSeqCounter;
    }
    
    public String getIssAuthData() {
        return this.issAuthData;
    }
    
    public String getTxnlifeCycleId() {
        return this.txnlifeCycleId;
    }
    
    public String getMsgNumber() {
        return this.msgNumber;
    }
    
    public String getMemberText() {
        return this.memberText;
    }
    
    public String getOrgInstIdCode() {
        return this.orgInstIdCode;
    }
    
    public Character getRevIndiCator() {
        return this.revIndiCator;
    }
    
    public String getMaid() {
        return this.maid;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }
    
    public Character getDmsSmsMode() {
        return this.dmsSmsMode;
    }
    
    public String getPosPgType() {
        return this.posPgType;
    }
    
    public LocalDate getCentreProcDate() {
        return this.centreProcDate;
    }
    
    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }
    
    public String getMeCountryOfOrigin() {
        return this.meCountryOfOrigin;
    }
    
    public String getApiResponse() {
        return this.apiResponse;
    }
    
    public Double getTipAmount() {
        return this.tipAmount;
    }
    
    public Character getMposAccDevType() {
        return this.mposAccDevType;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInstitutionCode(final int institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setPrjSerNumber(final Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }
    
    public void setGeneralStatus(final int generalStatus) {
        this.generalStatus = generalStatus;
    }
    
    public void setTxnRefNumber(final Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setFileID(final String fileID) {
        this.fileID = fileID;
    }
    
    public void setMessageTypeId(final String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
    }
    
    public void setFunctionCode(final String functionCode) {
        this.functionCode = functionCode;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setSurchargeAmount(final Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }
    
    public void setFileProcDate(final LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setPosDataCode(final String posDataCode) {
        this.posDataCode = posDataCode;
    }
    
    public void setMsgReasonCode(final String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setAcqRefData(final String acqRefData) {
        this.acqRefData = acqRefData;
    }
    
    public void setAcqinstIdCode(final String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setMeName(final String meName) {
        this.meName = meName;
    }
    
    public void setMeCity(final String meCity) {
        this.meCity = meCity;
    }
    
    public void setMePinCode(final String mePinCode) {
        this.mePinCode = mePinCode;
    }
    
    public void setMeCountry(final String meCountry) {
        this.meCountry = meCountry;
    }
    
    public void setTrlType(final String trlType) {
        this.trlType = trlType;
    }
    
    public void setMotoEcomIndicator(final String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setTxnFeeAmount(final Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }
    
    public void setTxnCurrencyExponent(final int txnCurrencyExponent) {
        this.txnCurrencyExponent = txnCurrencyExponent;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setSettlementIndicator(final Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }
    
    public void setCardSeqNumber(final String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
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
    
    public void setTxnDate(final LocalDate txnDate) {
        this.txnDate = txnDate;
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
    
    public void setAppICProfile(final String appICProfile) {
        this.appICProfile = appICProfile;
    }
    
    public void setTrlConCode(final String trlConCode) {
        this.trlConCode = trlConCode;
    }
    
    public void setCashBackAmount(final Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    public void setCvmResult(final String cvmResult) {
        this.cvmResult = cvmResult;
    }
    
    public void setTrlCapabilities(final String trlCapabilities) {
        this.trlCapabilities = trlCapabilities;
    }
    
    public void setIfdSerNumber(final String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }
    
    public void setTcc(final String tcc) {
        this.tcc = tcc;
    }
    
    public void setChipCurCode(final String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }
    
    public void setChipTrlType(final String chipTrlType) {
        this.chipTrlType = chipTrlType;
    }
    
    public void setTrlAppVerNumber(final String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }
    
    public void setTxnSeqCounter(final String txnSeqCounter) {
        this.txnSeqCounter = txnSeqCounter;
    }
    
    public void setIssAuthData(final String issAuthData) {
        this.issAuthData = issAuthData;
    }
    
    public void setTxnlifeCycleId(final String txnlifeCycleId) {
        this.txnlifeCycleId = txnlifeCycleId;
    }
    
    public void setMsgNumber(final String msgNumber) {
        this.msgNumber = msgNumber;
    }
    
    public void setMemberText(final String memberText) {
        this.memberText = memberText;
    }
    
    public void setOrgInstIdCode(final String orgInstIdCode) {
        this.orgInstIdCode = orgInstIdCode;
    }
    
    public void setRevIndiCator(final Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }
    
    public void setMaid(final String maid) {
        this.maid = maid;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setDmsSmsMode(final Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }
    
    public void setPosPgType(final String posPgType) {
        this.posPgType = posPgType;
    }
    
    public void setCentreProcDate(final LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }
    
    public void setEncryptedCardNumber(final String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }
    
    public void setMeCountryOfOrigin(final String meCountryOfOrigin) {
        this.meCountryOfOrigin = meCountryOfOrigin;
    }
    
    public void setApiResponse(final String apiResponse) {
        this.apiResponse = apiResponse;
    }
    
    public void setTipAmount(final Double tipAmount) {
        this.tipAmount = tipAmount;
    }
    
    public void setMposAccDevType(final Character mposAccDevType) {
        this.mposAccDevType = mposAccDevType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McRTSTxnDataEntity)) {
            return false;
        }
        final McRTSTxnDataEntity other = (McRTSTxnDataEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        if (this.getGeneralStatus() != other.getGeneralStatus()) {
            return false;
        }
        if (this.getTxnCurrencyExponent() != other.getTxnCurrencyExponent()) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0104: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0104;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0104;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0141: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0141;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0141;
            }
            return false;
        }
        final Object this$intCode = this.getIntCode();
        final Object other$intCode = other.getIntCode();
        Label_0178: {
            if (this$intCode == null) {
                if (other$intCode == null) {
                    break Label_0178;
                }
            }
            else if (this$intCode.equals(other$intCode)) {
                break Label_0178;
            }
            return false;
        }
        final Object this$prjSerNumber = this.getPrjSerNumber();
        final Object other$prjSerNumber = other.getPrjSerNumber();
        Label_0215: {
            if (this$prjSerNumber == null) {
                if (other$prjSerNumber == null) {
                    break Label_0215;
                }
            }
            else if (this$prjSerNumber.equals(other$prjSerNumber)) {
                break Label_0215;
            }
            return false;
        }
        final Object this$txnRefNumber = this.getTxnRefNumber();
        final Object other$txnRefNumber = other.getTxnRefNumber();
        Label_0252: {
            if (this$txnRefNumber == null) {
                if (other$txnRefNumber == null) {
                    break Label_0252;
                }
            }
            else if (this$txnRefNumber.equals(other$txnRefNumber)) {
                break Label_0252;
            }
            return false;
        }
        final Object this$txnAmount = this.getTxnAmount();
        final Object other$txnAmount = other.getTxnAmount();
        Label_0289: {
            if (this$txnAmount == null) {
                if (other$txnAmount == null) {
                    break Label_0289;
                }
            }
            else if (this$txnAmount.equals(other$txnAmount)) {
                break Label_0289;
            }
            return false;
        }
        final Object this$surchargeAmount = this.getSurchargeAmount();
        final Object other$surchargeAmount = other.getSurchargeAmount();
        Label_0326: {
            if (this$surchargeAmount == null) {
                if (other$surchargeAmount == null) {
                    break Label_0326;
                }
            }
            else if (this$surchargeAmount.equals(other$surchargeAmount)) {
                break Label_0326;
            }
            return false;
        }
        final Object this$txnFeeAmount = this.getTxnFeeAmount();
        final Object other$txnFeeAmount = other.getTxnFeeAmount();
        Label_0363: {
            if (this$txnFeeAmount == null) {
                if (other$txnFeeAmount == null) {
                    break Label_0363;
                }
            }
            else if (this$txnFeeAmount.equals(other$txnFeeAmount)) {
                break Label_0363;
            }
            return false;
        }
        final Object this$settlementIndicator = this.getSettlementIndicator();
        final Object other$settlementIndicator = other.getSettlementIndicator();
        Label_0400: {
            if (this$settlementIndicator == null) {
                if (other$settlementIndicator == null) {
                    break Label_0400;
                }
            }
            else if (this$settlementIndicator.equals(other$settlementIndicator)) {
                break Label_0400;
            }
            return false;
        }
        final Object this$cryptAmount = this.getCryptAmount();
        final Object other$cryptAmount = other.getCryptAmount();
        Label_0437: {
            if (this$cryptAmount == null) {
                if (other$cryptAmount == null) {
                    break Label_0437;
                }
            }
            else if (this$cryptAmount.equals(other$cryptAmount)) {
                break Label_0437;
            }
            return false;
        }
        final Object this$cashBackAmount = this.getCashBackAmount();
        final Object other$cashBackAmount = other.getCashBackAmount();
        Label_0474: {
            if (this$cashBackAmount == null) {
                if (other$cashBackAmount == null) {
                    break Label_0474;
                }
            }
            else if (this$cashBackAmount.equals(other$cashBackAmount)) {
                break Label_0474;
            }
            return false;
        }
        final Object this$revIndiCator = this.getRevIndiCator();
        final Object other$revIndiCator = other.getRevIndiCator();
        Label_0511: {
            if (this$revIndiCator == null) {
                if (other$revIndiCator == null) {
                    break Label_0511;
                }
            }
            else if (this$revIndiCator.equals(other$revIndiCator)) {
                break Label_0511;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0548: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0548;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0548;
            }
            return false;
        }
        final Object this$cardDomIntlFlag = this.getCardDomIntlFlag();
        final Object other$cardDomIntlFlag = other.getCardDomIntlFlag();
        Label_0585: {
            if (this$cardDomIntlFlag == null) {
                if (other$cardDomIntlFlag == null) {
                    break Label_0585;
                }
            }
            else if (this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
                break Label_0585;
            }
            return false;
        }
        final Object this$dmsSmsMode = this.getDmsSmsMode();
        final Object other$dmsSmsMode = other.getDmsSmsMode();
        Label_0622: {
            if (this$dmsSmsMode == null) {
                if (other$dmsSmsMode == null) {
                    break Label_0622;
                }
            }
            else if (this$dmsSmsMode.equals(other$dmsSmsMode)) {
                break Label_0622;
            }
            return false;
        }
        final Object this$tipAmount = this.getTipAmount();
        final Object other$tipAmount = other.getTipAmount();
        Label_0659: {
            if (this$tipAmount == null) {
                if (other$tipAmount == null) {
                    break Label_0659;
                }
            }
            else if (this$tipAmount.equals(other$tipAmount)) {
                break Label_0659;
            }
            return false;
        }
        final Object this$mposAccDevType = this.getMposAccDevType();
        final Object other$mposAccDevType = other.getMposAccDevType();
        Label_0696: {
            if (this$mposAccDevType == null) {
                if (other$mposAccDevType == null) {
                    break Label_0696;
                }
            }
            else if (this$mposAccDevType.equals(other$mposAccDevType)) {
                break Label_0696;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0733: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0733;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0733;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_0770: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_0770;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_0770;
            }
            return false;
        }
        final Object this$fileID = this.getFileID();
        final Object other$fileID = other.getFileID();
        Label_0807: {
            if (this$fileID == null) {
                if (other$fileID == null) {
                    break Label_0807;
                }
            }
            else if (this$fileID.equals(other$fileID)) {
                break Label_0807;
            }
            return false;
        }
        final Object this$messageTypeId = this.getMessageTypeId();
        final Object other$messageTypeId = other.getMessageTypeId();
        Label_0844: {
            if (this$messageTypeId == null) {
                if (other$messageTypeId == null) {
                    break Label_0844;
                }
            }
            else if (this$messageTypeId.equals(other$messageTypeId)) {
                break Label_0844;
            }
            return false;
        }
        final Object this$procCode = this.getProcCode();
        final Object other$procCode = other.getProcCode();
        Label_0881: {
            if (this$procCode == null) {
                if (other$procCode == null) {
                    break Label_0881;
                }
            }
            else if (this$procCode.equals(other$procCode)) {
                break Label_0881;
            }
            return false;
        }
        final Object this$functionCode = this.getFunctionCode();
        final Object other$functionCode = other.getFunctionCode();
        Label_0918: {
            if (this$functionCode == null) {
                if (other$functionCode == null) {
                    break Label_0918;
                }
            }
            else if (this$functionCode.equals(other$functionCode)) {
                break Label_0918;
            }
            return false;
        }
        final Object this$fileProcDate = this.getFileProcDate();
        final Object other$fileProcDate = other.getFileProcDate();
        Label_0955: {
            if (this$fileProcDate == null) {
                if (other$fileProcDate == null) {
                    break Label_0955;
                }
            }
            else if (this$fileProcDate.equals(other$fileProcDate)) {
                break Label_0955;
            }
            return false;
        }
        final Object this$cardNumber = this.getCardNumber();
        final Object other$cardNumber = other.getCardNumber();
        Label_0992: {
            if (this$cardNumber == null) {
                if (other$cardNumber == null) {
                    break Label_0992;
                }
            }
            else if (this$cardNumber.equals(other$cardNumber)) {
                break Label_0992;
            }
            return false;
        }
        final Object this$localDateTime = this.getLocalDateTime();
        final Object other$localDateTime = other.getLocalDateTime();
        Label_1029: {
            if (this$localDateTime == null) {
                if (other$localDateTime == null) {
                    break Label_1029;
                }
            }
            else if (this$localDateTime.equals(other$localDateTime)) {
                break Label_1029;
            }
            return false;
        }
        final Object this$expiryDate = this.getExpiryDate();
        final Object other$expiryDate = other.getExpiryDate();
        Label_1066: {
            if (this$expiryDate == null) {
                if (other$expiryDate == null) {
                    break Label_1066;
                }
            }
            else if (this$expiryDate.equals(other$expiryDate)) {
                break Label_1066;
            }
            return false;
        }
        final Object this$posDataCode = this.getPosDataCode();
        final Object other$posDataCode = other.getPosDataCode();
        Label_1103: {
            if (this$posDataCode == null) {
                if (other$posDataCode == null) {
                    break Label_1103;
                }
            }
            else if (this$posDataCode.equals(other$posDataCode)) {
                break Label_1103;
            }
            return false;
        }
        final Object this$msgReasonCode = this.getMsgReasonCode();
        final Object other$msgReasonCode = other.getMsgReasonCode();
        Label_1140: {
            if (this$msgReasonCode == null) {
                if (other$msgReasonCode == null) {
                    break Label_1140;
                }
            }
            else if (this$msgReasonCode.equals(other$msgReasonCode)) {
                break Label_1140;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_1177: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_1177;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_1177;
            }
            return false;
        }
        final Object this$acqRefData = this.getAcqRefData();
        final Object other$acqRefData = other.getAcqRefData();
        Label_1214: {
            if (this$acqRefData == null) {
                if (other$acqRefData == null) {
                    break Label_1214;
                }
            }
            else if (this$acqRefData.equals(other$acqRefData)) {
                break Label_1214;
            }
            return false;
        }
        final Object this$acqinstIdCode = this.getAcqinstIdCode();
        final Object other$acqinstIdCode = other.getAcqinstIdCode();
        Label_1251: {
            if (this$acqinstIdCode == null) {
                if (other$acqinstIdCode == null) {
                    break Label_1251;
                }
            }
            else if (this$acqinstIdCode.equals(other$acqinstIdCode)) {
                break Label_1251;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_1288: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_1288;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_1288;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_1325: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_1325;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_1325;
            }
            return false;
        }
        final Object this$responseCode = this.getResponseCode();
        final Object other$responseCode = other.getResponseCode();
        Label_1362: {
            if (this$responseCode == null) {
                if (other$responseCode == null) {
                    break Label_1362;
                }
            }
            else if (this$responseCode.equals(other$responseCode)) {
                break Label_1362;
            }
            return false;
        }
        final Object this$serviceCode = this.getServiceCode();
        final Object other$serviceCode = other.getServiceCode();
        Label_1399: {
            if (this$serviceCode == null) {
                if (other$serviceCode == null) {
                    break Label_1399;
                }
            }
            else if (this$serviceCode.equals(other$serviceCode)) {
                break Label_1399;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_1436: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_1436;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_1436;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_1473: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_1473;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_1473;
            }
            return false;
        }
        final Object this$meName = this.getMeName();
        final Object other$meName = other.getMeName();
        Label_1510: {
            if (this$meName == null) {
                if (other$meName == null) {
                    break Label_1510;
                }
            }
            else if (this$meName.equals(other$meName)) {
                break Label_1510;
            }
            return false;
        }
        final Object this$meCity = this.getMeCity();
        final Object other$meCity = other.getMeCity();
        Label_1547: {
            if (this$meCity == null) {
                if (other$meCity == null) {
                    break Label_1547;
                }
            }
            else if (this$meCity.equals(other$meCity)) {
                break Label_1547;
            }
            return false;
        }
        final Object this$mePinCode = this.getMePinCode();
        final Object other$mePinCode = other.getMePinCode();
        Label_1584: {
            if (this$mePinCode == null) {
                if (other$mePinCode == null) {
                    break Label_1584;
                }
            }
            else if (this$mePinCode.equals(other$mePinCode)) {
                break Label_1584;
            }
            return false;
        }
        final Object this$meCountry = this.getMeCountry();
        final Object other$meCountry = other.getMeCountry();
        Label_1621: {
            if (this$meCountry == null) {
                if (other$meCountry == null) {
                    break Label_1621;
                }
            }
            else if (this$meCountry.equals(other$meCountry)) {
                break Label_1621;
            }
            return false;
        }
        final Object this$trlType = this.getTrlType();
        final Object other$trlType = other.getTrlType();
        Label_1658: {
            if (this$trlType == null) {
                if (other$trlType == null) {
                    break Label_1658;
                }
            }
            else if (this$trlType.equals(other$trlType)) {
                break Label_1658;
            }
            return false;
        }
        final Object this$motoEcomIndicator = this.getMotoEcomIndicator();
        final Object other$motoEcomIndicator = other.getMotoEcomIndicator();
        Label_1695: {
            if (this$motoEcomIndicator == null) {
                if (other$motoEcomIndicator == null) {
                    break Label_1695;
                }
            }
            else if (this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
                break Label_1695;
            }
            return false;
        }
        final Object this$txnCurCode = this.getTxnCurCode();
        final Object other$txnCurCode = other.getTxnCurCode();
        Label_1732: {
            if (this$txnCurCode == null) {
                if (other$txnCurCode == null) {
                    break Label_1732;
                }
            }
            else if (this$txnCurCode.equals(other$txnCurCode)) {
                break Label_1732;
            }
            return false;
        }
        final Object this$ird = this.getIrd();
        final Object other$ird = other.getIrd();
        Label_1769: {
            if (this$ird == null) {
                if (other$ird == null) {
                    break Label_1769;
                }
            }
            else if (this$ird.equals(other$ird)) {
                break Label_1769;
            }
            return false;
        }
        final Object this$cardSeqNumber = this.getCardSeqNumber();
        final Object other$cardSeqNumber = other.getCardSeqNumber();
        Label_1806: {
            if (this$cardSeqNumber == null) {
                if (other$cardSeqNumber == null) {
                    break Label_1806;
                }
            }
            else if (this$cardSeqNumber.equals(other$cardSeqNumber)) {
                break Label_1806;
            }
            return false;
        }
        final Object this$appCryptogram = this.getAppCryptogram();
        final Object other$appCryptogram = other.getAppCryptogram();
        Label_1843: {
            if (this$appCryptogram == null) {
                if (other$appCryptogram == null) {
                    break Label_1843;
                }
            }
            else if (this$appCryptogram.equals(other$appCryptogram)) {
                break Label_1843;
            }
            return false;
        }
        final Object this$cryptInfoData = this.getCryptInfoData();
        final Object other$cryptInfoData = other.getCryptInfoData();
        Label_1880: {
            if (this$cryptInfoData == null) {
                if (other$cryptInfoData == null) {
                    break Label_1880;
                }
            }
            else if (this$cryptInfoData.equals(other$cryptInfoData)) {
                break Label_1880;
            }
            return false;
        }
        final Object this$issAppData = this.getIssAppData();
        final Object other$issAppData = other.getIssAppData();
        Label_1917: {
            if (this$issAppData == null) {
                if (other$issAppData == null) {
                    break Label_1917;
                }
            }
            else if (this$issAppData.equals(other$issAppData)) {
                break Label_1917;
            }
            return false;
        }
        final Object this$upblNumber = this.getUpblNumber();
        final Object other$upblNumber = other.getUpblNumber();
        Label_1954: {
            if (this$upblNumber == null) {
                if (other$upblNumber == null) {
                    break Label_1954;
                }
            }
            else if (this$upblNumber.equals(other$upblNumber)) {
                break Label_1954;
            }
            return false;
        }
        final Object this$appTxnCounter = this.getAppTxnCounter();
        final Object other$appTxnCounter = other.getAppTxnCounter();
        Label_1991: {
            if (this$appTxnCounter == null) {
                if (other$appTxnCounter == null) {
                    break Label_1991;
                }
            }
            else if (this$appTxnCounter.equals(other$appTxnCounter)) {
                break Label_1991;
            }
            return false;
        }
        final Object this$trlVerResult = this.getTrlVerResult();
        final Object other$trlVerResult = other.getTrlVerResult();
        Label_2028: {
            if (this$trlVerResult == null) {
                if (other$trlVerResult == null) {
                    break Label_2028;
                }
            }
            else if (this$trlVerResult.equals(other$trlVerResult)) {
                break Label_2028;
            }
            return false;
        }
        final Object this$txnDate = this.getTxnDate();
        final Object other$txnDate = other.getTxnDate();
        Label_2065: {
            if (this$txnDate == null) {
                if (other$txnDate == null) {
                    break Label_2065;
                }
            }
            else if (this$txnDate.equals(other$txnDate)) {
                break Label_2065;
            }
            return false;
        }
        final Object this$chipTxnDate = this.getChipTxnDate();
        final Object other$chipTxnDate = other.getChipTxnDate();
        Label_2102: {
            if (this$chipTxnDate == null) {
                if (other$chipTxnDate == null) {
                    break Label_2102;
                }
            }
            else if (this$chipTxnDate.equals(other$chipTxnDate)) {
                break Label_2102;
            }
            return false;
        }
        final Object this$chipTxnType = this.getChipTxnType();
        final Object other$chipTxnType = other.getChipTxnType();
        Label_2139: {
            if (this$chipTxnType == null) {
                if (other$chipTxnType == null) {
                    break Label_2139;
                }
            }
            else if (this$chipTxnType.equals(other$chipTxnType)) {
                break Label_2139;
            }
            return false;
        }
        final Object this$appICProfile = this.getAppICProfile();
        final Object other$appICProfile = other.getAppICProfile();
        Label_2176: {
            if (this$appICProfile == null) {
                if (other$appICProfile == null) {
                    break Label_2176;
                }
            }
            else if (this$appICProfile.equals(other$appICProfile)) {
                break Label_2176;
            }
            return false;
        }
        final Object this$trlConCode = this.getTrlConCode();
        final Object other$trlConCode = other.getTrlConCode();
        Label_2213: {
            if (this$trlConCode == null) {
                if (other$trlConCode == null) {
                    break Label_2213;
                }
            }
            else if (this$trlConCode.equals(other$trlConCode)) {
                break Label_2213;
            }
            return false;
        }
        final Object this$cvmResult = this.getCvmResult();
        final Object other$cvmResult = other.getCvmResult();
        Label_2250: {
            if (this$cvmResult == null) {
                if (other$cvmResult == null) {
                    break Label_2250;
                }
            }
            else if (this$cvmResult.equals(other$cvmResult)) {
                break Label_2250;
            }
            return false;
        }
        final Object this$trlCapabilities = this.getTrlCapabilities();
        final Object other$trlCapabilities = other.getTrlCapabilities();
        Label_2287: {
            if (this$trlCapabilities == null) {
                if (other$trlCapabilities == null) {
                    break Label_2287;
                }
            }
            else if (this$trlCapabilities.equals(other$trlCapabilities)) {
                break Label_2287;
            }
            return false;
        }
        final Object this$ifdSerNumber = this.getIfdSerNumber();
        final Object other$ifdSerNumber = other.getIfdSerNumber();
        Label_2324: {
            if (this$ifdSerNumber == null) {
                if (other$ifdSerNumber == null) {
                    break Label_2324;
                }
            }
            else if (this$ifdSerNumber.equals(other$ifdSerNumber)) {
                break Label_2324;
            }
            return false;
        }
        final Object this$tcc = this.getTcc();
        final Object other$tcc = other.getTcc();
        Label_2361: {
            if (this$tcc == null) {
                if (other$tcc == null) {
                    break Label_2361;
                }
            }
            else if (this$tcc.equals(other$tcc)) {
                break Label_2361;
            }
            return false;
        }
        final Object this$chipCurCode = this.getChipCurCode();
        final Object other$chipCurCode = other.getChipCurCode();
        Label_2398: {
            if (this$chipCurCode == null) {
                if (other$chipCurCode == null) {
                    break Label_2398;
                }
            }
            else if (this$chipCurCode.equals(other$chipCurCode)) {
                break Label_2398;
            }
            return false;
        }
        final Object this$chipTrlType = this.getChipTrlType();
        final Object other$chipTrlType = other.getChipTrlType();
        Label_2435: {
            if (this$chipTrlType == null) {
                if (other$chipTrlType == null) {
                    break Label_2435;
                }
            }
            else if (this$chipTrlType.equals(other$chipTrlType)) {
                break Label_2435;
            }
            return false;
        }
        final Object this$trlAppVerNumber = this.getTrlAppVerNumber();
        final Object other$trlAppVerNumber = other.getTrlAppVerNumber();
        Label_2472: {
            if (this$trlAppVerNumber == null) {
                if (other$trlAppVerNumber == null) {
                    break Label_2472;
                }
            }
            else if (this$trlAppVerNumber.equals(other$trlAppVerNumber)) {
                break Label_2472;
            }
            return false;
        }
        final Object this$txnSeqCounter = this.getTxnSeqCounter();
        final Object other$txnSeqCounter = other.getTxnSeqCounter();
        Label_2509: {
            if (this$txnSeqCounter == null) {
                if (other$txnSeqCounter == null) {
                    break Label_2509;
                }
            }
            else if (this$txnSeqCounter.equals(other$txnSeqCounter)) {
                break Label_2509;
            }
            return false;
        }
        final Object this$issAuthData = this.getIssAuthData();
        final Object other$issAuthData = other.getIssAuthData();
        Label_2546: {
            if (this$issAuthData == null) {
                if (other$issAuthData == null) {
                    break Label_2546;
                }
            }
            else if (this$issAuthData.equals(other$issAuthData)) {
                break Label_2546;
            }
            return false;
        }
        final Object this$txnlifeCycleId = this.getTxnlifeCycleId();
        final Object other$txnlifeCycleId = other.getTxnlifeCycleId();
        Label_2583: {
            if (this$txnlifeCycleId == null) {
                if (other$txnlifeCycleId == null) {
                    break Label_2583;
                }
            }
            else if (this$txnlifeCycleId.equals(other$txnlifeCycleId)) {
                break Label_2583;
            }
            return false;
        }
        final Object this$msgNumber = this.getMsgNumber();
        final Object other$msgNumber = other.getMsgNumber();
        Label_2620: {
            if (this$msgNumber == null) {
                if (other$msgNumber == null) {
                    break Label_2620;
                }
            }
            else if (this$msgNumber.equals(other$msgNumber)) {
                break Label_2620;
            }
            return false;
        }
        final Object this$memberText = this.getMemberText();
        final Object other$memberText = other.getMemberText();
        Label_2657: {
            if (this$memberText == null) {
                if (other$memberText == null) {
                    break Label_2657;
                }
            }
            else if (this$memberText.equals(other$memberText)) {
                break Label_2657;
            }
            return false;
        }
        final Object this$orgInstIdCode = this.getOrgInstIdCode();
        final Object other$orgInstIdCode = other.getOrgInstIdCode();
        Label_2694: {
            if (this$orgInstIdCode == null) {
                if (other$orgInstIdCode == null) {
                    break Label_2694;
                }
            }
            else if (this$orgInstIdCode.equals(other$orgInstIdCode)) {
                break Label_2694;
            }
            return false;
        }
        final Object this$maid = this.getMaid();
        final Object other$maid = other.getMaid();
        Label_2731: {
            if (this$maid == null) {
                if (other$maid == null) {
                    break Label_2731;
                }
            }
            else if (this$maid.equals(other$maid)) {
                break Label_2731;
            }
            return false;
        }
        final Object this$posPgType = this.getPosPgType();
        final Object other$posPgType = other.getPosPgType();
        Label_2768: {
            if (this$posPgType == null) {
                if (other$posPgType == null) {
                    break Label_2768;
                }
            }
            else if (this$posPgType.equals(other$posPgType)) {
                break Label_2768;
            }
            return false;
        }
        final Object this$centreProcDate = this.getCentreProcDate();
        final Object other$centreProcDate = other.getCentreProcDate();
        Label_2805: {
            if (this$centreProcDate == null) {
                if (other$centreProcDate == null) {
                    break Label_2805;
                }
            }
            else if (this$centreProcDate.equals(other$centreProcDate)) {
                break Label_2805;
            }
            return false;
        }
        final Object this$encryptedCardNumber = this.getEncryptedCardNumber();
        final Object other$encryptedCardNumber = other.getEncryptedCardNumber();
        Label_2842: {
            if (this$encryptedCardNumber == null) {
                if (other$encryptedCardNumber == null) {
                    break Label_2842;
                }
            }
            else if (this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
                break Label_2842;
            }
            return false;
        }
        final Object this$meCountryOfOrigin = this.getMeCountryOfOrigin();
        final Object other$meCountryOfOrigin = other.getMeCountryOfOrigin();
        Label_2879: {
            if (this$meCountryOfOrigin == null) {
                if (other$meCountryOfOrigin == null) {
                    break Label_2879;
                }
            }
            else if (this$meCountryOfOrigin.equals(other$meCountryOfOrigin)) {
                break Label_2879;
            }
            return false;
        }
        final Object this$apiResponse = this.getApiResponse();
        final Object other$apiResponse = other.getApiResponse();
        if (this$apiResponse == null) {
            if (other$apiResponse == null) {
                return true;
            }
        }
        else if (this$apiResponse.equals(other$apiResponse)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof McRTSTxnDataEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInstitutionCode();
        result = result * 59 + this.getGeneralStatus();
        result = result * 59 + this.getTxnCurrencyExponent();
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + (($prjSerNumber == null) ? 43 : $prjSerNumber.hashCode());
        final Object $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + (($txnRefNumber == null) ? 43 : $txnRefNumber.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + (($surchargeAmount == null) ? 43 : $surchargeAmount.hashCode());
        final Object $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + (($txnFeeAmount == null) ? 43 : $txnFeeAmount.hashCode());
        final Object $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + (($settlementIndicator == null) ? 43 : $settlementIndicator.hashCode());
        final Object $cryptAmount = this.getCryptAmount();
        result = result * 59 + (($cryptAmount == null) ? 43 : $cryptAmount.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $revIndiCator = this.getRevIndiCator();
        result = result * 59 + (($revIndiCator == null) ? 43 : $revIndiCator.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + (($dmsSmsMode == null) ? 43 : $dmsSmsMode.hashCode());
        final Object $tipAmount = this.getTipAmount();
        result = result * 59 + (($tipAmount == null) ? 43 : $tipAmount.hashCode());
        final Object $mposAccDevType = this.getMposAccDevType();
        result = result * 59 + (($mposAccDevType == null) ? 43 : $mposAccDevType.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $fileID = this.getFileID();
        result = result * 59 + (($fileID == null) ? 43 : $fileID.hashCode());
        final Object $messageTypeId = this.getMessageTypeId();
        result = result * 59 + (($messageTypeId == null) ? 43 : $messageTypeId.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        final Object $functionCode = this.getFunctionCode();
        result = result * 59 + (($functionCode == null) ? 43 : $functionCode.hashCode());
        final Object $fileProcDate = this.getFileProcDate();
        result = result * 59 + (($fileProcDate == null) ? 43 : $fileProcDate.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * 59 + (($expiryDate == null) ? 43 : $expiryDate.hashCode());
        final Object $posDataCode = this.getPosDataCode();
        result = result * 59 + (($posDataCode == null) ? 43 : $posDataCode.hashCode());
        final Object $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + (($msgReasonCode == null) ? 43 : $msgReasonCode.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $acqRefData = this.getAcqRefData();
        result = result * 59 + (($acqRefData == null) ? 43 : $acqRefData.hashCode());
        final Object $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + (($acqinstIdCode == null) ? 43 : $acqinstIdCode.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $mePinCode = this.getMePinCode();
        result = result * 59 + (($mePinCode == null) ? 43 : $mePinCode.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $trlType = this.getTrlType();
        result = result * 59 + (($trlType == null) ? 43 : $trlType.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        final Object $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + (($cardSeqNumber == null) ? 43 : $cardSeqNumber.hashCode());
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
        final Object $txnDate = this.getTxnDate();
        result = result * 59 + (($txnDate == null) ? 43 : $txnDate.hashCode());
        final Object $chipTxnDate = this.getChipTxnDate();
        result = result * 59 + (($chipTxnDate == null) ? 43 : $chipTxnDate.hashCode());
        final Object $chipTxnType = this.getChipTxnType();
        result = result * 59 + (($chipTxnType == null) ? 43 : $chipTxnType.hashCode());
        final Object $appICProfile = this.getAppICProfile();
        result = result * 59 + (($appICProfile == null) ? 43 : $appICProfile.hashCode());
        final Object $trlConCode = this.getTrlConCode();
        result = result * 59 + (($trlConCode == null) ? 43 : $trlConCode.hashCode());
        final Object $cvmResult = this.getCvmResult();
        result = result * 59 + (($cvmResult == null) ? 43 : $cvmResult.hashCode());
        final Object $trlCapabilities = this.getTrlCapabilities();
        result = result * 59 + (($trlCapabilities == null) ? 43 : $trlCapabilities.hashCode());
        final Object $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + (($ifdSerNumber == null) ? 43 : $ifdSerNumber.hashCode());
        final Object $tcc = this.getTcc();
        result = result * 59 + (($tcc == null) ? 43 : $tcc.hashCode());
        final Object $chipCurCode = this.getChipCurCode();
        result = result * 59 + (($chipCurCode == null) ? 43 : $chipCurCode.hashCode());
        final Object $chipTrlType = this.getChipTrlType();
        result = result * 59 + (($chipTrlType == null) ? 43 : $chipTrlType.hashCode());
        final Object $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + (($trlAppVerNumber == null) ? 43 : $trlAppVerNumber.hashCode());
        final Object $txnSeqCounter = this.getTxnSeqCounter();
        result = result * 59 + (($txnSeqCounter == null) ? 43 : $txnSeqCounter.hashCode());
        final Object $issAuthData = this.getIssAuthData();
        result = result * 59 + (($issAuthData == null) ? 43 : $issAuthData.hashCode());
        final Object $txnlifeCycleId = this.getTxnlifeCycleId();
        result = result * 59 + (($txnlifeCycleId == null) ? 43 : $txnlifeCycleId.hashCode());
        final Object $msgNumber = this.getMsgNumber();
        result = result * 59 + (($msgNumber == null) ? 43 : $msgNumber.hashCode());
        final Object $memberText = this.getMemberText();
        result = result * 59 + (($memberText == null) ? 43 : $memberText.hashCode());
        final Object $orgInstIdCode = this.getOrgInstIdCode();
        result = result * 59 + (($orgInstIdCode == null) ? 43 : $orgInstIdCode.hashCode());
        final Object $maid = this.getMaid();
        result = result * 59 + (($maid == null) ? 43 : $maid.hashCode());
        final Object $posPgType = this.getPosPgType();
        result = result * 59 + (($posPgType == null) ? 43 : $posPgType.hashCode());
        final Object $centreProcDate = this.getCentreProcDate();
        result = result * 59 + (($centreProcDate == null) ? 43 : $centreProcDate.hashCode());
        final Object $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + (($encryptedCardNumber == null) ? 43 : $encryptedCardNumber.hashCode());
        final Object $meCountryOfOrigin = this.getMeCountryOfOrigin();
        result = result * 59 + (($meCountryOfOrigin == null) ? 43 : $meCountryOfOrigin.hashCode());
        final Object $apiResponse = this.getApiResponse();
        result = result * 59 + (($apiResponse == null) ? 43 : $apiResponse.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "McRTSTxnDataEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", generalStatus=" + this.getGeneralStatus() + ", txnRefNumber=" + this.getTxnRefNumber() + ", txnType=" + this.getTxnType() + ", fileID=" + this.getFileID() + ", messageTypeId=" + this.getMessageTypeId() + ", procCode=" + this.getProcCode() + ", functionCode=" + this.getFunctionCode() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", cardNumber=" + this.getCardNumber() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", expiryDate=" + this.getExpiryDate() + ", posDataCode=" + this.getPosDataCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", mcc=" + this.getMcc() + ", acqRefData=" + this.getAcqRefData() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", rrn=" + this.getRrn() + ", approvalCode=" + this.getApprovalCode() + ", responseCode=" + this.getResponseCode() + ", serviceCode=" + this.getServiceCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", mePinCode=" + this.getMePinCode() + ", meCountry=" + this.getMeCountry() + ", trlType=" + this.getTrlType() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", txnCurrencyExponent=" + this.getTxnCurrencyExponent() + ", txnCurCode=" + this.getTxnCurCode() + ", ird=" + this.getIrd() + ", settlementIndicator=" + this.getSettlementIndicator() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", issAppData=" + this.getIssAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", trlVerResult=" + this.getTrlVerResult() + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", chipTxnDate=" + this.getChipTxnDate() + ", chipTxnType=" + this.getChipTxnType() + ", cryptAmount=" + this.getCryptAmount() + ", appICProfile=" + this.getAppICProfile() + ", trlConCode=" + this.getTrlConCode() + ", cashBackAmount=" + this.getCashBackAmount() + ", cvmResult=" + this.getCvmResult() + ", trlCapabilities=" + this.getTrlCapabilities() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", tcc=" + this.getTcc() + ", chipCurCode=" + this.getChipCurCode() + ", chipTrlType=" + this.getChipTrlType() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", txnSeqCounter=" + this.getTxnSeqCounter() + ", issAuthData=" + this.getIssAuthData() + ", txnlifeCycleId=" + this.getTxnlifeCycleId() + ", msgNumber=" + this.getMsgNumber() + ", memberText=" + this.getMemberText() + ", orgInstIdCode=" + this.getOrgInstIdCode() + ", revIndiCator=" + this.getRevIndiCator() + ", maid=" + this.getMaid() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", posPgType=" + this.getPosPgType() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", meCountryOfOrigin=" + this.getMeCountryOfOrigin() + ", apiResponse=" + this.getApiResponse() + ", tipAmount=" + this.getTipAmount() + ", mposAccDevType=" + this.getMposAccDevType();
    }
    
    public McRTSTxnDataEntity() {
    }
    
    public McRTSTxnDataEntity(final Integer serNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final int institutionCode, final Integer intCode, final Integer prjSerNumber, final int generalStatus, final Integer txnRefNumber, final String txnType, final String fileID, final String messageTypeId, final String procCode, final String functionCode, final Double txnAmount, final Double surchargeAmount, final LocalDate fileProcDate, final String cardNumber, final LocalDateTime localDateTime, final String expiryDate, final String posDataCode, final String msgReasonCode, final String mcc, final String acqRefData, final String acqinstIdCode, final String rrn, final String approvalCode, final String responseCode, final String serviceCode, final String terminalId, final String merchantId, final String meName, final String meCity, final String mePinCode, final String meCountry, final String trlType, final String motoEcomIndicator, final Double txnFeeAmount, final int txnCurrencyExponent, final String txnCurCode, final String ird, final Character settlementIndicator, final String cardSeqNumber, final String appCryptogram, final String cryptInfoData, final String issAppData, final String upblNumber, final String appTxnCounter, final String trlVerResult, final LocalDate txnDate, final String chipTxnDate, final String chipTxnType, final Double cryptAmount, final String appICProfile, final String trlConCode, final Double cashBackAmount, final String cvmResult, final String trlCapabilities, final String ifdSerNumber, final String tcc, final String chipCurCode, final String chipTrlType, final String trlAppVerNumber, final String txnSeqCounter, final String issAuthData, final String txnlifeCycleId, final String msgNumber, final String memberText, final String orgInstIdCode, final Character revIndiCator, final String maid, final Character cardType, final Character cardDomIntlFlag, final Character dmsSmsMode, final String posPgType, final LocalDate centreProcDate, final String encryptedCardNumber, final String meCountryOfOrigin, final String apiResponse, final Double tipAmount, final Character mposAccDevType) {
        this.serNumber = serNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.intCode = intCode;
        this.prjSerNumber = prjSerNumber;
        this.generalStatus = generalStatus;
        this.txnRefNumber = txnRefNumber;
        this.txnType = txnType;
        this.fileID = fileID;
        this.messageTypeId = messageTypeId;
        this.procCode = procCode;
        this.functionCode = functionCode;
        this.txnAmount = txnAmount;
        this.surchargeAmount = surchargeAmount;
        this.fileProcDate = fileProcDate;
        this.cardNumber = cardNumber;
        this.localDateTime = localDateTime;
        this.expiryDate = expiryDate;
        this.posDataCode = posDataCode;
        this.msgReasonCode = msgReasonCode;
        this.mcc = mcc;
        this.acqRefData = acqRefData;
        this.acqinstIdCode = acqinstIdCode;
        this.rrn = rrn;
        this.approvalCode = approvalCode;
        this.responseCode = responseCode;
        this.serviceCode = serviceCode;
        this.terminalId = terminalId;
        this.merchantId = merchantId;
        this.meName = meName;
        this.meCity = meCity;
        this.mePinCode = mePinCode;
        this.meCountry = meCountry;
        this.trlType = trlType;
        this.motoEcomIndicator = motoEcomIndicator;
        this.txnFeeAmount = txnFeeAmount;
        this.txnCurrencyExponent = txnCurrencyExponent;
        this.txnCurCode = txnCurCode;
        this.ird = ird;
        this.settlementIndicator = settlementIndicator;
        this.cardSeqNumber = cardSeqNumber;
        this.appCryptogram = appCryptogram;
        this.cryptInfoData = cryptInfoData;
        this.issAppData = issAppData;
        this.upblNumber = upblNumber;
        this.appTxnCounter = appTxnCounter;
        this.trlVerResult = trlVerResult;
        this.txnDate = txnDate;
        this.chipTxnDate = chipTxnDate;
        this.chipTxnType = chipTxnType;
        this.cryptAmount = cryptAmount;
        this.appICProfile = appICProfile;
        this.trlConCode = trlConCode;
        this.cashBackAmount = cashBackAmount;
        this.cvmResult = cvmResult;
        this.trlCapabilities = trlCapabilities;
        this.ifdSerNumber = ifdSerNumber;
        this.tcc = tcc;
        this.chipCurCode = chipCurCode;
        this.chipTrlType = chipTrlType;
        this.trlAppVerNumber = trlAppVerNumber;
        this.txnSeqCounter = txnSeqCounter;
        this.issAuthData = issAuthData;
        this.txnlifeCycleId = txnlifeCycleId;
        this.msgNumber = msgNumber;
        this.memberText = memberText;
        this.orgInstIdCode = orgInstIdCode;
        this.revIndiCator = revIndiCator;
        this.maid = maid;
        this.cardType = cardType;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.dmsSmsMode = dmsSmsMode;
        this.posPgType = posPgType;
        this.centreProcDate = centreProcDate;
        this.encryptedCardNumber = encryptedCardNumber;
        this.meCountryOfOrigin = meCountryOfOrigin;
        this.apiResponse = apiResponse;
        this.tipAmount = tipAmount;
        this.mposAccDevType = mposAccDevType;
    }
    
    public static class McRTSTxnDataEntityBuilder
    {
        private Integer serNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private int institutionCode;
        private Integer intCode;
        private Integer prjSerNumber;
        private int generalStatus;
        private Integer txnRefNumber;
        private String txnType;
        private String fileID;
        private String messageTypeId;
        private String procCode;
        private String functionCode;
        private Double txnAmount;
        private Double surchargeAmount;
        private LocalDate fileProcDate;
        private String cardNumber;
        private LocalDateTime localDateTime;
        private String expiryDate;
        private String posDataCode;
        private String msgReasonCode;
        private String mcc;
        private String acqRefData;
        private String acqinstIdCode;
        private String rrn;
        private String approvalCode;
        private String responseCode;
        private String serviceCode;
        private String terminalId;
        private String merchantId;
        private String meName;
        private String meCity;
        private String mePinCode;
        private String meCountry;
        private String trlType;
        private String motoEcomIndicator;
        private Double txnFeeAmount;
        private int txnCurrencyExponent;
        private String txnCurCode;
        private String ird;
        private Character settlementIndicator;
        private String cardSeqNumber;
        private String appCryptogram;
        private String cryptInfoData;
        private String issAppData;
        private String upblNumber;
        private String appTxnCounter;
        private String trlVerResult;
        private LocalDate txnDate;
        private String chipTxnDate;
        private String chipTxnType;
        private Double cryptAmount;
        private String appICProfile;
        private String trlConCode;
        private Double cashBackAmount;
        private String cvmResult;
        private String trlCapabilities;
        private String ifdSerNumber;
        private String tcc;
        private String chipCurCode;
        private String chipTrlType;
        private String trlAppVerNumber;
        private String txnSeqCounter;
        private String issAuthData;
        private String txnlifeCycleId;
        private String msgNumber;
        private String memberText;
        private String orgInstIdCode;
        private Character revIndiCator;
        private String maid;
        private Character cardType;
        private Character cardDomIntlFlag;
        private Character dmsSmsMode;
        private String posPgType;
        private LocalDate centreProcDate;
        private String encryptedCardNumber;
        private String meCountryOfOrigin;
        private String apiResponse;
        private Double tipAmount;
        private Character mposAccDevType;
        
        McRTSTxnDataEntityBuilder() {
        }
        
        public McRTSTxnDataEntityBuilder serNumber(final Integer serNumber) {
            this.serNumber = serNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder institutionCode(final int institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder generalStatus(final int generalStatus) {
            this.generalStatus = generalStatus;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnRefNumber(final Integer txnRefNumber) {
            this.txnRefNumber = txnRefNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder fileID(final String fileID) {
            this.fileID = fileID;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder messageTypeId(final String messageTypeId) {
            this.messageTypeId = messageTypeId;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder procCode(final String procCode) {
            this.procCode = procCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder functionCode(final String functionCode) {
            this.functionCode = functionCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder surchargeAmount(final Double surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder fileProcDate(final LocalDate fileProcDate) {
            this.fileProcDate = fileProcDate;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder expiryDate(final String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder posDataCode(final String posDataCode) {
            this.posDataCode = posDataCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder msgReasonCode(final String msgReasonCode) {
            this.msgReasonCode = msgReasonCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder acqRefData(final String acqRefData) {
            this.acqRefData = acqRefData;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder acqinstIdCode(final String acqinstIdCode) {
            this.acqinstIdCode = acqinstIdCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder responseCode(final String responseCode) {
            this.responseCode = responseCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder serviceCode(final String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder mePinCode(final String mePinCode) {
            this.mePinCode = mePinCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder trlType(final String trlType) {
            this.trlType = trlType;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder motoEcomIndicator(final String motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnFeeAmount(final Double txnFeeAmount) {
            this.txnFeeAmount = txnFeeAmount;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnCurrencyExponent(final int txnCurrencyExponent) {
            this.txnCurrencyExponent = txnCurrencyExponent;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder ird(final String ird) {
            this.ird = ird;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder settlementIndicator(final Character settlementIndicator) {
            this.settlementIndicator = settlementIndicator;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cardSeqNumber(final String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder appCryptogram(final String appCryptogram) {
            this.appCryptogram = appCryptogram;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cryptInfoData(final String cryptInfoData) {
            this.cryptInfoData = cryptInfoData;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder issAppData(final String issAppData) {
            this.issAppData = issAppData;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder upblNumber(final String upblNumber) {
            this.upblNumber = upblNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder appTxnCounter(final String appTxnCounter) {
            this.appTxnCounter = appTxnCounter;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder trlVerResult(final String trlVerResult) {
            this.trlVerResult = trlVerResult;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnDate(final LocalDate txnDate) {
            this.txnDate = txnDate;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder chipTxnDate(final String chipTxnDate) {
            this.chipTxnDate = chipTxnDate;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder chipTxnType(final String chipTxnType) {
            this.chipTxnType = chipTxnType;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cryptAmount(final Double cryptAmount) {
            this.cryptAmount = cryptAmount;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder appICProfile(final String appICProfile) {
            this.appICProfile = appICProfile;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder trlConCode(final String trlConCode) {
            this.trlConCode = trlConCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cvmResult(final String cvmResult) {
            this.cvmResult = cvmResult;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder trlCapabilities(final String trlCapabilities) {
            this.trlCapabilities = trlCapabilities;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder ifdSerNumber(final String ifdSerNumber) {
            this.ifdSerNumber = ifdSerNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder tcc(final String tcc) {
            this.tcc = tcc;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder chipCurCode(final String chipCurCode) {
            this.chipCurCode = chipCurCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder chipTrlType(final String chipTrlType) {
            this.chipTrlType = chipTrlType;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder trlAppVerNumber(final String trlAppVerNumber) {
            this.trlAppVerNumber = trlAppVerNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnSeqCounter(final String txnSeqCounter) {
            this.txnSeqCounter = txnSeqCounter;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder issAuthData(final String issAuthData) {
            this.issAuthData = issAuthData;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder txnlifeCycleId(final String txnlifeCycleId) {
            this.txnlifeCycleId = txnlifeCycleId;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder msgNumber(final String msgNumber) {
            this.msgNumber = msgNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder memberText(final String memberText) {
            this.memberText = memberText;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder orgInstIdCode(final String orgInstIdCode) {
            this.orgInstIdCode = orgInstIdCode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder revIndiCator(final Character revIndiCator) {
            this.revIndiCator = revIndiCator;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder maid(final String maid) {
            this.maid = maid;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder dmsSmsMode(final Character dmsSmsMode) {
            this.dmsSmsMode = dmsSmsMode;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder posPgType(final String posPgType) {
            this.posPgType = posPgType;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder centreProcDate(final LocalDate centreProcDate) {
            this.centreProcDate = centreProcDate;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder encryptedCardNumber(final String encryptedCardNumber) {
            this.encryptedCardNumber = encryptedCardNumber;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder meCountryOfOrigin(final String meCountryOfOrigin) {
            this.meCountryOfOrigin = meCountryOfOrigin;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder apiResponse(final String apiResponse) {
            this.apiResponse = apiResponse;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder tipAmount(final Double tipAmount) {
            this.tipAmount = tipAmount;
            return this;
        }
        
        public McRTSTxnDataEntityBuilder mposAccDevType(final Character mposAccDevType) {
            this.mposAccDevType = mposAccDevType;
            return this;
        }
        
        public McRTSTxnDataEntity build() {
            return new McRTSTxnDataEntity(this.serNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.intCode, this.prjSerNumber, this.generalStatus, this.txnRefNumber, this.txnType, this.fileID, this.messageTypeId, this.procCode, this.functionCode, this.txnAmount, this.surchargeAmount, this.fileProcDate, this.cardNumber, this.localDateTime, this.expiryDate, this.posDataCode, this.msgReasonCode, this.mcc, this.acqRefData, this.acqinstIdCode, this.rrn, this.approvalCode, this.responseCode, this.serviceCode, this.terminalId, this.merchantId, this.meName, this.meCity, this.mePinCode, this.meCountry, this.trlType, this.motoEcomIndicator, this.txnFeeAmount, this.txnCurrencyExponent, this.txnCurCode, this.ird, this.settlementIndicator, this.cardSeqNumber, this.appCryptogram, this.cryptInfoData, this.issAppData, this.upblNumber, this.appTxnCounter, this.trlVerResult, this.txnDate, this.chipTxnDate, this.chipTxnType, this.cryptAmount, this.appICProfile, this.trlConCode, this.cashBackAmount, this.cvmResult, this.trlCapabilities, this.ifdSerNumber, this.tcc, this.chipCurCode, this.chipTrlType, this.trlAppVerNumber, this.txnSeqCounter, this.issAuthData, this.txnlifeCycleId, this.msgNumber, this.memberText, this.orgInstIdCode, this.revIndiCator, this.maid, this.cardType, this.cardDomIntlFlag, this.dmsSmsMode, this.posPgType, this.centreProcDate, this.encryptedCardNumber, this.meCountryOfOrigin, this.apiResponse, this.tipAmount, this.mposAccDevType);
        }
        
        @Override
        public String toString() {
            return "McRTSTxnDataEntity.McRTSTxnDataEntityBuilder(serNumber=" + this.serNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", intCode=" + this.intCode + ", prjSerNumber=" + this.prjSerNumber + ", generalStatus=" + this.generalStatus + ", txnRefNumber=" + this.txnRefNumber + ", txnType=" + this.txnType + ", fileID=" + this.fileID + ", messageTypeId=" + this.messageTypeId + ", procCode=" + this.procCode + ", functionCode=" + this.functionCode + ", txnAmount=" + this.txnAmount + ", surchargeAmount=" + this.surchargeAmount + ", fileProcDate=" + String.valueOf(this.fileProcDate) + ", cardNumber=" + this.cardNumber + ", localDateTime=" + String.valueOf(this.localDateTime) + ", expiryDate=" + this.expiryDate + ", posDataCode=" + this.posDataCode + ", msgReasonCode=" + this.msgReasonCode + ", mcc=" + this.mcc + ", acqRefData=" + this.acqRefData + ", acqinstIdCode=" + this.acqinstIdCode + ", rrn=" + this.rrn + ", approvalCode=" + this.approvalCode + ", responseCode=" + this.responseCode + ", serviceCode=" + this.serviceCode + ", terminalId=" + this.terminalId + ", merchantId=" + this.merchantId + ", meName=" + this.meName + ", meCity=" + this.meCity + ", mePinCode=" + this.mePinCode + ", meCountry=" + this.meCountry + ", trlType=" + this.trlType + ", motoEcomIndicator=" + this.motoEcomIndicator + ", txnFeeAmount=" + this.txnFeeAmount + ", txnCurrencyExponent=" + this.txnCurrencyExponent + ", txnCurCode=" + this.txnCurCode + ", ird=" + this.ird + ", settlementIndicator=" + this.settlementIndicator + ", cardSeqNumber=" + this.cardSeqNumber + ", appCryptogram=" + this.appCryptogram + ", cryptInfoData=" + this.cryptInfoData + ", issAppData=" + this.issAppData + ", upblNumber=" + this.upblNumber + ", appTxnCounter=" + this.appTxnCounter + ", trlVerResult=" + this.trlVerResult + ", txnDate=" + String.valueOf(this.txnDate) + ", chipTxnDate=" + this.chipTxnDate + ", chipTxnType=" + this.chipTxnType + ", cryptAmount=" + this.cryptAmount + ", appICProfile=" + this.appICProfile + ", trlConCode=" + this.trlConCode + ", cashBackAmount=" + this.cashBackAmount + ", cvmResult=" + this.cvmResult + ", trlCapabilities=" + this.trlCapabilities + ", ifdSerNumber=" + this.ifdSerNumber + ", tcc=" + this.tcc + ", chipCurCode=" + this.chipCurCode + ", chipTrlType=" + this.chipTrlType + ", trlAppVerNumber=" + this.trlAppVerNumber + ", txnSeqCounter=" + this.txnSeqCounter + ", issAuthData=" + this.issAuthData + ", txnlifeCycleId=" + this.txnlifeCycleId + ", msgNumber=" + this.msgNumber + ", memberText=" + this.memberText + ", orgInstIdCode=" + this.orgInstIdCode + ", revIndiCator=" + this.revIndiCator + ", maid=" + this.maid + ", cardType=" + this.cardType + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", dmsSmsMode=" + this.dmsSmsMode + ", posPgType=" + this.posPgType + ", centreProcDate=" + String.valueOf(this.centreProcDate) + ", encryptedCardNumber=" + this.encryptedCardNumber + ", meCountryOfOrigin=" + this.meCountryOfOrigin + ", apiResponse=" + this.apiResponse + ", tipAmount=" + this.tipAmount + ", mposAccDevType=" + this.mposAccDevType;
        }
    }
}
