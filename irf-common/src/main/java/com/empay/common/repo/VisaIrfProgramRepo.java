// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.VisaIrfProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaIrfProgramRepo extends JpaRepository<VisaIrfProgramEntity, Integer>
{
    VisaIrfProgramEntity findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(final Character region, final Character cardType, final String fpType, final String fpValue, final Character txnLimitIndicator, final Character qualifierIndicator);
    
    VisaIrfProgramEntity findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(final Character region, final Character cardType, final String fpType, final Character txnLimitIndicator);
    
    VisaIrfProgramEntity findByRegionAndCardTypeAndFpTypeAndMccAndTxnLimitIndicator(final Character region, final Character cardType, final String fpType, final String mcc, final Character txnLimitIndicator);
    
    VisaIrfProgramEntity findByRegionAndCardTypeAndFpTypeAndMccAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(final Character region, final Character cardType, final String fpType, final String mcc, final String fpValue, final Character txnLimitIndicator, final Character qualifierIndicator);
}

