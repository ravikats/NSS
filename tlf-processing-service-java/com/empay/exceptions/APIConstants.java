// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.exceptions;

public class APIConstants
{
    public static final Character CALLBACK_SUCCESS;
    public static final Character CALLBACK_PENDING;
    public static final Character CALLBACK_FAILED;
    public static final String INVALID_RRN = "INVALID RRN ; RRN exceeds the allowed length";
    public static final String MISSING_RRN = "INVALID RRN ; RRN is missing";
    public static final String DUPLICATE_RRN = "DUPLICATE RRN ; RRN already Exist";
    public static final String INVALID_TXN_SOURCE = "INVALID CHANNEL ; Accept MPOS/POS/E-COM Only";
    public static final String INVALID_INPUT = "INVALID INPUT ; ";
    public static final String INVALID_TXN_AMOUNT = "INVALID AMOUNT ; AMOUNT Should be Greater than Zero";
    public static final String INVALID_ONUSOFFUS = "INVALID ONUS_OFFUS_INDICATOR; Accept ONUS/OFFUS Only";
    public static final String INVALID_SMSDMS = "INVALID SMS_DMS_INDICATOR ; Accept SMS/DMS Only";
    public static final String INVALID_NETWORK = "INVALID NETWORK ; Accept MASTERCARD/VISA/RUPAY/AMEX/UAESWITCH Only";
    public static final String INVALID_MECATEGORY = "INVALID MERCHANT_CATEGORY_TYPE ; Accept S/B Only";
    public static final String MISSING_FILE_NAME = "INVALID FILE NAME;Filename is missing";
    public static final String INVALID_FILE_NAME = "INVALID FILE NAME;Filename exceeds the allowed length";
    public static final String INVALID_AUTH_AMOUNT = "INVALID AUTH AMOUNT; AUTH AMOUNT is mandatory for MTI 0130";
    public static final String INVALID_AMOUNT = "INVALID AUTH AMOUNT; AUTH AMOUNT must be numeric value";
    public static final String INVALID_REQUEST_FIELDS = "INVALID_REQUEST_FIELDS; Please provide valid request fields.";
    public static final String MESSAGE = "message";
    public static final String INVALID_TRANSACTION = "INVALID_TRANSACTION;original transaction not found.";
    public static final String INVALID_SUBROUTE = "INVALID SUBROUTE ; Accept MASTERCARD/VISA/RUPAY/AMEX/UAESWITCH/JAYWAN/omannet_tps/ONUS/MERCURY Only";
    public static final String INVALID_SCHEME = "INVALID SCHEME ; Accept MASTERCARD/VISA/RUPAY/AMEX/UAESWITCH/JAYWAN/ONUS/DISCOVER/DINERS/MERCURY Only";
    public static final String DUPLICATE_TRANSACTION = "DUPLICATE_TRANSACTION;duplicate transaction not allowed";
    
    private APIConstants() {
    }
    
    static {
        CALLBACK_SUCCESS = 'S';
        CALLBACK_PENDING = 'P';
        CALLBACK_FAILED = 'F';
    }
}
