/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McAcqTxnDataEntity
 *  com.empay.staging.entities.McAcqTxnWorkEntity
 *  com.empay.staging.entities.McAcqTxnWorkEntity$McAcqTxnWorkEntityBuilder
 *  com.empay.staging.entities.PosTransactionEntity
 *  com.empay.staging.repo.MCRejectionsRepo
 *  com.empay.staging.repo.McAcqTxnDataRepo
 *  com.empay.staging.repo.McAcqTxnWorkRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.service.OutgoingUpdateService
 *  com.empay.vo.RejectedTxnUpdateRequestVo
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.service;

import com.empay.staging.entities.McAcqTxnDataEntity;
import com.empay.staging.entities.McAcqTxnWorkEntity;
import com.empay.staging.entities.PosTransactionEntity;
import com.empay.staging.repo.MCRejectionsRepo;
import com.empay.staging.repo.McAcqTxnDataRepo;
import com.empay.staging.repo.McAcqTxnWorkRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.vo.RejectedTxnUpdateRequestVo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OutgoingUpdateService {
    private static final Logger log = LogManager.getLogger(OutgoingUpdateService.class);
    private final McAcqTxnDataRepo mcAcqTxnDataRepo;
    private final MCRejectionsRepo mcRejectionsRepo;
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final PosTransactionRepo posTransactionRepo;

    public String updateOutGoingData(Integer insCode, Integer user, RejectedTxnUpdateRequestVo requestVo) {
        boolean dataValidFlag = true;
        String responseMsg = "updated successfuly";
        try {
            List mcWorkData;
            List rejectedlist;
            List staggedDataList = this.mcAcqTxnDataRepo.findByRrnAndMessageTypeIdAndProcCodeOrderByLastUpdatedDesc(requestVo.getRrn(), requestVo.getMti(), requestVo.getProcessingCode());
            if (staggedDataList.isEmpty()) {
                dataValidFlag = false;
                responseMsg = "No Outgoing details found for this transaction";
            }
            if ((rejectedlist = this.mcRejectionsRepo.findByRetRefNumberAndMsgTypeIdAndProcCode(requestVo.getRrn(), requestVo.getMti(), requestVo.getProcessingCode())).isEmpty()) {
                dataValidFlag = false;
                responseMsg = "No rejected entries for this input is available in the system";
            }
            if (!(mcWorkData = this.mcAcqTxnWorkRepo.findByRrnAndMessageTypeIdAndProcCode(requestVo.getRrn(), requestVo.getMti(), requestVo.getProcessingCode())).isEmpty()) {
                dataValidFlag = false;
                responseMsg = "Transaction is already available in the outgoing queue";
            }
            if (dataValidFlag) {
                McAcqTxnDataEntity staggedData = (McAcqTxnDataEntity)this.mcAcqTxnDataRepo.findByRrnAndMessageTypeIdAndProcCodeOrderByLastUpdatedDesc(requestVo.getRrn(), requestVo.getMti(), requestVo.getProcessingCode()).getFirst();
                McAcqTxnWorkEntity mcWorkTxn = this.mapRequestToWork(staggedData, requestVo, user, insCode);
                if (mcWorkTxn == null) {
                    return "Data updation failed";
                }
                this.mcAcqTxnWorkRepo.saveAndFlush((Object)mcWorkTxn);
                Optional posDataEntity = this.posTransactionRepo.findById((Object)staggedData.getTxnRefSerNumber());
                if (posDataEntity.isPresent()) {
                    PosTransactionEntity posTxnEntity = (PosTransactionEntity)posDataEntity.get();
                    posTxnEntity.setGenStatus(Integer.valueOf(4));
                    posTxnEntity.setOutStatus("Marked for Outgoing");
                    this.posTransactionRepo.saveAndFlush((Object)posTxnEntity);
                }
            }
        }
        catch (Exception e) {
            log.error("update Out GoingData failed : ", (Throwable)e);
            responseMsg = "rejected data updation failed";
        }
        return responseMsg;
    }

    public McAcqTxnWorkEntity mapRequestToWork(McAcqTxnDataEntity staggedData, RejectedTxnUpdateRequestVo requestVo, Integer user, Integer insCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        try {
            McAcqTxnWorkEntity.McAcqTxnWorkEntityBuilder mcAsqBuilder = McAcqTxnWorkEntity.builder();
            mcAsqBuilder.lastUpdated(LocalDateTime.now()).updatedUser(user).institutionCode(insCode.intValue()).intCode(staggedData.getIntCode()).prjSerNumber(staggedData.getPrjSerNumber()).generalStatus(3).txnRefSerNumber(staggedData.getTxnRefSerNumber()).txnType(requestVo.getTransactionType()).messageTypeId(staggedData.getMessageTypeId()).cardNumber(requestVo.getCardNumber()).procCode(staggedData.getProcCode()).txnAmount(Double.valueOf(requestVo.getTransactionAmount() == null ? 0.0 : Double.valueOf(requestVo.getTransactionAmount()))).surchargeAmount(Double.valueOf(requestVo.getSurchargeAmount() == null ? 0.0 : Double.valueOf(requestVo.getSurchargeAmount()))).expiryDate(requestVo.getExpiryDate()).posDataCode(requestVo.getPosDataCode()).functionCode(requestVo.getFunctionCode()).msgReasonCode(requestVo.getMessageReasonCode()).mcc(requestVo.getMcc()).rrn(staggedData.getRrn()).approvalCode(requestVo.getApprovalCode()).responseCode(requestVo.getResponseCode()).serviceCode(requestVo.getSeviceCode()).terminalId(staggedData.getTerminalId()).merchantId(staggedData.getMerchantId()).meName(requestVo.getMerchantName()).meCity(requestVo.getMerchantCity()).meCountry(requestVo.getMerchantCountry()).mePinCode(requestVo.getMerchantZipCode()).chipTrlType(requestVo.getChipTerminalType()).txnFeeAmount(Double.valueOf(requestVo.getTransactionFeeAmount() == null ? 0.0 : Double.valueOf(requestVo.getTransactionFeeAmount()))).txnCurCode(requestVo.getTransactionCurrencyCode()).ird(requestVo.getIrd()).cardSeqNumber(requestVo.getCardSequenceNumber()).appCryptogram(requestVo.getApplicationCryptogram()).cryptInfoData(requestVo.getCryptogramInformationData()).issAppData(requestVo.getIssuerApplicationData()).upblNumber(requestVo.getUpblNumber()).appTxnCounter(requestVo.getApplicationTransactionCounter()).trlVerResult(requestVo.getTerminalVerificationResult()).chipTxnType(requestVo.getChipTransactionType()).cryptAmount(Double.valueOf(requestVo.getCryptAmount() == null ? 0.0 : Double.valueOf(requestVo.getCryptAmount()))).appICProfile(requestVo.getApplicationInterchangeProfile()).trlConCode(requestVo.getTerminalCountryCode()).cvmResult(requestVo.getCvmResult()).trlCapabilities(requestVo.getTerminalCapabilities()).ifdSerNumber(staggedData.getIfdSerNumber()).tcc(requestVo.getTcc()).chipCurCode(requestVo.getChipCurrencyCode()).chipTrlType(requestVo.getChipTerminalType()).trlAppVerNumber(requestVo.getTerminalApplicationVerificationNumber()).txnSeqCounter(requestVo.getTransactionSequenceCounter()).issAuthData(requestVo.getIssuerAuthData()).msgNumber(requestVo.getMessageNumber()).memberText(requestVo.getMemberText()).maid(requestVo.getMastercardAssignedId()).fileProcDate(staggedData.getFileProcDate()).fileID(requestVo.getFileId()).encryptedCardNumber(staggedData.getEncryptedCardNumber()).meCountryOfOrigin(staggedData.getMeCountryOfOrigin()).txnCurrExp(requestVo.getTransactionCurrencyExponent() == null ? 0 : Integer.parseInt(requestVo.getTransactionCurrencyExponent())).trlType(requestVo.getTerminalType()).motoEcomIndicator(requestVo.getEcomIndicator()).cardSeqNumber(requestVo.getCardSequenceNumber()).chipCashBack(Double.valueOf(requestVo.getCashbackAmount() == null ? 0.0 : Double.valueOf(requestVo.getCashbackAmount()))).txnlifeCycleId(requestVo.getTransactionLifeCycleId()).posPgType(requestVo.getPosPgType()).acqinstIdCode(requestVo.getAcquireInstitutionId()).orgInstIdCode(staggedData.getOrgInstIdCode()).acqRefData(staggedData.getAcqRefData());
            if (!Objects.isNull(requestVo.getLocalDateTime()) && !requestVo.getLocalDateTime().isEmpty()) {
                mcAsqBuilder.localDateTime(LocalDateTime.parse(requestVo.getLocalDateTime(), formatter));
            }
            if (!Objects.isNull(requestVo.getCentralProcessingDate()) && !requestVo.getCentralProcessingDate().isEmpty()) {
                mcAsqBuilder.centreProcDate(LocalDate.parse(requestVo.getCentralProcessingDate().substring(0, 8), DateTimeFormatter.ofPattern("yyyyMMdd")));
            }
            if (!Objects.isNull(requestVo.getReversalIndicator()) && !requestVo.getReversalIndicator().isEmpty()) {
                mcAsqBuilder.revIndiCator(Character.valueOf(requestVo.getReversalIndicator().charAt(0)));
            }
            if (!Objects.isNull(requestVo.getSettledIndicator()) && !requestVo.getSettledIndicator().isEmpty()) {
                mcAsqBuilder.settlementIndicator(Character.valueOf(requestVo.getSettledIndicator().charAt(0)));
            }
            if (!Objects.isNull(requestVo.getCardType()) && !requestVo.getCardType().isEmpty()) {
                mcAsqBuilder.cardType(Character.valueOf(requestVo.getCardType().charAt(0)));
            }
            if (!Objects.isNull(requestVo.getDomesticInternationFlag()) && !requestVo.getDomesticInternationFlag().isEmpty()) {
                mcAsqBuilder.cardDomIntlFlag(Character.valueOf(requestVo.getDomesticInternationFlag().charAt(0)));
            }
            if (!Objects.isNull(requestVo.getSmsDmsFlag()) && !requestVo.getSmsDmsFlag().isEmpty()) {
                mcAsqBuilder.dmsSmsMode(Character.valueOf(requestVo.getSmsDmsFlag().charAt(0)));
            }
            return mcAsqBuilder.build();
        }
        catch (Exception e) {
            log.error("mapRequestToWork failed:", (Throwable)e);
            return null;
        }
    }

    public OutgoingUpdateService(McAcqTxnDataRepo mcAcqTxnDataRepo, MCRejectionsRepo mcRejectionsRepo, McAcqTxnWorkRepo mcAcqTxnWorkRepo, PosTransactionRepo posTransactionRepo) {
        this.mcAcqTxnDataRepo = mcAcqTxnDataRepo;
        this.mcRejectionsRepo = mcRejectionsRepo;
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.posTransactionRepo = posTransactionRepo;
    }
}

