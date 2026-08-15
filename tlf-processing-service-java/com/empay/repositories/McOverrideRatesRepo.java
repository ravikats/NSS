// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import com.empay.entities.McOverrideRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McOverrideRatesRepo extends JpaRepository<McOverrideRatesEntity, Integer>
{
    McOverrideRatesEntity findByIrdAndOverRideIDAndCardTypeAndMccAndTxnLimitIndicator(final String ird, final String overRideId, final Character cardtype, final String mcc, final Character limitindicator);
    
    McOverrideRatesEntity findByIrdAndCardTypeAndMccAndTxnLimitIndicator(final String ird, final Character cardtype, final String mcc, final Character limitindicator);
    
    McOverrideRatesEntity findByIrdAndOverRideIDAndMccAndTxnLimitIndicator(final String morIrd, final String overRideId, final String mcc, final Character limitindicator);
}
