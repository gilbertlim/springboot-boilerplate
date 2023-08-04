package com.megazone.springbootboilerplate.common.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@MapperScan(basePackages = "com.megazone.springbootboilerplate.*.infra.dao")
@EnableTransactionManagement
@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext context) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.megazone.springbootboilerplate.*.domain");
        factoryBean.setMapperLocations(context.getResources("classpath:com/megazone/springbootboilerplate/*/infra/dao/*.xml"));
        factoryBean.setTypeHandlers(new ShopTierTypeHandler());
        return factoryBean.getObject();
    }
}
