/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.PosTransactionEntity
 *  com.empay.repositories.PosTransactionRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.PosTransactionEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PosTransactionRepository
extends JpaRepository<PosTransactionEntity, Integer>,
JpaSpecificationExecutor<PosTransactionEntity> {
    public List<PosTransactionEntity> findByRrn(String var1);

    public List<PosTransactionEntity> findByMerchantIdOrTerminalIdOrNetworkOrTxnTypeOrRrnOrGenStatus(String var1, String var2, String var3, String var4, String var5, int var6);

    public List<PosTransactionEntity> findByTxnDateTimeBetween(LocalDateTime var1, LocalDateTime var2);
}

