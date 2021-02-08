package com.mi.cims.exception;

/**
 * ClassName: AuthorityException
 * Function: 权限异常
 *
 * @author Magic Image-刘伟
 * @date 2017年9月29日 上午11:19:43
 * @version V1.0.0
 */
public class AuthorityException extends AdminBaseException {

    private static final long serialVersionUID = -82652135975313766L;

    public AuthorityException(String code) {
        super(code);
    }

}
