/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.JaywanIssAccRangeEntity
 *  com.empay.interfaces.IJaywanBinProcess
 *  com.empay.jaywan.JaywanBinProcessImpl
 *  com.empay.repositories.JaywanMcIssAccRangeRepository
 *  com.empay.services.CommonService
 *  com.opencsv.CSVReader
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.jaywan;

import com.empay.entities.JaywanIssAccRangeEntity;
import com.empay.interfaces.IJaywanBinProcess;
import com.empay.repositories.JaywanMcIssAccRangeRepository;
import com.empay.services.CommonService;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/*
 * Exception performing whole class analysis ignored.
 */
@Service
public class JaywanBinProcessImpl
implements IJaywanBinProcess {
    private static final Logger log = LogManager.getLogger(JaywanBinProcessImpl.class);
    private final CommonService commonService;
    private final Environment env;
    private final JaywanMcIssAccRangeRepository jaywanMcIssAccRangeRepo;

    public void processJaywanBin(String fileName, int userSerialNumber, int jobSerialNumber, int uploadSerialNumber, String insShortName) {
        try {
            String filePath = this.env.getProperty("RECON_IN_" + insShortName) + fileName;
            log.info("JAYWAN BIN FILE PROCESSING STARTED | FILENAME : {}", (Object)fileName);
            this.readCSVFile(fileName, insShortName, filePath, userSerialNumber, jobSerialNumber, uploadSerialNumber);
            log.info("JAYWAN BIN FILE PROCESSING COMPLETED | FILENAME : {}", (Object)fileName);
        }
        catch (Exception e) {
            log.error("Error processJaywanBin() :", (Throwable)e);
        }
    }

    private void readCSVFile(String fileName, String insShortName, String filePath, int userSerialNumber, int jobSerialNumber, int uploadSerialNumber) {
        int totalCount = 0;
        int totalAccTxnCount = 0;
        long totalFileLineCount = JaywanBinProcessImpl.getTotalLineCount((String)filePath);
        int maxBatchSize = 10000;
        int minBatchSize = 3500;
        long calculatedBatchSize = totalFileLineCount / 10L;
        int deletionCount = 1000;
        int batchCount = calculatedBatchSize > (long)maxBatchSize ? maxBatchSize : (calculatedBatchSize < (long)minBatchSize ? minBatchSize : (int)calculatedBatchSize);
        ArrayList<JaywanIssAccRangeEntity> entityList = new ArrayList<JaywanIssAccRangeEntity>();
        try (CSVReader reader = new CSVReader((Reader)new FileReader(filePath));){
            String[] nextLine;
            reader.readNext();
            ArrayList entities = new ArrayList();
            while ((nextLine = reader.readNext()) != null) {
                JaywanIssAccRangeEntity entity;
                if (!this.isValidLine(nextLine)) continue;
                List result = this.jaywanMcIssAccRangeRepo.findByBinRangeLowAndBinRangeHigh(Long.valueOf(Long.parseLong(nextLine[2])), Long.valueOf(Long.parseLong(nextLine[3])));
                if (Objects.nonNull(result)) {
                    entities.addAll(result);
                    if (entities.size() > deletionCount) {
                        this.jaywanMcIssAccRangeRepo.deleteAll(entities);
                        this.jaywanMcIssAccRangeRepo.flush();
                        entities.clear();
                    }
                }
                if (Objects.nonNull(entity = this.readLine(userSerialNumber, jobSerialNumber, nextLine))) {
                    entityList.add(entity);
                    if (entityList.size() > batchCount) {
                        this.jaywanMcIssAccRangeRepo.saveAllAndFlush(entityList);
                        entityList.clear();
                    }
                    ++totalAccTxnCount;
                }
                ++totalCount;
            }
            if (!entityList.isEmpty()) {
                this.jaywanMcIssAccRangeRepo.saveAllAndFlush(entityList);
            }
            if (!entities.isEmpty()) {
                this.jaywanMcIssAccRangeRepo.deleteAll(entities);
                this.jaywanMcIssAccRangeRepo.flush();
            }
            reader.close();
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

    private JaywanIssAccRangeEntity readLine(int userSerialNumber, int jobSerialNumber, String[] nextLine) {
        JaywanIssAccRangeEntity entity = new JaywanIssAccRangeEntity();
        try {
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(userSerialNumber);
            entity.setJobNumber(jobSerialNumber);
            entity.setIssuerBank(nextLine[0]);
            entity.setInstitutionId(Integer.valueOf(Integer.parseInt(nextLine[1])));
            entity.setBinRangeLow(Long.valueOf(Long.parseLong(nextLine[2])));
            entity.setBinRangeHigh(Long.valueOf(Long.parseLong(nextLine[3])));
            entity.setPanLength(Integer.valueOf(Integer.parseInt(nextLine[4])));
            entity.setProductType(Character.valueOf(nextLine[5].charAt(0)));
            entity.setSchemeCode(Character.valueOf(nextLine[6].charAt(0)));
            entity.setSchemeProduct(nextLine[7]);
            entity.setCardType(Integer.valueOf(Integer.parseInt(nextLine[8])));
            entity.setService(Integer.valueOf(Integer.parseInt(nextLine[9])));
            entity.setCurrencyCode(Integer.valueOf(Integer.parseInt(nextLine[10])));
            entity.setIsoNumCurrCode(Integer.valueOf(Integer.parseInt(nextLine[11])));
            entity.setActionTaken(Character.valueOf(nextLine[12].charAt(0)));
            entity.setBinLength(Integer.valueOf(Integer.parseInt(nextLine[13])));
            entity.setIssAccCap(Character.valueOf(nextLine[14].charAt(0)));
            entity.setProdClssfy(Character.valueOf(nextLine[15].charAt(0)));
            entity.setBadgeInd(nextLine[16]);
        }
        catch (Exception e) {
            log.error("ERROR LINE :{} ", (Object)String.join((CharSequence)", ", nextLine));
            log.error("Error readLine() :", (Throwable)e);
            entity = null;
        }
        return entity;
    }

    private boolean isValidLine(String[] nextLine) {
        String line = String.join((CharSequence)",", nextLine);
        int commaCount = JaywanBinProcessImpl.countCommas((String)line);
        if (commaCount != 16) {
            log.error("Invalid line :{}", (Object)line);
            return false;
        }
        return true;
    }

    private static int countCommas(String line) {
        if (line == null || line.isEmpty()) {
            return 0;
        }
        return (int)line.chars().filter(ch -> ch == 44).count();
    }

    public JaywanBinProcessImpl(CommonService commonService, Environment env, JaywanMcIssAccRangeRepository jaywanMcIssAccRangeRepo) {
        this.commonService = commonService;
        this.env = env;
        this.jaywanMcIssAccRangeRepo = jaywanMcIssAccRangeRepo;
    }
}

