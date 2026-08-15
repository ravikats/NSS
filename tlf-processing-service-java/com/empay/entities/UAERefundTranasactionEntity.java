// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "UAE_REFUND_TXN")
public class UAERefundTranasactionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "URT_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "URT_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "URT_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "URT_INS_CODE")
    private Integer insCode;
    @Column(name = "URT_INT_CODE")
    private Integer intCode;
    @Column(name = "URT_TXN_SER_NUMBER")
    private Integer txnSerNumber;
    @Column(name = "URT_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name = "URT_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "URT_PAN")
    private String pan;
    @Column(name = "URT_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name = "URT_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "URT_TXN_CURRENCY")
    private String txnCurrency;
    @Column(name = "URT_ACQ_INST_ID")
    private String acqinstIdCode;
    @Column(name = "URT_ISSUER_INST_ID")
    private String issInstIdCode;
    @Column(name = "URT_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "URT_STAN")
    private String stan;
    @Column(name = "URT_ISSUING_NETWORK")
    private String issuingNetwork;
    @Column(name = "URT_MCC")
    private String mcc;
    @Column(name = "URT_ACQ_COUNTRY_CODE")
    private String acqCountryCode;
    @Column(name = "URT_AUTH_CODE")
    private String authCode;
    @Column(name = "URT_TERMINAL_ID")
    private String terminalId;
    @Column(name = "URT_MERCHANT_NAME")
    private String merchantName;
    @Column(name = "URT_MERCHANT_ID")
    private String merchantId;
    @Column(name = "URT_RETAILER_ID")
    private String retailerID;
    @Column(name = "URT_STATUS")
    private String status;
    @Column(name = "URT_STATUS_DESC")
    private String statusDesc;
    
    public static UAERefundTranasactionEntityBuilder builder() {
        return new UAERefundTranasactionEntityBuilder();
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
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public Integer getIntCode() {
        return this.intCode;
    }
    
    public Integer getTxnSerNumber() {
        return this.txnSerNumber;
    }
    
    public Integer getJobNumber() {
        return this.jobNumber;
    }
    
    public Integer getGenStatus() {
        return this.genStatus;
    }
    
    public String getPan() {
        return this.pan;
    }
    
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }
    
    public Double getTxnAmount() {
        return this.txnAmount;
    }
    
    public String getTxnCurrency() {
        return this.txnCurrency;
    }
    
    public String getAcqinstIdCode() {
        return this.acqinstIdCode;
    }
    
    public String getIssInstIdCode() {
        return this.issInstIdCode;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getStan() {
        return this.stan;
    }
    
    public String getIssuingNetwork() {
        return this.issuingNetwork;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getAcqCountryCode() {
        return this.acqCountryCode;
    }
    
    public String getAuthCode() {
        return this.authCode;
    }
    
    public String getTerminalId() {
        return this.terminalId;
    }
    
    public String getMerchantName() {
        return this.merchantName;
    }
    
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public String getRetailerID() {
        return this.retailerID;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getStatusDesc() {
        return this.statusDesc;
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
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setTxnSerNumber(final Integer txnSerNumber) {
        this.txnSerNumber = txnSerNumber;
    }
    
    public void setJobNumber(final Integer jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
    }
    
    public void setPan(final String pan) {
        this.pan = pan;
    }
    
    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setTxnCurrency(final String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }
    
    public void setAcqinstIdCode(final String acqinstIdCode) {
        this.acqinstIdCode = acqinstIdCode;
    }
    
    public void setIssInstIdCode(final String issInstIdCode) {
        this.issInstIdCode = issInstIdCode;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public void setIssuingNetwork(final String issuingNetwork) {
        this.issuingNetwork = issuingNetwork;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setAcqCountryCode(final String acqCountryCode) {
        this.acqCountryCode = acqCountryCode;
    }
    
    public void setAuthCode(final String authCode) {
        this.authCode = authCode;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setMerchantName(final String merchantName) {
        this.merchantName = merchantName;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setRetailerID(final String retailerID) {
        this.retailerID = retailerID;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UAERefundTranasactionEntity)) {
            return false;
        }
        final UAERefundTranasactionEntity other = (UAERefundTranasactionEntity)o;
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
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0139: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0139;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
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
        final Object this$txnSerNumber = this.getTxnSerNumber();
        final Object other$txnSerNumber = other.getTxnSerNumber();
        Label_0213: {
            if (this$txnSerNumber == null) {
                if (other$txnSerNumber == null) {
                    break Label_0213;
                }
            }
            else if (this$txnSerNumber.equals(other$txnSerNumber)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$jobNumber = this.getJobNumber();
        final Object other$jobNumber = other.getJobNumber();
        Label_0250: {
            if (this$jobNumber == null) {
                if (other$jobNumber == null) {
                    break Label_0250;
                }
            }
            else if (this$jobNumber.equals(other$jobNumber)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$genStatus = this.getGenStatus();
        final Object other$genStatus = other.getGenStatus();
        Label_0287: {
            if (this$genStatus == null) {
                if (other$genStatus == null) {
                    break Label_0287;
                }
            }
            else if (this$genStatus.equals(other$genStatus)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$txnAmount = this.getTxnAmount();
        final Object other$txnAmount = other.getTxnAmount();
        Label_0324: {
            if (this$txnAmount == null) {
                if (other$txnAmount == null) {
                    break Label_0324;
                }
            }
            else if (this$txnAmount.equals(other$txnAmount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0361: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0361;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$pan = this.getPan();
        final Object other$pan = other.getPan();
        Label_0398: {
            if (this$pan == null) {
                if (other$pan == null) {
                    break Label_0398;
                }
            }
            else if (this$pan.equals(other$pan)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$localDateTime = this.getLocalDateTime();
        final Object other$localDateTime = other.getLocalDateTime();
        Label_0435: {
            if (this$localDateTime == null) {
                if (other$localDateTime == null) {
                    break Label_0435;
                }
            }
            else if (this$localDateTime.equals(other$localDateTime)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$txnCurrency = this.getTxnCurrency();
        final Object other$txnCurrency = other.getTxnCurrency();
        Label_0472: {
            if (this$txnCurrency == null) {
                if (other$txnCurrency == null) {
                    break Label_0472;
                }
            }
            else if (this$txnCurrency.equals(other$txnCurrency)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$acqinstIdCode = this.getAcqinstIdCode();
        final Object other$acqinstIdCode = other.getAcqinstIdCode();
        Label_0509: {
            if (this$acqinstIdCode == null) {
                if (other$acqinstIdCode == null) {
                    break Label_0509;
                }
            }
            else if (this$acqinstIdCode.equals(other$acqinstIdCode)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$issInstIdCode = this.getIssInstIdCode();
        final Object other$issInstIdCode = other.getIssInstIdCode();
        Label_0546: {
            if (this$issInstIdCode == null) {
                if (other$issInstIdCode == null) {
                    break Label_0546;
                }
            }
            else if (this$issInstIdCode.equals(other$issInstIdCode)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0583: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0583;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$stan = this.getStan();
        final Object other$stan = other.getStan();
        Label_0620: {
            if (this$stan == null) {
                if (other$stan == null) {
                    break Label_0620;
                }
            }
            else if (this$stan.equals(other$stan)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$issuingNetwork = this.getIssuingNetwork();
        final Object other$issuingNetwork = other.getIssuingNetwork();
        Label_0657: {
            if (this$issuingNetwork == null) {
                if (other$issuingNetwork == null) {
                    break Label_0657;
                }
            }
            else if (this$issuingNetwork.equals(other$issuingNetwork)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0694: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0694;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$acqCountryCode = this.getAcqCountryCode();
        final Object other$acqCountryCode = other.getAcqCountryCode();
        Label_0731: {
            if (this$acqCountryCode == null) {
                if (other$acqCountryCode == null) {
                    break Label_0731;
                }
            }
            else if (this$acqCountryCode.equals(other$acqCountryCode)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$authCode = this.getAuthCode();
        final Object other$authCode = other.getAuthCode();
        Label_0768: {
            if (this$authCode == null) {
                if (other$authCode == null) {
                    break Label_0768;
                }
            }
            else if (this$authCode.equals(other$authCode)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_0805: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_0805;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$merchantName = this.getMerchantName();
        final Object other$merchantName = other.getMerchantName();
        Label_0842: {
            if (this$merchantName == null) {
                if (other$merchantName == null) {
                    break Label_0842;
                }
            }
            else if (this$merchantName.equals(other$merchantName)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_0879: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_0879;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$retailerID = this.getRetailerID();
        final Object other$retailerID = other.getRetailerID();
        Label_0916: {
            if (this$retailerID == null) {
                if (other$retailerID == null) {
                    break Label_0916;
                }
            }
            else if (this$retailerID.equals(other$retailerID)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0953: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0953;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$statusDesc = this.getStatusDesc();
        final Object other$statusDesc = other.getStatusDesc();
        if (this$statusDesc == null) {
            if (other$statusDesc == null) {
                return true;
            }
        }
        else if (this$statusDesc.equals(other$statusDesc)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UAERefundTranasactionEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $txnSerNumber = this.getTxnSerNumber();
        result = result * 59 + (($txnSerNumber == null) ? 43 : $txnSerNumber.hashCode());
        final Object $jobNumber = this.getJobNumber();
        result = result * 59 + (($jobNumber == null) ? 43 : $jobNumber.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $pan = this.getPan();
        result = result * 59 + (($pan == null) ? 43 : $pan.hashCode());
        final Object $localDateTime = this.getLocalDateTime();
        result = result * 59 + (($localDateTime == null) ? 43 : $localDateTime.hashCode());
        final Object $txnCurrency = this.getTxnCurrency();
        result = result * 59 + (($txnCurrency == null) ? 43 : $txnCurrency.hashCode());
        final Object $acqinstIdCode = this.getAcqinstIdCode();
        result = result * 59 + (($acqinstIdCode == null) ? 43 : $acqinstIdCode.hashCode());
        final Object $issInstIdCode = this.getIssInstIdCode();
        result = result * 59 + (($issInstIdCode == null) ? 43 : $issInstIdCode.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $stan = this.getStan();
        result = result * 59 + (($stan == null) ? 43 : $stan.hashCode());
        final Object $issuingNetwork = this.getIssuingNetwork();
        result = result * 59 + (($issuingNetwork == null) ? 43 : $issuingNetwork.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $acqCountryCode = this.getAcqCountryCode();
        result = result * 59 + (($acqCountryCode == null) ? 43 : $acqCountryCode.hashCode());
        final Object $authCode = this.getAuthCode();
        result = result * 59 + (($authCode == null) ? 43 : $authCode.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $merchantName = this.getMerchantName();
        result = result * 59 + (($merchantName == null) ? 43 : $merchantName.hashCode());
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $retailerID = this.getRetailerID();
        result = result * 59 + (($retailerID == null) ? 43 : $retailerID.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $statusDesc = this.getStatusDesc();
        result = result * 59 + (($statusDesc == null) ? 43 : $statusDesc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UAERefundTranasactionEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", txnSerNumber=" + this.getTxnSerNumber() + ", jobNumber=" + this.getJobNumber() + ", genStatus=" + this.getGenStatus() + ", pan=" + this.getPan() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", txnAmount=" + this.getTxnAmount() + ", txnCurrency=" + this.getTxnCurrency() + ", acqinstIdCode=" + this.getAcqinstIdCode() + ", issInstIdCode=" + this.getIssInstIdCode() + ", rrn=" + this.getRrn() + ", stan=" + this.getStan() + ", issuingNetwork=" + this.getIssuingNetwork() + ", mcc=" + this.getMcc() + ", acqCountryCode=" + this.getAcqCountryCode() + ", authCode=" + this.getAuthCode() + ", terminalId=" + this.getTerminalId() + ", merchantName=" + this.getMerchantName() + ", merchantId=" + this.getMerchantId() + ", retailerID=" + this.getRetailerID() + ", status=" + this.getStatus() + ", statusDesc=" + this.getStatusDesc();
    }
    
    public UAERefundTranasactionEntity() {
    }
    
    public UAERefundTranasactionEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final Integer insCode, final Integer intCode, final Integer txnSerNumber, final Integer jobNumber, final Integer genStatus, final String pan, final LocalDateTime localDateTime, final Double txnAmount, final String txnCurrency, final String acqinstIdCode, final String issInstIdCode, final String rrn, final String stan, final String issuingNetwork, final String mcc, final String acqCountryCode, final String authCode, final String terminalId, final String merchantName, final String merchantId, final String retailerID, final String status, final String statusDesc) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.insCode = insCode;
        this.intCode = intCode;
        this.txnSerNumber = txnSerNumber;
        this.jobNumber = jobNumber;
        this.genStatus = genStatus;
        this.pan = pan;
        this.localDateTime = localDateTime;
        this.txnAmount = txnAmount;
        this.txnCurrency = txnCurrency;
        this.acqinstIdCode = acqinstIdCode;
        this.issInstIdCode = issInstIdCode;
        this.rrn = rrn;
        this.stan = stan;
        this.issuingNetwork = issuingNetwork;
        this.mcc = mcc;
        this.acqCountryCode = acqCountryCode;
        this.authCode = authCode;
        this.terminalId = terminalId;
        this.merchantName = merchantName;
        this.merchantId = merchantId;
        this.retailerID = retailerID;
        this.status = status;
        this.statusDesc = statusDesc;
    }
    
    public static class UAERefundTranasactionEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private Integer insCode;
        private Integer intCode;
        private Integer txnSerNumber;
        private Integer jobNumber;
        private Integer genStatus;
        private String pan;
        private LocalDateTime localDateTime;
        private Double txnAmount;
        private String txnCurrency;
        private String acqinstIdCode;
        private String issInstIdCode;
        private String rrn;
        private String stan;
        private String issuingNetwork;
        private String mcc;
        private String acqCountryCode;
        private String authCode;
        private String terminalId;
        private String merchantName;
        private String merchantId;
        private String retailerID;
        private String status;
        private String statusDesc;
        
        UAERefundTranasactionEntityBuilder() {
        }
        
        public UAERefundTranasactionEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder insCode(final Integer insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder intCode(final Integer intCode) {
            this.intCode = intCode;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder txnSerNumber(final Integer txnSerNumber) {
            this.txnSerNumber = txnSerNumber;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder jobNumber(final Integer jobNumber) {
            this.jobNumber = jobNumber;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder genStatus(final Integer genStatus) {
            this.genStatus = genStatus;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder pan(final String pan) {
            this.pan = pan;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder localDateTime(final LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder txnCurrency(final String txnCurrency) {
            this.txnCurrency = txnCurrency;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder acqinstIdCode(final String acqinstIdCode) {
            this.acqinstIdCode = acqinstIdCode;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder issInstIdCode(final String issInstIdCode) {
            this.issInstIdCode = issInstIdCode;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder stan(final String stan) {
            this.stan = stan;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder issuingNetwork(final String issuingNetwork) {
            this.issuingNetwork = issuingNetwork;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder acqCountryCode(final String acqCountryCode) {
            this.acqCountryCode = acqCountryCode;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder authCode(final String authCode) {
            this.authCode = authCode;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder terminalId(final String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder merchantName(final String merchantName) {
            this.merchantName = merchantName;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder merchantId(final String merchantId) {
            this.merchantId = merchantId;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder retailerID(final String retailerID) {
            this.retailerID = retailerID;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder status(final String status) {
            this.status = status;
            return this;
        }
        
        public UAERefundTranasactionEntityBuilder statusDesc(final String statusDesc) {
            this.statusDesc = statusDesc;
            return this;
        }
        
        public UAERefundTranasactionEntity build() {
            return new UAERefundTranasactionEntity(this.serialNumber, this.lastUpdated, this.updatedUser, this.insCode, this.intCode, this.txnSerNumber, this.jobNumber, this.genStatus, this.pan, this.localDateTime, this.txnAmount, this.txnCurrency, this.acqinstIdCode, this.issInstIdCode, this.rrn, this.stan, this.issuingNetwork, this.mcc, this.acqCountryCode, this.authCode, this.terminalId, this.merchantName, this.merchantId, this.retailerID, this.status, this.statusDesc);
        }
        
        @Override
        public String toString() {
            return "UAERefundTranasactionEntity.UAERefundTranasactionEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", insCode=" + this.insCode + ", intCode=" + this.intCode + ", txnSerNumber=" + this.txnSerNumber + ", jobNumber=" + this.jobNumber + ", genStatus=" + this.genStatus + ", pan=" + this.pan + ", localDateTime=" + String.valueOf(this.localDateTime) + ", txnAmount=" + this.txnAmount + ", txnCurrency=" + this.txnCurrency + ", acqinstIdCode=" + this.acqinstIdCode + ", issInstIdCode=" + this.issInstIdCode + ", rrn=" + this.rrn + ", stan=" + this.stan + ", issuingNetwork=" + this.issuingNetwork + ", mcc=" + this.mcc + ", acqCountryCode=" + this.acqCountryCode + ", authCode=" + this.authCode + ", terminalId=" + this.terminalId + ", merchantName=" + this.merchantName + ", merchantId=" + this.merchantId + ", retailerID=" + this.retailerID + ", status=" + this.status + ", statusDesc=" + this.statusDesc;
        }
    }
}
