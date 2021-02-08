package com.mi.cims.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.mi.cims.bean.bo.ChangeMatrixPwdBo;
import com.mi.cims.bean.pojo.MenuItem;

/**
 * ClassName: HomeService
 * Function: 主页业务处理服务接口
 *
 * @author Magic Image-刘伟
 * @date 2017年10月12日 下午3:57:49
 * @version V1.0.0
 */
public interface HomeService {

    /** 
     * getMenuList:取得菜单列表
     * 
     * @author 刘伟 
     * @date 2017年10月16日 下午7:13:35
     * @param request HTTP请求
     * @return 菜单列表
     * @throws Exception 
     */
    public List<MenuItem> getMenuList(HttpServletRequest request) throws Exception;

    /** 
     * getOperationCodeSet:取得操作编码集合
     * 
     * @author 刘伟 
     * @date 2017年10月19日 下午2:32:26
     * @param request HTTP请求
     * @return 操作编码集合
     * @throws Exception 
     */
    public Set<String> getOperationCodeSet(HttpServletRequest request) throws Exception;

    /**   
     * @Title: changeMatrixPwd
     * @Description: 修改用户登录密码
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  ChangeMatrixPwdBo 修改密码信息
     * @param:  loginManagerId 	       管理员ID
     * @param:  request			       请求信息
     * @throws: Exception
     */
    public void changeMatrixPwd(ChangeMatrixPwdBo changeMatrixPwdBo, Integer loginManagerId, HttpServletRequest request) throws Exception;

}
