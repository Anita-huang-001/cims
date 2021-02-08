(function () {
"use strict"

/**
 * 用户编辑页面控制器
 */
app.controller("UserInfoUpdateController", function($scope, $rootScope, $state, $stateParams, $http, $translate, $filter,MessageBoxService) {
	/**
	 * 页面初始化
	 */
	$scope.init = function() {
		// 取得全部角色下拉列表
		$scope.roleAllList();
		// 查询用户详细信息
		$scope.selectUserInfo();
	};
	// 要编辑的用户ID
	var userId = $stateParams.userId;
	// 编辑用户页面原始数据模型
	$scope.userInfoOriginal = {};
	// 编辑用户页面绑定模型
	$scope.userInfo = {};
	
	/**
	 * 查询用户详细信息
	 * */ 
	$scope.selectUserInfo = function() {
		// 取得用户详细信息
		$http({
			url : "/userInfo/update/" + userId,
			method : "GET",
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				// 提示错误信息
				MessageBoxService.alert(response.data.errMessage, function() {
					// 返回用户管理页面
					$scope.go("home.userManage");
				});
			} else { // 处理成功
				// 保存用户信息
				$scope.userInfoOriginal = response.data;
				// 复制用户信息
				$scope.userInfo = angular.copy($scope.userInfoOriginal);
			}
		});
	}
	/**
	 * 查询全部角色列表
	 * */ 
	$scope.roleAllList = function() {
		$http({
			url : "/roleList",
			method : "GET"
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				// 提示错误信息
				MessageBoxService.alert(response.data.errMessage);
			} else { // 处理成功
				// 列表数据
				$scope.dropDownHeader();
				$scope.roleDropDown  = $scope.emptyHeader.concat(response.data);
			}
		});
	};
	
	/**
	 * 下拉列表空行
	 */
	$scope.dropDownHeader = function(){
		$scope.emptyHeader =new Array();
		$scope.emptyHeader[0]={"key":"","value":""};
	};
	
	/**
	 * 页面跳转
	 * @pageId 页面ID
	 */
	$scope.go = function(pageId) {
		// 跳转页面
		$scope.toStateName=pageId;
		$state.go(pageId);
	};
	
	/**
	 * 离开页面时处理
	 * */ 
	$scope.$on('$destroy', function() {
		// 如果跳转到查询页面
		if ($scope.toStateName != "home.userManage") {
			// 设置进入查询页面时查询标识
			$rootScope.userinfo = null;
		} 
	});
	
	/**
	 * 更新用户信息
	 * */ 
	$scope.update = function() {
		$http({
			url : "/userInfo/updateUser",
			method : "PUT",
			data : $.param($scope.userInfo)
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				// 提示错误 信息
				MessageBoxService.alert(response.data.errMessage);
			} else { // 处理成功
				// 提示成功信息
				MessageBoxService.alert($translate.instant("100003"), function() {
					// 更新成功后，跳转到应用管理页面
					$scope.go("home.userManage");
				});
			}
		});
	};
	
	/**
	 * 重置
	 * */ 
	$scope.reset = function() {
		// 复制应用信息
		$scope.userInfo = angular.copy($scope.userInfoOriginal);
	};
	/**
	 * 重置用户密码
	 * */ 
	$scope.resetPwssword = function() {
		MessageBoxService.confirm($translate.instant("100007"), function(){
			$http({
				url : "/userInfo/resetPwd/" + $scope.userInfo.userId,
				method : "PUT",
			}).then(function(response) {
				if (response.data.errCode) { // 处理失败
					// 提示错误 信息
					MessageBoxService.alert(response.data.errMessage);
				} else { // 处理成功
					// 提示成功信息
					MessageBoxService.alert($translate.instant("100008"), function() {
						// 新建成功后，跳转到应用管理页面
						$scope.go("home.userManage");
					});
				}
			});
		});
	};
	
	// 页面进行初始化
	$scope.init();
});
}());