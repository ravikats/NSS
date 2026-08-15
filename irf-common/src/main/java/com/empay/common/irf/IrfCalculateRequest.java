package com.empay.common.irf;

/**
 * REST request body for "compute one IRF". Carries the txn-agnostic data the
 * server-side calculator needs plus the decrypted PAN used for BIN/route
 * lookups.
 */
public class IrfCalculateRequest {

    private String network;
    private Integer insCode;
    private IrfTxnData txnData;
    private String cardNumber;

    public IrfCalculateRequest() {
    }

    public IrfCalculateRequest(String network, Integer insCode, IrfTxnData txnData, String cardNumber) {
        this.network = network;
        this.insCode = insCode;
        this.txnData = txnData;
        this.cardNumber = cardNumber;
    }

    public String getNetwork() {
        return network;
    }

    public IrfCalculateRequest setNetwork(String network) {
        this.network = network;
        return this;
    }

    public Integer getInsCode() {
        return insCode;
    }

    public IrfCalculateRequest setInsCode(Integer insCode) {
        this.insCode = insCode;
        return this;
    }

    public IrfTxnData getTxnData() {
        return txnData;
    }

    public IrfCalculateRequest setTxnData(IrfTxnData txnData) {
        this.txnData = txnData;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public IrfCalculateRequest setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }
}
