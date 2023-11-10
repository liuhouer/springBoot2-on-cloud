package com.imooc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient // Spring Cloud注解，用于向服务注册中心注册服务。
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
