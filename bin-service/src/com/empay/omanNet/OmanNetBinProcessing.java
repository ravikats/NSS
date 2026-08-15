/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OmanNetBinEntity
 *  com.empay.interfaces.IOmanNetBinProcessing
 *  com.empay.omanNet.OmanNetBinProcessing
 *  com.empay.omanNet.OmanNetBinUtil
 *  com.empay.repositories.OmanNetBinRepo
 *  com.empay.services.CommonService
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.poi.ss.usermodel.Cell
 *  org.apache.poi.ss.usermodel.DataFormatter
 *  org.apache.poi.ss.usermodel.Row
 *  org.apache.poi.ss.usermodel.Sheet
 *  org.apache.poi.xssf.usermodel.XSSFWorkbook
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.omanNet;

import com.empay.entities.OmanNetBinEntity;
import com.empay.interfaces.IOmanNetBinProcessing;
import com.empay.omanNet.OmanNetBinUtil;
import com.empay.repositories.OmanNetBinRepo;
import com.empay.services.CommonService;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OmanNetBinProcessing
implements IOmanNetBinProcessing {
    private static final Logger log = LogManager.getLogger(OmanNetBinProcessing.class);
    private final Environment env;
    private final OmanNetBinRepo omanNetBinRepo;
    private final CommonService commonService;

    public void processOmanNetBin(String fileName, int userSerialNumber, int jobSerialNumber, int uploadSerialNumber, String insShortName) {
        try {
            String filePath = this.env.getProperty("RECON_IN_" + insShortName) + fileName;
            log.info("OMANNET BIN FILE PROCESSING STARTED | FILENAME : {}", (Object)fileName);
            this.readOmanBinExcelFile(fileName, insShortName, filePath, userSerialNumber, jobSerialNumber, uploadSerialNumber);
            log.info("OMANNET BIN FILE PROCESSING COMPLETED | FILENAME : {}", (Object)fileName);
        }
        catch (Exception e) {
            log.error("Error processJaywanBin() :", (Throwable)e);
        }
    }

    public void readOmanBinExcelFile(String fileName, String insShortName, String filePath, int user, int jobNumber, int uploadSerialNumber) {
        int totalCount = 0;
        long totalAccTxnCount = 0L;
        long totalFileLineCount = 0L;
        int maxBatchSize = 10000;
        int minBatchSize = 3500;
        long calculatedBatchSize = 0L;
        int batchSize = 0;
        LocalDateTime now = LocalDateTime.now();
        ArrayList<OmanNetBinEntity> omanNetEntityList = new ArrayList<OmanNetBinEntity>();
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook((InputStream)fileInputStream);){
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter format = new DataFormatter();
            Iterator rowIterator = sheet.iterator();
            totalCount = sheet.getLastRowNum();
            totalFileLineCount = sheet.getLastRowNum() + 1;
            calculatedBatchSize = totalFileLineCount / 10L;
            batchSize = calculatedBatchSize > (long)maxBatchSize ? maxBatchSize : (calculatedBatchSize < (long)minBatchSize ? minBatchSize : (int)calculatedBatchSize);
            while (rowIterator.hasNext()) {
                OmanNetBinUtil valueObject = new OmanNetBinUtil();
                Row row = (Row)rowIterator.next();
                if (row.getRowNum() == 0) continue;
                Iterator cellIterator = row.cellIterator();
                block20: while (cellIterator.hasNext()) {
                    Cell cell = (Cell)cellIterator.next();
                    valueObject.setGenStatus(Integer.valueOf(4));
                    switch (cell.getColumnIndex()) {
                        case 0: {
                            if (format.formatCellValue(cell).trim().equals("")) {
                                valueObject.setSubRoute(null);
                                valueObject.setGenStatus(Integer.valueOf(7));
                                valueObject.setRemarks("No Value in Sub route");
                                break;
                            }
                            if (format.formatCellValue(cell).trim().length() <= 20) {
                                if (format.formatCellValue(cell).matches(".*[@#$%].*")) {
                                    valueObject.setSubRoute(null);
                                    valueObject.setGenStatus(Integer.valueOf(7));
                                    valueObject.setRemarks("Invalid Value in Route");
                                    break;
                                }
                                valueObject.setSubRoute(format.formatCellValue(cell).trim().toUpperCase());
                                continue block20;
                            }
                            valueObject.setSubRoute(null);
                            valueObject.setGenStatus(Integer.valueOf(7));
                            valueObject.setRemarks("Sub route length exceeds limit");
                            break;
                        }
                        case 1: {
                            if (format.formatCellValue(cell).trim().equals("")) {
                                valueObject.setRoute(null);
                                valueObject.setGenStatus(Integer.valueOf(7));
                                valueObject.setRemarks("No Value in Route");
                                break;
                            }
                            if (format.formatCellValue(cell).trim().length() <= 20) {
                                if (format.formatCellValue(cell).matches(".*[@#$%].*")) {
                                    valueObject.setRoute(null);
                                    valueObject.setGenStatus(Integer.valueOf(7));
                                    valueObject.setRemarks("Invalid Value in Route");
                                    break;
                                }
                                valueObject.setRoute(format.formatCellValue(cell).toUpperCase());
                                continue block20;
                            }
                            valueObject.setRoute(format.formatCellValue(cell));
                            valueObject.setGenStatus(Integer.valueOf(7));
                            valueObject.setRemarks("Route length exceeds limit");
                            break;
                        }
                        case 2: {
                            if (format.formatCellValue(cell).trim().equals("")) {
                                valueObject.setBinNumber(null);
                                valueObject.setGenStatus(Integer.valueOf(7));
                                valueObject.setRemarks("Null value Bin Number" + valueObject.toString());
                                break;
                            }
                            if (this.valiateInputData(Integer.valueOf(format.formatCellValue(cell).trim().length()), Integer.valueOf(8)) && format.formatCellValue(cell).matches("^\\d+$")) {
                                valueObject.setBinNumber(format.formatCellValue(cell).trim());
                                continue block20;
                            }
                            valueObject.setGenStatus(Integer.valueOf(7));
                            valueObject.setRemarks("Bin Number length exceeds" + valueObject.toString());
                            break;
                        }
                        case 3: {
                            if (format.formatCellValue(cell).trim().equals("")) {
                                valueObject.setCardType(null);
                                break;
                            }
                            if (this.valiateInputData(Integer.valueOf(format.formatCellValue(cell).trim().length()), Integer.valueOf(7)) && !format.formatCellValue(cell).matches(".*[@#$%].*")) {
                                valueObject.setCardType(format.formatCellValue(cell).trim());
                                continue block20;
                            }
                            valueObject.setGenStatus(Integer.valueOf(7));
                            valueObject.setRemarks("Card Type length exceeds" + valueObject.toString().toUpperCase());
                            break;
                        }
                    }
                    break;
                }
                valueObject.setUser(Integer.valueOf(user));
                valueObject.setJobNumber(Integer.valueOf(jobNumber));
                if (valueObject.getGenStatus() != 7) {
                    OmanNetBinEntity entity = this.mapToOmanNetData(valueObject, now);
                    omanNetEntityList.add(entity);
                    ++totalAccTxnCount;
                    if (omanNetEntityList.size() < batchSize) continue;
                    this.removeDuplicate(omanNetEntityList);
                    this.omanNetBinRepo.saveAll(omanNetEntityList);
                    this.omanNetBinRepo.flush();
                    omanNetEntityList.clear();
                    continue;
                }
                log.info("OmanBin Rejected " + valueObject.toString());
            }
            if (!omanNetEntityList.isEmpty()) {
                this.removeDuplicate(omanNetEntityList);
                this.omanNetBinRepo.saveAll(omanNetEntityList);
                this.omanNetBinRepo.flush();
                omanNetEntityList.clear();
            }
            this.commonService.updateFileUploadLog(uploadSerialNumber, 4, "Success");
            this.commonService.updateProcess(uploadSerialNumber, jobNumber, totalCount, (int)totalAccTxnCount, 4);
            this.commonService.moveFile(filePath, fileName, insShortName, "RECON_PROCESSED_");
        }
        catch (EOFException e) {
            log.info("OmanNet Bin file reading failed:" + e.getMessage());
        }
        catch (IOException e) {
            log.info("OmanNet Bin file reading failed" + e.getMessage());
            this.commonService.updateFileUploadLog(uploadSerialNumber, 5, "Failed");
            this.commonService.moveFile(filePath, fileName, insShortName, "RECON_REJECTED_");
        }
    }

    public void removeDuplicate(List<OmanNetBinEntity> omanNetEntityList) {
        List<String> existingBinNumbers = omanNetEntityList.stream().map(OmanNetBinEntity::getBinNumber).distinct().toList();
        List existing = this.omanNetBinRepo.findByBinNumberIn(existingBinNumbers);
        if (!existing.isEmpty()) {
            this.omanNetBinRepo.deleteAll((Iterable)existing);
            this.omanNetBinRepo.flush();
        }
    }

    private OmanNetBinEntity mapToOmanNetData(OmanNetBinUtil valueObject, LocalDateTime now) {
        OmanNetBinEntity entity = new OmanNetBinEntity();
        entity.setLastUpdated(LocalDateTime.now());
        entity.setUser(valueObject.getUser());
        entity.setJobNumber(valueObject.getJobNumber());
        entity.setGenStatus(valueObject.getGenStatus());
        entity.setRoute(valueObject.getRoute().toUpperCase());
        entity.setSubRoute(valueObject.getSubRoute().toUpperCase());
        entity.setBinNumber(valueObject.getBinNumber());
        entity.setCardType(Character.valueOf(this.resolveCardType(valueObject.getCardType().toLowerCase())));
        entity.setRemarks(valueObject.getRemarks());
        return entity;
    }

    public boolean valiateInputData(Integer actualLength, Integer expectedlength) {
        boolean responseFlag = true;
        if (actualLength > expectedlength) {
            responseFlag = false;
        }
        return responseFlag;
    }

    private char resolveCardType(String cardType) {
        return switch (Optional.ofNullable(cardType).map(String::trim).map(String::toLowerCase).orElse("")) {
            case "credit" -> 'C';
            case "debit" -> 'D';
            default -> 'P';
        };
    }

    public OmanNetBinProcessing(Environment env, OmanNetBinRepo omanNetBinRepo, CommonService commonService) {
        this.env = env;
        this.omanNetBinRepo = omanNetBinRepo;
        this.commonService = commonService;
    }
}

