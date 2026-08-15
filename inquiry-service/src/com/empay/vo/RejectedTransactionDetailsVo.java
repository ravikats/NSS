/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.RejectedTransactionDetailsVo
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 */
package com.empay.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class RejectedTransactionDetailsVo {
    private String transactionType;
    private String mti;
    private String cardNumber;
    private String processingCode;
    private String transactionAmount;
    private String surchargeAmount;
    private String localDateTime;
    private String expiryDate;
    private String posDataCode;
    private String functionCode;
    private String messageReasonCode;
    private String mcc;
    private String arn;
    private String acquireInstitutionId;
    private String rrn;
    private String approvalCode;
    private String responseCode;
    private String seviceCode;
    private String terminalId;
    private String merchantId;
    private String merchantName;
    private String merchantCity;
    private String merchantZipCode;
    private String merchantCountry;
    private String terminalType;
    private String ecomIndicator;
    private String transactionFeeAmount;
    private String transactionCurrencyExponent;
    private String transactionCurrencyCode;
    private String ird;
    private String settledIndicator;
    private String cardSequenceNumber;
    private String applicationCryptogram;
    private String cryptogramInformationData;
    private String issuerApplicationData;
    private String upblNumber;
    private String applicationTransactionCounter;
    private String terminalVerificationResult;
    private String transactionDate;
    private String chipTransactionDate;
    private String chipTransactionType;
    private String cryptAmount;
    private String applicationInterchangeProfile;
    private String terminalCountryCode;
    private String cashbackAmount;
    private String cvmResult;
    private String terminalCapabilities;
    private String tcc;
    private String chipCurrencyCode;
    private String chipTerminalType;
    private String terminalApplicationVerificationNumber;
    private String transactionSequenceCounter;
    private String issuerAuthData;
    private String transactionLifeCycleId;
    private String messageNumber;
    private String memberText;
    private String independentSalesOrganizationId;
    private String reversalIndicator;
    private String mastercardAssignedId;
    private String cardType;
    private String domesticInternationFlag;
    private String smsDmsFlag;
    private String posPgType;
    private String centralProcessingDate;
    private String outgoingFileDate;
    private String fileId;
    private String encryptedCardNumber;
    private String meCountryOfOrigin;
    public Integer totalCount;
    public Integer totalPage;

    public String getTransactionType() {
        return this.transactionType;
    }

    public String getMti() {
        return this.mti;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcessingCode() {
        return this.processingCode;
    }

    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    public String getSurchargeAmount() {
        return this.surchargeAmount;
    }

    public String getLocalDateTime() {
        return this.localDateTime;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getPosDataCode() {
        return this.posDataCode;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getMessageReasonCode() {
        return this.messageReasonCode;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getArn() {
        return this.arn;
    }

    public String getAcquireInstitutionId() {
        return this.acquireInstitutionId;
    }

    public String getRrn() {
        return this.rrn;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getSeviceCode() {
        return this.seviceCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getMerchantCity() {
        return this.merchantCity;
    }

    public String getMerchantZipCode() {
        return this.merchantZipCode;
    }

    public String getMerchantCountry() {
        return this.merchantCountry;
    }

    public String getTerminalType() {
        return this.terminalType;
    }

    public String getEcomIndicator() {
        return this.ecomIndicator;
    }

    public String getTransactionFeeAmount() {
        return this.transactionFeeAmount;
    }

    public String getTransactionCurrencyExponent() {
        return this.transactionCurrencyExponent;
    }

    public String getTransactionCurrencyCode() {
        return this.transactionCurrencyCode;
    }

    public String getIrd() {
        return this.ird;
    }

    public String getSettledIndicator() {
        return this.settledIndicator;
    }

    public String getCardSequenceNumber() {
        return this.cardSequenceNumber;
    }

    public String getApplicationCryptogram() {
        return this.applicationCryptogram;
    }

    public String getCryptogramInformationData() {
        return this.cryptogramInformationData;
    }

    public String getIssuerApplicationData() {
        return this.issuerApplicationData;
    }

    public String getUpblNumber() {
        return this.upblNumber;
    }

    public String getApplicationTransactionCounter() {
        return this.applicationTransactionCounter;
    }

    public String getTerminalVerificationResult() {
        return this.terminalVerificationResult;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public String getChipTransactionDate() {
        return this.chipTransactionDate;
    }

    public String getChipTransactionType() {
        return this.chipTransactionType;
    }

    public String getCryptAmount() {
        return this.cryptAmount;
    }

    public String getApplicationInterchangeProfile() {
        return this.applicationInterchangeProfile;
    }

    public String getTerminalCountryCode() {
        return this.terminalCountryCode;
    }

    public String getCashbackAmount() {
        return this.cashbackAmount;
    }

    public String getCvmResult() {
        return this.cvmResult;
    }

    public String getTerminalCapabilities() {
        return this.terminalCapabilities;
    }

    public String getTcc() {
        return this.tcc;
    }

    public String getChipCurrencyCode() {
        return this.chipCurrencyCode;
    }

    public String getChipTerminalType() {
        return this.chipTerminalType;
    }

    public String getTerminalApplicationVerificationNumber() {
        return this.terminalApplicationVerificationNumber;
    }

    public String getTransactionSequenceCounter() {
        return this.transactionSequenceCounter;
    }

    public String getIssuerAuthData() {
        return this.issuerAuthData;
    }

    public String getTransactionLifeCycleId() {
        return this.transactionLifeCycleId;
    }

    public String getMessageNumber() {
        return this.messageNumber;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public String getIndependentSalesOrganizationId() {
        return this.independentSalesOrganizationId;
    }

    public String getReversalIndicator() {
        return this.reversalIndicator;
    }

    public String getMastercardAssignedId() {
        return this.mastercardAssignedId;
    }

    public String getCardType() {
        return this.cardType;
    }

    public String getDomesticInternationFlag() {
        return this.domesticInternationFlag;
    }

    public String getSmsDmsFlag() {
        return this.smsDmsFlag;
    }

    public String getPosPgType() {
        return this.posPgType;
    }

    public String getCentralProcessingDate() {
        return this.centralProcessingDate;
    }

    public String getOutgoingFileDate() {
        return this.outgoingFileDate;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getEncryptedCardNumber() {
        return this.encryptedCardNumber;
    }

    public String getMeCountryOfOrigin() {
        return this.meCountryOfOrigin;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setSurchargeAmount(String surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setMessageReasonCode(String messageReasonCode) {
        this.messageReasonCode = messageReasonCode;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public void setAcquireInstitutionId(String acquireInstitutionId) {
        this.acquireInstitutionId = acquireInstitutionId;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setSeviceCode(String seviceCode) {
        this.seviceCode = seviceCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public void setMerchantZipCode(String merchantZipCode) {
        this.merchantZipCode = merchantZipCode;
    }

    public void setMerchantCountry(String merchantCountry) {
        this.merchantCountry = merchantCountry;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public void setEcomIndicator(String ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }

    public void setTransactionFeeAmount(String transactionFeeAmount) {
        this.transactionFeeAmount = transactionFeeAmount;
    }

    public void setTransactionCurrencyExponent(String transactionCurrencyExponent) {
        this.transactionCurrencyExponent = transactionCurrencyExponent;
    }

    public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        this.transactionCurrencyCode = transactionCurrencyCode;
    }

    public void setIrd(String ird) {
        this.ird = ird;
    }

    public void setSettledIndicator(String settledIndicator) {
        this.settledIndicator = settledIndicator;
    }

    public void setCardSequenceNumber(String cardSequenceNumber) {
        this.cardSequenceNumber = cardSequenceNumber;
    }

    public void setApplicationCryptogram(String applicationCryptogram) {
        this.applicationCryptogram = applicationCryptogram;
    }

    public void setCryptogramInformationData(String cryptogramInformationData) {
        this.cryptogramInformationData = cryptogramInformationData;
    }

    public void setIssuerApplicationData(String issuerApplicationData) {
        this.issuerApplicationData = issuerApplicationData;
    }

    public void setUpblNumber(String upblNumber) {
        this.upblNumber = upblNumber;
    }

    public void setApplicationTransactionCounter(String applicationTransactionCounter) {
        this.applicationTransactionCounter = applicationTransactionCounter;
    }

    public void setTerminalVerificationResult(String terminalVerificationResult) {
        this.terminalVerificationResult = terminalVerificationResult;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setChipTransactionDate(String chipTransactionDate) {
        this.chipTransactionDate = chipTransactionDate;
    }

    public void setChipTransactionType(String chipTransactionType) {
        this.chipTransactionType = chipTransactionType;
    }

    public void setCryptAmount(String cryptAmount) {
        this.cryptAmount = cryptAmount;
    }

    public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
        this.applicationInterchangeProfile = applicationInterchangeProfile;
    }

    public void setTerminalCountryCode(String terminalCountryCode) {
        this.terminalCountryCode = terminalCountryCode;
    }

    public void setCashbackAmount(String cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public void setCvmResult(String cvmResult) {
        this.cvmResult = cvmResult;
    }

    public void setTerminalCapabilities(String terminalCapabilities) {
        this.terminalCapabilities = terminalCapabilities;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }

    public void setChipCurrencyCode(String chipCurrencyCode) {
        this.chipCurrencyCode = chipCurrencyCode;
    }

    public void setChipTerminalType(String chipTerminalType) {
        this.chipTerminalType = chipTerminalType;
    }

    public void setTerminalApplicationVerificationNumber(String terminalApplicationVerificationNumber) {
        this.terminalApplicationVerificationNumber = terminalApplicationVerificationNumber;
    }

    public void setTransactionSequenceCounter(String transactionSequenceCounter) {
        this.transactionSequenceCounter = transactionSequenceCounter;
    }

    public void setIssuerAuthData(String issuerAuthData) {
        this.issuerAuthData = issuerAuthData;
    }

    public void setTransactionLifeCycleId(String transactionLifeCycleId) {
        this.transactionLifeCycleId = transactionLifeCycleId;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public void setIndependentSalesOrganizationId(String independentSalesOrganizationId) {
        this.independentSalesOrganizationId = independentSalesOrganizationId;
    }

    public void setReversalIndicator(String reversalIndicator) {
        this.reversalIndicator = reversalIndicator;
    }

    public void setMastercardAssignedId(String mastercardAssignedId) {
        this.mastercardAssignedId = mastercardAssignedId;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setDomesticInternationFlag(String domesticInternationFlag) {
        this.domesticInternationFlag = domesticInternationFlag;
    }

    public void setSmsDmsFlag(String smsDmsFlag) {
        this.smsDmsFlag = smsDmsFlag;
    }

    public void setPosPgType(String posPgType) {
        this.posPgType = posPgType;
    }

    public void setCentralProcessingDate(String centralProcessingDate) {
        this.centralProcessingDate = centralProcessingDate;
    }

    public void setOutgoingFileDate(String outgoingFileDate) {
        this.outgoingFileDate = outgoingFileDate;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setMeCountryOfOrigin(String meCountryOfOrigin) {
        this.meCountryOfOrigin = meCountryOfOrigin;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RejectedTransactionDetailsVo)) {
            return false;
        }
        RejectedTransactionDetailsVo other = (RejectedTransactionDetailsVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$totalCount = this.getTotalCount();
        Integer other$totalCount = other.getTotalCount();
        if (this$totalCount == null ? other$totalCount != null : !((Object)this$totalCount).equals(other$totalCount)) {
            return false;
        }
        Integer this$totalPage = this.getTotalPage();
        Integer other$totalPage = other.getTotalPage();
        if (this$totalPage == null ? other$totalPage != null : !((Object)this$totalPage).equals(other$totalPage)) {
            return false;
        }
        String this$transactionType = this.getTransactionType();
        String other$transactionType = other.getTransactionType();
        if (this$transactionType == null ? other$transactionType != null : !this$transactionType.equals(other$transactionType)) {
            return false;
        }
        String this$mti = this.getMti();
        String other$mti = other.getMti();
        if (this$mti == null ? other$mti != null : !this$mti.equals(other$mti)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$processingCode = this.getProcessingCode();
        String other$processingCode = other.getProcessingCode();
        if (this$processingCode == null ? other$processingCode != null : !this$processingCode.equals(other$processingCode)) {
            return false;
        }
        String this$transactionAmount = this.getTransactionAmount();
        String other$transactionAmount = other.getTransactionAmount();
        if (this$transactionAmount == null ? other$transactionAmount != null : !this$transactionAmount.equals(other$transactionAmount)) {
            return false;
        }
        String this$surchargeAmount = this.getSurchargeAmount();
        String other$surchargeAmount = other.getSurchargeAmount();
        if (this$surchargeAmount == null ? other$surchargeAmount != null : !this$surchargeAmount.equals(other$surchargeAmount)) {
            return false;
        }
        String this$localDateTime = this.getLocalDateTime();
        String other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !this$localDateTime.equals(other$localDateTime)) {
            return false;
        }
        String this$expiryDate = this.getExpiryDate();
        String other$expiryDate = other.getExpiryDate();
        if (this$expiryDate == null ? other$expiryDate != null : !this$expiryDate.equals(other$expiryDate)) {
            return false;
        }
        String this$posDataCode = this.getPosDataCode();
        String other$posDataCode = other.getPosDataCode();
        if (this$posDataCode == null ? other$posDataCode != null : !this$posDataCode.equals(other$posDataCode)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        String this$messageReasonCode = this.getMessageReasonCode();
        String other$messageReasonCode = other.getMessageReasonCode();
        if (this$messageReasonCode == null ? other$messageReasonCode != null : !this$messageReasonCode.equals(other$messageReasonCode)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$arn = this.getArn();
        String other$arn = other.getArn();
        if (this$arn == null ? other$arn != null : !this$arn.equals(other$arn)) {
            return false;
        }
        String this$acquireInstitutionId = this.getAcquireInstitutionId();
        String other$acquireInstitutionId = other.getAcquireInstitutionId();
        if (this$acquireInstitutionId == null ? other$acquireInstitutionId != null : !this$acquireInstitutionId.equals(other$acquireInstitutionId)) {
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
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$seviceCode = this.getSeviceCode();
        String other$seviceCode = other.getSeviceCode();
        if (this$seviceCode == null ? other$seviceCode != null : !this$seviceCode.equals(other$seviceCode)) {
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
        String this$merchantZipCode = this.getMerchantZipCode();
        String other$merchantZipCode = other.getMerchantZipCode();
        if (this$merchantZipCode == null ? other$merchantZipCode != null : !this$merchantZipCode.equals(other$merchantZipCode)) {
            return false;
        }
        String this$merchantCountry = this.getMerchantCountry();
        String other$merchantCountry = other.getMerchantCountry();
        if (this$merchantCountry == null ? other$merchantCountry != null : !this$merchantCountry.equals(other$merchantCountry)) {
            return false;
        }
        String this$terminalType = this.getTerminalType();
        String other$terminalType = other.getTerminalType();
        if (this$terminalType == null ? other$terminalType != null : !this$terminalType.equals(other$terminalType)) {
            return false;
        }
        String this$ecomIndicator = this.getEcomIndicator();
        String other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null ? other$ecomIndicator != null : !this$ecomIndicator.equals(other$ecomIndicator)) {
            return false;
        }
        String this$transactionFeeAmount = this.getTransactionFeeAmount();
        String other$transactionFeeAmount = other.getTransactionFeeAmount();
        if (this$transactionFeeAmount == null ? other$transactionFeeAmount != null : !this$transactionFeeAmount.equals(other$transactionFeeAmount)) {
            return false;
        }
        String this$transactionCurrencyExponent = this.getTransactionCurrencyExponent();
        String other$transactionCurrencyExponent = other.getTransactionCurrencyExponent();
        if (this$transactionCurrencyExponent == null ? other$transactionCurrencyExponent != null : !this$transactionCurrencyExponent.equals(other$transactionCurrencyExponent)) {
            return false;
        }
        String this$transactionCurrencyCode = this.getTransactionCurrencyCode();
        String other$transactionCurrencyCode = other.getTransactionCurrencyCode();
        if (this$transactionCurrencyCode == null ? other$transactionCurrencyCode != null : !this$transactionCurrencyCode.equals(other$transactionCurrencyCode)) {
            return false;
        }
        String this$ird = this.getIrd();
        String other$ird = other.getIrd();
        if (this$ird == null ? other$ird != null : !this$ird.equals(other$ird)) {
            return false;
        }
        String this$settledIndicator = this.getSettledIndicator();
        String other$settledIndicator = other.getSettledIndicator();
        if (this$settledIndicator == null ? other$settledIndicator != null : !this$settledIndicator.equals(other$settledIndicator)) {
            return false;
        }
        String this$cardSequenceNumber = this.getCardSequenceNumber();
        String other$cardSequenceNumber = other.getCardSequenceNumber();
        if (this$cardSequenceNumber == null ? other$cardSequenceNumber != null : !this$cardSequenceNumber.equals(other$cardSequenceNumber)) {
            return false;
        }
        String this$applicationCryptogram = this.getApplicationCryptogram();
        String other$applicationCryptogram = other.getApplicationCryptogram();
        if (this$applicationCryptogram == null ? other$applicationCryptogram != null : !this$applicationCryptogram.equals(other$applicationCryptogram)) {
            return false;
        }
        String this$cryptogramInformationData = this.getCryptogramInformationData();
        String other$cryptogramInformationData = other.getCryptogramInformationData();
        if (this$cryptogramInformationData == null ? other$cryptogramInformationData != null : !this$cryptogramInformationData.equals(other$cryptogramInformationData)) {
            return false;
        }
        String this$issuerApplicationData = this.getIssuerApplicationData();
        String other$issuerApplicationData = other.getIssuerApplicationData();
        if (this$issuerApplicationData == null ? other$issuerApplicationData != null : !this$issuerApplicationData.equals(other$issuerApplicationData)) {
            return false;
        }
        String this$upblNumber = this.getUpblNumber();
        String other$upblNumber = other.getUpblNumber();
        if (this$upblNumber == null ? other$upblNumber != null : !this$upblNumber.equals(other$upblNumber)) {
            return false;
        }
        String this$applicationTransactionCounter = this.getApplicationTransactionCounter();
        String other$applicationTransactionCounter = other.getApplicationTransactionCounter();
        if (this$applicationTransactionCounter == null ? other$applicationTransactionCounter != null : !this$applicationTransactionCounter.equals(other$applicationTransactionCounter)) {
            return false;
        }
        String this$terminalVerificationResult = this.getTerminalVerificationResult();
        String other$terminalVerificationResult = other.getTerminalVerificationResult();
        if (this$terminalVerificationResult == null ? other$terminalVerificationResult != null : !this$terminalVerificationResult.equals(other$terminalVerificationResult)) {
            return false;
        }
        String this$transactionDate = this.getTransactionDate();
        String other$transactionDate = other.getTransactionDate();
        if (this$transactionDate == null ? other$transactionDate != null : !this$transactionDate.equals(other$transactionDate)) {
            return false;
        }
        String this$chipTransactionDate = this.getChipTransactionDate();
        String other$chipTransactionDate = other.getChipTransactionDate();
        if (this$chipTransactionDate == null ? other$chipTransactionDate != null : !this$chipTransactionDate.equals(other$chipTransactionDate)) {
            return false;
        }
        String this$chipTransactionType = this.getChipTransactionType();
        String other$chipTransactionType = other.getChipTransactionType();
        if (this$chipTransactionType == null ? other$chipTransactionType != null : !this$chipTransactionType.equals(other$chipTransactionType)) {
            return false;
        }
        String this$cryptAmount = this.getCryptAmount();
        String other$cryptAmount = other.getCryptAmount();
        if (this$cryptAmount == null ? other$cryptAmount != null : !this$cryptAmount.equals(other$cryptAmount)) {
            return false;
        }
        String this$applicationInterchangeProfile = this.getApplicationInterchangeProfile();
        String other$applicationInterchangeProfile = other.getApplicationInterchangeProfile();
        if (this$applicationInterchangeProfile == null ? other$applicationInterchangeProfile != null : !this$applicationInterchangeProfile.equals(other$applicationInterchangeProfile)) {
            return false;
        }
        String this$terminalCountryCode = this.getTerminalCountryCode();
        String other$terminalCountryCode = other.getTerminalCountryCode();
        if (this$terminalCountryCode == null ? other$terminalCountryCode != null : !this$terminalCountryCode.equals(other$terminalCountryCode)) {
            return false;
        }
        String this$cashbackAmount = this.getCashbackAmount();
        String other$cashbackAmount = other.getCashbackAmount();
        if (this$cashbackAmount == null ? other$cashbackAmount != null : !this$cashbackAmount.equals(other$cashbackAmount)) {
            return false;
        }
        String this$cvmResult = this.getCvmResult();
        String other$cvmResult = other.getCvmResult();
        if (this$cvmResult == null ? other$cvmResult != null : !this$cvmResult.equals(other$cvmResult)) {
            return false;
        }
        String this$terminalCapabilities = this.getTerminalCapabilities();
        String other$terminalCapabilities = other.getTerminalCapabilities();
        if (this$terminalCapabilities == null ? other$terminalCapabilities != null : !this$terminalCapabilities.equals(other$terminalCapabilities)) {
            return false;
        }
        String this$tcc = this.getTcc();
        String other$tcc = other.getTcc();
        if (this$tcc == null ? other$tcc != null : !this$tcc.equals(other$tcc)) {
            return false;
        }
        String this$chipCurrencyCode = this.getChipCurrencyCode();
        String other$chipCurrencyCode = other.getChipCurrencyCode();
        if (this$chipCurrencyCode == null ? other$chipCurrencyCode != null : !this$chipCurrencyCode.equals(other$chipCurrencyCode)) {
            return false;
        }
        String this$chipTerminalType = this.getChipTerminalType();
        String other$chipTerminalType = other.getChipTerminalType();
        if (this$chipTerminalType == null ? other$chipTerminalType != null : !this$chipTerminalType.equals(other$chipTerminalType)) {
            return false;
        }
        String this$terminalApplicationVerificationNumber = this.getTerminalApplicationVerificationNumber();
        String other$terminalApplicationVerificationNumber = other.getTerminalApplicationVerificationNumber();
        if (this$terminalApplicationVerificationNumber == null ? other$terminalApplicationVerificationNumber != null : !this$terminalApplicationVerificationNumber.equals(other$terminalApplicationVerificationNumber)) {
            return false;
        }
        String this$transactionSequenceCounter = this.getTransactionSequenceCounter();
        String other$transactionSequenceCounter = other.getTransactionSequenceCounter();
        if (this$transactionSequenceCounter == null ? other$transactionSequenceCounter != null : !this$transactionSequenceCounter.equals(other$transactionSequenceCounter)) {
            return false;
        }
        String this$issuerAuthData = this.getIssuerAuthData();
        String other$issuerAuthData = other.getIssuerAuthData();
        if (this$issuerAuthData == null ? other$issuerAuthData != null : !this$issuerAuthData.equals(other$issuerAuthData)) {
            return false;
        }
        String this$transactionLifeCycleId = this.getTransactionLifeCycleId();
        String other$transactionLifeCycleId = other.getTransactionLifeCycleId();
        if (this$transactionLifeCycleId == null ? other$transactionLifeCycleId != null : !this$transactionLifeCycleId.equals(other$transactionLifeCycleId)) {
            return false;
        }
        String this$messageNumber = this.getMessageNumber();
        String other$messageNumber = other.getMessageNumber();
        if (this$messageNumber == null ? other$messageNumber != null : !this$messageNumber.equals(other$messageNumber)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        if (this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText)) {
            return false;
        }
        String this$independentSalesOrganizationId = this.getIndependentSalesOrganizationId();
        String other$independentSalesOrganizationId = other.getIndependentSalesOrganizationId();
        if (this$independentSalesOrganizationId == null ? other$independentSalesOrganizationId != null : !this$independentSalesOrganizationId.equals(other$independentSalesOrganizationId)) {
            return false;
        }
        String this$reversalIndicator = this.getReversalIndicator();
        String other$reversalIndicator = other.getReversalIndicator();
        if (this$reversalIndicator == null ? other$reversalIndicator != null : !this$reversalIndicator.equals(other$reversalIndicator)) {
            return false;
        }
        String this$mastercardAssignedId = this.getMastercardAssignedId();
        String other$mastercardAssignedId = other.getMastercardAssignedId();
        if (this$mastercardAssignedId == null ? other$mastercardAssignedId != null : !this$mastercardAssignedId.equals(other$mastercardAssignedId)) {
            return false;
        }
        String this$cardType = this.getCardType();
        String other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !this$cardType.equals(other$cardType)) {
            return false;
        }
        String this$domesticInternationFlag = this.getDomesticInternationFlag();
        String other$domesticInternationFlag = other.getDomesticInternationFlag();
        if (this$domesticInternationFlag == null ? other$domesticInternationFlag != null : !this$domesticInternationFlag.equals(other$domesticInternationFlag)) {
            return false;
        }
        String this$smsDmsFlag = this.getSmsDmsFlag();
        String other$smsDmsFlag = other.getSmsDmsFlag();
        if (this$smsDmsFlag == null ? other$smsDmsFlag != null : !this$smsDmsFlag.equals(other$smsDmsFlag)) {
            return false;
        }
        String this$posPgType = this.getPosPgType();
        String other$posPgType = other.getPosPgType();
        if (this$posPgType == null ? other$posPgType != null : !this$posPgType.equals(other$posPgType)) {
            return false;
        }
        String this$centralProcessingDate = this.getCentralProcessingDate();
        String other$centralProcessingDate = other.getCentralProcessingDate();
        if (this$centralProcessingDate == null ? other$centralProcessingDate != null : !this$centralProcessingDate.equals(other$centralProcessingDate)) {
            return false;
        }
        String this$outgoingFileDate = this.getOutgoingFileDate();
        String other$outgoingFileDate = other.getOutgoingFileDate();
        if (this$outgoingFileDate == null ? other$outgoingFileDate != null : !this$outgoingFileDate.equals(other$outgoingFileDate)) {
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
        String this$meCountryOfOrigin = this.getMeCountryOfOrigin();
        String other$meCountryOfOrigin = other.getMeCountryOfOrigin();
        return !(this$meCountryOfOrigin == null ? other$meCountryOfOrigin != null : !this$meCountryOfOrigin.equals(other$meCountryOfOrigin));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RejectedTransactionDetailsVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $totalCount = this.getTotalCount();
        result = result * 59 + ($totalCount == null ? 43 : ((Object)$totalCount).hashCode());
        Integer $totalPage = this.getTotalPage();
        result = result * 59 + ($totalPage == null ? 43 : ((Object)$totalPage).hashCode());
        String $transactionType = this.getTransactionType();
        result = result * 59 + ($transactionType == null ? 43 : $transactionType.hashCode());
        String $mti = this.getMti();
        result = result * 59 + ($mti == null ? 43 : $mti.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $processingCode = this.getProcessingCode();
        result = result * 59 + ($processingCode == null ? 43 : $processingCode.hashCode());
        String $transactionAmount = this.getTransactionAmount();
        result = result * 59 + ($transactionAmount == null ? 43 : $transactionAmount.hashCode());
        String $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + ($surchargeAmount == null ? 43 : $surchargeAmount.hashCode());
        String $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : $localDateTime.hashCode());
        String $expiryDate = this.getExpiryDate();
        result = result * 59 + ($expiryDate == null ? 43 : $expiryDate.hashCode());
        String $posDataCode = this.getPosDataCode();
        result = result * 59 + ($posDataCode == null ? 43 : $posDataCode.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        String $messageReasonCode = this.getMessageReasonCode();
        result = result * 59 + ($messageReasonCode == null ? 43 : $messageReasonCode.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $arn = this.getArn();
        result = result * 59 + ($arn == null ? 43 : $arn.hashCode());
        String $acquireInstitutionId = this.getAcquireInstitutionId();
        result = result * 59 + ($acquireInstitutionId == null ? 43 : $acquireInstitutionId.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $seviceCode = this.getSeviceCode();
        result = result * 59 + ($seviceCode == null ? 43 : $seviceCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $merchantName = this.getMerchantName();
        result = result * 59 + ($merchantName == null ? 43 : $merchantName.hashCode());
        String $merchantCity = this.getMerchantCity();
        result = result * 59 + ($merchantCity == null ? 43 : $merchantCity.hashCode());
        String $merchantZipCode = this.getMerchantZipCode();
        result = result * 59 + ($merchantZipCode == null ? 43 : $merchantZipCode.hashCode());
        String $merchantCountry = this.getMerchantCountry();
        result = result * 59 + ($merchantCountry == null ? 43 : $merchantCountry.hashCode());
        String $terminalType = this.getTerminalType();
        result = result * 59 + ($terminalType == null ? 43 : $terminalType.hashCode());
        String $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + ($ecomIndicator == null ? 43 : $ecomIndicator.hashCode());
        String $transactionFeeAmount = this.getTransactionFeeAmount();
        result = result * 59 + ($transactionFeeAmount == null ? 43 : $transactionFeeAmount.hashCode());
        String $transactionCurrencyExponent = this.getTransactionCurrencyExponent();
        result = result * 59 + ($transactionCurrencyExponent == null ? 43 : $transactionCurrencyExponent.hashCode());
        String $transactionCurrencyCode = this.getTransactionCurrencyCode();
        result = result * 59 + ($transactionCurrencyCode == null ? 43 : $transactionCurrencyCode.hashCode());
        String $ird = this.getIrd();
        result = result * 59 + ($ird == null ? 43 : $ird.hashCode());
        String $settledIndicator = this.getSettledIndicator();
        result = result * 59 + ($settledIndicator == null ? 43 : $settledIndicator.hashCode());
        String $cardSequenceNumber = this.getCardSequenceNumber();
        result = result * 59 + ($cardSequenceNumber == null ? 43 : $cardSequenceNumber.hashCode());
        String $applicationCryptogram = this.getApplicationCryptogram();
        result = result * 59 + ($applicationCryptogram == null ? 43 : $applicationCryptogram.hashCode());
        String $cryptogramInformationData = this.getCryptogramInformationData();
        result = result * 59 + ($cryptogramInformationData == null ? 43 : $cryptogramInformationData.hashCode());
        String $issuerApplicationData = this.getIssuerApplicationData();
        result = result * 59 + ($issuerApplicationData == null ? 43 : $issuerApplicationData.hashCode());
        String $upblNumber = this.getUpblNumber();
        result = result * 59 + ($upblNumber == null ? 43 : $upblNumber.hashCode());
        String $applicationTransactionCounter = this.getApplicationTransactionCounter();
        result = result * 59 + ($applicationTransactionCounter == null ? 43 : $applicationTransactionCounter.hashCode());
        String $terminalVerificationResult = this.getTerminalVerificationResult();
        result = result * 59 + ($terminalVerificationResult == null ? 43 : $terminalVerificationResult.hashCode());
        String $transactionDate = this.getTransactionDate();
        result = result * 59 + ($transactionDate == null ? 43 : $transactionDate.hashCode());
        String $chipTransactionDate = this.getChipTransactionDate();
        result = result * 59 + ($chipTransactionDate == null ? 43 : $chipTransactionDate.hashCode());
        String $chipTransactionType = this.getChipTransactionType();
        result = result * 59 + ($chipTransactionType == null ? 43 : $chipTransactionType.hashCode());
        String $cryptAmount = this.getCryptAmount();
        result = result * 59 + ($cryptAmount == null ? 43 : $cryptAmount.hashCode());
        String $applicationInterchangeProfile = this.getApplicationInterchangeProfile();
        result = result * 59 + ($applicationInterchangeProfile == null ? 43 : $applicationInterchangeProfile.hashCode());
        String $terminalCountryCode = this.getTerminalCountryCode();
        result = result * 59 + ($terminalCountryCode == null ? 43 : $terminalCountryCode.hashCode());
        String $cashbackAmount = this.getCashbackAmount();
        result = result * 59 + ($cashbackAmount == null ? 43 : $cashbackAmount.hashCode());
        String $cvmResult = this.getCvmResult();
        result = result * 59 + ($cvmResult == null ? 43 : $cvmResult.hashCode());
        String $terminalCapabilities = this.getTerminalCapabilities();
        result = result * 59 + ($terminalCapabilities == null ? 43 : $terminalCapabilities.hashCode());
        String $tcc = this.getTcc();
        result = result * 59 + ($tcc == null ? 43 : $tcc.hashCode());
        String $chipCurrencyCode = this.getChipCurrencyCode();
        result = result * 59 + ($chipCurrencyCode == null ? 43 : $chipCurrencyCode.hashCode());
        String $chipTerminalType = this.getChipTerminalType();
        result = result * 59 + ($chipTerminalType == null ? 43 : $chipTerminalType.hashCode());
        String $terminalApplicationVerificationNumber = this.getTerminalApplicationVerificationNumber();
        result = result * 59 + ($terminalApplicationVerificationNumber == null ? 43 : $terminalApplicationVerificationNumber.hashCode());
        String $transactionSequenceCounter = this.getTransactionSequenceCounter();
        result = result * 59 + ($transactionSequenceCounter == null ? 43 : $transactionSequenceCounter.hashCode());
        String $issuerAuthData = this.getIssuerAuthData();
        result = result * 59 + ($issuerAuthData == null ? 43 : $issuerAuthData.hashCode());
        String $transactionLifeCycleId = this.getTransactionLifeCycleId();
        result = result * 59 + ($transactionLifeCycleId == null ? 43 : $transactionLifeCycleId.hashCode());
        String $messageNumber = this.getMessageNumber();
        result = result * 59 + ($messageNumber == null ? 43 : $messageNumber.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        String $independentSalesOrganizationId = this.getIndependentSalesOrganizationId();
        result = result * 59 + ($independentSalesOrganizationId == null ? 43 : $independentSalesOrganizationId.hashCode());
        String $reversalIndicator = this.getReversalIndicator();
        result = result * 59 + ($reversalIndicator == null ? 43 : $reversalIndicator.hashCode());
        String $mastercardAssignedId = this.getMastercardAssignedId();
        result = result * 59 + ($mastercardAssignedId == null ? 43 : $mastercardAssignedId.hashCode());
        String $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : $cardType.hashCode());
        String $domesticInternationFlag = this.getDomesticInternationFlag();
        result = result * 59 + ($domesticInternationFlag == null ? 43 : $domesticInternationFlag.hashCode());
        String $smsDmsFlag = this.getSmsDmsFlag();
        result = result * 59 + ($smsDmsFlag == null ? 43 : $smsDmsFlag.hashCode());
        String $posPgType = this.getPosPgType();
        result = result * 59 + ($posPgType == null ? 43 : $posPgType.hashCode());
        String $centralProcessingDate = this.getCentralProcessingDate();
        result = result * 59 + ($centralProcessingDate == null ? 43 : $centralProcessingDate.hashCode());
        String $outgoingFileDate = this.getOutgoingFileDate();
        result = result * 59 + ($outgoingFileDate == null ? 43 : $outgoingFileDate.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $encryptedCardNumber = this.getEncryptedCardNumber();
        result = result * 59 + ($encryptedCardNumber == null ? 43 : $encryptedCardNumber.hashCode());
        String $meCountryOfOrigin = this.getMeCountryOfOrigin();
        result = result * 59 + ($meCountryOfOrigin == null ? 43 : $meCountryOfOrigin.hashCode());
        return result;
    }

    public String toString() {
        return "RejectedTransactionDetailsVo(transactionType=" + this.getTransactionType() + ", mti=" + this.getMti() + ", cardNumber=" + this.getCardNumber() + ", processingCode=" + this.getProcessingCode() + ", transactionAmount=" + this.getTransactionAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", localDateTime=" + this.getLocalDateTime() + ", expiryDate=" + this.getExpiryDate() + ", posDataCode=" + this.getPosDataCode() + ", functionCode=" + this.getFunctionCode() + ", messageReasonCode=" + this.getMessageReasonCode() + ", mcc=" + this.getMcc() + ", arn=" + this.getArn() + ", acquireInstitutionId=" + this.getAcquireInstitutionId() + ", rrn=" + this.getRrn() + ", approvalCode=" + this.getApprovalCode() + ", responseCode=" + this.getResponseCode() + ", seviceCode=" + this.getSeviceCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", merchantName=" + this.getMerchantName() + ", merchantCity=" + this.getMerchantCity() + ", merchantZipCode=" + this.getMerchantZipCode() + ", merchantCountry=" + this.getMerchantCountry() + ", terminalType=" + this.getTerminalType() + ", ecomIndicator=" + this.getEcomIndicator() + ", transactionFeeAmount=" + this.getTransactionFeeAmount() + ", transactionCurrencyExponent=" + this.getTransactionCurrencyExponent() + ", transactionCurrencyCode=" + this.getTransactionCurrencyCode() + ", ird=" + this.getIrd() + ", settledIndicator=" + this.getSettledIndicator() + ", cardSequenceNumber=" + this.getCardSequenceNumber() + ", applicationCryptogram=" + this.getApplicationCryptogram() + ", cryptogramInformationData=" + this.getCryptogramInformationData() + ", issuerApplicationData=" + this.getIssuerApplicationData() + ", upblNumber=" + this.getUpblNumber() + ", applicationTransactionCounter=" + this.getApplicationTransactionCounter() + ", terminalVerificationResult=" + this.getTerminalVerificationResult() + ", transactionDate=" + this.getTransactionDate() + ", chipTransactionDate=" + this.getChipTransactionDate() + ", chipTransactionType=" + this.getChipTransactionType() + ", cryptAmount=" + this.getCryptAmount() + ", applicationInterchangeProfile=" + this.getApplicationInterchangeProfile() + ", terminalCountryCode=" + this.getTerminalCountryCode() + ", cashbackAmount=" + this.getCashbackAmount() + ", cvmResult=" + this.getCvmResult() + ", terminalCapabilities=" + this.getTerminalCapabilities() + ", tcc=" + this.getTcc() + ", chipCurrencyCode=" + this.getChipCurrencyCode() + ", chipTerminalType=" + this.getChipTerminalType() + ", terminalApplicationVerificationNumber=" + this.getTerminalApplicationVerificationNumber() + ", transactionSequenceCounter=" + this.getTransactionSequenceCounter() + ", issuerAuthData=" + this.getIssuerAuthData() + ", transactionLifeCycleId=" + this.getTransactionLifeCycleId() + ", messageNumber=" + this.getMessageNumber() + ", memberText=" + this.getMemberText() + ", independentSalesOrganizationId=" + this.getIndependentSalesOrganizationId() + ", reversalIndicator=" + this.getReversalIndicator() + ", mastercardAssignedId=" + this.getMastercardAssignedId() + ", cardType=" + this.getCardType() + ", domesticInternationFlag=" + this.getDomesticInternationFlag() + ", smsDmsFlag=" + this.getSmsDmsFlag() + ", posPgType=" + this.getPosPgType() + ", centralProcessingDate=" + this.getCentralProcessingDate() + ", outgoingFileDate=" + this.getOutgoingFileDate() + ", fileId=" + this.getFileId() + ", encryptedCardNumber=" + this.getEncryptedCardNumber() + ", meCountryOfOrigin=" + this.getMeCountryOfOrigin() + ", totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ")";
    }
}

