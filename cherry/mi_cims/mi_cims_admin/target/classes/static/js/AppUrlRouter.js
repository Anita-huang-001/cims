"use strict"

/**
 * 路由配置
 */
app.config(function ($stateProvider, $urlRouterProvider) {
    // 默认加载页面
    $urlRouterProvider.when("", "/login");
    // 未找到页面
    $urlRouterProvider.otherwise('/404');
    
    // 配置路由规则
    $stateProvider
    	/*************************** 404页 ***************************/
	 	// 404页
	    .state("404", {
	        url: "/404",
	        templateUrl: "view/404.html",
	    })
        /*************************** 登录 ***************************/
	    // 登录页
        .state("login", {
            url: "/login",
            templateUrl: "view/login.html",
            controller: "LoginController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/LoginController.js"]);
                }]
        	}
        })
        /*************************** 后台管理主页 ***************************/
        // 后台管理主页
        .state("home", {
        	url: "/home", 
            templateUrl: "view/home.html",
            controller: "HomeController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/HomeController.js"]);
                }]
        	}
        })
        // top页
        .state("home.top", {
        	url: "/top",
            templateUrl: "view/top.html",
            controller: "TopController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/TopController.js"]);
                }]
        	}
        })
        // 修改当前管理员图形口令
        .state("home.changeMatrixPwd", {
        	url: "/matrix_pwd/change", 
            templateUrl: "view/matrix_pwd_change.html",
            controller: "MatrixPwdChangeController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/MatrixPwdChangeController.js"]);
                }]
        	}
        })
      
        /*************************** 用户管理 ***************************/
        // 用户列表
        .state("home.userManage", {
        	menuId: 100,
         	url: "/userInfo",
        	templateUrl: "view/userInfo/user_info_list.html",
            controller: "UserInfoListController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/userInfo/UserInfoListController.js"]);
                }]
        	}
        })
        // 新建用户
        .state("home.userInfoAdd", {
        	operationCode: "ADD_USER",
         	url: "/userInfo/add", 
        	templateUrl: "view/userInfo/user_info_add.html",
            controller: "UserInfoAddController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/userInfo/UserInfoAddController.js"]);
                }]
        	}
        })
        // 编辑用户
        .state("home.userInfoUpdate", {
        	operationCode: "UPDATE_USER",
         	url: "/userInfo/update/:userId", 
        	templateUrl: "view/userInfo/user_info_update.html",
            controller: "UserInfoUpdateController",
            resolve: {
                deps: ["$ocLazyLoad",
                  function($ocLazyLoad){
                    return $ocLazyLoad.load(["js/controller/userInfo/UserInfoUpdateController.js"]);
                }]
        	}
        })



});