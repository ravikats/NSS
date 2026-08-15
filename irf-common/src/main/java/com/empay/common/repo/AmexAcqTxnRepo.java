// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.empay.common.entities.AmexAcqTxnWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AmexAcqTxnRepo extends JpaRepository<AmexAcqTxnWorkEntity, Integer>
{
    @Transactional
    int deleteByPrjSerNumberAndGenStatusIn(final Integer jobNumber, final Object[] genStatus);
    
    AmexAcqTxnWorkEntity findByRrn(final String retRefNumber);
}

