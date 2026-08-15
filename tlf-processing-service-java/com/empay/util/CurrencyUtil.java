// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.util;

import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CurrencyUtil
{
    private static final Set<String> CURRENCY_CODES_3;
    
    public static int getExponent(final String currencyCode) {
        if (currencyCode != null && CurrencyUtil.CURRENCY_CODES_3.contains(currencyCode)) {
            return 3;
        }
        return 2;
    }
    
    public static int getDivisor(final String currencyCode) {
        if (currencyCode != null && CurrencyUtil.CURRENCY_CODES_3.contains(currencyCode)) {
            return 1000;
        }
        return 100;
    }
    
    static {
        CURRENCY_CODES_3 = Set.of(new String[] { "048", "400", "414", "434", "512", "788", "BHD", "JOD", "KWD", "LYD", "OMR", "TND" });
    }
}
