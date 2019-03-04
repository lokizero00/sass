package com.loki.sass.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SassEurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SassEurakeApplication.class, args);
    }

}

