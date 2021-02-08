package com.mi.cims.aop;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mi.cims.bean.pojo.ErrorInfo;
import com.mi.cims.constant.ErrorCode;
import com.mi.cims.exception.AuthorityException;
import com.mi.cims.exception.BusinessException;
import com.mi.cims.exception.ParamValidException;
import com.mi.cims.util.MiUtils;

/**
 * ClassName: GlobalExceptionHandler
 * Function: 统一异常处理
 *
 * @author Magic Image-刘伟
 * @date 2017年9月29日 上午11:22:11
 * @version V1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 日志处理器
    static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 多语言资源文件处理器
    @Autowired
    private MessageSource messageSource;

    /** 
     * jsonErrorHandler:统一异常处理，返回json格式的处理信息
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:22:27
     * @param request HTTP请求
     * @param e 要处理的异常
     * @return 错误信息
     * @throws Exception 
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        // 错误异常描述信息
    	ErrorInfo errorInfo = null;

        // 请求信息
        String requestMessage = getRequestInfo(request);
        // 语言环境
        Locale locale = MiUtils.getRequestLocale(request);

        // 根据不同的异常类型进行不同的返回处理
        if (e instanceof BindException || e instanceof ConstraintViolationException || e instanceof ParamValidException
                || e instanceof MethodArgumentNotValidException) {
            // 参数异常处理
            errorInfo = paramExceptionHandler(e, locale, requestMessage);
        } else if (e instanceof BusinessException) {
            // 业务逻辑异常处理
            errorInfo = businessExceptionHandler((BusinessException) e, locale, requestMessage);
        } else if (e instanceof AuthorityException) {
            // 权限异常处理
            errorInfo = authorityExceptionHandler((AuthorityException) e, locale, requestMessage);
        } else { // 系统异常
            // 默认异常处理
            errorInfo = defaultExceptionHandler(e, locale, requestMessage);
        }
        return errorInfo;
    }

    /** 
     * paramExceptionHandler:参数异常处理器
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:27:56
     * @param e 要处理的异常
     * @param locale 语言环境
     * @param requestMessage 请求参数
     * @return 错误描述对象
     */
    private ErrorInfo paramExceptionHandler(Exception e, Locale locale, String requestMessage) {
        // 错误描述信息
    	ErrorInfo errorInfo = new ErrorInfo();
        // 取得错误码
        String errCode = null;
        if (e instanceof BindException) { // bean中属性验证
            BindException bindExp = (BindException) e;

            if (bindExp.getFieldError() != null) { // 如果是属性校验错误
                errCode = bindExp.getFieldError().getDefaultMessage();
            } else { // 如果是类校验错误
                errCode = bindExp.getGlobalError().getDefaultMessage();
            }
        } else if (e instanceof ConstraintViolationException) { // 方法中参数验证
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                errCode = violation.getMessage();
                break;
            }
        } else if (e instanceof ParamValidException) { // 自定义参数错误异常
            errCode = ((ParamValidException) e).getCode();
        } else if (e instanceof MethodArgumentNotValidException) {
            // 如果是属性校验错误(@RequestBody)方式验证
            MethodArgumentNotValidException manvexp = ((MethodArgumentNotValidException) e);
            BindingResult bindingResult = manvexp.getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                errCode = fieldError.getDefaultMessage();
            } else {
                List<ObjectError> errorList = bindingResult.getAllErrors();
                errCode = errorList.get(0).getDefaultMessage();
            }
        }

        // 参数错误统一错误码
        errorInfo.setErrCode(ErrorCode.REQUEST_PARAM_ERROR);
        // 参数错误信息
        String paramErrMsg = messageSource.getMessage(errCode, null, locale);
        // 参数错误类型对应的错误信息
        errorInfo.setErrMessage(paramErrMsg);
        // 记录INFO日志
        logger.info(paramErrMsg + "\n" + requestMessage);
        return errorInfo;
    }

    /** 
     * businessExceptionHandler:业务逻辑异常处理器
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:27:56
     * @param e 要处理的异常
     * @param locale 语言环境
     * @param requestMessage 请求参数
     * @return 错误描述对象
     */
    private ErrorInfo businessExceptionHandler(BusinessException e, Locale locale, String requestMessage) {
        // 错误描述信息
    	ErrorInfo errorInfo = new ErrorInfo();
        // 取得错误信息
        String errCode = e.getCode();
        String errMsg = messageSource.getMessage(errCode, ((BusinessException) e).getParam(), locale);
        // 设置错误返回信息
        errorInfo.setErrCode(errCode);
        errorInfo.setErrMessage(errMsg);
        // 记录INFO日志
        logger.info(errMsg + "\n" + requestMessage);
        return errorInfo;
    }

    /** 
     * authorityExceptionHandler:权限异常处理器
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:27:56
     * @param e 要处理的异常
     * @param locale 语言环境
     * @param requestMessage 请求参数
     * @return 错误描述对象
     */
    private ErrorInfo authorityExceptionHandler(AuthorityException e, Locale locale, String requestMessage) {
        // 错误描述信息
    	ErrorInfo errorInfo = new ErrorInfo();
        // 取得错误信息
        String errCode = e.getCode();
        String errMsg = messageSource.getMessage(errCode, null, locale);
        // 设置错误返回信息
        errorInfo.setErrCode(errCode);
        errorInfo.setErrMessage(errMsg);
        // 记录INFO日志
        logger.info(errMsg + "\n" + requestMessage);
        return errorInfo;
    }

    /** 
     * paramExceptionHandler:参数异常处理器
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:27:56
     * @param e 要处理的异常
     * @param locale 语言环境
     * @param requestMessage 请求参数
     * @return 错误描述对象
     */
    private ErrorInfo defaultExceptionHandler(Exception e, Locale locale, String requestMessage) {
        // 错误描述信息
    	ErrorInfo errorInfo = new ErrorInfo();
        // 默认系统错误码
        errorInfo.setErrCode(ErrorCode.SYSTEM_ERROR);
        // 系统错误码对应的错误信息
        String errMessage = messageSource.getMessage(ErrorCode.SYSTEM_ERROR, null, locale);
        errorInfo.setErrMessage(errMessage);
        // 记录ERROR日志
        logger.error(errMessage + "\n" + requestMessage, e);
        return errorInfo;
    }

    /** 
     * getRequestInfo:取得请求信息
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:24:23
     * @param request HTTP请求
     * @return 请求描述信息
     */
    private String getRequestInfo(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        // 请求URI
        String requestURI = request.getRequestURI();
        sb.append("Request URI : " + requestURI + "\n");
        // 请求方法
        String requestMethod = request.getMethod();
        sb.append("Request Method : " + requestMethod + "\n");
        // 查询字符串
        String queryString = request.getQueryString();
        sb.append("Query String : " + queryString + "\n");
        // 请求参数
        sb.append("Request Param：");
        Enumeration<String> e = request.getParameterNames();
        String parameterName, parameterValue;
        if (e.hasMoreElements()) {
            sb.append("\n");
        }
        while (e.hasMoreElements()) {
            parameterName = e.nextElement();
            parameterValue = request.getParameter(parameterName);
            sb.append("\t" + parameterName + " : " + parameterValue);
            if (e.hasMoreElements()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}

