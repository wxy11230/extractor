/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.step.kafka.entities;

import java.util.Date;

public class BrokerEntity {
    private String clusterId;
    private Long guid;
    private String host;
    private int port;
    private int version;
    private Date createTime;

    public String getClusterId() {
        return this.clusterId;
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

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
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

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BrokerEntity)) {
            return false;
        }
        BrokerEntity other = (BrokerEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$clusterId = this.getClusterId();
        String other$clusterId = other.getClusterId();
        if (this$clusterId == null ? other$clusterId != null : !this$clusterId.equals(other$clusterId)) {
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
        return !(this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BrokerEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $clusterId = this.getClusterId();
        result = result * 59 + ($clusterId == null ? 43 : $clusterId.hashCode());
        Long $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : ((Object)$guid).hashCode());
        String $host = this.getHost();
        result = result * 59 + ($host == null ? 43 : $host.hashCode());
        result = result * 59 + this.getPort();
        result = result * 59 + this.getVersion();
        Date $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        return result;
    }

    public String toString() {
        return "BrokerEntity(clusterId=" + this.getClusterId() + ", guid=" + this.getGuid() + ", host=" + this.getHost() + ", port=" + this.getPort() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ")";
    }
}
