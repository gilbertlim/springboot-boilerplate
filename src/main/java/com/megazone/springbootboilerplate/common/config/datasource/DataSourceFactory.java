package com.megazone.springbootboilerplate.common.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

public class DataSourceFactory {
    public static DataSource generateDataSource(String key, DataSourceProperties dataSourceProperties) {
        FlexibleDataSourceInfo flexibleDataSourceInfo = dataSourceProperties.getDataSourceInfo(key);
        if (flexibleDataSourceInfo.isMultiple()) {
            HikariDataSource reader = hikariDataSource(key, flexibleDataSourceInfo.reader());
            HikariDataSource writer = hikariDataSource(key, flexibleDataSourceInfo.writer());
            return new RoutingDataSource(reader, writer);
        }

        return hikariDataSource(key, flexibleDataSourceInfo.getOne());
    }

    private static HikariDataSource hikariDataSource(String name, AbstractDataSourceInfo info) {
        HikariDataSource dataSource = DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .url(info.getUrl())
            .username(info.getUsername())
            .password(info.getPassword())
            .build();
        dataSource.setReadOnly(info.isReadOnly());
        dataSource.setPoolName(name + (info.isReadOnly() ? "Reader" : "Writer"));
        // TODO: Hikari 옵션 적용
        return dataSource;
    }

}
