/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.entities.MCIssAcqRangeEntity
 *  com.empay.ipm.McT67Pro
 *  com.empay.repositories.FileUploadLogRepository
 *  com.empay.repositories.MCIssAcqRangeRepository
 *  com.empay.services.CommonService
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.ipm;

import com.empay.entities.FileUploadLogEntity;
import com.empay.entities.MCIssAcqRangeEntity;
import com.empay.repositories.FileUploadLogRepository;
import com.empay.repositories.MCIssAcqRangeRepository;
import com.empay.services.CommonService;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class McT67Pro {
    private static final Logger log = LogManager.getLogger(McT67Pro.class);
    private final Environment env;
    private final FileUploadLogRepository uploadLogRepo;
    private final CommonService commonService;
    private final MCIssAcqRangeRepository mcIssAcqRangeRepo;

    public void processMCBin(String fileName, Integer userSerialNumber, Integer jobNumber, Integer uploadSerialNumber, String insShortName) {
        Object fName = "";
        try {
            fName = this.env.getProperty("RECON_IN_" + insShortName) + fileName;
            log.info("MC BIN FILE PROCESSING STARTED | FILENAME :{}", (Object)fileName);
            File file = new File((String)fName);
            LocalDate businessDate = this.commonService.getBusinessDate();
            FileInputStream in = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String currentDir = this.env.getProperty("RECON_LOG");
            FileOutputStream fileOutputStream = new FileOutputStream(currentDir + fileName + ".log", false);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            PrintStream printStream = new PrintStream(bufferedOutputStream);
            System.setOut(printStream);
            int totalCount = 0;
            boolean flag = false;
            StringBuilder builder = new StringBuilder();
            StringBuilder abc = new StringBuilder();
            while (((InputStream)in).available() > 0) {
                char chars = (char)((InputStream)in).read();
                builder.append(chars);
                if ((byte)chars == 2 || (byte)chars == 3 || (byte)chars == 0) {
                    if (builder.toString().contains("PTRAILER RECORD IP0040T1") && !flag) {
                        log.info("End of file");
                        break;
                    }
                    builder = new StringBuilder();
                    if (flag) {
                        flag = false;
                        ++totalCount;
                        this.insertMcIssAccRange(jobNumber.intValue(), userSerialNumber.intValue(), abc.toString(), businessDate);
                        abc = new StringBuilder();
                    }
                }
                if (chars == '\u00b8' || chars == '\u00a9' || chars == '\u00c2') {
                    flag = true;
                    chars = (char)((InputStream)in).read();
                }
                if (!flag || chars == '@') continue;
                abc.append(chars);
            }
            reader.close();
            System.out.flush();
            System.out.close();
            this.commonService.updateProcess(uploadSerialNumber.intValue(), jobNumber.intValue(), totalCount, totalCount, 4);
            log.info("MC T067/T068 file Total  count: {}", (Object)totalCount);
            log.info("FILE PROCESSING COMPLETED SUCCESSFULLY");
            this.commonService.moveFile((String)fName, fileName, insShortName, "RECON_PROCESSED_");
            printStream.close();
            ((InputStream)in).close();
        }
        catch (Exception e) {
            log.error("Error::processMCBin():", (Throwable)e);
            FileUploadLogEntity entity = this.uploadLogRepo.findBySerialNumber(uploadSerialNumber);
            entity.setUploadStatus(Integer.valueOf(5));
            entity.setRemarks("An error occurred while attempting to read the file.");
            this.uploadLogRepo.saveAndFlush((Object)entity);
            this.commonService.moveFile((String)fName, fileName, insShortName, "RECON_REJECTED_");
        }
    }

    private void insertMcIssAccRange(int jobNumber, int userSerialNumber, String line, LocalDate businessDate) {
        String[] fields = new String[36];
        MCIssAcqRangeEntity entity = new MCIssAcqRangeEntity();
        try {
            fields[0] = line.substring(0, 5);
            LocalDate effectiveDate2 = this.commonService.convertToGregorianDate(fields[0]);
            fields[1] = line.substring(7, 8);
            fields[2] = line.substring(11, 30);
            fields[3] = line.substring(30, 33);
            fields[4] = line.substring(33, 52);
            fields[5] = line.substring(52, 55);
            if (fields[5].matches("DMC|MCC|MSI|PVL") && fields[1].equals("A")) {
                fields[6] = line.substring(55, 57);
                fields[7] = line.substring(57, 68);
                fields[8] = line.substring(68, 69);
                fields[9] = line.substring(69, 76);
                fields[10] = line.substring(76, 79);
                fields[11] = line.substring(79, 82);
                fields[12] = line.substring(82, 83);
                fields[13] = line.substring(83, 86);
                fields[14] = line.substring(86, 87);
                fields[15] = line.substring(87, 88);
                fields[16] = line.substring(88, 89);
                fields[17] = line.substring(89, 90);
                fields[18] = line.substring(90, 93);
                fields[19] = line.substring(93, 94);
                fields[20] = line.substring(94, 95);
                fields[21] = line.substring(101, 104);
                fields[22] = line.substring(104, 105);
                fields[23] = line.substring(133, 134);
                fields[24] = line.substring(134, 140);
                fields[25] = line.substring(140, 141);
                fields[26] = line.substring(141, 142);
                fields[27] = line.substring(142, 145);
                fields[28] = line.substring(151, 152);
                fields[29] = line.substring(152, 153);
                fields[30] = line.substring(153, 154);
                fields[31] = line.substring(154, 155);
                fields[32] = line.substring(156, 157);
                fields[33] = line.substring(157, 158);
                fields[34] = line.substring(158, 159);
                this.deleteMCIssAcqRange(fields[2], fields[4], fields[6]);
                entity.setLastUpdated(LocalDateTime.now());
                entity.setUpdatedUser(userSerialNumber);
                entity.setJobSerialNumber(jobNumber);
                entity.setEffectiveDate(effectiveDate2);
                entity.setActiveCode(Character.valueOf(fields[1].charAt(0)));
                entity.setIssRangeLow(fields[2]);
                entity.setGcmsProductId(fields[3]);
                entity.setIssRangeHigh(fields[4]);
                entity.setCardProgId(fields[5]);
                entity.setPriorityCode(fields[6]);
                entity.setMemberId(fields[7]);
                entity.setProdTypeId(Character.valueOf(fields[8].charAt(0)));
                entity.setEndPoint(fields[9]);
                entity.setCountryAlphaCode(fields[10]);
                entity.setCountryCode(fields[11]);
                entity.setRegion(Character.valueOf(fields[12].charAt(0)));
                entity.setProductClass(fields[13]);
                entity.setTxnRoutInd(Character.valueOf(fields[14].charAt(0)));
                entity.setFpReasignSwitch(Character.valueOf(fields[15].charAt(0)));
                entity.setProdReasignSwitch(Character.valueOf(fields[16].charAt(0)));
                entity.setPwcbSwitch(Character.valueOf(fields[17].charAt(0)));
                entity.setLicProdId(fields[18]);
                entity.setMapServInd(Character.valueOf(fields[19].charAt(0)));
                entity.setAccLevelInd(Character.valueOf(fields[20].charAt(0)));
                entity.setChBillCurr(fields[21]);
                entity.setChBillCurrExp(Character.valueOf(fields[22].charAt(0)));
                entity.setChipServInd(Character.valueOf(fields[23].charAt(0)));
                entity.setFloorExpDate(fields[24]);
                entity.setCoBrandSwitch(Character.valueOf(fields[25].charAt(0)));
                entity.setSpendControlSwitch(Character.valueOf(fields[26].charAt(0)));
                entity.setMeCleansingService(fields[27]);
                entity.setMePayPassInd(Character.valueOf(fields[28].charAt(0)));
                entity.setRateTypeInd(Character.valueOf(fields[29].charAt(0)));
                entity.setPsnRouteInd(Character.valueOf(fields[30].charAt(0)));
                entity.setCbWithoutPurchase(Character.valueOf(fields[31].charAt(0)));
                entity.setRepowerReloadInd(Character.valueOf(fields[32].charAt(0)));
                entity.setMoneySendInd(Character.valueOf(fields[33].charAt(0)));
                entity.setDurbinRateInd(Character.valueOf(fields[34].charAt(0)));
                entity.setBussDate(businessDate);
                entity.setGenStatus(1);
                this.mcIssAcqRangeRepo.saveAndFlush((Object)entity);
            }
        }
        catch (Exception e) {
            log.error("Error::insertMcIssAccRange():", (Throwable)e);
        }
    }

    public void deleteMCIssAcqRange(String lowRange, String highRange, String priorityCode) {
        try {
            MCIssAcqRangeEntity entity = this.mcIssAcqRangeRepo.findByIssRangeLowAndIssRangeHighAndPriorityCode(lowRange, highRange, priorityCode);
            if (Objects.nonNull(entity)) {
                this.mcIssAcqRangeRepo.delete((Object)entity);
                this.mcIssAcqRangeRepo.flush();
            }
        }
        catch (Exception e) {
            log.error("Error::deleteAccRange():", (Throwable)e);
        }
    }

    public McT67Pro(Environment env, FileUploadLogRepository uploadLogRepo, CommonService commonService, MCIssAcqRangeRepository mcIssAcqRangeRepo) {
        this.env = env;
        this.uploadLogRepo = uploadLogRepo;
        this.commonService = commonService;
        this.mcIssAcqRangeRepo = mcIssAcqRangeRepo;
    }
}

