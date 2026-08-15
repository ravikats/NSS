// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import jakarta.transaction.Transactional;
import com.empay.entities.McAcqTxnWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McAcqTxnWorkRepo extends JpaRepository<McAcqTxnWorkEntity, Integer>
{
    McAcqTxnWorkEntity findByRrn(final String rrn);
    
    @Transactional
    void deleteByRrn(final String rrn);
    
    @Transactional
    int deleteByPrjSerNumberAndGeneralStatusIn(final Integer jobNumber, final Object[] status);
}
