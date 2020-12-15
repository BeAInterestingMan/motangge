package com.motang.motangge.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private Long categoryId;

    /**小说描述*/
    private String bookDescription;

    /**
     * 小说状态: 1正在更新 2已完结
     */
    private Integer bookFinal;

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
    private Integer isVip;

    /**
     * 订阅数
     */
    private Long subscribe;

    /**
     * 总字数
     */
    private String totalTextCount;

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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 状态: 1正常 2已删除
     */
    private Integer status;

}
