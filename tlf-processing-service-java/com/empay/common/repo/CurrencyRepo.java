// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<CurrencyEntity, String>
{
    CurrencyEntity findByCurrencyCode(final String currencyCode);
    
    CurrencyEntity findBycurrencyCodeOrAlphaCode(final String dcc_currency, final String dcc_currency2);
}
