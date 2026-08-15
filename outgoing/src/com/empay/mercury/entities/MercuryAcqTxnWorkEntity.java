/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity$MercuryAcqTxnWorkEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.mercury.entities;

import com.empay.mercury.entities.MercuryAcqTxnWorkEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MERCURY_ACQ_TXN_WORK")
public class MercuryAcqTxnWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MAT_SER_NUMBER")
    private Integer serNumber;
    @Column(name="MAT_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MAT_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MAT_INS_CODE")
    private int institutionCode;
    @Column(name="MAT_INT_CODE")
    private Integer intCode;
    @Column(name="MAT_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name="MAT_GEN_STATUS")
    private int generalStatus;
    @Column(name="MAT_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name="MAT_RET_REF_NUMBER")
    private String rrn;
    @Column(name="MAT_MERCHANT_ID")
    private String merchantId;
    @Column(name="MAT_TERMINAL_ID")
    private String terminalId;
    @Column(name="MAT_TXN_TYPE")
    private String txnType;
    @Column(name="MAT_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MAT_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MAT_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name="MAT_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="MAT_TXN_DATE")
    private LocalDate txnDate;
    @Column(name="MAT_CHARGE_TYPE")
    private String chargeType;
    @Column(name="MAT_TYPE_OF_CHARGE")
    private String typeOfCharge;
    @Column(name="MAT_GEO_AREA")
    private String geoArea;
    @Column(name="MAT_ME_NAME")
    private String meName;
    @Column(name="MAT_ME_CITY")
    private String meCity;
    @Column(name="MAT_ME_COUNTRY")
    private String meCountry;
    @Column(name="MAT_CARD_ACC_STREET_ADDRESS")
    private String cardAccepStreetAddress;
    @Column(name="MAT_CARD_ACC_STATE_CODE")
    private String cardAccepStateCode;
    @Column(name="MAT_ME_ZIP_CODE")
    private String mePinCode;
    @Column(name="MAT_EST_PHONE_NO")
    private String estPhoneNumber;
    @Column(name="MAT_MCC")
    private String mcc;
    @Column(name="MAT_CARD_TYPE")
    private Character cardType;
    @Column(name="MAT_APPR_CODE")
    private String approvalCode;
    @Column(name="MAT_TXN_CURR_EXP")
    private int txnCurrencyExponent;
    @Column(name="MAT_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="MAT_MERCURY_REF_ID")
    private String mercuryRefId;
    @Column(name="MAT_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="MAT_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name="MAT_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name="MAT_ORG_INST_ID_CODE")
    private String orgInstIdCode;
    @Column(name="MAT_TRL_TYPE")
    private String trlType;
    @Column(name="MAT_SETL_INDICATOR")
    private Character settlementIndicator;
    @Column(name="MAT_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name="MAT_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name="MAT_RESP_CODE")
    private String responseCode;
    @Column(name="MAT_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name="MAT_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name="MAT_CARD_INPUT_MODE")
    private Character cardInputMode;
    @Column(name="MAT_CARD_INPUT_CAPABILITY")
    private Character cardInputCapability;
    @Column(name="MAT_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name="MAT_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name="MAT_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name="MAT_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name="MAT_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name="MAT_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name="MAT_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name="MAT_CVM_RESULTS")
    private String cvmResult;
    @Column(name="MAT_DEDICATED_FILE_NAME")
    private String dedicatedFileName;
    @Column(name="MAT_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name="MAT_ISS_APP_DATA")
    private String issAppData;
    @Column(name="MAT_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name="MAT_TRL_CON_CODE")
    private String trlConCode;
    @Column(name="MAT_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name="MAT_CHIP_TRL_CAPABILITIES")
    private String chipTrlCapabilities;
    @Column(name="MAT_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name="MAT_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name="MAT_CHIP_TXN_DATE")
    private String chipTxnDate;
    @Column(name="MAT_CHIP_TXN_TYPE")
    private String chipTxnType;
    @Column(name="MAT_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name="MAT_UPBL_NUMBER")
    private String upblNumber;
    @Column(name="MAT_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name="MAT_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name="MAT_FILE_ID")
    private String fileID;
    @Column(name="MAT_CARD_PRESENT")
    private Character cardPresent;
    @Column(name="MAT_CH_PRESENT")
    private Character chPresent;
    @Column(name="MAT_APP_PAN_SEQ_NUMBER")
    private String panSequenceNumber;
    @Column(name="MAT_POS_ENTRY_MODE")
    private String posEntryMode;

    public static MercuryAcqTxnWorkEntityBuilder builder() {
        return new MercuryAcqTxnWorkEntityBuilder();
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

    public String getRrn() {
        return this.rrn;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getSurchargeAmount() {
        return this.surchargeAmount;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public LocalDate getTxnDate() {
        return this.txnDate;
    }

    public String getChargeType() {
        return this.chargeType;
    }

    public String getTypeOfCharge() {
        return this.typeOfCharge;
    }

    public String getGeoArea() {
        return this.geoArea;
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

    public String getCardAccepStreetAddress() {
        return this.cardAccepStreetAddress;
    }

    public String getCardAccepStateCode() {
        return this.cardAccepStateCode;
    }

    public String getMePinCode() {
        return this.mePinCode;
    }

    public String getEstPhoneNumber() {
        return this.estPhoneNumber;
    }

    public String getMcc() {
        return this.mcc;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public int getTxnCurrencyExponent() {
        return this.txnCurrencyExponent;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
    }

    public String getMercuryRefId() {
        return this.mercuryRefId;
    }

    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }

    public Character getDmsSmsMode() {
        return this.dmsSmsMode;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }

    public String getOrgInstIdCode() {
        return this.orgInstIdCode;
    }

    public String getTrlType() {
        return this.trlType;
    }

    public Character getSettlementIndicator() {
        return this.settlementIndicator;
    }

    public Double getTxnFeeAmount() {
        return this.txnFeeAmount;
    }

    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getAcqinstIdCode() {
        return this.acqinstIdCode;
    }

    public String getAcqRefData() {
        return this.acqRefData;
    }

    public Character getCardInputMode() {
        return this.cardInputMode;
    }

    public Character getCardInputCapability() {
        return this.cardInputCapability;
    }

    public String getCardSeqNumber() {
        return this.cardSeqNumber;
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

    public Double getCryptAmount() {
        return this.cryptAmount;
    }

    public Double getCashBackAmount() {
        return this.cashBackAmount;
    }

    public String getCryptInfoData() {
        return this.cryptInfoData;
    }

    public String getCvmResult() {
        return this.cvmResult;
    }

    public String getDedicatedFileName() {
        return this.dedicatedFileName;
    }

    public String getIfdSerNumber() {
        return this.ifdSerNumber;
    }

    public String getIssAppData() {
        return this.issAppData;
    }

    public String getIssAuthData() {
        return this.issAuthData;
    }

    public String getTrlConCode() {
        return this.trlConCode;
    }

    public String getTrlAppVerNumber() {
        return this.trlAppVerNumber;
    }

    public String getChipTrlCapabilities() {
        return this.chipTrlCapabilities;
    }

    public String getChipTrlType() {
        return this.chipTrlType;
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

    public String getChipCurCode() {
        return this.chipCurCode;
    }

    public String getUpblNumber() {
        return this.upblNumber;
    }

    public LocalDate getCentreProcDate() {
        return this.centreProcDate;
    }

    public LocalDate getFileProcDate() {
        return this.fileProcDate;
    }

    public String getFileID() {
        return this.fileID;
    }

    public Character getCardPresent() {
        return this.cardPresent;
    }

    public Character getChPresent() {
        return this.chPresent;
    }

    public String getPanSequenceNumber() {
        return this.panSequenceNumber;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
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

    public void setInstitutionCode(int institutionCode) {
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

    public void setTxnRefNumber(Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setSurchargeAmount(Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public void setTypeOfCharge(String typeOfCharge) {
        this.typeOfCharge = typeOfCharge;
    }

    public void setGeoArea(String geoArea) {
        this.geoArea = geoArea;
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

    public void setCardAccepStreetAddress(String cardAccepStreetAddress) {
        this.cardAccepStreetAddress = cardAccepStreetAddress;
    }

    public void setCardAccepStateCode(String cardAccepStateCode) {
        this.cardAccepStateCode = cardAccepStateCode;
    }

    public void setMePinCode(String mePinCode) {
        this.mePinCode = mePinCode;
    }

    public void setEstPhoneNumber(String estPhoneNumber) {
        this.estPhoneNumber = estPhoneNumber;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setTxnCurrencyExponent(int txnCurrencyExponent) {
        this.txnCurrencyExponent = txnCurrencyExponent;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setMercuryRefId(String mercuryRefId) {
        this.mercuryRefId = mercuryRefId;
    }

    public void setCardDomIntlFlag(Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setDmsSmsMode(Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setOrgInstIdCode(String orgInstIdCode) {
        this.orgInstIdCode = orgInstIdCode;
    }

    public void setTrlType(String trlType) {
        this.trlType = trlType;
    }

    public void setSettlementIndicator(Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }

    public void setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }

    public void setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setAcqinstIdCode(String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }

    public void setAcqRefData(String acqRefData) {
        this.acqRefData = acqRefData;
    }

    public void setCardInputMode(Character cardInputMode) {
        this.cardInputMode = cardInputMode;
    }

    public void setCardInputCapability(Character cardInputCapability) {
        this.cardInputCapability = cardInputCapability;
    }

    public void setCardSeqNumber(String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }

    public void setAppICProfile(String appICProfile) {
        this.appICProfile = appICProfile;
    }

    public void setAppTxnCounter(String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }

    public void setAppCryptogram(String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }

    public void setCryptAmount(Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }

    public void setCashBackAmount(Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }

    public void setCryptInfoData(String cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }

    public void setCvmResult(String cvmResult) {
        this.cvmResult = cvmResult;
    }

    public void setDedicatedFileName(String dedicatedFileName) {
        this.dedicatedFileName = dedicatedFileName;
    }

    public void setIfdSerNumber(String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }

    public void setIssAppData(String issAppData) {
        this.issAppData = issAppData;
    }

    public void setIssAuthData(String issAuthData) {
        this.issAuthData = issAuthData;
    }

    public void setTrlConCode(String trlConCode) {
        this.trlConCode = trlConCode;
    }

    public void setTrlAppVerNumber(String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }

    public void setChipTrlCapabilities(String chipTrlCapabilities) {
        this.chipTrlCapabilities = chipTrlCapabilities;
    }

    public void setChipTrlType(String chipTrlType) {
        this.chipTrlType = chipTrlType;
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

    public void setChipCurCode(String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }

    public void setUpblNumber(String upblNumber) {
        this.upblNumber = upblNumber;
    }

    public void setCentreProcDate(LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }

    public void setFileProcDate(LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public void setCardPresent(Character cardPresent) {
        this.cardPresent = cardPresent;
    }

    public void setChPresent(Character chPresent) {
        this.chPresent = chPresent;
    }

    public void setPanSequenceNumber(String panSequenceNumber) {
        this.panSequenceNumber = panSequenceNumber;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MercuryAcqTxnWorkEntity)) {
            return false;
        }
        MercuryAcqTxnWorkEntity other = (MercuryAcqTxnWorkEntity)o;
        if (!other.canEqual((Object)this)) {
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
        Integer this$txnRefNumber = this.getTxnRefNumber();
        Integer other$txnRefNumber = other.getTxnRefNumber();
        if (this$txnRefNumber == null ? other$txnRefNumber != null : !((Object)this$txnRefNumber).equals(other$txnRefNumber)) {
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
        Character this$settlementIndicator = this.getSettlementIndicator();
        Character other$settlementIndicator = other.getSettlementIndicator();
        if (this$settlementIndicator == null ? other$settlementIndicator != null : !((Object)this$settlementIndicator).equals(other$settlementIndicator)) {
            return false;
        }
        Double this$txnFeeAmount = this.getTxnFeeAmount();
        Double other$txnFeeAmount = other.getTxnFeeAmount();
        if (this$txnFeeAmount == null ? other$txnFeeAmount != null : !((Object)this$txnFeeAmount).equals(other$txnFeeAmount)) {
            return false;
        }
        Character this$cardInputMode = this.getCardInputMode();
        Character other$cardInputMode = other.getCardInputMode();
        if (this$cardInputMode == null ? other$cardInputMode != null : !((Object)this$cardInputMode).equals(other$cardInputMode)) {
            return false;
        }
        Character this$cardInputCapability = this.getCardInputCapability();
        Character other$cardInputCapability = other.getCardInputCapability();
        if (this$cardInputCapability == null ? other$cardInputCapability != null : !((Object)this$cardInputCapability).equals(other$cardInputCapability)) {
            return false;
        }
        Double this$cryptAmount = this.getCryptAmount();
        Double other$cryptAmount = other.getCryptAmount();
        if (this$cryptAmount == null ? other$cryptAmount != null : !((Object)this$cryptAmount).equals(other$cryptAmount)) {
            return false;
        }
        Double this$cashBackAmount = this.getCashBackAmount();
        Double other$cashBackAmount = other.getCashBackAmount();
        if (this$cashBackAmount == null ? other$cashBackAmount != null : !((Object)this$cashBackAmount).equals(other$cashBackAmount)) {
            return false;
        }
        Character this$cardPresent = this.getCardPresent();
        Character other$cardPresent = other.getCardPresent();
        if (this$cardPresent == null ? other$cardPresent != null : !((Object)this$cardPresent).equals(other$cardPresent)) {
            return false;
        }
        Character this$chPresent = this.getChPresent();
        Character other$chPresent = other.getChPresent();
        if (this$chPresent == null ? other$chPresent != null : !((Object)this$chPresent).equals(other$chPresent)) {
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
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
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
        LocalDate this$txnDate = this.getTxnDate();
        LocalDate other$txnDate = other.getTxnDate();
        if (this$txnDate == null ? other$txnDate != null : !((Object)this$txnDate).equals(other$txnDate)) {
            return false;
        }
        String this$chargeType = this.getChargeType();
        String other$chargeType = other.getChargeType();
        if (this$chargeType == null ? other$chargeType != null : !this$chargeType.equals(other$chargeType)) {
            return false;
        }
        String this$typeOfCharge = this.getTypeOfCharge();
        String other$typeOfCharge = other.getTypeOfCharge();
        if (this$typeOfCharge == null ? other$typeOfCharge != null : !this$typeOfCharge.equals(other$typeOfCharge)) {
            return false;
        }
        String this$geoArea = this.getGeoArea();
        String other$geoArea = other.getGeoArea();
        if (this$geoArea == null ? other$geoArea != null : !this$geoArea.equals(other$geoArea)) {
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
        String this$mePinCode = this.getMePinCode();
        String other$mePinCode = other.getMePinCode();
        if (this$mePinCode == null ? other$mePinCode != null : !this$mePinCode.equals(other$mePinCode)) {
            return false;
        }
        String this$estPhoneNumber = this.getEstPhoneNumber();
        String other$estPhoneNumber = other.getEstPhoneNumber();
        if (this$estPhoneNumber == null ? other$estPhoneNumber != null : !this$estPhoneNumber.equals(other$estPhoneNumber)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
            return false;
        }
        String this$mercuryRefId = this.getMercuryRefId();
        String other$mercuryRefId = other.getMercuryRefId();
        if (this$mercuryRefId == null ? other$mercuryRefId != null : !this$mercuryRefId.equals(other$mercuryRefId)) {
            return false;
        }
        String this$encryptedCardNumber = this.getEncryptedCardNumber();
        String other$encryptedCardNumber = other.getEncryptedCardNumber();
        if (this$encryptedCardNumber == null ? other$encryptedCardNumber != null : !this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
            return false;
        }
        String this$orgInstIdCode = this.getOrgInstIdCode();
        String other$orgInstIdCode = other.getOrgInstIdCode();
        if (this$orgInstIdCode == null ? other$orgInstIdCode != null : !this$orgInstIdCode.equals(other$orgInstIdCode)) {
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
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$acqinstIdCode = this.getAcqinstIdCode();
        String other$acqinstIdCode = other.getAcqinstIdCode();
        if (this$acqinstIdCode == null ? other$acqinstIdCode != null : !this$acqinstIdCode.equals(other$acqinstIdCode)) {
            return false;
        }
        String this$acqRefData = this.getAcqRefData();
        String other$acqRefData = other.getAcqRefData();
        if (this$acqRefData == null ? other$acqRefData != null : !this$acqRefData.equals(other$acqRefData)) {
            return false;
        }
        String this$cardSeqNumber = this.getCardSeqNumber();
        String other$cardSeqNumber = other.getCardSeqNumber();
        if (this$cardSeqNumber == null ? other$cardSeqNumber != null : !this$cardSeqNumber.equals(other$cardSeqNumber)) {
            return false;
        }
        String this$appICProfile = this.getAppICProfile();
        String other$appICProfile = other.getAppICProfile();
        if (this$appICProfile == null ? other$appICProfile != null : !this$appICProfile.equals(other$appICProfile)) {
            return false;
        }
        String this$appTxnCounter = this.getAppTxnCounter();
        String other$appTxnCounter = other.getAppTxnCounter();
        if (this$appTxnCounter == null ? other$appTxnCounter != null : !this$appTxnCounter.equals(other$appTxnCounter)) {
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
        String this$cvmResult = this.getCvmResult();
        String other$cvmResult = other.getCvmResult();
        if (this$cvmResult == null ? other$cvmResult != null : !this$cvmResult.equals(other$cvmResult)) {
            return false;
        }
        String this$dedicatedFileName = this.getDedicatedFileName();
        String other$dedicatedFileName = other.getDedicatedFileName();
        if (this$dedicatedFileName == null ? other$dedicatedFileName != null : !this$dedicatedFileName.equals(other$dedicatedFileName)) {
            return false;
        }
        String this$ifdSerNumber = this.getIfdSerNumber();
        String other$ifdSerNumber = other.getIfdSerNumber();
        if (this$ifdSerNumber == null ? other$ifdSerNumber != null : !this$ifdSerNumber.equals(other$ifdSerNumber)) {
            return false;
        }
        String this$issAppData = this.getIssAppData();
        String other$issAppData = other.getIssAppData();
        if (this$issAppData == null ? other$issAppData != null : !this$issAppData.equals(other$issAppData)) {
            return false;
        }
        String this$issAuthData = this.getIssAuthData();
        String other$issAuthData = other.getIssAuthData();
        if (this$issAuthData == null ? other$issAuthData != null : !this$issAuthData.equals(other$issAuthData)) {
            return false;
        }
        String this$trlConCode = this.getTrlConCode();
        String other$trlConCode = other.getTrlConCode();
        if (this$trlConCode == null ? other$trlConCode != null : !this$trlConCode.equals(other$trlConCode)) {
            return false;
        }
        String this$trlAppVerNumber = this.getTrlAppVerNumber();
        String other$trlAppVerNumber = other.getTrlAppVerNumber();
        if (this$trlAppVerNumber == null ? other$trlAppVerNumber != null : !this$trlAppVerNumber.equals(other$trlAppVerNumber)) {
            return false;
        }
        String this$chipTrlCapabilities = this.getChipTrlCapabilities();
        String other$chipTrlCapabilities = other.getChipTrlCapabilities();
        if (this$chipTrlCapabilities == null ? other$chipTrlCapabilities != null : !this$chipTrlCapabilities.equals(other$chipTrlCapabilities)) {
            return false;
        }
        String this$chipTrlType = this.getChipTrlType();
        String other$chipTrlType = other.getChipTrlType();
        if (this$chipTrlType == null ? other$chipTrlType != null : !this$chipTrlType.equals(other$chipTrlType)) {
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
        String this$upblNumber = this.getUpblNumber();
        String other$upblNumber = other.getUpblNumber();
        if (this$upblNumber == null ? other$upblNumber != null : !this$upblNumber.equals(other$upblNumber)) {
            return false;
        }
        LocalDate this$centreProcDate = this.getCentreProcDate();
        LocalDate other$centreProcDate = other.getCentreProcDate();
        if (this$centreProcDate == null ? other$centreProcDate != null : !((Object)this$centreProcDate).equals(other$centreProcDate)) {
            return false;
        }
        LocalDate this$fileProcDate = this.getFileProcDate();
        LocalDate other$fileProcDate = other.getFileProcDate();
        if (this$fileProcDate == null ? other$fileProcDate != null : !((Object)this$fileProcDate).equals(other$fileProcDate)) {
            return false;
        }
        String this$fileID = this.getFileID();
        String other$fileID = other.getFileID();
        if (this$fileID == null ? other$fileID != null : !this$fileID.equals(other$fileID)) {
            return false;
        }
        String this$panSequenceNumber = this.getPanSequenceNumber();
        String other$panSequenceNumber = other.getPanSequenceNumber();
        if (this$panSequenceNumber == null ? other$panSequenceNumber != null : !this$panSequenceNumber.equals(other$panSequenceNumber)) {
            return false;
        }
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        return !(this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MercuryAcqTxnWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInstitutionCode();
        result = result * 59 + this.getGeneralStatus();
        result = result * 59 + this.getTxnCurrencyExponent();
        Integer $serNumber = this.getSerNumber();
        result = result * 59 + ($serNumber == null ? 43 : ((Object)$serNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + ($prjSerNumber == null ? 43 : ((Object)$prjSerNumber).hashCode());
        Integer $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + ($txnRefNumber == null ? 43 : ((Object)$txnRefNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + ($surchargeAmount == null ? 43 : ((Object)$surchargeAmount).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Character $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + ($dmsSmsMode == null ? 43 : ((Object)$dmsSmsMode).hashCode());
        Character $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + ($settlementIndicator == null ? 43 : ((Object)$settlementIndicator).hashCode());
        Double $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + ($txnFeeAmount == null ? 43 : ((Object)$txnFeeAmount).hashCode());
        Character $cardInputMode = this.getCardInputMode();
        result = result * 59 + ($cardInputMode == null ? 43 : ((Object)$cardInputMode).hashCode());
        Character $cardInputCapability = this.getCardInputCapability();
        result = result * 59 + ($cardInputCapability == null ? 43 : ((Object)$cardInputCapability).hashCode());
        Double $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : ((Object)$cryptAmount).hashCode());
        Double $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + ($cashBackAmount == null ? 43 : ((Object)$cashBackAmount).hashCode());
        Character $cardPresent = this.getCardPresent();
        result = result * 59 + ($cardPresent == null ? 43 : ((Object)$cardPresent).hashCode());
        Character $chPresent = this.getChPresent();
        result = result * 59 + ($chPresent == null ? 43 : ((Object)$chPresent).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        LocalDate $txnDate = this.getTxnDate();
        result = result * 59 + ($txnDate == null ? 43 : ((Object)$txnDate).hashCode());
        String $chargeType = this.getChargeType();
        result = result * 59 + ($chargeType == null ? 43 : $chargeType.hashCode());
        String $typeOfCharge = this.getTypeOfCharge();
        result = result * 59 + ($typeOfCharge == null ? 43 : $typeOfCharge.hashCode());
        String $geoArea = this.getGeoArea();
        result = result * 59 + ($geoArea == null ? 43 : $geoArea.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $cardAccepStreetAddress = this.getCardAccepStreetAddress();
        result = result * 59 + ($cardAccepStreetAddress == null ? 43 : $cardAccepStreetAddress.hashCode());
        String $cardAccepStateCode = this.getCardAccepStateCode();
        result = result * 59 + ($cardAccepStateCode == null ? 43 : $cardAccepStateCode.hashCode());
        String $mePinCode = this.getMePinCode();
        result = result * 59 + ($mePinCode == null ? 43 : $mePinCode.hashCode());
        String $estPhoneNumber = this.getEstPhoneNumber();
        result = result * 59 + ($estPhoneNumber == null ? 43 : $estPhoneNumber.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        String $mercuryRefId = this.getMercuryRefId();
        result = result * 59 + ($mercuryRefId == null ? 43 : $mercuryRefId.hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $orgInstIdCode = this.getOrgInstIdCode();
        result = result * 59 + ($orgInstIdCode == null ? 43 : $orgInstIdCode.hashCode());
        String $trlType = this.getTrlType();
        result = result * 59 + ($trlType == null ? 43 : $trlType.hashCode());
        String $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : $motoEcomIndicator.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + ($acqinstIdCode == null ? 43 : $acqinstIdCode.hashCode());
        String $acqRefData = this.getAcqRefData();
        result = result * 59 + ($acqRefData == null ? 43 : $acqRefData.hashCode());
        String $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + ($cardSeqNumber == null ? 43 : $cardSeqNumber.hashCode());
        String $appICProfile = this.getAppICProfile();
        result = result * 59 + ($appICProfile == null ? 43 : $appICProfile.hashCode());
        String $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + ($appTxnCounter == null ? 43 : $appTxnCounter.hashCode());
        String $appCryptogram = this.getAppCryptogram();
        result = result * 59 + ($appCryptogram == null ? 43 : $appCryptogram.hashCode());
        String $cryptInfoData = this.getCryptInfoData();
        result = result * 59 + ($cryptInfoData == null ? 43 : $cryptInfoData.hashCode());
        String $cvmResult = this.getCvmResult();
        result = result * 59 + ($cvmResult == null ? 43 : $cvmResult.hashCode());
        String $dedicatedFileName = this.getDedicatedFileName();
        result = result * 59 + ($dedicatedFileName == null ? 43 : $dedicatedFileName.hashCode());
        String $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + ($ifdSerNumber == null ? 43 : $ifdSerNumber.hashCode());
        String $issAppData = this.getIssAppData();
        result = result * 59 + ($issAppData == null ? 43 : $issAppData.hashCode());
        String $issAuthData = this.getIssAuthData();
        result = result * 59 + ($issAuthData == null ? 43 : $issAuthData.hashCode());
        String $trlConCode = this.getTrlConCode();
        result = result * 59 + ($trlConCode == null ? 43 : $trlConCode.hashCode());
        String $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + ($trlAppVerNumber == null ? 43 : $trlAppVerNumber.hashCode());
        String $chipTrlCapabilities = this.getChipTrlCapabilities();
        result = result * 59 + ($chipTrlCapabilities == null ? 43 : $chipTrlCapabilities.hashCode());
        String $chipTrlType = this.getChipTrlType();
        result = result * 59 + ($chipTrlType == null ? 43 : $chipTrlType.hashCode());
        String $trlVerResult = this.getTrlVerResult();
        result = result * 59 + ($trlVerResult == null ? 43 : $trlVerResult.hashCode());
        String $chipTxnDate = this.getChipTxnDate();
        result = result * 59 + ($chipTxnDate == null ? 43 : $chipTxnDate.hashCode());
        String $chipTxnType = this.getChipTxnType();
        result = result * 59 + ($chipTxnType == null ? 43 : $chipTxnType.hashCode());
        String $chipCurCode = this.getChipCurCode();
        result = result * 59 + ($chipCurCode == null ? 43 : $chipCurCode.hashCode());
        String $upblNumber = this.getUpblNumber();
        result = result * 59 + ($upblNumber == null ? 43 : $upblNumber.hashCode());
        LocalDate $centreProcDate = this.getCentreProcDate();
        result = result * 59 + ($centreProcDate == null ? 43 : ((Object)$centreProcDate).hashCode());
        LocalDate $fileProcDate = this.getFileProcDate();
        result = result * 59 + ($fileProcDate == null ? 43 : ((Object)$fileProcDate).hashCode());
        String $fileID = this.getFileID();
        result = result * 59 + ($fileID == null ? 43 : $fileID.hashCode());
        String $panSequenceNumber = this.getPanSequenceNumber();
        result = result * 59 + ($panSequenceNumber == null ? 43 : $panSequenceNumber.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        return result;
    }

    public String toString() {
        return "MercuryAcqTxnWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", generalStatus=" + this.getGeneralStatus() + ", txnRefNumber=" + this.getTxnRefNumber() + ", rrn=" + this.getRrn() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", txnType=" + this.getTxnType() + ", cardNumber=" + this.getCardNumber() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", chargeType=" + this.getChargeType() + ", typeOfCharge=" + this.getTypeOfCharge() + ", geoArea=" + this.getGeoArea() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", cardAccepStreetAddress=" + this.getCardAccepStreetAddress() + ", cardAccepStateCode=" + this.getCardAccepStateCode() + ", mePinCode=" + this.getMePinCode() + ", estPhoneNumber=" + this.getEstPhoneNumber() + ", mcc=" + this.getMcc() + ", cardType=" + this.getCardType() + ", approvalCode=" + this.getApprovalCode() + ", txnCurrencyExponent=" + this.getTxnCurrencyExponent() + ", txnCurCode=" + this.getTxnCurCode() + ", mercuryRefId=" + this.getMercuryRefId() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", orgInstIdCode=" + this.getOrgInstIdCode() + ", trlType=" + this.getTrlType() + ", settlementIndicator=" + this.getSettlementIndicator() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", responseCode=" + this.getResponseCode() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", acqRefData=" + this.getAcqRefData() + ", cardInputMode=" + this.getCardInputMode() + ", cardInputCapability=" + this.getCardInputCapability() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appICProfile=" + this.getAppICProfile() + ", appTxnCounter=" + this.getAppTxnCounter() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptAmount=" + this.getCryptAmount() + ", cashBackAmount=" + this.getCashBackAmount() + ", cryptInfoData=" + this.getCryptInfoData() + ", cvmResult=" + this.getCvmResult() + ", dedicatedFileName=" + this.getDedicatedFileName() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", issAppData=" + this.getIssAppData() + ", issAuthData=" + this.getIssAuthData() + ", trlConCode=" + this.getTrlConCode() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", chipTrlCapabilities=" + this.getChipTrlCapabilities() + ", chipTrlType=" + this.getChipTrlType() + ", trlVerResult=" + this.getTrlVerResult() + ", chipTxnDate=" + this.getChipTxnDate() + ", chipTxnType=" + this.getChipTxnType() + ", chipCurCode=" + this.getChipCurCode() + ", upblNumber=" + this.getUpblNumber() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", fileID=" + this.getFileID() + ", cardPresent=" + this.getCardPresent() + ", chPresent=" + this.getChPresent() + ", panSequenceNumber=" + this.getPanSequenceNumber() + ", posEntryMode=" + this.getPosEntryMode() + ")";
    }

    public MercuryAcqTxnWorkEntity() {
    }

    public MercuryAcqTxnWorkEntity(Integer serNumber, LocalDateTime lastUpdated, Integer updatedUser, int institutionCode, Integer intCode, Integer prjSerNumber, int generalStatus, Integer txnRefNumber, String rrn, String merchantId, String terminalId, String txnType, String cardNumber, Double txnAmount, Double surchargeAmount, LocalDateTime localDateTime, LocalDate txnDate, String chargeType, String typeOfCharge, String geoArea, String meName, String meCity, String meCountry, String cardAccepStreetAddress, String cardAccepStateCode, String mePinCode, String estPhoneNumber, String mcc, Character cardType, String approvalCode, int txnCurrencyExponent, String txnCurCode, String mercuryRefId, Character cardDomIntlFlag, Character dmsSmsMode, String encryptedCardNumber, String orgInstIdCode, String trlType, Character settlementIndicator, Double txnFeeAmount, String motoEcomIndicator, String responseCode, String acqinstIdCode, String acqRefData, Character cardInputMode, Character cardInputCapability, String cardSeqNumber, String appICProfile, String appTxnCounter, String appCryptogram, Double cryptAmount, Double cashBackAmount, String cryptInfoData, String cvmResult, String dedicatedFileName, String ifdSerNumber, String issAppData, String issAuthData, String trlConCode, String trlAppVerNumber, String chipTrlCapabilities, String chipTrlType, String trlVerResult, String chipTxnDate, String chipTxnType, String chipCurCode, String upblNumber, LocalDate centreProcDate, LocalDate fileProcDate, String fileID, Character cardPresent, Character chPresent, String panSequenceNumber, String posEntryMode) {
        this.serNumber = serNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.intCode = intCode;
        this.prjSerNumber = prjSerNumber;
        this.generalStatus = generalStatus;
        this.txnRefNumber = txnRefNumber;
        this.rrn = rrn;
        this.merchantId = merchantId;
        this.terminalId = terminalId;
        this.txnType = txnType;
        this.cardNumber = cardNumber;
        this.txnAmount = txnAmount;
        this.surchargeAmount = surchargeAmount;
        this.localDateTime = localDateTime;
        this.txnDate = txnDate;
        this.chargeType = chargeType;
        this.typeOfCharge = typeOfCharge;
        this.geoArea = geoArea;
        this.meName = meName;
        this.meCity = meCity;
        this.meCountry = meCountry;
        this.cardAccepStreetAddress = cardAccepStreetAddress;
        this.cardAccepStateCode = cardAccepStateCode;
        this.mePinCode = mePinCode;
        this.estPhoneNumber = estPhoneNumber;
        this.mcc = mcc;
        this.cardType = cardType;
        this.approvalCode = approvalCode;
        this.txnCurrencyExponent = txnCurrencyExponent;
        this.txnCurCode = txnCurCode;
        this.mercuryRefId = mercuryRefId;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.dmsSmsMode = dmsSmsMode;
        this.encryptedCardNumber = encryptedCardNumber;
        this.orgInstIdCode = orgInstIdCode;
        this.trlType = trlType;
        this.settlementIndicator = settlementIndicator;
        this.txnFeeAmount = txnFeeAmount;
        this.motoEcomIndicator = motoEcomIndicator;
        this.responseCode = responseCode;
        this.acqinstIdCode = acqinstIdCode;
        this.acqRefData = acqRefData;
        this.cardInputMode = cardInputMode;
        this.cardInputCapability = cardInputCapability;
        this.cardSeqNumber = cardSeqNumber;
        this.appICProfile = appICProfile;
        this.appTxnCounter = appTxnCounter;
        this.appCryptogram = appCryptogram;
        this.cryptAmount = cryptAmount;
        this.cashBackAmount = cashBackAmount;
        this.cryptInfoData = cryptInfoData;
        this.cvmResult = cvmResult;
        this.dedicatedFileName = dedicatedFileName;
        this.ifdSerNumber = ifdSerNumber;
        this.issAppData = issAppData;
        this.issAuthData = issAuthData;
        this.trlConCode = trlConCode;
        this.trlAppVerNumber = trlAppVerNumber;
        this.chipTrlCapabilities = chipTrlCapabilities;
        this.chipTrlType = chipTrlType;
        this.trlVerResult = trlVerResult;
        this.chipTxnDate = chipTxnDate;
        this.chipTxnType = chipTxnType;
        this.chipCurCode = chipCurCode;
        this.upblNumber = upblNumber;
        this.centreProcDate = centreProcDate;
        this.fileProcDate = fileProcDate;
        this.fileID = fileID;
        this.cardPresent = cardPresent;
        this.chPresent = chPresent;
        this.panSequenceNumber = panSequenceNumber;
        this.posEntryMode = posEntryMode;
    }
}

