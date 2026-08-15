// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.VisaIssAccRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaIssAccRangeRepo extends JpaRepository<VisaIssAccRangeEntity, Integer>
{
    VisaIssAccRangeEntity findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqual(final String lowRange, final String highRange);
}

