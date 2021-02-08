package com.mi.cims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi.cims.bean.vo.TopPageVo;

/**
 * ClassName: TopPageController 
 * Function:  顶部页面处理控制器
 *
 * @author 	        孙忠飞
 * @date 	  2021年1月26日 下午3:47:12
 * @version   V1.0.0
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
     * @Description: 页面顶部信息
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @return: TopPageVo 顶部信息
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
