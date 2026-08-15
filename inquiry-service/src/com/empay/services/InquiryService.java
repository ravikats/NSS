/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.entities.IRFCallbackEntity
 *  com.empay.entities.JaywanIRFEntity
 *  com.empay.entities.MCAcquirerExceptionsEntity
 *  com.empay.entities.MCIpmFeesEntity
 *  com.empay.entities.McNetworkDataEntity
 *  com.empay.entities.OmanNetIRFEntity
 *  com.empay.entities.OutgoingSchedulerEntity
 *  com.empay.entities.PosTransactionEntity
 *  com.empay.entities.ReportGenerationLogEntity
 *  com.empay.entities.UaeSwitchIrfEntity
 *  com.empay.entities.ViewFileFormatsEntity
 *  com.empay.entities.ViewInterfaceEntity
 *  com.empay.entities.ViewMCNetReconDetailsEntity
 *  com.empay.entities.ViewMcRejectionDetails
 *  com.empay.entities.ViewTxnInquiryDetails
 *  com.empay.entities.ViewVisaFundsTransferDetailsEntity
 *  com.empay.entities.VisaDisputeFinanceDataEntity
 *  com.empay.entities.VisaNetworkDataEntity
 *  com.empay.exceptions.ValidationException
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.repositories.IRFCallbackRepo
 *  com.empay.repositories.JaywanIRFRepo
 *  com.empay.repositories.MCAcquirerExceptionsRepo
 *  com.empay.repositories.MCIpmFeesRepo
 *  com.empay.repositories.MCRejectionsRepo
 *  com.empay.repositories.McNetworkDataRepo
 *  com.empay.repositories.OmanNetIRFRepo
 *  com.empay.repositories.OutgoingSchedulerRepo
 *  com.empay.repositories.PosTransactionRepository
 *  com.empay.repositories.ReportGenerationRepo
 *  com.empay.repositories.UaeSwitchIrfRepo
 *  com.empay.repositories.ViewFileFormatRepo
 *  com.empay.repositories.ViewInterfaceRepository
 *  com.empay.repositories.ViewMCNetReconDetailsRepo
 *  com.empay.repositories.ViewMcRejectionDetailsRepo
 *  com.empay.repositories.ViewTxnInquiryDetailsRepo
 *  com.empay.repositories.VisaDisputeFinanceDataRepo
 *  com.empay.repositories.VisaFundsTransferDetailsRepo
 *  com.empay.repositories.VisaNetworkDataRepo
 *  com.empay.services.InquiryService
 *  com.empay.vo.ChargeBackResponseVo
 *  com.empay.vo.FeeCollectionResponseVo
 *  com.empay.vo.InquiryAPIRequestVo
 *  com.empay.vo.InquiryRequestVo
 *  com.empay.vo.InterchangeRequestVo
 *  com.empay.vo.InterchangeResponseVo
 *  com.empay.vo.OmanNetinterchangeResponseVo
 *  com.empay.vo.OutgoingSchedulerResponseVo
 *  com.empay.vo.RejectedTransactionDetailsVo
 *  com.empay.vo.ReportRequestVo
 *  com.empay.vo.RequestVo
 *  com.empay.vo.SchemeFundDetailsRequestVo
 *  com.empay.vo.SchemeFundDetailsResponseVo
 *  com.empay.vo.TxnDetailsRequestVo
 *  com.empay.vo.TxnDetailsResponseVo
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

import com.empay.entities.FileUploadLogEntity;
import com.empay.entities.IRFCallbackEntity;
import com.empay.entities.JaywanIRFEntity;
import com.empay.entities.MCAcquirerExceptionsEntity;
import com.empay.entities.MCIpmFeesEntity;
import com.empay.entities.McNetworkDataEntity;
import com.empay.entities.OmanNetIRFEntity;
import com.empay.entities.OutgoingSchedulerEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.entities.ReportGenerationLogEntity;
import com.empay.entities.UaeSwitchIrfEntity;
import com.empay.entities.ViewFileFormatsEntity;
import com.empay.entities.ViewInterfaceEntity;
import com.empay.entities.ViewMCNetReconDetailsEntity;
import com.empay.entities.ViewMcRejectionDetails;
import com.empay.entities.ViewTxnInquiryDetails;
import com.empay.entities.ViewVisaFundsTransferDetailsEntity;
import com.empay.entities.VisaDisputeFinanceDataEntity;
import com.empay.entities.VisaNetworkDataEntity;
import com.empay.exceptions.ValidationException;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.repositories.IRFCallbackRepo;
import com.empay.repositories.JaywanIRFRepo;
import com.empay.repositories.MCAcquirerExceptionsRepo;
import com.empay.repositories.MCIpmFeesRepo;
import com.empay.repositories.MCRejectionsRepo;
import com.empay.repositories.McNetworkDataRepo;
import com.empay.repositories.OmanNetIRFRepo;
import com.empay.repositories.OutgoingSchedulerRepo;
import com.empay.repositories.PosTransactionRepository;
import com.empay.repositories.ReportGenerationRepo;
import com.empay.repositories.UaeSwitchIrfRepo;
import com.empay.repositories.ViewFileFormatRepo;
import com.empay.repositories.ViewInterfaceRepository;
import com.empay.repositories.ViewMCNetReconDetailsRepo;
import com.empay.repositories.ViewMcRejectionDetailsRepo;
import com.empay.repositories.ViewTxnInquiryDetailsRepo;
import com.empay.repositories.VisaDisputeFinanceDataRepo;
import com.empay.repositories.VisaFundsTransferDetailsRepo;
import com.empay.repositories.VisaNetworkDataRepo;
import com.empay.vo.ChargeBackResponseVo;
import com.empay.vo.FeeCollectionResponseVo;
import com.empay.vo.InquiryAPIRequestVo;
import com.empay.vo.InquiryRequestVo;
import com.empay.vo.InterchangeRequestVo;
import com.empay.vo.InterchangeResponseVo;
import com.empay.vo.OmanNetinterchangeResponseVo;
import com.empay.vo.OutgoingSchedulerResponseVo;
import com.empay.vo.RejectedTransactionDetailsVo;
import com.empay.vo.ReportRequestVo;
import com.empay.vo.RequestVo;
import com.empay.vo.SchemeFundDetailsRequestVo;
import com.empay.vo.SchemeFundDetailsResponseVo;
import com.empay.vo.TxnDetailsRequestVo;
import com.empay.vo.TxnDetailsResponseVo;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class InquiryService {
    private static final Logger log = LogManager.getLogger(InquiryService.class);
    private final ViewFileFormatRepo viewRepo;
    private final ViewInterfaceRepository viewInterfaceRepo;
    private final PosTransactionRepository posTransactionRepo;
    private final FileUploadLogRepository fileUploadRepo;
    private final Environment env;
    private final IRFCallbackRepo irfCallbackRepo;
    private final VisaNetworkDataRepo visaNetworkRepo;
    private final McNetworkDataRepo mcNetworkRepo;
    private final ViewTxnInquiryDetailsRepo viewTxnInquiryRepo;
    private final ReportGenerationRepo reportGenerationRepo;
    private final UaeSwitchIrfRepo uaeSwitchIrfRepo;
    private final ViewMcRejectionDetailsRepo viewMcRejectionDetailsRepo;
    private final OutgoingSchedulerRepo outgoingSchedulerRepo;
    private final OmanNetIRFRepo omanNetIRFRepo;
    private final JaywanIRFRepo jaywanIrfRepo;
    private final ViewMCNetReconDetailsRepo viewMCNetReconDetailsRepo;
    private final VisaFundsTransferDetailsRepo visaFundsTransferDetailsRepo;
    private final MCRejectionsRepo mcRejectionsRepo;
    private final MCIpmFeesRepo mcIpmFeesRepo;
    private final MCAcquirerExceptionsRepo mcAcquirerExceptionsRepo;
    private final VisaDisputeFinanceDataRepo visaDisputeFinanceDataRepo;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    IntFunction<String> getStatus = status -> switch (status) {
        case 1 -> "Ready for processing";
        case 3 -> "Processed";
        case 4 -> "Marked for outgoing";
        case 5 -> "Processing Failed";
        case 6 -> "Completed";
        case 7 -> "File Deletion Failed";
        case 8 -> "Deletion in Processing";
        case 9 -> "In processing";
        default -> "";
    };
    IntFunction<String> getOutgoingStatus = status -> switch (status) {
        case 1, 3 -> "Pending for outgoing";
        case 4 -> "Marked for outgoing";
        case 6 -> "Completed";
        case 9 -> "In processing";
        default -> "Failed";
    };
    ToIntFunction<String> getGenStatus = status -> switch (status) {
        case "ready for processing" -> 1;
        case "processed" -> 3;
        case "marked for outgoing" -> 4;
        case "processing failed" -> 5;
        case "completed" -> 6;
        case "file deletion failed" -> 7;
        case "deletion in processing" -> 8;
        case "in processing" -> 9;
        default -> 0;
    };
    IntFunction<String> getUploadStatus = status -> switch (status) {
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

    public Map<String, String> getProcessBinStatus(String fileName) {
        HashMap<String, String> responseBody = new HashMap<String, String>();
        try {
            ViewFileFormatsEntity filFmtEntity = this.viewRepo.findByFileName(fileName);
            if (Objects.nonNull(filFmtEntity)) {
                if (Objects.isNull(filFmtEntity.getTotalTxnCount())) {
                    filFmtEntity.setTotalTxnCount(Integer.valueOf(0));
                }
                Integer acceptedCount = Objects.isNull(filFmtEntity.getTotalAcceptedTxnCount()) ? 0 : filFmtEntity.getTotalAcceptedTxnCount();
                int rejectedCount = filFmtEntity.getTotalTxnCount() - acceptedCount;
                responseBody.put("rejectedCount", String.valueOf(rejectedCount));
                responseBody.put("status", Objects.nonNull(filFmtEntity.getStatus()) ? (String)this.getUploadStatus.apply(filFmtEntity.getStatus()) : "");
                responseBody.put("fileFormat", Objects.nonNull(filFmtEntity.getDescription()) ? filFmtEntity.getDescription() : "");
                responseBody.put("fileName", Objects.nonNull(filFmtEntity.getFileName()) ? filFmtEntity.getFileName() : "");
                responseBody.put("businessDate", Objects.nonNull(filFmtEntity.getBusinessDate()) ? filFmtEntity.getBusinessDate().format(DATE_FORMATTER) : "");
                if (filFmtEntity.getTotalAcceptedTxnCount() == null) {
                    responseBody.put("acceptedCount", "0");
                } else {
                    responseBody.put("acceptedCount", filFmtEntity.getTotalAcceptedTxnCount().toString());
                }
                if (filFmtEntity.getStatus() == 5) {
                    responseBody.put("remarks", Objects.nonNull(filFmtEntity.getRemarks()) ? filFmtEntity.getRemarks() : "");
                    return responseBody;
                }
                return responseBody;
            }
            responseBody.put("message", "The specified file does not exist.");
        }
        catch (Exception e) {
            log.error("ERROR:getProcessBinStatus()", (Throwable)e);
            responseBody.put("message", "Error");
        }
        return responseBody;
    }

    public Map<String, String> getFileStatus(String fileName) {
        HashMap<String, String> responseBody = new HashMap<String, String>();
        try {
            ViewInterfaceEntity intEntity = this.viewInterfaceRepo.findByFileName(fileName);
            if (Objects.nonNull(intEntity)) {
                if (Objects.nonNull(intEntity.getTotalCount()) && Objects.nonNull(intEntity.getAcceptCount())) {
                    responseBody.put("rejectedCount", String.valueOf(intEntity.getTotalCount() - intEntity.getAcceptCount()));
                }
                responseBody.put("status", (String)this.getUploadStatus.apply(intEntity.getUploadStatus()));
                responseBody.put("interface", Objects.nonNull(intEntity.getInterfaceName()) ? intEntity.getInterfaceName() : "");
                responseBody.put("fileFormat", Objects.nonNull(intEntity.getFormatDesc()) ? intEntity.getFormatDesc() : "");
                responseBody.put("fileName", Objects.nonNull(intEntity.getFileName()) ? intEntity.getFileName() : "");
                responseBody.put("startTime", Objects.nonNull(intEntity.getStartTime()) ? intEntity.getStartTime().format(DATE_TIME_FORMATTER) : "");
                responseBody.put("endTime", Objects.nonNull(intEntity.getEndTime()) ? intEntity.getEndTime().format(DATE_TIME_FORMATTER) : "");
                responseBody.put("acceptedCount", Objects.nonNull(intEntity.getAcceptCount()) ? intEntity.getAcceptCount().toString() : "");
            } else {
                responseBody.put("message", "The specified file does not exist.");
            }
        }
        catch (Exception e) {
            log.error("ERROR:getFileStatus()", (Throwable)e);
            responseBody.put("message", "Unexpected Error Occurred!");
        }
        return responseBody;
    }

    public List<Map<String, String>> getTLFUploadLog(@Valid RequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            Integer tlfSystemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("SWITCH_TLF_SYSTEM_CODE", "The property tlfSystemForCode not found!")));
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            if (Objects.nonNull(requestVo.getFromDate()) && Objects.isNull(requestVo.getToDate()) || Objects.nonNull(requestVo.getToDate()) && Objects.isNull(requestVo.getFromDate())) {
                throw new ValidationException("Invalid date fields!");
            }
            int genStatus = this.getUploadGenStatus.applyAsInt(Objects.nonNull(requestVo.getStatus()) ? requestVo.getStatus().toLowerCase() : "");
            Specification spec = this.buildTLFUploadLogSpecification(requestVo.getBussDate(), tlfSystemForCode, genStatus, requestVo.getFromDate(), requestVo.getToDate(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                long totalTxnCount = this.fileUploadRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalTxnCount / (double)size);
                Page pageList = this.fileUploadRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody = new HashMap<String, String>();
                        Integer totalCount = Objects.isNull(entity.getTotalTxnCount()) ? 0 : entity.getTotalTxnCount();
                        Integer acceptedCount = Objects.isNull(entity.getTotalAcceptedTxnCount()) ? 0 : entity.getTotalAcceptedTxnCount();
                        int rejectedCount = totalCount - acceptedCount;
                        responseBody.put("rejectedCount", String.valueOf(rejectedCount));
                        responseBody.put("fileName", Objects.nonNull(entity.getFileName()) ? entity.getFileName() : "");
                        responseBody.put("processedDate", Objects.nonNull(entity.getProcessingDate()) ? entity.getProcessingDate().format(DATE_FORMATTER) : "");
                        responseBody.put("businessDate", Objects.nonNull(entity.getBusinessDate()) ? entity.getBusinessDate().format(DATE_FORMATTER) : "");
                        if (Objects.nonNull(entity.getProcessingJobs())) {
                            responseBody.put("startTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessStartTime()));
                            responseBody.put("endTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessEndTime()));
                        } else {
                            responseBody.put("startTime", "");
                            responseBody.put("endTime", "");
                        }
                        responseBody.put("acceptedCount", String.valueOf(acceptedCount));
                        responseBody.put("status", Objects.nonNull(entity.getUploadStatus()) ? (String)this.getUploadStatus.apply(entity.getUploadStatus()) : "");
                        responseBody.put("totalCount", String.valueOf(totalTxnCount));
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
            errorResponse.put("status", "Error");
            log.error("ERROR: getTLFUploadLog()", (Throwable)e);
            responseBodyList.add(errorResponse);
        }
        return responseBodyList;
    }

    private Specification<FileUploadLogEntity> buildTLFUploadLogSpecification(String bussDate, Integer tlfSystemForCode, int genStatus, String fromDate, String toDate, String fileName) {
        try {
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                Predicate filterConditions = criteriaBuilder.equal((Expression)root.get("fileFormats").get("systemFileFormats").get("code"), (Object)tlfSystemForCode);
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
            log.error("Error buildTLFUploadLogSpecification()", (Throwable)e);
            return null;
        }
    }

    public List<Map<String, String>> getTxnStatus(String rrn, int page, int size) {
        HashMap<String, String> responseBody = new HashMap<String, String>();
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildTxnStatusSpecification(rrn);
            if (Objects.nonNull(spec)) {
                long totalCount = this.posTransactionRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalCount / (double)size);
                Page pageList = this.posTransactionRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        responseBody.put("irdCode", Objects.nonNull(entity.getIrdSerNumber()) ? entity.getIrdSerNumber().toString() : "");
                        responseBody.put("fixed", Objects.nonNull(entity.getIrfFixed()) ? entity.getIrfFixed().toString() : "");
                        responseBody.put("percentage", Objects.nonNull(entity.getIrfPercent()) ? entity.getIrfPercent().toString() : "");
                        responseBody.put("amount", Objects.nonNull(entity.getIrfAmount()) ? entity.getIrfAmount().toString() : "");
                        responseBody.put("domIntlFlag", Objects.nonNull(entity.getCardDomIntlFlag()) ? entity.getCardDomIntlFlag().toString() : "");
                        responseBody.put("cardType", Objects.nonNull(entity.getCardType()) ? entity.getCardType().toString() : "");
                        responseBody.put("description", "");
                        responseBody.put("status", Objects.nonNull(entity.getGenStatus()) ? (String)this.getStatus.apply(entity.getGenStatus()) : "");
                        responseBody.put("totalCount", String.valueOf(totalCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "Transaction not Found"));
                }
            }
        }
        catch (Exception e) {
            log.error("Error::getTxnStatus():", (Throwable)e);
            responseBody.put("status", "Unexpected Error Occurred!");
        }
        return responseBodyList;
    }

    private Specification<PosTransactionEntity> buildTxnStatusSpecification(String rrn) {
        try {
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                Predicate filterConditions = criteriaBuilder.equal((Expression)root.get("rrn"), (Object)rrn);
                query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
                return filterConditions;
            };
        }
        catch (Exception e) {
            log.error("Error buildTxnStatusSpecification() :", (Throwable)e);
            return null;
        }
    }

    private boolean isTrueIndicator(Character indicator) {
        return indicator != null && (indicator.charValue() == 'F' || indicator.charValue() == 'R');
    }

    private String getResponseCode(String responseCode) {
        return responseCode.equals("00") ? "Success" : "Failed";
    }

    private String mapDomIntlFlag(String domIntlFlag) {
        return switch (domIntlFlag) {
            case "D" -> "Domestic";
            case "I" -> "International";
            default -> domIntlFlag;
        };
    }

    private Character mapDomIntlToFlag(String domIntlFlag) {
        return switch (domIntlFlag.toUpperCase()) {
            case "DOMESTIC" -> Character.valueOf('D');
            case "INTERNATIONAL" -> Character.valueOf('I');
            default -> Character.valueOf(' ');
        };
    }

    private String mapCardType(String cardType) {
        return switch (cardType) {
            case "C" -> "Credit";
            case "D" -> "Debit";
            case "P" -> "Prepaid";
            default -> cardType;
        };
    }

    private String mapNetwork(String network) {
        return switch (network = Objects.nonNull(network) ? network.toUpperCase() : "") {
            case "MCI", "MDS" -> "MASTERCARD";
            case "VSMS" -> "VISA SMS";
            case "RSMS" -> "RUPAY SMS";
            case "AMEX" -> "AMEX";
            case "JAYWAN" -> "JAYWAN";
            case "OMANNET" -> "omannet_tps";
            case "ONUS" -> "ONUS";
            case "MAAL" -> "MAAL";
            default -> network;
        };
    }

    private String mapChannel(String network) {
        return switch (network = Objects.nonNull(network) ? network.toUpperCase() : "") {
            case "MASTERCARD" -> "MCI";
            case "VISA SMS" -> "VSMS";
            case "RUPAY SMS" -> "RSMS";
            case "AMEX" -> "AMEX";
            case "VISA" -> "VISA";
            case "UAESWITCH" -> "UAESWITCH";
            case "JAYWAN" -> "JAYWAN";
            case "OMANNET_TPS" -> "OMANNET";
            case "ONUS" -> "ONUS";
            case "MAAL" -> "MAAL";
            default -> network;
        };
    }

    private String getTxnType(String txnCode, String mti) {
        if (mti != null) {
            switch (mti) {
                case "0430": {
                    return "Void";
                }
                case "0410": {
                    return "Reversal";
                }
                case "0130": {
                    return "Preauth Complete";
                }
            }
        }
        return switch (txnCode) {
            case "00", "05" -> "Sale";
            case "01" -> "Cash on POS";
            case "07" -> "Cash Adv";
            case "09" -> "Cashback";
            case "06", "20" -> "Refund";
            case "02" -> "Cash withdrawal";
            case "03" -> "Balance inquiry";
            case "10" -> "Sale CB";
            case "11" -> "Cash on POS CB";
            case "17" -> "Cash Adv CB";
            case "19" -> "Cashback CB";
            case "16" -> "Refund CB";
            case "21" -> "Cash@POS Reversal";
            case "25" -> "Reversal";
            case "27" -> "Cash Adv Reversal";
            case "29" -> "Cashback Reversal";
            case "26" -> "Refund Reversal";
            case "22" -> "Cash withdrawal Reversal";
            case "30" -> "Sale CB Reversal";
            case "31" -> "Cash@POS CB Reversal";
            case "36" -> "Refund CB Reversal";
            case "37" -> "Cash Adv CB Reversal";
            case "39" -> "Cashback CB Reversal";
            case "61" -> "Preauth";
            case "62" -> "Incremental Preauth";
            case "71" -> "Preauth complete";
            case "91" -> "Mini Statements";
            case "96", "94", "98" -> "Fee";
            case "99" -> "Loan Sharing";
            default -> "";
        };
    }

    private String getTxnCode(String txnType) {
        return switch (txnType = Objects.nonNull(txnType) ? txnType.toUpperCase() : "") {
            case "VOID" -> "0430";
            case "REVERSAL" -> "0410";
            case "PREAUTH COMPLETE" -> "0130";
            case "SALE" -> "00,05";
            case "CASH ON POS" -> "01";
            case "CASH ADV" -> "07";
            case "CASHBACK" -> "09";
            case "REFUND" -> "06";
            case "CASH WITHDRAWAL" -> "02";
            case "BALANCE INQUIRY" -> "03";
            case "SALE CB" -> "10";
            case "CASH ON POS CB" -> "11";
            case "CASH ADV CB" -> "17";
            case "CASHBACK CB" -> "19";
            case "REFUND CB" -> "16";
            case "CASH@POS REVERSAL" -> "21";
            case "CASH ADV REVERSAL" -> "27";
            case "CASHBACK REVERSAL" -> "29";
            case "REFUND REVERSAL" -> "26";
            case "CASH WITHDRAWAL REVERSAL" -> "22";
            case "SALE CB REVERSAL" -> "30";
            case "CASH@POS CB REVERSAL" -> "31";
            case "CASH ADV CB REVERSAL" -> "37";
            case "CASHBACK CB REVERSAL" -> "39";
            case "REFUND CB REVERSAL" -> "36";
            case "PREAUTH" -> "61";
            case "PREAUTH INCREMENTAL" -> "71";
            case "MINI STATEMENTS" -> "91";
            case "FEE" -> "96";
            case "LOAN SHARING" -> "99";
            default -> "";
        };
    }

    public List<Map<String, String>> getMPGSUploadLog(@Valid RequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            Integer systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MPGS_SYSTEM_FORMAT_CODE"), "The property MPGS_SYSTEM_FORMAT_CODE not found!"));
            if (Objects.nonNull(requestVo.getFromProcessedDate()) && Objects.isNull(requestVo.getToProcessedDate()) || Objects.nonNull(requestVo.getToProcessedDate()) && Objects.isNull(requestVo.getFromProcessedDate())) {
                throw new ValidationException("Invalid date fields!");
            }
            int genStatus = this.getUploadGenStatus.applyAsInt(Objects.nonNull(requestVo.getStatus()) ? requestVo.getStatus().toLowerCase() : "");
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildMPGSOrCyberSourceUploadLogSpecification(systemForCode, requestVo.getBussDate(), genStatus, requestVo.getFromProcessedDate(), requestVo.getToProcessedDate(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                long totalTxnCount = this.fileUploadRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalTxnCount / (double)size);
                Page pageList = this.fileUploadRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody = new HashMap<String, String>();
                        Integer totalCount = Objects.isNull(entity.getTotalTxnCount()) ? 0 : entity.getTotalTxnCount();
                        Integer acceptedCount = Objects.isNull(entity.getTotalAcceptedTxnCount()) ? 0 : entity.getTotalAcceptedTxnCount();
                        Integer rejectedCount = totalCount - acceptedCount;
                        responseBody.put("fileName", entity.getFileName());
                        responseBody.put("status", (String)this.getUploadStatus.apply(entity.getUploadStatus()));
                        if (Objects.nonNull(entity.getProcessingJobs())) {
                            responseBody.put("startTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessStartTime()));
                            responseBody.put("endTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessEndTime()));
                        } else {
                            responseBody.put("startTime", "");
                            responseBody.put("endTime", "");
                        }
                        responseBody.put("acceptedCount", String.valueOf(acceptedCount));
                        responseBody.put("rejectedCount", String.valueOf(rejectedCount));
                        responseBody.put("totalCount", String.valueOf(totalTxnCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "No History Found"));
                }
            }
        }
        catch (Exception e) {
            log.error("Error getMPGSUploadLog() :", (Throwable)e);
            responseBodyList.add(Collections.singletonMap("message", "Unexpected Error Occurred!"));
        }
        return responseBodyList;
    }

    private Specification<FileUploadLogEntity> buildMPGSOrCyberSourceUploadLogSpecification(Integer systemForCode, String bussDate, int genStatus, String fromDate, String toDate, String fileName) {
        try {
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                Predicate filterConditions = criteriaBuilder.equal((Expression)root.get("fileFormats").get("systemFileFormats").get("code"), (Object)systemForCode);
                if (Objects.nonNull(bussDate) && !bussDate.isBlank()) {
                    LocalDate businessDate = LocalDate.parse(bussDate, DATE_FORMATTER);
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("businessDate"), (Object)businessDate));
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

    public List<Map<String, String>> getIncomingUploadLog(@Valid RequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            String network;
            int mcT464ForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MASTERCARD_T464_FORMAT_CODE"), "The property MASTERCARD_T464_FORMAT_CODE not found!"));
            int mcT112ForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MASTERCARD_T112_FORMAT_CODE"), "The property MASTERCARD_T112_FORMAT_CODE not found!"));
            int mcTN70ForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MASTERCARD_TN70_FORMAT_CODE"), "The property MASTERCARD_TN70_FORMAT_CODE not found!"));
            int rupayIncomingForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("RUPAY_INCOMING_FORMAT_CODE"), "The property RUPAY_INCOMING_FORMAT_CODE not found!"));
            int visaIncomingForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("VISA_INCOMING_FORMAT_CODE"), "The property VISA_INCOMING_FORMAT_CODE not found!"));
            int jaywanIncomingForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("JAYWAN_INCOMING_FORMAT_CODE"), "The property JAYWAN_INCOMING_FORMAT_CODE not found!"));
            int uaeSwitchForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("UAESWITCH_SYSTEM_FORMAT_CODE"), "The property UAESWITCH_SYSTEM_FORMAT_CODE not found!"));
            int omanNetCBOForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("OMANNET_CBO_INCOMING_FORMAT_CODE"), "The property OMANNET_CBO_INCOMING_FORMAT_CODE not found!"));
            int omanNetTPSForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("OMANNET_TPS_INCOMING_FORMAT_CODE"), "The property OMANNET_TPS_INCOMING_FORMAT_CODE not found!"));
            ArrayList<Integer> forCode = new ArrayList<Integer>();
            switch (network = requestVo.getNetwork().toUpperCase()) {
                case "MASTERCARD": {
                    forCode.add(mcT464ForCode);
                    forCode.add(mcT112ForCode);
                    forCode.add(mcTN70ForCode);
                    break;
                }
                case "RUPAY": {
                    forCode.add(rupayIncomingForCode);
                    break;
                }
                case "VISA": {
                    forCode.add(visaIncomingForCode);
                    break;
                }
                case "JAYWAN": {
                    forCode.add(jaywanIncomingForCode);
                    break;
                }
                case "UAESWITCH": {
                    forCode.add(uaeSwitchForCode);
                    break;
                }
                case "OMANNET": {
                    forCode.add(omanNetCBOForCode);
                    forCode.add(omanNetTPSForCode);
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
            Specification spec = this.buildIncommingUploadLogSpecification(forCode, requestVo.getBussDate(), genStatus, requestVo.getFromProcessedDate(), requestVo.getToProcessedDate(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                long totalTxnCount = this.fileUploadRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalTxnCount / (double)size);
                Page pageList = this.fileUploadRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody = new HashMap<String, String>();
                        Integer totalCount = Objects.isNull(entity.getTotalTxnCount()) ? 0 : entity.getTotalTxnCount();
                        Integer acceptedCount = Objects.isNull(entity.getTotalAcceptedTxnCount()) ? 0 : entity.getTotalAcceptedTxnCount();
                        Integer rejectedCount = totalCount - acceptedCount;
                        responseBody.put("fileFormat", entity.getFileFormats().getDescription());
                        responseBody.put("fileName", entity.getFileName());
                        responseBody.put("status", (String)this.getUploadStatus.apply(entity.getUploadStatus()));
                        if (Objects.nonNull(entity.getProcessingJobs())) {
                            responseBody.put("startTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessStartTime()));
                            responseBody.put("endTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessEndTime()));
                        } else {
                            responseBody.put("startTime", "");
                            responseBody.put("endTime", "");
                        }
                        responseBody.put("acceptedCount", String.valueOf(acceptedCount));
                        responseBody.put("rejectedCount", String.valueOf(rejectedCount));
                        responseBody.put("totalCount", String.valueOf(totalTxnCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "No History Found"));
                }
            }
        }
        catch (Exception e) {
            log.error("Error getIncomingUploadLog() :", (Throwable)e);
            responseBodyList.add(Collections.singletonMap("message", "No History Found"));
        }
        return responseBodyList;
    }

    private Specification<FileUploadLogEntity> buildIncommingUploadLogSpecification(ArrayList<Integer> forCode, String busssDate, int genStatus, String fromDate, String toDate, String fileName) {
        try {
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                Predicate filterConditions = root.get("fileFormats").get("systemFileFormats").get("code").in((Collection)forCode);
                if (Objects.nonNull(busssDate) && !busssDate.isBlank()) {
                    LocalDate businessDate = LocalDate.parse(busssDate, DATE_FORMATTER);
                    filterConditions = criteriaBuilder.and((Expression)filterConditions, (Expression)criteriaBuilder.equal((Expression)root.get("businessDate"), (Object)businessDate));
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

    public List<Map<String, String>> getVisaTxnInchgDetails(InquiryRequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildVisaTxnInchgDetails(requestVo);
            if (Objects.nonNull(spec)) {
                long totalCount = this.visaNetworkRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalCount / (double)size);
                Page pageList = this.visaNetworkRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        LinkedHashMap<String, String> responseBody = new LinkedHashMap<String, String>();
                        responseBody.put("transactionId", Objects.nonNull(entity.getTxnId()) ? entity.getTxnId().toString() : "");
                        responseBody.put("interchangeFeeAmount", Objects.nonNull(entity.getInchgFeeAmount()) ? entity.getInchgFeeAmount().toString() : "0.00");
                        responseBody.put("rrn", Objects.nonNull(entity.getRetRefNumber()) ? entity.getRetRefNumber().toString() : "");
                        responseBody.put("interchangeFeeSign", Objects.nonNull(entity.getInchgFeeSign()) ? entity.getInchgFeeSign().toString() : "");
                        responseBody.put("feeDescriptor", Objects.nonNull(entity.getFeeDesc()) ? entity.getFeeDesc().toString() : "");
                        responseBody.put("interchangeMatched", Objects.nonNull(entity.getMatchStatus()) ? "true" : "false");
                        responseBody.put("interchangeDifferenceAmount", Objects.nonNull(entity.getIrfDiff()) ? entity.getIrfDiff().toString() : "0.00");
                        responseBody.put("totalCount", String.valueOf(totalCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "Transaction not Found"));
                }
            }
        }
        catch (Exception e) {
            HashMap<String, String> errorResponse = new HashMap<String, String>();
            errorResponse.put("status", "Unexpected Error Occurred!");
            log.error("getVisaTxnInchgDetails() :", (Throwable)e);
            responseBodyList.add(errorResponse);
        }
        return responseBodyList;
    }

    private Specification<VisaNetworkDataEntity> buildVisaTxnInchgDetails(InquiryRequestVo requestVo) {
        try {
            if (Objects.nonNull(requestVo) && Objects.nonNull(requestVo.getFromDate()) && Objects.nonNull(requestVo.getToDate())) {
                LocalDateTime parsedFromDate = LocalDateTime.parse(requestVo.getFromDate(), DATE_TIME_FORMATTER);
                LocalDateTime parsedToDate = LocalDateTime.parse(requestVo.getToDate(), DATE_TIME_FORMATTER);
                return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                    Predicate filterConditions = criteriaBuilder.between((Expression)root.get("centerProcDate"), (Comparable)parsedFromDate, (Comparable)parsedToDate);
                    query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
                    return filterConditions;
                };
            }
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
                return criteriaBuilder.conjunction();
            };
        }
        catch (Exception e) {
            log.error("Error buildVisaTxnInchgDetails() :", (Throwable)e);
            return null;
        }
    }

    public List<Map<String, String>> getMcTxnInchgDetails(InquiryRequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildMcTxnInchgDetails(requestVo);
            if (Objects.nonNull(spec)) {
                long totalCount = this.mcNetworkRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalCount / (double)size);
                Page pageList = this.mcNetworkRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        LinkedHashMap<String, String> responseBody = new LinkedHashMap<String, String>();
                        responseBody.put("traceId", Objects.nonNull(entity.getTraceId()) ? entity.getTraceId().toString() : "");
                        responseBody.put("rrn", Objects.nonNull(entity.getRetRefNumber()) ? entity.getRetRefNumber().toString() : "");
                        responseBody.put("transactionAmountAED", Objects.nonNull(entity.getTxnAmount()) ? entity.getTxnAmount().toString() : "0.00");
                        responseBody.put("reconciliationAmountUSD", Objects.nonNull(entity.getReconAmount()) ? entity.getReconAmount().toString() : "0.00");
                        responseBody.put("interchangeAmountAED", Objects.nonNull(entity.getIrfAmount()) ? entity.getIrfAmount().toString() : "");
                        responseBody.put("interchangeAmountUSD", Objects.nonNull(entity.getIrfAmountUsd()) ? entity.getIrfAmountUsd().toString() : "");
                        responseBody.put("interchangeRateDesignator", Objects.nonNull(entity.getIrd()) ? entity.getIrd().toString() : "");
                        responseBody.put("interchangeMatched", Objects.nonNull(entity.getMatchStatus()) ? "true" : "false");
                        responseBody.put("interchangeDifferenceAmount", Objects.nonNull(entity.getIrfDifference()) ? entity.getIrfDifference().toString() : "0.00");
                        responseBody.put("totalCount", String.valueOf(totalCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "Transaction not Found"));
                }
            }
        }
        catch (Exception e) {
            HashMap<String, String> errorResponse = new HashMap<String, String>();
            errorResponse.put("status", "Unexpected Error Occurred!");
            log.error("getMcTxnInchgDetails() :", (Throwable)e);
            responseBodyList.add(errorResponse);
        }
        return responseBodyList;
    }

    private Specification<McNetworkDataEntity> buildMcTxnInchgDetails(InquiryRequestVo requestVo) {
        try {
            if (Objects.nonNull(requestVo) && Objects.nonNull(requestVo.getFromDate()) && Objects.nonNull(requestVo.getToDate())) {
                LocalDateTime parsedFromDate = LocalDateTime.parse(requestVo.getFromDate(), DATE_TIME_FORMATTER);
                LocalDateTime parsedToDate = LocalDateTime.parse(requestVo.getToDate(), DATE_TIME_FORMATTER);
                return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                    Predicate filterConditions = criteriaBuilder.between((Expression)root.get("localTxnDateTime"), (Comparable)parsedFromDate, (Comparable)parsedToDate);
                    query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
                    return filterConditions;
                };
            }
            return (Specification & Serializable)(root, query, criteriaBuilder) -> {
                query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
                return criteriaBuilder.conjunction();
            };
        }
        catch (Exception e) {
            log.error("Error buildMcTxnInchgDetails() :", (Throwable)e);
            return null;
        }
    }

    public List<TxnDetailsResponseVo> getTxnDetails(@Valid TxnDetailsRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        Character domIntlFlag = Character.valueOf(requestVo.getDomIntlFlag() != null ? this.mapDomIntlToFlag(requestVo.getDomIntlFlag()).charValue() : (char)' ');
        Specification spec = this.buildTxnDetailsSpecification(requestVo.getMerchantId(), requestVo.getTerminalId(), this.mapChannel(requestVo.getChannel()), this.getTxnCode(requestVo.getTransactionType()), requestVo.getRrn(), requestVo.getStatus(), requestVo.getFromDate(), requestVo.getToDate(), requestVo.getMti(), domIntlFlag, requestVo.getBankId(), requestVo.getIncomingStatus(), requestVo.getOutgoingStatus(), requestVo.getCardAccIdCode(), requestVo.getCardAccTerminalId(), requestVo.getRetrievalRefNo());
        List<TxnDetailsResponseVo> response = new ArrayList<TxnDetailsResponseVo>();
        if (spec != null) {
            long totalCount = this.viewTxnInquiryRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.viewTxnInquiryRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                TxnDetailsResponseVo vo = this.mapToTxnDetailsResponseVo(entity);
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        }
        return response;
    }

    private Specification<ViewTxnInquiryDetails> buildTxnDetailsSpecification(String merchantId, String terminalId, String channel, String transactionType, String rrn, String status, String fromDate, String toDate, String mti, Character domOrIntl, String bankId, String incomingStatus, String outgoingStatus, String cadAccIdCode, String cardAccTerminalId, String retrievalRefNo) {
        try {
            return (Specification & Serializable)(root, query, cb) -> {
                Predicate conditions = cb.conjunction();
                if (merchantId != null && !merchantId.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("merchantId"), (Object)merchantId));
                }
                if (terminalId != null && !terminalId.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("terminalId"), (Object)terminalId));
                }
                if (channel != null && !channel.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal(cb.upper((Expression)root.get("network")), (Object)channel.toUpperCase()));
                }
                if (Objects.nonNull(transactionType) && !transactionType.isEmpty()) {
                    String[] txnTypes = transactionType.split(",");
                    conditions = txnTypes.length > 1 ? cb.and((Expression)conditions, (Expression)root.get("txnCode").in(Arrays.asList(txnTypes))) : (transactionType.matches("0130|0430|0410") ? cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("msgTypeId"), (Object)transactionType)) : cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("txnCode"), (Object)transactionType)));
                }
                if (rrn != null && !rrn.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("retrievalRefNumber"), (Object)rrn));
                }
                if ("SUCCESS".equalsIgnoreCase(status)) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("respCode"), (Object)"00"));
                } else if ("FAILED".equalsIgnoreCase(status)) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.notEqual((Expression)root.get("respCode"), (Object)"00"));
                }
                if (mti != null && !mti.isBlank()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("msgTypeId"), (Object)mti));
                }
                if (domOrIntl != null && domOrIntl.charValue() != ' ') {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("cardDomIntlFlag"), (Object)domOrIntl));
                }
                if (bankId != null && !bankId.isBlank()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("bankCode"), (Object)bankId));
                }
                if (incomingStatus != null && !incomingStatus.isBlank()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("incStatus"), (Object)incomingStatus));
                }
                if (outgoingStatus != null && !outgoingStatus.isBlank()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("outStatus"), (Object)outgoingStatus));
                }
                if (Objects.nonNull(cadAccIdCode) && !cadAccIdCode.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("merchantId"), (Object)cadAccIdCode));
                }
                if (Objects.nonNull(cardAccTerminalId) && !cardAccTerminalId.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("terminalId"), (Object)cardAccTerminalId));
                }
                if (Objects.nonNull(retrievalRefNo) && !retrievalRefNo.isEmpty()) {
                    conditions = cb.and((Expression)conditions, (Expression)cb.equal((Expression)root.get("retrievalRefNumber"), (Object)retrievalRefNo));
                }
                if (fromDate != null && toDate != null) {
                    LocalDateTime parsedFromDate = LocalDateTime.parse(fromDate, DATE_TIME_FORMATTER);
                    LocalDateTime parsedToDate = LocalDateTime.parse(toDate, DATE_TIME_FORMATTER);
                    conditions = cb.and((Expression)conditions, (Expression)cb.between((Expression)root.get("txnDateTime"), (Comparable)parsedFromDate, (Comparable)parsedToDate));
                }
                query.orderBy(new Order[]{cb.desc((Expression)root.get("serialNumber"))});
                return conditions;
            };
        }
        catch (Exception e) {
            log.error("Error in buildTxnDetailsSpecification():", (Throwable)e);
            return null;
        }
    }

    private TxnDetailsResponseVo mapToTxnDetailsResponseVo(ViewTxnInquiryDetails entity) {
        TxnDetailsResponseVo vo = new TxnDetailsResponseVo();
        try {
            IRFCallbackEntity irfCallBackEntity;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            vo.setScheme(Objects.requireNonNullElse(entity.getSchema(), ""));
            vo.setMerchantId(Objects.requireNonNullElse(entity.getMerchantId(), ""));
            vo.setTerminalId(Objects.requireNonNullElse(entity.getTerminalId(), ""));
            vo.setRrn(Objects.requireNonNullElse(entity.getRetrievalRefNumber(), ""));
            vo.setIrdCode(Objects.requireNonNullElse(entity.getIrd(), ""));
            vo.setDccIndicator(Objects.requireNonNullElse(entity.getDccIndicator(), Character.valueOf(' ')));
            if (Objects.nonNull(entity.getDccIndicator())) {
                vo.setDccAmount(entity.getDccAmount() != null ? BigDecimal.valueOf(entity.getDccAmount()).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
                vo.setDccCurrency(Objects.requireNonNullElse(entity.getDccCurrency(), ""));
                vo.setExchangeRate(Objects.requireNonNullElse(entity.getDccExchangeRate(), 0.0).doubleValue());
            }
            double irfAmount = entity.getIrfAmount() != null ? new BigDecimal(entity.getIrfAmount()).setScale(2, RoundingMode.HALF_UP).doubleValue() : 0.0;
            vo.setIrfAmount(irfAmount);
            double irfAmountUsd = entity.getIrfAmountUsd() != null ? new BigDecimal(entity.getIrfAmountUsd()).setScale(2, RoundingMode.HALF_UP).doubleValue() : 0.0;
            vo.setIrfAmountUSD(irfAmountUsd);
            vo.setTxnAmount(Objects.requireNonNullElse(entity.getTxnAmount(), 0.0).doubleValue());
            vo.setDomIntlFlag(entity.getCardDomIntlFlag() != null ? this.mapDomIntlFlag(entity.getCardDomIntlFlag().toString()) : "");
            vo.setCardNumber(Objects.requireNonNullElse(entity.getCardNumber(), ""));
            vo.setCardType(entity.getCardType() != null ? this.mapCardType(entity.getCardType().toString()) : "");
            vo.setTxnType(Objects.nonNull(entity.getTxnCode()) ? this.getTxnType(entity.getTxnCode(), entity.getMsgTypeId()) : "");
            vo.setPercentage(Objects.requireNonNullElse(entity.getIrfPercent(), 0.0).doubleValue());
            vo.setIrfMin(entity.getIrfMinAmount() != null ? BigDecimal.valueOf(entity.getIrfMinAmount()).toPlainString() : "0");
            vo.setIrfMax(entity.getIrfMaxAmount() != null ? BigDecimal.valueOf(entity.getIrfMaxAmount()).toPlainString() : "0");
            vo.setFixed(Objects.requireNonNullElse(entity.getIrfFixed(), 0.0).doubleValue());
            vo.setDescription(Objects.requireNonNullElse(entity.getRemarks(), ""));
            vo.setNetwork(this.mapNetwork(entity.getNetwork()));
            vo.setTxnDate(entity.getTxnDateTime() != null ? entity.getTxnDateTime().format(formatter) : "");
            vo.setLocalDate(entity.getLocalDateTime() != null ? entity.getLocalDateTime().format(formatter) : "");
            vo.setResponseCode(Objects.requireNonNullElse(entity.getRespCode(), ""));
            vo.setTxnStatus(this.getResponseCode(entity.getRespCode()));
            vo.setIncomingStatus(Objects.requireNonNullElse(entity.getIncStatus(), ""));
            vo.setOutgoingStatus(Objects.requireNonNullElse(entity.getOutStatus(), ""));
            vo.setMti(Objects.requireNonNullElse(entity.getMsgTypeId(), ""));
            vo.setBankId(Objects.requireNonNullElse(entity.getBankCode(), ""));
            vo.setRevIndicator(entity.getRevIndicator() != null ? (this.isTrueIndicator(entity.getRevIndicator()) ? "true" : "false") : "false");
            vo.setOriginalRRN(Objects.nonNull(entity.getOriginalRrn()) ? entity.getOriginalRrn() : "");
            if ("VISA".equalsIgnoreCase(entity.getNetwork())) {
                vo.setTransactionId(Objects.requireNonNullElse(entity.getTxnId(), ""));
            } else if ("MCI".equalsIgnoreCase(entity.getNetwork())) {
                vo.setTraceId(Objects.requireNonNullElse(entity.getNetworkData(), ""));
            }
            vo.setProcessingCode(Objects.requireNonNullElse(entity.getProcCode(), ""));
            if ("VISA".equalsIgnoreCase(entity.getNetwork())) {
                vo.setInterchangeFeeAmountLocal(entity.getVisaInchgFeeAmount() != null ? entity.getVisaInchgFeeAmount() : 0.0);
                vo.setInterchangeFeeSign(Objects.requireNonNullElse(entity.getVisaInchgFeeSign(), ""));
                vo.setFeeDescriptor(Objects.requireNonNullElse(entity.getVisaFeeDesc(), ""));
                vo.setInterchangeMatched(Objects.requireNonNullElse(entity.getVisaIrfMatch(), ""));
                vo.setInterchangeDifferenceAmount(entity.getVisaIrfDiff() != null ? Math.abs(entity.getVisaIrfDiff()) : 0.0);
                vo.setTxnAmountLocal(entity.getVisaTxnAmount() != null ? entity.getVisaTxnAmount() : 0.0);
            }
            if ("MCI".equalsIgnoreCase(entity.getNetwork())) {
                vo.setTransactionAmountLocal(entity.getMciTxnAmount() != null ? entity.getMciTxnAmount() : 0.0);
                vo.setReconciliationAmountUSD(entity.getMciReconAmount() != null ? entity.getMciReconAmount() : 0.0);
                vo.setInterchangeAmountLocal(entity.getMciTxnIrfAmount() != null ? entity.getMciTxnIrfAmount() : 0.0);
                vo.setInterchangeAmountUSD(entity.getMciTxnIrfUsdAmount() != null ? entity.getMciTxnIrfUsdAmount() : 0.0);
                vo.setInterchangeRateDesignator(Objects.requireNonNullElse(entity.getMciIrd(), ""));
                vo.setInterchangeMatched(Objects.requireNonNullElse(entity.getMciIrfMatch(), ""));
                vo.setInterchangeDifferenceAmount(entity.getMciIrfDiff() != null ? Math.abs(entity.getMciIrfDiff()) : 0.0);
                vo.setFileID(Objects.requireNonNullElse(entity.getFileId(), ""));
                vo.setBusinessCycle(Objects.requireNonNullElse(entity.getBusinessCycle(), ""));
                vo.setApprovalCode(Objects.requireNonNullElse(entity.getApprovalCode(), ""));
            }
            if ("UAESWITCH".equalsIgnoreCase(entity.getNetwork())) {
                vo.setUaesIrf(entity.getUaeSwitchIrf() != null ? entity.getUaeSwitchIrf() : 0.0);
                vo.setUaesPF1(entity.getUaeSwitchPF1() != null ? entity.getUaeSwitchPF1() : 0.0);
                vo.setUaesPF2(entity.getUaeSwitchPF2() != null ? entity.getUaeSwitchPF2() : 0.0);
            }
            if ("VSMS".equalsIgnoreCase(entity.getNetwork())) {
                vo.setTransactionId(Objects.requireNonNullElse(entity.getVisaSmsTxnId(), ""));
                vo.setInterchangeFeeAmountLocal(entity.getVisaSmsIrfAmount() != null ? entity.getVisaSmsIrfAmount() : 0.0);
                vo.setInterchangeFeeSign(Objects.requireNonNullElse(entity.getVisaSmsInchgFeeSign(), ""));
                vo.setFeeDescriptor("");
                vo.setInterchangeMatched(Objects.requireNonNullElse(entity.getVisaSmsIrfMatch(), ""));
                vo.setInterchangeDifferenceAmount(entity.getVisaSmsIrfDiff() != null ? Math.abs(entity.getVisaSmsIrfDiff()) : 0.0);
            }
            if ((irfCallBackEntity = this.irfCallbackRepo.findByRefSerNumber(entity.getSerialNumber())) != null) {
                int status = irfCallBackEntity.getStatus() != null ? (int)irfCallBackEntity.getStatus().charValue() : 32;
                vo.setIrfCallbackStatus(switch (status) {
                    case 83 -> "Success";
                    case 70 -> "Failed";
                    case 80 -> "Pending";
                    default -> "";
                });
                vo.setRefCode(String.valueOf(irfCallBackEntity.getRefSerNumber()));
            } else {
                vo.setIrfCallbackStatus("");
                vo.setRefCode("");
            }
        }
        catch (Exception e) {
            log.error("Error in mapToTxnDetailsResponseVo():", (Throwable)e);
        }
        return vo;
    }

    public List<Map<String, String>> getReportDetails(@Valid ReportRequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            String insShortName = Objects.requireNonNull(this.env.getProperty("INS_SHORT_NAME"), "INS_SHORT_NAME is null");
            String path = this.env.getProperty("RECON_REPORT_" + insShortName);
            if (Objects.nonNull(requestVo.getFromDate()) && Objects.isNull(requestVo.getToDate()) || Objects.nonNull(requestVo.getToDate()) && Objects.isNull(requestVo.getFromDate())) {
                throw new ValidationException("Invalid date fields!");
            }
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildReportGenerationSpecification(requestVo.getFromDate(), requestVo.getToDate(), requestVo.getReportName(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                Page pageList = this.reportGenerationRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody = new HashMap<String, String>();
                        responseBody.put("reportName", entity.getReportName());
                        responseBody.put("status", entity.getStatus());
                        responseBody.put("fileName", String.valueOf(entity.getFileName()));
                        if (entity.getStatus().equals("Completed")) {
                            responseBody.put("report path", path);
                        }
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "No History Found"));
                }
            }
        }
        catch (Exception e) {
            log.error("Error getReportDetails() :", (Throwable)e);
            responseBodyList.add(Collections.singletonMap("message", "No History Found"));
        }
        return responseBodyList;
    }

    private Specification<ReportGenerationLogEntity> buildReportGenerationSpecification(String fromDate, String toDate, String reportName, String fileName) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (reportName != null && !reportName.isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("reportName"), (Object)reportName));
            }
            if (fileName != null && !fileName.isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("fileName"), (Object)fileName));
            }
            if (fromDate != null && toDate != null) {
                try {
                    LocalDateTime fromDateTime = LocalDateTime.parse(fromDate, DATE_TIME_FORMATTER);
                    LocalDateTime toDateTime = LocalDateTime.parse(toDate, DATE_TIME_FORMATTER);
                    predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.between((Expression)root.get("reportDate"), (Comparable)fromDateTime, (Comparable)toDateTime));
                }
                catch (DateTimeParseException e) {
                    log.error("Invalid date format for fromDate or toDate: {}", (Object)e.getMessage(), (Object)e);
                }
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
            return predicate;
        };
    }

    public List<InterchangeResponseVo> getUAESwitchInterchange(@Valid InterchangeRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        Specification spec = this.buildUAESwitchInterchangeSpecification(requestVo);
        List<InterchangeResponseVo> response = new ArrayList<InterchangeResponseVo>();
        if (spec != null) {
            long totalCount = this.uaeSwitchIrfRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.uaeSwitchIrfRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                InterchangeResponseVo vo = this.mapToUAESwitchInterchangeResponseVo(entity);
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        }
        return response;
    }

    private InterchangeResponseVo mapToUAESwitchInterchangeResponseVo(UaeSwitchIrfEntity entity) {
        InterchangeResponseVo vo = new InterchangeResponseVo();
        try {
            vo.setSegment(Objects.requireNonNullElse(entity.getSegment(), ""));
            vo.setSegmentDesc(Objects.requireNonNullElse(entity.getSegmentDesc(), ""));
            vo.setMcc(Objects.requireNonNullElse(entity.getMcc(), ""));
            vo.setMccDescription(Objects.requireNonNullElse(entity.getMccDesc(), ""));
            vo.setPosIrf(Objects.requireNonNullElse(entity.getPosIrf(), 0.0));
            vo.setEcomIrf(Objects.requireNonNullElse(entity.getEcomIrf(), 0.0));
            vo.setPosMaxIrf(Objects.requireNonNullElse(entity.getPosIrfMax(), 0.0));
            vo.setEcomMaxIrf(Objects.requireNonNullElse(entity.getEcomIrfMax(), 0.0));
            vo.setIrfRate(Objects.requireNonNullElse(entity.getIrfRate(), 0.0));
            vo.setIrfMax(Objects.requireNonNullElse(entity.getIrfMax(), 0.0));
            vo.setIrfFixed(Objects.requireNonNullElse(entity.getIrfFixed(), 0.0));
        }
        catch (Exception e) {
            log.error("Error in mapToUAESwitchInterchangeResponseVo():", (Throwable)e);
        }
        return vo;
    }

    private Specification<UaeSwitchIrfEntity> buildUAESwitchInterchangeSpecification(@Valid InterchangeRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (requestVo.getSegment() != null && !requestVo.getSegment().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("segment"), (Object)requestVo.getSegment()));
            }
            if (requestVo.getSegmentDesc() != null && !requestVo.getSegmentDesc().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("segmentDesc"), (Object)requestVo.getSegmentDesc()));
            }
            if (requestVo.getMcc() != null && !requestVo.getMcc().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("mcc"), (Object)requestVo.getMcc()));
            }
            if (requestVo.getMccDescription() != null && !requestVo.getMccDescription().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("mccDesc"), (Object)requestVo.getMccDescription()));
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serNumber"))});
            return predicate;
        };
    }

    public List<RejectedTransactionDetailsVo> getRejectedMcTxns(InquiryRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        List<RejectedTransactionDetailsVo> response = new ArrayList<RejectedTransactionDetailsVo>();
        Specification spec = this.buildMcRejectionDetailsSpecification(requestVo);
        if (spec != null) {
            long totalCount = this.viewMcRejectionDetailsRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.viewMcRejectionDetailsRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                RejectedTransactionDetailsVo responseVo = this.mapToRejectedTransactionDetailsVo(entity);
                responseVo.setTotalCount(Integer.valueOf((int)totalCount));
                responseVo.setTotalPage(Integer.valueOf(totalPage));
                return responseVo;
            }).toList();
        }
        return response;
    }

    private Specification<ViewMcRejectionDetails> buildMcRejectionDetailsSpecification(InquiryRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (Objects.nonNull(requestVo.getFromDate()) && Objects.nonNull(requestVo.getToDate())) {
                try {
                    LocalDateTime fromDateTime = LocalDateTime.parse(requestVo.getFromDate(), DATE_TIME_FORMATTER);
                    LocalDateTime toDateTime = LocalDateTime.parse(requestVo.getToDate(), DATE_TIME_FORMATTER);
                    predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.between((Expression)root.get("businessDate"), (Comparable)fromDateTime, (Comparable)toDateTime));
                }
                catch (DateTimeParseException e) {
                    log.error("Invalid date format for fromDate or toDate: {}", (Object)e.getMessage(), (Object)e);
                }
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
            return predicate;
        };
    }

    private RejectedTransactionDetailsVo mapToRejectedTransactionDetailsVo(ViewMcRejectionDetails entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            RejectedTransactionDetailsVo responseVo = new RejectedTransactionDetailsVo();
            responseVo.setTransactionType(entity.getTxnType());
            responseVo.setMti(entity.getMsgTypeId());
            responseVo.setCardNumber(entity.getCardNumber());
            responseVo.setProcessingCode(entity.getProcCode());
            responseVo.setTransactionAmount(entity.getTxnAmount() == null ? "0.0" : Double.toString(entity.getTxnAmount()));
            responseVo.setSurchargeAmount(entity.getSchgAmount() == null ? "0.0" : Double.toString(entity.getSchgAmount()));
            try {
                responseVo.setLocalDateTime(entity.getLocalDateTime().format(formatter));
            }
            catch (Exception e) {
                responseVo.setLocalDateTime(null);
            }
            responseVo.setExpiryDate(entity.getExpiryDate());
            responseVo.setPosDataCode(entity.getPosDataCode());
            responseVo.setFunctionCode(entity.getFunctionCode());
            responseVo.setMessageReasonCode(entity.getReasonCode());
            responseVo.setMcc(entity.getMcc());
            responseVo.setArn(entity.getAcqRefNumber());
            responseVo.setAcquireInstitutionId(entity.getAcqInstIdCode());
            responseVo.setRrn(entity.getRetRefNumber());
            responseVo.setApprovalCode(entity.getApprCode());
            responseVo.setResponseCode(entity.getRespCode());
            responseVo.setSeviceCode(entity.getServiceCode());
            responseVo.setTerminalId(entity.getTerminalId());
            responseVo.setMerchantId(entity.getMerchantId());
            responseVo.setMerchantName(entity.getMeName());
            responseVo.setMerchantCity(entity.getMeCity());
            responseVo.setMerchantZipCode(entity.getMeZipCode());
            responseVo.setMerchantCountry(entity.getMeCountry());
            responseVo.setTerminalType(entity.getTrlType());
            responseVo.setEcomIndicator(entity.getEcomIndicator());
            responseVo.setTransactionFeeAmount(entity.getTxnFeeAmount() == null ? "0.0" : Double.toString(entity.getTxnFeeAmount()));
            try {
                responseVo.setTransactionCurrencyExponent(entity.getTxnCurExponent().toString());
            }
            catch (Exception e) {
                responseVo.setTransactionCurrencyExponent(null);
            }
            responseVo.setTransactionCurrencyCode(entity.getTxnCurCode());
            responseVo.setIrd(entity.getMctIrd());
            try {
                responseVo.setSettledIndicator(entity.getSetlFlag().toString());
            }
            catch (Exception e) {
                responseVo.setSettledIndicator(null);
            }
            responseVo.setCardSequenceNumber(entity.getCardSeqNumber());
            responseVo.setApplicationCryptogram(entity.getAppCryptogram());
            responseVo.setCryptogramInformationData(entity.getCryptInfoData());
            responseVo.setIssuerApplicationData(entity.getAppData());
            responseVo.setUpblNumber(entity.getUpblNumber());
            responseVo.setApplicationTransactionCounter(entity.getAppTxnCounter());
            responseVo.setTerminalVerificationResult(entity.getTerminalVerificationResult());
            try {
                responseVo.setTransactionDate(entity.getTransactionDate().format(formatter));
            }
            catch (Exception e) {
                responseVo.setTransactionDate(null);
            }
            responseVo.setChipTransactionDate(entity.getChipTransactionDate());
            responseVo.setChipTransactionType(entity.getChipTransactionType());
            responseVo.setCryptAmount(entity.getCryptAmount() == null ? "0.0" : Double.toString(entity.getCryptAmount()));
            responseVo.setApplicationInterchangeProfile(entity.getApplicationInterchangeProfile());
            responseVo.setTerminalCountryCode(entity.getTerminalCountryCode());
            responseVo.setCashbackAmount(entity.getCashbackAmount() == null ? "0.0" : Double.toString(entity.getCashbackAmount()));
            responseVo.setCvmResult(entity.getCvmResult());
            responseVo.setTerminalCapabilities(entity.getTerminalCapabilities());
            responseVo.setTcc(entity.getTcc());
            responseVo.setChipCurrencyCode(entity.getChipCurrencyCode());
            responseVo.setChipTerminalType(entity.getChipTerminalType());
            responseVo.setTerminalApplicationVerificationNumber(entity.getTerminalApplicationVerificationNumber());
            responseVo.setTransactionSequenceCounter(entity.getTransactionSequenceCounter());
            responseVo.setIssuerAuthData(entity.getIssuerAuthData());
            responseVo.setTransactionLifeCycleId(entity.getTransactionLifeCycleId());
            responseVo.setMessageNumber(entity.getMessageNumber());
            responseVo.setMemberText(entity.getMemberText());
            responseVo.setIndependentSalesOrganizationId(entity.getOrganizationInstIdCode());
            responseVo.setReversalIndicator(entity.getReversalIndicator() == null ? "" : entity.getReversalIndicator().toString());
            responseVo.setMastercardAssignedId(entity.getMastercardAssignedId());
            responseVo.setCardType(entity.getCardType() == null ? "" : entity.getCardType().toString());
            responseVo.setDomesticInternationFlag(entity.getDomesticInternationFlag() == null ? "" : entity.getDomesticInternationFlag().toString());
            responseVo.setSmsDmsFlag(entity.getSmsDmsFlag().toString());
            responseVo.setPosPgType(entity.getPosPgType());
            try {
                responseVo.setCentralProcessingDate(entity.getCentralProcessingDate().format(formatter));
            }
            catch (Exception e) {
                responseVo.setCentralProcessingDate(null);
            }
            try {
                responseVo.setOutgoingFileDate(entity.getOutgoingFileDate().format(formatter));
            }
            catch (Exception e) {
                responseVo.setOutgoingFileDate(null);
            }
            responseVo.setFileId(entity.getRejectedField());
            responseVo.setEncryptedCardNumber(entity.getEncryptedCardNumber());
            responseVo.setMeCountryOfOrigin(entity.getCountryOfOrigin());
            return responseVo;
        }
        catch (Exception e) {
            log.error("mapToRejectedTransactionDetailsVo Failed :", (Throwable)e);
            return null;
        }
    }

    public List<OutgoingSchedulerResponseVo> getAllSchedulederCycles(int page, int size) {
        ArrayList<OutgoingSchedulerResponseVo> response = new ArrayList();
        int defaultPageNumber = Math.max(page - 1, 0);
        long totalCount = this.outgoingSchedulerRepo.count();
        int totalPage = (int)Math.ceil((double)totalCount / (double)size);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        Page pageList = this.outgoingSchedulerRepo.findAll((Pageable)pageable);
        response = pageList.stream().map(entity -> {
            OutgoingSchedulerResponseVo responseVo = this.mapOutgoingCycleList(entity, totalCount, totalPage);
            responseVo.setTotalCount(Integer.valueOf((int)totalCount));
            responseVo.setTotalPage(Integer.valueOf(totalPage));
            return responseVo;
        }).toList();
        return response;
    }

    private OutgoingSchedulerResponseVo mapOutgoingCycleList(OutgoingSchedulerEntity cycleDetails, long totalCount, int totalPage) {
        try {
            OutgoingSchedulerResponseVo valueObject = new OutgoingSchedulerResponseVo();
            valueObject.setName(cycleDetails.getTaskId());
            valueObject.setNetwork(cycleDetails.getNetwork());
            valueObject.setEndTime(cycleDetails.getEndTime());
            valueObject.setIsActive(Boolean.valueOf(cycleDetails.getGenStatus().charValue() == 'A'));
            valueObject.setCreatedDateTime(cycleDetails.getLastUpdated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            valueObject.setTotalCount(Integer.valueOf((int)totalCount));
            valueObject.setTotalPage(Integer.valueOf(totalPage));
            return valueObject;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, String>> getCyberSourceUploadLog(@Valid RequestVo requestVo, int page, int size) {
        List<Map<String, String>> responseBodyList = new ArrayList<Map<String, String>>();
        try {
            Integer systemForCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("CYBERSOURCE_SYSTEM_FORMAT_CODE"), "The property CYBERSOURCE_SYSTEM_FORMAT_CODE not found!"));
            if (Objects.nonNull(requestVo.getFromProcessedDate()) && Objects.isNull(requestVo.getToProcessedDate()) || Objects.nonNull(requestVo.getToProcessedDate()) && Objects.isNull(requestVo.getFromProcessedDate())) {
                throw new ValidationException("Invalid date fields!");
            }
            int genStatus = this.getUploadGenStatus.applyAsInt(Objects.nonNull(requestVo.getStatus()) ? requestVo.getStatus().toLowerCase() : "");
            int defaultPageNumber = page - 1;
            PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
            Specification spec = this.buildMPGSOrCyberSourceUploadLogSpecification(systemForCode, requestVo.getBussDate(), genStatus, requestVo.getFromProcessedDate(), requestVo.getToProcessedDate(), requestVo.getFileName());
            if (Objects.nonNull(spec)) {
                long totalTxnCount = this.fileUploadRepo.count(spec);
                int totalPage = (int)Math.ceil((double)totalTxnCount / (double)size);
                Page pageList = this.fileUploadRepo.findAll(spec, (Pageable)pageable);
                if (!pageList.getContent().isEmpty()) {
                    responseBodyList = pageList.getContent().stream().map(entity -> {
                        HashMap<String, String> responseBody = new HashMap<String, String>();
                        Integer totalCount = Objects.isNull(entity.getTotalTxnCount()) ? 0 : entity.getTotalTxnCount();
                        Integer acceptedCount = Objects.isNull(entity.getTotalAcceptedTxnCount()) ? 0 : entity.getTotalAcceptedTxnCount();
                        Integer rejectedCount = totalCount - acceptedCount;
                        responseBody.put("fileName", entity.getFileName());
                        responseBody.put("status", (String)this.getUploadStatus.apply(entity.getUploadStatus()));
                        if (Objects.nonNull(entity.getProcessingJobs())) {
                            responseBody.put("startTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessStartTime()));
                            responseBody.put("endTime", this.convertDateTimeToString(entity.getProcessingJobs().getProcessEndTime()));
                        } else {
                            responseBody.put("startTime", "");
                            responseBody.put("endTime", "");
                        }
                        responseBody.put("acceptedCount", String.valueOf(acceptedCount));
                        responseBody.put("rejectedCount", String.valueOf(rejectedCount));
                        responseBody.put("totalCount", String.valueOf(totalTxnCount));
                        responseBody.put("totalPage", String.valueOf(totalPage));
                        return responseBody;
                    }).collect(Collectors.toList());
                } else {
                    responseBodyList.add(Collections.singletonMap("message", "No History Found"));
                }
            }
        }
        catch (Exception e) {
            log.error("Error getCyberSourceUploadLog() :", (Throwable)e);
            responseBodyList.add(Collections.singletonMap("message", "Unexpected Error Occurred!"));
        }
        return responseBodyList;
    }

    public List<OmanNetinterchangeResponseVo> getOmanNetIrfConfig(int page, int size) {
        ArrayList<OmanNetinterchangeResponseVo> response = new ArrayList();
        int defaultPageNumber = Math.max(page - 1, 0);
        long totalCount = this.outgoingSchedulerRepo.count();
        int totalPage = (int)Math.ceil((double)totalCount / (double)size);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        Page pageList = this.omanNetIRFRepo.findAll((Pageable)pageable);
        response = pageList.stream().map(entity -> {
            OmanNetinterchangeResponseVo responseVo = this.mapOmanNetIrf(entity, totalCount, totalPage);
            responseVo.setTotalCount(Integer.valueOf((int)totalCount));
            responseVo.setTotalPage(Integer.valueOf(totalPage));
            return responseVo;
        }).toList();
        return response;
    }

    private OmanNetinterchangeResponseVo mapOmanNetIrf(OmanNetIRFEntity omanNetEntity, long totalCount, int totalPage) {
        try {
            OmanNetinterchangeResponseVo valueObject = new OmanNetinterchangeResponseVo();
            valueObject.setReferenceNumber(omanNetEntity.getSerialNumber());
            valueObject.setRoute(omanNetEntity.getRoute());
            valueObject.setSubRoute(omanNetEntity.getSubRoute());
            valueObject.setCardType(omanNetEntity.getCardType().toString());
            valueObject.setMcc(omanNetEntity.getMcc());
            valueObject.setMccDescription(omanNetEntity.getMccDesc());
            valueObject.setSegment(omanNetEntity.getSegmentDesc());
            valueObject.setSegmentDesc(omanNetEntity.getSegmentDesc());
            valueObject.setIrfFixed(omanNetEntity.getIrfFixed());
            valueObject.setIrfMax(omanNetEntity.getIrfMax());
            valueObject.setIrfRatePercent(omanNetEntity.getIrfPercentage());
            valueObject.setTotalCount(Integer.valueOf((int)totalCount));
            valueObject.setTotalPage(Integer.valueOf(totalPage));
            return valueObject;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<InterchangeResponseVo> getJaywanInterchange(@Valid InterchangeRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        Specification spec = this.buildJaywanInterchangeSpecification(requestVo);
        List<InterchangeResponseVo> response = new ArrayList<InterchangeResponseVo>();
        if (spec != null) {
            long totalCount = this.jaywanIrfRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.jaywanIrfRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                InterchangeResponseVo vo = this.mapToJaywanInterchangeResponseVo(entity);
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        }
        return response;
    }

    private InterchangeResponseVo mapToJaywanInterchangeResponseVo(JaywanIRFEntity entity) {
        InterchangeResponseVo vo = new InterchangeResponseVo();
        try {
            vo.setSegment(Objects.requireNonNullElse(entity.getSegment(), ""));
            vo.setSegmentDesc(Objects.requireNonNullElse(entity.getSegmentDesc(), ""));
            vo.setMcc(Objects.requireNonNullElse(entity.getMcc(), ""));
            vo.setMccDescription(Objects.requireNonNullElse(entity.getMccDesc(), ""));
            vo.setPosIrf(Objects.requireNonNullElse(entity.getPosIrf(), 0.0));
            vo.setEcomIrf(Objects.requireNonNullElse(entity.getEcomIrf(), 0.0));
            vo.setPosMaxIrf(Objects.requireNonNullElse(entity.getPosIrfMax(), 0.0));
            vo.setEcomMaxIrf(Objects.requireNonNullElse(entity.getEcomIrfMax(), 0.0));
            vo.setIrfRate(Objects.requireNonNullElse(entity.getIrfRate(), 0.0));
            vo.setIrfMax(Objects.requireNonNullElse(entity.getIrfMax(), 0.0));
            vo.setIrfFixed(Objects.requireNonNullElse(entity.getIrfFixed(), 0.0));
        }
        catch (Exception e) {
            log.error("Error in mapToJaywanInterchangeResponseVo():", (Throwable)e);
        }
        return vo;
    }

    private Specification<JaywanIRFEntity> buildJaywanInterchangeSpecification(@Valid InterchangeRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (requestVo.getSegment() != null && !requestVo.getSegment().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("segment"), (Object)requestVo.getSegment()));
            }
            if (requestVo.getSegmentDesc() != null && !requestVo.getSegmentDesc().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("segmentDesc"), (Object)requestVo.getSegmentDesc()));
            }
            if (requestVo.getMcc() != null && !requestVo.getMcc().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("mcc"), (Object)requestVo.getMcc()));
            }
            if (requestVo.getMccDescription() != null && !requestVo.getMccDescription().isBlank()) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("mccDesc"), (Object)requestVo.getMccDescription()));
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
            return predicate;
        };
    }

    public List<SchemeFundDetailsResponseVo> getSchemeFundDetails(@Valid SchemeFundDetailsRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        ArrayList<SchemeFundDetailsResponseVo> response = new ArrayList();
        if ("MASTERCARD".equalsIgnoreCase(requestVo.getNetwork())) {
            Specification spec = this.buildSchemeFundMcDetailsSpecification(requestVo);
            long totalCount = this.viewMCNetReconDetailsRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.viewMCNetReconDetailsRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                SchemeFundDetailsResponseVo vo = this.mapToSchemeFundDetailsResponseVo(entity, null, "MASTERCARD");
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        } else {
            Specification spec = this.buildSchemeFundVisaDetailsSpecification(requestVo);
            long totalCount = this.visaFundsTransferDetailsRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.visaFundsTransferDetailsRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                SchemeFundDetailsResponseVo vo = this.mapToSchemeFundDetailsResponseVo(null, entity, "VISA");
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        }
        return response;
    }

    private SchemeFundDetailsResponseVo mapToSchemeFundDetailsResponseVo(ViewMCNetReconDetailsEntity mcEntity, ViewVisaFundsTransferDetailsEntity visaEntity, String network) {
        SchemeFundDetailsResponseVo vo = new SchemeFundDetailsResponseVo();
        vo.setNetwork(Objects.requireNonNullElse(network, ""));
        try {
            switch (network == null ? "" : network.toUpperCase()) {
                case "MASTERCARD": {
                    if (mcEntity != null) {
                        vo.setBusinessDate(mcEntity.getBusinessDate());
                        vo.setTxnCount(Objects.requireNonNullElse(mcEntity.getTotalTxnCount(), 0L).longValue());
                        vo.setCreditAmount(Objects.requireNonNullElse(mcEntity.getTotalCreditAmount(), 0.0).doubleValue());
                        vo.setDebitAmount(Objects.requireNonNullElse(mcEntity.getTotalDebitAmount(), 0.0).doubleValue());
                        vo.setTotalAmount(Objects.requireNonNullElse(mcEntity.getTotalNetAmount(), 0.0).doubleValue());
                    }
                    break;
                }
                case "VISA": {
                    if (visaEntity != null) {
                        vo.setBusinessDate(visaEntity.getBusinessDate());
                        vo.setTxnCount(Objects.requireNonNullElse(visaEntity.getVisaTxnCount(), 0L).longValue());
                        vo.setCreditAmount(Objects.requireNonNullElse(visaEntity.getVisaTxnCredit(), 0.0).doubleValue());
                        vo.setDebitAmount(Objects.requireNonNullElse(visaEntity.getVisaTxnDebit(), 0.0).doubleValue());
                        vo.setTotalAmount(Objects.requireNonNullElse(visaEntity.getVisaTxnTotal(), 0.0).doubleValue());
                    }
                    break;
                }
                default: {
                    log.warn("Unsupported network: {}", (Object)network);
                    break;
                }
            }
        }
        catch (Exception e) {
            log.error("Error in mapToSchemeFundDetailsResponseVo()", (Throwable)e);
        }
        return vo;
    }

    private Specification<ViewVisaFundsTransferDetailsEntity> buildSchemeFundVisaDetailsSpecification(@Valid SchemeFundDetailsRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            LocalDate businessDate = this.parseBusinessDate(requestVo.getBusinessDate());
            if (businessDate != null) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("businessDate"), (Object)businessDate));
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("businessDate"))});
            return predicate;
        };
    }

    private Specification<ViewMCNetReconDetailsEntity> buildSchemeFundMcDetailsSpecification(@Valid SchemeFundDetailsRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            LocalDate businessDate = this.parseBusinessDate(requestVo.getBusinessDate());
            if (businessDate != null) {
                predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("businessDate"), (Object)businessDate));
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("businessDate"))});
            return predicate;
        };
    }

    private LocalDate parseBusinessDate(String businessDate) {
        if (businessDate == null || businessDate.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(businessDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid business date format. Expected dd/MM/yyyy");
        }
    }

    public List<FeeCollectionResponseVo> getFeeCollectionDetails(@Valid InquiryRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        Specification spec = this.buildFeeCollectionSpecification(requestVo);
        List<FeeCollectionResponseVo> response = new ArrayList<FeeCollectionResponseVo>();
        if (spec != null) {
            long totalCount = this.mcIpmFeesRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.mcIpmFeesRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                FeeCollectionResponseVo vo = this.mapToFeeCollectionResponseVo(entity);
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        }
        return response;
    }

    private FeeCollectionResponseVo mapToFeeCollectionResponseVo(MCIpmFeesEntity entity) {
        FeeCollectionResponseVo vo = new FeeCollectionResponseVo();
        try {
            vo.setIcaNumber(Objects.requireNonNullElse(entity.getIcaNumber(), ""));
            vo.setFileId(Objects.requireNonNullElse(entity.getFileId(), ""));
            vo.setProcessCode(Objects.requireNonNullElse(entity.getProcCode(), ""));
            vo.setTxnAmount(entity.getTxnAmount() != null ? entity.getTxnAmount() : 0.0);
            vo.setTxnCurrency(entity.getTxnCurrCode() != null ? String.valueOf(entity.getTxnCurrCode()) : "");
            vo.setReconAmount(entity.getReconAmount() != null ? entity.getReconAmount() : 0.0);
            vo.setMemberText(Objects.requireNonNullElse(entity.getMemberText(), ""));
        }
        catch (Exception e) {
            log.error("Error in mapToFeeCollectionResponseVo():", (Throwable)e);
        }
        return vo;
    }

    private Specification<MCIpmFeesEntity> buildFeeCollectionSpecification(@Valid InquiryRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.notEqual((Expression)root.get("genStatus"), (Object)0));
            if (Objects.nonNull(requestVo.getFromDate()) && Objects.nonNull(requestVo.getToDate())) {
                try {
                    LocalDateTime fromDateTime = LocalDateTime.parse(requestVo.getFromDate(), DATE_TIME_FORMATTER);
                    LocalDateTime toDateTime = LocalDateTime.parse(requestVo.getToDate(), DATE_TIME_FORMATTER);
                    predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.between((Expression)root.get("businessDate"), (Comparable)fromDateTime, (Comparable)toDateTime));
                }
                catch (DateTimeParseException e) {
                    log.error("Invalid date format for fromDate or toDate: {}", (Object)e.getMessage(), (Object)e);
                }
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
            return predicate;
        };
    }

    public List<ChargeBackResponseVo> getChargeBackDetails(@Valid InquiryAPIRequestVo requestVo, int page, int size) {
        int defaultPageNumber = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of((int)defaultPageNumber, (int)size);
        ArrayList<ChargeBackResponseVo> response = new ArrayList();
        if ("MASTERCARD".equalsIgnoreCase(requestVo.getNetwork())) {
            Specification spec = this.buildMcChargeBackDetailsSpecification(requestVo);
            long totalCount = this.mcAcquirerExceptionsRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.mcAcquirerExceptionsRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                ChargeBackResponseVo vo = this.mapToChargeBackDetailsResponseVo(entity, null, "MASTERCARD");
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        } else {
            Specification spec = this.buildVisaChargeBackDetailsSpecification(requestVo);
            long totalCount = this.visaDisputeFinanceDataRepo.count(spec);
            int totalPage = (int)Math.ceil((double)totalCount / (double)size);
            Page pageList = this.visaDisputeFinanceDataRepo.findAll(spec, (Pageable)pageable);
            response = pageList.stream().map(entity -> {
                ChargeBackResponseVo vo = this.mapToChargeBackDetailsResponseVo(null, entity, "VISA");
                vo.setTotalCount((int)totalCount);
                vo.setTotalPage(totalPage);
                return vo;
            }).toList();
        }
        return response;
    }

    private ChargeBackResponseVo mapToChargeBackDetailsResponseVo(MCAcquirerExceptionsEntity mcEntity, VisaDisputeFinanceDataEntity visaEntity, String network) {
        ChargeBackResponseVo vo = new ChargeBackResponseVo();
        vo.setNetwork(Objects.requireNonNullElse(network, ""));
        try {
            switch (network == null ? "" : network.toUpperCase()) {
                case "MASTERCARD": {
                    if (mcEntity != null) {
                        vo.setCardNumber(mcEntity.getCardNumber());
                        vo.setTxnAmount(mcEntity.getTxnAmount() != null ? mcEntity.getTxnAmount() : 0.0);
                        vo.setTxnCurrency(mcEntity.getTxnCurrency() != null ? String.valueOf(mcEntity.getTxnCurrency()) : "");
                        vo.setApprovalCode(Objects.requireNonNullElse(mcEntity.getApprCode(), ""));
                        vo.setTerminalId(Objects.nonNull(mcEntity.getTerminalId()) ? mcEntity.getTerminalId() : "");
                        vo.setMerchantId(Objects.nonNull(mcEntity.getMeCode()) ? mcEntity.getMeCode() : "");
                        vo.setCardAccepterName(Objects.nonNull(mcEntity.getCardAccetName()) ? mcEntity.getCardAccetName() : "");
                        vo.setCardAccepterCity(Objects.nonNull(mcEntity.getCardAccetCity()) ? mcEntity.getCardAccetCity() : "");
                        vo.setCardAccepterCountry(Objects.nonNull(mcEntity.getCardAccetCountry()) ? mcEntity.getCardAccetCountry() : "");
                        vo.setCaseId(Objects.nonNull(mcEntity.getCaseId()) ? mcEntity.getCaseId() : "");
                        vo.setBusinessDate(Objects.nonNull(mcEntity.getBusinessDate()) ? mcEntity.getBusinessDate().toString() : "");
                        vo.setArn(Objects.nonNull(mcEntity.getAcqRefNumber()) ? mcEntity.getAcqRefNumber() : "");
                    }
                    break;
                }
                case "VISA": {
                    if (visaEntity != null) {
                        vo.setCardNumber(visaEntity.getCardNumber());
                        vo.setTxnAmount(visaEntity.getTxnAmount() != null ? visaEntity.getTxnAmount() : 0.0);
                        vo.setTxnCurrency(visaEntity.getTxnCurrency() != null ? String.valueOf(visaEntity.getTxnCurrency()) : "");
                        vo.setApprovalCode(Objects.requireNonNullElse(visaEntity.getAuthCode(), ""));
                        vo.setTerminalId(Objects.nonNull(visaEntity.getTerminalId()) ? visaEntity.getTerminalId() : "");
                        vo.setMerchantId(Objects.nonNull(visaEntity.getMerchantCode()) ? visaEntity.getMerchantCode() : "");
                        vo.setCardAccepterName(Objects.nonNull(visaEntity.getCardAcceptName()) ? visaEntity.getCardAcceptName() : "");
                        vo.setCardAccepterCity(Objects.nonNull(visaEntity.getCardAcceptCity()) ? visaEntity.getCardAcceptCity() : "");
                        vo.setCardAccepterCountry(Objects.nonNull(visaEntity.getCardAcceptCountry()) ? visaEntity.getCardAcceptCountry() : "");
                        vo.setCaseId(Objects.nonNull(visaEntity.getCaseId()) ? visaEntity.getCaseId() : "");
                        vo.setBusinessDate(Objects.nonNull(visaEntity.getBussDate()) ? visaEntity.getBussDate().toString() : "");
                        vo.setArn(Objects.nonNull(visaEntity.getAcqRefNumber()) ? visaEntity.getAcqRefNumber() : "");
                    }
                    break;
                }
                default: {
                    log.warn("Unsupported network: {}", (Object)network);
                    break;
                }
            }
        }
        catch (Exception e) {
            log.error("Error in mapToChargeBackDetailsResponseVo()", (Throwable)e);
        }
        return vo;
    }

    private Specification<VisaDisputeFinanceDataEntity> buildVisaChargeBackDetailsSpecification(@Valid InquiryAPIRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.equal((Expression)root.get("source"), (Object)"CHARGEBACK"));
            if (Objects.nonNull(requestVo.getFromDate()) && Objects.nonNull(requestVo.getToDate())) {
                try {
                    LocalDateTime fromDateTime = LocalDateTime.parse(requestVo.getFromDate(), DATE_TIME_FORMATTER);
                    LocalDateTime toDateTime = LocalDateTime.parse(requestVo.getToDate(), DATE_TIME_FORMATTER);
                    predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.between((Expression)root.get("bussDate"), (Comparable)fromDateTime, (Comparable)toDateTime));
                }
                catch (DateTimeParseException e) {
                    log.error("Invalid date format for fromDate or toDate: {}", (Object)e.getMessage(), (Object)e);
                }
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serNumber"))});
            return predicate;
        };
    }

    private Specification<MCAcquirerExceptionsEntity> buildMcChargeBackDetailsSpecification(@Valid InquiryAPIRequestVo requestVo) {
        return (Specification & Serializable)(root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.notEqual((Expression)root.get("genStatus"), (Object)0));
            if (Objects.nonNull(requestVo.getFromDate()) && Objects.nonNull(requestVo.getToDate())) {
                try {
                    LocalDateTime fromDateTime = LocalDateTime.parse(requestVo.getFromDate(), DATE_TIME_FORMATTER);
                    LocalDateTime toDateTime = LocalDateTime.parse(requestVo.getToDate(), DATE_TIME_FORMATTER);
                    predicate = criteriaBuilder.and((Expression)predicate, (Expression)criteriaBuilder.between((Expression)root.get("businessDate"), (Comparable)fromDateTime, (Comparable)toDateTime));
                }
                catch (DateTimeParseException e) {
                    log.error("Invalid date format for fromDate or toDate: {}", (Object)e.getMessage(), (Object)e);
                }
            }
            query.orderBy(new Order[]{criteriaBuilder.desc((Expression)root.get("serialNumber"))});
            return predicate;
        };
    }

    public InquiryService(ViewFileFormatRepo viewRepo, ViewInterfaceRepository viewInterfaceRepo, PosTransactionRepository posTransactionRepo, FileUploadLogRepository fileUploadRepo, Environment env, IRFCallbackRepo irfCallbackRepo, VisaNetworkDataRepo visaNetworkRepo, McNetworkDataRepo mcNetworkRepo, ViewTxnInquiryDetailsRepo viewTxnInquiryRepo, ReportGenerationRepo reportGenerationRepo, UaeSwitchIrfRepo uaeSwitchIrfRepo, ViewMcRejectionDetailsRepo viewMcRejectionDetailsRepo, OutgoingSchedulerRepo outgoingSchedulerRepo, OmanNetIRFRepo omanNetIRFRepo, JaywanIRFRepo jaywanIrfRepo, ViewMCNetReconDetailsRepo viewMCNetReconDetailsRepo, VisaFundsTransferDetailsRepo visaFundsTransferDetailsRepo, MCRejectionsRepo mcRejectionsRepo, MCIpmFeesRepo mcIpmFeesRepo, MCAcquirerExceptionsRepo mcAcquirerExceptionsRepo, VisaDisputeFinanceDataRepo visaDisputeFinanceDataRepo) {
        this.viewRepo = viewRepo;
        this.viewInterfaceRepo = viewInterfaceRepo;
        this.posTransactionRepo = posTransactionRepo;
        this.fileUploadRepo = fileUploadRepo;
        this.env = env;
        this.irfCallbackRepo = irfCallbackRepo;
        this.visaNetworkRepo = visaNetworkRepo;
        this.mcNetworkRepo = mcNetworkRepo;
        this.viewTxnInquiryRepo = viewTxnInquiryRepo;
        this.reportGenerationRepo = reportGenerationRepo;
        this.uaeSwitchIrfRepo = uaeSwitchIrfRepo;
        this.viewMcRejectionDetailsRepo = viewMcRejectionDetailsRepo;
        this.outgoingSchedulerRepo = outgoingSchedulerRepo;
        this.omanNetIRFRepo = omanNetIRFRepo;
        this.jaywanIrfRepo = jaywanIrfRepo;
        this.viewMCNetReconDetailsRepo = viewMCNetReconDetailsRepo;
        this.visaFundsTransferDetailsRepo = visaFundsTransferDetailsRepo;
        this.mcRejectionsRepo = mcRejectionsRepo;
        this.mcIpmFeesRepo = mcIpmFeesRepo;
        this.mcAcquirerExceptionsRepo = mcAcquirerExceptionsRepo;
        this.visaDisputeFinanceDataRepo = visaDisputeFinanceDataRepo;
    }
}

