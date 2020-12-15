package com.motang.motangge.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description 书籍正文表
 * @author liuhu
 * @Date 2020/12/10 21:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("book_content")
@ApiModel("书籍正文表")
public class BookContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 正文Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "正文Id")
    private Long id;

    /**
     * 章节Id
     */
    @ApiModelProperty(name = "chapterId",value = "章节Id")
    private Long chapterId;

    /**
     * 章节内容
     */
    @ApiModelProperty(name = "content",value = "章节内容")
    private String content;


}
