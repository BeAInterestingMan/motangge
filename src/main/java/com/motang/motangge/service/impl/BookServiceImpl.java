package com.motang.motangge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.motangge.common.commonEnum.WrapperEnum;
import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.entity.Book;
import com.motang.motangge.mapper.BookMapper;
import com.motang.motangge.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 书籍表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 20:03
 */
@Service
@Slf4j
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book selectByBookName(String name) {
        return bookMapper.selectOne(new QueryWrapper<Book>().eq(WrapperEnum.NAME.getColumn(),name));
    }

    @Override
    public List<Book> selectBookList() {
        try {
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void saveBook(Book book) {
        try {
            bookMapper.insert(book);
        }catch (Exception e){
            log.error("保存书籍信息失败！");
            throw new BookException("保存书籍信息失败！");
        }
    }
}
