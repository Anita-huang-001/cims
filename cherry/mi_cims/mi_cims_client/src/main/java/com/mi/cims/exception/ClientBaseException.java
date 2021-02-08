package com.mi.cims.exception;

/**   
 * @ClassName: ClientBaseException   
 * @Description: 认证系统基础异常
 * @author: 刘伟
 * @date: 2017年9月5日 上午9:20:07   
 */
public class ClientBaseException extends Exception {

    private static final long serialVersionUID = -4138736492997152522L;

    /**
     * 异常码
     * */
    private String code;

    /**
     * 异常附加参数
     * */
    private Object[] param;

    public ClientBaseException(String code) {
        this.code = code;
        this.param = null;
    }

    public ClientBaseException(String code, Object[] param) {
        this.code = code;
        this.param = param;
    }

    /**   
     * @Title: getCode   
     * @Description: 取得异常码 
     * @author: 刘伟 
     * @date: 2017年9月5日 上午11:25:47 
     * @return: String 异常码
     */
    public String getCode() {
        return code;
    }

    /**   
     * @Title: getParam   
     * @Description: 取得附加参数
     * @author: 刘伟 
     * @date: 2017年9月5日 上午11:25:47 
     * @return: Object[] 附加参数
     */
    public Object[] getParam() {
        return param;
    }
}
