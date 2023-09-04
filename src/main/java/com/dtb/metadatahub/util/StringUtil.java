/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.util;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

public class StringUtil {
    public static final boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    public static final boolean isEmpty(StringBuilder string) {
        return string == null || string.length() == 0;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isNumber(String str) {
        try {
            Double.valueOf(str).doubleValue();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isDate(String str) {
        return StringUtil.isDate("yy-mm-dd");
    }

    public static boolean isDate(String str, String mask) {
        try {
            SimpleDateFormat fdate = new SimpleDateFormat("yy-mm-dd");
            fdate.parse(str);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isEquals(String first, String second) {
        if (!StringUtil.isEmpty(first) && StringUtil.isEmpty(second)) {
            return first.equalsIgnoreCase(second);
        }
        return false;
    }

    public static String getNextInternalId(AtomicLong s_nextId) {
        if (s_nextId == null) {
            AtomicLong nextId = new AtomicLong(System.nanoTime());
            return "-" + Long.toString(nextId.getAndIncrement());
        }
        return "-" + Long.toString(s_nextId.getAndIncrement());
    }
}
