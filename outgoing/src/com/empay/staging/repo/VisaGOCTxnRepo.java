/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.VisaGOCWorkEntity
 *  com.empay.staging.repo.VisaGOCTxnRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.VisaGOCWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaGOCTxnRepo
extends JpaRepository<VisaGOCWorkEntity, Integer> {
    public List<VisaGOCWorkEntity> findByInstitutionCodeAndGenStatusAndNetwork(Integer var1, int var2, String var3);

    public List<VisaGOCWorkEntity> findByInstitutionCodeAndFileId(Integer var1, String var2);

    public List<VisaGOCWorkEntity> findByArn(String var1);

    public Integer countByInstitutionCodeAndGenStatusAndNetworkAndPurchaseDateLessThanEqual(Integer var1, int var2, String var3, LocalDateTime var4);

    public Integer countByInstitutionCodeAndGenStatusAndNetworkAndPurchaseDateBetween(Integer var1, int var2, String var3, LocalDateTime var4, LocalDateTime var5);

    public List<VisaGOCWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndTxnCodeNotInAndPurchaseDateLessThanEqual(Integer var1, Integer var2, int var3, String var4, List<String> var5, LocalDateTime var6);

    public List<VisaGOCWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndPurchaseDateLessThanEqual(Integer var1, Integer var2, int var3, String var4, LocalDateTime var5);

    public List<VisaGOCWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndTxnCodeNotInAndPurchaseDateBetween(Integer var1, Integer var2, int var3, String var4, List<String> var5, LocalDateTime var6, LocalDateTime var7);

    public List<VisaGOCWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndPurchaseDateBetween(Integer var1, Integer var2, int var3, String var4, LocalDateTime var5, LocalDateTime var6);

    public List<VisaGOCWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatus(Integer var1, Integer var2, int var3);
}

