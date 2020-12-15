package com.motang.motangge.service.impl;

import com.motang.motangge.entity.Book;
import com.motang.motangge.mapper.BookMapper;
import com.motang.motangge.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 书籍表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 20:03
 */
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public Book selectOne(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> selectBookList() {
        try {
        }catch (Exception e){

        }
        return null;
    }
}
