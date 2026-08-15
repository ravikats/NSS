// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.util;

import org.springframework.stereotype.Component;

@Component
public class MercuryPosUtil
{
    public static String getTYPCH(final String posEntryMode) {
        if (posEntryMode == null || posEntryMode.isBlank()) {
            return "TE";
        }
        String value = posEntryMode.trim().toUpperCase();
        if ("ECOM".equals(value)) {
            return "TI";
        }
        if (value.length() >= 3) {
            value = value.substring(0, 3);
        }
        final String s = value;
        switch (s) {
            case "051":
            case "052":
            case "071":
            case "072": {
                return "TK";
            }
            case "021":
            case "022":
            case "012": {
                return "TE";
            }
            default: {
                return "TE";
            }
        }
    }
    
    public static Character getCPTRM(final String posEntryMode) {
        if (posEntryMode == null || posEntryMode.isBlank()) {
            return '1';
        }
        String posentryMode = posEntryMode.trim();
        final String s;
        posentryMode = (s = ((posentryMode.length() >= 3) ? posentryMode.substring(0, 3) : posentryMode));
        switch (s) {
            case "051":
            case "052": {
                return '5';
            }
            case "071":
            case "072": {
                return 'U';
            }
            case "801":
            case "802": {
                return '9';
            }
            case "021":
            case "022":
            case "901":
            case "902": {
                return '2';
            }
            case "012": {
                return '1';
            }
            default: {
                return '1';
            }
        }
    }
    
    public static Character getCRDINP(final String posEntryMode) {
        if (posEntryMode == null || posEntryMode.isBlank()) {
            return '1';
        }
        String posentryMode = posEntryMode.trim();
        final String s;
        posentryMode = (s = ((posentryMode.length() >= 3) ? posentryMode.substring(0, 3) : posentryMode));
        switch (s) {
            case "051":
            case "052": {
                return '5';
            }
            case "071":
            case "072": {
                return '8';
            }
            case "021":
            case "022": {
                return '2';
            }
            case "012": {
                return '1';
            }
            default: {
                return '1';
            }
        }
    }
}
