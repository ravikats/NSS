/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.vo.JaywanXmlHeaderValueObject
 *  com.empay.jaywan.vo.JaywanXmlTrailerValueObject
 *  com.empay.jaywan.vo.JaywanXmlTransactionsBlockValueObject
 *  com.empay.jaywan.vo.JaywanXmlWrapperValueObject
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
 *  com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
 */
package com.empay.jaywan.vo;

import com.empay.jaywan.vo.JaywanXmlHeaderValueObject;
import com.empay.jaywan.vo.JaywanXmlTrailerValueObject;
import com.empay.jaywan.vo.JaywanXmlTransactionsBlockValueObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="File")
public class JaywanXmlWrapperValueObject {
    @JacksonXmlProperty(localName="Hdr")
    private JaywanXmlHeaderValueObject header;
    @JacksonXmlProperty(localName="TxnBlock")
    private JaywanXmlTransactionsBlockValueObject txnBlock;
    @JacksonXmlProperty(localName="Trl")
    private JaywanXmlTrailerValueObject trailer;

    public JaywanXmlHeaderValueObject getHeader() {
        return this.header;
    }

    public JaywanXmlTransactionsBlockValueObject getTxnBlock() {
        return this.txnBlock;
    }

    public JaywanXmlTrailerValueObject getTrailer() {
        return this.trailer;
    }

    @JacksonXmlProperty(localName="Hdr")
    public void setHeader(JaywanXmlHeaderValueObject header) {
        this.header = header;
    }

    @JacksonXmlProperty(localName="TxnBlock")
    public void setTxnBlock(JaywanXmlTransactionsBlockValueObject txnBlock) {
        this.txnBlock = txnBlock;
    }

    @JacksonXmlProperty(localName="Trl")
    public void setTrailer(JaywanXmlTrailerValueObject trailer) {
        this.trailer = trailer;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JaywanXmlWrapperValueObject)) {
            return false;
        }
        JaywanXmlWrapperValueObject other = (JaywanXmlWrapperValueObject)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        JaywanXmlHeaderValueObject this$header = this.getHeader();
        JaywanXmlHeaderValueObject other$header = other.getHeader();
        if (this$header == null ? other$header != null : !this$header.equals(other$header)) {
            return false;
        }
        JaywanXmlTransactionsBlockValueObject this$txnBlock = this.getTxnBlock();
        JaywanXmlTransactionsBlockValueObject other$txnBlock = other.getTxnBlock();
        if (this$txnBlock == null ? other$txnBlock != null : !this$txnBlock.equals(other$txnBlock)) {
            return false;
        }
        JaywanXmlTrailerValueObject this$trailer = this.getTrailer();
        JaywanXmlTrailerValueObject other$trailer = other.getTrailer();
        return !(this$trailer == null ? other$trailer != null : !this$trailer.equals(other$trailer));
    }

    protected boolean canEqual(Object other) {
        return other instanceof JaywanXmlWrapperValueObject;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        JaywanXmlHeaderValueObject $header = this.getHeader();
        result = result * 59 + ($header == null ? 43 : $header.hashCode());
        JaywanXmlTransactionsBlockValueObject $txnBlock = this.getTxnBlock();
        result = result * 59 + ($txnBlock == null ? 43 : $txnBlock.hashCode());
        JaywanXmlTrailerValueObject $trailer = this.getTrailer();
        result = result * 59 + ($trailer == null ? 43 : $trailer.hashCode());
        return result;
    }

    public String toString() {
        return "JaywanXmlWrapperValueObject(header=" + String.valueOf(this.getHeader()) + ", txnBlock=" + String.valueOf(this.getTxnBlock()) + ", trailer=" + String.valueOf(this.getTrailer()) + ")";
    }
}

