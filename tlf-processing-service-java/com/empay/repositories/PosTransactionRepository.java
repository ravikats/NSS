// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.Optional;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.empay.entities.PosTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PosTransactionRepository extends JpaRepository<PosTransactionEntity, Integer>
{
    List<PosTransactionEntity> findByRrnAndTerminalIdAndProcCodeAndMsgTypeIdAndTxnAmount(final String rrn, final String terminalId, final String procCode, final String msgTypeId, final Double txnAmount);
    
    PosTransactionEntity findByRrnAndGenStatusNot(final String rrn, final Integer genStatus);
    
    @Transactional
    void deleteByRrn(final String rrn);
    
    @Transactional
    int deleteByJobNumberAndGenStatusIn(final int jobNumber, final Object[] genStatus);
    
    PosTransactionEntity findBySerialNumberAndRrn(final Integer genStatus, final String rrn);
    
    PosTransactionEntity findByMsgTypeIdAndRrnAndProcCode(final String msgTypeId, final String rrn, final String procCode);
    
    List<PosTransactionEntity> findByJobNumber(final int jobNumber);
    
    List<PosTransactionEntity> findByRrnAndRevIndiCatorNotAndGenStatusNot(final String rrn, final Character revindicator, final Integer genStatus);
    
    List<PosTransactionEntity> findByRrnAndTerminalIdAndMerchantIdAndApprovalCodeAndTxnCodeInAndMsgTypeIdIn(final String rrn, final String terminalId, final String merchantId, final String approvalCode, final List<String> txnCode, final List<String> msgTypeId);
    
    List<PosTransactionEntity> findByRrnAndTerminalIdAndMerchantIdAndApprovalCodeAndTxnCode(final String rrn, final String terminalId, final String merchantId, final String approvalCode, final String txnCode);
    
    Optional<PosTransactionEntity> findBySerialNumber(final Integer serNumber);
    
    PosTransactionEntity findByRrn(final String retRefNumber);
    
    PosTransactionEntity findFirstByMsgTypeIdAndRrnOrderBySerialNumberAsc(final String mti, final String rrn);
    
    PosTransactionEntity findByRrnAndProcCodeAndGenStatusNot(final String rrn, final String procCode, final int status);
    
    PosTransactionEntity findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(final String rrn, final String procCode, final String msgTypeId, final int status);
    
    PosTransactionEntity findByRrnAndProcCodeAndMsgTypeIdAndOutStatusNot(final String rrn, final String procCode, final String msgTypeId, final String status);
    
    PosTransactionEntity findByOriginalRRNAndProcCodeAndMsgTypeIdAndGenStatusNot(final String orgRrn, final String procCode, final String mti, final int status);
}
