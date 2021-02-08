package com.mi.cims.bean.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * ClassName: ChangeMatrixPwdBo
 * Function:  修改密码对象信息
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
@Data
public class ChangeMatrixPwdBo {

	@NotBlank(message = "parameter.error")
	// 旧密码
    private String oldMatrixPwd;

	@NotBlank(message = "parameter.error")
    // 新密码
    private String matrixPwd;

	@NotBlank(message = "parameter.error")
    // 确认密码
    private String matrixPwdForSure;

}
