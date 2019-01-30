package com.zoey.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * OrderController class
 *
 * @author zhaoyi
 * @date 18-10-12
 */
@RestController
public class OrderController {


    /**
     * eureka默认整合了ribbon负载均衡器的
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/order")
    public String getOrder(){

        String url = "http://app-zoey-member/member";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


}
