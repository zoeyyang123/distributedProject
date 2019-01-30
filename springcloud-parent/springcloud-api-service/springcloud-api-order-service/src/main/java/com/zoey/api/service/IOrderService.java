package com.zoey.api.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IOrderService class
 *
 * @author zhaoyi
 * @date 18-10-17
 */
public interface IOrderService {

    @RequestMapping("/orderToMember")
    String  orderToMember(String name);
}
