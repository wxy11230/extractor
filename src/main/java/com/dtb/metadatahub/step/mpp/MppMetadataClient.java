/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.zaxxer.hikari.HikariDataSource
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.jdbc.core.JdbcTemplate
 */
package com.dtb.metadatahub.step.mpp;

import com.dtb.metadatahub.step.mpp.MppStepMeta;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class MppMetadataClient {
    private static final Logger LOG = LoggerFactory.getLogger(MppMetadataClient.class);
    private JdbcTemplate jdbcTemplate;
    private HikariDataSource dataSource;
    private MppStepMeta stepMeta;
    private Connection connection;

    public MppMetadataClient(MppStepMeta stepMeta) throws Exception {
        this.stepMeta = stepMeta;
        this.initConnection();
    }

    private void initConnection() throws Exception {
        try {
            String url = "";
            switch (this.stepMeta.getDatabaseType()) {
                case "mpp_gauss": {
                    Class.forName("org.postgresql.Driver");
                    url = "jdbc:postgresql://" + this.stepMeta.getDatabaseServer() + ":" + this.stepMeta.getPort() + "/" + this.stepMeta.getDatabaseName();
                    this.connection = DriverManager.getConnection(url, this.stepMeta.getUserName(), this.stepMeta.getPassword());
                    this.dataSource = new HikariDataSource();
                    this.dataSource.setDriverClassName("org.postgresql.Driver");
                    this.dataSource.setJdbcUrl(url);
                    this.dataSource.setUsername(this.stepMeta.getUserName());
                    this.dataSource.setPassword(this.stepMeta.getPassword());
                    this.dataSource.setConnectionTimeout(5000L);
                    this.dataSource.setMaximumPoolSize(1);
                    this.dataSource.setMinimumIdle(1);
                    this.jdbcTemplate = new JdbcTemplate((DataSource)this.dataSource);
                    return;
                }
                case "mpp_greenplum": {
                    Class.forName("com.pivotal.jdbc.GreenplumDriver");
                    url = "jdbc:pivotal:greenplum://" + this.stepMeta.getDatabaseServer() + ":" + this.stepMeta.getPort() + ";DatabaseName=" + this.stepMeta.getDatabaseName();
                    this.connection = DriverManager.getConnection(url, this.stepMeta.getUserName(), this.stepMeta.getPassword());
                    this.dataSource = new HikariDataSource();
                    this.dataSource.setDriverClassName("com.pivotal.jdbc.GreenplumDriver");
                    this.dataSource.setJdbcUrl(url);
                    this.dataSource.setUsername(this.stepMeta.getUserName());
                    this.dataSource.setPassword(this.stepMeta.getPassword());
                    this.dataSource.setConnectionTimeout(5000L);
                    this.dataSource.setMaximumPoolSize(1);
                    this.dataSource.setMinimumIdle(1);
                    this.jdbcTemplate = new JdbcTemplate((DataSource)this.dataSource);
                    return;
                }
            }
            return;
        }
        catch (Exception e) {
            LOG.error("init connection error", (Throwable)e);
            throw new Exception(e);
        }
    }

    public ResultSet getDatabaseList() throws Exception {
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        rs = databaseMetaData.getCatalogs();
        return rs;
    }

    public List<Map<String, Object>> getDatabaseMetadata() throws Exception {
        return this.getDatabaseMetadata(null);
    }

    public List<Map<String, Object>> getDatabaseMetadata(String databaseName) throws Exception {
        String sql = "select pd.oid, pd.datname, pd.encoding, pd.datistemplate, pd.datallowconn, pd.datconnlimit, pd.datctype, pa.rolname, pt.spcname from pg_database pd left join pg_authid pa on pa.oid = pd.datdba left join pg_tablespace pt on pt.oid = pd.dattablespace where 1=1 ";
        if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{databaseName})) {
            sql = sql + "and pd.datname = '" + databaseName + "'";
        }
        return this.jdbcTemplate.queryForList(sql);
    }

    public Long getDatabaseOidByDatabaseName(String dbName) throws SQLException {
        String sql = "select oid from pg_database where datname = '%s';";
        return (Long)this.jdbcTemplate.queryForObject(String.format(sql, dbName), Long.class);
    }

    public List<String> getAllDatabaseList() {
        String sql = "select datname from pg_database ";
        LOG.info("\u83b7\u53d6greenplum\u6570\u636e\u5e93\u5217\u8868");
        return this.jdbcTemplate.queryForList(sql, String.class);
    }

    public List<Map<String, Object>> queryAllSchema() throws SQLException {
        String sql = "SELECT   n.oid,   n.nspname as name,   pg_catalog.pg_get_userbyid(n.nspowner) as owner,   pg_catalog.array_to_string(n.nspacl, ',') as privileges FROM pg_catalog.pg_namespace n WHERE n.nspname !~ '^pg_' AND n.nspname <> 'information_schema' ORDER BY 1;";
        LOG.info("\u67e5\u8be2\u6240\u6709\u7684schema:{}", (Object)sql);
        return this.jdbcTemplate.queryForList(sql);
    }

    public String getSchemaOidBySchemaName(String schemaName) throws Exception {
        String sql = "select oid from pg_namespace where nspname = '%s'";
        return (String)this.jdbcTemplate.queryForObject(String.format(sql, schemaName), String.class);
    }

    public List<Map<String, Object>> getTableMetadataBySchemaOid(String schemaOid) throws Exception {
        String sql = "";
        sql = "mpp_greenplum".equalsIgnoreCase(this.stepMeta.getDatabaseType()) ? "select pc.oid, pc.relname, pc.relpersistence, pc.relkind, pc.relhaspkey, ptype.typname, pn.nspname, pa.rolname, ptabspace.spcname from pg_class pc left join pg_namespace pn on pn.oid = pc.relnamespace left join pg_type ptype on ptype.oid = pc.reltype left join pg_tablespace ptabspace on ptabspace.oid = pc.reltablespace left join pg_authid pa on pa.oid = pc.relowner left join pg_description des on des.objoid = pc.oid and (des.objsubid=0 or des.objsubid is null) where pc.relnamespace = '%s' and (relkind = 'r' or relkind = 'v')" : "select pc.oid, pc.relname, pc.relpersistence, pc.relkind, pc.relhaspkey, pc.parttype, ptype.typname, pn.nspname, pa.rolname, ptabspace.spcname from pg_class pc left join pg_namespace pn on pn.oid = pc.relnamespace left join pg_type ptype on ptype.oid = pc.reltype left join pg_tablespace ptabspace on ptabspace.oid = pc.reltablespace left join pg_authid pa on pa.oid = pc.relowner left join pg_description des on des.objoid = pc.oid and (des.objsubid=0 or des.objsubid is null) where pc.relnamespace = '%s' and (relkind = 'r' or relkind = 'v')";
        LOG.info("\u67e5\u8be2\u67d0\u4e2aschema\u4e0b\u7684\u6240\u6709\u8868\u5143\u6570\u636e:{}", (Object)String.format(sql, schemaOid));
        return this.jdbcTemplate.queryForList(String.format(sql, schemaOid));
    }

    public List<Map<String, Object>> getTableMetadata(String tableOid) throws Exception {
        String sql = "select pc.relname, pc.relpersistence, pc.relkind, pc.relhaspkey, \npt.typname, pn.nspname, pa.rolename \nfrom pg_class pc \nleft join pg_namespace pn on pn.oid = pc.relnamespace \nleft join pg_type ptype on pt.oid = pc.reltype \nleft join pg_tablespace ptabspace on ptabspace.oid = pc.reltablespace \nleft join pg_authid pa on pa.oid = pc.relowner \nleft join pg_description des on des.objoid = pc.oid and (des.objsubid=0 or des.objsubid is null) \nwhere pc.oid = '%s'";
        return this.jdbcTemplate.queryForList(String.format(sql, tableOid));
    }

    public String getTableOidByTableName(String tableName) throws Exception {
        return this.getTableOidByTableNameAndSchemaName(tableName, null);
    }

    public String getTableOidByTableNameAndSchemaName(String tableName, String schemaName) throws Exception {
        String sql = null;
        if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{schemaName})) {
            String schemaOid = this.getSchemaOidBySchemaName(schemaName);
            sql = "select oid from pg_class where relname = '" + tableName + "' and relnamespace = " + schemaOid;
        } else {
            sql = "select oid from pg_class where relname = '" + tableName + "' limit 1";
        }
        return (String)this.jdbcTemplate.queryForObject(sql, String.class);
    }

    public String getTableDDL(String dbType, String schemaName, String tableName) {
        String sql = null;
        try {
            if ("mpp_gauss".equals(dbType)) {
                sql = "select pg_get_tabledef('\"%s\".\"%s\"')";
                return (String)this.jdbcTemplate.queryForObject(String.format(sql, schemaName, tableName), String.class);
            }
            if ("mpp_greenplum".equals(dbType)) {
                sql = "select public.get_table_structure('\"%s\".\"%s\"')";
                return (String)this.jdbcTemplate.queryForObject(String.format(sql, schemaName, tableName), String.class);
            }
            return null;
        }
        catch (Exception e) {
            LOG.error("\u83b7\u53d6\u8868DDL\u65f6\u51fa\u9519", (Throwable)e);
            e.printStackTrace();
            return null;
        }
    }

    public Long getTableRowNum(String dbType, String schemaName, String tableName) {
        String sql = null;
        try {
            if ("mpp_gauss".equals(dbType) || "mpp_greenplum".equals(dbType)) {
                sql = "select count(1) from \"%s\".\"%s\"";
                return (Long)this.jdbcTemplate.queryForObject(String.format(sql, schemaName, tableName), Long.class);
            }
            return null;
        }
        catch (Exception e) {
            LOG.error("\u83b7\u53d6\u8868\u8bb0\u5f55\u884c\u6570\u65f6\u51fa\u9519", (Throwable)e);
            e.printStackTrace();
            return null;
        }
    }

    public Long getTableSize(String dbType, String schemaName, String tableName) {
        String sql = null;
        try {
            if ("mpp_gauss".equals(dbType) || "mpp_greenplum".equals(dbType)) {
                sql = "select pg_total_relation_size('\"%s\".\"%s\"')";
                return (Long)this.jdbcTemplate.queryForObject(String.format(sql, schemaName, tableName), Long.class);
            }
            return null;
        }
        catch (Exception e) {
            LOG.error("\u83b7\u53d6\u8868\u4f7f\u7528\u7684\u603b\u78c1\u76d8\u7a7a\u95f4\u65f6\u51fa\u9519", (Throwable)e);
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> getColumnMetadataByTableOid(String tableOid) throws SQLException {
        String sql = "select pa.attrelid, pa.attname, pa.attnum, pa.attnotnull, pa.attlen, pc.relname as tableName, pt.typname from pg_attribute pa left join pg_class pc on pc.oid = pa.attrelid left join pg_type pt on pt.oid = pa.atttypid where pa.attrelid = '%s'";
        LOG.info("\u67e5\u8be2\u8868\u4e0b\u7684\u6240\u6709\u5b57\u6bb5\u5143\u6570\u636e:{}", (Object)String.format(sql, tableOid));
        return this.jdbcTemplate.queryForList(String.format(sql, tableOid));
    }

    public List<Map<String, Object>> queryRoles(String rolname) throws SQLException {
        String sql = "select * from pg_roles where rolname = '%s';";
        LOG.info("\u67e5\u8be2\u7528\u6237\u89d2\u8272:{}", (Object)String.format(sql, rolname));
        return this.jdbcTemplate.queryForList(String.format(sql, rolname));
    }

    public MppStepMeta getStepMeta() {
        return this.stepMeta;
    }
}
