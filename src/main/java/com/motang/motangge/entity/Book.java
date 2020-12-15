package com.motang.motangge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Description 书籍表
 * @author liuhu
 * @Date 2020/12/10 20:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("book")
@ApiModel("书籍表")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小说Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 小说名称
     */
    private String name;

    /**
     * 小说分类ID
     */
    private String categoryId;

    /**小说描述*/
    private String bookDescription;

    /**
     * 小说状态: 1正在更新 2已完结
     */
    private Integer status;

    /**
     * 是否下架: 1 已上架 2下架
     */
    private Integer takeDown;
    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 书籍封面
     */
    private String imageUrl;

    /**
     * 作品方向：1男频 2女频
     */
    private Integer workDirection;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 作者Id
     */
    private Long authorId;

    /**
     * 是否是vip 1是  2否
     */
    private String isVip;

    /**
     * 订阅数
     */
    private Long subscribe;

    /**
     * 总字数
     */
    private Long totalTextCount;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 点击量
     */
    private Long visitCount;

    /**
     * 书籍评分
     */
    private Integer bookScore;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
