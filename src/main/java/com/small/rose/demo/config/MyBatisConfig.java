package com.small.rose.demo.config;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ MyBatisConfig ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/18 17:44
 * @Version ： 1.0
 **/

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.small.rose.demo.module.mybatis.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {


    // MyBatis配置
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // MyBatis配置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mapper/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
