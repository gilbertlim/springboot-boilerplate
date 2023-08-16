package com.megazone.springbootboilerplate.common.config.mybatis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@MapperScan(basePackages = "com.megazone.springbootboilerplate.*.infra.mapper")
@Configuration
public class MybatisConfig {

    private final List<BaseTypeHandler<?>> typeHandlers;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext context) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.megazone.springbootboilerplate.*.domain");
        factoryBean.setMapperLocations(context.getResources("classpath:com/megazone/springbootboilerplate/*/infra/dao/*.xml"));
        factoryBean.setTypeHandlers(typeHandlers.toArray(BaseTypeHandler[]::new));
        return factoryBean.getObject();
    }
}
