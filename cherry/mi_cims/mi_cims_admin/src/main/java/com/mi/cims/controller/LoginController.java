package com.mi.cims.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi.cims.bean.bo.LoginInfoBo;
import com.mi.cims.bean.pojo.LoginedManager;
import com.mi.cims.bean.vo.LoginSuccessVo;
import com.mi.cims.bean.vo.UserInfoVo;
import com.mi.cims.dao.RoleOperationMapper;
import com.mi.cims.service.LoginService;
import com.mi.cims.service.RedisService;
import com.mi.cims.util.CacheUtils;
import com.mi.cims.util.MiUtils;

/**
 * ClassName: LoginController
 * Function:  登录控制器
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
@RestController
public class LoginController {

    // 登录验证服务
    @Autowired
    private LoginService loginService;

    // redis服务
    @Autowired
    private RedisService redisService;

    // 角色操作关系DAO
    @Autowired
    private RoleOperationMapper roleOperationMapper;

    // session超时时间
    @Value("${sesion.timeout}")
    private long sessionTimeout;

    /** 
     * loginCheck:登录校验
     * 
     * @author 刘伟 
     * @date 2017年10月11日 上午10:43:11
     * @param loginInfo 登录信息
     * @return 登录成功信息
     * @throws Exception 
     */
    @PostMapping("/login")
    public LoginSuccessVo login(@Valid LoginInfoBo loginInfo) throws Exception {
        // 验证管理员登录信息
    	UserInfoVo userInfoVo = loginService.checkLogin(loginInfo);

        // 用户访问Token
        String userToken = MiUtils.getUUID();

        // 已登录用户缓存对象
        LoginedManager loginedManager = new LoginedManager();
        // 设置ID
        loginedManager.setId(userInfoVo.getUserId());
        // 设置管理员ID
        loginedManager.setManagerId(userInfoVo.getLoginId());
        // 设置管理员姓名
        loginedManager.setManagerName(userInfoVo.getUserName());
        // 设置角色ID
        loginedManager.setRoleId(userInfoVo.getRoleId().toString());
        // 设置角色对应的权限
        Set<String> operationSet = roleOperationMapper.selectOperationCodeByRoleId(userInfoVo.getRoleId());
        loginedManager.setOperationSet(operationSet);
        // 保存登录管理员信息到缓存中
        CacheUtils.saveLoginedManager(redisService, loginedManager, userToken, sessionTimeout * 60);

        // 设置登录成功后用户访问token
        LoginSuccessVo loginSuccessVo = new LoginSuccessVo();
        // 设置阵列表token
        loginSuccessVo.setToken(userToken);

        return loginSuccessVo;
    }
}
