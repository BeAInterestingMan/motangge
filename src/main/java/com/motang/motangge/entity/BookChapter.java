package com.motang.motangge.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description 章节表
 * @author liuhu
 * @Date 2020/12/10 21:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("book_chapter")
public class BookChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节总数量
     */
    private Long totalCount;

    /**
     * 正文Id
     */
    private Long contentId;

    /**
     * 小说Id
     */
    private Long bookId;

    /**
     * 是否是vip 1-是 2否
     */
    private Integer isVip;

    /**
     * 所需积分
     */
    private Long costPoint;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
