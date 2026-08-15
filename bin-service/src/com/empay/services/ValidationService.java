/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.interfaces.IValidationService
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.services.ValidationService
 *  com.empay.vo.BinResponseVO
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.stereotype.Service
 */
package com.empay.services;

import com.empay.interfaces.IValidationService;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.vo.BinResponseVO;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ValidationService
implements IValidationService {
    private static final Logger log = LogManager.getLogger(ValidationService.class);
    private final FileUploadLogRepository fileUploadLogRepo;

    public BinResponseVO validateFile(File file, String network, String fileName) {
        log.info("Validation started...");
        if (file == null || !file.exists()) {
            log.info("The file does not exist at the specified path: {}", (Object)(file != null ? file.getPath() : "null"));
            return this.createResponse("The file was not found at the specified path. " + (file != null ? file.getPath() : ""));
        }
        Integer inProcessCount = this.fileUploadLogRepo.countByUploadStatus(9);
        if (inProcessCount > 0) {
            log.info("FAILED_TO_PROCESS; one file in processing.");
            return this.createResponse("FAILED_TO_PROCESS; one file in processing.");
        }
        if ("VISA".equalsIgnoreCase(network)) {
            if (file.length() <= 0L) {
                log.info("The file contains no data please check the file and process again");
                return this.createResponse("The file contains no data please check the file and process again");
            }
            if (!this.isValidFileName(fileName)) {
                log.info("Invalid VISA file");
                return this.createResponse("Invalid VISA file");
            }
        }
        log.info("Validation completed successfully.");
        return null;
    }

    private BinResponseVO createResponse(String message) {
        BinResponseVO response = new BinResponseVO();
        response.setMessage(message);
        return response;
    }

    private boolean isValidFileName(String fileName) {
        return fileName != null && fileName.endsWith(".txt");
    }

    public ValidationService(FileUploadLogRepository fileUploadLogRepo) {
        this.fileUploadLogRepo = fileUploadLogRepo;
    }
}

