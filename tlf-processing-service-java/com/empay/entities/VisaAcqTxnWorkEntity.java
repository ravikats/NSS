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
@Table(name = "VISA_ACQ_TXN_WORK")
public class VisaAcqTxnWorkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VTD_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "VTD_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "VTD_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "VTD_INS_CODE")
    private int institutionCode;
    @Column(name = "VTD_INT_CODE")
    private Integer intCode;
    @Column(name = "VTD_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "VTD_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "VTD_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name = "VTD_TXN_TYPE")
    private String txnType;
    @Column(name = "VTD_TXN_CODE")
    private String txnCode;
    @Column(name = "VTD_ENC_CARD_NUMBER")
    private String encCardNumber;
    @Column(name = "VTD_ACQ_REF_NUMBER")
    private String arn;
    @Column(name = "VTD_PURCHASE_DATE")
    private LocalDateTime purchaseDate;
    @Column(name = "VTD_TXN_CUR_CODE")
    private String txnCurCode;
    @Column(name = "VTD_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "VTD_SCHG_AMOUNT")
    private Double schgAmount;
    @Column(name = "VTD_ME_NAME")
    private String meName;
    @Column(name = "VTD_ME_CITY")
    private String meCity;
    @Column(name = "VTD_ME_COUNTRY")
    private String meCountry;
    @Column(name = "VTD_MCC")
    private String mcc;
    @Column(name = "VTD_APPR_CODE")
    private String approvalCode;
    @Column(name = "VTD_CH_ID_METHOD")
    private Character chIdMethod;
    @Column(name = "VTD_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name = "VTD_MEMBER_TEXT")
    private String memberText;
    @Column(name = "VTD_FEE_PRG_INDICATOR")
    private String feePrgIndicator;
    @Column(name = "VTD_MERCHANT_ID")
    private String merchantId;
    @Column(name = "VTD_TERMINAL_ID")
    private String terminalId;
    @Column(name = "VTD_MOTO_ECOM_INDICATOR")
    private Character motoEcomIndicator;
    @Column(name = "VTD_ACC_SELECTION")
    private Character accSelection;
    @Column(name = "VTD_ACQ_BUSS_ID")
    private String acqBussId;
    @Column(name = "VTD_POS_ENVIRONMENT")
    private Character posEnvironment;
    @Column(name = "VTD_RESP_CODE")
    private String respCode;
    @Column(name = "VTD_TRL_TXN_DATE")
    private LocalDate trlTxnDate;
    @Column(name = "VTD_CRYPT_AMOUNT")
    private Double cryptAmount;
    @Column(name = "VTD_CASHBACK_AMOUNT")
    private Double cashbackAmount;
    @Column(name = "VTD_TXN_ID")
    private String txnId;
    @Column(name = "VTD_VISA_TOKEN")
    private Character visaToken;
    @Column(name = "VTD_AUTH_CHAR_INDICATOR")
    private Character authCharIndicator;
    @Column(name = "VTD_ACC_FUND_SOURCE")
    private Character accFundSource;
    @Column(name = "VTD_MARKET_SPEC_DATA_IND")
    private Character marketSpecDataInd;
    @Column(name = "VTD_PRODUCT_ID")
    private String productId;
    @Column(name = "VTD_VALIDATION_CODE")
    private String validationCode;
    @Column(name = "VTD_SPEND_QUALI_IND")
    private Character spendQualiIndictor;
    @Column(name = "VTD_COLL_ONLY_FLAG")
    private Character collOnlyFlag;
    @Column(name = "VTD_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name = "VTD_PROC_CODE")
    private String procCode;
    @Column(name = "VTD_USAGE_CODE")
    private Character usageCode;
    @Column(name = "VTD_REASON_CODE")
    private String reasonCode;
    @Column(name = "VTD_SETL_FLAG")
    private Character setlFlag;
    @Column(name = "VTD_TRL_CAPABILITY")
    private Character terminalCapability;
    @Column(name = "VTD_REIMB_ATTRIBUTE")
    private Character reimAttribute;
    @Column(name = "VTD_STAN")
    private String stan;
    @Column(name = "VTD_AUTH_AMOUNT")
    private Double authAmount;
    @Column(name = "VTD_TRL_CAP_PROFILE")
    private String trlCapProfile;
    @Column(name = "VTD_TRL_CON_CODE")
    private String trlCountryCode;
    @Column(name = "VTD_UPBL_NUMBER")
    private String upblNumber;
    @Column(name = "VTD_CARD_SEQ_NUMBER")
    private String cardSeqNumber;
    @Column(name = "VTD_APP_TXN_COUNTER")
    private String appTxnCounter;
    @Column(name = "VTD_APP_IC_PROFILE")
    private String appIcProfile;
    @Column(name = "VTD_APP_CRYPTOGRAM")
    private String appCryptogram;
    @Column(name = "VTD_ISS_APP_DATA_B2")
    private String issAppDataB2;
    @Column(name = "VTD_ISS_APP_DATA_B3")
    private String issAppDataB3;
    @Column(name = "VTD_ISS_APP_DATA_B4")
    private String issAppDataB4;
    @Column(name = "VTD_ISS_APP_DATA_B8")
    private String issAppDataB8;
    @Column(name = "VTD_ISS_APP_DATA_B9")
    private String issAppDataB9;
    @Column(name = "VTD_ISS_APP_DATA_B1")
    private String issAppDataB1;
    @Column(name = "VTD_ISS_APP_DATA_B17")
    private String issAppDataB17;
    @Column(name = "VTD_ISS_APP_DATA_B18")
    private String issAppDataB18;
    @Column(name = "VTD_TRL_VER_RESULTS")
    private String trlVerResult;
    @Column(name = "VTD_FORM_FACT_INDICATOR")
    private String formFactorIndicator;
    @Column(name = "VTD_ISS_SCRIPT_RESULTS")
    private String issScriptResult;
    @Column(name = "VTD_SERVICE_CODE")
    private String serviceCode;
    @Column(name = "VTD_TXN_FEE_AMOUNT")
    private Double txnFeeAmount;
    @Column(name = "VTD_DCC_INDICATOR")
    private Character dccIndicator;
    @Column(name = "VTD_NETWORK")
    private String network;
    @Column(name = "VTD_SMS_DMS_FLAG")
    private Character smsDmsFlag;
    @Column(name = "VTD_DOM_INTL_FLAG")
    private Character domIntlFlag;
    @Column(name = "VTD_CARD_TYPE")
    private Character cardType;
    @Column(name = "VTD_DCC_CURRENCY")
    private String dccCurrency;
    @Column(name = "VTD_DCC_AMOUNT")
    private Double dccAmount;
    @Column(name = "VTD_ACC_TRL_INDICATOR")
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
    
    public int getInstitutionCode() {
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
    
    public Character getDccIndicator() {
        return this.dccIndicator;
    }
    
    public String getNetwork() {
        return this.network;
    }
    
    public Character getSmsDmsFlag() {
        return this.smsDmsFlag;
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
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
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
    
    public void setEncCardNumber(final String encCardNumber) {
        this.encCardNumber = encCardNumber;
    }
    
    public void setArn(final String arn) {
        this.arn = arn;
    }
    
    public void setPurchaseDate(final LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public void setTxnCurCode(final String txnCurCode) {
        this.txnCurCode = txnCurCode;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setSchgAmount(final Double schgAmount) {
        this.schgAmount = schgAmount;
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
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setChIdMethod(final Character chIdMethod) {
        this.chIdMethod = chIdMethod;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    public void setMemberText(final String memberText) {
        this.memberText = memberText;
    }
    
    public void setFeePrgIndicator(final String feePrgIndicator) {
        this.feePrgIndicator = feePrgIndicator;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setMotoEcomIndicator(final Character motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setAccSelection(final Character accSelection) {
        this.accSelection = accSelection;
    }
    
    public void setAcqBussId(final String acqBussId) {
        this.acqBussId = acqBussId;
    }
    
    public void setPosEnvironment(final Character posEnvironment) {
        this.posEnvironment = posEnvironment;
    }
    
    public void setRespCode(final String respCode) {
        this.respCode = respCode;
    }
    
    public void setTrlTxnDate(final LocalDate trlTxnDate) {
        this.trlTxnDate = trlTxnDate;
    }
    
    public void setCryptAmount(final Double cryptAmount) {
        this.cryptAmount = cryptAmount;
    }
    
    public void setCashbackAmount(final Double cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }
    
    public void setTxnId(final String txnId) {
        this.txnId = txnId;
    }
    
    public void setVisaToken(final Character visaToken) {
        this.visaToken = visaToken;
    }
    
    public void setAuthCharIndicator(final Character authCharIndicator) {
        this.authCharIndicator = authCharIndicator;
    }
    
    public void setAccFundSource(final Character accFundSource) {
        this.accFundSource = accFundSource;
    }
    
    public void setMarketSpecDataInd(final Character marketSpecDataInd) {
        this.marketSpecDataInd = marketSpecDataInd;
    }
    
    public void setProductId(final String productId) {
        this.productId = productId;
    }
    
    public void setValidationCode(final String validationCode) {
        this.validationCode = validationCode;
    }
    
    public void setSpendQualiIndictor(final Character spendQualiIndictor) {
        this.spendQualiIndictor = spendQualiIndictor;
    }
    
    public void setCollOnlyFlag(final Character collOnlyFlag) {
        this.collOnlyFlag = collOnlyFlag;
    }
    
    public void setRetRefNumber(final String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
    }
    
    public void setUsageCode(final Character usageCode) {
        this.usageCode = usageCode;
    }
    
    public void setReasonCode(final String reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public void setSetlFlag(final Character setlFlag) {
        this.setlFlag = setlFlag;
    }
    
    public void setTerminalCapability(final Character terminalCapability) {
        this.terminalCapability = terminalCapability;
    }
    
    public void setReimAttribute(final Character reimAttribute) {
        this.reimAttribute = reimAttribute;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public void setAuthAmount(final Double authAmount) {
        this.authAmount = authAmount;
    }
    
    public void setTrlCapProfile(final String trlCapProfile) {
        this.trlCapProfile = trlCapProfile;
    }
    
    public void setTrlCountryCode(final String trlCountryCode) {
        this.trlCountryCode = trlCountryCode;
    }
    
    public void setUpblNumber(final String upblNumber) {
        this.upblNumber = upblNumber;
    }
    
    public void setCardSeqNumber(final String cardSeqNumber) {
        this.cardSeqNumber = cardSeqNumber;
    }
    
    public void setAppTxnCounter(final String appTxnCounter) {
        this.appTxnCounter = appTxnCounter;
    }
    
    public void setAppIcProfile(final String appIcProfile) {
        this.appIcProfile = appIcProfile;
    }
    
    public void setAppCryptogram(final String appCryptogram) {
        this.appCryptogram = appCryptogram;
    }
    
    public void setIssAppDataB2(final String issAppDataB2) {
        this.issAppDataB2 = issAppDataB2;
    }
    
    public void setIssAppDataB3(final String issAppDataB3) {
        this.issAppDataB3 = issAppDataB3;
    }
    
    public void setIssAppDataB4(final String issAppDataB4) {
        this.issAppDataB4 = issAppDataB4;
    }
    
    public void setIssAppDataB8(final String issAppDataB8) {
        this.issAppDataB8 = issAppDataB8;
    }
    
    public void setIssAppDataB9(final String issAppDataB9) {
        this.issAppDataB9 = issAppDataB9;
    }
    
    public void setIssAppDataB1(final String issAppDataB1) {
        this.issAppDataB1 = issAppDataB1;
    }
    
    public void setIssAppDataB17(final String issAppDataB17) {
        this.issAppDataB17 = issAppDataB17;
    }
    
    public void setIssAppDataB18(final String issAppDataB18) {
        this.issAppDataB18 = issAppDataB18;
    }
    
    public void setTrlVerResult(final String trlVerResult) {
        this.trlVerResult = trlVerResult;
    }
    
    public void setFormFactorIndicator(final String formFactorIndicator) {
        this.formFactorIndicator = formFactorIndicator;
    }
    
    public void setIssScriptResult(final String issScriptResult) {
        this.issScriptResult = issScriptResult;
    }
    
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setTxnFeeAmount(final Double txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }
    
    public void setDccIndicator(final Character dccIndicator) {
        this.dccIndicator = dccIndicator;
    }
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setSmsDmsFlag(final Character smsDmsFlag) {
        this.smsDmsFlag = smsDmsFlag;
    }
    
    public void setDomIntlFlag(final Character domIntlFlag) {
        this.domIntlFlag = domIntlFlag;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setDccCurrency(final String dccCurrency) {
        this.dccCurrency = dccCurrency;
    }
    
    public void setDccAmount(final Double dccAmount) {
        this.dccAmount = dccAmount;
    }
    
    public void setAcceptanceTrlIndicator(final Character acceptanceTrlIndicator) {
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaAcqTxnWorkEntity)) {
            return false;
        }
        final VisaAcqTxnWorkEntity other = (VisaAcqTxnWorkEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0078: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0078;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0115: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0115;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0115;
            }
            return false;
        }
        final Object this$intCode = this.getIntCode();
        final Object other$intCode = other.getIntCode();
        Label_0152: {
            if (this$intCode == null) {
                if (other$intCode == null) {
                    break Label_0152;
                }
            }
            else if (this$intCode.equals(other$intCode)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$prjSerNumber = this.getPrjSerNumber();
        final Object other$prjSerNumber = other.getPrjSerNumber();
        Label_0189: {
            if (this$prjSerNumber == null) {
                if (other$prjSerNumber == null) {
                    break Label_0189;
                }
            }
            else if (this$prjSerNumber.equals(other$prjSerNumber)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$genStatus = this.getGenStatus();
        final Object other$genStatus = other.getGenStatus();
        Label_0226: {
            if (this$genStatus == null) {
                if (other$genStatus == null) {
                    break Label_0226;
                }
            }
            else if (this$genStatus.equals(other$genStatus)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$txnRefNumber = this.getTxnRefNumber();
        final Object other$txnRefNumber = other.getTxnRefNumber();
        Label_0263: {
            if (this$txnRefNumber == null) {
                if (other$txnRefNumber == null) {
                    break Label_0263;
                }
            }
            else if (this$txnRefNumber.equals(other$txnRefNumber)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$txnAmount = this.getTxnAmount();
        final Object other$txnAmount = other.getTxnAmount();
        Label_0300: {
            if (this$txnAmount == null) {
                if (other$txnAmount == null) {
                    break Label_0300;
                }
            }
            else if (this$txnAmount.equals(other$txnAmount)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$schgAmount = this.getSchgAmount();
        final Object other$schgAmount = other.getSchgAmount();
        Label_0337: {
            if (this$schgAmount == null) {
                if (other$schgAmount == null) {
                    break Label_0337;
                }
            }
            else if (this$schgAmount.equals(other$schgAmount)) {
                break Label_0337;
            }
            return false;
        }
        final Object this$chIdMethod = this.getChIdMethod();
        final Object other$chIdMethod = other.getChIdMethod();
        Label_0374: {
            if (this$chIdMethod == null) {
                if (other$chIdMethod == null) {
                    break Label_0374;
                }
            }
            else if (this$chIdMethod.equals(other$chIdMethod)) {
                break Label_0374;
            }
            return false;
        }
        final Object this$motoEcomIndicator = this.getMotoEcomIndicator();
        final Object other$motoEcomIndicator = other.getMotoEcomIndicator();
        Label_0411: {
            if (this$motoEcomIndicator == null) {
                if (other$motoEcomIndicator == null) {
                    break Label_0411;
                }
            }
            else if (this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
                break Label_0411;
            }
            return false;
        }
        final Object this$accSelection = this.getAccSelection();
        final Object other$accSelection = other.getAccSelection();
        Label_0448: {
            if (this$accSelection == null) {
                if (other$accSelection == null) {
                    break Label_0448;
                }
            }
            else if (this$accSelection.equals(other$accSelection)) {
                break Label_0448;
            }
            return false;
        }
        final Object this$posEnvironment = this.getPosEnvironment();
        final Object other$posEnvironment = other.getPosEnvironment();
        Label_0485: {
            if (this$posEnvironment == null) {
                if (other$posEnvironment == null) {
                    break Label_0485;
                }
            }
            else if (this$posEnvironment.equals(other$posEnvironment)) {
                break Label_0485;
            }
            return false;
        }
        final Object this$cryptAmount = this.getCryptAmount();
        final Object other$cryptAmount = other.getCryptAmount();
        Label_0522: {
            if (this$cryptAmount == null) {
                if (other$cryptAmount == null) {
                    break Label_0522;
                }
            }
            else if (this$cryptAmount.equals(other$cryptAmount)) {
                break Label_0522;
            }
            return false;
        }
        final Object this$cashbackAmount = this.getCashbackAmount();
        final Object other$cashbackAmount = other.getCashbackAmount();
        Label_0559: {
            if (this$cashbackAmount == null) {
                if (other$cashbackAmount == null) {
                    break Label_0559;
                }
            }
            else if (this$cashbackAmount.equals(other$cashbackAmount)) {
                break Label_0559;
            }
            return false;
        }
        final Object this$visaToken = this.getVisaToken();
        final Object other$visaToken = other.getVisaToken();
        Label_0596: {
            if (this$visaToken == null) {
                if (other$visaToken == null) {
                    break Label_0596;
                }
            }
            else if (this$visaToken.equals(other$visaToken)) {
                break Label_0596;
            }
            return false;
        }
        final Object this$authCharIndicator = this.getAuthCharIndicator();
        final Object other$authCharIndicator = other.getAuthCharIndicator();
        Label_0633: {
            if (this$authCharIndicator == null) {
                if (other$authCharIndicator == null) {
                    break Label_0633;
                }
            }
            else if (this$authCharIndicator.equals(other$authCharIndicator)) {
                break Label_0633;
            }
            return false;
        }
        final Object this$accFundSource = this.getAccFundSource();
        final Object other$accFundSource = other.getAccFundSource();
        Label_0670: {
            if (this$accFundSource == null) {
                if (other$accFundSource == null) {
                    break Label_0670;
                }
            }
            else if (this$accFundSource.equals(other$accFundSource)) {
                break Label_0670;
            }
            return false;
        }
        final Object this$marketSpecDataInd = this.getMarketSpecDataInd();
        final Object other$marketSpecDataInd = other.getMarketSpecDataInd();
        Label_0707: {
            if (this$marketSpecDataInd == null) {
                if (other$marketSpecDataInd == null) {
                    break Label_0707;
                }
            }
            else if (this$marketSpecDataInd.equals(other$marketSpecDataInd)) {
                break Label_0707;
            }
            return false;
        }
        final Object this$spendQualiIndictor = this.getSpendQualiIndictor();
        final Object other$spendQualiIndictor = other.getSpendQualiIndictor();
        Label_0744: {
            if (this$spendQualiIndictor == null) {
                if (other$spendQualiIndictor == null) {
                    break Label_0744;
                }
            }
            else if (this$spendQualiIndictor.equals(other$spendQualiIndictor)) {
                break Label_0744;
            }
            return false;
        }
        final Object this$collOnlyFlag = this.getCollOnlyFlag();
        final Object other$collOnlyFlag = other.getCollOnlyFlag();
        Label_0781: {
            if (this$collOnlyFlag == null) {
                if (other$collOnlyFlag == null) {
                    break Label_0781;
                }
            }
            else if (this$collOnlyFlag.equals(other$collOnlyFlag)) {
                break Label_0781;
            }
            return false;
        }
        final Object this$usageCode = this.getUsageCode();
        final Object other$usageCode = other.getUsageCode();
        Label_0818: {
            if (this$usageCode == null) {
                if (other$usageCode == null) {
                    break Label_0818;
                }
            }
            else if (this$usageCode.equals(other$usageCode)) {
                break Label_0818;
            }
            return false;
        }
        final Object this$setlFlag = this.getSetlFlag();
        final Object other$setlFlag = other.getSetlFlag();
        Label_0855: {
            if (this$setlFlag == null) {
                if (other$setlFlag == null) {
                    break Label_0855;
                }
            }
            else if (this$setlFlag.equals(other$setlFlag)) {
                break Label_0855;
            }
            return false;
        }
        final Object this$terminalCapability = this.getTerminalCapability();
        final Object other$terminalCapability = other.getTerminalCapability();
        Label_0892: {
            if (this$terminalCapability == null) {
                if (other$terminalCapability == null) {
                    break Label_0892;
                }
            }
            else if (this$terminalCapability.equals(other$terminalCapability)) {
                break Label_0892;
            }
            return false;
        }
        final Object this$reimAttribute = this.getReimAttribute();
        final Object other$reimAttribute = other.getReimAttribute();
        Label_0929: {
            if (this$reimAttribute == null) {
                if (other$reimAttribute == null) {
                    break Label_0929;
                }
            }
            else if (this$reimAttribute.equals(other$reimAttribute)) {
                break Label_0929;
            }
            return false;
        }
        final Object this$authAmount = this.getAuthAmount();
        final Object other$authAmount = other.getAuthAmount();
        Label_0966: {
            if (this$authAmount == null) {
                if (other$authAmount == null) {
                    break Label_0966;
                }
            }
            else if (this$authAmount.equals(other$authAmount)) {
                break Label_0966;
            }
            return false;
        }
        final Object this$txnFeeAmount = this.getTxnFeeAmount();
        final Object other$txnFeeAmount = other.getTxnFeeAmount();
        Label_1003: {
            if (this$txnFeeAmount == null) {
                if (other$txnFeeAmount == null) {
                    break Label_1003;
                }
            }
            else if (this$txnFeeAmount.equals(other$txnFeeAmount)) {
                break Label_1003;
            }
            return false;
        }
        final Object this$dccIndicator = this.getDccIndicator();
        final Object other$dccIndicator = other.getDccIndicator();
        Label_1040: {
            if (this$dccIndicator == null) {
                if (other$dccIndicator == null) {
                    break Label_1040;
                }
            }
            else if (this$dccIndicator.equals(other$dccIndicator)) {
                break Label_1040;
            }
            return false;
        }
        final Object this$smsDmsFlag = this.getSmsDmsFlag();
        final Object other$smsDmsFlag = other.getSmsDmsFlag();
        Label_1077: {
            if (this$smsDmsFlag == null) {
                if (other$smsDmsFlag == null) {
                    break Label_1077;
                }
            }
            else if (this$smsDmsFlag.equals(other$smsDmsFlag)) {
                break Label_1077;
            }
            return false;
        }
        final Object this$domIntlFlag = this.getDomIntlFlag();
        final Object other$domIntlFlag = other.getDomIntlFlag();
        Label_1114: {
            if (this$domIntlFlag == null) {
                if (other$domIntlFlag == null) {
                    break Label_1114;
                }
            }
            else if (this$domIntlFlag.equals(other$domIntlFlag)) {
                break Label_1114;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_1151: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_1151;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_1151;
            }
            return false;
        }
        final Object this$dccAmount = this.getDccAmount();
        final Object other$dccAmount = other.getDccAmount();
        Label_1188: {
            if (this$dccAmount == null) {
                if (other$dccAmount == null) {
                    break Label_1188;
                }
            }
            else if (this$dccAmount.equals(other$dccAmount)) {
                break Label_1188;
            }
            return false;
        }
        final Object this$acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        final Object other$acceptanceTrlIndicator = other.getAcceptanceTrlIndicator();
        Label_1225: {
            if (this$acceptanceTrlIndicator == null) {
                if (other$acceptanceTrlIndicator == null) {
                    break Label_1225;
                }
            }
            else if (this$acceptanceTrlIndicator.equals(other$acceptanceTrlIndicator)) {
                break Label_1225;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_1262: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_1262;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_1262;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_1299: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_1299;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_1299;
            }
            return false;
        }
        final Object this$txnCode = this.getTxnCode();
        final Object other$txnCode = other.getTxnCode();
        Label_1336: {
            if (this$txnCode == null) {
                if (other$txnCode == null) {
                    break Label_1336;
                }
            }
            else if (this$txnCode.equals(other$txnCode)) {
                break Label_1336;
            }
            return false;
        }
        final Object this$encCardNumber = this.getEncCardNumber();
        final Object other$encCardNumber = other.getEncCardNumber();
        Label_1373: {
            if (this$encCardNumber == null) {
                if (other$encCardNumber == null) {
                    break Label_1373;
                }
            }
            else if (this$encCardNumber.equals(other$encCardNumber)) {
                break Label_1373;
            }
            return false;
        }
        final Object this$arn = this.getArn();
        final Object other$arn = other.getArn();
        Label_1410: {
            if (this$arn == null) {
                if (other$arn == null) {
                    break Label_1410;
                }
            }
            else if (this$arn.equals(other$arn)) {
                break Label_1410;
            }
            return false;
        }
        final Object this$purchaseDate = this.getPurchaseDate();
        final Object other$purchaseDate = other.getPurchaseDate();
        Label_1447: {
            if (this$purchaseDate == null) {
                if (other$purchaseDate == null) {
                    break Label_1447;
                }
            }
            else if (this$purchaseDate.equals(other$purchaseDate)) {
                break Label_1447;
            }
            return false;
        }
        final Object this$txnCurCode = this.getTxnCurCode();
        final Object other$txnCurCode = other.getTxnCurCode();
        Label_1484: {
            if (this$txnCurCode == null) {
                if (other$txnCurCode == null) {
                    break Label_1484;
                }
            }
            else if (this$txnCurCode.equals(other$txnCurCode)) {
                break Label_1484;
            }
            return false;
        }
        final Object this$meName = this.getMeName();
        final Object other$meName = other.getMeName();
        Label_1521: {
            if (this$meName == null) {
                if (other$meName == null) {
                    break Label_1521;
                }
            }
            else if (this$meName.equals(other$meName)) {
                break Label_1521;
            }
            return false;
        }
        final Object this$meCity = this.getMeCity();
        final Object other$meCity = other.getMeCity();
        Label_1558: {
            if (this$meCity == null) {
                if (other$meCity == null) {
                    break Label_1558;
                }
            }
            else if (this$meCity.equals(other$meCity)) {
                break Label_1558;
            }
            return false;
        }
        final Object this$meCountry = this.getMeCountry();
        final Object other$meCountry = other.getMeCountry();
        Label_1595: {
            if (this$meCountry == null) {
                if (other$meCountry == null) {
                    break Label_1595;
                }
            }
            else if (this$meCountry.equals(other$meCountry)) {
                break Label_1595;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_1632: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_1632;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_1632;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_1669: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_1669;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_1669;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        Label_1706: {
            if (this$posEntryMode == null) {
                if (other$posEntryMode == null) {
                    break Label_1706;
                }
            }
            else if (this$posEntryMode.equals(other$posEntryMode)) {
                break Label_1706;
            }
            return false;
        }
        final Object this$memberText = this.getMemberText();
        final Object other$memberText = other.getMemberText();
        Label_1743: {
            if (this$memberText == null) {
                if (other$memberText == null) {
                    break Label_1743;
                }
            }
            else if (this$memberText.equals(other$memberText)) {
                break Label_1743;
            }
            return false;
        }
        final Object this$feePrgIndicator = this.getFeePrgIndicator();
        final Object other$feePrgIndicator = other.getFeePrgIndicator();
        Label_1780: {
            if (this$feePrgIndicator == null) {
                if (other$feePrgIndicator == null) {
                    break Label_1780;
                }
            }
            else if (this$feePrgIndicator.equals(other$feePrgIndicator)) {
                break Label_1780;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_1817: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_1817;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_1817;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_1854: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_1854;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_1854;
            }
            return false;
        }
        final Object this$acqBussId = this.getAcqBussId();
        final Object other$acqBussId = other.getAcqBussId();
        Label_1891: {
            if (this$acqBussId == null) {
                if (other$acqBussId == null) {
                    break Label_1891;
                }
            }
            else if (this$acqBussId.equals(other$acqBussId)) {
                break Label_1891;
            }
            return false;
        }
        final Object this$respCode = this.getRespCode();
        final Object other$respCode = other.getRespCode();
        Label_1928: {
            if (this$respCode == null) {
                if (other$respCode == null) {
                    break Label_1928;
                }
            }
            else if (this$respCode.equals(other$respCode)) {
                break Label_1928;
            }
            return false;
        }
        final Object this$trlTxnDate = this.getTrlTxnDate();
        final Object other$trlTxnDate = other.getTrlTxnDate();
        Label_1965: {
            if (this$trlTxnDate == null) {
                if (other$trlTxnDate == null) {
                    break Label_1965;
                }
            }
            else if (this$trlTxnDate.equals(other$trlTxnDate)) {
                break Label_1965;
            }
            return false;
        }
        final Object this$txnId = this.getTxnId();
        final Object other$txnId = other.getTxnId();
        Label_2002: {
            if (this$txnId == null) {
                if (other$txnId == null) {
                    break Label_2002;
                }
            }
            else if (this$txnId.equals(other$txnId)) {
                break Label_2002;
            }
            return false;
        }
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        Label_2039: {
            if (this$productId == null) {
                if (other$productId == null) {
                    break Label_2039;
                }
            }
            else if (this$productId.equals(other$productId)) {
                break Label_2039;
            }
            return false;
        }
        final Object this$validationCode = this.getValidationCode();
        final Object other$validationCode = other.getValidationCode();
        Label_2076: {
            if (this$validationCode == null) {
                if (other$validationCode == null) {
                    break Label_2076;
                }
            }
            else if (this$validationCode.equals(other$validationCode)) {
                break Label_2076;
            }
            return false;
        }
        final Object this$retRefNumber = this.getRetRefNumber();
        final Object other$retRefNumber = other.getRetRefNumber();
        Label_2113: {
            if (this$retRefNumber == null) {
                if (other$retRefNumber == null) {
                    break Label_2113;
                }
            }
            else if (this$retRefNumber.equals(other$retRefNumber)) {
                break Label_2113;
            }
            return false;
        }
        final Object this$procCode = this.getProcCode();
        final Object other$procCode = other.getProcCode();
        Label_2150: {
            if (this$procCode == null) {
                if (other$procCode == null) {
                    break Label_2150;
                }
            }
            else if (this$procCode.equals(other$procCode)) {
                break Label_2150;
            }
            return false;
        }
        final Object this$reasonCode = this.getReasonCode();
        final Object other$reasonCode = other.getReasonCode();
        Label_2187: {
            if (this$reasonCode == null) {
                if (other$reasonCode == null) {
                    break Label_2187;
                }
            }
            else if (this$reasonCode.equals(other$reasonCode)) {
                break Label_2187;
            }
            return false;
        }
        final Object this$stan = this.getStan();
        final Object other$stan = other.getStan();
        Label_2224: {
            if (this$stan == null) {
                if (other$stan == null) {
                    break Label_2224;
                }
            }
            else if (this$stan.equals(other$stan)) {
                break Label_2224;
            }
            return false;
        }
        final Object this$trlCapProfile = this.getTrlCapProfile();
        final Object other$trlCapProfile = other.getTrlCapProfile();
        Label_2261: {
            if (this$trlCapProfile == null) {
                if (other$trlCapProfile == null) {
                    break Label_2261;
                }
            }
            else if (this$trlCapProfile.equals(other$trlCapProfile)) {
                break Label_2261;
            }
            return false;
        }
        final Object this$trlCountryCode = this.getTrlCountryCode();
        final Object other$trlCountryCode = other.getTrlCountryCode();
        Label_2298: {
            if (this$trlCountryCode == null) {
                if (other$trlCountryCode == null) {
                    break Label_2298;
                }
            }
            else if (this$trlCountryCode.equals(other$trlCountryCode)) {
                break Label_2298;
            }
            return false;
        }
        final Object this$upblNumber = this.getUpblNumber();
        final Object other$upblNumber = other.getUpblNumber();
        Label_2335: {
            if (this$upblNumber == null) {
                if (other$upblNumber == null) {
                    break Label_2335;
                }
            }
            else if (this$upblNumber.equals(other$upblNumber)) {
                break Label_2335;
            }
            return false;
        }
        final Object this$cardSeqNumber = this.getCardSeqNumber();
        final Object other$cardSeqNumber = other.getCardSeqNumber();
        Label_2372: {
            if (this$cardSeqNumber == null) {
                if (other$cardSeqNumber == null) {
                    break Label_2372;
                }
            }
            else if (this$cardSeqNumber.equals(other$cardSeqNumber)) {
                break Label_2372;
            }
            return false;
        }
        final Object this$appTxnCounter = this.getAppTxnCounter();
        final Object other$appTxnCounter = other.getAppTxnCounter();
        Label_2409: {
            if (this$appTxnCounter == null) {
                if (other$appTxnCounter == null) {
                    break Label_2409;
                }
            }
            else if (this$appTxnCounter.equals(other$appTxnCounter)) {
                break Label_2409;
            }
            return false;
        }
        final Object this$appIcProfile = this.getAppIcProfile();
        final Object other$appIcProfile = other.getAppIcProfile();
        Label_2446: {
            if (this$appIcProfile == null) {
                if (other$appIcProfile == null) {
                    break Label_2446;
                }
            }
            else if (this$appIcProfile.equals(other$appIcProfile)) {
                break Label_2446;
            }
            return false;
        }
        final Object this$appCryptogram = this.getAppCryptogram();
        final Object other$appCryptogram = other.getAppCryptogram();
        Label_2483: {
            if (this$appCryptogram == null) {
                if (other$appCryptogram == null) {
                    break Label_2483;
                }
            }
            else if (this$appCryptogram.equals(other$appCryptogram)) {
                break Label_2483;
            }
            return false;
        }
        final Object this$issAppDataB2 = this.getIssAppDataB2();
        final Object other$issAppDataB2 = other.getIssAppDataB2();
        Label_2520: {
            if (this$issAppDataB2 == null) {
                if (other$issAppDataB2 == null) {
                    break Label_2520;
                }
            }
            else if (this$issAppDataB2.equals(other$issAppDataB2)) {
                break Label_2520;
            }
            return false;
        }
        final Object this$issAppDataB3 = this.getIssAppDataB3();
        final Object other$issAppDataB3 = other.getIssAppDataB3();
        Label_2557: {
            if (this$issAppDataB3 == null) {
                if (other$issAppDataB3 == null) {
                    break Label_2557;
                }
            }
            else if (this$issAppDataB3.equals(other$issAppDataB3)) {
                break Label_2557;
            }
            return false;
        }
        final Object this$issAppDataB4 = this.getIssAppDataB4();
        final Object other$issAppDataB4 = other.getIssAppDataB4();
        Label_2594: {
            if (this$issAppDataB4 == null) {
                if (other$issAppDataB4 == null) {
                    break Label_2594;
                }
            }
            else if (this$issAppDataB4.equals(other$issAppDataB4)) {
                break Label_2594;
            }
            return false;
        }
        final Object this$issAppDataB5 = this.getIssAppDataB8();
        final Object other$issAppDataB5 = other.getIssAppDataB8();
        Label_2631: {
            if (this$issAppDataB5 == null) {
                if (other$issAppDataB5 == null) {
                    break Label_2631;
                }
            }
            else if (this$issAppDataB5.equals(other$issAppDataB5)) {
                break Label_2631;
            }
            return false;
        }
        final Object this$issAppDataB6 = this.getIssAppDataB9();
        final Object other$issAppDataB6 = other.getIssAppDataB9();
        Label_2668: {
            if (this$issAppDataB6 == null) {
                if (other$issAppDataB6 == null) {
                    break Label_2668;
                }
            }
            else if (this$issAppDataB6.equals(other$issAppDataB6)) {
                break Label_2668;
            }
            return false;
        }
        final Object this$issAppDataB7 = this.getIssAppDataB1();
        final Object other$issAppDataB7 = other.getIssAppDataB1();
        Label_2705: {
            if (this$issAppDataB7 == null) {
                if (other$issAppDataB7 == null) {
                    break Label_2705;
                }
            }
            else if (this$issAppDataB7.equals(other$issAppDataB7)) {
                break Label_2705;
            }
            return false;
        }
        final Object this$issAppDataB8 = this.getIssAppDataB17();
        final Object other$issAppDataB8 = other.getIssAppDataB17();
        Label_2742: {
            if (this$issAppDataB8 == null) {
                if (other$issAppDataB8 == null) {
                    break Label_2742;
                }
            }
            else if (this$issAppDataB8.equals(other$issAppDataB8)) {
                break Label_2742;
            }
            return false;
        }
        final Object this$issAppDataB9 = this.getIssAppDataB18();
        final Object other$issAppDataB9 = other.getIssAppDataB18();
        Label_2779: {
            if (this$issAppDataB9 == null) {
                if (other$issAppDataB9 == null) {
                    break Label_2779;
                }
            }
            else if (this$issAppDataB9.equals(other$issAppDataB9)) {
                break Label_2779;
            }
            return false;
        }
        final Object this$trlVerResult = this.getTrlVerResult();
        final Object other$trlVerResult = other.getTrlVerResult();
        Label_2816: {
            if (this$trlVerResult == null) {
                if (other$trlVerResult == null) {
                    break Label_2816;
                }
            }
            else if (this$trlVerResult.equals(other$trlVerResult)) {
                break Label_2816;
            }
            return false;
        }
        final Object this$formFactorIndicator = this.getFormFactorIndicator();
        final Object other$formFactorIndicator = other.getFormFactorIndicator();
        Label_2853: {
            if (this$formFactorIndicator == null) {
                if (other$formFactorIndicator == null) {
                    break Label_2853;
                }
            }
            else if (this$formFactorIndicator.equals(other$formFactorIndicator)) {
                break Label_2853;
            }
            return false;
        }
        final Object this$issScriptResult = this.getIssScriptResult();
        final Object other$issScriptResult = other.getIssScriptResult();
        Label_2890: {
            if (this$issScriptResult == null) {
                if (other$issScriptResult == null) {
                    break Label_2890;
                }
            }
            else if (this$issScriptResult.equals(other$issScriptResult)) {
                break Label_2890;
            }
            return false;
        }
        final Object this$serviceCode = this.getServiceCode();
        final Object other$serviceCode = other.getServiceCode();
        Label_2927: {
            if (this$serviceCode == null) {
                if (other$serviceCode == null) {
                    break Label_2927;
                }
            }
            else if (this$serviceCode.equals(other$serviceCode)) {
                break Label_2927;
            }
            return false;
        }
        final Object this$network = this.getNetwork();
        final Object other$network = other.getNetwork();
        Label_2964: {
            if (this$network == null) {
                if (other$network == null) {
                    break Label_2964;
                }
            }
            else if (this$network.equals(other$network)) {
                break Label_2964;
            }
            return false;
        }
        final Object this$dccCurrency = this.getDccCurrency();
        final Object other$dccCurrency = other.getDccCurrency();
        if (this$dccCurrency == null) {
            if (other$dccCurrency == null) {
                return true;
            }
        }
        else if (this$dccCurrency.equals(other$dccCurrency)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof VisaAcqTxnWorkEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInstitutionCode();
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + (($prjSerNumber == null) ? 43 : $prjSerNumber.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + (($txnRefNumber == null) ? 43 : $txnRefNumber.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $schgAmount = this.getSchgAmount();
        result = result * 59 + (($schgAmount == null) ? 43 : $schgAmount.hashCode());
        final Object $chIdMethod = this.getChIdMethod();
        result = result * 59 + (($chIdMethod == null) ? 43 : $chIdMethod.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $accSelection = this.getAccSelection();
        result = result * 59 + (($accSelection == null) ? 43 : $accSelection.hashCode());
        final Object $posEnvironment = this.getPosEnvironment();
        result = result * 59 + (($posEnvironment == null) ? 43 : $posEnvironment.hashCode());
        final Object $cryptAmount = this.getCryptAmount();
        result = result * 59 + (($cryptAmount == null) ? 43 : $cryptAmount.hashCode());
        final Object $cashbackAmount = this.getCashbackAmount();
        result = result * 59 + (($cashbackAmount == null) ? 43 : $cashbackAmount.hashCode());
        final Object $visaToken = this.getVisaToken();
        result = result * 59 + (($visaToken == null) ? 43 : $visaToken.hashCode());
        final Object $authCharIndicator = this.getAuthCharIndicator();
        result = result * 59 + (($authCharIndicator == null) ? 43 : $authCharIndicator.hashCode());
        final Object $accFundSource = this.getAccFundSource();
        result = result * 59 + (($accFundSource == null) ? 43 : $accFundSource.hashCode());
        final Object $marketSpecDataInd = this.getMarketSpecDataInd();
        result = result * 59 + (($marketSpecDataInd == null) ? 43 : $marketSpecDataInd.hashCode());
        final Object $spendQualiIndictor = this.getSpendQualiIndictor();
        result = result * 59 + (($spendQualiIndictor == null) ? 43 : $spendQualiIndictor.hashCode());
        final Object $collOnlyFlag = this.getCollOnlyFlag();
        result = result * 59 + (($collOnlyFlag == null) ? 43 : $collOnlyFlag.hashCode());
        final Object $usageCode = this.getUsageCode();
        result = result * 59 + (($usageCode == null) ? 43 : $usageCode.hashCode());
        final Object $setlFlag = this.getSetlFlag();
        result = result * 59 + (($setlFlag == null) ? 43 : $setlFlag.hashCode());
        final Object $terminalCapability = this.getTerminalCapability();
        result = result * 59 + (($terminalCapability == null) ? 43 : $terminalCapability.hashCode());
        final Object $reimAttribute = this.getReimAttribute();
        result = result * 59 + (($reimAttribute == null) ? 43 : $reimAttribute.hashCode());
        final Object $authAmount = this.getAuthAmount();
        result = result * 59 + (($authAmount == null) ? 43 : $authAmount.hashCode());
        final Object $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + (($txnFeeAmount == null) ? 43 : $txnFeeAmount.hashCode());
        final Object $dccIndicator = this.getDccIndicator();
        result = result * 59 + (($dccIndicator == null) ? 43 : $dccIndicator.hashCode());
        final Object $smsDmsFlag = this.getSmsDmsFlag();
        result = result * 59 + (($smsDmsFlag == null) ? 43 : $smsDmsFlag.hashCode());
        final Object $domIntlFlag = this.getDomIntlFlag();
        result = result * 59 + (($domIntlFlag == null) ? 43 : $domIntlFlag.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $dccAmount = this.getDccAmount();
        result = result * 59 + (($dccAmount == null) ? 43 : $dccAmount.hashCode());
        final Object $acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        result = result * 59 + (($acceptanceTrlIndicator == null) ? 43 : $acceptanceTrlIndicator.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $txnCode = this.getTxnCode();
        result = result * 59 + (($txnCode == null) ? 43 : $txnCode.hashCode());
        final Object $encCardNumber = this.getEncCardNumber();
        result = result * 59 + (($encCardNumber == null) ? 43 : $encCardNumber.hashCode());
        final Object $arn = this.getArn();
        result = result * 59 + (($arn == null) ? 43 : $arn.hashCode());
        final Object $purchaseDate = this.getPurchaseDate();
        result = result * 59 + (($purchaseDate == null) ? 43 : $purchaseDate.hashCode());
        final Object $txnCurCode = this.getTxnCurCode();
        result = result * 59 + (($txnCurCode == null) ? 43 : $txnCurCode.hashCode());
        final Object $meName = this.getMeName();
        result = result * 59 + (($meName == null) ? 43 : $meName.hashCode());
        final Object $meCity = this.getMeCity();
        result = result * 59 + (($meCity == null) ? 43 : $meCity.hashCode());
        final Object $meCountry = this.getMeCountry();
        result = result * 59 + (($meCountry == null) ? 43 : $meCountry.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $memberText = this.getMemberText();
        result = result * 59 + (($memberText == null) ? 43 : $memberText.hashCode());
        final Object $feePrgIndicator = this.getFeePrgIndicator();
        result = result * 59 + (($feePrgIndicator == null) ? 43 : $feePrgIndicator.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $acqBussId = this.getAcqBussId();
        result = result * 59 + (($acqBussId == null) ? 43 : $acqBussId.hashCode());
        final Object $respCode = this.getRespCode();
        result = result * 59 + (($respCode == null) ? 43 : $respCode.hashCode());
        final Object $trlTxnDate = this.getTrlTxnDate();
        result = result * 59 + (($trlTxnDate == null) ? 43 : $trlTxnDate.hashCode());
        final Object $txnId = this.getTxnId();
        result = result * 59 + (($txnId == null) ? 43 : $txnId.hashCode());
        final Object $productId = this.getProductId();
        result = result * 59 + (($productId == null) ? 43 : $productId.hashCode());
        final Object $validationCode = this.getValidationCode();
        result = result * 59 + (($validationCode == null) ? 43 : $validationCode.hashCode());
        final Object $retRefNumber = this.getRetRefNumber();
        result = result * 59 + (($retRefNumber == null) ? 43 : $retRefNumber.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        final Object $reasonCode = this.getReasonCode();
        result = result * 59 + (($reasonCode == null) ? 43 : $reasonCode.hashCode());
        final Object $stan = this.getStan();
        result = result * 59 + (($stan == null) ? 43 : $stan.hashCode());
        final Object $trlCapProfile = this.getTrlCapProfile();
        result = result * 59 + (($trlCapProfile == null) ? 43 : $trlCapProfile.hashCode());
        final Object $trlCountryCode = this.getTrlCountryCode();
        result = result * 59 + (($trlCountryCode == null) ? 43 : $trlCountryCode.hashCode());
        final Object $upblNumber = this.getUpblNumber();
        result = result * 59 + (($upblNumber == null) ? 43 : $upblNumber.hashCode());
        final Object $cardSeqNumber = this.getCardSeqNumber();
        result = result * 59 + (($cardSeqNumber == null) ? 43 : $cardSeqNumber.hashCode());
        final Object $appTxnCounter = this.getAppTxnCounter();
        result = result * 59 + (($appTxnCounter == null) ? 43 : $appTxnCounter.hashCode());
        final Object $appIcProfile = this.getAppIcProfile();
        result = result * 59 + (($appIcProfile == null) ? 43 : $appIcProfile.hashCode());
        final Object $appCryptogram = this.getAppCryptogram();
        result = result * 59 + (($appCryptogram == null) ? 43 : $appCryptogram.hashCode());
        final Object $issAppDataB2 = this.getIssAppDataB2();
        result = result * 59 + (($issAppDataB2 == null) ? 43 : $issAppDataB2.hashCode());
        final Object $issAppDataB3 = this.getIssAppDataB3();
        result = result * 59 + (($issAppDataB3 == null) ? 43 : $issAppDataB3.hashCode());
        final Object $issAppDataB4 = this.getIssAppDataB4();
        result = result * 59 + (($issAppDataB4 == null) ? 43 : $issAppDataB4.hashCode());
        final Object $issAppDataB5 = this.getIssAppDataB8();
        result = result * 59 + (($issAppDataB5 == null) ? 43 : $issAppDataB5.hashCode());
        final Object $issAppDataB6 = this.getIssAppDataB9();
        result = result * 59 + (($issAppDataB6 == null) ? 43 : $issAppDataB6.hashCode());
        final Object $issAppDataB7 = this.getIssAppDataB1();
        result = result * 59 + (($issAppDataB7 == null) ? 43 : $issAppDataB7.hashCode());
        final Object $issAppDataB8 = this.getIssAppDataB17();
        result = result * 59 + (($issAppDataB8 == null) ? 43 : $issAppDataB8.hashCode());
        final Object $issAppDataB9 = this.getIssAppDataB18();
        result = result * 59 + (($issAppDataB9 == null) ? 43 : $issAppDataB9.hashCode());
        final Object $trlVerResult = this.getTrlVerResult();
        result = result * 59 + (($trlVerResult == null) ? 43 : $trlVerResult.hashCode());
        final Object $formFactorIndicator = this.getFormFactorIndicator();
        result = result * 59 + (($formFactorIndicator == null) ? 43 : $formFactorIndicator.hashCode());
        final Object $issScriptResult = this.getIssScriptResult();
        result = result * 59 + (($issScriptResult == null) ? 43 : $issScriptResult.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $dccCurrency = this.getDccCurrency();
        result = result * 59 + (($dccCurrency == null) ? 43 : $dccCurrency.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "VisaAcqTxnWorkEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", intCode=" + this.getIntCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", genStatus=" + this.getGenStatus() + ", txnRefNumber=" + this.getTxnRefNumber() + ", txnType=" + this.getTxnType() + ", txnCode=" + this.getTxnCode() + ", encCardNumber=" + this.getEncCardNumber() + ", arn=" + this.getArn() + ", purchaseDate=" + String.valueOf(this.getPurchaseDate()) + ", txnCurCode=" + this.getTxnCurCode() + ", txnAmount=" + this.getTxnAmount() + ", schgAmount=" + this.getSchgAmount() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meCountry=" + this.getMeCountry() + ", mcc=" + this.getMcc() + ", approvalCode=" + this.getApprovalCode() + ", chIdMethod=" + this.getChIdMethod() + ", posEntryMode=" + this.getPosEntryMode() + ", memberText=" + this.getMemberText() + ", feePrgIndicator=" + this.getFeePrgIndicator() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", accSelection=" + this.getAccSelection() + ", acqBussId=" + this.getAcqBussId() + ", posEnvironment=" + this.getPosEnvironment() + ", respCode=" + this.getRespCode() + ", trlTxnDate=" + String.valueOf(this.getTrlTxnDate()) + ", cryptAmount=" + this.getCryptAmount() + ", cashbackAmount=" + this.getCashbackAmount() + ", txnId=" + this.getTxnId() + ", visaToken=" + this.getVisaToken() + ", authCharIndicator=" + this.getAuthCharIndicator() + ", accFundSource=" + this.getAccFundSource() + ", marketSpecDataInd=" + this.getMarketSpecDataInd() + ", productId=" + this.getProductId() + ", validationCode=" + this.getValidationCode() + ", spendQualiIndictor=" + this.getSpendQualiIndictor() + ", collOnlyFlag=" + this.getCollOnlyFlag() + ", retRefNumber=" + this.getRetRefNumber() + ", procCode=" + this.getProcCode() + ", usageCode=" + this.getUsageCode() + ", reasonCode=" + this.getReasonCode() + ", setlFlag=" + this.getSetlFlag() + ", terminalCapability=" + this.getTerminalCapability() + ", reimAttribute=" + this.getReimAttribute() + ", stan=" + this.getStan() + ", authAmount=" + this.getAuthAmount() + ", trlCapProfile=" + this.getTrlCapProfile() + ", trlCountryCode=" + this.getTrlCountryCode() + ", upblNumber=" + this.getUpblNumber() + ", cardSeqNumber=" + this.getCardSeqNumber() + ", appTxnCounter=" + this.getAppTxnCounter() + ", appIcProfile=" + this.getAppIcProfile() + ", appCryptogram=" + this.getAppCryptogram() + ", issAppDataB2=" + this.getIssAppDataB2() + ", issAppDataB3=" + this.getIssAppDataB3() + ", issAppDataB4=" + this.getIssAppDataB4() + ", issAppDataB8=" + this.getIssAppDataB8() + ", issAppDataB9=" + this.getIssAppDataB9() + ", issAppDataB1=" + this.getIssAppDataB1() + ", issAppDataB17=" + this.getIssAppDataB17() + ", issAppDataB18=" + this.getIssAppDataB18() + ", trlVerResult=" + this.getTrlVerResult() + ", formFactorIndicator=" + this.getFormFactorIndicator() + ", issScriptResult=" + this.getIssScriptResult() + ", serviceCode=" + this.getServiceCode() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", dccIndicator=" + this.getDccIndicator() + ", network=" + this.getNetwork() + ", smsDmsFlag=" + this.getSmsDmsFlag() + ", domIntlFlag=" + this.getDomIntlFlag() + ", cardType=" + this.getCardType() + ", dccCurrency=" + this.getDccCurrency() + ", dccAmount=" + this.getDccAmount() + ", acceptanceTrlIndicator=" + this.getAcceptanceTrlIndicator();
    }
    
    public VisaAcqTxnWorkEntity() {
    }
    
    public VisaAcqTxnWorkEntity(final Integer serNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final int institutionCode, final Integer intCode, final Integer prjSerNumber, final Integer genStatus, final Integer txnRefNumber, final String txnType, final String txnCode, final String encCardNumber, final String arn, final LocalDateTime purchaseDate, final String txnCurCode, final Double txnAmount, final Double schgAmount, final String meName, final String meCity, final String meCountry, final String mcc, final String approvalCode, final Character chIdMethod, final String posEntryMode, final String memberText, final String feePrgIndicator, final String merchantId, final String terminalId, final Character motoEcomIndicator, final Character accSelection, final String acqBussId, final Character posEnvironment, final String respCode, final LocalDate trlTxnDate, final Double cryptAmount, final Double cashbackAmount, final String txnId, final Character visaToken, final Character authCharIndicator, final Character accFundSource, final Character marketSpecDataInd, final String productId, final String validationCode, final Character spendQualiIndictor, final Character collOnlyFlag, final String retRefNumber, final String procCode, final Character usageCode, final String reasonCode, final Character setlFlag, final Character terminalCapability, final Character reimAttribute, final String stan, final Double authAmount, final String trlCapProfile, final String trlCountryCode, final String upblNumber, final String cardSeqNumber, final String appTxnCounter, final String appIcProfile, final String appCryptogram, final String issAppDataB2, final String issAppDataB3, final String issAppDataB4, final String issAppDataB8, final String issAppDataB9, final String issAppDataB1, final String issAppDataB17, final String issAppDataB18, final String trlVerResult, final String formFactorIndicator, final String issScriptResult, final String serviceCode, final Double txnFeeAmount, final Character dccIndicator, final String network, final Character smsDmsFlag, final Character domIntlFlag, final Character cardType, final String dccCurrency, final Double dccAmount, final Character acceptanceTrlIndicator) {
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
        this.dccIndicator = dccIndicator;
        this.network = network;
        this.smsDmsFlag = smsDmsFlag;
        this.domIntlFlag = domIntlFlag;
        this.cardType = cardType;
        this.dccCurrency = dccCurrency;
        this.dccAmount = dccAmount;
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
    }
    
    public static class VisaAcqTxnWorkEntityBuilder
    {
        private Integer serNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private int institutionCode;
        private Integer intCode;
        private Integer prjSerNumber;
        private Integer genStatus;
        private Integer txnRefNumber;
        private String txnType;
        private String txnCode;
        private String encCardNumber;
        private String arn;
        private LocalDateTime purchaseDate;
        private String txnCurCode;
        private Double txnAmount;
        private Double schgAmount;
        private String meName;
        private String meCity;
        private String meCountry;
        private String mcc;
        private String approvalCode;
        private Character chIdMethod;
        private String posEntryMode;
        private String memberText;
        private String feePrgIndicator;
        private String merchantId;
        private String terminalId;
        private Character motoEcomIndicator;
        private Character accSelection;
        private String acqBussId;
        private Character posEnvironment;
        private String respCode;
        private LocalDate trlTxnDate;
        private Double cryptAmount;
        private Double cashbackAmount;
        private String txnId;
        private Character visaToken;
        private Character authCharIndicator;
        private Character accFundSource;
        private Character marketSpecDataInd;
        private String productId;
        private String validationCode;
        private Character spendQualiIndictor;
        private Character collOnlyFlag;
        private String retRefNumber;
        private String procCode;
        private Character usageCode;
        private String reasonCode;
        private Character setlFlag;
        private Character terminalCapability;
        private Character reimAttribute;
        private String stan;
        private Double authAmount;
        private String trlCapProfile;
        private String trlCountryCode;
        private String upblNumber;
        private String cardSeqNumber;
        private String appTxnCounter;
        private String appIcProfile;
        private String appCryptogram;
        private String issAppDataB2;
        private String issAppDataB3;
        private String issAppDataB4;
        private String issAppDataB8;
        private String issAppDataB9;
        private String issAppDataB1;
        private String issAppDataB17;
        private String issAppDataB18;
        private String trlVerResult;
        private String formFactorIndicator;
        private String issScriptResult;
        private String serviceCode;
        private Double txnFeeAmount;
        private Character dccIndicator;
        private String network;
        private Character smsDmsFlag;
        private Character domIntlFlag;
        private Character cardType;
        private String dccCurrency;
        private Double dccAmount;
        private Character acceptanceTrlIndicator;
        
        VisaAcqTxnWorkEntityBuilder() {
        }
        
        public VisaAcqTxnWorkEntityBuilder serNumber(final Integer serNumber) {
            this.serNumber = serNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder institutionCode(final int institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder genStatus(final Integer genStatus) {
            this.genStatus = genStatus;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnRefNumber(final Integer txnRefNumber) {
            this.txnRefNumber = txnRefNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnCode(final String txnCode) {
            this.txnCode = txnCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder encCardNumber(final String encCardNumber) {
            this.encCardNumber = encCardNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder arn(final String arn) {
            this.arn = arn;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder purchaseDate(final LocalDateTime purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnCurCode(final String txnCurCode) {
            this.txnCurCode = txnCurCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder schgAmount(final Double schgAmount) {
            this.schgAmount = schgAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder meName(final String meName) {
            this.meName = meName;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder meCity(final String meCity) {
            this.meCity = meCity;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder meCountry(final String meCountry) {
            this.meCountry = meCountry;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder chIdMethod(final Character chIdMethod) {
            this.chIdMethod = chIdMethod;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder memberText(final String memberText) {
            this.memberText = memberText;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder feePrgIndicator(final String feePrgIndicator) {
            this.feePrgIndicator = feePrgIndicator;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder motoEcomIndicator(final Character motoEcomIndicator) {
            this.motoEcomIndicator = motoEcomIndicator;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder accSelection(final Character accSelection) {
            this.accSelection = accSelection;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder acqBussId(final String acqBussId) {
            this.acqBussId = acqBussId;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder posEnvironment(final Character posEnvironment) {
            this.posEnvironment = posEnvironment;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder respCode(final String respCode) {
            this.respCode = respCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder trlTxnDate(final LocalDate trlTxnDate) {
            this.trlTxnDate = trlTxnDate;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder cryptAmount(final Double cryptAmount) {
            this.cryptAmount = cryptAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder cashbackAmount(final Double cashbackAmount) {
            this.cashbackAmount = cashbackAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnId(final String txnId) {
            this.txnId = txnId;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder visaToken(final Character visaToken) {
            this.visaToken = visaToken;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder authCharIndicator(final Character authCharIndicator) {
            this.authCharIndicator = authCharIndicator;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder accFundSource(final Character accFundSource) {
            this.accFundSource = accFundSource;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder marketSpecDataInd(final Character marketSpecDataInd) {
            this.marketSpecDataInd = marketSpecDataInd;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder productId(final String productId) {
            this.productId = productId;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder validationCode(final String validationCode) {
            this.validationCode = validationCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder spendQualiIndictor(final Character spendQualiIndictor) {
            this.spendQualiIndictor = spendQualiIndictor;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder collOnlyFlag(final Character collOnlyFlag) {
            this.collOnlyFlag = collOnlyFlag;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder retRefNumber(final String retRefNumber) {
            this.retRefNumber = retRefNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder procCode(final String procCode) {
            this.procCode = procCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder usageCode(final Character usageCode) {
            this.usageCode = usageCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder reasonCode(final String reasonCode) {
            this.reasonCode = reasonCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder setlFlag(final Character setlFlag) {
            this.setlFlag = setlFlag;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder terminalCapability(final Character terminalCapability) {
            this.terminalCapability = terminalCapability;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder reimAttribute(final Character reimAttribute) {
            this.reimAttribute = reimAttribute;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder stan(final String stan) {
            this.stan = stan;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder authAmount(final Double authAmount) {
            this.authAmount = authAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder trlCapProfile(final String trlCapProfile) {
            this.trlCapProfile = trlCapProfile;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder trlCountryCode(final String trlCountryCode) {
            this.trlCountryCode = trlCountryCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder upblNumber(final String upblNumber) {
            this.upblNumber = upblNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder cardSeqNumber(final String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder appTxnCounter(final String appTxnCounter) {
            this.appTxnCounter = appTxnCounter;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder appIcProfile(final String appIcProfile) {
            this.appIcProfile = appIcProfile;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder appCryptogram(final String appCryptogram) {
            this.appCryptogram = appCryptogram;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB2(final String issAppDataB2) {
            this.issAppDataB2 = issAppDataB2;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB3(final String issAppDataB3) {
            this.issAppDataB3 = issAppDataB3;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB4(final String issAppDataB4) {
            this.issAppDataB4 = issAppDataB4;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB8(final String issAppDataB8) {
            this.issAppDataB8 = issAppDataB8;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB9(final String issAppDataB9) {
            this.issAppDataB9 = issAppDataB9;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB1(final String issAppDataB1) {
            this.issAppDataB1 = issAppDataB1;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB17(final String issAppDataB17) {
            this.issAppDataB17 = issAppDataB17;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issAppDataB18(final String issAppDataB18) {
            this.issAppDataB18 = issAppDataB18;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder trlVerResult(final String trlVerResult) {
            this.trlVerResult = trlVerResult;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder formFactorIndicator(final String formFactorIndicator) {
            this.formFactorIndicator = formFactorIndicator;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder issScriptResult(final String issScriptResult) {
            this.issScriptResult = issScriptResult;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder serviceCode(final String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder txnFeeAmount(final Double txnFeeAmount) {
            this.txnFeeAmount = txnFeeAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder dccIndicator(final Character dccIndicator) {
            this.dccIndicator = dccIndicator;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder network(final String network) {
            this.network = network;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder smsDmsFlag(final Character smsDmsFlag) {
            this.smsDmsFlag = smsDmsFlag;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder domIntlFlag(final Character domIntlFlag) {
            this.domIntlFlag = domIntlFlag;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder dccCurrency(final String dccCurrency) {
            this.dccCurrency = dccCurrency;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder dccAmount(final Double dccAmount) {
            this.dccAmount = dccAmount;
            return this;
        }
        
        public VisaAcqTxnWorkEntityBuilder acceptanceTrlIndicator(final Character acceptanceTrlIndicator) {
            this.acceptanceTrlIndicator = acceptanceTrlIndicator;
            return this;
        }
        
        public VisaAcqTxnWorkEntity build() {
            return new VisaAcqTxnWorkEntity(this.serNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.intCode, this.prjSerNumber, this.genStatus, this.txnRefNumber, this.txnType, this.txnCode, this.encCardNumber, this.arn, this.purchaseDate, this.txnCurCode, this.txnAmount, this.schgAmount, this.meName, this.meCity, this.meCountry, this.mcc, this.approvalCode, this.chIdMethod, this.posEntryMode, this.memberText, this.feePrgIndicator, this.merchantId, this.terminalId, this.motoEcomIndicator, this.accSelection, this.acqBussId, this.posEnvironment, this.respCode, this.trlTxnDate, this.cryptAmount, this.cashbackAmount, this.txnId, this.visaToken, this.authCharIndicator, this.accFundSource, this.marketSpecDataInd, this.productId, this.validationCode, this.spendQualiIndictor, this.collOnlyFlag, this.retRefNumber, this.procCode, this.usageCode, this.reasonCode, this.setlFlag, this.terminalCapability, this.reimAttribute, this.stan, this.authAmount, this.trlCapProfile, this.trlCountryCode, this.upblNumber, this.cardSeqNumber, this.appTxnCounter, this.appIcProfile, this.appCryptogram, this.issAppDataB2, this.issAppDataB3, this.issAppDataB4, this.issAppDataB8, this.issAppDataB9, this.issAppDataB1, this.issAppDataB17, this.issAppDataB18, this.trlVerResult, this.formFactorIndicator, this.issScriptResult, this.serviceCode, this.txnFeeAmount, this.dccIndicator, this.network, this.smsDmsFlag, this.domIntlFlag, this.cardType, this.dccCurrency, this.dccAmount, this.acceptanceTrlIndicator);
        }
        
        @Override
        public String toString() {
            return "VisaAcqTxnWorkEntity.VisaAcqTxnWorkEntityBuilder(serNumber=" + this.serNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", intCode=" + this.intCode + ", prjSerNumber=" + this.prjSerNumber + ", genStatus=" + this.genStatus + ", txnRefNumber=" + this.txnRefNumber + ", txnType=" + this.txnType + ", txnCode=" + this.txnCode + ", encCardNumber=" + this.encCardNumber + ", arn=" + this.arn + ", purchaseDate=" + String.valueOf(this.purchaseDate) + ", txnCurCode=" + this.txnCurCode + ", txnAmount=" + this.txnAmount + ", schgAmount=" + this.schgAmount + ", meName=" + this.meName + ", meCity=" + this.meCity + ", meCountry=" + this.meCountry + ", mcc=" + this.mcc + ", approvalCode=" + this.approvalCode + ", chIdMethod=" + this.chIdMethod + ", posEntryMode=" + this.posEntryMode + ", memberText=" + this.memberText + ", feePrgIndicator=" + this.feePrgIndicator + ", merchantId=" + this.merchantId + ", terminalId=" + this.terminalId + ", motoEcomIndicator=" + this.motoEcomIndicator + ", accSelection=" + this.accSelection + ", acqBussId=" + this.acqBussId + ", posEnvironment=" + this.posEnvironment + ", respCode=" + this.respCode + ", trlTxnDate=" + String.valueOf(this.trlTxnDate) + ", cryptAmount=" + this.cryptAmount + ", cashbackAmount=" + this.cashbackAmount + ", txnId=" + this.txnId + ", visaToken=" + this.visaToken + ", authCharIndicator=" + this.authCharIndicator + ", accFundSource=" + this.accFundSource + ", marketSpecDataInd=" + this.marketSpecDataInd + ", productId=" + this.productId + ", validationCode=" + this.validationCode + ", spendQualiIndictor=" + this.spendQualiIndictor + ", collOnlyFlag=" + this.collOnlyFlag + ", retRefNumber=" + this.retRefNumber + ", procCode=" + this.procCode + ", usageCode=" + this.usageCode + ", reasonCode=" + this.reasonCode + ", setlFlag=" + this.setlFlag + ", terminalCapability=" + this.terminalCapability + ", reimAttribute=" + this.reimAttribute + ", stan=" + this.stan + ", authAmount=" + this.authAmount + ", trlCapProfile=" + this.trlCapProfile + ", trlCountryCode=" + this.trlCountryCode + ", upblNumber=" + this.upblNumber + ", cardSeqNumber=" + this.cardSeqNumber + ", appTxnCounter=" + this.appTxnCounter + ", appIcProfile=" + this.appIcProfile + ", appCryptogram=" + this.appCryptogram + ", issAppDataB2=" + this.issAppDataB2 + ", issAppDataB3=" + this.issAppDataB3 + ", issAppDataB4=" + this.issAppDataB4 + ", issAppDataB8=" + this.issAppDataB8 + ", issAppDataB9=" + this.issAppDataB9 + ", issAppDataB1=" + this.issAppDataB1 + ", issAppDataB17=" + this.issAppDataB17 + ", issAppDataB18=" + this.issAppDataB18 + ", trlVerResult=" + this.trlVerResult + ", formFactorIndicator=" + this.formFactorIndicator + ", issScriptResult=" + this.issScriptResult + ", serviceCode=" + this.serviceCode + ", txnFeeAmount=" + this.txnFeeAmount + ", dccIndicator=" + this.dccIndicator + ", network=" + this.network + ", smsDmsFlag=" + this.smsDmsFlag + ", domIntlFlag=" + this.domIntlFlag + ", cardType=" + this.cardType + ", dccCurrency=" + this.dccCurrency + ", dccAmount=" + this.dccAmount + ", acceptanceTrlIndicator=" + this.acceptanceTrlIndicator;
        }
    }
}
