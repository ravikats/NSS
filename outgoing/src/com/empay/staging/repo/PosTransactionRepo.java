/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.PosTransactionEntity
 *  com.empay.staging.repo.PosTransactionRepo
 *  jakarta.transaction.Transactional
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Modifying
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.PosTransactionEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PosTransactionRepo
extends JpaRepository<PosTransactionEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6, posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN (select mcAcqTxnWork.rrn from McAcqTxnWorkEntity mcAcqTxnWork where mcAcqTxnWork.generalStatus = 4 ) AND  posTransactions.network IN ('MCI','MDS') AND posTransactions.genStatus = 4 AND posTransactions.insCode =:insCode")
    public void completePosStatus(@Param(value="insCode") int var1);

    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6 , posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN (select visaAcqTxnWork.retRefNumber from VisaAcqTxnWorkEntity visaAcqTxnWork  where visaAcqTxnWork.genStatus = 4 ) And posTransactions.network ='VISA' AND posTransactions.genStatus = 4 AND posTransactions.insCode =:insCode ")
    public void completeVISAPosStatus(@Param(value="insCode") int var1);

    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6 , posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN  (select jaywanAcqTxnWork.rrn from JaywanAcqTxnWorkEntity jaywanAcqTxnWork  where jaywanAcqTxnWork.genStatus = 4 ) AND posTransactions.scheme ='JAYWAN' AND  posTransactions.genStatus = 4  AND posTransactions.insCode =:institutionCode")
    public void completeJaywanPosStatus(int var1);

    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6 , posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN  (select amexAcqTxnWork.rrn from AmexAcqTxnWorkEntity amexAcqTxnWork  where amexAcqTxnWork.genStatus = 4 ) AND posTransactions.scheme ='AMEX' AND  posTransactions.genStatus = 4  AND posTransactions.insCode =:institutionCode")
    public void completeAmexPosStatus(int var1);

    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6, posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN (select mcGCOTxnWorkEntity.rrn from McGCOTxnWorkEntity mcGCOTxnWorkEntity where mcGCOTxnWorkEntity.generalStatus = 4 ) AND  posTransactions.network IN ('OMANNET','UAESWITCH') AND posTransactions.genStatus = 4 AND posTransactions.insCode =:insCode")
    public void completeGcoPosStatus(@Param(value="insCode") int var1);

    public List<PosTransactionEntity> findBySerialNumberIn(List<Integer> var1);

    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6 , posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN (select visaGOCWork.retRefNumber from VisaGOCWorkEntity visaGOCWork  where visaGOCWork.genStatus = 4 ) And posTransactions.scheme ='VISA' AND posTransactions.network IN ('OMANNET','UAESWITCH') AND posTransactions.genStatus = 4 AND posTransactions.insCode =:institutionCode ")
    public void completeVISAGocPosStatus(@Param(value="institutionCode") int var1);

    @Modifying
    @Transactional
    @Query(value="update PosTransactionEntity posTransactions SET posTransactions.genStatus = 6, posTransactions.outStatus = 'Completed'  where posTransactions.rrn IN (select mercuryAcqTxnWork.rrn from MercuryAcqTxnWorkEntity mercuryAcqTxnWork where mercuryAcqTxnWork.generalStatus = 4 ) AND  posTransactions.network IN ('MERCURY') AND posTransactions.genStatus = 4 AND posTransactions.insCode =:insCode")
    public void completeMercuryPosStatus(@Param(value="insCode") int var1);
}

