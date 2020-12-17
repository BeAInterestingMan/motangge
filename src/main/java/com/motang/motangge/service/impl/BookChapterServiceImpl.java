package com.motang.motangge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.motangge.common.commonEnum.WrapperEnum;
import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.entity.BookChapter;
import com.motang.motangge.mapper.BookChapterMapper;
import com.motang.motangge.service.IBookChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 章节表 服务实现类
 * @author liuhu
 * @Date 2020/12/10 21:19
 */
@Service
@Slf4j
public class BookChapterServiceImpl implements IBookChapterService {

    @Autowired
    private BookChapterMapper bookChapterMapper;

    @Override
    public void saveChapter(BookChapter bookChapter) {
        try {
            bookChapterMapper.insert(bookChapter);
        }catch (Exception e){
            log.error("保存书籍章节信息失败！");
            throw new BookException("保存书籍章节信息失败！");
        }
    }

    @Override
    public BookChapter selectByName(String chapterName) {
        return bookChapterMapper.selectOne(new QueryWrapper<BookChapter>().eq(WrapperEnum.NAME.getColumn(),chapterName));
    }
}
