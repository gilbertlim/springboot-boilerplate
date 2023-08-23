package com.megazone.springbootboilerplate.common.config.datasource;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {

    private final DataSourceProperties dataSourceProperties;
    private final GenericApplicationContext applicationContext;

    @PostConstruct
    public void initDataSource() {
        for (var entry : dataSourceProperties.groups().entrySet()) {
            DataSource dataSource = DataSourceFactory.generateDataSource(entry.getKey(), entry.getValue(), dataSourceProperties.hikari());
            initializeDataSource(entry, dataSource);
        }
    }

    private void initializeDataSource(Map.Entry<String, DataSourceProperties.FlexibleDataSourceInfo> dataSourceInfoEntry, DataSource dataSource) {
        String beanName = dataSourceInfoEntry.getKey() + "DataSource";
        var dataSourceInfo = dataSourceInfoEntry.getValue();
        if (dataSourceInfo.isCluster()) {
            applicationContext.registerBean(beanName, LazyConnectionDataSourceProxy.class, primaryBeanCustomizer(dataSourceInfo, dataSource));
            initializeBean(beanName, dataSource);
            return;
        }

        applicationContext.registerBean(beanName, DataSource.class, () -> dataSource, primaryBeanCustomizer(dataSourceInfo, dataSource));
        initializeBean(beanName, dataSource);
    }

    private static BeanDefinitionCustomizer primaryBeanCustomizer(DataSourceProperties.FlexibleDataSourceInfo dataSourceInfo, DataSource dataSource) {
        return beanDefinition -> {
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(dataSource);
            beanDefinition.isSingleton();
            if (dataSourceInfo.primary()) {
                beanDefinition.setPrimary(true);
            }
        };
    }

    private void initializeBean(String beanName, DataSource dataSource) {
        var beanFactory = applicationContext.getBeanFactory();
        beanFactory.initializeBean(dataSource, beanName);
    }
}
