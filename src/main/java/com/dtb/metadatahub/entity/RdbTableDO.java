/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class RdbTableDO {
    private String guid;
    private String name;
    private String comment;
    private String dbName;
    private String dbUrl;
    private String typeName;
    private String subTypeName;
    private String tableType;
    private String primaryKey;
    private String qualifiedName;
    private int columnNum;
    private Long rowNum;
    private Long tableSize;
    private String ddl;
    private Long createTime;
    private String status;
    private String version;
    private String recordStatus;
    private Date recordCreateTime;

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }

    public String getComment() {
        return this.comment;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getDbUrl() {
        return this.dbUrl;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getSubTypeName() {
        return this.subTypeName;
    }

    public String getTableType() {
        return this.tableType;
    }

    public String getPrimaryKey() {
        return this.primaryKey;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public int getColumnNum() {
        return this.columnNum;
    }

    public Long getRowNum() {
        return this.rowNum;
    }

    public Long getTableSize() {
        return this.tableSize;
    }

    public String getDdl() {
        return this.ddl;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public String getStatus() {
        return this.status;
    }

    public String getVersion() {
        return this.version;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public Date getRecordCreateTime() {
        return this.recordCreateTime;
    }

    public RdbTableDO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public RdbTableDO setName(String name) {
        this.name = name;
        return this;
    }

    public RdbTableDO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public RdbTableDO setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public RdbTableDO setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
        return this;
    }

    public RdbTableDO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public RdbTableDO setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
        return this;
    }

    public RdbTableDO setTableType(String tableType) {
        this.tableType = tableType;
        return this;
    }

    public RdbTableDO setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public RdbTableDO setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        return this;
    }

    public RdbTableDO setColumnNum(int columnNum) {
        this.columnNum = columnNum;
        return this;
    }

    public RdbTableDO setRowNum(Long rowNum) {
        this.rowNum = rowNum;
        return this;
    }

    public RdbTableDO setTableSize(Long tableSize) {
        this.tableSize = tableSize;
        return this;
    }

    public RdbTableDO setDdl(String ddl) {
        this.ddl = ddl;
        return this;
    }

    public RdbTableDO setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public RdbTableDO setStatus(String status) {
        this.status = status;
        return this;
    }

    public RdbTableDO setVersion(String version) {
        this.version = version;
        return this;
    }

    public RdbTableDO setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public RdbTableDO setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbTableDO)) {
            return false;
        }
        RdbTableDO other = (RdbTableDO)o;
        if (!other.canEqual(this)) {
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
        String this$comment = this.getComment();
        String other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) {
            return false;
        }
        String this$dbName = this.getDbName();
        String other$dbName = other.getDbName();
        if (this$dbName == null ? other$dbName != null : !this$dbName.equals(other$dbName)) {
            return false;
        }
        String this$dbUrl = this.getDbUrl();
        String other$dbUrl = other.getDbUrl();
        if (this$dbUrl == null ? other$dbUrl != null : !this$dbUrl.equals(other$dbUrl)) {
            return false;
        }
        String this$typeName = this.getTypeName();
        String other$typeName = other.getTypeName();
        if (this$typeName == null ? other$typeName != null : !this$typeName.equals(other$typeName)) {
            return false;
        }
        String this$subTypeName = this.getSubTypeName();
        String other$subTypeName = other.getSubTypeName();
        if (this$subTypeName == null ? other$subTypeName != null : !this$subTypeName.equals(other$subTypeName)) {
            return false;
        }
        String this$tableType = this.getTableType();
        String other$tableType = other.getTableType();
        if (this$tableType == null ? other$tableType != null : !this$tableType.equals(other$tableType)) {
            return false;
        }
        String this$primaryKey = this.getPrimaryKey();
        String other$primaryKey = other.getPrimaryKey();
        if (this$primaryKey == null ? other$primaryKey != null : !this$primaryKey.equals(other$primaryKey)) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        if (this.getColumnNum() != other.getColumnNum()) {
            return false;
        }
        Long this$rowNum = this.getRowNum();
        Long other$rowNum = other.getRowNum();
        if (this$rowNum == null ? other$rowNum != null : !((Object)this$rowNum).equals(other$rowNum)) {
            return false;
        }
        Long this$tableSize = this.getTableSize();
        Long other$tableSize = other.getTableSize();
        if (this$tableSize == null ? other$tableSize != null : !((Object)this$tableSize).equals(other$tableSize)) {
            return false;
        }
        String this$ddl = this.getDdl();
        String other$ddl = other.getDdl();
        if (this$ddl == null ? other$ddl != null : !this$ddl.equals(other$ddl)) {
            return false;
        }
        Long this$createTime = this.getCreateTime();
        Long other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$version = this.getVersion();
        String other$version = other.getVersion();
        if (this$version == null ? other$version != null : !this$version.equals(other$version)) {
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
        return other instanceof RdbTableDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        String $dbName = this.getDbName();
        result = result * 59 + ($dbName == null ? 43 : $dbName.hashCode());
        String $dbUrl = this.getDbUrl();
        result = result * 59 + ($dbUrl == null ? 43 : $dbUrl.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $subTypeName = this.getSubTypeName();
        result = result * 59 + ($subTypeName == null ? 43 : $subTypeName.hashCode());
        String $tableType = this.getTableType();
        result = result * 59 + ($tableType == null ? 43 : $tableType.hashCode());
        String $primaryKey = this.getPrimaryKey();
        result = result * 59 + ($primaryKey == null ? 43 : $primaryKey.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        result = result * 59 + this.getColumnNum();
        Long $rowNum = this.getRowNum();
        result = result * 59 + ($rowNum == null ? 43 : ((Object)$rowNum).hashCode());
        Long $tableSize = this.getTableSize();
        result = result * 59 + ($tableSize == null ? 43 : ((Object)$tableSize).hashCode());
        String $ddl = this.getDdl();
        result = result * 59 + ($ddl == null ? 43 : $ddl.hashCode());
        Long $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }

    public String toString() {
        return "RdbTableDO(guid=" + this.getGuid() + ", name=" + this.getName() + ", comment=" + this.getComment() + ", dbName=" + this.getDbName() + ", dbUrl=" + this.getDbUrl() + ", typeName=" + this.getTypeName() + ", subTypeName=" + this.getSubTypeName() + ", tableType=" + this.getTableType() + ", primaryKey=" + this.getPrimaryKey() + ", qualifiedName=" + this.getQualifiedName() + ", columnNum=" + this.getColumnNum() + ", rowNum=" + this.getRowNum() + ", tableSize=" + this.getTableSize() + ", ddl=" + this.getDdl() + ", createTime=" + this.getCreateTime() + ", status=" + this.getStatus() + ", version=" + this.getVersion() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
