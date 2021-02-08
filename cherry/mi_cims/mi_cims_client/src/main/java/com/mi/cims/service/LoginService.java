package com.mi.cims.service;

import java.util.Locale;

import com.mi.cims.bean.bo.ChangePwdBo;
import com.mi.cims.bean.bo.LoginInfoBo;
import com.mi.cims.bean.pojo.ResultInfo;

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
     * @Title: checkLogin
     * @Description: 登录验证
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  loginInfo 登录信息
     * @return: ResultInfo 成功的用户信息
     * @throws: Exception
     */
    public ResultInfo checkLogin(LoginInfoBo loginInfo, Locale locale) throws Exception;
    
    /**   
     * @Title: changePwd
     * @Description: 修改密码
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  changePwdBo 要修改用户的密码信息
     * @return: ResultInfo  成功的用户信息
     * @throws: Exception
     */
    public ResultInfo changePwd(ChangePwdBo changePwdBo, Locale locale) throws Exception;
}
