package com.mi.cims.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.mi.cims.constant.ErrorCode;
import com.mi.cims.constant.HttpHeaderNames;
import com.mi.cims.constant.HttpRequestNames;
import com.mi.cims.exception.BusinessException;

/**   
 * @ClassName: MiUtils   
 * @Description: 工具类   
 * @author: 刘伟
 * @date: 2017年9月8日 下午1:37:10   
 */
public class MiUtils {

//    // 图形口令序列号前缀
//    private static final String MATRIX_SN_PREFIX = "SN";
//
//    // 图形口令序列号缓存标识
//    private static final String MATRIX_SN_KEY = "matrix_sn";
//
//    // 图形口令序列号前缀
//    private static final int MATRIX_SN_DIGIT_LENGTH = 14;
//
//    private static final String MYSQL = "mysql";
//
//    private static final String ORACLE = "oracle";
//
//    private static final String SQLSERVER = "sqlserver";
//
//    private static final String DB2 = "db2";

    /** 
     * getRequestLocale:取得请求的语言环境
     * 
     * @author 刘伟 
     * @date 2017年9月29日 上午11:23:11
     * @param request HTTP请求
     * @return 语言环境
     */
    public static Locale getRequestLocale(HttpServletRequest request) throws Exception {
        // 从请求头中获取客户端中所包含的语言环境
        String lang = request.getHeader(HttpHeaderNames.CLIENT_LANGUAGE); // 从请求头中获取
        if (lang == null) {
            lang = request.getParameter(HttpRequestNames.CLIENT_LANGUAGE); // 从请求参数中获取
        }
        if (lang != null) {
            if ("zh_CN".equals(lang)) {
                // 中文简体
                return Locale.SIMPLIFIED_CHINESE;
            } else {
                // 不支持的语言类型
                throw new BusinessException(ErrorCode.NOT_SUPPORT_LANGUAGE);
            }
        }
        // 默认用请求的语言环境
        return request.getLocale();
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
     * getRequestIP:取得请求IP
     * 
     * @author 刘伟 
     * @date 2017年11月15日 上午9:04:06
     * @param request HTTP请求
     * @return 请求IP
     */
    public static String getRequestIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("0:0:0:0:0:0:0:1")) {
                ip = "127.0.0.1";
            }
        }
        return ip;
    }

    /** 
     * checkIP:校验IP是否合法
     * 
     * @author 刘伟 
     * @date 2017年11月15日 下午1:51:49
     * @param ip ip字符串
     * @return 
     *      true ： 合法
     *     false ： 非法
     */
    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }

//    /** 
//     * generateMatrixSN:生成图形口令序列号
//     * 
//     * @author 刘伟 
//     * @date 2017年10月13日 下午3:58:52
//     * @return 图形口令序列号
//     */
//    public static String generateMatrixSN(RedisService redisService, MatrixInfoMapper matrixInfoMapper)
//            throws Exception {
//        // 如果图形口令序列号缓存不存在
//        if (redisService.getString(MATRIX_SN_KEY) == null) {
//            // 从数据库中取得SN码最大值
//            PageHelper.startPage(1, 1); // 只取一条
//            String maxMatrixSN = matrixInfoMapper.selectMaxMatrixSN();
//            // 计算最大的序列号
//            long maxPK = 1L;
//            if (maxMatrixSN != null) {
//                maxPK = Long.parseLong(maxMatrixSN.substring(MATRIX_SN_PREFIX.length()));
//            }
//            // 重置redis的序列号
//            redisService.resetPK(MATRIX_SN_KEY, maxPK);
//        }
//
//        // 从redis取得新序列号
//        long pk = redisService.generatePK(MATRIX_SN_KEY);
//        // 组合成符合条件的图形口令序列号
//        return MATRIX_SN_PREFIX + String.format("%0" + MATRIX_SN_DIGIT_LENGTH + "d", pk);
//    }

//    /**
//     * 
//     * getConnection:(获取DB连接).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年10月27日 下午5:47:25
//     * @param info ip,port,dbname,user,password
//     * @return 连接对象
//     * @throws ParamValidException 
//     */
//    public static Connection getDBConnection(String[] info) throws ParamValidException {
//        // 获取mysql数据库的驱动类
//        String driver = "";
//        // 数据源URL
//        StringBuffer sb = new StringBuffer();
//        // mysql
//        if ("1".equals(info[0])) {
//            driver = DBConfig.DRIVER_MYSQL;
//            sb.append(DBConfig.JDBC_MYSQL);
//            // DBIP
//            sb.append(info[1] + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append((info[2] + DBConfig.FILE_SEPARATOR));
//            // 数据库名
//            sb.append(info[3]);
//        } else if ("2".equals(info[0])) {
//            // oracle
//            driver = DBConfig.DRIVER_ORACLE;
//            sb.append(DBConfig.JDBC_ORACLE);
//            // DBIP
//            sb.append(info[1] + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append((info[2] + DBConfig.URL_SEPARATOR));
//            // 数据库名
//            sb.append(info[3]);
//        } else if ("3".equals(info[0])) {
//            // sqlserver
//            driver = DBConfig.DRIVER_SQLSERVER;
//            sb.append(DBConfig.JDBC_SQLSERVER);
//            // DBIP
//            sb.append(info[1] + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append((info[2] + DBConfig.URL_SEPARATOR_SQLSERVER));
//            // 数据库名
//            sb.append(info[3]);
//        } else if ("4".equals(info[0])) {
//            // DB2
//            driver = DBConfig.DRIVER_DB2;
//            sb.append(DBConfig.JDBC_DB2);
//            // DBIP
//            sb.append(info[1] + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append((info[2] + DBConfig.FILE_SEPARATOR));
//            // 数据库名
//            sb.append(info[3]);
//        } else {
//            // 参数传递类型异常
//            throw new ParamValidException(ErrorCode.REQUEST_PARAM_ERROR);
//        }
//        String url = sb.toString();// 连接数据库
//        String name = info[4];// 连接mysql的用户名
//        String pwd = info[5];// 连接mysql的密码
//        Connection conn = null;
//
//        // 获取mysql数据库的驱动类
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, name, pwd);// 获取连接对象
//        } catch (Exception e) {
//            return null;
//        }
//
//        return conn;
//    }
//
//    /**
//     * 
//     * getDbType:(取得数据库类型).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年10月27日 下午2:37:32
//     * @param url 连接数据库URL
//     * @return
//     * @throws ParamValidException
//     */
//    public static int getDbType(String url) throws ParamValidException {
//        if (StringUtils.isEmpty(url)) {
//            return 1;
//        }
//        String[] dbinfo = url.split(DBConfig.URL_SEPARATOR);
//        if (MYSQL.equalsIgnoreCase(dbinfo[1])) {
//            return 1;
//        } else if (ORACLE.equalsIgnoreCase(dbinfo[1])) {
//            return 2;
//        } else if (SQLSERVER.equalsIgnoreCase(dbinfo[1])) {
//            return 3;
//        } else if (DB2.equalsIgnoreCase(dbinfo[1])) {
//            return 4;
//        } else {
//            // 参数传递类型异常
//            throw new ParamValidException(ErrorCode.REQUEST_PARAM_ERROR);
//        }
//    }
//
//    /**
//     * 
//     * getDbIP:(取得IP).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年10月27日 下午2:35:19
//     * @param url 连接数据库URL
//     * @return
//     * @throws ParamValidException
//     */
//    public static String getDbIP(String url) throws ParamValidException {
//        if (StringUtils.isEmpty(url)) {
//            return "";
//        }
//        String[] dbinfo = url.split(DBConfig.URL_SEPARATOR);
//
//        // Oralce
//        if (getDbType(url) == 2) {
//            return dbinfo[3].replaceAll("/", "").replaceAll("@", "");
//        } else {
//            // mysql db2 sqlserver
//            return dbinfo[2].replaceAll("/", "");
//        }
//    }
//
//    /**
//     * 
//     * getDbPort:(取得端口号).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年10月27日 下午2:36:12
//     * @param url 连接数据库URL
//     * @return
//     * @throws ParamValidException
//     */
//    public static int getDbPort(String url) throws ParamValidException {
//        if (StringUtils.isEmpty(url)) {
//            return 0;
//        }
//        String[] dbinfo = url.split(DBConfig.URL_SEPARATOR);
//        // mysql OR db2
//        if (getDbType(url) == 1 || getDbType(url) == 4) {
//            return Integer.parseInt(dbinfo[3].split("/")[0].trim());
//        } else if (getDbType(url) == 2) {
//            // oracle
//            return Integer.parseInt(dbinfo[4].trim());
//        } else if (getDbType(url) == 3) {
//            // sqlserver
//            return Integer.parseInt(dbinfo[3].split(";")[0].trim());
//        } else {
//            // 参数传递类型异常
//            throw new ParamValidException(ErrorCode.REQUEST_PARAM_ERROR);
//        }
//    }
//
//    /**
//     * 
//     * getDbName:(取得数据库名).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年10月27日 下午2:36:57
//     * @param url 连接数据库URL
//     * @return
//     * @throws ParamValidException
//     */
//    public static String getDbName(String url) throws ParamValidException {
//        if (StringUtils.isEmpty(url)) {
//            return "";
//        }
//        String[] dbinfo = url.split("\\?")[0].split(DBConfig.URL_SEPARATOR);
//        // mysql OR db2
//        if (getDbType(url) == 1 || getDbType(url) == 4) {
//            return dbinfo[3].split("/")[1].trim();
//        } else if (getDbType(url) == 2) {
//            // oracle
//            return dbinfo[5].trim();
//        } else if (getDbType(url) == 3) {
//            // sqlserver
//            return dbinfo[3].split(";")[1].split("=")[1].trim();
//        } else {
//            // 参数传递类型异常
//            throw new ParamValidException(ErrorCode.REQUEST_PARAM_ERROR);
//        }
//    }
//
//    /** 
//     * getRedisConnection:redis测试连接
//     * 
//     * @author 朱典雅 
//     * @date 2017年11月14日 上午11:34:36
//     * @param info redis测试连接参数
//     * @throws Exception 
//     */
//    public static void getRedisConnection(String[] info) throws Exception {
//        // 非切片的客户端连接（单机）
//        Jedis jedis = null;
//        // 非切片连接池
//        JedisPool jedisPool = null;
//        // 配置连接池
//        JedisPoolConfig config = new JedisPoolConfig();
//        // 设置redis连接参数
//        String ip = info[0];
//        Integer port = Integer.valueOf(info[1]);
//        try {
//            // 配置连接参数
//            jedisPool = new JedisPool(config, ip, port);
//            jedis = jedisPool.getResource();
//        } catch (JedisConnectionException ex) {
//            // redis连接失败
//            throw new BusinessException(ErrorCode.REDIS_CONNECT_FAILED);
//        }
//        try {
//            // 如果输入了密码
//            if (!StringUtils.isEmpty(info[2])) {
//                String password = info[2];
//                // 设置密码
//                jedis.auth(password);
//            }
//            // 新增测试数据
//            jedis.set("testKey", "This init redis test!");
//            // 获取新增数据
//            String connectionTest = jedis.get("testKey");
//            // 判断新增数据是否成功
//            if (StringUtils.isEmpty(connectionTest)) {
//                // redis连接失败
//                throw new BusinessException(ErrorCode.REDIS_CONNECT_FAILED);
//            }
//            // 删除测试数据
//            jedis.del("testKey");
//        } catch (JedisDataException e) {
//            // 密码错误
//            throw new BusinessException(ErrorCode.REDIS_PASSWORD_WRONG);
//        } catch (JedisConnectionException ex) {
//            // redis连接失败
//            throw new BusinessException(ErrorCode.REDIS_CONNECT_FAILED);
//        } finally {
//            // 关闭连接
//            jedis.close();
//            // 关闭连接池
//            jedisPool.close();
//        }
//    }
//
//    /** 
//     * isUsbKeyExist:判断令牌是否被绑定
//     * 
//     * @author 朱典雅 
//     * @date 2017年11月3日 上午10:34:11
//     * @param usbKey
//     * @return 令牌是否被绑定
//     * @throws Exception 
//     */
////    public static boolean isUsbKeyExist(ManagerInfoMapper managerInfoMapper, String usbKey) throws Exception {
////        Integer count = managerInfoMapper.selectCountByUsbKey(usbKey);
////        if (count > 0) {
////            // 令牌已经被绑定
////            return true;
////        }
////        return false;
////    }
//
//    /**
//     * 
//     * codeString:(取得上传文件编码集).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年10月21日 下午5:03:54
//     * @param inputStream  上传文件输入流
//     * @return 编码集
//     * @throws Exception
//     */
//    public static String codeString(InputStream inputStream) throws Exception {
//        BufferedInputStream bin = new BufferedInputStream(inputStream);
//        int p = (bin.read() << 8) + bin.read();
//        String code = "";
//        switch (p) {
//        case 0xefbb:
//            code = "UTF-8";
//            break;
//        case 0xfffe:
//            code = "Unicode";
//            break;
//        case 0xfeff:
//            code = "UTF-16BE";
//            break;
//        case 0x5c75:
//            code = "ANSI|ASCII";
//            break;
//        default:
//            code = "GBK";
//        }
//        inputStream.close();
//        return code;
//    }
//
//    /**
//     * 
//     * save:(保存系统配置信息).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年11月16日 下午4:04:22
//     * @param filePath 系统配置文件
//     * @param Object DB配置BO
//     * @throws Exception
//     */
//    public static void save(String filePath, Object object) throws Exception {
//
//        Properties prop = new OrderProperties();
//        try {
//            prop.load(new FileInputStream(filePath));
//        } catch (Exception e) {
//            throw new BusinessException(ErrorCode.SYSTEM_CONFIG_NOT_EXIST);
//        }
//
//        // 数据源URL
//        StringBuffer sb = new StringBuffer();
//        // mysql
//        if ((Integer) getValue(object, "dbType") == 1) {
//            sb.append(DBConfig.JDBC_MYSQL);
//            // DBIP
//            sb.append(getValue(object, "dbIp").toString() + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append(getValue(object, "dbPort").toString() + DBConfig.FILE_SEPARATOR);
//            // 数据库名
//            sb.append(getValue(object, "dbName").toString());
//            // 字符集
//            sb.append(DBConfig.CHARACTERENCODING);
//            prop.setProperty(DBConfig.DATASOURCE_URL, sb.toString());
//            // 数据驱动
//            prop.setProperty(DBConfig.DRIVER_CLASSNAME, DBConfig.DRIVER_MYSQL);
//            // 分页配置数据源设置
//            prop.setProperty(DBConfig.PAGEHELPER, "mysql");
//        } else if ((Integer) getValue(object, "dbType") == 2) { // Oralce 数据库
//            sb.append(DBConfig.JDBC_ORACLE);
//            // DBIP
//            sb.append(getValue(object, "dbIp").toString() + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append(getValue(object, "dbPort").toString() + DBConfig.URL_SEPARATOR);
//            // 数据库名
//            sb.append(getValue(object, "dbName").toString());
//            // JDBC URL
//            prop.setProperty(DBConfig.DATASOURCE_URL, sb.toString());
//            // 数据驱动
//            prop.setProperty(DBConfig.DRIVER_CLASSNAME, DBConfig.DRIVER_ORACLE);
//            // 分页配置数据源设置
//            prop.setProperty(DBConfig.PAGEHELPER, "oracle");
//        } else if ((Integer) getValue(object, "dbType") == 3) { // SQL SERVER数据库
//            sb.append(DBConfig.JDBC_SQLSERVER);
//            // DBIP
//            sb.append(getValue(object, "dbIp").toString() + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append(getValue(object, "dbPort").toString() + DBConfig.URL_SEPARATOR_SQLSERVER);
//            // 数据库名
//            sb.append(getValue(object, "dbName").toString());
//            // JDBC URL
//            prop.setProperty(DBConfig.DATASOURCE_URL, sb.toString());
//            // 数据驱动
//            prop.setProperty(DBConfig.DRIVER_CLASSNAME, DBConfig.DRIVER_SQLSERVER);
//            // 分页配置数据源设置
//            prop.setProperty(DBConfig.PAGEHELPER, "sqlserver");
//        } else if ((Integer) getValue(object, "dbType") == 4) {
//            // DB2
//            sb.append(DBConfig.JDBC_DB2);
//            // DBIP
//            sb.append(getValue(object, "dbIp").toString() + DBConfig.URL_SEPARATOR);
//            // DB 端口号
//            sb.append(getValue(object, "dbPort").toString() + DBConfig.FILE_SEPARATOR);
//            // 数据库名
//            sb.append(getValue(object, "dbName").toString());
//            // JDBC URL
//            prop.setProperty(DBConfig.DATASOURCE_URL, sb.toString());
//            // 数据驱动
//            prop.setProperty(DBConfig.DRIVER_CLASSNAME, DBConfig.DRIVER_DB2);
//            // 分页配置数据源设置
//            prop.setProperty(DBConfig.PAGEHELPER, "db2");
//        } else { // 参数传递类型异常
//            throw new ParamValidException(ErrorCode.REQUEST_PARAM_ERROR);
//        }
//        // 用户名
//        prop.setProperty(DBConfig.USER_NAME, getValue(object, "account").toString());
//        // 密码
//        prop.setProperty(DBConfig.PASSWORD, getValue(object, "password").toString());
//        // 初始化连接个数
//        prop.setProperty(DBConfig.DATASOURCE_INITIALSIZE, getValue(object, "minConnection").toString());
//        // 最少连接数量
//        prop.setProperty(DBConfig.DATASOURCE_MINIDLE, getValue(object, "minConnection").toString());
//        // 最大连接数量
//        prop.setProperty(DBConfig.DATASOURCE_MAXACTIVE, getValue(object, "maxConnection").toString());
//        // 获取连接时最大等待时间（毫秒）
//        prop.setProperty(DBConfig.DATASOURCE_MAXWAIT, getValue(object, "maxWait").toString());
//        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//        prop.setProperty(DBConfig.DATASOURCE_TIMEBETWEENEVICTIONRUNSMILLIS,
//                getValue(object, "timeBetweenEvictionRunsMillis").toString());
//        // 连接在在池中最小生存的时间（毫秒）
//        prop.setProperty(DBConfig.DATASOURCE_MINEVICTABLEIDLETIMEMILLIS,
//                getValue(object, "minActiveTimeMillis").toString());
//        OutputStream fos = new FileOutputStream(filePath);
//        prop.store(fos, "######System Config######");
//        fos.flush();
//        fos.close();
//    }
//
//    /**
//     * 
//     * getValue:(反射取得属性值).
//     * 
//     * @author 刘恒玉 
//     * @date 2017年11月16日 下午7:14:03
//     * @param object 对象
//     * @param fname 属性名 
//     * @return  Object 属性值
//     * @throws Exception
//     */
//    @SuppressWarnings("rawtypes")
//    public static Object getValue(Object object, String fname) throws Exception {
//        Class userCla = (Class) object.getClass();
//        Field[] fs = userCla.getDeclaredFields();
//        Object value = "";
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true);
//            if (fname.equals(f.getName())) {
//                value = f.get(object);
//                break;
//            }
//        }
//        return value;
//    }
//
//    /** 
//     * isExistSuperManager:查看超级管理员是否已存在
//     * 
//     * @author 朱典雅 
//     * @date 2017年11月21日 下午1:34:18
//     * @param managerInfoMapper 管理员DAO
//     * @return 是否存在
//     * @throws Exception 
//     */
//    public static boolean isExistSuperManager(ManagerInfoMapper managerInfoMapper) throws Exception {
//        // 查看是否已存在超级管理员
//        Integer isExistSuperManager = managerInfoMapper.selectCountByRoleId(1);
//        if (isExistSuperManager > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    /** 
//     * isWindowsOS: 是否是windows操作系统
//     * 
//     * @author 刘伟 
//     * @date 2018年5月22日 下午5:33:21
//     * @return true - windows操作系统
//     *         false - linux操作系统
//     */
//    public static boolean isWindowsOS() {
//        String os = System.getProperty("os.name");
//        if (os.toLowerCase().startsWith("windows")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    
//	/**
//	 * 
//	 * serviceOP:单项服务操作.
//	 * 
//	 * @author 张万创
//	 * @date Nov 2, 2017 11:51:37 PM
//	 * @param serviceCode
//	 *            服务标识
//	 * @param serial
//	 * 			     指定的服务序号
//	 * @param cmd
//	 *            指令start|stop|restart|status
//	 * @throws Exception
//	 */
//    public static String serviceOP(String serviceCode, int serial, String cmd) throws Exception {
//		String serviceName = "";
//
//		String[] cmds = new String[3];
//		cmds[0] = "/bin/bash";
//		cmds[1] = "-c";
//
//		if (ServiceManageEnum.SERVICE_ADMIN.getServiceCode().equals(serviceCode)) {
//			serviceName = ServiceManageEnum.SERVICE_ADMIN.getConfigPath();
//			cmds[2] = "sleep 5; service " + serviceName + " " + cmd;
//			CommandExecuteUtils.runCommandNoWait(cmds);
//			return "0";
//		} else {
//			serviceName = ServiceManageEnum.SERVICE_AUTH.getConfigPath();
//			cmds[2] = "service " + serviceName + " " + cmd;
//			return CommandExecuteUtils.runCommand(cmds);
//		} 
//	}
    
	/**
	 * 
	 * changeStrTimeToLong:字符串时间(yyyy-MM-dd HH:mm:ss)转换Long
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param strDate
	 *            字符串时间(yyyy-MM-dd HH:mm:ss)
	 * @return lgTime
	 *            Long型时间
	 * @throws Exception
	 */
    public static Long changeStrTimeToLong(String strDate) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = sdf.parse(strDate);
	    Long lgTime=date.getTime();
	    return lgTime;
    }
    
	/**
	 * 
	 * changeStrShortTimeToLong:字符串时间(yyyy-MM-dd)转换Long
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param strDate
	 *            字符串时间(yyyy-MM-dd)
	 * @return lgTime
	 *            Long型时间
	 * @throws Exception
	 */
    public static Long changeStrShortTimeToLong(String strDate) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = sdf.parse(strDate);
	    Long lgTime=date.getTime();
	    return lgTime;
    }
    
	/**
	 * 
	 * changeLongTimeToStr:Long型时间转换字符串(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param lgTime
	 *            Long型时间
	 * @return strDate
	 *            字符串时间(yyyy-MM-dd HH:mm:ss)
	 * @throws Exception
	 */
    public static String changeLongTimeToStr(Long lgTime) throws Exception {
	    Date date = new Date(lgTime);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.format(date);
    }
    
	/**
	 * 
	 * changeTimeToyyyyMM:Long型时间转换字符串(yyyyMM)
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param lgTime
	 *            Long型时间
	 * @return strDate
	 *            字符串时间(yyyyMM)
	 * @throws Exception
	 */
    public static String changeTimeToyyyyMM(Long lgTime) throws Exception {
	    Date date = new Date(lgTime);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	    return sdf.format(date);
    }
    
	/**
	 * 
	 * changeTimeToyyyy_MM:Long型时间转换字符串(yyyy-MM)
	 * 
	 * @author张蓬
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param lgTime
	 *            Long型时间
	 * @return strDate
	 *            字符串时间(yyyy-MM)
	 * @throws Exception
	 */
    public static String changeTimeToyyyy_MM(Long lgTime) throws Exception {
	    Date date = new Date(lgTime);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	    return sdf.format(date);
    }
    
	/**
	 * 
	 * changeTimeToyyyyMMdd型时间转换字符串(yyyy-MM-dd)
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param lgTime
	 *            Long型时间
	 * @return strDate
	 *            字符串时间(yyyy-MM-dd HH:mm:ss)
	 * @throws Exception
	 */
    public static String changeTimeToyyyyMMdd(Long lgTime) throws Exception {
	    Date date = new Date(lgTime);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
    }
    
	/**
	 * 
	 * isStatisticalDeadline:报税截止日判断
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2017 11:51:37 PM
	 * @param deadline 报税截止日
	 * @return true：超过报税截止日；false：未超过报税截止日
	 * @throws Exception
	 */
	public static boolean isStatisticalDeadline(int deadline) throws Exception {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Integer day = Integer.valueOf(sdf.format(date));
		if (day <= deadline) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 
	 * dataDecimalFormat:千分格式化方法
	 * 
	 * @author 马云涛
	 * @date Nov 2, 2018 11:51:37 PM
	 * @param data 需格式化的数据
	 * @return 格式化后的数据
	 * @throws Exception
	 */
	public static String dataDecimalFormat(BigDecimal data) throws Exception {
		DecimalFormat df=new DecimalFormat(",###,##0.00");
		return df.format(data);
	}
}

