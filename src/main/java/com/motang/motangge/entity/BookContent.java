package com.motang.motangge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 书籍正文表
 * @author liuhu
 * @Date 2020/12/10 21:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
