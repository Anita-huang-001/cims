(function () {
"use strict"

/**
 * 用户管理控制器
 */
app.controller("UserInfoAddController", function($scope, $rootScope, $state, $translate, $http, LANGUAGE_LIST, MessageBoxService) {
	
	/**
	 * 页面初始化
	 */
	$scope.init = function() {
		// 取得全部角色下拉列表
		$scope.roleAllList();
	};
	
	/**
	 *  添加用户信息模型
	 */
	$scope.userInfo = {
			// 登录ID
			loginId : "",
			// 用户名
			userName : "",
			// 电话号
			userPhone : "",
			//用户邮箱
			mail : "",
			// 用户角色
			roleId : ""
	};
	
	/**
	 *  用户添加成功处理函数
	 * */ 
	$scope.add = function() {
		// 添加用户请求
		$http({
			url : '/userInfo/add',
			method : 'POST',
			data : $.param($scope.userInfo)
		}).then(function(response) {
			// 处理失败
			if (response.data.errCode) { 
				 MessageBoxService.alert(response.data.errMessage);
			}else{
				// 提示成功信息
				MessageBoxService.alert($translate.instant("100001"), function() {
					// 成功后，跳转到管理页面
					$scope.go("home.userManage");
				});
			}
		});
	};
	
	/**
	 * 页面跳转
	 * @pageId 页面ID
	 */
	$scope.go = function(pageId) {
		//跳转页面
		$scope.toStateName = pageId;
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
	 * 重置
	 * */ 
	$scope.reset = function() {
		// 清空新建应用模型
		$scope.userInfo = {
				// 登录名
				loginId : "",
				// 用户名
				userName : "",
				// 电话号
				userPhone : "",
				// 用户邮箱
				mail : "",
				// 用户角色
				roleId : ""
			};
	};
	
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
	
	// 页面进行初始化
	$scope.init();
});
}());