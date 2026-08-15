/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileFormatsEntity
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.exceptions.ValidationException
 *  com.empay.repositories.FileFormatRepository
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.services.BinHistoryService
 *  com.empay.vo.RequestVo
 *  jakarta.persistence.criteria.Expression
 *  jakarta.persistence.criteria.Order
 *  jakarta.persistence.criteria.Predicate
 *  jakarta.validation.Valid
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.data.domain.Page
 *  org.springframework.data.domain.PageRequest
 *  org.springframework.data.domain.Pageable
 *  org.springframework.data.jpa.domain.Specification
 *  org.springframework.stereotype.Service
 */
package com.empay.services;

import com.empay.entities.FileFormatsEntity;
import com.empay.entities.FileUploadLogEntity;
import com.empay.exceptions.ValidationException;
import com.empay.repositories.FileFormatRepository;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.vo.RequestVo;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BinHistoryService {
    private static final Logger log = LogManager.getLogger(BinHistoryService.class);
    private final FileUploadLogRepository fileUploadLogRepository;
    private final FileFormatRepository fileFormatRepository;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final Environment env;
    IntFunction<String> getBinStatus = status -> switch (status) {
        case 4 -> "Completed";
        case 5 -> "Processing Failed";
        case 8 -> "Deletion in Processing";
        case 9 -> "In processing";
        default -> "";
    };
    ToIntFunction<String> getUploadGenStatus = status -> switch (status) {
        case "completed" -> 4;
        case "processing failed" -> 5;
        case "deletion in processing" -> 8;
        case "in processing" -> 9;
        default -> 0;
    };

    public List<Map<String, String>> getBinHist(@Valid RequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        HashMap<String, String> responseBody = new HashMap<String, String>();
        int systemForCode = 0;
        try {
            String network;
            int forCode = 0;
            switch (network = requestVo.getNetwork().toUpperCase()) {
                case "MASTERCARD": {
                    systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MC_BIN_FORMAT_CODE"), "The property MC_BIN_FORMAT_CODE not found!"));
                    FileFormatsEntity masterCardForCode = this.fileFormatRepository.findBySystemFileFormats_Code(Integer.valueOf(systemForCode));
                    if (masterCardForCode == null) {
                        responseBody.put("FOR_CODE_NULL;", "MASTERCARD FOR CODE IS NULL");
                        responseBodyList.add(responseBody);
                        return responseBodyList;
                    }
                    forCode = masterCardForCode.getCode();
                    break;
                }
                case "RUPAY": {
                    systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("RUPAY_BIN_FORMAT_CODE"), "The property RUPAY_BIN_FORMAT_CODE not found!"));
                    FileFormatsEntity rupayForCode = this.fileFormatRepository.findBySystemFileFormats_Code(Integer.valueOf(systemForCode));
                    if (rupayForCode == null) {
                        responseBody.put("FOR_CODE_NULL;", "RUPAY FOR CODE IS NULL");
                        responseBodyList.add(responseBody);
                        return responseBodyList;
                    }
                    forCode = rupayForCode.getCode();
                    break;
                }
                case "VISA": {
                    systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("VISA_BIN_FORMAT_CODE"), "The property VISA_BIN_FORMAT_CODE not found!"));
                    FileFormatsEntity visaForCode = this.fileFormatRepository.findBySystemFileFormats_Code(Integer.valueOf(systemForCode));
                    if (visaForCode == null) {
                        responseBody.put("FOR_CODE_NULL;", "VISA FOR CODE IS NULL");
                        responseBodyList.add(responseBody);
                        return responseBodyList;
                    }
                    forCode = visaForCode.getCode();
                    break;
                }
                case "JAYWAN": {
                    systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("JAYWAN_BIN_FORMAT_CODE"), "The property JAYWAN_BIN_FORMAT_CODE not found!"));
                    FileFormatsEntity jaywanForCode = this.fileFormatRepository.findBySystemFileFormats_Code(Integer.valueOf(systemForCode));
                    if (jaywanForCode == null) {
                        responseBody.put("FOR_CODE_NULL;", "JAYWAN FOR CODE IS NULL");
                        responseBodyList.add(responseBody);
                        return responseBodyList;
                    }
                    forCode = jaywanForCode.getCode();
                    break;
                }
                case "OMANNET": {
                    systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("OMANNET_BIN_FORMAT_CODE"), "The property OMANNET_BIN_FORMAT_CODE not found!"));
                    FileFormatsEntity omannetForCode = this.fileFormatRepository.findBySystemFileFormats_Code(Integer.valueOf(systemForCode));
                    if (omannetForCode == null) {
                        responseBody.put("FOR_CODE_NULL;", "OMANNET FOR CODE IS NULL");
                        responseBodyList.add(responseBody);
                        return responseBodyList;
                    }
                    forCode = omannetForCode.getCode();
                    break;
                }
                case "MERCURY": {
                    systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MERCURY_BIN_FORMAT_CODE"), "The property MERCURY_BIN_FORMAT_CODE not found!"));
                    FileFormatsEntity mercuryForCode = this.fileFormatRepository.findBySystemFileFormats_Code(Integer.valueOf(systemForCode));
                    if (mercuryForCode == null) {
                        responseBody.put("FOR_CODE_NULL;", "MERCURY FOR CODE IS NULL");
                        responseBodyList.add(responseBody);
                        return responseBodyList;
                    }
                    forCode = mercuryForCode.getCode();
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unsupported network: " + network);
                }
            }
            if (Objects.nonNull(requestVo.getFromProcessedDate()) && Objects.isNull(requestVo.getToProcessedDate()) || Objects.nonNull(requestVo.getToProcessedDate()) && Objects.isNull(requestVo.getFromProcessedDate())) {
                throw new ValidationException("Invalid date fields!");
            }
            int genStatus = this.getUploadGenStatus.applyAsInt(Objects.nonNull(requestVo.getStatus()) ? requestVo.getStatus().toLowerCase() : "");
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildBinHistSpecification(forCode, requestVo.getBussDate(), genStatus, requestVo.getFromProcessedDate(), requestVo.getToProcessedDate(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                long totalCount = this.fileUploadLogRepository.count(spec);
                int totalPage = (int)Math.ceil((double)totalCount / (double)size);
                Page pageList = this.fileUploadLogRepository.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody1 = new HashMap<String, String>();
                        int rejectedCount = 0;
                        if (Objects.nonNull(entity.getTotalTxnCount()) || Objects.nonNull(entity.getTotalAcceptedTxnCount())) {
                            rejectedCount = entity.getTotalTxnCount() - entity.getTotalAcceptedTxnCount();
                        }
                        responseBody1.put("fileName", Objects.nonNull(entity.getFileName()) ? entity.getFileName() : "");
                        responseBody1.put("fileFormat", Objects.nonNull(entity.getFileFormats()) && Objects.nonNull(entity.getFileFormats().getDescription()) ? entity.getFileFormats().getDescription() : "");
                        responseBody1.put("processedDate", Objects.nonNull(entity.getProcessingDate()) ? entity.getProcessingDate().format(dateFormat) : "");
                        responseBody1.put("businessDate", Objects.nonNull(entity.getBusinessDate()) ? entity.getBusinessDate().format(dateFormat) : "");
                        responseBody1.put("acceptedCount", Objects.nonNull(entity.getTotalAcceptedTxnCount()) ? String.valueOf(entity.getTotalAcceptedTxnCount()) : "0");
                        responseBody1.put("rejectedCount", String.valueOf(rejectedCount));
                        responseBody1.put("status", Objects.nonNull(entity.getUploadStatus()) ? (String)this.getBinStatus.apply(entity.getUploadStatus()) : "");
                        if (Objects.nonNull(entity.getProcessingJobs())) {
                            responseBody1.put("startTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessStartTime()));
                            responseBody1.put("endTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessEndTime()));
                        } else {
                            responseBody1.put("startTime", "");
                            responseBody1.put("endTime", "");
                        }
                        responseBody1.put("totalCount", String.valueOf(totalCount));
                        responseBody1.put("totalPage", String.valueOf(totalPage));
                        return responseBody1;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "No History Found"));
                }
            }
        }
        catch (Exception e) {
            log.error("ERROR: getBinHist()", (Throwable)e);
            responseBodyList.add(responseBody);
        }
        return responseBodyList;
    }

    private Specification<FileUploadLogEntity> buildBinHistSpecification(int forCode, String bussDate, int genStatus, String fromDate, String toDate, String fileName) {
        try {
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                Predicate filterConditions = criteriaBuilder.equal((Expression)root.get("fileFormats").get("code"), (Object)forCode);
                if (Objects.nonNull(bussDate) && !bussDate.isBlank()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate bussinessDate = LocalDate.parse(bussDate, formatter);
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("businessDate"), (Object)bussinessDate));
                }
                if (genStatus != 0) {
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("uploadStatus"), (Object)genStatus));
                }
                if (Objects.nonNull(fileName) && !fileName.isBlank()) {
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("fileName"), (Object)fileName));
                }
                if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
                    LocalDateTime parsedFromDate = LocalDateTime.parse(fromDate, DATE_TIME_FORMATTER);
                    LocalDateTime parsedToDate = LocalDateTime.parse(toDate, DATE_TIME_FORMATTER);
                    Predicate startTimeCondition = criteriaBuilder.between((Expression)root.get("processingJobs").get("processStartTime"), (Comparable)parsedFromDate, (Comparable)parsedToDate);
                    Predicate endTimeCondition = criteriaBuilder.between((Expression)root.get("processingJobs").get("processEndTime"), (Comparable)parsedFromDate, (Comparable)parsedToDate);
                    Predicate combinedCondition = criteriaBuilder.and((Expression)startTimeCondition, (Expression)endTimeCondition);
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)combinedCondition);
                }
                query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
                return filterConditions;
            };
        }
        catch (Exception e) {
            log.error("Error buildTLFUploadLogSpecification() :", (Throwable)e);
            return null;
        }
    }

    private String convertDateTimeToString(LocalDateTime time) {
        try {
            if (Objects.nonNull(time)) {
                return time.format(DATE_TIME_FORMATTER);
            }
        }
        catch (Exception e) {
            log.error("Error::convertDateTimeToString():", (Throwable)e);
        }
        return "";
    }

    public BinHistoryService(FileUploadLogRepository fileUploadLogRepository, FileFormatRepository fileFormatRepository, Environment env) {
        this.fileUploadLogRepository = fileUploadLogRepository;
        this.fileFormatRepository = fileFormatRepository;
        this.env = env;
    }
}

