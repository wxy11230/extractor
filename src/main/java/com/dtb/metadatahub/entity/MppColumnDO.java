/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class MppColumnDO {
    private Long id;
    private String guid;
    private String name;
    private String typeName;
    private String qualifiedName;
    private String tbName;
    private String tbGuid;
    private String columnType;
    private int columnTypeLen;
    private String comment;
    private boolean nullAble;
    private int position;
    private String version;
    private String recordStatus;
    private Date recordCreateTime;

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
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

    public String getTypeName() {
        return this.typeName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getTbName() {
        return this.tbName;
    }

    public String getTbGuid() {
        return this.tbGuid;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public int getColumnTypeLen() {
        return this.columnTypeLen;
    }

    public String getComment() {
        return this.comment;
    }

    public boolean isNullAble() {
        return this.nullAble;
    }

    public int getPosition() {
        return this.position;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public void setTbGuid(String tbGuid) {
        this.tbGuid = tbGuid;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setColumnTypeLen(int columnTypeLen) {
        this.columnTypeLen = columnTypeLen;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPosition(int position) {
        this.position = position;
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
        if (!(o instanceof MppColumnDO)) {
            return false;
        }
        MppColumnDO other = (MppColumnDO)o;
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
        String this$tbName = this.getTbName();
        String other$tbName = other.getTbName();
        if (this$tbName == null ? other$tbName != null : !this$tbName.equals(other$tbName)) {
            return false;
        }
        String this$tbGuid = this.getTbGuid();
        String other$tbGuid = other.getTbGuid();
        if (this$tbGuid == null ? other$tbGuid != null : !this$tbGuid.equals(other$tbGuid)) {
            return false;
        }
        String this$columnType = this.getColumnType();
        String other$columnType = other.getColumnType();
        if (this$columnType == null ? other$columnType != null : !this$columnType.equals(other$columnType)) {
            return false;
        }
        if (this.getColumnTypeLen() != other.getColumnTypeLen()) {
            return false;
        }
        String this$comment = this.getComment();
        String other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) {
            return false;
        }
        if (this.isNullAble() != other.isNullAble()) {
            return false;
        }
        if (this.getPosition() != other.getPosition()) {
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
        return other instanceof MppColumnDO;
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
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $tbName = this.getTbName();
        result = result * 59 + ($tbName == null ? 43 : $tbName.hashCode());
        String $tbGuid = this.getTbGuid();
        result = result * 59 + ($tbGuid == null ? 43 : $tbGuid.hashCode());
        String $columnType = this.getColumnType();
        result = result * 59 + ($columnType == null ? 43 : $columnType.hashCode());
        result = result * 59 + this.getColumnTypeLen();
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        result = result * 59 + (this.isNullAble() ? 79 : 97);
        result = result * 59 + this.getPosition();
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }

    public String toString() {
        return "MppColumnDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", name=" + this.getName() + ", typeName=" + this.getTypeName() + ", qualifiedName=" + this.getQualifiedName() + ", tbName=" + this.getTbName() + ", tbGuid=" + this.getTbGuid() + ", columnType=" + this.getColumnType() + ", columnTypeLen=" + this.getColumnTypeLen() + ", comment=" + this.getComment() + ", nullAble=" + this.isNullAble() + ", position=" + this.getPosition() + ", version=" + this.getVersion() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
