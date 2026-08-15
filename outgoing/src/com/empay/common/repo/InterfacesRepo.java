/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.InterfacesEntity
 *  com.empay.common.repo.InterfacesRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.common.repo;

import com.empay.common.entity.InterfacesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfacesRepo
extends JpaRepository<InterfacesEntity, Integer> {
    public InterfacesEntity findByInterfaceCategoryAndInstitutionCode(String var1, Integer var2);

    public InterfacesEntity findByInterfaceCategory(String var1);
}

