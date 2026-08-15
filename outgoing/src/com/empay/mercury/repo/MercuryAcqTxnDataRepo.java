/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.mercury.entities.MercuryAcqTxnDataEntity
 *  com.empay.mercury.repo.MercuryAcqTxnDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.mercury.repo;

import com.empay.mercury.entities.MercuryAcqTxnDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercuryAcqTxnDataRepo
extends JpaRepository<MercuryAcqTxnDataEntity, Integer> {
    public List<MercuryAcqTxnDataEntity> findByInstitutionCodeAndFileID(Integer var1, String var2);
}

