/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McGCOTxnWorkEntity
 *  com.empay.staging.entities.McGCOTxnWorkEntity$McGCOTxnWorkEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import com.empay.staging.entities.McGCOTxnWorkEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MC_GCO_WORK")
public class McGCOTxnWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MGW_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MGW_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MGW_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MGW_INS_CODE")
    private int insCode;
    @Column(name="MGW_INT_CODE")
    private int intCode;
    @Column(name="MGW_GEN_STATUS")
    private Integer generalStatus;
    @Column(name="MGW_NETWORK")
    private String network;
    @Column(name="MGW_TXN_REF_NUMBER")
    private Integer txnRefSerNumber;
    @Column(name="MGW_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name="MGW_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="MGW_PROC_CODE")
    private String procCode;
    @Column(name="MGW_APPR_CODE")
    private String approvalCode;
    @Column(name="MGW_SERVICE_CODE")
    private String serviceCode;
    @Column(name="MGW_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name="MGW_MTI")
    private String mti;
    @Column(name="MGW_TXN_TYPE")
    private String txnType;
    @Column(name="MGW_TXN_AMOUNT", nullable=false)
    private Double txnAmount;
    @Column(name="MGW_SCHG_AMOUNT", nullable=false)
    private Double surchargeAmount;
    @Column(name="MGW_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="MGW_CH_PRESENT")
    private String chPresent;
    @Column(name="MGW_CARD_PRESENT")
    private String cardPresent;
    @Column(name="MGW_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name="MGW_POS_DATA_MODE")
    private String posDataMode;
    @Column(name="MGW_MCC")
    private String mcc;
    @Column(name="MGW_ACQ_INST_ID_CODE")
    private String acqInstIdCode;
    @Column(name="MGW_RET_REF_NUMBER")
    private String rrn;
    @Column(name="MGW_ACQ_REF_DATA")
    private String arn;
    @Column(name="MGW_TERMINAL_ID")
    private String terminalId;
    @Column(name="MGW_MERCHANT_ID")
    private String merchantId;
    @Column(name="MGW_ME_NAME")
    private String meName;
    @Column(name="MGW_ME_ADDRESS")
    private String meAddress;
    @Column(name="MGW_ME_CITY")
    private String meCity;
    @Column(name="MGW_ME_ZIP_CODE")
    private String meZipCode;
    @Column(name="MGW_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="MGW_ME_COUNTRY")
    private String meCountry;
    @Column(name="MGW_FILE_ID")
    private String fileId;
    @Column(name="MGW_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name="MGW_SETL_INDICATOR")
    private Character settlementIndicator;
    @Column(name="MGW_TXN_LIFE_CYCL_ID")
    private String txnlifeCycleId;
    @Column(name="MGW_FUNC_CODE")
    private String functionCode;
    @Column(name="MGW_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name="MGW_CARD_TYPE")
    private Character cardType;
    @Column(name="MGW_CARD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name="MGW_MPOS_ACC_DEV_TYPE")
    private Character mposAccDevType;
    @Column(name="MGW_ACC_URL_ADDRESS")
    private String accepterUrlAddress;
    @Column(name="MGW_TXN_CURR_EXP")
    private int txnCurrencyExponent;

    public static McGCOTxnWorkEntityBuilder builder() {
        return new McGCOTxnWorkEntityBuilder();
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

    public int getInsCode() {
        return this.insCode;
    }

    public int getIntCode() {
        return this.intCode;
    }

    public Integer getGeneralStatus() {
        return this.generalStatus;
    }

    public String getNetwork() {
        return this.network;
    }

    public Integer getTxnRefSerNumber() {
        return this.txnRefSerNumber;
    }

    public Integer getPrjSerNumber() {
        return this.prjSerNumber;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public LocalDate getFileProcDate() {
        return this.fileProcDate;
    }

    public String getMti() {
        return this.mti;
    }

    public String getTxnType() {
        return this.txnType;
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

    public String getChPresent() {
        return this.chPresent;
    }

    public String getCardPresent() {
        return this.cardPresent;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
    }

    public String getPosDataMode() {
        return this.posDataMode;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getAcqInstIdCode() {
        return this.acqInstIdCode;
    }

    public String getRrn() {
        return this.rrn;
    }

    public String getArn() {
        return this.arn;
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

    public String getMeAddress() {
        return this.meAddress;
    }

    public String getMeCity() {
        return this.meCity;
    }

    public String getMeZipCode() {
        return this.meZipCode;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
    }

    public String getMeCountry() {
        return this.meCountry;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }

    public Character getSettlementIndicator() {
        return this.settlementIndicator;
    }

    public String getTxnlifeCycleId() {
        return this.txnlifeCycleId;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getMsgReasonCode() {
        return this.msgReasonCode;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }

    public Character getMposAccDevType() {
        return this.mposAccDevType;
    }

    public String getAccepterUrlAddress() {
        return this.accepterUrlAddress;
    }

    public int getTxnCurrencyExponent() {
        return this.txnCurrencyExponent;
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

    public void setInsCode(int insCode) {
        this.insCode = insCode;
    }

    public void setIntCode(int intCode) {
        this.intCode = intCode;
    }

    public void setGeneralStatus(Integer generalStatus) {
        this.generalStatus = generalStatus;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setTxnRefSerNumber(Integer txnRefSerNumber) {
        this.txnRefSerNumber = txnRefSerNumber;
    }

    public void setPrjSerNumber(Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setFileProcDate(LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
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

    public void setChPresent(String chPresent) {
        this.chPresent = chPresent;
    }

    public void setCardPresent(String cardPresent) {
        this.cardPresent = cardPresent;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public void setPosDataMode(String posDataMode) {
        this.posDataMode = posDataMode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setAcqInstIdCode(String acqInstIdCode) {
        this.acqInstIdCode = acqInstIdCode;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setArn(String arn) {
        this.arn = arn;
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

    public void setMeAddress(String meAddress) {
        this.meAddress = meAddress;
    }

    public void setMeCity(String meCity) {
        this.meCity = meCity;
    }

    public void setMeZipCode(String meZipCode) {
        this.meZipCode = meZipCode;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setMeCountry(String meCountry) {
        this.meCountry = meCountry;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setSettlementIndicator(Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }

    public void setTxnlifeCycleId(String txnlifeCycleId) {
        this.txnlifeCycleId = txnlifeCycleId;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setMsgReasonCode(String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setCardDomIntlFlag(Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setMposAccDevType(Character mposAccDevType) {
        this.mposAccDevType = mposAccDevType;
    }

    public void setAccepterUrlAddress(String accepterUrlAddress) {
        this.accepterUrlAddress = accepterUrlAddress;
    }

    public void setTxnCurrencyExponent(int txnCurrencyExponent) {
        this.txnCurrencyExponent = txnCurrencyExponent;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McGCOTxnWorkEntity)) {
            return false;
        }
        McGCOTxnWorkEntity other = (McGCOTxnWorkEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getInsCode() != other.getInsCode()) {
            return false;
        }
        if (this.getIntCode() != other.getIntCode()) {
            return false;
        }
        if (this.getTxnCurrencyExponent() != other.getTxnCurrencyExponent()) {
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
        Integer this$generalStatus = this.getGeneralStatus();
        Integer other$generalStatus = other.getGeneralStatus();
        if (this$generalStatus == null ? other$generalStatus != null : !((Object)this$generalStatus).equals(other$generalStatus)) {
            return false;
        }
        Integer this$txnRefSerNumber = this.getTxnRefSerNumber();
        Integer other$txnRefSerNumber = other.getTxnRefSerNumber();
        if (this$txnRefSerNumber == null ? other$txnRefSerNumber != null : !((Object)this$txnRefSerNumber).equals(other$txnRefSerNumber)) {
            return false;
        }
        Integer this$prjSerNumber = this.getPrjSerNumber();
        Integer other$prjSerNumber = other.getPrjSerNumber();
        if (this$prjSerNumber == null ? other$prjSerNumber != null : !((Object)this$prjSerNumber).equals(other$prjSerNumber)) {
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
        Character this$settlementIndicator = this.getSettlementIndicator();
        Character other$settlementIndicator = other.getSettlementIndicator();
        if (this$settlementIndicator == null ? other$settlementIndicator != null : !((Object)this$settlementIndicator).equals(other$settlementIndicator)) {
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
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$serviceCode = this.getServiceCode();
        String other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null ? other$serviceCode != null : !this$serviceCode.equals(other$serviceCode)) {
            return false;
        }
        LocalDate this$fileProcDate = this.getFileProcDate();
        LocalDate other$fileProcDate = other.getFileProcDate();
        if (this$fileProcDate == null ? other$fileProcDate != null : !((Object)this$fileProcDate).equals(other$fileProcDate)) {
            return false;
        }
        String this$mti = this.getMti();
        String other$mti = other.getMti();
        if (this$mti == null ? other$mti != null : !this$mti.equals(other$mti)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$chPresent = this.getChPresent();
        String other$chPresent = other.getChPresent();
        if (this$chPresent == null ? other$chPresent != null : !this$chPresent.equals(other$chPresent)) {
            return false;
        }
        String this$cardPresent = this.getCardPresent();
        String other$cardPresent = other.getCardPresent();
        if (this$cardPresent == null ? other$cardPresent != null : !this$cardPresent.equals(other$cardPresent)) {
            return false;
        }
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode)) {
            return false;
        }
        String this$posDataMode = this.getPosDataMode();
        String other$posDataMode = other.getPosDataMode();
        if (this$posDataMode == null ? other$posDataMode != null : !this$posDataMode.equals(other$posDataMode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$acqInstIdCode = this.getAcqInstIdCode();
        String other$acqInstIdCode = other.getAcqInstIdCode();
        if (this$acqInstIdCode == null ? other$acqInstIdCode != null : !this$acqInstIdCode.equals(other$acqInstIdCode)) {
            return false;
        }
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        String this$arn = this.getArn();
        String other$arn = other.getArn();
        if (this$arn == null ? other$arn != null : !this$arn.equals(other$arn)) {
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
        String this$meAddress = this.getMeAddress();
        String other$meAddress = other.getMeAddress();
        if (this$meAddress == null ? other$meAddress != null : !this$meAddress.equals(other$meAddress)) {
            return false;
        }
        String this$meCity = this.getMeCity();
        String other$meCity = other.getMeCity();
        if (this$meCity == null ? other$meCity != null : !this$meCity.equals(other$meCity)) {
            return false;
        }
        String this$meZipCode = this.getMeZipCode();
        String other$meZipCode = other.getMeZipCode();
        if (this$meZipCode == null ? other$meZipCode != null : !this$meZipCode.equals(other$meZipCode)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
            return false;
        }
        String this$meCountry = this.getMeCountry();
        String other$meCountry = other.getMeCountry();
        if (this$meCountry == null ? other$meCountry != null : !this$meCountry.equals(other$meCountry)) {
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
        String this$txnlifeCycleId = this.getTxnlifeCycleId();
        String other$txnlifeCycleId = other.getTxnlifeCycleId();
        if (this$txnlifeCycleId == null ? other$txnlifeCycleId != null : !this$txnlifeCycleId.equals(other$txnlifeCycleId)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        String this$msgReasonCode = this.getMsgReasonCode();
        String other$msgReasonCode = other.getMsgReasonCode();
        if (this$msgReasonCode == null ? other$msgReasonCode != null : !this$msgReasonCode.equals(other$msgReasonCode)) {
            return false;
        }
        String this$accepterUrlAddress = this.getAccepterUrlAddress();
        String other$accepterUrlAddress = other.getAccepterUrlAddress();
        return !(this$accepterUrlAddress == null ? other$accepterUrlAddress != null : !this$accepterUrlAddress.equals(other$accepterUrlAddress));
    }

    protected boolean canEqual(Object other) {
        return other instanceof McGCOTxnWorkEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInsCode();
        result = result * 59 + this.getIntCode();
        result = result * 59 + this.getTxnCurrencyExponent();
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $generalStatus = this.getGeneralStatus();
        result = result * 59 + ($generalStatus == null ? 43 : ((Object)$generalStatus).hashCode());
        Integer $txnRefSerNumber = this.getTxnRefSerNumber();
        result = result * 59 + ($txnRefSerNumber == null ? 43 : ((Object)$txnRefSerNumber).hashCode());
        Integer $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + ($prjSerNumber == null ? 43 : ((Object)$prjSerNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + ($surchargeAmount == null ? 43 : ((Object)$surchargeAmount).hashCode());
        Character $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + ($settlementIndicator == null ? 43 : ((Object)$settlementIndicator).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Character $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : ((Object)$cardDomIntlFlag).hashCode());
        Character $mposAccDevType = this.getMposAccDevType();
        result = result * 59 + ($mposAccDevType == null ? 43 : ((Object)$mposAccDevType).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        LocalDate $fileProcDate = this.getFileProcDate();
        result = result * 59 + ($fileProcDate == null ? 43 : ((Object)$fileProcDate).hashCode());
        String $mti = this.getMti();
        result = result * 59 + ($mti == null ? 43 : $mti.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $chPresent = this.getChPresent();
        result = result * 59 + ($chPresent == null ? 43 : $chPresent.hashCode());
        String $cardPresent = this.getCardPresent();
        result = result * 59 + ($cardPresent == null ? 43 : $cardPresent.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        String $posDataMode = this.getPosDataMode();
        result = result * 59 + ($posDataMode == null ? 43 : $posDataMode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $acqInstIdCode = this.getAcqInstIdCode();
        result = result * 59 + ($acqInstIdCode == null ? 43 : $acqInstIdCode.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $arn = this.getArn();
        result = result * 59 + ($arn == null ? 43 : $arn.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meAddress = this.getMeAddress();
        result = result * 59 + ($meAddress == null ? 43 : $meAddress.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meZipCode = this.getMeZipCode();
        result = result * 59 + ($meZipCode == null ? 43 : $meZipCode.hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $txnlifeCycleId = this.getTxnlifeCycleId();
        result = result * 59 + ($txnlifeCycleId == null ? 43 : $txnlifeCycleId.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        String $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + ($msgReasonCode == null ? 43 : $msgReasonCode.hashCode());
        String $accepterUrlAddress = this.getAccepterUrlAddress();
        result = result * 59 + ($accepterUrlAddress == null ? 43 : $accepterUrlAddress.hashCode());
        return result;
    }

    public String toString() {
        return "McGCOTxnWorkEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", generalStatus=" + this.getGeneralStatus() + ", network=" + this.getNetwork() + ", txnRefSerNumber=" + this.getTxnRefSerNumber() + ", prjSerNumber=" + this.getPrjSerNumber() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", procCode=" + this.getProcCode() + ", approvalCode=" + this.getApprovalCode() + ", serviceCode=" + this.getServiceCode() + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", mti=" + this.getMti() + ", txnType=" + this.getTxnType() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent() + ", posEntryMode=" + this.getPosEntryMode() + ", posDataMode=" + this.getPosDataMode() + ", mcc=" + this.getMcc() + ", acqInstIdCode=" + this.getAcqInstIdCode() + ", rrn=" + this.getRrn() + ", arn=" + this.getArn() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meAddress=" + this.getMeAddress() + ", meCity=" + this.getMeCity() + ", meZipCode=" + this.getMeZipCode() + ", txnCurCode=" + this.getTxnCurCode() + ", meCountry=" + this.getMeCountry() + ", fileId=" + this.getFileId() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", settlementIndicator=" + this.getSettlementIndicator() + ", txnlifeCycleId=" + this.getTxnlifeCycleId() + ", functionCode=" + this.getFunctionCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", mposAccDevType=" + this.getMposAccDevType() + ", accepterUrlAddress=" + this.getAccepterUrlAddress() + ", txnCurrencyExponent=" + this.getTxnCurrencyExponent() + ")";
    }

    public McGCOTxnWorkEntity() {
    }

    public McGCOTxnWorkEntity(Integer serialNumber, LocalDateTime lastUpdated, Integer updatedUser, int insCode, int intCode, Integer generalStatus, String network, Integer txnRefSerNumber, Integer prjSerNumber, LocalDate businessDate, String procCode, String approvalCode, String serviceCode, LocalDate fileProcDate, String mti, String txnType, Double txnAmount, Double surchargeAmount, LocalDateTime localDateTime, String chPresent, String cardPresent, String posEntryMode, String posDataMode, String mcc, String acqInstIdCode, String rrn, String arn, String terminalId, String merchantId, String meName, String meAddress, String meCity, String meZipCode, String txnCurCode, String meCountry, String fileId, String encryptedCardNumber, Character settlementIndicator, String txnlifeCycleId, String functionCode, String msgReasonCode, Character cardType, Character cardDomIntlFlag, Character mposAccDevType, String accepterUrlAddress, int txnCurrencyExponent) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.insCode = insCode;
        this.intCode = intCode;
        this.generalStatus = generalStatus;
        this.network = network;
        this.txnRefSerNumber = txnRefSerNumber;
        this.prjSerNumber = prjSerNumber;
        this.businessDate = businessDate;
        this.procCode = procCode;
        this.approvalCode = approvalCode;
        this.serviceCode = serviceCode;
        this.fileProcDate = fileProcDate;
        this.mti = mti;
        this.txnType = txnType;
        this.txnAmount = txnAmount;
        this.surchargeAmount = surchargeAmount;
        this.localDateTime = localDateTime;
        this.chPresent = chPresent;
        this.cardPresent = cardPresent;
        this.posEntryMode = posEntryMode;
        this.posDataMode = posDataMode;
        this.mcc = mcc;
        this.acqInstIdCode = acqInstIdCode;
        this.rrn = rrn;
        this.arn = arn;
        this.terminalId = terminalId;
        this.merchantId = merchantId;
        this.meName = meName;
        this.meAddress = meAddress;
        this.meCity = meCity;
        this.meZipCode = meZipCode;
        this.txnCurCode = txnCurCode;
        this.meCountry = meCountry;
        this.fileId = fileId;
        this.encryptedCardNumber = encryptedCardNumber;
        this.settlementIndicator = settlementIndicator;
        this.txnlifeCycleId = txnlifeCycleId;
        this.functionCode = functionCode;
        this.msgReasonCode = msgReasonCode;
        this.cardType = cardType;
        this.cardDomIntlFlag = cardDomIntlFlag;
        this.mposAccDevType = mposAccDevType;
        this.accepterUrlAddress = accepterUrlAddress;
        this.txnCurrencyExponent = txnCurrencyExponent;
    }
}

