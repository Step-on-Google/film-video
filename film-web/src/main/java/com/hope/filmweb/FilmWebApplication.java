package com.hope.filmweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * web启动类
 *
 * @EnableTransactionManagement 开启事物管理
 * @EnableEurekaClient 只用于eureka为注册中心
 * @EnableDiscoveryClient 不仅仅是用于eureka做注册中心，也可以用作其他注册中心
 * @EnableHystrix 开启断路器
 * @author:Zhang jc
 * @date: 2018/11/16 10:42
 * @description:
 */
@SpringBootApplication(scanBasePackages = {"com.*"})
@ServletComponentScan
@MapperScan("com.zjc.dao")
@EnableTransactionManagement
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zjc.service.feign")
@EnableHystrix
public class FilmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmWebApplication.class, args);
    }
}
