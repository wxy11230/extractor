/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.step.kafka.entities;

public class TopicEntity {
    private String clusterId;
    private String name;
    private int version;
    private int partitionNum;

    public String getClusterId() {
        return this.clusterId;
    }

    public String getName() {
        return this.name;
    }

    public int getVersion() {
        return this.version;
    }

    public int getPartitionNum() {
        return this.partitionNum;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPartitionNum(int partitionNum) {
        this.partitionNum = partitionNum;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TopicEntity)) {
            return false;
        }
        TopicEntity other = (TopicEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$clusterId = this.getClusterId();
        String other$clusterId = other.getClusterId();
        if (this$clusterId == null ? other$clusterId != null : !this$clusterId.equals(other$clusterId)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        return this.getPartitionNum() == other.getPartitionNum();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TopicEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $clusterId = this.getClusterId();
        result = result * 59 + ($clusterId == null ? 43 : $clusterId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        result = result * 59 + this.getVersion();
        result = result * 59 + this.getPartitionNum();
        return result;
    }

    public String toString() {
        return "TopicEntity(clusterId=" + this.getClusterId() + ", name=" + this.getName() + ", version=" + this.getVersion() + ", partitionNum=" + this.getPartitionNum() + ")";
    }
}
