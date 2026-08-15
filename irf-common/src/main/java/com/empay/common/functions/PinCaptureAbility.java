// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.springframework.stereotype.Component;

@Component
public class PinCaptureAbility
{
    public char getPinCaptureAbility(final String vPosEntryMode) {
        char cPinCaptureAbility = '1';
        if (vPosEntryMode.startsWith("04")) {
            cPinCaptureAbility = '4';
        }
        else if (vPosEntryMode.startsWith("05")) {
            cPinCaptureAbility = '5';
        }
        else if (vPosEntryMode.startsWith("06")) {
            cPinCaptureAbility = '6';
        }
        else if (vPosEntryMode.startsWith("07")) {
            cPinCaptureAbility = '7';
        }
        else if (vPosEntryMode.startsWith("08")) {
            cPinCaptureAbility = '8';
        }
        else if (vPosEntryMode.startsWith("09")) {
            cPinCaptureAbility = '9';
        }
        else if (vPosEntryMode.startsWith("10")) {
            cPinCaptureAbility = 'A';
        }
        else if (vPosEntryMode.startsWith("11")) {
            cPinCaptureAbility = 'B';
        }
        else if (vPosEntryMode.startsWith("12")) {
            cPinCaptureAbility = 'C';
        }
        else if (vPosEntryMode.charAt(1) == '2') {
            cPinCaptureAbility = '0';
        }
        return cPinCaptureAbility;
    }
}

