package com.mi.cims.exception;

/**   
 * @ClassName: ParamValidException   
 * @Description: 请求参数异常
 * @author: 刘伟
 * @date: 2017年9月5日 上午9:20:07   
 */
public class ParamValidException extends ClientBaseException {

	private static final long serialVersionUID = 1892562202202196198L;

	public ParamValidException(String code) {
		super(code);
	}

	public ParamValidException(String code, Object[] param) {
		super(code, param);
	}
}
