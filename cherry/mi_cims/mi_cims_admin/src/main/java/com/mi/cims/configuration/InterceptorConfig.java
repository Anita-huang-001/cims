package com.mi.cims.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mi.cims.interceptor.AuthenticationInterceptor;
import com.mi.cims.interceptor.SessionInterceptor;

/**   
 * @ClassName: InterceptorConfig   
 * @Description: 拦截器配置  
 * @author: 刘伟
 * @date: 2017年9月7日 下午6:19:37   
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /** 
     * sessionInterceptor:会话拦截器对象
     * 
     * @author 刘伟 
     * @date 2017年10月10日 下午6:22:32
     * @return 
     */
    @Bean
    SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    /** 
     * authenticationInterceptor:权限拦截器对象
     * 
     * @author 刘伟 
     * @date 2017年10月10日 下午6:23:35
     * @return 
     */
    @Bean
    AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    /**   
     * @Title: addInterceptors   
     * @Description: 添加拦截器
     * @author: 刘伟 
     * @date: 2017年9月7日 下午6:19:37 
     * @param: registry 拦截器注册器
     */
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加会话拦截器
        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/")
                .excludePathPatterns("/login").excludePathPatterns("/login/**").excludePathPatterns("/init/**")
                .excludePathPatterns("/list/dbType");
        // 添加权限拦截器
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/")
                .excludePathPatterns("/login").excludePathPatterns("/login/**").excludePathPatterns("/init/**")
                .excludePathPatterns("/list/dbType");
    }

}
