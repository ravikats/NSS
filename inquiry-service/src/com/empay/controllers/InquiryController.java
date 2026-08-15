/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.controllers.InquiryController
 *  com.empay.exceptions.UnauthorizedException
 *  com.empay.exceptions.ValidationException
 *  com.empay.services.BatchInquiryService
 *  com.empay.services.BinHistoryService
 *  com.empay.services.InquiryService
 *  com.empay.services.OutgoingInquiryService
 *  com.empay.services.ValidationService
 *  com.empay.vo.BatchDetailsResponseVo
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
 *  jakarta.validation.Valid
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestHeader
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.empay.controllers;

import com.empay.exceptions.UnauthorizedException;
import com.empay.exceptions.ValidationException;
import com.empay.services.BatchInquiryService;
import com.empay.services.BinHistoryService;
import com.empay.services.InquiryService;
import com.empay.services.OutgoingInquiryService;
import com.empay.services.ValidationService;
import com.empay.vo.BatchDetailsResponseVo;
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
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/inquiry/"})
public class InquiryController {
    private static final Logger log = LogManager.getLogger(InquiryController.class);
    private final InquiryService service;
    private final ValidationService validateService;
    private final OutgoingInquiryService outgoingInquiryService;
    private final BinHistoryService binHistoryService;
    private final BatchInquiryService batchService;

    @GetMapping(value={"/v1/getBinFileStatus"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> getBinFileStatus(@RequestBody RequestVo requestVo) {
        log.debug("getBinFileStatus request :{}", (Object)requestVo);
        this.validateService.validateLength(requestVo.getFileName(), 60);
        Map responseBody = this.service.getProcessBinStatus(requestVo.getFileName());
        log.debug("getBinFileStatus response : {}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getFileStatus"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> getFileStatus(@Valid @RequestBody RequestVo requestVO) {
        log.debug("getFileStatus request :{}", (Object)requestVO);
        this.validateService.validateLength(requestVO.getFileName(), 60);
        Map responseBody = this.service.getFileStatus(requestVO.getFileName());
        log.debug("getFileStatus response :{} ", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getTLFUploadLog"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getTLFUploadLog(@RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getTLFUploadLog request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        this.validateService.validateRequest(requestVo.getBussDate(), 10, null, false);
        List responseBody = this.service.getTLFUploadLog(requestVo, page, size);
        log.debug("getTLFUploadLog response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getTxnStatus"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getTxnStatus(@Valid @RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getTxnStatus request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        this.validateService.validateRrn(requestVo.getRrn(), 12);
        List responseBody = this.service.getTxnStatus(requestVo.getRrn(), page, size);
        log.debug("getTxnStatus response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getOutgoingStatus"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> getOutgoingStatus() {
        log.info("getOutgoingStatus...");
        Map responseBody = this.outgoingInquiryService.getOutgoingStatus();
        log.debug("getOutgoingStatus response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getOutgoingHist"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getOutgoingHist(@Valid @RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getOutgoingHist request :{}", (Object)requestVo);
        this.validateService.validateRequest(requestVo.getBussDate(), 10, requestVo.getNetwork(), true);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBodyList = this.outgoingInquiryService.getOutgoingHist(requestVo, page, size, "");
        log.debug("getOutgoingHist response :{}", (Object)responseBodyList);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBodyList);
    }

    @GetMapping(value={"/v1/getBinUploadLog"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getBinUploadLog(@Valid @RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getBinUploadLog request :{}", (Object)requestVo);
        this.validateService.validateRequest(requestVo.getBussDate(), 10, requestVo.getNetwork(), true);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBodyList = this.binHistoryService.getBinHist(requestVo, page, size);
        log.debug("getBinUploadLog response :{}", (Object)responseBodyList);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBodyList);
    }

    @GetMapping(value={"/v1/getTxnDetails"}, produces={"application/json"})
    public ResponseEntity<List<TxnDetailsResponseVo>> getTxnDetails(@Valid @RequestBody TxnDetailsRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getTxnDetails request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        this.validateService.validateTxnDetails(requestVo);
        List response = this.service.getTxnDetails(requestVo, page, size);
        log.debug("getTxnDetails response :{}", (Object)response);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }

    @GetMapping(value={"/v1/getMPGSUploadLog"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getMPGSUploadLog(@Valid @RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getMPGSUploadLog request :{}", (Object)requestVo);
        this.validateService.validateRequest(requestVo.getBussDate(), 10, null, false);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getMPGSUploadLog(requestVo, page, size);
        log.debug("getMPGSUploadLog response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getIncomingUploadLog"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getIncomingUploadLog(@Valid @RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getIncomingUploadLog request :{}", (Object)requestVo);
        this.validateService.validateRequest(requestVo.getBussDate(), 10, requestVo.getNetwork(), true);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getIncomingUploadLog(requestVo, page, size);
        log.debug("getIncomingUploadLog response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getTxnCallback"}, produces={"application/json"})
    public ResponseEntity<List<TxnDetailsResponseVo>> getTxnCallback(@RequestHeader(value="Authorization") String authorization, @RequestParam(value="rrn", required=true) String rrn, @RequestParam(value="terminalId", required=false) String terminalId, @RequestParam(value="mti", required=false) String mti, @RequestParam(value="channel", required=false) String channel) {
        if (!this.validateService.isValidAuth(authorization)) {
            throw new UnauthorizedException("The request was unauthorized.");
        }
        TxnDetailsRequestVo requestVo = new TxnDetailsRequestVo();
        requestVo.setRrn(rrn);
        requestVo.setTerminalId(terminalId);
        requestVo.setMti(mti);
        requestVo.setChannel(channel);
        if (rrn == null || rrn.trim().isEmpty()) {
            throw new ValidationException("Please provide a valid rrn");
        }
        List response = this.service.getTxnDetails(requestVo, 1, 20);
        log.debug("getTxnCallback response :{}", (Object)response);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }

    @GetMapping(value={"/v1/getBatchAPI"}, produces={"application/json"})
    public ResponseEntity<?> getBatchAPI(@RequestHeader(value="Authorization") String authorization, @RequestParam(value="batchNo") String batchNoStr, @RequestParam(value="txnDate") String txnDate) {
        try {
            int batchNo;
            try {
                batchNo = Integer.parseInt(batchNoStr);
            }
            catch (NumberFormatException e) {
                return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(Map.of("errorMessage", "Invalid batch number format. It must be an integer."));
            }
            this.validateService.validateBatchParams(batchNo, txnDate, authorization);
            LocalDate date = LocalDate.parse(txnDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDateTime startTime = date.atTime(batchNo - 1, 0);
            LocalDateTime endTime = startTime.plusHours(1L);
            Map batchTotals = this.batchService.getBatchTotals(startTime, endTime);
            String batchId = "BATCH-" + txnDate + "-" + batchNo;
            BatchDetailsResponseVo responseVo = new BatchDetailsResponseVo();
            responseVo.setBatch_id(batchId);
            responseVo.setBatch_totals(batchTotals);
            return ResponseEntity.ok(Collections.singletonList(responseVo));
        }
        catch (ValidationException e) {
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(Map.of("errorMessage", e.getMessage()));
        }
        catch (Exception e) {
            log.error("Failed to process batch API", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value={"/v1/getCyberSourceUploadLog"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getCyberSourceUploadLog(@RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getCyberSourceUploadLog request :{}", (Object)requestVo);
        this.validateService.validateRequest(requestVo.getBussDate(), 10, null, false);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getCyberSourceUploadLog(requestVo, page, size);
        log.debug("getCyberSourceUploadLog response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getTC33InquiryAPI"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getTc33InquiryAPI(@Valid @RequestBody(required=false) InquiryRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getTc33InquiryAPI request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getVisaTxnInchgDetails(requestVo, page, size);
        log.debug("getTc33InquiryAPI response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getTN70InquiryAPI"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getTn70InquiryAPI(@Valid @RequestBody(required=false) InquiryRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getTn70InquiryAPI request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getMcTxnInchgDetails(requestVo, page, size);
        log.debug("getTn70InquiryAPI response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getReportDetails"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getReportDetails(@Valid @RequestBody ReportRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getReportDetails request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        this.validateService.validateReportDates(requestVo);
        List responseBody = this.service.getReportDetails(requestVo, page, size);
        log.debug("getReportDetails response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getUAESwitchInterchange"}, produces={"application/json"})
    public ResponseEntity<List<InterchangeResponseVo>> getUAESwitchInterchange(@Valid @RequestBody InterchangeRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getUAESwitchInterchange request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getUAESwitchInterchange(requestVo, page, size);
        log.debug("getUAESwitchInterchange response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getOmanNetInterchange"}, produces={"application/json"})
    public ResponseEntity<List<OmanNetinterchangeResponseVo>> getOmanNetInterchange(@RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("Get OmanNetInterchange Start>>>");
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getOmanNetIrfConfig(page, size);
        log.debug("getUAESwitchInterchange response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getRejectedMcTxns"}, produces={"application/json"})
    public ResponseEntity<List<RejectedTransactionDetailsVo>> getRejectedMcTxns(@Valid @RequestBody(required=false) InquiryRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getRejectedMcTxns request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getRejectedMcTxns(requestVo, page, size);
        log.debug("getRejectedMcTxns response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getAllCycle"}, produces={"application/json"})
    public ResponseEntity<List<OutgoingSchedulerResponseVo>> getAllOutgoingCycles(@Valid @RequestBody(required=false) @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getAllCycle request Start ");
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getAllSchedulederCycles(page, size);
        log.debug("getRejectedMcTxns response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getJaywanInterchange"}, produces={"application/json"})
    public ResponseEntity<List<InterchangeResponseVo>> getJaywanInterchange(@Valid @RequestBody InterchangeRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getJaywanInterchange request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getJaywanInterchange(requestVo, page, size);
        log.debug("getJaywanInterchange response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getSchemeFundDetails"}, produces={"application/json"})
    public ResponseEntity<List<SchemeFundDetailsResponseVo>> getSchemeFundDetails(@Valid @RequestBody SchemeFundDetailsRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getSchemeFundDetails request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBody = this.service.getSchemeFundDetails(requestVo, page, size);
        log.debug("getSchemeFundDetails response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getFeeCollectionDetails"}, produces={"application/json"})
    public ResponseEntity<List<FeeCollectionResponseVo>> getFeeCollectionDetails(@Valid @RequestBody InquiryRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getFeeCollectionDetails request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        this.validateService.validateDates(requestVo);
        List responseBody = this.service.getFeeCollectionDetails(requestVo, page, size);
        log.debug("getFeeCollectionDetails response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getChargeBackDetails"}, produces={"application/json"})
    public ResponseEntity<List<ChargeBackResponseVo>> getChargeBackDetails(@Valid @RequestBody InquiryAPIRequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getChargeBackDetails request :{}", (Object)requestVo);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        this.validateService.validateChargebackDates(requestVo);
        List responseBody = this.service.getChargeBackDetails(requestVo, page, size);
        log.debug("getChargeBackDetails response :{}", (Object)responseBody);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }

    @GetMapping(value={"/v1/getCollectionOnlyHist"}, produces={"application/json"})
    public ResponseEntity<List<Map<String, String>>> getCollectionOnlyHist(@Valid @RequestBody RequestVo requestVo, @RequestParam(value="page") int page, @RequestParam(value="size") int size) {
        log.debug("getCollectionOnlyHist request :{}", (Object)requestVo);
        this.validateService.validateCollectionOnlyRequest(requestVo.getBussDate(), 10, requestVo.getNetwork(), true);
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 20;
        List responseBodyList = this.outgoingInquiryService.getOutgoingHist(requestVo, page, size, "C");
        log.debug("getCollectionOnlyHist response :{}", (Object)responseBodyList);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBodyList);
    }

    public InquiryController(InquiryService service, ValidationService validateService, OutgoingInquiryService outgoingInquiryService, BinHistoryService binHistoryService, BatchInquiryService batchService) {
        this.service = service;
        this.validateService = validateService;
        this.outgoingInquiryService = outgoingInquiryService;
        this.binHistoryService = binHistoryService;
        this.batchService = batchService;
    }
}

