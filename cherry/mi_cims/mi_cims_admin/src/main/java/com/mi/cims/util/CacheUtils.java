package com.mi.cims.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.mi.cims.bean.pojo.LoginedManager;
import com.mi.cims.constant.ErrorCode;
import com.mi.cims.constant.HttpHeaderNames;
import com.mi.cims.constant.HttpRequestNames;
import com.mi.cims.exception.AuthorityException;
import com.mi.cims.service.RedisService;

/**
 * ClassName: CacheUtils
 * Function: 缓存工具类
 *
 * @author Magic Image-刘伟
 * @date 2017年9月29日 上午11:10:53
 * @version V1.0.0
 */
public class CacheUtils {

    /**
     * 已登录管理员缓存key前缀
     * */
    private static final String LOGINED_MANAGER_CACHE_KEY_PREFIX = "logined_manager_";

    /** 
     * getLoginedManagerCacheKey:取得已登录管理员缓存key
     * 
     * @author 刘伟 
     * @date 2017年10月19日 上午11:11:50
     * @param request HTTP请求
     * @return 访问Token
     * @throws Exception 
     */
    public static String getLoginedManagerCacheKey(HttpServletRequest request) throws Exception {
        // 从请求头中取
        String userToken = request.getHeader(HttpHeaderNames.ACCESS_TOKEN);
        if (userToken == null) {
            // 从请求参数中取
            userToken = request.getParameter(HttpRequestNames.ACCESS_TOKEN);
        }
        if (userToken == null) {
            throw new AuthorityException(ErrorCode.SESSION_TIMEOUT);
        }
        return LOGINED_MANAGER_CACHE_KEY_PREFIX + userToken;
    }

    /** 
     * saveLoginedManager:保存已登录管理员信息
     * 
     * @author 刘伟 
     * @date 2017年12月6日 下午9:02:48
     * @param redisService redis服务
     * @param loginedManager 已登录管理员信息
     * @param userToken 用户token
     * @param expireTime 超时时间
     * @throws Exception 
     */
    public static void saveLoginedManager(RedisService redisService, LoginedManager loginedManager, String userToken,
            Long expireTime) throws Exception {
        // 已登录管理员缓存key
        String cacheKey = LOGINED_MANAGER_CACHE_KEY_PREFIX + userToken;
        // 保存登录管理员信息到缓存中
        redisService.set(cacheKey, loginedManager, expireTime);
    }

    /** 
     * updateLoginedManager:更新已登录管理员信息
     * 
     * @author 刘伟 
     * @date 2017年12月7日 下午7:41:44
     * @param redisService redis服务
     * @param loginedManager 已登录管理员信息
     * @param request HTTP请求
     * @param expireTime 超时时间
     * @throws Exception 
     */
    public static void updateLoginedManager(RedisService redisService, LoginedManager loginedManager,
            HttpServletRequest request, Long expireTime) throws Exception {
        // 已登录管理员缓存key
        String cacheKey = getLoginedManagerCacheKey(request);
        // 重新设置登录管理员信息到缓存
        redisService.set(cacheKey, loginedManager, expireTime);
    }

    /** 
     * getLoginedManager:取得已登录管理员信息
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:15:14
     * @param redisService redis服务
     * @param request HTTP请求
     * @return 已登录管理员信息
     * @throws Exception 
     */
    public static LoginedManager getLoginedManager(RedisService redisService, HttpServletRequest request)
            throws Exception {
        // 已登录管理员缓存key
        String userToken = getLoginedManagerCacheKey(request);
        // 取得已登录管理员信息
        LoginedManager loginedManager = redisService.getObject(userToken, LoginedManager.class);
        if (loginedManager == null) {
            throw new AuthorityException(ErrorCode.SESSION_TIMEOUT);
        }
        return loginedManager;
    }

    /** 
     * getLoginedManagerId:取得登录管理员ID
     * 
     * @author 刘伟 
     * @date 2017年9月29日 下午12:49:20
     * @param redisService redis服务
     * @param request HTTP请求
     * @return
     * @throws Exception 
     */
    public static Integer getLoginedManagerId(RedisService redisService, HttpServletRequest request) throws Exception {
        LoginedManager loginedManager = getLoginedManager(redisService, request);
        return loginedManager.getId();
    }

    /** 
     * deleteLoginedManager:删除指定的已登录管理员信息
     * 
     * @author 刘伟 
     * @date 2017年12月7日 上午9:14:14
     * @param redisService redis服务
     * @param managerId 管理员ID
     * @throws Exception 
     */
    public static void deleteLoginedManager(RedisService redisService, int managerId) throws Exception {
        // 取得所有已登录管理员key集合
        Set<String> cacheKeySet = redisService.keys(LOGINED_MANAGER_CACHE_KEY_PREFIX + "*");
        Iterator<String> keyIterator = cacheKeySet.iterator();
        // 根据管理员ID查找要删除的管理员信息
        while (keyIterator.hasNext()) {
            String cacheKey = keyIterator.next();
            LoginedManager loginedManager = redisService.getObject(cacheKey, LoginedManager.class);
            if (loginedManager == null) {
                continue;
            }
            // 删除该管理员的登录信息
//            if (loginedManager.getManagerId() == managerId) {
//                redisService.remove(cacheKey);
//            }
        }
    }

    /** 
     * getAllLoginedManager:取得所有已登录管理员信息
     * 
     * @author 刘伟 
     * @date 2017年12月8日 下午3:58:39
     * @param redisService 缓存服务
     * @return 已登录管理员信息列表
     * @throws Exception 
     */
    public static List<LoginedManager> getAllLoginedManager(RedisService redisService) throws Exception {
        // 取得所有已登录管理员key集合
        Set<String> cacheKeySet = redisService.keys(LOGINED_MANAGER_CACHE_KEY_PREFIX + "*");
        Iterator<String> keyIterator = cacheKeySet.iterator();
        // 根据管理员ID查找要删除的管理员信息
        List<LoginedManager> list = new ArrayList<LoginedManager>();
        while (keyIterator.hasNext()) {
            String cacheKey = keyIterator.next();
            LoginedManager loginedManager = redisService.getObject(cacheKey, LoginedManager.class);
            if (loginedManager == null) {
                continue;
            }
            list.add(loginedManager);
        }
        return list;
    }
}
