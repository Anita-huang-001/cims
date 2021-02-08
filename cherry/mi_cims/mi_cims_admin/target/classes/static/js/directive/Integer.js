"use strict"

/**
 * 整数校验
 */
app.directive('integer', function() {
	return {
		restrict : "A",
		require : "ngModel",
		link : function(scope, ele, attrs, ngModelController) {
			ngModelController.$parsers.push(function(viewValue) {
				if (viewValue && viewValue.length > 0) {
					for (var i = 0; i < viewValue.length; i++) {
						if (viewValue.charAt(i) >= '0' && viewValue.charAt(i) <= '9') {
							ngModelController.$setValidity('integer', true);
						} else {
							ngModelController.$setValidity('integer', false);
							break;
						}
					}
				}
				return viewValue;
			});
		}
	}
})