/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.IpmOutWorkEntity
 *  com.empay.staging.repo.IpmOutWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.IpmOutWorkEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpmOutWorkRepo
extends JpaRepository<IpmOutWorkEntity, Integer> {
    public List<IpmOutWorkEntity> findByInsCodeAndFileIdOrderBySerialNumberAsc(Integer var1, String var2);
}

