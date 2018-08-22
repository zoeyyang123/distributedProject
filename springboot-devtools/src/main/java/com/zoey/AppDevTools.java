package com.zoey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AppDevTools class
 * 首先要在pom依赖中添加
 * 然后ctrl+alt+a 搜索registry
 * 找到compiler.automake.allow.when.app.running，勾上开启此功能
 * idea的这个功能比较蠢  因为idea会自动保存 重新make的频率非常高
 * @author zhaoyi
 * @date 18-8-22
 */
@SpringBootApplication
public class AppDevTools {
    // 1.实现注解版本 数据源

    public static void main(String[] args) {
        SpringApplication.run(AppDevTools.class, args);
    }
}
