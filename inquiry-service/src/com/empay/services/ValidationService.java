/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.ValidationException
 *  com.empay.services.ValidationService
 *  com.empay.vo.InquiryAPIRequestVo
 *  com.empay.vo.InquiryRequestVo
 *  com.empay.vo.ReportRequestVo
 *  com.empay.vo.TxnDetailsRequestVo
 *  jakarta.validation.Valid
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.services;

import com.empay.exceptions.ValidationException;
import com.empay.vo.InquiryAPIRequestVo;
import com.empay.vo.InquiryRequestVo;
import com.empay.vo.ReportRequestVo;
import com.empay.vo.TxnDetailsRequestVo;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final Environment env;

    public void validateLength(String fileName, int length) {
        if (fileName == null || fileName.isEmpty()) {
            throw new ValidationException("MISSING_FILE_NAME;Filename is missing");
        }
        if (fileName.length() > length) {
            throw new ValidationException("INVALID_FILE_NAME;Filename exceeds the allowed length");
        }
    }

    public void validateRrn(String rrn, int length) {
        if (rrn == null || rrn.isEmpty()) {
            throw new ValidationException("MISSING_RRN;RRN is missing");
        }
        if (rrn.length() > length) {
            throw new ValidationException("INVALID_RRN;RRN exceeds the allowed length");
        }
    }

    public void validateRequest(String bussDate, int dateLength, String network, boolean validateNetwork) {
        if (Objects.nonNull(bussDate)) {
            if (bussDate.length() > dateLength) {
                throw new ValidationException("INVALID_BUSS_DATE;BUSINESS DATE exceeds the allowed length");
            }
            String dateRegex = "^(0[1-9]|[1-2][0-9]|3[01])/([0][1-9]|1[0-2])/(?!0000)[0-9]{4}$";
            Pattern datePattern = Pattern.compile(dateRegex);
            Matcher dateMatcher = datePattern.matcher(bussDate);
            if (!dateMatcher.matches()) {
                throw new ValidationException("INVALID_BUSS_DATE_FORMAT; BUSINESS DATE format incorrect");
            }
        }
        if (validateNetwork) {
            if (Objects.isNull(network) || network.isBlank()) {
                throw new ValidationException("MISSING_NETWORK;NETWORK is missing");
            }
            String networkRegex = "(?i)^(MASTERCARD|VISA|RUPAY|AMEX|JAYWAN|UAESWITCH|OMANNET|ONUS|MAAL|MERCURY)$";
            Pattern networkPattern = Pattern.compile(networkRegex);
            Matcher networkMatcher = networkPattern.matcher(network);
            if (!networkMatcher.matches()) {
                throw new ValidationException("INVALID_NETWORK;NETWORK is not recognized");
            }
        }
    }

    public boolean validateTxnCallback(@Valid TxnDetailsRequestVo requestVo) {
        return !Objects.nonNull(requestVo.getFromDate()) && !Objects.nonNull(requestVo.getToDate()) && !Objects.nonNull(requestVo.getMerchantId()) && !Objects.nonNull(requestVo.getTransactionType()) && !Objects.nonNull(requestVo.getStatus()) && !Objects.nonNull(requestVo.getDomIntlFlag()) && !Objects.nonNull(requestVo.getBankId()) && !Objects.nonNull(requestVo.getIncomingStatus()) && !Objects.nonNull(requestVo.getOutgoingStatus()) && !Objects.nonNull(requestVo.getCardAccIdCode()) && !Objects.nonNull(requestVo.getCardAccTerminalId()) && !Objects.nonNull(requestVo.getRetrievalRefNo());
    }

    public void validateTxnDetails(@Valid TxnDetailsRequestVo requestVo) {
        if (Objects.nonNull(requestVo.getFromDate()) && Objects.isNull(requestVo.getToDate()) || Objects.nonNull(requestVo.getToDate()) && Objects.isNull(requestVo.getFromDate())) {
            throw new ValidationException("Invalid date fields!");
        }
    }

    public boolean isValidAuth(String authorization) {
        return Objects.equals(Objects.requireNonNull(this.env.getProperty("authKey"), "The property authKey not found!"), authorization);
    }

    public void isValidRRN(TxnDetailsRequestVo requestVo) {
        if (Objects.isNull(requestVo.getRrn())) {
            throw new ValidationException("Please provide a valid RRN.");
        }
    }

    public void validateBatchParams(int batchNo, String txnDate, String authorization) {
        if (batchNo < 1 || batchNo > 24) {
            throw new ValidationException("INVALID_BATCH_NUMBER; Batch number must be between 1 and 24");
        }
        if (txnDate == null || txnDate.isBlank() || !txnDate.matches("\\d{8}")) {
            throw new ValidationException("INVALID_TXN_DATE FORMAT; TXN_DATE must be in YYYYMMDD format");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate.parse(txnDate, formatter);
        }
        catch (DateTimeParseException e) {
            throw new ValidationException("INVALID_TXN_DATE FORMAT; TXN_DATE must be in YYYYMMDD format");
        }
        if (!this.isValidBatchAPIAuth(authorization)) {
            throw new ValidationException("UNAUTHORIZED;");
        }
    }

    public boolean isValidBatchAPIAuth(String authorization) {
        return Objects.equals(Objects.requireNonNull(this.env.getProperty("batchAPIAuthKey"), "The property batchAPIAuthKey not found!"), authorization);
    }

    public void validateReportDates(@Valid ReportRequestVo requestVo) {
        if (Objects.nonNull(requestVo.getFromDate()) && Objects.isNull(requestVo.getToDate()) || Objects.nonNull(requestVo.getToDate()) && Objects.isNull(requestVo.getFromDate())) {
            throw new ValidationException("Both fromDate and toDate must be provided together.");
        }
    }

    public void validateDates(@Valid InquiryRequestVo requestVo) {
        if (Objects.nonNull(requestVo.getFromDate()) && Objects.isNull(requestVo.getToDate()) || Objects.nonNull(requestVo.getToDate()) && Objects.isNull(requestVo.getFromDate())) {
            throw new ValidationException("Both fromDate and toDate must be provided together.");
        }
    }

    public void validateChargebackDates(@Valid InquiryAPIRequestVo requestVo) {
        if (Objects.nonNull(requestVo.getFromDate()) && Objects.isNull(requestVo.getToDate()) || Objects.nonNull(requestVo.getToDate()) && Objects.isNull(requestVo.getFromDate())) {
            throw new ValidationException("Both fromDate and toDate must be provided together.");
        }
    }

    public void validateCollectionOnlyRequest(String bussDate, int dateLength, String network, boolean validateNetwork) {
        if (Objects.nonNull(bussDate)) {
            if (bussDate.length() > dateLength) {
                throw new ValidationException("INVALID_BUSS_DATE;BUSINESS DATE exceeds the allowed length");
            }
            String dateRegex = "^(0[1-9]|[1-2][0-9]|3[01])/([0][1-9]|1[0-2])/(?!0000)[0-9]{4}$";
            Pattern datePattern = Pattern.compile(dateRegex);
            Matcher dateMatcher = datePattern.matcher(bussDate);
            if (!dateMatcher.matches()) {
                throw new ValidationException("INVALID_BUSS_DATE_FORMAT; BUSINESS DATE format incorrect");
            }
        }
        if (validateNetwork) {
            if (Objects.isNull(network) || network.isBlank()) {
                throw new ValidationException("MISSING_NETWORK;NETWORK is missing");
            }
            String networkRegex = "(?i)^(MASTERCARD|VISA)$";
            Pattern networkPattern = Pattern.compile(networkRegex);
            Matcher networkMatcher = networkPattern.matcher(network);
            if (!networkMatcher.matches()) {
                throw new ValidationException("INVALID_NETWORK;NETWORK is not recognized");
            }
        }
    }

    public ValidationService(Environment env) {
        this.env = env;
    }
}

