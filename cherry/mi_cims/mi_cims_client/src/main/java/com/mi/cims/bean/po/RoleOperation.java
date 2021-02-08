package com.mi.cims.bean.po;

import lombok.Data;

@Data
public class RoleOperation {
	
    private Integer roleId;

    private String operationCode;
    
    private Long createTime;

    private Integer createUserId;

}
