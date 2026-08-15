// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.McIssAcqRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MCIssAcqRangeRepo extends JpaRepository<McIssAcqRangeEntity, Integer>
{
    McIssAcqRangeEntity findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqualAndActiveCodeOrderByPriorityCodeAscEffectiveDateDesc(final String lowRange, final String highRange, final Character activeCode);
    
    McIssAcqRangeEntity findByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqualAndActiveCodeOrderByPriorityCodeAscEffectiveDateDesc(final String lowRange, final String highRange, final Character activeCode);
}
