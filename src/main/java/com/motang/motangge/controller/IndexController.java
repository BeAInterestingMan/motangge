package com.motang.motangge.controller;

import com.motang.motangge.common.constant.BookConstants;
import com.motang.motangge.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description 首页控制器
 * @author liuhu
 * @Date 2020/12/15 14:45
 */
@Controller
public class IndexController {

    @Autowired
    private IBookService bookService;

    @Value("${index.template}")
    private String indexTemplate;

    @RequestMapping("index")
    public String index(Model model){
        // 1 小说分类
        // 2 小说列表
        // 3 热门小说
        bookService.selectBookList();
        return BookConstants.HTML_PREFIX +indexTemplate;
    }

}
