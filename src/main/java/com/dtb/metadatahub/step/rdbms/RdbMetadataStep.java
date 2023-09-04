
package com.dtb.metadatahub.step.rdbms;

import com.alibaba.fastjson.JSONObject;
import com.dtb.metadatahub.entity.RdbColumnDO;
import com.dtb.metadatahub.entity.RdbDatabaseDO;
import com.dtb.metadatahub.entity.RdbTableDO;
import com.dtb.metadatahub.mapping.MappingEngine;
import com.dtb.metadatahub.step.IStep;
import com.dtb.metadatahub.step.rdbms.RdbMetadataProxy;
import com.dtb.metadatahub.step.rdbms.RdbStepMeta;
import com.dtb.metadatahub.step.rdbms.entities.RdbColumnEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbDatabaseEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbTableEntity;
import com.dtb.metadatahub.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RdbMetadataStep
implements IStep {
    private static final Logger LOG = LoggerFactory.getLogger(RdbMetadataStep.class);
    private List<String> databaseNameList = new ArrayList<String>();
    private List<String> tableNameList = new ArrayList<String>();
    private List<String> ignoreDatabaseNameList = new ArrayList<String>();
    private List<RdbDatabaseEntity> databaseEntityList = new ArrayList<RdbDatabaseEntity>();
    private List<RdbTableEntity> tableEntityList = new ArrayList<RdbTableEntity>();
    private List<RdbColumnEntity> columnEntityList = new ArrayList<RdbColumnEntity>();
    private RdbMetadataProxy rdbMetadataProxy;
    private RdbStepMeta rdbStepMeta;
    private String databaseMappingConfig;
    private String tableMappingConfig;
    private String columnMappingConfig;
    List<RdbDatabaseDO> databaseDOList = new ArrayList<RdbDatabaseDO>();
    List<RdbTableDO> tableDOList = new ArrayList<RdbTableDO>();
    List<RdbColumnDO> columnDOList = new ArrayList<RdbColumnDO>();
    private boolean successFlag;

    public RdbMetadataStep(RdbStepMeta rdbStepMeta) {
        this.rdbStepMeta = rdbStepMeta;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void getMetadata() {
        this.init();
        long startTime = System.currentTimeMillis();
        try {
            String resultJsonStr;
            if (this.tableNameList.size() > 0) {
                LOG.info("Gets the specified tables metadata, the table num are {}", (Object)this.tableNameList.size());
                this.getRdbMetadataByNames(this.tableNameList, "rdb_table");
            } else if (this.databaseNameList.size() > 0) {
                LOG.info("Gets the specified database metadata, the database num are {}", (Object)this.databaseNameList.size());
                this.getRdbMetadataByNames(this.databaseNameList, "rdb_db");
            } else {
                LOG.info("Gets all the database metadata for that user:{}", (Object)this.rdbMetadataProxy.getDatabaseEntity().getUser());
                this.databaseNameList = this.rdbMetadataProxy.getDatabaseList();
                LOG.info("databaseNameList = " + this.databaseNameList);
                this.ignoreDatabaseName(this.databaseNameList, this.ignoreDatabaseNameList);
                this.getRdbMetadataByNames(this.databaseNameList, "rdb_db");
            }
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{this.databaseMappingConfig})) {
                resultJsonStr = MappingEngine.mappingForObjectInstance((String)this.databaseMappingConfig, this.databaseEntityList);
                this.databaseDOList = JSONObject.parseArray((String)resultJsonStr, RdbDatabaseDO.class);
            }
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{this.tableMappingConfig})) {
                resultJsonStr = MappingEngine.mappingForObjectInstance((String)this.tableMappingConfig, this.tableEntityList);
                this.tableDOList = JSONObject.parseArray((String)resultJsonStr, RdbTableDO.class);
            }
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{this.columnMappingConfig})) {
                resultJsonStr = MappingEngine.mappingForObjectInstance((String)this.columnMappingConfig, this.columnEntityList);
                this.columnDOList = JSONObject.parseArray((String)resultJsonStr, RdbColumnDO.class);
            }
            this.successFlag = true;
        }
        catch (Exception e) {
            LOG.error("Gets the metadata failed", (Throwable)e);
            this.successFlag = false;
        }
        finally {
            this.rdbMetadataProxy.getRdbMetadataClient().close();
        }
    }

    private void init() {
        this.rdbMetadataProxy = new RdbMetadataProxy(this.rdbStepMeta);
        this.transVariable(this.databaseNameList, this.rdbStepMeta.getExtratorDatabaseNames());
        this.transVariable(this.tableNameList, this.rdbStepMeta.getExtratorTableNames());
        this.transVariable(this.ignoreDatabaseNameList, this.rdbStepMeta.getIgnoreDatabaseNames());
        if (this.databaseNameList.size() > 0 && this.ignoreDatabaseNameList.size() > 0) {
            this.ignoreDatabaseName(this.databaseNameList, this.ignoreDatabaseNameList);
        }
        this.successFlag = false;
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
            LOG.error("Trans the variable error", (Throwable)e);
        }
    }

    private void ignoreDatabaseName(List<String> inputDatabaseNameList, List<String> ignoreDatabaseNameList) {
        for (String ignoreDatabaseName : ignoreDatabaseNameList) {
            if (!inputDatabaseNameList.contains(ignoreDatabaseName)) continue;
            inputDatabaseNameList.remove(ignoreDatabaseName);
        }
    }

    private void getRdbMetadataByNames(List<String> inputNames, String typeName) {
        block3: {
            block2: {
                if (!typeName.equalsIgnoreCase("rdb_db")) break block2;
                for (String databaseName : inputNames) {
                    this.getRdbMetadataByDatabaseName(databaseName);
                }
                break block3;
            }
            if (!typeName.equalsIgnoreCase("rdb_table")) break block3;
            for (String tableName : inputNames) {
                this.getRdbMetadataByTableName(tableName);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void getRdbMetadataByDatabaseName(String databaseName) {
        RdbMetadataProxy extractor = new RdbMetadataProxy(this.rdbStepMeta);
        long startTime = System.currentTimeMillis();

        try {
            RdbDatabaseEntity databaseEntity = extractor.getDatabaseEntity(databaseName);
            this.databaseEntityList.add(databaseEntity);
            List<RdbTableEntity> tableEntities = extractor.getTablesEntities(databaseName);
            List<String> tableNames = extractor.getAllTableAndViewList(databaseName);
            Iterator var8 = tableNames.iterator();

            while(var8.hasNext()) {
                String tableName = (String)var8.next();
                List<RdbColumnEntity> columnEntities = extractor.getTableColumnsEntities(databaseName, tableName);
                String tableQualifiedName = databaseName + "." + tableName + "@";
                Iterator var12 = tableEntities.iterator();

                while(var12.hasNext()) {
                    RdbTableEntity tableEntity = (RdbTableEntity)var12.next();
                    if (tableEntity.getQualifiedName().contains(tableQualifiedName)) {
                        tableEntity.setColumnNum(columnEntities.size());
                    }
                }

                this.columnEntityList.addAll(columnEntities);
            }

            this.tableEntityList.addAll(tableEntities);
            long endTime = System.currentTimeMillis();
            long runTime = (endTime - startTime) / 1000L;
            LOG.info("====>Finished to collect the database [{}] metadata, take {} seconds", databaseName, runTime);
        } catch (Exception var17) {
            LOG.error("Collect the database [{}] metadata failed", databaseName, var17);
            var17.printStackTrace();
        } finally {
            extractor.getRdbMetadataClient().close();
        }

    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void getRdbMetadataByTableName(String tableName) {
        RdbMetadataProxy extractor = new RdbMetadataProxy(this.rdbStepMeta);
        long startTime = System.currentTimeMillis();
        try {
            RdbTableEntity tableEntity = extractor.getTableEntity(tableName);
            String databaseName = extractor.getDatabaseEntity().getName();
            List columnEntities = extractor.getTableColumnsEntities(tableName);
            tableEntity.setColumnNum(columnEntities.size());
            this.tableEntityList.add(tableEntity);
            this.columnEntityList.addAll(columnEntities);
            long endTime = System.currentTimeMillis();
            long runTime = (endTime - startTime) / 1000L;
            LOG.info("====>Finished to collect the table [{}] metadata, take {} seconds", (Object)tableName, (Object)runTime);
        }
        catch (Exception e) {
            LOG.error("Collect the table [{}] metadata failed", (Object)tableName, (Object)e);
            e.printStackTrace();
        }
        finally {
            extractor.getRdbMetadataClient().close();
        }
    }

    public boolean getSuccessFlag() {
        return this.successFlag;
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

    public List<RdbDatabaseEntity> getDatabaseEntityList() {
        return this.databaseEntityList;
    }

    public List<RdbTableEntity> getTableEntityList() {
        return this.tableEntityList;
    }

    public List<RdbColumnEntity> getColumnEntityList() {
        return this.columnEntityList;
    }

    public RdbMetadataProxy getRdbMetadataProxy() {
        return this.rdbMetadataProxy;
    }

    public RdbStepMeta getRdbStepMeta() {
        return this.rdbStepMeta;
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

    public List<RdbDatabaseDO> getDatabaseDOList() {
        return this.databaseDOList;
    }

    public List<RdbTableDO> getTableDOList() {
        return this.tableDOList;
    }

    public List<RdbColumnDO> getColumnDOList() {
        return this.columnDOList;
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

    public void setDatabaseEntityList(List<RdbDatabaseEntity> databaseEntityList) {
        this.databaseEntityList = databaseEntityList;
    }

    public void setTableEntityList(List<RdbTableEntity> tableEntityList) {
        this.tableEntityList = tableEntityList;
    }

    public void setColumnEntityList(List<RdbColumnEntity> columnEntityList) {
        this.columnEntityList = columnEntityList;
    }

    public void setRdbMetadataProxy(RdbMetadataProxy rdbMetadataProxy) {
        this.rdbMetadataProxy = rdbMetadataProxy;
    }

    public void setRdbStepMeta(RdbStepMeta rdbStepMeta) {
        this.rdbStepMeta = rdbStepMeta;
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

    public void setDatabaseDOList(List<RdbDatabaseDO> databaseDOList) {
        this.databaseDOList = databaseDOList;
    }

    public void setTableDOList(List<RdbTableDO> tableDOList) {
        this.tableDOList = tableDOList;
    }

    public void setColumnDOList(List<RdbColumnDO> columnDOList) {
        this.columnDOList = columnDOList;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbMetadataStep)) {
            return false;
        }
        RdbMetadataStep other = (RdbMetadataStep)o;
        if (!other.canEqual(this)) {
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
        List<RdbDatabaseEntity> this$databaseEntityList = this.getDatabaseEntityList();
        List<RdbDatabaseEntity> other$databaseEntityList = other.getDatabaseEntityList();
        if (this$databaseEntityList == null ? other$databaseEntityList != null : !((Object)this$databaseEntityList).equals(other$databaseEntityList)) {
            return false;
        }
        List<RdbTableEntity> this$tableEntityList = this.getTableEntityList();
        List<RdbTableEntity> other$tableEntityList = other.getTableEntityList();
        if (this$tableEntityList == null ? other$tableEntityList != null : !((Object)this$tableEntityList).equals(other$tableEntityList)) {
            return false;
        }
        List<RdbColumnEntity> this$columnEntityList = this.getColumnEntityList();
        List<RdbColumnEntity> other$columnEntityList = other.getColumnEntityList();
        if (this$columnEntityList == null ? other$columnEntityList != null : !((Object)this$columnEntityList).equals(other$columnEntityList)) {
            return false;
        }
        RdbMetadataProxy this$rdbMetadataProxy = this.getRdbMetadataProxy();
        RdbMetadataProxy other$rdbMetadataProxy = other.getRdbMetadataProxy();
        if (this$rdbMetadataProxy == null ? other$rdbMetadataProxy != null : !this$rdbMetadataProxy.equals(other$rdbMetadataProxy)) {
            return false;
        }
        RdbStepMeta this$rdbStepMeta = this.getRdbStepMeta();
        RdbStepMeta other$rdbStepMeta = other.getRdbStepMeta();
        if (this$rdbStepMeta == null ? other$rdbStepMeta != null : !((Object)this$rdbStepMeta).equals(other$rdbStepMeta)) {
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
        List<RdbDatabaseDO> this$databaseDOList = this.getDatabaseDOList();
        List<RdbDatabaseDO> other$databaseDOList = other.getDatabaseDOList();
        if (this$databaseDOList == null ? other$databaseDOList != null : !((Object)this$databaseDOList).equals(other$databaseDOList)) {
            return false;
        }
        List<RdbTableDO> this$tableDOList = this.getTableDOList();
        List<RdbTableDO> other$tableDOList = other.getTableDOList();
        if (this$tableDOList == null ? other$tableDOList != null : !((Object)this$tableDOList).equals(other$tableDOList)) {
            return false;
        }
        List<RdbColumnDO> this$columnDOList = this.getColumnDOList();
        List<RdbColumnDO> other$columnDOList = other.getColumnDOList();
        if (this$columnDOList == null ? other$columnDOList != null : !((Object)this$columnDOList).equals(other$columnDOList)) {
            return false;
        }
        return this.getSuccessFlag() == other.getSuccessFlag();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RdbMetadataStep;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        List<String> $databaseNameList = this.getDatabaseNameList();
        result = result * 59 + ($databaseNameList == null ? 43 : ((Object)$databaseNameList).hashCode());
        List<String> $tableNameList = this.getTableNameList();
        result = result * 59 + ($tableNameList == null ? 43 : ((Object)$tableNameList).hashCode());
        List<String> $ignoreDatabaseNameList = this.getIgnoreDatabaseNameList();
        result = result * 59 + ($ignoreDatabaseNameList == null ? 43 : ((Object)$ignoreDatabaseNameList).hashCode());
        List<RdbDatabaseEntity> $databaseEntityList = this.getDatabaseEntityList();
        result = result * 59 + ($databaseEntityList == null ? 43 : ((Object)$databaseEntityList).hashCode());
        List<RdbTableEntity> $tableEntityList = this.getTableEntityList();
        result = result * 59 + ($tableEntityList == null ? 43 : ((Object)$tableEntityList).hashCode());
        List<RdbColumnEntity> $columnEntityList = this.getColumnEntityList();
        result = result * 59 + ($columnEntityList == null ? 43 : ((Object)$columnEntityList).hashCode());
        RdbMetadataProxy $rdbMetadataProxy = this.getRdbMetadataProxy();
        result = result * 59 + ($rdbMetadataProxy == null ? 43 : $rdbMetadataProxy.hashCode());
        RdbStepMeta $rdbStepMeta = this.getRdbStepMeta();
        result = result * 59 + ($rdbStepMeta == null ? 43 : ((Object)$rdbStepMeta).hashCode());
        String $databaseMappingConfig = this.getDatabaseMappingConfig();
        result = result * 59 + ($databaseMappingConfig == null ? 43 : $databaseMappingConfig.hashCode());
        String $tableMappingConfig = this.getTableMappingConfig();
        result = result * 59 + ($tableMappingConfig == null ? 43 : $tableMappingConfig.hashCode());
        String $columnMappingConfig = this.getColumnMappingConfig();
        result = result * 59 + ($columnMappingConfig == null ? 43 : $columnMappingConfig.hashCode());
        List<RdbDatabaseDO> $databaseDOList = this.getDatabaseDOList();
        result = result * 59 + ($databaseDOList == null ? 43 : ((Object)$databaseDOList).hashCode());
        List<RdbTableDO> $tableDOList = this.getTableDOList();
        result = result * 59 + ($tableDOList == null ? 43 : ((Object)$tableDOList).hashCode());
        List<RdbColumnDO> $columnDOList = this.getColumnDOList();
        result = result * 59 + ($columnDOList == null ? 43 : ((Object)$columnDOList).hashCode());
        result = result * 59 + (this.getSuccessFlag() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "RdbMetadataStep(databaseNameList=" + this.getDatabaseNameList() + ", tableNameList=" + this.getTableNameList() + ", ignoreDatabaseNameList=" + this.getIgnoreDatabaseNameList() + ", databaseEntityList=" + this.getDatabaseEntityList() + ", tableEntityList=" + this.getTableEntityList() + ", columnEntityList=" + this.getColumnEntityList() + ", rdbMetadataProxy=" + this.getRdbMetadataProxy() + ", rdbStepMeta=" + this.getRdbStepMeta() + ", databaseMappingConfig=" + this.getDatabaseMappingConfig() + ", tableMappingConfig=" + this.getTableMappingConfig() + ", columnMappingConfig=" + this.getColumnMappingConfig() + ", databaseDOList=" + this.getDatabaseDOList() + ", tableDOList=" + this.getTableDOList() + ", columnDOList=" + this.getColumnDOList() + ", successFlag=" + this.getSuccessFlag() + ")";
    }
}
