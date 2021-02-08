package com.mi.cims.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mi.cims.service.RedisService;

/**
 * ClassName: RedisServiceImpl
 * Function: redis缓存服务实现
 *
 * @author Magic Image-刘伟
 * @date 2017年9月29日 下午1:24:22
 * @version V1.0.0
 */
@Service
public class RedisServiceImpl implements RedisService {

    // 连接池模板
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /** 
     * keys:查找符合条件的key
     * 
     * @author 刘伟 
     * @date 2017年10月25日 下午12:14:55
     * @param pattern key值正则表达式
     * @return 符合条件的key集合
     */
    public Set<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /** 
     * set:写入缓存
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:22:18
     * @param key 键
     * @param value 缓存对象
     */
    public void set(final String key, Object value) {
        if (value == null) {
            return;
        }
        String redisValue;
        if (value instanceof String) {
            redisValue = value.toString();
        } else {
            redisValue = JSON.toJSONString(value);
        }
        redisTemplate.opsForValue().set(key, redisValue);
    }

    /** 
     * set:写入缓存
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:22:18
     * @param key 键
     * @param value 缓存对象
     * @param expireTime 超时时间（秒）
     */
    public void set(final String key, Object value, Long expireTime) {
        if (value == null) {
            return;
        }
        String redisValue;
        if (value instanceof String) {
            redisValue = value.toString();
        } else {
            redisValue = JSON.toJSONString(value);
        }
        redisTemplate.opsForValue().set(key, redisValue, expireTime, TimeUnit.SECONDS);
    }

    /** 
     * expire:设置键值的超时时间
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:22:18
     * @param key 键
     * @param expireTime 超时时间（秒）
     */
    public void expire(final String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /** 
     * getString:取得字符串
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:21:52
     * @param key 键
     * @return 
     */
    public String getString(final String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**   
     * @Title: get   
     * @Description: 取得缓存
     * @author: 刘伟 
     * @date: 2017年9月8日 上午10:09:43 
     * @param key 键
     * @return 缓存对象
     */
    public <T> T getObject(final String key, Class<T> clazz) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        } else {
            return JSON.parseObject(value, clazz);
        }
    }

    /** 
     * remove:删除缓存
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:23:35
     * @param key 键
     */
    public void remove(final String key) {
        redisTemplate.delete(key);
    }

    /** 
     * exists:某缓存是否存在
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:23:49
     * @param key 键
     * @return 是否存在
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /** 
     * generatePK:生成序列号
     * 
     * @author 刘伟 
     * @date 2017年10月13日 下午3:21:48
     * @param key 键值
     * @return 序列号
     */
    public long generatePK(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /** 
     * resetPK:生成序列号
     * 
     * @author 刘伟 
     * @date 2017年10月13日 下午3:21:48
     * @param key 键值
     * @param value 要重置的值
     */
    public void resetPK(String key, long value) {
        set(key, value);
    }
}
