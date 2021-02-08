(function () {
"use strict"

/**
 * 显示配置说明对话框控制器
 */
app.controller("ShowSamplePageController", function($scope, $uibModalInstance, flag) {
	
	// LDAP服务器方式
	if(flag == 0){
		$scope.group.account="cn=Manager,o=csdc,c=cn";
	}else if(flag == 1){// AD域方式
		$scope.group.account="mi@mipass.local";
	}
	// context_factory
	$scope.group.contextFactory="com.sun.jndi.ldap.LdapCtxFactory";
	// provider_url
	$scope.group.providerUrl="ldap://192.168.3.238:389";
	// security_authentication
	$scope.group.securityAuth="simple";
	// baseDN
	$scope.group.ldapBasedn="DC=mipass,DC=local";
	// password
	$scope.group.password="1234567890";
	// filter
	$scope.group.filter="(&(cn=mizhen)(objectClass=top))";
	
	// 关闭页面按钮点击操作
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});
}());