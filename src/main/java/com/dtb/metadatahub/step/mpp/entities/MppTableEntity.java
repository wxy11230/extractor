/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.step.MetaDataEntity
 *  com.google.gson.annotations.SerializedName
 */
package com.dtb.metadatahub.step.mpp.entities;

import com.dtb.metadatahub.step.MetaDataEntity;
import com.dtb.metadatahub.util.StringUtil;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class MppTableEntity
implements MetaDataEntity {
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    @SerializedName(value="guid")
    private String guid;
    @SerializedName(value="name")
    private String name;
    @SerializedName(value="qualifiedName")
    private String qualifiedName;
    @SerializedName(value="typeName")
    private String typeName;
    @SerializedName(value="dbName")
    private String dbName;
    @SerializedName(value="dbGuid")
    private String dbGuid;
    @SerializedName(value="parttype")
    private String parttype;
    @SerializedName(value="isTemporary")
    private boolean isTemporary;
    @SerializedName(value="ddl")
    private String ddl;
    @SerializedName(value="nameSpace")
    private String nameSpace;
    @SerializedName(value="owner")
    private String owner;
    @SerializedName(value="tableSpace")
    private String tableSpace;
    @SerializedName(value="columnNum")
    private int columnNum;
    @SerializedName(value="rowNum")
    private Long rowNum;
    @SerializedName(value="tableSize")
    private Long tableSize;
    @SerializedName(value="kineName")
    private String kineName;
    @SerializedName(value="hasPKKey")
    private boolean hasPKKey;
    @SerializedName(value="reltype")
    private String reltype;
    @SerializedName(value="version")
    private String version = null;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;

    public MppTableEntity() {
        this.init();
    }

    private void init() {
        this.setGuid(StringUtil.getNextInternalId(s_nextId));
        this.setTypeName("mpp_table");
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
        return "MppTableEntity{guid=" + this.guid + "; name=" + this.name + "; qualifiedName=" + this.qualifiedName + "; typeName=" + this.typeName + "; dbName=" + this.dbName + "; dbGuid=" + this.dbGuid + "; parttype=" + this.parttype + "; isTemporary=" + this.isTemporary + "; ddl=" + this.ddl + "; nameSpace=" + this.nameSpace + "; owner=" + this.owner + "; tableSpace=" + this.tableSpace + "; columnNum=" + this.columnNum + "; rowNum=" + this.rowNum + "; tableSize=" + this.tableSize + "; kineName=" + this.kineName + "; hasPKKey=" + this.hasPKKey + "; reltype=" + this.reltype + '}';
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getDbGuid() {
        return this.dbGuid;
    }

    public String getParttype() {
        return this.parttype;
    }

    public boolean isTemporary() {
        return this.isTemporary;
    }

    public String getDdl() {
        return this.ddl;
    }

    public String getNameSpace() {
        return this.nameSpace;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getTableSpace() {
        return this.tableSpace;
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

    public String getKineName() {
        return this.kineName;
    }

    public boolean isHasPKKey() {
        return this.hasPKKey;
    }

    public String getReltype() {
        return this.reltype;
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

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbGuid(String dbGuid) {
        this.dbGuid = dbGuid;
    }

    public void setParttype(String parttype) {
        this.parttype = parttype;
    }

    public void setTemporary(boolean isTemporary) {
        this.isTemporary = isTemporary;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTableSpace(String tableSpace) {
        this.tableSpace = tableSpace;
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

    public void setKineName(String kineName) {
        this.kineName = kineName;
    }

    public void setHasPKKey(boolean hasPKKey) {
        this.hasPKKey = hasPKKey;
    }

    public void setReltype(String reltype) {
        this.reltype = reltype;
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
        if (!(o instanceof MppTableEntity)) {
            return false;
        }
        MppTableEntity other = (MppTableEntity)o;
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
        String this$dbName = this.getDbName();
        String other$dbName = other.getDbName();
        if (this$dbName == null ? other$dbName != null : !this$dbName.equals(other$dbName)) {
            return false;
        }
        String this$dbGuid = this.getDbGuid();
        String other$dbGuid = other.getDbGuid();
        if (this$dbGuid == null ? other$dbGuid != null : !this$dbGuid.equals(other$dbGuid)) {
            return false;
        }
        String this$parttype = this.getParttype();
        String other$parttype = other.getParttype();
        if (this$parttype == null ? other$parttype != null : !this$parttype.equals(other$parttype)) {
            return false;
        }
        if (this.isTemporary() != other.isTemporary()) {
            return false;
        }
        String this$ddl = this.getDdl();
        String other$ddl = other.getDdl();
        if (this$ddl == null ? other$ddl != null : !this$ddl.equals(other$ddl)) {
            return false;
        }
        String this$nameSpace = this.getNameSpace();
        String other$nameSpace = other.getNameSpace();
        if (this$nameSpace == null ? other$nameSpace != null : !this$nameSpace.equals(other$nameSpace)) {
            return false;
        }
        String this$owner = this.getOwner();
        String other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) {
            return false;
        }
        String this$tableSpace = this.getTableSpace();
        String other$tableSpace = other.getTableSpace();
        if (this$tableSpace == null ? other$tableSpace != null : !this$tableSpace.equals(other$tableSpace)) {
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
        String this$kineName = this.getKineName();
        String other$kineName = other.getKineName();
        if (this$kineName == null ? other$kineName != null : !this$kineName.equals(other$kineName)) {
            return false;
        }
        if (this.isHasPKKey() != other.isHasPKKey()) {
            return false;
        }
        String this$reltype = this.getReltype();
        String other$reltype = other.getReltype();
        if (this$reltype == null ? other$reltype != null : !this$reltype.equals(other$reltype)) {
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
        return other instanceof MppTableEntity;
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
        String $dbName = this.getDbName();
        result = result * 59 + ($dbName == null ? 43 : $dbName.hashCode());
        String $dbGuid = this.getDbGuid();
        result = result * 59 + ($dbGuid == null ? 43 : $dbGuid.hashCode());
        String $parttype = this.getParttype();
        result = result * 59 + ($parttype == null ? 43 : $parttype.hashCode());
        result = result * 59 + (this.isTemporary() ? 79 : 97);
        String $ddl = this.getDdl();
        result = result * 59 + ($ddl == null ? 43 : $ddl.hashCode());
        String $nameSpace = this.getNameSpace();
        result = result * 59 + ($nameSpace == null ? 43 : $nameSpace.hashCode());
        String $owner = this.getOwner();
        result = result * 59 + ($owner == null ? 43 : $owner.hashCode());
        String $tableSpace = this.getTableSpace();
        result = result * 59 + ($tableSpace == null ? 43 : $tableSpace.hashCode());
        result = result * 59 + this.getColumnNum();
        Long $rowNum = this.getRowNum();
        result = result * 59 + ($rowNum == null ? 43 : ((Object)$rowNum).hashCode());
        Long $tableSize = this.getTableSize();
        result = result * 59 + ($tableSize == null ? 43 : ((Object)$tableSize).hashCode());
        String $kineName = this.getKineName();
        result = result * 59 + ($kineName == null ? 43 : $kineName.hashCode());
        result = result * 59 + (this.isHasPKKey() ? 79 : 97);
        String $reltype = this.getReltype();
        result = result * 59 + ($reltype == null ? 43 : $reltype.hashCode());
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }
}
