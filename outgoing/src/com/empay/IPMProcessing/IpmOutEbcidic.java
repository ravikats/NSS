/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.IPMProcessing.IPM
 *  com.empay.IPMProcessing.IpmOutEbcidic
 *  com.empay.common.entity.OutgoingSummaryEntity
 *  com.empay.common.repo.OutgoingSummaryRepo
 *  com.empay.cryptapi.CryptAPI
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.staging.entities.IpmOutWorkEntity
 *  com.empay.staging.entities.McAcqTxnDataEntity
 *  com.empay.staging.entities.McAcqTxnWorkEntity
 *  com.empay.staging.entities.McGCOTxnDataEntity
 *  com.empay.staging.entities.McGCOTxnWorkEntity
 *  com.empay.staging.entities.OutgoingReportDataWorkEntity
 *  com.empay.staging.entities.ViewGCOIpmOutWorkEntity
 *  com.empay.staging.entities.ViewIpmOutWorkEntity
 *  com.empay.staging.repo.IpmOutWorkRepo
 *  com.empay.staging.repo.McAcqTxnDataRepo
 *  com.empay.staging.repo.McAcqTxnWorkRepo
 *  com.empay.staging.repo.McGCODataRepo
 *  com.empay.staging.repo.McGCOWorkRepo
 *  com.empay.staging.repo.OutgoingReportDataWorkRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.repo.ViewGcoIpmOutWorkRepo
 *  com.empay.staging.repo.ViewIpmOutWorkRepo
 *  jakarta.persistence.EntityManager
 *  jakarta.persistence.criteria.CriteriaBuilder
 *  jakarta.persistence.criteria.CriteriaQuery
 *  jakarta.persistence.criteria.Expression
 *  jakarta.persistence.criteria.Predicate
 *  jakarta.persistence.criteria.Selection
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.IPMProcessing;

import com.empay.IPMProcessing.IPM;
import com.empay.common.entity.OutgoingSummaryEntity;
import com.empay.common.repo.OutgoingSummaryRepo;
import com.empay.cryptapi.CryptAPI;
import com.empay.cryptapi.DecryptResponseVo;
import com.empay.staging.entities.IpmOutWorkEntity;
import com.empay.staging.entities.McAcqTxnDataEntity;
import com.empay.staging.entities.McAcqTxnWorkEntity;
import com.empay.staging.entities.McGCOTxnDataEntity;
import com.empay.staging.entities.McGCOTxnWorkEntity;
import com.empay.staging.entities.OutgoingReportDataWorkEntity;
import com.empay.staging.entities.ViewGCOIpmOutWorkEntity;
import com.empay.staging.entities.ViewIpmOutWorkEntity;
import com.empay.staging.repo.IpmOutWorkRepo;
import com.empay.staging.repo.McAcqTxnDataRepo;
import com.empay.staging.repo.McAcqTxnWorkRepo;
import com.empay.staging.repo.McGCODataRepo;
import com.empay.staging.repo.McGCOWorkRepo;
import com.empay.staging.repo.OutgoingReportDataWorkRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.repo.ViewGcoIpmOutWorkRepo;
import com.empay.staging.repo.ViewIpmOutWorkRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Selection;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IpmOutEbcidic {
    private static final Logger log = LogManager.getLogger(IpmOutEbcidic.class);
    public static char[] cBitMap = new char[16];
    public static String fileId;
    private final Environment env;
    private final EntityManager entityManager;
    private final IpmOutWorkRepo ipmOutWorkRepo;
    private final IPM ipm;
    private final OutgoingSummaryRepo outFileSummaryRepo;
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final ViewIpmOutWorkRepo viewIpmOutWorkRepo;
    private final OutgoingReportDataWorkRepo outGoingReportRepo;
    private final McAcqTxnDataRepo mcAcqTxnDataRepo;
    private final PosTransactionRepo posTxnRepo;
    private final CryptAPI cryptApi;
    private final McGCOWorkRepo mcGCOWorkRepo;
    private final ViewGcoIpmOutWorkRepo viewGcoIpmOutWorkRepo;
    private final McGCODataRepo mcGCODataRepo;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String ipmPro(String fileName, String processorId, int seqNo, int insCode, int intCode, LocalDate businessDate, int refSerNumber, int userSerNumber, String insShortName, LocalDateTime fromDate, LocalDateTime toDate, String network, String fileType) {
        int recCnt = 0;
        String sDataFormat = "ASCII";
        long amount = 0L;
        FileOutputStream out = null;
        FileOutputStream outNew = null;
        PrintStream originalOut = System.out;
        FileInputStream in = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        try {
            if ("GCO".equals(fileType)) {
                this.callGCOIpmOutWorkEntity(Integer.valueOf(insCode), fileName, fromDate, toDate, network);
            } else {
                this.callIpmOutWorkEntity(Integer.valueOf(insCode), fileName, fromDate, toDate);
            }
            String currentdir = this.env.getProperty("RECON_OUT_" + insShortName);
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(currentdir + fileName + ".log", false))));
            System.out.println("IPM Process Started at - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            out = new FileOutputStream(currentdir + "IPM.tmp");
            currentdir = this.env.getProperty("RECON_OUT_" + insShortName);
            outNew = new FileOutputStream(currentdir + fileName);
            this.createHeaderMessage(out, processorId, seqNo);
            List results = this.ipmOutWorkRepo.findByInsCodeAndFileIdOrderBySerialNumberAsc(Integer.valueOf(insCode), fileName);
            List<String> encryptedCardTokens = results.stream().map(IpmOutWorkEntity::getDE002).toList();
            HashSet<String> uniqueCardTokens = new HashSet<String>(encryptedCardTokens);
            DecryptResponseVo response = this.cryptApi.getCardNumber(uniqueCardTokens);
            for (IpmOutWorkEntity rs : results) {
                int i;
                for (int i2 = 0; i2 < 16; ++i2) {
                    IpmOutEbcidic.cBitMap[i2] = '\u0000';
                }
                StringBuffer sbOutMsg = new StringBuffer("");
                String sMTI = rs.getDE001();
                String de002 = (String)response.getCardNumbers().get(rs.getDE002());
                String de003 = rs.getDE003();
                String de004 = rs.getDE004();
                String de012 = rs.getDE012();
                String de022 = rs.getDE022();
                String de023 = rs.getDE023();
                String de024 = rs.getDE024();
                String de025 = rs.getDE025();
                String de026 = rs.getDE026();
                String de030 = rs.getDE030();
                String de031 = rs.getDE031();
                String de032 = rs.getDE032();
                String de033 = rs.getDE033();
                String de037 = rs.getDE037();
                String de038 = rs.getDE038();
                String de040 = rs.getDE040();
                String de041 = rs.getDE041();
                String de042 = rs.getDE042();
                String de043 = rs.getDE043();
                String de048 = null;
                de048 = rs.getPDS23() + rs.getPDS25() + rs.getPDS52() + rs.getPDS148() + rs.getPDS149() + (rs.getPDS155() == null ? "" : rs.getPDS155()) + rs.getPDS165() + (rs.getPDS176() == null ? "" : rs.getPDS176()) + (rs.getPDS211() == null ? "" : rs.getPDS211()) + (rs.getPDS262() == null ? "" : rs.getPDS262());
                String de049 = rs.getDE049();
                String de054 = rs.getDE054();
                String de063 = rs.getDE063();
                String de071 = rs.getDE071();
                String de072 = rs.getDE072();
                String de093 = rs.getDE093();
                String de094 = rs.getDE094();
                String de095 = rs.getDE095();
                if (sMTI != null && sMTI.equals("1740")) {
                    de012 = null;
                    de022 = null;
                    de023 = null;
                    de026 = null;
                    de030 = null;
                    de031 = null;
                    de032 = null;
                    de037 = null;
                    de038 = null;
                    de040 = null;
                    de041 = null;
                    de048 = rs.getPDS25() + rs.getPDS137() + rs.getPDS148() + rs.getPDS165();
                    de054 = null;
                    de063 = null;
                }
                String DE055_84 = rs.getDE055_84() == null || rs.getDE055_84().isEmpty() ? "" : "84" + String.format("%1$2s", Integer.toHexString(rs.getDE055_84().length() / 2)).replace(' ', '0') + rs.getDE055_84();
                String pds165 = rs.getPDS165();
                String de055 = null;
                if (!pds165.substring(pds165.length() - 1).equals("C")) {
                    String de0559F10 = rs.getDE055_9F10() == null || rs.getDE055_9F10().isEmpty() ? "" : "9F10" + String.format("%1$2s", Integer.toHexString(rs.getDE055_9F10().length() / 2)).replace(' ', '0') + rs.getDE055_9F10();
                    de055 = rs.getDE055_9F26() + rs.getDE055_9F27() + de0559F10 + rs.getDE055_9F34() + rs.getDE055_9F33() + rs.getDE055_9F37() + rs.getDE055_9F36() + rs.getDE055_95() + rs.getDE055_9A() + rs.getDE055_9C() + rs.getDE055_9F02() + rs.getDE055_5F2A() + rs.getDE055_82() + rs.getDE055_9F1A() + rs.getDE055_9F03() + DE055_84;
                    de048 = rs.getPDS23() + rs.getPDS25() + rs.getPDS52() + rs.getPDS148() + rs.getPDS149() + rs.getPDS155() + rs.getPDS165() + (rs.getPDS176() == null ? "" : rs.getPDS176()) + (rs.getPDS211() == null ? "" : rs.getPDS211()) + (rs.getPDS262() == null ? "" : rs.getPDS262()) + (rs.getDE048_PDS0213() == null ? "" : rs.getDE048_PDS0213()) + (rs.getDE048_PDS0170() == null ? "" : rs.getDE048_PDS0170()) + (rs.getPDS0018() == null ? "" : rs.getPDS0018()) + (rs.getDE048_PDS0175() == null ? "" : rs.getDE048_PDS0175());
                }
                if (Long.parseLong(de004) > 0L) {
                    amount += Long.parseLong(de004);
                }
                if (de002 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 1, de002, cBitMap);
                }
                if (de003 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 2, de003, cBitMap);
                }
                if (de004 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 3, de004, cBitMap);
                }
                if (de012 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 11, de012, cBitMap);
                }
                if (de022 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 21, de022, cBitMap);
                }
                if (de023 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 22, de023, cBitMap);
                }
                if (de024 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 23, de024, cBitMap);
                }
                if (de025 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 24, de025, cBitMap);
                }
                if (de026 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 25, de026, cBitMap);
                }
                if (de030 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 29, de030, cBitMap);
                }
                if (de031 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 30, de031, cBitMap);
                }
                if (de032 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 31, de032, cBitMap);
                }
                if (de033 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 32, de033, cBitMap);
                }
                if (de037 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 36, de037, cBitMap);
                }
                if (de038 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 37, de038, cBitMap);
                }
                if (de040 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 39, de040, cBitMap);
                }
                if (de041 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 40, de041, cBitMap);
                }
                if (de042 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 41, de042, cBitMap);
                }
                if (de043 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 42, de043, cBitMap);
                }
                if (de048 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 47, de048, cBitMap);
                }
                if (de049 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 48, de049, cBitMap);
                }
                if (de054 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 53, de054, cBitMap);
                }
                StringBuffer sEbcidic = new StringBuffer("");
                for (i = 0; i < sbOutMsg.length(); ++i) {
                    sEbcidic.append((char)this.ipm.AsciiToEbcdic((int)sbOutMsg.charAt(i)));
                }
                if (de055 != null && de055.length() > 0) {
                    int i3;
                    this.ipm.AddIsoField(sbOutMsg, 54, de055, cBitMap);
                    int len = sEbcidic.length() + 3;
                    for (i3 = sEbcidic.length(); i3 < len; ++i3) {
                        sEbcidic.append((char)this.ipm.AsciiToEbcdic((int)sbOutMsg.charAt(i3)));
                    }
                    for (i3 = sEbcidic.length(); i3 < sbOutMsg.length(); ++i3) {
                        sEbcidic.append(sbOutMsg.charAt(i3));
                    }
                }
                if (de063 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 62, de063, cBitMap);
                }
                if (de071 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 70, de071, cBitMap);
                }
                if (de072 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 70, de072, cBitMap);
                }
                if (de093 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 92, de093, cBitMap);
                }
                if (de094 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 93, de094, cBitMap);
                }
                if (de095 != null) {
                    this.ipm.AddIsoField(sbOutMsg, 94, de095, cBitMap);
                }
                for (i = sEbcidic.length(); i < sbOutMsg.length(); ++i) {
                    sEbcidic.append((char)this.ipm.AsciiToEbcdic((int)sbOutMsg.charAt(i)));
                }
                this.createDetails(sMTI, cBitMap, sEbcidic, out);
                ++recCnt;
                String sStr = sMTI + new String(cBitMap) + String.valueOf(sbOutMsg);
                this.dumpMessage(sStr, sDataFormat);
            }
            this.createFooterMessage(out, String.valueOf(amount), Integer.toString(recCnt += 2), processorId, seqNo);
            in = new FileInputStream(currentdir + "IPM.tmp");
            int chrCnt = 0;
            while (in.available() > 0) {
                byte bByte = (byte)in.read();
                outNew.write(bByte);
                if (++chrCnt % 1012 != 0) continue;
                outNew.write(0);
                outNew.write(0);
            }
            int Rem1014 = 1012 - chrCnt % 1012;
            byte[] bExtraBytes = new byte[Rem1014 + 2];
            for (int i = 0; i < Rem1014; ++i) {
                bExtraBytes[i] = 0;
            }
            bExtraBytes[Rem1014] = 0;
            bExtraBytes[Rem1014 + 1] = 0;
            outNew.write(bExtraBytes);
            bExtraBytes = new byte[1014];
            Arrays.fill(bExtraBytes, (byte)0);
            outNew.write(bExtraBytes);
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Object[].class);
            if ("GCO".equals(fileType)) {
                workEntity = cq.from(McGCOTxnWorkEntity.class);
                messageTypeId = workEntity.get("mti");
                functionCode = workEntity.get("functionCode");
                procCode = cb.substring((Expression)workEntity.get("procCode"), 1, 2);
                amountSum = cb.sum(cb.coalesce((Expression)workEntity.get("txnAmount"), (Object)0.0));
                surAmountSum = cb.sum(cb.coalesce((Expression)workEntity.get("surchargeAmount"), (Object)0.0));
                netAmountSum = cb.sum(cb.sum(cb.coalesce((Expression)workEntity.get("txnAmount"), (Object)0.0), cb.coalesce((Expression)workEntity.get("surchargeAmount"), (Object)0.0)));
                cq.multiselect(new Selection[]{messageTypeId, functionCode, procCode, cb.count((Expression)workEntity.get("mti")), amountSum, surAmountSum, netAmountSum, cb.literal((Object)3)}).where(new Predicate[]{cb.equal((Expression)workEntity.get("generalStatus"), (Object)9), cb.equal((Expression)workEntity.get("insCode"), (Object)insCode)}).groupBy(new Expression[]{messageTypeId, functionCode, procCode});
            } else {
                workEntity = cq.from(McAcqTxnWorkEntity.class);
                messageTypeId = workEntity.get("messageTypeId");
                functionCode = workEntity.get("functionCode");
                procCode = cb.substring((Expression)workEntity.get("procCode"), 1, 2);
                amountSum = cb.sum(cb.coalesce((Expression)workEntity.get("txnAmount"), (Object)0.0));
                surAmountSum = cb.sum(cb.coalesce((Expression)workEntity.get("surchargeAmount"), (Object)0.0));
                netAmountSum = cb.sum(cb.sum(cb.coalesce((Expression)workEntity.get("txnAmount"), (Object)0.0), cb.coalesce((Expression)workEntity.get("surchargeAmount"), (Object)0.0)));
                cq.multiselect(new Selection[]{messageTypeId, functionCode, procCode, cb.count((Expression)workEntity.get("messageTypeId")), amountSum, surAmountSum, netAmountSum, cb.literal((Object)3)}).where(new Predicate[]{cb.equal((Expression)workEntity.get("generalStatus"), (Object)9), cb.equal((Expression)workEntity.get("institutionCode"), (Object)insCode)}).groupBy(new Expression[]{messageTypeId, functionCode, procCode});
            }
            List summary = this.entityManager.createQuery(cq).getResultList();
            ArrayList<OutgoingSummaryEntity> entities = new ArrayList<OutgoingSummaryEntity>();
            for (Object[] row : summary) {
                String typeIdValue = (String)row[0];
                String functionCodeValue = (String)row[1];
                String procCodeValue = (String)row[2];
                int count = ((Number)row[3]).intValue();
                Double amnt = ((Number)row[4]).doubleValue();
                Double surAmount = ((Number)row[5]).doubleValue();
                Double netAmount = ((Number)row[6]).doubleValue();
                OutgoingSummaryEntity entity = new OutgoingSummaryEntity();
                entity.setLastupdated(LocalDateTime.now());
                entity.setUpdatedUser(userSerNumber);
                entity.setInstitution(insCode);
                entity.setInterfaceCode(intCode);
                entity.setOutFileDate(businessDate);
                entity.setFileId(fileId);
                entity.setRefSerialNumber(refSerNumber);
                entity.setMessageTypeId(typeIdValue);
                entity.setFunctionCode(functionCodeValue);
                entity.setProcCode(procCodeValue);
                entity.setCount(Integer.valueOf(count));
                entity.setTxnAmount(amnt);
                entity.setSurchargeAmount(surAmount);
                entity.setNetAmount(netAmount);
                entity.setGeneralStatus(Integer.valueOf(3));
                entities.add(entity);
            }
            this.outFileSummaryRepo.saveAll(entities);
            if ("GCO".equals(fileType)) {
                List mcGcoWrkEntities = this.mcGCOWorkRepo.findByInsCodeAndGeneralStatus(insCode, 9);
                for (McGCOTxnWorkEntity entity : mcGcoWrkEntities) {
                    entity.setGeneralStatus(Integer.valueOf(4));
                    entity.setFileProcDate(LocalDate.now());
                    entity.setFileId(fileId);
                }
                this.mcGCOWorkRepo.saveAll((Iterable)mcGcoWrkEntities);
                this.mcGCOWorkRepo.flush();
                System.out.println("GCO IPM Process Over at - " + formatter.format(LocalDateTime.now()));
                System.out.flush();
                System.out.close();
                this.posTxnRepo.completeGcoPosStatus(insCode);
                this.moveGCOWorkToData(insCode, userSerNumber);
            } else {
                List mcAcqTxnWrkEntities = this.mcAcqTxnWorkRepo.findByInstitutionCodeAndGeneralStatus(Integer.valueOf(insCode), Integer.valueOf(9));
                for (McGCOTxnWorkEntity entity : mcAcqTxnWrkEntities) {
                    entity.setGeneralStatus(4);
                    entity.setFileProcDate(LocalDate.now());
                    entity.setFileID(fileId);
                }
                this.mcAcqTxnWorkRepo.saveAll((Iterable)mcAcqTxnWrkEntities);
                this.mcAcqTxnWorkRepo.flush();
                System.out.println("IPM Process Over at - " + formatter.format(LocalDateTime.now()));
                System.out.flush();
                System.out.close();
                this.posTxnRepo.completePosStatus(insCode);
                this.moveWorkToData(insCode, userSerNumber);
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
            e.printStackTrace();
            System.out.flush();
            System.out.close();
            fileId = null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + String.valueOf(e.getCause()));
            e.printStackTrace(System.out);
            e.printStackTrace();
            System.out.flush();
            System.out.close();
            fileId = null;
            log.error("IpmPro", (Throwable)e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (outNew != null) {
                    outNew.close();
                }
                System.setOut(originalOut);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileId;
    }

    public void createHeaderMessage(FileOutputStream out, String processorId, int seqNo) {
        try {
            StringBuffer sbOut = new StringBuffer("");
            for (int i = 0; i < 16; ++i) {
                IpmOutEbcidic.cBitMap[i] = '\u0000';
            }
            this.ipm.AddIsoField(sbOut, 23, "697", cBitMap);
            Object DE048 = "0105025002";
            DE048 = (String)DE048 + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%11s", processorId).replace(' ', '0') + String.format("%05d", seqNo) + "0122001" + this.env.getProperty("PROCESSING_MODE");
            fileId = ((String)DE048).substring(7, 32);
            this.ipm.AddIsoField(sbOut, 47, (String)DE048, cBitMap);
            this.ipm.AddIsoField(sbOut, 70, "00000001", cBitMap);
            StringBuffer sEbcidic = new StringBuffer("");
            for (int i = 0; i < sbOut.length(); ++i) {
                sEbcidic.append((char)this.ipm.AsciiToEbcdic((int)sbOut.charAt(i)));
            }
            this.createDetails("1644", cBitMap, sEbcidic, out);
            String sStr = "1644" + new String(cBitMap) + String.valueOf(sbOut);
            this.dumpMessage(sStr, "ASCII");
        }
        catch (Exception e) {
            System.out.println("CreateHeaderMessage()");
            e.printStackTrace(System.out);
            System.out.flush();
            System.out.close();
            log.error("CreateHeaderMessage()", (Throwable)e);
        }
    }

    @Transactional
    public void callIpmOutWorkEntity(Integer institutionCode, String fileName, LocalDateTime fromDate, LocalDateTime toDate) {
        try {
            List fetchedEntities = new ArrayList();
            fetchedEntities = fromDate == null ? this.mcAcqTxnWorkRepo.findByInstitutionCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(institutionCode, Integer.valueOf(3), toDate) : this.mcAcqTxnWorkRepo.findByInstitutionCodeAndGeneralStatusAndLocalDateTimeBetween(institutionCode, Integer.valueOf(3), fromDate, toDate);
            ArrayList<McAcqTxnWorkEntity> updatedEntities = new ArrayList<McAcqTxnWorkEntity>();
            for (McAcqTxnWorkEntity entity : fetchedEntities) {
                entity.setGeneralStatus(9);
                entity.setFileID(fileId);
                updatedEntities.add(entity);
            }
            this.mcAcqTxnWorkRepo.saveAll(updatedEntities);
            this.mcAcqTxnWorkRepo.flush();
            List dataList = this.viewIpmOutWorkRepo.findAll();
            ArrayList<IpmOutWorkEntity> ipmOutentity = new ArrayList<IpmOutWorkEntity>();
            for (ViewIpmOutWorkEntity viewEntity : dataList) {
                IpmOutWorkEntity ipmOutWorkEntity = new IpmOutWorkEntity();
                ipmOutWorkEntity.setInsCode(((Integer)Optional.ofNullable(institutionCode).orElse(null)).intValue());
                ipmOutWorkEntity.setFileId((String)Optional.ofNullable(fileName).orElse(null));
                ipmOutWorkEntity.setRefNumber(((Integer)Optional.ofNullable(viewEntity.getSerialNo()).orElse(null)).intValue());
                ipmOutWorkEntity.setDE001((String)Optional.ofNullable(viewEntity.getDe001()).orElse(null));
                ipmOutWorkEntity.setDE002((String)Optional.ofNullable(viewEntity.getDe002()).orElse(null));
                ipmOutWorkEntity.setDE003((String)Optional.ofNullable(viewEntity.getDe003()).orElse(null));
                ipmOutWorkEntity.setDE004((String)Optional.ofNullable(viewEntity.getDe004()).orElse(null));
                ipmOutWorkEntity.setDE012((String)Optional.ofNullable(viewEntity.getDe012()).orElse(null));
                ipmOutWorkEntity.setDE014((String)Optional.ofNullable(viewEntity.getDe014()).orElse(null));
                ipmOutWorkEntity.setDE022((String)Optional.ofNullable(viewEntity.getDe022()).orElse(null));
                ipmOutWorkEntity.setDE023((String)Optional.ofNullable(viewEntity.getDe023()).orElse(null));
                ipmOutWorkEntity.setDE024((String)Optional.ofNullable(viewEntity.getDe024()).orElse(null));
                ipmOutWorkEntity.setDE025((String)Optional.ofNullable(viewEntity.getDe025()).orElse(null));
                ipmOutWorkEntity.setDE026((String)Optional.ofNullable(viewEntity.getDe026()).orElse(null));
                ipmOutWorkEntity.setDE030((String)Optional.ofNullable(viewEntity.getDe030()).orElse(null));
                ipmOutWorkEntity.setDE031((String)Optional.ofNullable(viewEntity.getDe031()).orElse(null));
                ipmOutWorkEntity.setDE032((String)Optional.ofNullable(viewEntity.getDe033()).orElse(null));
                ipmOutWorkEntity.setDE033((String)Optional.ofNullable(viewEntity.getDe033()).orElse(null));
                ipmOutWorkEntity.setDE037((String)Optional.ofNullable(viewEntity.getDe037()).orElse(null));
                ipmOutWorkEntity.setDE038((String)Optional.ofNullable(viewEntity.getDe038()).orElse(null));
                ipmOutWorkEntity.setDE040((String)Optional.ofNullable(viewEntity.getDe040()).orElse(null));
                ipmOutWorkEntity.setDE041((String)Optional.ofNullable(viewEntity.getDe041()).orElse(null));
                ipmOutWorkEntity.setDE042((String)Optional.ofNullable(viewEntity.getDe042()).orElse(null));
                ipmOutWorkEntity.setDE043((String)Optional.ofNullable(viewEntity.getDe043()).orElse(null));
                ipmOutWorkEntity.setDE049((String)Optional.ofNullable(viewEntity.getDe049()).orElse(null));
                ipmOutWorkEntity.setDE054((String)Optional.ofNullable(viewEntity.getDe054()).orElse(null));
                ipmOutWorkEntity.setDE063((String)Optional.ofNullable(viewEntity.getDe063()).orElse(null));
                ipmOutWorkEntity.setDE071((String)Optional.ofNullable(viewEntity.getDe071()).orElse(null));
                ipmOutWorkEntity.setDE072((String)Optional.ofNullable(viewEntity.getDe072()).orElse(null));
                ipmOutWorkEntity.setDE093((String)Optional.ofNullable(viewEntity.getDe093()).orElse(null));
                ipmOutWorkEntity.setDE094((String)Optional.ofNullable(viewEntity.getDe033()).orElse(null));
                ipmOutWorkEntity.setDE095((String)Optional.ofNullable(viewEntity.getDe095()).orElse(null));
                ipmOutWorkEntity.setPDS23((String)Optional.ofNullable(viewEntity.getDe0480023Pds23()).orElse(null));
                ipmOutWorkEntity.setPDS25(Optional.ofNullable(viewEntity.getDe0480025()).orElse(""));
                ipmOutWorkEntity.setPDS52(Optional.ofNullable(viewEntity.getDe0480052()).orElse(""));
                ipmOutWorkEntity.setPDS137((String)Optional.ofNullable(viewEntity.getDe0480137()).orElse(null));
                ipmOutWorkEntity.setPDS148((String)Optional.ofNullable(viewEntity.getDe0480148()).orElse(null));
                ipmOutWorkEntity.setPDS149(Optional.ofNullable(viewEntity.getDe0480149()).orElse(""));
                ipmOutWorkEntity.setPDS155((String)Optional.ofNullable(viewEntity.getDe0480155()).orElse(null));
                ipmOutWorkEntity.setPDS165((String)Optional.ofNullable(viewEntity.getDe0480165()).orElse(null));
                ipmOutWorkEntity.setPDS176(Optional.ofNullable(viewEntity.getDe0480176()).orElse(""));
                ipmOutWorkEntity.setPDS211((String)Optional.ofNullable(viewEntity.getDe0480211()).orElse(null));
                ipmOutWorkEntity.setPDS262(Optional.ofNullable(viewEntity.getDe0480262()).orElse(""));
                ipmOutWorkEntity.setDE055_9F26(Optional.ofNullable(viewEntity.getDe0559f26()).orElse(""));
                ipmOutWorkEntity.setDE055_9F27(Optional.ofNullable(viewEntity.getDe0559f27()).orElse(""));
                ipmOutWorkEntity.setDE055_9F10(Optional.ofNullable(viewEntity.getDe0559f10()).orElse(""));
                ipmOutWorkEntity.setDE055_9F37(Optional.ofNullable(viewEntity.getDe0559f37()).orElse(""));
                ipmOutWorkEntity.setDE055_9F36(Optional.ofNullable(viewEntity.getDe0559f36()).orElse(""));
                ipmOutWorkEntity.setDE055_95(Optional.ofNullable(viewEntity.getDe05595()).orElse(""));
                ipmOutWorkEntity.setDE055_9A(Optional.ofNullable(viewEntity.getDe0559a()).orElse(""));
                ipmOutWorkEntity.setDE055_9C(Optional.ofNullable(viewEntity.getDe0559c()).orElse(""));
                ipmOutWorkEntity.setDE055_9F02(Optional.ofNullable(viewEntity.getDe0559f02()).orElse(""));
                ipmOutWorkEntity.setDE055_5F2A(Optional.ofNullable(viewEntity.getDe0555f2a()).orElse(""));
                ipmOutWorkEntity.setDE055_82(Optional.ofNullable(viewEntity.getDe05582()).orElse(""));
                ipmOutWorkEntity.setDE055_9F1A(Optional.ofNullable(viewEntity.getDe0559f1a()).orElse(""));
                ipmOutWorkEntity.setDE055_9F03(Optional.ofNullable(viewEntity.getDe0559f03()).orElse(""));
                ipmOutWorkEntity.setDE048_PDS0213(Optional.ofNullable(viewEntity.getDe048Pds0213()).orElse(""));
                ipmOutWorkEntity.setDE048_PDS0170(Optional.ofNullable(viewEntity.getDe0480170()).orElse(""));
                ipmOutWorkEntity.setDE055_84(Optional.ofNullable(viewEntity.getDe05584()).orElse(""));
                ipmOutWorkEntity.setDE055_9F33(Optional.ofNullable(viewEntity.getDe0559f33()).orElse(""));
                ipmOutWorkEntity.setDE055_9F34(Optional.ofNullable(viewEntity.getDe0559f34()).orElse(""));
                ipmOutWorkEntity.setPDS0018((String)Optional.ofNullable(viewEntity.getDe0480018()).orElse(null));
                ipmOutWorkEntity.setDE048_PDS0175((String)Optional.ofNullable(viewEntity.getDe0480175()).orElse(null));
                ipmOutentity.add(ipmOutWorkEntity);
            }
            this.ipmOutWorkRepo.saveAll(ipmOutentity);
            this.processData(dataList, institutionCode, fileName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processData(List<ViewIpmOutWorkEntity> dataList, Integer institutionCode, String fileName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            Map<LocalDate, Map<String, AbstractMap.SimpleEntry>> groupedByKey = dataList.stream().filter(entity -> {
                try {
                    LocalDateTime.parse(entity.getLocalDateTime(), formatter);
                    return true;
                }
                catch (DateTimeParseException e) {
                    return false;
                }
            }).collect(Collectors.groupingBy(entity -> LocalDateTime.parse(entity.getLocalDateTime(), formatter).toLocalDate(), Collectors.groupingBy(ViewIpmOutWorkEntity::getTxnType, Collectors.collectingAndThen(Collectors.toList(), entityList -> {
                long count = entityList.size();
                double sum = entityList.stream().map(ViewIpmOutWorkEntity::getTxnAmount).mapToDouble(str -> {
                    try {
                        return Double.parseDouble(str);
                    }
                    catch (NumberFormatException e) {
                        return 0.0;
                    }
                }).sum();
                return new AbstractMap.SimpleEntry<Long, Double>(count, sum);
            }))));
            ArrayList<OutgoingReportDataWorkEntity> entitiesToSave = new ArrayList<OutgoingReportDataWorkEntity>();
            for (Map.Entry<LocalDate, Map<String, AbstractMap.SimpleEntry>> dateEntry : groupedByKey.entrySet()) {
                LocalDate txnDate = dateEntry.getKey();
                if (txnDate == null) continue;
                Map<String, AbstractMap.SimpleEntry> txnTypeGroups = dateEntry.getValue();
                for (Map.Entry<String, AbstractMap.SimpleEntry> txnTypeEntry : txnTypeGroups.entrySet()) {
                    String txnType = txnTypeEntry.getKey();
                    AbstractMap.SimpleEntry countAndSum = txnTypeEntry.getValue();
                    OutgoingReportDataWorkEntity targetEntity = new OutgoingReportDataWorkEntity();
                    targetEntity.setLastUpdated(LocalDateTime.now());
                    targetEntity.setUpdatedUser(Integer.valueOf(2));
                    targetEntity.setInstitutionCode(institutionCode.intValue());
                    targetEntity.setOutFileId(fileName);
                    targetEntity.setOutgoingDate(LocalDateTime.now());
                    targetEntity.setTxnDate(txnDate);
                    targetEntity.setNetwork("MC");
                    targetEntity.setPosOrgPg("POS");
                    targetEntity.setTxnType(txnType);
                    targetEntity.setCount((Long)countAndSum.getKey());
                    targetEntity.setAmount((Double)countAndSum.getValue());
                    entitiesToSave.add(targetEntity);
                }
            }
            this.outGoingReportRepo.saveAll(entitiesToSave);
        }
        catch (Exception e) {
            log.error("processData()", (Throwable)e);
        }
    }

    @Transactional
    public void moveWorkToData(int institutionCode, int user) {
        try {
            List workEntities = this.mcAcqTxnWorkRepo.findByInstitutionCodeAndGeneralStatus(Integer.valueOf(institutionCode), Integer.valueOf(4));
            ArrayList<McAcqTxnDataEntity> dataEntities = new ArrayList<McAcqTxnDataEntity>();
            LocalDateTime localDateTime = LocalDateTime.now();
            for (McAcqTxnWorkEntity workEntity : workEntities) {
                McAcqTxnDataEntity dataEntity = new McAcqTxnDataEntity();
                dataEntity.setSerNumber(workEntity.getSerNumber());
                dataEntity.setLastUpdated(localDateTime);
                dataEntity.setUpdatedUser(workEntity.getUpdatedUser());
                dataEntity.setInstitutionCode(Integer.valueOf(workEntity.getInstitutionCode()));
                dataEntity.setIntCode(workEntity.getIntCode());
                dataEntity.setTxnRefSerNumber(workEntity.getTxnRefSerNumber());
                dataEntity.setPrjSerNumber(workEntity.getPrjSerNumber());
                dataEntity.setGeneralStatus(workEntity.getGeneralStatus());
                dataEntity.setChipTxnType(workEntity.getTxnType());
                dataEntity.setFileID(workEntity.getFileID());
                dataEntity.setMessageTypeId(workEntity.getMessageTypeId());
                dataEntity.setProcCode(workEntity.getProcCode());
                dataEntity.setFunctionCode(workEntity.getFunctionCode());
                dataEntity.setTxnAmount(workEntity.getTxnAmount());
                dataEntity.setSurchargeAmount(workEntity.getSurchargeAmount());
                dataEntity.setFileProcDate(workEntity.getFileProcDate());
                dataEntity.setCardNumber(workEntity.getCardNumber());
                dataEntity.setLocalDateTime(workEntity.getLocalDateTime());
                dataEntity.setExpiryDate(workEntity.getExpiryDate());
                dataEntity.setPosDataCode(workEntity.getPosDataCode());
                dataEntity.setMsgReasonCode(workEntity.getMsgReasonCode());
                dataEntity.setMcc(workEntity.getMcc());
                dataEntity.setAcqRefData(workEntity.getAcqRefData());
                dataEntity.setAcqinstIdCode(workEntity.getAcqinstIdCode());
                dataEntity.setRrn(workEntity.getRrn());
                dataEntity.setApprovalCode(workEntity.getApprovalCode());
                dataEntity.setResponseCode(workEntity.getResponseCode());
                dataEntity.setServiceCode(workEntity.getServiceCode());
                dataEntity.setTerminalId(workEntity.getTerminalId());
                dataEntity.setMerchantId(workEntity.getMerchantId());
                dataEntity.setMeName(workEntity.getMeName());
                dataEntity.setMeCity(workEntity.getMeCity());
                dataEntity.setMePinCode(workEntity.getMePinCode());
                dataEntity.setMeCountry(workEntity.getMeCountry());
                dataEntity.setTrlType(workEntity.getTrlType());
                dataEntity.setMotoEcomIndicator(workEntity.getMotoEcomIndicator());
                dataEntity.setTxnFeeAmount(workEntity.getTxnFeeAmount());
                dataEntity.setTxnCurrExp(workEntity.getTxnCurrExp());
                dataEntity.setTxnCurCode(workEntity.getTxnCurCode());
                dataEntity.setIrd(workEntity.getIrd());
                dataEntity.setSettlementIndicator(workEntity.getSettlementIndicator());
                dataEntity.setCardSeqNumber(workEntity.getCardSeqNumber());
                dataEntity.setAppCryptogram(workEntity.getAppCryptogram());
                dataEntity.setCryptInfoData(workEntity.getCryptInfoData());
                dataEntity.setIssAppData(workEntity.getIssAppData());
                dataEntity.setUpblNumber(workEntity.getUpblNumber());
                dataEntity.setAppTxnCounter(workEntity.getAppTxnCounter());
                dataEntity.setTrlVerResult(workEntity.getTrlVerResult());
                dataEntity.setTxnDate(workEntity.getTxnDate());
                dataEntity.setChipTxnDate(workEntity.getChipTxnDate());
                dataEntity.setChipTxnType(workEntity.getChipTxnType());
                dataEntity.setCryptAmount(workEntity.getCryptAmount());
                dataEntity.setAppICProfile(workEntity.getAppICProfile());
                dataEntity.setTrlConCode(workEntity.getTrlConCode());
                dataEntity.setChipCashBack(workEntity.getChipCashBack());
                dataEntity.setCvmResult(workEntity.getCvmResult());
                dataEntity.setTrlCapabilities(workEntity.getTrlCapabilities());
                dataEntity.setIfdSerNumber(workEntity.getIfdSerNumber());
                dataEntity.setTcc(workEntity.getTcc());
                dataEntity.setChipCurCode(workEntity.getChipCurCode());
                dataEntity.setChipTrlType(workEntity.getChipTrlType());
                dataEntity.setTrlAppVerNumber(workEntity.getTrlAppVerNumber());
                dataEntity.setTxnSeqCounter(workEntity.getTxnSeqCounter());
                dataEntity.setIssAuthData(workEntity.getIssAuthData());
                dataEntity.setTxnlifeCycleId(workEntity.getTxnlifeCycleId());
                dataEntity.setMsgNumber(workEntity.getMsgNumber());
                dataEntity.setMemberText(workEntity.getMemberText());
                dataEntity.setOrgInstIdCode(workEntity.getOrgInstIdCode());
                dataEntity.setRevIndiCator(workEntity.getRevIndiCator());
                dataEntity.setMaid(workEntity.getMaid());
                dataEntity.setCardType(workEntity.getCardType());
                dataEntity.setCardDomIntlFlag(workEntity.getCardDomIntlFlag());
                dataEntity.setDmsSmsMode(workEntity.getDmsSmsMode());
                dataEntity.setPosPgType(workEntity.getPosPgType());
                dataEntity.setCentreProcDate(workEntity.getCentreProcDate());
                dataEntity.setEncryptedCardNumber(workEntity.getEncryptedCardNumber());
                dataEntity.setMeCountryOfOrigin(workEntity.getMeCountryOfOrigin());
                dataEntity.setTipAmount(workEntity.getTipAmount());
                dataEntity.setChipTrlCapabilities(workEntity.getChipTrlCapabilities());
                dataEntity.setDedicatedFileName(workEntity.getDedicatedFileName());
                dataEntity.setCardAccepStreetAddress(workEntity.getCardAccepStreetAddress());
                dataEntity.setCustomerServicePhNum(workEntity.getCustomerServicePhNum());
                dataEntity.setDccIndicator(workEntity.getDccIndicator());
                dataEntity.setDccAmount(workEntity.getDccAmount());
                dataEntity.setDccCurrency(workEntity.getDccCurrency());
                dataEntity.setDccTxnCurrencyExponent(workEntity.getDccTxnCurrencyExponent());
                dataEntity.setMposAccDevType(workEntity.getMposAccDevType());
                dataEntity.setAccepterUrlAddress(workEntity.getAccepterUrlAddress());
                dataEntities.add(dataEntity);
                dataEntity = null;
            }
            this.mcAcqTxnDataRepo.saveAll(dataEntities);
            this.mcAcqTxnWorkRepo.deleteAll((Iterable)workEntities);
            dataEntities = null;
        }
        catch (Exception e) {
            log.error("MoveWorkToData()", (Throwable)e);
        }
    }

    public void createDetails(String sMTI, char[] cBitMap, StringBuffer sbOutMsg, FileOutputStream out) {
        try {
            int i;
            int iMsgLen = sbOutMsg.length() + 16 + 4;
            byte[] bData = new byte[iMsgLen];
            byte[] bLengthBytes = new byte[]{0, 0, (byte)(iMsgLen / 256), (byte)(iMsgLen % 256)};
            bData[0] = (byte)this.ipm.AsciiToEbcdic((int)sMTI.charAt(0));
            bData[1] = (byte)this.ipm.AsciiToEbcdic((int)sMTI.charAt(1));
            bData[2] = (byte)this.ipm.AsciiToEbcdic((int)sMTI.charAt(2));
            bData[3] = (byte)this.ipm.AsciiToEbcdic((int)sMTI.charAt(3));
            int iPos = 4;
            this.ipm.SetSecondaryBitMap(cBitMap);
            for (i = 0; i <= 15; ++i) {
                bData[iPos + i] = (byte)cBitMap[i];
            }
            iPos = 20;
            for (i = 0; i < sbOutMsg.length(); ++i) {
                bData[iPos + i] = (byte)sbOutMsg.charAt(i);
            }
            out.write(bLengthBytes);
            out.write(bData);
            iPos = iMsgLen;
            out.flush();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.flush();
            System.out.close();
            log.error("CreateDetails()", (Throwable)e);
        }
    }

    public void dumpMessage(String sStr, String sDataFormat) {
        try {
            System.out.println("Record Length " + sStr.length());
            System.out.println("Hex dump");
            Object dump = "";
            for (int i = 1; i <= sStr.length(); ++i) {
                dump = (String)dump + "%02X".formatted(sStr.charAt(i - 1)) + " ";
                dump = (String)dump + String.format("%02X", sStr.charAt(i - 1)) + " ";
                if (i % 30 != 0) continue;
                System.out.println((String)dump);
                dump = "";
            }
            System.out.println((String)dump);
            System.out.flush();
            System.out.println("ISO Fields");
            System.out.println(String.format("%5s: %s", "MTI", sStr.substring(0, 4)));
            String subfield = null;
            this.ipm.ProcessIsoMessageAscii(sStr);
            for (int i = 1; i < 128; ++i) {
                subfield = this.ipm.GetIsoField(sStr, i, sDataFormat);
                if (subfield != null) {
                    if (i == 1) {
                        System.out.println(String.format("%s%03d: %s", "DE", i + 1, "PRESENT"));
                    } else {
                        System.out.println(String.format("%s%03d: %s", "DE", i + 1, subfield));
                    }
                    if (i == 47) {
                        String pdsTag = null;
                        String dataLength = null;
                        String pdsData = null;
                        String msg = subfield.replace("\u0000", "");
                        for (int pdsPosition = 0; pdsPosition < msg.length(); pdsPosition += Integer.parseInt(dataLength)) {
                            pdsTag = subfield.substring(pdsPosition, pdsPosition + 4);
                            dataLength = subfield.substring(pdsPosition += 4, pdsPosition + 3);
                            pdsData = subfield.substring(pdsPosition += 3, pdsPosition + Integer.parseInt(dataLength));
                            System.out.println(String.format("     %s%4s: %s", "PDS", pdsTag, pdsData));
                            System.out.flush();
                        }
                    }
                }
                System.out.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in DumpMessage()");
            e.printStackTrace(System.out);
            System.out.flush();
            System.out.close();
            log.error("DumpMessage()", (Throwable)e);
        }
    }

    public void createFooterMessage(FileOutputStream out, String amount, String recCnt, String processorId, int seqNo) {
        try {
            StringBuffer sbOut = new StringBuffer("");
            System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%11s", processorId).replace(' ', '0') + String.format("%05d", seqNo) + "0301016");
            for (int i = 0; i < 16; ++i) {
                IpmOutEbcidic.cBitMap[i] = '\u0000';
            }
            this.ipm.AddIsoField(sbOut, 23, "695", cBitMap);
            Object de048 = "0105025002";
            de048 = (String)de048 + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%11s", processorId).replace(' ', '0') + String.format("%05d", seqNo) + "0301016";
            de048 = (String)de048 + String.format("%16s", amount).replace(' ', '0') + "0306008" + String.format("%8s", recCnt).replace(' ', '0');
            this.ipm.AddIsoField(sbOut, 47, (String)de048, cBitMap);
            this.ipm.AddIsoField(sbOut, 70, String.format("%8s", recCnt).replace(' ', '0'), cBitMap);
            StringBuffer sEbcidic = new StringBuffer("");
            for (int i = 0; i < sbOut.length(); ++i) {
                sEbcidic.append((char)this.ipm.AsciiToEbcdic((int)sbOut.charAt(i)));
            }
            this.createDetails("1644", cBitMap, sEbcidic, out);
            String sStr = "1644" + new String(cBitMap) + String.valueOf(sbOut);
            this.dumpMessage(sStr, "ASCII");
        }
        catch (Exception e) {
            System.out.println("CreateFooterMessage()");
            e.printStackTrace(System.out);
            System.out.flush();
            System.out.close();
            log.error("CreateFooterMessage()", (Throwable)e);
        }
    }

    @Transactional
    public void callGCOIpmOutWorkEntity(Integer institutionCode, String fileName, LocalDateTime fromDate, LocalDateTime toDate, String network) {
        try {
            List fetchedEntities = new ArrayList();
            fetchedEntities = fromDate == null ? this.mcGCOWorkRepo.findByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeLessThanEqual(institutionCode, 3, network, toDate) : this.mcGCOWorkRepo.findByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeBetween(institutionCode, 3, network, fromDate, toDate);
            ArrayList<McGCOTxnWorkEntity> updatedEntities = new ArrayList<McGCOTxnWorkEntity>();
            for (McGCOTxnWorkEntity entity : fetchedEntities) {
                entity.setGeneralStatus(Integer.valueOf(9));
                entity.setFileId(fileId);
                updatedEntities.add(entity);
            }
            this.mcGCOWorkRepo.saveAll(updatedEntities);
            this.mcGCOWorkRepo.flush();
            List dataList = this.viewGcoIpmOutWorkRepo.findAll();
            ArrayList<IpmOutWorkEntity> ipmOutentity = new ArrayList<IpmOutWorkEntity>();
            for (ViewGCOIpmOutWorkEntity viewEntity : dataList) {
                IpmOutWorkEntity ipmOutWorkEntity = new IpmOutWorkEntity();
                ipmOutWorkEntity.setInsCode(((Integer)Optional.ofNullable(institutionCode).orElse(null)).intValue());
                ipmOutWorkEntity.setFileId((String)Optional.ofNullable(fileName).orElse(null));
                ipmOutWorkEntity.setRefNumber(((Integer)Optional.ofNullable(viewEntity.getSerialNo()).orElse(null)).intValue());
                ipmOutWorkEntity.setDE001((String)Optional.ofNullable(viewEntity.getDe001()).orElse(null));
                ipmOutWorkEntity.setDE002((String)Optional.ofNullable(viewEntity.getDe002()).orElse(null));
                ipmOutWorkEntity.setDE003((String)Optional.ofNullable(viewEntity.getDe003()).orElse(null));
                ipmOutWorkEntity.setDE004((String)Optional.ofNullable(viewEntity.getDe004()).orElse(null));
                ipmOutWorkEntity.setDE012((String)Optional.ofNullable(viewEntity.getDe012()).orElse(null));
                ipmOutWorkEntity.setDE014((String)Optional.ofNullable(viewEntity.getDe014()).orElse(null));
                ipmOutWorkEntity.setDE022((String)Optional.ofNullable(viewEntity.getDe022()).orElse(null));
                ipmOutWorkEntity.setDE023((String)Optional.ofNullable(viewEntity.getDe023()).orElse(null));
                ipmOutWorkEntity.setDE024((String)Optional.ofNullable(viewEntity.getDe024()).orElse(null));
                ipmOutWorkEntity.setDE025((String)Optional.ofNullable(viewEntity.getDe025()).orElse(null));
                ipmOutWorkEntity.setDE026((String)Optional.ofNullable(viewEntity.getDe026()).orElse(null));
                ipmOutWorkEntity.setDE030((String)Optional.ofNullable(viewEntity.getDe030()).orElse(null));
                ipmOutWorkEntity.setDE031((String)Optional.ofNullable(viewEntity.getDe031()).orElse(null));
                ipmOutWorkEntity.setDE032((String)Optional.ofNullable(viewEntity.getDe033()).orElse(null));
                ipmOutWorkEntity.setDE033((String)Optional.ofNullable(viewEntity.getDe033()).orElse(null));
                ipmOutWorkEntity.setDE037((String)Optional.ofNullable(viewEntity.getDe037()).orElse(null));
                ipmOutWorkEntity.setDE038((String)Optional.ofNullable(viewEntity.getDe038()).orElse(null));
                ipmOutWorkEntity.setDE040((String)Optional.ofNullable(viewEntity.getDe040()).orElse(null));
                ipmOutWorkEntity.setDE041((String)Optional.ofNullable(viewEntity.getDe041()).orElse(null));
                ipmOutWorkEntity.setDE042((String)Optional.ofNullable(viewEntity.getDe042()).orElse(null));
                ipmOutWorkEntity.setDE043((String)Optional.ofNullable(viewEntity.getDe043()).orElse(null));
                ipmOutWorkEntity.setDE049((String)Optional.ofNullable(viewEntity.getDe049()).orElse(null));
                ipmOutWorkEntity.setDE054((String)Optional.ofNullable(viewEntity.getDe054()).orElse(null));
                ipmOutWorkEntity.setDE063((String)Optional.ofNullable(viewEntity.getDe063()).orElse(null));
                ipmOutWorkEntity.setDE071((String)Optional.ofNullable(viewEntity.getDe071()).orElse(null));
                ipmOutWorkEntity.setDE072((String)Optional.ofNullable(viewEntity.getDe072()).orElse(null));
                ipmOutWorkEntity.setDE093((String)Optional.ofNullable(viewEntity.getDe093()).orElse(null));
                ipmOutWorkEntity.setDE094((String)Optional.ofNullable(viewEntity.getDe033()).orElse(null));
                ipmOutWorkEntity.setDE095((String)Optional.ofNullable(viewEntity.getDe095()).orElse(null));
                ipmOutWorkEntity.setPDS23((String)Optional.ofNullable(viewEntity.getDe0480023Pds23()).orElse(null));
                ipmOutWorkEntity.setPDS25(Optional.ofNullable(viewEntity.getDe0480025()).orElse(""));
                ipmOutWorkEntity.setPDS52(Optional.ofNullable(viewEntity.getDe0480052()).orElse(""));
                ipmOutWorkEntity.setPDS137((String)Optional.ofNullable(viewEntity.getDe0480137()).orElse(null));
                ipmOutWorkEntity.setPDS148((String)Optional.ofNullable(viewEntity.getDe0480148()).orElse(null));
                ipmOutWorkEntity.setPDS149(Optional.ofNullable(viewEntity.getDe0480149()).orElse(""));
                ipmOutWorkEntity.setPDS155((String)Optional.ofNullable(viewEntity.getDe0480155()).orElse(null));
                ipmOutWorkEntity.setPDS165((String)Optional.ofNullable(viewEntity.getDe0480165()).orElse(null));
                ipmOutWorkEntity.setPDS176(Optional.ofNullable(viewEntity.getDe0480176()).orElse(""));
                ipmOutWorkEntity.setPDS211((String)Optional.ofNullable(viewEntity.getDe0480211()).orElse(null));
                ipmOutWorkEntity.setPDS262(Optional.ofNullable(viewEntity.getDe0480262()).orElse(""));
                ipmOutWorkEntity.setDE055_9F26(Optional.ofNullable(viewEntity.getDe0559f26()).orElse(""));
                ipmOutWorkEntity.setDE055_9F27(Optional.ofNullable(viewEntity.getDe0559f27()).orElse(""));
                ipmOutWorkEntity.setDE055_9F10(Optional.ofNullable(viewEntity.getDe0559f10()).orElse(""));
                ipmOutWorkEntity.setDE055_9F37(Optional.ofNullable(viewEntity.getDe0559f37()).orElse(""));
                ipmOutWorkEntity.setDE055_9F36(Optional.ofNullable(viewEntity.getDe0559f36()).orElse(""));
                ipmOutWorkEntity.setDE055_95(Optional.ofNullable(viewEntity.getDe05595()).orElse(""));
                ipmOutWorkEntity.setDE055_9A(Optional.ofNullable(viewEntity.getDe0559a()).orElse(""));
                ipmOutWorkEntity.setDE055_9C(Optional.ofNullable(viewEntity.getDe0559c()).orElse(""));
                ipmOutWorkEntity.setDE055_9F02(Optional.ofNullable(viewEntity.getDe0559f02()).orElse(""));
                ipmOutWorkEntity.setDE055_5F2A(Optional.ofNullable(viewEntity.getDe0555f2a()).orElse(""));
                ipmOutWorkEntity.setDE055_82(Optional.ofNullable(viewEntity.getDe05582()).orElse(""));
                ipmOutWorkEntity.setDE055_9F1A(Optional.ofNullable(viewEntity.getDe0559f1a()).orElse(""));
                ipmOutWorkEntity.setDE055_9F03(Optional.ofNullable(viewEntity.getDe0559f03()).orElse(""));
                ipmOutWorkEntity.setDE055_84(Optional.ofNullable(viewEntity.getDe05584()).orElse(""));
                ipmOutWorkEntity.setDE055_9F33(Optional.ofNullable(viewEntity.getDe0559f33()).orElse(""));
                ipmOutWorkEntity.setDE055_9F34(Optional.ofNullable(viewEntity.getDe0559f34()).orElse(""));
                ipmOutentity.add(ipmOutWorkEntity);
            }
            this.ipmOutWorkRepo.saveAll(ipmOutentity);
            this.processGCOData(dataList, institutionCode, fileName);
        }
        catch (Exception e) {
            log.error("callGCOIpmOutWorkEntity()", (Throwable)e);
        }
    }

    public void processGCOData(List<ViewGCOIpmOutWorkEntity> dataList, Integer institutionCode, String fileName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            Map<LocalDate, Map<String, AbstractMap.SimpleEntry>> groupedByKey = dataList.stream().filter(entity -> {
                try {
                    LocalDateTime.parse(entity.getLocalDateTime(), formatter);
                    return true;
                }
                catch (DateTimeParseException e) {
                    return false;
                }
            }).collect(Collectors.groupingBy(entity -> LocalDateTime.parse(entity.getLocalDateTime(), formatter).toLocalDate(), Collectors.groupingBy(ViewGCOIpmOutWorkEntity::getTxnType, Collectors.collectingAndThen(Collectors.toList(), entityList -> {
                long count = entityList.size();
                double sum = entityList.stream().map(ViewGCOIpmOutWorkEntity::getTxnAmount).mapToDouble(str -> {
                    try {
                        return Double.parseDouble(str);
                    }
                    catch (NumberFormatException e) {
                        return 0.0;
                    }
                }).sum();
                return new AbstractMap.SimpleEntry<Long, Double>(count, sum);
            }))));
            ArrayList<OutgoingReportDataWorkEntity> entitiesToSave = new ArrayList<OutgoingReportDataWorkEntity>();
            for (Map.Entry<LocalDate, Map<String, AbstractMap.SimpleEntry>> dateEntry : groupedByKey.entrySet()) {
                LocalDate txnDate = dateEntry.getKey();
                if (txnDate == null) continue;
                Map<String, AbstractMap.SimpleEntry> txnTypeGroups = dateEntry.getValue();
                for (Map.Entry<String, AbstractMap.SimpleEntry> txnTypeEntry : txnTypeGroups.entrySet()) {
                    String txnType = txnTypeEntry.getKey();
                    AbstractMap.SimpleEntry countAndSum = txnTypeEntry.getValue();
                    OutgoingReportDataWorkEntity targetEntity = new OutgoingReportDataWorkEntity();
                    targetEntity.setLastUpdated(LocalDateTime.now());
                    targetEntity.setUpdatedUser(Integer.valueOf(2));
                    targetEntity.setInstitutionCode(institutionCode.intValue());
                    targetEntity.setOutFileId(fileName);
                    targetEntity.setOutgoingDate(LocalDateTime.now());
                    targetEntity.setTxnDate(txnDate);
                    targetEntity.setNetwork("MC");
                    targetEntity.setPosOrgPg("POS");
                    targetEntity.setTxnType(txnType);
                    targetEntity.setCount((Long)countAndSum.getKey());
                    targetEntity.setAmount((Double)countAndSum.getValue());
                    entitiesToSave.add(targetEntity);
                }
            }
            this.outGoingReportRepo.saveAll(entitiesToSave);
        }
        catch (Exception e) {
            log.error("processGCOData()", (Throwable)e);
        }
    }

    @Transactional
    public void moveGCOWorkToData(int institutionCode, int user) {
        try {
            List workEntities = this.mcGCOWorkRepo.findByInsCodeAndGeneralStatus(institutionCode, 4);
            ArrayList<McGCOTxnDataEntity> dataEntities = new ArrayList<McGCOTxnDataEntity>();
            LocalDateTime localDateTime = LocalDateTime.now();
            for (McGCOTxnWorkEntity workEntity : workEntities) {
                McGCOTxnDataEntity dataEntity = new McGCOTxnDataEntity();
                dataEntity.setSerialNumber(workEntity.getSerialNumber());
                dataEntity.setLastUpdated(localDateTime);
                dataEntity.setUpdatedUser(workEntity.getUpdatedUser());
                dataEntity.setInsCode(workEntity.getInsCode());
                dataEntity.setIntCode(workEntity.getIntCode());
                dataEntity.setTxnRefSerNumber(workEntity.getTxnRefSerNumber());
                dataEntity.setGeneralStatus(workEntity.getGeneralStatus().intValue());
                dataEntity.setPrjSerNumber(workEntity.getPrjSerNumber());
                dataEntity.setBusinessDate(workEntity.getBusinessDate());
                dataEntity.setProcCode(workEntity.getProcCode());
                dataEntity.setApprovalCode(workEntity.getApprovalCode());
                dataEntity.setServiceCode(workEntity.getServiceCode());
                dataEntity.setFileProcDate(workEntity.getFileProcDate());
                dataEntity.setMti(workEntity.getMti());
                dataEntity.setTxnType(workEntity.getTxnType());
                dataEntity.setTxnAmount(workEntity.getTxnAmount());
                dataEntity.setSurchargeAmount(workEntity.getSurchargeAmount());
                dataEntity.setLocalDateTime(workEntity.getLocalDateTime());
                dataEntity.setChPresent(workEntity.getChPresent());
                dataEntity.setCardPresent(workEntity.getCardPresent());
                dataEntity.setPosEntryMode(workEntity.getPosEntryMode());
                dataEntity.setPosDataMode(workEntity.getPosDataMode());
                dataEntity.setMcc(workEntity.getMcc());
                dataEntity.setAcqInstIdCode(workEntity.getAcqInstIdCode());
                dataEntity.setRrn(workEntity.getRrn());
                dataEntity.setArn(workEntity.getArn());
                dataEntity.setTerminalId(workEntity.getTerminalId());
                dataEntity.setMerchantId(workEntity.getMerchantId());
                dataEntity.setMeName(workEntity.getMeName());
                dataEntity.setMeAddress(workEntity.getMeAddress());
                dataEntity.setMeCity(workEntity.getMeCity());
                dataEntity.setMeZipCode(workEntity.getMeZipCode());
                dataEntity.setMeCountry(workEntity.getMeCountry());
                dataEntity.setTxnCurCode(workEntity.getTxnCurCode());
                dataEntity.setFileId(workEntity.getFileId());
                dataEntity.setEncryptedCardNumber(workEntity.getEncryptedCardNumber());
                dataEntity.setSettlementIndicator(workEntity.getSettlementIndicator());
                dataEntity.setTxnlifeCycleId(workEntity.getTxnlifeCycleId());
                dataEntity.setFunctionCode(workEntity.getFunctionCode());
                dataEntity.setMsgReasonCode(workEntity.getMsgReasonCode());
                dataEntity.setCardType(workEntity.getCardType());
                dataEntity.setCardDomIntlFlag(workEntity.getCardDomIntlFlag());
                dataEntity.setNetwork(workEntity.getNetwork());
                dataEntity.setMposAccDevType(workEntity.getMposAccDevType());
                dataEntity.setAccepterUrlAddress(workEntity.getAccepterUrlAddress());
                dataEntity.setTxnCurrencyExponent(workEntity.getTxnCurrencyExponent());
                dataEntities.add(dataEntity);
                dataEntity = null;
            }
            this.mcGCODataRepo.saveAll(dataEntities);
            this.mcGCOWorkRepo.deleteAll((Iterable)workEntities);
            dataEntities = null;
        }
        catch (Exception e) {
            log.error("moveGCOWorkToData()", (Throwable)e);
        }
    }

    public IpmOutEbcidic(Environment env, EntityManager entityManager, IpmOutWorkRepo ipmOutWorkRepo, IPM ipm, OutgoingSummaryRepo outFileSummaryRepo, McAcqTxnWorkRepo mcAcqTxnWorkRepo, ViewIpmOutWorkRepo viewIpmOutWorkRepo, OutgoingReportDataWorkRepo outGoingReportRepo, McAcqTxnDataRepo mcAcqTxnDataRepo, PosTransactionRepo posTxnRepo, CryptAPI cryptApi, McGCOWorkRepo mcGCOWorkRepo, ViewGcoIpmOutWorkRepo viewGcoIpmOutWorkRepo, McGCODataRepo mcGCODataRepo) {
        this.env = env;
        this.entityManager = entityManager;
        this.ipmOutWorkRepo = ipmOutWorkRepo;
        this.ipm = ipm;
        this.outFileSummaryRepo = outFileSummaryRepo;
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.viewIpmOutWorkRepo = viewIpmOutWorkRepo;
        this.outGoingReportRepo = outGoingReportRepo;
        this.mcAcqTxnDataRepo = mcAcqTxnDataRepo;
        this.posTxnRepo = posTxnRepo;
        this.cryptApi = cryptApi;
        this.mcGCOWorkRepo = mcGCOWorkRepo;
        this.viewGcoIpmOutWorkRepo = viewGcoIpmOutWorkRepo;
        this.mcGCODataRepo = mcGCODataRepo;
    }
}

