package com.mi.cims.bean.bo;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.mi.cims.constant.PatternRegexp;

import lombok.Data;

/**
 * @ClassName:	 ChangePwdBo
 * @Description: 登录对象信息
 * @author:		    孙忠飞
 * @date: 		 2021年1月25日 下午4:44:46
 */

@Data
public class ChangePwdBo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 用户登录名
	@NotBlank(message = "00000")
	@Pattern(regexp = PatternRegexp.loginId, message = "10001")
	private String loginId;
	
	// 旧密码
	@NotBlank(message = "00000")
	private String oldPwd;
	
	// 新密码
	@NotBlank(message = "00000")
	private String newPwd;
	
	// 确认密码
	@NotBlank(message = "00000")
	private String confirmPwd;

}
