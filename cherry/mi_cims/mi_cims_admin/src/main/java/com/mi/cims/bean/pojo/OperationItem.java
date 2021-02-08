package com.mi.cims.bean.pojo;

import lombok.Data;

/**
 * ClassName: OperationItem
 * Function:  操作权限
 *
 * @author Magic Image-刘伟
 * @date 2017年10月16日 下午6:58:56
 * @version V1.0.0
 */
@Data
public class OperationItem {

    /**
     * 操作编码
     * */
    private String code;

    /**
     * 操作文字
     * */
    private String text;

    /**
     * 是否是默认动作
     * */
    private boolean defaultOpt;

}
