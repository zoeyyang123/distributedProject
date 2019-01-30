package com.zoey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * AppMember class
 *
 * @author zhaoyi
 * @date 18-10-11
 */
@SpringBootApplication
@EnableEurekaClient
public class AppMember {

    public static void main(String[] args) {
        SpringApplication.run(AppMember.class ,args);
    }
}
