// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.empay.entities.VisaAcqTxnWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VisaAcqTxnRepo extends JpaRepository<VisaAcqTxnWorkEntity, Integer>
{
    VisaAcqTxnWorkEntity findByRetRefNumber(final String rrn);
    
    @Transactional
    int deleteByPrjSerNumberAndGenStatusIn(final Integer jobNumber, final Object[] genStatus);
}
