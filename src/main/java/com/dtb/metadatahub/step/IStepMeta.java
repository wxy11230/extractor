/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.step;

import com.dtb.metadatahub.step.StepType;

public interface IStepMeta {
    public void setStepName(String var1);

    public String getStepName();

    public void setStepGuid(String var1);

    public String getStepGuid();

    public void setStepType(StepType var1);

    public StepType getStepType();
}
