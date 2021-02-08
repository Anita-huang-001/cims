package com.mi.cims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mi.cims.bean.po.RoleInfo;
import com.mi.cims.bean.pojo.DropDown;
import com.mi.cims.dao.RoleInfoMapper;
import com.mi.cims.service.RoleInfoService;

/**
 * ClassName: RoleInfoServiceImpl
 * Function:  角色接口实现
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */

@Service
public class RoleInfoServiceImpl implements RoleInfoService{

	// 角色接口
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	
    /**   
     * @Title: getRoleInfoAllList
     * @Description: 获取全部角色
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @return: List<DropDown> 角色列表
     * @throws: Exception
     */
	public List<DropDown> getRoleInfoAllList() throws Exception{
		// 查询所有的角色
		List<RoleInfo> roleInfoAllList = roleInfoMapper.selectAll();
        // 封装角色下拉框
        List<DropDown> dropDownList = new ArrayList<DropDown>();
        for (int i = 0; i < roleInfoAllList.size(); i++) {
            DropDown dropDown = new DropDown();
            dropDown.setKey(roleInfoAllList.get(i).getRoleId().toString());
            dropDown.setValue(roleInfoAllList.get(i).getRoleName());
            dropDownList.add(dropDown);
        }
        return dropDownList;
	}
}
