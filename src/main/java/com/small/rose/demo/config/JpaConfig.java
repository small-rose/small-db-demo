package com.small.rose.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JpaConfig ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/18 18:11
 * @Version ： 1.0
 **/

// 借助spring data实现自动化的jpa repository，只需编写接口无需编写实现类
// 相当于xml配置的<jpa:repositories base-package="com.example.repository" />
// repositoryImplementationPostfix默认就是Impl
// entityManagerFactoryRef默认就是entityManagerFactory
// transactionManagerRef默认就是transactionManager
@Configuration
@EnableJpaRepositories(
        basePackages = "com.small.rose.demo.module.hibernate.dao",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
public class JpaConfig {


    // 配置jpa事务管理器
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    // JPA 配置实体管理器工厂
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.small.rose.demo.module.hibernate.entities");

        // ... JPA相关配置

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        // 设置数据库类型（可使用org.springframework.orm.jpa.vendor包下的Database枚举类）
        jpaVendorAdapter.setDatabase(Database.ORACLE);
        // 设置打印sql语句
        jpaVendorAdapter.setShowSql(true);
        // 设置不生成ddl语句
        jpaVendorAdapter.setGenerateDdl(false);
        // 设置hibernate方言
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.OracleDialect");

        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }






    private Properties additionalProperties() {
        Properties properties = new Properties();
        // 设置Hibernate属性
        properties.setProperty("hibernate.ddl-auto", "none");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        //properties.setProperty("show-sql", "true");
        return properties;
    }
}
