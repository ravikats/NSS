/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.McAcqTxnWorkEntity
 *  com.empay.repositories.McAcqTxnWorkRepository
 *  jakarta.transaction.Transactional
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.McAcqTxnWorkEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface McAcqTxnWorkRepository
extends JpaRepository<McAcqTxnWorkEntity, Integer> {
    @Transactional
    public int deleteByJobNumberAndGenStatusIn(Integer var1, Object[] var2);
}

