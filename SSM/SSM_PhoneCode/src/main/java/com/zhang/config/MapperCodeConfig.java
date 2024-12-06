package com.zhang.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 持久层配置类
 */
@Configuration
public class MapperCodeConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //指定配置文件等信息
        //指定数据库连接池对象
        sqlSessionFactoryBean.setDataSource(dataSource);

        //指定MyBatis配置文件的信息，使用代码的形式
        org.apache.ibatis.session.Configuration configuration =
                new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Slf4jImpl.class);
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);

        //存储setting的配置文件
        sqlSessionFactoryBean.setConfiguration(configuration);
        //别名设置
        sqlSessionFactoryBean.setTypeAliasesPackage("com.zhang.pojo");

        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.addPlugins(pageInterceptor);

        return sqlSessionFactoryBean;
    }

    //Mapper代理对象加入到IOC
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //设置Mapper接口和XML文件所在的共用包
        mapperScannerConfigurer.setBasePackage("com.zhang.mapper");
        return mapperScannerConfigurer;
    }
}
