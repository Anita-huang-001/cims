package com.mi.cims.service;

import com.mi.cims.bean.bo.LoginInfoBo;
import com.mi.cims.bean.vo.UserInfoVo;

/**
 * ClassName: LoginService
 * Function:  登录服务接口
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
public interface LoginService {

    /** 
     * checkLogin:登录验证
     * 
     * @author 刘伟 
     * @date 2017年9月26日 上午10:30:35
     * @param loginInfo  登录信息
     * @return 管理员信息
     * @throws Exception 
     */
    public UserInfoVo checkLogin(LoginInfoBo loginInfo) throws Exception;
}
