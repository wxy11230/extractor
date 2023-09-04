/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.configuration.PropertiesConfiguration
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.config;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RdbConfig {
    private static final Logger LOG = LoggerFactory.getLogger(RdbConfig.class);
    private static final String DATABASE_SERVER = "database_server";
    private static final String DATABASE_TYPE = "database_type";
    private static final String DATABASE_NAME = "database_name";
    private static final String PORT = "database_port";
    private static final String USERNAME = "database_username";
    private static final String PASSWORD = "database_password";
    private static final String IGNORE_DDL = "ignore_ddl";
    private static final String IGNORE_ROWNUM = "ignore_row_num";
    private static final String IGNORE_TABLESIZE = "ignore_table_size";
    private static final String DATABASE_CONNECT_TIMEOUT = "database_connect_timeout";
    private static final String DATABASE_SOCKET_TIMEOUT = "database_socket_timeout";
    private static boolean DEFAULT_IGNORE_DDL = true;
    private static boolean DEFAULT_IGNORE_ROWNUM = true;
    private static boolean DEFAULT_IGNORE_TABLESIZE = true;
    private static int DEFAULT_CONNECT_TIMEOUT = 60000;
    private static int DEFAULT_SOCKET_TIMEOUT = 600000;
    private static String EXTRACT_DATABASE_NAMES = "extract.database.names";
    private static String IGNORE_DATABASE_NAMES = "ignore.database.names";
    private static String EXTRACT_TABLE_NAMES = "extract.table.names";
    private PropertiesConfiguration props;

    public RdbConfig() {
        this("rdbms.conf");
    }

    public RdbConfig(String fileName) {
        try {
            this.props = new PropertiesConfiguration(fileName);
        }
        catch (Exception e) {
            LOG.error("get the config file error", (Throwable)e);
        }
    }

    public String getDatabaseServer() {
        return this.props.getString(DATABASE_SERVER);
    }

    public void setDatabaseServer(String databaseServer) {
        this.props.setProperty(DATABASE_SERVER, (Object)databaseServer);
    }

    public String getDatabaseType() {
        return this.props.getString(DATABASE_TYPE);
    }

    public void setDatabaseType(String databaseType) {
        this.props.setProperty(DATABASE_TYPE, (Object)databaseType);
    }

    public String getDatabaseName() {
        return this.props.getString(DATABASE_NAME);
    }

    public void setDatabaseName(String databaseName) {
        this.props.setProperty(DATABASE_NAME, (Object)databaseName);
    }

    public int getPort() {
        return this.props.getInt(PORT);
    }

    public void setPort(int port) {
        this.props.setProperty(PORT, (Object)port);
    }

    public String getUserName() {
        return this.props.getString(USERNAME);
    }

    public void setUserName(String userName) {
        this.props.setProperty(USERNAME, (Object)userName);
    }

    public String getPassword() {
        return this.props.getString(PASSWORD);
    }

    public void setPassword(String password) {
        this.props.setProperty(PASSWORD, (Object)password);
    }

    public int getConnectTimeout() {
        try {
            return this.props.getInt(DATABASE_CONNECT_TIMEOUT);
        }
        catch (Exception e) {
            LOG.warn("Gets the connect timeout error, return the default value:{}", (Object)DEFAULT_CONNECT_TIMEOUT);
            return DEFAULT_CONNECT_TIMEOUT;
        }
    }

    public void setConnectTimeout(int connectTimeout) {
        this.props.setProperty(DATABASE_CONNECT_TIMEOUT, (Object)connectTimeout);
    }

    public int getSocketTimeout() {
        try {
            return this.props.getInt(DATABASE_SOCKET_TIMEOUT);
        }
        catch (Exception e) {
            LOG.warn("Gets the sockect timeout error, return the default value:{}", (Object)DEFAULT_SOCKET_TIMEOUT);
            return DEFAULT_SOCKET_TIMEOUT;
        }
    }

    public void setSocketTimeout(int socketTimeout) {
        this.props.setProperty(DATABASE_SOCKET_TIMEOUT, (Object)socketTimeout);
    }

    public boolean getIgnoreDDL() {
        try {
            return this.props.getBoolean(IGNORE_DDL);
        }
        catch (Exception e) {
            LOG.warn("Gets the ignore ddl error, return the default value:{}", (Object)DEFAULT_IGNORE_DDL);
            return DEFAULT_IGNORE_DDL;
        }
    }

    public void setIgnoreDDL(boolean ignoreDDL) {
        this.props.setProperty(IGNORE_DDL, (Object)ignoreDDL);
    }

    public boolean getIgnoreRowNum() {
        try {
            return this.props.getBoolean(IGNORE_ROWNUM);
        }
        catch (Exception e) {
            LOG.warn("Gets the ignore rownum error, return the default value:{}", (Object)DEFAULT_IGNORE_ROWNUM);
            return DEFAULT_IGNORE_ROWNUM;
        }
    }

    public void setIgnoreRowNum(boolean ignoreRowNum) {
        this.props.setProperty(IGNORE_ROWNUM, (Object)ignoreRowNum);
    }

    public boolean getIgnoreTableSize() {
        try {
            return this.props.getBoolean(IGNORE_TABLESIZE);
        }
        catch (Exception e) {
            LOG.warn("Gets the ignore table size error, return the default value:{}", (Object)DEFAULT_IGNORE_TABLESIZE);
            return DEFAULT_IGNORE_ROWNUM;
        }
    }

    public void setIgnoreTableSize(boolean ignoreTableSize) {
        this.props.setProperty(IGNORE_TABLESIZE, (Object)ignoreTableSize);
    }

    public void setExtractDatabaseNames(String extractDatabaseNames) {
        this.props.setProperty(EXTRACT_DATABASE_NAMES, (Object)extractDatabaseNames);
    }

    public String getExtractDatabaseNames() {
        return this.props.getString(EXTRACT_DATABASE_NAMES);
    }

    public void setIgnoreDatabaseNames(String ignoreDatabaseNames) {
        this.props.setProperty(IGNORE_DATABASE_NAMES, (Object)ignoreDatabaseNames);
    }

    public String getIgnoreDatabaseNames() {
        return this.props.getString(IGNORE_DATABASE_NAMES);
    }

    public void setExtractTableNames(String extractTableNames) {
        this.props.setProperty(EXTRACT_TABLE_NAMES, (Object)extractTableNames);
    }

    public String getExtractTableNames() {
        return this.props.getString(EXTRACT_TABLE_NAMES);
    }

    public Object getProperty(String key) {
        return this.props.getProperty(key);
    }

    public void addProperty(String key, Object value) {
        this.props.addProperty(key, value);
    }

    public PropertiesConfiguration getProps() {
        return this.props;
    }

    public void setProps(PropertiesConfiguration props) {
        this.props = props;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbConfig)) {
            return false;
        }
        RdbConfig other = (RdbConfig)o;
        if (!other.canEqual(this)) {
            return false;
        }
        PropertiesConfiguration this$props = this.getProps();
        PropertiesConfiguration other$props = other.getProps();
        return !(this$props == null ? other$props != null : !this$props.equals(other$props));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RdbConfig;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        PropertiesConfiguration $props = this.getProps();
        result = result * 59 + ($props == null ? 43 : $props.hashCode());
        return result;
    }

    public String toString() {
        return "RdbConfig(props=" + this.getProps() + ")";
    }
}
