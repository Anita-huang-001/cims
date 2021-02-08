"use strict"

/**
 * http拦截器，统一处理异常请求
 */
app.factory('HttpInterceptor', function ($q, $location, $rootScope) {
	return {
		request : function(config) {
			// 客户端语言
			config.headers["Client-Language"] = window.localStorage.lang;
			// 用户访问token
			if (window.sessionStorage.userToken) {
				config.headers["Access-Token"] = window.sessionStorage.userToken;
			}
			// 禁用缓存
			config.headers["Cache-Control"] = "no-cache";
			config.headers["Pragma"] = "no-cache";
			// 请求超时时间（毫秒）
			//config.timeout=10000;
			
			return config;
		},
		requestError : function(err) {
			return $q.reject(err);
		},
		response : function(res) {
			try {
				// 发生权限错误后，进行广播
				if (res.data.errCode == "900001") {
					$rootScope.$emit('HttpPermissionDeniedResponse', res.data.errMessage);
					return $q.defer().promise;
				}
				// 发生会话超时错误后，进行广播
				if (res.data.errCode == "900003") {
					$rootScope.$emit('HttpSessionTimeoutResponse', res.data.errMessage);
					return $q.defer().promise;
				}
//				// 发生license过期错误后，进行广播
//				if (res.data.errCode == "900004") {
//					$rootScope.$emit('LicenseOutOfDateResponse', res.data.errMessage);
//					return $q.defer().promise;
//				}
//				// 发生初始化完成后仍发起初始化请求，进行广播
//				if (res.data.errCode == "900005") {
//					$rootScope.$emit('InitIllegalResponse', res.data.errMessage);
//					return $q.defer().promise;
//				}
			} catch (err) {
			}
			return res;
		},
		responseError : function(err) {
			// 发生HTTP请求错误后，进行广播，统一处理
			$rootScope.$emit('HttpRequestError', err);
			return $q.reject(err);
		}
	};
});

