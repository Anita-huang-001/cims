package com.mi.cims.bean.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * ClassName: MenuItem
 * Function: 菜单项
 *
 * @author Magic Image-刘伟
 * @date 2017年10月12日 下午1:56:52
 * @version V1.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {

    /**
     * 菜单id
     * */
    private int id;

    /**
     * 菜单文字
     * */
    private String text;

    /**
     * 菜单图标
     * */
    private String icon;

    /**
     * 菜单对应页面
     * */
    private String page;

    /**
     * 子菜单
     * */
    private List<MenuItem> subMenu;

    /**
     * 菜单对应的动作
     * */
    private List<OperationItem> operations;

}
