package com.zoey.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisService class
 *
 * @author zhaoyi
 * @date 18-7-4
 */
@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @SuppressWarnings("rawtypes")

    public void setStr(String key, String value) {
        this.setObject(key, value, null);
    }
    public void setStr(String key, String value, Long time) {
        this.setObject(key, value, time);
    }
    public void setList(String key, List<String> value) {
        this.setObject(key, value, null);
    }
    public void setList(String key, List<String> value, Long time) {
        this.setObject(key, value, time);
    }
    public void setSet(String key, Set<String> value) {
        this.setObject(key, value, null);
    }
    public void setSet(String key, Set<String> value, Long time) {
        this.setObject(key, value, time);
    }
    public void setHash(String key, HashMap<String,String> value) {
        this.setObject(key, value, null);
    }
    public void setStr(String key, HashMap<String,String> value, Long time) {
        this.setObject(key, value, time);
    }

    public void setObject(String key, Object value, Long time) {

        if (StringUtils.isEmpty(key) || value ==null){
            return;
        }

        if (value instanceof String){
            String strValue = (String) value;
            stringRedisTemplate.opsForValue().set(key, strValue);
            if (time != null) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }
        if (value instanceof List){
            List<String> listValue = (List<String>) value;
            for (String s : listValue) {
                stringRedisTemplate.opsForList().leftPush(key,s);
            }
            if (time != null) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }
        if (value instanceof Set){
            Set<String> listValue = (Set<String>) value;
            for (String s : listValue) {
                stringRedisTemplate.opsForSet().add(key,s);
            }
            if (time != null) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }
        if (value instanceof HashMap){
            HashMap<String,String> hashMapValue = (HashMap) value;
            for (String hashKey : hashMapValue.keySet()) {
                stringRedisTemplate.opsForHash().put(key,hashKey,hashMapValue.get(hashKey));
            }
            if (time != null) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }


    }

    public Object getKey(String key) {
        Object result = stringRedisTemplate.opsForValue().get(key);
        return result;
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
