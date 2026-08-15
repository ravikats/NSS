// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import org.springframework.stereotype.Repository;
import com.empay.common.entities.IpmDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IpmDetailsViewRepo extends JpaRepository<IpmDetailsView, Integer>
{
    IpmDetailsView findByTxnSerNumber(final Integer txnSerNumber);
}

