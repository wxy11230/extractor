/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class KafkaBrokerDO {
    private Long id;
    private String clusterId;
    private String qualifiedName;
    private Long guid;
    private String host;
    private int port;
    private int version;
    private Date createTime;
    private Date recordCreateTime;
    private String recordStatus;

    public Long getId() {
        return this.id;
    }

    public String getClusterId() {
        return this.clusterId;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public Long getGuid() {
        return this.guid;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public int getVersion() {
        return this.version;
    }

    public Date getCreateTime() {
        return this.createTime;
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

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        if (!(o instanceof KafkaBrokerDO)) {
            return false;
        }
        KafkaBrokerDO other = (KafkaBrokerDO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$clusterId = this.getClusterId();
        String other$clusterId = other.getClusterId();
        if (this$clusterId == null ? other$clusterId != null : !this$clusterId.equals(other$clusterId)) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        Long this$guid = this.getGuid();
        Long other$guid = other.getGuid();
        if (this$guid == null ? other$guid != null : !((Object)this$guid).equals(other$guid)) {
            return false;
        }
        String this$host = this.getHost();
        String other$host = other.getHost();
        if (this$host == null ? other$host != null : !this$host.equals(other$host)) {
            return false;
        }
        if (this.getPort() != other.getPort()) {
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        Date this$createTime = this.getCreateTime();
        Date other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
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
        return other instanceof KafkaBrokerDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $clusterId = this.getClusterId();
        result = result * 59 + ($clusterId == null ? 43 : $clusterId.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        Long $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : ((Object)$guid).hashCode());
        String $host = this.getHost();
        result = result * 59 + ($host == null ? 43 : $host.hashCode());
        result = result * 59 + this.getPort();
        result = result * 59 + this.getVersion();
        Date $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        return result;
    }

    public String toString() {
        return "KafkaBrokerDO(id=" + this.getId() + ", clusterId=" + this.getClusterId() + ", qualifiedName=" + this.getQualifiedName() + ", guid=" + this.getGuid() + ", host=" + this.getHost() + ", port=" + this.getPort() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", recordCreateTime=" + this.getRecordCreateTime() + ", recordStatus=" + this.getRecordStatus() + ")";
    }
}
