package com.zoey.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * OrderNumGenerator class
 *
 * @author zhaoyi
 * @date 18-7-30
 */
public class OrderNumGenerator {
    //业务ID
    private static volatile int count;

    public String getNumber(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpleDateFormat.format(new Date())+"-"+ ++count;
    }
}
