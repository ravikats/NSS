// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.visa.repo;

import com.empay.visa.entities.VisaIssAccRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaIssAccRangeRepo extends JpaRepository<VisaIssAccRangeEntity, Integer>
{
    VisaIssAccRangeEntity findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqual(final String lowRange, final String highRange);
}
