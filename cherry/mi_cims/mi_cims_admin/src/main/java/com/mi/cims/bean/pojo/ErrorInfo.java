package com.mi.cims.bean.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName: ErrorInfoVo
 * @Description: 错误返回信息
 * @author: 刘伟
 * @date: 2017年9月5日 上午9:16:15
 */
@Data
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = -8380730672450810658L;

    /**
     * 错误码
     * */
    private String errCode;

    /**
     * 错误信息
     * */
    private String errMessage;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

}
