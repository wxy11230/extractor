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

public class KafkaConfig {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);
    private static final String KAFKA_SERVER = "zkServers";
    private static final String EXTRACT_TOPICS = "extratorTopics";
    private static String IGNORE_TOPICS = "ignoreTopics";
    private PropertiesConfiguration props;

    public KafkaConfig() {
        this("kafka.conf");
    }

    public KafkaConfig(String fileName) {
        try {
            this.props = new PropertiesConfiguration(fileName);
        }
        catch (Exception e) {
            LOG.error("get the config file error", (Throwable)e);
        }
    }

    public String getKafkaServer() {
        return this.props.getString(KAFKA_SERVER);
    }

    public void setKafkaServer(String kafkaServer) {
        this.props.setProperty(KAFKA_SERVER, (Object)kafkaServer);
    }

    public void setExtractTopics(String extractDatabaseNames) {
        this.props.setProperty(EXTRACT_TOPICS, (Object)extractDatabaseNames);
    }

    public String getExtractTopics() {
        return this.props.getString(EXTRACT_TOPICS);
    }

    public void setIgnoreTopics(String ignoreDatabaseNames) {
        this.props.setProperty(IGNORE_TOPICS, (Object)ignoreDatabaseNames);
    }

    public String getIgnoreTopics() {
        return this.props.getString(IGNORE_TOPICS);
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
        if (!(o instanceof KafkaConfig)) {
            return false;
        }
        KafkaConfig other = (KafkaConfig)o;
        if (!other.canEqual(this)) {
            return false;
        }
        PropertiesConfiguration this$props = this.getProps();
        PropertiesConfiguration other$props = other.getProps();
        return !(this$props == null ? other$props != null : !this$props.equals(other$props));
    }

    protected boolean canEqual(Object other) {
        return other instanceof KafkaConfig;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        PropertiesConfiguration $props = this.getProps();
        result = result * 59 + ($props == null ? 43 : $props.hashCode());
        return result;
    }

    public String toString() {
        return "KafkaConfig(props=" + this.getProps() + ")";
    }
}
