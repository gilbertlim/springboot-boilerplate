package com.megazone.springbootboilerplate.common.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.*;

@ConfigurationProperties(prefix = "spring.datasource")
public record DataSourceProperties(
    Map<String, DataSourceGroup> groups,
    HikariConfig hikari
) {

    public DataSourceProperties(Map<String, DataSourceGroup> groups, HikariConfig hikari) {
        this.groups = groups;
        this.hikari = Objects.requireNonNullElse(hikari, new HikariConfig());
    }

    public record DataSourceGroup(
        boolean primary,
        ReaderDataSourceInfo reader,
        WriterDataSourceInfo writer
    ) {

        public DataSourceGroup(
            @DefaultValue("false") boolean primary,
            ReaderDataSourceInfo reader,
            WriterDataSourceInfo writer
        ) {
            this.primary = primary;
            this.reader = reader;
            this.writer = writer;
        }

        public boolean isCluster() {
            return reader != null && writer != null;
        }

        public AbstractDataSourceInfo getOne() {
            if (Optional.ofNullable(writer).isPresent()) {
                return writer;
            }
            return reader;
        }
    }
}
