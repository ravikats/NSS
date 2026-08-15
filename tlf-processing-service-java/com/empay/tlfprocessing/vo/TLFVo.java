// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TLFVo
{
    @NotNull(message = "fileName must not be null;")
    @NotBlank(message = "fileName must not be empty/blank;")
    @Size(max = 60, message = "fileName should not exceed 60 characters;")
    @Pattern(regexp = "^(TLF).*$", message = "Invalid fileName")
    public String fileName;
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TLFVo)) {
            return false;
        }
        final TLFVo other = (TLFVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        if (this$fileName == null) {
            if (other$fileName == null) {
                return true;
            }
        }
        else if (this$fileName.equals(other$fileName)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof TLFVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "TLFVo(fileName=" + this.getFileName();
    }
}
