// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.commonservice;

import org.apache.logging.log4j.LogManager;
import com.empay.entities.ReportGenerationLogEntity;
import java.nio.file.StandardCopyOption;
import java.nio.file.CopyOption;
import java.time.chrono.ChronoLocalDate;
import com.empay.entities.RejectedTransactionEntity;
import java.util.List;
import com.empay.tlfprocessing.vo.ResponseVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.empay.common.entities.ProcessingLogEntity;
import com.empay.common.entities.ProcessingJobsEntity;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.empay.common.entities.FileUploadLogEntity;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.format.DateTimeFormatter;
import com.empay.repositories.ReportGenerationLogRepo;
import com.empay.repositories.RejectedTransactionRepo;
import com.empay.common.repo.InterfacesRepo;
import com.empay.common.repo.ProcessLogRepo;
import com.empay.repositories.BusinessDateRepo;
import org.springframework.core.env.Environment;
import com.empay.common.repo.ProcessingJobsRepo;
import com.empay.common.repo.FileUploadRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CommonManagementsService
{
    private static final Logger log;
    FileUploadRepo uploadLogRepo;
    ProcessingJobsRepo processJobRepo;
    Environment env;
    BusinessDateRepo busRepo;
    ProcessLogRepo processLogRepo;
    InterfacesRepo intRepo;
    RejectedTransactionRepo rejTxnRepo;
    ReportGenerationLogRepo reportGenRepo;
    BusinessDateRepo businessDateRepo;
    private static final String yyyyMMdd = "yyyyMMdd";
    private DateTimeFormatter formatter;
    
    @Autowired
    CommonManagementsService(final FileUploadRepo uploadLogRepo, final ProcessingJobsRepo processJobRepo, final Environment env, final BusinessDateRepo busRepo, final ProcessLogRepo processLogRepo, final InterfacesRepo intRepo, final RejectedTransactionRepo rejTxnRepo, final ReportGenerationLogRepo reportGenRepo, final BusinessDateRepo businessDateRepo) {
        this.formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        this.uploadLogRepo = uploadLogRepo;
        this.processJobRepo = processJobRepo;
        this.env = env;
        this.busRepo = busRepo;
        this.processLogRepo = processLogRepo;
        this.intRepo = intRepo;
        this.rejTxnRepo = rejTxnRepo;
        this.reportGenRepo = reportGenRepo;
        this.businessDateRepo = businessDateRepo;
    }
    
    public boolean checkFileUploadLog(final Integer insCode) {
        try {
            final int[] uploadStatus = { 1, 9 };
            final FileUploadLogEntity entity = this.uploadLogRepo.findByInstitutionCodeAndUploadStatusIn(insCode, uploadStatus);
            if (Objects.isNull(entity)) {
                return true;
            }
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::checkFileUploadLog():", (Throwable)e);
        }
        return false;
    }
    
    public Integer insertFileUploadLog(final int insCode, final String filename, final int userSerialNumber, final int jobSerialNumber, final int intCode, final int forCode, final LocalDate businessDate) {
        try {
            final FileUploadLogEntity entity = FileUploadLogEntity.builder().lastUpdated(LocalDateTime.now()).updatedUser(userSerialNumber).institutionCode(insCode).interfaceCode(intCode).forCode(forCode).fileName(filename).uploadDate(LocalDate.now()).uploadStatus(1).processingDate(LocalDate.now()).businessDate(businessDate).jobNumber(jobSerialNumber).fileId(filename).build();
            return ((FileUploadLogEntity)this.uploadLogRepo.saveAndFlush((Object)entity)).getSerialNumber();
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::insertFileUploadLog():", (Throwable)e);
            return 0;
        }
    }
    
    public Map<String, String> checkFileExistance(final String fileName, final String insShortName) {
        final Map<String, String> responseBody = new HashMap<String, String>();
        try {
            final String path = this.env.getProperty("RECON_IN_" + insShortName);
            final Path filePath = Paths.get(path + fileName, new String[0]);
            if (!Files.exists(filePath, new LinkOption[0])) {
                responseBody.put("Message", "The file was not found at the specified path " + String.valueOf(filePath));
                return responseBody;
            }
            if (Files.size(filePath) == 0L) {
                responseBody.put("Message", String.valueOf(filePath) + " is empty");
                return responseBody;
            }
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("checkFileExistance()", (Throwable)e);
            responseBody.put("Message", "Failed at checkFileExistance()");
        }
        return responseBody;
    }
    
    public Integer getFileUpload(final Integer insCode, final Integer userSerialNumber, final String fileName) {
        final Integer jobNumber = this.insertProcessingJob(userSerialNumber, insCode);
        final int interfaceCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INTERFACE_CODE_TLF")));
        final int formatCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("FORMAT_CODE")));
        final LocalDate businessDate = this.busRepo.findByInsCode(insCode).getBusinessDate();
        if (Objects.nonNull(jobNumber) && jobNumber != 0) {
            this.insertProcessingLog(jobNumber, insCode, userSerialNumber, " Processing Started..");
            return this.insertFileUploadLog(insCode, fileName, userSerialNumber, jobNumber, interfaceCode, formatCode, businessDate);
        }
        return 0;
    }
    
    public Integer insertProcessingJob(final int user, final int insCode) {
        try {
            final ProcessingJobsEntity entity = ProcessingJobsEntity.builder().lastUpdated(LocalDateTime.now()).user(user).insCode(insCode).processRefNumber(1).processName("TLF FILE PROCESSING").processStartTime(LocalDateTime.now()).build();
            return ((ProcessingJobsEntity)this.processJobRepo.saveAndFlush((Object)entity)).getProcessSerialNo();
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::insertProcessingJob():", (Throwable)e);
            return 0;
        }
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertProcessingLog(final int jobNumber, final int institutionCode, final int userSerialNumber, final String description) {
        try {
            final ProcessingLogEntity logEntity = ProcessingLogEntity.builder().lastupdated(LocalDateTime.now()).updatedUser(userSerialNumber).institution(institutionCode).processDate(LocalDate.now()).processBatch(jobNumber).description(description).build();
            this.processLogRepo.saveAndFlush((Object)logEntity);
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::insertProcessingLog():", (Throwable)e);
        }
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean updateFileUploadLog(final int uploadSerNumber, final int status) {
        try {
            final FileUploadLogEntity entity = this.uploadLogRepo.findBySerialNumber(uploadSerNumber);
            entity.setUploadStatus(status);
            this.uploadLogRepo.saveAndFlush((Object)entity);
            return true;
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::updateFileUploadLog():", (Throwable)e);
            return false;
        }
    }
    
    public boolean updateFileUploadLog(final int jobnumber, final int status, final Integer count, final double amount) {
        try {
            final FileUploadLogEntity entity = this.uploadLogRepo.findByJobNumber(jobnumber);
            entity.setUploadStatus(status);
            entity.setTotalTxnCount(count);
            entity.setTotalAcceptedTxnCount(count);
            entity.setTotalTxnAmount(amount);
            entity.setTotalacceptTxnAmount(amount);
            this.uploadLogRepo.saveAndFlush((Object)entity);
            return true;
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::updateFileUploadLog():", (Throwable)e);
            return false;
        }
    }
    
    public RejectedTransactionEntity insertRejectedTxns(final ResponseVo responseVo, final List<String> testerror, final String txnDateTime, final Integer jobNumber, final String type, final String fileName) {
        final RejectedTransactionEntity entity = new RejectedTransactionEntity();
        try {
            Integer intCode;
            if (type.equals("KAFKA")) {
                intCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INTERFACE_CODE_KAFKA"), "Interface Code is Null"));
            }
            else {
                intCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INTERFACE_CODE_TLF"), "Interface Code is Null"));
            }
            final Integer insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("insCode"), "Institution code is Null"));
            final Integer user = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("userSerNumber"), "User serial Number is Null"));
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUser(user);
            entity.setInsCode(insCode);
            entity.setIntCode(intCode);
            entity.setBussDate(this.businessDateRepo.findByInsCode(insCode).getBusinessDate());
            entity.setJobNumber(jobNumber);
            entity.setFileName(fileName);
            entity.setRrn(responseVo.getRrn());
            final String amountStr = responseVo.getAmountTransaction();
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                entity.setAmount(Double.valueOf(amountStr));
            }
            else {
                entity.setAmount(0.0);
            }
            if (txnDateTime != null && !txnDateTime.isBlank()) {
                entity.setTxnDateTime(this.updateTxnDatetime(txnDateTime));
            }
            else {
                CommonManagementsService.log.warn("Missing txnDateTime for RRN: {}", (Object)responseVo.getRrn());
            }
            entity.setMid(responseVo.getCardAcceptorId());
            entity.setTid(responseVo.getCardAcceptorTid());
            final String rawError = (testerror != null && !testerror.isEmpty()) ? testerror.get(0) : "Unknown error";
            String firstLine = rawError.split("\n", 2)[0].trim();
            if (firstLine.length() > 180) {
                firstLine = firstLine.substring(0, 177) + "...";
            }
            entity.setRejReason(firstLine);
            this.rejTxnRepo.saveAndFlush((Object)entity);
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error : insertRejectedTxns() :", (Throwable)e);
        }
        return entity;
    }
    
    public LocalDateTime updateTxnDatetime(final String dateTime) {
        Integer year = LocalDateTime.now().getYear();
        final String txnDate = year + dateTime.substring(0, 4);
        final LocalDate dateToCheck = LocalDate.parse(txnDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (dateToCheck.isAfter(LocalDate.now())) {
            --year;
        }
        final String stringTobePadded = year + dateTime;
        final StringBuilder sb = new StringBuilder(stringTobePadded);
        if (stringTobePadded.length() < 14) {
            sb.append("0");
        }
        final String paddedString = sb.toString();
        return LocalDateTime.parse(paddedString.substring(0, 8) + " " + paddedString.substring(8, 10) + ":" + String.valueOf(paddedString.subSequence(10, 12)) + ":" + paddedString.substring(12, 14), this.formatter);
    }
    
    public void moveFile(final String inputPath, final String fileName, final String insShortName, final String property) {
        try {
            final String destinationPath = this.env.getProperty(property + insShortName) + fileName;
            final String sourcePath = this.env.getProperty(inputPath + insShortName) + fileName;
            final Path source = Paths.get(sourcePath, new String[0]);
            final Path destination = Paths.get(destinationPath, new String[0]);
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            CommonManagementsService.log.info("FILE MOVED SUCCESSFULLY | FILENAME :{}.", (Object)fileName);
        }
        catch (final Exception e) {
            CommonManagementsService.log.info("FAILED TO MOVE FILE :{}", (Object)fileName);
            CommonManagementsService.log.error("Error moveFile() :", (Throwable)e);
        }
    }
    
    public void updateProcessingJob(final int jobNumber, final int status) {
        try {
            final ProcessingJobsEntity entity = this.processJobRepo.findByProcessSerialNo(jobNumber);
            entity.setStatus(status);
            entity.setProcessEndTime(LocalDateTime.now());
            this.processJobRepo.saveAndFlush((Object)entity);
            this.processJobRepo.flush();
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error updateProcessingJob() :", (Throwable)e);
        }
    }
    
    public void insertReportGenLog(final int jobNumber, final int institutionCode, final int userSerialNumber, final String description, final String fileName) {
        try {
            final ReportGenerationLogEntity reportGenEntity = ReportGenerationLogEntity.builder().lastUpdated(LocalDateTime.now()).updatedUser(userSerialNumber).institutionCode(institutionCode).prjSerNumber(jobNumber).reportDate(LocalDate.now()).reportCode("REFUND").reportName(description).fileName(fileName).reportStatus(4).build();
            this.reportGenRepo.saveAndFlush((Object)reportGenEntity);
        }
        catch (final Exception e) {
            CommonManagementsService.log.error("Error::insertReportGenLog():", (Throwable)e);
        }
    }
    
    static {
        log = LogManager.getLogger((Class)CommonManagementsService.class);
    }
}
