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
 * @Description 章节表
 * @author liuhu
 * @Date 2020/12/10 21:17
 */
@Data
@TableName("book_chapter")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("章节表")
public class BookChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "章节id")
    private Long id;

    @ApiModelProperty(name = "name",value = "章节名称")
    private String name;

    @ApiModelProperty(name = "totalCount",value = "章节描述")
    private String chapterDescription;

    @ApiModelProperty(name = "bookId",value = "小说Id")
    private Long bookId;

    @ApiModelProperty(name = "isVip",value = "是否是vip 1-是 2否")
    private Integer isVip;

    @ApiModelProperty(name = "costPoint",value = "所需积分")
    private Long costPoint;

    @ApiModelProperty(name = "createTime",value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime",value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
