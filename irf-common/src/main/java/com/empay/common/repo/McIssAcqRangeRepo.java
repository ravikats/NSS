// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import org.springframework.stereotype.Repository;
import com.empay.common.entities.McIssAcqRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface McIssAcqRangeRepo extends JpaRepository<McIssAcqRangeEntity, Integer>
{
    McIssAcqRangeEntity findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqualAndActiveCodeOrderByPriorityCodeAscEffectiveDateDesc(final String lowRange, final String highRange, final Character activeCode);
}

