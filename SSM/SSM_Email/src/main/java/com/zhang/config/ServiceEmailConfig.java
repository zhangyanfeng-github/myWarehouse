package com.zhang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 业务层配置类：Service。aop，tx
 * 1、Service
 * 2、开启aop注解的支持aspect：@Before @After @AfterReturning @AfterThrowing @Around @Aspect @Order
 * 3、tx声明式事务管理：对应的事务管理器实现[TransactionManager DataSource 。。]
 */

@Configuration
@EnableAspectJAutoProxy     //开启AOP
@EnableTransactionManagement    //开启事务管理器
@ComponentScan("com.zhang.service")
public class ServiceEmailConfig {

    //事务管理器的实现
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
