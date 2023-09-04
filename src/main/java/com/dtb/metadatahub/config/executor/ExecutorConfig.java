/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.config.executor.ExecutorCommonProperty
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.scheduling.annotation.EnableAsync
 *  org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
 */
package com.dtb.metadatahub.config.executor;

import com.dtb.metadatahub.config.executor.ExecutorCommonProperty;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ExecutorConfig {
    @Autowired
    private ExecutorCommonProperty executorProperty;

    @Bean(value={"executorAsyncExecutor"})
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.executorProperty.getCorePoolSize());
        executor.setMaxPoolSize(this.executorProperty.getMaxPoolSize());
        executor.setQueueCapacity(this.executorProperty.getQueueCapacity());
        executor.setThreadNamePrefix(this.executorProperty.getThreadNamePrefix());
        executor.setWaitForTasksToCompleteOnShutdown(this.executorProperty.isShutdown());
        executor.setAwaitTerminationSeconds(this.executorProperty.getAwaitSecond());
        executor.setRejectedExecutionHandler((RejectedExecutionHandler)new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
