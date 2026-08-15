/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileFormatsEntity
 *  com.empay.repositories.FileFormatRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.FileFormatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileFormatRepository
extends JpaRepository<FileFormatsEntity, Integer>,
JpaSpecificationExecutor<FileFormatsEntity> {
    public FileFormatsEntity findBySystemFileFormats_Code(Integer var1);
}

