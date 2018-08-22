package com.zoey.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndedController class
 *
 * @author zhaoyi
 * @date 18-8-22
 */
@RestController
public class IndedController {

    @RequestMapping("/index")
    public String index(){
        return "springboot2.0.devtools.v1.0";
    }
    /*@RequestMapping("/testDevtools")
    public String testDevtools(){
        return "testDevtools-v1.0";
    }*/
}
