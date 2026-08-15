/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.FileFormatsEntity
 *  com.empay.common.repo.FileFormatsRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.common.repo;

import com.empay.common.entity.FileFormatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileFormatsRepo
extends JpaRepository<FileFormatsEntity, Integer> {
    public FileFormatsEntity findBySystemCodeAndType(Integer var1, Character var2);

    public FileFormatsEntity findBySystemCodeAndTypeAndInstitutionCode(Integer var1, Character var2, Integer var3);
}

