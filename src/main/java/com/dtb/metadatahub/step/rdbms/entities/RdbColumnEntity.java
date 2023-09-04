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

public class RdbColumnEntity
implements MetaDataEntity {
    @SerializedName(value="guid")
    private String guid = null;
    @SerializedName(value="name")
    private String name = null;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    @SerializedName(value="typeName")
    private String typeName = null;
    @SerializedName(value="subTypeName")
    private String subTypeName = null;
    @SerializedName(value="dataType")
    private int dataType = -1;
    @SerializedName(value="dataTypeName")
    private String dataTypeName = null;
    @SerializedName(value="columnSize")
    private int columnSize = -1;
    @SerializedName(value="charOctetLength")
    private int charOctetLength = -1;
    @SerializedName(value="decimalDigits")
    private int decimalDigits = -1;
    @SerializedName(value="nullAble")
    private String nullAble;
    @SerializedName(value="comment")
    private String comment = null;
    @SerializedName(value="columnDef")
    private String columnDef = null;
    @SerializedName(value="numPrecRadix")
    private int numPrecRadix = -1;
    @SerializedName(value="isPrimaryKey")
    private boolean isPrimaryKey;
    @SerializedName(value="tableName")
    private String tableName = null;
    @SerializedName(value="databaseName")
    private String databaseName = null;
    @SerializedName(value="ordinalPosition")
    private int ordinalPosition = -1;
    @SerializedName(value="columnStatus")
    private String columnStatus = null;
    @SerializedName(value="version")
    private String version = null;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;

    public RdbColumnEntity() {
        this.init();
    }

    private void init() {
        this.setGuid(String.valueOf(SnowFlakeUtil.getInstance().nextId()));
        this.setTypeName("rdb_column");
        this.setVersion("1.0");
        this.setPrimaryKey(false);
        this.setNullAble("yes");
        this.setColumnStatus("active");
        this.setRecordCreateTime(new Date());
        this.setRecordStatus("1");
        this.setPrimaryKey(false);
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
        return null;
    }

    public String toString() {
        return "RdbColumnEntity{guid=" + this.guid + "; name=" + this.name + "; comment=" + this.comment + "; typeName=" + this.typeName + "; subTypeName=" + this.subTypeName + "; dataTypeName=" + this.dataTypeName + "; columnSize=" + this.columnSize + "; charOctetLength=" + this.charOctetLength + "; isPrimaryKey=" + this.isPrimaryKey() + "; nullable=" + this.nullAble + "; numPrecRadix=" + this.numPrecRadix + "; ordinalPosition=" + this.ordinalPosition + "; tableName=" + this.tableName + "; databaseName=" + this.databaseName + "}";
    }

    public String getSubTypeName() {
        return this.subTypeName;
    }

    public int getDataType() {
        return this.dataType;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
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

    public String getNullAble() {
        return this.nullAble;
    }

    public String getComment() {
        return this.comment;
    }

    public String getColumnDef() {
        return this.columnDef;
    }

    public int getNumPrecRadix() {
        return this.numPrecRadix;
    }

    public boolean isPrimaryKey() {
        return this.isPrimaryKey;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public int getOrdinalPosition() {
        return this.ordinalPosition;
    }

    public String getColumnStatus() {
        return this.columnStatus;
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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void setCharOctetLength(int charOctetLength) {
        this.charOctetLength = charOctetLength;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public void setNumPrecRadix(int numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus;
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
        if (!(o instanceof RdbColumnEntity)) {
            return false;
        }
        RdbColumnEntity other = (RdbColumnEntity)o;
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
        if (this.getDataType() != other.getDataType()) {
            return false;
        }
        String this$dataTypeName = this.getDataTypeName();
        String other$dataTypeName = other.getDataTypeName();
        if (this$dataTypeName == null ? other$dataTypeName != null : !this$dataTypeName.equals(other$dataTypeName)) {
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
        String this$nullAble = this.getNullAble();
        String other$nullAble = other.getNullAble();
        if (this$nullAble == null ? other$nullAble != null : !this$nullAble.equals(other$nullAble)) {
            return false;
        }
        String this$comment = this.getComment();
        String other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) {
            return false;
        }
        String this$columnDef = this.getColumnDef();
        String other$columnDef = other.getColumnDef();
        if (this$columnDef == null ? other$columnDef != null : !this$columnDef.equals(other$columnDef)) {
            return false;
        }
        if (this.getNumPrecRadix() != other.getNumPrecRadix()) {
            return false;
        }
        if (this.isPrimaryKey() != other.isPrimaryKey()) {
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
        if (this.getOrdinalPosition() != other.getOrdinalPosition()) {
            return false;
        }
        String this$columnStatus = this.getColumnStatus();
        String other$columnStatus = other.getColumnStatus();
        if (this$columnStatus == null ? other$columnStatus != null : !this$columnStatus.equals(other$columnStatus)) {
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
        return other instanceof RdbColumnEntity;
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
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $subTypeName = this.getSubTypeName();
        result = result * 59 + ($subTypeName == null ? 43 : $subTypeName.hashCode());
        result = result * 59 + this.getDataType();
        String $dataTypeName = this.getDataTypeName();
        result = result * 59 + ($dataTypeName == null ? 43 : $dataTypeName.hashCode());
        result = result * 59 + this.getColumnSize();
        result = result * 59 + this.getCharOctetLength();
        result = result * 59 + this.getDecimalDigits();
        String $nullAble = this.getNullAble();
        result = result * 59 + ($nullAble == null ? 43 : $nullAble.hashCode());
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        String $columnDef = this.getColumnDef();
        result = result * 59 + ($columnDef == null ? 43 : $columnDef.hashCode());
        result = result * 59 + this.getNumPrecRadix();
        result = result * 59 + (this.isPrimaryKey() ? 79 : 97);
        String $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        String $databaseName = this.getDatabaseName();
        result = result * 59 + ($databaseName == null ? 43 : $databaseName.hashCode());
        result = result * 59 + this.getOrdinalPosition();
        String $columnStatus = this.getColumnStatus();
        result = result * 59 + ($columnStatus == null ? 43 : $columnStatus.hashCode());
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }
}
