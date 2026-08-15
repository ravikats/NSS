/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McReprGeneratedEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.staging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="MC_REPR_GENERATED")
public class McReprGeneratedEntity {
    @Id
    @Column(name="MRP_SER_NUMBER")
    private Integer serNumber;
    @Column(name="MRP_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MRP_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="MRP_INS_CODE")
    private int institutionCode;
    @Column(name="MRP_INT_CODE")
    private Integer intCode;
    @Column(name="MRP_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
}

