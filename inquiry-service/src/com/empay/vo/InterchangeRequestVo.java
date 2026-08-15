/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.InterchangeRequestVo
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.empay.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InterchangeRequestVo {
    @Pattern(regexp="^(?i)(Special|General)$", message="Invalid segment; Please provide valid segment.")
    private @Pattern(regexp="^(?i)(Special|General)$", message="Invalid segment; Please provide valid segment.") String segment;
    private String segmentDesc;
    @Size(min=4, max=4, message="Invalid mcc length.")
    private @Size(min=4, max=4, message="Invalid mcc length.") String mcc;
    private String mccDescription;

    public String getSegment() {
        return this.segment;
    }

    public String getSegmentDesc() {
        return this.segmentDesc;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getMccDescription() {
        return this.mccDescription;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setSegmentDesc(String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InterchangeRequestVo)) {
            return false;
        }
        InterchangeRequestVo other = (InterchangeRequestVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$segment = this.getSegment();
        String other$segment = other.getSegment();
        if (this$segment == null ? other$segment != null : !this$segment.equals(other$segment)) {
            return false;
        }
        String this$segmentDesc = this.getSegmentDesc();
        String other$segmentDesc = other.getSegmentDesc();
        if (this$segmentDesc == null ? other$segmentDesc != null : !this$segmentDesc.equals(other$segmentDesc)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$mccDescription = this.getMccDescription();
        String other$mccDescription = other.getMccDescription();
        return !(this$mccDescription == null ? other$mccDescription != null : !this$mccDescription.equals(other$mccDescription));
    }

    protected boolean canEqual(Object other) {
        return other instanceof InterchangeRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $segment = this.getSegment();
        result = result * 59 + ($segment == null ? 43 : $segment.hashCode());
        String $segmentDesc = this.getSegmentDesc();
        result = result * 59 + ($segmentDesc == null ? 43 : $segmentDesc.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $mccDescription = this.getMccDescription();
        result = result * 59 + ($mccDescription == null ? 43 : $mccDescription.hashCode());
        return result;
    }

    public String toString() {
        return "InterchangeRequestVo(segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDescription=" + this.getMccDescription() + ")";
    }
}

