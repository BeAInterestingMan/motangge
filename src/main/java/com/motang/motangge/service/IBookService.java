package com.motang.motangge.service;

import com.motang.motangge.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description 书籍表 服务类
 * @author liuhu
 * @Date 2020/12/10 21:15
 */
public interface IBookService  {
    /**
     * @description 根据书籍名称查找
     * @author liuhu
     * @param name
     * @date 2020/12/16 9:17
     * @return com.motang.motangge.entity.Book
     */
    Book selectByBookName(String name);
    
    /**
     * @description
     * @author liuhu
     * @param 
     * @date 2020/12/15 15:52
     * @return java.util.List<com.motang.motangge.entity.Book>
     */
    List<Book> selectBookList();

    /**
     * @Description 保存图书
     * @author liuhu
     * @Date 2020/12/15 22:15
     */
    void saveBook(Book book);
}
