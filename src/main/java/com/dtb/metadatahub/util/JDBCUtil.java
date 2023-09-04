/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCUtil.class);

    public static void close(Connection con) {
        if (con != null) {
            try {
                LOG.debug("close connection " + con + " " + con.hashCode());
                con.close();
            }
            catch (SQLException ex) {
                LOG.debug("Could not close JDBC Connection", (Throwable)ex);
            }
            catch (Throwable ex) {
                LOG.debug("Unexpected exception on closing JDBC Connection", ex);
            }
        }
    }

    public static Connection getConnection(DataSource dataSource) throws Exception {
        Connection con;
        try {
            con = dataSource.getConnection();
            con.isReadOnly();
            LOG.debug("Get the connection " + con + " " + con.hashCode());
        }
        catch (SQLException ex) {
            throw new Exception("Could not get JDBC Connection", ex);
        }
        return con;
    }
}
