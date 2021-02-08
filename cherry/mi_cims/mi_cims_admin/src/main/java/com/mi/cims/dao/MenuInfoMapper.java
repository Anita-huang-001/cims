package com.mi.cims.dao;

import java.util.List;

import com.mi.cims.bean.po.MenuInfo;

public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);

    List<MenuInfo> selectAll();

    List<MenuInfo> selectByRoleId(Integer roleId);
}
