package com.mi.cims.bean.bo;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.mi.cims.constant.PatternRegexp;

import lombok.Data;

/**
 * @ClassName:	 LoginInfoBo
 * @Description: 登录对象信息
 * @author:		    孙忠飞
 * @date: 		 2021年1月25日 下午4:44:46
 */
@Data
public class LoginInfoBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 用户登录ID
     * */
    @NotEmpty(message = "10001")
    @Pattern(regexp = PatternRegexp.loginId, message = "10001")
    private String loginId;

    /**
     * 用户登录密码
     * */
    @NotBlank(message = "00000")
    private String loginPwd;

}
