/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  com.dtb.metadatahub.entity.ChangeMakerDO
 *  com.dtb.metadatahub.entity.HiveColumnDO
 *  com.dtb.metadatahub.entity.HiveDatabaseDO
 *  com.dtb.metadatahub.entity.HiveTableDO
 *  com.dtb.metadatahub.entity.HiveViewDO
 *  com.dtb.metadatahub.mapping.MappingEngine
 *  com.dtb.metadatahub.repository.mysql.ChangeMakerDao
 *  com.dtb.metadatahub.step.IStep
 *  com.dtb.metadatahub.step.atlas.AtlasMetadataProxy
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.atlas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtb.metadatahub.entity.ChangeMakerDO;
import com.dtb.metadatahub.entity.HiveColumnDO;
import com.dtb.metadatahub.entity.HiveDatabaseDO;
import com.dtb.metadatahub.entity.HiveTableDO;
import com.dtb.metadatahub.entity.HiveViewDO;
import com.dtb.metadatahub.mapping.MappingEngine;
import com.dtb.metadatahub.repository.mysql.ChangeMakerDao;
import com.dtb.metadatahub.step.IStep;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveColumnEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveDatabaseEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveTableEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveViewEntity;
import com.dtb.metadatahub.util.SpringContextUtil;
import com.dtb.metadatahub.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtlasMetadataStep implements IStep {
    private static final Logger log = LoggerFactory.getLogger(AtlasMetadataStep.class);
    private AtlasMetadataProxy metadataProxy;
    private AtlasStepMeta atlasStepMeta;
    private String databaseMappingConfig;
    private String tableMappingConfig;
    private String columnMappingConfig;
    private String viewMappingConfig;
    private boolean successFlag;
    private List<String> tbQualifiedNameList = new ArrayList<String>();
    private List<String> dbQualifiedNameList = new ArrayList<String>();
    private List<String> clusterNameList = new ArrayList<String>();
    private List<AtlasHiveDatabaseEntity> databaseList = new ArrayList<AtlasHiveDatabaseEntity>();
    private List<AtlasHiveTableEntity> tableList = new ArrayList<AtlasHiveTableEntity>();
    private List<AtlasHiveViewEntity> viewList = new ArrayList<AtlasHiveViewEntity>();
    private List<AtlasHiveColumnEntity> columnList = new ArrayList<AtlasHiveColumnEntity>();
    private List<HiveDatabaseDO> databaseDOList = new ArrayList<HiveDatabaseDO>();
    private List<HiveTableDO> tableDOList = new ArrayList<HiveTableDO>();
    private List<HiveViewDO> viewDOList = new ArrayList<HiveViewDO>();
    private List<HiveColumnDO> columnDOList = new ArrayList<HiveColumnDO>();
    private List<ChangeMakerDO> changeMakerDOList = new ArrayList<ChangeMakerDO>();

    public AtlasMetadataStep(AtlasStepMeta atlasStepMeta) {
        this.atlasStepMeta = atlasStepMeta;
        this.metadataProxy = new AtlasMetadataProxy(atlasStepMeta);
        this.transVariable(this.tbQualifiedNameList, atlasStepMeta.getTbQualifiedNames());
        this.transVariable(this.dbQualifiedNameList, atlasStepMeta.getDbQualifiedNames());
        this.transVariable(this.clusterNameList, atlasStepMeta.getClusterQualifiedNames());
        this.successFlag = false;
    }

    public boolean getSuccessFlag() {
        return this.successFlag;
    }

    public void getMetadata() {
        try {
            String resultJsonStr;
            if (this.tbQualifiedNameList.size() > 0) {
                log.info("Gets hive matadata by specified table qualifiedName,the table num are {}", this.tbQualifiedNameList.size());
                this.collectBatchMetadata(this.tbQualifiedNameList, "hive_table");
            } else if (this.dbQualifiedNameList.size() > 0) {
                log.info("Gets hive matadata by specified database qualifiedName,the database num are {}", this.dbQualifiedNameList.size());
                this.collectBatchMetadata(this.dbQualifiedNameList, "hive_db");
            } else if (this.clusterNameList.size() > 0) {
                for (String clusterName : this.clusterNameList) {
                    this.dbQualifiedNameList = this.metadataProxy.getDataBaseListByClusterName(clusterName);
                    log.info("Gets hive matadata by specified clusterName {},the the database num are {}", clusterName, this.dbQualifiedNameList.size());
                    this.collectBatchMetadata(this.dbQualifiedNameList, "hive_db");
                }
            } else {
                this.dbQualifiedNameList = this.metadataProxy.getDataBaseListByClusterName("");
                log.info("Gets all the hive matadata in atlas,the database num are {}", this.dbQualifiedNameList.size());
                this.collectBatchMetadata(this.dbQualifiedNameList, "hive_db");
            }
            log.info("Start the mapping conversion....");
            if (StringUtils.isNoneEmpty(this.databaseMappingConfig)) {
                resultJsonStr = MappingEngine.mappingForObjectInstance(this.databaseMappingConfig, this.databaseList);
                this.databaseDOList = JSONObject.parseArray(resultJsonStr, HiveDatabaseDO.class);
            }
            if (StringUtils.isNoneEmpty(this.tableMappingConfig)) {
                resultJsonStr = MappingEngine.mappingForObjectInstance(this.tableMappingConfig, this.tableList);
                this.tableDOList = JSONObject.parseArray(resultJsonStr, HiveTableDO.class);
            }
            if (StringUtils.isNoneEmpty(this.columnMappingConfig)) {
                resultJsonStr = MappingEngine.mappingForObjectInstance(this.columnMappingConfig, this.columnList);
                this.columnDOList = JSONObject.parseArray(resultJsonStr, HiveColumnDO.class);
            }
            if (StringUtils.isNoneEmpty(this.viewMappingConfig)) {
                resultJsonStr = MappingEngine.mappingForObjectInstance(this.viewMappingConfig, this.viewList);
                this.viewDOList = JSONObject.parseArray(resultJsonStr, HiveViewDO.class);
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

    private void collectBatchMetadata(List<String> qualifiedNameList, String typeName) {
        for (String qualifiedName : qualifiedNameList) {
            long startTime = System.currentTimeMillis();
            if (typeName.equalsIgnoreCase("hive_db")) {
                try {
                    Long changeMaker = 0L;
                    ChangeMakerDao changeMakerDao = (ChangeMakerDao)SpringContextUtil.getContext().getBean(ChangeMakerDao.class);
                    ChangeMakerDO resultMaker = changeMakerDao.getByQualifiedName(qualifiedName);
                    log.info("get change maker by qualifiedName {}: {}", (Object)qualifiedName, (Object)JSON.toJSONString((Object)resultMaker));
                    changeMaker = resultMaker == null ? Long.valueOf(0L) : Long.valueOf(resultMaker.getChangeMaker());
                    this.metadataProxy.getDataBaseMetadata(qualifiedName, typeName, changeMaker);
                    this.databaseList = this.metadataProxy.getDatabaseEntityList();
                    this.tableList = this.metadataProxy.getTableEntityList();
                    this.viewList = this.metadataProxy.getViewEntityList();
                    this.columnList = this.metadataProxy.getColumnEntityList();
                    ChangeMakerDO cmDO = new ChangeMakerDO();
                    cmDO.setQualifiedName(qualifiedName);
                    cmDO.setChangeMaker(this.metadataProxy.getChangeMarker().longValue());
                    this.changeMakerDOList.add(cmDO);
                }
                catch (Exception e) {
                    log.error("An exception occurs when obtaining the table metadata by table qualifiedName:{}. The reason is: {}", (Object)qualifiedName, (Object)e.getMessage());
                }
            } else if (typeName.equalsIgnoreCase("hive_table")) {
                try {
                    this.metadataProxy.getTableMetadata(qualifiedName);
                    this.databaseList = this.metadataProxy.getDatabaseEntityList();
                    this.tableList = this.metadataProxy.getTableEntityList();
                    this.viewList = this.metadataProxy.getViewEntityList();
                    this.columnList = this.metadataProxy.getColumnEntityList();
                }
                catch (Exception e) {
                    log.error("An exception occurs when obtaining the table metadata by table qualifiedName:{}. The reason is: {}", (Object)qualifiedName, (Object)e.getMessage());
                }
            }
            long endTime = System.currentTimeMillis();
            long runTime = (endTime - startTime) / 1000L;
            log.info("{} metadata collection takes {} seconds", (Object)qualifiedName, (Object)runTime);
        }
    }

    private void transVariable(List<String> list, String strs) {
        String[] strArr;
        if (!StringUtil.isEmpty(strs) && (strArr = strs.trim().split(",")).length > 0) {
            for (String str : strArr) {
                if (StringUtil.isEmpty(str)) continue;
                list.add(str.trim());
            }
        }
    }

    public AtlasMetadataProxy getMetadataProxy() {
        return this.metadataProxy;
    }

    public AtlasStepMeta getAtlasStepMeta() {
        return this.atlasStepMeta;
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

    public List<String> getTbQualifiedNameList() {
        return this.tbQualifiedNameList;
    }

    public List<String> getDbQualifiedNameList() {
        return this.dbQualifiedNameList;
    }

    public List<String> getClusterNameList() {
        return this.clusterNameList;
    }

    public List<AtlasHiveDatabaseEntity> getDatabaseList() {
        return this.databaseList;
    }

    public List<AtlasHiveTableEntity> getTableList() {
        return this.tableList;
    }

    public List<AtlasHiveViewEntity> getViewList() {
        return this.viewList;
    }

    public List<AtlasHiveColumnEntity> getColumnList() {
        return this.columnList;
    }

    public List<HiveDatabaseDO> getDatabaseDOList() {
        return this.databaseDOList;
    }

    public List<HiveTableDO> getTableDOList() {
        return this.tableDOList;
    }

    public List<HiveViewDO> getViewDOList() {
        return this.viewDOList;
    }

    public List<HiveColumnDO> getColumnDOList() {
        return this.columnDOList;
    }

    public List<ChangeMakerDO> getChangeMakerDOList() {
        return this.changeMakerDOList;
    }

    public void setMetadataProxy(AtlasMetadataProxy metadataProxy) {
        this.metadataProxy = metadataProxy;
    }

    public void setAtlasStepMeta(AtlasStepMeta atlasStepMeta) {
        this.atlasStepMeta = atlasStepMeta;
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

    public void setTbQualifiedNameList(List<String> tbQualifiedNameList) {
        this.tbQualifiedNameList = tbQualifiedNameList;
    }

    public void setDbQualifiedNameList(List<String> dbQualifiedNameList) {
        this.dbQualifiedNameList = dbQualifiedNameList;
    }

    public void setClusterNameList(List<String> clusterNameList) {
        this.clusterNameList = clusterNameList;
    }

    public void setDatabaseList(List<AtlasHiveDatabaseEntity> databaseList) {
        this.databaseList = databaseList;
    }

    public void setTableList(List<AtlasHiveTableEntity> tableList) {
        this.tableList = tableList;
    }

    public void setViewList(List<AtlasHiveViewEntity> viewList) {
        this.viewList = viewList;
    }

    public void setColumnList(List<AtlasHiveColumnEntity> columnList) {
        this.columnList = columnList;
    }

    public void setDatabaseDOList(List<HiveDatabaseDO> databaseDOList) {
        this.databaseDOList = databaseDOList;
    }

    public void setTableDOList(List<HiveTableDO> tableDOList) {
        this.tableDOList = tableDOList;
    }

    public void setViewDOList(List<HiveViewDO> viewDOList) {
        this.viewDOList = viewDOList;
    }

    public void setColumnDOList(List<HiveColumnDO> columnDOList) {
        this.columnDOList = columnDOList;
    }

    public void setChangeMakerDOList(List<ChangeMakerDO> changeMakerDOList) {
        this.changeMakerDOList = changeMakerDOList;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AtlasMetadataStep)) {
            return false;
        }
        AtlasMetadataStep other = (AtlasMetadataStep)o;
        if (!other.canEqual(this)) {
            return false;
        }
        AtlasMetadataProxy this$metadataProxy = this.getMetadataProxy();
        AtlasMetadataProxy other$metadataProxy = other.getMetadataProxy();
        if (this$metadataProxy == null ? other$metadataProxy != null : !this$metadataProxy.equals(other$metadataProxy)) {
            return false;
        }
        AtlasStepMeta this$atlasStepMeta = this.getAtlasStepMeta();
        AtlasStepMeta other$atlasStepMeta = other.getAtlasStepMeta();
        if (this$atlasStepMeta == null ? other$atlasStepMeta != null : !((Object)this$atlasStepMeta).equals(other$atlasStepMeta)) {
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
        List<String> this$tbQualifiedNameList = this.getTbQualifiedNameList();
        List<String> other$tbQualifiedNameList = other.getTbQualifiedNameList();
        if (this$tbQualifiedNameList == null ? other$tbQualifiedNameList != null : !((Object)this$tbQualifiedNameList).equals(other$tbQualifiedNameList)) {
            return false;
        }
        List<String> this$dbQualifiedNameList = this.getDbQualifiedNameList();
        List<String> other$dbQualifiedNameList = other.getDbQualifiedNameList();
        if (this$dbQualifiedNameList == null ? other$dbQualifiedNameList != null : !((Object)this$dbQualifiedNameList).equals(other$dbQualifiedNameList)) {
            return false;
        }
        List<String> this$clusterNameList = this.getClusterNameList();
        List<String> other$clusterNameList = other.getClusterNameList();
        if (this$clusterNameList == null ? other$clusterNameList != null : !((Object)this$clusterNameList).equals(other$clusterNameList)) {
            return false;
        }
        List<AtlasHiveDatabaseEntity> this$databaseList = this.getDatabaseList();
        List<AtlasHiveDatabaseEntity> other$databaseList = other.getDatabaseList();
        if (this$databaseList == null ? other$databaseList != null : !((Object)this$databaseList).equals(other$databaseList)) {
            return false;
        }
        List<AtlasHiveTableEntity> this$tableList = this.getTableList();
        List<AtlasHiveTableEntity> other$tableList = other.getTableList();
        if (this$tableList == null ? other$tableList != null : !((Object)this$tableList).equals(other$tableList)) {
            return false;
        }
        List<AtlasHiveViewEntity> this$viewList = this.getViewList();
        List<AtlasHiveViewEntity> other$viewList = other.getViewList();
        if (this$viewList == null ? other$viewList != null : !((Object)this$viewList).equals(other$viewList)) {
            return false;
        }
        List<AtlasHiveColumnEntity> this$columnList = this.getColumnList();
        List<AtlasHiveColumnEntity> other$columnList = other.getColumnList();
        if (this$columnList == null ? other$columnList != null : !((Object)this$columnList).equals(other$columnList)) {
            return false;
        }
        List<HiveDatabaseDO> this$databaseDOList = this.getDatabaseDOList();
        List<HiveDatabaseDO> other$databaseDOList = other.getDatabaseDOList();
        if (this$databaseDOList == null ? other$databaseDOList != null : !((Object)this$databaseDOList).equals(other$databaseDOList)) {
            return false;
        }
        List<HiveTableDO> this$tableDOList = this.getTableDOList();
        List<HiveTableDO> other$tableDOList = other.getTableDOList();
        if (this$tableDOList == null ? other$tableDOList != null : !((Object)this$tableDOList).equals(other$tableDOList)) {
            return false;
        }
        List<HiveViewDO> this$viewDOList = this.getViewDOList();
        List<HiveViewDO> other$viewDOList = other.getViewDOList();
        if (this$viewDOList == null ? other$viewDOList != null : !((Object)this$viewDOList).equals(other$viewDOList)) {
            return false;
        }
        List<HiveColumnDO> this$columnDOList = this.getColumnDOList();
        List<HiveColumnDO> other$columnDOList = other.getColumnDOList();
        if (this$columnDOList == null ? other$columnDOList != null : !((Object)this$columnDOList).equals(other$columnDOList)) {
            return false;
        }
        List<ChangeMakerDO> this$changeMakerDOList = this.getChangeMakerDOList();
        List<ChangeMakerDO> other$changeMakerDOList = other.getChangeMakerDOList();
        return !(this$changeMakerDOList == null ? other$changeMakerDOList != null : !((Object)this$changeMakerDOList).equals(other$changeMakerDOList));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AtlasMetadataStep;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        AtlasMetadataProxy $metadataProxy = this.getMetadataProxy();
        result = result * 59 + ($metadataProxy == null ? 43 : $metadataProxy.hashCode());
        AtlasStepMeta $atlasStepMeta = this.getAtlasStepMeta();
        result = result * 59 + ($atlasStepMeta == null ? 43 : ((Object)$atlasStepMeta).hashCode());
        String $databaseMappingConfig = this.getDatabaseMappingConfig();
        result = result * 59 + ($databaseMappingConfig == null ? 43 : $databaseMappingConfig.hashCode());
        String $tableMappingConfig = this.getTableMappingConfig();
        result = result * 59 + ($tableMappingConfig == null ? 43 : $tableMappingConfig.hashCode());
        String $columnMappingConfig = this.getColumnMappingConfig();
        result = result * 59 + ($columnMappingConfig == null ? 43 : $columnMappingConfig.hashCode());
        String $viewMappingConfig = this.getViewMappingConfig();
        result = result * 59 + ($viewMappingConfig == null ? 43 : $viewMappingConfig.hashCode());
        result = result * 59 + (this.getSuccessFlag() ? 79 : 97);
        List<String> $tbQualifiedNameList = this.getTbQualifiedNameList();
        result = result * 59 + ($tbQualifiedNameList == null ? 43 : ((Object)$tbQualifiedNameList).hashCode());
        List<String> $dbQualifiedNameList = this.getDbQualifiedNameList();
        result = result * 59 + ($dbQualifiedNameList == null ? 43 : ((Object)$dbQualifiedNameList).hashCode());
        List<String> $clusterNameList = this.getClusterNameList();
        result = result * 59 + ($clusterNameList == null ? 43 : ((Object)$clusterNameList).hashCode());
        List<AtlasHiveDatabaseEntity> $databaseList = this.getDatabaseList();
        result = result * 59 + ($databaseList == null ? 43 : ((Object)$databaseList).hashCode());
        List<AtlasHiveTableEntity> $tableList = this.getTableList();
        result = result * 59 + ($tableList == null ? 43 : ((Object)$tableList).hashCode());
        List<AtlasHiveViewEntity> $viewList = this.getViewList();
        result = result * 59 + ($viewList == null ? 43 : ((Object)$viewList).hashCode());
        List<AtlasHiveColumnEntity> $columnList = this.getColumnList();
        result = result * 59 + ($columnList == null ? 43 : ((Object)$columnList).hashCode());
        List<HiveDatabaseDO> $databaseDOList = this.getDatabaseDOList();
        result = result * 59 + ($databaseDOList == null ? 43 : ((Object)$databaseDOList).hashCode());
        List<HiveTableDO> $tableDOList = this.getTableDOList();
        result = result * 59 + ($tableDOList == null ? 43 : ((Object)$tableDOList).hashCode());
        List<HiveViewDO> $viewDOList = this.getViewDOList();
        result = result * 59 + ($viewDOList == null ? 43 : ((Object)$viewDOList).hashCode());
        List<HiveColumnDO> $columnDOList = this.getColumnDOList();
        result = result * 59 + ($columnDOList == null ? 43 : ((Object)$columnDOList).hashCode());
        List<ChangeMakerDO> $changeMakerDOList = this.getChangeMakerDOList();
        result = result * 59 + ($changeMakerDOList == null ? 43 : ((Object)$changeMakerDOList).hashCode());
        return result;
    }

    public String toString() {
        return "AtlasMetadataStep(metadataProxy=" + this.getMetadataProxy() + ", atlasStepMeta=" + this.getAtlasStepMeta() + ", databaseMappingConfig=" + this.getDatabaseMappingConfig() + ", tableMappingConfig=" + this.getTableMappingConfig() + ", columnMappingConfig=" + this.getColumnMappingConfig() + ", viewMappingConfig=" + this.getViewMappingConfig() + ", successFlag=" + this.getSuccessFlag() + ", tbQualifiedNameList=" + this.getTbQualifiedNameList() + ", dbQualifiedNameList=" + this.getDbQualifiedNameList() + ", clusterNameList=" + this.getClusterNameList() + ", databaseList=" + this.getDatabaseList() + ", tableList=" + this.getTableList() + ", viewList=" + this.getViewList() + ", columnList=" + this.getColumnList() + ", databaseDOList=" + this.getDatabaseDOList() + ", tableDOList=" + this.getTableDOList() + ", viewDOList=" + this.getViewDOList() + ", columnDOList=" + this.getColumnDOList() + ", changeMakerDOList=" + this.getChangeMakerDOList() + ")";
    }
}
