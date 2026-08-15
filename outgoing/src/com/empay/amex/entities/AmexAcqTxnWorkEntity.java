/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.amex.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="AMEX_ACQ_TXN_WORK")
public class AmexAcqTxnWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ATD_SER_NUMBER")
    private Integer serNumber;
    @Column(name="ATD_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="ATD_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="ATD_INS_CODE")
    private Integer institutionCode;
    @Column(name="ATD_INT_CODE")
    private Integer intCode;
    @Column(name="ATD_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name="ATD_TXN_REF_NUMBER")
    private Integer txnRefSerNumber;
    @Column(name="ATD_TXN_TYPE")
    private String txnType;
    @Column(name="ATD_CARD_NUMBER")
    private String cardNumber;
    @Column(name="ATD_PROC_CODE")
    private String procCode;
    @Column(name="ATD_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="ATD_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name="ATD_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="ATD_POS_DATA_CODE")
    private String posDataCode;
    @Column(name="ATD_MCC")
    private String mcc;
    @Column(name="ATD_RET_REF_NUMBER")
    private String rrn;
    @Column(name="ATD_APPR_CODE")
    private String approvalCode;
    @Column(name="ATD_TERMINAL_ID")
    private String terminalId;
    @Column(name="ATD_MERCHANT_ID")
    private String merchantId;
    @Column(name="ATD_MAPPED_MID")
    private String mappedMid;
    @Column(name="ATD_ME_NAME")
    private String meName;
    @Column(name="ATD_ME_CITY")
    private String meCity;
    @Column(name="ATD_ME_ZIP_CODE")
    private String mePinCode;
    @Column(name="ATD_ME_COUNTRY")
    private String meCountry;
    @Column(name="ATD_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name="ATD_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="ATD_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name="ATD_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name="ATD_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name="ATD_ISS_APP_DATA")
    private String issAppData;
    @Column(name="ATD_UPBL_NUMBER")
    private String upblNumber;
    @Column(name="ATD_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name="ATD_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name="ATD_TXN_DATE")
    private LocalDate txnDate;
    @Column(name="ATD_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name="ATD_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name="ATD_TRL_CON_CODE")
    private String trlConCode;
    @Column(name="ATD_CASHBACK_AMOUNT")
    private Double chipCashBack;
    @Column(name="ATD_TXN_ID")
    private String txnId;
    @Column(name="ATD_TRL_BTH_NUMBER")
    private Integer trlBthNumber;
    @Column(name="ATD_CARD_TYPE")
    private Character cardType;
    @Column(name="ATD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="ATD_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name="ATD_TRL_TYPE")
    private String trlType;
    @Column(name="ATD_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name="ATD_OUT_FILE_DATE")
    private LocalDate outFileDate;
    @Column(name="ATD_FILE_ID")
    private String fileId;
    @Column(name="ATD_GEN_STATUS")
    private Integer genStatus;
    @Column(name="ATD_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name="ATD_EXPIRY_DATE")
    private String expiryDate;
    @Column(name="ATD_EMV")
    private String emv;
    @Column(name="ATD_LOCATION_ADDRESS")
    private String locationAddress;
    @Column(name="ATD_ME_CONTACT_EMAIL")
    private String contactEmail;
    @Column(name="ATD_TRL_LOCATION")
    private String trlLocation;
    @Column(name="ATD_LOC_REG_CODE")
    private String locRegionCode;
    @Column(name="ATD_STAN")
    private String stan;
    @Column(name="ATD_INVOICE_NUMBER")
    private String invoiceNumber;

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

    public Integer getTxnRefSerNumber() {
        return this.txnRefSerNumber;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcCode() {
        return this.procCode;
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

    public String getPosDataCode() {
        return this.posDataCode;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getRrn() {
        return this.rrn;
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

    public String getMappedMid() {
        return this.mappedMid;
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

    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
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

    public String getTxnId() {
        return this.txnId;
    }

    public Integer getTrlBthNumber() {
        return this.trlBthNumber;
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

    public String getTrlType() {
        return this.trlType;
    }

    public LocalDate getCentreProcDate() {
        return this.centreProcDate;
    }

    public LocalDate getOutFileDate() {
        return this.outFileDate;
    }

    public String getFileId() {
        return this.fileId;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getEmv() {
        return this.emv;
    }

    public String getLocationAddress() {
        return this.locationAddress;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public String getTrlLocation() {
        return this.trlLocation;
    }

    public String getLocRegionCode() {
        return this.locRegionCode;
    }

    public String getStan() {
        return this.stan;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
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

    public void setTxnRefSerNumber(Integer txnRefSerNumber) {
        this.txnRefSerNumber = txnRefSerNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
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

    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
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

    public void setMappedMid(String mappedMid) {
        this.mappedMid = mappedMid;
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

    public void setMotoEcomIndicator(String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
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

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setTrlBthNumber(Integer trlBthNumber) {
        this.trlBthNumber = trlBthNumber;
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

    public void setTrlType(String trlType) {
        this.trlType = trlType;
    }

    public void setCentreProcDate(LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }

    public void setOutFileDate(LocalDate outFileDate) {
        this.outFileDate = outFileDate;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setEmv(String emv) {
        this.emv = emv;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setTrlLocation(String trlLocation) {
        this.trlLocation = trlLocation;
    }

    public void setLocRegionCode(String locRegionCode) {
        this.locRegionCode = locRegionCode;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AmexAcqTxnWorkEntity)) {
            return false;
        }
        AmexAcqTxnWorkEntity other = (AmexAcqTxnWorkEntity)o;
        if (!other.canEqual((Object)this)) {
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
        Integer this$trlBthNumber = this.getTrlBthNumber();
        Integer other$trlBthNumber = other.getTrlBthNumber();
        if (this$trlBthNumber == null ? other$trlBthNumber != null : !((Object)this$trlBthNumber).equals(other$trlBthNumber)) {
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
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
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
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$posDataCode = this.getPosDataCode();
        String other$posDataCode = other.getPosDataCode();
        if (this$posDataCode == null ? other$posDataCode != null : !this$posDataCode.equals(other$posDataCode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
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
        String this$mappedMid = this.getMappedMid();
        String other$mappedMid = other.getMappedMid();
        if (this$mappedMid == null ? other$mappedMid != null : !this$mappedMid.equals(other$mappedMid)) {
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
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
            return false;
        }
        String this$trlType = this.getTrlType();
        String other$trlType = other.getTrlType();
        if (this$trlType == null ? other$trlType != null : !this$trlType.equals(other$trlType)) {
            return false;
        }
        LocalDate this$centreProcDate = this.getCentreProcDate();
        LocalDate other$centreProcDate = other.getCentreProcDate();
        if (this$centreProcDate == null ? other$centreProcDate != null : !((Object)this$centreProcDate).equals(other$centreProcDate)) {
            return false;
        }
        LocalDate this$outFileDate = this.getOutFileDate();
        LocalDate other$outFileDate = other.getOutFileDate();
        if (this$outFileDate == null ? other$outFileDate != null : !((Object)this$outFileDate).equals(other$outFileDate)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$encryptedCardNumber = this.getEncryptedCardNumber();
        String other$encryptedCardNumber = other.getEncryptedCardNumber();
        if (this$encryptedCardNumber == null ? other$encryptedCardNumber != null : !this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
            return false;
        }
        String this$expiryDate = this.getExpiryDate();
        String other$expiryDate = other.getExpiryDate();
        if (this$expiryDate == null ? other$expiryDate != null : !this$expiryDate.equals(other$expiryDate)) {
            return false;
        }
        String this$emv = this.getEmv();
        String other$emv = other.getEmv();
        if (this$emv == null ? other$emv != null : !this$emv.equals(other$emv)) {
            return false;
        }
        String this$locationAddress = this.getLocationAddress();
        String other$locationAddress = other.getLocationAddress();
        if (this$locationAddress == null ? other$locationAddress != null : !this$locationAddress.equals(other$locationAddress)) {
            return false;
        }
        String this$contactEmail = this.getContactEmail();
        String other$contactEmail = other.getContactEmail();
        if (this$contactEmail == null ? other$contactEmail != null : !this$contactEmail.equals(other$contactEmail)) {
            return false;
        }
        String this$trlLocation = this.getTrlLocation();
        String other$trlLocation = other.getTrlLocation();
        if (this$trlLocation == null ? other$trlLocation != null : !this$trlLocation.equals(other$trlLocation)) {
            return false;
        }
        String this$locRegionCode = this.getLocRegionCode();
        String other$locRegionCode = other.getLocRegionCode();
        if (this$locRegionCode == null ? other$locRegionCode != null : !this$locRegionCode.equals(other$locRegionCode)) {
            return false;
        }
        String this$stan = this.getStan();
        String other$stan = other.getStan();
        if (this$stan == null ? other$stan != null : !this$stan.equals(other$stan)) {
            return false;
        }
        String this$invoiceNumber = this.getInvoiceNumber();
        String other$invoiceNumber = other.getInvoiceNumber();
        return !(this$invoiceNumber == null ? other$invoiceNumber != null : !this$invoiceNumber.equals(other$invoiceNumber));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AmexAcqTxnWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
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
        Double $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : ((Object)$cryptAmount).hashCode());
        Double $chipCashBack = this.getChipCashBack();
        result = result * 59 + ($chipCashBack == null ? 43 : ((Object)$chipCashBack).hashCode());
        Integer $trlBthNumber = this.getTrlBthNumber();
        result = result * 59 + ($trlBthNumber == null ? 43 : ((Object)$trlBthNumber).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Character $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + ($dmsSmsMode == null ? 43 : ((Object)$dmsSmsMode).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $posDataCode = this.getPosDataCode();
        result = result * 59 + ($posDataCode == null ? 43 : $posDataCode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $mappedMid = this.getMappedMid();
        result = result * 59 + ($mappedMid == null ? 43 : $mappedMid.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $mePinCode = this.getMePinCode();
        result = result * 59 + ($mePinCode == null ? 43 : $mePinCode.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : $motoEcomIndicator.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
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
        String $appICProfile = this.getAppICProfile();
        result = result * 59 + ($appICProfile == null ? 43 : $appICProfile.hashCode());
        String $trlConCode = this.getTrlConCode();
        result = result * 59 + ($trlConCode == null ? 43 : $trlConCode.hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $trlType = this.getTrlType();
        result = result * 59 + ($trlType == null ? 43 : $trlType.hashCode());
        LocalDate $centreProcDate = this.getCentreProcDate();
        result = result * 59 + ($centreProcDate == null ? 43 : ((Object)$centreProcDate).hashCode());
        LocalDate $outFileDate = this.getOutFileDate();
        result = result * 59 + ($outFileDate == null ? 43 : ((Object)$outFileDate).hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $expiryDate = this.getExpiryDate();
        result = result * 59 + ($expiryDate == null ? 43 : $expiryDate.hashCode());
        String $emv = this.getEmv();
        result = result * 59 + ($emv == null ? 43 : $emv.hashCode());
        String $locationAddress = this.getLocationAddress();
        result = result * 59 + ($locationAddress == null ? 43 : $locationAddress.hashCode());
        String $contactEmail = this.getContactEmail();
        result = result * 59 + ($contactEmail == null ? 43 : $contactEmail.hashCode());
        String $trlLocation = this.getTrlLocation();
        result = result * 59 + ($trlLocation == null ? 43 : $trlLocation.hashCode());
        String $locRegionCode = this.getLocRegionCode();
        result = result * 59 + ($locRegionCode == null ? 43 : $locRegionCode.hashCode());
        String $stan = this.getStan();
        result = result * 59 + ($stan == null ? 43 : $stan.hashCode());
        String $invoiceNumber = this.getInvoiceNumber();
        result = result * 59 + ($invoiceNumber == null ? 43 : $invoiceNumber.hashCode());
        return result;
    }

    public String toString() {
        return "AmexAcqTxnWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", txnRefSerNumber=" + this.getTxnRefSerNumber() + ", txnType=" + this.getTxnType() + ", cardNumber=" + this.getCardNumber() + ", procCode=" + this.getProcCode() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", posDataCode=" + this.getPosDataCode() + ", mcc=" + this.getMcc() + ", rrn=" + this.getRrn() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", mappedMid=" + this.getMappedMid() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", mePinCode=" + this.getMePinCode() + ", meCountry=" + this.getMeCountry() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", txnCurCode=" + this.getTxnCurCode() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", issAppData=" + this.getIssAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", trlVerResult=" + this.getTrlVerResult() + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", cryptAmount=" + this.getCryptAmount() + ", appICProfile=" + this.getAppICProfile() + ", trlConCode=" + this.getTrlConCode() + ", chipCashBack=" + this.getChipCashBack() + ", txnId=" + this.getTxnId() + ", trlBthNumber=" + this.getTrlBthNumber() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", trlType=" + this.getTrlType() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", outFileDate=" + String.valueOf(this.getOutFileDate()) + ", fileId=" + this.getFileId() + ", genStatus=" + this.getGenStatus() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", expiryDate=" + this.getExpiryDate() + ", emv=" + this.getEmv() + ", locationAddress=" + this.getLocationAddress() + ", contactEmail=" + this.getContactEmail() + ", trlLocation=" + this.getTrlLocation() + ", locRegionCode=" + this.getLocRegionCode() + ", stan=" + this.getStan() + ", invoiceNumber=" + this.getInvoiceNumber() + ")";
    }
}

