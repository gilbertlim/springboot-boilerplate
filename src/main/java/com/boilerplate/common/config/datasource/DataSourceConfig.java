package com.boilerplate.common.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.datasource", name = {"reader.url", "writer.url"})
@Configuration
public class DataSourceConfig {

    private final DataSourceProperties dataSourceProperties;
    private final GenericApplicationContext applicationContext;

    @Bean
    public DataSource dataSource() {
        log.info(">>> DataSource 생성");
        DataSource dataSource = dataSourceProperties.initializeDataSource();
        return initializeAndWrapDataSource(dataSourceProperties.isCluster(), dataSource);
    }

    private DataSource initializeAndWrapDataSource(boolean isCluster, DataSource dataSource) {
        if (isCluster) {
            applicationContext.getBeanFactory().initializeBean(dataSource, "dataSource");
            return new LazyConnectionDataSourceProxy(dataSource);
        }
        return dataSource;
    }
}
