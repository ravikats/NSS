/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.controllers.BinProcessingController
 *  com.empay.interfaces.IBinProcessingService
 *  com.empay.vo.BinRequestVo
 *  com.empay.vo.BinResponseVO
 *  jakarta.validation.Valid
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.empay.controllers;

import com.empay.interfaces.IBinProcessingService;
import com.empay.vo.BinRequestVo;
import com.empay.vo.BinResponseVO;
import jakarta.validation.Valid;
import java.io.File;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/bin/"})
public class BinProcessingController {
    private static final Logger log = LogManager.getLogger(BinProcessingController.class);
    private final IBinProcessingService service;
    private final Environment env;

    @PostMapping(value={"/v1/processBin"}, produces={"application/json"})
    public ResponseEntity<BinResponseVO> processBin(@Valid @RequestBody BinRequestVo binRequestVo) {
        log.info("bin processing started...");
        log.info("bin request {} :", (Object)binRequestVo);
        BinResponseVO responseVO = new BinResponseVO();
        HttpStatus httpStatusCode = HttpStatus.OK;
        try {
            int userSerialNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("UPDATED_USER"), "The key UPDATED_USER not found in the property."));
            int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE"), "The key INS_CODE not found in the property."));
            String insShortName = Objects.requireNonNull(this.env.getProperty("INS_SHORT_NAME"), "The key INS_SHORT_NAME not found in the property.");
            String path = this.env.getProperty("RECON_IN_" + insShortName) + binRequestVo.getFileName();
            int binInterfaceCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("BIN_INTERFACE_CODE"), "The key BIN_INTERFACE_CODE not found in the property."));
            int binFormatCode = 0;
            if (binRequestVo.getNetwork().equalsIgnoreCase("MASTERCARD")) {
                binFormatCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MC_BIN_FORMAT_CODE"), "The key MC_BIN_FORMAT_CODE not found in the property."));
            } else if (binRequestVo.getNetwork().equalsIgnoreCase("JAYWAN")) {
                binFormatCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("JAYWAN_BIN_FORMAT_CODE"), "The key JAYWAN_BIN_FORMAT_CODE not found in the property."));
            } else if (binRequestVo.getNetwork().equalsIgnoreCase("VISA")) {
                binFormatCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("VISA_BIN_FORMAT_CODE"), "The key VISA_BIN_FORMAT_CODE not found in the property."));
            } else if (binRequestVo.getNetwork().equalsIgnoreCase("OMANNET")) {
                binFormatCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("OMANNET_BIN_FORMAT_CODE"), "The key OMANNET_BIN_FORMAT_CODE not found in the property."));
            } else if (binRequestVo.getNetwork().equalsIgnoreCase("MERCURY")) {
                binFormatCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MERCURY_BIN_FORMAT_CODE"), "The key MERCURY_BIN_FORMAT_CODE not found in the property."));
            }
            File file = new File(path);
            responseVO = this.service.processBin(binRequestVo.getFileName(), binRequestVo.getNetwork(), userSerialNumber, insCode, binInterfaceCode, binFormatCode, insShortName, file);
        }
        catch (Exception e) {
            responseVO.setMessage("Unexpected error occurred, Description :" + e.getMessage());
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Error processBin() :", (Throwable)e);
        }
        log.info("bin response {} :", (Object)responseVO);
        return ResponseEntity.status((HttpStatusCode)httpStatusCode).body((Object)responseVO);
    }

    @DeleteMapping(value={"/v1/deleteBinFile"}, produces={"application/json"})
    public ResponseEntity<BinResponseVO> deleteFailedBinFile(@Valid @RequestBody BinRequestVo binRequestVo) {
        log.info("bin deletion started...");
        BinResponseVO binResponseVO = this.service.binFileDeletion(binRequestVo.getFileName(), binRequestVo.getNetwork());
        log.debug("binResponseVO : {}", (Object)binResponseVO);
        log.info("bin deletion completed.");
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)binResponseVO);
    }

    public BinProcessingController(IBinProcessingService service, Environment env) {
        this.service = service;
        this.env = env;
    }
}

