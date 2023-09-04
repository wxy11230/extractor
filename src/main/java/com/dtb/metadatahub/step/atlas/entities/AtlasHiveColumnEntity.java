/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.step.MetaDataEntity
 *  com.google.gson.annotations.SerializedName
 *  org.apache.atlas.client.model.AtlasEntity
 *  org.apache.atlas.client.model.Entity$Status
 */
package com.dtb.metadatahub.step.atlas.entities;

import com.dtb.metadatahub.step.MetaDataEntity;
import com.dtb.metadatahub.util.StringUtil;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.atlas.client.model.AtlasEntity;
import org.apache.atlas.client.model.Entity;

public class AtlasHiveColumnEntity
extends AtlasEntity
implements MetaDataEntity {
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    private Long id = 0L;
    @SerializedName(value="guid")
    private String guid = null;
    @SerializedName(value="name")
    private String name = null;
    @SerializedName(value="description")
    private String description = null;
    @SerializedName(value="comment")
    private String comment = null;
    @SerializedName(value="typeName")
    private String typeName = null;
    @SerializedName(value="dataType")
    private String dataType = null;
    @SerializedName(value="columnType")
    private String columnType = null;
    @SerializedName(value="isPrimaryKey")
    private boolean isPrimaryKey = false;
    @SerializedName(value="status")
    private String columnStatus = null;
    @SerializedName(value="tableGuid")
    private String tableGuid = null;
    @SerializedName(value="tableName")
    private String tableName = null;
    @SerializedName(value="position")
    private int position = -1;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    @SerializedName(value="owner")
    private String owner = null;
    @SerializedName(value="createTime")
    private Long createTime = 0L;
    @SerializedName(value="updateTime")
    private Long updateTime = 0L;
    @SerializedName(value="createdBy")
    private String createdBy = null;
    @SerializedName(value="updatedBy")
    private String updatedBy = null;
    @SerializedName(value="version")
    private Long version = 0L;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;

    public AtlasHiveColumnEntity() {
        this("");
    }

    public AtlasHiveColumnEntity(String name) {
        this.name = name;
        this.init();
    }

    private void init() {
        this.setGuid(StringUtil.getNextInternalId(s_nextId));
        this.setTableGuid("");
        this.setTableName("");
        this.setDescription("");
        this.setComment("");
        this.setTypeName("hive_column");
        this.setDataType("");
        this.setColumnType("");
        this.setIsPrimaryKey(false);
        this.setStatus(Entity.Status.ACTIVE);
        this.setPosition(-1);
        this.setQualifiedName("");
        this.setOwner("");
        this.setCreatedBy("");
        this.setCreateTime(0L);
        this.setUpdatedBy("");
        this.setUpdateTime(0L);
        this.setVersion(0L);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "HiveColumnEntity{guid=" + this.guid + ",name=" + this.name + ",type=" + this.dataType + ",is_pk=" + this.isPrimaryKey + ",typeName=" + this.typeName + ",decription=" + this.description + ",comment=" + this.comment + ",table=" + this.tableName + ",createTime=" + this.createTime + ",updateTime=" + this.updateTime + ",createdBy=" + this.createdBy + ",updatedBy=" + this.updatedBy + ",owner=" + this.owner + ",ColumnStatus=" + this.columnStatus + ",version=" + this.version + "}";
    }

    public boolean equals(Object o) {
        AtlasHiveColumnEntity entity = (AtlasHiveColumnEntity)((Object)o);
        return entity != null && this.guid.equalsIgnoreCase(entity.getGuid()) && this.typeName.equalsIgnoreCase(entity.getTypeName()) && this.qualifiedName.equalsIgnoreCase(entity.getQualifiedName());
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Long getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getDescription() {
        return this.description;
    }

    public String getComment() {
        return this.comment;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public boolean isPrimaryKey() {
        return this.isPrimaryKey;
    }

    public String getColumnStatus() {
        return this.columnStatus;
    }

    public String getTableGuid() {
        return this.tableGuid;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int getPosition() {
        return this.position;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getOwner() {
        return this.owner;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Long getVersion() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus;
    }

    public void setTableGuid(String tableGuid) {
        this.tableGuid = tableGuid;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
    }
}
