package com.empay.common.irf;

/**
 * Minimal data required to build the scheme callback payload.
 * Adapters turn the service entity (PosTransactionEntity / MPGSTxnWorkEntity)
 * into this view. It mirrors the subset of {@code IRFCallbackEntity} needed
 * to enqueue or flush a callback over REST.
 */
public class IrfResultData {

    private String cpMid;
    private String uniqueId;
    private String irdCode;
    private Double fixed;
    private Double percentage;
    private Double irfAmount;
    private Double txnAmount;
    private String rrn;
    private String mti;
    private Character domIntlFlag;
    private Boolean isCredit;
    private String cardClassification;
    private Integer insCode;
    private Integer refSerNumber;

    public IrfResultData() {
    }

    public IrfResultData(String cpMid, String uniqueId, String irdCode,
                         Double fixed, Double percentage, Double irfAmount,
                         Double txnAmount, String rrn, String mti,
                         Character domIntlFlag, Boolean isCredit,
                         String cardClassification) {
        this(cpMid, uniqueId, irdCode, fixed, percentage, irfAmount,
                txnAmount, rrn, mti, domIntlFlag, isCredit, cardClassification,
                null, null);
    }

    public IrfResultData(String cpMid, String uniqueId, String irdCode,
                         Double fixed, Double percentage, Double irfAmount,
                         Double txnAmount, String rrn, String mti,
                         Character domIntlFlag, Boolean isCredit,
                         String cardClassification, Integer insCode,
                         Integer refSerNumber) {
        this.cpMid = cpMid;
        this.uniqueId = uniqueId;
        this.irdCode = irdCode;
        this.fixed = fixed;
        this.percentage = percentage;
        this.irfAmount = irfAmount;
        this.txnAmount = txnAmount;
        this.rrn = rrn;
        this.mti = mti;
        this.domIntlFlag = domIntlFlag;
        this.isCredit = isCredit;
        this.cardClassification = cardClassification;
        this.insCode = insCode;
        this.refSerNumber = refSerNumber;
    }

    public String getCpMid() { return cpMid; }
    public IrfResultData setCpMid(String cpMid) { this.cpMid = cpMid; return this; }

    public String getUniqueId() { return uniqueId; }
    public IrfResultData setUniqueId(String uniqueId) { this.uniqueId = uniqueId; return this; }

    public String getIrdCode() { return irdCode; }
    public IrfResultData setIrdCode(String irdCode) { this.irdCode = irdCode; return this; }

    public Double getFixed() { return fixed; }
    public IrfResultData setFixed(Double fixed) { this.fixed = fixed; return this; }

    public Double getPercentage() { return percentage; }
    public IrfResultData setPercentage(Double percentage) { this.percentage = percentage; return this; }

    public Double getIrfAmount() { return irfAmount; }
    public IrfResultData setIrfAmount(Double irfAmount) { this.irfAmount = irfAmount; return this; }

    public Double getTxnAmount() { return txnAmount; }
    public IrfResultData setTxnAmount(Double txnAmount) { this.txnAmount = txnAmount; return this; }

    public String getRrn() { return rrn; }
    public IrfResultData setRrn(String rrn) { this.rrn = rrn; return this; }

    public String getMti() { return mti; }
    public IrfResultData setMti(String mti) { this.mti = mti; return this; }

    public Character getDomIntlFlag() { return domIntlFlag; }
    public IrfResultData setDomIntlFlag(Character domIntlFlag) { this.domIntlFlag = domIntlFlag; return this; }

    public Boolean getCredit() { return isCredit; }
    public IrfResultData setIsCredit(Boolean isCredit) { this.isCredit = isCredit; return this; }

    public String getCardClassification() { return cardClassification; }
    public IrfResultData setCardClassification(String cardClassification) { this.cardClassification = cardClassification; return this; }

    public Integer getInsCode() { return insCode; }
    public IrfResultData setInsCode(Integer insCode) { this.insCode = insCode; return this; }

    public Integer getRefSerNumber() { return refSerNumber; }
    public IrfResultData setRefSerNumber(Integer refSerNumber) { this.refSerNumber = refSerNumber; return this; }
}
