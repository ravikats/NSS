/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewMcRejectionDetails
 *  com.empay.repositories.ViewMcRejectionDetailsRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 */
package com.empay.repositories;

import com.empay.entities.ViewMcRejectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ViewMcRejectionDetailsRepo
extends JpaRepository<ViewMcRejectionDetails, String>,
JpaSpecificationExecutor<ViewMcRejectionDetails> {
}

