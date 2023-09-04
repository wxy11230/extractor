/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class KafkaPartitionDO {
    private Long id;
    private String guid;
    private String topicName;
    private String qualifiedName;
    private int isrNum;
    private int leaderBrokenId;
    private int version;
    private Date recordCreateTime;
    private String recordStatus;

    public Long getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
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

    public Date getRecordCreateTime() {
        return this.recordCreateTime;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
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

    public void setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof KafkaPartitionDO)) {
            return false;
        }
        KafkaPartitionDO other = (KafkaPartitionDO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
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
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        if (this.getIsrNum() != other.getIsrNum()) {
            return false;
        }
        if (this.getLeaderBrokenId() != other.getLeaderBrokenId()) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        Date this$recordCreateTime = this.getRecordCreateTime();
        Date other$recordCreateTime = other.getRecordCreateTime();
        if (this$recordCreateTime == null ? other$recordCreateTime != null : !((Object)this$recordCreateTime).equals(other$recordCreateTime)) {
            return false;
        }
        String this$recordStatus = this.getRecordStatus();
        String other$recordStatus = other.getRecordStatus();
        return !(this$recordStatus == null ? other$recordStatus != null : !this$recordStatus.equals(other$recordStatus));
    }

    protected boolean canEqual(Object other) {
        return other instanceof KafkaPartitionDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $topicName = this.getTopicName();
        result = result * 59 + ($topicName == null ? 43 : $topicName.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        result = result * 59 + this.getIsrNum();
        result = result * 59 + this.getLeaderBrokenId();
        result = result * 59 + this.getVersion();
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        return result;
    }

    public String toString() {
        return "KafkaPartitionDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", topicName=" + this.getTopicName() + ", qualifiedName=" + this.getQualifiedName() + ", isrNum=" + this.getIsrNum() + ", leaderBrokenId=" + this.getLeaderBrokenId() + ", version=" + this.getVersion() + ", recordCreateTime=" + this.getRecordCreateTime() + ", recordStatus=" + this.getRecordStatus() + ")";
    }
}
