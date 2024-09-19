package com.msw.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子
 */
@Data
public class PictureVO implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "图片路径")
    private String url;

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