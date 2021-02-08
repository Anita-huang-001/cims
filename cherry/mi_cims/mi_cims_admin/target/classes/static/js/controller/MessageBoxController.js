(function () {
"use strict"

/**
 * alert对话框控制器
 */
app.controller("AlertMessageBoxController", function($scope, $uibModalInstance, message) {
	// 要显示的信息
	$scope.message = message;
	
	// OK按钮点击操作
	$scope.ok = function() {
		$uibModalInstance.close();
	};
});

/**
 * confirm对话框控制器
 */
app.controller("ConfirmMessageBoxController", function($scope, $uibModalInstance, message) {
	// 要显示的信息
	$scope.message = message;
	
	// 确定按钮点击操作
	$scope.ok = function() {
		$uibModalInstance.close();
	};
	
	// 取消按钮点击操作
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});
}());