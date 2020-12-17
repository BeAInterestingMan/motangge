package com.motang.motangge.service;

import com.motang.motangge.entity.Author;

/**
 * @Description 作者表 服务类
 * @author liuhu
 * @Date 2020/12/15 21:56
 */
public interface IAuthorService{
    /**
     * @Description 保存作者
     * @author liuhu
     * @Date 2020/12/15 22:06
     */
    void saveAuthor(Author authorO);

    /**
     * @description 根据书籍名称查找
     * @author liuhu
     * @param authorName
     * @date 2020/12/16 9:26
     * @return com.motang.motangge.entity.Author
     */
    Author selectByName(String authorName);
}
