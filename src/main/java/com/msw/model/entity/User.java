package com.msw.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@TableName(value = "user")
@Data
public class User implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String userAccount;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    /**
     * 开放平台id
     */
    @ApiModelProperty(value = "开放平台id")
    private String unionId;

    /**
     * 公众号openId
     */
    @ApiModelProperty("公众号openId")
    private String mpOpenId;

    /**
     * 公众号openId
     */
    @ApiModelProperty(value = "公众号openId")
    private String userName;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;

    /**
     * 用户简介
     */
    @ApiModelProperty(value = "用户简介")
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    @ApiModelProperty(value = "用户角色")
    private String userRole;

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