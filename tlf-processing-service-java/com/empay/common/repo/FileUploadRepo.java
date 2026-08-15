// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.FileUploadLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepo extends JpaRepository<FileUploadLogEntity, Integer>
{
    FileUploadLogEntity findByFileName(final String fileName);
    
    FileUploadLogEntity findByInstitutionCodeAndUploadStatusIn(final Integer insCode, final int[] status);
    
    FileUploadLogEntity findBySerialNumber(final Integer uploadSerNumber);
    
    FileUploadLogEntity findByJobNumber(final int jobnumber);
}
