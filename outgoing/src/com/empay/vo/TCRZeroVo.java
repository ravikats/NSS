/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.TCRZeroVo
 */
package com.empay.vo;

public class TCRZeroVo {
    private String txnCode;
    private String txnCodeQualifier;
    private String txnComponentSeqNum;
    private String accountNumber;
    private String accountNumberExtension;
    private String floorLimitIndicator;
    private String crbExceptionFileIndicator;
    private String reserved;
    private String arn;
    private String acquirerBusinessID;
    private String purchaseDate;
    private String destinationAmount;
    private String destinationCurrencyCode;
    private String sourceAmount;
    private String sourceCurrencyCode;
    private String merchantName;
    private String merchantCity;
    private String merchantCountryCode;
    private String merchantCategoryCode;
    private String merchantZIPCode;
    private String merchantStateOrProvinceCode;
    private String requestedPaymentService;
    private String numberofPaymentForms;
    private String usageCode;
    private String reasonCode;
    private String settlementFlag;
    private Character authorizationCharaIndicator;
    private String authorizationCode;
    private Character posTerminalCapability;
    private String reserved1;
    private Character cardholderIDMethod;
    private String collectionOnlyFlag;
    private String posEntryMode;
    private String centralProcessingDate;
    private String reimbursementAttribute;
    private String businessFormatCode;
    private String tokenAssuranceLevel;
    private String rateTableID;
    private String reserved2;
    private String reserved3;
    private String documentationIndicator;
    private String memberMessageText;
    private String specialConditionIndicators;
    private String feeProgramIndicator;
    private String issuerCharge;
    private String persistentFXAppliedIndicator;
    private String cardAcceptorID;
    private String terminalID;
    private String nationalReimbursementFee;
    private Character ecomIndicator;
    private String specialChargebackIndicator;
    private String conversionDate;
    private String reserved4;
    private String acceptanceTerminalIndicator;
    private String prepaidCardIndicator;
    private String serviceDevelopmentField;
    private String avsResponseCode;
    private String authorizationSourceCode;
    private String purchaseIdentifierFormat;
    private Character accountSelection;
    private String installmentPaymentCount;
    private String purchaseIdentifier;
    private String cashback;
    private String chipConditionCode;
    private Character posEnvironment;
    private String transactionIdentifier;
    private String authorizedAmount;
    private String authorizationCurrencyCode;
    private String authorizationResponseCode;
    private String validationCode;
    private String excludedTransactionIdentifierReason;
    private String reserved5;
    private String reserved6;
    private String multipleClearingSequenceNumber;
    private String multipleClearingSequenceCount;
    private Character marketSpecificAuthDataIndicator;
    private String totalAuthorizedAmount;
    private String informationIndicator;
    private String merchantTelephoneNumber;
    private String additionalDataIndicator;
    private String merchantVolumeIndicator;
    private String electronicCommerceGoodsIndicator;
    private String merchantVerificationValue;
    private String interchangeFeeAmount;
    private String interchangeFeeSign;
    private String sourceCurrtoBaseCurrExcRate;
    private String baseCurrtoDestinationCurrExcRate;
    private String optionalIssuerISAAmount;
    private String productID;
    private String programID;
    private String dccIndicator;
    private String accTypeIdentification;
    private Character spendQualifiedIndicator;
    private String panToken;
    private String reserved7;
    private String accFundingSource;
    private String cvv2ResultCode;
    private String transactionType;
    private String cardSequenceNumber;
    private String terminalTranDate;
    private String terminalCapabilityProfile;
    private String terminalCountryCode;
    private String terminalSerialNumber;
    private String unpredictableNumber;
    private String applicationTransactionCounter;
    private String applicationInterchangeProfile;
    private String cryptogram;
    private String issuerAppDataByte2;
    private String issuerAppDataByte3;
    private String terminalVeriResults;
    private String issuerAppDataByte4to7;
    private String cryptogramAmount;
    private String issuerAppDataByte8;
    private String issuerAppDataByte9to16;
    private String issuerAppDataByte1;
    private String issuerAppDataByte17;
    private String issuerAppDataByte18to32;
    private String formFactorIndicator;
    private String issuerScript1Results;
    private String transactionCode;
    private String destinationIdentifier;
    private String sourceIdentifier;
    private String reasonCode1;
    private String countryCode;
    private String eventDate;
    private String accountNumber1;
    private String accountNumberExtension1;
    private String destinationAmount1;
    private String destinationCurrencyCode1;
    private String sourceAmount1;
    private String sourceCurrencyCode1;
    private String messageText;
    private String settlementFlag1;
    private String transactionIdentifier1;
    private String reserved8;
    private String centralProcessingDate1;
    private String reimbursementAttribute1;
    private String serviceProcessingType;
    private String reserved9;
    private String fastFundsIndicator;
    private String businessFormatCodeCR;
    private String businessApplicationID;
    private String sourceofFunds;
    private String paymentReversalReasonCode;
    private String senderReferenceNumber;
    private String senderAccountNumber;
    private String senderName;
    private String senderAddress;
    private String senderCity;
    private String senderState;
    private String senderCountry;

    public String getTcr0format() {
        return this.txnCode + this.txnCodeQualifier + this.txnComponentSeqNum + this.accountNumber + this.accountNumberExtension + this.floorLimitIndicator + this.crbExceptionFileIndicator + this.reserved + this.arn + this.acquirerBusinessID + this.purchaseDate + this.destinationAmount + this.destinationCurrencyCode + this.sourceAmount + this.sourceCurrencyCode + this.merchantName + this.merchantCity + this.merchantCountryCode + this.merchantCategoryCode + this.merchantZIPCode + this.merchantStateOrProvinceCode + this.requestedPaymentService + this.numberofPaymentForms + this.usageCode + this.reasonCode + this.settlementFlag + this.authorizationCharaIndicator + this.authorizationCode + this.posTerminalCapability + this.reserved1 + this.cardholderIDMethod + this.collectionOnlyFlag + this.posEntryMode + this.centralProcessingDate + this.reimbursementAttribute;
    }

    public String getAdditionalDataformat() {
        return this.txnCode + this.txnCodeQualifier + this.txnComponentSeqNum + this.businessFormatCode + this.tokenAssuranceLevel + this.rateTableID + this.reserved2 + this.reserved3 + this.documentationIndicator + this.memberMessageText + this.specialConditionIndicators + this.feeProgramIndicator + this.issuerCharge + this.persistentFXAppliedIndicator + this.cardAcceptorID + this.terminalID + this.nationalReimbursementFee + this.ecomIndicator + this.specialChargebackIndicator + this.conversionDate + this.reserved4 + this.acceptanceTerminalIndicator + this.prepaidCardIndicator + this.serviceDevelopmentField + this.avsResponseCode + this.authorizationSourceCode + this.purchaseIdentifierFormat + this.accountSelection + this.installmentPaymentCount + this.purchaseIdentifier + this.cashback + this.chipConditionCode + this.posEnvironment;
    }

    public String getPaymentServiceDataformat() {
        return this.txnCode + this.txnCodeQualifier + this.txnComponentSeqNum + this.transactionIdentifier + this.authorizedAmount + this.authorizationCurrencyCode + this.authorizationResponseCode + this.validationCode + this.excludedTransactionIdentifierReason + this.reserved5 + this.reserved6 + this.multipleClearingSequenceNumber + this.multipleClearingSequenceCount + this.marketSpecificAuthDataIndicator + this.totalAuthorizedAmount + this.informationIndicator + this.merchantTelephoneNumber + this.additionalDataIndicator + this.merchantVolumeIndicator + this.electronicCommerceGoodsIndicator + this.merchantVerificationValue + this.interchangeFeeAmount + this.interchangeFeeSign + this.sourceCurrtoBaseCurrExcRate + this.baseCurrtoDestinationCurrExcRate + this.optionalIssuerISAAmount + this.productID + this.programID + this.dccIndicator + this.accTypeIdentification + this.spendQualifiedIndicator + this.panToken + this.reserved7 + this.accFundingSource + this.cvv2ResultCode;
    }

    public String getChipCardTxnDataformat() {
        return this.txnCode + this.txnCodeQualifier + this.txnComponentSeqNum + this.transactionType + this.cardSequenceNumber + this.terminalTranDate + this.terminalCapabilityProfile + this.terminalCountryCode + this.terminalSerialNumber + this.unpredictableNumber + this.applicationTransactionCounter + this.applicationInterchangeProfile + this.cryptogram + this.issuerAppDataByte2 + this.issuerAppDataByte3 + this.terminalVeriResults + this.issuerAppDataByte4to7 + this.cryptogramAmount + this.issuerAppDataByte8 + this.issuerAppDataByte9to16 + this.issuerAppDataByte1 + this.issuerAppDataByte17 + this.issuerAppDataByte18to32 + this.formFactorIndicator + this.issuerScript1Results;
    }

    public String getFeeCollectionformat() {
        return this.transactionCode + this.txnCodeQualifier + this.txnComponentSeqNum + this.destinationIdentifier + this.sourceIdentifier + this.reasonCode1 + this.countryCode + this.eventDate + this.accountNumber1 + this.accountNumberExtension1 + this.destinationAmount1 + this.destinationCurrencyCode1 + this.sourceAmount1 + this.sourceCurrencyCode1 + this.messageText + this.settlementFlag1 + this.transactionIdentifier1 + this.reserved8 + this.centralProcessingDate1 + this.reimbursementAttribute1;
    }

    public String getAFTDataformat() {
        return this.transactionCode + this.txnCodeQualifier + this.txnComponentSeqNum + this.serviceProcessingType + this.reserved9 + this.fastFundsIndicator + this.businessFormatCodeCR + this.businessApplicationID + this.sourceofFunds + this.paymentReversalReasonCode + this.senderReferenceNumber + this.senderAccountNumber + this.senderName + this.senderAddress + this.senderCity + this.senderState + this.senderCountry;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public String getTxnCodeQualifier() {
        return this.txnCodeQualifier;
    }

    public String getTxnComponentSeqNum() {
        return this.txnComponentSeqNum;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountNumberExtension() {
        return this.accountNumberExtension;
    }

    public String getFloorLimitIndicator() {
        return this.floorLimitIndicator;
    }

    public String getCrbExceptionFileIndicator() {
        return this.crbExceptionFileIndicator;
    }

    public String getReserved() {
        return this.reserved;
    }

    public String getArn() {
        return this.arn;
    }

    public String getAcquirerBusinessID() {
        return this.acquirerBusinessID;
    }

    public String getPurchaseDate() {
        return this.purchaseDate;
    }

    public String getDestinationAmount() {
        return this.destinationAmount;
    }

    public String getDestinationCurrencyCode() {
        return this.destinationCurrencyCode;
    }

    public String getSourceAmount() {
        return this.sourceAmount;
    }

    public String getSourceCurrencyCode() {
        return this.sourceCurrencyCode;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getMerchantCity() {
        return this.merchantCity;
    }

    public String getMerchantCountryCode() {
        return this.merchantCountryCode;
    }

    public String getMerchantCategoryCode() {
        return this.merchantCategoryCode;
    }

    public String getMerchantZIPCode() {
        return this.merchantZIPCode;
    }

    public String getMerchantStateOrProvinceCode() {
        return this.merchantStateOrProvinceCode;
    }

    public String getRequestedPaymentService() {
        return this.requestedPaymentService;
    }

    public String getNumberofPaymentForms() {
        return this.numberofPaymentForms;
    }

    public String getUsageCode() {
        return this.usageCode;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public String getSettlementFlag() {
        return this.settlementFlag;
    }

    public Character getAuthorizationCharaIndicator() {
        return this.authorizationCharaIndicator;
    }

    public String getAuthorizationCode() {
        return this.authorizationCode;
    }

    public Character getPosTerminalCapability() {
        return this.posTerminalCapability;
    }

    public String getReserved1() {
        return this.reserved1;
    }

    public Character getCardholderIDMethod() {
        return this.cardholderIDMethod;
    }

    public String getCollectionOnlyFlag() {
        return this.collectionOnlyFlag;
    }

    public String getPosEntryMode() {
        return this.posEntryMode;
    }

    public String getCentralProcessingDate() {
        return this.centralProcessingDate;
    }

    public String getReimbursementAttribute() {
        return this.reimbursementAttribute;
    }

    public String getBusinessFormatCode() {
        return this.businessFormatCode;
    }

    public String getTokenAssuranceLevel() {
        return this.tokenAssuranceLevel;
    }

    public String getRateTableID() {
        return this.rateTableID;
    }

    public String getReserved2() {
        return this.reserved2;
    }

    public String getReserved3() {
        return this.reserved3;
    }

    public String getDocumentationIndicator() {
        return this.documentationIndicator;
    }

    public String getMemberMessageText() {
        return this.memberMessageText;
    }

    public String getSpecialConditionIndicators() {
        return this.specialConditionIndicators;
    }

    public String getFeeProgramIndicator() {
        return this.feeProgramIndicator;
    }

    public String getIssuerCharge() {
        return this.issuerCharge;
    }

    public String getPersistentFXAppliedIndicator() {
        return this.persistentFXAppliedIndicator;
    }

    public String getCardAcceptorID() {
        return this.cardAcceptorID;
    }

    public String getTerminalID() {
        return this.terminalID;
    }

    public String getNationalReimbursementFee() {
        return this.nationalReimbursementFee;
    }

    public Character getEcomIndicator() {
        return this.ecomIndicator;
    }

    public String getSpecialChargebackIndicator() {
        return this.specialChargebackIndicator;
    }

    public String getConversionDate() {
        return this.conversionDate;
    }

    public String getReserved4() {
        return this.reserved4;
    }

    public String getAcceptanceTerminalIndicator() {
        return this.acceptanceTerminalIndicator;
    }

    public String getPrepaidCardIndicator() {
        return this.prepaidCardIndicator;
    }

    public String getServiceDevelopmentField() {
        return this.serviceDevelopmentField;
    }

    public String getAvsResponseCode() {
        return this.avsResponseCode;
    }

    public String getAuthorizationSourceCode() {
        return this.authorizationSourceCode;
    }

    public String getPurchaseIdentifierFormat() {
        return this.purchaseIdentifierFormat;
    }

    public Character getAccountSelection() {
        return this.accountSelection;
    }

    public String getInstallmentPaymentCount() {
        return this.installmentPaymentCount;
    }

    public String getPurchaseIdentifier() {
        return this.purchaseIdentifier;
    }

    public String getCashback() {
        return this.cashback;
    }

    public String getChipConditionCode() {
        return this.chipConditionCode;
    }

    public Character getPosEnvironment() {
        return this.posEnvironment;
    }

    public String getTransactionIdentifier() {
        return this.transactionIdentifier;
    }

    public String getAuthorizedAmount() {
        return this.authorizedAmount;
    }

    public String getAuthorizationCurrencyCode() {
        return this.authorizationCurrencyCode;
    }

    public String getAuthorizationResponseCode() {
        return this.authorizationResponseCode;
    }

    public String getValidationCode() {
        return this.validationCode;
    }

    public String getExcludedTransactionIdentifierReason() {
        return this.excludedTransactionIdentifierReason;
    }

    public String getReserved5() {
        return this.reserved5;
    }

    public String getReserved6() {
        return this.reserved6;
    }

    public String getMultipleClearingSequenceNumber() {
        return this.multipleClearingSequenceNumber;
    }

    public String getMultipleClearingSequenceCount() {
        return this.multipleClearingSequenceCount;
    }

    public Character getMarketSpecificAuthDataIndicator() {
        return this.marketSpecificAuthDataIndicator;
    }

    public String getTotalAuthorizedAmount() {
        return this.totalAuthorizedAmount;
    }

    public String getInformationIndicator() {
        return this.informationIndicator;
    }

    public String getMerchantTelephoneNumber() {
        return this.merchantTelephoneNumber;
    }

    public String getAdditionalDataIndicator() {
        return this.additionalDataIndicator;
    }

    public String getMerchantVolumeIndicator() {
        return this.merchantVolumeIndicator;
    }

    public String getElectronicCommerceGoodsIndicator() {
        return this.electronicCommerceGoodsIndicator;
    }

    public String getMerchantVerificationValue() {
        return this.merchantVerificationValue;
    }

    public String getInterchangeFeeAmount() {
        return this.interchangeFeeAmount;
    }

    public String getInterchangeFeeSign() {
        return this.interchangeFeeSign;
    }

    public String getSourceCurrtoBaseCurrExcRate() {
        return this.sourceCurrtoBaseCurrExcRate;
    }

    public String getBaseCurrtoDestinationCurrExcRate() {
        return this.baseCurrtoDestinationCurrExcRate;
    }

    public String getOptionalIssuerISAAmount() {
        return this.optionalIssuerISAAmount;
    }

    public String getProductID() {
        return this.productID;
    }

    public String getProgramID() {
        return this.programID;
    }

    public String getDccIndicator() {
        return this.dccIndicator;
    }

    public String getAccTypeIdentification() {
        return this.accTypeIdentification;
    }

    public Character getSpendQualifiedIndicator() {
        return this.spendQualifiedIndicator;
    }

    public String getPanToken() {
        return this.panToken;
    }

    public String getReserved7() {
        return this.reserved7;
    }

    public String getAccFundingSource() {
        return this.accFundingSource;
    }

    public String getCvv2ResultCode() {
        return this.cvv2ResultCode;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public String getCardSequenceNumber() {
        return this.cardSequenceNumber;
    }

    public String getTerminalTranDate() {
        return this.terminalTranDate;
    }

    public String getTerminalCapabilityProfile() {
        return this.terminalCapabilityProfile;
    }

    public String getTerminalCountryCode() {
        return this.terminalCountryCode;
    }

    public String getTerminalSerialNumber() {
        return this.terminalSerialNumber;
    }

    public String getUnpredictableNumber() {
        return this.unpredictableNumber;
    }

    public String getApplicationTransactionCounter() {
        return this.applicationTransactionCounter;
    }

    public String getApplicationInterchangeProfile() {
        return this.applicationInterchangeProfile;
    }

    public String getCryptogram() {
        return this.cryptogram;
    }

    public String getIssuerAppDataByte2() {
        return this.issuerAppDataByte2;
    }

    public String getIssuerAppDataByte3() {
        return this.issuerAppDataByte3;
    }

    public String getTerminalVeriResults() {
        return this.terminalVeriResults;
    }

    public String getIssuerAppDataByte4to7() {
        return this.issuerAppDataByte4to7;
    }

    public String getCryptogramAmount() {
        return this.cryptogramAmount;
    }

    public String getIssuerAppDataByte8() {
        return this.issuerAppDataByte8;
    }

    public String getIssuerAppDataByte9to16() {
        return this.issuerAppDataByte9to16;
    }

    public String getIssuerAppDataByte1() {
        return this.issuerAppDataByte1;
    }

    public String getIssuerAppDataByte17() {
        return this.issuerAppDataByte17;
    }

    public String getIssuerAppDataByte18to32() {
        return this.issuerAppDataByte18to32;
    }

    public String getFormFactorIndicator() {
        return this.formFactorIndicator;
    }

    public String getIssuerScript1Results() {
        return this.issuerScript1Results;
    }

    public String getTransactionCode() {
        return this.transactionCode;
    }

    public String getDestinationIdentifier() {
        return this.destinationIdentifier;
    }

    public String getSourceIdentifier() {
        return this.sourceIdentifier;
    }

    public String getReasonCode1() {
        return this.reasonCode1;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    public String getAccountNumber1() {
        return this.accountNumber1;
    }

    public String getAccountNumberExtension1() {
        return this.accountNumberExtension1;
    }

    public String getDestinationAmount1() {
        return this.destinationAmount1;
    }

    public String getDestinationCurrencyCode1() {
        return this.destinationCurrencyCode1;
    }

    public String getSourceAmount1() {
        return this.sourceAmount1;
    }

    public String getSourceCurrencyCode1() {
        return this.sourceCurrencyCode1;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public String getSettlementFlag1() {
        return this.settlementFlag1;
    }

    public String getTransactionIdentifier1() {
        return this.transactionIdentifier1;
    }

    public String getReserved8() {
        return this.reserved8;
    }

    public String getCentralProcessingDate1() {
        return this.centralProcessingDate1;
    }

    public String getReimbursementAttribute1() {
        return this.reimbursementAttribute1;
    }

    public String getServiceProcessingType() {
        return this.serviceProcessingType;
    }

    public String getReserved9() {
        return this.reserved9;
    }

    public String getFastFundsIndicator() {
        return this.fastFundsIndicator;
    }

    public String getBusinessFormatCodeCR() {
        return this.businessFormatCodeCR;
    }

    public String getBusinessApplicationID() {
        return this.businessApplicationID;
    }

    public String getSourceofFunds() {
        return this.sourceofFunds;
    }

    public String getPaymentReversalReasonCode() {
        return this.paymentReversalReasonCode;
    }

    public String getSenderReferenceNumber() {
        return this.senderReferenceNumber;
    }

    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public String getSenderAddress() {
        return this.senderAddress;
    }

    public String getSenderCity() {
        return this.senderCity;
    }

    public String getSenderState() {
        return this.senderState;
    }

    public String getSenderCountry() {
        return this.senderCountry;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setTxnCodeQualifier(String txnCodeQualifier) {
        this.txnCodeQualifier = txnCodeQualifier;
    }

    public void setTxnComponentSeqNum(String txnComponentSeqNum) {
        this.txnComponentSeqNum = txnComponentSeqNum;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountNumberExtension(String accountNumberExtension) {
        this.accountNumberExtension = accountNumberExtension;
    }

    public void setFloorLimitIndicator(String floorLimitIndicator) {
        this.floorLimitIndicator = floorLimitIndicator;
    }

    public void setCrbExceptionFileIndicator(String crbExceptionFileIndicator) {
        this.crbExceptionFileIndicator = crbExceptionFileIndicator;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public void setAcquirerBusinessID(String acquirerBusinessID) {
        this.acquirerBusinessID = acquirerBusinessID;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setDestinationAmount(String destinationAmount) {
        this.destinationAmount = destinationAmount;
    }

    public void setDestinationCurrencyCode(String destinationCurrencyCode) {
        this.destinationCurrencyCode = destinationCurrencyCode;
    }

    public void setSourceAmount(String sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public void setSourceCurrencyCode(String sourceCurrencyCode) {
        this.sourceCurrencyCode = sourceCurrencyCode;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public void setMerchantCountryCode(String merchantCountryCode) {
        this.merchantCountryCode = merchantCountryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public void setMerchantZIPCode(String merchantZIPCode) {
        this.merchantZIPCode = merchantZIPCode;
    }

    public void setMerchantStateOrProvinceCode(String merchantStateOrProvinceCode) {
        this.merchantStateOrProvinceCode = merchantStateOrProvinceCode;
    }

    public void setRequestedPaymentService(String requestedPaymentService) {
        this.requestedPaymentService = requestedPaymentService;
    }

    public void setNumberofPaymentForms(String numberofPaymentForms) {
        this.numberofPaymentForms = numberofPaymentForms;
    }

    public void setUsageCode(String usageCode) {
        this.usageCode = usageCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setSettlementFlag(String settlementFlag) {
        this.settlementFlag = settlementFlag;
    }

    public void setAuthorizationCharaIndicator(Character authorizationCharaIndicator) {
        this.authorizationCharaIndicator = authorizationCharaIndicator;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public void setPosTerminalCapability(Character posTerminalCapability) {
        this.posTerminalCapability = posTerminalCapability;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public void setCardholderIDMethod(Character cardholderIDMethod) {
        this.cardholderIDMethod = cardholderIDMethod;
    }

    public void setCollectionOnlyFlag(String collectionOnlyFlag) {
        this.collectionOnlyFlag = collectionOnlyFlag;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public void setCentralProcessingDate(String centralProcessingDate) {
        this.centralProcessingDate = centralProcessingDate;
    }

    public void setReimbursementAttribute(String reimbursementAttribute) {
        this.reimbursementAttribute = reimbursementAttribute;
    }

    public void setBusinessFormatCode(String businessFormatCode) {
        this.businessFormatCode = businessFormatCode;
    }

    public void setTokenAssuranceLevel(String tokenAssuranceLevel) {
        this.tokenAssuranceLevel = tokenAssuranceLevel;
    }

    public void setRateTableID(String rateTableID) {
        this.rateTableID = rateTableID;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public void setDocumentationIndicator(String documentationIndicator) {
        this.documentationIndicator = documentationIndicator;
    }

    public void setMemberMessageText(String memberMessageText) {
        this.memberMessageText = memberMessageText;
    }

    public void setSpecialConditionIndicators(String specialConditionIndicators) {
        this.specialConditionIndicators = specialConditionIndicators;
    }

    public void setFeeProgramIndicator(String feeProgramIndicator) {
        this.feeProgramIndicator = feeProgramIndicator;
    }

    public void setIssuerCharge(String issuerCharge) {
        this.issuerCharge = issuerCharge;
    }

    public void setPersistentFXAppliedIndicator(String persistentFXAppliedIndicator) {
        this.persistentFXAppliedIndicator = persistentFXAppliedIndicator;
    }

    public void setCardAcceptorID(String cardAcceptorID) {
        this.cardAcceptorID = cardAcceptorID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public void setNationalReimbursementFee(String nationalReimbursementFee) {
        this.nationalReimbursementFee = nationalReimbursementFee;
    }

    public void setEcomIndicator(Character ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }

    public void setSpecialChargebackIndicator(String specialChargebackIndicator) {
        this.specialChargebackIndicator = specialChargebackIndicator;
    }

    public void setConversionDate(String conversionDate) {
        this.conversionDate = conversionDate;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public void setAcceptanceTerminalIndicator(String acceptanceTerminalIndicator) {
        this.acceptanceTerminalIndicator = acceptanceTerminalIndicator;
    }

    public void setPrepaidCardIndicator(String prepaidCardIndicator) {
        this.prepaidCardIndicator = prepaidCardIndicator;
    }

    public void setServiceDevelopmentField(String serviceDevelopmentField) {
        this.serviceDevelopmentField = serviceDevelopmentField;
    }

    public void setAvsResponseCode(String avsResponseCode) {
        this.avsResponseCode = avsResponseCode;
    }

    public void setAuthorizationSourceCode(String authorizationSourceCode) {
        this.authorizationSourceCode = authorizationSourceCode;
    }

    public void setPurchaseIdentifierFormat(String purchaseIdentifierFormat) {
        this.purchaseIdentifierFormat = purchaseIdentifierFormat;
    }

    public void setAccountSelection(Character accountSelection) {
        this.accountSelection = accountSelection;
    }

    public void setInstallmentPaymentCount(String installmentPaymentCount) {
        this.installmentPaymentCount = installmentPaymentCount;
    }

    public void setPurchaseIdentifier(String purchaseIdentifier) {
        this.purchaseIdentifier = purchaseIdentifier;
    }

    public void setCashback(String cashback) {
        this.cashback = cashback;
    }

    public void setChipConditionCode(String chipConditionCode) {
        this.chipConditionCode = chipConditionCode;
    }

    public void setPosEnvironment(Character posEnvironment) {
        this.posEnvironment = posEnvironment;
    }

    public void setTransactionIdentifier(String transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public void setAuthorizedAmount(String authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public void setAuthorizationCurrencyCode(String authorizationCurrencyCode) {
        this.authorizationCurrencyCode = authorizationCurrencyCode;
    }

    public void setAuthorizationResponseCode(String authorizationResponseCode) {
        this.authorizationResponseCode = authorizationResponseCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public void setExcludedTransactionIdentifierReason(String excludedTransactionIdentifierReason) {
        this.excludedTransactionIdentifierReason = excludedTransactionIdentifierReason;
    }

    public void setReserved5(String reserved5) {
        this.reserved5 = reserved5;
    }

    public void setReserved6(String reserved6) {
        this.reserved6 = reserved6;
    }

    public void setMultipleClearingSequenceNumber(String multipleClearingSequenceNumber) {
        this.multipleClearingSequenceNumber = multipleClearingSequenceNumber;
    }

    public void setMultipleClearingSequenceCount(String multipleClearingSequenceCount) {
        this.multipleClearingSequenceCount = multipleClearingSequenceCount;
    }

    public void setMarketSpecificAuthDataIndicator(Character marketSpecificAuthDataIndicator) {
        this.marketSpecificAuthDataIndicator = marketSpecificAuthDataIndicator;
    }

    public void setTotalAuthorizedAmount(String totalAuthorizedAmount) {
        this.totalAuthorizedAmount = totalAuthorizedAmount;
    }

    public void setInformationIndicator(String informationIndicator) {
        this.informationIndicator = informationIndicator;
    }

    public void setMerchantTelephoneNumber(String merchantTelephoneNumber) {
        this.merchantTelephoneNumber = merchantTelephoneNumber;
    }

    public void setAdditionalDataIndicator(String additionalDataIndicator) {
        this.additionalDataIndicator = additionalDataIndicator;
    }

    public void setMerchantVolumeIndicator(String merchantVolumeIndicator) {
        this.merchantVolumeIndicator = merchantVolumeIndicator;
    }

    public void setElectronicCommerceGoodsIndicator(String electronicCommerceGoodsIndicator) {
        this.electronicCommerceGoodsIndicator = electronicCommerceGoodsIndicator;
    }

    public void setMerchantVerificationValue(String merchantVerificationValue) {
        this.merchantVerificationValue = merchantVerificationValue;
    }

    public void setInterchangeFeeAmount(String interchangeFeeAmount) {
        this.interchangeFeeAmount = interchangeFeeAmount;
    }

    public void setInterchangeFeeSign(String interchangeFeeSign) {
        this.interchangeFeeSign = interchangeFeeSign;
    }

    public void setSourceCurrtoBaseCurrExcRate(String sourceCurrtoBaseCurrExcRate) {
        this.sourceCurrtoBaseCurrExcRate = sourceCurrtoBaseCurrExcRate;
    }

    public void setBaseCurrtoDestinationCurrExcRate(String baseCurrtoDestinationCurrExcRate) {
        this.baseCurrtoDestinationCurrExcRate = baseCurrtoDestinationCurrExcRate;
    }

    public void setOptionalIssuerISAAmount(String optionalIssuerISAAmount) {
        this.optionalIssuerISAAmount = optionalIssuerISAAmount;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public void setDccIndicator(String dccIndicator) {
        this.dccIndicator = dccIndicator;
    }

    public void setAccTypeIdentification(String accTypeIdentification) {
        this.accTypeIdentification = accTypeIdentification;
    }

    public void setSpendQualifiedIndicator(Character spendQualifiedIndicator) {
        this.spendQualifiedIndicator = spendQualifiedIndicator;
    }

    public void setPanToken(String panToken) {
        this.panToken = panToken;
    }

    public void setReserved7(String reserved7) {
        this.reserved7 = reserved7;
    }

    public void setAccFundingSource(String accFundingSource) {
        this.accFundingSource = accFundingSource;
    }

    public void setCvv2ResultCode(String cvv2ResultCode) {
        this.cvv2ResultCode = cvv2ResultCode;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setCardSequenceNumber(String cardSequenceNumber) {
        this.cardSequenceNumber = cardSequenceNumber;
    }

    public void setTerminalTranDate(String terminalTranDate) {
        this.terminalTranDate = terminalTranDate;
    }

    public void setTerminalCapabilityProfile(String terminalCapabilityProfile) {
        this.terminalCapabilityProfile = terminalCapabilityProfile;
    }

    public void setTerminalCountryCode(String terminalCountryCode) {
        this.terminalCountryCode = terminalCountryCode;
    }

    public void setTerminalSerialNumber(String terminalSerialNumber) {
        this.terminalSerialNumber = terminalSerialNumber;
    }

    public void setUnpredictableNumber(String unpredictableNumber) {
        this.unpredictableNumber = unpredictableNumber;
    }

    public void setApplicationTransactionCounter(String applicationTransactionCounter) {
        this.applicationTransactionCounter = applicationTransactionCounter;
    }

    public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
        this.applicationInterchangeProfile = applicationInterchangeProfile;
    }

    public void setCryptogram(String cryptogram) {
        this.cryptogram = cryptogram;
    }

    public void setIssuerAppDataByte2(String issuerAppDataByte2) {
        this.issuerAppDataByte2 = issuerAppDataByte2;
    }

    public void setIssuerAppDataByte3(String issuerAppDataByte3) {
        this.issuerAppDataByte3 = issuerAppDataByte3;
    }

    public void setTerminalVeriResults(String terminalVeriResults) {
        this.terminalVeriResults = terminalVeriResults;
    }

    public void setIssuerAppDataByte4to7(String issuerAppDataByte4to7) {
        this.issuerAppDataByte4to7 = issuerAppDataByte4to7;
    }

    public void setCryptogramAmount(String cryptogramAmount) {
        this.cryptogramAmount = cryptogramAmount;
    }

    public void setIssuerAppDataByte8(String issuerAppDataByte8) {
        this.issuerAppDataByte8 = issuerAppDataByte8;
    }

    public void setIssuerAppDataByte9to16(String issuerAppDataByte9to16) {
        this.issuerAppDataByte9to16 = issuerAppDataByte9to16;
    }

    public void setIssuerAppDataByte1(String issuerAppDataByte1) {
        this.issuerAppDataByte1 = issuerAppDataByte1;
    }

    public void setIssuerAppDataByte17(String issuerAppDataByte17) {
        this.issuerAppDataByte17 = issuerAppDataByte17;
    }

    public void setIssuerAppDataByte18to32(String issuerAppDataByte18to32) {
        this.issuerAppDataByte18to32 = issuerAppDataByte18to32;
    }

    public void setFormFactorIndicator(String formFactorIndicator) {
        this.formFactorIndicator = formFactorIndicator;
    }

    public void setIssuerScript1Results(String issuerScript1Results) {
        this.issuerScript1Results = issuerScript1Results;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public void setDestinationIdentifier(String destinationIdentifier) {
        this.destinationIdentifier = destinationIdentifier;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    public void setReasonCode1(String reasonCode1) {
        this.reasonCode1 = reasonCode1;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setAccountNumber1(String accountNumber1) {
        this.accountNumber1 = accountNumber1;
    }

    public void setAccountNumberExtension1(String accountNumberExtension1) {
        this.accountNumberExtension1 = accountNumberExtension1;
    }

    public void setDestinationAmount1(String destinationAmount1) {
        this.destinationAmount1 = destinationAmount1;
    }

    public void setDestinationCurrencyCode1(String destinationCurrencyCode1) {
        this.destinationCurrencyCode1 = destinationCurrencyCode1;
    }

    public void setSourceAmount1(String sourceAmount1) {
        this.sourceAmount1 = sourceAmount1;
    }

    public void setSourceCurrencyCode1(String sourceCurrencyCode1) {
        this.sourceCurrencyCode1 = sourceCurrencyCode1;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setSettlementFlag1(String settlementFlag1) {
        this.settlementFlag1 = settlementFlag1;
    }

    public void setTransactionIdentifier1(String transactionIdentifier1) {
        this.transactionIdentifier1 = transactionIdentifier1;
    }

    public void setReserved8(String reserved8) {
        this.reserved8 = reserved8;
    }

    public void setCentralProcessingDate1(String centralProcessingDate1) {
        this.centralProcessingDate1 = centralProcessingDate1;
    }

    public void setReimbursementAttribute1(String reimbursementAttribute1) {
        this.reimbursementAttribute1 = reimbursementAttribute1;
    }

    public void setServiceProcessingType(String serviceProcessingType) {
        this.serviceProcessingType = serviceProcessingType;
    }

    public void setReserved9(String reserved9) {
        this.reserved9 = reserved9;
    }

    public void setFastFundsIndicator(String fastFundsIndicator) {
        this.fastFundsIndicator = fastFundsIndicator;
    }

    public void setBusinessFormatCodeCR(String businessFormatCodeCR) {
        this.businessFormatCodeCR = businessFormatCodeCR;
    }

    public void setBusinessApplicationID(String businessApplicationID) {
        this.businessApplicationID = businessApplicationID;
    }

    public void setSourceofFunds(String sourceofFunds) {
        this.sourceofFunds = sourceofFunds;
    }

    public void setPaymentReversalReasonCode(String paymentReversalReasonCode) {
        this.paymentReversalReasonCode = paymentReversalReasonCode;
    }

    public void setSenderReferenceNumber(String senderReferenceNumber) {
        this.senderReferenceNumber = senderReferenceNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    /*
     * WARNING - void declaration
     */
    public boolean equals(Object o) {
        void other$senderCountry;
        void this$senderCountry;
        void other$senderState;
        void this$senderState;
        void other$senderCity;
        void this$senderCity;
        void other$senderAddress;
        void this$senderAddress;
        void other$senderName;
        void this$senderName;
        void other$senderAccountNumber;
        void this$senderAccountNumber;
        void other$senderReferenceNumber;
        void this$senderReferenceNumber;
        void other$paymentReversalReasonCode;
        void this$paymentReversalReasonCode;
        void other$sourceofFunds;
        void this$sourceofFunds;
        void other$businessApplicationID;
        void this$businessApplicationID;
        void other$businessFormatCodeCR;
        void this$businessFormatCodeCR;
        void other$fastFundsIndicator;
        void this$fastFundsIndicator;
        void other$reserved9;
        void this$reserved9;
        void other$serviceProcessingType;
        void this$serviceProcessingType;
        void other$reimbursementAttribute1;
        void this$reimbursementAttribute1;
        void other$centralProcessingDate1;
        void this$centralProcessingDate1;
        void other$reserved8;
        void this$reserved8;
        void other$transactionIdentifier1;
        void this$transactionIdentifier1;
        void other$settlementFlag1;
        void this$settlementFlag1;
        void other$messageText;
        void this$messageText;
        void other$sourceCurrencyCode1;
        void this$sourceCurrencyCode1;
        void other$sourceAmount1;
        void this$sourceAmount1;
        void other$destinationCurrencyCode1;
        void this$destinationCurrencyCode1;
        void other$destinationAmount1;
        void this$destinationAmount1;
        void other$accountNumberExtension1;
        if (o == this) {
            return true;
        }
        if (!(o instanceof TCRZeroVo)) {
            return false;
        }
        TCRZeroVo other = (TCRZeroVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Character this$authorizationCharaIndicator = this.getAuthorizationCharaIndicator();
        Character other$authorizationCharaIndicator = other.getAuthorizationCharaIndicator();
        if (this$authorizationCharaIndicator == null ? other$authorizationCharaIndicator != null : !((Object)this$authorizationCharaIndicator).equals(other$authorizationCharaIndicator)) {
            return false;
        }
        Character this$posTerminalCapability = this.getPosTerminalCapability();
        Character other$posTerminalCapability = other.getPosTerminalCapability();
        if (this$posTerminalCapability == null ? other$posTerminalCapability != null : !((Object)this$posTerminalCapability).equals(other$posTerminalCapability)) {
            return false;
        }
        Character this$cardholderIDMethod = this.getCardholderIDMethod();
        Character other$cardholderIDMethod = other.getCardholderIDMethod();
        if (this$cardholderIDMethod == null ? other$cardholderIDMethod != null : !((Object)this$cardholderIDMethod).equals(other$cardholderIDMethod)) {
            return false;
        }
        Character this$ecomIndicator = this.getEcomIndicator();
        Character other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null ? other$ecomIndicator != null : !((Object)this$ecomIndicator).equals(other$ecomIndicator)) {
            return false;
        }
        Character this$accountSelection = this.getAccountSelection();
        Character other$accountSelection = other.getAccountSelection();
        if (this$accountSelection == null ? other$accountSelection != null : !((Object)this$accountSelection).equals(other$accountSelection)) {
            return false;
        }
        Character this$posEnvironment = this.getPosEnvironment();
        Character other$posEnvironment = other.getPosEnvironment();
        if (this$posEnvironment == null ? other$posEnvironment != null : !((Object)this$posEnvironment).equals(other$posEnvironment)) {
            return false;
        }
        Character this$marketSpecificAuthDataIndicator = this.getMarketSpecificAuthDataIndicator();
        Character other$marketSpecificAuthDataIndicator = other.getMarketSpecificAuthDataIndicator();
        if (this$marketSpecificAuthDataIndicator == null ? other$marketSpecificAuthDataIndicator != null : !((Object)this$marketSpecificAuthDataIndicator).equals(other$marketSpecificAuthDataIndicator)) {
            return false;
        }
        Character this$spendQualifiedIndicator = this.getSpendQualifiedIndicator();
        Character other$spendQualifiedIndicator = other.getSpendQualifiedIndicator();
        if (this$spendQualifiedIndicator == null ? other$spendQualifiedIndicator != null : !((Object)this$spendQualifiedIndicator).equals(other$spendQualifiedIndicator)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$txnCodeQualifier = this.getTxnCodeQualifier();
        String other$txnCodeQualifier = other.getTxnCodeQualifier();
        if (this$txnCodeQualifier == null ? other$txnCodeQualifier != null : !this$txnCodeQualifier.equals(other$txnCodeQualifier)) {
            return false;
        }
        String this$txnComponentSeqNum = this.getTxnComponentSeqNum();
        String other$txnComponentSeqNum = other.getTxnComponentSeqNum();
        if (this$txnComponentSeqNum == null ? other$txnComponentSeqNum != null : !this$txnComponentSeqNum.equals(other$txnComponentSeqNum)) {
            return false;
        }
        String this$accountNumber = this.getAccountNumber();
        String other$accountNumber = other.getAccountNumber();
        if (this$accountNumber == null ? other$accountNumber != null : !this$accountNumber.equals(other$accountNumber)) {
            return false;
        }
        String this$accountNumberExtension = this.getAccountNumberExtension();
        String other$accountNumberExtension = other.getAccountNumberExtension();
        if (this$accountNumberExtension == null ? other$accountNumberExtension != null : !this$accountNumberExtension.equals(other$accountNumberExtension)) {
            return false;
        }
        String this$floorLimitIndicator = this.getFloorLimitIndicator();
        String other$floorLimitIndicator = other.getFloorLimitIndicator();
        if (this$floorLimitIndicator == null ? other$floorLimitIndicator != null : !this$floorLimitIndicator.equals(other$floorLimitIndicator)) {
            return false;
        }
        String this$crbExceptionFileIndicator = this.getCrbExceptionFileIndicator();
        String other$crbExceptionFileIndicator = other.getCrbExceptionFileIndicator();
        if (this$crbExceptionFileIndicator == null ? other$crbExceptionFileIndicator != null : !this$crbExceptionFileIndicator.equals(other$crbExceptionFileIndicator)) {
            return false;
        }
        String this$reserved = this.getReserved();
        String other$reserved = other.getReserved();
        if (this$reserved == null ? other$reserved != null : !this$reserved.equals(other$reserved)) {
            return false;
        }
        String this$arn = this.getArn();
        String other$arn = other.getArn();
        if (this$arn == null ? other$arn != null : !this$arn.equals(other$arn)) {
            return false;
        }
        String this$acquirerBusinessID = this.getAcquirerBusinessID();
        String other$acquirerBusinessID = other.getAcquirerBusinessID();
        if (this$acquirerBusinessID == null ? other$acquirerBusinessID != null : !this$acquirerBusinessID.equals(other$acquirerBusinessID)) {
            return false;
        }
        String this$purchaseDate = this.getPurchaseDate();
        String other$purchaseDate = other.getPurchaseDate();
        if (this$purchaseDate == null ? other$purchaseDate != null : !this$purchaseDate.equals(other$purchaseDate)) {
            return false;
        }
        String this$destinationAmount = this.getDestinationAmount();
        String other$destinationAmount = other.getDestinationAmount();
        if (this$destinationAmount == null ? other$destinationAmount != null : !this$destinationAmount.equals(other$destinationAmount)) {
            return false;
        }
        String this$destinationCurrencyCode = this.getDestinationCurrencyCode();
        String other$destinationCurrencyCode = other.getDestinationCurrencyCode();
        if (this$destinationCurrencyCode == null ? other$destinationCurrencyCode != null : !this$destinationCurrencyCode.equals(other$destinationCurrencyCode)) {
            return false;
        }
        String this$sourceAmount = this.getSourceAmount();
        String other$sourceAmount = other.getSourceAmount();
        if (this$sourceAmount == null ? other$sourceAmount != null : !this$sourceAmount.equals(other$sourceAmount)) {
            return false;
        }
        String this$sourceCurrencyCode = this.getSourceCurrencyCode();
        String other$sourceCurrencyCode = other.getSourceCurrencyCode();
        if (this$sourceCurrencyCode == null ? other$sourceCurrencyCode != null : !this$sourceCurrencyCode.equals(other$sourceCurrencyCode)) {
            return false;
        }
        String this$merchantName = this.getMerchantName();
        String other$merchantName = other.getMerchantName();
        if (this$merchantName == null ? other$merchantName != null : !this$merchantName.equals(other$merchantName)) {
            return false;
        }
        String this$merchantCity = this.getMerchantCity();
        String other$merchantCity = other.getMerchantCity();
        if (this$merchantCity == null ? other$merchantCity != null : !this$merchantCity.equals(other$merchantCity)) {
            return false;
        }
        String this$merchantCountryCode = this.getMerchantCountryCode();
        String other$merchantCountryCode = other.getMerchantCountryCode();
        if (this$merchantCountryCode == null ? other$merchantCountryCode != null : !this$merchantCountryCode.equals(other$merchantCountryCode)) {
            return false;
        }
        String this$merchantCategoryCode = this.getMerchantCategoryCode();
        String other$merchantCategoryCode = other.getMerchantCategoryCode();
        if (this$merchantCategoryCode == null ? other$merchantCategoryCode != null : !this$merchantCategoryCode.equals(other$merchantCategoryCode)) {
            return false;
        }
        String this$merchantZIPCode = this.getMerchantZIPCode();
        String other$merchantZIPCode = other.getMerchantZIPCode();
        if (this$merchantZIPCode == null ? other$merchantZIPCode != null : !this$merchantZIPCode.equals(other$merchantZIPCode)) {
            return false;
        }
        String this$merchantStateOrProvinceCode = this.getMerchantStateOrProvinceCode();
        String other$merchantStateOrProvinceCode = other.getMerchantStateOrProvinceCode();
        if (this$merchantStateOrProvinceCode == null ? other$merchantStateOrProvinceCode != null : !this$merchantStateOrProvinceCode.equals(other$merchantStateOrProvinceCode)) {
            return false;
        }
        String this$requestedPaymentService = this.getRequestedPaymentService();
        String other$requestedPaymentService = other.getRequestedPaymentService();
        if (this$requestedPaymentService == null ? other$requestedPaymentService != null : !this$requestedPaymentService.equals(other$requestedPaymentService)) {
            return false;
        }
        String this$numberofPaymentForms = this.getNumberofPaymentForms();
        String other$numberofPaymentForms = other.getNumberofPaymentForms();
        if (this$numberofPaymentForms == null ? other$numberofPaymentForms != null : !this$numberofPaymentForms.equals(other$numberofPaymentForms)) {
            return false;
        }
        String this$usageCode = this.getUsageCode();
        String other$usageCode = other.getUsageCode();
        if (this$usageCode == null ? other$usageCode != null : !this$usageCode.equals(other$usageCode)) {
            return false;
        }
        String this$reasonCode = this.getReasonCode();
        String other$reasonCode = other.getReasonCode();
        if (this$reasonCode == null ? other$reasonCode != null : !this$reasonCode.equals(other$reasonCode)) {
            return false;
        }
        String this$settlementFlag = this.getSettlementFlag();
        String other$settlementFlag = other.getSettlementFlag();
        if (this$settlementFlag == null ? other$settlementFlag != null : !this$settlementFlag.equals(other$settlementFlag)) {
            return false;
        }
        String this$authorizationCode = this.getAuthorizationCode();
        String other$authorizationCode = other.getAuthorizationCode();
        if (this$authorizationCode == null ? other$authorizationCode != null : !this$authorizationCode.equals(other$authorizationCode)) {
            return false;
        }
        String this$reserved1 = this.getReserved1();
        String other$reserved1 = other.getReserved1();
        if (this$reserved1 == null ? other$reserved1 != null : !this$reserved1.equals(other$reserved1)) {
            return false;
        }
        String this$collectionOnlyFlag = this.getCollectionOnlyFlag();
        String other$collectionOnlyFlag = other.getCollectionOnlyFlag();
        if (this$collectionOnlyFlag == null ? other$collectionOnlyFlag != null : !this$collectionOnlyFlag.equals(other$collectionOnlyFlag)) {
            return false;
        }
        String this$posEntryMode = this.getPosEntryMode();
        String other$posEntryMode = other.getPosEntryMode();
        if (this$posEntryMode == null ? other$posEntryMode != null : !this$posEntryMode.equals(other$posEntryMode)) {
            return false;
        }
        String this$centralProcessingDate = this.getCentralProcessingDate();
        String other$centralProcessingDate = other.getCentralProcessingDate();
        if (this$centralProcessingDate == null ? other$centralProcessingDate != null : !this$centralProcessingDate.equals(other$centralProcessingDate)) {
            return false;
        }
        String this$reimbursementAttribute = this.getReimbursementAttribute();
        String other$reimbursementAttribute = other.getReimbursementAttribute();
        if (this$reimbursementAttribute == null ? other$reimbursementAttribute != null : !this$reimbursementAttribute.equals(other$reimbursementAttribute)) {
            return false;
        }
        String this$businessFormatCode = this.getBusinessFormatCode();
        String other$businessFormatCode = other.getBusinessFormatCode();
        if (this$businessFormatCode == null ? other$businessFormatCode != null : !this$businessFormatCode.equals(other$businessFormatCode)) {
            return false;
        }
        String this$tokenAssuranceLevel = this.getTokenAssuranceLevel();
        String other$tokenAssuranceLevel = other.getTokenAssuranceLevel();
        if (this$tokenAssuranceLevel == null ? other$tokenAssuranceLevel != null : !this$tokenAssuranceLevel.equals(other$tokenAssuranceLevel)) {
            return false;
        }
        String this$rateTableID = this.getRateTableID();
        String other$rateTableID = other.getRateTableID();
        if (this$rateTableID == null ? other$rateTableID != null : !this$rateTableID.equals(other$rateTableID)) {
            return false;
        }
        String this$reserved2 = this.getReserved2();
        String other$reserved2 = other.getReserved2();
        if (this$reserved2 == null ? other$reserved2 != null : !this$reserved2.equals(other$reserved2)) {
            return false;
        }
        String this$reserved3 = this.getReserved3();
        String other$reserved3 = other.getReserved3();
        if (this$reserved3 == null ? other$reserved3 != null : !this$reserved3.equals(other$reserved3)) {
            return false;
        }
        String this$documentationIndicator = this.getDocumentationIndicator();
        String other$documentationIndicator = other.getDocumentationIndicator();
        if (this$documentationIndicator == null ? other$documentationIndicator != null : !this$documentationIndicator.equals(other$documentationIndicator)) {
            return false;
        }
        String this$memberMessageText = this.getMemberMessageText();
        String other$memberMessageText = other.getMemberMessageText();
        if (this$memberMessageText == null ? other$memberMessageText != null : !this$memberMessageText.equals(other$memberMessageText)) {
            return false;
        }
        String this$specialConditionIndicators = this.getSpecialConditionIndicators();
        String other$specialConditionIndicators = other.getSpecialConditionIndicators();
        if (this$specialConditionIndicators == null ? other$specialConditionIndicators != null : !this$specialConditionIndicators.equals(other$specialConditionIndicators)) {
            return false;
        }
        String this$feeProgramIndicator = this.getFeeProgramIndicator();
        String other$feeProgramIndicator = other.getFeeProgramIndicator();
        if (this$feeProgramIndicator == null ? other$feeProgramIndicator != null : !this$feeProgramIndicator.equals(other$feeProgramIndicator)) {
            return false;
        }
        String this$issuerCharge = this.getIssuerCharge();
        String other$issuerCharge = other.getIssuerCharge();
        if (this$issuerCharge == null ? other$issuerCharge != null : !this$issuerCharge.equals(other$issuerCharge)) {
            return false;
        }
        String this$persistentFXAppliedIndicator = this.getPersistentFXAppliedIndicator();
        String other$persistentFXAppliedIndicator = other.getPersistentFXAppliedIndicator();
        if (this$persistentFXAppliedIndicator == null ? other$persistentFXAppliedIndicator != null : !this$persistentFXAppliedIndicator.equals(other$persistentFXAppliedIndicator)) {
            return false;
        }
        String this$cardAcceptorID = this.getCardAcceptorID();
        String other$cardAcceptorID = other.getCardAcceptorID();
        if (this$cardAcceptorID == null ? other$cardAcceptorID != null : !this$cardAcceptorID.equals(other$cardAcceptorID)) {
            return false;
        }
        String this$terminalID = this.getTerminalID();
        String other$terminalID = other.getTerminalID();
        if (this$terminalID == null ? other$terminalID != null : !this$terminalID.equals(other$terminalID)) {
            return false;
        }
        String this$nationalReimbursementFee = this.getNationalReimbursementFee();
        String other$nationalReimbursementFee = other.getNationalReimbursementFee();
        if (this$nationalReimbursementFee == null ? other$nationalReimbursementFee != null : !this$nationalReimbursementFee.equals(other$nationalReimbursementFee)) {
            return false;
        }
        String this$specialChargebackIndicator = this.getSpecialChargebackIndicator();
        String other$specialChargebackIndicator = other.getSpecialChargebackIndicator();
        if (this$specialChargebackIndicator == null ? other$specialChargebackIndicator != null : !this$specialChargebackIndicator.equals(other$specialChargebackIndicator)) {
            return false;
        }
        String this$conversionDate = this.getConversionDate();
        String other$conversionDate = other.getConversionDate();
        if (this$conversionDate == null ? other$conversionDate != null : !this$conversionDate.equals(other$conversionDate)) {
            return false;
        }
        String this$reserved4 = this.getReserved4();
        String other$reserved4 = other.getReserved4();
        if (this$reserved4 == null ? other$reserved4 != null : !this$reserved4.equals(other$reserved4)) {
            return false;
        }
        String this$acceptanceTerminalIndicator = this.getAcceptanceTerminalIndicator();
        String other$acceptanceTerminalIndicator = other.getAcceptanceTerminalIndicator();
        if (this$acceptanceTerminalIndicator == null ? other$acceptanceTerminalIndicator != null : !this$acceptanceTerminalIndicator.equals(other$acceptanceTerminalIndicator)) {
            return false;
        }
        String this$prepaidCardIndicator = this.getPrepaidCardIndicator();
        String other$prepaidCardIndicator = other.getPrepaidCardIndicator();
        if (this$prepaidCardIndicator == null ? other$prepaidCardIndicator != null : !this$prepaidCardIndicator.equals(other$prepaidCardIndicator)) {
            return false;
        }
        String this$serviceDevelopmentField = this.getServiceDevelopmentField();
        String other$serviceDevelopmentField = other.getServiceDevelopmentField();
        if (this$serviceDevelopmentField == null ? other$serviceDevelopmentField != null : !this$serviceDevelopmentField.equals(other$serviceDevelopmentField)) {
            return false;
        }
        String this$avsResponseCode = this.getAvsResponseCode();
        String other$avsResponseCode = other.getAvsResponseCode();
        if (this$avsResponseCode == null ? other$avsResponseCode != null : !this$avsResponseCode.equals(other$avsResponseCode)) {
            return false;
        }
        String this$authorizationSourceCode = this.getAuthorizationSourceCode();
        String other$authorizationSourceCode = other.getAuthorizationSourceCode();
        if (this$authorizationSourceCode == null ? other$authorizationSourceCode != null : !this$authorizationSourceCode.equals(other$authorizationSourceCode)) {
            return false;
        }
        String this$purchaseIdentifierFormat = this.getPurchaseIdentifierFormat();
        String other$purchaseIdentifierFormat = other.getPurchaseIdentifierFormat();
        if (this$purchaseIdentifierFormat == null ? other$purchaseIdentifierFormat != null : !this$purchaseIdentifierFormat.equals(other$purchaseIdentifierFormat)) {
            return false;
        }
        String this$installmentPaymentCount = this.getInstallmentPaymentCount();
        String other$installmentPaymentCount = other.getInstallmentPaymentCount();
        if (this$installmentPaymentCount == null ? other$installmentPaymentCount != null : !this$installmentPaymentCount.equals(other$installmentPaymentCount)) {
            return false;
        }
        String this$purchaseIdentifier = this.getPurchaseIdentifier();
        String other$purchaseIdentifier = other.getPurchaseIdentifier();
        if (this$purchaseIdentifier == null ? other$purchaseIdentifier != null : !this$purchaseIdentifier.equals(other$purchaseIdentifier)) {
            return false;
        }
        String this$cashback = this.getCashback();
        String other$cashback = other.getCashback();
        if (this$cashback == null ? other$cashback != null : !this$cashback.equals(other$cashback)) {
            return false;
        }
        String this$chipConditionCode = this.getChipConditionCode();
        String other$chipConditionCode = other.getChipConditionCode();
        if (this$chipConditionCode == null ? other$chipConditionCode != null : !this$chipConditionCode.equals(other$chipConditionCode)) {
            return false;
        }
        String this$transactionIdentifier = this.getTransactionIdentifier();
        String other$transactionIdentifier = other.getTransactionIdentifier();
        if (this$transactionIdentifier == null ? other$transactionIdentifier != null : !this$transactionIdentifier.equals(other$transactionIdentifier)) {
            return false;
        }
        String this$authorizedAmount = this.getAuthorizedAmount();
        String other$authorizedAmount = other.getAuthorizedAmount();
        if (this$authorizedAmount == null ? other$authorizedAmount != null : !this$authorizedAmount.equals(other$authorizedAmount)) {
            return false;
        }
        String this$authorizationCurrencyCode = this.getAuthorizationCurrencyCode();
        String other$authorizationCurrencyCode = other.getAuthorizationCurrencyCode();
        if (this$authorizationCurrencyCode == null ? other$authorizationCurrencyCode != null : !this$authorizationCurrencyCode.equals(other$authorizationCurrencyCode)) {
            return false;
        }
        String this$authorizationResponseCode = this.getAuthorizationResponseCode();
        String other$authorizationResponseCode = other.getAuthorizationResponseCode();
        if (this$authorizationResponseCode == null ? other$authorizationResponseCode != null : !this$authorizationResponseCode.equals(other$authorizationResponseCode)) {
            return false;
        }
        String this$validationCode = this.getValidationCode();
        String other$validationCode = other.getValidationCode();
        if (this$validationCode == null ? other$validationCode != null : !this$validationCode.equals(other$validationCode)) {
            return false;
        }
        String this$excludedTransactionIdentifierReason = this.getExcludedTransactionIdentifierReason();
        String other$excludedTransactionIdentifierReason = other.getExcludedTransactionIdentifierReason();
        if (this$excludedTransactionIdentifierReason == null ? other$excludedTransactionIdentifierReason != null : !this$excludedTransactionIdentifierReason.equals(other$excludedTransactionIdentifierReason)) {
            return false;
        }
        String this$reserved5 = this.getReserved5();
        String other$reserved5 = other.getReserved5();
        if (this$reserved5 == null ? other$reserved5 != null : !this$reserved5.equals(other$reserved5)) {
            return false;
        }
        String this$reserved6 = this.getReserved6();
        String other$reserved6 = other.getReserved6();
        if (this$reserved6 == null ? other$reserved6 != null : !this$reserved6.equals(other$reserved6)) {
            return false;
        }
        String this$multipleClearingSequenceNumber = this.getMultipleClearingSequenceNumber();
        String other$multipleClearingSequenceNumber = other.getMultipleClearingSequenceNumber();
        if (this$multipleClearingSequenceNumber == null ? other$multipleClearingSequenceNumber != null : !this$multipleClearingSequenceNumber.equals(other$multipleClearingSequenceNumber)) {
            return false;
        }
        String this$multipleClearingSequenceCount = this.getMultipleClearingSequenceCount();
        String other$multipleClearingSequenceCount = other.getMultipleClearingSequenceCount();
        if (this$multipleClearingSequenceCount == null ? other$multipleClearingSequenceCount != null : !this$multipleClearingSequenceCount.equals(other$multipleClearingSequenceCount)) {
            return false;
        }
        String this$totalAuthorizedAmount = this.getTotalAuthorizedAmount();
        String other$totalAuthorizedAmount = other.getTotalAuthorizedAmount();
        if (this$totalAuthorizedAmount == null ? other$totalAuthorizedAmount != null : !this$totalAuthorizedAmount.equals(other$totalAuthorizedAmount)) {
            return false;
        }
        String this$informationIndicator = this.getInformationIndicator();
        String other$informationIndicator = other.getInformationIndicator();
        if (this$informationIndicator == null ? other$informationIndicator != null : !this$informationIndicator.equals(other$informationIndicator)) {
            return false;
        }
        String this$merchantTelephoneNumber = this.getMerchantTelephoneNumber();
        String other$merchantTelephoneNumber = other.getMerchantTelephoneNumber();
        if (this$merchantTelephoneNumber == null ? other$merchantTelephoneNumber != null : !this$merchantTelephoneNumber.equals(other$merchantTelephoneNumber)) {
            return false;
        }
        String this$additionalDataIndicator = this.getAdditionalDataIndicator();
        String other$additionalDataIndicator = other.getAdditionalDataIndicator();
        if (this$additionalDataIndicator == null ? other$additionalDataIndicator != null : !this$additionalDataIndicator.equals(other$additionalDataIndicator)) {
            return false;
        }
        String this$merchantVolumeIndicator = this.getMerchantVolumeIndicator();
        String other$merchantVolumeIndicator = other.getMerchantVolumeIndicator();
        if (this$merchantVolumeIndicator == null ? other$merchantVolumeIndicator != null : !this$merchantVolumeIndicator.equals(other$merchantVolumeIndicator)) {
            return false;
        }
        String this$electronicCommerceGoodsIndicator = this.getElectronicCommerceGoodsIndicator();
        String other$electronicCommerceGoodsIndicator = other.getElectronicCommerceGoodsIndicator();
        if (this$electronicCommerceGoodsIndicator == null ? other$electronicCommerceGoodsIndicator != null : !this$electronicCommerceGoodsIndicator.equals(other$electronicCommerceGoodsIndicator)) {
            return false;
        }
        String this$merchantVerificationValue = this.getMerchantVerificationValue();
        String other$merchantVerificationValue = other.getMerchantVerificationValue();
        if (this$merchantVerificationValue == null ? other$merchantVerificationValue != null : !this$merchantVerificationValue.equals(other$merchantVerificationValue)) {
            return false;
        }
        String this$interchangeFeeAmount = this.getInterchangeFeeAmount();
        String other$interchangeFeeAmount = other.getInterchangeFeeAmount();
        if (this$interchangeFeeAmount == null ? other$interchangeFeeAmount != null : !this$interchangeFeeAmount.equals(other$interchangeFeeAmount)) {
            return false;
        }
        String this$interchangeFeeSign = this.getInterchangeFeeSign();
        String other$interchangeFeeSign = other.getInterchangeFeeSign();
        if (this$interchangeFeeSign == null ? other$interchangeFeeSign != null : !this$interchangeFeeSign.equals(other$interchangeFeeSign)) {
            return false;
        }
        String this$sourceCurrtoBaseCurrExcRate = this.getSourceCurrtoBaseCurrExcRate();
        String other$sourceCurrtoBaseCurrExcRate = other.getSourceCurrtoBaseCurrExcRate();
        if (this$sourceCurrtoBaseCurrExcRate == null ? other$sourceCurrtoBaseCurrExcRate != null : !this$sourceCurrtoBaseCurrExcRate.equals(other$sourceCurrtoBaseCurrExcRate)) {
            return false;
        }
        String this$baseCurrtoDestinationCurrExcRate = this.getBaseCurrtoDestinationCurrExcRate();
        String other$baseCurrtoDestinationCurrExcRate = other.getBaseCurrtoDestinationCurrExcRate();
        if (this$baseCurrtoDestinationCurrExcRate == null ? other$baseCurrtoDestinationCurrExcRate != null : !this$baseCurrtoDestinationCurrExcRate.equals(other$baseCurrtoDestinationCurrExcRate)) {
            return false;
        }
        String this$optionalIssuerISAAmount = this.getOptionalIssuerISAAmount();
        String other$optionalIssuerISAAmount = other.getOptionalIssuerISAAmount();
        if (this$optionalIssuerISAAmount == null ? other$optionalIssuerISAAmount != null : !this$optionalIssuerISAAmount.equals(other$optionalIssuerISAAmount)) {
            return false;
        }
        String this$productID = this.getProductID();
        String other$productID = other.getProductID();
        if (this$productID == null ? other$productID != null : !this$productID.equals(other$productID)) {
            return false;
        }
        String this$programID = this.getProgramID();
        String other$programID = other.getProgramID();
        if (this$programID == null ? other$programID != null : !this$programID.equals(other$programID)) {
            return false;
        }
        String this$dccIndicator = this.getDccIndicator();
        String other$dccIndicator = other.getDccIndicator();
        if (this$dccIndicator == null ? other$dccIndicator != null : !this$dccIndicator.equals(other$dccIndicator)) {
            return false;
        }
        String this$accTypeIdentification = this.getAccTypeIdentification();
        String other$accTypeIdentification = other.getAccTypeIdentification();
        if (this$accTypeIdentification == null ? other$accTypeIdentification != null : !this$accTypeIdentification.equals(other$accTypeIdentification)) {
            return false;
        }
        String this$panToken = this.getPanToken();
        String other$panToken = other.getPanToken();
        if (this$panToken == null ? other$panToken != null : !this$panToken.equals(other$panToken)) {
            return false;
        }
        String this$reserved7 = this.getReserved7();
        String other$reserved7 = other.getReserved7();
        if (this$reserved7 == null ? other$reserved7 != null : !this$reserved7.equals(other$reserved7)) {
            return false;
        }
        String this$accFundingSource = this.getAccFundingSource();
        String other$accFundingSource = other.getAccFundingSource();
        if (this$accFundingSource == null ? other$accFundingSource != null : !this$accFundingSource.equals(other$accFundingSource)) {
            return false;
        }
        String this$cvv2ResultCode = this.getCvv2ResultCode();
        String other$cvv2ResultCode = other.getCvv2ResultCode();
        if (this$cvv2ResultCode == null ? other$cvv2ResultCode != null : !this$cvv2ResultCode.equals(other$cvv2ResultCode)) {
            return false;
        }
        String this$transactionType = this.getTransactionType();
        String other$transactionType = other.getTransactionType();
        if (this$transactionType == null ? other$transactionType != null : !this$transactionType.equals(other$transactionType)) {
            return false;
        }
        String this$cardSequenceNumber = this.getCardSequenceNumber();
        String other$cardSequenceNumber = other.getCardSequenceNumber();
        if (this$cardSequenceNumber == null ? other$cardSequenceNumber != null : !this$cardSequenceNumber.equals(other$cardSequenceNumber)) {
            return false;
        }
        String this$terminalTranDate = this.getTerminalTranDate();
        String other$terminalTranDate = other.getTerminalTranDate();
        if (this$terminalTranDate == null ? other$terminalTranDate != null : !this$terminalTranDate.equals(other$terminalTranDate)) {
            return false;
        }
        String this$terminalCapabilityProfile = this.getTerminalCapabilityProfile();
        String other$terminalCapabilityProfile = other.getTerminalCapabilityProfile();
        if (this$terminalCapabilityProfile == null ? other$terminalCapabilityProfile != null : !this$terminalCapabilityProfile.equals(other$terminalCapabilityProfile)) {
            return false;
        }
        String this$terminalCountryCode = this.getTerminalCountryCode();
        String other$terminalCountryCode = other.getTerminalCountryCode();
        if (this$terminalCountryCode == null ? other$terminalCountryCode != null : !this$terminalCountryCode.equals(other$terminalCountryCode)) {
            return false;
        }
        String this$terminalSerialNumber = this.getTerminalSerialNumber();
        String other$terminalSerialNumber = other.getTerminalSerialNumber();
        if (this$terminalSerialNumber == null ? other$terminalSerialNumber != null : !this$terminalSerialNumber.equals(other$terminalSerialNumber)) {
            return false;
        }
        String this$unpredictableNumber = this.getUnpredictableNumber();
        String other$unpredictableNumber = other.getUnpredictableNumber();
        if (this$unpredictableNumber == null ? other$unpredictableNumber != null : !this$unpredictableNumber.equals(other$unpredictableNumber)) {
            return false;
        }
        String this$applicationTransactionCounter = this.getApplicationTransactionCounter();
        String other$applicationTransactionCounter = other.getApplicationTransactionCounter();
        if (this$applicationTransactionCounter == null ? other$applicationTransactionCounter != null : !this$applicationTransactionCounter.equals(other$applicationTransactionCounter)) {
            return false;
        }
        String this$applicationInterchangeProfile = this.getApplicationInterchangeProfile();
        String other$applicationInterchangeProfile = other.getApplicationInterchangeProfile();
        if (this$applicationInterchangeProfile == null ? other$applicationInterchangeProfile != null : !this$applicationInterchangeProfile.equals(other$applicationInterchangeProfile)) {
            return false;
        }
        String this$cryptogram = this.getCryptogram();
        String other$cryptogram = other.getCryptogram();
        if (this$cryptogram == null ? other$cryptogram != null : !this$cryptogram.equals(other$cryptogram)) {
            return false;
        }
        String this$issuerAppDataByte2 = this.getIssuerAppDataByte2();
        String other$issuerAppDataByte2 = other.getIssuerAppDataByte2();
        if (this$issuerAppDataByte2 == null ? other$issuerAppDataByte2 != null : !this$issuerAppDataByte2.equals(other$issuerAppDataByte2)) {
            return false;
        }
        String this$issuerAppDataByte3 = this.getIssuerAppDataByte3();
        String other$issuerAppDataByte3 = other.getIssuerAppDataByte3();
        if (this$issuerAppDataByte3 == null ? other$issuerAppDataByte3 != null : !this$issuerAppDataByte3.equals(other$issuerAppDataByte3)) {
            return false;
        }
        String this$terminalVeriResults = this.getTerminalVeriResults();
        String other$terminalVeriResults = other.getTerminalVeriResults();
        if (this$terminalVeriResults == null ? other$terminalVeriResults != null : !this$terminalVeriResults.equals(other$terminalVeriResults)) {
            return false;
        }
        String this$issuerAppDataByte4to7 = this.getIssuerAppDataByte4to7();
        String other$issuerAppDataByte4to7 = other.getIssuerAppDataByte4to7();
        if (this$issuerAppDataByte4to7 == null ? other$issuerAppDataByte4to7 != null : !this$issuerAppDataByte4to7.equals(other$issuerAppDataByte4to7)) {
            return false;
        }
        String this$cryptogramAmount = this.getCryptogramAmount();
        String other$cryptogramAmount = other.getCryptogramAmount();
        if (this$cryptogramAmount == null ? other$cryptogramAmount != null : !this$cryptogramAmount.equals(other$cryptogramAmount)) {
            return false;
        }
        String this$issuerAppDataByte8 = this.getIssuerAppDataByte8();
        String other$issuerAppDataByte8 = other.getIssuerAppDataByte8();
        if (this$issuerAppDataByte8 == null ? other$issuerAppDataByte8 != null : !this$issuerAppDataByte8.equals(other$issuerAppDataByte8)) {
            return false;
        }
        String this$issuerAppDataByte9to16 = this.getIssuerAppDataByte9to16();
        String other$issuerAppDataByte9to16 = other.getIssuerAppDataByte9to16();
        if (this$issuerAppDataByte9to16 == null ? other$issuerAppDataByte9to16 != null : !this$issuerAppDataByte9to16.equals(other$issuerAppDataByte9to16)) {
            return false;
        }
        String this$issuerAppDataByte1 = this.getIssuerAppDataByte1();
        String other$issuerAppDataByte1 = other.getIssuerAppDataByte1();
        if (this$issuerAppDataByte1 == null ? other$issuerAppDataByte1 != null : !this$issuerAppDataByte1.equals(other$issuerAppDataByte1)) {
            return false;
        }
        String this$issuerAppDataByte17 = this.getIssuerAppDataByte17();
        String other$issuerAppDataByte17 = other.getIssuerAppDataByte17();
        if (this$issuerAppDataByte17 == null ? other$issuerAppDataByte17 != null : !this$issuerAppDataByte17.equals(other$issuerAppDataByte17)) {
            return false;
        }
        String this$issuerAppDataByte18to32 = this.getIssuerAppDataByte18to32();
        String other$issuerAppDataByte18to32 = other.getIssuerAppDataByte18to32();
        if (this$issuerAppDataByte18to32 == null ? other$issuerAppDataByte18to32 != null : !this$issuerAppDataByte18to32.equals(other$issuerAppDataByte18to32)) {
            return false;
        }
        String this$formFactorIndicator = this.getFormFactorIndicator();
        String other$formFactorIndicator = other.getFormFactorIndicator();
        if (this$formFactorIndicator == null ? other$formFactorIndicator != null : !this$formFactorIndicator.equals(other$formFactorIndicator)) {
            return false;
        }
        String this$issuerScript1Results = this.getIssuerScript1Results();
        String other$issuerScript1Results = other.getIssuerScript1Results();
        if (this$issuerScript1Results == null ? other$issuerScript1Results != null : !this$issuerScript1Results.equals(other$issuerScript1Results)) {
            return false;
        }
        String this$transactionCode = this.getTransactionCode();
        String other$transactionCode = other.getTransactionCode();
        if (this$transactionCode == null ? other$transactionCode != null : !this$transactionCode.equals(other$transactionCode)) {
            return false;
        }
        String this$destinationIdentifier = this.getDestinationIdentifier();
        String other$destinationIdentifier = other.getDestinationIdentifier();
        if (this$destinationIdentifier == null ? other$destinationIdentifier != null : !this$destinationIdentifier.equals(other$destinationIdentifier)) {
            return false;
        }
        String this$sourceIdentifier = this.getSourceIdentifier();
        String other$sourceIdentifier = other.getSourceIdentifier();
        if (this$sourceIdentifier == null ? other$sourceIdentifier != null : !this$sourceIdentifier.equals(other$sourceIdentifier)) {
            return false;
        }
        String this$reasonCode1 = this.getReasonCode1();
        String other$reasonCode1 = other.getReasonCode1();
        if (this$reasonCode1 == null ? other$reasonCode1 != null : !this$reasonCode1.equals(other$reasonCode1)) {
            return false;
        }
        String this$countryCode = this.getCountryCode();
        String other$countryCode = other.getCountryCode();
        if (this$countryCode == null ? other$countryCode != null : !this$countryCode.equals(other$countryCode)) {
            return false;
        }
        String this$eventDate = this.getEventDate();
        String other$eventDate = other.getEventDate();
        if (this$eventDate == null ? other$eventDate != null : !this$eventDate.equals(other$eventDate)) {
            return false;
        }
        String this$accountNumber1 = this.getAccountNumber1();
        String other$accountNumber1 = other.getAccountNumber1();
        if (this$accountNumber1 == null ? other$accountNumber1 != null : !this$accountNumber1.equals(other$accountNumber1)) {
            return false;
        }
        String this$accountNumberExtension1 = this.getAccountNumberExtension1();
        String string = other.getAccountNumberExtension1();
        if (this$accountNumberExtension1 == null ? other$accountNumberExtension1 != null : !this$accountNumberExtension1.equals(other$accountNumberExtension1)) {
            return false;
        }
        String string2 = this.getDestinationAmount1();
        String string3 = other.getDestinationAmount1();
        if (this$destinationAmount1 == null ? other$destinationAmount1 != null : !this$destinationAmount1.equals(other$destinationAmount1)) {
            return false;
        }
        String string4 = this.getDestinationCurrencyCode1();
        String string5 = other.getDestinationCurrencyCode1();
        if (this$destinationCurrencyCode1 == null ? other$destinationCurrencyCode1 != null : !this$destinationCurrencyCode1.equals(other$destinationCurrencyCode1)) {
            return false;
        }
        String string6 = this.getSourceAmount1();
        String string7 = other.getSourceAmount1();
        if (this$sourceAmount1 == null ? other$sourceAmount1 != null : !this$sourceAmount1.equals(other$sourceAmount1)) {
            return false;
        }
        String string8 = this.getSourceCurrencyCode1();
        String string9 = other.getSourceCurrencyCode1();
        if (this$sourceCurrencyCode1 == null ? other$sourceCurrencyCode1 != null : !this$sourceCurrencyCode1.equals(other$sourceCurrencyCode1)) {
            return false;
        }
        String string10 = this.getMessageText();
        String string11 = other.getMessageText();
        if (this$messageText == null ? other$messageText != null : !this$messageText.equals(other$messageText)) {
            return false;
        }
        String string12 = this.getSettlementFlag1();
        String string13 = other.getSettlementFlag1();
        if (this$settlementFlag1 == null ? other$settlementFlag1 != null : !this$settlementFlag1.equals(other$settlementFlag1)) {
            return false;
        }
        String string14 = this.getTransactionIdentifier1();
        String string15 = other.getTransactionIdentifier1();
        if (this$transactionIdentifier1 == null ? other$transactionIdentifier1 != null : !this$transactionIdentifier1.equals(other$transactionIdentifier1)) {
            return false;
        }
        String string16 = this.getReserved8();
        String string17 = other.getReserved8();
        if (this$reserved8 == null ? other$reserved8 != null : !this$reserved8.equals(other$reserved8)) {
            return false;
        }
        String string18 = this.getCentralProcessingDate1();
        String string19 = other.getCentralProcessingDate1();
        if (this$centralProcessingDate1 == null ? other$centralProcessingDate1 != null : !this$centralProcessingDate1.equals(other$centralProcessingDate1)) {
            return false;
        }
        String string20 = this.getReimbursementAttribute1();
        String string21 = other.getReimbursementAttribute1();
        if (this$reimbursementAttribute1 == null ? other$reimbursementAttribute1 != null : !this$reimbursementAttribute1.equals(other$reimbursementAttribute1)) {
            return false;
        }
        String string22 = this.getServiceProcessingType();
        String string23 = other.getServiceProcessingType();
        if (this$serviceProcessingType == null ? other$serviceProcessingType != null : !this$serviceProcessingType.equals(other$serviceProcessingType)) {
            return false;
        }
        String string24 = this.getReserved9();
        String string25 = other.getReserved9();
        if (this$reserved9 == null ? other$reserved9 != null : !this$reserved9.equals(other$reserved9)) {
            return false;
        }
        String string26 = this.getFastFundsIndicator();
        String string27 = other.getFastFundsIndicator();
        if (this$fastFundsIndicator == null ? other$fastFundsIndicator != null : !this$fastFundsIndicator.equals(other$fastFundsIndicator)) {
            return false;
        }
        String string28 = this.getBusinessFormatCodeCR();
        String string29 = other.getBusinessFormatCodeCR();
        if (this$businessFormatCodeCR == null ? other$businessFormatCodeCR != null : !this$businessFormatCodeCR.equals(other$businessFormatCodeCR)) {
            return false;
        }
        String string30 = this.getBusinessApplicationID();
        String string31 = other.getBusinessApplicationID();
        if (this$businessApplicationID == null ? other$businessApplicationID != null : !this$businessApplicationID.equals(other$businessApplicationID)) {
            return false;
        }
        String string32 = this.getSourceofFunds();
        String string33 = other.getSourceofFunds();
        if (this$sourceofFunds == null ? other$sourceofFunds != null : !this$sourceofFunds.equals(other$sourceofFunds)) {
            return false;
        }
        String string34 = this.getPaymentReversalReasonCode();
        String string35 = other.getPaymentReversalReasonCode();
        if (this$paymentReversalReasonCode == null ? other$paymentReversalReasonCode != null : !this$paymentReversalReasonCode.equals(other$paymentReversalReasonCode)) {
            return false;
        }
        String string36 = this.getSenderReferenceNumber();
        String string37 = other.getSenderReferenceNumber();
        if (this$senderReferenceNumber == null ? other$senderReferenceNumber != null : !this$senderReferenceNumber.equals(other$senderReferenceNumber)) {
            return false;
        }
        String string38 = this.getSenderAccountNumber();
        String string39 = other.getSenderAccountNumber();
        if (this$senderAccountNumber == null ? other$senderAccountNumber != null : !this$senderAccountNumber.equals(other$senderAccountNumber)) {
            return false;
        }
        String string40 = this.getSenderName();
        String string41 = other.getSenderName();
        if (this$senderName == null ? other$senderName != null : !this$senderName.equals(other$senderName)) {
            return false;
        }
        String string42 = this.getSenderAddress();
        String string43 = other.getSenderAddress();
        if (this$senderAddress == null ? other$senderAddress != null : !this$senderAddress.equals(other$senderAddress)) {
            return false;
        }
        String string44 = this.getSenderCity();
        String string45 = other.getSenderCity();
        if (this$senderCity == null ? other$senderCity != null : !this$senderCity.equals(other$senderCity)) {
            return false;
        }
        String string46 = this.getSenderState();
        String string47 = other.getSenderState();
        if (this$senderState == null ? other$senderState != null : !this$senderState.equals(other$senderState)) {
            return false;
        }
        String string48 = this.getSenderCountry();
        String string49 = other.getSenderCountry();
        return !(this$senderCountry == null ? other$senderCountry != null : !this$senderCountry.equals(other$senderCountry));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TCRZeroVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Character $authorizationCharaIndicator = this.getAuthorizationCharaIndicator();
        result = result * 59 + ($authorizationCharaIndicator == null ? 43 : ((Object)$authorizationCharaIndicator).hashCode());
        Character $posTerminalCapability = this.getPosTerminalCapability();
        result = result * 59 + ($posTerminalCapability == null ? 43 : ((Object)$posTerminalCapability).hashCode());
        Character $cardholderIDMethod = this.getCardholderIDMethod();
        result = result * 59 + ($cardholderIDMethod == null ? 43 : ((Object)$cardholderIDMethod).hashCode());
        Character $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + ($ecomIndicator == null ? 43 : ((Object)$ecomIndicator).hashCode());
        Character $accountSelection = this.getAccountSelection();
        result = result * 59 + ($accountSelection == null ? 43 : ((Object)$accountSelection).hashCode());
        Character $posEnvironment = this.getPosEnvironment();
        result = result * 59 + ($posEnvironment == null ? 43 : ((Object)$posEnvironment).hashCode());
        Character $marketSpecificAuthDataIndicator = this.getMarketSpecificAuthDataIndicator();
        result = result * 59 + ($marketSpecificAuthDataIndicator == null ? 43 : ((Object)$marketSpecificAuthDataIndicator).hashCode());
        Character $spendQualifiedIndicator = this.getSpendQualifiedIndicator();
        result = result * 59 + ($spendQualifiedIndicator == null ? 43 : ((Object)$spendQualifiedIndicator).hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $txnCodeQualifier = this.getTxnCodeQualifier();
        result = result * 59 + ($txnCodeQualifier == null ? 43 : $txnCodeQualifier.hashCode());
        String $txnComponentSeqNum = this.getTxnComponentSeqNum();
        result = result * 59 + ($txnComponentSeqNum == null ? 43 : $txnComponentSeqNum.hashCode());
        String $accountNumber = this.getAccountNumber();
        result = result * 59 + ($accountNumber == null ? 43 : $accountNumber.hashCode());
        String $accountNumberExtension = this.getAccountNumberExtension();
        result = result * 59 + ($accountNumberExtension == null ? 43 : $accountNumberExtension.hashCode());
        String $floorLimitIndicator = this.getFloorLimitIndicator();
        result = result * 59 + ($floorLimitIndicator == null ? 43 : $floorLimitIndicator.hashCode());
        String $crbExceptionFileIndicator = this.getCrbExceptionFileIndicator();
        result = result * 59 + ($crbExceptionFileIndicator == null ? 43 : $crbExceptionFileIndicator.hashCode());
        String $reserved = this.getReserved();
        result = result * 59 + ($reserved == null ? 43 : $reserved.hashCode());
        String $arn = this.getArn();
        result = result * 59 + ($arn == null ? 43 : $arn.hashCode());
        String $acquirerBusinessID = this.getAcquirerBusinessID();
        result = result * 59 + ($acquirerBusinessID == null ? 43 : $acquirerBusinessID.hashCode());
        String $purchaseDate = this.getPurchaseDate();
        result = result * 59 + ($purchaseDate == null ? 43 : $purchaseDate.hashCode());
        String $destinationAmount = this.getDestinationAmount();
        result = result * 59 + ($destinationAmount == null ? 43 : $destinationAmount.hashCode());
        String $destinationCurrencyCode = this.getDestinationCurrencyCode();
        result = result * 59 + ($destinationCurrencyCode == null ? 43 : $destinationCurrencyCode.hashCode());
        String $sourceAmount = this.getSourceAmount();
        result = result * 59 + ($sourceAmount == null ? 43 : $sourceAmount.hashCode());
        String $sourceCurrencyCode = this.getSourceCurrencyCode();
        result = result * 59 + ($sourceCurrencyCode == null ? 43 : $sourceCurrencyCode.hashCode());
        String $merchantName = this.getMerchantName();
        result = result * 59 + ($merchantName == null ? 43 : $merchantName.hashCode());
        String $merchantCity = this.getMerchantCity();
        result = result * 59 + ($merchantCity == null ? 43 : $merchantCity.hashCode());
        String $merchantCountryCode = this.getMerchantCountryCode();
        result = result * 59 + ($merchantCountryCode == null ? 43 : $merchantCountryCode.hashCode());
        String $merchantCategoryCode = this.getMerchantCategoryCode();
        result = result * 59 + ($merchantCategoryCode == null ? 43 : $merchantCategoryCode.hashCode());
        String $merchantZIPCode = this.getMerchantZIPCode();
        result = result * 59 + ($merchantZIPCode == null ? 43 : $merchantZIPCode.hashCode());
        String $merchantStateOrProvinceCode = this.getMerchantStateOrProvinceCode();
        result = result * 59 + ($merchantStateOrProvinceCode == null ? 43 : $merchantStateOrProvinceCode.hashCode());
        String $requestedPaymentService = this.getRequestedPaymentService();
        result = result * 59 + ($requestedPaymentService == null ? 43 : $requestedPaymentService.hashCode());
        String $numberofPaymentForms = this.getNumberofPaymentForms();
        result = result * 59 + ($numberofPaymentForms == null ? 43 : $numberofPaymentForms.hashCode());
        String $usageCode = this.getUsageCode();
        result = result * 59 + ($usageCode == null ? 43 : $usageCode.hashCode());
        String $reasonCode = this.getReasonCode();
        result = result * 59 + ($reasonCode == null ? 43 : $reasonCode.hashCode());
        String $settlementFlag = this.getSettlementFlag();
        result = result * 59 + ($settlementFlag == null ? 43 : $settlementFlag.hashCode());
        String $authorizationCode = this.getAuthorizationCode();
        result = result * 59 + ($authorizationCode == null ? 43 : $authorizationCode.hashCode());
        String $reserved1 = this.getReserved1();
        result = result * 59 + ($reserved1 == null ? 43 : $reserved1.hashCode());
        String $collectionOnlyFlag = this.getCollectionOnlyFlag();
        result = result * 59 + ($collectionOnlyFlag == null ? 43 : $collectionOnlyFlag.hashCode());
        String $posEntryMode = this.getPosEntryMode();
        result = result * 59 + ($posEntryMode == null ? 43 : $posEntryMode.hashCode());
        String $centralProcessingDate = this.getCentralProcessingDate();
        result = result * 59 + ($centralProcessingDate == null ? 43 : $centralProcessingDate.hashCode());
        String $reimbursementAttribute = this.getReimbursementAttribute();
        result = result * 59 + ($reimbursementAttribute == null ? 43 : $reimbursementAttribute.hashCode());
        String $businessFormatCode = this.getBusinessFormatCode();
        result = result * 59 + ($businessFormatCode == null ? 43 : $businessFormatCode.hashCode());
        String $tokenAssuranceLevel = this.getTokenAssuranceLevel();
        result = result * 59 + ($tokenAssuranceLevel == null ? 43 : $tokenAssuranceLevel.hashCode());
        String $rateTableID = this.getRateTableID();
        result = result * 59 + ($rateTableID == null ? 43 : $rateTableID.hashCode());
        String $reserved2 = this.getReserved2();
        result = result * 59 + ($reserved2 == null ? 43 : $reserved2.hashCode());
        String $reserved3 = this.getReserved3();
        result = result * 59 + ($reserved3 == null ? 43 : $reserved3.hashCode());
        String $documentationIndicator = this.getDocumentationIndicator();
        result = result * 59 + ($documentationIndicator == null ? 43 : $documentationIndicator.hashCode());
        String $memberMessageText = this.getMemberMessageText();
        result = result * 59 + ($memberMessageText == null ? 43 : $memberMessageText.hashCode());
        String $specialConditionIndicators = this.getSpecialConditionIndicators();
        result = result * 59 + ($specialConditionIndicators == null ? 43 : $specialConditionIndicators.hashCode());
        String $feeProgramIndicator = this.getFeeProgramIndicator();
        result = result * 59 + ($feeProgramIndicator == null ? 43 : $feeProgramIndicator.hashCode());
        String $issuerCharge = this.getIssuerCharge();
        result = result * 59 + ($issuerCharge == null ? 43 : $issuerCharge.hashCode());
        String $persistentFXAppliedIndicator = this.getPersistentFXAppliedIndicator();
        result = result * 59 + ($persistentFXAppliedIndicator == null ? 43 : $persistentFXAppliedIndicator.hashCode());
        String $cardAcceptorID = this.getCardAcceptorID();
        result = result * 59 + ($cardAcceptorID == null ? 43 : $cardAcceptorID.hashCode());
        String $terminalID = this.getTerminalID();
        result = result * 59 + ($terminalID == null ? 43 : $terminalID.hashCode());
        String $nationalReimbursementFee = this.getNationalReimbursementFee();
        result = result * 59 + ($nationalReimbursementFee == null ? 43 : $nationalReimbursementFee.hashCode());
        String $specialChargebackIndicator = this.getSpecialChargebackIndicator();
        result = result * 59 + ($specialChargebackIndicator == null ? 43 : $specialChargebackIndicator.hashCode());
        String $conversionDate = this.getConversionDate();
        result = result * 59 + ($conversionDate == null ? 43 : $conversionDate.hashCode());
        String $reserved4 = this.getReserved4();
        result = result * 59 + ($reserved4 == null ? 43 : $reserved4.hashCode());
        String $acceptanceTerminalIndicator = this.getAcceptanceTerminalIndicator();
        result = result * 59 + ($acceptanceTerminalIndicator == null ? 43 : $acceptanceTerminalIndicator.hashCode());
        String $prepaidCardIndicator = this.getPrepaidCardIndicator();
        result = result * 59 + ($prepaidCardIndicator == null ? 43 : $prepaidCardIndicator.hashCode());
        String $serviceDevelopmentField = this.getServiceDevelopmentField();
        result = result * 59 + ($serviceDevelopmentField == null ? 43 : $serviceDevelopmentField.hashCode());
        String $avsResponseCode = this.getAvsResponseCode();
        result = result * 59 + ($avsResponseCode == null ? 43 : $avsResponseCode.hashCode());
        String $authorizationSourceCode = this.getAuthorizationSourceCode();
        result = result * 59 + ($authorizationSourceCode == null ? 43 : $authorizationSourceCode.hashCode());
        String $purchaseIdentifierFormat = this.getPurchaseIdentifierFormat();
        result = result * 59 + ($purchaseIdentifierFormat == null ? 43 : $purchaseIdentifierFormat.hashCode());
        String $installmentPaymentCount = this.getInstallmentPaymentCount();
        result = result * 59 + ($installmentPaymentCount == null ? 43 : $installmentPaymentCount.hashCode());
        String $purchaseIdentifier = this.getPurchaseIdentifier();
        result = result * 59 + ($purchaseIdentifier == null ? 43 : $purchaseIdentifier.hashCode());
        String $cashback = this.getCashback();
        result = result * 59 + ($cashback == null ? 43 : $cashback.hashCode());
        String $chipConditionCode = this.getChipConditionCode();
        result = result * 59 + ($chipConditionCode == null ? 43 : $chipConditionCode.hashCode());
        String $transactionIdentifier = this.getTransactionIdentifier();
        result = result * 59 + ($transactionIdentifier == null ? 43 : $transactionIdentifier.hashCode());
        String $authorizedAmount = this.getAuthorizedAmount();
        result = result * 59 + ($authorizedAmount == null ? 43 : $authorizedAmount.hashCode());
        String $authorizationCurrencyCode = this.getAuthorizationCurrencyCode();
        result = result * 59 + ($authorizationCurrencyCode == null ? 43 : $authorizationCurrencyCode.hashCode());
        String $authorizationResponseCode = this.getAuthorizationResponseCode();
        result = result * 59 + ($authorizationResponseCode == null ? 43 : $authorizationResponseCode.hashCode());
        String $validationCode = this.getValidationCode();
        result = result * 59 + ($validationCode == null ? 43 : $validationCode.hashCode());
        String $excludedTransactionIdentifierReason = this.getExcludedTransactionIdentifierReason();
        result = result * 59 + ($excludedTransactionIdentifierReason == null ? 43 : $excludedTransactionIdentifierReason.hashCode());
        String $reserved5 = this.getReserved5();
        result = result * 59 + ($reserved5 == null ? 43 : $reserved5.hashCode());
        String $reserved6 = this.getReserved6();
        result = result * 59 + ($reserved6 == null ? 43 : $reserved6.hashCode());
        String $multipleClearingSequenceNumber = this.getMultipleClearingSequenceNumber();
        result = result * 59 + ($multipleClearingSequenceNumber == null ? 43 : $multipleClearingSequenceNumber.hashCode());
        String $multipleClearingSequenceCount = this.getMultipleClearingSequenceCount();
        result = result * 59 + ($multipleClearingSequenceCount == null ? 43 : $multipleClearingSequenceCount.hashCode());
        String $totalAuthorizedAmount = this.getTotalAuthorizedAmount();
        result = result * 59 + ($totalAuthorizedAmount == null ? 43 : $totalAuthorizedAmount.hashCode());
        String $informationIndicator = this.getInformationIndicator();
        result = result * 59 + ($informationIndicator == null ? 43 : $informationIndicator.hashCode());
        String $merchantTelephoneNumber = this.getMerchantTelephoneNumber();
        result = result * 59 + ($merchantTelephoneNumber == null ? 43 : $merchantTelephoneNumber.hashCode());
        String $additionalDataIndicator = this.getAdditionalDataIndicator();
        result = result * 59 + ($additionalDataIndicator == null ? 43 : $additionalDataIndicator.hashCode());
        String $merchantVolumeIndicator = this.getMerchantVolumeIndicator();
        result = result * 59 + ($merchantVolumeIndicator == null ? 43 : $merchantVolumeIndicator.hashCode());
        String $electronicCommerceGoodsIndicator = this.getElectronicCommerceGoodsIndicator();
        result = result * 59 + ($electronicCommerceGoodsIndicator == null ? 43 : $electronicCommerceGoodsIndicator.hashCode());
        String $merchantVerificationValue = this.getMerchantVerificationValue();
        result = result * 59 + ($merchantVerificationValue == null ? 43 : $merchantVerificationValue.hashCode());
        String $interchangeFeeAmount = this.getInterchangeFeeAmount();
        result = result * 59 + ($interchangeFeeAmount == null ? 43 : $interchangeFeeAmount.hashCode());
        String $interchangeFeeSign = this.getInterchangeFeeSign();
        result = result * 59 + ($interchangeFeeSign == null ? 43 : $interchangeFeeSign.hashCode());
        String $sourceCurrtoBaseCurrExcRate = this.getSourceCurrtoBaseCurrExcRate();
        result = result * 59 + ($sourceCurrtoBaseCurrExcRate == null ? 43 : $sourceCurrtoBaseCurrExcRate.hashCode());
        String $baseCurrtoDestinationCurrExcRate = this.getBaseCurrtoDestinationCurrExcRate();
        result = result * 59 + ($baseCurrtoDestinationCurrExcRate == null ? 43 : $baseCurrtoDestinationCurrExcRate.hashCode());
        String $optionalIssuerISAAmount = this.getOptionalIssuerISAAmount();
        result = result * 59 + ($optionalIssuerISAAmount == null ? 43 : $optionalIssuerISAAmount.hashCode());
        String $productID = this.getProductID();
        result = result * 59 + ($productID == null ? 43 : $productID.hashCode());
        String $programID = this.getProgramID();
        result = result * 59 + ($programID == null ? 43 : $programID.hashCode());
        String $dccIndicator = this.getDccIndicator();
        result = result * 59 + ($dccIndicator == null ? 43 : $dccIndicator.hashCode());
        String $accTypeIdentification = this.getAccTypeIdentification();
        result = result * 59 + ($accTypeIdentification == null ? 43 : $accTypeIdentification.hashCode());
        String $panToken = this.getPanToken();
        result = result * 59 + ($panToken == null ? 43 : $panToken.hashCode());
        String $reserved7 = this.getReserved7();
        result = result * 59 + ($reserved7 == null ? 43 : $reserved7.hashCode());
        String $accFundingSource = this.getAccFundingSource();
        result = result * 59 + ($accFundingSource == null ? 43 : $accFundingSource.hashCode());
        String $cvv2ResultCode = this.getCvv2ResultCode();
        result = result * 59 + ($cvv2ResultCode == null ? 43 : $cvv2ResultCode.hashCode());
        String $transactionType = this.getTransactionType();
        result = result * 59 + ($transactionType == null ? 43 : $transactionType.hashCode());
        String $cardSequenceNumber = this.getCardSequenceNumber();
        result = result * 59 + ($cardSequenceNumber == null ? 43 : $cardSequenceNumber.hashCode());
        String $terminalTranDate = this.getTerminalTranDate();
        result = result * 59 + ($terminalTranDate == null ? 43 : $terminalTranDate.hashCode());
        String $terminalCapabilityProfile = this.getTerminalCapabilityProfile();
        result = result * 59 + ($terminalCapabilityProfile == null ? 43 : $terminalCapabilityProfile.hashCode());
        String $terminalCountryCode = this.getTerminalCountryCode();
        result = result * 59 + ($terminalCountryCode == null ? 43 : $terminalCountryCode.hashCode());
        String $terminalSerialNumber = this.getTerminalSerialNumber();
        result = result * 59 + ($terminalSerialNumber == null ? 43 : $terminalSerialNumber.hashCode());
        String $unpredictableNumber = this.getUnpredictableNumber();
        result = result * 59 + ($unpredictableNumber == null ? 43 : $unpredictableNumber.hashCode());
        String $applicationTransactionCounter = this.getApplicationTransactionCounter();
        result = result * 59 + ($applicationTransactionCounter == null ? 43 : $applicationTransactionCounter.hashCode());
        String $applicationInterchangeProfile = this.getApplicationInterchangeProfile();
        result = result * 59 + ($applicationInterchangeProfile == null ? 43 : $applicationInterchangeProfile.hashCode());
        String $cryptogram = this.getCryptogram();
        result = result * 59 + ($cryptogram == null ? 43 : $cryptogram.hashCode());
        String $issuerAppDataByte2 = this.getIssuerAppDataByte2();
        result = result * 59 + ($issuerAppDataByte2 == null ? 43 : $issuerAppDataByte2.hashCode());
        String $issuerAppDataByte3 = this.getIssuerAppDataByte3();
        result = result * 59 + ($issuerAppDataByte3 == null ? 43 : $issuerAppDataByte3.hashCode());
        String $terminalVeriResults = this.getTerminalVeriResults();
        result = result * 59 + ($terminalVeriResults == null ? 43 : $terminalVeriResults.hashCode());
        String $issuerAppDataByte4to7 = this.getIssuerAppDataByte4to7();
        result = result * 59 + ($issuerAppDataByte4to7 == null ? 43 : $issuerAppDataByte4to7.hashCode());
        String $cryptogramAmount = this.getCryptogramAmount();
        result = result * 59 + ($cryptogramAmount == null ? 43 : $cryptogramAmount.hashCode());
        String $issuerAppDataByte8 = this.getIssuerAppDataByte8();
        result = result * 59 + ($issuerAppDataByte8 == null ? 43 : $issuerAppDataByte8.hashCode());
        String $issuerAppDataByte9to16 = this.getIssuerAppDataByte9to16();
        result = result * 59 + ($issuerAppDataByte9to16 == null ? 43 : $issuerAppDataByte9to16.hashCode());
        String $issuerAppDataByte1 = this.getIssuerAppDataByte1();
        result = result * 59 + ($issuerAppDataByte1 == null ? 43 : $issuerAppDataByte1.hashCode());
        String $issuerAppDataByte17 = this.getIssuerAppDataByte17();
        result = result * 59 + ($issuerAppDataByte17 == null ? 43 : $issuerAppDataByte17.hashCode());
        String $issuerAppDataByte18to32 = this.getIssuerAppDataByte18to32();
        result = result * 59 + ($issuerAppDataByte18to32 == null ? 43 : $issuerAppDataByte18to32.hashCode());
        String $formFactorIndicator = this.getFormFactorIndicator();
        result = result * 59 + ($formFactorIndicator == null ? 43 : $formFactorIndicator.hashCode());
        String $issuerScript1Results = this.getIssuerScript1Results();
        result = result * 59 + ($issuerScript1Results == null ? 43 : $issuerScript1Results.hashCode());
        String $transactionCode = this.getTransactionCode();
        result = result * 59 + ($transactionCode == null ? 43 : $transactionCode.hashCode());
        String $destinationIdentifier = this.getDestinationIdentifier();
        result = result * 59 + ($destinationIdentifier == null ? 43 : $destinationIdentifier.hashCode());
        String $sourceIdentifier = this.getSourceIdentifier();
        result = result * 59 + ($sourceIdentifier == null ? 43 : $sourceIdentifier.hashCode());
        String $reasonCode1 = this.getReasonCode1();
        result = result * 59 + ($reasonCode1 == null ? 43 : $reasonCode1.hashCode());
        String $countryCode = this.getCountryCode();
        result = result * 59 + ($countryCode == null ? 43 : $countryCode.hashCode());
        String $eventDate = this.getEventDate();
        result = result * 59 + ($eventDate == null ? 43 : $eventDate.hashCode());
        String $accountNumber1 = this.getAccountNumber1();
        result = result * 59 + ($accountNumber1 == null ? 43 : $accountNumber1.hashCode());
        String $accountNumberExtension1 = this.getAccountNumberExtension1();
        result = result * 59 + ($accountNumberExtension1 == null ? 43 : $accountNumberExtension1.hashCode());
        String $destinationAmount1 = this.getDestinationAmount1();
        result = result * 59 + ($destinationAmount1 == null ? 43 : $destinationAmount1.hashCode());
        String $destinationCurrencyCode1 = this.getDestinationCurrencyCode1();
        result = result * 59 + ($destinationCurrencyCode1 == null ? 43 : $destinationCurrencyCode1.hashCode());
        String $sourceAmount1 = this.getSourceAmount1();
        result = result * 59 + ($sourceAmount1 == null ? 43 : $sourceAmount1.hashCode());
        String $sourceCurrencyCode1 = this.getSourceCurrencyCode1();
        result = result * 59 + ($sourceCurrencyCode1 == null ? 43 : $sourceCurrencyCode1.hashCode());
        String $messageText = this.getMessageText();
        result = result * 59 + ($messageText == null ? 43 : $messageText.hashCode());
        String $settlementFlag1 = this.getSettlementFlag1();
        result = result * 59 + ($settlementFlag1 == null ? 43 : $settlementFlag1.hashCode());
        String $transactionIdentifier1 = this.getTransactionIdentifier1();
        result = result * 59 + ($transactionIdentifier1 == null ? 43 : $transactionIdentifier1.hashCode());
        String $reserved8 = this.getReserved8();
        result = result * 59 + ($reserved8 == null ? 43 : $reserved8.hashCode());
        String $centralProcessingDate1 = this.getCentralProcessingDate1();
        result = result * 59 + ($centralProcessingDate1 == null ? 43 : $centralProcessingDate1.hashCode());
        String $reimbursementAttribute1 = this.getReimbursementAttribute1();
        result = result * 59 + ($reimbursementAttribute1 == null ? 43 : $reimbursementAttribute1.hashCode());
        String $serviceProcessingType = this.getServiceProcessingType();
        result = result * 59 + ($serviceProcessingType == null ? 43 : $serviceProcessingType.hashCode());
        String $reserved9 = this.getReserved9();
        result = result * 59 + ($reserved9 == null ? 43 : $reserved9.hashCode());
        String $fastFundsIndicator = this.getFastFundsIndicator();
        result = result * 59 + ($fastFundsIndicator == null ? 43 : $fastFundsIndicator.hashCode());
        String $businessFormatCodeCR = this.getBusinessFormatCodeCR();
        result = result * 59 + ($businessFormatCodeCR == null ? 43 : $businessFormatCodeCR.hashCode());
        String $businessApplicationID = this.getBusinessApplicationID();
        result = result * 59 + ($businessApplicationID == null ? 43 : $businessApplicationID.hashCode());
        String $sourceofFunds = this.getSourceofFunds();
        result = result * 59 + ($sourceofFunds == null ? 43 : $sourceofFunds.hashCode());
        String $paymentReversalReasonCode = this.getPaymentReversalReasonCode();
        result = result * 59 + ($paymentReversalReasonCode == null ? 43 : $paymentReversalReasonCode.hashCode());
        String $senderReferenceNumber = this.getSenderReferenceNumber();
        result = result * 59 + ($senderReferenceNumber == null ? 43 : $senderReferenceNumber.hashCode());
        String $senderAccountNumber = this.getSenderAccountNumber();
        result = result * 59 + ($senderAccountNumber == null ? 43 : $senderAccountNumber.hashCode());
        String $senderName = this.getSenderName();
        result = result * 59 + ($senderName == null ? 43 : $senderName.hashCode());
        String $senderAddress = this.getSenderAddress();
        result = result * 59 + ($senderAddress == null ? 43 : $senderAddress.hashCode());
        String $senderCity = this.getSenderCity();
        result = result * 59 + ($senderCity == null ? 43 : $senderCity.hashCode());
        String $senderState = this.getSenderState();
        result = result * 59 + ($senderState == null ? 43 : $senderState.hashCode());
        String $senderCountry = this.getSenderCountry();
        result = result * 59 + ($senderCountry == null ? 43 : $senderCountry.hashCode());
        return result;
    }

    public String toString() {
        return "TCRZeroVo(txnCode=" + this.getTxnCode() + ", txnCodeQualifier=" + this.getTxnCodeQualifier() + ", txnComponentSeqNum=" + this.getTxnComponentSeqNum() + ", accountNumber=" + this.getAccountNumber() + ", accountNumberExtension=" + this.getAccountNumberExtension() + ", floorLimitIndicator=" + this.getFloorLimitIndicator() + ", crbExceptionFileIndicator=" + this.getCrbExceptionFileIndicator() + ", reserved=" + this.getReserved() + ", arn=" + this.getArn() + ", acquirerBusinessID=" + this.getAcquirerBusinessID() + ", purchaseDate=" + this.getPurchaseDate() + ", destinationAmount=" + this.getDestinationAmount() + ", destinationCurrencyCode=" + this.getDestinationCurrencyCode() + ", sourceAmount=" + this.getSourceAmount() + ", sourceCurrencyCode=" + this.getSourceCurrencyCode() + ", merchantName=" + this.getMerchantName() + ", merchantCity=" + this.getMerchantCity() + ", merchantCountryCode=" + this.getMerchantCountryCode() + ", merchantCategoryCode=" + this.getMerchantCategoryCode() + ", merchantZIPCode=" + this.getMerchantZIPCode() + ", merchantStateOrProvinceCode=" + this.getMerchantStateOrProvinceCode() + ", requestedPaymentService=" + this.getRequestedPaymentService() + ", numberofPaymentForms=" + this.getNumberofPaymentForms() + ", usageCode=" + this.getUsageCode() + ", reasonCode=" + this.getReasonCode() + ", settlementFlag=" + this.getSettlementFlag() + ", authorizationCharaIndicator=" + this.getAuthorizationCharaIndicator() + ", authorizationCode=" + this.getAuthorizationCode() + ", posTerminalCapability=" + this.getPosTerminalCapability() + ", reserved1=" + this.getReserved1() + ", cardholderIDMethod=" + this.getCardholderIDMethod() + ", collectionOnlyFlag=" + this.getCollectionOnlyFlag() + ", posEntryMode=" + this.getPosEntryMode() + ", centralProcessingDate=" + this.getCentralProcessingDate() + ", reimbursementAttribute=" + this.getReimbursementAttribute() + ", businessFormatCode=" + this.getBusinessFormatCode() + ", tokenAssuranceLevel=" + this.getTokenAssuranceLevel() + ", rateTableID=" + this.getRateTableID() + ", reserved2=" + this.getReserved2() + ", reserved3=" + this.getReserved3() + ", documentationIndicator=" + this.getDocumentationIndicator() + ", memberMessageText=" + this.getMemberMessageText() + ", specialConditionIndicators=" + this.getSpecialConditionIndicators() + ", feeProgramIndicator=" + this.getFeeProgramIndicator() + ", issuerCharge=" + this.getIssuerCharge() + ", persistentFXAppliedIndicator=" + this.getPersistentFXAppliedIndicator() + ", cardAcceptorID=" + this.getCardAcceptorID() + ", terminalID=" + this.getTerminalID() + ", nationalReimbursementFee=" + this.getNationalReimbursementFee() + ", ecomIndicator=" + this.getEcomIndicator() + ", specialChargebackIndicator=" + this.getSpecialChargebackIndicator() + ", conversionDate=" + this.getConversionDate() + ", reserved4=" + this.getReserved4() + ", acceptanceTerminalIndicator=" + this.getAcceptanceTerminalIndicator() + ", prepaidCardIndicator=" + this.getPrepaidCardIndicator() + ", serviceDevelopmentField=" + this.getServiceDevelopmentField() + ", avsResponseCode=" + this.getAvsResponseCode() + ", authorizationSourceCode=" + this.getAuthorizationSourceCode() + ", purchaseIdentifierFormat=" + this.getPurchaseIdentifierFormat() + ", accountSelection=" + this.getAccountSelection() + ", installmentPaymentCount=" + this.getInstallmentPaymentCount() + ", purchaseIdentifier=" + this.getPurchaseIdentifier() + ", cashback=" + this.getCashback() + ", chipConditionCode=" + this.getChipConditionCode() + ", posEnvironment=" + this.getPosEnvironment() + ", transactionIdentifier=" + this.getTransactionIdentifier() + ", authorizedAmount=" + this.getAuthorizedAmount() + ", authorizationCurrencyCode=" + this.getAuthorizationCurrencyCode() + ", authorizationResponseCode=" + this.getAuthorizationResponseCode() + ", validationCode=" + this.getValidationCode() + ", excludedTransactionIdentifierReason=" + this.getExcludedTransactionIdentifierReason() + ", reserved5=" + this.getReserved5() + ", reserved6=" + this.getReserved6() + ", multipleClearingSequenceNumber=" + this.getMultipleClearingSequenceNumber() + ", multipleClearingSequenceCount=" + this.getMultipleClearingSequenceCount() + ", marketSpecificAuthDataIndicator=" + this.getMarketSpecificAuthDataIndicator() + ", totalAuthorizedAmount=" + this.getTotalAuthorizedAmount() + ", informationIndicator=" + this.getInformationIndicator() + ", merchantTelephoneNumber=" + this.getMerchantTelephoneNumber() + ", additionalDataIndicator=" + this.getAdditionalDataIndicator() + ", merchantVolumeIndicator=" + this.getMerchantVolumeIndicator() + ", electronicCommerceGoodsIndicator=" + this.getElectronicCommerceGoodsIndicator() + ", merchantVerificationValue=" + this.getMerchantVerificationValue() + ", interchangeFeeAmount=" + this.getInterchangeFeeAmount() + ", interchangeFeeSign=" + this.getInterchangeFeeSign() + ", sourceCurrtoBaseCurrExcRate=" + this.getSourceCurrtoBaseCurrExcRate() + ", baseCurrtoDestinationCurrExcRate=" + this.getBaseCurrtoDestinationCurrExcRate() + ", optionalIssuerISAAmount=" + this.getOptionalIssuerISAAmount() + ", productID=" + this.getProductID() + ", programID=" + this.getProgramID() + ", dccIndicator=" + this.getDccIndicator() + ", accTypeIdentification=" + this.getAccTypeIdentification() + ", spendQualifiedIndicator=" + this.getSpendQualifiedIndicator() + ", panToken=" + this.getPanToken() + ", reserved7=" + this.getReserved7() + ", accFundingSource=" + this.getAccFundingSource() + ", cvv2ResultCode=" + this.getCvv2ResultCode() + ", transactionType=" + this.getTransactionType() + ", cardSequenceNumber=" + this.getCardSequenceNumber() + ", terminalTranDate=" + (this.getTerminalTranDate() + ", terminalCapabilityProfile=" + this.getTerminalCapabilityProfile() + ", terminalCountryCode=" + this.getTerminalCountryCode() + ", terminalSerialNumber=" + this.getTerminalSerialNumber() + ", unpredictableNumber=" + this.getUnpredictableNumber() + ", applicationTransactionCounter=" + this.getApplicationTransactionCounter() + ", applicationInterchangeProfile=" + this.getApplicationInterchangeProfile() + ", cryptogram=" + this.getCryptogram() + ", issuerAppDataByte2=" + this.getIssuerAppDataByte2() + ", issuerAppDataByte3=" + this.getIssuerAppDataByte3() + ", terminalVeriResults=" + this.getTerminalVeriResults() + ", issuerAppDataByte4to7=" + this.getIssuerAppDataByte4to7() + ", cryptogramAmount=" + this.getCryptogramAmount() + ", issuerAppDataByte8=" + this.getIssuerAppDataByte8() + ", issuerAppDataByte9to16=" + this.getIssuerAppDataByte9to16() + ", issuerAppDataByte1=" + this.getIssuerAppDataByte1() + ", issuerAppDataByte17=" + this.getIssuerAppDataByte17() + ", issuerAppDataByte18to32=" + this.getIssuerAppDataByte18to32() + ", formFactorIndicator=" + this.getFormFactorIndicator() + ", issuerScript1Results=" + this.getIssuerScript1Results() + ", transactionCode=" + this.getTransactionCode() + ", destinationIdentifier=" + this.getDestinationIdentifier() + ", sourceIdentifier=" + this.getSourceIdentifier() + ", reasonCode1=" + this.getReasonCode1() + ", countryCode=" + this.getCountryCode() + ", eventDate=" + this.getEventDate() + ", accountNumber1=" + this.getAccountNumber1() + ", accountNumberExtension1=" + this.getAccountNumberExtension1() + ", destinationAmount1=" + this.getDestinationAmount1() + ", destinationCurrencyCode1=" + this.getDestinationCurrencyCode1() + ", sourceAmount1=" + this.getSourceAmount1() + ", sourceCurrencyCode1=" + this.getSourceCurrencyCode1() + ", messageText=" + this.getMessageText() + ", settlementFlag1=" + this.getSettlementFlag1() + ", transactionIdentifier1=" + this.getTransactionIdentifier1() + ", reserved8=" + this.getReserved8() + ", centralProcessingDate1=" + this.getCentralProcessingDate1() + ", reimbursementAttribute1=" + this.getReimbursementAttribute1() + ", serviceProcessingType=" + this.getServiceProcessingType() + ", reserved9=" + this.getReserved9() + ", fastFundsIndicator=" + this.getFastFundsIndicator() + ", businessFormatCodeCR=" + this.getBusinessFormatCodeCR() + ", businessApplicationID=" + this.getBusinessApplicationID() + ", sourceofFunds=" + this.getSourceofFunds() + ", paymentReversalReasonCode=" + this.getPaymentReversalReasonCode() + ", senderReferenceNumber=" + this.getSenderReferenceNumber() + ", senderAccountNumber=" + this.getSenderAccountNumber() + ", senderName=" + this.getSenderName() + ", senderAddress=" + this.getSenderAddress() + ", senderCity=" + this.getSenderCity() + ", senderState=" + this.getSenderState() + ", senderCountry=" + this.getSenderCountry() + ")");
    }
}

