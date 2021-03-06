package com.shoping.mallgn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication
public class MallGnApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallGnApplication.class, args);
	}

}
