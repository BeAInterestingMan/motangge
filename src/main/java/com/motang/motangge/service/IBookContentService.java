package com.motang.motangge.service;

import com.motang.motangge.entity.BookContent;

/**
 * @Description 书籍正文表 服务类
 * @author liuhu
 * @Date 2020/12/10 21:19
 */
public interface IBookContentService  {
    /**
     * @Description 保存书籍正文信息
     * @author liuhu
     * @Date 2020/12/15 22:27
     */
    void saveBookContent(BookContent bookContent);
}
