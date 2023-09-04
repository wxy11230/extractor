/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class MppDatabaseDO {
    private Long id;
    private String guid;
    private String name;
    private String url;
    private String userName;
    private String password;
    private String encoding;
    private int connlimit;
    private String tableSpace;
    private String typeName;
    private String qualifiedName;
    private String owner;
    private String datctype;
    private boolean isTemplate;
    private boolean allowconn;
    private String version;
    private String recordStatus;
    private Date recordCreateTime;

    public void setAllowconn(boolean allowconn) {
        this.allowconn = allowconn;
    }

    public void setIsTemplate(boolean isTemplate) {
        this.isTemplate = isTemplate;
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

    public String getUrl() {
        return this.url;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public int getConnlimit() {
        return this.connlimit;
    }

    public String getTableSpace() {
        return this.tableSpace;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getDatctype() {
        return this.datctype;
    }

    public boolean isTemplate() {
        return this.isTemplate;
    }

    public boolean isAllowconn() {
        return this.allowconn;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setConnlimit(int connlimit) {
        this.connlimit = connlimit;
    }

    public void setTableSpace(String tableSpace) {
        this.tableSpace = tableSpace;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDatctype(String datctype) {
        this.datctype = datctype;
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
        if (!(o instanceof MppDatabaseDO)) {
            return false;
        }
        MppDatabaseDO other = (MppDatabaseDO)o;
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
        String this$url = this.getUrl();
        String other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) {
            return false;
        }
        String this$userName = this.getUserName();
        String other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$encoding = this.getEncoding();
        String other$encoding = other.getEncoding();
        if (this$encoding == null ? other$encoding != null : !this$encoding.equals(other$encoding)) {
            return false;
        }
        if (this.getConnlimit() != other.getConnlimit()) {
            return false;
        }
        String this$tableSpace = this.getTableSpace();
        String other$tableSpace = other.getTableSpace();
        if (this$tableSpace == null ? other$tableSpace != null : !this$tableSpace.equals(other$tableSpace)) {
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
        String this$owner = this.getOwner();
        String other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) {
            return false;
        }
        String this$datctype = this.getDatctype();
        String other$datctype = other.getDatctype();
        if (this$datctype == null ? other$datctype != null : !this$datctype.equals(other$datctype)) {
            return false;
        }
        if (this.isTemplate() != other.isTemplate()) {
            return false;
        }
        if (this.isAllowconn() != other.isAllowconn()) {
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
        return other instanceof MppDatabaseDO;
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
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        String $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $encoding = this.getEncoding();
        result = result * 59 + ($encoding == null ? 43 : $encoding.hashCode());
        result = result * 59 + this.getConnlimit();
        String $tableSpace = this.getTableSpace();
        result = result * 59 + ($tableSpace == null ? 43 : $tableSpace.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $owner = this.getOwner();
        result = result * 59 + ($owner == null ? 43 : $owner.hashCode());
        String $datctype = this.getDatctype();
        result = result * 59 + ($datctype == null ? 43 : $datctype.hashCode());
        result = result * 59 + (this.isTemplate() ? 79 : 97);
        result = result * 59 + (this.isAllowconn() ? 79 : 97);
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }

    public String toString() {
        return "MppDatabaseDO(id=" + this.getId() + ", guid=" + this.getGuid() + ", name=" + this.getName() + ", url=" + this.getUrl() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", encoding=" + this.getEncoding() + ", connlimit=" + this.getConnlimit() + ", tableSpace=" + this.getTableSpace() + ", typeName=" + this.getTypeName() + ", qualifiedName=" + this.getQualifiedName() + ", owner=" + this.getOwner() + ", datctype=" + this.getDatctype() + ", isTemplate=" + this.isTemplate() + ", allowconn=" + this.isAllowconn() + ", version=" + this.getVersion() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
