/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.config.ConfigBuilder
 *  com.dtb.metadatahub.config.RdbConfig
 *  com.dtb.metadatahub.step.IStepMeta
 *  com.dtb.metadatahub.step.StepType
 */
package com.dtb.metadatahub.step.rdbms;

import com.dtb.metadatahub.config.ConfigBuilder;
import com.dtb.metadatahub.config.RdbConfig;
import com.dtb.metadatahub.step.IStepMeta;
import com.dtb.metadatahub.step.StepType;
import com.dtb.metadatahub.util.StringUtil;
import java.util.concurrent.atomic.AtomicLong;

public class RdbStepMeta
implements IStepMeta {
    private static AtomicLong s_nextId = new AtomicLong(System.nanoTime());
    private String stepName;
    private String stepGuid;
    private StepType stepType;
    private boolean isConfig;
    private String databaseServer;
    private String databaseType;
    private String databaseName;
    private int port;
    private String userName;
    private String password;
    private int connectTimeout = 60000;
    private int socketTimeout = 600000;
    private boolean ignoreDDL = true;
    private boolean ignoreRowNum = true;
    private boolean ignoreTableSize = true;
    private String ignoreDatabaseNames = "";
    private String extratorDatabaseNames = "";
    private String extratorTableNames = "";

    public RdbStepMeta() {
        this.stepName = "DEFAULT_RDB_STEP";
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.RDB_BASE;
        this.isConfig = false;
    }

    public RdbStepMeta(String stepName) {
        this.stepName = stepName;
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        this.stepType = StepType.RDB_BASE;
        this.isConfig = false;
    }

    public RdbStepMeta(String stepName, String fileName) {
        this.stepName = stepName;
        this.stepGuid = StringUtil.getNextInternalId(s_nextId);
        RdbConfig config = new ConfigBuilder().withPropertiesFile(fileName).buildRdbConfig();
        if (config.getProps() != null) {
            this.isConfig = true;
            this.databaseServer = config.getDatabaseServer();
            this.databaseType = config.getDatabaseType();
            this.databaseName = config.getDatabaseName();
            this.port = config.getPort();
            this.userName = config.getUserName();
            this.password = config.getPassword();
            this.connectTimeout = config.getConnectTimeout();
            this.socketTimeout = config.getSocketTimeout();
            this.ignoreDDL = config.getIgnoreDDL();
            this.ignoreRowNum = config.getIgnoreRowNum();
            this.ignoreTableSize = config.getIgnoreTableSize();
            this.extratorDatabaseNames = config.getExtractDatabaseNames();
            this.ignoreDatabaseNames = config.getIgnoreDatabaseNames();
            this.extratorTableNames = config.getExtractTableNames();
        } else {
            this.isConfig = false;
        }
    }

    public String getStepName() {
        return this.stepName;
    }

    public String getStepGuid() {
        return this.stepGuid;
    }

    public StepType getStepType() {
        return this.stepType;
    }

    public boolean isConfig() {
        return this.isConfig;
    }

    public String getDatabaseServer() {
        return this.databaseServer;
    }

    public String getDatabaseType() {
        return this.databaseType;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public int getPort() {
        return this.port;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public boolean isIgnoreDDL() {
        return this.ignoreDDL;
    }

    public boolean isIgnoreRowNum() {
        return this.ignoreRowNum;
    }

    public boolean isIgnoreTableSize() {
        return this.ignoreTableSize;
    }

    public String getIgnoreDatabaseNames() {
        return this.ignoreDatabaseNames;
    }

    public String getExtratorDatabaseNames() {
        return this.extratorDatabaseNames;
    }

    public String getExtratorTableNames() {
        return this.extratorTableNames;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setStepGuid(String stepGuid) {
        this.stepGuid = stepGuid;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public void setConfig(boolean isConfig) {
        this.isConfig = isConfig;
    }

    public void setDatabaseServer(String databaseServer) {
        this.databaseServer = databaseServer;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setIgnoreDDL(boolean ignoreDDL) {
        this.ignoreDDL = ignoreDDL;
    }

    public void setIgnoreRowNum(boolean ignoreRowNum) {
        this.ignoreRowNum = ignoreRowNum;
    }

    public void setIgnoreTableSize(boolean ignoreTableSize) {
        this.ignoreTableSize = ignoreTableSize;
    }

    public void setIgnoreDatabaseNames(String ignoreDatabaseNames) {
        this.ignoreDatabaseNames = ignoreDatabaseNames;
    }

    public void setExtratorDatabaseNames(String extratorDatabaseNames) {
        this.extratorDatabaseNames = extratorDatabaseNames;
    }

    public void setExtratorTableNames(String extratorTableNames) {
        this.extratorTableNames = extratorTableNames;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RdbStepMeta)) {
            return false;
        }
        RdbStepMeta other = (RdbStepMeta)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$stepName = this.getStepName();
        String other$stepName = other.getStepName();
        if (this$stepName == null ? other$stepName != null : !this$stepName.equals(other$stepName)) {
            return false;
        }
        String this$stepGuid = this.getStepGuid();
        String other$stepGuid = other.getStepGuid();
        if (this$stepGuid == null ? other$stepGuid != null : !this$stepGuid.equals(other$stepGuid)) {
            return false;
        }
        StepType this$stepType = this.getStepType();
        StepType other$stepType = other.getStepType();
        if (this$stepType == null ? other$stepType != null : !this$stepType.equals(other$stepType)) {
            return false;
        }
        if (this.isConfig() != other.isConfig()) {
            return false;
        }
        String this$databaseServer = this.getDatabaseServer();
        String other$databaseServer = other.getDatabaseServer();
        if (this$databaseServer == null ? other$databaseServer != null : !this$databaseServer.equals(other$databaseServer)) {
            return false;
        }
        String this$databaseType = this.getDatabaseType();
        String other$databaseType = other.getDatabaseType();
        if (this$databaseType == null ? other$databaseType != null : !this$databaseType.equals(other$databaseType)) {
            return false;
        }
        String this$databaseName = this.getDatabaseName();
        String other$databaseName = other.getDatabaseName();
        if (this$databaseName == null ? other$databaseName != null : !this$databaseName.equals(other$databaseName)) {
            return false;
        }
        if (this.getPort() != other.getPort()) {
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
        if (this.getConnectTimeout() != other.getConnectTimeout()) {
            return false;
        }
        if (this.getSocketTimeout() != other.getSocketTimeout()) {
            return false;
        }
        if (this.isIgnoreDDL() != other.isIgnoreDDL()) {
            return false;
        }
        if (this.isIgnoreRowNum() != other.isIgnoreRowNum()) {
            return false;
        }
        if (this.isIgnoreTableSize() != other.isIgnoreTableSize()) {
            return false;
        }
        String this$ignoreDatabaseNames = this.getIgnoreDatabaseNames();
        String other$ignoreDatabaseNames = other.getIgnoreDatabaseNames();
        if (this$ignoreDatabaseNames == null ? other$ignoreDatabaseNames != null : !this$ignoreDatabaseNames.equals(other$ignoreDatabaseNames)) {
            return false;
        }
        String this$extratorDatabaseNames = this.getExtratorDatabaseNames();
        String other$extratorDatabaseNames = other.getExtratorDatabaseNames();
        if (this$extratorDatabaseNames == null ? other$extratorDatabaseNames != null : !this$extratorDatabaseNames.equals(other$extratorDatabaseNames)) {
            return false;
        }
        String this$extratorTableNames = this.getExtratorTableNames();
        String other$extratorTableNames = other.getExtratorTableNames();
        return !(this$extratorTableNames == null ? other$extratorTableNames != null : !this$extratorTableNames.equals(other$extratorTableNames));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RdbStepMeta;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $stepName = this.getStepName();
        result = result * 59 + ($stepName == null ? 43 : $stepName.hashCode());
        String $stepGuid = this.getStepGuid();
        result = result * 59 + ($stepGuid == null ? 43 : $stepGuid.hashCode());
        StepType $stepType = this.getStepType();
        result = result * 59 + ($stepType == null ? 43 : $stepType.hashCode());
        result = result * 59 + (this.isConfig() ? 79 : 97);
        String $databaseServer = this.getDatabaseServer();
        result = result * 59 + ($databaseServer == null ? 43 : $databaseServer.hashCode());
        String $databaseType = this.getDatabaseType();
        result = result * 59 + ($databaseType == null ? 43 : $databaseType.hashCode());
        String $databaseName = this.getDatabaseName();
        result = result * 59 + ($databaseName == null ? 43 : $databaseName.hashCode());
        result = result * 59 + this.getPort();
        String $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        result = result * 59 + this.getConnectTimeout();
        result = result * 59 + this.getSocketTimeout();
        result = result * 59 + (this.isIgnoreDDL() ? 79 : 97);
        result = result * 59 + (this.isIgnoreRowNum() ? 79 : 97);
        result = result * 59 + (this.isIgnoreTableSize() ? 79 : 97);
        String $ignoreDatabaseNames = this.getIgnoreDatabaseNames();
        result = result * 59 + ($ignoreDatabaseNames == null ? 43 : $ignoreDatabaseNames.hashCode());
        String $extratorDatabaseNames = this.getExtratorDatabaseNames();
        result = result * 59 + ($extratorDatabaseNames == null ? 43 : $extratorDatabaseNames.hashCode());
        String $extratorTableNames = this.getExtratorTableNames();
        result = result * 59 + ($extratorTableNames == null ? 43 : $extratorTableNames.hashCode());
        return result;
    }

    public String toString() {
        return "RdbStepMeta(stepName=" + this.getStepName() + ", stepGuid=" + this.getStepGuid() + ", stepType=" + this.getStepType() + ", isConfig=" + this.isConfig() + ", databaseServer=" + this.getDatabaseServer() + ", databaseType=" + this.getDatabaseType() + ", databaseName=" + this.getDatabaseName() + ", port=" + this.getPort() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", connectTimeout=" + this.getConnectTimeout() + ", socketTimeout=" + this.getSocketTimeout() + ", ignoreDDL=" + this.isIgnoreDDL() + ", ignoreRowNum=" + this.isIgnoreRowNum() + ", ignoreTableSize=" + this.isIgnoreTableSize() + ", ignoreDatabaseNames=" + this.getIgnoreDatabaseNames() + ", extratorDatabaseNames=" + this.getExtratorDatabaseNames() + ", extratorTableNames=" + this.getExtratorTableNames() + ")";
    }
}
