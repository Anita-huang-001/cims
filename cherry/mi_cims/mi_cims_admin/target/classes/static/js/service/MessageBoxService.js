"use strict"

/**
 * 消息对话框服务
 */
app.service("MessageBoxService", function($rootScope, $uibModal) {
	/**
	 * 显示alert对话框
	 * @param message 要显示的信息
	 * @param okFunction 点击确定按钮后回调函数
	 * */
	this.alert = function(message, okFunction) {
		// 对话框弹出标识
		if($rootScope.alertShow) {
			return;
		} else {
			$rootScope.alertShow = true;
		}
		
		var modalInstance = $uibModal.open({
			templateUrl : "view/message-box/alert.html",
			controller : "AlertMessageBoxController",
			backdrop : "static",
			resolve : {
				message : function() {
					return message;
				}
			}
		});
		// 执行窗口关闭函数
		modalInstance.result.then(function(){
			// 对话框关闭标识
			$rootScope.alertShow = false;
			if (okFunction) {
				okFunction();
			}
		});
	};
	
	/**
	 * 显示confirm对话框
	 * @param message 要显示的信息
	 * @param okFunction 点击确定按钮后回调函数
	 * @param cancelFunction 点击取消按钮后回调函数
	 * */
	this.confirm = function(message, okFunction, cancelFunction) {
		var modalInstance = $uibModal.open({
			templateUrl : "view/message-box/confirm.html",
			controller : "ConfirmMessageBoxController",
			backdrop : "static",
			resolve : {
				message : function() {
					return message;
				}
			}
		});
		// 执行窗口关闭函数
		modalInstance.result.then(okFunction, cancelFunction);
	};
	
	
	
	/**
	 * 显示alert对话框
	 * @param message 要显示的信息
	 * @param okFunction 点击确定按钮后回调函数
	 * */
	this.alerts = function(message, okFunction) {
		// 对话框弹出标识
		if($rootScope.alertShow) {
			return;
		} else {
			$rootScope.alertShow = true;
		}
		
		var modalInstance = $uibModal.open({
			templateUrl : "view/message-box/alert2.html",
			controller : "AlertMessageBoxController",
			backdrop : "static",
			resolve : {
				message : function() {
					return message;
				}
			}
		});
		// 执行窗口关闭函数
		modalInstance.result.then(function(){
			// 对话框关闭标识
			$rootScope.alertShow = false;
			if (okFunction) {
				okFunction();
			}
		});
	};
	
}); 