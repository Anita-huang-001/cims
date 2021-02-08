package com.mi.cims.bean.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mi.cims.constant.ResultCode;

import lombok.Data;

/**
 * 
 * ClassName: ResultInfo Function: 接口返回结果.
 *
 * @author Magic Image-张万创
 * @date Nov 15, 2017 9:15:08 PM
 * @version V1.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultInfo implements Serializable {
	
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 返回类型 0：成功 1：失败
     */
    private String resultType;

    /**
     * 返回信息码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMsg;

    /**
     * 返回携带数据
     */
    private Object resultData;
    
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();
    
    // 默认构造方法
    public ResultInfo() {
		this.resultType = ResultCode.TYPE_SUCCESS;
		this.resultCode = ResultCode.SUCCESS;
		//this.resultMsg = "处理成功";
    }
    
    // 默认成功并返回信息
    public ResultInfo(String resultCode, String resultMsg) {
		this.resultType = ResultCode.TYPE_SUCCESS;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
    }
    
    // 默认成功并返回信息加数据
    public ResultInfo(String resultMsg, Object resultData) {
		this.resultType = ResultCode.TYPE_SUCCESS;
		this.resultCode = ResultCode.SUCCESS;
		this.resultMsg = resultMsg;
		this.resultData = resultData;
    }
    
    // 默认成功并返回信息加数据
    public ResultInfo(String resultCode, String resultMsg, Object resultData) {
		this.resultType = ResultCode.TYPE_SUCCESS;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultData = resultData;
    }
    
    // 返回成功相关信息
    public ResultInfo(String resultType, String resultCode, String resultMsg) {
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
    }
    
    // 返回成功相关信息加数据
    public ResultInfo(String resultType, String resultCode, String resultMsg, Object resultData) {
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultData = resultData;
    }

}
