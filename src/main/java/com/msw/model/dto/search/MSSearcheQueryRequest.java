package com.msw.model.dto.search;

import com.msw.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 图片查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MSSearcheQueryRequest extends PageRequest implements Serializable {
    /**
     * 搜索词
     */
    @ApiModelProperty(value = "搜索词")
    private String searchText;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;

    private static final long serialVersionUID = 1L;
}