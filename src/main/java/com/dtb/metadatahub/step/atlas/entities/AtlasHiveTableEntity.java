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

public class AtlasHiveTableEntity
extends AtlasEntity
implements MetaDataEntity {
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    private Long id = 0L;
    @SerializedName(value="guid")
    private String guid = null;
    @SerializedName(value="name")
    private String name = null;
    @SerializedName(value="dbGuid")
    private String dbGuid = null;
    @SerializedName(value="dbName")
    private String dbName = null;
    @SerializedName(value="description")
    private String description = null;
    @SerializedName(value="comment")
    private String comment = null;
    @SerializedName(value="typeName")
    private String typeName = null;
    @SerializedName(value="tableType")
    private String tableType = null;
    @SerializedName(value="status")
    private String tbStatus = null;
    @SerializedName(value="temporary")
    private boolean temporary = false;
    @SerializedName(value="queryText")
    private String queryText = null;
    @SerializedName(value="totalSize")
    private double totalSize = 0.0;
    @SerializedName(value="rowDataSize")
    private double rowDataSize = 0.0;
    @SerializedName(value="numRows")
    private long numRows = 0L;
    @SerializedName(value="numFiles")
    private long numFiles = 0L;
    @SerializedName(value="partitionKeys")
    private String partitionKeys = null;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    @SerializedName(value="owner")
    private String owner = null;
    @SerializedName(value="location")
    private String location = null;
    @SerializedName(value="createTime")
    private Long createTime = 0L;
    @SerializedName(value="updateTime")
    private Long updateTime = 0L;
    @SerializedName(value="createdBy")
    private String createdBy = null;
    @SerializedName(value="updatedBy")
    private String updatedBy = null;
    @SerializedName(value="lastAccessTime")
    private Long lastAccessTime = 0L;
    @SerializedName(value="version")
    private Long version = 0L;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;
    @SerializedName(value="columnNum")
    private int columnNum;

    public AtlasHiveTableEntity() {
        this("");
    }

    public AtlasHiveTableEntity(String name) {
        this.name = name;
        this.init();
    }

    private void init() {
        this.setGuid(StringUtil.getNextInternalId(s_nextId));
        this.setDbGuid("");
        this.setDbName("");
        this.setDescription("");
        this.setComment("");
        this.setTypeName("hive_table");
        this.setTableType("");
        this.setStatus(Entity.Status.ACTIVE);
        this.setTemporary(false);
        this.setQueryText("");
        this.setTotalSize(0.0);
        this.setRowDataSize(0.0);
        this.setNumRows(0L);
        this.setNumFiles(0L);
        this.setPartitionKeys("");
        this.setQualifiedName("");
        this.setColumnNum(0);
        this.setOwner("");
        this.setLocation("");
        this.setCreatedBy("");
        this.setCreateTime(0L);
        this.setUpdatedBy("");
        this.setUpdateTime(0L);
        this.setLastAccessTime(0L);
        this.setVersion(0L);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "HiveTableEntity{guid=" + this.guid + ",name=" + this.name + ",typeName=" + this.typeName + ",decription=" + this.description + ",database=" + this.dbName + ",createTime=" + this.createTime + ",updateTime=" + this.updateTime + ",createdBy=" + this.createdBy + ",updatedBy=" + this.updatedBy + ",owner=" + this.owner + ",tbStatus=" + this.tbStatus + ",location=" + this.location + ",version=" + this.version + "}";
    }

    public boolean equals(Object o) {
        AtlasHiveTableEntity entity = (AtlasHiveTableEntity)((Object)o);
        return entity != null && this.guid.equalsIgnoreCase(entity.getGuid()) && this.typeName.equalsIgnoreCase(entity.getTypeName()) && this.qualifiedName.equalsIgnoreCase(entity.getQualifiedName());
    }

    public Long getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getDbGuid() {
        return this.dbGuid;
    }

    public String getDbName() {
        return this.dbName;
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

    public String getTableType() {
        return this.tableType;
    }

    public String getTbStatus() {
        return this.tbStatus;
    }

    public boolean isTemporary() {
        return this.temporary;
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

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getLocation() {
        return this.location;
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

    public Long getLastAccessTime() {
        return this.lastAccessTime;
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

    public int getColumnNum() {
        return this.columnNum;
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

    public void setDbGuid(String dbGuid) {
        this.dbGuid = dbGuid;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
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

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public void setTotalSize(double totalSize) {
        this.totalSize = totalSize;
    }

    public void setRowDataSize(double rowDataSize) {
        this.rowDataSize = rowDataSize;
    }

    public void setNumRows(long numRows) {
        this.numRows = numRows;
    }

    public void setNumFiles(long numFiles) {
        this.numFiles = numFiles;
    }

    public void setPartitionKeys(String partitionKeys) {
        this.partitionKeys = partitionKeys;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setLastAccessTime(Long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
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

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }
}
