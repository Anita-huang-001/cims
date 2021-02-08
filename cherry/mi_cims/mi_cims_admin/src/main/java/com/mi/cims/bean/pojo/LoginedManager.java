package com.mi.cims.bean.pojo;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**   
 * @ClassName: LoginedManager   
 * @Description: 已登录管理员缓存数据
 * @author: 刘伟
 * @date: 2017年9月8日 下午12:59:28   
 */
@Data
public class LoginedManager {

    /**
     * ID
     * */
    private int id;

	/**
     * 管理员ID
     * */
    private String managerId;

    /**
     * 管理员姓名
     * */
    private String managerName;

    /**
    * 角色ID
    * */
    private String roleId;

    /**
     * 动作集合
     */
    private Set<String> OperationSet = new HashSet<String>();

}
