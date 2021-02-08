(function () {
"use strict"

/**
 * top页控制器
 */
app.controller("TopController", function($scope, $http, $state,$window, MessageBoxService) {
	$scope.init = function() {
		// 编辑TOP页面原始数据模型
		$scope.topPage = {
				//版本号
				versionNo:"",
				//版本类型
				versionType:"",
		};
		// 执行一遍查询
		$scope.refreshTable();
	}
	
	/**
	 * 刷新表格
	 * */ 
	$scope.refreshTable = function() {
		// 取得TOP页数据
		$http({
			url : "/top",
			method : "GET",
		}).then(function(response) {
			
			if (response.data.errCode) { // 处理失败
				// 提示错误信息
				MessageBoxService.alert(response.data.errMessage, function() {
					// 刷新页面
					window.location.reload();
				});
			} else { // 处理成功
				// 取得TOP页信息
				$scope.topPage = response.data;
			}
		});
	};
	
	/**
	 * 页面跳转
	 * @pageId 页面ID
	 */
	$scope.go = function(pageId) {
		// 执行一遍查询
		$scope.refreshTable();
	};
	// 页面进行初始化
	$scope.init();
});
}());