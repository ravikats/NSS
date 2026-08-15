/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.entities.ProcessingJobsEntity
 *  com.empay.interfaces.IBinProcessingService
 *  com.empay.interfaces.ICommonService
 *  com.empay.interfaces.IJaywanBinProcess
 *  com.empay.interfaces.IMercuryBinProcessing
 *  com.empay.interfaces.IOmanNetBinProcessing
 *  com.empay.interfaces.IValidationService
 *  com.empay.ipm.McT67Pro
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.repositories.JaywanMcIssAccRangeRepository
 *  com.empay.repositories.MCIssAcqRangeRepository
 *  com.empay.repositories.OmanNetBinRepo
 *  com.empay.repositories.ProcessingJobRepository
 *  com.empay.repositories.VisaIssAcqRangeRepository
 *  com.empay.services.BinProcessingService
 *  com.empay.services.BinProcessingService$BinFileProcessingThread
 *  com.empay.visa.VisaARDEF
 *  com.empay.vo.BinResponseVO
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.services;

import com.empay.entities.FileUploadLogEntity;
import com.empay.entities.ProcessingJobsEntity;
import com.empay.interfaces.IBinProcessingService;
import com.empay.interfaces.ICommonService;
import com.empay.interfaces.IJaywanBinProcess;
import com.empay.interfaces.IMercuryBinProcessing;
import com.empay.interfaces.IOmanNetBinProcessing;
import com.empay.interfaces.IValidationService;
import com.empay.ipm.McT67Pro;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.repositories.JaywanMcIssAccRangeRepository;
import com.empay.repositories.MCIssAcqRangeRepository;
import com.empay.repositories.OmanNetBinRepo;
import com.empay.repositories.ProcessingJobRepository;
import com.empay.repositories.VisaIssAcqRangeRepository;
import com.empay.services.BinProcessingService;
import com.empay.visa.VisaARDEF;
import com.empay.vo.BinResponseVO;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BinProcessingService
implements IBinProcessingService {
    private static final Logger log = LogManager.getLogger(BinProcessingService.class);
    private final ProcessingJobRepository processingJobRepo;
    private final McT67Pro mcT67Pro;
    private final ICommonService commonService;
    private final IValidationService validationService;
    private final FileUploadLogRepository fileUploadLogRepo;
    private final MCIssAcqRangeRepository mcIssAcqRangeRepo;
    private final VisaIssAcqRangeRepository visaIssRepo;
    private final VisaARDEF visaProcessing;
    private final IJaywanBinProcess jaywanBinProcess;
    private final IOmanNetBinProcessing omanNetBinProcessing;
    private final JaywanMcIssAccRangeRepository jaywanMcIssAccRangeRepo;
    private final OmanNetBinRepo omanNetBinRepo;
    private final IMercuryBinProcessing mercuryBinProcessing;

    public BinResponseVO processBin(String filename, String network, int userSerialNumber, int insCode, int intCode, int forCode, String insShortName, File file) {
        BinResponseVO binResponseVO = this.validationService.validateFile(file, network, filename);
        if (Objects.isNull(binResponseVO)) {
            Integer jobSerialNumber = this.insertProcessingJob(userSerialNumber, insCode);
            binResponseVO = new BinResponseVO();
            if (Objects.nonNull(jobSerialNumber)) {
                Integer resCode = this.commonService.insertFileUploadLog(insCode, filename, userSerialNumber, jobSerialNumber.intValue(), intCode, forCode);
                if (Objects.isNull(resCode) || resCode == 0) {
                    if (Objects.isNull(resCode)) {
                        binResponseVO.setMessage("ERROR_OCCURRED; an error occurred while inserting into file_upload_log!");
                        log.debug("DUPLICATE_FILENAME; provided filename is already exist.");
                    } else {
                        binResponseVO.setMessage("DUPLICATE_FILENAME; provided filename is already exist.");
                        log.debug("DUPLICATE_FILENAME; provided filename is already exist.");
                    }
                } else {
                    new BinFileProcessingThread(this, filename, network, jobSerialNumber.intValue(), userSerialNumber, resCode.intValue(), insShortName);
                    binResponseVO.setMessage("BIN File Processing Scheduled Successfully.");
                }
            } else {
                binResponseVO.setMessage("FAILED_TO_INSERT_JOB; failed to insert processing_job!");
            }
        }
        return binResponseVO;
    }

    private void processBinFile(String filename, String network, int jobSerialNumber, int userSerialNumber, int uploadSerialNumber, String insShortName) {
        try {
            if (this.commonService.updateFileUploadLog(jobSerialNumber, 9, null)) {
                if ("MASTERCARD".equalsIgnoreCase(network)) {
                    this.mcT67Pro.processMCBin(filename, Integer.valueOf(userSerialNumber), Integer.valueOf(jobSerialNumber), Integer.valueOf(uploadSerialNumber), insShortName);
                } else if ("VISA".equalsIgnoreCase(network)) {
                    this.visaProcessing.processVisaBin(filename, Integer.valueOf(userSerialNumber), Integer.valueOf(jobSerialNumber), Integer.valueOf(uploadSerialNumber), insShortName);
                } else if ("JAYWAN".equalsIgnoreCase(network)) {
                    this.jaywanBinProcess.processJaywanBin(filename, userSerialNumber, jobSerialNumber, uploadSerialNumber, insShortName);
                } else if ("OMANNET".equalsIgnoreCase(network)) {
                    this.omanNetBinProcessing.processOmanNetBin(filename, userSerialNumber, jobSerialNumber, uploadSerialNumber, insShortName);
                } else if ("MERCURY".equalsIgnoreCase(network)) {
                    this.mercuryBinProcessing.processMercuryBin(filename, userSerialNumber, jobSerialNumber, uploadSerialNumber, insShortName);
                }
            } else {
                log.error("Failed to update file upload log!");
            }
        }
        catch (Exception e) {
            log.error("Error::processBinFile():", (Throwable)e);
        }
    }

    private Integer insertProcessingJob(int user, int insCode) {
        try {
            ProcessingJobsEntity entity = ProcessingJobsEntity.builder().lastUpdated(LocalDateTime.now()).updatedUser(user).institutionCode(insCode).refNumber(1).processName("BIN_FILE_PROCESSING").startTime(LocalDateTime.now()).build();
            return ((ProcessingJobsEntity)this.processingJobRepo.saveAndFlush((Object)entity)).getSerialNumber();
        }
        catch (Exception e) {
            log.error("Error::insertProcessingJob():", (Throwable)e);
            return null;
        }
    }

    @Transactional
    public BinResponseVO binFileDeletion(String filename, String network) {
        BinResponseVO binResponseVO = new BinResponseVO();
        try {
            FileUploadLogEntity entity = this.fileUploadLogRepo.findByFileName(filename);
            if (Objects.isNull(entity)) {
                binResponseVO.setMessage("FILE_NOT_FOUND; there is no filename found " + filename);
            } else if (entity.getUploadStatus() != 5) {
                binResponseVO.setMessage("DELETION_NOT_ALLOWED; file deletion not allowed.");
            } else {
                if (network.equalsIgnoreCase("MASTERCARD")) {
                    this.mcIssAcqRangeRepo.deleteAllByJobSerialNumber(entity.getJobNumber());
                    this.mcIssAcqRangeRepo.flush();
                } else if (network.equalsIgnoreCase("VISA")) {
                    this.visaIssRepo.deleteAllByJobSerialNumber(entity.getJobNumber());
                    this.visaIssRepo.flush();
                } else if (network.equalsIgnoreCase("JAYWAN")) {
                    this.jaywanMcIssAccRangeRepo.deleteAllByJobNumber(entity.getJobNumber());
                    this.jaywanMcIssAccRangeRepo.flush();
                } else if (network.equalsIgnoreCase("OMANNET")) {
                    this.omanNetBinRepo.deleteAllByJobNumber(entity.getJobNumber());
                    this.omanNetBinRepo.flush();
                }
                this.fileUploadLogRepo.deleteByFileName(filename);
                this.fileUploadLogRepo.flush();
                binResponseVO.setMessage("File deleted successfully.");
            }
        }
        catch (Exception e) {
            binResponseVO.setMessage("Failed to delete file, filename :" + filename);
            log.error("error binFileDeletion() :", (Throwable)e);
        }
        return binResponseVO;
    }

    public BinProcessingService(ProcessingJobRepository processingJobRepo, McT67Pro mcT67Pro, ICommonService commonService, IValidationService validationService, FileUploadLogRepository fileUploadLogRepo, MCIssAcqRangeRepository mcIssAcqRangeRepo, VisaIssAcqRangeRepository visaIssRepo, VisaARDEF visaProcessing, IJaywanBinProcess jaywanBinProcess, IOmanNetBinProcessing omanNetBinProcessing, JaywanMcIssAccRangeRepository jaywanMcIssAccRangeRepo, OmanNetBinRepo omanNetBinRepo, IMercuryBinProcessing mercuryBinProcessing) {
        this.processingJobRepo = processingJobRepo;
        this.mcT67Pro = mcT67Pro;
        this.commonService = commonService;
        this.validationService = validationService;
        this.fileUploadLogRepo = fileUploadLogRepo;
        this.mcIssAcqRangeRepo = mcIssAcqRangeRepo;
        this.visaIssRepo = visaIssRepo;
        this.visaProcessing = visaProcessing;
        this.jaywanBinProcess = jaywanBinProcess;
        this.omanNetBinProcessing = omanNetBinProcessing;
        this.jaywanMcIssAccRangeRepo = jaywanMcIssAccRangeRepo;
        this.omanNetBinRepo = omanNetBinRepo;
        this.mercuryBinProcessing = mercuryBinProcessing;
    }
}

