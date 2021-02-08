package com.mi.cims.exception;

/**   
 * @ClassName: BusinessException   
 * @Description: 业务逻辑异常
 * @author: 刘伟
 * @date: 2017年9月7日 上午11:38:14   
 */
public class BusinessException extends AdminBaseException {

	private static final long serialVersionUID = 1892562202202196198L;

	public BusinessException(String code) {
		super(code);
	}

	public BusinessException(String code, Object[] param) {
		super(code, param);
	}
}
