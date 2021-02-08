package com.mi.cims.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mi.cims.aop.annotation.Operation;
import com.mi.cims.bean.pojo.LoginedManager;
import com.mi.cims.constant.ErrorCode;
import com.mi.cims.exception.AuthorityException;
import com.mi.cims.service.RedisService;
import com.mi.cims.util.CacheUtils;

/**   
 * @ClassName: AuthenticationInterceptor   
 * @Description: 权限拦截器  
 * @author: 刘伟
 * @date: 2017年9月7日 下午6:41:25   
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    // 缓存服务
    @Autowired
    private RedisService redisService;

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
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的操作权限注解
        Operation optAuthority = method.getAnnotation(Operation.class);
        if (optAuthority == null || optAuthority.value() == null || optAuthority.value().length == 0) {
            // 如果方法未设置Authority权限注解，或权限为空, 说明不需要拦截, 直接放过
            return true;
        }

        // 解决拦截器中无法注入bean问题
        if (redisService == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request
                    .getServletContext());
            redisService = (RedisService) factory.getBean("redisService");
        }

        // 取得该用户的操作权限
        LoginedManager sessionData = CacheUtils.getLoginedManager(redisService, request);
        Set<String> operationSet = sessionData.getOperationSet();
        // 判断用户是否拥有此操作权限
        for (int i = 0; i < optAuthority.value().length; i++) {
            if (operationSet.contains(optAuthority.value()[i])) {
                return true;
            }
        }
        // 如果没有操作权限，抛出权限异常
        throw new AuthorityException(ErrorCode.PERMISSION_DENIED);
    }
}
