package com.megazone.springbootboilerplate.common.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
        return new LazyConnectionDataSourceProxy(defaultDataSource());
    }

    @Bean
    public DataSource defaultDataSource() {
        String key = "default";
        DataSourceProperties.DataSourcePair pair = dataSourceProperties.getDataSourcePair(key);
        HikariDataSource reader = hikariDataSource(key, pair.reader());
        HikariDataSource writer = hikariDataSource(key, pair.writer());
        return new RoutingDataSource(reader, writer);
    }

    private HikariDataSource hikariDataSource(String name, AbstractDataSourceInfo info) {
        HikariDataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(info.getUrl())
                .username(info.getUsername())
                .password(info.getPassword())
                .build();
        dataSource.setReadOnly(info.isReadOnly());
        dataSource.setPoolName(name + (info.isReadOnly() ? "Reader" : "Writer"));
        return dataSource;
    }
}
