(function () {
"use strict"

/**
 * 登录控制器
 */
app.controller("LoginController", function($scope, $rootScope, $state, $translate, $http, LANGUAGE_LIST, MessageBoxService, CryptService) {
	
	// 登录信息模型
	$scope.loginInfo = {
		// 用户名
		managerId : "",
		
		// 用户密码
		managerPwd : "",
	};
	
	/**
	 * 密码按键事件
	 * */
	$scope.managerPwdKeyup = function(e) {
		// IE 编码包含在window.event.keyCode中，Firefox或Safari包含在event.which中
		var keycode = window.event ? e.keyCode : e.which;
		// 回车键相当于直接点击登录按钮
		if (keycode == 13) {
			if (!$("#loginBtn").prop("disabled")) {
				$scope.login();
			}
		}
	}
	
	/**
	 * 登录验证
	 * */ 
	$scope.login = function() {
		
		
		// 复制请求参数
		$scope.requestParam = angular.copy($scope.loginInfo);
		// 密码加密
		$scope.requestParam.managerPwd = CryptService.MD5(CryptService.SHA256(CryptService.MD5($scope.requestParam.managerPwd)));
		
		// 登录认证请求
		$http({
			url : "/login",
			method : "POST",
			data : $.param($scope.requestParam)
		}).then(function(response) {
			if (response.data.errCode) {
				// 处理失败
				MessageBoxService.alert(response.data.errMessage, function() {
					// 清空页面信息
					$scope.loginInfo.managerId = "";
					$scope.loginInfo.managerPwd = "";
				});
			} else {
				// 保存用户token
				window.sessionStorage.userToken = response.data.token;
				// 保存license是否过期状态
				window.sessionStorage.licenseOutOfDate = response.data.outOfDate;
				// 跳转主页面
				$state.go("home");
			}
		});
	};
});
}());