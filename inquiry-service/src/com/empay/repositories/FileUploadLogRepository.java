/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.repositories.FileUploadLogRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.FileUploadLogEntity;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadLogRepository
extends JpaRepository<FileUploadLogEntity, Integer>,
JpaSpecificationExecutor<FileUploadLogEntity> {
    public FileUploadLogEntity findByFileName(String var1);

    public FileUploadLogEntity findByBusinessDate(Date var1);

    public List<FileUploadLogEntity> findByFileFormats_CodeAndBusinessDate(Integer var1, LocalDate var2);

    public List<FileUploadLogEntity> findByFileFormats_SystemFileFormats_CodeAndBusinessDate(Integer var1, LocalDate var2);

    public List<FileUploadLogEntity> findByFileFormats_SystemFileFormats_CodeInAndBusinessDate(ArrayList<Integer> var1, LocalDate var2);
}

