package com.motang.motangge.service;

import com.motang.motangge.entity.BookChapter;

/**
 * @Description 章节表 服务类
 * @author liuhu
 * @Date 2020/12/10 21:19
 */
public interface IBookChapterService  {
    /**
     * @Description 保存书籍章节
     * @author liuhu
     * @Date 2020/12/15 22:22
     */
    void saveChapter(BookChapter bookChapter);
}
