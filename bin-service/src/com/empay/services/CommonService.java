/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.BusinessDateEntity
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.entities.ProcessingJobsEntity
 *  com.empay.interfaces.ICommonService
 *  com.empay.repositories.BusinessDateRepository
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.repositories.ProcessingJobRepository
 *  com.empay.services.CommonService
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.dao.DataIntegrityViolationException
 *  org.springframework.stereotype.Service
 */
package com.empay.services;

import com.empay.entities.BusinessDateEntity;
import com.empay.entities.FileUploadLogEntity;
import com.empay.entities.ProcessingJobsEntity;
import com.empay.interfaces.ICommonService;
import com.empay.repositories.BusinessDateRepository;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.repositories.ProcessingJobRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CommonService
implements ICommonService {
    private static final Logger log = LogManager.getLogger(CommonService.class);
    private final BusinessDateRepository businessDateRepo;
    private final FileUploadLogRepository uploadLogRepo;
    private final ProcessingJobRepository processingJobRepo;
    private final Environment env;

    public LocalDate convertToGregorianDate(String julianDate) {
        if (((String)julianDate).length() == 5) {
            julianDate = "20" + (String)julianDate;
        }
        int year = Integer.parseInt(((String)julianDate).substring(0, 4));
        int dayOfYear = Integer.parseInt(((String)julianDate).substring(4, 7));
        return LocalDate.ofYearDay(year, dayOfYear);
    }

    public LocalDate getBusinessDate() {
        try {
            BusinessDateEntity entity = this.businessDateRepo.findTopByOrderByInsCodeAsc();
            if (Objects.nonNull(entity)) {
                return entity.getBusinessDate();
            }
        }
        catch (Exception e) {
            log.error("Error::getBusinessDate():", (Throwable)e);
        }
        return null;
    }

    public Integer insertFileUploadLog(int insCode, String filename, int userSerialNumber, int jobSerialNumber, int intCode, int forCode) {
        try {
            FileUploadLogEntity entity = FileUploadLogEntity.builder().lastUpdated(LocalDateTime.now()).updatedUser(Integer.valueOf(userSerialNumber)).institutionCode(Integer.valueOf(insCode)).interfaceCode(Integer.valueOf(intCode)).formatCode(forCode).fileName(filename).uploadDate(LocalDate.now()).uploadStatus(Integer.valueOf(1)).processingDate(LocalDate.now()).businessDate(LocalDate.now()).jobNumber(Integer.valueOf(jobSerialNumber)).fileId(filename).build();
            return ((FileUploadLogEntity)this.uploadLogRepo.saveAndFlush((Object)entity)).getSerialNumber();
        }
        catch (DataIntegrityViolationException e1) {
            log.error("Error::processBin():Unique-Constraint-Exception::", (Object)e1.getMessage());
            return 0;
        }
        catch (Exception e) {
            log.error("Error::processBin():", (Throwable)e);
            return null;
        }
    }

    public boolean updateFileUploadLog(int jobSerialNumber, int status, String remarks) {
        try {
            FileUploadLogEntity entity = this.uploadLogRepo.findByJobNumber(jobSerialNumber);
            entity.setUploadStatus(Integer.valueOf(status));
            entity.setRemarks(remarks);
            this.uploadLogRepo.saveAndFlush((Object)entity);
            return true;
        }
        catch (Exception e) {
            log.error("Error::updateFileUploadLog():", (Throwable)e);
            return false;
        }
    }

    public void updateProcess(int uploadSerialNumber, int jobNumber, int totalCount, int accTxnCount, int status) {
        try {
            FileUploadLogEntity uploadLogEntity = this.uploadLogRepo.findBySerialNumber(Integer.valueOf(uploadSerialNumber));
            uploadLogEntity.setTotalAcceptedTxnCount(Integer.valueOf(accTxnCount));
            uploadLogEntity.setTotalTxnCount(Integer.valueOf(totalCount));
            uploadLogEntity.setUploadStatus(Integer.valueOf(status));
            this.uploadLogRepo.saveAndFlush((Object)uploadLogEntity);
            ProcessingJobsEntity jobEntity = this.processingJobRepo.findBySerialNumber(Integer.valueOf(jobNumber));
            jobEntity.setEndTime(LocalDateTime.now());
            this.processingJobRepo.saveAndFlush((Object)jobEntity);
        }
        catch (Exception e) {
            log.error("Error::updateProcess() :", (Throwable)e);
        }
    }

    public void moveFile(String sourcePath, String fileName, String insShortName, String property) {
        try {
            String destinationPath = this.env.getProperty(property + insShortName) + fileName;
            Path source = Paths.get(sourcePath, new String[0]);
            Path destination = Paths.get(destinationPath, new String[0]);
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            log.info("FILE MOVED SUCCESSFULLY | FILENAME :{}.", (Object)fileName);
        }
        catch (Exception e) {
            log.info("FAILED TO MOVE FILE :{}", (Object)fileName);
            log.error("Error moveFile() :", (Throwable)e);
        }
    }

    public CommonService(BusinessDateRepository businessDateRepo, FileUploadLogRepository uploadLogRepo, ProcessingJobRepository processingJobRepo, Environment env) {
        this.businessDateRepo = businessDateRepo;
        this.uploadLogRepo = uploadLogRepo;
        this.processingJobRepo = processingJobRepo;
        this.env = env;
    }
}

