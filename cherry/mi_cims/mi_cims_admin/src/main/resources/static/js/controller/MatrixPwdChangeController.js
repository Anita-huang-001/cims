(function () {
"use strict"

/**
 * 首页修改图形口令控制器
 */
app.controller("MatrixPwdChangeController", function($scope, $rootScope, $state, $stateParams, $translate, $http, LANGUAGE_LIST,CryptService, MessageBoxService) {
	
	/**
	 * 页面初始化
	 */
	$scope.init = function() {
		//原图形口令动态阵
		$scope.oldMatrixItems = [];
		// 动态阵列表元素
		$scope.matrixItems = [];
		// 确认动态阵列表元素
		$scope.matrixForSureItems = [];
		// 动态阵列表图片路径
		$scope.imagePath = "image/digit";
		//刷新动态阵列表
//		$scope.refreshMatrix();
	};
	
	// 修改管理员图形口令模型
	$scope.managerInfo = {
			// 原图形口令
			oldMatrixPwd:"",
			// 图形口令
			matrixPwd:"",
			// 图形口令确认
			matrixPwdForSure:"",
			// 原图形口令动态阵列表
			oldMatrix:"",
			// 动态阵列表	
			newMatrix : "",
			// 确认动态阵列表	
			confirmMatrix : "",
			// 阵列表Token
			token:""
	};
	
	/**
	 * 刷新动态阵列表
	 * */ 
	$scope.refreshMatrix = function() {
		// 清空动态阵列表元素
		$scope.oldMatrixItems = [];
		$scope.matrixItems = [];
		$scope.matrixForSureItems = [];
		// 取得动态阵列表
		$http({
			url : "/getChangeMatrix",
			method : "GET"
		}).then(function(response) {
			if (response.data.errCode) {
				// 处理失败
				MessageBoxService.alert(response.data.errMessage);
			} else {
				// 登录时动态阵列表
				var oldMatrixList = response.data.oldMatrix;
				// 动态阵列表token
				$scope.managerInfo.token = response.data.token;
				// 按字符分割成动态阵列表元素
				$scope.oldMatrixItems = oldMatrixList.split("");
				// 保存阵列表
				$scope.managerInfo.oldMatrix = oldMatrixList;
				// 动态阵列表
				var matrix = response.data.newMatrix;
				// 按字符分割成动态阵列表元素
				$scope.matrixItems = matrix.split("");
				// 保存阵列表
				$scope.managerInfo.newMatrix = matrix;
				// 确认动态阵列表
				var matrixForSure = response.data.confirmMatrix;
				// 按字符分割成动态阵列表元素
				$scope.matrixForSureItems = matrixForSure.split("");
				// 确认动态阵列表
				$scope.managerInfo.confirmMatrix = matrixForSure;
			}
		});
	};
	/**
	 * 修改图形口令
	 * */ 
	$scope.matrixPwdChange = function() {
		// 复制请求参数
		$scope.requestParam = angular.copy($scope.managerInfo);
		//前台加密
		var secretKey = window.sessionStorage.userToken.substring(0,16);
		// 原图形口令加密
		$scope.requestParam.oldMatrixPwd = CryptService.MD5(CryptService.SHA256(CryptService.MD5($scope.requestParam.oldMatrixPwd)));
		// 加密图形口令
		$scope.requestParam.matrixPwd =  CryptService.MD5(CryptService.SHA256(CryptService.MD5($scope.requestParam.matrixPwd)));
		// 加密图形口令确认
		$scope.requestParam.matrixPwdForSure =  CryptService.MD5(CryptService.SHA256(CryptService.MD5($scope.requestParam.matrixPwdForSure)));
//		// 加密图形口令
//		$scope.requestParam.matrixPwd =  CryptService.AES.encrypt($scope.requestParam.matrixPwd,secretKey);
//		// 加密图形口令确认
//		$scope.requestParam.matrixPwdForSure =  CryptService.AES.encrypt($scope.requestParam.matrixPwdForSure,secretKey);
		//发送请求		
		$http({
			url : "/matrixPwdChange" ,
			method : "PUT",
			data : $.param($scope.requestParam)
		}).then(function(response) {
			if (response.data.errCode) { // 处理失败
				MessageBoxService.alert(response.data.errMessage, function() {
					//原图形口令
					$scope.managerInfo.oldMatrixPwd = "";
					//图形口令
					$scope.managerInfo.matrixPwd = "";
					//图形口令确认
					$scope.managerInfo.matrixPwdForSure = "";
					// 刷新动态阵列表
//					$scope.refreshMatrix();
				});
			} else { // 处理成功
				// 提示成功信息
				MessageBoxService.alert($translate.instant("100004"), function() {
					// 修改成功后，跳转到登录页面
					$state.go("login");
				});
			}
		});
	};
	/**
	 * 页面跳转
	 * @pageId 页面ID
	 */
	$scope.go = function(pageId) {
		$state.go(pageId);
	};
	/**
	 * 重置
	 * */ 
	$scope.reset = function() {
		// 清空修改管理员图形口令模型
		//原图形口令
		$scope.managerInfo.oldMatrixPwd = "";
		//图形口令
		$scope.managerInfo.matrixPwd = "";
		//图形口令确认
		$scope.managerInfo.matrixPwdForSure = "";
	};
	
	// 页面进行初始化
	$scope.init();
});
}());