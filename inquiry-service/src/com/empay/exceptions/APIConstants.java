/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.APIConstants
 */
package com.empay.exceptions;

public class APIConstants {
    public static final String INVALID_FILE_NAME = "INVALID_FILE_NAME;Filename exceeds the allowed length";
    public static final String MISSING_FILE_NAME = "MISSING_FILE_NAME;Filename is missing";
    public static final String INVALID_RRN = "INVALID_RRN;RRN exceeds the allowed length";
    public static final String MISSING_RRN = "MISSING_RRN;RRN is missing";
    public static final String MISSING_BUSINESS_DATE = "MISSING_BUSINESS_DATE;BUSINESS DATE is missing";
    public static final String INVALID_BUSS_DATE = "INVALID_BUSS_DATE;BUSINESS DATE exceeds the allowed length";
    public static final String INVALID_BUSS_DATE_FORMAT = "INVALID_BUSS_DATE_FORMAT; BUSINESS DATE format incorrect";
    public static final String MISSING_NETWORK = "MISSING_NETWORK;NETWORK is missing";
    public static final String INVALID_NETWORK = "INVALID_NETWORK;NETWORK is not recognized";
    public static final String MISSING_SCHEME = "MISSING_SCHEME;SCHEME is not recognized";
    public static final String STATUS = "status";
    public static final String FILE_FORMAT = "fileFormat";
    public static final String FILE_NAME = "fileName";
    public static final String BUSINESS_DATE = "businessDate";
    public static final String ACCEPTED_COUNT = "acceptedCount";
    public static final String REJECTED_COUNT = "rejectedCount";
    public static final String INTERFACE = "interface";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String PROCESSED_DATE = "processedDate";
    public static final String IRD_CODE = "irdCode";
    public static final String FIXED = "fixed";
    public static final String PERCENTAGE = "percentage";
    public static final String AMOUNT = "amount";
    public static final String DOM_INTL_FLAG = "domIntlFlag";
    public static final String CARD_TYPE = "cardType";
    public static final String DESCRIPTION = "description";
    public static final String GENERATED_DATE = "generatedDate";
    public static final String INVALID_CONFIGURATION = "INVALID_CONFIGURATION; The provided configuration is invalid.";
    public static final String INVALID_FILE = "INVALID_FILE; Please provide a valid file name";
    public static final String MESSAGE = "message";
    public static final String FILE_ID = "fileId";
    public static final String FOR_CODE_NULL = "FOR_CODE_NULL;";
    public static final String TOTALCOUNT = "totalCount";
    public static final String TOTALPAGE = "totalPage";
    public static final String INVALID_REQUEST_FIELDS = "INVALID_REQUEST_FIELDS; Please provide valid request fields.";
    public static final String UNAUTHORIZED = "UNAUTHORIZED;";
    public static final String BATCH_NUMBER = "INVALID_BATCH_NUMBER; Batch number must be between 1 and 24";
    public static final String TXN_DATE = "INVALID_TXN_DATE FORMAT; TXN_DATE must be in YYYYMMDD format";
    public static final String TXN_ID = "transactionId";
    public static final String IRF_AMOUNT = "interchangeFeeAmount";
    public static final String FEE_SIGN = "interchangeFeeSign";
    public static final String FEE_DESC = "feeDescriptor";
    public static final String MATCH_FLAG = "interchangeMatched";
    public static final String IRF_DIFF_AMOUNT = "interchangeDifferenceAmount";
    public static final String TRACE_ID = "traceId";
    public static final String TXN_AMOUNT_AED = "transactionAmountAED";
    public static final String RECON_AMOUNT_USD = "reconciliationAmountUSD";
    public static final String IRF_AMOUNT_AED = "interchangeAmountAED";
    public static final String IRF_AMOUNT_USD = "interchangeAmountUSD";
    public static final String IRF_DESIGNATOR = "interchangeRateDesignator";
    public static final String RRN = "rrn";
    public static final String REPORT_NAME = "reportName";
    public static final String REPORT_TYPE = "reportType";
    public static final String INVALID_REPORT = "INVALID_REPORT; Please provide a valid report name";
    public static final String REPORT_PATH = "report path";

    private APIConstants() {
    }
}

