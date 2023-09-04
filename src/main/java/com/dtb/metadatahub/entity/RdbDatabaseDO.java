/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.entity;

import java.util.Date;

public class RdbDatabaseDO {
    private String guid;
    private String name;
    private String user;
    private String url;
    private String userName;
    private String password;
    private String comment;
    private String typeName;
    private String qualifiedName;
    private String subTypeName;
    private String productName;
    private String productVersion;
    private String driverName;
    private String driverVersion;
    private String status;
    private String version;
    private String recordStatus;
    private Date recordCreateTime;

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }

    public String getUser() {
        return this.user;
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

    public String getComment() {
        return this.comment;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getQualifiedName() {
        return this.qualifiedName;
    }

    public String getSubTypeName() {
        return this.subTypeName;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductVersion() {
        return this.productVersion;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public String getDriverVersion() {
        return this.driverVersion;
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

    public RdbDatabaseDO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public RdbDatabaseDO setName(String name) {
        this.name = name;
        return this;
    }

    public RdbDatabaseDO setUser(String user) {
        this.user = user;
        return this;
    }

    public RdbDatabaseDO setUrl(String url) {
        this.url = url;
        return this;
    }

    public RdbDatabaseDO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public RdbDatabaseDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public RdbDatabaseDO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public RdbDatabaseDO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public RdbDatabaseDO setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        return this;
    }

    public RdbDatabaseDO setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
        return this;
    }

    public RdbDatabaseDO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public RdbDatabaseDO setProductVersion(String productVersion) {
        this.productVersion = productVersion;
        return this;
    }

    public RdbDatabaseDO setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public RdbDatabaseDO setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
        return this;
    }

    public RdbDatabaseDO setStatus(String status) {
        this.status = status;
        return this;
    }

    public RdbDatabaseDO setVersion(String version) {
        this.version = version;
        return this;
    }

    public RdbDatabaseDO setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public RdbDatabaseDO setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbDatabaseDO)) {
            return false;
        }
        RdbDatabaseDO other = (RdbDatabaseDO)o;
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
        String this$user = this.getUser();
        String other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) {
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
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
            return false;
        }
        String this$subTypeName = this.getSubTypeName();
        String other$subTypeName = other.getSubTypeName();
        if (this$subTypeName == null ? other$subTypeName != null : !this$subTypeName.equals(other$subTypeName)) {
            return false;
        }
        String this$productName = this.getProductName();
        String other$productName = other.getProductName();
        if (this$productName == null ? other$productName != null : !this$productName.equals(other$productName)) {
            return false;
        }
        String this$productVersion = this.getProductVersion();
        String other$productVersion = other.getProductVersion();
        if (this$productVersion == null ? other$productVersion != null : !this$productVersion.equals(other$productVersion)) {
            return false;
        }
        String this$driverName = this.getDriverName();
        String other$driverName = other.getDriverName();
        if (this$driverName == null ? other$driverName != null : !this$driverName.equals(other$driverName)) {
            return false;
        }
        String this$driverVersion = this.getDriverVersion();
        String other$driverVersion = other.getDriverVersion();
        if (this$driverVersion == null ? other$driverVersion != null : !this$driverVersion.equals(other$driverVersion)) {
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
        return other instanceof RdbDatabaseDO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : $user.hashCode());
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        String $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $subTypeName = this.getSubTypeName();
        result = result * 59 + ($subTypeName == null ? 43 : $subTypeName.hashCode());
        String $productName = this.getProductName();
        result = result * 59 + ($productName == null ? 43 : $productName.hashCode());
        String $productVersion = this.getProductVersion();
        result = result * 59 + ($productVersion == null ? 43 : $productVersion.hashCode());
        String $driverName = this.getDriverName();
        result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
        String $driverVersion = this.getDriverVersion();
        result = result * 59 + ($driverVersion == null ? 43 : $driverVersion.hashCode());
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
        return "RdbDatabaseDO(guid=" + this.getGuid() + ", name=" + this.getName() + ", user=" + this.getUser() + ", url=" + this.getUrl() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", comment=" + this.getComment() + ", typeName=" + this.getTypeName() + ", qualifiedName=" + this.getQualifiedName() + ", subTypeName=" + this.getSubTypeName() + ", productName=" + this.getProductName() + ", productVersion=" + this.getProductVersion() + ", driverName=" + this.getDriverName() + ", driverVersion=" + this.getDriverVersion() + ", status=" + this.getStatus() + ", version=" + this.getVersion() + ", recordStatus=" + this.getRecordStatus() + ", recordCreateTime=" + this.getRecordCreateTime() + ")";
    }
}
