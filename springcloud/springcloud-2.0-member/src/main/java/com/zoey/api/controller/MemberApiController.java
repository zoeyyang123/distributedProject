package com.zoey.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberApiController class
 *
 * @author zhaoyi
 * @date 18-10-11
 */
@RestController
public class MemberApiController {

    @Value("${server.port}")
    private String port;
    @RequestMapping("/member")
    public String getMember(){
        return "springcloud-2.0 会员服务。port:"+port;
    }
}
