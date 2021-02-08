package com.mi.cims.aop;

//import java.lang.reflect.Method;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.BindException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import com.mi.cims.aop.annotation.Operation;
//import com.mi.cims.bean.po.OptLog;
//import com.mi.cims.bean.pojo.LoginedManager;
//import com.mi.cims.constant.ErrorCode;
//import com.mi.cims.constant.ResultCode;
//import com.mi.cims.constant.ServiceManageEnum;
//import com.mi.cims.controller.ServiceManageController;
//import com.mi.cims.dao.OptLocimsapper;
//import com.mi.cims.exception.AdminBaseException;
//import com.mi.cims.exception.BusinessException;
//import com.mi.cims.exception.ParamValidException;
//import com.mi.cims.service.RedisService;
//import com.mi.cims.util.CacheUtils;
//import com.mi.cims.util.MiUtils;
//
///**
// * ClassName: GlobalOptLogHandler
// * Function: 全局操作日志处理
// *
// * @author Magic Image-刘伟
// * @date 2017年11月15日 上午11:27:31
// * @version V1.0.0
// */
//@Aspect
//@Component
//public class GlobalOptLogHandler {
//
//    // 日志处理器
//    static final Logger logger = LoggerFactory.getLogger(GlobalOptLogHandler.class);
//
//    // 操作日志DAO
//    @Autowired
//    private OptLocimsapper optLocimsapper;
//
//    // 缓存服务
//    @Autowired
//    private RedisService redisService;
//
//    // HTTP请求
//    @Autowired
//    private HttpServletRequest request;
//
//    /** 
//     * aspect:切点
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 下午1:27:32 
//     */
//    @Pointcut("@annotation(com.mi.cims.aop.annotation.Operation)")
//    public void aspect() {
//
//    }
//
//    /** 
//     * doAfterReturning:切点函数正常返回处理
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 下午1:22:30
//     * @param joinPoint 切点
//     * @param retVal 返回信息
//     */
//    @AfterReturning(pointcut = "aspect()", returning = "retVal")
//    public void doAfterReturning(JoinPoint joinPoint, Object retVal) {
//        try {
//            // 添加操作成功日志
//            addOptLog(joinPoint, ResultCode.SUCCEED, String.valueOf(ResultCode.SUCCEED.getCode()));
//        } catch (Exception e) {
//            logger.error("Add operation log error!", e);
//        }
//    }
//
//    /** 
//     * doAfterThrowing:切点函数异常处理
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 下午1:55:46
//     * @param joinPoint 切点
//     * @param ex 异常
//     */
//    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
//    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
//        try {
//            // 错误码
//            String errorCode = null;
//
//            // 根据异常类型取得错误码
//            if (ex instanceof BindException || ex instanceof ConstraintViolationException
//                    || ex instanceof ParamValidException || ex instanceof MethodArgumentNotValidException) {
//                // 参数异常
//                errorCode = ErrorCode.REQUEST_PARAM_ERROR;
//            } else if (ex instanceof AdminBaseException) {
//                // 业务处理异常
//                errorCode = ((BusinessException) ex).getCode();
//            } else {
//                // 系统异常
//                errorCode = ErrorCode.SYSTEM_ERROR;
//            }
//
//            // 添加操作失败日志
//            addOptLog(joinPoint, ResultCode.FAILED, errorCode);
//        } catch (Exception e) {
//            logger.error("Add operation log error!", e);
//        }
//    }
//
//    /** 
//     * addOptLog:新建操作日志
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 上午9:17:30
//     * @param joinPoint 切点
//     * @param resultCode 结果码
//     * @param result 处理结果
//     * @throws Exception 
//     */
//    private void addOptLog(JoinPoint joinPoint, ResultCode resultCode, String result) throws Exception {
//        // 取得取得已登录管理员信息
//        LoginedManager loginedManager = CacheUtils.getLoginedManager(redisService, request);
//        // 取得操作类型
//        String optType = getOptType(joinPoint);
//
//        // 构建管理员操作日志
//        OptLog record = new OptLog();
//        // 管理员ID
//        record.setManagerId(loginedManager.getUserId());
//        // 管理员姓名
//        record.setManagerName(loginedManager.getUserName());
//        // 客户端IP
//        record.setIp(MiUtils.getRequestIP(request));
//        // 操作时间
//        record.setOptTime(System.currentTimeMillis());
//        // 操作类别
//        record.setOptType(optType);
//        // 处理结果
//        record.setResult(String.valueOf(resultCode.getCode()));
//        // 结果码
//        record.setCode(result);
//        // 生成校验码
//        record.setCheckCode(generateCheckCode(record));
//
//        // 插入数据库
//        optLocimsapper.insert(record);
//    }
//
//    /** 
//     * generateCheckCode:生成操作日志校验码
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 下午2:43:55
//     * @param optLog 操作日志信息
//     * @return 校验码
//     */
//    private String generateCheckCode(OptLog optLog) {
//        StringBuffer logText = new StringBuffer();
//        // 结果码
//        logText.append(optLog.getCode());
//        // 客户端IP
//        logText.append(optLog.getIp());
//        // 管理员姓名
//        logText.append(optLog.getManagerName());
//        // 操作类别
//        logText.append(optLog.getOptType());
//        // 处理结果
//        logText.append(optLog.getResult());
//        // 管理员ID
//        logText.append(optLog.getManagerId());
//        // 操作时间
//        logText.append(optLog.getOptTime());
//        // 进行信息摘要
//        return DigestUtils.md5Hex(DigestUtils.sha256Hex(logText.toString()));
//    }
//
//    /** 
//     * getOptType:取得操作类型
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 下午2:20:51
//     * @param joinPoint 切点
//     * @return 操作类型
//     */
//    private String getOptType(JoinPoint joinPoint) throws Exception {
//        // 目标类名
//        String className = joinPoint.getTarget().getClass().getName();
//        // 目标方法名
//        String methodName = joinPoint.getSignature().getName();
//        // 目标方法的参数实例列表
//        Object[] argsInstance = joinPoint.getArgs();
//
//        // 取得操作注解
//        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
//        Method method = ms.getMethod();
//        Operation optAnnotation = method.getAnnotation(Operation.class);
//        // 操作注解中的操作编码
//        String[] optTypes = optAnnotation.value();
//
//        // 如果操作注解没有设置值
//        if (optTypes == null || optTypes.length == 0) {
//            throw new Exception(className + "." + methodName + " [Opeartion] annotation's value is empty!");
//        }
//
//        // 如果只有一个操作注解值，直接把这个值作为操作类型
//        if (optTypes.length == 1) {
//            return optTypes[0];
//        } else { // 多个操作注解值时，需要根据具体情况，实际取值操作类型
//            // 取得服务管理中操作类型
//            if (className.equals(ServiceManageController.class.getName())) {
//                return getServiceManageOptType(className, methodName, argsInstance, optTypes);
//            } else {
//                throw new Exception(className + "." + methodName + " [Opeartion] annotation's value not match!");
//            }
//        }
//    }
//
//    /** 
//     * getServiceManageOptType:取得服务管理中操作类型
//     * 
//     * @author 刘伟 
//     * @date 2017年11月15日 下午3:02:23
//     * @param className 类名
//     * @param methodName 方法名
//     * @param argsInstance 方法参数
//     * @param optTypes 动作注解值
//     * @return 操作类型
//     * @throws Exception 
//     */
//    private String getServiceManageOptType(String className, String methodName, Object[] argsInstance, String[] optTypes)
//            throws Exception {
//        // 重启服务
//        if (methodName.equals("restartService")) {
//            // 服务标识
//            String serviceCode = (String) argsInstance[0];
//            if (ServiceManageEnum.SERVICE_AUTH.getServiceCode().equals(serviceCode)) { // 管理服务
//                return optTypes[0];
//            } else if (ServiceManageEnum.SERVICE_ADMIN.getServiceCode().equals(serviceCode)) { // 认证服务
//                return optTypes[1];
//            } else {
//            	throw new Exception(className + "." + methodName + " not suport service code : " + serviceCode);
//            }
//        }
//        if(methodName.equals("stopService")||methodName.equals("startService")) {
//        	  // 服务标识
//            String serviceCode = (String) argsInstance[0];
//            if (ServiceManageEnum.SERVICE_AUTH.getServiceCode().equals(serviceCode)) { // 管理服务
//                return optTypes[0];
//            } else {
//            	throw new Exception(className + "." + methodName + " not suport service code : " + serviceCode);
//            }
//        }
//
//        throw new Exception(className + "." + methodName + " [Opeartion] annotation not handle!");
//    }
//}
