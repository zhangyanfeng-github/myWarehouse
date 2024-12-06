package com.zhang;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationPlusAuto {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationPlusAuto.class, args);
    }

    //将MyBatis-Plus插件加入到Ioc容器中
    @Bean
    public MybatisPlusInterceptor plusInterceptor() {
        //MyBatis——Plus的插件集合，所有的插件都加入到这里就行，比如分页插件、乐观锁插件
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        //添加分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        //添加乐观锁插件，MyBatis-Plus会在更新的时候，每次都对比版本号字段和增加版本号+1
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        //添加拦截器，防止全表删除或更新
        mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}