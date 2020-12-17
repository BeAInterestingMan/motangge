package com.motang.motangge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.motangge.common.commonEnum.WrapperEnum;
import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.entity.Author;
import com.motang.motangge.mapper.AuthorMapper;
import com.motang.motangge.service.IAuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description  作者表 服务实现类
 * @author liuhu
 * @Date 2020/12/15 21:55
 */
@Service
@Slf4j
public class AuthorServiceImpl  implements IAuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public void saveAuthor(Author authorO) {
        try {
            authorMapper.insert(authorO);
        }catch (Exception e){
            log.error("保存书籍作者失败！");
            throw new BookException("保存书籍作者失败！");
        }
    }

    @Override
    public Author selectByName(String authorName) {
        return authorMapper.selectOne(new QueryWrapper<Author>().eq(WrapperEnum.PEN_NAME.getColumn(),authorName));
    }
}
