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
 * @Description 书籍分类表
 * @author liuhu
 * @Date 2020/12/15 20:07
 */
@Data
@TableName("book_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="BookCategory对象", description="书籍分类表")
public class BookCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类书籍数量")
    private Long categoryCount;

    @ApiModelProperty(value = "作品方向，1：男频，2：女频'")
    private Integer workDirection;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
