// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class JulianDateConverter
{
    public String getCurrentJulianYDDD(final LocalDate date) {
        final int lastDigitOfYear = date.getYear() % 10;
        final int dayOfYear = date.getDayOfYear();
        return String.format("%d%03d", lastDigitOfYear, dayOfYear);
    }
}

