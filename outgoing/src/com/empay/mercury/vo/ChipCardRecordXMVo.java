/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.mercury.vo.ChipCardRecordXMVo
 */
package com.empay.mercury.vo;

public class ChipCardRecordXMVo {
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChipCardRecordXMVo)) {
            return false;
        }
        ChipCardRecordXMVo other = (ChipCardRecordXMVo)o;
        return other.canEqual((Object)this);
    }

    protected boolean canEqual(Object other) {
        return other instanceof ChipCardRecordXMVo;
    }

    public int hashCode() {
        boolean result = true;
        return 1;
    }

    public String toString() {
        return "ChipCardRecordXMVo()";
    }
}

