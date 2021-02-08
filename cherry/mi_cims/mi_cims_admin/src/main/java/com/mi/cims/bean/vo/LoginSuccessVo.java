package com.mi.cims.bean.vo;

import lombok.Data;

/**
 * @ClassName: LoginSuccessInfoVo
 * @Description: 登录成功信息
 * @author: 刘伟
 * @date: 2017年9月19日 下午5:25:37
 */
@Data
public class LoginSuccessVo {

    /**
     * 用户token
     */
    private String token;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

}
