/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnDataEntity
 *  com.empay.amex.repo.AmexTxnDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.amex.repo;

import com.empay.amex.entities.AmexAcqTxnDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmexTxnDataRepo
extends JpaRepository<AmexAcqTxnDataEntity, Integer> {
    public List<AmexAcqTxnDataEntity> findByInstitutionCodeAndFileId(Integer var1, String var2);
}

