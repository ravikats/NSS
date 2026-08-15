// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import com.empay.common.entities.FileUploadLogEntity;
import com.empay.entities.PosTransactionEntity;
import java.util.List;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import com.empay.tlfprocessing.vo.DeleteTxnVo;
import com.empay.repositories.AmexAcqTxnRepo;
import com.empay.repositories.VisaAcqTxnRepo;
import com.empay.common.repo.FileUploadRepo;
import com.empay.repositories.McAcqTxnWorkRepo;
import com.empay.repositories.PosTransactionRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransactionService
{
    private static final Logger log;
    private final PosTransactionRepository posTransactionRepo;
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final FileUploadRepo fileUploadRepo;
    private final VisaAcqTxnRepo visaAcqTxnWorkRepo;
    private final AmexAcqTxnRepo amexAcqTxnRepo;
    
    public Map<String, String> deleteTxn(final DeleteTxnVo inputVo) {
        final Map<String, String> responseBody = new HashMap<String, String>();
        String responseMsg = "No Transaction Found";
        try {
            final List<PosTransactionEntity> entity = this.posTransactionRepo.findByRrnAndTerminalIdAndProcCodeAndMsgTypeIdAndTxnAmount(inputVo.getRetRefNumber(), inputVo.getCardAcceptorTid(), inputVo.getProcessCode(), inputVo.getMti(), Double.valueOf(inputVo.getAmountTransaction()));
            if (Objects.nonNull(entity) && entity.size() > 1) {
                responseMsg = "Failed to delete, Multiple records found !!";
            }
            responseBody.put("message", responseMsg);
        }
        catch (final Exception e) {
            DeleteTransactionService.log.error("Error::deleteTxnFile():", (Throwable)e);
            responseBody.put("status", "Error Occured!");
        }
        return responseBody;
    }
    
    public Map<String, String> deleteTxnFile(final String fileName) {
        final Map<String, String> response = new HashMap<String, String>();
        try {
            final FileUploadLogEntity fileUploadLogEntity = this.fileUploadRepo.findByFileName(fileName);
            if (Objects.isNull(fileUploadLogEntity)) {
                response.put("message", "File Does Not Exist!");
            }
            else {
                this.deleteTxnFileThread(fileUploadLogEntity);
                response.put("message", "File Deletion Scheduled Successfully");
            }
        }
        catch (final Exception e) {
            DeleteTransactionService.log.error("Error::deleteTxnFile():", (Throwable)e);
            response.put("Status", "Error Occured!");
        }
        return response;
    }
    
    private void deleteTxnFileThread(final FileUploadLogEntity entity) {
        final Runnable run = () -> this.deleteTxFile(entity);
        final Thread thread = new Thread(run);
        thread.start();
    }
    
    @Transactional
    public void deleteTxFile(final FileUploadLogEntity entity) {
        try {
            entity.setUploadStatus(8);
            entity.setLastUpdated(LocalDateTime.now());
            this.fileUploadRepo.saveAndFlush((Object)entity);
            final Object[] genStatus = { 3, 4, 5 };
            if (this.posTransactionRepo.deleteByJobNumberAndGenStatusIn(entity.getJobNumber(), genStatus) == 0) {
                DeleteTransactionService.log.info("No such file found : POS_TRANSACTIONS");
            }
            if (this.mcAcqTxnWorkRepo.deleteByPrjSerNumberAndGeneralStatusIn(entity.getJobNumber(), genStatus) == 0) {
                DeleteTransactionService.log.info("No such file found : MC_ACQ_TXN_WORK");
            }
            if (this.visaAcqTxnWorkRepo.deleteByPrjSerNumberAndGenStatusIn(entity.getJobNumber(), genStatus) == 0) {
                DeleteTransactionService.log.info("No such file found : VISA_ACQ_TXN_WORK");
            }
            if (this.amexAcqTxnRepo.deleteByPrjSerNumberAndGenStatusIn(entity.getJobNumber(), genStatus) == 0) {
                DeleteTransactionService.log.info("No such file found : AMEX_ACQ_TXN_WORK");
            }
            this.fileUploadRepo.delete((Object)entity);
            this.posTransactionRepo.flush();
            this.mcAcqTxnWorkRepo.flush();
            this.visaAcqTxnWorkRepo.flush();
            this.fileUploadRepo.flush();
            DeleteTransactionService.log.info("File deleted successfully");
        }
        catch (final Exception e) {
            entity.setUploadStatus(7);
            entity.setLastUpdated(LocalDateTime.now());
            this.fileUploadRepo.saveAndFlush((Object)entity);
            DeleteTransactionService.log.info("Could not delete file");
            DeleteTransactionService.log.error("Error:deleteTxnFileThread():", (Throwable)e);
        }
    }
    
    private String findStatus(final PosTransactionEntity posData) {
        String responsDesc = null;
        try {
            responsDesc = switch (posData.getGenStatus()) {
                case 3,  4,  5 -> {
                    this.mcAcqTxnWorkRepo.deleteByRrn(posData.getRrn());
                    this.mcAcqTxnWorkRepo.flush();
                    this.posTransactionRepo.deleteById((Object)posData.getSerialNumber());
                    this.posTransactionRepo.flush();
                    yield "Transaction Deleted Successfully";
                }
                case 6 -> "Unable to delete,File Generation Already Completed";
                case 9 -> "File Generation Already In Processing";
                default -> "Invalid Data";
            };
        }
        catch (final Exception e) {
            DeleteTransactionService.log.error("Error::findStatus():", (Throwable)e);
        }
        return responsDesc;
    }
    
    public DeleteTransactionService(final PosTransactionRepository posTransactionRepo, final McAcqTxnWorkRepo mcAcqTxnWorkRepo, final FileUploadRepo fileUploadRepo, final VisaAcqTxnRepo visaAcqTxnWorkRepo, final AmexAcqTxnRepo amexAcqTxnRepo) {
        this.posTransactionRepo = posTransactionRepo;
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.fileUploadRepo = fileUploadRepo;
        this.visaAcqTxnWorkRepo = visaAcqTxnWorkRepo;
        this.amexAcqTxnRepo = amexAcqTxnRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)DeleteTransactionService.class);
    }
}
