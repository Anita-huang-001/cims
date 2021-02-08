package com.mi.cims.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mi.cims.service.RedisService;
import com.mi.cims.util.CacheUtils;

/**
 * ClassName: SessionInterceptor
 * Function: 会话拦截器
 *
 * @author Magic Image-刘伟
 * @date 2017年10月10日 下午3:25:51
 * @version V1.0.0
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    // 缓存服务
    @Autowired
    private RedisService redisService;

    // session超时时间
    @Value("${sesion.timeout}")
    private long sessionTimeout;

    /**   
     * @Title: preHandle  
     * @Description: 拦截器前处理
     * @author: 刘伟 
     * @date: 2017年9月7日 下午6:41:25 
     * @param: request 请求
     * @param: response 返回
     * @param: handler 处理器
     * @return: 拦截处理结果
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 解决拦截器中无法注入bean问题
        if (redisService == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request
                    .getServletContext());
            redisService = (RedisService) factory.getBean("redisService");
        }

        // 查看用户是否登录
        CacheUtils.getLoginedManager(redisService, request);

        // 刷新已登录用户的缓存时间
        String cacheKey = CacheUtils.getLoginedManagerCacheKey(request);
        redisService.expire(cacheKey, sessionTimeout * 60);

        return true;
    }
}

