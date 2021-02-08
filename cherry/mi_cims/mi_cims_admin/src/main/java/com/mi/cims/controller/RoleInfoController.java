package com.mi.cims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi.cims.bean.pojo.DropDown;
import com.mi.cims.service.RoleInfoService;

/**
 * ClassName: RoleInfoController
 * Function:  角色控制器
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */

@RestController
public class RoleInfoController {
	
	// 角色接口
	@Autowired
	private RoleInfoService roleInfoService;

    /**   
     * @Title: getRoleInfoAllList
     * @Description: 获取全部角色
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @return: List<DropDown> 角色列表
     * @throws: Exception
     */
	@GetMapping("/roleList")
	public List<DropDown> getRoleInfoAllList() throws Exception {
        // 查询所有角色列表
		return roleInfoService.getRoleInfoAllList();
	}
}
