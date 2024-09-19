package com.msw.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子
 */
@TableName(value = "post")
@Data
public class Post implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 标签列表 json
     */
    @ApiModelProperty(value = "标签列表 json")
    private String tags;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer thumbNum;

    /**
     * 收藏数
     */
    @ApiModelProperty(value = "收藏数")
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    @ApiModelProperty(value = "创建用户 id")
    private Long userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除(删除：Y。未删除：N)")
    @TableLogic(value = "N", delval = "Y")
    private String isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}