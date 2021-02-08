package com.mi.cims.dao;

import java.util.List;
import java.util.Set;

import com.mi.cims.bean.po.RoleOperation;

public interface RoleOperationMapper {
//    int deleteByPrimaryKey(RoleOperationKey key);

    int insert(RoleOperation record);

    int insertSelective(RoleOperation record);

//    RoleOperation selectByPrimaryKey(RoleOperationKey key);

    int updateByPrimaryKeySelective(RoleOperation record);

    int updateByPrimaryKey(RoleOperation record);

    int insertList(List<RoleOperation> list);

    int deleteByRoleId(Integer roleId);

    Set<String> selectOperationCodeByRoleId(Integer roleId);
}
