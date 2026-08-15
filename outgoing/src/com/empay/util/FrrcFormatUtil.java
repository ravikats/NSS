/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.util.FrrcFormatUtil
 */
package com.empay.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FrrcFormatUtil {
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("0.00");

    private FrrcFormatUtil() {
    }

    public static String safe(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    public static String amount(BigDecimal value) {
        if (value == null) {
            return ".00";
        }
        return AMOUNT_FORMAT.format(value);
    }

    public static String line(String ... fields) {
        return String.join((CharSequence)">", fields);
    }
}

