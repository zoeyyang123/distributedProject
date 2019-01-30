package com.zoey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * MemberApp class
 *
 * @author zhaoyi
 * @date 18-10-18
 */
@SpringBootApplication
@EnableEurekaClient
public class MemberApp {

    public static void main(String[] args) {
        SpringApplication.run(MemberApp.class,args);
    }
}
