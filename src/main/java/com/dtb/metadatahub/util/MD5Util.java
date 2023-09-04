/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final String[] strDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteTostring(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; ++i) {
            sBuffer.append(MD5Util.byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String getMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = MD5Util.byteTostring(md.digest(strObj.getBytes())).substring(0, 16);
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b = md.digest();
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getMD5Code("172.16.0.11:15400@test_db1.public.warehouse_t1.w_suite_number"));
        System.out.println(MD5Util.getMD5("172.16.0.11:15400@test_db1.public.warehouse_t1.w_suite_number"));
    }
}
