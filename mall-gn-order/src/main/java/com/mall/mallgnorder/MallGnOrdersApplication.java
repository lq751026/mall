package com.mall.mallgnorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching  //开启缓存
@EnableFeignClients  //启用openign的支持
public class MallGnOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallGnOrdersApplication.class, args);
	}

}
