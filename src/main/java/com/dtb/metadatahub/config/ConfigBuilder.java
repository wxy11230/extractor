/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.config.AtlasConfig
 *  org.apache.commons.lang.StringUtils
 */
package com.dtb.metadatahub.config;

import com.dtb.metadatahub.config.AtlasConfig;
import com.dtb.metadatahub.config.KafkaConfig;
import com.dtb.metadatahub.config.RdbConfig;
import org.apache.commons.lang.StringUtils;

public class ConfigBuilder {
    private String fileName = null;

    public RdbConfig buildRdbConfig() {
        return StringUtils.isNotBlank((String)this.fileName) ? new RdbConfig(this.fileName) : new RdbConfig();
    }

    public ConfigBuilder withPropertiesFile(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public AtlasConfig buildAtlasConfig() {
        return StringUtils.isNotBlank((String)this.fileName) ? new AtlasConfig(this.fileName) : new AtlasConfig();
    }

    public KafkaConfig buildKafkaConfig() {
        return StringUtils.isNotBlank((String)this.fileName) ? new KafkaConfig(this.fileName) : new KafkaConfig();
    }
}
