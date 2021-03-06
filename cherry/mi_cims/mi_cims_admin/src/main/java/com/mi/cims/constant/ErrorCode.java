package com.mi.cims.constant;

/**
 * @ClassName: ErrorCode
 * @Description: 错误码
 * @author: 刘伟
 * @date: 2017年9月5日 上午9:32:09
 */
public interface ErrorCode {

	/**
	 * 共通错误:00000* 管理员登录：00100* 用户管理：00200*
	 * 
	 */

	/**
	 * 参数异常
	 */
	public static final String REQUEST_PARAM_ERROR = "000001";

	/**
	 * 分页参数错误
	 */
	public static final String PAGE_PARAM_ERROR = "000002";

	/**
	 * 该数据不存在可能已被删除
	 */
	public static final String DATA_NOT_EXIST_ERROR = "000003";

	
	
	/**
	 * 登录名或密码错误
	 */
	public static final String ACCT_OR_PWD_ERROR = "001001";

	/**
	 * 登录名不存在
	 */
	public static final String ACCT_NOT_EXIST = "001002";

	/**
	 * 旧密码错误
	 */
	public static final String OLD_PWD_ERROR = "001003";

	/**
	 * 新密码与确认密码不一致
	 */
	public static final String NEWPWD_CFMPWD_NOT_SAME = "001004";

	
	
	
	/**
	 * 登录名已经存在
	 */
	public static final String ACCT_ALREADY_EXIST = "002001";
	
	/**
	 * 用户不存在
	 */
	public static final String USER_NOT_EXIST = "002002";
	
	
	

	/**
	 * 权限错误
	 */
	public static final String PERMISSION_DENIED = "900001";

	/**
	 * 不支持的语言类型
	 */
	public static final String NOT_SUPPORT_LANGUAGE = "900002";

	/**
	 * 会话超时
	 */
	public static final String SESSION_TIMEOUT = "900003";

	/**
	 * 系统异常
	 */
	public static final String SYSTEM_ERROR = "999999";
	
}
