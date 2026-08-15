/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity
 *  com.empay.mercury.service.MercuryFileService
 *  com.empay.mercury.serviceImpl.MercuryFileServiceImpl
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.mercury.serviceImpl;

import com.empay.cryptapi.DecryptResponseVo;
import com.empay.mercury.entities.MercuryAcqTxnWorkEntity;
import com.empay.mercury.service.MercuryFileService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/*
 * Exception performing whole class analysis ignored.
 */
@Service
public class MercuryFileServiceImpl
implements MercuryFileService {
    private static final Logger log = LoggerFactory.getLogger(MercuryFileServiceImpl.class);
    private final Environment env;
    private static final String TRANS = "FRRC";
    private static final String SFTER = "RK";
    private static final String DFTER = "MP";
    private static final String RECAP_CURRENCY = "AED";
    private static final int MAX_BATCH_RECORDS = 60;
    private BigDecimal multiplier = BigDecimal.valueOf(100L);
    private static final DateTimeFormatter RECAP_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyy");
    private static final DateTimeFormatter TXN_DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMdd");

    public String writeMercuryFile(List<MercuryAcqTxnWorkEntity> txnList, String insShortName, String fileName, String recapNumber, DecryptResponseVo response) {
        if (txnList == null || txnList.isEmpty()) {
            log.info("No Mercury transactions found for file generation");
            return null;
        }
        String currencyCode = Objects.requireNonNull(this.env.getProperty("CURRENCY_CODE_KAFKA"), "NIL");
        if (currencyCode.equals("NIL")) {
            return null;
        }
        Currency currency = Currency.getInstance(StringUtils.substring((String)currencyCode, (int)0, (int)3));
        int fractionDigits = currency.getDefaultFractionDigits();
        this.multiplier = BigDecimal.TEN.pow(fractionDigits);
        String recapDate = LocalDate.now().format(RECAP_DATE_FORMAT);
        ArrayList<StringBuilder> lines = new ArrayList<StringBuilder>();
        int batchNo = 1;
        int seqNo = 1;
        int batchTxnCount = 0;
        int batchCreditCount = 0;
        int batchDebitCount = 0;
        int recapCreditCount = 0;
        int recapDebitCount = 0;
        BigDecimal batchCreditAmount = BigDecimal.ZERO;
        BigDecimal batchDebitAmount = BigDecimal.ZERO;
        BigDecimal recapCreditAmount = BigDecimal.ZERO;
        BigDecimal recapDebitAmount = BigDecimal.ZERO;
        lines.add(this.buildUX(recapNumber, recapDate));
        lines.add(this.buildUH(recapNumber, this.format(batchNo), recapDate));
        for (MercuryAcqTxnWorkEntity txn : txnList) {
            if (batchTxnCount == 60) {
                lines.add(this.buildUT(recapNumber, this.format(batchNo), batchCreditCount, batchCreditAmount, batchDebitCount, batchDebitAmount));
                ++batchNo;
                seqNo = 1;
                batchTxnCount = 0;
                batchCreditCount = 0;
                batchDebitCount = 0;
                batchCreditAmount = BigDecimal.ZERO;
                batchDebitAmount = BigDecimal.ZERO;
                lines.add(this.buildUH(recapNumber, this.format(batchNo), recapDate));
            }
            String batchNumber = this.format(batchNo);
            String sequenceNumber = this.format(seqNo);
            lines.add(this.buildXD(txn, recapNumber, batchNumber, sequenceNumber, response));
            if (this.isXMRequired(txn.getPosEntryMode())) {
                lines.add(this.buildXM(txn, recapNumber, batchNumber, sequenceNumber));
            }
            if (this.isXCRequired(txn)) {
                lines.add(this.buildXC(txn, recapNumber, batchNumber, sequenceNumber));
            }
            if (this.isMCRequired(txn)) {
                lines.add(this.buildMC(txn, recapNumber, batchNumber, sequenceNumber));
            }
            BigDecimal txnAmount = this.amount(txn.getTxnAmount());
            ++batchTxnCount;
            ++seqNo;
            if (this.isCreditTxn(txn)) {
                ++batchCreditCount;
                batchCreditAmount = batchCreditAmount.add(txnAmount);
                ++recapCreditCount;
                recapCreditAmount = recapCreditAmount.add(txnAmount);
                continue;
            }
            ++batchDebitCount;
            batchDebitAmount = batchDebitAmount.add(txnAmount);
            ++recapDebitCount;
            recapDebitAmount = recapDebitAmount.add(txnAmount);
        }
        lines.add(this.buildUT(recapNumber, this.format(batchNo), batchCreditCount, batchCreditAmount, batchDebitCount, batchDebitAmount));
        lines.add(this.buildUY(recapNumber, recapCreditCount, recapCreditAmount, recapDebitCount, recapDebitAmount));
        log.info("Total Mercury file lines : {}", (Object)lines.size());
        log.info("fileName : {}", (Object)fileName);
        return this.writeLinesToFile(lines, insShortName, fileName);
    }

    private boolean isCreditTxn(MercuryAcqTxnWorkEntity txn) {
        return switch (txn.getChargeType()) {
            case "TF", "TG", "TJ", "TL" -> true;
            default -> false;
        };
    }

    private String writeLinesToFile(List<StringBuilder> lines, String insShortName, String fileName) {
        try {
            String filePath = this.env.getProperty("RECON_OUT_" + insShortName);
            File file = new File(filePath + fileName);
            if (Objects.nonNull(lines)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
                    for (StringBuilder line : lines) {
                        writer.write(line.toString());
                        writer.newLine();
                    }
                }
                return fileName;
            }
            return null;
        }
        catch (IOException e) {
            log.error("Error in writeLinesToFile():", (Throwable)e);
            return null;
        }
    }

    private String format(int value) {
        return String.format("%03d", value);
    }

    private String format1(int value) {
        return String.format("%01d", value);
    }

    private StringBuilder record(String ... values) {
        return new StringBuilder(String.join((CharSequence)">", values));
    }

    private StringBuilder buildUX(String recapNumber, String recapDate) {
        return this.record(new String[]{"FRRC", "UX", "RK", recapNumber, "MP", "AED", recapDate});
    }

    private StringBuilder buildUH(String recapNumber, String batchNo, String recapDate) {
        return this.record(new String[]{"FRRC", "UH", "RK", recapNumber, "MP", batchNo, recapDate});
    }

    private StringBuilder buildUT(String recapNumber, String batchNo, int creditCount, BigDecimal creditAmount, int debitCount, BigDecimal debitAmount) {
        return this.record(new String[]{"FRRC", "UT", "RK", recapNumber, "MP", batchNo, this.format1(creditCount), this.amount(creditAmount), this.format1(debitCount), this.amount(debitAmount)});
    }

    private StringBuilder buildUY(String recapNumber, int creditCount, BigDecimal creditAmount, int debitCount, BigDecimal debitAmount) {
        BigDecimal netAmount = creditAmount.subtract(debitAmount);
        return this.record(new String[]{"FRRC", "UY", "RK", recapNumber, "MP", this.format1(creditCount), this.amount(creditAmount), this.format1(debitCount), this.amount(debitAmount), "01.000", this.amount(netAmount), "", "", "", "", "", ""});
    }

    private StringBuilder buildXD(MercuryAcqTxnWorkEntity txn, String recapNumber, String batchNo, String seqNo, DecryptResponseVo response) {
        return this.record(new String[]{"FRRC", "XD", "RK", recapNumber, "MP", batchNo, seqNo, (String)response.getCardNumbers().get(txn.getEncryptedCardNumber()), this.formatAmount(txn.getTxnAmount()), this.date(txn.getTxnDate()), "TS", this.text((Object)txn.getChargeType()), this.formatToFixedLength(txn.getMeName(), 36), this.formatToFixedLength(txn.getMeCity(), 26), this.text((Object)txn.getGeoArea()), "000", this.text((Object)txn.getTypeOfCharge()), this.generateReferenceNumber(), this.text((Object)txn.getApprovalCode()), this.text((Object)txn.getMerchantId()), "", "", "", this.formatToFixedLength(txn.getCardAccepStreetAddress(), 35), MercuryFileServiceImpl.text((String)txn.getMeCountry(), (int)20), this.text((Object)txn.getMePinCode()), this.trimTo(txn.getEstPhoneNumber(), 20), "", this.text((Object)txn.getMcc()), "", "", "", this.text((Object)txn.getRrn()), this.text((Object)txn.getTerminalId()), "", "", "", "", this.text((Object)txn.getChPresent()), this.text((Object)txn.getCardPresent()), this.cptrm(txn), "", "", MercuryFileServiceImpl.text((String)txn.getMercuryRefId(), (int)15), this.cardInputCapability(txn), this.formatAmount(txn.getSurchargeAmount()), "", this.acquirerGeo(txn), "", "", "", "", "", "", this.authRespCode(txn), "", "", "", "", "", "", "", "", ""});
    }

    private StringBuilder buildXM(MercuryAcqTxnWorkEntity txn, String recapNumber, String batchNo, String seqNo) {
        return this.record(new String[]{"FRRC", "XM", "RK", recapNumber, "MP", batchNo, seqNo, "001", MercuryFileServiceImpl.text((String)txn.getPanSequenceNumber(), (int)3), this.pad("", 32), MercuryFileServiceImpl.text((String)txn.getAppICProfile(), (int)4), MercuryFileServiceImpl.text((String)txn.getAppTxnCounter(), (int)4), MercuryFileServiceImpl.text((String)txn.getAppCryptogram(), (int)16), this.pad("", 4), this.amount12(txn.getCryptAmount()), this.amount12(txn.getCashBackAmount()), MercuryFileServiceImpl.text((String)txn.getCryptInfoData(), (int)2), MercuryFileServiceImpl.text((String)txn.getCvmResult(), (int)6), MercuryFileServiceImpl.text((String)txn.getDedicatedFileName(), (int)32), MercuryFileServiceImpl.text((String)txn.getIfdSerNumber(), (int)16), MercuryFileServiceImpl.text((String)txn.getIssAppData(), (int)64), MercuryFileServiceImpl.text((String)txn.getIssAuthData(), (int)32), this.pad("", 50), MercuryFileServiceImpl.text((String)txn.getTrlConCode(), (int)3), MercuryFileServiceImpl.text((String)txn.getTrlAppVerNumber(), (int)4), MercuryFileServiceImpl.text((String)txn.getChipTrlCapabilities(), (int)6), MercuryFileServiceImpl.text((String)txn.getChipTrlType(), (int)2), MercuryFileServiceImpl.text((String)txn.getTrlVerResult(), (int)10), MercuryFileServiceImpl.text((String)txn.getChipTxnDate(), (int)6), MercuryFileServiceImpl.text((String)txn.getChipTxnType(), (int)2), MercuryFileServiceImpl.text((String)txn.getChipCurCode(), (int)3), MercuryFileServiceImpl.text((String)txn.getUpblNumber(), (int)8)});
    }

    private StringBuilder buildXC(MercuryAcqTxnWorkEntity txn, String recapNumber, String batchNo, String seqNo) {
        return this.record(new String[]{"FRRC", "XC", "RK", recapNumber, "MP", batchNo, seqNo, "001", this.localTime(txn.getLocalDateTime()), this.localDate(txn.getLocalDateTime()), this.localTime(txn.getLocalDateTime()), this.localDate(txn.getLocalDateTime()), txn.getTerminalId()});
    }

    private StringBuilder buildMC(MercuryAcqTxnWorkEntity txn, String recapNumber, String batchNo, String seqNo) {
        return this.record(new String[]{"FRRC", "MC", "RK", recapNumber, "MP", batchNo, seqNo, "001", this.amount12(txn.getCashBackAmount())});
    }

    private boolean isXMRequired(String posEntryMode) {
        return posEntryMode.startsWith("05") || posEntryMode.startsWith("07") || posEntryMode.startsWith("95");
    }

    private boolean isXCRequired(MercuryAcqTxnWorkEntity txn) {
        String chargeType = this.text((Object)txn.getChargeType()).trim();
        return "830".equals(chargeType) || "831".equals(chargeType) || "832".equals(chargeType);
    }

    private boolean isMCRequired(MercuryAcqTxnWorkEntity txn) {
        return this.amount(txn.getCashBackAmount()).compareTo(BigDecimal.ZERO) > 0;
    }

    private BigDecimal amount(Double value) {
        return value == null ? BigDecimal.ZERO : BigDecimal.valueOf(value).abs().multiply(this.multiplier);
    }

    private String generateReferenceNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    private String formatAmount(Double value) {
        if (value == null) {
            return "0";
        }
        return BigDecimal.valueOf(value).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).toPlainString();
    }

    private String amount12(Double value) {
        long amount = this.amount(value).movePointRight(2).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).longValue();
        return String.format("%012d", amount);
    }

    private String text(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private static String text(String value, int length) {
        return ("%-" + length + "." + length + "s").formatted(Objects.requireNonNullElse(value, ""));
    }

    private String date(LocalDate value) {
        return value == null ? "" : value.format(TXN_DATE_FORMAT);
    }

    private String pad(String value, int size) {
        return String.format("%-" + size + "s", value == null ? "" : value);
    }

    private String formatToFixedLength(String value, int size) {
        if (value == null) {
            return "";
        }
        return value.length() > size ? value.substring(0, size) : String.format("%-" + size + "s", value);
    }

    private String trimTo(String value, int size) {
        if (value == null) {
            return "";
        }
        return value.length() > size ? value.substring(0, size) : value;
    }

    private String cptrm(MercuryAcqTxnWorkEntity txn) {
        return txn.getCardInputMode() == null ? "" : String.valueOf(txn.getCardInputMode());
    }

    private String cardInputCapability(MercuryAcqTxnWorkEntity txn) {
        return txn.getCardInputCapability() == null ? "" : String.valueOf(txn.getCardInputCapability());
    }

    private String acquirerGeo(MercuryAcqTxnWorkEntity txn) {
        return this.text((Object)txn.getGeoArea());
    }

    private String authRespCode(MercuryAcqTxnWorkEntity txn) {
        return this.text((Object)txn.getResponseCode());
    }

    private String amount(BigDecimal value) {
        BigDecimal safeValue = value == null ? BigDecimal.ZERO : value.abs();
        return safeValue.setScale(0, RoundingMode.HALF_UP).toPlainString();
    }

    private String localTime(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    private String localDate(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DateTimeFormatter.ofPattern("yyMMdd"));
    }

    public MercuryFileServiceImpl(Environment env) {
        this.env = env;
    }
}

