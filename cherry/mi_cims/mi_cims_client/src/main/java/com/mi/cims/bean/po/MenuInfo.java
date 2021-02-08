package com.mi.cims.bean.po;

import lombok.Data;

@Data
public class MenuInfo {
    private Integer menuId;

    private String menuName;

    private String textKey;

    private String pageUrl;

    private String icon;

    private Integer parentMenuId;

    private Integer serial;

}
