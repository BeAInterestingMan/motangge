package com.motang.motangge.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description MybatisPlus 分页配置
 * @author liuhu
 * @Date 2020/12/10 19:37
 */
@Configuration
@MapperScan("com.motang.motangge.mapper")
public class MybatisPlusPageConfig {
    /**
     * @Description 分页插件
     * @author liuhu
     * @createTime 2020-12-09 09:20:16
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}