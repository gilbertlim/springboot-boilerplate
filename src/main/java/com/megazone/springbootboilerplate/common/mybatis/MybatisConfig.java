package com.megazone.springbootboilerplate.common.mybatis;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;

@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = MybatisAutoConfiguration.class)
@MapperScan(basePackages = "${mybatis.base-package}" + ".mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@Configuration
public class MybatisConfig {

    private final MybatisCustomProperties properties;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        factoryBean.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        return factoryBean.getObject();
    }
}
