package com.motang.motangge.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(name = "name",value = "小说名称")
    private String name;

    @ApiModelProperty(name = "categoryId",value = "小说分类ID")
    private Long categoryId;

    @ApiModelProperty(name = "bookDescription",value = "小说描述")
    private String bookDescription;

    @ApiModelProperty(name = "bookFinal",value = "小说状态: 1正在更新 2已完结")
    private Integer bookFinal;

    @ApiModelProperty(name = "takeDown",value = "是否下架: 1 已上架 2下架")
    private Integer takeDown;

    @ApiModelProperty(name = "categoryName",value = "分类名称")
    private String categoryName;

    @ApiModelProperty(name = "imageUrl",value = "书籍封面")
    private String imageUrl;

    @ApiModelProperty(name = "attachmentId",value = "附件表ID")
    private Long attachmentId;

    @ApiModelProperty(name = "workDirection",value = "作品方向：1男频 2女频")
    private Integer workDirection;

    @ApiModelProperty(name = "authorName",value = "作者名称")
    private String authorName;

    @ApiModelProperty(name = "authorId",value = "作者Id")
    private Long authorId;

    @ApiModelProperty(name = "isVip",value = "是否是vip 1是  2否")
    private Integer isVip;

    @ApiModelProperty(name = "subscribe",value = "订阅数")
    private Long subscribe;

    @ApiModelProperty(name = "totalTextCount",value = "总字数")
    private String totalTextCount;

    @ApiModelProperty(name = "commentCount",value = "评论数")
    private Long commentCount;

    @ApiModelProperty(name = "visitCount",value = "点击量")
    private Long visitCount;

    @ApiModelProperty(name = "bookScore",value = "书籍评分")
    private Integer bookScore;

    @ApiModelProperty(name = "lastUpdateTime",value = "最后更新时间")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(name = "createTime",value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "updateTime",value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "status",value = "状态: 1正常 2已删除")
    private Integer status;

}
