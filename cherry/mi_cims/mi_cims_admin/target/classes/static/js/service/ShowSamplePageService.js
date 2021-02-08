"use strict"

/**
 * 显示示例对话框服务
 */
app.service("ShowSamplePageService", function($uibModal) {

	/**
	 * 显示示例对话框
	 * */ 
	this.showPage = function(flag) {
		var url;
		if(flag == 0){
			url = "view/group_ldap_sample.html";
		}else if(flag == 1){
			url = "view/group_ad_sample.html";
		}
		var modalInstance = $uibModal.open({
			templateUrl : url,
			controller : "ShowSamplePageController",
			backdrop : "static",
			resolve : {
				flag : function() {
					return flag;
				}
			}
		});
	};
}); 