"use strict"

/**
 * 整数范围校验
 * 例：
 * data-range="6,64"
 */
app.directive('range', function() {
	return {
		restrict : "A",
		require : "ngModel",
		link : function(scope, ele, attrs, ngModelController) {
			ngModelController.$parsers.push(function(viewValue) {
				if (viewValue.trim().length == 0) {
					ngModelController.$setValidity('range', true);
				}
				if (viewValue && viewValue.length > 0) {
					var REGEXP = /^-[1-9][0-9]*$|^0$|^[1-9][0-9]*$/;
					if (REGEXP.test(viewValue) == false) {
						ngModelController.$setValidity('range', false);
						return viewValue;
					}
					var i = parseInt(viewValue);
					if (isNaN(i)) {
						ngModelController.$setValidity('range', false);
					} else {
						var min = parseInt(attrs.range.split(',')[0]);
						var max = parseInt(attrs.range.split(',')[1]);
						if (i < min || i > max) {
							ngModelController.$setValidity('range', false);
						} else {
							ngModelController.$setValidity('range', true);
						}
					}
				}
				return viewValue;
			});
			
			ngModelController.$formatters.push(function(modelValue) {
				// 校验错误标志更改为正常
				ngModelController.$setValidity('range', true);
				if (modelValue == undefined) {
					return;
				}
				var i;
				// 值是字符串
				if (isNaN(modelValue)) {
					ngModelController.$setValidity('range', false);
					return modelValue;
				} else {
					// 值是数字
					i = parseInt(modelValue);
					// modelValue 为空时
					if (isNaN(i)) {
						return modelValue.trim();
					}
				}
				// 属性检查
				var min = parseInt(attrs.range.split(',')[0]);
				var max = parseInt(attrs.range.split(',')[1]);
				if (i < min || i > max) {
					ngModelController.$setValidity('range', false);
				} else {
					ngModelController.$setValidity('range', true);
				}
				return modelValue;
			});
		}
	}
})