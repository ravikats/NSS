/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.JaywanIssAccRangeEntity
 *  com.empay.repositories.JaywanMcIssAccRangeRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.JaywanIssAccRangeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaywanMcIssAccRangeRepository
extends JpaRepository<JaywanIssAccRangeEntity, Integer> {
    public void deleteAllByJobNumber(Integer var1);

    public List<JaywanIssAccRangeEntity> findByBinRangeLowAndBinRangeHigh(Long var1, Long var2);
}

