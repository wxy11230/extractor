/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.step.mpp.MppMetadataClient
 *  com.google.common.base.Throwables
 *  org.apache.logging.log4j.util.Strings
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.BeanUtils
 */
package com.dtb.metadatahub.step.mpp;

import com.dtb.metadatahub.step.mpp.MppMetadataClient;
import com.dtb.metadatahub.step.mpp.MppStepMeta;
import com.dtb.metadatahub.step.mpp.entities.MppColumnEntity;
import com.dtb.metadatahub.step.mpp.entities.MppDatabaseEntity;
import com.dtb.metadatahub.step.mpp.entities.MppTableEntity;
import com.dtb.metadatahub.util.MD5Util;
import com.google.common.base.Throwables;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class MppMetadataProxy {
    private static final Logger LOG = LoggerFactory.getLogger(MppMetadataProxy.class);
    static final List<String> FILTER_DATABASE = Arrays.asList("template0", "template1");
    static final List<String> FILTER_SCHEMA = Arrays.asList("pg_toast", "sys", "information_schema", "pg_catalog", "cstore", "dbms_job", "utl_file", "dbms_output", "dbms_random", "dbms_sql", "dbms_lob", "utl_raw", "snapshot", "dbe_perf", "gp_toolkit");
    static final List<String> FILTER_COLUMN = Arrays.asList("xc_node_id", "tableoid", "ctid", "xmin", "xmax", "cmin", "cmax", "gp_segment_id");
    static final String FILTER_SCHEMA_PREFIX = "pg_";
    static final String FILTER_TABLE_PATTERN = "_prt_";
    static final String FILTER_TABLE_PKEY = "_pkey";
    private MppMetadataClient client;
    private boolean ignoreDDL;
    private boolean ignoreRowNum;
    private boolean ignoreTableSize;
    private MppStepMeta stepMeta;
    List<MppDatabaseEntity> databaseEntityList;
    List<MppTableEntity> tableEntityList;
    List<MppColumnEntity> columnEntityList;

    public MppMetadataProxy(MppStepMeta stepMeta) {
        try {
            LOG.info("Initialize the MppMetadataClient");
            this.client = new MppMetadataClient(stepMeta);
            this.stepMeta = stepMeta;
            this.ignoreDDL = this.stepMeta.isIgnoreDDL();
            this.ignoreRowNum = this.stepMeta.isIgnoreRowNum();
            this.ignoreTableSize = this.stepMeta.isIgnoreTableSize();
            this.databaseEntityList = new ArrayList<MppDatabaseEntity>();
            this.tableEntityList = new ArrayList<MppTableEntity>();
            this.columnEntityList = new ArrayList<MppColumnEntity>();
        }
        catch (Exception e) {
            LOG.error("Initialize the MppMetadataClient failed!", (Throwable)e);
            Throwables.propagate((Throwable)e);
        }
    }

    public List<String> getDatabaseList() throws Exception {
        ArrayList<String> databaseList = new ArrayList<String>();
        databaseList.addAll(this.client.getAllDatabaseList());
        return databaseList;
    }

    public void getDatabaseMetadata() {
        this.getDatabaseMetadata(null);
    }

    public void getDatabaseMetadata(String databaseName) {
        this.databaseEntityList.clear();
        this.tableEntityList.clear();
        this.columnEntityList.clear();

        try {
            LOG.info("====>Start to get the database [{}] entity metadata...", databaseName);
            List<Map<String, Object>> resultList = this.client.getDatabaseMetadata(databaseName);
            Iterator var3 = resultList.iterator();

            while(true) {
                Map result;
                String dbName;
                do {
                    if (!var3.hasNext()) {
                        return;
                    }

                    result = (Map)var3.next();
                    dbName = (String)result.get("datName");
                } while(FILTER_DATABASE.contains(dbName));

                String url = null;
                List<Map<String, Object>> schemas = this.getSchemaByDatabase((String)result.get("datName"));
                Iterator var8 = schemas.iterator();

                while(var8.hasNext()) {
                    Map<String, Object> schemaObject = (Map)var8.next();
                    String schemaName = schemaObject.get("name").toString();
                    String schemaId = schemaObject.get("oid").toString();
                    if ("mpp_gauss".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                        url = "jdbc:postgresql://" + this.stepMeta.getDatabaseServer() + ":" + this.stepMeta.getPort() + "/" + this.stepMeta.getDatabaseName();
                    } else if ("mpp_greenplum".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                        url = "jdbc:pivotal:greenplum://" + this.stepMeta.getDatabaseServer() + ":" + this.stepMeta.getPort() + ";DatabaseName=" + this.stepMeta.getDatabaseName();
                    }

                    if (!FILTER_SCHEMA.contains(schemaName) && !schemaName.startsWith("pg_")) {
                        MppDatabaseEntity databaseEntity = new MppDatabaseEntity();
                        databaseEntity.setAllowconn((Boolean)result.get("datallowconn"));
                        databaseEntity.setConnlimit((Integer)result.get("datconnlimit"));
                        databaseEntity.setDatctype((String)result.get("datctype"));
                        databaseEntity.setEncoding(String.valueOf((Integer)result.get("encoding")));
                        databaseEntity.setGuid(schemaId);
                        databaseEntity.setIstemplate((Boolean)result.get("datistemplate"));
                        databaseEntity.setName(schemaName);
                        databaseEntity.setQualifiedName(schemaName + "@" + this.stepMeta.getDatabaseServer() + ":" + this.stepMeta.getPort() + ":" + dbName);
                        databaseEntity.setTableSpace((String)result.get("spcname"));
                        databaseEntity.setUser((String)result.get("rolname"));
                        databaseEntity.setUrl(url);
                        databaseEntity.setUserName(this.stepMeta.getUserName());
                        databaseEntity.setPassword(this.stepMeta.getPassword());
                        if ("mpp_gauss".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                            databaseEntity.setTypeName("gauss_db");
                        } else if ("mpp_greenplum".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                            databaseEntity.setTypeName("gp_db");
                        }

                        this.databaseEntityList.add(databaseEntity);
                        this.getTableAndColumnByDatabaseName(dbName, schemaObject, (String)null, (String)null);
                    }
                }
            }
        } catch (Exception var13) {
            LOG.error("Gets the database entity metadata failed!", var13);
        }
    }

    public void getTableMetadata(String tableName) throws Exception {
        this.getTableMetadata(null, tableName);
    }

    public void getTableMetadata(String schemaName, String tableName) throws Exception {
        LOG.info("Start to get the table [{}] entity metadata...", (Object)tableName);
        String dbName = this.stepMeta.getDatabaseName();
        List<Map<String, Object>> schemas = this.getSchemaByDatabase(dbName);
        Map<String, Object> schemaMap = null;
        for (Map<String, Object> schemaObject : schemas) {
            String name = schemaObject.get("name").toString();
            if (!name.equals(schemaName)) continue;
            schemaMap = schemaObject;
        }
        this.getTableAndColumnByDatabaseName(dbName, schemaMap, schemaName, tableName);
    }

    public String getDatabaseType() {
        return this.stepMeta.getDatabaseType();
    }

    private List<Map<String, Object>> getSchemaByDatabase(String dbName) throws Exception {
        MppStepMeta currentStepMeta = new MppStepMeta();
        BeanUtils.copyProperties((Object)this.stepMeta, (Object)currentStepMeta);
        currentStepMeta.setDatabaseName(dbName);
        MppMetadataClient currentClient = new MppMetadataClient(currentStepMeta);
        List schemaList = currentClient.queryAllSchema();
        return schemaList;
    }

    private void getTableAndColumnByDatabaseName(String dbName, Map<String, Object> schemaObject, String targetSchemaName, String targetTableName) throws Exception {
        MppStepMeta currentStepMeta = new MppStepMeta();
        BeanUtils.copyProperties(this.stepMeta, currentStepMeta);
        currentStepMeta.setDatabaseName(dbName);
        MppMetadataClient currentClient = new MppMetadataClient(currentStepMeta);
        List<Map<String, Object>> role = currentClient.queryRoles(currentStepMeta.getUserName());
        if (role.isEmpty()) {
            LOG.info("用户角色{}不存在", currentStepMeta.getUserName());
        } else {
            Boolean isAdminRole = role.stream().anyMatch((item) -> {
                return (Boolean)Optional.ofNullable((Boolean)item.get("rolsystemadmin")).orElse(false);
            });
            if (schemaObject != null) {
                String schemaName = schemaObject.get("name").toString();
                String schemaId = schemaObject.get("oid").toString();
                if (!Strings.isNotEmpty(targetSchemaName) || targetSchemaName.equals(schemaName)) {
                    String owner = schemaObject.get("owner").toString();
                    String privileges = Objects.isNull(schemaObject.get("privileges")) ? "" : schemaObject.get("privileges").toString();
                    List<String> schemaRoleNames = (List)Arrays.stream(privileges.split(",")).map((item) -> {
                        return item.contains("=U") ? item.substring(0, item.indexOf("=")) : null;
                    }).filter(Objects::nonNull).distinct().collect(Collectors.toList());
                    LOG.info("schema privileges info:{}", privileges);
                    LOG.info("schemaRoleNames:{}", schemaRoleNames);
                    if (isAdminRole || owner.equals(currentStepMeta.getUserName()) || schemaRoleNames.contains("") || schemaRoleNames.contains(currentStepMeta.getUserName())) {
                        String schemaOid = schemaObject.get("oid").toString();
                        List<Map<String, Object>> tableList = currentClient.getTableMetadataBySchemaOid(schemaOid);
                        Iterator var16 = tableList.iterator();

                        while(true) {
                            Map table;
                            String tableName;
                            do {
                                if (!var16.hasNext()) {
                                    return;
                                }

                                table = (Map)var16.next();
                                tableName = (String)table.get("relname");
                            } while(Strings.isNotEmpty(targetTableName) && !targetTableName.equals(tableName));

                            MppTableEntity tableEntity = new MppTableEntity();
                            String tableOid = null;
                            if ("mpp_gauss".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                                tableOid = String.valueOf((Long)table.get("oid"));
                            } else if ("mpp_greenplum".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                                tableOid = (String)table.get("oid");
                            }

                            tableEntity.setDbName(schemaName);
                            tableEntity.setGuid(tableOid);
                            tableEntity.setHasPKKey((Boolean)table.get("relhaspkey"));
                            tableEntity.setKineName((String)table.get("relkind"));
                            tableEntity.setName(tableName);
                            tableEntity.setNameSpace((String)table.get("nspname"));
                            tableEntity.setOwner((String)table.get("rolename"));
                            tableEntity.setParttype((String)table.get("parttype"));
                            tableEntity.setQualifiedName(schemaName + "." + tableName + "@" + currentStepMeta.getDatabaseServer() + ":" + currentStepMeta.getPort() + ":" + dbName);
                            tableEntity.setTableSpace((String)table.get("spcname"));
                            tableEntity.setReltype((String)table.get("typname"));
                            tableEntity.setDbGuid(schemaId);
                            String isTemporary = (String)table.get("relpersistence");
                            if ("t".equals(isTemporary)) {
                                tableEntity.setTemporary(true);
                            } else {
                                tableEntity.setTemporary(false);
                            }

                            Long rowNum;
                            if (!this.ignoreTableSize) {
                                rowNum = currentClient.getTableSize(this.stepMeta.getDatabaseType(), schemaName, tableName);
                                tableEntity.setTableSize(rowNum);
                            }

                            if (!this.ignoreRowNum) {
                                rowNum = currentClient.getTableRowNum(this.stepMeta.getDatabaseType(), schemaName, tableName);
                                tableEntity.setRowNum(rowNum);
                            }

                            if (!this.ignoreDDL) {
                                String ddl = currentClient.getTableDDL(this.stepMeta.getDatabaseType(), schemaName, tableName);
                                tableEntity.setDdl(ddl);
                            }

                            if ("mpp_gauss".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                                tableEntity.setTypeName("gauss_table");
                            } else if ("mpp_greenplum".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                                tableEntity.setTypeName("gp_table");
                            }

                            List<Map<String, Object>> columnList = currentClient.getColumnMetadataByTableOid(tableOid);
                            Iterator var23 = columnList.iterator();

                            while(var23.hasNext()) {
                                Map<String, Object> column = (Map)var23.next();
                                MppColumnEntity columnEntity = new MppColumnEntity();
                                String columnName = String.valueOf(column.get("attname"));
                                if (!FILTER_COLUMN.contains(columnName)) {
                                    String qualifiedName = schemaName + "." + tableName + "." + columnName + "@" + currentStepMeta.getDatabaseServer() + ":" + currentStepMeta.getPort() + ":" + dbName;
                                    columnEntity.setColumnType(String.valueOf(column.get("typname")));
                                    columnEntity.setName(columnName);
                                    columnEntity.setNullAble((Boolean)column.get("attnotnull"));
                                    columnEntity.setQualifiedName(qualifiedName);
                                    columnEntity.setGuid(MD5Util.getMD5(qualifiedName));
                                    columnEntity.setTbName(tableName);
                                    columnEntity.setPosition((Integer)column.get("attnum"));
                                    columnEntity.setColumnTypeLen((Integer)column.get("attlen"));
                                    columnEntity.setTbGuid(tableOid);
                                    if ("mpp_gauss".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                                        columnEntity.setTypeName("gauss_column");
                                    } else if ("mpp_greenplum".equalsIgnoreCase(this.stepMeta.getDatabaseType())) {
                                        columnEntity.setTypeName("gp_column");
                                    }

                                    this.columnEntityList.add(columnEntity);
                                }
                            }

                            tableEntity.setColumnNum(columnList.size());
                            this.tableEntityList.add(tableEntity);
                        }
                    }
                }
            }
        }
    }

    public MppMetadataClient getClient() {
        return this.client;
    }

    public boolean isIgnoreDDL() {
        return this.ignoreDDL;
    }

    public boolean isIgnoreRowNum() {
        return this.ignoreRowNum;
    }

    public boolean isIgnoreTableSize() {
        return this.ignoreTableSize;
    }

    public MppStepMeta getStepMeta() {
        return this.stepMeta;
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

    public void setClient(MppMetadataClient client) {
        this.client = client;
    }

    public void setIgnoreDDL(boolean ignoreDDL) {
        this.ignoreDDL = ignoreDDL;
    }

    public void setIgnoreRowNum(boolean ignoreRowNum) {
        this.ignoreRowNum = ignoreRowNum;
    }

    public void setIgnoreTableSize(boolean ignoreTableSize) {
        this.ignoreTableSize = ignoreTableSize;
    }

    public void setStepMeta(MppStepMeta stepMeta) {
        this.stepMeta = stepMeta;
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
        if (!(o instanceof MppMetadataProxy)) {
            return false;
        }
        MppMetadataProxy other = (MppMetadataProxy)o;
        if (!other.canEqual(this)) {
            return false;
        }
        MppMetadataClient this$client = this.getClient();
        MppMetadataClient other$client = other.getClient();
        if (this$client == null ? other$client != null : !this$client.equals(other$client)) {
            return false;
        }
        if (this.isIgnoreDDL() != other.isIgnoreDDL()) {
            return false;
        }
        if (this.isIgnoreRowNum() != other.isIgnoreRowNum()) {
            return false;
        }
        if (this.isIgnoreTableSize() != other.isIgnoreTableSize()) {
            return false;
        }
        MppStepMeta this$stepMeta = this.getStepMeta();
        MppStepMeta other$stepMeta = other.getStepMeta();
        if (this$stepMeta == null ? other$stepMeta != null : !((Object)this$stepMeta).equals(other$stepMeta)) {
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
        return other instanceof MppMetadataProxy;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        MppMetadataClient $client = this.getClient();
        result = result * 59 + ($client == null ? 43 : $client.hashCode());
        result = result * 59 + (this.isIgnoreDDL() ? 79 : 97);
        result = result * 59 + (this.isIgnoreRowNum() ? 79 : 97);
        result = result * 59 + (this.isIgnoreTableSize() ? 79 : 97);
        MppStepMeta $stepMeta = this.getStepMeta();
        result = result * 59 + ($stepMeta == null ? 43 : ((Object)$stepMeta).hashCode());
        List<MppDatabaseEntity> $databaseEntityList = this.getDatabaseEntityList();
        result = result * 59 + ($databaseEntityList == null ? 43 : ((Object)$databaseEntityList).hashCode());
        List<MppTableEntity> $tableEntityList = this.getTableEntityList();
        result = result * 59 + ($tableEntityList == null ? 43 : ((Object)$tableEntityList).hashCode());
        List<MppColumnEntity> $columnEntityList = this.getColumnEntityList();
        result = result * 59 + ($columnEntityList == null ? 43 : ((Object)$columnEntityList).hashCode());
        return result;
    }

    public String toString() {
        return "MppMetadataProxy(client=" + this.getClient() + ", ignoreDDL=" + this.isIgnoreDDL() + ", ignoreRowNum=" + this.isIgnoreRowNum() + ", ignoreTableSize=" + this.isIgnoreTableSize() + ", stepMeta=" + this.getStepMeta() + ", databaseEntityList=" + this.getDatabaseEntityList() + ", tableEntityList=" + this.getTableEntityList() + ", columnEntityList=" + this.getColumnEntityList() + ")";
    }
}
