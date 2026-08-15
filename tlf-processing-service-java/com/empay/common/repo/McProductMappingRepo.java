// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.McProductMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McProductMappingRepo extends JpaRepository<McProductMappingEntity, Integer>
{
    McProductMappingEntity findByIrdAndGcmsProductId(final String ird, final String gcmsProdId);
}
