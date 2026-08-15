// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.exceptions;

public class UnauthorizedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private String errorMessage;
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public UnauthorizedException(final String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    
    public UnauthorizedException() {
    }
}
