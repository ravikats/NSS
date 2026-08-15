// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import com.empay.entities.IpmDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpmDetailsViewRepo extends JpaRepository<IpmDetailsView, Integer>
{
    IpmDetailsView findByTxnSerNumber(final Integer txnSerNumber);
}
