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

public class AtlasHiveDatabaseEntity
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
    @SerializedName(value="typeName")
    private String typeName = null;
    @SerializedName(value="status")
    private String dbStatus = null;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    @SerializedName(value="clusterName")
    private String clusterName = null;
    @SerializedName(value="owner")
    private String owner = null;
    @SerializedName(value="ownerType")
    private String ownerType = null;
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
    @SerializedName(value="version")
    private Long version = 0L;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;

    private void init() {
        this.setGuid(StringUtil.getNextInternalId(s_nextId));
        this.setTypeName("hive_db");
        this.setDescription("");
        this.setClusterName("");
        this.setCreatedBy("");
        this.setCreateTime(0L);
        this.setLocation("");
        this.setOwner("");
        this.setOwnerType("");
        this.setQualifiedName("");
        this.setStatus(Entity.Status.ACTIVE);
        this.setUpdatedBy("");
        this.setUpdateTime(0L);
        this.setVersion(0L);
    }

    public AtlasHiveDatabaseEntity() {
        this("");
    }

    public AtlasHiveDatabaseEntity(String name) {
        this.name = name;
        this.init();
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "HiveDatabaseEntity{guid=" + this.guid + ",name=" + this.name + ",typeName=" + this.typeName + ",decription=" + this.description + ",createTime=" + this.createTime + ",updateTime=" + this.updateTime + ",createdBy=" + this.createdBy + ",updatedBy=" + this.updatedBy + ",owner=" + this.owner + ",dbStatus=" + this.dbStatus + ",location=" + this.location + ",version=" + this.version + "}";
    }

    public boolean equals(Object o) {
        AtlasHiveDatabaseEntity entity = (AtlasHiveDatabaseEntity)((Object)o);
        return entity != null && this.guid.equalsIgnoreCase(entity.getGuid()) && this.typeName.equalsIgnoreCase(entity.getTypeName()) && this.qualifiedName.equalsIgnoreCase(entity.getQualifiedName());
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

    public String getTypeName() {
        return this.typeName;
    }

    public String getDbStatus() {
        return this.dbStatus;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getOwnerType() {
        return this.ownerType;
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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
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
