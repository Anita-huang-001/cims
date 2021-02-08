package com.mi.cims.bean.po;

import lombok.Data;

@Data
public class UserRole {
	// 用户角色主键ID
	private Integer userRoleId;
	
	// 用户ID
	private Integer userId;
	
	// 角色ID
	private Integer roleId;
    
	// 删除标识
	private String deleteFlag;

}
