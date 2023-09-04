/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.step.rdbms.entities;

public interface RdbMetadataEntity {
    public String getGuid();

    public String getName();

    public String getTypeName();

    public String getQualifiedName();

    public String getStatus();

    public boolean equals(Object var1);

    public String toString();
}
