package com.megazone.springbootboilerplate.common.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@ConfigurationProperties(prefix = "spring.datasource")
public record DataSourceProperties(
    Map<String, FlexibleDataSourceInfo> groups,
    HikariConfig hikari
) {
    public DataSourceProperties(Map<String, FlexibleDataSourceInfo> groups, HikariConfig hikari) {
        this.groups = groups;
        this.hikari = Objects.requireNonNullElse(hikari, new HikariConfig());
    }

    public record FlexibleDataSourceInfo(
        ReaderDataSourceInfo reader,
        WriterDataSourceInfo writer
    ) {
        public boolean isMultiple() {
            return reader != null && writer != null;
        }

        public AbstractDataSourceInfo getOne() {
            if (Optional.ofNullable(writer).isPresent()) {
                return writer;
            }
            return reader;
        }
    }

    public FlexibleDataSourceInfo getDataSourceInfo(String key) {
        return Optional.ofNullable(groups.get(key))
                .orElseThrow(() -> new IllegalArgumentException(key + " 데이터소스 정보가 존재하지 않습니다."));
    }
}
