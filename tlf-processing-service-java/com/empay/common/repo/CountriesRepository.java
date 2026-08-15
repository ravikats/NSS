// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import org.springframework.stereotype.Repository;
import com.empay.common.entities.CountriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CountriesRepository extends JpaRepository<CountriesEntity, String>
{
    CountriesEntity findByCountryCode(final String countryCode);
    
    CountriesEntity findByCountryAlpha3Code(final String alphaCode);
    
    CountriesEntity findByCountryAlpha2Code(final String alphaCode);
    
    CountriesEntity findByCountryAlpha3CodeOrCountryCode(final String cardAcceptorCountryCode, final String cardAcceptorCountryCode2);
}
