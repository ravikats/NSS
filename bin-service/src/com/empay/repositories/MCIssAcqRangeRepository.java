/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MCIssAcqRangeEntity
 *  com.empay.repositories.MCIssAcqRangeRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.MCIssAcqRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCIssAcqRangeRepository
extends JpaRepository<MCIssAcqRangeEntity, Integer> {
    public MCIssAcqRangeEntity findByIssRangeLowAndIssRangeHighAndPriorityCode(String var1, String var2, String var3);

    public void deleteAllByJobSerialNumber(Integer var1);
}

