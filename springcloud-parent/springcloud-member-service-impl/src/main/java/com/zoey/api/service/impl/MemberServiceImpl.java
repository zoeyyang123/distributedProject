package com.zoey.api.service.impl;

import com.zoey.api.entity.UserEntity;
import com.zoey.api.service.IMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberServiceImpl class
 *
 * @author zhaoyi
 * @date 18-10-18
 */
@RestController
public class MemberServiceImpl implements IMemberService {

    @Override
    @RequestMapping("/getMember")
    public UserEntity getMember(@RequestParam("name")String name) {
        UserEntity user = new UserEntity();
        user.setAge(29);
        user.setName(name);
        return user;
    }
}
