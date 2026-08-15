/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.VisaAcqTxnDataEntity
 *  com.empay.staging.repo.VisaAcqTxnDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.VisaAcqTxnDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaAcqTxnDataRepo
extends JpaRepository<VisaAcqTxnDataEntity, Integer> {
    public List<VisaAcqTxnDataEntity> findByInstitutionCodeAndFileId(Integer var1, String var2);
}

