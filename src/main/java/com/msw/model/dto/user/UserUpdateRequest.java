package com.msw.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String userName;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    @ApiModelProperty(value = "用户角色")
    private String userRole;

    private static final long serialVersionUID = 1L;
}