package com.msw.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新个人信息请求
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "userName")
    private String userName;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;

    /**
     * 简介
     */
    @ApiModelProperty(value = "userProfile")
    private String userProfile;

    private static final long serialVersionUID = 1L;
}