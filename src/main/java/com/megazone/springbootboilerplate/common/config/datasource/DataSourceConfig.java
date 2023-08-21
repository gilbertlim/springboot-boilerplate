package com.megazone.springbootboilerplate.common.config.datasource;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {

    private final DataSourceProperties dataSourceProperties;

    @Primary
    @Bean
    public DataSource lazyConnectionDataSource() {
        return new LazyConnectionDataSourceProxy(defaultRoutingDataSource());
    }

    @Bean
    public DataSource defaultRoutingDataSource() {
        String key = "default";
        return DataSourceFactory.generateDataSource(key, dataSourceProperties);
    }

    @Bean
    public DataSource yamsrounDataSource() {
        String key = "yamsroun";
        return DataSourceFactory.generateDataSource(key, dataSourceProperties);
    }
}
