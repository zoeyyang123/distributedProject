package com.zoey.api.service;

import com.zoey.api.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IMemberService class
 *
 * @author zhaoyi
 * @date 18-10-17
 */
public interface IMemberService {

    @RequestMapping("/getMember")
    UserEntity getMember(@RequestParam("name")String name);
}
