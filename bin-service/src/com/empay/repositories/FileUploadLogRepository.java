/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.repositories.FileUploadLogRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.FileUploadLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadLogRepository
extends JpaRepository<FileUploadLogEntity, Integer> {
    public FileUploadLogEntity findByJobNumber(int var1);

    public FileUploadLogEntity findBySerialNumber(Integer var1);

    public Integer countByUploadStatus(int var1);

    public FileUploadLogEntity findByFileName(String var1);

    public void deleteByFileName(String var1);
}

