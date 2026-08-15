// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.entities.CurrencyRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRatesRepo extends JpaRepository<CurrencyRatesEntity, Integer>
{
    CurrencyRatesEntity findTopByInsCodeAndSourceCurCodeOrderByCrtCodeDesc(final Integer insCode, final String srcCurrCode);
}
