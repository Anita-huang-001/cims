package com.mi.cims.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mi.cims.bean.bo.LoginInfoBo;
import com.mi.cims.bean.po.UserInfo;
import com.mi.cims.bean.po.UserRole;
import com.mi.cims.bean.vo.UserInfoVo;
import com.mi.cims.constant.ErrorCode;
import com.mi.cims.dao.UserInfoMapper;
import com.mi.cims.dao.UserRoleMapper;
import com.mi.cims.exception.BusinessException;
import com.mi.cims.service.LoginService;

/**
 * ClassName: LoginServiceImpl
 * Function:  登录服务接口实现
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    // 用户信息
    @Autowired
    private UserInfoMapper userInfoMapper;
    
    // 用户角色信息
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**   
     * @Title: checkLogin
     * @Description: 登录验证
     * @author: 刘伟 
     * @date: 2017年9月22日 下午5:23:06
     * @param loginInfo 登录信息
     * @return 管理员信息
     * @throws Exception
     */
    public UserInfoVo checkLogin(LoginInfoBo loginInfo) throws Exception {

    	UserInfoVo userInfoVo = new UserInfoVo();
        // 取得登录信息
    	UserInfo userInfo = userInfoMapper.selectByLoginId(loginInfo.getManagerId());
        if (userInfo == null) {
            throw new BusinessException(ErrorCode.ACCT_NOT_EXIST);
        } else {
        	// 根据用户ID查询所拥有的角色
        	List<UserRole> UserRole = userRoleMapper.selectRoleByUserId(userInfo.getUserId());
            // 用户角色信息数组
            Integer[] roleId = new Integer[UserRole.size()];
        	for(int i=0;i<UserRole.size();i++) {
        		roleId[i] = UserRole.get(i).getRoleId();
        	}
        	// 权限数组升序排序
        	java.util.Arrays.sort(roleId);
        	// 如果登录用户不是超级管理员不可登录
        	if(roleId[0] != 1) {
        		throw new BusinessException(ErrorCode.ACCT_NOT_EXIST);	
        	}
        	// 用户密码验证失败
			if (!userInfo.getPassWord().equalsIgnoreCase(loginInfo.getManagerPwd())) {
				throw new BusinessException(ErrorCode.ACCT_OR_PWD_ERROR);
			}
	        
	        BeanUtils.copyProperties(userInfo, userInfoVo);
	        userInfoVo.setRoleId(roleId[0]);
        }
        return userInfoVo;
    }
}
