/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OmanNetBinEntity
 *  com.empay.repositories.OmanNetBinRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.repositories;

import com.empay.entities.OmanNetBinEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmanNetBinRepo
extends JpaRepository<OmanNetBinEntity, Integer> {
    public List<OmanNetBinEntity> findByBinNumberIn(List<String> var1);

    public void deleteAllByJobNumber(Integer var1);
}

