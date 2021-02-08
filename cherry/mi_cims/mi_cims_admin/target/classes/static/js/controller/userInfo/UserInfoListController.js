(function () {
"use strict"

/**
 * 用户列表页面控制器
 */
app.controller("UserInfoListController", function($scope, $rootScope, $state, $http, $translate,$window,CryptService,MessageBoxService) {
	
	/**
	 * 页面初始化
	 */
	$scope.init = function() {
		// 如果缓存数据不存在，设置默认空数据
		if (!$rootScope.userinfo) {
			$rootScope.userinfo = {
				condition : {
					//用户名
					loginId : "",
					//用户权限
					roleId : ""
				},
				requestCondition : {},
				listData : [],
				pagination : {
					pageNum : 1
				},
				searchFlag:""
			};
		} 
		// 查询条件模型
		$scope.condition = $rootScope.userinfo.condition;
		// 请求提交的查询条件
		$scope.requestCondition = $rootScope.userinfo.requestCondition;
		// 用户列表数据模型
		$scope.listData = $rootScope.userinfo.listData;
		// 分页数据
		$scope.pagination = $rootScope.userinfo.pagination;
		//查询动作
		$scope.searchFlag = $rootScope.userinfo.searchFlag;
		//有过查询动作
		if(	$scope.searchFlag == true){
			// 刷新表格
			$scope.refreshTable();
		}
		$scope.roleAllList();
		$scope.search();
	};
	/**
	 * 查询全部角色
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
	 * @param pageId 页面ID
	 * @param params 页面跳转参数
	 */
	$scope.go = function(pageId, params) {
		if (pageId == "home.userInfoAdd" || pageId == "home.userInfoUpdate") {
			// 保存当前查询页面状态
			$rootScope.userinfo = {
				condition : $scope.condition,
				requestCondition : $scope.requestCondition,
				listData : $scope.listData,
				pagination : $scope.pagination,
				searchFlag:$scope.searchFlag
			};
			//查询条件保存标识 为保存
			$scope.isSavedCurrentState = true;
		} else {
			// 清除页面状态缓存
			$rootScope.userinfo = null;
		}
		// 页面跳转
		$state.go(pageId, params);
	};
	
	/**
	 * 离开页面时处理
	 * */ 
	$scope.$on('$destroy', function() {
		// 如果未保存过数据，清空
		//如果保存了，不清空
		if (!$scope.isSavedCurrentState) {
			// 清除页面状态缓存
			$rootScope.userinfo = null;
		}
		//查询条件保存标识 为不保存
		$scope.isSavedCurrentState=false;
	})
	
	/**
	 * 查询
	 * */ 
	$scope.search = function() {
		$scope.searchFlag = true;
		// 复制保存查询参数
		$scope.requestCondition = angular.copy($scope.condition);
		
		// 查询第一页数据
		$scope.searchByPage(1);
	};
	
	/**
	 * 按页查询用户
	 * @param pageNum 页码
	 * */ 
	$scope.searchByPage = function(pageNum) {
		$http({
			url : "/userInfo",
			method : "GET",
			params : $scope.requestCondition,
			headers : {'Current-Page' : pageNum}
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				// 提示错误信息
				MessageBoxService.alert(response.data.errMessage);
			} else { // 处理成功
				// 列表数据
				$scope.listData = response.data.list;
				// 分页数据
				$scope.pagination = response.data;
			}
		});
	};
	
	/**
	 * 重置查询条件
	 * */ 
	$scope.reset = function() {
		$rootScope.userinfo="";
		// 清空查询条件
		$scope.condition = {
				// 用户名
				loginId : "",
				// 用户角色
				roleId : ""
		};
	};
	
	/**
	 * 编辑用户
	 * @param userId 用户ID
	 * */
	$scope.edit = function(userId) {
		// 跳转至应用编辑页面
		$scope.go("home.userInfoUpdate", {userId:userId});
	};
	
	/**
	 * 删除用户
	 * @param userId 用户ID
	 * */
	$scope.remove = function(userId) {
		MessageBoxService.confirm($translate.instant("100005"), function(){
			$http({
				url : "/userInfo/delete/" + userId,
				method : "PUT",
			}).then(function(response) {
				if (response.data.errCode) { // 处理失败
					MessageBoxService.alert(response.data.errMessage, function(){
						// 刷新表格
						$scope.refreshTable();
					});
				} else { // 处理成功
					// 提示删除成功信息
					MessageBoxService.alert($translate.instant("100002"), function(){
						// 刷新表格
						$scope.refreshTable();
					});
				}
			});
		});
	};
	
	/**
	 * 刷新表格
	 * */ 
	$scope.refreshTable = function() {
		// 重新查询当前分页
		$scope.searchByPage($scope.pagination.pageNum);
	};
	
	/**
	 * 切换分页
	 * @param pageNum 页码
	 * */ 
	$scope.changePagination = function(pageNum) {
		// 如果页数非法
		if (pageNum <= 0 || pageNum > $scope.pagination.pages
				|| pageNum == $scope.pagination.pageNum) {
			return;
		}
		// 查询指定页数据
		$scope.searchByPage(pageNum);
	};

	// 页面进行初始化
	$scope.init();
});
}());