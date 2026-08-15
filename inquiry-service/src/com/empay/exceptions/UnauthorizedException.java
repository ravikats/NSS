/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.UnauthorizedException
 */
package com.empay.exceptions;

public class UnauthorizedException
extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public UnauthorizedException() {
    }
}

