/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.BusinessDateEntity
 *  com.empay.entities.OutgoingFileLogEntity
 *  com.empay.exceptions.ValidationException
 *  com.empay.repositories.BusinessDateRepository
 *  com.empay.repositories.OutgoingFileLogRepository
 *  com.empay.services.OutgoingInquiryService
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

import com.empay.entities.BusinessDateEntity;
import com.empay.entities.OutgoingFileLogEntity;
import com.empay.exceptions.ValidationException;
import com.empay.repositories.BusinessDateRepository;
import com.empay.repositories.OutgoingFileLogRepository;
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
public class OutgoingInquiryService {
    private static final Logger log = LogManager.getLogger(OutgoingInquiryService.class);
    private final OutgoingFileLogRepository outgoingFileLogRepository;
    private final BusinessDateRepository businessDateRepo;
    private final Environment env;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    ToIntFunction<String> getOutHistGenStatus = status -> switch (status) {
        case "completed" -> 6;
        case "pending" -> 3;
        case "marked for outgoing" -> 4;
        case "processing failed", "failed" -> 5;
        default -> 0;
    };

    public Map<String, String> getOutgoingStatus() {
        HashMap<String, String> responseBody = new HashMap<String, String>();
        try {
            OutgoingFileLogEntity entity = this.outgoingFileLogRepository.findByBusinessDate(this.getBusinessDate());
            if (Objects.nonNull(entity)) {
                responseBody.put("fileFormat", "MASTERCARD IPM OUTGOING");
                responseBody.put("fileName", entity.getFileName());
                responseBody.put("businessDate", entity.getBusinessDate().format(DATE_FORMATTER));
                responseBody.put("generatedDate", entity.getGeneratedDate().format(DATE_FORMATTER));
                responseBody.put("status", this.findStatus(entity.getGenerateStatus()));
            } else {
                responseBody.put("message", "File Generation Not Found");
            }
        }
        catch (Exception e) {
            log.error("Error::getOutgoingStatus():", (Throwable)e);
            responseBody.put("message", "Unexpected Error Occurred!");
        }
        return responseBody;
    }

    private LocalDate getBusinessDate() {
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

    public List<Map<String, String>> getOutgoingHist(@Valid RequestVo requestVo, int page, int size, String filetype) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            Integer forCode = 0;
            String network = requestVo.getNetwork().toUpperCase();
            if (filetype.equalsIgnoreCase("C")) {
                forCode = switch (network) {
                    case "MASTERCARD" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("GCO_FORMAT_CODE"), "The property GCO_FORMAT_CODE not found!"));
                    case "VISA" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("GOC_FORMAT_CODE"), "The property GOC_FORMAT_CODE not found!"));
                    default -> throw new IllegalArgumentException("Unsupported network: " + network);
                };
            } else {
                forCode = switch (network) {
                    case "MASTERCARD" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MASTERCARD_SYSTEM_FORMAT_CODE"), "The property MASTERCARD_SYSTEM_FORMAT_CODE not found!"));
                    case "RUPAY" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("RUPAY_SYSTEM_FORMAT_CODE"), "The property RUPAY_SYSTEM_FORMAT_CODE not found!"));
                    case "VISA" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("VISA_SYSTEM_FORMAT_CODE"), "The property VISA_SYSTEM_FORMAT_CODE not found!"));
                    case "JAYWAN" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("JAYWAN_SYSTEM_FORMAT_CODE"), "The property JAYWAN_SYSTEM_FORMAT_CODE not found!"));
                    case "AMEX" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("AMEX_SYSTEM_FORMAT_CODE"), "The property AMEX_SYSTEM_FORMAT_CODE not found!"));
                    default -> throw new IllegalArgumentException("Unsupported network: " + network);
                };
            }
            if (Objects.nonNull(requestVo.getFromGeneratedDate()) && Objects.isNull(requestVo.getToGeneratedDate()) || Objects.nonNull(requestVo.getToGeneratedDate()) && Objects.isNull(requestVo.getFromGeneratedDate())) {
                throw new ValidationException("Invalid date fields!");
            }
            int genStatus = this.getOutHistGenStatus.applyAsInt(Objects.nonNull(requestVo.getStatus()) ? requestVo.getStatus().toLowerCase() : "");
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildOutgoingHistSpecification(forCode, requestVo.getBussDate(), genStatus, requestVo.getFromGeneratedDate(), requestVo.getToGeneratedDate(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                long totalCount = this.outgoingFileLogRepository.count(spec);
                int totalPage = (int)Math.ceil((double)totalCount / (double)size);
                Page pageList = this.outgoingFileLogRepository.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody = new HashMap<String, String>();
                        responseBody.put("fileName", entity.getFileName());
                        responseBody.put("fileId", entity.getFileId());
                        responseBody.put("businessDate", entity.getBusinessDate().format(DATE_FORMATTER));
                        responseBody.put("generatedDate", entity.getGeneratedDate().format(DATE_TIME_FORMATTER));
                        responseBody.put("status", this.findStatus(entity.getGenerateStatus()));
                        responseBody.put("totalCount", String.valueOf(totalCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "No History Found"));
                }
            }
        }
        catch (Exception e) {
            HashMap<String, String> errorResponse = new HashMap<String, String>();
            errorResponse.put("message", "Error");
            log.error("ERROR: getOutgoingHist()", (Throwable)e);
            responseBodyList.add(errorResponse);
        }
        return responseBodyList;
    }

    private Specification<OutgoingFileLogEntity> buildOutgoingHistSpecification(Integer forCode, String bussDate, int genStatus, String fromDate, String toDate, String fileName) {
        try {
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                Predicate filterConditions = criteriaBuilder.equal((Expression)root.get("fileFormats").get("systemFileFormats").get("code"), (Object)forCode);
                if (Objects.nonNull(bussDate) && !bussDate.isBlank()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate businessDate = LocalDate.parse(bussDate, formatter);
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("businessDate"), (Object)businessDate));
                }
                if (genStatus != 0) {
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("generateStatus"), (Object)genStatus));
                }
                if (Objects.nonNull(fileName) && !fileName.isBlank()) {
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("fileName"), (Object)fileName));
                }
                if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
                    LocalDateTime parsedFromDate = LocalDateTime.parse(fromDate, DATE_TIME_FORMATTER);
                    LocalDateTime parsedToDate = LocalDateTime.parse(toDate, DATE_TIME_FORMATTER);
                    Predicate dateRangeCondition = criteriaBuilder.between((Expression)root.get("generatedDate"), (Comparable)parsedFromDate, (Comparable)parsedToDate);
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)dateRangeCondition);
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

    private String findStatus(Integer genStatus) {
        return switch (genStatus) {
            case 3 -> "Pending for outgoing";
            case 4 -> "Marked for outgoing";
            case 6 -> "File Generation Completed";
            case 9 -> "In processing";
            case 5 -> "Processing Failed";
            default -> "";
        };
    }

    public OutgoingInquiryService(OutgoingFileLogRepository outgoingFileLogRepository, BusinessDateRepository businessDateRepo, Environment env) {
        this.outgoingFileLogRepository = outgoingFileLogRepository;
        this.businessDateRepo = businessDateRepo;
        this.env = env;
    }
}

