package com.zoey.lock.service;

import com.zoey.lock.OrderNumGenerator;

/**
 * OrderService class
 *
 * @author zhaoyi
 * @date 18-7-30
 */
public class OrderService implements Runnable {

    //生成订单号
    OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    public void run() {
        //模拟用户生成订单号
        getNumber();
    }

    public void getNumber(){
        String number =  orderNumGenerator.getNumber();
        System.out.println(Thread.currentThread().getName()+"####生成唯一订单号："+ number);
    }

    public static void main(String[] args) {
        System.out.println("###模拟生成订单号开始");
        for (int i = 0; i < 100; i++) {
            new Thread(new OrderService()).start();
        }
    }
}
