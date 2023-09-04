/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.exception.MetadatahubException
 */
package com.dtb.metadatahub.exception;

import com.dtb.metadatahub.exception.MetadatahubException;

public class RowException
extends MetadatahubException {
    public RowException() {
    }

    public RowException(String message) {
        super(message);
    }

    public RowException(Throwable cause) {
        super(cause);
    }

    public RowException(String message, Throwable cause) {
        super(message, cause);
    }
}
