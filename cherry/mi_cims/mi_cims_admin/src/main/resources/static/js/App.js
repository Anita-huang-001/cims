"use strict"

/**
 * 主模块
 */
var app = angular.module("app", [
    // 路由模块 
	"ui.router",
	// UI模块 
	"ui.bootstrap",
	//"ui.select",
	// 动画模块
	"ngAnimate",
	// 消息提示模块
	"ngMessages",
	// highcharts图表模块
	"highcharts-ng",
	// 懒加载模块
	"oc.lazyLoad",
	// 多语言化模块
	"pascalprecht.translate"
]);

/**
 * 按键处理
 */
function keyEventHandler(e) {
	//获取event对象
	var ev = e || window.event;
	// 获取事件源
	var obj = ev.target || ev.srcElement;
	// 获取事件源类型
	var t = obj.type || obj.getAttribute('type');
	//获取作为判断条件的事件类型
	var vReadOnly = obj.getAttribute('readonly');
	var vEnabled = obj.getAttribute('enabled');
	// 处理null值情况
	vReadOnly = (vReadOnly == null) ? false : true;
	vEnabled = (vEnabled == null) ? true : vEnabled;
	
	// ESC键
	if (e.keyCode == 27) {
		// 禁用ESC键
		return false;
	}
	// 回退键
	if (e.keyCode == 8) {
		// 当事件源类型为密码或单行、多行文本的，不处理
		if ((t == "password" || t == "text" || t == "textarea")
				&& vReadOnly != true && vEnabled == true) {
			return true;
		} else { // 其它情况屏蔽退格键
			return false;
		}
	}
}

// 按键事件处理（Firefox、Opera）
document.onkeypress = keyEventHandler;
// 按键事件处理（IE、Chrome）
document.onkeydown = keyEventHandler;
