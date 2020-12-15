package com.motang.motangge.service.impl;

import com.motang.motangge.common.exception.BookRedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
/**
 * @description 封装常用redis操作工具
 * @author liuhu
 * @Date 2020/12/15 16:14
 */
@Service
@Slf4j
public class RedisService {


    private RedisTemplate<String,Object> redisTemplate;
    /**
     * @description 字符串get
     * @author liuhu
     * @param key
     * @date 2020/12/15 16:15
     * @return java.lang.Object
     */
    public Object get(String key){
        return StringUtils.isNotBlank(key)?redisTemplate.opsForValue().get(key):null;
    }

    /**
     * @description 字符串set
     * @author liuhu
     * @param key
     * @param value
     * @date 2020/12/15 16:15
     * @return void
     */
    public void set(String key,Object value){
       try {
           redisTemplate.opsForValue().set(key,value);
       }catch (Exception e){
           log.error("string redis set exception");
           throw new BookRedisException("string redis set exception");
       }
    }
}
