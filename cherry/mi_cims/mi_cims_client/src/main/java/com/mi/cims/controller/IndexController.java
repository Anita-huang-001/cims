package com.mi.cims.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: IndexController 
 * Function:  默认页处理控制器
 *
 * @author 	        孙忠飞
 * @date 	  2021年1月26日 下午3:47:12
 * @version   V1.0.0
 */
@RestController
public class IndexController {

    /**
     * showIndexPage:默认页面处理
     * 
     * @author 		   孙忠飞
     * @date 		 2021年1月26日 下午3:47:12
     * @return 		   链接成功 显示信息
     */
    @RequestMapping(value = { "", "/" })
    public String showIndexPage() {
        return "Client server is running...";
    }

}
