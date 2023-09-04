
package com.dtb.metadatahub.step.atlas.entities;

import com.dtb.metadatahub.step.MetaDataEntity;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import org.apache.atlas.client.model.AtlasEntity;

public class AtlasHiveLineageEntity
extends AtlasEntity
implements MetaDataEntity {
    private Long id = 0L;
    @SerializedName(value="guid")
    private String guid = null;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    private String lineageText = null;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;
    @SerializedName(value="recordCreateTime")
    private Date recordUpdateTime;

    public AtlasHiveLineageEntity() {
        this.init();
    }

    private void init() {
        this.setLineageText("");
        this.setRecordStatus("1");
    }

    public String getName() {
        return this.qualifiedName;
    }

    public String toString() {
        return "LinegeEntity{guid=" + this.guid + ",qualifiedName=" + this.qualifiedName + ",lineageInfo{\n" + this.lineageText + "\n}}";
    }

    public boolean equals(Object o) {
        AtlasHiveLineageEntity entity = (AtlasHiveLineageEntity)((Object)o);
        return entity != null && this.guid.equalsIgnoreCase(entity.getGuid()) && this.qualifiedName.equalsIgnoreCase(entity.getQualifiedName());
    }

    public Long getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getLineageText() {
        return this.lineageText;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public Date getRecordCreateTime() {
        return this.recordCreateTime;
    }

    public Date getRecordUpdateTime() {
        return this.recordUpdateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setLineageText(String lineageText) {
        this.lineageText = lineageText;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
    }

    public void setRecordUpdateTime(Date recordUpdateTime) {
        this.recordUpdateTime = recordUpdateTime;
    }
}
