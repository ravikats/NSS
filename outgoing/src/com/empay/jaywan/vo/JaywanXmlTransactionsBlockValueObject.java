/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.vo.JaywanXmlTransactionsBlockValueObject
 *  com.empay.jaywan.vo.JaywanXmlTransactionsValueObject
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
 */
package com.empay.jaywan.vo;

import com.empay.jaywan.vo.JaywanXmlTransactionsValueObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class JaywanXmlTransactionsBlockValueObject {
    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName="Txn")
    private List<JaywanXmlTransactionsValueObject> transactions;

    public List<JaywanXmlTransactionsValueObject> getTransactions() {
        return this.transactions;
    }

    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName="Txn")
    public void setTransactions(List<JaywanXmlTransactionsValueObject> transactions) {
        this.transactions = transactions;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanXmlTransactionsBlockValueObject)) {
            return false;
        }
        JaywanXmlTransactionsBlockValueObject other = (JaywanXmlTransactionsBlockValueObject)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        List this$transactions = this.getTransactions();
        List other$transactions = other.getTransactions();
        return !(this$transactions == null ? other$transactions != null : !((Object)this$transactions).equals(other$transactions));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanXmlTransactionsBlockValueObject;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        List $transactions = this.getTransactions();
        result = result * 59 + ($transactions == null ? 43 : ((Object)$transactions).hashCode());
        return result;
    }

    public String toString() {
        return "JaywanXmlTransactionsBlockValueObject(transactions=" + String.valueOf(this.getTransactions()) + ")";
    }
}

