package com.msw.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String userName;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String userAccount;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userAvatar;

    /**
     * 用户角色: user, admin
     */
    @ApiModelProperty(value = "用户角色")
    private String userRole;

    private static final long serialVersionUID = 1L;
}