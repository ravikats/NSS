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
@Table(name = "JAYWAN_ACQ_TXN_WORK")
public class JaywanAcqTxnWorkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JWN_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "JWN_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "JWN_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "JWN_INS_CODE")
    private Integer institutionCode;
    @Column(name = "JWN_INT_CODE")
    private Integer intCode;
    @Column(name = "JWN_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "JWN_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name = "JWN_TXN_TYPE")
    private String txnType;
    @Column(name = "JWN_TXN_CODE")
    private String txnCode;
    @Column(name = "JWN_MSG_TYPE_ID")
    private String messageTypeId;
    @Column(name = "JWN_FUNC_CODE")
    private String functionCode;
    @Column(name = "JWN_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "JWN_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "JWN_ACQ_REF_DATA")
    private String acqRefData;
    @Column(name = "JWN_APPR_CODE")
    private String approvalCode;
    @Column(name = "JWN_TERMINAL_ID")
    private String terminalId;
    @Column(name = "JWN_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "JWN_SETL_AMOUNT")
    private Double settledAmount;
    @Column(name = "JWN_BILL_AMOUNT")
    private Double billAmount;
    @Column(name = "JWN_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name = "JWN_CONV_RATE")
    private Double convRate;
    @Column(name = "JWN_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "JWN_CASHBACK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "JWN_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "JWN_MERCHANT_ID")
    private String merchantId;
    @Column(name = "JWN_ME_NAME")
    private String meName;
    @Column(name = "JWN_ME_CITY")
    private String meCity;
    @Column(name = "JWN_ME_STATE_CODE")
    private String meStateCode;
    @Column(name = "JWN_ME_COUNTRY")
    private String meCountry;
    @Column(name = "JWN_MCC")
    private String mcc;
    @Column(name = "JWN_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name = "JWN_ACQ_INST_ID")
    private String acqinstIdCode;
    @Column(name = "JWN_REV_INDICATOR")
    private Character revIndiCator;
    @Column(name = "JWN_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "JWN_TRL_TYPE")
    private String trlType;
    @Column(name = "JWN_ME_CATEGORY_TYPE")
    private Character meCategoryType;
    @Column(name = "JWN_CARD_TYPE")
    private Character cardType;
    @Column(name = "JWN_SMS_DMS_FLAG")
    private Character dmsSmsMode;
    @Column(name = "JWN_CENTRE_PROC_DATE")
    private LocalDate centreProcDate;
    @Column(name = "JWN_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name = "JWN_FILE_ID")
    private String fileID;
    @Column(name = "JWN_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "JWN_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name = "JWN_RESP_CODE")
    private String responseCode;
    @Column(name = "JWN_ECOM_INDICATOR")
    private String motoEcomIndicator;
    @Column(name = "JWN_SETTL_DATE")
    private LocalDate settlDate;
    @Column(name = "JWN_SETTL_INDICATOR")
    private Character settlIndiCator;
    @Column(name = "JWN_POS_CONDITION_CODE")
    private String posConditionCode;
    @Column(name = "JWN_FULL_PARTIAL_INDICATOR")
    private Character fullPartialIndiCator;
    
    public static JaywanAcqTxnWorkEntityBuilder builder() {
        return new JaywanAcqTxnWorkEntityBuilder();
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
    
    public Integer getInstitutionCode() {
        return this.institutionCode;
    }
    
    public Integer getIntCode() {
        return this.intCode;
    }
    
    public Integer getPrjSerNumber() {
        return this.prjSerNumber;
    }
    
    public Integer getTxnRefNumber() {
        return this.txnRefNumber;
    }
    
    public String getTxnType() {
        return this.txnType;
    }
    
    public String getTxnCode() {
        return this.txnCode;
    }
    
    public String getMessageTypeId() {
        return this.messageTypeId;
    }
    
    public String getFunctionCode() {
        return this.functionCode;
    }
    
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }
    
    public String getCardNumber() {
        return this.cardNumber;
    }
    
    public String getAcqRefData() {
        return this.acqRefData;
    }
    
    public String getApprovalCode() {
        return this.approvalCode;
    }
    
    public String getTerminalId() {
        return this.terminalId;
    }
    
    public Double getTxnAmount() {
        return this.txnAmount;
    }
    
    public Double getSettledAmount() {
        return this.settledAmount;
    }
    
    public Double getBillAmount() {
        return this.billAmount;
    }
    
    public Double getSurchargeAmount() {
        return this.surchargeAmount;
    }
    
    public Double getConvRate() {
        return this.convRate;
    }
    
    public String getTxnCurCode() {
        return this.txnCurCode;
    }
    
    public Double getCashBackAmount() {
        return this.cashBackAmount;
    }
    
    public String getRrn() {
        return this.rrn;
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
    
    public String getMeStateCode() {
        return this.meStateCode;
    }
    
    public String getMeCountry() {
        return this.meCountry;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getPosEntryMode() {
        return this.posEntryMode;
    }
    
    public String getAcqinstIdCode() {
        return this.acqinstIdCode;
    }
    
    public Character getRevIndiCator() {
        return this.revIndiCator;
    }
    
    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }
    
    public String getTrlType() {
        return this.trlType;
    }
    
    public Character getMeCategoryType() {
        return this.meCategoryType;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public Character getDmsSmsMode() {
        return this.dmsSmsMode;
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
    
    public Integer getGenStatus() {
        return this.genStatus;
    }
    
    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }
    
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }
    
    public LocalDate getSettlDate() {
        return this.settlDate;
    }
    
    public Character getSettlIndiCator() {
        return this.settlIndiCator;
    }
    
    public String getPosConditionCode() {
        return this.posConditionCode;
    }
    
    public Character getFullPartialIndiCator() {
        return this.fullPartialIndiCator;
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
    
    public void setInstitutionCode(final Integer institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setPrjSerNumber(final Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }
    
    public void setTxnRefNumber(final Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setTxnCode(final String txnCode) {
        this.txnCode = txnCode;
    }
    
    public void setMessageTypeId(final String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }
    
    public void setFunctionCode(final String functionCode) {
        this.functionCode = functionCode;
    }
    
    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setAcqRefData(final String acqRefData) {
        this.acqRefData = acqRefData;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setSettledAmount(final Double settledAmount) {
        this.settledAmount = settledAmount;
    }
    
    public void setBillAmount(final Double billAmount) {
        this.billAmount = billAmount;
    }
    
    public void setSurchargeAmount(final Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }
    
    public void setConvRate(final Double convRate) {
        this.convRate = convRate;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }
    
    public void setCashBackAmount(final Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
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
    
    public void setMeStateCode(final String meStateCode) {
        this.meStateCode = meStateCode;
    }
    
    public void setMeCountry(final String meCountry) {
        this.meCountry = meCountry;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    public void setAcqinstIdCode(final String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }
    
    public void setRevIndiCator(final Character revIndiCator) {
        this.revIndiCator = revIndiCator;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setTrlType(final String trlType) {
        this.trlType = trlType;
    }
    
    public void setMeCategoryType(final Character meCategoryType) {
        this.meCategoryType = meCategoryType;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setDmsSmsMode(final Character dmsSmsMode) {
        this.dmsSmsMode = dmsSmsMode;
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
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
    }
    
    public void setEncryptedCardNumber(final String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setMotoEcomIndicator(final String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setSettlDate(final LocalDate settlDate) {
        this.settlDate = settlDate;
    }
    
    public void setSettlIndiCator(final Character settlIndiCator) {
        this.settlIndiCator = settlIndiCator;
    }
    
    public void setPosConditionCode(final String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }
    
    public void setFullPartialIndiCator(final Character fullPartialIndiCator) {
        this.fullPartialIndiCator = fullPartialIndiCator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanAcqTxnWorkEntity)) {
            return false;
        }
        final JaywanAcqTxnWorkEntity other = (JaywanAcqTxnWorkEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0065: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
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
        final Object this$txnRefNumber = this.getTxnRefNumber();
        final Object other$txnRefNumber = other.getTxnRefNumber();
        Label_0250: {
            if (this$txnRefNumber == null) {
                if (other$txnRefNumber == null) {
                    break Label_0250;
                }
            }
            else if (this$txnRefNumber.equals(other$txnRefNumber)) {
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
        final Object this$settledAmount = this.getSettledAmount();
        final Object other$settledAmount = other.getSettledAmount();
        Label_0324: {
            if (this$settledAmount == null) {
                if (other$settledAmount == null) {
                    break Label_0324;
                }
            }
            else if (this$settledAmount.equals(other$settledAmount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$billAmount = this.getBillAmount();
        final Object other$billAmount = other.getBillAmount();
        Label_0361: {
            if (this$billAmount == null) {
                if (other$billAmount == null) {
                    break Label_0361;
                }
            }
            else if (this$billAmount.equals(other$billAmount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$surchargeAmount = this.getSurchargeAmount();
        final Object other$surchargeAmount = other.getSurchargeAmount();
        Label_0398: {
            if (this$surchargeAmount == null) {
                if (other$surchargeAmount == null) {
                    break Label_0398;
                }
            }
            else if (this$surchargeAmount.equals(other$surchargeAmount)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$convRate = this.getConvRate();
        final Object other$convRate = other.getConvRate();
        Label_0435: {
            if (this$convRate == null) {
                if (other$convRate == null) {
                    break Label_0435;
                }
            }
            else if (this$convRate.equals(other$convRate)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$cashBackAmount = this.getCashBackAmount();
        final Object other$cashBackAmount = other.getCashBackAmount();
        Label_0472: {
            if (this$cashBackAmount == null) {
                if (other$cashBackAmount == null) {
                    break Label_0472;
                }
            }
            else if (this$cashBackAmount.equals(other$cashBackAmount)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$revIndiCator = this.getRevIndiCator();
        final Object other$revIndiCator = other.getRevIndiCator();
        Label_0509: {
            if (this$revIndiCator == null) {
                if (other$revIndiCator == null) {
                    break Label_0509;
                }
            }
            else if (this$revIndiCator.equals(other$revIndiCator)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$cardDomIntlFlag = this.getCardDomIntlFlag();
        final Object other$cardDomIntlFlag = other.getCardDomIntlFlag();
        Label_0546: {
            if (this$cardDomIntlFlag == null) {
                if (other$cardDomIntlFlag == null) {
                    break Label_0546;
                }
            }
            else if (this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$meCategoryType = this.getMeCategoryType();
        final Object other$meCategoryType = other.getMeCategoryType();
        Label_0583: {
            if (this$meCategoryType == null) {
                if (other$meCategoryType == null) {
                    break Label_0583;
                }
            }
            else if (this$meCategoryType.equals(other$meCategoryType)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0620: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0620;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$dmsSmsMode = this.getDmsSmsMode();
        final Object other$dmsSmsMode = other.getDmsSmsMode();
        Label_0657: {
            if (this$dmsSmsMode == null) {
                if (other$dmsSmsMode == null) {
                    break Label_0657;
                }
            }
            else if (this$dmsSmsMode.equals(other$dmsSmsMode)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$genStatus = this.getGenStatus();
        final Object other$genStatus = other.getGenStatus();
        Label_0694: {
            if (this$genStatus == null) {
                if (other$genStatus == null) {
                    break Label_0694;
                }
            }
            else if (this$genStatus.equals(other$genStatus)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$settlIndiCator = this.getSettlIndiCator();
        final Object other$settlIndiCator = other.getSettlIndiCator();
        Label_0731: {
            if (this$settlIndiCator == null) {
                if (other$settlIndiCator == null) {
                    break Label_0731;
                }
            }
            else if (this$settlIndiCator.equals(other$settlIndiCator)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$fullPartialIndiCator = this.getFullPartialIndiCator();
        final Object other$fullPartialIndiCator = other.getFullPartialIndiCator();
        Label_0768: {
            if (this$fullPartialIndiCator == null) {
                if (other$fullPartialIndiCator == null) {
                    break Label_0768;
                }
            }
            else if (this$fullPartialIndiCator.equals(other$fullPartialIndiCator)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0805: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0805;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_0842: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_0842;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$txnCode = this.getTxnCode();
        final Object other$txnCode = other.getTxnCode();
        Label_0879: {
            if (this$txnCode == null) {
                if (other$txnCode == null) {
                    break Label_0879;
                }
            }
            else if (this$txnCode.equals(other$txnCode)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$messageTypeId = this.getMessageTypeId();
        final Object other$messageTypeId = other.getMessageTypeId();
        Label_0916: {
            if (this$messageTypeId == null) {
                if (other$messageTypeId == null) {
                    break Label_0916;
                }
            }
            else if (this$messageTypeId.equals(other$messageTypeId)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$functionCode = this.getFunctionCode();
        final Object other$functionCode = other.getFunctionCode();
        Label_0953: {
            if (this$functionCode == null) {
                if (other$functionCode == null) {
                    break Label_0953;
                }
            }
            else if (this$functionCode.equals(other$functionCode)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$localDateTime = this.getLocalDateTime();
        final Object other$localDateTime = other.getLocalDateTime();
        Label_0990: {
            if (this$localDateTime == null) {
                if (other$localDateTime == null) {
                    break Label_0990;
                }
            }
            else if (this$localDateTime.equals(other$localDateTime)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$cardNumber = this.getCardNumber();
        final Object other$cardNumber = other.getCardNumber();
        Label_1027: {
            if (this$cardNumber == null) {
                if (other$cardNumber == null) {
                    break Label_1027;
                }
            }
            else if (this$cardNumber.equals(other$cardNumber)) {
                break Label_1027;
            }
            return false;
        }
        final Object this$acqRefData = this.getAcqRefData();
        final Object other$acqRefData = other.getAcqRefData();
        Label_1064: {
            if (this$acqRefData == null) {
                if (other$acqRefData == null) {
                    break Label_1064;
                }
            }
            else if (this$acqRefData.equals(other$acqRefData)) {
                break Label_1064;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_1101: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_1101;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_1101;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_1138: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_1138;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_1138;
            }
            return false;
        }
        final Object this$txnCurCode = this.getTxnCurCode();
        final Object other$txnCurCode = other.getTxnCurCode();
        Label_1175: {
            if (this$txnCurCode == null) {
                if (other$txnCurCode == null) {
                    break Label_1175;
                }
            }
            else if (this$txnCurCode.equals(other$txnCurCode)) {
                break Label_1175;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_1212: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_1212;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_1212;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_1249: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_1249;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_1249;
            }
            return false;
        }
        final Object this$meName = this.getMeName();
        final Object other$meName = other.getMeName();
        Label_1286: {
            if (this$meName == null) {
                if (other$meName == null) {
                    break Label_1286;
                }
            }
            else if (this$meName.equals(other$meName)) {
                break Label_1286;
            }
            return false;
        }
        final Object this$meCity = this.getMeCity();
        final Object other$meCity = other.getMeCity();
        Label_1323: {
            if (this$meCity == null) {
                if (other$meCity == null) {
                    break Label_1323;
                }
            }
            else if (this$meCity.equals(other$meCity)) {
                break Label_1323;
            }
            return false;
        }
        final Object this$meStateCode = this.getMeStateCode();
        final Object other$meStateCode = other.getMeStateCode();
        Label_1360: {
            if (this$meStateCode == null) {
                if (other$meStateCode == null) {
                    break Label_1360;
                }
            }
            else if (this$meStateCode.equals(other$meStateCode)) {
                break Label_1360;
            }
            return false;
        }
        final Object this$meCountry = this.getMeCountry();
        final Object other$meCountry = other.getMeCountry();
        Label_1397: {
            if (this$meCountry == null) {
                if (other$meCountry == null) {
                    break Label_1397;
                }
            }
            else if (this$meCountry.equals(other$meCountry)) {
                break Label_1397;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_1434: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_1434;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_1434;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        Label_1471: {
            if (this$posEntryMode == null) {
                if (other$posEntryMode == null) {
                    break Label_1471;
                }
            }
            else if (this$posEntryMode.equals(other$posEntryMode)) {
                break Label_1471;
            }
            return false;
        }
        final Object this$acqinstIdCode = this.getAcqinstIdCode();
        final Object other$acqinstIdCode = other.getAcqinstIdCode();
        Label_1508: {
            if (this$acqinstIdCode == null) {
                if (other$acqinstIdCode == null) {
                    break Label_1508;
                }
            }
            else if (this$acqinstIdCode.equals(other$acqinstIdCode)) {
                break Label_1508;
            }
            return false;
        }
        final Object this$trlType = this.getTrlType();
        final Object other$trlType = other.getTrlType();
        Label_1545: {
            if (this$trlType == null) {
                if (other$trlType == null) {
                    break Label_1545;
                }
            }
            else if (this$trlType.equals(other$trlType)) {
                break Label_1545;
            }
            return false;
        }
        final Object this$centreProcDate = this.getCentreProcDate();
        final Object other$centreProcDate = other.getCentreProcDate();
        Label_1582: {
            if (this$centreProcDate == null) {
                if (other$centreProcDate == null) {
                    break Label_1582;
                }
            }
            else if (this$centreProcDate.equals(other$centreProcDate)) {
                break Label_1582;
            }
            return false;
        }
        final Object this$fileProcDate = this.getFileProcDate();
        final Object other$fileProcDate = other.getFileProcDate();
        Label_1619: {
            if (this$fileProcDate == null) {
                if (other$fileProcDate == null) {
                    break Label_1619;
                }
            }
            else if (this$fileProcDate.equals(other$fileProcDate)) {
                break Label_1619;
            }
            return false;
        }
        final Object this$fileID = this.getFileID();
        final Object other$fileID = other.getFileID();
        Label_1656: {
            if (this$fileID == null) {
                if (other$fileID == null) {
                    break Label_1656;
                }
            }
            else if (this$fileID.equals(other$fileID)) {
                break Label_1656;
            }
            return false;
        }
        final Object this$encryptedCardNumber = this.getEncryptedCardNumber();
        final Object other$encryptedCardNumber = other.getEncryptedCardNumber();
        Label_1693: {
            if (this$encryptedCardNumber == null) {
                if (other$encryptedCardNumber == null) {
                    break Label_1693;
                }
            }
            else if (this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
                break Label_1693;
            }
            return false;
        }
        final Object this$responseCode = this.getResponseCode();
        final Object other$responseCode = other.getResponseCode();
        Label_1730: {
            if (this$responseCode == null) {
                if (other$responseCode == null) {
                    break Label_1730;
                }
            }
            else if (this$responseCode.equals(other$responseCode)) {
                break Label_1730;
            }
            return false;
        }
        final Object this$motoEcomIndicator = this.getMotoEcomIndicator();
        final Object other$motoEcomIndicator = other.getMotoEcomIndicator();
        Label_1767: {
            if (this$motoEcomIndicator == null) {
                if (other$motoEcomIndicator == null) {
                    break Label_1767;
                }
            }
            else if (this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
                break Label_1767;
            }
            return false;
        }
        final Object this$settlDate = this.getSettlDate();
        final Object other$settlDate = other.getSettlDate();
        Label_1804: {
            if (this$settlDate == null) {
                if (other$settlDate == null) {
                    break Label_1804;
                }
            }
            else if (this$settlDate.equals(other$settlDate)) {
                break Label_1804;
            }
            return false;
        }
        final Object this$posConditionCode = this.getPosConditionCode();
        final Object other$posConditionCode = other.getPosConditionCode();
        if (this$posConditionCode == null) {
            if (other$posConditionCode == null) {
                return true;
            }
        }
        else if (this$posConditionCode.equals(other$posConditionCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof JaywanAcqTxnWorkEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $institutionCode = this.getInstitutionCode();
        result = result * 59 + (($institutionCode == null) ? 43 : $institutionCode.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + (($prjSerNumber == null) ? 43 : $prjSerNumber.hashCode());
        final Object $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + (($txnRefNumber == null) ? 43 : $txnRefNumber.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $settledAmount = this.getSettledAmount();
        result = result * 59 + (($settledAmount == null) ? 43 : $settledAmount.hashCode());
        final Object $billAmount = this.getBillAmount();
        result = result * 59 + (($billAmount == null) ? 43 : $billAmount.hashCode());
        final Object $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + (($surchargeAmount == null) ? 43 : $surchargeAmount.hashCode());
        final Object $convRate = this.getConvRate();
        result = result * 59 + (($convRate == null) ? 43 : $convRate.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $revIndiCator = this.getRevIndiCator();
        result = result * 59 + (($revIndiCator == null) ? 43 : $revIndiCator.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $meCategoryType = this.getMeCategoryType();
        result = result * 59 + (($meCategoryType == null) ? 43 : $meCategoryType.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $dmsSmsMode = this.getDmsSmsMode();
        result = result * 59 + (($dmsSmsMode == null) ? 43 : $dmsSmsMode.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $settlIndiCator = this.getSettlIndiCator();
        result = result * 59 + (($settlIndiCator == null) ? 43 : $settlIndiCator.hashCode());
        final Object $fullPartialIndiCator = this.getFullPartialIndiCator();
        result = result * 59 + (($fullPartialIndiCator == null) ? 43 : $fullPartialIndiCator.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $txnCode = this.getTxnCode();
        result = result * 59 + (($txnCode == null) ? 43 : $txnCode.hashCode());
        final Object $messageTypeId = this.getMessageTypeId();
        result = result * 59 + (($messageTypeId == null) ? 43 : $messageTypeId.hashCode());
        final Object $functionCode = this.getFunctionCode();
        result = result * 59 + (($functionCode == null) ? 43 : $functionCode.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $acqRefData = this.getAcqRefData();
        result = result * 59 + (($acqRefData == null) ? 43 : $acqRefData.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $meStateCode = this.getMeStateCode();
        result = result * 59 + (($meStateCode == null) ? 43 : $meStateCode.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + (($acqinstIdCode == null) ? 43 : $acqinstIdCode.hashCode());
        final Object $trlType = this.getTrlType();
        result = result * 59 + (($trlType == null) ? 43 : $trlType.hashCode());
        final Object $centreProcDate = this.getCentreProcDate();
        result = result * 59 + (($centreProcDate == null) ? 43 : $centreProcDate.hashCode());
        final Object $fileProcDate = this.getFileProcDate();
        result = result * 59 + (($fileProcDate == null) ? 43 : $fileProcDate.hashCode());
        final Object $fileID = this.getFileID();
        result = result * 59 + (($fileID == null) ? 43 : $fileID.hashCode());
        final Object $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + (($encryptedCardNumber == null) ? 43 : $encryptedCardNumber.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $settlDate = this.getSettlDate();
        result = result * 59 + (($settlDate == null) ? 43 : $settlDate.hashCode());
        final Object $posConditionCode = this.getPosConditionCode();
        result = result * 59 + (($posConditionCode == null) ? 43 : $posConditionCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "JaywanAcqTxnWorkEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", txnRefNumber=" + this.getTxnRefNumber() + ", txnType=" + this.getTxnType() + ", txnCode=" + this.getTxnCode() + ", messageTypeId=" + this.getMessageTypeId() + ", functionCode=" + this.getFunctionCode() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", cardNumber=" + this.getCardNumber() + ", acqRefData=" + this.getAcqRefData() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", txnAmount=" + this.getTxnAmount() + ", settledAmount=" + this.getSettledAmount() + ", billAmount=" + this.getBillAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", convRate=" + this.getConvRate() + ", txnCurCode=" + this.getTxnCurCode() + ", cashBackAmount=" + this.getCashBackAmount() + ", rrn=" + this.getRrn() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meStateCode=" + this.getMeStateCode() + ", meCountry=" + this.getMeCountry() + ", mcc=" + this.getMcc() + ", posEntryMode=" + this.getPosEntryMode() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", revIndiCator=" + this.getRevIndiCator() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", trlType=" + this.getTrlType() + ", meCategoryType=" + this.getMeCategoryType() + ", cardType=" + this.getCardType() + ", dmsSmsMode=" + this.getDmsSmsMode() + ", centreProcDate=" + String.valueOf(this.getCentreProcDate()) + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", fileID=" + this.getFileID() + ", genStatus=" + this.getGenStatus() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", responseCode=" + this.getResponseCode() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", settlDate=" + String.valueOf(this.getSettlDate()) + ", settlIndiCator=" + this.getSettlIndiCator() + ", posConditionCode=" + this.getPosConditionCode() + ", fullPartialIndiCator=" + this.getFullPartialIndiCator();
    }
    
    public JaywanAcqTxnWorkEntity() {
    }
    
    public JaywanAcqTxnWorkEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final Integer institutionCode, final Integer intCode, final Integer prjSerNumber, final Integer txnRefNumber, final String txnType, final String txnCode, final String messageTypeId, final String functionCode, final LocalDateTime localDateTime, final String cardNumber, final String acqRefData, final String approvalCode, final String terminalId, final Double txnAmount, final Double settledAmount, final Double billAmount, final Double surchargeAmount, final Double convRate, final String txnCurCode, final Double cashBackAmount, final String rrn, final String merchantId, final String meName, final String meCity, final String meStateCode, final String meCountry, final String mcc, final String posEntryMode, final String acqinstIdCode, final Character revIndiCator, final Character cardDomIntlFlag, final String trlType, final Character meCategoryType, final Character cardType, final Character dmsSmsMode, final LocalDate centreProcDate, final LocalDate fileProcDate, final String fileID, final Integer genStatus, final String encryptedCardNumber, final String responseCode, final String motoEcomIndicator, final LocalDate settlDate, final Character settlIndiCator, final String posConditionCode, final Character fullPartialIndiCator) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.intCode = intCode;
        this.prjSerNumber = prjSerNumber;
        this.txnRefNumber = txnRefNumber;
        this.txnType = txnType;
        this.txnCode = txnCode;
        this.messageTypeId = messageTypeId;
        this.functionCode = functionCode;
        this.localDateTime = localDateTime;
        this.cardNumber = cardNumber;
        this.acqRefData = acqRefData;
        this.approvalCode = approvalCode;
        this.terminalId = terminalId;
        this.txnAmount = txnAmount;
        this.settledAmount = settledAmount;
        this.billAmount = billAmount;
        this.surchargeAmount = surchargeAmount;
        this.convRate = convRate;
        this.txnCurCode = txnCurCode;
        this.cashBackAmount = cashBackAmount;
        this.rrn = rrn;
        this.merchantId = merchantId;
        this.meName = meName;
        this.meCity = meCity;
        this.meStateCode = meStateCode;
        this.meCountry = meCountry;
        this.mcc = mcc;
        this.posEntryMode = posEntryMode;
        this.acqinstIdCode = acqinstIdCode;
        this.revIndiCator = revIndiCator;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.trlType = trlType;
        this.meCategoryType = meCategoryType;
        this.cardType = cardType;
        this.dmsSmsMode = dmsSmsMode;
        this.centreProcDate = centreProcDate;
        this.fileProcDate = fileProcDate;
        this.fileID = fileID;
        this.genStatus = genStatus;
        this.encryptedCardNumber = encryptedCardNumber;
        this.responseCode = responseCode;
        this.motoEcomIndicator = motoEcomIndicator;
        this.settlDate = settlDate;
        this.settlIndiCator = settlIndiCator;
        this.posConditionCode = posConditionCode;
        this.fullPartialIndiCator = fullPartialIndiCator;
    }
    
    public static class JaywanAcqTxnWorkEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private Integer institutionCode;
        private Integer intCode;
        private Integer prjSerNumber;
        private Integer txnRefNumber;
        private String txnType;
        private String txnCode;
        private String messageTypeId;
        private String functionCode;
        private LocalDateTime localDateTime;
        private String cardNumber;
        private String acqRefData;
        private String approvalCode;
        private String terminalId;
        private Double txnAmount;
        private Double settledAmount;
        private Double billAmount;
        private Double surchargeAmount;
        private Double convRate;
        private String txnCurCode;
        private Double cashBackAmount;
        private String rrn;
        private String merchantId;
        private String meName;
        private String meCity;
        private String meStateCode;
        private String meCountry;
        private String mcc;
        private String posEntryMode;
        private String acqinstIdCode;
        private Character revIndiCator;
        private Character cardDomIntlFlag;
        private String trlType;
        private Character meCategoryType;
        private Character cardType;
        private Character dmsSmsMode;
        private LocalDate centreProcDate;
        private LocalDate fileProcDate;
        private String fileID;
        private Integer genStatus;
        private String encryptedCardNumber;
        private String responseCode;
        private String motoEcomIndicator;
        private LocalDate settlDate;
        private Character settlIndiCator;
        private String posConditionCode;
        private Character fullPartialIndiCator;
        
        JaywanAcqTxnWorkEntityBuilder() {
        }
        
        public JaywanAcqTxnWorkEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder institutionCode(final Integer institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder txnRefNumber(final Integer txnRefNumber) {
            this.txnRefNumber = txnRefNumber;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder txnCode(final String txnCode) {
            this.txnCode = txnCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder messageTypeId(final String messageTypeId) {
            this.messageTypeId = messageTypeId;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder functionCode(final String functionCode) {
            this.functionCode = functionCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder acqRefData(final String acqRefData) {
            this.acqRefData = acqRefData;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder settledAmount(final Double settledAmount) {
            this.settledAmount = settledAmount;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder billAmount(final Double billAmount) {
            this.billAmount = billAmount;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder surchargeAmount(final Double surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder convRate(final Double convRate) {
            this.convRate = convRate;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder meStateCode(final String meStateCode) {
            this.meStateCode = meStateCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder acqinstIdCode(final String acqinstIdCode) {
            this.acqinstIdCode = acqinstIdCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder revIndiCator(final Character revIndiCator) {
            this.revIndiCator = revIndiCator;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder trlType(final String trlType) {
            this.trlType = trlType;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder meCategoryType(final Character meCategoryType) {
            this.meCategoryType = meCategoryType;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder dmsSmsMode(final Character dmsSmsMode) {
            this.dmsSmsMode = dmsSmsMode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder centreProcDate(final LocalDate centreProcDate) {
            this.centreProcDate = centreProcDate;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder fileProcDate(final LocalDate fileProcDate) {
            this.fileProcDate = fileProcDate;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder fileID(final String fileID) {
            this.fileID = fileID;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder genStatus(final Integer genStatus) {
            this.genStatus = genStatus;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder encryptedCardNumber(final String encryptedCardNumber) {
            this.encryptedCardNumber = encryptedCardNumber;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder responseCode(final String responseCode) {
            this.responseCode = responseCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder motoEcomIndicator(final String motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder settlDate(final LocalDate settlDate) {
            this.settlDate = settlDate;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder settlIndiCator(final Character settlIndiCator) {
            this.settlIndiCator = settlIndiCator;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder posConditionCode(final String posConditionCode) {
            this.posConditionCode = posConditionCode;
            return this;
        }
        
        public JaywanAcqTxnWorkEntityBuilder fullPartialIndiCator(final Character fullPartialIndiCator) {
            this.fullPartialIndiCator = fullPartialIndiCator;
            return this;
        }
        
        public JaywanAcqTxnWorkEntity build() {
            return new JaywanAcqTxnWorkEntity(this.serialNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.intCode, this.prjSerNumber, this.txnRefNumber, this.txnType, this.txnCode, this.messageTypeId, this.functionCode, this.localDateTime, this.cardNumber, this.acqRefData, this.approvalCode, this.terminalId, this.txnAmount, this.settledAmount, this.billAmount, this.surchargeAmount, this.convRate, this.txnCurCode, this.cashBackAmount, this.rrn, this.merchantId, this.meName, this.meCity, this.meStateCode, this.meCountry, this.mcc, this.posEntryMode, this.acqinstIdCode, this.revIndiCator, this.cardDomIntlFlag, this.trlType, this.meCategoryType, this.cardType, this.dmsSmsMode, this.centreProcDate, this.fileProcDate, this.fileID, this.genStatus, this.encryptedCardNumber, this.responseCode, this.motoEcomIndicator, this.settlDate, this.settlIndiCator, this.posConditionCode, this.fullPartialIndiCator);
        }
        
        @Override
        public String toString() {
            return "JaywanAcqTxnWorkEntity.JaywanAcqTxnWorkEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", intCode=" + this.intCode + ", prjSerNumber=" + this.prjSerNumber + ", txnRefNumber=" + this.txnRefNumber + ", txnType=" + this.txnType + ", txnCode=" + this.txnCode + ", messageTypeId=" + this.messageTypeId + ", functionCode=" + this.functionCode + ", localDateTime=" + String.valueOf(this.localDateTime) + ", cardNumber=" + this.cardNumber + ", acqRefData=" + this.acqRefData + ", approvalCode=" + this.approvalCode + ", terminalId=" + this.terminalId + ", txnAmount=" + this.txnAmount + ", settledAmount=" + this.settledAmount + ", billAmount=" + this.billAmount + ", surchargeAmount=" + this.surchargeAmount + ", convRate=" + this.convRate + ", txnCurCode=" + this.txnCurCode + ", cashBackAmount=" + this.cashBackAmount + ", rrn=" + this.rrn + ", merchantId=" + this.merchantId + ", meName=" + this.meName + ", meCity=" + this.meCity + ", meStateCode=" + this.meStateCode + ", meCountry=" + this.meCountry + ", mcc=" + this.mcc + ", posEntryMode=" + this.posEntryMode + ", acqinstIdCode=" + this.acqinstIdCode + ", revIndiCator=" + this.revIndiCator + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", trlType=" + this.trlType + ", meCategoryType=" + this.meCategoryType + ", cardType=" + this.cardType + ", dmsSmsMode=" + this.dmsSmsMode + ", centreProcDate=" + String.valueOf(this.centreProcDate) + ", fileProcDate=" + String.valueOf(this.fileProcDate) + ", fileID=" + this.fileID + ", genStatus=" + this.genStatus + ", encryptedCardNumber=" + this.encryptedCardNumber + ", responseCode=" + this.responseCode + ", motoEcomIndicator=" + this.motoEcomIndicator + ", settlDate=" + String.valueOf(this.settlDate) + ", settlIndiCator=" + this.settlIndiCator + ", posConditionCode=" + this.posConditionCode + ", fullPartialIndiCator=" + this.fullPartialIndiCator;
        }
    }
}
