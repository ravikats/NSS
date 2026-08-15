/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McAcqTxnDataEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MC_ACQ_TXN_DATA")
public class McAcqTxnDataEntity {
    @Id
    @Column(name="MCT_SER_NUMBER")
    private Integer serNumber;
    @Column(name="MCT_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MCT_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MCT_INS_CODE")
    private Integer institutionCode;
    @Column(name="MCT_INT_CODE")
    private Integer intCode;
    @Column(name="MCT_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name="MCT_GEN_STATUS")
    private int generalStatus;
    @Column(name="MCT_TXN_REF_NUMBER")
    private Integer txnRefSerNumber;
    @Column(name="MCT_TXN_TYPE")
    private String txnType;
    @Column(name="MCT_FILE_ID")
    private String fileID;
    @Column(name="MCT_MSG_TYPE_ID")
    private String messageTypeId;
    @Column(name="MCT_PROC_CODE")
    private String procCode;
    @Column(name="MCT_FUNC_CODE")
    private String functionCode;
    @Column(name="MCT_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MCT_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name="MCT_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name="MCT_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MCT_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="MCT_EXPIRY_DATE")
    private String expiryDate;
    @Column(name="MCT_POS_DATA_CODE")
    private String posDataCode;
    @Column(name="MCT_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name="MCT_MCC")
    private String mcc;
    @Column(name="MCT_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name="MCT_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name="MCT_RET_REF_NUMBER")
    private String rrn;
    @Column(name="MCT_APPR_CODE")
    private String approvalCode;
    @Column(name="MCT_RESP_CODE")
    private String responseCode;
    @Column(name="MCT_SERVICE_CODE")
    private String serviceCode;
    @Column(name="MCT_TERMINAL_ID")
    private String terminalId;
    @Column(name="MCT_MERCHANT_ID")
    private String merchantId;
    @Column(name="MCT_ME_NAME")
    private String meName;
    @Column(name="MCT_ME_CITY")
    private String meCity;
    @Column(name="MCT_ME_ZIP_CODE")
    private String mePinCode;
    @Column(name="MCT_ME_COUNTRY")
    private String meCountry;
    @Column(name="MCT_TRL_TYPE")
    private String trlType;
    @Column(name="MCT_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name="MCT_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name="MCT_TXN_CURR_EXP")
    private int txnCurrExp;
    @Column(name="MCT_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="MCT_IRD")
    private String ird;
    @Column(name="MCT_SETL_INDICATOR")
    private Character settlementIndicator;
    @Column(name="MCT_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name="MCT_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name="MCT_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name="MCT_ISS_APP_DATA")
    private String issAppData;
    @Column(name="MCT_UPBL_NUMBER")
    private String upblNumber;
    @Column(name="MCT_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name="MCT_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name="MCT_TXN_DATE")
    private LocalDate txnDate;
    @Column(name="MCT_CHIP_TXN_DATE")
    private String chipTxnDate;
    @Column(name="MCT_CHIP_TXN_TYPE")
    private String chipTxnType;
    @Column(name="MCT_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name="MCT_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name="MCT_TRL_CON_CODE")
    private String trlConCode;
    @Column(name="MCT_CASHBACK_AMOUNT")
    private Double chipCashBack;
    @Column(name="MCT_CVM_RESULTS")
    private String cvmResult;
    @Column(name="MCT_TRL_CAPABILITIES")
    private String trlCapabilities;
    @Column(name="MCT_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name="MCT_TCC")
    private String tcc;
    @Column(name="MCT_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name="MCT_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name="MCT_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name="MCT_TXN_SEQ_COUNTER")
    private String txnSeqCounter;
    @Column(name="MCT_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name="MCT_TXN_LIFE_CYCL_ID")
    private String txnlifeCycleId;
    @Column(name="MCT_MSG_NUMBER")
    private String msgNumber;
    @Column(name="MCT_MEMBER_TEXT")
    private String memberText;
    @Column(name="MCT_ORG_INST_ID_CODE")
    private String orgInstIdCode;
    @Column(name="MCT_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name="MCT_MER_MC_ASSIGNED_ID")
    private String maid;
    @Column(name="MCT_CARD_TYPE")
    private Character cardType;
    @Column(name="MCT_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="MCT_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name="MCT_POS_PG_TYPE")
    private String posPgType;
    @Column(name="MCT_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name="MCT_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name="MCT_MRP_SER_NUMBER")
    private Integer mrpSerNumber;
    @Column(name="MCT_ME_COUNTRY_OF_ORIGIN")
    private String meCountryOfOrigin;
    @Column(name="MCT_TIP_AMOUNT")
    private Double tipAmount;
    @Column(name="MCT_CHIP_TRL_CAPABILITIES")
    private String chipTrlCapabilities;
    @Column(name="MCT_DEDICATED_FILE_NAME")
    private String dedicatedFileName;
    @Column(name="MCT_CARD_ACC_STREET_ADDRESS")
    private String cardAccepStreetAddress;
    @Column(name="MCT_CUSTOMER_SERVICE_PHONE_NO")
    private String customerServicePhNum;
    @Column(name="MCT_DCC_INDICATOR")
    private Character dccIndicator;
    @Column(name="MCT_DCC_CURRENCY")
    private String dccCurrency;
    @Column(name="MCT_DCC_AMOUNT")
    private Double dccAmount;
    @Column(name="MCT_DCC_CURR_EXP")
    private Integer dccTxnCurrencyExponent;
    @Column(name="MCT_MPOS_ACC_DEV_TYPE")
    private Character mposAccDevType;
    @Column(name="MCT_ACC_URL_ADDRESS")
    private String accepterUrlAddress;

    public Integer getSerNumber() {
        return this.serNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInstitutionCode() {
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

    public Integer getTxnRefSerNumber() {
        return this.txnRefSerNumber;
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

    public int getTxnCurrExp() {
        return this.txnCurrExp;
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

    public Double getChipCashBack() {
        return this.chipCashBack;
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

    public Integer getMrpSerNumber() {
        return this.mrpSerNumber;
    }

    public String getMeCountryOfOrigin() {
        return this.meCountryOfOrigin;
    }

    public Double getTipAmount() {
        return this.tipAmount;
    }

    public String getChipTrlCapabilities() {
        return this.chipTrlCapabilities;
    }

    public String getDedicatedFileName() {
        return this.dedicatedFileName;
    }

    public String getCardAccepStreetAddress() {
        return this.cardAccepStreetAddress;
    }

    public String getCustomerServicePhNum() {
        return this.customerServicePhNum;
    }

    public Character getDccIndicator() {
        return this.dccIndicator;
    }

    public String getDccCurrency() {
        return this.dccCurrency;
    }

    public Double getDccAmount() {
        return this.dccAmount;
    }

    public Integer getDccTxnCurrencyExponent() {
        return this.dccTxnCurrencyExponent;
    }

    public Character getMposAccDevType() {
        return this.mposAccDevType;
    }

    public String getAccepterUrlAddress() {
        return this.accepterUrlAddress;
    }

    public void setSerNumber(Integer serNumber) {
        this.serNumber = serNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setIntCode(Integer intCode) {
        this.intCode = intCode;
    }

    public void setPrjSerNumber(Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }

    public void setGeneralStatus(int generalStatus) {
        this.generalStatus = generalStatus;
    }

    public void setTxnRefSerNumber(Integer txnRefSerNumber) {
        this.txnRefSerNumber = txnRefSerNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setSurchargeAmount(Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public void setFileProcDate(LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    public void setMsgReasonCode(String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setAcqRefData(String acqRefData) {
        this.acqRefData = acqRefData;
    }

    public void setAcqinstIdCode(String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public void setMeCity(String meCity) {
        this.meCity = meCity;
    }

    public void setMePinCode(String mePinCode) {
        this.mePinCode = mePinCode;
    }

    public void setMeCountry(String meCountry) {
        this.meCountry = meCountry;
    }

    public void setTrlType(String trlType) {
        this.trlType = trlType;
    }

    public void setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }

    public void setTxnCurrExp(int txnCurrExp) {
        this.txnCurrExp = txnCurrExp;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setIrd(String ird) {
        this.ird = ird;
    }

    public void setSettlementIndicator(Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }

    public void setCardSeqNumber(String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
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

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
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

    public void setIfdSerNumber(String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }

    public void setChipCurCode(String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }

    public void setChipTrlType(String chipTrlType) {
        this.chipTrlType = chipTrlType;
    }

    public void setTrlAppVerNumber(String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }

    public void setTxnSeqCounter(String txnSeqCounter) {
        this.txnSeqCounter = txnSeqCounter;
    }

    public void setIssAuthData(String issAuthData) {
        this.issAuthData = issAuthData;
    }

    public void setTxnlifeCycleId(String txnlifeCycleId) {
        this.txnlifeCycleId = txnlifeCycleId;
    }

    public void setMsgNumber(String msgNumber) {
        this.msgNumber = msgNumber;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setOrgInstIdCode(String orgInstIdCode) {
        this.orgInstIdCode = orgInstIdCode;
    }

    public void setRevIndiCator(Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }

    public void setMaid(String maid) {
        this.maid = maid;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setCardDomIntlFlag(Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setDmsSmsMode(Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }

    public void setPosPgType(String posPgType) {
        this.posPgType = posPgType;
    }

    public void setCentreProcDate(LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setMrpSerNumber(Integer mrpSerNumber) {
        this.mrpSerNumber = mrpSerNumber;
    }

    public void setMeCountryOfOrigin(String meCountryOfOrigin) {
        this.meCountryOfOrigin = meCountryOfOrigin;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public void setChipTrlCapabilities(String chipTrlCapabilities) {
        this.chipTrlCapabilities = chipTrlCapabilities;
    }

    public void setDedicatedFileName(String dedicatedFileName) {
        this.dedicatedFileName = dedicatedFileName;
    }

    public void setCardAccepStreetAddress(String cardAccepStreetAddress) {
        this.cardAccepStreetAddress = cardAccepStreetAddress;
    }

    public void setCustomerServicePhNum(String customerServicePhNum) {
        this.customerServicePhNum = customerServicePhNum;
    }

    public void setDccIndicator(Character dccIndicator) {
        this.dccIndicator = dccIndicator;
    }

    public void setDccCurrency(String dccCurrency) {
        this.dccCurrency = dccCurrency;
    }

    public void setDccAmount(Double dccAmount) {
        this.dccAmount = dccAmount;
    }

    public void setDccTxnCurrencyExponent(Integer dccTxnCurrencyExponent) {
        this.dccTxnCurrencyExponent = dccTxnCurrencyExponent;
    }

    public void setMposAccDevType(Character mposAccDevType) {
        this.mposAccDevType = mposAccDevType;
    }

    public void setAccepterUrlAddress(String accepterUrlAddress) {
        this.accepterUrlAddress = accepterUrlAddress;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McAcqTxnDataEntity)) {
            return false;
        }
        McAcqTxnDataEntity other = (McAcqTxnDataEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getGeneralStatus() != other.getGeneralStatus()) {
            return false;
        }
        if (this.getTxnCurrExp() != other.getTxnCurrExp()) {
            return false;
        }
        Integer this$serNumber = this.getSerNumber();
        Integer other$serNumber = other.getSerNumber();
        if (this$serNumber == null ? other$serNumber != null : !((Object)this$serNumber).equals(other$serNumber)) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Integer this$institutionCode = this.getInstitutionCode();
        Integer other$institutionCode = other.getInstitutionCode();
        if (this$institutionCode == null ? other$institutionCode != null : !((Object)this$institutionCode).equals(other$institutionCode)) {
            return false;
        }
        Integer this$intCode = this.getIntCode();
        Integer other$intCode = other.getIntCode();
        if (this$intCode == null ? other$intCode != null : !((Object)this$intCode).equals(other$intCode)) {
            return false;
        }
        Integer this$prjSerNumber = this.getPrjSerNumber();
        Integer other$prjSerNumber = other.getPrjSerNumber();
        if (this$prjSerNumber == null ? other$prjSerNumber != null : !((Object)this$prjSerNumber).equals(other$prjSerNumber)) {
            return false;
        }
        Integer this$txnRefSerNumber = this.getTxnRefSerNumber();
        Integer other$txnRefSerNumber = other.getTxnRefSerNumber();
        if (this$txnRefSerNumber == null ? other$txnRefSerNumber != null : !((Object)this$txnRefSerNumber).equals(other$txnRefSerNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$surchargeAmount = this.getSurchargeAmount();
        Double other$surchargeAmount = other.getSurchargeAmount();
        if (this$surchargeAmount == null ? other$surchargeAmount != null : !((Object)this$surchargeAmount).equals(other$surchargeAmount)) {
            return false;
        }
        Double this$txnFeeAmount = this.getTxnFeeAmount();
        Double other$txnFeeAmount = other.getTxnFeeAmount();
        if (this$txnFeeAmount == null ? other$txnFeeAmount != null : !((Object)this$txnFeeAmount).equals(other$txnFeeAmount)) {
            return false;
        }
        Character this$settlementIndicator = this.getSettlementIndicator();
        Character other$settlementIndicator = other.getSettlementIndicator();
        if (this$settlementIndicator == null ? other$settlementIndicator != null : !((Object)this$settlementIndicator).equals(other$settlementIndicator)) {
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
        Character this$revIndiCator = this.getRevIndiCator();
        Character other$revIndiCator = other.getRevIndiCator();
        if (this$revIndiCator == null ? other$revIndiCator != null : !((Object)this$revIndiCator).equals(other$revIndiCator)) {
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
        Character this$dmsSmsMode = this.getDmsSmsMode();
        Character other$dmsSmsMode = other.getDmsSmsMode();
        if (this$dmsSmsMode == null ? other$dmsSmsMode != null : !((Object)this$dmsSmsMode).equals(other$dmsSmsMode)) {
            return false;
        }
        Integer this$mrpSerNumber = this.getMrpSerNumber();
        Integer other$mrpSerNumber = other.getMrpSerNumber();
        if (this$mrpSerNumber == null ? other$mrpSerNumber != null : !((Object)this$mrpSerNumber).equals(other$mrpSerNumber)) {
            return false;
        }
        Double this$tipAmount = this.getTipAmount();
        Double other$tipAmount = other.getTipAmount();
        if (this$tipAmount == null ? other$tipAmount != null : !((Object)this$tipAmount).equals(other$tipAmount)) {
            return false;
        }
        Character this$dccIndicator = this.getDccIndicator();
        Character other$dccIndicator = other.getDccIndicator();
        if (this$dccIndicator == null ? other$dccIndicator != null : !((Object)this$dccIndicator).equals(other$dccIndicator)) {
            return false;
        }
        Double this$dccAmount = this.getDccAmount();
        Double other$dccAmount = other.getDccAmount();
        if (this$dccAmount == null ? other$dccAmount != null : !((Object)this$dccAmount).equals(other$dccAmount)) {
            return false;
        }
        Integer this$dccTxnCurrencyExponent = this.getDccTxnCurrencyExponent();
        Integer other$dccTxnCurrencyExponent = other.getDccTxnCurrencyExponent();
        if (this$dccTxnCurrencyExponent == null ? other$dccTxnCurrencyExponent != null : !((Object)this$dccTxnCurrencyExponent).equals(other$dccTxnCurrencyExponent)) {
            return false;
        }
        Character this$mposAccDevType = this.getMposAccDevType();
        Character other$mposAccDevType = other.getMposAccDevType();
        if (this$mposAccDevType == null ? other$mposAccDevType != null : !((Object)this$mposAccDevType).equals(other$mposAccDevType)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$fileID = this.getFileID();
        String other$fileID = other.getFileID();
        if (this$fileID == null ? other$fileID != null : !this$fileID.equals(other$fileID)) {
            return false;
        }
        String this$messageTypeId = this.getMessageTypeId();
        String other$messageTypeId = other.getMessageTypeId();
        if (this$messageTypeId == null ? other$messageTypeId != null : !this$messageTypeId.equals(other$messageTypeId)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        LocalDate this$fileProcDate = this.getFileProcDate();
        LocalDate other$fileProcDate = other.getFileProcDate();
        if (this$fileProcDate == null ? other$fileProcDate != null : !((Object)this$fileProcDate).equals(other$fileProcDate)) {
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
        String this$expiryDate = this.getExpiryDate();
        String other$expiryDate = other.getExpiryDate();
        if (this$expiryDate == null ? other$expiryDate != null : !this$expiryDate.equals(other$expiryDate)) {
            return false;
        }
        String this$posDataCode = this.getPosDataCode();
        String other$posDataCode = other.getPosDataCode();
        if (this$posDataCode == null ? other$posDataCode != null : !this$posDataCode.equals(other$posDataCode)) {
            return false;
        }
        String this$msgReasonCode = this.getMsgReasonCode();
        String other$msgReasonCode = other.getMsgReasonCode();
        if (this$msgReasonCode == null ? other$msgReasonCode != null : !this$msgReasonCode.equals(other$msgReasonCode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$acqRefData = this.getAcqRefData();
        String other$acqRefData = other.getAcqRefData();
        if (this$acqRefData == null ? other$acqRefData != null : !this$acqRefData.equals(other$acqRefData)) {
            return false;
        }
        String this$acqinstIdCode = this.getAcqinstIdCode();
        String other$acqinstIdCode = other.getAcqinstIdCode();
        if (this$acqinstIdCode == null ? other$acqinstIdCode != null : !this$acqinstIdCode.equals(other$acqinstIdCode)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$serviceCode = this.getServiceCode();
        String other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null ? other$serviceCode != null : !this$serviceCode.equals(other$serviceCode)) {
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
        String this$mePinCode = this.getMePinCode();
        String other$mePinCode = other.getMePinCode();
        if (this$mePinCode == null ? other$mePinCode != null : !this$mePinCode.equals(other$mePinCode)) {
            return false;
        }
        String this$meCountry = this.getMeCountry();
        String other$meCountry = other.getMeCountry();
        if (this$meCountry == null ? other$meCountry != null : !this$meCountry.equals(other$meCountry)) {
            return false;
        }
        String this$trlType = this.getTrlType();
        String other$trlType = other.getTrlType();
        if (this$trlType == null ? other$trlType != null : !this$trlType.equals(other$trlType)) {
            return false;
        }
        String this$motoEcomIndicator = this.getMotoEcomIndicator();
        String other$motoEcomIndicator = other.getMotoEcomIndicator();
        if (this$motoEcomIndicator == null ? other$motoEcomIndicator != null : !this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
            return false;
        }
        String this$ird = this.getIrd();
        String other$ird = other.getIrd();
        if (this$ird == null ? other$ird != null : !this$ird.equals(other$ird)) {
            return false;
        }
        String this$cardSeqNumber = this.getCardSeqNumber();
        String other$cardSeqNumber = other.getCardSeqNumber();
        if (this$cardSeqNumber == null ? other$cardSeqNumber != null : !this$cardSeqNumber.equals(other$cardSeqNumber)) {
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
        LocalDate this$txnDate = this.getTxnDate();
        LocalDate other$txnDate = other.getTxnDate();
        if (this$txnDate == null ? other$txnDate != null : !((Object)this$txnDate).equals(other$txnDate)) {
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
        String this$chipCurCode = this.getChipCurCode();
        String other$chipCurCode = other.getChipCurCode();
        if (this$chipCurCode == null ? other$chipCurCode != null : !this$chipCurCode.equals(other$chipCurCode)) {
            return false;
        }
        String this$chipTrlType = this.getChipTrlType();
        String other$chipTrlType = other.getChipTrlType();
        if (this$chipTrlType == null ? other$chipTrlType != null : !this$chipTrlType.equals(other$chipTrlType)) {
            return false;
        }
        String this$trlAppVerNumber = this.getTrlAppVerNumber();
        String other$trlAppVerNumber = other.getTrlAppVerNumber();
        if (this$trlAppVerNumber == null ? other$trlAppVerNumber != null : !this$trlAppVerNumber.equals(other$trlAppVerNumber)) {
            return false;
        }
        String this$txnSeqCounter = this.getTxnSeqCounter();
        String other$txnSeqCounter = other.getTxnSeqCounter();
        if (this$txnSeqCounter == null ? other$txnSeqCounter != null : !this$txnSeqCounter.equals(other$txnSeqCounter)) {
            return false;
        }
        String this$issAuthData = this.getIssAuthData();
        String other$issAuthData = other.getIssAuthData();
        if (this$issAuthData == null ? other$issAuthData != null : !this$issAuthData.equals(other$issAuthData)) {
            return false;
        }
        String this$txnlifeCycleId = this.getTxnlifeCycleId();
        String other$txnlifeCycleId = other.getTxnlifeCycleId();
        if (this$txnlifeCycleId == null ? other$txnlifeCycleId != null : !this$txnlifeCycleId.equals(other$txnlifeCycleId)) {
            return false;
        }
        String this$msgNumber = this.getMsgNumber();
        String other$msgNumber = other.getMsgNumber();
        if (this$msgNumber == null ? other$msgNumber != null : !this$msgNumber.equals(other$msgNumber)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
            return false;
        }
        String this$orgInstIdCode = this.getOrgInstIdCode();
        String other$orgInstIdCode = other.getOrgInstIdCode();
        if (this$orgInstIdCode == null ? other$orgInstIdCode != null : !this$orgInstIdCode.equals(other$orgInstIdCode)) {
            return false;
        }
        String this$maid = this.getMaid();
        String other$maid = other.getMaid();
        if (this$maid == null ? other$maid != null : !this$maid.equals(other$maid)) {
            return false;
        }
        String this$posPgType = this.getPosPgType();
        String other$posPgType = other.getPosPgType();
        if (this$posPgType == null ? other$posPgType != null : !this$posPgType.equals(other$posPgType)) {
            return false;
        }
        LocalDate this$centreProcDate = this.getCentreProcDate();
        LocalDate other$centreProcDate = other.getCentreProcDate();
        if (this$centreProcDate == null ? other$centreProcDate != null : !((Object)this$centreProcDate).equals(other$centreProcDate)) {
            return false;
        }
        String this$encryptedCardNumber = this.getEncryptedCardNumber();
        String other$encryptedCardNumber = other.getEncryptedCardNumber();
        if (this$encryptedCardNumber == null ? other$encryptedCardNumber != null : !this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
            return false;
        }
        String this$meCountryOfOrigin = this.getMeCountryOfOrigin();
        String other$meCountryOfOrigin = other.getMeCountryOfOrigin();
        if (this$meCountryOfOrigin == null ? other$meCountryOfOrigin != null : !this$meCountryOfOrigin.equals(other$meCountryOfOrigin)) {
            return false;
        }
        String this$chipTrlCapabilities = this.getChipTrlCapabilities();
        String other$chipTrlCapabilities = other.getChipTrlCapabilities();
        if (this$chipTrlCapabilities == null ? other$chipTrlCapabilities != null : !this$chipTrlCapabilities.equals(other$chipTrlCapabilities)) {
            return false;
        }
        String this$dedicatedFileName = this.getDedicatedFileName();
        String other$dedicatedFileName = other.getDedicatedFileName();
        if (this$dedicatedFileName == null ? other$dedicatedFileName != null : !this$dedicatedFileName.equals(other$dedicatedFileName)) {
            return false;
        }
        String this$cardAccepStreetAddress = this.getCardAccepStreetAddress();
        String other$cardAccepStreetAddress = other.getCardAccepStreetAddress();
        if (this$cardAccepStreetAddress == null ? other$cardAccepStreetAddress != null : !this$cardAccepStreetAddress.equals(other$cardAccepStreetAddress)) {
            return false;
        }
        String this$customerServicePhNum = this.getCustomerServicePhNum();
        String other$customerServicePhNum = other.getCustomerServicePhNum();
        if (this$customerServicePhNum == null ? other$customerServicePhNum != null : !this$customerServicePhNum.equals(other$customerServicePhNum)) {
            return false;
        }
        String this$dccCurrency = this.getDccCurrency();
        String other$dccCurrency = other.getDccCurrency();
        if (this$dccCurrency == null ? other$dccCurrency != null : !this$dccCurrency.equals(other$dccCurrency)) {
            return false;
        }
        String this$accepterUrlAddress = this.getAccepterUrlAddress();
        String other$accepterUrlAddress = other.getAccepterUrlAddress();
        return !(this$accepterUrlAddress == null ? other$accepterUrlAddress != null : !this$accepterUrlAddress.equals(other$accepterUrlAddress));
    }

    protected boolean canEqual(Object other) {
        return other instanceof McAcqTxnDataEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getGeneralStatus();
        result = result * 59 + this.getTxnCurrExp();
        Integer $serNumber = this.getSerNumber();
        result = result * 59 + ($serNumber == null ? 43 : ((Object)$serNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + ($prjSerNumber == null ? 43 : ((Object)$prjSerNumber).hashCode());
        Integer $txnRefSerNumber = this.getTxnRefSerNumber();
        result = result * 59 + ($txnRefSerNumber == null ? 43 : ((Object)$txnRefSerNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + ($surchargeAmount == null ? 43 : ((Object)$surchargeAmount).hashCode());
        Double $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + ($txnFeeAmount == null ? 43 : ((Object)$txnFeeAmount).hashCode());
        Character $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + ($settlementIndicator == null ? 43 : ((Object)$settlementIndicator).hashCode());
        Double $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : ((Object)$cryptAmount).hashCode());
        Double $chipCashBack = this.getChipCashBack();
        result = result * 59 + ($chipCashBack == null ? 43 : ((Object)$chipCashBack).hashCode());
        Character $revIndiCator = this.getRevIndiCator();
        result = result * 59 + ($revIndiCator == null ? 43 : ((Object)$revIndiCator).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Character $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + ($dmsSmsMode == null ? 43 : ((Object)$dmsSmsMode).hashCode());
        Integer $mrpSerNumber = this.getMrpSerNumber();
        result = result * 59 + ($mrpSerNumber == null ? 43 : ((Object)$mrpSerNumber).hashCode());
        Double $tipAmount = this.getTipAmount();
        result = result * 59 + ($tipAmount == null ? 43 : ((Object)$tipAmount).hashCode());
        Character $dccIndicator = this.getDccIndicator();
        result = result * 59 + ($dccIndicator == null ? 43 : ((Object)$dccIndicator).hashCode());
        Double $dccAmount = this.getDccAmount();
        result = result * 59 + ($dccAmount == null ? 43 : ((Object)$dccAmount).hashCode());
        Integer $dccTxnCurrencyExponent = this.getDccTxnCurrencyExponent();
        result = result * 59 + ($dccTxnCurrencyExponent == null ? 43 : ((Object)$dccTxnCurrencyExponent).hashCode());
        Character $mposAccDevType = this.getMposAccDevType();
        result = result * 59 + ($mposAccDevType == null ? 43 : ((Object)$mposAccDevType).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $fileID = this.getFileID();
        result = result * 59 + ($fileID == null ? 43 : $fileID.hashCode());
        String $messageTypeId = this.getMessageTypeId();
        result = result * 59 + ($messageTypeId == null ? 43 : $messageTypeId.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        LocalDate $fileProcDate = this.getFileProcDate();
        result = result * 59 + ($fileProcDate == null ? 43 : ((Object)$fileProcDate).hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $expiryDate = this.getExpiryDate();
        result = result * 59 + ($expiryDate == null ? 43 : $expiryDate.hashCode());
        String $posDataCode = this.getPosDataCode();
        result = result * 59 + ($posDataCode == null ? 43 : $posDataCode.hashCode());
        String $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + ($msgReasonCode == null ? 43 : $msgReasonCode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $acqRefData = this.getAcqRefData();
        result = result * 59 + ($acqRefData == null ? 43 : $acqRefData.hashCode());
        String $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + ($acqinstIdCode == null ? 43 : $acqinstIdCode.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $mePinCode = this.getMePinCode();
        result = result * 59 + ($mePinCode == null ? 43 : $mePinCode.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $trlType = this.getTrlType();
        result = result * 59 + ($trlType == null ? 43 : $trlType.hashCode());
        String $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : $motoEcomIndicator.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        String $ird = this.getIrd();
        result = result * 59 + ($ird == null ? 43 : $ird.hashCode());
        String $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + ($cardSeqNumber == null ? 43 : $cardSeqNumber.hashCode());
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
        LocalDate $txnDate = this.getTxnDate();
        result = result * 59 + ($txnDate == null ? 43 : ((Object)$txnDate).hashCode());
        String $chipTxnDate = this.getChipTxnDate();
        result = result * 59 + ($chipTxnDate == null ? 43 : $chipTxnDate.hashCode());
        String $chipTxnType = this.getChipTxnType();
        result = result * 59 + ($chipTxnType == null ? 43 : $chipTxnType.hashCode());
        String $appICProfile = this.getAppICProfile();
        result = result * 59 + ($appICProfile == null ? 43 : $appICProfile.hashCode());
        String $trlConCode = this.getTrlConCode();
        result = result * 59 + ($trlConCode == null ? 43 : $trlConCode.hashCode());
        String $cvmResult = this.getCvmResult();
        result = result * 59 + ($cvmResult == null ? 43 : $cvmResult.hashCode());
        String $trlCapabilities = this.getTrlCapabilities();
        result = result * 59 + ($trlCapabilities == null ? 43 : $trlCapabilities.hashCode());
        String $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + ($ifdSerNumber == null ? 43 : $ifdSerNumber.hashCode());
        String $tcc = this.getTcc();
        result = result * 59 + ($tcc == null ? 43 : $tcc.hashCode());
        String $chipCurCode = this.getChipCurCode();
        result = result * 59 + ($chipCurCode == null ? 43 : $chipCurCode.hashCode());
        String $chipTrlType = this.getChipTrlType();
        result = result * 59 + ($chipTrlType == null ? 43 : $chipTrlType.hashCode());
        String $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + ($trlAppVerNumber == null ? 43 : $trlAppVerNumber.hashCode());
        String $txnSeqCounter = this.getTxnSeqCounter();
        result = result * 59 + ($txnSeqCounter == null ? 43 : $txnSeqCounter.hashCode());
        String $issAuthData = this.getIssAuthData();
        result = result * 59 + ($issAuthData == null ? 43 : $issAuthData.hashCode());
        String $txnlifeCycleId = this.getTxnlifeCycleId();
        result = result * 59 + ($txnlifeCycleId == null ? 43 : $txnlifeCycleId.hashCode());
        String $msgNumber = this.getMsgNumber();
        result = result * 59 + ($msgNumber == null ? 43 : $msgNumber.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $orgInstIdCode = this.getOrgInstIdCode();
        result = result * 59 + ($orgInstIdCode == null ? 43 : $orgInstIdCode.hashCode());
        String $maid = this.getMaid();
        result = result * 59 + ($maid == null ? 43 : $maid.hashCode());
        String $posPgType = this.getPosPgType();
        result = result * 59 + ($posPgType == null ? 43 : $posPgType.hashCode());
        LocalDate $centreProcDate = this.getCentreProcDate();
        result = result * 59 + ($centreProcDate == null ? 43 : ((Object)$centreProcDate).hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $meCountryOfOrigin = this.getMeCountryOfOrigin();
        result = result * 59 + ($meCountryOfOrigin == null ? 43 : $meCountryOfOrigin.hashCode());
        String $chipTrlCapabilities = this.getChipTrlCapabilities();
        result = result * 59 + ($chipTrlCapabilities == null ? 43 : $chipTrlCapabilities.hashCode());
        String $dedicatedFileName = this.getDedicatedFileName();
        result = result * 59 + ($dedicatedFileName == null ? 43 : $dedicatedFileName.hashCode());
        String $cardAccepStreetAddress = this.getCardAccepStreetAddress();
        result = result * 59 + ($cardAccepStreetAddress == null ? 43 : $cardAccepStreetAddress.hashCode());
        String $customerServicePhNum = this.getCustomerServicePhNum();
        result = result * 59 + ($customerServicePhNum == null ? 43 : $customerServicePhNum.hashCode());
        String $dccCurrency = this.getDccCurrency();
        result = result * 59 + ($dccCurrency == null ? 43 : $dccCurrency.hashCode());
        String $accepterUrlAddress = this.getAccepterUrlAddress();
        result = result * 59 + ($accepterUrlAddress == null ? 43 : $accepterUrlAddress.hashCode());
        return result;
    }

    public String toString() {
        return "McAcqTxnDataEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", generalStatus=" + this.getGeneralStatus() + ", txnRefSerNumber=" + this.getTxnRefSerNumber() + ", txnType=" + this.getTxnType() + ", fileID=" + this.getFileID() + ", messageTypeId=" + this.getMessageTypeId() + ", procCode=" + this.getProcCode() + ", functionCode=" + this.getFunctionCode() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", cardNumber=" + this.getCardNumber() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", expiryDate=" + this.getExpiryDate() + ", posDataCode=" + this.getPosDataCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", mcc=" + this.getMcc() + ", acqRefData=" + this.getAcqRefData() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", rrn=" + this.getRrn() + ", approvalCode=" + this.getApprovalCode() + ", responseCode=" + this.getResponseCode() + ", serviceCode=" + this.getServiceCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", mePinCode=" + this.getMePinCode() + ", meCountry=" + this.getMeCountry() + ", trlType=" + this.getTrlType() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", txnCurrExp=" + this.getTxnCurrExp() + ", txnCurCode=" + this.getTxnCurCode() + ", ird=" + this.getIrd() + ", settlementIndicator=" + this.getSettlementIndicator() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", issAppData=" + this.getIssAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", trlVerResult=" + this.getTrlVerResult() + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", chipTxnDate=" + this.getChipTxnDate() + ", chipTxnType=" + this.getChipTxnType() + ", cryptAmount=" + this.getCryptAmount() + ", appICProfile=" + this.getAppICProfile() + ", trlConCode=" + this.getTrlConCode() + ", chipCashBack=" + this.getChipCashBack() + ", cvmResult=" + this.getCvmResult() + ", trlCapabilities=" + this.getTrlCapabilities() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", tcc=" + this.getTcc() + ", chipCurCode=" + this.getChipCurCode() + ", chipTrlType=" + this.getChipTrlType() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", txnSeqCounter=" + this.getTxnSeqCounter() + ", issAuthData=" + this.getIssAuthData() + ", txnlifeCycleId=" + this.getTxnlifeCycleId() + ", msgNumber=" + this.getMsgNumber() + ", memberText=" + this.getMemberText() + ", orgInstIdCode=" + this.getOrgInstIdCode() + ", revIndiCator=" + this.getRevIndiCator() + ", maid=" + this.getMaid() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", posPgType=" + this.getPosPgType() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", mrpSerNumber=" + this.getMrpSerNumber() + ", meCountryOfOrigin=" + this.getMeCountryOfOrigin() + ", tipAmount=" + this.getTipAmount() + ", chipTrlCapabilities=" + this.getChipTrlCapabilities() + ", dedicatedFileName=" + this.getDedicatedFileName() + ", cardAccepStreetAddress=" + this.getCardAccepStreetAddress() + ", customerServicePhNum=" + this.getCustomerServicePhNum() + ", dccIndicator=" + this.getDccIndicator() + ", dccCurrency=" + this.getDccCurrency() + ", dccAmount=" + this.getDccAmount() + ", dccTxnCurrencyExponent=" + this.getDccTxnCurrencyExponent() + ", mposAccDevType=" + this.getMposAccDevType() + ", accepterUrlAddress=" + this.getAccepterUrlAddress() + ")";
    }
}

