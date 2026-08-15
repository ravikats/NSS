// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.empay.entities.McRTSTxnDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McRTSTxnDataRepo extends JpaRepository<McRTSTxnDataEntity, Integer>, JpaSpecificationExecutor<McRTSTxnDataEntity>
{
    McRTSTxnDataEntity findByRrn(final String retRefNumber);
    
    McRTSTxnDataEntity findFirstByRrnOrderBySerNumberAsc(final String rrn);
    
    McRTSTxnDataEntity findByRrnAndProcCode(final String retRefNumber, final String procCode);
    
    McRTSTxnDataEntity findByRrnAndProcCodeAndMessageTypeId(final String rrn, final String procCode, final String mti);
}
