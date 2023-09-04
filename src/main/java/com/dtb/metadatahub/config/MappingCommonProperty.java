/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Throwables
 *  org.apache.commons.configuration.ConfigurationException
 *  org.apache.commons.configuration.PropertiesConfiguration
 *  org.springframework.stereotype.Component
 */
package com.dtb.metadatahub.config;

import com.google.common.base.Throwables;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

@Component
public class MappingCommonProperty {
    private PropertiesConfiguration props;

    public MappingCommonProperty() {
        try {
            this.props = new PropertiesConfiguration("mapping.properties");
        }
        catch (ConfigurationException e) {
            e.printStackTrace();
            throw Throwables.propagate((Throwable)e);
        }
    }

    public String getHiveDatabaseConfig() {
        return this.props.getString("hive.database");
    }

    public String getHiveTableConfig() {
        return this.props.getString("hive.table");
    }

    public String getHiveColumnConfig() {
        return this.props.getString("hive.column");
    }

    public String getHiveViewConfig() {
        return this.props.getString("hive.view");
    }

    public String getRdbDatabaseConfig() {
        return this.props.getString("rdb.database");
    }

    public String getRdbTableConfig() {
        return this.props.getString("rdb.table");
    }

    public String getRdbColumnConfig() {
        return this.props.getString("rdb.column");
    }

    public String getMppDatabaseConfig() {
        return this.props.getString("mpp.database");
    }

    public String getMppTableConfig() {
        return this.props.getString("mpp.table");
    }

    public String getMppColumnConfig() {
        return this.props.getString("mpp.column");
    }
}
