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
@Table(name = "MERCURY_ACQ_TXN_WORK")
public class MercuryAcqTxnWorkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAT_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "MAT_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "MAT_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "MAT_INS_CODE")
    private int institutionCode;
    @Column(name = "MAT_INT_CODE")
    private Integer intCode;
    @Column(name = "MAT_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "MAT_GEN_STATUS")
    private int generalStatus;
    @Column(name = "MAT_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name = "MAT_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "MAT_MERCHANT_ID")
    private String merchantId;
    @Column(name = "MAT_TERMINAL_ID")
    private String terminalId;
    @Column(name = "MAT_TXN_TYPE")
    private String txnType;
    @Column(name = "MAT_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "MAT_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "MAT_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name = "MAT_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "MAT_TXN_DATE")
    private LocalDate txnDate;
    @Column(name = "MAT_CHARGE_TYPE")
    private String chargeType;
    @Column(name = "MAT_TYPE_OF_CHARGE")
    private String typeOfCharge;
    @Column(name = "MAT_GEO_AREA")
    private String geoArea;
    @Column(name = "MAT_ME_NAME")
    private String meName;
    @Column(name = "MAT_ME_CITY")
    private String meCity;
    @Column(name = "MAT_ME_COUNTRY")
    private String meCountry;
    @Column(name = "MAT_CARD_ACC_STREET_ADDRESS")
    private String cardAccepStreetAddress;
    @Column(name = "MAT_CARD_ACC_STATE_CODE")
    private String cardAccepStateCode;
    @Column(name = "MAT_ME_ZIP_CODE")
    private String mePinCode;
    @Column(name = "MAT_EST_PHONE_NO")
    private String estPhoneNumber;
    @Column(name = "MAT_MCC")
    private String mcc;
    @Column(name = "MAT_CARD_TYPE")
    private Character cardType;
    @Column(name = "MAT_APPR_CODE")
    private String approvalCode;
    @Column(name = "MAT_TXN_CURR_EXP")
    private int txnCurrencyExponent;
    @Column(name = "MAT_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "MAT_MERCURY_REF_ID")
    private String mercuryRefId;
    @Column(name = "MAT_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "MAT_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name = "MAT_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name = "MAT_ORG_INST_ID_CODE")
    private String orgInstIdCode;
    @Column(name = "MAT_TRL_TYPE")
    private String trlType;
    @Column(name = "MAT_SETL_INDICATOR")
    private Character settlementIndicator;
    @Column(name = "MAT_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name = "MAT_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name = "MAT_RESP_CODE")
    private String responseCode;
    @Column(name = "MAT_ACQ_INST_ID_CODE")
    private String acqinstIdCode;
    @Column(name = "MAT_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name = "MAT_CARD_INPUT_MODE")
    private Character cardInputMode;
    @Column(name = "MAT_CARD_INPUT_CAPABILITY")
    private Character cardInputCapability;
    @Column(name = "MAT_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name = "MAT_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name = "MAT_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name = "MAT_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name = "MAT_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name = "MAT_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "MAT_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name = "MAT_CVM_RESULTS")
    private String cvmResult;
    @Column(name = "MAT_DEDICATED_FILE_NAME")
    private String dedicatedFileName;
    @Column(name = "MAT_IFD_SER_NUMBER")
    private String ifdSerNumber;
    @Column(name = "MAT_ISS_APP_DATA")
    private String issAppData;
    @Column(name = "MAT_ISS_AUTH_DATA")
    private String issAuthData;
    @Column(name = "MAT_TRL_CON_CODE")
    private String trlConCode;
    @Column(name = "MAT_TRL_APP_VER_NUMBER")
    private String trlAppVerNumber;
    @Column(name = "MAT_CHIP_TRL_CAPABILITIES")
    private String chipTrlCapabilities;
    @Column(name = "MAT_CHIP_TRL_TYPE")
    private String chipTrlType;
    @Column(name = "MAT_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name = "MAT_CHIP_TXN_DATE")
    private String chipTxnDate;
    @Column(name = "MAT_CHIP_TXN_TYPE")
    private String chipTxnType;
    @Column(name = "MAT_CHIP_CUR_CODE")
    private String chipCurCode;
    @Column(name = "MAT_UPBL_NUMBER")
    private String upblNumber;
    @Column(name = "MAT_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name = "MAT_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name = "MAT_FILE_ID")
    private String fileID;
    @Column(name = "MAT_CARD_PRESENT")
    private Character cardPresent;
    @Column(name = "MAT_CH_PRESENT")
    private Character chPresent;
    @Column(name = "MAT_APP_PAN_SEQ_NUMBER")
    private String panSequenceNumber;
    @Column(name = "MAT_POS_ENTRY_MODE")
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
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setSurchargeAmount(final Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }
    
    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    public void setTxnDate(final LocalDate txnDate) {
        this.txnDate = txnDate;
    }
    
    public void setChargeType(final String chargeType) {
        this.chargeType = chargeType;
    }
    
    public void setTypeOfCharge(final String typeOfCharge) {
        this.typeOfCharge = typeOfCharge;
    }
    
    public void setGeoArea(final String geoArea) {
        this.geoArea = geoArea;
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
    
    public void setCardAccepStreetAddress(final String cardAccepStreetAddress) {
        this.cardAccepStreetAddress = cardAccepStreetAddress;
    }
    
    public void setCardAccepStateCode(final String cardAccepStateCode) {
        this.cardAccepStateCode = cardAccepStateCode;
    }
    
    public void setMePinCode(final String mePinCode) {
        this.mePinCode = mePinCode;
    }
    
    public void setEstPhoneNumber(final String estPhoneNumber) {
        this.estPhoneNumber = estPhoneNumber;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setTxnCurrencyExponent(final int txnCurrencyExponent) {
        this.txnCurrencyExponent = txnCurrencyExponent;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }
    
    public void setMercuryRefId(final String mercuryRefId) {
        this.mercuryRefId = mercuryRefId;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setDmsSmsMode(final Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
    }
    
    public void setEncryptedCardNumber(final String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }
    
    public void setOrgInstIdCode(final String orgInstIdCode) {
        this.orgInstIdCode = orgInstIdCode;
    }
    
    public void setTrlType(final String trlType) {
        this.trlType = trlType;
    }
    
    public void setSettlementIndicator(final Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }
    
    public void setTxnFeeAmount(final Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }
    
    public void setMotoEcomIndicator(final String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setAcqinstIdCode(final String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }
    
    public void setAcqRefData(final String acqRefData) {
        this.acqRefData = acqRefData;
    }
    
    public void setCardInputMode(final Character cardInputMode) {
        this.cardInputMode = cardInputMode;
    }
    
    public void setCardInputCapability(final Character cardInputCapability) {
        this.cardInputCapability = cardInputCapability;
    }
    
    public void setCardSeqNumber(final String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
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
    
    public void setCryptAmount(final Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }
    
    public void setCashBackAmount(final Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    public void setCryptInfoData(final String cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }
    
    public void setCvmResult(final String cvmResult) {
        this.cvmResult = cvmResult;
    }
    
    public void setDedicatedFileName(final String dedicatedFileName) {
        this.dedicatedFileName = dedicatedFileName;
    }
    
    public void setIfdSerNumber(final String ifdSerNumber) {
        this.ifdSerNumber = ifdSerNumber;
    }
    
    public void setIssAppData(final String issAppData) {
        this.issAppData = issAppData;
    }
    
    public void setIssAuthData(final String issAuthData) {
        this.issAuthData = issAuthData;
    }
    
    public void setTrlConCode(final String trlConCode) {
        this.trlConCode = trlConCode;
    }
    
    public void setTrlAppVerNumber(final String trlAppVerNumber) {
        this.trlAppVerNumber = trlAppVerNumber;
    }
    
    public void setChipTrlCapabilities(final String chipTrlCapabilities) {
        this.chipTrlCapabilities = chipTrlCapabilities;
    }
    
    public void setChipTrlType(final String chipTrlType) {
        this.chipTrlType = chipTrlType;
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
    
    public void setChipCurCode(final String chipCurCode) {
        this.chipCurCode = chipCurCode;
    }
    
    public void setUpblNumber(final String upblNumber) {
        this.upblNumber = upblNumber;
    }
    
    public void setCentreProcDate(final LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }
    
    public void setFileProcDate(final LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }
    
    public void setFileID(final String fileID) {
        this.fileID = fileID;
    }
    
    public void setCardPresent(final Character cardPresent) {
        this.cardPresent = cardPresent;
    }
    
    public void setChPresent(final Character chPresent) {
        this.chPresent = chPresent;
    }
    
    public void setPanSequenceNumber(final String panSequenceNumber) {
        this.panSequenceNumber = panSequenceNumber;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MercuryAcqTxnWorkEntity)) {
            return false;
        }
        final MercuryAcqTxnWorkEntity other = (MercuryAcqTxnWorkEntity)o;
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
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0363: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0363;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0363;
            }
            return false;
        }
        final Object this$cardDomIntlFlag = this.getCardDomIntlFlag();
        final Object other$cardDomIntlFlag = other.getCardDomIntlFlag();
        Label_0400: {
            if (this$cardDomIntlFlag == null) {
                if (other$cardDomIntlFlag == null) {
                    break Label_0400;
                }
            }
            else if (this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
                break Label_0400;
            }
            return false;
        }
        final Object this$dmsSmsMode = this.getDmsSmsMode();
        final Object other$dmsSmsMode = other.getDmsSmsMode();
        Label_0437: {
            if (this$dmsSmsMode == null) {
                if (other$dmsSmsMode == null) {
                    break Label_0437;
                }
            }
            else if (this$dmsSmsMode.equals(other$dmsSmsMode)) {
                break Label_0437;
            }
            return false;
        }
        final Object this$settlementIndicator = this.getSettlementIndicator();
        final Object other$settlementIndicator = other.getSettlementIndicator();
        Label_0474: {
            if (this$settlementIndicator == null) {
                if (other$settlementIndicator == null) {
                    break Label_0474;
                }
            }
            else if (this$settlementIndicator.equals(other$settlementIndicator)) {
                break Label_0474;
            }
            return false;
        }
        final Object this$txnFeeAmount = this.getTxnFeeAmount();
        final Object other$txnFeeAmount = other.getTxnFeeAmount();
        Label_0511: {
            if (this$txnFeeAmount == null) {
                if (other$txnFeeAmount == null) {
                    break Label_0511;
                }
            }
            else if (this$txnFeeAmount.equals(other$txnFeeAmount)) {
                break Label_0511;
            }
            return false;
        }
        final Object this$cardInputMode = this.getCardInputMode();
        final Object other$cardInputMode = other.getCardInputMode();
        Label_0548: {
            if (this$cardInputMode == null) {
                if (other$cardInputMode == null) {
                    break Label_0548;
                }
            }
            else if (this$cardInputMode.equals(other$cardInputMode)) {
                break Label_0548;
            }
            return false;
        }
        final Object this$cardInputCapability = this.getCardInputCapability();
        final Object other$cardInputCapability = other.getCardInputCapability();
        Label_0585: {
            if (this$cardInputCapability == null) {
                if (other$cardInputCapability == null) {
                    break Label_0585;
                }
            }
            else if (this$cardInputCapability.equals(other$cardInputCapability)) {
                break Label_0585;
            }
            return false;
        }
        final Object this$cryptAmount = this.getCryptAmount();
        final Object other$cryptAmount = other.getCryptAmount();
        Label_0622: {
            if (this$cryptAmount == null) {
                if (other$cryptAmount == null) {
                    break Label_0622;
                }
            }
            else if (this$cryptAmount.equals(other$cryptAmount)) {
                break Label_0622;
            }
            return false;
        }
        final Object this$cashBackAmount = this.getCashBackAmount();
        final Object other$cashBackAmount = other.getCashBackAmount();
        Label_0659: {
            if (this$cashBackAmount == null) {
                if (other$cashBackAmount == null) {
                    break Label_0659;
                }
            }
            else if (this$cashBackAmount.equals(other$cashBackAmount)) {
                break Label_0659;
            }
            return false;
        }
        final Object this$cardPresent = this.getCardPresent();
        final Object other$cardPresent = other.getCardPresent();
        Label_0696: {
            if (this$cardPresent == null) {
                if (other$cardPresent == null) {
                    break Label_0696;
                }
            }
            else if (this$cardPresent.equals(other$cardPresent)) {
                break Label_0696;
            }
            return false;
        }
        final Object this$chPresent = this.getChPresent();
        final Object other$chPresent = other.getChPresent();
        Label_0733: {
            if (this$chPresent == null) {
                if (other$chPresent == null) {
                    break Label_0733;
                }
            }
            else if (this$chPresent.equals(other$chPresent)) {
                break Label_0733;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0770: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0770;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0770;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0807: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0807;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0807;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_0844: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_0844;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_0844;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_0881: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_0881;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_0881;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_0918: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_0918;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_0918;
            }
            return false;
        }
        final Object this$cardNumber = this.getCardNumber();
        final Object other$cardNumber = other.getCardNumber();
        Label_0955: {
            if (this$cardNumber == null) {
                if (other$cardNumber == null) {
                    break Label_0955;
                }
            }
            else if (this$cardNumber.equals(other$cardNumber)) {
                break Label_0955;
            }
            return false;
        }
        final Object this$localDateTime = this.getLocalDateTime();
        final Object other$localDateTime = other.getLocalDateTime();
        Label_0992: {
            if (this$localDateTime == null) {
                if (other$localDateTime == null) {
                    break Label_0992;
                }
            }
            else if (this$localDateTime.equals(other$localDateTime)) {
                break Label_0992;
            }
            return false;
        }
        final Object this$txnDate = this.getTxnDate();
        final Object other$txnDate = other.getTxnDate();
        Label_1029: {
            if (this$txnDate == null) {
                if (other$txnDate == null) {
                    break Label_1029;
                }
            }
            else if (this$txnDate.equals(other$txnDate)) {
                break Label_1029;
            }
            return false;
        }
        final Object this$chargeType = this.getChargeType();
        final Object other$chargeType = other.getChargeType();
        Label_1066: {
            if (this$chargeType == null) {
                if (other$chargeType == null) {
                    break Label_1066;
                }
            }
            else if (this$chargeType.equals(other$chargeType)) {
                break Label_1066;
            }
            return false;
        }
        final Object this$typeOfCharge = this.getTypeOfCharge();
        final Object other$typeOfCharge = other.getTypeOfCharge();
        Label_1103: {
            if (this$typeOfCharge == null) {
                if (other$typeOfCharge == null) {
                    break Label_1103;
                }
            }
            else if (this$typeOfCharge.equals(other$typeOfCharge)) {
                break Label_1103;
            }
            return false;
        }
        final Object this$geoArea = this.getGeoArea();
        final Object other$geoArea = other.getGeoArea();
        Label_1140: {
            if (this$geoArea == null) {
                if (other$geoArea == null) {
                    break Label_1140;
                }
            }
            else if (this$geoArea.equals(other$geoArea)) {
                break Label_1140;
            }
            return false;
        }
        final Object this$meName = this.getMeName();
        final Object other$meName = other.getMeName();
        Label_1177: {
            if (this$meName == null) {
                if (other$meName == null) {
                    break Label_1177;
                }
            }
            else if (this$meName.equals(other$meName)) {
                break Label_1177;
            }
            return false;
        }
        final Object this$meCity = this.getMeCity();
        final Object other$meCity = other.getMeCity();
        Label_1214: {
            if (this$meCity == null) {
                if (other$meCity == null) {
                    break Label_1214;
                }
            }
            else if (this$meCity.equals(other$meCity)) {
                break Label_1214;
            }
            return false;
        }
        final Object this$meCountry = this.getMeCountry();
        final Object other$meCountry = other.getMeCountry();
        Label_1251: {
            if (this$meCountry == null) {
                if (other$meCountry == null) {
                    break Label_1251;
                }
            }
            else if (this$meCountry.equals(other$meCountry)) {
                break Label_1251;
            }
            return false;
        }
        final Object this$cardAccepStreetAddress = this.getCardAccepStreetAddress();
        final Object other$cardAccepStreetAddress = other.getCardAccepStreetAddress();
        Label_1288: {
            if (this$cardAccepStreetAddress == null) {
                if (other$cardAccepStreetAddress == null) {
                    break Label_1288;
                }
            }
            else if (this$cardAccepStreetAddress.equals(other$cardAccepStreetAddress)) {
                break Label_1288;
            }
            return false;
        }
        final Object this$cardAccepStateCode = this.getCardAccepStateCode();
        final Object other$cardAccepStateCode = other.getCardAccepStateCode();
        Label_1325: {
            if (this$cardAccepStateCode == null) {
                if (other$cardAccepStateCode == null) {
                    break Label_1325;
                }
            }
            else if (this$cardAccepStateCode.equals(other$cardAccepStateCode)) {
                break Label_1325;
            }
            return false;
        }
        final Object this$mePinCode = this.getMePinCode();
        final Object other$mePinCode = other.getMePinCode();
        Label_1362: {
            if (this$mePinCode == null) {
                if (other$mePinCode == null) {
                    break Label_1362;
                }
            }
            else if (this$mePinCode.equals(other$mePinCode)) {
                break Label_1362;
            }
            return false;
        }
        final Object this$estPhoneNumber = this.getEstPhoneNumber();
        final Object other$estPhoneNumber = other.getEstPhoneNumber();
        Label_1399: {
            if (this$estPhoneNumber == null) {
                if (other$estPhoneNumber == null) {
                    break Label_1399;
                }
            }
            else if (this$estPhoneNumber.equals(other$estPhoneNumber)) {
                break Label_1399;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_1436: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_1436;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_1436;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_1473: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_1473;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_1473;
            }
            return false;
        }
        final Object this$txnCurCode = this.getTxnCurCode();
        final Object other$txnCurCode = other.getTxnCurCode();
        Label_1510: {
            if (this$txnCurCode == null) {
                if (other$txnCurCode == null) {
                    break Label_1510;
                }
            }
            else if (this$txnCurCode.equals(other$txnCurCode)) {
                break Label_1510;
            }
            return false;
        }
        final Object this$mercuryRefId = this.getMercuryRefId();
        final Object other$mercuryRefId = other.getMercuryRefId();
        Label_1547: {
            if (this$mercuryRefId == null) {
                if (other$mercuryRefId == null) {
                    break Label_1547;
                }
            }
            else if (this$mercuryRefId.equals(other$mercuryRefId)) {
                break Label_1547;
            }
            return false;
        }
        final Object this$encryptedCardNumber = this.getEncryptedCardNumber();
        final Object other$encryptedCardNumber = other.getEncryptedCardNumber();
        Label_1584: {
            if (this$encryptedCardNumber == null) {
                if (other$encryptedCardNumber == null) {
                    break Label_1584;
                }
            }
            else if (this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
                break Label_1584;
            }
            return false;
        }
        final Object this$orgInstIdCode = this.getOrgInstIdCode();
        final Object other$orgInstIdCode = other.getOrgInstIdCode();
        Label_1621: {
            if (this$orgInstIdCode == null) {
                if (other$orgInstIdCode == null) {
                    break Label_1621;
                }
            }
            else if (this$orgInstIdCode.equals(other$orgInstIdCode)) {
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
        final Object this$responseCode = this.getResponseCode();
        final Object other$responseCode = other.getResponseCode();
        Label_1732: {
            if (this$responseCode == null) {
                if (other$responseCode == null) {
                    break Label_1732;
                }
            }
            else if (this$responseCode.equals(other$responseCode)) {
                break Label_1732;
            }
            return false;
        }
        final Object this$acqinstIdCode = this.getAcqinstIdCode();
        final Object other$acqinstIdCode = other.getAcqinstIdCode();
        Label_1769: {
            if (this$acqinstIdCode == null) {
                if (other$acqinstIdCode == null) {
                    break Label_1769;
                }
            }
            else if (this$acqinstIdCode.equals(other$acqinstIdCode)) {
                break Label_1769;
            }
            return false;
        }
        final Object this$acqRefData = this.getAcqRefData();
        final Object other$acqRefData = other.getAcqRefData();
        Label_1806: {
            if (this$acqRefData == null) {
                if (other$acqRefData == null) {
                    break Label_1806;
                }
            }
            else if (this$acqRefData.equals(other$acqRefData)) {
                break Label_1806;
            }
            return false;
        }
        final Object this$cardSeqNumber = this.getCardSeqNumber();
        final Object other$cardSeqNumber = other.getCardSeqNumber();
        Label_1843: {
            if (this$cardSeqNumber == null) {
                if (other$cardSeqNumber == null) {
                    break Label_1843;
                }
            }
            else if (this$cardSeqNumber.equals(other$cardSeqNumber)) {
                break Label_1843;
            }
            return false;
        }
        final Object this$appICProfile = this.getAppICProfile();
        final Object other$appICProfile = other.getAppICProfile();
        Label_1880: {
            if (this$appICProfile == null) {
                if (other$appICProfile == null) {
                    break Label_1880;
                }
            }
            else if (this$appICProfile.equals(other$appICProfile)) {
                break Label_1880;
            }
            return false;
        }
        final Object this$appTxnCounter = this.getAppTxnCounter();
        final Object other$appTxnCounter = other.getAppTxnCounter();
        Label_1917: {
            if (this$appTxnCounter == null) {
                if (other$appTxnCounter == null) {
                    break Label_1917;
                }
            }
            else if (this$appTxnCounter.equals(other$appTxnCounter)) {
                break Label_1917;
            }
            return false;
        }
        final Object this$appCryptogram = this.getAppCryptogram();
        final Object other$appCryptogram = other.getAppCryptogram();
        Label_1954: {
            if (this$appCryptogram == null) {
                if (other$appCryptogram == null) {
                    break Label_1954;
                }
            }
            else if (this$appCryptogram.equals(other$appCryptogram)) {
                break Label_1954;
            }
            return false;
        }
        final Object this$cryptInfoData = this.getCryptInfoData();
        final Object other$cryptInfoData = other.getCryptInfoData();
        Label_1991: {
            if (this$cryptInfoData == null) {
                if (other$cryptInfoData == null) {
                    break Label_1991;
                }
            }
            else if (this$cryptInfoData.equals(other$cryptInfoData)) {
                break Label_1991;
            }
            return false;
        }
        final Object this$cvmResult = this.getCvmResult();
        final Object other$cvmResult = other.getCvmResult();
        Label_2028: {
            if (this$cvmResult == null) {
                if (other$cvmResult == null) {
                    break Label_2028;
                }
            }
            else if (this$cvmResult.equals(other$cvmResult)) {
                break Label_2028;
            }
            return false;
        }
        final Object this$dedicatedFileName = this.getDedicatedFileName();
        final Object other$dedicatedFileName = other.getDedicatedFileName();
        Label_2065: {
            if (this$dedicatedFileName == null) {
                if (other$dedicatedFileName == null) {
                    break Label_2065;
                }
            }
            else if (this$dedicatedFileName.equals(other$dedicatedFileName)) {
                break Label_2065;
            }
            return false;
        }
        final Object this$ifdSerNumber = this.getIfdSerNumber();
        final Object other$ifdSerNumber = other.getIfdSerNumber();
        Label_2102: {
            if (this$ifdSerNumber == null) {
                if (other$ifdSerNumber == null) {
                    break Label_2102;
                }
            }
            else if (this$ifdSerNumber.equals(other$ifdSerNumber)) {
                break Label_2102;
            }
            return false;
        }
        final Object this$issAppData = this.getIssAppData();
        final Object other$issAppData = other.getIssAppData();
        Label_2139: {
            if (this$issAppData == null) {
                if (other$issAppData == null) {
                    break Label_2139;
                }
            }
            else if (this$issAppData.equals(other$issAppData)) {
                break Label_2139;
            }
            return false;
        }
        final Object this$issAuthData = this.getIssAuthData();
        final Object other$issAuthData = other.getIssAuthData();
        Label_2176: {
            if (this$issAuthData == null) {
                if (other$issAuthData == null) {
                    break Label_2176;
                }
            }
            else if (this$issAuthData.equals(other$issAuthData)) {
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
        final Object this$trlAppVerNumber = this.getTrlAppVerNumber();
        final Object other$trlAppVerNumber = other.getTrlAppVerNumber();
        Label_2250: {
            if (this$trlAppVerNumber == null) {
                if (other$trlAppVerNumber == null) {
                    break Label_2250;
                }
            }
            else if (this$trlAppVerNumber.equals(other$trlAppVerNumber)) {
                break Label_2250;
            }
            return false;
        }
        final Object this$chipTrlCapabilities = this.getChipTrlCapabilities();
        final Object other$chipTrlCapabilities = other.getChipTrlCapabilities();
        Label_2287: {
            if (this$chipTrlCapabilities == null) {
                if (other$chipTrlCapabilities == null) {
                    break Label_2287;
                }
            }
            else if (this$chipTrlCapabilities.equals(other$chipTrlCapabilities)) {
                break Label_2287;
            }
            return false;
        }
        final Object this$chipTrlType = this.getChipTrlType();
        final Object other$chipTrlType = other.getChipTrlType();
        Label_2324: {
            if (this$chipTrlType == null) {
                if (other$chipTrlType == null) {
                    break Label_2324;
                }
            }
            else if (this$chipTrlType.equals(other$chipTrlType)) {
                break Label_2324;
            }
            return false;
        }
        final Object this$trlVerResult = this.getTrlVerResult();
        final Object other$trlVerResult = other.getTrlVerResult();
        Label_2361: {
            if (this$trlVerResult == null) {
                if (other$trlVerResult == null) {
                    break Label_2361;
                }
            }
            else if (this$trlVerResult.equals(other$trlVerResult)) {
                break Label_2361;
            }
            return false;
        }
        final Object this$chipTxnDate = this.getChipTxnDate();
        final Object other$chipTxnDate = other.getChipTxnDate();
        Label_2398: {
            if (this$chipTxnDate == null) {
                if (other$chipTxnDate == null) {
                    break Label_2398;
                }
            }
            else if (this$chipTxnDate.equals(other$chipTxnDate)) {
                break Label_2398;
            }
            return false;
        }
        final Object this$chipTxnType = this.getChipTxnType();
        final Object other$chipTxnType = other.getChipTxnType();
        Label_2435: {
            if (this$chipTxnType == null) {
                if (other$chipTxnType == null) {
                    break Label_2435;
                }
            }
            else if (this$chipTxnType.equals(other$chipTxnType)) {
                break Label_2435;
            }
            return false;
        }
        final Object this$chipCurCode = this.getChipCurCode();
        final Object other$chipCurCode = other.getChipCurCode();
        Label_2472: {
            if (this$chipCurCode == null) {
                if (other$chipCurCode == null) {
                    break Label_2472;
                }
            }
            else if (this$chipCurCode.equals(other$chipCurCode)) {
                break Label_2472;
            }
            return false;
        }
        final Object this$upblNumber = this.getUpblNumber();
        final Object other$upblNumber = other.getUpblNumber();
        Label_2509: {
            if (this$upblNumber == null) {
                if (other$upblNumber == null) {
                    break Label_2509;
                }
            }
            else if (this$upblNumber.equals(other$upblNumber)) {
                break Label_2509;
            }
            return false;
        }
        final Object this$centreProcDate = this.getCentreProcDate();
        final Object other$centreProcDate = other.getCentreProcDate();
        Label_2546: {
            if (this$centreProcDate == null) {
                if (other$centreProcDate == null) {
                    break Label_2546;
                }
            }
            else if (this$centreProcDate.equals(other$centreProcDate)) {
                break Label_2546;
            }
            return false;
        }
        final Object this$fileProcDate = this.getFileProcDate();
        final Object other$fileProcDate = other.getFileProcDate();
        Label_2583: {
            if (this$fileProcDate == null) {
                if (other$fileProcDate == null) {
                    break Label_2583;
                }
            }
            else if (this$fileProcDate.equals(other$fileProcDate)) {
                break Label_2583;
            }
            return false;
        }
        final Object this$fileID = this.getFileID();
        final Object other$fileID = other.getFileID();
        Label_2620: {
            if (this$fileID == null) {
                if (other$fileID == null) {
                    break Label_2620;
                }
            }
            else if (this$fileID.equals(other$fileID)) {
                break Label_2620;
            }
            return false;
        }
        final Object this$panSequenceNumber = this.getPanSequenceNumber();
        final Object other$panSequenceNumber = other.getPanSequenceNumber();
        Label_2657: {
            if (this$panSequenceNumber == null) {
                if (other$panSequenceNumber == null) {
                    break Label_2657;
                }
            }
            else if (this$panSequenceNumber.equals(other$panSequenceNumber)) {
                break Label_2657;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null) {
            if (other$posEntryMode == null) {
                return true;
            }
        }
        else if (this$posEntryMode.equals(other$posEntryMode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof MercuryAcqTxnWorkEntity;
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
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + (($dmsSmsMode == null) ? 43 : $dmsSmsMode.hashCode());
        final Object $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + (($settlementIndicator == null) ? 43 : $settlementIndicator.hashCode());
        final Object $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + (($txnFeeAmount == null) ? 43 : $txnFeeAmount.hashCode());
        final Object $cardInputMode = this.getCardInputMode();
        result = result * 59 + (($cardInputMode == null) ? 43 : $cardInputMode.hashCode());
        final Object $cardInputCapability = this.getCardInputCapability();
        result = result * 59 + (($cardInputCapability == null) ? 43 : $cardInputCapability.hashCode());
        final Object $cryptAmount = this.getCryptAmount();
        result = result * 59 + (($cryptAmount == null) ? 43 : $cryptAmount.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $cardPresent = this.getCardPresent();
        result = result * 59 + (($cardPresent == null) ? 43 : $cardPresent.hashCode());
        final Object $chPresent = this.getChPresent();
        result = result * 59 + (($chPresent == null) ? 43 : $chPresent.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $txnDate = this.getTxnDate();
        result = result * 59 + (($txnDate == null) ? 43 : $txnDate.hashCode());
        final Object $chargeType = this.getChargeType();
        result = result * 59 + (($chargeType == null) ? 43 : $chargeType.hashCode());
        final Object $typeOfCharge = this.getTypeOfCharge();
        result = result * 59 + (($typeOfCharge == null) ? 43 : $typeOfCharge.hashCode());
        final Object $geoArea = this.getGeoArea();
        result = result * 59 + (($geoArea == null) ? 43 : $geoArea.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $cardAccepStreetAddress = this.getCardAccepStreetAddress();
        result = result * 59 + (($cardAccepStreetAddress == null) ? 43 : $cardAccepStreetAddress.hashCode());
        final Object $cardAccepStateCode = this.getCardAccepStateCode();
        result = result * 59 + (($cardAccepStateCode == null) ? 43 : $cardAccepStateCode.hashCode());
        final Object $mePinCode = this.getMePinCode();
        result = result * 59 + (($mePinCode == null) ? 43 : $mePinCode.hashCode());
        final Object $estPhoneNumber = this.getEstPhoneNumber();
        result = result * 59 + (($estPhoneNumber == null) ? 43 : $estPhoneNumber.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $mercuryRefId = this.getMercuryRefId();
        result = result * 59 + (($mercuryRefId == null) ? 43 : $mercuryRefId.hashCode());
        final Object $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + (($encryptedCardNumber == null) ? 43 : $encryptedCardNumber.hashCode());
        final Object $orgInstIdCode = this.getOrgInstIdCode();
        result = result * 59 + (($orgInstIdCode == null) ? 43 : $orgInstIdCode.hashCode());
        final Object $trlType = this.getTrlType();
        result = result * 59 + (($trlType == null) ? 43 : $trlType.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + (($acqinstIdCode == null) ? 43 : $acqinstIdCode.hashCode());
        final Object $acqRefData = this.getAcqRefData();
        result = result * 59 + (($acqRefData == null) ? 43 : $acqRefData.hashCode());
        final Object $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + (($cardSeqNumber == null) ? 43 : $cardSeqNumber.hashCode());
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
        final Object $dedicatedFileName = this.getDedicatedFileName();
        result = result * 59 + (($dedicatedFileName == null) ? 43 : $dedicatedFileName.hashCode());
        final Object $ifdSerNumber = this.getIfdSerNumber();
        result = result * 59 + (($ifdSerNumber == null) ? 43 : $ifdSerNumber.hashCode());
        final Object $issAppData = this.getIssAppData();
        result = result * 59 + (($issAppData == null) ? 43 : $issAppData.hashCode());
        final Object $issAuthData = this.getIssAuthData();
        result = result * 59 + (($issAuthData == null) ? 43 : $issAuthData.hashCode());
        final Object $trlConCode = this.getTrlConCode();
        result = result * 59 + (($trlConCode == null) ? 43 : $trlConCode.hashCode());
        final Object $trlAppVerNumber = this.getTrlAppVerNumber();
        result = result * 59 + (($trlAppVerNumber == null) ? 43 : $trlAppVerNumber.hashCode());
        final Object $chipTrlCapabilities = this.getChipTrlCapabilities();
        result = result * 59 + (($chipTrlCapabilities == null) ? 43 : $chipTrlCapabilities.hashCode());
        final Object $chipTrlType = this.getChipTrlType();
        result = result * 59 + (($chipTrlType == null) ? 43 : $chipTrlType.hashCode());
        final Object $trlVerResult = this.getTrlVerResult();
        result = result * 59 + (($trlVerResult == null) ? 43 : $trlVerResult.hashCode());
        final Object $chipTxnDate = this.getChipTxnDate();
        result = result * 59 + (($chipTxnDate == null) ? 43 : $chipTxnDate.hashCode());
        final Object $chipTxnType = this.getChipTxnType();
        result = result * 59 + (($chipTxnType == null) ? 43 : $chipTxnType.hashCode());
        final Object $chipCurCode = this.getChipCurCode();
        result = result * 59 + (($chipCurCode == null) ? 43 : $chipCurCode.hashCode());
        final Object $upblNumber = this.getUpblNumber();
        result = result * 59 + (($upblNumber == null) ? 43 : $upblNumber.hashCode());
        final Object $centreProcDate = this.getCentreProcDate();
        result = result * 59 + (($centreProcDate == null) ? 43 : $centreProcDate.hashCode());
        final Object $fileProcDate = this.getFileProcDate();
        result = result * 59 + (($fileProcDate == null) ? 43 : $fileProcDate.hashCode());
        final Object $fileID = this.getFileID();
        result = result * 59 + (($fileID == null) ? 43 : $fileID.hashCode());
        final Object $panSequenceNumber = this.getPanSequenceNumber();
        result = result * 59 + (($panSequenceNumber == null) ? 43 : $panSequenceNumber.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "MercuryAcqTxnWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", generalStatus=" + this.getGeneralStatus() + ", txnRefNumber=" + this.getTxnRefNumber() + ", rrn=" + this.getRrn() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", txnType=" + this.getTxnType() + ", cardNumber=" + this.getCardNumber() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", chargeType=" + this.getChargeType() + ", typeOfCharge=" + this.getTypeOfCharge() + ", geoArea=" + this.getGeoArea() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", cardAccepStreetAddress=" + this.getCardAccepStreetAddress() + ", cardAccepStateCode=" + this.getCardAccepStateCode() + ", mePinCode=" + this.getMePinCode() + ", estPhoneNumber=" + this.getEstPhoneNumber() + ", mcc=" + this.getMcc() + ", cardType=" + this.getCardType() + ", approvalCode=" + this.getApprovalCode() + ", txnCurrencyExponent=" + this.getTxnCurrencyExponent() + ", txnCurCode=" + this.getTxnCurCode() + ", mercuryRefId=" + this.getMercuryRefId() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", orgInstIdCode=" + this.getOrgInstIdCode() + ", trlType=" + this.getTrlType() + ", settlementIndicator=" + this.getSettlementIndicator() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", responseCode=" + this.getResponseCode() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", acqRefData=" + this.getAcqRefData() + ", cardInputMode=" + this.getCardInputMode() + ", cardInputCapability=" + this.getCardInputCapability() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appICProfile=" + this.getAppICProfile() + ", appTxnCounter=" + this.getAppTxnCounter() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptAmount=" + this.getCryptAmount() + ", cashBackAmount=" + this.getCashBackAmount() + ", cryptInfoData=" + this.getCryptInfoData() + ", cvmResult=" + this.getCvmResult() + ", dedicatedFileName=" + this.getDedicatedFileName() + ", ifdSerNumber=" + this.getIfdSerNumber() + ", issAppData=" + this.getIssAppData() + ", issAuthData=" + this.getIssAuthData() + ", trlConCode=" + this.getTrlConCode() + ", trlAppVerNumber=" + this.getTrlAppVerNumber() + ", chipTrlCapabilities=" + this.getChipTrlCapabilities() + ", chipTrlType=" + this.getChipTrlType() + ", trlVerResult=" + this.getTrlVerResult() + ", chipTxnDate=" + this.getChipTxnDate() + ", chipTxnType=" + this.getChipTxnType() + ", chipCurCode=" + this.getChipCurCode() + ", upblNumber=" + this.getUpblNumber() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", fileID=" + this.getFileID() + ", cardPresent=" + this.getCardPresent() + ", chPresent=" + this.getChPresent() + ", panSequenceNumber=" + this.getPanSequenceNumber() + ", posEntryMode=" + this.getPosEntryMode();
    }
    
    public MercuryAcqTxnWorkEntity() {
    }
    
    public MercuryAcqTxnWorkEntity(final Integer serNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final int institutionCode, final Integer intCode, final Integer prjSerNumber, final int generalStatus, final Integer txnRefNumber, final String rrn, final String merchantId, final String terminalId, final String txnType, final String cardNumber, final Double txnAmount, final Double surchargeAmount, final LocalDateTime localDateTime, final LocalDate txnDate, final String chargeType, final String typeOfCharge, final String geoArea, final String meName, final String meCity, final String meCountry, final String cardAccepStreetAddress, final String cardAccepStateCode, final String mePinCode, final String estPhoneNumber, final String mcc, final Character cardType, final String approvalCode, final int txnCurrencyExponent, final String txnCurCode, final String mercuryRefId, final Character cardDomIntlFlag, final Character dmsSmsMode, final String encryptedCardNumber, final String orgInstIdCode, final String trlType, final Character settlementIndicator, final Double txnFeeAmount, final String motoEcomIndicator, final String responseCode, final String acqinstIdCode, final String acqRefData, final Character cardInputMode, final Character cardInputCapability, final String cardSeqNumber, final String appICProfile, final String appTxnCounter, final String appCryptogram, final Double cryptAmount, final Double cashBackAmount, final String cryptInfoData, final String cvmResult, final String dedicatedFileName, final String ifdSerNumber, final String issAppData, final String issAuthData, final String trlConCode, final String trlAppVerNumber, final String chipTrlCapabilities, final String chipTrlType, final String trlVerResult, final String chipTxnDate, final String chipTxnType, final String chipCurCode, final String upblNumber, final LocalDate centreProcDate, final LocalDate fileProcDate, final String fileID, final Character cardPresent, final Character chPresent, final String panSequenceNumber, final String posEntryMode) {
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
    
    public static class MercuryAcqTxnWorkEntityBuilder
    {
        private Integer serNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private int institutionCode;
        private Integer intCode;
        private Integer prjSerNumber;
        private int generalStatus;
        private Integer txnRefNumber;
        private String rrn;
        private String merchantId;
        private String terminalId;
        private String txnType;
        private String cardNumber;
        private Double txnAmount;
        private Double surchargeAmount;
        private LocalDateTime localDateTime;
        private LocalDate txnDate;
        private String chargeType;
        private String typeOfCharge;
        private String geoArea;
        private String meName;
        private String meCity;
        private String meCountry;
        private String cardAccepStreetAddress;
        private String cardAccepStateCode;
        private String mePinCode;
        private String estPhoneNumber;
        private String mcc;
        private Character cardType;
        private String approvalCode;
        private int txnCurrencyExponent;
        private String txnCurCode;
        private String mercuryRefId;
        private Character cardDomIntlFlag;
        private Character dmsSmsMode;
        private String encryptedCardNumber;
        private String orgInstIdCode;
        private String trlType;
        private Character settlementIndicator;
        private Double txnFeeAmount;
        private String motoEcomIndicator;
        private String responseCode;
        private String acqinstIdCode;
        private String acqRefData;
        private Character cardInputMode;
        private Character cardInputCapability;
        private String cardSeqNumber;
        private String appICProfile;
        private String appTxnCounter;
        private String appCryptogram;
        private Double cryptAmount;
        private Double cashBackAmount;
        private String cryptInfoData;
        private String cvmResult;
        private String dedicatedFileName;
        private String ifdSerNumber;
        private String issAppData;
        private String issAuthData;
        private String trlConCode;
        private String trlAppVerNumber;
        private String chipTrlCapabilities;
        private String chipTrlType;
        private String trlVerResult;
        private String chipTxnDate;
        private String chipTxnType;
        private String chipCurCode;
        private String upblNumber;
        private LocalDate centreProcDate;
        private LocalDate fileProcDate;
        private String fileID;
        private Character cardPresent;
        private Character chPresent;
        private String panSequenceNumber;
        private String posEntryMode;
        
        MercuryAcqTxnWorkEntityBuilder() {
        }
        
        public MercuryAcqTxnWorkEntityBuilder serNumber(final Integer serNumber) {
            this.serNumber = serNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder institutionCode(final int institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder generalStatus(final int generalStatus) {
            this.generalStatus = generalStatus;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnRefNumber(final Integer txnRefNumber) {
            this.txnRefNumber = txnRefNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder surchargeAmount(final Double surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnDate(final LocalDate txnDate) {
            this.txnDate = txnDate;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chargeType(final String chargeType) {
            this.chargeType = chargeType;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder typeOfCharge(final String typeOfCharge) {
            this.typeOfCharge = typeOfCharge;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder geoArea(final String geoArea) {
            this.geoArea = geoArea;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardAccepStreetAddress(final String cardAccepStreetAddress) {
            this.cardAccepStreetAddress = cardAccepStreetAddress;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardAccepStateCode(final String cardAccepStateCode) {
            this.cardAccepStateCode = cardAccepStateCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder mePinCode(final String mePinCode) {
            this.mePinCode = mePinCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder estPhoneNumber(final String estPhoneNumber) {
            this.estPhoneNumber = estPhoneNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnCurrencyExponent(final int txnCurrencyExponent) {
            this.txnCurrencyExponent = txnCurrencyExponent;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder mercuryRefId(final String mercuryRefId) {
            this.mercuryRefId = mercuryRefId;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder dmsSmsMode(final Character dmsSmsMode) {
            this.dmsSmsMode = dmsSmsMode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder encryptedCardNumber(final String encryptedCardNumber) {
            this.encryptedCardNumber = encryptedCardNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder orgInstIdCode(final String orgInstIdCode) {
            this.orgInstIdCode = orgInstIdCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder trlType(final String trlType) {
            this.trlType = trlType;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder settlementIndicator(final Character settlementIndicator) {
            this.settlementIndicator = settlementIndicator;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder txnFeeAmount(final Double txnFeeAmount) {
            this.txnFeeAmount = txnFeeAmount;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder motoEcomIndicator(final String motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder responseCode(final String responseCode) {
            this.responseCode = responseCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder acqinstIdCode(final String acqinstIdCode) {
            this.acqinstIdCode = acqinstIdCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder acqRefData(final String acqRefData) {
            this.acqRefData = acqRefData;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardInputMode(final Character cardInputMode) {
            this.cardInputMode = cardInputMode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardInputCapability(final Character cardInputCapability) {
            this.cardInputCapability = cardInputCapability;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardSeqNumber(final String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder appICProfile(final String appICProfile) {
            this.appICProfile = appICProfile;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder appTxnCounter(final String appTxnCounter) {
            this.appTxnCounter = appTxnCounter;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder appCryptogram(final String appCryptogram) {
            this.appCryptogram = appCryptogram;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cryptAmount(final Double cryptAmount) {
            this.cryptAmount = cryptAmount;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cryptInfoData(final String cryptInfoData) {
            this.cryptInfoData = cryptInfoData;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cvmResult(final String cvmResult) {
            this.cvmResult = cvmResult;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder dedicatedFileName(final String dedicatedFileName) {
            this.dedicatedFileName = dedicatedFileName;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder ifdSerNumber(final String ifdSerNumber) {
            this.ifdSerNumber = ifdSerNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder issAppData(final String issAppData) {
            this.issAppData = issAppData;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder issAuthData(final String issAuthData) {
            this.issAuthData = issAuthData;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder trlConCode(final String trlConCode) {
            this.trlConCode = trlConCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder trlAppVerNumber(final String trlAppVerNumber) {
            this.trlAppVerNumber = trlAppVerNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chipTrlCapabilities(final String chipTrlCapabilities) {
            this.chipTrlCapabilities = chipTrlCapabilities;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chipTrlType(final String chipTrlType) {
            this.chipTrlType = chipTrlType;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder trlVerResult(final String trlVerResult) {
            this.trlVerResult = trlVerResult;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chipTxnDate(final String chipTxnDate) {
            this.chipTxnDate = chipTxnDate;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chipTxnType(final String chipTxnType) {
            this.chipTxnType = chipTxnType;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chipCurCode(final String chipCurCode) {
            this.chipCurCode = chipCurCode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder upblNumber(final String upblNumber) {
            this.upblNumber = upblNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder centreProcDate(final LocalDate centreProcDate) {
            this.centreProcDate = centreProcDate;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder fileProcDate(final LocalDate fileProcDate) {
            this.fileProcDate = fileProcDate;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder fileID(final String fileID) {
            this.fileID = fileID;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder cardPresent(final Character cardPresent) {
            this.cardPresent = cardPresent;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder chPresent(final Character chPresent) {
            this.chPresent = chPresent;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder panSequenceNumber(final String panSequenceNumber) {
            this.panSequenceNumber = panSequenceNumber;
            return this;
        }
        
        public MercuryAcqTxnWorkEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public MercuryAcqTxnWorkEntity build() {
            return new MercuryAcqTxnWorkEntity(this.serNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.intCode, this.prjSerNumber, this.generalStatus, this.txnRefNumber, this.rrn, this.merchantId, this.terminalId, this.txnType, this.cardNumber, this.txnAmount, this.surchargeAmount, this.localDateTime, this.txnDate, this.chargeType, this.typeOfCharge, this.geoArea, this.meName, this.meCity, this.meCountry, this.cardAccepStreetAddress, this.cardAccepStateCode, this.mePinCode, this.estPhoneNumber, this.mcc, this.cardType, this.approvalCode, this.txnCurrencyExponent, this.txnCurCode, this.mercuryRefId, this.cardDomIntlFlag, this.dmsSmsMode, this.encryptedCardNumber, this.orgInstIdCode, this.trlType, this.settlementIndicator, this.txnFeeAmount, this.motoEcomIndicator, this.responseCode, this.acqinstIdCode, this.acqRefData, this.cardInputMode, this.cardInputCapability, this.cardSeqNumber, this.appICProfile, this.appTxnCounter, this.appCryptogram, this.cryptAmount, this.cashBackAmount, this.cryptInfoData, this.cvmResult, this.dedicatedFileName, this.ifdSerNumber, this.issAppData, this.issAuthData, this.trlConCode, this.trlAppVerNumber, this.chipTrlCapabilities, this.chipTrlType, this.trlVerResult, this.chipTxnDate, this.chipTxnType, this.chipCurCode, this.upblNumber, this.centreProcDate, this.fileProcDate, this.fileID, this.cardPresent, this.chPresent, this.panSequenceNumber, this.posEntryMode);
        }
        
        @Override
        public String toString() {
            return "MercuryAcqTxnWorkEntity.MercuryAcqTxnWorkEntityBuilder(serNumber=" + this.serNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", intCode=" + this.intCode + ", prjSerNumber=" + this.prjSerNumber + ", generalStatus=" + this.generalStatus + ", txnRefNumber=" + this.txnRefNumber + ", rrn=" + this.rrn + ", merchantId=" + this.merchantId + ", terminalId=" + this.terminalId + ", txnType=" + this.txnType + ", cardNumber=" + this.cardNumber + ", txnAmount=" + this.txnAmount + ", surchargeAmount=" + this.surchargeAmount + ", localDateTime=" + String.valueOf(this.localDateTime) + ", txnDate=" + String.valueOf(this.txnDate) + ", chargeType=" + this.chargeType + ", typeOfCharge=" + this.typeOfCharge + ", geoArea=" + this.geoArea + ", meName=" + this.meName + ", meCity=" + this.meCity + ", meCountry=" + this.meCountry + ", cardAccepStreetAddress=" + this.cardAccepStreetAddress + ", cardAccepStateCode=" + this.cardAccepStateCode + ", mePinCode=" + this.mePinCode + ", estPhoneNumber=" + this.estPhoneNumber + ", mcc=" + this.mcc + ", cardType=" + this.cardType + ", approvalCode=" + this.approvalCode + ", txnCurrencyExponent=" + this.txnCurrencyExponent + ", txnCurCode=" + this.txnCurCode + ", mercuryRefId=" + this.mercuryRefId + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", dmsSmsMode=" + this.dmsSmsMode + ", encryptedCardNumber=" + this.encryptedCardNumber + ", orgInstIdCode=" + this.orgInstIdCode + ", trlType=" + this.trlType + ", settlementIndicator=" + this.settlementIndicator + ", txnFeeAmount=" + this.txnFeeAmount + ", motoEcomIndicator=" + this.motoEcomIndicator + ", responseCode=" + this.responseCode + ", acqinstIdCode=" + this.acqinstIdCode + ", acqRefData=" + this.acqRefData + ", cardInputMode=" + this.cardInputMode + ", cardInputCapability=" + this.cardInputCapability + ", cardSeqNumber=" + this.cardSeqNumber + ", appICProfile=" + this.appICProfile + ", appTxnCounter=" + this.appTxnCounter + ", appCryptogram=" + this.appCryptogram + ", cryptAmount=" + this.cryptAmount + ", cashBackAmount=" + this.cashBackAmount + ", cryptInfoData=" + this.cryptInfoData + ", cvmResult=" + this.cvmResult + ", dedicatedFileName=" + this.dedicatedFileName + ", ifdSerNumber=" + this.ifdSerNumber + ", issAppData=" + this.issAppData + ", issAuthData=" + this.issAuthData + ", trlConCode=" + this.trlConCode + ", trlAppVerNumber=" + this.trlAppVerNumber + ", chipTrlCapabilities=" + this.chipTrlCapabilities + ", chipTrlType=" + this.chipTrlType + ", trlVerResult=" + this.trlVerResult + ", chipTxnDate=" + this.chipTxnDate + ", chipTxnType=" + this.chipTxnType + ", chipCurCode=" + this.chipCurCode + ", upblNumber=" + this.upblNumber + ", centreProcDate=" + String.valueOf(this.centreProcDate) + ", fileProcDate=" + String.valueOf(this.fileProcDate) + ", fileID=" + this.fileID + ", cardPresent=" + this.cardPresent + ", chPresent=" + this.chPresent + ", panSequenceNumber=" + this.panSequenceNumber + ", posEntryMode=" + this.posEntryMode;
        }
    }
}
