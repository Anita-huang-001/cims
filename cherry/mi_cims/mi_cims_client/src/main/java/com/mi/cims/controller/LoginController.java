package com.mi.cims.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mi.cims.bean.bo.ChangePwdBo;
import com.mi.cims.bean.bo.LoginInfoBo;
import com.mi.cims.bean.pojo.ResultInfo;
import com.mi.cims.constant.HttpHeaderNames;
import com.mi.cims.service.LoginService;
import com.mi.cims.util.MiUtils;

/**
 * ClassName: LoginController 
 * Function:  默认页处理控制器
 *
 * @author 	        孙忠飞
 * @date 	  2021年1月26日 下午3:47:12
 * @version   V1.0.0
 */
@RestController
public class LoginController {

    // 登录验证服务
    @Autowired
    private LoginService loginService;

    // HTTP请求
    @Autowired
    private HttpServletRequest request;

    /**   
     * @Title: checkLogin
     * @Description: 登录验证
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  loginInfo 登录信息
     * @return: ResultInfo 成功的用户信息
     * @throws: Exception
     */
    @PostMapping("/login")
    public ResultInfo login(@Valid @RequestBody LoginInfoBo loginInfoBo) throws Exception {
		// 获得请求语言
		Locale locale = MiUtils.parseLocale(request.getHeader(HttpHeaderNames.CLIENT_LANGUAGE));
    	// 验证用户登录并返回结果
		return loginService.checkLogin(loginInfoBo,locale);
    }

    /**   
     * @Title: changePwd
     * @Description: 修改密码
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  changePwdBo 要修改用户的密码信息
     * @return: ResultInfo  成功的用户信息
     * @throws: Exception
     */
    @PutMapping("/changePwd")
    public ResultInfo changePwd(@Valid @RequestBody ChangePwdBo changePwdBo) throws Exception {
		// 获得请求语言
		Locale locale = MiUtils.parseLocale(request.getHeader(HttpHeaderNames.CLIENT_LANGUAGE));
        // 用户修改密码并返回结果
		return loginService.changePwd(changePwdBo, locale);
    }
    
}
