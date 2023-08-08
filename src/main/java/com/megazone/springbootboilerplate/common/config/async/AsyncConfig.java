package com.megazone.springbootboilerplate.common.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean
    public TaskExecutor defaultThreadPool() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setThreadNamePrefix("default-thread-pool");
        threadPoolExecutor.setCorePoolSize(10);
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }
}
