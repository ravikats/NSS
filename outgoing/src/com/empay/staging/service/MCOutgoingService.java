/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.IPMProcessing.IpmOutEbcidic
 *  com.empay.common.entity.BusinessDateEntity
 *  com.empay.common.entity.FileFormatsEntity
 *  com.empay.common.entity.InterfacesEntity
 *  com.empay.common.entity.OutGoingFileProcessingEntity
 *  com.empay.common.repo.BusinessDateRepo
 *  com.empay.common.repo.FileFormatsRepo
 *  com.empay.common.repo.InterfacesRepo
 *  com.empay.common.repo.OutFileLogRepo
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  com.empay.staging.repo.AcquirerBinsRepo
 *  com.empay.staging.service.IOutGoingSummaryService
 *  com.empay.staging.service.MCOutgoingService
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.staging.service;

import com.empay.IPMProcessing.IpmOutEbcidic;
import com.empay.common.entity.BusinessDateEntity;
import com.empay.common.entity.FileFormatsEntity;
import com.empay.common.entity.InterfacesEntity;
import com.empay.common.entity.OutGoingFileProcessingEntity;
import com.empay.common.repo.BusinessDateRepo;
import com.empay.common.repo.FileFormatsRepo;
import com.empay.common.repo.InterfacesRepo;
import com.empay.common.repo.OutFileLogRepo;
import com.empay.staging.entities.AcquirerBinsEntity;
import com.empay.staging.repo.AcquirerBinsRepo;
import com.empay.staging.service.IOutGoingSummaryService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MCOutgoingService {
    private static final Logger log = LogManager.getLogger(MCOutgoingService.class);
    @Autowired
    private AcquirerBinsRepo acqBinRepo;
    @Autowired
    private FileFormatsRepo fileFormatsRepo;
    @Autowired
    private OutFileLogRepo outFileRepo;
    @Autowired
    private InterfacesRepo interfacesRepo;
    @Autowired
    private BusinessDateRepo businessDateRepo;
    @Autowired
    private IpmOutEbcidic ipm;
    @Autowired
    private IOutGoingSummaryService iOutGoingSummaryService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Transactional
    public String processMCOutgoing(int insCode, int user, int formatCode, String insShortName, LocalDateTime fromDate, LocalDateTime toDate) {
        String fileName = null;
        String ProcessorId = null;
        int SeqNo = 0;
        String intCategory = "MCI";
        Character forType = Character.valueOf('O');
        try {
            AcquirerBinsEntity acqBin;
            Integer[] status = new Integer[]{1, 9};
            FileFormatsEntity fileFormatEntity = this.fileFormatsRepo.findBySystemCodeAndType(Integer.valueOf(formatCode), forType);
            Integer forCode = fileFormatEntity == null ? 0 : fileFormatEntity.getCode();
            InterfacesEntity interfaces = this.interfacesRepo.findByInterfaceCategory(intCategory);
            int intCode = interfaces == null ? 0 : interfaces.getInterfaceCode();
            List results = this.outFileRepo.findByFormatCodeAndGeneratedStatusIn(forCode, status);
            if (!results.isEmpty()) {
                return "File Generation already Scheduled";
            }
            OutGoingFileProcessingEntity entity = new OutGoingFileProcessingEntity();
            entity.setLastUpdatedDate(LocalDateTime.now());
            entity.setGeneratedDate(LocalDateTime.now());
            entity.setLastUpdatedUser(user);
            entity.setInstitutionCode(insCode);
            entity.setInterfaceCode(Integer.valueOf(intCode));
            entity.setFormatCode(forCode);
            entity.setGeneratedStatus(9);
            List result = this.businessDateRepo.findByInstitutionCode(insCode);
            if (!result.isEmpty()) {
                BusinessDateEntity businessDateEntity = (BusinessDateEntity)result.get(0);
                entity.setBusinessDate(businessDateEntity.getBusinessDate());
            }
            int outgoingLogSerialNumber = ((OutGoingFileProcessingEntity)this.outFileRepo.saveAndFlush((Object)entity)).getSerialNumber();
            List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(Integer.valueOf(insCode), "M");
            if (!acqBinList.isEmpty() && (acqBin = (AcquirerBinsEntity)acqBinList.get(0)) != null) {
                ProcessorId = acqBin.getMcIcaNo();
                SeqNo = acqBin.getOutfileDate() != null && acqBin.getOutfileDate().toLocalDate().equals(LocalDate.now()) ? acqBin.getOutFileSeq() : 1;
                acqBin.setOutFileSeq(Integer.valueOf(SeqNo + 1));
                acqBin.setOutfileDate(LocalDateTime.now());
                this.acqBinRepo.saveAndFlush((Object)acqBin);
            }
            fileName = insShortName + "R111" + LocalDate.now().format(this.formatter) + "." + String.format("%02d", SeqNo);
            OutGoingFileProcessingEntity outGoingFileProcEntity = this.outFileRepo.findByInstitutionCodeAndSerialNumber(Integer.valueOf(insCode), Integer.valueOf(outgoingLogSerialNumber));
            outGoingFileProcEntity.setLastUpdatedDate(LocalDateTime.now());
            outGoingFileProcEntity.setFileName(fileName);
            this.outFileRepo.saveAndFlush((Object)outGoingFileProcEntity);
            String fileID = this.ipm.ipmPro(fileName, ProcessorId, SeqNo, insCode, intCode, LocalDate.now(), outgoingLogSerialNumber, user, insShortName, fromDate, toDate, "", "");
            outGoingFileProcEntity.setFileId(fileID);
            outGoingFileProcEntity.setGeneratedStatus(fileID == null ? 5 : 4);
            this.outFileRepo.saveAndFlush((Object)outGoingFileProcEntity);
            this.iOutGoingSummaryService.generateOutgoingSummaryPDF(user, insCode, intCode, outgoingLogSerialNumber, intCategory, insShortName, outGoingFileProcEntity, "");
        }
        catch (Exception e) {
            log.error("processMCOutgoing()", (Throwable)e);
            return "Failed";
        }
        return "Success";
    }
}

