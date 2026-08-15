/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.BatchDetailsResponseVo
 *  com.empay.vo.BatchTxnSummaryVo
 */
package com.empay.vo;

import com.empay.vo.BatchTxnSummaryVo;
import java.util.Map;

public class BatchDetailsResponseVo {
    private String batch_id;
    private Map<String, Map<String, BatchTxnSummaryVo>> batch_totals;

    public String getBatch_id() {
        return this.batch_id;
    }

    public Map<String, Map<String, BatchTxnSummaryVo>> getBatch_totals() {
        return this.batch_totals;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public void setBatch_totals(Map<String, Map<String, BatchTxnSummaryVo>> batch_totals) {
        this.batch_totals = batch_totals;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDetailsResponseVo)) {
            return false;
        }
        BatchDetailsResponseVo other = (BatchDetailsResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$batch_id = this.getBatch_id();
        String other$batch_id = other.getBatch_id();
        if (this$batch_id == null ? other$batch_id != null : !this$batch_id.equals(other$batch_id)) {
            return false;
        }
        Map this$batch_totals = this.getBatch_totals();
        Map other$batch_totals = other.getBatch_totals();
        return !(this$batch_totals == null ? other$batch_totals != null : !((Object)this$batch_totals).equals(other$batch_totals));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BatchDetailsResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $batch_id = this.getBatch_id();
        result = result * 59 + ($batch_id == null ? 43 : $batch_id.hashCode());
        Map $batch_totals = this.getBatch_totals();
        result = result * 59 + ($batch_totals == null ? 43 : ((Object)$batch_totals).hashCode());
        return result;
    }

    public String toString() {
        return "BatchDetailsResponseVo(batch_id=" + this.getBatch_id() + ", batch_totals=" + String.valueOf(this.getBatch_totals()) + ")";
    }
}

