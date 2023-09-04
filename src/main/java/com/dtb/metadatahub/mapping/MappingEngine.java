/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 */
package com.dtb.metadatahub.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtb.metadatahub.step.MetaDataEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveColumnEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveDatabaseEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveTableEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveViewEntity;
import com.dtb.metadatahub.step.mpp.entities.MppColumnEntity;
import com.dtb.metadatahub.step.mpp.entities.MppDatabaseEntity;
import com.dtb.metadatahub.step.mpp.entities.MppTableEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbColumnEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbDatabaseEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbTableEntity;
import com.dtb.metadatahub.util.SnowFlakeUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MappingEngine {
    public static String mappingForObjectInstance(String mappingConfig, List<?> resultObj) throws Exception {
        ArrayList resultList = new ArrayList();
        JSONObject configJson = JSON.parseObject((String)mappingConfig);
        String className = configJson.getString("className");
        JSONObject mappingJson = configJson.getJSONObject("config");
        Class<?> targetClass = Class.forName(className);
        for (Object currentObj : resultObj) {
            Method[] methods;
            boolean flag = false;
            Class<?> primaryKeyType = null;
            Object obj = targetClass.newInstance();
            for (Method method : methods = targetClass.getMethods()) {
                if (!"setId".equals(method.getName())) continue;
                primaryKeyType = method.getParameters()[0].getType();
                flag = true;
                break;
            }
            if (flag) {
                targetClass.getMethod("setId", primaryKeyType).invoke(obj, SnowFlakeUtil.getInstance().nextId());
            }
            Object currentRowObj = currentObj;
            MetaDataEntity baseEntity = MappingEngine.typeConversion(currentRowObj);
            for (Map.Entry entry : mappingJson.entrySet()) {
                String sourceAttribute = entry.getKey().toString();
                Field sourceField = baseEntity.getClass().getDeclaredField(sourceAttribute);
                sourceField.setAccessible(true);
                String targetAttribute = entry.getValue().toString();
                String methodName = "set" + targetAttribute.substring(0, 1).toUpperCase() + targetAttribute.substring(1, targetAttribute.length());
                Method currentMethod = targetClass.getMethod(methodName, sourceField.getType());
                if (!Objects.nonNull(currentMethod)) continue;
                currentMethod.invoke(obj, sourceField.get(baseEntity));
            }
            resultList.add(obj);
        }
        return JSON.toJSONString(resultList);
    }

    private static MetaDataEntity typeConversion(Object obj) {
        MetaDataEntity baseEntity = null;
        if (obj instanceof AtlasHiveDatabaseEntity) {
            baseEntity = (AtlasHiveDatabaseEntity)obj;
        }
        if (obj instanceof AtlasHiveTableEntity) {
            baseEntity = (AtlasHiveTableEntity)obj;
        }
        if (obj instanceof AtlasHiveColumnEntity) {
            baseEntity = (AtlasHiveColumnEntity)obj;
        }
        if (obj instanceof AtlasHiveViewEntity) {
            baseEntity = (AtlasHiveViewEntity)obj;
        }
        if (obj instanceof RdbColumnEntity) {
            baseEntity = (RdbColumnEntity)obj;
        }
        if (obj instanceof RdbDatabaseEntity) {
            baseEntity = (RdbDatabaseEntity)obj;
        }
        if (obj instanceof RdbTableEntity) {
            baseEntity = (RdbTableEntity)obj;
        }
        if (obj instanceof MppColumnEntity) {
            baseEntity = (MppColumnEntity)obj;
        }
        if (obj instanceof MppDatabaseEntity) {
            baseEntity = (MppDatabaseEntity)obj;
        }
        if (obj instanceof MppTableEntity) {
            baseEntity = (MppTableEntity)obj;
        }
        return baseEntity;
    }
}
