package com.mi.cims.service;

import java.util.Set;

/**
 * ClassName: RedisService
 * Function: redis缓存服务
 *
 * @author Magic Image-刘伟
 * @date 2017年9月29日 下午1:22:09
 * @version V1.0.0
 */
public interface RedisService {

    /** 
     * keys:查找符合条件的key
     * 
     * @author 刘伟 
     * @date 2017年10月25日 下午12:14:55
     * @param pattern key值正则表达式
     * @return 符合条件的key集合
     */
    public Set<String> keys(final String pattern);

    /** 
     * set:写入缓存
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:22:18
     * @param key 键
     * @param value 缓存对象
     */
    public void set(final String key, Object value);

    /** 
     * set:写入缓存
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:22:18
     * @param key 键
     * @param value 缓存对象
     * @param expireTime 超时时间（秒）
     */
    public void set(final String key, Object value, Long expireTime);

    /** 
     * expire:设置键值的超时时间
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:22:18
     * @param key 键
     * @param expireTime 超时时间（秒）
     */
    public void expire(final String key, Long expireTime);

    /** 
     * getString:取得字符串
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:21:52
     * @param key 键
     * @return 
     */
    public String getString(final String key);

    /**   
     * @Title: get   
     * @Description: 取得缓存
     * @author: 刘伟 
     * @date: 2017年9月8日 上午10:09:43 
     * @param key 键
     * @return 缓存对象
     */
    public <T> T getObject(final String key, Class<T> clazz);

    /** 
     * remove:删除缓存
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:23:35
     * @param key 键
     */
    public void remove(final String key);

    /** 
     * exists:某缓存是否存在
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午1:23:49
     * @param key 键
     * @return 是否存在
     */
    public boolean exists(final String key);

    /** 
     * generatePK:生成序列号
     * 
     * @author 刘伟 
     * @date 2017年10月13日 下午3:21:48
     * @param key 键值
     * @return 序列号
     */
    public long generatePK(String key);

    /** 
     * generatePK:生成序列号
     * 
     * @author 刘伟 
     * @date 2017年10月13日 下午3:21:48
     * @param key 键值
     * @param value 要重置的值
     */
    public void resetPK(String key, long value);
}

