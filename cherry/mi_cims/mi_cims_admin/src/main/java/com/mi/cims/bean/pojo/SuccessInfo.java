package com.mi.cims.bean.pojo;

import lombok.Data;

/**
 * ClassName: SuccessInfoVo
 * Function: 成功信息
 *
 * @author Magic Image-刘伟
 * @date 2017年9月26日 上午10:25:42
 * @version V1.0.0
 */
@Data
public class SuccessInfo {
	
    /**
     * 成功码
     * */
    private String sucCode;

    /**
     * 成功信息
     * */
    private String sucMessage;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();
    
    public SuccessInfo(){
    	this.sucCode = "0000";
    	this.sucMessage = "请求成功";
    }

}

