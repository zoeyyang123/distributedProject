package com.zoey.controller;

import com.zoey.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController class
 *
 * @author zhaoyi
 * @date 18-7-4
 */
@RestController
public class IndexController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/setString")
    public String setString(String key, String value) {
        redisService.setStr(key, value,300L);
        return "success";
    }

    @RequestMapping("/getKey")
    public String getKey(String key){
        String result = (String) redisService.getKey(key);
        return result;
    }
    @RequestMapping("/delString")
    public String delString(String key){
        redisService.delKey(key);
        return "success";
    }

}

