package com.mi.cims.service;

import java.util.List;

import com.mi.cims.bean.pojo.DropDown;

/**
 * ClassName: RoleInfoService
 * Function:  角色接口
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
public interface RoleInfoService {

	
    /**   
     * @Title: getRoleInfoAllList
     * @Description: 获取全部角色
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @return: List<DropDown> 角色列表
     * @throws: Exception
     */
	public List<DropDown> getRoleInfoAllList() throws Exception;
}
