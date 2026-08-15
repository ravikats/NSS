// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "AMEX_ACQ_TXN_WORK")
public class AmexAcqTxnWorkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATD_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ATD_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "ATD_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "ATD_INS_CODE")
    private Integer institutionCode;
    @Column(name = "ATD_INT_CODE")
    private Integer intCode;
    @Column(name = "ATD_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "ATD_TXN_REF_NUMBER")
    private Integer txnRefSerNumber;
    @Column(name = "ATD_TXN_TYPE")
    private String txnType;
    @Column(name = "ATD_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "ATD_PROC_CODE")
    private String procCode;
    @Column(name = "ATD_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "ATD_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name = "ATD_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "ATD_POS_DATA_CODE")
    private String posDataCode;
    @Column(name = "ATD_MCC")
    private String mcc;
    @Column(name = "ATD_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "ATD_APPR_CODE")
    private String approvalCode;
    @Column(name = "ATD_TERMINAL_ID")
    private String terminalId;
    @Column(name = "ATD_MERCHANT_ID")
    private String merchantId;
    @Column(name = "ATD_MAPPED_MID")
    private String mappedMid;
    @Column(name = "ATD_ME_NAME")
    private String meName;
    @Column(name = "ATD_ME_CITY")
    private String meCity;
    @Column(name = "ATD_ME_ZIP_CODE")
    private String mePinCode;
    @Column(name = "ATD_ME_COUNTRY")
    private String meCountry;
    @Column(name = "ATD_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name = "ATD_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "ATD_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name = "ATD_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name = "ATD_CRYPT_INFO_DATA")
    private String cryptInfoData;
    @Column(name = "ATD_ISS_APP_DATA")
    private String issAppData;
    @Column(name = "ATD_UPBL_NUMBER")
    private String upblNumber;
    @Column(name = "ATD_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name = "ATD_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name = "ATD_TXN_DATE")
    private LocalDate txnDate;
    @Column(name = "ATD_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name = "ATD_APP_IC_PROFILE")
    private String appICProfile;
    @Column(name = "ATD_TRL_CON_CODE")
    private String trlConCode;
    @Column(name = "ATD_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "ATD_TXN_ID")
    private String txnId;
    @Column(name = "ATD_TRL_BTH_NUMBER")
    private Integer trlBthNumber;
    @Column(name = "ATD_CARD_TYPE")
    private Character cardType;
    @Column(name = "ATD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "ATD_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name = "ATD_TRL_TYPE")
    private String trlType;
    @Column(name = "ATD_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name = "ATD_OUT_FILE_DATE")
    private LocalDate outFileDate;
    @Column(name = "ATD_FILE_ID")
    private String fileId;
    @Column(name = "ATD_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "ATD_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name = "ATD_EXPIRY_DATE")
    private String expiryDate;
    @Column(name = "ATD_EMV")
    private String emv;
    @Column(name = "ATD_LOCATION_ADDRESS")
    private String locationAddress;
    @Column(name = "ATD_ME_CONTACT_EMAIL")
    private String contactEmail;
    @Column(name = "ATD_TRL_LOCATION")
    private String trlLocation;
    @Column(name = "ATD_LOC_REG_CODE")
    private String locRegionCode;
    @Column(name = "ATD_STAN")
    private String stan;
    @Column(name = "ATD_INVOICE_NUMBER")
    private String invoiceNumber;
    
    public static AmexAcqTxnWorkEntityBuilder builder() {
        return new AmexAcqTxnWorkEntityBuilder();
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
    
    public Double getCashBackAmount() {
        return this.cashBackAmount;
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
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInstitutionCode(final Integer institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setPrjSerNumber(final Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }
    
    public void setTxnRefSerNumber(final Integer txnRefSerNumber) {
        this.txnRefSerNumber = txnRefSerNumber;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
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
    
    public void setPosDataCode(final String posDataCode) {
        this.posDataCode = posDataCode;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
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
    
    public void setMappedMid(final String mappedMid) {
        this.mappedMid = mappedMid;
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
    
    public void setMotoEcomIndicator(final String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
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
    
    public void setTxnId(final String txnId) {
        this.txnId = txnId;
    }
    
    public void setTrlBthNumber(final Integer trlBthNumber) {
        this.trlBthNumber = trlBthNumber;
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
    
    public void setTrlType(final String trlType) {
        this.trlType = trlType;
    }
    
    public void setCentreProcDate(final LocalDate centreProcDate) {
        this.centreProcDate = centreProcDate;
    }
    
    public void setOutFileDate(final LocalDate outFileDate) {
        this.outFileDate = outFileDate;
    }
    
    public void setFileId(final String fileId) {
        this.fileId = fileId;
    }
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
    }
    
    public void setEncryptedCardNumber(final String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }
    
    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setEmv(final String emv) {
        this.emv = emv;
    }
    
    public void setLocationAddress(final String locationAddress) {
        this.locationAddress = locationAddress;
    }
    
    public void setContactEmail(final String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    public void setTrlLocation(final String trlLocation) {
        this.trlLocation = trlLocation;
    }
    
    public void setLocRegionCode(final String locRegionCode) {
        this.locRegionCode = locRegionCode;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public void setInvoiceNumber(final String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AmexAcqTxnWorkEntity)) {
            return false;
        }
        final AmexAcqTxnWorkEntity other = (AmexAcqTxnWorkEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0065: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0102: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0102;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$institutionCode = this.getInstitutionCode();
        final Object other$institutionCode = other.getInstitutionCode();
        Label_0139: {
            if (this$institutionCode == null) {
                if (other$institutionCode == null) {
                    break Label_0139;
                }
            }
            else if (this$institutionCode.equals(other$institutionCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$intCode = this.getIntCode();
        final Object other$intCode = other.getIntCode();
        Label_0176: {
            if (this$intCode == null) {
                if (other$intCode == null) {
                    break Label_0176;
                }
            }
            else if (this$intCode.equals(other$intCode)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$prjSerNumber = this.getPrjSerNumber();
        final Object other$prjSerNumber = other.getPrjSerNumber();
        Label_0213: {
            if (this$prjSerNumber == null) {
                if (other$prjSerNumber == null) {
                    break Label_0213;
                }
            }
            else if (this$prjSerNumber.equals(other$prjSerNumber)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$txnRefSerNumber = this.getTxnRefSerNumber();
        final Object other$txnRefSerNumber = other.getTxnRefSerNumber();
        Label_0250: {
            if (this$txnRefSerNumber == null) {
                if (other$txnRefSerNumber == null) {
                    break Label_0250;
                }
            }
            else if (this$txnRefSerNumber.equals(other$txnRefSerNumber)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$txnAmount = this.getTxnAmount();
        final Object other$txnAmount = other.getTxnAmount();
        Label_0287: {
            if (this$txnAmount == null) {
                if (other$txnAmount == null) {
                    break Label_0287;
                }
            }
            else if (this$txnAmount.equals(other$txnAmount)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$surchargeAmount = this.getSurchargeAmount();
        final Object other$surchargeAmount = other.getSurchargeAmount();
        Label_0324: {
            if (this$surchargeAmount == null) {
                if (other$surchargeAmount == null) {
                    break Label_0324;
                }
            }
            else if (this$surchargeAmount.equals(other$surchargeAmount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$cryptAmount = this.getCryptAmount();
        final Object other$cryptAmount = other.getCryptAmount();
        Label_0361: {
            if (this$cryptAmount == null) {
                if (other$cryptAmount == null) {
                    break Label_0361;
                }
            }
            else if (this$cryptAmount.equals(other$cryptAmount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$cashBackAmount = this.getCashBackAmount();
        final Object other$cashBackAmount = other.getCashBackAmount();
        Label_0398: {
            if (this$cashBackAmount == null) {
                if (other$cashBackAmount == null) {
                    break Label_0398;
                }
            }
            else if (this$cashBackAmount.equals(other$cashBackAmount)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$trlBthNumber = this.getTrlBthNumber();
        final Object other$trlBthNumber = other.getTrlBthNumber();
        Label_0435: {
            if (this$trlBthNumber == null) {
                if (other$trlBthNumber == null) {
                    break Label_0435;
                }
            }
            else if (this$trlBthNumber.equals(other$trlBthNumber)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0472: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0472;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$cardDomIntlFlag = this.getCardDomIntlFlag();
        final Object other$cardDomIntlFlag = other.getCardDomIntlFlag();
        Label_0509: {
            if (this$cardDomIntlFlag == null) {
                if (other$cardDomIntlFlag == null) {
                    break Label_0509;
                }
            }
            else if (this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$dmsSmsMode = this.getDmsSmsMode();
        final Object other$dmsSmsMode = other.getDmsSmsMode();
        Label_0546: {
            if (this$dmsSmsMode == null) {
                if (other$dmsSmsMode == null) {
                    break Label_0546;
                }
            }
            else if (this$dmsSmsMode.equals(other$dmsSmsMode)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$genStatus = this.getGenStatus();
        final Object other$genStatus = other.getGenStatus();
        Label_0583: {
            if (this$genStatus == null) {
                if (other$genStatus == null) {
                    break Label_0583;
                }
            }
            else if (this$genStatus.equals(other$genStatus)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0620: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0620;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_0657: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_0657;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$cardNumber = this.getCardNumber();
        final Object other$cardNumber = other.getCardNumber();
        Label_0694: {
            if (this$cardNumber == null) {
                if (other$cardNumber == null) {
                    break Label_0694;
                }
            }
            else if (this$cardNumber.equals(other$cardNumber)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$procCode = this.getProcCode();
        final Object other$procCode = other.getProcCode();
        Label_0731: {
            if (this$procCode == null) {
                if (other$procCode == null) {
                    break Label_0731;
                }
            }
            else if (this$procCode.equals(other$procCode)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$localDateTime = this.getLocalDateTime();
        final Object other$localDateTime = other.getLocalDateTime();
        Label_0768: {
            if (this$localDateTime == null) {
                if (other$localDateTime == null) {
                    break Label_0768;
                }
            }
            else if (this$localDateTime.equals(other$localDateTime)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$posDataCode = this.getPosDataCode();
        final Object other$posDataCode = other.getPosDataCode();
        Label_0805: {
            if (this$posDataCode == null) {
                if (other$posDataCode == null) {
                    break Label_0805;
                }
            }
            else if (this$posDataCode.equals(other$posDataCode)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0842: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0842;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0879: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0879;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_0916: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_0916;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_0953: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_0953;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_0990: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_0990;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$mappedMid = this.getMappedMid();
        final Object other$mappedMid = other.getMappedMid();
        Label_1027: {
            if (this$mappedMid == null) {
                if (other$mappedMid == null) {
                    break Label_1027;
                }
            }
            else if (this$mappedMid.equals(other$mappedMid)) {
                break Label_1027;
            }
            return false;
        }
        final Object this$meName = this.getMeName();
        final Object other$meName = other.getMeName();
        Label_1064: {
            if (this$meName == null) {
                if (other$meName == null) {
                    break Label_1064;
                }
            }
            else if (this$meName.equals(other$meName)) {
                break Label_1064;
            }
            return false;
        }
        final Object this$meCity = this.getMeCity();
        final Object other$meCity = other.getMeCity();
        Label_1101: {
            if (this$meCity == null) {
                if (other$meCity == null) {
                    break Label_1101;
                }
            }
            else if (this$meCity.equals(other$meCity)) {
                break Label_1101;
            }
            return false;
        }
        final Object this$mePinCode = this.getMePinCode();
        final Object other$mePinCode = other.getMePinCode();
        Label_1138: {
            if (this$mePinCode == null) {
                if (other$mePinCode == null) {
                    break Label_1138;
                }
            }
            else if (this$mePinCode.equals(other$mePinCode)) {
                break Label_1138;
            }
            return false;
        }
        final Object this$meCountry = this.getMeCountry();
        final Object other$meCountry = other.getMeCountry();
        Label_1175: {
            if (this$meCountry == null) {
                if (other$meCountry == null) {
                    break Label_1175;
                }
            }
            else if (this$meCountry.equals(other$meCountry)) {
                break Label_1175;
            }
            return false;
        }
        final Object this$motoEcomIndicator = this.getMotoEcomIndicator();
        final Object other$motoEcomIndicator = other.getMotoEcomIndicator();
        Label_1212: {
            if (this$motoEcomIndicator == null) {
                if (other$motoEcomIndicator == null) {
                    break Label_1212;
                }
            }
            else if (this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
                break Label_1212;
            }
            return false;
        }
        final Object this$txnCurCode = this.getTxnCurCode();
        final Object other$txnCurCode = other.getTxnCurCode();
        Label_1249: {
            if (this$txnCurCode == null) {
                if (other$txnCurCode == null) {
                    break Label_1249;
                }
            }
            else if (this$txnCurCode.equals(other$txnCurCode)) {
                break Label_1249;
            }
            return false;
        }
        final Object this$cardSeqNumber = this.getCardSeqNumber();
        final Object other$cardSeqNumber = other.getCardSeqNumber();
        Label_1286: {
            if (this$cardSeqNumber == null) {
                if (other$cardSeqNumber == null) {
                    break Label_1286;
                }
            }
            else if (this$cardSeqNumber.equals(other$cardSeqNumber)) {
                break Label_1286;
            }
            return false;
        }
        final Object this$appCryptogram = this.getAppCryptogram();
        final Object other$appCryptogram = other.getAppCryptogram();
        Label_1323: {
            if (this$appCryptogram == null) {
                if (other$appCryptogram == null) {
                    break Label_1323;
                }
            }
            else if (this$appCryptogram.equals(other$appCryptogram)) {
                break Label_1323;
            }
            return false;
        }
        final Object this$cryptInfoData = this.getCryptInfoData();
        final Object other$cryptInfoData = other.getCryptInfoData();
        Label_1360: {
            if (this$cryptInfoData == null) {
                if (other$cryptInfoData == null) {
                    break Label_1360;
                }
            }
            else if (this$cryptInfoData.equals(other$cryptInfoData)) {
                break Label_1360;
            }
            return false;
        }
        final Object this$issAppData = this.getIssAppData();
        final Object other$issAppData = other.getIssAppData();
        Label_1397: {
            if (this$issAppData == null) {
                if (other$issAppData == null) {
                    break Label_1397;
                }
            }
            else if (this$issAppData.equals(other$issAppData)) {
                break Label_1397;
            }
            return false;
        }
        final Object this$upblNumber = this.getUpblNumber();
        final Object other$upblNumber = other.getUpblNumber();
        Label_1434: {
            if (this$upblNumber == null) {
                if (other$upblNumber == null) {
                    break Label_1434;
                }
            }
            else if (this$upblNumber.equals(other$upblNumber)) {
                break Label_1434;
            }
            return false;
        }
        final Object this$appTxnCounter = this.getAppTxnCounter();
        final Object other$appTxnCounter = other.getAppTxnCounter();
        Label_1471: {
            if (this$appTxnCounter == null) {
                if (other$appTxnCounter == null) {
                    break Label_1471;
                }
            }
            else if (this$appTxnCounter.equals(other$appTxnCounter)) {
                break Label_1471;
            }
            return false;
        }
        final Object this$trlVerResult = this.getTrlVerResult();
        final Object other$trlVerResult = other.getTrlVerResult();
        Label_1508: {
            if (this$trlVerResult == null) {
                if (other$trlVerResult == null) {
                    break Label_1508;
                }
            }
            else if (this$trlVerResult.equals(other$trlVerResult)) {
                break Label_1508;
            }
            return false;
        }
        final Object this$txnDate = this.getTxnDate();
        final Object other$txnDate = other.getTxnDate();
        Label_1545: {
            if (this$txnDate == null) {
                if (other$txnDate == null) {
                    break Label_1545;
                }
            }
            else if (this$txnDate.equals(other$txnDate)) {
                break Label_1545;
            }
            return false;
        }
        final Object this$appICProfile = this.getAppICProfile();
        final Object other$appICProfile = other.getAppICProfile();
        Label_1582: {
            if (this$appICProfile == null) {
                if (other$appICProfile == null) {
                    break Label_1582;
                }
            }
            else if (this$appICProfile.equals(other$appICProfile)) {
                break Label_1582;
            }
            return false;
        }
        final Object this$trlConCode = this.getTrlConCode();
        final Object other$trlConCode = other.getTrlConCode();
        Label_1619: {
            if (this$trlConCode == null) {
                if (other$trlConCode == null) {
                    break Label_1619;
                }
            }
            else if (this$trlConCode.equals(other$trlConCode)) {
                break Label_1619;
            }
            return false;
        }
        final Object this$txnId = this.getTxnId();
        final Object other$txnId = other.getTxnId();
        Label_1656: {
            if (this$txnId == null) {
                if (other$txnId == null) {
                    break Label_1656;
                }
            }
            else if (this$txnId.equals(other$txnId)) {
                break Label_1656;
            }
            return false;
        }
        final Object this$trlType = this.getTrlType();
        final Object other$trlType = other.getTrlType();
        Label_1693: {
            if (this$trlType == null) {
                if (other$trlType == null) {
                    break Label_1693;
                }
            }
            else if (this$trlType.equals(other$trlType)) {
                break Label_1693;
            }
            return false;
        }
        final Object this$centreProcDate = this.getCentreProcDate();
        final Object other$centreProcDate = other.getCentreProcDate();
        Label_1730: {
            if (this$centreProcDate == null) {
                if (other$centreProcDate == null) {
                    break Label_1730;
                }
            }
            else if (this$centreProcDate.equals(other$centreProcDate)) {
                break Label_1730;
            }
            return false;
        }
        final Object this$outFileDate = this.getOutFileDate();
        final Object other$outFileDate = other.getOutFileDate();
        Label_1767: {
            if (this$outFileDate == null) {
                if (other$outFileDate == null) {
                    break Label_1767;
                }
            }
            else if (this$outFileDate.equals(other$outFileDate)) {
                break Label_1767;
            }
            return false;
        }
        final Object this$fileId = this.getFileId();
        final Object other$fileId = other.getFileId();
        Label_1804: {
            if (this$fileId == null) {
                if (other$fileId == null) {
                    break Label_1804;
                }
            }
            else if (this$fileId.equals(other$fileId)) {
                break Label_1804;
            }
            return false;
        }
        final Object this$encryptedCardNumber = this.getEncryptedCardNumber();
        final Object other$encryptedCardNumber = other.getEncryptedCardNumber();
        Label_1841: {
            if (this$encryptedCardNumber == null) {
                if (other$encryptedCardNumber == null) {
                    break Label_1841;
                }
            }
            else if (this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
                break Label_1841;
            }
            return false;
        }
        final Object this$expiryDate = this.getExpiryDate();
        final Object other$expiryDate = other.getExpiryDate();
        Label_1878: {
            if (this$expiryDate == null) {
                if (other$expiryDate == null) {
                    break Label_1878;
                }
            }
            else if (this$expiryDate.equals(other$expiryDate)) {
                break Label_1878;
            }
            return false;
        }
        final Object this$emv = this.getEmv();
        final Object other$emv = other.getEmv();
        Label_1915: {
            if (this$emv == null) {
                if (other$emv == null) {
                    break Label_1915;
                }
            }
            else if (this$emv.equals(other$emv)) {
                break Label_1915;
            }
            return false;
        }
        final Object this$locationAddress = this.getLocationAddress();
        final Object other$locationAddress = other.getLocationAddress();
        Label_1952: {
            if (this$locationAddress == null) {
                if (other$locationAddress == null) {
                    break Label_1952;
                }
            }
            else if (this$locationAddress.equals(other$locationAddress)) {
                break Label_1952;
            }
            return false;
        }
        final Object this$contactEmail = this.getContactEmail();
        final Object other$contactEmail = other.getContactEmail();
        Label_1989: {
            if (this$contactEmail == null) {
                if (other$contactEmail == null) {
                    break Label_1989;
                }
            }
            else if (this$contactEmail.equals(other$contactEmail)) {
                break Label_1989;
            }
            return false;
        }
        final Object this$trlLocation = this.getTrlLocation();
        final Object other$trlLocation = other.getTrlLocation();
        Label_2026: {
            if (this$trlLocation == null) {
                if (other$trlLocation == null) {
                    break Label_2026;
                }
            }
            else if (this$trlLocation.equals(other$trlLocation)) {
                break Label_2026;
            }
            return false;
        }
        final Object this$locRegionCode = this.getLocRegionCode();
        final Object other$locRegionCode = other.getLocRegionCode();
        Label_2063: {
            if (this$locRegionCode == null) {
                if (other$locRegionCode == null) {
                    break Label_2063;
                }
            }
            else if (this$locRegionCode.equals(other$locRegionCode)) {
                break Label_2063;
            }
            return false;
        }
        final Object this$stan = this.getStan();
        final Object other$stan = other.getStan();
        Label_2100: {
            if (this$stan == null) {
                if (other$stan == null) {
                    break Label_2100;
                }
            }
            else if (this$stan.equals(other$stan)) {
                break Label_2100;
            }
            return false;
        }
        final Object this$invoiceNumber = this.getInvoiceNumber();
        final Object other$invoiceNumber = other.getInvoiceNumber();
        if (this$invoiceNumber == null) {
            if (other$invoiceNumber == null) {
                return true;
            }
        }
        else if (this$invoiceNumber.equals(other$invoiceNumber)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof AmexAcqTxnWorkEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $institutionCode = this.getInstitutionCode();
        result = result * 59 + (($institutionCode == null) ? 43 : $institutionCode.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + (($prjSerNumber == null) ? 43 : $prjSerNumber.hashCode());
        final Object $txnRefSerNumber = this.getTxnRefSerNumber();
        result = result * 59 + (($txnRefSerNumber == null) ? 43 : $txnRefSerNumber.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + (($surchargeAmount == null) ? 43 : $surchargeAmount.hashCode());
        final Object $cryptAmount = this.getCryptAmount();
        result = result * 59 + (($cryptAmount == null) ? 43 : $cryptAmount.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $trlBthNumber = this.getTrlBthNumber();
        result = result * 59 + (($trlBthNumber == null) ? 43 : $trlBthNumber.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + (($dmsSmsMode == null) ? 43 : $dmsSmsMode.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $posDataCode = this.getPosDataCode();
        result = result * 59 + (($posDataCode == null) ? 43 : $posDataCode.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $mappedMid = this.getMappedMid();
        result = result * 59 + (($mappedMid == null) ? 43 : $mappedMid.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $mePinCode = this.getMePinCode();
        result = result * 59 + (($mePinCode == null) ? 43 : $mePinCode.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
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
        final Object $appICProfile = this.getAppICProfile();
        result = result * 59 + (($appICProfile == null) ? 43 : $appICProfile.hashCode());
        final Object $trlConCode = this.getTrlConCode();
        result = result * 59 + (($trlConCode == null) ? 43 : $trlConCode.hashCode());
        final Object $txnId = this.getTxnId();
        result = result * 59 + (($txnId == null) ? 43 : $txnId.hashCode());
        final Object $trlType = this.getTrlType();
        result = result * 59 + (($trlType == null) ? 43 : $trlType.hashCode());
        final Object $centreProcDate = this.getCentreProcDate();
        result = result * 59 + (($centreProcDate == null) ? 43 : $centreProcDate.hashCode());
        final Object $outFileDate = this.getOutFileDate();
        result = result * 59 + (($outFileDate == null) ? 43 : $outFileDate.hashCode());
        final Object $fileId = this.getFileId();
        result = result * 59 + (($fileId == null) ? 43 : $fileId.hashCode());
        final Object $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + (($encryptedCardNumber == null) ? 43 : $encryptedCardNumber.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * 59 + (($expiryDate == null) ? 43 : $expiryDate.hashCode());
        final Object $emv = this.getEmv();
        result = result * 59 + (($emv == null) ? 43 : $emv.hashCode());
        final Object $locationAddress = this.getLocationAddress();
        result = result * 59 + (($locationAddress == null) ? 43 : $locationAddress.hashCode());
        final Object $contactEmail = this.getContactEmail();
        result = result * 59 + (($contactEmail == null) ? 43 : $contactEmail.hashCode());
        final Object $trlLocation = this.getTrlLocation();
        result = result * 59 + (($trlLocation == null) ? 43 : $trlLocation.hashCode());
        final Object $locRegionCode = this.getLocRegionCode();
        result = result * 59 + (($locRegionCode == null) ? 43 : $locRegionCode.hashCode());
        final Object $stan = this.getStan();
        result = result * 59 + (($stan == null) ? 43 : $stan.hashCode());
        final Object $invoiceNumber = this.getInvoiceNumber();
        result = result * 59 + (($invoiceNumber == null) ? 43 : $invoiceNumber.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "AmexAcqTxnWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", txnRefSerNumber=" + this.getTxnRefSerNumber() + ", txnType=" + this.getTxnType() + ", cardNumber=" + this.getCardNumber() + ", procCode=" + this.getProcCode() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", posDataCode=" + this.getPosDataCode() + ", mcc=" + this.getMcc() + ", rrn=" + this.getRrn() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", mappedMid=" + this.getMappedMid() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", mePinCode=" + this.getMePinCode() + ", meCountry=" + this.getMeCountry() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", txnCurCode=" + this.getTxnCurCode() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appCryptogram=" + this.getAppCryptogram() + ", cryptInfoData=" + this.getCryptInfoData() + ", issAppData=" + this.getIssAppData() + ", upblNumber=" + this.getUpblNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", trlVerResult=" + this.getTrlVerResult() + ", txnDate=" + String.valueOf(this.getTxnDate()) + ", cryptAmount=" + this.getCryptAmount() + ", appICProfile=" + this.getAppICProfile() + ", trlConCode=" + this.getTrlConCode() + ", cashBackAmount=" + this.getCashBackAmount() + ", txnId=" + this.getTxnId() + ", trlBthNumber=" + this.getTrlBthNumber() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", trlType=" + this.getTrlType() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", outFileDate=" + String.valueOf(this.getOutFileDate()) + ", fileId=" + this.getFileId() + ", genStatus=" + this.getGenStatus() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", expiryDate=" + this.getExpiryDate() + ", emv=" + this.getEmv() + ", locationAddress=" + this.getLocationAddress() + ", contactEmail=" + this.getContactEmail() + ", trlLocation=" + this.getTrlLocation() + ", locRegionCode=" + this.getLocRegionCode() + ", stan=" + this.getStan() + ", invoiceNumber=" + this.getInvoiceNumber();
    }
    
    public AmexAcqTxnWorkEntity() {
    }
    
    public AmexAcqTxnWorkEntity(final Integer serNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final Integer institutionCode, final Integer intCode, final Integer prjSerNumber, final Integer txnRefSerNumber, final String txnType, final String cardNumber, final String procCode, final Double txnAmount, final Double surchargeAmount, final LocalDateTime localDateTime, final String posDataCode, final String mcc, final String rrn, final String approvalCode, final String terminalId, final String merchantId, final String mappedMid, final String meName, final String meCity, final String mePinCode, final String meCountry, final String motoEcomIndicator, final String txnCurCode, final String cardSeqNumber, final String appCryptogram, final String cryptInfoData, final String issAppData, final String upblNumber, final String appTxnCounter, final String trlVerResult, final LocalDate txnDate, final Double cryptAmount, final String appICProfile, final String trlConCode, final Double cashBackAmount, final String txnId, final Integer trlBthNumber, final Character cardType, final Character cardDomIntlFlag, final Character dmsSmsMode, final String trlType, final LocalDate centreProcDate, final LocalDate outFileDate, final String fileId, final Integer genStatus, final String encryptedCardNumber, final String expiryDate, final String emv, final String locationAddress, final String contactEmail, final String trlLocation, final String locRegionCode, final String stan, final String invoiceNumber) {
        this.serNumber = serNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.intCode = intCode;
        this.prjSerNumber = prjSerNumber;
        this.txnRefSerNumber = txnRefSerNumber;
        this.txnType = txnType;
        this.cardNumber = cardNumber;
        this.procCode = procCode;
        this.txnAmount = txnAmount;
        this.surchargeAmount = surchargeAmount;
        this.localDateTime = localDateTime;
        this.posDataCode = posDataCode;
        this.mcc = mcc;
        this.rrn = rrn;
        this.approvalCode = approvalCode;
        this.terminalId = terminalId;
        this.merchantId = merchantId;
        this.mappedMid = mappedMid;
        this.meName = meName;
        this.meCity = meCity;
        this.mePinCode = mePinCode;
        this.meCountry = meCountry;
        this.motoEcomIndicator = motoEcomIndicator;
        this.txnCurCode = txnCurCode;
        this.cardSeqNumber = cardSeqNumber;
        this.appCryptogram = appCryptogram;
        this.cryptInfoData = cryptInfoData;
        this.issAppData = issAppData;
        this.upblNumber = upblNumber;
        this.appTxnCounter = appTxnCounter;
        this.trlVerResult = trlVerResult;
        this.txnDate = txnDate;
        this.cryptAmount = cryptAmount;
        this.appICProfile = appICProfile;
        this.trlConCode = trlConCode;
        this.cashBackAmount = cashBackAmount;
        this.txnId = txnId;
        this.trlBthNumber = trlBthNumber;
        this.cardType = cardType;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.dmsSmsMode = dmsSmsMode;
        this.trlType = trlType;
        this.centreProcDate = centreProcDate;
        this.outFileDate = outFileDate;
        this.fileId = fileId;
        this.genStatus = genStatus;
        this.encryptedCardNumber = encryptedCardNumber;
        this.expiryDate = expiryDate;
        this.emv = emv;
        this.locationAddress = locationAddress;
        this.contactEmail = contactEmail;
        this.trlLocation = trlLocation;
        this.locRegionCode = locRegionCode;
        this.stan = stan;
        this.invoiceNumber = invoiceNumber;
    }
    
    public static class AmexAcqTxnWorkEntityBuilder
    {
        private Integer serNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private Integer institutionCode;
        private Integer intCode;
        private Integer prjSerNumber;
        private Integer txnRefSerNumber;
        private String txnType;
        private String cardNumber;
        private String procCode;
        private Double txnAmount;
        private Double surchargeAmount;
        private LocalDateTime localDateTime;
        private String posDataCode;
        private String mcc;
        private String rrn;
        private String approvalCode;
        private String terminalId;
        private String merchantId;
        private String mappedMid;
        private String meName;
        private String meCity;
        private String mePinCode;
        private String meCountry;
        private String motoEcomIndicator;
        private String txnCurCode;
        private String cardSeqNumber;
        private String appCryptogram;
        private String cryptInfoData;
        private String issAppData;
        private String upblNumber;
        private String appTxnCounter;
        private String trlVerResult;
        private LocalDate txnDate;
        private Double cryptAmount;
        private String appICProfile;
        private String trlConCode;
        private Double cashBackAmount;
        private String txnId;
        private Integer trlBthNumber;
        private Character cardType;
        private Character cardDomIntlFlag;
        private Character dmsSmsMode;
        private String trlType;
        private LocalDate centreProcDate;
        private LocalDate outFileDate;
        private String fileId;
        private Integer genStatus;
        private String encryptedCardNumber;
        private String expiryDate;
        private String emv;
        private String locationAddress;
        private String contactEmail;
        private String trlLocation;
        private String locRegionCode;
        private String stan;
        private String invoiceNumber;
        
        AmexAcqTxnWorkEntityBuilder() {
        }
        
        public AmexAcqTxnWorkEntityBuilder serNumber(final Integer serNumber) {
            this.serNumber = serNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder institutionCode(final Integer institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder txnRefSerNumber(final Integer txnRefSerNumber) {
            this.txnRefSerNumber = txnRefSerNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder procCode(final String procCode) {
            this.procCode = procCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder surchargeAmount(final Double surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder posDataCode(final String posDataCode) {
            this.posDataCode = posDataCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder mappedMid(final String mappedMid) {
            this.mappedMid = mappedMid;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder mePinCode(final String mePinCode) {
            this.mePinCode = mePinCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder motoEcomIndicator(final String motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cardSeqNumber(final String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder appCryptogram(final String appCryptogram) {
            this.appCryptogram = appCryptogram;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cryptInfoData(final String cryptInfoData) {
            this.cryptInfoData = cryptInfoData;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder issAppData(final String issAppData) {
            this.issAppData = issAppData;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder upblNumber(final String upblNumber) {
            this.upblNumber = upblNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder appTxnCounter(final String appTxnCounter) {
            this.appTxnCounter = appTxnCounter;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder trlVerResult(final String trlVerResult) {
            this.trlVerResult = trlVerResult;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder txnDate(final LocalDate txnDate) {
            this.txnDate = txnDate;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cryptAmount(final Double cryptAmount) {
            this.cryptAmount = cryptAmount;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder appICProfile(final String appICProfile) {
            this.appICProfile = appICProfile;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder trlConCode(final String trlConCode) {
            this.trlConCode = trlConCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder txnId(final String txnId) {
            this.txnId = txnId;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder trlBthNumber(final Integer trlBthNumber) {
            this.trlBthNumber = trlBthNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder dmsSmsMode(final Character dmsSmsMode) {
            this.dmsSmsMode = dmsSmsMode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder trlType(final String trlType) {
            this.trlType = trlType;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder centreProcDate(final LocalDate centreProcDate) {
            this.centreProcDate = centreProcDate;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder outFileDate(final LocalDate outFileDate) {
            this.outFileDate = outFileDate;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder fileId(final String fileId) {
            this.fileId = fileId;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder genStatus(final Integer genStatus) {
            this.genStatus = genStatus;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder encryptedCardNumber(final String encryptedCardNumber) {
            this.encryptedCardNumber = encryptedCardNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder expiryDate(final String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder emv(final String emv) {
            this.emv = emv;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder locationAddress(final String locationAddress) {
            this.locationAddress = locationAddress;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder contactEmail(final String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder trlLocation(final String trlLocation) {
            this.trlLocation = trlLocation;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder locRegionCode(final String locRegionCode) {
            this.locRegionCode = locRegionCode;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder stan(final String stan) {
            this.stan = stan;
            return this;
        }
        
        public AmexAcqTxnWorkEntityBuilder invoiceNumber(final String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }
        
        public AmexAcqTxnWorkEntity build() {
            return new AmexAcqTxnWorkEntity(this.serNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.intCode, this.prjSerNumber, this.txnRefSerNumber, this.txnType, this.cardNumber, this.procCode, this.txnAmount, this.surchargeAmount, this.localDateTime, this.posDataCode, this.mcc, this.rrn, this.approvalCode, this.terminalId, this.merchantId, this.mappedMid, this.meName, this.meCity, this.mePinCode, this.meCountry, this.motoEcomIndicator, this.txnCurCode, this.cardSeqNumber, this.appCryptogram, this.cryptInfoData, this.issAppData, this.upblNumber, this.appTxnCounter, this.trlVerResult, this.txnDate, this.cryptAmount, this.appICProfile, this.trlConCode, this.cashBackAmount, this.txnId, this.trlBthNumber, this.cardType, this.cardDomIntlFlag, this.dmsSmsMode, this.trlType, this.centreProcDate, this.outFileDate, this.fileId, this.genStatus, this.encryptedCardNumber, this.expiryDate, this.emv, this.locationAddress, this.contactEmail, this.trlLocation, this.locRegionCode, this.stan, this.invoiceNumber);
        }
        
        @Override
        public String toString() {
            return "AmexAcqTxnWorkEntity.AmexAcqTxnWorkEntityBuilder(serNumber=" + this.serNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", intCode=" + this.intCode + ", prjSerNumber=" + this.prjSerNumber + ", txnRefSerNumber=" + this.txnRefSerNumber + ", txnType=" + this.txnType + ", cardNumber=" + this.cardNumber + ", procCode=" + this.procCode + ", txnAmount=" + this.txnAmount + ", surchargeAmount=" + this.surchargeAmount + ", localDateTime=" + String.valueOf(this.localDateTime) + ", posDataCode=" + this.posDataCode + ", mcc=" + this.mcc + ", rrn=" + this.rrn + ", approvalCode=" + this.approvalCode + ", terminalId=" + this.terminalId + ", merchantId=" + this.merchantId + ", mappedMid=" + this.mappedMid + ", meName=" + this.meName + ", meCity=" + this.meCity + ", mePinCode=" + this.mePinCode + ", meCountry=" + this.meCountry + ", motoEcomIndicator=" + this.motoEcomIndicator + ", txnCurCode=" + this.txnCurCode + ", cardSeqNumber=" + this.cardSeqNumber + ", appCryptogram=" + this.appCryptogram + ", cryptInfoData=" + this.cryptInfoData + ", issAppData=" + this.issAppData + ", upblNumber=" + this.upblNumber + ", appTxnCounter=" + this.appTxnCounter + ", trlVerResult=" + this.trlVerResult + ", txnDate=" + String.valueOf(this.txnDate) + ", cryptAmount=" + this.cryptAmount + ", appICProfile=" + this.appICProfile + ", trlConCode=" + this.trlConCode + ", cashBackAmount=" + this.cashBackAmount + ", txnId=" + this.txnId + ", trlBthNumber=" + this.trlBthNumber + ", cardType=" + this.cardType + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", dmsSmsMode=" + this.dmsSmsMode + ", trlType=" + this.trlType + ", centreProcDate=" + String.valueOf(this.centreProcDate) + ", outFileDate=" + String.valueOf(this.outFileDate) + ", fileId=" + this.fileId + ", genStatus=" + this.genStatus + ", encryptedCardNumber=" + this.encryptedCardNumber + ", expiryDate=" + this.expiryDate + ", emv=" + this.emv + ", locationAddress=" + this.locationAddress + ", contactEmail=" + this.contactEmail + ", trlLocation=" + this.trlLocation + ", locRegionCode=" + this.locRegionCode + ", stan=" + this.stan + ", invoiceNumber=" + this.invoiceNumber;
        }
    }
}

