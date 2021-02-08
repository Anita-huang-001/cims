package com.mi.cims.bean.vo;

import lombok.Data;

/**
 * 
 * ClassName: TopPageVo
 * Function: (top页数据VO).
 *
 * @author Magic Image-王宁
 * @date Nov 14, 2017 2:21:55 PM
 * @version V1.0.0
 */
@Data
public class TopPageVo {

    // 授权到期日期
    private String dueDate;

    // 版本号
    private String versionNo;

    // 版本类型
    private String versionType;
    
}
