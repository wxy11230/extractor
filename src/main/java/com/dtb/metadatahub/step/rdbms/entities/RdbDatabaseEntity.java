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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RdbDatabaseEntity
implements MetaDataEntity {
    @SerializedName(value="guid")
    private String guid = null;
    @SerializedName(value="name")
    private String name = null;
    @SerializedName(value="typeName")
    private String typeName = null;
    @SerializedName(value="subTypeName")
    private String subTypeName = null;
    @SerializedName(value="status")
    private String dbStatus = null;
    @SerializedName(value="url")
    private String url = null;
    @SerializedName(value="userName")
    private String userName = null;
    @SerializedName(value="password")
    private String password = null;
    @SerializedName(value="comment")
    private String comment = null;
    @SerializedName(value="productName")
    private String productName = null;
    @SerializedName(value="productVersion")
    private String productVersion = null;
    @SerializedName(value="driverName")
    private String driverName = null;
    @SerializedName(value="driverVersion")
    private String driverVersion = null;
    @SerializedName(value="isReadOnly")
    private boolean isReadOnly;
    @SerializedName(value="tableTypes")
    private List<String> tableTypes = null;
    @SerializedName(value="createTime")
    private Long createTime = 0L;
    @SerializedName(value="user")
    private String user = null;
    @SerializedName(value="qualifiedName")
    private String qualifiedName = null;
    @SerializedName(value="version")
    private String version = null;
    @SerializedName(value="recordStatus")
    private String recordStatus;
    @SerializedName(value="recordCreateTime")
    private Date recordCreateTime;

    public RdbDatabaseEntity() {
        this.init();
    }

    private void init() {
        this.setGuid(String.valueOf(SnowFlakeUtil.getInstance().nextId()));
        this.setTypeName("rdb_db");
        this.setVersion("1.0");
        this.setReadOnly(false);
        this.setCreateTime(0L);
        this.setDbStatus("active");
        this.setRecordCreateTime(new Date());
        this.setRecordStatus("1");
        this.setTableTypes(new ArrayList<String>());
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
        return this.url;
    }

    public String toString() {
        return "RdbDatabaseEntity{guid=" + this.guid + "; name=" + this.name + "; comment=" + this.comment + "; typeName=" + this.typeName + "; subTypeName=" + this.subTypeName + "; url = " + this.url + "; userName = " + this.userName + "; password = " + this.password + "; user=" + this.user + "; productName=" + this.productName + "; productVersion" + this.productVersion + "; driverName=" + this.driverName + "; driverVersion=" + this.driverVersion + "}";
    }

    public String getSubTypeName() {
        return this.subTypeName;
    }

    public String getDbStatus() {
        return this.dbStatus;
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

    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    public List<String> getTableTypes() {
        return this.tableTypes;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public String getUser() {
        return this.user;
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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public void setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public void setReadOnly(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public void setTableTypes(List<String> tableTypes) {
        this.tableTypes = tableTypes;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
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
        if (!(o instanceof RdbDatabaseEntity)) {
            return false;
        }
        RdbDatabaseEntity other = (RdbDatabaseEntity)o;
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
        String this$dbStatus = this.getDbStatus();
        String other$dbStatus = other.getDbStatus();
        if (this$dbStatus == null ? other$dbStatus != null : !this$dbStatus.equals(other$dbStatus)) {
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
        if (this.isReadOnly() != other.isReadOnly()) {
            return false;
        }
        List<String> this$tableTypes = this.getTableTypes();
        List<String> other$tableTypes = other.getTableTypes();
        if (this$tableTypes == null ? other$tableTypes != null : !((Object)this$tableTypes).equals(other$tableTypes)) {
            return false;
        }
        Long this$createTime = this.getCreateTime();
        Long other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        String this$user = this.getUser();
        String other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) {
            return false;
        }
        String this$qualifiedName = this.getQualifiedName();
        String other$qualifiedName = other.getQualifiedName();
        if (this$qualifiedName == null ? other$qualifiedName != null : !this$qualifiedName.equals(other$qualifiedName)) {
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
        return other instanceof RdbDatabaseEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $guid = this.getGuid();
        result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $typeName = this.getTypeName();
        result = result * 59 + ($typeName == null ? 43 : $typeName.hashCode());
        String $subTypeName = this.getSubTypeName();
        result = result * 59 + ($subTypeName == null ? 43 : $subTypeName.hashCode());
        String $dbStatus = this.getDbStatus();
        result = result * 59 + ($dbStatus == null ? 43 : $dbStatus.hashCode());
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        String $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $comment = this.getComment();
        result = result * 59 + ($comment == null ? 43 : $comment.hashCode());
        String $productName = this.getProductName();
        result = result * 59 + ($productName == null ? 43 : $productName.hashCode());
        String $productVersion = this.getProductVersion();
        result = result * 59 + ($productVersion == null ? 43 : $productVersion.hashCode());
        String $driverName = this.getDriverName();
        result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
        String $driverVersion = this.getDriverVersion();
        result = result * 59 + ($driverVersion == null ? 43 : $driverVersion.hashCode());
        result = result * 59 + (this.isReadOnly() ? 79 : 97);
        List<String> $tableTypes = this.getTableTypes();
        result = result * 59 + ($tableTypes == null ? 43 : ((Object)$tableTypes).hashCode());
        Long $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : $user.hashCode());
        String $qualifiedName = this.getQualifiedName();
        result = result * 59 + ($qualifiedName == null ? 43 : $qualifiedName.hashCode());
        String $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        String $recordStatus = this.getRecordStatus();
        result = result * 59 + ($recordStatus == null ? 43 : $recordStatus.hashCode());
        Date $recordCreateTime = this.getRecordCreateTime();
        result = result * 59 + ($recordCreateTime == null ? 43 : ((Object)$recordCreateTime).hashCode());
        return result;
    }
}
