package com.mi.cims.dao;

import java.util.List;

import com.mi.cims.bean.po.UserRole;

public interface UserRoleMapper {
 
	// 根据用户ID查询所拥有的角色
	List<UserRole> selectRoleByUserId(Integer userId);
}