/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.mercury.vo.ATMRecordXCVo
 */
package com.empay.mercury.vo;

public class ATMRecordXCVo {
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ATMRecordXCVo)) {
            return false;
        }
        ATMRecordXCVo other = (ATMRecordXCVo)o;
        return other.canEqual((Object)this);
    }

    protected boolean canEqual(Object other) {
        return other instanceof ATMRecordXCVo;
    }

    public int hashCode() {
        boolean result = true;
        return 1;
    }

    public String toString() {
        return "ATMRecordXCVo()";
    }
}

