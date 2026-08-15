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
@Table(name = "MC_GCO_WORK")
public class McGCOTxnWorkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MGW_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "MGW_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "MGW_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "MGW_INS_CODE")
    private int insCode;
    @Column(name = "MGW_INT_CODE")
    private int intCode;
    @Column(name = "MGW_GEN_STATUS")
    private Integer generalStatus;
    @Column(name = "MGW_NETWORK")
    private String network;
    @Column(name = "MGW_TXN_REF_NUMBER")
    private Integer txnRefSerNumber;
    @Column(name = "MGW_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "MGW_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name = "MGW_PROC_CODE")
    private String procCode;
    @Column(name = "MGW_APPR_CODE")
    private String approvalCode;
    @Column(name = "MGW_SERVICE_CODE")
    private String serviceCode;
    @Column(name = "MGW_OUT_FILE_DATE")
    private LocalDate fileProcDate;
    @Column(name = "MGW_MTI")
    private String mti;
    @Column(name = "MGW_TXN_TYPE")
    private String txnType;
    @Column(name = "MGW_TXN_AMOUNT", nullable = false)
    private Double txnAmount;
    @Column(name = "MGW_SCHG_AMOUNT", nullable = false)
    private Double surchargeAmount;
    @Column(name = "MGW_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "MGW_CH_PRESENT")
    private String chPresent;
    @Column(name = "MGW_CARD_PRESENT")
    private String cardPresent;
    @Column(name = "MGW_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name = "MGW_POS_DATA_MODE")
    private String posDataMode;
    @Column(name = "MGW_MCC")
    private String mcc;
    @Column(name = "MGW_ACQ_INST_ID_CODE")
    private String acqInstIdCode;
    @Column(name = "MGW_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "MGW_ACQ_REF_DATA")
    private String arn;
    @Column(name = "MGW_TERMINAL_ID")
    private String terminalId;
    @Column(name = "MGW_MERCHANT_ID")
    private String merchantId;
    @Column(name = "MGW_ME_NAME")
    private String meName;
    @Column(name = "MGW_ME_ADDRESS")
    private String meAddress;
    @Column(name = "MGW_ME_CITY")
    private String meCity;
    @Column(name = "MGW_ME_ZIP_CODE")
    private String meZipCode;
    @Column(name = "MGW_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "MGW_ME_COUNTRY")
    private String meCountry;
    @Column(name = "MGW_FILE_ID")
    private String fileId;
    @Column(name = "MGW_ENC_CARD_NUMBER")
    private String encryptedCardNumber;
    @Column(name = "MGW_SETL_INDICATOR")
    private Character settlementIndicator;
    @Column(name = "MGW_TXN_LIFE_CYCL_ID")
    private String txnlifeCycleId;
    @Column(name = "MGW_FUNC_CODE")
    private String functionCode;
    @Column(name = "MGW_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name = "MGW_CARD_TYPE")
    private Character cardType;
    @Column(name = "MGW_CARD_DOM_INTL_FLAG")
    private Character cardDomIntlFlag;
    @Column(name = "MGW_MPOS_ACC_DEV_TYPE")
    private Character mposAccDevType;
    @Column(name = "MGW_CUSTOMER_SERVICE_PHONE_NO")
    private String customerServicePhNum;
    @Column(name = "MGW_ACC_URL_ADDRESS")
    private String accepterUrlAddress;
    @Column(name = "MGW_TXN_CURR_EXP")
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
    
    public String getCustomerServicePhNum() {
        return this.customerServicePhNum;
    }
    
    public String getAccepterUrlAddress() {
        return this.accepterUrlAddress;
    }
    
    public int getTxnCurrencyExponent() {
        return this.txnCurrencyExponent;
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
    
    public void setInsCode(final int insCode) {
        this.insCode = insCode;
    }
    
    public void setIntCode(final int intCode) {
        this.intCode = intCode;
    }
    
    public void setGeneralStatus(final Integer generalStatus) {
        this.generalStatus = generalStatus;
    }
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setTxnRefSerNumber(final Integer txnRefSerNumber) {
        this.txnRefSerNumber = txnRefSerNumber;
    }
    
    public void setPrjSerNumber(final Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }
    
    public void setBusinessDate(final LocalDate businessDate) {
        this.businessDate = businessDate;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setFileProcDate(final LocalDate fileProcDate) {
        this.fileProcDate = fileProcDate;
    }
    
    public void setMti(final String mti) {
        this.mti = mti;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
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
    
    public void setChPresent(final String chPresent) {
        this.chPresent = chPresent;
    }
    
    public void setCardPresent(final String cardPresent) {
        this.cardPresent = cardPresent;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    public void setPosDataMode(final String posDataMode) {
        this.posDataMode = posDataMode;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setAcqInstIdCode(final String acqInstIdCode) {
        this.acqInstIdCode = acqInstIdCode;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setArn(final String arn) {
        this.arn = arn;
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
    
    public void setMeAddress(final String meAddress) {
        this.meAddress = meAddress;
    }
    
    public void setMeCity(final String meCity) {
        this.meCity = meCity;
    }
    
    public void setMeZipCode(final String meZipCode) {
        this.meZipCode = meZipCode;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }
    
    public void setMeCountry(final String meCountry) {
        this.meCountry = meCountry;
    }
    
    public void setFileId(final String fileId) {
        this.fileId = fileId;
    }
    
    public void setEncryptedCardNumber(final String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }
    
    public void setSettlementIndicator(final Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }
    
    public void setTxnlifeCycleId(final String txnlifeCycleId) {
        this.txnlifeCycleId = txnlifeCycleId;
    }
    
    public void setFunctionCode(final String functionCode) {
        this.functionCode = functionCode;
    }
    
    public void setMsgReasonCode(final String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setMposAccDevType(final Character mposAccDevType) {
        this.mposAccDevType = mposAccDevType;
    }
    
    public void setCustomerServicePhNum(final String customerServicePhNum) {
        this.customerServicePhNum = customerServicePhNum;
    }
    
    public void setAccepterUrlAddress(final String accepterUrlAddress) {
        this.accepterUrlAddress = accepterUrlAddress;
    }
    
    public void setTxnCurrencyExponent(final int txnCurrencyExponent) {
        this.txnCurrencyExponent = txnCurrencyExponent;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McGCOTxnWorkEntity)) {
            return false;
        }
        final McGCOTxnWorkEntity other = (McGCOTxnWorkEntity)o;
        if (!other.canEqual(this)) {
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
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0104: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0104;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
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
        final Object this$generalStatus = this.getGeneralStatus();
        final Object other$generalStatus = other.getGeneralStatus();
        Label_0178: {
            if (this$generalStatus == null) {
                if (other$generalStatus == null) {
                    break Label_0178;
                }
            }
            else if (this$generalStatus.equals(other$generalStatus)) {
                break Label_0178;
            }
            return false;
        }
        final Object this$txnRefSerNumber = this.getTxnRefSerNumber();
        final Object other$txnRefSerNumber = other.getTxnRefSerNumber();
        Label_0215: {
            if (this$txnRefSerNumber == null) {
                if (other$txnRefSerNumber == null) {
                    break Label_0215;
                }
            }
            else if (this$txnRefSerNumber.equals(other$txnRefSerNumber)) {
                break Label_0215;
            }
            return false;
        }
        final Object this$prjSerNumber = this.getPrjSerNumber();
        final Object other$prjSerNumber = other.getPrjSerNumber();
        Label_0252: {
            if (this$prjSerNumber == null) {
                if (other$prjSerNumber == null) {
                    break Label_0252;
                }
            }
            else if (this$prjSerNumber.equals(other$prjSerNumber)) {
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
        final Object this$settlementIndicator = this.getSettlementIndicator();
        final Object other$settlementIndicator = other.getSettlementIndicator();
        Label_0363: {
            if (this$settlementIndicator == null) {
                if (other$settlementIndicator == null) {
                    break Label_0363;
                }
            }
            else if (this$settlementIndicator.equals(other$settlementIndicator)) {
                break Label_0363;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0400: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0400;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0400;
            }
            return false;
        }
        final Object this$cardDomIntlFlag = this.getCardDomIntlFlag();
        final Object other$cardDomIntlFlag = other.getCardDomIntlFlag();
        Label_0437: {
            if (this$cardDomIntlFlag == null) {
                if (other$cardDomIntlFlag == null) {
                    break Label_0437;
                }
            }
            else if (this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
                break Label_0437;
            }
            return false;
        }
        final Object this$mposAccDevType = this.getMposAccDevType();
        final Object other$mposAccDevType = other.getMposAccDevType();
        Label_0474: {
            if (this$mposAccDevType == null) {
                if (other$mposAccDevType == null) {
                    break Label_0474;
                }
            }
            else if (this$mposAccDevType.equals(other$mposAccDevType)) {
                break Label_0474;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0511: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0511;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0511;
            }
            return false;
        }
        final Object this$network = this.getNetwork();
        final Object other$network = other.getNetwork();
        Label_0548: {
            if (this$network == null) {
                if (other$network == null) {
                    break Label_0548;
                }
            }
            else if (this$network.equals(other$network)) {
                break Label_0548;
            }
            return false;
        }
        final Object this$businessDate = this.getBusinessDate();
        final Object other$businessDate = other.getBusinessDate();
        Label_0585: {
            if (this$businessDate == null) {
                if (other$businessDate == null) {
                    break Label_0585;
                }
            }
            else if (this$businessDate.equals(other$businessDate)) {
                break Label_0585;
            }
            return false;
        }
        final Object this$procCode = this.getProcCode();
        final Object other$procCode = other.getProcCode();
        Label_0622: {
            if (this$procCode == null) {
                if (other$procCode == null) {
                    break Label_0622;
                }
            }
            else if (this$procCode.equals(other$procCode)) {
                break Label_0622;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_0659: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_0659;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_0659;
            }
            return false;
        }
        final Object this$serviceCode = this.getServiceCode();
        final Object other$serviceCode = other.getServiceCode();
        Label_0696: {
            if (this$serviceCode == null) {
                if (other$serviceCode == null) {
                    break Label_0696;
                }
            }
            else if (this$serviceCode.equals(other$serviceCode)) {
                break Label_0696;
            }
            return false;
        }
        final Object this$fileProcDate = this.getFileProcDate();
        final Object other$fileProcDate = other.getFileProcDate();
        Label_0733: {
            if (this$fileProcDate == null) {
                if (other$fileProcDate == null) {
                    break Label_0733;
                }
            }
            else if (this$fileProcDate.equals(other$fileProcDate)) {
                break Label_0733;
            }
            return false;
        }
        final Object this$mti = this.getMti();
        final Object other$mti = other.getMti();
        Label_0770: {
            if (this$mti == null) {
                if (other$mti == null) {
                    break Label_0770;
                }
            }
            else if (this$mti.equals(other$mti)) {
                break Label_0770;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_0807: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_0807;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_0807;
            }
            return false;
        }
        final Object this$localDateTime = this.getLocalDateTime();
        final Object other$localDateTime = other.getLocalDateTime();
        Label_0844: {
            if (this$localDateTime == null) {
                if (other$localDateTime == null) {
                    break Label_0844;
                }
            }
            else if (this$localDateTime.equals(other$localDateTime)) {
                break Label_0844;
            }
            return false;
        }
        final Object this$chPresent = this.getChPresent();
        final Object other$chPresent = other.getChPresent();
        Label_0881: {
            if (this$chPresent == null) {
                if (other$chPresent == null) {
                    break Label_0881;
                }
            }
            else if (this$chPresent.equals(other$chPresent)) {
                break Label_0881;
            }
            return false;
        }
        final Object this$cardPresent = this.getCardPresent();
        final Object other$cardPresent = other.getCardPresent();
        Label_0918: {
            if (this$cardPresent == null) {
                if (other$cardPresent == null) {
                    break Label_0918;
                }
            }
            else if (this$cardPresent.equals(other$cardPresent)) {
                break Label_0918;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        Label_0955: {
            if (this$posEntryMode == null) {
                if (other$posEntryMode == null) {
                    break Label_0955;
                }
            }
            else if (this$posEntryMode.equals(other$posEntryMode)) {
                break Label_0955;
            }
            return false;
        }
        final Object this$posDataMode = this.getPosDataMode();
        final Object other$posDataMode = other.getPosDataMode();
        Label_0992: {
            if (this$posDataMode == null) {
                if (other$posDataMode == null) {
                    break Label_0992;
                }
            }
            else if (this$posDataMode.equals(other$posDataMode)) {
                break Label_0992;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_1029: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_1029;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_1029;
            }
            return false;
        }
        final Object this$acqInstIdCode = this.getAcqInstIdCode();
        final Object other$acqInstIdCode = other.getAcqInstIdCode();
        Label_1066: {
            if (this$acqInstIdCode == null) {
                if (other$acqInstIdCode == null) {
                    break Label_1066;
                }
            }
            else if (this$acqInstIdCode.equals(other$acqInstIdCode)) {
                break Label_1066;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_1103: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_1103;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_1103;
            }
            return false;
        }
        final Object this$arn = this.getArn();
        final Object other$arn = other.getArn();
        Label_1140: {
            if (this$arn == null) {
                if (other$arn == null) {
                    break Label_1140;
                }
            }
            else if (this$arn.equals(other$arn)) {
                break Label_1140;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_1177: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_1177;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_1177;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_1214: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_1214;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_1214;
            }
            return false;
        }
        final Object this$meName = this.getMeName();
        final Object other$meName = other.getMeName();
        Label_1251: {
            if (this$meName == null) {
                if (other$meName == null) {
                    break Label_1251;
                }
            }
            else if (this$meName.equals(other$meName)) {
                break Label_1251;
            }
            return false;
        }
        final Object this$meAddress = this.getMeAddress();
        final Object other$meAddress = other.getMeAddress();
        Label_1288: {
            if (this$meAddress == null) {
                if (other$meAddress == null) {
                    break Label_1288;
                }
            }
            else if (this$meAddress.equals(other$meAddress)) {
                break Label_1288;
            }
            return false;
        }
        final Object this$meCity = this.getMeCity();
        final Object other$meCity = other.getMeCity();
        Label_1325: {
            if (this$meCity == null) {
                if (other$meCity == null) {
                    break Label_1325;
                }
            }
            else if (this$meCity.equals(other$meCity)) {
                break Label_1325;
            }
            return false;
        }
        final Object this$meZipCode = this.getMeZipCode();
        final Object other$meZipCode = other.getMeZipCode();
        Label_1362: {
            if (this$meZipCode == null) {
                if (other$meZipCode == null) {
                    break Label_1362;
                }
            }
            else if (this$meZipCode.equals(other$meZipCode)) {
                break Label_1362;
            }
            return false;
        }
        final Object this$txnCurCode = this.getTxnCurCode();
        final Object other$txnCurCode = other.getTxnCurCode();
        Label_1399: {
            if (this$txnCurCode == null) {
                if (other$txnCurCode == null) {
                    break Label_1399;
                }
            }
            else if (this$txnCurCode.equals(other$txnCurCode)) {
                break Label_1399;
            }
            return false;
        }
        final Object this$meCountry = this.getMeCountry();
        final Object other$meCountry = other.getMeCountry();
        Label_1436: {
            if (this$meCountry == null) {
                if (other$meCountry == null) {
                    break Label_1436;
                }
            }
            else if (this$meCountry.equals(other$meCountry)) {
                break Label_1436;
            }
            return false;
        }
        final Object this$fileId = this.getFileId();
        final Object other$fileId = other.getFileId();
        Label_1473: {
            if (this$fileId == null) {
                if (other$fileId == null) {
                    break Label_1473;
                }
            }
            else if (this$fileId.equals(other$fileId)) {
                break Label_1473;
            }
            return false;
        }
        final Object this$encryptedCardNumber = this.getEncryptedCardNumber();
        final Object other$encryptedCardNumber = other.getEncryptedCardNumber();
        Label_1510: {
            if (this$encryptedCardNumber == null) {
                if (other$encryptedCardNumber == null) {
                    break Label_1510;
                }
            }
            else if (this$encryptedCardNumber.equals(other$encryptedCardNumber)) {
                break Label_1510;
            }
            return false;
        }
        final Object this$txnlifeCycleId = this.getTxnlifeCycleId();
        final Object other$txnlifeCycleId = other.getTxnlifeCycleId();
        Label_1547: {
            if (this$txnlifeCycleId == null) {
                if (other$txnlifeCycleId == null) {
                    break Label_1547;
                }
            }
            else if (this$txnlifeCycleId.equals(other$txnlifeCycleId)) {
                break Label_1547;
            }
            return false;
        }
        final Object this$functionCode = this.getFunctionCode();
        final Object other$functionCode = other.getFunctionCode();
        Label_1584: {
            if (this$functionCode == null) {
                if (other$functionCode == null) {
                    break Label_1584;
                }
            }
            else if (this$functionCode.equals(other$functionCode)) {
                break Label_1584;
            }
            return false;
        }
        final Object this$msgReasonCode = this.getMsgReasonCode();
        final Object other$msgReasonCode = other.getMsgReasonCode();
        Label_1621: {
            if (this$msgReasonCode == null) {
                if (other$msgReasonCode == null) {
                    break Label_1621;
                }
            }
            else if (this$msgReasonCode.equals(other$msgReasonCode)) {
                break Label_1621;
            }
            return false;
        }
        final Object this$customerServicePhNum = this.getCustomerServicePhNum();
        final Object other$customerServicePhNum = other.getCustomerServicePhNum();
        Label_1658: {
            if (this$customerServicePhNum == null) {
                if (other$customerServicePhNum == null) {
                    break Label_1658;
                }
            }
            else if (this$customerServicePhNum.equals(other$customerServicePhNum)) {
                break Label_1658;
            }
            return false;
        }
        final Object this$accepterUrlAddress = this.getAccepterUrlAddress();
        final Object other$accepterUrlAddress = other.getAccepterUrlAddress();
        if (this$accepterUrlAddress == null) {
            if (other$accepterUrlAddress == null) {
                return true;
            }
        }
        else if (this$accepterUrlAddress.equals(other$accepterUrlAddress)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof McGCOTxnWorkEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInsCode();
        result = result * 59 + this.getIntCode();
        result = result * 59 + this.getTxnCurrencyExponent();
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $generalStatus = this.getGeneralStatus();
        result = result * 59 + (($generalStatus == null) ? 43 : $generalStatus.hashCode());
        final Object $txnRefSerNumber = this.getTxnRefSerNumber();
        result = result * 59 + (($txnRefSerNumber == null) ? 43 : $txnRefSerNumber.hashCode());
        final Object $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + (($prjSerNumber == null) ? 43 : $prjSerNumber.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + (($surchargeAmount == null) ? 43 : $surchargeAmount.hashCode());
        final Object $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + (($settlementIndicator == null) ? 43 : $settlementIndicator.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $mposAccDevType = this.getMposAccDevType();
        result = result * 59 + (($mposAccDevType == null) ? 43 : $mposAccDevType.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $businessDate = this.getBusinessDate();
        result = result * 59 + (($businessDate == null) ? 43 : $businessDate.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        final Object $fileProcDate = this.getFileProcDate();
        result = result * 59 + (($fileProcDate == null) ? 43 : $fileProcDate.hashCode());
        final Object $mti = this.getMti();
        result = result * 59 + (($mti == null) ? 43 : $mti.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $chPresent = this.getChPresent();
        result = result * 59 + (($chPresent == null) ? 43 : $chPresent.hashCode());
        final Object $cardPresent = this.getCardPresent();
        result = result * 59 + (($cardPresent == null) ? 43 : $cardPresent.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $posDataMode = this.getPosDataMode();
        result = result * 59 + (($posDataMode == null) ? 43 : $posDataMode.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $acqInstIdCode = this.getAcqInstIdCode();
        result = result * 59 + (($acqInstIdCode == null) ? 43 : $acqInstIdCode.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $arn = this.getArn();
        result = result * 59 + (($arn == null) ? 43 : $arn.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meAddress = this.getMeAddress();
        result = result * 59 + (($meAddress == null) ? 43 : $meAddress.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $meZipCode = this.getMeZipCode();
        result = result * 59 + (($meZipCode == null) ? 43 : $meZipCode.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $fileId = this.getFileId();
        result = result * 59 + (($fileId == null) ? 43 : $fileId.hashCode());
        final Object $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + (($encryptedCardNumber == null) ? 43 : $encryptedCardNumber.hashCode());
        final Object $txnlifeCycleId = this.getTxnlifeCycleId();
        result = result * 59 + (($txnlifeCycleId == null) ? 43 : $txnlifeCycleId.hashCode());
        final Object $functionCode = this.getFunctionCode();
        result = result * 59 + (($functionCode == null) ? 43 : $functionCode.hashCode());
        final Object $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + (($msgReasonCode == null) ? 43 : $msgReasonCode.hashCode());
        final Object $customerServicePhNum = this.getCustomerServicePhNum();
        result = result * 59 + (($customerServicePhNum == null) ? 43 : $customerServicePhNum.hashCode());
        final Object $accepterUrlAddress = this.getAccepterUrlAddress();
        result = result * 59 + (($accepterUrlAddress == null) ? 43 : $accepterUrlAddress.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "McGCOTxnWorkEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", generalStatus=" + this.getGeneralStatus() + ", network=" + this.getNetwork() + ", txnRefSerNumber=" + this.getTxnRefSerNumber() + ", prjSerNumber=" + this.getPrjSerNumber() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", procCode=" + this.getProcCode() + ", approvalCode=" + this.getApprovalCode() + ", serviceCode=" + this.getServiceCode() + ", fileProcDate=" + String.valueOf(this.getFileProcDate()) + ", mti=" + this.getMti() + ", txnType=" + this.getTxnType() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent() + ", posEntryMode=" + this.getPosEntryMode() + ", posDataMode=" + this.getPosDataMode() + ", mcc=" + this.getMcc() + ", acqInstIdCode=" + this.getAcqInstIdCode() + ", rrn=" + this.getRrn() + ", arn=" + this.getArn() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", meName=" + this.getMeName() + ", meAddress=" + this.getMeAddress() + ", meCity=" + this.getMeCity() + ", meZipCode=" + this.getMeZipCode() + ", txnCurCode=" + this.getTxnCurCode() + ", meCountry=" + this.getMeCountry() + ", fileId=" + this.getFileId() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", settlementIndicator=" + this.getSettlementIndicator() + ", txnlifeCycleId=" + this.getTxnlifeCycleId() + ", functionCode=" + this.getFunctionCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", cardType=" + this.getCardType() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", mposAccDevType=" + this.getMposAccDevType() + ", customerServicePhNum=" + this.getCustomerServicePhNum() + ", accepterUrlAddress=" + this.getAccepterUrlAddress() + ", txnCurrencyExponent=" + this.getTxnCurrencyExponent();
    }
    
    public McGCOTxnWorkEntity() {
    }
    
    public McGCOTxnWorkEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final int insCode, final int intCode, final Integer generalStatus, final String network, final Integer txnRefSerNumber, final Integer prjSerNumber, final LocalDate businessDate, final String procCode, final String approvalCode, final String serviceCode, final LocalDate fileProcDate, final String mti, final String txnType, final Double txnAmount, final Double surchargeAmount, final LocalDateTime localDateTime, final String chPresent, final String cardPresent, final String posEntryMode, final String posDataMode, final String mcc, final String acqInstIdCode, final String rrn, final String arn, final String terminalId, final String merchantId, final String meName, final String meAddress, final String meCity, final String meZipCode, final String txnCurCode, final String meCountry, final String fileId, final String encryptedCardNumber, final Character settlementIndicator, final String txnlifeCycleId, final String functionCode, final String msgReasonCode, final Character cardType, final Character cardDomIntlFlag, final Character mposAccDevType, final String customerServicePhNum, final String accepterUrlAddress, final int txnCurrencyExponent) {
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
        this.customerServicePhNum = customerServicePhNum;
        this.accepterUrlAddress = accepterUrlAddress;
        this.txnCurrencyExponent = txnCurrencyExponent;
    }
    
    public static class McGCOTxnWorkEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private int insCode;
        private int intCode;
        private Integer generalStatus;
        private String network;
        private Integer txnRefSerNumber;
        private Integer prjSerNumber;
        private LocalDate businessDate;
        private String procCode;
        private String approvalCode;
        private String serviceCode;
        private LocalDate fileProcDate;
        private String mti;
        private String txnType;
        private Double txnAmount;
        private Double surchargeAmount;
        private LocalDateTime localDateTime;
        private String chPresent;
        private String cardPresent;
        private String posEntryMode;
        private String posDataMode;
        private String mcc;
        private String acqInstIdCode;
        private String rrn;
        private String arn;
        private String terminalId;
        private String merchantId;
        private String meName;
        private String meAddress;
        private String meCity;
        private String meZipCode;
        private String txnCurCode;
        private String meCountry;
        private String fileId;
        private String encryptedCardNumber;
        private Character settlementIndicator;
        private String txnlifeCycleId;
        private String functionCode;
        private String msgReasonCode;
        private Character cardType;
        private Character cardDomIntlFlag;
        private Character mposAccDevType;
        private String customerServicePhNum;
        private String accepterUrlAddress;
        private int txnCurrencyExponent;
        
        McGCOTxnWorkEntityBuilder() {
        }
        
        public McGCOTxnWorkEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder insCode(final int insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder intCode(final int intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder generalStatus(final Integer generalStatus) {
            this.generalStatus = generalStatus;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder network(final String network) {
            this.network = network;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder txnRefSerNumber(final Integer txnRefSerNumber) {
            this.txnRefSerNumber = txnRefSerNumber;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder businessDate(final LocalDate businessDate) {
            this.businessDate = businessDate;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder procCode(final String procCode) {
            this.procCode = procCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder serviceCode(final String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder fileProcDate(final LocalDate fileProcDate) {
            this.fileProcDate = fileProcDate;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder mti(final String mti) {
            this.mti = mti;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder surchargeAmount(final Double surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder chPresent(final String chPresent) {
            this.chPresent = chPresent;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder cardPresent(final String cardPresent) {
            this.cardPresent = cardPresent;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder posDataMode(final String posDataMode) {
            this.posDataMode = posDataMode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder acqInstIdCode(final String acqInstIdCode) {
            this.acqInstIdCode = acqInstIdCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder arn(final String arn) {
            this.arn = arn;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder meAddress(final String meAddress) {
            this.meAddress = meAddress;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder meZipCode(final String meZipCode) {
            this.meZipCode = meZipCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder fileId(final String fileId) {
            this.fileId = fileId;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder encryptedCardNumber(final String encryptedCardNumber) {
            this.encryptedCardNumber = encryptedCardNumber;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder settlementIndicator(final Character settlementIndicator) {
            this.settlementIndicator = settlementIndicator;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder txnlifeCycleId(final String txnlifeCycleId) {
            this.txnlifeCycleId = txnlifeCycleId;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder functionCode(final String functionCode) {
            this.functionCode = functionCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder msgReasonCode(final String msgReasonCode) {
            this.msgReasonCode = msgReasonCode;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder cardDomIntlFlag(final Character cardDomIntlFlag) {
            this.cardDomIntlFlag = cardDomIntlFlag;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder mposAccDevType(final Character mposAccDevType) {
            this.mposAccDevType = mposAccDevType;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder customerServicePhNum(final String customerServicePhNum) {
            this.customerServicePhNum = customerServicePhNum;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder accepterUrlAddress(final String accepterUrlAddress) {
            this.accepterUrlAddress = accepterUrlAddress;
            return this;
        }
        
        public McGCOTxnWorkEntityBuilder txnCurrencyExponent(final int txnCurrencyExponent) {
            this.txnCurrencyExponent = txnCurrencyExponent;
            return this;
        }
        
        public McGCOTxnWorkEntity build() {
            return new McGCOTxnWorkEntity(this.serialNumber, this.lastUpdated, this.updatedUser, this.insCode, this.intCode, this.generalStatus, this.network, this.txnRefSerNumber, this.prjSerNumber, this.businessDate, this.procCode, this.approvalCode, this.serviceCode, this.fileProcDate, this.mti, this.txnType, this.txnAmount, this.surchargeAmount, this.localDateTime, this.chPresent, this.cardPresent, this.posEntryMode, this.posDataMode, this.mcc, this.acqInstIdCode, this.rrn, this.arn, this.terminalId, this.merchantId, this.meName, this.meAddress, this.meCity, this.meZipCode, this.txnCurCode, this.meCountry, this.fileId, this.encryptedCardNumber, this.settlementIndicator, this.txnlifeCycleId, this.functionCode, this.msgReasonCode, this.cardType, this.cardDomIntlFlag, this.mposAccDevType, this.customerServicePhNum, this.accepterUrlAddress, this.txnCurrencyExponent);
        }
        
        @Override
        public String toString() {
            return "McGCOTxnWorkEntity.McGCOTxnWorkEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", insCode=" + this.insCode + ", intCode=" + this.intCode + ", generalStatus=" + this.generalStatus + ", network=" + this.network + ", txnRefSerNumber=" + this.txnRefSerNumber + ", prjSerNumber=" + this.prjSerNumber + ", businessDate=" + String.valueOf(this.businessDate) + ", procCode=" + this.procCode + ", approvalCode=" + this.approvalCode + ", serviceCode=" + this.serviceCode + ", fileProcDate=" + String.valueOf(this.fileProcDate) + ", mti=" + this.mti + ", txnType=" + this.txnType + ", txnAmount=" + this.txnAmount + ", surchargeAmount=" + this.surchargeAmount + ", localDateTime=" + String.valueOf(this.localDateTime) + ", chPresent=" + this.chPresent + ", cardPresent=" + this.cardPresent + ", posEntryMode=" + this.posEntryMode + ", posDataMode=" + this.posDataMode + ", mcc=" + this.mcc + ", acqInstIdCode=" + this.acqInstIdCode + ", rrn=" + this.rrn + ", arn=" + this.arn + ", terminalId=" + this.terminalId + ", merchantId=" + this.merchantId + ", meName=" + this.meName + ", meAddress=" + this.meAddress + ", meCity=" + this.meCity + ", meZipCode=" + this.meZipCode + ", txnCurCode=" + this.txnCurCode + ", meCountry=" + this.meCountry + ", fileId=" + this.fileId + ", encryptedCardNumber=" + this.encryptedCardNumber + ", settlementIndicator=" + this.settlementIndicator + ", txnlifeCycleId=" + this.txnlifeCycleId + ", functionCode=" + this.functionCode + ", msgReasonCode=" + this.msgReasonCode + ", cardType=" + this.cardType + ", cardDomIntlFlag=" + this.cardDomIntlFlag + ", mposAccDevType=" + this.mposAccDevType + ", customerServicePhNum=" + this.customerServicePhNum + ", accepterUrlAddress=" + this.accepterUrlAddress + ", txnCurrencyExponent=" + this.txnCurrencyExponent;
        }
    }
}
