/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.config.ConfigBuilder
 *  com.dtb.metadatahub.config.KafkaConfig
 *  com.dtb.metadatahub.step.IStepMeta
 *  com.dtb.metadatahub.step.StepType
 */
package com.dtb.metadatahub.step.kafka;

import com.dtb.metadatahub.config.ConfigBuilder;
import com.dtb.metadatahub.config.KafkaConfig;
import com.dtb.metadatahub.step.IStepMeta;
import com.dtb.metadatahub.step.StepType;
import com.dtb.metadatahub.util.StringUtil;
import java.util.concurrent.atomic.AtomicLong;

public class KafkaStepMeta
implements IStepMeta {
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    private String stepName;
    private String stepGuid;
    private StepType stepType;
    private boolean isConfig;
    private String servers;
    private String ignoreTopics = "";
    private String extratorTopics = "";

    public KafkaStepMeta() {
        this.stepName = "DEFAULT_STEPNAME_MQ";
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.MQ_KAFKA;
        this.isConfig = false;
    }

    public KafkaStepMeta(String stepName) {
        this.stepName = stepName;
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.MQ_KAFKA;
        this.isConfig = false;
    }

    public KafkaStepMeta(String stepName, String fileName) {
        this.stepName = stepName;
        this.stepType = StepType.MQ_KAFKA;
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        KafkaConfig config = new ConfigBuilder().withPropertiesFile(fileName).buildKafkaConfig();
        if (config.getProps() != null) {
            this.isConfig = true;
            this.servers = config.getKafkaServer();
            this.extratorTopics = config.getExtractTopics();
            this.ignoreTopics = config.getIgnoreTopics();
        } else {
            this.isConfig = false;
        }
    }

    public String getStepName() {
        return this.stepName;
    }

    public String getStepGuid() {
        return this.stepGuid;
    }

    public StepType getStepType() {
        return this.stepType;
    }

    public boolean isConfig() {
        return this.isConfig;
    }

    public String getServers() {
        return this.servers;
    }

    public String getIgnoreTopics() {
        return this.ignoreTopics;
    }

    public String getExtratorTopics() {
        return this.extratorTopics;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setStepGuid(String stepGuid) {
        this.stepGuid = stepGuid;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public void setConfig(boolean isConfig) {
        this.isConfig = isConfig;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public void setIgnoreTopics(String ignoreTopics) {
        this.ignoreTopics = ignoreTopics;
    }

    public void setExtratorTopics(String extratorTopics) {
        this.extratorTopics = extratorTopics;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof KafkaStepMeta)) {
            return false;
        }
        KafkaStepMeta other = (KafkaStepMeta)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$stepName = this.getStepName();
        String other$stepName = other.getStepName();
        if (this$stepName == null ? other$stepName != null : !this$stepName.equals(other$stepName)) {
            return false;
        }
        String this$stepGuid = this.getStepGuid();
        String other$stepGuid = other.getStepGuid();
        if (this$stepGuid == null ? other$stepGuid != null : !this$stepGuid.equals(other$stepGuid)) {
            return false;
        }
        StepType this$stepType = this.getStepType();
        StepType other$stepType = other.getStepType();
        if (this$stepType == null ? other$stepType != null : !this$stepType.equals(other$stepType)) {
            return false;
        }
        if (this.isConfig() != other.isConfig()) {
            return false;
        }
        String this$servers = this.getServers();
        String other$servers = other.getServers();
        if (this$servers == null ? other$servers != null : !this$servers.equals(other$servers)) {
            return false;
        }
        String this$ignoreTopics = this.getIgnoreTopics();
        String other$ignoreTopics = other.getIgnoreTopics();
        if (this$ignoreTopics == null ? other$ignoreTopics != null : !this$ignoreTopics.equals(other$ignoreTopics)) {
            return false;
        }
        String this$extratorTopics = this.getExtratorTopics();
        String other$extratorTopics = other.getExtratorTopics();
        return !(this$extratorTopics == null ? other$extratorTopics != null : !this$extratorTopics.equals(other$extratorTopics));
    }

    protected boolean canEqual(Object other) {
        return other instanceof KafkaStepMeta;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $stepName = this.getStepName();
        result = result * 59 + ($stepName == null ? 43 : $stepName.hashCode());
        String $stepGuid = this.getStepGuid();
        result = result * 59 + ($stepGuid == null ? 43 : $stepGuid.hashCode());
        StepType $stepType = this.getStepType();
        result = result * 59 + ($stepType == null ? 43 : $stepType.hashCode());
        result = result * 59 + (this.isConfig() ? 79 : 97);
        String $servers = this.getServers();
        result = result * 59 + ($servers == null ? 43 : $servers.hashCode());
        String $ignoreTopics = this.getIgnoreTopics();
        result = result * 59 + ($ignoreTopics == null ? 43 : $ignoreTopics.hashCode());
        String $extratorTopics = this.getExtratorTopics();
        result = result * 59 + ($extratorTopics == null ? 43 : $extratorTopics.hashCode());
        return result;
    }

    public String toString() {
        return "KafkaStepMeta(stepName=" + this.getStepName() + ", stepGuid=" + this.getStepGuid() + ", stepType=" + this.getStepType() + ", isConfig=" + this.isConfig() + ", servers=" + this.getServers() + ", ignoreTopics=" + this.getIgnoreTopics() + ", extratorTopics=" + this.getExtratorTopics() + ")";
    }
}
