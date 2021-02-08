package com.mi.cims.dao;

import java.util.List;

import com.mi.cims.bean.po.OperationInfo;

public interface OperationInfoMapper {
    int deleteByPrimaryKey(String operationCode);

    int insert(OperationInfo record);

    int insertSelective(OperationInfo record);

    OperationInfo selectByPrimaryKey(String operationCode);

    int updateByPrimaryKeySelective(OperationInfo record);

    int updateByPrimaryKey(OperationInfo record);

    List<OperationInfo> selectAll();
}