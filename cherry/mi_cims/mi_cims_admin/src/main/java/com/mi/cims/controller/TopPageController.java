package com.mi.cims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi.cims.bean.vo.TopPageVo;

/**
 * ClassName: TopPageController
 * Function:  初始页面控制器
 *
 * @author  孙忠飞
 * @date    2020年12月09日 下午1:14:09
 * @version V1.0.0
 */
@RestController
public class TopPageController {

    /**
     * 版本号
     * */
    public static final String VERSION_NO = "1.0.0";

    /**
     * 版本类型
     * */
    public static final String VERSION_TYPE = "GM";

    /**   
     * @Title: getTopPage
     * @Description: 初始页面查询
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @return: TopPageVo 初始页面数据
     * @throws: Exception
     */
    @GetMapping("/top")
    public TopPageVo getTopPage() throws Exception {
        // 返回TOP页的数据
        TopPageVo topPage = new TopPageVo();
		// 设置版本号
		topPage.setVersionNo(VERSION_NO);
		// 设置版本类型
		topPage.setVersionType(VERSION_TYPE);
		return topPage;
    }
}
