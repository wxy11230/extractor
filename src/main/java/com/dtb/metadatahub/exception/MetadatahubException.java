/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.constant.Constant
 */
package com.dtb.metadatahub.exception;

import com.dtb.metadatahub.constant.Constant;

public class MetadatahubException
extends Exception {
    public MetadatahubException() {
    }

    public MetadatahubException(String message) {
        super(message);
    }

    public MetadatahubException(Throwable cause) {
        super(cause);
    }

    public MetadatahubException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        String retval = Constant.NEWLINE;
        retval = retval + super.getMessage() + Constant.NEWLINE;
        Throwable cause = this.getCause();
        if (cause != null) {
            String message = cause.getMessage();
            if (message != null) {
                retval = retval + message + Constant.NEWLINE;
            } else {
                StackTraceElement[] ste = cause.getStackTrace();
                for (int i = ste.length - 1; i >= 0; --i) {
                    retval = retval + " at " + ste[i].getClassName() + "." + ste[i].getMethodName() + " (" + ste[i].getFileName() + ":" + ste[i].getLineNumber() + ")" + Constant.NEWLINE;
                }
            }
        }
        return retval;
    }

    public String getSuperMessage() {
        return super.getMessage();
    }
}
