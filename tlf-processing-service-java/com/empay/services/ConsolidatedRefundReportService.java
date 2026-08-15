// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import org.apache.commons.csv.CSVPrinter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.csv.CSVFormat;
import com.empay.entities.PosTransactionEntity;
import java.util.Optional;
import java.util.Iterator;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.Path;
import com.empay.entities.UAERefundTranasactionEntity;
import java.util.List;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.empay.tlfprocessing.vo.RefundReportVo;
import java.time.format.DateTimeFormatter;
import org.springframework.core.env.Environment;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import com.empay.commonservice.CommonManagementsService;
import com.empay.repositories.PosTransactionRepository;
import com.empay.repositories.UAERefundTransactionRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ConsolidatedRefundReportService
{
    private static final Logger log;
    private final UAERefundTransactionRepo refundRepo;
    private final PosTransactionRepository posRepo;
    private final CommonManagementsService commonService;
    @PersistenceContext
    private EntityManager entityManager;
    private final Environment env;
    private static final DateTimeFormatter DATE_TIME_FORMATTER;
    DateTimeFormatter dateFormat;
    DateTimeFormatter timeFormat;
    
    @Transactional
    public Map<String, String> generateConsolidatedRefundReport(final RefundReportVo reportVo, final Integer insCode, final Integer jobNumber, final Integer userSerNumber, final String insShortName, final String institutionID, final String BankID) {
        ConsolidatedRefundReportService.log.info("Consolidated Refund Report started..................");
        final Map<String, String> response = new HashMap<String, String>();
        final List<String> generatedFiles = new ArrayList<String>();
        final int maxRecordsPerFile = 200;
        try {
            final LocalDateTime parsedFromDate = LocalDateTime.parse(reportVo.getFromDate(), ConsolidatedRefundReportService.DATE_TIME_FORMATTER);
            final LocalDateTime parsedToDate = LocalDateTime.parse(reportVo.getToDate(), ConsolidatedRefundReportService.DATE_TIME_FORMATTER);
            final List<UAERefundTranasactionEntity> refundDataList = this.refundRepo.findByGenStatusAndLocalDateTimeBetween(3, parsedFromDate, parsedToDate);
            if (refundDataList == null || refundDataList.isEmpty()) {
                response.put("Message", "No Pending Refund to Generate");
                return response;
            }
            this.partition(refundDataList, 200).forEach(chunk -> {
                final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                final Long uniqueSequence = this.getNextSequenceValue();
                final String fileName = String.format("CBUAE_POS_REFUND_REQ_%s_%s_%s_%03d.csv", BankID, institutionID, timestamp, uniqueSequence);
                final Path filePath = Paths.get(this.env.getProperty("RECON_OUT_" + insShortName), fileName);
                this.generateConsolidateRefund(chunk, filePath);
                this.commonService.insertReportGenLog(jobNumber, insCode, userSerNumber, "UAE Switch Refund File", fileName);
                generatedFiles.add(fileName);
                return;
            });
            this.updateRefundedTxns(refundDataList);
            response.put("Message", "Refund Report Generated in " + generatedFiles.size() + " file(s): " + String.join(", ", generatedFiles));
            ConsolidatedRefundReportService.log.info("Consolidated Refund Report Completed ..................");
        }
        catch (final Exception e) {
            ConsolidatedRefundReportService.log.error("generateConsolidatedRefundReport() ", (Throwable)e);
            response.put("Message", "Failed To Generate Refund File");
            throw new RuntimeException("Failed to generate report or update DB", e);
        }
        return response;
    }
    
    public Long getNextSequenceValue() {
        final String query = "SELECT REFUND_FILE_SEQ.NEXTVAL FROM DUAL";
        final Object result = this.entityManager.createNativeQuery(query).getSingleResult();
        return ((Number)result).longValue();
    }
    
    public void updateRefundedTxns(final List<UAERefundTranasactionEntity> refundData) {
        ConsolidatedRefundReportService.log.info("POS and Refund matching started..................");
        for (final UAERefundTranasactionEntity refundTxn : refundData) {
            final Optional<PosTransactionEntity> posEntity = this.posRepo.findBySerialNumber(refundTxn.getTxnSerNumber());
            final Optional<UAERefundTranasactionEntity> refundStatus = this.refundRepo.findBySerialNumber(refundTxn.getSerialNumber());
            posEntity.ifPresent(entity -> {
                entity.setOutStatus("Completed");
                entity.setGenStatus(4);
                entity.setLastUpdated(LocalDateTime.now());
                this.posRepo.saveAndFlush((Object)entity);
                return;
            });
            refundStatus.ifPresent(refund -> posEntity.ifPresent(pos -> {
                refund.setGenStatus(4);
                this.refundRepo.saveAndFlush((Object)refund);
            }));
        }
        ConsolidatedRefundReportService.log.info("POS and Refund matching Completed..................");
    }
    
    private List<List<UAERefundTranasactionEntity>> partition(final List<UAERefundTranasactionEntity> list, final int size) {
        final List<List<UAERefundTranasactionEntity>> parts = new ArrayList<List<UAERefundTranasactionEntity>>();
        for (int i = 0; i < list.size(); i += size) {
            parts.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return parts;
    }
    
    private void generateConsolidateRefund(final List<UAERefundTranasactionEntity> refundDataList, final Path filePath) {
        ConsolidatedRefundReportService.log.info("Refund file generation started..................");
        final CSVFormat format = CSVFormat.DEFAULT.builder().setRecordSeparator('\n').build();
        try (final BufferedWriter writer = Files.newBufferedWriter(filePath, new OpenOption[0]);
             final CSVPrinter csvPrinter = new CSVPrinter((Appendable)writer, format)) {
            for (final UAERefundTranasactionEntity refundData : refundDataList) {
                csvPrinter.printRecord(new Object[] { refundData.getPan(), this.dateFormat.format(refundData.getLocalDateTime().toLocalDate()), this.timeFormat.format(refundData.getLocalDateTime().toLocalTime()), refundData.getTxnAmount(), refundData.getTxnCurrency(), refundData.getAcqinstIdCode(), refundData.getIssInstIdCode(), refundData.getRrn(), refundData.getStan(), refundData.getIssuingNetwork(), refundData.getMcc(), refundData.getAcqCountryCode(), refundData.getAuthCode(), refundData.getTerminalId(), refundData.getMerchantName(), refundData.getMerchantId(), refundData.getRetailerID(), "", "", "" });
            }
            csvPrinter.flush();
            ConsolidatedRefundReportService.log.info("Refund file generation Completed..................");
        }
        catch (final IOException e) {
            ConsolidatedRefundReportService.log.error("generateConsolidateRefund(): ", (Throwable)e);
            throw new RuntimeException("generateConsolidateRefund() : ", e);
        }
    }
    
    public ConsolidatedRefundReportService(final UAERefundTransactionRepo refundRepo, final PosTransactionRepository posRepo, final CommonManagementsService commonService, final Environment env) {
        this.dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        this.timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        this.refundRepo = refundRepo;
        this.posRepo = posRepo;
        this.commonService = commonService;
        this.env = env;
    }
    
    static {
        log = LogManager.getLogger((Class)ConsolidatedRefundReportService.class);
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
}
