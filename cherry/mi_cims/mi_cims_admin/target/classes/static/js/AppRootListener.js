"use strict"

/**
 * 路由改变监听事件
 */
app.run(function($rootScope, $state, $http, $location, $translate, MessageBoxService, PATTERN) {
	/**
	 * 路由改变监听事件
	 */
	$rootScope.$on('$stateChangeStart',function(event, toState, toParams, fromState, fromParams){
		// 滚动条定位到头部
		$(window).scrollTop(0);
		
		// 非登录校验
		if (toState.name == 'login') {
			window.sessionStorage.userToken = "";
			return;
		}
		
//		// 初始化校验
//		if (toState.name == 'init'|| toState.name == 'initDbConfig' || toState.name == 'initRedisConfig' ||
//				toState.name == 'initManagerConfig' ) {
//			// 获取初始化进度
//			$http({
//				url : "/init/initSchedule",
//				method : "GET",
//			}).then(function(response) {			
//				if (response.data.errCode) {
//					// 处理失败
//					MessageBoxService.alert(response.data.errMessage);
//				} else {
//					//页面跳转
//					//后台拦截器判断初始化完成
//					if(response.data.page == 'login')
//					{
//						// 跳转到登录页面
//						$state.go("login");
//					}
//					//后台拦截器判断DB未初始化
//					else if(response.data.page == 'initDbConfig')
//					{
//						// 跳转到DB页面
//						$state.go("initDbConfig");
//					}
//					//后台拦截器判断redis未初始化
//					else if(response.data.page == 'initRedisConfig')
//					{
//						//从DB初始化页面或者redis初始化页面跳转DB页面
//						if((fromState.name=='initDbConfig'||fromState.name=='initRedisConfig'||fromState.name=='') && toState.name == 'initDbConfig'){
//							// 跳转到DB页面
//							$state.go("initDbConfig");
//						}else{
//							// 跳转到redis页面
//							$state.go("initRedisConfig");
//						}						
//					}
//					//后台拦截器判断管理员未初始化
//					else{
//						//开放权限，进入相应的初始化页面
//						window.sessionStorage.userToken = "";
//					}						
//				}
//			});
//			
//			return;
//		}	
		
		// 登录校验
		if (!window.sessionStorage.userToken) {
			// 取消默认跳转行为
			event.preventDefault();
			// 跳转到登录页面
			$state.go("login");
		}
		
//		// License过期页面直接进入
//		if (toState.name == 'licenseOutOfDate') {
//			return;
//		}
//		// License过期校验
//		if (window.sessionStorage.licenseOutOfDate == "true") {
//			// 取消默认跳转行为
//			event.preventDefault();
//			// 跳转到license过期页面
//			$state.go("licenseOutOfDate");
//		}
		
		// 根据菜单ID进行判断，是否有权限访问该页面
		if(toState.menuId && !$rootScope.menuCheck(toState.menuId)) {
			// 取消默认跳转行为
			event.preventDefault();
			// 跳转到主页
			$state.go("home.top");
		}
		
		// 根据操作编码进行判断，是否有权限访问该页面
		if(toState.operationCode && !$rootScope.operationCheck(toState.operationCode)) {
			// 取消默认跳转行为
			event.preventDefault();
			// 跳转到主页
			$state.go("home.top");
		}
		
		// 如果跳转home页，直接跳转到top页
		if (toState.name == 'home') {
			// 取消默认跳转行为
			event.preventDefault();
			// 跳转到主页
			$state.go("home.top");
		}
	});
	
	/**
	 * HTTP请求错误处理
	 */
	$rootScope.$on('HttpRequestError', function(event, error) {
		if (error.status == -1) {
			// 服务器超时 
			//alert($translate.instant("909001"));
		} else if (error.status == 404) {
			// 页面未找到
			MessageBoxService.alert($translate.instant("909404"));
		} else if (error.status == 500) {
			// 服务器错误
			MessageBoxService.alert($translate.instant("909500"));
		} else {
			// 其它错误
			MessageBoxService.alert($translate.instant("909999") + " : " + error.status);
		}
    });
	
	/**
	 * 权限错误响应处理
	 */
	$rootScope.$on('HttpPermissionDeniedResponse', function(event, errMessage) {
		// 权限错误时，显示提示信息后， 跳转到登录界面
		MessageBoxService.alert(errMessage, function() {
			// 跳转到登录界面
			$state.go("login");
		});
	});
	
	/**
	 * 会话超时错误响应处理
	 */
	$rootScope.$on('HttpSessionTimeoutResponse', function(event, errMessage) {
		// 会话超时，显示提示信息后，跳转到登录页面
		MessageBoxService.alert(errMessage, function() {
			// 跳转到登录界面
			$state.go("login");
		});
	});
	
//	/**
//	 * 发生初始化完成后仍发起初始化请求处理
//	 */
//	$rootScope.$on('InitIllegalResponse', function(event, errMessage) {
//		// 会话超时，显示提示信息后，跳转到登录页面
//		MessageBoxService.alert(errMessage, function() {
//			// 跳转到登录界面
//			$state.go("login");
//		});
//	});
//	/**
//	 * License过期错误响应处理
//	 */
//	$rootScope.$on('LicenseOutOfDateResponse', function(event, errMessage) {
//		// 会话超时，显示提示信息后，跳转到登录页面
//		MessageBoxService.alert(errMessage, function() {
//			// 跳转到license过期界面
//			$state.go("licenseOutOfDate");
//		});
//	});
	
	/**
	 * 菜单权限判断
	 * @param menuId 菜单ID
	 */
	$rootScope.menuCheck = function(menuId) {
		if ($rootScope.menuSet) {
			if ($.inArray(menuId, $rootScope.menuSet) != -1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 动作权限判断
	 * @param operationCode 操作编码
	 */
	$rootScope.operationCheck = function(operationCode) {
		if ($rootScope.operationSet) {
			if ($.inArray(operationCode, $rootScope.operationSet) != -1) {
				return true;
			}
		}
		return false;
	}
	
	// 全局的正则表达式
	$rootScope.PATTERN = PATTERN;
});