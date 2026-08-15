/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MercuryIssAccRangeEntity
 *  com.empay.interfaces.IMercuryBinProcessing
 *  com.empay.mercury.MercuryBinProcessing
 *  com.empay.repositories.MercuryIssAccRangeRepo
 *  com.empay.services.CommonService
 *  com.opencsv.CSVReader
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.mercury;

import com.empay.entities.MercuryIssAccRangeEntity;
import com.empay.interfaces.IMercuryBinProcessing;
import com.empay.repositories.MercuryIssAccRangeRepo;
import com.empay.services.CommonService;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/*
 * Exception performing whole class analysis ignored.
 */
@Service
public class MercuryBinProcessing
implements IMercuryBinProcessing {
    private static final Logger log = LogManager.getLogger(MercuryBinProcessing.class);
    private final Environment env;
    private final CommonService commonService;
    private final MercuryIssAccRangeRepo mercuryRepo;

    public void processMercuryBin(String fileName, int userSerialNumber, int jobSerialNumber, int uploadSerialNumber, String insShortName) {
        try {
            String filePath = this.env.getProperty("RECON_IN_" + insShortName) + fileName;
            log.info("MERCURY BIN FILE PROCESSING STARTED | FILENAME : {}", (Object)fileName);
            this.readCSVFile(fileName, insShortName, filePath, userSerialNumber, jobSerialNumber, uploadSerialNumber);
            log.info("MERCURY BIN FILE PROCESSING COMPLETED | FILENAME : {}", (Object)fileName);
        }
        catch (Exception e) {
            log.error("Error processMercuryBin() :", (Throwable)e);
        }
    }

    private void readCSVFile(String fileName, String insShortName, String filePath, int userSerialNumber, int jobSerialNumber, int uploadSerialNumber) {
        int totalCount = 0;
        int totalAccTxnCount = 0;
        long totalFileLineCount = MercuryBinProcessing.getTotalLineCount((String)filePath);
        int maxBatchSize = 10000;
        int minBatchSize = 3500;
        long calculatedBatchSize = totalFileLineCount / 10L;
        int batchCount = calculatedBatchSize > (long)maxBatchSize ? maxBatchSize : (calculatedBatchSize < (long)minBatchSize ? minBatchSize : (int)calculatedBatchSize);
        ArrayList<MercuryIssAccRangeEntity> saveList = new ArrayList<MercuryIssAccRangeEntity>();
        ArrayList deleteList = new ArrayList();
        try (CSVReader reader = new CSVReader((Reader)new FileReader(filePath));){
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                MercuryIssAccRangeEntity entity;
                if (!this.isValidLine(nextLine) || (entity = this.readLine(userSerialNumber, jobSerialNumber, nextLine)) == null) continue;
                Character action = entity.getStatus();
                List existingList = this.mercuryRepo.findByBinRangeLowAndBinRangeHigh(entity.getBinRangeLow(), entity.getBinRangeHigh());
                if (action != null) {
                    switch (action.charValue()) {
                        case 'A': {
                            saveList.add(entity);
                            ++totalAccTxnCount;
                            break;
                        }
                        case 'E': 
                        case 'U': {
                            if (existingList != null && !existingList.isEmpty()) {
                                deleteList.addAll(existingList);
                            }
                            saveList.add(entity);
                            ++totalAccTxnCount;
                            break;
                        }
                        case 'D': {
                            if (existingList == null || existingList.isEmpty()) break;
                            deleteList.addAll(existingList);
                            break;
                        }
                        default: {
                            log.warn("Invalid status value: {}", (Object)action);
                        }
                    }
                }
                if (deleteList.size() >= batchCount) {
                    this.mercuryRepo.deleteAll(deleteList);
                    this.mercuryRepo.flush();
                    deleteList.clear();
                }
                if (saveList.size() >= batchCount) {
                    this.mercuryRepo.saveAllAndFlush(saveList);
                    saveList.clear();
                }
                ++totalCount;
            }
            if (!deleteList.isEmpty()) {
                this.mercuryRepo.deleteAll(deleteList);
                this.mercuryRepo.flush();
            }
            if (!saveList.isEmpty()) {
                this.mercuryRepo.saveAllAndFlush(saveList);
            }
            this.commonService.updateProcess(uploadSerialNumber, jobSerialNumber, totalCount, totalAccTxnCount, 4);
            this.commonService.moveFile(filePath, fileName, insShortName, "RECON_PROCESSED_");
        }
        catch (Exception e) {
            log.error("Error readCSVFile() :", (Throwable)e);
            this.commonService.updateProcess(uploadSerialNumber, jobSerialNumber, totalCount, 0, 5);
            this.commonService.moveFile(filePath, fileName, insShortName, "RECON_REJECTED_");
        }
    }

    private static int getTotalLineCount(String filePath) {
        int n;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        try {
            n = (int)reader.lines().skip(1L).count();
        }
        catch (Throwable throwable) {
            try {
                try {
                    reader.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (IOException e) {
                log.error("Error getTotalLineCount() :", (Throwable)e);
                return 0;
            }
        }
        reader.close();
        return n;
    }

    private boolean isValidLine(String[] nextLine) {
        if (nextLine == null) {
            return false;
        }
        if (nextLine.length != 9) {
            log.error("Invalid column count : {}", (Object)Arrays.toString(nextLine));
            return false;
        }
        boolean allBlank = true;
        for (String value : nextLine) {
            if (value == null || value.trim().isEmpty()) continue;
            allBlank = false;
            break;
        }
        return !allBlank;
    }

    private MercuryIssAccRangeEntity readLine(int userSerialNumber, int jobSerialNumber, String[] nextLine) {
        MercuryIssAccRangeEntity entity = new MercuryIssAccRangeEntity();
        try {
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(userSerialNumber);
            entity.setJobNumber(jobSerialNumber);
            entity.setBinRangeLow(Long.valueOf(Long.parseLong(nextLine[0])));
            entity.setBinRangeHigh(Long.valueOf(Long.parseLong(nextLine[1])));
            entity.setCardType(Integer.valueOf(Integer.parseInt(nextLine[2])));
            entity.setCardProductId(Integer.valueOf(Integer.parseInt(nextLine[3])));
            entity.setCardVariant(Integer.valueOf(Integer.parseInt(nextLine[4])));
            entity.setCardScheme(Integer.valueOf(Integer.parseInt(nextLine[5])));
            entity.setCurrencyCode(Integer.valueOf(Integer.parseInt(nextLine[6])));
            entity.setCountryCode(Integer.valueOf(Integer.parseInt(nextLine[7])));
            entity.setStatus(Character.valueOf(nextLine[8].charAt(0)));
        }
        catch (Exception e) {
            log.error("ERROR LINE :{} ", (Object)String.join((CharSequence)", ", nextLine));
            log.error("Error readLine() :", (Throwable)e);
            entity = null;
        }
        return entity;
    }

    public MercuryBinProcessing(Environment env, CommonService commonService, MercuryIssAccRangeRepo mercuryRepo) {
        this.env = env;
        this.commonService = commonService;
        this.mercuryRepo = mercuryRepo;
    }
}

