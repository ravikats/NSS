/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.mercury.vo.CashBackRecordMCVo
 */
package com.empay.mercury.vo;

public class CashBackRecordMCVo {
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CashBackRecordMCVo)) {
            return false;
        }
        CashBackRecordMCVo other = (CashBackRecordMCVo)o;
        return other.canEqual((Object)this);
    }

    protected boolean canEqual(Object other) {
        return other instanceof CashBackRecordMCVo;
    }

    public int hashCode() {
        boolean result = true;
        return 1;
    }

    public String toString() {
        return "CashBackRecordMCVo()";
    }
}

