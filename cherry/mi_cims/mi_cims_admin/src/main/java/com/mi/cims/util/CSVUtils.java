/** 
 * Project Name:mi-cims-admin-平台API企业级
 * File Name:CSVUtils.java 
 * Package Name:com.mi.cims.util 
 * Copyright (c) 2017,
 * Company: Magic Image,Ltd.
 * 
 * @author Magic Image-刘恒玉
 * @date 2017年10月18日 上午11:03:45
 * @version V1.0.0
 */

package com.mi.cims.util;

//import java.io.ByteArrayOutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.util.List;
//import java.util.Locale;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.github.pagehelper.util.StringUtil;
//import com.mi.cims.constant.PatternRegexp;
//
//import au.com.bytecode.opencsv.CSVWriter;

/**
 * ClassName: CSVUtils Function: (csv文件导出).
 *
 * @author Magic Image-刘恒玉
 * @date 2017年10月18日 上午11:03:45
 * @version V1.0.0
 */
public class CSVUtils {

/*	*//**
	 * 
	 * csvExport:(CSV文件输出).
	 * 
	 * @author 刘恒玉
	 * @date 2017年11月16日 上午9:09:25
	 * @param exportDate
	 *            输出数据
	 * @param fileName
	 *            文件名
	 * @param request
	 *            HTTP请求
	 * @return ResponseEntity HTTPResponse
	 * @throws Exception
	 *//*
	public static ResponseEntity<byte[]> csvExport(List<String[]> exportDate, String fileName,
			HttpServletRequest request) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Writer writer = new OutputStreamWriter(baos, Charset.forName("UTF-8"));
		CSVWriter csvWriter = new CSVWriter(writer, ',', CSVWriter.NO_QUOTE_CHARACTER);
		for (String[] data : exportDate) {
			csvWriter.writeNext(data);
		}
		csvWriter.close();
		baos.close();

		// response 设置
		HttpHeaders headers = new HttpHeaders();
		// 下载显示的文件名，解决中文名称乱码问题
		// 通知浏览器以attachment附件下载
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF8"));
		// 设置CSV文件下载类型
		headers.set("Content-Type", "text/csv; charset=UTF-8");
		// 防止excel打开乱码，为CSV添加UTF-8的BOM头
		byte[] bomHeader = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		byte[] csvContent = baos.toByteArray();
		byte[] body = new byte[bomHeader.length + csvContent.length];
		System.arraycopy(bomHeader, 0, body, 0, bomHeader.length);
		System.arraycopy(csvContent, 0, body, bomHeader.length, csvContent.length);
		// 返回文件内容
		return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
	}

	*//**
	 * 
	 * csvDataCheck:(导入CSV文件内容检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param columNum
	 *            列号
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @return String 错误信息
	 *//*
	public static String csvDataCheck(String columContent, int columNum, int lineNum, Locale locale,
			MessageSource messageSource) {
		String errorStr = "";
		switch (columNum) {
		case 0:
			// 登录ID检查
			errorStr = checkLoginId(columContent, lineNum, locale, messageSource);
			break;
		case 1:
			// 用户检查
			errorStr = checkUserName(columContent, lineNum, locale, messageSource);
			break;
		case 2:
			// 应用编码检查
			errorStr = checkAppCode(columContent, lineNum, locale, messageSource);
			break;
		case 3:
			// 用户组ID检查
			errorStr = checkGroupId(columContent, lineNum, locale, messageSource);
			break;
		case 4:
			// 邮箱检查
			errorStr = checkMail(columContent, lineNum, locale, messageSource);
			break;
		case 5:
			// 手机号检查
			errorStr = checkPhone(columContent, lineNum, locale, messageSource);
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			errorStr = checkExtendedFiled(columContent, columNum, lineNum, locale, messageSource);
			break;
		default:
			// 别名检查
			errorStr = checkAlias(columContent, lineNum, locale, messageSource);
			break;
		}
		return errorStr;
	}

	*//**
	 * 
	 * checkLoginId:(登录ID检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkLoginId(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 长度检查
		if (content.length() > 32) {
			errorFlag = 1;
		}
		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.loginId);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("loginId", null, locale);
		} else {
			error = "";
		}

		return error;
	}

	*//**
	 * 
	 * checkUserName:(用户名检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkUserName(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 空值不作检查
		if (StringUtil.isEmpty(content)) {
			return error = "";
		}
		// 长度检查
		if (content.length() > 32) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("userName", null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * checkAppCode:(应用编码检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkAppCode(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 长度检查
		if (content.length() > 32) {
			errorFlag = 1;
		}
		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.appCode);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("appCode", null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * checkGroupId:(用户组ID检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkGroupId(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}

		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.id);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("groupId", null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * checkMail:(邮箱检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkMail(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 空值不作检查
		if (StringUtil.isEmpty(content)) {
			return error = "";
		}
		// 长度检查
		if (content.length() > 32) {
			errorFlag = 1;
		}
		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.mail);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("mailAddress", null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * checkPhone:(手机号检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkPhone(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 空值不作检查
		if (StringUtil.isEmpty(content)) {
			return error = "";
		}
		// 长度检查
		if (content.length() > 11) {
			errorFlag = 1;
		}
		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.phone);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("phoneNumber", null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * checkAlias:(用户别名检查).
	 * 
	 * @author 刘恒玉
	 * @date 2017年10月19日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkAlias(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 长度检查
		if (content.length() > 32) {
			errorFlag = 1;
		}
		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.loginId);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("userAlias", null, locale);
		} else {
			error = "";
		}

		return error;
	}

	*//**
	 * 
	 * checkUserName:(用户扩展属性检查).
	 * 
	 * @author 刘恒玉
	 * @date 2018年5月24日 下午2:14:54
	 * @param columContent
	 *            字段
	 * @param columNum
	 *            列号
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkExtendedFiled(String content, int columNum, int lineNum, Locale locale,
			MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 空值不作检查
		if (StringUtil.isEmpty(content)) {
			return error = "";
		}
		// 长度检查
		if (content.length() > 255) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			String messageKey = "extendedField" + String.valueOf(columNum - 5);
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage(messageKey, null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * downLoadFile:下载文件
	 * 
	 * @author 高奎
	 * @date Nov 1, 2017 9:43:52 AM
	 * @param buffer
	 *            字节数组
	 * @param fileName
	 *            文件名
	 * @return response
	 * @throws Exception
	 *//*
	public static ResponseEntity<byte[]> downLoadFile(byte[] buffer, String fileName) throws Exception {
		// response 设置
		HttpHeaders headers = new HttpHeaders();
		// 通知浏览器以attachment附件下载
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));
		// 设置CSV文件下载类型
		headers.set("Content-Type", "application/octet-stream; charset=UTF-8");
		// 防止excel打开乱码，为CSV添加UTF-8的BOM头
		byte[] bomHeader = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		byte[] csvContent = buffer;
		byte[] body = new byte[bomHeader.length + csvContent.length];
		System.arraycopy(bomHeader, 0, body, 0, bomHeader.length);
		System.arraycopy(csvContent, 0, body, bomHeader.length, csvContent.length);
		return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
	}

	*//**
	 * 
	 * csvCompanyDataCheck:(公司信息导入CSV文件内容检查).
	 * 
	 * @author 马云涛
	 * @date 2018年12月12日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param columNum
	 *            列号
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @return String 错误信息
	 *//*
	public static String csvCompanyDataCheck(String columContent, int columNum, int lineNum, Locale locale,
			MessageSource messageSource) {
		String errorStr = "";
		switch (columNum) {
		case 0:
			// 纳税人识别号检查
			errorStr = checkSocialCreditCode(columContent, lineNum, locale, messageSource);
			break;
		case 1:
			// 公司名称检查
			errorStr = checkCompanyName(columContent, lineNum, locale, messageSource);
			break;
		
		case 2:
			// 科室名称检查
			errorStr = checkGroupName(columContent, lineNum, locale, messageSource);
			break;
		case 4:
			// 是否需要预报税检查
			errorStr = checkTaxFlag(columContent, lineNum, locale, messageSource);
			break;
		}		
		return errorStr;
		
	}

	*//**
	 * 
	 * checkCompanyName:(公司名称检查).
	 * 
	 * @author 马云涛
	 * @date 2018年12月13日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkCompanyName(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 长度检查
		if (content.length() > 128) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("companyName", null, locale);
		} else {
			error = "";
		}
		return error;
	}

	*//**
	 * 
	 * checkSocialCreditCode:(纳税人识别号检查).
	 * 
	 * @author 马云涛
	 * @date 2018年12月13日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkSocialCreditCode(String content, int lineNum, Locale locale,
			MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 长度检查
		// 修改前代码 if (content.length() != 18)
		// 纳税人识别号最长32位 ------张蓬
		if ( content.length() > 32) {
			errorFlag = 1;
		}
		// 格式检查
		Pattern p = Pattern.compile(PatternRegexp.socialCreditCode);
		Matcher m = p.matcher(content);
		if (!m.matches()) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("socialCreditCode", null, locale);
		} else {
			error = "";
		}
		return error;
	}
	
	*//**
	 * 
	 * checkGroupName:(科室名称检查).
	 * 
	 * @author 张蓬
	 * @date 2018年12月13日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkGroupName(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 长度检查
		if (content.length() > 32) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("groupName", null, locale);
		} else {
			error = "";
		}
		return error;
	}
	
	*//**
	 * 
	 * checkTaxFlag:(是否需要预报税检查).
	 * 
	 * @author 张蓬
	 * @date 2018年12月13日 下午3:54:54
	 * @param columContent
	 *            字段
	 * @param lineNum
	 *            行号
	 * @param locale
	 *            本地环境
	 * @param MessageSource
	 *            提示信息源
	 * @return String 错误信息
	 *//*
	private static String checkTaxFlag(String content, int lineNum, Locale locale, MessageSource messageSource) {
		String error;
		int errorFlag = 0;
		// 非空检查
		if (StringUtil.isEmpty(content)) {
			errorFlag = 1;
		}
		// 内容检查
		if (!(content.equals("1"))&&!(content.equals("2"))) {
			errorFlag = 1;
		}
		// 错误信息
		if (errorFlag == 1) {
			error = String.valueOf(lineNum) + ":" + messageSource.getMessage("taxFlag", null, locale);
		} else {
			error = "";
		}
		return error;
	}*/

}
