/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McAcqTxnWorkEntity
 *  com.empay.staging.repo.McAcqTxnWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.McAcqTxnWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface McAcqTxnWorkRepo
extends JpaRepository<McAcqTxnWorkEntity, Long> {
    public List<McAcqTxnWorkEntity> findByInstitutionCodeAndGeneralStatus(Integer var1, Integer var2);

    public List<McAcqTxnWorkEntity> findByInstitutionCodeAndGeneralStatusAndLocalDateTimeBetween(Integer var1, Integer var2, LocalDateTime var3, LocalDateTime var4);

    public List<McAcqTxnWorkEntity> findByInstitutionCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(Integer var1, Integer var2, LocalDateTime var3);

    public Integer countByInstitutionCodeAndGeneralStatus(int var1, int var2);

    public Integer countByInstitutionCodeAndGeneralStatusAndLocalDateTimeBetween(int var1, int var2, LocalDateTime var3, LocalDateTime var4);

    public Integer countByInstitutionCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(int var1, int var2, LocalDateTime var3);

    public List<McAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGeneralStatus(Integer var1, Integer var2, int var3);

    public List<McAcqTxnWorkEntity> findByRrnAndMessageTypeIdAndProcCode(String var1, String var2, String var3);
}

