/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class HiveDatabaseDO {
    private Long id;
    private String guid;
    private String name;
    private String description;
    private String typeName;
    private String status;
    private String owner;
    private String ownerType;
    private String qualifiedName;
    private String clusterName;
    private String location;
    private Long version;
    private String createBy;
    private Long createTime;
    private String updateBy;
    private Long updateTime;
    private String recordStatus;
    private Date recordCreateTime;

    public Long getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getOwnerType() {
        return this.ownerType;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public String getLocation() {
        return this.location;
    }

    public Long getVersion() {
        return this.version;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public Date getRecordCreateTime() {
        return this.recordCreateTime;
    }

    public HiveDatabaseDO setId(Long id) {
        this.id = id;
        return this;
    }

    public HiveDatabaseDO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public HiveDatabaseDO setName(String name) {
        this.name = name;
        return this;
    }

    public HiveDatabaseDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public HiveDatabaseDO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public HiveDatabaseDO setStatus(String status) {
        this.status = status;
        return this;
    }

    public HiveDatabaseDO setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public HiveDatabaseDO setOwnerType(String ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public HiveDatabaseDO setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        return this;
    }

    public HiveDatabaseDO setClusterName(String clusterName) {
        this.clusterName = clusterName;
        return this;
    }

    public HiveDatabaseDO setLocation(String location) {
        this.location = location;
        return this;
    }

    public HiveDatabaseDO setVersion(Long version) {
        this.version = version;
        return this;
    }

    public HiveDatabaseDO setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public HiveDatabaseDO setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public HiveDatabaseDO setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public HiveDatabaseDO setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public HiveDatabaseDO setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public HiveDatabaseDO setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HiveDatabaseDO)) {
            return false;
        }
        HiveDatabaseDO other = (HiveDatabaseDO)o;
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
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$typeName = this.getTypeName();
        String other$typeName = other.getTypeName();
        if (this$typeName == null ? other$typeName != null : !this$typeName.equals(other$typeName)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$owner = this.getOwner();
        String other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) {
            return false;
        }
        String this$ownerType = this.getOwnerType();
        String other$ownerType = other.getOwnerType();
        if (this$ownerType == null ? other$ownerType != null : !this$ownerType.equals(other$ownerType)) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        String this$clusterName = this.getClusterName();
        String other$clusterName = other.getClusterName();
        if (this$clusterName == null ? other$clusterName != null : !this$clusterName.equals(other$clusterName)) {
            return false;
        }
        String this$location = this.getLocation();
        String other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) {
            return false;
        }
        Long this$version = this.getVersion();
        Long other$version = other.getVersion();
        if (this$version == null ? other$version != null : !((Object)this$version).equals(other$version)) {
            return false;
        }
        String this$createBy = this.getCreateBy();
        String other$createBy = other.getCreateBy();
        if (this$createBy == null ? other$createBy != null : !this$createBy.equals(other$createBy)) {
            return false;
        }
        Long this$createTime = this.getCreateTime();
        Long other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        String this$updateBy = this.getUpdateBy();
        String other$updateBy = other.getUpdateBy();
        if (this$updateBy == null ? other$updateBy != null : !this$updateBy.equals(other$updateBy)) {
            return false;
        }
        Long this$updateTime = this.getUpdateTime();
        Long other$updateTime = other.getUpdateTime();
        if (this$updateTime == null ? other$updateTime != null : !((Object)this$updateTime).equals(other$updateTime)) {
            return false;
        }
        String this$recordStatus = this.getRecordStatus();
        String other$recordStatus = other.getRecordStatus();
        if (this$recordStatus == null ? other$recordStatus != null : !this$recordStatus.equals(other$recordStatus)) {
            return false;
        }
        Date this$recordCreateTime = this.getRecordCreateTime();
        Date other$recordCreateTime = other.getRecordCreateTime();
        return !(this$recordCreateTime == null ? other$recordCreateTime != null : !((Object)this$recordCreateTime).equals(other$recordCreateTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof HiveDatabaseDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $owner = this.getOwner();
        result = result * 59 + ($owner == null ? 43 : $owner.hashCode());
        String $ownerType = this.getOwnerType();
        result = result * 59 + ($ownerType == null ? 43 : $ownerType.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $clusterName = this.getClusterName();
        result = result * 59 + ($clusterName == null ? 43 : $clusterName.hashCode());
        String $location = this.getLocation();
        result = result * 59 + ($location == null ? 43 : $location.hashCode());
        Long $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : ((Object)$version).hashCode());
        String $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Long $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Long $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : ((Object)$updateTime).hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }

    public String toString() {
        return "HiveDatabaseDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", typeName=" + this.getTypeName() + ", status=" + this.getStatus() + ", owner=" + this.getOwner() + ", ownerType=" + this.getOwnerType() + ", qualifiedName=" + this.getQualifiedName() + ", clusterName=" + this.getClusterName() + ", location=" + this.getLocation() + ", version=" + this.getVersion() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
