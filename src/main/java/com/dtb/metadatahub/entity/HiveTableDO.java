/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class HiveTableDO {
    private Long id;
    private String guid;
    private String name;
    private String description;
    private String comment;
    private String dbGuid;
    private String dbName;
    private String owner;
    private String tableType;
    private String typeName;
    private String qualifiedName;
    private String status;
    private boolean isTemporary;
    private String queryText;
    private double totalSize;
    private double rowDataSize;
    private long numRows;
    private long numFiles;
    private String partitionKeys;
    private String location;
    private Long version;
    private String createBy;
    private Long createTime;
    private String updateBy;
    private Long updateTime;
    private Long lastAccessTime;
    private String recordStatus;
    private Date recordCreateTime;
    private int columnNum;

    public void setIsTemporary(boolean isTemporary) {
        this.isTemporary = isTemporary;
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

    public String getDbGuid() {
        return this.dbGuid;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getTableType() {
        return this.tableType;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean isTemporary() {
        return this.isTemporary;
    }

    public String getQueryText() {
        return this.queryText;
    }

    public double getTotalSize() {
        return this.totalSize;
    }

    public double getRowDataSize() {
        return this.rowDataSize;
    }

    public long getNumRows() {
        return this.numRows;
    }

    public long getNumFiles() {
        return this.numFiles;
    }

    public String getPartitionKeys() {
        return this.partitionKeys;
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

    public Long getLastAccessTime() {
        return this.lastAccessTime;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public Date getRecordCreateTime() {
        return this.recordCreateTime;
    }

    public int getColumnNum() {
        return this.columnNum;
    }

    public HiveTableDO setId(Long id) {
        this.id = id;
        return this;
    }

    public HiveTableDO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public HiveTableDO setName(String name) {
        this.name = name;
        return this;
    }

    public HiveTableDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public HiveTableDO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public HiveTableDO setDbGuid(String dbGuid) {
        this.dbGuid = dbGuid;
        return this;
    }

    public HiveTableDO setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public HiveTableDO setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public HiveTableDO setTableType(String tableType) {
        this.tableType = tableType;
        return this;
    }

    public HiveTableDO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public HiveTableDO setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        return this;
    }

    public HiveTableDO setStatus(String status) {
        this.status = status;
        return this;
    }

    public HiveTableDO setQueryText(String queryText) {
        this.queryText = queryText;
        return this;
    }

    public HiveTableDO setTotalSize(double totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public HiveTableDO setRowDataSize(double rowDataSize) {
        this.rowDataSize = rowDataSize;
        return this;
    }

    public HiveTableDO setNumRows(long numRows) {
        this.numRows = numRows;
        return this;
    }

    public HiveTableDO setNumFiles(long numFiles) {
        this.numFiles = numFiles;
        return this;
    }

    public HiveTableDO setPartitionKeys(String partitionKeys) {
        this.partitionKeys = partitionKeys;
        return this;
    }

    public HiveTableDO setLocation(String location) {
        this.location = location;
        return this;
    }

    public HiveTableDO setVersion(Long version) {
        this.version = version;
        return this;
    }

    public HiveTableDO setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public HiveTableDO setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public HiveTableDO setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public HiveTableDO setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public HiveTableDO setLastAccessTime(Long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
        return this;
    }

    public HiveTableDO setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public HiveTableDO setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
        return this;
    }

    public HiveTableDO setColumnNum(int columnNum) {
        this.columnNum = columnNum;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HiveTableDO)) {
            return false;
        }
        HiveTableDO other = (HiveTableDO)o;
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
        String this$dbGuid = this.getDbGuid();
        String other$dbGuid = other.getDbGuid();
        if (this$dbGuid == null ? other$dbGuid != null : !this$dbGuid.equals(other$dbGuid)) {
            return false;
        }
        String this$dbName = this.getDbName();
        String other$dbName = other.getDbName();
        if (this$dbName == null ? other$dbName != null : !this$dbName.equals(other$dbName)) {
            return false;
        }
        String this$owner = this.getOwner();
        String other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) {
            return false;
        }
        String this$tableType = this.getTableType();
        String other$tableType = other.getTableType();
        if (this$tableType == null ? other$tableType != null : !this$tableType.equals(other$tableType)) {
            return false;
        }
        String this$typeName = this.getTypeName();
        String other$typeName = other.getTypeName();
        if (this$typeName == null ? other$typeName != null : !this$typeName.equals(other$typeName)) {
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
        if (this.isTemporary() != other.isTemporary()) {
            return false;
        }
        String this$queryText = this.getQueryText();
        String other$queryText = other.getQueryText();
        if (this$queryText == null ? other$queryText != null : !this$queryText.equals(other$queryText)) {
            return false;
        }
        if (Double.compare(this.getTotalSize(), other.getTotalSize()) != 0) {
            return false;
        }
        if (Double.compare(this.getRowDataSize(), other.getRowDataSize()) != 0) {
            return false;
        }
        if (this.getNumRows() != other.getNumRows()) {
            return false;
        }
        if (this.getNumFiles() != other.getNumFiles()) {
            return false;
        }
        String this$partitionKeys = this.getPartitionKeys();
        String other$partitionKeys = other.getPartitionKeys();
        if (this$partitionKeys == null ? other$partitionKeys != null : !this$partitionKeys.equals(other$partitionKeys)) {
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
        Long this$lastAccessTime = this.getLastAccessTime();
        Long other$lastAccessTime = other.getLastAccessTime();
        if (this$lastAccessTime == null ? other$lastAccessTime != null : !((Object)this$lastAccessTime).equals(other$lastAccessTime)) {
            return false;
        }
        String this$recordStatus = this.getRecordStatus();
        String other$recordStatus = other.getRecordStatus();
        if (this$recordStatus == null ? other$recordStatus != null : !this$recordStatus.equals(other$recordStatus)) {
            return false;
        }
        Date this$recordCreateTime = this.getRecordCreateTime();
        Date other$recordCreateTime = other.getRecordCreateTime();
        if (this$recordCreateTime == null ? other$recordCreateTime != null : !((Object)this$recordCreateTime).equals(other$recordCreateTime)) {
            return false;
        }
        return this.getColumnNum() == other.getColumnNum();
    }

    protected boolean canEqual(Object other) {
        return other instanceof HiveTableDO;
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
        String $dbGuid = this.getDbGuid();
        result = result * 59 + ($dbGuid == null ? 43 : $dbGuid.hashCode());
        String $dbName = this.getDbName();
        result = result * 59 + ($dbName == null ? 43 : $dbName.hashCode());
        String $owner = this.getOwner();
        result = result * 59 + ($owner == null ? 43 : $owner.hashCode());
        String $tableType = this.getTableType();
        result = result * 59 + ($tableType == null ? 43 : $tableType.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        result = result * 59 + (this.isTemporary() ? 79 : 97);
        String $queryText = this.getQueryText();
        result = result * 59 + ($queryText == null ? 43 : $queryText.hashCode());
        long $totalSize = Double.doubleToLongBits(this.getTotalSize());
        result = result * 59 + (int)($totalSize >>> 32 ^ $totalSize);
        long $rowDataSize = Double.doubleToLongBits(this.getRowDataSize());
        result = result * 59 + (int)($rowDataSize >>> 32 ^ $rowDataSize);
        long $numRows = this.getNumRows();
        result = result * 59 + (int)($numRows >>> 32 ^ $numRows);
        long $numFiles = this.getNumFiles();
        result = result * 59 + (int)($numFiles >>> 32 ^ $numFiles);
        String $partitionKeys = this.getPartitionKeys();
        result = result * 59 + ($partitionKeys == null ? 43 : $partitionKeys.hashCode());
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
        Long $lastAccessTime = this.getLastAccessTime();
        result = result * 59 + ($lastAccessTime == null ? 43 : ((Object)$lastAccessTime).hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        result = result * 59 + this.getColumnNum();
        return result;
    }

    public String toString() {
        return "HiveTableDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", comment=" + this.getComment() + ", dbGuid=" + this.getDbGuid() + ", dbName=" + this.getDbName() + ", owner=" + this.getOwner() + ", tableType=" + this.getTableType() + ", typeName=" + this.getTypeName() + ", qualifiedName=" + this.getQualifiedName() + ", status=" + this.getStatus() + ", isTemporary=" + this.isTemporary() + ", queryText=" + this.getQueryText() + ", totalSize=" + this.getTotalSize() + ", rowDataSize=" + this.getRowDataSize() + ", numRows=" + this.getNumRows() + ", numFiles=" + this.getNumFiles() + ", partitionKeys=" + this.getPartitionKeys() + ", location=" + this.getLocation() + ", version=" + this.getVersion() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", lastAccessTime=" + this.getLastAccessTime() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ", columnNum=" + this.getColumnNum() + ")";
    }
}
