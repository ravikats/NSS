/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  com.empay.amex.repo.AmexTxnWorkRepo
 *  com.empay.common.entity.OutGoingFileProcessingEntity
 *  com.empay.common.entity.OutgoingSummaryEntity
 *  com.empay.common.repo.OutgoingSummaryRepo
 *  com.empay.jaywan.entities.JaywanAcqTxnWorkEntity
 *  com.empay.jaywan.repo.JWNAcqTxnWorkRepo
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity
 *  com.empay.mercury.repo.MercuryAcqTxnWorkRepo
 *  com.empay.staging.entities.McAcqTxnWorkEntity
 *  com.empay.staging.entities.McGCOTxnWorkEntity
 *  com.empay.staging.entities.VisaAcqTxnWorkEntity
 *  com.empay.staging.entities.VisaGOCWorkEntity
 *  com.empay.staging.repo.McAcqTxnWorkRepo
 *  com.empay.staging.repo.McGCOWorkRepo
 *  com.empay.staging.repo.VisaAcqTxnWorkRepo
 *  com.empay.staging.repo.VisaGOCTxnRepo
 *  com.empay.staging.service.IOutGoingSummaryService
 *  com.empay.staging.service.PDFService
 *  com.empay.staging.serviceImpl.OutGoingSummaryService
 *  com.lowagie.text.Document
 *  com.lowagie.text.Element
 *  com.lowagie.text.Font
 *  com.lowagie.text.Image
 *  com.lowagie.text.Paragraph
 *  com.lowagie.text.pdf.PdfPTable
 *  com.lowagie.text.pdf.PdfWriter
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.serviceImpl;

import com.empay.amex.entities.AmexAcqTxnWorkEntity;
import com.empay.amex.repo.AmexTxnWorkRepo;
import com.empay.common.entity.OutGoingFileProcessingEntity;
import com.empay.common.entity.OutgoingSummaryEntity;
import com.empay.common.repo.OutgoingSummaryRepo;
import com.empay.jaywan.entities.JaywanAcqTxnWorkEntity;
import com.empay.jaywan.repo.JWNAcqTxnWorkRepo;
import com.empay.mercury.entities.MercuryAcqTxnWorkEntity;
import com.empay.mercury.repo.MercuryAcqTxnWorkRepo;
import com.empay.staging.entities.McAcqTxnWorkEntity;
import com.empay.staging.entities.McGCOTxnWorkEntity;
import com.empay.staging.entities.VisaAcqTxnWorkEntity;
import com.empay.staging.entities.VisaGOCWorkEntity;
import com.empay.staging.repo.McAcqTxnWorkRepo;
import com.empay.staging.repo.McGCOWorkRepo;
import com.empay.staging.repo.VisaAcqTxnWorkRepo;
import com.empay.staging.repo.VisaGOCTxnRepo;
import com.empay.staging.service.IOutGoingSummaryService;
import com.empay.staging.service.PDFService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OutGoingSummaryService
implements IOutGoingSummaryService {
    private static final Logger log = LogManager.getLogger(OutGoingSummaryService.class);
    private final Environment env;
    private final PDFService pdfService;
    private final OutgoingSummaryRepo outgoingSummaryRepo;
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final VisaAcqTxnWorkRepo visaAcqTxnWorkRepo;
    private final McGCOWorkRepo mcGCOWorkRepo;
    private final VisaGOCTxnRepo visaGOCTxnRepo;
    private final AmexTxnWorkRepo amexTxnWorkRepo;
    private final JWNAcqTxnWorkRepo jwnAcqTxnWorkRepo;
    private final MercuryAcqTxnWorkRepo mercuryTxnWorkRepo;
    static DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
    private static final NumberFormat countFormat = new DecimalFormat("#,##0", decimalFormatSymbols);
    private static final DecimalFormat amountFormat = new DecimalFormat("#,##,##0.000", decimalFormatSymbols);

    public void generateOutgoingSummaryPDF(int user, int insCode, int intCode, int outFileLogSerialNumber, String intCategory, String insShortName, OutGoingFileProcessingEntity outGoingFileProcEntity, String fileType) {
        log.info("generateOutgoingSummaryPDF started...");
        Document document = new Document();
        try {
            String path = Objects.requireNonNull(this.env.getProperty("RECON_REPORTS_" + insShortName), "The property RECON_REPORTS not found!");
            Object fileName = outGoingFileProcEntity.getFileName() + "Summary.pdf";
            if (fileName != null) {
                fileName = ((String)fileName).replace(".xml", "");
            }
            this.insertIntoOutgoingSummary(Integer.valueOf(user), Integer.valueOf(insCode), Integer.valueOf(intCode), (String)fileName, Integer.valueOf(outFileLogSerialNumber), intCategory, fileType);
            try (FileOutputStream fileOutputStream = new FileOutputStream(path + (String)fileName);){
                PdfPTable pdfTable;
                PdfWriter.getInstance((Document)document, (OutputStream)fileOutputStream);
                document.open();
                Font titleFont = new Font(2, 14.0f, 1);
                Font subTitleFont = new Font(2, 12.0f);
                Font dataFont = new Font(2, 9.0f);
                Font boldDataFont = new Font(2, 9.0f, 1);
                PdfPTable imageTable = this.pdfService.defineHeadImageTable(3);
                String reconImage = Objects.requireNonNull(this.env.getProperty("RECON_IMAGE"), "The property RECON_IMAGE not found!");
                String providerUrl = reconImage + "provider.png";
                String clientUrl = reconImage + "client" + insCode + ".png";
                Image providerImage = Image.getInstance((String)providerUrl);
                Image clientImage = Image.getInstance((String)clientUrl);
                this.pdfService.WriteHeaderImage(imageTable, clientImage, "", providerImage);
                document.add((Element)imageTable);
                Paragraph header = switch (intCategory) {
                    case "MCI" -> new Paragraph("MasterCard Outgoing Summary Report", titleFont);
                    case "VISA" -> new Paragraph("Visa Outgoing Summary Report", titleFont);
                    case "AMEX" -> new Paragraph("Amex Outgoing Summary Report", titleFont);
                    case "RUPAY" -> new Paragraph("Rupay Outgoing Summary Report", titleFont);
                    case "JAYWAN" -> new Paragraph("Jaywan Outgoing Summary Report", titleFont);
                    case "MERCURY" -> new Paragraph("Mercury Outgoing Summary Report", titleFont);
                    default -> throw new IllegalStateException("Unexpected value :" + intCategory);
                };
                header.setAlignment(1);
                document.add((Element)header);
                Paragraph subHead = new Paragraph("Report Date: " + String.valueOf(outGoingFileProcEntity.getBusinessDate()), subTitleFont);
                subHead.setAlignment(0);
                document.add((Element)subHead);
                if (intCategory.equals("MCI") || intCategory.equals("RUPAY") || intCategory.equals("AMEX") || intCategory.equals("JAYWAN") || intCategory.equals("OMANNET")) {
                    subHead = new Paragraph("File ID        : " + outGoingFileProcEntity.getFileId(), subTitleFont);
                    subHead.setAlignment(0);
                    document.add((Element)subHead);
                }
                subHead = new Paragraph("File Name   : " + outGoingFileProcEntity.getFileName(), subTitleFont);
                subHead.setAlignment(0);
                document.add((Element)subHead);
                if (intCategory.equals("MCI") || intCategory.equals("OMANNET")) {
                    pdfTable = new PdfPTable(7);
                    pdfTable.setWidths(new float[]{10.0f, 10.0f, 10.0f, 20.0f, 20.0f, 20.0f, 20.0f});
                } else if (intCategory.equals("RUPAY") || intCategory.equals("VISA")) {
                    pdfTable = new PdfPTable(6);
                    pdfTable.setWidths(new float[]{10.0f, 10.0f, 20.0f, 20.0f, 20.0f, 20.0f});
                } else {
                    pdfTable = new PdfPTable(5);
                    pdfTable.setWidths(new float[]{10.0f, 20.0f, 20.0f, 20.0f, 20.0f});
                }
                pdfTable.setHorizontalAlignment(0);
                pdfTable.setSpacingBefore(10.0f);
                pdfTable.setSpacingAfter(10.0f);
                pdfTable.setWidthPercentage(100.0f);
                switch (intCategory) {
                    case "MCI": {
                        this.pdfService.AddCenterCell(pdfTable, "MTI", boldDataFont);
                        this.pdfService.AddCenterCell(pdfTable, "Function Code", boldDataFont);
                        this.pdfService.AddCenterCell(pdfTable, "Proc Code", boldDataFont);
                        break;
                    }
                    case "RUPAY": {
                        this.pdfService.AddCenterCell(pdfTable, "MTI", boldDataFont);
                        this.pdfService.AddCenterCell(pdfTable, "Function Code", boldDataFont);
                        break;
                    }
                    case "VISA": {
                        this.pdfService.AddCenterCell(pdfTable, "TC", boldDataFont);
                        this.pdfService.AddCenterCell(pdfTable, "Usage Code", boldDataFont);
                        break;
                    }
                    case "AMEX": {
                        this.pdfService.AddCenterCell(pdfTable, "Proc Code", boldDataFont);
                        break;
                    }
                    case "JAYWAN": {
                        this.pdfService.AddCenterCell(pdfTable, "Function Code", boldDataFont);
                        break;
                    }
                    case "MERCURY": {
                        this.pdfService.AddCenterCell(pdfTable, "Function Code", boldDataFont);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("Unexpected value: " + intCategory);
                    }
                }
                this.pdfService.AddCenterCell(pdfTable, "Txn Count", boldDataFont);
                this.pdfService.AddCenterCell(pdfTable, "Txn Amount", boldDataFont);
                this.pdfService.AddCenterCell(pdfTable, "Surcharge", boldDataFont);
                this.pdfService.AddCenterCell(pdfTable, "Net Amount", boldDataFont);
                List summaryEntities = this.outgoingSummaryRepo.findByInstitutionAndInterfaceCodeAndRefSerialNumber(insCode, intCode, outFileLogSerialNumber);
                for (OutgoingSummaryEntity row : summaryEntities) {
                    switch (intCategory) {
                        case "MCI": {
                            this.pdfService.AddCenterCell(pdfTable, row.getMessageTypeId(), dataFont);
                            this.pdfService.AddCenterCell(pdfTable, row.getFunctionCode(), dataFont);
                            this.pdfService.AddCenterCell(pdfTable, row.getProcCode(), dataFont);
                            break;
                        }
                        case "RUPAY": 
                        case "VISA": {
                            this.pdfService.AddCenterCell(pdfTable, row.getMessageTypeId(), dataFont);
                            this.pdfService.AddCenterCell(pdfTable, row.getFunctionCode(), dataFont);
                            break;
                        }
                        case "AMEX": {
                            this.pdfService.AddCenterCell(pdfTable, row.getProcCode(), dataFont);
                            break;
                        }
                        case "JAYWAN": {
                            this.pdfService.AddCenterCell(pdfTable, row.getFunctionCode(), dataFont);
                            break;
                        }
                        case "MERCURY": {
                            this.pdfService.AddCenterCell(pdfTable, row.getFunctionCode(), dataFont);
                            break;
                        }
                        default: {
                            throw new IllegalStateException("Unexpected value: " + intCategory);
                        }
                    }
                    this.pdfService.AddRightCell(pdfTable, countFormat.format(row.getCount()), dataFont);
                    this.pdfService.AddRightCell(pdfTable, amountFormat.format(row.getTxnAmount()), dataFont);
                    this.pdfService.AddRightCell(pdfTable, amountFormat.format(row.getSurchargeAmount()), dataFont);
                    this.pdfService.AddRightCell(pdfTable, amountFormat.format(row.getNetAmount()), dataFont);
                }
                document.add((Element)pdfTable);
                document.setMargins(180.0f, 108.0f, 72.0f, 36.0f);
                document.close();
                log.info("Outgoing summary pdf file hs been generated, filename :{}, fileID :{}", fileName, (Object)outGoingFileProcEntity.getFileId());
            }
        }
        catch (Exception e) {
            log.error("Error in generateOutgoingSummaryPDF: ", (Throwable)e);
        }
        log.info("generateOutgoingSummaryPDF completed.");
    }

    private void insertIntoOutgoingSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber, String intCategory, String fileType) {
        try {
            if ("MCI".equalsIgnoreCase(intCategory)) {
                if ("GCO".equalsIgnoreCase(fileType)) {
                    this.processMcGcoSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
                } else {
                    this.processMcAcqSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
                }
            } else if ("VISA".equalsIgnoreCase(intCategory)) {
                if ("GOC".equalsIgnoreCase(fileType)) {
                    this.processVisaGocSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
                } else {
                    this.processVisaAcqSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
                }
            } else if ("AMEX".equalsIgnoreCase(intCategory)) {
                this.processAmexSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
            } else if ("JAYWAN".equalsIgnoreCase(intCategory)) {
                this.processJaywanSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
            } else if ("MERCURY".equalsIgnoreCase(intCategory)) {
                this.processMercurySummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
            }
        }
        catch (Exception e) {
            log.error("Error in insertIntoOutgoingSummary()", (Throwable)e);
        }
    }

    private void processMercurySummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.mercuryTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGeneralStatus(insCode, intCode, 9);
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(MercuryAcqTxnWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(MercuryAcqTxnWorkEntity::getSurchargeAmount).sum();
        for (MercuryAcqTxnWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void processMcGcoSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.mcGCOWorkRepo.findByInsCodeAndIntCodeAndGeneralStatus(insCode, intCode, 9);
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(McGCOTxnWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(McGCOTxnWorkEntity::getSurchargeAmount).sum();
        for (McGCOTxnWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void processMcAcqSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.mcAcqTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGeneralStatus(insCode, intCode, 9);
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(McAcqTxnWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(McAcqTxnWorkEntity::getSurchargeAmount).sum();
        for (McAcqTxnWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void processVisaGocSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatus(insCode, intCode, 9);
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(VisaGOCWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(VisaGOCWorkEntity::getSchgAmount).sum();
        for (VisaGOCWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void processVisaAcqSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.visaAcqTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatus(insCode, intCode, 9);
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(VisaAcqTxnWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(VisaAcqTxnWorkEntity::getSchgAmount).sum();
        for (VisaAcqTxnWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void processAmexSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.amexTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatus(insCode, intCode, 9);
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(AmexAcqTxnWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(AmexAcqTxnWorkEntity::getSurchargeAmount).sum();
        for (AmexAcqTxnWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void processJaywanSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        List txnList = this.jwnAcqTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatus(insCode, intCode, Integer.valueOf(9));
        if (txnList.isEmpty()) {
            return;
        }
        int count = txnList.size();
        double totalTxnAmount = txnList.stream().mapToDouble(JaywanAcqTxnWorkEntity::getTxnAmount).sum();
        double totalSurchargeAmount = txnList.stream().mapToDouble(JaywanAcqTxnWorkEntity::getSurchargeAmount).sum();
        for (JaywanAcqTxnWorkEntity entity : txnList) {
            this.saveOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, entity.getTxnType(), Integer.valueOf(count), Double.valueOf(totalTxnAmount), Double.valueOf(totalSurchargeAmount));
        }
    }

    private void saveOutgoingSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber, String txnType, Integer count, Double totalTxnAmount, Double totalSurchargeAmount) {
        OutgoingSummaryEntity otsEntity = new OutgoingSummaryEntity();
        otsEntity.setLastupdated(LocalDateTime.now());
        otsEntity.setUpdatedUser(user.intValue());
        otsEntity.setInstitution(insCode.intValue());
        otsEntity.setInterfaceCode(intCode.intValue());
        otsEntity.setOutFileDate(LocalDate.now());
        otsEntity.setFileId(fileName);
        otsEntity.setRefSerialNumber(outgoingLogSerialNumber.intValue());
        otsEntity.setMessageTypeId(txnType);
        otsEntity.setFunctionCode("1");
        otsEntity.setProcCode("");
        otsEntity.setCount(count);
        otsEntity.setTxnAmount(totalTxnAmount);
        otsEntity.setSurchargeAmount(totalSurchargeAmount);
        otsEntity.setNetAmount(Double.valueOf(totalTxnAmount + totalSurchargeAmount));
        otsEntity.setGeneralStatus(Integer.valueOf(3));
        this.outgoingSummaryRepo.save((Object)otsEntity);
    }

    public OutGoingSummaryService(Environment env, PDFService pdfService, OutgoingSummaryRepo outgoingSummaryRepo, McAcqTxnWorkRepo mcAcqTxnWorkRepo, VisaAcqTxnWorkRepo visaAcqTxnWorkRepo, McGCOWorkRepo mcGCOWorkRepo, VisaGOCTxnRepo visaGOCTxnRepo, AmexTxnWorkRepo amexTxnWorkRepo, JWNAcqTxnWorkRepo jwnAcqTxnWorkRepo, MercuryAcqTxnWorkRepo mercuryTxnWorkRepo) {
        this.env = env;
        this.pdfService = pdfService;
        this.outgoingSummaryRepo = outgoingSummaryRepo;
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.visaAcqTxnWorkRepo = visaAcqTxnWorkRepo;
        this.mcGCOWorkRepo = mcGCOWorkRepo;
        this.visaGOCTxnRepo = visaGOCTxnRepo;
        this.amexTxnWorkRepo = amexTxnWorkRepo;
        this.jwnAcqTxnWorkRepo = jwnAcqTxnWorkRepo;
        this.mercuryTxnWorkRepo = mercuryTxnWorkRepo;
    }
}

