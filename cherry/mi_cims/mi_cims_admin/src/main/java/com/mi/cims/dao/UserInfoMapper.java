package com.mi.cims.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mi.cims.bean.bo.userInfo.GetUserInfoBo;
import com.mi.cims.bean.po.UserInfo;
import com.mi.cims.bean.po.UserRole;
import com.mi.cims.bean.vo.UserInfoVo;

public interface UserInfoMapper {
	
	// 查询所有用户信息
	List<UserInfo> selectAll();
	
	// 根据条件查询用户信息
	List<UserInfoVo> selectByCondittion(GetUserInfoBo getUserInfoBo);
	
	// 根据主键ID查询用户信息
	UserInfo selectByUserId(int userId);

	// 根据登录ID查询用户信息
	UserInfo selectByLoginId(String loginId);
	
	// 新建用户信息
	int insert(UserInfo userInfo);
	
	// 更新用户信息
	int update(@Param("userInfo")UserInfo userInfo, @Param("userRole")UserRole userRole);
	
	// 重置用户登录密码
	int resetPwd(UserInfo userInfo);
	
	// 删除用户信息
	int deleteUserInfo(@Param("deleteFlag")String deleteFlag, @Param("userId")Integer userId);
}
