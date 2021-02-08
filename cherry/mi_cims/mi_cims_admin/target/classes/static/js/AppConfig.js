"use strict"

/**
 * 懒加载配置
 */
app.config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide', 
	function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
		app.controller = $controllerProvider.register;
		app.directive  = $compileProvider.directive;
		app.filter     = $filterProvider.register;
		app.factory    = $provide.factory;
		app.service    = $provide.service;
		app.constant   = $provide.constant;
		app.value      = $provide.value;
	}
]);

/**
 * 多语言化配置
 */
app.config(['$translateProvider', 'LANGUAGE_LIST', function($translateProvider, LANGUAGE_LIST) {
	// 读取本地语言配置
	if (!window.localStorage.lang) {
		window.localStorage.lang = LANGUAGE_LIST[0].lang;
	}
	var lang = window.localStorage.lang;
	// 设置多语言
	$translateProvider.preferredLanguage(lang);
	$translateProvider.useStaticFilesLoader({
		prefix : 'i18n/',
		suffix : '.json'
	});
} ]);

/**
 * 拦截器配置
 */
app.config(['$httpProvider', function($httpProvider) {
	// http拦截器
	$httpProvider.interceptors.push('HttpInterceptor');
	
	// post
	$httpProvider.defaults.headers.post = {
		// 默认以formdata方式提交
		'Content-Type' : 'application/x-www-form-urlencoded'
	}
	// put
	$httpProvider.defaults.headers.put = {
		// 默认以formdata方式提交
		'Content-Type' : 'application/x-www-form-urlencoded'
	}
} ]);
