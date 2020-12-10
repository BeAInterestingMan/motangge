package com.motang.motangge.service.impl;

import com.motang.motangge.entity.BookContent;
import com.motang.motangge.mapper.BookContentMapper;
import com.motang.motangge.service.IBookContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description 书籍正文表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 21:19
 */
@Service
public class BookContentServiceImpl extends ServiceImpl<BookContentMapper, BookContent> implements IBookContentService {

}
