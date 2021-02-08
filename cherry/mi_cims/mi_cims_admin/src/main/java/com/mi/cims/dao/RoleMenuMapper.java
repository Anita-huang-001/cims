package com.mi.cims.dao;

import java.util.List;
import java.util.Set;

import com.mi.cims.bean.po.RoleMenu;


public interface RoleMenuMapper {
//    int deleteByPrimaryKey(RoleMenuKey key);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

//    RoleMenu selectByPrimaryKey(RoleMenuKey key);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    int insertList(List<RoleMenu> list);

    int deleteByRoleId(Integer roleId);

    Set<Integer> selectMenuIdByRoleId(Integer roleId);
}