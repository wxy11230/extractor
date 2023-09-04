/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.constant;

public class RdbConstant {
    public static final String RDB_DB_TYPE_NAME = "rdb_db";
    public static final String RDB_TABLE_TYPE_NAME = "rdb_table";
    public static final String RDB_VIEW_TYPE_NAME = "rdb_view";
    public static final String RDB_COLUMN_TYPE_NAME = "rdb_column";
    public static final String RDB_ORACLE_TYPE = "rdb_oracle";
    public static final String RDB_MYSQL_TYPE = "rdb_mysql";
    public static final String RDB_SQLSERVER_TYPE = "rdb_sqlserver";
    public static final String RDB_MARIADB_TYPE = "rdb_mariadb";
    public static final String RDB_POSTGRESQL_TYPE = "rdb_postgresql";
    public static final String ORACLE_URL_PREFIX = "jdbc:oracle:thin:@";
    public static final String ORACLE_URL_SEPARATOR = ":";
    public static final String SQLSERVER_URL_PREFIX = "jdbc:sqlserver://";
    public static final String RDB_ACTIVE_STAUS = "active";
    public static final String RDB_DELETED_STAUS = "deleted";
    public static final String RDB_COLUMN_NULLABLE = "yes";
    public static final String RDB_COLUMN_NULLS = "no";
    public static final String RDB_COLUMN_NULLUNKNOWN = "unknown";
    public static final String RDB_METRIC_DATABASE = "rdb_metric_database";
    public static final String RDB_METRIC_TABLE = "rdb_metric_table";
    public static final String RDB_METRIC_COLUMN = "rdb_metric_column";
    public static final String DEFAULT_StepName = "DEFAULT_RDB_STEP";
    public static final String DATABASE_SERVER = "database_server";
    public static final String DATABASE_TYPE = "database_type";
    public static final String DATABASE_NAME = "database_name";
    public static final String PORT = "database_port";
    public static final String USERNAME = "database_username";
    public static final String PASSWORD = "database_password";
    public static final String IGNORE_DDL = "ignore_ddl";
    public static final String IGNORE_ROWNUM = "ignore_row_num";
    public static final String DATABASE_CONNECT_TIMEOUT = "database_connect_timeout";
    public static final String DATABASE_SOCKET_TIMEOUT = "database_socket_timeout";
    public static boolean DEFAULT_IGNORE_DDL = true;
    public static boolean DEFAULT_IGNORE_ROWNUM = true;
    public static int DEFAULT_CONNECT_TIMEOUT = 60000;
    public static int DEFAULT_SOCKET_TIMEOUT = 600000;
    public static final String MPP_DB_TYPE_NAME = "mpp_db";
    public static final String MPP_TABLE_TYPE_NAME = "mpp_table";
    public static final String MPP_COLUMN_TYPE_NAME = "mpp_column";
    public static final String MPP_GAUSS_TYPE = "rdb_gauss";
    public static final String MPP_GREENPLUM_TYPE = "rdb_greenplum";
}
