/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  com.dtb.metadatahub.entity.MppColumnDO
 *  com.dtb.metadatahub.entity.MppDatabaseDO
 *  com.dtb.metadatahub.entity.MppTableDO
 *  com.dtb.metadatahub.mapping.MappingEngine
 *  com.dtb.metadatahub.step.IStep
 *  com.dtb.metadatahub.step.mpp.MppMetadataProxy
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.mpp;

import com.alibaba.fastjson.JSONObject;
import com.dtb.metadatahub.entity.MppColumnDO;
import com.dtb.metadatahub.entity.MppDatabaseDO;
import com.dtb.metadatahub.entity.MppTableDO;
import com.dtb.metadatahub.mapping.MappingEngine;
import com.dtb.metadatahub.step.IStep;
import com.dtb.metadatahub.step.mpp.MppMetadataProxy;
import com.dtb.metadatahub.step.mpp.MppStepMeta;
import com.dtb.metadatahub.step.mpp.entities.MppColumnEntity;
import com.dtb.metadatahub.step.mpp.entities.MppDatabaseEntity;
import com.dtb.metadatahub.step.mpp.entities.MppTableEntity;
import com.dtb.metadatahub.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MppMetadataStep
implements IStep {
    private static final Logger log = LoggerFactory.getLogger(MppMetadataStep.class);
    private MppMetadataProxy metadataProxy;
    private MppStepMeta stepMeta;
    private String databaseMappingConfig;
    private String tableMappingConfig;
    private String columnMappingConfig;
    private String viewMappingConfig;
    private boolean successFlag;
    private List<String> databaseNameList = new ArrayList<String>();
    private List<String> tableNameList = new ArrayList<String>();
    private List<String> ignoreDatabaseNameList = new ArrayList<String>();
    private List<MppDatabaseDO> databaseDOList = new ArrayList<MppDatabaseDO>();
    private List<MppTableDO> tableDOList = new ArrayList<MppTableDO>();
    private List<MppColumnDO> columnDOList = new ArrayList<MppColumnDO>();
    private List<MppDatabaseEntity> databaseEntityList = new ArrayList<MppDatabaseEntity>();
    private List<MppTableEntity> tableEntityList = new ArrayList<MppTableEntity>();
    private List<MppColumnEntity> columnEntityList = new ArrayList<MppColumnEntity>();

    public MppMetadataStep(MppStepMeta stepMeta) {
        this.stepMeta = stepMeta;
        this.metadataProxy = new MppMetadataProxy(stepMeta);
        this.transVariable(this.databaseNameList, stepMeta.getExtratorDatabaseNames());
        this.transVariable(this.tableNameList, stepMeta.getExtratorTableNames());
        this.successFlag = false;
    }

    public boolean getSuccessFlag() {
        return this.successFlag;
    }

    public void getMetadata() {
        try {
            String resultJsonStr;
            if (this.tableNameList.size() > 0) {
                log.info("Gets the specified tables metadata, the table num are {}", (Object)this.tableNameList.size());
                this.getMppTableMetadataByNames(this.tableNameList);
            } else if (this.databaseNameList.size() > 0) {
                log.info("Gets the specified database metadata, the database num are {}", (Object)this.databaseNameList.size());
                this.getMppDatabaseMetadataByNames(this.databaseNameList);
            } else {
                log.info("Gets all the database metadata for that user:{}", (Object)this.stepMeta.getUserName());
                this.databaseNameList = this.metadataProxy.getDatabaseList();
                System.out.println("\u7528\u6237\u4e0b\u6240\u6709\u7684\u5e93: " + this.databaseNameList);
                this.ignoreDatabaseName(this.databaseNameList, this.ignoreDatabaseNameList);
                System.out.println("\u8fc7\u6ee4\u540e\u6240\u6709\u7684\u5e93: " + this.databaseNameList);
                this.getMppDatabaseMetadataByNames(this.databaseNameList);
            }
            log.info("Start the mapping conversion....");
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{this.databaseMappingConfig})) {
                resultJsonStr = MappingEngine.mappingForObjectInstance((String)this.databaseMappingConfig, this.databaseEntityList);
                this.databaseDOList = JSONObject.parseArray((String)resultJsonStr, MppDatabaseDO.class);
            }
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{this.tableMappingConfig})) {
                resultJsonStr = MappingEngine.mappingForObjectInstance((String)this.tableMappingConfig, this.tableEntityList);
                this.tableDOList = JSONObject.parseArray((String)resultJsonStr, MppTableDO.class);
            }
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{this.columnMappingConfig})) {
                resultJsonStr = MappingEngine.mappingForObjectInstance((String)this.columnMappingConfig, this.columnEntityList);
                this.columnDOList = JSONObject.parseArray((String)resultJsonStr, MppColumnDO.class);
            }
            log.info("End the mapping conversion....");
            this.successFlag = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.successFlag = false;
            log.error("An exception occurred while collecting altas metadata. The reason is: {}", (Object)e.getMessage());
        }
    }

    private void getMppTableMetadataByNames(List<String> tableList) throws Exception {
        for (String tableName : tableList) {
            this.metadataProxy.getTableMetadata(tableName);
            this.tableEntityList.addAll(this.metadataProxy.getTableEntityList());
            this.columnEntityList.addAll(this.metadataProxy.getColumnEntityList());
        }
    }

    private void getMppDatabaseMetadataByNames(List<String> databaseList) {
        for (String databaseName : databaseList) {
            this.metadataProxy.getDatabaseMetadata(databaseName);
            this.databaseEntityList.addAll(this.metadataProxy.getDatabaseEntityList());
            this.tableEntityList.addAll(this.metadataProxy.getTableEntityList());
            this.columnEntityList.addAll(this.metadataProxy.getColumnEntityList());
        }
    }

    private void transVariable(List<String> list, String strs) {
        try {
            if (!StringUtil.isEmpty(strs)) {
                String[] str1 = strs.trim().split("\\,");
                if (str1.length == 0) {
                    list.add(strs.trim());
                } else {
                    for (String str : str1) {
                        if (StringUtil.isEmpty(str)) continue;
                        list.add(str.trim());
                    }
                }
            }
        }
        catch (Exception e) {
            log.error("Trans the variable error", (Throwable)e);
        }
    }

    private void ignoreDatabaseName(List<String> inputDatabaseNameList, List<String> ignoreDatabaseNameList) {
        for (String ignoreDatabaseName : ignoreDatabaseNameList) {
            if (!inputDatabaseNameList.contains(ignoreDatabaseName)) continue;
            inputDatabaseNameList.remove(ignoreDatabaseName);
        }
    }

    public MppMetadataProxy getMetadataProxy() {
        return this.metadataProxy;
    }

    public MppStepMeta getStepMeta() {
        return this.stepMeta;
    }

    public String getDatabaseMappingConfig() {
        return this.databaseMappingConfig;
    }

    public String getTableMappingConfig() {
        return this.tableMappingConfig;
    }

    public String getColumnMappingConfig() {
        return this.columnMappingConfig;
    }

    public String getViewMappingConfig() {
        return this.viewMappingConfig;
    }

    public List<String> getDatabaseNameList() {
        return this.databaseNameList;
    }

    public List<String> getTableNameList() {
        return this.tableNameList;
    }

    public List<String> getIgnoreDatabaseNameList() {
        return this.ignoreDatabaseNameList;
    }

    public List<MppDatabaseDO> getDatabaseDOList() {
        return this.databaseDOList;
    }

    public List<MppTableDO> getTableDOList() {
        return this.tableDOList;
    }

    public List<MppColumnDO> getColumnDOList() {
        return this.columnDOList;
    }

    public List<MppDatabaseEntity> getDatabaseEntityList() {
        return this.databaseEntityList;
    }

    public List<MppTableEntity> getTableEntityList() {
        return this.tableEntityList;
    }

    public List<MppColumnEntity> getColumnEntityList() {
        return this.columnEntityList;
    }

    public void setMetadataProxy(MppMetadataProxy metadataProxy) {
        this.metadataProxy = metadataProxy;
    }

    public void setStepMeta(MppStepMeta stepMeta) {
        this.stepMeta = stepMeta;
    }

    public void setDatabaseMappingConfig(String databaseMappingConfig) {
        this.databaseMappingConfig = databaseMappingConfig;
    }

    public void setTableMappingConfig(String tableMappingConfig) {
        this.tableMappingConfig = tableMappingConfig;
    }

    public void setColumnMappingConfig(String columnMappingConfig) {
        this.columnMappingConfig = columnMappingConfig;
    }

    public void setViewMappingConfig(String viewMappingConfig) {
        this.viewMappingConfig = viewMappingConfig;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public void setDatabaseNameList(List<String> databaseNameList) {
        this.databaseNameList = databaseNameList;
    }

    public void setTableNameList(List<String> tableNameList) {
        this.tableNameList = tableNameList;
    }

    public void setIgnoreDatabaseNameList(List<String> ignoreDatabaseNameList) {
        this.ignoreDatabaseNameList = ignoreDatabaseNameList;
    }

    public void setDatabaseDOList(List<MppDatabaseDO> databaseDOList) {
        this.databaseDOList = databaseDOList;
    }

    public void setTableDOList(List<MppTableDO> tableDOList) {
        this.tableDOList = tableDOList;
    }

    public void setColumnDOList(List<MppColumnDO> columnDOList) {
        this.columnDOList = columnDOList;
    }

    public void setDatabaseEntityList(List<MppDatabaseEntity> databaseEntityList) {
        this.databaseEntityList = databaseEntityList;
    }

    public void setTableEntityList(List<MppTableEntity> tableEntityList) {
        this.tableEntityList = tableEntityList;
    }

    public void setColumnEntityList(List<MppColumnEntity> columnEntityList) {
        this.columnEntityList = columnEntityList;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MppMetadataStep)) {
            return false;
        }
        MppMetadataStep other = (MppMetadataStep)o;
        if (!other.canEqual(this)) {
            return false;
        }
        MppMetadataProxy this$metadataProxy = this.getMetadataProxy();
        MppMetadataProxy other$metadataProxy = other.getMetadataProxy();
        if (this$metadataProxy == null ? other$metadataProxy != null : !this$metadataProxy.equals(other$metadataProxy)) {
            return false;
        }
        MppStepMeta this$stepMeta = this.getStepMeta();
        MppStepMeta other$stepMeta = other.getStepMeta();
        if (this$stepMeta == null ? other$stepMeta != null : !((Object)this$stepMeta).equals(other$stepMeta)) {
            return false;
        }
        String this$databaseMappingConfig = this.getDatabaseMappingConfig();
        String other$databaseMappingConfig = other.getDatabaseMappingConfig();
        if (this$databaseMappingConfig == null ? other$databaseMappingConfig != null : !this$databaseMappingConfig.equals(other$databaseMappingConfig)) {
            return false;
        }
        String this$tableMappingConfig = this.getTableMappingConfig();
        String other$tableMappingConfig = other.getTableMappingConfig();
        if (this$tableMappingConfig == null ? other$tableMappingConfig != null : !this$tableMappingConfig.equals(other$tableMappingConfig)) {
            return false;
        }
        String this$columnMappingConfig = this.getColumnMappingConfig();
        String other$columnMappingConfig = other.getColumnMappingConfig();
        if (this$columnMappingConfig == null ? other$columnMappingConfig != null : !this$columnMappingConfig.equals(other$columnMappingConfig)) {
            return false;
        }
        String this$viewMappingConfig = this.getViewMappingConfig();
        String other$viewMappingConfig = other.getViewMappingConfig();
        if (this$viewMappingConfig == null ? other$viewMappingConfig != null : !this$viewMappingConfig.equals(other$viewMappingConfig)) {
            return false;
        }
        if (this.getSuccessFlag() != other.getSuccessFlag()) {
            return false;
        }
        List<String> this$databaseNameList = this.getDatabaseNameList();
        List<String> other$databaseNameList = other.getDatabaseNameList();
        if (this$databaseNameList == null ? other$databaseNameList != null : !((Object)this$databaseNameList).equals(other$databaseNameList)) {
            return false;
        }
        List<String> this$tableNameList = this.getTableNameList();
        List<String> other$tableNameList = other.getTableNameList();
        if (this$tableNameList == null ? other$tableNameList != null : !((Object)this$tableNameList).equals(other$tableNameList)) {
            return false;
        }
        List<String> this$ignoreDatabaseNameList = this.getIgnoreDatabaseNameList();
        List<String> other$ignoreDatabaseNameList = other.getIgnoreDatabaseNameList();
        if (this$ignoreDatabaseNameList == null ? other$ignoreDatabaseNameList != null : !((Object)this$ignoreDatabaseNameList).equals(other$ignoreDatabaseNameList)) {
            return false;
        }
        List<MppDatabaseDO> this$databaseDOList = this.getDatabaseDOList();
        List<MppDatabaseDO> other$databaseDOList = other.getDatabaseDOList();
        if (this$databaseDOList == null ? other$databaseDOList != null : !((Object)this$databaseDOList).equals(other$databaseDOList)) {
            return false;
        }
        List<MppTableDO> this$tableDOList = this.getTableDOList();
        List<MppTableDO> other$tableDOList = other.getTableDOList();
        if (this$tableDOList == null ? other$tableDOList != null : !((Object)this$tableDOList).equals(other$tableDOList)) {
            return false;
        }
        List<MppColumnDO> this$columnDOList = this.getColumnDOList();
        List<MppColumnDO> other$columnDOList = other.getColumnDOList();
        if (this$columnDOList == null ? other$columnDOList != null : !((Object)this$columnDOList).equals(other$columnDOList)) {
            return false;
        }
        List<MppDatabaseEntity> this$databaseEntityList = this.getDatabaseEntityList();
        List<MppDatabaseEntity> other$databaseEntityList = other.getDatabaseEntityList();
        if (this$databaseEntityList == null ? other$databaseEntityList != null : !((Object)this$databaseEntityList).equals(other$databaseEntityList)) {
            return false;
        }
        List<MppTableEntity> this$tableEntityList = this.getTableEntityList();
        List<MppTableEntity> other$tableEntityList = other.getTableEntityList();
        if (this$tableEntityList == null ? other$tableEntityList != null : !((Object)this$tableEntityList).equals(other$tableEntityList)) {
            return false;
        }
        List<MppColumnEntity> this$columnEntityList = this.getColumnEntityList();
        List<MppColumnEntity> other$columnEntityList = other.getColumnEntityList();
        return !(this$columnEntityList == null ? other$columnEntityList != null : !((Object)this$columnEntityList).equals(other$columnEntityList));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MppMetadataStep;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        MppMetadataProxy $metadataProxy = this.getMetadataProxy();
        result = result * 59 + ($metadataProxy == null ? 43 : $metadataProxy.hashCode());
        MppStepMeta $stepMeta = this.getStepMeta();
        result = result * 59 + ($stepMeta == null ? 43 : ((Object)$stepMeta).hashCode());
        String $databaseMappingConfig = this.getDatabaseMappingConfig();
        result = result * 59 + ($databaseMappingConfig == null ? 43 : $databaseMappingConfig.hashCode());
        String $tableMappingConfig = this.getTableMappingConfig();
        result = result * 59 + ($tableMappingConfig == null ? 43 : $tableMappingConfig.hashCode());
        String $columnMappingConfig = this.getColumnMappingConfig();
        result = result * 59 + ($columnMappingConfig == null ? 43 : $columnMappingConfig.hashCode());
        String $viewMappingConfig = this.getViewMappingConfig();
        result = result * 59 + ($viewMappingConfig == null ? 43 : $viewMappingConfig.hashCode());
        result = result * 59 + (this.getSuccessFlag() ? 79 : 97);
        List<String> $databaseNameList = this.getDatabaseNameList();
        result = result * 59 + ($databaseNameList == null ? 43 : ((Object)$databaseNameList).hashCode());
        List<String> $tableNameList = this.getTableNameList();
        result = result * 59 + ($tableNameList == null ? 43 : ((Object)$tableNameList).hashCode());
        List<String> $ignoreDatabaseNameList = this.getIgnoreDatabaseNameList();
        result = result * 59 + ($ignoreDatabaseNameList == null ? 43 : ((Object)$ignoreDatabaseNameList).hashCode());
        List<MppDatabaseDO> $databaseDOList = this.getDatabaseDOList();
        result = result * 59 + ($databaseDOList == null ? 43 : ((Object)$databaseDOList).hashCode());
        List<MppTableDO> $tableDOList = this.getTableDOList();
        result = result * 59 + ($tableDOList == null ? 43 : ((Object)$tableDOList).hashCode());
        List<MppColumnDO> $columnDOList = this.getColumnDOList();
        result = result * 59 + ($columnDOList == null ? 43 : ((Object)$columnDOList).hashCode());
        List<MppDatabaseEntity> $databaseEntityList = this.getDatabaseEntityList();
        result = result * 59 + ($databaseEntityList == null ? 43 : ((Object)$databaseEntityList).hashCode());
        List<MppTableEntity> $tableEntityList = this.getTableEntityList();
        result = result * 59 + ($tableEntityList == null ? 43 : ((Object)$tableEntityList).hashCode());
        List<MppColumnEntity> $columnEntityList = this.getColumnEntityList();
        result = result * 59 + ($columnEntityList == null ? 43 : ((Object)$columnEntityList).hashCode());
        return result;
    }

    public String toString() {
        return "MppMetadataStep(metadataProxy=" + this.getMetadataProxy() + ", stepMeta=" + this.getStepMeta() + ", databaseMappingConfig=" + this.getDatabaseMappingConfig() + ", tableMappingConfig=" + this.getTableMappingConfig() + ", columnMappingConfig=" + this.getColumnMappingConfig() + ", viewMappingConfig=" + this.getViewMappingConfig() + ", successFlag=" + this.getSuccessFlag() + ", databaseNameList=" + this.getDatabaseNameList() + ", tableNameList=" + this.getTableNameList() + ", ignoreDatabaseNameList=" + this.getIgnoreDatabaseNameList() + ", databaseDOList=" + this.getDatabaseDOList() + ", tableDOList=" + this.getTableDOList() + ", columnDOList=" + this.getColumnDOList() + ", databaseEntityList=" + this.getDatabaseEntityList() + ", tableEntityList=" + this.getTableEntityList() + ", columnEntityList=" + this.getColumnEntityList() + ")";
    }
}
