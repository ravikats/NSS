// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.springframework.stereotype.Service;

@Service
public class ARNCheckDigit
{
    public String addCheckDigit(final String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); ++i) {
            int digit = Character.getNumericValue(number.charAt(number.length() - 1 - i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        final int checkDigit = (10 - sum % 10) % 10;
        return number + checkDigit;
    }
}

