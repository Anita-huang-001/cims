package com.mi.cims.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi.cims.bean.bo.ChangeMatrixPwdBo;
import com.mi.cims.bean.pojo.MenuItem;
import com.mi.cims.bean.pojo.SuccessInfo;
import com.mi.cims.service.HomeService;
import com.mi.cims.service.RedisService;
import com.mi.cims.util.CacheUtils;

/**
 * ClassName: HomeController
 * Function: 主页处理控制器
 *
 * @author Magic Image-刘伟
 * @date 2017年10月19日 下午1:37:11
 * @version V1.0.0
 */
@RestController
public class HomeController {

    // home页面业务服务
    @Autowired
    private HomeService homeService;

    // redis服务
    @Autowired
    private RedisService redisService;

    // HTTP请求
    @Autowired
    private HttpServletRequest request;

    // session超时时间
    @Value("${sesion.timeout}")
    private long sessionTimeout;

    /** 
     * getMenuList:取得菜单列表
     * 
     * @author 刘伟 
     * @date 2017年10月19日 下午1:40:01
     * @return 菜单列表
     * @throws Exception 
     */
    @GetMapping("/home/menu")
    public List<MenuItem> getMenuList() throws Exception {
        return homeService.getMenuList(request);
    }

    /** 
     * getOperationCodeSet:取得操作编码权限集合
     * 
     * @author 刘伟 
     * @date 2017年10月19日 下午2:30:39
     * @return 操作编码集合
     * @throws Exception 
     */
    @GetMapping("/home/operation")
    public Set<String> getOperationCodeSet() throws Exception {
        return homeService.getOperationCodeSet(request);
    }

    /** 
     * logout:注销
     * 
     * @author 刘伟 
     * @date 2017年11月17日 下午5:12:02
     * @return 成功信息
     * @throws Exception 
     */
    @GetMapping("/logout")
    public SuccessInfo logout() throws Exception {
        // 已登录管理员缓存key
        String token = CacheUtils.getLoginedManagerCacheKey(request);
        // 从缓存中删除登录管理员信息
        redisService.remove(token);
        return new SuccessInfo();
    }

    /**
     * @Title: changeMatrixPwd
     * @Description: 修改用户登录密码
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  ChangeMatrixPwdBo 修改密码信息
     * @return: SuccessInfo 成功标识
     * @throws: Exception
     */
    @PutMapping("/matrixPwdChange")
    public SuccessInfo changeMatrixPwd(@Valid ChangeMatrixPwdBo changeMatrixPwdBo) throws Exception {
        // 获取登录管理员ID
        int loginManagerId = CacheUtils.getLoginedManagerId(redisService, request);
        // 修改管理员登录密码
        homeService.changeMatrixPwd(changeMatrixPwdBo, loginManagerId, request);
        return new SuccessInfo();
    }

}
