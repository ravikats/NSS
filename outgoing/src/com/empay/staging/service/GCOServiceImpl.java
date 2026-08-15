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
 *  com.empay.staging.service.GCOService
 *  com.empay.staging.service.GCOServiceImpl
 *  com.empay.staging.service.IOutGoingSummaryService
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
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
import com.empay.staging.service.GCOService;
import com.empay.staging.service.IOutGoingSummaryService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GCOServiceImpl
implements GCOService {
    private static final Logger log = LoggerFactory.getLogger(GCOServiceImpl.class);
    private final AcquirerBinsRepo acqBinRepo;
    private final FileFormatsRepo fileFormatsRepo;
    private final InterfacesRepo interfacesRepo;
    private final BusinessDateRepo businessDateRepo;
    private final IpmOutEbcidic ipm;
    private final OutFileLogRepo outFileRepo;
    private final IOutGoingSummaryService iOutGoingSummaryService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Transactional
    public String generateMcCollectionOnly(Integer insCode, Integer user, Integer formatCode, String insShortName, LocalDateTime fromDate, LocalDateTime toDate, String network) {
        String fileName = null;
        String ProcessorId = null;
        int SeqNo = 0;
        String intCategory = "MCI";
        Character forType = Character.valueOf('O');
        try {
            AcquirerBinsEntity acqBin;
            Integer[] status = new Integer[]{1, 9};
            FileFormatsEntity fileFormatEntity = this.fileFormatsRepo.findBySystemCodeAndType(formatCode, forType);
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
            entity.setLastUpdatedUser(user.intValue());
            entity.setInstitutionCode(insCode.intValue());
            entity.setInterfaceCode(Integer.valueOf(intCode));
            entity.setFormatCode(forCode);
            entity.setGeneratedStatus(9);
            List result = this.businessDateRepo.findByInstitutionCode(insCode.intValue());
            if (!result.isEmpty()) {
                BusinessDateEntity businessDateEntity = (BusinessDateEntity)result.get(0);
                entity.setBusinessDate(businessDateEntity.getBusinessDate());
            }
            int outgoingLogSerialNumber = ((OutGoingFileProcessingEntity)this.outFileRepo.saveAndFlush((Object)entity)).getSerialNumber();
            List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(insCode, "M");
            if (!acqBinList.isEmpty() && (acqBin = (AcquirerBinsEntity)acqBinList.get(0)) != null) {
                ProcessorId = acqBin.getMcIcaNo();
                SeqNo = acqBin.getOutfileDate() != null && acqBin.getOutfileDate().toLocalDate().equals(LocalDate.now()) ? acqBin.getOutFileSeq() : 1;
                acqBin.setOutFileSeq(Integer.valueOf(SeqNo + 1));
                acqBin.setOutfileDate(LocalDateTime.now());
                this.acqBinRepo.saveAndFlush((Object)acqBin);
            }
            fileName = insShortName + "R111" + LocalDate.now().format(this.formatter) + "." + String.format("%02d", SeqNo);
            OutGoingFileProcessingEntity outGoingFileProcEntity = this.outFileRepo.findByInstitutionCodeAndSerialNumber(insCode, Integer.valueOf(outgoingLogSerialNumber));
            outGoingFileProcEntity.setLastUpdatedDate(LocalDateTime.now());
            outGoingFileProcEntity.setFileName(fileName);
            this.outFileRepo.saveAndFlush((Object)outGoingFileProcEntity);
            String fileID = this.ipm.ipmPro(fileName, ProcessorId, SeqNo, insCode.intValue(), intCode, LocalDate.now(), outgoingLogSerialNumber, user.intValue(), insShortName, fromDate, toDate, network, "GCO");
            outGoingFileProcEntity.setFileId(fileID);
            outGoingFileProcEntity.setGeneratedStatus(fileID == null ? 5 : 4);
            this.outFileRepo.saveAndFlush((Object)outGoingFileProcEntity);
            this.iOutGoingSummaryService.generateOutgoingSummaryPDF(user.intValue(), insCode.intValue(), intCode, outgoingLogSerialNumber, intCategory, insShortName, outGoingFileProcEntity, "GCO");
        }
        catch (Exception e) {
            log.error("generateMcCollectionOnly()", (Throwable)e);
            return "Failed";
        }
        return "Success";
    }

    public GCOServiceImpl(AcquirerBinsRepo acqBinRepo, FileFormatsRepo fileFormatsRepo, InterfacesRepo interfacesRepo, BusinessDateRepo businessDateRepo, IpmOutEbcidic ipm, OutFileLogRepo outFileRepo, IOutGoingSummaryService iOutGoingSummaryService) {
        this.acqBinRepo = acqBinRepo;
        this.fileFormatsRepo = fileFormatsRepo;
        this.interfacesRepo = interfacesRepo;
        this.businessDateRepo = businessDateRepo;
        this.ipm = ipm;
        this.outFileRepo = outFileRepo;
        this.iOutGoingSummaryService = iOutGoingSummaryService;
    }
}

