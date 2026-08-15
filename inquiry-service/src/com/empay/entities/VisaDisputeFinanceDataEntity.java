/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.VisaDisputeFinanceDataEntity
 *  com.empay.entities.VisaDisputeFinanceDataEntity$VisaDisputeFinanceDataEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.VisaDisputeFinanceDataEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="VISA_DISPUTE_FINANCE_DATA")
public class VisaDisputeFinanceDataEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="VDF_SER_NUMBER")
    private Integer serNumber;
    @Column(name="VDF_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="VDF_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="VDF_INS_CODE")
    private Integer institutionCode;
    @Column(name="VDF_INT_CODE")
    private Integer interfaceCode;
    @Column(name="VDF_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="VDF_TXN_CODE")
    private String txnCode;
    @Column(name="VDF_SOURCE")
    private String source;
    @Column(name="VDF_ORG_TXN_SER_NUMBER")
    private Integer orgTxnSerNumber;
    @Column(name="VDF_RRR_SER_NUMBER")
    private Integer representSerNumber;
    @Column(name="VDF_PRR_SER_NUMBER")
    private Integer prrSerNumber;
    @Column(name="VDF_CBR_SER_NUMBER")
    private Integer cbrSerNumber;
    @Column(name="VDF_DISPUTE_STATUS")
    private String disputeStatus;
    @Column(name="VDF_DISPUTE_TXN_CODE")
    private String disputeTxnCode;
    @Column(name="VDF_ORG_REC_INDICATOR")
    private String orgRecIndicator;
    @Column(name="VDF_CARD_NUMBER")
    private String cardNumber;
    @Column(name="VDF_ACC_NUMBER")
    private String accNumber;
    @Column(name="VDF_ACQ_REF_NUMBER")
    private String acqRefNumber;
    @Column(name="VDF_PURCHASE_DATE")
    private LocalDate purchaseDate;
    @Column(name="VDF_SOURCE_AMOUNT")
    private Double sourceAmount;
    @Column(name="VDF_SOURCE_CURR_CODE")
    private String sourceCurrency;
    @Column(name="VDF_ME_NAME")
    private String meName;
    @Column(name="VDF_ME_CITY")
    private String meCity;
    @Column(name="VDF_ME_CON_CODE")
    private String meConCode;
    @Column(name="VDF_MCC")
    private String mcc;
    @Column(name="VDF_USAGE_CODE")
    private String usageCode;
    @Column(name="VDF_ME_ZIP_CODE")
    private String meZipCode;
    @Column(name="VDF_AUTH_CODE")
    private String authCode;
    @Column(name="VDF_AUTH_SOURCE_CODE")
    private Character authSourceCode;
    @Column(name="VDF_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name="VDF_CENTRE_PROC_DATE")
    private LocalDate centerProcDate;
    @Column(name="VDF_CARD_ACCEPT_ID")
    private String cardAcceptId;
    @Column(name="VDF_REIMBURSEMENT_ATTRIBUTE")
    private Character reimbAttribute;
    @Column(name="VDF_NTW_IDENTIFICATION_CODE")
    private String ntwIdentificationCode;
    @Column(name="VDF_PRODUCT_ID")
    private String productId;
    @Column(name="VDF_TXN_ID")
    private String txnId;
    @Column(name="VDF_DISPUTE_FINANCIAL_REASON_CODE")
    private String disputeFinancialReasonCode;
    @Column(name="VDF_SETL_FLAG")
    private Character setlFlag;
    @Column(name="VDF_ORG_TXN_AMOUNT")
    private Double orgTxnAmount;
    @Column(name="VDF_ORG_TXN_CURR_CODE")
    private String orgTxnCurrCode;
    @Column(name="VDF_SPECIAL_CB_INDICATOR")
    private Character specialCbIndicator;
    @Column(name="VDF_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name="VDF_BCH_BRANCH_CODE")
    private String bchBranchCode;
    @Column(name="VDF_FLOOR_LIMIT_INDICATOR")
    private Character floorLimitInd;
    @Column(name="VDF_EXCP_FILE_INDICATOR")
    private Character exceptionFileIndicator;
    @Column(name="VDF_PCAS_INDICATOR")
    private String pcasIndicator;
    @Column(name="VDF_DEST_AMOUNT")
    private Double destinationAmount;
    @Column(name="VDF_DEST_CURRENCY")
    private String destinationCurrency;
    @Column(name="VDF_CARD_ACPT_NAME")
    private String cardAcceptName;
    @Column(name="VDF_CARD_ACPT_CITY")
    private String cardAcceptCity;
    @Column(name="VDF_CARD_ACPT_COUNTRY")
    private String cardAcceptCountry;
    @Column(name="VDF_REQ_PAYMENT_SERVICE")
    private String reqPaymentService;
    @Column(name="VDF_REASON_CODE")
    private String reasonCode;
    @Column(name="VDF_POS_CAPABILITY")
    private String posCapability;
    @Column(name="VDF_INT_FEE_INDICATOR")
    private String intFeeIndicator;
    @Column(name="VDF_CH_ID_METHOD")
    private String cardHolderId;
    @Column(name="VDF_ACQ_WORKSTATION_BIN")
    private String acqWorkstationBin;
    @Column(name="VDF_ISS_WORKSTATION_BIN")
    private String issWorkstationBin;
    @Column(name="VDF_CB_REF_NUMBER")
    private String cbRefNumber;
    @Column(name="VDF_DOC_INDICATOR")
    private String docIndicator;
    @Column(name="VDF_MEMBER_TEXT")
    private String memberText;
    @Column(name="VDF_SPECIAL_CONDITION")
    private String specialCondition;
    @Column(name="VDF_FEE_PROGRAM")
    private String feeProgram;
    @Column(name="VDF_ME_CODE")
    private String merchantCode;
    @Column(name="VDF_TERMINAL_ID")
    private String terminalId;
    @Column(name="VDF_NATIONAL_REIMB_FEE")
    private Double nationalReimbFee;
    @Column(name="VDF_MOTO_ECOM_INDICATOR")
    private String ecomIndicator;
    @Column(name="VDF_SPECIAL_CD_INDICATOR")
    private String specialCdIndicator;
    @Column(name="VDF_CHIP_CONDITION_CODE")
    private Character chipConditionCode;
    @Column(name="VDF_POS_ENVIRONMENT")
    private Character posEnvironment;
    @Column(name="VDF_AUTH_AMOUNT")
    private Double authAmount;
    @Column(name="VDF_AUTH_CURRENCY")
    private String authCurrency;
    @Column(name="VDF_AUTH_RESP_CODE")
    private String authRespCode;
    @Column(name="VDF_CB_RIGHT_INDICATOR")
    private String cbRightIndicator;
    @Column(name="VDF_CLRG_SEQ_NUMBER")
    private String clearingSeqNumber;
    @Column(name="VDF_CVV2_RESULTS")
    private String cvv2Results;
    @Column(name="VDF_PRODUCT_CODE")
    private String productCode;
    @Column(name="VDF_INTERCHANGE_FEE")
    private Double interChangeFee;
    @Column(name="VDF_TRL_CAPABLE_PROFILE")
    private String trlCapableProfile;
    @Column(name="VDF_TRL_COUNTRY_CODE")
    private String trlCountryCode;
    @Column(name="VDF_ISS_APP_DATA_B2")
    private String issAppDataB2;
    @Column(name="VDF_ISS_APP_DATA_B3")
    private String issAppDataB3;
    @Column(name="VDF_REV_INDICATOR")
    private String revIndicator;
    @Column(name="VDF_ACTIVE_STATUS")
    private String activeStatus;
    @Column(name="VDF_ACTIVATION_DATE")
    private LocalDateTime activationDate;
    @Column(name="VDF_REPR_FLAG")
    private String reprFlag;
    @Column(name="VDF_PRIORITY")
    private Long priority;
    @Column(name="VDF_REMARKS")
    private String remarks;
    @Column(name="VDF_GEN_STATUS", nullable=false)
    private Integer genStatus;
    @Column(name="VDF_ENC_CARD_NUMBER")
    private String encrypTcardNumber;
    @Column(name="VDF_PAYMENT_REF_NUMBER")
    private Long paymentRefNumber;
    @Column(name="VDF_TXN_CURRENCY")
    private String txnCurrency;
    @Column(name="VDF_TXN_AMOUNT", nullable=false)
    private Double txnAmount;
    @Column(name="VDF_ISS_CONTROL_NUMBER")
    private Integer issControlNumber;
    @Column(name="VDF_REQ_REASON_CODE")
    private String reqReasonCode;
    @Column(name="VDF_RET_REQ_NUMBER")
    private String retReqNumber;
    @Column(name="VDF_REQ_FULFILL_METHOD")
    private String reqFulfillMethod;
    @Column(name="VDF_CASE_ID")
    private String caseId;

    public static VisaDisputeFinanceDataEntityBuilder builder() {
        return new VisaDisputeFinanceDataEntityBuilder();
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

    public Integer getInterfaceCode() {
        return this.interfaceCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public String getSource() {
        return this.source;
    }

    public Integer getOrgTxnSerNumber() {
        return this.orgTxnSerNumber;
    }

    public Integer getRepresentSerNumber() {
        return this.representSerNumber;
    }

    public Integer getPrrSerNumber() {
        return this.prrSerNumber;
    }

    public Integer getCbrSerNumber() {
        return this.cbrSerNumber;
    }

    public String getDisputeStatus() {
        return this.disputeStatus;
    }

    public String getDisputeTxnCode() {
        return this.disputeTxnCode;
    }

    public String getOrgRecIndicator() {
        return this.orgRecIndicator;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getAccNumber() {
        return this.accNumber;
    }

    public String getAcqRefNumber() {
        return this.acqRefNumber;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public Double getSourceAmount() {
        return this.sourceAmount;
    }

    public String getSourceCurrency() {
        return this.sourceCurrency;
    }

    public String getMeName() {
        return this.meName;
    }

    public String getMeCity() {
        return this.meCity;
    }

    public String getMeConCode() {
        return this.meConCode;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getUsageCode() {
        return this.usageCode;
    }

    public String getMeZipCode() {
        return this.meZipCode;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public Character getAuthSourceCode() {
        return this.authSourceCode;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
    }

    public LocalDate getCenterProcDate() {
        return this.centerProcDate;
    }

    public String getCardAcceptId() {
        return this.cardAcceptId;
    }

    public Character getReimbAttribute() {
        return this.reimbAttribute;
    }

    public String getNtwIdentificationCode() {
        return this.ntwIdentificationCode;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public String getDisputeFinancialReasonCode() {
        return this.disputeFinancialReasonCode;
    }

    public Character getSetlFlag() {
        return this.setlFlag;
    }

    public Double getOrgTxnAmount() {
        return this.orgTxnAmount;
    }

    public String getOrgTxnCurrCode() {
        return this.orgTxnCurrCode;
    }

    public Character getSpecialCbIndicator() {
        return this.specialCbIndicator;
    }

    public LocalDate getBussDate() {
        return this.bussDate;
    }

    public String getBchBranchCode() {
        return this.bchBranchCode;
    }

    public Character getFloorLimitInd() {
        return this.floorLimitInd;
    }

    public Character getExceptionFileIndicator() {
        return this.exceptionFileIndicator;
    }

    public String getPcasIndicator() {
        return this.pcasIndicator;
    }

    public Double getDestinationAmount() {
        return this.destinationAmount;
    }

    public String getDestinationCurrency() {
        return this.destinationCurrency;
    }

    public String getCardAcceptName() {
        return this.cardAcceptName;
    }

    public String getCardAcceptCity() {
        return this.cardAcceptCity;
    }

    public String getCardAcceptCountry() {
        return this.cardAcceptCountry;
    }

    public String getReqPaymentService() {
        return this.reqPaymentService;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public String getPosCapability() {
        return this.posCapability;
    }

    public String getIntFeeIndicator() {
        return this.intFeeIndicator;
    }

    public String getCardHolderId() {
        return this.cardHolderId;
    }

    public String getAcqWorkstationBin() {
        return this.acqWorkstationBin;
    }

    public String getIssWorkstationBin() {
        return this.issWorkstationBin;
    }

    public String getCbRefNumber() {
        return this.cbRefNumber;
    }

    public String getDocIndicator() {
        return this.docIndicator;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public String getSpecialCondition() {
        return this.specialCondition;
    }

    public String getFeeProgram() {
        return this.feeProgram;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public Double getNationalReimbFee() {
        return this.nationalReimbFee;
    }

    public String getEcomIndicator() {
        return this.ecomIndicator;
    }

    public String getSpecialCdIndicator() {
        return this.specialCdIndicator;
    }

    public Character getChipConditionCode() {
        return this.chipConditionCode;
    }

    public Character getPosEnvironment() {
        return this.posEnvironment;
    }

    public Double getAuthAmount() {
        return this.authAmount;
    }

    public String getAuthCurrency() {
        return this.authCurrency;
    }

    public String getAuthRespCode() {
        return this.authRespCode;
    }

    public String getCbRightIndicator() {
        return this.cbRightIndicator;
    }

    public String getClearingSeqNumber() {
        return this.clearingSeqNumber;
    }

    public String getCvv2Results() {
        return this.cvv2Results;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public Double getInterChangeFee() {
        return this.interChangeFee;
    }

    public String getTrlCapableProfile() {
        return this.trlCapableProfile;
    }

    public String getTrlCountryCode() {
        return this.trlCountryCode;
    }

    public String getIssAppDataB2() {
        return this.issAppDataB2;
    }

    public String getIssAppDataB3() {
        return this.issAppDataB3;
    }

    public String getRevIndicator() {
        return this.revIndicator;
    }

    public String getActiveStatus() {
        return this.activeStatus;
    }

    public LocalDateTime getActivationDate() {
        return this.activationDate;
    }

    public String getReprFlag() {
        return this.reprFlag;
    }

    public Long getPriority() {
        return this.priority;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getEncrypTcardNumber() {
        return this.encrypTcardNumber;
    }

    public Long getPaymentRefNumber() {
        return this.paymentRefNumber;
    }

    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Integer getIssControlNumber() {
        return this.issControlNumber;
    }

    public String getReqReasonCode() {
        return this.reqReasonCode;
    }

    public String getRetReqNumber() {
        return this.retReqNumber;
    }

    public String getReqFulfillMethod() {
        return this.reqFulfillMethod;
    }

    public String getCaseId() {
        return this.caseId;
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

    public void setInterfaceCode(Integer interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setOrgTxnSerNumber(Integer orgTxnSerNumber) {
        this.orgTxnSerNumber = orgTxnSerNumber;
    }

    public void setRepresentSerNumber(Integer representSerNumber) {
        this.representSerNumber = representSerNumber;
    }

    public void setPrrSerNumber(Integer prrSerNumber) {
        this.prrSerNumber = prrSerNumber;
    }

    public void setCbrSerNumber(Integer cbrSerNumber) {
        this.cbrSerNumber = cbrSerNumber;
    }

    public void setDisputeStatus(String disputeStatus) {
        this.disputeStatus = disputeStatus;
    }

    public void setDisputeTxnCode(String disputeTxnCode) {
        this.disputeTxnCode = disputeTxnCode;
    }

    public void setOrgRecIndicator(String orgRecIndicator) {
        this.orgRecIndicator = orgRecIndicator;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void setAcqRefNumber(String acqRefNumber) {
        this.acqRefNumber = acqRefNumber;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public void setMeCity(String meCity) {
        this.meCity = meCity;
    }

    public void setMeConCode(String meConCode) {
        this.meConCode = meConCode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setUsageCode(String usageCode) {
        this.usageCode = usageCode;
    }

    public void setMeZipCode(String meZipCode) {
        this.meZipCode = meZipCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setAuthSourceCode(Character authSourceCode) {
        this.authSourceCode = authSourceCode;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public void setCenterProcDate(LocalDate centerProcDate) {
        this.centerProcDate = centerProcDate;
    }

    public void setCardAcceptId(String cardAcceptId) {
        this.cardAcceptId = cardAcceptId;
    }

    public void setReimbAttribute(Character reimbAttribute) {
        this.reimbAttribute = reimbAttribute;
    }

    public void setNtwIdentificationCode(String ntwIdentificationCode) {
        this.ntwIdentificationCode = ntwIdentificationCode;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setDisputeFinancialReasonCode(String disputeFinancialReasonCode) {
        this.disputeFinancialReasonCode = disputeFinancialReasonCode;
    }

    public void setSetlFlag(Character setlFlag) {
        this.setlFlag = setlFlag;
    }

    public void setOrgTxnAmount(Double orgTxnAmount) {
        this.orgTxnAmount = orgTxnAmount;
    }

    public void setOrgTxnCurrCode(String orgTxnCurrCode) {
        this.orgTxnCurrCode = orgTxnCurrCode;
    }

    public void setSpecialCbIndicator(Character specialCbIndicator) {
        this.specialCbIndicator = specialCbIndicator;
    }

    public void setBussDate(LocalDate bussDate) {
        this.bussDate = bussDate;
    }

    public void setBchBranchCode(String bchBranchCode) {
        this.bchBranchCode = bchBranchCode;
    }

    public void setFloorLimitInd(Character floorLimitInd) {
        this.floorLimitInd = floorLimitInd;
    }

    public void setExceptionFileIndicator(Character exceptionFileIndicator) {
        this.exceptionFileIndicator = exceptionFileIndicator;
    }

    public void setPcasIndicator(String pcasIndicator) {
        this.pcasIndicator = pcasIndicator;
    }

    public void setDestinationAmount(Double destinationAmount) {
        this.destinationAmount = destinationAmount;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public void setCardAcceptName(String cardAcceptName) {
        this.cardAcceptName = cardAcceptName;
    }

    public void setCardAcceptCity(String cardAcceptCity) {
        this.cardAcceptCity = cardAcceptCity;
    }

    public void setCardAcceptCountry(String cardAcceptCountry) {
        this.cardAcceptCountry = cardAcceptCountry;
    }

    public void setReqPaymentService(String reqPaymentService) {
        this.reqPaymentService = reqPaymentService;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setPosCapability(String posCapability) {
        this.posCapability = posCapability;
    }

    public void setIntFeeIndicator(String intFeeIndicator) {
        this.intFeeIndicator = intFeeIndicator;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public void setAcqWorkstationBin(String acqWorkstationBin) {
        this.acqWorkstationBin = acqWorkstationBin;
    }

    public void setIssWorkstationBin(String issWorkstationBin) {
        this.issWorkstationBin = issWorkstationBin;
    }

    public void setCbRefNumber(String cbRefNumber) {
        this.cbRefNumber = cbRefNumber;
    }

    public void setDocIndicator(String docIndicator) {
        this.docIndicator = docIndicator;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }

    public void setFeeProgram(String feeProgram) {
        this.feeProgram = feeProgram;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setNationalReimbFee(Double nationalReimbFee) {
        this.nationalReimbFee = nationalReimbFee;
    }

    public void setEcomIndicator(String ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }

    public void setSpecialCdIndicator(String specialCdIndicator) {
        this.specialCdIndicator = specialCdIndicator;
    }

    public void setChipConditionCode(Character chipConditionCode) {
        this.chipConditionCode = chipConditionCode;
    }

    public void setPosEnvironment(Character posEnvironment) {
        this.posEnvironment = posEnvironment;
    }

    public void setAuthAmount(Double authAmount) {
        this.authAmount = authAmount;
    }

    public void setAuthCurrency(String authCurrency) {
        this.authCurrency = authCurrency;
    }

    public void setAuthRespCode(String authRespCode) {
        this.authRespCode = authRespCode;
    }

    public void setCbRightIndicator(String cbRightIndicator) {
        this.cbRightIndicator = cbRightIndicator;
    }

    public void setClearingSeqNumber(String clearingSeqNumber) {
        this.clearingSeqNumber = clearingSeqNumber;
    }

    public void setCvv2Results(String cvv2Results) {
        this.cvv2Results = cvv2Results;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setInterChangeFee(Double interChangeFee) {
        this.interChangeFee = interChangeFee;
    }

    public void setTrlCapableProfile(String trlCapableProfile) {
        this.trlCapableProfile = trlCapableProfile;
    }

    public void setTrlCountryCode(String trlCountryCode) {
        this.trlCountryCode = trlCountryCode;
    }

    public void setIssAppDataB2(String issAppDataB2) {
        this.issAppDataB2 = issAppDataB2;
    }

    public void setIssAppDataB3(String issAppDataB3) {
        this.issAppDataB3 = issAppDataB3;
    }

    public void setRevIndicator(String revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public void setReprFlag(String reprFlag) {
        this.reprFlag = reprFlag;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setEncrypTcardNumber(String encrypTcardNumber) {
        this.encrypTcardNumber = encrypTcardNumber;
    }

    public void setPaymentRefNumber(Long paymentRefNumber) {
        this.paymentRefNumber = paymentRefNumber;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setIssControlNumber(Integer issControlNumber) {
        this.issControlNumber = issControlNumber;
    }

    public void setReqReasonCode(String reqReasonCode) {
        this.reqReasonCode = reqReasonCode;
    }

    public void setRetReqNumber(String retReqNumber) {
        this.retReqNumber = retReqNumber;
    }

    public void setReqFulfillMethod(String reqFulfillMethod) {
        this.reqFulfillMethod = reqFulfillMethod;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaDisputeFinanceDataEntity)) {
            return false;
        }
        VisaDisputeFinanceDataEntity other = (VisaDisputeFinanceDataEntity)o;
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
        Integer this$interfaceCode = this.getInterfaceCode();
        Integer other$interfaceCode = other.getInterfaceCode();
        if (this$interfaceCode == null ? other$interfaceCode != null : !((Object)this$interfaceCode).equals(other$interfaceCode)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$orgTxnSerNumber = this.getOrgTxnSerNumber();
        Integer other$orgTxnSerNumber = other.getOrgTxnSerNumber();
        if (this$orgTxnSerNumber == null ? other$orgTxnSerNumber != null : !((Object)this$orgTxnSerNumber).equals(other$orgTxnSerNumber)) {
            return false;
        }
        Integer this$representSerNumber = this.getRepresentSerNumber();
        Integer other$representSerNumber = other.getRepresentSerNumber();
        if (this$representSerNumber == null ? other$representSerNumber != null : !((Object)this$representSerNumber).equals(other$representSerNumber)) {
            return false;
        }
        Integer this$prrSerNumber = this.getPrrSerNumber();
        Integer other$prrSerNumber = other.getPrrSerNumber();
        if (this$prrSerNumber == null ? other$prrSerNumber != null : !((Object)this$prrSerNumber).equals(other$prrSerNumber)) {
            return false;
        }
        Integer this$cbrSerNumber = this.getCbrSerNumber();
        Integer other$cbrSerNumber = other.getCbrSerNumber();
        if (this$cbrSerNumber == null ? other$cbrSerNumber != null : !((Object)this$cbrSerNumber).equals(other$cbrSerNumber)) {
            return false;
        }
        Double this$sourceAmount = this.getSourceAmount();
        Double other$sourceAmount = other.getSourceAmount();
        if (this$sourceAmount == null ? other$sourceAmount != null : !((Object)this$sourceAmount).equals(other$sourceAmount)) {
            return false;
        }
        Character this$authSourceCode = this.getAuthSourceCode();
        Character other$authSourceCode = other.getAuthSourceCode();
        if (this$authSourceCode == null ? other$authSourceCode != null : !((Object)this$authSourceCode).equals(other$authSourceCode)) {
            return false;
        }
        Character this$reimbAttribute = this.getReimbAttribute();
        Character other$reimbAttribute = other.getReimbAttribute();
        if (this$reimbAttribute == null ? other$reimbAttribute != null : !((Object)this$reimbAttribute).equals(other$reimbAttribute)) {
            return false;
        }
        Character this$setlFlag = this.getSetlFlag();
        Character other$setlFlag = other.getSetlFlag();
        if (this$setlFlag == null ? other$setlFlag != null : !((Object)this$setlFlag).equals(other$setlFlag)) {
            return false;
        }
        Double this$orgTxnAmount = this.getOrgTxnAmount();
        Double other$orgTxnAmount = other.getOrgTxnAmount();
        if (this$orgTxnAmount == null ? other$orgTxnAmount != null : !((Object)this$orgTxnAmount).equals(other$orgTxnAmount)) {
            return false;
        }
        Character this$specialCbIndicator = this.getSpecialCbIndicator();
        Character other$specialCbIndicator = other.getSpecialCbIndicator();
        if (this$specialCbIndicator == null ? other$specialCbIndicator != null : !((Object)this$specialCbIndicator).equals(other$specialCbIndicator)) {
            return false;
        }
        Character this$floorLimitInd = this.getFloorLimitInd();
        Character other$floorLimitInd = other.getFloorLimitInd();
        if (this$floorLimitInd == null ? other$floorLimitInd != null : !((Object)this$floorLimitInd).equals(other$floorLimitInd)) {
            return false;
        }
        Character this$exceptionFileIndicator = this.getExceptionFileIndicator();
        Character other$exceptionFileIndicator = other.getExceptionFileIndicator();
        if (this$exceptionFileIndicator == null ? other$exceptionFileIndicator != null : !((Object)this$exceptionFileIndicator).equals(other$exceptionFileIndicator)) {
            return false;
        }
        Double this$destinationAmount = this.getDestinationAmount();
        Double other$destinationAmount = other.getDestinationAmount();
        if (this$destinationAmount == null ? other$destinationAmount != null : !((Object)this$destinationAmount).equals(other$destinationAmount)) {
            return false;
        }
        Double this$nationalReimbFee = this.getNationalReimbFee();
        Double other$nationalReimbFee = other.getNationalReimbFee();
        if (this$nationalReimbFee == null ? other$nationalReimbFee != null : !((Object)this$nationalReimbFee).equals(other$nationalReimbFee)) {
            return false;
        }
        Character this$chipConditionCode = this.getChipConditionCode();
        Character other$chipConditionCode = other.getChipConditionCode();
        if (this$chipConditionCode == null ? other$chipConditionCode != null : !((Object)this$chipConditionCode).equals(other$chipConditionCode)) {
            return false;
        }
        Character this$posEnvironment = this.getPosEnvironment();
        Character other$posEnvironment = other.getPosEnvironment();
        if (this$posEnvironment == null ? other$posEnvironment != null : !((Object)this$posEnvironment).equals(other$posEnvironment)) {
            return false;
        }
        Double this$authAmount = this.getAuthAmount();
        Double other$authAmount = other.getAuthAmount();
        if (this$authAmount == null ? other$authAmount != null : !((Object)this$authAmount).equals(other$authAmount)) {
            return false;
        }
        Double this$interChangeFee = this.getInterChangeFee();
        Double other$interChangeFee = other.getInterChangeFee();
        if (this$interChangeFee == null ? other$interChangeFee != null : !((Object)this$interChangeFee).equals(other$interChangeFee)) {
            return false;
        }
        Long this$priority = this.getPriority();
        Long other$priority = other.getPriority();
        if (this$priority == null ? other$priority != null : !((Object)this$priority).equals(other$priority)) {
            return false;
        }
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        Long this$paymentRefNumber = this.getPaymentRefNumber();
        Long other$paymentRefNumber = other.getPaymentRefNumber();
        if (this$paymentRefNumber == null ? other$paymentRefNumber != null : !((Object)this$paymentRefNumber).equals(other$paymentRefNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Integer this$issControlNumber = this.getIssControlNumber();
        Integer other$issControlNumber = other.getIssControlNumber();
        if (this$issControlNumber == null ? other$issControlNumber != null : !((Object)this$issControlNumber).equals(other$issControlNumber)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$source = this.getSource();
        String other$source = other.getSource();
        if (this$source == null ? other$source != null : !this$source.equals(other$source)) {
            return false;
        }
        String this$disputeStatus = this.getDisputeStatus();
        String other$disputeStatus = other.getDisputeStatus();
        if (this$disputeStatus == null ? other$disputeStatus != null : !this$disputeStatus.equals(other$disputeStatus)) {
            return false;
        }
        String this$disputeTxnCode = this.getDisputeTxnCode();
        String other$disputeTxnCode = other.getDisputeTxnCode();
        if (this$disputeTxnCode == null ? other$disputeTxnCode != null : !this$disputeTxnCode.equals(other$disputeTxnCode)) {
            return false;
        }
        String this$orgRecIndicator = this.getOrgRecIndicator();
        String other$orgRecIndicator = other.getOrgRecIndicator();
        if (this$orgRecIndicator == null ? other$orgRecIndicator != null : !this$orgRecIndicator.equals(other$orgRecIndicator)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$accNumber = this.getAccNumber();
        String other$accNumber = other.getAccNumber();
        if (this$accNumber == null ? other$accNumber != null : !this$accNumber.equals(other$accNumber)) {
            return false;
        }
        String this$acqRefNumber = this.getAcqRefNumber();
        String other$acqRefNumber = other.getAcqRefNumber();
        if (this$acqRefNumber == null ? other$acqRefNumber != null : !this$acqRefNumber.equals(other$acqRefNumber)) {
            return false;
        }
        LocalDate this$purchaseDate = this.getPurchaseDate();
        LocalDate other$purchaseDate = other.getPurchaseDate();
        if (this$purchaseDate == null ? other$purchaseDate != null : !((Object)this$purchaseDate).equals(other$purchaseDate)) {
            return false;
        }
        String this$sourceCurrency = this.getSourceCurrency();
        String other$sourceCurrency = other.getSourceCurrency();
        if (this$sourceCurrency == null ? other$sourceCurrency != null : !this$sourceCurrency.equals(other$sourceCurrency)) {
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
        String this$meConCode = this.getMeConCode();
        String other$meConCode = other.getMeConCode();
        if (this$meConCode == null ? other$meConCode != null : !this$meConCode.equals(other$meConCode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$usageCode = this.getUsageCode();
        String other$usageCode = other.getUsageCode();
        if (this$usageCode == null ? other$usageCode != null : !this$usageCode.equals(other$usageCode)) {
            return false;
        }
        String this$meZipCode = this.getMeZipCode();
        String other$meZipCode = other.getMeZipCode();
        if (this$meZipCode == null ? other$meZipCode != null : !this$meZipCode.equals(other$meZipCode)) {
            return false;
        }
        String this$authCode = this.getAuthCode();
        String other$authCode = other.getAuthCode();
        if (this$authCode == null ? other$authCode != null : !this$authCode.equals(other$authCode)) {
            return false;
        }
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode)) {
            return false;
        }
        LocalDate this$centerProcDate = this.getCenterProcDate();
        LocalDate other$centerProcDate = other.getCenterProcDate();
        if (this$centerProcDate == null ? other$centerProcDate != null : !((Object)this$centerProcDate).equals(other$centerProcDate)) {
            return false;
        }
        String this$cardAcceptId = this.getCardAcceptId();
        String other$cardAcceptId = other.getCardAcceptId();
        if (this$cardAcceptId == null ? other$cardAcceptId != null : !this$cardAcceptId.equals(other$cardAcceptId)) {
            return false;
        }
        String this$ntwIdentificationCode = this.getNtwIdentificationCode();
        String other$ntwIdentificationCode = other.getNtwIdentificationCode();
        if (this$ntwIdentificationCode == null ? other$ntwIdentificationCode != null : !this$ntwIdentificationCode.equals(other$ntwIdentificationCode)) {
            return false;
        }
        String this$productId = this.getProductId();
        String other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) {
            return false;
        }
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
            return false;
        }
        String this$disputeFinancialReasonCode = this.getDisputeFinancialReasonCode();
        String other$disputeFinancialReasonCode = other.getDisputeFinancialReasonCode();
        if (this$disputeFinancialReasonCode == null ? other$disputeFinancialReasonCode != null : !this$disputeFinancialReasonCode.equals(other$disputeFinancialReasonCode)) {
            return false;
        }
        String this$orgTxnCurrCode = this.getOrgTxnCurrCode();
        String other$orgTxnCurrCode = other.getOrgTxnCurrCode();
        if (this$orgTxnCurrCode == null ? other$orgTxnCurrCode != null : !this$orgTxnCurrCode.equals(other$orgTxnCurrCode)) {
            return false;
        }
        LocalDate this$bussDate = this.getBussDate();
        LocalDate other$bussDate = other.getBussDate();
        if (this$bussDate == null ? other$bussDate != null : !((Object)this$bussDate).equals(other$bussDate)) {
            return false;
        }
        String this$bchBranchCode = this.getBchBranchCode();
        String other$bchBranchCode = other.getBchBranchCode();
        if (this$bchBranchCode == null ? other$bchBranchCode != null : !this$bchBranchCode.equals(other$bchBranchCode)) {
            return false;
        }
        String this$pcasIndicator = this.getPcasIndicator();
        String other$pcasIndicator = other.getPcasIndicator();
        if (this$pcasIndicator == null ? other$pcasIndicator != null : !this$pcasIndicator.equals(other$pcasIndicator)) {
            return false;
        }
        String this$destinationCurrency = this.getDestinationCurrency();
        String other$destinationCurrency = other.getDestinationCurrency();
        if (this$destinationCurrency == null ? other$destinationCurrency != null : !this$destinationCurrency.equals(other$destinationCurrency)) {
            return false;
        }
        String this$cardAcceptName = this.getCardAcceptName();
        String other$cardAcceptName = other.getCardAcceptName();
        if (this$cardAcceptName == null ? other$cardAcceptName != null : !this$cardAcceptName.equals(other$cardAcceptName)) {
            return false;
        }
        String this$cardAcceptCity = this.getCardAcceptCity();
        String other$cardAcceptCity = other.getCardAcceptCity();
        if (this$cardAcceptCity == null ? other$cardAcceptCity != null : !this$cardAcceptCity.equals(other$cardAcceptCity)) {
            return false;
        }
        String this$cardAcceptCountry = this.getCardAcceptCountry();
        String other$cardAcceptCountry = other.getCardAcceptCountry();
        if (this$cardAcceptCountry == null ? other$cardAcceptCountry != null : !this$cardAcceptCountry.equals(other$cardAcceptCountry)) {
            return false;
        }
        String this$reqPaymentService = this.getReqPaymentService();
        String other$reqPaymentService = other.getReqPaymentService();
        if (this$reqPaymentService == null ? other$reqPaymentService != null : !this$reqPaymentService.equals(other$reqPaymentService)) {
            return false;
        }
        String this$reasonCode = this.getReasonCode();
        String other$reasonCode = other.getReasonCode();
        if (this$reasonCode == null ? other$reasonCode != null : !this$reasonCode.equals(other$reasonCode)) {
            return false;
        }
        String this$posCapability = this.getPosCapability();
        String other$posCapability = other.getPosCapability();
        if (this$posCapability == null ? other$posCapability != null : !this$posCapability.equals(other$posCapability)) {
            return false;
        }
        String this$intFeeIndicator = this.getIntFeeIndicator();
        String other$intFeeIndicator = other.getIntFeeIndicator();
        if (this$intFeeIndicator == null ? other$intFeeIndicator != null : !this$intFeeIndicator.equals(other$intFeeIndicator)) {
            return false;
        }
        String this$cardHolderId = this.getCardHolderId();
        String other$cardHolderId = other.getCardHolderId();
        if (this$cardHolderId == null ? other$cardHolderId != null : !this$cardHolderId.equals(other$cardHolderId)) {
            return false;
        }
        String this$acqWorkstationBin = this.getAcqWorkstationBin();
        String other$acqWorkstationBin = other.getAcqWorkstationBin();
        if (this$acqWorkstationBin == null ? other$acqWorkstationBin != null : !this$acqWorkstationBin.equals(other$acqWorkstationBin)) {
            return false;
        }
        String this$issWorkstationBin = this.getIssWorkstationBin();
        String other$issWorkstationBin = other.getIssWorkstationBin();
        if (this$issWorkstationBin == null ? other$issWorkstationBin != null : !this$issWorkstationBin.equals(other$issWorkstationBin)) {
            return false;
        }
        String this$cbRefNumber = this.getCbRefNumber();
        String other$cbRefNumber = other.getCbRefNumber();
        if (this$cbRefNumber == null ? other$cbRefNumber != null : !this$cbRefNumber.equals(other$cbRefNumber)) {
            return false;
        }
        String this$docIndicator = this.getDocIndicator();
        String other$docIndicator = other.getDocIndicator();
        if (this$docIndicator == null ? other$docIndicator != null : !this$docIndicator.equals(other$docIndicator)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
            return false;
        }
        String this$specialCondition = this.getSpecialCondition();
        String other$specialCondition = other.getSpecialCondition();
        if (this$specialCondition == null ? other$specialCondition != null : !this$specialCondition.equals(other$specialCondition)) {
            return false;
        }
        String this$feeProgram = this.getFeeProgram();
        String other$feeProgram = other.getFeeProgram();
        if (this$feeProgram == null ? other$feeProgram != null : !this$feeProgram.equals(other$feeProgram)) {
            return false;
        }
        String this$merchantCode = this.getMerchantCode();
        String other$merchantCode = other.getMerchantCode();
        if (this$merchantCode == null ? other$merchantCode != null : !this$merchantCode.equals(other$merchantCode)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$ecomIndicator = this.getEcomIndicator();
        String other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null ? other$ecomIndicator != null : !this$ecomIndicator.equals(other$ecomIndicator)) {
            return false;
        }
        String this$specialCdIndicator = this.getSpecialCdIndicator();
        String other$specialCdIndicator = other.getSpecialCdIndicator();
        if (this$specialCdIndicator == null ? other$specialCdIndicator != null : !this$specialCdIndicator.equals(other$specialCdIndicator)) {
            return false;
        }
        String this$authCurrency = this.getAuthCurrency();
        String other$authCurrency = other.getAuthCurrency();
        if (this$authCurrency == null ? other$authCurrency != null : !this$authCurrency.equals(other$authCurrency)) {
            return false;
        }
        String this$authRespCode = this.getAuthRespCode();
        String other$authRespCode = other.getAuthRespCode();
        if (this$authRespCode == null ? other$authRespCode != null : !this$authRespCode.equals(other$authRespCode)) {
            return false;
        }
        String this$cbRightIndicator = this.getCbRightIndicator();
        String other$cbRightIndicator = other.getCbRightIndicator();
        if (this$cbRightIndicator == null ? other$cbRightIndicator != null : !this$cbRightIndicator.equals(other$cbRightIndicator)) {
            return false;
        }
        String this$clearingSeqNumber = this.getClearingSeqNumber();
        String other$clearingSeqNumber = other.getClearingSeqNumber();
        if (this$clearingSeqNumber == null ? other$clearingSeqNumber != null : !this$clearingSeqNumber.equals(other$clearingSeqNumber)) {
            return false;
        }
        String this$cvv2Results = this.getCvv2Results();
        String other$cvv2Results = other.getCvv2Results();
        if (this$cvv2Results == null ? other$cvv2Results != null : !this$cvv2Results.equals(other$cvv2Results)) {
            return false;
        }
        String this$productCode = this.getProductCode();
        String other$productCode = other.getProductCode();
        if (this$productCode == null ? other$productCode != null : !this$productCode.equals(other$productCode)) {
            return false;
        }
        String this$trlCapableProfile = this.getTrlCapableProfile();
        String other$trlCapableProfile = other.getTrlCapableProfile();
        if (this$trlCapableProfile == null ? other$trlCapableProfile != null : !this$trlCapableProfile.equals(other$trlCapableProfile)) {
            return false;
        }
        String this$trlCountryCode = this.getTrlCountryCode();
        String other$trlCountryCode = other.getTrlCountryCode();
        if (this$trlCountryCode == null ? other$trlCountryCode != null : !this$trlCountryCode.equals(other$trlCountryCode)) {
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
        String this$revIndicator = this.getRevIndicator();
        String other$revIndicator = other.getRevIndicator();
        if (this$revIndicator == null ? other$revIndicator != null : !this$revIndicator.equals(other$revIndicator)) {
            return false;
        }
        String this$activeStatus = this.getActiveStatus();
        String other$activeStatus = other.getActiveStatus();
        if (this$activeStatus == null ? other$activeStatus != null : !this$activeStatus.equals(other$activeStatus)) {
            return false;
        }
        LocalDateTime this$activationDate = this.getActivationDate();
        LocalDateTime other$activationDate = other.getActivationDate();
        if (this$activationDate == null ? other$activationDate != null : !((Object)this$activationDate).equals(other$activationDate)) {
            return false;
        }
        String this$reprFlag = this.getReprFlag();
        String other$reprFlag = other.getReprFlag();
        if (this$reprFlag == null ? other$reprFlag != null : !this$reprFlag.equals(other$reprFlag)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        if (this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks)) {
            return false;
        }
        String this$encrypTcardNumber = this.getEncrypTcardNumber();
        String other$encrypTcardNumber = other.getEncrypTcardNumber();
        if (this$encrypTcardNumber == null ? other$encrypTcardNumber != null : !this$encrypTcardNumber.equals(other$encrypTcardNumber)) {
            return false;
        }
        String this$txnCurrency = this.getTxnCurrency();
        String other$txnCurrency = other.getTxnCurrency();
        if (this$txnCurrency == null ? other$txnCurrency != null : !this$txnCurrency.equals(other$txnCurrency)) {
            return false;
        }
        String this$reqReasonCode = this.getReqReasonCode();
        String other$reqReasonCode = other.getReqReasonCode();
        if (this$reqReasonCode == null ? other$reqReasonCode != null : !this$reqReasonCode.equals(other$reqReasonCode)) {
            return false;
        }
        String this$retReqNumber = this.getRetReqNumber();
        String other$retReqNumber = other.getRetReqNumber();
        if (this$retReqNumber == null ? other$retReqNumber != null : !this$retReqNumber.equals(other$retReqNumber)) {
            return false;
        }
        String this$reqFulfillMethod = this.getReqFulfillMethod();
        String other$reqFulfillMethod = other.getReqFulfillMethod();
        if (this$reqFulfillMethod == null ? other$reqFulfillMethod != null : !this$reqFulfillMethod.equals(other$reqFulfillMethod)) {
            return false;
        }
        String this$caseId = this.getCaseId();
        String other$caseId = other.getCaseId();
        return !(this$caseId == null ? other$caseId != null : !this$caseId.equals(other$caseId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof VisaDisputeFinanceDataEntity;
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
        Integer $interfaceCode = this.getInterfaceCode();
        result = result * 59 + ($interfaceCode == null ? 43 : ((Object)$interfaceCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $orgTxnSerNumber = this.getOrgTxnSerNumber();
        result = result * 59 + ($orgTxnSerNumber == null ? 43 : ((Object)$orgTxnSerNumber).hashCode());
        Integer $representSerNumber = this.getRepresentSerNumber();
        result = result * 59 + ($representSerNumber == null ? 43 : ((Object)$representSerNumber).hashCode());
        Integer $prrSerNumber = this.getPrrSerNumber();
        result = result * 59 + ($prrSerNumber == null ? 43 : ((Object)$prrSerNumber).hashCode());
        Integer $cbrSerNumber = this.getCbrSerNumber();
        result = result * 59 + ($cbrSerNumber == null ? 43 : ((Object)$cbrSerNumber).hashCode());
        Double $sourceAmount = this.getSourceAmount();
        result = result * 59 + ($sourceAmount == null ? 43 : ((Object)$sourceAmount).hashCode());
        Character $authSourceCode = this.getAuthSourceCode();
        result = result * 59 + ($authSourceCode == null ? 43 : ((Object)$authSourceCode).hashCode());
        Character $reimbAttribute = this.getReimbAttribute();
        result = result * 59 + ($reimbAttribute == null ? 43 : ((Object)$reimbAttribute).hashCode());
        Character $setlFlag = this.getSetlFlag();
        result = result * 59 + ($setlFlag == null ? 43 : ((Object)$setlFlag).hashCode());
        Double $orgTxnAmount = this.getOrgTxnAmount();
        result = result * 59 + ($orgTxnAmount == null ? 43 : ((Object)$orgTxnAmount).hashCode());
        Character $specialCbIndicator = this.getSpecialCbIndicator();
        result = result * 59 + ($specialCbIndicator == null ? 43 : ((Object)$specialCbIndicator).hashCode());
        Character $floorLimitInd = this.getFloorLimitInd();
        result = result * 59 + ($floorLimitInd == null ? 43 : ((Object)$floorLimitInd).hashCode());
        Character $exceptionFileIndicator = this.getExceptionFileIndicator();
        result = result * 59 + ($exceptionFileIndicator == null ? 43 : ((Object)$exceptionFileIndicator).hashCode());
        Double $destinationAmount = this.getDestinationAmount();
        result = result * 59 + ($destinationAmount == null ? 43 : ((Object)$destinationAmount).hashCode());
        Double $nationalReimbFee = this.getNationalReimbFee();
        result = result * 59 + ($nationalReimbFee == null ? 43 : ((Object)$nationalReimbFee).hashCode());
        Character $chipConditionCode = this.getChipConditionCode();
        result = result * 59 + ($chipConditionCode == null ? 43 : ((Object)$chipConditionCode).hashCode());
        Character $posEnvironment = this.getPosEnvironment();
        result = result * 59 + ($posEnvironment == null ? 43 : ((Object)$posEnvironment).hashCode());
        Double $authAmount = this.getAuthAmount();
        result = result * 59 + ($authAmount == null ? 43 : ((Object)$authAmount).hashCode());
        Double $interChangeFee = this.getInterChangeFee();
        result = result * 59 + ($interChangeFee == null ? 43 : ((Object)$interChangeFee).hashCode());
        Long $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : ((Object)$priority).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        Long $paymentRefNumber = this.getPaymentRefNumber();
        result = result * 59 + ($paymentRefNumber == null ? 43 : ((Object)$paymentRefNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Integer $issControlNumber = this.getIssControlNumber();
        result = result * 59 + ($issControlNumber == null ? 43 : ((Object)$issControlNumber).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $source = this.getSource();
        result = result * 59 + ($source == null ? 43 : $source.hashCode());
        String $disputeStatus = this.getDisputeStatus();
        result = result * 59 + ($disputeStatus == null ? 43 : $disputeStatus.hashCode());
        String $disputeTxnCode = this.getDisputeTxnCode();
        result = result * 59 + ($disputeTxnCode == null ? 43 : $disputeTxnCode.hashCode());
        String $orgRecIndicator = this.getOrgRecIndicator();
        result = result * 59 + ($orgRecIndicator == null ? 43 : $orgRecIndicator.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $accNumber = this.getAccNumber();
        result = result * 59 + ($accNumber == null ? 43 : $accNumber.hashCode());
        String $acqRefNumber = this.getAcqRefNumber();
        result = result * 59 + ($acqRefNumber == null ? 43 : $acqRefNumber.hashCode());
        LocalDate $purchaseDate = this.getPurchaseDate();
        result = result * 59 + ($purchaseDate == null ? 43 : ((Object)$purchaseDate).hashCode());
        String $sourceCurrency = this.getSourceCurrency();
        result = result * 59 + ($sourceCurrency == null ? 43 : $sourceCurrency.hashCode());
        String $meName = this.getMeName();
        result = result * 59 + ($meName == null ? 43 : $meName.hashCode());
        String $meCity = this.getMeCity();
        result = result * 59 + ($meCity == null ? 43 : $meCity.hashCode());
        String $meConCode = this.getMeConCode();
        result = result * 59 + ($meConCode == null ? 43 : $meConCode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $usageCode = this.getUsageCode();
        result = result * 59 + ($usageCode == null ? 43 : $usageCode.hashCode());
        String $meZipCode = this.getMeZipCode();
        result = result * 59 + ($meZipCode == null ? 43 : $meZipCode.hashCode());
        String $authCode = this.getAuthCode();
        result = result * 59 + ($authCode == null ? 43 : $authCode.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        LocalDate $centerProcDate = this.getCenterProcDate();
        result = result * 59 + ($centerProcDate == null ? 43 : ((Object)$centerProcDate).hashCode());
        String $cardAcceptId = this.getCardAcceptId();
        result = result * 59 + ($cardAcceptId == null ? 43 : $cardAcceptId.hashCode());
        String $ntwIdentificationCode = this.getNtwIdentificationCode();
        result = result * 59 + ($ntwIdentificationCode == null ? 43 : $ntwIdentificationCode.hashCode());
        String $productId = this.getProductId();
        result = result * 59 + ($productId == null ? 43 : $productId.hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $disputeFinancialReasonCode = this.getDisputeFinancialReasonCode();
        result = result * 59 + ($disputeFinancialReasonCode == null ? 43 : $disputeFinancialReasonCode.hashCode());
        String $orgTxnCurrCode = this.getOrgTxnCurrCode();
        result = result * 59 + ($orgTxnCurrCode == null ? 43 : $orgTxnCurrCode.hashCode());
        LocalDate $bussDate = this.getBussDate();
        result = result * 59 + ($bussDate == null ? 43 : ((Object)$bussDate).hashCode());
        String $bchBranchCode = this.getBchBranchCode();
        result = result * 59 + ($bchBranchCode == null ? 43 : $bchBranchCode.hashCode());
        String $pcasIndicator = this.getPcasIndicator();
        result = result * 59 + ($pcasIndicator == null ? 43 : $pcasIndicator.hashCode());
        String $destinationCurrency = this.getDestinationCurrency();
        result = result * 59 + ($destinationCurrency == null ? 43 : $destinationCurrency.hashCode());
        String $cardAcceptName = this.getCardAcceptName();
        result = result * 59 + ($cardAcceptName == null ? 43 : $cardAcceptName.hashCode());
        String $cardAcceptCity = this.getCardAcceptCity();
        result = result * 59 + ($cardAcceptCity == null ? 43 : $cardAcceptCity.hashCode());
        String $cardAcceptCountry = this.getCardAcceptCountry();
        result = result * 59 + ($cardAcceptCountry == null ? 43 : $cardAcceptCountry.hashCode());
        String $reqPaymentService = this.getReqPaymentService();
        result = result * 59 + ($reqPaymentService == null ? 43 : $reqPaymentService.hashCode());
        String $reasonCode = this.getReasonCode();
        result = result * 59 + ($reasonCode == null ? 43 : $reasonCode.hashCode());
        String $posCapability = this.getPosCapability();
        result = result * 59 + ($posCapability == null ? 43 : $posCapability.hashCode());
        String $intFeeIndicator = this.getIntFeeIndicator();
        result = result * 59 + ($intFeeIndicator == null ? 43 : $intFeeIndicator.hashCode());
        String $cardHolderId = this.getCardHolderId();
        result = result * 59 + ($cardHolderId == null ? 43 : $cardHolderId.hashCode());
        String $acqWorkstationBin = this.getAcqWorkstationBin();
        result = result * 59 + ($acqWorkstationBin == null ? 43 : $acqWorkstationBin.hashCode());
        String $issWorkstationBin = this.getIssWorkstationBin();
        result = result * 59 + ($issWorkstationBin == null ? 43 : $issWorkstationBin.hashCode());
        String $cbRefNumber = this.getCbRefNumber();
        result = result * 59 + ($cbRefNumber == null ? 43 : $cbRefNumber.hashCode());
        String $docIndicator = this.getDocIndicator();
        result = result * 59 + ($docIndicator == null ? 43 : $docIndicator.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $specialCondition = this.getSpecialCondition();
        result = result * 59 + ($specialCondition == null ? 43 : $specialCondition.hashCode());
        String $feeProgram = this.getFeeProgram();
        result = result * 59 + ($feeProgram == null ? 43 : $feeProgram.hashCode());
        String $merchantCode = this.getMerchantCode();
        result = result * 59 + ($merchantCode == null ? 43 : $merchantCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + ($ecomIndicator == null ? 43 : $ecomIndicator.hashCode());
        String $specialCdIndicator = this.getSpecialCdIndicator();
        result = result * 59 + ($specialCdIndicator == null ? 43 : $specialCdIndicator.hashCode());
        String $authCurrency = this.getAuthCurrency();
        result = result * 59 + ($authCurrency == null ? 43 : $authCurrency.hashCode());
        String $authRespCode = this.getAuthRespCode();
        result = result * 59 + ($authRespCode == null ? 43 : $authRespCode.hashCode());
        String $cbRightIndicator = this.getCbRightIndicator();
        result = result * 59 + ($cbRightIndicator == null ? 43 : $cbRightIndicator.hashCode());
        String $clearingSeqNumber = this.getClearingSeqNumber();
        result = result * 59 + ($clearingSeqNumber == null ? 43 : $clearingSeqNumber.hashCode());
        String $cvv2Results = this.getCvv2Results();
        result = result * 59 + ($cvv2Results == null ? 43 : $cvv2Results.hashCode());
        String $productCode = this.getProductCode();
        result = result * 59 + ($productCode == null ? 43 : $productCode.hashCode());
        String $trlCapableProfile = this.getTrlCapableProfile();
        result = result * 59 + ($trlCapableProfile == null ? 43 : $trlCapableProfile.hashCode());
        String $trlCountryCode = this.getTrlCountryCode();
        result = result * 59 + ($trlCountryCode == null ? 43 : $trlCountryCode.hashCode());
        String $issAppDataB2 = this.getIssAppDataB2();
        result = result * 59 + ($issAppDataB2 == null ? 43 : $issAppDataB2.hashCode());
        String $issAppDataB3 = this.getIssAppDataB3();
        result = result * 59 + ($issAppDataB3 == null ? 43 : $issAppDataB3.hashCode());
        String $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : $revIndicator.hashCode());
        String $activeStatus = this.getActiveStatus();
        result = result * 59 + ($activeStatus == null ? 43 : $activeStatus.hashCode());
        LocalDateTime $activationDate = this.getActivationDate();
        result = result * 59 + ($activationDate == null ? 43 : ((Object)$activationDate).hashCode());
        String $reprFlag = this.getReprFlag();
        result = result * 59 + ($reprFlag == null ? 43 : $reprFlag.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        String $encrypTcardNumber = this.getEncrypTcardNumber();
        result = result * 59 + ($encrypTcardNumber == null ? 43 : $encrypTcardNumber.hashCode());
        String $txnCurrency = this.getTxnCurrency();
        result = result * 59 + ($txnCurrency == null ? 43 : $txnCurrency.hashCode());
        String $reqReasonCode = this.getReqReasonCode();
        result = result * 59 + ($reqReasonCode == null ? 43 : $reqReasonCode.hashCode());
        String $retReqNumber = this.getRetReqNumber();
        result = result * 59 + ($retReqNumber == null ? 43 : $retReqNumber.hashCode());
        String $reqFulfillMethod = this.getReqFulfillMethod();
        result = result * 59 + ($reqFulfillMethod == null ? 43 : $reqFulfillMethod.hashCode());
        String $caseId = this.getCaseId();
        result = result * 59 + ($caseId == null ? 43 : $caseId.hashCode());
        return result;
    }

    public String toString() {
        return "VisaDisputeFinanceDataEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", interfaceCode=" + this.getInterfaceCode() + ", jobNumber=" + this.getJobNumber() + ", txnCode=" + this.getTxnCode() + ", source=" + this.getSource() + ", orgTxnSerNumber=" + this.getOrgTxnSerNumber() + ", representSerNumber=" + this.getRepresentSerNumber() + ", prrSerNumber=" + this.getPrrSerNumber() + ", cbrSerNumber=" + this.getCbrSerNumber() + ", disputeStatus=" + this.getDisputeStatus() + ", disputeTxnCode=" + this.getDisputeTxnCode() + ", orgRecIndicator=" + this.getOrgRecIndicator() + ", cardNumber=" + this.getCardNumber() + ", accNumber=" + this.getAccNumber() + ", acqRefNumber=" + this.getAcqRefNumber() + ", purchaseDate=" + String.valueOf(this.getPurchaseDate()) + ", sourceAmount=" + this.getSourceAmount() + ", sourceCurrency=" + this.getSourceCurrency() + ", meName=" + this.getMeName() + ", meCity=" + this.getMeCity() + ", meConCode=" + this.getMeConCode() + ", mcc=" + this.getMcc() + ", usageCode=" + this.getUsageCode() + ", meZipCode=" + this.getMeZipCode() + ", authCode=" + this.getAuthCode() + ", authSourceCode=" + this.getAuthSourceCode() + ", posEntryMode=" + this.getPosEntryMode() + ", centerProcDate=" + String.valueOf(this.getCenterProcDate()) + ", cardAcceptId=" + this.getCardAcceptId() + ", reimbAttribute=" + this.getReimbAttribute() + ", ntwIdentificationCode=" + this.getNtwIdentificationCode() + ", productId=" + this.getProductId() + ", txnId=" + this.getTxnId() + ", disputeFinancialReasonCode=" + this.getDisputeFinancialReasonCode() + ", setlFlag=" + this.getSetlFlag() + ", orgTxnAmount=" + this.getOrgTxnAmount() + ", orgTxnCurrCode=" + this.getOrgTxnCurrCode() + ", specialCbIndicator=" + this.getSpecialCbIndicator() + ", bussDate=" + String.valueOf(this.getBussDate()) + ", bchBranchCode=" + this.getBchBranchCode() + ", floorLimitInd=" + this.getFloorLimitInd() + ", exceptionFileIndicator=" + this.getExceptionFileIndicator() + ", pcasIndicator=" + this.getPcasIndicator() + ", destinationAmount=" + this.getDestinationAmount() + ", destinationCurrency=" + this.getDestinationCurrency() + ", cardAcceptName=" + this.getCardAcceptName() + ", cardAcceptCity=" + this.getCardAcceptCity() + ", cardAcceptCountry=" + this.getCardAcceptCountry() + ", reqPaymentService=" + this.getReqPaymentService() + ", reasonCode=" + this.getReasonCode() + ", posCapability=" + this.getPosCapability() + ", intFeeIndicator=" + this.getIntFeeIndicator() + ", cardHolderId=" + this.getCardHolderId() + ", acqWorkstationBin=" + this.getAcqWorkstationBin() + ", issWorkstationBin=" + this.getIssWorkstationBin() + ", cbRefNumber=" + this.getCbRefNumber() + ", docIndicator=" + this.getDocIndicator() + ", memberText=" + this.getMemberText() + ", specialCondition=" + this.getSpecialCondition() + ", feeProgram=" + this.getFeeProgram() + ", merchantCode=" + this.getMerchantCode() + ", terminalId=" + this.getTerminalId() + ", nationalReimbFee=" + this.getNationalReimbFee() + ", ecomIndicator=" + this.getEcomIndicator() + ", specialCdIndicator=" + this.getSpecialCdIndicator() + ", chipConditionCode=" + this.getChipConditionCode() + ", posEnvironment=" + this.getPosEnvironment() + ", authAmount=" + this.getAuthAmount() + ", authCurrency=" + this.getAuthCurrency() + ", authRespCode=" + this.getAuthRespCode() + ", cbRightIndicator=" + this.getCbRightIndicator() + ", clearingSeqNumber=" + this.getClearingSeqNumber() + ", cvv2Results=" + this.getCvv2Results() + ", productCode=" + this.getProductCode() + ", interChangeFee=" + this.getInterChangeFee() + ", trlCapableProfile=" + this.getTrlCapableProfile() + ", trlCountryCode=" + this.getTrlCountryCode() + ", issAppDataB2=" + this.getIssAppDataB2() + ", issAppDataB3=" + this.getIssAppDataB3() + ", revIndicator=" + this.getRevIndicator() + ", activeStatus=" + this.getActiveStatus() + ", activationDate=" + String.valueOf(this.getActivationDate()) + ", reprFlag=" + this.getReprFlag() + ", priority=" + this.getPriority() + ", remarks=" + this.getRemarks() + ", genStatus=" + this.getGenStatus() + ", encrypTcardNumber=" + this.getEncrypTcardNumber() + ", paymentRefNumber=" + this.getPaymentRefNumber() + ", txnCurrency=" + this.getTxnCurrency() + ", txnAmount=" + this.getTxnAmount() + ", issControlNumber=" + this.getIssControlNumber() + ", reqReasonCode=" + this.getReqReasonCode() + ", retReqNumber=" + this.getRetReqNumber() + ", reqFulfillMethod=" + this.getReqFulfillMethod() + ", caseId=" + this.getCaseId() + ")";
    }

    public VisaDisputeFinanceDataEntity() {
    }

    public VisaDisputeFinanceDataEntity(Integer serNumber, LocalDateTime lastUpdated, Integer updatedUser, Integer institutionCode, Integer interfaceCode, Integer jobNumber, String txnCode, String source, Integer orgTxnSerNumber, Integer representSerNumber, Integer prrSerNumber, Integer cbrSerNumber, String disputeStatus, String disputeTxnCode, String orgRecIndicator, String cardNumber, String accNumber, String acqRefNumber, LocalDate purchaseDate, Double sourceAmount, String sourceCurrency, String meName, String meCity, String meConCode, String mcc, String usageCode, String meZipCode, String authCode, Character authSourceCode, String posEntryMode, LocalDate centerProcDate, String cardAcceptId, Character reimbAttribute, String ntwIdentificationCode, String productId, String txnId, String disputeFinancialReasonCode, Character setlFlag, Double orgTxnAmount, String orgTxnCurrCode, Character specialCbIndicator, LocalDate bussDate, String bchBranchCode, Character floorLimitInd, Character exceptionFileIndicator, String pcasIndicator, Double destinationAmount, String destinationCurrency, String cardAcceptName, String cardAcceptCity, String cardAcceptCountry, String reqPaymentService, String reasonCode, String posCapability, String intFeeIndicator, String cardHolderId, String acqWorkstationBin, String issWorkstationBin, String cbRefNumber, String docIndicator, String memberText, String specialCondition, String feeProgram, String merchantCode, String terminalId, Double nationalReimbFee, String ecomIndicator, String specialCdIndicator, Character chipConditionCode, Character posEnvironment, Double authAmount, String authCurrency, String authRespCode, String cbRightIndicator, String clearingSeqNumber, String cvv2Results, String productCode, Double interChangeFee, String trlCapableProfile, String trlCountryCode, String issAppDataB2, String issAppDataB3, String revIndicator, String activeStatus, LocalDateTime activationDate, String reprFlag, Long priority, String remarks, Integer genStatus, String encrypTcardNumber, Long paymentRefNumber, String txnCurrency, Double txnAmount, Integer issControlNumber, String reqReasonCode, String retReqNumber, String reqFulfillMethod, String caseId) {
        this.serNumber = serNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.interfaceCode = interfaceCode;
        this.jobNumber = jobNumber;
        this.txnCode = txnCode;
        this.source = source;
        this.orgTxnSerNumber = orgTxnSerNumber;
        this.representSerNumber = representSerNumber;
        this.prrSerNumber = prrSerNumber;
        this.cbrSerNumber = cbrSerNumber;
        this.disputeStatus = disputeStatus;
        this.disputeTxnCode = disputeTxnCode;
        this.orgRecIndicator = orgRecIndicator;
        this.cardNumber = cardNumber;
        this.accNumber = accNumber;
        this.acqRefNumber = acqRefNumber;
        this.purchaseDate = purchaseDate;
        this.sourceAmount = sourceAmount;
        this.sourceCurrency = sourceCurrency;
        this.meName = meName;
        this.meCity = meCity;
        this.meConCode = meConCode;
        this.mcc = mcc;
        this.usageCode = usageCode;
        this.meZipCode = meZipCode;
        this.authCode = authCode;
        this.authSourceCode = authSourceCode;
        this.posEntryMode = posEntryMode;
        this.centerProcDate = centerProcDate;
        this.cardAcceptId = cardAcceptId;
        this.reimbAttribute = reimbAttribute;
        this.ntwIdentificationCode = ntwIdentificationCode;
        this.productId = productId;
        this.txnId = txnId;
        this.disputeFinancialReasonCode = disputeFinancialReasonCode;
        this.setlFlag = setlFlag;
        this.orgTxnAmount = orgTxnAmount;
        this.orgTxnCurrCode = orgTxnCurrCode;
        this.specialCbIndicator = specialCbIndicator;
        this.bussDate = bussDate;
        this.bchBranchCode = bchBranchCode;
        this.floorLimitInd = floorLimitInd;
        this.exceptionFileIndicator = exceptionFileIndicator;
        this.pcasIndicator = pcasIndicator;
        this.destinationAmount = destinationAmount;
        this.destinationCurrency = destinationCurrency;
        this.cardAcceptName = cardAcceptName;
        this.cardAcceptCity = cardAcceptCity;
        this.cardAcceptCountry = cardAcceptCountry;
        this.reqPaymentService = reqPaymentService;
        this.reasonCode = reasonCode;
        this.posCapability = posCapability;
        this.intFeeIndicator = intFeeIndicator;
        this.cardHolderId = cardHolderId;
        this.acqWorkstationBin = acqWorkstationBin;
        this.issWorkstationBin = issWorkstationBin;
        this.cbRefNumber = cbRefNumber;
        this.docIndicator = docIndicator;
        this.memberText = memberText;
        this.specialCondition = specialCondition;
        this.feeProgram = feeProgram;
        this.merchantCode = merchantCode;
        this.terminalId = terminalId;
        this.nationalReimbFee = nationalReimbFee;
        this.ecomIndicator = ecomIndicator;
        this.specialCdIndicator = specialCdIndicator;
        this.chipConditionCode = chipConditionCode;
        this.posEnvironment = posEnvironment;
        this.authAmount = authAmount;
        this.authCurrency = authCurrency;
        this.authRespCode = authRespCode;
        this.cbRightIndicator = cbRightIndicator;
        this.clearingSeqNumber = clearingSeqNumber;
        this.cvv2Results = cvv2Results;
        this.productCode = productCode;
        this.interChangeFee = interChangeFee;
        this.trlCapableProfile = trlCapableProfile;
        this.trlCountryCode = trlCountryCode;
        this.issAppDataB2 = issAppDataB2;
        this.issAppDataB3 = issAppDataB3;
        this.revIndicator = revIndicator;
        this.activeStatus = activeStatus;
        this.activationDate = activationDate;
        this.reprFlag = reprFlag;
        this.priority = priority;
        this.remarks = remarks;
        this.genStatus = genStatus;
        this.encrypTcardNumber = encrypTcardNumber;
        this.paymentRefNumber = paymentRefNumber;
        this.txnCurrency = txnCurrency;
        this.txnAmount = txnAmount;
        this.issControlNumber = issControlNumber;
        this.reqReasonCode = reqReasonCode;
        this.retReqNumber = retReqNumber;
        this.reqFulfillMethod = reqFulfillMethod;
        this.caseId = caseId;
    }
}

