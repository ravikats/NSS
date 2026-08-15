/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.VisaAcqTxnWorkEntity
 *  com.empay.staging.repo.VisaAcqTxnWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.VisaAcqTxnWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaAcqTxnWorkRepo
extends JpaRepository<VisaAcqTxnWorkEntity, Integer> {
    public Integer countByInstitutionCodeAndGenStatusAndPurchaseDateBetween(Integer var1, Integer var2, LocalDateTime var3, LocalDateTime var4);

    public Integer countByInstitutionCodeAndGenStatusAndPurchaseDateLessThanEqual(Integer var1, Integer var2, LocalDateTime var3);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndPurchaseDateBetween(Integer var1, Integer var2, Integer var3, LocalDateTime var4, LocalDateTime var5);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndPurchaseDateLessThanEqual(Integer var1, Integer var2, Integer var3, LocalDateTime var4);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndTxnCodeInAndPurchaseDateBetween(Integer var1, Integer var2, Integer var3, List<String> var4, LocalDateTime var5, LocalDateTime var6);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndTxnCodeInAndPurchaseDateLessThanEqual(Integer var1, Integer var2, Integer var3, List<String> var4, LocalDateTime var5);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndTxnCodeNotInAndPurchaseDateBetween(Integer var1, Integer var2, Integer var3, List<String> var4, LocalDateTime var5, LocalDateTime var6);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndTxnCodeNotInAndPurchaseDateLessThanEqual(Integer var1, Integer var2, Integer var3, List<String> var4, LocalDateTime var5);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndGenStatus(Integer var1, Integer var2);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndFileId(Integer var1, String var2);

    public List<VisaAcqTxnWorkEntity> findByArn(String var1);

    public List<VisaAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatus(Integer var1, Integer var2, int var3);
}

