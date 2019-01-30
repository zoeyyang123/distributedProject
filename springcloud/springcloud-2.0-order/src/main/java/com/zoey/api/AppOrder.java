package com.zoey.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * AppOrder class
 *
 * @author zhaoyi
 * @date 18-10-12
 */
@SpringBootApplication
public class AppOrder {

    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class,args);
    }

    /**
     * 这里必须加上@LoadBalance注解开启ribbon负载均衡才能用服务名称的方式调用接口
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
