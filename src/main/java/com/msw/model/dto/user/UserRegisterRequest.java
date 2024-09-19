package com.msw.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 用户登录账号
     */
    @ApiModelProperty(value = "用户登录账号")
    private String userAccount;

    /**
     * 用户登录密码
     */
    @ApiModelProperty(value = "用户登录密码")
    private String userPassword;

    /**
     * 用户登录密码确认
     */
    @ApiModelProperty(value = "用户登录密码确认")
    private String checkPassword;
}
