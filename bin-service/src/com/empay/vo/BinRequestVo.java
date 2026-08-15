/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.BinRequestVo
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.NotNull
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.empay.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BinRequestVo {
    @NotNull(message="fileName must not be null;")
    @NotBlank(message="fileName must not be empty/blank;")
    @Size(max=60, message="fileName should not exceed 60 characters;")
    private @NotNull(message="fileName must not be null;") @NotBlank(message="fileName must not be empty/blank;") @Size(max=60, message="fileName should not exceed 60 characters;") String fileName;
    @NotNull(message="Network must not be null")
    @Pattern(regexp="^(?i)(MASTERCARD|VISA|JAYWAN|OMANNET|MERCURY)$", message="Invalid network;")
    private @NotNull(message="Network must not be null") @Pattern(regexp="^(?i)(MASTERCARD|VISA|JAYWAN|OMANNET|MERCURY)$", message="Invalid network;") String network;

    public String getFileName() {
        return this.fileName;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BinRequestVo)) {
            return false;
        }
        BinRequestVo other = (BinRequestVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        return !(this$network == null ? other$network != null : !this$network.equals(other$network));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BinRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        return result;
    }

    public String toString() {
        return "BinRequestVo(fileName=" + this.getFileName() + ", network=" + this.getNetwork() + ")";
    }
}

