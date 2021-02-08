package com.mi.cims.bean.bo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.mi.cims.constant.PatternRegexp;

import lombok.Data;

/**
 * ClassName: LoginInfoBo
 * Function:  登录对象信息
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
@Data
public class LoginInfoBo {

	@NotBlank(message = "parameter.error")
	@Size(max = 32, message = "parameter.error")
    @Pattern(regexp = PatternRegexp.loginId, message = "loginId")
	// 管理员用户登录ID
    private String managerId;

	@NotBlank(message = "parameter.error")
    // 管理员用户密码
    private String managerPwd;   

}
