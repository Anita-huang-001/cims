package com.mi.cims.dao;

import java.util.List;

import com.mi.cims.bean.po.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> selectAll();

//    List<RoleInfo> selectByGetRoleInfoBo(GetRoleInfoBo getRoleInfoBo);

    RoleInfo selectByRoleName(String roleName);
    
    List<RoleInfo> selectAllFa();
    
    List<RoleInfo> selectAllSe();
    
    // 根据用户角色id查询相关角色信息
    List<RoleInfo> selectUserInfoAll(String[] roleId);
}