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

public class AtlasConfig {
    private static final Logger LOG = LoggerFactory.getLogger(AtlasConfig.class);
    private static final String IGNORE_EXIST = "ignoreExist";
    private static final String LINEAGE_DEPTH = "lineage.depth";
    private static final String AFTER_EXPORT_CLEAR = "after.export.clear";
    private static final String DATABASE_QUALIFIED_NAMES = "dbQualifiedNames";
    private static final String TABLE_QUALIFIED_NAMES = "tbQualifiedNames";
    private static final String CLUSTER_QUALIFIED_NAMES = "clusterQualifiedNames";
    private static final boolean IGNORE_EXIST_DEFAULT_VALUE = true;
    private static final int LINEAGE_DEPTH_DEFAULT_VALUE = 10;
    private static final boolean AFTER_EXPORT_CLEAR_DEFAULT_VALUE = false;
    private PropertiesConfiguration props;

    public AtlasConfig() {
        this("atlas.properties");
    }

    public AtlasConfig(String fileName) {
        try {
            this.props = new PropertiesConfiguration(fileName);
        }
        catch (Exception e) {
            LOG.error("get the config file error", (Throwable)e);
        }
    }

    public boolean getIgnoreExist() {
        try {
            return this.props.getBoolean(IGNORE_EXIST, true);
        }
        catch (Exception e) {
            LOG.error("Gets the ignore exist error, return the default value:{}", (Object)true);
            return true;
        }
    }

    public void setIgnoreExist(boolean ignoreExist) {
        this.props.setProperty(IGNORE_EXIST, (Object)ignoreExist);
    }

    public int getLineageDepth() {
        try {
            return this.props.getInt(LINEAGE_DEPTH, 10);
        }
        catch (Exception e) {
            LOG.error("Gets the lineage depth error, return the default value:{}", (Object)10);
            return 10;
        }
    }

    public void setLineageDepth(int lineageDepth) {
        this.props.setProperty(LINEAGE_DEPTH, (Object)lineageDepth);
    }

    public boolean getExportClear() {
        try {
            return this.props.getBoolean(AFTER_EXPORT_CLEAR, false);
        }
        catch (Exception e) {
            LOG.error("Gets the after export clear error, return the default value:{}", (Object)false);
            return false;
        }
    }

    public void setExportClear(boolean afterExportClear) {
        this.props.setProperty(AFTER_EXPORT_CLEAR, (Object)afterExportClear);
    }

    public String getDbQualifiedNames() {
        try {
            return this.props.getString(DATABASE_QUALIFIED_NAMES, "");
        }
        catch (Exception e) {
            LOG.error("Gets the dbQualifiedNames error, return the default value:{}", (Object)"");
            return "";
        }
    }

    public String getTbQualifiedNames() {
        try {
            return this.props.getString(TABLE_QUALIFIED_NAMES, "");
        }
        catch (Exception e) {
            LOG.error("Gets the tbQualifiedNames error, return the default value:{}", (Object)"");
            return "";
        }
    }

    public String getClusterQualifiedNames() {
        try {
            return this.props.getString(CLUSTER_QUALIFIED_NAMES, "");
        }
        catch (Exception e) {
            LOG.error("Gets the clusterQualifiedNames error, return the default value:{}", (Object)"");
            return "";
        }
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
        if (!(o instanceof AtlasConfig)) {
            return false;
        }
        AtlasConfig other = (AtlasConfig)o;
        if (!other.canEqual(this)) {
            return false;
        }
        PropertiesConfiguration this$props = this.getProps();
        PropertiesConfiguration other$props = other.getProps();
        return !(this$props == null ? other$props != null : !this$props.equals(other$props));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AtlasConfig;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        PropertiesConfiguration $props = this.getProps();
        result = result * 59 + ($props == null ? 43 : $props.hashCode());
        return result;
    }

    public String toString() {
        return "AtlasConfig(props=" + this.getProps() + ")";
    }
}
