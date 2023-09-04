/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class HiveColumnDO {
    private Long id;
    private String guid;
    private String name;
    private String description;
    private String comment;
    private String tableGuid;
    private String tableName;
    private String owner;
    private String dataType;
    private String typeName;
    private String columnType;
    private boolean isPrimarykey;
    private int position;
    private String qualifiedName;
    private String status;
    private Long version;
    private String createBy;
    private Long createTime;
    private String updateBy;
    private Long updateTime;
    private String recordStatus;
    private Date recordCreateTime;

    public void setIsPrimarykey(boolean isPrimarykey) {
        this.isPrimarykey = isPrimarykey;
    }

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

    public String getComment() {
        return this.comment;
    }

    public String getTableGuid() {
        return this.tableGuid;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public boolean isPrimarykey() {
        return this.isPrimarykey;
    }

    public int getPosition() {
        return this.position;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getStatus() {
        return this.status;
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

    public HiveColumnDO setId(Long id) {
        this.id = id;
        return this;
    }

    public HiveColumnDO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public HiveColumnDO setName(String name) {
        this.name = name;
        return this;
    }

    public HiveColumnDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public HiveColumnDO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public HiveColumnDO setTableGuid(String tableGuid) {
        this.tableGuid = tableGuid;
        return this;
    }

    public HiveColumnDO setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public HiveColumnDO setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public HiveColumnDO setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public HiveColumnDO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public HiveColumnDO setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public HiveColumnDO setPosition(int position) {
        this.position = position;
        return this;
    }

    public HiveColumnDO setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        return this;
    }

    public HiveColumnDO setStatus(String status) {
        this.status = status;
        return this;
    }

    public HiveColumnDO setVersion(Long version) {
        this.version = version;
        return this;
    }

    public HiveColumnDO setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public HiveColumnDO setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public HiveColumnDO setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public HiveColumnDO setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public HiveColumnDO setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public HiveColumnDO setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HiveColumnDO)) {
            return false;
        }
        HiveColumnDO other = (HiveColumnDO)o;
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
        String this$comment = this.getComment();
        String other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) {
            return false;
        }
        String this$tableGuid = this.getTableGuid();
        String other$tableGuid = other.getTableGuid();
        if (this$tableGuid == null ? other$tableGuid != null : !this$tableGuid.equals(other$tableGuid)) {
            return false;
        }
        String this$tableName = this.getTableName();
        String other$tableName = other.getTableName();
        if (this$tableName == null ? other$tableName != null : !this$tableName.equals(other$tableName)) {
            return false;
        }
        String this$owner = this.getOwner();
        String other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) {
            return false;
        }
        String this$dataType = this.getDataType();
        String other$dataType = other.getDataType();
        if (this$dataType == null ? other$dataType != null : !this$dataType.equals(other$dataType)) {
            return false;
        }
        String this$typeName = this.getTypeName();
        String other$typeName = other.getTypeName();
        if (this$typeName == null ? other$typeName != null : !this$typeName.equals(other$typeName)) {
            return false;
        }
        String this$columnType = this.getColumnType();
        String other$columnType = other.getColumnType();
        if (this$columnType == null ? other$columnType != null : !this$columnType.equals(other$columnType)) {
            return false;
        }
        if (this.isPrimarykey() != other.isPrimarykey()) {
            return false;
        }
        if (this.getPosition() != other.getPosition()) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
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
        return other instanceof HiveColumnDO;
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
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        String $tableGuid = this.getTableGuid();
        result = result * 59 + ($tableGuid == null ? 43 : $tableGuid.hashCode());
        String $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        String $owner = this.getOwner();
        result = result * 59 + ($owner == null ? 43 : $owner.hashCode());
        String $dataType = this.getDataType();
        result = result * 59 + ($dataType == null ? 43 : $dataType.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $columnType = this.getColumnType();
        result = result * 59 + ($columnType == null ? 43 : $columnType.hashCode());
        result = result * 59 + (this.isPrimarykey() ? 79 : 97);
        result = result * 59 + this.getPosition();
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
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
        return "HiveColumnDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", comment=" + this.getComment() + ", tableGuid=" + this.getTableGuid() + ", tableName=" + this.getTableName() + ", owner=" + this.getOwner() + ", dataType=" + this.getDataType() + ", typeName=" + this.getTypeName() + ", columnType=" + this.getColumnType() + ", isPrimarykey=" + this.isPrimarykey() + ", position=" + this.getPosition() + ", qualifiedName=" + this.getQualifiedName() + ", status=" + this.getStatus() + ", version=" + this.getVersion() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
