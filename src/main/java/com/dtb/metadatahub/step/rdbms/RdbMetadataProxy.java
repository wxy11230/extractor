/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.step.rdbms.RdbMetadataClient
 *  org.apache.logging.log4j.util.Strings
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.rdbms;

import com.dtb.metadatahub.step.rdbms.RdbMetadataClient;
import com.dtb.metadatahub.step.rdbms.RdbStepMeta;
import com.dtb.metadatahub.step.rdbms.entities.RdbColumnEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbDatabaseEntity;
import com.dtb.metadatahub.step.rdbms.entities.RdbTableEntity;
import com.dtb.metadatahub.util.MD5Util;
import com.dtb.metadatahub.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RdbMetadataProxy {
    private static final Logger LOG = LoggerFactory.getLogger(RdbMetadataProxy.class);
    private RdbMetadataClient rdbMetadataClient;
    private Map<String, Integer> metric;
    private boolean ignoreDDL;
    private boolean ignoreRowNum;
    private boolean ignoreTableSize;
    private RdbStepMeta stepMeta;
    private static final List<String> FILTER_DATABASE = Arrays.asList("information_schema", "mysql", "performance_schema", "sys");

    public RdbMetadataProxy(RdbStepMeta stepMeta) {
        try {
            LOG.info("Initialize the RdbMetadataClient");
            this.rdbMetadataClient = new RdbMetadataClient(stepMeta);
            this.stepMeta = stepMeta;
            this.ignoreDDL = this.stepMeta.isIgnoreDDL();
            this.ignoreRowNum = this.stepMeta.isIgnoreRowNum();
            this.ignoreTableSize = this.stepMeta.isIgnoreTableSize();
        }
        catch (Exception e) {
            LOG.error("Initialize the RdbMetadataClient failed!", (Throwable)e);
            this.rdbMetadataClient.close();
        }
    }

    public RdbDatabaseEntity getDatabaseEntity() {
        return this.getDatabaseEntity(null);
    }

    public RdbDatabaseEntity getDatabaseEntity(String databaseName) {
        RdbDatabaseEntity databaseEntity = new RdbDatabaseEntity();
        try {
            LOG.info("====>Start to get the database [{}] entity metadata...", (Object)databaseName);
            DatabaseMetaData databaseMetaData = this.rdbMetadataClient.getDatabaseMetadata();
            String userName = databaseMetaData.getUserName();
            String url = databaseMetaData.getURL();
            String loginUser = this.stepMeta.getUserName();
            String password = this.stepMeta.getPassword();
            String realm = url.substring(url.lastIndexOf("@"));
            String productName = databaseMetaData.getDatabaseProductName();
            String productVersion = databaseMetaData.getDatabaseProductVersion();
            String driverName = databaseMetaData.getDriverName();
            String driverVersion = databaseMetaData.getDriverVersion();
            boolean isReadOnly = databaseMetaData.isReadOnly();
            String name = null;
            name = !StringUtil.isEmpty(databaseName) ? databaseName : this.stepMeta.getDatabaseName();
            String qualifiedName = name + realm;
            databaseEntity.setGuid(MD5Util.getMD5(qualifiedName));
            databaseEntity.setName(name);
            databaseEntity.setUser(userName);
            databaseEntity.setSubTypeName(this.stepMeta.getDatabaseType());
            databaseEntity.setUrl(url);
            databaseEntity.setProductName(productName);
            databaseEntity.setProductVersion(productVersion);
            databaseEntity.setDriverName(driverName);
            databaseEntity.setDriverVersion(driverVersion);
            databaseEntity.setReadOnly(isReadOnly);
            databaseEntity.setQualifiedName(qualifiedName);
            databaseEntity.setUserName(loginUser);
            databaseEntity.setPassword(password);
            ResultSet rs = databaseMetaData.getTableTypes();
            ArrayList<String> tableTypes = new ArrayList<String>();
            while (rs.next()) {
                String tableType = rs.getString(1);
                if (StringUtil.isEmpty(tableType)) continue;
                tableTypes.add(tableType);
            }
            databaseEntity.setTableTypes(tableTypes);
            rs.close();
            LOG.info("Gets the database entity metadata successfully!");
        }
        catch (Exception e) {
            LOG.error("Gets the database entity metadata failed!", (Throwable)e);
            this.rdbMetadataClient.close();
        }
        return databaseEntity;
    }

    public RdbTableEntity getTableEntity(String tableNamePattern) {
        return this.getTableEntity(null, tableNamePattern);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public RdbTableEntity getTableEntity(String schemaName, String tableNamePattern) {
        LOG.info("Start to get the table [{}] entity metadata...", (Object)tableNamePattern);
        RdbTableEntity rdbTableEntity = null;
        Statement psStatement = null;
        try {
            ResultSet rs = this.rdbMetadataClient.getTableMetadata(schemaName, tableNamePattern);
            String subTypeName = this.stepMeta.getDatabaseType();
            String user = this.stepMeta.getUserName();
            while (rs.next()) {
                rdbTableEntity = new RdbTableEntity();
                String tableCat = rs.getString("TABLE_CAT");
                String tableSchemaName = rs.getString("TABLE_SCHEM");
                String tableName = rs.getString("TABLE_NAME");
                String tableType = rs.getString("TABLE_TYPE");
                String remarks = rs.getString("REMARKS");
                rdbTableEntity.setName(tableName);
                String url = this.rdbMetadataClient.getDatabaseMetadata().getURL();
                String realm = url.substring(url.lastIndexOf("@"));
                rdbTableEntity.setDbUrl(url);
                rdbTableEntity.setTableType(tableType);
                rdbTableEntity.setSubTypeName(subTypeName);
                rdbTableEntity.setComment(remarks);
                String qualifiedName = schemaName + "." + tableName + realm;
                rdbTableEntity.setGuid(MD5Util.getMD5(qualifiedName));
                rdbTableEntity.setQualifiedName(qualifiedName);
                ResultSet rsPrimary = this.rdbMetadataClient.getPrimaryKey(tableName);
                String primaryKey = "";
                while (rsPrimary.next()) {
                    primaryKey = rsPrimary.getString("PK_NAME");
                }
                rdbTableEntity.setPrimaryKey(primaryKey);
                rsPrimary.close();
                if (subTypeName.equalsIgnoreCase("rdb_oracle")) {
                    rdbTableEntity.setDbName(tableSchemaName);
                } else if (subTypeName.equalsIgnoreCase("rdb_mysql")) {
                    rdbTableEntity.setDbName(tableCat);
                } else if (subTypeName.equalsIgnoreCase("rdb_postgresql")) {
                    rdbTableEntity.setDbName(schemaName);
                }
                if (!this.ignoreTableSize && !tableName.startsWith("V_$")) {
                    Long tableSize = this.getTableSize(schemaName, tableName, subTypeName);
                    LOG.debug("Gets the table [{}]'s table size is: {}", (Object)tableNamePattern, (Object)tableSize);
                    rdbTableEntity.setTableSize(tableSize == -1L ? null : tableSize);
                }
                if (!this.ignoreRowNum && !tableName.startsWith("V_$")) {
                    Long rowNum = this.getTableRowNum(schemaName, tableName, subTypeName);
                    LOG.debug("Gets the table [{}]'s row num is: {}", (Object)tableNamePattern, (Object)rowNum);
                    rdbTableEntity.setRowNum(rowNum == -1L ? null : rowNum);
                }
                if (this.ignoreDDL) continue;
                String ddl = this.getTableDDL(schemaName, tableName, subTypeName);
                LOG.debug("Gets the table [{}]'s DDL is: {}", (Object)tableNamePattern, (Object)ddl);
                rdbTableEntity.setDdl(ddl);
            }
            rs.close();
            LOG.info("Gets the table [{}] entity metadata successfully!", (Object)tableNamePattern);
        }
        catch (Exception e) {
            LOG.error("Gets the table [{}] entity metadata failed!", (Object)tableNamePattern, (Object)e);
            this.rdbMetadataClient.close();
        }
        finally {
            try {
                if (psStatement != null) {
                    psStatement.close();
                }
            }
            catch (SQLException e) {
                LOG.error("Close the PreparedStatement error!", (Throwable)e);
            }
        }
        return rdbTableEntity;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Long getTableRowNum(String schemaName, String tableName, String subTypeName) {
        Long rowNum = -1L;
        if (!StringUtil.isEmpty(tableName) && !StringUtil.isEmpty(subTypeName)) {
            String sql = "";
            PreparedStatement psStatement = null;

            try {
                if (subTypeName.equalsIgnoreCase("rdb_oracle")) {
                    if (!StringUtil.isEmpty(schemaName)) {
                        sql = String.format("SELECT count(1) FROM %s.%s", schemaName.toUpperCase(), tableName.toUpperCase());
                    } else {
                        sql = String.format("SELECT count(1) FROM %s", tableName.toUpperCase());
                    }
                } else if (subTypeName.equalsIgnoreCase("rdb_mysql")) {
                    if (!StringUtil.isEmpty(schemaName)) {
                        sql = String.format("SELECT table_rows FROM information_schema.tables WHERE table_schema = '%s' AND table_name = '%s'", schemaName, tableName);
                    } else {
                        sql = String.format("SELECT table_rows FROM information_schema.tables WHERE table_name = '%s'", tableName);
                    }
                }

                psStatement = this.rdbMetadataClient.getConnection().prepareStatement(sql);

                ResultSet rsRowNum;
                for(rsRowNum = psStatement.executeQuery(); rsRowNum.next(); rowNum = rsRowNum.getLong(1)) {
                }

                rsRowNum.close();
            } catch (Exception var16) {
                LOG.error("Gets {} row num failed!", tableName, var16);
            } finally {
                try {
                    if (psStatement != null) {
                        psStatement.close();
                    }
                } catch (SQLException var15) {
                    LOG.error("Close the PreparedStatement error!", var15);
                }

            }
        }
        return rowNum;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Long getTableSize(String schemaName, String tableName, String subTypeName) {
        Long tableSize = -1L;
        if (!StringUtil.isEmpty(tableName) && !StringUtil.isEmpty(subTypeName)) {
            String sql = "";
            PreparedStatement psStatement = null;

            try {
                if (subTypeName.equalsIgnoreCase("rdb_oracle")) {
                    if (!StringUtil.isEmpty(schemaName)) {
                        sql = String.format("SELECT num_rows * avg_row_len FROM all_tables WHERE tablespace_name = '%s' AND table_name = '%s'", schemaName.toUpperCase(), tableName.toUpperCase());
                    } else {
                        sql = String.format("SELECT num_rows * avg_row_len FROM all_tables WHERE table_name = '%s'", tableName.toUpperCase());
                    }
                } else if (subTypeName.equalsIgnoreCase("rdb_mysql")) {
                    if (!StringUtil.isEmpty(schemaName)) {
                        sql = String.format("SELECT data_length + index_length FROM information_schema.tables WHERE table_schema = '%s' AND table_name = '%s'", schemaName, tableName);
                    } else {
                        sql = String.format("SELECT data_length + index_length FROM information_schema.tables WHERE table_name = '%s'", tableName);
                    }
                }

                psStatement = this.rdbMetadataClient.getConnection().prepareStatement(sql);

                ResultSet rsRowNum;
                for(rsRowNum = psStatement.executeQuery(); rsRowNum.next(); tableSize = rsRowNum.getLong(1)) {
                }

                rsRowNum.close();
            } catch (Exception var16) {
                LOG.error("Gets {} table size failed!", tableName, var16);
            } finally {
                try {
                    if (psStatement != null) {
                        psStatement.close();
                    }
                } catch (SQLException var15) {
                    LOG.error("Close the PreparedStatement error!", var15);
                }

            }
        }

        return tableSize;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String getTableDDL(String schemaName, String tableName, String subTypeName) {
        String ddl = null;
        if (!StringUtil.isEmpty(tableName) && !StringUtil.isEmpty(subTypeName)) {
            String sql = "";
            PreparedStatement psStatement = null;

            try {
                ResultSet rsDdl;
                if (subTypeName.equalsIgnoreCase("rdb_oracle")) {
                    if (!StringUtil.isEmpty(schemaName)) {
                        sql = String.format("SELECT DBMS_METADATA.GET_DDL('TABLE', '%s', '%s') FROM DUAL", tableName.toUpperCase(), schemaName.toUpperCase());
                    } else {
                        sql = String.format("SELECT DBMS_METADATA.GET_DDL('TABLE', '%s') FROM DUAL", tableName.toUpperCase());
                    }

                    psStatement = this.rdbMetadataClient.getConnection().prepareStatement(sql);

                    for(rsDdl = psStatement.executeQuery(); rsDdl.next(); ddl = rsDdl.getString(1)) {
                    }

                    rsDdl.close();
                } else if (subTypeName.equalsIgnoreCase("rdb_mysql")) {
                    if (!StringUtil.isEmpty(schemaName)) {
                        sql = String.format("SHOW CREATE TABLE %s.%s", schemaName, tableName);
                    } else {
                        sql = String.format("SHOW CREATE TABLE %s", tableName);
                    }

                    psStatement = this.rdbMetadataClient.getConnection().prepareStatement(sql);

                    for(rsDdl = psStatement.executeQuery(); rsDdl.next(); ddl = rsDdl.getString(2)) {
                    }

                    rsDdl.close();
                }
            } catch (Exception var16) {
                LOG.error("Gets {} DDL information failed!", tableName, var16);
            } finally {
                try {
                    if (psStatement != null) {
                        psStatement.close();
                    }
                } catch (SQLException var15) {
                    LOG.error("Close the PreparedStatement error!", var15);
                }

            }
        }

        return ddl;
    }

    public List<RdbTableEntity> getTablesEntities(String databaseName) {
        ArrayList<RdbTableEntity> rdbTableEntities = new ArrayList<RdbTableEntity>();
        List<String> tableNames = this.getAllTableAndViewList(databaseName);
        for (String tableName : tableNames) {
            RdbTableEntity rdbTableEntity = this.getTableEntity(databaseName, tableName);
            if (rdbTableEntity == null) continue;
            rdbTableEntities.add(rdbTableEntity);
        }
        return rdbTableEntities;
    }

    public List<RdbColumnEntity> getAllDatabaseColumnsEntites(String databaseName) {
        ArrayList<RdbColumnEntity> columnEntityList = new ArrayList<RdbColumnEntity>();
        List<String> tableNames = this.getAllTableAndViewList(databaseName);
        for (String tableName : tableNames) {
            List<RdbColumnEntity> rbdColumnEntityList = this.getTableColumnsEntities(tableName);
            if (rbdColumnEntityList.size() <= 0) continue;
            columnEntityList.addAll(rbdColumnEntityList);
        }
        return columnEntityList;
    }

    public List<RdbColumnEntity> getTableColumnsEntities(String tableName) {
        return this.getTableColumnsEntities(null, tableName);
    }

    public List<RdbColumnEntity> getTableColumnsEntities(String schemaName, String tableName) {
        String subTypeName = this.stepMeta.getDatabaseType();
        if (subTypeName.equalsIgnoreCase("rdb_oracle")) {
            return this.getTableColumnsEntities(null, schemaName, tableName);
        }
        return this.getTableColumnsEntities(schemaName, null, tableName);
    }

    public List<RdbColumnEntity> getTableColumnsEntities(String catalogName, String schemaName, String tableName) {
        ArrayList<RdbColumnEntity> columnEntityList = new ArrayList<RdbColumnEntity>();
        try {
            String dbName;
            ResultSet rs = this.rdbMetadataClient.getColumnsMetadata(catalogName, schemaName, tableName);
            String subTypeName = this.stepMeta.getDatabaseType();
            String string = dbName = Strings.isBlank((String)catalogName) ? schemaName : catalogName;
            while (rs.next()) {
                String tableCat = rs.getString("TABLE_CAT");
                String tableSchema = rs.getString("TABLE_SCHEM");
                String rsTableName = rs.getString("TABLE_NAME");
                String columnName = rs.getString("COLUMN_NAME");
                int dataType = rs.getInt("DATA_TYPE");
                String dataTypeName = rs.getString("TYPE_NAME");
                int columnSize = rs.getInt("COLUMN_SIZE");
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");
                String url = this.rdbMetadataClient.getDatabaseMetadata().getURL();
                String realm = url.substring(url.lastIndexOf("@"));
                String remarks = rs.getString("REMARKS");
                String columnDef = rs.getString("COLUMN_DEF");
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");
                String isNullAble = rs.getString("IS_NULLABLE");
                RdbColumnEntity columnEntity = new RdbColumnEntity();
                String qualifiedName = dbName + "." + tableName + "." + columnName + realm;
                columnEntity.setGuid(MD5Util.getMD5(qualifiedName));
                columnEntity.setName(columnName);
                columnEntity.setDataType(dataType);
                columnEntity.setDataTypeName(dataTypeName);
                columnEntity.setColumnSize(columnSize);
                columnEntity.setDecimalDigits(decimalDigits);
                columnEntity.setColumnDef(columnDef);
                columnEntity.setComment(remarks);
                columnEntity.setNullAble(isNullAble);
                columnEntity.setOrdinalPosition(ordinalPosition);
                columnEntity.setCharOctetLength(charOctetLength);
                columnEntity.setNumPrecRadix(numPrecRadix);
                columnEntity.setTableName(rsTableName);
                columnEntity.setSubTypeName(subTypeName);
                columnEntity.setQualifiedName(qualifiedName);
                if (subTypeName.equalsIgnoreCase("rdb_oracle")) {
                    columnEntity.setDatabaseName(tableSchema);
                } else if (subTypeName.equalsIgnoreCase("rdb_mysql") || subTypeName.equalsIgnoreCase("rdb_sqlserver")) {
                    columnEntity.setDatabaseName(tableCat);
                } else {
                    columnEntity.setDatabaseName(tableCat);
                }
                ResultSet rsPrimary = this.rdbMetadataClient.getPrimaryKey(columnEntity.getDatabaseName(), tableName);
                String pryColumnName = "";
                while (rsPrimary.next()) {
                    pryColumnName = rsPrimary.getString("COLUMN_NAME");
                    if (!(!StringUtil.isEmpty(pryColumnName) & columnName.equalsIgnoreCase(pryColumnName))) continue;
                    columnEntity.setPrimaryKey(true);
                }
                rsPrimary.close();
                columnEntityList.add(columnEntity);
            }
            rs.close();
        }
        catch (Exception e) {
            LOG.error("Gets the table [{}] column entity metadata failed!", (Object)tableName, (Object)e);
            this.rdbMetadataClient.close();
        }
        return columnEntityList;
    }

    public List<String> getDatabaseList() {
        ArrayList<String> databaseList = new ArrayList<String>();
        String databaseType = this.getDatabaseType();
        try {
            ResultSet rs = null;
            if (databaseType.equalsIgnoreCase("rdb_oracle")) {
                rs = this.rdbMetadataClient.getSchemas();
                while (rs.next()) {
                    String name = rs.getString("TABLE_SCHEM");
                    databaseList.add(name);
                }
            } else if (databaseType.equalsIgnoreCase("rdb_mysql")) {
                rs = this.rdbMetadataClient.getCatalogs();
                while (rs.next()) {
                    String name = rs.getString("TABLE_CAT");
                    if (FILTER_DATABASE.contains(name)) continue;
                    databaseList.add(name);
                }
            } else if (databaseType.equalsIgnoreCase("rdb_postgresql")) {
                rs = this.rdbMetadataClient.getCatalogs();
                while (rs.next()) {
                    String name = rs.getString("TABLE_CAT");
                    databaseList.add(name);
                }
                rs.close();
            }
        }
        catch (Exception e) {
            LOG.error("Gets the database list failed!", (Throwable)e);
            this.rdbMetadataClient.close();
        }
        return databaseList;
    }

    public List<String> getAllTableAndViewList() {
        return this.getAllTableAndViewList(null);
    }

    public List<String> getAllTableAndViewList(String databaseName) {
        ArrayList<String> tableList = new ArrayList<String>();
        try {
            ResultSet rs = this.rdbMetadataClient.getTableAndViews(databaseName);
            while (rs.next()) {
                String name = rs.getString("TABLE_NAME");
                tableList.add(name);
            }
            rs.close();
        }
        catch (Exception e) {
            LOG.error("Gets the table and view list failed!", (Throwable)e);
            this.rdbMetadataClient.close();
        }
        return tableList;
    }

    public List<String> getSchemaList() {
        ArrayList<String> schemaList = new ArrayList<String>();
        try {
            ResultSet rs = this.rdbMetadataClient.getSchemas();
            while (rs.next()) {
                String tableSchem = rs.getString("TABLE_SCHEM");
                schemaList.add(tableSchem);
            }
            rs.close();
        }
        catch (Exception e) {
            LOG.error("Gets the table and view list failed!", (Throwable)e);
            this.rdbMetadataClient.close();
        }
        return schemaList;
    }

    public List<String> getCatalogList() {
        ArrayList<String> catalogList = new ArrayList<String>();
        try {
            ResultSet rs = this.rdbMetadataClient.getCatalogs();
            while (rs.next()) {
                String tableSchem = rs.getString("TABLE_CAT");
                catalogList.add(tableSchem);
            }
            rs.close();
        }
        catch (Exception e) {
            LOG.error("Gets the table and view list failed!", (Throwable)e);
            this.rdbMetadataClient.close();
        }
        return catalogList;
    }

    public String getDatabaseType() {
        return this.stepMeta.getDatabaseType();
    }

    public RdbMetadataClient getRdbMetadataClient() {
        return this.rdbMetadataClient;
    }

    public Map<String, Integer> getMetric() {
        return this.metric;
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

    public RdbStepMeta getStepMeta() {
        return this.stepMeta;
    }

    public void setRdbMetadataClient(RdbMetadataClient rdbMetadataClient) {
        this.rdbMetadataClient = rdbMetadataClient;
    }

    public void setMetric(Map<String, Integer> metric) {
        this.metric = metric;
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

    public void setStepMeta(RdbStepMeta stepMeta) {
        this.stepMeta = stepMeta;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbMetadataProxy)) {
            return false;
        }
        RdbMetadataProxy other = (RdbMetadataProxy)o;
        if (!other.canEqual(this)) {
            return false;
        }
        RdbMetadataClient this$rdbMetadataClient = this.getRdbMetadataClient();
        RdbMetadataClient other$rdbMetadataClient = other.getRdbMetadataClient();
        if (this$rdbMetadataClient == null ? other$rdbMetadataClient != null : !this$rdbMetadataClient.equals(other$rdbMetadataClient)) {
            return false;
        }
        Map<String, Integer> this$metric = this.getMetric();
        Map<String, Integer> other$metric = other.getMetric();
        if (this$metric == null ? other$metric != null : !((Object)this$metric).equals(other$metric)) {
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
        RdbStepMeta this$stepMeta = this.getStepMeta();
        RdbStepMeta other$stepMeta = other.getStepMeta();
        return !(this$stepMeta == null ? other$stepMeta != null : !((Object)this$stepMeta).equals(other$stepMeta));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RdbMetadataProxy;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        RdbMetadataClient $rdbMetadataClient = this.getRdbMetadataClient();
        result = result * 59 + ($rdbMetadataClient == null ? 43 : $rdbMetadataClient.hashCode());
        Map<String, Integer> $metric = this.getMetric();
        result = result * 59 + ($metric == null ? 43 : ((Object)$metric).hashCode());
        result = result * 59 + (this.isIgnoreDDL() ? 79 : 97);
        result = result * 59 + (this.isIgnoreRowNum() ? 79 : 97);
        result = result * 59 + (this.isIgnoreTableSize() ? 79 : 97);
        RdbStepMeta $stepMeta = this.getStepMeta();
        result = result * 59 + ($stepMeta == null ? 43 : ((Object)$stepMeta).hashCode());
        return result;
    }

    public String toString() {
        return "RdbMetadataProxy(rdbMetadataClient=" + this.getRdbMetadataClient() + ", metric=" + this.getMetric() + ", ignoreDDL=" + this.isIgnoreDDL() + ", ignoreRowNum=" + this.isIgnoreRowNum() + ", ignoreTableSize=" + this.isIgnoreTableSize() + ", stepMeta=" + this.getStepMeta() + ")";
    }
}
