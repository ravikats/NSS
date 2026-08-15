// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.util;

import org.springframework.stereotype.Component;

@Component
public class MercuryChargeTypeUtil
{
    public static String getChargeTypeCode(final String mcc) {
        if (mcc == null || mcc.isBlank()) {
            return "999";
        }
        int value;
        try {
            value = Integer.parseInt(mcc.trim());
        }
        catch (final NumberFormatException e) {
            return "999";
        }
        if (value == 9311) {
            return "181";
        }
        if (value == 9222) {
            return "182";
        }
        if (value == 8043) {
            return "309";
        }
        if (value == 4814) {
            return "860";
        }
        if (value == 4816) {
            return "920";
        }
        if (value >= 3501 && value <= 3999) {
            return "100";
        }
        if (value >= 7531 && value <= 7538) {
            return "410";
        }
        if (value >= 3000 && value <= 3299) {
            return "500";
        }
        switch (value) {
            case 8011:
            case 8021:
            case 8031:
            case 8041:
            case 8042:
            case 8050:
            case 8062:
            case 8071:
            case 8099: {
                return "110";
            }
            case 8211:
            case 8220:
            case 8241:
            case 8244:
            case 8249:
            case 8299: {
                return "120";
            }
            case 7210:
            case 7211:
            case 7216:
            case 7217: {
                return "130";
            }
            case 4899:
            case 4900: {
                return "150";
            }
            case 6513: {
                return "160";
            }
            case 9211:
            case 9223:
            case 9399:
            case 9402:
            case 9405: {
                return "180";
            }
            case 4784:
            case 7523: {
                return "183";
            }
            case 5812: {
                return "200";
            }
            case 5811: {
                return "201";
            }
            case 5813: {
                return "210";
            }
            case 7832:
            case 7922:
            case 7929: {
                return "211";
            }
            case 5941: {
                return "302";
            }
            case 5942:
            case 5943: {
                return "303";
            }
            case 5921: {
                return "305";
            }
            case 5193:
            case 5992: {
                return "306";
            }
            case 7230: {
                return "307";
            }
            case 5912: {
                return "314";
            }
            case 5411:
            case 5499: {
                return "315";
            }
            case 5094:
            case 5944: {
                return "320";
            }
            case 5621:
            case 5651:
            case 5691: {
                return "330";
            }
            case 5732:
            case 5734: {
                return "340";
            }
            case 7512: {
                return "400";
            }
            case 4121: {
                return "420";
            }
            case 4511: {
                return "500";
            }
            case 4722: {
                return "600";
            }
            case 4468: {
                return "610";
            }
            case 4411: {
                return "620";
            }
            case 4112: {
                return "630";
            }
            case 5541: {
                return "700";
            }
            case 5542: {
                return "710";
            }
            case 7995: {
                return "850";
            }
            case 5960:
            case 5964:
            case 5965: {
                return "900";
            }
            case 5968:
            case 5969: {
                return "910";
            }
            case 8111: {
                return "930";
            }
            case 8931: {
                return "940";
            }
            case 7393: {
                return "950";
            }
            case 7361: {
                return "960";
            }
            case 7392: {
                return "970";
            }
            case 1520:
            case 1711:
            case 1731:
            case 1740: {
                return "980";
            }
            default: {
                return "999";
            }
        }
    }
}
