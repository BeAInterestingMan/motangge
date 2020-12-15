package com.motang.motangge.service;

import com.motang.motangge.entity.BookCategory;

/**
 * @Description 书籍分类表 服务类
 * @author liuhu
 * @Date 2020/12/15 20:04
 */
public interface IBookCategoryService {
    /**
     * @description 保存书籍分类
     * @author liuhu
     * @param category
     * @date 2020/12/15 20:11
     * @return void
     */
    void saveBookCategory(BookCategory category);

    /**
     * @description 通过分类名称获取分类
     * @author liuhu
     * @param categoryName
     * @date 2020/12/15 20:52
     * @return com.motang.motangge.entity.BookCategory
     */
    BookCategory selectByName(String categoryName);
}
