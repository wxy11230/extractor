/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class ChangeMakerDO {
    private long id;
    private String type;
    private long changeMaker;
    private String qualifiedName;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public long getChangeMaker() {
        return this.changeMaker;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChangeMaker(long changeMaker) {
        this.changeMaker = changeMaker;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChangeMakerDO)) {
            return false;
        }
        ChangeMakerDO other = (ChangeMakerDO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        if (this.getChangeMaker() != other.getChangeMaker()) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        Date this$createTime = this.getCreateTime();
        Date other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        Date this$updateTime = this.getUpdateTime();
        Date other$updateTime = other.getUpdateTime();
        return !(this$updateTime == null ? other$updateTime != null : !((Object)this$updateTime).equals(other$updateTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ChangeMakerDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        long $changeMaker = this.getChangeMaker();
        result = result * 59 + (int)($changeMaker >>> 32 ^ $changeMaker);
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        Date $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        Date $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : ((Object)$updateTime).hashCode());
        return result;
    }

    public String toString() {
        return "ChangeMakerDO(id=" + this.getId() + ", type=" + this.getType() + ", changeMaker=" + this.getChangeMaker() + ", qualifiedName=" + this.getQualifiedName() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}
