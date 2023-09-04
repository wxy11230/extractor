/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.step.MetaDataEntity
 *  com.google.gson.annotations.SerializedName
 */
package com.dtb.metadatahub.step.rdbms.entities;

import com.dtb.metadatahub.step.MetaDataEntity;
import com.dtb.metadatahub.util.SnowFlakeUtil;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class RdbTableEntity
implements MetaDataEntity {
    @SerializedName(value="guid")
    private String guid = null;
    @SerializedName(value="name")
    private String name = null;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    @SerializedName(value="dbName")
    private String dbName = null;
    @SerializedName(value="dbUrl")
    private String dbUrl = null;
    @SerializedName(value="comment")
    private String comment = null;
    @SerializedName(value="typeName")
    private String typeName = null;
    @SerializedName(value="subTypeName")
    private String subTypeName = null;
    @SerializedName(value="tableType")
    private String tableType = null;
    @SerializedName(value="primaryKey")
    private String primaryKey = null;
    @SerializedName(value="columnNum")
    private int columnNum;
    @SerializedName(value="rowNum")
    private Long rowNum;
    @SerializedName(value="tableSize")
    private Long tableSize;
    @SerializedName(value="ddl")
    private String ddl = null;
    @SerializedName(value="status")
    private String tbStatus = null;
    @SerializedName(value="temporary")
    private boolean temporary = false;
    @SerializedName(value="createTime")
    private Long createTime = 0L;
    @SerializedName(value="version")
    private String version = null;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;

    public RdbTableEntity() {
        this.init();
    }

    private void init() {
        this.setGuid(String.valueOf(SnowFlakeUtil.getInstance().nextId()));
        this.setTypeName("rdb_table");
        this.setCreateTime(0L);
        this.setTbStatus("active");
        this.setRecordCreateTime(new Date());
        this.setRecordStatus("1");
        this.setVersion("1.0");
    }

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String toString() {
        return "RdbTableEntity{guid=" + this.guid + "; name=" + this.name + "; comment=" + this.comment + "; typeName=" + this.typeName + "; subTypeName=" + this.subTypeName + "; databaseName=" + this.dbName + "; url=" + this.dbUrl + "; tableType=" + this.tableType + "; primaryKey=" + this.primaryKey + "; columnNum=" + this.columnNum + "; rowNum=" + this.rowNum + "; tableSize=" + this.tableSize + "; ddl=" + this.ddl + "}";
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getDbUrl() {
        return this.dbUrl;
    }

    public String getComment() {
        return this.comment;
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

    public String getTbStatus() {
        return this.tbStatus;
    }

    public boolean isTemporary() {
        return this.temporary;
    }

    public Long getCreateTime() {
        return this.createTime;
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

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public void setTableSize(Long tableSize) {
        this.tableSize = tableSize;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbTableEntity)) {
            return false;
        }
        RdbTableEntity other = (RdbTableEntity)o;
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
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
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
        String this$comment = this.getComment();
        String other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) {
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
        String this$tbStatus = this.getTbStatus();
        String other$tbStatus = other.getTbStatus();
        if (this$tbStatus == null ? other$tbStatus != null : !this$tbStatus.equals(other$tbStatus)) {
            return false;
        }
        if (this.isTemporary() != other.isTemporary()) {
            return false;
        }
        Long this$createTime = this.getCreateTime();
        Long other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
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
        return other instanceof RdbTableEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $dbName = this.getDbName();
        result = result * 59 + ($dbName == null ? 43 : $dbName.hashCode());
        String $dbUrl = this.getDbUrl();
        result = result * 59 + ($dbUrl == null ? 43 : $dbUrl.hashCode());
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $subTypeName = this.getSubTypeName();
        result = result * 59 + ($subTypeName == null ? 43 : $subTypeName.hashCode());
        String $tableType = this.getTableType();
        result = result * 59 + ($tableType == null ? 43 : $tableType.hashCode());
        String $primaryKey = this.getPrimaryKey();
        result = result * 59 + ($primaryKey == null ? 43 : $primaryKey.hashCode());
        result = result * 59 + this.getColumnNum();
        Long $rowNum = this.getRowNum();
        result = result * 59 + ($rowNum == null ? 43 : ((Object)$rowNum).hashCode());
        Long $tableSize = this.getTableSize();
        result = result * 59 + ($tableSize == null ? 43 : ((Object)$tableSize).hashCode());
        String $ddl = this.getDdl();
        result = result * 59 + ($ddl == null ? 43 : $ddl.hashCode());
        String $tbStatus = this.getTbStatus();
        result = result * 59 + ($tbStatus == null ? 43 : $tbStatus.hashCode());
        result = result * 59 + (this.isTemporary() ? 79 : 97);
        Long $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }
}
