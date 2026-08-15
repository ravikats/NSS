/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.VisaAcqTxnWorkEntity
 *  com.empay.staging.entities.VisaAcqTxnWorkEntity$VisaAcqTxnWorkEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import com.empay.staging.entities.VisaAcqTxnWorkEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="VISA_ACQ_TXN_WORK")
public class VisaAcqTxnWorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="VTD_SER_NUMBER")
    private Integer serNumber;
    @Column(name="VTD_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="VTD_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="VTD_INS_CODE")
    private Integer institutionCode;
    @Column(name="VTD_INT_CODE")
    private Integer intCode;
    @Column(name="VTD_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name="VTD_GEN_STATUS")
    private Integer genStatus;
    @Column(name="VTD_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name="VTD_TXN_TYPE")
    private String txnType;
    @Column(name="VTD_TXN_CODE")
    private String txnCode;
    @Column(name="VTD_ENC_CARD_NUMBER")
    private String encCardNumber;
    @Column(name="VTD_ACQ_REF_NUMBER")
    private String arn;
    @Column(name="VTD_PURCHASE_DATE")
    private LocalDateTime purchaseDate;
    @Column(name="VTD_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name="VTD_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="VTD_SCHG_AMOUNT")
    private Double schgAmount;
    @Column(name="VTD_ME_NAME")
    private String meName;
    @Column(name="VTD_ME_CITY")
    private String meCity;
    @Column(name="VTD_ME_COUNTRY")
    private String meCountry;
    @Column(name="VTD_MCC")
    private String mcc;
    @Column(name="VTD_APPR_CODE")
    private String approvalCode;
    @Column(name="VTD_CH_ID_METHOD")
    private Character chIdMethod;
    @Column(name="VTD_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name="VTD_MEMBER_TEXT")
    private String memberText;
    @Column(name="VTD_FEE_PRG_INDICATOR")
    private String feePrgIndicator;
    @Column(name="VTD_MERCHANT_ID")
    private String merchantId;
    @Column(name="VTD_TERMINAL_ID")
    private String terminalId;
    @Column(name="VTD_MOTO_ECOM_INDICATOR")
    private Character motoEcomIndicator;
    @Column(name="VTD_ACC_SELECTION")
    private Character accSelection;
    @Column(name="VTD_ACQ_BUSS_ID")
    private String acqBussId;
    @Column(name="VTD_POS_ENVIRONMENT")
    private Character posEnvironment;
    @Column(name="VTD_RESP_CODE")
    private String respCode;
    @Column(name="VTD_TRL_TXN_DATE")
    private LocalDate trlTxnDate;
    @Column(name="VTD_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name="VTD_CASHBACK_AMOUNT")
    private Double cashbackAmount;
    @Column(name="VTD_TXN_ID")
    private String txnId;
    @Column(name="VTD_VISA_TOKEN")
    private Character visaToken;
    @Column(name="VTD_AUTH_CHAR_INDICATOR")
    private Character authCharIndicator;
    @Column(name="VTD_ACC_FUND_SOURCE")
    private Character accFundSource;
    @Column(name="VTD_MARKET_SPEC_DATA_IND")
    private Character marketSpecDataInd;
    @Column(name="VTD_PRODUCT_ID")
    private String productId;
    @Column(name="VTD_VALIDATION_CODE")
    private String validationCode;
    @Column(name="VTD_SPEND_QUALI_IND")
    private Character spendQualiIndictor;
    @Column(name="VTD_COLL_ONLY_FLAG")
    private Character collOnlyFlag;
    @Column(name="VTD_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name="VTD_PROC_CODE")
    private String procCode;
    @Column(name="VTD_USAGE_CODE")
    private Character usageCode;
    @Column(name="VTD_REASON_CODE")
    private String reasonCode;
    @Column(name="VTD_SETL_FLAG")
    private Character setlFlag;
    @Column(name="VTD_TRL_CAPABILITY")
    private Character terminalCapability;
    @Column(name="VTD_REIMB_ATTRIBUTE")
    private Character reimAttribute;
    @Column(name="VTD_STAN")
    private String stan;
    @Column(name="VTD_AUTH_AMOUNT")
    private Double authAmount;
    @Column(name="VTD_TRL_CAP_PROFILE")
    private String trlCapProfile;
    @Column(name="VTD_TRL_CON_CODE")
    private String trlCountryCode;
    @Column(name="VTD_UPBL_NUMBER")
    private String upblNumber;
    @Column(name="VTD_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name="VTD_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name="VTD_APP_IC_PROFILE")
    private String appIcProfile;
    @Column(name="VTD_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name="VTD_ISS_APP_DATA_B2")
    private String issAppDataB2;
    @Column(name="VTD_ISS_APP_DATA_B3")
    private String issAppDataB3;
    @Column(name="VTD_ISS_APP_DATA_B4")
    private String issAppDataB4;
    @Column(name="VTD_ISS_APP_DATA_B8")
    private String issAppDataB8;
    @Column(name="VTD_ISS_APP_DATA_B9")
    private String issAppDataB9;
    @Column(name="VTD_ISS_APP_DATA_B1")
    private String issAppDataB1;
    @Column(name="VTD_ISS_APP_DATA_B17")
    private String issAppDataB17;
    @Column(name="VTD_ISS_APP_DATA_B18")
    private String issAppDataB18;
    @Column(name="VTD_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name="VTD_FORM_FACT_INDICATOR")
    private String formFactorIndicator;
    @Column(name="VTD_ISS_SCRIPT_RESULTS")
    private String issScriptResult;
    @Column(name="VTD_SERVICE_CODE")
    private String serviceCode;
    @Column(name="VTD_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name="VTD_FILE_ID")
    private String fileId;
    @Column(name="VTD_SENDER_NAME")
    private String senderName;
    @Column(name="VTD_RECIPIENT_NAME")
    private String recipientName;
    @Column(name="VTD_BUSS_APP_ID")
    private String bussAppId;
    @Column(name="VTD_SENDER_ACCOUNT")
    private String senderAccount;
    @Column(name="VTD_DCC_INDICATOR")
    private Character dccIndicator;
    @Column(name="VTD_NETWORK")
    private String network;
    @Column(name="VTD_DOM_INTL_FLAG")
    private Character domIntlFlag;
    @Column(name="VTD_CARD_TYPE")
    private Character cardType;
    @Column(name="VTD_DCC_CURRENCY")
    private String dccCurrency;
    @Column(name="VTD_DCC_AMOUNT")
    private Double dccAmount;
    @Column(name="VTD_ACC_TRL_INDICATOR")
    private Character acceptanceTrlIndicator;

    public static VisaAcqTxnWorkEntityBuilder builder() {
        return new VisaAcqTxnWorkEntityBuilder();
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

    public Integer getGenStatus() {
        return this.genStatus;
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

    public String getEncCardNumber() {
        return this.encCardNumber;
    }

    public String getArn() {
        return this.arn;
    }

    public LocalDateTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public String getTxnCurCode() {
        return this.txnCurCode;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getSchgAmount() {
        return this.schgAmount;
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

    public String getMcc() {
        return this.mcc;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public Character getChIdMethod() {
        return this.chIdMethod;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public String getFeePrgIndicator() {
        return this.feePrgIndicator;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public Character getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }

    public Character getAccSelection() {
        return this.accSelection;
    }

    public String getAcqBussId() {
        return this.acqBussId;
    }

    public Character getPosEnvironment() {
        return this.posEnvironment;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public LocalDate getTrlTxnDate() {
        return this.trlTxnDate;
    }

    public Double getCryptAmount() {
        return this.cryptAmount;
    }

    public Double getCashbackAmount() {
        return this.cashbackAmount;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public Character getVisaToken() {
        return this.visaToken;
    }

    public Character getAuthCharIndicator() {
        return this.authCharIndicator;
    }

    public Character getAccFundSource() {
        return this.accFundSource;
    }

    public Character getMarketSpecDataInd() {
        return this.marketSpecDataInd;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getValidationCode() {
        return this.validationCode;
    }

    public Character getSpendQualiIndictor() {
        return this.spendQualiIndictor;
    }

    public Character getCollOnlyFlag() {
        return this.collOnlyFlag;
    }

    public String getRetRefNumber() {
        return this.retRefNumber;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public Character getUsageCode() {
        return this.usageCode;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public Character getSetlFlag() {
        return this.setlFlag;
    }

    public Character getTerminalCapability() {
        return this.terminalCapability;
    }

    public Character getReimAttribute() {
        return this.reimAttribute;
    }

    public String getStan() {
        return this.stan;
    }

    public Double getAuthAmount() {
        return this.authAmount;
    }

    public String getTrlCapProfile() {
        return this.trlCapProfile;
    }

    public String getTrlCountryCode() {
        return this.trlCountryCode;
    }

    public String getUpblNumber() {
        return this.upblNumber;
    }

    public String getCardSeqNumber() {
        return this.cardSeqNumber;
    }

    public String getAppTxnCounter() {
        return this.appTxnCounter;
    }

    public String getAppIcProfile() {
        return this.appIcProfile;
    }

    public String getAppCryptogram() {
        return this.appCryptogram;
    }

    public String getIssAppDataB2() {
        return this.issAppDataB2;
    }

    public String getIssAppDataB3() {
        return this.issAppDataB3;
    }

    public String getIssAppDataB4() {
        return this.issAppDataB4;
    }

    public String getIssAppDataB8() {
        return this.issAppDataB8;
    }

    public String getIssAppDataB9() {
        return this.issAppDataB9;
    }

    public String getIssAppDataB1() {
        return this.issAppDataB1;
    }

    public String getIssAppDataB17() {
        return this.issAppDataB17;
    }

    public String getIssAppDataB18() {
        return this.issAppDataB18;
    }

    public String getTrlVerResult() {
        return this.trlVerResult;
    }

    public String getFormFactorIndicator() {
        return this.formFactorIndicator;
    }

    public String getIssScriptResult() {
        return this.issScriptResult;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public Double getTxnFeeAmount() {
        return this.txnFeeAmount;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public String getBussAppId() {
        return this.bussAppId;
    }

    public String getSenderAccount() {
        return this.senderAccount;
    }

    public Character getDccIndicator() {
        return this.dccIndicator;
    }

    public String getNetwork() {
        return this.network;
    }

    public Character getDomIntlFlag() {
        return this.domIntlFlag;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public String getDccCurrency() {
        return this.dccCurrency;
    }

    public Double getDccAmount() {
        return this.dccAmount;
    }

    public Character getAcceptanceTrlIndicator() {
        return this.acceptanceTrlIndicator;
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

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setTxnRefNumber(Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setEncCardNumber(String encCardNumber) {
        this.encCardNumber = encCardNumber;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTxnCurCode(String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setSchgAmount(Double schgAmount) {
        this.schgAmount = schgAmount;
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

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setChIdMethod(Character chIdMethod) {
        this.chIdMethod = chIdMethod;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setFeePrgIndicator(String feePrgIndicator) {
        this.feePrgIndicator = feePrgIndicator;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMotoEcomIndicator(Character motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }

    public void setAccSelection(Character accSelection) {
        this.accSelection = accSelection;
    }

    public void setAcqBussId(String acqBussId) {
        this.acqBussId = acqBussId;
    }

    public void setPosEnvironment(Character posEnvironment) {
        this.posEnvironment = posEnvironment;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setTrlTxnDate(LocalDate trlTxnDate) {
        this.trlTxnDate = trlTxnDate;
    }

    public void setCryptAmount(Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }

    public void setCashbackAmount(Double cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setVisaToken(Character visaToken) {
        this.visaToken = visaToken;
    }

    public void setAuthCharIndicator(Character authCharIndicator) {
        this.authCharIndicator = authCharIndicator;
    }

    public void setAccFundSource(Character accFundSource) {
        this.accFundSource = accFundSource;
    }

    public void setMarketSpecDataInd(Character marketSpecDataInd) {
        this.marketSpecDataInd = marketSpecDataInd;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public void setSpendQualiIndictor(Character spendQualiIndictor) {
        this.spendQualiIndictor = spendQualiIndictor;
    }

    public void setCollOnlyFlag(Character collOnlyFlag) {
        this.collOnlyFlag = collOnlyFlag;
    }

    public void setRetRefNumber(String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setUsageCode(Character usageCode) {
        this.usageCode = usageCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setSetlFlag(Character setlFlag) {
        this.setlFlag = setlFlag;
    }

    public void setTerminalCapability(Character terminalCapability) {
        this.terminalCapability = terminalCapability;
    }

    public void setReimAttribute(Character reimAttribute) {
        this.reimAttribute = reimAttribute;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public void setAuthAmount(Double authAmount) {
        this.authAmount = authAmount;
    }

    public void setTrlCapProfile(String trlCapProfile) {
        this.trlCapProfile = trlCapProfile;
    }

    public void setTrlCountryCode(String trlCountryCode) {
        this.trlCountryCode = trlCountryCode;
    }

    public void setUpblNumber(String upblNumber) {
        this.upblNumber = upblNumber;
    }

    public void setCardSeqNumber(String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }

    public void setAppTxnCounter(String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }

    public void setAppIcProfile(String appIcProfile) {
        this.appIcProfile = appIcProfile;
    }

    public void setAppCryptogram(String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }

    public void setIssAppDataB2(String issAppDataB2) {
        this.issAppDataB2 = issAppDataB2;
    }

    public void setIssAppDataB3(String issAppDataB3) {
        this.issAppDataB3 = issAppDataB3;
    }

    public void setIssAppDataB4(String issAppDataB4) {
        this.issAppDataB4 = issAppDataB4;
    }

    public void setIssAppDataB8(String issAppDataB8) {
        this.issAppDataB8 = issAppDataB8;
    }

    public void setIssAppDataB9(String issAppDataB9) {
        this.issAppDataB9 = issAppDataB9;
    }

    public void setIssAppDataB1(String issAppDataB1) {
        this.issAppDataB1 = issAppDataB1;
    }

    public void setIssAppDataB17(String issAppDataB17) {
        this.issAppDataB17 = issAppDataB17;
    }

    public void setIssAppDataB18(String issAppDataB18) {
        this.issAppDataB18 = issAppDataB18;
    }

    public void setTrlVerResult(String trlVerResult) {
        this.trlVerResult = trlVerResult;
    }

    public void setFormFactorIndicator(String formFactorIndicator) {
        this.formFactorIndicator = formFactorIndicator;
    }

    public void setIssScriptResult(String issScriptResult) {
        this.issScriptResult = issScriptResult;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setTxnFeeAmount(Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setBussAppId(String bussAppId) {
        this.bussAppId = bussAppId;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setDccIndicator(Character dccIndicator) {
        this.dccIndicator = dccIndicator;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setDomIntlFlag(Character domIntlFlag) {
        this.domIntlFlag = domIntlFlag;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setDccCurrency(String dccCurrency) {
        this.dccCurrency = dccCurrency;
    }

    public void setDccAmount(Double dccAmount) {
        this.dccAmount = dccAmount;
    }

    public void setAcceptanceTrlIndicator(Character acceptanceTrlIndicator) {
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaAcqTxnWorkEntity)) {
            return false;
        }
        VisaAcqTxnWorkEntity other = (VisaAcqTxnWorkEntity)o;
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
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
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
        Double this$schgAmount = this.getSchgAmount();
        Double other$schgAmount = other.getSchgAmount();
        if (this$schgAmount == null ? other$schgAmount != null : !((Object)this$schgAmount).equals(other$schgAmount)) {
            return false;
        }
        Character this$chIdMethod = this.getChIdMethod();
        Character other$chIdMethod = other.getChIdMethod();
        if (this$chIdMethod == null ? other$chIdMethod != null : !((Object)this$chIdMethod).equals(other$chIdMethod)) {
            return false;
        }
        Character this$motoEcomIndicator = this.getMotoEcomIndicator();
        Character other$motoEcomIndicator = other.getMotoEcomIndicator();
        if (this$motoEcomIndicator == null ? other$motoEcomIndicator != null : !((Object)this$motoEcomIndicator).equals(other$motoEcomIndicator)) {
            return false;
        }
        Character this$accSelection = this.getAccSelection();
        Character other$accSelection = other.getAccSelection();
        if (this$accSelection == null ? other$accSelection != null : !((Object)this$accSelection).equals(other$accSelection)) {
            return false;
        }
        Character this$posEnvironment = this.getPosEnvironment();
        Character other$posEnvironment = other.getPosEnvironment();
        if (this$posEnvironment == null ? other$posEnvironment != null : !((Object)this$posEnvironment).equals(other$posEnvironment)) {
            return false;
        }
        Double this$cryptAmount = this.getCryptAmount();
        Double other$cryptAmount = other.getCryptAmount();
        if (this$cryptAmount == null ? other$cryptAmount != null : !((Object)this$cryptAmount).equals(other$cryptAmount)) {
            return false;
        }
        Double this$cashbackAmount = this.getCashbackAmount();
        Double other$cashbackAmount = other.getCashbackAmount();
        if (this$cashbackAmount == null ? other$cashbackAmount != null : !((Object)this$cashbackAmount).equals(other$cashbackAmount)) {
            return false;
        }
        Character this$visaToken = this.getVisaToken();
        Character other$visaToken = other.getVisaToken();
        if (this$visaToken == null ? other$visaToken != null : !((Object)this$visaToken).equals(other$visaToken)) {
            return false;
        }
        Character this$authCharIndicator = this.getAuthCharIndicator();
        Character other$authCharIndicator = other.getAuthCharIndicator();
        if (this$authCharIndicator == null ? other$authCharIndicator != null : !((Object)this$authCharIndicator).equals(other$authCharIndicator)) {
            return false;
        }
        Character this$accFundSource = this.getAccFundSource();
        Character other$accFundSource = other.getAccFundSource();
        if (this$accFundSource == null ? other$accFundSource != null : !((Object)this$accFundSource).equals(other$accFundSource)) {
            return false;
        }
        Character this$marketSpecDataInd = this.getMarketSpecDataInd();
        Character other$marketSpecDataInd = other.getMarketSpecDataInd();
        if (this$marketSpecDataInd == null ? other$marketSpecDataInd != null : !((Object)this$marketSpecDataInd).equals(other$marketSpecDataInd)) {
            return false;
        }
        Character this$spendQualiIndictor = this.getSpendQualiIndictor();
        Character other$spendQualiIndictor = other.getSpendQualiIndictor();
        if (this$spendQualiIndictor == null ? other$spendQualiIndictor != null : !((Object)this$spendQualiIndictor).equals(other$spendQualiIndictor)) {
            return false;
        }
        Character this$collOnlyFlag = this.getCollOnlyFlag();
        Character other$collOnlyFlag = other.getCollOnlyFlag();
        if (this$collOnlyFlag == null ? other$collOnlyFlag != null : !((Object)this$collOnlyFlag).equals(other$collOnlyFlag)) {
            return false;
        }
        Character this$usageCode = this.getUsageCode();
        Character other$usageCode = other.getUsageCode();
        if (this$usageCode == null ? other$usageCode != null : !((Object)this$usageCode).equals(other$usageCode)) {
            return false;
        }
        Character this$setlFlag = this.getSetlFlag();
        Character other$setlFlag = other.getSetlFlag();
        if (this$setlFlag == null ? other$setlFlag != null : !((Object)this$setlFlag).equals(other$setlFlag)) {
            return false;
        }
        Character this$terminalCapability = this.getTerminalCapability();
        Character other$terminalCapability = other.getTerminalCapability();
        if (this$terminalCapability == null ? other$terminalCapability != null : !((Object)this$terminalCapability).equals(other$terminalCapability)) {
            return false;
        }
        Character this$reimAttribute = this.getReimAttribute();
        Character other$reimAttribute = other.getReimAttribute();
        if (this$reimAttribute == null ? other$reimAttribute != null : !((Object)this$reimAttribute).equals(other$reimAttribute)) {
            return false;
        }
        Double this$authAmount = this.getAuthAmount();
        Double other$authAmount = other.getAuthAmount();
        if (this$authAmount == null ? other$authAmount != null : !((Object)this$authAmount).equals(other$authAmount)) {
            return false;
        }
        Double this$txnFeeAmount = this.getTxnFeeAmount();
        Double other$txnFeeAmount = other.getTxnFeeAmount();
        if (this$txnFeeAmount == null ? other$txnFeeAmount != null : !((Object)this$txnFeeAmount).equals(other$txnFeeAmount)) {
            return false;
        }
        Character this$dccIndicator = this.getDccIndicator();
        Character other$dccIndicator = other.getDccIndicator();
        if (this$dccIndicator == null ? other$dccIndicator != null : !((Object)this$dccIndicator).equals(other$dccIndicator)) {
            return false;
        }
        Character this$domIntlFlag = this.getDomIntlFlag();
        Character other$domIntlFlag = other.getDomIntlFlag();
        if (this$domIntlFlag == null ? other$domIntlFlag != null : !((Object)this$domIntlFlag).equals(other$domIntlFlag)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Double this$dccAmount = this.getDccAmount();
        Double other$dccAmount = other.getDccAmount();
        if (this$dccAmount == null ? other$dccAmount != null : !((Object)this$dccAmount).equals(other$dccAmount)) {
            return false;
        }
        Character this$acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        Character other$acceptanceTrlIndicator = other.getAcceptanceTrlIndicator();
        if (this$acceptanceTrlIndicator == null ? other$acceptanceTrlIndicator != null : !((Object)this$acceptanceTrlIndicator).equals(other$acceptanceTrlIndicator)) {
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
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$encCardNumber = this.getEncCardNumber();
        String other$encCardNumber = other.getEncCardNumber();
        if (this$encCardNumber == null ? other$encCardNumber != null : !this$encCardNumber.equals(other$encCardNumber)) {
            return false;
        }
        String this$arn = this.getArn();
        String other$arn = other.getArn();
        if (this$arn == null ? other$arn != null : !this$arn.equals(other$arn)) {
            return false;
        }
        LocalDateTime this$purchaseDate = this.getPurchaseDate();
        LocalDateTime other$purchaseDate = other.getPurchaseDate();
        if (this$purchaseDate == null ? other$purchaseDate != null : !((Object)this$purchaseDate).equals(other$purchaseDate)) {
            return false;
        }
        String this$txnCurCode = this.getTxnCurCode();
        String other$txnCurCode = other.getTxnCurCode();
        if (this$txnCurCode == null ? other$txnCurCode != null : !this$txnCurCode.equals(other$txnCurCode)) {
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
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
            return false;
        }
        String this$feePrgIndicator = this.getFeePrgIndicator();
        String other$feePrgIndicator = other.getFeePrgIndicator();
        if (this$feePrgIndicator == null ? other$feePrgIndicator != null : !this$feePrgIndicator.equals(other$feePrgIndicator)) {
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
        String this$acqBussId = this.getAcqBussId();
        String other$acqBussId = other.getAcqBussId();
        if (this$acqBussId == null ? other$acqBussId != null : !this$acqBussId.equals(other$acqBussId)) {
            return false;
        }
        String this$respCode = this.getRespCode();
        String other$respCode = other.getRespCode();
        if (this$respCode == null ? other$respCode != null : !this$respCode.equals(other$respCode)) {
            return false;
        }
        LocalDate this$trlTxnDate = this.getTrlTxnDate();
        LocalDate other$trlTxnDate = other.getTrlTxnDate();
        if (this$trlTxnDate == null ? other$trlTxnDate != null : !((Object)this$trlTxnDate).equals(other$trlTxnDate)) {
            return false;
        }
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
            return false;
        }
        String this$productId = this.getProductId();
        String other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) {
            return false;
        }
        String this$validationCode = this.getValidationCode();
        String other$validationCode = other.getValidationCode();
        if (this$validationCode == null ? other$validationCode != null : !this$validationCode.equals(other$validationCode)) {
            return false;
        }
        String this$retRefNumber = this.getRetRefNumber();
        String other$retRefNumber = other.getRetRefNumber();
        if (this$retRefNumber == null ? other$retRefNumber != null : !this$retRefNumber.equals(other$retRefNumber)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$reasonCode = this.getReasonCode();
        String other$reasonCode = other.getReasonCode();
        if (this$reasonCode == null ? other$reasonCode != null : !this$reasonCode.equals(other$reasonCode)) {
            return false;
        }
        String this$stan = this.getStan();
        String other$stan = other.getStan();
        if (this$stan == null ? other$stan != null : !this$stan.equals(other$stan)) {
            return false;
        }
        String this$trlCapProfile = this.getTrlCapProfile();
        String other$trlCapProfile = other.getTrlCapProfile();
        if (this$trlCapProfile == null ? other$trlCapProfile != null : !this$trlCapProfile.equals(other$trlCapProfile)) {
            return false;
        }
        String this$trlCountryCode = this.getTrlCountryCode();
        String other$trlCountryCode = other.getTrlCountryCode();
        if (this$trlCountryCode == null ? other$trlCountryCode != null : !this$trlCountryCode.equals(other$trlCountryCode)) {
            return false;
        }
        String this$upblNumber = this.getUpblNumber();
        String other$upblNumber = other.getUpblNumber();
        if (this$upblNumber == null ? other$upblNumber != null : !this$upblNumber.equals(other$upblNumber)) {
            return false;
        }
        String this$cardSeqNumber = this.getCardSeqNumber();
        String other$cardSeqNumber = other.getCardSeqNumber();
        if (this$cardSeqNumber == null ? other$cardSeqNumber != null : !this$cardSeqNumber.equals(other$cardSeqNumber)) {
            return false;
        }
        String this$appTxnCounter = this.getAppTxnCounter();
        String other$appTxnCounter = other.getAppTxnCounter();
        if (this$appTxnCounter == null ? other$appTxnCounter != null : !this$appTxnCounter.equals(other$appTxnCounter)) {
            return false;
        }
        String this$appIcProfile = this.getAppIcProfile();
        String other$appIcProfile = other.getAppIcProfile();
        if (this$appIcProfile == null ? other$appIcProfile != null : !this$appIcProfile.equals(other$appIcProfile)) {
            return false;
        }
        String this$appCryptogram = this.getAppCryptogram();
        String other$appCryptogram = other.getAppCryptogram();
        if (this$appCryptogram == null ? other$appCryptogram != null : !this$appCryptogram.equals(other$appCryptogram)) {
            return false;
        }
        String this$issAppDataB2 = this.getIssAppDataB2();
        String other$issAppDataB2 = other.getIssAppDataB2();
        if (this$issAppDataB2 == null ? other$issAppDataB2 != null : !this$issAppDataB2.equals(other$issAppDataB2)) {
            return false;
        }
        String this$issAppDataB3 = this.getIssAppDataB3();
        String other$issAppDataB3 = other.getIssAppDataB3();
        if (this$issAppDataB3 == null ? other$issAppDataB3 != null : !this$issAppDataB3.equals(other$issAppDataB3)) {
            return false;
        }
        String this$issAppDataB4 = this.getIssAppDataB4();
        String other$issAppDataB4 = other.getIssAppDataB4();
        if (this$issAppDataB4 == null ? other$issAppDataB4 != null : !this$issAppDataB4.equals(other$issAppDataB4)) {
            return false;
        }
        String this$issAppDataB8 = this.getIssAppDataB8();
        String other$issAppDataB8 = other.getIssAppDataB8();
        if (this$issAppDataB8 == null ? other$issAppDataB8 != null : !this$issAppDataB8.equals(other$issAppDataB8)) {
            return false;
        }
        String this$issAppDataB9 = this.getIssAppDataB9();
        String other$issAppDataB9 = other.getIssAppDataB9();
        if (this$issAppDataB9 == null ? other$issAppDataB9 != null : !this$issAppDataB9.equals(other$issAppDataB9)) {
            return false;
        }
        String this$issAppDataB1 = this.getIssAppDataB1();
        String other$issAppDataB1 = other.getIssAppDataB1();
        if (this$issAppDataB1 == null ? other$issAppDataB1 != null : !this$issAppDataB1.equals(other$issAppDataB1)) {
            return false;
        }
        String this$issAppDataB17 = this.getIssAppDataB17();
        String other$issAppDataB17 = other.getIssAppDataB17();
        if (this$issAppDataB17 == null ? other$issAppDataB17 != null : !this$issAppDataB17.equals(other$issAppDataB17)) {
            return false;
        }
        String this$issAppDataB18 = this.getIssAppDataB18();
        String other$issAppDataB18 = other.getIssAppDataB18();
        if (this$issAppDataB18 == null ? other$issAppDataB18 != null : !this$issAppDataB18.equals(other$issAppDataB18)) {
            return false;
        }
        String this$trlVerResult = this.getTrlVerResult();
        String other$trlVerResult = other.getTrlVerResult();
        if (this$trlVerResult == null ? other$trlVerResult != null : !this$trlVerResult.equals(other$trlVerResult)) {
            return false;
        }
        String this$formFactorIndicator = this.getFormFactorIndicator();
        String other$formFactorIndicator = other.getFormFactorIndicator();
        if (this$formFactorIndicator == null ? other$formFactorIndicator != null : !this$formFactorIndicator.equals(other$formFactorIndicator)) {
            return false;
        }
        String this$issScriptResult = this.getIssScriptResult();
        String other$issScriptResult = other.getIssScriptResult();
        if (this$issScriptResult == null ? other$issScriptResult != null : !this$issScriptResult.equals(other$issScriptResult)) {
            return false;
        }
        String this$serviceCode = this.getServiceCode();
        String other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null ? other$serviceCode != null : !this$serviceCode.equals(other$serviceCode)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$senderName = this.getSenderName();
        String other$senderName = other.getSenderName();
        if (this$senderName == null ? other$senderName != null : !this$senderName.equals(other$senderName)) {
            return false;
        }
        String this$recipientName = this.getRecipientName();
        String other$recipientName = other.getRecipientName();
        if (this$recipientName == null ? other$recipientName != null : !this$recipientName.equals(other$recipientName)) {
            return false;
        }
        String this$bussAppId = this.getBussAppId();
        String other$bussAppId = other.getBussAppId();
        if (this$bussAppId == null ? other$bussAppId != null : !this$bussAppId.equals(other$bussAppId)) {
            return false;
        }
        String this$senderAccount = this.getSenderAccount();
        String other$senderAccount = other.getSenderAccount();
        if (this$senderAccount == null ? other$senderAccount != null : !this$senderAccount.equals(other$senderAccount)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$dccCurrency = this.getDccCurrency();
        String other$dccCurrency = other.getDccCurrency();
        return !(this$dccCurrency == null ? other$dccCurrency != null : !this$dccCurrency.equals(other$dccCurrency));
    }

    protected boolean canEqual(Object other) {
        return other instanceof VisaAcqTxnWorkEntity;
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
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        Integer $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + ($txnRefNumber == null ? 43 : ((Object)$txnRefNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $schgAmount = this.getSchgAmount();
        result = result * 59 + ($schgAmount == null ? 43 : ((Object)$schgAmount).hashCode());
        Character $chIdMethod = this.getChIdMethod();
        result = result * 59 + ($chIdMethod == null ? 43 : ((Object)$chIdMethod).hashCode());
        Character $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + ($motoEcomIndicator == null ? 43 : ((Object)$motoEcomIndicator).hashCode());
        Character $accSelection = this.getAccSelection();
        result = result * 59 + ($accSelection == null ? 43 : ((Object)$accSelection).hashCode());
        Character $posEnvironment = this.getPosEnvironment();
        result = result * 59 + ($posEnvironment == null ? 43 : ((Object)$posEnvironment).hashCode());
        Double $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : ((Object)$cryptAmount).hashCode());
        Double $cashbackAmount = this.getCashbackAmount();
        result = result * 59 + ($cashbackAmount == null ? 43 : ((Object)$cashbackAmount).hashCode());
        Character $visaToken = this.getVisaToken();
        result = result * 59 + ($visaToken == null ? 43 : ((Object)$visaToken).hashCode());
        Character $authCharIndicator = this.getAuthCharIndicator();
        result = result * 59 + ($authCharIndicator == null ? 43 : ((Object)$authCharIndicator).hashCode());
        Character $accFundSource = this.getAccFundSource();
        result = result * 59 + ($accFundSource == null ? 43 : ((Object)$accFundSource).hashCode());
        Character $marketSpecDataInd = this.getMarketSpecDataInd();
        result = result * 59 + ($marketSpecDataInd == null ? 43 : ((Object)$marketSpecDataInd).hashCode());
        Character $spendQualiIndictor = this.getSpendQualiIndictor();
        result = result * 59 + ($spendQualiIndictor == null ? 43 : ((Object)$spendQualiIndictor).hashCode());
        Character $collOnlyFlag = this.getCollOnlyFlag();
        result = result * 59 + ($collOnlyFlag == null ? 43 : ((Object)$collOnlyFlag).hashCode());
        Character $usageCode = this.getUsageCode();
        result = result * 59 + ($usageCode == null ? 43 : ((Object)$usageCode).hashCode());
        Character $setlFlag = this.getSetlFlag();
        result = result * 59 + ($setlFlag == null ? 43 : ((Object)$setlFlag).hashCode());
        Character $terminalCapability = this.getTerminalCapability();
        result = result * 59 + ($terminalCapability == null ? 43 : ((Object)$terminalCapability).hashCode());
        Character $reimAttribute = this.getReimAttribute();
        result = result * 59 + ($reimAttribute == null ? 43 : ((Object)$reimAttribute).hashCode());
        Double $authAmount = this.getAuthAmount();
        result = result * 59 + ($authAmount == null ? 43 : ((Object)$authAmount).hashCode());
        Double $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + ($txnFeeAmount == null ? 43 : ((Object)$txnFeeAmount).hashCode());
        Character $dccIndicator = this.getDccIndicator();
        result = result * 59 + ($dccIndicator == null ? 43 : ((Object)$dccIndicator).hashCode());
        Character $domIntlFlag = this.getDomIntlFlag();
        result = result * 59 + ($domIntlFlag == null ? 43 : ((Object)$domIntlFlag).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Double $dccAmount = this.getDccAmount();
        result = result * 59 + ($dccAmount == null ? 43 : ((Object)$dccAmount).hashCode());
        Character $acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        result = result * 59 + ($acceptanceTrlIndicator == null ? 43 : ((Object)$acceptanceTrlIndicator).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $encCardNumber = this.getEncCardNumber();
        result = result * 59 + ($encCardNumber == null ? 43 : $encCardNumber.hashCode());
        String $arn = this.getArn();
        result = result * 59 + ($arn == null ? 43 : $arn.hashCode());
        LocalDateTime $purchaseDate = this.getPurchaseDate();
        result = result * 59 + ($purchaseDate == null ? 43 : ((Object)$purchaseDate).hashCode());
        String $txnCurCode = this.getTxnCurCode();
        result = result * 59 + ($txnCurCode == null ? 43 : $txnCurCode.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meCountry = this.getMeCountry();
        result = result * 59 + ($meCountry == null ? 43 : $meCountry.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $feePrgIndicator = this.getFeePrgIndicator();
        result = result * 59 + ($feePrgIndicator == null ? 43 : $feePrgIndicator.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $acqBussId = this.getAcqBussId();
        result = result * 59 + ($acqBussId == null ? 43 : $acqBussId.hashCode());
        String $respCode = this.getRespCode();
        result = result * 59 + ($respCode == null ? 43 : $respCode.hashCode());
        LocalDate $trlTxnDate = this.getTrlTxnDate();
        result = result * 59 + ($trlTxnDate == null ? 43 : ((Object)$trlTxnDate).hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $productId = this.getProductId();
        result = result * 59 + ($productId == null ? 43 : $productId.hashCode());
        String $validationCode = this.getValidationCode();
        result = result * 59 + ($validationCode == null ? 43 : $validationCode.hashCode());
        String $retRefNumber = this.getRetRefNumber();
        result = result * 59 + ($retRefNumber == null ? 43 : $retRefNumber.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $reasonCode = this.getReasonCode();
        result = result * 59 + ($reasonCode == null ? 43 : $reasonCode.hashCode());
        String $stan = this.getStan();
        result = result * 59 + ($stan == null ? 43 : $stan.hashCode());
        String $trlCapProfile = this.getTrlCapProfile();
        result = result * 59 + ($trlCapProfile == null ? 43 : $trlCapProfile.hashCode());
        String $trlCountryCode = this.getTrlCountryCode();
        result = result * 59 + ($trlCountryCode == null ? 43 : $trlCountryCode.hashCode());
        String $upblNumber = this.getUpblNumber();
        result = result * 59 + ($upblNumber == null ? 43 : $upblNumber.hashCode());
        String $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + ($cardSeqNumber == null ? 43 : $cardSeqNumber.hashCode());
        String $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + ($appTxnCounter == null ? 43 : $appTxnCounter.hashCode());
        String $appIcProfile = this.getAppIcProfile();
        result = result * 59 + ($appIcProfile == null ? 43 : $appIcProfile.hashCode());
        String $appCryptogram = this.getAppCryptogram();
        result = result * 59 + ($appCryptogram == null ? 43 : $appCryptogram.hashCode());
        String $issAppDataB2 = this.getIssAppDataB2();
        result = result * 59 + ($issAppDataB2 == null ? 43 : $issAppDataB2.hashCode());
        String $issAppDataB3 = this.getIssAppDataB3();
        result = result * 59 + ($issAppDataB3 == null ? 43 : $issAppDataB3.hashCode());
        String $issAppDataB4 = this.getIssAppDataB4();
        result = result * 59 + ($issAppDataB4 == null ? 43 : $issAppDataB4.hashCode());
        String $issAppDataB8 = this.getIssAppDataB8();
        result = result * 59 + ($issAppDataB8 == null ? 43 : $issAppDataB8.hashCode());
        String $issAppDataB9 = this.getIssAppDataB9();
        result = result * 59 + ($issAppDataB9 == null ? 43 : $issAppDataB9.hashCode());
        String $issAppDataB1 = this.getIssAppDataB1();
        result = result * 59 + ($issAppDataB1 == null ? 43 : $issAppDataB1.hashCode());
        String $issAppDataB17 = this.getIssAppDataB17();
        result = result * 59 + ($issAppDataB17 == null ? 43 : $issAppDataB17.hashCode());
        String $issAppDataB18 = this.getIssAppDataB18();
        result = result * 59 + ($issAppDataB18 == null ? 43 : $issAppDataB18.hashCode());
        String $trlVerResult = this.getTrlVerResult();
        result = result * 59 + ($trlVerResult == null ? 43 : $trlVerResult.hashCode());
        String $formFactorIndicator = this.getFormFactorIndicator();
        result = result * 59 + ($formFactorIndicator == null ? 43 : $formFactorIndicator.hashCode());
        String $issScriptResult = this.getIssScriptResult();
        result = result * 59 + ($issScriptResult == null ? 43 : $issScriptResult.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $senderName = this.getSenderName();
        result = result * 59 + ($senderName == null ? 43 : $senderName.hashCode());
        String $recipientName = this.getRecipientName();
        result = result * 59 + ($recipientName == null ? 43 : $recipientName.hashCode());
        String $bussAppId = this.getBussAppId();
        result = result * 59 + ($bussAppId == null ? 43 : $bussAppId.hashCode());
        String $senderAccount = this.getSenderAccount();
        result = result * 59 + ($senderAccount == null ? 43 : $senderAccount.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $dccCurrency = this.getDccCurrency();
        result = result * 59 + ($dccCurrency == null ? 43 : $dccCurrency.hashCode());
        return result;
    }

    public String toString() {
        return "VisaAcqTxnWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", genStatus=" + this.getGenStatus() + ", txnRefNumber=" + this.getTxnRefNumber() + ", txnType=" + this.getTxnType() + ", txnCode=" + this.getTxnCode() + ", encCardNumber=" + this.getEncCardNumber() + ", arn=" + this.getArn() + ", purchaseDate=" + String.valueOf(this.getPurchaseDate()) + ", txnCurCode=" + this.getTxnCurCode() + ", txnAmount=" + this.getTxnAmount() + ", schgAmount=" + this.getSchgAmount() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", mcc=" + this.getMcc() + ", approvalCode=" + this.getApprovalCode() + ", chIdMethod=" + this.getChIdMethod() + ", posEntryMode=" + this.getPosEntryMode() + ", memberText=" + this.getMemberText() + ", feePrgIndicator=" + this.getFeePrgIndicator() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", accSelection=" + this.getAccSelection() + ", acqBussId=" + this.getAcqBussId() + ", posEnvironment=" + this.getPosEnvironment() + ", respCode=" + this.getRespCode() + ", trlTxnDate=" + String.valueOf(this.getTrlTxnDate()) + ", cryptAmount=" + this.getCryptAmount() + ", cashbackAmount=" + this.getCashbackAmount() + ", txnId=" + this.getTxnId() + ", visaToken=" + this.getVisaToken() + ", authCharIndicator=" + this.getAuthCharIndicator() + ", accFundSource=" + this.getAccFundSource() + ", marketSpecDataInd=" + this.getMarketSpecDataInd() + ", productId=" + this.getProductId() + ", validationCode=" + this.getValidationCode() + ", spendQualiIndictor=" + this.getSpendQualiIndictor() + ", collOnlyFlag=" + this.getCollOnlyFlag() + ", retRefNumber=" + this.getRetRefNumber() + ", procCode=" + this.getProcCode() + ", usageCode=" + this.getUsageCode() + ", reasonCode=" + this.getReasonCode() + ", setlFlag=" + this.getSetlFlag() + ", terminalCapability=" + this.getTerminalCapability() + ", reimAttribute=" + this.getReimAttribute() + ", stan=" + this.getStan() + ", authAmount=" + this.getAuthAmount() + ", trlCapProfile=" + this.getTrlCapProfile() + ", trlCountryCode=" + this.getTrlCountryCode() + ", upblNumber=" + this.getUpblNumber() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", appIcProfile=" + this.getAppIcProfile() + ", appCryptogram=" + this.getAppCryptogram() + ", issAppDataB2=" + this.getIssAppDataB2() + ", issAppDataB3=" + this.getIssAppDataB3() + ", issAppDataB4=" + this.getIssAppDataB4() + ", issAppDataB8=" + this.getIssAppDataB8() + ", issAppDataB9=" + this.getIssAppDataB9() + ", issAppDataB1=" + this.getIssAppDataB1() + ", issAppDataB17=" + this.getIssAppDataB17() + ", issAppDataB18=" + this.getIssAppDataB18() + ", trlVerResult=" + this.getTrlVerResult() + ", formFactorIndicator=" + this.getFormFactorIndicator() + ", issScriptResult=" + this.getIssScriptResult() + ", serviceCode=" + this.getServiceCode() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", fileId=" + this.getFileId() + ", senderName=" + this.getSenderName() + ", recipientName=" + this.getRecipientName() + ", bussAppId=" + this.getBussAppId() + ", senderAccount=" + this.getSenderAccount() + ", dccIndicator=" + this.getDccIndicator() + ", network=" + this.getNetwork() + ", domIntlFlag=" + this.getDomIntlFlag() + ", cardType=" + this.getCardType() + ", dccCurrency=" + this.getDccCurrency() + ", dccAmount=" + this.getDccAmount() + ", acceptanceTrlIndicator=" + this.getAcceptanceTrlIndicator() + ")";
    }

    public VisaAcqTxnWorkEntity() {
    }

    public VisaAcqTxnWorkEntity(Integer serNumber, LocalDateTime lastUpdated, Integer updatedUser, Integer institutionCode, Integer intCode, Integer prjSerNumber, Integer genStatus, Integer txnRefNumber, String txnType, String txnCode, String encCardNumber, String arn, LocalDateTime purchaseDate, String txnCurCode, Double txnAmount, Double schgAmount, String meName, String meCity, String meCountry, String mcc, String approvalCode, Character chIdMethod, String posEntryMode, String memberText, String feePrgIndicator, String merchantId, String terminalId, Character motoEcomIndicator, Character accSelection, String acqBussId, Character posEnvironment, String respCode, LocalDate trlTxnDate, Double cryptAmount, Double cashbackAmount, String txnId, Character visaToken, Character authCharIndicator, Character accFundSource, Character marketSpecDataInd, String productId, String validationCode, Character spendQualiIndictor, Character collOnlyFlag, String retRefNumber, String procCode, Character usageCode, String reasonCode, Character setlFlag, Character terminalCapability, Character reimAttribute, String stan, Double authAmount, String trlCapProfile, String trlCountryCode, String upblNumber, String cardSeqNumber, String appTxnCounter, String appIcProfile, String appCryptogram, String issAppDataB2, String issAppDataB3, String issAppDataB4, String issAppDataB8, String issAppDataB9, String issAppDataB1, String issAppDataB17, String issAppDataB18, String trlVerResult, String formFactorIndicator, String issScriptResult, String serviceCode, Double txnFeeAmount, String fileId, String senderName, String recipientName, String bussAppId, String senderAccount, Character dccIndicator, String network, Character domIntlFlag, Character cardType, String dccCurrency, Double dccAmount, Character acceptanceTrlIndicator) {
        this.serNumber = serNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.intCode = intCode;
        this.prjSerNumber = prjSerNumber;
        this.genStatus = genStatus;
        this.txnRefNumber = txnRefNumber;
        this.txnType = txnType;
        this.txnCode = txnCode;
        this.encCardNumber = encCardNumber;
        this.arn = arn;
        this.purchaseDate = purchaseDate;
        this.txnCurCode = txnCurCode;
        this.txnAmount = txnAmount;
        this.schgAmount = schgAmount;
        this.meName = meName;
        this.meCity = meCity;
        this.meCountry = meCountry;
        this.mcc = mcc;
        this.approvalCode = approvalCode;
        this.chIdMethod = chIdMethod;
        this.posEntryMode = posEntryMode;
        this.memberText = memberText;
        this.feePrgIndicator = feePrgIndicator;
        this.merchantId = merchantId;
        this.terminalId = terminalId;
        this.motoEcomIndicator = motoEcomIndicator;
        this.accSelection = accSelection;
        this.acqBussId = acqBussId;
        this.posEnvironment = posEnvironment;
        this.respCode = respCode;
        this.trlTxnDate = trlTxnDate;
        this.cryptAmount = cryptAmount;
        this.cashbackAmount = cashbackAmount;
        this.txnId = txnId;
        this.visaToken = visaToken;
        this.authCharIndicator = authCharIndicator;
        this.accFundSource = accFundSource;
        this.marketSpecDataInd = marketSpecDataInd;
        this.productId = productId;
        this.validationCode = validationCode;
        this.spendQualiIndictor = spendQualiIndictor;
        this.collOnlyFlag = collOnlyFlag;
        this.retRefNumber = retRefNumber;
        this.procCode = procCode;
        this.usageCode = usageCode;
        this.reasonCode = reasonCode;
        this.setlFlag = setlFlag;
        this.terminalCapability = terminalCapability;
        this.reimAttribute = reimAttribute;
        this.stan = stan;
        this.authAmount = authAmount;
        this.trlCapProfile = trlCapProfile;
        this.trlCountryCode = trlCountryCode;
        this.upblNumber = upblNumber;
        this.cardSeqNumber = cardSeqNumber;
        this.appTxnCounter = appTxnCounter;
        this.appIcProfile = appIcProfile;
        this.appCryptogram = appCryptogram;
        this.issAppDataB2 = issAppDataB2;
        this.issAppDataB3 = issAppDataB3;
        this.issAppDataB4 = issAppDataB4;
        this.issAppDataB8 = issAppDataB8;
        this.issAppDataB9 = issAppDataB9;
        this.issAppDataB1 = issAppDataB1;
        this.issAppDataB17 = issAppDataB17;
        this.issAppDataB18 = issAppDataB18;
        this.trlVerResult = trlVerResult;
        this.formFactorIndicator = formFactorIndicator;
        this.issScriptResult = issScriptResult;
        this.serviceCode = serviceCode;
        this.txnFeeAmount = txnFeeAmount;
        this.fileId = fileId;
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.bussAppId = bussAppId;
        this.senderAccount = senderAccount;
        this.dccIndicator = dccIndicator;
        this.network = network;
        this.domIntlFlag = domIntlFlag;
        this.cardType = cardType;
        this.dccCurrency = dccCurrency;
        this.dccAmount = dccAmount;
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
    }
}

