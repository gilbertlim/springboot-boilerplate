package com.megazone.springbootboilerplate.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Optional;

@ConfigurationProperties("spring.datasource")
public record DataSourceProperties(
    Map<String, DataSourcePair> datasourcePair
) {
    public record DataSourcePair (
            ReaderDataSourceInfo reader,
            WriterDataSourceInfo writer
    ) {
    }

    public DataSourcePair getDataSourcePair(String key) {
        return Optional.ofNullable(datasourcePair.get(key))
                .orElseThrow(() -> new IllegalArgumentException(key + " 데이터소스 정보가 존재하지 않습니다."));
    }
}
