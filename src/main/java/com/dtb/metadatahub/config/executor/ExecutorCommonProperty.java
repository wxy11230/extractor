/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.context.properties.ConfigurationProperties
 *  org.springframework.context.annotation.Configuration
 */
package com.dtb.metadatahub.config.executor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="executor.common.property")
public class ExecutorCommonProperty {
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private String threadNamePrefix;
    private int awaitSecond;
    private boolean shutdown;

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public int getQueueCapacity() {
        return this.queueCapacity;
    }

    public String getThreadNamePrefix() {
        return this.threadNamePrefix;
    }

    public int getAwaitSecond() {
        return this.awaitSecond;
    }

    public boolean isShutdown() {
        return this.shutdown;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    public void setAwaitSecond(int awaitSecond) {
        this.awaitSecond = awaitSecond;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExecutorCommonProperty)) {
            return false;
        }
        ExecutorCommonProperty other = (ExecutorCommonProperty)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getCorePoolSize() != other.getCorePoolSize()) {
            return false;
        }
        if (this.getMaxPoolSize() != other.getMaxPoolSize()) {
            return false;
        }
        if (this.getQueueCapacity() != other.getQueueCapacity()) {
            return false;
        }
        String this$threadNamePrefix = this.getThreadNamePrefix();
        String other$threadNamePrefix = other.getThreadNamePrefix();
        if (this$threadNamePrefix == null ? other$threadNamePrefix != null : !this$threadNamePrefix.equals(other$threadNamePrefix)) {
            return false;
        }
        if (this.getAwaitSecond() != other.getAwaitSecond()) {
            return false;
        }
        return this.isShutdown() == other.isShutdown();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ExecutorCommonProperty;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getCorePoolSize();
        result = result * 59 + this.getMaxPoolSize();
        result = result * 59 + this.getQueueCapacity();
        String $threadNamePrefix = this.getThreadNamePrefix();
        result = result * 59 + ($threadNamePrefix == null ? 43 : $threadNamePrefix.hashCode());
        result = result * 59 + this.getAwaitSecond();
        result = result * 59 + (this.isShutdown() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "ExecutorCommonProperty(corePoolSize=" + this.getCorePoolSize() + ", maxPoolSize=" + this.getMaxPoolSize() + ", queueCapacity=" + this.getQueueCapacity() + ", threadNamePrefix=" + this.getThreadNamePrefix() + ", awaitSecond=" + this.getAwaitSecond() + ", shutdown=" + this.isShutdown() + ")";
    }
}
