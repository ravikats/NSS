package com.empay.irfservice.callback;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Owned by irf-service (the single source of truth for the IRF_CALLBACK table).
 * Mirrors the subset of {@code com.empay.entities.IRFCallbackEntity} used by TLF
 * and MPGS, plus the job-number/institution fields needed for batch flush.
 */
@Entity
@Table(name = "IRF_CALLBACK")
public class IRFCallbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ICB_SER_NUMBER")
    private Integer serialNumber;

    @Column(name = "ICB_LAST_UPDATED", updatable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "ICB_UPDATED_USER")
    private Integer updatedUser;

    @Column(name = "ICB_INS_CODE")
    private Integer institutionCode;

    @Column(name = "ICB_JOB_NUMBER")
    private Integer jobNumber;

    @Column(name = "ICB_STATUS")
    private Character status;

    @Column(name = "ICB_REF_SER_NUMBER")
    private Integer refSerNumber;

    @Column(name = "ICB_REQUEST")
    private String request;

    @Column(name = "ICB_RESPONSE")
    private String response;

    @Column(name = "ICB_SCHEME_INCHG_FLAG")
    private String schemeInchgFlag;

    @Column(name = "ICB_CP_MID")
    private String cpMid;

    @Column(name = "ICB_UNIQUE_ID")
    private String uniqueId;

    @Column(name = "ICB_IRD_CODE")
    private String irdCode;

    @Column(name = "ICB_FIXED")
    private Double fixed;

    @Column(name = "ICB_PERCENTAGE")
    private Double percentage;

    @Column(name = "ICB_IRF_AMOUNT")
    private Double irfAmount;

    @Column(name = "ICB_TXN_AMOUNT")
    private Double txnAmount;

    @Column(name = "ICB_RRN")
    private String rrn;

    @Column(name = "ICB_MTI")
    private String mti;

    @Column(name = "ICB_DOM_INTL_FLAG")
    private Character domIntlFlag;

    @Column(name = "ICB_IS_CREDIT")
    private Boolean credit;

    @Column(name = "ICB_CARD_CLASSIFICATION")
    private String cardClassification;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Integer getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public Integer getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Integer getRefSerNumber() {
        return refSerNumber;
    }

    public void setRefSerNumber(Integer refSerNumber) {
        this.refSerNumber = refSerNumber;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSchemeInchgFlag() {
        return schemeInchgFlag;
    }

    public void setSchemeInchgFlag(String schemeInchgFlag) {
        this.schemeInchgFlag = schemeInchgFlag;
    }

    public String getCpMid() {
        return cpMid;
    }

    public void setCpMid(String cpMid) {
        this.cpMid = cpMid;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIrdCode() {
        return irdCode;
    }

    public void setIrdCode(String irdCode) {
        this.irdCode = irdCode;
    }

    public Double getFixed() {
        return fixed;
    }

    public void setFixed(Double fixed) {
        this.fixed = fixed;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getIrfAmount() {
        return irfAmount;
    }

    public void setIrfAmount(Double irfAmount) {
        this.irfAmount = irfAmount;
    }

    public Double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public Character getDomIntlFlag() {
        return domIntlFlag;
    }

    public void setDomIntlFlag(Character domIntlFlag) {
        this.domIntlFlag = domIntlFlag;
    }

    public Boolean getCredit() {
        return credit;
    }

    public void setCredit(Boolean credit) {
        this.credit = credit;
    }

    public String getCardClassification() {
        return cardClassification;
    }

    public void setCardClassification(String cardClassification) {
        this.cardClassification = cardClassification;
    }
}
