package com.zoey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoey.test01.service.UserServiceTest01;
import com.zoey.test02.service.UserServiceTest02;


@RestController
public class MybatisMultilDataSourceController {
	@Autowired
	private UserServiceTest01 userServiceTest01;
	@Autowired
	private UserServiceTest02 userServiceTest02;

	@RequestMapping("/insertUserTest1")
	public Integer insertUserTest1(String name, Integer age) {
		return userServiceTest01.insertUser(name, age);
	}

	@RequestMapping("/insertUserTest2")
	public Integer insertUserTest2(String name, Integer age) {
		return userServiceTest02.insertUser(name, age);
	}
}
