package com.mi.cims.bean.bo.userInfo;

import lombok.Data;

@Data
public class GetUserInfoBo {

	// 用户登录id
	private String loginId;
	
	// 用户角色id
	private String roleId;
}
