package com.loki.sass.service.resident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.loki.sass.feignclient.*"})
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableHystrix
@EnableCaching
@ComponentScan("com.loki.sass")
public class SassServiceResidentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SassServiceResidentApplication.class, args);
    }

}
