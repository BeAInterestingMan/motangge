package com.motang.motangge.controller;


import com.motang.motangge.entity.Book;
import com.motang.motangge.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 书籍表 前端控制器
 * @author liuhu
 * @Date 2020/12/10 21:07
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("{id}")
    public Book book (@PathVariable("id")Long id){
        return bookService.selectOne(id);
    }
}
