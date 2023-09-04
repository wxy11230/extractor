/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.config.AtlasConfig
 *  com.dtb.metadatahub.config.ConfigBuilder
 *  com.dtb.metadatahub.step.IStepMeta
 *  com.dtb.metadatahub.step.StepType
 */
package com.dtb.metadatahub.step.atlas;

import com.dtb.metadatahub.config.AtlasConfig;
import com.dtb.metadatahub.config.ConfigBuilder;
import com.dtb.metadatahub.step.IStepMeta;
import com.dtb.metadatahub.step.StepType;
import com.dtb.metadatahub.util.StringUtil;
import java.util.concurrent.atomic.AtomicLong;

public class AtlasStepMeta
implements IStepMeta {
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    private String stepName;
    private String stepGuid;
    private StepType stepType;
    private boolean isConfig;
    private boolean isExportClear;
    private int lineageDepth;
    private boolean ignoreExist;
    private String tbQualifiedNames;
    private String dbQualifiedNames;
    private String clusterQualifiedNames;

    public AtlasStepMeta() {
        this.stepName = "DEFAULT_RDB_STEP";
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.NOSQL_HIVE;
        this.isConfig = false;
        this.isExportClear = false;
        this.lineageDepth = 10;
        this.ignoreExist = true;
    }

    public AtlasStepMeta(String stepName, boolean isExportClear, int lineageDepth, boolean ignoreExist) {
        this.stepName = stepName;
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.NOSQL_HIVE;
        this.isConfig = false;
        this.isExportClear = isExportClear;
        this.lineageDepth = lineageDepth;
        this.ignoreExist = ignoreExist;
    }

    public AtlasStepMeta(String stepName, String fileName) {
        this.stepName = stepName;
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.NOSQL_HIVE;
        AtlasConfig config = new ConfigBuilder().withPropertiesFile(fileName).buildAtlasConfig();
        if (config.getProps() != null) {
            this.isConfig = true;
            this.ignoreExist = config.getIgnoreExist();
            this.isExportClear = config.getExportClear();
            this.lineageDepth = config.getLineageDepth();
            this.dbQualifiedNames = config.getDbQualifiedNames();
            this.tbQualifiedNames = config.getTbQualifiedNames();
            this.clusterQualifiedNames = config.getClusterQualifiedNames();
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

    public boolean isExportClear() {
        return this.isExportClear;
    }

    public int getLineageDepth() {
        return this.lineageDepth;
    }

    public boolean isIgnoreExist() {
        return this.ignoreExist;
    }

    public String getTbQualifiedNames() {
        return this.tbQualifiedNames;
    }

    public String getDbQualifiedNames() {
        return this.dbQualifiedNames;
    }

    public String getClusterQualifiedNames() {
        return this.clusterQualifiedNames;
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

    public void setExportClear(boolean isExportClear) {
        this.isExportClear = isExportClear;
    }

    public void setLineageDepth(int lineageDepth) {
        this.lineageDepth = lineageDepth;
    }

    public void setIgnoreExist(boolean ignoreExist) {
        this.ignoreExist = ignoreExist;
    }

    public void setTbQualifiedNames(String tbQualifiedNames) {
        this.tbQualifiedNames = tbQualifiedNames;
    }

    public void setDbQualifiedNames(String dbQualifiedNames) {
        this.dbQualifiedNames = dbQualifiedNames;
    }

    public void setClusterQualifiedNames(String clusterQualifiedNames) {
        this.clusterQualifiedNames = clusterQualifiedNames;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AtlasStepMeta)) {
            return false;
        }
        AtlasStepMeta other = (AtlasStepMeta)o;
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
        if (this.isExportClear() != other.isExportClear()) {
            return false;
        }
        if (this.getLineageDepth() != other.getLineageDepth()) {
            return false;
        }
        if (this.isIgnoreExist() != other.isIgnoreExist()) {
            return false;
        }
        String this$tbQualifiedNames = this.getTbQualifiedNames();
        String other$tbQualifiedNames = other.getTbQualifiedNames();
        if (this$tbQualifiedNames == null ? other$tbQualifiedNames != null : !this$tbQualifiedNames.equals(other$tbQualifiedNames)) {
            return false;
        }
        String this$dbQualifiedNames = this.getDbQualifiedNames();
        String other$dbQualifiedNames = other.getDbQualifiedNames();
        if (this$dbQualifiedNames == null ? other$dbQualifiedNames != null : !this$dbQualifiedNames.equals(other$dbQualifiedNames)) {
            return false;
        }
        String this$clusterQualifiedNames = this.getClusterQualifiedNames();
        String other$clusterQualifiedNames = other.getClusterQualifiedNames();
        return !(this$clusterQualifiedNames == null ? other$clusterQualifiedNames != null : !this$clusterQualifiedNames.equals(other$clusterQualifiedNames));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AtlasStepMeta;
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
        result = result * 59 + (this.isExportClear() ? 79 : 97);
        result = result * 59 + this.getLineageDepth();
        result = result * 59 + (this.isIgnoreExist() ? 79 : 97);
        String $tbQualifiedNames = this.getTbQualifiedNames();
        result = result * 59 + ($tbQualifiedNames == null ? 43 : $tbQualifiedNames.hashCode());
        String $dbQualifiedNames = this.getDbQualifiedNames();
        result = result * 59 + ($dbQualifiedNames == null ? 43 : $dbQualifiedNames.hashCode());
        String $clusterQualifiedNames = this.getClusterQualifiedNames();
        result = result * 59 + ($clusterQualifiedNames == null ? 43 : $clusterQualifiedNames.hashCode());
        return result;
    }

    public String toString() {
        return "AtlasStepMeta(stepName=" + this.getStepName() + ", stepGuid=" + this.getStepGuid() + ", stepType=" + this.getStepType() + ", isConfig=" + this.isConfig() + ", isExportClear=" + this.isExportClear() + ", lineageDepth=" + this.getLineageDepth() + ", ignoreExist=" + this.isIgnoreExist() + ", tbQualifiedNames=" + this.getTbQualifiedNames() + ", dbQualifiedNames=" + this.getDbQualifiedNames() + ", clusterQualifiedNames=" + this.getClusterQualifiedNames() + ")";
    }
}
