package com.msw.model.dto.user;

import com.msw.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 开放平台id
     */
    @ApiModelProperty(value = "开放平台id")
    private String unionId;

    /**
     * 公众号openId
     */
    @ApiModelProperty(value = "公众号openId")
    private String mpOpenId;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String userName;

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