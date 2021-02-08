package com.mi.cims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: IndexController 
 * Function: 默认页处理控制器
 *
 * @author Magic Image-刘伟
 * @date 2017年9月22日 下午3:47:12
 * @version V1.0.0
 */
@Controller
public class IndexController {

    /**
     * showIndexPage:默认页面处理
     * 
     * @author 刘伟
     * @date 2017年9月4日 下午2:42:25
     * @return String 默认页面URL
     */
    @RequestMapping(value = { "", "/" })
    public String showIndexPage() {
        return "index.html";
    }

}
