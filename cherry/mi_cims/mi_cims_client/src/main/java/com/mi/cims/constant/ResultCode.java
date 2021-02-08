package com.mi.cims.constant;

/**
 * 
 * ClassName: 返回信息码
 *
 * @author	       孙忠飞
 * @date	  2021年1月25日 下午4:44:46
 * @version   V1.0.0
 */
public interface ResultCode {

    /**
     * 成功
     */
    public static final String TYPE_SUCCESS = "0";

    /**
     * 失败
     */
    public static final String TYPE_ERROR = "1";

    /**
     * 正常返回：0000
     */
    public static final String SUCCESS = "0000";

    
    /**
     * 用户已存在
     */
    public static final String ERROR_USER_EXIST = "1001";

    /**
     * 用户不存在
     */
    public static final String ERROR_USER_NOT_EXIST = "1002";
    
    /**
     * 密码错误，请输入正确密码
     */
    public static final String ERROR_PWD_CHECK_FAIL = "1003";
    
    /**
     * 请输入正确的旧密码
     */
    public static final String ERROR_OLDPWD_CHECK_FAIL = "1004";
    
    /**
     * 新密码与确认密码不一致
     */
    public static final String ERROR_NEWPWD_OR_CONFRIMPWD = "1005";
    
    
    /**
     * 系统错误
     */
    public static final String ERROR_UNKOWN = "9999";

}
