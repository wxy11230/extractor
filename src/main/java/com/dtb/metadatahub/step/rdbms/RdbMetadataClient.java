
package com.dtb.metadatahub.step.rdbms;

import com.dtb.metadatahub.step.rdbms.RdbStepMeta;
import com.dtb.metadatahub.util.JDBCUtil;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RdbMetadataClient {
    private static final Logger LOG = LoggerFactory.getLogger(RdbMetadataClient.class);
    private DatabaseMetaData databaseMetaData;
    private Connection connection;
    private RdbStepMeta stepMeta;

    public RdbMetadataClient(RdbStepMeta stepMeta) throws Exception {
        this.stepMeta = stepMeta;
        this.initConnection();
        try {
            this.databaseMetaData = this.connection.getMetaData();
        }
        catch (SQLException e) {
            LOG.error("Could not get DatabaseMeta Object", (Throwable)e);
            this.close();
        }
    }

    private void initConnection() {
        try {
            int connectTimeout = this.stepMeta.getConnectTimeout();
            int socketTimeout = this.stepMeta.getSocketTimeout();
            switch (this.stepMeta.getDatabaseType()) {
                case "rdb_oracle": {
                    OracleDataSource oracleDataSource = new OracleDataSource();
                    oracleDataSource.setURL("jdbc:oracle:thin:@" + this.stepMeta.getDatabaseServer() + ":" + this.stepMeta.getPort() + ":" + this.stepMeta.getDatabaseName());
                    oracleDataSource.setUser(this.stepMeta.getUserName());
                    oracleDataSource.setPassword(this.stepMeta.getPassword());
                    Properties prop = new Properties();
                    prop.setProperty("remarks", "true");
                    prop.setProperty("oracle.net.CONNECT_TIMEOUT", String.valueOf(connectTimeout));
                    prop.setProperty("oracle.jdbc.ReadTimeout", String.valueOf(socketTimeout));
                    oracleDataSource.setConnectionProperties(prop);
                    this.connection = oracleDataSource.getConnection();
                    return;
                }
                case "rdb_mysql": {
                    MysqlDataSource mysqlDataSource = new MysqlDataSource();
                    mysqlDataSource.setServerName(this.stepMeta.getDatabaseServer());
                    mysqlDataSource.setPort(this.stepMeta.getPort());
                    mysqlDataSource.setDatabaseName(this.stepMeta.getDatabaseName());
                    mysqlDataSource.setUser(this.stepMeta.getUserName());
                    mysqlDataSource.setPassword(this.stepMeta.getPassword());
                    mysqlDataSource.setConnectTimeout(connectTimeout);
                    mysqlDataSource.setSocketTimeout(socketTimeout);
                    mysqlDataSource.setUseUnicode(true);
                    mysqlDataSource.setCharacterEncoding("UTF-8");
                    this.connection = mysqlDataSource.getConnection();
                    return;
                }
                case "rdb_postgresql": {
                    PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
                    String serverName = this.stepMeta.getDatabaseServer();
                    pgDataSource.setServerName(serverName);
                    pgDataSource.setDatabaseName(this.stepMeta.getDatabaseName());
                    int portNumber = this.stepMeta.getPort();
                    pgDataSource.setPortNumber(portNumber);
                    pgDataSource.setUser(this.stepMeta.getUserName());
                    pgDataSource.setPassword(this.stepMeta.getPassword());
                    this.connection = pgDataSource.getConnection();
                    return;
                }
            }
            return;
        }
        catch (Exception e) {
            LOG.error("init connection error", (Throwable)e);
            return;
        }
    }

    public DatabaseMetaData getDatabaseMetadata() throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        return databaseMetaData;
    }

    public ResultSet getTableMetadata(String tableName) throws Exception {
        return this.getTableMetadata(null, tableName);
    }

    public ResultSet getTableMetadata(String schemaName, String tableName) throws Exception {
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getTableMetadata(null, schemaName, tableName);
        }
        return this.getTableMetadata(schemaName, null, tableName);
    }

    public ResultSet getTableMetadata(String catalogName, String schemaName, String tableName) throws Exception {
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        rs = databaseMetaData.getTables(catalogName, schemaName, tableName, new String[]{"TABLE", "VIEW"});
        return rs;
    }

    public ResultSet getColumnsMetadata(String tableName) throws Exception {
        return this.getColumnsMetadata(null, tableName);
    }

    public ResultSet getColumnsMetadata(String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getColumnsMetadata(null, schemaName, tableName);
        }
        return this.getColumnsMetadata(schemaName, null, tableName);
    }

    public ResultSet getColumnsMetadata(String catalogName, String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        return databaseMetaData.getColumns(catalogName, schemaName, tableName, "%");
    }

    public ResultSet getCatalogs() throws Exception {
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        rs = databaseMetaData.getCatalogs();
        return rs;
    }

    public ResultSet getSchemas() throws Exception {
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        rs = databaseMetaData.getSchemas();
        return rs;
    }

    public ResultSet getTables() throws Exception {
        return this.getTables(null);
    }

    public ResultSet getTables(String schemaName) throws Exception {
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getTables(null, schemaName);
        }
        return this.getTables(schemaName, null);
    }

    public ResultSet getTables(String catalogName, String schemaName) throws Exception {
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String[] types = new String[]{"TABLE"};
        rs = databaseMetaData.getTables(catalogName, schemaName, "%", types);
        return rs;
    }

    public ResultSet getViews() throws Exception {
        return this.getViews(null);
    }

    public ResultSet getViews(String schemaName) throws Exception {
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getViews(null, schemaName);
        }
        return this.getViews(schemaName, null);
    }

    public ResultSet getViews(String catalogName, String schemaName) throws Exception {
        ResultSet rs = null;
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String[] types = new String[]{"VIEW"};
        rs = databaseMetaData.getTables(catalogName, schemaName, "%", types);
        return rs;
    }

    public ResultSet getTableAndViews() throws Exception {
        return this.getTableAndViews(null);
    }

    public ResultSet getTableAndViews(String schemaName) throws Exception {
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getTableAndViews(null, schemaName);
        }
        return this.getTableAndViews(schemaName, null);
    }

    public ResultSet getTableAndViews(String catalogName, String schemaName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String[] types = new String[]{"TABLE", "VIEW"};
        return databaseMetaData.getTables(catalogName, schemaName, "%", types);
    }

    public ResultSet getPrimaryKey(String tableName) throws Exception {
        return this.getPrimaryKey(null, tableName);
    }

    public ResultSet getPrimaryKey(String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getPrimaryKey(null, schemaName, tableName);
        }
        return this.getPrimaryKey(schemaName, null, tableName);
    }

    public ResultSet getPrimaryKey(String catalogName, String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        return databaseMetaData.getPrimaryKeys(catalogName, schemaName, tableName);
    }

    public ResultSet getForeignKey(String tableName) throws Exception {
        return this.getForeignKey(null, tableName);
    }

    public ResultSet getForeignKey(String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getForeignKey(null, schemaName, tableName);
        }
        return this.getForeignKey(schemaName, null, tableName);
    }

    public ResultSet getForeignKey(String catalogName, String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        return databaseMetaData.getImportedKeys(catalogName, schemaName, tableName);
    }

    public ResultSet getIndex(String tableName) throws Exception {
        return this.getIndex(null, tableName);
    }

    public ResultSet getIndex(String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        String databaseType = this.stepMeta.getDatabaseType();
        if (databaseType.equalsIgnoreCase("rdb_oracle")) {
            return this.getIndex(null, schemaName, tableName);
        }
        return this.getIndex(schemaName, null, tableName);
    }

    public ResultSet getIndex(String catalogName, String schemaName, String tableName) throws Exception {
        DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        return databaseMetaData.getIndexInfo(catalogName, schemaName, tableName, true, true);
    }

    public RdbStepMeta getStepMeta() {
        return this.stepMeta;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public DatabaseMetaData getDatabaseMetaData() {
        return this.databaseMetaData;
    }

    public void close() {
        JDBCUtil.close(this.connection);
    }
}
