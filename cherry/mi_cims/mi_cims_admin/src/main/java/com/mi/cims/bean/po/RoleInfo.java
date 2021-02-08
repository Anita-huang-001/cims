package com.mi.cims.bean.po;

import lombok.Data;

@Data
public class RoleInfo {
    private Integer roleId;

    private String roleName;

    private Long createTime;

    private Integer createUserId;

    private Long updateTime;

    private Integer updateUserId;

}