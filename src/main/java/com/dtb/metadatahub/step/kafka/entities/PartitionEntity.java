/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.step.kafka.entities;

public class PartitionEntity {
    private String guid;
    private String topicName;
    private int isrNum;
    private int leaderBrokenId;
    private int version;

    public String getGuid() {
        return this.guid;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public int getIsrNum() {
        return this.isrNum;
    }

    public int getLeaderBrokenId() {
        return this.leaderBrokenId;
    }

    public int getVersion() {
        return this.version;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setIsrNum(int isrNum) {
        this.isrNum = isrNum;
    }

    public void setLeaderBrokenId(int leaderBrokenId) {
        this.leaderBrokenId = leaderBrokenId;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PartitionEntity)) {
            return false;
        }
        PartitionEntity other = (PartitionEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$guid = this.getGuid();
        String other$guid = other.getGuid();
        if (this$guid == null ? other$guid != null : !this$guid.equals(other$guid)) {
            return false;
        }
        String this$topicName = this.getTopicName();
        String other$topicName = other.getTopicName();
        if (this$topicName == null ? other$topicName != null : !this$topicName.equals(other$topicName)) {
            return false;
        }
        if (this.getIsrNum() != other.getIsrNum()) {
            return false;
        }
        if (this.getLeaderBrokenId() != other.getLeaderBrokenId()) {
            return false;
        }
        return this.getVersion() == other.getVersion();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PartitionEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $topicName = this.getTopicName();
        result = result * 59 + ($topicName == null ? 43 : $topicName.hashCode());
        result = result * 59 + this.getIsrNum();
        result = result * 59 + this.getLeaderBrokenId();
        result = result * 59 + this.getVersion();
        return result;
    }

    public String toString() {
        return "PartitionEntity(guid=" + this.getGuid() + ", topicName=" + this.getTopicName() + ", isrNum=" + this.getIsrNum() + ", leaderBrokenId=" + this.getLeaderBrokenId() + ", version=" + this.getVersion() + ")";
    }
}
