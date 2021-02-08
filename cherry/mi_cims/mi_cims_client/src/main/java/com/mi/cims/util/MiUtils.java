package com.mi.cims.util;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: MiUtils
 * @Description: 工具类
 * @author: 刘伟
 * @date: 2017年9月8日 下午1:37:10
 */
public class MiUtils {
	
    /**
     * 
     * parseCheckcode:根据357469281规则使用SHA256加密.
     * 
     * @author 张万创
     * @date Nov 10, 2017 6:31:44 PM
     * @param authLog
     *            认证日志
     * @return 加密checkcode
     */
//    public static String parseCheckcode(AuthLog authLog) {
//        StringBuffer checkCode = new StringBuffer();
//        checkCode.append(authLog.getLoginId());
//        checkCode.append(authLog.getMatrixSn());
//        checkCode.append(authLog.getAuthTime());
//        checkCode.append(authLog.getAuthType());
//        checkCode.append(authLog.getIp());
//        checkCode.append(authLog.getResult());
//        checkCode.append(authLog.getGroupName());
//        checkCode.append(authLog.getCode());
//        checkCode.append(authLog.getAppName());
//
//        // 根据357469281规则加密保存
//        return DigestUtils.sha256Hex(checkCode.toString());
//    }

    /**
     * parseLocale:解析语言环境
     * 
     * @author 刘伟
     * @date 2017年9月29日 上午11:08:04
     * @param language
     *            语言环境字符串
     * @return 语言环境对象
     * @throws Exception
     */
    public static Locale parseLocale(String language) {
        if ("zh_CN".equals(language)) {
            // 中文简体
            return Locale.SIMPLIFIED_CHINESE;
        } else if ("en_US".equals(language)) {
            // 英文
            return Locale.US;
        } else {
            // 默认语言类型
//            return Locale.US;
        	return Locale.SIMPLIFIED_CHINESE;
        }
    }
    
    /**
     * 
     * getUUID:生成36位原生UUID.
     * 
     * @author 张万创
     * @date Nov 9, 2017 8:36:18 PM
     * @return UUID字符串
     */
    public static String getUUID36() {
        return UUID.randomUUID().toString();
    }

    /**
     * getUUID:生成32位的UUid
     * 
     * @author 刘伟
     * @date 2017年9月29日 上午11:08:48
     * @return UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 
     * getIpAddress:获取客户端IP地址
     * 
     * @author 张万创
     * @date Oct 20, 2017 11:30:51 AM
     * @param request
     * @return ip地址
     */
    public final static String getIpAddress(HttpServletRequest request) {
        try {
            // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int index = 0; index < ips.length; index++) {
                    String strIp = ips[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {
                        ip = strIp;
                        break;
                    }
                }
            }
            return ip;
        } catch (Exception e) {
            return null;
        }
    }

}
