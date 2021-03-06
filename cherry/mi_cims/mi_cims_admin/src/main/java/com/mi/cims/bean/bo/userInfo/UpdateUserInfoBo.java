package com.mi.cims.bean.bo.userInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.mi.cims.constant.PatternRegexp;

import lombok.Data;

@Data
public class UpdateUserInfoBo {
	
	@NotNull(message = "parameter.error")
	// 用户主键ID
	private Integer userId;
	
	// 非空校验
	@NotBlank(message = "parameter.error")
	// 大小校验
	@Size(max = 32, message = "parameter.error")
	// 正则校验
    @Pattern(regexp = PatternRegexp.loginId, message = "loginId")
	// 用户登录名
	private String loginId;
	
	@NotBlank(message = "parameter.error")
	@Size(max = 64, message = "parameter.error")
	// 用户名字
	private String userName;
	
	@NotBlank(message = "parameter.error")
	@Size(max = 11, message = "parameter.error")
	@Pattern(regexp = PatternRegexp.phone, message = "parameter.error")
	// 用户电话号码
	private String userPhone;
	
	// 用户电子邮件
	private String mail;
	
	@NotNull(message = "parameter.error")
	// 用户角色id
	private Integer roleId;

}
