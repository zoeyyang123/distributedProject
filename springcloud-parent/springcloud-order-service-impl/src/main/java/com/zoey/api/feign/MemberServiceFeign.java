package com.zoey.api.feign;


import com.zoey.api.entity.UserEntity;
import com.zoey.api.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IMemberService class
 *
 * @author zhaoyi
 * @date 18-10-17
 */
@FeignClient("app-zoey-member")
public interface MemberServiceFeign extends IMemberService {

    @Override
    @RequestMapping("/getMember")
    UserEntity getMember(@RequestParam("name")String name);
}
