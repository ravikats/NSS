/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.entities.ProcessingJobsEntity
 *  com.empay.entities.VisaIssAcqRangeEntity
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.repositories.ProcessingJobRepository
 *  com.empay.repositories.VisaIssAcqRangeRepository
 *  com.empay.visa.VisaARDEF
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.data.domain.Pageable
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.visa;

import com.empay.entities.FileUploadLogEntity;
import com.empay.entities.ProcessingJobsEntity;
import com.empay.entities.VisaIssAcqRangeEntity;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.repositories.ProcessingJobRepository;
import com.empay.repositories.VisaIssAcqRangeRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisaARDEF {
    private static final Logger log = LogManager.getLogger(VisaARDEF.class);
    private final Environment env;
    private final FileUploadLogRepository uploadLogRepo;
    private final ProcessingJobRepository processingJobRepo;
    private final VisaIssAcqRangeRepository visaRepo;

    @Transactional
    public void processVisaBin(String fileName, Integer userSerialNumber, Integer jobSerialNumber, Integer uploadSerialNumber, String insShortName) throws Exception {
        log.info("VISA BIN FILE PROCESSING STARTED");
        StringBuilder builder = new StringBuilder();
        ArrayList<VisaIssAcqRangeEntity> entities = new ArrayList<VisaIssAcqRangeEntity>();
        Object fName = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.env.getProperty("RECON_IN_" + insShortName) + fileName));){
            String line = "";
            int totalTransactionCount = 0;
            fName = this.env.getProperty("RECON_IN_" + insShortName) + fileName;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            String[] lines = builder.toString().split("\n");
            int batchSize = this.calculateBatchSize((long)lines.length);
            if (lines.length > 0) {
                this.deleteFromVisaIssRange(batchSize);
                for (String currentLine : lines) {
                    VisaIssAcqRangeEntity entity = new VisaIssAcqRangeEntity();
                    entity.setUpdatedDate(LocalDateTime.now());
                    entity.setUpdatedUser(userSerialNumber);
                    entity.setBinLength(Integer.valueOf(Integer.parseInt(currentLine.substring(31, 33))));
                    entity.setIssAccountRangeHigh(currentLine.substring(0, 9).trim());
                    entity.setIssAccountRangeLow(currentLine.substring(12, 21).trim());
                    entity.setBin(currentLine.substring(24, 30));
                    entity.setDomain(Character.valueOf(currentLine.substring(41, 42).charAt(0)));
                    entity.setRegion(Character.valueOf(currentLine.substring(42, 43).charAt(0)));
                    entity.setCountryAlphaCode(currentLine.substring(43, 45));
                    entity.setCardProduct(currentLine.substring(58, 60));
                    entity.setCrdrIndicator(Character.valueOf(currentLine.substring(69, 70).charAt(0)));
                    entity.setProcessingBin(currentLine.substring(35, 41));
                    entity.setProductSubTytpe(StringUtils.substring((String)currentLine, (int)74, (int)76));
                    entity.setJobSerialNumber(jobSerialNumber);
                    entities.add(entity);
                    ++totalTransactionCount;
                    if (entities.size() < batchSize) continue;
                    this.visaRepo.saveAll(entities);
                    entities.clear();
                }
                if (!entities.isEmpty()) {
                    this.visaRepo.saveAll(entities);
                }
                this.updateProcess(uploadSerialNumber, jobSerialNumber, totalTransactionCount);
                this.moveFile((String)fName, fileName, insShortName, "RECON_PROCESSED_");
                log.info("VISA ARDEF file Total count: {}", (Object)totalTransactionCount);
                log.info("FILE PROCESSING COMPLETED SUCCESSFULLY");
            } else {
                this.moveFile((String)fName, fileName, insShortName, "RECON_PROCESSED_");
                log.info("VISA ARDEF file Total count: 0");
                log.info("FILE PROCESSING COMPLETED");
            }
        }
        catch (Exception e) {
            log.error("Error in processVisaBin():", (Throwable)e);
            this.handleUploadError(uploadSerialNumber);
            this.moveFile((String)fName, fileName, insShortName, "RECON_REJECTED_");
        }
    }

    private void handleUploadError(Integer uploadSerialNumber) {
        FileUploadLogEntity entity = this.uploadLogRepo.findBySerialNumber(uploadSerialNumber);
        if (entity != null) {
            entity.setUploadStatus(Integer.valueOf(5));
            entity.setRemarks("An error occurred while attempting to read the file.");
            this.uploadLogRepo.saveAndFlush((Object)entity);
        }
    }

    private void updateProcess(Integer uploadSerialNumber, Integer jobNumber, int totalCount) {
        try {
            ProcessingJobsEntity jobEntity;
            FileUploadLogEntity uploadLogEntity = this.uploadLogRepo.findBySerialNumber(uploadSerialNumber);
            if (uploadLogEntity != null) {
                uploadLogEntity.setTotalAcceptedTxnCount(Integer.valueOf(totalCount));
                uploadLogEntity.setTotalTxnCount(Integer.valueOf(totalCount));
                uploadLogEntity.setUploadStatus(Integer.valueOf(4));
                this.uploadLogRepo.saveAndFlush((Object)uploadLogEntity);
            }
            if ((jobEntity = this.processingJobRepo.findBySerialNumber(jobNumber)) != null) {
                jobEntity.setEndTime(LocalDateTime.now());
                this.processingJobRepo.saveAndFlush((Object)jobEntity);
            }
        }
        catch (Exception e) {
            log.error("Error in updateProcess():", (Throwable)e);
        }
    }

    private void deleteFromVisaIssRange(int batchSize) {
        try {
            while (this.visaRepo.count() > 0L) {
                this.visaRepo.deleteAllInBatch((Iterable)this.visaRepo.findAll(Pageable.ofSize((int)batchSize)).toList());
            }
        }
        catch (Exception e) {
            log.error("Error in deleteFromVisaIssRange():", (Throwable)e);
        }
    }

    private void moveFile(String fName, String fileName, String insShortName, String path) {
        try {
            File file = new File(fName);
            String saveDirectory = this.env.getProperty(path + insShortName);
            boolean renamed = file.renameTo(new File(saveDirectory + fileName));
            if (renamed) {
                log.info("FILE RENAMED SUCCESSFULLY | FILENAME :{}.", (Object)fileName);
            } else {
                log.info("FAILED TO RENAME FILE :{}", (Object)fileName);
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private int calculateBatchSize(long totalFileLineCount) {
        int maxBatchSize = 2000;
        int minBatchSize = 1000;
        long calculatedBatchSize = totalFileLineCount / 10L;
        if (calculatedBatchSize > (long)maxBatchSize) {
            return maxBatchSize;
        }
        if (calculatedBatchSize < (long)minBatchSize) {
            return minBatchSize;
        }
        return (int)calculatedBatchSize;
    }

    public VisaARDEF(Environment env, FileUploadLogRepository uploadLogRepo, ProcessingJobRepository processingJobRepo, VisaIssAcqRangeRepository visaRepo) {
        this.env = env;
        this.uploadLogRepo = uploadLogRepo;
        this.processingJobRepo = processingJobRepo;
        this.visaRepo = visaRepo;
    }
}

