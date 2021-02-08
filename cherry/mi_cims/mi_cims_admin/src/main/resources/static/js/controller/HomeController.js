(function () {
"use strict"

/**
 * 首页控制器
 */
app.controller("HomeController", function($scope, $rootScope, $state, $translate, $http, LANGUAGE_LIST, MessageBoxService) {
	// 菜单内容数据模型
	$scope.menuData = {};
	// 菜单收取状态模型
	$scope.menuCollapseState = [];
	
	/**
	 * 页面初始化
	 * */ 
	$scope.init = function() {
		// 取得系统菜单
		$http({
			url : "/home/menu",
			method : "GET",
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				MessageBoxService.alert(response.data.errMessage);
			} else { // 处理成功
				// 菜单数据
				var menuData = response.data;
				// 设置所有菜单为收起状态
				for (var i = 0; i < menuData.length; i++) {
					$scope.menuCollapseState[i] = true;
				}
				// 菜单数据模型
				$scope.menuData = menuData;
				
				// 生成菜单权限集合
				$rootScope.menuSet = [];
				for (var i = 0; i < menuData.length; i++) {
					$rootScope.menuSet.push(menuData[i].id);
					if(menuData[i].subMenu) {
						for (var j = 0; j < menuData[i].subMenu.length; j++) {
							$rootScope.menuSet.push(menuData[i].subMenu[j].id);
						}
					}
				}
			}
		});
		
		// 取得操作权限集合
		$http({
			url : "/home/operation",
			method : "GET",
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				MessageBoxService.alert(response.data.errMessage);
			} else { // 处理成功
				// 操作权限集合
				$rootScope.operationSet = response.data;
			}
		});
		
		// 打开top页面
		$state.go("home.top");
	}
	
	/**
	 * 页面跳转
	 * @pageId 页面ID
	 */
	$scope.go = function(pageId) {
		$state.go(pageId);
	};
	
	/**
	 * 菜单点击
	 * */ 
	$scope.clickMenu = function(toState, menuIndex) {
		// 如果菜单没有子菜单
		if (toState) {
			// 直接打开菜单对应的页面
			$state.go(toState, {});
		} else { // 如果是父级菜单，有子菜单
			// 切换子菜单的显示隐藏状态
			$scope.menuCollapseState[menuIndex] = !$scope.menuCollapseState[menuIndex];
		}
	}
	
	/**
	 * 注销
	 */
	$scope.logout = function() {
		MessageBoxService.confirm($translate.instant("100006"),function(){
			// 执行注销操作
			$http({
				url : "/logout",
				method : "GET",
			}).then(function(response) {
				if (response.data.errCode) { // 处理失败
					MessageBoxService.alert(response.data.errMessage);
				} else { // 处理成功
						// 跳转到登录页面
						$scope.go("login");
				}
			});
		});
	}
	
	// 页面初始化
	$scope.init();
});
}());