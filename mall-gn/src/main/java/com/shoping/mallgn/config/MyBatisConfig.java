package com.shoping.mallgn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement   //开启使用
@MapperScan("com.shoping.mallgn.dao")
public class MyBatisConfig {
     //引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
         //设置 最大页数 操作 true: 如果请求的页数大与总页数 就返回到首页去  fase ：就继续请求
        paginationInterceptor.setOverflow(true);
         paginationInterceptor.setLimit(1000);   //设置单页数量最大值 -1:是不受限制   默认500条
        return paginationInterceptor;
    }


}
