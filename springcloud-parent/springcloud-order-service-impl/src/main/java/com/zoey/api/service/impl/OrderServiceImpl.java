package com.zoey.api.service.impl;


import com.zoey.api.entity.UserEntity;
import com.zoey.api.feign.MemberServiceFeign;
import com.zoey.api.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @Override
    @RequestMapping("/orderToMember")
    public String orderToMember(@RequestParam("name")String name) {
        UserEntity user = memberServiceFeign.getMember(name);
        return user == null?"没找到用户信息":user.toString();
    }
}
