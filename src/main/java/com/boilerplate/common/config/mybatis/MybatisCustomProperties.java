package com.boilerplate.common.config.mybatis;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisCustomProperties extends MybatisProperties {

    private final List<String> typeHandlersPackages;
    private final List<String> typeAliasesPackages;

    @Override
    public String getTypeHandlersPackage() {
        return String.join(",", typeHandlersPackages);
    }

    @Override
    public String getTypeAliasesPackage() {
        return String.join(",", typeAliasesPackages);
    }
}
