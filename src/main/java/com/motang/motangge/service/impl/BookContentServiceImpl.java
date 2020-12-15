package com.motang.motangge.service.impl;

import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.entity.BookContent;
import com.motang.motangge.mapper.BookContentMapper;
import com.motang.motangge.service.IBookContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 书籍正文表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 21:19
 */
@Service
@Slf4j
public class BookContentServiceImpl  implements IBookContentService {

    @Autowired
    private BookContentMapper contentMapper;

    @Override
    public void saveBookContent(BookContent bookContent) {
        try {
            contentMapper.insert(bookContent);
        }catch (Exception e){
            log.error("保存书籍正文信息失败！");
            throw new BookException("保存书籍正文信息失败！");
        }
    }
}
