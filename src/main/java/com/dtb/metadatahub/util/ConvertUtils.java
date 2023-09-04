/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.BeanUtils
 */
package com.dtb.metadatahub.util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class ConvertUtils {
    public static <T> List<T> copyList(List<?> sourceList, Class<T> target) {
        ArrayList<T> list = new ArrayList<T>();
        try {
            for (Object bean : sourceList) {
                T t = target.newInstance();
                BeanUtils.copyProperties(bean, t);
                list.add(t);
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
