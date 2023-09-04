/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class KafkaTopicDO {
    private Long id;
    private String guid;
    private String clusterId;
    private String name;
    private String qualifiedName;
    private int version;
    private int partitionNum;
    private Date recordCreateTime;
    private String recordStatus;

    public Long getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getClusterId() {
        return this.clusterId;
    }

    public String getName() {
        return this.name;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public int getVersion() {
        return this.version;
    }

    public int getPartitionNum() {
        return this.partitionNum;
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

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPartitionNum(int partitionNum) {
        this.partitionNum = partitionNum;
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
        if (!(o instanceof KafkaTopicDO)) {
            return false;
        }
        KafkaTopicDO other = (KafkaTopicDO)o;
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
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        if (this.getPartitionNum() != other.getPartitionNum()) {
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
        return other instanceof KafkaTopicDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $clusterId = this.getClusterId();
        result = result * 59 + ($clusterId == null ? 43 : $clusterId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        result = result * 59 + this.getVersion();
        result = result * 59 + this.getPartitionNum();
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        return result;
    }

    public String toString() {
        return "KafkaTopicDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", clusterId=" + this.getClusterId() + ", name=" + this.getName() + ", qualifiedName=" + this.getQualifiedName() + ", version=" + this.getVersion() + ", partitionNum=" + this.getPartitionNum() + ", recordCreateTime=" + this.getRecordCreateTime() + ", recordStatus=" + this.getRecordStatus() + ")";
    }
}
