"use strict"

/**
 * 语言列表
 */
app.constant("LANGUAGE_LIST", [ {
	lang : "zh_CN",
	text : "中文"
} ]);

/**
 * 正则表达式
 */
app.constant("PATTERN", {
	
	// 管理员用户名
	managerName : "[A-Za-z0-9_]*",
	// 图形口令
	matrixPwd : "[A-Za-z0-9~!@#$%^&*()_+{}:\\\"|<>?`~\\-=\\[\\];'\\\\,./]*",
	// 密码
	matrixPwdSe : "[A-Za-z0-9]*",
	// 登录ID
	loginId : "[A-Za-z0-9_]+",
	// 手机号
	phone : "[0-9]{11}",
	// 邮箱
	mail : "([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)*@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)+",


});