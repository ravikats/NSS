/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewTxnInquiryDetails
 *  com.empay.repositories.ViewTxnInquiryDetailsRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 */
package com.empay.repositories;

import com.empay.entities.ViewTxnInquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ViewTxnInquiryDetailsRepo
extends JpaRepository<ViewTxnInquiryDetails, String>,
JpaSpecificationExecutor<ViewTxnInquiryDetails> {
}

