package com.mi.cims.bean.po;

import lombok.Data;

@Data
public class UserInfo {
	// 用户主键ID
	private Integer userId;
	
	// 用户登录id
	private String loginId;
	
	// 用户登录密码
	private String passWord;

	// 用户名字
	private String userName;
	
	// 用户电话号码
	private String userPhone;
	
	// 用户电子邮件
	private String mail;
	
	// 删除标识
	private String deleteFlag;
	
	// 用户最终登陆时间
	private long lastLoginTime;
	
	// 创建日
	private long createTime;
	
	// 创建者ID
	private int createId;
	
	// 最终更新日
	private long updateTime;
	
	// 最终更新者ID
	private int updateId;
}
