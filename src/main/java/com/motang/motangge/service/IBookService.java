package com.motang.motangge.service;

import com.motang.motangge.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description 书籍表 服务类
 * @author liuhu
 * @Date 2020/12/10 21:15
 */
public interface IBookService  {

    Book selectOne(Long id);
}
