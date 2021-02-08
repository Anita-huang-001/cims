"use strict"

/**
 * 多选框树
 */
app.directive('checkboxTree', function() {
	return {
		restrict : "A",
		require : "ngModel",
		link : function($scope, element, attrs, ngModel) {
			// zTree配置
			var setting = {
				// 多选框树
				check : {
					enable : true,
					chkboxType : {
						// 被勾选时：关联父p  关联子s
						"Y" : "ps",
						// 取消勾选时：关联父p 关联子s
						"N" : "s"
					}
				},
				// 数据类型
				data : {
					simpleData : {
						enable : true
					}
				},
				// 回调函数
				callback : {
					// 树节点选择事件处理
					onCheck : function(event, treeId, treeNode, clickFlag) {
						// 树对象
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						// 取得所有已选中的树节点
						var nodes = treeObj.getCheckedNodes(true);
						// 取得已选中节点ID
						var selectedNodesIds = [];
						for (var i = 0; i < nodes.length; i++) {
							selectedNodesIds.push(nodes[i].id);
						}
						// 同步到树控件绑定的变量上
						ngModel.$setViewValue(selectedNodesIds);
					}
				}
			}; 
			
			// 接受控制器发送的多选树数据  
			$scope.$on("RefreshCheckboxTree", function(event, data){
				if (data.treeId == element[0].id) {
					$.fn.zTree.init($("#" + element[0].id), setting, data.nodes);
				}
            }); 
			
			// 清空树中所有选择框  
			$scope.$on("ClearCheckedCheckboxTree", function(event, treeId){
				if (treeId == element[0].id) {
					// 树对象
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					// 取消所有选择
					treeObj.checkAllNodes(false);
				}
            }); 
			
			// 设置树中选中节点
			$scope.$on("SetCheckboxTreeCheckNodes", function(event, data){
				if (data.treeId == element[0].id) {
					// 树对象
					var treeObj = $.fn.zTree.getZTreeObj(data.treeId);
					// 取消所有已选择的节点
					treeObj.checkAllNodes(false);
					// 遍历所有要选中的节点
					for (var i = 0; i < data.checkNodes.length; i++) {
						// 根据ID取得对应的树节点
						var node = treeObj.getNodeByParam("id", data.checkNodes[i]);
						if (node) {
							// 将树节点设置为选中状态
							treeObj.checkNode(node, true, false, true);
						}
					}
				}
            });
			
			// 向控制器发送消息，进行树数据的获取
            $scope.$emit("GetCheckboxTreeData", element[0].id);
		}
	}
})