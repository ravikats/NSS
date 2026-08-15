/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewInterfaceEntity
 *  com.empay.repositories.ViewInterfaceRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.ViewInterfaceEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewInterfaceRepository
extends JpaRepository<ViewInterfaceEntity, String> {
    public ViewInterfaceEntity findByFileName(String var1);

    public List<ViewInterfaceEntity> findByBussDate(LocalDate var1);
}

