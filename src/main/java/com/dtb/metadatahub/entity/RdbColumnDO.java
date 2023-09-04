/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class RdbColumnDO {
    private String guid;
    private String name;
    private String comment;
    private String typeName;
    private String subTypeName;
    private String columnType;
    private String dataTypeName;
    private String qualifiedName;
    private int columnSize;
    private int charOctetLength;
    private int decimalDigits;
    private int numPrecRadix;
    private String nullable;
    private String columnDef;
    private boolean isPrimary;
    private int ordinalPosition;
    private String tableName;
    private String databaseName;
    private String status;
    private String version;
    private String recordStatus;
    private Date recordCreateTime;

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }

    public String getComment() {
        return this.comment;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getSubTypeName() {
        return this.subTypeName;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    public int getCharOctetLength() {
        return this.charOctetLength;
    }

    public int getDecimalDigits() {
        return this.decimalDigits;
    }

    public int getNumPrecRadix() {
        return this.numPrecRadix;
    }

    public String getNullable() {
        return this.nullable;
    }

    public String getColumnDef() {
        return this.columnDef;
    }

    public boolean isPrimary() {
        return this.isPrimary;
    }

    public int getOrdinalPosition() {
        return this.ordinalPosition;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getDatabaseName() {
        return this.databaseName;
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

    public RdbColumnDO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public RdbColumnDO setName(String name) {
        this.name = name;
        return this;
    }

    public RdbColumnDO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public RdbColumnDO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public RdbColumnDO setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
        return this;
    }

    public RdbColumnDO setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public RdbColumnDO setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
        return this;
    }

    public RdbColumnDO setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        return this;
    }

    public RdbColumnDO setColumnSize(int columnSize) {
        this.columnSize = columnSize;
        return this;
    }

    public RdbColumnDO setCharOctetLength(int charOctetLength) {
        this.charOctetLength = charOctetLength;
        return this;
    }

    public RdbColumnDO setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
        return this;
    }

    public RdbColumnDO setNumPrecRadix(int numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
        return this;
    }

    public RdbColumnDO setNullable(String nullable) {
        this.nullable = nullable;
        return this;
    }

    public RdbColumnDO setColumnDef(String columnDef) {
        this.columnDef = columnDef;
        return this;
    }

    public RdbColumnDO setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
        return this;
    }

    public RdbColumnDO setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public RdbColumnDO setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public RdbColumnDO setStatus(String status) {
        this.status = status;
        return this;
    }

    public RdbColumnDO setVersion(String version) {
        this.version = version;
        return this;
    }

    public RdbColumnDO setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public RdbColumnDO setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbColumnDO)) {
            return false;
        }
        RdbColumnDO other = (RdbColumnDO)o;
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
        String this$columnType = this.getColumnType();
        String other$columnType = other.getColumnType();
        if (this$columnType == null ? other$columnType != null : !this$columnType.equals(other$columnType)) {
            return false;
        }
        String this$dataTypeName = this.getDataTypeName();
        String other$dataTypeName = other.getDataTypeName();
        if (this$dataTypeName == null ? other$dataTypeName != null : !this$dataTypeName.equals(other$dataTypeName)) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        if (this.getColumnSize() != other.getColumnSize()) {
            return false;
        }
        if (this.getCharOctetLength() != other.getCharOctetLength()) {
            return false;
        }
        if (this.getDecimalDigits() != other.getDecimalDigits()) {
            return false;
        }
        if (this.getNumPrecRadix() != other.getNumPrecRadix()) {
            return false;
        }
        String this$nullable = this.getNullable();
        String other$nullable = other.getNullable();
        if (this$nullable == null ? other$nullable != null : !this$nullable.equals(other$nullable)) {
            return false;
        }
        String this$columnDef = this.getColumnDef();
        String other$columnDef = other.getColumnDef();
        if (this$columnDef == null ? other$columnDef != null : !this$columnDef.equals(other$columnDef)) {
            return false;
        }
        if (this.isPrimary() != other.isPrimary()) {
            return false;
        }
        if (this.getOrdinalPosition() != other.getOrdinalPosition()) {
            return false;
        }
        String this$tableName = this.getTableName();
        String other$tableName = other.getTableName();
        if (this$tableName == null ? other$tableName != null : !this$tableName.equals(other$tableName)) {
            return false;
        }
        String this$databaseName = this.getDatabaseName();
        String other$databaseName = other.getDatabaseName();
        if (this$databaseName == null ? other$databaseName != null : !this$databaseName.equals(other$databaseName)) {
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
        return other instanceof RdbColumnDO;
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
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $subTypeName = this.getSubTypeName();
        result = result * 59 + ($subTypeName == null ? 43 : $subTypeName.hashCode());
        String $columnType = this.getColumnType();
        result = result * 59 + ($columnType == null ? 43 : $columnType.hashCode());
        String $dataTypeName = this.getDataTypeName();
        result = result * 59 + ($dataTypeName == null ? 43 : $dataTypeName.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        result = result * 59 + this.getColumnSize();
        result = result * 59 + this.getCharOctetLength();
        result = result * 59 + this.getDecimalDigits();
        result = result * 59 + this.getNumPrecRadix();
        String $nullable = this.getNullable();
        result = result * 59 + ($nullable == null ? 43 : $nullable.hashCode());
        String $columnDef = this.getColumnDef();
        result = result * 59 + ($columnDef == null ? 43 : $columnDef.hashCode());
        result = result * 59 + (this.isPrimary() ? 79 : 97);
        result = result * 59 + this.getOrdinalPosition();
        String $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        String $databaseName = this.getDatabaseName();
        result = result * 59 + ($databaseName == null ? 43 : $databaseName.hashCode());
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
        return "RdbColumnDO(guid=" + this.getGuid() + ", name=" + this.getName() + ", comment=" + this.getComment() + ", typeName=" + this.getTypeName() + ", subTypeName=" + this.getSubTypeName() + ", columnType=" + this.getColumnType() + ", dataTypeName=" + this.getDataTypeName() + ", qualifiedName=" + this.getQualifiedName() + ", columnSize=" + this.getColumnSize() + ", charOctetLength=" + this.getCharOctetLength() + ", decimalDigits=" + this.getDecimalDigits() + ", numPrecRadix=" + this.getNumPrecRadix() + ", nullable=" + this.getNullable() + ", columnDef=" + this.getColumnDef() + ", isPrimary=" + this.isPrimary() + ", ordinalPosition=" + this.getOrdinalPosition() + ", tableName=" + this.getTableName() + ", databaseName=" + this.getDatabaseName() + ", status=" + this.getStatus() + ", version=" + this.getVersion() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
